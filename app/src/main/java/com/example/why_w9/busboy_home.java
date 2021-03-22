package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
            Intent i = new Intent(busboy_home.this, busboy_tablestatus.class);
            startActivity(i);
        }
    }
}
