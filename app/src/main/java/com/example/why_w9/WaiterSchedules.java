package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WaiterSchedules extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> waiterList;
    HashMap<String,List<String>> waiterInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_schedules);
        expandableListView = findViewById(R.id.exListView_ws);
        makeWaiterSchedules();
        listViewAdapter = new ExpandableListViewAdapter(this, waiterList, waiterInfo);
        expandableListView.setAdapter(listViewAdapter);
    }
    public void makeWaiterSchedules(){
        waiterList = new ArrayList<String>();
        waiterInfo = new HashMap<String,List<String>>();

        waiterList.add("Waiter1");
        waiterList.add("Waiter2");

        List<String> days = new ArrayList<>();
        days.add("Monday:");
        days.add("Tuesday:");
        days.add("Wednesday:");
        days.add("Thursday:");
        days.add("Friday:");
        days.add("Saturday:");
        days.add("Sunday:");

        waiterInfo.put(waiterList.get(0),days);
        waiterInfo.put(waiterList.get(1),days);
    }
    public void backToSchedules(View v){
        Intent intent = new Intent(this,EmployeeSchedules.class);
        startActivity(intent);
    }
}