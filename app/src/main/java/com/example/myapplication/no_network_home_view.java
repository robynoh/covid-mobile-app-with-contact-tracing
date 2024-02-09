package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class no_network_home_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network_home_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Network Failure");



        ImageView faq = findViewById(R.id.faqs);
        ImageView guide = findViewById(R.id.guide);
        ImageView contact = findViewById(R.id.contact);
        ImageView asses = findViewById(R.id.assess);
        ImageView news = findViewById(R.id.news);
        ImageView video = findViewById(R.id.vid);

        faq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(no_network_home_view.this, faq.class);
                startActivity(intent);

            }
        });

        guide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(no_network_home_view.this, guideline.class);
                startActivity(intent);
            }
        });


        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(no_network_home_view.this, econtact.class);
                startActivity(intent);
            }
        });


        asses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(no_network_home_view.this, diagnose_question.class);
                startActivity(intent);
            }
        });


       news.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(no_network_home_view.this, news.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(no_network_home_view.this, video.class);
                startActivity(intent);
            }
        });

    }
}
