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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        if(global.Global.TN == 1)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("1");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();

                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText("1");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
       /* else if(global.Global.TN == 2)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("2");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 3)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("3");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 4)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("4");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 5)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("5");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 6)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("6");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 7)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("7");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 8)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("8");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 9)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("9");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 10)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("10");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 11)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("11");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 12)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("12");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 13)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("13");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 14)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("14");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }
        else if(global.Global.TN == 15)
        {
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Table").child("15");

            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Status = dataSnapshot.child("Status").getValue().toString();
                    ((TextView) findViewById(R.id.tableCurrentStatus)).setText(Status);
                    ((TextView) findViewById(R.id.tableNumber)).setText(global.Global.TN);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });



        }*/

    }

}
