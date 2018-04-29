package com.example.plucky.mytree.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.plucky.mytree.R;

public class InputDialog extends Dialog implements View.OnClickListener{

    private TextView title,left,right;
    private EditText input;
    private Context mContext;
    private OnCloseListener listener;
    private String sTitle,sLeft,sRight;
    public InputDialog(Context context, int themeResID, OnCloseListener listener){
        super(context,themeResID);
        this.listener = listener;
        this.mContext = context;
    }

    public InputDialog(Context context, int themeResID, OnCloseListener listener, int count){
        super(context,themeResID);
        this.listener = listener;
        this.mContext = context;
    }

    protected InputDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener){
        super(context,cancelable,cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog);
        setCanceledOnTouchOutside(false);
        initView();

    }

    private void initView(){
        title = (TextView)findViewById(R.id.input_title);
        left = (TextView)findViewById(R.id.left);
        right = (TextView)findViewById(R.id.right);
        input = (EditText)findViewById(R.id.dialog_input);


        title.setText(sTitle);
        left.setText(sLeft);
        right.setText(sRight);



        right.setOnClickListener(this);
        left.setOnClickListener(this);

    }

    public void idolize(String title, String left, String right){

        this.sLeft = left;
        this.sRight = right;
        this.sTitle = title;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left:
                if (listener!=null){
                    listener.onClick(this,false,input.getText().toString(),1);
                }
                this.dismiss();
                break;
            case R.id.right:
                if (listener!=null){
                    if (this.title.getText().toString().equals("输入新密码"))
                        listener.onClick(this,true,input.getText().toString(),2);
                    else
                        listener.onClick(this,true,input.getText().toString(),1);
                }
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm,String text,int mode);
    }

    public void changeTitle(String s){
        this.title.setText(s);
        this.input.setText("");
    }
}
