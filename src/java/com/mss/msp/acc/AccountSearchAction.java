/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.location.Country;
import com.mss.msp.location.State;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;

/**
 *
 * @author Kyle Bissell
 */
public class AccountSearchAction extends ActionSupport implements ServletRequestAware {

    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    private int responceFlag;
    /**
     * Page Drop Downs *
     */
    private List<State> accountState;
    private List<String> accountStatus;
    private List<Country> countries;
    private Map<Integer, String> types;
    private Map<Integer, String> industries;
    /**
     * Page Search Results *
     */
    private List<Account> accounts;
    /**
     * Holds Page Search values *
     */
    private Account account;

    public String searchAccount() {
        String resultType = LOGIN;
        populateData();
        resultType = SUCCESS;
        return resultType;
    }

    public String searchAccountBy() {
        String resultType = LOGIN;
        System.out.println("in accounts search loading1");
        if (getAccount() == null) {
            setAccount(new Account());
        }
        System.out.println("in accounts search loading2");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("in accounts search loading3");
            populateData();
            try {
                Integer orgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                //session usr id and primary role check wether csr or not if csr send usr_id
                int usrPriRole = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                System.out.println("in accounts search loading4-->" + usrPriRole);
                setResponceFlag(usrPriRole);

                int csrId = 0;
                if (usrPriRole == 1) {
                    csrId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                }
                accounts = ServiceLocator.getAccountService().doAccountSearch(getAccount(), orgId, csrId);
                if (!(accounts.size() > 0)) {
                    accounts = new ArrayList<Account>();
                }


                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("Attempiting to get Account info\nerrorMessage:", ex.toString());
                System.out.println(ex);
                resultType = ERROR;
            }
        }
        return resultType;
    }

    private void populateData() {
        populateStatus();
        populateCountries();
        populateIndustry();
        populateType();
        if (getAccountState() == null) {
            accountState = new ArrayList<State>();
        }
        if (getAccount().getCountry() != null && getAccount().getCountry().getId() != null
                && getAccount().getCountry().getId().intValue() >= 0) {
            accountState = ServiceLocator.getLocationService().getStatesByCountry(getAccount().getCountry().getId());
        }
    }
    //divya gandreti
    public String assignedRoles() {
        String resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                resultType = SUCCESS;
            } catch (Exception e) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", e.toString());
                System.out.println("Exception" + e);
                resultType = ERROR;
            }
        }
        return resultType;
    }

    private void populateStatus() {
        accountStatus = ServiceLocator.getAccountService().getAccountStatuses();
    }

    private void populateCountries() {
        countries = ServiceLocator.getLocationService().getCountries();
    }

    private void populateIndustry() {
        this.industries = ServiceLocator.getLookUpHandlerService().getIndustryTypesMap();
    }

    private void populateType() {
        this.types = ServiceLocator.getLookUpHandlerService().getAccountTypesMap();
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<State> getAccountState() {
        return accountState;
    }

    public void setAccountState(List<State> accountState) {
        this.accountState = accountState;
    }

    public List<String> getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(List<String> accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Map<Integer, String> getTypes() {
        return types;
    }

    public void setTypes(Map<Integer, String> types) {
        this.types = types;
    }

    public Map<Integer, String> getIndustries() {
        return industries;
    }

    public void setIndustries(Map<Integer, String> industries) {
        this.industries = industries;
    }

    public List<Account> getAccounts() {
        if (this.accounts == null) {
            this.accounts = new ArrayList<Account>();
        }
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.httpServletRequest = hsr;
    }

    public int getResponceFlag() {
        return responceFlag;
    }

    public void setResponceFlag(int responceFlag) {
        this.responceFlag = responceFlag;
    }
    
}
