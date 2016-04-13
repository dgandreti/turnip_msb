/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.onlineexam;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.Blob;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author miracle
 */
public class OnlineExamServiceImpl implements OnlineExamService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTokenInfo() method is used to
     *
     *
     * *****************************************************************************
     */
    public void getTokenInfo(OnlineExamAction onlineExamAction) throws ServiceLocatorException {
        System.out.println("********************OnlineExamServiceImpl :: getTokenInfo Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT eid,reqid,consultantid,acctoken,validationkey,createdby,createddate,comments,totalquestions,qualifiedmarks,examstatus,severity,exam_topics FROM sb_onlineexam WHERE acctoken='" + onlineExamAction.getToken() + "'";
            System.out.println("getTokenInfo :: query string ------>" + queryString);
            onlineExamAction.setIsValid("INVALID");
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            int count = 0;
            while (resultSet.next()) {
                onlineExamAction.setConsultantId(resultSet.getInt("consultantid"));
                onlineExamAction.setExamStaus(resultSet.getString("examstatus"));
                onlineExamAction.setCreatedDate(resultSet.getString("createddate"));
                onlineExamAction.setConsultantName(DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("consultantid")));
                onlineExamAction.setExamTopics(resultSet.getString("exam_topics"));
                boolean isExpired = DataSourceDataProvider.getInstance().isExamExpired(onlineExamAction.getToken());
                onlineExamAction.setIsExpired(isExpired);
                count++;
            }
            if (count > 0) {
                onlineExamAction.setIsValid("VALID");
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
        System.out.println("********************OnlineExamServiceImpl :: getTokenInfo Method End*********************");
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getValidationInfo() method is used to
     *
     *
     * *****************************************************************************
     */
    public void getValidationInfo(OnlineExamAction onlineExamAction) throws ServiceLocatorException {

        System.out.println("********************OnlineExamServiceImpl :: getValidationInfo Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT eid,reqid,consultantid,acctoken,validationkey,createdby,createddate,comments,totalquestions,qualifiedmarks,examstatus,severity,exam_type,duration_ts,orgid,techreviewid FROM sb_onlineexam WHERE acctoken='" + onlineExamAction.getToken() + "'";
            System.out.println("getValidationInfo :: query string ------>" + queryString);
            onlineExamAction.setIsValid("INVALID");
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            int count = 0;
            while (resultSet.next()) {
                onlineExamAction.setConsultantId(resultSet.getInt("consultantid"));
                onlineExamAction.setTotalQuestions(resultSet.getInt("totalquestions"));
                onlineExamAction.setQualifiedMarks(resultSet.getInt("qualifiedmarks"));
                onlineExamAction.setRequirementId(resultSet.getInt("reqid"));
                onlineExamAction.setExamSeverity(resultSet.getString("severity"));
                onlineExamAction.setActualValidationToken(resultSet.getString("validationkey"));
                onlineExamAction.setActualConsultantEmailId(DataSourceDataProvider.getInstance().getEmailIdbyuser(resultSet.getInt("consultantid")));
                onlineExamAction.setExamType(resultSet.getString("exam_type"));
                onlineExamAction.setDurationTime(resultSet.getString("duration_ts"));
                onlineExamAction.setExamStaus(resultSet.getString("examstatus"));
                onlineExamAction.setOrgId(resultSet.getInt("orgid"));
                onlineExamAction.setConTechReviewId(resultSet.getInt("techreviewid"));
                onlineExamAction.setExamId(resultSet.getInt("eid"));
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
        System.out.println("********************OnlineExamServiceImpl :: getValidationInfo Method End*********************");
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getQuestions() method is used to
     *
     *
     * *****************************************************************************
     */
    public Map getQuestions(Map skillsMap, int totalQuestions, String level, String examType, int orgId) throws ServiceLocatorException {
        Map map = new TreeMap();
        QuestionVTO questionVTO = null;
        String key;
        int keyValue;
        int mapsize;
        int skillId = 0;
        int questions = 0;
        int noOfQuestions = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************OnlineExamServiceImpl :: getQuestions Method Start*********************");
        try {
            mapsize = skillsMap.size();
            if (mapsize > 0) {

                Iterator tempIterator = skillsMap.entrySet().iterator();
                queryString = "SELECT id,question,option1,option2,option3,option4,option5,option6,explanation,STATUS,LEVEL,skillid,orgid,question_type,ispictorial,question_path FROM sb_onlineexamQuestion WHERE skillid =? AND LEVEL='" + level + "' AND exam_type='" + examType + "' AND orgid =" + orgId + " AND STATUS='Active' ORDER BY RAND() ,createddate DESC  LIMIT ?";
                System.out.println("getQuestions :: query string ------>" + queryString);

                connection = ConnectionProvider.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(queryString);
                int i = 0;
                int skillCount = 0;
                while (tempIterator.hasNext()) {
                    skillCount++;
                    Map.Entry entry = (Map.Entry) tempIterator.next();
                    skillId = Integer.parseInt(entry.getKey().toString());
                    keyValue = Integer.parseInt(entry.getValue().toString());
                    preparedStatement.setInt(1, skillId);

                    preparedStatement.setInt(2, keyValue);

                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        i++;
                        questionVTO = new QuestionVTO();

                        questionVTO.setId(resultSet.getInt("id"));
                        questionVTO.setQuestion(resultSet.getString("question"));
                        questionVTO.setOption1(resultSet.getString("option1"));
                        questionVTO.setOption2(resultSet.getString("option2"));
                        questionVTO.setOption3(resultSet.getString("option3"));
                        questionVTO.setOption4(resultSet.getString("option4"));
                        questionVTO.setOption5(resultSet.getString("option5"));
                        questionVTO.setOption6(resultSet.getString("option6"));

                        questionVTO.setExplanation(resultSet.getString("explanation"));
                        questionVTO.setStatus(resultSet.getString("STATUS"));
                        questionVTO.setLevel(resultSet.getString("LEVEL"));
                        questionVTO.setSkillId(resultSet.getInt("skillid"));
                        questionVTO.setOrgId(resultSet.getInt("orgid"));
                        questionVTO.setTopicName(StringUtils.chop(DataSourceDataProvider.getInstance().getReqSkillsSet(skillId)));
                        questionVTO.setQuestionType(resultSet.getString("question_type"));
                        questionVTO.setIsPictorial(resultSet.getInt("ispictorial"));
                        if (!"null".equals(resultSet.getString("question_path"))) {
                            questionVTO.setQuestionPath(resultSet.getString("question_path"));
                        } else {
                            questionVTO.setQuestionPath(" ");
                        }
                        map.put(i, questionVTO);
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
        System.out.println("********************OnlineExamServiceImpl :: getQuestions Method End*********************");
        return map;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getOnlineExamKey() method is used to
     *
     *
     * *****************************************************************************
     */
    public int getOnlineExamKey(OnlineExamAction onlineExamAction) throws ServiceLocatorException {

        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        Connection connection = null;
        CallableStatement callableStatement = null;
        System.out.println("********************OnlineExamServiceImpl :: getOnlineExamKey Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL sponlineexamkey(?,?,?)}");
            System.out.println("getOnlineExamKey :: procedure name : sponlineexamkey");
            callableStatement.setInt(1, onlineExamAction.getConsultantId());
            callableStatement.setInt(2, onlineExamAction.getRequirementId());
            updatedRows = callableStatement.executeUpdate();
            addResult = callableStatement.getInt(3);
            if (addResult > 0) {
            } else {
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
        System.out.println("********************OnlineExamServiceImpl :: getOnlineExamKey Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : insertAnswer() method is used to
     *
     *
     * *****************************************************************************
     */
    public int insertAnswer(int examQuestionId, int ans1, int ans2, int ans3, int ans4, int ans5, int ans6, int consultantId, int examKey, int skiiId, int reqId, String status, int examId) throws ServiceLocatorException {

        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        String queryString = "";
        System.out.println("********************OnlineExamServiceImpl :: insertAnswer Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "INSERT INTO sb_onlineexamsummery(reqid,consultantid,questionid,ans1,ans2,ans3,ans4,ans5,ans6,examkey,ansstatus,examid,skillid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            System.out.println("insertAnswer :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, reqId);
            preparedStatement.setInt(2, consultantId);
            preparedStatement.setInt(3, examQuestionId);
            preparedStatement.setInt(4, ans1);
            preparedStatement.setInt(5, ans2);
            preparedStatement.setInt(6, ans3);
            preparedStatement.setInt(7, ans4);
            preparedStatement.setInt(8, ans5);
            preparedStatement.setInt(9, ans6);
            preparedStatement.setInt(10, examKey);
            preparedStatement.setString(11, status);
            preparedStatement.setInt(12, examId);
            preparedStatement.setInt(13, skiiId);
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
        System.out.println("********************OnlineExamServiceImpl :: insertAnswer Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : updateConsultantStatusTechReview() method is used to
     *
     *
     * *****************************************************************************
     */
    public int updateConsultantStatusTechReview(int contechReviewId, String techstatus, int examId, int totalQuestions) throws ServiceLocatorException {

        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        Connection connection = null;
        CallableStatement callableStatement = null;
        System.out.println("********************OnlineExamServiceImpl :: updateConsultantStatusTechReview Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL sp_onlineexamupdatestatus(?,?,?,?,?)}");
            System.out.println("updateConsultantStatusTechReview :: procedure name : sp_onlineexamupdatestatus");
            callableStatement.setInt(1, examId);
            callableStatement.setInt(2, totalQuestions);
            callableStatement.setInt(3, contechReviewId);
            callableStatement.setString(4, techstatus);
            updatedRows = callableStatement.executeUpdate();
            addResult = callableStatement.getInt(5);
            if (addResult > 0) {
            } else {
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
        System.out.println("********************OnlineExamServiceImpl :: updateConsultantStatusTechReview Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : updateExamStatus() method is used to
     *
     *
     * *****************************************************************************
     */
    public int updateExamStatus(int conTechReviewId, String examStatus) throws ServiceLocatorException {

        int addResult = 0;
        boolean isExceute = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        System.out.println("********************OnlineExamServiceImpl :: updateExamStatus Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE sb_onlineexam SET examstatus=? WHERE techreviewid=? ";

            System.out.println("updateExamStatus :: query string ------>" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, examStatus);
            preparedStatement.setInt(2, conTechReviewId);
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
        System.out.println("********************OnlineExamServiceImpl :: updateExamStatus Method End*********************");
        return addResult;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getExamResult() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getExamResult(int contechId, OnlineExamAction onlineExamAction) throws ServiceLocatorException {

        String resultString = "";
        String date = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String option1 = null, option2 = null, option3 = null, option4 = null, option5 = null, option6 = null, option7 = null, option8 = null, option9 = null, option10 = null;
        int noOfQuestion1 = 0, noOfQuestion2 = 0, noOfQuestion3 = 0, noOfQuestion4 = 0, noOfQuestion5 = 0, noOfQuestion6 = 0, noOfQuestion7 = 0, noOfQuestion8 = 0, noOfQuestion9 = 0, noOfQuestion10 = 0;
        int rightAns1 = 0, rightAns2 = 0, rightAns3 = 0, rightAns4 = 0, rightAns5 = 0, rightAns6 = 0, rightAns7 = 0, rightAns8 = 0, rightAns9 = 0, rightAns10 = 0;
        int totalRightAns = 0;
        System.out.println("********************OnlineExamServiceImpl :: getExamResult Method Start*********************");
        try {
            queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,so.techreviewid,so.consultantid,cr.id,cr.STATUS,so.option1,so.option2,so.option3,so.option4,so.option5,so.option6,so.option7,so.option8,so.option9,so.option10,c.email1,so.createddate,c.phone1,cd.job_title,so.eid,so.examstatus,se.completed_ts,so.totalquestions  FROM sb_onlineexam so"
                    + " LEFT OUTER JOIN con_techreview cr ON(so.techreviewid=cr.id)"
                    + " LEFT OUTER JOIN users c ON(c.usr_id=so.consultantid)"
                    + " LEFT OUTER JOIN usr_details cd ON(cd.usr_id=so.consultantid)"
                    + " LEFT OUTER JOIN sb_onlineexamsummery se ON(so.eid=se.examid)"
                    + " WHERE so.techreviewid=" + contechId + "";

            System.out.println("getExamResult :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {

                if (resultSet.last()) {
                    int eid = resultSet.getInt("so.eid");
                    option1 = resultSet.getString("option1");
                    if (!"".equals(option1)) {
                        String[] str1 = option1.split("-");

                        onlineExamAction.setSkillName1(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str1[0]))));
                        rightAns1 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str1[0]), eid);
                        totalRightAns = totalRightAns + rightAns1;
                        noOfQuestion1 = Integer.parseInt(str1[1]);
                        onlineExamAction.setSkillResult1(Math.round(((float) rightAns1 / (float) noOfQuestion1) * 100));
                    } else {
                        onlineExamAction.setSkillName1("no");
                    }
                    option2 = resultSet.getString("option2");
                    if (!"".equals(option2)) {
                        String[] str2 = option2.split("-");
                        onlineExamAction.setSkillName2((StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str2[0])))));
                        rightAns2 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str2[0]), eid);
                        totalRightAns = totalRightAns + rightAns2;
                        noOfQuestion2 = Integer.parseInt(str2[1]);
                        onlineExamAction.setSkillResult2(Math.round((((float) rightAns2 / (float) noOfQuestion2) * 100)));
                    } else {
                        onlineExamAction.setSkillName2("no");
                    }
                    option3 = resultSet.getString("option3");
                    if (!"".equals(option3)) {
                        String[] str3 = option3.split("-");
                        onlineExamAction.setSkillName3((StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str3[0])))));
                        rightAns3 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str3[0]), eid);
                        totalRightAns = totalRightAns + rightAns3;
                        noOfQuestion3 = Integer.parseInt(str3[1]);
                        onlineExamAction.setSkillResult3(Math.round(((float) rightAns3 / (float) noOfQuestion3) * 100));
                    } else {
                        onlineExamAction.setSkillName3("no");
                    }

                    option4 = resultSet.getString("option4");

                    if (!"".equals(option4)) {
                        String[] str4 = option4.split("-");
                        onlineExamAction.setSkillName4((StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str4[0])))));
                        rightAns4 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str4[0]), eid);
                        totalRightAns = totalRightAns + rightAns4;
                        noOfQuestion4 = Integer.parseInt(str4[1]);
                        onlineExamAction.setSkillResult4(Math.round(((float) rightAns4 / (float) noOfQuestion4) * 100));
                    } else {
                        onlineExamAction.setSkillName4("no");
                    }
                    option5 = resultSet.getString("option5");
                    if (!"".equals(option5)) {
                        String[] str5 = option5.split("-");
                        onlineExamAction.setSkillName5(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str5[0]))));
                        rightAns5 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str5[0]), eid);
                        totalRightAns = totalRightAns + rightAns5;
                        noOfQuestion5 = Integer.parseInt(str5[1]);
                        onlineExamAction.setSkillResult5(Math.round(((float) rightAns5 / (float) noOfQuestion5) * 100));
                    } else {
                        onlineExamAction.setSkillName5("no");
                    }

                    option6 = resultSet.getString("option6");
                    if (!"".equals(option6)) {
                        String[] str6 = option6.split("-");
                        onlineExamAction.setSkillName6(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str6[0]))));
                        rightAns6 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str6[0]), eid);
                        totalRightAns = totalRightAns + rightAns6;
                        noOfQuestion6 = Integer.parseInt(str6[1]);
                        onlineExamAction.setSkillResult6(Math.round(((float) rightAns6 / (float) noOfQuestion6) * 100));
                    } else {
                        onlineExamAction.setSkillName6("no");
                    }

                    option7 = resultSet.getString("option7");
                    if (!"".equals(option7)) {
                        String[] str7 = option7.split("-");
                        onlineExamAction.setSkillName7(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str7[0]))));
                        rightAns7 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str7[0]), eid);
                        totalRightAns = totalRightAns + rightAns7;
                        noOfQuestion7 = Integer.parseInt(str7[1]);
                        onlineExamAction.setSkillResult7(Math.round(((float) rightAns7 / (float) noOfQuestion7) * 100));
                    } else {
                        onlineExamAction.setSkillName7("no");
                    }
                    option8 = resultSet.getString("option8");
                    if (!"".equals(option8)) {

                        String[] str8 = option8.split("-");
                        onlineExamAction.setSkillName8(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str8[0]))));
                        rightAns8 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str8[0]), eid);
                        totalRightAns = totalRightAns + rightAns8;
                        noOfQuestion8 = Integer.parseInt(str8[1]);
                        onlineExamAction.setSkillResult8(Math.round(((float) rightAns8 / (float) noOfQuestion8) * 100));
                    } else {
                        onlineExamAction.setSkillName8("no");
                    }
                    option9 = resultSet.getString("option9");
                    if (!"".equals(option9)) {
                        String[] str9 = option9.split("-");
                        onlineExamAction.setSkillName9(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str9[0]))));
                        rightAns9 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str9[0]), eid);
                        totalRightAns = totalRightAns + rightAns9;
                        noOfQuestion9 = Integer.parseInt(str9[1]);
                        onlineExamAction.setSkillResult9(Math.round(((float) rightAns9 / (float) noOfQuestion9) * 100));
                    } else {
                        onlineExamAction.setSkillName9("no");
                    }
                    option10 = resultSet.getString("option10");
                    if (!"".equals(option10)) {

                        String[] str10 = option10.split("-");
                        onlineExamAction.setSkillName10(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str10[0]))));
                        rightAns10 = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str10[0]), eid);
                        totalRightAns = totalRightAns + rightAns10;
                        noOfQuestion10 = Integer.parseInt(str10[1]);
                        onlineExamAction.setSkillResult10(Math.round(((float) rightAns10 / (float) noOfQuestion10) * 100));
                    } else {
                        onlineExamAction.setSkillName10("no");
                    }
                    onlineExamAction.setTotalResult(Math.round(((float) totalRightAns / (float) resultSet.getInt("totalquestions")) * 100));
                    if (resultSet.getString("se.completed_ts") != null) {
                        date = DateUtility.getInstance().convertToviewFormatInDashWithTime(resultSet.getString("se.completed_ts"));
                    } else {
                        date = "";
                    }
                    resultString = "Exam Result";
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************OnlineExamServiceImpl :: getExamResult Method End*********************");
        return resultString;
    }
}
