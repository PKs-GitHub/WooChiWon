package com.netple.woochiwon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.netple.woochiwon.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.main_login_btn);
        login_btn.setOnClickListener((view)->{
            onClick(view);
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.main_login_btn:
                Intent login_intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login_intent);
                break;
        }
    }
}