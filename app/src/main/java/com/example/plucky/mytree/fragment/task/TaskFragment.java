
package com.example.plucky.mytree.fragment.task;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
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

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.dialog.AddTaskDialog;
import com.example.plucky.mytree.dialog.TimeSelectorDialog;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskFragment extends Fragment implements TaskAdapter.MyItemLongClickListener,TaskAdapter.MyItemClickListener,FloatingToolbar.ItemClickListener {
    private List<Task> taskList = new ArrayList<>();
    private int ListCount;
    private  RecyclerView recyclerView;
    private com.example.plucky.mytree.dialog.AddTaskDialog AddTaskDialog;
    private TimeSelectorDialog mTimeSelectorDialog;
    private TaskAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton mFab;
    private FloatingToolbar mFloatingToolbar;
    private RemoteData mRemoteData = new RemoteData(getActivity());

    private DatePicker mDatePicker;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable

    private static final String TAG = "TaskFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.task_fragment, container, false);

        recyclerView =(RecyclerView)v.findViewById(R.id.recycler_view);


        mFloatingToolbar=(FloatingToolbar)v.findViewById(R.id.floatingToolbar);
        mFab=(FloatingActionButton)v.findViewById(R.id.add_fab);


        mFloatingToolbar.attachFab(mFab);
        mFloatingToolbar.attachRecyclerView(recyclerView);
        mFloatingToolbar.setClickListener(this);

        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = new Date();
        String time=format.format(date);
        final int year,month,day;
        format=new SimpleDateFormat("yyyy");
        year=Integer.parseInt(format.format(date));
        format=new SimpleDateFormat("MM");
        month=Integer.parseInt(format.format(date));
        format=new SimpleDateFormat("dd");
        day=Integer.parseInt(format.format(date));




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
            taskList= mRemoteData.getTaskList();
            adapter = new TaskAdapter(taskList);
            adapter.setOnItemLongClickListener(this);
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {


            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
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
                taskList.remove(position);
                adapter.notifyDataSetChanged();
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
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(),"Click "+position,Toast.LENGTH_SHORT).show();
        Task mTask = taskList.get(position);
        if (mTask.getTimeLimit()==0){
            AlertDialog.Builder dialog = new AlertDialog.Builder (getActivity());
            dialog.setTitle("开始");
            dialog.setMessage("要开始此任务吗？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.
                    OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //锁屏倒计时
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

    }

    @Override
    public void onItemClick(MenuItem item) {
        Log.d(TAG, "onItemClick: "+item.getItemId());
        switch (item.getItemId()){

            case R.id.add_task:
                AddTaskDialog = new AddTaskDialog(getActivity(), R.style.dialog, new AddTaskDialog.OnCloseListener() {

                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            Toast.makeText(getActivity(),"hahaha",Toast.LENGTH_SHORT).show();
                            taskList.add(AddTaskDialog.getTask());
                            mRemoteData.addTask(AddTaskDialog.getTask());
                            UpdateUI();
                            dialog.dismiss();
                        }

                    }
                },ListCount);
                AddTaskDialog.show();
                break;
            case R.id.resources:
                break;

            case R.id.filter:
                mTimeSelectorDialog = new TimeSelectorDialog(getActivity(), R.style.dialog, new TimeSelectorDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm,int year,int month,int day) {
                        if (confirm){
                            taskList.clear();
                            taskList.addAll(mRemoteData.getFilteredTask(year,month,day));
                            UpdateUI();
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
}

