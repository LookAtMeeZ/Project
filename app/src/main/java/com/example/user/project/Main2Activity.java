package com.example.user.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


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
        lv = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1);
        list = new ArrayList<>();

        lv.setAdapter(adapter);

    }
    public void add(View view){
        prod newProd = new prod();
        newProd.str=n.getText().toString();
        newProd.num=true;
        newProd.icon="-";
        int a=1;

        if(size==0) {
            list.add(newProd);
            adapter.add(newProd.str);
            size++;
        }
        else {

            for (int i = 0; i < size; i++) {
                if (n.getText().toString().equals(list.get(i).str
                ))
                    a = 0;

            }
        if (a!=0){
                    list.add(newProd);
                    adapter.add(newProd.str);
                    size++;
                    a=1;
                }
        else
            Toast.makeText(Main2Activity.this,  "Уже добавлено", Toast.LENGTH_SHORT).show();

        }

        n.setText("");

    }
    public void onBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}






