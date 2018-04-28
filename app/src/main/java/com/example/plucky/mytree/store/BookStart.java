package com.example.plucky.mytree.store;


import android.content.Context;
import android.content.Intent;

import com.example.plucky.mytree.connection.RemoteData;
import com.example.plucky.mytree.store.ViewPage.cardActivity;

import java.io.Serializable;
import java.util.List;


public class BookStart{
    private Context mContext;
    private RemoteData mRemoteData;
    private BookManager mBookManager;
    private String TAG = "BookStart";
    private List<RootDiagram> list;
    public BookStart(Context context){
        this.mContext = context;
    }

    public void startReading(String bookId,int taskId){
        mRemoteData = new RemoteData(mContext);
        mBookManager = new BookManager(mContext);
        list = mBookManager.getPages(bookId);
        Intent intent=new Intent();
        intent.setClass(mContext,cardActivity.class);
        intent.putExtra("list",(Serializable)list);
        intent.putExtra("taskId",taskId);
        mContext.startActivity(intent);


    }
}
