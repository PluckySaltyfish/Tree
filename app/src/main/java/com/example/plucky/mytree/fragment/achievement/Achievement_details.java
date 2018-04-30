package com.example.plucky.mytree.fragment.achievement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plucky.mytree.R;

public class Achievement_details extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mnameTextView,mdetailTextView,mstatusTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achieve_details);
        Intent intent=getIntent();
        int picture=intent.getIntExtra("picture",0);
        String details=intent.getStringExtra("details");
        int status=intent.getIntExtra("status",0);
        String names=intent.getStringExtra("names");

        mImageView=(ImageView)findViewById(R.id.achieveimage);
        mImageView.setImageResource(picture);
        mnameTextView=(TextView)findViewById(R.id.nametextView);
        mnameTextView.setText(names);
        mdetailTextView=(TextView)findViewById(R.id.detailtextView);
        mdetailTextView.setText(details);
        mstatusTextView=(TextView)findViewById(R.id.statustextView);
        if(status==0){
            mstatusTextView.setText("已完成");
        }
        else mstatusTextView.setText("未完成");
    }
}
