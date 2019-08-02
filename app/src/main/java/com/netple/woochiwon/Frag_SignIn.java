package com.netple.woochiwon;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kakao.usermgmt.LoginButton;


public  class Frag_SignIn extends Fragment {
    private LoginActivity loginActivity;

    private Button email_loginbtn;
    private TextView signup_link;

    private LoginButton kakao_loginbtn;
    private ImageButton fake_kakao_loginbtn;

    private ImageButton fake_google_loginbtn;
    private ImageButton fake_naver_loginbtn;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        loginActivity = (LoginActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_signin, container, false);

        email_loginbtn = (Button) rootView.findViewById(R.id.btn_email_login);
        email_loginbtn.setOnClickListener((view)->{
            onClick(view);
        });

        signup_link = (TextView) rootView.findViewById(R.id.link_signup);
        signup_link.setOnClickListener((view)->{
            onClick(view);
        });



        kakao_loginbtn = rootView.findViewById(R.id.btn_Kakao_login);


        fake_kakao_loginbtn = rootView.findViewById(R.id.fakebtn_Kakao_login);
        fake_kakao_loginbtn.setOnClickListener((view)->{
            onClick(view);
        });



        fake_google_loginbtn = rootView.findViewById(R.id.fakebtn_Google_login);
        fake_google_loginbtn.setOnClickListener((view)->{
            onClick(view);
        });


        fake_naver_loginbtn = rootView.findViewById(R.id.fakebtn_Naver_login);
        fake_naver_loginbtn.setOnClickListener((view)->{
            onClick(view);
        });

        return rootView;
    }

    /**************************************
     * [START] Buttons Listener
     **************************************/
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_email_login:
                loginActivity.email_signin();
                break;

            case R.id.link_signup:
                loginActivity.email_signup();
                break;

            //Kakao 로그인 버튼 리스너
            case R.id.fakebtn_Kakao_login:
                kakao_loginbtn.performClick();
                break;

            //Naver 로그인 버튼 리스너
            case R.id.fakebtn_Naver_login:
                loginActivity.IF_naver_login();
                break;

            //Google 로그인 버튼 리스너
            case R.id.fakebtn_Google_login:
                loginActivity.google_signin();
                break;
        }
    }
    /**************************************
     * [END] Buttons Listener
     **************************************/

}