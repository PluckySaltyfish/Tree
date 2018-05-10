package com.example.plucky.mytree.connection;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.fragment.task.Task;
import com.example.plucky.mytree.store.StoreItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.Store;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RemoteData {

    private Context mContext;
    private String result;
    private final String HOST ="119.23.203.164:80";
    List<String>list = new ArrayList<>();
    List<Integer>list1 = new ArrayList<>();
    List<Task>list2 = new ArrayList<>();


    public RemoteData(Context mContext){
        mContext=mContext;
    }

/*-----------------------------USER------------------------------------*/

    public int ifExist(final String username){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json; charset=utf-8");
                //使用JSONObject封装参数
                String json = "{\"usrName\":\"" +username+"\"}";
                RequestBody requestBody = RequestBody.create(JSON,json);

                Request request = new Request
                        .Builder()
                        .post(requestBody)//Post请求的参数传递
                        .url("http://"+HOST+"/exist")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().string();
                    list = parseJSONtoString(result);
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        SystemClock.sleep(1000);
        for (int i = 0;i<list.size();i++){
            if (list.get(i).equals(username))
                return 1;
        }
        return 0;
    }

    public int getTreeExp(String username){
        return 200;
    }

    public void setTreeExp(String username,int exp){

    }

    public int getTreeNumber(String username){
        return 1;
    }

    public void setTreeNumber(String username,int number){

    }

    public int getCoin(final String username){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json; charset=utf-8");
                //使用JSONObject封装参数
                String json = "{\"usrName\":\"" +username+"\"}";
                RequestBody requestBody = RequestBody.create(JSON,json);

                Request request = new Request
                        .Builder()
                        .post(requestBody)//Post请求的参数传递
                        .url("http://"+HOST+"/getCoin")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().string();
                    list1 = parseJSONtoint(result,"coin");
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        SystemClock.sleep(1000);
        return list1.get(0);
    }

    public void setCoin(final String username,final int coin){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json; charset=utf-8");
                //使用JSONObject封装参数
                String json = "{\"usrName\":\""+username+"\",\"coin\":\""+coin+"\"}";
                RequestBody requestBody = RequestBody.create(JSON,json);

                Request request = new Request
                        .Builder()
                        .post(requestBody)//Post请求的参数传递
                        .url("http://"+HOST+"/setCoin")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().string();
                    list1 = parseJSONtoint(result,"coin");
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public int getExp(String username){
        return 1000;
    }

    public void setExp(String name,int exp){

    }

    public String getPassword(String username){
        return "123456";
    }

    public String getMailbox(String username){
        //get mailbox
        String mailbox="1828151761@qq.com";
        return mailbox;
    }

    public void alterPassword(String username,String password){
        //change user's password
    }

    public void addUser(String username,String password,String mailbox){
        //add user
    }

/*-----------------------TASK----------------------------------*/
    public void addTask(final String username,final Task task){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json; charset=utf-8");
                //使用JSONObject封装参数
                String json = "{\"usrName\":\""+username+"\",\"timelimit\":"+task.getTimeLimit()+",\"status\":"+task.getStatus()+",\"create_time\":\""
                        +task.getCreateTime()+"\",\"deadline\":\""
                        +task.getDeadline()+"\",\"content\":\""+task.getContent()+"\",\"times\":"+task.getTimes()+",\"type\":"+task.getType()+"}";
                RequestBody requestBody = RequestBody.create(JSON,json);
                Request request = new Request
                        .Builder()
                        .post(requestBody)//Post请求的参数传递
                        .url("http://"+HOST+"/addTask")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();

                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void deleteTask(int taskID){

    }

    public void setStatus(int taskID,int status){

    }

    public void setTimes(int taskID){

    }



    public List<Task> getTaskList(final String username,final int year,final int month,final int day){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json; charset=utf-8");
                //使用JSONObject封装参数
                String json = "{\"usrName\":\""+username+"\",\"year\":\""+year+"\",\"month\":\""+month+"\",\"day\":\""+day+"\"}";
                RequestBody requestBody = RequestBody.create(JSON,json);

                Request request = new Request
                        .Builder()
                        .post(requestBody)//Post请求的参数传递
                        .url("http://"+HOST+"/getTaskList")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().string();
                    list2 = parseJSONtoTask(result);
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        SystemClock.sleep(1500);
        return list2;
    }

    public List<Task> getMonthlyTaskList(String username,int year,int month){
        List<Task> TaskList = new ArrayList<>();
        return TaskList;
    }

    public List<Task> getWeeklyTaskList(String username,int year,int month,int day){
        List<Task> TaskList = new ArrayList<>();
        return TaskList;
    }

    public List<Task> getWeeklyCreateTask(String username,int year,int month,int day){
        List<Task> TaskList = new ArrayList<>();
        return TaskList;
    }

    public void setFinishTime(int taskID,int year,int month,int day){

    }


    /*-----------------Achievement--------------------*/
    public List<Integer>getAchievementID(String username){
        List<Integer>list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(18);
        list.add(5);
        list.add(7);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(8);
        list.add(6);
        return list;
    }

    public void addAchievement(String username,int medalID){}


    /*------------------Medal-------------------------*/
    public List<Integer>getMedalID(){
        List<Integer>list = new ArrayList<>();
        list.add(1);
        return list;
    }

    /*---------------Purchased------------------------*/
    public List<StoreItem> getPurchasedBook(String username){
        List<StoreItem>ItemList = new ArrayList<>();

        StoreItem item0 = new StoreItem("book1","词根大百科");
        StoreItem item1 = new StoreItem("book2","前缀大百科");
        ItemList.add(item0);
        ItemList.add(item1);

        return ItemList;
    }



    public  List<StoreItem> getPurchasedItem(String username){
        List<StoreItem>ItemList = new ArrayList<>();
        StoreItem item0 = new StoreItem("book1","前缀大百科");
        item0.setPrice(25);
        //StoreItem item1 = new StoreItem("book2","词根大百科");
        //item1.setPrice(35);

        ItemList.add(item0);
        //ItemList.add(item1);


        StoreItem item6 = new StoreItem("tree1","萤火树");
        item6.setPrice(230);
        StoreItem item7 = new StoreItem("tree2","樱花树");
        item7.setPrice(230);

        ItemList.add(item6);
        ItemList.add(item7);
        return ItemList;
    }

    public  List<StoreItem> getPurchasedSeed(String username){
        List<StoreItem>ItemList = new ArrayList<>();
        StoreItem item6 = new StoreItem("tree1","萤火树");
        item6.setPrice(230);
        ItemList.add(item6);
        return ItemList;
    }


    public void addPurchased(String username,String ItemID){

    }

    /*-------------Resource----------------*/

    public List<StoreItem>getResource(){
        List<StoreItem>ItemList = new ArrayList<>();

        StoreItem item0 = new StoreItem("book1","前缀大百科");
        item0.setPrice(25);
        StoreItem item1 = new StoreItem("book2","词根大百科");
        item1.setPrice(35);
        StoreItem item2 = new StoreItem("book3","Linux命令");
        item2.setPrice(35);
        StoreItem item3 = new StoreItem("book4","后缀大百科");
        item3.setPrice(77);
        StoreItem item4 = new StoreItem("book5","前缀大百科2");
        item4.setPrice(88);
        StoreItem item5 = new StoreItem("book6","后缀大百科2");
        item5.setPrice(88);
        ItemList.add(item0);
        ItemList.add(item1);
        ItemList.add(item2);
        ItemList.add(item3);
        ItemList.add(item4);
        ItemList.add(item5);

        StoreItem item6 = new StoreItem("tree1","萤火树");
        item6.setPrice(230);
        StoreItem item7 = new StoreItem("tree2","樱花树");
        item7.setPrice(230);

        ItemList.add(item6);
        ItemList.add(item7);



        return ItemList;
    }

    public void postDatawithOKhttp(final String json){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType JSON=MediaType.parse("application/json; charset=utf-8");
                //使用JSONObject封装参数

                RequestBody requestBody = RequestBody.create(JSON, json);

                Request request = new Request
                        .Builder()
                        .post(requestBody)//Post请求的参数传递
                        .url(HOST+"/exist")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().string();
                    //parseJSONWithJSONObject(result);
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private List<String> parseJSONtoString(String jsonData){
        List<String>list = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(jsonObject.getString("username"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private List<Integer> parseJSONtoint(String jsonData,String name){
        int num;
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list1.add(Integer.valueOf(jsonObject.getString(name)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list1;
    }


    private List<Task> parseJSONtoTask(String jsonData){

        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int task_id = jsonObject.getInt("task_id");
                Task task = new Task(task_id);
                task.setTimeLimit(jsonObject.getInt("time_limit"));
                task.setStatus(jsonObject.getInt("status"));
                task.setDeadline(jsonObject.getString("deadline").substring(0,19).replace("T"," "));
                task.setContent(jsonObject.getString("content"));
                task.setCreateTime(jsonObject.getString("create_time"));
                task.setTimes(jsonObject.getInt("times"));
                task.setType(jsonObject.getInt("type"));
                list2.add(task);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list2;
    }








}
