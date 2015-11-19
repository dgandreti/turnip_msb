/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class InvoiceAction extends ActionSupport implements ServletRequestAware {

    private String resultType;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private List invoiceVto;
    private int userSeessionId;
    private int sessionOrgId;
    private int invoiceId;
    private int invoiceMonth;
    private int invoiceYear;
    private int usrId;
    private String invoiceResource;
    private String invVendor;
    private String invoiceStatus;
    private InvoiceVTO invoiceVTOClass;

    public String getInvoice() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("hear updation is completed....");
                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));


                setInvoiceVto(ServiceLocator.getInvoiceService().getInvoiceDetails(this, (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String deleteInvoice() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("hear delete is completed...." + getInvoiceId());
//                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
//
//              
                boolean response = ServiceLocator.getInvoiceService().deleteInvoice(getInvoiceId());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String doSearchInvoice() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("hear Search is completed....");
                //            userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
//                setInvoiceVto(ServiceLocator.getInvoiceService().getInvoiceDetails(this, (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));
                System.out.println("invoiceMonth-->" + getInvoiceMonth());
                System.out.println("invoiceYear-->" + getInvoiceYear());
                System.out.println("usrId-->" + getUsrId());
                System.out.println("invoiceResource-->" + getInvoiceResource());
                System.out.println("invVendor-->" + getInvVendor());
                System.out.println("invoiceStatus-->" + getInvoiceStatus());
                setInvoiceVto(ServiceLocator.getInvoiceService().doSearchInvoiceDetails(this, (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String goInvoiceEditDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("hear Search is completed....");
                // userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setInvoiceVTOClass(ServiceLocator.getInvoiceService().goInvoiceEditDetails((this),(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String doEditInvoiceDetatils() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("hear Search is completed....");
                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                boolean responce=ServiceLocator.getInvoiceService().doEditInvoiceDetatils(getInvoiceVTOClass(),(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString(),this);

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public List getInvoiceVto() {
        return invoiceVto;
    }

    public void setInvoiceVto(List invoiceVto) {
        this.invoiceVto = invoiceVto;
    }

    public int getUserSeessionId() {
        return userSeessionId;
    }

    public void setUserSeessionId(int userSeessionId) {
        this.userSeessionId = userSeessionId;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceMonth(int invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public int getInvoiceYear() {
        return invoiceYear;
    }

    public void setInvoiceYear(int invoiceYear) {
        this.invoiceYear = invoiceYear;
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public String getInvoiceResource() {
        return invoiceResource;
    }

    public void setInvoiceResource(String invoiceResource) {
        this.invoiceResource = invoiceResource;
    }

    public String getInvVendor() {
        return invVendor;
    }

    public void setInvVendor(String invVendor) {
        this.invVendor = invVendor;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public InvoiceVTO getInvoiceVTOClass() {
        return invoiceVTOClass;
    }

    public void setInvoiceVTOClass(InvoiceVTO invoiceVTOClass) {
        this.invoiceVTOClass = invoiceVTOClass;
    }
}
