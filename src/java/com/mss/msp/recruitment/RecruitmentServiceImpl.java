/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.recruitment;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DataUtility;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;

/**
 * getMyConsultantSearch
 *
 * @author NagireddySeerapu
 */
public class RecruitmentServiceImpl implements RecruitmentService {

    private DataSourceDataProvider dataSourceDataProvider;
    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * ****************************************************************************
     * Date : May 12 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * getMyDefaultConsListDetails method can be used to show default
     * requirement list
     *
     * This method is used for all my,team and services search
     * *****************************************************************************
     */
    public List getMyDefaultConsListDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        ArrayList<ConsultantVTO> conslist = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println("********************RecruitmentServiceImpl :: getMyDefaultConsListDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        try {
            Map map = dataSourceDataProvider.getInstance().getEmpConsultantTeamMap(recruitmentAction.getSessionOrgId());
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
            queryString = "SELECT c.usr_id as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS name,c.phone1,c.email1,cd.usr_id,cd.consultant_skills, cd.rate_salary ,c.cur_status FROM users c LEFT OUTER JOIN"
                    + " usr_details cd ON c.usr_id=cd.usr_id where type_of_user='IC'";
            if ("My".equalsIgnoreCase(recruitmentAction.getConsultantFlag())) {
                queryString = queryString + " and created_by=" + recruitmentAction.getUserSessionId();
            }
            if ("All".equalsIgnoreCase(recruitmentAction.getConsultantFlag()) || "Team".equalsIgnoreCase(recruitmentAction.getConsultantFlag())) {
                queryString = queryString + " and created_by in(" + retrunValue + ")";
            }
            queryString = queryString + " ORDER BY usr_id desc LIMIT 100";
            System.out.println("getMyDefaultConsListDetails::getMyDefaultConsListDetails::queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            String joined = "Name" + "|" + "E-Mail" + "|" + "Skill Set" + "|" + "Rate/Salary" + "|" + "Phone Number" + "|" + "Status" + "^";
            String resultantString = joined;
            while (resultSet.next()) {
                ConsultantVTO cons = new ConsultantVTO();
                cons.setConsult_id(resultSet.getInt("consultant_id"));
                cons.setConsult_email(resultSet.getString("email1"));
                cons.setConsult_name(resultSet.getString("name"));
                cons.setConsult_phno(resultSet.getString("phone1"));
                cons.setConsult_skill(resultSet.getString("consultant_skills"));
                cons.setConsult_salary(resultSet.getString("rate_salary"));
                cons.setConsult_status(resultSet.getString("cur_status"));
                System.out.println("div" + resultSet.getString("phone1") + "  " + resultSet.getString("email1") + "  " + resultSet.getString("name") + "    " + resultSet.getString("consultant_skills"));
                conslist.add(cons);
                joined = cons.getConsult_name() + "|" + cons.getConsult_email() + "|" + cons.getConsult_skill() + "|" + cons.getConsult_salary() + "|" + cons.getConsult_phno() + "|" + cons.getConsult_status() + "^";
                resultantString += joined;
            }
            recruitmentAction.setGridPDFDownload(queryString);
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
        System.out.println("********************RecruitmentServiceImpl :: getMyDefaultConsListDetails Method End*********************");
        return conslist;
    }

    /**
     * ****************************************************************************
     * Date : May 12 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * getConsListDetails method can be used to search required persons
     * requirement list or Searching can be done by either name,phone,email or
     * skills
     *
     * This method is used for all my,team and services search
     * *****************************************************************************
     */
    public List getConsListDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        ArrayList<ConsultantVTO> conslist = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String joined = "Name" + "|" + "E-Mail" + "|" + "Skill Set" + "|" + "Rate/Salary" + "|" + "Phone Number" + "|" + "Status" + "^"; // for grid download
        String resultantString = joined;
        System.out.println("********************RecruitmentServiceImpl :: getConsListDetails Method Start*********************");
        try {
            Map map = dataSourceDataProvider.getInstance().getEmpConsultantTeamMap(recruitmentAction.getSessionOrgId());
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
            queryString = "SELECT DISTINCT c.usr_id as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS name,c.phone1,c.email1,cd.usr_id,cd.consultant_skills, cd.rate_salary,c.cur_status FROM users c LEFT OUTER JOIN"
                    + " usr_details cd ON c.usr_id=cd.usr_id "
                    + "LEFT OUTER JOIN usr_address ca ON c.usr_id=ca.usr_id "
                    + "where type_of_user='IC'AND ca.STATUS LIKE 'Active' ";
            if ("My".equalsIgnoreCase(recruitmentAction.getConsultantFlag())) {
                queryString = queryString + " and c.created_by = '" + recruitmentAction.getUserSessionId() + "'";
            }
            if ("All".equalsIgnoreCase(recruitmentAction.getConsultantFlag()) || "Team".equalsIgnoreCase(recruitmentAction.getConsultantFlag())) {
                if (!"".equalsIgnoreCase(recruitmentAction.getEnameForRecruitment())) {
                    //queryString = queryString + "AND(t.task_created_by="+userLeavesAction.getTeamMember()+" )";
                    queryString = queryString + " and c.created_by =" + recruitmentAction.getEnameIdForRecruitment() + "";
                } else {
                    queryString = queryString + " and c.created_by in(" + retrunValue + ")";
                }
            }
            if (recruitmentAction.getConsult_name() != null && !"".equals(recruitmentAction.getConsult_name())) {
                queryString = queryString + " and (c.first_name like'%" + recruitmentAction.getConsult_name().trim() + "%' or c.middle_name like'%" + recruitmentAction.getConsult_name().trim() + "%' or c.last_name like'%" + recruitmentAction.getConsult_name().trim() + "%') ";
            }
            if (recruitmentAction.getConsult_email() != null && !"".equals(recruitmentAction.getConsult_email())) {
                queryString = queryString + " and c.email1 like'%" + recruitmentAction.getConsult_email().trim() + "%' ";
            }
            if (recruitmentAction.getConsult_phno() != null && !"".equals(recruitmentAction.getConsult_phno())) {
                queryString = queryString + " and c.phone1 like'%" + recruitmentAction.getConsult_phno().trim() + "%' ";
            }
            if (!"-1".equalsIgnoreCase(recruitmentAction.getConsult_status()) && recruitmentAction.getConsult_status() != null) {
                queryString = queryString + " and c.cur_status ='" + recruitmentAction.getConsult_status() + "' ";
            }
            String str = recruitmentAction.getSkillValues();
            if (str != null && !"".equals(recruitmentAction.getSkillValues())) {
                if (!"".equalsIgnoreCase(recruitmentAction.getSkillValues())) {
                    queryString += " and MATCH(consultant_skills) AGAINST ('" + str + "' IN BOOLEAN MODE)";
                }
            }
            if (!"-1".equalsIgnoreCase(recruitmentAction.getConsult_Country()) && recruitmentAction.getConsult_Country() != null) {
                queryString = queryString + " AND ca.country=" + recruitmentAction.getConsult_Country() + " "
                        + "AND (ca.address_flag='PC' OR ca.address_flag='C') ";
            }
            if ((recruitmentAction.getConsult_State() != -1) && (recruitmentAction.getConsult_State() != 0)) {
                queryString = queryString + " AND ca.state=" + recruitmentAction.getConsult_State() + " ";
            }
            if (recruitmentAction.getConsult_City() != null && !"".equals(recruitmentAction.getConsult_City())) {
                queryString = queryString + " AND ca.city LIKE '%" + recruitmentAction.getConsult_City().trim() + "%' ";
            }
            queryString = queryString + " ORDER BY usr_id desc LIMIT 100";
            System.out.println("getConsListDetails::queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ConsultantVTO cons = new ConsultantVTO();
                cons.setConsult_id(resultSet.getInt("consultant_id"));
                cons.setCons_id(resultSet.getInt("consultant_id"));
                cons.setConsult_email(resultSet.getString("email1"));
                cons.setConsult_name(resultSet.getString("name"));
                cons.setConsult_phno(resultSet.getString("phone1"));
                cons.setConsult_skill(resultSet.getString("consultant_skills"));
                cons.setConsult_salary(resultSet.getString("rate_salary"));
                cons.setConsult_status(resultSet.getString("cur_status"));
                conslist.add(cons);
                joined = cons.getConsult_name() + "|" + cons.getConsult_email() + "|" + cons.getConsult_skill() + "|" + cons.getConsult_salary() + "|" + cons.getConsult_phno() + "|" + cons.getConsult_status() + "^";
                resultantString += joined;
            }
            recruitmentAction.setGridPDFDownload(queryString);
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
        System.out.println("********************RecruitmentServiceImpl :: getConsListDetails Method End*********************");
        return conslist;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRequirementDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getRequirementDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        ArrayList<ConsultantVTO> requirementList = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String resultString = "";
        int i = 0;
        System.out.println("********************RecruitmentServiceImpl :: getRequirementDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT requirement_id,req_name,no_of_resources,req_skills,req_st_date,req_status "
                    + "FROM acc_requirements WHERE acc_id=" + recruitmentAction.getAccountSearchID();
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("requirement_id") + " " + resultSet.getString("req_name") + " " + resultSet.getInt("no_of_resources") + " " + resultSet.getString("req_st_date") + " " + resultSet.getString("req_status") + " " + resultSet.getString("req_skills"));
                resultString += resultSet.getInt("requirement_id") + "|" + resultSet.getString("req_name") + "|" + resultSet.getInt("no_of_resources") + "|" + resultSet.getString("req_st_date") + "|" + resultSet.getString("req_status") + "|" + resultSet.getString("req_skills") + "^";
            }
            System.out.println("getRequirementDetails::queryString-->" + requirementList);
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
        System.out.println("********************RecruitmentServiceImpl :: getRequirementDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAllRequirementList() method is used to
     *
     * *****************************************************************************
     */
    public List getAllRequirementList(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) {
        ArrayList<RequirementListVTO> list = new ArrayList<RequirementListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String joined = "";
        String queryString = "";
        String resultantString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        System.out.println("********************RecruitmentServiceImpl :: getAllRequirementList Method Start*********************");
        try {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            if (typeofusr.equalsIgnoreCase("VC")) {
                joined = "Job Id" + "|" + "Jog Title" + "|" + "Customer" + "|" + "Skills Set" + "|" + "Posted Date" + "|" + "Status" + "^"; // for grid download
                resultantString = joined;
                queryString = "SELECT created_date,jdid,requirement_id,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,req_created_by FROM acc_requirements LEFT OUTER JOIN req_ven_rel  ON requirement_id=req_id WHERE ven_id=" + recruitmentAction.getSessionOrgId() + " AND  NOW() >= req_access_time AND  STATUS LIKE 'Active' AND  req_status LIKE 'R' ";
            } else {
                joined = "Job Id" + "|" + "Jog Title" + "|" + "Positions" + "|" + "Skills Set" + "|" + "Posted Date" + "|" + "Status" + "|" + "Noof Submissions" + "^"; // for grid download
                resultantString = joined;
                queryString = "SELECT DISTINCT(requirement_id),req_created_by,created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel  ON requirement_id=req_id "
                        + " WHERE 1=1 AND req_status IN ('O','R','C','OR') and acc_id=" + recruitmentAction.getSessionOrgId();// + "  GROUP BY requirement_id order by req_name,FIELD(req_status,'O','R','C','F','I','H','W','S','L'),req_created_date desc LIMIT 100";

            }
            queryString += " GROUP BY requirement_id  order by jdid desc LIMIT 100";
            System.out.println("getAllRequirementList::queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
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
                } else if (resultSet.getString("req_status").equalsIgnoreCase("OR")) {
                    status = "Open For Resume";
                }
                RequirementListVTO requirementListVTO = new RequirementListVTO();
                requirementListVTO.setJdId(resultSet.getString("jdid"));
                requirementListVTO.setId(resultSet.getInt("requirement_id"));
                requirementListVTO.setTitle(resultSet.getString("req_name"));
                requirementListVTO.setNoOfPosition(resultSet.getString("no_of_resources"));
                requirementListVTO.setPreSkillSet(resultSet.getString("preferred_skills"));
                requirementListVTO.setReqSkillSet(resultSet.getString("req_skills"));
                requirementListVTO.setStartDate(resultSet.getString("req_st_date"));
                requirementListVTO.setStatus(status);
                Date myDate = resultSet.getDate("created_date");
                if (myDate != null) {
                    requirementListVTO.setPostedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(myDate));
                } else {
                    requirementListVTO.setPostedDate("---");
                }
                requirementListVTO.setReq_contact1(resultSet.getString("req_contact1"));
                requirementListVTO.setReq_contact2(resultSet.getString("req_contact2"));
                requirementListVTO.setReqContactName1(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")));
                requirementListVTO.setReqContactName2(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")));
                requirementListVTO.setCreatedByName(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("req_created_by")));
                requirementListVTO.setNoOfSubmissions(com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfSubmisions(resultSet.getInt("requirement_id"), 0));
                list.add(requirementListVTO);
                if (typeofusr.equalsIgnoreCase("VC")) {
                    joined = requirementListVTO.getJdId() + "|" + requirementListVTO.getTitle() + "|" + requirementListVTO.getCustomerName() + "|" + requirementListVTO.getReqSkillSet() + "|" + requirementListVTO.getPostedDate() + "|" + requirementListVTO.getStatus() + "^";
                    resultantString += joined;
                } else {
                    joined = requirementListVTO.getJdId() + "|" + requirementListVTO.getTitle() + "|" + requirementListVTO.getNoOfPosition() + "|" + requirementListVTO.getReqSkillSet() + "|" + requirementListVTO.getPostedDate() + "|" + requirementListVTO.getStatus() + "|" + requirementListVTO.getNoOfSubmissions() + "^";
                    resultantString += joined;
                }
                recruitmentAction.setGridPDFDownload(queryString);
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
        System.out.println("********************RecruitmentServiceImpl :: getAllRequirementList Method End*********************");
        return list;
    }

    /**
     * *****************************************************************************
     * Date : May 5 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : doAddConsultantDetails() method is used to enter the consultant
     * record into database using stored procedure
     *
     * *****************************************************************************
     */
    public int doAddConsultantDetails(RecruitmentAction recruitmentAction, int orgId) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: doAddConsultantDetails Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        Connection connection = null;
        CallableStatement callableStatement = null;
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();
        String availableDate, dobDate;
        String consult_relocation = recruitmentAction.getConsult_relocation();
        String str = recruitmentAction.getSkillValues();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String reqSkillsResultString = "";
        while (stk.hasMoreTokens()) {
            reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
        }
        recruitmentAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));

        try {
            if ("Y".equals(recruitmentAction.getConsult_available())) {
                availableDate = dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_add_date());
            } else {
                availableDate = null;
            }
            dobDate = dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_dob());
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL addConsultant(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("doAddConsultantDetails :: procedure name : addConsultant ");
            callableStatement.setInt(1, orgId);
            callableStatement.setString(2, recruitmentAction.getConsult_email());
            callableStatement.setString(3, availableDate);
            callableStatement.setString(4, recruitmentAction.getConsult_available());
            callableStatement.setString(5, recruitmentAction.getConsult_fstname());
            callableStatement.setString(6, recruitmentAction.getConsult_lstname());
            callableStatement.setString(7, recruitmentAction.getConsult_midname());
            callableStatement.setString(8, dobDate);
            callableStatement.setString(9, recruitmentAction.getConsult_homePhone());
            callableStatement.setString(10, recruitmentAction.getConsult_mobileNo());
            callableStatement.setInt(11, recruitmentAction.getConsult_lcountry());
            callableStatement.setString(12, recruitmentAction.getConsult_Address());
            callableStatement.setString(13, recruitmentAction.getConsult_Address2());
            callableStatement.setString(14, recruitmentAction.getConsult_City());
            callableStatement.setString(15, recruitmentAction.getConsult_Country());
            callableStatement.setInt(16, recruitmentAction.getConsult_State());
            callableStatement.setString(17, recruitmentAction.getConsult_Zip());
            callableStatement.setString(18, recruitmentAction.getAddress_flag());
            callableStatement.setString(19, recruitmentAction.getConsult_CAddress());
            callableStatement.setString(20, recruitmentAction.getConsult_CAddress2());
            callableStatement.setString(21, recruitmentAction.getConsult_CCity());
            callableStatement.setString(22, recruitmentAction.getConsult_CCountry());
            callableStatement.setInt(23, recruitmentAction.getConsult_CState());
            callableStatement.setString(24, recruitmentAction.getConsult_CZip());
            callableStatement.setInt(25, recruitmentAction.getConsult_industry());
            callableStatement.setString(26, recruitmentAction.getConsult_jobTitle());
            callableStatement.setString(27, recruitmentAction.getConsult_salary());
            callableStatement.setInt(28, recruitmentAction.getConsult_experience());
            callableStatement.setString(29, recruitmentAction.getConsult_workPhone());
            callableStatement.setInt(30, recruitmentAction.getConsult_pcountry());
            callableStatement.setString(31, recruitmentAction.getPrefstateValues());
            callableStatement.setInt(32, recruitmentAction.getConsult_wcountry());
            callableStatement.setString(33, recruitmentAction.getConsult_education());
            callableStatement.setString(34, recruitmentAction.getConsult_comments());
            callableStatement.setString(35, recruitmentAction.getConsult_referredBy());
            callableStatement.setString(36, recruitmentAction.getFileFileName());
            callableStatement.setString(37, recruitmentAction.getFilesPath());
            callableStatement.setInt(38, recruitmentAction.getUserSessionId());
            callableStatement.setString(39, recruitmentAction.getReqSkillSet());
            callableStatement.setString(40, recruitmentAction.getConsult_alterEmail());
            callableStatement.setString(41, com.mss.msp.util.DataUtility.encrypted(recruitmentAction.getConsult_ssnNo()));
            callableStatement.setString(42, recruitmentAction.getConsult_linkedInId());
            callableStatement.setString(43, recruitmentAction.getConsult_facebookId());
            callableStatement.setString(44, recruitmentAction.getConsult_twitterId());
            if (consult_relocation.equalsIgnoreCase("yes")) {
                callableStatement.setString(45, consult_relocation);
            } else {
                callableStatement.setString(45, consult_relocation);
            }
            callableStatement.setString(46, recruitmentAction.getConsultantVisa());
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(47);
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
        System.out.println("********************RecruitmentServiceImpl :: doAddConsultantDetails Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getupdateConsultantDetails() method is used to
     *
     * *****************************************************************************
     */
    public ConsultantVTO getupdateConsultantDetails(RecruitmentAction recruitmentAction, Map map) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: getupdateConsultantDetails Method Start*********************");
        ConsultantVTO consult = new ConsultantVTO();
        DateUtility dateUtility = new DateUtility();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT c.usr_id AS usr_consultant_id,c.job_title,c.usr_industry,c.preffered_country, "
                    + "c.preffered_state,c.referred_by,c.experience,c.rate_salary,c.available_from, "
                    + "c.available,c.education,c.relocation,c.comments,c.ssn_number,u.email1,u.email2, "
                    + "u.first_name,u.last_name,u.middle_name,u.dob,u.created_by_org_id,u.living_country, "
                    + "u.working_country,u.phone2,u.phone1,u.cur_status ,u.linkedin_link, u.facebook_link, "
                    + "u.twitter_link, c.consultant_skills,a.address,a.city,a.state,a.zip,a.country, "
                    + "a.phone,a.address2,a.address_flag,a.STATUS,ar.object_id, ar.acc_attachment_id, ar.object_type, ar.attachment_path, ar.attachment_name,u.type_of_user,c.visa,c.idprooftype,c.idproofattachment FROM users u JOIN usr_details c ON (u.usr_id = c.usr_id) "
                    + "JOIN usr_address a ON (u.usr_id = a.usr_id) JOIN acc_rec_attachment ar ON(ar.object_id=u.usr_id) WHERE u.usr_id = ? AND a.STATUS='ACTIVE' AND ar.STATUS='Active' ";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, recruitmentAction.getConsult_id());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                consult.setVendorcomments(recruitmentAction.getVendorcomments());
                consult.setConsult_id(resultSet.getInt("usr_consultant_id"));
                consult.setConsult_email(resultSet.getString("email1"));
                consult.setConsult_fstname(resultSet.getString("first_name"));
                consult.setConsult_lstname(resultSet.getString("last_name"));
                consult.setConsult_midname(resultSet.getString("middle_name"));
                if (resultSet.getString("dob") == null) {
                    consult.setConsult_dob("");
                } else {
                    consult.setConsult_dob(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("dob")));
                }
                consult.setConsult_lcountry(resultSet.getString("living_country"));
                consult.setConsult_wcountry(resultSet.getInt("working_country"));
                consult.setConsult_homePhone(resultSet.getString("phone2"));
                consult.setConsult_mobileNo(resultSet.getString("phone1"));
                consult.setConsult_linkedInId(resultSet.getString("linkedin_link"));
                consult.setConsult_facebookId(resultSet.getString("facebook_link"));
                consult.setConsult_twitterId(resultSet.getString("twitter_link"));
                consult.setConsultantVisa(resultSet.getString("visa"));
                consult.setConsultantIdProof(resultSet.getString("idprooftype"));
                consult.setConsultantIdProofAttach(resultSet.getString("idproofattachment"));
                consult.setConsult_status(resultSet.getString("cur_status"));
                consult.setConsult_jobTitle(resultSet.getString("job_title"));
                consult.setConsult_industry(resultSet.getInt("usr_industry"));
                consult.setConsult_preferredCountry(resultSet.getInt("preffered_country"));
                consult.setConsult_acc_attachment_id(resultSet.getString("acc_attachment_id"));
                consult.setConsult_object_id(resultSet.getInt("object_id"));
                consult.setConsult_attachment_name(resultSet.getString("attachment_name"));
                consult.setConsult_object_type(resultSet.getString("object_type"));
                consult.setConsult_attachment_path(resultSet.getString("attachment_path"));
                if (resultSet.getString("preffered_state") != null) {
                    String str = resultSet.getString("preffered_state");
                    StringTokenizer stk = new StringTokenizer(str, ",");
                    List list = new ArrayList();
                    while (stk.hasMoreTokens()) {
                        list.add(stk.nextToken());
                    }
                    consult.setConsult_preferredState(list);
                }
                consult.setConsult_experience(resultSet.getInt("experience"));
                consult.setConsult_organization(resultSet.getInt("created_by_org_id"));
                consult.setConsult_referredBy(resultSet.getString("referred_by"));
                consult.setConsult_salary(resultSet.getString("rate_salary"));
                if (resultSet.getString("available_from") == null) {
                    consult.setConsult_favail("");
                } else {
                    consult.setConsult_favail(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("available_from")));
                }
                consult.setConsult_available(resultSet.getString("available"));
                consult.setConsult_education(resultSet.getString("education"));
                consult.setConsult_relocation(resultSet.getString("relocation"));
                consult.setConsult_skill(resultSet.getString("consultant_skills"));
                consult.setConsult_comments(resultSet.getString("comments"));
                String str1 = resultSet.getString("consultant_skills");
                StringTokenizer stk1 = new StringTokenizer(str1, ",");
                String reqSkillsResultString = "";
                List list1 = new ArrayList();
                while (stk1.hasMoreTokens()) {
                    list1.add(getKeyFromValue(map, stk1.nextToken()));
                }
                System.out.println("getupdateConsultantDetails::reqSkillsResultString----------->" + reqSkillsResultString);
                consult.setSkillSetList(list1);
                consult.setConsult_alterEmail(resultSet.getString("email2"));
                if (resultSet.getString("ssn_number") != null) {
                    consult.setConsult_ssnNo(com.mss.msp.util.DataUtility.decrypted(resultSet.getString("ssn_number")));
                }
                if ("PC".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    consult.setConsult_Address(resultSet.getString("address"));
                    consult.setConsult_City(resultSet.getString("city"));
                    consult.setConsult_State(resultSet.getInt("state"));
                    consult.setConsult_Zip(resultSet.getString("zip"));
                    consult.setConsult_Country(resultSet.getInt("country"));
                    consult.setConsult_Phone(resultSet.getString("phone"));
                    consult.setConsult_Address2(resultSet.getString("address2"));
                    consult.setAddress_flag("true");
                    consult.setConsult_CAddress(resultSet.getString("address"));
                    consult.setConsult_CCity(resultSet.getString("city"));
                    consult.setConsult_CState(resultSet.getInt("state"));
                    consult.setConsult_CZip(resultSet.getString("zip"));
                    consult.setConsult_CCountry(resultSet.getInt("country"));
                    consult.setConsult_CAddress2(resultSet.getString("address2"));
                }
                if ("C".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    consult.setConsult_CAddress(resultSet.getString("address"));
                    consult.setConsult_CCity(resultSet.getString("city"));
                    consult.setConsult_CState(resultSet.getInt("state"));
                    consult.setConsult_CZip(resultSet.getString("zip"));
                    consult.setConsult_CCountry(resultSet.getInt("country"));
                    consult.setConsult_CAddress2(resultSet.getString("address2"));
                    consult.setAddress_flag("false");
                }
                if ("P".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    consult.setConsult_Address(resultSet.getString("address"));
                    consult.setConsult_City(resultSet.getString("city"));
                    consult.setConsult_State(resultSet.getInt("state"));
                    consult.setConsult_Zip(resultSet.getString("zip"));
                    consult.setConsult_Country(resultSet.getInt("country"));
                    consult.setConsult_Address2(resultSet.getString("address2"));
                    consult.setAddress_flag("false");
                }
                if ("VC".equals(resultSet.getString("type_of_user"))) {
                    consult.setConsult_Address(resultSet.getString("address"));
                    consult.setConsult_City(resultSet.getString("city"));
                    consult.setConsult_State(resultSet.getInt("state"));
                    consult.setConsult_Zip(resultSet.getString("zip"));
                    consult.setConsult_Country(resultSet.getInt("country"));
                    consult.setConsult_Address2(resultSet.getString("address2"));
                    consult.setAddress_flag("emp");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        System.out.println("********************RecruitmentServiceImpl :: getupdateConsultantDetails Method End*********************");
        return consult;
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
     * ForUse : doupdateConsultantDetails() method is used to
     *
     * *****************************************************************************
     */
    public ConsultantVTO doupdateConsultantDetails(RecruitmentAction recruitmentAction, int userSessionId, int orgid) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: doupdateConsultantDetails Method Start*********************");
        DateUtility dateUtility = new DateUtility();
        ConsultantVTO consult = new ConsultantVTO();
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean isExceute = false;
        int updatedRows = 0;
        String consult_relocation = recruitmentAction.getConsult_relocation();
        String str = recruitmentAction.getSkillValues();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String reqSkillsResultString = "";
        while (stk.hasMoreTokens()) {
            reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
        }
        recruitmentAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL updateConsultantDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("doupdateConsultantDetails :: procedure name : updateConsultantDetails ");
            callableStatement.setString(1, recruitmentAction.getConsult_email());
            callableStatement.setString(2, recruitmentAction.getConsult_fstname());
            callableStatement.setString(3, recruitmentAction.getConsult_lstname());
            callableStatement.setString(4, recruitmentAction.getConsult_midname());
            callableStatement.setString(5, dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_dob()));
            callableStatement.setString(6, recruitmentAction.getConsult_mobileNo());
            callableStatement.setInt(7, recruitmentAction.getConsult_lcountry());
            callableStatement.setInt(8, recruitmentAction.getConsult_wcountry());
            callableStatement.setInt(9, orgid);
            callableStatement.setString(10, recruitmentAction.getConsult_homePhone());
            callableStatement.setString(11, recruitmentAction.getConsult_checkAddress());
            callableStatement.setString(12, recruitmentAction.getConsult_Address());
            callableStatement.setString(13, recruitmentAction.getConsult_Address2());
            callableStatement.setString(14, recruitmentAction.getConsult_City());
            callableStatement.setString(15, recruitmentAction.getConsult_Country());
            callableStatement.setInt(16, recruitmentAction.getConsult_State());
            callableStatement.setString(17, recruitmentAction.getConsult_Zip());
            callableStatement.setString(18, recruitmentAction.getConsult_CAddress());
            callableStatement.setString(19, recruitmentAction.getConsult_CAddress2());
            callableStatement.setString(20, recruitmentAction.getConsult_CCity());
            callableStatement.setString(21, recruitmentAction.getConsult_CCountry());
            callableStatement.setInt(22, recruitmentAction.getConsult_CState());
            callableStatement.setString(23, recruitmentAction.getConsult_CZip());
            if ("Y".equals(recruitmentAction.getConsult_available())) {
                callableStatement.setString(24, dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_favail()));
            } else {
                callableStatement.setString(24, null);
            }
            callableStatement.setString(25, recruitmentAction.getConsult_available());
            callableStatement.setString(26, recruitmentAction.getConsult_education());
            callableStatement.setInt(27, recruitmentAction.getConsult_industry());
            callableStatement.setString(28, recruitmentAction.getConsult_salary());
            callableStatement.setInt(29, recruitmentAction.getConsult_experience());
            callableStatement.setString(30, recruitmentAction.getPrefstateValues());
            callableStatement.setString(31, recruitmentAction.getConsult_preferredCountry());
            callableStatement.setString(32, recruitmentAction.getConsult_jobTitle());
            callableStatement.setString(33, recruitmentAction.getConsult_comments());
            callableStatement.setInt(34, recruitmentAction.getConsult_id());
            callableStatement.setInt(35, userSessionId);
            callableStatement.setString(36, recruitmentAction.getReqSkillSet());
            callableStatement.setString(37, recruitmentAction.getConsult_status());
            callableStatement.setString(38, recruitmentAction.getConsult_alterEmail());
            callableStatement.setString(39, com.mss.msp.util.DataUtility.encrypted(recruitmentAction.getConsult_ssnNo()));
            callableStatement.setString(40, recruitmentAction.getConsult_referredBy());
            callableStatement.setString(41, recruitmentAction.getConsult_linkedInId());
            callableStatement.setString(42, recruitmentAction.getConsult_facebookId());
            callableStatement.setString(43, recruitmentAction.getConsult_twitterId());
            if (consult_relocation.equalsIgnoreCase("yes")) {
                callableStatement.setString(44, consult_relocation);
            } else {
                callableStatement.setString(44, consult_relocation);
            }
            callableStatement.setString(45, recruitmentAction.getConsultantVisa());
            callableStatement.setString(46, recruitmentAction.getConsultantIdProof());
            callableStatement.registerOutParameter(47, Types.INTEGER);
            isExceute = callableStatement.execute();
            updatedRows = callableStatement.getInt(47);
            if (updatedRows > 0) {
                recruitmentAction.setUpdateMessage("success");
            } else {
                recruitmentAction.setUpdateMessage("failure");
            }
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************RecruitmentServiceImpl :: doupdateConsultantDetails Method End*********************");
        return consult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getConsultantListDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getConsultantListDetails(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: getConsultantListDetails Method Start*********************");
        String resultString = "";
        String decryptedSSN = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            if (typeofusr.equalsIgnoreCase("VC")) {
                queryString = "SELECT Distinct(c.usr_id) AS consultant_id,"
                        + "CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME,c.phone1,aat.acc_attachment_id,"
                        + "c.email1,rc.STATUS,rc.con_skill AS consultant_skills,rc.createdbyOrgId as created_by_org_id,"
                        + "rc.rate_salary,r.acc_id,rc.created_Date,rc.Comments,rc.customercomments,ud.ssn_number,rc.vendorcomments "
                        + " FROM req_con_rel rc "
                        + "LEFT OUTER JOIN users c ON (rc.consultantId=c.usr_id) "
                        + "LEFT OUTER JOIN usr_details ud ON (ud.usr_id=c.usr_id)  "
                        + "LEFT OUTER JOIN acc_requirements r ON (r.requirement_id=rc.reqId) "
                        + "LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=aat.object_id) ";
                queryString = queryString + " WHERE rc.reqId=" + recruitmentAction.getRequirementId() + " AND rc.createdbyOrgId=" + recruitmentAction.getSessionOrgId() + " AND  aat.STATUS LIKE 'Active' AND (aat.object_type='E' OR aat.object_type='CSA' AND aat.req_id = " + recruitmentAction.getRequirementId() + ") LIMIT 100";
            } else {
                queryString = "SELECT Distinct(c.usr_id) AS consultant_id,"
                        + "CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME,c.phone1,aat.acc_attachment_id,"
                        + "c.email1,rc.STATUS,rc.con_skill AS consultant_skills,rc.createdbyOrgId as created_by_org_id,"
                        + "rc.rate_salary,r.acc_id,rc.created_Date,rc.Comments,rc.customercomments,ud.ssn_number,rc.vendorcomments  "
                        + " FROM req_con_rel rc "
                        + "LEFT OUTER JOIN users c ON (rc.consultantId=c.usr_id) "
                        + "LEFT OUTER JOIN usr_details ud ON (ud.usr_id=c.usr_id)  "
                        + "LEFT OUTER JOIN acc_requirements r ON (r.requirement_id=rc.reqId) "
                        + "LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=aat.object_id) ";
                queryString = queryString + " WHERE  rc.reqId=" + recruitmentAction.getRequirementId() + "  AND  aat.STATUS LIKE 'Active' AND (aat.object_type='E' OR (aat.object_type='CSA' AND aat.req_id = " + recruitmentAction.getRequirementId() + ")) LIMIT 100";
            }
            System.out.println("getConsultantListDetails::query=========by nag=======" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                String postedDate = "";
                Date myDate = resultSet.getDate("rc.created_Date");
                if (myDate != null) {
                    postedDate = com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(myDate);
                } else {
                    postedDate = "---";
                }
                if (resultSet.getString("ssn_number") != null && !"".equalsIgnoreCase(resultSet.getString("ssn_number"))) {
                    decryptedSSN = com.mss.msp.util.DataUtility.decrypted(resultSet.getString("ssn_number"));
                }
                resultString += resultSet.getString("consultant_id") + "|" + resultSet.getString("name") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("consultant_skills") + "|" + resultSet.getString("phone1") + "|" + resultSet.getString("status") + "|" + resultSet.getString("acc_attachment_id") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")) + "|" + resultSet.getString("rate_salary") + "|" + resultSet.getInt("created_by_org_id") + "|" + resultSet.getInt("acc_id") + "|" + postedDate + "|" + resultSet.getString("Comments") + "|" + resultSet.getString("customercomments") + "|" + decryptedSSN + "|" + resultSet.getString("rc.vendorcomments") + "|" + queryString + "^";
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
        System.out.println("********************RecruitmentServiceImpl :: getConsultantListDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : searchConsultantListDetails() getting consultant list by
     * searching.
     *
     *
     * *****************************************************************************
     */
    public String searchConsultantListDetails(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: searchConsultantListDetails Method Start*********************");
        String resultString = "";
        String queryString = "";
        String decryptedSSN = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            if (typeofusr.equalsIgnoreCase("VC")) {
                queryString = " SELECT Distinct(c.usr_id) as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
                        + "c.phone1,acc_attachment_id,c.email1,rc.status,rc.con_skill AS consultant_skills,rc.createdbyOrgId AS created_by_org_id,rc.rate_salary,rc.created_Date,"
                        + " rc.Comments,ar.acc_id,rc.customercomments,cd.ssn_number,rc.vendorcomments  FROM users c LEFT OUTER JOIN "
                        + "usr_details cd ON (c.usr_id=cd.usr_id) LEFT OUTER JOIN req_con_rel rc"
                        + " ON (c.usr_id=rc.consultantId) LEFT OUTER JOIN accounts a ON(a.account_id=rc.createdbyOrgId)"
                        + " LEFT OUTER JOIN acc_requirements ar "
                        + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "' AND aat.STATUS='active' AND (aat.object_type='E' OR (aat.object_type='CSA' AND aat.req_id ='" + recruitmentAction.getRequirementId() + "'))";
                if (recruitmentAction.getVen_id() == 0) {
                    queryString = queryString + " AND rc.createdbyOrgId=" + recruitmentAction.getSessionOrgId() + " ";
                } else {
                    queryString = queryString + " AND rc.createdbyOrgId=" + recruitmentAction.getVen_id() + " ";
                }
            } else {
                queryString = " SELECT Distinct(c.usr_id) as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
                        + "c.phone1,acc_attachment_id,c.email1,rc.status,rc.con_skill AS consultant_skills,rc.createdbyOrgId AS created_by_org_id,rc.rate_salary,rc.created_Date,"
                        + " rc.Comments,ar.acc_id,rc.customercomments,cd.ssn_number,rc.vendorcomments  FROM users c LEFT OUTER JOIN "
                        + "usr_details cd ON (c.usr_id=cd.usr_id) LEFT OUTER JOIN req_con_rel rc"
                        + " ON (c.usr_id=rc.consultantId) LEFT OUTER JOIN accounts a ON(a.account_id=rc.createdbyOrgId)"
                        + " LEFT OUTER JOIN acc_requirements ar "
                        + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "' AND aat.STATUS='active' AND (aat.object_type='E' OR (aat.object_type='CSA' AND aat.req_id ='" + recruitmentAction.getRequirementId() + "'))";
            }
            if (recruitmentAction.getConsult_name() != null && !"".equals(recruitmentAction.getConsult_name())) {
                queryString = queryString + " and c.first_name like'%" + recruitmentAction.getConsult_name().trim() + "%' ";
            }
            if (recruitmentAction.getConsult_lstname() != null && !"".equals(recruitmentAction.getConsult_lstname())) {
                queryString = queryString + " and c.last_name like'%" + recruitmentAction.getConsult_lstname().trim() + "%' ";
            }
            if (recruitmentAction.getConsult_email() != null && !"".equals(recruitmentAction.getConsult_email())) {
                queryString = queryString + " and c.email1 like'%" + recruitmentAction.getConsult_email().trim() + "%' ";
            }

            if (recruitmentAction.getVendor() != null && !"".equals(recruitmentAction.getVendor())) {
                queryString = queryString + " AND a.account_name LIKE '%" + recruitmentAction.getVendor().trim() + "%' ";
            }
            if (recruitmentAction.getConsult_phno() != null && !"".equals(recruitmentAction.getConsult_phno())) {
                queryString = queryString + " and c.phone1 like'%" + recruitmentAction.getConsult_phno().trim() + "%' ";

            }
            if (recruitmentAction.getConsult_skill() != null && !"".equals(recruitmentAction.getConsult_skill())) {
                queryString = queryString + " and rc.con_skill like'%" + recruitmentAction.getConsult_skill().trim() + "%' ";

            }
            if (recruitmentAction.getConsult_ssnNo() != null && !"".equals(recruitmentAction.getConsult_ssnNo())) {
                queryString = queryString + " and cd.ssn_number ='" + com.mss.msp.util.DataUtility.encrypted(recruitmentAction.getConsult_ssnNo().trim()) + "' ";
            }
            queryString = queryString + " LIMIT 100";
            System.out.println("searchConsultantListDetails::query================" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if (resultSet.getString("ssn_number") != null && !"".equalsIgnoreCase(resultSet.getString("ssn_number"))) {
                    decryptedSSN = com.mss.msp.util.DataUtility.decrypted(resultSet.getString("ssn_number"));
                }
                resultString += resultSet.getString("consultant_id") + "|" + resultSet.getString("name") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("consultant_skills") + "|" + resultSet.getString("phone1") + "|" + resultSet.getString("status") + "|" + resultSet.getString("acc_attachment_id") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")) + "|" + resultSet.getString("rate_salary") + "|" + resultSet.getInt("created_by_org_id") + "|" + resultSet.getInt("acc_id") + "|" + com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(resultSet.getDate("created_date")) + "|" + resultSet.getString("Comments") + "|" + resultSet.getString("customercomments") + "|" + decryptedSSN + "|" + resultSet.getString("vendorcomments") + "|" + queryString + "^";
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
        System.out.println("********************RecruitmentServiceImpl :: searchConsultantListDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addConsultAttachmentDetails() method is used to
     *
     * *****************************************************************************
     */
    public int addConsultAttachmentDetails(RecruitmentAction recruitmentaction) {
        System.out.println("********************RecruitmentServiceImpl :: addConsultAttachmentDetails Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        int addResult = 0;
        boolean isExceute = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String fpath = recruitmentaction.getFilePath();
            recruitmentaction.setFilePath(fpath);
            callableStatement = connection.prepareCall("{CALL addConsultantAttachment(?,?,?,?,?,?)}");
            System.out.println("addConsultAttachmentDetails :: procedure name : addConsultantAttachment ");
            callableStatement.setInt(1, recruitmentaction.getConsult_id());
            callableStatement.setString(2, "CV");
            callableStatement.setString(3, recruitmentaction.getConsultAttachmentFileName());
            callableStatement.setString(4, recruitmentaction.getFilePath());
            callableStatement.setInt(5, recruitmentaction.getUserSessionId());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(6);
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
        System.out.println("********************RecruitmentServiceImpl :: addConsultAttachmentDetails Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getActivityDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getActivityDetails(int consultantId) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: getActivityDetails Method Start*********************");
        ArrayList<UserActivity> activityList = new ArrayList<UserActivity>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int i = 0;
        try {
            queryString = " SELECT actvity_id,object_id,object_type,activity_name,actvity_type,activity_relation,activity_created_date,"
                    + "activity_created_by,activity_desc,activity_comments,activity_status FROM acc_rec_activities WHERE activity_relation='acc' and object_id= " + consultantId;
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserActivity userActivity = new UserActivity();
                userActivity.setActivityId(resultSet.getInt("actvity_id"));
                userActivity.setObjectID(resultSet.getInt("object_id"));
                userActivity.setObjectType(resultSet.getString("object_type"));
                userActivity.setActivityName(resultSet.getString("activity_name"));
                userActivity.setActivityType(resultSet.getString("actvity_type"));
                userActivity.setActivityRelation(resultSet.getString("activity_relation"));
                userActivity.setActivityCratedBy(resultSet.getString("activity_created_by"));
                userActivity.setActivityDesc(resultSet.getString("activity_desc"));
                userActivity.setActivityComments(resultSet.getString("activity_comments"));
                userActivity.setActivityStatus(resultSet.getString("activity_status"));
                resultString += userActivity.getActivityId() + "|" + userActivity.getActivityName() + "|"
                        + userActivity.getObjectID() + "|" + userActivity.getObjectType() + "|"
                        + userActivity.getActivityType() + '|' + userActivity.getActivityRelation() + '|'
                        + dsdp.getFnameandLnamebyUserid(Integer.parseInt(userActivity.getActivityCratedBy())) + '|' + userActivity.getActivityDesc() + '|'
                        + userActivity.getActivityComments() + '|' + userActivity.getActivityStatus() + '^';
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
        System.out.println("********************RecruitmentServiceImpl :: getActivityDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doAddConsultantActivityDetails() method is used to
     *
     * *****************************************************************************
     */
    public int doAddConsultantActivityDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: doAddConsultantActivityDetails Method Start*********************");
        String queryString = "";
        int resultInt = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int i = 0;
            queryString = "insert into acc_rec_activities(object_id,object_type,activity_name,actvity_type,activity_relation,activity_created_by,activity_desc,activity_comments,activity_status) values (?,?,?,?,?,?,?,?,?)";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, recruitmentAction.getConsult_id());
            preparedStatement.setString(2, recruitmentAction.getActivityRelation());
            preparedStatement.setString(3, recruitmentAction.getActivityName());
            preparedStatement.setString(4, recruitmentAction.getActivityType());
            preparedStatement.setString(5, recruitmentAction.getActivityRelation());
            preparedStatement.setInt(6, recruitmentAction.getActivityCratedBy());
            preparedStatement.setString(7, recruitmentAction.getActivityDesc());
            preparedStatement.setString(8, recruitmentAction.getActivityComments());
            preparedStatement.setString(9, recruitmentAction.getActivityStatus());
            resultInt = preparedStatement.executeUpdate();
            System.out.println("doAddConsultantActivityDetails::resultInt---->" + resultInt);
        } catch (Exception ex) {
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
        System.out.println("********************RecruitmentServiceImpl :: doAddConsultantActivityDetails Method End*********************");
        return resultInt;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doEditConsultantActivityDetails() method is used to
     *
     * *****************************************************************************
     */
    public int doEditConsultantActivityDetails(RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: doEditConsultantActivityDetails Method Start*********************");
        ArrayList consultActivitylist = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        int resultInt = 0;
        int i = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE acc_rec_activities SET activity_name = ?,actvity_type = ?,"
                    + "activity_relation = ?, activity_created_by = ?, activity_desc = ?, "
                    + "activity_comments = ? ,activity_status = ?, modified_by=?, modified_date=?  WHERE actvity_id = ? ";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, recruitmentAction.getActivityName());
            preparedStatement.setString(2, recruitmentAction.getActivityType());
            preparedStatement.setString(3, recruitmentAction.getActivityRelation());
            preparedStatement.setInt(4, recruitmentAction.getActivityCratedBy());
            preparedStatement.setString(5, recruitmentAction.getActivityDesc());
            preparedStatement.setString(6, recruitmentAction.getActivityComments());
            preparedStatement.setString(7, recruitmentAction.getActivityStatus());
            preparedStatement.setInt(8, recruitmentAction.getUserSessionId());
            preparedStatement.setTimestamp(9, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(10, recruitmentAction.getActivityId());
            resultInt = preparedStatement.executeUpdate();
            System.out.println("doEditConsultantActivityDetails::resultInt---->" + resultInt);
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
        System.out.println("********************RecruitmentServiceImpl :: doEditConsultantActivityDetails Method End*********************");
        return resultInt;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getConsultantActivityDetailsbyActivityId() method is used to
     *
     * *****************************************************************************
     */
    public String getConsultantActivityDetailsbyActivityId(RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getConsultantActivityDetailsbyActivityId Method Start*********************");
        ArrayList consultActivitylist = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        String resultMsg = "";
        int i = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            queryString = "SELECT actvity_id,activity_name,actvity_type,activity_created_date,activity_desc,activity_comments,activity_status,activity_relation FROM acc_rec_activities where actvity_id=" + recruitmentAction.getActivityId();
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultMsg += resultSet.getInt("actvity_id") + "|" + resultSet.getString("activity_name") + "|"
                        + resultSet.getString("actvity_type") + "|" + resultSet.getString("activity_created_date") + "|"
                        + resultSet.getString("activity_desc") + "|" + resultSet.getString("activity_comments") + "|"
                        + resultSet.getString("activity_status") + "|" + resultSet.getString("activity_relation");
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
        System.out.println("********************RecruitmentServiceImpl :: getConsultantActivityDetailsbyActivityId Method End*********************");
        return resultMsg;
    }

    /**
     * *************************************
     *
     * @getLoginUserRequirementList() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/07/2015
     *
     **************************************
     */
    public List getLoginUserRequirementList(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getLoginUserRequirementList Method Start*********************");
        ArrayList<RequirementListVTO> list = new ArrayList<RequirementListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        String joined = "";
        String resultantString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            int sessionusrPrimaryrole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
            if (typeofusr.equalsIgnoreCase("VC")) {
                joined = "Job Id" + "|" + "Jog Title" + "|" + "Customer" + "|" + "Skills Set" + "|" + "Posted Date" + "|" + "Status" + "^"; // for grid download
                resultantString = joined;
                queryString = "SELECT account_name,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,requirement_id,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate"
                        + " FROM acc_requirements LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                        + "WHERE ven_id=" + recruitmentAction.getSessionOrgId() + " AND  NOW() >= req_access_time AND  r.STATUS LIKE 'Active' "
                        + "AND  (req_status = 'R' OR req_status = 'OR')";
                if (sessionusrPrimaryrole != 3 && sessionusrPrimaryrole != 2 && sessionusrPrimaryrole != 13 && sessionusrPrimaryrole != 1) {
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST) != null) {
                        queryString += " and req_category IN (" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST).toString() + ") ";
                    }
                }
                queryString = queryString + " order by jdid desc LIMIT 100";
            } else {
                joined = "Job Id" + "|" + "Jog Title" + "|" + "Positions" + "|" + "Skills Set" + "|" + "Posted Date" + "|" + "Status" + "|" + "Noof Submissions" + "^"; // for grid download
                resultantString = joined;
                if (recruitmentAction.getTypeOfUser() == 3) {
                    queryString = "SELECT DISTINCT(requirement_id),account_name,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                            + " WHERE 1=1 AND  req_created_by=" + recruitmentAction.getUserSessionId() + " and acc_id=" + recruitmentAction.getSessionOrgId() + "  GROUP BY requirement_id order by jdid desc LIMIT 100";
                } else if (recruitmentAction.getTypeOfUser() == 13 || recruitmentAction.getTypeOfUser() == 2) {
                    queryString = "SELECT DISTINCT(requirement_id),account_name,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel r  ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                            + " WHERE 1=1 AND acc_id=" + recruitmentAction.getSessionOrgId() + "  GROUP BY requirement_id order by jdid desc LIMIT 100";
                } else {
                    queryString = "SELECT DISTINCT(requirement_id),account_name,r.created_date,req_created_date,tax_term,req_type,target_rate,jdid,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id LEFT OUTER JOIN accounts a ON account_id= created_by_org_id "
                            + " WHERE 1=1 "
                            + "and acc_id=" + recruitmentAction.getSessionOrgId();
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST) != null) {
                        queryString += " and req_category IN (" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST).toString() + ") ";
                    }
                    queryString = queryString + " GROUP BY requirement_id order by jdid desc LIMIT 100";
                }
            }
            System.out.println("getLoginUserRequirementList::queryString     11-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
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
                RequirementListVTO requirementListVTO = new RequirementListVTO();
                requirementListVTO.setJdId(resultSet.getString("jdid"));
                requirementListVTO.setId(resultSet.getInt("requirement_id"));
                requirementListVTO.setTitle(resultSet.getString("req_name"));
                requirementListVTO.setNoOfPosition(resultSet.getString("no_of_resources"));
                Date myDate = resultSet.getDate("created_date");
                if (myDate != null) {
                    requirementListVTO.setPostedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashFormat(myDate));
                } else {
                    requirementListVTO.setPostedDate("---");
                }
                String preSkills = resultSet.getString("preferred_skills");
                requirementListVTO.setReqSkillSet(resultSet.getString("req_skills"));
                requirementListVTO.setPreSkillSet(preSkills.replaceAll("<br/>", ","));
                requirementListVTO.setStartDate(resultSet.getString("req_st_date"));
                requirementListVTO.setTargetRate(resultSet.getString("target_rate"));
                requirementListVTO.setTaxTerm(resultSet.getString("tax_term")); //reqType
                requirementListVTO.setStatus(status);
                requirementListVTO.setReq_contact1(resultSet.getString("req_contact1"));
                requirementListVTO.setReq_contact2(resultSet.getString("req_contact2"));
                requirementListVTO.setReqContactName1(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")));
                requirementListVTO.setReqContactName2(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")));
                requirementListVTO.setCustomerName(resultSet.getString("account_name"));
                requirementListVTO.setRequirementMaxRate(resultSet.getString("max_rate"));
                requirementListVTO.setNoOfSubmissions(com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfSubmisions(resultSet.getInt("requirement_id"), 0));
                list.add(requirementListVTO);
                if (typeofusr.equalsIgnoreCase("VC")) {
                    joined = requirementListVTO.getJdId() + "|" + requirementListVTO.getTitle() + "|" + requirementListVTO.getCustomerName() + "|" + requirementListVTO.getReqSkillSet() + "|" + requirementListVTO.getPostedDate() + "|" + requirementListVTO.getStatus() + "^";
                    resultantString += joined;
                } else {
                    joined = requirementListVTO.getJdId() + "|" + requirementListVTO.getTitle() + "|" + requirementListVTO.getNoOfPosition() + "|" + requirementListVTO.getReqSkillSet() + "|" + requirementListVTO.getPostedDate() + "|" + requirementListVTO.getStatus() + "|" + requirementListVTO.getNoOfSubmissions() + "^";
                    resultantString += joined;
                }
            }
            recruitmentAction.setGridPDFDownload(queryString);
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
        System.out.println("********************RecruitmentServiceImpl :: getLoginUserRequirementList Method End*********************");
        return list;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getCurrentStatus() method is used to
     *
     * *****************************************************************************
     */
    public List getCurrentStatus(RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getCurrentStatus Method Start*********************");
        ArrayList<ConsultantListVTO> conslist = new ArrayList<ConsultantListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        DateUtility dateUtility = new DateUtility();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            queryString = "SELECT * FROM acc_rec_attachment WHERE object_type='CV' AND object_id=" + recruitmentAction.getUserSessionId() + " AND status='Active' ORDER BY STATUS LIMIT 10";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ConsultantListVTO cons = new ConsultantListVTO();
                cons.setAcc_attachment_id(resultSet.getInt("acc_attachment_id"));
                cons.setObject_id(resultSet.getInt("object_id"));
                cons.setAttachement_file_name(resultSet.getString("attachment_name"));
                cons.setAttachment_type(resultSet.getString("object_type"));
                cons.setDownload_link(resultSet.getString("attachment_path"));
                cons.setDate_of_attachment(dateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("opp_created_date")));
                cons.setStatus(resultSet.getString("status"));
                conslist.add(cons);
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
        System.out.println("********************RecruitmentServiceImpl :: getCurrentStatus Method End*********************");
        return conslist;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getConsultantStatus() method is used to
     *
     * *****************************************************************************
     */
    public List getConsultantStatus(RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getConsultantStatus Method Start*********************");
        ArrayList<ConsultantListVTO> consStatus = new ArrayList<ConsultantListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            queryString = "SELECT rcl.reqId, ar.req_name, ar.req_skills, rcl.status,rcl.applied_date FROM req_con_rel rcl JOIN acc_requirements ar ON(rcl.reqId = ar.requirement_id) WHERE consultantId=" + recruitmentAction.getUserSessionId() + " ORDER BY STATUS LIMIT 10";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ConsultantListVTO consultantListVTO = new ConsultantListVTO();
                consultantListVTO.setReqId(resultSet.getInt("reqId"));
                consultantListVTO.setReq_name(resultSet.getString("req_name"));
                consultantListVTO.setReq_skills(resultSet.getString("req_skills"));
                consultantListVTO.setStatus(resultSet.getString("status"));
                consultantListVTO.setCreatedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("applied_date")));
                consStatus.add(consultantListVTO);
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
        System.out.println("********************RecruitmentServiceImpl :: getConsultantStatus Method End*********************");
        return consStatus;
    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * techReviewForward() getting consultant list Default.
     *
     *
     * *****************************************************************************
     */
    public int techReviewForward(RecruitmentAction recruitmentAction, String accToken, String validKey) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: techReviewForward Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();
        Connection connection1 = null;
        String interviewDate = "";
        String reviewAlertDate = "";

        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            if ("checked".equals(recruitmentAction.getChecked())) {
                if (!"".equals(recruitmentAction.getReviewAlertDate())) {
                    reviewAlertDate = dateUtility.getInstance().convertStringToMySQLDateInDashWithTimeWithOutSeconds(recruitmentAction.getReviewAlertDate());
                } else {
                    reviewAlertDate = " ";
                }
            }
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL techReviewForward(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("techReviewForward :: procedure name : techReviewForward ");
            callableStatement.setInt(1, recruitmentAction.getRequirementId());
            callableStatement.setInt(2, recruitmentAction.getConsult_id());
            callableStatement.setInt(3, recruitmentAction.getEmpIdTechReview());
            if (!"".equals(recruitmentAction.getInterviewDate())) {
                callableStatement.setString(4, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDashWithTimeWithOutSeconds((recruitmentAction.getInterviewDate())));
            } else {
                callableStatement.setString(4, " ");
            }
            callableStatement.setInt(5, recruitmentAction.getEmpIdTechReview2());
            callableStatement.setInt(6, recruitmentAction.getUserSessionId());
            callableStatement.setString(7, recruitmentAction.getInterviewType());
            callableStatement.setString(8, recruitmentAction.getTimeZone());
            callableStatement.setString(9, recruitmentAction.getInterviewLocation());
            callableStatement.setString(10, accToken);
            callableStatement.setString(11, validKey);
            if (!"".equals(recruitmentAction.getTechReviewComments())) {
                callableStatement.setString(12, recruitmentAction.getTechReviewComments());
            } else {
                callableStatement.setString(12, " ");
            }
            if (!"".equals(recruitmentAction.getTechReviewQuestions())) {
                callableStatement.setString(13, recruitmentAction.getTechReviewQuestions());
            } else {
                callableStatement.setString(13, "0");
            }
            callableStatement.setString(14, recruitmentAction.getTechReviewSeverity());
            callableStatement.setString(15, recruitmentAction.getTechReviewTime());
            callableStatement.setString(16, recruitmentAction.getSkillCategoryArry());
            callableStatement.setString(17, recruitmentAction.getSkill1Questions());
            callableStatement.setString(18, recruitmentAction.getSkill2Questions());
            callableStatement.setString(19, recruitmentAction.getSkill3Questions());
            callableStatement.setString(20, recruitmentAction.getSkill4Questions());
            callableStatement.setString(21, recruitmentAction.getSkill5Questions());
            callableStatement.setString(22, recruitmentAction.getSkill6Questions());
            callableStatement.setString(23, recruitmentAction.getSkill7Questions());
            callableStatement.setString(24, recruitmentAction.getSkill8Questions());
            callableStatement.setString(25, recruitmentAction.getSkill9Questions());
            callableStatement.setString(26, recruitmentAction.getSkill10Questions());
            callableStatement.setInt(27, recruitmentAction.getSessionOrgId());
            callableStatement.setString(28, recruitmentAction.getContechNote());
            callableStatement.setString(29, reviewAlertDate);
            if ("checked".equals(recruitmentAction.getChecked())) {
                callableStatement.setString(30, recruitmentAction.getAlertMessageTechReview());
            } else {
                callableStatement.setString(30, "");
            }
            callableStatement.registerOutParameter(31, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(31);
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
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************RecruitmentServiceImpl :: techReviewForward Method End*********************");
        return addResult;
    }

    /**
     * *************************************
     *
     * @getLoginUserRequirementList() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/07/2015
     *
     **************************************
     */
    public List getTechReviewDetails(RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getTechReviewDetails Method Start*********************");
        ArrayList<ConsultantVTO> list = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            queryString = "SELECT rcr.vendorcomments,CONCAT(c.first_name,'.',c.last_name) AS NAME,ct.forwarded_by,ct.forwarded_date,CONCAT(u.first_name,'.',u.last_name)AS fbyName,c.email1,c.phone1,c.usr_id,r.acc_attachment_id,ct.status,ct.req_id,ct.review_type,ct.id,c.office_phone,ct.scheduled_date,ct.scheduled_time,ct.zone  "
                    + "FROM con_techreview ct "
                    + "LEFT OUTER JOIN users c ON(c.usr_id=ct.consultant_id) "
                    + "LEFT OUTER JOIN acc_rec_attachment r ON(r.object_id=c.usr_id AND r.req_id = ct.req_id) "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=ct.forwarded_by) "
                    + "LEFT OUTER JOIN req_con_rel rcr ON(ct.consultant_id=rcr.consultantId AND rcr.reqId=ct.req_id)"
                    + "WHERE ct.forwarded_to=" + recruitmentAction.getUserSessionId() + " AND r.STATUS='active' ORDER BY forwarded_date  DESC LIMIT 50";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ConsultantVTO consultantVTO = new ConsultantVTO();
                consultantVTO.setConsult_id(resultSet.getInt("usr_id"));
                consultantVTO.setConsult_name(resultSet.getString("NAME"));
                consultantVTO.setForwardedDate(com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("forwarded_date")));
                consultantVTO.setForwardedBy(resultSet.getString("fbyName"));
                consultantVTO.setConsult_email(resultSet.getString("email1"));
                consultantVTO.setResumeId(resultSet.getString("acc_attachment_id"));
                consultantVTO.setConsult_Phone(resultSet.getString("phone1"));
                consultantVTO.setForwardedById(resultSet.getInt("forwarded_by"));
                consultantVTO.setStatus(resultSet.getString("status"));
                consultantVTO.setRequirementId(resultSet.getInt("req_id"));
                consultantVTO.setReviewType(resultSet.getString("review_type"));
                consultantVTO.setConTechReviewId(resultSet.getInt("id"));
                if (resultSet.getString("scheduled_date") != null) {
                    consultantVTO.setScheduledDate(com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("scheduled_date")));
                } else {
                    consultantVTO.setScheduledDate("");
                }
                consultantVTO.setScheduledTime(resultSet.getString("scheduled_time"));
                consultantVTO.setZone(resultSet.getString("zone"));
                consultantVTO.setVendorcomments(resultSet.getString("vendorcomments"));
                list.add(consultantVTO);
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
        System.out.println("********************RecruitmentServiceImpl :: getTechReviewDetails Method End*********************");
        return list;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getSearchTechReviewList() method is used to
     *
     * *****************************************************************************
     */
    public String getSearchTechReviewList(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: getSearchTechReviewList Method Start*********************");
        DateUtility dateUtility = new DateUtility();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        String resultString = "";
        String startdate = "";
        String endDate = "";
        String scheduledDate = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            queryString = "SELECT rcr.vendorcomments,CONCAT(c.first_name,'.',c.last_name) AS NAME,ct.forwarded_date,ct.forwarded_by,"
                    + "CONCAT(u.first_name,'.',u.last_name)AS fbyName,c.email1,c.phone1,"
                    + "c.usr_id,r.acc_attachment_id,ct.status,ct.req_id,ct.review_type,ct.id,c.office_phone,ct.scheduled_date,ct.scheduled_time,ct.zone "
                    + "FROM con_techreview ct "
                    + "LEFT OUTER JOIN users c ON(c.usr_id=ct.consultant_id)"
                    + "LEFT OUTER JOIN acc_rec_attachment r ON(r.object_id=c.usr_id AND r.req_id = ct.req_id) "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=ct.forwarded_by) "
                    + "LEFT OUTER JOIN req_con_rel rcr ON(ct.consultant_id=rcr.consultantId AND rcr.reqId=ct.req_id)"
                    + "WHERE ct.forwarded_to=" + recruitmentAction.getUserSessionId() + " AND r.STATUS='Active' ";
            if (!"".equals(recruitmentAction.getReviewStartDate()) && !"".equals(recruitmentAction.getReviewEndDate())) {
                startdate = dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getReviewStartDate());
                endDate = dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getReviewEndDate());
                queryString = queryString + " AND ct.scheduled_date between '" + startdate + "' AND '" + endDate + "'";
            }
            if (!"-1".equalsIgnoreCase(recruitmentAction.getTechReviewStatus())) {
                queryString = queryString + " and ct.status = '" + recruitmentAction.getTechReviewStatus() + "'";
            }
            queryString = queryString + " ORDER BY forwarded_date DESC";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if (resultSet.getString("scheduled_date") != null) {
                    scheduledDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("scheduled_date"));
                } else {
                    scheduledDate = "";
                }
                resultString += resultSet.getString("usr_id") + "|" + resultSet.getString("NAME") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("phone1") + "|" + resultSet.getString("fbyName") + "|" + dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("forwarded_date")) + "|" + resultSet.getString("acc_attachment_id") + "|" + resultSet.getString("forwarded_by") + "|" + resultSet.getString("status") + "|" + resultSet.getString("req_id") + "|" + resultSet.getString("ct.review_type") + "|" + resultSet.getInt("ct.id") + "|" + scheduledDate + "|" + resultSet.getString("scheduled_time") + "|" + resultSet.getString("zone") + "|" + resultSet.getString("vendorcomments") + "^";
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
        System.out.println("********************RecruitmentServiceImpl :: getSearchTechReviewList Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getConsultDetailsOnOverlay() method is used to
     *
     * *****************************************************************************
     */
    public String getConsultDetailsOnOverlay(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: getConsultDetailsOnOverlay Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        String resultString = "";
        String jobTitle = " ";
        String conSkills = " ";
        String scheduledDate = " ";
        String date = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String option1 = null, option2 = null, option3 = null, option4 = null, option5 = null, option6 = null, option7 = null, option8 = null, option9 = null, option10 = null;
        String skill1Name1 = null, skill1Name2 = null, skill1Name3 = null, skill1Name4 = null, skill1Name5 = null, skill1Name6 = null, skill1Name7 = null, skill1Name8 = null, skill1Name9 = null, skill1Name10 = null;
        int noOfQuestion1 = 0, noOfQuestion2 = 0, noOfQuestion3 = 0, noOfQuestion4 = 0, noOfQuestion5 = 0, noOfQuestion6 = 0, noOfQuestion7 = 0, noOfQuestion8 = 0, noOfQuestion9 = 0, noOfQuestion10 = 0;
        int rightAns1 = 0, rightAns2 = 0, rightAns3 = 0, rightAns4 = 0, rightAns5 = 0, rightAns6 = 0, rightAns7 = 0, rightAns8 = 0, rightAns9 = 0, rightAns10 = 0;

        try {
            if (!"Online".equals(recruitmentAction.getReviewType()) && !"Psychometric".equals(recruitmentAction.getReviewType())) {
                queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,c.email1,c.phone1,cd.job_title,rcr.con_skill,"
                        + "cr.scheduled_date,ar.acc_attachment_id,cr.review_type, cr.tech_skills, cr.domain_skills, cr.commmunication_skills, cr.comments,cr.note,cr.interview_lacation "
                        + "FROM users c LEFT OUTER JOIN con_techreview cr ON(cr.consultant_id=c.usr_id) "
                        + "LEFT OUTER JOIN acc_rec_attachment ar ON(ar.object_id=c.usr_id) "
                        + "LEFT OUTER JOIN usr_details cd ON(cd.usr_id=c.usr_id) "
                        + "LEFT OUTER JOIN req_con_rel rcr ON(rcr.consultantId=c.usr_id)"
                        + "WHERE c.usr_id=" + recruitmentAction.getConsultantId() + " AND ar.STATUS='active' AND cr.id=" + recruitmentAction.getContechId() + " AND rcr.reqId=" + recruitmentAction.getReq_Id();
            } else {
                queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,so.techreviewid,so.consultantid,cr.id,cr.STATUS,so.option1,so.option2,so.option3,so.option4,so.option5,so.option6,so.option7,so.option8,so.option9,so.option10,c.email1,so.createddate,c.phone1,cd.job_title,so.eid,so.examstatus,se.completed_ts,cr.note,cr.comments  FROM sb_onlineexam so"
                        + " LEFT OUTER JOIN con_techreview cr ON(so.techreviewid=cr.id)"
                        + " LEFT OUTER JOIN users c ON(c.usr_id=so.consultantid)"
                        + " LEFT OUTER JOIN usr_details cd ON(cd.usr_id=so.consultantid)"
                        + " LEFT OUTER JOIN sb_onlineexamsummery se ON(so.eid=se.examid)"
                        + " WHERE so.techreviewid=" + recruitmentAction.getContechId() + "";
            }
            System.out.println("getConsultDetailsOnOverlay::query================>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                if (!"Online".equals(recruitmentAction.getReviewType()) && !"Psychometric".equals(recruitmentAction.getReviewType())) {
                    resultString = resultSet.getString("NAME") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("phone1") + "|" + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("scheduled_date")) + "|" + resultSet.getString("acc_attachment_id") + "|" + resultSet.getString("job_title") + "|" + resultSet.getString("con_skill") + "|" + resultSet.getString("review_type") + "|" + resultSet.getString("tech_skills") + "|" + resultSet.getString("domain_skills") + "|" + resultSet.getString("commmunication_skills") + "|" + resultSet.getString("cr.comments") + "|" + resultSet.getString("cr.note") + "|" + resultSet.getString("interview_lacation") + "^";
                } else {
                    if (resultSet.last()) {
                        int eid = resultSet.getInt("so.eid");
                        option1 = resultSet.getString("option1");
                        if (!"".equals(option1)) {
                            String[] str1 = option1.split("-");
                            skill1Name1 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str1[0]));
                            rightAns1 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str1[0]), eid);
                            noOfQuestion1 = Integer.parseInt(str1[1]);
                        }
                        option2 = resultSet.getString("option2");
                        if (!"".equals(option2)) {
                            String[] str2 = option2.split("-");
                            skill1Name2 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str2[0]));
                            rightAns2 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str2[0]), eid);
                            noOfQuestion2 = Integer.parseInt(str2[1]);
                        }
                        option3 = resultSet.getString("option3");
                        System.out.println("option3-->" + option3 + "<---");
                        if (!"".equals(option3)) {
                            String[] str3 = option3.split("-");
                            skill1Name3 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str3[0]));
                            rightAns3 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str3[0]), eid);
                            noOfQuestion3 = Integer.parseInt(str3[1]);
                        }
                        option4 = resultSet.getString("option4");
                        if (!"".equals(option4)) {
                            String[] str4 = option4.split("-");
                            skill1Name4 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str4[0]));
                            rightAns4 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str4[0]), eid);
                            noOfQuestion4 = Integer.parseInt(str4[1]);
                        }
                        option5 = resultSet.getString("option5");
                        if (!"".equals(option5)) {
                            String[] str5 = option5.split("-");
                            skill1Name5 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str5[0]));
                            rightAns5 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str5[0]), eid);
                            noOfQuestion5 = Integer.parseInt(str5[1]);
                        }
                        option6 = resultSet.getString("option6");
                        if (!"".equals(option6)) {
                            String[] str6 = option6.split("-");
                            skill1Name6 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str6[0]));
                            rightAns6 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str6[0]), eid);
                            noOfQuestion6 = Integer.parseInt(str6[1]);
                        }
                        option7 = resultSet.getString("option7");
                        if (!"".equals(option7)) {
                            String[] str7 = option7.split("-");
                            skill1Name7 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str7[0]));
                            rightAns7 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str7[0]), eid);
                            noOfQuestion7 = Integer.parseInt(str7[1]);
                        }
                        option8 = resultSet.getString("option8");
                        if (!"".equals(option8)) {
                            String[] str8 = option8.split("-");
                            skill1Name8 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str8[0]));
                            rightAns8 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str8[0]), eid);
                            noOfQuestion8 = Integer.parseInt(str8[1]);
                        }
                        option9 = resultSet.getString("option9");
                        if (!"".equals(option9)) {
                            System.out.println("option9-->" + option9 + "<---");
                            String[] str9 = option9.split("-");
                            skill1Name9 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str9[0]));
                            rightAns9 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str9[0]), eid);
                            noOfQuestion9 = Integer.parseInt(str9[1]);
                        }
                        option10 = resultSet.getString("option10");
                        if (!"".equals(option10)) {
                            String[] str10 = option10.split("-");
                            skill1Name10 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str10[0]));
                            rightAns10 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str10[0]), eid);
                            noOfQuestion10 = Integer.parseInt(str10[1]);
                        }
                        if (resultSet.getString("se.completed_ts") != null) {
                            date = DateUtility.getInstance().convertToviewFormatInDashWithTime(resultSet.getString("se.completed_ts"));
                        } else {
                            date = "";
                        }
                        resultString = resultSet.getString("NAME") + "|" + resultSet.getString("job_title") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("phone1") + "|" + DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("so.createddate")) + "|" + resultSet.getString("cr.STATUS")
                                + "|" + StringUtils.chop(skill1Name1) + "|" + noOfQuestion1 + "|" + StringUtils.chop(skill1Name2) + "|" + noOfQuestion2 + "|" + StringUtils.chop(skill1Name3) + "|" + noOfQuestion3 + "|" + StringUtils.chop(skill1Name4) + "|" + noOfQuestion4 + "|" + StringUtils.chop(skill1Name5) + "|" + noOfQuestion5
                                + "|" + StringUtils.chop(skill1Name6) + "|" + noOfQuestion6 + "|" + StringUtils.chop(skill1Name7) + "|" + noOfQuestion7 + "|" + StringUtils.chop(skill1Name8) + "|" + noOfQuestion8 + "|" + StringUtils.chop(skill1Name9) + "|" + noOfQuestion9 + "|" + StringUtils.chop(skill1Name10) + "|" + noOfQuestion10
                                + "|" + rightAns1 + "|" + rightAns2 + "|" + rightAns3 + "|" + rightAns4 + "|" + rightAns5 + "|" + rightAns6 + "|" + rightAns7 + "|" + rightAns8 + "|" + rightAns9 + "|" + rightAns10 + "|" + resultSet.getString("so.examstatus") + "|" + date + "|" + resultSet.getString("note") + "|" + resultSet.getString("comments") + "^";
                    }
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
        System.out.println("********************RecruitmentServiceImpl :: getConsultDetailsOnOverlay Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : saveTechReviewResults() method is used to
     *
     * *****************************************************************************
     */
    public int saveTechReviewResults(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: saveTechReviewResults Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        int resultString = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            queryString = "UPDATE con_techreview ct left outer join req_con_rel rc on(rc.consultantId=ct.consultant_id) "
                    + "SET ct.tech_skills=" + recruitmentAction.getTechSkill() + " ,"
                    + "ct.domain_skills=" + recruitmentAction.getDomainSkill() + " ,"
                    + "ct.commmunication_skills=" + recruitmentAction.getComSkill() + " ,"
                    + "ct.rating=" + recruitmentAction.getRating() + " ,"
                    + "ct.comments='" + recruitmentAction.getConsultantComments() + "' ,"
                    + "ct.STATUS='" + recruitmentAction.getFinalTechReviewStatus() + "' ,"
                    + "ct.review_type='" + recruitmentAction.getInterviewType() + "' ,"
                    + "ct.techie_title='" + recruitmentAction.getTechTitle() + "' ,"
                    + "rc.STATUS='" + recruitmentAction.getFinalTechReviewStatus() + "',"
                    + "rc.customercomments='" + recruitmentAction.getConsultantComments() + "' ,"
                    + "rc.modified_By='" + recruitmentAction.getUserSessionId() + "',"
                    + "rc.modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDate() + "',"
                    + "rc.tech_review_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDate() + "',"
                    + "rc.tech_review_by='" + recruitmentAction.getUserSessionId() + "' "
                    + "WHERE ct.consultant_id=" + recruitmentAction.getConsultId() + " AND ct.req_id=" + recruitmentAction.getRequirementId() + " "
                    + "AND rc.consultantId=" + recruitmentAction.getConsultId() + " AND rc.reqId=" + recruitmentAction.getRequirementId() + " "
                    + "AND ct.forwarded_to=" + recruitmentAction.getUserSessionId() + " AND ct.id=" + recruitmentAction.getContechId();
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultString = statement.executeUpdate(queryString);
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
        System.out.println("********************RecruitmentServiceImpl :: saveTechReviewResults Method End*********************");
        return resultString;
    }

    /**
     * *************************************
     *
     * @doDeleteConsultantAttachment() method is used to delete the attachment
     * of consultant
     *
     * @Author:mahmad<mahmad@miraclesoft.com>
     *
     * @Created Date: June 1 2015
     *
     **************************************
     */
    public int doDeleteConsultantAttachment(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: doDeleteConsultantAttachment Method Start*********************");
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "update acc_rec_attachment set status='In-Active' WHERE acc_attachment_id=" + recruitmentAction.getAcc_attachment_id();

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(queryString);
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
        System.out.println("********************RecruitmentServiceImpl :: doDeleteConsultantAttachment Method End*********************");
        return deletedRows;
    }

    /**
     * *************************************
     *
     * @updateConsultAttachmentDetails() method is used to add the consultant
     * updated resume
     *
     * @Author:mahmad<mahmad@miraclesoft.com>
     *
     * @Created Date: June 1 2015
     *
     **************************************
     */
    public int updateConsultAttachmentDetails(RecruitmentAction recruitmentaction) {
        System.out.println("********************RecruitmentServiceImpl :: updateConsultAttachmentDetails Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String fpath = recruitmentaction.getFilePath();
            recruitmentaction.setFilePath(fpath);
            callableStatement = connection.prepareCall("{CALL addConsultantAttachment(?,?,?,?,?,?)}");
            System.out.println("updateConsultAttachmentDetails :: procedure name : addConsultantAttachment ");
            callableStatement.setInt(1, recruitmentaction.getUserSessionId());
            callableStatement.setString(2, "CV");
            callableStatement.setString(3, recruitmentaction.getFileFileName());
            callableStatement.setString(4, recruitmentaction.getFilePath());
            callableStatement.setInt(5, recruitmentaction.getUserSessionId());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(6);
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
        System.out.println("********************RecruitmentServiceImpl :: updateConsultAttachmentDetails Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTechReviewSearchDetails() method is used to
     *
     * *****************************************************************************
     */
    public List getTechReviewSearchDetails(RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getTechReviewSearchDetails Method Start*********************");
        ArrayList<ConsultantVTO> list = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            queryString = "SELECT forwarded_to_name1,forwarded_to1,id,review_type,forwarded_to,consultant_id,req_id,scheduled_date,forwarded_to_name,comments,techie_title,STATUS,rating,forwarded_by,forwarded_date FROM con_techreview WHERE consultant_id=" + recruitmentAction.getConsult_id() + " AND req_id=" + recruitmentAction.getRequirementId();
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ConsultantVTO consultantVTO = new ConsultantVTO();
                if (resultSet.getString("scheduled_date") != null) {
                    consultantVTO.setDateOfReview(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("scheduled_date")));
                } else {
                    consultantVTO.setDateOfReview(" ");
                }
                consultantVTO.setForwardedToName(resultSet.getString("forwarded_to_name"));
                consultantVTO.setForwardedToName1(resultSet.getString("forwarded_to_name1"));
                consultantVTO.setComments(resultSet.getString("comments"));
                consultantVTO.setTechieTitle(resultSet.getString("techie_title"));
                consultantVTO.setStatus(resultSet.getString("STATUS"));
                consultantVTO.setConsult_id(resultSet.getInt("consultant_id"));
                consultantVTO.setRequirementId(resultSet.getInt("req_id"));
                consultantVTO.setForwardedToId(resultSet.getInt("forwarded_to"));
                consultantVTO.setForwardedToId1(resultSet.getInt("forwarded_to1"));
                consultantVTO.setReviewType(resultSet.getString("review_type"));
                consultantVTO.setConTechReviewId(resultSet.getInt("id"));
                if (resultSet.getString("rating") != null) {
                    Double value = Double.parseDouble(resultSet.getString("rating"));
                    consultantVTO.setAvgRating(String.format("%.1f", value));
                } else {
                    consultantVTO.setAvgRating(" ");
                }
                consultantVTO.setForwardby(resultSet.getInt("forwarded_by"));
                consultantVTO.setForwardedBy(DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("forwarded_by")));
                consultantVTO.setForwardedDate(DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("forwarded_date")));
                list.add(consultantVTO);
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
        System.out.println("********************RecruitmentServiceImpl :: getTechReviewSearchDetails Method End*********************");
        return list;
    }

    /**
     * *************************************
     *
     * @ userMigration() method is used to migrate consultant into user
     *
     *
     * @Author:divya gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:07/20/2015
     *
     **************************************
     */
    public int userMigration(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: userMigration Method Start*********************");
        int resultString = 0;
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call userMigration(?,?,?,?,?)}");
            System.out.println("userMigration :: procedure name : userMigration ");
            callableStatement.setInt(1, recruitmentAction.getConsult_id());
            callableStatement.setInt(2, recruitmentAction.getUserSessionId());
            callableStatement.setInt(3, recruitmentAction.getReq_Id());
            callableStatement.setString(4, recruitmentAction.getMigrationEmailId());
            callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
            callableStatement.execute();
            resultString = callableStatement.getInt(5);
        } catch (SQLException ex) {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************RecruitmentServiceImpl :: userMigration Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doAddVendorFormDetails() method is used to
     *
     * *****************************************************************************
     */
    public int doAddVendorFormDetails(RecruitmentAction recruitmentAction, int orgId, String typeOfAccount) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: doAddVendorFormDetails Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "INSERT INTO acc_rec_attachment(object_id,object_type,attachment_name,attachment_path,STATUS,opp_created_by,validity,attachment_title,comments) VALUES(?,?,?,?,?,?,?,?,?)";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, recruitmentAction.getViewAccountID());
            preparedStatement.setString(2, recruitmentAction.getVendorDocs());
            preparedStatement.setString(3, recruitmentAction.getFileFileName());
            preparedStatement.setString(4, recruitmentAction.getFilesPath());
            preparedStatement.setString(5, "Active");
            preparedStatement.setInt(6, recruitmentAction.getUserSessionId());
            preparedStatement.setString(7, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getValidity()));
            preparedStatement.setString(8, recruitmentAction.getAttachmentTitle());
            preparedStatement.setString(9, recruitmentAction.getAttachmentComments());
            addResult = preparedStatement.executeUpdate();
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
        System.out.println("********************RecruitmentServiceImpl :: doAddVendorFormDetails Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doUpdateVendorFormDetails() method is used to
     *
     * *****************************************************************************
     */
    public int doUpdateVendorFormDetails(RecruitmentAction recruitmentAction, int attachment_id) throws ServiceLocatorException {
        System.out.println("********************RecruitmentServiceImpl :: doUpdateVendorFormDetails Method Start*********************");
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;

        DateUtility dateUtility = new DateUtility();
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE acc_rec_attachment SET validity=?,comments=?,attachment_title=?  WHERE acc_attachment_id=" + attachment_id;
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getEditValidity()));
            preparedStatement.setString(2, recruitmentAction.getEditattachmentComments());
            preparedStatement.setString(3, recruitmentAction.getEditTitle());
            addResult = preparedStatement.executeUpdate();
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
        System.out.println("********************RecruitmentServiceImpl :: doUpdateVendorFormDetails Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorRequirementsDashboard() method is used to
     *
     * *****************************************************************************
     */
    public List getVendorRequirementsDashboard(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) {
        System.out.println("********************RecruitmentServiceImpl :: getVendorRequirementsDashboard Method Start*********************");
        ArrayList<RequirementListVTO> list = new ArrayList<RequirementListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            queryString = "SELECT acc_id,account_name,jdid,requirement_id,req_name,no_of_resources,target_rate,max_rate FROM accounts a JOIN acc_requirements ar ON ar.acc_id=a.account_id LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id WHERE ven_id=" + recruitmentAction.getOrgid() + " AND  NOW() >= req_access_time AND  r.STATUS LIKE 'Active' AND  ar.req_status = 'OR' order by requirement_id desc LIMIT 3 ";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                RequirementListVTO requirementListVTO = new RequirementListVTO();
                requirementListVTO.setJdId(resultSet.getString("jdid"));
                requirementListVTO.setId(resultSet.getInt("requirement_id"));
                requirementListVTO.setTitle(resultSet.getString("req_name"));
                requirementListVTO.setNoOfPosition(resultSet.getString("no_of_resources"));
                requirementListVTO.setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                requirementListVTO.setTargetRate(String.valueOf((resultSet.getDouble("target_rate") + resultSet.getDouble("max_rate")) / 2));
                requirementListVTO.setRequirementMaxRate(resultSet.getString("max_rate"));
                requirementListVTO.setCustomerName(resultSet.getString("account_name"));
                list.add(requirementListVTO);
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
        System.out.println("********************RecruitmentServiceImpl :: getVendorRequirementsDashboard Method End*********************");
        return list;
    }
}
