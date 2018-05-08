package com.example.plucky.mytree.connection;


import android.content.Context;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.fragment.task.Task;
import com.example.plucky.mytree.store.StoreItem;

import java.util.ArrayList;
import java.util.Collections;
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
        return 200;
    }

    public void setTreeExp(String username,int exp){

    }

    public int getTreeNumber(String username){
        return 1;
    }

    public void setTreeNumber(String username,int number){

    }

    public int getCoin(String username){return 300;}

    public void setCoin(String username,int coin){}

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
    public String addTask(String username,Task task){
        //添加Task的同时返回taskID
        return "00000";
    }

    public void deleteTask(int taskID){

    }

    public void setStatus(int taskID,int status){

    }

    public void incTimes(int taskID){

    }



    public List<Task> getTaskList(String username,int year,int month,int day){
        List<Task> TaskList = new ArrayList<>();
        /*get task whose start time before the input time and
         the end time behind the input time*/
        for (int i = 0; i < 5 ; i=i+3) {
            Task task1 = new Task("操作系统一二章习题" ,i);
            task1.setDeadline("2018-04-19 12:17:41");
            Task task2 = new Task(
                    "计算机网络试卷" ,i+1);
            task2.setTimeLimit(0);
            task2.setDeadline("2018-04-19 12:17:42");
            Task task3 = new Task(
                    "嵌入式大作业" ,i+2);
            task3.setTimeLimit(0);
            task3.setDeadline("2018-04-19 12:17:40");
            TaskList.add(task1);
            TaskList.add(task2);
            TaskList.add(task3);
        }

        //check status
        for (int i = 0;i < TaskList.size();i++){
            if (TaskList.get(i).getTimeLimit()==0)
                if (TaskList.get(i).getStatus()==0)
                    TaskList.get(i).setStatus(1);
        }
        Collections.sort(TaskList);
        return TaskList;
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
        StoreItem item0 = new StoreItem("book1","Linux命令");
        item0.setPrice(25);
        StoreItem item1 = new StoreItem("book2","词根大百科");
        item1.setPrice(35);
        StoreItem item2 = new StoreItem("book3","前缀大百科");
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

        StoreItem item0 = new StoreItem("book1","Linux命令");
        item0.setPrice(25);
        StoreItem item1 = new StoreItem("book2","词根大百科");
        item1.setPrice(35);
        StoreItem item2 = new StoreItem("book3","前缀大百科");
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




}
