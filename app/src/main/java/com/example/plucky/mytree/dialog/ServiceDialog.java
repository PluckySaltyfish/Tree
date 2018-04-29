package com.example.plucky.mytree.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.plucky.mytree.R;


public class ServiceDialog extends AlertDialog {
    private TextView confirm;
    public ServiceDialog(Context context, int themeResID) {
        super(context, themeResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_declaration);
        setCanceledOnTouchOutside(false);

        initView0();
    }


    protected void initView0() {
        confirm = (TextView)findViewById(R.id.button_confirm);
        confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_confirm)
            this.dismiss();
    }
}
