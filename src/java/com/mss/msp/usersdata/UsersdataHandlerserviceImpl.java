/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usersdata;

import com.mss.msp.security.SecurityServiceProvider;
import com.mss.msp.usr.task.TasksVTO;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocatorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author miracle
 */
public class UsersdataHandlerserviceImpl implements UsersdataHandlerservice {

    private Connection connection;

    public List getAllEmployeeDetails(HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        ArrayList<UserVTO> searchklist = new ArrayList<UserVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {
            queryString = "SELECT usr_id,email1,CONCAT_WS(' ',first_name,last_name) AS name,cur_status,phone1 FROM users WHERE cur_status like 'Active' AND type_of_user !='R' AND org_id = " + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID);


            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserVTO usersVTO = new UserVTO();
                usersVTO.setEmpId(resultSet.getInt("usr_id"));
                usersVTO.setEmpLoginId(resultSet.getString("email1"));
                usersVTO.setEmpName(resultSet.getString("name"));
                usersVTO.setCur_status(resultSet.getString("cur_status"));
                usersVTO.setPhone1(resultSet.getString("phone1"));
                searchklist.add(usersVTO);

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
        return searchklist;

    }

    /**
     *
     * This method is used to getting employee myprofile address
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public UserAddress getEmployeeAddress(HttpServletRequest httpServletRequest, String tableName) throws ServiceLocatorException {
        // ArrayList<UserAddress> addressList = new ArrayList<UserAddress>();
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        UserAddress userAddress = new UserAddress();
        try {
            if ("usr_address".equalsIgnoreCase(tableName)) {
                sqlquery = "select  address_flag,address,city,state,zip,country,phone,address2 from usr_address where usr_id=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) + " and status='Active'";
            } else if ("consultant_address".equalsIgnoreCase(tableName)) {
                System.out.println("this seession login id from consultant-->" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID));
                sqlquery = "select  address_flag,address,city,state,zip,country,phone,address2 from consultant_address where usr_consultant_id=" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) + " and status='Active'";
                System.out.println("query-->" + sqlquery);
            }
            // System.out.println(sqlquery + "  sql query from address");
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);


            while (resultSet.next()) {

                if ("PC".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    userAddress.setP_address(resultSet.getString("address"));
                    userAddress.setP_city(resultSet.getString("city"));
                    userAddress.setP_state(com.mss.msp.util.DataSourceDataProvider.getInstance().getStateName(resultSet.getInt("state")));
                    userAddress.setP_zip(resultSet.getString("zip"));
                    userAddress.setP_country(
                            com.mss.msp.util.DataSourceDataProvider.getInstance().getCountry(resultSet.getInt("country")));
                    userAddress.setP_phone(resultSet.getString("phone"));
                    userAddress.setP_address2(resultSet.getString("address2"));
                    userAddress.setAddress_flag("true");
                    userAddress.setC_address(resultSet.getString("address"));
                    userAddress.setC_city(resultSet.getString("city"));
                    userAddress.setC_state(com.mss.msp.util.DataSourceDataProvider.getInstance().getStateName(resultSet.getInt("state")));
                    userAddress.setC_zip(resultSet.getString("zip"));
                    userAddress.setC_country(com.mss.msp.util.DataSourceDataProvider.getInstance().getCountry(resultSet.getInt("country")));
                    userAddress.setC_phone(resultSet.getString("phone"));
                    userAddress.setC_address2(resultSet.getString("address2"));


                }
                if ("C".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    userAddress.setC_address(resultSet.getString("address"));
                    userAddress.setC_city(resultSet.getString("city"));
                    userAddress.setC_state(com.mss.msp.util.DataSourceDataProvider.getInstance().getStateName(resultSet.getInt("state")));
                    userAddress.setC_zip(resultSet.getString("zip"));
                    userAddress.setC_country(com.mss.msp.util.DataSourceDataProvider.getInstance().getCountry(resultSet.getInt("country")));
                    userAddress.setC_phone(resultSet.getString("phone"));
                    userAddress.setC_address2(resultSet.getString("address2"));
                    userAddress.setAddress_flag("false");
                }
                if ("P".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    userAddress.setP_address(resultSet.getString("address"));
                    userAddress.setP_city(resultSet.getString("city"));
                    userAddress.setP_state(com.mss.msp.util.DataSourceDataProvider.getInstance().getStateName(resultSet.getInt("state")));
                    userAddress.setP_zip(resultSet.getString("zip"));
                    userAddress.setP_country(com.mss.msp.util.DataSourceDataProvider.getInstance().getCountry(resultSet.getInt("country")));
                    userAddress.setP_phone(resultSet.getString("phone"));
                    userAddress.setP_address2(resultSet.getString("address2"));
                    userAddress.setAddress_flag("false");


                }
            }
            //         addressList.add(userAddress);

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
        return userAddress;
    }

    public EmpDetails getEmployeeDetails(int userid) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "SELECT u.first_name,u.last_name,u.dob,u.gender,u.marital_status,u.living_country,u.working_country,u.phone1,u.alias_name,u.image_path,u.office_phone,u.fax,u.email1,u.email2,emp_position,u.cur_status,a.account_name,a.account_id FROM users u LEFT OUTER JOIN accounts a ON ((SELECT org_id FROM users WHERE usr_id=" + userid + ")=a.account_id)  WHERE u.usr_id=" + userid;

        Map titlesMap = new HashMap();
        EmpDetails empdetails = new EmpDetails();
        try {

            //   System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                empdetails.setOrgid(resultSet.getInt("account_id"));
                empdetails.setFirst_name(resultSet.getString("first_name"));
                empdetails.setLast_name(resultSet.getString("last_name"));
                //   empdetails.setDob(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate("dob")));

                //    System.out.println(empdetails.getDob() + " in result set we print");
                // empdetails.setGender(resultSet.getString("gender"));
                if ("M".equalsIgnoreCase(resultSet.getString("gender"))) {
                    empdetails.setGender("1");
                } else {
                    empdetails.setGender("2");
                }
                empdetails.setPhone1(resultSet.getString("phone1"));
                empdetails.setAlias(resultSet.getString("alias_name"));
                // System.out.println(empdetails.getAlias() + " here we print alias name");
                empdetails.setLiving_country(resultSet.getString("living_country"));
                // empdetails.setMarital_status(resultSet.getString("marital_status"));
                if ("S".equalsIgnoreCase(resultSet.getString("marital_status"))) {
                    empdetails.setMarital_status("1");
                } else {
                    empdetails.setMarital_status("2");
                }
                empdetails.setEmp_position(resultSet.getString("emp_position"));
                empdetails.setCorp_phone(resultSet.getString("office_phone"));
                empdetails.setFax(resultSet.getString("fax"));
                empdetails.setEmail1(resultSet.getString("email1"));
                empdetails.setEmail2(resultSet.getString("email2"));
                empdetails.setCurrent_status(resultSet.getString("cur_status"));
                empdetails.setWorking_country(resultSet.getString("working_country"));
                empdetails.setImage_path(resultSet.getString("image_path"));
                empdetails.setAccount_name(resultSet.getString("account_name"));


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
        return empdetails;

    }

    /**
     *
     * This method is used to update employee profile
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public boolean updateEmpDetails(UsersdataHandlerAction usersdataHandlerAction, int userSessionId) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isExceute = false;
        int updatedRows = 0;
        String date;
        //System.out.println("up to here okay");
        date = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate(usersdataHandlerAction.getDob());
        //System.out.println(date + "... here we print date");


        // System.out.println("in update emp details");

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String query = "select cur_status from users where usr_id=" + usersdataHandlerAction.getUserid();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            String status = "";
            while (resultSet.next()) {
                status = resultSet.getString("cur_status");
            }
            if ("Active".equalsIgnoreCase(status)) {
                String queryString = "update users set first_name=?,last_name=?,dob=?,alias_name=?,gender=?,working_country=?,phone1=?,marital_status=?,living_country=?,office_phone=?,fax=?,email1=?,email2=?,cur_status=?,emp_position=?,modified_by=?,modified_date=? where usr_id=?";

                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, usersdataHandlerAction.getFirst_name());
                preparedStatement.setString(2, usersdataHandlerAction.getLast_name());
                preparedStatement.setString(3, date);
                preparedStatement.setString(4, usersdataHandlerAction.getAlias());
                preparedStatement.setString(5, usersdataHandlerAction.getGender());
                preparedStatement.setString(6, usersdataHandlerAction.getWorking_country());
                preparedStatement.setString(7, usersdataHandlerAction.getPhone1());
                preparedStatement.setString(8, usersdataHandlerAction.getMarital_status());
                preparedStatement.setString(9, usersdataHandlerAction.getLiving_country());
                preparedStatement.setString(10, usersdataHandlerAction.getCorp_phone());
                preparedStatement.setString(11, usersdataHandlerAction.getFax());
                preparedStatement.setString(12, usersdataHandlerAction.getEmail1());
                preparedStatement.setString(13, usersdataHandlerAction.getEmail2());
                preparedStatement.setString(14, usersdataHandlerAction.getCurrent_status());
                preparedStatement.setString(15, usersdataHandlerAction.getEmp_position());
                preparedStatement.setInt(16, userSessionId);
                preparedStatement.setTimestamp(17, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
                preparedStatement.setInt(18, usersdataHandlerAction.getUserid());

                preparedStatement.executeUpdate();
                //  System.out.println("statement okay");
            } else {
                //   System.out.println("in else update emp details" + userSessionId);
                String plainPassword = SecurityServiceProvider.generateRandamSecurityKey(6, 6, 1, 1, 0);
                String pwdSalt = SecurityServiceProvider.generateRandamSecurityKey(10, 10, 2, 3, 3);
                String encPwd = SecurityServiceProvider.getEncrypt(plainPassword, pwdSalt);
                callableStatement = connection.prepareCall("{CALL updateEmpProfile(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                //  System.out.println("hello here print after prepare call");
                callableStatement.setString(1, usersdataHandlerAction.getFirst_name());
                callableStatement.setString(2, usersdataHandlerAction.getLast_name());
                callableStatement.setDate(3, com.mss.msp.util.DateUtility.getInstance().getMysqlDate(usersdataHandlerAction.getDob()));
                //  System.out.println("here we print after date changing...........");
                callableStatement.setString(4, usersdataHandlerAction.getAlias());
                callableStatement.setString(5, usersdataHandlerAction.getGender());
                callableStatement.setString(6, usersdataHandlerAction.getWorking_country());
                callableStatement.setString(7, usersdataHandlerAction.getMarital_status());
                callableStatement.setString(8, usersdataHandlerAction.getLiving_country());
                callableStatement.setString(9, usersdataHandlerAction.getEmail1());
                callableStatement.setString(10, usersdataHandlerAction.getEmail2());
                callableStatement.setString(11, usersdataHandlerAction.getCorp_phone());
                callableStatement.setString(12, usersdataHandlerAction.getFax());
                callableStatement.setString(13, usersdataHandlerAction.getPhone1());
                callableStatement.setString(14, usersdataHandlerAction.getCurrent_status());
                callableStatement.setInt(15, 1);
                callableStatement.setInt(16, usersdataHandlerAction.getUserid());
                //  System.out.println("hello here print after prepare call------------> after setting");
                callableStatement.setString(17, pwdSalt);
                callableStatement.setString(18, encPwd);
                callableStatement.setInt(19, userSessionId);
                callableStatement.setString(20, usersdataHandlerAction.getEmp_position());
                callableStatement.registerOutParameter(21, Types.INTEGER);
                //  System.out.println("hello here print after prepare call parameter ");

                isExceute = callableStatement.execute();
                updatedRows = callableStatement.getInt(21);
                //Send Email to the user
                if (updatedRows > 0) {
                    doAddMailManagerStatusActivation(usersdataHandlerAction.getEmail1(), usersdataHandlerAction.getFirst_name(), usersdataHandlerAction.getLast_name(), plainPassword, "serviceBayLoginCredentials", userSessionId);
                }
                // Insert query do add email logger MailManager usersdataHandlerAction.getEmail1() cc bc subject content
                //  System.out.println("update okay");
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    public void doAddMailManagerStatusActivation(String email1, String first_name, String last_name, String plainPassword, String subject, int createdBy) throws SQLException, ServiceLocatorException {
        String toAdd = "", bodyContent = "", bcc = "", cc = "", SubjectStatusActivation = "";
        //java.util.Properties prop = new java.util.Properties();
        // InputStream input = null;
        // input = new FileInputStream("msb.properties");
        // load a properties file
        //  prop.load(input);
        // toAdd = prop.getProperty("MSB.reg");
        //String FromAdd = prop.getProperty("MSB.from");
        String FromAdd = Properties.getProperty("MSB.from");
        String Empname = first_name;
        Empname = Empname.concat("." + last_name);
        //  System.out.println("Empname" + Empname);
        toAdd = email1;
        // System.out.println("Here we print the properties" + toAdd);
        SubjectStatusActivation = subject;
        StringBuilder htmlText = new StringBuilder();
        htmlText.append("<html>");
        htmlText.append("<body>");
        htmlText.append("<table align='center'>");
        htmlText.append("<tr style='background:#99FF33;height:40px;width:100%;'>");
        htmlText.append("<td>");
        htmlText.append("<font color='white' size='4' face='Arial'>");
        htmlText.append("<p>MSB Login Credentials</p>");
        htmlText.append("</font>");
        htmlText.append("</td>");
        htmlText.append("</tr>");
        htmlText.append("<tr>");
        htmlText.append("<td>");
        htmlText.append("<table style='background:#FFFFCC;width:100%;'>");
        htmlText.append("<tr>");
        htmlText.append("<td>");
        htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
        htmlText.append("<p>Hello " + Empname + ",</p><br/>");
        htmlText.append("<p>You have been recently added to Servicebay</p>");
        htmlText.append("<p>Please login with below credentials</p><br/>");
        htmlText.append("Email :  " + toAdd + "<br/>");
        htmlText.append("Password : " + plainPassword + "");
        htmlText.append("<p>If you did not have not registered, you can safely ignore this email.</p>");
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

        new com.mss.msp.util.MailManager().doaddemailLog(FromAdd, toAdd, bcc, cc, SubjectStatusActivation, bodyContent, createdBy);
        // System.out.println("logger is created after Status activating email method.... ");
    }
//modified by praveen

    public Map getAllRoles(int userId, String type_of_relation) throws ServiceLocatorException {
        Map orgRoles = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT  role_id ,role_name FROM roles  WHERE 1=1 and org_type='" + type_of_relation + "' ";
        /* if ("SA".equalsIgnoreCase(type_of_user)) {
         queryString += " and org_type='M'";
         }
         if ("AC".equalsIgnoreCase(type_of_user)) {
         queryString += " and org_type='C'";
         }
         if ("VC".equalsIgnoreCase(type_of_user)) {
         queryString += " and org_type='V'";
         }*///System.out.println(query);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                orgRoles.put(resultSet.getInt("role_id"), resultSet.getString("role_name"));

            }

        } catch (SQLException ex) {
            // System.out.println("getOrgAllRoles method-->" + ex.getMessage());
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
        //System.out.println("orgRolesMap-->" + orgRoles);
        return orgRoles;

    }
