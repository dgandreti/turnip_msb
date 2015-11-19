/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.detailsAjax;

import com.mss.msp.acc.Account;
import com.mss.msp.acc.details.AccountDetails;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Greg
 */
public class AccountDetailsAjaxAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private String resultType;
    private String resultMessage;
    private AccountDetails accountDetails;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private List<String> states;
    private List<String> countries;
    private String accountNameCheck;
    private String accountURLCheck;
    private String viewAccountID;
    private int userSessionId;
      private String skillValues;
    
    public String updateAccount() {
        System.out.println("==== \tIN UPDATE\t =====\nUPDATEING "+viewAccountID);
        try {
            String id = (servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            if (id != null) {
                accountDetails.setId(Integer.parseInt(viewAccountID));
                System.out.println("UPDATING INFO for \n" + accountDetails);
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                com.mss.msp.util.DataSourceDataProvider.getInstance().updateAccountLastAccessedBy(accountDetails.getId(), getUserSessionId(), "Account Details Updated");
                setAccountDetails(ServiceLocator.getAccountDetailsAjaxHandlerService().ajaxAccountUpdate(accountDetails,Integer.parseInt(id),getUserSessionId()));
                resultMessage = "";
                resultType = SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();;
            System.out.println("Problem updating account : ==> (" + e.getMessage() + ");");
            resultMessage = "Problem updating account : ==> (" + e.getMessage() + ");";
            resultType = ERROR;
        }
        return resultType;
    }

    public String checkForName() {
        try {
            servletResponse.addHeader("exists", "");

            String id = (servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            if (id == null) {
                id = "-1";//can still use searchFor name with out attached account
                //NO account names with id of -1 <-------------------------------------------------------
            }
            if (ServiceLocator.getAccountDetailsAjaxHandlerService().checkForAccountName(accountNameCheck, Integer.parseInt(id))) {
                servletResponse.setHeader("exists", "notFree");
                resultType = SUCCESS;//the name exists, and is not the current account name
            } else {
                servletResponse.setHeader("exists", "free");
                resultType = SUCCESS; //the name does not exist, or is the current account name
            }
            resultMessage = "";
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "Problem checking account name: ==> (" + e.getMessage() + ");";
            resultType = ERROR;
        }
        return resultType;
    }

    public String checkForURL() {
        try {
            servletResponse.addHeader("urlexists", "");

            String id = (servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            if (id == null) {
                id = "-1";//can still use searchFor name with out attached account
                //NO account names with id of -1 <-------------------------------------------------------
            }
            if (ServiceLocator.getAccountDetailsAjaxHandlerService().checkForAccountURL(accountURLCheck, Integer.parseInt(id))) {
                servletResponse.setHeader("urlexists", "notFree");
                resultType = SUCCESS;//the url exists, and is not the current account name
            } else {
                servletResponse.setHeader("urlexists", "free");
                resultType = SUCCESS; //the url does not exist, or is the current account name
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "Problem checking account name: ==> (" + e.getMessage() + ");";
            resultType = ERROR;
        }
        return resultType;
    }

    public String ajaxLoadContacts() {
        return SUCCESS;
    }
    
    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getAccountNameCheck() {
        return accountNameCheck;
    }

    public void setAccountNameCheck(String accountNameCheck) {
        this.accountNameCheck = accountNameCheck;
    }

    public String getAccountURLCheck() {
        return accountURLCheck;
    }

    public void setAccountURLCheck(String accountURLCheck) {
        this.accountURLCheck = accountURLCheck;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.servletRequest = hsr;
    }

    public void setServletResponse(HttpServletResponse hsr) {
        this.servletResponse = hsr;
    }

    public String getViewAccountID() {
        return viewAccountID;
    }

    public void setViewAccountID(String viewAccountID) {
        this.viewAccountID = viewAccountID;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getSkillValues() {
        return skillValues;
    }

    public void setSkillValues(String skillValues) {
        this.skillValues = skillValues;
    }

  
    
}
