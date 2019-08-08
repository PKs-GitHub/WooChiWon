package com.netple.woochiwon.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netple.woochiwon.DataType.TimelineItem;
import com.netple.woochiwon.R;

import java.util.ArrayList;

public class TimelineActivity extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    float scale;

    public static TimelineActivity newInstance() {
        return new TimelineActivity();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_timeline, container, false);



        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView_timeline_item);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scale = getContext().getResources().getDisplayMetrics().density;


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        load_timeline_items();
    }



    //get timeline items from DB
    public void load_timeline_items() {


        /*private int timeline_avatar_resId;             //private ImageView avatar
        private String timeline_nickname;               //private TextView nickname
        private String timeline_location;               //private TextView location
        private String timeline_written_time;          //private TextView written_time

        private String timeline_title;                  //private TextView title
        private int timeline_content_img_resId;      //private ImageView content_img
        private String timeline_content_txt;           //private TextView content_txt*/

        ArrayList<Integer> timeline_avatar_resId_arr_list = new ArrayList<>();
        ArrayList<String> timeline_nickname_arr_list = new ArrayList<>();
        ArrayList<String> timeline_location_arr_list = new ArrayList<>();
        ArrayList<String> timeline_written_time_arr_list = new ArrayList<>();

        ArrayList<String> timeline_title_arr_list = new ArrayList<>();
        ArrayList<Integer> timeline_content_img_resId_arr_list = new ArrayList<>();
        ArrayList<String> timeline_content_txt_arr_list = new ArrayList<>();

        timeline_avatar_resId_arr_list.add(R.drawable.kakao_default_profile_image);
        timeline_avatar_resId_arr_list.add(R.drawable.kakao_default_profile_image);
        timeline_avatar_resId_arr_list.add(R.drawable.kakao_default_profile_image);
        timeline_avatar_resId_arr_list.add(R.drawable.kakao_default_profile_image);

        timeline_nickname_arr_list.add("ABC");
        timeline_nickname_arr_list.add("DEF");
        timeline_nickname_arr_list.add("TTT");
        timeline_nickname_arr_list.add("GHI");

        timeline_location_arr_list.add("AAA BBB CCC");
        timeline_location_arr_list.add("AAA BBB DDD");
        timeline_location_arr_list.add("TTT");
        timeline_location_arr_list.add("AAA ZZZ XXX");

        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");


        timeline_title_arr_list.add("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        timeline_title_arr_list.add("BBBBBBBBBBBBB");
        timeline_title_arr_list.add("T_T");
        timeline_title_arr_list.add("CCCCCCCCCCCCC");

        timeline_content_img_resId_arr_list.add(R.mipmap.testimg1);
        timeline_content_img_resId_arr_list.add(R.mipmap.testimg2);
        timeline_content_img_resId_arr_list.add(0);
        timeline_content_img_resId_arr_list.add(R.mipmap.testimg3);


        timeline_content_txt_arr_list.add("Hi");
        timeline_content_txt_arr_list.add("Hello");
        timeline_content_txt_arr_list.add("QQ");
        timeline_content_txt_arr_list.add("Coooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooool");

        for(int i=0; i<timeline_avatar_resId_arr_list.size(); i++) {

            TimelineItem item = new TimelineItem();

            item.set_timeline_avatar_resId(timeline_avatar_resId_arr_list.get(i));
            item.set_timeline_nickname(timeline_nickname_arr_list.get(i));
            item.set_timeline_location((timeline_location_arr_list.get(i)));
            item.set_timeline_written_time(timeline_written_time_arr_list.get(i));

            item.set_timeline_title(timeline_title_arr_list.get(i));
            item.set_timeline_content_img_resId(timeline_content_img_resId_arr_list.get(i));
            item.set_timeline_content_txt(timeline_content_txt_arr_list.get(i));

            recyclerAdapter.addItem(item);
        }

        recyclerAdapter.notifyDataSetChanged();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

        private ArrayList<TimelineItem> item_arr_list = new ArrayList<>();

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false);

            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.onBind(item_arr_list.get(position));
        }

        public int getItemCount() {
            return item_arr_list.size();
        }

        void addItem(TimelineItem item) {
            item_arr_list.add(item);
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout whole_container;

            private ImageView avatar;
            private TextView nickname;
            private TextView location;
            private TextView written_time;

            private TextView title;
            private LinearLayout content_img_container;
            private LinearLayout.LayoutParams layoutParams;
            private ImageView content_img;
            private TextView content_txt;

            ItemViewHolder(View itemView) {

                super(itemView);

                avatar = itemView.findViewById(R.id.timeline_avatar);
                nickname = itemView.findViewById(R.id.timeline_nickname);
                location = itemView.findViewById(R.id.timeline_location);
                written_time = itemView.findViewById(R.id.timeline_written_time);

                title = itemView.findViewById(R.id.timeline_title);

                content_img_container = itemView.findViewById(R.id.timeline_content_img_container);
                layoutParams = (LinearLayout.LayoutParams) content_img_container.getLayoutParams();

                content_img = itemView.findViewById(R.id.timeline_content_img);
                content_txt = itemView.findViewById(R.id.timeline_content_txt);
            }

            void onBind(TimelineItem item) {
                avatar.setImageResource(item.get_timeline_avatar_resId());
                nickname.setText(item.get_timeline_nickname());
                location.setText(item.get_timeline_location());
                written_time.setText(item.get_timeline_written_time());

                title.setText(item.get_timeline_title());
                content_txt.setText(item.get_timeline_content_txt());

                /*
                if (item.get_timeline_content_img_resId() == 0)
                    content_img.setVisibility(View.GONE);
                */

                if (item.get_timeline_content_img_resId() != 0) {

                    Glide.with(content_img.getContext()).load("").placeholder(item.get_timeline_content_img_resId()).into(content_img);
                    //Glide.with(load(item.get_timeline_content_img_resId()).override(400, 300).into(content_img);

                    layoutParams.height = (int) (250 * scale + 0.5f);
                    content_img_container.setLayoutParams(layoutParams);
                }

                else {
                    content_img.setImageResource(0);
                    //frame.setLayoutParams(not_has_image);
                }
            }
        }
    }
}