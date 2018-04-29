package com.example.plucky.mytree.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import com.example.plucky.mytree.dialog.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plucky.mytree.AvatarImageView;
import com.example.plucky.mytree.MainActivity;
import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.dialog.ServiceDialog;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.mail.SendMailUtil;
import com.example.plucky.mytree.watcher.Validation;

public class Login2Activity extends AppCompatActivity {

    private UsersManager mUserManager;
    private  EditText mEditText;
    private Button Login,ForgetPassword;
    private AvatarImageView mImageView;
    private ImageView mPasswordImageView;
    private EditText mPasswordEditText;
    private AlertDialog mAlertDialog;
    private TextView servicecontinue;
    private ServiceDialog mDialog;
    private RemoteData mRemoteData =new RemoteData(Login2Activity.this);
    private String UserId,password;
    private Validation mValidation = new Validation(Login2Activity.this);
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mUserManager=new UsersManager(Login2Activity.this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        servicecontinue = (TextView)findViewById(R.id.servicecontinue);
        mEditText=(EditText) findViewById(R.id.edituser);
        mPasswordEditText=(EditText) findViewById(R.id.password);
        mImageView=(AvatarImageView) findViewById(R.id.imageView6);
        mPasswordImageView=(ImageView) findViewById(R.id.imageView8);
        Login =(Button)findViewById(R.id.loginin);
        ForgetPassword=(Button)findViewById(R.id.forgetpassword);

        mEditText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    mImageView.setImageResource(R.drawable.me);
                }
                else {
                    UserId = mEditText.getText().toString();

                    User user = mUserManager.getUser(UserId);
                    if (user != null) {
                        String image = user.getPhoto();
                        if(image==null){
                            mImageView.setImageResource(R.drawable.me);
                        }
                        else{
                            Bitmap bitmap= BitmapFactory.decodeFile(image);
                            mImageView.setImageBitmap(bitmap);
                        }
                    }
                }
            }
        });

        servicecontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new ServiceDialog(Login2Activity.this,R.style.dialog);
                mDialog.show();
            }
        });
        mPasswordImageView.setImageResource(R.drawable.eye2);
        mPasswordImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageView8:
                        if (isHideFirst == true) {
                            mPasswordImageView.setImageResource(R.drawable.eye1);
                            //密文
                            HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                            mPasswordEditText.setTransformationMethod(method1);
                            isHideFirst = false;
                        } else {
                            mPasswordImageView.setImageResource(R.drawable.eye2);
                            //密文
                            TransformationMethod method = PasswordTransformationMethod.getInstance();
                            mPasswordEditText.setTransformationMethod(method);
                            isHideFirst = true;

                        }
                        // 光标的位置
                        int index = mPasswordEditText.getText().toString().length();
                        mPasswordEditText.setSelection(index);
                        break;

                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    UserId = mEditText.getText().toString();
                    password = mPasswordEditText.getText().toString();
                    if (mValidation.isEmpty(UserId,"用户名")!=1){
                        if (mValidation.isEmpty(password,"密码")!=1){
                            if(mRemoteData.ifExist(UserId)==1){
                                String true_password = mRemoteData.getPassword(UserId);
                                if (mValidation.isEqual(true_password,password,"密码错误")==1){
                                    User user = mUserManager.getUser(UserId);
                                    if (user == null){
                                        User user1 = new User(UserId,password);
                                        user1.setStatus(1);
                                        mUserManager.addUser(user1);
                                        user = user1;
                                    }

                                    user.setStatus(1);
                                    mUserManager.updateUser(user);
                                    Intent i = new Intent(Login2Activity.this, MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);

                                }
                            }
                            else{
                                mAlertDialog = new AlertDialog(Login2Activity.this,R.style.dialog);
                                mAlertDialog.idolize("用户不存在","确定",R.drawable.warning);
                                mAlertDialog.show();
                            }
                        }

                    }
            }
        });

        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserId = mEditText.getText().toString();
                if (mValidation.isEmpty(UserId,"用户名")!=1){

                    Intent i = new Intent(Login2Activity.this, RetrievePassword1.class);
                    i.putExtra("username",UserId);
                    startActivity(i);
                }


            }
        });

    }
}
