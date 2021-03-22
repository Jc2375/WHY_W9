package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EmployeeSchedules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_schedules);
    }
    public void toChefSchedules(View view){
        Intent intent = new Intent(this,ChefSchedules.class);
        startActivity(intent);

    }

    public void toWaiterSchedules(View view){
        Intent intent = new Intent(this,WaiterSchedules.class);
        startActivity(intent);

    }
    public void toBusboySchedules(View view){
        Intent intent = new Intent(this,BusboySchedules.class);
        startActivity(intent);

    }
    public void toDriverSchedules(View view){
        Intent intent = new Intent(this,DriverSchedules.class);
        startActivity(intent);
    }



}