package com.example.why_w9;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClick(View v)
    {
        if(v.getId()== R.id.Blogin)
        {
            Intent i=new Intent(MainActivity.this, login.class );
            startActivity(i);
        }
        if(v.getId()== R.id.Bregister)
        {

        }
    }
    public void onButtonClick2(View v)
    {
        if(v.getId()== R.id.BRegister)
        {
            Intent i=new Intent(MainActivity.this, register.class );
            startActivity(i);
        }
    }


}

