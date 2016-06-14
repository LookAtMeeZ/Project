package com.example.user.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
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
    private String[] prodName = new String[100];
    private int i=0;

    ArrayList<prod> list;
    EditText n;
    int size=0;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        n = (EditText) findViewById(R.id.editText);
    }
    public void onClick(View view){
        TextView infoTextView = (TextView)findViewById(R.id.textView);

        try {
            mDatabaseHelper = new DatabaseHelper(this);
            mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(DatabaseHelper.PROD_NAME_COLUMN, n.getText().toString());
            mSqLiteDatabase.insert(DatabaseHelper.DATABASE_TABLE,null,value);
            mSqLiteDatabase.close();
            mDatabaseHelper.close();
        } catch(SQLiteException e){
            Toast t = Toast.makeText(this, "БД не доступна", Toast.LENGTH_SHORT);
            t.show();
        }

        try {
            mDatabaseHelper = new DatabaseHelper(this);
            mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
            Cursor cursor = mSqLiteDatabase.query("prods", new String[] {DatabaseHelper.PROD_NAME_COLUMN},null, null,null, null, null);
            if (cursor.moveToFirst()) {
                infoTextView.setText("");
                while (cursor.moveToNext()) {
                    infoTextView.setText(infoTextView.getText()+"\n"+cursor.getString(0));
                }
            }
            mSqLiteDatabase.close();
            mDatabaseHelper.close();
        } catch(SQLiteException e){
            Toast t = Toast.makeText(this, "БД не доступна", Toast.LENGTH_SHORT);
            t.show();
        }

        n.setText("");
    }
    public void onBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}






