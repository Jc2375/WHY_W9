package com.example.why_w9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class payment_type extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        boolean deliv=bundle.getBoolean("Delivery");
        if(deliv==true)
        {
            setContentView(R.layout.payment_type);
            b = findViewById(R.id.BCashPayment);
            b.setVisibility(View.INVISIBLE);

        }
        else
        {
            setContentView(R.layout.payment_type);
        }
    }
}

