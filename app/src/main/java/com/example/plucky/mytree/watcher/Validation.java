package com.example.plucky.mytree.watcher;


import android.content.Context;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.dialog.AlertDialog;
import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.fragment.profile.UsersManager;
import com.example.plucky.mytree.store.StoreItem;
import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
        String password = "";
        Random random=new Random();
        int length = 6;
        for(int i=0;i<length;i++){
            password += random.nextInt(10);
        }
        return password;
    }

    public int isRightLength(String password){
        if(password.length()<6||password.length()>12){
            mAlertDialog = new AlertDialog(mContext, R.style.dialog);
            mAlertDialog.idolize("密码长度不符合","确定",R.drawable.warning);
            mAlertDialog.show();
            return 0;
        }
        else return 1;
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

    public int isEnough(int need,int have){
        if (need < have) return have-need;
        else{
            mAlertDialog = new AlertDialog(mContext, R.style.dialog);
            mAlertDialog.idolize("金币不够哦", "返回", R.drawable.warning);
            mAlertDialog.show();
        }
        return -1;
    }

    public int isPurchased(String id, List<StoreItem>ItemList){
        for (int i = 0;i<ItemList.size();i++){
            if (ItemList.get(i).getId().equals(id))
                return 1;
        }
        return 0;
    }


}
