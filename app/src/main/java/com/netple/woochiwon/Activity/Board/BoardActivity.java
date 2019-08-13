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
    private Button button;
    private ProgressBar progressBar;
    private TextView textView;

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

        button = rootView.findViewById(R.id.frag3_btn);
        progressBar = rootView.findViewById(R.id.frag3_progressBar);
        textView = rootView.findViewById(R.id.frag3_tv);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new myAsync().execute();


            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getChildFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 2) {

        } else {
            (MainActivity.getInstance()).onBackPressedDefault();
        }
    }

    class myAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                change_txt();
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBar.setVisibility(View.GONE);
        }
    }


    public void change_txt() {

        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    handler.sendMessage(Message.obtain(handler, 1));

                    SystemClock.sleep(2000);
                }
            });

            t.start();
            t.join();
        } catch(Exception ee) {
            ee.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView.setText(Integer.toString(++cnt));
                    Toast.makeText(getActivity(), "Increasing CNT", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
