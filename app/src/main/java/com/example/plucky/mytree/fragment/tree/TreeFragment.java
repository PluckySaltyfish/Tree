package com.example.plucky.mytree.fragment.tree;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.fragment.profile.UsersManager;

public class TreeFragment extends Fragment {
    private VideoView mVideoView;
    private FrameLayout placehodler;
    private RemoteData mRemoteData;
    private String username;
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
        int video_name;
        int treeExp = mRemoteData.getTreeExp(username);
        int treeLevel,maxTreeExp;
        if (treeExp==200){
            treeLevel = 5;
            video_name = R.raw.tree103;
        }
        else if(treeExp>150){
            treeLevel = 4;
            video_name = R.raw.tree102;
        }
        else if(treeExp>100){
            treeLevel = 3;
            video_name = R.raw.tree102;
        }
        else if (treeExp>50){
            treeLevel = 2;
            video_name = R.raw.tree101;
        }
        else{
            treeLevel = 1;
            video_name = R.raw.tree101;
        }

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



        return   v;



    }

}
