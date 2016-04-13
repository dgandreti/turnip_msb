/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.details;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * *****************************************************************************
 * Date :
 *
 * Author :
 *
 * ForUse : AccountDetailsHandlerServiceImpl() class is used to
 *
 * *****************************************************************************
 */
public class AccountDetailsHandlerServiceImpl implements AccountDetailsHandlerService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    public AccountDetails viewAccountDetails(int id) throws ServiceLocatorException {
        AccountDetails details = new AccountDetails();
        Session session = HibernateServiceLocator.getInstance().getSession();
        Query query = session.createQuery("from AccountDetails where id = :accId and isPrimary = :primary");
        query.setParameter("accId", id);
        query.setParameter("primary", 1);
        List temp = query.list();
        if (temp.size() > 0) {
            details = (AccountDetails) temp.get(0);
        }
        if (details.getBankAccountNumber() != null) {
            details.setBankAccountNumber(com.mss.msp.util.DataUtility.decrypted(details.getBankAccountNumber()));
        }
        if (details.getBankAccountNumber() != null) {
            details.setBankRoutingNumber(com.mss.msp.util.DataUtility.decrypted(details.getBankRoutingNumber()));
        }
        if (details.getAccLogo() == null) {
            details.setAccLogo(Properties.getProperty("DEFAULTLOGO"));
        }
        System.out.println("********************AccountDetailsHandlerServiceImpl :: viewAccountDetails Method Start*********************");
        try {
            // Closing hibernate session
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        System.out.println("********************AccountDetailsHandlerServiceImpl :: viewAccountDetails Method End*********************");
        return details;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : editAccountContacts() method is used to
     *
     * *****************************************************************************
     */
    public AccountContactVTO editAccountContacts(int contactId) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        AccountContactVTO accountContactVTO = new AccountContactVTO();
        Map state1 = new HashMap();
        Map state2 = new LinkedHashMap();
        System.out.println("********************AccountDetailsHandlerServiceImpl :: editAccountContacts Method Start*********************");
        try {
            queryString = "SELECT u.location_id,u.gender,u.first_name,u.last_name,u.middle_name,u.image_path,u.org_id,u.phone1,u.phone2,u.email1,u.email2,u.office_phone,u.cur_status,u.designation,ua.address,ua.address_flag, ua.address2, ua.city, ua.state, ua.zip, ua.country,ua.phone, ud.job_title, ud.usr_industry, ud.experience, ud.education, ud.consultant_skills,ud.ssn_number FROM usr_address ua LEFT OUTER JOIN users u ON (ua.usr_id=u.usr_id) LEFT OUTER JOIN usr_details ud ON(ud.usr_id=u.usr_id)  WHERE u.usr_id =" + contactId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            while (resultSet.next()) {
                accountContactVTO.setFirstName(resultSet.getString("u.first_name"));
                accountContactVTO.setLastName(resultSet.getString("u.last_name"));
                accountContactVTO.setMiddleName(resultSet.getString("u.middle_name"));
                accountContactVTO.setCheckAddress(false);
                accountContactVTO.setOfficePhone(resultSet.getString("u.office_phone"));
                accountContactVTO.setStatus(resultSet.getString("u.cur_status"));
                accountContactVTO.setMoblieNumber(resultSet.getString("u.phone1"));
                accountContactVTO.setHomePhone(resultSet.getString("u.phone2"));
                accountContactVTO.setEmail(resultSet.getString("u.email1"));
                accountContactVTO.setEmail2(resultSet.getString("u.email2"));
                if (resultSet.getString("u.image_path") != null) {
                    File imageFile = new File(resultSet.getString("u.image_path"));
                    if (imageFile.exists() == false) {
                        if ("F".equalsIgnoreCase(resultSet.getString("u.gender"))) {
                            accountContactVTO.setProfileImage(Properties.getProperty("Profile.FEMALEIMAGE"));
                        } else {
                            accountContactVTO.setProfileImage(Properties.getProperty("Profile.GENERALIMAGE"));
                        }
                    } else {
                        accountContactVTO.setProfileImage(resultSet.getString("u.image_path"));
                    }
                } else {
                    if ("F".equalsIgnoreCase(resultSet.getString("u.gender"))) {
                        accountContactVTO.setProfileImage(Properties.getProperty("Profile.FEMALEIMAGE"));
                    } else {
                        accountContactVTO.setProfileImage(Properties.getProperty("Profile.GENERALIMAGE"));
                    }
                }
                accountContactVTO.setOrgId(resultSet.getInt("u.org_id"));
                accountContactVTO.setGender(resultSet.getString("u.gender"));
                accountContactVTO.setConPAddress(resultSet.getString("ua.address"));
                accountContactVTO.setConPAddress2(resultSet.getString("ua.address2"));
                accountContactVTO.setConPZip(resultSet.getString("ua.zip"));
                accountContactVTO.setConPCity(resultSet.getString("ua.city"));
                accountContactVTO.setConPPhone(resultSet.getString("ua.phone"));
                accountContactVTO.setConPState(resultSet.getInt("ua.state"));
                accountContactVTO.setConPCountry(resultSet.getInt("ua.country"));
                accountContactVTO.setWorkingLocation(resultSet.getInt("u.location_id"));
                // Add By Aklakh
                accountContactVTO.setContactTitle(resultSet.getString("job_title"));
                accountContactVTO.setContactExperience(resultSet.getInt("experience"));
                accountContactVTO.setContactIndustry(resultSet.getInt("usr_industry"));
                if (resultSet.getString("ssn_number") != null) {
                    accountContactVTO.setContactSsnNo(com.mss.msp.util.DataUtility.decrypted(resultSet.getString("ssn_number")));
                }
                accountContactVTO.setContactEducation(resultSet.getString("education"));
                accountContactVTO.setContactSkillValues(resultSet.getString("consultant_skills"));
                String str1 = resultSet.getString("consultant_skills");
                if (!"null".equals(str1) && str1 != null) {
                    StringTokenizer stk1 = new StringTokenizer(str1, ",");
                    String skillsResultString = "";
                    while (stk1.hasMoreTokens()) {
                        skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId((stk1.nextToken())) + ",";
                    }
                    String str2 = skillsResultString;
                    StringTokenizer stk2 = new StringTokenizer(str2, ",");
                    List list1 = new ArrayList();
                    while (stk2.hasMoreTokens()) {
                        list1.add(stk2.nextToken());
                    }
                    accountContactVTO.setSkillListSet(list1);
                }
                String stateQuery = "";
                try {
                    if (accountContactVTO.getConPCountry() == -1) {
                        stateQuery = "SELECT id,name FROM lk_states where countryId=3 ORDER BY name ASC";
                        preparedStatement = connection.prepareStatement(stateQuery);
                    } else {
                        stateQuery = "SELECT id,name FROM lk_states where countryId=? ORDER BY name ASC";
                        preparedStatement = connection.prepareStatement(stateQuery);
                        preparedStatement.setInt(1, accountContactVTO.getConPCountry());
                    }
                    ResultSet resultset1 = null;
                    resultset1 = preparedStatement.executeQuery();
                    while (resultset1.next()) {
                        state2.put(resultset1.getInt("id"), resultset1.getString("name"));
                    }
                    accountContactVTO.setState1(state2);
                } catch (Exception e) {
                    e.printStackTrace();
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
        System.out.println("********************AccountDetailsHandlerServiceImpl :: editAccountContacts Method End*********************");
        return accountContactVTO;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : updateAccountContactDetails() method is used to
     *
     * *****************************************************************************
     */
    public String updateAccountContactDetails(AccountDetailsAction accountDetailsAction) throws ServiceLocatorException {

        boolean isExceute = false;
        String resultString = "";
        int updatedRows = 0;
         Connection connection = null;
          CallableStatement callableStatement = null;
        System.out.println("********************AccountDetailsHandlerServiceImpl :: updateAccountContactDetails Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL updateAccContact(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("updateAccountContactDetails :: procedure name : updateAccContact ");
            callableStatement.setString(1, accountDetailsAction.getContactEmail());
            callableStatement.setString(2, accountDetailsAction.getContactFname());
            callableStatement.setString(3, accountDetailsAction.getContactLname());
            callableStatement.setString(4, accountDetailsAction.getContactMname());
            callableStatement.setString(5, accountDetailsAction.getOfficephone());
            callableStatement.setString(6, accountDetailsAction.getStatus());
            callableStatement.setString(7, accountDetailsAction.getConAddress());
            callableStatement.setString(8, accountDetailsAction.getConAddress2());
            callableStatement.setString(9, accountDetailsAction.getConCity());
            callableStatement.setInt(10, accountDetailsAction.getConCountry());
            callableStatement.setInt(11, accountDetailsAction.getConState());
            callableStatement.setString(12, accountDetailsAction.getConZip());
            callableStatement.setString(13, accountDetailsAction.getConPhone());
            callableStatement.setInt(14, accountDetailsAction.getContactId());
            callableStatement.setString(15, accountDetailsAction.getGender());
            callableStatement.setString(16, accountDetailsAction.getFlagname());
            callableStatement.setString(17, accountDetailsAction.getMoblieNumber());
            callableStatement.setString(18, accountDetailsAction.getContactEmail2());
            callableStatement.setString(19, accountDetailsAction.getHomePhone());
            callableStatement.setInt(20, accountDetailsAction.getPrimaryRole());
            callableStatement.setInt(21, accountDetailsAction.getUserSessionId());
            callableStatement.setInt(22, accountDetailsAction.getWorkingLocation());
            //Add By Aklakh
            callableStatement.setString(23, accountDetailsAction.getContactTitle());
            callableStatement.setInt(24, accountDetailsAction.getContactExperience());
            callableStatement.setInt(25, accountDetailsAction.getContactIndustry());
            callableStatement.setString(26, com.mss.msp.util.DataUtility.encrypted(accountDetailsAction.getContactSsnNo()));
            callableStatement.setString(27, accountDetailsAction.getContactEducation());
            callableStatement.setString(28, accountDetailsAction.getContactSkillValues());
            callableStatement.registerOutParameter(29, Types.INTEGER);
            isExceute = callableStatement.execute();
            updatedRows = callableStatement.getInt(29);
            if (updatedRows > 0) {
                resultString = "Updated";
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
        System.out.println("********************AccountDetailsHandlerServiceImpl :: updateAccountContactDetails Method End*********************");
        return resultString;

    }
}
