package com.example.plucky.mytree.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plucky.mytree.R;
import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.store.ItemAdapter;
import com.example.plucky.mytree.store.ResourceAdapter;
import com.example.plucky.mytree.store.StoreItem;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Store;

public class ResourceDialog extends Dialog implements View.OnClickListener,ResourceAdapter.MyItemClickListener{

    private ImageView title,back,confirm;
    private List<StoreItem> ItemList = new ArrayList<>();
    private Context mContext;
    private StoreItem item;
    private int mode = 0;
    private OnCloseListener listener;
    private ResourceAdapter adapter;
    private RecyclerView recyclerView;
    private String username;
    private RemoteData mRemoteData;


    public ResourceDialog(Context context, int themeResID, OnCloseListener listener,String username,int mode){
        super(context,themeResID);
        this.mContext = context;
        this.listener = listener;
        this.username = username;
        this.mode = mode;
    }

    protected ResourceDialog(Context context, boolean cancelable, OnCancelListener cancelListener,int mode){
        super(context,cancelable,cancelListener);
        this.mContext = context;
        this.mode = mode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bag_dialog);
        setCanceledOnTouchOutside(false);
        initView();
        UpdateUI();

    }
    private void initView(){
        init();
        title = (ImageView) findViewById(R.id.store_title);
        back = (ImageView)findViewById(R.id.back_button);
        confirm = (ImageView)findViewById(R.id.right_button);
        back.setOnClickListener(this);
        confirm.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void init(){
        mRemoteData = new RemoteData(mContext);
        if (mode == 0)
            ItemList = mRemoteData.getPurchasedBook(username);
        else
            ItemList = mRemoteData.getPurchasedItem(username);
        for (int i = 0;i<ItemList.size();i++){
            ItemList.get(i).setRes(mContext);
        }
    }

    public StoreItem getItem(){
        return item;
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                this.dismiss();
                break;
            case R.id.right_button:
                if (listener!=null){
                    listener.onClick(this,true);
                }
        }
    }

    private void UpdateUI(){


        if (adapter == null) {
            adapter = new ResourceAdapter(ItemList);
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        setSelected(position);
    }

    private void setSelected(int position){

        item = ItemList.get(position);

        for (int i = 0;i<ItemList.size();i++){
            if (i==position)
                ItemList.get(i).setSelected(1);
            else
                ItemList.get(i).setSelected(0);

        }
        UpdateUI();
    }


}
