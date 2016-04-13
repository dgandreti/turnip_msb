/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.timesheets;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public class UsrTimesheetServiceImpl implements UsrTimesheetService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : getTimesheetListDetails() method is used to get
     *
     * *****************************************************************************
     */
    public List getTimesheetListDetails(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {

        System.out.println("********************UsrTimesheetServiceImpl :: getTimesheetListDetails Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        try {
            queryString = "SELECT EmpId,TimesheetId,TotalHrs,ReportsTo,CurStatus,DateStart,DateEnd,SubmittedDate,ApprovedDate FROM vwtimesheetlist WHERE 1=1 ";
            if (usrTimeSheetAction.getStartDate() != null && usrTimeSheetAction.getStartDate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getStartDate());
                queryString = queryString + " and DateStart >= '" + startDate + "'";
            }
            if (usrTimeSheetAction.getEndDate() != null && usrTimeSheetAction.getEndDate().toString().isEmpty() == false) {
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getEndDate());
                queryString = queryString + " and DateEnd <= '" + endDate + "'";
            }
            queryString = queryString + " and EmpId=" + usrTimeSheetAction.getUserId() + " ORDER BY TimesheetId DESC LIMIT 20 ";
            System.out.println("getTimesheetListDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setUsr_id(resultSet.getInt("EmpId"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setReportsto1(dsdp.getFnameandLnamebyUserid(resultSet.getInt("ReportsTo")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setTotalBillHrs(resultSet.getDouble("TotalHrs"));
                if ("1950-01-01".equals(resultSet.getString("ApprovedDate"))) {
                    timesheetVTO.setApprovedDate("-");
                } else {
                    timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                }
                timesheetList.add(timesheetVTO);
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: getTimesheetListDetails Method End*********************");
        return timesheetList;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : getAllTimeSheetList() method is used to
     *
     * *****************************************************************************
     */
    public List getAllTimeSheetList(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getAllTimeSheetList Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        int i = 0;
        try {
            queryString = "SELECT EmpId,TimesheetId,TotalHrs,ReportsTo,CurStatus,DateStart,DateEnd,SubmittedDate,ApprovedDate FROM vwtimesheetlist WHERE 1=1 ";
            queryString = queryString + " and EmpId=" + usrTimeSheetAction.getUserId() + " ORDER BY TimesheetId DESC LIMIT 20 ";
            System.out.println("getAllTimeSheetList :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setReportsto1(dsdp.getFnameandLnamebyUserid(resultSet.getInt("ReportsTo")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                timesheetVTO.setTotalBillHrs(resultSet.getDouble("TotalHrs"));
                timesheetList.add(timesheetVTO);
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: getAllTimeSheetList Method End*********************");
        return timesheetList;
    }

    /**
     *
     * @return @throws ServiceLocatorException
     */
    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : deleteTimeSheet() method is used to delete a particular
     * timesheet.
     *
     * *****************************************************************************
     */
    public int deleteTimeSheet(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: deleteTimeSheet Method Start*********************");
        int deletedRows = 0;
        Connection connection = null;
        String queryString = "";
        Statement statement = null;

        queryString = "Delete from usrtimesheets  WHERE usr_id=" + usrTimeSheetAction.getUserId() + " AND TimeSheetId='" + usrTimeSheetAction.getTimesheetid() + "'";
        String queryString1 = "Delete from usrtimesheetlines WHERE usr_id=" + usrTimeSheetAction.getUserId() + " AND timesheetid='" + usrTimeSheetAction.getTimesheetid() + "'";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("deleteTimeSheet :: query string ------>" + queryString);
            System.out.println("deleteTimeSheet :: query string 1------>" + queryString1);
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(queryString);
            deletedRows = statement.executeUpdate(queryString1);
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: deleteTimeSheet Method End*********************");
        return deletedRows;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : getTimesheetStatusByTmsheetId() method is used to get status of
     * timesheet.
     *
     * *****************************************************************************
     */
    public String getTimesheetStatusByTmsheetId(int tmStatus) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getTimesheetStatusByTmsheetId Method Start*********************");
        String status = null;
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT  Description FROM lk_timeSheetStatusType WHERE id =" + tmStatus;
        System.out.println("getTimesheetStatusByTmsheetId :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                status = resultSet.getString("Description");
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
        System.out.println("********************UsrTimesheetServiceImpl :: getTimesheetStatusByTmsheetId Method End*********************");
        return status;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : AddTimesheet() method is used to
     *
     * *****************************************************************************
     */
    public int AddTimesheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: AddTimesheet Method Start*********************");
        int timeAdd = 0;
        Connection connection = null;
        String queryString = "";
        CallableStatement callableStatement = null;

        DateUtility dateUtility = new DateUtility();
        String startDate = null;
        String endDate = null;
        String submittedDate = null;
        startDate = dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getTimeSheetStartDate());
        endDate = dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getTimeSheetEndDate());
        submittedDate = dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getTimeSheetSubmittedDate());
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("AddTimesheet :: procedure name : addTimesheet ");
            callableStatement = connection.prepareCall("{call addTimesheet(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    + ")}");
            callableStatement.setInt(1, userTimeSheetAction.getUserSessionId());
            callableStatement.setString(2, startDate);
            callableStatement.setString(3, endDate);
            callableStatement.setDouble(4, userTimeSheetAction.getProjectNameSun1());
            callableStatement.setDouble(5, userTimeSheetAction.getProjectNameMon1());
            callableStatement.setDouble(6, userTimeSheetAction.getProjectNameTue1());
            callableStatement.setDouble(7, userTimeSheetAction.getProjectNameWed1());
            callableStatement.setDouble(8, userTimeSheetAction.getProjectNameThu1());
            callableStatement.setDouble(9, userTimeSheetAction.getProjectNameFri1());
            callableStatement.setDouble(10, userTimeSheetAction.getProjectNameSat1());

            callableStatement.setDouble(11, userTimeSheetAction.getProjectNameSun2());
            callableStatement.setDouble(12, userTimeSheetAction.getProjectNameMon2());
            callableStatement.setDouble(13, userTimeSheetAction.getProjectNameTue2());
            callableStatement.setDouble(14, userTimeSheetAction.getProjectNameWed2());
            callableStatement.setDouble(15, userTimeSheetAction.getProjectNameThu2());
            callableStatement.setDouble(16, userTimeSheetAction.getProjectNameFri2());
            callableStatement.setDouble(17, userTimeSheetAction.getProjectNameSat2());

            callableStatement.setDouble(18, userTimeSheetAction.getProjectNameSun3());
            callableStatement.setDouble(19, userTimeSheetAction.getProjectNameMon3());
            callableStatement.setDouble(20, userTimeSheetAction.getProjectNameTue3());
            callableStatement.setDouble(21, userTimeSheetAction.getProjectNameWed3());
            callableStatement.setDouble(22, userTimeSheetAction.getProjectNameThu3());
            callableStatement.setDouble(23, userTimeSheetAction.getProjectNameFri3());
            callableStatement.setDouble(24, userTimeSheetAction.getProjectNameSat3());

            callableStatement.setDouble(25, userTimeSheetAction.getProjectNameSun4());
            callableStatement.setDouble(26, userTimeSheetAction.getProjectNameMon4());
            callableStatement.setDouble(27, userTimeSheetAction.getProjectNameTue4());
            callableStatement.setDouble(28, userTimeSheetAction.getProjectNameWed4());
            callableStatement.setDouble(29, userTimeSheetAction.getProjectNameThu4());
            callableStatement.setDouble(30, userTimeSheetAction.getProjectNameFri4());
            callableStatement.setDouble(31, userTimeSheetAction.getProjectNameSat4());

            callableStatement.setDouble(32, userTimeSheetAction.getProjectNameSun5());
            callableStatement.setDouble(33, userTimeSheetAction.getProjectNameMon5());
            callableStatement.setDouble(34, userTimeSheetAction.getProjectNameTue5());
            callableStatement.setDouble(35, userTimeSheetAction.getProjectNameWed5());
            callableStatement.setDouble(36, userTimeSheetAction.getProjectNameThu5());
            callableStatement.setDouble(37, userTimeSheetAction.getProjectNameFri5());
            callableStatement.setDouble(38, userTimeSheetAction.getProjectNameSat5());

            callableStatement.setDouble(39, userTimeSheetAction.getInternalSun());
            callableStatement.setDouble(40, userTimeSheetAction.getInternalMon());
            callableStatement.setDouble(41, userTimeSheetAction.getInternalTue());
            callableStatement.setDouble(42, userTimeSheetAction.getInternalWed());
            callableStatement.setDouble(43, userTimeSheetAction.getInternalThu());
            callableStatement.setDouble(44, userTimeSheetAction.getInternalFri());
            callableStatement.setDouble(45, userTimeSheetAction.getInternalSat());

            callableStatement.setDouble(46, userTimeSheetAction.getVacationSun());
            callableStatement.setDouble(47, userTimeSheetAction.getVacationMon());
            callableStatement.setDouble(48, userTimeSheetAction.getVacationTue());
            callableStatement.setDouble(49, userTimeSheetAction.getVacationWed());
            callableStatement.setDouble(50, userTimeSheetAction.getVacationThu());
            callableStatement.setDouble(51, userTimeSheetAction.getVacationFri());
            callableStatement.setDouble(52, userTimeSheetAction.getVacationSat());

            callableStatement.setDouble(53, userTimeSheetAction.getHolidaySun());
            callableStatement.setDouble(54, userTimeSheetAction.getHolidayMon());
            callableStatement.setDouble(55, userTimeSheetAction.getHolidayTue());
            callableStatement.setDouble(56, userTimeSheetAction.getHolidayWed());
            callableStatement.setDouble(57, userTimeSheetAction.getHolidayThu());
            callableStatement.setDouble(58, userTimeSheetAction.getHolidayFri());
            callableStatement.setDouble(59, userTimeSheetAction.getHolidaySat());

            callableStatement.setString(60, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates1()));
            callableStatement.setString(61, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates2()));
            callableStatement.setString(62, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates3()));
            callableStatement.setString(63, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates4()));
            callableStatement.setString(64, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates5()));
            callableStatement.setString(65, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates6()));
            callableStatement.setString(66, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getWeeklyDates7()));

            callableStatement.setInt(67, userTimeSheetAction.getProject1key());
            callableStatement.setInt(68, userTimeSheetAction.getProject2key());
            callableStatement.setInt(69, userTimeSheetAction.getProject3key());
            callableStatement.setInt(70, userTimeSheetAction.getProject4key());
            callableStatement.setInt(71, userTimeSheetAction.getProject5key());

            callableStatement.setInt(72, userTimeSheetAction.getReportsTo());
            callableStatement.setString(73, dateUtility.getInstance().convertStringToMySQLDate(userTimeSheetAction.getTimeSheetSubmittedDate()));
            callableStatement.setDouble(74, userTimeSheetAction.getTotalBillHrs());
            callableStatement.setString(75, userTimeSheetAction.getTimeSheetNotes());
            if (userTimeSheetAction.getTempVar() == 1) {
                callableStatement.setInt(76, 2);
            } else {
                callableStatement.setInt(76, 1);
            }
            callableStatement.registerOutParameter(77, java.sql.Types.INTEGER);
            callableStatement.execute();
            int timeSheetId = callableStatement.getInt(77);
            if (timeSheetId > 0) {
                timeAdd = 1;
            } else {
                timeAdd = 0;
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: AddTimesheet Method End*********************");
        return timeAdd;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : getTimesheetList() method is used to
     *
     * *****************************************************************************
     */
    public List getTimesheetList(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getTimesheetList Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        String status = "";
        try {
            queryString = "SELECT EmpId,TimesheetId,TotalHrs,ReportsTo,CurStatus,DateStart,DateEnd,SubmittedDate,ApprovedDate FROM vwtimesheetlist WHERE 1=1 ";
            if (usrTimeSheetAction.getStartDate() != null && usrTimeSheetAction.getStartDate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getStartDate());
                queryString = queryString + " and DateStart >= '" + startDate + "'";
            }
            if (usrTimeSheetAction.getEndDate() != null && usrTimeSheetAction.getEndDate().toString().isEmpty() == false) {
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getEndDate());
                queryString = queryString + " and DateEnd <= '" + endDate + "'";
            }
            if (!"-1".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                if ("CA".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "1";
                } else if ("SU".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "2";
                } else if ("AP".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "3";
                }
                queryString = queryString + " and CurStatus = '" + status + "'";
            }
            queryString = queryString + " and EmpId=" + usrTimeSheetAction.getUserId() + " ORDER BY DateStart DESC LIMIT 20 ";
            System.out.println("getTimesheetList :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setReportsto1(dsdp.getFnameandLnamebyUserid(resultSet.getInt("ReportsTo")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setTotalBillHrs(resultSet.getDouble("TotalHrs"));
                if ("1950-01-01".equals(resultSet.getString("ApprovedDate"))) {
                    timesheetVTO.setApprovedDate("-");
                } else {
                    timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                }
                timesheetVTO.setUsr_id(resultSet.getInt("EmpId"));
                timesheetList.add(timesheetVTO);
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: getTimesheetList Method End*********************");
        return timesheetList;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : getTeamTimeSheetList() method is used to
     *
     * *****************************************************************************
     */
    public List getTeamTimeSheetList(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getTeamTimeSheetList Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        String status = "";
        String tmMember = "";
        try {
            int id = usrTimeSheetAction.getUserSessionId();
            Map map = usrTimeSheetAction.getTeamMembersList();
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
            queryString = "SELECT EmpId,EmpName,TimesheetId,TotalHrs,ReportsTo,CurStatus,DateStart,DateEnd,SubmittedDate,ApprovedDate FROM vwtimesheetlist WHERE 1=1 ";
            if (usrTimeSheetAction.getStartDate() != null && usrTimeSheetAction.getStartDate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getStartDate());
                queryString = queryString + " and DateStart >= '" + startDate + "'";
            }
            if (usrTimeSheetAction.getEndDate() != null && usrTimeSheetAction.getEndDate().toString().isEmpty() == false) {
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getEndDate());
                queryString = queryString + " and DateEnd <= '" + endDate + "'";
            }
            if (!"-1".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                if ("CA".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "1";
                } else if ("SU".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "2";
                } else if ("AP".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "3";
                }
                queryString = queryString + " and CurStatus = '" + status + "'";
            }
            if (!"-1".equalsIgnoreCase(usrTimeSheetAction.getTmmember())) {
                tmMember = usrTimeSheetAction.getTmmember();
            } else {
                System.out.println("in else");
                tmMember = retrunValue;
            }
            queryString = queryString + " and EmpId in (" + tmMember + ") ORDER BY TimesheetId DESC LIMIT 20 ";
            System.out.println("getTeamTimeSheetList :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setUsr_id(resultSet.getInt("EmpId"));
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setEmpName(resultSet.getString("EmpName"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setReportsto1(dsdp.getFnameandLnamebyUserid(resultSet.getInt("ReportsTo")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                timesheetVTO.setTotalBillHrs(resultSet.getDouble("TotalHrs"));
                timesheetList.add(timesheetVTO);
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: getTeamTimeSheetList Method End*********************");
        return timesheetList;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : getUserTimeSheet() method is used to getUserTimeSheet on edit
     * page.
     *
     * *****************************************************************************
     */
    public TimesheetVTO getUserTimeSheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getUserTimeSheet Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        TimesheetVTO timeSheetVTO = new TimesheetVTO();
        int rowCount = 0;
        try {
            String queryString1 = "select comments,notes,DateStart,DateEnd,SubmittedDate,ApprovedDate,usr_id,timesheetid,Description From usrtimesheets us LEFT OUTER JOIN lk_timeSheetStatusType ts ON(us.reportsto1status=ts.Id) Where us.timesheetid=" + userTimeSheetAction.getTimesheetid() + " and us.usr_id=" + userTimeSheetAction.getUsr_id();
            System.out.println("getUserTimeSheet :: query string ------>" + queryString1);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString1);
            while (resultSet.next()) {
                timeSheetVTO.setTimeSheetApprovedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate("ApprovedDate")));
                timeSheetVTO.setTimeSheetSubmittedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate("SubmittedDate")));
                timeSheetVTO.setTimeSheetEndDate(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate("DateEnd")));
                timeSheetVTO.setTimeSheetStartDate(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate("DateStart")));
                timeSheetVTO.setUsr_id(resultSet.getInt("usr_id"));
                timeSheetVTO.setTimesheetid(resultSet.getInt("timesheetid"));
                timeSheetVTO.setTimeSheetStatus(resultSet.getString("Description"));
                timeSheetVTO.setTimeSheetNotes(resultSet.getString("notes"));
                timeSheetVTO.setComments(resultSet.getString("comments"));
            }
            queryString = "select subproject1hrs,subproject2hrs,subproject3hrs,subproject4hrs,subproject5hrs,"
                    + "internalhrs,vacationhrs,comptimehrs,holidayhrs,workdate,subproject1id,"
                    + "subproject2id,subproject3id,subproject4id,subproject5id"
                    + " FROM usrtimesheetlines  WHERE timesheetid=" + timeSheetVTO.getTimesheetid() + " AND usr_id=" + timeSheetVTO.getUsr_id();
            System.out.println("getUserTimeSheet :: query string 1 ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if (rowCount == 0) {
                    timeSheetVTO.setProjectNameSun1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameSun2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameSun3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameSun4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameSun5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalSun(resultSet.getDouble(6));
                    timeSheetVTO.setVacationSun(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeSun(resultSet.getDouble(8));
                    timeSheetVTO.setHolidaySun(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates1(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                    timeSheetVTO.setProject1key(resultSet.getInt(11));
                    timeSheetVTO.setProject2key(resultSet.getInt(12));
                    timeSheetVTO.setProject3key(resultSet.getInt(13));
                    timeSheetVTO.setProject4key(resultSet.getInt(14));
                    timeSheetVTO.setProject5key(resultSet.getInt(15));
                } else if (rowCount == 1) {
                    timeSheetVTO.setProjectNameMon1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameMon2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameMon3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameMon4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameMon5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalMon(resultSet.getDouble(6));
                    timeSheetVTO.setVacationMon(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeMon(resultSet.getDouble(8));
                    timeSheetVTO.setHolidayMon(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates2(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                } else if (rowCount == 2) {
                    timeSheetVTO.setProjectNameTue1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameTue2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameTue3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameTue4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameTue5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalTue(resultSet.getDouble(6));
                    timeSheetVTO.setVacationTue(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeTue(resultSet.getDouble(8));
                    timeSheetVTO.setHolidayTue(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates3(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                } else if (rowCount == 3) {
                    timeSheetVTO.setProjectNameWed1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameWed2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameWed3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameWed4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameWed5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalWed(resultSet.getDouble(6));
                    timeSheetVTO.setVacationWed(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeWed(resultSet.getDouble(8));
                    timeSheetVTO.setHolidayWed(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates4(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                } else if (rowCount == 4) {
                    timeSheetVTO.setProjectNameThu1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameThu2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameThu3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameThu4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameThu5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalThu(resultSet.getDouble(6));
                    timeSheetVTO.setVacationThu(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeThu(resultSet.getDouble(8));
                    timeSheetVTO.setHolidayThu(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates5(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                } else if (rowCount == 5) {
                    timeSheetVTO.setProjectNameFri1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameFri2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameFri3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameFri4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameFri5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalFri(resultSet.getDouble(6));
                    timeSheetVTO.setVacationFri(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeFri(resultSet.getDouble(8));
                    timeSheetVTO.setHolidayFri(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates6(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                } else if (rowCount == 6) {
                    timeSheetVTO.setProjectNameSat1(resultSet.getDouble(1));
                    timeSheetVTO.setProjectNameSat2(resultSet.getDouble(2));
                    timeSheetVTO.setProjectNameSat3(resultSet.getDouble(3));
                    timeSheetVTO.setProjectNameSat4(resultSet.getDouble(4));
                    timeSheetVTO.setProjectNameSat5(resultSet.getDouble(5));
                    timeSheetVTO.setInternalSat(resultSet.getDouble(6));
                    timeSheetVTO.setVacationSat(resultSet.getDouble(7));
                    timeSheetVTO.setComptimeSat(resultSet.getDouble(8));
                    timeSheetVTO.setHolidaySat(resultSet.getDouble(9));
                    timeSheetVTO.setWeeklyDates7(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate(10)));
                }
                rowCount++;
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
        System.out.println("********************UsrTimesheetServiceImpl :: getUserTimeSheet Method End*********************");
        return timeSheetVTO;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : approveRejectTimeSheet() used to Update the approve/reject
     * status of timesheet.
     *
     * *****************************************************************************
     */
    public int approveRejectTimeSheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: approveRejectTimeSheet Method Start*********************");
        Connection connection = null;
        String queryString = "";
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int isUpdated = 0;
        Timestamp approvedDate = com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (userTimeSheetAction.getTempVar() == 1) {
                System.out.println("approveRejectTimeSheet :: procedure name : sp_ApproveTimesheet");
                callableStatement = connection.prepareCall("{call sp_ApproveTimesheet(?,?,?,?,?,?)}");
                callableStatement.setInt(1, 3);
                callableStatement.setString(2, userTimeSheetAction.getComments());
                callableStatement.setInt(3, userTimeSheetAction.getUserSessionId());
                callableStatement.setInt(4, userTimeSheetAction.getUsr_id());
                callableStatement.setInt(5, userTimeSheetAction.getTimesheetid());
                callableStatement.registerOutParameter(6, java.sql.Types.INTEGER);
                callableStatement.execute();
                isUpdated = callableStatement.getInt(6);
            } else if (userTimeSheetAction.getTempVar() == 2) {
                queryString = "UPDATE usrtimeSheets SET reportsto1status=?,Comments=?,ApprovedDate=?,StatusChangedDate=?,reportsto1=? where usr_id='" + userTimeSheetAction.getUsr_id() + "' AND timeSheetid='" + userTimeSheetAction.getTimesheetid() + "'";
                System.out.println("approveRejectTimeSheet :: query string ------>" + queryString);
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, 10);
                preparedStatement.setString(2, userTimeSheetAction.getComments());
                preparedStatement.setTimestamp(3, approvedDate);
                preparedStatement.setTimestamp(4, approvedDate);
                preparedStatement.setInt(5, userTimeSheetAction.getUserSessionId());
                isUpdated = preparedStatement.executeUpdate();
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
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
        System.out.println("********************UsrTimesheetServiceImpl :: approveRejectTimeSheet Method End*********************");
        return isUpdated;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : editTimeSheet() used to Update the timesheet from edit page
     *
     *
     * *****************************************************************************
     */
    public int editTimeSheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: editTimeSheet Method Start*********************");
        Connection connection = null;
        String queryString = "";
        CallableStatement callableStatement = null;
        int isUpdated = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (connection != null) {
                System.out.println("checkTimeSheetExists :: procedure name : updateTimeSheets ");
                callableStatement = connection.prepareCall("{call updateTimeSheets(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, userTimeSheetAction.getUserSessionId());
                callableStatement.setInt(2, userTimeSheetAction.getTimesheetid());
                callableStatement.setDate(3, DateUtility.getInstance().getMysqlDate(userTimeSheetAction.getTimeSheetSubmittedDate().trim()));
                callableStatement.setDouble(4, userTimeSheetAction.getTotalBillHrs());
                callableStatement.setDouble(5, userTimeSheetAction.getProjectNameSun1());
                callableStatement.setDouble(6, userTimeSheetAction.getProjectNameMon1());
                callableStatement.setDouble(7, userTimeSheetAction.getProjectNameTue1());
                callableStatement.setDouble(8, userTimeSheetAction.getProjectNameWed1());
                callableStatement.setDouble(9, userTimeSheetAction.getProjectNameThu1());
                callableStatement.setDouble(10, userTimeSheetAction.getProjectNameFri1());
                callableStatement.setDouble(11, userTimeSheetAction.getProjectNameSat1());

                callableStatement.setDouble(12, userTimeSheetAction.getProjectNameSun2());
                callableStatement.setDouble(13, userTimeSheetAction.getProjectNameMon2());
                callableStatement.setDouble(14, userTimeSheetAction.getProjectNameTue2());
                callableStatement.setDouble(15, userTimeSheetAction.getProjectNameWed2());
                callableStatement.setDouble(16, userTimeSheetAction.getProjectNameThu2());
                callableStatement.setDouble(17, userTimeSheetAction.getProjectNameFri2());
                callableStatement.setDouble(18, userTimeSheetAction.getProjectNameSat2());

                callableStatement.setDouble(19, userTimeSheetAction.getProjectNameSun3());
                callableStatement.setDouble(20, userTimeSheetAction.getProjectNameMon3());
                callableStatement.setDouble(21, userTimeSheetAction.getProjectNameTue3());
                callableStatement.setDouble(22, userTimeSheetAction.getProjectNameWed3());
                callableStatement.setDouble(23, userTimeSheetAction.getProjectNameThu3());
                callableStatement.setDouble(24, userTimeSheetAction.getProjectNameFri3());
                callableStatement.setDouble(25, userTimeSheetAction.getProjectNameSat3());

                callableStatement.setDouble(26, userTimeSheetAction.getProjectNameSun4());
                callableStatement.setDouble(27, userTimeSheetAction.getProjectNameMon4());
                callableStatement.setDouble(28, userTimeSheetAction.getProjectNameTue4());
                callableStatement.setDouble(29, userTimeSheetAction.getProjectNameWed4());
                callableStatement.setDouble(30, userTimeSheetAction.getProjectNameThu4());
                callableStatement.setDouble(31, userTimeSheetAction.getProjectNameFri4());
                callableStatement.setDouble(32, userTimeSheetAction.getProjectNameSat4());

                callableStatement.setDouble(33, userTimeSheetAction.getProjectNameSun5());
                callableStatement.setDouble(34, userTimeSheetAction.getProjectNameMon5());
                callableStatement.setDouble(35, userTimeSheetAction.getProjectNameTue5());
                callableStatement.setDouble(36, userTimeSheetAction.getProjectNameWed5());
                callableStatement.setDouble(37, userTimeSheetAction.getProjectNameThu5());
                callableStatement.setDouble(38, userTimeSheetAction.getProjectNameFri5());
                callableStatement.setDouble(39, userTimeSheetAction.getProjectNameSat5());

                callableStatement.setDouble(40, userTimeSheetAction.getInternalSun());
                callableStatement.setDouble(41, userTimeSheetAction.getInternalMon());
                callableStatement.setDouble(42, userTimeSheetAction.getInternalTue());
                callableStatement.setDouble(43, userTimeSheetAction.getInternalWed());
                callableStatement.setDouble(44, userTimeSheetAction.getInternalThu());
                callableStatement.setDouble(45, userTimeSheetAction.getInternalFri());
                callableStatement.setDouble(46, userTimeSheetAction.getInternalSat());

                callableStatement.setDouble(47, userTimeSheetAction.getVacationSun());
                callableStatement.setDouble(48, userTimeSheetAction.getVacationMon());
                callableStatement.setDouble(49, userTimeSheetAction.getVacationTue());
                callableStatement.setDouble(50, userTimeSheetAction.getVacationWed());
                callableStatement.setDouble(51, userTimeSheetAction.getVacationThu());
                callableStatement.setDouble(52, userTimeSheetAction.getVacationFri());
                callableStatement.setDouble(53, userTimeSheetAction.getVacationSat());

                callableStatement.setDouble(54, userTimeSheetAction.getHolidaySun());
                callableStatement.setDouble(55, userTimeSheetAction.getHolidayMon());
                callableStatement.setDouble(56, userTimeSheetAction.getHolidayTue());
                callableStatement.setDouble(57, userTimeSheetAction.getHolidayWed());
                callableStatement.setDouble(58, userTimeSheetAction.getHolidayThu());
                callableStatement.setDouble(59, userTimeSheetAction.getHolidayFri());
                callableStatement.setDouble(60, userTimeSheetAction.getHolidaySat());

                callableStatement.setString(61, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates1().trim())));
                callableStatement.setString(62, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates2().trim())));
                callableStatement.setString(63, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates3().trim())));
                callableStatement.setString(64, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates4().trim())));
                callableStatement.setString(65, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates5().trim())));
                callableStatement.setString(66, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates6().trim())));
                callableStatement.setString(67, DateUtility.getInstance().convertDateToMySql(DateUtility.getInstance().convertStringToMySql(userTimeSheetAction.getWeeklyDates7().trim())));
                callableStatement.setString(68, userTimeSheetAction.getTimeSheetNotes().trim());

                callableStatement.setInt(69, userTimeSheetAction.getProject1key());
                callableStatement.setInt(70, userTimeSheetAction.getProject2key());
                callableStatement.setInt(71, userTimeSheetAction.getProject3key());
                callableStatement.setInt(72, userTimeSheetAction.getProject4key());
                callableStatement.setInt(73, userTimeSheetAction.getProject5key());
                callableStatement.setInt(74, userTimeSheetAction.getTempVar());

                callableStatement.registerOutParameter(75, java.sql.Types.INTEGER);
                callableStatement.execute();
                isUpdated = callableStatement.getInt(75);
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
        System.out.println("********************UsrTimesheetServiceImpl :: editTimeSheet Method End*********************");
        return isUpdated;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : checkTimeSheetExists() method is used to check whether the
     * timesheet is exist for that given date or not.
     *
     * *****************************************************************************
     */
    public String checkTimeSheetExists(List li, int empID) {
        System.out.println("********************UsrTimesheetServiceImpl :: checkTimeSheetExists Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String isTimeSheetExist = "";
        try {
            // convert the date to string
            String wstDate = DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql((String) li.get(0)));
            String wenDate = DateUtility.getInstance().convertDateToMySql1(DateUtility.getInstance().convertStringToMySql((String) li.get(1)));
            Date weekStarDate = DateUtility.getInstance().convertStringToMySql((String) li.get(0));
            Date currDate = DateUtility.getInstance().convertStringToMySql((String) li.get(2));
            connection = ConnectionProvider.getInstance().getConnection();
            if (connection != null) {
                statement = connection.createStatement();
                // select the data from vwTimeSheetList of DataBase.
                queryString = "SELECT *  FROM vwtimesheetlist WHERE EmpId=" + empID + " AND DateStart='" + wstDate + "' AND DateEnd='" + wenDate + "'";
                resultSet = statement.executeQuery(queryString);
                System.out.println("checkTimeSheetExists :: query string ------>" + queryString);
                if (resultSet.next()) {
                    isTimeSheetExist = "exist";
                } else if (currDate.before(weekStarDate)) {
                    isTimeSheetExist = "notallow";
                } else {
                    isTimeSheetExist = "allow";
                }
            } //if
        } // try
        catch (Exception ex) {
            ex.printStackTrace();
        } // catch
        finally {
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
                ex.printStackTrace();
            }
        }
        System.out.println("********************UsrTimesheetServiceImpl :: checkTimeSheetExists Method End*********************");
        return isTimeSheetExist;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : getweekStartAndEndDays() method is used to
     *
     * *****************************************************************************
     */
    public List getweekStartAndEndDays(Calendar cal) {
        System.out.println("********************UsrTimesheetServiceImpl :: getweekStartAndEndDays Method Start*********************");
        /* used to store the totla weekdays. */
        List daysList = new ArrayList();
        /* Used for Storing the Week Start Date */
        String wstDate = null;
        /* Used for Storing the Week End Date */
        String wenDate = null;
        /* used for the current date */
        String curntDate = null;
        /* for stroring the weekdays */
        String[] weekDays = new String[7];
        /* to get the day of week */
        int w = cal.get(Calendar.DAY_OF_WEEK);

        /* if its sunday then the index is 0 other then sunday then minus the index */
        if (w == 1) {
            cal.add(Calendar.DATE, 0);

        } else if (w == 2) {
            cal.add(Calendar.DATE, -1);

        } else if (w == 3) {
            cal.add(Calendar.DATE, -2);

        } else if (w == 4) {
            cal.add(Calendar.DATE, -3);

        } else if (w == 5) {
            cal.add(Calendar.DATE, -4);

        } else if (w == 6) {
            cal.add(Calendar.DATE, -5);

        } else if (w == 7) {
            cal.add(Calendar.DATE, -6);

        }
        /* for generating the month/day sequence of the week */
        int zeroForMon = 0; // if month is single digit then Zero is append to left of that digit
        int zeroForDay = 0; // if day is single digit then Zero is append to left of that digit
        for (int index = 0; index < 7; index++) {
            /* for the purpose of concatinating 0 before the day and month */
            zeroForMon = (cal.get(Calendar.MONTH) + 1);
            zeroForDay = cal.get(Calendar.DAY_OF_MONTH);
            if (zeroForMon < 10 && zeroForDay < 10) {
                weekDays[index] = "0" + (cal.get(Calendar.MONTH) + 1) + "/0" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.YEAR));
            } else if (zeroForMon > 9 && zeroForDay < 10) {
                weekDays[index] = (cal.get(Calendar.MONTH) + 1) + "/0" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.YEAR));
            } else if (zeroForMon < 10 && zeroForDay > 9) {
                weekDays[index] = "0" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.YEAR));
            } else {
                weekDays[index] = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.YEAR));
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }// End for loop
        wstDate = weekDays[0];  // storing the week startday
        wenDate = weekDays[6]; // storing the week endday
        /* current date */
        Calendar currDay = Calendar.getInstance();
        int calendarMonth = currDay.get(Calendar.MONTH) + 1;
        int calendarDay = currDay.get(Calendar.DAY_OF_MONTH);
        if (calendarMonth != 10 && calendarMonth != 11 && calendarMonth != 12) {
            if (calendarDay < 10) {
                curntDate = "0" + (currDay.get(Calendar.MONTH) + 1) + "/0" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);
            } else {
                curntDate = "0" + (currDay.get(Calendar.MONTH) + 1) + "/" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);
            }
        } else {
            if (calendarDay < 10) {
                curntDate = (currDay.get(Calendar.MONTH) + 1) + "/0" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);
            } else {
                curntDate = (currDay.get(Calendar.MONTH) + 1) + "/" + currDay.get(Calendar.DAY_OF_MONTH) + "/" + currDay.get(Calendar.YEAR);
            }
        }
        daysList.add(wstDate);
        daysList.add(wenDate);
        daysList.add(curntDate);
        daysList.add(weekDays);
        System.out.println("********************UsrTimesheetServiceImpl :: getweekStartAndEndDays Method End*********************");
        return daysList;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Mani Kanta Eeralla<meeralla@miraclesoft.com>
     *
     * ForUse : getWeekDaysBean() method is used to
     *
     * *****************************************************************************
     */
    public TimesheetVTO getWeekDaysBean(List li) {
        System.out.println("********************UsrTimesheetServiceImpl :: getWeekDaysBean Method Start*********************");
        TimesheetVTO timeSheetVTO = new TimesheetVTO();

        /* Used for Storing the Week Start Date */
        timeSheetVTO.setTimeSheetStartDate((String) li.get(0));

        /* Used for Storing the Week End Date */
        timeSheetVTO.setTimeSheetEndDate((String) li.get(1));

        /* Used for Storing the Week Submitted Date */
        timeSheetVTO.setSubmittedDate((String) li.get(2));

        /* for storing the seven weekdays */
        String[] weekDaysSequence = (String[]) li.get(3);

        timeSheetVTO.setWeeklyDates1(weekDaysSequence[0]);
        timeSheetVTO.setWeeklyDates2(weekDaysSequence[1]);
        timeSheetVTO.setWeeklyDates3(weekDaysSequence[2]);
        timeSheetVTO.setWeeklyDates4(weekDaysSequence[3]);
        timeSheetVTO.setWeeklyDates5(weekDaysSequence[4]);
        timeSheetVTO.setWeeklyDates6(weekDaysSequence[5]);
        timeSheetVTO.setWeeklyDates7(weekDaysSequence[6]);

        System.out.println("********************UsrTimesheetServiceImpl :: getWeekDaysBean Method End*********************");
        return timeSheetVTO; //  return the bean reference
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTeamTimeSheetListDefault() method is used to
     *
     * *****************************************************************************
     */
    public List getTeamTimeSheetListDefault(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getTeamTimeSheetListDefault Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        String tmMember = "";
        try {
            int id = usrTimeSheetAction.getUserSessionId();
            Map map = usrTimeSheetAction.getTeamMembersList();
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
            if ("".equals(retrunValue)) {
                retrunValue = null;
            }
            queryString = "SELECT EmpId,EmpName,TimesheetId,TotalHrs,ReportsTo,CurStatus,DateStart,DateEnd,SubmittedDate,ApprovedDate FROM vwtimesheetlist WHERE 1=1 ";
            if (usrTimeSheetAction.getStartDate() != null && usrTimeSheetAction.getStartDate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getStartDate());
                queryString = queryString + " and DateStart >= '" + startDate + "'";
            }
            if (usrTimeSheetAction.getEndDate() != null && usrTimeSheetAction.getEndDate().toString().isEmpty() == false) {
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getEndDate());
                queryString = queryString + " and DateEnd <= '" + endDate + "'";
            }
            queryString = queryString + " and EmpId in (" + retrunValue + ") ORDER BY TimesheetId DESC LIMIT 20 ";
            System.out.println("getTeamTimeSheetListDefault :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setUsr_id(resultSet.getInt("EmpId"));
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setEmpName(resultSet.getString("EmpName"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setReportsto1(dsdp.getFnameandLnamebyUserid(resultSet.getInt("ReportsTo")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                timesheetVTO.setTotalBillHrs(resultSet.getDouble("TotalHrs"));
                timesheetList.add(timesheetVTO);
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
        System.out.println("********************UsrTimesheetServiceImpl :: getTeamTimeSheetListDefault Method End*********************");
        return timesheetList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : Divya Gandreti <dgandreti@miraclesoft.com>
     *
     * ForUse : getAllTimeSheets() method is used to
     *
     * *****************************************************************************
     */
    public List getAllTimeSheets(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getAllTimeSheets Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        try {
            queryString = "SELECT acc_Id,vendorName,customerName,orgId,EmpId,EmpName,TimesheetId,TotalHrs,ReportsTo,CurStatus,DateStart,DateEnd,SubmittedDate,"
                    + "ApprovedDate FROM vwopttimesheets WHERE 1=1 and memStatus like 'Active' ";
            if (userTimeSheetAction.getStartDate() != null && userTimeSheetAction.getStartDate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userTimeSheetAction.getStartDate());
                queryString = queryString + " and DateStart >= '" + startDate + "'";
            }
            if (userTimeSheetAction.getEndDate() != null && userTimeSheetAction.getEndDate().toString().isEmpty() == false) {
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userTimeSheetAction.getEndDate());
                queryString = queryString + " and DateEnd <= '" + endDate + "'";
            }
            if ("7".equals(userTimeSheetAction.getPrimaryRole())) {
                queryString = queryString + " and acc_Id=" + userTimeSheetAction.getSessionOrgId()
                        + " ORDER BY SubmittedDate,EmpName DESC LIMIT 20";
            } else {
                queryString = queryString + " and orgId=" + userTimeSheetAction.getSessionOrgId()
                        + " ORDER BY SubmittedDate,EmpName DESC LIMIT 20";
            }
            System.out.println("getAllTimeSheets :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setUsr_id(resultSet.getInt("EmpId"));
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setEmpName(resultSet.getString("EmpName"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                timesheetVTO.setCustomerName(resultSet.getString("customerName"));
                timesheetVTO.setVendorName(resultSet.getString("vendorName"));
                timesheetVTO.setTotalhrs(resultSet.getDouble("TotalHrs"));
                timesheetList.add(timesheetVTO);
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
        System.out.println("********************UsrTimesheetServiceImpl :: getAllTimeSheets Method End*********************");
        return timesheetList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : Divya Gandreti <dgandreti@miraclesoft.com>
     *
     * ForUse : getAllTimeSheetsSearch() method is used to
     *
     * *****************************************************************************
     */
    public List getAllTimeSheetsSearch(UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException {
        System.out.println("********************UsrTimesheetServiceImpl :: getAllTimeSheetsSearch Method Start*********************");
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<TimesheetVTO> timesheetList = new ArrayList<TimesheetVTO>();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        String status = "";
        try {
            queryString = "SELECT acc_Id,vendorName,customerName,orgId,EmpId,EmpName,TimesheetId,TotalHrs,ReportsTo,CurStatus,"
                    + "DateStart,DateEnd,SubmittedDate,ApprovedDate FROM vwopttimesheets WHERE 1=1";
            if (usrTimeSheetAction.getStartDate() != null && usrTimeSheetAction.getStartDate().toString().isEmpty() == false) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getStartDate());
                queryString = queryString + " and DateStart >= '" + startDate + "'";
            }
            if (usrTimeSheetAction.getEndDate() != null && usrTimeSheetAction.getEndDate().toString().isEmpty() == false) {
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(usrTimeSheetAction.getEndDate());
                queryString = queryString + " and DateEnd <= '" + endDate + "'";
            }
            if (!"-1".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                if ("CA".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "1";
                } else if ("SU".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "2";
                } else if ("AP".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "3";
                } else if ("DA".equalsIgnoreCase(usrTimeSheetAction.getTmstatus())) {
                    status = "10";
                }
                queryString = queryString + " and CurStatus = '" + status + "'";
            }
            if (!"".equalsIgnoreCase(usrTimeSheetAction.getTmmember())) {
                queryString = queryString + "and EmpName like '%" + usrTimeSheetAction.getTmmember() + "%'";
            }
            if (!"-1".equalsIgnoreCase(usrTimeSheetAction.getStatus())) {
                queryString = queryString + "and memStatus = '" + usrTimeSheetAction.getStatus() + "'";
            } else {
                queryString = queryString + " and memStatus = 'Active' ";
            }
            if ("7".equals(usrTimeSheetAction.getPrimaryRole())) {
                if (!"".equalsIgnoreCase(usrTimeSheetAction.getVendorName())) {
                    queryString = queryString + "and vendorName like '%" + usrTimeSheetAction.getVendorName() + "%'";
                }
                queryString = queryString + " and acc_Id=" + usrTimeSheetAction.getSessionOrgId()
                        + " ORDER BY SubmittedDate,EmpName DESC LIMIT 20";
            } else {
                if (!"".equalsIgnoreCase(usrTimeSheetAction.getCustomerName())) {
                    queryString = queryString + "and customerName like '%" + usrTimeSheetAction.getCustomerName() + "%'";
                }
                queryString = queryString + " and orgId=" + usrTimeSheetAction.getSessionOrgId()
                        + " ORDER BY SubmittedDate,EmpName DESC LIMIT 20";
            }
            System.out.println("getAllTimeSheetsSearch :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                TimesheetVTO timesheetVTO = new TimesheetVTO();
                timesheetVTO.setUsr_id(resultSet.getInt("EmpId"));
                timesheetVTO.setTimesheetid(resultSet.getInt("TimesheetId"));
                timesheetVTO.setEmpName(resultSet.getString("EmpName"));
                timesheetVTO.setDateStart(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateStart")));
                timesheetVTO.setDateEnd(dateUtility.convertDateToViewInDashformat(resultSet.getDate("DateEnd")));
                timesheetVTO.setReportsto1status(getTimesheetStatusByTmsheetId(resultSet.getInt("CurStatus")));
                timesheetVTO.setSubmittedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("SubmittedDate")));
                timesheetVTO.setApprovedDate(dateUtility.convertDateToViewInDashformat(resultSet.getDate("ApprovedDate")));
                timesheetVTO.setCustomerName(resultSet.getString("customerName"));
                timesheetVTO.setVendorName(resultSet.getString("vendorName"));
                timesheetVTO.setTotalhrs(resultSet.getDouble("TotalHrs"));
                timesheetList.add(timesheetVTO);
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
        System.out.println("********************UsrTimesheetServiceImpl :: getAllTimeSheetsSearch Method End*********************");
        return timesheetList;
    }
}
