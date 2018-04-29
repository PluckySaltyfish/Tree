package com.example.plucky.mytree.fragment.achievement;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;

import java.util.List;

public class Achievement {
    private int id[]=new int[30];
    private int image[]=new int[30];
    private String names[]={};
    //status用来存储用户是否获取此勋章
    private int status[]=new int[18];
    private RemoteData mRemoteData;
    private Context mContext;
    //0--未获得
    //1--已获得

    public Achievement(){
        for(int i=0;i<30;i++) id[i]=i+1;

        for(int i=0;i<30;i=i+5)
                image[i]= R.mipmap.tree_one;
        for(int i=1;i<30;i=i+5)
            image[i]=R.mipmap.tree_two;
        for(int i=2;i<30;i=i+5)
            image[i]=R.mipmap.tree_three;
        for(int i=3;i<30;i=i+5)
            image[i]=R.mipmap.tree_four;
        for(int i=4;i<30;i=i+5)
            image[i]=R.mipmap.tree_five;
    }

    private void loadImage(){
        ApplicationInfo appInfo = mContext.getApplicationInfo();

        for (int i = 0 ;i < 18 ;i++){
            int num = i+1;
            String name_prefix;
            if (status[i]==0){
                //加载灰色图片
                name_prefix="n_medal";
            }
            else{
                //加载彩色图片
                name_prefix="medal";
            }
            int res= mContext.getResources().getIdentifier(name_prefix+num, "drawable", appInfo.packageName);
            image[i]=res;
        }
    }

    public Achievement(Context mContext,String username){
        this.mContext = mContext;
        for (int i = 0;i<18;i++){
            status[i]=0;
        }
        mRemoteData = new RemoteData(mContext);
        List<Integer>list = mRemoteData.getAchievementID(username);
        for (int i = 0;i<list.size();i++){
            status[list.get(i)-1]=1;
        }
        loadImage();
    }

    public int getId(int i){
        return id[i];
    }
    public int getImage(int i){
        return image[i];
    }

}


