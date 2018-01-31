
package com.example.plucky.mytree;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class task_fragment extends Fragment implements TaskAdapter.MyItemLongClickListener,TaskAdapter.MyItemClickListener {
    private List<Task> taskList = new ArrayList<>();
    private FloatingActionButton mAddFAB,mResourceFAB,mTaskFAB;
    private int ListCount;
    private  RecyclerView recyclerView;
    private AddTaskDialog mdialog;
    private TaskAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Button TimeFilter;
    private DatePicker mDatePicker;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable

    private static final String TAG = "task_fragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.task_fragment, container, false);

        recyclerView =(RecyclerView)v.findViewById(R.id.recycler_view);


        TimeFilter=(Button)v.findViewById(R.id.filter);
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
        TimeFilter.setText(time);
        TimeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                TimeSelector timeSelector = new TimeSelector(getActivity(), new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        Toast.makeText(getActivity(), time, Toast.LENGTH_LONG).show();
                    }
                }, "2015-11-22 17:34", "2015-12-1 15:20");
                timeSelector.setMode(TimeSelector.MODE.YMD);
                timeSelector.show();

            }





        });


        mResourceFAB=(FloatingActionButton)v.findViewById(R.id.resource_fab);
        mTaskFAB=(FloatingActionButton)v.findViewById(R.id.task_fab);
        mAddFAB=(FloatingActionButton)v.findViewById(R.id.add_fab);

        mTaskFAB.setVisibility(View.INVISIBLE);
        mResourceFAB.setVisibility(View.INVISIBLE);
        mTaskFAB.setEnabled(false);
        mResourceFAB.setEnabled(false);


        layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        UpdateUI();

        mAddFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListCount =adapter.getItemCount();
                mTaskFAB.setVisibility(View.VISIBLE);
                mResourceFAB.setVisibility(View.VISIBLE);
                mTaskFAB.setEnabled(true);
                mTaskFAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         mdialog= new AddTaskDialog(getActivity(), R.style.dialog, new AddTaskDialog.OnCloseListener() {

                            public void onClick(Dialog dialog, boolean confirm) {
                                if(confirm){
                                    Toast.makeText(getActivity(),"hahaha",Toast.LENGTH_SHORT).show();
                                    taskList.add(mdialog.getTask());
                                    UpdateUI();
                                    dialog.dismiss();
                                }

                            }
                        },ListCount);
                        mdialog.show();
                    }
                });
                mResourceFAB.setEnabled(true);
                mResourceFAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"resource",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
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


    private void getTasks(){
        for (int i = 0; i < 5 ; i=i+2) {
            Task task1 = new Task("我很帅很帅很帅我很帅" +
                    "很帅很帅我很帅很帅很帅我很帅很帅很帅我很帅" +
                    "很帅很帅我很帅很帅很帅我我很帅很帅很帅我很" +
                    "帅很帅很帅我很帅很帅很帅我很帅很帅很帅我很" +
                    "帅很帅很帅" +
                    "很帅很帅很帅",i);
            Task task2 = new Task("我很美很美我很美很美我很" +
                    "美很美我很美很美我很美很美我很美很美我很美很美我很美" +
                    "很美我很美很美我很美很" +
                    "美我很美很美我很美很美我很美很美",i+1);
            taskList.add(task1);
            taskList.add(task2);
        }
    }

    private void UpdateUI(){


        if (adapter == null) {
            getTasks();
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
        mTaskFAB.setVisibility(View.INVISIBLE);
        mResourceFAB.setVisibility(View.INVISIBLE);
        mTaskFAB.setEnabled(false);
        mResourceFAB.setEnabled(false);
    }
}

