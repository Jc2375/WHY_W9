package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class waiter_home extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_home);
    }
    public void onButtonClick(View v)
    {
        if(v.getId()== R.id.BMenuWaiter)
        {
            Intent i=new Intent(waiter_home.this, menu_home_canorder.class );
            startActivity(i);
        }
        if (v.getId() == R.id.bSignOut) {
            Toast.makeText(waiter_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(waiter_home.this, MainActivity.class);
            startActivity(i);
        }

    }


}
