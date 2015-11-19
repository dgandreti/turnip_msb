/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import java.io.Serializable;

/**
 *
 * @author Riza
 */
public class ProjectsVTO implements Serializable {

    private Integer projectID;
    private Integer accountID;
    private String projectName;
    private String projectType;
    private String projectReqSkillSet;
    private String project_description;
    private String projectStartDate;
    private String projectTargetDate;
    private String project_status;
    private Integer parentProjectID;
    private Integer projectCreatedBy;
    private String projectCreatedDate;
    private Integer projectModifiedBy;
    private String projectModifiedDate;
//    private String projectTargetHrs;
//    private String projectWorkedHrs;
    private String costCenterName;
    private Double projectTargetHrs;
    private Double projectWorkedHrs;

    public Double getProjectTargetHrs() {
        return projectTargetHrs;
    }

    public void setProjectTargetHrs(Double projectTargetHrs) {
        this.projectTargetHrs = projectTargetHrs;
    }

    public Double getProjectWorkedHrs() {
        return projectWorkedHrs;
    }

    public void setProjectWorkedHrs(Double projectWorkedHrs) {
        this.projectWorkedHrs = projectWorkedHrs;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectReqSkillSet() {
        return projectReqSkillSet;
    }

    public void setProjectReqSkillSet(String projectReqSkillSet) {
        this.projectReqSkillSet = projectReqSkillSet;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectTargetDate() {
        return projectTargetDate;
    }

    public void setProjectTargetDate(String projectTargetDate) {
        this.projectTargetDate = projectTargetDate;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public Integer getParentProjectID() {
        return parentProjectID;
    }

    public void setParentProjectID(Integer parentProjectID) {
        this.parentProjectID = parentProjectID;
    }

    public Integer getProjectCreatedBy() {
        return projectCreatedBy;
    }

    public void setProjectCreatedBy(Integer projectCreatedBy) {
        this.projectCreatedBy = projectCreatedBy;
    }

    public Integer getProjectModifiedBy() {
        return projectModifiedBy;
    }

    public void setProjectModifiedBy(Integer projectModifiedBy) {
        this.projectModifiedBy = projectModifiedBy;
    }

    public String getProjectModifiedDate() {
        return projectModifiedDate;
    }

    public void setProjectModifiedDate(String projectModifiedDate) {
        this.projectModifiedDate = projectModifiedDate;
    }

    public String getProjectCreatedDate() {
        return projectCreatedDate;
    }

    public void setProjectCreatedDate(String projectCreatedDate) {
        this.projectCreatedDate = projectCreatedDate;
    }

    @Override
    public String toString() {
        return "ProjectsVTO{" + "projectID=" + projectID + ", accountID=" + accountID + ", projectName=" + projectName + ", projectType=" + projectType + ", projectReqSkillSet=" + projectReqSkillSet + ", project_description=" + project_description + ", projectStartDate=" + projectStartDate + ", projectTargetDate=" + projectTargetDate + ", project_status=" + project_status + ", parentProjectID=" + parentProjectID + ", projectCreatedBy=" + projectCreatedBy + ", projectCreatedDate=" + projectCreatedDate + ", projectModifiedBy=" + projectModifiedBy + ", projectModifiedDate=" + projectModifiedDate + '}';
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }
    
}
