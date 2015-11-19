/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.leaves;

import com.mss.msp.usr.task.TasksVTO;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.MailManager;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author NagireddySeerapu
 */
public class UserLeaveServiceImpl implements UserLeavesService {

    private Connection connection;

    public List getEmpLeavesListDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException {
        ArrayList<LeavesVTO> leaveslist = new ArrayList<LeavesVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";

        //String queryString1 = "";
        int i = 0;
        try {


            queryString = "SELECT leave_id,usr_id,DATE(leave_startdate) as l_sdate,DATE(leave_enddate) as l_edate,leave_status,leave_type,leave_reason,reports_to,created_date,created_by,modified_by FROM usr_leaves WHERE 1=1 ";
            if (userLeavesAction.getLeave_startdate() != null && userLeavesAction.getLeave_startdate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userLeavesAction.getLeave_startdate());

                queryString = queryString + " and leave_startdate >= '" + startDate + "'";
            }

            if (userLeavesAction.getLeave_enddate() != null && userLeavesAction.getLeave_enddate().toString().isEmpty() == false) {
                 endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userLeavesAction.getLeave_enddate());

                queryString = queryString + " and leave_enddate <= '" + endDate + "'";
            }

            if (!"DF".equalsIgnoreCase(userLeavesAction.getLeave_status())) {

                queryString = queryString + " and leave_status = '" + userLeavesAction.getLeave_status() + "' ";
            }

            if (!"DF".equals(userLeavesAction.getLeave_type())) {

                queryString = queryString + " and leave_type = '" + userLeavesAction.getLeave_type() + "' ";
            }

