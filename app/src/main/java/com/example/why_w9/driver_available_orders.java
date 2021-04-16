package com.example.why_w9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.why_w9.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class driver_available_orders extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_available_orders);

        /*CONTINUE WORKING ON THIS

        Customers customers;
        DatabaseReference reff;
        reff = FirebaseDatabase.getInstance().getReference().child("Customers");
        customers = new Customers();
        String address = reff.child("Zb30i2DQgNcuLruT1XALx8Kj1Fl2").child("address").toString().trim();

        Customers.setImageUrl(snapshot.child("Image").getValue().toString());

        Button bDeliveryAddress1 = (Button)findViewById(R.id.bDeliveryAddress1);
        bDeliveryAddress1.setText(address);

        TO HERE

    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.bDeliveryAddress1) {

        }
    */
    }


}