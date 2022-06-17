package com.appstacks.indiannaukribazaar.NewActivities.Models;

public class DeviceDataModel {

    String deviceID,userPhoneNumber;

    public DeviceDataModel() {
    }

    public DeviceDataModel(String deviceID, String userPhoneNumber) {
        this.deviceID = deviceID;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
