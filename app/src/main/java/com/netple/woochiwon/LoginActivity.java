package com.netple.woochiwon;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_Kakao_login)
    Button btn_kakao_login;

    @BindView(R.id.btn_Naver_login)
    Button btn_naver_login;

    @BindView(R.id.btn_Google_login)
    Button btn_google_login;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    public void initKakao() {

    }


}
