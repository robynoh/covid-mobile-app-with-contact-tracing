package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class diagnose_question<interger> extends AppCompatActivity {
    private LinearLayout one;
    private LinearLayout two;
    private LinearLayout three;
    private LinearLayout four;
    private LinearLayout five;
    private LinearLayout six;
    private LinearLayout seven;
    private LinearLayout eight;
    private LinearLayout result_message;
    private LinearLayout cancel_check;
    private RadioGroup travel;
    private RadioGroup contact;
    private RadioGroup breathing;
    private RadioGroup hotness;
    private RadioGroup cough;
    private RadioGroup taste;
    private RadioGroup stool;
    private RadioButton travelselected,contactselected,breathingselected,hotnessselected,coughselected,tasteselected,stoolselected;
    EditText name2, phone2,address2;
    String namevalue,addressvalue,phonevalue;
    //TextView result_message,score_message,contact_message,retake;
    int sum=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_question);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        openDialog();





        // Find the toolbar view inside the activity layout
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
       // setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("SELF ASSESMENT TOOL");
        //toolbar.setTitleTextColor(android.graphics.Color.WHITE);

       // toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          //  @Override
       //     public void onClick(View v) {
       //         onBackPressed();
       //     }
      //  });



        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        travel = (RadioGroup) findViewById(R.id.travel);
        contact = (RadioGroup) findViewById(R.id.contact);
        breathing = (RadioGroup) findViewById(R.id.breathing);
        hotness = (RadioGroup) findViewById(R.id.hotness);
        cough = (RadioGroup) findViewById(R.id.cough);
        taste = (RadioGroup) findViewById(R.id.taste);
        stool = (RadioGroup) findViewById(R.id.stool);


        Button submittest = (Button) findViewById(R.id.submittest);
        Button retake = (Button) findViewById(R.id.retake);


        final TextView score_message = (TextView) findViewById(R.id.score_message);
        final TextView contact_message = (TextView) findViewById(R.id.contact_message);

        final LinearLayout result_message = findViewById(R.id.result_message);
        final LinearLayout main_question = findViewById(R.id.main_question);
        result_message.setVisibility(View.INVISIBLE);

        final LinearLayout cancel_check = findViewById(R.id.cancel_check);

        retake.setOnClickListener(new View.OnClickListener() {

                                          @Override
                                          public void onClick(View v) {


                                              Intent i = new Intent(diagnose_question.this, diagnose_question.class);
                                              startActivity(i);


    }});








        submittest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedtravel = travel.getCheckedRadioButtonId();
                int selectedcontact = contact.getCheckedRadioButtonId();
                int selectedbreathing = breathing.getCheckedRadioButtonId();
                int selectedhotness = hotness.getCheckedRadioButtonId();
                int selectedcough = cough.getCheckedRadioButtonId();
                int selectedtaste = taste.getCheckedRadioButtonId();
                int selectedstool = stool.getCheckedRadioButtonId();

                // find the radiobutton by returned id
              travelselected = (RadioButton) findViewById(selectedtravel);
                contactselected = (RadioButton) findViewById(selectedcontact);
                breathingselected = (RadioButton) findViewById(selectedbreathing);
                hotnessselected = (RadioButton) findViewById(selectedhotness);
                coughselected = (RadioButton) findViewById(selectedcough);
                tasteselected = (RadioButton) findViewById(selectedtaste);
                stoolselected = (RadioButton) findViewById(selectedstool);



                name2 = (EditText)findViewById(R.id.name);
                phone2 = (EditText)findViewById(R.id.phone);
                address2= (EditText)findViewById(R.id.address);

                namevalue = name2.getText().toString();
                phonevalue = phone2.getText().toString();
                addressvalue = address2.getText().toString();



                if (TextUtils.isEmpty(namevalue)) {
                    name2.setError("Please enter name");
                    name2.requestFocus();
                    return;
                }

                if(!isValidPhone(phonevalue)) {


                    phone2.setError("Please enter Phone number ");
                    phone2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(addressvalue)) {
                    address2.setError("Please enter address ");
                    address2.requestFocus();
                    return;
                }
                 else {


                    if (travelselected.getText().equals("Yes")) {

                        sum = sum + 5;

                    } else {

                        sum = sum + 0;

                    }
                    if (contactselected.getText().equals("Yes")) {

                        sum = sum + 30;

                    } else {

                        sum = sum + 0;

                    }
                    if (breathingselected.getText().equals("Yes")) {

                        sum = sum + 10;

                    } else {

                        sum = sum + 0;

                    }
                    if (hotnessselected.getText().equals("Yes")) {

                        sum = sum + 5;

                    } else {

                        sum = sum + 0;

                    }

                    if (coughselected.getText().equals("Yes")) {

                        sum = sum + 5;

                    } else {

                        sum = sum + 0;

                    }


                    if (tasteselected.getText().equals("Yes")) {

                        sum = sum + 2;

                    } else {

                        sum = sum + 0;

                    }

                    if (stoolselected.getText().equals("Yes")) {

                        sum = sum + 2;

                    } else {

                        sum = sum + 0;

                    }


                    result_message.setVisibility(View.VISIBLE);
                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    four.setVisibility(View.INVISIBLE);
                    five.setVisibility(View.INVISIBLE);
                    six.setVisibility(View.INVISIBLE);
                    seven.setVisibility(View.INVISIBLE);
                    eight.setVisibility(View.INVISIBLE);
                    cancel_check.setVisibility(View.INVISIBLE);

                    if (sum >= 0) {

                        score_message.setText("YOU ARE LOW RISK OF COVID-19");
                        contact_message.setText("Drink water regularly and observe personal good hygiene. Pay attention to your health and redo test after two days");

                    }
                    if (sum >= 7) {

                        score_message.setText("YOU ARE LOW RISK OF COVID-19");
                        contact_message.setText("What you are feeling may be caused by stress or malaria. Drink water regularly and observe personal good hygiene. Call a doctor If your symptoms worsen.");


                    }

                    if (sum >= 10) {

                        score_message.setText("YOU ARE MEDIUM RISK OF COVID-19");
                        contact_message.setText("Please visit the nearest health care facility for futher advise on your case");


                    }
                    if (sum >= 20) {

                        score_message.setText("YOU ARE MEDIUM RISK OF COVID-19");
                        contact_message.setText("Please visit the nearest health care facility for futher advise on your case");

                    }
                    if (sum >= 25) {

                        InsertData(namevalue, phonevalue,addressvalue);
                        score_message.setText("YOU ARE HIGH RISK OF COVID-19");
                        contact_message.setText("isolate yourself from friends and family, Immidiately call any of our emergency numbers ");
                    }
                    if (sum >= 35) {
                        InsertData(namevalue, phonevalue,addressvalue);
                        score_message.setText("YOU ARE HIGH RISK OF COVID-19");
                        contact_message.setText("isolate yourself from friends and family, Immidiately call any of our emergency numbers");


                    }
                    //Toast.makeText(diagnose_question.this,String.valueOf(sum),Toast.LENGTH_LONG).show();
                }
            }

        });



        Button button = (Button) findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(diagnose_question.this, Notice_message.class);
                startActivity(i);
            }
        });




        one =findViewById(R.id.one);
        one.setVisibility(View.VISIBLE);

        two =findViewById(R.id.two);
        two.setVisibility(View.INVISIBLE);


        three = findViewById(R.id.three);
        three.setVisibility(View.INVISIBLE);

        four = findViewById(R.id.four);
        four.setVisibility(View.INVISIBLE);


        five = findViewById(R.id.five);
        five.setVisibility(View.INVISIBLE);

        six = findViewById(R.id.six);
        six.setVisibility(View.INVISIBLE);


        seven = findViewById(R.id.seven);
        seven.setVisibility(View.INVISIBLE);

        eight = findViewById(R.id.eight);
        eight.setVisibility(View.INVISIBLE);

    }


    public void openDialog(){

    test_infomation test_info =new test_infomation();
        test_info.show(getSupportFragmentManager(),"test Information");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }


    public void one(View view)
    {

        travel = (RadioGroup) findViewById(R.id.travel);
        int selectedtravel = travel.getCheckedRadioButtonId();

        if (travel.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question one before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {
            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");
            LinearLayout one = findViewById(R.id.one);
            one.setVisibility(View.INVISIBLE);

            LinearLayout two = findViewById(R.id.two);
            two.setVisibility(View.VISIBLE);
            // Do something in response to button click

            //one.setVisibility(View.INVISIBLE);
            //two.setVisibility(View.VISIBLE);
        }

    }

    public void two(View view)
    {

        contact = (RadioGroup) findViewById(R.id.contact);
        int selectedcontact = contact.getCheckedRadioButtonId();

        if (contact.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question two before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {
            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");
            LinearLayout two = findViewById(R.id.two);
            two.setVisibility(View.INVISIBLE);

            LinearLayout three = findViewById(R.id.three);
            three.setVisibility(View.VISIBLE);
            // Do something in response to button click

            //one.setVisibility(View.INVISIBLE);
            //two.setVisibility(View.VISIBLE);

        }
    }

    public void three(View view)
    {

        breathing = (RadioGroup) findViewById(R.id.breathing);
        int selectedcontact = breathing.getCheckedRadioButtonId();

        if (breathing.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question three before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");

            LinearLayout three = findViewById(R.id.three);
            three.setVisibility(View.INVISIBLE);

            LinearLayout four = findViewById(R.id.four);
            four.setVisibility(View.VISIBLE);
            // Do something in response to button click

            //one.setVisibility(View.INVISIBLE);
            //two.setVisibility(View.VISIBLE);

        }
    }

    public void four(View view) {

        hotness = (RadioGroup) findViewById(R.id.hotness);
        int selectedcontact = hotness.getCheckedRadioButtonId();

        if (hotness.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question four before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");

        LinearLayout four = findViewById(R.id.four);
        four.setVisibility(View.INVISIBLE);

        LinearLayout five = findViewById(R.id.five);
        five.setVisibility(View.VISIBLE);
        // Do something in response to button click

    }

    }


    public void five(View view) {

        cough = (RadioGroup) findViewById(R.id.cough);
        int selectedcontact = cough.getCheckedRadioButtonId();

        if (cough.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question five before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");

        LinearLayout five = findViewById(R.id.five);
        five.setVisibility(View.INVISIBLE);

        LinearLayout six = findViewById(R.id.six);
        six.setVisibility(View.VISIBLE);
        // Do something in response to button click
    }


    }


    public void six(View view)
    {

        taste = (RadioGroup) findViewById(R.id.taste);
        int selectedtaste = taste.getCheckedRadioButtonId();

        if (taste.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question six before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");

            LinearLayout six = findViewById(R.id.six);
            six.setVisibility(View.INVISIBLE);

            LinearLayout seven = findViewById(R.id.seven);
            seven.setVisibility(View.VISIBLE);
            // Do something in response to button click

        }

    }

    public void seven(View view)
    {

        stool = (RadioGroup) findViewById(R.id.stool);
        int selectedstool = stool.getCheckedRadioButtonId();

        if (stool.getCheckedRadioButtonId() == -1)
        {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText("Please answer question seven before proceeding to the Next.");
            // no radio buttons are checked
            // You must choose your answer before proceeding to the next question
        }else {

            TextView textView = (TextView) findViewById(R.id.errormsg);
            textView.setText(" ");

            LinearLayout seven = findViewById(R.id.seven);
            seven.setVisibility(View.INVISIBLE);

            LinearLayout eight = findViewById(R.id.eight);
            eight.setVisibility(View.VISIBLE);
            // Do something in response to button click

        }

    }

    public static boolean isValidPhone(String phone)
    {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }


    public void InsertData(final String namevalue, final String phonevalue, final String addressvalue){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            String Register_url;
            Context ctx = getApplicationContext();

            @Override
            protected void onPreExecute() {
                Register_url="https://www.bysgcovid19library.ng/contrace/collect_high_score.php";
             }

            @Override
            protected String doInBackground(String... params) {

                String NameHolder = namevalue;
                String PHONEHolder = phonevalue ;
                String ADDRESSHolder = addressvalue ;

                // generate your params:
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("phone", PHONEHolder));
                nameValuePairs.add(new BasicNameValuePair("address", ADDRESSHolder));

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

        sendPostReqAsyncTask.execute(namevalue,phonevalue,addressvalue);
    }


}
