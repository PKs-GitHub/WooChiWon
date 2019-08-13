
package com.netple.woochiwon.Activity.Search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.netple.woochiwon.Activity.Common.MainActivity;
import com.netple.woochiwon.DataType.toGSON_basicInfo;
import com.netple.woochiwon.R;

import org.w3c.dom.Text;

import java.util.Iterator;

public class KinderInfoActivity extends Fragment implements MainActivity.OnBackPressedListener {

    private FragmentManager kinderInfo_TabFragmentManager;
    private FragmentTransaction kinderInfo_TabFragmentTransaction;
    private Fragment parentFragment;
    private Fragment activeTabFragment;

    final Fragment tab_fragment1 = KinderInfo_TimelineActivity.newInstance();
    final Fragment tab_fragment2 = KinderInfo_InfoActivity.newInstance();

    private String kinderID;
    private String kinderName;

    private TextView kinderinfoName;
    private TextView kinderinfoLocation;

    //TODO: 유치원 페이지 생성시 Parameter로 전달 필요 (DB Primary Key: KinderID 관리)
    public KinderInfoActivity(String kinderName) {
        this.kinderName = kinderName;
    }


    public static KinderInfoActivity newInstance(String KinderName){
        return new KinderInfoActivity(KinderName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_kinderinfo, container, false);

        kinderinfoName = rootView.findViewById(R.id.kinderinfoName);
        kinderinfoLocation = rootView.findViewById(R.id.kinderinfoLocation);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parentFragment = getParentFragment();

        boolean flagFound = false;
        Iterator itr = ((SearchActivity) parentFragment).getKinderList().iterator();
        while(itr.hasNext()) {

            toGSON_basicInfo.basicInfo item = (toGSON_basicInfo.basicInfo) itr.next();

            if( item.getkindername().indexOf(kinderName) >= 0) {

                kinderinfoName.setText(kinderName);
                kinderinfoLocation.setText(item.getaddr());
                flagFound = true;
                break;
            }
        }

        if(!flagFound) {
            Log.e("###kinderinfo DB Err::", "");
            Toast.makeText(getContext(), "데이터베이스 에러", Toast.LENGTH_SHORT);
        }
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

