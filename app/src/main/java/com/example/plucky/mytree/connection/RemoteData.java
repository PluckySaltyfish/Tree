package com.example.plucky.mytree.connection;


import android.content.Context;

import com.example.plucky.mytree.fragment.task.Task;

import java.util.ArrayList;
import java.util.List;

public class RemoteData {

    private Context mContext;

    public RemoteData(Context mContext){
        mContext=mContext;
    }

/*-----------------------------USER------------------------------------*/
    public int verifyUser(String username, String password){
//        String t_password = "null";
//        if (password.equals(t_password))return 1;
//        return 0;
        return 1;

    }

    public String getMailbox(){
        //get mailbox
        String mailbox="1828151761@qq.com";
        return mailbox;
    }

    public void alterPassword(String password){
        //change user's password
    }

    public void addUser(String username,String password,String mailbox){
        //add user
    }
/*-----------------------Task----------------------------------*/
    public void addTask(Task task){

    }

    public void deleteTask(int taskID){

    }

    public void setStatus(int taskID,int status){

    }

    public List<Task> getTaskList(int year,int month,int day){
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

}
