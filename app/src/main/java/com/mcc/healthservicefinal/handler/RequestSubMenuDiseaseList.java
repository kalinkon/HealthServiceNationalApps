package com.mcc.healthservicefinal.handler;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.mcc.healthservicefinal.constants.AppConsts;
import com.mcc.healthservicefinal.network.BaseNetwork;
import com.mcc.healthservicefinal.objects.SubMenu;
import com.mcc.healthservicefinal.parser.SubMenuParser;


/**
 * Created by LINKON on 1/29/2017.
 */

public class RequestSubMenuDiseaseList extends BaseNetwork {
    private Context mContext;
    private String responseString;
    private String menuIdValue;
    private ArrayList<SubMenu> subMenus;


    public RequestSubMenuDiseaseList(Context mContext,String menuIdValue){
        super(mContext);
        this.mContext = mContext;
        this.menuIdValue = menuIdValue;


    }

    private String getResponseString() {

        setRequestParams(setParameters());
        setRequestedUrl( getUrl(), AppConsts.REQUEST_METHOD_POST);
        String responseString = null;
        try {
            responseString = super.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return responseString;

    }

    private ArrayList<SubMenu> parseResponseString(String responseString){

        ArrayList<SubMenu> Submenus;
        SubMenuParser subMenuParser = new SubMenuParser(mContext);
        subMenuParser.parseSubMenuString(responseString);
        Submenus = subMenuParser.getSubMenus();
        return Submenus;
    }

    private HashMap< String,String > setParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        params.put(AppConsts.KEY_MENU_ID, menuIdValue);
        return params;
    }


    private String getUrl() {
        return AppConsts.BASE_URL+ AppConsts.SUB_MENU_API;
    }
    public ArrayList<SubMenu> getSubMenus(){
        responseString = getResponseString();
        subMenus = parseResponseString(responseString);
        return subMenus;

    }
}
