package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MySchedule extends AppCompatActivity {
    FirebaseDatabase root;
    DatabaseReference ref;
    TextView mondaySlot;
    TextView tuesdaySlot;
    TextView wednesdaySlot;
    TextView thursdaySlot;
    TextView fridaySlot;
    TextView saturdaySlot;
    TextView sundaySlot;
    String userType;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        userType = getIntent().getStringExtra("type");
        userId = getIntent().getStringExtra("id");
        mondaySlot = findViewById(R.id.monday_ms);
        tuesdaySlot = findViewById(R.id.tuesday_ms);
        wednesdaySlot = findViewById(R.id.wednesday_ms);
        thursdaySlot  = findViewById(R.id.thursday_ms);
        fridaySlot = findViewById(R.id.friday_ms);
        saturdaySlot = findViewById(R.id.saturday_ms);
        sundaySlot = findViewById(R.id.sunday_ms);


        //get specific employees schedule from db and return it as java object
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Schedules").child(userType).child(userId);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ScheduleModel schedule =  snapshot.getValue(ScheduleModel.class);
                //display schedule info on screen for specific employee
                mondaySlot.setText(schedule.getMondayTime());
                tuesdaySlot.setText(schedule.getTuesdayTime());
                wednesdaySlot.setText(schedule.getWednesdayTime());
                thursdaySlot.setText(schedule.getThursdayTime());
                fridaySlot.setText(schedule.getFridayTime());
                saturdaySlot.setText(schedule.getSaturdayTime());
                sundaySlot.setText(schedule.getSundayTime());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}