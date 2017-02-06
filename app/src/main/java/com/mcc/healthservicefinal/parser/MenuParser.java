package com.mcc.healthservicefinal.parser;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.mcc.healthservicefinal.constants.ParseKey;
import com.mcc.healthservicefinal.objects.MainMenu;

/**
 * Created by LINKON on 1/25/2017.
 */

public class MenuParser {
    private ArrayList<MainMenu> menus;
    private Context mContext;

    public MenuParser(Context mContext){
        this.mContext = mContext;
    }

    public void parseMenuString(String response) {
        menus = new ArrayList<>();

        try {
            if (response != null && !response.isEmpty()) {


                JSONArray jsonArray = new JSONObject(response).getJSONArray(ParseKey.KEY_MAIN_MENU);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject != null) {

                        String menuId = null, name = null, status = null, img = null;

                        if (jsonObject.has(ParseKey.KEY_MENU_ID))
                            menuId = jsonObject.getString(ParseKey.KEY_MENU_ID);

                        if (jsonObject.has(ParseKey.KEY_NAME))
                            name = jsonObject.getString(ParseKey.KEY_NAME);

                        if (jsonObject.has(ParseKey.KEY_STATUS))
                            status = jsonObject.getString(ParseKey.KEY_STATUS);

                        if (jsonObject.has(ParseKey.KEY_IMG))
                            img = jsonObject.getString(ParseKey.KEY_IMG);

                        menus.add(new MainMenu(menuId, name, status, img));
                    }
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MainMenu> getMenus() {
        return menus;
    }
}
