package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class order_type extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_type);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.bDineIn) {
            Toast.makeText(order_type.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",false);
            Intent i = new Intent(order_type.this, payment_type.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (v.getId() == R.id.bTakeout) {
            Toast.makeText(order_type.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",false);
            Intent i = new Intent(order_type.this, payment_type.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (v.getId() == R.id.bDelivery) {
            Toast.makeText(order_type.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",true);
            Intent i = new Intent(order_type.this, payment_type.class);
            i.putExtras(bundle);
            startActivity(i);
        }

    }
}
