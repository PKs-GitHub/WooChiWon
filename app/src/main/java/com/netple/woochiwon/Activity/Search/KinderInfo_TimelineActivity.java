package com.netple.woochiwon.Activity.Search;

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

public class KinderInfo_TimelineActivity extends Fragment {

    private Fragment KinderInfoFragment;

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    private String this_kinderinfo_name;
    private String this_kinderinfo_location;

    private float scale;


    public static KinderInfo_TimelineActivity newInstance() {
        return new KinderInfo_TimelineActivity();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        KinderInfoFragment = getParentFragment();

        this_kinderinfo_name = KinderInfoFragment.getView().findViewById(R.id.kinderinfoName).toString();
        this_kinderinfo_location = KinderInfoFragment.getView().findViewById(R.id.kinderinfoLocation).toString();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_kinderinfo_timeline, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView_kinderpage_timeline);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scale = getContext().getResources().getDisplayMetrics().density;


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new KinderInfo_TimelineActivity.RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        load_timeline_items();
    }


    //get timeline items from DB
    public void load_timeline_items() {

        ArrayList<String> timeline_nickname_arr_list = new ArrayList<>();
        ArrayList<String> timeline_location_arr_list = new ArrayList<>();

        ArrayList<String> timeline_title_arr_list = new ArrayList<>();
        ArrayList<String> timeline_written_time_arr_list = new ArrayList<>();
        ArrayList<Integer> timeline_content_img_resId_arr_list = new ArrayList<>();
        ArrayList<String> timeline_content_txt_arr_list = new ArrayList<>();

        /**********************************
         *
         *
         *
         * TODO : get datas from DB
         *
         *
         *
         *
         ***********************************/
        timeline_nickname_arr_list.add("A유치원");
        timeline_nickname_arr_list.add("B유치원");
        timeline_nickname_arr_list.add("C유치원");
        timeline_nickname_arr_list.add("D유치원");
        timeline_nickname_arr_list.add("A유치원");

        timeline_location_arr_list.add("A시 A구 A로");
        timeline_location_arr_list.add("B시 B구 A로");
        timeline_location_arr_list.add("C시 C구 B로");
        timeline_location_arr_list.add("D시 D구 B로");
        timeline_location_arr_list.add("A시 A구 A로");


        timeline_title_arr_list.add("샘플5");
        timeline_title_arr_list.add("샘플4");
        timeline_title_arr_list.add("샘플3 (No Image)");
        timeline_title_arr_list.add("샘플2");
        timeline_title_arr_list.add("샘플1");

        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");
        timeline_written_time_arr_list.add("YYYY MM DD hh:mm");

        timeline_content_img_resId_arr_list.add(R.mipmap.testimg1);
        timeline_content_img_resId_arr_list.add(R.mipmap.testimg2);
        timeline_content_img_resId_arr_list.add(0);
        timeline_content_img_resId_arr_list.add(R.mipmap.testimg3);
        timeline_content_img_resId_arr_list.add(R.mipmap.testimg2);

        timeline_content_txt_arr_list.add("테스트5");
        timeline_content_txt_arr_list.add("테스트4");
        timeline_content_txt_arr_list.add("테스트3");
        timeline_content_txt_arr_list.add("테스트2");
        timeline_content_txt_arr_list.add("테스트1");

        /*********************************
         *
         * TODO: 각 유치원별 고유 ID 관리 필요
         *
         ********************************/
        for(int i=0; i<timeline_nickname_arr_list.size(); i++) {
            if(timeline_nickname_arr_list.get(i).equals(this_kinderinfo_name) &&
                    timeline_location_arr_list.get(i).equals(this_kinderinfo_location)) {

                TimelineItem item = new TimelineItem();

                item.set_timeline_title(timeline_title_arr_list.get(i));
                item.set_timeline_written_time(timeline_written_time_arr_list.get(i));
                item.set_timeline_content_img_resId(timeline_content_img_resId_arr_list.get(i));
                item.set_timeline_content_txt(timeline_content_txt_arr_list.get(i));

                recyclerAdapter.addItem(item);
            }
        }

        recyclerAdapter.notifyDataSetChanged();
    }


    private class RecyclerAdapter extends RecyclerView.Adapter<KinderInfo_TimelineActivity.RecyclerAdapter.ItemViewHolder> {

        private ArrayList<TimelineItem> item_arr_list = new ArrayList<>();

        @NonNull
        @Override
        public KinderInfo_TimelineActivity.RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline_content, parent, false);

            return new KinderInfo_TimelineActivity.RecyclerAdapter.ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull KinderInfo_TimelineActivity.RecyclerAdapter.ItemViewHolder holder, int position) {
            holder.onBind(item_arr_list.get(position));
        }

        public int getItemCount() {
            return item_arr_list.size();
        }

        void addItem(TimelineItem item) {
            item_arr_list.add(item);
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            private TextView title;
            private TextView written_time;
            private LinearLayout content_img_container;
            private LinearLayout.LayoutParams layoutParams;
            private ImageView content_img;
            private TextView content_txt;

            ItemViewHolder(View itemView) {

                super(itemView);

                title = itemView.findViewById(R.id.timeline_title);
                written_time = itemView.findViewById(R.id.timeline_written_time);
                content_img_container = itemView.findViewById(R.id.timeline_content_img_container);
                layoutParams = (LinearLayout.LayoutParams) content_img_container.getLayoutParams();

                content_img = itemView.findViewById(R.id.timeline_content_img);
                content_txt = itemView.findViewById(R.id.timeline_content_txt);
            }

            void onBind(TimelineItem item) {

                title.setText(item.get_timeline_title());
                written_time.setText(item.get_timeline_written_time());
                content_txt.setText(item.get_timeline_content_txt());

                //본문 중 이미지 있을 때
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