package com.example.plucky.mytree.login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.plucky.mytree.MainActivity;
import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.dialog.AlertDialog;
import com.example.plucky.mytree.dialog.ConfirmDialog;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.watcher.Validation;


public class Register extends AppCompatActivity {
    private Validation mValidation ;
    private  EditText psw;
    private  EditText vpsw;
    private EditText username,mailbox;
    private Button buttonreg;
    private ConfirmDialog mConfirmDialog;
    private UsersManager mUsersManager;
    private RemoteData mRemoteData;
    private String psw1,psw2,usr;
    private AlertDialog mAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mValidation = new Validation(Register.this);
        mUsersManager = new UsersManager(Register.this);
        mRemoteData=new RemoteData(Register.this);


        psw=(EditText) findViewById(R.id.editpsw);
        vpsw=(EditText) findViewById(R.id.verifypd);
        username=(EditText)findViewById(R.id.edituser);
        mailbox=(EditText)findViewById(R.id.editmail);


        buttonreg=(Button) findViewById(R.id.buttonregister);
        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mailbox.getText().toString();
                usr = username.getText().toString();
                psw1 = vpsw.getText().toString();
                psw2 = psw.getText().toString();
                if (mValidation.isEmpty(usr,"用户名")==0){
                    if (mValidation.isEmpty(psw2,"密码")==0){
                        if (mValidation.isEmpty(psw1,"重复密码")==0){
                            if (mValidation.isEmpty(mail,"邮箱")==0){
                                if(mValidation.isEqual(psw1,psw2,"密码不一致")==1){
                                    if (mValidation.isRightLength(psw1)==1){
                                        if (mRemoteData.ifExist(usr)!=1){
                                            mRemoteData.addUser(usr,psw2,mail);
                                            mConfirmDialog = new ConfirmDialog(Register.this, R.style.dialog, new ConfirmDialog.OnCloseListener() {
                                                @Override
                                                public void onClick(Dialog dialog, boolean confirm) {
                                                    if (confirm){
                                                        mConfirmDialog.dismiss();
                                                        User user1 = new User(usr,psw2);
                                                        user1.setStatus(1);
                                                        mUsersManager.addUser(user1);
                                                        Intent i = new Intent(Register.this, MainActivity.class);
                                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(i);
                                                    }
                                                }
                                            });
                                            mConfirmDialog.idolize("注册","注册成功，欢迎加入！","取消","登录",R.drawable.smile);
                                            mConfirmDialog.show();


                                        }
                                        else{
                                            mAlertDialog = new AlertDialog(Register.this,R.style.dialog);
                                            mAlertDialog.idolize("用户已存在","返回",R.drawable.warning);
                                            mAlertDialog.show();
                                        }
                                    }


                                }
                            }
                        }
                    }
                }

            }
        });
    }
}
