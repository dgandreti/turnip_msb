/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.task;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import com.mss.msp.acc.projectsdata.ProjectsVTO;
import com.mss.msp.util.ServiceLocatorException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miracle
 */
public class TaskHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    private String task_id;
    private String task_name;
    private String task_comments;//used for add task also
    private int task_created_by;
    private String task_status;
    private String task_created_date;
    private String docdatepickerfrom;
    private String docdatepicker;
    private List details;
    private Map tasksStatusList;
    private Map tasksRelatedToList;
    /*variables for adding task start*/
    private int taskRelatedTo;
    private int taskType;
    private int taskStatus;
    private String taskName;
    private String startDate;
    private String endDate;
    private int userId;
    private File taskAttachment;
    private String taskAttachmentContentType;
    private String taskAttachmentFileName;
    private String filePath;
    private ServletContext context;
    private int userSessionId;
    private List teamtaskDetails;
    private Map teamMembers;
    private String teamMember;
    private TasksVTO tasksVto;
    private int taskid;
    private String pri_assign_to;
    private int sec_assign_to;
    private String priority;
    private String textcheck;
    private String alertme;
    private String alerttextarea;
    private List taskAttachments;
    private int id;
    private String note_name;
    private String note_comments;
    private int primaryAssign;
    private int secondaryId;
    private String taskPriority;
    private String fullName;
    private String statusName;
    private String primaryMailId;
    private String secondaryMailId;
    private int lastTaskId;
    private String taskCreatedByEmail;
    private int notes_id;
    private String notesName;
    private int sec_reportsId;
    /*variables for adding task start*/
    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Map teamMemberNames;
    /**
     * Added by kbissell Holds the names of the Main/Sub projects that the User
     * is on(user is team member)
     *
     * Holds the selected Main and Sub project
     */
    private List<String> userMainProjects;
    private List<String> userSubProjects;
    private String mainProject;
    private String subProject;
    private String downloadFlag;

    public TaskHandlerAction() {
    }
    private DataSourceDataProvider dataSourceDataProvider;
    private MailManager mailManager = new MailManager();

   
     /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : tasksSearch() method is used to get task details of loggedIn user
     * 
     *
     * *****************************************************************************
     */
    public String tasksSearch() {
        resultType = LOGIN;
        System.out.println("********************TaskHandlerAction :: tasksSearch Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                 String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                Calendar now = Calendar.getInstance();
                now.add(Calendar.MONTH, 1);
                String monthString = "";
                String dateString = "";

                int month = now.get(Calendar.MONTH) + 1;
                int date = now.get(Calendar.DATE);
                if (date < 10) {
                    dateString = "0" + now.get(Calendar.DATE);
                } else {
                    dateString = "" + now.get(Calendar.DATE);
                }
                if (month < 10) {
                    monthString = "0" + (now.get(Calendar.MONTH) + 1);
                } else {
                    monthString = "" + (now.get(Calendar.MONTH) + 1);
                }
                setEndDate(monthString + "-" + dateString + "-" + now.get(Calendar.YEAR));
                //substract months from current date using Calendar.add method
                now = Calendar.getInstance();
                now.add(Calendar.MONTH, -1);

                String startMonthString = "";
                String startDateString = "";

                int startMonth = now.get(Calendar.MONTH) + 1;
                int startDate = now.get(Calendar.DATE);

                if (startDate < 10) {
                    startDateString = "0" + now.get(Calendar.DATE);
                } else {
                    startDateString = "" + now.get(Calendar.DATE);
                }
                if (startMonth < 10) {
                    startMonthString = "0" + (now.get(Calendar.MONTH) + 1);
                } else {
                    startMonthString = "" + (now.get(Calendar.MONTH) + 1);
                }

                setStartDate(startMonthString + "-" + startDateString + "-" + now.get(Calendar.YEAR));

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap(usrPriRole,usrType,getUserSessionId()));
                teamtaskDetails = ServiceLocator.getTaskHandlerService().getLoggedInEmpTasksDetails(this);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: getEmployeeAddress Method End*********************");
        return resultType;
    }

   
     /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : RK Ankireddy
     *
     * ForUse : showTaskSearchDetails() method is used to get task details based on search scenario
     * 
     *
     * *****************************************************************************
     */
    public String showTaskSearchDetails() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: showTaskSearchDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                 String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                
                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap(usrPriRole,usrType,getUserSessionId()));
                teamtaskDetails = ServiceLocator.getTaskHandlerService().getEmployeeTasksDetails(this);

                setEndDate(getDocdatepicker());

                setStartDate(getDocdatepickerfrom());
                if (teamtaskDetails.size() > 0) {
                    resultType = SUCCESS;
                } else {
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: showTaskSearchDetails Method End*********************");
        return resultType;
    }

  
       /**
     * *****************************************************************************
     * Date : 04/20/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getTaskDetails() method is used to get task details and appends on TaskEdit screen
     * 
     *
     * *****************************************************************************
     */
    public String getTaskDetails() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: getTaskDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                 int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                  String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                tasksVto = (ServiceLocator.getTaskHandlerService().getEditTaskDetails(this,usrPriRole,usrType));
                taskAttachments = (dataSourceDataProvider.getInstance().getAttachmentDetails(this));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: getTaskDetails Method End*********************");
        return resultType;
    }

   
    
      /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : addNewTaskDetails() method is used to add new task details in task_list table
     * 
     *
     * *****************************************************************************
     */
    public String addNewTaskDetails() {
       
        resultType = LOGIN;
        int addresult = 0;
         System.out.println("********************TaskHandlerAction :: addNewTaskDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                 setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();

                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap(usrPriRole,usrType,getUserSessionId()));
                if (getTaskAttachmentFileName() == null) {
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

                    //setFilePath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile + File.separator + taskAttachmentFileName);
                    setFilePath(destFile.toString());
                    FileUtils.copyFile(taskAttachment, destFile);
                }

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setFullName(dataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUserSessionId()));
                setStatusName(dataSourceDataProvider.getInstance().getStatusById(getTaskStatus()));
                setSecondaryMailId(dataSourceDataProvider.getInstance().getEmailId(getSecondaryId()));
                setPrimaryMailId(dataSourceDataProvider.getInstance().getEmailId(getPrimaryAssign()));

                addresult = ServiceLocator.getTaskHandlerService().addTaskDetals(this);

                if (addresult > 0) {
                    setLastTaskId(addresult);
                    int result = mailManager.generateTaskAddEmail(this);
                    if (result > 0) {
                    }
                } else {
                   
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: addNewTaskDetails Method End*********************");
        return SUCCESS;
    }

  
      /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getTeamTaskDetails() method is used to get task details in task_list table from team
     * 
     *
     * *****************************************************************************
     */
    public String getTeamTaskDetails() {
        resultType = LOGIN;
        System.out.println("********************TaskHandlerAction :: getTeamTaskDetails Method Start*********************");
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                Calendar now = Calendar.getInstance();
                now.add(Calendar.MONTH, 1);
                String monthString = "";
                String dateString = "";

                int month = now.get(Calendar.MONTH) + 1;
                int date = now.get(Calendar.DATE);
                if (date < 10) {
                    dateString = "0" + now.get(Calendar.DATE);
                } else {
                    dateString = "" + now.get(Calendar.DATE);
                }
                if (month < 10) {
                    monthString = "0" + (now.get(Calendar.MONTH) + 1);
                } else {
                    monthString = "" + (now.get(Calendar.MONTH) + 1);
                }
                setEndDate(monthString + "-" + dateString + "-" + now.get(Calendar.YEAR));

                //substract months from current date using Calendar.add method
                now = Calendar.getInstance();
                now.add(Calendar.MONTH, -1);

                String startMonthString = "";
                String startDateString = "";

                int startMonth = now.get(Calendar.MONTH) + 1;
                int startDate = now.get(Calendar.DATE);

                if (startDate < 10) {
                    startDateString = "0" + now.get(Calendar.DATE);
                } else {
                    startDateString = "" + now.get(Calendar.DATE);
                }
                if (startMonth < 10) {
                    startMonthString = "0" + (now.get(Calendar.MONTH) + 1);
                } else {
                    startMonthString = "" + (now.get(Calendar.MONTH) + 1);
                }

                setStartDate(startMonthString + "-" + startDateString + "-" + now.get(Calendar.YEAR));

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTeamMemberNames(dataSourceDataProvider.getInstance().getMyTeamMembers(getUserSessionId()));
                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap(usrPriRole,usrType,getUserSessionId()));
                teamtaskDetails = ServiceLocator.getTaskHandlerService().getLoggedInTeamTasksDetails(this);
                Iterator i = teamtaskDetails.iterator();
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: getTeamTaskDetails Method End*********************");
        return resultType;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

   
      /**
     * *****************************************************************************
     * Date : 04/15/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : showTeamTaskSearchDetails() method is used to get task details in task_list table based on search criteria
     * 
     *
     * *****************************************************************************
     */
    
    public String showTeamTaskSearchDetails() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: showTeamTaskSearchDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap(usrPriRole,usrType,getUserSessionId()));
                setTeamMemberNames(dataSourceDataProvider.getInstance().getMyTeamMembers(getUserSessionId()));
                teamtaskDetails = ServiceLocator.getTaskHandlerService().getTeamTasksDetails(this);
                
                 setEndDate(getDocdatepicker());

                //substract months from current date using Calendar.add method
                
                setStartDate(getDocdatepickerfrom());
                if (teamtaskDetails.size() > 0) {
                    resultType = SUCCESS;
                } else {
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: showTeamTaskSearchDetails Method End*********************");
        return resultType;
    }

    
     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : addAttachment() method is used to 
     * 
     *
     * *****************************************************************************
     */
    
    public String addAttachment() {
        resultType = LOGIN;
        int addresult = 0;
        System.out.println("********************TaskHandlerAction :: addAttachment Method Start*********************");

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {

                if (getTaskAttachmentFileName() == null) {
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


                    setFilePath(theFile.toString() + "\\");
                    /*copies the file to the destination*/
                    File destFile = new File(theFile, taskAttachmentFileName);
                    FileUtils.copyFile(taskAttachment, destFile);
                }

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                addresult = ServiceLocator.getTaskHandlerService().addAttachmentDetails(this);

                if (addresult > 0) {
               
                } else {
                 
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: addAttachment Method End*********************");
        return SUCCESS;
    }

    
     /**
     * *****************************************************************************
     * Date : 04/21/2015
     *
     * Author : praveen<pkatru@miraclesoft.com> Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : updateTaskDetails() method is used to get task details and appends on TaskEdit screen
     * 
     *
     * *****************************************************************************
     */
    public String updateTaskDetails() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: updateTaskDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int i = ServiceLocator.getTaskHandlerService().updateTaskDetails(this);
                if (getTaskStatus() == 3) {
                    String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
                    setTaskCreatedByEmail(dataSourceDataProvider.getInstance().getEmailId(getTask_created_by()));
                    setSecondaryMailId(dataSourceDataProvider.getInstance().getEmailId(getSec_assign_to()));
                    setPrimaryMailId(dataSourceDataProvider.getInstance().getEmailId(getPrimaryAssign()));
                    setFullName(dataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getTask_created_by()));
                    setStatusName(dataSourceDataProvider.getInstance().getStatusById(getTaskStatus()));
                    int result = mailManager.generateTaskCompleteEmail(this);
                    if (result > 0) {
                    }
                }
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: updateTaskDetails Method End*********************");
        return null;
    }

   
      /**
     * *****************************************************************************
     * Date : 04/22/2015
     *
     * Author : praveen<pkatru@miraclesoft.com> 
     *
     * ForUse : getNotesDetails() method is used to get task details and appends on TaskEdit screen
     * 
     *
     * *****************************************************************************
     */
    public String getNotesDetails() {
        resultType = LOGIN;
        System.out.println("********************TaskHandlerAction :: getNotesDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                String resultMsg = ServiceLocator.getTaskHandlerService().getNotesDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultMsg);
                return null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: getNotesDetails Method End*********************");
        return null;
    }

    
     /**
     * *****************************************************************************
     * Date : 04/22/2015
     *
     * Author : praveen<pkatru@miraclesoft.com> 
     *
     * ForUse : getNotesDetailsOverlay() method is used to get task details and appends on TaskEdit screen
     * 
     *
     * *****************************************************************************
     */
    public String getNotesDetailsOverlay() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: getNotesDetailsOverlay Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                String resultMsg = ServiceLocator.getTaskHandlerService().getNotesDetailsOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultMsg);
                return null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: getNotesDetailsOverlay Method End*********************");
        return null;
    }

   
     /**
     * *****************************************************************************
     * Date : 04/23/2015
     *
     * Author : praveen<pkatru@miraclesoft.com> 
     *
     * ForUse : UpdateNotesDetails() method is used to get task details and appends on TaskEdit screen
     * 
     *
     * *****************************************************************************
     */
    public String UpdateNotesDetails() {
        resultType = LOGIN;
        System.out.println("********************TaskHandlerAction :: UpdateNotesDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int resultMsg = ServiceLocator.getTaskHandlerService().UpdateNotesDetails(this);
                return null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: UpdateNotesDetails Method End*********************");
        return null;
    }

   
     /**
     * *****************************************************************************
     * Date : 04/23/2015
     *
     * Author : praveen<pkatru@miraclesoft.com> 
     *
     * ForUse : DoInsertNotesDetails() method is used to get task details and appends on TaskEdit screen
     * 
     *
     * *****************************************************************************
     */
    public String DoInsertNotesDetails() {
        resultType = LOGIN;
        System.out.println("********************TaskHandlerAction :: DoInsertNotesDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int resultMsg = ServiceLocator.getTaskHandlerService().DoInsertNotesDetails(this);
                return null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: DoInsertNotesDetails Method End*********************");
        return null;
    }

    
      /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : addTask() method is used to add task
     * 
     *
     * *****************************************************************************
     */
    public String addTask() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: addTask Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                 setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String usrType=httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap(usrPriRole,usrType,getUserSessionId()));
                setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                populateProjectsDropDowns();    //Gets Users Main/Sub Projects
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: addTask Method End*********************");
        return resultType;
    }

    private void populateProjectsDropDowns() {

    }

    
     /**
     * *****************************************************************************
     * Date : 04/29/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getNotesDetailsBySearch() method is used to get notes details.
     * 
     *
     * *****************************************************************************
     */
    public String getNotesDetailsBySearch() {
        resultType = LOGIN;
         System.out.println("********************TaskHandlerAction :: getNotesDetailsBySearch Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                String resultMsg = ServiceLocator.getTaskHandlerService().getNotesDetailsBySearch(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultMsg);
                return null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
         System.out.println("********************TaskHandlerAction :: getNotesDetailsBySearch Method End*********************");
        return null;
    }

   
     /**
     * *****************************************************************************
     * Date : 04/22/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getCommentsOnOverlay() method is used to get task details and appends on TaskEdit screen.
     * 
     *
     * *****************************************************************************
     */
    public String getCommentsOnOverlay() {
        resultType = LOGIN;
        System.out.println("********************TaskHandlerAction :: getCommentsOnOverlay Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                String resultMsg = ServiceLocator.getTaskHandlerService().getCommentsOnOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultMsg);
                return null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        System.out.println("********************TaskHandlerAction :: getCommentsOnOverlay Method End*********************");
        return null;
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

    /**
     *
     * This method is used to set the Servlet Response
     *
     * @param httpServletResponse
     */
    public String getResultMessage() {
        return resultMessage;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_comments() {
        return task_comments;
    }

    public void setTask_comments(String task_comments) {
        this.task_comments = task_comments;
    }

    public int getTask_created_by() {
        return task_created_by;
    }

    public void setTask_created_by(int task_created_by) {
        this.task_created_by = task_created_by;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getTask_created_date() {
        return task_created_date;
    }

    public void setTask_created_date(String task_created_date) {
        this.task_created_date = task_created_date;
    }

    public List getDetails() {
        return details;
    }

    public void setDetails(List details) {
        this.details = details;
    }

    /**
     * @return the tasksStatusList
     */
    public Map getTasksStatusList() {
        return tasksStatusList;
    }

    /**
     * @param tasksStatusList the tasksStatusList to set
     */
    public void setTasksStatusList(Map tasksStatusList) {
        this.tasksStatusList = tasksStatusList;
    }

    public Map getTasksRelatedToList() {
        return tasksRelatedToList;
    }

    public void setTasksRelatedToList(Map tasksRelatedToList) {
        this.tasksRelatedToList = tasksRelatedToList;
    }

    public String getDocdatepickerfrom() {
        return docdatepickerfrom;
    }

    public void setDocdatepickerfrom(String docdatepickerfrom) {
        this.docdatepickerfrom = docdatepickerfrom;
    }

    public String getDocdatepicker() {
        return docdatepicker;
    }

    public void setDocdatepicker(String docdatepicker) {
        this.docdatepicker = docdatepicker;
    }

    public int getTaskRelatedTo() {
        return taskRelatedTo;
    }

    public void setTaskRelatedTo(int taskRelatedTo) {
        this.taskRelatedTo = taskRelatedTo;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public File getTaskAttachment() {
        return taskAttachment;
    }

    public void setTaskAttachment(File taskAttachment) {
        this.taskAttachment = taskAttachment;
    }

    public String getTaskAttachmentContentType() {
        return taskAttachmentContentType;
    }

    public void setTaskAttachmentContentType(String taskAttachmentContentType) {
        this.taskAttachmentContentType = taskAttachmentContentType;
    }

    public String getTaskAttachmentFileName() {
        return taskAttachmentFileName;
    }

    public void setTaskAttachmentFileName(String taskAttachmentFileName) {
        this.taskAttachmentFileName = taskAttachmentFileName;
    }
    ServletContext sc;

    public void setServletContext(ServletContext sc) {
        this.sc = sc;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public List getTeamtaskDetails() {
        return teamtaskDetails;
    }

    public void setTeamtaskDetails(List teamtaskDetails) {
        this.teamtaskDetails = teamtaskDetails;
    }

    public Map getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Map teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Map getTeamMemberNames() {
        return teamMemberNames;
    }

    public void setTeamMemberNames(Map teamMemberNames) {
        this.teamMemberNames = teamMemberNames;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public TasksVTO getTasksVto() {
        return tasksVto;
    }

    public void setTasksVto(TasksVTO tasksVto) {
        this.tasksVto = tasksVto;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getPri_assign_to() {
        return pri_assign_to;
    }

    public void setPri_assign_to(String pri_assign_to) {
        this.pri_assign_to = pri_assign_to;
    }

    public int getSec_assign_to() {
        return sec_assign_to;
    }

    public void setSec_assign_to(int sec_assign_to) {
        this.sec_assign_to = sec_assign_to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTextcheck() {
        return textcheck;
    }

    public void setTextcheck(String textcheck) {
        this.textcheck = textcheck;
    }

    public String getAlertme() {
        return alertme;
    }

    public void setAlertme(String alertme) {
        this.alertme = alertme;
    }

    public String getAlerttextarea() {
        return alerttextarea;
    }

    public void setAlerttextarea(String alerttextarea) {
        this.alerttextarea = alerttextarea;
    }

    public List getTaskAttachments() {
        return taskAttachments;
    }

    public void setTaskAttachments(List taskAttachments) {
        this.taskAttachments = taskAttachments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getNote_comments() {
        return note_comments;
    }

    public void setNote_comments(String note_comments) {
        this.note_comments = note_comments;
    }

    public int getPrimaryAssign() {
        return primaryAssign;
    }

    public void setPrimaryAssign(int primaryAssign) {
        this.primaryAssign = primaryAssign;
    }

    public int getSecondaryId() {
        return secondaryId;
    }

    public void setSecondaryId(int secondaryId) {
        this.secondaryId = secondaryId;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPrimaryMailId() {
        return primaryMailId;
    }

    public void setPrimaryMailId(String primaryMailId) {
        this.primaryMailId = primaryMailId;
    }

    public String getSecondaryMailId() {
        return secondaryMailId;
    }

    public void setSecondaryMailId(String secondaryMailId) {
        this.secondaryMailId = secondaryMailId;
    }

    public int getLastTaskId() {
        return lastTaskId;
    }

    public void setLastTaskId(int lastTaskId) {
        this.lastTaskId = lastTaskId;
    }

    public String getTaskCreatedByEmail() {
        return taskCreatedByEmail;
    }

    public void setTaskCreatedByEmail(String taskCreatedByEmail) {
        this.taskCreatedByEmail = taskCreatedByEmail;
    }

    public int getNotes_id() {
        return notes_id;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
    }

    public String getNotesName() {
        return notesName;
    }

    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }

    public int getSec_reportsId() {
        return sec_reportsId;
    }

    public void setSec_reportsId(int sec_reportsId) {
        this.sec_reportsId = sec_reportsId;
    }

    public List<String> getUserMainProjects() {
        return userMainProjects;
    }

    public void setUserMainProjects(List<String> userMainProjects) {
        this.userMainProjects = userMainProjects;
    }

    public List<String> getUserSubProjects() {
        return userSubProjects;
    }

    public void setUserSubProjects(List<String> userSubProjects) {
        this.userSubProjects = userSubProjects;
    }

    public String getMainProject() {
        return mainProject;
    }

    public void setMainProject(String mainProject) {
        this.mainProject = mainProject;
    }

    public String getSubProject() {
        return subProject;
    }

    public void setSubProject(String subProject) {
        this.subProject = subProject;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }
}
