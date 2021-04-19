package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class manager_home extends AppCompatActivity
{
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_home);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            Toast.makeText(manager_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(manager_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BManageEmp) {
            Intent i = new Intent(manager_home.this, ManageEmployeeOptions.class);
            startActivity(i);

        }
        if(v.getId()== R.id.BManageOrderList)
        {
            Bundle bundle=new Bundle();
            bundle.putString("usertype","manager");
            bundle.putString("uid",uid);
            Intent i = new Intent(manager_home.this, OrdersList.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
    public void toAnalyticsOptions(View v){
        Intent intent = new Intent(this,AnalyticsOptions.class);
        startActivity(intent);
    }



}
