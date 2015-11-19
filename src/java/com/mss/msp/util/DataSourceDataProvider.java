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
    private Connection connection;

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

    /*
     * Author :Prasad Kandregula
     * Date   :March 03 2015
     * Method :getUserIdAndStatusByEmail() 
     */
    public String getUserIdAndStatusByEmail(String email) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String status = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //String queryString = "Select Id,ProjectName,ProjectType from tblProjects where Id ="+projectId;
        //String queryString = "SELECT usr_id,cur_status FROM users WHERE login_id LIKE '"+email.trim()+"'";
        String queryString = "SELECT u.usr_id,cur_status FROM users u LEFT OUTER JOIN usr_reg ur "
                + "ON(u.usr_id=ur.usr_id) WHERE login_id LIKE '" + email.trim() + "'";
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
        return resultString;
    }

    /**
     *
     * @param emailId
     * @return
     * @throws ServiceLocatorException
     */
    public int checkLoginIdExistance(String emailId) throws ServiceLocatorException {

        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select count(usr_id) as id from users where email1 like '" + emailId + "'";
        //System.out.println("queryString-->" + queryString);
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
        return count;
    }

    /**
     *
     */
    public Map getUsrRolesMap(int usrId) throws ServiceLocatorException {

        Map rolesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select r.role_id as roleId,role_name from usr_roles ur left outer join roles r on(ur.role_id=r.role_id) where usr_id=" + usrId;
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

        return rolesMap;

    }

    public String getUsrPrimaryRole(int usrId) throws ServiceLocatorException {
        int primaryrole = 0;
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        System.err.println("In getUsrPrimaryRole");
        String queryString = "SELECT r.role_id as roleId,role_name FROM usr_roles ur LEFT OUTER JOIN roles r ON(ur.role_id=r.role_id) WHERE usr_id=" + usrId + " and primary_flag=1";

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
        return resultString;
    }

    public int getOrgIdByEmailExt(String loginId) throws ServiceLocatorException {
        int orgId = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //  System.out.println("logininfo-->" + loginId.split("\\@")[1]);

        String queryString = "SELECT org_id FROM siteaccess_mail_ext WHERE email_ext='" + loginId.split("\\@")[1] + "'";

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
        return orgId;
    }

    public Map getTaskStatusByOrgId() throws ServiceLocatorException {

        Map tasksStatusMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select id,task_status_name from lk_task_status";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksStatusMap.put(resultSet.getInt("id"), resultSet.getString("task_status_name"));
            }
        } catch (SQLException ex) {
            // System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        //  System.out.println("tasksStatusMap-->" + tasksStatusMap);
        return tasksStatusMap;

    }

    //get task related to map
    public Map getTaskrelatedToMap() throws ServiceLocatorException {

        Map tasksRelatedtoMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksRelatedtoMap.put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
            }
        } catch (SQLException ex) {
            //  System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        // System.out.println("tasksStatusMap-->" + tasksRelatedtoMap);
        return tasksRelatedtoMap;

    }

    //task related to by orgId
    public Map getTaskrelatedToMapByOrgId(String orgId) throws ServiceLocatorException {

        Map tasksRelatedtoMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT task_relatedto_id,task_relatedto_name FROM lk_taskrelated_to WHERE STATUS LIKE 'Active' AND org_id=" + orgId + "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksRelatedtoMap.put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
            }
        } catch (SQLException ex) {
            //  System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        // System.out.println("tasksStatusMap-->" + tasksRelatedtoMap);
        return tasksRelatedtoMap;

    }

    public Map getTasksTypeByRelatedId(String relatedToId) throws ServiceLocatorException {

        Map tasksTypesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT task_types_id,task_type_name FROM lk_task_types WHERE STATUS LIKE 'Active' AND task_rel_toId==" + relatedToId + "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                tasksTypesMap.put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
            }
        } catch (SQLException ex) {
            //   System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        // System.out.println("tasksStatusMap-->" + tasksTypesMap);
        return tasksTypesMap;

    }

    /*Methods from here created by rk*/
    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com> Author :
     * RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting MyTeamMembers based on userId on userId and return map
     * object
     * *****************************************************************************
     */
    public Map getMyTeamMembers(int reportsTo) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        Map myTeamMembers = new TreeMap();

        try {

            //queryString = "SELECT m.usr_id,m.reports_to,m.is_team_lead,m.is_manager,m.is_sbteam,m.is_PMO,m.opt_contact,u.first_name,u.last_name FROM usr_miscellaneous m LEFT OUTER JOIN users u ON (m.usr_id=u.usr_id) WHERE m.reports_to=?";

            queryString = "SELECT pt.usr_id,pt.designation,first_name,last_name,pt.current_status FROM project_team pt LEFT OUTER JOIN users u ON ((pt.usr_id=u.usr_id)) WHERE pt.reportsto1=? AND pt.current_status LIKE 'Active'";
            preparedStatement = connection.prepareStatement(queryString);
            System.out.println("query==========>" + queryString);

            myTeamMembers = getMyTeamMembersUpTo(reportsTo, preparedStatement);

            //myTeamMembers.put(reportsTo, getFname_Lname(reportsTo));


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


        // System.out.println("myTeamMembers"+myTeamMembers);
        // System.out.println("I am Out of Data Source Provider");
        return myTeamMembers; // returning the object.
    }

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST Author :
     * Praveenkumar<pkatru@miraclesoft.com> Author
     * :RamaKrishna<lankireddy@miraclesoft.com> ForUse : getting TeamMembers
     * under userId And return map object
     * *****************************************************************************
     */
    public Map getMyTeamMembersUpTo(int reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {
        ResultSet resultSet = null;
        Map myTeamManagersMap = new TreeMap();
        Map tempMap = new TreeMap();
        int[] keys = new int[100];
        int keyCnt = 0;
        int key = 0;
        String value = null;

        try {
            //System.out.println("Main ReportsTo:" + reportsTo);
            theStatement.setInt(1, reportsTo);


            resultSet = theStatement.executeQuery();
            while (resultSet.next()) {
                key = resultSet.getInt("usr_id");
                value = resultSet.getString("first_name") + "." + resultSet.getString("last_name");
                myTeamManagersMap.put(key, value);
                // If the Team Member is a Manager then Get his Team Members List
                // if ((resultSet.getInt("designation")) != 0 || (resultSet.getInt("designation")) != 0 || (resultSet.getInt("is_PMO") != 0) || (resultSet.getInt("is_sbteam") != 0)) {
                if (DataUtility.getInstance().getTimsheetAccessingRolesList().contains(resultSet.getInt("designation"))) {
                    keys[keyCnt] = key;
                    keyCnt++;
                    //  System.out.println("keyCnt--- Value"+keyCnt);

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
                // System.out.println("keyCnt value"+keyCnt);
                // System.out.println("i value"+i);
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }
        myTeamManagersMap = sortMapByValues(myTeamManagersMap);
        return myTeamManagersMap; // returning the object.
    } //closing the method

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST Author :
     * Praveenkumar<pkatru@miraclesoft.com> Author :
     * RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : sorting of map taken from Nagireddy<nseerapu@miraclesoft.com>
     * sir
     * *****************************************************************************
     */
    public < K, V extends Comparable< ? super V>> Map< K, V> sortMapByValues(final Map< K, V> mapToSort) {
        List< Map.Entry< K, V>> entries =
                new ArrayList< Map.Entry< K, V>>(mapToSort.size());

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

        return sortedMap;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 18, 2015, 2:23 AM IST Author
     * :Praveenkumar<pkatru@miraclesoft.com>
     *
     * ForUse : getting child organizations and organization names based on
     * organization id
     * *****************************************************************************
     */
    public Map getOrganizationRelations(int org_id) {
        Map childmap = new LinkedHashMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "SELECT DISTINCT account_id,account_name FROM accounts JOIN org_rel ON(account_id=related_org_id) AND org_id=? WHERE type_of_relation='M' OR type_of_relation='CH'";

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

        return childmap;
    }
    /*
     * Author :Kiran Arogya
     * Date   :April 04 2015
     * Method :getFnameandLnamebyUserid()
     * This methood is used to get first name and last name based on userid from users table.
     */

    public String getFnameandLnamebyUserid(int userId) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String user_name = "";
        String status = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=" + userId;
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
        return resultString;
    }

//To get modified person added by divya
    public String getModifiedPersonByUserId(int userId) throws ServiceLocatorException {

        String modified_person = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        System.out.println("userid is" + userId);
        //String queryString = "SELECT CONCAT_WS(' ',first_name,last_name) AS name FROM users WHERE usr_id=(SELECT reports_to FROM usr_miscellaneous WHERE usr_id = "+userId+")";
        String queryString = "SELECT CONCAT_WS(' ',first_name,last_name) AS name FROM users WHERE usr_id=" + userId;
        //String queryString1="SELECT usr_id FROM users WHERE usr_id=(SELECT reports_to FROM usr_miscellaneous WHERE usr_id = "+userId+")";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                //reporting_person=put(resultSet.getInt("task_relatedto_id"), resultSet.getString("task_relatedto_name"));
                modified_person = resultSet.getString("name");


            }
            // reporting_person=  reporting_person.concat(reporting);

            //reporting_person=reporting_person.concat(reporting);
            System.out.println("reporting person is" + modified_person);
        } catch (SQLException ex) {
            System.out.println("getReportingPersonByUserId method-->" + ex.getMessage());
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
        System.out.println("reporting person is-->" + modified_person);
        return modified_person;

    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     * Author:RAmakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting attachment details based on the task id
     * *****************************************************************************
     */
    public List getAttachmentDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {


        ArrayList<TasksVTO> fileslist = new ArrayList<TasksVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT id,attachment_name,attachment_path FROM task_attachments WHERE task_id=" + taskHandlerAction.getTaskid() + " AND STATUS='Active'";

            System.out.println("queryString-->" + queryString);
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

            System.out.println("queryString-->" + fileslist);

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
        return fileslist;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     * Author:RAmakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting attachment data based on the attachment id
     * *****************************************************************************
     */
    public String getAttachmentLocation(int id) throws ServiceLocatorException {


        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "", attachmentLocation = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT id,attachment_name,attachment_path FROM task_attachments WHERE id=" + id + "";

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation = resultSet.getString("attachment_path");
            }

            System.out.println("queryString-->" + attachmentLocation);

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
        return attachmentLocation;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:16 PM IST Author
     * :ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting task type based on task id
     * *****************************************************************************
     */
    public Map getTaskTypeById(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        Map map = new HashMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //String querystrings = "SELECT task_types_id,task_type_name FROM task_list  JOIN  lk_task_types ON(task_related_to=task_rel_toId) WHERE task_id=?";
        String querystrings = "SELECT p.proj_name,p.project_id FROM acc_projects p "
                + "LEFT OUTER JOIN prj_sub_prjteam t ON(t.sub_project_id=p.project_id) "
                + "WHERE t.usr_id=?";
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
        return map;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     * Author:Praveenkumar<pkatru@miraclesoft.com>
     *
     * ForUse : getting primary assigned to based on task id
     * *****************************************************************************
     */
    public Map getPrimaryAssignTo(int taskId) throws ServiceLocatorException {
        Map map = new HashMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String string = "SELECT ur.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM task_list t  JOIN  usr_roles ur ON (t.task_related_to=ur.role_id) JOIN users u ON(ur.usr_id=u.usr_id) WHERE t.task_id=?";

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
        return map;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 28, 2015, 11:23 PM IST
     * Author:Praveenkumar<pkatru@miraclesoft.com>
     *
     * ForUse : getting email id by passing list of user id's
     * *****************************************************************************
     */
    public List getReportingEmailId(List listUserIds) {
        Connection connection = null;
        ArrayList emailId = new ArrayList();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String ids = "";
        ids = listUserIds.get(0).toString() + "," + listUserIds.get(1).toString();
        System.out.println(ids);
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT email1 from users where usr_id in (" + ids + ")";
            System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                emailId.add(resultSet.getString("email1"));
            }

            System.out.println("queryString-->");

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
        return emailId;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 28, 2015, 11:23 PM IST Author:divya
     * gandreti<dgandreti@miralcesoft.com>
     *
     * ForUse : getting Email Id by using user Id
     * *****************************************************************************
     */
    public String getEmailIdbyuser(int userid) {
        String email = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT email1 from users where usr_id= " + userid;
            System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                email = resultSet.getString("email1");
            }

            System.out.println("queryString-->");

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




        return email;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 28, 2015, 11:23 PM IST Author:divya
     * gandreti<dgandreti@miralcesoft.com>
     *
     * ForUse : getting Email Id by using user Id
     * *****************************************************************************
     */
    public int getUserIdByLeaveId(int leave_id) {
        int usr_id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT usr_id from usr_leaves where leave_id= " + leave_id;
            System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                usr_id = resultSet.getInt("usr_id");
            }

            System.out.println("queryString-->");

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


        return usr_id;

    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting primary assigned to based on task id
     * *****************************************************************************
     */
    public String getEmailId(int userId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String mailId = "";

        String string = "SELECT email1 from users WHERE usr_id=?";

        try {
            //System.out.println("in DSDP EMAILID METHOD  " + userId);
            //System.out.println("in DSDP query  " + string);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(string);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mailId = resultSet.getString("email1");
            }
            System.out.println("================>EmailId:::::" + mailId);
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
        return mailId;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting primary assigned to based on task id
     * *****************************************************************************
     */
    public String getStatusById(int statusId) throws ServiceLocatorException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String status = "";

        String string = "SELECT task_status_name from lk_task_status WHERE id=?";

        try {
            System.out.println("in DSDP EMAILID METHOD  " + statusId);
            System.out.println("in DSDP query  " + string);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(string);
            preparedStatement.setInt(1, statusId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = resultSet.getString("task_status_name");
            }
            System.out.println("================>status:::::" + status);
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
        return status;
    }

    /*
     * Author :Kiran Arogya
     * Date   :Apr 29 2015
     * Method :getUserIdAndStatusByEmail() 
     */
    public String getFirstnameandLastnameByEmail(String email) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String name = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //String queryString = "Select Id,ProjectName,ProjectType from tblProjects where Id ="+projectId;
        //String queryString = "SELECT usr_id,cur_status FROM users WHERE login_id LIKE '"+email.trim()+"'";
        String queryString = "SELECT CONCAT(first_name,' ',last_name) AS NAME,usr_id  FROM users WHERE email1='" + email.trim() + "'";
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
        return resultString;
    }

    public Map getCountryNames() throws ServiceLocatorException {

        Map countryNameMap = new LinkedHashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select id, country from lk_country ORDER BY country ASC";
        System.out.println("queryString=====>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                countryNameMap.put(resultSet.getInt("id"), resultSet.getString("country"));
            }
        } catch (SQLException ex) {
            System.out.println("getCountryNames method-->" + ex.getMessage());
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
        System.out.println("departmentNameMap-->" + countryNameMap);
        return countryNameMap;

    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting vendor Types
     * *****************************************************************************
     */
    public Map getVendorType() throws ServiceLocatorException {

        Map vendorTypeMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select id,acc_type_name from lk_acc_type";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorTypeMap.put(resultSet.getInt("id"), resultSet.getString("acc_type_name"));
            }
        } catch (SQLException ex) {
            // System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        //  System.out.println("tasksStatusMap-->" + tasksStatusMap);
        return vendorTypeMap;

    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting industry type Types
     * *****************************************************************************
     */
    public Map getIndystryTypes() throws ServiceLocatorException {
        // System.out.println("im in getIndystryTypes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map industryList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select id,acc_industry_name from lk_acc_industry_type where status='Active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryList.put(resultSet.getInt("id"), resultSet.getString("acc_industry_name"));
            }
            //  System.out.println(industryList.toString());
        } catch (SQLException ex) {
            // System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        //  System.out.println("tasksStatusMap-->" + tasksStatusMap);
        return industryList;

    }

    /**
     * *************************************
     *
     * @getOrganizationByOrgId() This method is used to set the organization
     * name in add consultant field addConsultant.jsp
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     **************************************
     */
    public Map getOrganizationByOrgId(int orgId) throws ServiceLocatorException {

        Map organizationNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT a.account_id, a.account_name FROM accounts a, org_rel o WHERE a.account_id=o.related_org_id AND o.STATUS='active' AND o.org_id=" + orgId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                organizationNameMap.put(resultSet.getInt("a.account_id"), resultSet.getString("a.account_name"));
            }
        } catch (SQLException ex) {
            System.out.println("getOrganizationByOrgId method-->" + ex.getMessage());
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
        System.out.println("OrganizationNameMap-->" + organizationNameMap);
        return organizationNameMap;

    }

    /**
     * *************************************
     *
     * @getIndustryName() This method is used to set the industry name in add
     * consultant field addConsultant.jsp
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     **************************************
     */
    public Map getIndustryName() throws ServiceLocatorException {

        Map industryNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id, acc_industry_name FROM lk_acc_industry_type WHERE STATUS='active'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                industryNameMap.put(resultSet.getInt("id"), resultSet.getString("acc_industry_name"));
            }
        } catch (SQLException ex) {
            System.out.println("getIndustryName method-->" + ex.getMessage());
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
        System.out.println("IndustryNameMap-->" + industryNameMap);
        return industryNameMap;

    }

    public Map getYearsOfExp() throws ServiceLocatorException {

        Map ExperienceMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,exp_years FROM lk_years_of_exp";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ExperienceMap.put(resultSet.getInt("id"), resultSet.getString("exp_years"));
            }
        } catch (SQLException ex) {
            System.out.println("getDepartmentNameByOrgId method-->" + ex.getMessage());
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
        System.out.println("departmentNameMap-->" + ExperienceMap);
        return ExperienceMap;

    }

    public Map getNameByOrgId(int org_id) throws ServiceLocatorException {

        Map EmployeeNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT usr_id,CONCAT_WS(' ',first_name,last_name) AS name1 FROM users WHERE org_id=" + org_id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                EmployeeNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("name1"));
            }
        } catch (SQLException ex) {
            System.out.println("getDepartmentNameByOrgId method-->" + ex.getMessage());
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
        System.out.println("departmentNameMap-->" + EmployeeNameMap);
        return EmployeeNameMap;

    }

    /**
     * *****************************************************************************
     * ForUSE :getVendorTypes() getting vendor types return map object Date: May
     * 5, 2015, 11:23 PM IST Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getVendorTierTypes() throws ServiceLocatorException {
        // System.out.println("im in getIndystryTypes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map industryList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select id,vendor_type from lk_vendor_type";
        System.out.println("in dssp tier types");
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryList.put(resultSet.getInt("id"), resultSet.getString("vendor_type"));
            }
            //  System.out.println(industryList.toString());
        } catch (SQLException ex) {
            // System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        //  System.out.println("tasksStatusMap-->" + tasksStatusMap);
        return industryList;

    }

    /**
     * *****************************************************************************
     * ForUSE :getStateNameById() getting vendor types return map object Date:
     * May 5, 2015, 11:23 PM IST Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getStateNameById(String stateId) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String name = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT NAME FROM lk_states WHERE id=" + stateId;
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
        return name;
    }

    /**
     * *****************************************************************************
     * ForUSE :getFnameandLnamebyStringId() getting vendor types return map
     * object Date: May 5, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getFnameandLnamebyStringId(String userId) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String user_name = "";
        String status = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=" + userId;
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
        return resultString;
    }

    /**
     * *****************************************************************************
     * ForUSE :getEmailPhoneDetails() getting vendor types return map object
     * Date: May 5, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getEmailPhoneDetails(int userId) throws ServiceLocatorException {
        System.out.println("%%%%%%%%%%%%% ENTERED INTO THE DSDP %%%%%%%%>>>>>>>" + userId);
        String resultString = "";
        int usr_Id = 0;
        String details = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT email1,phone1 FROM users WHERE usr_id=" + userId;
        System.out.println("queryString---------->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                details += resultSet.getString("email1") + "|" + resultSet.getString("phone1");
            }
            System.out.println("Result in dsdp------->" + details);
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
        return details;
    }

    public Map getRecruitmentDeptNames(int org_id) throws ServiceLocatorException {

        Map EmployeeNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT usr_id,CONCAT(first_name,'.',last_name) AS NAMES FROM users  WHERE org_id=" + org_id;
        System.out.println("WWWWWWWWWWWWWWWWW===========>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                EmployeeNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            System.out.println("getDepartmentNameByOrgId method-->" + ex.getMessage());
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
        System.out.println("departmentNameMap-->" + EmployeeNameMap);
        return EmployeeNameMap;

    }

    /**
     * *****************************************************************************
     * ForUSE :isVendor() getting vendor is vendor or not Date: May 5, 2015,
     * 11:23 PM IST Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public boolean isVendor(int acc_id) throws ServiceLocatorException {
        // System.out.println("im in getIndystryTypes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Connection connection = null;
        boolean isvendor = false;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select * from org_rel where acc_type=5 and related_org_id=" + acc_id;
        //System.out.println("in dssp tier types");
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                isvendor = true;
            }
            //  System.out.println(industryList.toString());
        } catch (SQLException ex) {
            // System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        //  System.out.println("tasksStatusMap-->" + tasksStatusMap);
        return isvendor;

    }

    /**
     * *****************************************************************************
     * ForUSE :getSalesTeam() getting sales team members Date: May 5, 2015,
     * 11:23 PM IST Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getSalesTeam(int vendorId) throws ServiceLocatorException {
        Map salesTeamList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_miscellaneous m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) WHERE m.dept_id=7";
        String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
                + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) "
                + "WHERE m.role_id=3";
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

        return salesTeamList;


    }

    /**
     * *****************************************************************************
     * ForUSE :getSalesTeam() getting assigned sales team members Date: May 5,
     * 2015, 11:23 PM IST Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getVendorSalesTeam(int vendorId) throws ServiceLocatorException {
        Map vendorSalesTeamList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        String queryString = "SELECT a.teamMember_Id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM accteam a LEFT OUTER JOIN users u ON(u.usr_id=a.teamMember_Id) WHERE a.acc_id=" + vendorId;
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

        return vendorSalesTeamList;


    }

    /**
     * *****************************************************************************
     * Date : May 11 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * getAccountNameById() method can be used to get account name by using org
     * id, And returns accounts name
     * *****************************************************************************
     */
    public String getAccountNameById(int accountId) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String account_name = "";
        String status = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT account_name FROM accounts WHERE account_id=" + accountId;
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
        return resultString;
    }

    /**
     * *************************************
     *
     * @checkConsultantLoginId() This method is used to check the consultants
     * existence
     * @Author:Aklakh Ahmad
     *
     * @Created Date:05/12/2015
     *
     **************************************
     */
    public int checkConsultantLoginId(String emailId, int vendorId) throws ServiceLocatorException {

        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select count(*) as id from users where email1 like '" + emailId + "'";
        //System.out.println("queryString-->" + queryString);
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
        return count;
    }

    public Map getAllStates() throws ServiceLocatorException {

        Map stateMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,name FROM lk_states";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                stateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllState method-->" + ex.getMessage());
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
        System.out.println("stateMap-->" + stateMap);
        return stateMap;

    }

    /**
     * *****************************************************************************
     * ForUSE :getSalesTeam() getting sales team members Date: May 5, 2015,
     * 11:23 PM IST Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getAccTeam(int accountSearchID) throws ServiceLocatorException {
        Map salesTeamList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_miscellaneous m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) WHERE m.dept_id=7";
        String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
                + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) ";
        // + "WHERE m.role_id=3 AND m.usr_id NOT IN(SELECT teamMember_Id FROM accteam WHERE acc_id=" + accountSearchID + " )";
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

        return salesTeamList;


    }

    /**
     * *****************************************************************************
     * ForUSE :getSalesTeam() getting assigned sales team members Date: May 5,
     * 2015, 11:23 PM IST Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getAccSalesTeam(int accountSearchID) throws ServiceLocatorException {
        Map accSalesTeamList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //String queryString = "SELECT a.teamMember_Id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM accteam a LEFT OUTER JOIN users u ON(u.usr_id=a.teamMember_Id) WHERE a.acc_id=" + accountSearchID;
        try {
            statement = connection.createStatement();
//            resultSet = statement.executeQuery(queryString);
//            
//            while (resultSet.next()) {
//                accSalesTeamList.put(resultSet.getInt("teamMember_Id"), resultSet.getString("NAMES"));
//            }
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

        return accSalesTeamList;


    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * getConsultantListDetailsBySearch() getting consultant list by searching.
     *
     *
     * *****************************************************************************
     */
    public String getConsult_AttachmentLocation(int consult_acc_attachment_id) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "", attachmentLocation = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT attachment_path,attachment_name FROM acc_rec_attachment WHERE acc_attachment_id=" + consult_acc_attachment_id + "";

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                attachmentLocation += resultSet.getString("attachment_path") + File.separator+ resultSet.getString("attachment_name");

            }

            System.out.println("queryString-->" + attachmentLocation);

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
        return attachmentLocation;

    }

    /**
     * *****************************************************************************
     * ForUSE :getAllAccTeam() getting sales team members Date: May 19, 2015,
     * Author:jagan chukkala<jchukkla@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getAllAccTeam(int accountSearchID) throws ServiceLocatorException {
        Map allAccTeam = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_miscellaneous m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) WHERE m.dept_id=7";
        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
        //        + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) "
        //        + "WHERE m.role_id=3 AND m.usr_id =(SELECT teamMember_Id FROM accteam)";
        String queryString = "SELECT ur.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_roles ur LEFT OUTER JOIN users u ON (u.usr_id=ur.usr_id)WHERE ur.role_id=3";
        System.out.println("queryString----->" + queryString);
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
        System.out.println("allAccTeam-----<>" + allAccTeam);

        return allAccTeam;


    }

    /**
     * *****************************************************************************
     * ForUSE :getPrimaryAccount() to get the primary account of sales Date: May
     * 19, 2015, Author:jagan chukkala<jchukkla@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public int getPrimaryAccount(int accountSearchId) throws ServiceLocatorException {
        int primaryAccount = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        System.out.println("hello primary" + accountSearchId);

//        String queryString = "SELECT teamMember_Id FROM accteam WHERE acc_id=" + accountSearchId + " AND primaryflag=1";
        //  System.out.println("queryString" + queryString);
        try {
            statement = connection.createStatement();
//            resultSet = statement.executeQuery(queryString);
//            while (resultSet.next()) {
//                primaryAccount = resultSet.getInt("teamMember_Id");
//            }
//            if (primaryAccount > 0) {
//                System.out.println("data is there");
//            } else {
//                System.out.println("data is null");
//            }
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

        System.out.println("primary Account ---->" + primaryAccount);
        return 0;

    }

    public Map getAllVendorTeam() throws ServiceLocatorException {
        Map allVendorTeam = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_miscellaneous m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) WHERE m.dept_id=7";
        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
        //        + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) "
        //        + "WHERE m.role_id=3 AND m.usr_id =(SELECT teamMember_Id FROM accteam)";
        String queryString = "SELECT ur.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_roles ur LEFT OUTER JOIN users u ON (u.usr_id=ur.usr_id)WHERE ur.role_id=3";
        System.out.println("queryString----->" + queryString);
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
        System.out.println("allVendorTeam-----<>" + allVendorTeam);

        return allVendorTeam;


    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : praveeb<pkatru@miraclesoft.com>
     *
     * getcountryName through country id
     *
     *
     * *****************************************************************************
     */
    public String getCountry(int id) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";

        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            queryString = " SELECT country FROM lk_country WHERE id=" + id;

            System.out.println("queryString-->" + queryString);
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
        return resultString;

    }

    /**
     * *****************************************************************************
     * Date : May 19 2015
     *
     * Author : praveeb<pkatru@miraclesoft.com>
     *
     * getStateName through country id
     *
     *
     * *****************************************************************************
     */
    public String getStateName(int id) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";

        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            queryString = " SELECT name FROM lk_states WHERE id=" + id;

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
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
        return resultString;

    }

    public int updateAccountLastAccessedBy(int accId, int usrId, String accessDesc) throws ServiceLocatorException {

        //String projectType = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int c = 0;

        try {

            connection = ConnectionProvider.getInstance().getConnection();
            // System.out.println("count-->"+count);
            //String queryString1 = "update tblProjects set TotalResources="+count+" where Id="+projectId;
            String queryString1 = "UPDATE accounts SET last_access_by=" + usrId + ",last_accdesc='" + accessDesc + "',last_access_date='" + DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE account_id=" + accId;
            //  System.out.println("queryString1-->"+queryString1);
            statement = connection.createStatement();
            c = statement.executeUpdate(queryString1);

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
        return c;
    }

    /**
     * *****************************************************************************
     * Date : May 23 2015
     *
     * Author : Aklakh ahmad<mahmad@miraclesoft.com>
     *
     * getAdmin(int usrId)
     *
     * to get the the admin role
     * *****************************************************************************
     */
    public int getAdmin(int usrId) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int adminRole = 0;

        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            queryString = "SELECT COUNT(role_id)  AS id FROM usr_roles WHERE role_id=1 AND usr_id=" + usrId;

            System.out.println("queryString-->" + queryString);
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
        return adminRole;

    }

    public String getProjectMap(int userId) throws ServiceLocatorException {
        String projectDetails = "";
        List projectList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM usr_miscellaneous m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) WHERE m.dept_id=7";
        //String queryString = "SELECT m.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
        //        + "FROM usr_roles m LEFT OUTER JOIN users u ON(u.usr_id=m.usr_id) "
        //        + "WHERE m.role_id=3 AND m.usr_id =(SELECT teamMember_Id FROM accteam)";
        String queryString = "SELECT acp.Project_id,proj_name FROM acc_projects acp LEFT OUTER JOIN prj_sub_prjteam psp ON (acp.project_id=psp.sub_project_id) WHERE usr_id=" + userId + " AND current_status LIKE 'Active' limit 5";
        System.out.println("queryString----->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
//                projectList.add(resultSet.getInt("Project_id"));
//                projectList.add(resultSet.getString("proj_name"));
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
        System.out.println("projectMap----->" + projectDetails);

        return projectDetails;


    }

    /**
     * *****************************************************************************
     * ForUSE :getVendorTypes() getting vendor types return map object Date: May
     * 5, 2015, 11:23 PM IST Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getAddVendorTierTypes(String id) throws ServiceLocatorException {
        System.out.println("im in getVendorTierTypes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map industryList = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,vendor_type FROM lk_vendor_type WHERE id NOT IN "
                + "(SELECT vendor_tier_id FROM customer_ven_rel WHERE customer_id=" + id + ")";
        System.out.println("in dssp tier types");
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                industryList.put(resultSet.getInt("id"), resultSet.getString("vendor_type"));
            }
            System.out.println(industryList.toString());
        } catch (SQLException ex) {
            // System.out.println("getTaskStatusByOrgId method-->" + ex.getMessage());
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
        //  System.out.println("tasksStatusMap-->" + tasksStatusMap);
        return industryList;

    }

    /**
     * *****************************************************************************
     * ForUSE :getFnameandLnamebyStringId() getting account types return map
     * object Date: May 5, 2015, 11:23 PM IST
     * Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getAccountType(int accId) throws ServiceLocatorException {

        String resultString = "";
        int usr_Id = 0;
        String user_name = "";
        String status = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT l.acc_type_name FROM lk_acc_type l LEFT OUTER JOIN org_rel o ON(l.id=o.acc_type) WHERE o.related_org_Id=" + accId;
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
        return resultString;
    }

    /**
     * *****************************************************************************
     * ForUSE :getTypeOfAccount() getting account types return VC OR AC object
     * Date: May 29, 2015, 11:23 PM IST
     * Author:manikanta<meeralla@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getTypeOfAccount(int orgId) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        int accType = 0;
        try {
            queryString = " SELECT acc_type FROM org_rel WHERE related_org_id=" + orgId;
            System.out.println("queryString-->" + queryString);
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
        return resultString;

    }

    /**
     * *****************************************************************************
     * ForUSE :getTierOneOrg_Id()getting tier one organization id's return array
     * list Date:06,02, 2015, Author:praveen<pkatru@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public ArrayList getTierOneOrg_Id(boolean flag, int org_id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int count = 0;
        ArrayList<Integer> array = new ArrayList<Integer>();

        try {
            if (flag) {
                queryString = "SELECT DISTINCT(vendor_id) FROM customer_ven_rel WHERE is_permanent=1 AND STATUS='Active' AND customer_id=" + org_id;
            } else {
                queryString = "SELECT DISTINCT(vendor_id) FROM customer_ven_rel WHERE vendor_tier_id=1 AND STATUS='Active' AND customer_id=" + org_id;
            }
            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {

                array.add(resultSet.getInt("vendor_id"));

            }
            System.out.println("upto here very fine in dsdp");
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
        return array;
    }

    /**
     * *************************************
     *
     * @getPermanentStates() This method is used to get the permanent state on
     * the basic of basic country
     * @Author:Aklakh Ahmad
     *
     * @Created Date:05/15/2015
     *
     **************************************
     */
    public Map getPermanentStates(int conId) throws ServiceLocatorException {

        Map pStateMap = new LinkedHashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,name FROM lk_states where countryId=" + conId + " ORDER BY name ASC";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                pStateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllState method-->" + ex.getMessage());
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
        System.out.println("PstateMap-->" + pStateMap);
        return pStateMap;

    }

    /**
     * *************************************
     *
     * @getCurrentStates() This method is used to get the current state on the
     * basic of basic country
     * @Author:Aklakh Ahmad
     *
     * @Created Date:05/15/2015
     *
     **************************************
     */
    public Map getCurrentStates(int cId) throws ServiceLocatorException {

        Map cStateMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,name FROM lk_states where countryId=" + cId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                cStateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllState method-->" + ex.getMessage());
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
        System.out.println("CstateMap-->" + cStateMap);
        return cStateMap;

    }

    /**
     * *************************************
     *
     * @getPreferredStates() This method is used to get the preferred state on
     * the basic of basic country
     * @Author:Aklakh Ahmad
     *
     * @Created Date:05/15/2015
     *
     **************************************
     */
    public Map getPreferredStates(int countryId) throws ServiceLocatorException {

        Map stateMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,name FROM lk_states where countryId=" + countryId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                stateMap.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllState method-->" + ex.getMessage());
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
        System.out.println("stateMap-->" + stateMap);
        return stateMap;

    }

    public String getOrganizationName(int aInt) throws ServiceLocatorException {
        String orgName = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "SELECT account_name FROM accounts WHERE account_id=" + aInt;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                orgName = resultSet.getString("account_name");
            }
        } catch (SQLException ex) {
            System.out.println("getAllState method-->" + ex.getMessage());
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

        return orgName;
    }

    /**
     * *****************************************************************************
     * ForUSE :getMailIdsOfVendorManager()getting tier one organization id's
     * return array list Date:06,02, 2015,
     * Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public RequirementVTO setRequirementDetails(String reqId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        int count = 0;
        RequirementVTO requirementVTO = new RequirementVTO();

        try {
            queryString = "SELECT req_name,req_function_desc,req_st_date,no_of_resources FROM acc_requirements WHERE requirement_id=" + reqId;
            System.out.println("queryString--In DSDP setRequirementDetails>>>>>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>After execute query in dsdp for setReqDetailsssssssssssssssssssssssssssssssssssssssss");
            while (resultSet.next()) {
                System.out.println("VALUEW IN WHILE>>>>>>>>>>>>>>>" + resultSet.getString("req_name") + "     " + resultSet.getString("req_function_desc") + " " + resultSet.getString("no_of_resources") + " " + resultSet.getString("req_st_date"));
                requirementVTO.setReqName(resultSet.getString("req_name"));
                requirementVTO.setReqDesc(resultSet.getString("req_function_desc"));
                System.out.println("first two are ok");
                requirementVTO.setReqStartDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("req_st_date")));
                //requirementVTO.setReqEndDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("req_tr_date")));
                requirementVTO.setReqResources(resultSet.getString("no_of_resources"));
                System.out.println("AFTER SETTING VALUES IN WHILE");
            }

            System.out.println("setRequirementDetails>>>>>>>" + resultStr);
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
        return requirementVTO;
    }

    /**
     * *****************************************************************************
     * ForUSE :getMailIdsOfVendorManager()getting tier one organization id's
     * return array list Date:06,02, 2015,
     * Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getMailIdsOfVendorManagerAndLeads(String vendorIdList) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        int count = 0;

        try {
            queryString = "SELECT u.email1 FROM users u "
                    + "LEFT OUTER JOIN usr_grouping ug ON(ug.usr_id=u.usr_id) "
                    + "WHERE ug.cat_type=1 "
                    + "AND ug.is_primary=1 "
                    + "AND ug.STATUS='Active' "
                    + "AND u.cur_status='Active' "
                    + "AND u.org_id IN(" + vendorIdList + ")";
            System.out.println("queryString--In DSDP getMailIdsOfVendorManagerLeads>>>>" + queryString);
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
            System.out.println("getMailIdsOfVendorManager>>>>>>>" + resultStr);
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
        return resultStr;
    }
//

    /**
     * *****************************************************************************
     * ForUSE :getMailIdsOfVendorManager()getting tier one organization id's
     * return array list Date:06,02, 2015,
     * Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public void getMailIdsOfConAndEmp(RecruitmentAction recruitmentAction) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        int count = 0;

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

            System.out.println("queryString--In DSDP getMailIdsOfVendorManager>>>>" + queryString);
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
            System.out.println("getMailIdsOfConAndEmp>>>>>>>" + recruitmentAction.getConEmail() + "  " + recruitmentAction.getEmpEmail());
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
        //return resultStr;
    }

    /**
     * *****************************************************************************
     * Date : June 10 2015
     *
     * Author : praveeb<pkatru@miraclesoft.com>
     *
     *
     *
     *
     * *****************************************************************************
     */
    public Map getManagerAndDirectersByOrgID(int org_id, int projectId) throws ServiceLocatorException {

        Map EmployeeNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //String queryString = "SELECT usr_id,CONCAT(first_name,'.',last_name) AS NAMES FROM users  WHERE org_id=" + org_id;
        String queryString = "SELECT p.usr_id,CONCAT_WS(' ',u.first_name,u.last_name) AS NAMES FROM Project_team p LEFT OUTER JOIN users u ON(p.usr_id=u.usr_id) WHERE  (account_id=" + org_id + " AND project_id=" + projectId + ") and (designation='Di' OR designation='M')";
        System.out.println("WWWWWWWWWWWWWWWWW===========>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                EmployeeNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAMES"));
            }
        } catch (SQLException ex) {
            System.out.println("getDepartmentNameByOrgId method-->" + ex.getMessage());
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
        System.out.println("departmentNameMap-->" + EmployeeNameMap);
        return EmployeeNameMap;

    }

    /**
     * *****************************************************************************
     * ForUSE :getReqNameById()getting requirement name by id return array list
     * Date:06,02, 2015, Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getReqNameById(int reqId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        int count = 0;

        try {
            queryString = "SELECT req_name FROM acc_requirements WHERE requirement_id=" + reqId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {

                resultString = resultSet.getString("req_name");

            }

            System.out.println("getReqNameById>>>>>>>" + resultString);
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
        return resultString;
    }

    /**
     * *****************************************************************************
     * ForUSE :getConsultNameById()getting consult name by id return array list
     * Date:06,02, 2015, Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getConsultNameById(int conId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String resultStr = "";
        int count = 0;

        try {
            queryString = "SELECT CONCAT(first_name,'.',last_name) as Name FROM users WHERE usr_id=" + conId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>query>>>>>" + queryString);
            while (resultSet.next()) {

                resultString = resultSet.getString("Name");

            }

            System.out.println("getReqNameById>>>>>>>" + resultString);
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
        return resultString;
    }

    /**
     * *****************************************************************************
     * ForUSE :getsubProject() getting the sub project of particular project
     * Date: June 12, 2015, Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getSubProject(int projectID, int userID) throws ServiceLocatorException {
        Map allSubProject = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        System.out.println("====== Sub project =======");
        //String queryString = "SELECT project_id, proj_name FROM acc_projects WHERE proj_type='SP' AND parent_project_id=" + projectID;
        String queryString = "SELECT project_id, proj_name FROM acc_projects WHERE proj_type='SP' AND parent_project_id=" + projectID + "  AND project_id NOT IN(SELECT ap.project_id FROM acc_projects ap LEFT OUTER JOIN prj_sub_prjteam sp ON(ap.parent_project_id=sp.project_id ) WHERE ap.project_id=sp.sub_project_id AND ap.proj_type='SP' AND sp.usr_id=" + userID + " AND sp.project_id=" + projectID + ")";
        System.out.println("queryString----->" + queryString);
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
        System.out.println("allSubProject-----<>" + allSubProject);

        return allSubProject;


    }

    /**
     * *****************************************************************************
     * ForUSE :getAssignedSubProject() getting the sub project of particular
     * project Date: June 12, 2015, Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public Map getAssignedSubProject(int projectID, int userID) throws ServiceLocatorException {
        Map assignSubProject = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        String queryString = "SELECT ap.project_id, ap.proj_name "
                + "FROM acc_projects ap LEFT OUTER JOIN prj_sub_prjteam sp ON(ap.parent_project_id=sp.project_id )"
                + "WHERE ap.project_id=sp.sub_project_id AND ap.proj_type='SP' AND sp.usr_id=" + userID + " AND sp.project_id=" + projectID;
        System.out.println("queryString----->" + queryString);
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
        System.out.println("allSubProject-----<>" + assignSubProject);

        return assignSubProject;


    }

    /**
     * *************************************
     *
     * @getContactPersonsByProjectIdHeigerDesignationId() This method is used to
     * set the set designations
     *
     * @Author:pravee<pkatru@miraclesoft.com>
     *
     * @Created Date:06/22/2015
     *
     **************************************
     */
    public Map getContactPersonsByProjectIdHeigerDesignationId(int projectID, int designation, int usr_id) throws ServiceLocatorException {
        Map departmentNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        System.out.println("in dsdp======= project id" + projectID);
        System.out.println("in dsdp======= designation id" + designation);
        System.out.println("in dsdp======= usr Id" + usr_id);

        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT pt.usr_id,CONCAT (first_name,'.',last_name) names FROM Project_team pt JOIN users u ON (pt.usr_id=u.usr_id) WHERE pt.designation IN (13,3,4,5,6) AND project_id=" + projectID + " AND current_status='Active' AND pt.usr_id NOT IN(" + usr_id + ")";
        System.out.println("query=====" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                departmentNameMap.put(resultSet.getInt("usr_id"), resultSet.getString("names"));
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
        System.out.println("Designation-->" + departmentNameMap);
        return departmentNameMap;
    }

    /**
     * *************************************
     *
     * @getDesignationId() get designation id trough project id and usr id
     *
     *
     * @Author:pravee<pkatru@miraclesoft.com>
     *
     * @Created Date:06/22/2015
     *
     **************************************
     */
    private int getDesignationId(int usr_id, int projectId) throws ServiceLocatorException {
        Map departmentNameMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int id = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT designation FROM Project_team WHERE usr_id=" + usr_id + " AND project_id=" + projectId;
        System.out.println("query=====" + queryString);
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
        return id;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST Author :
     * :RamaKrishna<lankireddy@miraclesoft.com> ForUse : getting TeamMembers
     * under userId And return map object
     * *****************************************************************************
     */
    public String getTeamMembersUpTo(int reportsTo, java.sql.PreparedStatement theStatement) throws ServiceLocatorException {
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
            //System.out.println("Main ReportsTo:" + reportsTo);
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
                    //  System.out.println("keyCnt--- Value"+keyCnt);

                }
            }

            for (int i = 0; i < keyCnt; i++) {
                key = keys[i];

                //tempMap = getTeamMembersUpTo(key, theStatement);
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
                // System.out.println("keyCnt value"+keyCnt);
                // System.out.println("i value"+i);
            }

        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }
        //myTeamManagersMap = sortMapByValue(myTeamManagersMap);
        //return myTeamManagersMap; // returning the object.
        return resultTeam;

    } //closing the method

    /**
     * *****************************************************************************
     * Date : APRIL 16, 2015, 8:30 PM IST Author :
     * RamaKrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : sorting of map taken from Nagireddy<nseerapu@miraclesoft.com>
     * sir
     * *****************************************************************************
     */
    public < K, V extends Comparable< ? super V>> Map< K, V> sortMapByValue(final Map< K, V> mapToSort) {
        List< Map.Entry< K, V>> entries =
                new ArrayList< Map.Entry< K, V>>(mapToSort.size());

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

        return sortedMap;
    }
    //praveen <pkatru@miraclesoft.com>
    //06232015: Date

    public int getNoOfResourcesInProject(int projectId, String prjFlag) throws ServiceLocatorException {
        int resultInt = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        if ("Main Project".equalsIgnoreCase(prjFlag)) {
            queryString = "SELECT COUNT(usr_id) AS COUNT FROM Project_team WHERE project_id=" + projectId;
        } else {
            queryString = "SELECT COUNT(DISTINCT(usr_id)) AS COUNT FROM prj_sub_prjteam WHERE sub_project_id=" + projectId;
        }
        System.out.println("query in dsdp.." + queryString);
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
        return resultInt;
    }

    /**
     * *****************************************************************************
     * Date : APRIL 21, 2015, 11:16 PM IST Author
     * :ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getting task type based on task id
     * *****************************************************************************
     */
    public Map getCSRTeam(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        Map map = new HashMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //String querystrings = "SELECT task_types_id,task_type_name FROM task_list  JOIN  lk_task_types ON(task_related_to=task_rel_toId) WHERE task_id=?";
        String querystrings = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAMES,u.usr_id FROM users u LEFT OUTER JOIN usr_roles r ON(r.usr_id=u.usr_id) WHERE r.role_id=1";
        System.out.println("querystrings>>>>>>>>>>>>>>>" + querystrings);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querystrings);
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
        return map;
    }

    /**
     * *****************************************************************************
     * Date : june 26, 2015,
     *
     * Author :praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getting no of submission by requirement id and organization id
     * *****************************************************************************
     */
    public int getNoOfSubmisions(int req_id, int orgId) throws ServiceLocatorException {
        //System.out.println("getNoOfSubmisions start.");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        int resultString = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            queryString = "SELECT COUNT(createdbyOrgId) as count FROM req_con_rel  WHERE  status not like '%SOW%' and reqId=" + req_id + " AND createdbyOrgId=" + orgId;
            //  System.out.println("getNoOfSubmisions query ->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getInt("count");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

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
        //System.out.println("getNoOfSubmisions end.");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : june 26, 2015,
     *
     * Author :praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getting AvgRateByOrg by requirement id and organization id
     * *****************************************************************************
     */
    public double getAvgRateByOrg(int req_id, int orgId) throws ServiceLocatorException {
        // System.out.println("getAvgRateByOrg start.");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        double resultString = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            queryString = "SELECT AVG(rate_salary) as avar FROM req_con_rel WHERE status not like '%SOW%' and reqId=" + req_id + " AND createdbyOrgId=" + orgId;
            //   System.out.println("getAvgRateByOrg query ->"+queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getInt("avar");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

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
        //System.out.println("getAvgRateByOrg end.");
        return resultString;
    }

    /**
     * *************************************
     *
     * @customerList() This method is used to set the department name in
     * employee search field employeeSearch.jsp
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/19/2015
     *
     **************************************
     */
    public Map customerList(String typeOfUser, int userSessionId) throws ServiceLocatorException {

        Map customerMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
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
        System.out.println("Designation-->" + customerMap);
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

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();

        String url_ext = "";

        try {

            queryString = "SELECT email_ext from siteaccess_mail_ext WHERE org_id=" + orgId;
            System.out.println("query==========>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                url_ext = resultSet.getString("email_ext");
            }
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        } finally {
            try {

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

        return url_ext;
    }

    /**
     * *************************************
     *
     * @getVendorList() This method is used to set the department name in
     * employee search field employeeSearch.jsp
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/19/2015
     *
     **************************************
     */
    public Map getVendorList() throws ServiceLocatorException {

        Map customerMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT a.account_id,a.account_name FROM accounts a LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) WHERE o.acc_type=5";
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
        System.out.println("vendors-->" + customerMap);
        return customerMap;

    }

    //praveen
    public String getTypeOfUser(int userId) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String accType = "";
        try {
            queryString = "SELECT type_of_user FROM users WHERE usr_id=" + userId;
            System.out.println("queryString-->" + queryString);
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
        return resultString;

    }

    /**
     * *************************************
     *
     * @checkResetEmailId() This method is used to check the existence
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:07/15/2015
     *
     **************************************
     */
    public int checkResetEmailId(String emailId, int orgId) throws ServiceLocatorException {

        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        if (orgId == 10001) {
            queryString = "Select count(email1) as id from users where email1='" + emailId + "'";
            //System.out.println("siteadimin"+queryString);
        } else {
            // queryString = "Select count(email1) as id from users where email1 like '" + emailId + "'AND work_for_org=" + orgId;
            queryString = "Select count(email1) as id from users where email1='" + emailId + "' AND org_id=" + orgId;
            //System.out.println("remain"+queryString);
        }
        //System.out.println("queryString-->" + queryString);
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
        return count;
    }

    /**
     * *************************************
     *
     * @getCsrAccountCount() This method is used to get Csr Account Count
     * @Author:Manikanta<meeralla@miraclesoft.com>
     *
     * @Created Date:07/15/2015
     *
     **************************************
     */
    public int getCsrAccountCount(int usrId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT COUNT(*) AS COUNT FROM csrorgrel WHERE status='Active' AND csr_id=" + usrId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                count = resultSet.getInt("COUNT");
            }
            System.out.println("queryString==>In getCsrAccountCount" + queryString);
        } catch (SQLException ex) {
            System.out.println("getCsrAccountCount method-->" + ex.getMessage());
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
        return count;

    }

    /**
     * *************************************
     *
     * @customerList() This method is used to get category of the group in it.
     * @Author:Praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:07/14/2015
     *
     **************************************
     */
    public Map getRequiteCategory(int grpId) throws ServiceLocatorException {
        Map customerMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select grpcategory,catname from lkusr_grpcategory  where grpid=" + grpId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("grpcategory"), resultSet.getString("catname"));
            }
        } catch (SQLException ex) {
            System.out.println("req category method-->" + ex.getMessage());
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
        System.out.println("vendors-->" + customerMap);
        return customerMap;

    }

    /**
     * *************************************
     *
     * @customerList() This method is used to set the department name in
     * employee search field employeeSearch.jsp
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/19/2015
     *
     **************************************
     */
    public Map getProjectList(String roleValue, int userSessionId, int orgId) throws ServiceLocatorException {

        Map projectMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        if (roleValue.equalsIgnoreCase("Director")) {
            queryString = "SELECT project_id,proj_name FROM acc_projects WHERE acc_id=" + orgId;
        } else {
            queryString = "SELECT project_id,proj_name FROM acc_projects WHERE created_by=" + userSessionId + " AND acc_id=" +  orgId+" AND proj_type = 'MP'";
        }
        System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                projectMap.put(resultSet.getInt("project_id"), resultSet.getString("proj_name"));
            }
        } catch (SQLException ex) {
            System.out.println("getProjectList method-->" + ex.getMessage());
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
        return projectMap;

    }

    /**
     * *************************************
     *
     * @getCsrAccountCount() This method is used to get Csr Account Count
     * @Author:Manikanta<meeralla@miraclesoft.com>
     *
     * @Created Date:07/17/2015
     *
     **************************************
     */
    public String getUserNameByUserId(int userId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String result = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT concat(first_name,'.',last_name) as name FROM users WHERE usr_id=" + userId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getString("name");
            }
        } catch (SQLException ex) {
            System.out.println("getCsrAccountCount method-->" + ex.getMessage());
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
        return result;
    }

    public Map GetProjectManagersListByOrgId(int sessionOrgId) throws ServiceLocatorException {
        Map managerMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";

        queryString = "SELECT u.usr_id, concat(first_name,'.',last_name) Names FROM users u JOIN usr_roles ur ON (ur.usr_id=u.usr_id ) WHERE ur.role_id=3 AND org_id=" + sessionOrgId;

        // System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                managerMap.put(resultSet.getInt("usr_id"), resultSet.getString("Names"));
            }
        } catch (SQLException ex) {
            System.out.println("getProjectList method-->" + ex.getMessage());
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
        return managerMap;
    }

    public Map getRolesForAccType(String orgType) throws ServiceLocatorException {
        Map rolesMap = new HashMap();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //  Statement statement = null;
        ResultSet resultSet = null;
        //int result = 0;
        //String resultMessage = "";
        String queryString = "";
        //StringBuffer sb = new StringBuffer();
//        System.out.println("getActionName-->" + accAuthAjaxHandlerAction.getActionName());
//        System.out.println("Status-->" + accAuthAjaxHandlerAction.getStatus());
//        System.out.println("Desc-->" + accAuthAjaxHandlerAction.getDesc());

        try {

            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT `role_id`,`role_name` FROM `servicebay`.`roles` WHERE org_type='" + orgType + "' ";

            System.out.println("queryString-->" + queryString);

            preparedStatement = connection.prepareStatement(queryString);
            // preparedStatement.setInt(1, dept_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // resultMessage += resultSet.getInt("role_id") + "#" + resultSet.getString("role_name") + "^";
                rolesMap.put(resultSet.getInt("role_id"), resultSet.getString("role_name"));
            }

            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        return rolesMap;


    }

    /**
     * *************************************
     *
     * @getHomeRedirectDetails() method is used to get Requirement details of
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public Map getAllAccounts(int OrgId) throws ServiceLocatorException {
        Map accountsMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
//        if ("AC".equalsIgnoreCase(accType)) {
//            queryString = "SELECT account_id,account_name FROM accounts LEFT OUTER JOIN org_rel ON(account_id=related_org_Id) WHERE type_of_relation = 'C'";
//        } else if ("VC".equalsIgnoreCase(accType)) {
//            queryString = "SELECT account_id,account_name FROM accounts LEFT OUTER JOIN org_rel ON(account_id=related_org_Id) WHERE type_of_relation = 'V'";
//        } else {
         if(OrgId>0){
            queryString = "SELECT account_id,account_name FROM accounts where account_id="+OrgId; 
         }
         else{
            queryString = "SELECT account_id,account_name FROM accounts"; 
         }
//        }
        System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                accountsMap.put(resultSet.getInt("account_id"), resultSet.getString("account_name"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllAccounts method-->" + ex.getMessage());
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
        return accountsMap;
    }

    /**
     * *************************************
     *
     * @getAllRoles() method is used to get Requirement details of account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public Map getAllRoles(String accType) throws ServiceLocatorException {
        Map rolesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        if (accType.equalsIgnoreCase("VC")) {
            queryString = "SELECT role_id,role_name,org_type FROM roles WHERE STATUS='Active'  AND org_type='V'";
        } else if (accType.equalsIgnoreCase("AC")) {
            queryString = "SELECT role_id,role_name,org_type FROM roles WHERE STATUS='Active' AND org_type='C'";
        } else {
            queryString = "SELECT role_id,role_name,org_type FROM roles WHERE STATUS='Active'";
        }
        System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
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
            System.out.println("getAllRoles method-->" + ex.getMessage());
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
        return rolesMap;
    }

    /**
     * *************************************
     *
     * @getAllRoles() method is used to get Requirement details of account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String getAllRolesString(String accType) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
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
        // System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("role_id") + "|" + resultSet.getString("role_name") + "^";
            }
        } catch (SQLException ex) {
            System.out.println("getAllRoles method-->" + ex.getMessage());
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
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : July 23, 2015, 2:23 AM IST Author
     * :Vinodkumar<vsiram@miraclesoft.com>
     *
     * ForUse : getting organization type of relation based on orgid
     * *****************************************************************************
     */
    public String getOrganizationType(String org_id) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String result = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT type_of_relation FROM org_rel WHERE related_org_id=" + org_id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getString("type_of_relation");
            }
        } catch (SQLException ex) {
            System.out.println("getOrganizationType method-->" + ex.getMessage());
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
        return result;
    }

    /**
     * *****************************************************************************
     * Date : July 23, 2015, 2:23 AM IST Author
     * :Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getting organization type of relation based on orgid
     * *****************************************************************************
     */
    public int getCategoryByUserId(int usrId) {
        //   ArrayList category = new ArrayList();
        int groupid = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT cat_type FROM usr_grouping WHERE usr_id=? ";

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

        return groupid;
    }
