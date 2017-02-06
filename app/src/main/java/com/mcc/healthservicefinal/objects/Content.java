package com.mcc.healthservicefinal.objects;

/**
 * Created by LINKON on 1/25/2017.
 */

public class Content {
    private String contentId;
    private String title;
    private String details;
    private String img;

    public Content( String contentId, String title, String details,String img){
        this.contentId = contentId;
        this.title = title;
        this.details = details;
        this.img=img;
    }

    public String getContentId() {
        return contentId;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }

    public String getImg(){ return img;}

}
