package com.example.plucky.mytree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Login2Activity extends AppCompatActivity {

    Users mUsers=new Users();

    private  EditText mEditText;
    private AvatarImageView mImageView;
    private ImageView mPasswordImageView;
    private EditText mPasswordEditText;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mEditText=(EditText) findViewById(R.id.edituser);
        mPasswordEditText=(EditText) findViewById(R.id.password);
        mImageView=(AvatarImageView) findViewById(R.id.imageView6);
        mPasswordImageView=(ImageView) findViewById(R.id.imageView8);

        mEditText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    mImageView.setImageResource(R.drawable.me);
                }
                else{
                    String userid=mEditText.getText().toString();

                    int image=mUsers.getImage(userid);
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

    }
}
