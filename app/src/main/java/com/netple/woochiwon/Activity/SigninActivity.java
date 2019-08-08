package com.netple.woochiwon.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.netple.woochiwon.R;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;


public  class SigninActivity extends Fragment {
    public LoginActivity parentFragment;

    private Button email_loginbtn;
    private TextView signup_link;

    private LoginButton kakao_loginbtn;
    private ImageButton fake_kakao_loginbtn;

    private ImageButton fake_google_loginbtn;
    private ImageButton fake_naver_loginbtn;

    //Kakao Login Var
    public kakao_SessionCallBack kakao_sessionCallBack;

    //Google Login Var
    public static final int GOOGLE_RC_SIGN_IN=1;
    private GoogleSignInClient googleSignInClient;

    //Naver Login Var
    public static OAuthLogin mOAuthLoginModule;
    private OAuthLoginHandler mOAuthLoginHandler = new NaverLoginHandler((MainActivity) getActivity());



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        parentFragment = (LoginActivity) SigninActivity.this.getParentFragment();




        /**************************************
         * [START] Kakao Login Init
         **************************************/
        kakao_sessionCallBack = new kakao_SessionCallBack();
        Session.getCurrentSession().addCallback(kakao_sessionCallBack);
        Session.getCurrentSession().checkAndImplicitOpen();
        /**************************************
         * [END] Kakao Login Init
         **************************************/

        /**************************************
         * [START] Google Login Init
         **************************************/
        //Check for exsiting Google Sign in Account
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount((MainActivity) getActivity());

        if(account != null)
            Log.d("###Google Login ID::", account.getId());

        else
            Log.d("###Google Login ID::", "None");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient((MainActivity) getActivity(), gso);
        /**************************************
         * [END] Google Login Init
         **************************************/

