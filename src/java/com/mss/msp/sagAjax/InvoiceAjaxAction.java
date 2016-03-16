/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sagAjax;

import com.mss.msp.util.ApplicationConstants;
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

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
    
     public String getTotalHoursTooltip() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                
                // userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                String details = ServiceLocator.getInvoiceAjaxService().getTotalHoursTooltip(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text/html");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(details);
//                resultMessage = null;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return null;

    }

    public String generateInvoice() {
        resultType = LOGIN;
        try {
            System.out.println("Ajax Handler action generateInvoice");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUsrSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String editoverlay = ServiceLocator.getInvoiceAjaxService().generateInvoice(this);
                System.out.println("this is generate invoice-->" + editoverlay);
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
        return null;
    }
     public String getRecreatedList() {
        resultType = LOGIN;
        try {
            System.out.println("Ajax Handler action SOW");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUsrSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String recreatedList = ServiceLocator.getInvoiceAjaxService().getRecreatedList(this);
                System.out.println("this is generate invoice-->" + recreatedList);
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
    
    
}
