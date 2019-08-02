package com.netple.woochiwon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

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
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.Stack;

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


    //Email Login Var
    private FragmentTransaction fragment_transaction;


    //Kakao Login Var
    public kakao_SessionCallBack kakao_sessionCallBack;

    //Google Login Var
    public static final int GOOGLE_RC_SIGN_IN=1;
    private GoogleSignInClient googleSignInClient;

    //Naver Login Var
    public static OAuthLogin mOAuthLoginModule;
    private OAuthLoginHandler mOAuthLoginHandler = new NaverLoginHandler(this);


    /**********************************************************
     * [END] Var Area
     **********************************************************/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        get_keyHash(this.getPackageName());

        fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.add(R.id.frag_container, new Frag_SignIn());
        fragment_transaction.commit();

        //ButterKnife.bind(this);

        /**************************************
         * [START] Email Login Init
         **************************************/



        /**************************************
         * [END] Email Login Init
         **************************************/


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
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account != null)
            Log.d("###Google Login ID::", account.getId());

        else
            Log.d("###Google Login ID::", "None");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        /**************************************
         * [END] Google Login Init
         **************************************/



        /**************************************
         * [START] Naver Login Init
         **************************************/
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(this, "B4p9lfM_7aoNfpgSixRO", "qSbvF1XEdo", "WooChiWon");

        /**************************************
         * [END] Naver Login Init
         **************************************/

    }



    /**************************************
     * [START] Email Login Handler
     **************************************/
    public void email_signin() {
        Log.d("###Test::", "emailsignin()");
    }

    public void email_signup() {
        fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragment_transaction.replace(R.id.frag_container, new Frag_SignUp());
        fragment_transaction.addToBackStack(null);
        fragment_transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
            Log.d("###Back Btn::", "clicked in signup");

            AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
            alt_bld.setMessage("로그인 화면으로 돌아갈까요?").setCancelable(
                    false).setPositiveButton("예",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Action for 'Yes' Button
                            getSupportFragmentManager().popBackStack();
                        }
                    }).setNegativeButton("아뇨",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Action for 'NO' Button
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = alt_bld.create();
            alert.show();
        }

        else {
            Log.d("###Back Btn::", "clicked in signin");

            AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
            alt_bld.setMessage("어플을 종료할까요?").setCancelable(
                    false).setPositiveButton("예",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Action for 'Yes' Button
                            finish();
                        }
                    }).setNegativeButton("아뇨",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Action for 'NO' Button
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = alt_bld.create();
            alert.show();
        }
    }

    /**************************************
     * [END] Email Login Handler
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

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
        mOAuthLoginModule.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
    }

    private static class NaverLoginHandler extends OAuthLoginHandler {
        private final WeakReference<LoginActivity> mActivity;

        public NaverLoginHandler(LoginActivity activity) {
            mActivity = new WeakReference<LoginActivity>(activity);
        }


        @Override
        public void run(boolean success) {
            LoginActivity activity = mActivity.get();

            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(activity);
                String refreshToken = mOAuthLoginModule.getRefreshToken(activity);
                long expiresAt = mOAuthLoginModule.getExpiresAt(activity);
                String tokenType = mOAuthLoginModule.getTokenType(activity);
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(activity).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(activity);
                Toast.makeText(activity, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**************************************
     * [END] Naver Login Handler
     **************************************/


    /**************************************
     * [START] Get App Key Hash
     **************************************/
    private void get_keyHash(String s) {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(s, PackageManager.GET_SIGNATURES);

            for (Signature signature : packageInfo.signatures) {

                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("###Key Hash::", android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**************************************
     * [END] Get App Key Hash
     **************************************/


}
