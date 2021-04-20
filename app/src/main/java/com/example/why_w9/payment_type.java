package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.why_w9.Config.Config;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;


import org.json.JSONException;
import java.math.BigDecimal;
import java.util.List;


public class payment_type extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    Button BPaypalPayment;
    EditText edtAmout;

    public String amount = "";

    String requestId;
    public float amount2;
    public float amount3;
    public int reload = 0;

    @Override
    protected void onDestroy()
    {
                stopService(new Intent(this,PayPalService.class));
                super.onDestroy();
    }

    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getIntent().getExtras();
        boolean deliv=bundle.getBoolean("Delivery");


        if(deliv==true)
        {
            setContentView(R.layout.payment_type);
            b = findViewById(R.id.BCashPayment);
            b.setVisibility(View.INVISIBLE);

        }
        else
        {
            setContentView(R.layout.payment_type);
        }

        Intent intent = new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);



        BPaypalPayment = (Button)findViewById(R.id.BPaypalPayment);
        edtAmout = (EditText)findViewById(R.id.edtAmount);




    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.BPaypalPayment: {

                    amount2 = Float.parseFloat(edtAmout.getText().toString());
                    float amount4 = (amount2)  + (order_type.amount3) ; //NEED TO ADD HERE.. current code does not add correct values
                    amount4 = (float) (amount4 * 1.07);
                    amount = String.valueOf(amount4);


                    processPayment();
            }
            case R.id.BCashPayment:{
                //FirebaseDatabase.getInstance().getReference().child("Requests").child(order_type.w).child("paid").setValue("1");
                Toast.makeText(payment_type.this, "Your order has been submitted.", Toast.LENGTH_SHORT).show();

                //Toast.makeText(payment_type.this, "Your app will now close automatically.", Toast.LENGTH_SHORT).show();
                //Thread.sleep(5000);
                //finishAffinity();


                //setContentView(R.layout.activity_main);
            }
            case R.id.bConfirmTip: {

                Toast.makeText(payment_type.this, "Tip Successfully Added!", Toast.LENGTH_SHORT).show();

            }
        }
    }


        private void processPayment(){

            Intent intent = new Intent(this, PaymentActivity.class);
            PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(amount), "USD",
                "Pay Your Bill + Tip + Taxes", PayPalPayment.PAYMENT_INTENT_SALE);

            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
            startActivityForResult(intent,PAYPAL_REQUEST_CODE);

        }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            if(requestCode == PAYPAL_REQUEST_CODE)
            {
                if(resultCode == RESULT_OK)
                {
                    PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                    if(confirmation != null)
                    {
                        try{
                            String paymentDetails = confirmation.toJSONObject().toString(4);
                            startActivity(new Intent(this, PaymentDetails.class)
                                    .putExtra("PaymentDetails",paymentDetails)
                                    .putExtra("PaymentAmount",amount)
                                    );


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if (resultCode == Activity.RESULT_CANCELED)
                    Toast.makeText(this,"Cancel", Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
                Toast.makeText(this,"Invalid", Toast.LENGTH_SHORT).show();

            super.onActivityResult(requestCode,resultCode,data);

            /*temporary fix??
            if (reload == 0)
            {
                reload++;
                finishActivity(requestCode);

                processPayment();

            }
            -------------------------------*/
        }

}