//modified by praveen

    public Map getAssignedRoles(int userId) throws ServiceLocatorException {
        Map rolesMap = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select r.role_id as roleId,role_name from usr_roles ur left outer join roles r on(ur.role_id=r.role_id) where usr_id=" + userId;
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

//modified by praveen
    public Map getNotAssignedRoles(int userId, String type_of_relation) throws ServiceLocatorException {
        Map notAssignedRoles = new HashMap();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT  role_id ,role_name FROM roles  WHERE role_id NOT IN(SELECT role_id FROM usr_roles WHERE usr_id=" + userId + ") and org_type='" + type_of_relation + "' ";

        /*  if ("SA".equalsIgnoreCase(type_of_user)) {
         queryString += " and org_type='M'";
         }
         if ("AC".equalsIgnoreCase(type_of_user)) {
         queryString += " and org_type='C'";
         }
         if ("VC".equalsIgnoreCase(type_of_user)) {
         queryString += " and org_type='V'";
         }*/

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                notAssignedRoles.put(resultSet.getInt("role_id"), resultSet.getString("role_name"));
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

        return notAssignedRoles;


    }

    /**
     *
     * This method is used to getting department names return Map object added
     * by praveen<pkatru@miraclesoft.com>
     */
    public Map getDepartment_Names(UsersdataHandlerAction aThis) {
        Map mapDept_names = new HashMap();
        //Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String sqlquery = "SELECT a.account_name, d.dept_id ,d.dept_name FROM departments  AS d LEFT OUTER JOIN accounts  AS a ON (d.org_id=a.account_id) WHERE d.org_id=(SELECT org_id FROM users WHERE usr_id=?)";
            preparedStatement = connection.prepareStatement(sqlquery);

            preparedStatement.setInt(1, aThis.getUserid());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mapDept_names.put(resultSet.getString("dept_id"), resultSet.getString("dept_name"));

            }

        } catch (SQLException ex) {
            mapDept_names = null;
            ex.printStackTrace();
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
            return mapDept_names;
        }
    }

    public int insertRoles(String[] assignedRoleIds, int employeeId, int primaryRoleId) throws ServiceLocatorException {
        Statement statement = null;

        /**
         * The statement is useful to execute the above queryString
         */
        ResultSet resultSet;

        /**
         * The connection is object to useful to create the statement object
         */
        Connection connection = null;
        String queryString;
        int insertedRows = 0;
        int updateRows = 0;
        int deletedRows = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            /**
             * The queryString is created depends on the employeeId
             */
            queryString = "DELETE FROM usr_roles WHERE usr_id=" + employeeId;
            deletedRows = statement.executeUpdate(queryString);
            statement.close();
            statement = null;
            statement = connection.createStatement();


            /**
             * it loops the roles.length and inserts the data into database for
             * each addedVals
             *
             * @throws NullPointerException If a NullPointerException exists and
             * its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
            //System.out.println("assignedRoleIds.length-->" + assignedRoleIds.length);
            for (int counter = 0; counter < assignedRoleIds.length; counter++) {
                if (Integer.parseInt(assignedRoleIds[counter]) == primaryRoleId) {
                    queryString = "Insert into usr_roles(primary_flag,usr_id,role_id) values(1," + employeeId + ", " + assignedRoleIds[counter] + ")";
                } else {
                    queryString = "Insert into usr_roles(primary_flag,usr_id,role_id) values(0," + employeeId + ", " + assignedRoleIds[counter] + ")";
                }
                insertedRows = statement.executeUpdate(queryString);
            }


        } catch (Exception e) {
            throw new ServiceLocatorException(e);
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
        return insertedRows;
    }

    public List<UserVTO> getCsrList(UsersdataHandlerAction usersdataHandlerAction, int userId) throws ServiceLocatorException {
        List<UserVTO> userVTOList = new ArrayList<UserVTO>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
        connection = ConnectionProvider.getInstance().getConnection();

        // String queryString = "SELECT  role_id ,role_name FROM roles  WHERE role_id NOT IN(SELECT role_id FROM usr_roles WHERE usr_id=" + userId + ")";

        String queryString = "SELECT DISTINCT(u.usr_id),concat(u.first_name,'.',u.last_name) as name,u.email1,u.cur_status FROM users u "
                + " LEFT OUTER JOIN csrorgrel csr  ON(u.usr_id=csr.csr_id) LEFT OUTER JOIN usr_roles ur "
                + " ON(u.usr_id=ur.usr_id) LEFT OUTER JOIN roles r ON(ur.role_id=r.role_id)"
                + " WHERE ur.role_id=1 AND primary_flag=1";

        if (usersdataHandlerAction.getEmail1() != null) {
            queryString = queryString + " and u.email1 like '%" + usersdataHandlerAction.getEmail1() + "%'  ";
        }
        if (usersdataHandlerAction.getEmpName() != null) {
            queryString = queryString + " and (u.first_name like '%" + usersdataHandlerAction.getEmpName() + "%'  "
                    + "or u.last_name like '%" + usersdataHandlerAction.getEmpName() + "%')  ";
        }

//        if (usersdataHandlerAction.getEmpName() != null) {
//            queryString = queryString + " and u.first_name like '%" + usersdataHandlerAction.getEmpName() + "%'  ";
//        }
        if (usersdataHandlerAction.getStatus() != null) {
            if ("All".equals(usersdataHandlerAction.getStatus())) {
                queryString = queryString + " and u.cur_status like '%%'  ";
            } else {
                queryString = queryString + " and u.cur_status= '" + usersdataHandlerAction.getStatus() + "'  ";
            }
        }


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserVTO userVTO = new UserVTO();
                userVTO.setEmpId(resultSet.getInt("u.usr_id"));
                userVTO.setFirst_name(resultSet.getString("name"));
                userVTO.setEmail1(resultSet.getString("u.email1"));
                userVTO.setCur_status(resultSet.getString("u.cur_status"));
                userVTO.setNoOfAccounts(dsdp.getCsrAccountCount(resultSet.getInt("u.usr_id")));
                userVTOList.add(userVTO);
            }
            System.out.println("In getCsrList------>" + queryString);

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
        return userVTOList;
    }

    public List<UserVTO> getCsrAccounts(UsersdataHandlerAction usersdataHandlerAction) throws ServiceLocatorException {
        List<UserVTO> userVTOList = new ArrayList<UserVTO>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        // String queryString = "SELECT  role_id ,role_name FROM roles  WHERE role_id NOT IN(SELECT role_id FROM usr_roles WHERE usr_id=" + userId + ")";


        String queryString = "SELECT csr.csr_id,a.account_id,a.account_name,csr.status FROM accounts a LEFT OUTER JOIN csrorgrel csr ON(a.account_id=csr.org_id) WHERE csr.status='Active' AND csr.csr_id=" + usersdataHandlerAction.getUserId();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserVTO userVTO = new UserVTO();
                userVTO.setEmpId(resultSet.getInt("csr.csr_id"));
                userVTO.setOrgId(resultSet.getInt("a.account_id"));
                userVTO.setAccountName(resultSet.getString("a.account_name"));
                userVTO.setCur_status(resultSet.getString("csr.status"));
                userVTOList.add(userVTO);
            }
            System.out.println("In getCsrList------>" + queryString);

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
        return userVTOList;
    }

    public List<UserVTO> getEmployeeCategorization(UsersdataHandlerAction usersdataHandlerAction, int userOrgSessionId) throws ServiceLocatorException {
        List<UserVTO> userVTOList = new ArrayList<UserVTO>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
        connection = ConnectionProvider.getInstance().getConnection();


        String queryString = " SELECT uc.sub_cat,lk.grpname,r.role_name,uc.id,u.usr_id,u.org_id,uc.cat_type,"
                + "CONCAT(u.first_name,'.',u.last_name) AS name,uc.status,uc.created_by,uc.is_primary "
                + "FROM usr_grouping uc "
                + "LEFT OUTER JOIN lkusr_group lk ON(lk.id=uc.cat_type)"
                + " LEFT OUTER JOIN users u ON(uc.usr_id=u.usr_id) LEFT OUTER JOIN usr_roles ur ON(ur.usr_id=u.usr_id)"
                + " LEFT OUTER JOIN roles r  ON(ur.role_id=r.role_id) "
                + " WHERE uc.status='Active' AND u.org_id=" + userOrgSessionId + "";


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserVTO userVTO = new UserVTO();
                userVTO.setGroupingId(resultSet.getInt("uc.id"));
                userVTO.setEmpId(resultSet.getInt("u.usr_id"));
                userVTO.setCatogoryGroup(resultSet.getString("lk.grpname"));

                // userVTO.setOrgId(resultSet.getInt("a.account_id"));
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
                userVTOList.add(userVTO);
            }
            System.out.println("In getCsrList------>" + queryString);

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
        return userVTOList;
    }
    //praveen

    public void getUserGroupingData(UsersdataHandlerAction usersdataHandlerAction) throws ServiceLocatorException {
        connection = ConnectionProvider.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        String queryString = "SELECT CONCAT(first_name,'.',last_name) NAMES,sub_cat, ug.id,ug.usr_id,ug.cat_type,ug.is_primary,ug.STATUS,ug.description FROM usr_grouping ug JOIN users u ON (ug.usr_id=u.usr_id) WHERE ug.id=" + usersdataHandlerAction.getGroupingId();
        System.out.println("this is getUserGroupingData  query-->" + queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                usersdataHandlerAction.setGroupingId(resultSet.getInt("id"));
                usersdataHandlerAction.setUserName(resultSet.getString("NAMES"));
                usersdataHandlerAction.setUserId(resultSet.getInt("usr_id"));
                usersdataHandlerAction.setUsrCatType(resultSet.getInt("cat_type"));
                if (resultSet.getInt("is_primary") == 1) {
                    usersdataHandlerAction.setPrimaryvalue(true);
                } else {
                    usersdataHandlerAction.setPrimaryvalue(false);
                }
                String str = resultSet.getString("sub_cat");
                StringTokenizer stk = new StringTokenizer(str, ",");
                List list = new ArrayList();

                while (stk.hasMoreTokens()) {
                    list.add(stk.nextToken());
                }
                // System.out.println("list----------->"+list);
                //  List list = new ArrayList(idsMap.keySet());

                usersdataHandlerAction.setCatValuesList(list);
                usersdataHandlerAction.setUsrDescription(resultSet.getString("description"));
                usersdataHandlerAction.setUsrStatus(resultSet.getString("status"));
                usersdataHandlerAction.setCatValuesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory(resultSet.getInt("cat_type")));
                System.out.println("this is printing --->" + resultSet.getString("sub_cat"));
                System.out.println("here fine for result set");
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
    public List getHomeRedirectDetails(UsersdataHandlerAction usersdataHandlerAction) throws ServiceLocatorException {
        List<HomeVTO> homeVTOList = new ArrayList<HomeVTO>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();

        String queryString = "SELECT h.id,a.account_name,h.type_of_user,r.role_name,h.action_name,h.STATUS,h.description "
                + "FROM home_redirect_action h "
                + "LEFT OUTER JOIN accounts a ON(a.account_id=h.org_id) "
                + "LEFT OUTER JOIN roles r ON(r.role_id=h.primaryrole)";
        if ("AC".equalsIgnoreCase(usersdataHandlerAction.getAccountType()) || "VC".equalsIgnoreCase(usersdataHandlerAction.getAccountType())) {
            // if ("AC".equals(usersdataHandlerAction.getAccountType())) {
            queryString = queryString + " WHERE type_of_user = '" + usersdataHandlerAction.getAccountType() + "' AND h.org_id= " + usersdataHandlerAction.getSessionOrgId();
//            } else if ("VC".equals(usersdataHandlerAction.getAccountType())) {
//                queryString = queryString + " WHERE type_of_user = 'VC'";
//            }
            // queryString = queryString + " 
        }
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            int counter = 0;
            while (resultSet.next()) {
                HomeVTO homeVTO = new HomeVTO();
                homeVTO.setHomeId(resultSet.getInt("id"));
                if (resultSet.getString("account_name") == null || resultSet.getString("account_name").equals("")) {
                    homeVTO.setAccountName("All");
                } else {
                    homeVTO.setAccountName(resultSet.getString("account_name"));
                }
                homeVTO.setRoleName(resultSet.getString("role_name"));
                if (resultSet.getString("type_of_user").equalsIgnoreCase("SA")) {
                    homeVTO.setTypeOfUSer("Site Admin");
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("AC")) {
                    homeVTO.setTypeOfUSer("Customer");
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("VC")) {
                    homeVTO.setTypeOfUSer("Vendor");
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("E")) {
                    homeVTO.setTypeOfUSer("Employee");
                } else if (resultSet.getString("type_of_user").equalsIgnoreCase("CO")) {
                    homeVTO.setTypeOfUSer("Consultant");
                } else {
                    homeVTO.setTypeOfUSer("");
                }
                //homeVTO.setTypeOfUSer(resultSet.getString("type_of_user"));
                homeVTO.setActionName(resultSet.getString("action_name"));
                homeVTO.setStatus(resultSet.getString("STATUS"));
                homeVTO.setDescription(resultSet.getString("description"));
                homeVTOList.add(homeVTO);
            }
            System.out.println("In homeRedirect List query String------>" + queryString);

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
        return homeVTOList;
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
     public HomeVTO getHomeRedirectDetailsForEdit(UsersdataHandlerAction usersdataHandlerAction) throws ServiceLocatorException {
        List<HomeVTO> homeVTOList = new ArrayList<HomeVTO>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        HomeVTO homeVTO = new HomeVTO();

        String queryString = "SELECT h.id,a.account_name,h.type_of_user,r.role_name,h.action_name,h.STATUS,"
                + "h.description,l.acc_type_name,h.org_id,h.primaryrole   "
                + "FROM home_redirect_action h "
                + "LEFT OUTER JOIN accounts a ON(a.account_id=h.org_id) "
                + "LEFT OUTER JOIN roles r ON(r.role_id=h.primaryrole) "
                + "LEFT OUTER JOIN org_rel o ON(o.related_org_Id=h.org_id) "
                + "LEFT OUTER JOIN lk_acc_type l ON(l.id=o.acc_type) "
                + "WHERE h.id=" + usersdataHandlerAction.getHomeRedirectActionId();
        System.out.println(">>>>>>>>>>>>>query for ---->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                homeVTO.setHomeId(resultSet.getInt("id"));
                if (resultSet.getString("account_name") == null) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>account name is null");
                    homeVTO.setAccountName("");
                } else {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>account name in not null");
                    homeVTO.setAccountName(resultSet.getString("account_name"));
                }
                if (resultSet.getString("acc_type_name") == null) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>acc_type_name  is null");
                    homeVTO.setAccountType(resultSet.getString("type_of_user"));
                } else {
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>acc_type_name  in not null");
                    if (resultSet.getString("acc_type_name").equalsIgnoreCase("Customer")) {
                        if ("SA".equalsIgnoreCase(resultSet.getString("type_of_user"))) {
                            homeVTO.setAccountType("M");
                        } else {
                            homeVTO.setAccountType("AC");
                        }
                    } else if (resultSet.getString("acc_type_name").equalsIgnoreCase("Vendor")) {
                        homeVTO.setAccountType("VC");
                    } else {
                        homeVTO.setAccountType("M");
                    }
                }
                if (0 == Integer.parseInt(resultSet.getString("org_id"))) {
                    homeVTO.setAccountId("0");
                } else {
                    homeVTO.setAccountId(resultSet.getString("org_id"));
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>PRIMARY ROLE?>>>>>>>>>>" + resultSet.getString("primaryrole"));
                homeVTO.setRoleName(resultSet.getString("primaryrole"));
                usersdataHandlerAction.setActionName(com.mss.msp.util.DataSourceDataProvider.getInstance().getActionNamesList(usersdataHandlerAction.getSessionOrgId(),resultSet.getInt("primaryrole"),usersdataHandlerAction.getAccountType()));
                homeVTO.setTypeOfUSer(resultSet.getString("type_of_user"));
                homeVTO.setActionName(resultSet.getString("action_name"));
                homeVTO.setStatus(resultSet.getString("STATUS"));
                homeVTO.setDescription(resultSet.getString("description"));
                //homeVTOList.add(homeVTO);
            }
            System.out.println("In homeRedirect List query String------>" + queryString);

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
        return homeVTO;
    }

    public void doXlsFileUpload(UsersdataHandlerAction usersdataHandlerAction, String xlsfileFileName) throws ServiceLocatorException {
//        Connection connection = null;
//        Statement statement = null;
//        String queryString = null;
        Logger reportsLog = Logger.getLogger(UsersdataHandlerserviceImpl.class.getName());
        Handler fileHandler = null;

        try {
            fileHandler = new FileHandler(usersdataHandlerAction.getFilePath() + File.separator + xlsfileFileName + ".log");
            reportsLog.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            reportsLog.info("Importing Accounts");

            Workbook workbook = Workbook.getWorkbook(usersdataHandlerAction.getFileWithPath());
            Sheet sheet = workbook.getSheet(0);
            int count = sheet.getColumns();
            System.out.println(" i am printing colums:" + count);
            Cell columns = null;
            List<String> list = new ArrayList<String>();
            Map columnsMap = new HashMap();
            for (int i = 0; i < count; i++) {
                columns = sheet.getCell(i, 0);
                list.add(columns.getContents());
                columnsMap.put(i, columns.getContents() + "(" + i + ",0)");
            }
            columnsMap.put(-1, "Others");
            usersdataHandlerAction.setColumnsMap(columnsMap);
            workbook.close();
            //connection = ConnectionProvider.getInstance().getConnection();
//            try {  
//                statement = connection.createStatement();
//                queryString = "insert into utility_logger(logger,uploaded_file,created_by) values("+xlsfileFileName+","+usersdataHandlerAction.getFilePath()+","+usersdataHandlerAction.getUserSessionId()+")";
//                statement.executeUpdate(queryString);
//            } catch (SQLException ex) {
//                Logger.getLogger(UsersdataHandlerserviceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } catch (IOException ex) {
            Logger.getLogger(UsersdataHandlerserviceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(UsersdataHandlerserviceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCellContentValues(List list, UsersdataHandlerAction usersdataHandlerAction, int count, String type, String columsString) throws ServiceLocatorException {
        String exist_str = "";
        String fail_str = "";
        String result = "";
        String res = "";
        String fileName = "";
        String finalPath = "";
        CallableStatement callableStatement = null;
        StringTokenizer st2 = new StringTokenizer(usersdataHandlerAction.getPath(), File.separator);
        while (st2.hasMoreTokens()) {
            fileName = st2.nextToken();
        }
        StringTokenizer coulmsdata = new StringTokenizer(columsString, "|");
        int excelColValue[] = new int[50];
        int k = 0;
        while (coulmsdata.hasMoreElements()) {
            excelColValue[k] = Integer.parseInt(coulmsdata.nextElement().toString());
            k++;
        }
        ListIterator<String[]> litr = null;
        litr = list.listIterator();
        int updatedRows;
        boolean isExceute = false;
        String arrayData[] = new String[count];
        String accRecord = "";
        while (litr.hasNext()) {
            arrayData = litr.next();
            for (int i = 0; i < count; i++) {
                accRecord += arrayData[i] + "|";
            }
            accRecord += "^";
        }
        System.out.println("--------------------" + fileName);
        try {
            if ("Accounts".equalsIgnoreCase(type)) {
                System.out.println("---------------> in imple" + usersdataHandlerAction.getFilePath());
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call loadExcel(?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, accRecord);
                System.out.println("after 1 st call" + accRecord);
                callableStatement.setString(2, "^");
                System.out.println("----------");
                callableStatement.setString(3, usersdataHandlerAction.getFilePath());
                callableStatement.setString(4, fileName);
                callableStatement.setInt(5, usersdataHandlerAction.getUserSessionId());
                callableStatement.setString(6, usersdataHandlerAction.getAccType());
                callableStatement.setString(7, File.separator);
                callableStatement.registerOutParameter(8, Types.VARCHAR);
                callableStatement.registerOutParameter(9, Types.VARCHAR);
                callableStatement.registerOutParameter(10, Types.VARCHAR);
                System.out.println("after out param--------------");
                callableStatement.execute();
                System.out.println("-----------exe-----------");
                exist_str = callableStatement.getString(8);
                fail_str = callableStatement.getString(9);
                result = callableStatement.getString(10);
            } else if ("Contacts".equalsIgnoreCase(type)) {

                System.out.println("---------------> in imple" + usersdataHandlerAction.getFilePath());
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call sp_addUserContacts(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, accRecord);
                System.out.println("after 1 st call" + accRecord);
                callableStatement.setString(2, "^");
                System.out.println("----------");
                callableStatement.setString(3, usersdataHandlerAction.getFilePath());
                callableStatement.setString(4, fileName);
                callableStatement.setInt(5, usersdataHandlerAction.getUserSessionId());
                callableStatement.setString(6, File.separator);
//                callableStatement.setInt(7, usersdataHandlerAction.getAccountSearchOrgId());
                callableStatement.registerOutParameter(7, Types.VARCHAR);
                callableStatement.registerOutParameter(8, Types.VARCHAR);
                callableStatement.registerOutParameter(9, Types.VARCHAR);
                System.out.println("after out param--------------");
                callableStatement.execute();
                System.out.println("-----------exe-----------");
                exist_str = callableStatement.getString(7);
                fail_str = callableStatement.getString(8);
                result = callableStatement.getString(9);
                System.out.println("this is for users------------>");
            }
            //  int id = callableStatement.getInt(10);
            System.out.println("---------rs---------->" + result);
            System.out.println("---------exist_count---------->" + exist_str);
            System.out.println("---------exist_str---------->" + fail_str);
            usersdataHandlerAction.setSp_res(result);
            usersdataHandlerAction.setSp_exists(exist_str);
            usersdataHandlerAction.setSp_failure(fail_str);
            //usersdataHandlerAction.setExcel_id(id);
            if (!"".equals(usersdataHandlerAction.getSp_exists()) || !"".equals(usersdataHandlerAction.getSp_failure())) {
                //String ecel = resultantExcelFile(usersdataHandlerAction, fileName, exist_str, excelColValue, k);
                List<String> record = new ArrayList<String>();

                StringTokenizer str = null;
                if (!"".equals(usersdataHandlerAction.getSp_failure())) {
                    if (!"".equals(usersdataHandlerAction.getSp_exists())) {
                        str = new StringTokenizer(fail_str + "^" + exist_str, "^");
                    } else {
                        str = new StringTokenizer(fail_str, "^");
                    }
                } else if (!"".equals(usersdataHandlerAction.getSp_exists())) {
                    if (!"".equals(usersdataHandlerAction.getSp_failure())) {
                        str = new StringTokenizer(fail_str + "^" + exist_str, "^");
                    } else {
                        str = new StringTokenizer(exist_str, "^");
                    }
                }

                int columnCount = 1;
                while (str.hasMoreTokens()) {
                    record.add(str.nextToken());
                    columnCount++;
                }
                List<String[]> flist = new ArrayList<String[]>();
                count = record.size();
                CellView cv = new CellView();
                // cv.setAutosize(true);
                int l = 1;

                for (int ki = 0; ki < columnCount - 1; ki++) {
                    // str = new StringTokenizer(record.get(k), "|", true);
                    int i = 0;
                    //  String[] fStringList = new String[k+1];
                    String[] tokens1 = record.get(ki).split("\\|", -1);
                    String[] fStringList = new String[tokens1.length];
                    // for (int i = 0; i < tokens1.length - 1; i++) {
                    for (String string : tokens1) {

                        fStringList[i] = string;

                        i++;
                    }

                    flist.add(fStringList);
                }
                doCreateFailedExcelFile(flist, usersdataHandlerAction.getPath(), excelColValue, columnCount, type);
            }
//            else if (usersdataHandlerAction.getSp_failure() != "") {
//                //String ecel = resultantExcelFile(usersdataHandlerAction, fileName, fail_str, excelColValue, k);
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return res;
    }

    public String resultantExcelFile(UsersdataHandlerAction usersdataHandlerAction, String fileName, String failRecord) throws Exception {
        String result = "";
        int count;
        System.out.println("---------------- i n  excel");
        int[] colNumbers = null;
//                File fileobj = new File(usersdataHandlerAction.getFilePath());
//		Workbook workbook = Workbook.getWorkbook(fileobj);		
//		Sheet sheet = workbook.getSheet(0);
//        int count1 = sheet.getColumns();
//		int rowsCount = sheet.getRows();
//				Cell rows = null;
//                String dataArray[]=new String[colNumbers.length];
//				for (int j=0;j<1;j++) 
//				{	
//					for(int i=0;i<count1;i++){
//                    rows = sheet.getCell(colNumbers[i], j);
//                    dataArray[i]=rows.getContents();
//					//System.out.println("--->"+dataArray[i]);
//					}
//					
//                }


        //System.out.println("get abs path-->"+fileobj.getPath());

        //System.out.println("get abs path-->"+fileobj.getParent());
        //System.out.println("this is file name-->"+fileobj.getName());

        WritableWorkbook wworkbook;
        wworkbook = Workbook.createWorkbook(new File(usersdataHandlerAction.getFilePath() + File.separator + "resultantFile" + fileName));
        WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
        WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
        cellFont.setColour(Colour.PINK);

        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        cellFormat.setBackground(Colour.BLUE);
        Label label1 = new Label(0, 0, "Company Name", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(1, 0, "Url", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(2, 0, "Mail Extentions", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(3, 0, "Address1", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(4, 0, "Address2", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(5, 0, "City", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(6, 0, "Zip Code", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(7, 0, "Country", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(8, 0, "State", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(9, 0, "Phone Number", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(10, 0, "Fax", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(11, 0, "Industry", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(12, 0, "Region", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(13, 0, "territory", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(14, 0, "No.of Employees", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(15, 0, "TaxId", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(16, 0, "stockSymbol", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(17, 0, "Revenue", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(18, 0, "Description", cellFormat);
        wsheet.addCell(label1);
        label1 = new Label(19, 0, "Result", cellFormat);
        wsheet.addCell(label1);
        Label label;
        System.out.println("--------------- in downoad xls-----------" + failRecord);
        List<String> record = new ArrayList<String>();
        StringTokenizer str = new StringTokenizer(failRecord, "^");
        while (str.hasMoreTokens()) {
            record.add(str.nextToken());
        }
        count = record.size();
        CellView cv = new CellView();
        // cv.setAutosize(true);
        int l = 1;
        for (int k = 0; k < count; k++) {
            // str = new StringTokenizer(record.get(k), "|", true);
            int i = 0;
            String[] tokens1 = record.get(k).split("\\|", -1);
            // for (int i = 0; i < tokens1.length - 1; i++) {
            for (String string : tokens1) {
                if (string.equalsIgnoreCase("")) {
                    string = null;
                }
                System.out.println(string);
                label = new Label(i, l, string);
                wsheet.setColumnView(i, 30);
                wsheet.addCell(label);
                i++;
            }

            l++;
        }

        System.out.println("--------------" + record);
        wworkbook.write();
        wworkbook.close();
        return result;

    }

    public List defaultSearch(UsersdataHandlerAction usersdataHandlerAction) throws ServiceLocatorException {
        String result = "";
        ArrayList utilityList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String current_date = "";
        connection = ConnectionProvider.getInstance().getConnection();
        System.out.println("-----------------" + current_date);
        String queryString = "SELECT * FROM utility_logger";
        System.out.println("--------------" + usersdataHandlerAction.getCreatedDate());
        if (usersdataHandlerAction.getCreatedDate() != null && usersdataHandlerAction.getCreatedDate().toString().isEmpty() == false) {
            current_date = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDash(usersdataHandlerAction.getCreatedDate());
        } else {
            current_date = com.mss.msp.util.DateUtility.getInstance().getCurrentSQLDate();
        }
        queryString = queryString + " WHERE created_date LIKE '%" + current_date + "%'";
        System.out.println("--------------------" + usersdataHandlerAction.getStatus());
        if (usersdataHandlerAction.getStatus() != null && !"DF".equalsIgnoreCase(usersdataHandlerAction.getStatus())) {
            queryString = queryString + " AND utility_status LIKE '" + usersdataHandlerAction.getStatus() + "'";
        }
        queryString = queryString + " AND created_by = " + usersdataHandlerAction.getUserSessionId();
        System.out.println(">>>>>>>>>>>>>query for ---->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserVTO users = new UserVTO();
                System.out.println("---------------" + resultSet.getString("resultant_file"));
                users.setLogger(resultSet.getString("logger"));
                users.setLog_id(resultSet.getInt("log_id"));
                users.setUtility_status(resultSet.getString("utility_status"));
                users.setUploaded_file(resultSet.getString("uploaded_file").substring(resultSet.getString("uploaded_file").lastIndexOf(File.separator) + 1));
                users.setResultant_file(resultSet.getString("resultant_file").substring(resultSet.getString("resultant_file").lastIndexOf(File.separator) + 1));
                users.setLoggerCreatedDate(DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("created_date")));
                users.setLoggerFlag(usersdataHandlerAction.getLoggerFlag());
                utilityList.add(users);
            }
            System.out.println("------>" + queryString);

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
        return utilityList;
    }

    public int getIndustryValue(String industry) throws ServiceLocatorException {
        int industryValue = 0;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call getIndustry(?,?)}");
            callableStatement.setString(1, industry);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            industryValue = callableStatement.getInt(2);

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return industryValue;
    }

    public int getCountryValue(String country) throws ServiceLocatorException {
        int countryValue = 0;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call getCountry(?,?)}");
            callableStatement.setString(1, country);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            countryValue = callableStatement.getInt(2);

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return countryValue;
    }

    public int getStateValue(String state, String country) throws ServiceLocatorException {
        int stateValue = 0;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call getState(?,?,?)}");
            callableStatement.setString(1, state);
            callableStatement.setString(2, country);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();
            stateValue = callableStatement.getInt(3);

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return stateValue;
    }

    public void doCreateFailedExcelFile(List failedList, String path, int[] colNumbers, int countCol, String type) {
        System.out.println("the path is hear create excel file " + path + "---" + countCol);

        try {
            File fileobj = new File(path);
            Workbook workbook = Workbook.getWorkbook(fileobj);
            Sheet sheet = workbook.getSheet(0);
            int count = sheet.getColumns();
            int rowsCount = sheet.getRows();
            System.out.println("hear i am printkn count-->" + count);
            Cell rows = null;
            String dataArray[] = new String[count];
            for (int j = 0; j < 1; j++) {
                for (int i = 0; i < count; i++) {
                    rows = sheet.getCell(i, j);
                    dataArray[i] = rows.getContents();
                    //System.out.println("printing data--->"+dataArray[i]);
                }

            }


            //System.out.println("get abs path-->"+fileobj.getPath());

            //System.out.println("get abs path-->"+fileobj.getParent());
            //System.out.println("this is file name-->"+fileobj.getName());

            ListIterator<String[]> litr = null;
            litr = failedList.listIterator();
            int records = failedList.size();
            String arrayData[] = new String[count];
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File(fileobj.getParent() + File.separator + "resultantFile" + fileobj.getName()));
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
            cellFont.setColour(Colour.PINK);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            cellFormat.setBackground(Colour.BLUE);
            //System.out.println("this is i am println arraData length-->"+arrayData.length);
            for (int i = 0; i < count; i++) {
                Label label = new Label(i, 0, dataArray[i], cellFormat);
                wsheet.addCell(label);
            }
            int j = 1;
            while (litr.hasNext()) {
                arrayData = litr.next();
                for (int i = 0; i < count; i++) {
                    //System.out.println("array data  is--->" + Arrays.toString(arrayData));
                    //System.out.println("col numbers data is--->" +Arrays.toString(colNumbers));
                    if (arrayData[colNumbers[i]] != null && !"null".equalsIgnoreCase(arrayData[colNumbers[i]])) {
                        Label label = new Label(i, j, arrayData[colNumbers[i]]);
                        wsheet.addCell(label);
                    } else {
                        Label label = new Label(i, j, "");
                        wsheet.addCell(label);
                    }
                }
                j++;

            }
            wworkbook.write();
            wworkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
