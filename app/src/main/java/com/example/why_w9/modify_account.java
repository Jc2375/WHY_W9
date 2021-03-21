package com.example.why_w9;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.why_w9.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth; //logout part 1
import com.google.firebase.auth.FirebaseUser; //logout part 2

import java.util.Random;

public class modify_account extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //logout part 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_account);

        mFirebaseAuth = FirebaseAuth.getInstance(); //logout part 4


    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.bSignOut) {
            mFirebaseAuth.signOut();
            Toast.makeText(modify_account.this, "User Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(modify_account.this, MainActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.bDeleteAccount) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(modify_account.this, "User Account Deleted", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(modify_account.this, MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
        }
    }
}