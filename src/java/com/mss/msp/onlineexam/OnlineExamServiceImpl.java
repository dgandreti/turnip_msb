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

    public void getTokenInfo(OnlineExamAction onlineExamAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
//        int i = 0;
//        //System.err.println(days+"Diff in Dyas...");
        try {
            queryString = "SELECT eid,reqid,consultantid,acctoken,validationkey,createdby,createddate,comments,totalquestions,qualifiedmarks,examstatus,severity,exam_topics FROM sb_onlineexam WHERE acctoken='" + onlineExamAction.getToken() + "'";
            System.out.println("queryString--->getAccAuthrization-->" + queryString);


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
                // onlineExamAction.setRequirementTitle(DataSourceDataProvider.getInstance().get);
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
    }

    public void getValidationInfo(OnlineExamAction onlineExamAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

//        int i = 0;
//        //System.err.println(days+"Diff in Dyas...");
        try {
            queryString = "SELECT eid,reqid,consultantid,acctoken,validationkey,createdby,createddate,comments,totalquestions,qualifiedmarks,examstatus,severity,exam_type,duration_ts,orgid,techreviewid FROM sb_onlineexam WHERE acctoken='" + onlineExamAction.getToken() + "'";
            System.out.println("queryString--->getAccAuthrization-->" + queryString);


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
                //   String actualValidationToken=  resultSet.getString("validationkey");
                //   String consultantEmailId=DataSourceDataProvider.getInstance().getEmailIdbyuser(resultSet.getInt("consultantid"));
                onlineExamAction.setActualValidationToken(resultSet.getString("validationkey"));
                onlineExamAction.setActualConsultantEmailId(DataSourceDataProvider.getInstance().getEmailIdbyuser(resultSet.getInt("consultantid")));
                onlineExamAction.setExamType(resultSet.getString("exam_type"));
                //System.out.println("Duration time is-->"+resultSet.getString("duration_ts"));
                onlineExamAction.setDurationTime(resultSet.getString("duration_ts"));
                onlineExamAction.setExamStaus(resultSet.getString("examstatus"));
                onlineExamAction.setOrgId(resultSet.getInt("orgid"));
                onlineExamAction.setConTechReviewId(resultSet.getInt("techreviewid"));
                 onlineExamAction.setExamId(resultSet.getInt("eid"));
                // et


                //boolean isExpired = DataSourceDataProvider.getInstance().isExamExpired(onlineExamAction.getToken());
                // onlineExamAction.setIsExpired(isExpired);
                // count++;

            }
            //if(count>0){
            //    onlineExamAction.setIsValid("VALID");
            //  }

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
    }

    

    public Map getQuestions(Map skillsMap,int totalQuestions,String level,String examType,int orgId)  throws ServiceLocatorException
    {
        Connection connection=null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        Map map =new TreeMap();
         QuestionVTO questionVTO =null;
        String key;
        int keyValue ;

        int mapsize;
        int skillId=0;
         int questions=0;
          int noOfQuestions=0;
        try {
          mapsize = skillsMap.size();  
          System.out.println("map is--->"+mapsize);
          if (mapsize > 0) {
               
//                  int limit = totalQuestions/skillsMap.size(); 
//                   System.out.println("limit"+limit);
//                  int extraQuestions=totalQuestions-(limit*skillsMap.size());
             //     System.out.println("extraQuestions"+extraQuestions);
            Iterator tempIterator = skillsMap.entrySet().iterator();
            System.out.println("ExamType---->"+examType);
            
            queryString ="SELECT id,question,option1,option2,option3,option4,option5,option6,explanation,STATUS,LEVEL,skillid,orgid,question_type,ispictorial,question_path FROM sb_onlineexamQuestion WHERE skillid =? AND LEVEL='"+level+"' AND exam_type='"+examType+"' AND orgid ="+orgId+" AND STATUS='Active' ORDER BY RAND() ,createddate DESC  LIMIT ?";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            int i=0;
            int skillCount=0;
            System.out.println("queryString"+queryString);
            while (tempIterator.hasNext()) {
                skillCount++;
               Map.Entry entry = (Map.Entry) tempIterator.next();
               skillId = Integer.parseInt(entry.getKey().toString()); 
               keyValue= Integer.parseInt(entry.getValue().toString());
               preparedStatement.setInt(1, skillId);
//               if(skillCount==skillsMap.size()){
//               preparedStatement.setInt(2, (limit+extraQuestions));
//               }
//               else{
               preparedStatement.setInt(2, keyValue);
//               }
               
               resultSet=preparedStatement.executeQuery();
              
               
               while(resultSet.next())
               {
                   i++;
                  questionVTO =new QuestionVTO();
                  System.out.println("question id"+resultSet.getInt("id"));
                  
                  questionVTO.setId(resultSet.getInt("id")); 
                 
                  questionVTO.setQuestion(resultSet.getString("question"));
                  questionVTO.setOption1(resultSet.getString("option1"));
                  questionVTO.setOption2(resultSet.getString("option2"));
                  questionVTO.setOption3(resultSet.getString("option3"));
                  questionVTO.setOption4(resultSet.getString("option4"));
                  questionVTO.setOption5(resultSet.getString("option5"));
                  questionVTO.setOption6(resultSet.getString("option6"));
                   
                   
//                   Blob questionBlob = resultSet.getBlob("question");
//                   System.out.println("question blob--->"+questionBlob);
//                    if(questionBlob != null){
//                   byte[] questionBdata = questionBlob.getBytes(1, (int) questionBlob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setQuestion(new String(questionBdata));
//                    }
////                  questionVTO.setQuestion(resultSet.getString("question"));
//                     
//                     Blob option1Blob = resultSet.getBlob("option1");
//                     if(option1Blob != null){
//                   byte[] option1Bdata = option1Blob.getBytes(1, (int) option1Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption1(new String(option1Bdata));
//                     }
//                      Blob option2Blob = resultSet.getBlob("option2");
//                     if(option2Blob != null){
//                     
//                   byte[] option2Bdata = option2Blob.getBytes(1, (int) option2Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption2(new String(option2Bdata));
//                     }
//                      Blob option3Blob = resultSet.getBlob("option3");
//                      //System.out.println("option3Blob--->"+option3Blob);
//                      if(option3Blob != null){
//                           byte[] option3Bdata = option3Blob.getBytes(1, (int) option3Blob.length());
//                    //String empSummary = new String(option3Bdata);
//                  
//                     questionVTO.setOption3(new String(option3Bdata));
//                     
//                      }
//                  
//                      Blob option4Blob = resultSet.getBlob("option4");
//                      if(option4Blob != null){
//                           byte[] option4Bdata = option4Blob.getBytes(1, (int) option4Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption4(new String(option4Bdata));
//                     
//                      }
//                       Blob option5Blob = resultSet.getBlob("option5");
//                   if(option5Blob != null){
//                   byte[] option5Bdata = option5Blob.getBytes(1, (int) option5Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption5(new String(option5Bdata));
//                     
//                      }
//                    Blob option6Blob = resultSet.getBlob("option6");
//                    if(option6Blob != null){
//                  byte[] option6Bdata = option6Blob.getBytes(1, (int) option6Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption6(new String(option6Bdata));
//                     
//                      } 
                  questionVTO.setExplanation(resultSet.getString("explanation"));
                  questionVTO.setStatus(resultSet.getString("STATUS"));
                  questionVTO.setLevel(resultSet.getString("LEVEL"));
                  System.out.println("skillId is--->"+resultSet.getInt("skillid"));
                  questionVTO.setSkillId(resultSet.getInt("skillid"));
                  questionVTO.setOrgId(resultSet.getInt("orgid"));
                  questionVTO.setTopicName(StringUtils.chop(DataSourceDataProvider.getInstance().getReqSkillsSet(skillId)));
                  questionVTO.setQuestionType(resultSet.getString("question_type"));
                  questionVTO.setIsPictorial(resultSet.getInt("ispictorial"));
                  //System.out.println("path-->"+resultSet.getString("question_path"));
                  if(!"null".equals(resultSet.getString("question_path"))){
                      questionVTO.setQuestionPath(resultSet.getString("question_path"));
                  }else{
                      questionVTO.setQuestionPath(" ");
                  }
                  map.put(i, questionVTO);
               }
              // resultSet.close();
               
            }
            //int mapSize=skillsMap.size();
            
//            if(map.size()<totalQuestions){
//              int requiredQuestions=totalQuestions-map.size();
//              
//              preparedStatement.setInt(1, skillId);
//              preparedStatement.setInt(2, requiredQuestions);
//               resultSet=preparedStatement.executeQuery();
//               while(resultSet.next())
//               {
//                   i++;
//                  questionVTO =new QuestionVTO();
//                  questionVTO.setId(resultSet.getInt("id")); 
//                   Blob questionBlob = resultSet.getBlob("question");
//                    if(questionBlob != null){
//                   byte[] questionBdata = questionBlob.getBytes(1, (int) questionBlob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setQuestion(new String(questionBdata));
//                    }
////                  questionVTO.setQuestion(resultSet.getString("question"));
//                     
//                     Blob option1Blob = resultSet.getBlob("option1");
//                     if(option1Blob != null){
//                   byte[] option1Bdata = option1Blob.getBytes(1, (int) option1Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption1(new String(option1Bdata));
//                     }
//                      Blob option2Blob = resultSet.getBlob("option2");
//                     if(option2Blob != null){
//                     
//                   byte[] option2Bdata = option2Blob.getBytes(1, (int) option2Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption2(new String(option2Bdata));
//                     }
//                      Blob option3Blob = resultSet.getBlob("option3");
//                      //System.out.println("option3Blob--->"+option3Blob);
//                      if(option3Blob != null){
//                           byte[] option3Bdata = option3Blob.getBytes(1, (int) option3Blob.length());
//                    String empSummary = new String(option3Bdata);
//                  
//                     questionVTO.setOption3(new String(option3Bdata));
//                     
//                      }
//                  
//                      Blob option4Blob = resultSet.getBlob("option4");
//                      if(option4Blob != null){
//                           byte[] option4Bdata = option4Blob.getBytes(1, (int) option4Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption4(new String(option4Bdata));
//                     
//                      }
//                       Blob option5Blob = resultSet.getBlob("option5");
//                   if(option5Blob != null){
//                   byte[] option5Bdata = option5Blob.getBytes(1, (int) option5Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption5(new String(option5Bdata));
//                     
//                      }
//                    Blob option6Blob = resultSet.getBlob("option6");
//                    if(option6Blob != null){
//                  byte[] option6Bdata = option6Blob.getBytes(1, (int) option6Blob.length());
//                    //String empSummary = new String(bdata);
//                     questionVTO.setOption6(new String(option6Bdata));
//                     
//                      } 
//                   
//                     
//                  
//                  //questionVTO.setOption1(resultSet.getString("option1"));
//                  //questionVTO.setOption2(resultSet.getString("option2"));
//                  //questionVTO.setOption3(resultSet.getString("option3"));
//                  //questionVTO.setOption4(resultSet.getString("option4"));
//                  //questionVTO.setOption5(resultSet.getString("option5"));
//                  //questionVTO.setOption6(resultSet.getString("option6"));
//                  questionVTO.setExplanation(resultSet.getString("explanation"));
//                  questionVTO.setStatus(resultSet.getString("STATUS"));
//                  questionVTO.setLevel(resultSet.getString("LEVEL"));
//                  questionVTO.setSkillId(resultSet.getInt("skillid"));
//                  questionVTO.setOrgId(resultSet.getInt("orgid"));
//                  questionVTO.setTopicName(keyValue);
//                  questionVTO.setQuestionType(resultSet.getString("question_type"));
//                  map.put(i, questionVTO);
//               }
//              System.out.println("requiredQuestions--->"+requiredQuestions);
//            }
            
            
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
        return map;
    }

    public int getOnlineExamKey(OnlineExamAction onlineExamAction) throws ServiceLocatorException {



        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;

        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        Connection connection = null;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL sponlineexamkey(?,?,?)}");

            callableStatement.setInt(1, onlineExamAction.getConsultantId());
            callableStatement.setInt(2, onlineExamAction.getRequirementId());

            updatedRows = callableStatement.executeUpdate();
            System.out.println("Execute=========>" + updatedRows);
            addResult = callableStatement.getInt(3);

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

    public int insertAnswer(int examQuestionId, int ans1, int ans2, int ans3, int ans4, int ans5, int ans6, int consultantId, int examKey, int skiiId, int reqId, String status,int examId) throws ServiceLocatorException {

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;

        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        String queryString = "";
        Connection connection = null;
        ResultSet resultSet = null;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "INSERT INTO sb_onlineexamsummery(reqid,consultantid,questionid,ans1,ans2,ans3,ans4,ans5,ans6,examkey,ansstatus,examid,skillid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
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

//            if(addResult>0)
//            {
//             System.out.println("adding into sbonline exam");   
//             updateConsultantStatusTechReview(consultantId,reqId,"Exam Completed");   
//             
//            }



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
        return addResult;




    }

     public int updateConsultantStatusTechReview(int contechReviewId, String techstatus,int examId,int totalQuestions) throws ServiceLocatorException {

        //CallableStatement callableStatement = null;
        

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;

        int addResult = 0;
        boolean isExceute = false;
        int updatedRows = 0;
        Connection connection = null;
        System.out.println(contechReviewId+"-->"+techstatus+"-->"+examId+"-->"+totalQuestions);
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL sp_onlineexamupdatestatus(?,?,?,?,?)}");

            callableStatement.setInt(1, examId);
            callableStatement.setInt(2, totalQuestions);
            callableStatement.setInt(3, contechReviewId);
            callableStatement.setString(4, techstatus);
            updatedRows = callableStatement.executeUpdate();
            System.out.println("Execute=========>" + updatedRows);
            addResult = callableStatement.getInt(5);

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

    public int updateExamStatus(int conTechReviewId,String examStatus) throws ServiceLocatorException {

        PreparedStatement preparedStatement = null;
        //Statement statement = null;

        int addResult = 0;
        boolean isExceute = false;
        // int updatedRows=0;
        String queryString = "";
        Connection connection = null;
        //ResultSet resultSet=null;
        try {
            //System.out.println("consultantId" + consultantid + "reqid" + reqid + "techstatus" + status);
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE sb_onlineexam SET examstatus=? WHERE techreviewid=? ";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, examStatus);
            preparedStatement.setInt(2, conTechReviewId);
           // preparedStatement.setInt(3, consultantid);
            //preparedStatement.setString(4, validKey);

            addResult = preparedStatement.executeUpdate();

            System.out.println("result is updated----->" + addResult);


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
        return addResult;

    }
    
    public String getExamResult(int contechId,OnlineExamAction onlineExamAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String date = "";
        String option1 = null,option2 = null,option3 = null,option4 = null,option5 = null,option6 = null,option7 = null,option8 = null,option9 = null,option10 = null;
        
        //String skill1Name1 = null, skill1Name2 = null, skill1Name3 = null, skill1Name4 = null, skill1Name5 = null, skill1Name6 = null, skill1Name7 = null, skill1Name8 = null, skill1Name9 = null, skill1Name10 = null;
        int noOfQuestion1 = 0, noOfQuestion2 = 0, noOfQuestion3 = 0, noOfQuestion4 = 0, noOfQuestion5 = 0, noOfQuestion6 = 0, noOfQuestion7 = 0, noOfQuestion8 = 0, noOfQuestion9 = 0, noOfQuestion10 = 0;
        int rightAns1 = 0, rightAns2 = 0, rightAns3 = 0, rightAns4 = 0, rightAns5 = 0, rightAns6 = 0, rightAns7 = 0, rightAns8 = 0, rightAns9 = 0, rightAns10 = 0;
        int totalRightAns=0;

        try {
           
                queryString = "SELECT CONCAT(c.first_name,'.',c.last_name) AS NAME,so.techreviewid,so.consultantid,cr.id,cr.STATUS,so.option1,so.option2,so.option3,so.option4,so.option5,so.option6,so.option7,so.option8,so.option9,so.option10,c.email1,so.createddate,c.phone1,cd.job_title,so.eid,so.examstatus,se.completed_ts,so.totalquestions  FROM sb_onlineexam so"
                        + " LEFT OUTER JOIN con_techreview cr ON(so.techreviewid=cr.id)"
                        + " LEFT OUTER JOIN users c ON(c.usr_id=so.consultantid)"
                        + " LEFT OUTER JOIN usr_details cd ON(cd.usr_id=so.consultantid)"
                        + " LEFT OUTER JOIN sb_onlineexamsummery se ON(so.eid=se.examid)"
                        + " WHERE so.techreviewid=" + contechId+"";

                        System.out.println("gueryString>>>> " + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         

            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
              

                
                     if( resultSet.last())
                     {
                         
                     
                    // resultSet.getString("NAME") 
                    int eid=resultSet.getInt("so.eid");
                    option1 = resultSet.getString("option1");
                    System.out.println("option1-->" + option1 + "<---");
                    if (!"".equals(option1)) {
                        String[] str1 = option1.split("-");

                        //skill1Name1 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str1[0]));
                        onlineExamAction.setSkillName1(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str1[0]))));
                        rightAns1  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str1[0]), eid);
                        totalRightAns=totalRightAns+rightAns1;
                        noOfQuestion1 = Integer.parseInt(str1[1]);
                        //int total=(rightAns1/noOfQuestion1)*100;
                        System.out.println("rightans1"+rightAns1+"noOfQuestion1"+noOfQuestion1);
                        //System.out.println("percentage is-->"+(((float)rightAns1/(float)noOfQuestion1)*100));
                        onlineExamAction.setSkillResult1(Math.round(((float)rightAns1/(float)noOfQuestion1)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName1("no");  
                    }
                    option2 = resultSet.getString("option2");
                    System.out.println("option2-->" + option2 + "<---");
                    if (!"".equals(option2)) {
                        String[] str2 = option2.split("-");
                        //skill1Name2 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str2[0]));
                        onlineExamAction.setSkillName2((StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str2[0])))));
                        rightAns2  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str2[0]), eid);
                        totalRightAns=totalRightAns+rightAns2;
                        noOfQuestion2 = Integer.parseInt(str2[1]);
                        System.out.println("percentage is-->"+(rightAns2/noOfQuestion2)*100);
                        onlineExamAction.setSkillResult2(Math.round((((float)rightAns2/(float)noOfQuestion2)*100)));
                    }
                    else
                    {
                      onlineExamAction.setSkillName2("no");  
                    }
                    option3 = resultSet.getString("option3");
                    System.out.println("option3-->" + option3 + "<---");
                    if (!"".equals(option3)) {
                        String[] str3 = option3.split("-");
                        //skill1Name3 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str3[0]));
                        onlineExamAction.setSkillName3((StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str3[0])))));
                        rightAns3  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str3[0]), eid);
                        totalRightAns=totalRightAns+rightAns3;
                        
                        noOfQuestion3 = Integer.parseInt(str3[1]);
                        System.out.println("percentage is-->"+(rightAns3/noOfQuestion3)*100);
                        onlineExamAction.setSkillResult3(Math.round(((float)rightAns3/(float)noOfQuestion3)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName3("no");  
                    }
                    
                    option4 = resultSet.getString("option4");
                    System.out.println("option4-->" + option4 + "<---");

                    if (!"".equals(option4)) {
                        String[] str4 = option4.split("-");
                        //skill1Name4 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str4[0]));
                        onlineExamAction.setSkillName4((StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str4[0])))));
                        rightAns4  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str4[0]), eid);
                        totalRightAns=totalRightAns+rightAns4;
                        noOfQuestion4 = Integer.parseInt(str4[1]);
                        System.out.println("percentage is-->"+(rightAns4/noOfQuestion4)*100);
                        onlineExamAction.setSkillResult4(Math.round(((float)rightAns4/(float)noOfQuestion4)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName4("no");  
                    }
                    option5 = resultSet.getString("option5");
                    System.out.println("option5-->" + option5 + "<---");
                    if (!"".equals(option5)) {
                        String[] str5 = option5.split("-");
                        //skill1Name5 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str5[0]));
                        onlineExamAction.setSkillName5(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str5[0]))));
                        rightAns5  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str5[0]), eid);
                        totalRightAns=totalRightAns+rightAns5;
                        noOfQuestion5 = Integer.parseInt(str5[1]);
                        System.out.println("percentage is-->"+(rightAns5/noOfQuestion5)*100);
                        onlineExamAction.setSkillResult5(Math.round(((float)rightAns5/(float)noOfQuestion5)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName5("no");  
                    }

                    option6 = resultSet.getString("option6");
                    System.out.println("option6-->" + option6 + "<---");
                    if (!"".equals(option6)) {
                        String[] str6 = option6.split("-");
                        //skill1Name6 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str6[0]));
                        onlineExamAction.setSkillName6(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str6[0]))));
                        rightAns6  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str6[0]), eid);
                        totalRightAns=totalRightAns+rightAns6;
                        noOfQuestion6 = Integer.parseInt(str6[1]);
                        System.out.println("percentage is-->"+(rightAns6/noOfQuestion6)*100);
                        onlineExamAction.setSkillResult6(Math.round(((float)rightAns6/(float)noOfQuestion6)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName6("no");  
                    }

                    option7 = resultSet.getString("option7");
                    System.out.println("option7-->" + option7 + "<---");
                    if (!"".equals(option7)) {
                        String[] str7 = option7.split("-");
                        //skill1Name7 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str7[0]));
                         onlineExamAction.setSkillName7(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str7[0]))));
                        rightAns7  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str7[0]), eid);
                        totalRightAns=totalRightAns+rightAns7;
                        noOfQuestion7 = Integer.parseInt(str7[1]);
                        System.out.println("percentage is-->"+(rightAns7/noOfQuestion7)*100);
                        onlineExamAction.setSkillResult7(Math.round(((float)rightAns7/(float)noOfQuestion7)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName7("no");  
                    }
                    option8 = resultSet.getString("option8");
                    System.out.println("option8-->" + option8 + "<---");
                    if (!"".equals(option8)) {

                        String[] str8 = option8.split("-");
                        //skill1Name8 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str8[0]));
                        onlineExamAction.setSkillName8(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str8[0]))));
                        rightAns8  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str8[0]), eid);
                        totalRightAns=totalRightAns+rightAns8;
                        noOfQuestion8 = Integer.parseInt(str8[1]);
                        System.out.println("percentage is-->"+(rightAns8/noOfQuestion8)*100);
                        onlineExamAction.setSkillResult8(Math.round(((float)rightAns8/(float)noOfQuestion8)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName8("no");  
                    }
                    option9 = resultSet.getString("option9");
                    if (!"".equals(option9)) {
                        System.out.println("option9-->" + option9 + "<---");
                        String[] str9 = option9.split("-");
                        //skill1Name9 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str9[0]));
                        onlineExamAction.setSkillName9(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str9[0]))));
                        rightAns9  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str9[0]), eid);
                        totalRightAns=totalRightAns+rightAns9;
                        noOfQuestion9 = Integer.parseInt(str9[1]);
                        System.out.println("percentage is-->"+(rightAns9/noOfQuestion9)*100);
                        onlineExamAction.setSkillResult9(Math.round(((float)rightAns9/(float)noOfQuestion9)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName9("no");  
                    }
                    option10 = resultSet.getString("option10");
                    if (!"".equals(option10)) {

                        System.out.println("option10-->" + option10 + "<---");
                        String[] str10 = option10.split("-");
                       // skill1Name10 = com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str10[0]));
                        onlineExamAction.setSkillName10(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(Integer.parseInt(str10[0]))));
                        rightAns10  =com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfRightAns(Integer.parseInt(str10[0]), eid);
                        totalRightAns=totalRightAns+rightAns10;
                        noOfQuestion10 = Integer.parseInt(str10[1]);
                        System.out.println("percentage is-->"+(rightAns10/noOfQuestion10)*100);
                        onlineExamAction.setSkillResult10(Math.round(((float)rightAns10/(float)noOfQuestion10)*100));
                    }
                    else
                    {
                      onlineExamAction.setSkillName10("no");  
                    }
                    //onlineExamAction.setTotalQuestions(resultSet.getInt("totalquestions"));
                    //onlineExamAction.setTotalResult(totalRightAns+"/"+resultSet.getInt("totalquestions"));
                    System.out.println("percentage is-->"+(totalRightAns/resultSet.getInt("totalquestions"))*100);
                    onlineExamAction.setTotalResult(Math.round(((float)totalRightAns/(float)resultSet.getInt("totalquestions"))*100));
                     if (resultSet.getString("se.completed_ts") != null) {
                        //System.out.println("Scheduled Date if");
                        date = DateUtility.getInstance().convertToviewFormatInDashWithTime(resultSet.getString("se.completed_ts"));
                    } else {
                        // System.out.println("Scheduled Date else");
                        date = "";
                    }
                     

             resultString="Exam Result";
                     }
                }
            
            System.out.println("resultString>>>" + resultString);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return resultString;
    }
}
