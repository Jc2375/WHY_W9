package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class driver_home extends AppCompatActivity {
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_home);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            Toast.makeText(driver_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(driver_home.this, MainActivity.class);
            startActivity(i);
        }

        if (v.getId() == R.id.bViewAvailableOrders) {
            Bundle bundle = new Bundle();
            bundle.putString("usertype","driver");
            bundle.putString("uid",uid);
            Intent i = new Intent(driver_home.this, finishedCooking.class);
            i.putExtras(bundle);
            startActivity(i);
        }

        if (v.getId() == R.id.bViewCurrentOrder) {//dont need right now, talk to jason about why
            Bundle bundle = new Bundle();
            bundle.putString("usertype","driver");
            bundle.putString("uid",uid);
            Intent i = new Intent(driver_home.this, driver_current_order.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}