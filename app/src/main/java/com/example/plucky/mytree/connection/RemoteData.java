package com.example.plucky.mytree.connection;


import android.content.Context;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.fragment.task.Task;
import com.example.plucky.mytree.store.StoreItem;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Store;

public class RemoteData {

    private Context mContext;

    public RemoteData(Context mContext){
        mContext=mContext;
    }

/*-----------------------------USER------------------------------------*/

    public int ifExist(String username){
        return 1;
    }

    public int getTreeExp(String username){
        return 20;
    }

    public void setTreeExp(String username,int exp){

    }

    public int getTreeNumber(String username){
        return 1;
    }

    public void setTreeNumber(String username,int number){

    }

    public int getCoin(String username){return 30;}

    public void setCoin(String username,int coin){}

    public int getExp(String username){
        return 30;
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
    public String addTask(String username,Task task){
        //添加Task的同时返回taskID
        return "00000";
    }

    public void deleteTask(int taskID){

    }

    public void setStatus(int taskID,int status){

    }

    public void setTimes(int taskID,int times){

    }

    public List<Task> getTaskList(String username,int year,int month,int day){
        List<Task> TaskList = new ArrayList<>();
        /*get task whose start time before the input time and
         the end time behind the input time*/
        for (int i = 0; i < 5 ; i=i+2) {
            Task task1 = new Task("我很帅很帅很帅我很帅" +
                    "很帅很帅我很帅很帅很帅我很帅很帅很帅我很帅",i);
            Task task2 = new Task(
                    "美很美我很美很美我很美很美我很美很美我很美很美我很美" +
                            "很美我很美很美我很美很" ,i+1);
            task2.setTimeLimit(0);
            TaskList.add(task1);
            TaskList.add(task2);
        }

        //check status
        for (int i = 0;i < TaskList.size();i++){
            if (TaskList.get(i).getTimeLimit()==0)
                if (TaskList.get(i).getStatus()==0)
                    TaskList.get(i).setStatus(1);
        }
        return TaskList;
    }

    public List<Task> getMonthlyTaskList(String username,int year,int month,int day){
        List<Task> TaskList = new ArrayList<>();
        return TaskList;
    }

    public List<Task> getWeeklyTaskList(String username,int year,int month){
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
        for (int i = 0; i <= 2;i++){
            StoreItem item0 = new StoreItem(R.drawable.book1,"么么哒");
            StoreItem item1 = new StoreItem(R.drawable.book2,"呵呵哒");
            ItemList.add(item0);
            ItemList.add(item1);
        }
        return ItemList;
    }



    public  List<StoreItem> getPurchasedItem(String username){
        List<StoreItem>ItemList = new ArrayList<>();
        return ItemList;
    }

    public  List<StoreItem> getPurchasedSeed(String username){
        List<StoreItem>ItemList = new ArrayList<>();
        return ItemList;
    }


    public void addPurchased(String username,String ItemID){

    }

    /*-------------Resource----------------*/

    public List<StoreItem>getResource(){
        List<StoreItem>ItemList = new ArrayList<>();
        return ItemList;
    }




}
