package com.netple.woochiwon.DataType;

import com.google.gson.annotations.SerializedName;


public class toGSON_findSggRoList {

    //@SerializedName("code") private String sggroCode;
    @SerializedName("name") public String sggroName;

    //public String getSggRoCode() { return sggroCode; }
    public String getSggRoName() { return sggroName; }

    //public void setSggRoCode(String sggroCode) { this.sggroCode = sggroCode; }
    public void setSggRoName(String sggroName) { this.sggroName = sggroName; }
}
