package com.example.plucky.mytree;

import android.app.Dialog;
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
import com.example.plucky.mytree.dialog.AlertDialog;
import com.example.plucky.mytree.dialog.ConfirmDialog;
import com.example.plucky.mytree.dialog.InputDialog;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.local.UserSchema;
import com.example.plucky.mytree.login.LoginActivity;
import com.example.plucky.mytree.mail.SendMailUtil;
import com.example.plucky.mytree.watcher.Validation;

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
    private InputDialog mInputDialog;
    private Validation mValidation;
    private ConfirmDialog mConfirmDialog;
    private InputDialog inputDialog;
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
        mValidation = new Validation(SettingsActivity.this);
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
                mInputDialog = new InputDialog(SettingsActivity.this, R.style.dialog, new InputDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String text, int mode) {
                        if (confirm){
                            if (mValidation.isEmpty(text,"密码")!=1) {
                                if (mValidation.isRightLength(text)==1){
                                    mInputDialog.dismiss();
                                    mRemoteData.alterPassword(username,text);
                                    User user = mUsersManager.getUser(username);
                                    user.setPassword(text);
                                    mUsersManager.updateUser(user);
                                    AlertDialog alertDialog = new AlertDialog(SettingsActivity.this,R.style.dialog);
                                    alertDialog.idolize("修改成功","返回",R.drawable.smile);
                                    alertDialog.show();
                                }
                            }
                        }
                    }
                });
                mInputDialog.idolize("输入新密码","取消","确定");
                mInputDialog.show();
            }
        });




        mtaskimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //on:settings_treebuttonon off:settings_treebuttonof
                mtaskimage.setImageResource(R.drawable.settings_treebuttonon);
            }
        });

        msuggestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputDialog = new InputDialog(SettingsActivity.this, R.style.dialog, new InputDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String text, int mode) {
                        if (confirm){
                            SendMailUtil.send("1828151761@qq.com","text");
                        }
                    }
                });
                inputDialog.idolize("反馈与建议","取消","确定");
                inputDialog.show();
            }
        });

        mcontactus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mConfirmDialog = new ConfirmDialog(SettingsActivity.this, R.style.dialog, new ConfirmDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm){

                        }
                    }
                });
                mConfirmDialog.idolize("联系我们","QQ:182815161","取消","确定",R.drawable.smile);
                mConfirmDialog.show();
            }
        });

        mversion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SettingsActivity.this, "当前版本已经是最新", Toast.LENGTH_SHORT).show();
            }
        });

//        mversionimage.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//            }
//        });

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
