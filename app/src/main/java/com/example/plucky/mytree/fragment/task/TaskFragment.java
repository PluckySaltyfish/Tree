
package com.example.plucky.mytree.fragment.task;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.plucky.mytree.LockScreen.LockActivity;
import com.example.plucky.mytree.LockScreen.LockService;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.dialog.AddTaskDialog;
import com.example.plucky.mytree.dialog.ConfirmDialog;
import com.example.plucky.mytree.dialog.ResourceDialog;
import com.example.plucky.mytree.dialog.TimeSelectorDialog;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.store.BookStart;
import com.example.plucky.mytree.store.StoreItem;
import com.example.plucky.mytree.watcher.Check;
import com.example.plucky.mytree.watcher.Validation;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskFragment extends Fragment implements TaskAdapter.MyItemLongClickListener,TaskAdapter.MyItemClickListener,FloatingToolbar.ItemClickListener {
    private List<Task> taskList = new ArrayList<>();
    private int ListCount;
    private Check mCheck;
    private  RecyclerView recyclerView;
    private int currentYear,currentMonth,currentDay;
    private UsersManager mUsersManager;
    private String username;
    private com.example.plucky.mytree.dialog.AddTaskDialog AddTaskDialog;
    private TimeSelectorDialog mTimeSelectorDialog;
    private ConfirmDialog mConfirmDialog;
    private TaskAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton mFab;
    private FloatingToolbar mFloatingToolbar;
    private ResourceDialog mResourceDialog;
    private RemoteData mRemoteData = new RemoteData(getActivity());
    private StoreItem mStoreItem;
    private DatePicker mDatePicker;
    private Validation mValidation;

    public KeyguardManager keyguardManager = null;
    public KeyguardManager.KeyguardLock keyguardLock = null;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable

    private static final String TAG = "TaskFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.task_fragment, container, false);
        mCheck = new Check(getActivity());
        mUsersManager = new UsersManager(getActivity());
        username = mUsersManager.getUsername();
        //获取当前时间
        int []time = mCheck.getCurrentTime();
        currentYear = time[0];
        currentMonth = time[1];
        currentDay = time[2];
        mValidation = new Validation(getActivity());

        recyclerView =(RecyclerView)v.findViewById(R.id.recycler_view);

        mFloatingToolbar=(FloatingToolbar)v.findViewById(R.id.floatingToolbar);
        mFab=(FloatingActionButton)v.findViewById(R.id.add_fab);
        mFloatingToolbar.attachFab(mFab);
        mFloatingToolbar.attachRecyclerView(recyclerView);
        mFloatingToolbar.setClickListener(this);

        layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        UpdateUI();

        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    private void UpdateUI(){


        if (adapter == null) {
            taskList= mRemoteData.getTaskList(username,currentYear,currentMonth,currentDay);
            adapter = new TaskAdapter(taskList);
            adapter.setOnItemLongClickListener(this);
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //adapter.notifyAll();
        }
    }

    private void Reload(int year,int month ,int day){
        taskList.clear();
        taskList.addAll(mRemoteData.getTaskList(username,year,month,day));
        Collections.sort(taskList);
        UpdateUI();
    }
    @Override
    public void onItemLongClick(View view, final int position){

        AlertDialog.Builder dialog = new AlertDialog.Builder (getActivity());
        dialog.setTitle("删除任务");
        dialog.setMessage("确定要删除此任务吗？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRemoteData.deleteTask(taskList.get(position).getTaskID());
                taskList.remove(position);
                UpdateUI();
                //Reload(currentYear,currentMonth,currentDay);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); dialog.show();
    }

    @Override
    public void onItemClick(View view, final int position) {
        Toast.makeText(getActivity(),"Click "+position,Toast.LENGTH_SHORT).show();
        final Task mTask = taskList.get(position);
        if (mTask.getType()==0){
            if (mTask.getStatus()==0) {
                mConfirmDialog = new ConfirmDialog(getActivity(), R.style.dialog, new ConfirmDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            //锁屏倒计时
                            mTask.setTimes(mTask.getTimes() + 1);
                            mRemoteData.setTimes(mTask.getTaskID());
                            UpdateUI();

                            int timelimit = taskList.get(position).getTimeLimit();
                            String minute = String.valueOf(timelimit);
                            String second = "00";
                            initKeyguardManager();

                            Intent i = new Intent(getActivity(), LockActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("minutes", minute);
                            bundle.putString("seconds", second);
                            bundle.putString("flagg", "0");
                            bundle.putInt("taskID",mTask.getTaskID());
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                        mConfirmDialog.dismiss();
                    }
                });
                mConfirmDialog.idolize("开始任务", "确定要开始此任务吗？", "取消", "确定", R.drawable.frog);
                mConfirmDialog.show();
            }

            else if(mTask.getStatus()==1){
                mConfirmDialog = new ConfirmDialog(getActivity(), R.style.dialog, new ConfirmDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            mTask.setTimes(1);
                            adapter.notifyItemChanged(position);
                            UpdateUI();
                            //mRemoteData.setTimes(mTask.getTaskID(),mTask.getTimes());
                            //Reload(currentYear,currentMonth,currentDay);
                            mConfirmDialog.dismiss();
                        }
                    }
                });
                mConfirmDialog.idolize("每日检查", "今日此任务计划已完成？", "取消", "完成", R.drawable.frog);
                mConfirmDialog.show();
            }
        }else{
            String bookid = mTask.getContent();
            if (bookid.equals("前缀大百科"))bookid="book1";
            else bookid = "bool2";
            BookStart bookStart= new BookStart(getActivity());
            bookStart.startReading(bookid,mTask.getTaskID());
        }


    }

    @Override
    public void onItemClick(MenuItem item) {
        Log.d(TAG, "onItemClick: "+item.getItemId());
        switch (item.getItemId()){

            case R.id.add_task:
                AddTaskDialog = new AddTaskDialog(getActivity(), R.style.dialog, new AddTaskDialog.OnCloseListener() {

                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            Task mTask = AddTaskDialog.getTask();
                            if (mValidation.isEmpty(mTask.getContent(),"任务内容")!=1){
                                if (mValidation.isEmpty(mTask.getDeadline(),"结束日期")!=1){
                                    if (mValidation.isEmpty(mTask.getCreateTime(),"开始时间")!=1){
                                        taskList.add(mTask);
                                        Collections.sort(taskList);
                                        mRemoteData.addTask(username,AddTaskDialog.getTask());
                                        UpdateUI();
                                        //Reload(currentYear,currentMonth,currentDay);
                                        dialog.dismiss();
                                    }
                                }
                            }

                        }

                    }
                },ListCount,username);
                AddTaskDialog.show();
                break;
            case R.id.resources:
                mResourceDialog = new ResourceDialog(getActivity(), R.style.dialog, new ResourceDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        mStoreItem = mResourceDialog.getItem();
                        BookStart bookStart = new BookStart(getActivity());
                        bookStart.startReading(mStoreItem.getId(),0);

                    }
                },username,0);
                mResourceDialog.show();
                break;

            case R.id.filter:
                mTimeSelectorDialog = new TimeSelectorDialog(getActivity(), R.style.dialog, new TimeSelectorDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm,int year,int month,int day) {
                        if (confirm){
                            Reload(year,month,day);
                            dialog.dismiss();
                        }

                    }
                });

                mTimeSelectorDialog.show();
                break;

        }
    }

    @Override
    public void onItemLongClick(MenuItem item) {

    }

    //初始化和取消系统锁屏，启动服务
    private void initKeyguardManager() {
        keyguardManager =
                (KeyguardManager)getActivity().getSystemService(Context.KEYGUARD_SERVICE);
        keyguardLock = keyguardManager.newKeyguardLock("");

        keyguardLock.disableKeyguard();//取消系统锁屏
        getActivity().startService(new Intent(getActivity(), LockService.class));
    }


}

