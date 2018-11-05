package com.example.tapan.wallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupAccdb extends SQLiteOpenHelper {


    private String tablename=null;
    private String col_name="name";
    private String col_amt="amt";
    private String col_due="due";

    private String create_stmt= "table create "+tablename+"(name text not null, amt text, due text);";
    SQLiteDatabase db;

    public GroupAccdb(Context context) {
        super(context, "", null, 1);
       //tablename=grpname;
        //addmem(mem);
    }

    private void addmem(String[] mem) {
        ContentValues values = new ContentValues();
        for(int i=0;i<5;i++)
        {
            values.put(col_name,mem[i]);
            values.put(col_amt,0);
            values.put(col_due,0);
            db.insert(tablename,null,values);
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_stmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists group_details");
        onCreate(db);
    }

    public void addgrpmems(String mem[]){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=0;

        ContentValues values = new ContentValues();
    for(i=0; i<5; i++)
        {
            values.put(col_name,mem[i]);
            values.put(col_amt,0);
            values.put(col_due,0);
            db.insert(tablename,null,values);
        }

    }

    public void addgrpexp(String nameuser,String amt1){
        SQLiteDatabase db=this.getWritableDatabase();
        GroupAcc g=new GroupAcc();
        Cursor cu =getPerson(nameuser);
        int amt=cu.getColumnIndex("amount");
        amt+=Integer.parseInt(amt1);
        ContentValues values = new ContentValues();

        values.put(col_name,nameuser);
        values.put(col_amt,amt);
        values.put(col_due,0);
        db.insert(tablename,null,values);
    }

    public Cursor getPerson(String nameuser){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu=db.rawQuery("select name from "+tablename+"where name="+nameuser,null);
        return cu;
    }

    public Cursor splitingcur(Context con) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cu = db.rawQuery("select * from" + tablename, null);
        return cu;
    }
       /* GroupAcc ga[]=new GroupAcc[5];
        int i=0;
        while(cu.moveToNext()&&i<5)
        {
           ga[i].name=cu.getString(0);
           ga[i].amount=cu.getDouble(1);
        }
        int count=5,j=0;
        double sum=0,x=0,y=0;
        for( i=0; i<5; i++)
            sum+=ga[i].amount;
        x=sum/5;
        for(i=0;i<5;i++)
            ga[i].due=x-ga[i].amount;
        while(count==0){
            for(i=0,j=0;i<5&&j<5;i++,j++){
                if ((ga[i].due < 0 && ga[j].due > 0)) {
                    if ((-1 * ga[i].due) <= ga[j].due) {
                        y = ga[i].due + ga[j].due;
                        LinearLayout lView = new LinearLayout(con);
                        TextView myText = new TextView(con);
                        double z=ga[j].due - y;
                        myText.setText(ga[i].name + " gets " + z + " from " + ga[j]);
                        lView.addView(myText);
                        setContentView(lView);
                    } else {
                        y = ga[j].due;
                        LinearLayout lView = new LinearLayout(con);
                        TextView myText = new TextView(con);
                        myText.setText(ga[i].name + " gets " + y + " from " + ga[j]);
                        lView.addView(myText);
                        setContentView(lView);

                    }
                    ga[i].due = ga[i].due + y;
                    ga[j].due = ga[i].due - y;
                } else break;
            }
        }*/
    }
