
package com.example.plucky.mytree.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.fragment.task.Task;

import org.angmarch.views.NiceSpinner;
import org.feezu.liuli.timeselector.TimeSelector;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AddTaskDialog  extends Dialog implements View.OnClickListener{


    private RadioGroup mRadioGroup;
    private Button TimeSelector1,TimeSelector2;
    private TextView ConfirmTxt;
    private TextView CancelTxt;
    private EditText TaskContent;
    private NiceSpinner TimeSpinner;
    private Context mContext;
    private OnCloseListener listener;
    private Task mTask;
    private ArrayAdapter adapter;
    private int count;
    private String now;


    public AddTaskDialog(Context context, int themeResId, OnCloseListener listener,int count) {
        //test
        super(context, themeResId);
        this.mContext = context;
        this.count = count;
        this.listener = listener;
    }

    protected AddTaskDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        this.count=count;
    }


    private static final String TAG = "AddTaskDialog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);
        setCanceledOnTouchOutside(false);
        initView();



    }

    private void initView(){
        mTask = new Task(count);
        TimeSpinner =(NiceSpinner)findViewById(R.id.time_limit);
        TaskContent=(EditText)findViewById(R.id.task_content);
        List<String> data = new LinkedList<>(Arrays.asList("120","60", "40", "30", "20","不限时"));
        TimeSpinner.attachDataSource(data);

        TimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        mTask.setTimeLimit(120);
                        break;
                    case 1:
                        mTask.setTimeLimit(60);
                        break;
                    case 2:
                        mTask.setTimeLimit(40);
                        break;
                    case 3:
                        mTask.setTimeLimit(30);
                        break;
                    case 4:
                        mTask.setTimeLimit(20);
                        break;
                    case 5:
                        mTask.setTimeLimit(0);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTask.setTimeLimit(120);
            }
        });



        mRadioGroup=(RadioGroup)findViewById(R.id.type_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.typical_task:
                        mTask.setType(0);
                        break;
                    case R.id.inherent_task:
                        mTask.setType(1);
                        break;
                }

            }
        });

        TimeSelector1=(Button)findViewById(R.id.time_select1);
        TimeSelector1.setOnClickListener(this);

        TimeSelector2=(Button)findViewById(R.id.time_select2);
        TimeSelector2.setOnClickListener(this);



        ConfirmTxt = (TextView)findViewById(R.id.submit);
        ConfirmTxt.setOnClickListener(this);
        CancelTxt = (TextView)findViewById(R.id.cancel);
        CancelTxt.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                if(listener != null){
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if(listener != null){
                    listener.onClick(this, true);
                }
                mTask.setContent(TaskContent.getText().toString());
                Log.d(TAG, "onClick: "+mTask.toString());
                break;
            case R.id.time_select1:
                if (listener!=null){
                    listener.onClick(this,false);
                }
                now=getNow();
                TimeSelector timeSelector = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        TimeSelector1.setText(time);
                        mTask.setStartTime(time);
                    }
                }, now, "2032-12-12 15:20");
                timeSelector.show();
                break;
            case R.id.time_select2:
                if (listener!=null){
                    listener.onClick(this,false);
                }
                now=getNow();
                TimeSelector timeSelector2 = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        TimeSelector2.setText(time);
                        mTask.setEndTime(time);
                    }
                }, now, "2032-12-12 15:20");
                timeSelector2.show();
                break;


        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

    public Task getTask() {
        return mTask;
    }


    public String getNow(){
        Date now = new Date();
        String date;
        date = mTask.TimeFormatter(now);
        return date;
    }

}


