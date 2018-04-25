package com.example.plucky.mytree.fragment.task;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Comparable{
    private String content;
    private int taskID;
    private int type;
    //0--普通任务
    //1--内置任务
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    private int times = 0;

    private int TimeLimit=120;
    //0--限时
    //1--不限时
    private int status=0;
    //0--未开始 yellow
    //1--进行中 blue
    //2--已完成 green
    //3--未完成 red
    private String createTime,deadline,finishtime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTimeLimit() {
        return TimeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        TimeLimit = timeLimit;
    }
    public Task(String content, int taskID) {
        this.content = content;
        this.taskID = taskID;
        this.deadline ="2017-11-08 08:11:50";
    }

    public Task(int taskID) {
        this.taskID = taskID;
        this.content="";
        this.deadline = "2017-11-08 08:11:50";
    }

    @Override
    public String toString() {
        return "Task{" +
                "content='" + content + '\'' +
                ", taskID=" + taskID +
                ", type=" + type +
                ", TimeLimit=" + TimeLimit +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }

    String getContent() {
        return content;
    }

    public int getTaskID() {
        return taskID;
    }

    public String TimeFormatter(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }



    @Override
    public int compareTo(Object task1) {
        Task task2=(Task) task1;
        String []time2=task2.getDeadline().split("");
        String []time1= deadline.split("");
        int year1 = Integer.parseInt(time1[0])*1000+Integer.parseInt(time1[1])*100+Integer.parseInt(time1[2])*10+Integer.parseInt(time1[3]);
        int month1 = Integer.parseInt(time1[5])*10+Integer.parseInt(time1[6]);
        int day1 = Integer.parseInt(time1[8])*10+Integer.parseInt(time1[9]);
        int hour1 = Integer.parseInt(time1[11])*10+Integer.parseInt(time1[12]);
        int minute1 = Integer.parseInt(time1[14])*10+Integer.parseInt(time1[15]);

        int year2 = Integer.parseInt(time2[0])*1000+Integer.parseInt(time2[1])*100+Integer.parseInt(time2[2])*10+Integer.parseInt(time2[3]);
        int month2 = Integer.parseInt(time2[5])*10+Integer.parseInt(time2[6]);
        int day2 = Integer.parseInt(time2[8])*10+Integer.parseInt(time2[9]);
        int hour2 = Integer.parseInt(time2[11])*10+Integer.parseInt(time2[12]);
        int minute2 = Integer.parseInt(time2[14])*10+Integer.parseInt(time2[15]);

        if(year1<year2) return -1;
        else if(year1>year2) return 1;
        else{
            if(month1<month2) return -1;
            else if(month1>month2) return 1;
            else{
                if(day1<day2) return -1;
                else if(day1>day2) return 1;
                else{
                    if(hour1<hour2) return -1;
                    else if(hour1>hour2) return 1;
                    else{
                        if(minute1<minute2) return -1;
                        else if(minute1>minute2) return 1;
                        else return 0;
                    }
                }
            }
        }
    }



}

