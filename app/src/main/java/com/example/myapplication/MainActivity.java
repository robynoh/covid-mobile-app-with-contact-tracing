package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.slideshow.SlideshowFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    int n=0;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    //Intent i = new Intent(MainActivity.this, Notice_message.class);
   // startActivity(i);

        Intent intent = new Intent(MainActivity.this, splash_screen.class);
        startActivity(intent);




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, report_suspect_case.class);
                startActivity(i);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
            }
        });







        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void one(View view)
    {

        LinearLayout one = findViewById(R.id.one);
        one.setVisibility(View.INVISIBLE);

        LinearLayout two = findViewById(R.id.two);
        two.setVisibility(View.VISIBLE);
        // Do something in response to button click

        //one.setVisibility(View.INVISIBLE);
        //two.setVisibility(View.VISIBLE);


    }

    public void two(View view)
    {

        LinearLayout two = findViewById(R.id.two);
        two.setVisibility(View.INVISIBLE);

        LinearLayout three = findViewById(R.id.three);
        three.setVisibility(View.VISIBLE);
        // Do something in response to button click

        //one.setVisibility(View.INVISIBLE);
        //two.setVisibility(View.VISIBLE);


    }

    public void three(View view)
    {

        LinearLayout three = findViewById(R.id.three);
        three.setVisibility(View.INVISIBLE);

        LinearLayout four = findViewById(R.id.four);
        four.setVisibility(View.VISIBLE);
        // Do something in response to button click

        //one.setVisibility(View.INVISIBLE);
        //two.setVisibility(View.VISIBLE);


    }

    public void four(View view)
    {

        LinearLayout four = findViewById(R.id.four);
        four.setVisibility(View.INVISIBLE);

        LinearLayout five = findViewById(R.id.five);
        five.setVisibility(View.VISIBLE);
        // Do something in response to button click



    }


    public void five(View view)
    {

        LinearLayout five = findViewById(R.id.five);
        five.setVisibility(View.INVISIBLE);

        LinearLayout six = findViewById(R.id.six);
        six.setVisibility(View.VISIBLE);
        // Do something in response to button click



    }


    public void six(View view)
    {

        LinearLayout six = findViewById(R.id.six);
        six.setVisibility(View.INVISIBLE);

        LinearLayout seven = findViewById(R.id.seven);
        seven.setVisibility(View.VISIBLE);
        // Do something in response to button click



    }











}