        /**************************************
         * [START] Naver Login Init
         **************************************/
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init((MainActivity) getActivity(), "B4p9lfM_7aoNfpgSixRO", "qSbvF1XEdo", "WooChiWon");
        /**************************************
         * [END] Naver Login Init
         **************************************/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_signin, container, false);

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
                Log.d("###getActivity(): ", getActivity().toString());
                parentFragment.email_signin();
                break;

            case R.id.link_signup:
                parentFragment.email_signup();
                break;

            //Kakao 로그인 버튼 리스너
            case R.id.fakebtn_Kakao_login:
                kakao_loginbtn.performClick();
                break;

            //Naver 로그인 버튼 리스너
            case R.id.fakebtn_Naver_login:
                IF_naver_login();
                break;

            //Google 로그인 버튼 리스너
            case R.id.fakebtn_Google_login:
                google_signin();
                break;
        }
    }
    /**************************************
     * [END] Buttons Listener
     **************************************/





    /**************************************
     * [START] Google Login Handler
     **************************************/
    //Google SignInIntent Activity 띄우기
    public void google_signin() {
        Intent signIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, GOOGLE_RC_SIGN_IN);
    }


    //Activity 수행 후 Result 처리
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            return ;

        if(requestCode == GOOGLE_RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            google_handleSignInResult(task);
        }
    }

    //로그인 성공 여부 확인
    private void google_handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("###Google Email::", account.getEmail());
            Log.d("###Google ID::", account.getId());
        } catch(ApiException e) {
            Log.e("###Google Login Err::", e.toString());
        }
    }
    /**************************************
     * [END] Google Login Handler
     **************************************/



    /**************************************
     * [START] Kakao Login Handler
     **************************************/

    public void kakao_request() {

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("###Kakao Err::", "Session Closed Error: " + errorResult.toString());
            }

            @Override
            public void onNotSignedUp() {
                Log.e("###Kakao Err::", "onNotSignedUp");
            }

            @Override
            public void onSuccess(UserProfile result) {
                /*************************************
                 * 로그인 성공시 수행할 루틴
                 ************************************/
                String nickname = result.getNickname();
                long id = result.getId();
                String UUID = result.getUUID();

                Log.d("###Kakao Nickname:: ", nickname + "");
                Log.d("###Kakao ID:: ", id + "");
                Log.d("###Kakao UUID:: ", UUID + "");
            }
        });
    }

    //로그인 버튼 리스너
    private class kakao_SessionCallBack implements ISessionCallback {

        //로그인 성공
        @Override
        public void onSessionOpened() {

            kakao_request();
        }

        //로그인 실패
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("###Kakao Err::", "Session Fail Error: " + exception.getMessage().toString());
        }
    }
    /**************************************
     * [END] Kakao Login Handler
     **************************************/



    /**************************************
     * [START] Naver Login Handler
     **************************************/

    public void IF_naver_login() {
        mOAuthLoginModule.startOauthLoginActivity((MainActivity) getActivity(), mOAuthLoginHandler);
    }

    private static class NaverLoginHandler extends OAuthLoginHandler {
        private final WeakReference<MainActivity> mActivity;

        public NaverLoginHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }


        @Override
        public void run(boolean success) {
            MainActivity activity = mActivity.get();

            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(activity);
                String refreshToken = mOAuthLoginModule.getRefreshToken(activity);
                long expiresAt = mOAuthLoginModule.getExpiresAt(activity);
                String tokenType = mOAuthLoginModule.getTokenType(activity);
                String emailAddress = "";

                String HTTP_response = SigninActivity.HTTP_getUserInfo(tokenType, accessToken);
                JSONObject jsonObject = new JSONObject();

                try {
                    //{"resultcode":"00","message":"success","response":{"id":"37259829","email":"hgfhgfhgf@naver.com"}}
                    emailAddress = HTTP_response.substring( HTTP_response.indexOf("email\":")+7, HTTP_response.indexOf("}")-1 );
                    //emailAddress = jsonObject.getJSONObject("response").getString("email");
                } catch(Exception e) {
                    Log.e("###Naver Err::", e.getMessage());
                }

                Log.d("###Naver Login Token::", accessToken);
                Log.d("###Naver HTTP Result::", emailAddress);
            }
            else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(activity).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(activity);
                Log.e("###Naver Err::", "errorCode:" + errorCode);
            }
        }
    }

    private static String HTTP_getUserInfo(String tokenType, String accessToken) {
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String header = "Bearer " + accessToken;
        String result = "";

        HTTP_NetworkTask networkTask = new HTTP_NetworkTask(apiURL, header);

        try {
            result = networkTask.execute().get();
        } catch(Exception e) {
            Log.e("###Naver Err::", e.getMessage());
        }

        return result;
    }

    private static class HTTP_NetworkTask extends AsyncTask<Void, Void, String> {

        private String apiURL;
        private String header;
        private String result;


        public HTTP_NetworkTask(String apiURL, String header) {
            this.apiURL = apiURL;
            this.header = header;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", header);
                int responseCode = conn.getResponseCode();
                BufferedReader br;

                if(responseCode == 200)
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                else
                    br = br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();
                while( (inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }

                br.close();

                result = response.toString();

            } catch (Exception e) {
                Log.e("###Naver Err::", e.getMessage());
            }

            return result;
        }
    }
    /**************************************
     * [END] Naver Login Handler
     **************************************/
}




    /*CREATE  TABLE `USERS` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `username` VARCHAR(50) NULL ,
        `email` VARCHAR(100) NULL ,
        `nickname` VARCHAR(50) NULL ,
        `sns_id` VARCHAR(255) NULL ,
        `sns_type` varchar(10)  NULL,
        `sns_profile` varchar(255)  NULL,
        `create_date` DATETIME NULL ,
        `modify_date` DATETIME NULL ,
        PRIMARY KEY (`id`) ,
        INDEX `idx1_username` (`username` ASC) ,
        INDEX `idx2_email` (`email` ASC),
        INDEX `idx3_sns_id` (`sns_id` ASC),
        );*/