package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChefSchedules extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> chefList;
    HashMap<String,List<String>> chefInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_schedules);
        expandableListView = findViewById(R.id.exListView_cs);
        makeChefSchedules();
        listViewAdapter = new ExpandableListViewAdapter(this, chefList, chefInfo);
        expandableListView.setAdapter(listViewAdapter);

    }
    public void makeChefSchedules(){
        chefList = new ArrayList<String>();
        chefInfo = new HashMap<String,List<String>>();

        chefList.add("Chef1");
        chefList.add("Chef2");


        List<String> days = new ArrayList<>();
        days.add("Monday:");
        days.add("Tuesday:");
        days.add("Wednesday:");
        days.add("Thursday:");
        days.add("Friday:");
        days.add("Saturday:");
        days.add("Sunday:");

        chefInfo.put(chefList.get(0),days);
        chefInfo.put(chefList.get(1),days);
    }
    public void backToSchedules(View v){
        Intent intent = new Intent(this,EmployeeSchedules.class);
        startActivity(intent);
    }
}