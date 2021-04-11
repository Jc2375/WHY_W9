package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriverSchedules extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> driverList;
    HashMap<String,List<String>> driverInfo;
    List<String> days;
    FirebaseDatabase root;
    DatabaseReference ref;
    ArrayList<String> vals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_schedules);
        vals = new ArrayList<>();
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Schedules");//.child("Chefs");
        expandableListView = findViewById(R.id.exListView_ds);
        makeDriverSchedules();
        listViewAdapter = new ExpandableListViewAdapter(this, driverList, driverInfo);
        expandableListView.setAdapter(listViewAdapter);

        Spinner daySpinner = findViewById(R.id.dayOption_d);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dayOption, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);
        daySpinner.setOnItemSelectedListener(this);

        Spinner timesSpinner = findViewById(R.id.timeStart_d);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.timeOptions, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesSpinner.setAdapter(adapter2);
        timesSpinner.setOnItemSelectedListener(this);

        Spinner timesSpinner2 = findViewById(R.id.timeEnd_d);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.timeOptions, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesSpinner2.setAdapter(adapter3);
        timesSpinner2.setOnItemSelectedListener(this);


    }
    public void makeDriverSchedules(){
        /*
        ref = ref.child("Chefs").child("Chef1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    vals.add(snapshot1.getValue().toString());
                }
                Log.d("times",vals.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */
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

    public void addShift(View v) {
        //get start time from spinner
        Spinner startTime = (Spinner)findViewById(R.id.timeStart_d);
        String start = startTime.getSelectedItem().toString();

        //get end time from spinner
        Spinner endTime = (Spinner)findViewById(R.id.timeEnd_d);
        String end = endTime.getSelectedItem().toString();

        EditText driverName_tv = (EditText) findViewById(R.id.editTextDriverName);
        String driverName = driverName_tv.getText().toString();

        //get day option from spinner
        Spinner day_spin = (Spinner)findViewById(R.id.dayOption_d);
        String dayField = day_spin.getSelectedItem().toString();

        if (driverName.compareTo(driverList.get(0)) == 0) {

            List<String> newDays = new ArrayList<>();
            newDays.add((String) listViewAdapter.getChild(0,0));
            newDays.add((String) listViewAdapter.getChild(0,1));
            newDays.add((String) listViewAdapter.getChild(0,2));
            newDays.add((String) listViewAdapter.getChild(0,3));
            newDays.add((String) listViewAdapter.getChild(0,4));
            newDays.add((String) listViewAdapter.getChild(0,5));
            newDays.add((String) listViewAdapter.getChild(0,6));


            if (dayField.compareTo("Monday") == 0) {
                newDays.set(0,"Monday:" + start + "-" + end);
            }
            if (dayField.compareTo("Tuesday") == 0) {
                newDays.set(1,"Tuesday:" + start + "-" + end);
            }
            if (dayField.compareTo("Wednesday") == 0) {
                newDays.set(2,"Wednesday:" + start + "-" + end);
            }
            if (dayField.compareTo("Thursday") == 0) {
                newDays.set(3,"Thursday:" + start + "-" + end);
            }
            if (dayField.compareTo("Friday") == 0) {
                newDays.set(4,"Friday:" + start + "-" + end);
            }
            if (dayField.compareTo("Saturday") == 0) {
                newDays.set(5,"Saturday:" + start + "-" + end);
            }
            if (dayField.compareTo("Sunday") == 0) {
                newDays.set(6,"Sunday:" + start + "-" + end);
            }
            listViewAdapter.updateExpListView(driverList,newDays,0);
        }
        else if(driverName.compareTo(driverList.get(1))==0){
            List<String> newDays = new ArrayList<>();
            newDays.add((String) listViewAdapter.getChild(1,0));
            newDays.add((String) listViewAdapter.getChild(1,1));
            newDays.add((String) listViewAdapter.getChild(1,2));
            newDays.add((String) listViewAdapter.getChild(1,3));
            newDays.add((String) listViewAdapter.getChild(1,4));
            newDays.add((String) listViewAdapter.getChild(1,5));
            newDays.add((String) listViewAdapter.getChild(1,6));

            if (dayField.compareTo("Monday") == 0) {
                newDays.set(0,"Monday:" + start + "-" + end);
            }
            if (dayField.compareTo("Tuesday") == 0) {
                newDays.set(1,"Tuesday:" + start + "-" + end);
            }
            if (dayField.compareTo("Wednesday") == 0) {
                newDays.set(2,"Wednesday:" + start + "-" + end);
            }
            if (dayField.compareTo("Thursday") == 0) {
                newDays.set(3,"Thursday:" + start + "-" + end);
            }
            if (dayField.compareTo("Friday") == 0) {
                newDays.set(4, "Friday:" + start + "-" + end);
            }
            if (dayField.compareTo("Saturday") == 0) {
                newDays.set(5,"Saturday:" + start + "-" + end);
            }
            if (dayField.compareTo("Sunday") == 0) {
                newDays.set(6,"Sunday:" + start + "-" + end);
            }
            listViewAdapter.updateExpListView(driverList,newDays,1);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}