/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sagAjax;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class InvoiceAjaxAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int invoiceMonth;
    private int invoiceYear;
    private String invoiceResource;
    private boolean cheked;
    private int usrSessionId;
    private int orgSessionId;
    private int serviceId;
    private String his_status;
    private String his_serviceType;
    private int usrId;
    private String invEmailSubject;
    private String invEmailBodyContent;
    private int invCreatedBy;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getTotalHoursTooltip() {
        resultType = LOGIN;
        System.out.println("********************InvoiceAjaxAction :: getTotalHoursTooltip Method Start*********************");

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {


                String details = ServiceLocator.getInvoiceAjaxService().getTotalHoursTooltip(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(details);

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************InvoiceAjaxAction :: getTotalHoursTooltip Method End*********************");
        return null;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : generateInvoice() method is used
     *
     *
     * *****************************************************************************
     */
    public String generateInvoice() {
        System.out.println("********************InvoiceAjaxAction :: generateInvoice Method Start*********************");

        resultType = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUsrSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String editoverlay = ServiceLocator.getInvoiceAjaxService().generateInvoice(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + editoverlay + "");
            }
        } catch (Exception ex) {

            resultType = ERROR;
        }
        System.out.println("********************InvoiceAjaxAction :: generateInvoice Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getStates() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */
    public String getRecreatedList() {
        System.out.println("********************InvoiceAjaxAction :: getRecreatedList Method Start*********************");

        resultType = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUsrSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String recreatedList = ServiceLocator.getInvoiceAjaxService().getRecreatedList(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + recreatedList + "");
            }
        } catch (Exception ex) {

            resultType = ERROR;
        }
        System.out.println("********************InvoiceAjaxAction :: getRecreatedList Method End*********************");
        return null;
    }
    /**
     * *****************************************************************************
     * Date : 03/26/2016
     *
     * Author : Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : sendPendingInvMail() method is used to send Pending Invoice Mail
     * 
     *
     * *****************************************************************************
     */
     public String sendPendingInvMail() {
        resultType = LOGIN;
        System.out.println("********************InvoiceAjaxAction :: sendPendingInvMail Method Start*********************");
        try {
            System.out.println("Ajax  action sendPendingInvMail");
            String resultString = "";
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUsrSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                MailManager mailManager = new MailManager();
                System.out.println("sendPendingInvMail getInvEmailBodyContent->"+getInvEmailBodyContent());
                System.out.println("sendPendingInvMail getInvEmailSubject->"+getInvEmailSubject());
                int status = mailManager.sendPendingInvMail(this, DataSourceDataProvider.getInstance().getEmailIdbyuser(getInvCreatedBy()));
                if (status != 0) {
                    resultString = "success";
                } else {
                    resultString = "error";
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + resultString + "");
            }
        } catch (Exception ex) {

            resultType = ERROR;
        }
          System.out.println("********************InvoiceAjaxAction :: sendPendingInvMail Method End*********************");
        return null;
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

    public String getInvoiceResource() {
        return invoiceResource;
    }

    public void setInvoiceResource(String invoiceResource) {
        this.invoiceResource = invoiceResource;
    }

    public boolean isCheked() {
        return cheked;
    }

    public void setCheked(boolean cheked) {
        this.cheked = cheked;
    }

    public int getUsrSessionId() {
        return usrSessionId;
    }

    public void setUsrSessionId(int usrSessionId) {
        this.usrSessionId = usrSessionId;
    }

    public int getOrgSessionId() {
        return orgSessionId;
    }

    public void setOrgSessionId(int orgSessionId) {
        this.orgSessionId = orgSessionId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getHis_status() {
        return his_status;
    }

    public void setHis_status(String his_status) {
        this.his_status = his_status;
    }

    public String getHis_serviceType() {
        return his_serviceType;
    }

    public void setHis_serviceType(String his_serviceType) {
        this.his_serviceType = his_serviceType;
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public String getInvEmailSubject() {
        return invEmailSubject;
    }

    public void setInvEmailSubject(String invEmailSubject) {
        this.invEmailSubject = invEmailSubject;
    }

    public String getInvEmailBodyContent() {
        return invEmailBodyContent;
    }

    public void setInvEmailBodyContent(String invEmailBodyContent) {
        this.invEmailBodyContent = invEmailBodyContent;
    }

    public int getInvCreatedBy() {
        return invCreatedBy;
    }

    public void setInvCreatedBy(int invCreatedBy) {
        this.invCreatedBy = invCreatedBy;
    }
    
    
}
