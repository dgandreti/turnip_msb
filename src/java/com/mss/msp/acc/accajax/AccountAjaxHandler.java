/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.accajax;

import com.mss.msp.acc.Account;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author NagireddySeerapu
 */
public class AccountAjaxHandler extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    private String resultMessage;
    private Account account;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private List<String> states;
    private List<String> countries;
    private String accountNameCheck;
    private String accountURLCheck;
    private int accountSearchOrgId;
    private int orgUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String status;
    private int userSessionId;
    //for email phone
    private int userId;
    private String ContactEmail;
    private int id;
    private DataSourceDataProvider dataSourceDataProvider;
    //for vendor tier
    private String accountSearchID;
    private Map vendorTierMap;
    private String vendorTier;
    private String VendorTierId;
    private String tierId;
    private String vendorTierStatus;
    private String vendorTierType;
    private String TierStatus;
    private String typeOfAccount;
    // Add By Aklakh, for reporting person
    private int designationId;
    private int projectID;
    //for adding vendor fro acustomer
    private int vendorId;
    private String vendorStatus;
    private int pf;
    private String vendorComments;
    private String csrName;
    private String csrStatus;
    private String csrEmail;
    private String csrphone;
    private String accountName;
    private String csrId;
    private int toCSRID;
    private int fromCSRID;
    private String typeTransfer;
    private String accCity;
    private String accCountry;
    private String accState;
    private String accPhone;
    private String locationFlag;
    private String locationAddress1;
    private String locationAddress2;
    private String locationZip;
    private String locationFax;
    private String locationId;
    private String locationStatus;
    private String locationName;
    private int viewAccountID;
    private String validFrom;
    private String validTo;
    private String vendorDocs;
    private String acc_attachment_name;
    private int acc_attachment_id;
    private int attachment_id;
    private String attachmentTitle;

    public String csrStatusChange() {
        int responseStrign;
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                userSessionId = Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                responseStrign = ServiceLocator.getAccountAjaxHandlerService().csrStatusChange(this, getOrgUserId());
                servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(responseStrign);

            } catch (Exception e) {
            }
        }

        return null;
    }

    public String doAddAccountToCsr() {
        int responseStrign;
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                userSessionId = Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                responseStrign = ServiceLocator.getAccountAjaxHandlerService().doAddAccountToCsr(this, getOrgUserId());
                System.out.println("this is adding acc to csr value--->" + responseStrign);
                servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write("" + responseStrign + "");

            } catch (Exception e) {
            }
        }

        return null;
    }

    public String goAddintAccToCsr() {
        System.out.println("this goAddintAccToCsr method");
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setAccountName(com.mss.msp.util.DataSourceDataProvider.getInstance().getAccountNameById(orgUserId));
                System.out.println("this is account name-->" + getAccountName());
            } catch (Exception e) {
            }
        }
        return SUCCESS;
    }

    public String getCsrDetailsTable() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                reponseString = ServiceLocator.getAccountAjaxHandlerService().getCsrDetailsTable(this, getOrgUserId());
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                return null;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String updateAccount() {
        System.out.println("==== \tIN UPDATE\t =====");
        try {
            String id = (servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            if (id != null) {
                account.setId(Integer.parseInt(id));
                System.out.println("UPDATING INFO for " + account);
                setAccount(ServiceLocator.getAccountAjaxHandlerService().ajaxAccountUpdate(account));
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

    public String checkVendorName() {
        try {
            servletResponse.addHeader("exists", "");

            String id = null;
            System.out.println("=================" + id);
            if (id == null) {
                id = "-1";//can still use searchFor name with out attached account
                //NO account names with id of -1 <-------------------------------------------------------
            }
            if (ServiceLocator.getAccountAjaxHandlerService().checkForAccountName(accountNameCheck, Integer.parseInt(id))) {
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

    public String checkForRegistrationURL() {
        try {
            servletResponse.addHeader("urlexists", "");
            System.out.println("---------url------------" + accountURLCheck);
            String id = null;
            if (id == null) {
                id = "-1";//can still use searchFor name with out attached account
                //NO account names with id of -1 <-------------------------------------------------------
            }
            if (ServiceLocator.getAccountAjaxHandlerService().checkForAccountURL(accountURLCheck, Integer.parseInt(id))) {
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

    public String checkForName() {
        try {
            servletResponse.addHeader("exists", "");

            String id = (servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            if (id == null) {
                id = "-1";//can still use searchFor name with out attached account
                //NO account names with id of -1 <-------------------------------------------------------
            }
            if (ServiceLocator.getAccountAjaxHandlerService().checkForAccountName(accountNameCheck, Integer.parseInt(id))) {
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
            if (ServiceLocator.getAccountAjaxHandlerService().checkForAccountURL(accountURLCheck, Integer.parseInt(id))) {
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

    public String getContactDetails() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getAccountSearchOrgId());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getContactDetails(getAccountSearchOrgId(), typeOfAccount);
                System.out.println("===============>in titles" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getLocations() method is used to get multiple Locations details of
     * account
     *
     * @Author:jagan<jchukkala@miraclesoft.com>
     *
     * @Created Date:25/09/2015
     *
     **************************************
     */
    public String getLocations() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getLocations");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getAccountSearchOrgId());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getLocationDetails(getAccountSearchOrgId(), this);
                System.out.println("===============>in titles" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @editLocations() method is used to edit Location details of account
     *
     * @Author:jagan<jchukkala@miraclesoft.com>
     *
     * @Created Date:25/09/2015
     *
     **************************************
     */
    public String editLocations() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->editLocations()");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                reponseString = ServiceLocator.getAccountAjaxHandlerService().editLocationDetails(this);
                System.out.println("===============>in titles" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @addOrUpdateLocations() method is used to addOrUpdate Location details of
     * account
     *
     * @Author:jagan<jchukkala@miraclesoft.com>
     *
     * @Created Date:25/09/2015
     *
     **************************************
     */
    public String addOrUpdateLocations() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                reponseString = ServiceLocator.getAccountAjaxHandlerService().addOrUpdateLocationDetails(this, getOrgUserId());
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                return null;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String saveUserContacts() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getOrgUserId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().saveUserContacts(getOrgUserId(), userSessionId);
                if (reponseString.equalsIgnoreCase("Login credentials Sent Succesfully to email")) {
                    String message = "ContactRegistered";
                    dataSourceDataProvider.getInstance().updateAccountLastAccessedBy(getAccountSearchOrgId(), getUserSessionId(), message);
                }
                //System.out.println("===============>in titles" + repoString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String getContactSearchResults() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getAccountSearchOrgId());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getContactSearchResults(this, getAccountSearchOrgId());
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getEmailPhoneDetails() method is used to get Requirement details of
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String getEmailPhoneDetails() {
        System.out.println("$$$$$$$$$$$$$ ENTERED INTO THE ACCOUNT AJAX HANDLER ACTION $$$$$$$$$$$$$$$$$");
        System.out.println(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID));
        resultMessage = LOGIN;
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("$$$$$$$$$$$$$ ENTERED INTO THE ACCOUNT AJAX HANDLER ACTION $$$$$$$$$$$$$$$$$");
                String details = dataSourceDataProvider.getInstance().getEmailPhoneDetails(getUserId());
                System.out.println("AFTER DSDP CALL>>>>>>>>>>>IN ACTION");
 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(details);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    public String doOfficeEmailVerify() {
        System.out.println("-------In action class-------");
        resultMessage = LOGIN;
        int result;
        try {
            result = DataSourceDataProvider.getInstance().checkLoginIdExistance(getContactEmail());
            //  System.out.println("result-------"+result);
             servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
            if (result == 0) {
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write("success");
            } else {
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(ERROR);
            }
            System.err.println("resultString---->" + result);

        } catch (Exception ex) {
            servletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
        }
        return null;
    }

    public String doEmailVerify() {
        System.out.println("-------In action class-------");
        resultMessage = LOGIN;
        int result;
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID) != null) {
            try {
                result = DataSourceDataProvider.getInstance().checkLoginIdExistance(getContactEmail());
                //  System.out.println("result-------"+result);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                if (result == 0) {
                    servletResponse.setContentType("text");
                    servletResponse.setCharacterEncoding("UTF-8");
                    servletResponse.getWriter().write("success");
                } else {
                    servletResponse.setContentType("text");
                    servletResponse.setCharacterEncoding("UTF-8");
                    servletResponse.getWriter().write(ERROR);
                }
                System.err.println("resultString---->" + result);

            } catch (Exception ex) {
                servletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            }
        }
        return null;
    }

//added by shankar
    public String getContactState() {

        try {
            System.out.println("I am   in States action");
            // Map countries =ServiceLocator.getLocationService().getCountriesNames();
            String states = ServiceLocator.getGeneralService().getStatesOfCountry(servletRequest, getId());
            //setStateMap(states);
            System.out.println("list of States----->" + states);
//            setResultMessage(getResultMessage());
//            httpServletRequest.setAttribute("stateList", states);
//            setResultMessage(getResultMessage());
            servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
            servletResponse.setContentType("text");
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.getWriter().write(states);



            //System.out.println(httpServletRequest.getAttribute("statesList"));


        } catch (Exception ex) {
            servletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            //resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getDefaultVendorTiers() used to get vendor tiers related to account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String getDefaultVendorTiers() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setVendorTierMap(dataSourceDataProvider.getInstance().getVendorTierTypes());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getDefaultVendorTiers(this);
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @addVendorTierToCustmer() used to add vendor tier to account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String addVendorTierToCustmer() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setVendorTierMap(dataSourceDataProvider.getInstance().getVendorTierTypes());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().addVendorTierToCustmer(this);
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @addVendorTierToCustmer()used to edit a vendor tier to account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/28/2015
     *
     **************************************
     */
    public String editVendorTierDetails() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setVendorTierMap(dataSourceDataProvider.getInstance().getVendorTierTypes());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().editVendorTierDetails(this);
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @searchVendorTierDetails() used to search vendor tier relation with
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/28/2015
     *
     **************************************
     */
    public String searchVendorTierDetails() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setVendorTierMap(dataSourceDataProvider.getInstance().getVendorTierTypes());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().searchVendorTierDetails(this);
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @setReportingPerson() used to set reporting person for project assign
     * account
     *
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date: June 20 2015
     *
     **************************************
     */
    public String setReportingPerson() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->setReportingPerson");
            System.out.println("Designation id----->" + getDesignationId() + "projectId---->" + getProjectID());

            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                //reponseString = ServiceLocator.getAccountAjaxHandlerService().getReportingPersonDetails(this, getDesignationId());
                System.out.println("===============>in searchResults" + reponseString);
                
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getVendorDetails() used to set reporting person for project assign
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date: June 29 2015
     *
     **************************************
     */
    public String getVendorDetails() {
        resultType = LOGIN;
        String resultString = "";
        try {
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                resultString = ServiceLocator.getAccountAjaxHandlerService().getVendorDetails(this);
               
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(resultString);
                resultType = null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getVendorDetails() used to set reporting person for project assign
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date: June 29 2015
     *
     **************************************
     */
    public String saveVendorTierDetails() {
        resultType = LOGIN;
        String resultString = "";
        int i = 0;
        try {
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                resultString = ServiceLocator.getAccountAjaxHandlerService().saveVendorTierDetails(this);
                
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(resultString);
                resultType = null;
            }
        } catch (Exception ex) {
            resultType = ERROR;
        }
        return null;
    }
//    public String getContactEmailId() {
//        resultType = LOGIN;
//        String reponseString = "";
//        try {
//            System.out.println("Ajax Handler action -->getContactEmail");
//           // System.out.println("orgid" + getUserId());
//              //System.out.println("===============>in titles" + repoString);
//             reponseString=dataSourceDataProvider.getInstance().getEmailId(getUserId());
//             //System.out.println("Contact Email id===============>" + reponseString);
//                servletResponse.setContentType("text");
//                servletResponse.setCharacterEncoding("UTF-8");
//                servletResponse.getWriter().write(reponseString);
//
//                //  resultType = SUCCESS;
//           
//        } catch (Exception e) {
//            resultType = ERROR;
//        }
//        return null;
//    }

    public String checkContactExist() {
        resultType = LOGIN;
        String responseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getOrgUserId());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                // userSessionId = Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int userCount = ServiceLocator.getAccountAjaxHandlerService().getUserCount(getOrgUserId());
                if (userCount == 1) {
                    responseString = "User contact is already Registered Please Check Email";
                }
                if (userCount == 0) {
                    responseString = "Do you want to send Login Credentials To Email?";
                }
                //System.out.println("===============>in titles" + repoString);
                
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(responseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @editVendorTierOverlay() used to get vendor tiers details on overlay
     *
     * @Author:Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:07/08/2015
     *
     **************************************
     */
    public String editVendorTierOverlay() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->editVendorTierOverly()");
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getVendorTierOverlayDetails(this);
                System.out.println("===============>in searchResults" + reponseString);
                
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @accountTransferOrCopy() used to account transfer or copy from one CSR to
     * another.
     *
     * @Author:Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:07/16/2015
     *
     **************************************
     */
    public String accountTransferOrCopy() {
        System.out.println("in ");
        String resultType = LOGIN;
        String responseString = "";
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("in accountTransferorcopy");
                setUserSessionId(Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int result = ServiceLocator.getAccountAjaxHandlerService().accountTransferOrCopy(this);
                if (result > 0) {
                    responseString = "success";
                }
                if (result == 0) {
                    responseString = "error";
                }
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(responseString);
            } catch (Exception e) {
                servletRequest.getSession(false).setAttribute("errorMessage:", e.toString());
                System.out.println("Exception" + e);
            }
        }
        return null;
    }

    public String getAttachmentDetails() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getAttachmentDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            System.out.println("orgid" + getViewAccountID());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getViewAccountID());
                System.out.println("typeOfAccount" + typeOfAccount);
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getAttachmentDetails(getViewAccountID(), typeOfAccount);
                System.out.println("===============>in titles" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String getVendorFormEditDetails() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getAttachmentDetails");
            System.out.println("orgid" + getAccountSearchOrgId());
            System.out.println("orgid" + getViewAccountID());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                // typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getViewAccountID());
                System.out.println("getAcc_attachment_id" + getAttachment_id());
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getVendorFormEditDetails(getViewAccountID(), getAttachment_id());
                System.out.println("===============>in titles" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String getAttachmentsSearchDetails() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getViewAccountID());
            if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                reponseString = ServiceLocator.getAccountAjaxHandlerService().getAttachmentsSearchDetails(this, getViewAccountID());
                System.out.println("===============>in searchResults" + reponseString);
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String getTeamMemberReportingPersons() {
        if (servletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                int orgId = Integer.parseInt(servletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                String allRoles = "";
                int userRole = dataSourceDataProvider.getInstance().getUsrRoleById(getUserId());
                String usrRole = String.valueOf(userRole);
                //,7,11,14
                if ("7".equals(usrRole) || "11".equals(usrRole) || "14".equals(usrRole)) {
                    allRoles = "13,4,5,3,6";
                } else {
                    allRoles = Properties.getProperty("REPORTSTOROLES");
                }
                System.out.println("allRoles-->" + allRoles);
                //  String usrRole = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString();
                String finalReportsList = "";
                String allRoleArray[] = allRoles.split(",");

                for (int i = 0; i < allRoleArray.length; i++) {
                    if (allRoleArray[i].equals(usrRole)) {
                        break;
                    } else {
                        finalReportsList += allRoleArray[i] + ",";
                    }

                }
                System.out.println("finalReportsList" + finalReportsList);
                finalReportsList = StringUtils.chop(finalReportsList);
                System.out.println("finalReportsList" + finalReportsList);
                String reportingPersons = dataSourceDataProvider.getInstance().getTeamMemberReportingPersons(getUserId(), finalReportsList, orgId, getProjectID());
                 servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                servletResponse.setHeader("Pragma", "no-cache");
                servletResponse.setDateHeader("Expires", 0);
                servletResponse.setContentType("text");
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.getWriter().write(reportingPersons);

            } catch (Exception e) {
            }
        }

        return null;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public int getAccountSearchOrgId() {
        return accountSearchOrgId;
    }

    public void setAccountSearchOrgId(int accountSearchOrgId) {
        this.accountSearchOrgId = accountSearchOrgId;
    }

    public int getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(int orgUserId) {
        this.orgUserId = orgUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String ContactEmail) {
        this.ContactEmail = ContactEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(String accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public Map getVendorTierMap() {
        return vendorTierMap;
    }

    public void setVendorTierMap(Map vendorTierMap) {
        this.vendorTierMap = vendorTierMap;
    }

    public String getVendorTier() {
        return vendorTier;
    }

    public void setVendorTier(String vendorTier) {
        this.vendorTier = vendorTier;
    }

    public String getVendorTierId() {
        return VendorTierId;
    }

    public void setVendorTierId(String VendorTierId) {
        this.VendorTierId = VendorTierId;
    }

    public String getTierId() {
        return tierId;
    }

    public void setTierId(String tierId) {
        this.tierId = tierId;
    }

    public String getVendorTierStatus() {
        return vendorTierStatus;
    }

    public void setVendorTierStatus(String vendorTierStatus) {
        this.vendorTierStatus = vendorTierStatus;
    }

    public String getVendorTierType() {
        return vendorTierType;
    }

    public void setVendorTierType(String vendorTierType) {
        this.vendorTierType = vendorTierType;
    }

    public String getTierStatus() {
        return TierStatus;
    }

    public void setTierStatus(String TierStatus) {
        this.TierStatus = TierStatus;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public int getDesignationId() {
        return designationId;
    }

    public void setDesignationId(int designationId) {
        this.designationId = designationId;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(String vendorStatus) {
        this.vendorStatus = vendorStatus;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public void setVendorComments(String vendorComments) {
        this.vendorComments = vendorComments;
    }

    public String getCsrName() {
        return csrName;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
    }

    public String getCsrStatus() {
        return csrStatus;
    }

    public void setCsrStatus(String csrStatus) {
        this.csrStatus = csrStatus;
    }

    public String getCsrEmail() {
        return csrEmail;
    }

    public void setCsrEmail(String csrEmail) {
        this.csrEmail = csrEmail;
    }

    public String getCsrphone() {
        return csrphone;
    }

    public void setCsrphone(String csrphone) {
        this.csrphone = csrphone;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCsrId() {
        return csrId;
    }

    public void setCsrId(String csrId) {
        this.csrId = csrId;
    }

    public int getToCSRID() {
        return toCSRID;
    }

    public void setToCSRID(int toCSRID) {
        this.toCSRID = toCSRID;
    }

    public int getFromCSRID() {
        return fromCSRID;
    }

    public void setFromCSRID(int fromCSRID) {
        this.fromCSRID = fromCSRID;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    public String getAccCity() {
        return accCity;
    }

    public void setAccCity(String accCity) {
        this.accCity = accCity;
    }

    public String getAccCountry() {
        return accCountry;
    }

    public void setAccCountry(String accCountry) {
        this.accCountry = accCountry;
    }

    public String getAccState() {
        return accState;
    }

    public void setAccState(String accState) {
        this.accState = accState;
    }

    public String getAccPhone() {
        return accPhone;
    }

    public void setAccPhone(String accPhone) {
        this.accPhone = accPhone;
    }

    public String getLocationFlag() {
        return locationFlag;
    }

    public void setLocationFlag(String locationFlag) {
        this.locationFlag = locationFlag;
    }

    public String getLocationAddress1() {
        return locationAddress1;
    }

    public void setLocationAddress1(String locationAddress1) {
        this.locationAddress1 = locationAddress1;
    }

    public String getLocationAddress2() {
        return locationAddress2;
    }

    public void setLocationAddress2(String locationAddress2) {
        this.locationAddress2 = locationAddress2;
    }

    public String getLocationZip() {
        return locationZip;
    }

    public void setLocationZip(String locationZip) {
        this.locationZip = locationZip;
    }

    public String getLocationFax() {
        return locationFax;
    }

    public void setLocationFax(String locationFax) {
        this.locationFax = locationFax;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    public int getViewAccountID() {
        return viewAccountID;
    }

    public void setViewAccountID(int viewAccountID) {
        this.viewAccountID = viewAccountID;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        try {
            if ((!"".equals(validFrom)) && (!"null".equals(validFrom))) {
                this.validFrom = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(validFrom);
            } else {
                this.validFrom = validFrom;
            }
        } catch (ServiceLocatorException ex) {
            System.out.println("exception " + ex);
        }
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        try {
            if ((!"".equals(validTo)) && (!"null".equals(validTo))) {
                this.validTo = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(validTo);
            } else {
                this.validTo = validTo;
            }
        } catch (ServiceLocatorException ex) {
            System.out.println("exception " + ex);
        }
    }

    public String getVendorDocs() {
        return vendorDocs;
    }

    public void setVendorDocs(String vendorDocs) {
        this.vendorDocs = vendorDocs;
    }

    public String getAcc_attachment_name() {
        return acc_attachment_name;
    }

    public void setAcc_attachment_name(String acc_attachment_name) {
        this.acc_attachment_name = acc_attachment_name;
    }

    public int getAcc_attachment_id() {
        return acc_attachment_id;
    }

    public void setAcc_attachment_id(int acc_attachment_id) {
        this.acc_attachment_id = acc_attachment_id;
    }

    public int getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(int attachment_id) {
        this.attachment_id = attachment_id;
    }

    public String getAttachmentTitle() {
        return attachmentTitle;
    }

    public void setAttachmentTitle(String attachmentTitle) {
        this.attachmentTitle = attachmentTitle;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
