/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

import com.mss.msp.location.Country;
import com.mss.msp.location.State;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.HibernateServiceLocator;
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
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NagireddySeerapu
 */
public class AccountServiceImpl implements AccountService {

    private static Logger log = Logger.getLogger(AccountServiceImpl.class);

    public List<Account> AccountSearch() throws ServiceLocatorException {
        List<Account> accounts = new ArrayList<Account>();

        //getting sessionFactory for the HibernateUtil class.
        Session session = HibernateServiceLocator.getInstance().getSession();


        //Create query
        Criteria query = session.createCriteria(com.mss.msp.acc.Account.class);


        //List the results
        accounts = query.list();

        try {
            // Closing hibernate session
            session.flush();
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
        return accounts;
    }

    public List<String> lookupAccountNamesByName(String accountName) {
        List<String> accounts = new ArrayList<String>();
        Session session = null;
        Query query = session.createQuery("from Account where name = :name");
        query.setString("name", accountName);
        accounts = query.list();
        try {
            closeSession(session);
        } catch (ServiceLocatorException ex) {
            java.util.logging.Logger.getLogger(AccountServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return accounts;
    }

    //TODO Refactor to take in Account Object
    public List<Account> doAccountSearch(String name, String url, String zip, String status,
            State state, Country country, Integer type, Integer industry, String lastAcccessDate, Integer orgId, Integer csr_Id)
            throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
        List<Account> accounts = new ArrayList<Account>();
        System.err.println("in accountsearch impl2" + csr_Id);
        //getting sessionFactory for the HibernateUtil class.
        Session session = HibernateServiceLocator.getInstance().getSession();
        System.out.println("Adding criteria");

        //Create query
        Criteria criteria = null;// session.createCriteria(com.mss.msp.acc.Account.class); 
        //ToDo Add search paramters;
        // by nag
        if (csr_Id > 0) {
            criteria = session.createCriteria(com.mss.msp.acc.CsrAccount.class);
            criteria.add(Restrictions.eq("csrId", csr_Id));
            criteria.add(Restrictions.ilike("csrorgStatus", "Active", MatchMode.EXACT));
        } else {
            criteria = session.createCriteria(com.mss.msp.acc.Account.class);
        }

        if (name != null && !name.equals("")) {
            criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        }
        if (state != null && !state.equals(new State())
                && state.getId() != null && state.getId().intValue() >= 0) { // drop starts with value of -1
            criteria.add(Restrictions.eq("state", state));
        }
        // drop starts with value of -1
        if (status != null && !status.equals("") && !status.equals("-1")) {
            criteria.add(Restrictions.like("status", status, MatchMode.EXACT));
        }
        if (zip != null && !zip.equals("")) {
            criteria.add(Restrictions.ilike("zip", zip, MatchMode.ANYWHERE));
        }
        if (url != null && !url.equals("")) {
            criteria.add(Restrictions.ilike("url", url, MatchMode.ANYWHERE));
        }
        if (country != null && !country.equals(new Country()) && country.getId() != null && country.getId().intValue() >= 0) {
            criteria.add(Restrictions.eq("country", country));
        }
        if (type != null && type.intValue() >= 0) {
            criteria.add(Restrictions.eq("typeId", type));
        }
        if (industry != null && industry.intValue() >= 0) {
            criteria.add(Restrictions.eq("industryId", industry));
        }
        if (lastAcccessDate != null && !lastAcccessDate.equals("")) {
            criteria.add(Restrictions.ilike("lastAccessDate", dateUtility.getInstance().convertStringToMySQLDateInDash(lastAcccessDate), MatchMode.ANYWHERE));
        }
        if (orgId != null && orgId.intValue() >= 0) {
            criteria.add(Restrictions.eq("org_rel_id", orgId));
        }
         criteria.add(Restrictions.eq("isPrimary", 1));
        //Only get one of each Account
        criteria.addOrder(Order.asc("name"));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setMaxResults(100);
        System.out.println("Listing results");
        //List the results
        accounts = criteria.list();
        System.out.println("have results");
        closeSession(session);
        return accounts;
    }

    //TODO rename
    /**
     *
     * @return a list of strings, none of which are null.
     */
    private List<String> hibernateDistinctSearch(String name) throws ServiceLocatorException {
        List<String> results = new ArrayList<String>();
        //getting sessionFactory for the HibernateUtil class.
        Session session = HibernateServiceLocator.getInstance().getSession();
        results = session.createQuery("select distinct " + name + " from Account").list();
        closeSession(session);
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) == null) {
                results.remove(i);
            }
        }
        return results;
    }

    private void closeSession(Session session) throws ServiceLocatorException {
        try {
            // Closing hibernate session
            session.flush();
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
    }

    public List<String> getAccountStatuses() {
        List<String> status = new ArrayList<String>();
        try {
            status = hibernateDistinctSearch("status");
        } catch (ServiceLocatorException e) {
            log.log(Priority.FATAL, "Unable to get Account statuses: " + e.getStackTrace());
        }
        return status;
    }

    public Account viewAccount(int id) throws ServiceLocatorException {
        Account account = new Account();
        Session session = HibernateServiceLocator.getInstance().getSession();

        Query query = session.createQuery("from Account where id = :accId");
        query.setParameter("accId", id);
        //get look up names fomr lk tables =>CHANGE LATER<=
        if ((id > 0)) {
            account = (Account) query.list().get(0);
        }

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

        return account;
    }

    /**
     * *************************************
     *
     * @updateAccSalesTeam()
     *
     * @Author:rama krishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/05/2015
     *
     * For USe:assigning account to particular account sales team
     * *************************************
     */
    public int updateAccSalesTeam(com.mss.msp.acc.details.AccountDetailsAction accountDetailsAction, String[] salesId, int primaryAccount) throws ServiceLocatorException {
        Statement statement = null;

        ResultSet resultSet;

        Connection connection = null;
        String queryString;
        int insertedRows = 0;
        int updateRows = 0;
        int deletedRows = 0;
        try {
            System.out.println("%%%%%%%%%%%%%%%%%%Entered in to the IMPL%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("VendorID>>>>>>>>>>>>>>>>>>>>" + accountDetailsAction.getAccountSearchID());
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "DELETE FROM accteam WHERE acc_id=" + accountDetailsAction.getAccountSearchID();
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
//            //System.out.println("assignedRoleIds.length-->" + assignedRoleIds.length);
            for (int counter = 0; counter < salesId.length; counter++) {
                System.out.println("IN IMPL>>>>>>>>>>>>>>>>" + salesId[counter]);
                if (Integer.parseInt(salesId[counter]) == primaryAccount) {
                    queryString = "Insert into accteam(primaryflag,acc_id,teamMember_Id,created_by) values(1," + accountDetailsAction.getAccountSearchID() + "," + salesId[counter] + "," + accountDetailsAction.getUserSessionId() + ")";
                    //System.out.println(queryString);
                } else {
                    queryString = "Insert into accteam(primaryflag,acc_id,teamMember_Id,created_by) values(0," + accountDetailsAction.getAccountSearchID() + "," + salesId[counter] + "," + accountDetailsAction.getUserSessionId() + ")";
                    //System.out.println(queryString);
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

    public String addAccountContactDetails(AccountAction accountAction) throws ServiceLocatorException {
        Connection connection = null;
        Connection connection1 = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean isExceute = false;
        String resultString = "";
        int updatedRows = 0;
//        String str = accountAction.getContactSkillValues();
//        StringTokenizer stk = new StringTokenizer(str, ",");
//        String skillsResultString = "";
//        while (stk.hasMoreTokens()) {
//            skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//            System.out.println("Result String in while===========================>"+skillsResultString);
//        }
//        System.out.println("ResultString---------->" + skillsResultString);
//        accountAction.setSkillSet(StringUtils.chop(skillsResultString));
//        System.out.println("reqSkillsResultString---------->" + skillsResultString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL addAccContact(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, accountAction.getContactFname());
            callableStatement.setString(2, accountAction.getContactMname());
            if ("".equals(accountAction.getFilePath())) { //(or) what ever condition you want to set for null
                callableStatement.setNull(3, Types.NULL);
            } else {
                callableStatement.setString(3, accountAction.getFilePath());
            }
            //System.out.println("here we print after date changing...........");
            callableStatement.setString(4, accountAction.getContactLname());

            callableStatement.setString(5, accountAction.getContactEmail());
            callableStatement.setString(6, accountAction.getOfficephone());
            // callableStatement.setString(8, accountAjaxHandler.getAddress());
            //  callableStatement.setString(8, accountAjaxHandler.getOfficeAddress());
            // callableStatement.setString(8, accountAction.getStatus());
            callableStatement.setInt(7, accountAction.getAccountSearchOrgId());
            System.out.println("OrgId--->" + accountAction.getAccountSearchOrgId());
            callableStatement.setString(8, accountAction.getConCPhone());
            callableStatement.setInt(9, accountAction.getId());
            callableStatement.setString(10, accountAction.getConAddress());
            callableStatement.setString(11, accountAction.getConAddress2());
            callableStatement.setString(12, accountAction.getConCity());
            callableStatement.setString(13, accountAction.getConZip());
            callableStatement.setInt(14, accountAction.getConCountry());
            callableStatement.setInt(15, accountAction.getConState());
            callableStatement.setString(16, accountAction.getConPhone());

//            callableStatement.setInt(5, accountAction.getConCState());
//            callableStatement.setString(18, accountAction.getConCAddress());
//            callableStatement.setString(19, accountAction.getConCAddress2());
//            callableStatement.setString(20, accountAction.getConCCity());
//            callableStatement.setString(21, accountAction.getConCZip());
//            callableStatement.setInt(22, accountAction.getConCCountry());
//            callableStatement.setString(23, accountAction.getAddAddressFlag());
//            
            callableStatement.setString(17, accountAction.getTypeOfAccount());
            //  callableStatement.setInt(18, accountAction.getDepartments());
            // callableStatement.setString(18, accountAction.getContactDesignation());
            callableStatement.setString(18, accountAction.getGender());
            // callableStatement.setInt(20, accountAction.getAddTeamLead());
            // callableStatement.setInt(21, accountAction.getAddManager());
            callableStatement.setInt(19, accountAction.getUserSessionId());
            callableStatement.setString(20, accountAction.getContactAEmail());
             callableStatement.setInt(21, accountAction.getWorkingLocation());
             // Add By Aklakh 
             callableStatement.setString(22, accountAction.getContactTitle());
             callableStatement.setInt(23, accountAction.getContactExperience());
             callableStatement.setInt(24,accountAction.getContactIndustry());
             callableStatement.setString(25, com.mss.msp.util.DataUtility.encrypted(accountAction.getContactSsnNo()));
             callableStatement.setString(26, accountAction.getContactEducation());
             callableStatement.setString(27,accountAction.getContactSkillValues());
             callableStatement.setString(28,File.separator);
            callableStatement.registerOutParameter(29, Types.INTEGER);
            callableStatement.registerOutParameter(30, Types.INTEGER);

            System.out.println("hello here print after prepare call parameter ");

            isExceute = callableStatement.execute();
            updatedRows = callableStatement.getInt(29);
            int usrId = callableStatement.getInt(30);
            System.out.println("usrId-------->" + usrId);
            String imgPath = null;

            connection1 = ConnectionProvider.getInstance().getConnection();
            statement = connection1.createStatement();
            String queryString = "select image_path from users where usr_id=" + usrId;
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                imgPath = resultSet.getString("image_path");
            }
            //File destFile = new File(accountAction.getFilePath() + File.separator + accountAction.getTaskAttachmentFileName());
            if (imgPath != null) {
                File destFile = new File(imgPath);
                System.out.println(">>>>>>>>>>>>>>>>>>IN Impl image PATH222222>>>>>>>>>>>>" + destFile);
                FileUtils.copyFile(accountAction.getTaskAttachment(), destFile);
            }
            if (updatedRows > 0) {
                resultString = "Added successfully";
            }
            System.out.println("is updatedRows " + updatedRows);
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

    public boolean addAccount(Account account, Integer userId, Integer orgId) {
        boolean success = false;
        Connection conn = null;
        Statement stmt = null;
        int status = 0;
        try {
            String accName = com.mss.msp.util.DataUtility.getInstance().formatName(account.getName());
            conn = ConnectionProvider.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "CALL `servicebay`.`addAccounts`"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @a_message);");

            ps.setString(1, accName);
            ps.setString(2, account.getUrl());
            ps.setString(3, account.getAddress1());
            ps.setString(4, account.getAddress2());
            Integer stateId = account.getState().getId();
            if (stateId == null || stateId.intValue() < 0) {
                ps.setString(5, null); // If Country is UK there is no state
            } else {
                ps.setInt(5, stateId.intValue());
            }
            Integer countryId = account.getCountry().getId();
            if (countryId != null && countryId.intValue() >= 0) {
                ps.setInt(6, countryId);
            } else {
                ps.setString(6, null);
            }

            ps.setString(7, account.getCity());
            ps.setString(8, account.getZip());
            if (userId != null && userId.intValue() >= 0) {
                ps.setInt(9, userId);
            } else {
                ps.setString(9, null);
            }

            ps.setString(10, account.getPhone());
            ps.setString(11, account.getFax());
            Integer industryId = account.getIndustryId();
            if (industryId != null && industryId.intValue() >= 0) {
                ps.setInt(12, industryId);
            } else {
                ps.setString(12, null);
            }
            Double Budget = account.getBudget();
            if (Budget != null) {
                ps.setInt(13, Budget.intValue());
            } else {
                ps.setString(13, null);
            }
            ps.setString(14, account.getRegion());
            ps.setString(15, account.getTax_id());

            ps.setString(16, account.getTerritory());
            ps.setString(17, account.getStockSymbol());
//            Integer noEmp = account.getNoemp();
//            if (noEmp != null && noEmp.intValue() >= 0) {
//                ps.setInt(18, noEmp);
//            } else {
//                ps.setString(18, null);
//            }
            ps.setString(18, account.getNoemp());
            ps.setString(19, account.getDescription());
            Long rev = account.getRevenue();
            if (rev != null) {
                ps.setInt(20, rev.intValue());
            } else {
                ps.setString(20, null);
            }

            ps.setInt(21, orgId); //Related Account Id can be null if null will be related to itself
            ps.setInt(22, account.getTypeId()); //Account Type check for null
            ps.setString(23, account.getEmail_ext());
//            Integer vendorType = account.getVendorTypeId();
//            if (vendorType == null || vendorType < 0) {
//                ps.setString(23, null);
//            } else {
//                ps.setInt(23, vendorType); //VendorType id can be null.
//            }
            status = ps.executeUpdate();


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        if (status == 1) {
            success = true;
        } else {
            System.out.println("Failed to add Account: " + status);
        }

        return success;
    }

    public void updateImageForProfile(AccountAction accountAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String resultString = "";
        PreparedStatement preparedStatement = null;
        String queryString = "update users set image_path=? where usr_id=?";
        int updatedRows = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, accountAction.getFilePath());
            preparedStatement.setInt(2, accountAction.getContactId());
            preparedStatement.execute();
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
    }

    public List<Account> doAccountSearch(Account account, Integer orgId, Integer csrId) {
        List<Account> accounts = new ArrayList<Account>();
        try {
            System.err.println("in accountsearch impl1" + csrId);
            //calling 1
            accounts = this.doAccountSearch(account.getName(), account.getUrl(), account.getZip(),
                    account.getStatus(), account.getState(), account.getCountry(), account.getTypeId(),
                    account.getIndustryId(), account.getLastAccessDate(), orgId, csrId);
        } catch (ServiceLocatorException ex) {
            java.util.logging.Logger.getLogger(AccountServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public String addTeamMemberToProject(AccountAction accountAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        System.out.println("up to here okay....0");
        String querystring = "";
        String result = "";
        try {
            connection = (Connection) ConnectionProvider.getInstance().getConnection();
            if (isAvailableTeamPerson(accountAction.getTeamMemberId(), accountAction.getProjectID())) {
                querystring = "INSERT INTO Project_team(project_id,usr_id,current_status,created_date,created_by,reportsto1,reportsto2,account_id,designation,resource_type) values(?,?,?,?,?,?,?,?,?,?)";
                System.out.println("query is " + querystring);
                preparedStatement = connection.prepareStatement(querystring);
                System.out.println("up to here okay....1");
                System.out.println("this is list of values==="
                        + accountAction.getDesignation() + " ");

                preparedStatement.setInt(1, accountAction.getProjectID());
                preparedStatement.setInt(2, accountAction.getTeamMemberId());
                preparedStatement.setString(3, accountAction.getTeamMemberStatus());
                preparedStatement.setTimestamp(4, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
                preparedStatement.setInt(5, accountAction.getUserSessionId());
                preparedStatement.setInt(6, accountAction.getReportsto1());
                preparedStatement.setInt(7, accountAction.getReportsto2());
                preparedStatement.setInt(8, accountAction.getAccountID());
                preparedStatement.setInt(9, DataSourceDataProvider.getInstance().getUsrRoleById(accountAction.getTeamMemberId()));
                preparedStatement.setString(10, accountAction.getResourceType());
                result = "Insert";
            } else {
                System.out.println("query is in upodate");
                querystring = "UPDATE Project_team SET current_status='" + accountAction.getTeamMemberStatus() + "',modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "',modified_by=" + accountAction.getUserSessionId() + ",reportsto1=" + accountAction.getReportsto1() + " WHERE usr_id=" + accountAction.getTeamMemberId() + " AND project_id=" + accountAction.getProjectID();
                System.out.println("query is in upodate-->" + querystring);
                preparedStatement = connection.prepareStatement(querystring);
                result = "Update";
            }
            boolean flag = preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
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
        return result;
    }

    private boolean isAvailableTeamPerson(int teamMemberId, int projectID) throws ServiceLocatorException {
        boolean result = true;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //String queryString = "SELECT usr_id,CONCAT(first_name,'.',last_name) AS NAMES FROM users  WHERE org_id=" + org_id;
        String queryString = "SELECT * FROM Project_team WHERE usr_id=" + teamMemberId + " AND project_id=" + projectID;
        System.out.println("query===========>" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                result = false;
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
        return result;
    }

    /**
     * ****************************************************************************
     * Date : June 12 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * getProjectName method can be used to retrieve Project Name by giving
     * projectId
     * *****************************************************************************
     */
    public String getProjectName(int projectId, HttpServletRequest httpServletRequest) throws ServiceLocatorException {

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
        String queryString = "SELECT proj_name FROM acc_projects WHERE project_id=" + projectId;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                name = resultSet.getString("proj_name");

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

    /**
     * ****************************************************************************
     * Date : June 12 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * getSubProjectDetails method can be used to retrieve the sub project
     * details
     *
     * This method is used for all my,team and services search
     * *****************************************************************************
     */
    public Account getSubProjectDetails(HttpServletRequest httpServletRequest, AccountAction accountAction) throws ServiceLocatorException {
        ArrayList<Account> subProjectList = new ArrayList<Account>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultString = null;
        String queryString = "";
        int i = 0;
        Account account = new Account();
        try {

            queryString = "SELECT p.project_id, p.proj_name,u.usr_id, CONCAT(u.first_name,'.',u.last_name) AS NAME , pt.designation, pt.current_status , pt.reportsto1, pt.reportsto2 "
                    + "FROM acc_projects p LEFT OUTER JOIN Project_team pt ON(p.project_id=pt.project_id) "
                    + "LEFT OUTER JOIN users u ON(pt.usr_id=u.usr_id) WHERE p.project_id =" + accountAction.getProjectID() + "  AND u.usr_id=" + accountAction.getUserID();

            queryString = queryString + " LIMIT 100";
            System.out.println(" sub project queryString  -->" + queryString);
            connection = (Connection) ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultString = statement.executeQuery(queryString);

            while (resultString.next()) {
                account.setProjectID(resultString.getInt("project_id"));
                account.setProjectName(resultString.getString("proj_name"));
                account.setProjectEmpName(resultString.getString("NAME"));
                account.setUserID(resultString.getInt("usr_id"));
                account.setDesignation(resultString.getInt("designation"));
                account.setTeamMemberStatus(resultString.getString("current_status"));
                account.setReportsto1(resultString.getString("reportsto1"));
                account.setReportsto2(resultString.getString("reportsto2"));

                System.out.println(subProjectList.size());

            }
            // System.out.println(" sub project details----------->" + subProjectList);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        } finally {
            try {
                if (resultString != null) {
                    resultString.close();
                    resultString = null;
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
        //  System.out.println("sub project list------>" + subProjectList);

        return account;
    }

    /**
     * *************************************
     *
     * @updateAssignTeam()
     *
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date: June 16 2015
     *
     * For USe:assigning project to particular team member
     * *************************************
     */
    public int updateAssignTeam(AccountAction accountAction, String[] prjId) throws ServiceLocatorException {
        Statement statement = null;

        ResultSet resultSet;

        Connection connection = null;
        String queryString;
        int insertedRows = 0;
        int updateRows = 0;
        int deletedRows = 0;
        try {
            System.out.println("%%%%%%%%%%%%%%%%%%Entered in to the IMPL%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("Project>>>>>>>>>>>>>>>>>>>>" + accountAction.getProjectID());
            System.out.println("User Id>>>>>>>>>>>>>>>>>>>>" + accountAction.getUserID());
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString = "DELETE FROM prj_sub_prjteam WHERE project_id=" + accountAction.getProjectID() + "  AND usr_id=" + accountAction.getUserID();
            System.out.println("Delete Query String------->" + queryString);
            deletedRows = statement.executeUpdate(queryString);
            statement.close();
            statement = null;
            statement = connection.createStatement();
            System.out.println("After Delete");

            /**
             * it loops the roles.length and inserts the data into database for
             * each addedVals
             *
             * @throws NullPointerException If a NullPointerException exists and
             * its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
//            //System.out.println("assignedRoleIds.length-->" + assignedRoleIds.length);
            System.out.println("Sesssion Id---->" + accountAction.getUserSessionId());
            for (int counter = 0; counter < prjId.length; counter++) {
                System.out.println("IN IMPL>>>>>>>>>>>>>>>>" + prjId[counter]);

                queryString = "Insert into prj_sub_prjteam(project_id,sub_project_id ,usr_id,current_status,created_by) values(" + accountAction.getProjectID() + "," + prjId[counter] + "," + accountAction.getUserID() + "," + "'Active'" + "," + accountAction.getUserSessionId() + ")";
                System.out.println(queryString);



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

    public String checkAccount(Account account, Integer userId, Integer orgId) {
        boolean accExist;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultString = null;
        String queryString = "";
        String status = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("Account Name is----->" + account.getName());
            System.out.println("Account Url is----->" + account.getUrl());
//            if(account.getName()!=null)
//            {
            queryString = "SELECT account_name FROM accounts WHERE account_name=? OR account_url=?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            System.out.println("accout Check Query---->" + queryString);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getUrl());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String accName = rs.getString("account_name");
                status = "Account already Exist";

            }


            System.out.println(status);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// do nothing
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return status;

    }

    public Account getProjectTeamDetails(HttpServletRequest httpServletRequest, AccountAction accountAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultString = null;
        String queryString = "";
        int i = 0;
        Account account = new Account();
        try {

            if(accountAction.getProjectFlag() != null&&!"".equals(accountAction.getProjectFlag().trim())){
                 System.out.println("Inside if");
                 queryString = "SELECT  CONCAT(first_name,'.',last_name) AS FullName,`project_id`, project_team.`usr_id`, project_team.`designation`, `resource_type`, project_team.`current_status`,`reportsto1`,"
                    + "`reportsto2`, `account_id`,consultant_skills,rate_salary FROM "
                    + " `servicebay`.`project_team` LEFT OUTER JOIN usr_details ON "
                    + " (project_team.usr_id=usr_details.usr_id) LEFT OUTER JOIN users ON (users.usr_id=project_team.usr_id) WHERE project_team.project_id =" + accountAction.getProjectID() ;
            System.out.println(" sub project queryString  -->" + queryString);
            }else{
                 System.out.println("Inside else");
                   queryString = "SELECT  CONCAT(first_name,'.',last_name) AS FullName,`project_id`, project_team.`usr_id`, project_team.`designation`, `resource_type`, project_team.`current_status`,`reportsto1`,"
                    + "`reportsto2`, `account_id`,consultant_skills,rate_salary FROM "
                    + " `servicebay`.`project_team` LEFT OUTER JOIN usr_details ON "
                    + " (project_team.usr_id=usr_details.usr_id) LEFT OUTER JOIN users ON (users.usr_id=project_team.usr_id) WHERE project_team.project_id =" + accountAction.getProjectID() + "  AND project_team.usr_id=" + accountAction.getUserID();
            System.out.println(" sub project queryString  -->" + queryString);
             }
            connection = (Connection) ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultString = statement.executeQuery(queryString);
            account.setProjectName(getProjectName(accountAction.getProjectID(), httpServletRequest));
            while (resultString.next()) {
                int prjId = resultString.getInt("project_id");
                account.setProjectName(getProjectName(prjId, httpServletRequest));
                account.setProjectID(prjId);
                account.setUserID(resultString.getInt("usr_id"));
                account.setDesignation(resultString.getInt("designation"));
                account.setTeamMemberStatus(resultString.getString("current_status"));
                account.setReportsto1(resultString.getString("reportsto1"));
                account.setReportsto2(resultString.getString("reportsto2"));
                account.setResourceType(resultString.getString("resource_type"));
                account.setConsSkills(resultString.getString("consultant_skills"));
                account.setRateSalary(resultString.getString("rate_salary"));
                account.setTeamMemberIdname(resultString.getString("FullName"));
            }
            // System.out.println(" sub project details----------->" + subProjectList);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        } finally {
            try {
                if (resultString != null) {
                    resultString.close();
                    resultString = null;
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
        //  System.out.println("sub project list------>" + subProjectList);
        return account;

    }
}