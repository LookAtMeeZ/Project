



package com.example.user.project;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<prod> prodlist = new ArrayList<>();
        final prod list = new prod();
        prodlist.add(list);
        list.num = true;
        final ImageButton b3 = (ImageButton) findViewById(R.id.imageButton);
        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final Button b1 = (Button) findViewById(R.id.b1);
        final ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        editText.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Зачем вы нажали?", Toast.LENGTH_SHORT).show();
                switch (v.getId()) {

                    case R.id.imageButton:
                        Toast.makeText(MainActivity.this, "Добавление", Toast.LENGTH_SHORT).show();
                        editText.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.VISIBLE);
                        if(b2.getVisibility() == View.VISIBLE)
                            b2.setVisibility(View.INVISIBLE);
                        else
                            b2.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, String.valueOf(list.num), Toast.LENGTH_SHORT).show();
                    case R.id.b1:
                        list.str = editText.getText().toString();
                        Toast.makeText(MainActivity.this, String.valueOf(list.str), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.switch1:
                        list.num = !list.num;
                        Toast.makeText(MainActivity.this, String.valueOf(list.num), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.b2:
                        if(b3.getVisibility() == View.VISIBLE)
                            b3.setVisibility(View.INVISIBLE);
                        else
                            b3.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, String.valueOf(list.num), Toast.LENGTH_SHORT).show();
                }
            }
        };


        b3.setOnClickListener(listener);
        switch1.setOnClickListener(listener);
        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.project/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.project/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
