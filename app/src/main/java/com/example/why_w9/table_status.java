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

        if(v.getId() == R.id.table1status){
            global.Global.TN = 1;
        }
        /*else if(v.getId() == R.id.table2status){
            global.Global.TN = 2;
        }
        else if(v.getId() == R.id.table3status){
            global.Global.TN = 3;
        }
        else if(v.getId() == R.id.table4status){
            global.Global.TN = 4;
        }
        else if(v.getId() == R.id.table5status){
            global.Global.TN = 5;
        }
        else if(v.getId() == R.id.table6status){
            global.Global.TN = 6;
        }
        else if(v.getId() == R.id.table7status){
            global.Global.TN = 7;
        }
        else if(v.getId() == R.id.table8status){
            global.Global.TN = 8;
        }
        else if(v.getId() == R.id.table9status){
            global.Global.TN = 9;
        }
        else if(v.getId() == R.id.table10status){
            global.Global.TN = 10;
        }
        else if(v.getId() == R.id.table11status){
            global.Global.TN = 11;
        }
        else if(v.getId() == R.id.table12status){
            global.Global.TN = 12;
        }
        else if(v.getId() == R.id.table13status){
            global.Global.TN = 13;
        }
        else if(v.getId() == R.id.table14status){
            global.Global.TN = 14;
        }
        else if(v.getId() == R.id.table15status){
            global.Global.TN = 15;
        }*/











        startActivity(i);
    }
}