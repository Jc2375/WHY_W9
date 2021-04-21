package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class driver_home extends AppCompatActivity {
    public static String ID= FirebaseAuth.getInstance().getCurrentUser().getUid();

    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_home);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
    }

    public void onButtonClick(View v) {
        if(v.getId() == R.id.BdriverClockIn){
            //Get uid from database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            //Creating bundle to pass along info of uid and usertype to clock in page
            Bundle bundle = new Bundle();
            bundle.putString("usertype","driver");
            bundle.putString("uid",uid);
            Intent i = new Intent(driver_home.this, clockinout.class);
            i.putExtras(bundle);
            startActivity(i);
        }

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
        if (v.getId() == R.id.myScheduleDriver){
            Intent i = new Intent(this,MySchedule.class);
            i.putExtra("type","Drivers");
            i.putExtra("id","Driver1");
            startActivity(i);
        }
    }
}