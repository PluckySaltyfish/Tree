package com.example.plucky.mytree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login2Activity extends AppCompatActivity {

    Users mUsers=new Users();

    private  EditText mEditText;
    private Button Login,ForgetPassword;
    private AvatarImageView mImageView;
    private ImageView mPasswordImageView;
    private EditText mPasswordEditText;
    private Connection mConnection=new Connection();
    private String UserId;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView =getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

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
                else{
                    UserId=mEditText.getText().toString();

                    int image=mUsers.getImage(UserId);
                    mImageView.setImageResource(image);

                }
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
                if (mConnection.VerifyUser(UserId,mPasswordEditText.getText().toString())==1){
                    Intent i = new Intent(Login2Activity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                else{
                    //Alert Dialog show"登录失败:用户名或密码错误"
                }
            }
        });

        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start forgetpassword activity
//                Intent i = new Intent(Login2Activity.this, xxx.class);
//                startActivity(i);
            }
        });

    }
}
