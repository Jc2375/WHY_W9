package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class driver_home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_home);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            Toast.makeText(driver_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(driver_home.this, MainActivity.class);
            startActivity(i);
        }

        if (v.getId() == R.id.bViewAvailableOrders) {
            Intent i = new Intent(driver_home.this, driver_available_orders.class);
            startActivity(i);
        }

        if (v.getId() == R.id.bViewCurrentOrder) {
            Intent i = new Intent(driver_home.this, driver_current_order.class);
            startActivity(i);
        }
    }
}