//aklaq

    public String getEmiltExistOrNot(String resourceType, String conEmail, int sessionOrgId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String result = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";

       // queryString = "SELECT usr_id,org_id,type_of_user FROM users WHERE cur_status='Active' AND  email1  ='" + conEmail + "'";
queryString = "SELECT u.usr_id,u.org_id,u.type_of_user , ud.consultant_skills FROM usr_details ud LEFT OUTER JOIN users u ON(ud.usr_id=u.usr_id)  WHERE cur_status='Active' AND  email1  ='" + conEmail + "'";
        System.out.println(">>>>>>  getEmiltExistOrNot -->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if ("IC".equalsIgnoreCase(resultSet.getString("type_of_user"))) {
                    result = resultSet.getString("usr_id") + "#IC";
                } else {
                    if (sessionOrgId == resultSet.getInt("org_id")) {
                        result = resultSet.getString("usr_id") + "#VC";
                    } else {
                        result = null;
                    }
                }

                String str1 = resultSet.getString("consultant_skills");
                StringTokenizer stk1 = new StringTokenizer(str1, ",");
                String skillsResultString="";
                while (stk1.hasMoreTokens()) {
                    String s=stk1.nextToken();
                     skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId(s)+","+s+"^";
                  //  skillsResultString +=  s+"^";
                    System.out.println("skillsResultString--->"+skillsResultString);
                }
                result+= "#"+skillsResultString;
                System.out.println("Reesult in dsdp---->"+result);
            }
            return result;

        } catch (SQLException ex) {
            System.out.println("getProjectList method-->" + ex.getMessage());
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
        return result;
    }
