package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class econtact extends AppCompatActivity {

    private static final int REQUEST_CALL =1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_econtact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Emergency Numbers");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


      Button  number1 = (Button) findViewById(R.id.number1);
        Button  number2 = (Button) findViewById(R.id.number2);
        Button  number3 = (Button) findViewById(R.id.number3);
        Button  number4 = (Button) findViewById(R.id.number4);
        Button  number5 = (Button) findViewById(R.id.number5);


        number1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                if (ContextCompat.checkSelfPermission(econtact.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(econtact.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:08039216821";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                if (ContextCompat.checkSelfPermission(econtact.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(econtact.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:07019304970";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }





            }
        });



        number3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {




                if (ContextCompat.checkSelfPermission(econtact.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(econtact.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:08151693570";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }





            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {



                if (ContextCompat.checkSelfPermission(econtact.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(econtact.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:09010999972";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }




            }
        });


        number5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {



                if (ContextCompat.checkSelfPermission(econtact.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(econtact.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:09010999969";
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }



            }
        });

    }



    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}
