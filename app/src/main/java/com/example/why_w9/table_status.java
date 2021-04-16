package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class table_status extends AppCompatActivity {
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_status);
    }

    public void onButtonClick(View v)
    {
        Intent i = new Intent(table_status.this, table.class);
        startActivity(i);
    }
}