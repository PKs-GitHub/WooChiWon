package com.netple.woochiwon.Activity.Board;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.netple.woochiwon.Activity.Common.MainActivity;
import com.netple.woochiwon.R;

public class BoardActivity extends Fragment implements MainActivity.OnBackPressedListener {

    private static int cnt = 0;

    public static BoardActivity newInstance() {
        return new BoardActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_board, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getChildFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 2) {

        } else {
            (MainActivity.getInstance()).onBackPressedDefault();
        }
    }

}
