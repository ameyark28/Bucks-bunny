package com.example.tapan.wallet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Personal{

    private float amount=0;
    private String type= null;
    private String category=null;


    public Personal()
    {
    }
    public float getAmount(){return amount; }
    public String getType(){return type;}
    public String getCategory(){return category;}

    public void setAmount(float amount){
        this.amount=amount;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setCategory(String category){
        this.category=category;
    }
}