            queryString = queryString + " and usr_id=" + userLeavesAction.getUserSessionId() + " LIMIT 20";

            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            //String name="SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=resultSet.getInt("reports_to")";
            while (resultSet.next()) {
                //queryString1= queryString1+ resultSet.getInt("reports_to");
                LeavesVTO leavesVTO = new LeavesVTO();
                leavesVTO.setLeaveid(resultSet.getInt("leave_id"));
                leavesVTO.setLeavestartdate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_sdate")));
                leavesVTO.setLeaveenddate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_edate")));
                String status = resultSet.getString("leave_status");
                //leavesVTO.setModifiedby(resultSet.getInt("modified_by"));
                // leavesVTO.setApprovedBy(approvedBy);
                if ("C".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Canceled");
                } else if ("O".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Applied");
                } else if ("A".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Approved");
                } else if ("R".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Rejected");
                }
                String leaveType = resultSet.getString("leave_type");
                if ("PA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Post Approval");
                } else if ("CT".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("CompTime");
                } else if ("VA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Vacation");
                } else if ("TO".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("TimeOFF");
                } else if ("CL".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Cancel Leave");
                }
                leavesVTO.setReportsto(dsdp.getFnameandLnamebyUserid(resultSet.getInt("reports_to")));
                leavesVTO.setApprovedBy(dsdp.getFnameandLnamebyUserid(resultSet.getInt("modified_by")));
                leaveslist.add(leavesVTO);
            }
            // System.out.println("----------->" + leaveslist);
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return leaveslist;
    }

    public List getDefaultMyEmpLeavesListDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException {
         DateUtility dateUtility = new DateUtility();
        ArrayList<LeavesVTO> leaveslist = new ArrayList<LeavesVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        /*CallableStatement callableStatement = null;
         PreparedStatement preparedStatement = null;*/
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        try {
            queryString = "SELECT leave_id,usr_id,DATE(leave_startdate) as l_sdate,DATE(leave_enddate) as l_edate,leave_status,leave_type,leave_reason,reports_to,created_date,created_by,modified_by FROM usr_leaves WHERE 1=1 ";
            queryString = queryString + " and usr_id=" + userLeavesAction.getUserSessionId() + " LIMIT 15";

            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();

            while (resultSet.next()) {
                //queryString1= queryString1+ resultSet.getInt("reports_to");
                LeavesVTO leavesVTO = new LeavesVTO();
                leavesVTO.setLeaveid(resultSet.getInt("leave_id"));
               leavesVTO.setLeavestartdate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_sdate")));
                leavesVTO.setLeaveenddate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_edate")));
                 String status = resultSet.getString("leave_status");
                //leavesVTO.setModifiedby(resultSet.getInt("modified_by"));
                // leavesVTO.setApprovedBy(approvedBy);
                if ("C".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Canceled");
                } else if ("O".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Applied");
                } else if ("A".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Approved");
                } else if ("R".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Rejected");
                }
                String leaveType = resultSet.getString("leave_type");
                if ("PA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Post Approval");
                } else if ("CT".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("CompTime");
                } else if ("VA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Vacation");
                } else if ("TO".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("TimeOFF");
                } else if ("CL".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Cancel Leave");
                }
                leavesVTO.setReportsto(dsdp.getFnameandLnamebyUserid(resultSet.getInt("reports_to")));
                leavesVTO.setApprovedBy(dsdp.getFnameandLnamebyUserid(resultSet.getInt("modified_by")));
                leaveslist.add(leavesVTO);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return leaveslist;
    }

    /**
     * ****************************************************************************
     * Date : April 20 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * getEmployeeLeaves method can be used to retrive existing leaves data by
     * using leave id, And returns EmpLeaves Object for the respected user
     * *****************************************************************************
     */
    public EmpLeaves getEmployeeLeaves(HttpServletRequest httpServletRequest, int userid, int leave_id) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sb = new StringBuffer();
        String queryString = "";
        EmpLeaves empLeaves = new EmpLeaves();

        try {
            queryString = "SELECT usr_id,leave_id,leave_startdate,leave_enddate,leave_type,leave_reason,reports_to,leave_status FROM usr_leaves WHERE leave_id=" + leave_id;

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();

            while (resultSet.next()) {
                empLeaves.setLeaveId(resultSet.getInt("leave_id"));
                empLeaves.setEmpName(dsdp.getFnameandLnamebyUserid(resultSet.getInt("usr_id")));
                empLeaves.setUser(resultSet.getInt("usr_id"));
               empLeaves.setLeaveEditFrmDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("leave_startdate")));

                empLeaves.setLeaveEditEndDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("leave_enddate")));

                empLeaves.setReportsTo(dsdp.getFnameandLnamebyUserid(resultSet.getInt("reports_to")));
                //empLeaves.setReportsTo(resultSet.getInt("reports_to"));
                empLeaves.setLeaveType(resultSet.getString("leave_type"));
                empLeaves.setAlertMessage(resultSet.getString("leave_reason"));
                empLeaves.setStatus(resultSet.getString("leave_status"));

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
        return empLeaves;
    } // end of getEmployeeLeaves method

    /**
     * ****************************************************************************
     * Date : April 20 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * UpdatedEmpLeaves method can be used to update the existing leaves by
     * using leave id, And returns integer value for the respected user
     * *****************************************************************************
     */
    public int UpdatedEmpLeaves(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction, int userid) throws ServiceLocatorException {

        int isUpdated = 0;
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String status = userLeavesAction.getStatus();
        MailManager mailManager = new MailManager();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userLeavesAction.getLeaveEditFrmDate());
        endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userLeavesAction.getLeaveEditEndDate());

        try {
            queryString = "UPDATE usr_leaves SET leave_startdate=?,leave_enddate=?,leave_type=?,leave_reason=?,modified_date=?,modified_by=?,leave_status=? WHERE leave_id=" + userLeavesAction.getLeave_id();

            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, startDate);

            preparedStatement.setString(2, endDate);
            preparedStatement.setString(3, userLeavesAction.getLeaveType());
            preparedStatement.setString(4, userLeavesAction.getAlertMessage());

            // preparedStatement.setInt(5,reportingPersonId);
            preparedStatement.setTimestamp(5, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(6, userid);
            if (status == null) {
                preparedStatement.setString(7, userLeavesAction.getLeavestatus());

            } else {
                preparedStatement.setString(7, userLeavesAction.getStatus());
            }

            isUpdated = preparedStatement.executeUpdate();
            if (isUpdated > 0) {
                if (status != null) {
                    DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
                    int usrid = userLeavesAction.getUser();
                    //dsdp.getUserIdByLeaveId(userLeavesAction.getLeave_id());
                    String emailto = dsdp.getEmailIdbyuser(usrid);
                    System.out.println("hai user id " + usrid);
                    String cc = "";
                    String bcc = "";
                    String username = dsdp.getFnameandLnamebyUserid(usrid);
                    List leaveCc = null;//dsdp.getReportsTo(usrid);
                    //please change the get reports code for forwarding actions.
                    System.out.println("leaveCc" + leaveCc);
                    if (leaveCc.size() == 1) {
                        for (int j = 0; j < leaveCc.size(); j++) {
                            cc = (String) leaveCc.get(0);
                        }

                    }
                    if (leaveCc.size() == 2) {
                        for (int j = 0; j < leaveCc.size(); j++) {
                            cc = (String) leaveCc.get(0);
                            bcc = (String) leaveCc.get(1);
                        }
                    }

//                      
                    mailManager.sendLeaveEmail(emailto, cc, "", username, userLeavesAction.getAlertMessage(), userLeavesAction.getStatus(), startDate, endDate, userLeavesAction.getLeaveType(), userid);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }


        return isUpdated;
    } //end of UpdatedEmpLeaves method.

    /**
     * ****************************************************************************
     * Date : April 21 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * getTeamLeavesListDetails() method which retrieves team leaves the
     * existing leaves.
     * *****************************************************************************
     */
    public List getTeamLeavesListDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
      
        ArrayList leaveslist = new ArrayList();
        //List leaveslist = null;
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String queryString1 = "";
        int i = 0;
        try {


            System.out.println(userLeavesAction.getUserSessionId());
            int id = userLeavesAction.getUserSessionId();
            Map map = DataSourceDataProvider.getInstance().getMyTeamMembers(id);
            System.out.println(map);

            leaveslist.add(map);

            String key, retrunValue = "";
            int mapsize = map.size();
            if (map.size() > 0) {
                Iterator tempIterator = map.entrySet().iterator();
                while (tempIterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    key = entry.getKey().toString();
                    mapsize--;
                    if (mapsize != 0) {
                        retrunValue += key + ",";
                    } else {
                        retrunValue += key;
                    }
                    entry = null;
                }
            }

            queryString = "SELECT leave_id,usr_id,DATE(leave_startdate) as l_sdate,DATE(leave_enddate) as l_edate,leave_status,leave_type,leave_reason,reports_to,created_date,created_by,modified_by FROM usr_leaves WHERE 1=1 ";

            queryString = queryString + "and  usr_id in(" + retrunValue + ")  ORDER BY FIELD(leave_status, 'O','A','C','R'),created_date";



            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            //String name="SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=resultSet.getInt("reports_to")";
            while (resultSet.next()) {
                // queryString1= queryString1+ resultSet.getInt("reports_to");
                LeavesVTO leavesVTO = new LeavesVTO();
                leavesVTO.setLeaveid(resultSet.getInt("leave_id"));
                leavesVTO.setLeavestartdate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_sdate")));
                leavesVTO.setLeaveenddate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_edate")));
               
                String status = resultSet.getString("leave_status");
                //leavesVTO.setModifiedby(resultSet.getInt("modified_by"));
                // leavesVTO.setApprovedBy(approvedBy);
                if ("C".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Canceled");
                } else if ("O".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Applied");
                } else if ("A".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Approved");
                } else if ("R".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Rejected");
                }
                String leaveType = resultSet.getString("leave_type");
                if ("PA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Post Approval");
                } else if ("CT".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("CompTime");
                } else if ("VA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Vacation");
                } else if ("TO".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("TimeOFF");
                } else if ("CL".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Cancel Leave");
                }
                //leavesVTO.setLeavestatus(resultSet.getString("leave_status"));
                leavesVTO.setReportsto(dsdp.getFnameandLnamebyUserid(resultSet.getInt("reports_to")));
                leavesVTO.setApprovedBy(dsdp.getFnameandLnamebyUserid(resultSet.getInt("modified_by")));
                leaveslist.add(leavesVTO);

            }

            System.out.println("----------->" + leaveslist);

        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return leaveslist;
    }//end

    /**
     * ****************************************************************************
     * Date : April 21 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * getTeamMemberLeavesDetails() method which retrieves team member leaves
     * the existing leaves.
     * *****************************************************************************
     */
    public List getTeamMemberLeavesDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException {
        ArrayList leaveslist = new ArrayList();
        //List leaveslist = null;
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String queryString1 = "";
        int i = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        System.out.println("end date in team leaveis@@@--->" + endDate);
        System.out.println("Start date in team leave is@@@--->" + startDate);
        
        try {
            int id = userLeavesAction.getUserSessionId();
            Map map = DataSourceDataProvider.getInstance().getMyTeamMembers(id);
            //System.out.println(map);

            leaveslist.add(map);

            String key, retrunValue = "";
            int mapsize = map.size();
            if (map.size() > 0) {
                Iterator tempIterator = map.entrySet().iterator();
                while (tempIterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    key = entry.getKey().toString();
                    mapsize--;
                    if (mapsize != 0) {
                        retrunValue += key + ",";
                    } else {
                        retrunValue += key;
                    }
                    entry = null;
                }
            }

            queryString = "SELECT leave_id,usr_id,DATE(leave_startdate) as l_sdate,DATE(leave_enddate) as l_edate,leave_status,leave_type,leave_reason,reports_to,created_date,created_by,modified_by FROM usr_leaves WHERE 1=1 and  usr_id in(" + retrunValue + ")";


            if (userLeavesAction.getLeave_startdate() != null && userLeavesAction.getLeave_startdate().toString().isEmpty() == false) {
                 startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userLeavesAction.getLeave_startdate());

                queryString = queryString + " and leave_startdate >= '" +startDate + "'";
            }

            if (userLeavesAction.getLeave_enddate() != null && userLeavesAction.getLeave_enddate().toString().isEmpty() == false) {
                  endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userLeavesAction.getLeave_enddate());

                queryString = queryString + " and leave_enddate <= '" + endDate + "'";
            }

            if (!"DF".equalsIgnoreCase(userLeavesAction.getLeave_status())) {

                queryString = queryString + " and leave_status = '" + userLeavesAction.getLeave_status() + "' ";
            }

            if (!"DF".equals(userLeavesAction.getLeave_type())) {

                queryString = queryString + " and leave_type = '" + userLeavesAction.getLeave_type() + "' ";
            }



            //andFlag = false;
            System.out.println(userLeavesAction.getTeamMember());

            if (!"-1".equalsIgnoreCase(userLeavesAction.getTeamMember())) {
                //queryString = queryString + "AND(t.task_created_by="+userLeavesAction.getTeamMember()+" )";
                queryString = queryString + " and usr_id ='" + userLeavesAction.getTeamMember() + "'";
            }
            queryString = queryString + " ORDER BY FIELD(leave_status, 'O','A','C','R'),created_date";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            //String name="SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=resultSet.getInt("reports_to")";
            while (resultSet.next()) {
                // queryString1= queryString1+ resultSet.getInt("reports_to");
                LeavesVTO leavesVTO = new LeavesVTO();
                leavesVTO.setLeaveid(resultSet.getInt("leave_id"));
               leavesVTO.setLeavestartdate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_sdate")));
                leavesVTO.setLeaveenddate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_edate")));

                String status = resultSet.getString("leave_status");
                //leavesVTO.setModifiedby(resultSet.getInt("modified_by"));
                // leavesVTO.setApprovedBy(approvedBy);
                if ("C".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Canceled");
                } else if ("O".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Applied");
                } else if ("A".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Approved");
                } else if ("R".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Rejected");
                }
                String leaveType = resultSet.getString("leave_type");
                if ("PA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Post Approval");
                } else if ("CT".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("CompTime");
                } else if ("VA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Vacation");
                } else if ("TO".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("TimeOFF");
                } else if ("CL".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Cancel Leave");
                }

                //leavesVTO.setLeavestatus(resultSet.getString("leave_status"));
                leavesVTO.setReportsto(dsdp.getFnameandLnamebyUserid(resultSet.getInt("reports_to")));
                leavesVTO.setApprovedBy(dsdp.getFnameandLnamebyUserid(resultSet.getInt("modified_by")));

                leaveslist.add(leavesVTO);

            }

            System.out.println("----------->" + leaveslist);

        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return leaveslist;
    } //end

    public List getLeavesDashboardSearchList(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException {
        ArrayList<LeavesVTO> leavesDashboardList = new ArrayList<LeavesVTO>();
        //StringBuffer stringBuffer = new StringBuffer();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        //String queryString1 = "";
        int i = 0;
        try {

            /*
             * SELECT l.leave_id,l.leave_startdate,l.leave_enddate,l.leave_status,l.leave_type,l.reports_to,l.modified_by FROM usr_leaves l  
             LEFT OUTER JOIN usr_miscellaneous um ON (l.usr_id=um.usr_id) JOIN lk_country lc JOIN users us ON (lc.id=us.living_country AND us.usr_id=um.usr_id) WHERE EXTRACT(YEAR FROM l.leave_startdate)=2015 
             AND EXTRACT(MONTH FROM l.leave_startdate)=4 
             AND um.dept_id = 3 AND um.usr_id=1003 AND lc.id=2
             */
            queryString = "SELECT l.leave_id,DATE(l.leave_startdate) as l_sdate,DATE(l.leave_enddate) as l_edate,l.leave_status,l.leave_type,l.reports_to,l.modified_by FROM usr_leaves l LEFT OUTER JOIN usr_miscellaneous um ON (l.usr_id=um.usr_id) JOIN lk_country lc JOIN users us ON (lc.id=us.living_country AND us.usr_id=um.usr_id) WHERE 1=1 ";
            if (userLeavesAction.getYear() != -1) {
                queryString = queryString + " and EXTRACT(YEAR FROM l.leave_startdate)= " + userLeavesAction.getYear() + "";
            }

            if (userLeavesAction.getMonth() != -1) {
                queryString = queryString + " and EXTRACT(MONTH FROM l.leave_startdate)= " + userLeavesAction.getMonth() + "";
            }

            if (userLeavesAction.getCountryId() != -1) {

                queryString = queryString + " and lc.id=" + userLeavesAction.getCountryId() + "";
            }

            if (userLeavesAction.getDepartmentId() != -1) {

                queryString = queryString + " and um.dept_id = " + userLeavesAction.getDepartmentId() + "";
            }

            if (userLeavesAction.getUserId() != -1) {

                queryString = queryString + " and um.usr_id = " + userLeavesAction.getUserId() + " ";
            }
            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            //String name="SELECT CONCAT(first_name,' ',last_name) AS NAME FROM users WHERE usr_id=resultSet.getInt("reports_to")";
            while (resultSet.next()) {
                //queryString1= queryString1+ resultSet.getInt("reports_to");
                LeavesVTO leavesVTO = new LeavesVTO();
                leavesVTO.setLeaveid(resultSet.getInt("leave_id"));
                leavesVTO.setLeavestartdate(resultSet.getString("l_sdate"));
                leavesVTO.setLeaveenddate(resultSet.getString("l_edate"));
                String status = resultSet.getString("leave_status");
                //leavesVTO.setModifiedby(resultSet.getInt("modified_by"));
                // leavesVTO.setApprovedBy(approvedBy);
                if ("C".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Canceled");
                } else if ("O".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Opened");
                } else if ("A".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Approved");
                } else if ("R".equalsIgnoreCase(status)) {
                    leavesVTO.setLeavestatus("Rejected");
                }
                String leaveType = resultSet.getString("leave_type");
                if ("PA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Post Approval");
                } else if ("CT".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("CompTime");
                } else if ("VA".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Vacation");
                } else if ("TO".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("TimeOFF");
                } else if ("CL".equalsIgnoreCase(leaveType)) {
                    leavesVTO.setLeavetype("Cancel Leave");
                }
                leavesVTO.setReportsto(dsdp.getFnameandLnamebyUserid(resultSet.getInt("reports_to")));
                leavesVTO.setApprovedBy(dsdp.getFnameandLnamebyUserid(resultSet.getInt("modified_by")));
                leavesDashboardList.add(leavesVTO);
            }
            // System.out.println("----------->" + leaveslist);
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return leavesDashboardList;
    }
}
