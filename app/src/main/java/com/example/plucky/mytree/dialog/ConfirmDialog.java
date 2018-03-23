package com.example.plucky.mytree.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plucky.mytree.R;

public class ConfirmDialog extends Dialog implements View.OnClickListener{
    private TextView title,content,cancel,confirm;
    private ImageView icon;
    private String sTitle,sContent,sCancel,sConfirm;
    private int resID;
    private Context mContext;
    private OnCloseListener listener;
    public ConfirmDialog(Context context, int themeResID, OnCloseListener listener){
        super(context,themeResID);
        this.listener = listener;
        this.mContext = context;
    }

    protected ConfirmDialog(Context context, boolean cancelable, OnCancelListener cancelListener){
        super(context,cancelable,cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog);
        setCanceledOnTouchOutside(false);
        initView();

    }

    private void initView(){
        title = (TextView) findViewById(R.id.confirm_title);
        content =(TextView)  findViewById(R.id.confirm_content);
        icon = (ImageView) findViewById(R.id.confirm_icon);
        cancel = (TextView) findViewById(R.id.left_button);
        confirm = (TextView) findViewById(R.id.right_button);

        this.title.setText(sTitle);
        this.content.setText(sContent);
        this.cancel.setText(sCancel);
        this.confirm.setText(sConfirm);
        this.icon.setImageResource(resID);

        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    public void idolize(String title, String content, String cancel, String confirm, int resID){
        this.sTitle = title;
        this.sContent = content;
        this.sCancel = cancel;
        this.sConfirm = confirm;
        this.resID = resID;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_button:
                if (listener!=null){
                    listener.onClick(this,false);
                }
                this.dismiss();
                break;
            case R.id.right_button:
                if (listener!=null){
                    listener.onClick(this,true);
                }
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}
