package com.example.plucky.mytree.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.mail.SendMailUtil;
import com.example.plucky.mytree.watcher.Validation;
import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class RetrievePassword1 extends AppCompatActivity {

    private EditText musermail;
    private Button next;
    private Validation mValidation;
    private RemoteData mRemoteData;
    private UsersManager mUsersManager;
    private String mailbox;
    private String username;
    private String vali_code;

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

        mValidation = new Validation(RetrievePassword1.this);
        mUsersManager = new UsersManager(RetrievePassword1.this);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        musermail=(EditText) findViewById(R.id.usermail);
        next=(Button) findViewById(R.id.buttonnext);
        mRemoteData  =new RemoteData(RetrievePassword1.this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailbox = musermail.getText().toString();
                if (mValidation.isEmpty(mailbox,"邮箱")!=1){
                    String true_mailbox = mRemoteData.getMailbox(username);
                    if (mValidation.isEqual(mailbox,true_mailbox,"邮箱输入错误")==1){
                        vali_code = mValidation.PasswordGenerator();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    SendMailUtil.send("1828151761@qq.com",vali_code);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        Intent i = new Intent(RetrievePassword1.this, RetrievePassword2.class);
                        i.putExtra("code",vali_code);
                        i.putExtra("mail",mailbox);
                        i.putExtra("username",username);
                        startActivity(i);

                    }
                }

            }
        });
    }



}
