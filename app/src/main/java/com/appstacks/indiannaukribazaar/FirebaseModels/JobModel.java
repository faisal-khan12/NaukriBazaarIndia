package com.appstacks.indiannaukribazaar.FirebaseModels;

public class JobModel {

    String jobImg;
    String jobTitle, companyName, jobType,uniqueKey, jobTime, jobPostDate;
    String applyBefore, jobPriceINR, JobDescription, rolesAndRESPONSIBILITIES,salaryRange,jobLocation;

    public JobModel() {
    }

    public JobModel(String jobImg, String jobTitle, String companyName, String jobType, String uniqueKey, String jobTime, String jobPostDate, String applyBefore, String jobPriceINR, String jobDescription, String rolesAndRESPONSIBILITIES, String salaryRange, String jobLocation) {
        this.jobImg = jobImg;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.jobType = jobType;
        this.uniqueKey = uniqueKey;
        this.jobTime = jobTime;
        this.jobPostDate = jobPostDate;
        this.applyBefore = applyBefore;
        this.jobPriceINR = jobPriceINR;
        JobDescription = jobDescription;
        this.rolesAndRESPONSIBILITIES = rolesAndRESPONSIBILITIES;
        this.salaryRange = salaryRange;
        this.jobLocation = jobLocation;
    }

    public String getJobImg() {
        return jobImg;
    }

    public void setJobImg(String jobImg) {
        this.jobImg = jobImg;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public String getJobPostDate() {
        return jobPostDate;
    }

    public void setJobPostDate(String jobPostDate) {
        this.jobPostDate = jobPostDate;
    }

    public String getApplyBefore() {
        return applyBefore;
    }

    public void setApplyBefore(String applyBefore) {
        this.applyBefore = applyBefore;
    }

    public String getJobPriceINR() {
        return jobPriceINR;
    }

    public void setJobPriceINR(String jobPriceINR) {
        this.jobPriceINR = jobPriceINR;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    public String getRolesAndRESPONSIBILITIES() {
        return rolesAndRESPONSIBILITIES;
    }

    public void setRolesAndRESPONSIBILITIES(String rolesAndRESPONSIBILITIES) {
        this.rolesAndRESPONSIBILITIES = rolesAndRESPONSIBILITIES;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }
}

