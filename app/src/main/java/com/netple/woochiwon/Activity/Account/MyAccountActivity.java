package com.netple.woochiwon.Activity.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netple.woochiwon.R;

public class MyAccountActivity extends Fragment {

    private TextView accountSocialType, accountEmail, signOff;

    private String SocialType = "";
    private String Email = "";

    public static MyAccountActivity newInstance() {
        return new MyAccountActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_myaccount, container, false);

        accountSocialType = (TextView) rootView.findViewById(R.id.accountSocialType);
        accountEmail = (TextView) rootView.findViewById(R.id.accountEmail);
        signOff = (TextView) rootView.findViewById(R.id.linkSignOff);

        signOff.setOnClickListener((view)->{
            onClick(view);
        });

        accountSocialType.setText(SocialType);
        accountEmail.setText(Email);

        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.linkSignOff:
                AccountActivity.getInstance().signOff();
                break;
        }
    }


    public void setSocialType(String SocialType) { this.SocialType = SocialType; }

    public void setEmail(String Email) { this.Email = Email; }
}