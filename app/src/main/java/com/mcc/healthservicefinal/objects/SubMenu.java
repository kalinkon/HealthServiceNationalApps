package com.mcc.healthservicefinal.objects;

import java.io.Serializable;

/**
 * Created by LINKON on 1/26/2017.
 */

public class SubMenu implements Serializable {
    private String menuId;
    private String subMenuId;
    private String subMenuName;
    private String status;
    private String image;

    public SubMenu(String menuId, String subMenuId, String subMenuName, String image,String status){
        this.menuId = menuId;
        this.subMenuId=subMenuId;
        this.subMenuName = subMenuName;
        this.status = status;
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getSubMenuId(){return subMenuId; }

    public String getImage() {
        return image;
    }
}
