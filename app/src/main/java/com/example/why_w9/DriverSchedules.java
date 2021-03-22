package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriverSchedules extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> driverList;
    HashMap<String,List<String>> driverInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_schedules);
        expandableListView = findViewById(R.id.exListView_ds);
        makeDriverSchedules();
        listViewAdapter = new ExpandableListViewAdapter(this, driverList, driverInfo);
        expandableListView.setAdapter(listViewAdapter);
    }
    public void makeDriverSchedules(){
        driverList = new ArrayList<String>();
        driverInfo = new HashMap<String,List<String>>();

        driverList.add("Driver1");
        driverList.add("Driver2");

        List<String> days = new ArrayList<>();
        days.add("Monday:");
        days.add("Tuesday:");
        days.add("Wednesday:");
        days.add("Thursday:");
        days.add("Friday:");
        days.add("Saturday:");
        days.add("Sunday:");

        driverInfo.put(driverList.get(0),days);
        driverInfo.put(driverList.get(1),days);
    }
    public void backToSchedules(View v){
        Intent intent = new Intent(this,EmployeeSchedules.class);
        startActivity(intent);
    }
}