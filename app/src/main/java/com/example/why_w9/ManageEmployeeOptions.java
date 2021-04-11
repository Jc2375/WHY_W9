package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManageEmployeeOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employee_options);
    }
    //go to a screen with every employees payroll
    public void toEmployeePayroll(View v){
        Intent intent = new Intent(this, EmployeePayroll.class);
        startActivity(intent);
    }
    //go to screen where manager can choose to look at the
    //schedule of different workers
    public void toEmployeeSchedules(View v){
        Intent intent = new Intent(this,EmployeeSchedules.class);
        startActivity(intent);


    }
    public void toEmployeeRegistration(View v) {
        Bundle bundle=new Bundle();
        bundle.putBoolean("Manager_Check",true);
        Intent intent = new Intent(this, register.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}