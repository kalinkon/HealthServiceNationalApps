package com.mcc.healthservicefinal.objects;

import java.io.Serializable;

/**
 * Created by LINKON on 1/26/2017.
 */

public class SubSubMenu implements Serializable{
    private String menuId;
    private String subMenuId;
    private String subMenuName;
    private String status;
    private String image;
    private String SubSubMenuId;
    private String SubSubMenuName;


    public SubSubMenu(String menuId, String subMenuId,String SubSubMenuId,String SubSubMenuName, String image,String status){
        this.menuId = menuId;
        this.subMenuId=subMenuId;
        this.subMenuName = subMenuName;
        this.status = status;
        this.image = image;
        this.SubSubMenuId =SubSubMenuId;
        this.SubSubMenuName=SubSubMenuName;
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

    public String getSubSubMenuId(){return SubSubMenuId;}

    public String getSubSubMenuName(){return SubSubMenuName;}
}
