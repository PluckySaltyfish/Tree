package com.example.plucky.mytree.watcher;


import android.content.Context;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.dialog.AlertDialog;

public class Validation {
    private Context mContext;
    private AlertDialog mAlertDialog;
    public Validation(Context mContext){
        this.mContext = mContext;
    }

    public int isExist(String username){
        //判断username是否存在于数据库中，存在返回1，不存在返回0
        return 0;
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
        //生成6-12位 由a-z，A-Z，0-9组成的字符串并返回
        return "";
    }

    public int isRightLength(String password){
        return 1;
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
