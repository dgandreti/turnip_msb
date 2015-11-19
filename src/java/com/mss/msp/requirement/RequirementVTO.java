/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.requirement;

import java.util.List;

/**
 *
 * @author miracle
 */
public class RequirementVTO {

    private int RequirementId;
    private String RequirementFrom;
    private String RequirementTo;
    private String RequirementTargetRate;
    private String RequirementDuration;
    private String RequirementContact1;
    private String RequirementReason;
    private String RequirementPresales1;
    private String RequirementType;
    private String RequirementContact2;
    private String RequirementTaxTerm;
    private String RequirementPresales2;
    private String RequirementPractice;
    private String RequirementName;
    private int RequirementNoofResources;
    private String RequirementStatus;
    private String RequirementLocation;
    private String RequirementContactNo;
    private String RequirementState;
    private String RequirementCountry;
    private String RequirementAddress;
    private String RequirementResponse;
    private String RequirementJobdesc;
    private String RequirementSkills;
    private String RequirementDescription;
    private String RequirementComments;
    private String RequirementExp;
    private String RequirementPreferredSkills;
    //for mailing release requirements
    private String reqName;
    private String reqStartDate;
    private String reqEndDate;
    private String reqResources;
    private String reqDesc;
    private int accountSearchID;
    private String billingContact;
    private String requirementDurationDescriptor;
    //for customer dashboard
    private String month;
    private String total;
    private String open;
    private String released;
    private String closed;
    private String createdByName;
    private String requirementMaxRate;
    private String skillCategoryValue;
    private List skillSetList;
    private List preSkillSetList;
    private int othersCount;
     private String billingtype;

    public int getOthersCount() {
        return othersCount;
    }

    public void setOthersCount(int othersCount) {
        this.othersCount = othersCount;
    }
    
    

    public String getSkillCategoryValue() {
        return skillCategoryValue;
    }

    public void setSkillCategoryValue(String skillCategoryValue) {
        this.skillCategoryValue = skillCategoryValue;
    }

    public List getSkillSetList() {
        return skillSetList;
    }

    public void setSkillSetList(List skillSetList) {
        this.skillSetList = skillSetList;
    }

    public List getPreSkillSetList() {
        return preSkillSetList;
    }

    public void setPreSkillSetList(List preSkillSetList) {
        this.preSkillSetList = preSkillSetList;
    }

    
    public String getRequirementExp() {
        return RequirementExp;
    }

    public void setRequirementExp(String RequirementExp) {
        this.RequirementExp = RequirementExp;
    }

    public int getRequirementId() {
        return RequirementId;
    }

    public void setRequirementId(int RequirementId) {
        this.RequirementId = RequirementId;
    }

    public String getRequirementFrom() {
        return RequirementFrom;
    }

    public void setRequirementFrom(String RequirementFrom) {
        this.RequirementFrom = RequirementFrom;
    }

    public String getRequirementTo() {
        return RequirementTo;
    }

    public void setRequirementTo(String RequirementTo) {
        this.RequirementTo = RequirementTo;
    }

    public String getRequirementTargetRate() {
        return RequirementTargetRate;
    }

    public void setRequirementTargetRate(String RequirementTargetRate) {
        this.RequirementTargetRate = RequirementTargetRate;
    }

    public String getRequirementDuration() {
        return RequirementDuration;
    }

    public void setRequirementDuration(String RequirementDuration) {
        this.RequirementDuration = RequirementDuration;
    }

    public String getRequirementContact1() {
        return RequirementContact1;
    }

    public void setRequirementContact1(String RequirementContact1) {
        this.RequirementContact1 = RequirementContact1;
    }

    public String getRequirementReason() {
        return RequirementReason;
    }

    public void setRequirementReason(String RequirementReason) {
        this.RequirementReason = RequirementReason;
    }

    public String getRequirementPresales1() {
        return RequirementPresales1;
    }

    public void setRequirementPresales1(String RequirementPresales1) {
        this.RequirementPresales1 = RequirementPresales1;
    }

    public String getRequirementType() {
        return RequirementType;
    }

    public void setRequirementType(String RequirementType) {
        this.RequirementType = RequirementType;
    }

    public String getRequirementContact2() {
        return RequirementContact2;
    }

    public void setRequirementContact2(String RequirementContact2) {
        this.RequirementContact2 = RequirementContact2;
    }

    public String getRequirementTaxTerm() {
        return RequirementTaxTerm;
    }

    public void setRequirementTaxTerm(String RequirementTaxTerm) {
        this.RequirementTaxTerm = RequirementTaxTerm;
    }

    public String getRequirementPresales2() {
        return RequirementPresales2;
    }

    public void setRequirementPresales2(String RequirementPresales2) {
        this.RequirementPresales2 = RequirementPresales2;
    }

