package com.mss.msp.general;

import com.mss.msp.general.GeneralService;
import com.mss.msp.security.SecurityServiceProvider;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Iterator;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import com.mss.msp.util.Properties;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class GeneralServiceImpl implements GeneralService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /**
     * Creates a new instance of GeneralServiceIml
     */
    public GeneralServiceImpl() {
    }

    public String generateUserId(String mailId) {

        /*@param atOccurance is used to store index of mailId upto @ char*/
        int atOccurance = mailId.indexOf("@");

        /*finally those string return here*/
        return mailId.substring(0, atOccurance).toLowerCase();
    }

    public int doUpdateResetPassword(String password, String email) throws ServiceLocatorException {

        //System.out.println("::::doUpdateResetPassword Impll.. :::");
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        int isUpdated = 0;
        String hexa16digitSalt = SecurityServiceProvider.generateRandamSecurityKey(16, 16, 4, 4, 4);
        //String queryString = "UPDATE tblCreTchComments SET ModifiedDate = ?, ModifiedBy = ?, Comments = ?, Status = ? WHERE id = ? AND CreId = ?";
        String encPwd = SecurityServiceProvider.getEncrypt(password.trim(), hexa16digitSalt);
        //System.out.println("plain text-->"+password.trim());
        String queryString = "UPDATE usr_reg SET password=?,salt=? WHERE login_id=?";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);


            preparedStatement.setString(1, encPwd);
            preparedStatement.setString(2, hexa16digitSalt);
            preparedStatement.setString(3, email);


            isUpdated = preparedStatement.executeUpdate();

            // System.err.println("query result -->"+isUpdated);
        } catch (SQLException se) {
            // System.err.println("11 ---se-->"+se.getMessage());
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                //  System.out.println("se"+se.getMessage());
                throw new ServiceLocatorException(se);
            }
        }

        return isUpdated;
    }

    public int doPasswordLinkStatusUpdate(String email) throws ServiceLocatorException {

        // System.out.println("::::doPasswordLinkStatusUpdate Impll.. :::");
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        int isUpdated = 0;

        String queryString = "UPDATE forgotpasswordlink SET status = ? WHERE email_id = ?";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);


            preparedStatement.setString(1, "InActive");
            preparedStatement.setString(2, email.trim());


            isUpdated = preparedStatement.executeUpdate();
            //System.out.println("For Got password linkUpdated successfully ");
        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
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
            } catch (SQLException se) {
                // System.err.println("linkupdation flow exception --->"+se.getMessage());
                throw new ServiceLocatorException(se);

            }
        }

        return isUpdated;
    }

    public String forGotPwdLinkStatus(String mailId, String ssid) throws ServiceLocatorException {

        String curStatus = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "SELECT status FROM forgotpasswordlink WHERE email_id like '" + mailId + "' and code ='" + ssid + "'";
        //System.out.println("queryString-->"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                curStatus = resultSet.getString("status");
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
        return curStatus;
    }

    public String getPrimaryAction(int orgId, int roleId) throws ServiceLocatorException {
        String action = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        Connection connection = null;
        try {
            
            queryString = " SELECT action_name from home_redirect_action where org_id=" + orgId + " and primaryrole=" + roleId;
            System.err.println("queryString--->"+queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                action = resultSet.getString("action_name");
            }
            //httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PRIMARYROLE, roleId);
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

        return action;
    }

    /**
     * *****************************************************************************
     * Date : April 28 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * getCountriesNames() method can be used to get the countries And returns
     * countries Map
     * *****************************************************************************
     */
    public Map getCountriesNames() {
        Map countries = new LinkedHashMap();
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");
            String hqlQuery = "select cv.id,cv.name from CountryVto cv";
            Query query = session.createQuery(hqlQuery);
            List list = query.list();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                countries.put(o[0], o[1]);
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

        return countries;
    }

    /**
     * *****************************************************************************
     * Date : April 29 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * getStatesOfCountry() method can be used to get the states by passing
     * country id And returns resultString
     * *****************************************************************************
     */
    public Map getStatesMapOfCountry(HttpServletRequest httpServletRequest, int id) {
        Map states = new LinkedHashMap();
        String resultString = "";
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");

            String hqlQuery = "select id,name from State  WHERE countryId=:countryid";

            Query query = session.createQuery(hqlQuery);
            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                //resultString += o[0] + "#" + o[1] + "^";
                states.put(o[0], o[1]);

            }

        } catch (ServiceLocatorException e) {
            System.out.println(e);
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
        //System.out.println("List of States are"+states);
        return states;
    }

    public String getStatesStringOfCountry(HttpServletRequest httpServletRequest, int id) {
        Map states = new LinkedHashMap();
        String resultString = "";
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");

            String hqlQuery = "select id,name from State  WHERE countryId=:countryid";

            Query query = session.createQuery(hqlQuery);
            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                resultString += o[0] + "#" + o[1] + "^";
                //states.put(o[0],o[1]);

            }

        } catch (ServiceLocatorException e) {
            System.out.println(e);
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
        //System.out.println("List of States are"+states);
        return resultString;
    }

    public String getStatesOfCountry(HttpServletRequest httpServletRequest, int id) {
        Map states = new LinkedHashMap();
        String resultString = "";
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");

             String hqlQuery = "select id,name from State s WHERE countryId=:countryid order by s.name asc";

            Query query = session.createQuery(hqlQuery);
            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                resultString += o[0] + "#" + o[1] + "^";
                //resultString += resultSet.getInt("title_id") + "#" + resultSet.getString("title_name") + "^";
//System.out.println(o[0]);
//System.out.println(o[1]);
//System.out.println("success");
            }

        } catch (ServiceLocatorException e) {
            System.out.println(e);
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
        //System.out.println("List of States are"+states);
        return resultString;
    }

    /**
     * *************************************
     *
     * @getDefaultRequirementDashBoardDetails() update status in requirement
     * table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public List getDefaultRequirementDashBoardDetails(GeneralAction generalAction) throws ServiceLocatorException {
        String resultString = "";
        ArrayList<CsrDashBoardVTO> csrDashBoardList = new ArrayList<CsrDashBoardVTO>();
        String queryString = "";
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            connection = ConnectionProvider.getInstance().getConnection();
            if (generalAction.getTypeOfUser().equalsIgnoreCase("SA")) {
                queryString = "SELECT COUNT(requirement_id) AS total,"
                        + "COUNT(IF(req_status='O',1, NULL)) 'Open',"
                        + "COUNT(IF(req_status='R',1, NULL)) 'Released',"
                        + "COUNT(IF(req_status='C',1, NULL)) 'Closed',"
                        + "a.account_name,a.account_id "
                        + "FROM acc_requirements "
                        + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                        + "WHERE DATE_FORMAT(req_st_date,'%Y')=" + year + " "
                        + "GROUP BY a.account_id";
            } else {
                queryString = "SELECT COUNT(requirement_id) AS total,"
                        + "COUNT(IF(req_status='O',1, NULL)) 'Open',"
                        + "COUNT(IF(req_status='R',1, NULL)) 'Released',"
                        + "COUNT(IF(req_status='C',1, NULL)) 'Closed',"
                        + "a.account_name,a.account_id "
                        + "FROM acc_requirements "
                        + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                        + "LEFT OUTER JOIN csrorgrel csr ON(a.account_id=csr.org_id)"
                        + "WHERE DATE_FORMAT(req_st_date,'%Y')=" + year + " "
                        + "AND csr.csr_id=" + generalAction.getUserSessionId() + " "
                        + "GROUP BY a.account_id";
            }

            System.out.println("query...DashBoard....>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                CsrDashBoardVTO csrDashBoardVTO = new CsrDashBoardVTO();
                csrDashBoardVTO.setTotal(resultSet.getString("total"));
                csrDashBoardVTO.setOpen(resultSet.getString("Open"));
                csrDashBoardVTO.setReleased(resultSet.getString("Released"));
                csrDashBoardVTO.setClosed(resultSet.getString("Closed"));
                csrDashBoardVTO.setCustomerName(resultSet.getString("account_name"));
                csrDashBoardVTO.setAccountId(resultSet.getString("account_id"));
                csrDashBoardList.add(csrDashBoardVTO);
            }
            System.out.println("result=========>" + resultString);
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
        return csrDashBoardList;
    }
     /**
     * *************************************
     *
     * @verifyCurrentPassword() This method is 
     * used to verify the current password
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:09/11/2015
     *
     **************************************
     */
    public int verifyCurrentPassword(GeneralAction generalAction) throws ServiceLocatorException {

        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "";
        String curPwd = "";
        String pwdSalt = "";

        queryString = "SELECT usr_id, salt,PASSWORD  from usr_reg where  usr_id=" + generalAction.getUserSessionId();
       // System.out.println("queryString-->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                curPwd = resultSet.getString("PASSWORD");
                pwdSalt = resultSet.getString("salt");
            }
            String encPwd = SecurityServiceProvider.getEncrypt(generalAction.getCurrentPwd().trim(), pwdSalt.trim());
           // System.out.println("password --->" + generalAction.getCurrentPwd());
           // System.out.println("password is--->" + encPwd);
            if (curPwd.equals(encPwd)) {
                count = 1;
            } else {
                count = 0;
            }
            System.out.println("Count-->" + count);
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
    public String UserRegistration(GeneralAction generalAction) throws ServiceLocatorException {

        String result = null;
        Connection connection = null;
        Connection connection1 = null;
        CallableStatement callableStatement = null;
        CallableStatement callableStatement1 = null;
        boolean isExceute = false;
        int updatedRows = 0;
        try {
            connection1 = ConnectionProvider.getInstance().getConnection();
            callableStatement1 = connection1.prepareCall("{CALL addAccounts(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement1.setString(1, generalAction.getOrgName());
            callableStatement1.setString(2, generalAction.getOrg_web_address());
            callableStatement1.setString(3, generalAction.getOrg_address1());
            callableStatement1.setString(4, generalAction.getOrg_address2());
            callableStatement1.setInt(5, generalAction.getOrg_state());

            callableStatement1.setInt(6, generalAction.getOrg_country());
            callableStatement1.setString(7, generalAction.getOrg_city());
            callableStatement1.setString(8, generalAction.getOrg_zip());
            callableStatement1.setInt(9, 0);//created by.  in sp frmUsr_id
            System.out.println("-------------" + generalAction.getOffice_Phone());
            callableStatement1.setString(10, generalAction.getOffice_Phone());
            callableStatement1.setString(11, generalAction.getOrg_fax());
            callableStatement1.setString(12, null);//industry type
            callableStatement1.setString(13, null);//it budget
            callableStatement1.setString(14, null);
            callableStatement1.setString(15, "");//tax id

            callableStatement1.setString(16, null);
            callableStatement1.setString(17, "");//stock symbol
                callableStatement1.setString(18,generalAction.getNoOfEmployees() );
            callableStatement1.setString(19, "");//description
            callableStatement1.setString(20, null);//revenue
            callableStatement1.setInt(21, 10001); //session orgId
            callableStatement1.setInt(22, 5); //Account Type check for null
            System.out.println("-------" + generalAction.getOrg_web_address());

            callableStatement1.setString(23, generalAction.getEmail_ext());//email ext
            callableStatement1.registerOutParameter(24, Types.INTEGER);
            callableStatement1.execute();
            int status = callableStatement1.getInt(24);
            if (status > 0) {
                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{CALL addAccContact(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, generalAction.getFirst_name());
                callableStatement.setString(2, generalAction.getMiddle_name());
                callableStatement.setNull(3, Types.NULL);//file path
                callableStatement.setString(4, generalAction.getLast_name());
                callableStatement.setString(5, generalAction.getOffice_emailId());
                callableStatement.setString(6, generalAction.getOffice_Phone());
                callableStatement.setInt(7, status);// generalAction.getAccountSearchOrgId());
                callableStatement.setString(8, generalAction.getPhone());
                callableStatement.setInt(9, 0);//not used in stored procedur e so 0
                callableStatement.setString(10, generalAction.getAddress1());
                callableStatement.setString(11, generalAction.getAddress2());
                callableStatement.setString(12, generalAction.getCity());
                callableStatement.setString(13, generalAction.getZip());
                callableStatement.setInt(14, generalAction.getCountry());
                callableStatement.setInt(15, generalAction.getState1());
                callableStatement.setString(16, generalAction.getPhone2());
                callableStatement.setString(17, "VC");
                callableStatement.setString(18, "");// gender 
                callableStatement.setInt(19, 0);//created by 0
                callableStatement.setString(20, generalAction.getEmailId());
                callableStatement.setInt(21, -1);
                callableStatement.setString(22, generalAction.getTitle());
                callableStatement.setString(23, null);//Exp
                callableStatement.setInt(24, -1);//Industry
                callableStatement.setString(25,null);//SSn
                callableStatement.setString(26,null);//education
                callableStatement.setString(27,null);//skills
                callableStatement.setString(28,File.separator);
                callableStatement.registerOutParameter(29, Types.INTEGER);
                callableStatement.registerOutParameter(30, Types.INTEGER);

                System.out.println("hello here print after prepare call parameter ");

                isExceute = callableStatement.execute();
                updatedRows = callableStatement.getInt(29);
                int usrId = callableStatement.getInt(30);
                System.out.println("usrId-------->" + usrId);

                if (updatedRows > 0) {
                    doAddMailManager(generalAction.getOffice_emailId(), generalAction.getFirst_name(), generalAction.getLast_name(), "serviceBayUserRegistration");
                    result = "Added successfully";
                }
                System.out.println("-----status " + status);
            }
            System.out.println("is updatedRows " + updatedRows);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
                if (callableStatement1 != null) {
                    callableStatement1.close();
                    callableStatement1 = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return result;
    }

     public void doAddMailManager(String email1, String first_name, String last_name, String subject) throws SQLException, ServiceLocatorException {
        String toAdd = "", bodyContent = "", bcc = "", cc = "", SubjectStatusActivation = "";
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
        htmlText.append("<p>Registered Successfully.</p>");
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
        htmlText.append("<p>You have been recently Registered in Servicebay</p>");
        htmlText.append("Email :  " + toAdd + "<br/>");
       
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

        new com.mss.msp.util.MailManager().doaddemailLog(FromAdd, toAdd, bcc, cc, SubjectStatusActivation, bodyContent,0);
        // System.out.println("logger is created after Status activating email method.... ");
    }

}
