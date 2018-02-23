package com.example.plucky.mytree.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;



public class Register extends AppCompatActivity {

    private  EditText psw;
    private  EditText vpsw;
    private EditText username,mailbox;
    private Button buttonreg;

    private RemoteData mRemoteData=new RemoteData(Register.this);
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

        psw=(EditText) findViewById(R.id.editpsw);
        vpsw=(EditText) findViewById(R.id.verifypd);
        username=(EditText)findViewById(R.id.edituser);
        mailbox=(EditText)findViewById(R.id.editmail);


        buttonreg=(Button) findViewById(R.id.buttonregister);
        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vpsw.getText().toString().equals(psw.getText().toString())){
                    mRemoteData.addUser(username.getText().toString(),psw.getText().toString(),mailbox.getText().toString());
                    Toast.makeText(Register.this, "password confirmed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this, "password mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
