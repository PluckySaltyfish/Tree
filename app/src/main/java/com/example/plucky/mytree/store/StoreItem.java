package com.example.plucky.mytree.store;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import javax.mail.Store;

public class StoreItem {
    private int res;
    private String name;
    private int selected=0;
    private String id;
    private int price;

    public StoreItem(int res, String name) {
        this.res = res;
        this.name = name;
    }

    public StoreItem(String id,String name){
        this.id = id;
        this.name = name;
    }

    public StoreItem(String id, String name,Context mContext) {
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        this.res  = mContext.getResources().getIdentifier(id, "drawable", appInfo.packageName);
        this.name = name;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getRes() {
        return res;
    }

    public void setRes(Context mContext) {
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        this.res  = mContext.getResources().getIdentifier(id, "drawable", appInfo.packageName);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
