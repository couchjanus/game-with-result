package com.bignerdranch.android.gamewithresult;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    String userName, userEmail;
    int userRating = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null){
            userName = arguments.get("name").toString();
            userEmail = arguments.get("email").toString();
            userRating = Integer.parseInt(arguments.get("rating").toString());
        }

        TextView showName = findViewById(R.id.showName);
        TextView showEmail = findViewById(R.id.showEmail);
        TextView showRating = findViewById(R.id.showRating);

        showName.setText("");
        showEmail.setText("");
        showRating.setText("");
        showName.append(userName);
        showEmail.append(userEmail);
        showRating.append(""+userRating);


    }


}