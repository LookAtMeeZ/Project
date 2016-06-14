package com.example.user.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    ArrayList<prod> list;
    EditText n;
    ListView lv;
    int size=0;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        n = (EditText) findViewById(R.id.editText);

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
        list = new ArrayList<>();

        lv.setAdapter(adapter);

    }
    public void onClick(View view){
        ContentValues values = new ContentValues();
        Cursor cursor = mSqLiteDatabase.query("cats", new String[] {DatabaseHelper.PROD_NAME_COLUMN
        },
                null, null,
                null, null, null) ;

        cursor.moveToFirst();

        String prodName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROD_NAME_COLUMN));
        values.put(DatabaseHelper.PROD_NAME_COLUMN, n.getText().toString());
        TextView infoTextView = (TextView)findViewById(R.id.textView);
        infoTextView.setText(prodName);
        n.setText("");
        cursor.close();
    }
    public void onBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}






