package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

class message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}