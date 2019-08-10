/*

package com.netple.woochiwon.Activity.Search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.netple.woochiwon.Activity.Account.SigninActivity;
import com.netple.woochiwon.Activity.Common.MainActivity;
import com.netple.woochiwon.R;

public class KinderInfoActivity extends Fragment implements MainActivity.OnBackPressedListener {

    private FragmentManager kinderInfo_TabFragmentManager;
    private FragmentTransaction kinderInfo_TabFragmentTransaction;
    private Fragment active_TabFragment;

    final Fragment tab_fragment1 = KinderInfo_TimelineActivity.newInstance();
    final Fragment tab_fragment2 = KinderInfo_InfoActivity.newInstance();

    public static KinderInfoActivity newInstance(){
        return new KinderInfoActivity();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        ( (MainActivity) context).setOnBackPressedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_kinderinfo, container, false);


        fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.login_fragment_container, new SigninActivity());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return rootView;
    }
}

*/