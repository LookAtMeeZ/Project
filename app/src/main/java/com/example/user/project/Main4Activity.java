package com.example.user.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper1;
    private SQLiteDatabase mSqLiteDatabase1;
    ListView lv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        lv = (ListView) findViewById(R.id.listview2);



        try {
            mDatabaseHelper1 = new DatabaseHelper(this, "database2.db");
            mSqLiteDatabase1 = mDatabaseHelper1.getWritableDatabase();
            Cursor cursor = mSqLiteDatabase1.query("prods", new String[]{"_id", "prod_name"}, null, null, null, null, null);
            final CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"prod_name"}, new int[]{android.R.id.text1}, 0);
            lv.setAdapter(listAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Cursor c = (Cursor) parent.getItemAtPosition(position);

                    mSqLiteDatabase1.delete("prods", "prod_name = ?", new String[]{ c.getString( c.getColumnIndex("prod_name"))});
                    Cursor cursor_update = mSqLiteDatabase1.query("prods", new String[]{"_id", "prod_name"}, null, null, null, null, null);
                    listAdapter.swapCursor(cursor_update);
                }

            });
        }
        catch (SQLiteException e) {
            Toast t = Toast.makeText(this, "БД не доступна", Toast.LENGTH_SHORT);
            t.show();
            }
    }

    public void delete(View v) {
        mSqLiteDatabase1.delete("prods", null, null);
        mSqLiteDatabase1.close();
        mDatabaseHelper1.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
