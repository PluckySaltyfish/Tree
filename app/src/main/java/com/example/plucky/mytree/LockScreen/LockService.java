package com.example.plucky.mytree.LockScreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plucky.mytree.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/2/13.
 */

public class LockService extends Service {

    //注册广播，广播监听和打开屏幕
    String minute="0";
    String second="0";
    int flag=0;
    private int CountDataHour,CountDataMinute,CountDataSecond;
    private int StartTimeHour,StartTimeMinute,StartTimeSecond;
    private int HourEndnow,MinuteEndnow,SecondEndnow;
    private int isend=0;
    private ImageView mImageView;

    private WindowManager windowManager;
    private Context mContext;
    private MyHandler handler;
    private Timer timer;
    private LayoutInflater inflater;
    private View mDesktopLayout;
    private TextView mTextView;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        LockService.this.registerReceiver(mScreenOffReceiver,
                filter);
    }

    //关闭service
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterComponent();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //flag=1开始，flag=0暂停
        String f=intent.getStringExtra("flag");
        if(f!=null){
            flag= Integer.parseInt(f);
        }
        //倒计时起始时间
        CountDataHour=intent.getIntExtra("CountDataHour",0);
        CountDataMinute=intent.getIntExtra("CountDataMinute",0);
        CountDataSecond=intent.getIntExtra("CountDataSecond",0);
        if(flag==1){
            //开始时系统时间
            StartTimeHour=intent.getIntExtra("StartTimeHour",0);
            StartTimeMinute=intent.getIntExtra("StartTimeMinute",0);
            StartTimeSecond=intent.getIntExtra("StartTimeSecond",0);
        }
        return Service.START_STICKY;
    }

    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)){

                Toast.makeText(LockService.this, "解锁被监听", Toast.LENGTH_SHORT).show();
                timecompute();

                WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
                mContext=getApplicationContext().getApplicationContext();
                //初始化后不首先获得窗口焦点。不妨碍设备上其他部件的点击、触摸事件。
                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.flags= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                params.flags= WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                params.windowAnimations= R.style.float_window_anim;
                params.height = 120;
                params.gravity= Gravity.TOP | Gravity.LEFT;
                params.y=0;
                params.x=0;

                inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mDesktopLayout = inflater.inflate(R.layout.float_layout, null);
                mTextView=(TextView) mDesktopLayout.findViewById(R.id.textisfinished);
                mImageView=(ImageView) mDesktopLayout.findViewById(R.id.floatimageView2);
                if(isend==1){
                    //已自动结束任务
                    mTextView.setText("已自动结束任务");
                    mImageView.setImageResource(R.drawable.bingo);
                }
                else{
                    //任务失败
                    mTextView.setText("任务失败");
                    mImageView.setImageResource(R.drawable.failed);
                }

                handler=new MyHandler();
                windowManager = (WindowManager) mContext.getSystemService(getApplication().WINDOW_SERVICE);
                windowManager.addView(mDesktopLayout, params);
                timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg=handler.obtainMessage();
                        handler.sendEmptyMessage(0x123);
                    }
                },5000);


                Intent intentFinishActivity=new Intent("FinishActivity");
                sendBroadcast(intentFinishActivity);
                LockService.this.stopSelf();
            }
            else if ((intent.getAction().equals(Intent.ACTION_SCREEN_ON) || intent.getAction().equals(Intent.ACTION_SCREEN_OFF))) {

                if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){

                    timecompute();
                        Intent intent1 = new Intent(LockService.this, LockActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle=new Bundle();
                        bundle.putString("flagg", String.valueOf(flag));
                        bundle.putString("minutes",minute);
                        bundle.putString("seconds",second);
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                }
                else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

                    Intent intent1 = new Intent(LockService.this, LockActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putString("flagg", String.valueOf(flag));
                    bundle.putString("minutes", minute);
                    bundle.putString("seconds", second);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                }
            }

        }
    };

    public void unregisterComponent() {
        // TODO Auto-generated method stub
        if (mScreenOffReceiver != null) {
            LockService.this.unregisterReceiver(mScreenOffReceiver);
        }
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what==0x123){
                windowManager.removeView(mDesktopLayout);
            }
        }
    }

    public void timecompute(){
        if (flag == 1) {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);

        HourEndnow = mCalendar.get(Calendar.HOUR);
        MinuteEndnow = mCalendar.get(Calendar.MINUTE);
        SecondEndnow = mCalendar.get(Calendar.SECOND);

        Log.i(TAG, "CountTime:" + CountDataHour + ":" + CountDataMinute + ":" + CountDataSecond);
        Log.i(TAG, "StartTime:" + StartTimeHour + ":" + StartTimeMinute + ":" + StartTimeSecond);
        Log.i(TAG, "EndTime:" + HourEndnow + ":" + MinuteEndnow + ":" + SecondEndnow);

        int hoursfinished = 0;
        int minutesfinished = 0;
        int secondsfinished = 0;

        int daltahour = (HourEndnow - StartTimeHour);
        if (daltahour > 0) {

            minutesfinished = 59 - StartTimeMinute + MinuteEndnow + (daltahour - 1) * 60;
            secondsfinished = 60 - StartTimeSecond + SecondEndnow;

            if (secondsfinished >= 60) {
                minutesfinished += secondsfinished / 60;
                secondsfinished = secondsfinished - (secondsfinished / 60) * 60;
            }
        }
        else {
            int daltaminute = (MinuteEndnow - StartTimeMinute);
            if (daltaminute > 0) {

                secondsfinished = 60 - StartTimeSecond + SecondEndnow;
                minutesfinished = daltaminute - 1;

                if (secondsfinished >= 60) {
                    minutesfinished += secondsfinished / 60;
                    secondsfinished = secondsfinished - (secondsfinished / 60) * 60;
                }
            }
            else {
                minutesfinished = 0;
                secondsfinished = SecondEndnow - StartTimeSecond;
            }
        }
        if (minutesfinished > 60) {
            hoursfinished = minutesfinished / 60;
            minutesfinished = minutesfinished - (minutesfinished / 60) * 60;
        }

        //1:20:30 2:00:00
        if (CountDataHour < hoursfinished) {
            minute = "0";
            second = "0";
        } else if (CountDataHour == hoursfinished) {
            //1:20:30 1:30:15
            if (CountDataMinute < minutesfinished) {
                minute = "0";
                second = "0";
            } else if (CountDataMinute == minutesfinished) {
                //1:20:30 1:20:45
                if (CountDataSecond <= secondsfinished) {
                    minute = "0";
                    second = "0";
                }
                //1:20:30 1:20:15
                else {
                    minute = "0";
                    second = String.valueOf(CountDataSecond - secondsfinished);
                }
            }
            //1:20:30 1:11:15
            else {
                int sec = (60 - secondsfinished) + CountDataSecond;
                int min = CountDataMinute - minutesfinished - 1;
                if (sec >= 60) {
                    min += sec / 60;
                    sec = sec - (sec / 60) * 60;
                }
                minute = String.valueOf(min);
                second = String.valueOf(sec);
            }
        }
        else {
            //1:20:30 0:30:10
            int sec = (60 - secondsfinished) + CountDataSecond;
            int min = (59 - minutesfinished) + CountDataMinute + (CountDataHour - hoursfinished - 1) * 60;
            if (sec >= 60) {
                min += sec / 60;
                sec = sec - (sec / 60) * 60;
            }
            minute = String.valueOf(min);
            second = String.valueOf(sec);
        }

    }
    else {
            int min = CountDataMinute + CountDataHour * 60;
            int sec = CountDataSecond;
            minute = String.valueOf(min);
            second = String.valueOf(sec);
        }

        if (minute == "0" && second == "0") {
            flag = 0;
            isend=1;
        }
    }
}
