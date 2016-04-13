/**
 * *************************************
 *
 * @Author:praveen kumar<pkatru@miraclesoft.com>
 * @Author:rama krishna<lankireddy@miraclesoft.com>
 * @Created Date:05/05/2015
 *
 *
 * *************************************
 */
package com.mss.msp.vendor.vendorajax;

import com.mss.msp.security.SecurityServiceProvider;
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
import javax.servlet.http.HttpServletRequest;
import com.mss.msp.vendor.VendorListVTO;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class VendorAjaxHandlerServiceImpl implements VendorAjaxHandlerService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * *****************************************************************************
     * Date : MAY 04, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorStates() method is used to get states by country code
     *
     * *****************************************************************************
     */
    public String getVendorStates(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorStates Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT id, NAME FROM lk_states WHERE countryId=" + vendorAjaxHandler.getCountryId();
            System.out.println("getVendorStates :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("id") + "|" + resultSet.getString("name") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorStates Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : MAY 04, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorSearchDetails() method is used to search and get vendor
     * details.
     *
     * *****************************************************************************
     */
    public String getVendorSearchDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorSearchDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            Map map = DataSourceDataProvider.getInstance().getMyTeamMembers(vendorAjaxHandler.getSessionId());
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

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT account_id,ac.created_by,org_id,last_access_date,ac.STATUS,account_name,account_url,acc_type,type_of_relation,acc_industry_name,aa.acc_city,lks.NAME,abc.acc_phone "
                    + "FROM accounts ac LEFT OUTER JOIN org_rel orl ON (ac.account_id=orl.related_org_id) "
                    + " LEFT OUTER JOIN acc_address aa ON (orl.related_org_id=aa.acc_id) "
                    + "LEFT OUTER JOIN acc_basic_info abc ON (orl.related_org_id=abc.acc_id) LEFT OUTER JOIN  lk_acc_industry_type it "
                    + "ON (it.id=acc_type) LEFT OUTER JOIN lk_states lks ON (lks.id=aa.acc_state)"
                    + "WHERE orl.org_id=" + vendorAjaxHandler.getSessionOrgId() + " AND acc_type=5 ";
            if ("My".equalsIgnoreCase(vendorAjaxHandler.getVendorFlag())) {
                queryString = queryString + " and ac.created_by = '" + vendorAjaxHandler.getSessionId() + "'";
            }
            if ("Team".equalsIgnoreCase(vendorAjaxHandler.getVendorFlag())) {
                queryString = queryString + " and ac.created_by in(" + retrunValue + ")";
                if (!"-1".equalsIgnoreCase(vendorAjaxHandler.getTeamMembers())) {

                    queryString = queryString + " and ac.created_by = '" + vendorAjaxHandler.getTeamMembers() + "'";
                }
            }

            if (vendorAjaxHandler.getVendorName().trim().isEmpty() == false) {
                queryString += " and account_name like '%" + vendorAjaxHandler.getVendorName() + "%'";
            }
            if (vendorAjaxHandler.getVendorURL().trim().isEmpty() == false) {
                queryString += " and account_url like '%" + vendorAjaxHandler.getVendorURL() + "%'";
            }
            if (vendorAjaxHandler.getVendorPhone().trim().isEmpty() == false) {
                queryString += " and abc.acc_phone like '%" + vendorAjaxHandler.getVendorPhone() + "%'";
            }
            if (Integer.parseInt(
                    vendorAjaxHandler.getVendorCountry()) > 0) {
                queryString += " and aa.acc_country = " + vendorAjaxHandler.getVendorCountry();
            }
            if (Integer.parseInt(vendorAjaxHandler.getVendorState()) > 0) {
                queryString += " and  lks.id = " + vendorAjaxHandler.getVendorState();
            }
            if (!vendorAjaxHandler.getVendorStatus().equals("-1")) {
                queryString += " and ac.status = '" + vendorAjaxHandler.getVendorStatus() + "'";
            }

            System.out.println("getVendorSearchDetails :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString +=
                        resultSet.getInt("account_id") + "|"
                        + "" + "|"
                        + resultSet.getString("account_name") + "|"
                        + resultSet.getString("account_url") + "|"
                        + resultSet.getString("acc_city") + "|"
                        + resultSet.getString("name") + "|"
                        + resultSet.getString("status") + "|"
                        + resultSet.getString("acc_phone") + "|"
                        + resultSet.getString("last_access_date") + "|"
                        + resultSet.getString("acc_industry_name") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorSearchDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 8:30 PM IST
     *
     * Author : rama krishna<lankireddy@miraclesoft.com>
     *
     * ForUse : updateVendorDetails() method is used to update vendor details.
     *
     * *****************************************************************************
     */
    public int updateVendorDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {

        System.out.println("********************VendorAjaxHandlerServiceImpl :: updateVendorDetails Method Start*********************");
        Connection connection = null;
        CallableStatement callableStatement = null;
        int updateResult = 0;
        boolean isExceute = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("updateVendorDetails :: procedure name : updateVendorDetails ");
            callableStatement = connection.prepareCall("{CALL updateVendorDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, vendorAjaxHandler.getVendorName());

            callableStatement.setString(2, vendorAjaxHandler.getVendorURL());
            callableStatement.setString(3, vendorAjaxHandler.getVendorStatus());
            callableStatement.setString(4, vendorAjaxHandler.getVendorAddress1());
            callableStatement.setString(5, vendorAjaxHandler.getVendorAddress2());
            callableStatement.setInt(6, vendorAjaxHandler.getVendorType());
            callableStatement.setString(7, vendorAjaxHandler.getVendorPhone());
            callableStatement.setString(8, vendorAjaxHandler.getVendorFax());
            callableStatement.setString(9, vendorAjaxHandler.getVendorState());
            callableStatement.setString(10, vendorAjaxHandler.getVendorCity());
            callableStatement.setString(11, vendorAjaxHandler.getVendorCountry());
            callableStatement.setString(12, vendorAjaxHandler.getVendorZip());
            callableStatement.setInt(13, vendorAjaxHandler.getVendorIndustry());
            callableStatement.setString(14, vendorAjaxHandler.getVendorRegion());
            callableStatement.setString(15, vendorAjaxHandler.getVendorTerritory());
            callableStatement.setInt(16, vendorAjaxHandler.getEmpCount());
            callableStatement.setString(17, vendorAjaxHandler.getVendorDescription());
            callableStatement.setInt(18, vendorAjaxHandler.getVendorBudget());
            callableStatement.setString(19, vendorAjaxHandler.getVendorTaxid());
            callableStatement.setString(20, vendorAjaxHandler.getStockSymbol());
            callableStatement.setInt(21, vendorAjaxHandler.getVendorRvenue());
            callableStatement.setInt(22, vendorAjaxHandler.getVendorId());
            callableStatement.setInt(23, vendorAjaxHandler.getUserSessionId());
            callableStatement.setTimestamp(24, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            callableStatement.registerOutParameter(25, Types.INTEGER);
            isExceute = callableStatement.execute();
            updateResult = callableStatement.getInt(25);

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
        System.out.println("********************VendorAjaxHandlerServiceImpl :: updateVendorDetails Method End*********************");
        return updateResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorContactDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getVendorContactDetails(int orgId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorContactDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        VendorListVTO vendorListVTO = new VendorListVTO();
        int i = 0;
        try {

            queryString = " SELECT usr_id,concat(first_name,'.',last_name) as name,email1,phone1,cur_status FROM users WHERE  cur_status='Active' AND type_of_user LIKE 'VC' AND org_id='" + orgId + "'";
            System.out.println("getVendorContactDetails :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorListVTO.setUserId(resultSet.getInt("usr_id"));
                vendorListVTO.setVendorName(resultSet.getString("name"));
                vendorListVTO.setEmail1(resultSet.getString("email1"));
                vendorListVTO.setPhone1(resultSet.getString("phone1"));
                vendorListVTO.setStatus(resultSet.getString("cur_status"));

                resultString += vendorListVTO.getUserId() + "|" + vendorListVTO.getVendorName() + "|" + vendorListVTO.getEmail1() + "|" + vendorListVTO.getPhone1() + '|' + vendorListVTO.getStatus() + '^';
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
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorContactDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : saveVendorContacts() method is used to
     *
     * *****************************************************************************
     */
    public String saveVendorContacts(int userId, int userSessionId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: saveVendorContacts Method Start*********************");
        Connection connection = null;
        CallableStatement callableStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        boolean isExceute = false;
        int updatedRows = 0;

        int i = getUserCount(userId);
        if (i == 1) {
            resultString = "User contact is already Registered";
        } else {
            String query = "select first_name,last_name,email1,cur_status,created_by from users where usr_id=" + userId;
            System.out.println("saveVendorContacts :: query string ------>" + query);
            try {
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                String status = "";
                String email = "";
                int createdBy = 0;
                String firstName = "";
                String lastName = "";
                while (resultSet.next()) {
                    firstName = resultSet.getString("first_name");
                    lastName = resultSet.getString("last_name");
                    status = resultSet.getString("cur_status");
                    email = resultSet.getString("email1");
                    createdBy = resultSet.getInt("created_by");

                }
                if ("Active".equalsIgnoreCase(status)) {

                    String plainPassword = SecurityServiceProvider.generateRandamSecurityKey(6, 6, 1, 1, 0);
                    String pwdSalt = SecurityServiceProvider.generateRandamSecurityKey(10, 10, 2, 3, 3);
                    String encPwd = SecurityServiceProvider.getEncrypt(plainPassword, pwdSalt);
                    System.out.println("saveVendorContacts :: procedure name : addContacts ");
                    callableStatement = connection.prepareCall("{CALL addContacts(?,?,?,?,?,?,?)}");
                    callableStatement.setString(1, status);
                    callableStatement.setInt(2, userId);
                    callableStatement.setString(3, email);
                    callableStatement.setString(4, pwdSalt);
                    callableStatement.setString(5, encPwd);
                    callableStatement.setInt(6, createdBy);
                    callableStatement.registerOutParameter(7, Types.INTEGER);
                    isExceute = callableStatement.execute();
                    updatedRows = callableStatement.getInt(7);
                    if (updatedRows > 0) {

                        doAddMailManagerStatusActivation(email, firstName, lastName, plainPassword, "serviceBayLoginCredentialsForVendorContact", userSessionId);

                        resultString = "User Contact Registered Succesfully";
                    }

                } else {
                    resultString = "User Contact is not in Active";
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
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if (statement != null) {
                        statement.close();
                        statement = null;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: saveVendorContacts Method End*********************");
        return resultString;


    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getUserCount() method is used
     *
     * *****************************************************************************
     */
    public int getUserCount(int userId) {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getUserCount Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        try {

            queryString = "select usr_id from usr_reg where usr_id=" + userId;
            System.out.println("getUserCount :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                i++;
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getUserCount Method End*********************");
        return i;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doAddMailManagerStatusActivation() method is used
     *
     * *****************************************************************************
     */
    public void doAddMailManagerStatusActivation(String email1, String first_name, String last_name, String plainPassword, String subject, int createdBy) throws SQLException, ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: doAddMailManagerStatusActivation Method Start*********************");
        String toAdd = "", bodyContent = "", bcc = "", cc = "", SubjectStatusActivation = "";

        String FromAdd = Properties.getProperty("MSB.from");
        String Empname = first_name;
        Empname = Empname.concat("." + last_name);

        toAdd = email1;

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
        System.out.println("********************VendorAjaxHandlerServiceImpl :: doAddMailManagerStatusActivation Method End*********************");
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorContactSearchResults() method is used
     *
     * *****************************************************************************
     */
    public String getVendorContactSearchResults(VendorAjaxHandler vendorAjaxHandler, int orgId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorContactSearchResults Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        VendorListVTO vendorListVTO = new VendorListVTO();
        try {
            queryString = "SELECT usr_id,concat(first_name,'.',last_name) as name,email1,phone1,cur_status FROM users WHERE org_id=" + orgId + " AND type_of_user LIKE 'VC' ";


            if (!"".equals(vendorAjaxHandler.getVendorEmail())) {
                queryString = queryString + " and  email1 like '%" + vendorAjaxHandler.getVendorEmail() + "%' ";
            }
            if (!"".equals(vendorAjaxHandler.getVendorFirstName())) {
                queryString = queryString + " and first_name like '%" + vendorAjaxHandler.getVendorFirstName() + "%' ";
            }
            if (!"-1".equals(vendorAjaxHandler.getVendorLastName())) {
                queryString = queryString + " and last_name like '%" + vendorAjaxHandler.getVendorLastName() + "%' ";
            }
            if (!"-1".equalsIgnoreCase(vendorAjaxHandler.getVendorPhone())) {
                queryString = queryString + " and phone1 like '%" + vendorAjaxHandler.getVendorPhone() + "%' ";
            }
            if (!"DF".equalsIgnoreCase(vendorAjaxHandler.getVendorStatus())) {

                queryString = queryString + " and cur_status = '" + vendorAjaxHandler.getVendorStatus() + "' ";
            }


            System.out.println("getVendorContactSearchResults :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorListVTO.setUserId(resultSet.getInt("usr_id"));
                vendorListVTO.setVendorName(resultSet.getString("name"));
                vendorListVTO.setEmail1(resultSet.getString("email1"));
                vendorListVTO.setPhone1(resultSet.getString("phone1"));
                vendorListVTO.setStatus(resultSet.getString("cur_status"));

                resultString += vendorListVTO.getUserId() + "|" + vendorListVTO.getVendorName() + "|" + vendorListVTO.getEmail1() + "|" + vendorListVTO.getPhone1() + '|' + vendorListVTO.getStatus() + '^';


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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorContactSearchResults Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorsListByTireType() method is used
     *
     * *****************************************************************************
     */
    public String getVendorsListByTireType(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorsListByTireType Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT vendor_id,account_name FROM accounts JOIN customer_ven_rel c ON vendor_id=account_id JOIN lk_vendor_type lk ON vendor_tier_id=lk.id WHERE c.STATUS='Active' AND vendor_tier_id = " + vendorAjaxHandler.getTireType() + " AND customer_id= " + vendorAjaxHandler.getOrgId();
            System.out.println("getVendorsListByTireType :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("vendor_id") + "|" + resultSet.getString("account_name") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorsListByTireType Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/May/2015
     *
     * Author : praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : SaveVendorsAssociationDetals() method is used
     *
     * *****************************************************************************
     */
    public int SaveVendorsAssociationDetals(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: SaveVendorsAssociationDetals Method Start*********************");
        int count = 0;
        try {
            StringTokenizer st2 = new StringTokenizer(vendorAjaxHandler.getVendorList(), ",");
            while (st2.hasMoreElements()) {
                count += doInsertVendorAssociation(vendorAjaxHandler.getReq_id(), st2.nextElement().toString(), vendorAjaxHandler.getAccessTime(), vendorAjaxHandler.getUserSessionId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: SaveVendorsAssociationDetals Method End*********************");
        return count;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doInsertVendorAssociation() method is used
     *
     * *****************************************************************************
     */
    public int doInsertVendorAssociation(String req_id, String vendorId, String accessTime, int sessionId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: doInsertVendorAssociation Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        int count = 0;
        try {
            if (isAssociationRecordAvailable(req_id, vendorId)) {
                connection = ConnectionProvider.getInstance().getConnection();
                queryString = "insert into req_ven_rel(req_id,ven_id,status,req_access_time,created_by) values(?,?,'Active',?,?)";
                System.out.println("doInsertVendorAssociation :: query string ------>" + queryString);
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, req_id);
                preparedStatement.setString(2, vendorId);
                preparedStatement.setString(3, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDashWithTimeWithOutSeconds(accessTime));
                preparedStatement.setInt(4, sessionId);
                count = preparedStatement.executeUpdate();
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
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
        System.out.println("********************VendorAjaxHandlerServiceImpl :: doInsertVendorAssociation Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date : 06/05/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorAssociationDetails() method is used update vendor
     * details.
     *
     * *****************************************************************************
     */
    public String getVendorAssociationDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorAssociationDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        DateUtility dateUtility = new DateUtility();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT req.id,req.req_id,req.ven_id,req.STATUS,req.req_access_time,"
                    + "ar.acc_id,ar.req_name,crl.vendor_tier_id,lkvt.vendor_type,acc.account_name,"
                    + "ar.req_created_by AS createdUser "
                    + "FROM req_ven_rel req "
                    + "JOIN acc_requirements ar ON (req.req_id= ar.requirement_id) "
                    + "JOIN customer_ven_rel crl ON (ar.acc_id=crl.customer_id AND req.ven_id=crl.vendor_id) "
                    + "JOIN accounts acc ON  (req.Ven_id=acc.account_id) "
                    + "JOIN lk_vendor_type lkvt ON(lkvt.id=crl.vendor_tier_id) "
                    + "WHERE req.STATUS='Active' AND crl.STATUS='Active' AND req.req_id=" + vendorAjaxHandler.getRequirementId() + " Limit 50";
            System.out.println("getVendorAssociationDetails :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                int ven_id = resultSet.getInt("ven_id");
                int req_id = resultSet.getInt("req_id");
                resultString += resultSet.getString("id") + "|" + resultSet.getString("vendor_type") + "|" + resultSet.getString("createdUser") + "|" + dateUtility.getInstance().convertToviewFormatInDashWithTime(resultSet.getString("req_access_time")) + "|" + resultSet.getString("status") + "|" + resultSet.getString("account_name") + "|" + resultSet.getString("req_name") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfSubmisions(req_id, ven_id) + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getAvgRateByOrg(req_id, ven_id) + "|" + resultSet.getString("ven_id") + "^";

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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorAssociationDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/05/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : searchVendorAssociationDetails() method is used search vendor
     * details.
     *
     * *****************************************************************************
     */
    public String searchVendorAssociationDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: searchVendorAssociationDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        DateUtility dateUtility = new DateUtility();
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT req.id,req.req_id,req.ven_id,req.STATUS,req.req_access_time,ar.acc_id,"
                    + "ar.req_name,crl.vendor_tier_id,lkvt.vendor_type,acc.account_name,"
                    + "ar.req_created_by AS createdUser "
                    + "FROM req_ven_rel req "
                    + "JOIN acc_requirements ar ON (req.req_id= ar.requirement_id) "
                    + "JOIN customer_ven_rel crl ON (ar.acc_id=crl.customer_id AND req.ven_id=crl.vendor_id) "
                    + "JOIN accounts acc ON  (req.Ven_id=acc.account_id) "
                    + "JOIN lk_vendor_type lkvt ON(lkvt.id=crl.vendor_tier_id) "
                    + "WHERE crl.STATUS='Active' and req.req_id=" + vendorAjaxHandler.getRequirementId();
            if (vendorAjaxHandler.getTireType() != -1) {
                queryString += " AND crl.vendor_tier_id=" + vendorAjaxHandler.getTireType();
            }
            if (!vendorAjaxHandler.getStatus().equalsIgnoreCase("DF")) {
                queryString += " AND req.STATUS='" + vendorAjaxHandler.getStatus() + "'";
            }
            System.out.println("searchVendorAssociationDetails :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("id") + "|" + resultSet.getString("vendor_type") + "|" + resultSet.getString("createdUser") + "|" + dateUtility.getInstance().convertToviewFormatInDashWithTime(resultSet.getString("req_access_time")) + "|" + resultSet.getString("status") + "|" + resultSet.getString("account_name") + "|" + resultSet.getString("req_name") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfSubmisions(resultSet.getInt("req_id"), resultSet.getInt("ven_id")) + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getAvgRateByOrg(resultSet.getInt("req_id"), resultSet.getInt("ven_id")) + "|" + resultSet.getString("ven_id") + "^";

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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: searchVendorAssociationDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 14/May/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : editVendorAssociation() method is used retrieve vendor details
     * based on vendorId.
     *
     * *****************************************************************************
     */
    public String editVendorAssociation(int vendorId, int orgId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: editVendorAssociation Method Start*********************");
        String vendorString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = " SELECT lkv.id ,ac.account_name ,req.STATUS FROM req_ven_rel req JOIN"
                    + " accounts ac ON(req.ven_id=ac.account_id) JOIN customer_ven_rel con"
                    + " ON(req.ven_id=con.vendor_Id) JOIN lk_vendor_type lkv ON(con.vendor_tier_id=lkv.id)"
                    + " WHERE  req.id=" + vendorId + " AND customer_id= " + orgId;
            System.out.println("editVendorAssociation :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorString += resultSet.getString("id") + "|" + resultSet.getString("account_name") + "|" + resultSet.getString("STATUS") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: editVendorAssociation Method End*********************");
        return vendorString;
    }

    /**
     * *****************************************************************************
     * Date : 15/May/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getVendorNames() method is used
     *
     * *****************************************************************************
     */
    public String getVendorNames(int tireId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorNames Method Start*********************");
        String vendorString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT a.account_id, a.account_name  FROM accounts a JOIN org_rel o ON(o.related_org_id=a.account_id) WHERE o.type_of_vendor=" + tireId;
            System.out.println("getVendorNames :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorString += resultSet.getString("account_id") + "#" + resultSet.getString("account_name") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorNames Method End*********************");
        return vendorString;
    }

    /**
     * *****************************************************************************
     * Date : 15/May/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : updateVendorAssociationDetails() method is used
     *
     * *****************************************************************************
     */
    public int updateVendorAssociationDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: updateVendorAssociationDetails Method Start*********************");
        String queryStringupdate = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int isUpdated = 0;
        int i = 0;
        try {

            queryStringupdate = " update req_ven_rel SET status=? WHERE id =" + vendorAjaxHandler.getVendorId();

            System.out.println("updateVendorAssociationDetails :: query string ------>" + queryStringupdate);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryStringupdate);

            preparedStatement.setString(1, vendorAjaxHandler.getStatusEdit());
            isUpdated = preparedStatement.executeUpdate();
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
        System.out.println("********************VendorAjaxHandlerServiceImpl :: updateVendorAssociationDetails Method End*********************");
        return isUpdated;

    }

    /**
     * *****************************************************************************
     * Date : 15/May/2015
     *
     * Author : praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : isAssociationRecordAvailable() method is used for checking
     * whether the record is existing or not.
     *
     * *****************************************************************************
     */
    public boolean isAssociationRecordAvailable(String req_id, String vendorId) {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: isAssociationRecordAvailable Method Start*********************");
        boolean status = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT COUNT(req_id) as record FROM req_ven_rel WHERE req_id=? AND ven_id=? ";
            System.out.println("isAssociationRecordAvailable :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, req_id);
            preparedStatement.setString(2, vendorId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("record") > 0) {
                    status = false;
                } else {
                    status = true;
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
        System.out.println("********************VendorAjaxHandlerServiceImpl :: isAssociationRecordAvailable Method End*********************");
        return status;
    }

    /**
     * *****************************************************************************
     * Date : June 02 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * ForUse : getVendorDashboardList()
     *
     * *****************************************************************************
     */
    public String getVendorDashboardList(int year, int month, int sessionOrgId) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorDashboardList Method Start*********************");
        String vendorString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String queryString = "SELECT MONTHNAME(rcr.created_Date) AS MONTH,"
                    + "COUNT(requirement_id) AS requirements,"
                    + "COUNT(IF(rcr.STATUS='Processing',1, NULL)) 'Processing',"
                    + "COUNT(IF(rcr.STATUS='Selected',1, NULL)) 'Selected',"
                    + "COUNT(IF(rcr.STATUS='Rejected',1, NULL)) 'Rejected',"
                    + "COUNT(IF(rcr.STATUS='Servicing',1, NULL)) 'Servicing',"
                    + "COUNT(IF(rcr.STATUS='SOWApproved',1, NULL)) 'SOWApproved',"
                    + "COUNT(IF(rcr.STATUS='ShortListed',1, NULL)) 'ShortListed'"
                    + " FROM acc_requirements a LEFT OUTER JOIN req_con_rel rcr "
                    + "ON(a.requirement_id=rcr.reqId) LEFT OUTER JOIN accounts ac "
                    + "ON(ac.account_id=rcr.createdbyOrgId) WHERE rcr.createdbyOrgId= " + sessionOrgId + " ";

            if (year != 0) {
                queryString = queryString + " AND EXTRACT(YEAR FROM rcr.created_Date)= " + year + "";
            }

            if (month != -1) {
                queryString = queryString + " AND EXTRACT(MONTH FROM rcr.created_Date)= " + month + "";
            }
            queryString = queryString + " GROUP BY DATE_FORMAT(rcr.created_Date, '%m')";

            System.out.println("getVendorDashboardList :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorString += resultSet.getString("MONTH") + "|" + resultSet.getString("requirements") + "|" + resultSet.getString("Processing") + "|" + resultSet.getString("Selected") + "|" + resultSet.getString("Rejected") + "|" + resultSet.getString("Servicing") + "|" + resultSet.getString("ShortListed") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorDashboardList Method End*********************");
        return vendorString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorReqDashBoardGrid()
     *
     * *****************************************************************************
     */
    public String getVendorReqDashBoardGrid(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException {
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorReqDashBoardGrid Method Start*********************");
        String vendorString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT req_name,reqId,consultantId,c.usr_id AS consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME,c.phone1,c.email1,c.created_by ,status,c.org_id "
                    + "FROM users c JOIN req_con_rel ON(consultantId=c.usr_id) JOIN acc_requirements ON(requirement_id=reqId) WHERE "
                    + "c.created_by = " + vendorAjaxHandler.getUserSessionId() + " AND c.type_of_user='IC' ";

            if (vendorAjaxHandler.getCandidateName() != null && !"".equals(vendorAjaxHandler.getCandidateName())) {
                queryString = queryString + " AND (first_name LIKE '%" + vendorAjaxHandler.getCandidateName() + "%' OR last_name LIKE '%" + vendorAjaxHandler.getCandidateName() + "%') ";
            }

            if (vendorAjaxHandler.getJobTitle() != null && !"".equals(vendorAjaxHandler.getJobTitle())) {
                queryString = queryString + " AND req_name LIKE '%" + vendorAjaxHandler.getJobTitle() + "%'";
            }
            queryString = queryString + " LIMIT 100";

            System.out.println("getVendorReqDashBoardGrid :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                vendorString += resultSet.getString("req_name") + "|" + resultSet.getString("NAME") + "|" + resultSet.getString("status") + "^";
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************VendorAjaxHandlerServiceImpl :: getVendorReqDashBoardGrid Method End*********************");
        return vendorString;
    }
}
