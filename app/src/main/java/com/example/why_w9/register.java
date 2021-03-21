package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity
{
    EditText mEmail, mPassword;
    Button mRegisterBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById(R.id.mPassword);
        mRegisterBtn = findViewById(R.id.mRegisterBtn);
        fAuth = FirebaseAuth.getInstance();

        /*if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

    }
    public void onButtonClick(View v)
    {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            mEmail.setError("Email is Required");
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            mPassword.setError("Password is Required");
            return;
        }
        if(password.length() < 6)
        {
            mPassword.setError("Password must be greater than 5 characters!");
            return;
        }

          fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful())
                  {
                      Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(getApplicationContext(), customer_home.class));
                  }
                  else {
                        Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }
}
