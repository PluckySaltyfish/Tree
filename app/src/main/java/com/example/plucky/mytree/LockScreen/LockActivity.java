package com.example.plucky.mytree.LockScreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.mytree.R;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/2/13.
 */

public class LockActivity extends Activity {

    private long hour=0;
    private long minute=0;
    private long second=0;
    private TextView hoursTv,minutesTv,secondsTv;
    private boolean isRun = false;
    private Button start,pause,end;
    private final static int REQUEST_CODE=1;
    int flag=0;

    private FinishReceiver mFinshReceiver;

    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                hoursTv.setText(String.valueOf(hour)+"");
                minutesTv.setText(String.valueOf(minute)+"");
                secondsTv.setText(String.valueOf(second)+"");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mFinshReceiver=new FinishReceiver();
        registerReceiver(mFinshReceiver,new IntentFilter("FinishActivity"));

        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_lock);



        Bundle bundle=this.getIntent().getExtras();
        String min=bundle.getString("minutes");
        String sec= bundle.getString("seconds");
        int minutes= Integer.parseInt(min);
        int seconds= Integer.parseInt(sec);

        hour=minutes/60;
        minute=minutes-hour*60;
        second=seconds;

        hoursTv = (TextView) findViewById(R.id.hours_tv);
        minutesTv = (TextView) findViewById(R.id.minutes_tv);
        secondsTv = (TextView) findViewById(R.id.seconds_tv);

        hoursTv.setText(String.valueOf(hour));
        minutesTv.setText(String.valueOf(minute));
        secondsTv.setText(String.valueOf(second));

        start=(Button) findViewById(R.id.startbutton);
        pause=(Button) findViewById(R.id.pausebutton);
        end=(Button) findViewById(R.id.endbutton);

        String f=bundle.getString("flagg");

            if(Integer.parseInt(f)==1){
                flag=1;

                StartService(flag, Integer.parseInt(hoursTv.getText().toString()), Integer.parseInt(minutesTv.getText().toString()), Integer.parseInt(secondsTv.getText().toString()),1);

                start.setEnabled(false);
                pause.setEnabled(true);
                isRun=true;
                startRun();
            }
            else{
                if(hour==0 && minute==0 && second==0){
                    start.setEnabled(false);
                    pause.setEnabled(false);
                    end.setEnabled(true);
                    isRun=false;
                }
                else{
                    pause.setEnabled(false);
                    start.setEnabled(true);
                    isRun=false;
                }
                flag=0;
                StartService(flag, Integer.parseInt(hoursTv.getText().toString()), Integer.parseInt(minutesTv.getText().toString()), Integer.parseInt(secondsTv.getText().toString()),0);
            }

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                flag=1;
                StartService(flag, Integer.parseInt(hoursTv.getText().toString()), Integer.parseInt(minutesTv.getText().toString()), Integer.parseInt(secondsTv.getText().toString()),1);
                start.setEnabled(false);
                pause.setEnabled(true);
                isRun=true;
                startRun();
            }
        });
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hour= Integer.parseInt(hoursTv.getText().toString());
                minute= Integer.parseInt(minutesTv.getText().toString());
                second= Integer.parseInt(secondsTv.getText().toString());

                flag=0;
                StartService(flag, Integer.parseInt(hoursTv.getText().toString()), Integer.parseInt(minutesTv.getText().toString()), Integer.parseInt(secondsTv.getText().toString()),0);
                pause.setEnabled(false);
                start.setEnabled(true);
                isRun=false;
            }
        });
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(hour==0&&minute==0&&second==0) {
                    //finish();
                }
                else {
                    Toast.makeText(LockActivity.this, "alert dialog", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //开启倒计时
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        if(isRun){
                            Message message = Message.obtain();
                            message.what = 1;
                            timeHandler.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //倒计时计算
    private void computeTime() {
        second--;

        if(hour==0&&minute==0&&second==0){
            isRun=false;
            flag=0;
            StartService(flag,0,0,0,0);
            start.setEnabled(false);
            pause.setEnabled(false);
        }
        else{
            if (second <0) {
                minute--;
                second = 59;
                if (minute <0) {
                    minute = 59;
                    hour--;
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        // do nothing
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        hoursTv = (TextView) findViewById(R.id.hours_tv);
        //Toast.makeText(LockActivity.this,hoursTv.getText() , Toast.LENGTH_LONG).show();
        minutesTv.setText(String.valueOf(minute));
        secondsTv.setText(String.valueOf(second));
        if(hoursTv.getText().toString().equals("0")&&minutesTv.getText().toString().equals("0")&&secondsTv.getText().toString().equals("0")){
            //更新任务成功信息
        }

        unregisterReceiver(mFinshReceiver);
        Log.i(TAG,"LockActivity -onDestroy");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"LockActivity -onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"LockActivity -onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();

        hoursTv = (TextView) findViewById(R.id.hours_tv);
        //Toast.makeText(LockActivity.this,hoursTv.getText() , Toast.LENGTH_LONG).show();
        minutesTv.setText(String.valueOf(minute));
        secondsTv.setText(String.valueOf(second));
        if(hoursTv.getText().toString().equals("0")&&minutesTv.getText().toString().equals("0")&&secondsTv.getText().toString().equals("0")){
            //更新任务成功信息
        }

        Log.i(TAG,"LockActivity -onStop");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"LockActivity -onPause");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"LockActivity -onRestart");
    }

    public void StartService(int fl,int countdatahour,int countdataminute,int countdatasecond,int flagisone){
        Intent intent=new Intent(LockActivity.this,LockService.class);
        intent.putExtra("flag", String.valueOf(fl));
        intent.putExtra("CountDataHour",countdatahour);
        intent.putExtra("CountDataMinute",countdataminute);
        intent.putExtra("CountDataSecond",countdatasecond);
        if(flagisone==1){
            long time= System.currentTimeMillis();
            final Calendar mCalendar= Calendar.getInstance();
            mCalendar.setTimeInMillis(time);
            int Hournow=mCalendar.get(Calendar.HOUR);
            int Minutenow=mCalendar.get(Calendar.MINUTE);
            int Secondnow=mCalendar.get(Calendar.SECOND);

            intent.putExtra("StartTimeHour",Hournow);
            intent.putExtra("StartTimeMinute",Minutenow);
            intent.putExtra("StartTimeSecond",Secondnow);
        }
        startService(intent);
    }


    public class FinishReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            finish();
        }
    }
}