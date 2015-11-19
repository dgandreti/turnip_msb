/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

import java.util.Map;

/**
 *
 * @author praveen<pkatru@miraclesoft.com>
 */
public class ProjectTeamDetailsVTO {

    private int projectID;
    private int userId;
    private String teamMemberName;
    private Map assignedMap;
    private Map subProjectMap;
    private String status;
    private int reportPerson1;
    private int reportPerson2;
    private int designation;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public void setTeamMemberName(String teamMemberName) {
        this.teamMemberName = teamMemberName;
    }

    public Map getAssignedMap() {
        return assignedMap;
    }

    public void setAssignedMap(Map assignedMap) {
        this.assignedMap = assignedMap;
    }

    public Map getSubProjectMap() {
        return subProjectMap;
    }

    public void setSubProjectMap(Map subProjectMap) {
        this.subProjectMap = subProjectMap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReportPerson1() {
        return reportPerson1;
    }

    public void setReportPerson1(int reportPerson1) {
        this.reportPerson1 = reportPerson1;
    }

    public int getReportPerson2() {
        return reportPerson2;
    }

    public void setReportPerson2(int reportPerson2) {
        this.reportPerson2 = reportPerson2;
    }

    public int getDesignation() {
        return designation;
    }

    public void setDesignation(int designation) {
        this.designation = designation;
    }
}
