package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //mEmail = findViewById(R.id.mEmail);
        //mPassword = findViewById(R.id.mPassword);
        //mLoginBtn = findViewById(R.id.mRegisterBtn);
        //fAuth = FirebaseAuth.getInstance();

    }
    public void onButtonClick(View v)
    {
        switch(v.getId())
        {
            case R.id.BLoginAttempt:
            {
                EditText a=(EditText)findViewById(R.id.inputEmail);
                 String str= a.getText().toString();
                 if(str.equals("manager@gmail.com"))
                 {
                     Intent i=new Intent(login.this, customer_home.class );
                     i.putExtra("Username",str);

                 }
                Intent i=new Intent(login.this, customer_home.class );
                i.putExtra("Username",str);
                startActivity(i);
            }

        }
    }

}