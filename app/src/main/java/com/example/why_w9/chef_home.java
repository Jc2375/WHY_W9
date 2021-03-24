package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class chef_home extends AppCompatActivity {
    Button b;
    Button c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_home);

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
            Intent i = new Intent(chef_home.this, menu_home_canorder3.class);
            i.putExtras(bundle);
            startActivity(i);
        }
    }

}
