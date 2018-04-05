package com.example.plucky.mytree.fragment.achievement;

import android.content.Context;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;

import java.util.List;

public class Achievement {
    private int id[]=new int[30];
    private int image[]=new int[30];
    private String names[]={};
    private int status[]=new int[30];
    private RemoteData mRemoteData;
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
        for (int i = 0 ;i < 30 ;i++){
            if (status[i]==0){
                //加载灰色图片
            }
            else{
                //加载彩色图片
            }
        }
    }

    public Achievement(Context mContext,String username){
        mRemoteData = new RemoteData(mContext);
        List<Integer>list = mRemoteData.getAchievementID();
        for (int i = 0;i<list.size();i++){
            status[list.get(i)]=1;
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


