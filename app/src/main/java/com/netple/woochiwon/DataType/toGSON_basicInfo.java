package com.netple.woochiwon.DataType;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class toGSON_basicInfo {
        
        @SerializedName("kinderInfo") private List<basicInfo> data;

        public List<basicInfo> getbasicInfo() {
                return data;
        }
        
        public class basicInfo {

                @SerializedName("key") private String key; //행 번호
                @SerializedName("officeedu") private String officeedu; //교육청명
                @SerializedName("subofficeedu") private String subofficeedu;        //교육지원청명
                @SerializedName("kindername") private String kindername;      //유치원명
                @SerializedName("establish") private String establish;       //설립유형
                @SerializedName("edate") private String edate;   //설립일
                @SerializedName("odate") private String odate;   //개원일
                @SerializedName("addr") private String addr;    //주소
                @SerializedName("telno") private String telno;   //전화번호
                @SerializedName("hpaddr") private String hpaddr;  //홈페이지
                @SerializedName("opertime") private String opertime;        //운영시간
                @SerializedName("clcnt3") private int clcnt3;  //만3세학급수
                @SerializedName("clcnt4") private int clcnt4;  //만4세학급수
                @SerializedName("clcnt5") private int clcnt5;  //만5세학급수
                @SerializedName("mixclcnt") private int mixclcnt;        //혼합학급수
                @SerializedName("shclcnt") private int shclcnt; //특수학급수
                @SerializedName("ppcnt3") private int ppcnt3;  //만3세원아수
                @SerializedName("ppcnt4") private int ppcnt4;  //만4세원아수
                @SerializedName("ppcnt5") private int ppcnt5;  //만5세원아수
                @SerializedName("mixppcnt") private int mixppcnt;        //혼합원아수
                @SerializedName("shppcnt") private int shppcnt; //특수원아수


                /******************************************
                 * [START] get Methods
                 ******************************************/
                public String getkey() { //행 번호
                        return key;
                }
                public String getofficeedu() { //교육청명
                        return officeedu;
                }
                public String getsubofficeedu() {        //교육지원청명
                        return subofficeedu;
                }
                public String getkindername() {      //유치원명
                        return kindername;
                }
                public String getestablish() {       //설립유형
                        return establish;
                }
                public String getedate() {   //설립일
                        return edate;
                }
                public String getodate() {   //개원일
                        return odate;
                }
                public String getaddr() {    //주소
                        return addr;
                }
                public String gettelno() {   //전화번호
                        return telno;
                }
                public String gethpaddr() {  //홈페이지
                        return hpaddr;
                }
                public String getopertime() {        //운영시간
                        return opertime;
                }
                public int getclcnt3() {  //만3세학급수
                        return clcnt3;
                }
                public int getclcnt4() {  //만4세학급수
                        return clcnt4;
                }
                public int getclcnt5() {  //만5세학급수
                        return clcnt5;
                }
                public int getmixclcnt() { return mixclcnt; }
                public int getshclcnt() { //특수학급수
                        return shclcnt;
                }
                public int getppcnt3() {  //만3세원아수
                        return ppcnt3;
                }
                public int getppcnt4() {  //만4세원아수
                        return ppcnt4;
                }
                public int getppcnt5() {  //만5세원아수
                        return ppcnt5;
                }
                public int getmixppcnt() {        //혼합원아수
                        return mixppcnt;
                }
                public int getshppcnt() { //특수원아수
                        return shppcnt;
                }
                /******************************************
                 * [END] get Methods
                 ******************************************/




                /******************************************
                 * [START] set Methods
                 ******************************************/
                public void setkey(String key) { //행 번호
                        this.key = key;
                }
                public void setofficeedu(String officeedu) { this.officeedu = officeedu; }
                public void setsubofficeedu(String subofficeedu) { this.subofficeedu = subofficeedu; }
                public void setkindername(String kindername) {      //유치원명
                        this.kindername = kindername;
                }
                public void setestablish(String establish) {       //설립유형
                        this.establish = establish;
                }
                public void setedate(String edate) {   //설립일
                        this.edate = edate;
                }
                public void setodate(String odate) {   //개원일
                        this.odate = odate;
                }
                public void setaddr(String addr) {    //주소
                        this.addr = addr;
                }
                public void settelno(String telno) {   //전화번호
                        this.telno = telno;
                }
                public void sethpaddr(String hpaddr) {  //홈페이지
                        this.hpaddr = hpaddr;
                }
                public void setopertime(String opertime) {        //운영시간
                        this.opertime = opertime;
                }
                public void setclcnt3(int clcnt3) {  //만3세학급수
                        this.clcnt3 = clcnt3;
                }
                public void setclcnt4(int clcnt4) {  //만4세학급수
                        this.clcnt4 = clcnt4;
                }
                public void setclcnt5(int clcnt5) {  //만5세학급수
                        this.clcnt5 = clcnt5;
                }
                public void setmixclcnt(int mixclcnt) {        //혼합학급수
                        this.mixclcnt = mixclcnt;
                }
                public void setshclcnt(int shclcnt) { //특수학급수
                        this.shclcnt = shclcnt;
                }
                public void setppcnt3(int ppcnt3) {  //만3세원아수
                        this.ppcnt3 = ppcnt3;
                }
                public void setppcnt4(int ppcnt4) {  //만4세원아수
                        this.ppcnt4 = ppcnt4;
                }
                public void setppcnt5(int ppcnt5) {  //만5세원아수
                        this.ppcnt5 = ppcnt5;
                }
                public void setmixppcnt(int mixppcnt) {        //혼합원아수
                        this.mixppcnt = mixppcnt;
                }
                public void setshppcnt(int shppcnt) { //특수원아수
                        this.shppcnt = shppcnt;
                }
                /******************************************
                 * [END] set Methods
                 ******************************************/
        }
}
