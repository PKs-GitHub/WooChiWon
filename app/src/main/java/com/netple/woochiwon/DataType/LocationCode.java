package com.netple.woochiwon.DataType;

import java.util.HashMap;

public class LocationCode {

    public static final HashMap<String, String> SIDOcode = new HashMap<>();
    static {
        SIDOcode.put("전체", "99");
        SIDOcode.put("서울특별시", "11");
        SIDOcode.put("부산광역시", "26");
        SIDOcode.put("대구광역시", "27");
        SIDOcode.put("인천광역시", "28");
        SIDOcode.put("광주광역시", "29");
        SIDOcode.put("대전광역시", "30");
        SIDOcode.put("울산광역시", "31");
        SIDOcode.put("세종특별자치시", "36");
        SIDOcode.put("경기도", "41");
        SIDOcode.put("강원도", "42");
        SIDOcode.put("충청북도", "43");
        SIDOcode.put("충청남도", "44");
        SIDOcode.put("전라북도", "45");
        SIDOcode.put("전라남도", "46");
        SIDOcode.put("경상북도", "47");
        SIDOcode.put("경상남도", "48");
        SIDOcode.put("제주특별자치도", "50");
    };

    public static final HashMap<String, String> SGGcode = new HashMap<>();
    static {
        //서울특별시
        SGGcode.put("중구", "11140");
        SGGcode.put("영등포구", "11560");
        SGGcode.put("중랑구", "11260");
        SGGcode.put("은평구", "11380");
        SGGcode.put("금천구", "11545");
        SGGcode.put("동작구", "11590");
        SGGcode.put("강남구", "11680");
        SGGcode.put("종로구", "11110");
        SGGcode.put("용산구", "11170");
        SGGcode.put("광진구", "11215");
        SGGcode.put("양천구", "11470");
        SGGcode.put("관악구", "11620");
        SGGcode.put("서대문구", "11410");
        SGGcode.put("마포구", "11440");
        SGGcode.put("성동구", "11200");
        SGGcode.put("강북구", "11305");
        SGGcode.put("서초구", "11650");
        SGGcode.put("송파구", "11710");
        SGGcode.put("강동구", "11740");
        SGGcode.put("성북구", "11290");
        SGGcode.put("도봉구", "11320");
        SGGcode.put("노원구", "11350");
        SGGcode.put("강서구", "11500");
        SGGcode.put("구로구", "11530");
        SGGcode.put("동대문구", "11230");


        //부산광역시
        SGGcode.put("부산진구", "26230");
        SGGcode.put("사상구", "26530");
        SGGcode.put("서구", "26140");
        SGGcode.put("연제구", "26470");
        SGGcode.put("사하구", "26380");
        SGGcode.put("강서구", "26440");
        SGGcode.put("중구", "26110");
        SGGcode.put("동래구", "26260");
        SGGcode.put("수영구", "26500");
        SGGcode.put("동구", "26170");
        SGGcode.put("금정구", "26410");
        SGGcode.put("남구", "26290");
        SGGcode.put("영도구", "26200");
        SGGcode.put("해운대구", "26350");
        SGGcode.put("기장군", "26710");
        SGGcode.put("북구", "26320");

        //대구광역시
        SGGcode.put("달성군", "27710");
        SGGcode.put("남구", "27200");
        SGGcode.put("서구", "27170");
        SGGcode.put("달서구", "27290");
        SGGcode.put("수성구", "27260");
        SGGcode.put("중구", "27110");
        SGGcode.put("북구", "27230");
        SGGcode.put("동구", "27140");

        //인천광역시
        SGGcode.put("미추홀구", "28177");
        SGGcode.put("강화군", "28710");
        SGGcode.put("동구", "28140");
        SGGcode.put("옹진군", "28720");
        SGGcode.put("부평구", "28237");
        SGGcode.put("남동구", "28200");
        SGGcode.put("계양구", "28245");
        SGGcode.put("서구", "28260");
        SGGcode.put("연수구", "28185");
        SGGcode.put("중구", "28110");

        //광주광역시
        SGGcode.put("북구", "29170");
        SGGcode.put("서구", "29140");
        SGGcode.put("남구", "29155");
        SGGcode.put("광산구", "29200");
        SGGcode.put("동구", "29110");

        //대전광역시
        SGGcode.put("대덕구", "30230");
        SGGcode.put("동구", "30110");
        SGGcode.put("서구", "30170");
        SGGcode.put("중구", "30140");
        SGGcode.put("유성구", "30200");

        //울산광역시
        SGGcode.put("중구", "31110");
        SGGcode.put("동구", "31170");
        SGGcode.put("남구", "31140");
        SGGcode.put("울주군", "31710");
        SGGcode.put("북구", "31200");

        //세종특별자치시
        SGGcode.put("세종특별자치시", "36110");

        //경기도
        SGGcode.put("수원시 장안구", "41111");
        SGGcode.put("파주시", "41480");
        SGGcode.put("의정부시", "41150");
        SGGcode.put("남양주시", "41360");
        SGGcode.put("오산시", "41370");
        SGGcode.put("시흥시", "41390");
        SGGcode.put("용인시 처인구", "41461");
        SGGcode.put("부천시", "41190");
        SGGcode.put("부천시 소사구", "41197");
        SGGcode.put("안성시", "41550");
        SGGcode.put("화성시", "41590");
        SGGcode.put("김포시", "41570");
        SGGcode.put("여주시", "41670");
        SGGcode.put("수원시 팔달구", "41115");
        SGGcode.put("수원시 영통구", "41117");
        SGGcode.put("안양시 동안구", "41173");
        SGGcode.put("광명시", "41210");
        SGGcode.put("평택시", "41220");
        SGGcode.put("안산시 상록구", "41271");
        SGGcode.put("안산시 단원구", "41273");
        SGGcode.put("양주시", "41630");
        SGGcode.put("연천군", "41800");
        SGGcode.put("수원시", "41110");
        SGGcode.put("동두천시", "41250");
        SGGcode.put("용인시 기흥구", "41463");
        SGGcode.put("포천시", "41650");
        SGGcode.put("양평군", "41830");
        SGGcode.put("안양시", "41170");
        SGGcode.put("의왕시", "41430");
        SGGcode.put("성남시", "41130");
        SGGcode.put("성남시 수정구", "41131");
        SGGcode.put("하남시", "41450");
        SGGcode.put("광주시", "41610");
        SGGcode.put("가평군", "41820");
        SGGcode.put("수원시 권선구", "41113");
        SGGcode.put("성남시 분당구", "41135");
        SGGcode.put("고양시 일산서구", "41287");
        SGGcode.put("과천시", "41290");
        SGGcode.put("구리시", "41310");
        SGGcode.put("군포시", "41410");
        SGGcode.put("용인시 수지구", "41465");
        SGGcode.put("이천시", "41500");
        SGGcode.put("안양시 만안구", "41171");
        SGGcode.put("고양시 덕양구", "41281");
        SGGcode.put("고양시 일산동구", "41285");
        SGGcode.put("성남시 중원구", "41133");

        //강원도
        SGGcode.put("삼척시", "42230");
        SGGcode.put("영월군", "42750");
        SGGcode.put("고성군", "42820");
        SGGcode.put("춘천시", "42110");
        SGGcode.put("태백시", "42190");
        SGGcode.put("양구군", "42800");
        SGGcode.put("인제군", "42810");
        SGGcode.put("양양군", "42830");
        SGGcode.put("홍천군", "42720");
        SGGcode.put("정선군", "42770");
        SGGcode.put("강릉시", "42150");
        SGGcode.put("동해시", "42170");
        SGGcode.put("속초시", "42210");
        SGGcode.put("횡성군", "42730");
        SGGcode.put("평창군", "42760");
        SGGcode.put("화천군", "42790");
        SGGcode.put("원주시", "42130");
        SGGcode.put("철원군", "42780");

        //충청북도
        SGGcode.put("제천시", "43150");
        SGGcode.put("괴산군", "43760");
        SGGcode.put("보은군", "43720");
        SGGcode.put("청주시 상당구", "43111");
        SGGcode.put("청주시 청원구", "43114");
        SGGcode.put("영동군", "43740");
        SGGcode.put("청주시 흥덕구", "43113");
        SGGcode.put("증평군", "43745");
        SGGcode.put("음성군", "43770");
        SGGcode.put("단양군", "43800");
        SGGcode.put("충주시", "43130");
        SGGcode.put("옥천군", "43730");
        SGGcode.put("청주시 서원구", "43112");
        SGGcode.put("진천군", "43750");

        //충청남도
        SGGcode.put("보령시", "44180");
        SGGcode.put("홍성군", "44800");
        SGGcode.put("천안시 동남구", "44131");
        SGGcode.put("서산시", "44210");
        SGGcode.put("논산시", "44230");
        SGGcode.put("청양군", "44790");
        SGGcode.put("아산시", "44200");
        SGGcode.put("부여군", "44760");
        SGGcode.put("천안시 서북구", "44133");
        SGGcode.put("태안군", "44825");
        SGGcode.put("계룡시", "44250");
        SGGcode.put("당진시", "44270");
        SGGcode.put("예산군", "44810");
        SGGcode.put("공주시", "44150");
        SGGcode.put("금산군", "44710");
        SGGcode.put("서천군", "44770");

        //전라북도
        SGGcode.put("정읍시", "45180");
        SGGcode.put("고창군", "45790");
        SGGcode.put("임실군", "45750");
        SGGcode.put("전주시 완산구", "45111");
        SGGcode.put("군산시", "45130");
        SGGcode.put("순창군", "45770");
        SGGcode.put("부안군", "45800");
        SGGcode.put("전주시 덕진구", "45113");
        SGGcode.put("익산시", "45140");
        SGGcode.put("완주군", "45710");
        SGGcode.put("장수군", "45740");
        SGGcode.put("진안군", "45720");
        SGGcode.put("김제시", "45210");
        SGGcode.put("남원시", "45190");
        SGGcode.put("무주군", "45730");

        //전라남도
        SGGcode.put("목포시", "46110");
        SGGcode.put("장성군", "46880");
        SGGcode.put("완도군", "46890");
        SGGcode.put("구례군", "46730");
        SGGcode.put("여수시", "46130");
        SGGcode.put("나주시", "46170");
        SGGcode.put("화순군", "46790");
        SGGcode.put("영광군", "46870");
        SGGcode.put("담양군", "46710");
        SGGcode.put("곡성군", "46720");
        SGGcode.put("고흥군", "46770");
        SGGcode.put("장흥군", "46800");
        SGGcode.put("무안군", "46840");
        SGGcode.put("보성군", "46780");
        SGGcode.put("해남군", "46820");
        SGGcode.put("광양시", "46230");
        SGGcode.put("영암군", "46830");
        SGGcode.put("신안군", "46910");
        SGGcode.put("강진군", "46810");
        SGGcode.put("진도군", "46900");
        SGGcode.put("함평군", "46860");
        SGGcode.put("순천시", "46150");

        //경상북도
        SGGcode.put("구미시", "47190");
        SGGcode.put("성주군", "47840");
        SGGcode.put("영덕군", "47770");
        SGGcode.put("고령군", "47830");
        SGGcode.put("칠곡군", "47850");
        SGGcode.put("예천군", "47900");
        SGGcode.put("울진군", "47930");
        SGGcode.put("봉화군", "47920");
        SGGcode.put("포항시 남구", "47111");
        SGGcode.put("영주시", "47210");
        SGGcode.put("경산시", "47290");
        SGGcode.put("군위군", "47720");
        SGGcode.put("청송군", "47750");
        SGGcode.put("청도군", "47820");
        SGGcode.put("울릉군", "47940");
        SGGcode.put("경주시", "47130");
        SGGcode.put("김천시", "47150");
        SGGcode.put("안동시", "47170");
        SGGcode.put("상주시", "47250");
        SGGcode.put("문경시", "47280");
        SGGcode.put("의성군", "47730");
        SGGcode.put("영양군", "47760");
        SGGcode.put("포항시", "47110");
        SGGcode.put("영천시", "47230");
        SGGcode.put("포항시 북구", "47113");

        //경상남도
        SGGcode.put("거제시", "48310");
        SGGcode.put("양산시", "48330");
        SGGcode.put("창녕군", "48740");
        SGGcode.put("고성군", "48820");
        SGGcode.put("함양군", "48870");
        SGGcode.put("사천시", "48240");
        SGGcode.put("의령군", "48720");
        SGGcode.put("산청군", "48860");
        SGGcode.put("합천군", "48890");
        SGGcode.put("창원시 의창구", "48121");
        SGGcode.put("통영시", "48220");
        SGGcode.put("밀양시", "48270");
        SGGcode.put("하동군", "48850");
        SGGcode.put("거창군", "48880");
        SGGcode.put("창원시", "48120");
        SGGcode.put("창원시 마산회원구", "48127");
        SGGcode.put("진주시", "48170");
        SGGcode.put("함안군", "48730");
        SGGcode.put("남해군", "48840");
        SGGcode.put("창원시 성산구", "48123");
        SGGcode.put("창원시 마산합포구", "48125");
        SGGcode.put("창원시 진해구", "48129");
        SGGcode.put("김해시", "48250");

        //제주특별자치도
        SGGcode.put("서귀포시", "50130");
        SGGcode.put("제주시", "50110");
    };


