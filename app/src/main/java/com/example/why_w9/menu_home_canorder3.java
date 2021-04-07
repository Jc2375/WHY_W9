package com.example.why_w9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menu_home_canorder3 extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<food_item> food_items;
    private RecyclerAdapter recyclerAdapter;
    private Context mConxtext;

    Button b, c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home_canorder3);
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("usertype");
        if (user.equals("chef")) {
            b = findViewById(R.id.BConfirmOrder2);
            b.setVisibility(View.GONE);
        }
        if (user.equals("waiter")) {
            b = findViewById(R.id.BConfirmOrder2);
            b.setVisibility(View.VISIBLE);
        }
        if (user.equals("host")) {
            b = findViewById(R.id.BConfirmOrder2);
            b.setVisibility(View.GONE);
        }
        if (user.equals("customer")) {
            b = findViewById(R.id.BConfirmOrder2);
            b.setVisibility(View.VISIBLE);
        }
        recyclerView= findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        myRef= FirebaseDatabase.getInstance().getReference();
        food_items=new ArrayList<>();
        ClearAll();
        GetDataFromFirebase();

    }
    /*public void onClick(View v,int position,boolean isLongCLick) {

        Intent i = new Intent(menu_home_canorder3.this, food_detail.class);
        i.putExtra("FoodId",recyclerAdapter.getReference(position).getKey()) ;
        startActivity(i);
    }*/
    private void GetDataFromFirebase()
    {
        Query query= myRef.child("Menu");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    food_item items = new food_item();
                    items.setImageUrl(snapshot.child("Image").getValue().toString());
                    items.setName(snapshot.child("Name").getValue().toString());
                    food_items.add(items);
                }
                recyclerAdapter =new RecyclerAdapter(getApplicationContext(),food_items);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ClearAll()
    {
        if(food_items !=null)
        {
            food_items.clear();
            if(recyclerAdapter !=null)
            {
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        food_items=new ArrayList<>();
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.BConfirmOrder2)
        {
            Toast.makeText(menu_home_canorder3.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(menu_home_canorder3.this, order_type.class);
            startActivity(i);
        }if (v.getId() == R.id.BMenuBack)
        {
            Bundle bundle = getIntent().getExtras();
            String user = bundle.getString("usertype");
            if (user.equals("chef")) {
                Toast.makeText(menu_home_canorder3.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(menu_home_canorder3.this, chef_home.class);
                startActivity(i);
            }
            if (user.equals("waiter")) {
                Toast.makeText(menu_home_canorder3.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(menu_home_canorder3.this, waiter_home.class);
                startActivity(i);
            }
            if (user.equals("host")) {
                Toast.makeText(menu_home_canorder3.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(menu_home_canorder3.this, host_home.class);
                startActivity(i);
            }
            if (user.equals("customer")) {
                Toast.makeText(menu_home_canorder3.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(menu_home_canorder3.this, customer_home.class);
                startActivity(i);
            }
        }

       /* CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked())
        {


        }*/
    }
}
