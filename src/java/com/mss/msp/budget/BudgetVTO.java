/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.budget;

/**
 *
 * @author miracle
 */
public class BudgetVTO {

    private String projectName;
    private String estimatedBudget;
    private String remainingBudget;
    private String status;
    private String quarterId;
    private String year;
    private String comments;
    private int id;
    private String ccName;
    private double cosumedAmt;
    private double costCenterBudget;
    private String projectType;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEstimatedBudget() {
        return estimatedBudget;
    }

    public void setEstimatedBudget(String estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }

    public String getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(String remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(String quarterId) {
        this.quarterId = quarterId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public double getCosumedAmt() {
        return cosumedAmt;
    }

    public void setCosumedAmt(double cosumedAmt) {
        this.cosumedAmt = cosumedAmt;
    }

    public double getCostCenterBudget() {
        return costCenterBudget;
    }

    public void setCostCenterBudget(double costCenterBudget) {
        this.costCenterBudget = costCenterBudget;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

}
