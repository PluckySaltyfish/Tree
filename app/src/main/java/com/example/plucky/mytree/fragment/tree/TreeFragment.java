package com.example.plucky.mytree.fragment.tree;

import android.app.Dialog;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.dialog.AlertDialog;
import com.example.plucky.mytree.dialog.ConfirmDialog;
import com.example.plucky.mytree.dialog.ResourceDialog;
import com.example.plucky.mytree.dialog.StoreDialog;
import com.example.plucky.mytree.fragment.profile.UsersManager;

import com.example.plucky.mytree.store.StoreItem;
import com.example.plucky.mytree.watcher.Validation;

import java.util.ArrayList;
import java.util.List;

public class TreeFragment extends Fragment {
    private VideoView mVideoView;
    private FrameLayout placeholder;
    private RemoteData mRemoteData;
    private String username;
    private ImageView tree_level,user_level;
    private TextView tree_exp,exp_text,coin_text;
    private UsersManager mUsersManager;
    private ImageView store_icon,bag_icon;
    private StoreDialog mStoreDialog;
    private ResourceDialog mResourceDialog;
    private ConfirmDialog mConfirmDialog;
    private AlertDialog mAlertDialog;
    private StoreItem item;
    private Validation mValidation;
    private List<StoreItem> ItemList = new ArrayList<>();
    private int coin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tree_fragment, container, false);

        //get data from remote server
        mRemoteData = new RemoteData(getActivity());
        mUsersManager = new UsersManager(getActivity());
        mValidation = new Validation(getActivity());
        username = mUsersManager.getUsername();

        int maxExp;
        int exp = mRemoteData.getExp(username);
        int level;
        coin = mRemoteData.getCoin(username);

        tree_exp = (TextView)v.findViewById(R.id.tree_exp);
        exp_text = (TextView)v.findViewById(R.id.exp);
        coin_text = (TextView)v.findViewById(R.id.coin);

        ItemList = mRemoteData.getPurchasedItem(username);

        if (exp < 200){
            level = exp / 50 + 1;
            maxExp = 200;
        }
        else if(exp < 700){
            level = (exp-200) / 100 + 5;
            maxExp =700;
        }
        else if (exp < 940){
            level = (exp-700) / 120 + 10;
            maxExp =940;
        }
        else if (exp < 1240){
            level = (exp-940) / 150 + 12;
            maxExp = 1240;
        }
        else if (exp < 1640){
            level = (exp-1240) / 200 + 14;
            maxExp = 1640;
        }
        else if (exp < 2140){
            level = (exp-1640) /250 + 16;
            maxExp = 2140;
        }
        else if (exp < 2740){
            level = (exp-2140) /300 + 18;
            maxExp = 2740;
        }
        else {
            level = 20;
            maxExp = 2740;
        }




        int video_name;
        int treeExp = mRemoteData.getTreeExp(username);
        int treeLevel,treeMaxExp;
        if (treeExp==200){
            treeLevel = 5;
            video_name = R.raw.tree103;
            treeMaxExp = 200;
        }
        else if(treeExp>150){
            treeLevel = 4;
            video_name = R.raw.tree102;
            treeMaxExp = 200;
        }
        else if(treeExp>100){
            treeLevel = 3;
            video_name = R.raw.tree102;
            treeMaxExp = 150;
        }
        else if (treeExp>50){
            treeLevel = 2;
            video_name = R.raw.tree101;
            treeMaxExp = 100;
        }
        else{
            treeLevel = 1;
            treeMaxExp = 50;
            video_name = R.raw.tree101;
        }



        String tree_level_mark = "tree_v"+treeLevel;
        String level_mark = "v"+level;
        final String user_mark = exp+"/"+maxExp;
        String tree_exp_mark = treeExp+"/"+treeMaxExp;

        tree_exp.setText(tree_exp_mark);
        exp_text.setText(user_mark);
        coin_text.setText(String.valueOf(coin));



        ApplicationInfo appInfo = getActivity().getApplicationInfo();
        int tree_res  = getActivity().getResources().getIdentifier(tree_level_mark, "drawable", appInfo.packageName);

        int res = getActivity().getResources().getIdentifier(level_mark,"drawable",appInfo.packageName);

        mVideoView = (VideoView)v.findViewById(R.id.tree_view);
        placeholder = (FrameLayout)v.findViewById(R.id.placeholder);
        final String videoPath = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + video_name).toString();
        mVideoView.setVideoPath(videoPath);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
                placeholder.setVisibility(View.GONE);
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {

                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                            mVideoView.setBackgroundColor(Color.TRANSPARENT);
                        return true;
                    }
                });

            }});
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.setVideoPath(videoPath);
                mVideoView.start();
            }
        });

        tree_level = (ImageView)v.findViewById(R.id.tree_level);
        user_level = (ImageView)v.findViewById(R.id.level);
        tree_level.setImageResource(tree_res);
        user_level.setImageResource(res);

        store_icon =(ImageView)v.findViewById(R.id.store_icon);
        bag_icon =(ImageView)v.findViewById(R.id.bag_icon);
        store_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStoreDialog = new StoreDialog(getActivity(), R.style.dialog, new StoreDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm){
                            item = mStoreDialog.getItem();
                            if (mValidation.isPurchased(item.getId(),ItemList)!=1) {
                                mConfirmDialog = new ConfirmDialog(getActivity(), R.style.dialog, new ConfirmDialog.OnCloseListener() {
                                    @Override
                                    public void onClick(Dialog dialog, boolean confirm) {
                                        if (confirm) {
                                            mConfirmDialog.dismiss();
                                            int coin_new = mValidation.isEnough(item.getPrice(), coin);
                                            if (coin_new != -1) {
                                                mAlertDialog = new AlertDialog(getActivity(), R.style.dialog);
                                                mAlertDialog.idolize("购买成功", "确定", R.drawable.smile);
                                                mAlertDialog.show();
                                                mRemoteData.setCoin(username, coin_new);
                                                coin_text.setText(String.valueOf(coin_new));
                                                mStoreDialog.dismiss();
                                            }
                                        }
                                    }
                                });
                                mConfirmDialog.idolize("购买确认", "确认要购买" + item.getName() + "吗？",
                                        "取消", "确认", R.drawable.frog);
                                mConfirmDialog.show();
                            }else {
                                mAlertDialog = new AlertDialog(getActivity(), R.style.dialog);
                                mAlertDialog.idolize("请勿重复购买", "确定", R.drawable.warning);
                                mAlertDialog.show();

                            }
                        }
                    }
                },username);
                mStoreDialog.show();

            }
        });

        bag_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResourceDialog = new ResourceDialog(getActivity(), R.style.dialog, new ResourceDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm){
                            item = mResourceDialog.getItem();
                            if (item.getId().substring(0,4).equals("tree")) {
                                mAlertDialog = new AlertDialog(getActivity(), R.style.dialog);
                                mAlertDialog.idolize("已经种了一棵树了哦", "返回", R.drawable.warning);
                                mAlertDialog.show();
                            }

                        }
                    }
                },username,1);
                mResourceDialog.show();
            }
        });


        return v;



    }



}
