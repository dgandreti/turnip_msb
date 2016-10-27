/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.requirement;

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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * *****************************************************************************
 * Date :
 *
 * Author :miracle
 *
 * ForUse : RequirementServiceImpl() is used to
 *
 * *****************************************************************************
 */
public class RequirementServiceImpl implements RequirementService {

    private static Logger log = Logger.getLogger(RequirementServiceImpl.class);
    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    public int addRequirementDetails(RequirementAction requirementAction, int orgId) throws ServiceLocatorException {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        String queryString = "";
        int result = 0;

        log.info("********************RequirementServiceImpl :: addRequirementDetails Method Start*********************");
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";

        String str = requirementAction.getSkillCategoryArry();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String reqSkillsResultString = "";
        while (stk.hasMoreTokens()) {
            reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
        }
        requirementAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));

        try {
            startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getRequirementFrom());

            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL addRequirements(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            log.info("addRequirementDetails :: procedure name : addRequirements ");

            callableStatement.setInt(1, requirementAction.getOrgId());
            callableStatement.setString(2, requirementAction.getRequirementType());
            callableStatement.setString(3, requirementAction.getRequirementName());
            callableStatement.setInt(4, requirementAction.getRequirementYears());
            callableStatement.setString(5, requirementAction.getRequirementComments());

            callableStatement.setString(6, requirementAction.getRequirementStatus());

            callableStatement.setInt(7, requirementAction.getUserSessionId());
            callableStatement.setString(8, requirementAction.getReqSkillSet());
            callableStatement.setString(9, requirementAction.getPreSkillCategoryArry());
            callableStatement.setString(10, startDate);

            callableStatement.setInt(11, requirementAction.getRequirementNoofResources());
            callableStatement.setInt(12, requirementAction.getRequirementContact1());
            callableStatement.setInt(13, requirementAction.getRequirementContact2());

            callableStatement.setString(14, requirementAction.getRequirementTargetRate());
            callableStatement.setString(15, requirementAction.getRequirementDuration());
            callableStatement.setString(16, requirementAction.getRequirementTaxTerm());
            callableStatement.setString(17, requirementAction.getRequirementLocation());

            callableStatement.setString(18, requirementAction.getRequirementJobdesc());

            callableStatement.setString(19, requirementAction.getRequirementResponse());
            callableStatement.setInt(20, orgId);
            callableStatement.setString(21, requirementAction.getBillingContact());
            callableStatement.setString(22, requirementAction.getRequirementDurationDescriptor());
            callableStatement.setInt(23, requirementAction.getReqCategoryValue());
            callableStatement.setString(24, requirementAction.getRequirementMaxRate());
            if (requirementAction.getRequirementTaxTerm().equals("CO")) {
                callableStatement.setString(25, requirementAction.getBillingtype());
            } else {
                callableStatement.setString(25, "");

            }

            callableStatement.setString(26, requirementAction.getRequirementQualification());
            callableStatement.registerOutParameter(27, Types.INTEGER);

            result = callableStatement.executeUpdate();

            if (result > 0) {

                return result;
            } else {

                return result;
            }

        } catch (Exception sqe) {
            log.log(Level.ERROR, "Exception in try catch: " + sqe);
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
                log.log(Level.ERROR, "Exception in finally catch: " + sqle);
                sqle.printStackTrace();
            }
        }
        log.info("********************RequirementServiceImpl :: addRequirementDetails Method End*********************");
        return result;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : editrequirement() method is used to
     *
     * *****************************************************************************
     */
    public RequirementVTO editrequirement(String requirementid, Map skillsMap) throws ServiceLocatorException {
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        RequirementVTO requirementVTO = new RequirementVTO();
        log.info("********************RequirementServiceImpl :: editrequirement Method Start*********************");
        try {
            queryString = "SELECT requirement_id,req_name,req_comments,req_desc,req_status,req_skills,req_st_date,req_tr_date,no_of_resources,req_contact1,req_contact2,"
                    + "hr_week_month,target_rate,req_duration,tax_term,req_location,presales1,presales2,req_function_desc,req_responsibilities,"
                    + "preferred_skills,req_years_exp,billing_contact,req_category,max_rate,billingtype,qualification  FROM acc_requirements WHERE requirement_id=" + requirementid;
            log.info("editrequirement::queryString-->" + queryString);

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                requirementVTO.setReqCatgory(resultSet.getInt("req_category"));

                requirementVTO.setRequirementId(resultSet.getInt("requirement_id"));
                requirementVTO.setRequirementStatus(resultSet.getString("req_status"));
                requirementVTO.setRequirementExp(resultSet.getString("req_years_exp"));
                requirementVTO.setRequirementJobdesc(resultSet.getString("req_function_desc"));

                requirementVTO.setRequirementResponse(resultSet.getString("req_responsibilities"));
                requirementVTO.setRequirementName(resultSet.getString("req_name"));
                requirementVTO.setRequirementComments(resultSet.getString("req_comments"));
                requirementVTO.setRequirementDescription(resultSet.getString("req_desc"));

                String str2 = resultSet.getString("req_skills");
                StringTokenizer stk2 = new StringTokenizer(str2, ",");

                List list = new ArrayList();
                while (stk2.hasMoreTokens()) {

                    list.add(getKeyFromValue(skillsMap, stk2.nextToken()));

                }

                requirementVTO.setSkillSetList(list);

                String str1 = resultSet.getString("preferred_skills");
                StringTokenizer stk1 = new StringTokenizer(str1, ",");
                List list1 = new ArrayList();

                while (stk1.hasMoreTokens()) {
                    list1.add(stk1.nextToken());
                }

                requirementVTO.setPreSkillSetList(list1);

                requirementVTO.setRequirementTargetRate(resultSet.getString("target_rate"));
                requirementVTO.setRequirementDuration(resultSet.getString("req_duration"));
                requirementVTO.setRequirementTaxTerm(resultSet.getString("tax_term"));

                requirementVTO.setRequirementLocation(resultSet.getString("req_location"));

                requirementVTO.setRequirementPresales1(resultSet.getString("presales1"));
                requirementVTO.setRequirementPresales2(resultSet.getString("presales2"));
                requirementVTO.setRequirementNoofResources(resultSet.getInt("no_of_resources"));
                requirementVTO.setRequirementFrom(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("req_st_date")));

                requirementVTO.setRequirementContact1(resultSet.getString("req_contact1"));
                requirementVTO.setRequirementContact2(resultSet.getString("req_contact2"));

                requirementVTO.setBillingContact(resultSet.getString("billing_contact"));
                requirementVTO.setRequirementDurationDescriptor(resultSet.getString("hr_week_month"));
                requirementVTO.setRequirementMaxRate(resultSet.getString("max_rate"));
                requirementVTO.setBillingtype(resultSet.getString("billingtype"));
                requirementVTO.setRequirementQualification(resultSet.getString("qualification"));
            }

        } catch (Exception sqe) {
            log.log(Level.ERROR, "Exception in try catch : " + sqe);
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
                log.log(Level.ERROR, "Exception in finally catch : " + sqle);
                sqle.printStackTrace();
            }
        }
        log.info("********************RequirementServiceImpl :: editrequirement Method End*********************");
        return requirementVTO;
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : updateRequirement() method is used to
     *
     * *****************************************************************************
     */
    public int updateRequirement(int userid, RequirementAction requirementAction) throws ServiceLocatorException {

        log.info("********************RequirementServiceImpl :: updateRequirement Method Start*********************");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String FromDate = "";
        String ToDate = "";
        DateUtility dateUtility = new DateUtility();
        int updated = 0;
        try {
            FromDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getRequirementFrom());
            queryString = "UPDATE acc_requirements SET req_name=?,req_comments=?,req_status=?,req_skills=?,req_st_date=?,no_of_resources=?,req_contact1=?,req_contact2=?,"
                    + "target_rate=?,req_duration=?,tax_term=?,req_location=?,req_function_desc=?,"
                    + "req_responsibilities=?,req_modified_date=?,req_modified_by=?,preferred_skills=?,req_years_exp=?,billing_contact=?,"
                    + "hr_week_month=?,max_rate=?,billingtype=?,qualification=?  WHERE requirement_id=" + requirementAction.getRequirementId();
            log.info("updateRequirement::queryString-->" + queryString);
            String str = requirementAction.getSkillCategoryArry();
            StringTokenizer stk = new StringTokenizer(str, ",");
            String reqSkillsResultString = "";
            while (stk.hasMoreTokens()) {
                reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
            }
            requirementAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));

            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setString(1, requirementAction.getRequirementName());
            preparedStatement.setString(2, requirementAction.getRequirementComments());

            preparedStatement.setString(3, requirementAction.getRequirementStatus());
            preparedStatement.setString(4, requirementAction.getReqSkillSet());
            preparedStatement.setString(5, FromDate);

            preparedStatement.setInt(6, requirementAction.getRequirementNoofResources());
            preparedStatement.setInt(7, requirementAction.getRequirementContact1());
            preparedStatement.setInt(8, requirementAction.getRequirementContact2());

            preparedStatement.setString(9, requirementAction.getRequirementTargetRate());
            preparedStatement.setString(10, requirementAction.getRequirementDuration());
            preparedStatement.setString(11, requirementAction.getRequirementTaxTerm());
            preparedStatement.setString(12, requirementAction.getRequirementLocation());

            preparedStatement.setString(13, requirementAction.getRequirementJobdesc());
            preparedStatement.setString(14, requirementAction.getRequirementResponse());
            preparedStatement.setTimestamp(15, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(16, userid);
            preparedStatement.setString(17, requirementAction.getPreSkillCategoryArry());
            preparedStatement.setInt(18, requirementAction.getRequirementYears());
            preparedStatement.setString(19, requirementAction.getBillingContact());
            preparedStatement.setString(20, requirementAction.getRequirementDurationDescriptor());
            preparedStatement.setString(21, requirementAction.getRequirementMaxRate());
            if (requirementAction.getRequirementTaxTerm().equals("CO")) {
                preparedStatement.setString(22, requirementAction.getBillingtype());
            } else {
                preparedStatement.setString(22, "");

            }
            preparedStatement.setString(23, requirementAction.getRequirementQualification());
            updated = preparedStatement.executeUpdate();

        } catch (Exception sqe) {
            log.log(Level.ERROR, "Exception in try catch: " + sqe);
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
                log.log(Level.ERROR, "Exception in finally catch:: " + sqle);
                sqle.printStackTrace();
            }
        }
        log.info("********************RequirementServiceImpl :: updateRequirement Method End*********************");
        return updated;
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
    public String getRequirementDetails(RequirementAction requirementAction) throws ServiceLocatorException {

        log.info("********************RequirementServiceImpl :: getRequirementDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        ArrayList<RequirementVTO> requirementList = new ArrayList<RequirementVTO>();
        StringBuffer stringBuffer = new StringBuffer();

        String resultString = "";
        int i = 0;
        try {

            queryString = "SELECT jdid,requirement_id,req_name,no_of_resources,req_skills,req_st_date,req_status,preferred_skills "
                    + "FROM acc_requirements WHERE acc_id=" + requirementAction.getAccountSearchID() + " order by jdid desc LIMIT 100 ";

            log.info("getRequirementDetails::queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String status = "";
                if (resultSet.getString("req_status").equalsIgnoreCase("O")) {
                    status = "Open";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("F")) {
                    status = "Forecast";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("I")) {
                    status = "In-Progress";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("H")) {
                    status = "Hold";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("S")) {
                    status = "Won";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("L")) {
                    status = "Lost";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("W")) {
                    status = "Withdraw";
                }

                resultString += resultSet.getInt("requirement_id") + "|" + resultSet.getString("req_name") + "|" + resultSet.getInt("no_of_resources") + "|" + resultSet.getString("req_st_date") + "|" + status + "|" + resultSet.getString("req_skills") + "|" + resultSet.getString("preferred_skills") + "|" + resultSet.getInt("jdid") + "^";
            }

        } catch (Exception sqe) {
            log.log(Level.ERROR, "Exception in getRequirementDetails: " + sqe);
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
                log.log(Level.ERROR, "Exception in getRequirementDetails: " + sqle);
                sqle.printStackTrace();
            }
        }
        System.out.println("********************RequirementServiceImpl :: getRequirementDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :04/may/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse :
     *
     * @getReqDetailsBySearch() is used to
     *
     * *****************************************************************************
     */
    public String getReqDetailsBySearch(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        System.out.println("********************RequirementServiceImpl :: getReqDetailsBySearch Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT requirement_id,req_name,no_of_resources,req_skills,req_st_date,req_status "
                    + "FROM acc_requirements WHERE 1=1 AND acc_id=" + requirementAction.getAccountSearchID();
            System.out.println("getReqDetailsBySearch::queryString-------->" + queryString);

            if (!"".equals(requirementAction.getRequirementId())) {
                queryString += " and requirement_id =" + requirementAction.getRequirementId() + " ";
            }
            if (!"-1".equalsIgnoreCase(requirementAction.getRequirementStatus())) {
                queryString += " and req_status like '%" + requirementAction.getRequirementStatus() + "%'";
            }
            if (!"".equals(requirementAction.getReqStart()) && !"".equals(requirementAction.getReqEnd())) {
                startDate = dateUtility.getInstance().convertStringToMySQLDate(requirementAction.getReqStart());
                endDate = dateUtility.getInstance().convertStringToMySQLDate(requirementAction.getReqEnd());

                queryString += " and  req_st_date between '" + startDate + "' and '" + endDate + "' ";
            }
            if (requirementAction.getRequirementSkill() != null) {
                queryString += " and req_skills like '%" + requirementAction.getRequirementSkill() + "%'";
            }
            if (!"".equals(requirementAction.getJobTitle())) {
                queryString += " and req_name like '%" + requirementAction.getJobTitle() + "%'";
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String status = "";
                if (resultSet.getString("req_status").equalsIgnoreCase("O")) {
                    status = "Open";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("F")) {
                    status = "Forecast";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("I")) {
                    status = "In-Progress";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("H")) {
                    status = "Hold";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("S")) {
                    status = "Won";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("L")) {
                    status = "Lost";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("W")) {
                    status = "Withdraw";
                }
                resultString
                        += resultSet.getInt("requirement_id") + "|"
                        + resultSet.getString("req_name") + "|"
                        + resultSet.getString("no_of_resources") + "|"
                        + resultSet.getString("req_st_date") + "|"
                        + status + "|"
                        + "" + "|"
                        + "" + "^";

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
        System.out.println("********************RequirementServiceImpl :: getReqDetailsBySearch Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 04/may/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getSkillDetaisls() method is used to
     *
     * *****************************************************************************
     */
    public String getSkillDetaisls(RequirementAction requirementAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getSkillDetaisls Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT req_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getRequirementId();

            System.out.println("getSkillDetaisls::queryString" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString
                        += resultSet.getString("req_skills");
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
        System.out.println("********************RequirementServiceImpl :: getSkillDetaisls Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 04/may/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : g@getPreferedSkillDetails() getting search vendor details
     *
     * *****************************************************************************
     */
    public String getPreferedSkillDetails(RequirementAction requirementAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getPreferedSkillDetails Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT preferred_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getRequirementId();

            System.out.println("getPreferedSkillDetails::queryString" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString
                        += resultSet.getString("preferred_skills");
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
        System.out.println("********************RequirementServiceImpl :: getPreferedSkillDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :08/may/2015
     *
     * Author :praveen<pkatru@miraclesoft.com>
     *
     * ForUse :
     *
     * @getSearchRequirementsList() getting search vendor details
     *
     * *****************************************************************************
     */
    public String getSearchRequirementsList(HttpServletRequest httpServletRequest, RequirementAction requirementAction, Map skillsMap) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getSearchRequirementsList Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();

            int sessionusrPrimaryrole = requirementAction.getPrimaryRole();
            if (typeofusr.equalsIgnoreCase("VC")) {

                queryString = "SELECT account_name,r.created_date,tax_term,jdid,requirement_id,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_created_by,req_status,req_contact1,req_contact2 ,created_by_org_id,target_rate,max_rate FROM acc_requirements acc LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id  LEFT OUTER JOIN accounts a ON account_id= created_by_org_id WHERE ven_id=" + requirementAction.getSessionOrgId() + " AND  NOW() >= req_access_time AND  r.STATUS LIKE 'Active' ";
                System.out.println("getSearchRequirementsList::queryString------->" + queryString);
            } else {

                if (sessionusrPrimaryrole == 3) { // for pr.manager
                    queryString = "SELECT DISTINCT(requirement_id),account_name,req_created_by,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                            + " WHERE 1=1 AND  acc_id=" + requirementAction.getSessionOrgId() + " AND  req_created_by=" + requirementAction.getUserSessionId();
                } else if (sessionusrPrimaryrole == 1) {
                    queryString = "SELECT DISTINCT(requirement_id),account_name,req_created_by,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                            + " WHERE 1=1 AND  acc_id=" + requirementAction.getAccountSearchID();
                } else {

                    queryString = "SELECT DISTINCT(requirement_id),account_name,req_created_by,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                            + " WHERE 1=1 AND  acc_id=" + requirementAction.getSessionOrgId();
                }
            }

            if (requirementAction.getJdId() != null && !"".equals(requirementAction.getJdId())) {
                queryString += " and jdid like '%" + requirementAction.getJdId() + "%'";
            }

            if (typeofusr.equalsIgnoreCase("VC") || typeofusr.equalsIgnoreCase("AC")) {
                if (sessionusrPrimaryrole != 13) {
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST) != null) {
                        queryString += " and req_category IN (" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST).toString() + ") ";
                    }
                }
            }

            if (requirementAction.getReqCategoryValue() != -1) {
                if (!typeofusr.equalsIgnoreCase("VC")) {
                    queryString += " and req_category =" + requirementAction.getReqCategoryValue() + "";
                }
            }

            if (!requirementAction.getVendor().equalsIgnoreCase("yes")) {
                if (!"-1".equalsIgnoreCase(requirementAction.getRequirementStatus())) {
                    queryString += " and req_status = '" + requirementAction.getRequirementStatus() + "'";
                }
            } else if (typeofusr.equalsIgnoreCase("VC")) {
                queryString += " and (req_status = 'R' OR req_status = 'OR')";
            } else {
                if (!"-1".equalsIgnoreCase(requirementAction.getRequirementStatus())) {
                    queryString += " and req_status = '" + requirementAction.getRequirementStatus() + "'";
                }
            }
            if (!"".equals(requirementAction.getReqStart()) && !"".equals(requirementAction.getReqEnd())) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getReqStart());
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getReqEnd());
                queryString += " and  req_st_date between '" + startDate + "' and '" + endDate + "' ";
            }

            String str = requirementAction.getSkillCategoryArry();
            if (str != null && !"".equals(requirementAction.getSkillCategoryArry())) {

                if (!"".equalsIgnoreCase(requirementAction.getSkillCategoryArry())) {
                    queryString += " and MATCH(req_skills) AGAINST ('" + str + "' IN BOOLEAN MODE)";
                }
            }
            if (!"".equals(requirementAction.getJobTitle()) && requirementAction.getJobTitle() != null) {
                queryString += " and req_name like '%" + requirementAction.getJobTitle().trim() + "%'";
            }
            if (requirementAction.getReqCreatedBy() != -1 && requirementAction.getReqCreatedBy() != 0) {
                queryString += " and req_created_by = " + requirementAction.getReqCreatedBy() + " ";
            }
            if (!"".equals(requirementAction.getCustomerName()) && requirementAction.getCustomerName() != null) {
                queryString += " and account_name LIKE '%" + requirementAction.getCustomerName().trim() + "%' ";
            }

            queryString += " GROUP BY requirement_id  order by jdid desc LIMIT 100";
            System.out.println("getSearchRequirementsList::queryString------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String status = "";
                if (resultSet.getString("req_status").equalsIgnoreCase("O")) {
                    status = "Opened";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("R")) {
                    status = "Released";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("OR")) {
                    status = "Open for Resume";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("C")) {
                    status = "Closed";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("F")) {
                    status = "Forecast";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("I")) {
                    status = "Inprogess";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("H")) {
                    status = "Hold";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("W")) {
                    status = "Withdrawn";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("S")) {
                    status = "Won";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("L")) {
                    status = "Lost";
                }
                String postedDate = "";
                Date myDate = resultSet.getDate("created_date");
                if (myDate != null) {
                    postedDate = com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(myDate);
                } else {
                    postedDate = "---";
                }

                resultString
                        += resultSet.getInt("requirement_id") + "|"
                        + resultSet.getString("req_name") + "|"
                        + resultSet.getString("no_of_resources") + "|"
                        + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("req_st_date")) + "|"
                        + status + "|" + resultSet.getString("req_contact1") + "|"
                        + resultSet.getString("req_contact2") + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")) + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")) + "|"
                        + resultSet.getString("account_name") + "|"
                        + resultSet.getString("req_skills") + "|"
                        + resultSet.getString("preferred_skills") + "|"
                        + resultSet.getString("jdid") + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("req_created_by")) + "|"
                        + resultSet.getString("tax_term") + "|"
                        + resultSet.getString("target_rate") + "|"
                        + resultSet.getString("max_rate") + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfSubmisions(resultSet.getInt("requirement_id"), 0) + "|"
                        + postedDate + "|"
                        + queryString + "^";
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
        System.out.println("********************RequirementServiceImpl :: getSearchRequirementsList Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse :
     *
     * @getRecruiterOverlay() getting search vendor details
     *
     * *****************************************************************************
     */
    public String getRecruiterOverlay(RequirementAction requirementAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getRecruiterOverlay Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "select concat(first_name,'.',last_name) as Name,email1,phone1 from users where usr_id=" + requirementAction.getId();
            System.out.println("getRecruiterOverlay::query-------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString
                        += resultSet.getString("Name") + "|"
                        + resultSet.getString("email1") + "|"
                        + resultSet.getString("phone1");

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
        System.out.println("********************RequirementServiceImpl :: getRecruiterOverlay Method End*********************");
        return resultString;

    }

    /**
     * *****************************************************************************
     * Date : 09/june/2015
     *
     * Author : jagan<jchukkala@miraclesoft.com>
     *
     * ForUse :
     *
     * @getSkillOverlay() getting skills on overlay for requirement List details
     *
     * *****************************************************************************
     */
    public String getSkillOverlay(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getSkillOverlay Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT req_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getId();
            System.out.println("getSkillOverlay::query------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString
                        += resultSet.getString("req_skills");

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
        System.out.println("********************RequirementServiceImpl :: getSkillOverlay Method End*********************");
        return resultString;

    }

    /**
     * *****************************************************************************
     * Date : 09/june/2015
     *
     * Author :jagan<jchukkala@miraclesoft.com>
     *
     * ForUse :
     *
     * @getPreSkillOverlay() getting prefered skills on overlay for requirement
     * List details
     *
     * *****************************************************************************
     */
    public String getPreSkillOverlay(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getPreSkillOverlay Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT preferred_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getId();
            System.out.println("getPreSkillOverlay::query------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString
                        += resultSet.getString("preferred_skills");

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
        System.out.println("********************RequirementServiceImpl :: getPreSkillOverlay Method End*********************");
        return resultString;

    }

    /**
     * *****************************************************************************
     * Date : 04/may/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : storeProofData() method is used to
     *
     * *****************************************************************************
     */
    public String storeProofData(HttpServletRequest httpServletRequest, RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        StringBuffer stringBuffer = new StringBuffer();

        String Result = "";
        boolean isExceute = false;

        String str = requirementAction.getPropsedSkills();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String skillsResultString = "";
        log.info("********************RequirementServiceImpl :: storeProofData Method Start*********************");
        while (stk.hasMoreTokens()) {
            skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));

        }

        requirementAction.setSkillList(StringUtils.chop(skillsResultString));
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{CALL addConsultantProof(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            log.info("storeProofData :: procedure name : addConsultantProof ");
            callableStatement.setInt(1, requirementAction.getUserSessionId());

            callableStatement.setInt(2, DataSourceDataProvider.getInstance().getusrIdByemailId(requirementAction.getConEmail()));
            callableStatement.setString(3, requirementAction.getProofType());

            callableStatement.setString(4, requirementAction.getProofValue());
            callableStatement.setString(5, requirementAction.getReqId());
            callableStatement.setString(6, requirementAction.getRatePerHour());

            if (requirementAction.getResourceType().equalsIgnoreCase("VC")) {
                callableStatement.setString(7, "E");

            } else {

                callableStatement.setString(7, "C");
            }

            callableStatement.setString(8, requirementAction.getFilesPath());
            callableStatement.setString(9, requirementAction.getFileFileName());
            callableStatement.setString(10, requirementAction.getSkillList());
            callableStatement.setInt(11, Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            callableStatement.setString(12, requirementAction.getVendorComments());
            String ssnNo = com.mss.msp.util.DataUtility.encrypted(requirementAction.getSsnNo());
            callableStatement.setString(13, ssnNo);
            callableStatement.registerOutParameter(14, Types.VARCHAR);
            isExceute = callableStatement.execute();
            Result = callableStatement.getString(14);
            if (Result.equalsIgnoreCase("AddSuccess")) {
                log.info("****************** in impl result after NotExist:::::::::" + Result);
            } else {
                 log.info("****************** in impl result after :::::::::" + Result);
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
        log.info("********************RequirementServiceImpl :: storeProofData Method End*********************");
        return Result;
    }

    /**
     * *****************************************************************************
     * Date : 06/02/2015
     *
     * Author : praveen <pkatru@miraclesoft.com>
     *
     * ForUse :
     *
     * @doReleaseRequirements() release reuirements for tier 1
     *
     *
     * *****************************************************************************
     */
    public int doReleaseRequirements(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        int count = 0;
        boolean isExceute = false;
        log.info("********************RequirementServiceImpl :: doReleaseRequirements Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{CALL spReleaseRequirements(?,?,?,?,?,?)}");
             log.info("doReleaseRequirements :: procedure name : spReleaseRequirements ");
            callableStatement.setInt(1, requirementAction.getSessionOrgId());
            callableStatement.setString(2, requirementAction.getTaxTerm());
            callableStatement.setString(3, requirementAction.getRequirementId());
            callableStatement.setInt(4, requirementAction.getUserSessionId());
            callableStatement.setString(5, requirementAction.getAccount_name());

            callableStatement.registerOutParameter(6, Types.INTEGER);
            isExceute = callableStatement.execute();
            count = callableStatement.getInt(6);

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        log.info("********************RequirementServiceImpl :: doReleaseRequirements Method End*********************");
        return count;
    }

    /**
     * *****************************************************************************
     * Date : 06/03/2015
     *
     * Author : praveen <pkatru@miraclesoft.com>
     *
     * ForUse :
     *
     * @doUpdateStatusReport() update status in requirement table
     *
     *
     * *****************************************************************************
     */
    public int doUpdateStatusReport(RequirementAction aThis) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        int res = 0;
        System.out.println("********************RequirementServiceImpl :: doUpdateStatusReport Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "update acc_requirements set req_status='R' where requirement_id=" + aThis.getRequirementId();
            System.out.println("doUpdateStatusReport::query------>" + queryString);
            statement = connection.createStatement();
            res = statement.executeUpdate(queryString);

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
        System.out.println("********************RequirementServiceImpl :: doUpdateStatusReport Method End*********************");
        return res;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getOrgIdCustomer() method is used to
     *
     *
     * *****************************************************************************
     */
    public int getOrgIdCustomer(String requirementid) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        int resultString = 0;
        System.out.println("********************RequirementServiceImpl :: getOrgIdCustomer Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT acc_id FROM acc_requirements WHERE requirement_id=" + requirementid;

            System.out.println("getOrgIdCustomer::query------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString = resultSet.getInt("acc_id");
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
        System.out.println("********************RequirementServiceImpl :: getOrgIdCustomer Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/03/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getRequirementDashBoardDetails() update status in requirement table
     *
     *
     * *****************************************************************************
     */
    public String getRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getRequirementDashBoardDetails Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            if (requirementAction.getTypeOfUser().equalsIgnoreCase("SA")) {
                queryString = "SELECT COUNT(requirement_id) AS total,"
                        + "COUNT(IF(req_status='O',1, NULL)) 'Open',"
                        + "COUNT(IF(req_status='R',1, NULL)) 'Released',"
                        + "COUNT(IF(req_status='C',1, NULL)) 'Closed',"
                        + "a.account_name,a.account_id "
                        + "FROM acc_requirements "
                        + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                        + "WHERE DATE_FORMAT(req_st_date,'%Y')=" + requirementAction.getDashYears() + " ";
            } else {
                queryString = "SELECT COUNT(requirement_id) AS total,"
                        + "COUNT(IF(req_status='O',1, NULL)) 'Open',"
                        + "COUNT(IF(req_status='R',1, NULL)) 'Released',"
                        + "COUNT(IF(req_status='C',1, NULL)) 'Closed',"
                        + "a.account_name,a.account_id "
                        + "FROM acc_requirements "
                        + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                        + "LEFT OUTER JOIN csrorgrel csr ON(a.account_id=csr.org_id) "
                        + "WHERE DATE_FORMAT(req_st_date,'%Y')=" + requirementAction.getDashYears() + " "
                        + "AND csr.STATUS = 'active'"
                        + "AND csr.csr_id=" + requirementAction.getUserSessionId() + " ";
            }

            if (!"".equalsIgnoreCase(requirementAction.getCsrCustomer())) {
                queryString = queryString + " AND acc_id = '" + requirementAction.getCsrCustomer() + "'  ";
            }
            queryString = queryString + "GROUP BY a.account_id";
            System.out.println("getRequirementDashBoardDetails::query------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("total") + "|"
                        + resultSet.getString("Open") + "|"
                        + resultSet.getString("Released") + "|"
                        + resultSet.getString("Closed") + "|"
                        + resultSet.getString("account_name") + "|"
                        + resultSet.getString("account_id") + "^";
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

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************RequirementServiceImpl :: getRequirementDashBoardDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/03/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getRequirementDashBoardDetailsOnOverlay() update status in requirement
     * table
     *
     * *****************************************************************************
     */
    public String getRequirementDashBoardDetailsOnOverlay(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getRequirementDashBoardDetailsOnOverlay Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT MONTHNAME(req_st_date) AS MONTH,"
                    + "COUNT(requirement_id) AS requirements,"
                    + "a.account_name "
                    + "FROM acc_requirements "
                    + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                    + "WHERE req_st_date LIKE '%" + requirementAction.getDashYears() + "%' "
                    + "AND a.account_id=" + requirementAction.getAccountId() + " "
                    + "GROUP BY DATE_FORMAT(req_st_date, '%m')";

            System.out.println("getRequirementDashBoardDetailsOnOverlay::query------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("MONTH") + "|"
                        + resultSet.getString("requirements") + "^";
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
        System.out.println("********************RequirementServiceImpl :: getRequirementDashBoardDetailsOnOverlay Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : 06/03/2015
     *
     * Author :ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getDefaultCustomerRequirementDashBoardDetails() method is used
     * to
     *
     *
     * *****************************************************************************
     */
    public List getDefaultCustomerRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        ArrayList<RequirementVTO> customerDashBoardList = new ArrayList<RequirementVTO>();
        System.out.println("********************RequirementServiceImpl :: getDefaultCustomerRequirementDashBoardDetails Method Start*********************");

        try {

            int year = Calendar.getInstance().get(Calendar.YEAR);
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT MONTHNAME(req_st_date) AS MONTH,"
                    + "COUNT(IF(req_status='O',1, NULL)) 'Open',"
                    + "COUNT(IF(req_status='R',1, NULL)) 'Released',"
                    + "COUNT(IF(req_status='C',1, NULL)) 'Closed',"
                    + "COUNT(IF(req_status='F',1, NULL)) 'Forecast',"
                    + "COUNT(IF(req_status='I',1, NULL)) 'Inprogress',"
                    + "COUNT(IF(req_status='H',1, NULL)) 'Hold',"
                    + "COUNT(IF(req_status='W',1, NULL)) 'Withdrawn',"
                    + "COUNT(IF(req_status='S',1, NULL)) 'Won',"
                    + "COUNT(IF(req_status='L',1, NULL)) 'Lost',"
                    + "COUNT(requirement_id) AS total "
                    + "FROM acc_requirements "
                    + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                    + "WHERE DATE_FORMAT(req_st_date,'%Y')=" + year + " "
                    + "AND acc_id =" + requirementAction.getSessionOrgId() + " "
                    + "GROUP BY DATE_FORMAT(req_st_date,'%m')";
            System.out.println("getDefaultCustomerRequirementDashBoardDetails::queryDashBoard....>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                int count = 0;
                count = resultSet.getInt("Forecast") + resultSet.getInt("Inprogress") + resultSet.getInt("Hold") + resultSet.getInt("Withdrawn") + resultSet.getInt("Won") + resultSet.getInt("Lost");

                RequirementVTO custDashBoardVTO = new RequirementVTO();
                custDashBoardVTO.setMonth(resultSet.getString("MONTH"));
                custDashBoardVTO.setTotal(resultSet.getString("total"));
                custDashBoardVTO.setOpen(resultSet.getString("Open"));
                custDashBoardVTO.setReleased(resultSet.getString("Released"));
                custDashBoardVTO.setClosed(resultSet.getString("Closed"));
                custDashBoardVTO.setOthersCount(count);

                customerDashBoardList.add(custDashBoardVTO);
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
        System.out.println("********************RequirementServiceImpl :: getDefaultCustomerRequirementDashBoardDetails Method End*********************");
        return customerDashBoardList;
    }

    /**
     * *****************************************************************************
     * Date : 06/03/2015
     *
     * Author :ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getCustomerRequirementDashBoardDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getCustomerRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getCustomerRequirementDashBoardDetails Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT MONTHNAME(req_st_date) AS MONTH,"
                    + "COUNT(IF(req_status='O',1, NULL)) 'Open',"
                    + "COUNT(IF(req_status='R',1, NULL)) 'Released',"
                    + "COUNT(IF(req_status='OR',1, NULL)) 'Open for Resume',"
                    + "COUNT(IF(req_status='C',1, NULL)) 'Closed',"
                    + "COUNT(IF(req_status='F',1, NULL)) 'Forecast',"
                    + "COUNT(IF(req_status='I',1, NULL)) 'Inprogress',"
                    + "COUNT(IF(req_status='H',1, NULL)) 'Hold',"
                    + "COUNT(IF(req_status='W',1, NULL)) 'Withdrawn',"
                    + "COUNT(IF(req_status='S',1, NULL)) 'Won',"
                    + "COUNT(IF(req_status='L',1, NULL)) 'Lost',"
                    + "COUNT(requirement_id) AS total "
                    + "FROM acc_requirements LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                    + "WHERE acc_id =" + requirementAction.getSessionOrgId() + " "
                    + "AND DATE_FORMAT(req_st_date,'%Y')='" + requirementAction.getDashYears() + "' ";

            if (!"-1".equalsIgnoreCase(requirementAction.getDashMonths())) {
                queryString = queryString + " AND DATE_FORMAT(req_st_date,'%m')= '" + requirementAction.getDashMonths() + "'  ";
            } else {
                queryString = queryString + " GROUP BY DATE_FORMAT(req_st_date,'%m')";
            }
            System.out.println("getCustomerRequirementDashBoardDetails::queryfororg id-------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                int count = 0;
                count = resultSet.getInt("Forecast") + resultSet.getInt("Inprogress") + resultSet.getInt("Hold") + resultSet.getInt("Withdrawn") + resultSet.getInt("Won") + resultSet.getInt("Lost");
                resultString += resultSet.getString("MONTH") + "|"
                        + resultSet.getString("Open") + "|"
                        + resultSet.getString("Released") + "|"
                        + resultSet.getString("Closed") + "|"
                        + resultSet.getString("total") + "|"
                        + count + "|"
                        + resultSet.getString("Open for Resume") + "^";

                if (resultSet.getString("MONTH").equalsIgnoreCase("null")) {
                    resultString = "";
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
        System.out.println("********************RequirementServiceImpl :: getCustomerRequirementDashBoardDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorRequirementsDashBoard() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getVendorRequirementsDashBoard(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getVendorRequirementsDashBoard Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT ac.account_name,ac.account_id,"
                    + "COUNT(rcr.consultantId) AS total,"
                    + "COUNT(IF(rcr.STATUS='Processing',1, NULL)) 'Processing',"
                    + "COUNT(IF(rcr.STATUS='Selected',1, NULL)) 'Selected',"
                    + "COUNT(IF(rcr.STATUS='Rejected',1, NULL)) 'Rejected',"
                    + "COUNT(IF(rcr.STATUS='Selected',1, NULL)) 'Won',"
                    + "COUNT(IF(rcr.STATUS<>'Selected',1, NULL)) 'Lost' "
                    + "FROM acc_requirements a "
                    + "LEFT OUTER JOIN req_con_rel rcr ON(a.requirement_id=rcr.reqId) "
                    + "LEFT OUTER JOIN accounts ac ON(ac.account_id=rcr.createdbyOrgId) "
                    + "WHERE 1=1 ";
            if (requirementAction.getDashYears() != null && !"".equals(requirementAction.getDashYears())) {
                queryString = queryString + " AND EXTRACT(YEAR FROM rcr.created_Date)= " + requirementAction.getDashYears() + "";
            }

            if (!"".equalsIgnoreCase(requirementAction.getCsrCustomer())) {
                queryString = queryString + " AND ac.account_id= '" + requirementAction.getCsrCustomer() + "'  ";
            }
            queryString = queryString + " GROUP BY ac.account_id";
            System.out.println("getVendorRequirementsDashBoard::queryfororg id-------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("total") + "|"
                        + resultSet.getString("Processing") + "|"
                        + resultSet.getString("Selected") + "|"
                        + resultSet.getString("Rejected") + "|"
                        + resultSet.getString("Won") + "|"
                        + resultSet.getString("Lost") + "|"
                        + resultSet.getString("account_name") + "|"
                        + resultSet.getString("account_id") + "^";
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
        System.out.println("********************RequirementServiceImpl :: getVendorRequirementsDashBoard Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorDashBoardDetailsOnOverlay() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getVendorDashBoardDetailsOnOverlay(RequirementAction requirementAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        System.out.println("********************RequirementServiceImpl :: getVendorDashBoardDetailsOnOverlay Method Start*********************");
        try {
            System.out.println("IN IMPL getVendorDashBoardDetailsOnOverlay()");
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT MONTHNAME(rcr.created_Date) AS MONTH,"
                    + "COUNT(IF(rcr.STATUS='Selected',1, NULL)) 'Won',"
                    + "COUNT(IF(rcr.STATUS<>'Selected',1, NULL)) 'Lost' "
                    + "FROM acc_requirements a "
                    + "LEFT OUTER JOIN req_con_rel rcr ON(a.requirement_id=rcr.reqId) "
                    + "LEFT OUTER JOIN accounts ac ON(ac.account_id=rcr.createdbyOrgId) "
                    + "WHERE rcr.created_Date LIKE '%" + requirementAction.getDashYears() + "%' "
                    + "AND ac.account_id=" + requirementAction.getAccountId() + " "
                    + "GROUP BY DATE_FORMAT(rcr.created_Date, '%m')";

            System.out.println("getVendorDashBoardDetailsOnOverlay::queryfororg id------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("MONTH") + "|"
                        + resultSet.getString("Won") + "|"
                        + resultSet.getString("Lost") + "^";
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
        System.out.println("********************RequirementServiceImpl :: getVendorDashBoardDetailsOnOverlay Method End*********************");
        return resultString;
    }
}
