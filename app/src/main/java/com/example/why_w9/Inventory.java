package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory extends AppCompatActivity {
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView inventory;
    List<String> ingredients;
    List <String> info;
    HashMap<String,List<String>> ingredientsInfo;
    FirebaseDatabase root;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventory = findViewById(R.id.inventoryExpList);
        ingredients = new ArrayList<>();
        ingredients.add("Ingredients");
        info = new ArrayList<>();
        ingredientsInfo = new HashMap<>();

        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Inventory");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    String ing =snapshot1.getKey().toString();
                    String quantity = snapshot1.getValue().toString();
                    String inf = ing + ":" + quantity;
                    info.add(inf);
                }
                makeExpList();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void makeExpList() {
        ingredientsInfo.put(ingredients.get(0),info);
        listViewAdapter = new ExpandableListViewAdapter(this,ingredients,ingredientsInfo);
        inventory.setAdapter(listViewAdapter);
    }
}