package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class news extends AppCompatActivity {

    ArrayList<Product> arrayList;
    ListView lv;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News");


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://www.bysgcovid19library.ng/contrace/pull_news.php");
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





               Intent intent = new Intent(news.this,new_detail.class);
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

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {

            progressDialog = ProgressDialog.show(news.this,"Loading. Please wait...",null,true,true);

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
                    arrayList.add(new Product(
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
            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.list_layout, arrayList
            );


            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if(netInfo!=null && netInfo.isConnected()){
                lv.setAdapter(adapter);
                progressDialog.dismiss();

            }else{
                Intent i = new Intent(news.this, no_network.class);
                startActivity(i);
                progressDialog.dismiss();

            }


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




}
