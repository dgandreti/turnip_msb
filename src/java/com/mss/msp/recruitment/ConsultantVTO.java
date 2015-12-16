/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.recruitment;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author miracle
 */
public class ConsultantVTO implements Serializable {

    //for Requirement retrieval//
    private int requirementId;
    private String jobTitle;
    private String requirementSkill;
    private String requirementStatus;
    private String reqStart;
    private String reqEnd;
    private String requirementName;
    private int requirementNoPositions;
    private String yearExperience;
    //for Requirement retrieval//
    // for consultant details aded by triveni
    private int cons_id;
    private int consult_id;
    private String consult_name;
    private String consult_email;
    private String consult_skill;
    private String consult_phno;
    private String consult_fstname;
    // private String consult_gender;
    private String consult_homePhone;
    private String consult_favail;
    private String consult_lstname;
    private String consult_dob;
    private String consult_mobileNo;
    private String consult_available;
    private String consult_midname;
    //private String consult_mStatus;
    private String consult_lcountry;
    private String consult_Address;
    private String consult_Address2;
    private String consult_City;
    private int consult_Country;
    private int consult_State;
    private String consult_Zip;
    private String consult_Phone;
    private String address_flag;
    private String consult_CAddress;
    private String consult_CAddress2;
    private String consult_CCity;
    private int consult_CCountry;
    private int consult_CState;
    private String consult_CZip;
    private String consult_CPhone;
    private int modified_by;
    private String consult_education;
    private int consult_industry;
    private String consult_salary;
    private int consult_wcountry;
    private int consult_organization;
    private int consult_experience;
    private List consult_preferredState;
    private int consult_preferredCountry;
    private String consult_jobTitle;
    private String consult_workPhone;
    private String consult_referredBy;
    private String consult_comments;
    private String consult_status;
    private String consult_checkAddress;
    private String consult_acc_attachment_id;
    private int consult_object_id;
    private String consult_object_type;
    private String consult_attachment_path;
    private String consult_attachment_name;
    private String consult_attachment_status;
    private int consult_attachment_modified_by;
    private String consult_attachment_modified_date;
    private String consult_attachment_created_date;
    /* Added for consultant Activity*/
    private int consult_activityId;
    private String consult_activityType;
    private String consult_activityPriority;
    private String consult_activityName;
    private String consult_activityStatus;
    private String consult_activityComments;
    private String consult_activityDesc;
    private String consult_activityCratedDate;
    private int consult_activityCratedBy;
    private int consult_orgid;
    private String consult_activityRelation;
    /* End for consultant Activity*/
    private String forwardedDate;
    private String forwardedBy;
    private String resumeId;
    private int forwardedById;
    private String status;
    private String consult_alterEmail;
    private String consult_ssnNo;
    //for review new concept
    private String dateOfReview;
    private String forwardedToName;
    private String comments;
    //using status;
    private String techieTitle;
    private int forwardedToId;
    private String reviewType;
    private int conTechReviewId;
    private int forwardedToId1;
    private String forwardedToName1;
    // Add By Aklakh
    private String consultantFlag;
    private String jdId;
    private String accountFlag;
    private String customerFlag;
    private String accountSearchID;
    private String scheduledDate;
    private String scheduledTime;
    private String zone;
    private String avgRating;
    // Add by Aklakh for social media account
    private String consult_facebookId;
    private String consult_twitterId;
    private String consult_linkedInId;
    private List skillSetList;
//Add for Relocation
    private String consult_relocation;
    private int forwardby;
    private String ConsultantVisa;
    private String consultantIdProof;
    private String consultantIdProofAttach;

    public String getConsultantIdProof() {
        return consultantIdProof;
    }

    public void setConsultantIdProof(String consultantIdProof) {
        this.consultantIdProof = consultantIdProof;
    }

    public String getConsultantIdProofAttach() {
        return consultantIdProofAttach;
    }

    public void setConsultantIdProofAttach(String consultantIdProofAttach) {
        this.consultantIdProofAttach = consultantIdProofAttach;
    }
    

