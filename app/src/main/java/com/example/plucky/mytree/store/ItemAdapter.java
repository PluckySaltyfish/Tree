package com.example.plucky.mytree.store;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.plucky.mytree.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<StoreItem> mItemList;
    MyItemClickListener mItemClickListener;
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ItemPic;
        TextView ItemText;
        FrameLayout Layout;
        MyItemClickListener mItemClickListener;
        public ViewHolder(View view, MyItemClickListener myItemClickListener) {
            super(view);
            ItemPic = (ImageView)view.findViewById(R.id.item_pic);
            ItemText = (TextView)view.findViewById(R.id.item_text);
            Layout = (FrameLayout)view.findViewById(R.id.item_layout);
            this.mItemClickListener = myItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

    public ItemAdapter(List<StoreItem> ItemList) {
        mItemList = ItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.select_item, parent, false);
        ViewHolder holder = new ViewHolder(view,mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StoreItem item = mItemList.get(position);
        holder.ItemPic.setImageResource(item.getRes());
        holder.ItemText.setText(item.getName());
        if (item.getSelected()==1)
            holder.ItemPic.setBackgroundResource(R.color.bkg_u);
        else
            holder.Layout.setBackgroundResource(R.color.white);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener =listener;
    }
}
