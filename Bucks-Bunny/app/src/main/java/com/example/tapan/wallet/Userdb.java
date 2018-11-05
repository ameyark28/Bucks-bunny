package com.example.tapan.wallet;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;


public class Userdb extends SQLiteOpenHelper {


    protected String col_pid="pid";
    protected String col_name="name";
    protected String col_ph_num="ph_num";
    protected String col_password="password";
    protected String col_email="email";

    private String create_statement="create table user_detail ("+col_pid+" integer primary key autoincrement,"+col_name+" text not null,"+col_ph_num+" text not null,"+col_password+" password not null,"+col_email+" text not null);";


    Userdb(Context context)
    {
        super(context,"user_details",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user_details");
        onCreate(db);
    }


    public void addUser(ThreadLocal<User> user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_name, user.get().getName());
        values.put(col_email, user.get().getEmail());
        values.put(col_password, user.get().getPassword());
        values.put(col_ph_num,user.get().getPh_num());

        db.insert("user_detail", null, values);
        db.close();
    }


    public boolean checkUser(String name){
        String[] columns = {
               col_pid
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = name + " = ?";
        String[] selectionArgs = { name };

        Cursor cursor = db.query("user_details",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }

        return false;
    }

    public boolean checkUser(String name, String password){
        String[] columns = {
                col_pid
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = name + " = ?" + " AND " + password + " =?";
        String[] selectionArgs = { name, password };

        Cursor cursor = db.query("user_details",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        AtomicInteger cursorCount = new AtomicInteger(cursor.getCount( ));

        cursor.close();
        db.close();

        if (cursorCount.get( ) > 0){
            return true;
        }
        return false;
    }
}

