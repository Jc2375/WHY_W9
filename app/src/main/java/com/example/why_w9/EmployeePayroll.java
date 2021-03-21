package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeePayroll extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> nameList;
    HashMap<String,List<String>> payroll_Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_payroll);

        expandableListView = findViewById(R.id.exListView);
        make_payroll();
        listViewAdapter = new ExpandableListViewAdapter(this,nameList,payroll_Info);
        expandableListView.setAdapter(listViewAdapter);
    }

    public void make_payroll(){
        nameList = new ArrayList<String>();
        payroll_Info = new HashMap<String,List<String>>();

        nameList.add("employee1");
        nameList.add("employee2");
        nameList.add("employee3");

        List<String> info = new ArrayList<>();
        info.add("Role:");
        info.add("Hourly Rate:");
        info.add("Hours worked this week:");
        info.add("Pay this week:");



        payroll_Info.put(nameList.get(0),info);
        payroll_Info.put(nameList.get(1),info);
        payroll_Info.put(nameList.get(2),info);

    }
    public void backtoOptions(View v){
        Intent intent = new Intent(EmployeePayroll.this, ManageEmployeeOptions.class);
        startActivity(intent);
    }
}