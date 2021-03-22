package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class menu_home_canorder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home_canorder);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.BConfirmOrder) {
            Toast.makeText(menu_home_canorder.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(menu_home_canorder.this, order_type.class);
            startActivity(i);
        }

    }
}
