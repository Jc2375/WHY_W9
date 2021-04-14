package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChefSchedules extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseDatabase root;
    DatabaseReference ref;

    private RecyclerView recyclerView;
    private ScheduleRecAdapter adapter;
    private ArrayList<ScheduleModel> list;
    ArrayAdapter<String> adapterSpinner;

    String dayField;
    String startTimeField;
    String endTimeField;
    String chefName;

    Spinner daySpinner;
    Spinner timeStartSpinner;
    Spinner timeEndSpinner;
    Spinner nameSpinner;
    Button schedule;

    ArrayList<String> chefNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_schedules);
        schedule = findViewById(R.id.ScheduleBtn_c);
        schedule.setEnabled(false);

        recyclerView = findViewById(R.id.chefRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Schedules").child("Chefs");

        daySpinner = findViewById(R.id.dayOption_c);
        timeStartSpinner = findViewById(R.id.timeStart_c);
        timeEndSpinner = findViewById(R.id.timeEnd_c);
        nameSpinner  = findViewById(R.id.nameSpinner_c);
        nameSpinner.setEnabled(false);
        chefNames = new ArrayList<>();

        list = new ArrayList<>();
        adapter = new ScheduleRecAdapter(this,list);

        recyclerView.setAdapter(adapter);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    ScheduleModel model = snapshot1.getValue(ScheduleModel.class);
                    chefNames.add(model.name);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
                createNameSpinner();
                schedule.setEnabled(true);
                return;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Spinner daySpinner = findViewById(R.id.dayOption_c);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dayOption, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);
        daySpinner.setOnItemSelectedListener(this);

        Spinner timesSpinner = findViewById(R.id.timeStart_c);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.timeOptions, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesSpinner.setAdapter(adapter2);
        timesSpinner.setOnItemSelectedListener(this);

        Spinner timesSpinner2 = findViewById(R.id.timeEnd_c);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.timeOptions, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesSpinner2.setAdapter(adapter3);
        timesSpinner2.setOnItemSelectedListener(this);
    }

    private void createNameSpinner() {
        adapterSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, chefNames);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(adapterSpinner);
        nameSpinner.setEnabled(true);
    }
    public void backToSchedules(View v){
        Intent intent = new Intent(this,EmployeeSchedules.class);
        startActivity(intent);
    }

    public void addShift(View v){
        nameSpinner.setEnabled(false);
        dayField = daySpinner.getSelectedItem().toString();
        startTimeField = timeStartSpinner.getSelectedItem().toString();
        endTimeField =timeEndSpinner.getSelectedItem().toString();
        chefName = nameSpinner.getSelectedItem().toString();
        ref = root.getReference("Schedules").child("Chefs").child(chefName);
        if(dayField.compareTo("Monday")==0){
            ref.child("mondayTime").setValue(startTimeField+"-"+endTimeField);

            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).mondayTime = startTimeField+"-"+endTimeField;
            }
        }

        else if(dayField.compareTo("Tuesday")==0){
            ref.child("tuesdayTime").setValue(startTimeField+"-"+endTimeField);

            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).tuesdayTime = startTimeField+"-"+endTimeField;
            }
        }

        else if(dayField.compareTo("Wednesday")==0){
            ref.child("wednesdayTime").setValue(startTimeField+"-"+endTimeField);

            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).wednesdayTime = startTimeField+"-"+endTimeField;
            }
        }

        else if(dayField.compareTo("Thursday")==0){

            ref.child("thursdayTime").setValue(startTimeField+"-"+endTimeField);
            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).thursdayTime = startTimeField+"-"+endTimeField;
            }
        }

        else if(dayField.compareTo("Friday")==0){
            ref.child("fridayTime").setValue(startTimeField+"-"+endTimeField);

            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).fridayTime = startTimeField+"-"+endTimeField;
            }
        }

        else if(dayField.compareTo("Saturday")==0){
            ref.child("saturdayTime").setValue(startTimeField+"-"+endTimeField);

            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).saturdayTime = startTimeField+"-"+endTimeField;
            }
        }

        else if(dayField.compareTo("Sunday")==0){
            ref.child("sundayTime").setValue(startTimeField+"-"+endTimeField);

            int pos = searchList(chefName);
            if (pos!=-1){
                list.get(pos).sundayTime = startTimeField+"-"+endTimeField;
            }
        }

        else{

        }
        adapter.notifyDataSetChanged();
        nameSpinner.setEnabled(true);
    }
    private int searchList(String name) {
        for(int i = 0; i< chefNames.size(); i++){
            if (chefNames.get(i).compareTo(name)==0){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}