package com.example.plucky.mytree.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.example.plucky.mytree.AvatarImageView;
import com.example.plucky.mytree.MainActivity;
import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;

import java.util.List;

public class Login2Activity extends AppCompatActivity {

    private UsersManager mUserManager;
    private  EditText mEditText;
    private Button Login,ForgetPassword;
    private AvatarImageView mImageView;
    private ImageView mPasswordImageView;
    private EditText mPasswordEditText;
    private RemoteData mRemoteData =new RemoteData(Login2Activity.this);
    private String UserId;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mUserManager=new UsersManager(Login2Activity.this);

        final User userfortext=new User("CXY","123456");
        User usertext=mUserManager.getUser("CXY");

        if(usertext==null){
            mUserManager.addUser(userfortext);
        }
        else{
            usertext.setStatus(0);
            mUserManager.updateUser(usertext);
        }
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
                if (mRemoteData.verifyUser(UserId,mPasswordEditText.getText().toString())==1){
                    UserId = mEditText.getText().toString();
                    if(UserId.equals("")){
                        Toast.makeText(Login2Activity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        User user = mUserManager.getUser(UserId);
                        List<User> users = mUserManager.getUsers();
                        int flag=0;
                        int length = users.size();
                        for(int i=0;i<length;i++){
                            if(users.get(i).getUsername().equals(UserId)){
                                flag=1;
                            }
                        }
                        if(flag==1){
                            if(user.getPassword().equals(mPasswordEditText.getText().toString())){
                                user.setStatus(1);
                                mUserManager.updateUser(user);
                                Toast.makeText(Login2Activity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login2Activity.this, MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(Login2Activity.this, "密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Login2Activity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                        }

                    }

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
                Intent i = new Intent(Login2Activity.this, RetrievePassword1.class);
                startActivity(i);
            }
        });

    }
}
