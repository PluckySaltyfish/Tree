package com.example.plucky.mytree.fragment.achievement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.fragment.profile.UsersManager;

import java.util.ArrayList;

public class achievement_fragment extends Fragment {
    private UsersManager mUserManager;
    private String username;
    private RecyclerView mAchieveRecyclerView;
    private AchieveAdapter mAdapter;
    private ArrayList<String> mdetailsArrayList=new ArrayList();
    private ArrayList<String> mnamesArrayList=new ArrayList();
    private ImageView mImageView,mcloseView;
    private TextView mnameTextView,mdetailTextView,mstatusTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achieve_list, container, false);

        mAchieveRecyclerView = (RecyclerView) view.findViewById(R.id.achieve_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mAchieveRecyclerView.setLayoutManager(layoutManager);

        mUserManager = new UsersManager(getActivity());
        username = mUserManager.getUsername();
        achievedetails(mdetailsArrayList);
        achievednames(mnamesArrayList);
        updateUI();
        return view;
    }

    public void updateUI() {
        //ImageLab imageLab=ImageLab.get(getActivity());
        //List<Image> images=imageLab.getImages();

        Achievement images = new Achievement(getActivity(), username);

        mAdapter = new AchieveAdapter(images);
        mAchieveRecyclerView.setAdapter(mAdapter);
    }

    private class AchieveHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;

        public AchieveHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.tree_image);
        }
    }

    private class AchieveAdapter extends RecyclerView.Adapter<AchieveHolder> {
        //private List<Image> mImages;
        private Achievement mImages;

        public AchieveAdapter(Achievement images) {
            mImages = images;
        }

        @Override
        public AchieveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_achieve, parent, false);
            return new AchieveHolder(view);
        }

        @Override
        public void onBindViewHolder(AchieveHolder holder, final int position) {
            final int imagenow = mImages.getImage(position);


            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            Bitmap imageBitmap2 = BitmapFactory.decodeResource(getResources(), imagenow);
            Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(imageBitmap2, width/3, width/3);
            holder.mImageView.setImageBitmap(resizeBmp);
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(), "点击了图片" + position, Toast.LENGTH_SHORT).show();

                    final View contentView =LayoutInflater.from(getActivity()).inflate(R.layout.achieve_details, null, false);
                    final PopupWindow popupWindow = new PopupWindow(contentView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    popupWindow.setOutsideTouchable(false);
                    popupWindow.setFocusable(true);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                    popupWindow.setClippingEnabled(false);
                    popupWindow.setAnimationStyle(R.style.take_photo_anim);
                    popupWindow.showAtLocation(contentView, Gravity.CENTER_HORIZONTAL, 0, 0);

                    mImageView=(ImageView)contentView.findViewById(R.id.achieveimage);
                    mImageView.setImageResource(imagenow);
                    mnameTextView=(TextView)contentView.findViewById(R.id.nametextView);
                    mnameTextView.setText(mnamesArrayList.get(position));
                    mdetailTextView=(TextView)contentView.findViewById(R.id.detailtextView);
                    mdetailTextView.setText(mdetailsArrayList.get(position));
                    mstatusTextView=(TextView)contentView.findViewById(R.id.statustextView);
                    if(mImages.getstatus(position)==1) mstatusTextView.setText("已完成");
                    else mstatusTextView.setText("未完成");

                    mcloseView=(ImageView)contentView.findViewById(R.id.closeView);
                    mcloseView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                }
            });
        }

        @Override
        public int getItemCount() {
            return mImages.getimagesize();
        }
    }

    public void achievedetails(ArrayList<String> mlist) {
        mlist.add("新建一个任务");
        mlist.add("第一次完成一个任务");
        mlist.add("种下第二棵树");

        mlist.add("一天新建5个任务");
        mlist.add("一天新建10个任务");
        mlist.add("连续7天任务全部完成");

        mlist.add("第一次阅读内置资源");
        mlist.add("阅读完成1本内置资源");
        mlist.add("阅读完成3本内置资源");

        mlist.add("第一次购买物品");
        mlist.add("购买物品达到10件");
        mlist.add("金币数量达到200");

        mlist.add("树等级达到3");
        mlist.add("树等级达到5");
        mlist.add("萤火树满级");

        mlist.add("等级达到5");
        mlist.add("等级达到10");
        mlist.add("等级达到15");

    }
    public void achievednames(ArrayList<String> mlist) {
        mlist.add("初出茅庐");
        mlist.add("初露头角");
        mlist.add("绿色大使");

        mlist.add("任务达人");
        mlist.add("任务狂人");
        mlist.add("行动达人");

        mlist.add("略有所闻");
        mlist.add("学有所成");
        mlist.add("满腹经纶");

        mlist.add("初探商店");
        mlist.add("购物达人");
        mlist.add("堆金积玉");

        mlist.add("枝繁叶茂");
        mlist.add("硕果累累");
        mlist.add("萤火树");

        mlist.add("小有成就");
        mlist.add("出类拔萃");
        mlist.add("一代宗师");


    }
}