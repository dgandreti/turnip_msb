/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.timesheets;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.mss.msp.util.MailManager;
import java.util.Calendar;

/**
 *
 * @author miracle
 */
public class UsrTimeSheetAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    private int userSessionId;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int timesheetid;
    private String resultMessage;
    private String startDate;
    private String endDate;
    private String sqlQuery;
    private String userId;
    private String tmstatus;
    private String reportingPerson;
    private String timeSheetStartDate;
    private String timeSheetEndDate;
    private String timeSheetSubmittedDate;
    private Double projectNameSun1;
    private Double projectNameMon1;
    private Double projectNameTue1;
    private Double projectNameWed1;
    private Double projectNameThu1;
    private Double projectNameFri1;
    private Double projectNameSat1;
    private Double projectNameSun2;
    private Double projectNameMon2;
    private Double projectNameTue2;
    private Double projectNameWed2;
    private Double projectNameThu2;
    private Double projectNameFri2;
    private Double projectNameSat2;
    private Double projectNameSun3;
    private Double projectNameMon3;
    private Double projectNameTue3;
    private Double projectNameWed3;
    private Double projectNameThu3;
    private Double projectNameFri3;
    private Double projectNameSat3;
    private Double projectNameSun4;
    private Double projectNameMon4;
    private Double projectNameTue4;
    private Double projectNameWed4;
    private Double projectNameThu4;
    private Double projectNameFri4;
    private Double projectNameSat4;
    private Double projectNameSun5;
    private Double projectNameMon5;
    private Double projectNameTue5;
    private Double projectNameWed5;
    private Double projectNameThu5;
    private Double projectNameFri5;
    private Double projectNameSat5;
    private Double internalSun;
    private Double internalMon;
    private Double internalTue;
    private Double internalWed;
    private Double internalThu;
    private Double internalFri;
    private Double internalSat;
    private Double vacationSun;
    private Double vacationMon;
    private Double vacationTue;
    private Double vacationWed;
    private Double vacationThu;
    private Double vacationFri;
    private Double vacationSat;
    private Double holidaySun;
    private Double holidayMon;
    private Double holidayTue;
    private Double holidayWed;
    private Double holidayThu;
    private Double holidayFri;
    private Double holidaySat;
    private Double comptimeSun;
    private Double comptimeMon;
    private Double comptimeTue;
    private Double comptimeWed;
    private Double comptimeThu;
    private Double comptimeFri;
    private Double comptimeSat;
    private String timeSheetNotes;
    private String timeSheetStatus;
    private String weeklyDates1;
    private String weeklyDates2;
    private String weeklyDates3;
    private String weeklyDates4;
    private String weeklyDates5;
    private String weeklyDates6;
    private String weeklyDates7;
    private int project1key;
    private int project2key;
    private int project3key;
    private int project4key;
    private int project5key;
    private int reportsTo;
    private Double totalBillHrs;
    private DataSourceDataProvider dataSourceDataProvider;
    private String empName;
    private String projectDetails;
    /* added by kiran*/
    private Map teamMembersList;
    private String tmmember;
    private TimesheetVTO timeSheetVTO;
    private int tempVar;
    private String timeSheetApprovedDate;
    private String comments;
    private String timesheetFlag;
    private int usr_id;
    private Date previousDate;
    private String status;
    private String primaryRole;
    private String vendorName;
    private int sessionOrgId;
    private String memberName;
    private String customerName;

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : doTimesheetSearch() method is used to
     *
     * *****************************************************************************
     */
    public String doTimesheetSearch() {
        System.out.println("********************UsrTimeSheetAction :: doTimesheetSearch Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setReportingPerson(DataSourceDataProvider.getInstance().getReportingPersonsEmail(userId));
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
                List TimesheetListDetails = ServiceLocator.getUserTimesheetService().getTimesheetListDetails(this);
                if (TimesheetListDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("timesheetsData", TimesheetListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("timesheetsData", null);
                    resultType = SUCCESS;
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }//END-Authorization Checking
        //Close Session Checking
        System.out.println("********************UsrTimeSheetAction :: doTimesheetSearch Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : doSearch() method is used to
     *
     * *****************************************************************************
     */
    public String doSearch() {
        System.out.println("********************UsrTimeSheetAction :: doSearch Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setReportingPerson(DataSourceDataProvider.getInstance().getReportingPersonsEmail(userId));
                List TimesheetListDetails = ServiceLocator.getUserTimesheetService().getTimesheetList(this);
                httpServletRequest.getSession(false).setAttribute("timesheetsData", TimesheetListDetails);
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }//END-Authorization Checking
        //Close Session Checking
        System.out.println("********************UsrTimeSheetAction :: doSearch Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : deleteTimeSheet() method is used to
     *
     * *****************************************************************************
     */
    public String deleteTimeSheet() {
        System.out.println("********************UsrTimeSheetAction :: deleteTimeSheet Method Start*********************");
        resultType = LOGIN;
        if ((httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null)) {
            setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
            try {
                int deletedRows = ServiceLocator.getUserTimesheetService().deleteTimeSheet(this);
                if (deletedRows > 1) {
                    resultType = SUCCESS;
                    resultMessage = "<font color=\"green\" size=\"1.5\">TimeSheet has been successfully Deleted!</font>";
                } else {
                    resultType = INPUT;
                    resultMessage = "<font color=\"red\" size=\"1.5\">Sorry! Please Try again!</font>";
                }
                List TimesheetListDetails = ServiceLocator.getUserTimesheetService().getAllTimeSheetList(this);
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                httpServletRequest.getSession(false).setAttribute("timesheetsData", TimesheetListDetails);
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
            //}//END-Authorization Checking
        }//Close Session Checking
        System.out.println("********************UsrTimeSheetAction :: deleteTimeSheet Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Mani Kanta Eeralla<meeralla@miraclesoft.com>
     *
     * ForUse : getAddTimeSheetAdd() method is used to
     *
     * *****************************************************************************
     */
    public String getAddTimeSheetAdd() {
        System.out.println("********************UsrTimeSheetAction :: getAddTimeSheetAdd Method Start*********************");
        resultType = LOGIN;
        /* for checking Timesheet exists */
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setEmpName(dataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUserSessionId()));
                if (getPreviousDate() != null) {
                    String stortingDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(getPreviousDate());
                    String splitDate[] = stortingDate.split("/");
                    int mon = Integer.parseInt(splitDate[0]);
                    int day = Integer.parseInt(splitDate[1]);
                    int year = Integer.parseInt(splitDate[2]);
                    /* for setting the date for the spliting filed */
                    Calendar previouseCalender = Calendar.getInstance();
                    previouseCalender.set(Calendar.YEAR, year);
                    previouseCalender.set(Calendar.MONTH, mon - 1); // becoz the index is starting 0
                    previouseCalender.set(Calendar.DAY_OF_MONTH, day);
                    List prevoiusWeekDaysList = ServiceLocator.getUserTimesheetService().getweekStartAndEndDays(previouseCalender);
                    TimesheetVTO timeSheetVTO = ServiceLocator.getUserTimesheetService().getWeekDaysBean(prevoiusWeekDaysList);
                    setTimeSheetVTO(timeSheetVTO);
                } else {
                    Calendar cal = Calendar.getInstance();
                    /**
                     * For generating the weekdays
                     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#getweekStartAndEndDays(cal)}
                     */
                    List currentWeekDaysList = ServiceLocator.getUserTimesheetService().getweekStartAndEndDays(cal);
                    TimesheetVTO timeSheetVTO = ServiceLocator.getUserTimesheetService().getWeekDaysBean(currentWeekDaysList);
                    setTimeSheetVTO(timeSheetVTO);
                }

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************UsrTimeSheetAction :: getAddTimeSheetAdd Method End*********************");
        return resultType;

    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : getTimeSheetBeforeAdd() method is used to
     *
     * *****************************************************************************
     */
    public String getTimeSheetBeforeAdd() {
        System.out.println("********************UsrTimeSheetAction :: getTimeSheetBeforeAdd Method Start*********************");
        resultMessage = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setEmpName(dataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUserSessionId()));
                int usrid = getUsr_id();
                if ("Team".equals(getTimesheetFlag()) || "Operations".equals(getTimesheetFlag())) {
                    projectDetails = dataSourceDataProvider.getInstance().getProjectMap(usrid);
                } else {
                    projectDetails = dataSourceDataProvider.getInstance().getProjectMap(getUserSessionId());
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(projectDetails);
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************UsrTimeSheetAction :: getTimeSheetBeforeAdd Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : addTimesheet() method is used to
     *
     * *****************************************************************************
     */
    public String addTimesheet() {
        System.out.println("********************UsrTimeSheetAction :: addTimesheet Method Start*********************");
        resultType = LOGIN;
        try {
            MailManager mailManager = new MailManager();
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int timesheetAdd = ServiceLocator.getUserTimesheetService().AddTimesheet(this);
                if (timesheetAdd > 0) {
                    if (getTempVar() == 1) {
                        String reportiingPersonsEmail = dataSourceDataProvider.getInstance().getReportingPersonsEmail(getUserSessionId());
                        String empName = DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUserSessionId());
                        mailManager.timesheetAddEmail(this, empName, reportiingPersonsEmail);
                    }
                    resultMessage = "<font color=\"green\" size=\"1.5\">The TimeSheet Successfully Added for WeekStartDate: " + getTimeSheetStartDate() + " And WeekEndDate:" + getTimeSheetEndDate() + "</font>";
                } else {
                    resultMessage = "<font color=\"red\" size=\"1.5\">Error occour Adding for WeekStartDate: " + getTimeSheetStartDate() + " And WeekEndDate:" + getTimeSheetEndDate() + "</font>";
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {

            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************UsrTimeSheetAction :: addTimesheet Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doTeamTimesheetSearch() method is used to
     *
     * *****************************************************************************
     */
    public String doTeamTimesheetSearch() {
        System.out.println("********************UsrTimeSheetAction :: doTeamTimesheetSearch Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                Map map = DataSourceDataProvider.getInstance().getMyTeamMembers(userid);
                setTeamMembersList(map);
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

                List teamTimesheetListDetails = ServiceLocator.getUserTimesheetService().getTeamTimeSheetListDefault(this);
                if (teamTimesheetListDetails.size() > 0) {

                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", teamTimesheetListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", null);
                    resultType = SUCCESS;
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }//END-Authorization Checking
        //Close Session Checking
        System.out.println("********************UsrTimeSheetAction :: doTeamTimesheetSearch Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Kiran Arogya<adoddi@miraclesoft.com>
     *
     * ForUse : doTeamTMSearch() method is used to
     *
     * *****************************************************************************
     */
    public String doTeamTMSearch() {
        System.out.println("********************UsrTimeSheetAction :: doTeamTMSearch Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                Map map = DataSourceDataProvider.getInstance().getMyTeamMembers(userid);
                setTeamMembersList(map);

                List teamTimesheetListDetails = ServiceLocator.getUserTimesheetService().getTeamTimeSheetList(this);
                if (teamTimesheetListDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", teamTimesheetListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", null);
                    resultType = SUCCESS;
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }//END-Authorization Checking
        //Close Session Checking
        System.out.println("********************UsrTimeSheetAction :: doTeamTMSearch Method Start*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : getUserTimeSheets() method is used to getUserTimeSheet on edit
     * page.
     *
     * *****************************************************************************
     */
    public String getUserTimeSheets() {
        System.out.println("********************UsrTimeSheetAction :: getUserTimeSheets Method Start*********************");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setTimeSheetVTO(ServiceLocator.getUserTimesheetService().getUserTimeSheet(this));
                int usrid = timeSheetVTO.getUsr_id();
                setEmpName(dataSourceDataProvider.getInstance().getFnameandLnamebyUserid(usrid));
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }
        }// Session validator if END
        System.out.println("********************UsrTimeSheetAction :: getUserTimeSheets Method End*********************");
        return resultMessage;

    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : editTimeSheets() used to Update the timesheet from edit page
     *
     *
     * *****************************************************************************
     */
    public String editTimeSheets() {
        System.out.println("********************UsrTimeSheetAction :: editTimeSheets Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                MailManager mailManager = new MailManager();
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int editTimeSheet = ServiceLocator.getUserTimesheetService().editTimeSheet(this);
                if (editTimeSheet > 0) {
                    if (getTempVar() == 2) {
                        String reportingPersonsEmail = dataSourceDataProvider.getInstance().getReportingPersonsEmail(getUserSessionId());
                        String empName = DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUserSessionId());
                        mailManager.timesheetAddEmail(this, empName, reportingPersonsEmail);
                    }

                    resultMessage = "<font color=\"green\" size=\"2.5\">The TimeSheet Updated Successfully for WeekStartDate: " + getTimeSheetStartDate() + " And WeekEndDate:" + getTimeSheetEndDate() + "</font>";
                } else {
                    resultMessage = "<font color=\"red\" size=\"2.5\">Error occured while updating timesheet for WeekStartDate: " + getTimeSheetStartDate() + " And WeekEndDate:" + getTimeSheetEndDate() + "</font>";
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultType = ERROR;
            }
        }
        System.out.println("********************UsrTimeSheetAction :: editTimeSheets Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * ForUse : newapprove() used to Update the approve/reject status of
     * timesheet.
     *
     * *****************************************************************************
     */
    public String newapprove() {
        System.out.println("********************UsrTimeSheetAction :: newapprove Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                MailManager mailManager = new MailManager();
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int isUpdated = ServiceLocator.getUserTimesheetService().approveRejectTimeSheet(this);
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                Map map = DataSourceDataProvider.getInstance().getMyTeamMembers(userid);
                setTeamMembersList(map);
                if (isUpdated > 0) {
                    resultMessage = "<font color=\"green\" size=\"2.5\">The TimeSheet Updated Successfully for WeekStartDate: " + getTimeSheetStartDate() + " And WeekEndDate:" + getTimeSheetEndDate() + "</font>";
                    String empName = DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUsr_id());
                    mailManager.timesheetApproveEmail(this, empName);
                } else {
                    resultMessage = "<font color=\"red\" size=\"2.5\">Error occured while updating timesheet for WeekStartDate: " + getTimeSheetStartDate() + " And WeekEndDate:" + getTimeSheetEndDate() + "</font>";
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultType = ERROR;
            }
        }
        System.out.println("********************UsrTimeSheetAction :: newapprove Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 07/08/2015
     *
     * Author : Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * ForUse : getTimeSheetCheck() method is used to check whether the
     * timesheet is exist for that given date or not.
     *
     * *****************************************************************************
     */
    public String getTimeSheetCheck() {
        System.out.println("********************UsrTimeSheetAction :: getTimeSheetCheck Method Start*********************");
        resultMessage = LOGIN;
        /* for checking Timesheet exists */
        String isTimeSheetExist = "";
        String isPreviousTimeSheetExist = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setEmpName(dataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getUserSessionId()));
                /*added by kiran*/
                if (getPreviousDate() != null) {
                    String stortingDate = new java.text.SimpleDateFormat("MM/dd/yyyy").format(getPreviousDate());
                    String splitDate[] = stortingDate.split("/");
                    int mon = Integer.parseInt(splitDate[0]);
                    int day = Integer.parseInt(splitDate[1]);
                    int year = Integer.parseInt(splitDate[2]);

                    /* for setting the date for the spliting filed */
                    Calendar previouseCalender = Calendar.getInstance();

                    previouseCalender.set(Calendar.YEAR, year);
                    previouseCalender.set(Calendar.MONTH, mon - 1); // becoz the index is starting 0
                    previouseCalender.set(Calendar.DAY_OF_MONTH, day);

                    List prevoiusWeekDaysList = ServiceLocator.getUserTimesheetService().getweekStartAndEndDays(previouseCalender);
                    isPreviousTimeSheetExist = ServiceLocator.getUserTimesheetService().checkTimeSheetExists(prevoiusWeekDaysList, userId);
                    httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    httpServletResponse.setHeader("Pragma", "no-cache");
                    httpServletResponse.setDateHeader("Expires", 0);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.getWriter().write(isPreviousTimeSheetExist);
                } else {
                    System.out.println("no week range");
                    Calendar cal = Calendar.getInstance();
                    /**
                     * For generating the weekdays
                     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#getweekStartAndEndDays(cal)}
                     */
                    List currentWeekDaysList = ServiceLocator.getUserTimesheetService().getweekStartAndEndDays(cal);
                    /**
                     * To check wethere the timesheet exists or not
                     * {@Link com.mss.mirage.employee.timesheets.TimeSheetService#checkTimeSheetExists(List,String)}
                     */
                    isTimeSheetExist = ServiceLocator.getUserTimesheetService().checkTimeSheetExists(currentWeekDaysList, userId);
                    httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    httpServletResponse.setHeader("Pragma", "no-cache");
                    httpServletResponse.setDateHeader("Expires", 0);
                    httpServletResponse.setContentType("text");
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.getWriter().write(isTimeSheetExist);
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            ex.printStackTrace();
        }
        System.out.println("********************UsrTimeSheetAction :: getTimeSheetCheck Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : Divya Gandreti <dgandreti@miraclesoft.com>
     *
     * ForUse : getAllTimeSheets() method is used to
     *
     * *****************************************************************************
     */
    public String getAllTimeSheets() {
        System.out.println("********************UsrTimeSheetAction :: getAllTimeSheets Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setPrimaryRole(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
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

                List teamTimesheetListDetails = ServiceLocator.getUserTimesheetService().getAllTimeSheets(this);
                if (teamTimesheetListDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", teamTimesheetListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", null);
                    resultType = SUCCESS;
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                ex.printStackTrace();
                resultType = ERROR;
            }
        }
        System.out.println("********************UsrTimeSheetAction :: getAllTimeSheets Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author : Divya Gandreti <dgandreti@miraclesoft.com>
     *
     * ForUse : getAllTimeSheetsSearch() method is used to
     *
     * *****************************************************************************
     */
    public String getAllTimeSheetsSearch() {
        System.out.println("********************UsrTimeSheetAction :: getAllTimeSheetsSearch Method Start*********************");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserId(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setPrimaryRole(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                List teamTimesheetListDetails = ServiceLocator.getUserTimesheetService().getAllTimeSheetsSearch(this);
                if (teamTimesheetListDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", teamTimesheetListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("teamTimesheetsData", null);
                    resultType = SUCCESS;
                }
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                ex.printStackTrace();
                resultType = ERROR;
            }
        }
        System.out.println("********************UsrTimeSheetAction :: getAllTimeSheetsSearch Method End*********************");
        return resultType;
    }

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

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
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

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTimesheetid() {
        return timesheetid;
    }

    public void setTimesheetid(int timesheetid) {
        this.timesheetid = timesheetid;
    }

    public String getTmstatus() {
        return tmstatus;
    }

    public void setTmstatus(String tmstatus) {
        this.tmstatus = tmstatus;
    }

    public String getReportingPerson() {
        return reportingPerson;
    }

    public void setReportingPerson(String reportingPerson) {
        this.reportingPerson = reportingPerson;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getTimeSheetStartDate() {
        return timeSheetStartDate;
    }

    public void setTimeSheetStartDate(String timeSheetStartDate) {
        this.timeSheetStartDate = timeSheetStartDate;
    }

    public String getTimeSheetEndDate() {
        return timeSheetEndDate;
    }

    public void setTimeSheetEndDate(String timeSheetEndDate) {
        this.timeSheetEndDate = timeSheetEndDate;
    }

    public String getTimeSheetSubmittedDate() {
        return timeSheetSubmittedDate;
    }

    public void setTimeSheetSubmittedDate(String timeSheetSubmittedDate) {
        this.timeSheetSubmittedDate = timeSheetSubmittedDate;
    }

    public Double getProjectNameSun1() {
        return projectNameSun1;
    }

    public void setProjectNameSun1(Double projectNameSun1) {
        this.projectNameSun1 = projectNameSun1;
    }

    public Double getProjectNameMon1() {
        return projectNameMon1;
    }

    public void setProjectNameMon1(Double projectNameMon1) {
        this.projectNameMon1 = projectNameMon1;
    }

    public Double getProjectNameTue1() {
        return projectNameTue1;
    }

    public void setProjectNameTue1(Double projectNameTue1) {
        this.projectNameTue1 = projectNameTue1;
    }

    public Double getProjectNameWed1() {
        return projectNameWed1;
    }

    public void setProjectNameWed1(Double projectNameWed1) {
        this.projectNameWed1 = projectNameWed1;
    }

    public Double getProjectNameThu1() {
        return projectNameThu1;
    }

    public void setProjectNameThu1(Double projectNameThu1) {
        this.projectNameThu1 = projectNameThu1;
    }

    public Double getProjectNameFri1() {
        return projectNameFri1;
    }

    public void setProjectNameFri1(Double projectNameFri1) {
        this.projectNameFri1 = projectNameFri1;
    }

    public Double getProjectNameSat1() {
        return projectNameSat1;
    }

    public void setProjectNameSat1(Double projectNameSat1) {
        this.projectNameSat1 = projectNameSat1;
    }

    public Double getProjectNameSun2() {
        return projectNameSun2;
    }

    public void setProjectNameSun2(Double projectNameSun2) {
        this.projectNameSun2 = projectNameSun2;
    }

    public Double getProjectNameMon2() {
        return projectNameMon2;
    }

    public void setProjectNameMon2(Double projectNameMon2) {
        this.projectNameMon2 = projectNameMon2;
    }

    public Double getProjectNameTue2() {
        return projectNameTue2;
    }

    public void setProjectNameTue2(Double projectNameTue2) {
        this.projectNameTue2 = projectNameTue2;
    }

    public Double getProjectNameWed2() {
        return projectNameWed2;
    }

    public void setProjectNameWed2(Double projectNameWed2) {
        this.projectNameWed2 = projectNameWed2;
    }

    public Double getProjectNameThu2() {
        return projectNameThu2;
    }

    public void setProjectNameThu2(Double projectNameThu2) {
        this.projectNameThu2 = projectNameThu2;
    }

    public Double getProjectNameFri2() {
        return projectNameFri2;
    }

    public void setProjectNameFri2(Double projectNameFri2) {
        this.projectNameFri2 = projectNameFri2;
    }

    public Double getProjectNameSat2() {
        return projectNameSat2;
    }

    public void setProjectNameSat2(Double projectNameSat2) {
        this.projectNameSat2 = projectNameSat2;
    }

    public Double getProjectNameSun3() {
        return projectNameSun3;
    }

    public void setProjectNameSun3(Double projectNameSun3) {
        this.projectNameSun3 = projectNameSun3;
    }

    public Double getProjectNameMon3() {
        return projectNameMon3;
    }

    public void setProjectNameMon3(Double projectNameMon3) {
        this.projectNameMon3 = projectNameMon3;
    }

    public Double getProjectNameTue3() {
        return projectNameTue3;
    }

    public void setProjectNameTue3(Double projectNameTue3) {
        this.projectNameTue3 = projectNameTue3;
    }

    public Double getProjectNameWed3() {
        return projectNameWed3;
    }

    public void setProjectNameWed3(Double projectNameWed3) {
        this.projectNameWed3 = projectNameWed3;
    }

    public Double getProjectNameThu3() {
        return projectNameThu3;
    }

    public void setProjectNameThu3(Double projectNameThu3) {
        this.projectNameThu3 = projectNameThu3;
    }

    public Double getProjectNameFri3() {
        return projectNameFri3;
    }

    public void setProjectNameFri3(Double projectNameFri3) {
        this.projectNameFri3 = projectNameFri3;
    }

    public Double getProjectNameSat3() {
        return projectNameSat3;
    }

    public void setProjectNameSat3(Double projectNameSat3) {
        this.projectNameSat3 = projectNameSat3;
    }

    public Double getProjectNameSun4() {
        return projectNameSun4;
    }

    public void setProjectNameSun4(Double projectNameSun4) {
        this.projectNameSun4 = projectNameSun4;
    }

    public Double getProjectNameMon4() {
        return projectNameMon4;
    }

    public void setProjectNameMon4(Double projectNameMon4) {
        this.projectNameMon4 = projectNameMon4;
    }

    public Double getProjectNameTue4() {
        return projectNameTue4;
    }

    public void setProjectNameTue4(Double projectNameTue4) {
        this.projectNameTue4 = projectNameTue4;
    }

    public Double getProjectNameWed4() {
        return projectNameWed4;
    }

    public void setProjectNameWed4(Double projectNameWed4) {
        this.projectNameWed4 = projectNameWed4;
    }

    public Double getProjectNameThu4() {
        return projectNameThu4;
    }

    public void setProjectNameThu4(Double projectNameThu4) {
        this.projectNameThu4 = projectNameThu4;
    }

    public Double getProjectNameFri4() {
        return projectNameFri4;
    }

    public void setProjectNameFri4(Double projectNameFri4) {
        this.projectNameFri4 = projectNameFri4;
    }

    public Double getProjectNameSat4() {
        return projectNameSat4;
    }

    public void setProjectNameSat4(Double projectNameSat4) {
        this.projectNameSat4 = projectNameSat4;
    }

    public Double getProjectNameSun5() {
        return projectNameSun5;
    }

    public void setProjectNameSun5(Double projectNameSun5) {
        this.projectNameSun5 = projectNameSun5;
    }

    public Double getProjectNameMon5() {
        return projectNameMon5;
    }

    public void setProjectNameMon5(Double projectNameMon5) {
        this.projectNameMon5 = projectNameMon5;
    }

    public Double getProjectNameTue5() {
        return projectNameTue5;
    }

    public void setProjectNameTue5(Double projectNameTue5) {
        this.projectNameTue5 = projectNameTue5;
    }

    public Double getProjectNameWed5() {
        return projectNameWed5;
    }

    public void setProjectNameWed5(Double projectNameWed5) {
        this.projectNameWed5 = projectNameWed5;
    }

    public Double getProjectNameThu5() {
        return projectNameThu5;
    }

    public void setProjectNameThu5(Double projectNameThu5) {
        this.projectNameThu5 = projectNameThu5;
    }

    public Double getProjectNameFri5() {
        return projectNameFri5;
    }

    public void setProjectNameFri5(Double projectNameFri5) {
        this.projectNameFri5 = projectNameFri5;
    }

    public Double getProjectNameSat5() {
        return projectNameSat5;
    }

    public void setProjectNameSat5(Double projectNameSat5) {
        this.projectNameSat5 = projectNameSat5;
    }

    public Double getInternalSun() {
        return internalSun;
    }

    public void setInternalSun(Double internalSun) {
        this.internalSun = internalSun;
    }

    public Double getInternalMon() {
        return internalMon;
    }

    public void setInternalMon(Double internalMon) {
        this.internalMon = internalMon;
    }

    public Double getInternalTue() {
        return internalTue;
    }

    public void setInternalTue(Double internalTue) {
        this.internalTue = internalTue;
    }

    public Double getInternalWed() {
        return internalWed;
    }

    public void setInternalWed(Double internalWed) {
        this.internalWed = internalWed;
    }

    public Double getInternalThu() {
        return internalThu;
    }

    public void setInternalThu(Double internalThu) {
        this.internalThu = internalThu;
    }

    public Double getInternalFri() {
        return internalFri;
    }

    public void setInternalFri(Double internalFri) {
        this.internalFri = internalFri;
    }

    public Double getInternalSat() {
        return internalSat;
    }

    public void setInternalSat(Double internalSat) {
        this.internalSat = internalSat;
    }

    public Double getVacationSun() {
        return vacationSun;
    }

    public void setVacationSun(Double vacationSun) {
        this.vacationSun = vacationSun;
    }

    public Double getVacationMon() {
        return vacationMon;
    }

    public void setVacationMon(Double vacationMon) {
        this.vacationMon = vacationMon;
    }

    public Double getVacationTue() {
        return vacationTue;
    }

    public void setVacationTue(Double vacationTue) {
        this.vacationTue = vacationTue;
    }

    public Double getVacationWed() {
        return vacationWed;
    }

    public void setVacationWed(Double vacationWed) {
        this.vacationWed = vacationWed;
    }

    public Double getVacationThu() {
        return vacationThu;
    }

    public void setVacationThu(Double vacationThu) {
        this.vacationThu = vacationThu;
    }

    public Double getVacationFri() {
        return vacationFri;
    }

    public void setVacationFri(Double vacationFri) {
        this.vacationFri = vacationFri;
    }

    public Double getVacationSat() {
        return vacationSat;
    }

    public void setVacationSat(Double vacationSat) {
        this.vacationSat = vacationSat;
    }

    public Double getHolidaySun() {
        return holidaySun;
    }

    public void setHolidaySun(Double holidaySun) {
        this.holidaySun = holidaySun;
    }

    public Double getHolidayMon() {
        return holidayMon;
    }

    public void setHolidayMon(Double holidayMon) {
        this.holidayMon = holidayMon;
    }

    public Double getHolidayTue() {
        return holidayTue;
    }

    public void setHolidayTue(Double holidayTue) {
        this.holidayTue = holidayTue;
    }

    public Double getHolidayWed() {
        return holidayWed;
    }

    public void setHolidayWed(Double holidayWed) {
        this.holidayWed = holidayWed;
    }

    public Double getHolidayThu() {
        return holidayThu;
    }

    public void setHolidayThu(Double holidayThu) {
        this.holidayThu = holidayThu;
    }

    public Double getHolidayFri() {
        return holidayFri;
    }

    public void setHolidayFri(Double holidayFri) {
        this.holidayFri = holidayFri;
    }

    public Double getHolidaySat() {
        return holidaySat;
    }

    public void setHolidaySat(Double holidaySat) {
        this.holidaySat = holidaySat;
    }

    public Double getComptimeSun() {
        return comptimeSun;
    }

    public void setComptimeSun(Double comptimeSun) {
        this.comptimeSun = comptimeSun;
    }

    public Double getComptimeMon() {
        return comptimeMon;
    }

    public void setComptimeMon(Double comptimeMon) {
        this.comptimeMon = comptimeMon;
    }

    public Double getComptimeTue() {
        return comptimeTue;
    }

    public void setComptimeTue(Double comptimeTue) {
        this.comptimeTue = comptimeTue;
    }

    public Double getComptimeWed() {
        return comptimeWed;
    }

    public void setComptimeWed(Double comptimeWed) {
        this.comptimeWed = comptimeWed;
    }

    public Double getComptimeThu() {
        return comptimeThu;
    }

    public void setComptimeThu(Double comptimeThu) {
        this.comptimeThu = comptimeThu;
    }

    public Double getComptimeFri() {
        return comptimeFri;
    }

    public void setComptimeFri(Double comptimeFri) {
        this.comptimeFri = comptimeFri;
    }

    public Double getComptimeSat() {
        return comptimeSat;
    }

    public void setComptimeSat(Double comptimeSat) {
        this.comptimeSat = comptimeSat;
    }

    public String getTimeSheetNotes() {
        return timeSheetNotes;
    }

    public void setTimeSheetNotes(String timeSheetNotes) {
        this.timeSheetNotes = timeSheetNotes;
    }

    public String getTimeSheetStatus() {
        return timeSheetStatus;
    }

    public void setTimeSheetStatus(String timeSheetStatus) {
        this.timeSheetStatus = timeSheetStatus;
    }

    public String getWeeklyDates1() {
        return weeklyDates1;
    }

    public void setWeeklyDates1(String weeklyDates1) {
        this.weeklyDates1 = weeklyDates1;
    }

    public String getWeeklyDates2() {
        return weeklyDates2;
    }

    public void setWeeklyDates2(String weeklyDates2) {
        this.weeklyDates2 = weeklyDates2;
    }

    public String getWeeklyDates3() {
        return weeklyDates3;
    }

    public void setWeeklyDates3(String weeklyDates3) {
        this.weeklyDates3 = weeklyDates3;
    }

    public String getWeeklyDates4() {
        return weeklyDates4;
    }

    public void setWeeklyDates4(String weeklyDates4) {
        this.weeklyDates4 = weeklyDates4;
    }

    public String getWeeklyDates5() {
        return weeklyDates5;
    }

    public void setWeeklyDates5(String weeklyDates5) {
        this.weeklyDates5 = weeklyDates5;
    }

    public String getWeeklyDates6() {
        return weeklyDates6;
    }

    public void setWeeklyDates6(String weeklyDates6) {
        this.weeklyDates6 = weeklyDates6;
    }

    public String getWeeklyDates7() {
        return weeklyDates7;
    }

    public void setWeeklyDates7(String weeklyDates7) {
        this.weeklyDates7 = weeklyDates7;
    }

    public int getProject1key() {
        return project1key;
    }

    public void setProject1key(int project1key) {
        this.project1key = project1key;
    }

    public int getProject2key() {
        return project2key;
    }

    public void setProject2key(int project2key) {
        this.project2key = project2key;
    }

    public int getProject3key() {
        return project3key;
    }

    public void setProject3key(int project3key) {
        this.project3key = project3key;
    }

    public int getProject4key() {
        return project4key;
    }

    public void setProject4key(int project4key) {
        this.project4key = project4key;
    }

    public int getProject5key() {
        return project5key;
    }

    public void setProject5key(int project5key) {
        this.project5key = project5key;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Double getTotalBillHrs() {
        return totalBillHrs;
    }

    public void setTotalBillHrs(Double totalBillHrs) {
        this.totalBillHrs = totalBillHrs;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Map getTeamMembersList() {
        return teamMembersList;
    }

    public void setTeamMembersList(Map teamMembersList) {
        this.teamMembersList = teamMembersList;
    }

    public String getTmmember() {
        return tmmember;
    }

    public void setTmmember(String tmmember) {
        this.tmmember = tmmember;
    }

    public TimesheetVTO getTimeSheetVTO() {
        return timeSheetVTO;
    }

    public void setTimeSheetVTO(TimesheetVTO timeSheetVTO) {
        this.timeSheetVTO = timeSheetVTO;
    }

    public int getTempVar() {
        return tempVar;
    }

    public void setTempVar(int tempVar) {
        this.tempVar = tempVar;
    }

    public String getTimeSheetApprovedDate() {
        return timeSheetApprovedDate;
    }

    public void setTimeSheetApprovedDate(String timeSheetApprovedDate) {
        this.timeSheetApprovedDate = timeSheetApprovedDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTimesheetFlag() {
        return timesheetFlag;
    }

    public void setTimesheetFlag(String timesheetFlag) {
        this.timesheetFlag = timesheetFlag;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public Date getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(Date previousDate) {
        this.previousDate = previousDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
