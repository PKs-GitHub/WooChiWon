package com.netple.woochiwon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SearchActivity extends Fragment {

    private final static String kinder_key = "7ec2f18d1fe74920ab726f1df7eb63f3";

    private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    //Window OS: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36




    public static SearchActivity newInstance() {
        return new SearchActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWebData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag2, container, false);
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

    public void getWebData() {

        String url = "http://e-childschoolinfo.moe.go.kr/api/notice/basicInfo.do";

        Document doc;

        try {
            doc = new ConnetingTask().execute(url).get();

            Elements elements = doc.getElementsByClass("kinderInfo");

            for(Element e:elements)
                Log.d("###Element::", e.text());

        } catch (Exception e) {
            Log.e("###getWebData Err::", e.toString());
        }

        Log.d("###BP", "");
    }

    /*************************************************
     * [END] Jsoup Crawling
     *************************************************/

    //AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
    //출처: https://itmir.tistory.com/624 [미르의 IT 정복기]
    private class ConnetingTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... strings) {

            Document doc = null;

            Connection conn = Jsoup
                    .connect(strings[0])
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .timeout(4000);

            try {
                doc = conn
                        .data("key", kinder_key)
                        .data("sidoCode", "11")
                        .data("sggCode", "11140")
                        .get();
            } catch (Exception e) {
                Log.e("###ConnetingTask Err::", e.toString());
            }

            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            super.onPostExecute(doc);
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