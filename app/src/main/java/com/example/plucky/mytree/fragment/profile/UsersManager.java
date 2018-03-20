package com.example.plucky.mytree.fragment.profile;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.plucky.mytree.local.UserHelper;
import com.example.plucky.mytree.local.UserSchema.UserTable;

import java.util.ArrayList;
import java.util.List;

public class UsersManager {

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public UsersManager(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UserHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, user.getUsername());
        values.put(UserTable.Cols.PASSWORD, user.getPassword());
        values.put(UserTable.Cols.PHOTO, user.getPhoto());
        values.put(UserTable.Cols.STATUS, user.getStatus());
        return values;
    }

    public void addUser(User user){
        ContentValues values = getContentValues(user);
        mDatabase.insert(UserTable.NAME, null, values);

    }

    public void updateUser(User user){
        String username =user.getUsername();
        ContentValues values = getContentValues(user);
        mDatabase.update(UserTable.NAME,values,UserTable.Cols.USERNAME +
                "= ?",new String[]{username});
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return  new UserCursorWrapper(cursor);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        UserCursorWrapper cursor = queryUsers(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return users;
    }

    public User getUser(String username){
        UserCursorWrapper cursor = queryUsers(
                UserTable.Cols.USERNAME + "= ?",
                new String[]{username}
        );
        try{
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        }finally {
            cursor.close();
        }
    }
}
