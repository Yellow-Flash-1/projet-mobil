package com.example.helpcasamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);

        Intent intent = new Intent(this,
                sharedPreferences.getBoolean("isLoggedIn", false)?
                        HomeActivity.class:
                        LoginActivity.class
                );
        startActivity(intent);
        finish();
    }
}