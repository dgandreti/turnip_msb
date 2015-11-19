 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.leaves;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NagireddySeerapu
 */
public class UserLeavesAction extends ActionSupport implements ServletRequestAware {

    private String resultType;
    private int userSessionId;
    private int leave_id;
    private String leave_startdate;
    private String leave_enddate;
    private String leave_status;
    private String leave_type;
    private String leave_reason;
    private String reports_to;
    private String modified_date;
    private String reportingPerson;
    // 
    private String leaveEditFrmDate;
    private String leaveEditEndDate;
    private String reportsTo;
    private String leaveType;
    private String alertMessage;
    private EmpLeaves empLeaves;
    private String leaveflag;
    private Map teamMembersList;
    // private List teamLeavesListDetails;
    private DataSourceDataProvider dataSourceDataProvider;
    private String teamMember;
    private String status;
    private String leavestatus;
    private int user;
    private int year;
    private int month;
    private int countryId;
    private int departmentId;
    private int userId;
    private Map departments;
    private Map countryNames;

    public UserLeavesAction() {
    }
    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;

    /*
     * Method: Fetches the user leaves when he click on Left menu.
     */
    public String defaultMyLeavesList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            //System.out.println("hello i am in action..");
            try {
                //System.out.println("in if leave sarech");
                List leavesListDetails = ServiceLocator.getUserLeavesService().getDefaultMyEmpLeavesListDetails(httpServletRequest, this);
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
              //  setReportingPerson(DataSourceDataProvider.getInstance().getReportingPersonByUserId(userId));
                //please change the get reporting person methos for modifications.
                //System.out.println("reporting person---->" + getReportingPerson());
                //System.out.println("leave list ------ " + leavesListDetails.size());
                if (leavesListDetails.size() > 0) {

                    httpServletRequest.getSession(false).setAttribute("leavesData", leavesListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("leavesData", null);
                    resultType = SUCCESS;
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessagetUserLeavesServiceges(ex);
                System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }// Session validator if END
        return resultMessage;
    }

    /**
     * Method : Fetches the leaves list.
     *
     */
    public String MyLeavesList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            //System.out.println("hello i am in action..");
            try {
                //System.out.println("in if leave sarech");
                List leavesListDetails = ServiceLocator.getUserLeavesService().getEmpLeavesListDetails(httpServletRequest, this);
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
              //  setReportingPerson(DataSourceDataProvider.getInstance().getReportingPersonByUserId(userId));
                //please change the getreportspersogs By usr id methos
                //System.out.println("reporting person---->" + getReportingPerson());
                //System.out.println("leave list ------ " + leavesListDetails.size());
                if (leavesListDetails.size() > 0) {

                    httpServletRequest.getSession(false).setAttribute("leavesData", leavesListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("leavesData", null);
                    resultType = SUCCESS;
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessagetUserLeavesServiceges(ex);
                System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }// Session validator if END
        return resultMessage;
    }

//      public String updatemyLeaveList() {
//        resultType = LOGIN;
//        try {
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//               // System.err.println("update profile action");
//
//                ServiceLocator.getUserLeavesService().updateMyLeaveList(this, httpServletRequest, userSessionId);
//                //System.out.println("hear updation is completed....");
//                addActionMessage("Profile Updated successfully");
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return resultType;
//    }
//    
//    
    /**
     * ****************************************************************************
     * Date : April 20 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * editLeaves() method is used to call getEmployeeLeaves method which is
     * used to retrieve the existing leaves by using leave id.
     * *****************************************************************************
     */
    public String editLeaves() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            try {
                setEmpLeaves(ServiceLocator.getUserLeavesService().getEmployeeLeaves(httpServletRequest, this.getUserSessionId(), leave_id));
                resultMessage = SUCCESS;


            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }

        }

        return resultMessage;
    }//end editLeaves()

    /**
     * ****************************************************************************
     * Date : April 20 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * updateLeaves() method can be used to call UpdatedEmpLeaves method which
     * updates the existing leaves by using leave id.
     * *****************************************************************************
     */
    public String updateLeaves() throws Exception {
        resultMessage = LOGIN;
        int isUpdated = 0;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());

            try {
                isUpdated = ServiceLocator.getUserLeavesService().UpdatedEmpLeaves(httpServletRequest, this, userid);
                if (this.getLeaveflag().equalsIgnoreCase("teamLeaveFlag")) {
                    resultMessage = INPUT;
                } else if (this.getLeaveflag().equalsIgnoreCase("myLeaveFlag")) {
                    resultMessage = SUCCESS;
                }

            } catch (Exception ex) {
                System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }  // end updateLeaves()

