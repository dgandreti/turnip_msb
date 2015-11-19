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

/**
 *
 * @author miracle
 */
public class RequirementServiceImpl implements RequirementService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public int addRequirementDetails(RequirementAction requirementAction, int orgId) throws ServiceLocatorException {
        CallableStatement callableStatement = null;
//        PreparedStatement preparedStatement = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        String queryString = "";
//        String resultString = "";
        int result = 0;

////        int i = 0;
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        System.out.println("org id is" + orgId);
        System.out.println("user id is==================>>>>: " + requirementAction.getUserSessionId());
        System.out.println("Start date==================>>>>: " + requirementAction.getRequirementFrom());
        System.out.println("End date is====================>>>>: " + requirementAction.getRequirementTo());
        System.out.println(" Serach id==================>>>>: " + requirementAction.getAccountSearchID());
        System.out.println("reports to==================>>>>: " + requirementAction.getRequirementCountry());
        System.out.println("reasson is==================>>>>: " + requirementAction.getRequirementComments());
        System.out.println("Requirement description is==================>>>>: " + requirementAction.getRequirementJobdesc());
        System.out.println("requirementAction.getReqSkillSet()----------->" + requirementAction.getReqSkillSet());
        String str = requirementAction.getSkillCategoryArry();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String reqSkillsResultString = "";
        while (stk.hasMoreTokens()) {
            reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
        }
        requirementAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
        System.out.println("reqSkillsResultString---------->" + reqSkillsResultString);
        //int reportingPersonId=DataSourceDataProvider.getInstance().getReportingPersonIDByUserId(userSessionId);
        //System.out.println("reporting id is---->"+reportingPersonId);

        try {
            startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getRequirementFrom());
            //  endDate = dateUtility.getInstance().convertStringToMySQLDate(requirementAction.getRequirementTo());
//            queryString = " INSERT into acc_requirements(acc_id, req_type,req_name,req_comments,req_status,req_created_by,req_skills,preferred_skills,req_st_date,no_of_resources,req_contact1,req_contact2,"
//                    + "target_rate,req_duration,tax_term,req_location,req_function_desc,req_responsibilities,created_by_org_id)"
//                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //  System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL addRequirements(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("After Connection");

            callableStatement.setInt(1, requirementAction.getOrgId());
            callableStatement.setString(2, requirementAction.getRequirementType());
            callableStatement.setString(3, requirementAction.getRequirementName());
            callableStatement.setInt(4, requirementAction.getRequirementYears());
            callableStatement.setString(5, requirementAction.getRequirementComments());
            // preparedStatement.setString(5, requirementAction.getRequirementDescription());
            callableStatement.setString(6, requirementAction.getRequirementStatus());

            callableStatement.setInt(7, requirementAction.getUserSessionId());
            callableStatement.setString(8, requirementAction.getReqSkillSet());
            callableStatement.setString(9, requirementAction.getPreSkillCategoryArry());
            callableStatement.setString(10, startDate);
            //   preparedStatement.setString(11, endDate);
            callableStatement.setInt(11, requirementAction.getRequirementNoofResources());
            callableStatement.setInt(12, requirementAction.getRequirementContact1());
            callableStatement.setInt(13, requirementAction.getRequirementContact2());
            //preparedStatement.setString(15, requirementAction.getRequirementCountry());
            // preparedStatement.setString(16, requirementAction.getRequirementState());
            //  preparedStatement.setString(15, requirementAction.getRequirementAddress());
            //preparedStatement.setString(18, requirementAction.getRequirementPractice());
            //  preparedStatement.setString(19, requirementAction.getRequirementContactNo());
            callableStatement.setString(14, requirementAction.getRequirementTargetRate());
            callableStatement.setString(15, requirementAction.getRequirementDuration());
            callableStatement.setString(16, requirementAction.getRequirementTaxTerm());
            callableStatement.setString(17, requirementAction.getRequirementLocation());
            // preparedStatement.setString(19, requirementAction.getRequirementPresales1());
            // preparedStatement.setString(20, requirementAction.getRequirementPresales2());
            //preparedStatement.setString(22, requirementAction.getRequirementReason());
            callableStatement.setString(18, requirementAction.getRequirementJobdesc());
            callableStatement.setString(19, requirementAction.getRequirementResponse());
            callableStatement.setInt(20, orgId);
            callableStatement.setString(21, requirementAction.getBillingContact());
            callableStatement.setString(22, requirementAction.getRequirementDurationDescriptor());
            callableStatement.setInt(23, requirementAction.getReqCategoryValue());
            callableStatement.setString(24, requirementAction.getRequirementMaxRate());
            if( requirementAction.getRequirementTaxTerm().equals("CO")){
            callableStatement.setString(25, requirementAction.getBillingtype());
            }else{
            callableStatement.setString(25,"");
  
            }

