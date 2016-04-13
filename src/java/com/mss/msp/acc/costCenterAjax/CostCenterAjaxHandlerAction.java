/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.costCenterAjax;

import com.mss.msp.acc.costCenter.*;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mss.msp.util.ServiceLocatorException;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.struts2.interceptor.ServletRequestAware;
import java.util.List;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class CostCenterAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    // move this code to last line of this class and rebuild the code and tes the flow..
    private int sessionOrgId;
    private List costCenterSearchList;
    private String ccCode;
    private String ccName;
    private String status;
    private int sessionUserId;
    private String ccFlag;
    private int ccId;
    private String startDate;
    private String endDate;
    private double budgetAmt;
    private int id;
    private String year;
    private int orgId;
    private int dashBoardYear;
    private String typeOfUser;
    private String costCenters;
    private String quarter;
    private String budgetStatus;
    private String approveComments;
    private String rejectionComments;

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse :getCostCenterDashboardList() method is used to
     *
     * *****************************************************************************
     */
    public String getCostCenterDashboardList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: getCostCenterDashboardList Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                resultString = ServiceLocator.getCostCenterAjaxHandlerService().getCostCenterDashboardDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: getCostCenterDashboardList Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : getCostCentersDashboardForOrg() method is used to
     *
     * *****************************************************************************
     */
    public String getCostCentersDashboardForOrg() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: getCostCentersDashboardForOrg Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                resultString = ServiceLocator.getCostCenterAjaxHandlerService().getCostCentersDashboardForOrg(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: getCostCentersDashboardForOrg Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : October 14, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getProjectNamesInCostCenter() method is used to
     *
     * *****************************************************************************
     */
    public String getProjectNamesInCostCenter() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: getCostCentersDashboardForOrg Method Start*********************");
            try {
                String resultString = null;
                resultString = ServiceLocator.getCostCenterAjaxHandlerService().getProjectNamesInCostCenter(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: getCostCentersDashboardForOrg Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : costCenterInfoSearchList() method is used to
     *
     * *****************************************************************************
     */
    public String costCenterInfoSearchList() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: costCenterInfoSearchList Method Start*********************");
            try {
                String resultString = null;
                resultString = ServiceLocator.getCostCenterAjaxHandlerService().costCenterInfoSearchList(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: costCenterInfoSearchList Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : addCostCenter() method is used to
     *
     * *****************************************************************************
     */
    public String addCostCenter() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: addCostCenter Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                if ("edit".equalsIgnoreCase(getCcFlag())) {
                    resultString = ServiceLocator.getCostCenterAjaxHandlerService().editCostCenter(this);
                } else {
                    resultString = ServiceLocator.getCostCenterAjaxHandlerService().addCostCenter(this);
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: addCostCenter Method Start*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : addCostCenterBudget() method is used to
     *
     * *****************************************************************************
     */
    public String addCostCenterBudget() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: addCostCenterBudget Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                resultString = ServiceLocator.getCostCenterAjaxHandlerService().addCostCenterBudget(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: addCostCenterBudget Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getCostCenterBudgetDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getCostCenterBudgetDetails() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************CostCenterAjaxHandlerAction :: getCostCenterBudgetDetails Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                resultString = ServiceLocator.getCostCenterAjaxHandlerService().getCostCenterBudgetDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************CostCenterAjaxHandlerAction :: getCostCenterBudgetDetails Method End*********************");
        return null;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.httpServletResponse = hsr;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public String getCcCode() {
        return ccCode;
    }

    public void setCcCode(String ccCode) {
        this.ccCode = ccCode;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getCostCenterSearchList() {
        return costCenterSearchList;
    }

    public void setCostCenterSearchList(List costCenterSearchList) {
        this.costCenterSearchList = costCenterSearchList;
    }

    public int getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(int sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public String getCcFlag() {
        return ccFlag;
    }

    public void setCcFlag(String ccFlag) {
        this.ccFlag = ccFlag;
    }

    public int getCcId() {
        return ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
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

    public double getBudgetAmt() {
        return budgetAmt;
    }

    public void setBudgetAmt(double budgetAmt) {
        this.budgetAmt = budgetAmt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getDashBoardYear() {
        return dashBoardYear;
    }

    public void setDashBoardYear(int dashBoardYear) {
        this.dashBoardYear = dashBoardYear;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getCostCenters() {
        return costCenters;
    }

    public void setCostCenters(String costCenters) {
        this.costCenters = costCenters;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public String getApproveComments() {
        return approveComments;
    }

    public void setApproveComments(String approveComments) {
        this.approveComments = approveComments;
    }

    public String getRejectionComments() {
        return rejectionComments;
    }

    public void setRejectionComments(String rejectionComments) {
        this.rejectionComments = rejectionComments;
    }
}
