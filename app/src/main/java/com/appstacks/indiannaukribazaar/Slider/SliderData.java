package com.appstacks.indiannaukribazaar.Slider;

public class SliderData {

    // string for our image url.
    private String imageLink;

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    private String websiteLink;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    // empty constructor which is
    // required when using Firebase.
    public SliderData() {
    }

    // Constructor
    public SliderData(String imageLink) {
        this.imageLink = imageLink;
    }

    // Getter method.
    public String getimageLink() {
        return imageLink;
    }

    // Setter method.
    public void setimageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
