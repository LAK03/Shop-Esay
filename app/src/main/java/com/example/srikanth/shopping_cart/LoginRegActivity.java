package com.example.srikanth.shopping_cart;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Srikanth on 3/6/2017.
 */

public class LoginRegActivity  extends AppCompatActivity{


    Button _signIn;
    Button _Regis;
    EditText _emailText;
    EditText _passwordText;
    EditText _regemail;
    EditText _regpass;
    EditText _confpass;
    EditText _firstName;
    EditText _lastName;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);
        _signIn =(Button) findViewById(R.id.signIn);
        _Regis = (Button) findViewById(R.id.register);
        _emailText= (EditText) findViewById(R.id.emailId);
        _passwordText = (EditText) findViewById(R.id.passwrd);
        _regemail = (EditText) findViewById(R.id.regemailId);
        _regpass = (EditText) findViewById(R.id.regPass);
        _confpass = (EditText) findViewById(R.id.regconfPass);
        _firstName = (EditText) findViewById(R.id.firstName);
        _lastName = (EditText) findViewById(R.id.lastName);


        db = new DatabaseHelper(this);

        _signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyUser();

            }
        });
        _Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    void verifyUser()
    {
        String email = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();
        int c = db.getLogin(email,password);

        Log.d("email",email);
        Log.d("password",password);
        if(c == 1)
        {
            Intent i=new Intent(LoginRegActivity.this,MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
        }

    }
    void registerUser()
    {
        String email = _regemail.getText().toString().trim();
        String passwrd = _regpass.getText().toString().trim();
        String fName = _firstName.getText().toString().trim();
        String lName = _lastName.getText().toString().trim();
        db.insertEntry(email,passwrd,fName,lName);
        Cursor c =db.getDetails(email,passwrd);
        Toast.makeText(this,"Registration Successful", Toast.LENGTH_LONG).show();
    }

    }
