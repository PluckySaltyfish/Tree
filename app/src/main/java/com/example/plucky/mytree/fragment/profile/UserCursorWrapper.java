package com.example.plucky.mytree.fragment.profile;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.plucky.mytree.local.UserSchema.UserTable;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String username = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        String photo = getString(getColumnIndex(UserTable.Cols.PHOTO));
        int status = getInt(getColumnIndex(UserTable.Cols.STATUS));
        User user = new User(username,password);
        user.setPhoto(photo);
        user.setStatus(status);

        return user;
    }
}
