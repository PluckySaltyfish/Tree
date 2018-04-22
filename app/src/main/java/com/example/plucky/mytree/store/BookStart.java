package com.example.plucky.mytree.store;


import android.content.Context;
import android.util.Log;

import com.example.plucky.mytree.connection.RemoteData;


import java.util.List;


public class BookStart {
    private Context mContext;
    private RemoteData mRemoteData;
    private BookManager mBookManager;
    private String TAG = "BookStart";
    private List<RootDiagram>list;
    public BookStart(Context context){
        this.mContext = context;
    }

    public void startReading(String bookId,int taskId){
        mRemoteData = new RemoteData(mContext);
        mBookManager = new BookManager(mContext);
        list = mBookManager.getPages(bookId);

        //开启新的Activity
        //用cardView展示list中的每一项内容
        //最后一页添加一张，显示End


        if (taskId!=0){
//            if (读到了最后一页){
//                mRemoteData.setStatus(taskId,2);
//            }
        }

    }
}
