/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.turnip;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
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
public class turnipAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private List vendorResourceList;
    private int sessionOrgId;
    private String dashYears;
    private String dashMonths;
    private String accountName;
    private String reqName;
    private int year;

    public String getVendorResourcePage() throws ServiceLocatorException {
        String resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setYear(Calendar.getInstance().get(Calendar.YEAR));
            resultType = SUCCESS;
        }
        return resultType;
    }

    public String vendorResourceDetails() throws ServiceLocatorException {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************turnipAction :: vendorResourceDetails Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
//                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
//                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                resultString = ServiceLocator.getTurnipService().getDefaultVendorResourceDetails(this);
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
        System.out.println("********************turnipAction :: vendorResourceDetails Method End*********************");
        return null;
    }

    public String projectResourceDetails() throws ServiceLocatorException {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************turnipAction :: vendorResourceDetails Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
//                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
//                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                resultString = ServiceLocator.getTurnipService().getDefaultProjectResourceDetails(this);
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
        System.out.println("********************turnipAction :: vendorResourceDetails Method End*********************");
        return null;
    }

    public String getClientRequirementsDashboards() throws ServiceLocatorException {
        String resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            resultType = SUCCESS;
        }
        return resultType;
    }

    public String getRegionResourcesDashboards() throws ServiceLocatorException {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************turnipAction :: clientResourceDetails Method Start*********************");
            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String resultString = null;
                resultString = ServiceLocator.getTurnipService().getDefaultRegionResourcesDetails(this);
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
        System.out.println("********************turnipAction :: clientResourceDetails Method End*********************");
        return null;
    }

    public String clientResourceDetails() throws ServiceLocatorException {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************turnipAction :: clientResourceDetails Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                resultString = ServiceLocator.getTurnipService().getDefaultClientResourceDetails(this);
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
        System.out.println("********************turnipAction :: clientResourceDetails Method End*********************");
        return null;
    }

    public String clientRequirementDetails() throws ServiceLocatorException {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("********************turnipAction :: clientResourceDetails Method Start*********************");
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                resultString = ServiceLocator.getTurnipService().getClientRequirementDetails(this);
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
        System.out.println("********************turnipAction :: clientResourceDetails Method End*********************");
        return null;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.httpServletRequest = hsr;
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

    public String getDashYears() {
        return dashYears;
    }

    public void setDashYears(String dashYears) {
        this.dashYears = dashYears;
    }

    public String getDashMonths() {
        return dashMonths;
    }

    public void setDashMonths(String dashMonths) {
        this.dashMonths = dashMonths;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }
}
