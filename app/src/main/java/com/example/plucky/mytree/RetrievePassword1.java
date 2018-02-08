package com.example.plucky.mytree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/2/9.
 */

public class RetrievePassword1 extends AppCompatActivity {

    private EditText musermail;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_retrieve1);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        musermail=(EditText) findViewById(R.id.usermail);
        next=(Button) findViewById(R.id.buttonnext);

        musermail.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){

                }
                else{

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RetrievePassword1.this, RetrievePassword2.class);
                startActivity(i);
            }
        });
    }



}
