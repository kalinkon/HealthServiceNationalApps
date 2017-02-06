package com.mcc.healthservicefinal.Hospital_Info;

import android.content.Context;

import com.mcc.healthservicefinal.objects.*;

/**
 * Created by LINKON on 1/29/17.
 */

public class HospitalInfo {
    private Context mContext;
    private String strHospitalInfo;
    private HospitalInfoModel hospital;

    public HospitalInfo(Context mContext, String strHospitalInfoInfo){
        this.mContext = mContext;
        this.strHospitalInfo = strHospitalInfoInfo;
    }

    public void transformIntoHospitalModel(){

        String[] arrHospitalInfo = strHospitalInfo.split("\n");
        arrHospitalInfo = modifyData(arrHospitalInfo);
        hospital = new HospitalInfoModel(arrHospitalInfo[0], arrHospitalInfo[1], arrHospitalInfo[2]);
    }

    private String[] modifyData(String[] arrHospitalInfo) {
        for (int index = 0; index<arrHospitalInfo.length; index++){

            String [] str = arrHospitalInfo[index].split(" ");
            StringBuilder builder = new StringBuilder();

            for (int nestedIndex =1; nestedIndex < str.length; nestedIndex++){
                builder.append(str[nestedIndex]);
                builder.append(" ");
            }

            arrHospitalInfo[index] = builder.toString();
        }
        return arrHospitalInfo;
    }

    public HospitalInfoModel getHospitalInfo(){
        return hospital;
    }

}
