/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauth;

/**
 *
 * @author miracle
 */
public class AccauthVTO {

    private int action_id;
    private String action_name;
    private String status;
    private String description;
    private String accountName;
    private String rollName;
    private int roleId;
    private int id;
    private String accType;
    private int userGroupList;
    private int blockFlag;
    public int getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(int userGroupList) {
        this.userGroupList = userGroupList;
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getBlockFlag() {
        return blockFlag;
    }

    public void setBlockFlag(int blockFlag) {
        this.blockFlag = blockFlag;
    }
    
    
}
