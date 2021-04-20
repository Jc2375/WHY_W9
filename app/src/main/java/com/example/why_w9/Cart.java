package com.example.why_w9;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.why_w9.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Database.Database;

//import com.google.android.gms.common.internal.service.Common;
//import com.google.firebase.*;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;

    Button btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;
    String uid;

    public static int totalCost = R.id.total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle bundle = getIntent().getExtras();

        uid= bundle.getString("uid");
        //Firebase
        database = FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);

        btnPlace = (Button)findViewById(R.id.BPlaceOrder); //previously FButton instead of Button

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.size() > 0){
                    Bundle bundle=new Bundle();
                    bundle.putString("uid",uid);
                    bundle.putString("total",txtTotalPrice.getText().toString());
                    Intent i = new Intent(Cart.this, order_type.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Cart.this, "Your Cart is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loadListFood();
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address: ");

        final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //add edited text to the alert dialog
        alertDialog.setIcon(R.drawable.why9logo);

        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
                //Create new Request
                Request request = new Request (
                        //UNFINISHED
                        //need to send this information to the request class to firebase

                        /*
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtaddress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart
                        */
                );
                //Submit to Firebase
                //using System.CurrentMilli to key
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                //Delete cart
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Your order has been submitted.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }

    private void loadListFood() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        //Calculate total price
        //fixed price problem here and in CartAdapter.java
        //previous lines commented out
        //int total = 0;
        float total = 0;
        for(Order order:cart)
            //total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
            total+=(Float.parseFloat(order.getPrice()))*(Float.parseFloat(order.getQuantity()));
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        //txtTotalPrice.setText(fmt.format(total));
        txtTotalPrice.setText(String.valueOf(total));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(Common.DELETE))
            deleteCart(item.getOrder());
        return true;
    }

    private void deleteCart(int position) {
        //We will remove item at List<Order> by position
        cart.remove(position);
        //After that, we will delete all old data from SQLite
        new Database(this).cleanCart();
        //And final , we will update new data from List<Order> to SQLite
        for(Order item:cart)
            new Database(this).addToCart(item);
        //Refresh
        loadListFood();
    }
}