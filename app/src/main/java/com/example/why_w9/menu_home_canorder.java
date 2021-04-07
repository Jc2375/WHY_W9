package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class menu_home_canorder extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference category;
    DatabaseReference image;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home_canorder);
        database= FirebaseDatabase.getInstance();
        category=database.getReference("Menu");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            Intent cartIntent = new Intent(menu_home_canorder.this,Cart.class);
            startActivity(cartIntent);
        });

        //LOAD MENU
        recycler_menu= (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("usertype");
        loadMenu();
    }
    private void loadMenu()
    {
        Query query=FirebaseDatabase.getInstance().getReference().child("Menu");
        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(query, Category.class).build();

        adapter=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
            @Override
            public MenuViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_item,parent,false);
                return new MenuViewHolder(view);
            }
            @Override
            protected void onBindViewHolder( MenuViewHolder viewHolder, int position,  Category model)
            {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.imageView);

                final Category clickItem=model;
                viewHolder.setItemClickListener(new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick)
                    {
                        Intent foodList= new Intent(menu_home_canorder.this,FoodList.class);
                        Toast.makeText(menu_home_canorder.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
                        foodList.putExtra("MenuId", adapter.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });
            }



        };

        recycler_menu.setAdapter(adapter);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



    public void onButtonClick(View v) {
        database= FirebaseDatabase.getInstance();
        category=database.getReference("Menu");

        /*if (v.getId() == R.id.BConfirmOrder)
        {
            Toast.makeText(menu_home_canorder.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(menu_home_canorder.this, order_type.class);
            startActivity(i);
        }*/

    }
}
//29:56