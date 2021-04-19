package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class  EmployeePayroll extends AppCompatActivity {
    FirebaseDatabase root;
    DatabaseReference ref;
    private RecyclerView recyclerView;
    private PayrollRecAdapter adapter;
    private ArrayList<PayrollModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_payroll);

        //initialize variables needed
        recyclerView = findViewById(R.id.payrollRecView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Payroll");
        list = new ArrayList<>();
        adapter = new PayrollRecAdapter(this,list);

        //originally empty
        recyclerView.setAdapter(adapter);

        //get data from firebase
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    PayrollModel model = snapshot1.getValue(PayrollModel.class);
                    list.add(model);
                }
                //show data on recycler view since we've updated
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

