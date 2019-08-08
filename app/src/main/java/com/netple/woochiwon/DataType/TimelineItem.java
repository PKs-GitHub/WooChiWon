package com.netple.woochiwon.DataType;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

public class TimelineItem {

    private int timeline_avatar_resId;             //private ImageView avatar
    private String timeline_nickname;               //private TextView nickname
    private String timeline_location;               //private TextView location
    private String timeline_written_time;          //private TextView written_time

    private String timeline_title;                  //private TextView title
    private int timeline_content_img_resId;      //private ImageView content_img
    private String timeline_content_txt;           //private TextView content_txt


    /*****************************************
     * [START] get Methods
     ****************************************/
    public int get_timeline_avatar_resId() {    return timeline_avatar_resId;   }

    public String get_timeline_nickname() { return timeline_nickname;   }

    public String get_timeline_location() { return timeline_location;   }

    public String get_timeline_written_time() { return timeline_written_time;   }

    public String get_timeline_title() { return timeline_title; }

    public int get_timeline_content_img_resId() {   return timeline_content_img_resId;  }

    public String get_timeline_content_txt() {  return timeline_content_txt;    }

    /*****************************************
     * [END] get Methods
     ****************************************/



    /*****************************************
     * [START] set Methods
     ****************************************/
    public void set_timeline_avatar_resId(int timeline_avatar_resId) {    this.timeline_avatar_resId = timeline_avatar_resId;   }

    public void set_timeline_nickname(String timeline_nickname) { this.timeline_nickname = timeline_nickname;   }

    public void set_timeline_location(String timeline_location) { this.timeline_location = timeline_location;   }

    public void set_timeline_written_time(String timeline_written_time) { this.timeline_written_time = timeline_written_time;   }

    public void set_timeline_title(String timeline_title) { this.timeline_title = timeline_title; }

    public void set_timeline_content_img_resId(int timeline_content_img_resId) {    this.timeline_content_img_resId = timeline_content_img_resId;   }

    public void set_timeline_content_txt(String timeline_content_txt) {  this.timeline_content_txt = timeline_content_txt;    }

    /*****************************************
     * [END] set Methods
     ****************************************/


}



/*

// 위에처럼 EditText에 포커스가 갈때 스크롤이 되게 이벤트를 주면
// 사용자가 스크롤 하지않아도 시원하게 화면이 보이게된다

myEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
    @Override public void onFocusChange(View v, boolean hasFocus) {
        if( hasFocus == true ) {
            myScrollView.postDelayed( new Runnable() {
                @Override public void run() {
                    myScrollView.smoothScrollBy(0, 800);
                }

                }, 100);
        }
    }
});

출처: https://devbible.tistory.com/17 [devbible]
*/

