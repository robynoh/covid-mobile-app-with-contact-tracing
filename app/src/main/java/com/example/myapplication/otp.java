package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;

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
import java.util.ArrayList;
import java.util.List;

public class otp extends AppCompatActivity {

    PinEntryEditText pinEntry;
    BluetoothAdapter mBluetoothAdapter;
    myDbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Validation");
        dbAdapter=new myDbAdapter(this);

        final String phone = getIntent().getStringExtra("PHONE");
        final String otp = getIntent().getStringExtra("OTP");



 Toast.makeText(getApplicationContext(),otp, Toast.LENGTH_LONG).show();

        requestsmspermission();
        pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        new OTP_Receiver().setEditText(pinEntry);
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().equals(otp)) {


              /////////////////////// on success, get user bluetooth name ////////////////////////////
                        String bluetoothName=getLocalBluetoothName();
             ///////////////////////////insert into database on live server ////////////////////////////
                        InsertUserData(bluetoothName,phone);


             ////////////////////////// also insert in database in phone ////////////////////////////////////
            Long   id=  dbAdapter.insertData(phone,bluetoothName);


                        Intent intent = new Intent(otp.this, contacts_near_you.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(otp.this, "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);
                    }
                }
            });
        }




    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public String getLocalBluetoothName(){

        if(mBluetoothAdapter == null){
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        String name = mBluetoothAdapter.getName();
        if(name == null){
            System.out.println("Name is null!");
            name = mBluetoothAdapter.getAddress();
        }
        return name;
    }


    private void requestsmspermission() {
        String smspermission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this,smspermission);
        //check if read SMS permission is granted or not
        if(grant!= PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0]=smspermission;
            ActivityCompat.requestPermissions(this,permission_list,1);
        }
    }



    public void InsertUserData(final String bname, final String phone){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            String Register_url;
            Context ctx = getApplicationContext();

            @Override
            protected void onPreExecute() {
                Register_url="https://www.bysgcovid19library.ng/contrace/traceUsers.php";
                         }

            @Override
            protected String doInBackground(String... params) {

                String BNAME = bname;
                String PHONE = phone ;

                // generate your params:
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("bname", BNAME));
                nameValuePairs.add(new BasicNameValuePair("phone", PHONE));

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


            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(bname,phone);
    }
}
