/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauth;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.interceptor.ParameterAware;

/**
 *
 * @author NagireddySeerapu
 */
public class AccAuthAction extends ActionSupport implements ServletRequestAware, ParameterAware {

    // private int userId;
    /**
     *
     * /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    private String resultType;
    private String queryString;
    private String successMessage;
    private Map parameters;
    List<AccauthVTO> accauthVTO = new ArrayList<AccauthVTO>();
   // private String status;
    private int action_id;
    private String action_name;
    private String status;
    private String description;
    private String flag;
    private int id;
    private Map rolesMap;
    private String accType;
    private String rollName;
    private String accountName;
    private int roleId;
    private String actionName;
    private Map userGroupIdList;
    private int userGroupList;
    /**
     * Method : Account add action
     *
     */
    public String getAccAuthrization() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            resultType = "accessFailed";

            try {
                setStatus("Active");
                accauthVTO = ServiceLocator.getAccAuthservice().getAccAuthrization(this);


                resultType = SUCCESS;





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }
    public String searchAccAuthorization() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            resultType = "accessFailed";

            try {
                accauthVTO = ServiceLocator.getAccAuthservice().searchAccAuthorization(this);


                resultType = SUCCESS;





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }
    //getSearchAccAuthorisationResults
     public String searchActionResources() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            resultType = "accessFailed";

            try {
                accauthVTO = ServiceLocator.getAccAuthservice().searchActionResources(this);


                resultType = SUCCESS;





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }
      public String actionResourcesForAddOrUpdate() {
        resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            resultType = "accessFailed";

            try {
              //  accauthVTO = ServiceLocator.getAccAuthservice().searchActionResources(this,httpServletRequest);
                setUserGroupIdList(com.mss.msp.util.DataSourceDataProvider.getInstance().getInstance().getRequiteCategory());
                setRolesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getRolesForAccType(getAccType()));

                resultType = SUCCESS;





            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
                ex.printStackTrace();
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }

        }//Close Session Checking
        return resultType;
    }
    
    

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public List<AccauthVTO> getAccauthVTO() {
        return accauthVTO;
    }

    public void setAccauthVTO(List<AccauthVTO> accauthVTO) {
        this.accauthVTO = accauthVTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map getRolesMap() {
        return rolesMap;
    }

    public void setRolesMap(Map rolesMap) {
        this.rolesMap = rolesMap;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map getUserGroupIdList() {
        return userGroupIdList;
    }

    public void setUserGroupIdList(Map userGroupIdList) {
        this.userGroupIdList = userGroupIdList;
    }

    public int getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(int userGroupList) {
        this.userGroupList = userGroupList;
    }
    
    
}
