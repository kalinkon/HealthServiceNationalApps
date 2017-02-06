package com.mcc.healthservicefinal.objects;

/**
 * Created by nitul on 1/30/17.
 */

public class HospitalInfoModel {

    private String rehabName;
    private String rehabAdderss;
    private String rehabContactInfo;

    public HospitalInfoModel(String rehabName, String rehabAdderss, String rehabContactInfo){
        this.rehabName = rehabName;
        this.rehabAdderss = rehabAdderss;
        this.rehabContactInfo = rehabContactInfo;
    }

    public String getRehabAdderss() {
        return rehabAdderss;
    }

    public String getRehabContactInfo() {
        return rehabContactInfo;
    }

    public String getRehabName() {
        return rehabName;
    }
}
