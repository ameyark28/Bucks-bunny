package com.example.tapan.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class ExpenseInput extends AppCompatActivity {

    AppCompatActivity activity = new AppCompatActivity();
    public void onCreate(Bundle savesInstanceState) {
        super.onCreate(savesInstanceState);
        setContentView(R.layout.activity_expense_input);
        Intent intent = getIntent( );

            final Personaldb pdb = new Personaldb(activity);
            final Personal per = new Personal( );

            final EditText amount = (EditText) findViewById(R.id.amount);
            final String amtString = amount.getText().toString( );

            amount.setText("Not Given Yet");
            RadioGroup typegrp = (RadioGroup) findViewById(R.id.type);
            RadioGroup categerp = (RadioGroup) findViewById(R.id.category);

            Button done = (Button) findViewById(R.id.done);
            int selecttype = typegrp.getCheckedRadioButtonId( );
            int selectcate = categerp.getCheckedRadioButtonId( );
            Button type = (Button) findViewById(selecttype);
            Button cate = (Button) findViewById(selectcate);

            final String categoryfeed = cate.getText( ).toString( );
            final String typefeed = type.getText( ).toString( );
            done.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {
                    if (amount.getText().toString().equals("Not Given Yet")) {
                        Toast.makeText(getApplicationContext(), "amount not given!", Toast.LENGTH_SHORT).show();
                    } else {
                        pdb.addExpense(per, amtString, categoryfeed, typefeed);
                        Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
