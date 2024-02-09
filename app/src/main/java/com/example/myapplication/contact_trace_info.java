package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contact_trace_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_trace_info);
        myDbAdapter db=new myDbAdapter(this);

       int num= getUserCount("SELECT  * FROM users" );
       if(num>0){

           Intent intent = new Intent(contact_trace_info.this, contacts_near_you.class);
           startActivity(intent);

       }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("People Near You");
        Button activate = (Button) findViewById(R.id.activate);

        activate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {



                Intent intent = new Intent(contact_trace_info.this, enter_contact.class);
                startActivity(intent);



            }
        });



    }

    public int getUserCount(String query) {
        String countQuery = query;
        SQLiteOpenHelper database = new myDbAdapter.myDbHelper(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
