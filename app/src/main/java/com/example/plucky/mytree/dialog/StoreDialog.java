package com.example.plucky.mytree.dialog;

import android.app.Dialog;
import android.content.Context;
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
import com.example.plucky.mytree.store.StoreItem;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Store;

public class StoreDialog extends Dialog implements View.OnClickListener,ItemAdapter.MyItemClickListener{

    private ImageView title,back,confirm;
    private List<StoreItem> ItemList = new ArrayList<>();
    private Context mContext;
    private StoreItem item,tree1,tree2;
    private OnCloseListener listener;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private String username;
    private RemoteData mRemoteData;
    private ImageView tree1_box,tree2_box;
    private ImageView tree1_price,tree2_price;
    private TextView tree1_name,tree2_name;

    public StoreDialog(Context context, int themeResID, OnCloseListener listener,String username){
        super(context,themeResID);
        this.mContext = context;
        this.listener = listener;
        this.username = username;
    }

    protected StoreDialog(Context context, boolean cancelable, OnCancelListener cancelListener){
        super(context,cancelable,cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_dialog);
        setCanceledOnTouchOutside(false);
        initView();
        UpdateUI();

    }
    private void initView(){
        init();
        tree1_box = (ImageView)findViewById(R.id.tree1_box);
        tree2_box = (ImageView)findViewById(R.id.tree2_box);
        tree1_price = (ImageView)findViewById(R.id.tree1_price);
        tree2_price = (ImageView)findViewById(R.id.tree2_price);
        tree1_name = (TextView) findViewById(R.id.tree1_name);
        tree2_name = (TextView) findViewById(R.id.tree2_name);
        tree1_box.setImageResource(tree1.getRes());
        tree1_price.setImageResource(tree1.getPrice_res());
        tree1_name.setText(tree1.getName());
        tree2_box.setImageResource(tree2.getRes());
        tree2_price.setImageResource(tree2.getPrice_res());
        tree2_name.setText(tree2.getName());

        tree1_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = tree1;
                setSelected(-1);


            }
        });

        tree2_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = tree2;
                setSelected(-2);


            }
        });

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
        List<StoreItem> itemList = mRemoteData.getResource();
        for (int i = 0;i<itemList.size()-2;i++){
            itemList.get(i).setRes(mContext);
            ItemList.add(itemList.get(i));
        }

        tree1 = itemList.get(itemList.size()-2);
        tree1.setRes(mContext);

        tree2 = itemList.get(itemList.size()-1);
        tree2.setRes(mContext);


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
            adapter = new ItemAdapter(ItemList);
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
        if (position>=0) {
            item = ItemList.get(position);
            setTree(0);
        }
        else
            setTree(position);

        for (int i = 0;i<ItemList.size();i++){
            if (i==position)
                ItemList.get(i).setSelected(1);
            else
                ItemList.get(i).setSelected(0);

        }
        UpdateUI();
    }

    private void setTree(int i){
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        if (i==-1){
            int selected_res  = mContext.getResources().getIdentifier(tree1.getId()+"_selected", "drawable", appInfo.packageName);
            tree1_box.setImageResource(selected_res);
            tree2_box.setImageResource(tree2.getRes());
        }
        else if(i==-2){
            int selected_res  = mContext.getResources().getIdentifier(tree2.getId()+"_selected", "drawable", appInfo.packageName);
            tree2_box.setImageResource(selected_res);
            tree1_box.setImageResource(tree1.getRes());
        }
        else {
            tree1_box.setImageResource(tree1.getRes());
            tree2_box.setImageResource(tree2.getRes());

        }
    }


}
