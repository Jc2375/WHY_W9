package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class customer_home extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username=getIntent().getStringExtra("Username");
        if(username.equals("jason"))
        {
            setContentView(R.layout.customer_home);
            //kekw
        }
        else
        {
            Intent i=new Intent(customer_home.this, register.class );
            startActivity(i);
        }
    }
}