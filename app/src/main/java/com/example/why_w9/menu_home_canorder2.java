package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class menu_home_canorder2 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference menu;
    DatabaseReference image;
    ImageView rImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home_canorder2);
        rImage = findViewById(R.id.rImage);
        database= FirebaseDatabase.getInstance();
        menu=database.getReference();
        image= menu.child("01").child("Image"); //needs to be in onclickbton if statement
        menu.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // getting a DataSnapshot for the location at the specified
                // relative path and getting in the link variable
                String link = dataSnapshot.getValue(String.class);

                // loading that data into rImage
                // variable which is ImageView
                Picasso.get().load(link).into(rImage);
            }

            // this will called when any problem
            // occurs in getting data
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // we are showing that error message in toast
                Toast.makeText(menu_home_canorder2.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onButtonClick(View v) {


        if (v.getId() == R.id.BConfirmOrder)
        {
            Toast.makeText(menu_home_canorder2.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(menu_home_canorder2.this, order_type.class);
            startActivity(i);
        }

       /* CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked())
        {


        }*/
    }
}
