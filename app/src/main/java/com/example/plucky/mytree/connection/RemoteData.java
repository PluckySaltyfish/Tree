package com.example.plucky.mytree.connection;


import android.content.Context;

import com.example.plucky.mytree.connection.LocalData;
import com.example.plucky.mytree.fragment.task.Task;

import java.util.ArrayList;
import java.util.List;

public class RemoteData {

    private Context mContext;

    public RemoteData(Context mContext){
        mContext=mContext;
    }

    private LocalData mLocalData = new LocalData(mContext,"tree",null,1);

    public int verifyUser(String username, String password){
//        String t_password = "null";
//        if (password.equals(t_password))return 1;
//        return 0;
        return 1;

    }

    public void addTask(Task task){

    }

    public List<Task> getTaskList(){
        List<Task> TaskList = new ArrayList<>();
        //get task list
        for (int i = 0; i < 5 ; i=i+2) {
            Task task1 = new Task("我很帅很帅很帅我很帅" +
                    "很帅很帅我很帅很帅很帅我很帅很帅很帅我很帅",i);
            Task task2 = new Task(
                    "美很美我很美很美我很美很美我很美很美我很美很美我很美" +
                            "很美我很美很美我很美很" ,i+1);
            TaskList.add(task1);
            TaskList.add(task2);
        }
        return TaskList;
    }

    public List<Task> getFilteredTask(int year, int month, int day){
        //获取year,month,day日期前的所有任务

        List<Task> TaskList = new ArrayList<>();
        for (int i = 0; i < 5 ; i=i+2) {
            Task task1 = new Task("唐雨馨么么哒",i);
            Task task2 = new Task(
                    "琦玉老师我爱你" ,i+1);
            TaskList.add(task1);
            TaskList.add(task2);
        }
        return TaskList;
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

}
