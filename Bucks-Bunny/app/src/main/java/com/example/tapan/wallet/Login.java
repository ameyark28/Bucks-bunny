package com.example.tapan.wallet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Login extends SQLiteOpenHelper {

    private static final String admin_db="Admin.db";
    private static final int database_version=1;

    private String col_pid="pid",col_name="name",col_ph_no="ph_no",col_pass="password",col_email="email";
    private String create_statement="create table user ("+col_pid+" integer primary key AUTOINCREMENT, "+col_name+" text not null,"+col_ph_no+" text not null unique,"+col_pass+" text not null,"+col_email+" text not null unique);";

    public Login(Context context) {
        super(context, admin_db, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
