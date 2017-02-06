package com.mcc.healthservicefinal.parser;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.mcc.healthservicefinal.constants.ParseKey;
import com.mcc.healthservicefinal.objects.MainMenu;
import com.mcc.healthservicefinal.objects.SubMenu;


/**
 * Created by LINKON on 1/26/2017.
 */

public class SubMenuParser {
    private ArrayList<SubMenu> SubMenus;
    private Context mContext;

    public SubMenuParser(Context mContext){
        this.mContext = mContext;
    }

    public void parseSubMenuString(String response) {
        SubMenus = new ArrayList<>();

        try {
            if (response != null && !response.isEmpty()) {


                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_SUB_MENU);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject != null) {

                        String MenuId = null,SubMenuId=null, SubMenuName = null, status = null, img = null;

                        if (jsonObject.has(ParseKey.KEY_MENU_ID))
                            MenuId = jsonObject.getString(ParseKey.KEY_MENU_ID);
                        if(jsonObject.has(ParseKey.KEY_SUB_MENU_ID))
                            SubMenuId = jsonObject.getString(ParseKey.KEY_SUB_MENU_ID);

                        if (jsonObject.has(ParseKey.KEY_SUB_MENU_NAME))
                            SubMenuName = jsonObject.getString(ParseKey.KEY_SUB_MENU_NAME);

                        if (jsonObject.has(ParseKey.KEY_STATUS))
                            status = jsonObject.getString(ParseKey.KEY_STATUS);

                        if (jsonObject.has(ParseKey.KEY_IMG))
                            img = jsonObject.getString(ParseKey.KEY_IMG);

                        SubMenus.add(new SubMenu(MenuId, SubMenuId,SubMenuName,status,img));
                    }
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SubMenu> getSubMenus() {
        return SubMenus;
    }

}
