package com.example.why_w9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class clockinout extends AppCompatActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockinout);
        showDateTime();
        Bundle bundle = getIntent().getExtras();
        String usertype = bundle.getString("usertype");
        String uid = bundle.getString("uid");


    }

    //Shows current date & time at top of page
    public void showDateTime(){
        TextView timeText = (TextView)findViewById(R.id.clockinTime);
        String currentTimeString = new SimpleDateFormat("MM/dd/yy"+" hh:mm a").format(new Date());
        timeText.setText("The current time is: "+currentTimeString);
    }

    //Get date
    public String getDate(){
        String currDate = new SimpleDateFormat("MM_dd").format(new Date());
        return currDate;
    }
    //Get time
    public String getTime(){
        String currTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return currTime;
    }
    //Get year
    public String getYear(){
        String currYear = new SimpleDateFormat("yyyy").format(new Date());
        return currYear;
    }

    public void clockIn(){
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reff = db.getReference().child("User").child(userid).child("Year").child(getYear()).child(getDate());

        HashMap<String, Object> map = new HashMap<>();
        map.put(getTime(), "Clocked in");

        reff.updateChildren(map);
    }

    public void clockOut(String date, String time){
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reff = db.getReference().child("User").child(userid).child("Year").child(getYear()).child(getDate());

        HashMap<String, Object> map = new HashMap<>();
        map.put(getTime(),"Clocked out");

        reff.updateChildren(map);
    }

    //Once button is clicked:
    public void onButtonClick(View v){
        TextView timeText = (TextView)findViewById(R.id.clockinTime);
        CharSequence timeString = timeText.getText();
        String currTimeString = new SimpleDateFormat("hh:mm a").format(new Date());

        //Clocking in
        if(v.getId()== R.id.BclockIn){
            Button button = (Button)findViewById(R.id.BclockIn);
            button.setTextSize(18);
            button.setText("Successfully clocked in at "+currTimeString);

            //retrieving and adding to database
            clockIn();

            //Switches text back to original
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    button.setTextSize(24);
                    button.setText("Clock in");
                }
            }, 2000);


        }

        //Clocking out
        if(v.getId()== R.id.BclockOut){
            Button button = (Button)findViewById(R.id.BclockOut);
            button.setTextSize(18);
            button.setText("Successfully clocked out at "+currTimeString);

            //retrieving and adding to database
            clockOut(getDate(),currTimeString);

            //Switches text back to original
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    button.setTextSize(24);
                    button.setText("Clock out");
                }
            }, 2000);
        }

        //going back to home of user
        if(v.getId()==R.id.BsendBack){
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference userReff = db.getReference().child("User").child(userid).child("user_type");

            userReff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot){
                    String usertype = snapshot.getValue().toString();
                    //Waiter check
                    if(usertype.toLowerCase().equals("waiter")){

                        //Creating intent and transferring uid and usertype back to waiter home
                        Intent i = new Intent(clockinout.this, waiter_home.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("usertype","waiter");
                        bundle.putString("uid",userid);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                    //Busboy check
                    if(usertype.toLowerCase().equals("busboy")){


                        //Creating intent and transferring uid and usertype back to busboy home
                        Intent i = new Intent(clockinout.this, busboy_home.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("usertype","busboy");
                        bundle.putString("uid",userid);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                    //Chef check
                    if(usertype.toLowerCase().equals("chef")){


                        //Creating intent and transferring uid and usertype back to chef home
                        Intent i = new Intent(clockinout.this, chef_home.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("usertype","chef");
                        bundle.putString("uid",userid);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                    //Host check
                    if(usertype.toLowerCase().equals("host")){


                        //Creating intent and transferring uid and usertype back to chef home
                        Intent i = new Intent(clockinout.this, host_home.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("usertype","host");
                        bundle.putString("uid",userid);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("Error: " + error.getMessage());
                }
            });
        }
    }
}
