/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.recruitment;

/**
 *
 * @author praveen
 */
public class RequirementListVTO {

    private int Id;
    private String title;
    private String noOfPosition;
    private String reqSkillSet;
    private String preSkillSet;
    private String startDate;
    private String status;
    private String req_contact1;
    private String req_contact2;
    private String reqContactName1;
    private String reqContactName2;
    private String customerName;
    private String jdId;
    private String createdByName;
    private String targetRate;
    private String taxTerm; //reqType
    private String requirementMaxRate;
    private int orgId;
    private String postedDate;

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    } 
    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getTaxTerm() {
        return taxTerm;
    }

    public void setTaxTerm(String taxTerm) {
        this.taxTerm = taxTerm;
    }

    public String getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(String targetRate) {
        this.targetRate = targetRate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoOfPosition() {
        return noOfPosition;
    }

    public void setNoOfPosition(String noOfPosition) {
        this.noOfPosition = noOfPosition;
    }

    public String getReqSkillSet() {
        return reqSkillSet;
    }

    public void setReqSkillSet(String reqSkillSet) {
        this.reqSkillSet = reqSkillSet;
    }

    public String getPreSkillSet() {
        return preSkillSet;
    }

    public void setPreSkillSet(String preSkillSet) {
        this.preSkillSet = preSkillSet;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReq_contact1() {
        return req_contact1;
    }

    public void setReq_contact1(String req_contact1) {
        this.req_contact1 = req_contact1;
    }

    public String getReq_contact2() {
        return req_contact2;
    }

    public void setReq_contact2(String req_contact2) {
        this.req_contact2 = req_contact2;
    }

    public String getReqContactName1() {
        return reqContactName1;
    }

    public void setReqContactName1(String reqContactName1) {
        this.reqContactName1 = reqContactName1;
    }

    public String getReqContactName2() {
        return reqContactName2;
    }

    public void setReqContactName2(String reqContactName2) {
        this.reqContactName2 = reqContactName2;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getJdId() {
        return jdId;
    }

    public void setJdId(String jdId) {
        this.jdId = jdId;
    }

    public String getRequirementMaxRate() {
        return requirementMaxRate;
    }

    public void setRequirementMaxRate(String requirementMaxRate) {
        this.requirementMaxRate = requirementMaxRate;
    }
}