    public String getSIDOcode(String SIDOname) {
        return SIDOcode.get(SIDOname);
    }

    public String getSGGcode(String SGGname) {
        return SGGcode.get(SGGname);
    }

}
/*
        //서울특별시
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "중구", "11140");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "영등포구", "11560");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "중랑구", "11260");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "은평구", "11380");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "금천구", "11545");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "동작구", "11590");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강남구", "11680");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "종로구", "11110");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "용산구", "11170");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "광진구", "11215");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "양천구", "11470");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "관악구", "11620");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "서대문구", "11410");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "마포구", "11440");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "성동구", "11200");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강북구", "11305");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "서초구", "11650");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "송파구", "11710");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강동구", "11740");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "성북구", "11290");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "도봉구", "11320");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "노원구", "11350");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "강서구", "11500");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "구로구", "11530");
        config_location_arr_list.add(new Config_Location("서울특별시", "11", "동대문구", "11230");
        
        
        //부산광역시
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "부산진구", "26230");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "사상구", "26530");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "서구", "26140");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "연제구", "26470");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "사하구", "26380");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "강서구", "26440");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "중구", "26110");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "동래구", "26260");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "수영구", "26500");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "동구", "26170");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "금정구", "26410");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "남구", "26290");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "영도구", "26200");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "해운대구", "26350");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "기장군", "26710");
        config_location_arr_list.add(new Config_Location("부산광역시", "26", "북구", "26320");
        
        //대구광역시
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "달성군", "27710");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "남구", "27200");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "서구", "27170");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "달서구", "27290");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "수성구", "27260");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "중구", "27110");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "북구", "27230");
        config_location_arr_list.add(new Config_Location("대구광역시", "27", "동구", "27140");
        
        //인천광역시
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "미추홀구", "28177");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "강화군", "28710");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "동구", "28140");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "옹진군", "28720");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "부평구", "28237");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "남동구", "28200");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "계양구", "28245");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "서구", "28260");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "연수구", "28185");
        config_location_arr_list.add(new Config_Location("인천광역시", "28", "중구", "28110");
        
        //광주광역시
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "북구", "29170");
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "서구", "29140");
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "남구", "29155");
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "광산구", "29200");
        config_location_arr_list.add(new Config_Location("광주광역시", "29", "동구", "29110");
        
        //대전광역시
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "대덕구", "30230");
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "동구", "30110");
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "서구", "30170");
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "중구", "30140");
        config_location_arr_list.add(new Config_Location("대전광역시", "30", "유성구", "30200");
        
        //울산광역시
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "중구", "31110");
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "동구", "31170");
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "남구", "31140");
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "울주군", "31710");
        config_location_arr_list.add(new Config_Location("울산광역시", "31", "북구", "31200");
        
        //세종특별자치시
        config_location_arr_list.add(new Config_Location("세종특별자치시", "36", "세종특별자치시", "36110");
        
        //경기도
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 장안구", "41111");
        config_location_arr_list.add(new Config_Location("경기도", "41", "파주시", "41480");
        config_location_arr_list.add(new Config_Location("경기도", "41", "의정부시", "41150");
        config_location_arr_list.add(new Config_Location("경기도", "41", "남양주시", "41360");
        config_location_arr_list.add(new Config_Location("경기도", "41", "오산시", "41370");
        config_location_arr_list.add(new Config_Location("경기도", "41", "시흥시", "41390");
        config_location_arr_list.add(new Config_Location("경기도", "41", "용인시 처인구", "41461");
        config_location_arr_list.add(new Config_Location("경기도", "41", "부천시", "41190");
        config_location_arr_list.add(new Config_Location("경기도", "41", "부천시 소사구", "41197");
        config_location_arr_list.add(new Config_Location("경기도", "41", "안성시", "41550");
        config_location_arr_list.add(new Config_Location("경기도", "41", "화성시", "41590");
        config_location_arr_list.add(new Config_Location("경기도", "41", "김포시", "41570");
        config_location_arr_list.add(new Config_Location("경기도", "41", "여주시", "41670");
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 팔달구", "41115");
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 영통구", "41117");
        config_location_arr_list.add(new Config_Location("경기도", "41", "안양시 동안구", "41173");
        config_location_arr_list.add(new Config_Location("경기도", "41", "광명시", "41210");
        config_location_arr_list.add(new Config_Location("경기도", "41", "평택시", "41220");
        config_location_arr_list.add(new Config_Location("경기도", "41", "안산시 상록구", "41271");
        config_location_arr_list.add(new Config_Location("경기도", "41", "안산시 단원구", "41273");
        config_location_arr_list.add(new Config_Location("경기도", "41", "양주시", "41630");
        config_location_arr_list.add(new Config_Location("경기도", "41", "연천군", "41800");
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시", "41110");
        config_location_arr_list.add(new Config_Location("경기도", "41", "동두천시", "41250");
        config_location_arr_list.add(new Config_Location("경기도", "41", "용인시 기흥구", "41463");
        config_location_arr_list.add(new Config_Location("경기도", "41", "포천시", "41650");
        config_location_arr_list.add(new Config_Location("경기도", "41", "양평군", "41830");
        config_location_arr_list.add(new Config_Location("경기도", "41", "안양시", "41170");
        config_location_arr_list.add(new Config_Location("경기도", "41", "의왕시", "41430");
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시", "41130");
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시 수정구", "41131");
        config_location_arr_list.add(new Config_Location("경기도", "41", "하남시", "41450");
        config_location_arr_list.add(new Config_Location("경기도", "41", "광주시", "41610");
        config_location_arr_list.add(new Config_Location("경기도", "41", "가평군", "41820");
        config_location_arr_list.add(new Config_Location("경기도", "41", "수원시 권선구", "41113");
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시 분당구", "41135");
        config_location_arr_list.add(new Config_Location("경기도", "41", "고양시 일산서구", "41287");
        config_location_arr_list.add(new Config_Location("경기도", "41", "과천시", "41290");
        config_location_arr_list.add(new Config_Location("경기도", "41", "구리시", "41310");
        config_location_arr_list.add(new Config_Location("경기도", "41", "군포시", "41410");
        config_location_arr_list.add(new Config_Location("경기도", "41", "용인시 수지구", "41465");
        config_location_arr_list.add(new Config_Location("경기도", "41", "이천시", "41500");
        config_location_arr_list.add(new Config_Location("경기도", "41", "안양시 만안구", "41171");
        config_location_arr_list.add(new Config_Location("경기도", "41", "고양시 덕양구", "41281");
        config_location_arr_list.add(new Config_Location("경기도", "41", "고양시 일산동구", "41285");
        config_location_arr_list.add(new Config_Location("경기도", "41", "성남시 중원구", "41133");
        
        //강원도
        config_location_arr_list.add(new Config_Location("강원도", "42", "삼척시", "42230");
        config_location_arr_list.add(new Config_Location("강원도", "42", "영월군", "42750");
        config_location_arr_list.add(new Config_Location("강원도", "42", "고성군", "42820");
        config_location_arr_list.add(new Config_Location("강원도", "42", "춘천시", "42110");
        config_location_arr_list.add(new Config_Location("강원도", "42", "태백시", "42190");
        config_location_arr_list.add(new Config_Location("강원도", "42", "양구군", "42800");
        config_location_arr_list.add(new Config_Location("강원도", "42", "인제군", "42810");
        config_location_arr_list.add(new Config_Location("강원도", "42", "양양군", "42830");
        config_location_arr_list.add(new Config_Location("강원도", "42", "홍천군", "42720");
        config_location_arr_list.add(new Config_Location("강원도", "42", "정선군", "42770");
        config_location_arr_list.add(new Config_Location("강원도", "42", "강릉시", "42150");
        config_location_arr_list.add(new Config_Location("강원도", "42", "동해시", "42170");
        config_location_arr_list.add(new Config_Location("강원도", "42", "속초시", "42210");
        config_location_arr_list.add(new Config_Location("강원도", "42", "횡성군", "42730");
        config_location_arr_list.add(new Config_Location("강원도", "42", "평창군", "42760");
        config_location_arr_list.add(new Config_Location("강원도", "42", "화천군", "42790");
        config_location_arr_list.add(new Config_Location("강원도", "42", "원주시", "42130");
        config_location_arr_list.add(new Config_Location("강원도", "42", "철원군", "42780");
        
        //충청북도
        config_location_arr_list.add(new Config_Location("충청북도", "43", "제천시", "43150");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "괴산군", "43760");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "보은군", "43720");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 상당구", "43111");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 청원구", "43114");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "영동군", "43740");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 흥덕구", "43113");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "증평군", "43745");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "음성군", "43770");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "단양군", "43800");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "충주시", "43130");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "옥천군", "43730");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "청주시 서원구", "43112");
        config_location_arr_list.add(new Config_Location("충청북도", "43", "진천군", "43750");
        
        //충청남도
        config_location_arr_list.add(new Config_Location("충청남도", "44", "보령시", "44180");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "홍성군", "44800");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "천안시 동남구", "44131");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "서산시", "44210");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "논산시", "44230");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "청양군", "44790");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "아산시", "44200");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "부여군", "44760");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "천안시 서북구", "44133");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "태안군", "44825");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "계룡시", "44250");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "당진시", "44270");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "예산군", "44810");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "공주시", "44150");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "금산군", "44710");
        config_location_arr_list.add(new Config_Location("충청남도", "44", "서천군", "44770");
        
        //전라북도
        config_location_arr_list.add(new Config_Location("전라북도", "45", "정읍시", "45180");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "고창군", "45790");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "임실군", "45750");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "전주시 완산구", "45111");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "군산시", "45130");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "순창군", "45770");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "부안군", "45800");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "전주시 덕진구", "45113");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "익산시", "45140");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "완주군", "45710");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "장수군", "45740");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "진안군", "45720");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "김제시", "45210");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "남원시", "45190");
        config_location_arr_list.add(new Config_Location("전라북도", "45", "무주군", "45730");
        
        //전라남도
        config_location_arr_list.add(new Config_Location("전라남도", "46", "목포시", "46110");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "장성군", "46880");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "완도군", "46890");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "구례군", "46730");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "여수시", "46130");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "나주시", "46170");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "화순군", "46790");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "영광군", "46870");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "담양군", "46710");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "곡성군", "46720");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "고흥군", "46770");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "장흥군", "46800");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "무안군", "46840");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "보성군", "46780");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "해남군", "46820");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "광양시", "46230");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "영암군", "46830");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "신안군", "46910");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "강진군", "46810");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "진도군", "46900");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "함평군", "46860");
        config_location_arr_list.add(new Config_Location("전라남도", "46", "순천시", "46150");
        
        //경상북도
        config_location_arr_list.add(new Config_Location("경상북도", "47", "구미시", "47190");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "성주군", "47840");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영덕군", "47770");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "고령군", "47830");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "칠곡군", "47850");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "예천군", "47900");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "울진군", "47930");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "봉화군", "47920");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "포항시 남구", "47111");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영주시", "47210");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "경산시", "47290");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "군위군", "47720");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "청송군", "47750");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "청도군", "47820");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "울릉군", "47940");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "경주시", "47130");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "김천시", "47150");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "안동시", "47170");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "상주시", "47250");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "문경시", "47280");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "의성군", "47730");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영양군", "47760");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "포항시", "47110");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "영천시", "47230");
        config_location_arr_list.add(new Config_Location("경상북도", "47", "포항시 북구", "47113");
        
        //경상남도
        config_location_arr_list.add(new Config_Location("경상남도", "48", "거제시", "48310");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "양산시", "48330");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창녕군", "48740");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "고성군", "48820");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "함양군", "48870");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "사천시", "48240");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "의령군", "48720");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "산청군", "48860");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "합천군", "48890");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 의창구", "48121");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "통영시", "48220");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "밀양시", "48270");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "하동군", "48850");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "거창군", "48880");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시", "48120");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 마산회원구", "48127");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "진주시", "48170");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "함안군", "48730");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "남해군", "48840");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 성산구", "48123");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 마산합포구", "48125");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "창원시 진해구", "48129");
        config_location_arr_list.add(new Config_Location("경상남도", "48", "김해시", "48250");
        
        //제주특별자치도
        config_location_arr_list.add(new Config_Location("제주특별자치도", "50", "서귀포시", "50130");
        config_location_arr_list.add(new Config_Location("제주특별자치도", "50", "제주시", "50110");
        */