    /**
     * ****************************************************************************
     * Date : April 20 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * getTeamLeaveList() method can be used to call getTeamLeavesListDetails
     * method which retrieves team leaves the existing leaves.
     * *****************************************************************************
     */
    public String getTeamLeaveList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));

            try {
                System.out.println("in if leave sarech");
                List teamLeavesListDetails = ServiceLocator.getUserLeavesService().getTeamLeavesListDetails(httpServletRequest, this);
                Iterator i = teamLeavesListDetails.iterator();
                teamMembersList = (java.util.Map) i.next();
                if (teamLeavesListDetails.size() > 0) {

                    httpServletRequest.getSession(false).setAttribute("teamleavesData", teamLeavesListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("teamleavesData", null);
                    resultType = SUCCESS;
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }
        return resultMessage;
    } //end of getTeamLeaveList method.

    /**
     * ****************************************************************************
     * Date : April 21 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * getTeamMemberSearchDetails() method can be used to call
     * getTeamMemberLeavesDetails method which retrieves team member leaves the
     * existing leaves.
     * *****************************************************************************
     */
    public String getTeamMemberSearchDetails() {
        resultMessage = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(getUserSessionId()));
                List teamLeavesListDetails = ServiceLocator.getUserLeavesService().getTeamMemberLeavesDetails(httpServletRequest, this);

                if (teamLeavesListDetails.size() > 0) {

                    httpServletRequest.getSession(false).setAttribute("teamleavesData", teamLeavesListDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("teamleavesData", null);
                    resultType = SUCCESS;
                }
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            System.out.println("I am in error" + ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


            resultMessage = ERROR;
        }
        return resultMessage;
    }

    /**
     * ***************************************************************************************************
     * Date : April 27 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * getLeavesDashboardList() method can be used to display the leaves
     * Dashboard search page with department names and country names.
     * ****************************************************************************************************
     */
    public String getLeavesDashboardList() {
        resultMessage = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //setDepartments(dataSourceDataProvider.getInstance().getDepartmentNameByOrgId());
                setCountryNames(dataSourceDataProvider.getInstance().getCountryNames());
//            List leavesListDetails=(List)this.getCountryNames();
//            Iterator i =leavesListDetails.iterator();
//            countryNames = (java.util.Map) i.next();
                //setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            System.out.println("I am in error" + ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultMessage = ERROR;
        }
        return resultMessage;
    }

    /**
     * ***************************************************************************************************
     * Date : April 28 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * getLeavesDashboardSearchList() method can be used to call
     * getLeavesDashboardSearch() method which is display the leaves Dashboard
     * search page with search results.
     *
     * ****************************************************************************************************
     */
    public String getLeavesDashboardSearchList() {
        resultMessage = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //setDepartments(dataSourceDataProvider.getInstance().getDepartmentNameByOrgId());
                setCountryNames(dataSourceDataProvider.getInstance().getCountryNames());
                List LeavesDashboardDetails = ServiceLocator.getUserLeavesService().getLeavesDashboardSearchList(httpServletRequest, this);

                if (LeavesDashboardDetails.size() > 0) {

                    httpServletRequest.getSession(false).setAttribute("leavesDashboardData", LeavesDashboardDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("leavesDashboardData", null);
                    resultType = SUCCESS;
                }
                //setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            System.out.println("I am in error" + ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultMessage = ERROR;
        }
        return resultMessage;
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

    public int getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public String getLeave_startdate() {
        return leave_startdate;
    }

    public void setLeave_startdate(String leave_startdate) {
        this.leave_startdate = leave_startdate;
    }

    public String getLeave_enddate() {
        return leave_enddate;
    }

    public void setLeave_enddate(String leave_enddate) {
        this.leave_enddate = leave_enddate;
    }

    public String getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(String leave_status) {
        this.leave_status = leave_status;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public String getReports_to() {
        return reports_to;
    }

    public void setReports_to(String reports_to) {
        this.reports_to = reports_to;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getReportingPerson() {
        return reportingPerson;
    }

    public void setReportingPerson(String reportingPerson) {
        this.reportingPerson = reportingPerson;
    }

    public String getLeaveEditFrmDate() {
        return leaveEditFrmDate;
    }

    public void setLeaveEditFrmDate(String leaveEditFrmDate) {
        this.leaveEditFrmDate = leaveEditFrmDate;
    }

    public String getLeaveEditEndDate() {
        return leaveEditEndDate;
    }

    public void setLeaveEditEndDate(String leaveEditEndDate) {
        this.leaveEditEndDate = leaveEditEndDate;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public EmpLeaves getEmpLeaves() {
        return empLeaves;
    }

    public void setEmpLeaves(EmpLeaves empLeaves) {
        this.empLeaves = empLeaves;
    }

    public Map getTeamMembersList() {
        return teamMembersList;
    }

    public void setTeamMembersList(Map teamMembersList) {
        this.teamMembersList = teamMembersList;
    }

    public String getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(String teamMember) {
        this.teamMember = teamMember;
    }

    public String getLeaveflag() {
        return leaveflag;
    }

    public void setLeaveflag(String leaveflag) {
        this.leaveflag = leaveflag;
        System.out.println("in setter " + leaveflag);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeavestatus() {
        return leavestatus;
    }

    public void setLeavestatus(String leavestatus) {
        this.leavestatus = leavestatus;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map getDepartments() {
        return departments;
    }

    public void setDepartments(Map departments) {
        this.departments = departments;
    }

    public Map getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(Map countryNames) {
        this.countryNames = countryNames;
    }
}
