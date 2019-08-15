package com.netple.woochiwon.Activity.Account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.netple.woochiwon.Activity.Common.MainActivity;
import com.netple.woochiwon.R;

import java.security.MessageDigest;
import java.util.HashMap;

public class AccountActivity extends Fragment implements MainActivity.OnBackPressedListener {

    private FragmentManager accountFragmentManager;
    private FragmentTransaction accountFragmentTransaction;
    private Fragment activeFragment;

    private static AccountActivity instance;
    final SignInActivity signInActivity = SignInActivity.newInstance();
    final MyAccountActivity myAccountActivity = MyAccountActivity.newInstance();

    final Fragment fragment1 = signInActivity;
    final Fragment fragment2 = myAccountActivity;

    private String accountSocialType, accountEmail;

    public enum LoginPlatoform {
        EMAIL, KAKAO, GOOGLE, NAVER
    }

    public String logged_in_email;


    public static AccountActivity newInstance(){ return new AccountActivity(); }

    public static AccountActivity getInstance() { return instance; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;
        get_keyHash(this.getActivity().getPackageName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //( (MainActivity) context).setOnBackPressedListener(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_account, container, false);

        accountFragmentManager = getChildFragmentManager();
        accountFragmentTransaction = accountFragmentManager.beginTransaction();
        //accountFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);



        switch(checkSignedIn()) {

            //Commit SignInActivity Fragment
            case 1:
                accountFragmentManager.beginTransaction().add(R.id.accountFrameLayout, fragment2, "MyAccount").hide(fragment2);
                accountFragmentManager.beginTransaction().add(R.id.accountFrameLayout, fragment1, "SignIn").show(fragment1).commit();
                activeFragment = fragment1;
                break;

            //Commit MyAccountActivity Fragment
            case 2:
                accountFragmentManager.beginTransaction().add(R.id.accountFrameLayout, fragment1, "SignIn").hide(fragment1);
                accountFragmentManager.beginTransaction().add(R.id.accountFrameLayout, fragment2, "MyAccount").show(fragment2).commit();


                activeFragment = fragment2;
                break;
        }


        return rootView;
    }



/**************************************
 * [START] Email Login Handler
 **************************************/
    public void email_signin() {

    }

    public void email_signup() {

        //accountFragmentTransaction = getChildFragmentManager().beginTransaction();
        accountFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        accountFragmentTransaction.replace(R.id.login_fragment_container, new SignUpActivity());
        accountFragmentTransaction.addToBackStack(null);
        accountFragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getChildFragmentManager();

        //getBackStackEntryCount() == 2) => in SignUpActivity
        if(fragmentManager.getBackStackEntryCount() == 2) {

            Log.d("###Back Btn::", "clicked in signup");

            AlertDialog.Builder alt_bld = new AlertDialog.Builder(getActivity());
            alt_bld.setMessage("로그인 화면으로 돌아갈까요?").setCancelable(
                    false).setPositiveButton("네!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Action for 'Yes' Button
                            fragmentManager.popBackStack();
                        }
                    }).setNegativeButton("아니요!",
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
            (MainActivity.getInstance()).onBackPressedDefault();
        }
    }


/**************************************
 * [END] Email Login Handler
 **************************************/

/**************************************
 * [START] Get App Key Hash
 **************************************/

    private int checkSignedIn() {

        HashMap<String, String> lastMap = new HashMap<>();


        lastMap = signInActivity.getLastSignIn();

        accountSocialType = lastMap.keySet().iterator().next();

        if(accountSocialType.length() > 0) {
            accountEmail = lastMap.get(accountSocialType);

            myAccountActivity.setSocialType(accountSocialType);
            myAccountActivity.setEmail(accountEmail);

            return 2;
        }

        else
            return 1;
    }

    public void signOff() {

        MainActivity.getInstance().clear("LastSignInType");
        MainActivity.getInstance().clear("LastSignInEmail");
        MainActivity.getInstance().showAllPref();

        accountFragmentTransaction = accountFragmentManager.beginTransaction();
        accountFragmentTransaction.replace(R.id.accountFrameLayout, fragment1).commit();
    }



/**************************************
 * [END] Get App Key Hash
 **************************************/




/**************************************
 * [START] Get App Key Hash
 **************************************/
    private void get_keyHash(String s) {
        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(s, PackageManager.GET_SIGNATURES);

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