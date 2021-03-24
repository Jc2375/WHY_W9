package com.example.why_w9;

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
            Toast.makeText(waiter_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("usertype","waiter");
            Intent i = new Intent(waiter_home.this, menu_home_canorder3.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (v.getId() == R.id.bSignOut)
        {
            Intent i = new Intent(waiter_home.this, MainActivity.class);
            startActivity(i);
        }

    }


}
