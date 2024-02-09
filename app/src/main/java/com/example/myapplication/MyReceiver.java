package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyReceiver extends BroadcastReceiver {
    final ArrayList list = new ArrayList();
    private ListView lstvw;
    private ArrayAdapter aAdapter;
    BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Discovery has found a device. Get the BluetoothDevice
            // object and its info from the Intent.
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String deviceName = device.getName();
            list.add(device.getName());
            //String deviceHardwareAddress = device.getAddress(); // MAC address

        }
        //lstvw = (ListView) lstvw.findViewById(R.id.deviceList);
       // aAdapter = new ArrayAdapter(context.getApplicationContext(), android.R.layout.simple_list_item_1, list);
        //lstvw.setAdapter(aAdapter);
        Toast.makeText(context,"Broadcast Receiver Triggered"+list,Toast.LENGTH_SHORT).show();
    }
}
