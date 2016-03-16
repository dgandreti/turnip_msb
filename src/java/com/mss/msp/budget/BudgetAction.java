/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.budget;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class BudgetAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int userSessionId;
    private int sessionOrgId;
    private int year;
    private List projectBudgetList;
    private Map projectsMap;
    private String typeOfUser;
    private String roleValue;
    private String resultMessage;
    //for budget search
    private String project;
    private String quarterId;
    private String status;
    private String estimateBudget;
    private String comments;
    private String flag;
    private String addEditFlag;
    private int budgetId;
    private int projectId;
    private double costCenterBudgetAmt;
    private String costCenterCode;
    private String projectType;
    private String approveComments;

    /**
     * *************************************
     *
     * @getProjectBudgetDetails() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:16/07/2015
     *
     **************************************
     */
    public String getProjectBudgetDetails() throws ServiceLocatorException {
        String resulttype = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("ENTERED IN TO THE ACTION FOR getProjectBudgetDetails******************************************************");
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            setRoleValue(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLEVALUE).toString());

            //setRoleValue(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
            setProjectsMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getProjectList(getRoleValue(), getUserSessionId(), getSessionOrgId()));
            System.out.println(">>>>>>>>>>>>>>>>>in budget action >>>" + getRoleValue());


            projectBudgetList = (ServiceLocator.getBudgetService().getProjectBudgetDetails(this));
            System.out.println(">>>>>>>>ACTION>>>>>>" + getProjectBudgetList().toString());
            setYear(Calendar.getInstance().get(Calendar.YEAR));
            resulttype = SUCCESS;
        }
        return resulttype;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetSearchResults() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:16/07/2015
     *
     **************************************
     */
    public String getProjectBudgetSearchResults() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setRoleValue(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLEVALUE).toString());
                String csrReq = ServiceLocator.getBudgetService().getProjectBudgetSearchResults(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetSearchResults() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:16/07/2015
     *
     **************************************
     */
    public String saveProjectBudgetDetails() {
        resultMessage = LOGIN;
        String csrReq = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                //setRoleValue(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLEVALUE).toString());
                System.out.println(">>>>>>>>>>>>>>>FLAG IN ACTION>>>>>>>>>>>" + getFlag());

                if (getFlag().equalsIgnoreCase("A") || getFlag().equalsIgnoreCase("R")) {
                    if (getFlag().equalsIgnoreCase("A")) {
                        setFlag("Approved");
                    } else {
                        setFlag("Rejected");
                    }
                    csrReq = ServiceLocator.getBudgetService().setDirectorResultForBudget( this);
                } else {
                    csrReq = ServiceLocator.getBudgetService().saveProjectBudgetDetails(this);
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetDetailsToViewOnOverlay() update status in requirement
     * table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:16/07/2015
     *
     **************************************
     */
    public String getProjectBudgetDetailsToViewOnOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String csrReq = ServiceLocator.getBudgetService().getProjectBudgetDetailsToViewOnOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetDetailsToViewOnOverlay() update status in requirement
     * table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:16/07/2015
     *
     **************************************
     */
    public String doBudgetRecordDelete() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String csrReq = ServiceLocator.getBudgetService().doBudgetRecordDelete( this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }
    /**
     * *************************************
     *
     *  
     *
     * @Author:Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:October 14,2015
     *
     **************************************
     */
    public String getCostCenterNameByProjectId() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String costCenterName = ServiceLocator.getBudgetService().getCostCentertDetailsByProjectId(this);
                System.out.println("-------------cost yar----------"+getYear());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(costCenterName);
            } catch (Exception ex) {
                resultMessage = ERROR;
            }
        }
        return null;
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

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List getProjectBudgetList() {
        return projectBudgetList;
    }

    public void setProjectBudgetList(List projectBudgetList) {
        this.projectBudgetList = projectBudgetList;
    }

    public Map getProjectsMap() {
        return projectsMap;
    }

    public void setProjectsMap(Map projectsMap) {
        this.projectsMap = projectsMap;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(String quarterId) {
        this.quarterId = quarterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimateBudget() {
        return estimateBudget;
    }

    public void setEstimateBudget(String estimateBudget) {
        this.estimateBudget = estimateBudget;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAddEditFlag() {
        return addEditFlag;
    }

    public void setAddEditFlag(String addEditFlag) {
        this.addEditFlag = addEditFlag;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public double getCostCenterBudgetAmt() {
        return costCenterBudgetAmt;
    }

    public void setCostCenterBudgetAmt(double costCenterBudgetAmt) {
        this.costCenterBudgetAmt = costCenterBudgetAmt;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getApproveComments() {
        return approveComments;
    }

    public void setApproveComments(String approveComments) {
        this.approveComments = approveComments;
    }
}
