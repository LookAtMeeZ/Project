package com.example.user.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;


    int size=0;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ListView lv =  (ListView)findViewById(R.id.listview);



       try {
            mDatabaseHelper = new DatabaseHelper(this);
            mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
            Cursor cursor = mSqLiteDatabase.query("prods", new String[] {DatabaseHelper.PROD_NAME_COLUMN},null, null,null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,new String[]{"NAME"}, new int[]{android.R.id.text1},0);
            lv.setAdapter(listAdapter);
            mSqLiteDatabase.close();
            mDatabaseHelper.close();
        } catch(SQLiteException e){
            Toast t = Toast.makeText(this, "БД не доступна", Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public void onTrash(View v) {
        mSqLiteDatabase.delete(DatabaseHelper.PROD_NAME_COLUMN, null, null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
