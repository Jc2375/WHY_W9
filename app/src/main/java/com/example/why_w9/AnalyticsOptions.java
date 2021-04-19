package com.example.why_w9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class  AnalyticsOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_options);
    }
    public void toDailyTraffic(View v){
        Intent intent = new Intent(this,DailyTraffic.class);
        startActivity(intent);
    }
    public void toFoodAnalytics(View v){
        Intent intent = new Intent(this,FoodAnalytics.class);
        startActivity(intent);
    }
    public void toRevenuesExpenses(View v){
        Intent intent = new Intent(this,OperationsInformation.class);
        startActivity(intent);

    }
}