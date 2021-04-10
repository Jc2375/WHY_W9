package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OperationsInformation extends AppCompatActivity {
    BarChart infos;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> months;
    FirebaseDatabase root;
    DatabaseReference expenses;
    DatabaseReference revenues;
    ArrayList<Float> monthlyExpenses;
    ArrayList<Float> monthlyRevenues;
    Float yearProfit;
    Button RC;
    Button Profits;
    TextView yearProfitAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_information);
        root = FirebaseDatabase.getInstance();
        expenses = root.getReference("Expenses").child("2021");
        revenues = root.getReference("Revenues").child("2021");
        yearProfitAmount = findViewById(R.id.profitAmountTV);
        monthlyExpenses = new ArrayList<>();
        monthlyRevenues = new ArrayList<>();
        yearProfit = Float.valueOf(0);
        infos = findViewById(R.id.operationBarChart);
        barEntryArrayList = new ArrayList<>();
        RC = findViewById(R.id.R_C_Btn);
        Profits = findViewById(R.id.ProfitBtn);
        //disable buttons to prevent error
        //need to get data from db first
        RC.setEnabled(false);
        Profits.setEnabled(false);

        //get expenses from database and add to monthlyExpenses ArrayList
        expenses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Log.d("val",snapshot1.getValue().toString());
                    monthlyExpenses.add(Float.parseFloat(snapshot1.getValue().toString()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //get revenues from database and add to monthlyRevenues ArrayList
        revenues.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Log.d("val",snapshot1.getValue().toString());
                    monthlyRevenues.add(Float.parseFloat(snapshot1.getValue().toString()));
                }
                //once data is obtained we can make buttons clickable to show different options
                RC.setEnabled(true);
                Profits.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
    public void managerMain(View v){
        Intent intent = new Intent(this,manager_home.class);
        startActivity(intent);
    }

    public void showRandC(View v){
        //Log.d("val",monthlyExpenses.toString());
        //Log.d("val",monthlyRevenues.toString());
        barEntryArrayList.clear();
        yearProfit = Float.valueOf(0);
        for (int i=0;i<monthlyRevenues.size();i++){
            float z = i;
            barEntryArrayList.add(new BarEntry(z,monthlyExpenses.get(i)*-1));
            barEntryArrayList.add(new BarEntry(z,monthlyRevenues.get(i)));
            yearProfit +=monthlyRevenues.get(i);
            yearProfit -=monthlyExpenses.get(i);
        }
        infos.getAxisRight().setEnabled(false);
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Revenues/Expeneses");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(10);
        Description description = new Description();
        description.setText("Revenues/Profits");
        infos.setDescription(description);
        //set data
        BarData barData = new BarData(barDataSet);
        infos.setData(barData);
        //get string array of every month to label x axis
        String[] stringArray = getResources().getStringArray(R.array.months);
        //only add a month to x axis if we have data for that month
        ArrayList<String> MONTHS = new ArrayList<>();
        for(int i=0;i<monthlyExpenses.size();i++){
            MONTHS.add(stringArray[i]);
        }



        //change x axis to show months
        XAxis xAxis = infos.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(MONTHS));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(MONTHS.size());
        xAxis.setLabelRotationAngle(270);
        infos.animateY(1000);
        infos.invalidate();
        yearProfitAmount.setText(yearProfit.toString());

    }
    public void showProfits(View v){
        barEntryArrayList.clear();
        //monthlyProfits.clear();
        yearProfit = Float.valueOf(0);
        for (int i=0;i<monthlyRevenues.size();i++){
            float z = i;
            barEntryArrayList.add(new BarEntry(z,monthlyRevenues.get(i)-monthlyExpenses.get(i)));
            yearProfit -=monthlyExpenses.get(i);
            yearProfit +=monthlyRevenues.get(i);
        }
        infos.getAxisRight().setEnabled(false);
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Profits");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(10);
        Description description = new Description();
        description.setText("Profits");
        infos.setDescription(description);
        //set data
        BarData barData = new BarData(barDataSet);
        infos.setData(barData);
        //get string array of every month to label x axis
        String[] stringArray = getResources().getStringArray(R.array.months);
        //only add a month to x axis if we have data for that month
        ArrayList<String> MONTHS = new ArrayList<>();
        for(int i=0;i<monthlyExpenses.size();i++){
            MONTHS.add(stringArray[i]);
        }

        XAxis xAxis = infos.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(MONTHS));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(MONTHS.size());
        xAxis.setLabelRotationAngle(270);
        infos.animateY(1000);
        infos.invalidate();
        yearProfitAmount.setText(yearProfit.toString());

    }
}