package com.netple.woochiwon;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.auth.helper.Base64;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    /**********************************************************
     * [START] Var Area
     **********************************************************/
    /*
    @BindView(R.id.btn_Kakao_login)
    Button btn_kakao_login;

    @BindView(R.id.btn_Naver_login)
    Button btn_naver_login;

    @BindView(R.id.btn_Google_login)
    Button btn_google_login;
    */

    //kakao Login Var
    private ImageButton fake_kakao_loginbtn;
    private LoginButton kakao_loginbtn;
    private kakao_SessionCallBack kakao_sessionCallBack;

    //Google Login Var
    public static final int GOOGLE_RC_SIGN_IN=1;
    private GoogleSignInClient googleSignInClient;
    /**********************************************************
     * [END] Var Area
     **********************************************************/




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ButterKnife.bind(this);

        /**************************************
         * [START] Get App Key Hash
         **************************************/
        try {
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);

            for(Signature signature: info.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("URLSafeString: ", Base64.encodeBase64URLSafeString(messageDigest.digest()));
            }
        } catch(Exception e) {
            Log.d("Error: ", "PackageInfo Error: " + e.toString());
        }
        /**************************************
         * [END] Get App Key Hash
         **************************************/




        /**************************************
         * [START] Kakao Login Settings
         **************************************/
        kakao_loginbtn = findViewById(R.id.btn_Kakao_login);
        fake_kakao_loginbtn = findViewById(R.id.fakebtn_Kakao_login);
        fake_kakao_loginbtn.setOnClickListener((view)->{
            onClick(view);
        });

        kakao_sessionCallBack = new kakao_SessionCallBack();
        Session.getCurrentSession().addCallback(kakao_sessionCallBack);
        Session.getCurrentSession().checkAndImplicitOpen();
        /**************************************
         * [END] Kakao Login Settings
         **************************************/


        /**************************************
         * [START] Google Login Settings
         **************************************/
        //Check for exsiting Google Sign in Account
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account != null)
            Log.d("Google Logged in ID:: ", account.getId());

        else
            Log.d("Google Logged in ID:: ", "None");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.btn_Google_login);
        signInButton.setOnClickListener((view)->{
            onClick(view);
        });

        /**************************************
         * [END] Google Login Settings
         **************************************/
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fakebtn_Kakao_login:
                kakao_loginbtn.performClick();
                break;

            //Google 로그인 버튼 리스너
            case R.id.btn_Google_login:
                google_signin();
                break;

        }
    }

    /***************************************
     *
     * Google SignInIntent Activity 띄우기
     *
     ***************************************/
    public void google_signin() {
        Intent signIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, GOOGLE_RC_SIGN_IN);
    }



    /***************************************
     *
     * Activity 수행 후 Result 처리
     *
     ***************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            return ;

        if(requestCode == GOOGLE_RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            google_handleSignInResult(task);
        }
    }

    private void google_handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("Google Account Name:: ", account.getEmail());
            Log.d("Google Account Name::", account.getId());
        } catch(ApiException e) {
            Log.e("google Error:: ", e.toString());
        }
    }

    public void kakao_request() {

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("Error", "Session Closed Error: " + errorResult.toString());
            }

            @Override
            public void onNotSignedUp() {
                Log.e("SessionCallback :: ", "onNotSignedUp");
            }

            @Override
            public void onSuccess(UserProfile result) {
                /*************************************
                 * 로그인 성공시 수행할 루틴
                 ************************************/
                String nickname = result.getNickname();
                long id = result.getId();
                String UUID = result.getUUID();

                Log.e("Profile : ", nickname + "");
                Log.e("Profile : ", id + "");
                Log.e("Profile : ", UUID + "");
            }
        });
    }

    /************************************
     *
     * kakao 로그인 버튼 리스너
     *
     ***********************************/
    private class kakao_SessionCallBack implements ISessionCallback {

        //로그인 성공
        @Override
        public void onSessionOpened() {

            kakao_request();
        }

        //로그인 실패
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("Error", "Session Fail Error: " + exception.getMessage().toString());
        }
    }
}
