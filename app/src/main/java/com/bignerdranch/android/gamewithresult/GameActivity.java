package com.bignerdranch.android.gamewithresult;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.net.Uri;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class GameActivity extends AppCompatActivity {



    int currentColor;
    int guessColor;
    int scoreYes = 0;
    int scoreNo = 0;

    String name, email;
    int rating = 0;

    public void displayResult(String result){
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    public void guessYes(View view){
        if (guessColor == currentColor){
            scoreYes++;
        }
    }

    public void guessNo(View view){
        if (guessColor != currentColor){
            scoreNo++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textHeader = findViewById(R.id.header);
        textHeader.setText("Чи співпадає назва кольору зліва з кольором техта зправа?");
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null){
            name = arguments.get("name").toString();
            email = arguments.get("email").toString();
        }
        run();

    }

    public void run(){
        TextView textViewRight = findViewById(R.id.textViewRight);
        TextView textViewLeft = findViewById(R.id.textViewLeft);


            Random rand = new Random();
            currentColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            textViewLeft.setBackgroundColor(currentColor);

            new CountDownTimer(30000,1000){
                public void onTick(long millisUntilFinished){
                    guessColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                    textViewRight.setBackgroundColor(guessColor);
                }
                public void onFinish(){
                    displayResult("All done: ");
                    rating = scoreYes*10 + scoreNo;
                }
            }.start();
    }


    public void toResult(View v){

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("rating", rating);
        startActivity(intent);
    }
}