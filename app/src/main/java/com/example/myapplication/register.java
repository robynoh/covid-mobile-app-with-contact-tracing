package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class register extends AppCompatActivity {
EditText USER,PHONE;
String uservalue, phonenumber;
Button button,button2;
TextView network;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         USER = (EditText)findViewById(R.id.username);
         PHONE = (EditText)findViewById(R.id.phonenumber);
        button = (Button)findViewById(R.id.bn);
        button2 = (Button)findViewById(R.id.tlbtn);
        network=(TextView)findViewById(R.id.network);

        ////// use connectivity manager to check if there is network, if no network set button to disable/////
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if(netInfo!=null && netInfo.isConnected()){

            network.setVisibility(View.INVISIBLE);


        }else{

            Intent i = new Intent(register.this, no_network.class);
            startActivity(i);

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                GetData();

                if (TextUtils.isEmpty(uservalue)) {
                    USER.setError("Please enter username");
                    USER.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(phonenumber)) {
                    PHONE.setError("Please enter Phone number");
                    PHONE.requestFocus();
                    return;
                }



               // Context context = getApplicationContext();
                //CharSequence text = phonenumber+uservalue;
                //int duration = Toast.LENGTH_SHORT;

               // Toast toast = Toast.makeText(context, text, duration);
               // toast.show();

                InsertData(uservalue, phonenumber);

            }
        });



    }

    public void GetData(){

        uservalue = USER.getText().toString();
        phonenumber = PHONE.getText().toString();

    }



    public void InsertData(final String uservalue, final String phonenumber){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            String Register_url;
            Context ctx = getApplicationContext();

            @Override
            protected void onPreExecute() {
                Register_url="https://www.bysgcovid19library.ng/contrace/register.php";
                progressDialog = ProgressDialog.show(register.this,"Loading. Please wait...",null,true,true);
            }

            @Override
            protected String doInBackground(String... params) {

                String NameHolder = uservalue;
                String EmailHolder = phonenumber ;

                // generate your params:
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("uservalue", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("phonenumber", EmailHolder));

                // send them on their way
                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(Register_url);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();





                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Account created successfuly";
            }

            @Override
            protected void onPostExecute(String result) {

                Context context = getApplicationContext();
                CharSequence text = result;
                int duration = Toast.LENGTH_SHORT;



                Intent intent = new Intent(register.this, login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(uservalue,phonenumber);
    }




   public void openLogin(View view){

       Intent i = new Intent(this, login.class);
       startActivity(i);

   }







}
