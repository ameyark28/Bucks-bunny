package com.example.tapan.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signinpage extends AppCompatActivity {

    private final AppCompatActivity activity12 = signinpage.this;
   private EditText ename;
   private EditText eemail;

    final ThreadLocal<Button> SignUp = new ThreadLocal<Button>() {
        @Override
        protected Button initialValue() {
            return (Button) findViewById(R.id.esignup1);
        }
    };

    final ThreadLocal<Userdb> userdb = new ThreadLocal<Userdb>(){
        @Override
      protected Userdb initialValue(){
          return new Userdb(activity12);
      }
    };
    final ThreadLocal<InputValidation> inputValidation = new ThreadLocal<InputValidation>( ) {
        @Override
        protected InputValidation initialValue() {
            return new InputValidation(activity12);
        }
    };

    final ThreadLocal<User> user = new ThreadLocal<User>( ) {
        @Override
        protected User initialValue() {
            return new User();
        }
    };
    final ThreadLocal<View> view = new ThreadLocal<View>( ) {
        @Override
        protected View initialValue() {
            return new View(activity12);
        }
    };
    private final ThreadLocal<EditText> Name = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.ename);
        }
    };
    private final ThreadLocal<EditText> phone = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.ephone);
        }
    };
    private final ThreadLocal<EditText> Email = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.eemail);
        }
    };
    private final ThreadLocal<EditText> password1 = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.epwd1);
        }
    };
    private final ThreadLocal<EditText> password2 = new ThreadLocal<EditText>() {
        @Override
        protected EditText initialValue() {
            return (EditText) findViewById(R.id.epwd2);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signinpage);
        ename=findViewById(R.id.ename);
        SignUp.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.get().getText().toString().equalsIgnoreCase("")||Email.get().getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(signinpage.this, "Username and email compulsory", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (view.getId() == R.id.esignup1) {
                        //create personal database obj to enter values
                        postDataToSQLite();

                        Intent i = new Intent(activity12, secondpage.class);
                        startActivity(i);

                    }
                }

            }
        });
    }

    private void postDataToSQLite(){
        int flag=0;

        if (!inputValidation.get( ).isInputEditTextFilled(Name.get(), "name not given"))
        {
            flag++;
            return;
        }
        if (!inputValidation.get( ).isInputEditTextFilled(Email.get(), "email not given")) {
            flag++;
            return;
        }
        if (!inputValidation.get( ).isInputEditTextFilled(phone.get(), "phone not given")) {
            flag++;
            return;
        }
        if (!inputValidation.get( ).isInputEditTextEmail(Email.get(), "Email not correct")) {
            flag++;
            return;
        }

        if (!inputValidation.get( ).isInputEditTextFilled(password1.get(), "password not given")) {
            flag++;
            return;
        }
        if (!inputValidation.get( ).isInputEditTextMatches(password1.get(), password2.get(),"password doesn't matches") ){
            flag++;
            return;
        }

        if(flag>0) {
            // Snack Bar to show error message that record already exists
            if (!userdb.get( ).checkUser(Name.get( ).getText( ).toString( ).trim( ), password1.get( ).toString( ).trim( ))) {

                user.get( ).setName(Name.get( ).getText( ).toString( ).trim( ));
                user.get( ).setEmail(Email.get( ).getText( ).toString( ).trim( ));
                user.get( ).setPassword(password1.get( ).getText( ).toString( ).trim( ));
                user.get( ).setPh_num(phone.get( ).getText( ).toString( ).trim( ));
                userdb.get( ).addUser(user);

                // Snack Bar to show success message that record saved successfully

            } else Toast.makeText(this, "the user already exsits", Toast.LENGTH_SHORT).show( );

        }
    }
}