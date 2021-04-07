package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DailyTraffic extends AppCompatActivity {
    BarChart customerBarChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> times;
    FirebaseDatabase root;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_traffic);
        //get database reference to a certain day
        root = FirebaseDatabase.getInstance();
        //ref = root.getReference("DailyTraffic").child("4-2-21");
        customerBarChart = findViewById(R.id.customerVisitsBarChart);
        barEntryArrayList = new ArrayList<>();
        times = new ArrayList<>();
        times.add("10am");
        times.add("11am");
        times.add("12pm");
        times.add("1pm");
        times.add("2pm");
        times.add("3pm");
        times.add("4pm");
        times.add("5pm");
        times.add("6pm");
        times.add("7pm");
        times.add("8pm");
        times.add("9pm");
        times.add("10pm");
    }

    public void refresh_4_2_21(View v){
        barEntryArrayList.clear();
        ref = root.getReference("DailyTraffic").child("4-2-21");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float i=0;
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String s = snapshot1.getValue().toString();
                    barEntryArrayList.add(new BarEntry(i,Float.parseFloat(s)));
                    i+=1;
                }

                customerBarChart.getAxisRight().setEnabled(false);

                BarDataSet barDataSet  = new BarDataSet(barEntryArrayList,"hourly customer count ");
                //add color to bars
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                Description description = new Description();
                description.setText("Customers visited");
                customerBarChart.setDescription(description);
                //set data
                BarData barData = new BarData(barDataSet);
                customerBarChart.setData(barData);

                XAxis xAxis = customerBarChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(times.size());
                xAxis.setLabelRotationAngle(270);
                customerBarChart.animateY(1000);
                customerBarChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void refresh_4_3_21(View v){
        barEntryArrayList.clear();
        ref = root.getReference("DailyTraffic").child("4-3-21");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float i=0;
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String s = snapshot1.getValue().toString();
                    barEntryArrayList.add(new BarEntry(i,Float.parseFloat(s)));
                    i+=1;
                }


                customerBarChart.getAxisRight().setEnabled(false);

                BarDataSet barDataSet  = new BarDataSet(barEntryArrayList,"hourly customer count");
                //add color to bars
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                Description description = new Description();
                description.setText("Customers visited");
                customerBarChart.setDescription(description);
                //set data
                BarData barData = new BarData(barDataSet);
                customerBarChart.setData(barData);

                XAxis xAxis = customerBarChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(times.size());
                xAxis.setLabelRotationAngle(270);
                customerBarChart.animateY(1000);
                customerBarChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}