//aklaq

    public String getIsExistConsultantByReqId(String reqId, String result) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String resultmsg = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";

        queryString = "SELECT COUNT(consultantId) COUNT FROM req_con_rel WHERE reqId=" + reqId + " AND consultantId=" + result;

        System.out.println(">>>>>>>>>>>>getIsExistConsultantByReqId --->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultmsg = resultSet.getString("count");
            }


        } catch (SQLException ex) {
            System.out.println("getProjectList method-->" + ex.getMessage());
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
        return resultmsg;
    }

    public String getUserSubCategoryByUsrId(int usrId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String resultmsg = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";

        queryString = "SELECT sub_cat FROM usr_grouping WHERE usr_id=" + usrId;

        System.out.println(">>>>>>>>>>>>getUserSubCategoryByUsrId --->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultmsg = resultSet.getString("sub_cat");
            }


        } catch (SQLException ex) {
            System.out.println("getUserSubCategoryByUsrId method-->" + ex.getMessage());
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
        return resultmsg;
    }

    /**
     * *************************************
     *
     * @getAllRoles() method is used to get Requirement details of account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public Map getReporingByProjectId(int prjId) throws ServiceLocatorException {
        Map rolesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAME,pt.usr_id "
                + "FROM project_team pt "
                + "LEFT OUTER JOIN users u ON(u.usr_id=pt.usr_id) "
                + "WHERE pt.project_id=" + prjId + " "
                + "AND pt.designation IN(3,4,5,13,6) "
                + "AND pt.current_status='Active'";

        System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                rolesMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAME"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllRoles method-->" + ex.getMessage());
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
        return rolesMap;
    }

    public Map getRequiteCategory() throws ServiceLocatorException {
        Map customerMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select id,grpname from lkusr_group";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("id"), resultSet.getString("grpname"));
            }
        } catch (SQLException ex) {
            System.out.println("req category method-->" + ex.getMessage());
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
        System.out.println("vendors-->" + customerMap);
        return customerMap;

    }

    public boolean isHeadHunterOrNot(String requirementId) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        boolean flag = false;
        String queryString = "SELECT * FROM acc_requirements WHERE  tax_term='PE' AND requirement_id=" + requirementId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            System.out.println("req category method-->" + ex.getMessage());
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
        return flag;
    }

    public int getusrIdByemailId(String emailId) throws ServiceLocatorException {

        int usr_id = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "Select usr_id from users where email1 like '" + emailId + "'";
        //System.out.println("queryString-->" + queryString);
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
        return usr_id;
    }

    public List getProjectTeamMembersList(int projectId) {
        ArrayList projectMembers = new ArrayList();
        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT usr_id FROM project_team WHERE project_id=" + projectId;

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

        System.out.println(projectMembers);
        return projectMembers;
    }

    public int getUsrRoleById(int usrId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int roleId = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT role_id FROM usr_roles WHERE usr_id=" + usrId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                roleId = resultSet.getInt("role_id");
            }
            System.out.println("queryString==>In getCsrAccountCount" + queryString);
        } catch (SQLException ex) {
            System.out.println("getCsrAccountCount method-->" + ex.getMessage());
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
        return roleId;

    }

    /**
     * *************************************
     *
     * @getSubProjectTeamMap() method is used to get Requirement details of
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public Map getSubProjectTeamMap(int prjId) throws ServiceLocatorException {
        Map rolesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT CONCAT(TRIM(u.first_name),'.',TRIM(u.last_name)) AS NAME,u.usr_id "
                + "FROM users u "
                + "LEFT OUTER JOIN prj_sub_prjteam pt ON(pt.usr_id=u.usr_id) "
                + "WHERE pt.sub_project_id=" + prjId;

        System.out.println(">>>>>>>>>>>>PROJECT LIST>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                rolesMap.put(resultSet.getInt("usr_id"), resultSet.getString("NAME"));
            }
        } catch (SQLException ex) {
            System.out.println("getAllRoles method-->" + ex.getMessage());
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
        return rolesMap;
    }

    /**
     * *************************************
     *
     * @doCheckEmailExistsOrNot() method is used to know user email already in
     * migration rable or not
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public int doCheckEmailExistsOrNot(int conId, int reqId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int roleId = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT m.id FROM con_or_ven_mig_rel m WHERE m.reqId=" + reqId + " AND m.consultantid=" + conId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
            System.out.println("queryString==>In getCsrAccountCount" + queryString);
        } catch (SQLException ex) {
            System.out.println("getCsrAccountCount method-->" + ex.getMessage());
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
        return roleId;

    }

    public String getSOWStatus(SOWAction sowAction) throws ServiceLocatorException {
        String status = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT his_curstatus FROM his_serviceagreements WHERE his_serviceid=" + sowAction.getServiceId();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                status = resultSet.getString("his_curstatus");
                System.out.println("----------------------in dsdo-------------------------" + resultSet.getString("his_curstatus"));
            }
            System.out.println("queryString==>In getCsrAccountCount" + queryString);
        } catch (SQLException ex) {
            System.out.println(" method-->" + ex.getMessage());
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
        return status;
    }

    /**
     * *****************************************************************************
     * Date : september 9, 2015, 11:23 PM IST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getting attachment data based on the attachment id
     * *****************************************************************************
     */
    public String getExcelocation(int id) throws ServiceLocatorException {


        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "", attachmentLocation = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT resultant_file FROM utility_logger WHERE log_id =" + id + "";

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attachmentLocation = resultSet.getString("resultant_file");// + resultSet.getString("logger");//+"|"+resultSet.getString("uploaded_file");
            }

            System.out.println("queryString-->" + attachmentLocation);

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
        return attachmentLocation;
    }

    public String getVendorFormAttachmentLocation(int acc_attachment_id) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "", attachmentLocation = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            System.out.println("in DownloadAction");
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT attachment_path,attachment_name FROM acc_rec_attachment WHERE acc_attachment_id=" + acc_attachment_id + "";

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                attachmentLocation += resultSet.getString("attachment_path") + File.separator + resultSet.getString("attachment_name");

            }

            System.out.println("queryString-->" + attachmentLocation);

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
        return attachmentLocation;

    }

    public Map getWorkLocations(int accId) throws ServiceLocatorException {
        Map workLocationsMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT acc_add_id,location_name "
                + " FROM acc_address  "
                + " WHERE acc_id=" + accId;

        System.out.println(">>>>>>>>>>>>WorkLocations LIST queryString >>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                workLocationsMap.put(resultSet.getInt("acc_add_id"), resultSet.getString("location_name"));
            }
        } catch (SQLException ex) {
            System.out.println("getWorkLocations method-->" + ex.getMessage());
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
        return workLocationsMap;

    }

    public Map getReqSkillsCategory(int flag) throws ServiceLocatorException {
        Map customerMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT id,skill_name FROM lk_skills WHERE STATUS='Active' AND online_flag="+ flag +" ";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                customerMap.put(resultSet.getInt("id"), resultSet.getString("skill_name"));
            }
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
        System.out.println("skills-->" + customerMap);
        return customerMap;

    }
   

    public String getReqSkillsSet(int skillId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String resultString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT skill_name FROM lk_skills WHERE id=" + skillId;
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
        System.out.println("getReqSkillsSet queryString-->" + queryString);
        return resultString;

    }
    
    public int getReqSkillId(String skillName) throws ServiceLocatorException {
        System.out.println("in getReqSkillId--------->"+skillName);
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int resultString=0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT ls.id FROM lk_skills ls WHERE ls.skill_name='"+skillName+"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getInt("id");
                System.out.println("resultString in while------>"+resultString);
            }
           // resultString = resultString + ',';
               System.out.println("resultString after while------>"+resultString);

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
      //  System.out.println("getReqSkillsSet queryString-->" + queryString);
        return resultString;

    }
    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getting Budget data based on the cost center Code id
     * *****************************************************************************
     */

    public String getCostCenterBudget(String ccCode) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT budgetamt,id,status FROM costcenterbudgets WHERE ccbstatus like 'Active' and cccode='" + ccCode + "'";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            System.out.println("----------"+queryString);
            while (resultSet.next()) {
                resultString += resultSet.getDouble("budgetamt") + "^" + resultSet.getInt("id")+"^"+resultSet.getString("status");
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
        return resultString;

    }
     /**
     * *****************************************************************************
     * Date : October 07, 2015, 04:13 PM IST
     * Author:Manikanta<meeralla@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public Map getProjectsMap(int orgId,String projectType,int year) throws ServiceLocatorException {

        Map projectsMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT project_id,proj_name FROM acc_projects WHERE proj_type='"+projectType+"' "
                + " AND (EXTRACT(YEAR FROM proj_stdate) = " + year + "  OR EXTRACT(YEAR FROM proj_trdate) =" + year + " )"
                + " AND acc_id=" + orgId;
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

        return projectsMap;

    }
    public Map getCostCenterNames(int sessionOrgId) throws ServiceLocatorException {
        Map ccNames = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT cccode,ccname FROM costcenter WHERE orgid=" + sessionOrgId;
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
        return ccNames;
    }

//    public String getCostCenterNameByProjectId(int projectId) {
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        String queryString = "";
//        String resultString = "";
//        try {
//            queryString = "SELECT ccname FROM costcenter cc LEFT OUTER JOIN acc_projects ap ON(cc.cccode=ap.cccode) WHERE project_id =" + projectId;
//            connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(queryString);
//            while (resultSet.next()) {
//                resultString += resultSet.getString("ccname");
//            }
//        } catch (Exception sqe) {
//            sqe.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                    resultSet = null;
//                }
//                if (statement != null) {
//                    statement.close();
//                    statement = null;
//                }
//                if (connection != null) {
//                    connection.close();
//                    connection = null;
//                }
//            } catch (SQLException sqle) {
//                sqle.printStackTrace();
//            }
//        }
//        return resultString;
//    }
//    
    public int noOfQuestionsReturns(int id, String examLevel, String examType, int orgid) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int attempt = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT COUNT(id) AS total FROM sb_onlineexamQuestion WHERE skillid=" + id + " AND LEVEL='" + examLevel + "' AND exam_type='" + examType + "' AND orgid=" + orgid + " AND STATUS='Active' ";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attempt = resultSet.getInt("total");
//                attempt=1;
            }
            System.out.println("queryString==>In isAttempted" + queryString);
        } catch (SQLException ex) {
            //System.out.println("isAttempted method-->" + ex.getMessage());
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
        return attempt;

    }
    
    public Map getSkillsQuestionsMap(String validKey) throws ServiceLocatorException {
        Map skillsQuestionsMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;


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
        System.out.println("skillsQuestionsMap-->" + skillsQuestionsMap);
        return skillsQuestionsMap;

    }
    
    public Map getSkillsMap(int  contechReviewId) throws ServiceLocatorException {
        Map skillsQuestionsMap = new TreeMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;


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

        queryString = "SELECT option1,option2,option3,option4,option5,option6,option7,option8,option9,option10 FROM sb_onlineexam WHERE techreviewid="+ contechReviewId +" ";




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
                if(Integer.parseInt(parts1[1])!=0){
                 skillsQuestionsMap.put(parts1[0],StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts1[0]))));   
                }
            }
            if (!"".equals(option2)) {
                String[] parts2 = option2.split("-");
                if(Integer.parseInt(parts2[1])!=0){
                skillsQuestionsMap.put(parts2[0],StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts2[0]))));
                }
            }
            if (!"".equals(option3)) {
                String[] parts3 = option3.split("-");
                if(Integer.parseInt(parts3[1])!=0){
                skillsQuestionsMap.put(parts3[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts3[0]))));
                }
            }
            if (!"".equals(option4)) {
                String[] parts4 = option4.split("-");
                if(Integer.parseInt(parts4[1])!=0){
                skillsQuestionsMap.put(parts4[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts4[0]))));
                }
            }
            if (!"".equals(option5)) {
                String[] parts5 = option5.split("-");
                if(Integer.parseInt(parts5[1])!=0){
                skillsQuestionsMap.put(parts5[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts5[0]))));
                }
            }
            if (!"".equals(option6)) {
                String[] parts6 = option6.split("-");
                if(Integer.parseInt(parts6[1])!=0){
                skillsQuestionsMap.put(parts6[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts6[0]))));
                }
            }
            if (!"".equals(option7)) {
                String[] parts7 = option7.split("-");
                if(Integer.parseInt(parts7[1])!=0){
                skillsQuestionsMap.put(parts7[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts7[0]))));
                }
            }
            if (!"".equals(option8)) {
                String[] parts8 = option8.split("-");
                if(Integer.parseInt(parts8[1])!=0){
                skillsQuestionsMap.put(parts8[0], StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts8[0]))));
                }
            }
            if (!"".equals(option9)) {
                String[] parts9 = option9.split("-");
                if(Integer.parseInt(parts9[1])!=0){
                skillsQuestionsMap.put(parts9[0],StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts9[0]))));
                }
            }
            if (!"".equals(option10)) {
                String[] parts10 = option10.split("-");
                if(Integer.parseInt(parts10[1])!=0){
                skillsQuestionsMap.put(parts10[0],StringUtils.chop(getReqSkillsSet(Integer.parseInt(parts10[0]))));
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
        System.out.println("skillsQuestionsMap-->" + skillsQuestionsMap);
        return skillsQuestionsMap;

    }
    
    public String getExamStatus(int conTechReviewId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String examStatus = "";
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT examstatus FROM sb_onlineexam WHERE techreviewid=" + conTechReviewId +"";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                examStatus = resultSet.getString("examstatus");
//                attempt=1;
            }
            System.out.println("queryString==>In getExamStatus" + queryString);
        } catch (SQLException ex) {
            //System.out.println("isAttempted method-->" + ex.getMessage());
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
        return examStatus;

    }
     /**
     * *****************************************************************************
     * Date : october 6, 2015, 5:40 PM IST
     * Author:jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : To find the question attempted or not
     * *****************************************************************************
     */
    public int isAttempted(int qusetionId, int examKey) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int attempt = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT COUNT(id) AS total FROM sb_onlineexamsummery WHERE questionid=" + qusetionId + " AND examkey=" + examKey;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                attempt = resultSet.getInt("total");
