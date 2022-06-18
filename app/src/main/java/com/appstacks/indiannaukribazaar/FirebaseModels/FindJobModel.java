package com.appstacks.indiannaukribazaar.FirebaseModels;

public class FindJobModel {

    int jobImg2;
    String jobTitle2;
    String address;
    String salary2;
    String jobType2;
    String time2;

    public int getJobImg2() {
        return jobImg2;
    }

    public void setJobImg2(int jobImg2) {
        this.jobImg2 = jobImg2;
    }

    public String getJobTitle2() {
        return jobTitle2;
    }

    public void setJobTitle2(String jobTitle2) {
        this.jobTitle2 = jobTitle2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary2() {
        return salary2;
    }

    public void setSalary2(String salary2) {
        this.salary2 = salary2;
    }

    public String getJobType2() {
        return jobType2;
    }

    public void setJobType2(String jobType2) {
        this.jobType2 = jobType2;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public FindJobModel(int jobImg2, String jobTitle2, String address, String salary2, String jobType2, String time2) {
        this.jobImg2 = jobImg2;
        this.jobTitle2 = jobTitle2;
        this.address = address;
        this.salary2 = salary2;
        this.jobType2 = jobType2;
        this.time2 = time2;
    }
}
