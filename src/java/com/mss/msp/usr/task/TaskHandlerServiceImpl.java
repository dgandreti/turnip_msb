/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.task;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public class TaskHandlerServiceImpl implements TaskHandlerService {

    private Connection connection;

    /**
     * *************************************
     *
     * @getEmployeeTasksDetails() method is used to get task details and
     * displays in TaskSearch Grid
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     **************************************
     */
    public List getEmployeeTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        DateUtility dateUtility = new DateUtility();
        ArrayList<TasksVTO> tasklist = new ArrayList<TasksVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        String startDate = "";
        String endDate = "";

        //System.err.println(days+"Diff in Dyas...");
        try {
            queryString = "SELECT t.priority,t.task_id,t.task_name,t.task_created_date,t.task_comments,lts.task_status_name,email1,"
                    + "CONCAT(u.first_name,'.',u.last_name) AS created "
                    + "FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) "
                    + "LEFT OUTER JOIN lk_task_status lts ON(lts.id=t.task_status) WHERE 1=1 "
                    + "AND(t.task_created_by=" + taskHandlerAction.getUserSessionId() + " OR t.pri_assigned_to=" + taskHandlerAction.getUserSessionId() + " OR t.sec_assigned_to=" + taskHandlerAction.getUserSessionId() + ") ";

            if (!"".equals(taskHandlerAction.getDocdatepickerfrom()) && !"".equals(taskHandlerAction.getDocdatepicker())) {
                startDate = dateUtility.convertStringToMySQLDateInDash(taskHandlerAction.getDocdatepickerfrom());
                endDate = dateUtility.convertStringToMySQLDateInDash(taskHandlerAction.getDocdatepicker());
                queryString = queryString + " and  t.task_created_date between '" + startDate + "' and '" + endDate + "' ";
            }
            if (!"".equals(taskHandlerAction.getTask_id())) {
                queryString = queryString + " and t.task_id = " + taskHandlerAction.getTask_id() + " ";
            }
            if (!"".equals(taskHandlerAction.getTask_name())) {
                queryString = queryString + " and t.task_name like '%" + taskHandlerAction.getTask_name() + "%'  ";
            }
            if (!"-1".equalsIgnoreCase(taskHandlerAction.getTask_status())) {
                queryString = queryString + " and t.task_status = '" + taskHandlerAction.getTask_status() + "'  ";
            }


            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TasksVTO tasksVTO = new TasksVTO();
                tasksVTO.setTask_id(resultSet.getString("task_id"));
                tasksVTO.setTask_name(resultSet.getString("task_name"));
                tasksVTO.setTask_comments(resultSet.getString("task_comments"));
                tasksVTO.setTask_created_date(dateUtility.convertToviewFormatInDash(resultSet.getString("task_created_date")));
                tasksVTO.setTask_created_by(resultSet.getString("created"));
                tasksVTO.setTask_status(resultSet.getString("task_status_name"));
                if (resultSet.getString("priority").equalsIgnoreCase("M")) {
                    tasksVTO.setTaskPriority("Medium");
                } else if (resultSet.getString("priority").equalsIgnoreCase("H")) {
                    tasksVTO.setTaskPriority("High");
                } else {
                    tasksVTO.setTaskPriority("Low");
                }
                tasklist.add(tasksVTO);
            }

            System.out.println("queryString-->" + tasklist);

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
        return tasklist;
    }

    /**
     * *************************************
     *
     * @getLoggedInEmpTasksDetails() method is used to get loggedIn user task
     * details and displays in TaskSearch Grid
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     **************************************
     */
    public List getLoggedInEmpTasksDetails( TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        DateUtility dateUtility = new DateUtility();
        ArrayList<TasksVTO> tasklist = new ArrayList<TasksVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
         String startDate = "";
        String endDate = "";
        //System.err.println(days+"Diff in Dyas...");
        try {
            endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate());
             startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate());
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT t.priority,t.task_id,t.task_name,t.task_created_date,t.task_comments,lts.task_status_name,"
                    + "CONCAT(u.first_name,'.',u.last_name) AS created "
                    + "FROM task_list t LEFT OUTER JOIN users u ON(t.task_created_by=u.usr_id) "
                    + "LEFT OUTER JOIN lk_task_status lts ON(lts.id=t.task_status) "
                    + "WHERE 1=1 AND t.task_created_by=" + taskHandlerAction.getUserSessionId() + " "
                    + "AND  t.task_created_date BETWEEN '" + startDate + "' AND '" + endDate + "' "
                    + "OR t.pri_assigned_to=" + taskHandlerAction.getUserSessionId() + " "
                    + "OR t.sec_assigned_to=" + taskHandlerAction.getUserSessionId();

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TasksVTO tasksVTO = new TasksVTO();
                tasksVTO.setTask_id(resultSet.getString("task_id"));
                tasksVTO.setTask_name(resultSet.getString("task_name"));
                tasksVTO.setTask_comments(resultSet.getString("task_comments"));
                tasksVTO.setTask_created_date(dateUtility.convertToviewFormatInDash(resultSet.getString("task_created_date")));
                tasksVTO.setTask_created_by(resultSet.getString("created"));
                tasksVTO.setTask_status(resultSet.getString("task_status_name"));
                if (resultSet.getString("priority").equalsIgnoreCase("M")) {
                    tasksVTO.setTaskPriority("Medium");
                } else if (resultSet.getString("priority").equalsIgnoreCase("H")) {
                    tasksVTO.setTaskPriority("High");
                } else {
                    tasksVTO.setTaskPriority("Low");
                }
                tasklist.add(tasksVTO);
            }

            System.out.println("queryString-->" + tasklist);

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
        return tasklist;
    }

    /**
     * *************************************
     *
     * @addTaskDetals() method is used to add new task details in task_list
     * table
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     **************************************
     */
    public int addTaskDetals(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPL EXECUTED %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(taskHandlerAction.getTaskAttachmentFileName() + "=================>" + taskHandlerAction.getFilePath());
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();

        //System.err.println(days+"Diff in Dyas...");
        String startDate = "";
        String endDate = "";
        try {
            //startDate = dateUtility.getInstance().convertStringToMySQLDate(taskHandlerAction.getStartDate());
            //endDate = dateUtility.getInstance().convertStringToMySQLDate(taskHandlerAction.getEndDate());
            connection = ConnectionProvider.getInstance().getConnection();
//            queryString = "insert into task_list"
//                    + "(task_related_to,task_type,task_status,task_start_date,task_end_date,task_name,task_comments,task_created_by) "
//                    + "values('"+taskHandlerAction.getTaskRelatedTo()+"','"+taskHandlerAction.getTaskType()+"','"
//                    +taskHandlerAction.getTask_status()+"','"+taskHandlerAction.getStartDate()+"','"
//                    +taskHandlerAction.getEndDate()+"','"+taskHandlerAction.getTask_name()+"','"
//                    +taskHandlerAction.getTask_comments()+"',"+userId+")";                         
//
//            System.out.println("queryString-->" + queryString);
//            connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.createStatement();
//            addResult=statement.executeUpdate(queryString);
//            System.out.println("****************** in impl result after adding:::::::::"+addResult);
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL addTaskProc(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, taskHandlerAction.getTaskRelatedTo());
            System.out.println("after 1st valueeeeeeeeeeeeeee");
            callableStatement.setInt(2, taskHandlerAction.getTaskType());
            callableStatement.setInt(3, taskHandlerAction.getTaskStatus());
            callableStatement.setString(4, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate()));
            callableStatement.setString(5, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate()));
            callableStatement.setString(6, taskHandlerAction.getTaskName());
            callableStatement.setString(7, taskHandlerAction.getTask_comments());
            callableStatement.setString(8, taskHandlerAction.getTaskAttachmentFileName());
            callableStatement.setString(9, taskHandlerAction.getFilePath());
            callableStatement.setInt(10, taskHandlerAction.getUserSessionId());

            callableStatement.setString(11, taskHandlerAction.getTaskPriority());
            callableStatement.setInt(12, taskHandlerAction.getPrimaryAssign());
            callableStatement.setInt(13, taskHandlerAction.getSecondaryId());
            callableStatement.registerOutParameter(14, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(14);
            if (addResult > 0) {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return addResult;
    }

    /**
     * *************************************
     *
     * @getLoggedInTeamTasksDetails() method is used to get task details in
     * task_list table for team percepective
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     **************************************
     */
    public List getLoggedInTeamTasksDetails( TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {


        List tasklist = new ArrayList();
         DateUtility dateUtility = new DateUtility();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map newMap = new HashMap();
        int i = 0;
        String startDate = "";
        String endDate = "";
        //System.err.println(days+"Diff in Dyas...");
        try {
              endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate());
                startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate());
            Map map = com.mss.msp.util.DataSourceDataProvider.getInstance().getMyTeamMembers(taskHandlerAction.getUserSessionId());
            String key, listId = "";
            System.out.println(">>>>after dsdp method>>" + map.size());
            System.out.println(">>>>before adding to tasklist>>" + tasklist.size());
            tasklist.add(newMap);
            System.out.println(">>>>after adding map to tasklist>>" + tasklist.size());

            int mapsize = map.size();
            if (map.size() > 0) {
                Iterator tempIterator = map.entrySet().iterator();
                while (tempIterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    key = entry.getKey().toString();
                    mapsize--;
                    if (mapsize != 0) {
                        listId += "," + key;
                    } else {
                        listId +=","+ key;
                    }
                    entry = null;
                }

            }
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT DATE_FORMAT(t.task_created_date, '%m-%d-%Y') AS DATE,t.priority,t.task_id,t.task_name,t.task_comments,t.task_created_date,"
                    + "CONCAT(up.first_name,'.',up.last_name) AS pri_assigned_to,"
                    + "CONCAT(us.first_name,'.',us.last_name) AS sec_assigned_to,lts.task_status_name,prj.proj_name "
                    + "FROM task_list t "
                    + "LEFT OUTER JOIN lk_task_status lts ON(lts.id=t.task_status)"
                    + "LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) "
                    + "LEFT OUTER JOIN users up  ON(t.pri_assigned_to=up.usr_id) "
                    + "LEFT OUTER JOIN users us  ON(t.sec_assigned_to=us.usr_id) "
                    + "LEFT OUTER JOIN acc_projects prj ON(prj.project_id=t.task_prj_related_to) "
                    + "WHERE 1=1 AND task_created_by IN(" + taskHandlerAction.getUserSessionId() + "" + listId + ") "
                    + "AND  t.task_created_date BETWEEN '" + startDate + "' AND '" + endDate + "' "
                    + "ORDER BY t.task_name LIMIT 100";


            System.out.println("queryString-default team tasks->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TasksVTO tasksVTO = new TasksVTO();
                tasksVTO.setTask_id(resultSet.getString("task_id"));
                tasksVTO.setTask_name(resultSet.getString("task_name"));
                tasksVTO.setTask_comments(resultSet.getString("task_comments"));
                tasksVTO.setTask_created_date(resultSet.getString("task_created_date"));
                tasksVTO.setTask_status(resultSet.getString("task_status_name"));
                tasksVTO.setPri_assigned_to(resultSet.getString("pri_assigned_to"));
                tasksVTO.setSec_assigned_to(resultSet.getString("sec_assigned_to"));
                tasksVTO.setRelatedProject(resultSet.getString("proj_name"));
                tasksVTO.setCreatedDate(resultSet.getString("DATE"));

                if (resultSet.getString("priority").equalsIgnoreCase("M")) {
                    tasksVTO.setTaskPriority("Medium");
                } else if (resultSet.getString("priority").equalsIgnoreCase("H")) {
                    tasksVTO.setTaskPriority("High");
                } else {
                    tasksVTO.setTaskPriority("Low");
                }

                tasklist.add(tasksVTO);
            }

            System.out.println("queryString-->" + tasklist);

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
        return tasklist;
    }

    /**
     * *************************************
     *
     * @getTeamTasksDetails() method is used to get task details in task_list
     * based on search criteria
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     **************************************
     */
    public List getTeamTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {


        ArrayList tasklist = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        //System.err.println(days+"Diff in Dyas...");

        try {

            Map map = com.mss.msp.util.DataSourceDataProvider.getInstance().getMyTeamMembers(taskHandlerAction.getUserSessionId());
            String key, listId = "";
            tasklist.add(map);
            int mapsize = map.size();
            if (map.size() > 0) {
                Iterator tempIterator = map.entrySet().iterator();
                while (tempIterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    key = entry.getKey().toString();
                    mapsize--;
                    if (mapsize != 0) {
                        listId += "," + key;
                    } else {
                        listId += ","+key;
                    }
                    entry = null;
                }

            }
            queryString = "SELECT DATE_FORMAT(t.task_created_date, '%m-%d-%Y') AS DATE,t.priority,t.task_id,t.task_name,t.task_comments,t.task_created_date,"
                    + "CONCAT(up.first_name,'.',up.last_name) AS pri_assigned_to,"
                    + "CONCAT(us.first_name,'.',us.last_name) AS sec_assigned_to,ltr.task_relatedto_name,lts.task_status_name,prj.proj_name "
                    + "FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) "
                    + "LEFT OUTER JOIN users up  ON(t.pri_assigned_to=up.usr_id) "
                    + "LEFT OUTER JOIN users us  ON(t.sec_assigned_to=us.usr_id) "
                    + "LEFT OUTER JOIN lk_task_status lts ON(lts.id=t.task_status) "
                    + "LEFT OUTER JOIN acc_projects prj ON(prj.project_id=t.task_prj_related_to) "
                    + "LEFT OUTER JOIN lk_taskrelated_to ltr ON(ltr.task_relatedto_id=t.task_related_to) WHERE 1=1";

            if (!"".equals(taskHandlerAction.getDocdatepickerfrom()) && !"".equals(taskHandlerAction.getDocdatepicker())) {
                endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getDocdatepicker());
                startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getDocdatepickerfrom());
                queryString = queryString + " and  t.task_created_date between '" + startDate + "' and '" + endDate + "' ";
            }

            if (!"".equals(taskHandlerAction.getTask_name())) {
                queryString = queryString + " and t.task_name like '%" + taskHandlerAction.getTask_name() + "%'  ";
            }
            if (!"".equals(taskHandlerAction.getTask_id())) {
                queryString = queryString + " and t.task_id =" + taskHandlerAction.getTask_id() + " ";
            }
            if (!"-1".equalsIgnoreCase(taskHandlerAction.getTask_status())) {
                queryString = queryString + " and t.task_status = '" + taskHandlerAction.getTask_status() + "'  ";
            }
            if (!"-1".equalsIgnoreCase(taskHandlerAction.getTeamMember())) {
                queryString = queryString + "AND(t.task_created_by=" + taskHandlerAction.getTeamMember() + " OR t.pri_assigned_to=" + taskHandlerAction.getTeamMember() + " OR t.sec_assigned_to=" + taskHandlerAction.getTeamMember() + ") ";
            }
            queryString = queryString + "AND task_created_by IN(" + taskHandlerAction.getUserSessionId() + "" + listId + ")";

            System.out.println("queryString-in team task search->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TasksVTO tasksVTO = new TasksVTO();
                tasksVTO.setTask_id(resultSet.getString("task_id"));
                tasksVTO.setTask_name(resultSet.getString("task_name"));
                tasksVTO.setTask_comments(resultSet.getString("task_comments"));
                tasksVTO.setTask_created_date(resultSet.getString("task_created_date"));
                tasksVTO.setTask_status(resultSet.getString("task_status_name"));
                tasksVTO.setPri_assigned_to(resultSet.getString("pri_assigned_to"));
                tasksVTO.setSec_assigned_to(resultSet.getString("sec_assigned_to"));
                tasksVTO.setTask_relatedto_name(resultSet.getString("task_relatedto_name"));
                tasksVTO.setRelatedProject(resultSet.getString("proj_name"));
                tasksVTO.setCreatedDate(resultSet.getString("DATE"));

                if (resultSet.getString("priority").equalsIgnoreCase("M")) {
                    tasksVTO.setTaskPriority("Medium");
                } else if (resultSet.getString("priority").equalsIgnoreCase("H")) {
                    tasksVTO.setTaskPriority("High");
                } else {
                    tasksVTO.setTaskPriority("Low");
                }
                tasklist.add(tasksVTO);
            }

            System.out.println("queryString-->" + tasklist);

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
        return tasklist;
    }

    /**
     * *************************************
     *
     * @getEditTaskDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/21/2015
     *
     *
     **************************************
     *
     */
    public TasksVTO getEditTaskDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        TasksVTO tasksVTO = new TasksVTO();
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        Map map = new HashMap();
        Map map1 = new HashMap();
        try {
            queryString = "SELECT task_prj_related_to,task_id,task_related_to,task_type,task_status,priority,task_start_date,task_end_date,task_name,task_comments,pri_assigned_to,sec_assigned_to,task_created_by FROM task_list WHERE task_id=?";
            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            System.out.println("hello praveen...." + taskHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            DateUtility dateUtility = new DateUtility();
            while (resultSet.next()) {
                tasksVTO.setTask_prj_related_to(resultSet.getInt("task_prj_related_to"));
                System.out.println("pri_assigned_to-------------------------------------->" + resultSet.getInt("task_prj_related_to"));
                tasksVTO.setTask_id(resultSet.getString("task_id"));
                tasksVTO.setTask_related_to(resultSet.getString("task_related_to"));
                tasksVTO.setTask_status(resultSet.getString("task_status"));
                tasksVTO.setTask_type(resultSet.getString("task_type"));
                tasksVTO.setTask_priority(resultSet.getString("priority"));
                tasksVTO.setStart_date(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("task_start_date")));
                tasksVTO.setEnd_date(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("task_end_date")));
                tasksVTO.setTask_title(resultSet.getString("task_name"));
                tasksVTO.setTask_comments(resultSet.getString("task_comments"));
                tasksVTO.setPri_assigned_to(resultSet.getString("pri_assigned_to"));
                System.out.println("pri_assigned_to-------------------------------------->" + resultSet.getString("pri_assigned_to"));
                tasksVTO.setSec_reportsId(resultSet.getInt("sec_assigned_to"));
                tasksVTO.setSec_assigned_to(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("sec_assigned_to")));
                tasksVTO.setTask_created_by(resultSet.getString("task_created_by"));

            }
            tasksVTO.setTypeMaps(com.mss.msp.util.DataSourceDataProvider.getInstance().getTaskTypeById(taskHandlerAction));
            if (tasksVTO.getTask_related_to().equals("2")) {
                //tasksVTO.setTeamMembers(com.mss.msp.util.DataSourceDataProvider.getInstance().getCSRTeam(taskHandlerAction));
                taskHandlerAction.setTeamMemberNames(com.mss.msp.util.DataSourceDataProvider.getInstance().getCSRTeam(taskHandlerAction));
            } else {
                taskHandlerAction.setTeamMemberNames(com.mss.msp.util.DataSourceDataProvider.getInstance().getMyTeamMembers(taskHandlerAction.getUserSessionId()));
            }
            //tasksVTO.setPrimaryMaps(com.mss.msp.util.DataSourceDataProvider.getInstance().getPrimaryAssignTo(taskHandlerAction.getTaskid()));
            taskHandlerAction.setTasksRelatedToList(com.mss.msp.util.DataSourceDataProvider.getInstance().getTaskrelatedToMap());

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
        return tasksVTO;

    }

    /**
     * *************************************
     *
     * @updateTaskDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:r amakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/21/2015
     *
     *
     **************************************
     *
     */
    public int updateTaskDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        //System.err.println(days+"Diff in Dyas...");
        int i = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate());
        endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate());
        System.out.println("startDate=====================>" + startDate);
        System.out.println("endDate=====================>" + endDate);

        try {
            System.out.println("=====================>in impl try");
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "update task_list set task_related_to=?,task_status=?,task_name=?,task_prj_related_to=?,task_comments=?,priority=?,pri_assigned_to=?,sec_assigned_to=?,task_start_date=?,task_end_date=?,task_modified_by=?,task_modified_date=? where task_id=?";
            System.out.println("=====================>Priority::::" + taskHandlerAction.getPriority());
            System.out.println("queryString-->" + queryString);
            System.out.println("primary string---->" + taskHandlerAction.getPri_assign_to() + ",,,,,,,,,,,,," + taskHandlerAction.getSec_assign_to());
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskRelatedTo());
            preparedStatement.setInt(2, taskHandlerAction.getTaskStatus());
            preparedStatement.setString(3, taskHandlerAction.getTaskName());
            preparedStatement.setInt(4, taskHandlerAction.getTaskType());
            preparedStatement.setString(5, taskHandlerAction.getTask_comments());
            preparedStatement.setString(6, taskHandlerAction.getPriority());
            preparedStatement.setString(7, taskHandlerAction.getPri_assign_to());
            preparedStatement.setInt(8, taskHandlerAction.getSec_assign_to());
            preparedStatement.setString(9, startDate);
            preparedStatement.setString(10, endDate);
            preparedStatement.setInt(11, taskHandlerAction.getUserSessionId());
            preparedStatement.setTimestamp(12, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(13, taskHandlerAction.getTaskid());
            i = preparedStatement.executeUpdate();
            System.out.println("here we completed execution.....");
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
        return i;
    }

    /**
     * *************************************
     *
     * @addAttachmentDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/21/2015
     *
     *
     **************************************
     *
     */
    public int addAttachmentDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPL EXECUTED %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(taskHandlerAction.getTaskAttachmentFileName() + "=================>" + taskHandlerAction.getFilePath());
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        try {
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");

            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("values in impl are:::::::::::" + taskHandlerAction.getTaskid() + " " + taskHandlerAction.getTaskAttachmentFileName() + " " + taskHandlerAction.getFilePath());
            String fpath = taskHandlerAction.getFilePath();
            StringTokenizer st = new StringTokenizer(fpath);
            StringTokenizer st2 = new StringTokenizer(fpath, "\\");
            String finalPath = "";
            while (st2.hasMoreElements()) {
                //System.out.println(".............>>>>>>>>>>>"+st2.nextElement());
                finalPath = finalPath + st2.nextElement() + "\\" + "\\";
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + finalPath);
            taskHandlerAction.setFilePath(finalPath);

            queryString = "INSERT INTO task_attachments(task_id,attachment_name,attachment_path,STATUS,created_by) VALUES(" + taskHandlerAction.getTaskid() + ",'" + taskHandlerAction.getTaskAttachmentFileName() + "','" + taskHandlerAction.getFilePath() + "" + taskHandlerAction.getTaskAttachmentFileName() + "','Active'," + taskHandlerAction.getUserSessionId() + ")";
//            System.out.println("values in impl are:::::::::::"+taskHandlerAction.getTaskid()+" "+taskHandlerAction.getTaskAttachmentFileName()+" "+taskHandlerAction.getFilePath());
//            callableStatement = connection.prepareCall("{CALL addAttachment(?,?,?,?.?)}");
//            callableStatement.setInt(1, taskHandlerAction.getTaskid());
//            System.out.println("after 1st valueeeeeeeeeeeeeee");
//            callableStatement.setString(2, taskHandlerAction.getTaskAttachmentFileName());
//            callableStatement.setString(3, taskHandlerAction.getFilePath());
//            callableStatement.setInt(4, taskHandlerAction.getUserSessionId());
//            callableStatement.registerOutParameter(5, Types.INTEGER);
//            isExceute = callableStatement.execute();
//            addResult = callableStatement.getInt(5);
            System.out.println("queryString-->" + queryString);

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            addResult = statement.executeUpdate(queryString);
            System.out.println("****************** in impl result after adding:::::::::" + addResult);
            if (addResult > 0) {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return addResult;
    }

    /**
     * *************************************
     *
     * @getNotesDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/22/2015
     *
     *
     **************************************
     *
     */
    public String getNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultMsg = "";
        //System.err.println(days+"Diff in Dyas...");
        int i = 0;
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT n.id, n.reference_id,n.notes_title,n.notes_comments,n.created_date ,concat(u.first_name,'.',u.last_name) as Names FROM notes n JOIN users u ON(n.created_by=u.usr_id)  WHERE n.reference_type='T' AND n.reference_id=?";

            System.out.println("queryString-->" + queryString + taskHandlerAction.getTaskid());
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultMsg += resultSet.getString("id") + "|" + resultSet.getString("reference_id") + "|" + resultSet.getString("notes_title") + "|" + resultSet.getString("notes_comments") + "|" + dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("created_date")) + "^";
            }
            System.out.println(resultMsg);
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
        return resultMsg;
    }

    /**
     * *************************************
     *
     * @getNotesDetailsOverlay() method is used to get task details and appends
     * on TaskEdit screen
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/22/2015
     *
     *
     **************************************
     *
     */
    public String getNotesDetailsOverlay(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultMsg = "";
        //System.err.println(days+"Diff in Dyas...");
        int i = 0;
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT n.id, n.reference_id,n.notes_title,n.notes_comments,n.created_date ,concat(u.first_name,'.',u.last_name) as Names FROM notes n JOIN users u ON(n.created_by=u.usr_id)  WHERE n.reference_type='T' AND n.reference_id=? and n.id=?";

            System.out.println("queryString-->" + queryString + taskHandlerAction.getTaskid());
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            preparedStatement.setInt(2, taskHandlerAction.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultMsg += resultSet.getString("id") + "|" + resultSet.getString("reference_id") + "|" + resultSet.getString("notes_title") + "|" + resultSet.getString("notes_comments");
            }
            System.out.println(resultMsg);
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
        return resultMsg;
    }

    /**
     * *************************************
     *
     * @UpdateNotesDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/23/2015
     *
     *
     **************************************
     *
     */
    public int UpdateNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultInt = 0;
        //System.err.println(days+"Diff in Dyas...");
        int i = 0;
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "UPDATE notes SET notes_title=?,notes_comments=?,modified_by=?,modified_date=? WHERE id=? AND reference_id=?";
            System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, taskHandlerAction.getNote_name());
            System.out.println("note ...name" + taskHandlerAction.getNote_name());
            preparedStatement.setString(2, taskHandlerAction.getNote_comments());
            preparedStatement.setInt(3, taskHandlerAction.getUserSessionId());
            preparedStatement.setTimestamp(4, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(5, taskHandlerAction.getId());
            preparedStatement.setInt(6, taskHandlerAction.getTaskid());
            resultInt = preparedStatement.executeUpdate();
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
        return resultInt;
    }

    /**
     * *************************************
     *
     * @DoInsertNotesDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/23/2015
     *
     *
     **************************************
     *
     */
    public int DoInsertNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultInt = 0;
        //System.err.println(days+"Diff in Dyas...");
        int i = 0;
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "insert into notes(reference_type,reference_id,notes_title,notes_comments,created_by) values(?,?,?,?,?)";
            System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, "T");
            System.out.println("note ...comments........" + taskHandlerAction.getNote_comments());
            preparedStatement.setInt(2, taskHandlerAction.getTaskid());

            preparedStatement.setString(3, taskHandlerAction.getNote_name());
            preparedStatement.setString(4, taskHandlerAction.getNote_comments());

            preparedStatement.setInt(5, taskHandlerAction.getUserSessionId());
            resultInt = preparedStatement.executeUpdate();
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
        return resultInt;
    }

    /**
     * *************************************
     *
     * @getNotesDetailsBySearch() getting search notes details
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/23/2015
     *
     *
     **************************************
     *
     */
    public String getNotesDetailsBySearch(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            System.out.println("this is notes id" + taskHandlerAction.getNotes_id());
            System.out.println("this is notes name" + taskHandlerAction.getNotesName());
            //queryString = "select id,reference_id,notes_title,notes_comments,created_date from notes where reference_type='T' ";
            queryString = "select id,reference_id,notes_title,notes_comments,created_date from notes where reference_type='T' AND reference_id="+taskHandlerAction.getTaskid();
//            if (taskHandlerAction.getNotes_id() > 0) {
//                queryString += " and reference_id=" + taskHandlerAction.getNotes_id();
//            }
            if (!taskHandlerAction.getNotesName().equals("")) {
                queryString += " and notes_title LIKE '%" + taskHandlerAction.getNotesName() + "%'";
            }
            System.out.println(queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("id") + "|" + resultSet.getString("reference_id") + "|" + resultSet.getString("notes_title") + "|" + resultSet.getString("notes_comments") + "|" + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("created_date")) + "^";
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
     * @getNotesDetailsOverlay() method is used to get task details and appends
     * on TaskEdit screen
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/22/2015
     *
     *
     **************************************
     *
     */
    public String getCommentsOnOverlay(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultMsg = "";
        //System.err.println(days+"Diff in Dyas...");
        int i = 0;
        try {
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT task_comments FROM task_list WHERE task_id=?";

            System.out.println("queryString-->" + queryString + taskHandlerAction.getTaskid());
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultMsg = resultSet.getString("task_comments");
            }
            System.out.println(resultMsg);
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
        return resultMsg;
    }
}
