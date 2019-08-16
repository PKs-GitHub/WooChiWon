package com.netple.woochiwon.Activity.Timeline;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netple.woochiwon.Activity.Common.MainActivity;
import com.netple.woochiwon.DataType.TimelineItem;
import com.netple.woochiwon.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimelineActivity extends Fragment implements MainActivity.OnBackPressedListener {

    float scale;

    private ProgressBar progressBar;

    private LinkedHashMap<String, String> sidoHashMap;
    private ArrayList<String> sidoList;
    private Spinner sidoSpinner;
    private NestedScrollView nestedScrollView;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private final int OFFSET = 'K' - 'A';                      // 한 페이지마다 로드할 데이터 갯수.
    private boolean mLockRecyclerView = false;          // 데이터 불러올때 중복안되게 하기위한 변수
    private boolean initialized_getTimelineList = false;

    public ArrayList<TimelineItem> timeline_list;

    private static int page = 0;

    public static TimelineActivity newInstance() {
        return new TimelineActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timeline_list = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_timeline, container, false);

        progressBar =(ProgressBar) rootView.findViewById(R.id.search_progressBar);
        sidoSpinner = (Spinner) rootView.findViewById(R.id.sidoSpinner);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView_timeline_item);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scale = getContext().getResources().getDisplayMetrics().density;

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.d("###Scrolling...", "");

                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if(lastVisible >= totalItemCount -1 && mLockRecyclerView == false && initialized_getTimelineList) {
                    new getTimelineList_AsyncTask().execute();
                }
            }
        });

        getTimelineList();

        sidoHashMap = new LinkedHashMap<>();
        sidoHashMap.put("전체", "");
        Iterator itr = timeline_list.iterator();

        while(itr.hasNext()) {
            String addr = ((TimelineItem) itr.next()).get_timeline_location();

            sidoHashMap.put(addr.split(" ")[0], "");
        }
        sidoList = new ArrayList<>();

        for(Map.Entry entry:sidoHashMap.entrySet())
            sidoList.add((String) entry.getKey());

        sidoSpinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sidoList));

        sidoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainActivity) getActivity()).save("Timeline_selected_SIDOindex", sidoSpinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { //Do nothing }
            }
        });

        int prev_index = -1;
        ((MainActivity)getActivity()).load("Timeline_selected_SIDOindex", prev_index);

        if(prev_index >= 0)
            sidoSpinner.setSelection(prev_index);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getChildFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 2) {

        } else {
            (MainActivity.getInstance()).onBackPressedDefault();
        }
    }


    //get timeline items from DB
    public void getTimelineList() {

        char ch;

        for(ch = 'A'; ch<'D'; ch++) {

            TimelineItem item = new TimelineItem();

            item.set_timeline_avatar_resId(R.drawable.kakao_default_profile_image);
            item.set_timeline_nickname(Character.toString(ch) +"유치원");
            item.set_timeline_location(Character.toString(ch) +"시 "+Character.toString(ch)+"구 "+Character.toString(ch)+"로 ");
            item.set_timeline_title("샘플" + (ch -'A') + page);
            item.set_timeline_written_time("YYYY MM DD hh:mm");
            item.set_timeline_content_txt("테스트" + (ch -'A') + page);
            switch(((int) ch-'A') % 4) {
                case 0:
                    item.set_timeline_content_img_resId(R.mipmap.testimg1);
                    break;

                case 1:
                    item.set_timeline_content_img_resId(R.mipmap.testimg2);
                    break;

                case 2:
                    item.set_timeline_content_img_resId(R.mipmap.testimg3);
                    break;

                case 3:
                    item.set_timeline_content_img_resId(0);
                    break;
            }

            timeline_list.add(item);
            recyclerAdapter.addItem(item);
        }

        page++;
        initialized_getTimelineList = true;
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

            private ImageView avatar;
            private TextView nickname;
            private TextView location;

            private TextView title;
            private TextView written_time;
            private LinearLayout content_img_container;
            private LinearLayout.LayoutParams layoutParams;
            private ImageView content_img;
            private TextView content_txt;

            ItemViewHolder(View itemView) {

                super(itemView);

                avatar = itemView.findViewById(R.id.kinderinfo_avatar);
                nickname = itemView.findViewById(R.id.kinderinfoName);
                location = itemView.findViewById(R.id.kinderinfoLocation);

                title = itemView.findViewById(R.id.timeline_title);
                written_time = itemView.findViewById(R.id.timeline_written_time);
                content_img_container = itemView.findViewById(R.id.timeline_content_img_container);
                layoutParams = (LinearLayout.LayoutParams) content_img_container.getLayoutParams();

                content_img = itemView.findViewById(R.id.timeline_content_img);
                content_txt = itemView.findViewById(R.id.timeline_content_txt);
            }

            void onBind(TimelineItem item) {
                avatar.setImageResource(item.get_timeline_avatar_resId());
                nickname.setText(item.get_timeline_nickname());
                location.setText(item.get_timeline_location());

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

    class getTimelineList_AsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mLockRecyclerView = true;

            //Disable any touch on the screen
            getActivity().getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.bringToFront();
            progressBar.invalidate();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                getTimelineList();
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mLockRecyclerView = false;
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            //Enable touch on the screen again
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.GONE);
            //recyclerView.setNestedScrollingEnabled(true);
        }
    }
}