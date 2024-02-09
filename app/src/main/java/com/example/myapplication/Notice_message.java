package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.internal.location.zzas;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;




public class Notice_message extends AppCompatActivity {

    ArrayList<news_product> arrayList;
    ListView lv;


    ArrayList<trendingVideos> arrayList2;
    ListView lv2;
    ProgressDialog progressDialog;

    private static final String HI ="https://www.bysgcovid19library.ng/contrace/pull_report.php" ;


    private FusedLocationProviderClient fusedLocationClient;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_notice_message);




        getSupportActionBar().setTitle("Stay Safe Bayelsa");
        getSupportActionBar().setSubtitle("Report Dashboard");
        getSupportActionBar().setLogo(R.drawable.stay_safe);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

///////////////////////// check if there is network ///////////////////////////////////////
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if(netInfo!=null && netInfo.isConnected()){


        }else{
            Intent i = new Intent(Notice_message.this, no_network_home_view.class);
            startActivity(i);


        }






        TextView more=findViewById(R.id.more);

        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent i = new Intent(Notice_message.this, news.class);
                startActivity(i);
            }
        });

        TextView videomore=findViewById(R.id.moreVideo);

        videomore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent i = new Intent(Notice_message.this,video.class);
                startActivity(i);
            }
        });


        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.Newslist);

        arrayList2 = new ArrayList<>();
        lv2 = (ListView) findViewById(R.id.Videolist);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Notice_message.ReadJSON().execute("https://www.bysgcovid19library.ng/contrace/pull_news_update.php");
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Notice_message.ReadJSON2().execute("https://www.bysgcovid19library.ng/contrace/trendingVideos.php");
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                // ListView Clicked item index

                String title = arrayList.get(position).getTitle();
                String content = arrayList.get(position).getContent();
                String date = arrayList.get(position).getDate();
                String day = arrayList.get(position).getDay();
                String month = arrayList.get(position).getMonth();
                String year = arrayList.get(position).getYear();
                String image = arrayList.get(position).getImage();
                String ids = arrayList.get(position).getId();





                Intent intent = new Intent(Notice_message.this,new_detail.class);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                intent.putExtra("id", ids);
                startActivity(intent);



            }
        });


        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                // ListView Clicked item index

                String title = arrayList2.get(position).getTitle();
                String content = arrayList2.get(position).getContent();
                String date = arrayList2.get(position).getDate();
                String day = arrayList2.get(position).getDay();
                String month = arrayList2.get(position).getMonth();
                String year = arrayList2.get(position).getYear();
                String image = arrayList2.get(position).getImage();
                String ids = arrayList2.get(position).getId();





                Intent intent = new Intent(Notice_message.this,video_detail.class);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                intent.putExtra("apid", ids);
                startActivity(intent);



            }
        });


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        ImageView sharer = findViewById(R.id.sharer);

        sharer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://www.bysgcovid19library.ng/report-dashboard";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        TextView info_text2 = findViewById(R.id.info_text2);


        Date date = java.util.Calendar.getInstance().getTime();
        info_text2.setText("As at " + date);
        ////// pull report update ///////////


        pullReport();


    }





    // test = (Button) findViewById(R.id.totest);

    //  test.setOnClickListener(new View.OnClickListener() {
    //  @Override
    // public void onClick(View view) {
    //  Intent i = new Intent(Notice_message.this, diagnose_question.class);
    // startActivity(i);

    //   }
    //  });


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.covid) {

            Intent intent = new Intent(this, faq.class);
            this.startActivity(intent);

        }
        if (item.getItemId() == R.id.assesment) {

            Intent intent = new Intent(this, diagnose_question.class);
            this.startActivity(intent);

        }


        if (item.getItemId() == R.id.measures) {
            Intent intent = new Intent(this, guideline.class);
            this.startActivity(intent);
        }

        if (item.getItemId() == R.id.contact) {
            Intent intent = new Intent(this, econtact.class);
            this.startActivity(intent);
        }

        if (item.getItemId() == R.id.exit) {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        if (item.getItemId() == R.id.news) {
            Intent intent = new Intent(this, news.class);
            this.startActivity(intent);
        }

        if (item.getItemId() == R.id.video) {
            Intent intent = new Intent(this, video.class);
            this.startActivity(intent);
        }


   // if (item.getItemId() == R.id.current_contact) {
    //        Intent intent = new Intent(this, contact_trace_info.class);
     //       this.startActivity(intent);
      //  }


        if (item.getItemId() == R.id.fresh) {
            Intent intent = new Intent(this, Notice_message.class);
            this.startActivity(intent);

            Context context = getApplicationContext();
            CharSequence text = "Page Refreshed Successfuly";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }



        return true;
    }


    public void pullReport() {


        StringRequest stringRequest=new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);

                        TextView cases = findViewById(R.id.cases);
                        TextView a_case = findViewById(R.id.a_case);
                        TextView d_case = findViewById(R.id.d_case);
                        TextView dt_case = findViewById(R.id.dt_case);

                        cases.setText(ob.getString("cases"));
                        a_case.setText(ob.getString("a_case"));
                        d_case.setText(ob.getString("d_case"));
                        dt_case.setText(ob.getString("dt_case"));
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

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


    class ReadJSON extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {

           // progressDialog = ProgressDialog.show(Notice_message.this,"Loading. Please wait...",null,true,true);

        }

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("data");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new news_product(
                            productObject.getString("filename"),
                            productObject.getString("title"),
                            productObject.getString("time_update"),
                            productObject.getString("day"),
                            productObject.getString("month"),
                            productObject.getString("year"),
                            productObject.getString("content"),
                            productObject.getString("news_id")



                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NewsListAdapter adapter = new NewsListAdapter(
                    getApplicationContext(), R.layout.activity_news_list_view, arrayList
            );



                lv.setAdapter(adapter);



        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }




    class ReadJSON2 extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {

            // progressDialog = ProgressDialog.show(Notice_message.this,"Loading. Please wait...",null,true,true);

        }

        @Override
        protected String doInBackground(String... params) {
            return readURL2(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("data");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList2.add(new trendingVideos(
                            productObject.getString("img"),
                            productObject.getString("title"),
                            productObject.getString("post_time"),
                            productObject.getString("day"),
                            productObject.getString("month"),
                            productObject.getString("year"),
                            productObject.getString("description"),
                            productObject.getString("appid")



                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            VideoListAdaptor adapter2 = new VideoListAdaptor(
                    getApplicationContext(), R.layout.video_list_view, arrayList2
            );



            lv2.setAdapter(adapter2);



        }
    }


    private static String readURL2(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


}
