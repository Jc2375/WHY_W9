package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Database.Database;

public class order_type extends AppCompatActivity {
    public static String w=String.valueOf(System.currentTimeMillis());

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    Button btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;
    String uid; String total;

    String requestID;
    public static Float amount3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_type);
        Bundle bundle = getIntent().getExtras();

        uid= bundle.getString("uid");
        total=bundle.getString("total");
        //Firebase
        database = FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");
        cart = new Database(this).getCarts();


    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.bDineIn) {
            Toast.makeText(order_type.this, "Select Payment Type", Toast.LENGTH_SHORT).show();
            Request request = new Request (uid,total, cart,"DineIn");
            requests.child(w).setValue(request);
            new Database(getBaseContext()).cleanCart();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",false);
            bundle.putString("requestId",w);
            Intent i = new Intent(order_type.this, payment_type.class);
            i.putExtras(bundle);

            //Pain -------------------------------------------------
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Requests").child(w);
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String totalGet = dataSnapshot.child("total").getValue().toString();
                    amount3 = Float.parseFloat(totalGet);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            //-----------------------------------------------------------------------------

            startActivity(i);
        }
        if (v.getId() == R.id.bTakeout) {
            Toast.makeText(order_type.this, "Select Payment Type", Toast.LENGTH_SHORT).show();
            Request request = new Request (uid,total, cart,"Takeout");
            String w=String.valueOf(System.currentTimeMillis());
            requests.child(w).setValue(request);
            new Database(getBaseContext()).cleanCart();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",false);
            bundle.putString("requestId",w);
            Intent i = new Intent(order_type.this, payment_type.class);
            i.putExtras(bundle);

            //Pain -------------------------------------------------
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Requests").child(w);
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String totalGet = dataSnapshot.child("total").getValue().toString();
                    amount3 = Float.parseFloat(totalGet);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            //-----------------------------------------------------------------------------

            startActivity(i);
        }
        if (v.getId() == R.id.bDelivery) {
            Toast.makeText(order_type.this, "Input Address", Toast.LENGTH_SHORT).show();
            Request request = new Request (uid,total, cart,"Delivery");

            requests.child(w).setValue(request);
            new Database(getBaseContext()).cleanCart();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",true);
            bundle.putString("requestId",w);
            Intent i = new Intent(order_type.this, delivery_address.class);
            i.putExtras(bundle);

            //Pain -------------------------------------------------
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference("Requests").child(w);
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String totalGet = dataSnapshot.child("total").getValue().toString();
                    amount3 = Float.parseFloat(totalGet);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            //-----------------------------------------------------------------------------

            startActivity(i);
        }

    }
}
