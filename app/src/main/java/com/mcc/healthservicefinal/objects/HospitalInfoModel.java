package com.mcc.healthservicefinal.objects;

import java.io.Serializable;

/**
 * Created by nitul on 1/30/17.
 */

public class HospitalInfoModel implements Serializable {

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
