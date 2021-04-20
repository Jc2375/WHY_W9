package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class host_home extends AppCompatActivity {
    public static String ID= FirebaseAuth.getInstance().getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_home);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            Toast.makeText(host_home.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(host_home.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.BMenuHost) {
            Toast.makeText(host_home.this, "View Menu", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("usertype","host");
            Intent i = new Intent(host_home.this, menu_home_canorder.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if(v.getId() == R.id.BHostClocking){
            //Get uid from database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            //Creating bundle to pass along info of uid and usertype to clock in page
            Bundle bundle = new Bundle();
            bundle.putString("usertype","host");
            bundle.putString("uid",uid);
            Intent i = new Intent(host_home.this, clockinout.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if(v.getId() == R.id.BOrdersListHost){
            //Get uid from database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            //Creating bundle to pass along info of uid and usertype to clock in page
            Bundle bundle = new Bundle();
            bundle.putString("usertype","host");
            bundle.putString("uid",uid);
            Intent i = new Intent(host_home.this, OrdersList.class);
            i.putExtras(bundle);
            startActivity(i);
        }
        if(v.getId() == R.id.BHostTakeoutOrders){
            //Get uid from database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            //Creating bundle to pass along info of uid and usertype to clock in page
            Bundle bundle = new Bundle();
            bundle.putString("usertype","host");
            bundle.putString("uid",uid);
            Intent i = new Intent(host_home.this,finishedCooking.class);
            i.putExtras(bundle);
            startActivity(i);
        }

        if (v.getId() == R.id.BTableStatusHost)
        {
            Intent i = new Intent(host_home.this, table_status.class);
            startActivity(i);
        }
        if (v.getId() == R.id.myScheduleHost)
        {
            Intent i = new Intent(this,MySchedule.class);
            i.putExtra("type","Hosts");
            i.putExtra("id","Host1");
            startActivity(i);
        }
    }
}
