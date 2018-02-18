package com.example.plucky.mytree.fragment.achievement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.fragment.achievement.Achievement;

public class achievement_fragment extends Fragment {

    private RecyclerView mAchieveRecyclerView;
    private AchieveAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achieve_list, container, false);

        mAchieveRecyclerView = (RecyclerView) view.findViewById(R.id.achieve_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mAchieveRecyclerView.setLayoutManager(layoutManager);

        updateUI();
        return view;
    }
    public void updateUI(){
        //ImageLab imageLab=ImageLab.get(getActivity());
        //List<Image> images=imageLab.getImages();
        Achievement images =new Achievement();
        mAdapter=new AchieveAdapter(images);
        mAchieveRecyclerView.setAdapter(mAdapter);
    }

    private class AchieveHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;

        public AchieveHolder(View itemView){
            super(itemView);
            mImageView=(ImageView) itemView.findViewById(R.id.tree_image);
        }
    }

    private class AchieveAdapter extends RecyclerView.Adapter<AchieveHolder>{
        //private List<Image> mImages;
        private Achievement mImages=new Achievement();
        public AchieveAdapter(Achievement images){
            mImages=images;
        }

        @Override
        public AchieveHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.fragment_achieve,parent,false);
            return new AchieveHolder(view);
        }

        @Override
        public void onBindViewHolder(AchieveHolder holder,int position){
            holder.mImageView.setImageResource(mImages.getImage(position));
        }

        @Override
        public int getItemCount(){
            return 30;
        }
    }
}
