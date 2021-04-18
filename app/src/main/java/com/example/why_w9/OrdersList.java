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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrdersList extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference OrdersList;
    DatabaseReference image;
    RecyclerView orders_list;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;
    String uid;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);
        database= FirebaseDatabase.getInstance();
        OrdersList=database.getReference("Requests");
        //---------------
        Bundle bundle = getIntent().getExtras();
         user = bundle.getString("usertype");
        uid= bundle.getString("uid");


        //-----------------------
        //LOAD MENU
        orders_list= (RecyclerView)findViewById(R.id.orderlist_scroll);
        orders_list.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        orders_list.setLayoutManager(layoutManager);

        loadOrders();
    }
    private void loadOrders()
    {
        Query query=FirebaseDatabase.getInstance().getReference().child("Requests");
        FirebaseRecyclerOptions<Request> options = new FirebaseRecyclerOptions.Builder<Request>().setQuery(query,Request.class).build();

        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(options) {
            @Override
            public OrderViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_item,parent,false);
                return new OrderViewHolder(view);
            }
            @Override
            protected void onBindViewHolder( OrderViewHolder viewHolder, int position, Request model)
            {
                Toast.makeText(OrdersList.this, ""+model.getTotal(), Toast.LENGTH_SHORT).show();
                viewHolder.txtOrderName.setText(model.getUid());
                final Request clickItem=model;
                viewHolder.setItemClickListener(new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick)
                    {
                        Intent orderList= new Intent(OrdersList.this,order_detail.class);
                        orderList.putExtra("requestId", adapter.getRef(position).getKey());
                        orderList.putExtra("uid", uid);
                        orderList.putExtra("usertype", user);
                        startActivity(orderList);
                    }
                });
            }



        };

        orders_list.setAdapter(adapter);
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
        OrdersList=database.getReference("Requests");

        /*if (v.getId() == R.id.BConfirmOrder)
        {
            Toast.makeText(menu_home_canorder.this, "Select Order Type", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(menu_home_canorder.this, order_type.class);
            startActivity(i);
        }*/

    }
}
//29:56