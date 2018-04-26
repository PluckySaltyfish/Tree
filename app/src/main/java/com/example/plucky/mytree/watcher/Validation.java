package com.example.plucky.mytree.watcher;


import android.content.Context;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.dialog.AlertDialog;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;

import java.util.Random;

public class Validation {
    private Context mContext;
    private AlertDialog mAlertDialog;
    private UsersManager mUserManager;
    public Validation(Context mContext){
        this.mContext = mContext;
    }

    public int isExist(String username){
        mUserManager=new UsersManager(mContext);
        User user = mUserManager.getUser(username);
        if(user==null) return 0;
        else return 1;
        //判断username是否存在于数据库中，存在返回1，不存在返回0
    }

    public int isEmpty(String text,String title){
        if (text.equals("")){
            mAlertDialog = new AlertDialog(mContext, R.style.dialog);
            mAlertDialog.idolize(title+"不能为空","确定",R.drawable.warning);
            mAlertDialog.show();
            return 1;
        }
        return 0;
    }

    public String PasswordGenerator(){
        int pass[]=new int[12];
        String password=new String();
        Random random=new Random();
        int length=random.nextInt(7)+6;
        for(int i=0;i<length;i++){
            pass[i]=random.nextInt(62)+1;
            if(pass[i]<27) pass[i]+=64;
            else if(pass[i]<53) pass[i]+=70;
            else pass[i]-=5;
            password=password+(char)pass[i];
        }
        //生成6-12位 由a-z，A-Z，0-9组成的字符串并返回
        return password;
    }

    public int isRightLength(String password){
        if(password.length()<6||password.length()>12){
            mAlertDialog = new AlertDialog(mContext, R.style.dialog);
            mAlertDialog.idolize("密码长度不符合","确定",R.drawable.warning);
            mAlertDialog.show();
            return 1;
        }
        else return 0;
        //判断password的长度是否在6-12位之间，如果小于6或大于12，显示AlertDialog,调用方法如isEmpty()中所示
    }

    public int isEqual(String s1,String s2,String title){
        if (s1.equals(s2))return 1;
        else{
            mAlertDialog = new AlertDialog(mContext, R.style.dialog);
            mAlertDialog.idolize(title,"确定",R.drawable.warning);
            mAlertDialog.show();
        }
        return 0;
    }
}
