

package com.example.plucky.mytree.fragment.task;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plucky.mytree.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTasksList;
    MyItemLongClickListener mLongClickListener;
    MyItemClickListener mItemClickListener;
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener {
        TextView status;
        TextView task_content;
        ImageView status_circle;

        MyItemLongClickListener mLongClickListener;
        MyItemClickListener mItemClickListener;

        ViewHolder(View view,MyItemLongClickListener mLongClickListener,MyItemClickListener myItemClickListener) {
            super(view);
            status = (TextView) view.findViewById(R.id.status_text);
            task_content = (TextView) view.findViewById(R.id.textView3);
            status_circle = (ImageView)view.findViewById(R.id.status_circle);

            this.mLongClickListener = mLongClickListener;
            this.mItemClickListener = myItemClickListener;
            view.setOnLongClickListener(this);
            view.setOnClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(view,getAdapterPosition());
            }
            return true;
        }

        @Override
        public void onClick(View view) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(view,getAdapterPosition());
            }
        }


    }


    TaskAdapter(List<Task> tasksList) {
        mTasksList=tasksList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        ViewHolder holder = new ViewHolder(view,mLongClickListener, mItemClickListener);


        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mTasksList.get(position);
        holder.status.setText(task.getDeadline());
        int status = task.getStatus();
        if (status==0){
            holder.status_circle.setImageResource(R.drawable.yellow_circle);
        }else if(status==1){
            if (task.getTimeLimit()==0&&task.getTimes()==1)
                holder.status_circle.setImageResource(R.drawable.green_circle);
            holder.status_circle.setImageResource(R.drawable.blue_circle);
        }else if (status==2){
            holder.status_circle.setImageResource(R.drawable.green_circle);
        }else{
            holder.status_circle.setImageResource(R.drawable.red_circle);
        }
        holder.task_content.setText(task.getContent());
    }
    @Override
    public int getItemCount() {
        return mTasksList.size();
    }

    public interface MyItemLongClickListener {
        public void onItemLongClick(View view,int position);
    }

    void setOnItemLongClickListener(MyItemLongClickListener listener){
        this.mLongClickListener = listener;
    }

    public interface MyItemClickListener {
        public void onItemClick(View view,int position);
    }

    void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener =listener;
    }






}

