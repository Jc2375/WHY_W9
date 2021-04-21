package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class customer_home extends AppCompatActivity
{

    public static String ID= FirebaseAuth.getInstance().getCurrentUser().getUid();

    private FirebaseAuth mFirebaseAuth; //logout part 3
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_home);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
        mFirebaseAuth = FirebaseAuth.getInstance(); //logout part 4
    }

    //Logout part 5
    protected void onStart()
    {
        super.onStart();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            startActivity(new Intent(this, login.class));
        }
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            mFirebaseAuth.signOut();
            Toast.makeText(customer_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(customer_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.bModifyAccount) {
            Intent i = new Intent(customer_home.this, modify_account.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BCustomerReservation) {
            Intent i = new Intent(customer_home.this, customer_reservation.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BMenuCustomer) {
            Toast.makeText(customer_home.this, "View Menu", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("usertype","customer");
            bundle.putString("uid",uid);
            Intent i = new Intent(customer_home.this, menu_home_canorder.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
    //Up to here

}


