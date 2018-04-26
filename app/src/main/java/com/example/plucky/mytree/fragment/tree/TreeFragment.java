package com.example.plucky.mytree.fragment.tree;

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
import com.example.plucky.mytree.fragment.profile.UsersManager;

public class TreeFragment extends Fragment {
    private VideoView mVideoView;
    private FrameLayout placehodler;
    private RemoteData mRemoteData;
    private String username;
    private ImageView tree_level,user_level;
    private TextView tree_exp,exp_text;
    private UsersManager mUsersManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tree_fragment, container, false);

        //get data from remote server
        mRemoteData = new RemoteData(getActivity());
        mUsersManager = new UsersManager(getActivity());
        username = mUsersManager.getUsername();

        int maxExp;
        int exp = mRemoteData.getExp(username);
        int level;

        tree_exp = (TextView)v.findViewById(R.id.tree_exp);
        exp_text = (TextView)v.findViewById(R.id.exp);

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
        String user_mark = exp+"/"+maxExp;
        String tree_exp_mark = treeExp+"/"+treeMaxExp;

        tree_exp.setText(tree_exp_mark);
        exp_text.setText(user_mark);



        ApplicationInfo appInfo = getActivity().getApplicationInfo();
        int tree_res  = getActivity().getResources().getIdentifier(tree_level_mark, "drawable", appInfo.packageName);

        int res = getActivity().getResources().getIdentifier(level_mark,"drawable",appInfo.packageName);

        mVideoView = (VideoView)v.findViewById(R.id.tree_view);
        placehodler = (FrameLayout)v.findViewById(R.id.placeholder);
        final String videoPath = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + video_name).toString();
        mVideoView.setVideoPath(videoPath);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
                placehodler.setVisibility(View.GONE);
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
        return   v;



    }

}
