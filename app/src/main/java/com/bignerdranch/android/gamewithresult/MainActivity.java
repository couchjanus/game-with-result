package com.bignerdranch.android.gamewithresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, email, password, repeatPassword;
    final int MIN_PASSWORD_LENGTH = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInitializations();
    }

    void viewInitializations() {
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeat_password);

        // To show back button in actionbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Checking if the input in form is valid
    protected boolean validateInput() {
        if (firstName.getText().toString().equals("")) {
            firstName.setError("Please Enter First Name");
            return false;
        }
        if (lastName.getText().toString().equals("")) {
            lastName.setError("Please Enter Last Name");
            return false;
        }
        if (email.getText().toString().equals("")) {
            email.setError("Please Enter Email");
            return false;
        }
        if (password.getText().toString().equals("")) {
            password.setError("Please Enter Password");
            return false;
        }
        if (repeatPassword.getText().toString().equals("")) {
            repeatPassword.setError("Please Enter Repeat Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(email.getText().toString())) {
            email.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (password.getText().length() < MIN_PASSWORD_LENGTH) {
            password.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }

        // Checking if repeat password is same
        if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
            repeatPassword.setError("Password does not match");
            return false;
        }
        return true;
    }

    public void signUp(View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            String resFirstName = firstName.getText().toString();
            String resLastName = lastName.getText().toString();
            String resEmail = email.getText().toString();
            String resPassword = password.getText().toString();
            String resRepeatPassword = repeatPassword.getText().toString();

            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            // Here you can call you API

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("name", firstName.getText().toString() + " " + lastName.getText().toString());
            intent.putExtra("email", email.getText().toString());
            startActivity(intent);
        }
    }
}