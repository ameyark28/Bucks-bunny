package com.example.tapan.wallet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GroupPage extends AppCompatActivity {

    AppCompatActivity activity= new AppCompatActivity();

    @Override
    public void onCreate(Bundle savesInstanceState) {
        super.onCreate(savesInstanceState);

        setContentView(R.layout.grouppage);
        Intent intent = getIntent();

        EditText grpname=(EditText)findViewById(R.id.grpname);
        String GrpName=grpname.getText().toString();
        EditText mem1 = (EditText) findViewById(R.id.etmem1);
        String Member1= mem1.getText().toString();
        EditText mem2=(EditText)findViewById(R.id.etmem2);
        String Member2=mem2.getText().toString();
        EditText mem3=(EditText)findViewById(R.id.etmem3);
        String Member3=mem3.getText().toString();
        EditText mem4=(EditText)findViewById(R.id.etmem4);
        String Member4=mem4.getText().toString();
        EditText mem5 = (EditText) findViewById(R.id.etmem5);
        String Member5= mem5.getText().toString();
        Button done1=(Button)findViewById(R.id.done1btn);

        final String[] mem={Member1,Member2,Member3,Member4,Member5};
        done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context=getApplicationContext();
                GroupAccdb gdb=new GroupAccdb(activity);
                gdb.addgrpmems(mem);
                Toast toast=Toast.makeText(context,"Created",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
