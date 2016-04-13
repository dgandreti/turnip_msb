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
import java.util.logging.SimpleFormatter;
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
    String queryString = "";
    private static Logger reportsLog = Logger.getLogger(ExamQuestionsServiceImpl.class.getName());

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doQuestionsSearch() method is used to
     *
     *
     * *****************************************************************************
     */
    public List doQuestionsSearch(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        List<ExamQuesVTO> ExamQuesVTOList = new ArrayList<ExamQuesVTO>();
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************ExamQuestionsServiceImpl :: doQuestionsSearch Method Start*********************");
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT DISTINCT question,id,skillid,status,level,question_type,ispictorial FROM sb_onlineexamQuestion WHERE orgid=" + examQuestionsAction.getUserOrgSessionId() + " ORDER BY createddate DESC LIMIT 100";

            System.out.println("doQuestionsSearch :: query string ------>" + queryString);
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
        System.out.println("********************ExamQuestionsServiceImpl :: doQuestionsSearch Method End*********************");
        return ExamQuesVTOList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addOrEditExamQuestionsEdit() method is used to
     *
     *
     * *****************************************************************************
     */
    public String addOrEditExamQuestionsEdit(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        String resultString = "";
        int result = 0;
        String filePath1 = "";
        String filePath2 = "";
        Connection connection = null;
        CallableStatement callableStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************ExamQuestionsServiceImpl :: addOrEditExamQuestionsEdit Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            callableStatement = connection.prepareCall("{call sp_onlineExamQuestionAddOrEdit(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            System.out.println("addOrEditExamQuestionsEdit :: procedure name : sp_onlineExamQuestionAddOrEdit");
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

                String isPicture = "";
                isPicture = examQuestionsAction.getIsPic();
                if (isPicture.equals("false")) {
                    callableStatement.setInt(23, 0);
                } else {
                    callableStatement.setInt(23, 1);
                }
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
                    if (filePath2 != null) {
                        File destFile1 = new File(filePath2);
                        destFile1.delete();
                    }
                    if (filePath1 != null) {
                        File destFile = new File(filePath1);
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
                if (filePath1 != null) {
                    File destFile = new File(filePath1);
                    FileUtils.copyFile(examQuestionsAction.getQuesImage(), destFile);
                }
            }
            if (result > 0) {
                resultString = "Success";
            } else {
                resultString = "Fail";
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
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
        System.out.println("********************ExamQuestionsServiceImpl :: addOrEditExamQuestionsEdit Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doExamQuestionsEdit() method is used to
     *
     *
     * *****************************************************************************
     */
    public ExamQuesVTO doExamQuestionsEdit(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        List<ExamQuesVTO> ExamQuesVTOList = new ArrayList<ExamQuesVTO>();
        ExamQuesVTO examQuesVTO = new ExamQuesVTO();
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************ExamQuestionsServiceImpl :: doExamQuestionsEdit Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT question,skillid,STATUS,LEVEL,question_type,option1,option2,option3,option4,option5,option6,ans1,ans2,ans3,ans4,ans5,ans6,explanation,exam_type,ispictorial,question_path FROM sb_onlineexamQuestion ques JOIN sb_onlineexamqusans ans ON  ans.questionid=ques.id WHERE ques.id=" + examQuestionsAction.getQuesId();

            System.out.println("doExamQuestionsEdit :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                examQuesVTO.setQuestion(resultSet.getString("question"));
                examQuesVTO.setSkillCategoryValue1(resultSet.getInt("skillid"));
                examQuesVTO.setStatus(resultSet.getString("status"));
                examQuesVTO.setLevel(resultSet.getString("LEVEL"));

                examQuesVTO.setOption1(resultSet.getString("option1"));
                examQuesVTO.setOption2(resultSet.getString("option2"));
                examQuesVTO.setOption3(resultSet.getString("option3"));
                examQuesVTO.setOption4(resultSet.getString("option4"));
                examQuesVTO.setOption5(resultSet.getString("option5"));
                examQuesVTO.setOption6(resultSet.getString("option6"));
                examQuesVTO.setAnswerType(resultSet.getString("question_type"));
                if (examQuesVTO.getAnswerType().equals("M")) {
                    if (resultSet.getString("ans1").equals("0")) {

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

                examQuesVTO.setExplanation(resultSet.getString("explanation"));
                examQuesVTO.setExamType(resultSet.getString("exam_type"));
                if (resultSet.getString("ispictorial").equals("0")) {
                    examQuesVTO.setIsPic(false);
                } else {
                    examQuesVTO.setIsPic(true);
                }
                examQuesVTO.setQuestion_path(resultSet.getString("question_path"));

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
        System.out.println("********************ExamQuestionsServiceImpl :: doExamQuestionsEdit Method End*********************");
        return examQuesVTO;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doQuestionsSearchList() method is used to
     *
     *
     * *****************************************************************************
     */
    public List doQuestionsSearchList(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        List<ExamQuesVTO> ExamQuesVTOList = new ArrayList<ExamQuesVTO>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        System.out.println("********************ExamQuestionsServiceImpl :: doQuestionsSearchList Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT DISTINCT question,id,skillid,status,level,question_type,ispictorial FROM sb_onlineexamQuestion WHERE orgid=" + examQuestionsAction.getUserOrgSessionId();
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

            System.out.println("doQuestionsSearchList :: query string ------>" + queryString);
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
        System.out.println("********************ExamQuestionsServiceImpl :: doQuestionsSearchList Method End*********************");
        return ExamQuesVTOList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getImagePath() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getImagePath(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException {
        String resultString = "";
        System.out.println("********************ExamQuestionsServiceImpl :: getImagePath Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT question_path FROM sb_onlineexamQuestion WHERE id=" + examQuestionsAction.getQuesId();

            System.out.println("getImagePath :: query string ------>" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                resultString += resultSet.getString("question_path");
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("********************ExamQuestionsServiceImpl :: getImagePath Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getCellContentValues() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getCellContentValues(List list, ExamQuestionsAction examQuestionsAction, int count, String type, String columsString) throws ServiceLocatorException {
        String exist_str = "";
        String fail_str = "";
        String result = "";
        String res = "";
        String fileName = "";
        String finalPath = "";
        Handler fileHandler = null;
        String status = "Success";
        String comments = "";
        int NumberOfRecords = 0;
        int failCount = 0;
        int existCount = 0;
        StringTokenizer st2 = new StringTokenizer(examQuestionsAction.getPath(), File.separator);
        Connection connection = null;
        CallableStatement callableStatement = null;

        System.out.println("********************ExamQuestionsServiceImpl :: getCellContentValues Method Start*********************");
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
            NumberOfRecords = NumberOfRecords + 1;
        }
        try {
            if ("skills".equalsIgnoreCase(type)) {

                connection = ConnectionProvider.getInstance().getConnection();
                callableStatement = connection.prepareCall("{call sp_onlineExamQuestionUpload(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                System.out.println("getCellContentValues :: procedure name : sp_onlineExamQuestionUpload");
                callableStatement.setString(1, accRecord);
                callableStatement.setString(2, "^");
                callableStatement.setString(3, examQuestionsAction.getFilePath());
                callableStatement.setString(4, fileName);
                callableStatement.setInt(5, examQuestionsAction.getSkillCategoryValue());
                callableStatement.setInt(6, examQuestionsAction.getUserSessionId());
                callableStatement.setInt(7, examQuestionsAction.getUserOrgSessionId());
                callableStatement.setString(8, File.separator);
                callableStatement.registerOutParameter(9, Types.VARCHAR);
                callableStatement.registerOutParameter(10, Types.VARCHAR);
                callableStatement.registerOutParameter(11, Types.VARCHAR);
                callableStatement.registerOutParameter(12, Types.INTEGER);
                callableStatement.registerOutParameter(13, Types.INTEGER);
                callableStatement.execute();
                exist_str = callableStatement.getString(9);
                fail_str = callableStatement.getString(10);
                result = callableStatement.getString(11);
                failCount = callableStatement.getInt(12);
                existCount = callableStatement.getInt(13);
            }
            examQuestionsAction.setSp_res(result);
            examQuestionsAction.setSp_exists(exist_str);
            examQuestionsAction.setSp_failure(fail_str);

            if (!"".equals(examQuestionsAction.getSp_exists()) || !"".equals(examQuestionsAction.getSp_failure())) {
                int k = 1;
                StringTokenizer coulmsdata = new StringTokenizer(columsString, "|");
                int excelColValue[] = new int[50];

                if ("skills".equalsIgnoreCase(type)) {
                    int c = 0;
                    while (c < 17) {
                        c = c + 1;
                        excelColValue[k] = c;
                        k++;
                    }
                }
                List<String> record = new ArrayList<String>();
                StringTokenizer str = null;

                if (!"".equals(examQuestionsAction.getSp_failure())) {
                    if (!"".equals(examQuestionsAction.getSp_exists())) {
                        str = new StringTokenizer(fail_str + "^" + exist_str, "^");
                        comments = "some failure records and some Invalid records";
                        status = "Un-success";
                    } else {
                        str = new StringTokenizer(fail_str, "^");
                        comments = "failure records";
                        status = "Un-success";
                    }
                } else if (!"".equals(examQuestionsAction.getSp_exists())) {
                    if (!"".equals(examQuestionsAction.getSp_failure())) {
                        str = new StringTokenizer(fail_str + "^" + exist_str, "^");
                        comments = "some failure records and some Invalid records";
                        status = "Un-success";
                    } else {
                        str = new StringTokenizer(exist_str, "^");
                        comments = "Invalid records";
                        status = "Un-success";
                    }
                }

                int columnCount = 1;
                while (str.hasMoreTokens()) {
                    record.add(str.nextToken());
                    columnCount++;
                }
                List<String[]> flist = new ArrayList<String[]>();
                count = record.size();
                CellView cv = new CellView();
                int l = 1;
                for (int ki = 0; ki < columnCount - 1; ki++) {
                    int i = 0;
                    String[] tokens1 = record.get(ki).split("\\|", -1);
                    String[] fStringList = new String[tokens1.length];
                    for (String string : tokens1) {
                        fStringList[i] = string;
                        i++;
                    }

                    flist.add(fStringList);
                }
                doCreateFailedExcelFile(flist, examQuestionsAction.getPath(), excelColValue, columnCount, type);
            }
            fileHandler = new FileHandler(examQuestionsAction.getPath() + ".log");
            reportsLog.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            int uploadedCount = 0;
            uploadedCount = NumberOfRecords - (failCount + existCount);
            reportsLog.info("-- =================== Accounts uploading logger ================================\n"
                    + "Created date:" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime()
                    + "\nCreated By: " + examQuestionsAction.getSessionFirstName() + " " + examQuestionsAction.getSessionLastName()
                    + "\nOrganization :MSB.Inc\nPurpose : To upload " + type + "\nNumber of records :" + NumberOfRecords
                    + "\nUploaded records :" + uploadedCount
                    + "\nFailed records :" + failCount
                    + "\nInvalid records :" + existCount
                    + "\nStatus : " + status + " \ncomments : " + comments
                    + "\n-- ===============================================================================");

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
        System.out.println("********************ExamQuestionsServiceImpl :: getCellContentValues Method End*********************");
        return res;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doCreateFailedExcelFile() method is used to
     *
     *
     * *****************************************************************************
     */
    public void doCreateFailedExcelFile(List failedList, String path, int[] colNumbers, int countCol, String type) {
        System.out.println("********************ExamQuestionsServiceImpl :: doCreateFailedExcelFile Method Start*********************");
        try {
            File fileobj = new File(path);
            Workbook workbook = Workbook.getWorkbook(fileobj);
            Sheet sheet = workbook.getSheet(0);
            int count = sheet.getColumns();
            int rowsCount = sheet.getRows();
            Cell rows = null;
            String dataArray[] = new String[count];
            for (int j = 0; j < 1; j++) {
                for (int i = 0; i < count; i++) {
                    rows = sheet.getCell(i, j);
                    dataArray[i] = rows.getContents();
                }
            }
            ListIterator<String[]> litr = null;
            litr = failedList.listIterator();
            int records = failedList.size();
            String arrayData[] = new String[count];
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File(fileobj.getParent() + File.separator + "resultantFile" + fileobj.getName()));
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
            cellFont.setColour(Colour.PINK);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
            cellFormat.setBackground(Colour.BLUE);
            for (int i = 0; i < count; i++) {
                Label label = new Label(i, 0, dataArray[i], cellFormat);
                wsheet.addCell(label);
            }
            int j = 1;
            while (litr.hasNext()) {
                arrayData = litr.next();
                for (int i = 0; i < count; i++) {

                    if (arrayData[colNumbers[i]] != null && !"null".equalsIgnoreCase(arrayData[colNumbers[i]])) {
                        Label label = new Label(i, j, arrayData[colNumbers[i]]);
                        wsheet.addCell(label);
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
        System.out.println("********************ExamQuestionsServiceImpl :: doCreateFailedExcelFile Method End*********************");
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : logSearch() method is used to
     *
     *
     * *****************************************************************************
     */
    public List logSearch(ExamQuestionsAction examQuestionsAction, int sessionusrPrimaryrole) throws ServiceLocatorException {
        String result = "";
        ArrayList utilityList = new ArrayList();
        String current_date = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        System.out.println("********************ExamQuestionsServiceImpl :: logSearch Method Start*********************");

        queryString = "SELECT * FROM utility_logger WHERE 1=1";
        if (sessionusrPrimaryrole == 2 || sessionusrPrimaryrole == 8) {
            queryString = queryString + " AND created_by = " + examQuestionsAction.getUserSessionId();
        }
        queryString = queryString + " ORDER BY created_date DESC";

        System.out.println("logSearch :: query string ------>" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                UserVTO users = new UserVTO();
                users.setLogger(resultSet.getString("logger"));
                users.setLog_id(resultSet.getInt("log_id"));
                users.setUtility_status(resultSet.getString("utility_status"));
                users.setUploaded_file(resultSet.getString("uploaded_file").substring(resultSet.getString("uploaded_file").lastIndexOf(File.separator) + 1));
                users.setResultant_file(resultSet.getString("resultant_file").substring(resultSet.getString("resultant_file").lastIndexOf(File.separator) + 1));
                users.setLoggerCreatedDate(DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("created_date")));
                users.setLoggerFlag(examQuestionsAction.getLoggerFlag());
                utilityList.add(users);
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
        System.out.println("********************ExamQuestionsServiceImpl :: logSearch Method End*********************");
        return utilityList;
    }
}
