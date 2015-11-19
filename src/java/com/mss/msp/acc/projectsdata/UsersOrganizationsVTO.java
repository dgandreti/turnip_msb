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
public class UsersOrganizationsVTO implements Serializable{
    
    private int uoID;
    private int userID;
    private int organizationID;
    private int projectID;

    public int getUoID() {
        return uoID;
    }

    public void setUoID(int uoID) {
        this.uoID = uoID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "UsersOrganizationsVTO{" + "uoID=" + uoID + ", userID=" + userID + ", organizationID=" + organizationID + ", projectID=" + projectID + '}';
    }
    
    
}
