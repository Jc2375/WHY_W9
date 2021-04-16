package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class waiter_home extends AppCompatActivity
{
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_home);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");



    }
    public void onButtonClick(View v)
    {
        if(v.getId()== R.id.BMenuWaiter)
        {
            Toast.makeText(waiter_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("usertype","waiter");
            bundle.putString("uid",uid);
            Intent i = new Intent(waiter_home.this, menu_home_canorder.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (v.getId() == R.id.bSignOut)
        {
            Intent i = new Intent(waiter_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BWaiterClocking){
            Bundle bundle = new Bundle();
            bundle.putString("usertype","waiter");
            bundle.putString("uid",uid);
            Intent i = new Intent(waiter_home.this, clockinout.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (v.getId() == R.id.BTableStatusWaiter)
        {
            Intent i = new Intent(waiter_home.this, table_status.class);
            startActivity(i);
        }


    }


}
