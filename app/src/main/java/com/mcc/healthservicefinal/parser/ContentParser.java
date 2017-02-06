package com.mcc.healthservicefinal.parser;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


import com.mcc.healthservicefinal.constants.ParseKey;
import com.mcc.healthservicefinal.objects.Content;

/**
 * Created by LINKON on 1/25/2017.
 */

public class ContentParser {
    private Context mContext;
    private ArrayList<Content> contents;

    public ContentParser(Context mContext){
        this.mContext = mContext;
    }

    public void parse(String response){
        contents = new ArrayList<>();

        try {

            if (response!= null && !response.isEmpty()){
                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_CONTENT_LIST);

                for (int index = 0; index < jsonArray.length(); index++){
                    JSONObject jsonObject = jsonArray.getJSONObject(index);

                    if (jsonObject != null){
                        String contentId = null, title = null, details = null,img=null;

                        if (jsonObject.has(ParseKey.KEY_CONTENT_ID))
                            contentId = jsonObject.getString(ParseKey.KEY_CONTENT_ID);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_TITLE))
                            title = jsonObject.getString(ParseKey.KEY_CONTENT_TITLE);

                        if (jsonObject.has(ParseKey.KEY_CONTENT_DETAILS))
                            details = jsonObject.getString(ParseKey.KEY_CONTENT_DETAILS);

                        if(jsonObject.has(ParseKey.KEY_CONTENT_IMG))
                            img = jsonObject.getString(ParseKey.KEY_CONTENT_IMG);

                        contents.add(new Content(contentId, title, details,img));
                    }
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Content> getContents(){return contents;}

}

