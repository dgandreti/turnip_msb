/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usrajax;

import com.mss.msp.security.SecurityServiceProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DefaultDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.regex.Pattern;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miracle
 */
public class UserJSONAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String responseString;
    private DataSourceDataProvider dataSourceDataProvider;
    private DefaultDataProvider defaultDataProvider;
    private String loginId;
    private String message;

    public UserJSONAjaxHandlerAction() {
    }

    @SuppressWarnings("deprecation")
    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doLoginIdChecheck() method is used for
     *
     * *****************************************************************************
     */
    public String doLoginIdChecheck() {
        System.out.println("********************UserJSONAjaxHandlerAction :: doLoginIdChecheck Start*********************");
        int res = 0;
        try {
            res = DataSourceDataProvider.getInstance().checkLoginIdExistance(getLoginId());
            if (res == 0) {
                message = "valid";
                setResponseString(SUCCESS);
            } else {
                message = "EmailId already exists Please try with another LoginId";
                setResponseString(ERROR);
            }
        } catch (Exception ex) {
            setResponseString(ERROR);
            message = "Internal error!!!";
            ex.printStackTrace();
        } finally {
        }
        System.out.println("********************UserJSONAjaxHandlerAction :: doLoginIdChecheck End*********************");
        return getResponseString();
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

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the responseString
     */
    public String getResponseString() {
        return responseString;
    }

    /**
     * @param responseString the responseString to set
     */
    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