    public String getConsultantVisa() {
        return ConsultantVisa;
    }

    public void setConsultantVisa(String ConsultantVisa) {
        this.ConsultantVisa = ConsultantVisa;
    }

    public String getConsult_relocation() {
        return consult_relocation;
    }

    public void setConsult_relocation(String consult_relocation) {
        this.consult_relocation = consult_relocation;
    }

    public List getSkillSetList() {
        return skillSetList;
    }

    public void setSkillSetList(List skillSetList) {
        this.skillSetList = skillSetList;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRequirementSkill() {
        return requirementSkill;
    }

    public void setRequirementSkill(String requirementSkill) {
        this.requirementSkill = requirementSkill;
    }

    public String getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(String requirementStatus) {
        this.requirementStatus = requirementStatus;
    }

    public String getReqStart() {
        return reqStart;
    }

    public void setReqStart(String reqStart) {
        this.reqStart = reqStart;
    }

    public String getReqEnd() {
        return reqEnd;
    }

    public void setReqEnd(String reqEnd) {
        this.reqEnd = reqEnd;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public int getRequirementNoPositions() {
        return requirementNoPositions;
    }

    public void setRequirementNoPositions(int requirementNoPositions) {
        this.requirementNoPositions = requirementNoPositions;
    }

    public String getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(String yearExperience) {
        this.yearExperience = yearExperience;
    }

    public int getConsult_id() {
        return consult_id;
    }

    public void setConsult_id(int consult_id) {
        this.consult_id = consult_id;
    }

    public String getConsult_name() {
        return consult_name;
    }

    public void setConsult_name(String consult_name) {
        this.consult_name = consult_name;
    }

    public String getConsult_email() {
        return consult_email;
    }

    public void setConsult_email(String consult_email) {
        this.consult_email = consult_email;
    }

    public String getConsult_skill() {
        return consult_skill;
    }

    public void setConsult_skill(String consult_skill) {
        this.consult_skill = consult_skill;
    }

    public String getConsult_phno() {
        return consult_phno;
    }

    public void setConsult_phno(String consult_phno) {
        this.consult_phno = consult_phno;
    }

    public String getConsult_fstname() {
        return consult_fstname;
    }

    public void setConsult_fstname(String consult_fstname) {
        this.consult_fstname = consult_fstname;
    }

    public String getConsult_homePhone() {
        return consult_homePhone;
    }

    public void setConsult_homePhone(String consult_homePhone) {
        this.consult_homePhone = consult_homePhone;
    }

    public String getConsult_favail() {
        return consult_favail;
    }

    public void setConsult_favail(String consult_favail) {
        this.consult_favail = consult_favail;
    }

    public String getConsult_lstname() {
        return consult_lstname;
    }

    public void setConsult_lstname(String consult_lstname) {
        this.consult_lstname = consult_lstname;
    }

    public String getConsult_dob() {
        return consult_dob;
    }

    public void setConsult_dob(String consult_dob) {
        this.consult_dob = consult_dob;
    }

    public String getConsult_mobileNo() {
        return consult_mobileNo;
    }

    public void setConsult_mobileNo(String consult_mobileNo) {
        this.consult_mobileNo = consult_mobileNo;
    }

    public String getConsult_available() {
        return consult_available;
    }

    public void setConsult_available(String consult_available) {
        this.consult_available = consult_available;
    }

    public String getConsult_midname() {
        return consult_midname;
    }

    public void setConsult_midname(String consult_midname) {
        this.consult_midname = consult_midname;
    }

    public String getConsult_education() {
        return consult_education;
    }

    public void setConsult_education(String consult_education) {
        this.consult_education = consult_education;
    }

    public String getConsult_alterEmail() {
        return consult_alterEmail;
    }

    public void setConsult_alterEmail(String consult_alterEmail) {
        this.consult_alterEmail = consult_alterEmail;
    }

    public String getConsult_ssnNo() {
        return consult_ssnNo;
    }

    public void setConsult_ssnNo(String consult_ssnNo) {
        this.consult_ssnNo = consult_ssnNo;
    }

    public String getConsult_lcountry() {
        return consult_lcountry;
    }

    public void setConsult_lcountry(String consult_lcountry) {
        this.consult_lcountry = consult_lcountry;
    }

    public String getConsult_Address() {
        return consult_Address;
    }

    public void setConsult_Address(String consult_Address) {
        this.consult_Address = consult_Address;
    }

    public String getConsult_Address2() {
        return consult_Address2;
    }

    public void setConsult_Address2(String consult_Address2) {
        this.consult_Address2 = consult_Address2;
    }

    public String getConsult_City() {
        return consult_City;
    }

    public void setConsult_City(String consult_City) {
        this.consult_City = consult_City;
    }

    public String getConsult_Phone() {
        return consult_Phone;
    }

    public void setConsult_Phone(String consult_Phone) {
        this.consult_Phone = consult_Phone;
    }

    public String getAddress_flag() {
        return address_flag;
    }

    public void setAddress_flag(String address_flag) {
        this.address_flag = address_flag;
    }

    public String getConsult_CAddress() {
        return consult_CAddress;
    }

    public void setConsult_CAddress(String consult_CAddress) {
        this.consult_CAddress = consult_CAddress;
    }

    public String getConsult_CAddress2() {
        return consult_CAddress2;
    }

    public void setConsult_CAddress2(String consult_CAddress2) {
        this.consult_CAddress2 = consult_CAddress2;
    }

    public String getConsult_CCity() {
        return consult_CCity;
    }

    public void setConsult_CCity(String consult_CCity) {
        this.consult_CCity = consult_CCity;
    }

    public int getConsult_State() {
        return consult_State;
    }

    public void setConsult_State(int consult_State) {
        this.consult_State = consult_State;
    }

    public int getConsult_CState() {
        return consult_CState;
    }

    public void setConsult_CState(int consult_CState) {
        this.consult_CState = consult_CState;
    }

    public String getConsult_CZip() {
        return consult_CZip;
    }

    public void setConsult_CZip(String consult_CZip) {
        this.consult_CZip = consult_CZip;
    }

    public String getConsult_CPhone() {
        return consult_CPhone;
    }

    public void setConsult_CPhone(String consult_CPhone) {
        this.consult_CPhone = consult_CPhone;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public int getConsult_industry() {
        return consult_industry;
    }

    public void setConsult_industry(int consult_industry) {
        this.consult_industry = consult_industry;
    }

    public String getConsult_Zip() {
        return consult_Zip;
    }

    public void setConsult_Zip(String consult_Zip) {
        this.consult_Zip = consult_Zip;
    }

    public String getConsult_salary() {
        return consult_salary;
    }

    public void setConsult_salary(String consult_salary) {
        this.consult_salary = consult_salary;
    }

    public int getConsult_wcountry() {
        return consult_wcountry;
    }

    public void setConsult_wcountry(int consult_wcountry) {
        this.consult_wcountry = consult_wcountry;
    }

    public int getConsult_organization() {
        return consult_organization;
    }

    public void setConsult_organization(int consult_organization) {
        this.consult_organization = consult_organization;
    }

    public int getConsult_experience() {
        return consult_experience;
    }

    public void setConsult_experience(int consult_experience) {
        this.consult_experience = consult_experience;
    }

    public List getConsult_preferredState() {
        return consult_preferredState;
    }

    public void setConsult_preferredState(List consult_preferredState) {
        this.consult_preferredState = consult_preferredState;
    }

    public String getConsult_jobTitle() {
        return consult_jobTitle;
    }

    public void setConsult_jobTitle(String consult_jobTitle) {
        this.consult_jobTitle = consult_jobTitle;
    }

    public String getConsult_workPhone() {
        return consult_workPhone;
    }

    public void setConsult_workPhone(String consult_workPhone) {
        this.consult_workPhone = consult_workPhone;
    }

    public String getConsult_referredBy() {
        return consult_referredBy;
    }

    public void setConsult_referredBy(String consult_referredBy) {
        this.consult_referredBy = consult_referredBy;
    }

    public String getConsult_comments() {
        return consult_comments;
    }

    public void setConsult_comments(String consult_comments) {
        this.consult_comments = consult_comments;
    }

    public String getConsult_status() {
        return consult_status;
    }

    public void setConsult_status(String consult_status) {
        this.consult_status = consult_status;
    }

    public String getConsult_checkAddress() {
        return consult_checkAddress;
    }

    public void setConsult_checkAddress(String consult_checkAddress) {
        this.consult_checkAddress = consult_checkAddress;
    }

    public String getConsult_acc_attachment_id() {
        return consult_acc_attachment_id;
    }

    public void setConsult_acc_attachment_id(String consult_acc_attachment_id) {
        this.consult_acc_attachment_id = consult_acc_attachment_id;
    }

    public int getConsult_object_id() {
        return consult_object_id;
    }

    public void setConsult_object_id(int consult_object_id) {
        this.consult_object_id = consult_object_id;
    }

    public String getConsult_object_type() {
        return consult_object_type;
    }

    public void setConsult_object_type(String consult_object_type) {
        this.consult_object_type = consult_object_type;
    }

    public String getConsult_attachment_path() {
        return consult_attachment_path;
    }

    public void setConsult_attachment_path(String consult_attachment_path) {
        this.consult_attachment_path = consult_attachment_path;
    }

    public String getConsult_attachment_name() {
        return consult_attachment_name;
    }

    public void setConsult_attachment_name(String consult_attachment_name) {
        this.consult_attachment_name = consult_attachment_name;
    }

    public String getConsult_attachment_status() {
        return consult_attachment_status;
    }

    public void setConsult_attachment_status(String consult_attachment_status) {
        this.consult_attachment_status = consult_attachment_status;
    }

    public int getConsult_attachment_modified_by() {
        return consult_attachment_modified_by;
    }

    public void setConsult_attachment_modified_by(int consult_attachment_modified_by) {
        this.consult_attachment_modified_by = consult_attachment_modified_by;
    }

    public String getConsult_attachment_modified_date() {
        return consult_attachment_modified_date;
    }

    public void setConsult_attachment_modified_date(String consult_attachment_modified_date) {
        this.consult_attachment_modified_date = consult_attachment_modified_date;
    }

    public int getCons_id() {
        return cons_id;
    }

    public void setCons_id(int cons_id) {
        this.cons_id = cons_id;
    }

    public String getConsult_attachment_created_date() {
        return consult_attachment_created_date;
    }

    public void setConsult_attachment_created_date(String consult_attachment_created_date) {
        this.consult_attachment_created_date = consult_attachment_created_date;
    }

    public int getConsult_activityId() {
        return consult_activityId;
    }

    public void setConsult_activityId(int consult_activityId) {
        this.consult_activityId = consult_activityId;
    }

    public String getConsult_activityType() {
        return consult_activityType;
    }

    public void setConsult_activityType(String consult_activityType) {
        this.consult_activityType = consult_activityType;
    }

    public String getConsult_activityPriority() {
        return consult_activityPriority;
    }

    public void setConsult_activityPriority(String consult_activityPriority) {
        this.consult_activityPriority = consult_activityPriority;
    }

    public String getConsult_activityName() {
        return consult_activityName;
    }

    public void setConsult_activityName(String consult_activityName) {
        this.consult_activityName = consult_activityName;
    }

    public String getConsult_activityStatus() {
        return consult_activityStatus;
    }

    public void setConsult_activityStatus(String consult_activityStatus) {
        this.consult_activityStatus = consult_activityStatus;
    }

    public String getConsult_activityComments() {
        return consult_activityComments;
    }

    public void setConsult_activityComments(String consult_activityComments) {
        this.consult_activityComments = consult_activityComments;
    }

    public String getConsult_activityDesc() {
        return consult_activityDesc;
    }

    public void setConsult_activityDesc(String consult_activityDesc) {
        this.consult_activityDesc = consult_activityDesc;
    }

    public String getConsult_activityCratedDate() {
        return consult_activityCratedDate;
    }

    public void setConsult_activityCratedDate(String consult_activityCratedDate) {
        this.consult_activityCratedDate = consult_activityCratedDate;
    }

    public int getConsult_activityCratedBy() {
        return consult_activityCratedBy;
    }

    public void setConsult_activityCratedBy(int consult_activityCratedBy) {
        this.consult_activityCratedBy = consult_activityCratedBy;
    }

    public int getConsult_orgid() {
        return consult_orgid;
    }

    public void setConsult_orgid(int consult_orgid) {
        this.consult_orgid = consult_orgid;
    }

    public String getConsult_activityRelation() {
        return consult_activityRelation;
    }

    public void setConsult_activityRelation(String consult_activityRelation) {
        this.consult_activityRelation = consult_activityRelation;
    }

    public String getForwardedDate() {
        return forwardedDate;
    }

    public void setForwardedDate(String forwardedDate) {
        this.forwardedDate = forwardedDate;
    }

    public String getForwardedBy() {
        return forwardedBy;
    }

    public void setForwardedBy(String forwardedBy) {
        this.forwardedBy = forwardedBy;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public int getForwardedById() {
        return forwardedById;
    }

    public void setForwardedById(int forwardedById) {
        this.forwardedById = forwardedById;
    }

    public int getConsult_Country() {
        return consult_Country;
    }

    public void setConsult_Country(int consult_Country) {
        this.consult_Country = consult_Country;
    }

    public int getConsult_CCountry() {
        return consult_CCountry;
    }

    public void setConsult_CCountry(int consult_CCountry) {
        this.consult_CCountry = consult_CCountry;
    }

    public int getConsult_preferredCountry() {
        return consult_preferredCountry;
    }

    public void setConsult_preferredCountry(int consult_preferredCountry) {
        this.consult_preferredCountry = consult_preferredCountry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(String dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public String getForwardedToName() {
        return forwardedToName;
    }

    public void setForwardedToName(String forwardedToName) {
        this.forwardedToName = forwardedToName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTechieTitle() {
        return techieTitle;
    }

    public void setTechieTitle(String techieTitle) {
        this.techieTitle = techieTitle;
    }

    public int getForwardedToId() {
        return forwardedToId;
    }

    public void setForwardedToId(int forwardedToId) {
        this.forwardedToId = forwardedToId;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public int getConTechReviewId() {
        return conTechReviewId;
    }

    public void setConTechReviewId(int conTechReviewId) {
        this.conTechReviewId = conTechReviewId;
    }

    public int getForwardedToId1() {
        return forwardedToId1;
    }

    public void setForwardedToId1(int forwardedToId1) {
        this.forwardedToId1 = forwardedToId1;
    }

    public String getForwardedToName1() {
        return forwardedToName1;
    }

    public void setForwardedToName1(String forwardedToName1) {
        this.forwardedToName1 = forwardedToName1;
    }

    public String getConsultantFlag() {
        return consultantFlag;
    }

    public void setConsultantFlag(String consultantFlag) {
        this.consultantFlag = consultantFlag;
    }

    public String getJdId() {
        return jdId;
    }

    public void setJdId(String jdId) {
        this.jdId = jdId;
    }

    public String getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    public String getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(String customerFlag) {
        this.customerFlag = customerFlag;
    }

    public String getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(String accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public String getConsult_facebookId() {
        return consult_facebookId;
    }

    public void setConsult_facebookId(String consult_facebookId) {
        this.consult_facebookId = consult_facebookId;
    }

    public String getConsult_twitterId() {
        return consult_twitterId;
    }

    public void setConsult_twitterId(String consult_twitterId) {
        this.consult_twitterId = consult_twitterId;
    }

    public String getConsult_linkedInId() {
        return consult_linkedInId;
    }

    public void setConsult_linkedInId(String consult_linkedInId) {
        this.consult_linkedInId = consult_linkedInId;
    }

    public int getForwardby() {
        return forwardby;
    }

    public void setForwardby(int forwardby) {
        this.forwardby = forwardby;
    }
}