//                attempt=1;
            }
            System.out.println("queryString==>In isAttempted" + queryString);
        } catch (SQLException ex) {
            //System.out.println("isAttempted method-->" + ex.getMessage());
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
        return attempt;

    }
     /**
     * *****************************************************************************
     * Date : october 1, 2015, 3:40 PM IST
     * Author:jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : getting the exam expired or not
     * *****************************************************************************
     */
    public boolean isExamExpired(String token) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        boolean isExpired = false;
//        int i = 0;
//        //System.err.println(days+"Diff in Dyas...");
        try {
            // queryString = "SELECT eid,reqid,consultantid,acctoken,validationkey,createdby,createddate,comments,totalquestions,qualifiedmarks,examstatus,severity FROM sb_onlineexam WHERE acctoken='"+onlineExamAction.getToken()+"'";

            queryString = " SELECT HOUR(TIMEDIFF(  NOW(),createddate)) AS DIFF FROM sb_onlineexam WHERE acctoken='" + token + "'";

            System.out.println("queryString--->isExamExpired-->" + queryString);
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
        return isExpired;
    }
    public int getNoOfRightAns(int skillId,int examId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String resultString = "";
        int result=0;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT COUNT(IF(ansstatus='R',1, NULL)) AS rightans FROM sb_onlineexamsummery WHERE skillid="+skillId+" AND examid="+examId+"";
        System.out.println("getNoOfRightAns queryString-->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = resultSet.getInt("rightans");
            }
            //resultString = resultString + ',';
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
        System.out.println("result--->"+result);
        return result;

    }
    public int doMailExtensionVerify(String mailExt) throws ServiceLocatorException {

        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
            queryString = "SELECT count(*) as id FROM siteaccess_mail_ext WHERE email_ext LIKE '"+mailExt+"'";
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
        return count;
    }
    
  public PdfPTable getITextPDFTable(String gridData, PdfPTable table) throws ServiceLocatorException {


        if (!"".equals(gridData)) {
            
            // int Count = stk.countTokens();
            //  System.out.println("Total Count-->" + Count);
            String[] s = gridData.split("\\^");
            for (int i = 0; i < s.length; i++) {
                System.out.println("stk.split;-->" + s[i]);
                String ss = s[i];
                String[] inner = ss.split("\\|");
                //  System.out.println("inner--->"+inner);
                for (int j = 0; j < inner.length; j++) {
                    System.out.println("inner.split;-->" + inner[j]);
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

        return table;

    }   
   /**
     * *****************************************************************************
     * Date : November 11, 2015, 3:00 PM IST
     * Author:jagan<jchukkala@miraclesoft.com>
     *
     * ForUse : getting the Recruitment Team For an Account 
     * *****************************************************************************
     */
  public Map getEmpConsultantTeamMap(int orgId) throws ServiceLocatorException {

        Map empTeam = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //String queryString = "SELECT u.usr_id,CONCAT_WS(' ',u.first_name,u.middle_name,u.last_name) AS NAME FROM users u LEFT OUTER JOIN usr_roles ur ON u.usr_id=ur.usr_id WHERE ur.role_id=11  AND u.org_id=" + orgId;
      
         String queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAME,u.usr_id  FROM users u "
                + " LEFT OUTER JOIN usr_roles ur ON u.usr_id=ur.usr_id "
                + " LEFT OUTER JOIN usr_grouping ug ON ug.usr_id=u.usr_id "
                + " WHERE   ur.role_id=11  AND u.org_id="+orgId+" AND cat_type=1";
        
        
        System.out.println("employee-->"+queryString);
        
        
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
        //System.out.println("empteam"+empTeam);
        return empTeam;

    }
  /*by  divya gandreti*/
  public String getActionDescription(String actionName) throws ServiceLocatorException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        String resultString = "";
            queryString = "SELECT description FROM ac_action WHERE action_name = SUBSTRING_INDEX(SUBSTRING_INDEX('"+actionName+"','/',-1),'.',1)";
         System.out.println(">>>>>>>>>>>>Action Description>>>>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString +=  resultSet.getString("description") ;
            }
        } catch (SQLException ex) {
            System.out.println("getAllRoles method-->" + ex.getMessage());
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
        return resultString;
    }
/* by divya gandreti*/
  public List getActionNamesList(int orgId,int roleId,String accType) {
        ArrayList actionNames = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT DISTINCT action_name FROM home_redirect_action WHERE STATUS ='Active' AND type_of_user LIKE '"+accType+"'";// AND primaryrole="+roleId;//+" AND (org_id="+orgId+" OR org_id=0)";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actionNames.add(resultSet.getString("action_name"));
            }
            System.out.println("----------------------"+queryString);
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

        System.out.println(actionNames);
        return actionNames;
    }

}
