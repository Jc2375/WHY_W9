package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class order_detail extends AppCompatActivity {
    TextView test_price;

    String requestId;
    FirebaseDatabase database;
    DatabaseReference ordersList;
    DatabaseReference completeOrders;
    Request currentRequest;
    List<Order> currentOrder;
    RecyclerView orders_list;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Order,OrderDetailViewHolder> adapter;
    DataSnapshot dataSnapshot;
    Button b;
    String uid;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);
        database= FirebaseDatabase.getInstance();
        ordersList= database.getReference("Requests");
        orders_list= (RecyclerView)findViewById(R.id.orders_items);
        orders_list.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        orders_list.setLayoutManager(layoutManager);
        completeOrders=database.getReference("completeOrders");
        Bundle bundle = getIntent().getExtras();
         user = bundle.getString("usertype");
        uid= bundle.getString("uid");
        if(user.equals("chef")||user.equals("manager"))
        {
            b = findViewById(R.id.BConfirmCompletion);
            b.setVisibility(View.VISIBLE);
        }
        if(getIntent() !=null)
        {
            requestId=bundle.getString("requestId");
            if(!requestId.isEmpty())
            {
                getDetailOrder(requestId);
            }
        }
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.BConfirmCompletion) {
            Toast.makeText(order_detail.this, "Order has been marked as Completed", Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference("Requests").child(requestId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    currentRequest=snapshot.getValue(Request.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            completeOrders.child(String.valueOf(System.currentTimeMillis())).setValue(currentRequest);
            FirebaseDatabase.getInstance().getReference().child("Requests").child(requestId).removeValue();
            Bundle bundle=new Bundle();
            bundle.putString("uid",uid);
            Intent i = new Intent(order_detail.this, chef_home.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
    private void getDetailOrder(String requestId) {
        Query query=FirebaseDatabase.getInstance().getReference().child("Requests").child(requestId).child("foods");
        FirebaseRecyclerOptions<Order> options = new FirebaseRecyclerOptions.Builder<Order>().setQuery(query,Order.class).build();

        adapter=new FirebaseRecyclerAdapter<Order, OrderDetailViewHolder>(options) {
            @Override
            public OrderDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_detail_item,parent,false);
                return new OrderDetailViewHolder(view);
            }
            @Override
            protected void onBindViewHolder( OrderDetailViewHolder viewHolder, int position, Order model)
            {
                Toast.makeText(order_detail.this, ""+model.getProductName(), Toast.LENGTH_SHORT).show();
                viewHolder.txtOrderItem1.setText("Item: "+model.getProductName());
                viewHolder.txtOrderItem2.setText("          Quantity: "+model.getQuantity());
                final Order clickItem=model;
                /*viewHolder.setItemClickListener(new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick)
                    {
                        Intent orderList= new Intent(order_detail.this,OrdersList.class);
                        orderList.putExtra("requestId", adapter.getRef(position).getKey());
                        startActivity(orderList);
                    }
                });*/
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


}
