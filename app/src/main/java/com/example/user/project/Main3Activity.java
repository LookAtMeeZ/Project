package com.example.user.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    ListView lv;
    int size=0;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        mDatabaseHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);
        SQLiteDatabase sdb;
        sdb = mDatabaseHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDatabaseHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        mSqLiteDatabase.insert("prods", null, values);
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1);

        lv.setAdapter(adapter);

    }
    public void onTrash(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
