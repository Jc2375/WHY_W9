package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class delivery_address extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_address);


    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.BDeliveryAddress) {
            //String email = FirebaseAuth.getInstance().getUid();

            Customers customers;
            EditText Name, Address, City, State, Zipcode;
            DatabaseReference reff;
            Name = (EditText) findViewById(R.id.editTextTextPersonName);
            Address = (EditText) findViewById(R.id.editTextTextPersonName2);
            City = (EditText) findViewById(R.id.editTextTextPersonName3);
            State = (EditText) findViewById(R.id.editTextTextPersonName4);
            Zipcode = (EditText) findViewById(R.id.editTextTextPersonName5);
            reff = FirebaseDatabase.getInstance().getReference().child("Customers");

            customers = new Customers();
            customers.setName(Name.getText().toString().trim());
            customers.setAddress(Address.getText().toString().trim());
            customers.setCity(City.getText().toString().trim());
            customers.setState(State.getText().toString().trim());
            customers.setZipcode(Zipcode.getText().toString().trim());
            reff.push().setValue(customers);

            Toast.makeText(delivery_address.this, "Select Payment Type", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",true);
            Intent i = new Intent(delivery_address.this, payment_type.class);
            i.putExtras(bundle);
            startActivity(i);
        }

    }
}
