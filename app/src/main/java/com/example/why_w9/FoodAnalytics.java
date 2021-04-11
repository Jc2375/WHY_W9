package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

public class FoodAnalytics extends AppCompatActivity {
    Spinner itemSpinner;
    FirebaseDatabase root;
    DatabaseReference menuItem;

    ArrayList<String> menuOptions;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> monthlySales;
    ArrayList<String> months;

    ArrayList<BarEntry> barEntryArrayList;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analytics);

        itemSpinner = findViewById(R.id.spinner1);
        itemSpinner.setEnabled(false);

        barChart = findViewById(R.id.foodOptionChart);
        barEntryArrayList = new ArrayList<>();
        monthlySales = new ArrayList<>();
        menuOptions = new ArrayList<>();
        menuOptions.add("Choose item");

        root = FirebaseDatabase.getInstance();
        menuItem = root.getReference("Food");
        //create spinner options by getting the names
        //of the menu items and putting them into the array list
        menuItem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    menuOptions.add(snapshot1.child("Name").getValue().toString());
                }
                //Log.d("Food",menuOptions.toString());
                //create Spinner
                createSpinner();
                itemSpinner.setEnabled(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //when a item from the menu is selected
        //show its data from January to April
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Choose item")){

                }
                else {
                    itemSpinner.setEnabled(false);
                    String item = parent.getItemAtPosition(position).toString();
                    menuItem = root.getReference("FoodStats").child(item);
                    monthlySales.clear();
                    menuItem.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                monthlySales.add(Integer.parseInt(snapshot1.getValue().toString()));
                            }
                            //Log.d("sales",monthlySales.toString());
                            //daySpinner.setEnabled(true);
                            makeGraph(monthlySales);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }//end of onCreate

    private void makeGraph(ArrayList<Integer> monthlySales) {

        barEntryArrayList.clear();
        for(int i=0;i<monthlySales.size();i++){
            float z = i;
            barEntryArrayList.add(new BarEntry(z,monthlySales.get(i)));
        }

        barChart.getAxisRight().setEnabled(false);
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Monthly Sales");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(10);
        Description description = new Description();
        description.setText("Monthly Sales");
        barChart.setDescription(description);
        //set data
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        //get string array of every month to label x axis
        String[] stringArray = getResources().getStringArray(R.array.months);
        //only add a month to x axis if we have data for that month
        ArrayList<String> MONTHS = new ArrayList<>();
        for(int i=0;i<monthlySales.size();i++){
            MONTHS.add(stringArray[i]);
        }



        //change x axis to show months
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(MONTHS));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(MONTHS.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(1000);
        barChart.invalidate();
        itemSpinner.setEnabled(true);

    }

    private void createSpinner() {
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,menuOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);
    }


}

