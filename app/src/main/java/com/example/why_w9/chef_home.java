package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class chef_home extends AppCompatActivity {
    Button b;
    Button c;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_home);
        uid=getIntent().getExtras().getString("uid");
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            Toast.makeText(chef_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(chef_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BMenuChef) {
            Toast.makeText(chef_home.this, "View Menu", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("usertype","chef");
            bundle.putString("uid",uid);
            Intent i = new Intent(chef_home.this, menu_home_canorder.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if(v.getId() == R.id.BChefClocking){
            //Get uid from database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            //Creating bundle to pass along info of uid and usertype to clock in page
            Bundle bundle = new Bundle();
            bundle.putString("usertype","chef");
            bundle.putString("uid",uid);
            Intent i = new Intent(chef_home.this, clockinout.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if (v.getId() == R.id.BOrdersListChef) {
            Toast.makeText(chef_home.this, "View Orders", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("usertype","chef");
            bundle.putString("uid",uid);
            Intent i = new Intent(chef_home.this, OrdersList.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }

}
