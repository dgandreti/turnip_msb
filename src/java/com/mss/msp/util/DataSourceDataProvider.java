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
 * File : DataSourceDataProvider.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.mss.msp.acc.AccountAction;
import com.mss.msp.recruitment.RecruitmentAction;
import com.mss.msp.requirement.RequirementVTO;
import com.mss.msp.sag.sow.SOWAction;
import com.mss.msp.usr.task.TaskHandlerAction;
import com.mss.msp.usr.task.TasksVTO;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author miracle
 */
public class DataSourceDataProvider {

    private static DataSourceDataProvider _instance;
    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * Creates a new instance of DataSourceDataProvider
     */
    private DataSourceDataProvider() {
    }

    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static DataSourceDataProvider getInstance() throws ServiceLocatorException {
        try {
            if (_instance == null) {
                _instance = new DataSourceDataProvider();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }

    /**
     * *****************************************************************************
     * Date : March 03 2015
     *
     * Author : Prasad Kandregula
     *
     * ForUse : getUserIdAndStatusByEmail() method is used to get
     *
     * *****************************************************************************
     */
    public String getUserIdAndStatusByEmail(String email) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUserIdAndStatusByEmail Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        int usr_Id = 0;
        String status = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT u.usr_id,cur_status FROM users u LEFT OUTER JOIN usr_reg ur "
                + "ON(u.usr_id=ur.usr_id) WHERE login_id LIKE '" + email.trim() + "'";
        System.out.println("getUserIdAndStatusByEmail :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                usr_Id = resultSet.getInt("usr_id");
                status = resultSet.getString("cur_status");
                isRecordExists = 1;
            }
            if (isRecordExists == 1) {
                resultString = usr_Id + "^" + status;
            } else {
                resultString = "NoRecordExists";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUserIdAndStatusByEmail Method End*********************");
        return resultString;
    }

    /**
     *
     * @param emailId
     * @return
     * @throws ServiceLocatorException
     */
    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkLoginIdExistance() method is used to
     *
     * *****************************************************************************
     */
    public int checkLoginIdExistance(String emailId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: checkLoginIdExistance Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "Select count(usr_id) as id from users where email1 like '" + emailId + "'";
        System.out.println("checkLoginIdExistance :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: checkLoginIdExistance Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getUsrRolesMap() method is used to
     *
     * *****************************************************************************
     */
    public Map getUsrRolesMap(int usrId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUsrRolesMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map rolesMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select r.role_id as roleId,role_name from usr_roles ur left outer join roles r on(ur.role_id=r.role_id) where usr_id=" + usrId;
        System.out.println("getUsrRolesMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                rolesMap.put(resultSet.getInt("roleId"), resultSet.getString("role_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUsrRolesMap Method End*********************");
        return rolesMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getUsrPrimaryRole() method is used to
     *
     * *****************************************************************************
     */
    public String getUsrPrimaryRole(int usrId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUsrPrimaryRole Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int primaryrole = 0;
        String resultString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT r.role_id as roleId,role_name FROM usr_roles ur LEFT OUTER JOIN roles r ON(ur.role_id=r.role_id) WHERE usr_id=" + usrId + " and primary_flag=1";
        System.out.println("getUsrPrimaryRole :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                primaryrole = resultSet.getInt("roleId");
                resultString = primaryrole + "#" + resultSet.getString("role_name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUsrPrimaryRole Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getOrgIdByEmailExt() method is used to
     *
     * *****************************************************************************
     */
    public int getOrgIdByEmailExt(String loginId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getOrgIdByEmailExt Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int orgId = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT org_id FROM siteaccess_mail_ext WHERE email_ext='" + loginId.split("\\@")[1] + "'";
        System.out.println("getOrgIdByEmailExt :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                orgId = resultSet.getInt("org_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getOrgIdByEmailExt Method End*********************");
        return orgId;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTaskStatusByOrgId() method is used to
     *
     * *****************************************************************************
     */
    public Map getTaskStatusByOrgId() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTaskStatusByOrgId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map tasksStatusMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select id,task_status_name from lk_task_status";
        System.out.println("getTaskStatusByOrgId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksStatusMap.put(resultSet.getInt("id"), resultSet.getString("task_status_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTaskStatusByOrgId Method End*********************");
        return tasksStatusMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTaskrelatedToMap() method is used to
     *
     * *****************************************************************************
     */
    //get task related to map
    public Map getTaskrelatedToMap(int roleId, String userType, int userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTaskrelatedToMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map tasksRelatedtoMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        if ("VC".equals(userType)) {
            int checked = getUsrExistedOrNotProject(userId);
            if (checked == 0) {
                queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active' AND task_relatedto_id=2";
            } else {
                if (roleId == 8) {
                    queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active'";
                } else {
                    queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active' AND task_relatedto_id=1";
                }
            }
        } else {
            if (roleId == 1) {
                queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active' AND task_relatedto_id=2";
            } else {
                int checked = getUsrExistedOrNotProject(userId);
                if (checked == 0) {
                    queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active' AND task_relatedto_id=2";
                } else {
                    queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active'";
                }
            }
        }
        System.out.println("getTaskrelatedToMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksRelatedtoMap.put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTaskrelatedToMap Method End*********************");
        return tasksRelatedtoMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTaskrelatedToMapByOrgId() method is used to
     *
     * *****************************************************************************
     */
    //task related to by orgId
    public Map getTaskrelatedToMapByOrgId(String orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTaskrelatedToMapByOrgId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map tasksRelatedtoMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active' AND org_id=" + orgId + "";
        System.out.println("getTaskrelatedToMapByOrgId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksRelatedtoMap.put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTaskrelatedToMapByOrgId Method End*********************");
        return tasksRelatedtoMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTasksTypeByRelatedId() method is used to
     *
     * *****************************************************************************
     */
    public Map getTasksTypeByRelatedId(String relatedToId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTasksTypeByRelatedId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map tasksTypesMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT task_types_id,task_type_name FROM lk_task_types WHERE STATUS LIKE 'Active' AND task_rel_toId==" + relatedToId + "";
        System.out.println("getTasksTypeByRelatedId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksTypesMap.put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTasksTypeByRelatedId Method End*********************");
        return tasksTypesMap;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     * RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getMyTeamMembers() method is used to getting MyTeamMembers based
     * on userId and return map object.
     *
     * *****************************************************************************
     */
    public Map getMyTeamMembers(int reportsTo) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getMyTeamMembers Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        Map myTeamMembers = new TreeMap();
        try {
            queryString = "SELECT pt.usr_id,pt.designation,first_name,last_name,pt.current_status FROM project_team pt LEFT OUTER JOIN users u ON ((pt.usr_id=u.usr_id)) WHERE pt.reportsto1=? AND pt.current_status LIKE 'Active'";
            System.out.println("getMyTeamMembers :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            myTeamMembers = getMyTeamMembersUpTo(reportsTo, preparedStatement);
            //Closing Cache Checking
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getMyTeamMembers Method End*********************");
        return myTeamMembers; // returning the object.
    }

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     * RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getMyTeamMembersUpTo() method is used to getting MyTeamMembers
     * based under userId and return map object.
     *
     * *****************************************************************************
     */
    public Map getMyTeamMembersUpTo(int reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getMyTeamMembersUpTo Method Start*********************");
        ResultSet resultSet = null;
        Map myTeamManagersMap = new TreeMap();
        Map tempMap = new TreeMap();
        int[] keys = new int[100];
        int keyCnt = 0;
        int key = 0;
        String value = null;
        try {
            theStatement.setInt(1, reportsTo);
            resultSet = theStatement.executeQuery();
            while (resultSet.next()) {
                key = resultSet.getInt("usr_id");
                value = resultSet.getString("first_name") + "." + resultSet.getString("last_name");
                myTeamManagersMap.put(key, value);
                if (DataUtility.getInstance().getTimsheetAccessingRolesList().contains(resultSet.getInt("designation"))) {
                    keys[keyCnt] = key;
                    keyCnt++;
                }
            }
            for (int i = 0; i < keyCnt; i++) {
                key = keys[i];
                tempMap = getMyTeamMembersUpTo(key, theStatement);
                if (tempMap.size() > 0) {
                    Iterator tempIterator = tempMap.entrySet().iterator();
                    while (tempIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) tempIterator.next();
                        key = Integer.parseInt(entry.getKey().toString());
                        value = entry.getValue().toString();
                        myTeamManagersMap.put(key, value);
                        entry = null;
                    }
                }
            }
            myTeamManagersMap = sortMapByValues(myTeamManagersMap);
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getMyTeamMembersUpTo Method End*********************");
        return myTeamManagersMap; // returning the object.
    } //closing the method

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST
     *
     * Author : Praveenkumar<pkatru@miraclesoft.com>
     * RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : sorting of map taken from Nagireddy<nseerapu@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public < K, V extends Comparable< ? super V>> Map< K, V> sortMapByValues(final Map< K, V> mapToSort) {
        System.out.println("********************DataSourceDataProvider :: sortMapByValues Method Start*********************");
        List< Map.Entry< K, V>> entries = new ArrayList< Map.Entry< K, V>>(mapToSort.size());
        entries.addAll(mapToSort.entrySet());
        Collections.sort(entries,
                new Comparator< Map.Entry< K, V>>() {
                    public int compare(
                            final Map.Entry< K, V> entry1,
                            final Map.Entry< K, V> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    }
                });

        Map< K, V> sortedMap = new LinkedHashMap< K, V>();
        for (Map.Entry< K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("********************DataSourceDataProvider :: sortMapByValues Method End*********************");
        return sortedMap;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 18, 2015, 2:23 AM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : getMyTeamMembersUpTo() method is used to getting child
     * organizations names based on organization id.
     *
     * *****************************************************************************
     */
    public Map getOrganizationRelations(int org_id) {
        System.out.println("********************DataSourceDataProvider :: getOrganizationRelations Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map childmap = new LinkedHashMap();
        queryString = "SELECT DISTINCT account_id,account_name FROM accounts JOIN org_rel ON(account_id=related_org_id) AND org_id=? WHERE type_of_relation='M' OR type_of_relation='CH'";
        System.out.println("getOrganizationRelations :: query string ------>" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, org_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                childmap.put(resultSet.getInt("account_id"), resultSet.getString("account_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getOrganizationRelations Method End*********************");
        return childmap;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 04, 2015, 2:23 AM IST
     *
     * Author : Kiran Arogya
     *
     * ForUse : getFnameandLnamebyUserid() method is used to get first name and
     * last name based on userid from users table.
     *
     * *****************************************************************************
     */
    public String getFnameandLnamebyUserid(int userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getFnameandLnamebyUserid Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String user_name = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=" + userId;
        System.out.println("getFnameandLnamebyUserid :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                user_name = resultSet.getString("NAME");
                isRecordExists = 1;
            }
            if (isRecordExists == 1) {
                resultString = user_name;
            } else {
                resultString = " - ";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getFnameandLnamebyUserid Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getAttachmentDetails() method is used to getting attachment
     * details based on the task id.
     *
     * *****************************************************************************
     */
    public List getAttachmentDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAttachmentDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList<TasksVTO> fileslist = new ArrayList<TasksVTO>();
        try {
            queryString = "SELECT id,attachment_name,attachment_path FROM task_attachments WHERE task_id=" + taskHandlerAction.getTaskid() + " AND STATUS='Active'";
            System.out.println("getAttachmentDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TasksVTO tasksVTO = new TasksVTO();
                tasksVTO.setAttachmentId(resultSet.getString("id"));
                tasksVTO.setAttachmentName(resultSet.getString("attachment_name"));
                tasksVTO.setAttachmentPath(resultSet.getString("attachment_path"));
                fileslist.add(tasksVTO);
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAttachmentDetails Method End*********************");
        return fileslist;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getAttachmentLocation() method is used to getting attachment
     * data based on the attachment id.
     *
     * *****************************************************************************
     */
    public String getAttachmentLocation(int id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAttachmentLocation Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String attachmentLocation = "";
        try {
            queryString = "SELECT id,attachment_name,attachment_path FROM task_attachments WHERE id=" + id + "";
            System.out.println("getAttachmentLocation :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation = resultSet.getString("attachment_path");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAttachmentLocation Method End*********************");
        return attachmentLocation;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getTaskTypeById() method is used to getting task type based on
     * task id.
     *
     * *****************************************************************************
     */
    public Map getTaskTypeById(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTaskTypeById Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map map = new HashMap();
        String querystrings = "SELECT p.proj_name,p.project_id FROM acc_projects p "
                + "LEFT OUTER JOIN prj_sub_prjteam t ON(t.sub_project_id=p.project_id) "
                + "WHERE t.usr_id=?";
        System.out.println("getTaskTypeById :: query string ------>" + querystrings);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(querystrings);
            preparedStatement.setInt(1, taskHandlerAction.getUserSessionId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getInt("project_id"), resultSet.getString("proj_name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTaskTypeById Method End*********************");
        return map;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getPrimaryAssignTo() method is used to getting primary assigned
     * to based on task id.
     *
     * *****************************************************************************
     */
    public Map getPrimaryAssignTo(int taskId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getPrimaryAssignTo Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map map = new HashMap();
        String string = "SELECT ur.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM task_list t  JOIN  usr_roles ur ON (t.task_related_to=ur.role_id) JOIN users u ON(ur.usr_id=u.usr_id) WHERE t.task_id=?";
        System.out.println("getPrimaryAssignTo :: query string ------>" + string);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(string);
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getPrimaryAssignTo Method End*********************");
        return map;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 28, 2015, 11:23 PM IST
     *
     * Author:Praveenkumar<pkatru@miraclesoft.com>
     *
     * ForUse : getReportingEmailId() method is used to getting email id by
     * passing list of user id's.
     *
     * *****************************************************************************
     */
    public List getReportingEmailId(List listUserIds) {
        System.out.println("********************DataSourceDataProvider :: getReportingEmailId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList emailId = new ArrayList();
        String ids = "";
        ids = listUserIds.get(0).toString() + "," + listUserIds.get(1).toString();
        try {
            queryString = "SELECT email1 from users where usr_id in (" + ids + ")";
            System.out.println("getReportingEmailId :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                emailId.add(resultSet.getString("email1"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReportingEmailId Method End*********************");
        return emailId;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 28, 2015, 11:23 PM IST
     *
     * Author:Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : getEmailIdbyuser() method is used to getting email id by using
     * user id.
     *
     * *****************************************************************************
     */
    public String getEmailIdbyuser(int userid) {
        System.out.println("********************DataSourceDataProvider :: getEmailIdbyuser Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String email = "";
        try {
            queryString = "SELECT email1 from users where usr_id= " + userid;
            System.out.println("getEmailIdbyuser :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                email = resultSet.getString("email1");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getEmailIdbyuser Method End*********************");
        return email;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getUserIdByLeaveId() method is used to
     *
     * *****************************************************************************
     */
    public int getUserIdByLeaveId(int leave_id) {
        System.out.println("********************DataSourceDataProvider :: getUserIdByLeaveId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int usr_id = 0;
        try {
            queryString = "SELECT usr_id from usr_leaves where leave_id= " + leave_id;
            System.out.println("getUserIdByLeaveId :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                usr_id = resultSet.getInt("usr_id");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUserIdByLeaveId Method End*********************");
        return usr_id;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getEmailId() method is used to get emailId by userid.
     *
     * *****************************************************************************
     */
    public String getEmailId(int userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getEmailId Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String mailId = "";
        String string = "SELECT email1 from users WHERE usr_id=?";
        System.out.println("getEmailId :: query string ------>" + string);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(string);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mailId = resultSet.getString("email1");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getEmailId Method End*********************");
        return mailId;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getStatusById() method is used to
     *
     * *****************************************************************************
     */
    public String getStatusById(int statusId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getStatusById Method Start*********************");
        String status = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String string = "SELECT task_status_name from lk_task_status WHERE id=?";
        System.out.println("getStatusById :: query string ------>" + string);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(string);
            preparedStatement.setInt(1, statusId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = resultSet.getString("task_status_name");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getStatusById Method End*********************");
        return status;
    }

    /**
     * *****************************************************************************
     * Date : April 29 2015
     *
     * Author: Kiran Arogya
     *
     * ForUse : getFirstnameandLastnameByEmail() method is used to get first
     * name and last name based on email id.
     *
     * *****************************************************************************
     */
    public String getFirstnameandLastnameByEmail(String email) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getFirstnameandLastnameByEmail Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        int usr_Id = 0;
        String name = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT CONCAT(first_name,' ',last_name) AS NAME,usr_id  FROM users WHERE email1='" + email.trim() + "'";
        System.out.println("getFirstnameandLastnameByEmail :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                name = resultSet.getString("NAME");
                usr_Id = resultSet.getInt("usr_id");
                isRecordExists = 1;
            }
            if (isRecordExists == 1) {
                resultString = name;
            } else {
                resultString = "NoRecordExists";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getFirstnameandLastnameByEmail Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getCountryNames() method is used to get country names
     *
     * *****************************************************************************
     */
    public Map getCountryNames() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getCountryNames Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map countryNameMap = new LinkedHashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select id, country from lk_country ORDER BY country ASC";
        System.out.println("getCountryNames :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                countryNameMap.put(resultSet.getInt("id"), resultSet.getString("country"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCountryNames Method End*********************");
        return countryNameMap;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author: Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getVendorType() method is used to getting vendor Types.
     *
     * *****************************************************************************
     */
    public Map getVendorType() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getVendorType Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map vendorTypeMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select id,acc_type_name from lk_acc_type";
        System.out.println("getVendorType :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorTypeMap.put(resultSet.getInt("id"), resultSet.getString("acc_type_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorType Method End*********************");
        return vendorTypeMap;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author: Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getIndystryTypes() method is used to getting industry types.
     *
     * *****************************************************************************
     */
    public Map getIndystryTypes() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getIndystryTypes Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map industryList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select id,acc_industry_name from lk_acc_industry_type where status='Active'";
        System.out.println("getIndystryTypes :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryList.put(resultSet.getInt("id"), resultSet.getString("acc_industry_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getIndystryTypes Method End*********************");
        return industryList;
    }

    /**
     * *****************************************************************************
     * Date : 04/29/2015
     *
     * Author: Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getOrganizationByOrgId() method is used to set the organization
     * name in add consultant field.
     *
     * *****************************************************************************
     */
    public Map getOrganizationByOrgId(int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getOrganizationByOrgId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map organizationNameMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT a.account_id, a.account_name FROM accounts a, org_rel o WHERE a.account_id=o.related_org_id AND o.STATUS='active' AND o.org_id=" + orgId;
        System.out.println("getOrganizationByOrgId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                organizationNameMap.put(resultSet.getInt("a.account_id"), resultSet.getString("a.account_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getOrganizationByOrgId Method End*********************");
        return organizationNameMap;
    }

    /**
     * *****************************************************************************
     * Date : 04/29/2015
     *
     * Author: Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getIndustryName() method is used to set the industry name in add
     * consultant field.
     *
     * *****************************************************************************
     */
    public Map getIndustryName() throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************DataSourceDataProvider :: getIndustryName Method Start*********************");
        Map industryNameMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id, acc_industry_name FROM lk_acc_industry_type WHERE STATUS='active'";
        System.out.println("getIndustryName :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryNameMap.put(resultSet.getInt("id"), resultSet.getString("acc_industry_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getIndustryName Method End*********************");
        return industryNameMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getYearsOfExp() method is used
     *
     * *****************************************************************************
     */
    public Map getYearsOfExp() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getYearsOfExp Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map ExperienceMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,exp_years FROM lk_years_of_exp";
        System.out.println("getYearsOfExp :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ExperienceMap.put(resultSet.getInt("id"), resultSet.getString("exp_years"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getYearsOfExp Method End*********************");
        return ExperienceMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getNameByOrgId() method is used
     *
     * *****************************************************************************
     */
    public Map getNameByOrgId(int org_id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getNameByOrgId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map EmployeeNameMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT usr_id,CONCAT_WS(' ',first_name,last_name) AS name1 FROM users WHERE org_id=" + org_id;
        System.out.println("getNameByOrgId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                EmployeeNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("name1"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getNameByOrgId Method End*********************");
        return EmployeeNameMap;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author : Praveen <pkatru@miraclesoft.com>
     *
     * ForUse : getVendorTierTypes() method is used to getting vendor types
     * return map object
     *
     * *****************************************************************************
     */
    public Map getVendorTierTypes() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getVendorTierTypes Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map industryList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select id,vendor_type from lk_vendor_type";
        System.out.println("getVendorTierTypes :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryList.put(resultSet.getInt("id"), resultSet.getString("vendor_type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorTierTypes Method End*********************");
        return industryList;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getStateNameById() method is used to
     *
     * *****************************************************************************
     */
    public String getStateNameById(String stateId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getStateNameById Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String name = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT NAME FROM lk_states WHERE id=" + stateId;
        System.out.println("getStateNameById :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                name = resultSet.getString("NAME");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getStateNameById Method End*********************");
        return name;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getFnameandLnamebyStringId() method is used to
     *
     * *****************************************************************************
     */
    public String getFnameandLnamebyStringId(String userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getFnameandLnamebyStringId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String user_name = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=" + userId;
        System.out.println("getFnameandLnamebyStringId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                user_name = resultSet.getString("NAME");
                isRecordExists = 1;
            }
            if (isRecordExists == 1) {
                resultString = user_name;
            } else {
                resultString = "";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getFnameandLnamebyStringId Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getEmailPhoneDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getEmailPhoneDetails(int userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getEmailPhoneDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String details = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT email1,phone1 FROM users WHERE usr_id=" + userId;
        System.out.println("getEmailPhoneDetails :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                details += resultSet.getString("email1") + "|" + resultSet.getString("phone1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getEmailPhoneDetails Method End*********************");
        return details;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRecruitmentDeptNames() method is used to
     *
     * *****************************************************************************
     */
    public Map getRecruitmentDeptNames(int org_id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getRecruitmentDeptNames Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map EmployeeNameMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT usr_id,CONCAT(first_name,'.',last_name) AS NAMES FROM users  WHERE org_id=" + org_id;
        System.out.println("getRecruitmentDeptNames :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                EmployeeNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getRecruitmentDeptNames Method End*********************");
        return EmployeeNameMap;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author : praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : isVendor() method is used to getting vendor is vendor or not
     *
     * *****************************************************************************
     */
    public boolean isVendor(int acc_id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: isVendor Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        boolean isvendor = false;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select * from org_rel where acc_type=5 and related_org_id=" + acc_id;
        System.out.println("isVendor :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                isvendor = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: isVendor Method End*********************");
        return isvendor;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getSalesTeam() method is used to
     *
     * *****************************************************************************
     */
    public Map getSalesTeam(int vendorId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getSalesTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map salesTeamList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
                + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) "
                + "WHERE m.role_id=3";
        System.out.println("getSalesTeam :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                salesTeamList.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getSalesTeam Method End*********************");
        return salesTeamList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getVendorSalesTeam() method is used to
     *
     * *****************************************************************************
     */
    public Map getVendorSalesTeam(int vendorId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getVendorSalesTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map vendorSalesTeamList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT a.teamMember_Id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM accteam a LEFT OUTER JOIN users u ON(u.usr_id=a.teamMember_Id) WHERE a.acc_id=" + vendorId;
        System.out.println("getVendorSalesTeam :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorSalesTeamList.put(resultSet.getInt("teamMember_Id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorSalesTeam Method End*********************");
        return vendorSalesTeamList;
    }

    /**
     * *****************************************************************************
     * Date : May 11 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * For Use : getAccountNameById() method can be used to get account name by
     * using org id, And returns accounts name
     * *****************************************************************************
     */
    public String getAccountNameById(int accountId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAccountNameById Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String account_name = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT account_name FROM accounts WHERE account_id=" + accountId;
        System.out.println("getAccountNameById :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                account_name = resultSet.getString("account_name");
                isRecordExists = 1;
            }
            if (isRecordExists == 1) {
                resultString = account_name;
            } else {
                resultString = " - ";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAccountNameById Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 05/12/2015
     *
     * Author : Aklakh Ahmad
     *
     * ForUse : checkConsultantLoginId() method is used to check the consultant
     * existence
     *
     * *****************************************************************************
     */
    public int checkConsultantLoginId(String emailId, int usrId, int vendorId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: checkConsultantLoginId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "Select count(*) as id from users where email1 like '" + emailId + "'"
                + " AND created_by_org_id=" + vendorId + " ";
        System.out.println("checkConsultantLoginId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: checkConsultantLoginId Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAllStates() method is used to
     *
     * *****************************************************************************
     */
    public Map getAllStates() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAllStates Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map stateMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,name FROM lk_states";
        System.out.println("getAllStates :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                stateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAllStates Method End*********************");
        return stateMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAccTeam() method is used to
     *
     * *****************************************************************************
     */
    public Map getAccTeam(int accountSearchID) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAccTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map salesTeamList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
                + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) ";
        System.out.println("getAccTeam :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                salesTeamList.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAccTeam Method End*********************");
        return salesTeamList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAccSalesTeam() method is used to
     *
     * *****************************************************************************
     */
    public Map getAccSalesTeam(int accountSearchID) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAccSalesTeam Method Start*********************");
        Map accSalesTeamList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAccSalesTeam Method End*********************");
        return accSalesTeamList;
    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * For Use : getConsult_AttachmentLocation() is used to
     *
     *
     * *****************************************************************************
     */
    public String getConsult_AttachmentLocation(int consult_acc_attachment_id) {
        System.out.println("********************DataSourceDataProvider :: getConsult_AttachmentLocation Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String attachmentLocation = "";
        try {
            queryString = "SELECT attachment_path,attachment_name FROM acc_rec_attachment WHERE acc_attachment_id=" + consult_acc_attachment_id + "";
            System.out.println("getConsult_AttachmentLocation :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation += resultSet.getString("attachment_path") + File.separator + resultSet.getString("attachment_name");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getConsult_AttachmentLocation Method End*********************");
        return attachmentLocation;
    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : jagan chukkala<jchukkla@miraclesoft.com>
     *
     * For Use : getAllAccTeam() is used to
     *
     *
     * *****************************************************************************
     */
    public Map getAllAccTeam(int accountSearchID) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAllAccTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map allAccTeam = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT ur.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_roles ur LEFT OUTER JOIN users u ON (u.usr_id=ur.usr_id)WHERE ur.role_id=3";
        System.out.println("getAllAccTeam :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                allAccTeam.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAllAccTeam Method End*********************");
        return allAccTeam;
    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : jagan chukkala<jchukkla@miraclesoft.com>
     *
     * For Use : getPrimaryAccount() is used to get the primary account of
     * sales.
     *
     *
     * *****************************************************************************
     */
    public int getPrimaryAccount(int accountSearchId) throws ServiceLocatorException {
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return 0;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * For Use : getAllVendorTeam() is used to
     *
     * *****************************************************************************
     */
    public Map getAllVendorTeam() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAllVendorTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map allVendorTeam = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT ur.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_roles ur LEFT OUTER JOIN users u ON (u.usr_id=ur.usr_id)WHERE ur.role_id=3";
        System.out.println("getAllVendorTeam :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                allVendorTeam.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAllVendorTeam Method End*********************");
        return allVendorTeam;
    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * For Use : getCountry() method is used to getcountryName through country
     * id
     *
     * *****************************************************************************
     */
    public String getCountry(int id) {
        System.out.println("********************DataSourceDataProvider :: getCountry Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = " SELECT country FROM lk_country WHERE id=" + id;
            System.out.println("getCountry :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("country");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCountry Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * For Use : getStateName() method is used to get state name through country
     * id.
     *
     * *****************************************************************************
     */
    public String getStateName(int id) {
        System.out.println("********************DataSourceDataProvider :: getStateName Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = " SELECT name FROM lk_states WHERE id=" + id;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            System.out.println("getStateName :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("name");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getStateName Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * For Use : updateAccountLastAccessedBy() method is used to
     *
     * *****************************************************************************
     */
    public int updateAccountLastAccessedBy(int accId, int usrId, String accessDesc) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: updateAccountLastAccessedBy Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        int c = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString1 = "UPDATE accounts SET last_access_by=" + usrId + ",last_accdesc='" + accessDesc + "',last_access_date='" + DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE account_id=" + accId;
            System.out.println("updateAccountLastAccessedBy :: query string ------>" + queryString1);
            statement = connection.createStatement();
            c = statement.executeUpdate(queryString1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: updateAccountLastAccessedBy Method End*********************");
        return c;
    }

    /**
     * *****************************************************************************
     * Date : May 23 2015
     *
     * Author : Aklakh ahmad<mahmad@miraclesoft.com>
     *
     * For Use : getAdmin() method is used to get the admin role.
     *
     * *****************************************************************************
     */
    public int getAdmin(int usrId) {
        System.out.println("********************DataSourceDataProvider :: getAdmin Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int adminRole = 0;
        try {
            queryString = "SELECT COUNT(role_id)  AS id FROM usr_roles WHERE role_id=1 AND usr_id=" + usrId;
            System.out.println("getAdmin :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                adminRole = resultSet.getInt("id");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAdmin Method End*********************");
        return adminRole;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * For Use : getProjectMap() method is
     *
     * *****************************************************************************
     */
    public String getProjectMap(int userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getProjectMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String projectDetails = "";
        List projectList = new ArrayList();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT acp.Project_id,proj_name FROM acc_projects acp LEFT OUTER JOIN prj_sub_prjteam psp ON (acp.project_id=psp.sub_project_id) WHERE usr_id=" + userId + " AND current_status LIKE 'Active' limit 5";
        System.out.println("getProjectMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectDetails = projectDetails + resultSet.getInt("Project_id") + "|" + resultSet.getString("proj_name") + "^";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getProjectMap Method End*********************");
        return projectDetails;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * For Use : getAddVendorTierTypes() method is used to
     *
     * *****************************************************************************
     */
    public Map getAddVendorTierTypes(String id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAddVendorTierTypes Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map industryList = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,vendor_type FROM lk_vendor_type WHERE id NOT IN "
                + "(SELECT vendor_tier_id FROM customer_ven_rel WHERE customer_id=" + id + ")";
        System.out.println("getAddVendorTierTypes :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryList.put(resultSet.getInt("id"), resultSet.getString("vendor_type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAddVendorTierTypes Method End*********************");
        return industryList;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getAccountType() method is used to
     *
     * *****************************************************************************
     */
    public String getAccountType(int accId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAccountType Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String user_name = "";
        int isRecordExists = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT l.acc_type_name FROM lk_acc_type l LEFT OUTER JOIN org_rel o ON(l.id=o.acc_type) WHERE o.related_org_Id=" + accId;
        System.out.println("getAccountType :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                user_name = resultSet.getString("acc_type_name");
                isRecordExists = 1;
            }
            if (isRecordExists == 1) {
                resultString = user_name;
            } else {
                resultString = "";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAccountType Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : May 29, 2015, 11:23 PM IST
     *
     * Author : manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : getTypeOfAccount() method is used to getting account types
     * return VC OR AC object
     *
     * *****************************************************************************
     */
    public String getTypeOfAccount(int orgId) {
        System.out.println("********************DataSourceDataProvider :: getTypeOfAccount Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        int accType = 0;
        try {
            queryString = " SELECT acc_type FROM org_rel WHERE related_org_id=" + orgId;
            System.out.println("getTypeOfAccount :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                accType = resultSet.getInt("acc_type");
            }
            if (accType == 5) {
                resultString = "VC";
            }
            if (accType == 1) {
                resultString = "AC";
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTypeOfAccount Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/02/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getTierOneOrg_Id() method is used to getting tier one
     * organization id's return array
     *
     * *****************************************************************************
     */
    public ArrayList getTierOneOrg_Id(boolean flag, int org_id) {
        System.out.println("********************DataSourceDataProvider :: getTierOneOrg_Id Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList<Integer> array = new ArrayList<Integer>();
        try {
            if (flag) {
                queryString = "SELECT DISTINCT(vendor_id) FROM customer_ven_rel WHERE is_permanent=1 AND STATUS='Active' AND customer_id=" + org_id;
            } else {
                queryString = "SELECT DISTINCT(vendor_id) FROM customer_ven_rel WHERE vendor_tier_id=1 AND STATUS='Active' AND customer_id=" + org_id;
            }
            System.out.println("getTierOneOrg_Id :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                array.add(resultSet.getInt("vendor_id"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTierOneOrg_Id Method End*********************");
        return array;
    }

    /**
     * *****************************************************************************
     * Date : 05/15/2015
     *
     * Author : Aklakh Ahmad
     *
     * ForUse : getPermanentStates() method is used to get the permanent state
     * on the basic of basic country
     *
     * *****************************************************************************
     */
    public Map getPermanentStates(int conId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getPermanentStates Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map pStateMap = new LinkedHashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,name FROM lk_states where countryId=" + conId + " ORDER BY name ASC";
        System.out.println("getPermanentStates :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                pStateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getPermanentStates Method End*********************");
        return pStateMap;
    }

    /**
     * *****************************************************************************
     * Date : 05/15/2015
     *
     * Author : Aklakh Ahmad
     *
     * ForUse : getCurrentStates() method is used to get the current state on
     * the basic of basic country
     *
     * *****************************************************************************
     */
    public Map getCurrentStates(int cId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getCurrentStates Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map cStateMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,name FROM lk_states where countryId=" + cId;
        System.out.println("getCurrentStates :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                cStateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCurrentStates Method End*********************");
        return cStateMap;
    }

    /**
     * *****************************************************************************
     * Date : 05/15/2015
     *
     * Author : Aklakh Ahmad
     *
     * ForUse : getPreferredStates() method is used to get the preferred state
     * on the basic of basic country
     *
     * *****************************************************************************
     */
    public Map getPreferredStates(int countryId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getPreferredStates Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map stateMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,name FROM lk_states where countryId=" + countryId;
        System.out.println("getPreferredStates :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                stateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getPreferredStates Method End*********************");
        return stateMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getOrganizationName() method is used to
     *
     * *****************************************************************************
     */
    public String getOrganizationName(int aInt) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getOrganizationName Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String orgName = "";
        queryString = "SELECT account_name FROM accounts WHERE account_id=" + aInt;
        System.out.println("getOrganizationName :: query string ------>" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                orgName = resultSet.getString("account_name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getOrganizationName Method End*********************");
        return orgName;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : setRequirementDetails() method is used to
     *
     * *****************************************************************************
     */
    public RequirementVTO setRequirementDetails(String reqId) {
        System.out.println("********************DataSourceDataProvider :: setRequirementDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultStr = "";
        RequirementVTO requirementVTO = new RequirementVTO();
        try {
            queryString = "SELECT req_name,req_function_desc,req_st_date,no_of_resources FROM acc_requirements WHERE requirement_id=" + reqId;
            System.out.println("setRequirementDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                requirementVTO.setReqName(resultSet.getString("req_name"));
                requirementVTO.setReqDesc(resultSet.getString("req_function_desc"));
                requirementVTO.setReqStartDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("req_st_date")));
                requirementVTO.setReqResources(resultSet.getString("no_of_resources"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: setRequirementDetails Method End*********************");
        return requirementVTO;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getMailIdsOfVendorManagerAndLeads() method is used to
     *
     * *****************************************************************************
     */
    public String getMailIdsOfVendorManagerAndLeads(String vendorIdList) {
        System.out.println("********************DataSourceDataProvider :: getMailIdsOfVendorManagerAndLeads Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        try {
            queryString = "SELECT u.email1 FROM users u "
                    + "LEFT OUTER JOIN usr_grouping ug ON(ug.usr_id=u.usr_id) "
                    + "WHERE ug.cat_type=1 "
                    + "AND ug.is_primary=1 "
                    + "AND ug.STATUS='Active' "
                    + "AND u.cur_status='Active' "
                    + "AND u.org_id IN(" + vendorIdList + ")";
            System.out.println("getMailIdsOfVendorManagerAndLeads :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("email1") + ",";
            }
            if (null != resultString && resultString.length() > 0) {
                int endIndex = resultString.lastIndexOf(",");
                if (endIndex != -1) {
                    resultStr = resultString.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
                }
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getMailIdsOfVendorManagerAndLeads Method End*********************");
        return resultStr;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getMailIdsOfConAndEmp() method is used to
     *
     * *****************************************************************************
     */
    public void getMailIdsOfConAndEmp(RecruitmentAction recruitmentAction) {
        System.out.println("********************DataSourceDataProvider :: getMailIdsOfConAndEmp Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        try {
            queryString = "SELECT cd.job_title,CONCAT(c.first_name,'.',c.last_name)AS NAME,c.email1 AS conEmail,"
                    + "u.email1 AS empEmail,cd.consultant_skills,ct.scheduled_date,ct.scheduled_time,"
                    + "ct.forwarded_by,ar.req_name,ct.forwarded_to_name,ct.review_type,ct.interview_lacation,usr.email1 AS empEmail2 "
                    + "FROM users c "
                    + "LEFT OUTER JOIN con_techreview ct ON(ct.consultant_id=c.usr_id) "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=ct.forwarded_to) "
                    + "LEFT OUTER JOIN users usr ON(usr.usr_id=ct.forwarded_to1)"
                    + "LEFT OUTER JOIN usr_details cd ON(cd.usr_id=c.usr_id) "
                    + "LEFT OUTER JOIN acc_requirements ar ON(ar.requirement_id=ct.req_id)"
                    + "WHERE ct.consultant_id=" + recruitmentAction.getConsult_id() + " "
                    + "AND ct.req_id=" + recruitmentAction.getRequirementId() + " "
                    + "AND ct.review_type='" + recruitmentAction.getInterviewType() + "'";
            System.out.println("getMailIdsOfConAndEmp :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                recruitmentAction.setEmpEmail2(resultSet.getString("empEmail2"));
                recruitmentAction.setInterviewType(resultSet.getString("review_type"));
                recruitmentAction.setInterviewLocation(resultSet.getString("interview_lacation"));
                recruitmentAction.setForwardedToName(resultSet.getString("forwarded_to_name"));
                recruitmentAction.setReqName(resultSet.getString("req_name"));
                recruitmentAction.setConsult_jobTitle(resultSet.getString("job_title"));
                recruitmentAction.setConsult_name(resultSet.getString("NAME"));
                recruitmentAction.setConEmail(resultSet.getString("conEmail"));
                recruitmentAction.setEmpEmail(resultSet.getString("empEmail"));
                recruitmentAction.setConSkills(resultSet.getString("consultant_skills"));
                if (resultSet.getDate("scheduled_date") != null) {
                    recruitmentAction.setReviewDate(com.mss.msp.util.DateUtility.getInstance().convertDateYMDtoMDY(resultSet.getString("scheduled_date")));
                } else {
                    recruitmentAction.setReviewDate("");
                }
                recruitmentAction.setForwardedByName(this.getFnameandLnamebyStringId(resultSet.getString("forwarded_by")));
            }
            if (null != resultString && resultString.length() > 0) {
                int endIndex = resultString.lastIndexOf(",");
                if (endIndex != -1) {
                    resultStr = resultString.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
                }
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getMailIdsOfConAndEmp Method End*********************");
    }

    /**
     * *****************************************************************************
     * Date : June 10 2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getManagerAndDirectersByOrgID() method is used to
     *
     * *****************************************************************************
     */
    public Map getManagerAndDirectersByOrgID(int org_id, int projectId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getManagerAndDirectersByOrgID Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map EmployeeNameMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT p.usr_id,CONCAT_WS(' ',u.first_name,u.last_name) AS NAMES FROM Project_team p LEFT OUTER JOIN users u ON(p.usr_id=u.usr_id) WHERE  (account_id=" + org_id + " AND project_id=" + projectId + ") and (designation='Di' OR designation='M')";
        System.out.println("getManagerAndDirectersByOrgID :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                EmployeeNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getManagerAndDirectersByOrgID Method End*********************");
        return EmployeeNameMap;
    }

    /**
     * *****************************************************************************
     * Date :06,02, 2015
     *
     * Author: ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getReqNameById()getting requirement name by id return array
     * list.
     *
     * *****************************************************************************
     */
    public String getReqNameById(int reqId) {
        System.out.println("********************DataSourceDataProvider :: getReqNameById Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT req_name FROM acc_requirements WHERE requirement_id=" + reqId;
            System.out.println("getReqNameById :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("req_name");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReqNameById Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :06,02, 2015
     *
     * Author: ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getConsultNameById()getting consult name by id return array
     * list.
     *
     * *****************************************************************************
     */
    public String getConsultNameById(int conId) {
        System.out.println("********************DataSourceDataProvider :: getConsultNameById Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT CONCAT(first_name,'.',last_name) as Name FROM users WHERE usr_id=" + conId;
            System.out.println("getConsultNameById :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("Name");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getConsultNameById Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : June 12, 2015
     *
     * Author: Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getsubProject() getting the sub project of particular project.
     *
     * *****************************************************************************
     */
    public Map getSubProject(int projectID, int userID) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getSubProject Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map allSubProject = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT project_id, proj_name FROM acc_projects WHERE proj_type='SP' AND proj_status='Active' AND parent_project_id=" + projectID + "  AND project_id NOT IN(SELECT ap.project_id FROM acc_projects ap LEFT OUTER JOIN prj_sub_prjteam sp ON(ap.parent_project_id=sp.project_id ) WHERE ap.project_id=sp.sub_project_id AND ap.proj_type='SP' AND sp.current_status='Active' AND sp.usr_id=" + userID + " AND sp.project_id=" + projectID + ")";
        System.out.println("getSubProject :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                allSubProject.put(resultSet.getInt("project_id"), resultSet.getString("proj_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getSubProject Method End*********************");
        return allSubProject;
    }

    /**
     * *****************************************************************************
     * Date : June 12, 2015
     *
     * Author: Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getAssignedSubProject() method is used to getting the sub
     * project of particular project
     *
     * *****************************************************************************
     */
    public Map getAssignedSubProject(int projectID, int userID) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAssignedSubProject Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map assignSubProject = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT ap.project_id, ap.proj_name "
                + "FROM acc_projects ap LEFT OUTER JOIN prj_sub_prjteam sp ON(ap.parent_project_id=sp.project_id )"
                + "WHERE ap.project_id=sp.sub_project_id AND ap.proj_type='SP' AND sp.current_status='Active' AND sp.usr_id=" + userID + " AND sp.project_id=" + projectID;
        System.out.println("getAssignedSubProject :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                assignSubProject.put(resultSet.getInt("project_id"), resultSet.getString("proj_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAssignedSubProject Method End*********************");
        return assignSubProject;
    }

    /**
     * *****************************************************************************
     * Date : 06/22/2015, 8:30 PM IST
     *
     * Author: praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getContactPersonsByProjectIdHeigerDesignationId() This method is
     * used to set the set designations
     *
     * *****************************************************************************
     */
    public Map getContactPersonsByProjectIdHeigerDesignationId(int projectID, int designation, int usr_id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getContactPersonsByProjectIdHeigerDesignationId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map departmentNameMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT pt.usr_id,CONCAT (first_name,'.',last_name) names FROM Project_team pt JOIN users u ON (pt.usr_id=u.usr_id) WHERE pt.designation IN (13,3,4,5,6) AND project_id=" + projectID + " AND current_status='Active' AND pt.usr_id NOT IN(" + usr_id + ")";
        System.out.println("getContactPersonsByProjectIdHeigerDesignationId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                departmentNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("names"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getContactPersonsByProjectIdHeigerDesignationId Method End*********************");
        return departmentNameMap;
    }

    /**
     * *****************************************************************************
     * Date : 06/22/2015, 8:30 PM IST
     *
     * Author: praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getDesignationId() method is used to get designation id trough
     * project id and usr id
     *
     * *****************************************************************************
     */
    private int getDesignationId(int usr_id, int projectId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getDesignationId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int id = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT designation FROM Project_team WHERE usr_id=" + usr_id + " AND project_id=" + projectId;
        System.out.println("getDesignationId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                id = resultSet.getInt("designation");
            }
        } catch (SQLException ex) {
            System.out.println("getDesignation method-->" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getDesignationId Method End*********************");
        return id;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST
     *
     * Author :RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getTeamMembersUpTo() method is used to getting TeamMembers under
     * userId And return map object
     *
     * *****************************************************************************
     */
    public String getTeamMembersUpTo(int reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTeamMembersUpTo Method Start*********************");
        ResultSet resultSet = null;
        Map myTeamManagersMap = new TreeMap();
        String resultTeam = "";
        Map tempMap = new TreeMap();
        String tempTeam = "";
        int[] keys = new int[100];
        int keyCnt = 0;
        int key = 0;
        String value = null;
        try {
            theStatement.setInt(1, reportsTo);
            resultSet = theStatement.executeQuery();
            while (resultSet.next()) {
                key = resultSet.getInt("usr_id");
                value = resultSet.getString("first_name") + "." + resultSet.getString("last_name");
                myTeamManagersMap.put(key, value);
                resultTeam += "" + key + "#" + value + "^";
                // If the Team Member is a Manager then Get his Team Members List
                if ((resultSet.getInt("is_manager")) != 0 || (resultSet.getInt("is_team_lead")) != 0 || (resultSet.getInt("is_PMO") != 0) || (resultSet.getInt("is_sbteam") != 0)) {
                    keys[keyCnt] = key;
                    keyCnt++;
                }
            }
            for (int i = 0; i < keyCnt; i++) {
                key = keys[i];
                tempTeam = getTeamMembersUpTo(key, theStatement);
                if (tempMap.size() > 0) {
                    Iterator tempIterator = tempMap.entrySet().iterator();
                    while (tempIterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) tempIterator.next();
                        key = Integer.parseInt(entry.getKey().toString());
                        value = entry.getValue().toString();
                        myTeamManagersMap.put(key, value);
                        resultTeam += "" + key + "#" + value + "^";
                        entry = null;
                    }
                }
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTeamMembersUpTo Method End*********************");
        return resultTeam;
    } //closing the method

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST
     *
     * Author: RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : sorting of map taken from Nagireddy<nseerapu@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public < K, V extends Comparable< ? super V>> Map< K, V> sortMapByValue(final Map< K, V> mapToSort) {
        System.out.println("********************DataSourceDataProvider :: sortMapByValue Method Start*********************");
        List< Map.Entry< K, V>> entries = new ArrayList< Map.Entry< K, V>>(mapToSort.size());
        entries.addAll(mapToSort.entrySet());
        Collections.sort(entries,
                new Comparator< Map.Entry< K, V>>() {
                    public int compare(
                            final Map.Entry< K, V> entry1,
                            final Map.Entry< K, V> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    }
                });
        Map< K, V> sortedMap = new LinkedHashMap< K, V>();
        for (Map.Entry< K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        System.out.println("********************DataSourceDataProvider :: sortMapByValue Method End*********************");
        return sortedMap;
    }

    /**
     * *****************************************************************************
     * Date : 06/23/2015, 11:16 PM IST
     *
     * Author :praveen <pkatru@miraclesoft.com>
     *
     * ForUse : getNoOfResourcesInProject() method is used to
     *
     * *****************************************************************************
     */
    public int getNoOfResourcesInProject(int projectId, String prjFlag) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getNoOfResourcesInProject Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultInt = 0;
        if ("Main Project".equalsIgnoreCase(prjFlag)) {
            queryString = "SELECT COUNT(usr_id) AS COUNT FROM Project_team WHERE current_status='Active' AND project_id=" + projectId;
        } else {
            queryString = "SELECT COUNT(DISTINCT(usr_id)) AS COUNT FROM prj_sub_prjteam WHERE current_status='Active' AND sub_project_id=" + projectId;
        }
        System.out.println("getNoOfResourcesInProject :: query string ------>" + queryString);
        try {
            try {
                connection = ConnectionProvider.getInstance().getConnection();
            } catch (ServiceLocatorException ex) {
                Logger.getLogger(DataSourceDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultInt = resultSet.getInt("COUNT");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getNoOfResourcesInProject Method End*********************");
        return resultInt;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:16 PM IST
     *
     * Author :ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getCSRTeam() method is used to getting task type based on task
     * id
     *
     * *****************************************************************************
     */
    public Map getCSRTeam(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getCSRTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map map = new HashMap();
        queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAMES,u.usr_id FROM users u LEFT OUTER JOIN usr_roles r ON(r.usr_id=u.usr_id) WHERE r.role_id=1";
        System.out.println("getCSRTeam :: query string ------>" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                map.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCSRTeam Method End*********************");
        return map;
    }

    /**
     * *****************************************************************************
     * Date : june 26, 2015,
     *
     * Author :praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getNoOfSubmisions() method is used to getting no of submission
     * by requirement id and organization id
     *
     * *****************************************************************************
     */
    public int getNoOfSubmisions(int req_id, int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getNoOfSubmisions Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        queryString = null;
        int resultString = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            queryString = "SELECT COUNT(createdbyOrgId) as count FROM req_con_rel  WHERE  status not like '%SOW%' and reqId=" + req_id;
            System.out.println("getNoOfSubmisions :: query string ------>" + queryString);
            if (orgId != 0) {
                queryString = queryString + " AND createdbyOrgId=" + orgId;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getInt("count");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getNoOfSubmisions Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : june 26, 2015,
     *
     * Author :praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getAvgRateByOrg() method is used to getting AvgRateByOrg by
     * requirement id and organization id
     *
     * *****************************************************************************
     */
    public double getAvgRateByOrg(int req_id, int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAvgRateByOrg Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        double resultString = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            queryString = "SELECT AVG(rate_salary) as avar FROM req_con_rel WHERE status not like '%SOW%' and reqId=" + req_id + " AND createdbyOrgId=" + orgId;
            System.out.println("getAvgRateByOrg :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getInt("avar");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAvgRateByOrg Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/19/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : customerList() This method is used to set the department name in
     * employee search field employeeSearch.jsp
     *
     * *****************************************************************************
     */
    public Map customerList(String typeOfUser, int userSessionId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************DataSourceDataProvider :: customerList Method Start*********************");
        Map customerMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        if (typeOfUser.equalsIgnoreCase("SA")) {
            queryString = "SELECT a.account_id,a.account_name FROM accounts a LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) WHERE o.acc_type=1";
        } else {
            queryString = "SELECT a.account_id,a.account_name "
                    + "FROM accounts a "
                    + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                    + "LEFT OUTER JOIN csrorgrel csr ON(csr.org_id=a.account_id) "
                    + "WHERE o.acc_type=1 "
                    + "AND csr.csr_id=" + userSessionId;
        }
        System.out.println("customerList :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("account_id"), resultSet.getString("account_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: customerList Method End*********************");
        return customerMap;
    }

    /**
     * *****************************************************************************
     * Date : July 2 2015
     *
     * Author :Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getting extension of url on the basic of org_id
     * *****************************************************************************
     */
    public String getUrlExtension(int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUrlExtension Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String url_ext = "";
        try {
            queryString = "SELECT email_ext from siteaccess_mail_ext WHERE org_id=" + orgId;
            statement = connection.createStatement();
            System.out.println("getUrlExtension :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                url_ext = resultSet.getString("email_ext");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUrlExtension Method End*********************");
        return url_ext;
    }

    /**
     * *****************************************************************************
     * Date : 06/19/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getVendorList() method is used toset the department name in
     * employee search field employeeSearch.jsp
     *
     * *****************************************************************************
     */
    public Map getVendorList() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getVendorList Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map customerMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT a.account_id,a.account_name FROM accounts a LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) WHERE o.acc_type=5";
        System.out.println("getVendorList :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("account_id"), resultSet.getString("account_name"));
            }
        } catch (SQLException ex) {
            System.out.println("getDesignation method-->" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorList Method End*********************");
        return customerMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : praveen <pkatru@miraclesoft.com>
     *
     * ForUse : getTypeOfUser() method is used to
     *
     * *****************************************************************************
     */
    public String getTypeOfUser(int userId) {
        System.out.println("********************DataSourceDataProvider :: getTypeOfUser Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT type_of_user FROM users WHERE usr_id=" + userId;
            System.out.println("getTypeOfUser :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("type_of_user");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTypeOfUser Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 07/15/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : checkResetEmailId() method is used to check the existence
     *
     * *****************************************************************************
     */
    public int checkResetEmailId(String emailId, int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: checkResetEmailId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        if (orgId == 10001) {
            queryString = "Select count(email1) as id from users where email1='" + emailId + "'";
        } else {
            queryString = "Select count(email1) as id from users where email1='" + emailId + "' AND org_id=" + orgId;
        }
        System.out.println("checkResetEmailId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: checkResetEmailId Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date : 07/15/2015
     *
     * Author : Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : getCsrAccountCount() method is used to get Csr Account Count
     *
     * *****************************************************************************
     */
    public int getCsrAccountCount(int usrId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getCsrAccountCount Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT COUNT(*) AS COUNT FROM csrorgrel WHERE status='Active' AND csr_id=" + usrId;
        System.out.println("getCsrAccountCount :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("COUNT");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCsrAccountCount Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRequiteCategory() method is used to get
     *
     * *****************************************************************************
     */
    public Map getRequiteCategory(int grpId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getRequiteCategory Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map customerMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select grpcategory,catname from lkusr_grpcategory  where grpid=" + grpId;
        System.out.println("getRequiteCategory :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("grpcategory"), resultSet.getString("catname"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getRequiteCategory Method End*********************");
        return customerMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectList() method is used to get
     *
     * *****************************************************************************
     */
    public Map getProjectList(String roleValue, int userSessionId, int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getProjectList Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map projectMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        if (roleValue.equalsIgnoreCase("Director")) {
            queryString = "SELECT project_id,proj_name FROM acc_projects WHERE acc_id=" + orgId;
        } else {
            queryString = "SELECT project_id,proj_name FROM acc_projects WHERE created_by=" + userSessionId + " AND acc_id=" + orgId + " AND proj_type = 'MP'";
        }
        System.out.println("getProjectList :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectMap.put(resultSet.getInt("project_id"), resultSet.getString("proj_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getProjectList Method End*********************");
        return projectMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getUserNameByUserId() method is used to get
     *
     * *****************************************************************************
     */
    public String getUserNameByUserId(int userId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUserNameByUserId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String result = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT concat(first_name,'.',last_name) as name FROM users WHERE usr_id=" + userId;
        System.out.println("getUserNameByUserId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getString("name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUserNameByUserId Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : GetProjectManagersListByOrgId() method is used to get
     *
     * *****************************************************************************
     */
    public Map GetProjectManagersListByOrgId(int sessionOrgId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************DataSourceDataProvider :: GetProjectManagersListByOrgId Method Start*********************");
        Map managerMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT u.usr_id, concat(first_name,'.',last_name) Names FROM users u JOIN usr_roles ur ON (ur.usr_id=u.usr_id ) WHERE ur.role_id=3 AND org_id=" + sessionOrgId;
        System.out.println("GetProjectManagersListByOrgId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                managerMap.put(resultSet.getInt("usr_id"), resultSet.getString("Names"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: GetProjectManagersListByOrgId Method End*********************");
        return managerMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRolesForAccType() method is used to get
     *
     * *****************************************************************************
     */
    public Map getRolesForAccType(String orgType) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getRolesForAccType Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map rolesMap = new HashMap();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT `role_id`,`role_name` FROM `servicebay`.`roles` WHERE org_type='" + orgType + "' ";
            System.out.println("getRolesForAccType :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rolesMap.put(resultSet.getInt("role_id"), resultSet.getString("role_name"));
            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getRolesForAccType Method End*********************");
        return rolesMap;
    }

    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getAllAccounts() method is used to get
     *
     * *****************************************************************************
     */
    public Map getAllAccounts(int OrgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAllAccounts Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map accountsMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        if (OrgId > 0) {
            queryString = "SELECT account_id,account_name FROM accounts where account_id=" + OrgId;
        } else {
            queryString = "SELECT account_id,account_name FROM accounts";
        }
        System.out.println("getAllAccounts :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                accountsMap.put(resultSet.getInt("account_id"), resultSet.getString("account_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAllAccounts Method End*********************");
        return accountsMap;
    }

    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getAllRoles() method is used to get
     *
     * *****************************************************************************
     */
    public Map getAllRoles(String accType) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAllRoles Method Start*********************");
        Map rolesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        if (accType.equalsIgnoreCase("VC")) {
            queryString = "SELECT role_id,role_name,org_type FROM roles WHERE STATUS='Active'  AND org_type='V'";
        } else if (accType.equalsIgnoreCase("AC")) {
            queryString = "SELECT role_id,role_name,org_type FROM roles WHERE STATUS='Active' AND org_type='C'";
        } else {
            queryString = "SELECT role_id,role_name,org_type FROM roles WHERE STATUS='Active'";
        }
        System.out.println("getAllRoles :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            String role = "";
            while (resultSet.next()) {
                if (resultSet.getString("role_name").equalsIgnoreCase("Employee") || resultSet.getString("role_name").equalsIgnoreCase("Team Lead") || resultSet.getString("role_name").equalsIgnoreCase("Manager")) {
                    if (resultSet.getString("org_type").equalsIgnoreCase("C")) {
                        role = "Customer." + resultSet.getString("role_name");
                    } else if (resultSet.getString("org_type").equalsIgnoreCase("V")) {
                        role = "Vendor." + resultSet.getString("role_name");
                    } else {
                        role = resultSet.getString("role_name");
                    }
                    rolesMap.put(resultSet.getInt("role_id"), role);
                } else {
                    rolesMap.put(resultSet.getInt("role_id"), resultSet.getString("role_name"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAllRoles Method End*********************");
        return rolesMap;
    }

    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author :Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getAllRoles() method is used to get Requirement details of
     * account
     *
     * *****************************************************************************
     */
    public String getAllRolesString(String accType) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getAllRolesString Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String resultString = "";
        if (accType.equalsIgnoreCase("VC")) {
            queryString = "SELECT role_id,role_name FROM roles WHERE STATUS='Active'  AND org_type='V'";
        } else if (accType.equalsIgnoreCase("AC")) {
            queryString = "SELECT role_id,role_name FROM roles WHERE STATUS='Active' AND org_type='C'";
        } else if (accType.equalsIgnoreCase("M")) {
            queryString = "SELECT role_id,role_name FROM roles WHERE STATUS='Active' AND org_type='M'";
        } else {
            queryString = "SELECT role_id,role_name FROM roles WHERE STATUS='Active'";
        }
        System.out.println("getAllRolesString :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("role_id") + "|" + resultSet.getString("role_name") + "^";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getAllRolesString Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : July 23, 2015, 2:23 AM IST
     *
     * Author :Vinodkumar<vsiram@miraclesoft.com>
     *
     * ForUse : getOrganizationType() method is used to getting organization
     * type of relation based on orgid
     *
     * *****************************************************************************
     */
    public String getOrganizationType(String org_id) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getOrganizationType Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String result = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT type_of_relation FROM org_rel WHERE related_org_id=" + org_id;
        System.out.println("getOrganizationType :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getString("type_of_relation");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getOrganizationType Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author: Divya<dgandreti@miraclesoft.com>
     *
     * ForUse :getCategoryByUserId() method is used to
     *
     * *****************************************************************************
     */
    public int getCategoryByUserId(int usrId) {
        System.out.println("********************DataSourceDataProvider :: getCategoryByUserId Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int groupid = 0;
        try {
            queryString = "SELECT cat_type FROM usr_grouping WHERE usr_id=? ";
            System.out.println("getCategoryByUserId :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, usrId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groupid = resultSet.getInt("cat_type");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCategoryByUserId Method End*********************");
        return groupid;
    }
//aklaq

    /**
     * *****************************************************************************
     * Date :
     *
     * Author: aklaq
     *
     * ForUse :getEmiltExistOrNot() method is used to
     *
     * *****************************************************************************
     */
    public String getEmiltExistOrNot(String resourceType, String conEmail, int sessionOrgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getEmiltExistOrNot Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String result = null;
        connection = ConnectionProvider.getInstance().getConnection();
        if ("IC".equals(resourceType)) {
            queryString = "SELECT u.usr_id,u.org_id,u.type_of_user , ud.consultant_skills,ud.ssn_number FROM usr_details ud LEFT OUTER JOIN users u ON(ud.usr_id=u.usr_id)  WHERE cur_status='Active' AND  email1  ='" + conEmail + "'"
                    + " AND created_by_org_id=" + sessionOrgId;
        } else {
            queryString = "SELECT u.usr_id,u.org_id,u.type_of_user , ud.consultant_skills,ud.ssn_number FROM usr_details ud LEFT OUTER JOIN users u ON(ud.usr_id=u.usr_id)  WHERE cur_status='Active' AND  email1  ='" + conEmail + "'"
                    + " AND org_id=" + sessionOrgId;
        }
        System.out.println("getEmiltExistOrNot :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String ssn = "";
                if (resultSet.getString("ssn_number") != null && !"".equals(resultSet.getString("ssn_number"))) {
                    ssn = com.mss.msp.util.DataUtility.decrypted(resultSet.getString("ssn_number"));
                }
                if ("IC".equalsIgnoreCase(resultSet.getString("type_of_user"))) {
                    result = resultSet.getString("usr_id") + "#IC#" + ssn;
                } else {
                    if (sessionOrgId == resultSet.getInt("org_id")) {
                        result = resultSet.getString("usr_id") + "#VC#" + ssn;
                    } else {
                        result = null;
                    }
                }
                String str1 = resultSet.getString("consultant_skills");
                if (str1 != null && !"".equals(str1)) {
                    StringTokenizer stk1 = new StringTokenizer(str1, ",");
                    String skillsResultString = "";
                    while (stk1.hasMoreTokens()) {
                        String s = stk1.nextToken();
                        skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId(s) + "," + s + "^";
                    }
                    result += "#" + skillsResultString;
                } else {
                    result += "#" + "null";
                }
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getEmiltExistOrNot Method End*********************");
        return result;
    }
//aklaq

    /**
     * *****************************************************************************
     * Date :
     *
     * Author: aklaq
     *
     * ForUse :getIsExistConsultantByReqId() method is used to
     *
     * *****************************************************************************
     */
    public String getIsExistConsultantByReqId(String reqId, String result) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getIsExistConsultantByReqId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultmsg = null;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT COUNT(consultantId) COUNT FROM req_con_rel WHERE reqId=" + reqId + " AND consultantId=" + result;
        System.out.println("getIsExistConsultantByReqId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultmsg = resultSet.getString("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getIsExistConsultantByReqId Method End*********************");
        return resultmsg;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getUserSubCategoryByUsrId() method is used to
     *
     * *****************************************************************************
     */
    public String getUserSubCategoryByUsrId(int usrId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUserSubCategoryByUsrId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultmsg = null;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT sub_cat FROM usr_grouping WHERE usr_id=" + usrId;
        System.out.println("getUserSubCategoryByUsrId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultmsg = resultSet.getString("sub_cat");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUserSubCategoryByUsrId Method End*********************");
        return resultmsg;
    }

    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author: Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :getAllRoles() method is used to get Requirement details of
     * account
     *
     * *****************************************************************************
     */
    public Map getReporingByProjectId(AccountAction accountAction, String finalReportsList) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getReporingByProjectId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map rolesMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        if (accountAction.getProjectFlag() != null && !"".equals(accountAction.getProjectFlag().trim())) {
            queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAME,pt.usr_id "
                    + "FROM prgetReporingByProjectIdoject_team pt "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=pt.usr_id) "
                    + "WHERE pt.project_id=" + accountAction.getProjectID() + " "
                    + "AND pt.designation IN(" + finalReportsList + ") "
                    + "AND pt.current_status='Active'";
        } else {
            queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAME,pt.usr_id "
                    + "FROM project_team pt "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=pt.usr_id) "
                    + "WHERE pt.project_id=" + accountAction.getProjectID() + " "
                    + "AND pt.designation IN(" + finalReportsList + ") "
                    + "AND pt.usr_id NOT IN(" + accountAction.getUserID() + ") "
                    + "AND pt.current_status='Active'";
        }
        System.out.println("getReporingByProjectId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                rolesMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReporingByProjectId Method End*********************");
        return rolesMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getRequiteCategory() method is used to
     *
     * *****************************************************************************
     */
    public Map getRequiteCategory() throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getRequiteCategory Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map customerMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "select id,grpname from lkusr_group";
        System.out.println("queryString :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("id"), resultSet.getString("grpname"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getRequiteCategory Method End*********************");
        return customerMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :isHeadHunterOrNot() method is used to
     *
     * *****************************************************************************
     */
    public boolean isHeadHunterOrNot(String requirementId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: isHeadHunterOrNot Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        boolean flag = false;
        queryString = "SELECT * FROM acc_requirements WHERE  tax_term='PE' AND requirement_id=" + requirementId;
        System.out.println("isHeadHunterOrNot :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: isHeadHunterOrNot Method End*********************");
        return flag;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getusrIdByemailId() method is used to
     *
     * *****************************************************************************
     */
    public int getusrIdByemailId(String emailId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getusrIdByemailId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int usr_id = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "Select usr_id from users where email1 like '" + emailId + "'";
        System.out.println("getusrIdByemailId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                usr_id = resultSet.getInt("usr_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getusrIdByemailId Method End*********************");
        return usr_id;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getProjectTeamMembersList() method is used to
     *
     * *****************************************************************************
     */
    public List getProjectTeamMembersList(int projectId) {
        System.out.println("********************DataSourceDataProvider :: getProjectTeamMembersList Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList projectMembers = new ArrayList();
        try {
            queryString = "SELECT usr_id FROM project_team WHERE project_id=" + projectId;
            System.out.println("getProjectTeamMembersList :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                projectMembers.add(resultSet.getInt("usr_id"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getProjectTeamMembersList Method End*********************");
        return projectMembers;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getUsrRoleById() method is used to
     *
     * *****************************************************************************
     */
    public int getUsrRoleById(int usrId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUsrRoleById Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int roleId = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT role_id FROM usr_roles WHERE usr_id=" + usrId;
        System.out.println("getUsrRoleById :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                roleId = resultSet.getInt("role_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUsrRoleById Method End*********************");
        return roleId;
    }

    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author: Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :getSubProjectTeamMap() method is used to get Requirement details
     * of account.
     *
     * *****************************************************************************
     */
    public Map getSubProjectTeamMap(int prjId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getSubProjectTeamMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map rolesMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT CONCAT(TRIM(u.first_name),'.',TRIM(u.last_name)) AS NAME,u.usr_id "
                + "FROM users u "
                + "LEFT OUTER JOIN prj_sub_prjteam pt ON(pt.usr_id=u.usr_id) "
                + "WHERE pt.sub_project_id=" + prjId;
        System.out.println("getSubProjectTeamMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                rolesMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getSubProjectTeamMap Method End*********************");
        return rolesMap;
    }

    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author: Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :doCheckEmailExistsOrNot() method is used to know user email
     * already in migration rable or not
     *
     * *****************************************************************************
     */
    public int doCheckEmailExistsOrNot(int conId, int reqId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: doCheckEmailExistsOrNot Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int roleId = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT m.id FROM con_or_ven_mig_rel m WHERE m.reqId=" + reqId + " AND m.consultantid=" + conId;
        System.out.println("doCheckEmailExistsOrNot :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: doCheckEmailExistsOrNot Method End*********************");
        return roleId;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getSOWStatus() method is used to
     *
     * *****************************************************************************
     */
    public String getSOWStatus(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getSOWStatus Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String status = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT his_curstatus FROM his_serviceagreements WHERE his_serviceid=" + sowAction.getServiceId();
        System.out.println("getSOWStatus :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                status = resultSet.getString("his_curstatus");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getSOWStatus Method End*********************");
        return status;
    }

    /**
     * *****************************************************************************
     * Date : september 9, 2015, 11:23 PM IST
     *
     * Author: Divya<dgandreti@miraclesoft.com>
     *
     * ForUse :getExcelocation() method is used to getting attachment data based
     * on the attachment id
     *
     * *****************************************************************************
     */
    public String getExcelocation(int id, String logDownloadFlag) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getExcelocation Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String attachmentLocation = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            if ("logDownload".equals(logDownloadFlag)) {
                queryString = "SELECT logger,uploaded_file FROM utility_logger WHERE log_id =" + id + "";
                resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    attachmentLocation = resultSet.getString("uploaded_file");// + resultSet.getString("logger");//+"|"+resultSet.getString("uploaded_file");
                }
                attachmentLocation = attachmentLocation + ".log";
            } else {
                queryString = "SELECT resultant_file FROM utility_logger WHERE log_id =" + id + "";
                resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    attachmentLocation = resultSet.getString("resultant_file");// + resultSet.getString("logger");//+"|"+resultSet.getString("uploaded_file");
                }
            }
            System.out.println("getExcelocation :: query string ------>" + queryString);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorFormAttachmentLocation Method End*********************");
        return attachmentLocation;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getVendorFormAttachmentLocation() method is used to
     *
     * *****************************************************************************
     */
    public String getVendorFormAttachmentLocation(int acc_attachment_id) {
        System.out.println("********************DataSourceDataProvider :: getVendorFormAttachmentLocation Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String attachmentLocation = "";
        int i = 0;
        try {
            queryString = "SELECT attachment_path,attachment_name FROM acc_rec_attachment WHERE acc_attachment_id=" + acc_attachment_id + "";
            System.out.println("getVendorFormAttachmentLocation :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation += resultSet.getString("attachment_path") + File.separator + resultSet.getString("attachment_name");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorFormAttachmentLocation Method End*********************");
        return attachmentLocation;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getWorkLocations() method is used to
     *
     * *****************************************************************************
     */
    public Map getWorkLocations(int accId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getWorkLocations Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map workLocationsMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT acc_add_id,location_name "
                + " FROM acc_address  "
                + " WHERE acc_id=" + accId;
        System.out.println("getWorkLocations :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                workLocationsMap.put(resultSet.getInt("acc_add_id"), resultSet.getString("location_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getWorkLocations Method End*********************");
        return workLocationsMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getReqSkillsCategory() method is used to
     *
     * *****************************************************************************
     */
    public Map getReqSkillsCategory(int flag) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getReqSkillsCategory Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map customerMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT id,skill_name FROM lk_skills WHERE STATUS='Active' AND online_flag=" + flag + " ";
        System.out.println("getReqSkillsCategory :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("id"), resultSet.getString("skill_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReqSkillsCategory Method End*********************");
        return customerMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getReqSkillId() method is used to
     *
     * *****************************************************************************
     */
    public String getReqSkillsSet(int skillId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getReqSkillsSet Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT skill_name FROM lk_skills WHERE id=" + skillId;
        System.out.println("getReqSkillsSet :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("skill_name");
            }
            resultString = resultString + ',';
        } catch (SQLException ex) {
            System.out.println("req skills category method-->" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReqSkillsSet Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse :getReqSkillId() method is used to
     *
     * *****************************************************************************
     */
    public int getReqSkillId(String skillName) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getReqSkillId Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultString = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT ls.id FROM lk_skills ls WHERE ls.skill_name='" + skillName + "'";
        System.out.println("getReqSkillId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReqSkillId Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse :getCostCenterBudget() method is used to getting Budget data based
     * on the cost center Code id
     *
     * *****************************************************************************
     */
    public String getCostCenterBudget(String ccCode) {
        System.out.println("********************DataSourceDataProvider :: getCostCenterBudget Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT budgetamt,id,status FROM costcenterbudgets WHERE ccbstatus like 'Active' and cccode='" + ccCode + "'";
            System.out.println("getCostCenterBudget :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getDouble("budgetamt") + "^" + resultSet.getInt("id") + "^" + resultSet.getString("status");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCostCenterBudget Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : October 07, 2015, 04:13 PM IST
     *
     * Author: Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : getProjectsMap() method is used to
     *
     * *****************************************************************************
     */
    public Map getProjectsMap(int orgId, String projectType, int year) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getProjectsMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map projectsMap = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT project_id,proj_name FROM acc_projects WHERE proj_type='" + projectType + "' "
                + " AND (EXTRACT(YEAR FROM proj_stdate) = " + year + "  OR EXTRACT(YEAR FROM proj_trdate) =" + year + " )"
                + " AND acc_id=" + orgId;
        System.out.println("getProjectsMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectsMap.put(resultSet.getInt("project_id"), resultSet.getString("proj_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getProjectsMap Method End*********************");
        return projectsMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author: Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : getCostCenterNames() method is used to
     *
     * *****************************************************************************
     */
    public Map getCostCenterNames(int sessionOrgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getCostCenterNames Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map ccNames = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT cccode,ccname FROM costcenter WHERE orgid=" + sessionOrgId;
        System.out.println("getCostCenterNames :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ccNames.put(resultSet.getString("cccode"), resultSet.getString("ccname"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getCostCenterNames Method End*********************");
        return ccNames;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : noOfQuestionsReturns() method is used to
     *
     * *****************************************************************************
     */
    public int noOfQuestionsReturns(int id, String examLevel, String examType, int orgid) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: noOfQuestionsReturns Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int attempt = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT COUNT(id) AS total FROM sb_onlineexamQuestion WHERE skillid=" + id + " AND LEVEL='" + examLevel + "' AND exam_type='" + examType + "' AND orgid=" + orgid + " AND STATUS='Active' ";
        System.out.println("noOfQuestionsReturns :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attempt = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: noOfQuestionsReturns Method End*********************");
        return attempt;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getSkillsQuestionsMap() method is used to
     *
     * *****************************************************************************
     */
    public Map getSkillsQuestionsMap(String validKey) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getSkillsQuestionsMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map skillsQuestionsMap = new TreeMap();
        queryString = null;
        String option1 = null;
        String option2 = null;
        String option3 = null;
        String option4 = null;
        String option5 = null;
        String option6 = null;
        String option7 = null;
        String option8 = null;
        String option9 = null;
        String option10 = null;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT option1,option2,option3,option4,option5,option6,option7,option8,option9,option10 FROM sb_onlineexam WHERE validationkey='" + validKey + "' ";
        System.out.println("getSkillsQuestionsMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                option1 = resultSet.getString("option1");
                option2 = resultSet.getString("option2");
                option3 = resultSet.getString("option3");
                option4 = resultSet.getString("option4");
                option5 = resultSet.getString("option5");
                option6 = resultSet.getString("option6");
                option7 = resultSet.getString("option7");
                option8 = resultSet.getString("option8");
                option9 = resultSet.getString("option9");
                option10 = resultSet.getString("option10");
            }
            if (!"".equals(option1)) {
                String[] parts1 = option1.split("-");
                skillsQuestionsMap.put(parts1[0], parts1[1]);
            }
            if (!"".equals(option2)) {
                String[] parts2 = option2.split("-");
                skillsQuestionsMap.put(parts2[0], parts2[1]);
            }
            if (!"".equals(option3)) {
                String[] parts3 = option3.split("-");
                skillsQuestionsMap.put(parts3[0], parts3[1]);
            }
            if (!"".equals(option4)) {
                String[] parts4 = option4.split("-");
                skillsQuestionsMap.put(parts4[0], parts4[1]);
            }
            if (!"".equals(option5)) {
                String[] parts5 = option5.split("-");
                skillsQuestionsMap.put(parts5[0], parts5[1]);
            }
            if (!"".equals(option6)) {
                String[] parts6 = option6.split("-");
                skillsQuestionsMap.put(parts6[0], parts6[1]);
            }
            if (!"".equals(option7)) {
                String[] parts7 = option7.split("-");
                skillsQuestionsMap.put(parts7[0], parts7[1]);
            }
            if (!"".equals(option8)) {
                String[] parts8 = option8.split("-");
                skillsQuestionsMap.put(parts8[0], parts8[1]);
            }
            if (!"".equals(option9)) {
                String[] parts9 = option9.split("-");
                skillsQuestionsMap.put(parts9[0], parts9[1]);
            }
            if (!"".equals(option10)) {
                String[] parts10 = option10.split("-");
                skillsQuestionsMap.put(parts10[0], parts10[1]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getSkillsQuestionsMap Method End*********************");
        return skillsQuestionsMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getSkillsMap() method is used to
     *
     * *****************************************************************************
     */
    public Map getSkillsMap(int contechReviewId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getSkillsMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map skillsQuestionsMap = new TreeMap();
        queryString = null;
        String option1 = null;
        String option2 = null;
        String option3 = null;
        String option4 = null;
        String option5 = null;
        String option6 = null;
        String option7 = null;
        String option8 = null;
        String option9 = null;
        String option10 = null;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT option1,option2,option3,option4,option5,option6,option7,option8,option9,option10 FROM sb_onlineexam WHERE techreviewid=" + contechReviewId + " ";
        System.out.println("getSkillsMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                option1 = resultSet.getString("option1");
                option2 = resultSet.getString("option2");
                option3 = resultSet.getString("option3");
                option4 = resultSet.getString("option4");
                option5 = resultSet.getString("option5");
                option6 = resultSet.getString("option6");
                option7 = resultSet.getString("option7");
                option8 = resultSet.getString("option8");
                option9 = resultSet.getString("option9");
                option10 = resultSet.getString("option10");
            }
            if (!"".equals(option1)) {
                String[] parts1 = option1.split("-");
                if (Integer.parseInt(parts1[1]) != 0) {
                    skillsQuestionsMap.put(parts1[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts1[0]))));
                }
            }
            if (!"".equals(option2)) {
                String[] parts2 = option2.split("-");
                if (Integer.parseInt(parts2[1]) != 0) {
                    skillsQuestionsMap.put(parts2[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts2[0]))));
                }
            }
            if (!"".equals(option3)) {
                String[] parts3 = option3.split("-");
                if (Integer.parseInt(parts3[1]) != 0) {
                    skillsQuestionsMap.put(parts3[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts3[0]))));
                }
            }
            if (!"".equals(option4)) {
                String[] parts4 = option4.split("-");
                if (Integer.parseInt(parts4[1]) != 0) {
                    skillsQuestionsMap.put(parts4[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts4[0]))));
                }
            }
            if (!"".equals(option5)) {
                String[] parts5 = option5.split("-");
                if (Integer.parseInt(parts5[1]) != 0) {
                    skillsQuestionsMap.put(parts5[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts5[0]))));
                }
            }
            if (!"".equals(option6)) {
                String[] parts6 = option6.split("-");
                if (Integer.parseInt(parts6[1]) != 0) {
                    skillsQuestionsMap.put(parts6[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts6[0]))));
                }
            }
            if (!"".equals(option7)) {
                String[] parts7 = option7.split("-");
                if (Integer.parseInt(parts7[1]) != 0) {
                    skillsQuestionsMap.put(parts7[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts7[0]))));
                }
            }
            if (!"".equals(option8)) {
                String[] parts8 = option8.split("-");
                if (Integer.parseInt(parts8[1]) != 0) {
                    skillsQuestionsMap.put(parts8[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts8[0]))));
                }
            }
            if (!"".equals(option9)) {
                String[] parts9 = option9.split("-");
                if (Integer.parseInt(parts9[1]) != 0) {
                    skillsQuestionsMap.put(parts9[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts9[0]))));
                }
            }
            if (!"".equals(option10)) {
                String[] parts10 = option10.split("-");
                if (Integer.parseInt(parts10[1]) != 0) {
                    skillsQuestionsMap.put(parts10[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts10[0]))));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getSkillsMap Method End*********************");
        return skillsQuestionsMap;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getExamStatus() method is used to
     *
     * *****************************************************************************
     */
    public String getExamStatus(int conTechReviewId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getExamStatus Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String examStatus = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT examstatus FROM sb_onlineexam WHERE techreviewid=" + conTechReviewId + "";
        System.out.println("getExamStatus :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                examStatus = resultSet.getString("examstatus");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getExamStatus Method End*********************");
        return examStatus;
    }

    /**
     * *****************************************************************************
     * Date : october 6, 2015, 5:40 PM IST
     *
     * Author: jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : isAttempted() method is used to find the question attempted or
     * not
     *
     * *****************************************************************************
     */
    public int isAttempted(int qusetionId, int examKey) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: isAttempted Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int attempt = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT COUNT(id) AS total FROM sb_onlineexamsummery WHERE questionid=" + qusetionId + " AND examkey=" + examKey;
        System.out.println("isAttempted :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attempt = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: isAttempted Method End*********************");
        return attempt;
    }

    /**
     * *****************************************************************************
     * Date : october 1, 2015, 3:40 PM IST
     *
     * Author: jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : isExamExpired() method is used to get whether the exam expired
     * or not
     *
     * *****************************************************************************
     */
    public boolean isExamExpired(String token) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: isExamExpired Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        boolean isExpired = false;
        try {
            queryString = " SELECT HOUR(TIMEDIFF(  NOW(),createddate)) AS DIFF FROM sb_onlineexam WHERE acctoken='" + token + "'";
            System.out.println("isExamExpired :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {
                if (resultSet.getInt("DIFF") <= 24) {
                    isExpired = false;
                } else {
                    isExpired = true;
                }
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: isExamExpired Method End*********************");
        return isExpired;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getNoOfRightAns() method is used to
     *
     * *****************************************************************************
     */
    public int getNoOfRightAns(int skillId, int examId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getNoOfRightAns Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int result = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT COUNT(IF(ansstatus='R',1, NULL)) AS rightans FROM sb_onlineexamsummery WHERE skillid=" + skillId + " AND examid=" + examId + "";
        System.out.println("getNoOfRightAns :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getInt("rightans");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getNoOfRightAns Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : doMailExtensionVerify() method is used to
     *
     * *****************************************************************************
     */
    public int doMailExtensionVerify(String mailExt) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: doMailExtensionVerify Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT count(*) as id FROM siteaccess_mail_ext WHERE email_ext LIKE '" + mailExt + "'";
        System.out.println("doMailExtensionVerify :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: doMailExtensionVerify Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getITextPDFTable() method is used to
     *
     * *****************************************************************************
     */
    public PdfPTable getITextPDFTable(String gridData, PdfPTable table) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getITextPDFTable Method Start*********************");
        if (!"".equals(gridData)) {
            String[] s = gridData.split("\\^");
            for (int i = 0; i < s.length; i++) {
                String ss = s[i];
                String[] inner = ss.split("\\|");
                for (int j = 0; j < inner.length; j++) {
                    if (i == 0) {
                        PdfPCell cell = new PdfPCell(new Paragraph(inner[j]));
                        cell.setBackgroundColor(BaseColor.ORANGE);
                        table.addCell(cell);
                    } else {
                        table.addCell(inner[j]);
                    }
                }
            }
        }
        System.out.println("********************DataSourceDataProvider :: getITextPDFTable Method End*********************");
        return table;
    }

    /**
     * *****************************************************************************
     * Date : November 11, 2015, 3:00 PM IST
     *
     * Author: jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : getEmpConsultantTeamMap() method is used to
     *
     * *****************************************************************************
     */
    public Map getEmpConsultantTeamMap(int orgId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getEmpConsultantTeamMap Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map empTeam = new HashMap();
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAME,u.usr_id  FROM users u "
                + " LEFT OUTER JOIN usr_roles ur ON u.usr_id=ur.usr_id "
                + " LEFT OUTER JOIN usr_grouping ug ON ug.usr_id=u.usr_id "
                + " WHERE   ur.role_id=11  AND u.org_id=" + orgId + " AND cat_type=1";
        System.out.println("getEmpConsultantTeamMap :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                empTeam.put(resultSet.getInt("u.usr_id"), resultSet.getString("NAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getEmpConsultantTeamMap Method End*********************");
        return empTeam;
    }
    /*by  divya gandreti*/

    /**
     * *****************************************************************************
     * Date :
     *
     * Author: Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : getActionDescription() method is used to getting description of
     * a action
     *
     * *****************************************************************************
     */
    public String getActionDescription(String actionName) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getActionDescription Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String resultString = "";
        queryString = "SELECT description FROM ac_action WHERE action_name = SUBSTRING_INDEX(SUBSTRING_INDEX('" + actionName + "','/',-1),'.',1)";
        System.out.println("getActionDescription :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("description");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getActionDescription Method End*********************");
        return resultString;
    }
    /* by divya gandreti*/

    /**
     * *****************************************************************************
     * Date :
     *
     * Author: Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : getActionNamesList() method is used to getting action names
     *
     * *****************************************************************************
     */
    public List getActionNamesList(int orgId, int roleId, String accType) {
        System.out.println("********************DataSourceDataProvider :: getActionNamesList Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList actionNames = new ArrayList();
        try {
            queryString = "SELECT DISTINCT action_name FROM home_redirect_action WHERE STATUS ='Active' AND type_of_user LIKE '" + accType + "'";// AND primaryrole="+roleId;//+" AND (org_id="+orgId+" OR org_id=0)";
            System.out.println("getActionNamesList :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actionNames.add(resultSet.getString("action_name"));
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getActionNamesList Method End*********************");
        return actionNames;
    }

    /**
     * *****************************************************************************
     * Date : December 15, 2015, 3:00 PM IST
     *
     * Author: jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : getVendorEmpEmail() method is used to getting the vendor
     * employee email
     *
     * *****************************************************************************
     */
    public void getVendorEmpEmail(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getVendorEmpEmail Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT CONCAT(c.first_name,'.',c.last_name)AS NAME ,c.email1 AS venEmail FROM users c "
                + " LEFT OUTER JOIN req_con_rel rcr ON(rcr.created_By=c.usr_id) "
                + " WHERE rcr.reqId=" + recruitmentAction.getRequirementId() + " AND rcr.consultantId=" + recruitmentAction.getConsult_id() + " ";
        System.out.println("getVendorEmpEmail :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                recruitmentAction.setVenEmail(resultSet.getString("venEmail"));
                recruitmentAction.setVenName(resultSet.getString("NAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getVendorEmpEmail Method End*********************");
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getConsultVisaAttachment() method is used to
     *
     * *****************************************************************************
     */
    public String getConsultVisaAttachment(int consultantId) {
        System.out.println("********************DataSourceDataProvider :: getConsultVisaAttachment Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String attachmentLocation = "";
        try {
            queryString = "SELECT idproofattachment FROM usr_details WHERE usr_id=" + consultantId + "";
            System.out.println("getConsultVisaAttachment :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation += resultSet.getString("idproofattachment");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getConsultVisaAttachment Method End*********************");
        return attachmentLocation;
    }

    /**
     * *****************************************************************************
     * Date : DEC 15, 2015, 3:00 PM IST
     *
     * Author: jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : getReportingPersonsEmail() method is used to getting the
     * getReportingPerson email
     *
     * *****************************************************************************
     */
    public String getReportingPersonsEmail(int userId) {
        System.out.println("********************DataSourceDataProvider :: getReportingPersonsEmail Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String reportingMails = "";
        try {
            queryString = " SELECT u.email1 FROM project_team pt LEFT OUTER JOIN users u ON(u.usr_id=pt.reportsto1)"
                    + " WHERE pt.usr_id = " + userId + " AND pt.current_status = 'Active' AND pt.reportsto1 != -1";
            System.out.println("getReportingPersonsEmail :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                reportingMails += resultSet.getString("email1");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getReportingPersonsEmail Method Start*********************");
        return reportingMails;
    }

    /**
     * *****************************************************************************
     * Date : DEC 15, 2015, 3:00 PM IST
     *
     * Author: jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : checkUserExistOrNotForProjectRespectedOrg() method is used to
     * check the user is existed or not for project for customer.
     *
     * *****************************************************************************
     */
    public String checkUserExistOrNotForProjectRespectedOrg(int userId, int orgId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************DataSourceDataProvider :: checkUserExistOrNotForProjectRespectedOrg Method Start*********************");
        String existOrNot = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT COUNT(project_id)AS total FROM project_team WHERE usr_id=" + userId + " AND account_id=" + orgId + " AND current_status='Active'";
        System.out.println("checkUserExistOrNotForProjectRespectedOrg :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if (resultSet.getInt("total") == 0) {
                    existOrNot = "notExisted";
                    System.out.println("" + existOrNot);
                } else {
                    existOrNot = "Existed";
                    System.out.println("" + existOrNot);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: checkUserExistOrNotForProjectRespectedOrg Method End*********************");
        return existOrNot;
    }

    /**
     * *****************************************************************************
     * Date : JAN 07, 2016, 3:00 PM IST
     *
     * Author: Mani Kanta Eeralla<meeralla@miraclesoft.com>
     *
     * ForUse : getTeamMemberReportingPersons() method is used to get team
     * members reporting persons.
     *
     * *****************************************************************************
     */
    public String getTeamMemberReportingPersons(int userId, String finalReportsList, int orgId, int projectID) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getTeamMemberReportingPersons Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        queryString = "SELECT DISTINCT(pt.usr_id),CONCAT(u.first_name,'.',u.last_name) AS NAME "
                + "FROM project_team pt "
                + "LEFT OUTER JOIN users u ON(u.usr_id=pt.usr_id) "
                + " WHERE  pt.designation IN(" + finalReportsList + ") "
                + " AND pt.project_id=" + projectID + " "
                + " AND pt.current_status='Active'"
                + " AND pt.account_id=" + orgId + " ";
        System.out.println("getTeamMemberReportingPersons :: query string ------>" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("pt.usr_id") + "|" + resultSet.getString("NAME") + "^";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getTeamMemberReportingPersons Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getGridData() method is used
     *
     * *****************************************************************************
     */
    public String getGridData(String query, String flag, String accType) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getGridData Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String decryptedSSN = "";
        queryString = query;
        System.out.println("getGridData :: query string ------>" + queryString);
        String postedDate = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if ("Req".equals(flag)) {
                if ("AC".equalsIgnoreCase(accType)) {
                    resultString = "Job Id" + "|" + "Jog Title" + "|" + "Positions" + "|" + "Skills Set" + "|" + "Posted Date" + "|" + "Status" + "|" + "No of Submissions" + "^";
                } else {
                    resultString = "Job Id" + "|" + "Jog Title" + "|" + "Customer" + "|" + "Skills Set" + "|" + "Posted Date" + "|" + "Status" + "^";
                }
                while (resultSet.next()) {
                    java.util.Date myDate = resultSet.getDate("created_date");
                    if (myDate != null) {
                        postedDate = com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(myDate);
                    } else {
                        postedDate = "---";
                    }
                    String status = "";
                    if (resultSet.getString("req_status").equalsIgnoreCase("O")) {
                        status = "Opened";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("R")) {
                        status = "Released";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("OR")) {
                        status = "Open for Resume";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("C")) {
                        status = "Closed";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("F")) {
                        status = "Forecast";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("I")) {
                        status = "Inprogess";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("H")) {
                        status = "Hold";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("W")) {
                        status = "Withdrawn";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("S")) {
                        status = "Won";
                    } else if (resultSet.getString("req_status").equalsIgnoreCase("L")) {
                        status = "Lost";
                    }
                    if ("AC".equalsIgnoreCase(accType)) {
                        resultString += resultSet.getString("jdid") + "|"
                                + resultSet.getString("req_name") + "|"
                                + resultSet.getString("no_of_resources") + "|"
                                + resultSet.getString("req_skills") + "|"
                                + postedDate + "|"
                                + status + "|"
                                + com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfSubmisions(resultSet.getInt("requirement_id"), 0) + "^";
                    } else {
                        resultString += resultSet.getString("jdid") + "|"
                                + resultSet.getString("req_name") + "|"
                                + resultSet.getString("account_name") + "|"
                                + resultSet.getString("req_skills") + "|"
                                + postedDate + "|" + status + "^";
                    }
                }
            } else if ("Sub".equals(flag)) {
                if ("AC".equalsIgnoreCase(accType)) {
                    resultString = "Vendor" + "|" + "Candidate Name" + "|" + "Submitted Date" + "|" + "SSN No" + "|" + "Skills" + "|" + "Status" + "|" + "Rate" + "^";
                } else {
                    resultString = "Candidate Name" + "|" + "Submitted Date" + "|" + "SSN No" + "|" + "Email" + "|" + "Skills" + "|" + "Phone Number" + "|" + "Status" + "|" + "Rate" + "^";
                }
                while (resultSet.next()) {
                    if (resultSet.getString("ssn_number") != null && !"".equalsIgnoreCase(resultSet.getString("ssn_number"))) {
                        decryptedSSN = com.mss.msp.util.DataUtility.decrypted(resultSet.getString("ssn_number"));
                    }
                    if ("AC".equalsIgnoreCase(accType)) {
                        resultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")) + "|"
                                + resultSet.getString("name") + "|"
                                + com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("created_date")) + "|"
                                + decryptedSSN + "|"
                                + resultSet.getString("consultant_skills") + "|"
                                + resultSet.getString("status") + "|"
                                + resultSet.getString("rate_salary") + "/Hr^";
                    } else {
                        resultString += resultSet.getString("name") + "|"
                                + com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("created_date")) + "|"
                                + decryptedSSN + "|"
                                + resultSet.getString("email1") + "|"
                                + resultSet.getString("consultant_skills") + "|"
                                + resultSet.getString("phone1") + "|"
                                + resultSet.getString("status") + "|"
                                + resultSet.getString("rate_salary") + "/Hr^";
                    }
                }
            } else {
                resultString = "Name" + "|" + "E-Mail" + "|" + "Skill Set" + "|" + "Rate/Salary" + "|" + "Phone Number" + "|" + "Status" + "^";
                while (resultSet.next()) {
                    resultString += resultSet.getString("name") + "|"
                            + resultSet.getString("email1") + "|"
                            + resultSet.getString("consultant_skills") + "|"
                            + resultSet.getString("rate_salary") + "|"
                            + resultSet.getString("phone1") + "|"
                            + resultSet.getString("cur_status") + "|"
                            + "^";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getGridData Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getMailIdsOfVendorAssociated() method is used
     *
     * *****************************************************************************
     */
    public String getMailIdsOfVendorAssociated(String requirementId) {
        System.out.println("********************DataSourceDataProvider :: getMailIdsOfVendorAssociated Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        int count = 0;
        try {
            queryString = "SELECT u.usr_id,u.email1,is_primary FROM usr_grouping ug LEFT OUTER JOIN users u ON(ug.usr_id=u.usr_id)"
                    + "WHERE is_primary=1 AND org_id IN(SELECT crl.vendor_id FROM req_ven_rel req "
                    + "JOIN customer_ven_rel crl ON ( req.ven_id=crl.vendor_id) "
                    + "WHERE req.STATUS='Active' AND crl.STATUS='Active' AND req.req_id=" + requirementId + " AND req_access_time< '" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "')";
            System.out.println("getMailIdsOfVendorAssociated :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("email1") + ",";
            }
            if (null != resultString && resultString.length() > 0) {
                int endIndex = resultString.lastIndexOf(",");
                if (endIndex != -1) {
                    resultStr = resultString.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
                }
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************DataSourceDataProvider :: getMailIdsOfVendorAssociated Method End*********************");
        return resultStr;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author:
     *
     * ForUse : getUsrExistedOrNotProject() method is used
     *
     * *****************************************************************************
     */
    public int getUsrExistedOrNotProject(int usrId) throws ServiceLocatorException {
        System.out.println("********************DataSourceDataProvider :: getUsrExistedOrNotProject Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int available = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = " SELECT COUNT(*) AS total FROM project_team WHERE usr_id=" + usrId + " AND current_status='Active'";
        System.out.println("getUsrExistedOrNotProject :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                available = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************DataSourceDataProvider :: getUsrExistedOrNotProject Method End*********************");
        return available;
    }
}
