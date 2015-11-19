/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.opp;

import java.io.Serializable;

/**
 *
 * @author Anton Franklin
 *
 */
public class OpportunityVTO implements Serializable {

    private Integer opportunityID;
    private Integer accountID;
    private String opportunityType;
    private String opportunityName;
    private String opportunityDesc;
    private String opportunityCreatedDate;
    private Integer opportunityCreatedBy;
    private String opportunityComments;
    private Integer opportunityModifiedBy;
    private String opportunityModifiedDate;

    public Integer getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(Integer opportunityID) {
        this.opportunityID = opportunityID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(String opportunityType) {
        this.opportunityType = opportunityType;
    }

    public String getOpportunityName() {
        return opportunityName;
    }

    public void setOpportunityName(String opportunityName) {
        this.opportunityName = opportunityName;
    }

    public String getOpportunityDesc() {
        return opportunityDesc;
    }

    public void setOpportunityDesc(String opportunityDesc) {
        this.opportunityDesc = opportunityDesc;
    }

    public String getOpportunityCreatedDate() {
        return opportunityCreatedDate;
    }

    public void setOpportunityCreatedDate(String opportunityCreatedDate) {
        this.opportunityCreatedDate = opportunityCreatedDate;
    }

    public int getOpportunityCreatedBy() {
        return opportunityCreatedBy;
    }

    public void setOpportunityCreatedBy(int opportunityCreatedBy) {
        this.opportunityCreatedBy = opportunityCreatedBy;
    }

    public String getOpportunityComments() {
        return opportunityComments;
    }

    public void setOpportunityComments(String opportunityComments) {
        this.opportunityComments = opportunityComments;
    }

    public int getOpportunityModifiedBy() {
        return opportunityModifiedBy;
    }

    public void setOpportunityModifiedBy(int opportunityModifiedBy) {
        this.opportunityModifiedBy = opportunityModifiedBy;
    }

    public String getopportunityModifiedDate() {
        return opportunityModifiedDate;
    }

    public void setopportunityModifiedDate(String opportunityModifiedDate) {
        this.opportunityModifiedDate = opportunityModifiedDate;
    }

    public String getOpportunityDescTitle() {

        if (this.opportunityDesc.length() < 10) {

            return this.opportunityDesc;
        } else {
            return this.opportunityDesc.substring(0, 10);
        }

    }
}
