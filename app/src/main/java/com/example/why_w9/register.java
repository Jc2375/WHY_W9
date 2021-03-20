package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class register extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void onButtonClick(View v)
    {

        if(v.getId()== R.id.BregisterBack)
        {
            Intent i=new Intent(register.this, MainActivity.class );
            startActivity(i);
        }

    }
}
