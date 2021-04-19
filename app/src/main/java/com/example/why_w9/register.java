package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity
{
    Spinner type;
    EditText mEmail, mPassword, mUsertype;
    TextView text10;
    Button mRegisterBtn;
    FirebaseAuth fAuth;
    boolean Manager_check;

    String typeofuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Bundle bundle= getIntent().getExtras();
        Manager_check=bundle.getBoolean("Manager_Check");

        if(Manager_check == true){
            text10 = findViewById(R.id.textView10);
            text10.setVisibility(View.VISIBLE);
            type = findViewById(R.id.typeSpinner);
            type.setVisibility(View.VISIBLE);
            String[] employeeTypes = getResources().getStringArray(R.array.employeeTypes);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,employeeTypes);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            type.setAdapter(spinnerAdapter);

        }


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

        //will need to change this 4/18
        if(Manager_check == true)
        {
            //typeofuser = mUsertype.getText().toString().trim();
            typeofuser = type.getSelectedItem().toString();
        }


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
                      if(Manager_check == true)
                      {
                          Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                          String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                          User_Parameters userParameters;
                          DatabaseReference reff;
                          reff = FirebaseDatabase.getInstance().getReference().child("User");
                          userParameters = new User_Parameters();
                          userParameters.setUser_type(typeofuser);
                          userParameters.setEmail(email);
                          userParameters.setPassword(password);
                          reff.child(uid).setValue(userParameters);
                      }
                      else {
                          Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                          String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                          User_Parameters userParameters;
                          DatabaseReference reff;
                          reff = FirebaseDatabase.getInstance().getReference().child("User");
                          userParameters = new User_Parameters();
                          userParameters.setUser_type("Customer");
                          userParameters.setEmail(email);
                          userParameters.setPassword(password);
                          reff.child(uid).setValue(userParameters);

                          startActivity(new Intent(getApplicationContext(), customer_home.class));
                      }
                  }
                  else {
                        Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }
}
