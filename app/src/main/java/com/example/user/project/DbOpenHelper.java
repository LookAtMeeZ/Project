package com.example.user.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 6/8/2016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final ArrayList<prod> list= new ArrayList<prod>();
    public DbOpenHelper(Context context) {
        super(context, "test", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("List of products");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
