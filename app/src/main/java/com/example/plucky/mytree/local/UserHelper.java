package com.example.plucky.mytree.local;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.plucky.mytree.local.UserSchema.UserTable;


public class UserHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "profile.db";
    private static final String CREAT_USER = "create table "+ UserSchema.UserTable.NAME+" ("
            + UserTable.Cols.USERNAME+","
            + UserTable.Cols.PASSWORD+","
            + UserTable.Cols.PHOTO+","
            + UserTable.Cols.STATUS+")";

    public UserHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
