/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usersdata;

import java.sql.Date;

/**
 *
 * @author miracle
 */
public class UserVTO {
    
    private String logger;
    private String uploaded_file;
    private String resultant_file;
    private String utility_status;
    private int log_id;
    private String loggerCreatedDate;
    private String loggerFlag;
    private int empId;
    private String empLoginId;
    private String first_name;
    private String last_name;
    private String cur_status;
    private String Phone1;
    private String empName;
    private String status;
    private String workPhone;
    private String organization;
    private String reportingName;
    private String userType;
    private int usr_id;
    private String alias_name;
    private String dob;
    private String gender;
    private String working_country;
    private String living_country;
    private String email1;
    private String marital_status;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String qualification;
    private String year_start;
    private String year_end;
    private double percentage;
    private String university;
    private String institution;
    private String specialization;
    private int edu_id;
    private int usr_edu_id;
    private String usr_title;
    private int noOfAccounts;
    private String accountName;
    private int orgId;
    //for user categorization
    private String catogoryType;
    private String isPrimary;
    private String createdBy;
    private int groupingId;
    private String Role;
    private String catogoryGroup;
    private int catogoryTypeId;
    private String subCategory;

    public String getCatogoryGroup() {
        return catogoryGroup;
    }

    public void setCatogoryGroup(String catogoryGroup) {
        this.catogoryGroup = catogoryGroup;
    }

    public int getCatogoryTypeId() {
        return catogoryTypeId;
    }

    public void setCatogoryTypeId(int catogoryTypeId) {
        this.catogoryTypeId = catogoryTypeId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getEmpLoginId() {
        return empLoginId;
    }

    public void setEmpLoginId(String empLoginId) {
        this.empLoginId = empLoginId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCur_status() {
        return cur_status;
    }

    public void setCur_status(String cur_status) {
        this.cur_status = cur_status;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String Phone1) {
        this.Phone1 = Phone1;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getReportingName() {
        return reportingName;
    }

    public void setReportingName(String reportingName) {
        this.reportingName = reportingName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getAlias_name() {
        return alias_name;
    }

    public void setAlias_name(String alias_name) {
        this.alias_name = alias_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorking_country() {
        return working_country;
    }

    public void setWorking_country(String working_country) {
        this.working_country = working_country;
    }

    public String getLiving_country() {
        return living_country;
    }

    public void setLiving_country(String living_country) {
        this.living_country = living_country;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getYear_start() {
        return year_start;
    }

    public void setYear_start(String year_start) {
        this.year_start = year_start;
    }

    public String getYear_end() {
        return year_end;
    }

    public void setYear_end(String year_end) {
        this.year_end = year_end;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(int edu_id) {
        this.edu_id = edu_id;
    }

    public int getUsr_edu_id() {
        return usr_edu_id;
    }

    public void setUsr_edu_id(int usr_edu_id) {
        this.usr_edu_id = usr_edu_id;
    }

    public String getUsr_title() {
        return usr_title;
    }

    public void setUsr_title(String usr_title) {
        this.usr_title = usr_title;
    }

    public int getNoOfAccounts() {
        return noOfAccounts;
    }

    public void setNoOfAccounts(int noOfAccounts) {
        this.noOfAccounts = noOfAccounts;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getCatogoryType() {
        return catogoryType;
    }

    public void setCatogoryType(String catogoryType) {
        this.catogoryType = catogoryType;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getGroupingId() {
        return groupingId;
    }

    public void setGroupingId(int groupingId) {
        this.groupingId = groupingId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getUploaded_file() {
        return uploaded_file;
    }

    public void setUploaded_file(String uploaded_file) {
        this.uploaded_file = uploaded_file;
    }

    public String getResultant_file() {
        return resultant_file;
    }

    public void setResultant_file(String resultant_file) {
        this.resultant_file = resultant_file;
    }

    public String getUtility_status() {
        return utility_status;
    }

    public void setUtility_status(String utility_status) {
        this.utility_status = utility_status;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLoggerCreatedDate() {
        return loggerCreatedDate;
    }

    public void setLoggerCreatedDate(String loggerCreatedDate) {
        this.loggerCreatedDate = loggerCreatedDate;
    }

    public String getLoggerFlag() {
        return loggerFlag;
    }

    public void setLoggerFlag(String loggerFlag) {
        this.loggerFlag = loggerFlag;
    }
}
