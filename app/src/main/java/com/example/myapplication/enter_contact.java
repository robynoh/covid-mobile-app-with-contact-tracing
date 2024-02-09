package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class enter_contact extends AppCompatActivity {
    String phone;
   // private static final int PERMISSION_REQUEST_CODE = 1;

    private final static int SEND_SMS_PERMISSION_REQ=1;
    private final static int REQUEST_READ_PHONE_STATE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Detail");

        Button button =  findViewById(R.id.phone_continue);



       button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                SecureRandom random = new SecureRandom();
                int num = random.nextInt(100000);
                String randomNumber = String.format("%05d", num);
                String sms="Your validation code is "+randomNumber;


                EditText phonevalue = (EditText) findViewById(R.id.phone);
                phone =phonevalue.getText().toString();
                if (!TextUtils.isEmpty(randomNumber) && !TextUtils.isEmpty(phone)) {


                    if (ContextCompat.checkSelfPermission(enter_contact.this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted
                        // Ask for permision
                        ActivityCompat.requestPermissions(enter_contact.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                    } else {
// Permission has already been granted




                        try {
                            SmsManager.getDefault().sendTextMessage(phone, null,sms, null, null);
                            Intent intent = new Intent(enter_contact.this, otp.class);
                            intent.putExtra("OTP",  randomNumber);//
                            intent.putExtra("PHONE",  phone);//
                            startActivity(intent);
                            Toast.makeText(enter_contact.this, "A validation code has been sent to you", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            if (e.toString().contains(Manifest.permission.READ_PHONE_STATE) && ContextCompat
                                    .checkSelfPermission(enter_contact.this, Manifest.permission.READ_PHONE_STATE)!=
                                    PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(enter_contact.this, new String[] {Manifest.permission
                                        .READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                            } // else it's some other exception
                        }




                      // SmsManager smsManager = SmsManager.getDefault();
                      //smsManager.sendTextMessage(phone, null,sms, null, null);

                       // sendSMS(phone,sms);
                    }
                }


            }


           // Intent intent = new Intent(enter_contact.this, otp.class);
                //startActivity(intent);




        });
    }


    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


  public void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }








}
