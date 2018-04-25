package com.example.plucky.mytree.watcher;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.plucky.mytree.chart.PairData;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.fragment.task.Task;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Check {
    private Context mContext;
    private RemoteData mRemoteData;
    private String TAG = "Check";
    public Check(Context context){
        this.mContext = context;
    }
    private void TaskCheck(){

    }

    private void AchievementCheck(){

    }

    private PairData[] ChartCheck(int chart,int currentDay,int currentMonth,int currentYear,String username){
        mRemoteData = new RemoteData(mContext);
        if (chart == 1){
            List<Task>list = mRemoteData.getWeeklyCreateTask(username,currentYear,currentMonth,currentDay);
            PairData []Data = new PairData[7];
            int []create = new int[7];

            for (int i = 0;i < 7;i++){
               Data[i] = new PairData(i,0);
            }
            for (int i = 0;i < list.size();i++){
                Task task = list.get(i);
                String createTime = task.getCreateTime();
                int week = getWeek(getTime(createTime,0),getTime(createTime,1),getTime(createTime,2));
                create[week]++;
            }

            int currentWeek = getWeek(currentYear,currentMonth,currentDay);
            int i =currentWeek;
            Data[6].setValueY(create[i]);
            for (i = 0;i < 6;i++){
                Data[i].setValueY(create[(i+currentWeek+1)%7]);
            }

            return Data;
        }
        return null;
    }

    public int getTime(String date,int i){
        String []time = date.split(" ");
        String []p_time = time[0].split("-");
        return Integer.parseInt(p_time[i]);

    }

    public int getWeek(int year,int month,int day){
        int c =20;
        int y = year%100;
        int m = month;
        if (month == 1 || month ==2){
            m = month + 12;
            y = y-1;
        }
        int w = y+y/4+c/4-2*c+26*(m+1)/10+day-1;
        return w%7;
    }

    @SuppressLint("SimpleDateFormat")
    public int[] getCurrentTime(){
        //获取当前时间
        int []c_time = new int[3];
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = new Date();
        String time=format.format(date);
        format=new SimpleDateFormat("yyyy");
        c_time[0]=Integer.parseInt(format.format(date));
        format=new SimpleDateFormat("MM");
        c_time[1]=Integer.parseInt(format.format(date));
        format=new SimpleDateFormat("dd");
        c_time[2]=Integer.parseInt(format.format(date));
        return c_time;
    }

    public void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://192.168.1.208:3000/user/user")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d(TAG, "run: "+responseData);
//                    parseJSONWithGSON(responseData);
//                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
