package com.netple.woochiwon.Activity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.snackbar.Snackbar;
import com.netple.woochiwon.GeneralClass.NetworkStatus;
import com.netple.woochiwon.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager main_FragmentManager;
    private FragmentTransaction main_FragmentTransaction;
    private Fragment active_Fragment;

    final Fragment fragment1 = TimelineActivity.newInstance();
    final Fragment fragment2 = SearchActivity.newInstance();
    final Fragment fragment3 = Frag3Activity.newInstance();
    final Fragment fragment4 = LoginActivity.newInstance();
    final Fragment fragment5 = MyAccountActivity.newInstance();

    public int ScreenWidth;
    public int ScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }

    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        // 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if(mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is not null");
            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
        } else {
            Log.e("!!!", "Listener is null");
            if ( pressedTime == 0 ) {
                Snackbar.make(findViewById(R.id.bottom_navigation),
                        " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            }
            else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if ( seconds > 2000 ) {
                    Snackbar.make(findViewById(R.id.bottom_navigation),
                            " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                    pressedTime = 0 ;
                }
                else {
                    super.onBackPressed();
                    Log.e("!!!", "onBackPressed : finish, killProcess");
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
    }
    /*****************************************************
     * [END] Custom BackKey Listener
     ****************************************************/

}