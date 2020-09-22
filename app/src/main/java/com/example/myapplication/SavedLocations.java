package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class SavedLocations extends AppCompatActivity {


    DbHelper dbHelper;
    Button list;
    ListView lv;
    Map<String, Object> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_locations);
        dbHelper = new DbHelper();
        data=dbHelper.data;
        lv=findViewById(R.id.listView);
        list=findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonReadClick();
            }
        });

    }

    private void buttonReadClick(){ dbHelper.read();}
}
