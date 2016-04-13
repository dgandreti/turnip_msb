/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.onlineexam.questions;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class ExamQuestionsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    private String resultMessage;
    private int userSessionId;
    private int resultFlag;
    private int userid;
    private int userId;
    private int userOrgSessionId;
    private String accountSearchID;
    private String filePath;
    private File xlsfile;
    private String xlsfileContentType;
    private String xlsfileFileName;
    private int columnValue;
    private String sp_res;
    private String sp_exists;
    private String sp_failure;
    private int excel_id;
    private List utility_logger;
    private String createdDate;
    private String FileExist;
    private String loggerFlag;
    private String downloadFlag;
    private String loadingFileType;
    private int accountSearchOrgId;
    private Map skillValuesMap;
    private int skillCategoryValue;
    private String question;
    private String level;
    private String status;
    private String answerType;
    private int quesId;
    private String addEdit;
    private File fileWithPath;
    private Map rowMap;
    private Map columnsMap;
    public String path;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private List<ExamQuesVTO> skills;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String explanation;
    private String examType;
    private int skillCategoryValue1;
    private String answer;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private String ans5;
    private String ans6;
    private ExamQuesVTO editQues;
    private String successMsg;
    private String isPic;
    private String newFilePath;
    private String oldPath;
    private int examTypeFlag;
    private File quesImage;
    private String quesImageContentType;
    private String quesImageFileName;
    private String sessionFirstName;
    private String sessionLastName;

    public ExamQuestionsAction() {
    }
    private DataSourceDataProvider dataSourceDataProvider;
    
     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : getQuestionsList() method is used to 
     * 
     *
     * *****************************************************************************
     */

    public String getQuestionsList() {
        System.out.println("********************ExamQuestionsAction :: getQuestionsList Method Start*********************");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                skills = ServiceLocator.getExamQuestionsHandlerservice().doQuestionsSearch(this);
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************ExamQuestionsAction :: getQuestionsList Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : getQuestionsSearchList() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String getQuestionsSearchList() {
        System.out.println("********************ExamQuestionsAction :: getQuestionsSearchList Method Start*********************");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                skills = ServiceLocator.getExamQuestionsHandlerservice().doQuestionsSearchList(this);
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************ExamQuestionsAction :: getQuestionsSearchList Method End*********************");
        return resultMessage;
    }

     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : doEditExamQues() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String doEditExamQues() {
        resultType = LOGIN;
        System.out.println("********************ExamQuestionsAction :: doEditExamQues Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                if (getQuesId() >= 0) {
                    editQues = ServiceLocator.getExamQuestionsHandlerservice().doExamQuestionsEdit(this);
                }
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************ExamQuestionsAction :: doEditExamQues Method End*********************");
        return resultType;
    }

     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : storeAddOrEditExamQues() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String storeAddOrEditExamQues() {
        resultType = LOGIN;
         System.out.println("********************ExamQuestionsAction :: storeAddOrEditExamQues Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                if (getQuesImageFileName() == null) {
                } else {
                    filePath = Properties.getProperty("QUESTIONHOMEPATH");
                    File createPath = new File(filePath);
                    Date dt = new Date();
                    /*The month is generated from here*/
                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }

                    short week = (short) (Math.round(dt.getDate() / 7));
                    createPath = new File(createPath.getAbsolutePath() + File.separator + getUserOrgSessionId() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    createPath.mkdir();
                    File theFile = new File(createPath.getAbsolutePath() + File.separator + getQuesImageFileName());
                    setFilePath(theFile.toString());
                }

                addEdit = ServiceLocator.getExamQuestionsHandlerservice().addOrEditExamQuestionsEdit(this);

                if (addEdit.equals("Success") && getQuesId() > 0) {
                    setSuccessMsg("Question Updated Successfully");
                } else if (addEdit.equals("Success") && getQuesId() == 0) {
                    setSuccessMsg("Question Added Successfully");
                }
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************ExamQuestionsAction :: storeAddOrEditExamQues Method End*********************");
        return resultType;
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
    public String getImagePath() {
        resultMessage = LOGIN;
        String responseString = "";
        System.out.println("********************ExamQuestionsAction :: getImagePath Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                responseString = ServiceLocator.getExamQuestionsHandlerservice().getImagePath(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(responseString);
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************ExamQuestionsAction :: getImagePath Method End*********************");
        return null;
    }

     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : getSkillValuesOnChange() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String getSkillValuesOnChange() {

        resultMessage = LOGIN;
        String responseString = "";
        System.out.println("********************ExamQuestionsAction :: getSkillValuesOnChange Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                Map SkillValuesMap = dataSourceDataProvider.getInstance().getReqSkillsCategory(examTypeFlag);
                StringBuffer buffer = new StringBuffer();
                Iterator<Map.Entry<Integer, String>> entryIterator = SkillValuesMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<Integer, String> entry = entryIterator.next();
                    buffer.append(entry.getKey());
                    buffer.append("|");
                    buffer.append(entry.getValue());
                    if (entryIterator.hasNext()) {
                        buffer.append("^");
                    }
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(buffer.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************ExamQuestionsAction :: getSkillValuesOnChange Method End*********************");
        return null;
    }

     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : getUploadQuestions() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String getUploadQuestions() {
        resultType = LOGIN;
         System.out.println("********************ExamQuestionsAction :: getUploadQuestions Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************ExamQuestionsAction :: getUploadQuestions Method End*********************");
        return resultType;
    }

     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : doOnlineExamQuestionsXlsUpload() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String doOnlineExamQuestionsXlsUpload() {
        resultType = LOGIN;
        System.out.println("********************ExamQuestionsAction :: doOnlineExamQuestionsXlsUpload Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionFirstName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.FIRST_NAME).toString());
                setSessionLastName(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Last_NAME).toString());

                if (getXlsfileFileName() == null) {
                } else {
                    filePath = Properties.getProperty("Task.Attachment");
                    File createPath = new File(filePath);
                    Date dt = new Date();
                    /*The month is generated from here*/
                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }
                    short week = (short) (Math.round(dt.getDate() / 7));
                    /*getrequestType is used to create a directory of the object type specified in the jsp page*/
                    createPath = new File(createPath.getAbsolutePath() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    /*This creates a directory forcefully if the directory does not exsist*/

                    createPath.mkdir();
                    /*here it takes the absolute path and the name of the file that is to be uploaded*/
                    File theFile = new File(createPath.getAbsolutePath());

                    setFilePath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile + File.separator + xlsfileFileName);
                    FileUtils.copyFile(xlsfile, destFile);
                }
                setPath(getFilePath() + File.separator + xlsfileFileName);
                setFileWithPath(new File(getFilePath() + File.separator + xlsfileFileName));
                String FileNameExist = ServiceLocator.getUserAjaxHandlerService().checkFileName(xlsfileFileName);
                if ("Exist".equals(FileNameExist)) {
                    setFileExist("File Name Already Exists!!");
                    resultType = INPUT;
                } else {
                    WorkbookSettings ws = new WorkbookSettings();

                    Workbook workbook = Workbook.getWorkbook(getFileWithPath(), ws);
                    Sheet sheet = workbook.getSheet(0);
                    Cell rows = null;
                    List<String> rowList = new ArrayList<String>();
                    Map rowMap = new HashMap();
                    String stringForBatch = "";
                    int columnCount = 0;
                    int count = sheet.getRows();
                    columnCount = sheet.getColumns();
                    List<String[]> list = new ArrayList<String[]>();
                    ArrayList<String> list1 = new ArrayList<String>();
                    for (int column = 0; column < columnCount; column++) {
                        stringForBatch = stringForBatch + (sheet.getCell(column, 0).getContents()) + "|";

                    }
                    stringForBatch = StringUtils.chop(stringForBatch);
                    StringTokenizer st = new StringTokenizer(stringForBatch, "|");
                    int k = 0;
                    k = st.countTokens();
                    int colCount = sheet.getColumns();
                    int rowsCount = sheet.getRows();
                    Cell cellValue = null;

                    Map columnsMap = new HashMap();
                    for (int row = 1; row < rowsCount; row++) {
                        String dataArray[] = new String[colCount];
                        for (int column = 0; column < colCount; column++) {

                            cellValue = sheet.getCell(column, row);
                            if (!cellValue.getContents().contains("|") && !cellValue.getContents().contains("^")) {
                                dataArray[column] = cellValue.getContents().trim() + "";
                            } else {
                                dataArray[column] = "";
                            }
                        }
                        list.add(dataArray);
                    }

                    //Close and free allocated memory 
                    workbook.close();
                    String res = ServiceLocator.getExamQuestionsHandlerservice().getCellContentValues(list, this, k, "skills", stringForBatch);
                    int sessionusrPrimaryrole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                    utility_logger = ServiceLocator.getExamQuestionsHandlerservice().logSearch(this, sessionusrPrimaryrole);

                    resultType = SUCCESS;
                }

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************ExamQuestionsAction :: doOnlineExamQuestionsXlsUpload Method End*********************");
        return resultType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public int getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(int resultFlag) {
        this.resultFlag = resultFlag;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserOrgSessionId() {
        return userOrgSessionId;
    }

    public void setUserOrgSessionId(int userOrgSessionId) {
        this.userOrgSessionId = userOrgSessionId;
    }

    public String getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(String accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getXlsfile() {
        return xlsfile;
    }

    public void setXlsfile(File xlsfile) {
        this.xlsfile = xlsfile;
    }

    public String getXlsfileContentType() {
        return xlsfileContentType;
    }

    public void setXlsfileContentType(String xlsfileContentType) {
        this.xlsfileContentType = xlsfileContentType;
    }

    public String getXlsfileFileName() {
        return xlsfileFileName;
    }

    public void setXlsfileFileName(String xlsfileFileName) {
        this.xlsfileFileName = xlsfileFileName;
    }

    public int getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(int columnValue) {
        this.columnValue = columnValue;
    }

    public String getSp_res() {
        return sp_res;
    }

    public void setSp_res(String sp_res) {
        this.sp_res = sp_res;
    }

    public String getSp_exists() {
        return sp_exists;
    }

    public void setSp_exists(String sp_exists) {
        this.sp_exists = sp_exists;
    }

    public String getSp_failure() {
        return sp_failure;
    }

    public void setSp_failure(String sp_failure) {
        this.sp_failure = sp_failure;
    }

    public int getExcel_id() {
        return excel_id;
    }

    public void setExcel_id(int excel_id) {
        this.excel_id = excel_id;
    }

    public List getUtility_logger() {
        return utility_logger;
    }

    public void setUtility_logger(List utility_logger) {
        this.utility_logger = utility_logger;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFileExist() {
        return FileExist;
    }

    public void setFileExist(String FileExist) {
        this.FileExist = FileExist;
    }

    public String getLoggerFlag() {
        return loggerFlag;
    }

    public void setLoggerFlag(String loggerFlag) {
        this.loggerFlag = loggerFlag;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getLoadingFileType() {
        return loadingFileType;
    }

    public void setLoadingFileType(String loadingFileType) {
        this.loadingFileType = loadingFileType;
    }

    public int getAccountSearchOrgId() {
        return accountSearchOrgId;
    }

    public void setAccountSearchOrgId(int accountSearchOrgId) {
        this.accountSearchOrgId = accountSearchOrgId;
    }

    public Map getSkillValuesMap() {
        return skillValuesMap;
    }

    public void setSkillValuesMap(Map skillValuesMap) {
        this.skillValuesMap = skillValuesMap;
    }

    public int getSkillCategoryValue() {
        return skillCategoryValue;
    }

    public void setSkillCategoryValue(int skillCategoryValue) {
        this.skillCategoryValue = skillCategoryValue;
    }

    public File getFileWithPath() {
        return fileWithPath;
    }

    public void setFileWithPath(File fileWithPath) {
        this.fileWithPath = fileWithPath;
    }

    public Map getRowMap() {
        return rowMap;
    }

    public void setRowMap(Map rowMap) {
        this.rowMap = rowMap;
    }

    public Map getColumnsMap() {
        return columnsMap;
    }

    public void setColumnsMap(Map columnsMap) {
        this.columnsMap = columnsMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public DataSourceDataProvider getDataSourceDataProvider() {
        return dataSourceDataProvider;
    }

    public void setDataSourceDataProvider(DataSourceDataProvider dataSourceDataProvider) {
        this.dataSourceDataProvider = dataSourceDataProvider;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     *
     * This method is used to set the Servlet Response
     *
     * @param httpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public List<ExamQuesVTO> getSkills() {
        return skills;
    }

    public void setSkills(List<ExamQuesVTO> skills) {
        this.skills = skills;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public ExamQuesVTO getEditQues() {
        return editQues;
    }

    public void setEditQues(ExamQuesVTO editQues) {
        this.editQues = editQues;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getAddEdit() {
        return addEdit;
    }

    public void setAddEdit(String addEdit) {
        this.addEdit = addEdit;
    }

    public int getSkillCategoryValue1() {
        return skillCategoryValue1;
    }

    public void setSkillCategoryValue1(int skillCategoryValue1) {
        this.skillCategoryValue1 = skillCategoryValue1;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getAns5() {
        return ans5;
    }

    public void setAns5(String ans5) {
        this.ans5 = ans5;
    }

    public String getAns6() {
        return ans6;
    }

    public void setAns6(String ans6) {
        this.ans6 = ans6;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getIsPic() {
        return isPic;
    }

    public void setIsPic(String isPic) {
        this.isPic = isPic;
    }

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public int getExamTypeFlag() {
        return examTypeFlag;
    }

    public void setExamTypeFlag(int examTypeFlag) {
        this.examTypeFlag = examTypeFlag;
    }

    public File getQuesImage() {
        return quesImage;
    }

    public void setQuesImage(File quesImage) {
        this.quesImage = quesImage;
    }

    public String getQuesImageContentType() {
        return quesImageContentType;
    }

    public void setQuesImageContentType(String quesImageContentType) {
        this.quesImageContentType = quesImageContentType;
    }

    public String getQuesImageFileName() {
        return quesImageFileName;
    }

    public void setQuesImageFileName(String quesImageFileName) {
        this.quesImageFileName = quesImageFileName;
    }

    public String getSessionFirstName() {
        return sessionFirstName;
    }

    public void setSessionFirstName(String sessionFirstName) {
        this.sessionFirstName = sessionFirstName;
    }

    public String getSessionLastName() {
        return sessionLastName;
    }

    public void setSessionLastName(String sessionLastName) {
        this.sessionLastName = sessionLastName;
    }
}
