package com.example.plucky.mytree.store;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.plucky.mytree.local.BookSchema.RootTable;


public class BookCursorWrapper extends CursorWrapper {
    public BookCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public RootDiagram getPage(String table){
        int pno = getInt(getColumnIndex(RootTable.Cols.pno));
        String title = getString(getColumnIndex(RootTable.Cols.title));
        String content = getString(getColumnIndex(RootTable.Cols.content));
        String meaning = getString(getColumnIndex(RootTable.Cols.meaning));
        RootDiagram rootDiagram = new RootDiagram(pno);
        rootDiagram.setContent(content);
        rootDiagram.setTitle(title);
        rootDiagram.setMeaning(meaning);
        return rootDiagram;
    }
}
