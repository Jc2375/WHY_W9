package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusboySchedules extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> busboyList;
    HashMap<String,List<String>> busboyInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busboy_schedules);
        expandableListView = findViewById(R.id.exListView_bs);
        makeBusboySchedules();
        listViewAdapter = new ExpandableListViewAdapter(this, busboyList, busboyInfo);
        expandableListView.setAdapter(listViewAdapter);
    }
    public void makeBusboySchedules(){
        busboyList = new ArrayList<String>();
        busboyInfo = new HashMap<String,List<String>>();

        busboyList.add("Busboy1");
        busboyList.add("Busboy2");

        List<String> days = new ArrayList<>();
        days.add("Monday:");
        days.add("Tuesday:");
        days.add("Wednesday:");
        days.add("Thursday:");
        days.add("Friday:");
        days.add("Saturday:");
        days.add("Sunday:");

        busboyInfo.put(busboyList.get(0),days);
        busboyInfo.put(busboyList.get(1),days);
    }
    public void backToSchedules(View v){
        Intent intent = new Intent(this,EmployeeSchedules.class);
        startActivity(intent);
    }
}