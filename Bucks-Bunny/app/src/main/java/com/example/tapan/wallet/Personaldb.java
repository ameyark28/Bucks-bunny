package com.example.tapan.wallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Personaldb extends SQLiteOpenHelper {


    protected String user_name="personal";
    protected String col_amt="amount";
    protected String col_category="category";
    protected String col_type="type";

    private String create_statement="create table personal("+col_amt+" float,"+col_category+"text,"+col_type+" text);";

    public Personaldb(Context context) {
        super(context, null, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(create_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists personal;");
    }

    public void addExpense(Personal personal, String amt, String cate, String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_amt, amt);
        values.put(col_category,cate);
        values.put(col_type,type);

        db.insert(user_name, null, values);
        db.close();

    }

    public Cursor getTransaction(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select * from "+user_name,null);
        return cu;
    }


}
