package com.example.plucky.mytree;


import java.util.ArrayList;
import java.util.List;

class Connection {

    int VerifyUser(String username,String password){
//        String t_password = "null";
//        if (password.equals(t_password))return 1;
//        return 0;
        return 1;

    }

    void AddTask(Task task){

    }

    List<Task> getTaskList(){
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

    List<Task> getFilteredTask(int year, int month, int day){
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

}
