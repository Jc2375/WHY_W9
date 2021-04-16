package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class busboy_home extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busboy_home);
    }
    public void onButtonClick(View v) {

        if (v.getId() == R.id.BBusboySignout) {
            Intent i = new Intent(busboy_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BBusboyTableStatus) {
            Intent i = new Intent(busboy_home.this, table_status.class);
            startActivity(i);
        }
        if(v.getId() == R.id.BbusboyClockIn){
            //Get uid from database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            //Creating bundle to pass along info of uid and usertype to clock in page
            Bundle bundle = new Bundle();
            bundle.putString("usertype","busboy");
            bundle.putString("uid",uid);
            Intent i = new Intent(busboy_home.this, clockinout.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
