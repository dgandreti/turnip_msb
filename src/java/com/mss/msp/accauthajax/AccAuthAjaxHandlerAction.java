/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauthajax;

/**
 *
 * @author miracle
 */
import com.mss.msp.util.DefaultDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccAuthAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String responseString;
    private DataSourceDataProvider dataSourceDataProvider;
    private DefaultDataProvider defaultDataProvider;
    private int flag;
    private int actionId;
    private String desc;
    private String status;
    private String actionName;
    private String accType;
    private String accName;
    private int roles;
    private int orgId;
    private int id;
    private int blockFlag;
    private String accNameFlag;
    private int userSessionId;
    private String typeOfUser;
    private int userGroupId;
    private String actionResourceStatus;
    private String actionHiddenName;
    private int actionHiddenRole;

    public AccAuthAjaxHandlerAction() {
    }

    /**
     * *****************************************************************************
     * Date : July 20 2015
     *
     * Author : Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : insertOrUpdateAccAuth() method is used to
     *
     *
     * *****************************************************************************
     */
    public String insertOrUpdateAccAuth() throws IOException, ServiceLocatorException {
        System.out.println("********************AccAuthAjaxHandlerAction :: insertOrUpdateAccAuth Method Start*********************");
        try {

            responseString = ServiceLocator.getAccAuthAjaxservice().insertOrUpdateAccAuth(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
        }
        System.out.println("********************AccAuthAjaxHandlerAction :: insertOrUpdateAccAuth Method End*********************");

        return null;
    }
     /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRolesForAccType() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */

    public String getRolesForAccType() throws IOException, ServiceLocatorException {
        System.out.println("********************AccAuthAjaxHandlerAction :: getRolesForAccType Method Start*********************");

        try {

            responseString = ServiceLocator.getAccAuthAjaxservice().getRolesForAccType(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
        }
        System.out.println("********************AccAuthAjaxHandlerAction :: getRolesForAccType Method End*********************");

        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAccountNames() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */
    public String getAccountNames() throws IOException, ServiceLocatorException {
        System.out.println("********************AccAuthAjaxHandlerAction :: getAccountNames Method Start*********************");

        try {

            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
            responseString = ServiceLocator.getAccAuthAjaxservice().getAccountNames(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
        }
        System.out.println("********************AccAuthAjaxHandlerAction :: getAccountNames Method End*********************");

        return null;
    }

     /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getActionResorucesSearchResults() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */
    public String getActionResorucesSearchResults() throws IOException, ServiceLocatorException {
        System.out.println("********************AccAuthAjaxHandlerAction :: getActionResorucesSearchResults Method Start*********************");

        try {

            responseString = ServiceLocator.getAccAuthAjaxservice().getActionResorucesSearchResults(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
        }
        System.out.println("********************AccAuthAjaxHandlerAction :: getActionResorucesSearchResults Method End*********************");

        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : insertOrUpdateActionResources() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */
    public String insertOrUpdateActionResources() throws IOException, ServiceLocatorException {
        System.out.println("********************AccAuthAjaxHandlerAction :: insertOrUpdateActionResources Method Start*********************");

        try {

            responseString = ServiceLocator.getAccAuthAjaxservice().insertOrUpdateActionResources(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
        }
        System.out.println("********************AccAuthAjaxHandlerAction :: insertOrUpdateActionResources Method End*********************");

        return null;
    }
    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : actionResourceTermination() method is used to get state names of a particular
     * country.
     *
     * *****************************************************************************
     */
    public String actionResourceTermination() throws IOException, ServiceLocatorException {
        System.out.println("********************AccAuthAjaxHandlerAction :: actionResourceTermination Method Start*********************");
        try {

            responseString = ServiceLocator.getAccAuthAjaxservice().actionResourceTermination(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
        }
        System.out.println("********************AccAuthAjaxHandlerAction :: actionResourceTermination Method End*********************");

        return null;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlockFlag() {
        return blockFlag;
    }

    public void setBlockFlag(int blockFlag) {
        this.blockFlag = blockFlag;
    }

    public String getAccNameFlag() {
        return accNameFlag;
    }

    public void setAccNameFlag(String accNameFlag) {
        this.accNameFlag = accNameFlag;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getActionResourceStatus() {
        return actionResourceStatus;
    }

    public void setActionResourceStatus(String actionResourceStatus) {
        this.actionResourceStatus = actionResourceStatus;
    }

    public String getActionHiddenName() {
        return actionHiddenName;
    }

    public void setActionHiddenName(String actionHiddenName) {
        this.actionHiddenName = actionHiddenName;
    }

    public int getActionHiddenRole() {
        return actionHiddenRole;
    }

    public void setActionHiddenRole(int actionHiddenRole) {
        this.actionHiddenRole = actionHiddenRole;
    }
}
