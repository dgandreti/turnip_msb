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

    private Connection connection;
    private DataSourceDataProvider dataSourceDataProvider;

    /**
     * ****************************************************************************
     * Date : May 12 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * getConsListDetails method can be used to show default requirement list
     *
     * This method is used for all my,team and services search
     * *****************************************************************************
     */
    public List getMyDefaultConsListDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        ArrayList<ConsultantVTO> conslist = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int i = 0;
        try {
            Map map = dataSourceDataProvider.getInstance().getEmpConsultantTeamMap(recruitmentAction.getSessionOrgId());
            //ConsultantListDetails.add(map);


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
                    + " usr_details cd ON c.usr_id=cd.usr_id where type_of_user='IC'"; //and created_by=" + recruitmentAction.getUserSessionId();


            if ("My".equalsIgnoreCase(recruitmentAction.getConsultantFlag())) {
                queryString = queryString + " and created_by=" + recruitmentAction.getUserSessionId();
            }
             if ("All".equalsIgnoreCase(recruitmentAction.getConsultantFlag()) || "Team".equalsIgnoreCase(recruitmentAction.getConsultantFlag()) ) {
             queryString = queryString + " and created_by in(" + retrunValue + ")";
             System.out.println("queryString helloooo -->" + queryString);

             }
            queryString = queryString + " LIMIT 100";
            System.out.println("queryString helloooo -->" + queryString);
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
                // cons.setConsult_skill(resultSet.getString("consultant_skills"));
//                String str = resultSet.getString("consultant_skills");
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                // List list = new ArrayList();
//                String skillsResultString = "";
//                while (stk.hasMoreTokens()) {
//                    skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//                }
//                cons.setConsult_skill(StringUtils.chop(skillsResultString));
                cons.setConsult_skill(resultSet.getString("consultant_skills"));
                cons.setConsult_salary(resultSet.getString("rate_salary"));
                cons.setConsult_status(resultSet.getString("cur_status"));
                //cons.setConsultantFlag(recruitmentAction.getConsultantFlag());
                // System.out.println("consultantfalag in impl"+cons.getConsultantFlag());
                System.out.println("div" + resultSet.getString("phone1") + "  " + resultSet.getString("email1") + "  " + resultSet.getString("name") + "    " + resultSet.getString("consultant_skills"));
                conslist.add(cons);
                joined = cons.getConsult_name() + "|" + cons.getConsult_email() + "|" + cons.getConsult_skill() + "|" + cons.getConsult_salary() + "|" + cons.getConsult_phno() + "|" + cons.getConsult_status() + "^";
                resultantString += joined;
                System.out.println(conslist.size());


                // System.out.println(conslist.size()+cons.getCons_email()+cons.getCons_name());


            }
            recruitmentAction.setGridPDFDownload(resultantString);
            // System.out.println("----------->" + leaveslist);
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
        System.out.println(conslist);

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
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String joined = "Name" + "|" + "E-Mail" + "|" + "Skill Set" + "|" + "Rate/Salary" + "|" + "Phone Number" + "|" + "Status" + "^"; // for grid download
        String resultantString = joined;

        try {
            Map map = dataSourceDataProvider.getInstance().getEmpConsultantTeamMap(recruitmentAction.getSessionOrgId());

            //ConsultantListDetails.add(map);


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

            queryString = "SELECT c.usr_id as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS name,c.phone1,c.email1,cd.usr_id,cd.consultant_skills, cd.rate_salary,c.cur_status FROM users c LEFT OUTER JOIN"
                    + " usr_details cd ON c.usr_id=cd.usr_id "
                    + "LEFT OUTER JOIN usr_address ca ON c.usr_id=ca.usr_id "
                    + "where type_of_user='IC'";
            System.out.println("in vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" + recruitmentAction.getConsultantFlag());
            if ("My".equalsIgnoreCase(recruitmentAction.getConsultantFlag())) {
                queryString = queryString + " and c.created_by = '" + recruitmentAction.getUserSessionId() + "'";
            }
             if ("All".equalsIgnoreCase(recruitmentAction.getConsultantFlag()) || "Team".equalsIgnoreCase(recruitmentAction.getConsultantFlag()) ) {
                
                // System.out.println("getTeamMembers -->" + recruitmentAction.getTeamMembers()+"<--");
                if (!"".equalsIgnoreCase(recruitmentAction.getEnameForRecruitment())) {
                    //queryString = queryString + "AND(t.task_created_by="+userLeavesAction.getTeamMember()+" )";
                    queryString = queryString + " and c.created_by =" + recruitmentAction.getEnameIdForRecruitment() + "";
                }else
                {
                 queryString = queryString + " and c.created_by in(" + retrunValue + ")";   
                }

            }
            if (recruitmentAction.getConsult_name().trim().isEmpty() == false) {
                queryString = queryString + " and (c.first_name like'%" + recruitmentAction.getConsult_name().trim() + "%' or c.middle_name like'%" + recruitmentAction.getConsult_name().trim() + "%' or c.last_name like'%" + recruitmentAction.getConsult_name().trim() + "%') ";
            }
            if (recruitmentAction.getConsult_email().trim().isEmpty() == false) {
                queryString = queryString + " and c.email1 like'%" + recruitmentAction.getConsult_email().trim() + "%' ";
            }
            if (recruitmentAction.getConsult_phno().trim().isEmpty() == false) {
                queryString = queryString + " and c.phone1 like'%" + recruitmentAction.getConsult_phno().trim() + "%' ";

            }
//            if (recruitmentAction.getConsult_skill().trim().isEmpty() == false) {
//                queryString = queryString + " and cd.consultant_skills like'%" + recruitmentAction.getConsult_skill().trim() + "%' ";
//
//            }
            if (!"-1".equalsIgnoreCase(recruitmentAction.getConsult_status())) {
                queryString = queryString + " and c.cur_status ='" + recruitmentAction.getConsult_status() + "' ";
            }
            String str = recruitmentAction.getSkillValues();
            if (str != null && !"".equals(recruitmentAction.getSkillValues())) {
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                String reqSkillsResultString = "";
//                while (stk.hasMoreTokens()) {
//                    reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//                }
//                recruitmentAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
//                System.out.println("reqSkillsResultString---------->" + reqSkillsResultString);
//                String sampleString = "";
//                sampleString = recruitmentAction.getReqSkillSet();
//                String[] reqSkillSet = sampleString.split(",");
//                String matchSkillSet = "";
//                for (String skillSet : reqSkillSet) {
//                    matchSkillSet = matchSkillSet + " +" + skillSet;
//                }
//                System.out.println("matchSkillSet---------->" + matchSkillSet);

                if (!"".equalsIgnoreCase(recruitmentAction.getSkillValues())) {
                    // queryString = queryString + " and cd.consultant_skills LIKE '%" + recruitmentAction.getSkillValues() + "%' ";
                    queryString += " and MATCH(consultant_skills) AGAINST ('" + str + "' IN BOOLEAN MODE)";
                }
            }
            if (!"-1".equalsIgnoreCase(recruitmentAction.getConsult_Country())) {
                queryString = queryString + " AND ca.country=" + recruitmentAction.getConsult_Country() + " "
                        + "AND (ca.address_flag='PC' OR ca.address_flag='C') ";
            }
            if (recruitmentAction.getConsult_State() != -1) {
                queryString = queryString + " AND ca.state=" + recruitmentAction.getConsult_State() + " ";
            }
            if (recruitmentAction.getConsult_City().trim().isEmpty() == false) {
                queryString = queryString + " AND ca.city LIKE '%" + recruitmentAction.getConsult_City().trim() + "%' ";
            }
            queryString = queryString + " LIMIT 100";

            System.out.println("queryString helloooo -->" + queryString);
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
                //cons.setConsult_skill(resultSet.getString("consultant_skills"));
//                String str = resultSet.getString("consultant_skills");
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                // List list = new ArrayList();
//                String skillsResultString = "";
//                while (stk.hasMoreTokens()) {
//                    skillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//                }
//                cons.setConsult_skill(StringUtils.chop(skillsResultString));
                cons.setConsult_skill(resultSet.getString("consultant_skills"));
                cons.setConsult_salary(resultSet.getString("rate_salary"));
                cons.setConsult_status(resultSet.getString("cur_status"));
                System.out.println("div" + resultSet.getString("phone1") + resultSet.getString("email1") + resultSet.getString("name") + resultSet.getString("consultant_skills"));
                conslist.add(cons);
                joined = cons.getConsult_name() + "|" + cons.getConsult_email() + "|" + cons.getConsult_skill() + "|" + cons.getConsult_salary() + "|" + cons.getConsult_phno() + "|" + cons.getConsult_status() + "^";
                resultantString += joined;
                System.out.println(conslist.size() + cons.getConsult_email() + cons.getConsult_name());
                System.out.println(conslist.size() + recruitmentAction.getConsult_email() + cons.getConsult_name());


            }
            recruitmentAction.setGridPDFDownload(resultantString);
            // System.out.println("----------->" + leaveslist);
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

        return conslist;
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
    public String getRequirementDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {


        ArrayList<ConsultantVTO> requirementList = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "", resultString = "";
        int i = 0;
        //System.err.println(days+"Diff in Dyas...");
        try {

            queryString = "SELECT requirement_id,req_name,no_of_resources,req_skills,req_st_date,req_status "
                    + "FROM acc_requirements WHERE acc_id=" + recruitmentAction.getAccountSearchID();

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("requirement_id") + " " + resultSet.getString("req_name") + " " + resultSet.getInt("no_of_resources") + " " + resultSet.getString("req_st_date") + " " + resultSet.getString("req_status") + " " + resultSet.getString("req_skills"));
                resultString += resultSet.getInt("requirement_id") + "|" + resultSet.getString("req_name") + "|" + resultSet.getInt("no_of_resources") + "|" + resultSet.getString("req_st_date") + "|" + resultSet.getString("req_status") + "|" + resultSet.getString("req_skills") + "^";
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
     * @getAllRequirementList() method is used to get Requirement details of
     * account
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:05/07/2015
     *
     **************************************
     */
    public List getAllRequirementList(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) {
        ArrayList<RequirementListVTO> list = new ArrayList<RequirementListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            // if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {
            if (typeofusr.equalsIgnoreCase("VC")) {
                queryString = "SELECT jdid,requirement_id,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,req_created_by FROM acc_requirements LEFT OUTER JOIN req_ven_rel  ON requirement_id=req_id WHERE ven_id=" + recruitmentAction.getSessionOrgId() + " AND  NOW() >= req_access_time AND  STATUS LIKE 'Active' AND  req_status LIKE 'R' ";
            } else {
                queryString = "SELECT * "
                        + "FROM acc_requirements where req_status IN ('o','R','C') and acc_id=" + recruitmentAction.getSessionOrgId();
            }
            queryString += " order by req_name,FIELD(req_status,'O','R','C'),req_created_date desc LIMIT 100";
            System.out.println("queryString-->" + queryString);
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
                requirementListVTO.setReq_contact1(resultSet.getString("req_contact1"));
                requirementListVTO.setReq_contact2(resultSet.getString("req_contact2"));
                requirementListVTO.setReqContactName1(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")));
                requirementListVTO.setReqContactName2(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")));
                requirementListVTO.setCreatedByName(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("req_created_by")));
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

        return list;
    }

    /**
     * *****************************************************************************
     * Date : May 5 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * doAddConsultantDetails() method is used to enter the consultant record
     * into database using stored procedure
     *
     * *****************************************************************************
     */
    public int doAddConsultantDetails(RecruitmentAction recruitmentAction, int orgId) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();
        String availableDate, dobDate;

        //Add for Relocation
        String consult_relocation = recruitmentAction.getConsult_relocation();
        String str = recruitmentAction.getSkillValues();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String reqSkillsResultString = "";
        while (stk.hasMoreTokens()) {
            reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
        }
        recruitmentAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
        System.out.println("reqSkillsResultString---------->" + reqSkillsResultString);

        try {
            if (!"".equals(recruitmentAction.getConsult_add_date())) {
                System.out.println("parsing");
                availableDate = dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_add_date());
            } else {
                System.out.println("without parsing");
                availableDate = null;
            }
            dobDate = dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_dob());
            System.out.println("---------------------In Impl class------------");
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL addConsultant(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("ORGID--------->>>>>>" + orgId);
            callableStatement.setInt(1, orgId);
            callableStatement.setString(2, recruitmentAction.getConsult_email());
            callableStatement.setString(3, availableDate);
            callableStatement.setString(4, recruitmentAction.getConsult_available());
            callableStatement.setString(5, recruitmentAction.getConsult_fstname());
            callableStatement.setString(6, recruitmentAction.getConsult_lstname());
            callableStatement.setString(7, recruitmentAction.getConsult_midname());
            // callableStatement.setString(8, recruitmentAction.getConsult_gender());
            callableStatement.setString(8, dobDate);
            // callableStatement.setString(10, recruitmentAction.getConsult_mStatus());
            callableStatement.setString(9, recruitmentAction.getConsult_homePhone());
            callableStatement.setString(10, recruitmentAction.getConsult_mobileNo());
            callableStatement.setInt(11, recruitmentAction.getConsult_lcountry());
            // callableStatement.setString(14,recruitmentAction.getAddAddressFlag());
            callableStatement.setString(12, recruitmentAction.getConsult_Address());
            callableStatement.setString(13, recruitmentAction.getConsult_Address2());
            callableStatement.setString(14, recruitmentAction.getConsult_City());
            callableStatement.setString(15, recruitmentAction.getConsult_Country());
            callableStatement.setInt(16, recruitmentAction.getConsult_State());
            callableStatement.setString(17, recruitmentAction.getConsult_Zip());
            //  callableStatement.setString(20, recruitmentAction.getConsult_Phone());
            callableStatement.setString(18, recruitmentAction.getAddress_flag());
            callableStatement.setString(19, recruitmentAction.getConsult_CAddress());
            callableStatement.setString(20, recruitmentAction.getConsult_CAddress2());
            callableStatement.setString(21, recruitmentAction.getConsult_CCity());
            callableStatement.setString(22, recruitmentAction.getConsult_CCountry());
            callableStatement.setInt(23, recruitmentAction.getConsult_CState());
            callableStatement.setString(24, recruitmentAction.getConsult_CZip());
            //  callableStatement.setString(28, recruitmentAction.getConsult_CPhone());

            callableStatement.setInt(25, recruitmentAction.getConsult_industry());
            //  callableStatement.setInt(30, recruitmentAction.getConsult_organization());
            callableStatement.setString(26, recruitmentAction.getConsult_jobTitle());

            callableStatement.setString(27, recruitmentAction.getConsult_salary());
            callableStatement.setInt(28, recruitmentAction.getConsult_experience());
            callableStatement.setString(29, recruitmentAction.getConsult_workPhone());

            callableStatement.setInt(30, recruitmentAction.getConsult_pcountry());
            //callableStatement.setInt(31, recruitmentAction.getConsult_preferredState());
            callableStatement.setString(31, recruitmentAction.getPrefstateValues());
            callableStatement.setInt(32, recruitmentAction.getConsult_wcountry());

            callableStatement.setString(33, recruitmentAction.getConsult_education());
            callableStatement.setString(34, recruitmentAction.getConsult_comments());
            callableStatement.setString(35, recruitmentAction.getConsult_referredBy());
            //  System.out.println("after 1st valueeeeeeeeeeeeeee");
            callableStatement.setString(36, recruitmentAction.getFileFileName());
            callableStatement.setString(37, recruitmentAction.getFilesPath());
            callableStatement.setInt(38, recruitmentAction.getUserSessionId());
            callableStatement.setString(39, recruitmentAction.getReqSkillSet());
            // callableStatement.setString(40, recruitmentAction.getConsult_position());
            callableStatement.setString(40, recruitmentAction.getConsult_alterEmail());
            callableStatement.setString(41, recruitmentAction.getConsult_ssnNo());
            callableStatement.setString(42, recruitmentAction.getConsult_linkedInId());
            callableStatement.setString(43, recruitmentAction.getConsult_facebookId());
            callableStatement.setString(44, recruitmentAction.getConsult_twitterId());
            if (consult_relocation.equalsIgnoreCase("yes")) {
                callableStatement.setString(45, consult_relocation);
            } else {
                callableStatement.setString(45, consult_relocation);
            }
            isExceute = callableStatement.execute();
            System.out.println("Execute=========>" + isExceute);
            addResult = callableStatement.getInt(46);
            if (addResult > 0) {
                System.out.println("****************** in impl result>0  after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
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
        return addResult;
    }

    /**
     * *************************************
     *
     * @ method is used to get loggedIn user task details and displays in
     * TaskSearch Grid
     *
     * @Author:triveni
     *
     * @Created Date:04/15/2015
     *
     **************************************
     */
    public ConsultantVTO getupdateConsultantDetails(RecruitmentAction recruitmentAction, Map map) throws ServiceLocatorException {

        ConsultantVTO consult = new ConsultantVTO();
        DateUtility dateUtility = new DateUtility();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        PreparedStatement preparedStatement = null;
        try {
//            queryString = "SELECT c.usr_id as usr_consultant_id,c.job_title,c.usr_industry,c.preffered_country,c.preffered_state,c.referred_by,c.experience,c.rate_salary,c.available_from,c.available,c.education,c.relocation,c.comments,c.ssn_number,"
//                    + "u.email1,u.email2,u.first_name,u.last_name,u.middle_name,u.dob,u.created_by_org_id,u.living_country,u.working_country,u.phone2,u.phone1,u.cur_status ,u.linkedin_link, u.facebook_link, u.twitter_link, c.consultant_skills,"
//                    + "a.address,a.city,a.state,a.zip,a.country,a.phone,a.address2,a.address_flag,a.STATUS FROM users u JOIN usr_details c ON (u.usr_id = c.usr_id) JOIN usr_address a ON "
//                    + "(u.usr_id = a.usr_id) WHERE u.usr_id = ? AND a.STATUS='ACTIVE'";

            queryString = "SELECT c.usr_id AS usr_consultant_id,c.job_title,c.usr_industry,c.preffered_country, "
                    + "c.preffered_state,c.referred_by,c.experience,c.rate_salary,c.available_from, "
                    + "c.available,c.education,c.relocation,c.comments,c.ssn_number,u.email1,u.email2, "
                    + "u.first_name,u.last_name,u.middle_name,u.dob,u.created_by_org_id,u.living_country, "
                    + "u.working_country,u.phone2,u.phone1,u.cur_status ,u.linkedin_link, u.facebook_link, "
                    + "u.twitter_link, c.consultant_skills,a.address,a.city,a.state,a.zip,a.country, "
                    + "a.phone,a.address2,a.address_flag,a.STATUS,ar.object_id, ar.acc_attachment_id, ar.object_type, ar.attachment_path, ar.attachment_name FROM users u JOIN usr_details c ON (u.usr_id = c.usr_id) "
                    + "JOIN usr_address a ON (u.usr_id = a.usr_id) JOIN acc_rec_attachment ar ON(ar.object_id=u.usr_id) WHERE u.usr_id = ? AND a.STATUS='ACTIVE' AND ar.STATUS='Active' ";
            //  queryString = queryString + " and usr_id=" + Id( + " LIMIT 20";

            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);

            preparedStatement.setInt(1, recruitmentAction.getConsult_id());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //   System.out.println("===In while===");
                consult.setConsult_id(resultSet.getInt("usr_consultant_id"));
                consult.setConsult_email(resultSet.getString("email1"));
                consult.setConsult_fstname(resultSet.getString("first_name"));
                consult.setConsult_lstname(resultSet.getString("last_name"));
                consult.setConsult_midname(resultSet.getString("middle_name"));
                if (resultSet.getString("dob") == null) {
                    System.out.println("dob " + resultSet.getString("dob"));
                    consult.setConsult_dob(resultSet.getString("dob"));
                } else {
                    consult.setConsult_dob(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("dob")));
                }
                //consult.setConsult_dob(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("dob")));
                // consult.setConsult_mStatus(resultSet.getString("marital_status"));
                //consult.setConsult_gender(resultSet.getString("gender"));
                consult.setConsult_lcountry(resultSet.getString("living_country"));
                consult.setConsult_wcountry(resultSet.getInt("working_country"));
                consult.setConsult_homePhone(resultSet.getString("phone2"));
                consult.setConsult_mobileNo(resultSet.getString("phone1"));
                consult.setConsult_linkedInId(resultSet.getString("linkedin_link"));
                consult.setConsult_facebookId(resultSet.getString("facebook_link"));
                consult.setConsult_twitterId(resultSet.getString("twitter_link"));


                // consult.setConsult_Address2(resultSet.getString("address_flag"));
                consult.setConsult_status(resultSet.getString("cur_status"));
                consult.setConsult_jobTitle(resultSet.getString("job_title"));
                consult.setConsult_industry(resultSet.getInt("usr_industry"));
                consult.setConsult_preferredCountry(resultSet.getInt("preffered_country"));
                //consult.setConsult_preferredState(resultSet.getInt("preffered_state"));
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
                    System.out.println("available date" + resultSet.getString("available_from"));
                    consult.setConsult_favail(resultSet.getString("available_from"));
                } else {
                    consult.setConsult_favail(dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("available_from")));
                }
                consult.setConsult_available(resultSet.getString("available"));
                consult.setConsult_education(resultSet.getString("education"));
                consult.setConsult_relocation(resultSet.getString("relocation"));
                consult.setConsult_skill(resultSet.getString("consultant_skills"));
                consult.setConsult_comments(resultSet.getString("comments"));
                //consult.setConsult_skill(resultSet.getString("consultant_skills"));
//                String str1 = resultSet.getString("consultant_skills");
//                StringTokenizer stk1 = new StringTokenizer(str1, ",");
//                List list1 = new ArrayList();
//
//                while (stk1.hasMoreTokens()) {
//                    list1.add(stk1.nextToken());
//                }
//              
//
//                consult.setSkillSetList(list1);
                String str1 = resultSet.getString("consultant_skills");
                StringTokenizer stk1 = new StringTokenizer(str1, ",");
                String reqSkillsResultString = "";
                List list1 = new ArrayList();
                while (stk1.hasMoreTokens()) {
                    list1.add(getKeyFromValue(map, stk1.nextToken()));
                    //  reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillId((stk1.nextToken())) + ",";
                }
                System.out.println("reqSkillsResultString----------->" + reqSkillsResultString);
                //  String str2 = reqSkillsResultString;
                //   StringTokenizer stk2 = new StringTokenizer(str2, ",");


//                while (stk2.hasMoreTokens()) {
//                    list1.add(stk2.nextToken());
//                }
                consult.setSkillSetList(list1);
                consult.setConsult_alterEmail(resultSet.getString("email2"));
                consult.setConsult_ssnNo(resultSet.getString("ssn_number"));
                // System.out.println("=====>>" + resultSet.getString("preffered_country") + resultSet.getInt("preffered_state"));

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
                    //   consult.setConsult_CPhone(resultSet.getString("phone"));
                    consult.setConsult_CAddress2(resultSet.getString("address2"));

                }
                if ("C".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    consult.setConsult_CAddress(resultSet.getString("address"));
                    consult.setConsult_CCity(resultSet.getString("city"));
                    consult.setConsult_CState(resultSet.getInt("state"));
                    consult.setConsult_CZip(resultSet.getString("zip"));
                    consult.setConsult_CCountry(resultSet.getInt("country"));
                    //   consult.setConsult_CPhone(resultSet.getString("phone"));
                    consult.setConsult_CAddress2(resultSet.getString("address2"));
                    consult.setAddress_flag("false");
                }
                if ("P".equalsIgnoreCase(resultSet.getString("address_flag"))) {
                    consult.setConsult_Address(resultSet.getString("address"));
                    consult.setConsult_City(resultSet.getString("city"));
                    consult.setConsult_State(resultSet.getInt("state"));
                    consult.setConsult_Zip(resultSet.getString("zip"));
                    consult.setConsult_Country(resultSet.getInt("country"));
                    //  consult.setConsult_Phone(resultSet.getString("phone"));
                    consult.setConsult_Address2(resultSet.getString("address2"));

                    consult.setAddress_flag("false");


                }



            }
            System.out.println("-----------> outside while....");
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

    public ConsultantVTO doupdateConsultantDetails(RecruitmentAction recruitmentAction, int userSessionId, int orgid) throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
        ConsultantVTO consult = new ConsultantVTO();
        CallableStatement callableStatement = null;

        boolean isExceute = false;
        int updatedRows = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        PreparedStatement preparedStatement = null;
        String consult_relocation = recruitmentAction.getConsult_relocation();
        String str = recruitmentAction.getSkillValues();
        StringTokenizer stk = new StringTokenizer(str, ",");
        String reqSkillsResultString = "";
        while (stk.hasMoreTokens()) {
            reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
        }
        recruitmentAction.setReqSkillSet(StringUtils.chop(reqSkillsResultString));
        System.out.println(" recruitmentAction.getReqSkillSet()---------->" + recruitmentAction.getReqSkillSet());
        try {


            //  queryString = queryString + " and usr_id=" + Id( + " LIMIT 20";

            //     System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{CALL updateConsultantDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            callableStatement.setString(1, recruitmentAction.getConsult_email());
            callableStatement.setString(2, recruitmentAction.getConsult_fstname());
            callableStatement.setString(3, recruitmentAction.getConsult_lstname());
            callableStatement.setString(4, recruitmentAction.getConsult_midname());
            //  callableStatement.setString(5, recruitmentAction.getConsult_gender());
            callableStatement.setString(5, dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_dob()));
            // callableStatement.setString(7, recruitmentAction.getConsult_mStatus());
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
            // callableStatement.setString(20, recruitmentAction.getConsult_Phone());

            callableStatement.setString(18, recruitmentAction.getConsult_CAddress());
            callableStatement.setString(19, recruitmentAction.getConsult_CAddress2());
            callableStatement.setString(20, recruitmentAction.getConsult_CCity());
            callableStatement.setString(21, recruitmentAction.getConsult_CCountry());
            callableStatement.setInt(22, recruitmentAction.getConsult_CState());
            callableStatement.setString(23, recruitmentAction.getConsult_CZip());
            //  callableStatement.setString(27, recruitmentAction.getConsult_CPhone());


            if ("".equals(recruitmentAction.getConsult_favail())) {
                System.out.println("consultant available null");
                callableStatement.setString(24, null);
            } else {
                System.out.println("consultant available not null");
                callableStatement.setString(24, dateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getConsult_favail()));

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
            // callableStatement.setTimestamp(36, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            callableStatement.setString(36, recruitmentAction.getReqSkillSet());
            callableStatement.setString(37, recruitmentAction.getConsult_status());
            callableStatement.setString(38, recruitmentAction.getConsult_alterEmail());
            callableStatement.setString(39, recruitmentAction.getConsult_ssnNo());
            callableStatement.setString(40, recruitmentAction.getConsult_referredBy());
            callableStatement.setString(41, recruitmentAction.getConsult_linkedInId());
            callableStatement.setString(42, recruitmentAction.getConsult_facebookId());
            callableStatement.setString(43, recruitmentAction.getConsult_twitterId());
            if (consult_relocation.equalsIgnoreCase("yes")) {
                callableStatement.setString(44, consult_relocation);
            } else {
                callableStatement.setString(44, consult_relocation);
            }
            System.out.println("after update==========>" + userSessionId + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());


            callableStatement.registerOutParameter(45, Types.INTEGER);
            //  System.out.println("hello here print after prepare call parameter ");

            isExceute = callableStatement.execute();
            updatedRows = callableStatement.getInt(45);
            if (updatedRows > 0) {
                recruitmentAction.setUpdateMessage("success");
            } else {
                recruitmentAction.setUpdateMessage("failure");
            }

            System.out.println("in impl==================================>>" + recruitmentAction.getConsult_CAddress() + recruitmentAction.getConsult_CCountry());
            System.out.println("<<==================================>>" + recruitmentAction.getConsult_industry() + recruitmentAction.getConsult_organization() + recruitmentAction.getConsult_experience());


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
        return consult;
    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * getDefaultConsultantListDetails() getting consultant list Default.
     *
     *
     * *****************************************************************************
     */
    public String getConsultantListDetails(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        String resultString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            // if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {
            if (typeofusr.equalsIgnoreCase("VC")) {
                /* queryString = " SELECT c.usr_id as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
                 + "c.phone1,acc_attachment_id,c.email1,rc.status,cd.consultant_skills,c.created_by_org_id,rc.rate_salary FROM users c LEFT OUTER JOIN "
                 + "usr_details cd ON (c.usr_id=cd.usr_id) LEFT OUTER JOIN req_con_rel rc"
                 + " ON (c.usr_id=rc.consultantId) LEFT OUTER JOIN acc_requirements ar "
                 + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "'  AND aat.STATUS='active' LIMIT 100";*/

                queryString = "SELECT Distinct(c.usr_id) AS consultant_id,"
                        + "CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME,c.phone1,aat.acc_attachment_id,"
                        + "c.email1,rc.STATUS,rc.con_skill AS consultant_skills,rc.createdbyOrgId as created_by_org_id,"
                        + "rc.rate_salary,r.acc_id "
                        + "FROM req_con_rel rc "
                        + "LEFT OUTER JOIN users c ON (rc.consultantId=c.usr_id) "
                        + "LEFT OUTER JOIN acc_requirements r ON (r.requirement_id=rc.reqId) "
                        + "LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=aat.object_id) ";
                queryString = queryString + " WHERE rc.reqId=" + recruitmentAction.getRequirementId() + " AND rc.createdbyOrgId=" + recruitmentAction.getSessionOrgId() + " AND  aat.STATUS LIKE 'Active' AND (aat.object_type='CV' OR aat.object_type='E') LIMIT 100";

                //queryString = queryString + "AND c.created_by_org_id=" + recruitmentAction.getSessionOrgId() + " LIMIT 100";
            } else {
                /* queryString = " SELECT c.usr_id as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
                 + "c.phone1,acc_attachment_id,c.email1,rc.status,cd.consultant_skills,c.created_by_org_id,rc.rate_salary FROM users c LEFT OUTER JOIN "
                 + "usr_details cd ON (c.usr_id=cd.usr_id) LEFT OUTER JOIN req_con_rel rc"
                 + " ON (c.usr_id=rc.consultantId) LEFT OUTER JOIN acc_requirements ar "
                 + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "' AND c.created_By=" + recruitmentAction.getUserSessionId() + "  AND aat.STATUS='active' LIMIT 100";*/
                queryString = "SELECT Distinct(c.usr_id) AS consultant_id,"
                        + "CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME,c.phone1,aat.acc_attachment_id,"
                        + "c.email1,rc.STATUS,rc.con_skill AS consultant_skills,rc.createdbyOrgId as created_by_org_id,"
                        + "rc.rate_salary,r.acc_id "
                        + "FROM req_con_rel rc "
                        + "LEFT OUTER JOIN users c ON (rc.consultantId=c.usr_id) "
                        + "LEFT OUTER JOIN acc_requirements r ON (r.requirement_id=rc.reqId) "
                        + "LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=aat.object_id) ";
                queryString = queryString + " WHERE  rc.reqId=" + recruitmentAction.getRequirementId() + "  AND  aat.STATUS LIKE 'Active' AND (aat.object_type='CV' OR aat.object_type='E') LIMIT 100";
            }

            System.out.println("query=========by nag=======" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("consultant_id") + "|" + resultSet.getString("name") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("consultant_skills") + "|" + resultSet.getString("phone1") + "|" + resultSet.getString("status") + "|" + resultSet.getString("acc_attachment_id") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")) + "|" + resultSet.getString("rate_salary") + "|" + resultSet.getInt("created_by_org_id") + "|" + resultSet.getInt("acc_id") + "^";
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
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * getConsultantListDetailsBySearch() getting consultant list by searching.
     *
     *
     * *****************************************************************************
     */
    public String searchConsultantListDetails(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        String resultString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //     if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            // if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {
            if (typeofusr.equalsIgnoreCase("VC")) {
                queryString = " SELECT Distinct(c.usr_id) as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
                        + "c.phone1,acc_attachment_id,c.email1,rc.status,rc.con_skill,rc.createdbyOrgId,rc.rate_salary FROM users c LEFT OUTER JOIN "
                        + "usr_details cd ON (c.usr_id=cd.usr_id) LEFT OUTER JOIN req_con_rel rc"
                        + " ON (c.usr_id=rc.consultantId) LEFT OUTER JOIN accounts a ON(a.account_id=rc.createdbyOrgId)"
                        + " LEFT OUTER JOIN acc_requirements ar "
                        + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "' AND aat.STATUS='active' AND (aat.object_type='CV' OR aat.object_type='E')";

                if (recruitmentAction.getVen_id() == 0) {
                    queryString = queryString + " AND rc.createdbyOrgId=" + recruitmentAction.getSessionOrgId() + " ";
                } else {
                    queryString = queryString + " AND rc.createdbyOrgId=" + recruitmentAction.getVen_id() + " ";
                }
            } else {
                queryString = " SELECT Distinct(c.usr_id) as consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
                        + "c.phone1,acc_attachment_id,c.email1,rc.status,rc.con_skill,rc.createdbyOrgId,rc.rate_salary FROM users c LEFT OUTER JOIN "
                        + "usr_details cd ON (c.usr_id=cd.usr_id) LEFT OUTER JOIN req_con_rel rc"
                        + " ON (c.usr_id=rc.consultantId) LEFT OUTER JOIN accounts a ON(a.account_id=rc.createdbyOrgId)"
                        + " LEFT OUTER JOIN acc_requirements ar "
                        + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.usr_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "' AND aat.STATUS='active' AND (aat.object_type='CV' OR aat.object_type='E')";
            }
            /* } else {
             queryString = " SELECT c.consultant_id,CONCAT_WS(' ',c.first_name,c.middle_name,c.last_name) AS NAME, "
             + "c.phone1,acc_attachment_id,c.email1,rc.status,cd.consultant_skills,c.created_by_org_id,rc.rate_salary FROM consultants c LEFT OUTER JOIN "
             + "consultant_details cd ON (c.consultant_id=cd.usr_consultant_id) LEFT OUTER JOIN req_con_rel rc"
             + " ON (c.consultant_id=rc.consultantId) LEFT OUTER JOIN accounts a ON(a.account_id=c.created_by_org_id)"
             + " LEFT OUTER JOIN acc_requirements ar "
             + "ON (requirement_id=reqId) LEFT OUTER JOIN acc_rec_attachment aat ON (c.consultant_id=object_id) WHERE reqId='" + recruitmentAction.getRequirementId() + "' and c.created_By=" + recruitmentAction.getUserSessionId() + "  AND aat.STATUS='active'";
             }*/

            if (recruitmentAction.getConsult_name().trim().isEmpty() == false) {
                queryString = queryString + " and (c.first_name like'%" + recruitmentAction.getConsult_name().trim() + "%' or c.middle_name like'%" + recruitmentAction.getConsult_name().trim() + "%' or c.last_name like'%" + recruitmentAction.getConsult_name().trim() + "%') ";
            }
            if (recruitmentAction.getConsult_email().trim().isEmpty() == false) {
                queryString = queryString + " and c.email1 like'%" + recruitmentAction.getConsult_email().trim() + "%' ";
            }

            if (recruitmentAction.getVendor().trim().isEmpty() == false) {
                queryString = queryString + " AND a.account_name LIKE '%" + recruitmentAction.getVendor().trim() + "%' ";
            }
            if (recruitmentAction.getConsult_phno().trim().isEmpty() == false) {
                queryString = queryString + " and c.phone1 like'%" + recruitmentAction.getConsult_phno().trim() + "%' ";

            }
            if (recruitmentAction.getConsult_skill().trim().isEmpty() == false) {
                queryString = queryString + " and rc.con_skill like'%" + recruitmentAction.getConsult_skill().trim() + "%' ";

            }

            queryString = queryString + " LIMIT 100";

            System.out.println("query================" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("consultant_id") + "|" + resultSet.getString("name") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("con_skill") + "|" + resultSet.getString("phone1") + "|" + resultSet.getString("status") + "|" + resultSet.getString("acc_attachment_id") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("createdbyOrgId")) + "|" + resultSet.getString("rate_salary") + "^";
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
     * @addAttachmentDetails() method is used to get task details and appends on
     * TaskEdit screen
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     * @Author:Triveni<tniddara@miraclesoft.com>
     * @Created Date:04/21/2015
     *
     *
     **************************************
     *
     */
    public int addConsultAttachmentDetails(RecruitmentAction recruitmentaction) {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPL EXECUTED %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(recruitmentaction.getConsultAttachmentFileName() + "=================>" + recruitmentaction.getFilePath());
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        try {
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");

            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("values in impl are:::::::::::" + recruitmentaction.getConsult_id() + " " + recruitmentaction.getConsultAttachmentFileName() + " " + recruitmentaction.getFilePath());
            String fpath = recruitmentaction.getFilePath();

//            StringTokenizer st = new StringTokenizer(fpath);
//            StringTokenizer st2 = new StringTokenizer(fpath, "\\");
//            String finalPath = "";
//            while (st2.hasMoreElements()) {
//                //System.out.println(".............>>>>>>>>>>>"+st2.nextElement());
//                finalPath = finalPath + st2.nextElement() + "\\" + "\\";
//            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + fpath);
            recruitmentaction.setFilePath(fpath);



            callableStatement = connection.prepareCall("{CALL addConsultantAttachment(?,?,?,?,?,?)}");
            callableStatement.setInt(1, recruitmentaction.getConsult_id());
            System.out.println("after 1st valueeeeeeeeeeeeeee");
            callableStatement.setString(2, "CV");
            callableStatement.setString(3, recruitmentaction.getConsultAttachmentFileName());
            callableStatement.setString(4, recruitmentaction.getFilePath());
            callableStatement.setInt(5, recruitmentaction.getUserSessionId());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(6);

//            queryString = "INSERT INTO acc_rec_attachment(object_id,object_type,attachment_name,attachment_path,STATUS,opp_created_by) VALUES(" + recruitmentaction.getConsult_id() + ",'CV'" + ",'" + recruitmentaction.getConsultAttachmentFileName() + "'" + ",'" + recruitmentaction.getFilePath() + "'" + ",'Active'," + recruitmentaction.getUserSessionId() + ")";
//            System.out.println("queryString-->" + queryString);
//
//            connection = ConnectionProvider.getInstance().getConnection();
//            statement = connection.createStatement();
//            addResult = statement.executeUpdate(queryString);
            System.out.println("****************** in impl result after adding:::::::::" + addResult);
            if (addResult > 0) {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
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
        return addResult;
    }

    public String getActivityDetails(int consultantId) throws ServiceLocatorException {

        ArrayList<UserActivity> activityList = new ArrayList<UserActivity>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        int i = 0;
        try {

            queryString = " SELECT actvity_id,object_id,object_type,activity_name,actvity_type,activity_relation,activity_created_date,"
                    + "activity_created_by,activity_desc,activity_comments,activity_status FROM acc_rec_activities WHERE activity_relation='acc' and object_id= " + consultantId;

            System.out.println("get details queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            //     System.out.println("After Connection");
            resultSet = statement.executeQuery(queryString);
            //    System.out.println("after statements ");
            //String createdby=dsdp.getFnameandLnamebyStringId("1003");
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

                // eduList.add(usersVTO);
                resultString += userActivity.getActivityId() + "|" + userActivity.getActivityName() + "|"
                        + userActivity.getObjectID() + "|" + userActivity.getObjectType() + "|"
                        + userActivity.getActivityType() + '|' + userActivity.getActivityRelation() + '|'
                        + dsdp.getFnameandLnamebyUserid(Integer.parseInt(userActivity.getActivityCratedBy())) + '|' + userActivity.getActivityDesc() + '|'
                        + userActivity.getActivityComments() + '|' + userActivity.getActivityStatus() + '^';
                //   System.out.println("---------> skill result table"+resultString);

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
        return resultString;

    }

    /**
     * *************************************
     *
     * @InsertActivityDetails() method for Inserting activity details
     *
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/01/2015
     *
     *
     **************************************
     *
     */
    public int doAddConsultantActivityDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException {

        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultInt = 0;
        //List consultActivityList;
        try {
            //System.err.println(days+"Diff in Dyas...");
            int i = 0;
            System.out.println("getConsult_id" + recruitmentAction.getConsult_id());
            System.out.println("activity relation" + recruitmentAction.getActivityRelation());
            //queryString = INSERT INTO servicebay.acc_rec_activities (acc_id,activity_name,actvity_type,activity_created_by,activity_desc,activity_comments,  activity_status) VALUES (10001,'Testing Activity','call - outbound',1003,'Mail sent ','Mail Test', 'Not start');
            queryString = "insert into acc_rec_activities(object_id,object_type,activity_name,actvity_type,activity_relation,activity_created_by,activity_desc,activity_comments,activity_status) values (?,?,?,?,?,?,?,?,?)";
            System.out.println("doAddConsultantActivityDetails-------->" + queryString);
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
            //preparedStatement.setString(10, recruitmentAction.getActivityComments());
            //preparedStatement.setString(11, recruitmentAction.getActivityComments());

            resultInt = preparedStatement.executeUpdate();
            //consultActivityList=this.getConsultantActivityDetails();
            System.out.println("resultInt---->" + resultInt);

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
        return resultInt;

    }

    /*
     * *************************************
     *
     * @doEditConsultantActivityDetails() method for updating Activity details
     *
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/01/2015
     *
     *
     **************************************
     */
    public int doEditConsultantActivityDetails(RecruitmentAction recruitmentAction) {
        ArrayList consultActivitylist = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultInt = 0;
        int i = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            // System.out.println("getConsult_id" + recruitmentAction.getConsult_id());
            System.out.println("activity ID is" + recruitmentAction.getActivityId());
            System.out.println("activity_name" + recruitmentAction.getActivityName());
            System.out.println("actvity_type" + recruitmentAction.getActivityType());
            System.out.println("activity_desc is" + recruitmentAction.getActivityDesc());
            System.out.println("activity_comments" + recruitmentAction.getActivityComments());
            //queryString = INSERT INTO servicebay.acc_rec_activities (acc_id,activity_name,actvity_type,activity_created_by,activity_desc,activity_comments,  activity_status) VALUES (10001,'Testing Activity','call - outbound',1003,'Mail sent ','Mail Test', 'Not start');
            queryString = "UPDATE acc_rec_activities SET activity_name = ?,actvity_type = ?,"
                    + "activity_relation = ?, activity_created_by = ?, activity_desc = ?, "
                    + "activity_comments = ? ,activity_status = ?, modified_by=?, modified_date=?  WHERE actvity_id = ? ";
            System.out.println(queryString);
            preparedStatement = connection.prepareStatement(queryString);
            //preparedStatement.setInt(1, recruitmentAction.getOrgid());
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

            System.out.println("resultInt---->" + resultInt);




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
        return resultInt;
    }
    /*
     * *************************************
     *
     * @getConsultantActivityDetailsbyActivityId() method for getting Activity by activity id 
     *
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/01/2015
     *
     *
     **************************************
     */

    public String getConsultantActivityDetailsbyActivityId(RecruitmentAction recruitmentAction) {
        ArrayList consultActivitylist = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultMsg = "";
        int i = 0;

        try {

            System.out.println(" Activity id In getConsultantActivityDetailsbyActivityId-->" + recruitmentAction.getActivityId());
            //queryString = "SELECT t.task_id,t.task_name,t.task_created_date,t.task_comments,t.task_status,u.usr_id FROM task_list t LEFT OUTER JOIN users u  ON(t.task_created_by=u.usr_id) WHERE 1=1 and task_status LIKE 'Active' ";
            queryString = "SELECT actvity_id,activity_name,actvity_type,activity_created_date,activity_desc,activity_comments,activity_status,activity_relation FROM acc_rec_activities where actvity_id=" + recruitmentAction.getActivityId();


            System.out.println("queryString in getConsultantActivityDetailsbyActivityId-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);



            while (resultSet.next()) {
                resultMsg += resultSet.getInt("actvity_id") + "|" + resultSet.getString("activity_name") + "|"
                        + resultSet.getString("actvity_type") + "|" + resultSet.getString("activity_created_date") + "|"
                        + resultSet.getString("activity_desc") + "|" + resultSet.getString("activity_comments") + "|"
                        + resultSet.getString("activity_status") + "|" + resultSet.getString("activity_relation");

//                ConsultantVTO consultantVTO = new ConsultantVTO();
//                consultantVTO.setConsult_activityId(resultSet.getInt("actvity_id"));
//                consultantVTO.setConsult_id(recruitmentAction.getConsult_id());
//                consultantVTO.setConsult_activityName(resultSet.getString("activity_name"));
//                consultantVTO.setConsult_activityType(resultSet.getString("actvity_type"));
//                consultantVTO.setConsult_activityCratedDate(resultSet.getString("activity_created_date"));
//                consultantVTO.setConsult_activityDesc(resultSet.getString("activity_desc"));
//                consultantVTO.setConsult_activityComments(resultSet.getString("activity_comments"));
//                consultantVTO.setConsult_activityStatus(resultSet.getString("activity_status"));
//                consultantVTO.setConsult_activityRelation(resultSet.getString("activity_relation"));
//                consultActivitylist.add(consultantVTO);
                //recruitmentAction.setActivityId(resultSet.getInt("actvity_id"));
            }

            // System.out.println("resultMsg ---------->" + resultMsg);
            //System.out.println("setActivityID In Impl ------------->" + recruitmentAction.getActivityId());

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
        ArrayList<RequirementListVTO> list = new ArrayList<RequirementListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String joined = "";
        String resultantString = "";
        try {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            int sessionusrPrimaryrole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
            // if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {
            if (typeofusr.equalsIgnoreCase("VC")) {
                joined = "Job Id" + "|" + "Jog Title" + "|" + "Customer" + "|" + "Skills Set" + "|" + "Status" + "^"; // for grid download
                resultantString = joined;
                queryString = "SELECT tax_term,req_type,target_rate,jdid,requirement_id,req_name,no_of_resources,req_skills,preferred_skills,req_st_date,req_status,req_contact1,req_contact2,created_by_org_id,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel  ON requirement_id=req_id "
                        + "WHERE ven_id=" + recruitmentAction.getSessionOrgId() + " AND  NOW() >= req_access_time AND  STATUS LIKE 'Active' "
                        + "AND  req_status LIKE 'R' ";
                if (sessionusrPrimaryrole != 3 && sessionusrPrimaryrole != 2 && sessionusrPrimaryrole != 13 && sessionusrPrimaryrole != 1) {
                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST) != null) {
                        queryString += " and req_category IN (" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST).toString() + ") ";
                    }
                }
                queryString = queryString + " order by req_name,FIELD(req_status,'O','R','C','F','I','H','W','S','L'),req_created_date desc LIMIT 100";

            } else {
                joined = "Job Id" + "|" + "Jog Title" + "|" + "Positions" + "|" + "Skills Set" + "|" + "Approver" + "|" + "Requisitioner" + "|" + "Status" + "^"; // for grid download
                resultantString = joined;
                // queryString = "SELECT * FROM acc_requirements WHERE 1=1 AND (req_contact1=" + recruitmentAction.getUserSessionId() + " OR req_contact2=" + recruitmentAction.getUserSessionId() + " or req_created_by=" + recruitmentAction.getUserSessionId() + ") and acc_id=" + recruitmentAction.getSessionOrgId() + " order by req_name,FIELD(req_status,'O','R','C'),req_created_date desc LIMIT 100";
                if (recruitmentAction.getTypeOfUser() == 3) {
                    queryString = "SELECT * FROM acc_requirements WHERE 1=1 AND  req_created_by=" + recruitmentAction.getUserSessionId() + " and acc_id=" + recruitmentAction.getSessionOrgId() + " order by req_name,FIELD(req_status,'O','R','C'),req_created_date desc LIMIT 100";
                } else if (recruitmentAction.getTypeOfUser() == 13 || recruitmentAction.getTypeOfUser() == 2) {
                    queryString = "SELECT * FROM acc_requirements WHERE 1=1 AND acc_id=" + recruitmentAction.getSessionOrgId() + " order by req_name,FIELD(req_status,'O','R','C','F','I','H','W','S','L'),req_created_date desc LIMIT 100";
                } else {
                    queryString = "SELECT * FROM acc_requirements  WHERE 1=1 "
                            + "and acc_id=" + recruitmentAction.getSessionOrgId();

                    if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST) != null) {
                        queryString += " and req_category IN (" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_CATEGORY_LIST).toString() + ") ";
                    }
                    queryString = queryString + " order by req_name,FIELD(req_status,'O','R','C','F','I','H','W','S','L'),req_created_date desc LIMIT 100";
                }
            }

            System.out.println("queryString     11-->" + queryString);
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

                String preSkills = resultSet.getString("preferred_skills");
                System.out.println("preferred_skills" + preSkills);
//                String str = resultSet.getString("req_skills");
//                StringTokenizer stk = new StringTokenizer(str, ",");
//                String reqSkillsResultString = "";
//                while (stk.hasMoreTokens()) {
//                    reqSkillsResultString += com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(stk.nextToken()));
//                }
                requirementListVTO.setReqSkillSet(resultSet.getString("req_skills"));
                requirementListVTO.setPreSkillSet(preSkills.replaceAll("<br/>", ","));
                //   requirementListVTO.setReqSkillSet(reqSkills.replaceAll("<br/>", ","));

                // requirementListVTO.setPreSkillSet(resultSet.getString("preferred_skills"));
                // requirementListVTO.setReqSkillSet(resultSet.getString("req_skills"));
                requirementListVTO.setStartDate(resultSet.getString("req_st_date"));
                requirementListVTO.setTargetRate(resultSet.getString("target_rate"));
                requirementListVTO.setTaxTerm(resultSet.getString("tax_term")); //reqType

                requirementListVTO.setStatus(status);
                requirementListVTO.setReq_contact1(resultSet.getString("req_contact1"));
                requirementListVTO.setReq_contact2(resultSet.getString("req_contact2"));
                requirementListVTO.setReqContactName1(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact1")));
                requirementListVTO.setReqContactName2(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyStringId(resultSet.getString("req_contact2")));
                requirementListVTO.setCustomerName(com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(resultSet.getInt("created_by_org_id")));
                requirementListVTO.setRequirementMaxRate(resultSet.getString("max_rate"));
                list.add(requirementListVTO);
                if (typeofusr.equalsIgnoreCase("VC")) {
                   joined = requirementListVTO.getJdId() + "|" + requirementListVTO.getTitle() + "|" + requirementListVTO.getCustomerName() + "|" + requirementListVTO.getReqSkillSet()  + "|" + requirementListVTO.getStatus() + "^";
                resultantString += joined; 
                }
                else{
                    joined = requirementListVTO.getJdId() + "|" + requirementListVTO.getTitle() + "|" + requirementListVTO.getNoOfPosition() + "|" + requirementListVTO.getReqSkillSet() + "|" + requirementListVTO.getReqContactName1() + "|" + requirementListVTO.getReqContactName2() + "|" + requirementListVTO.getStatus() + "^";
                resultantString += joined;
                }
            }
            recruitmentAction.setGridPDFDownload(resultantString);

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

        return list;
    }

    public List getCurrentStatus(RecruitmentAction recruitmentAction) {

        ArrayList<ConsultantListVTO> conslist = new ArrayList<ConsultantListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        DateUtility dateUtility = new DateUtility();
        try {

            queryString = "SELECT * FROM acc_rec_attachment WHERE object_type='CV' AND object_id=" + recruitmentAction.getUserSessionId() + " AND status='Active' ORDER BY STATUS LIMIT 10";

            System.out.println("queryString helloooo -->" + queryString);
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
            // System.out.println("----------->" + leaveslist);
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


        return conslist;
    }

    public List getConsultantStatus(RecruitmentAction recruitmentAction) {
        ArrayList<ConsultantListVTO> consStatus = new ArrayList<ConsultantListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        try {

            queryString = "SELECT rcl.reqId, ar.req_name, ar.req_skills, rcl.status,rcl.applied_date FROM req_con_rel rcl JOIN acc_requirements ar ON(rcl.reqId = ar.requirement_id) WHERE consultantId=" + recruitmentAction.getUserSessionId() + " ORDER BY STATUS LIMIT 10";

            System.out.println("queryString helloooo -->" + queryString);
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
            System.out.println("----------->" + consStatus);
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
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPL EXECUTED %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();
        Connection connection1 = null;
        //System.err.println(days+"Diff in Dyas...");
        String interviewDate = "";
        String reviewAlertDate = "";
        try {
            //interviewDate = dateUtility.getInstance().convertStringToMySQLDate(recruitmentAction.getInterviewDate());
            //reviewAlertDate = dateUtility.getInstance().convertStringToMySQLDate(recruitmentAction.getReviewAlertDate());

            connection = ConnectionProvider.getInstance().getConnection();

            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL techReviewForward(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, recruitmentAction.getRequirementId());
            //System.out.println("after 1st valueeeeeeeeeeeeeee");
            callableStatement.setInt(2, recruitmentAction.getConsult_id());
            callableStatement.setInt(3, recruitmentAction.getEmpIdTechReview());
            //System.out.println("after date valueeeeeeeeeeeeeee" + recruitmentAction.getInterviewDate()+"DATE");
            if (!"".equals(recruitmentAction.getInterviewDate())) {
                System.out.println("if");
                callableStatement.setString(4, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDashWithTimeWithOutSeconds((recruitmentAction.getInterviewDate())));
            } else {
                System.out.println("else");
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
                //System.out.println("if question");   
                callableStatement.setString(13, recruitmentAction.getTechReviewQuestions());
            } else {
                //System.out.println("else question");       
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


            callableStatement.registerOutParameter(28, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(28);

            if (addResult > 0) {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);

            } else {
                System.out.println("****************** in impl result after adding fail:::::::::" + addResult);
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
                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
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
        ArrayList<ConsultantVTO> list = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,ct.forwarded_by,ct.forwarded_date,CONCAT(u.first_name,'.',u.last_name)AS fbyName,c.email1,c.phone1,c.usr_id,r.acc_attachment_id,ct.status,ct.req_id,ct.review_type,ct.id,c.office_phone,ct.scheduled_date,ct.scheduled_time,ct.zone  "
                    + "FROM con_techreview ct "
                    + "LEFT OUTER JOIN users c ON(c.usr_id=ct.consultant_id) "
                    + "LEFT OUTER JOIN acc_rec_attachment r ON(r.object_id=c.usr_id) "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=ct.forwarded_by) "
                    + "WHERE ct.forwarded_to=" + recruitmentAction.getUserSessionId() + " AND r.STATUS='active' ORDER BY forwarded_date  DESC LIMIT 50";

            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                ConsultantVTO consultantVTO = new ConsultantVTO();
                consultantVTO.setConsult_id(resultSet.getInt("usr_id"));
                consultantVTO.setConsult_name(resultSet.getString("NAME"));
                // consultantVTO.setForwardedDate(com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("forwarded_date")));
                consultantVTO.setForwardedBy(resultSet.getString("fbyName"));
                consultantVTO.setConsult_email(resultSet.getString("email1"));
                consultantVTO.setResumeId(resultSet.getString("acc_attachment_id"));
                consultantVTO.setConsult_Phone(resultSet.getString("phone1"));
                consultantVTO.setForwardedById(resultSet.getInt("forwarded_by"));
                consultantVTO.setStatus(resultSet.getString("status"));
                consultantVTO.setRequirementId(resultSet.getInt("req_id"));
                consultantVTO.setReviewType(resultSet.getString("review_type"));
                consultantVTO.setConTechReviewId(resultSet.getInt("id"));
                consultantVTO.setScheduledDate(com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("scheduled_date")));
                consultantVTO.setScheduledTime(resultSet.getString("scheduled_time"));
                consultantVTO.setZone(resultSet.getString("zone"));

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

        return list;
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
    public String getSearchTechReviewList(RecruitmentAction recruitmentAction) throws ServiceLocatorException {
        DateUtility dateUtility = new DateUtility();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String startdate = "";
        String endDate = "";

        try {
            queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,ct.forwarded_date,ct.forwarded_by,"
                    + "CONCAT(u.first_name,'.',u.last_name)AS fbyName,c.email1,c.phone1,"
                    + "c.usr_id,r.acc_attachment_id,ct.status,ct.req_id,ct.review_type,ct.id,c.office_phone,ct.scheduled_date,ct.scheduled_time,ct.zone "
                    + "FROM con_techreview ct "
                    + "LEFT OUTER JOIN users c ON(c.usr_id=ct.consultant_id)"
                    + "LEFT OUTER JOIN acc_rec_attachment r ON(r.object_id=c.usr_id) "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=ct.forwarded_by) "
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
            System.out.println(">>>>>>>>>>>>>>>>>query------------1>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("usr_id") + "|" + resultSet.getString("NAME") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("phone1") + "|" + resultSet.getString("fbyName") + "|" + dateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("forwarded_date")) + "|" + resultSet.getString("acc_attachment_id") + "|" + resultSet.getString("forwarded_by") + "|" + resultSet.getString("status") + "|" + resultSet.getString("req_id") + "|" + resultSet.getString("ct.review_type") + "|" + resultSet.getInt("ct.id") + "|" + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("scheduled_date")) + "|" + resultSet.getString("scheduled_time") + "|" + resultSet.getString("zone") + "^";
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
        return resultString;
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
    public String getConsultDetailsOnOverlay(RecruitmentAction recruitmentAction) throws ServiceLocatorException {

        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String jobTitle = " ";
        String conSkills = " ";
        try {
            queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,c.email1,c.phone1,cd.job_title,rcr.con_skill,"
                    + "cr.scheduled_date,ar.acc_attachment_id,cr.review_type, cr.tech_skills, cr.domain_skills, cr.commmunication_skills, cr.comments "
                    + "FROM users c LEFT OUTER JOIN con_techreview cr ON(cr.consultant_id=c.usr_id) "
                    + "LEFT OUTER JOIN acc_rec_attachment ar ON(ar.object_id=c.usr_id) "
                    + "LEFT OUTER JOIN usr_details cd ON(cd.usr_id=c.usr_id) "
                    //+ "LEFT OUTER JOIN usr_miscellaneous um ON(um.usr_id=" + recruitmentAction.getUserSessionId() + ") "
                    // + "LEFT OUTER JOIN title t ON(t.title_id=um.title_id) "
                    + "LEFT OUTER JOIN req_con_rel rcr ON(rcr.consultantId=c.usr_id)"
                    + "WHERE c.usr_id=" + recruitmentAction.getConsultantId() + " AND ar.STATUS='active' AND cr.id=" + recruitmentAction.getContechId() + " AND rcr.reqId=" + recruitmentAction.getReq_Id();

            System.out.println("query================>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultString = resultSet.getString("NAME") + "|" + resultSet.getString("email1") + "|" + resultSet.getString("phone1") + "|" + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("scheduled_date")) + "|" + resultSet.getString("acc_attachment_id") + "|" + resultSet.getString("job_title") + "|" + resultSet.getString("con_skill") + "|" + resultSet.getString("review_type") + "|" + resultSet.getString("tech_skills") + "|" + resultSet.getString("domain_skills") + "|" + resultSet.getString("commmunication_skills") + "|" + resultSet.getString("cr.comments") + "^";
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
        return resultString;
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
    public int saveTechReviewResults(RecruitmentAction recruitmentAction) throws ServiceLocatorException {

        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int resultString = 0;
        try {
//            queryString = "UPDATE con_techreview SET tech_skills=" + recruitmentAction.getTechSkill() + " ,"
//                    + "domain_skills=" + recruitmentAction.getDomainSkill() + " ,"
//                    + "commmunication_skills=" + recruitmentAction.getComSkill() + " ,"
//                    + "rating=" + recruitmentAction.getRating() + " ,"
//                    + "comments='" + recruitmentAction.getConsultantComments() + "' ,"
//                    + "STATUS='" + recruitmentAction.getFinalTechReviewStatus() + "' ,"
//                    + "techie_title='" + recruitmentAction.getTechTitle() + "' "
//                    + "WHERE consultant_id=" + recruitmentAction.getConsultId() + "";


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
                    + "rc.modified_By='" + recruitmentAction.getUserSessionId() + "',"
                    + "rc.modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDate() + "',"
                    + "rc.tech_review_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDate() + "',"
                    + "rc.tech_review_by='" + recruitmentAction.getUserSessionId() + "' "
                    + "WHERE ct.consultant_id=" + recruitmentAction.getConsultId() + " AND ct.req_id=" + recruitmentAction.getRequirementId() + " "
                    + "AND rc.consultantId=" + recruitmentAction.getConsultId() + " AND rc.reqId=" + recruitmentAction.getRequirementId() + " "
                    + "AND ct.forwarded_to=" + recruitmentAction.getUserSessionId() + " AND ct.id=" + recruitmentAction.getContechId();

            System.out.println("query================>" + queryString);
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
        int deletedRows = 0;
        Connection connection = null;
        Statement statement = null;
        String queryString = "update acc_rec_attachment set status='In-Active' WHERE acc_attachment_id=" + recruitmentAction.getAcc_attachment_id();
        System.out.println("Delete queryString" + queryString);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(queryString);

            System.out.println("delete rows------->" + deletedRows);
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
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPL EXECUTED %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(recruitmentaction.getFileFileName() + "=================>" + recruitmentaction.getFilePath());
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        try {
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");

            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("values in impl are:::::::::::" + recruitmentaction.getUserSessionId() + " " + recruitmentaction.getFileFileName() + " " + recruitmentaction.getFilePath());
            String fpath = recruitmentaction.getFilePath();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + fpath);
            recruitmentaction.setFilePath(fpath);
            callableStatement = connection.prepareCall("{CALL addConsultantAttachment(?,?,?,?,?,?)}");
            callableStatement.setInt(1, recruitmentaction.getUserSessionId());
            System.out.println("after 1st valueeeeeeeeeeeeeee");
            callableStatement.setString(2, "CV");
            callableStatement.setString(3, recruitmentaction.getFileFileName());
            callableStatement.setString(4, recruitmentaction.getFilePath());
            callableStatement.setInt(5, recruitmentaction.getUserSessionId());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            isExceute = callableStatement.execute();
            addResult = callableStatement.getInt(6);
            if (addResult > 0) {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
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
    public List getTechReviewSearchDetails(RecruitmentAction recruitmentAction) {
        ArrayList<ConsultantVTO> list = new ArrayList<ConsultantVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            // queryString = "SELECT forwarded_to_name1,forwarded_to1,id,review_type,forwarded_to,consultant_id,req_id,scheduled_date,forwarded_to_name,comments,techie_title,STATUS,rating FROM con_techreview WHERE consultant_id=" + recruitmentAction.getConsult_id() + " AND req_id=" + recruitmentAction.getRequirementId();
            queryString = "SELECT forwarded_to_name1,forwarded_to1,id,review_type,forwarded_to,consultant_id,req_id,scheduled_date,forwarded_to_name,comments,techie_title,STATUS,rating,forwarded_by,forwarded_date FROM con_techreview WHERE consultant_id=" + recruitmentAction.getConsult_id() + " AND req_id=" + recruitmentAction.getRequirementId();
            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                ConsultantVTO consultantVTO = new ConsultantVTO();

                if (resultSet.getString("scheduled_date") != null) {
                    //System.out.println("Scheduled Date if");     
                    consultantVTO.setDateOfReview(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("scheduled_date")));
                } else {
                    //System.out.println("Scheduled Date else");     
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
        int resultString = 0;
        System.out.println("in mirgaion impl");
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("before sp");
            callableStatement = connection.prepareCall("{call userMigration(?,?,?,?,?)}");
            System.out.println("in mirgaion impl" + recruitmentAction.getMigrationStatus() + "hjkgg" + recruitmentAction.getConsult_id());
            System.out.println("in mirgaion impl" + recruitmentAction.getUserSessionId() + "hjkgg" + recruitmentAction.getReq_Id());
            //callableStatement.setString(1,recruitmentAction.getMigrationStatus());
            callableStatement.setInt(1, recruitmentAction.getConsult_id());
            callableStatement.setInt(2, recruitmentAction.getUserSessionId());
            callableStatement.setInt(3, recruitmentAction.getReq_Id());
            System.out.println("in" + recruitmentAction.getOrgid() + "hjj" + recruitmentAction.getMigrationEmailId());
            // callableStatement.setInt(5,recruitmentAction.getOrgid());
            System.out.println("in" + recruitmentAction.getOrgid() + "hjj" + recruitmentAction.getMigrationEmailId());
            callableStatement.setString(4, recruitmentAction.getMigrationEmailId());
            callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
            System.out.println("fjkdddddddddddddddddddddddddddu");
            callableStatement.execute();
            resultString = callableStatement.getInt(5);
            System.out.println("fjkddddddddddddddddddddddddddd" + resultString);
            if (resultString > 0) {
                System.out.println("migration added sucessfully");
            } else {
                System.out.println("migration not added");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return resultString;
    }

    public int doAddVendorFormDetails(RecruitmentAction recruitmentAction, int orgId, String typeOfAccount) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();

        try {

            System.out.println("---------------------In Impl class------------");
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");

            System.out.println("recruitmentAction.getTypeOfUser()---------------->" + recruitmentAction.getTypeOfUser());
            System.out.println("recruitmentAction.getTypeOfUser()---------------->" + recruitmentAction.getTypeOfAccount());

            queryString = "INSERT INTO acc_rec_attachment(object_id,object_type,attachment_name,attachment_path,STATUS,opp_created_by,validity,attachment_title,comments) VALUES(?,?,?,?,?,?,?,?,?)";
            System.out.println("queryString PreparedStatement-->" + queryString);
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

            if (addResult > 0) {
                System.out.println("****************** in impl result>0  after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
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
        return addResult;
    }

    public int doUpdateVendorFormDetails(RecruitmentAction recruitmentAction, int attachment_id) throws ServiceLocatorException {
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int addResult = 0;
        boolean isExceute = false;
        DateUtility dateUtility = new DateUtility();

        try {

            System.out.println("---------------------In Impl class------------");
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");

            System.out.println("recruitmentAction.getTypeOfUser()---------------->" + recruitmentAction.getFileFileName());
            System.out.println("recruitmentAction.getTypeOfUser()---------------->" + recruitmentAction.getFilesPath());
            System.out.println("attachment_id---------------->" + recruitmentAction.getEditValidity());
            //  System.out.println("recruitmentAction.getTypeOfUser()---------------->"+recruitmentAction.getVendorDocs());
//            UPDATE DBUSER SET USERNAME = ? WHERE USER_ID = ?
            //queryString = "UPDATE acc_rec_attachment SET attachment_name=?,attachment_path=?,validity=?  WHERE acc_attachment_id=?";
            queryString = "UPDATE acc_rec_attachment SET validity=?,comments=?,attachment_title=?  WHERE acc_attachment_id=" + attachment_id;
            System.out.println("queryString PreparedStatement-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDateInDash(recruitmentAction.getEditValidity()));
            preparedStatement.setString(2, recruitmentAction.getEditattachmentComments());
            preparedStatement.setString(3, recruitmentAction.getEditTitle());

            //preparedStatement.setInt(4, attachment_id);




            addResult = preparedStatement.executeUpdate();

            if (addResult > 0) {
                System.out.println("****************** in impl result>0  after adding:::::::::" + addResult);
            } else {
                System.out.println("****************** in impl result after adding:::::::::" + addResult);
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
        return addResult;
    }
    public List getVendorRequirementsDashboard(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) {
        ArrayList<RequirementListVTO> list = new ArrayList<RequirementListVTO>();
        StringBuffer stringBuffer = new StringBuffer();
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            String typeofusr = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
            // if (!com.mss.msp.util.DataSourceDataProvider.getInstance().isVendor(recruitmentAction.getSessionOrgId())) {
            queryString = "SELECT acc_id,account_name,jdid,requirement_id,req_name,no_of_resources,target_rate,max_rate FROM accounts a JOIN acc_requirements ar ON ar.acc_id=a.account_id LEFT OUTER JOIN req_ven_rel r ON requirement_id=req_id WHERE ven_id=" + recruitmentAction.getOrgid() + " AND  NOW() >= req_access_time AND  r.STATUS LIKE 'Active' AND  ar.req_status LIKE 'R' order by requirement_id desc LIMIT 3 ";
            //  queryString = "SELECT jdid,requirement_id,req_name,no_of_resources,target_rate,max_rate FROM acc_requirements LEFT OUTER JOIN req_ven_rel  ON requirement_id=req_id WHERE ven_id=" + recruitmentAction.getOrgid() + " AND  NOW() >= req_access_time AND  STATUS LIKE 'Active' AND  req_status LIKE 'R' ";
            //  queryString += " ";
            System.out.println("queryString-->" + queryString);
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

        return list;
    }
}
