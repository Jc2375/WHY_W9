package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class finishedCooking extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference OrdersList;
    DatabaseReference image;
    RecyclerView orders_to_deliver;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request,OrderToDeliverViewHolder> adapter;
    String uid;
    String user;
    int paid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_to_deliver);
        database= FirebaseDatabase.getInstance();
        OrdersList=database.getReference("Requests");
        //---------------
        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("usertype");
        uid= bundle.getString("uid");


        //-----------------------
        //LOAD MENU
        orders_to_deliver= (RecyclerView)findViewById(R.id.ordersToDeliverScroll);
        orders_to_deliver.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        orders_to_deliver.setLayoutManager(layoutManager);

        loadOrders();
    }
    private void loadOrders()
    {
        Query query=FirebaseDatabase.getInstance().getReference().child("completeOrders");
        FirebaseRecyclerOptions<Request> options = new FirebaseRecyclerOptions.Builder<Request>().setQuery(query,Request.class).build();

        adapter=new FirebaseRecyclerAdapter<Request, OrderToDeliverViewHolder>(options) {
            @Override
            public OrderToDeliverViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_item_todeliver,parent,false);
                return new OrderToDeliverViewHolder(view);
            }
            @Override
            protected void onBindViewHolder( OrderToDeliverViewHolder viewHolder, int position, Request model)
            {
                if(model.getOrderType().equals("DineIn")&&user.equals("waiter"))
                {
                    viewHolder.txtOrderName.setText(model.getUid());
                    final Request clickItem=model;
                    viewHolder.setItemClickListener(new ItemClickListener()
                    {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick)
                        {
                            Intent orderList= new Intent(finishedCooking.this,finishedCookingDetails.class);
                            orderList.putExtra("requestId", adapter.getRef(position).getKey());
                            orderList.putExtra("uid", uid);
                            orderList.putExtra("usertype", user);
                            startActivity(orderList);
                        }
                    });
                }
                else if(model.getOrderType().equals("Takeout")&&user.equals("host"))
                {
                    viewHolder.txtOrderName.setText(model.getUid());
                    final Request clickItem=model;
                    viewHolder.setItemClickListener(new ItemClickListener()
                    {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick)
                        {
                            Intent orderList= new Intent(finishedCooking.this,finishedCookingDetails.class);
                            orderList.putExtra("requestId", adapter.getRef(position).getKey());
                            orderList.putExtra("uid", uid);
                            orderList.putExtra("usertype", user);
                            startActivity(orderList);
                        }
                    });
                }
                else if(model.getOrderType().equals("Delivery")&&user.equals("driver"))
                {
                    viewHolder.txtOrderName.setText(model.getUid());
                    paid=model.getPaid();
                    final Request clickItem=model;
                    viewHolder.setItemClickListener(new ItemClickListener()
                    {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick)
                        {
                            Intent orderList= new Intent(finishedCooking.this,finishedCookingDetails.class);
                            orderList.putExtra("requestId", adapter.getRef(position).getKey());
                            orderList.putExtra("uid", uid);
                            orderList.putExtra("usertype", user);
                            orderList.putExtra("paid", paid);
                            startActivity(orderList);
                        }
                    });
                }
                else
                {
                    viewHolder.txtOrderName.setVisibility(View.INVISIBLE);
                }
            }



        };

        orders_to_deliver.setAdapter(adapter);
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