            callableStatement.registerOutParameter(26, Types.INTEGER);
            
            // preparedStatement.setString(24, requirementAction.getRequirementPresales2());
            // preparedStatement.setString(25, requirementAction.getRequirementReason());
//            
//            preparedStatement.setInt(8, userSessionId);
            result = callableStatement.executeUpdate();
            System.out.println("Add requirement" + result);
            if (result > 0) {
                System.out.println("===================================== record added ====================================================================>");
                return result;
            } else {
                System.out.println("===================================== record not added ====================================================================>");
                return result;
            }
//
//
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
        System.out.println("result is" + result);
        return result;


    }

    public RequirementVTO editrequirement(String requirementid, Map skillsMap) throws ServiceLocatorException {
        Connection connection = null;
        RequirementVTO requirementVTO = new RequirementVTO();
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT requirement_id,req_name,req_comments,req_desc,req_status,req_skills,req_st_date,req_tr_date,no_of_resources,req_contact1,req_contact2,"
                    + "hr_week_month,target_rate,req_duration,tax_term,req_location,presales1,presales2,req_function_desc,req_responsibilities,preferred_skills,req_years_exp,billing_contact,req_category,max_rate,billingtype  FROM acc_requirements WHERE requirement_id=" + requirementid;
            System.out.println("queryString-->" + queryString);
            // queryString = "SELECT first_name FROM users where usr_id=1009";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                requirementVTO.setReqCatgory(resultSet.getInt("req_category"));
                System.out.println("this is req category-->" + requirementVTO.getReqCatgory());
                requirementVTO.setRequirementId(resultSet.getInt("requirement_id"));
                requirementVTO.setRequirementStatus(resultSet.getString("req_status"));
                requirementVTO.setRequirementExp(resultSet.getString("req_years_exp"));
                requirementVTO.setRequirementJobdesc(resultSet.getString("req_function_desc"));
                // requirementVTO.setRequirementReason(resultSet.getString("reject_reason"));
                requirementVTO.setRequirementResponse(resultSet.getString("req_responsibilities"));
                requirementVTO.setRequirementName(resultSet.getString("req_name"));
                requirementVTO.setRequirementComments(resultSet.getString("req_comments"));
                requirementVTO.setRequirementDescription(resultSet.getString("req_desc"));
                // requirementVTO.setRequirementSkills(resultSet.getString("req_skills"));
//                String str = resultSet.getString("req_skills");
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                List list = new ArrayList();
//
//                while (stk.hasMoreTokens()) {
//                    list.add(stk.nextToken());
//                }
//                // System.out.println("list----------->"+list);
//                //  List list = new ArrayList(idsMap.keySet());
//
//                requirementVTO.setSkillSetList(list);
//                String str2 = resultSet.getString("req_skills");
//                StringTokenizer stk2 = new StringTokenizer(str2, ",");
//                //List list1 = new ArrayList();
//      /          String reqSkillsResultString = "";
//                while (stk2.hasMoreTokens()) {
//                    reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId((stk2.nextToken())) + ",";
//                    //  list1.add(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId((stk.nextToken())));
//                }
//                System.out.println("reqSkillsResultString----------->" + reqSkillsResultString);
//                String str = reqSkillsResultString;
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                List list = new ArrayList();
//
//                while (stk.hasMoreTokens()) {
//                    list.add(stk.nextToken());
//                }
//
//
//  /              requirementVTO.setSkillSetList(list);
                String str2 = resultSet.getString("req_skills");
                StringTokenizer stk2 = new StringTokenizer(str2, ",");
                //List list1 = new ArrayList();
                //  String reqSkillsResultString="";
                List list = new ArrayList();
                while (stk2.hasMoreTokens()) {
                    //  System.out.println(getKeyFromValue(map,stk2.nextToken()));
                    list.add(getKeyFromValue(skillsMap, stk2.nextToken()));
                    //  reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId((stk2.nextToken()))+",";
                    //  list1.add(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId((stk.nextToken())));
                }
                System.out.println("list for req skills----------->" + list);
                // String str = reqSkillsResultString;
                // StringTokenizer stk = new StringTokenizer(str, ",");
                requirementVTO.setSkillSetList(list);

                String str1 = resultSet.getString("preferred_skills");
                StringTokenizer stk1 = new StringTokenizer(str1, ",");
                List list1 = new ArrayList();

                while (stk1.hasMoreTokens()) {
                    list1.add(stk1.nextToken());
                }
                // System.out.println("list----------->"+list);
                //  List list = new ArrayList(idsMap.keySet());

                requirementVTO.setPreSkillSetList(list1);

                // requirementVTO.setRequirementCountry(resultSet.getString("country"));
                //requirementVTO.setRequirementState(resultSet.getString("state"));
                // requirementVTO.setRequirementAddress(resultSet.getString("address"));
                // requirementVTO.setRequirementPractice(resultSet.getString("practice"));
                requirementVTO.setRequirementTargetRate(resultSet.getString("target_rate"));
                requirementVTO.setRequirementDuration(resultSet.getString("req_duration"));
                requirementVTO.setRequirementTaxTerm(resultSet.getString("tax_term"));
                System.out.println("here i print taxt term---->" + resultSet.getString("tax_term"));
                requirementVTO.setRequirementLocation(resultSet.getString("req_location"));
                // requirementVTO.setRequirementAddress(resultSet.getString("address"));
                // requirementVTO.setRequirementContactNo(resultSet.getString("contact_no"));
                requirementVTO.setRequirementPresales1(resultSet.getString("presales1"));
                requirementVTO.setRequirementPresales2(resultSet.getString("presales2"));
                requirementVTO.setRequirementNoofResources(resultSet.getInt("no_of_resources"));
                requirementVTO.setRequirementFrom(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("req_st_date")));
                //  requirementVTO.setRequirementTo(com.mss.msp.util.DateUtility.getInstance().convertDateToView(resultSet.getDate("req_tr_date")));
                requirementVTO.setRequirementContact1(resultSet.getString("req_contact1"));
                requirementVTO.setRequirementContact2(resultSet.getString("req_contact2"));
                //  requirementVTO.setRequirementPreferredSkills(resultSet.getString("preferred_skills"));
                requirementVTO.setBillingContact(resultSet.getString("billing_contact"));
                requirementVTO.setRequirementDurationDescriptor(resultSet.getString("hr_week_month"));
                requirementVTO.setRequirementMaxRate(resultSet.getString("max_rate"));
                 requirementVTO.setBillingtype(resultSet.getString("billingtype"));

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

    public int updateRequirement(int userid, RequirementAction requirementAction) throws ServiceLocatorException {
        System.out.println("in top");

        String queryString = "";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String FromDate = "";
        String ToDate = "";
        DateUtility dateUtility = new DateUtility();
        System.out.println("in the update requirement mehto");
        int updated = 0;
        try {
            FromDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getRequirementFrom());
            //  ToDate = dateUtility.getInstance().convertStringToMySQLDate(requirementAction.getRequirementTo());

            queryString = "UPDATE acc_requirements SET req_name=?,req_comments=?,req_status=?,req_skills=?,req_st_date=?,no_of_resources=?,req_contact1=?,req_contact2=?,"
                    + "target_rate=?,req_duration=?,tax_term=?,req_location=?,req_function_desc=?,"
                    + "req_responsibilities=?,req_modified_date=?,req_modified_by=?,preferred_skills=?,req_years_exp=?,billing_contact=?,hr_week_month=?,max_rate=?,billingtype=?  WHERE requirement_id=" + requirementAction.getRequirementId();
            System.out.println("queryString-->" + queryString);
            String str = requirementAction.getSkillCategoryArry();
            StringTokenizer stk = new StringTokenizer(str, ",");
            String reqSkillsResultString = "";
            while (stk.hasMoreTokens()) {
                reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
            }
            requirementAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
            System.out.println("reqSkillsResultString---------->" + reqSkillsResultString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            // preparedStatement.setString(1, requirementAction.getRequirementExp());
            preparedStatement.setString(1, requirementAction.getRequirementName());
            preparedStatement.setString(2, requirementAction.getRequirementComments());
            // preparedStatement.setString(3, requirementAction.getRequirementDescription());
            preparedStatement.setString(3, requirementAction.getRequirementStatus());
            preparedStatement.setString(4, requirementAction.getReqSkillSet());
            preparedStatement.setString(5, FromDate);
            //   preparedStatement.setString(7, ToDate);
            preparedStatement.setInt(6, requirementAction.getRequirementNoofResources());
            preparedStatement.setInt(7, requirementAction.getRequirementContact1());
            preparedStatement.setInt(8, requirementAction.getRequirementContact2());
            //  preparedStatement.setString(, requirementAction.getRequirementCountry());
            //  preparedStatement.setString(13, requirementAction.getRequirementState());
            //   preparedStatement.setString(11, requirementAction.getRequirementAddress());
            //  preparedStatement.setString(15, requirementAction.getRequirementPractice());
            //  preparedStatement.setString(16, requirementAction.getRequirementContactNo());
            preparedStatement.setString(9, requirementAction.getRequirementTargetRate());
            preparedStatement.setString(10, requirementAction.getRequirementDuration());
            preparedStatement.setString(11, requirementAction.getRequirementTaxTerm());
            preparedStatement.setString(12, requirementAction.getRequirementLocation());
            //preparedStatement.setString(15, requirementAction.getRequirementPresales1());
            //preparedStatement.setString(16, requirementAction.getRequirementPresales2());
            // preparedStatement.setString(23, requirementAction.getRequirementReason());
            preparedStatement.setString(13, requirementAction.getRequirementJobdesc());
            preparedStatement.setString(14, requirementAction.getRequirementResponse());
            preparedStatement.setTimestamp(15, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(16, userid);
            preparedStatement.setString(17, requirementAction.getPreSkillCategoryArry());
            preparedStatement.setInt(18, requirementAction.getRequirementYears());
            preparedStatement.setString(19, requirementAction.getBillingContact());
            preparedStatement.setString(20, requirementAction.getRequirementDurationDescriptor());
            preparedStatement.setString(21, requirementAction.getRequirementMaxRate());
            if(requirementAction.getRequirementTaxTerm().equals("CO")){
            preparedStatement.setString(22, requirementAction.getBillingtype());
              }else{
           preparedStatement.setString(22, "");
 
              }
            updated = preparedStatement.executeUpdate();

            // System.out.println("------------------------record updated----------------------");
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

        System.out.println("############# IN REQUIREMENT IMPLLLLLLLLLLLLLLLLLL");
        ArrayList<RequirementVTO> requirementList = new ArrayList<RequirementVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "", resultString = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {

            queryString = "SELECT jdid,requirement_id,req_name,no_of_resources,req_skills,req_st_date,req_status,preferred_skills "
                    + "FROM acc_requirements WHERE acc_id=" + requirementAction.getAccountSearchID() + " order by req_name,FIELD(req_status,'O','R','C'),req_created_date desc LIMIT 100 ";

            System.out.println("queryString-->" + queryString);
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
                //  System.out.println(resultSet.getInt("requirement_id") + " " + resultSet.getString("req_name") + " " + resultSet.getInt("no_of_resources") + " " + resultSet.getString("req_st_date") + " " + resultSet.getString("req_status") + " " + resultSet.getString("req_skills"));
                resultString += resultSet.getInt("requirement_id") + "|" + resultSet.getString("req_name") + "|" + resultSet.getInt("no_of_resources") + "|" + resultSet.getString("req_st_date") + "|" + status + "|" + resultSet.getString("req_skills") + "|" + resultSet.getString("preferred_skills") + "|" + resultSet.getInt("jdid") + "^";
            }

            System.out.println("queryString-->" + requirementList);

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

    /**
     * *************************************
     *
     * @getVendorSearchDetails() getting search vendor details
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/may/2015
     *
     *
     **************************************
     *
     */
    public String getReqDetailsBySearch(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";
        System.out.println("start date is@@@---->" + startDate);
        System.out.println("end date is@@@---->" + endDate);



        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT requirement_id,req_name,no_of_resources,req_skills,req_st_date,req_status "
                    + "FROM acc_requirements WHERE 1=1 AND acc_id=" + requirementAction.getAccountSearchID();


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

            System.out.println("query.....getReqDetailsBySearch-->" + queryString);
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
                resultString +=
                        resultSet.getInt("requirement_id") + "|"
                        + resultSet.getString("req_name") + "|"
                        + resultSet.getString("no_of_resources") + "|"
                        + resultSet.getString("req_st_date") + "|"
                        + status + "|"
                        + "" + "|"
                        + "" + "^";

            }
            System.out.println("result=========>getReqDetailsBySearch-->" + resultString);
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
        return resultString;
    }

    /**
     * *************************************
     *
     * @getVendorSearchDetails() getting search vendor details
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/may/2015
     *
     *
     **************************************
     *
     */
    public String getSkillDetaisls(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT req_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getRequirementId();


            System.out.println("query...for..Skills...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString +=
                        resultSet.getString("req_skills");
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
        return resultString;
    }

    /**
     * *************************************
     *
     * @getPreferedSkillDetails() getting search vendor details
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/may/2015
     *
     *
     **************************************
     *
     */
    public String getPreferedSkillDetails(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT preferred_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getRequirementId();


            System.out.println("query...for..Skills...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString +=
                        resultSet.getString("preferred_skills");
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
        return resultString;
    }

    /**
     * *************************************
     *
     * @getSearchRequirementsList() getting search vendor details
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:08/may/2015
     *
     *
     **************************************
     *
     */
    public String getSearchRequirementsList(HttpServletRequest httpServletRequest, RequirementAction requirementAction, Map skillsMap) throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
        String startDate = "";
        String endDate = "";

        String resultString = "";
        try {
            String queryString = "";
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("account flag in impl>>Nag>>>>>" + requirementAction.getAccountFlag());
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            // if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {

            int sessionusrPrimaryrole = requirementAction.getPrimaryRole();
            if (typeofusr.equalsIgnoreCase("VC")) {
//              
                queryString = "SELECT tax_term,jdid,requirement_id,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_created_by,req_status,req_contact1,req_contact2 ,created_by_org_id,target_rate,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel ON requirement_id=req_id WHERE ven_id=" + requirementAction.getSessionOrgId() + " AND  NOW() >= req_access_time AND  STATUS LIKE 'Active' ";

            } else {


                if (sessionusrPrimaryrole == 3) { // for pr.manager
                    queryString = "SELECT * FROM acc_requirements WHERE 1=1 AND  acc_id=" + requirementAction.getSessionOrgId() + " AND  req_created_by=" + requirementAction.getUserSessionId();
                } else if (sessionusrPrimaryrole == 1) {
                    queryString = "SELECT * FROM acc_requirements  WHERE 1=1 AND  acc_id=" + requirementAction.getAccountSearchID();
                } else {
                    // queryString = "SELECT * FROM acc_requirements JOIN usr_grouping ON (req_category=cat_type) WHERE usr_id=" + requirementAction.getUserSessionId() + " and acc_id=" + requirementAction.getSessionOrgId() + " ";
                    queryString = "SELECT * FROM acc_requirements  WHERE 1=1 AND  acc_id=" + requirementAction.getSessionOrgId();
                }
            }



            if (requirementAction.getJdId() != null) {
                queryString += " and jdid like '%" + requirementAction.getJdId() + "%'";
            }

            // for grouping employees like except csr,director, customer-admin and vendoradmin..


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
                    queryString += " and req_status like '%" + requirementAction.getRequirementStatus() + "%'";
                }
            } else if (typeofusr.equalsIgnoreCase("VC")) {
                queryString += " and req_status like '%R%'";
            } else {
                if (!"-1".equalsIgnoreCase(requirementAction.getRequirementStatus())) {
                    queryString += " and req_status like '%" + requirementAction.getRequirementStatus() + "%'";
                }
            }
            if (!"".equals(requirementAction.getReqStart()) && !"".equals(requirementAction.getReqEnd())) {
                startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getReqStart());
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(requirementAction.getReqEnd());
                queryString += " and  req_st_date between '" + startDate + "' and '" + endDate + "' ";
            }
//            if (requirementAction.getRequirementSkill() != null) {
//                queryString += " and req_skills like '%" + requirementAction.getRequirementSkill() + "%'";
//            }
            String str = requirementAction.getSkillCategoryArry();
            if (str != null && !"".equals(requirementAction.getSkillCategoryArry())) {
//            StringTokenizer stk = new StringTokenizer(str, ",");
//            String reqSkillsResultString = "";
//            while (stk.hasMoreTokens()) {
//                reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//            }
//            requirementAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
//            System.out.println("reqSkillsResultString---------->" + reqSkillsResultString);
//            String sampleString = "";
//            sampleString = requirementAction.getReqSkillSet();
//            String[] reqSkillSet = sampleString.split(",");
//           String matchSkillSet="";
//            for (String skillSet : reqSkillSet) {
//              matchSkillSet =matchSkillSet+" +" + skillSet;
//            }
//            System.out.println("matchSkillSet---------->"+matchSkillSet);
                if (!"".equalsIgnoreCase(requirementAction.getSkillCategoryArry())) {
                    queryString += " and MATCH(req_skills) AGAINST ('" + str + "' IN BOOLEAN MODE)";
                }
            }
            if (!"".equals(requirementAction.getJobTitle())) {
                queryString += " and req_name like '%" + requirementAction.getJobTitle() + "%'";
            }
            if (requirementAction.getReqCreatedBy() != -1 && requirementAction.getReqCreatedBy() != 0) {
                queryString += " and req_created_by = " + requirementAction.getReqCreatedBy() + " ";
            }

            queryString += " order by req_name,FIELD(req_status,'O','R','C','F','I','H','W','S','L'),req_created_date desc LIMIT 100";
            System.out.println("query....." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String status = "";
                if (resultSet.getString("req_status").equalsIgnoreCase("O")) {
                    status = "Opened";
                } else if (resultSet.getString("req_status").equalsIgnoreCase("R")) {
                    status = "Released";
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
//                String str = resultSet.getString("req_skills");
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                // List list = new ArrayList();
//                String reqSkillsResultString = "";
//                while (stk.hasMoreTokens()) {
//                    reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//                }
//                resultString +=
//                        resultSet.getInt("requirement_id") + "|"
//                        + resultSet.getString("req_name") + "|"
//                        + resultSet.getString("no_of_resources") + "|"
//                        + resultSet.getString("req_st_date") + "|"
//                        + status + "|" + resultSet.getString("req_contact1") + "|" + resultSet.getString("req_contact2") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")) + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")) +"|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")) + "^";

                resultString +=
                        resultSet.getInt("requirement_id") + "|"
                        + resultSet.getString("req_name") + "|"
                        + resultSet.getString("no_of_resources") + "|"
                        + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("req_st_date")) + "|"
                        + status + "|" + resultSet.getString("req_contact1") + "|"
                        + resultSet.getString("req_contact2") + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")) + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")) + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")) + "|"
                        + resultSet.getString("req_skills") + "|"
                        + resultSet.getString("preferred_skills") + "|"
                        + resultSet.getString("jdid") + "|"
                        + com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("req_created_by")) + "|"
                        + resultSet.getString("tax_term") + "|"
                        + resultSet.getString("target_rate") + "|"
                        + resultSet.getString("max_rate") + "^";
            }
            // System.out.println("result=========>" + resultString);
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
        return resultString;
    }

    public String getRecruiterOverlay(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "select concat(first_name,'.',last_name) as Name,email1,phone1 from users where usr_id=" + requirementAction.getId();
            System.out.println("query....." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString +=
                        resultSet.getString("Name") + "|"
                        + resultSet.getString("email1") + "|"
                        + resultSet.getString("phone1");

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
        return resultString;

    }

    /**
     * *************************************
     *
     * @getSkillOverlay() getting skills on overlay for requirement List details
     *
     * @Author:jagan<jchukkala@miraclesoft.com>
     *
     * @Created Date:09/june/2015
     *
     *
     **************************************
     *
     */
    public String getSkillOverlay(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT req_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getId();
            System.out.println("query....." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString +=
                        resultSet.getString("req_skills");

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
        return resultString;

    }

    /**
     * *************************************
     *
     * @getPreSkillOverlay() getting prefered skills on overlay for requirement
     * List details
     *
     * @Author:jagan<jchukkala@miraclesoft.com>
     *
     * @Created Date:09/june/2015
     *
     *
     **************************************
     *
     */
    public String getPreSkillOverlay(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT preferred_skills FROM acc_requirements WHERE requirement_id=" + requirementAction.getId();
            System.out.println("query....." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString +=
                        resultSet.getString("preferred_skills");

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
        return resultString;

    }

    /**
     * *************************************
     *
     * @getVendorSearchDetails() getting search vendor details
     *
     * @Author:praveen<pkatru@miraclesoft.com>
     *
     * @Created Date:04/may/2015
     *
     *
     **************************************
     *
     */
    public String storeProofData(HttpServletRequest httpServletRequest, RequirementAction requirementAction) throws ServiceLocatorException {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPL EXECUTED %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String Result = "";
        boolean isExceute = false;
        System.out.println("Skill Id in Impl----->" + requirementAction.getPropsedSkills());
        String str = requirementAction.getPropsedSkills();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String skillsResultString = "";
        while (stk.hasMoreTokens()) {
            skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
            System.out.println("Result String in while===========================>" + skillsResultString);
        }
        System.out.println("ResultString---------->" + skillsResultString);
        requirementAction.setSkillList(StringUtils.chop(skillsResultString));
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL addConsultantProof(?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, requirementAction.getUserSessionId());
            System.out.println("after 1st valueeeeeeeeeeeeeee-->" + requirementAction.getConEmail());
            callableStatement.setInt(2, DataSourceDataProvider.getInstance().getusrIdByemailId(requirementAction.getConEmail()));
            callableStatement.setString(3, requirementAction.getProofType());
            System.out.println(">>>>>>>pValue:::::::>" + requirementAction.getProofValue());
            callableStatement.setString(4, requirementAction.getProofValue());
            callableStatement.setString(5, requirementAction.getReqId());
            callableStatement.setString(6, requirementAction.getRatePerHour());
            // callableStatement.setString(6, requirementAction.getResourceType());
            if (requirementAction.getResourceType().equalsIgnoreCase("VC")) {
                callableStatement.setString(7, "E");
                System.out.println("this is i am in resource type --> PE");
            } else {
                System.out.println("this is i am in resource type --> E");

                callableStatement.setString(7, "C");
            }

            callableStatement.setString(8, requirementAction.getFilesPath());
            callableStatement.setString(9, requirementAction.getFileFileName());
            callableStatement.setString(10, requirementAction.getSkillList());
            callableStatement.setInt(11, Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            callableStatement.registerOutParameter(12, Types.VARCHAR);
            isExceute = callableStatement.execute();
            Result = callableStatement.getString(12);
            if (Result.equalsIgnoreCase("AddSuccess")) {
                System.out.println("****************** in impl result after NotExist:::::::::" + Result);
            } else {
                System.out.println("****************** in impl result after :::::::::" + Result);
            }

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
        return Result;
    }

    /**
     * *************************************
     *
     * @doReleaseRequirements() release reuirements for tier 1
     *
     *
     * @Author:praveen <pkatru@miraclesoft.com>
     *
     * @Created Date:06/02/2015
     *
     **************************************
     */
    public int doReleaseRequirements(RequirementAction requirementAction) throws ServiceLocatorException {
        int count = 0;
        System.out.println("In Impl doReleaseRequirements-->TaxTerm===>" + requirementAction.getTaxTerm());
        System.out.println("In Impl doReleaseRequirements-->reqId===>" + requirementAction.getRequirementId());
        CallableStatement callableStatement = null;
        boolean isExceute = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL spReleaseRequirements(?,?,?,?,?,?)}");

            callableStatement.setInt(1, requirementAction.getSessionOrgId());
            callableStatement.setString(2, requirementAction.getTaxTerm());
            callableStatement.setString(3, requirementAction.getRequirementId());
            callableStatement.setInt(4, requirementAction.getUserSessionId());
            callableStatement.setString(5, requirementAction.getAccount_name());

            callableStatement.registerOutParameter(6, Types.INTEGER);
            isExceute = callableStatement.execute();
            count = callableStatement.getInt(6);
            System.out.println("Count--->" + count);

        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return count;
    }

    /**
     * *************************************
     *
     * @doUpdateStatusReport() update status in requirement table
     *
     *
     * @Author:praveen <pkatru@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public int doUpdateStatusReport(RequirementAction aThis) throws ServiceLocatorException {
        String resultString = "";
        int res = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "update acc_requirements set req_status='R' where requirement_id=" + aThis.getRequirementId();
            System.out.println("query....." + queryString);
            statement = connection.createStatement();
            res = statement.executeUpdate(queryString);
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
        return res;

    }

    public int getOrgIdCustomer(String requirementid) throws ServiceLocatorException {
        int resultString = 0;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT acc_id FROM acc_requirements WHERE requirement_id=" + requirementid;


            System.out.println("query...for..org id...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString = resultSet.getInt("acc_id");
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
        return resultString;
    }

    /**
     * *************************************
     *
     * @getRequirementDashBoardDetails() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String getRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString = "";
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
                        + "AND csr.csr_id=" + requirementAction.getUserSessionId() + " ";
            }


            if (!"".equalsIgnoreCase(requirementAction.getCsrCustomer())) {
                queryString = queryString + " AND acc_id = '" + requirementAction.getCsrCustomer() + "'  ";
            }
            queryString = queryString + "GROUP BY a.account_id";
            System.out.println("query...for..org id...." + queryString);
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
        return resultString;
    }

    /**
     * *************************************
     *
     * @getRequirementDashBoardDetailsOnOverlay() update status in requirement
     * table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String getRequirementDashBoardDetailsOnOverlay(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT MONTHNAME(req_st_date) AS MONTH,"
                    + "COUNT(requirement_id) AS requirements,"
                    + "a.account_name "
                    + "FROM acc_requirements "
                    + "LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                    + "WHERE req_st_date LIKE '%" + requirementAction.getDashYears() + "%' "
                    + "AND a.account_id=" + requirementAction.getAccountId() + " "
                    + "GROUP BY DATE_FORMAT(req_st_date, '%m')";

            System.out.println("query...for..org id...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("MONTH") + "|"
                        + resultSet.getString("requirements") + "^";
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
    public List getDefaultCustomerRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        ArrayList<RequirementVTO> customerDashBoardList = new ArrayList<RequirementVTO>();

        try {
            System.out.println("ENTERED IN TO THE IMPL FOR CUSTOMER DASHBOARD******************************************************");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT MONTHNAME(req_st_date) AS MONTH,"
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
            System.out.println("query...DashBoard....>" + queryString);
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
        return customerDashBoardList;
    }

    /**
     * *************************************
     *
     * @getRequirementDashBoardDetails() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String getCustomerRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT MONTHNAME(req_st_date) AS MONTH,"
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
                    + "FROM acc_requirements LEFT OUTER JOIN accounts a ON(a.account_id=acc_id) "
                    + "WHERE acc_id =" + requirementAction.getSessionOrgId() + " "
                    + "AND DATE_FORMAT(req_st_date,'%Y')='" + requirementAction.getDashYears() + "' ";

            if (!"-1".equalsIgnoreCase(requirementAction.getDashMonths())) {
                queryString = queryString + " AND DATE_FORMAT(req_st_date,'%m')= '" + requirementAction.getDashMonths() + "'  ";
            } else {
                queryString = queryString + " GROUP BY DATE_FORMAT(req_st_date,'%m')";
            }
            System.out.println("query...for..org id...." + queryString);
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
                        + count + "^";

                if (resultSet.getString("MONTH").equalsIgnoreCase("null")) {
                    resultString = "";
                }
            }

            System.out.println("result=========>" + resultString);
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
        return resultString;
    }

    public String getVendorRequirementsDashBoard(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            String queryString = "SELECT ac.account_name,ac.account_id,"
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
            System.out.println("query...for..org id...." + queryString);
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
        return resultString;
    }

    public String getVendorDashBoardDetailsOnOverlay(RequirementAction requirementAction) throws ServiceLocatorException {
        String resultString = "";
        try {
            System.out.println("IN IMPL getVendorDashBoardDetailsOnOverlay()");
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT MONTHNAME(rcr.created_Date) AS MONTH,"
                    + "COUNT(IF(rcr.STATUS='Selected',1, NULL)) 'Won',"
                    + "COUNT(IF(rcr.STATUS<>'Selected',1, NULL)) 'Lost' "
                    + "FROM acc_requirements a "
                    + "LEFT OUTER JOIN req_con_rel rcr ON(a.requirement_id=rcr.reqId) "
                    + "LEFT OUTER JOIN accounts ac ON(ac.account_id=rcr.createdbyOrgId) "
                    + "WHERE rcr.created_Date LIKE '%" + requirementAction.getDashYears() + "%' "
                    + "AND ac.account_id=" + requirementAction.getAccountId() + " "
                    + "GROUP BY DATE_FORMAT(rcr.created_Date, '%m')";

            System.out.println("query...for..org id...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("MONTH") + "|"
                        + resultSet.getString("Won") + "|"
                        + resultSet.getString("Lost") + "^";
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
        return resultString;
    }
}
