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

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : getEmployeeTasksDetails() method is used to get task details and
     * displays in TaskSearch Grid
     *
     *
     * *****************************************************************************
     */
    public List getEmployeeTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DateUtility dateUtility = new DateUtility();
        ArrayList<TasksVTO> tasklist = new ArrayList<TasksVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        String startDate = "";
        String endDate = "";
        System.out.println("********************TaskHandlerServiceImpl :: getEmployeeTasksDetails Method Start*********************");
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
            if (!"".equals(taskHandlerAction.getTask_name())) {
                queryString = queryString + " and t.task_name like '%" + taskHandlerAction.getTask_name() + "%'  ";
            }
            if (!"-1".equalsIgnoreCase(taskHandlerAction.getTask_status())) {
                queryString = queryString + " and t.task_status = '" + taskHandlerAction.getTask_status() + "'  ";
            }
            queryString = queryString + " ORDER BY t.task_id DESC ";
            System.out.println("getEmployeeTasksDetails :: query string ------>" + queryString);

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
        System.out.println("********************TaskHandlerServiceImpl :: getEmployeeTasksDetails Method End*********************");
        return tasklist;
    }

    /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : getLoggedInEmpTasksDetails() method is used to get loggedIn user
     * task details and displays in TaskSearch Grid
     *
     *
     * *****************************************************************************
     */
    public List getLoggedInEmpTasksDetails(TaskHandlerAction taskHandlerAction, int usrPriRole, String usrType) throws ServiceLocatorException {

        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DateUtility dateUtility = new DateUtility();
        ArrayList<TasksVTO> tasklist = new ArrayList<TasksVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        String startDate = "";
        String endDate = "";
        System.out.println("********************TaskHandlerServiceImpl :: getLoggedInEmpTasksDetails Method Start*********************");
        try {
            endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate());
            startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate());
            queryString = "SELECT t.priority,t.task_id,t.task_name,t.task_created_date,t.task_comments,lts.task_status_name,"
                    + "CONCAT(u.first_name,'.',u.last_name) AS created "
                    + "FROM task_list t LEFT OUTER JOIN users u ON(t.task_created_by=u.usr_id) "
                    + "LEFT OUTER JOIN lk_task_status lts ON(lts.id=t.task_status) "
                    + "WHERE 1=1  AND  t.task_created_date BETWEEN '" + startDate + "' AND '" + endDate + "' "
                    + " AND (t.pri_assigned_to=" + taskHandlerAction.getUserSessionId() + " ";
            if (("VC".equals(usrType) && usrPriRole == 8) || ("AC".equals(usrType))) {
                queryString = queryString + " OR t.task_created_by=" + taskHandlerAction.getUserSessionId();
            }
            queryString = queryString + " OR t.sec_assigned_to=" + taskHandlerAction.getUserSessionId() + ")"
                    + " ORDER BY t.task_id DESC";
            System.out.println("getLoggedInEmpTasksDetails :: query string ------>" + queryString);
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
        System.out.println("********************TaskHandlerServiceImpl :: getLoggedInEmpTasksDetails Method End*********************");
        return tasklist;
    }

    /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : addTaskDetals() method is used to add new task details in
     * task_list
     *
     *
     * *****************************************************************************
     */
    public int addTaskDetals(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        CallableStatement callableStatement = null;

        StringBuffer stringBuffer = new StringBuffer();
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        System.out.println("********************TaskHandlerServiceImpl :: addTaskDetals Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL addTaskProc(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("addTaskDetals :: procedure name : addTaskProc");
            callableStatement.setInt(1, taskHandlerAction.getTaskRelatedTo());
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
            } else {
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************TaskHandlerServiceImpl :: addTaskDetals Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : getLoggedInTeamTasksDetails() method is used to get task details
     * in task_list table for team perspective
     *
     *
     * *****************************************************************************
     */
    public List getLoggedInTeamTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List tasklist = new ArrayList();
        DateUtility dateUtility = new DateUtility();
        Map newMap = new HashMap();
        int i = 0;
        String startDate = "";
        String endDate = "";
        System.out.println("********************TaskHandlerServiceImpl :: getLoggedInTeamTasksDetails Method Start*********************");
        try {
            endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate());
            startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate());
            Map map = com.mss.msp.util.DataSourceDataProvider.getInstance().getMyTeamMembers(taskHandlerAction.getUserSessionId());
            String key, listId = "";
            tasklist.add(newMap);
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
                        listId += "," + key;
                    }
                    entry = null;
                }

            }
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
                    + "ORDER BY t.task_id DESC LIMIT 100";

            System.out.println("getLoggedInTeamTasksDetails :: query string ------>" + queryString);
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
        System.out.println("********************TaskHandlerServiceImpl :: getLoggedInTeamTasksDetails Method End*********************");
        return tasklist;
    }

    /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : getTeamTasksDetails() method is used to get task details in
     * task_list based on search criteria
     *
     *
     * *****************************************************************************
     */
    public List getTeamTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList tasklist = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        System.out.println("********************TaskHandlerServiceImpl :: getTeamTasksDetails Method Start*********************");

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
                        listId += "," + key;
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
            if (!"-1".equalsIgnoreCase(taskHandlerAction.getTask_status())) {
                queryString = queryString + " and t.task_status = '" + taskHandlerAction.getTask_status() + "'  ";
            }
            if (!"-1".equalsIgnoreCase(taskHandlerAction.getTeamMember())) {
                queryString = queryString + "AND(t.task_created_by=" + taskHandlerAction.getTeamMember() + " OR t.pri_assigned_to=" + taskHandlerAction.getTeamMember() + " OR t.sec_assigned_to=" + taskHandlerAction.getTeamMember() + ") ";
            }
            queryString = queryString + "AND task_created_by IN(" + taskHandlerAction.getUserSessionId() + "" + listId + ")";

            queryString = queryString + " ORDER BY t.task_id DESC";

            System.out.println("getTeamTasksDetails :: query string ------>" + queryString);
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
        System.out.println("********************TaskHandlerServiceImpl :: getTeamTasksDetails Method End*********************");
        return tasklist;
    }

    /**
     * *****************************************************************************
     * Date : 04/21/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getEditTaskDetails() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public TasksVTO getEditTaskDetails(TaskHandlerAction taskHandlerAction, int usrRole, String usrType) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TasksVTO tasksVTO = new TasksVTO();
        Map map = new HashMap();
        Map map1 = new HashMap();
        System.out.println("********************TaskHandlerServiceImpl :: getEditTaskDetails Method Start*********************");
        try {
            queryString = "SELECT task_prj_related_to,task_id,task_related_to,task_type,task_status,priority,task_start_date,task_end_date,task_name,task_comments,pri_assigned_to,sec_assigned_to,task_created_by FROM task_list WHERE task_id=?";
            System.out.println("getEditTaskDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            DateUtility dateUtility = new DateUtility();
            while (resultSet.next()) {
                tasksVTO.setTask_prj_related_to(resultSet.getInt("task_prj_related_to"));
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
                tasksVTO.setSec_reportsId(resultSet.getInt("sec_assigned_to"));
                tasksVTO.setSec_assigned_to(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("sec_assigned_to")));
                tasksVTO.setTask_created_by(resultSet.getString("task_created_by"));
            }
            tasksVTO.setTypeMaps(com.mss.msp.util.DataSourceDataProvider.getInstance().getTaskTypeById(taskHandlerAction));
            if (tasksVTO.getTask_related_to().equals("2")) {
                taskHandlerAction.setTeamMemberNames(com.mss.msp.util.DataSourceDataProvider.getInstance().getCSRTeam(taskHandlerAction));
            } else {
                taskHandlerAction.setTeamMemberNames(com.mss.msp.util.DataSourceDataProvider.getInstance().getMyTeamMembers(taskHandlerAction.getUserSessionId()));
            }
            taskHandlerAction.setTasksRelatedToList(com.mss.msp.util.DataSourceDataProvider.getInstance().getTaskrelatedToMap(usrRole, usrType, taskHandlerAction.getUserSessionId()));

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
        System.out.println("********************TaskHandlerServiceImpl :: getEditTaskDetails Method End*********************");
        return tasksVTO;

    }

    /**
     * *****************************************************************************
     * Date : 04/21/2015
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : updateTaskDetails() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public int updateTaskDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int i = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        startDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getStartDate());
        endDate = dateUtility.getInstance().convertStringToMySQLDate1(taskHandlerAction.getEndDate());
        System.out.println("********************TaskHandlerServiceImpl :: updateTaskDetails Method Start*********************");
        try {
            queryString = "update task_list set task_related_to=?,task_status=?,task_name=?,task_prj_related_to=?,task_comments=?,priority=?,pri_assigned_to=?,sec_assigned_to=?,task_start_date=?,task_end_date=?,task_modified_by=?,task_modified_date=? where task_id=?";
            System.out.println("updateTaskDetails :: query string ------>" + queryString);
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
        } catch (Exception sqe) {
            sqe.printStackTrace();
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************TaskHandlerServiceImpl :: updateTaskDetails Method End*********************");
        return i;
    }

    /**
     * *****************************************************************************
     * Date : 04/21/2015
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : addAttachmentDetails() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public int addAttachmentDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        System.out.println("********************TaskHandlerServiceImpl :: addAttachmentDetails Method Start*********************");
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        StringBuffer stringBuffer = new StringBuffer();
        int addResult = 0;
        boolean isExceute = false;
        try {

            queryString = "INSERT INTO task_attachments(task_id,attachment_name,attachment_path,status,created_by) VALUES(?,?,?,?,?)";
            System.out.println("addAttachmentDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            preparedStatement.setString(2, taskHandlerAction.getTaskAttachmentFileName());
            preparedStatement.setString(3, taskHandlerAction.getFilePath());
            preparedStatement.setString(4, "Active");
            preparedStatement.setInt(5, taskHandlerAction.getUserSessionId());
            addResult = preparedStatement.executeUpdate();
            if (addResult > 0) {
            } else {
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************TaskHandlerServiceImpl :: addAttachmentDetails Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date : 04/22/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getNotesDetails() method is used to get task details and appends
     * on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public String getNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
        String resultMsg = "";
        int i = 0;
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        System.out.println("********************TaskHandlerServiceImpl :: getNotesDetails Method Start*********************");
        try {
            queryString = "SELECT n.id, n.reference_id,n.notes_title,n.notes_comments,n.created_date ,concat(u.first_name,'.',u.last_name) as Names FROM notes n JOIN users u ON(n.created_by=u.usr_id)  WHERE n.reference_type='T' AND n.reference_id=?";

            System.out.println("getNotesDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultMsg += resultSet.getString("id") + "|" + resultSet.getString("reference_id") + "|" + resultSet.getString("notes_title") + "|" + resultSet.getString("notes_comments") + "|" + dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("created_date")) + "^";
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
        System.out.println("********************TaskHandlerServiceImpl :: getNotesDetails Method End*********************");
        return resultMsg;
    }

    /**
     * *****************************************************************************
     * Date : 04/22/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getNotesDetailsOverlay() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public String getNotesDetailsOverlay(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        String resultMsg = "";
        int i = 0;
        System.out.println("********************TaskHandlerServiceImpl :: getNotesDetailsOverlay Method Start*********************");
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            queryString = "SELECT n.id, n.reference_id,n.notes_title,n.notes_comments,n.created_date ,concat(u.first_name,'.',u.last_name) as Names FROM notes n JOIN users u ON(n.created_by=u.usr_id)  WHERE n.reference_type='T' AND n.reference_id=? and n.id=?";

            System.out.println("getNotesDetailsOverlay :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            preparedStatement.setInt(2, taskHandlerAction.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultMsg += resultSet.getString("id") + "|" + resultSet.getString("reference_id") + "|" + resultSet.getString("notes_title") + "|" + resultSet.getString("notes_comments");
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
        System.out.println("********************TaskHandlerServiceImpl :: getNotesDetailsOverlay Method End*********************");
        return resultMsg;
    }

    /**
     * *****************************************************************************
     * Date : 04/23/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : UpdateNotesDetails() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public int UpdateNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int resultInt = 0;
        int i = 0;
        System.out.println("********************TaskHandlerServiceImpl :: UpdateNotesDetails Method Start*********************");
        try {
            queryString = "UPDATE notes SET notes_title=?,notes_comments=?,modified_by=?,modified_date=? WHERE id=? AND reference_id=?";
            System.out.println("UpdateNotesDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, taskHandlerAction.getNote_name());
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
        System.out.println("********************TaskHandlerServiceImpl :: UpdateNotesDetails Method End*********************");
        return resultInt;
    }

    /**
     * *****************************************************************************
     * Date : 04/23/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : DoInsertNotesDetails() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public int DoInsertNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int resultInt = 0;
        int i = 0;
        System.out.println("********************TaskHandlerServiceImpl :: DoInsertNotesDetails Method Start*********************");
        try {
            queryString = "insert into notes(reference_type,reference_id,notes_title,notes_comments,created_by) values(?,?,?,?,?)";
            System.out.println("DoInsertNotesDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, "T");
            preparedStatement.setInt(2, taskHandlerAction.getTaskid());
            preparedStatement.setString(3, taskHandlerAction.getNote_name());
            preparedStatement.setString(4, taskHandlerAction.getNote_comments());
            preparedStatement.setInt(5, taskHandlerAction.getUserSessionId());
            resultInt = preparedStatement.executeUpdate();
        } catch (Exception sqe) {
            sqe.printStackTrace();
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************TaskHandlerServiceImpl :: DoInsertNotesDetails Method End*********************");
        return resultInt;
    }

    /**
     * *****************************************************************************
     * Date : 04/23/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getNotesDetailsBySearch() method is used to getting search notes
     * details
     *
     *
     * *****************************************************************************
     */
    public String getNotesDetailsBySearch(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {

        String resultString = "";
        System.out.println("********************TaskHandlerServiceImpl :: getNotesDetailsBySearch Method Start*********************");
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            queryString = "select id,reference_id,notes_title,notes_comments,created_date from notes where reference_type='T' AND reference_id=" + taskHandlerAction.getTaskid();

            if (!taskHandlerAction.getNotesName().equals("")) {
                queryString += " and notes_title LIKE '%" + taskHandlerAction.getNotesName() + "%'";
            }
            System.out.println("getNotesDetailsBySearch :: query string ------>" + queryString);
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
        System.out.println("********************TaskHandlerServiceImpl :: getNotesDetailsBySearch Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 04/22/2015
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getCommentsOnOverlay() method is used to get task details and
     * appends on TaskEdit screen
     *
     *
     * *****************************************************************************
     */
    public String getCommentsOnOverlay(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String resultMsg = "";
        int i = 0;
        System.out.println("********************TaskHandlerServiceImpl :: getCommentsOnOverlay Method Start*********************");
        try {
            queryString = "SELECT task_comments FROM task_list WHERE task_id=?";

            System.out.println("getCommentsOnOverlay :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultMsg = resultSet.getString("task_comments");
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
        System.out.println("********************TaskHandlerServiceImpl :: getCommentsOnOverlay Method End*********************");
        return resultMsg;
    }
}
