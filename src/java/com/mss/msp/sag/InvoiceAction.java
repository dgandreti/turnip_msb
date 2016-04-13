/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
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
    private String searchFlag;
    private int currentYear;

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getInvoice() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getInvoice() {
        System.out.println("********************InvoiceAction :: getInvoice Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setInvoiceYear(Calendar.getInstance().get(Calendar.YEAR));


                setInvoiceVto(ServiceLocator.getInvoiceService().getInvoiceDetails(this, (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAction :: getInvoice Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : deleteInvoice() method is used to
     *
     *
     * *****************************************************************************
     */
    public String deleteInvoice() {
        System.out.println("********************InvoiceAction :: deleteInvoice Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                boolean response = ServiceLocator.getInvoiceService().deleteInvoice(getInvoiceId());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAction :: deleteInvoice Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doSearchInvoice() method is used to
     *
     *
     * *****************************************************************************
     */
    public String doSearchInvoice() {
        System.out.println("********************InvoiceAction :: doSearchInvoice Method Start*********************");

        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setInvoiceVto(ServiceLocator.getInvoiceService().doSearchInvoiceDetails(this, (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));
                setInvoiceYear(getInvoiceYear());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAction :: doSearchInvoice Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : goInvoiceEditDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public String goInvoiceEditDetails() {
        System.out.println("********************InvoiceAction :: goInvoiceEditDetails Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                setInvoiceVTOClass(ServiceLocator.getInvoiceService().goInvoiceEditDetails((this), (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString()));

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAction :: goInvoiceEditDetails Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doEditInvoiceDetatils() method is used to
     *
     *
     * *****************************************************************************
     */
    public String doEditInvoiceDetatils() {
        System.out.println("********************InvoiceAction :: doEditInvoiceDetatils Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                boolean responce = ServiceLocator.getInvoiceService().doEditInvoiceDetatils(getInvoiceVTOClass(), (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString(), this);

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAction :: doEditInvoiceDetatils Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 03/26/2016
     *
     * Author : Manikanta <meeralla@miraclesoft.com>
     *
     * ForUse : getOutstandingInvoiceList() method is used to get Outstanding Invoices List
     *
     *
     * *****************************************************************************
     */
    public String getOutstandingInvoiceList() {
        System.out.println("********************InvoiceAction :: getOutstandingInvoiceList Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                if ("invoiceSearch".equals(getSearchFlag())) {
                } else {
                    setCurrentYear(Calendar.getInstance().get(Calendar.YEAR));
                }
                invoiceVto = ServiceLocator.getInvoiceService().getOutstandingInvoiceList(this, (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER)).toString());
                if ("invoiceSearch".equals(getSearchFlag())) {
                } else {
                    setInvoiceYear(getCurrentYear());

                }

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAction :: getOutstandingInvoiceList Method End*********************");
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

    public String getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(String searchFlag) {
        this.searchFlag = searchFlag;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }
}
