package com.example.tapan.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GroupExpenseInput extends AppCompatActivity {
    public void onCreate(Bundle savesInstanceState) {
        final GroupAccdb d = new GroupAccdb(this);
        super.onCreate(savesInstanceState);
        setContentView(R.layout.activity_expense_input_grp);
        Intent intent = getIntent();
        final EditText name = (EditText)findViewById(R.id.name);
        final EditText amt = (EditText)findViewById(R.id.amt);
        name.setText("Not Given Yet");
        amt.setText("Not Given Yet");
        final String namefeed = name.getText().toString();
        final String amtfeed = amt.getText().toString();

            //Toast.makeText(getApplicationContext(),"Enter the name!",Toast.LENGTH_SHORT).show();
        Button Add = (Button)findViewById(R.id.addgrp);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amt.getText().toString().equals("Not Given Yet")&&name.getText().toString().equals("Not Given Yet"))
                    Toast.makeText(getApplicationContext(),"name or amount not given",Toast.LENGTH_SHORT).show();
                else {
                    d.addgrpexp(namefeed, amtfeed);
                    Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
