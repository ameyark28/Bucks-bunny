package com.example.tapan.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Categories extends AppCompatActivity {
    private Button done;

    public void onCheckboxClicked(View view){
        boolean checked=((CheckBox)view).isChecked();
        switch(view.getId()) {
            case R.id.cb1:
                if(checked)
                    setChecked(false);
                else
                    break;

            case R.id.cb2:
                if(checked)
                    setChecked(false);
                else
                    break;
            case R.id.cb3:
                if(checked)
                    setChecked(false);
                else
                    break;
            case R.id.cb4:
                if(checked)
                    setChecked(false);
                else
                     break;
            case R.id.cb5:
                if(checked)
                    setChecked(false);
                else
                     break;
        }
    }

    private void setChecked(boolean b) {
        return;
    }

    @Override
    public void onCreate(Bundle savesInstanceState) {
        super.onCreate(savesInstanceState);
        setContentView(R.layout.activity_category);
        Intent intent = getIntent();

        done=(Button)findViewById(R.id.donebtn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Categories.this,secondpage.class);
                startActivity(in);
            }
        });
    }

}
