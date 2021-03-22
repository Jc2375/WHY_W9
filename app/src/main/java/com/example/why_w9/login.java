package com.example.why_w9;

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

public class login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mEmail = findViewById(R.id.inputEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mLoginBtn = findViewById(R.id.BLoginAttempt);
        fAuth = FirebaseAuth.getInstance();
    }

        public void onButtonClick(View v)
        {
            switch (v.getId())
            {
                case R.id.BLoginAttempt:
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
                        if(email.equals("manager@gmail.com")&&password.equals("managerpassword"))
                        {
                            Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), manager_home.class));
                            return;
                        }
                        if(email.equals("driver@gmail.com")&&password.equals("driverpassword"))
                        {
                            Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), driver_home.class));
                            return;
                        }
                        if(email.equals("waiter@gmail.com")&&password.equals("waiterpassword"))
                        {
                            Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), waiter_home.class));
                            return;
                        }
                        if(email.equals("chef@gmail.com")&&password.equals("chefpassword"))
                        {
                            Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), chef_home.class));
                            return;
                        }
                        if(email.equals("host@gmail.com")&&password.equals("hostpassword"))
                        {
                            Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), host_home.class));
                            return;
                        }
                        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), customer_home.class));
                                }
                                else {
                                    Toast.makeText(login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
            }


        }

    }



