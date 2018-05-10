package com.example.plucky.mytree.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.plucky.mytree.fragment.profile.User;
import com.example.plucky.mytree.local.UserSchema.UserTable;
import com.example.plucky.mytree.local.BookSchema.RootTable;
import com.example.plucky.mytree.store.RootDiagram;


public class LocalHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tree.db";
    private static final String book2 = "book2";
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

    private static final String CREAT_PB = "create table "+ book2+" ("
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
        db.execSQL(CREAT_PB);
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

//        --prefix--
        RootDiagram page1_2 = new RootDiagram(1,"Bio",
                "bicycle:vehicle with two wheels%binocular:involving or built for two eyes",
                "two");
        RootDiagram page2_2 = new RootDiagram(2,"Bio",
                "biography:story of someone's life%biology:study of life or living matter",
                "life");
        contentValues = getContentValues(page1_2);
        db.insert(book2,null,contentValues);
        contentValues2 = getContentValues(page2_2);
        db.insert(book2,null,contentValues2);
        RootDiagram page3_2 = new RootDiagram(3,"Co",
                "coexist:to live together in peace%cohabitant:person living with another",
                "together");
        contentValues = getContentValues(page3_2);
        db.insert(book2,null,contentValues);
        RootDiagram page4_2 = new RootDiagram(4,"Dis",
                "disappear:to vanish from sight%dislike:feeling of aversion or distate","apart");
        contentValues = getContentValues(page4_2);
        db.insert(book2,null,contentValues);
        RootDiagram page5_2 = new RootDiagram(5,"Ex",
                "exclude:to deny someone access%exit:way out of somewhere","out of");
        contentValues = getContentValues(page5_2);
        db.insert(book2,null,contentValues);
        RootDiagram page6_2 = new RootDiagram(6,"Fore",
                "forecast:to predict a future event%foreshadow:to indicate a future event","before");
        contentValues = getContentValues(page6_2);
        db.insert(book2,null,contentValues);
        RootDiagram page7_2 = new RootDiagram(7,"In",
                "not of lacking:being unable to do something%inordinate:unusually very large or excessive",
                "not or lacking");
        contentValues = getContentValues(page7_2);
        db.insert(book2,null,contentValues);
        RootDiagram page8_2 = new RootDiagram(8,
                "Inter",
                "interactive:involving the actions of another%international:existing between two or more countries",
                "between or among");
        contentValues = getContentValues(page8_2);
        db.insert(book2,null,contentValues);
        RootDiagram page9_2 = new RootDiagram(9,
                "Non",
                "nonsense:words or language having no meaning%nonstop:continuing without pause",
                "not");
        contentValues = getContentValues(page9_2);
        db.insert(book2,null,contentValues);
        RootDiagram page10_2 = new RootDiagram(10,
                "Semi",
                "semiannual:occurring twice a year%semiserious:partly serious",
                "half or partly");
        contentValues = getContentValues(page10_2);
        db.insert(book2,null,contentValues);
        User user = new User("CXY","123456");
        contentValues = getUserContentValues(user);
        db.insert(book2,null,contentValues);


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

    private static ContentValues getUserContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, user.getUsername());
        values.put(UserTable.Cols.PASSWORD, user.getPassword());
        values.put(UserTable.Cols.PHOTO, user.getPhoto());
        values.put(UserTable.Cols.STATUS, user.getStatus());
        return values;
    }
}

