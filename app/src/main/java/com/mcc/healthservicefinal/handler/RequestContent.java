package com.mcc.healthservicefinal.handler;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.mcc.healthservicefinal.network.BaseNetwork;
import com.mcc.healthservicefinal.constants.AppConsts;
import com.mcc.healthservicefinal.objects.Content;
import com.mcc.healthservicefinal.parser.ContentParser;

/**
 * Created by LINKON on 1/25/2017.
 */
public class RequestContent extends BaseNetwork{

    private Context mContext;
    private String menuIdValue;
    private String subMenuId;
    private String subSubMenuId;
    private String responseString;
    private ArrayList<Content> contents;

    public RequestContent(Context mContext, String menuIdValue){
        super(mContext);
        this.mContext = mContext;
        this.menuIdValue = menuIdValue;

    }
    public RequestContent(Context mContext,String menuIdValue,String subMenuId,String subSubMenuId){
        super(mContext);
        this.menuIdValue=menuIdValue;
        this.subMenuId=subMenuId;
        this.subSubMenuId=subSubMenuId;
    }

    private String getResponseString(){
        setRequestedUrl(getUrl(), AppConsts.REQUEST_METHOD_POST);
        setRequestParams( setParameters() );

        String responseString = null;
        try {
            responseString=super.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return responseString;

    }


    private ArrayList<Content> parseResponseString(String responseString){

        ArrayList<Content> contents ;
        ContentParser contentParser = new ContentParser(mContext);
        contentParser.parse(responseString);
        contents = contentParser.getContents();
        return contents;
    }

    private HashMap<String,String> setParameters() {

        HashMap<String, String>params = new HashMap<>();
        params.put(AppConsts.KEY_APP_ID, AppConsts.VALUE_APP_ID);
        if(menuIdValue!=null) {
            params.put(AppConsts.KEY_MENU_ID, menuIdValue);
        }
        if(subMenuId!=null){
            params.put(AppConsts.KEY_SUB_MENU_ID,subMenuId);
        }
        if(subSubMenuId!=null){
            params.put(AppConsts.KEY_SUB_SUB_MENU_ID,subSubMenuId);
        }
        return params;
    }

    private String getUrl() {
        return AppConsts.BASE_URL+ AppConsts.CONTENT_API;
    }


   public ArrayList<Content> getContent(){
       responseString =getResponseString();
       contents=parseResponseString(responseString);
       return contents;

   }


}

