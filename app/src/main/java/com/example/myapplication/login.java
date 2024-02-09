package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.home.HomeFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
EditText phone;
Button button,button2;
TextView network2;
String phonenumber;
String tem,result;
ProgressDialog progressDialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






        phone = (EditText)findViewById(R.id.phone);
        button = (Button)findViewById(R.id.testbn);
       button2 = (Button)findViewById(R.id.regbtn);
        network2=(TextView)findViewById(R.id.network2);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                GetData();

                if (TextUtils.isEmpty(phonenumber)) {
                   phone.setError("Enter Phone number");
                   phone.requestFocus();
                   return;
              }




                InsertData(phonenumber);





            }
        });
    }

    public void GetData(){


        phonenumber = phone.getText().toString();

    }

    public void InsertData(final String phonenumber){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            Context ctx = getApplicationContext();



            @Override
            protected void onPreExecute() {

                String EmailHolder =phonenumber ;
                tem="https://www.bysgcovid19library.ng/contrace/login.php?phonenumber="+EmailHolder;
                progressDialog = ProgressDialog.show(login.this,"Loading. Please wait...",null,true,true);

            }

            @Override
            protected String doInBackground(String... params) {




                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(tem);

                // send them on their way
                try
                {
                    ////// use connectivity manager to check if there is network, if no network set button to disable/////
                    ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                    if(netInfo!=null && netInfo.isConnected()){

                        HttpResponse response=client.execute(request);
                        HttpEntity entity=response.getEntity();
                        InputStream is=entity.getContent();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader reader = new BufferedReader(isr);

                        if((reader.readLine()) != null)
                        {

                            result=reader.readLine();
                        }
                    }else{



                        Intent i = new Intent(login.this, no_network.class);
                        startActivity(i);
                        progressDialog.dismiss();

                    }





                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }


               return result;
            }

            @Override
            protected void onPostExecute(String result) {




                if(result.equals("0")==true)
                {

                    Context context = getApplicationContext();
                    CharSequence text = "This number is yet to create an account";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    progressDialog.dismiss();


                }
                else
                {


                    Intent i = new Intent(login.this, Notice_message.class);
                    startActivity(i);
                    //FragmentManager fm=getSupportFragmentManager();
                   // HomeFragment fragment = new HomeFragment();
                   // fm.beginTransaction().replace(R.id.loginFrame, fragment).commit();
                    //progressDialog.dismiss();


                }





            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(phonenumber);
    }


    public void registerAcc(View view){

        Intent i = new Intent(this, register.class);
        startActivity(i);

    }
}
