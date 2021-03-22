package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delivery_address extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_address);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.BDeliveryAddress) {
            Toast.makeText(delivery_address.this, "Select Payment Type", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",true);
            Intent i = new Intent(delivery_address.this, payment_type.class);
            i.putExtras(bundle);
            startActivity(i);
        }

    }
}
