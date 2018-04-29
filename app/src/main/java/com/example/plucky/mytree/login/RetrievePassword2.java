package com.example.plucky.mytree.login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.plucky.mytree.MainActivity;
import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.dialog.AlertDialog;
import com.example.plucky.mytree.dialog.ConfirmDialog;
import com.example.plucky.mytree.dialog.InputDialog;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.mail.SendMailUtil;
import com.example.plucky.mytree.watcher.Validation;

import java.util.Timer;
import java.util.TimerTask;


public class RetrievePassword2 extends AppCompatActivity {
    private Button signin;
    private Button again;
    private Timer timer;
    private String code;
    private String mailbox;
    private Validation mValidation;
    private InputDialog mInputDialog;
    private ConfirmDialog mConfirmDialog;
    private RemoteData mRemoteData;
    private UsersManager mUsersManager;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_retrieve2);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mUsersManager = new UsersManager(RetrievePassword2.this);
        mRemoteData = new RemoteData(RetrievePassword2.this);
        mValidation = new Validation(RetrievePassword2.this);
        Intent i = getIntent();
        code = i.getStringExtra("code");
        mailbox = i.getStringExtra("mailbox");
        username = i.getStringExtra("username");

        signin = (Button) findViewById(R.id.buttonsignin);
        again = (Button) findViewById(R.id.buttonagain);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputDialog = new InputDialog(RetrievePassword2.this, R.style.dialog, new InputDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, final String text, int mode) {
                        if (confirm){
                            if (mode == 1){
                                if (text.equals(code)){
                                    mInputDialog.changeTitle("输入新密码");
                                }
                                else
                                {
                                    AlertDialog alertDialog = new AlertDialog(RetrievePassword2.this,R.style.dialog);
                                    alertDialog.idolize("验证码错误","确认",R.drawable.warning);
                                    alertDialog.show();
                                }
                            }
                            else{
                                if (mValidation.isEmpty(text,"新密码")!=1) {
                                    if (mValidation.isRightLength(text) == 1) {
                                        mInputDialog.dismiss();
                                        mRemoteData.alterPassword(username, text);
                                        mConfirmDialog = new ConfirmDialog(RetrievePassword2.this, R.style.dialog, new ConfirmDialog.OnCloseListener() {
                                            @Override
                                            public void onClick(Dialog dialog, boolean confirm) {
                                                if (confirm) {
                                                    User user = new User(username, text);
                                                    user.setStatus(1);
                                                    if (mValidation.isExist(username) == 1) {
                                                        mUsersManager.updateUser(user);
                                                    } else {
                                                        mUsersManager.addUser(user);
                                                    }

                                                    Intent i = new Intent(RetrievePassword2.this, MainActivity.class);
                                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(i);

                                                    mConfirmDialog.dismiss();


                                                }
                                            }
                                        });
                                        mConfirmDialog.idolize("修改密码", "修改成功,欢迎登录！", "取消", "登录", R.drawable.smile);
                                        mConfirmDialog.show();
                                    }
                                }

                            }
                        }
                    }
                });
                mInputDialog.idolize("输入验证码","取消","确定");
                mInputDialog.show();

            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = mValidation.PasswordGenerator();
                SendMailUtil.send(mailbox,code);
            }
        });
    }
}
