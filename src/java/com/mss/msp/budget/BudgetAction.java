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
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class BudgetAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static Logger log = Logger.getLogger(BudgetAction.class);
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
     * @getProjectBudgetDetails() is for getting Project Budget Details
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
            log.info("************Entered to The BudgetAction ACTION ::: getProjectBudgetDetails***********");
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            setRoleValue(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLEVALUE).toString());
            setProjectsMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getProjectList(getRoleValue(), getUserSessionId(), getSessionOrgId()));
            projectBudgetList = (ServiceLocator.getBudgetService().getProjectBudgetDetails(this));
            setYear(Calendar.getInstance().get(Calendar.YEAR));
            resulttype = SUCCESS;
            log.info("************End of BudgetAction ACTION ::: getProjectBudgetDetails***********");
        }
        return resulttype;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetSearchResults() is for getting Project Budget Search
     * Results
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
                log.info("************Entered to The BudgetAction ACTION ::: getProjectBudgetDetails***********");
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
                log.info("************End of BudgetAction ACTION ::: getProjectBudgetDetails***********");
            } catch (Exception ex) {
                ex.printStackTrace();
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetAction:: getProjectBudgetSearchResults()" + ex.toString());
                }
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetSearchResults() is for save Project Budget Details
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
                log.info("************Entered to The BudgetAction ACTION ::: saveProjectBudgetDetails***********");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                if (getFlag().equalsIgnoreCase("A") || getFlag().equalsIgnoreCase("R")) {
                    if (getFlag().equalsIgnoreCase("A")) {
                        setFlag("Approved");
                    } else {
                        setFlag("Rejected");
                    }
                    csrReq = ServiceLocator.getBudgetService().setDirectorResultForBudget(this);
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
                log.info("************End of BudgetAction ACTION ::: saveProjectBudgetDetails***********");
            } catch (Exception ex) {
                ex.printStackTrace();
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetAction:: saveProjectBudgetDetails()" + ex.toString());
                }
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetDetailsToViewOnOverlay() is for get Project Budget
     * Details To View On Overlay
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
                log.info("************Entered into BudgetAction ACTION ::: getProjectBudgetDetailsToViewOnOverlay***********");
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
                log.info("************End of BudgetAction ACTION ::: getProjectBudgetDetailsToViewOnOverlay***********");
            } catch (Exception ex) {
                ex.printStackTrace();
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetAction:: getProjectBudgetDetailsToViewOnOverlay()" + ex.toString());
                }
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @doBudgetRecordDelete() is for Delete Budget Record
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
                log.info("************Entered into BudgetAction ACTION ::: doBudgetRecordDelete***********");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String csrReq = ServiceLocator.getBudgetService().doBudgetRecordDelete(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
                log.info("************End of BudgetAction ACTION ::: doBudgetRecordDelete***********");
            } catch (Exception ex) {
                ex.printStackTrace();
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetAction:: doBudgetRecordDelete()" + ex.toString());
                }
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getCostCenterNameByProjectId() is for getting CostCenter Name By ProjectId
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
                log.info("************Entered into BudgetAction ACTION ::: getCostCenterNameByProjectId***********");
                String costCenterName = ServiceLocator.getBudgetService().getCostCentertDetailsByProjectId(this);
                System.out.println("-------------cost yar----------" + getYear());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(costCenterName);
                log.info("************End of BudgetAction ACTION ::: getCostCenterNameByProjectId***********");
            } catch (Exception ex) {
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetAction:: getCostCenterNameByProjectId()" + ex.toString());
                }
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
