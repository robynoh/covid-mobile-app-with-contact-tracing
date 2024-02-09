package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class new_detail extends AppCompatActivity {

  int  pos=0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News Update");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }



        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        final String day = getIntent().getStringExtra("day");
        final String month = getIntent().getStringExtra("month");
        final String year = getIntent().getStringExtra("year");
        String content = getIntent().getStringExtra("content");
        final String ids = getIntent().getStringExtra("id");
        String image = getIntent().getStringExtra("image");

        TextView newsHead = (TextView) findViewById(R.id.newsHead);
        TextView newsDate = (TextView) findViewById(R.id.newsDate);
        ImageView newsImage = (ImageView) findViewById(R.id.newsImage);
        TextView newsContent = (TextView) findViewById(R.id.newsContent);

        newsHead.setText(title);
        newsDate.setText(date);
        newsContent.setText(Html.fromHtml(content));

        final String replaceTitle = title.replace(' ', '-');

        Picasso.with(new_detail.this).load("https://www.bysgcovid19library.ng/mgt/news_photos/"+image).into(newsImage);



        Button share = (Button) findViewById(R.id.share);


        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://www.bysgcovid19library.ng/news/"+day+"/"+month+"/"+year+"/"+replaceTitle+"/"+ids;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }


    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void pullReport(int id) {
        final String HI ="https://www.bysgcovid19library.ng/contrace/news_detail.php?id="+id;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);

                        TextView newsHead = (TextView) findViewById(R.id.newsHead);
                        TextView newsDate = (TextView) findViewById(R.id.newsDate);
                        ImageView newsImage = (ImageView) findViewById(R.id.newsImage);
                        TextView newsContent = (TextView) findViewById(R.id.newsContent);


                        newsHead.setText(ob.getString("title"));
                        newsContent.setText(ob.getString("content"));
                        newsDate.setText(ob.getString("time_update"));
                        Picasso.with(new_detail.this).load("https://www.bysgcovid19library.ng/mgt/news_photos/"+ob.getString("filename")).into(newsImage);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}