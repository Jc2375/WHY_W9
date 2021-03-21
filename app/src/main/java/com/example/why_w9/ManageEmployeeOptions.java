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
    public void toEmployeePayroll(View v){
        Intent intent = new Intent(this, EmployeePayroll.class);
        startActivity(intent);


    }
}