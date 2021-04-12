package com.example.why_w9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.why_w9.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import org.json.JSONException;
import java.math.BigDecimal;


public class payment_type extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    Button BPaypalPayment;
    EditText edtAmout;

    String amount = "";

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
        //add amount here



    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.BPaypalPayment: {
                processPayment();
            }
        }
    }

        private void processPayment(){
            amount = edtAmout.getText().toString();
            PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD",
                "Pay Your Bill + Tip", PayPalPayment.PAYMENT_INTENT_SALE);
            Intent intent = new Intent(this, PaymentActivity.class);
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
        }

}

