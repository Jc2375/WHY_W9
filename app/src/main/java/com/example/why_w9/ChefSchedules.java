package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChefSchedules extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> chefList;
    HashMap<String,List<String>> chefInfo;
    List<String> days;

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

    public void addShift(View v) {
        EditText startTime_tv = (EditText) findViewById(R.id.editTextStartTime);
        String start = startTime_tv.getText().toString();

        EditText endTime_tv = (EditText) findViewById(R.id.editTextEndTime);
        String end = endTime_tv.getText().toString();

        EditText chefName_tv = (EditText) findViewById(R.id.editTextChefName);
        String chefName = chefName_tv.getText().toString();

        EditText dayField_tv = (EditText) findViewById(R.id.editTextDay);
        String day = dayField_tv.getText().toString();

        if (chefName.compareTo(chefList.get(0)) == 0) {

            List<String> newDays = new ArrayList<>();
            newDays.add("Monday:");
            newDays.add("Tuesday:");
            newDays.add("Wednesday:");
            newDays.add("Thursday:");
            newDays.add("Friday:");
            newDays.add("Saturday:");
            newDays.add("Sunday:");

            if (day.compareTo("Monday") == 0) {
                newDays.set(0,"Monday:" + start + "-" + end);
            }
            if (day.compareTo("Tuesday") == 0) {
                newDays.set(1,"Tuesday:" + start + "-" + end);
            }
            if (day.compareTo("MWednesday") == 0) {
                newDays.set(2,"Wednesday:" + start + "-" + end);
            }
            if (day.compareTo("Thursday") == 0) {
                newDays.set(3,"Thursday:" + start + "-" + end);
            }
            if (day.compareTo("Friday") == 0) {
                newDays.set(4,"Friday:" + start + "-" + end);
            }
            if (day.compareTo("Saturday") == 0) {
                newDays.set(5,"Saturday:" + start + "-" + end);
            }
            if (day.compareTo("Sunday") == 0) {
                newDays.set(6,"Sunday:" + start + "-" + end);
            }
            listViewAdapter.updateExpListView(chefList,newDays,0);
        }
        else if(chefName.compareTo(chefList.get(1))==0){
            List<String> newDays = new ArrayList<>();
            newDays.add("Monday:");
            newDays.add("Tuesday:");
            newDays.add("Wednesday:");
            newDays.add("Thursday:");
            newDays.add("Friday:");
            newDays.add("Saturday:");
            newDays.add("Sunday:");

            if (day.compareTo("Monday") == 0) {
                newDays.set(0,"Monday:" + start + "-" + end);
            }
            if (day.compareTo("Tuesday") == 0) {
                newDays.set(1,"Tuesday:" + start + "-" + end);
            }
            if (day.compareTo("MWednesday") == 0) {
                newDays.set(2,"Wednesday:" + start + "-" + end);
            }
            if (day.compareTo("Thursday") == 0) {
                newDays.set(3,"Thursday:" + start + "-" + end);
            }
            if (day.compareTo("Friday") == 0) {
                newDays.set(4, "Friday:" + start + "-" + end);
            }
            if (day.compareTo("Saturday") == 0) {
                newDays.set(5,"Saturday:" + start + "-" + end);
            }
            if (day.compareTo("Sunday") == 0) {
                newDays.set(6,"Sunday:" + start + "-" + end);
            }
            listViewAdapter.updateExpListView(chefList,newDays,1);
        }
    }
}