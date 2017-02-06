package com.mcc.healthservicefinal.parser;

import android.content.Context;

import com.mcc.healthservicefinal.constants.ParseKey;
import com.mcc.healthservicefinal.objects.SubMenu;
import com.mcc.healthservicefinal.objects.SubSubMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by LINKON on 1/29/2017.
 */

public class SubSubMenuParser {
    private ArrayList<SubSubMenu> SubSubMenus;
    private Context mContext;

    public SubSubMenuParser(Context mContext){
        this.mContext = mContext;
    }

    public void parseSubSubMenuString(String response) {
        SubSubMenus = new ArrayList<>();

        try {
            if (response != null && !response.isEmpty()) {


                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_SUB_SUB_MENU);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject != null) {

                        String MenuId = null,SubMenuId=null,SubSubMenuID=null,SubSubMenuName=null, status = null, img = null;

                        if (jsonObject.has(ParseKey.KEY_MENU_ID))
                            MenuId = jsonObject.getString(ParseKey.KEY_MENU_ID);
                        if(jsonObject.has(ParseKey.KEY_SUB_MENU_ID))
                            SubMenuId = jsonObject.getString(ParseKey.KEY_SUB_MENU_ID);

                        if (jsonObject.has(ParseKey.KEY_SUB_SUB_MENU_ID))
                            SubSubMenuID = jsonObject.getString(ParseKey.KEY_SUB_SUB_MENU_ID);

                        if(jsonObject.has(ParseKey.KEY_SUB_SUB_MENU_NAME))
                            SubSubMenuName = jsonObject.getString(ParseKey.KEY_SUB_SUB_MENU_NAME);

                        if (jsonObject.has(ParseKey.KEY_STATUS))
                            status = jsonObject.getString(ParseKey.KEY_STATUS);

                        if (jsonObject.has(ParseKey.KEY_IMG))
                            img = jsonObject.getString(ParseKey.KEY_IMG);


                        SubSubMenus.add(new SubSubMenu(MenuId,SubMenuId,SubSubMenuID,SubSubMenuName,status,img));
                    }
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SubSubMenu> getSubSubMenus() {
        return SubSubMenus;
    }

}
