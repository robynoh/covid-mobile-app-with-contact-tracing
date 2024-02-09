package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //receive Broadcast msg here
        //also use same Action as we use in Broadcast Sender App
        if ("com.jc_code.ACTION_SEND".equals(intent.getAction())){
            String msg=intent.getStringExtra("com.jc_code.ACTION_TEXT");//same key as we use in
            //Broadcast sender project
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}