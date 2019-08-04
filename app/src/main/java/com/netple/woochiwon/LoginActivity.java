package com.netple.woochiwon;

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

import java.security.MessageDigest;

public class LoginActivity extends Fragment  implements MainActivity.OnBackPressedListener {

    /**********************************************************
     * [START] Var Area
     **********************************************************/

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public enum LoginPlatoform {
        EMAIL, KAKAO, GOOGLE, NAVER
    }

    public String logged_in_email;


    /**********************************************************
     * [END] Var Area
     **********************************************************/

    public static LoginActivity newInstance(){
        return new LoginActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        get_keyHash(this.getActivity().getPackageName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        ( (MainActivity) context).setOnBackPressedListener(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.login_fragment_container, new SigninActivity());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return inflater.inflate(R.layout.activity_login, container, false);
    }

    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.login_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }

    /**************************************
     * [START] Email Login Handler
     **************************************/
    public void email_signin() {
        Log.d("###Test::", "emailsignin()");
    }

    public void email_signup() {

        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.login_fragment_container, new SignupActivity());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBack() {

        fragmentManager = getChildFragmentManager();

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
            MainActivity activity = (MainActivity) getActivity();
            activity.setOnBackPressedListener(null);
            activity.onBackPressed();
        }
    }

    /**************************************
     * [END] Email Login Handler
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