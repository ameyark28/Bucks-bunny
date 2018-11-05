package com.example.tapan.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final AppCompatActivity activity = MainActivity.this;
    final ThreadLocal<User> user = new ThreadLocal<User>( ) {
        @Override
        protected User initialValue() {
            return new User();
        }
    };
    final ThreadLocal<InputValidation> inputValidation = new ThreadLocal<InputValidation>( ) {
        @Override
        protected InputValidation initialValue() {
            return new InputValidation(activity);
        }
    };
    private final ThreadLocal<EditText> Name = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.ename);
        }
    };

    final ThreadLocal<Userdb> userdb = new ThreadLocal<Userdb>( ) {
        @Override
        protected Userdb initialValue() {
            return new Userdb(activity);
        }
    };
    private final ThreadLocal<EditText> Password = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.epwd);
        }
    };

    private final ThreadLocal<Button> Login = new ThreadLocal<Button>() {
        @Override
        protected Button initialValue() {
            return (Button) findViewById(R.id.elogin);
        }
    };

    private final ThreadLocal<Button> SignUp = new ThreadLocal<Button>() {
        @Override
        protected Button initialValue() {
            return (Button) findViewById(R.id.esignup);
        }
    };
    private EditText ename;
    private EditText epwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename = findViewById(R.id.ename);
        Login.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ename.getText().toString().equalsIgnoreCase("")||epwd.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(MainActivity.this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                }
                if(view.getId()==R.id.elogin){
                    verifyFromSQLite();
                    Intent i = new Intent(activity, signinpage.class);
                    startActivity(i);
                }
            }
        });

        SignUp.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.esignup) {
                    Intent i = new Intent(activity, signinpage.class);
                    startActivity(i);

                }
            }
        });
    }

    private void verifyFromSQLite(){
        if (!inputValidation.get( ).isInputEditTextFilled(Name.get(),  "empty email")) {
            return;
        }
        if (!inputValidation.get( ).isInputEditTextEmail(Name.get(),"invalid email")) {
            return;
        }
        if (!inputValidation.get( ).isInputEditTextFilled(Password.get(),  "empty password")) {
            return;
        }

        if (userdb.get( ).checkUser(Name.get().getText().toString().trim()
                , Password.get().getText().toString().trim())) {
            //Intent accountsIntent = new Intent(MainActivity.this,secondpage.class);
            //accountsIntent.putExtra("Name", Name.get().getText().toString().trim());
            //emptyInputEditText();
            //startActivity(accountsIntent);
        } else {
            Toast.makeText(this,"created!",Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText(){
       Name.get().setText(null);
        Password.get().setText(null);
    }
}