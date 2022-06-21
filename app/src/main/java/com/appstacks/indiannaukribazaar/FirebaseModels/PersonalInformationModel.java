package com.appstacks.indiannaukribazaar.FirebaseModels;

public class PersonalInformationModel {
    String firstName, lastName, userAddress, city, zipCode, gst, uniqueKey;


    public PersonalInformationModel() {

    }

    public PersonalInformationModel(String firstName, String lastName, String userAddress, String city, String zipCode, String gst, String uniqueKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAddress = userAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.gst = gst;
        this.uniqueKey = uniqueKey;
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