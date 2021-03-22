package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class busboy_tablestatus extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busboy_tablestatus);
    }
    public void onButtonClick(View v) {

        if (v.getId() == R.id.BReturn_BusboyHome) {
            Intent i = new Intent(busboy_tablestatus.this, busboy_home.class);
            startActivity(i);
        }
    }
}
