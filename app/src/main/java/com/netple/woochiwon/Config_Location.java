package com.netple.woochiwon;

public class Config_Location {

    String sido_name;
    String sido_code;

    String ssg_name;
    String ssg_code;

    String ro_name;
    String ro_code;
    
    public Config_Location(String sido_name, 
                           String sido_code,
                           String ssg_name,
                           String ssg_code,
                           String ro_name,
                           String ro_code) {

        this.sido_name = sido_name;
        this.sido_code = sido_code;

        this.ssg_name = ssg_name;
        this.ssg_code = ssg_code;

        this.ro_name = ro_name;
        this.ro_code = ro_code;
    }
}
