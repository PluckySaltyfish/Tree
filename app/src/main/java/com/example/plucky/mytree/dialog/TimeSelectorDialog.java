package com.example.plucky.mytree.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.example.plucky.mytree.R;

import java.util.Calendar;

public class TimeSelectorDialog extends Dialog implements  View.OnClickListener{
    private TextView cancel,confirm;
    private Context mContext;
    private WheelDatePicker datePicker;
    private OnCloseListener listener;
    public TimeSelectorDialog(Context context, int themeResID, OnCloseListener listener){
        super(context,themeResID);
        this.mContext=context;
        this.listener = listener;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_picker);
        setCanceledOnTouchOutside(false);
        initView();

    }

    private void initView(){
        Calendar now = Calendar.getInstance();
        datePicker =(WheelDatePicker)findViewById(R.id.datePicker);
        confirm=(TextView)findViewById(R.id.submit);
        cancel=(TextView)findViewById(R.id.cancel);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        datePicker.setVisibleItemCount(3);
        datePicker.setSelectedItemTextColor(0xFF4bcbe0);
        datePicker.setYearStart(now.get(Calendar.YEAR));
    }


    public interface  OnCloseListener{
        void onClick(Dialog dialog, boolean confirm, int year, int month, int day);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.cancel:
                if(listener != null){
                    listener.onClick(this, false,0,0,0);
                }
                this.dismiss();
                break;

            case R.id.submit:
                if(listener != null){
                    listener.onClick(this, true,datePicker.getCurrentYear(),datePicker.getCurrentMonth(),datePicker.getCurrentDay());
                }
                break;
        }


    }
}
