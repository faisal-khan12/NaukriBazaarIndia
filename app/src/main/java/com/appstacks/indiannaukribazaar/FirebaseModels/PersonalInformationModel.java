package com.appstacks.indiannaukribazaar.FirebaseModels;

public class PersonalInformationModel {
    String firstName, lastName, userAddress, city, zipCode, gst, uniqueKey,userId;

    String frontImage,backImage,selectedDoc;


    public PersonalInformationModel() {

    }
  public  PersonalInformationModel(String frontImage,String backImage,String selectedDoc){
        this.backImage=backImage;
        this.frontImage=frontImage;
        this.selectedDoc=selectedDoc;
  }

    public String getSelectedDoc() {
        return selectedDoc;
    }

    public void setSelectedDoc(String selectedDoc) {
        this.selectedDoc = selectedDoc;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public PersonalInformationModel(String firstName, String lastName, String userAddress, String city, String zipCode, String gst, String uniqueKey, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAddress = userAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.gst = gst;
        this.uniqueKey = uniqueKey;
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