    public String getRequirementPractice() {
        return RequirementPractice;
    }

    public void setRequirementPractice(String RequirementPractice) {
        this.RequirementPractice = RequirementPractice;
    }

    public String getRequirementName() {
        return RequirementName;
    }

    public void setRequirementName(String RequirementName) {
        this.RequirementName = RequirementName;
    }

    public int getRequirementNoofResources() {
        return RequirementNoofResources;
    }

    public void setRequirementNoofResources(int RequirementNoofResources) {
        this.RequirementNoofResources = RequirementNoofResources;
    }

    public String getRequirementStatus() {
        return RequirementStatus;
    }

    public void setRequirementStatus(String RequirementStatus) {
        this.RequirementStatus = RequirementStatus;
    }

    public String getRequirementLocation() {
        return RequirementLocation;
    }

    public void setRequirementLocation(String RequirementLocation) {
        this.RequirementLocation = RequirementLocation;
    }

    public String getRequirementContactNo() {
        return RequirementContactNo;
    }

    public void setRequirementContactNo(String RequirementContactNo) {
        this.RequirementContactNo = RequirementContactNo;
    }

    public String getRequirementState() {
        return RequirementState;
    }

    public void setRequirementState(String RequirementState) {
        this.RequirementState = RequirementState;
    }

    public String getRequirementCountry() {
        return RequirementCountry;
    }

    public void setRequirementCountry(String RequirementCountry) {
        this.RequirementCountry = RequirementCountry;
    }

    public String getRequirementAddress() {
        return RequirementAddress;
    }

    public void setRequirementAddress(String RequirementAddress) {
        this.RequirementAddress = RequirementAddress;
    }

    public String getRequirementResponse() {
        RequirementResponse = RequirementResponse.replaceAll("<br/>", "\n");
        return RequirementResponse;
    }

    public void setRequirementResponse(String RequirementResponse) {
        this.RequirementResponse = RequirementResponse;
    }

    public String getRequirementJobdesc() {
        RequirementJobdesc = RequirementJobdesc.replaceAll("<br/>", "\n");
        return RequirementJobdesc;
    }

    public void setRequirementJobdesc(String RequirementJobdesc) {
        this.RequirementJobdesc = RequirementJobdesc;
    }

    public String getRequirementSkills() {
        RequirementSkills = RequirementSkills.replaceAll("<br/>", "\n");
        return RequirementSkills;
    }

    public void setRequirementSkills(String RequirementSkills) {
        this.RequirementSkills = RequirementSkills;
    }

    public String getRequirementDescription() {
        return RequirementDescription;
    }

    public void setRequirementDescription(String RequirementDescription) {
        this.RequirementDescription = RequirementDescription;
    }

    public String getRequirementComments() {
        RequirementComments = RequirementComments.replaceAll("<br/>", "\n");
        return RequirementComments;
    }

    public void setRequirementComments(String RequirementComments) {
        this.RequirementComments = RequirementComments;
    }

    public String getRequirementPreferredSkills() {
        RequirementPreferredSkills = RequirementPreferredSkills.replaceAll("<br/>", "\n");
        return RequirementPreferredSkills;
    }

    public void setRequirementPreferredSkills(String RequirementPreferredSkills) {
        this.RequirementPreferredSkills = RequirementPreferredSkills;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqStartDate() {
        return reqStartDate;
    }

    public void setReqStartDate(String reqStartDate) {
        this.reqStartDate = reqStartDate;
    }

    public String getReqEndDate() {
        return reqEndDate;
    }

    public void setReqEndDate(String reqEndDate) {
        this.reqEndDate = reqEndDate;
    }

    public String getReqResources() {
        return reqResources;
    }

    public void setReqResources(String reqResources) {
        this.reqResources = reqResources;
    }

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    public int getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(int accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public String getBillingContact() {
        return billingContact;
    }

    public void setBillingContact(String billingContact) {
        this.billingContact = billingContact;
    }

    public String getRequirementDurationDescriptor() {
        return requirementDurationDescriptor;
    }

    public void setRequirementDurationDescriptor(String requirementDurationDescriptor) {
        this.requirementDurationDescriptor = requirementDurationDescriptor;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }
    private int reqCatgory;

    public int getReqCatgory() {
        return reqCatgory;
    }

    public void setReqCatgory(int reqCatgory) {
        this.reqCatgory = reqCatgory;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getRequirementMaxRate() {
        return requirementMaxRate;
    }

    public void setRequirementMaxRate(String requirementMaxRate) {
        this.requirementMaxRate = requirementMaxRate;
    }

    public String getBillingtype() {
        return billingtype;
    }

    public void setBillingtype(String billingtype) {
        this.billingtype = billingtype;
    }
    
}
