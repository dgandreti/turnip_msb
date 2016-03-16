/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

import com.mss.msp.location.Country;
import com.mss.msp.location.State;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Kyle Bissell
 */
public class AddAccountAction extends ActionSupport implements ServletRequestAware {

    /**
     *
     * /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    private String successMessage;
    private Integer userId;
    private Account account;
    private List<Country> countryList;
    private List<State> stateList;
    private Map<Integer, String> industryList;
    private Map<Integer, String> accountTypeList;
    private Map<Integer, String> vendorTypeList;

    public String getAddAccount() {
        String resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            Integer orgId = (Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

            if (isVaildInput()) {
                account.setUrl(account.getUrl().trim());
                account.setEmail_ext(account.getEmail_ext().trim());
                String existed = ServiceLocator.getAccountService().checkAccount(this.account, getUserId(), orgId);
                System.out.println("Account is---->" + existed);
                if (existed != null) {
                    System.out.println("existed: " + existed);
                    resultMessage = "";
                    successMessage = existed;
                    resultType = SUCCESS;

                }
                if (existed.equals("")) {
                    if (ServiceLocator.getAccountService().addAccount(this.account, getUserId(), orgId)) {
                        resultMessage = "";
                        successMessage = "Successfully Added your Account: " + account.getName();
                        resultType = SUCCESS;
                    } else {
                        resultType = ERROR;
                        resultMessage = "Failed to Add your Account";
                    }
                }
            } else {
                resultType = SUCCESS;
                if (this.account == null) {
                    this.account = new Account();
                }
            }
            createDropDowns();
        }// Session validator if END

        return resultType;
    }

    private void createDropDowns() {
        populateCountryList();
        populateIndustryList();
        populateAccountTypeList();
        //populateVendorTypeList();
        setStateList(new ArrayList<State>());
        if (this.account != null
                && this.account.getCountry() != null
                && this.account.getCountry().getId() != null
                && this.account.getCountry().getId().intValue() > 0) {
            setStateList(ServiceLocator.getLocationService().getStatesByCountry(this.account.getCountry().getId()));
        }
    }

    private void populateCountryList() {
        countryList = ServiceLocator.getLocationService().getCountries();
    }

    private void populateIndustryList() {
        this.industryList = ServiceLocator.getLookUpHandlerService().getIndustryTypesMap();
    }

    private void populateAccountTypeList() {
        this.accountTypeList = ServiceLocator.getLookUpHandlerService().getAccountTypesMap();
    }

    private void populateVendorTypeList() {
        this.vendorTypeList = ServiceLocator.getLookUpHandlerService().getVendorTypesMap();
    }

    private boolean isVaildInput() {
        boolean isVaildInput = true;
        if (account == null) {
            isVaildInput = false;
            resultMessage = "";
        } else if (account.getName() == null || account.getName().equals("")) {
            isVaildInput = false;
            resultMessage = "Please enter an Account name";
        } else if (account.getUrl() == null || account.getUrl().equals("")) {
            isVaildInput = false;
            resultMessage = "Please enter an Account Url";
        }
        else if (account.getTypeId() == null || account.getTypeId().intValue() < 0) {
            isVaildInput = false;
            resultMessage = "Please select an Account Type";
        }
        else if (account.getEmail_ext() == null || account.getEmail_ext().equals("")) {
            isVaildInput = false;
            resultMessage = "Please enter Email extension";
        } else if (account.getAddress1() == null || account.getAddress1().equals("")) {
            account.setAddress1(null);
        } else if (account.getAddress2() == null || account.getAddress2().equals("")) {
            account.setAddress2(null);
        } //    else if (account.getState() == null || account.getState().getId() == null ) {
        //      isVaildInput = false;
        //      resultMessage = "Please Enter a state";
        //    } else if (account.getCountry() == null || account.getCountry().getId() == null) {
        //      isVaildInput = false;
        //      resultMessage = "Please Enter a country";
        //    }
        else if (account.getZip() == null || account.getZip().equals("")) {
            account.setZip(null);
        } //    else if (account.getPhone() == null || account.getPhone().equals("")) {
        //      isVaildInput = false;
        //      resultMessage = "Please Enter a phone number";
        //    } else if (account.getIndustryId() == null || account.getIndustryId().intValue() < 0) {
        //      isVaildInput = false;
        //      resultMessage = "Please select an industry(id)";
        //    } else if (account.getStockSymbol() == null || account.getStockSymbol().equals("")) {
        //      isVaildInput = false;
        //      resultMessage = "Please Enter a stockSymbol";
        //    }  else if (account.getDescription() == null || account.getDescription().equals("")) {
        //      isVaildInput = false;
        //      resultMessage = "Please Enter a description";
        //    } else if (account.getDescription().length() > ApplicationConstants.ACCOUNT_DESCRIPTION_LENGTH  ){
        //      isVaildInput = false;
        //      resultMessage = "Please enter a shorter description ("+ApplicationConstants.ACCOUNT_DESCRIPTION_LENGTH+" Characters) or less";
        //    }
        
//    else if (account.getTypeId().intValue() == 5) {
//      if (account.getVendorTypeId() == null || account.getVendorTypeId().intValue() < 0) {
//        isVaildInput = false;
//        resultMessage = "Please select a vendor type";
//      }
//    }

        return isVaildInput;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.httpServletRequest = hsr;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    public Map<Integer, String> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(Map<Integer, String> industryList) {
        this.industryList = industryList;
    }

    public Map<Integer, String> getAccountTypeList() {
        return accountTypeList;
    }

    public void setAccountTypeList(Map<Integer, String> accountTypeList) {
        this.accountTypeList = accountTypeList;
    }

    public Map<Integer, String> getVendorTypeList() {
        return vendorTypeList;
    }

    public void setVendorTypeList(Map<Integer, String> vendorTypeList) {
        this.vendorTypeList = vendorTypeList;
    }
}
