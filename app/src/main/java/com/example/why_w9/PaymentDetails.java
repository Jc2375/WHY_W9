package com.example.why_w9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView txtId,txtAmount,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtId= (TextView)findViewById(R.id.txtId);
        txtAmount = (TextView)findViewById(R.id.txtAmount);
        txtStatus = (TextView)findViewById(R.id.txtStatus);

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        FirebaseDatabase.getInstance().getReference().child("Requests").child(order_type.w).child("paid").setValue("1");

    }

    private void showDetails(JSONObject response, String paymentAmount) {
        try {
            txtId.setText(response.getString("id"));
            txtStatus.setText(response.getString("state"));
            txtAmount.setText(response.getString("$"+paymentAmount));




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.bHome: {
                Toast.makeText(PaymentDetails.this, "Your Order Has Been Placed Successfully!", Toast.LENGTH_SHORT).show();

                //Toast.makeText(PaymentDetails.this, "Your app will now close automatically.", Toast.LENGTH_SHORT).show();
                //Thread.sleep(5000);
                //finishAffinity();
                //setContentView(R.layout.activity_main);
            }
        }
    }
}