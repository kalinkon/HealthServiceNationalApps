package com.mcc.healthservicefinal.objects;

import java.io.Serializable;

/**
 * Created by LINKON on 1/25/2017.
 */
public class MainMenu implements Serializable{

    private String menuId;
    private String name;
    private String status;
    private String image;

    public MainMenu(String menuId, String name, String status, String image){
        this.menuId = menuId;
        this.name = name;
        this.status = status;
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getImage() {
        return image;
    }
}
