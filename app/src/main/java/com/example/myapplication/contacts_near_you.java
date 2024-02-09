package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class contacts_near_you extends AppCompatActivity {
    private ListView lstvw;
    private ArrayAdapter aAdapter;
    final ArrayList list = new ArrayList();
    BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    private int REQUEST_ENABLE_BT = 99; // Any positive integer should work.
    private BroadcastReceiver broadcastReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_near_you);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contacts Around you");
        broadcastReceiver=new MyReceiver();
        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();



        lstvw = (ListView) findViewById(R.id.deviceList);


        adapter.startDiscovery();








        //aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
      //  lstvw.setAdapter(aAdapter);



        if (bAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
        } else {


            if (!bAdapter.isEnabled()) {


                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


            } else {


            }

           // Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
           // ArrayList list = new ArrayList();
           // if (pairedDevices.size() > 0) {
            //    for (BluetoothDevice device : pairedDevices) {
             //       String devicename = device.getName();
             //       String macAddress = device.getAddress();
             //       list.add(devicename);
              //  }
              // lstvw = (ListView) findViewById(R.id.deviceList);
               // aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
               // lstvw.setAdapter(aAdapter);
           // }


        }


    }


    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                list.add(device.getName());
                aAdapter.notifyDataSetChanged();
            }


        }
    };


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

               // showToast("Found device " + device.getName());
                list.add("Name:"+device.getName()+"\r\n Address:"+device.getAddress());
                //Toast.makeText(context,"Broadcast Receiver Triggered"+device.getName(),Toast.LENGTH_SHORT).show();

            }
             lstvw = (ListView) findViewById(R.id.deviceList);
             aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
             lstvw.setAdapter(aAdapter);
        }
    };

    //@Override
   // public void onDestroy() {
       // unregisterReceiver(mReceiver);

      //  super.onDestroy();
    //}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT)
        {
            if (resultCode == 0)
            {
                // If the resultCode is 0, the user selected “No” when prompt to
                // allow the app to enable bluetooth.
                // You may want to display a dialog explaining what would happen if
                // the user doesn’t enable bluetooth.
                Intent intent = new Intent(contacts_near_you.this, Notice_message.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(contacts_near_you.this, contacts_near_you.class);
                startActivity(intent);
            }
        }
    }



    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }







    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
public void onBackPressed(){

  Intent intent = new Intent(contacts_near_you.this, Notice_message.class);
   startActivity(intent);
}

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
