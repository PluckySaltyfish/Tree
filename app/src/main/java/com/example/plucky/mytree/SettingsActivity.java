package com.example.plucky.mytree;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity  extends AppCompatActivity {
    private ImageView topimageview;
    private TextView mnamechange,mpasswordchange,mportraitchange;
    private TextView mtasknotification,mtexttimenotification;
    private TextView msuggestion,mcontactus;
    private TextView mversion,mclean,mquit;
    private ImageView mtaskimage,mtexttimeimage,mversionimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        topimageview=(ImageView)findViewById(R.id.topimage);

        mnamechange=(TextView)findViewById(R.id.changename);
        mpasswordchange=(TextView)findViewById(R.id.changepassword);
        mportraitchange=(TextView)findViewById(R.id.changeportrait);

        mtasknotification=(TextView)findViewById(R.id.tasknotification);
        mtexttimenotification=(TextView)findViewById(R.id.texttimenotification);
        mtaskimage=(ImageView)findViewById(R.id.taskimage);
        mtexttimeimage=(ImageView)findViewById(R.id.texttimeimage);

        msuggestion=(TextView)findViewById(R.id.suggestion);
        mcontactus=(TextView)findViewById(R.id.contactus);

        mversion=(TextView)findViewById(R.id.version);
        mclean=(TextView)findViewById(R.id.clean);
        mquit=(TextView)findViewById(R.id.quit);
        mversionimage=(ImageView)findViewById(R.id.versionimage);

        topimageview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //退出设置
                Toast.makeText(SettingsActivity.this, "退出", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mnamechange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "修改用户名称", Toast.LENGTH_SHORT).show();
            }
        });

        mpasswordchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "修改用户密码", Toast.LENGTH_SHORT).show();
            }
        });

        mportraitchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "修改用户头像", Toast.LENGTH_SHORT).show();
            }
        });

        mtasknotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "任务日常提醒", Toast.LENGTH_SHORT).show();
            }
        });

        mtaskimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //on:settings_treebuttonon off:settings_treebuttonof
                Toast.makeText(SettingsActivity.this, "任务日常提醒图标", Toast.LENGTH_SHORT).show();
            }
        });

        mtexttimenotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "考试时间倒计时", Toast.LENGTH_SHORT).show();
            }
        });

        mtexttimeimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //on:settings_treebuttonon off:settings_treebuttonof
                Toast.makeText(SettingsActivity.this, "考试时间倒计时图标", Toast.LENGTH_SHORT).show();
            }
        });

        msuggestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "用户反馈与建议", Toast.LENGTH_SHORT).show();
            }
        });

        mcontactus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "联系我们", Toast.LENGTH_SHORT).show();
            }
        });

        mversion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "版本", Toast.LENGTH_SHORT).show();
            }
        });

        mversionimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //on:settings_updatebuttonon off:settings_updatebuttonof
                Toast.makeText(SettingsActivity.this, "版本图标", Toast.LENGTH_SHORT).show();
            }
        });

        mclean.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "清除缓存", Toast.LENGTH_SHORT).show();
            }
        });

        mquit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
