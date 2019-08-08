package com.netple.woochiwon.DataType;

import com.google.gson.annotations.SerializedName;


public class toGSON_findSggList {

    //@SerializedName("code") private String sggCode;
    @SerializedName("name") public String sggName;

    //public String getSggCode() { return sggCode; }
    public String getSggName() { return sggName; }

    //public void setSggCode(String sggCode) { this.sggCode = sggCode; }
    public void setSggName(String sggName) { this.sggName = sggName; }
}
