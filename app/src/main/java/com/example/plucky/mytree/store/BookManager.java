package com.example.plucky.mytree.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.plucky.mytree.local.BookSchema.RootTable;
import com.example.plucky.mytree.local.LocalHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class BookManager {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public BookManager(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new LocalHelper(mContext).getWritableDatabase();

    }

    private static ContentValues getContentValues(RootDiagram rootDiagram){
        ContentValues values = new ContentValues();
        values.put(RootTable.Cols.pno, String.valueOf(rootDiagram.getPno()));
        values.put(RootTable.Cols.title,rootDiagram.getTitle());
        values.put(RootTable.Cols.content,rootDiagram.getContent());
        values.put(RootTable.Cols.meaning,rootDiagram.getMeaning());

        return values;
    }

    public void addPages(String table, RootDiagram rootDiagram){
        ContentValues values = getContentValues(rootDiagram);
        mDatabase.insert(table,null,values);
    }

    public void updateBook(String table, RootDiagram rootDiagram){

    }

    public List<RootDiagram> getPages(String table){
        List<RootDiagram> pages = new ArrayList<>();

        try (BookCursorWrapper cursor = queryBook(table, null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pages.add(cursor.getPage(table));
                cursor.moveToNext();
            }
        }

        Collections.sort(pages);
        return pages;
    }

    private BookCursorWrapper queryBook(String table, String whereClause, String[]whereArgs){
        Cursor cursor = mDatabase.query(
                table,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new BookCursorWrapper(cursor);
    }
}
