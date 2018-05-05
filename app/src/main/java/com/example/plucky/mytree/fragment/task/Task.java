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

    public String getDeadline() {
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

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    @Override
    public int compareTo(Object task1) {
        Task task2=(Task) task1;
        String []time2=task2.getDeadline().split(" ");
        String []year2=time2[0].split("-");
        String []minute2=time2[1].split(":");
        String []time1= deadline.split(" ");
        String []year1= time1[0].split("-");
        String []minute1= time2[1].split(":");

        int year_1 = Integer.parseInt(year1[0]);
        int month_1 = Integer.parseInt(year1[1]);
        int day_1 = Integer.parseInt(year1[2]);

        int year_2 = Integer.parseInt(year2[0]);
        int month_2 = Integer.parseInt(year2[1]);
        int day_2 = Integer.parseInt(year2[2]);

        int hour_1 = Integer.parseInt(minute1[0]);
        int minute_1 = Integer.parseInt(minute1[1]);
        int second_1 = 0;
        if (minute1.length>2)
            second_1 = Integer.parseInt(minute1[2]);

        int hour_2 = Integer.parseInt(minute2[0]);
        int minute_2 = Integer.parseInt(minute2[1]);
        int second_2 = 0;
        if (minute2.length>2)
            second_2 = Integer.parseInt(minute2[2]);


        if(year_1 < year_2) return 1;
        else if(year_1 > year_2) return -1;
        else{
            if(month_1<month_2) return 1;
            else if(month_1>month_2) return -1;
            else{
                if(day_1<day_2) return 1;
                else if(day_1>day_2) return -1;
                else{
                    if(hour_1<hour_2) return 1;
                    else if(hour_1>hour_2) return -1;
                    else{
                        if(minute_1<minute_2) return 1;
                        else if(minute_1>minute_2) return -1;
                        else {
                            if (second_1<second_2)return 1;
                            else if (second_1>second_2)return -1;
                            else return 0;
                        }
                    }
                }
            }
        }
    }



}

