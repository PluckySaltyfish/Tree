package com.example.plucky.mytree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.login.LoginActivity;

public class SettingsActivity  extends AppCompatActivity {
    private ImageView topimageview;
    private TextView mnamechange,mpasswordchange,mportraitchange;
    private TextView mtasknotification,mtexttimenotification;
    private TextView msuggestion,mcontactus;
    private TextView mversion,mclean,mquit;
    private ImageView mtaskimage,mtexttimeimage,mversionimage;
    private UsersManager mUsersManager;
    private RemoteData mRemoteData;
    private String username;
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

        mpasswordchange=(TextView)findViewById(R.id.changepassword);

        mtasknotification=(TextView)findViewById(R.id.tasknotification);
        mtaskimage=(ImageView)findViewById(R.id.taskimage);

        msuggestion=(TextView)findViewById(R.id.suggestion);
        mcontactus=(TextView)findViewById(R.id.contactus);

        mversion=(TextView)findViewById(R.id.version);
        mquit=(TextView)findViewById(R.id.quit);
        mversionimage=(ImageView)findViewById(R.id.versionimage);

        mRemoteData = new RemoteData(SettingsActivity.this);
        mUsersManager = new UsersManager(SettingsActivity.this);
        username = mUsersManager.getUsername();

        topimageview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });


        mpasswordchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "修改用户密码", Toast.LENGTH_SHORT).show();
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

        mquit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                User user = mUsersManager.getUser(username);
                user.setStatus(0);
                mUsersManager.updateUser(user);

                Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

}
