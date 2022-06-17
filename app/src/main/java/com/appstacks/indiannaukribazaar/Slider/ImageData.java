package com.appstacks.indiannaukribazaar.Slider;

public class ImageData {



    public  static ImageData imageData=new ImageData();
    public static ImageData getInstance(){
        return imageData;
    }
    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    String imglink;

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    String websiteLink;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String text;
}
