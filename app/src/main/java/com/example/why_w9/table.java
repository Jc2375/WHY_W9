package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class table extends AppCompatActivity
{
    String table_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);


        DatabaseReference reff;
        reff = FirebaseDatabase.getInstance().getReference("Table").child(String.valueOf(global.Global.TN));

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Status = dataSnapshot.child("Status").getValue().toString();
                String TableNumber = reff.toString();

                ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                ((TextView) findViewById(R.id.tableNumber)).setText(TableNumber);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }




    public void onButtonClick(View v) {

        if (v.getId() == R.id.Available) {
            FirebaseDatabase.getInstance().getReference("Table").child(String.valueOf(global.Global.TN)).child("Status").setValue("Available");
            ((TextView) findViewById(R.id.tableCurrentStatus)).setText("Available");
            //Toast.makeText(table.this, "Table Status Has Been Set To: Available!", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.Taken) {
            FirebaseDatabase.getInstance().getReference("Table").child(String.valueOf(global.Global.TN)).child("Status").setValue("Taken");
            ((TextView) findViewById(R.id.tableCurrentStatus)).setText("Taken");
            //Toast.makeText(table.this, "Table Status Has Been Set To: Available!", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.Reserved) {
            FirebaseDatabase.getInstance().getReference("Table").child(String.valueOf(global.Global.TN)).child("Status").setValue("Reserved");
            ((TextView) findViewById(R.id.tableCurrentStatus)).setText("Reserved");
            //Toast.makeText(table.this, "Table Status Has Been Set To: Available!", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.Dirty) {
            FirebaseDatabase.getInstance().getReference("Table").child(String.valueOf(global.Global.TN)).child("Status").setValue("Dirty");
            ((TextView) findViewById(R.id.tableCurrentStatus)).setText("Dirty");
            //Toast.makeText(table.this, "Table Status Has Been Set To: Available!", Toast.LENGTH_SHORT).show();
        }
    }

}
