package com.netple.woochiwon.Activity.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netple.woochiwon.R;

public class SignUpActivity extends Fragment {


    public static SignUpActivity newInstance() { return new SignUpActivity(); }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_signup, container, false);
    }


}