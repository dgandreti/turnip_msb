/*
 * AjaxHandlerServiceImpl.java
 *
 * Created on June 11, 2008, 12:57 AM
 *greensheetListSearch
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.msp.usrajax;

import com.mss.msp.onlineexam.QuestionVTO;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;

import com.mss.msp.util.DateUtility;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.mss.msp.usersdata.UserAddress;
import com.mss.msp.usersdata.UserVTO;
import com.mss.msp.usr.leaves.LeavesVTO;
import com.mss.msp.usr.leaves.UserLeavesAction;
import com.mss.msp.util.HibernateServiceLocator;
import java.util.LinkedHashMap;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author miracle
 */
public class UserAjaxHandlerServiceImpl implements UserAjaxHandlerService {

    /**
     *
     * Creating a reference variable for Connection
     */
    private Connection connection = null;
    /**
     *
     * Creating a reference variable for preparedStatement
     */
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private CallableStatement callableStatement = null;
    /**
     *
     * Creating a reference variable for Resultset
     */
    private ResultSet resultSet = null;
    /**
     *
     * Creating a reference variable for String Buffer
     */
    private StringBuffer stringBuffer;
    /**
     *
     * Creating a String queryString used to store SQL Query
     */
    private String queryString = "";
    /**
     *
     * Creating a boolean flag to return true or false
     */
    private boolean flag;
    /**
     *
     * Creating a String noRecords to replace spaces in Ajax response
     */
    private String noRecords = "no records";

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getStates() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */
    public String getStates(String country) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getStates Method Start*********************");
        stringBuffer = new StringBuffer();
        queryString = null;
        queryString = "SELECT StateName,CountryName FROM vwCountryState where CountryName = " + "'" + country + "'";
        try {
            System.out.println("getStates :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            stringBuffer.append("<xml version=\"1.0\">");
            stringBuffer.append("<COUNTRY countryName=\"" + country + "\">");
            stringBuffer.append("<STATE>--Please Select--</STATE>");
            while (resultSet.next()) {
                stringBuffer.append("<STATE>" + resultSet.getString("StateName") + "</STATE>");
            }
            stringBuffer.append("</COUNTRY>");
            stringBuffer.append("</xml>");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: getStates Method End*********************");
        return stringBuffer.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addForGotPasswordDetails() method is used to insert
     *
     * *****************************************************************************
     */
    public int addForGotPasswordDetails(String emailId, String urlLink, String key) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: addForGotPasswordDetails Method Start*********************");
        int updatedRows = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "insert into forgotpasswordlink (email_id,url_link,code) values(?,?,?)";
            System.out.println("addForGotPasswordDetails :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, urlLink);
            preparedStatement.setString(3, key);
            updatedRows = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: addForGotPasswordDetails Method End*********************");
        return updatedRows;
    }

    /**
     * *****************************************************************************
     * Date : 03/10/2015
     *
     * Author :
     *
     * ForUse : doUserRegister() method is used to insert registration details
     * of a User.
     *
     * *****************************************************************************
     */
    public int doUserRegister(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: doUserRegister Method Start*********************");
        int updatedRows = 0;
        boolean isExceute = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("doUserRegister :: procedure name : spUsrResgistration ");
            callableStatement = connection.prepareCall("{CALL spUsrResgistration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, userAjaxHandlerAction.getLoginId());
            callableStatement.setString(2, userAjaxHandlerAction.getFirstName());
            callableStatement.setString(3, userAjaxHandlerAction.getMiddleName());
            callableStatement.setString(4, userAjaxHandlerAction.getLastName());
            callableStatement.setString(5, userAjaxHandlerAction.getFilePath());
            if (userAjaxHandlerAction.getGender().equalsIgnoreCase("male")) {
                callableStatement.setString(6, "M");
            } else {
                callableStatement.setString(6, "F");
            }
            if (userAjaxHandlerAction.getMaritalStatus().equalsIgnoreCase("single")) {
                callableStatement.setString(7, "S");
            } else {
                callableStatement.setString(7, "M");
            }

            callableStatement.setDate(8, DateUtility.getInstance().getMysqlDate(userAjaxHandlerAction.getDob()));
            callableStatement.setString(9, userAjaxHandlerAction.getPhone());
            callableStatement.setString(10, userAjaxHandlerAction.getOfficeAddress1());
            callableStatement.setString(11, userAjaxHandlerAction.getOfficeAddress2());
            callableStatement.setString(12, userAjaxHandlerAction.getOfficeCity());
            callableStatement.setString(13, userAjaxHandlerAction.getOfficeState());
            callableStatement.setString(14, userAjaxHandlerAction.getZip());
            callableStatement.setString(15, userAjaxHandlerAction.getOfficeCountry());
            callableStatement.setString(16, userAjaxHandlerAction.getPhone());
            callableStatement.setString(17, userAjaxHandlerAction.getRegType());
            callableStatement.setString(18, "Registered");
            callableStatement.setString(19, userAjaxHandlerAction.getLivingCountry());
            callableStatement.setInt(20, DataSourceDataProvider.getInstance().getOrgIdByEmailExt(userAjaxHandlerAction.getLoginId()));
            callableStatement.registerOutParameter(21, Types.INTEGER);
            isExceute = callableStatement.execute();
            updatedRows = callableStatement.getInt(21);
            if (updatedRows > 0) {
                try {
                    addmailling(userAjaxHandlerAction.getLoginId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: doUserRegister Method End*********************");
        return updatedRows;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getEmployeeDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getEmployeeDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmployeeDetails Method Start*********************");
        boolean isGetting = false;
        stringBuffer = new StringBuffer();
        if (userAjaxHandlerAction.getTechReview().equalsIgnoreCase("TR")) {
            queryString = "SELECT CONCAT(TRIM(u.first_name),'.',TRIM(u.last_name)) AS FullName,u.usr_id FROM users u LEFT OUTER JOIN acc_requirements ar ON(ar.acc_id=u.org_id) LEFT OUTER JOIN usr_miscellaneous m ON(m.usr_id=u.usr_id) WHERE (last_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%' OR first_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%') AND ar.requirement_id=" + userAjaxHandlerAction.getRequirementId() + " AND u.cur_status='Active' LIMIT 30";
        } else if (userAjaxHandlerAction.getProjectID() == 0) {
            queryString = " SELECT CONCAT(TRIM(u.first_name),'.',TRIM(u.last_name)) AS FullName,u.usr_id FROM users u "
                    + " LEFT OUTER JOIN usr_roles p ON(p.usr_id=u.usr_id)LEFT OUTER JOIN "
                    + " csrorgrel cor ON(cor.csr_id=u.usr_id) WHERE role_id=1"
                    + " AND cor.org_id=" + userAjaxHandlerAction.getSessionOrgId() + " AND cor.STATUS='Active'"
                    + " AND (last_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%'"
                    + " OR first_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%')"
                    + " AND cur_status='Active' LIMIT 30";
        } else {
            queryString = "SELECT CONCAT(TRIM(u.first_name),'.',TRIM(u.last_name)) AS FullName,u.usr_id FROM users u "
                    + "LEFT OUTER JOIN prj_sub_prjteam pt ON(pt.usr_id=u.usr_id) "
                    + "WHERE (last_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%' OR first_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%') "
                    + "and cur_status='Active' "
                    + "AND pt.sub_project_id=" + userAjaxHandlerAction.getProjectID() + " "
                    + "AND org_id=" + userAjaxHandlerAction.getSessionOrgId() + " LIMIT 30";
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getEmployeeDetails :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            stringBuffer.append("<xml version=\"1.0\">");
            stringBuffer.append("<EMPLOYEES>");
            while (resultSet.next()) {
                stringBuffer.append("<EMPLOYEE><VALID>true</VALID>");
                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    stringBuffer.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    stringBuffer.append("<NAME>" + title + "</NAME>");
                }
                stringBuffer.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                stringBuffer.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }
            if (!isGetting) {
                isGetting = false;
                stringBuffer.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            stringBuffer.append("</EMPLOYEES>");
            stringBuffer.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmployeeDetails Method End*********************");
        return stringBuffer.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getEmpAddressDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getEmpAddressDetails(int empId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpAddressDetails Method Start*********************");
        queryString = "";
        String resultString = "", aflag = "";
        UserAddress userAddress = new UserAddress();
        try {
            queryString = " SELECT address_flag, address, address2, city, state, zip, country,phone "
                    + "  FROM usr_address  "
                    + "WHERE usr_id ='" + empId + "' and status='Active'";
            System.out.println("getEmpAddressDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if (resultSet.getString("address_flag").equalsIgnoreCase("pc")) {
                    resultString = resultSet.getString("address_flag") + '#' + resultSet.getString("address") + '&' + resultSet.getString("address2") + '&' + resultSet.getString("city") + '&' + resultSet.getString("state") + '&' + resultSet.getString("zip") + '&' + resultSet.getString("country") + '&' + resultSet.getString("phone");
                    return resultString;
                }
                if (resultSet.getString("address_flag").equalsIgnoreCase("p")) {
                    resultString = "false" + '#' + resultSet.getString("address") + '&' + resultSet.getString("address2") + '&' + resultSet.getString("city") + '&' + resultSet.getString("state") + '&' + resultSet.getString("zip") + '&' + resultSet.getString("country") + '&' + resultSet.getString("phone");
                }
                if (resultSet.getString("address_flag").equalsIgnoreCase("c")) {
                    resultString += '#' + resultSet.getString("address") + '&' + resultSet.getString("address2") + '&' + resultSet.getString("city") + '&' + resultSet.getString("state") + '&' + resultSet.getString("zip") + '&' + resultSet.getString("country") + '&' + resultSet.getString("phone");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpAddressDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : setEmpAddressDetails() method is used to
     *
     * *****************************************************************************
     */
    /*these methods for contact info updation created by ramakrishna  start*/
    public int setEmpAddressDetails(int empId, String address, String address2, String city, String state, String zip, String country, String phone, String address_flag) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: setEmpAddressDetails Method Start*********************");
        String queryString2 = "";
        try {
            if (address_flag.equalsIgnoreCase("p")) {
                queryString = "update usr_address set address_flag='p',address='" + address + "',city='" + city + "',state='" + state + "',zip='" + zip + "',country='" + country + "',phone='" + phone + "',address2='" + address2 + "',status='Active' where usr_id='" + empId + "' and address_flag not in('c')";
                queryString2 = "update usr_address set status='Active' where usr_id='" + empId + "' and address_flag='c'";
            } else {
                queryString = "update usr_address set address_flag='pc',address='" + address + "',city='" + city + "',state='" + state + "',zip='" + zip + "',country='" + country + "',phone='" + phone + "',address2='" + address2 + "',status='Active' where usr_id='" + empId + "' and address_flag not in('c')";
                queryString2 = "update usr_address set status='In-Active' where usr_id='" + empId + "' and address_flag='c'";
            }
            System.out.println("setEmpAddressDetails :: query string ------>" + queryString);
            System.out.println("setEmpAddressDetails :: query string2 ------>" + queryString2);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            int updateResult = statement.executeUpdate(queryString);
            int updateResult2 = 0;
            if (!queryString2.isEmpty()) {
                updateResult2 = statement.executeUpdate(queryString2);
            }
            return updateResult;
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: setEmpAddressDetails Method End*********************");
        return 0;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : setEmpCAddressDetails() method is used to
     *
     * *****************************************************************************
     */
    public int setEmpCAddressDetails(int empId, String address, String address2, String city, String state, String zip, String country, String phone, String address_flag) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: setEmpCAddressDetails Method Start*********************");
        String queryString2 = "", queryStringInsert = "";
        String resultString = "";
        try {
            queryString = "update usr_address set address_flag='" + address_flag + "',address='" + address + "',city='" + city + "',state='" + state + "',zip='" + zip + "',country='" + country + "',phone='" + phone + "',address2='" + address2 + "',status='Active' where usr_id='" + empId + "' and address_flag='c'";
            queryString2 = "update usr_address set address_flag='p' where usr_id=" + empId + " and address_flag='pc'";
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("setEmpCAddressDetails :: query string ------>" + queryString);
            System.out.println("setEmpCAddressDetails :: query string2 ------>" + queryString2);
            statement = connection.createStatement();
            int updateResult = 0, updateResult2 = 0, updateResultInsert = 0;
            updateResult = statement.executeUpdate(queryString);
            if (updateResult == 0) {
                queryStringInsert = "insert into usr_address(usr_id,address_flag,address,city,state,zip,country,phone,address2,status) values(" + empId + ",'" + address_flag + "','" + address + "','" + city + "','" + state + "','" + zip + "','" + country + "','" + phone + "','" + address2 + "','Active')";
            }
            if (queryStringInsert.isEmpty()) {
                updateResult2 = statement.executeUpdate(queryString2);
            } else {
                updateResultInsert = statement.executeUpdate(queryStringInsert);
                updateResult2 = statement.executeUpdate(queryString2);
            }
            return updateResult;
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: setEmpCAddressDetails Method End*********************");
        return 0;
    }

    /* End , impl added by triveni for Skill Details */

    /*
     * this is for using insert data into email logger table
     *
     * added by praveen<pkatru@miraclesoft.com>
     * 
     */
    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addmailling() method is used to
     *
     * *****************************************************************************
     */
    public int addmailling(String loginId) throws SQLException, ServiceLocatorException {

        System.out.println("********************UserAjaxHandlerServiceImpl :: addmailling Method Start*********************");
        String toAdd = "", bodyContent = "", bcc = "", cc = "", Subject = "";
        String FromAdd = Properties.getProperty("MSB.from");
        toAdd = loginId;
        Subject = "Registration_Request";
        StringBuilder htmlText = new StringBuilder();
        htmlText.append("<html>");
        htmlText.append("<body>");
        htmlText.append("<table align='center'>");
        htmlText.append("<tr style='background:#99FF33;height:40px;width:100%;'>");
        htmlText.append("<td>");
        htmlText.append("<font color='white' size='4' face='Arial'>");
        htmlText.append("<p>MSB Reset Password</p>");
        htmlText.append("</font>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td>");
        htmlText.append("<table style='background:#FFFFCC;width:100%;'>");
        htmlText.append("<tr>");
        htmlText.append("<td>");
        htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
        htmlText.append("<p>Hello,</p><br/>");
        htmlText.append("<p>We recieved a request to the password associated with this email address .</p>");
        htmlText.append("<p>If you made this request,please follow the instructions below.</p><br/>");
        htmlText.append("<p>If you did not have your password reset, you can safely ignore this email.</p>");
        htmlText.append("<p>We assure you that your customer account is safe.</p>");
        htmlText.append("</font>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</table>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td>");
        htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>'");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("</table>");
        htmlText.append("</body>");
        htmlText.append("</html>");
        htmlText.append("</body>");
        htmlText.append("</html>");
        bodyContent = htmlText.toString();
        int ReturnStatus = new com.mss.msp.util.MailManager().doaddemailLog(FromAdd, toAdd, bcc, cc, Subject, bodyContent, 0);
        System.out.println("********************UserAjaxHandlerServiceImpl :: addmailling Method End*********************");
        return ReturnStatus;
    }

    // new method for role submit
    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : roleSubmit() method is used to
     *
     * *****************************************************************************
     */
    public String roleSubmit(HttpServletRequest httpServletRequest, int roleId, int orgId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: roleSubmit Method Start*********************");
        String action = "";
        String resultString = "";
        try {

            queryString = " SELECT action_name from home_redirect_action where org_id=" + orgId + " and primaryrole=" + roleId;
            System.out.println("roleSubmit :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                action = resultSet.getString("action_name");
            }
            httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PRIMARYROLE, roleId);
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: roleSubmit Method End*********************");
        return action;
    }

    /* Start, Add task types by Aklakh */
    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTypesOfTask() method is used to
     *
     * *****************************************************************************
     */
    public String getTypesOfTask(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getTypesOfTask Method Start*********************");
        queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT p.proj_name,p.project_id FROM acc_projects p "
                    + "LEFT OUTER JOIN prj_sub_prjteam t ON(t.sub_project_id=p.project_id) "
                    + "WHERE t.usr_id=" + userAjaxHandlerAction.getUserSessionId() + "";
            System.out.println("getTypesOfTask :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("project_id") + "#" + resultSet.getString("proj_name") + "^";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getTypesOfTask Method End*********************");
        return resultString;
    }
    /*end aklakh code*/

    /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : getRelatedToNames() method is used to get task details and
     * appends on TaskEdit Screen.
     *
     * *****************************************************************************
     */
    public String getRelatedToNames(UserAjaxHandlerAction userAjaxHandlerAction, String usrType) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getRelatedToNames Method Start*********************");

        String resultString = "";
        try {
            if (userAjaxHandlerAction.getTask_related_to().equalsIgnoreCase("1")) {
                queryString = " SELECT u.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
                        + "FROM users u "
                        + "LEFT OUTER JOIN prj_sub_prjteam p ON(p.usr_id=u.usr_id) "
                        + "WHERE p.sub_project_id=? AND p.current_status='Active'";
                connection = ConnectionProvider.getInstance().getConnection();
                System.out.println("getRelatedToNames :: query string ------>" + queryString);
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, userAjaxHandlerAction.getTaskType());
            }
            if (userAjaxHandlerAction.getTask_related_to().equalsIgnoreCase("2")) {
                if ("VC".equals(usrType)) {
                    queryString = " SELECT u.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES "
                            + "FROM users u "
                            + "LEFT OUTER JOIN usr_roles p ON(p.usr_id=u.usr_id) "
                            + "WHERE role_id=1";
                } else {
                    queryString = " SELECT u.usr_id,CONCAT(u.first_name,'.',u.last_name) AS NAMES FROM users u "
                            + " LEFT OUTER JOIN usr_roles p ON(p.usr_id=u.usr_id)"
                            + "LEFT OUTER JOIN csrorgrel cor ON(cor.csr_id=u.usr_id)"
                            + "WHERE role_id=1 AND cor.org_id=" + userAjaxHandlerAction.getOrgId() + " AND cor.STATUS='Active'";
                }
                connection = ConnectionProvider.getInstance().getConnection();
                System.out.println("getRelatedToNames :: query string 2------>" + queryString);
                preparedStatement = connection.prepareStatement(queryString);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultString += resultSet.getInt("usr_id") + "#" + resultSet.getString("NAMES") + "^";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getRelatedToNames Method End*********************");
        return resultString;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAttachment() method is used to
     *
     * *****************************************************************************
     */
    public String getAttachment(UserAjaxHandlerAction userAjaxHandlerAction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getAttachment Method Start*********************");

        String resultString = "";
        try {
            queryString = " SELECT id,attachment_name,attachment_path FROM task_attachments WHERE task_id=? AND STATUS='Active'";
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getAttachment :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, userAjaxHandlerAction.getTaskid());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultString += resultSet.getInt("id") + "#" + resultSet.getString("attachment_name") + "#" + resultSet.getString("attachment_path") + "^";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getAttachment Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doDeactiveAttachment() method is used to
     *
     * *****************************************************************************
     */
    public int doDeactiveAttachment(UserAjaxHandlerAction userAjaxHandlerAction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: doDeactiveAttachment Method Start*********************");

        String resultString = "";
        int queryResult = 0;
        try {
            queryString = " UPDATE task_attachments SET STATUS='In-Active',modified_by=?,modified_date=? WHERE id=?";
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("doDeactiveAttachment :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, userAjaxHandlerAction.getUserSessionId());
            preparedStatement.setTimestamp(2, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setString(3, userAjaxHandlerAction.getTaskAttachId());
            queryResult = preparedStatement.executeUpdate();
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: doDeactiveAttachment Method End*********************");
        return queryResult;
    }

    /**
     * *****************************************************************************
     * Date : April 28 2015
     *
     * Author : KIRAN <addodi@miraclesoft.com>
     *
     * getInsertedLeaveDetails() method can be used to add the leave by the user
     * And returns Result
     * *****************************************************************************
     */
    public int getInsertedLeaveDetails(int userSessionId, UserAjaxHandlerAction userajaxhandleraction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getInsertedLeaveDetails Method Start*********************");

        String resultString = "";
        int result = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        int reportingPersonId = 0;
        //reporting person change logic
        try {
            startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userajaxhandleraction.getFromleave());
            endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(userajaxhandleraction.getToleave());
            queryString = " INSERT into usr_leaves(usr_id, leave_startdate,leave_enddate,leave_status,leave_type,leave_reason,reports_to,created_by)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getInsertedLeaveDetails :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, userSessionId);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setString(4, userajaxhandleraction.getLeavestatus());
            preparedStatement.setString(5, userajaxhandleraction.getLeavetype());
            preparedStatement.setString(6, userajaxhandleraction.getReason());
            preparedStatement.setInt(7, reportingPersonId);
            preparedStatement.setInt(8, userSessionId);
            result = preparedStatement.executeUpdate();
            if (result > 0) {
                return result;
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getInsertedLeaveDetails Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getStatesOfCountry() method is used to get the state names and
     * id based on the country id.
     *
     * *****************************************************************************
     */
    public String getStatesOfCountry(int countryId) throws ServiceLocatorException {

        System.out.println("********************UserAjaxHandlerServiceImpl :: getStatesOfCountry Method Start*********************");
        String resultString = "";
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            String hqlQuery = "select id,name from State  WHERE countryId=:countryid";
            if (countryId <= 0) {
                countryId = 1;
            }
            Query query = session.createQuery(hqlQuery);
            query.setInteger("countryid", countryId);
            List list = query.list();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                resultString += o[0] + "#" + o[1] + "^";
            }
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } finally {
            // Closing hibernate session
            if (session != null) {
                session.close();
                if (session.isOpen()) {
                    try {
                        session.flush();
                        session.close();
                        session = null;
                    } catch (HibernateException he) {
                        he.printStackTrace();
                    }
                }
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: getStatesOfCountry Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 10/may/2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : getLeavesListDetails() getting leaves details of user.
     *
     * *****************************************************************************
     */
    public String getLeavesListDetails(UserAjaxHandlerAction aThis) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getLeavesListDetails Method Start*********************");
        ArrayList<LeavesVTO> leaveslist = new ArrayList<LeavesVTO>();
        String resultString = " ";
        UserLeavesAction userLeavesAction = new UserLeavesAction();
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
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
            queryString = queryString + " and usr_id=" + aThis.getUserSessionId() + " LIMIT 20";
            System.out.println("getLeavesListDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                LeavesVTO leavesVTO = new LeavesVTO();
                leavesVTO.setLeaveid(resultSet.getInt("leave_id"));
                leavesVTO.setLeavestartdate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_sdate")));
                leavesVTO.setLeaveenddate(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_edate")));
                String status = resultSet.getString("leave_status");
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
                resultString = resultString + resultSet.getInt("leave_id") + "|" + dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_sdate")) + "|" + dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("l_edate")) + "|" + leavesVTO.getLeavestatus() + "|" + leavesVTO.getLeavetype() + "|" + leavesVTO.getReportsto() + "|" + leavesVTO.getApprovedBy() + "^";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getLeavesListDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getExternalEmployeeDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getExternalEmployeeDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getExternalEmployeeDetails Method Start*********************");
        boolean isGetting = false;
        String exist = "";
        StringBuffer sb = new StringBuffer();
        if ("E".equalsIgnoreCase(userAjaxHandlerAction.getResourceType())) {
            queryString = "SELECT concat(trim(first_name),'.',trim(last_name)) AS FullName,usr_id FROM users WHERE (last_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%' OR first_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%') and cur_status='Active' AND org_id=" + userAjaxHandlerAction.getSessionOrgId() + " ORDER BY FullName LIMIT 30";
        } else if ("C".equalsIgnoreCase(userAjaxHandlerAction.getResourceType())) {
            queryString = "SELECT CONCAT(TRIM(first_name),'.',TRIM(last_name)) AS FullName,usr_id FROM con_or_ven_mig_rel cvm LEFT OUTER JOIN USERS u ON (cvm.consultantid=u.usr_id) "
                    + "WHERE u.cur_status='Active' and accountId=" + userAjaxHandlerAction.getSessionOrgId() + " AND curStatus!='Released' AND Afrtypeofusr LIKE 'VC' and (last_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%' OR first_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%') ORDER BY FullName LIMIT 30 ";
        } else {
            queryString = "SELECT concat(trim(u.first_name),'.',trim(u.last_name)) AS FullName,u.usr_id FROM users u LEFT OUTER JOIN usr_roles ur ON (u.usr_id=ur.usr_id) "
                    + "WHERE (u.last_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' OR u.first_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%')"
                    + " and u.cur_status='Active' AND u.org_id=" + userAjaxHandlerAction.getSessionOrgId() + " AND ur.role_id IN(7,11) ORDER BY FullName LIMIT 30";
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getExternalEmployeeDetails :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            List projectTeamList = DataSourceDataProvider.getInstance().getProjectTeamMembersList(userAjaxHandlerAction.getProjectID());
            while (resultSet.next()) {
                if ("E".equalsIgnoreCase(userAjaxHandlerAction.getResourceType()) || "C".equalsIgnoreCase(userAjaxHandlerAction.getResourceType())) {
                    exist = DataSourceDataProvider.getInstance().checkUserExistOrNotForProjectRespectedOrg(resultSet.getInt(2), userAjaxHandlerAction.getSessionOrgId());
                } else {
                    exist = "notExisted";
                }
                if ("notExisted".equals(exist)) {
                    if (!projectTeamList.contains(resultSet.getInt(2))) {
                        sb.append("<EMPLOYEE><VALID>true</VALID>");
                        if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                            sb.append("<NAME>NoRecord</NAME>");
                        } else {
                            String title = resultSet.getString(1);
                            if (title.contains("&")) {
                                title = title.replace("&", "&amp;");
                            }
                            sb.append("<NAME>" + title + "</NAME>");
                        }
                        sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                        sb.append("</EMPLOYEE>");
                        isGetting = true;
                        count++;
                    }
                }
            }
            if (!isGetting) {
                isGetting = false;
                //nothing to show
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getExternalEmployeeDetails Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getExternalEmployee2Details() method is used to
     *
     * *****************************************************************************
     */
    public String getExternalEmployee2Details(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getExternalEmployee2Details Method Start*********************");
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        queryString = "SELECT concat(trim(first_name),'.',trim(last_name)) AS FullName,usr_id FROM users WHERE (last_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%' OR first_name LIKE '" + userAjaxHandlerAction.getEmpName() + "%') and cur_status='Active' AND org_id=" + userAjaxHandlerAction.getSessionOrgId() + " LIMIT 30";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getExternalEmployee2Details :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");
                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }

            if (!isGetting) {
                isGetting = false;
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getExternalEmployee2Details Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date : 10/may/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getVendorNames() method is used to
     *
     * *****************************************************************************
     */
    public String getVendorNames(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getVendorNames Method Start*********************");
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        queryString = "SELECT a.account_name,a.account_id "
                + "FROM accounts a "
                + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                + "WHERE a.account_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' "
                + "AND  o.acc_type=5 "
                + "AND a.account_id NOT IN(SELECT vendor_id FROM customer_ven_rel WHERE customer_id=" + userAjaxHandlerAction.getCustomerId() + ")";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getVendorNames :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");
                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }
            if (!isGetting) {
                isGetting = false;
                //nothing to show
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getVendorNames Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getCsrNames() method is used to
     *
     * *****************************************************************************
     */
    public String getCsrNames(String csrName) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getCsrNames Method Start*********************");
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        queryString = "SELECT CONCAT(u.first_name,'.',u.last_name)AS NAME,u.usr_id FROM users u LEFT OUTER JOIN usr_roles ur ON (u.usr_id=ur.usr_id) WHERE CONCAT(u.first_name,' ',u.last_name) LIKE '%" + csrName + "%' AND u.cur_status='Active' AND ur.role_id=1";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("getCsrNames :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");
                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }
            if (!isGetting) {
                isGetting = false;
                //nothing to show
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getCsrNames Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : csrTermination() method is used to
     *
     * *****************************************************************************
     */
    public String csrTermination(int userId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: csrTermination Method Start*********************");
        int result = 0;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        String resultMessage = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryStringupdate = " update csrorgrel SET status=? WHERE csr_id =" + userId;
            System.out.println("csrTermination :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryStringupdate);
            preparedStatement.setString(1, "In-Active");
            result = preparedStatement.executeUpdate();
            if (result > 0) {
                DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
                connection1 = ConnectionProvider.getInstance().getConnection();
                String queryString1 = "SELECT DISTINCT(u.usr_id),concat(u.first_name,'.',u.last_name) as name,u.email1,u.cur_status FROM users u "
                        + " LEFT OUTER JOIN csrorgrel csr  ON(u.usr_id=csr.csr_id) LEFT OUTER JOIN usr_roles ur "
                        + " ON(u.usr_id=ur.usr_id) LEFT OUTER JOIN roles r ON(ur.role_id=r.role_id)"
                        + " WHERE ur.role_id=1 AND primary_flag=1";
                statement1 = connection1.createStatement();
                resultSet1 = statement1.executeQuery(queryString1);
                while (resultSet1.next()) {
                    resultMessage += resultSet1.getInt("u.usr_id") + "|"
                            + resultSet1.getString("name") + "|"
                            + resultSet1.getString("u.email1") + "|"
                            + resultSet1.getString("u.cur_status") + "|"
                            + dsdp.getCsrAccountCount(resultSet1.getInt("u.usr_id")) + "^";
                }
            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: csrTermination Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : changeCsrAccountStatus() method is used to
     *
     * *****************************************************************************
     */
    public String changeCsrAccountStatus(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: changeCsrAccountStatus Method Start*********************");
        int result = 0;
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        String resultMessage = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryStringupdate = " update csrorgrel SET status=? WHERE csr_id =" + userAjaxHandlerAction.getUserid() + ""
                    + " AND org_id=" + userAjaxHandlerAction.getOrgId() + "";
            System.out.println("changeCsrAccountStatus :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryStringupdate);
            preparedStatement.setString(1, userAjaxHandlerAction.getStatus());
            result = preparedStatement.executeUpdate();
            if ("In-Active".equals(userAjaxHandlerAction.getStatus())) {
                userAjaxHandlerAction.setStatus("Active");
            } else {
                userAjaxHandlerAction.setStatus("In-Active");
            }
            if (result > 0) {
                DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
                connection1 = ConnectionProvider.getInstance().getConnection();
                String queryString1 = "SELECT csr.csr_id,a.account_id,a.account_name,csr.status FROM accounts a LEFT OUTER JOIN csrorgrel csr ON(a.account_id=csr.org_id) WHERE csr.csr_id=" + userAjaxHandlerAction.getUserid() + " and csr.status='" + userAjaxHandlerAction.getStatus() + "'";
                statement1 = connection1.createStatement();
                resultSet1 = statement1.executeQuery(queryString1);
                while (resultSet1.next()) {

                    resultMessage += resultSet1.getInt("csr.csr_id") + "|"
                            + resultSet1.getInt("a.account_id") + "|"
                            + resultSet1.getString("a.account_name") + "|"
                            + resultSet1.getString("csr.status") + "^";
                }
            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }

        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: changeCsrAccountStatus Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getCsrAccount() method is used to
     *
     * *****************************************************************************
     */
    public String getCsrAccount(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getCsrAccount Method Start*********************");
        String resultMessage = "";
        try {
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString1 = "SELECT csr.csr_id,a.account_id,a.account_name,csr.status FROM accounts a LEFT OUTER JOIN csrorgrel csr ON(a.account_id=csr.org_id) WHERE csr.csr_id=" + userAjaxHandlerAction.getUserid() + "";
            if (userAjaxHandlerAction.getStatus() != null) {
                if ("All".equals(userAjaxHandlerAction.getStatus())) {
                    queryString1 = queryString1 + " and csr.status like '%%'  ";
                } else {
                    queryString1 = queryString1 + " and csr.status= '" + userAjaxHandlerAction.getStatus() + "'  ";
                }
            }
            if (userAjaxHandlerAction.getAccountName() != null || !"".equals(userAjaxHandlerAction.getAccountName())) {
                queryString1 = queryString1 + " and a.account_name LIKE '" + userAjaxHandlerAction.getAccountName() + "%'";
            }
            statement = connection.createStatement();
            System.out.println("getCsrAccount :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString1);
            while (resultSet.next()) {
                resultMessage += resultSet.getInt("csr.csr_id") + "|"
                        + resultSet.getInt("a.account_id") + "|"
                        + resultSet.getString("a.account_name") + "|"
                        + resultSet.getString("csr.status") + "^";
            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
                sql.printStackTrace();
            }

        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: getCsrAccount Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getEmpCategories() method is used to
     *
     * *****************************************************************************
     */
    public String getEmpCategories(UserAjaxHandlerAction userAjaxHandlerAction, int sessionOrgId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpCategories Method Start*********************");
        String resultMessage = "";
        try {
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString1 = " SELECT uc.sub_cat,lk.grpname,r.role_name,uc.id,u.usr_id,u.org_id,uc.cat_type,"
                    + "CONCAT(u.first_name,'.',u.last_name) AS name,uc.is_primary,uc.status,uc.created_by "
                    + "FROM usr_grouping uc "
                    + "LEFT OUTER JOIN lkusr_group lk ON(lk.id=uc.cat_type)"
                    + " LEFT OUTER JOIN users u ON(uc.usr_id=u.usr_id) LEFT OUTER JOIN usr_roles ur ON(ur.usr_id=u.usr_id)"
                    + " LEFT OUTER JOIN roles r ON(ur.role_id=r.role_id)"
                    + " WHERE u.org_id=" + sessionOrgId + "";
            if (userAjaxHandlerAction.getStatus() != null) {
                if ("All".equals(userAjaxHandlerAction.getStatus())) {
                    queryString1 = queryString1 + " and uc.status like '%%'  ";
                } else {
                    queryString1 = queryString1 + " and uc.status= '" + userAjaxHandlerAction.getStatus() + "'  ";
                }
            }
            if (userAjaxHandlerAction.getEmpName() != null || !"".equals(userAjaxHandlerAction.getEmpName())) {
                if (userAjaxHandlerAction.getEmpId() != 0) {
                    queryString1 = queryString1 + " and u.usr_id= '" + userAjaxHandlerAction.getEmpId() + "'  ";
                } else {
                    queryString1 = queryString1 + " and  (u.first_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' or u.last_name like '%" + userAjaxHandlerAction.getEmpName() + "%')";
                }
            }
            if (userAjaxHandlerAction.getCategoryType() != -1) {
                queryString1 = queryString1 + " and lk.id= '" + userAjaxHandlerAction.getCategoryType() + "'";
            }
            statement = connection.createStatement();
            System.out.println("getEmpCategories :: query string ------>" + queryString1);
            resultSet = statement.executeQuery(queryString1);
            String primary = "";
            while (resultSet.next()) {
                UserVTO userVTO = new UserVTO();
                userVTO.setGroupingId(resultSet.getInt("uc.id"));
                userVTO.setEmpId(resultSet.getInt("u.usr_id"));
                userVTO.setCatogoryGroup(resultSet.getString("lk.grpname"));
                userVTO.setCatogoryTypeId(resultSet.getInt("uc.cat_type"));
                userVTO.setSubCategory(resultSet.getString("uc.sub_cat"));
                userVTO.setEmpName(resultSet.getString("name"));
                userVTO.setRole(resultSet.getString("r.role_name"));
                int primaryNumber = resultSet.getInt("uc.is_primary");
                if (primaryNumber == 0) {
                    userVTO.setIsPrimary("No");
                } else {
                    userVTO.setIsPrimary("Yes");
                }
                userVTO.setStatus(resultSet.getString("uc.status"));
                userVTO.setCreatedBy(dsdp.getUserNameByUserId(resultSet.getInt("uc.created_by")));
                resultMessage += userVTO.getGroupingId() + "|"
                        + userVTO.getEmpId() + "|"
                        + userVTO.getCatogoryGroup() + "|"
                        + userVTO.getCatogoryTypeId() + "|"
                        + ("'" + userVTO.getSubCategory() + "'") + "|"
                        + userVTO.getEmpName() + "|"
                        + userVTO.getStatus() + "|"
                        + userVTO.getRole() + "|"
                        + userVTO.getCreatedBy() + "|"
                        + userVTO.getIsPrimary() + "^";
            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
                sql.printStackTrace();
            }

        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpCategories Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doUserGroupingMethod() method is used to
     *
     * *****************************************************************************
     */
    public String doUserGroupingMethod(UserAjaxHandlerAction userAjaxHandlerAction) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: doUserGroupingMethod Method Start*********************");
        String resultString = "";

        int result;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();

            if (isUserGroupExist(userAjaxHandlerAction.getUserId())) {
                queryString = "update usr_grouping set is_primary=" + userAjaxHandlerAction.getPrimary() + ", cat_type=" + userAjaxHandlerAction.getUsrCatType() + " ,sub_cat='" + userAjaxHandlerAction.getUserCatArry() + "',STATUS='" + userAjaxHandlerAction.getUsrStatus() + "',description='" + userAjaxHandlerAction.getUsrDescription() + "',modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "',modified_by=" + userAjaxHandlerAction.getUserSessionId() + " where usr_id=" + userAjaxHandlerAction.getUserId();
                resultString = "Grouping updated succesfully.";
            } else {
                queryString = "insert into usr_grouping (usr_id,work_type,cat_type,sub_cat,status,description,created_by,is_primary) values"
                        + "(" + userAjaxHandlerAction.getUserId() + "," + "'REC'," + userAjaxHandlerAction.getUsrCatType() + ",'" + userAjaxHandlerAction.getUserCatArry() + "','" + userAjaxHandlerAction.getUsrStatus() + "','" + userAjaxHandlerAction.getUsrDescription() + "'," + userAjaxHandlerAction.getUserSessionId() + "," + userAjaxHandlerAction.getPrimary() + ")";
                resultString = "Grouping Added succesfully.";
            }
            System.out.println("doUserGroupingMethod :: query string ------>" + queryString);
            result = statement.executeUpdate(queryString);
        } catch (Exception sqe) {
            resultString = "Something were Wrong!";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: doUserGroupingMethod Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : isUserGroupExist() method is used to
     *
     * *****************************************************************************
     */
    public boolean isUserGroupExist(int userId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: isUserGroupExist Method Start*********************");
        boolean result = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT usr_id FROM usr_grouping WHERE usr_id=" + userId;
            System.out.println("isUserGroupExist :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = true;
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: isUserGroupExist Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : empCategoryTermination() method is used to
     *
     * *****************************************************************************
     */
    public String empCategoryTermination(UserAjaxHandlerAction userAjaxHandlerAction, int sessionOrgId) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: empCategoryTermination Method Start*********************");
        int result = 0;
        String resultMessage = "";
        Connection connection1 = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryStringupdate = " update usr_grouping SET status=? WHERE id =" + userAjaxHandlerAction.getGroupingId() + "";
            System.out.println("empCategoryTermination :: query string ------>" + queryStringupdate);
            preparedStatement = connection.prepareStatement(queryStringupdate);
            preparedStatement.setString(1, "In-Active");
            result = preparedStatement.executeUpdate();
            if (result > 0) {
                DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
                connection1 = ConnectionProvider.getInstance().getConnection();
                String queryString1 = " SELECT uc.id,u.usr_id,u.org_id,uc.cat_type,CONCAT(u.first_name,'.',u.last_name) AS name,uc.is_primary,uc.status,uc.created_by FROM usr_grouping uc LEFT OUTER JOIN users u ON(uc.usr_id=u.usr_id)"
                        + " WHERE u.org_id=" + sessionOrgId + "";
                System.out.println("empCategoryTermination :: query string 2------>" + queryString1);
                statement1 = connection1.createStatement();
                resultSet1 = statement1.executeQuery(queryString1);
                String primary = "";
                while (resultSet1.next()) {
                    int primaryNumber = resultSet1.getInt("uc.is_primary");
                    if (primaryNumber == 0) {
                        primary = "NO";
                    } else {
                        primary = "YES";
                    }
                    resultMessage += resultSet1.getInt("uc.id") + "|"
                            + resultSet1.getInt("u.usr_id") + "|"
                            + resultSet1.getString("name") + "|"
                            + primary + "|"
                            + resultSet1.getString("uc.status") + "|"
                            + dsdp.getUserNameByUserId(resultSet1.getInt("uc.created_by")) + "^";
                }
            }
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: empCategoryTermination Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getHomeRedirectSearchDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getHomeRedirectSearchDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getHomeRedirectSearchDetails Method Start*********************");
        String resultString = "";
        int result;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "SELECT h.id,a.account_name,h.type_of_user,r.role_name,h.action_name,h.STATUS,h.description "
                    + "FROM home_redirect_action h "
                    + "LEFT OUTER JOIN accounts a ON(a.account_id=h.org_id) "
                    + "LEFT OUTER JOIN roles r ON(r.role_id=h.primaryrole) "
                    + "where 1=1 ";
            if (!"-1".equalsIgnoreCase(userAjaxHandlerAction.getAccountName())) {
                queryString = queryString + " AND h.org_id= '" + userAjaxHandlerAction.getAccountName() + "'  ";
            } else {
                if ("AC".equalsIgnoreCase(userAjaxHandlerAction.getAccountType()) || "VC".equalsIgnoreCase(userAjaxHandlerAction.getAccountType())) {
                    queryString = queryString + " AND h.org_id= " + userAjaxHandlerAction.getSessionOrgId();
                }
            }
            if (!"-1".equalsIgnoreCase(userAjaxHandlerAction.getPrimaryRole())) {
                queryString = queryString + " AND h.primaryrole= '" + userAjaxHandlerAction.getPrimaryRole() + "'  ";
            }
            if (!"-1".equalsIgnoreCase(userAjaxHandlerAction.getTypeOfUser())) {
                if ("AC".equalsIgnoreCase(userAjaxHandlerAction.getAccountType()) || "VC".equalsIgnoreCase(userAjaxHandlerAction.getAccountType())) {
                    queryString = queryString + " AND type_of_user= '" + userAjaxHandlerAction.getAccountType() + "'  ";
                } else {
                    queryString = queryString + " AND h.type_of_user= '" + userAjaxHandlerAction.getTypeOfUser() + "'  ";
                }
            }
            System.out.println("getHomeRedirectSearchDetails :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            String typeOfUser = "";
            while (resultSet.next()) {
                if (resultSet.getString("type_of_user").equalsIgnoreCase("SA")) {
                    typeOfUser = "Site Admin";
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("AC")) {
                    typeOfUser = "Customer";
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("VC")) {
                    typeOfUser = "Vendor";
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("E")) {
                    typeOfUser = "Employee";
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("CO")) {
                    typeOfUser = "Consultant";
                } else {
                    typeOfUser = "";
                }
                resultString += resultSet.getString("id") + "|"
                        + resultSet.getString("account_name") + "|"
                        + typeOfUser + "|"
                        + resultSet.getString("role_name") + "|"
                        + resultSet.getString("action_name") + "|"
                        + resultSet.getString("description") + "|"
                        + resultSet.getString("STATUS") + "^";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getHomeRedirectSearchDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAccountNames() method is used to
     *
     * *****************************************************************************
     */
    public String getAccountNames(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getAccountNames Method Start*********************");
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        if (userAjaxHandlerAction.getAccountType().equalsIgnoreCase("AC")) {
            queryString = "SELECT a.account_name,a.account_id "
                    + "FROM accounts a "
                    + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                    + "WHERE account_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' "
                    + "AND a.STATUS='Active' "
                    + "AND o.acc_type=1 "
                    + "AND type_of_relation='C'"
                    + "LIMIT 30";
        } else if (userAjaxHandlerAction.getAccountType().equalsIgnoreCase("VC")) {
            queryString = "SELECT a.account_name,a.account_id "
                    + "FROM accounts a "
                    + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                    + "WHERE account_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' "
                    + "AND a.STATUS='Active' "
                    + "AND o.acc_type=5 "
                    + "LIMIT 30";
        } else if (userAjaxHandlerAction.getAccountType().equalsIgnoreCase("M")) {
            queryString = "SELECT a.account_name,a.account_id "
                    + "FROM accounts a "
                    + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                    + "WHERE account_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' "
                    + "AND a.STATUS='Active' "
                    + "AND o.acc_type=1 "
                    + "AND type_of_relation='M'"
                    + "LIMIT 30";
        } else {
            queryString = "SELECT a.account_name,a.account_id "
                    + "FROM accounts a "
                    + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                    + "WHERE account_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' "
                    + "AND a.STATUS='Active' "
                    + "LIMIT 30";
        }

        try {
            System.out.println("getAccountNames :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");
                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }
            if (!isGetting) {
                isGetting = false;
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getAccountNames Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : storeAddOrEditHomeRedirectDetails() method is used to
     *
     * *****************************************************************************
     */
    public String storeAddOrEditHomeRedirectDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: storeAddOrEditHomeRedirectDetails Method Start*********************");
        String resultString = "";
        String validateQuery = "";
        int result = 0;
        int validateResult = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            validateQuery = "SELECT id FROM home_redirect_action WHERE org_id=" + userAjaxHandlerAction.getAccountId() + " and  primaryrole=" + userAjaxHandlerAction.getRoleName() + " and type_of_user='" + userAjaxHandlerAction.getTypeOfUser() + "'";
            System.out.println("storeAddOrEditHomeRedirectDetails :: query string ------>" + validateQuery);
            resultSet = statement.executeQuery(validateQuery);
            while (resultSet.next()) {
                validateResult = 1;
            }
            if (userAjaxHandlerAction.getHomeRedirectActionId() > 0) {
                if (validateResult > 0) {
                    queryString = "UPDATE home_redirect_action SET "
                            + "org_id=" + userAjaxHandlerAction.getAccountId() + ","
                            + "type_of_user='" + userAjaxHandlerAction.getTypeOfUser() + "',"
                            + "primaryrole=" + userAjaxHandlerAction.getRoleName() + " ,"
                            + "action_name='" + userAjaxHandlerAction.getActionName() + "',"
                            + "STATUS='" + userAjaxHandlerAction.getHomeRedirectStatus() + "',"
                            + "description='" + userAjaxHandlerAction.getHomeRedirectDescription() + "' "
                            + "WHERE id=" + userAjaxHandlerAction.getHomeRedirectActionId() + "";
                } else {
                    resultString = "Fail";
                }
            } else {
                if (validateResult < 1) {
                    queryString = "INSERT INTO home_redirect_action"
                            + "(org_id,type_of_user,primaryrole,action_name,description,STATUS,created_by) "
                            + "VALUES("
                            + "" + userAjaxHandlerAction.getAccountId() + ","
                            + "'" + userAjaxHandlerAction.getTypeOfUser() + "',"
                            + "" + userAjaxHandlerAction.getRoleName() + ","
                            + "'" + userAjaxHandlerAction.getActionName() + "',"
                            + "'" + userAjaxHandlerAction.getHomeRedirectDescription() + "',"
                            + "'Active',"
                            + "" + userAjaxHandlerAction.getUserSessionId() + ""
                            + ")";
                } else {
                    resultString = "Fail";
                }
            }
            System.out.println("storeAddOrEditHomeRedirectDetails :: query string 2------>" + queryString);
            result = statement.executeUpdate(queryString);
            if (result > 0) {
                resultString = "Success";
            } else {
                resultString = "Fail";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: storeAddOrEditHomeRedirectDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getHomeRedirectCommentsDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getHomeRedirectCommentsDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getHomeRedirectCommentsDetails Method Start*********************");
        String resultString = "";
        int result = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "SELECT description FROM home_redirect_action WHERE id=" + userAjaxHandlerAction.getHomeRedirectActionId();
            System.out.println("getHomeRedirectCommentsDetails :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString = resultSet.getString("description");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getHomeRedirectCommentsDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getCategoryList() method is used to
     *
     * *****************************************************************************
     */
    public String getCategoryList(UserAjaxHandlerAction aThis) throws ServiceLocatorException {
        System.out.println("********************UserAjaxHandlerServiceImpl :: getCategoryList Method Start*********************");
        String result = "";
        Map map1;
        map1 = com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory(aThis.getUsrCatType());
        Map map;
        map = map1;
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            result += key + "," + value + "^";
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: getCategoryList Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getEmpCategoryNames() method is used to
     *
     * *****************************************************************************
     */
    public String getEmpCategoryNames(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpCategoryNames Method Start*********************");
        String resultString = "";
        int result = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "SELECT catName,grpid FROM lkusr_grpcategory WHERE grpcategory IN (" + userAjaxHandlerAction.getCategoryNamesList() + ")";
            System.out.println("getEmpCategoryNames :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("grpid") + "|"
                        + resultSet.getString("catName") + "^";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpCategoryNames Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTechEmployeeDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getTechEmployeeDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getTechEmployeeDetails Method Start*********************");
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        queryString = "SELECT concat(trim(u.first_name),'.',trim(u.last_name)) AS FullName,u.usr_id FROM users u LEFT OUTER JOIN usr_roles ur ON (u.usr_id=ur.usr_id) "
                + "WHERE (u.last_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' OR u.first_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%')"
                + " and u.cur_status='Active' AND u.org_id=" + userAjaxHandlerAction.getSessionOrgId() + " AND ur.role_id IN(3,5,6,13) ORDER BY FullName LIMIT 30";
        try {
            System.out.println("getTechEmployeeDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            List projectTeamList = DataSourceDataProvider.getInstance().getProjectTeamMembersList(userAjaxHandlerAction.getProjectID());
            while (resultSet.next()) {
                if (!projectTeamList.contains(resultSet.getInt(2))) {
                    sb.append("<EMPLOYEE><VALID>true</VALID>");
                    if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                        sb.append("<NAME>NoRecord</NAME>");
                    } else {
                        String title = resultSet.getString(1);
                        if (title.contains("&")) {
                            title = title.replace("&", "&amp;");
                        }
                        sb.append("<NAME>" + title + "</NAME>");
                    }
                    sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                    sb.append("</EMPLOYEE>");
                    isGetting = true;
                    count++;
                }
            }
            if (!isGetting) {
                isGetting = false;
                //nothing to show
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getTechEmployeeDetails Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkFileName() method is used to
     *
     * *****************************************************************************
     */
    public String checkFileName(String xlsfileFileName) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: checkFileName Method Start*********************");
        String result = "NotExist";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "select uploaded_file from utility_logger where uploaded_file like '%" + xlsfileFileName + "%'";
            System.out.println("checkFileName :: query string ------>" + queryString);
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = "Exist";
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: checkFileName Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getQuestion() method is used to
     *
     * *****************************************************************************
     */
    public String getQuestion(int questionNo, HttpServletRequest httpServletRequest, int selectedAns, String navigation, int remainingQuestions, int onClickStatus, int subTopicId, int specficQuestionNo, UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getQuestion Method Start*********************");
        QuestionVTO questionVTO = null, nextQuestionVTO = null, previousQuestionVTO = null, specificQuestionVTO = null, startQuestionVTO = null, topicQuestionVto = null;
        int empId = 0, examKeyId = 0, answer = 0, attemptedQuestions = 0, questionId = 0, reqId = 0;
        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        try {
            empId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID).toString());
            examKeyId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CURRENT_KEY).toString());
            reqId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID).toString());
            Map questionVtoMap = (Map) httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_QUESTION_MAP);
            if (navigation.equalsIgnoreCase("I")) {
                int qId = 0;
                /**
                 * Display Question
                 */
                startQuestionVTO = (QuestionVTO) questionVtoMap.get(1);
                int startQId = startQuestionVTO.getId();
                int mapsize = questionVtoMap.size();
                remainingQuestions = mapsize;
                /**
                 * XML start *
                 */
                int startQuestionNo = 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + startQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + startQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + startQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + startQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + startQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + startQuestionVTO.getOption4() + "]]></OPTION4>");
                stringBuffer.append("<OPTION5><![CDATA[" + startQuestionVTO.getOption5() + "]]></OPTION5>");
                stringBuffer.append("<OPTION6><![CDATA[" + startQuestionVTO.getOption6() + "]]></OPTION6>");

                stringBuffer.append("<MAPQUESTIONID>" + startQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER1>" + 0 + "</EMPANSWER1>");
                stringBuffer.append("<EMPANSWER2>" + 0 + "</EMPANSWER2>");
                stringBuffer.append("<EMPANSWER3>" + 0 + "</EMPANSWER3>");
                stringBuffer.append("<EMPANSWER4>" + 0 + "</EMPANSWER4>");
                stringBuffer.append("<EMPANSWER5>" + 0 + "</EMPANSWER5>");
                stringBuffer.append("<EMPANSWER6>" + 0 + "</EMPANSWER6>");
                stringBuffer.append("<SUBTOPICID>" + startQuestionVTO.getSkillId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + remainingQuestions + "</REMAININGQUESTIONS>");
                if (!"".equals(startQuestionVTO.getTopicName())) {
                    stringBuffer.append("<SECTION>" + startQuestionVTO.getTopicName() + "</SECTION>");
                }
                stringBuffer.append("<QUESTIONTYPE>" + startQuestionVTO.getQuestionType() + "</QUESTIONTYPE>");
                stringBuffer.append("<QUESTIONPIC>" + startQuestionVTO.getIsPictorial() + "</QUESTIONPIC>");
                stringBuffer.append("<QUESTIONPATH>" + startQuestionVTO.getQuestionPath() + "</QUESTIONPATH>");
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } else if (navigation.equalsIgnoreCase("N")) {


                int qId = 0;
                nextQuestionVTO = (QuestionVTO) questionVtoMap.get(questionNo + 1);
                int nextQId = nextQuestionVTO.getId();
                /* Insert Question into db */
                questionVTO = (QuestionVTO) questionVtoMap.get(questionNo);
                qId = questionVTO.getId();
                /**
                 * answered by user or not
                 */
                /**
                 * Display Question
                 */
                connection = ConnectionProvider.getInstance().getConnection();
                System.out.println("getQuestion :: procedure name : sp_onlineexamsummery ");
                callableStatement = connection.prepareCall("{CALL sp_onlineexamsummery(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, reqId);
                callableStatement.setInt(2, empId);
                callableStatement.setInt(3, userAjaxHandlerAction.getAnswer1());
                callableStatement.setInt(4, userAjaxHandlerAction.getAnswer2());
                callableStatement.setInt(5, userAjaxHandlerAction.getAnswer3());
                callableStatement.setInt(6, userAjaxHandlerAction.getAnswer4());
                callableStatement.setInt(7, userAjaxHandlerAction.getAnswer5());
                callableStatement.setInt(8, userAjaxHandlerAction.getAnswer6());
                callableStatement.setInt(9, qId);
                callableStatement.setInt(10, nextQId);
                callableStatement.setInt(11, examKeyId);
                callableStatement.setInt(12, subTopicId);
                callableStatement.setInt(13, remainingQuestions);
                callableStatement.setInt(14, userAjaxHandlerAction.getExamId());
                updatedRows = callableStatement.executeUpdate();
                addResult = callableStatement.getInt(15);

                /**
                 * XML start *
                 */
                int nextQuestionNo = questionNo + 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + nextQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + nextQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + nextQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + nextQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + nextQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + nextQuestionVTO.getOption4() + "]]></OPTION4>");
                stringBuffer.append("<OPTION5><![CDATA[" + nextQuestionVTO.getOption5() + "]]></OPTION5>");
                stringBuffer.append("<OPTION6><![CDATA[" + nextQuestionVTO.getOption6() + "]]></OPTION6>");
                stringBuffer.append("<MAPQUESTIONID>" + nextQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER1>" + callableStatement.getString(16) + "</EMPANSWER1>");
                stringBuffer.append("<EMPANSWER2>" + callableStatement.getString(17) + "</EMPANSWER2>");
                stringBuffer.append("<EMPANSWER3>" + callableStatement.getString(18) + "</EMPANSWER3>");
                stringBuffer.append("<EMPANSWER4>" + callableStatement.getString(19) + "</EMPANSWER4>");
                stringBuffer.append("<EMPANSWER5>" + callableStatement.getString(20) + "</EMPANSWER5>");
                stringBuffer.append("<EMPANSWER6>" + callableStatement.getString(21) + "</EMPANSWER6>");
                stringBuffer.append("<SUBTOPICID>" + nextQuestionVTO.getSkillId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + callableStatement.getString(15) + "</REMAININGQUESTIONS>");
                if (!"".equals(nextQuestionVTO.getTopicName())) {
                    stringBuffer.append("<SECTION>" + nextQuestionVTO.getTopicName() + "</SECTION>");

                }
                stringBuffer.append("<QUESTIONTYPE>" + nextQuestionVTO.getQuestionType() + "</QUESTIONTYPE>");
                stringBuffer.append("<QUESTIONPIC>" + nextQuestionVTO.getIsPictorial() + "</QUESTIONPIC>");
                stringBuffer.append("<QUESTIONPATH>" + nextQuestionVTO.getQuestionPath() + "</QUESTIONPATH>");
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } else if (navigation.equalsIgnoreCase("P")) {
                questionVTO = (QuestionVTO) questionVtoMap.get(questionNo);
                int qId = questionVTO.getId();
                /**
                 * answered by user or not
                 */
                previousQuestionVTO = (QuestionVTO) questionVtoMap.get(questionNo - 1);
                int prevoiusQId = previousQuestionVTO.getId();

                connection = ConnectionProvider.getInstance().getConnection();
                System.out.println("getQuestion :: procedure name : sp_onlineexamsummery ");
                callableStatement = connection.prepareCall("{CALL sp_onlineexamsummery(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, reqId);
                callableStatement.setInt(2, empId);
                callableStatement.setInt(3, userAjaxHandlerAction.getAnswer1());
                callableStatement.setInt(4, userAjaxHandlerAction.getAnswer2());
                callableStatement.setInt(5, userAjaxHandlerAction.getAnswer3());
                callableStatement.setInt(6, userAjaxHandlerAction.getAnswer4());
                callableStatement.setInt(7, userAjaxHandlerAction.getAnswer5());
                callableStatement.setInt(8, userAjaxHandlerAction.getAnswer6());
                callableStatement.setInt(9, qId);
                callableStatement.setInt(10, prevoiusQId);
                callableStatement.setInt(11, examKeyId);
                callableStatement.setInt(12, subTopicId);
                callableStatement.setInt(13, remainingQuestions);
                callableStatement.setInt(14, userAjaxHandlerAction.getExamId());
                updatedRows = callableStatement.executeUpdate();
                addResult = callableStatement.getInt(15);
                /**
                 * XML start *
                 */
                int previousQuestionNo = questionNo - 1;
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + prevoiusQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + previousQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + previousQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + previousQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + previousQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + previousQuestionVTO.getOption4() + "]]></OPTION4>");
                stringBuffer.append("<OPTION5><![CDATA[" + previousQuestionVTO.getOption5() + "]]></OPTION5>");
                stringBuffer.append("<OPTION6><![CDATA[" + previousQuestionVTO.getOption6() + "]]></OPTION6>");

                stringBuffer.append("<MAPQUESTIONID>" + previousQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER1>" + callableStatement.getString(16) + "</EMPANSWER1>");
                stringBuffer.append("<EMPANSWER2>" + callableStatement.getString(17) + "</EMPANSWER2>");
                stringBuffer.append("<EMPANSWER3>" + callableStatement.getString(18) + "</EMPANSWER3>");
                stringBuffer.append("<EMPANSWER4>" + callableStatement.getString(19) + "</EMPANSWER4>");
                stringBuffer.append("<EMPANSWER5>" + callableStatement.getString(20) + "</EMPANSWER5>");
                stringBuffer.append("<EMPANSWER6>" + callableStatement.getString(21) + "</EMPANSWER6>");
                stringBuffer.append("<SUBTOPICID>" + previousQuestionVTO.getSkillId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + callableStatement.getString(15) + "</REMAININGQUESTIONS>");
                if (!"".equals(previousQuestionVTO.getTopicName())) {
                    stringBuffer.append("<SECTION>" + previousQuestionVTO.getTopicName() + "</SECTION>");
                }
                stringBuffer.append("<QUESTIONTYPE>" + previousQuestionVTO.getQuestionType() + "</QUESTIONTYPE>");
                stringBuffer.append("<QUESTIONPIC>" + previousQuestionVTO.getIsPictorial() + "</QUESTIONPIC>");
                stringBuffer.append("<QUESTIONPATH>" + previousQuestionVTO.getQuestionPath() + "</QUESTIONPATH>");
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");


            } else if (navigation.equalsIgnoreCase("R")) {
                int qId = 0;
                /* Insert Question into db */
                questionVTO = (QuestionVTO) questionVtoMap.get(questionNo);
                qId = questionVTO.getId();
                /**
                 * answered by user or not
                 */
                /**
                 * Display Question
                 */
                specificQuestionVTO = (QuestionVTO) questionVtoMap.get(specficQuestionNo);
                int specificQId = specificQuestionVTO.getId();
                connection = ConnectionProvider.getInstance().getConnection();
                System.out.println("getQuestion :: procedure name : sp_onlineexamsummery ");
                callableStatement = connection.prepareCall("{CALL sp_onlineexamsummery(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, reqId);
                callableStatement.setInt(2, empId);
                callableStatement.setInt(3, userAjaxHandlerAction.getAnswer1());
                callableStatement.setInt(4, userAjaxHandlerAction.getAnswer2());
                callableStatement.setInt(5, userAjaxHandlerAction.getAnswer3());
                callableStatement.setInt(6, userAjaxHandlerAction.getAnswer4());
                callableStatement.setInt(7, userAjaxHandlerAction.getAnswer5());
                callableStatement.setInt(8, userAjaxHandlerAction.getAnswer6());
                callableStatement.setInt(9, qId);
                callableStatement.setInt(10, specificQId);
                callableStatement.setInt(11, examKeyId);
                callableStatement.setInt(12, subTopicId);
                callableStatement.setInt(13, remainingQuestions);
                callableStatement.setInt(14, userAjaxHandlerAction.getExamId());
                updatedRows = callableStatement.executeUpdate();
                addResult = callableStatement.getInt(15);
                /**
                 * XML start *
                 */
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>true</QUESTIONSTATUS>");
                stringBuffer.append("<QUESTIONID>" + specificQId + "</QUESTIONID>");
                stringBuffer.append("<QUESTIONNAME><![CDATA[" + specificQuestionVTO.getQuestion() + "]]></QUESTIONNAME>");
                stringBuffer.append("<OPTION1><![CDATA[" + specificQuestionVTO.getOption1() + "]]></OPTION1>");
                stringBuffer.append("<OPTION2><![CDATA[" + specificQuestionVTO.getOption2() + "]]></OPTION2>");
                stringBuffer.append("<OPTION3><![CDATA[" + specificQuestionVTO.getOption3() + "]]></OPTION3>");
                stringBuffer.append("<OPTION4><![CDATA[" + specificQuestionVTO.getOption4() + "]]></OPTION4>");
                stringBuffer.append("<OPTION5><![CDATA[" + specificQuestionVTO.getOption5() + "]]></OPTION5>");
                stringBuffer.append("<OPTION6><![CDATA[" + specificQuestionVTO.getOption6() + "]]></OPTION6>");

                stringBuffer.append("<MAPQUESTIONID>" + specficQuestionNo + "</MAPQUESTIONID>");
                stringBuffer.append("<EMPANSWER1>" + callableStatement.getString(16) + "</EMPANSWER1>");
                stringBuffer.append("<EMPANSWER2>" + callableStatement.getString(17) + "</EMPANSWER2>");
                stringBuffer.append("<EMPANSWER3>" + callableStatement.getString(18) + "</EMPANSWER3>");
                stringBuffer.append("<EMPANSWER4>" + callableStatement.getString(19) + "</EMPANSWER4>");
                stringBuffer.append("<EMPANSWER5>" + callableStatement.getString(20) + "</EMPANSWER5>");
                stringBuffer.append("<EMPANSWER6>" + callableStatement.getString(21) + "</EMPANSWER6>");
                stringBuffer.append("<SUBTOPICID>" + specificQuestionVTO.getSkillId() + "</SUBTOPICID>");
                stringBuffer.append("<REMAININGQUESTIONS>" + callableStatement.getString(15) + "</REMAININGQUESTIONS>");
                if (!"".equals(specificQuestionVTO.getTopicName())) {
                    stringBuffer.append("<SECTION>" + specificQuestionVTO.getTopicName() + "</SECTION>");
                }
                stringBuffer.append("<QUESTIONTYPE>" + specificQuestionVTO.getQuestionType() + "</QUESTIONTYPE>");
                stringBuffer.append("<QUESTIONPIC>" + specificQuestionVTO.getIsPictorial() + "</QUESTIONPIC>");
                stringBuffer.append("<QUESTIONPATH>" + specificQuestionVTO.getQuestionPath() + "</QUESTIONPATH>");
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");
            } else if (navigation.equalsIgnoreCase("S")) {
                questionVTO = (QuestionVTO) questionVtoMap.get(questionNo);
                int qId = questionVTO.getId();
                /**
                 * answered by user or not
                 */
                connection = ConnectionProvider.getInstance().getConnection();
                System.out.println("getQuestion :: procedure name : sp_onlineexamsummery ");
                callableStatement = connection.prepareCall("{CALL sp_onlineexamsummery(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setInt(1, reqId);
                callableStatement.setInt(2, empId);
                callableStatement.setInt(3, userAjaxHandlerAction.getAnswer1());
                callableStatement.setInt(4, userAjaxHandlerAction.getAnswer2());
                callableStatement.setInt(5, userAjaxHandlerAction.getAnswer3());
                callableStatement.setInt(6, userAjaxHandlerAction.getAnswer4());
                callableStatement.setInt(7, userAjaxHandlerAction.getAnswer5());
                callableStatement.setInt(8, userAjaxHandlerAction.getAnswer6());
                callableStatement.setInt(9, qId);
                callableStatement.setInt(10, 0);
                callableStatement.setInt(11, examKeyId);
                callableStatement.setInt(12, subTopicId);
                callableStatement.setInt(13, remainingQuestions);
                callableStatement.setInt(14, userAjaxHandlerAction.getExamId());
                updatedRows = callableStatement.executeUpdate();
                addResult = callableStatement.getInt(15);
                stringBuffer.append("<xml version=\"1.0\">");
                stringBuffer.append("<QUESTIONDETAILS >");
                stringBuffer.append("<QUESTIONSTATUS>false</QUESTIONSTATUS>");
                stringBuffer.append("<REMAININGQUESTIONS>" + callableStatement.getString(15) + "</REMAININGQUESTIONS>");
                stringBuffer.append("</QUESTIONDETAILS>");
                stringBuffer.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getQuestion Method End*********************");
        return stringBuffer.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getEmpRecruitment() method is used to
     *
     * *****************************************************************************
     */
    public String getEmpRecruitment(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpRecruitment Method Start*********************");
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        queryString = "SELECT CONCAT(u.first_name,'.',u.last_name) AS NAME,u.usr_id  FROM users u "
                + " LEFT OUTER JOIN usr_roles ur ON u.usr_id=ur.usr_id "
                + " LEFT OUTER JOIN usr_grouping ug ON ug.usr_id=u.usr_id "
                + " WHERE   ur.role_id=11  AND u.org_id=" + userAjaxHandlerAction.getSessionOrgId() + " AND cat_type=1 AND(u.last_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%' OR u.first_name LIKE '%" + userAjaxHandlerAction.getEmpName() + "%')";
        try {
            System.out.println("getEmpRecruitment :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            sb.append("<xml version=\"1.0\">");
            sb.append("<EMPLOYEES>");
            while (resultSet.next()) {
                sb.append("<EMPLOYEE><VALID>true</VALID>");
                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }
                sb.append("<EMPID>" + resultSet.getInt(2) + "</EMPID>");
                sb.append("</EMPLOYEE>");
                isGetting = true;
                count++;
            }
            if (!isGetting) {
                isGetting = false;
                //nothing to show
                sb.append("<EMPLOYEE><VALID>false</VALID></EMPLOYEE>");
            }
            sb.append("</EMPLOYEES>");
            sb.append("</xml>");
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: getEmpRecruitment Method End*********************");
        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doUpdateVisaAttachment() method is used to
     *
     * *****************************************************************************
     */
    public int doUpdateVisaAttachment(int consultantId, String fileName) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: doUpdateVisaAttachment Method Start*********************");
        int result = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = " update usr_details SET idproofattachment=? WHERE usr_id=" + consultantId;
            System.out.println("doUpdateVisaAttachment :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, fileName);
            result = preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: doUpdateVisaAttachment Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doUpdateLogo() method is used to
     *
     * *****************************************************************************
     */
    public int doUpdateLogo(String accountId, String fileName) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: doUpdateLogo Method Start*********************");
        int result = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "update accounts SET acc_logo=? WHERE account_id=" + accountId;
            System.out.println("doUpdateLogo :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, fileName);
            result = preparedStatement.executeUpdate();
        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        System.out.println("********************UserAjaxHandlerServiceImpl :: doUpdateLogo Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date : 08/Jan/2016
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : insertPoAttachment() method is used to insert PO attachment of
     * user.
     *
     * *****************************************************************************
     */
    public String insertPoAttachment(UserAjaxHandlerAction userAjaxHandlerAction, String filePath, String files, String fileName) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: insertPoAttachment Method Start*********************");
        String result = "NotExist";
        int results = 0;
        int userId = 0;
        String userName = "";
        String acclogo = "";
        String poStartDate = "", poEndDate = "", accountName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "INSERT INTO acc_rec_attachment(object_id,req_id,object_type,attachment_path,attachment_name,STATUS,opp_created_by) VALUES(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, userAjaxHandlerAction.getUserId());
            preparedStatement.setString(2, userAjaxHandlerAction.getRequirementId());
            preparedStatement.setString(3, "PO");
            preparedStatement.setString(4, files);
            preparedStatement.setString(5, fileName);
            preparedStatement.setString(6, "Active");
            preparedStatement.setInt(7, userAjaxHandlerAction.getUserSessionId());
            System.out.println("insertPoAttachment :: query string ------>" + queryString);
            results = preparedStatement.executeUpdate();
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: insertPoAttachment Method End*********************");
        return result;
    }

    /**
     * *****************************************************************************
     * Date : 08/Jan/2016
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : poRelease() method is used to get the PO details of a User.
     *
     * *****************************************************************************
     */
    public String poRelease(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************UserAjaxHandlerServiceImpl :: poRelease Method Start*********************");
        String result = "NotExist";
        int userId = 0;
        String userName = "";
        String acclogo = "";
        String poStartDate = "", poEndDate = "", accountName = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "SELECT sa.usr_id,sa.req_id,sa.vendor_id,acc_logo,acc.account_name,sa.po_start_date,sa.po_end_date,sa.target_rate,sa.target_rate_type,sa.over_time_rate,sa.over_time_limit,rcr.created_By,ur.email1"
                    + " FROM serviceagreements sa"
                    + " LEFT OUTER JOIN accounts acc ON (acc.account_id = sa.customer_id)"
                    + " LEFT OUTER JOIN req_con_rel rcr ON (rcr.consultantId = sa.usr_id)"
                    + " LEFT OUTER JOIN users ur ON (rcr.created_By = ur.usr_id)"
                    + " WHERE sa.serviceid=" + userAjaxHandlerAction.getSowId() + " AND rcr.reqId =" + userAjaxHandlerAction.getRequirementId() + "";
            resultSet = statement.executeQuery(queryString);
            System.out.println("poRelease :: query string ------>" + queryString);
            while (resultSet.next()) {
                result = "Exist";
                userAjaxHandlerAction.setUserId(resultSet.getInt("sa.usr_id"));
                userAjaxHandlerAction.setUserName(DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("sa.usr_id")));
                userAjaxHandlerAction.setAcclogo(resultSet.getString("acc_logo"));
                userAjaxHandlerAction.setRequirementId(resultSet.getString("req_id"));
                if (resultSet.getString("sa.po_start_date") != null) {
                    userAjaxHandlerAction.setPoStartDate(DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("sa.po_start_date")));
                } else {
                    userAjaxHandlerAction.setPoStartDate("--");
                }
                if (resultSet.getString("sa.po_end_date") != null) {
                    userAjaxHandlerAction.setPoEndDate(DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("sa.po_end_date")));
                } else {
                    userAjaxHandlerAction.setPoEndDate("--");
                }
                userAjaxHandlerAction.setAccountName(DataSourceDataProvider.getInstance().getAccountNameById(resultSet.getInt("sa.vendor_id")));
                userAjaxHandlerAction.setEmailId(resultSet.getString("email1"));

                userAjaxHandlerAction.setBaseRate(resultSet.getString("sa.target_rate") + " USD per " + resultSet.getString("sa.target_rate_type"));
                if (resultSet.getString("over_time_rate") == null) {
                    userAjaxHandlerAction.setOverTimeRate("--");
                } else {
                    userAjaxHandlerAction.setOverTimeRate(resultSet.getString("over_time_rate") + " USD per " + resultSet.getString("sa.target_rate_type"));
                }
                if (resultSet.getString("over_time_limit") == null) {
                    userAjaxHandlerAction.setOverTimeLimit("--");
                } else {
                    userAjaxHandlerAction.setOverTimeLimit(resultSet.getString("over_time_limit") + " USD per " + resultSet.getString("sa.target_rate_type"));
                }
                if (resultSet.getString("acc_logo") == null) {
                    userAjaxHandlerAction.setAcclogo(Properties.getProperty("DEFAULTLOGO"));
                } else {
                    userAjaxHandlerAction.setAcclogo(resultSet.getString("acc_logo"));
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
        System.out.println("********************UserAjaxHandlerServiceImpl :: poRelease Method End*********************");
        return result;
    }
}
