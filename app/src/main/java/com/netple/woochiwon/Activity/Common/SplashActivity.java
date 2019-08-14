package com.netple.woochiwon.Activity.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public SharedPreferences appData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        appData = getSharedPreferences("appData", MODE_PRIVATE);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}