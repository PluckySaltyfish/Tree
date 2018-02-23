package com.example.plucky.mytree.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalData extends SQLiteOpenHelper {


    private Context mContext;
    private static final String CREAT_USER = "create table user("
            +"username text primary key,"
            +"status integer,"
            +"photo text)";

    public LocalData(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getUsername(){
        //get username
        return "cxy";
    }


}
