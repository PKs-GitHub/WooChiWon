package com.netple.woochiwon.Activity.Common;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.snackbar.Snackbar;
import com.netple.woochiwon.Activity.Account.AccountActivity;
import com.netple.woochiwon.Activity.Account.MyAccountActivity;
import com.netple.woochiwon.Activity.Board.BoardActivity;
import com.netple.woochiwon.Activity.Search.SearchActivity;
import com.netple.woochiwon.Activity.Timeline.TimelineActivity;
import com.netple.woochiwon.GeneralClass.NetworkStatus;
import com.netple.woochiwon.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences appData;
    private String sharedPreferencesData;

    private static MainActivity instance;

    private FragmentManager main_FragmentManager;
    private FragmentTransaction main_FragmentTransaction;
    private Fragment active_Fragment;

    final Fragment fragment1 = TimelineActivity.newInstance();
    final Fragment fragment2 = SearchActivity.newInstance();
    final Fragment fragment3 = BoardActivity.newInstance();
    final Fragment fragment4 = AccountActivity.newInstance();
    final Fragment fragment5 = MyAccountActivity.newInstance();

    public int ScreenWidth;
    public int ScreenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        //앱 설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);

        setContentView(R.layout.activity_main);

        //키보드가 UI 가리는 것 방지
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ScreenWidth = size.x;
        ScreenHeight = size.y;

        int status = NetworkStatus.getConnectivityStatus((getApplicationContext()));

        switch (status) {
            case NetworkStatus.TYPE_MOBILE:
                Log.d("###Network Status::", "Mobile Network");
                break;

            case NetworkStatus.TYPE_WIFI:
                Log.d("###Network Status::", "WIFI Network");
                break;

            default:
                Log.d("###Network Status::", "Network not available");
                break;
        }

        main_FragmentManager = getSupportFragmentManager();
        main_FragmentTransaction = main_FragmentManager.beginTransaction();

        main_FragmentManager.beginTransaction().add(R.id.main_frame, fragment4, "4").hide(fragment4).commit();
        main_FragmentManager.beginTransaction().add(R.id.main_frame, fragment3, "3").hide(fragment3).commit();
        main_FragmentManager.beginTransaction().add(R.id.main_frame, fragment2, "2").hide(fragment2).commit();

        main_FragmentManager.beginTransaction().add(R.id.main_frame, fragment1, "1").commit();
        active_Fragment = fragment1;

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static MainActivity getInstance() { return instance; }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fg;

            switch (item.getItemId()) {
                case R.id.bottom_nav_menu_1:
                    main_FragmentManager.beginTransaction().hide(active_Fragment).show(fragment1).commit();
                    active_Fragment = fragment1;

                    return true;

                case R.id.bottom_nav_menu_2:
                    main_FragmentManager.beginTransaction().hide(active_Fragment).show(fragment2).commit();
                    active_Fragment = fragment2;
                    return true;

                case R.id.bottom_nav_menu_3:
                    main_FragmentManager.beginTransaction().hide(active_Fragment).show(fragment3).commit();
                    active_Fragment = fragment3;
                    return true;

                case R.id.bottom_nav_menu_4:
                    main_FragmentManager.beginTransaction().hide(active_Fragment).show(fragment4).commit();
                    active_Fragment = fragment4;
                    return true;

            }
            return false;
        }
    };

/*****************************************************
 * [START] Custom BackKey Listener
 ****************************************************/
    // 뒤로가기 버튼 입력시간이 담길 long 객체
    private long pressedTime = 0;

    public interface OnBackPressedListener {
        void onBackPressed();
    }

    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            //TODO: Perform your logic to pass back press here
            for(Fragment fragment : fragmentList){
                if(fragment.isVisible()){
                    ((OnBackPressedListener)fragment).onBackPressed();

                    break;
                }
            }
        }

        else {
            onBackPressedDefault();
        }
    }

    public void onBackPressedDefault() {

        if ( pressedTime == 0 ) {
            Toast.makeText(getApplicationContext(), "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if (seconds > 2000) {
                Toast.makeText(getApplicationContext(), "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
                Log.e("!!!", "onBackPressed : finish, killProcess");
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }
/*****************************************************
 * [END] Custom BackKey Listener
 ****************************************************/


/*****************************************************
 * [START] SharedPreferences Methods
 ****************************************************/


    public void save(String key, Object data) {

        SharedPreferences.Editor editor = appData.edit();

        if(data instanceof Integer) { editor.putInt(key, (Integer) data); }
        else if(data instanceof String) { editor.putString(key, (String) data); }
        else if(data instanceof Set) { editor.putStringSet(key, (Set<String>) data); }
        else if(data instanceof Boolean) { editor.putBoolean(key, (Boolean) data); }
        else if(data instanceof Long) { editor.putLong(key, (Long) data); }
        else if(data instanceof Float) { editor.putFloat(key, (Float) data); }

        editor.commit();
    }


    public Object load(String key, Object type) {

        if(type instanceof Integer) { return appData.getInt(key, -1); }
        else if(type instanceof String) { return appData.getString(key, null); }
        else if(type instanceof Set) { return appData.getStringSet(key, null); }
        else if(type instanceof Boolean) { return appData.getBoolean(key, false); }
        else if(type instanceof Long) { return appData.getLong(key, -1); }
        else if(type instanceof Float) { return appData.getFloat(key, -1); }

        return null;
    }

    public void clear(String key) {

        SharedPreferences.Editor editor = appData.edit();

        editor.remove(key);

        editor.commit();
    }




/*****************************************************
 * [END] Custom BackKey Listener
 ****************************************************/

}



