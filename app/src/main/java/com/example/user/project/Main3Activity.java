package com.example.user.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private DatabaseHelper mDatabaseHelper1;
    private SQLiteDatabase mSqLiteDatabase1;
    ListView lv;


    int size=0;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        lv =  (ListView)findViewById(R.id.listview);



       try {
            mDatabaseHelper = new DatabaseHelper(this,"mydatabase.db");
            mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
            mDatabaseHelper1 = new DatabaseHelper(this,"database2.db");
            mSqLiteDatabase1 = mDatabaseHelper1.getWritableDatabase();
            Cursor cursor = mSqLiteDatabase.query("prods", new String[] {"_id","prod_name"},null, null,null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,new String[]{"prod_name"}, new int[]{android.R.id.text1},0);
            lv.setAdapter(listAdapter);



    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         ContentValues value = new ContentValues();
         Cursor c = (Cursor)lv.getItemAtPosition(position);
         value.put(DatabaseHelper.PROD_NAME_COLUMN,  c.getString(c.getColumnIndex("prod_name")));


        mSqLiteDatabase1.insert(DatabaseHelper.DATABASE_TABLE,null,value);

    }
    });

        } catch(SQLiteException e){
            Toast t = Toast.makeText(this, "БД не доступна", Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public void onTrash(View v) {
        mSqLiteDatabase.delete("prods", null, null);
        mSqLiteDatabase.close();
        mDatabaseHelper.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
