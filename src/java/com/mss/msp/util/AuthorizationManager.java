/**
 * *****************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date : Feb 16, 2015, 7:53 PM
 *
 * Author : Services Bay Team
 *
 * File : AuthorizationManager.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import com.mss.msp.util.SecurityProperties;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public class AuthorizationManager {

    public static AuthorizationManager _instance;

    /**
     * Creates a new instance of AuthorizationManager
     */
    private AuthorizationManager() {
    }

    public static AuthorizationManager getInstance() {
        if (_instance == null) {
            _instance = new AuthorizationManager();
        }
        return _instance;
    }

    public boolean isAuthorizedUser(String accessKey, int roleId) {
        boolean isAuthorized = false;
        // System.err.print("roleId in  AUTH******---"+roleId);
        try {

            int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_ROLES"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for (int counter = 0; counter < authorizedRoleIdsArray.length; counter++) {
                if (roleId == Integer.parseInt(authorizedRoleIdsArray[counter])) {
                    isAuthorized = true;
                }
            }
        } catch (Exception slex) {

        }
        return isAuthorized;
    }

    public boolean isAuthorizedUserCreateIssue(int isManager, int isTeamLead, String accessKey) {
        boolean isAuthorized = false;
        // System.err.print("roleId in  AUTH******---"+roleId);
        try {

            int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_LEADS"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for (int counter = 0; counter < authorizedRoleIdsArray.length; counter++) {
                if (isTeamLead == Integer.parseInt(authorizedRoleIdsArray[counter]) || isManager == Integer.parseInt(authorizedRoleIdsArray[counter])) {
                    isAuthorized = true;
                }
            }
        } catch (Exception slex) {

        }
        return isAuthorized;
    }

    public boolean isAuthorizedUser(HttpServletRequest request, String invokeaction, Map<String, Object> userDetailsMap) throws ServiceLocatorException {
        boolean isAuthorized = false;
        int invokuserId = Integer.parseInt(userDetailsMap.get("userId").toString());

        int invokusrorgid = Integer.parseInt(userDetailsMap.get("orgId").toString());
        // Map userrolesFromsession = (HashMap) userDetailsMap.get("rolesMap");
        int primaryRole = Integer.parseInt(userDetailsMap.get("primaryrole").toString());

        String typeOfUsr = userDetailsMap.get("typeOfUsr").toString();
        // System.err.print("roleId in  AUTH******---"+roleId);
        try {
//            for (Iterator iterator = parameters.keySet().iterator(); iterator.hasNext();) {
//                String key = (String) iterator.next();
//                String[] obj = (String[]) (parameters.get(key));
//                if (obj.length > 0) {
//                    System.out.println(key + "<>" + obj[0]);
//                }
//            }
//            
            System.out.println("parameters.get()-->out side --> accountSearchID" + request.getParameter("accountSearchID"));
            System.out.println("invokusrorgid-->" + invokusrorgid);

            if ("viewAccount".equalsIgnoreCase(invokeaction) || "setContacts".equalsIgnoreCase(invokeaction)) {
                if (primaryRole == 0 || primaryRole == 1) {
                    isAuthorized = true;
                } else if ((Integer.parseInt(request.getParameter("accountSearchID").toString())) == invokusrorgid) {
                    isAuthorized = true;
                }
            } else if ("getLoginUserRequirementList".equalsIgnoreCase(invokeaction)) {
                if ((Integer.parseInt(request.getParameter("orgid").toString())) == invokusrorgid) {
                    System.out.println("parameters.get()-->orgid" + request.getParameter("orgid"));
                    isAuthorized = true;
                }
            } else if ("accountcontactedit".equalsIgnoreCase(invokeaction)) {
                if (primaryRole == 0 || primaryRole == 1) {
                    isAuthorized = true;
                } else if ((Integer.parseInt(request.getParameter("accountSearchID").toString())) == invokusrorgid) {
                    System.out.println("parameters.get()-->accountSearchID" + request.getParameter("accountSearchID"));

                    if (primaryRole == 2 || primaryRole == 8 || primaryRole == 13) {
                        int contactId = Integer.parseInt(request.getParameter("contactId").toString());
                        if (DataSourceDataProvider.getInstance().checkUsrBelongsToHisOrg(invokusrorgid, contactId)) {
                            isAuthorized = true;
                        }
                    } else {
                        if ((Integer.parseInt(request.getParameter("contactId").toString())) == invokuserId) {
                            isAuthorized = true;
                        }
                    }

                }
            } else if ("addRequirements".equalsIgnoreCase(invokeaction)) {
                if ((Integer.parseInt(request.getParameter("accountSearchID").toString())) == invokusrorgid) {
                    System.out.println("parameters.get()-->accountSearchID" + request.getParameter("accountSearchID"));
                    isAuthorized = true;
                }
                // have to put more conditions 
            } else if ("requirementedit".equalsIgnoreCase(invokeaction)) {
                if (primaryRole == 1) {
                    isAuthorized = true;
                } else {
                    String reqExists = DataSourceDataProvider.getInstance().checkReqExists(Integer.parseInt(request.getParameter("requirementId").toString()), typeOfUsr, invokusrorgid);
                    if ((Integer.parseInt(request.getParameter("accountSearchID").toString())) == invokusrorgid && "allow".equals(reqExists)) {
                        System.out.println("parameters.get()-->accountSearchID" + request.getParameter("accountSearchID"));
                        System.out.println("parameters.get()-->requirementId" + request.getParameter("requirementId"));
                        isAuthorized = true;
                    }
                    // have to put more conditions 
                }
            } else if ("getUserGroping".equalsIgnoreCase(invokeaction)) {
                if ("add".equals(request.getParameter("addOrUpdate"))) {
                    isAuthorized = true;
                } else {
                    int groupingId = Integer.parseInt(request.getParameter("groupingId").toString());
                    int userId = 0;
                    if (request.getParameter("userId") != null) {
                        userId = Integer.parseInt(request.getParameter("userId").toString());
                    }
                    String allowOrDeny = DataSourceDataProvider.getInstance().checkGroupingId(invokusrorgid, groupingId, userId);
                    if ("allow".equals(allowOrDeny)) {
                        isAuthorized = true;
                    }
                }
            } else if ("doAddOrEditHomeRedirect".equalsIgnoreCase(invokeaction)) {
                int homeRedirectActionId = Integer.parseInt(request.getParameter("homeRedirectActionId").toString());
                if (homeRedirectActionId == 0) {
                    isAuthorized = true;
                } else {
                    int homeId = Integer.parseInt(request.getParameter("homeRedirectActionId").toString());
                    String allowOrDeny = DataSourceDataProvider.getInstance().checkHomeRedirectId(invokusrorgid, homeId, primaryRole);
                    System.out.println("checkHomeRedirect" + allowOrDeny);
                    if ("allow".equals(allowOrDeny)) {
                        isAuthorized = true;
                    }
                }

            } else if ("costCenterInfo".equalsIgnoreCase(invokeaction)) {
                String ccCode = request.getParameter("ccCode");
                String allowOrDeny = DataSourceDataProvider.getInstance().checkCostCenterCode(invokusrorgid, ccCode);
                System.out.println("checkHomeRedirect" + allowOrDeny);
                if ("allow".equals(allowOrDeny)) {
                    isAuthorized = true;
                }

            } else if ("goInvoiceEditDetails".equalsIgnoreCase(invokeaction)) {
                int invoiceId = Integer.parseInt(request.getParameter("invoiceId").toString());
                int userId = Integer.parseInt(request.getParameter("usrId").toString());
                String accType = typeOfUsr;
                String allowOrDeny = DataSourceDataProvider.getInstance().checkInvoiceId(invokusrorgid, invoiceId, userId, accType);
                System.out.println("goInvoiceEditDetails" + allowOrDeny);
                if ("allow".equals(allowOrDeny)) {
                    isAuthorized = true;
                }

            } else if ("getTimeSheets".equalsIgnoreCase(invokeaction)) {
                int invoiceId = Integer.parseInt(request.getParameter("timesheetid").toString());
                int userId = Integer.parseInt(request.getParameter("usr_id").toString());
                String flag = request.getParameter("timesheetFlag");
                System.out.println("flag" + flag);
//                System.out.println("invokuserId--->"+invokuserId);
                if ("My".equals(flag)) {
                    userId = invokuserId;
                }

                String allowOrDeny = DataSourceDataProvider.getInstance().checkTimesheetAuthId(invokusrorgid, invoiceId, userId, flag, primaryRole, invokuserId);
                System.out.println("goInvoiceEditDetails" + allowOrDeny);
                if ("allow".equals(allowOrDeny)) {
                    isAuthorized = true;
                }

            } else if ("getTaskDetails".equalsIgnoreCase(invokeaction)) {
                int taskId = Integer.parseInt(request.getParameter("taskid").toString());
                // int userId    = Integer.parseInt(request.getParameter("usr_id").toString());
                String flag = request.getParameter("myTask");
                System.out.println("taskId--->" + taskId);

                String allowOrDeny = DataSourceDataProvider.getInstance().checkTasksAuthId(invokusrorgid, taskId, flag, invokuserId);
                System.out.println("getTaskDetails" + allowOrDeny);
                if ("allow".equals(allowOrDeny)) {
                    isAuthorized = true;
                }

            } else if ("getConsultantDetails".equalsIgnoreCase(invokeaction)) {
//                 System.out.println("consultantId------>"+request.getParameter("consult_id"));
//                System.out.println("requirementId------>"+request.getParameter("requirementId"));
//             
                int consultantId = Integer.parseInt(request.getParameter("consult_id").toString());
                // int userId    = Integer.parseInt(request.getParameter("usr_id").toString());
                int requirementId = Integer.parseInt(request.getParameter("requirementId").toString());;
                //  System.out.println("invokuserId--->"+invokuserId);
//                System.out.println("consultantId------>"+consultantId);
//                System.out.println("requirementId------>"+requirementId);
                String allowOrDeny = DataSourceDataProvider.getInstance().checkConsultantExistPerReqId(consultantId, requirementId);
                System.out.println("goInvoiceEditDetails" + allowOrDeny);
                if ("allow".equals(allowOrDeny)) {
                    isAuthorized = true;
                }

            } else if ("projectDetails".equalsIgnoreCase(invokeaction)) {
                int avail = 0;
                if ((Integer.parseInt(request.getParameter("accountID").toString())) == invokusrorgid) {
                    System.out.println("parameters.get()-->accountSearchID" + request.getParameter("accountID"));
                    avail = DataSourceDataProvider.getInstance().projectIdExistOrNotInOrg(invokusrorgid, (Integer.parseInt(request.getParameter("projectID").toString())));
                    if (avail > 0) {
                        System.out.println("parameters.get()-->projectID" + request.getParameter("projectID"));
                        isAuthorized = true;
                    }
                }
            } else if ("editConsultantDetails".equalsIgnoreCase(invokeaction)) {
                int avail = 0;
                int invokeId = 0;
                if (primaryRole == 9) {
                    invokeId = invokusrorgid;
                } else {
                    invokeId = invokuserId;
                }
                avail = DataSourceDataProvider.getInstance().consultExistOrNotForOrg(primaryRole, invokeId, (Integer.parseInt(request.getParameter("consult_id").toString())));
                if (avail > 0) {
                    System.out.println("parameters.get()-->consult_id" + request.getParameter("consult_id"));
                    isAuthorized = true;
                }
            } else if ("setTeamMembersForProject".equalsIgnoreCase(invokeaction)) {
                int avail = 0;
                if ("addMember".equalsIgnoreCase(request.getParameter("projectFlag"))) {
                    avail = DataSourceDataProvider.getInstance().projectIdExistOrNotForOrg(invokusrorgid, (Integer.parseInt(request.getParameter("projectID").toString())),
                            ((request.getParameter("projectFlag"))), 0);
                } else {
                    avail = DataSourceDataProvider.getInstance().projectIdExistOrNotForOrg(invokusrorgid, (Integer.parseInt(request.getParameter("projectID").toString())),
                            ((request.getParameter("projectFlag"))), (Integer.parseInt(request.getParameter("userID").toString())));
                }
                if (avail > 0) {
                    System.out.println("parameters.get()-->projectID" + request.getParameter("projectID"));
                    isAuthorized = true;
                }
                //isAuthorized = true;
            } else if ("SOWEditAction".equalsIgnoreCase(invokeaction)) {
                int avail = 0;
                avail = DataSourceDataProvider.getInstance().serviceIdExistOrNotForOrg(primaryRole, invokusrorgid,
                        (Integer.parseInt(request.getParameter("serviceId").toString())), (request.getParameter("serviceType")));
                if (avail > 0) {
                    System.out.println("parameters.get()-->serviceId" + request.getParameter("serviceId"));
                    isAuthorized = true;
                }
            } else if ("sowRecreateEdit".equalsIgnoreCase(invokeaction)) {
                int avail = 0;
                avail = DataSourceDataProvider.getInstance().serviceIdExistOrNotInRecreatedListForOrg(primaryRole, invokusrorgid,
                        (Integer.parseInt(request.getParameter("serviceId").toString())), (Integer.parseInt(request.getParameter("his_id").toString())));
                if (avail > 0) {
                    System.out.println("parameters.get()-->serviceId" + request.getParameter("serviceId"));
                    isAuthorized = true;
                }
            } else {
                isAuthorized = true;
            }
        } catch (Exception slex) {
        }
        System.out.println("isAuthorized-->" + isAuthorized);
        return isAuthorized;
    }

}
