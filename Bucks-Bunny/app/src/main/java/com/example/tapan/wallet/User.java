package com.example.tapan.wallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

public class User{


    User(){

    }

    private int pid=0;
    private String name=null;
    private String email=null;
    private String password=null;
    private String ph_num=null;


    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getPh_num(){return ph_num;}

    public void setName(String name)
    {
        this.name=name;
    }
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password ){ this.password=password;}
    public void setPh_num(String ph_num){this.ph_num=ph_num;}



}
