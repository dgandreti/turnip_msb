/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.costCenter;

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
 * *************************************
 *
 * @CostCenterAction() Used to cost center functionality
 *
 * @Author:Divya Gandreti<dgandreti@miraclesoft.com>
 *
 * @Created Date:10/01/2015
 *
 **************************************
 */
public class CostCenterAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

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
    private String quarter;
    private String budgetStatus;
    private String approveComments;
    private String rejectionComments;
    List<CostCenterVTO> costCenterVTOList = new ArrayList<CostCenterVTO>();
    private int orgId;
    private int dashBoardYear;
    private String typeOfUser;

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String costCenterSearch() {
        System.out.println(":::::::::::::: costCenterSearch ==> costCenterSearch ::::::::::::::::::");
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setCostCenterSearchList(ServiceLocator.getCostCenterService().costCenterSearch(this));
                System.out.println("---list----duc--------" + getCostCenterSearchList());
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String costCenterInfoSearch() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setDashBoardYear(Calendar.getInstance().get(Calendar.YEAR));
                setCostCenterSearchList(ServiceLocator.getCostCenterService().costCenterInfoSearch(this));
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : October 01, 2015, 04:13 PM IST
     * Author:Manikanta<meeralla@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String costCenterDashBoardDetails() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            try {
                setDashBoardYear(Calendar.getInstance().get(Calendar.YEAR));
                costCenterVTOList = ServiceLocator.getCostCenterService().costCenterDashBoardDetails(this);
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
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

    public List<CostCenterVTO> getCostCenterVTOList() {
        return costCenterVTOList;
    }

    public void setCostCenterVTOList(List<CostCenterVTO> costCenterVTOList) {
        this.costCenterVTOList = costCenterVTOList;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
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
