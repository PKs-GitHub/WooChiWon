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

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;

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

    HashMap<String, String> office_hashmap;

    private final static ArrayList<Config_Location> config_location_arr_list;
    static
    {
        /*************************************
         * ="config_location_arr_list.add(new Config_Location("&""""&A4&""", """&B4&""""&", "&""""&C4&""", """&D4&""""&", "&""""", "&""""""&"));"
         *************************************/
        config_location_arr_list = new ArrayList<>();
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "중구", "11140", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "영등포구", "11560", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "중랑구", "11260", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "은평구", "11380", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "금천구", "11545", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "동작구", "11590", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강남구", "11680", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "종로구", "11110", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "용산구", "11170", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "광진구", "11215", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "양천구", "11470", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "관악구", "11620", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "서대문구", "11410", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "마포구", "11440", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "성동구", "11200", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강북구", "11305", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "서초구", "11650", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "송파구", "11710", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강동구", "11740", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "성북구", "11290", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "도봉구", "11320", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "노원구", "11350", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강서구", "11500", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "구로구", "11530", "", ""));
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "동대문구", "11230", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "부산진구", "26230", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "사상구", "26530", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "서구", "26140", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "연제구", "26470", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "사하구", "26380", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "강서구", "26440", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "중구", "26110", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "동래구", "26260", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "수영구", "26500", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "동구", "26170", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "금정구", "26410", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "남구", "26290", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "영도구", "26200", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "해운대구", "26350", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "기장군", "26710", "", ""));
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "북구", "26320", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "달성군", "27710", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "남구", "27200", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "서구", "27170", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "달서구", "27290", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "수성구", "27260", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "중구", "27110", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "북구", "27230", "", ""));
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "동구", "27140", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "미추홀구", "28177", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "강화군", "28710", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "동구", "28140", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "옹진군", "28720", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "부평구", "28237", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "남동구", "28200", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "계양구", "28245", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "서구", "28260", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "연수구", "28185", "", ""));
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "중구", "28110", "", ""));
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "북구", "29170", "", ""));
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "서구", "29140", "", ""));
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "남구", "29155", "", ""));
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "광산구", "29200", "", ""));
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "동구", "29110", "", ""));
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "대덕구", "30230", "", ""));
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "동구", "30110", "", ""));
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "서구", "30170", "", ""));
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "중구", "30140", "", ""));
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "유성구", "30200", "", ""));
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "중구", "31110", "", ""));
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "동구", "31170", "", ""));
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "남구", "31140", "", ""));
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "울주군", "31710", "", ""));
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "북구", "31200", "", ""));
        config_location_arr_list.add(new Config_Location("세종특별자치시", "36", "세종특별자치시", "36110", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 장안구", "41111", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "파주시", "41480", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "의정부시", "41150", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "남양주시", "41360", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "오산시", "41370", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "시흥시", "41390", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "용인시 처인구", "41461", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "부천시", "41190", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "부천시 소사구", "41197", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "안성시", "41550", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "화성시", "41590", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "김포시", "41570", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "여주시", "41670", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 팔달구", "41115", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 영통구", "41117", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "안양시 동안구", "41173", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "광명시", "41210", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "평택시", "41220", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "안산시 상록구", "41271", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "안산시 단원구", "41273", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "양주시", "41630", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "연천군", "41800", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시", "41110", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "동두천시", "41250", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "용인시 기흥구", "41463", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "포천시", "41650", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "양평군", "41830", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "안양시", "41170", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "의왕시", "41430", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시", "41130", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시 수정구", "41131", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "하남시", "41450", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "광주시", "41610", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "가평군", "41820", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 권선구", "41113", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시 분당구", "41135", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "고양시 일산서구", "41287", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "과천시", "41290", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "구리시", "41310", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "군포시", "41410", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "용인시 수지구", "41465", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "이천시", "41500", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "안양시 만안구", "41171", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "고양시 덕양구", "41281", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "고양시 일산동구", "41285", "", ""));
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시 중원구", "41133", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "삼척시", "42230", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "영월군", "42750", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "고성군", "42820", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "춘천시", "42110", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "태백시", "42190", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "양구군", "42800", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "인제군", "42810", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "양양군", "42830", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "홍천군", "42720", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "정선군", "42770", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "강릉시", "42150", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "동해시", "42170", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "속초시", "42210", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "횡성군", "42730", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "평창군", "42760", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "화천군", "42790", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "원주시", "42130", "", ""));
        config_location_arr_list.add(new Config_Location("강원도", "42", "철원군", "42780", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "제천시", "43150", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "괴산군", "43760", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "보은군", "43720", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 상당구", "43111", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 청원구", "43114", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "영동군", "43740", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 흥덕구", "43113", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "증평군", "43745", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "음성군", "43770", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "단양군", "43800", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "충주시", "43130", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "옥천군", "43730", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 서원구", "43112", "", ""));
        config_location_arr_list.add(new Config_Location("충청북도", "43", "진천군", "43750", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "보령시", "44180", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "홍성군", "44800", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "천안시 동남구", "44131", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "서산시", "44210", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "논산시", "44230", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "청양군", "44790", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "아산시", "44200", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "부여군", "44760", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "천안시 서북구", "44133", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "태안군", "44825", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "계룡시", "44250", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "당진시", "44270", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "예산군", "44810", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "공주시", "44150", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "금산군", "44710", "", ""));
        config_location_arr_list.add(new Config_Location("충청남도", "44", "서천군", "44770", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "정읍시", "45180", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "고창군", "45790", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "임실군", "45750", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "전주시 완산구", "45111", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "군산시", "45130", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "순창군", "45770", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "부안군", "45800", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "전주시 덕진구", "45113", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "익산시", "45140", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "완주군", "45710", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "장수군", "45740", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "진안군", "45720", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "김제시", "45210", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "남원시", "45190", "", ""));
        config_location_arr_list.add(new Config_Location("전라북도", "45", "무주군", "45730", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "목포시", "46110", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "장성군", "46880", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "완도군", "46890", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "구례군", "46730", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "여수시", "46130", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "나주시", "46170", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "화순군", "46790", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "영광군", "46870", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "담양군", "46710", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "곡성군", "46720", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "고흥군", "46770", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "장흥군", "46800", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "무안군", "46840", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "보성군", "46780", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "해남군", "46820", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "광양시", "46230", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "영암군", "46830", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "신안군", "46910", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "강진군", "46810", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "진도군", "46900", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "함평군", "46860", "", ""));
        config_location_arr_list.add(new Config_Location("전라남도", "46", "순천시", "46150", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "구미시", "47190", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "성주군", "47840", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영덕군", "47770", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "고령군", "47830", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "칠곡군", "47850", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "예천군", "47900", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "울진군", "47930", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "봉화군", "47920", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "포항시 남구", "47111", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영주시", "47210", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "경산시", "47290", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "군위군", "47720", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "청송군", "47750", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "청도군", "47820", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "울릉군", "47940", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "경주시", "47130", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "김천시", "47150", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "안동시", "47170", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "상주시", "47250", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "문경시", "47280", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "의성군", "47730", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영양군", "47760", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "포항시", "47110", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영천시", "47230", "", ""));
        config_location_arr_list.add(new Config_Location("경상북도", "47", "포항시 북구", "47113", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "거제시", "48310", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "양산시", "48330", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창녕군", "48740", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "고성군", "48820", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "함양군", "48870", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "사천시", "48240", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "의령군", "48720", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "산청군", "48860", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "합천군", "48890", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 의창구", "48121", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "통영시", "48220", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "밀양시", "48270", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "하동군", "48850", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "거창군", "48880", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시", "48120", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 마산회원구", "48127", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "진주시", "48170", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "함안군", "48730", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "남해군", "48840", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 성산구", "48123", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 마산합포구", "48125", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 진해구", "48129", "", ""));
        config_location_arr_list.add(new Config_Location("경상남도", "48", "김해시", "48250", "", ""));
        config_location_arr_list.add(new Config_Location("제주특별자치도", "50", "서귀포시", "50130", "", ""));
        config_location_arr_list.add(new Config_Location("제주특별자치도", "50", "제주시", "50110", "", ""));
    }


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

            String[] elements = doc.body().text().split("\"key\"");

            office_hashmap = new HashMap<>();

            for(int i=1; i<elements.length; i++) {


                Log.d("###BP", "");
                //element.indexOf("addr")



            }


        } catch (Exception e) {
            Log.e("###getWebData Err::", e.toString());
        }


    }

    /*************************************************
     * [END] Jsoup Crawling
     *************************************************/

    //AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
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