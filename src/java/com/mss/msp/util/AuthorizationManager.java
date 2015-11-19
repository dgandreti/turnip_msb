/*******************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date    :  Feb 16, 2015, 7:53 PM
 *
 * Author  : Services Bay Team
 *
 * File    : AuthorizationManager.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.msp.util;

import com.mss.msp.util.SecurityProperties;

/**
 *
 * @author miracle
 */
public class AuthorizationManager {
    
    public static AuthorizationManager _instance;
    
    /** Creates a new instance of AuthorizationManager */
    private AuthorizationManager() {
    }
    
    public static AuthorizationManager getInstance(){
        if(_instance == null){
            _instance = new AuthorizationManager();
        }
        return _instance;
    }
    
    public boolean isAuthorizedUser(String accessKey, int roleId){
        boolean isAuthorized = false;
       // System.err.print("roleId in  AUTH******---"+roleId);
        try{
            
             int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_ROLES"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
                if(roleId == Integer.parseInt(authorizedRoleIdsArray[counter])) isAuthorized = true;
            }
        }catch(Exception slex){
            
        }
        return isAuthorized;
    }
    public boolean isAuthorizedUserCreateIssue(int isManager, int isTeamLead,String accessKey){
        boolean isAuthorized = false;
       // System.err.print("roleId in  AUTH******---"+roleId);
        try{
            
            int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_LEADS"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
                if(isTeamLead == Integer.parseInt(authorizedRoleIdsArray[counter]) || isManager== Integer.parseInt(authorizedRoleIdsArray[counter])) isAuthorized = true;
            }
        }catch(Exception slex){
            
        }
        return isAuthorized;
    }
}
