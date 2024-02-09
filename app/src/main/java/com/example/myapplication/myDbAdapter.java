package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.widget.Toast;

public class myDbAdapter {
    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);

    }

    public long insertData(String phone, String bname) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COL_2, phone);
        contentValues.put(myDbHelper.COL_3, bname);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);

        return id;
    }




    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "user_db";    // Database Name
        private static final String TABLE_NAME = "users";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String COL_1="_id";     // Column I (Primary Key)
        private static final String COL_2 = "phone";    //Column II
        private static final String COL_3= "bluetooth";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" VARCHAR(255) ,"+ COL_3+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
             //   Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
               // Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
               // Message.message(context,""+e);
            }
        }


    }
}
