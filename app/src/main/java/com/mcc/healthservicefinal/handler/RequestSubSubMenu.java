package com.mcc.healthservicefinal.handler;

import android.content.Context;

import com.mcc.healthservicefinal.constants.AppConsts;
import com.mcc.healthservicefinal.network.BaseNetwork;
import com.mcc.healthservicefinal.objects.SubSubMenu;
import com.mcc.healthservicefinal.parser.SubSubMenuParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by LINKON on 1/29/2017.
 */

public class RequestSubSubMenu extends BaseNetwork{
    private Context mContext;
    private String responseString;
    private String menuIdValue;
    private String subMenuId;
    private ArrayList<SubSubMenu> subSubMenus;

    public RequestSubSubMenu(Context mContext, String menuIdValue,String subMenuId){
        super(mContext);
        this.mContext = mContext;
        this.menuIdValue = menuIdValue;
        this.subMenuId=subMenuId;

    }

    public String getResponseString() {

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

    public ArrayList<SubSubMenu> parseResponseString(String responseString){

        ArrayList<SubSubMenu> SubSubMenus;
        SubSubMenuParser subSubMenuParser = new SubSubMenuParser(mContext);
        subSubMenuParser.parseSubSubMenuString(responseString);
        SubSubMenus = subSubMenuParser.getSubSubMenus();
        return SubSubMenus;

    }

    private HashMap< String,String > setParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        params.put(AppConsts.KEY_MENU_ID, menuIdValue);
        params.put(AppConsts.KEY_SUB_MENU_ID,subMenuId);
        return params;

    }


    public String getUrl() {
        return AppConsts.BASE_URL+ AppConsts.SUB_SUB_MENU_API;
    }

    public ArrayList<SubSubMenu> getSubSubMenus(){
        responseString=getResponseString();
        subSubMenus=parseResponseString(responseString);
        return subSubMenus;
    }
}
