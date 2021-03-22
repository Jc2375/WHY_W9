package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth; //logout part 1
import com.google.firebase.auth.FirebaseUser; //logout part 2

import java.util.Random;

public class customer_home extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth; //logout part 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_home);

        mFirebaseAuth = FirebaseAuth.getInstance(); //logout part 4
    }

    //Logout part 5
    protected void onStart()
    {
        super.onStart();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            startActivity(new Intent(this, login.class));
        }
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            mFirebaseAuth.signOut();
            Toast.makeText(customer_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(customer_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.bModifyAccount) {
            Intent i = new Intent(customer_home.this, modify_account.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BCustomerReservation) {
            Intent i = new Intent(customer_home.this, customer_reservation.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BMenuCustomer) {
            Intent i = new Intent(customer_home.this, menu_home_canorder.class);
            startActivity(i);
        }
    }
    //Up to here

}


