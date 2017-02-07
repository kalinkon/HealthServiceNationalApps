package com.mcc.healthservicefinal.handler;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.mcc.healthservicefinal.activity.MainMenuActivity;
import com.mcc.healthservicefinal.constants.AppConsts;
import com.mcc.healthservicefinal.network.BaseNetwork;
import com.mcc.healthservicefinal.objects.MainMenu;
import com.mcc.healthservicefinal.parser.MenuParser;

/**
 * Created by LINKON on 1/25/2017.
 */

public class RequestMainMenu extends BaseNetwork{


    private String responseString;
    ArrayList<MainMenu> menus;
    private Context mContext;

//    public RequestMainMenu(Context mContext){
//        this.mContext = mContext;
//
//    }

    public RequestMainMenu(Context mContext){
        super(mContext);
        this.mContext=mContext;

    }


    private String getResponseString() {

        setRequestParams(setParameters());
        setRequestedUrl( getURL(), AppConsts.REQUEST_METHOD_POST);
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

    private ArrayList<MainMenu> parseResponseString(String responseString){

        ArrayList<MainMenu> menus ;
        MenuParser menuParser = new MenuParser(mContext);
        menuParser.parseMenuString(responseString);
        menus = menuParser.getMenus();
        return menus;
    }



    private HashMap< String,String > setParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        return params;
    }

    private String getURL() {
        return AppConsts.BASE_URL + AppConsts.MENU_API;
    }

    public ArrayList<MainMenu> getMenus(){

        responseString = getResponseString();
        menus=parseResponseString(responseString);
        return menus;
    }


}



