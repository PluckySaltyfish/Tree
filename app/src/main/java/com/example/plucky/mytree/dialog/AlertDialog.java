package com.example.plucky.mytree.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.plucky.mytree.R;

public class AlertDialog extends Dialog implements View.OnClickListener{
    private TextView title,confirm;
    private ImageView icon;
    private String sTitle,sConfirm;
    private int resID;
    private Context mContext;
    public AlertDialog(Context context, int themeResID)

    {
        super(context,themeResID);
        this.mContext = context;
    }

    protected AlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener){
        super(context,cancelable,cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        setCanceledOnTouchOutside(false);
        initView();

    }

    protected void initView(){
        title = (TextView) findViewById(R.id.alert_title);

        icon = (ImageView) findViewById(R.id.alert_icon);

        confirm = (TextView) findViewById(R.id.alert_button);

        this.title.setText(sTitle);
        this.confirm.setText(sConfirm);
        this.icon.setImageResource(resID);

        confirm.setOnClickListener(this);
    }

    public void idolize(String title, String confirm, int resID){
        this.sTitle = title;
        this.sConfirm = confirm;
        this.resID = resID;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.alert_button)
            this.dismiss();
    }

}
