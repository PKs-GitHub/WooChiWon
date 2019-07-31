package com.netple.woochiwon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.netple.woochiwon.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHashKey();
    }
    //key_hash ==> WC3EqUlxhD2H4Ielnr6zBGEKS2A=
    public void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(MainActivity.this.getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(TAG,"key_hash="+Base64.encodeToString(md.digest(), Base64.DEFAULT));
                Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
            }
        }

        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}