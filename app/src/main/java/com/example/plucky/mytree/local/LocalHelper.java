package com.example.plucky.mytree.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.plucky.mytree.local.UserSchema.UserTable;
import com.example.plucky.mytree.local.BookSchema.RootTable;
import com.example.plucky.mytree.store.RootDiagram;


public class LocalHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tree.db";
    private static final String CREAT_USER = "create table "+ UserTable.NAME+" ("
            + UserTable.Cols.USERNAME+","
            + UserTable.Cols.PASSWORD+","
            + UserTable.Cols.PHOTO+","
            + UserTable.Cols.STATUS+")";

    private static final String CREAT_WD = "create table "+ RootTable.NAME+" ("
            + RootTable.Cols.pno+","
            + RootTable.Cols.content+","
            + RootTable.Cols.meaning+","
            + RootTable.Cols.title+")";

    public LocalHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_USER);
        db.execSQL(CREAT_WD);
        RootDiagram page1 = new RootDiagram(1,"Anthrop",
                "anthropology:study of human beings%misanthrope:person who dislikes humans",
                "human");
        RootDiagram page2 = new RootDiagram(2,"Chron",
                "chronic:persisting for a long time%Chronological:arranged in the order of time",
                "time");
        ContentValues contentValues = getContentValues(page1);
        db.insert(RootTable.NAME,null,contentValues);
        ContentValues contentValues2 = getContentValues(page2);
        db.insert(RootTable.NAME,null,contentValues2);
        RootDiagram page3 = new RootDiagram(3,"Civ",
                "civilization:advanced state of human society%incivility:state of being rude or unsociable",
                "citizen");
        contentValues = getContentValues(page3);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page4 = new RootDiagram(4,"Dur",
                "endure:to suffer patiently%durable:able to withstand wear and damage","hard");
        contentValues = getContentValues(page4);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page5 = new RootDiagram(5,"Loc",
                "dislocate:to put out of place%locale:place where something happens","place");
        contentValues = getContentValues(page5);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page6 = new RootDiagram(6,"Mal",
                "dismal:gloomy or cheerless%malefactor:person who commits a crime","bad");
        contentValues = getContentValues(page6);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page7 = new RootDiagram(7,"Phil",
                "bibliophile:person who loves books%philosophy:study of knowledge and truth",
                "love");
        contentValues = getContentValues(page7);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page8 = new RootDiagram(8,
                "Phon",
                "cacophonous:having a harsh or loud sound%telephone:instrument for transmitting sound",
                "sound");
        contentValues = getContentValues(page8);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page9 = new RootDiagram(9,
                "Port",
                "transport:to move from one place to another%porter:person hired to carry baggage",
                "carry");
        contentValues = getContentValues(page9);
        db.insert(RootTable.NAME,null,contentValues);
        RootDiagram page10 = new RootDiagram(10,
                "Scrib",
                "scribble:to write or draw carelessly%transcribe:to make a written copy",
                "write");
        contentValues = getContentValues(page10);
        db.insert(RootTable.NAME,null,contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static ContentValues getContentValues(RootDiagram rootDiagram){
        ContentValues values = new ContentValues();
        values.put(RootTable.Cols.pno,String.valueOf(rootDiagram.getPno()));
        values.put(RootTable.Cols.title,rootDiagram.getTitle());
        values.put(RootTable.Cols.content,rootDiagram.getContent());
        values.put(RootTable.Cols.meaning,rootDiagram.getMeaning());

        return values;
    }
}

