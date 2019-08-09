package com.netple.woochiwon.Activity;

import android.content.Context;
import android.icu.util.TimeZone;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.netple.woochiwon.DataType.LocationCode;
import com.netple.woochiwon.DataType.toGSON_basicInfo;
import com.netple.woochiwon.DataType.toGSON_findSggRoList;
import com.netple.woochiwon.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.lang.reflect.Array;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class SearchActivity extends Fragment {

    private SearchActivity searchActivity = this;
    private InputMethodManager imm;

    private ProgressBar progressBar;

    private EditText searchEdittext;

    private Spinner sidoSpinner, sggSpinner, roSpinner;

    private Button searchbtn;



    private final static ArrayList<String> SIDO_list = new ArrayList<>(
            Arrays.asList("전체", "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도")
    );
    private ArrayList<String> SGG_list, RO_list;

    private final static String kinder_key = "7ec2f18d1fe74920ab726f1df7eb63f3";
    private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    //Window OS: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36

    private LocationCode locationCode = new LocationCode();
    public ArrayList<toGSON_basicInfo.basicInfo> kinderInfo_list;


    public static SearchActivity newInstance() { return new SearchActivity(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Soft keyboard auto on/off control
        imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);

        //set progressbar



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_search, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        searchEdittext = (EditText) rootView.findViewById(R.id.searchfield);
        searchEdittext.setFocusableInTouchMode(true);

        sidoSpinner = (Spinner) rootView.findViewById(R.id.sido_spinner);
        sggSpinner = (Spinner) rootView.findViewById(R.id.sgg_spinner);
        roSpinner = (Spinner) rootView.findViewById(R.id.ro_spinner);

        searchbtn = (Button) rootView.findViewById(R.id.search_btn);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                Log.d("###BP:::", "onFocusChange b:" + b);
                if(!b) {
                    imm.hideSoftInputFromWindow(searchEdittext.getWindowToken(),0);
                }

                else if(b) {
                    imm.showSoftInput(searchEdittext, 0);
                }
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getKinderList();
            }
        });


        sidoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(sidoSpinner.getSelectedItem().toString().indexOf("전체") >= 0) {
                    sggSpinner.setSelection(0, false);
                    sggSpinner.setVisibility(Spinner.INVISIBLE);
                }

                else {
                    setSGGList();
                    sggSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, SGG_list));
                    sggSpinner.setSelection(0, false);

                    sggSpinner.setVisibility(Spinner.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        sggSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if( sidoSpinner.getSelectedItem().toString().indexOf("전체") >= 0 || sggSpinner.getSelectedItem().toString().indexOf("전체") >= 0 ) {
                    roSpinner.setSelection(0, false);
                    roSpinner.setVisibility(Spinner.INVISIBLE);
                }

                else {
                    setROList();
                    roSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, RO_list));
                    roSpinner.setSelection(0, false);

                    roSpinner.setVisibility(Spinner.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        roSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //do nothing

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //get Kinder informaion from web
        //getKinderList();

    }


    public void setSGGList() {

        SGG_list = new ArrayList<>();

        String selected_SIDOname = sidoSpinner.getSelectedItem().toString();

        String URL = "http://e-childschoolinfo.moe.go.kr/code/" + locationCode.getSIDOcode(selected_SIDOname) + "/findSggList.do";

        Document doc;
        Gson gson = new Gson();

        HashMap<String, String> dataParams = new HashMap<>();
        dataParams.put("URL", URL);

        try {
            //get data from web thread start
            doc = new ConnetingTask().execute(dataParams).get();

            toGSON_findSggRoList[] SGGarr = gson.fromJson(doc.text(), toGSON_findSggRoList[].class);

            SGG_list.add("전체");

            for(toGSON_findSggRoList instance:SGGarr)
                SGG_list.add(instance.getSggRoName());

            Log.d("###BP", "");

        } catch (Exception e) {
            Log.e("###getKinderList Err::", e.toString());
        }

    }

    public void setROList() {

        RO_list = new ArrayList<>();

        String selected_SIDOname = sidoSpinner.getSelectedItem().toString();
        String selected_SGGname = sggSpinner.getSelectedItem().toString();


        roSpinner.setVisibility(Spinner.VISIBLE);

        String URL = "http://e-childschoolinfo.moe.go.kr/code/findRoList.do";

        Document doc;
        Gson gson = new Gson();

        HashMap<String, String> dataParams = new HashMap<>();
        dataParams.put("URL", URL);
        dataParams.put("sisggNm", selected_SIDOname + selected_SGGname);

        try {
            //get data from web thread start
            doc = new ConnetingTask().execute(dataParams).get();

            toGSON_findSggRoList[] Roarr = gson.fromJson(doc.text(), toGSON_findSggRoList[].class);

            RO_list.add("전체");
            for(toGSON_findSggRoList instance:Roarr)
                RO_list.add(instance.getSggRoName());

            Log.d("###BP", "");

        } catch (Exception e) {
            Log.e("###getKinderList Err::", e.toString());
        }
    }


    /*************************************************
     * [START] Jsoup Crawling
     *************************************************/

    public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {

        TrustManager[] trustManagers = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustManagers, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }



    public void getKinderList() {

        kinderInfo_list = new ArrayList<>();

        String SIDOcode = locationCode.getSIDOcode(sidoSpinner.getSelectedItem().toString());
        String SGGcode = locationCode.getSGGcode(sggSpinner.getSelectedItem().toString());


        Document doc;

        HashMap<String, String> dataParams = new HashMap<>();
        dataParams.put("URL", "http://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do");
        dataParams.put("key", kinder_key);
        dataParams.put("sidoCode", SIDOcode);
        dataParams.put("sggCode", SGGcode);

        try {
            //get data from web thread start
            doc = new ConnetingTask().execute(dataParams).get();

            toGSON_basicInfo toGSON_basicInfo = new Gson().fromJson(doc.text(), toGSON_basicInfo.class);

            for(toGSON_basicInfo.basicInfo data: toGSON_basicInfo.getbasicInfo())
                kinderInfo_list.add(data);

            Log.d("###BP", "");

        } catch (Exception e) {
            Log.e("###getKinderList Err::", e.toString());
        }
    }

    /*
    public void init_SIDOspinner() {

        SIDOlist = new ArrayList<>(
                Arrays.asList("전체", "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도"
                ,"충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도")
        );

        Collections.sort(SIDOlist);
    }
    */



    /*************************************************
     * [END] Jsoup Crawling
     *************************************************/

    //AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
    private class ConnetingTask extends AsyncTask<HashMap<String, String>, Void, Document> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(ProgressBar.VISIBLE);
            Log.d("###BP::", "progressBar visible set");
        }

        @Override
        protected Document doInBackground(HashMap... args) {

            Log.d("###BP:::", "in doInBackground / progressbar visiblity=" + progressBar.getVisibility());

            HashMap param = args[0];

            Document doc = null;

            Connection conn = Jsoup
                    .connect((String) param.get("URL"))
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .timeout(5000);

            param.remove("URL");

            try {
                doc = conn.data(param).get();

            } catch (Exception e) {
                Log.e("###ConnetingTask Err::", e.toString());
            }



            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            progressBar.setVisibility(ProgressBar.GONE);
        }
    }
}






