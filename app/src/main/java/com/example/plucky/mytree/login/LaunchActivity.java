package com.example.plucky.mytree.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.plucky.mytree.MainActivity;
import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LaunchActivity extends AppCompatActivity {
    private RemoteData mRemoteData;
    private UsersManager mUsersManager;
    private List<User> list;
    private Timer timer;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        mRemoteData = new RemoteData(LaunchActivity.this);
        mUsersManager = new UsersManager(LaunchActivity.this);
        list = mUsersManager.getUsers();

        flag = 0;
        for (int i = 0; i < list.size();i++){
            User user = list.get(i);
            if (user.getStatus()==1)flag=1;
        }

        TimerTask task = new TimerTask(){
            public void run(){
                if (flag==1){
                    Intent i = new Intent(LaunchActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(LaunchActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 2000);










    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
