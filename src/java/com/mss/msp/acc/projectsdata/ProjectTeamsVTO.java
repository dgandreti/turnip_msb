/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import java.io.Serializable;

/**
 *
 * @author Senay
 */
public class ProjectTeamsVTO implements Serializable {

    private Integer projectID;
    private Integer userID;
    private String status;
    private String createdDate;
    private Integer createdBy;
    private String modifiedDate;
    private Integer modifiedBy;
    private Integer reportsTo1;
    private String reportsTo1Name;
    private Integer reportsTo2;
    private String reportsTo2Name;
    private Integer accountID;
    private String skillName;
    private String skillComments;
    private String firstName;
    private String middleName;
    private String lastName;
    private String designation;
     private String mainProjectStatus;
    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getReportsTo1() {
        return reportsTo1;
    }

    public void setReportsTo1(Integer reportsTo1) {
        this.reportsTo1 = reportsTo1;
    }

    public Integer getReportsTo2() {
        return reportsTo2;
    }

    public void setReportsTo2(Integer reportsTo2) {
        this.reportsTo2 = reportsTo2;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillComments() {
        return skillComments;
    }

    public void setSkillComments(String skillComments) {
        this.skillComments = skillComments;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getReportsTo1Name() {
        return reportsTo1Name;
    }

    public void setReportsTo1Name(String reportsTo1Name) {
        this.reportsTo1Name = reportsTo1Name;
    }

    public String getReportsTo2Name() {
        return reportsTo2Name;
    }

    public void setReportsTo2Name(String reportsTo2Name) {
        this.reportsTo2Name = reportsTo2Name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMainProjectStatus() {
        return mainProjectStatus;
    }

    public void setMainProjectStatus(String mainProjectStatus) {
        this.mainProjectStatus = mainProjectStatus;
    }
    

    @Override
    public String toString() {
        return "ProjectTeamsVTO{" + "projectID=" + projectID + ", userID=" + userID + ", status=" + status + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy + ", reportsTo1=" + reportsTo1 + ", reportsTo1Name=" + reportsTo1Name + ", reportsTo2=" + reportsTo2 + ", reportsTo2Name=" + reportsTo2Name + ", accountID=" + accountID + ", skillName=" + skillName + ", skillComments=" + skillComments + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", mainProjectStatus=" + mainProjectStatus +'}';
    }
}