/*
help.wcw@gamil.com


Open API Key: 7ec2f18d1fe74920ab726f1df7eb63f3

http://e-childschoolinfo.moe.go.kr/kinderMt/combineFind.do

btn: javascript:fn_search(document.forms['combineSearch']

https://www.fun-coding.org/crawl_advance2.html

<form id="combineSearch" action="/kinderMt/combineFind.do" method="post">

전체URL
http://e-childschoolinfo.moe.go.kr/kinderMt/combineFind.do?combineSidoCode=12&combineSggCode=11680&combineRoName=99&organName=&childSidoCode=&childSggCode=&childRoName=99&childEstablishType=99&childServiceCode=99&childOrganName=&kinderSidoCode=&kinderSggCode=&kinderRoName=99&kinderEstablishType=99&kinderServiceCode=99&searchVal=&ittId=&tabNum=1&pageIndex=1

통합 시/도     combineSidoCode
통합 구        combineSggCode=11680&
통합 도로명     combineRoName=99&
통합 기관명(유치원+어린이집 통합 검색)     organName=&
어린이집 시/도    childSidoCode=&
어린이집 구          childSggCode=&
어린이집 로          childRoName=99&
어린이집 설립유형   childEstablishType=99&

어린이집 보육서비스  childServiceCode=99&

어린이집명           childOrganName=&
유치원 시/도     kinderSidoCode=&
유치원 구       kinderSggCode=&
유치원 도로명     kinderRoName=99&
유치원 설립유형    kinderEstablishType=99&
                            <option value="99"  selected="selected">전체 유형</option>
                            <option value="01"  >국립</option>
                            <option value="02"  >공립(단설)</option>
                            <option value="03"  >공립(병설)</option>
                            <option value="04"  >사립(법인)</option>
                            <option value="05"  >사립(사인)</option>
                            <option value="98"  >국공립(전체)</option>
                            <option value="97"  >사립(전체)</option>
유치원 제공서비스   kinderServiceCode=99&
                            <option value="99" selected="selected">제공 서비스</option>
                            <option value="01" >방과후과정</option>
                            <option value="02" >특수학급</option>
                            <option value="03" >아침돌봄</option>
                            <option value="04" >저녁돌봄</option>
                            <option value="05" >온종일돌봄</option>
검색어(안쓰임)       searchVal=&
    ittId=&
검색 탭            tabNum=1&
                            1: 통합
                            2: 어린이집
                            3: 유치원
    pageIndex=1



    <th>시/도</th>
                      <td>
                        <div class="select" title="시/도">
                          <label for="kinderSidoList">전체 시/도</label>
                          <select id="kinderSidoList" class="kinderSidoList" name="kinderSidoCode" title="시/도"><option></option></select>
                        </div>
                      </td>
                      <th>시/군/구</th>
                      <td>
                        <div class="select" title="시/군/구">
                          <label for="kinderSggList">전체 시/군/구</label>
                          <select id="kinderSggList" class="kinderSggList" name="kinderSggCode" title="시/군/구"><option></option></select>
                        </div>
                      </td>
                      <th>도로명</th>
                      <td>
                        <div class="select" title="도로명">
                          <label for="kinderRoList">전체 도로명</label>
                          <select id="kinderRoList" class="kinderRoList" name="kinderRoName" title="도로명">
                          	<option value="99">전체 도로명</option>
                          </select>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <th>설립유형</th>
                      <td>
                        <div class="select" title="설립유형">
                          <label for="kinderEstablishmentType">설립유형 선택</label>
                          <select id="kinderEstablishmentType" class="kinderEstablishmentType" name="kinderEstablishType" title="설립유형 선택">
                            <option value="99"  selected="selected">전체 유형</option>
                            <option value="01"  >국립</option>
                            <option value="02"  >공립(단설)</option>
                            <option value="03"  >공립(병설)</option>
                            <option value="04"  >사립(법인)</option>
                            <option value="05"  >사립(사인)</option>
                            <option value="98"  >국공립(전체)</option>
                            <option value="97"  >사립(전체)</option>
                          </select>
 						</div>
                      </td>
                      <th>제공서비스</th>
                      <td>
                        <div class="select" title="제공서비스">
                          <label for="kinderService">제공 서비스</label>
                          <select id="kinderService" class="kinderService" name="kinderServiceCode" title="제공서비스">
                            <option value="99" selected="selected">제공 서비스</option>
                            <option value="01" >방과후과정</option>
                            <option value="02" >특수학급</option>
                            <option value="03" >아침돌봄</option>
                            <option value="04" >저녁돌봄</option>
                            <option value="05" >온종일돌봄</option>
                          </select>
                        </div>
                      </td>



http://e-childschoolinfo.moe.go.kr/kinderMt/combineFind.do?
    combineSidoCode=11&
    combineSggCode=11680&
    combineRoName=99&
    organName=&
    childSidoCode=&
    childSggCode=&
    childRoName=99&
    childEstablishType=99&
    childServiceCode=99&
    childOrganName=&
    kinderSidoCode=&
    kinderSggCode=&
    kinderRoName=99&
    kinderEstablishType=99&
    kinderServiceCode=99&
    searchVal=&
    ittId=&
    tabNum=1&
    pageIndex=1

kinderInfo : {
            key : 행 번호
            , officeedu : 교육청명
            , subofficeedu : 교육지원청명
            , kindername : 유치원명
            , establish : 설립유형
            , edate : 설립일
            , odate  : 개원일
            , addr : 주소
            , telno : 전화번호
            , hpaddr : 홈페이지
            , opertime : 운영시간
            , clcnt3 : 만3세학급수
            , clcnt4 : 만4세학급수
            , clcnt5 : 만5세학급수
            , mixclcnt : 혼합학급수
            , shclcnt : 특수학급수
            , ppcnt3 : 만3세원아수
            , ppcnt4 : 만4세원아수
            , ppcnt5 : 만5세원아수
            , mixppcnt : 혼합원아수
            , shppcnt : 특수원아수
      }
 */