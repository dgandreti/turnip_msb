/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.onlineexam.questions;

import com.mss.msp.requirement.RequirementAction;
import com.mss.msp.security.SecurityServiceProvider;
import com.mss.msp.usersdata.UserVTO;
import com.mss.msp.usersdata.UsersdataHandlerAction;
import com.mss.msp.usr.task.TasksVTO;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocatorException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

/**
 *
 * @author miracle
 */
public class ExamQuestionsServiceImpl implements ExamQuestionsService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public List doQuestionsSearch(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        List<ExamQuesVTO> ExamQuesVTOList = new ArrayList<ExamQuesVTO>();
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT DISTINCT question,id,skillid,status,level,question_type,ispictorial FROM sb_onlineexamQuestion WHERE orgid=" + examQuestionsAction.getUserOrgSessionId() +" ORDER BY createddate DESC LIMIT 100";

            System.out.println("query...for..org id...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ExamQuesVTO examQuesVTO = new ExamQuesVTO();
                examQuesVTO.setQuestion(resultSet.getString("question"));
                examQuesVTO.setQuesId(resultSet.getInt("id"));
                examQuesVTO.setSkillCategoryValue(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(resultSet.getInt("skillid"))));
                examQuesVTO.setStatus(resultSet.getString("status"));
                 if (resultSet.getInt("ispictorial") == 1) {
                    examQuesVTO.setIsPic(true);
                } else {
                    examQuesVTO.setIsPic(false);
                }
                if ("L".equals(resultSet.getString("level"))) {
                    examQuesVTO.setLevel("Low");
                } else if ("M".equals(resultSet.getString("level"))) {
                    examQuesVTO.setLevel("Medium");
                } else {
                    examQuesVTO.setLevel("High");
                }
                if ("S".equals(resultSet.getString("question_type"))) {
                    examQuesVTO.setAnswerType("Single");
                } else {
                    examQuesVTO.setAnswerType("Multiple");
                }
                ExamQuesVTOList.add(examQuesVTO);
                //   resultString += resultSet.getString("question") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(resultSet.getInt("skillid"))+ "|"+resultSet.getString("status") + "|"+resultSet.getString("level") + "|"+ resultSet.getString("question_type") + "|"+"^";
            }

            System.out.println("ExamQuesVTOList=========>" + ExamQuesVTOList);
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
        return ExamQuesVTOList;
    }

      public String addOrEditExamQuestionsEdit(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        String resultString = "";
        Statement statement = null;
        String queryString = "";
        int result = 0;
        String filePath1 = "";
        String filePath2 = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            System.out.println("examQuestionsAction.getSkillCategoryValue()--->" + examQuestionsAction.getSkillCategoryValue());
                System.out.println("in updateques--->" + examQuestionsAction.getQuesId() + "usrSessionId" + examQuestionsAction.getUserSessionId());
                callableStatement = connection.prepareCall("{call sp_onlineExamQuestionAddOrEdit(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                callableStatement.setString(1, examQuestionsAction.getQuestion());
                callableStatement.setString(2, examQuestionsAction.getOption1());
                callableStatement.setString(3, examQuestionsAction.getOption2());
                callableStatement.setString(4, examQuestionsAction.getOption3());
                callableStatement.setString(5, examQuestionsAction.getOption4());
                callableStatement.setString(6, examQuestionsAction.getOption5());
                callableStatement.setString(7, examQuestionsAction.getOption6());

                callableStatement.setInt(8, examQuestionsAction.getSkillCategoryValue());
                callableStatement.setString(9, examQuestionsAction.getLevel());
                callableStatement.setString(10, examQuestionsAction.getAnswerType());
                callableStatement.setString(11, examQuestionsAction.getExamType());
                callableStatement.setString(12, examQuestionsAction.getExplanation());
                callableStatement.setString(13, examQuestionsAction.getStatus());
                callableStatement.setInt(14, examQuestionsAction.getUserSessionId());

                callableStatement.setInt(15, examQuestionsAction.getUserOrgSessionId());
                callableStatement.setInt(16, examQuestionsAction.getQuesId());
                System.out.println("examQuestionsAction.getAnswer()" + examQuestionsAction.getAnswer());
                if (examQuestionsAction.getAnswer() != null) {
                    if (examQuestionsAction.getAnswer().equals("Answer1")) {
                        callableStatement.setInt(17, 1);
                    } else {
                        callableStatement.setInt(17, 0);
                    }
                    if (examQuestionsAction.getAnswer().equals("Answer2")) {
                        callableStatement.setInt(18, 1);
                    } else {
                        callableStatement.setInt(18, 0);
                    }
                    if (examQuestionsAction.getAnswer().equals("Answer3")) {
                        callableStatement.setInt(19, 1);
                    } else {
                        callableStatement.setInt(19, 0);
                    }
                    if (examQuestionsAction.getAnswer().equals("Answer4")) {
                        callableStatement.setInt(20, 1);
                    } else {
                        callableStatement.setInt(20, 0);
                    }
                    if (examQuestionsAction.getAnswer().equals("Answer5")) {
                        callableStatement.setInt(21, 1);
                    } else {
                        callableStatement.setInt(21, 0);
                    }
                    if (examQuestionsAction.getAnswer().equals("Answer6")) {
                        callableStatement.setInt(22, 1);
                    } else {
                        callableStatement.setInt(22, 0);
                    }
                } else {
                    if (examQuestionsAction.getAns1().equals("true")) {
                        callableStatement.setInt(17, 1);
                    } else {
                        callableStatement.setInt(17, 0);
                    }
                    if (examQuestionsAction.getAns2().equals("true")) {
                        callableStatement.setInt(18, 1);
                    } else {
                        callableStatement.setInt(18, 0);
                    }
                    if (examQuestionsAction.getAns3().equals("true")) {
                        callableStatement.setInt(19, 1);
                    } else {
                        callableStatement.setInt(19, 0);
                    }
                    if (examQuestionsAction.getAns4().equals("true")) {
                        callableStatement.setInt(20, 1);
                    } else {
                        callableStatement.setInt(20, 0);
                    }
                    if (examQuestionsAction.getAns5().equals("true")) {
                        callableStatement.setInt(21, 1);
                    } else {
                        callableStatement.setInt(21, 0);
                    }

                    if ((examQuestionsAction.getAns6()).equals("true")) {
                        callableStatement.setInt(22, 1);
                    } else {
                        callableStatement.setInt(22, 0);
                    }

                }
            if (examQuestionsAction.getQuesId() > 0) {
                
                System.out.println("examQuestionsAction.getIsPic()--->" + examQuestionsAction.getIsPic());
                String isPicture = "";
                isPicture = examQuestionsAction.getIsPic();
                if (isPicture.equals("false")) {
                    callableStatement.setInt(23, 0);
                } else {
                    callableStatement.setInt(23, 1);
                }
                System.out.println("examQuestionsAction.getFilePath()- Update ->"+examQuestionsAction.getFilePath());
                if (examQuestionsAction.getIsPic().equals("true")) {
                    callableStatement.setString(24, examQuestionsAction.getFilePath());
                } else {
                    callableStatement.setString(24, null);
                }
                callableStatement.setString(25, File.separator);
                callableStatement.registerOutParameter(26, Types.INTEGER);
                callableStatement.registerOutParameter(27, Types.VARCHAR);
                callableStatement.registerOutParameter(28, Types.VARCHAR);
                callableStatement.execute();
                result = callableStatement.getInt(26);
                filePath1 = callableStatement.getString(27);
                filePath2 = callableStatement.getString(28);
                
                if (examQuestionsAction.getFilePath() != null || examQuestionsAction.getIsPic().equals("false")) {
                    if(filePath2 != null){
                    File destFile1 = new File(filePath2);
                    System.out.println("destFile1--->" + destFile1);
                    destFile1.delete();
                    System.out.println("filePath2----->" + filePath2);
                    }
                    if (filePath1 != null) {
                        System.out.println("filePath1-------->" + filePath1);
                        File destFile = new File(filePath1);
                        System.out.println(">>>>>>>>>>>>>>>>>>IN Impl image PATH222222>>>>>>>>>>>>" + destFile);
                        FileUtils.copyFile(examQuestionsAction.getQuesImage(), destFile);
                        examQuestionsAction.setNewFilePath(filePath1);
                    }
                }


            } else {
              
                if (examQuestionsAction.getIsPic().equals("true")) {
                    callableStatement.setInt(23, 1);
                } else {
                    callableStatement.setInt(23, 0);
                }
                System.out.println("examQuestionsAction.getFilePath()-->"+examQuestionsAction.getFilePath());
                System.out.println("examQuestionsAction.getIsPic()-->"+examQuestionsAction.getIsPic());
                 if (examQuestionsAction.getIsPic().equals("true")) {
                    callableStatement.setString(24, examQuestionsAction.getFilePath());
                } else {
                    callableStatement.setString(24,null);
                }
                 callableStatement.setString(25, File.separator);
                callableStatement.registerOutParameter(26, Types.INTEGER);
                callableStatement.registerOutParameter(27, Types.VARCHAR);
                callableStatement.registerOutParameter(28, Types.VARCHAR);
                callableStatement.execute();
                result = callableStatement.getInt(26);
                filePath1 = callableStatement.getString(27);

                filePath2 = callableStatement.getString(28);



                System.out.println("filePath2----->" + filePath2);
                if (filePath1 != null) {
                    System.out.println("filePath1-------->" + filePath1);
                    File destFile = new File(filePath1);
                    System.out.println(">>>>>>>>>>>>>>>>>>IN Impl image PATH222222>>>>>>>>>>>>" + destFile);
                    FileUtils.copyFile(examQuestionsAction.getQuesImage(), destFile);

                }

            }



            System.out.println("the storeAddOrEditHomeRedirectDetails query is-->");

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>result>>" + result);
            if (result > 0) {
                resultString = "Success";
            } else {
                resultString = "Fail";
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>resultString>>" + resultString);
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

    public ExamQuesVTO doExamQuestionsEdit(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        List<ExamQuesVTO> ExamQuesVTOList = new ArrayList<ExamQuesVTO>();
        ExamQuesVTO examQuesVTO = new ExamQuesVTO();
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT question,skillid,STATUS,LEVEL,question_type,option1,option2,option3,option4,option5,option6,ans1,ans2,ans3,ans4,ans5,ans6,explanation,exam_type,ispictorial,question_path FROM sb_onlineexamQuestion ques JOIN sb_onlineexamqusans ans ON  ans.questionid=ques.id WHERE ques.id=" + examQuestionsAction.getQuesId();

            System.out.println("query...for..org id...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                examQuesVTO.setQuestion(resultSet.getString("question"));

                examQuesVTO.setSkillCategoryValue1(resultSet.getInt("skillid"));
                examQuesVTO.setStatus(resultSet.getString("status"));

                examQuesVTO.setLevel(resultSet.getString("LEVEL"));


//                examQuesVTO.setAnswerType(resultSet.getString("question_type"));

//                if ("S".equals(resultSet.getString("question_type"))) {
//                    examQuesVTO.setCheckAnsType("Single");
//                } else {
//                    examQuesVTO.setCheckAnsType("Multiple");
//                }


                //examQuesVTO.setAnswerType( resultSet.getString("question_type"));

                System.out.println("question_type---->" + resultSet.getString("question_type"));
                examQuesVTO.setOption1(resultSet.getString("option1"));
                examQuesVTO.setOption2(resultSet.getString("option2"));
                examQuesVTO.setOption3(resultSet.getString("option3"));
                examQuesVTO.setOption4(resultSet.getString("option4"));
                examQuesVTO.setOption5(resultSet.getString("option5"));
                examQuesVTO.setOption6(resultSet.getString("option6"));
                examQuesVTO.setAnswerType(resultSet.getString("question_type"));
                System.out.println("resultSet.getString(\"ans1\")-->" + resultSet.getString("ans1"));
                if (examQuesVTO.getAnswerType().equals("M")) {
                    if (resultSet.getString("ans1").equals("0")) {

                        // {'Answer1','Answer2','Answer3','Answer4','Answer5','Answer6'
                        examQuesVTO.setAns1(false);
                    } else {

                        examQuesVTO.setAns1(true);
                    }
                    if (resultSet.getString("ans2").equals("0")) {
                        examQuesVTO.setAns2(false);
                    } else {

                        examQuesVTO.setAns2(true);
                    }
                    if (resultSet.getString("ans3").equals("0")) {
                        examQuesVTO.setAns3(false);
                    } else {
                        examQuesVTO.setAns3(true);
                    }
                    if (resultSet.getString("ans4").equals("0")) {
                        examQuesVTO.setAns4(false);
                    } else {
                        examQuesVTO.setAns4(true);
                    }
                    if (resultSet.getString("ans5").equals("0")) {
                        examQuesVTO.setAns5(false);
                    } else {
                        examQuesVTO.setAns5(true);
                    }
                    if (resultSet.getString("ans6").equals("0")) {
                        examQuesVTO.setAns6(false);
                    } else {
                        examQuesVTO.setAns6(true);
                    }
                } else {
                    if (resultSet.getString("ans1").equals("1")) {

                        // {'Answer1','Answer2','Answer3','Answer4','Answer5','Answer6'
                        examQuesVTO.setAnswer("Answer1");
                    } 
                    if (resultSet.getString("ans2").equals("1")) {
                       examQuesVTO.setAnswer("Answer2");
                    } 
                    if (resultSet.getString("ans3").equals("1")) {
                        examQuesVTO.setAnswer("Answer3");
                    } 
                    if (resultSet.getString("ans4").equals("1")) {
                       examQuesVTO.setAnswer("Answer4");
                    } 
                    if (resultSet.getString("ans5").equals("1")) {
                       examQuesVTO.setAnswer("Answer5");
                    } 
                    if (resultSet.getString("ans6").equals("1")) {
                        examQuesVTO.setAnswer("Answer6");
                    } 
                }

//                examQuesVTO.setOption6(resultSet.getString("ans3"));
//                examQuesVTO.setOption6(resultSet.getString("ans4"));
//                examQuesVTO.setOption6(resultSet.getString("ans5"));
//                examQuesVTO.setOption6(resultSet.getString("ans6"));
                examQuesVTO.setExplanation(resultSet.getString("explanation"));
                examQuesVTO.setExamType(resultSet.getString("exam_type"));
                 if (resultSet.getString("ispictorial").equals("0")) {
                    examQuesVTO.setIsPic(false);
                } else {
                    examQuesVTO.setIsPic(true);
                }
                examQuesVTO.setQuestion_path(resultSet.getString("question_path"));


                //   ExamQuesVTOList.add(examQuesVTO); 
                //   resultString += resultSet.getString("question") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(resultSet.getInt("skillid"))+ "|"+resultSet.getString("status") + "|"+resultSet.getString("level") + "|"+ resultSet.getString("question_type") + "|"+"^";
            }

            System.out.println("ExamQuesVTOList=========>" + examQuesVTO);
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
        return examQuesVTO;
    }

    public List doQuestionsSearchList(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        List<ExamQuesVTO> ExamQuesVTOList = new ArrayList<ExamQuesVTO>();
        // String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT DISTINCT question,id,skillid,status,level,question_type,ispictorial FROM sb_onlineexamQuestion WHERE orgid=" + examQuestionsAction.getUserOrgSessionId();
            if ((examQuestionsAction.getSkillCategoryValue() != -1)) {
                queryString = queryString + " AND skillid=" + examQuestionsAction.getSkillCategoryValue() + " ";
            }
            if (!"".equals(examQuestionsAction.getQuestion())) {
                queryString = queryString + " AND  question LIKE '%" + examQuestionsAction.getQuestion() + "%' ";
            }
            if (!"DF".equals(examQuestionsAction.getStatus())) {
                queryString = queryString + " AND status='" + examQuestionsAction.getStatus() + "' ";
            }
            if (!"DF".equals(examQuestionsAction.getLevel())) {
                queryString = queryString + " AND level='" + examQuestionsAction.getLevel() + "' ";
            }
            if (!"DF".equals(examQuestionsAction.getAnswerType())) {
                queryString = queryString + " AND question_type='" + examQuestionsAction.getAnswerType() + "'";
            }
             if (!"DF".equals(examQuestionsAction.getExamType())) {
                queryString = queryString + " AND exam_type='" + examQuestionsAction.getExamType() + "'";
            }

             queryString = queryString + " ORDER BY createddate DESC LIMIT 100";
            System.out.println("query...for..org id...." + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ExamQuesVTO examQuesVTO = new ExamQuesVTO();
                examQuesVTO.setQuestion(resultSet.getString("question"));
                examQuesVTO.setQuesId(resultSet.getInt("id"));


                examQuesVTO.setSkillCategoryValue(StringUtils.chop(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(resultSet.getInt("skillid"))));
                examQuesVTO.setStatus(resultSet.getString("status"));
                 if (resultSet.getInt("ispictorial") == 1) {
                    examQuesVTO.setIsPic(true);
                } else {
                    examQuesVTO.setIsPic(false);
                }
                if ("L".equals(resultSet.getString("level"))) {
                    examQuesVTO.setLevel("Low");
                } else if ("M".equals(resultSet.getString("level"))) {
                    examQuesVTO.setLevel("Medium");
                } else {
                    examQuesVTO.setLevel("High");
                }
                if ("S".equals(resultSet.getString("question_type"))) {
                    examQuesVTO.setAnswerType("Single");
                } else {
                    examQuesVTO.setAnswerType("Multiple");
                }

//                if(resultSet.getString("question_type")=="S"){
//                 examQuesVTO.setAnswerType("Single");
//                }

//                examQuesVTO.setLevel(resultSet.getString("level"));
//                 examQuesVTO.setAnswerType(resultSet.getString("question_type"));
                ExamQuesVTOList.add(examQuesVTO);
                //  resultString += resultSet.getString("question") + "|" + com.mss.msp.util.DataSourceDataProvider.getInstance().getReqSkillsSet(resultSet.getInt("skillid"))+ "|"+resultSet.getString("status") + "|"+resultSet.getString("level") + "|"+ resultSet.getString("question_type") + "|"+ "^";
            }

            System.out.println("ExamQuesVTOList=========>" + ExamQuesVTOList);
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
        return ExamQuesVTOList;
    }
    
    public String getImagePath(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        String queryString = "";
        String resultString = "";

        try {
            //  System.out.println("examQuestionsAction.getQuesId()---->"+examQuestionsAction.getQuesId());
            queryString = "SELECT question_path FROM sb_onlineexamQuestion WHERE id=" + examQuestionsAction.getQuesId();

            // System.out.println("gueryString>>>> " + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                resultString += resultSet.getString("question_path");
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
    
    
   

    public String getCellContentValues(List list, ExamQuestionsAction examQuestionsAction, int count, String type, String columsString) throws ServiceLocatorException {

        String exist_str = "";
        String fail_str = "";
        String result = "";
        String res = "";
        String fileName = "";
        String finalPath = "";
        CallableStatement callableStatement = null;
        StringTokenizer st2 = new StringTokenizer(examQuestionsAction.getPath(), File.separator);

        while (st2.hasMoreTokens()) {

            fileName = st2.nextToken();

        }

        ListIterator<String[]> litr = null;
        litr = list.listIterator();

        int updatedRows;
        boolean isExceute = false;
        String arrayData[] = new String[count];
        String accRecord = "";
        while (litr.hasNext()) {
            arrayData = litr.next();
            for (int i = 0; i < count; i++) {
                accRecord += arrayData[i] + "|";
            }
            accRecord += "^";
        }
        System.out.println("fileName--------------------" + fileName);
        System.out.println("accRecord--------------------" + accRecord);

        try {
            if ("skills".equalsIgnoreCase(type)) {
                System.out.println("---------------> in skills imp---getFilePath" + examQuestionsAction.getFilePath());

                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call sp_onlineExamQuestionUpload(?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, accRecord);
                System.out.println("after 1 st call" + accRecord);
                callableStatement.setString(2, "^");
                callableStatement.setString(3, examQuestionsAction.getFilePath());
                callableStatement.setString(4, fileName);
                callableStatement.setInt(5, examQuestionsAction.getSkillCategoryValue());

                callableStatement.setInt(6, examQuestionsAction.getUserSessionId());

                callableStatement.setInt(7, examQuestionsAction.getUserOrgSessionId());
                callableStatement.setString(8,File.separator);


                callableStatement.registerOutParameter(9, Types.VARCHAR);
                callableStatement.registerOutParameter(10, Types.VARCHAR);
                callableStatement.registerOutParameter(11, Types.VARCHAR);
                callableStatement.execute();
                exist_str = callableStatement.getString(9);
                fail_str = callableStatement.getString(10);
                result = callableStatement.getString(11);
                System.out.println("this is for skills------------sp_onlineExamQuestionUpload>");
            }
            //  int id = callableStatement.getInt(10);
            System.out.println("---------rs---------->" + result);
            System.out.println("---------exist_count---------->" + exist_str);
            System.out.println("---------exist_str---------->" + fail_str);
            examQuestionsAction.setSp_res(result);
            examQuestionsAction.setSp_exists(exist_str);
            examQuestionsAction.setSp_failure(fail_str);

            //   int excelColValue[] = new int[50];


            //usersdataHandlerAction.setExcel_id(id);

            if (!"".equals(examQuestionsAction.getSp_exists()) || !"".equals(examQuestionsAction.getSp_failure())) {
                //String ecel = resultantExcelFile(usersdataHandlerAction, fileName, exist_str, excelColValue, k);
                int k = 1;
                StringTokenizer coulmsdata = new StringTokenizer(columsString, "|");
                int excelColValue[] = new int[50];
                System.out.println("coulmsdata------------------>" + coulmsdata.countTokens());


                if ("skills".equalsIgnoreCase(type)) {
                    int c = 0;
                    while (c < 17) {
                        System.out.println("in while--->" + c);
                        c = c + 1;
                        System.out.println("in while--->" + c);
                        excelColValue[k] = c;

                        System.out.println("in while--->excelColValue-->" + excelColValue[k]);
                        k++;
                    }
                }



                List<String> record = new ArrayList<String>();

                StringTokenizer str = null;
//                if (!"".equals(examQuestionsAction.getSp_failure())) {
//                    str = new StringTokenizer(fail_str, "^");
//                } else if (!"".equals(examQuestionsAction.getSp_exists())) {
//                    str = new StringTokenizer(exist_str, "^");
//                }
                if (!"".equals(examQuestionsAction.getSp_failure())) {
                    if (!"".equals(examQuestionsAction.getSp_exists())) {
                        str = new StringTokenizer(fail_str + "^" + exist_str, "^");
                    } else {
                        str = new StringTokenizer(fail_str, "^");
                    }
                } else if (!"".equals(examQuestionsAction.getSp_exists())) {
                    if (!"".equals(examQuestionsAction.getSp_failure())) {
                        str = new StringTokenizer(fail_str + "^" + exist_str, "^");
                    } else {
                        str = new StringTokenizer(exist_str, "^");
                    }
                }

                int columnCount = 1;
                while (str.hasMoreTokens()) {
                    System.out.println("before while------------>");
                    record.add(str.nextToken());
                    System.out.println("after while------------>");
                    columnCount++;
                }
                List<String[]> flist = new ArrayList<String[]>();
                count = record.size();
                CellView cv = new CellView();
                // cv.setAutosize(true);
                int l = 1;

                for (int ki = 0; ki < columnCount - 1; ki++) {
                    // str = new StringTokenizer(record.get(k), "|", true);
                    int i = 0;
                    //  String[] fStringList = new String[k+1];
                    String[] tokens1 = record.get(ki).split("\\|", -1);
                    String[] fStringList = new String[tokens1.length];
                    // for (int i = 0; i < tokens1.length - 1; i++) {
                    for (String string : tokens1) {

                        fStringList[i] = string;

                        i++;
                    }

                    flist.add(fStringList);
                }

                doCreateFailedExcelFile(flist, examQuestionsAction.getPath(), excelColValue, columnCount, type);


            }
//            else if (usersdataHandlerAction.getSp_failure() != "") {
//                //String ecel = resultantExcelFile(usersdataHandlerAction, fileName, fail_str, excelColValue, k);
//            }

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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return res;
    }

    public void doCreateFailedExcelFile(List failedList, String path, int[] colNumbers, int countCol, String type) {
        System.out.println("the path is hear create excel file " + path + "---" + countCol);
        System.out.println("colNumbers" + colNumbers);
        try {
            File fileobj = new File(path);
            Workbook workbook = Workbook.getWorkbook(fileobj);
            Sheet sheet = workbook.getSheet(0);
            int count = sheet.getColumns();
            int rowsCount = sheet.getRows();
            System.out.println("hear i am printkn count-->" + count);
            Cell rows = null;
            String dataArray[] = new String[count];
            for (int j = 0; j < 1; j++) {
                for (int i = 0; i < count; i++) {
                    rows = sheet.getCell(i, j);
                    dataArray[i] = rows.getContents();
                    //System.out.println("printing data--->"+dataArray[i]);
                }

            }




            ListIterator<String[]> litr = null;
            litr = failedList.listIterator();
            int records = failedList.size();
            System.out.println("records" + records);
            String arrayData[] = new String[count];
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File(fileobj.getParent() + File.separator+"resultantFile" + fileobj.getName()));
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
            cellFont.setColour(Colour.PINK);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            cellFormat.setBackground(Colour.BLUE);

            for (int i = 0; i < count; i++) {
                Label label = new Label(i, 0, dataArray[i], cellFormat);
                System.out.println("LABEL--------->" + label);
                wsheet.addCell(label);
            }
            int j = 1;
            while (litr.hasNext()) {
                arrayData = litr.next();
                for (int i = 0; i < count; i++) {

                    if (arrayData[colNumbers[i]] != null && !"null".equalsIgnoreCase(arrayData[colNumbers[i]])) {
                        Label label = new Label(i, j, arrayData[colNumbers[i]]);

                        wsheet.addCell(label);
                        System.out.println(" wsheet.addCell(label)---------->" + label);
                    } else {
                        Label label = new Label(i, j, "");
                        wsheet.addCell(label);
                    }
                }
                j++;

            }
            wworkbook.write();
            wworkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
