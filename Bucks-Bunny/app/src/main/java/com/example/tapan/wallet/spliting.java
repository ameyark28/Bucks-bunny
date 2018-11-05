package com.example.tapan.wallet;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class spliting extends AppCompatActivity {
    Context con = getApplicationContext();
    private Button split;
    private EditText amt;
    private EditText per;
    private EditText pay;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spliting);

        GroupAccdb gadb = new GroupAccdb(con);
        //splitingtheamount(gadb);
        amt = (EditText) findViewById(R.id.eamt);
        per = (EditText) findViewById(R.id.eper);
        pay = (EditText) findViewById(R.id.epay);


        split = (Button) findViewById(R.id.esplit);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int val = Integer.parseInt(amt.getText().toString());
                int n = Integer.parseInt(per.getText().toString());
                int ans = val / n;
                //String s = ans.toString();
                pay.setText(ans);


            }
        });

    }
   /* public void splitingtheamount(GroupAccdb gadb) {

        Cursor cu = gadb.splitingcur(con);
        GroupAcc ga[] = new GroupAcc[5];
        int i = 0;
        while (cu.moveToNext() && i < 5) {
            ga[i].name = cu.getString(0);
            ga[i].amount = cu.getDouble(1);
            i++;
        }

        int count = 5, j = 0, k=0;
        double sum = 0, x = 0, y = 0;
        for (i = 0; i < 5; i++)
            sum += ga[i].amount;
        x = sum / 5;
        for (i = 0; i < 5; i++)
            ga[i].due = x - ga[i].amount;
        while (count!=0) {
            for (i = 0, j = 0; i < 5 && j < 5; i++, j++) {
                if ((ga[i].due < 0 && ga[j].due > 0)) {
                    if ((-1 * ga[i].due) <= ga[j].due) {
                        y = ga[i].due + ga[j].due;
                        LinearLayout lView = new LinearLayout(con);
                        TextView myText = new TextView(con);
                        double z = ga[j].due - y;
                        String s=ga[i].name + " gets " + z + " from " + ga[j];
                        myText.setText(s);
                        lView.addView(myText);

                        setContentView(lView);
                    } else {
                        y = ga[j].due;
                        LinearLayout lView = new LinearLayout(con);
                        TextView myText = new TextView(con);
                        String s=ga[i].name + " gets " + y + " from " + ga[j];
                        myText.setText(s);
                        lView.addView(myText);
                        setContentView(lView);
                    }
                    ga[i].due = ga[i].due + y;
                    ga[j].due = ga[i].due - y;
                } else break;
                for(k=0;k<5;k++)
                {
                    count=0;
                    if(ga[k].due==0)
                        count++;
                }
            }


        }
    }
}*/
}