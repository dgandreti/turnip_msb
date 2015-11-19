/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionContext;
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
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ParameterAware;

/**
 *
 * @author NagireddySeerapu
 */
public class AccountAction extends ActionSupport implements ServletRequestAware, ParameterAware {

    private List<String> countryList;
    private List<String> stateList;
    private List<String> industryList;
    private List<String> accountTypeList;
    private List<String> vendorTypeList;
    private String name;
    private String url;
    private String address1;
    private String address2;
    private String state;
    private String country;
    private String city;
    private String zip;
    //private int id;
    private String phone;
    private String fax;
    private String industry;
    private Integer budget;
    private String region;
    private String tax_id;
    private String territory;
    private String stockSymbol;
    private Integer numOfEmployees;
    private String description;
    private Integer revenue;
    private Integer userId;
    private Integer stateId;
    private Integer countryId;
    private Integer industryId;
    private Integer accountTypeId;
    private Integer relatedAccountId = null;
    private Integer vendorTypeId = null;
    private String vendor_type;
    private String account_type;
    private String related_account_name;
    //shanker
    private String ContactFname;
    private String ContactLname;
    private String ContactMname;
    private String conCZip;
    private String Officephone;
    private String OfficeAddress;
    private String status;
    private int AccountSearchOrgId;
    private String ContactEmail;
    private int id;
    private String conAddress;
    private String conAddress2;
    private String conCity;
    private String conZip;
    private int conCountry;
    private int conCCountry;
    private int conState;
    private String conPhone;
    private String conCAddress;
    private String conCAddress2;
    private String conCCity;
    private String conCPhone;
    private int conCState;
    private File picture;
    private String pictureFileName;
    private String pictureFileContentType;
    private String message = "";
    //private String filePath;
    private String addAddressFlag;
    private String currentAddressFlag;
    private File taskAttachment;
    private String taskAttachmentContentType;
    private String taskAttachmentFileName;
    private String filePath;
    private boolean checkAddress;
    private boolean add_checkAddress;
    private int departments;
    private int titles;
    private boolean isManager;
    private boolean isTeamLead;
    private int addManager;
    private int addTeamLead;
    private String typeOfAccount;
    private DataSourceDataProvider dataSourceDataProvider;
    private int userSessionId;
    private String ContactAEmail;
    private int contactId;
    private int accountSearchID;
    private String flag;
    private File imageupdate;
    private String imageupdateContentType;
    private String imageupdateFileName;
    private Map employeeMaps;
    private String workLocation;
    private int accountType;
    private int projectID;
    private int reportsto1;
    private int reportsto2;
    private int accountID;
    private int designation;
    private int teamMemberId;
    private String projectName;
    private ProjectTeamDetailsVTO projectTeamDetailsVTO;
    private Map designations;
    private Map countryNames;
    private String accountName;
    private Map vendorTierMap;
    private String gender;
    private Map reportsTOMap;
    // Add By Aklakh
    private String contactTitle;
    private int contactIndustry;
    private int contactExperience;
    private String contactSsnNo;
    private String contactEducation;
     private String[] skillCategoryValueList;
     private String contactSkillValues;
     private Map experience;
     private Map industryMap;
     private Map skillMap;
     private String skillSet;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
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
    private String successMessage;
    private int sessionOrgId;
    // Add By Aklakh
    private Map subProject;
    private Map assignedSubProject;
    private List subProjectList;
    private Account account;
    private Map parameters;
    private String resultType;
    private String assignedProject;
    private int userID;
    // private String projectName;
    private String projectEmpName;
    private List assignProjectForTeam;
    private String projectFlag;
    //private int contactDesignation;
    private String contactDesignation;
    private Map contactPersons;
    // Add by akalkh
    private String email_ext;
    private String resourceType;
    private String consSkills;
    private String rateSalary;
    private String teamMemberFlag;
    private String validateMessage;
    private Map workLocations;
    private int workingLocation;

    /**
     * Method : Account add action
     *
     */
    public String getAddAccount() {
        String resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            //Add Account true is added successfully
            //Add Account true is added successfully
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

            if (this.checkForNoNullValues()) {
                if (false) {
                    resultMessage = "";
                    successMessage = "Successfully Added your Account: name: " + getName();
                    resultType = SUCCESS;
                } else {
                    resultType = ERROR;
                    resultMessage = "Failed to Add your Account";
                }
            } else {
                resultType = SUCCESS;
            }
            createDropDowns();
        }// Session validator if END

        return resultType;
    }

    public String execute() {
        String resultType = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            createDropDowns();
            resultType = SUCCESS;
        }
        return resultType;
    }

    public String addContact() {

        String resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("checkAddress true or false------>" + getAdd_checkAddress());
            System.out.println("email------------>" + getContactEmail());
            setContactEmail(getContactEmail() + '@' + getEmail_ext());
            System.out.println("email------------->" + getContactEmail());
            String trmEmail = getContactEmail();
            setContactEmail(trmEmail.trim());
            System.out.println("trimEmail--->" + trmEmail.trim() + "..");
            System.out.println(" trim email------------->" + getContactEmail() + "hello");
            if (getAdd_checkAddress()) {
                addAddressFlag = "PC";
                System.out.println("checkAddress" + addAddressFlag);
            } else {
                addAddressFlag = "P";
                System.out.println("checkAddress" + addAddressFlag);

            }
            if (isIsManager()) {
                addManager = 1;
                System.out.println("Is Manager" + addManager);
            } else {
                addManager = 0;
                System.out.println("Is Manager" + addManager);
            }
            if (isIsTeamLead()) {
                addTeamLead = 1;
                System.out.println("Is TeamLead" + addTeamLead);
            } else {
                addTeamLead = 0;
                System.out.println("Is TeamLead" + addTeamLead);
            }

            System.out.println("checkAddress" + isCheckAddress());

            //filePath = "D:\\uploadedfiles";
            int addresult = 0;
            //filePath="C:\\usr\\ProjectFiles\\MSP\\TASK_ATTACHMENTS";
            filePath = Properties.getProperty("Profile.Image");

            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% File Data Start %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println("File Name is:" + taskAttachment);
            System.out.println("File ContentType is:" + taskAttachmentContentType);
            System.out.println("Files Directory is:" + filePath);
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% File Data End %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            try {
                File destFile = new File(filePath + File.separator + taskAttachmentFileName);
                System.out.println(">>>>>>>>>>>>>>>>>>PATH>>>>>>>>>>>>" + destFile);
                //FileUtils.copyFile(taskAttachment, destFile);
                String dfile = destFile.toString();
                if (taskAttachment == null) {
                    System.out.println("In if taskAttachment>>>1111" + taskAttachment);
                    setFilePath("");
                } else {
                    System.out.println("In else taskAttachment>>>1111" + taskAttachment);
                    setFilePath(dfile);
                }
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getAccountSearchOrgId());
                String result = ServiceLocator.getAccountService().addAccountContactDetails(this);
                if (result.equalsIgnoreCase("Added")) {
                    if (typeOfAccount.equalsIgnoreCase("AC")) {
                        dataSourceDataProvider.getInstance().updateAccountLastAccessedBy(getAccountSearchOrgId(), getUserSessionId(), "ContactAdded");
                    } else {
                        dataSourceDataProvider.getInstance().updateAccountLastAccessedBy(getAccountSearchOrgId(), getUserSessionId(), "VendorContactAdded");
                    }
                }
            } catch (Exception e) {
            }
            resultType = SUCCESS;
        }
        return resultType;
    }
    //added by praveen

    public String ProfileImageUpdate() {
        String resultType = LOGIN;
        //filePath = "D:\\uploadedfiles";
        int addresult = 0;
        //filePath="C:\\usr\\ProjectFiles\\MSP\\TASK_ATTACHMENTS";
        //filePath = Properties.getProperty("Profile.Image");
        HttpSession session = getHttpServletRequest().getSession(true);

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {

                //setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                //setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap());
                if (getImageupdateFileName() == null) {
                    System.out.println("file is null so it adds only data in task_list table");
                } else {
                    filePath = Properties.getProperty("Profile.Image");

                    String basename = FilenameUtils.getBaseName(imageupdateFileName);
                    String extension = FilenameUtils.getExtension(imageupdateFileName);
                    System.out.println("Base name " + basename);
                    System.out.println("extension name " + extension);
                    System.out.println("the path is--------" + getFilePath());
                    File destFile = new File(filePath + File.separator + getContactId() + '.' + extension);
                    setFilePath(destFile.toString());
                    System.out.println("the path is--------" + getFilePath());
                    FileUtils.copyFile(imageupdate, destFile);

                }
//                File destFile = new File(filePath, taskAttachmentFileName);
//                FileUtils.copyFile(taskAttachment, destFile);

                //System.out.println("file is null so it adds only data in task_list table");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                //  System.out.println("Action called in TaskHandlerAction classsssssssssssssssssssssssssssssssssssssssss");
                //  addresult = ServiceLocator.getRecruitmentService().addConsultAttachmentDetails(this, httpServletRequest);
                ServiceLocator.getAccountService().updateImageForProfile(this, httpServletRequest);
                if (getUserSessionId() == getContactId()) {
                    session.setAttribute(ApplicationConstants.USER_IMAGE_PATH, getFilePath());
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception ex) {
            System.out.println("Exception in ADD task  action-->" + ex.getMessage());
            resultType = ERROR;
        }

        return SUCCESS;
    }

    public String setTeamMembersForProject() {
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setContactPersons(com.mss.msp.util.DataSourceDataProvider.getInstance().getContactPersonsByProjectIdHeigerDesignationId(getProjectID(), getDesignation(), getUserID()));
                setReportsTOMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getReporingByProjectId(getProjectID()));
                // setAccount(ServiceLocator.getAccountService().getSubProjectDetails(httpServletRequest, this));
                setSubProject(com.mss.msp.util.DataSourceDataProvider.getInstance().getSubProject(getProjectID(), getUserID()));
                setAssignedSubProject(com.mss.msp.util.DataSourceDataProvider.getInstance().getAssignedSubProject(getProjectID(), getUserID()));
                setAccountID(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setAccount(ServiceLocator.getAccountService().getProjectTeamDetails(httpServletRequest, this));
                System.out.println("After assigned Project");
                System.out.println("the map is ---------------" + getReportsTOMap());
                System.out.println("project d id--------------" + getProjectID());
            }
        } catch (Exception e) {
            return LOGIN;
        }

        return SUCCESS;
    }

    public String addTeamMemberToProject() throws ServiceLocatorException {
        String resulttype = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//            setTeamMemberId(123);
            //  System.out.println("Teamemberid in action claas---->" + getTeamMemberId());
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setAccountID(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            String result = ServiceLocator.getAccountService().addTeamMemberToProject(this, httpServletRequest);
            // setDesignationMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getDesignation());

            if (result.equalsIgnoreCase("Insert")) {
                setProjectFlag("addMember");
                setResultMessage("Team Member Added Succesfully..");
            } else {
                setUserID(getTeamMemberId());
                setResultMessage("Team Member Updated Succesfully..");

            }
            resulttype = SUCCESS;
        }
        return resulttype;
    }

    private void createDropDowns() {
        populateCountryList();
        populateIndustryList();
        populateAccountTypeList();
        populateVendorTypeList();
        if (this.country != null && !this.country.equals("")) {
            setStateList(ServiceLocator.getLocationService().getStatesNameByCountry(getCountry()));
        } else {
            System.out.println("no Country in add Account: " + this.country);
            setStateList(new ArrayList<String>());
        }
    }

    /**
     * *****************************************************************************
     * Date : June 15 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * doAddConsultantDetails() method is used to enter the consultant record
     * into database using stored procedure
     *
     * *****************************************************************************
     */
    public String updateTeamMembersForProject() {
        resultType = LOGIN;
        System.out.println("-------IN  updateTeamMembersForProject Action Class-------");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                System.out.println("INSIDE IF");
                System.out.println(" >>>>>>> Project Id>>" + getProjectID() + " User id--------->" + getUserID());
                System.out.println("Assigned Project--->" + getAssignedProject());
                String[] assignedTeam = (String[]) parameters.get("assignProjectForTeam");
                System.out.println(">>>>>>>>>>>>>>>>>Length>>>>>>" + assignedTeam.length);
                if (assignedTeam.length > 5) {
                    System.out.println("More than 5 projects can not be accepted!");
                validateMessage = "More than 5 projects can not be accepted!";
                    setTeamMemberFlag("assignTeam");
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, validateMessage);
                    resultType = SUCCESS;
                } else {
                    setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                    int updateResult = ServiceLocator.getAccountService().updateAssignTeam(this, assignedTeam);
                    setAccountID(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                    setTeamMemberFlag("assignTeam");
                    if (updateResult > 0) {
                        validateMessage = "Projects has been successfully updated!";
                    } else {
                        validateMessage = "No Records Updated!";
                    }
                    httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, validateMessage);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception e) {
            return LOGIN;
        }

        return SUCCESS;
    }

    public String setContacts() {
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                // setDesignations(dataSourceDataProvider.getInstance().getDesignation());
                setCountryNames(dataSourceDataProvider.getInstance().getCountryNames());
                setAccountName(DataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                setEmail_ext(DataSourceDataProvider.getInstance().getUrlExtension(getAccountSearchID()));
                setWorkLocations(DataSourceDataProvider.getInstance().getWorkLocations(getAccountSearchID()));
                setIndustryMap(ServiceLocator.getLookUpHandlerService().getIndustryTypesMap());
                setExperience(DataSourceDataProvider.getInstance().getYearsOfExp());
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillMap(skillsmap);
               // setSkillMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
            }
        } catch (Exception e) {
            return LOGIN;
        }

        return SUCCESS;
    }

    /**
     * *************************************
     *
     * @addVendorForCustomer() method is used to get Requirement details of
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String addVendorForCustomer() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("IM IN addVendorForCustomer() IN ACCOUNT ACTION   " + getAccountSearchID());
                setAccountName(com.mss.msp.util.DataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                setVendorTierMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getVendorTierTypes());
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    private void populateCountryList() {
        countryList = ServiceLocator.getLocationService().getCountriesNames();
    }

    private void populateIndustryList() {
        this.industryList = ServiceLocator.getLookUpHandlerService().getIndustryTypeNames();
    }

    private void populateAccountTypeList() {
        this.accountTypeList = ServiceLocator.getLookUpHandlerService().getAccountTypeNames();
    }

    private void populateVendorTypeList() {
        this.vendorTypeList = ServiceLocator.getLookUpHandlerService().getVendorTypes();
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getServletRequest() {
        return this.httpServletRequest;
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

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getIndustry() {
        return industry;
    }

    public Integer getBudget() {
        return budget;
    }

    public String getRegion() {
        return region;
    }

    public String getTax_id() {

        return tax_id;
    }

    public String getTerritory() {
        return territory;
    }

    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public AccountAction() {
    }

    public int getUserId() {
        userId = Integer.parseInt(httpServletRequest.getSession(false).
                getAttribute(ApplicationConstants.USER_ID).toString());
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getStateId() {
        String statename = this.getState();
        if (statename.length() > 0 && statename.charAt(0) == '"') {
            statename = statename.substring(1, statename.length());
        }
        if (statename.length() > 0 && statename.charAt(statename.length() - 1) == '"') {
            statename = statename.substring(0, statename.length() - 2);
        }
        try {
            stateId = ServiceLocator.getLookUpHandlerService()
                    .getId("lk_states", "name", statename);
        } catch (ServiceLocatorException ex) {
            System.out.println("error getting state id for state: " + this.getState());
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("State id: " + stateId.intValue() + " for state: " + statename);
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public int getCountryId() {
        try {
            if (this.country != null) {
                this.countryId = ServiceLocator.getLookUpHandlerService()
                        .getId("lk_country", "country", this.country);
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    //TODO
    public int getIndustryId() {
        try {
            if (this.industry != null) {
                this.industryId = ServiceLocator.getLookUpHandlerService()
                        .getId("lk_acc_industry_type", "acc_industry_name", this.getIndustry());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
            this.industryId = -1;
        }
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public List<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public List<String> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<String> industyList) {
        this.industryList = industyList;
    }

    public List<String> getAccountTypeList() {
        return accountTypeList;
    }

    public void setAccountTypeList(List<String> accountTypeList) {
        this.accountTypeList = accountTypeList;
    }

    public List<String> getVendorTypeList() {
        return vendorTypeList;
    }

    public void setVendorTypeList(List<String> vendorTypeList) {
        this.vendorTypeList = vendorTypeList;
    }

    public int getAccountTypeId() {

        try {
            if (this.account_type != null) {
                this.accountTypeId = ServiceLocator.getLookUpHandlerService()
                        .getId("lk_acc_type", "acc_type_name", this.getAccount_type());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(Integer relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }

    public Integer getVendorTypeId() {
        try {
            if (this.vendor_type != null) {
                this.vendorTypeId = ServiceLocator.getLookUpHandlerService()
                        .getId("lk_vendor_type", "vendor_type", this.getVendor_type());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(AccountAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendorTypeId;
    }

    public void setVendorTypeId(Integer vendorTypeId) {
        this.vendorTypeId = vendorTypeId;
    }

    public String getVendor_type() {
        return vendor_type;
    }

    public void setVendor_type(String vendor_type) {
        this.vendor_type = vendor_type;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getRelated_account_name() {
        return related_account_name;
    }

    public void setRelated_account_name(String related_account_name) {
        this.related_account_name = related_account_name;
    }

    /**
     *
     * @return true if there are no null values in the AccountAction object.
     */
    private boolean checkForNoNullValues() {
        boolean noNullValues = true;
        if (this.name == null || this.name.equals("")) {
            noNullValues = false;
            resultMessage = "Please enter and Account Name";
        }
        if (this.url == null || this.url.equals("")) {
            noNullValues = false;
            resultMessage = "Please enter an Account Url";
        }
        if (this.email_ext == null || this.email_ext.equals("")) {
            noNullValues = false;
            resultMessage = "Please enter an MailExtention";
        }
        /* if (this.address1 == null || this.address1.equals("")) {
         noNullValues = false;
         resultMessage = "Please enter an Address 1";
         }
         if (this.address2 == null || this.address2.equals("")) {
         this.address2 = null;
         }
         if (this.state == null || this.state.equals("")) {
         noNullValues = false;
         resultMessage = "Please Enter a state";
         }
         if (this.country == null || this.country.equals("")) {
         noNullValues = false;
         resultMessage = "Please Enter a country";
         } else {
         getCountryId();
         if (this.countryId == null || this.countryId < 0) {
         noNullValues = false;

         resultMessage = "Please select a country(id)";
         } else {
         this.getStateId();
         if ((stateId == null || stateId.intValue() < 0)) {
         noNullValues = false;
         resultMessage = "Please select a state(id)";
         }
         }
         }
         if (this.zip == null || this.zip.equals("")) {
         noNullValues = false;
         resultMessage = "Please Enter a zip";
         }
         if (this.phone == null || this.phone.equals("")) {
         noNullValues = false;
         resultMessage = "Please Enter a phone number";
         }
         if (this.fax == null || this.fax.equals("")) {
         this.fax = null;
         }
         if (this.industry == null || this.industry.equals("")) {
         noNullValues = false;
         resultMessage = "Please select an industry type";
         } else {
         getIndustryId();
         if (this.industryId == null || this.industryId.intValue() < 0) {
         noNullValues = false;
         resultMessage = "Please select an industry(id)";
         }
         }

         if (this.region == null || this.region.equals("")) {
         this.region = null;
         }
         if (this.territory == null || this.territory.equals("")) {
         this.territory = null;
         }
         if (this.stockSymbol == null || this.stockSymbol.equals("")) {
         noNullValues = false;
         resultMessage = "Please Enter a stockSymbol";
         }
         if (this.numOfEmployees == null || this.numOfEmployees.intValue() < 0) {
         noNullValues = false;

         resultMessage = "Please Enter the number of employees";
         }
         if (this.description == null || this.description.equals("")) {
         noNullValues = false;
         resultMessage = "Please Enter a description";
         }
         */
        this.getUserId();
        if (this.userId == null || this.userId.intValue() < 0) {

            noNullValues = false;
            resultMessage = "You need to be logged in to Add an Account.";
        }

        if (this.account_type == null || this.account_type.equals("")) {
            noNullValues = false;

            resultMessage = "";
        } else if (this.account_type.equals("Vendor")) {
            if (this.vendor_type == null || this.vendor_type.equals("")) {
                noNullValues = false;
                resultMessage = "Please select a vendor type";
            } else {
                this.getVendorTypeId();
                if (this.vendorTypeId == null || this.vendorTypeId < 0) {

                    noNullValues = false;
                    resultMessage = "Please select a vendor type(id)";
                }
            }
        } else {
            this.getAccountTypeId();
            if (this.accountTypeId == null || this.accountTypeId.intValue() < 0) {
                noNullValues = false;

                resultMessage = "";
            }
        }


        return noNullValues;
    }

    public String getContactFname() {
        return ContactFname;
    }

    public void setContactFname(String ContactFname) {
        this.ContactFname = ContactFname;
    }

    public String getContactLname() {
        return ContactLname;
    }

    public void setContactLname(String ContactLname) {
        this.ContactLname = ContactLname;
    }

    /**
     * @return the successMessage
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    public String getContactMname() {
        return ContactMname;
    }

    /**
     * @param successMessage the successMessage to set
     */
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setContactMname(String ContactMname) {
        this.ContactMname = ContactMname;
    }

    public String getConCZip() {
        return conCZip;
    }

    public void setConCZip(String conCZip) {
        this.conCZip = conCZip;
    }

    public String getOfficephone() {
        return Officephone;
    }

    public void setOfficephone(String Officephone) {
        this.Officephone = Officephone;
    }

    public String getOfficeAddress() {
        return OfficeAddress;
    }

    public void setOfficeAddress(String OfficeAddress) {
        this.OfficeAddress = OfficeAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccountSearchOrgId() {
        return AccountSearchOrgId;
    }

    public void setAccountSearchOrgId(int AccountSearchOrgId) {
        this.AccountSearchOrgId = AccountSearchOrgId;
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

    public String getConAddress() {
        return conAddress;
    }

    public void setConAddress(String conAddress) {
        this.conAddress = conAddress;
    }

    public String getConAddress2() {
        return conAddress2;
    }

    public void setConAddress2(String conAddress2) {
        this.conAddress2 = conAddress2;
    }

    public String getConCity() {
        return conCity;
    }

    public void setConCity(String conCity) {
        this.conCity = conCity;
    }

    public String getConZip() {
        return conZip;
    }

    public void setConZip(String conZip) {
        this.conZip = conZip;
    }

    public int getConCountry() {
        return conCountry;
    }

    public void setConCountry(int conCountry) {
        this.conCountry = conCountry;
    }

    public int getConCCountry() {
        return conCCountry;
    }

    public void setConCCountry(int conCCountry) {
        this.conCCountry = conCCountry;
    }

    public int getConState() {
        return conState;
    }

    public void setConState(int conState) {
        this.conState = conState;
    }

    public String getConPhone() {
        return conPhone;
    }

    public void setConPhone(String conPhone) {
        this.conPhone = conPhone;
    }

    public String getConCAddress() {
        return conCAddress;
    }

    public void setConCAddress(String conCAddress) {
        this.conCAddress = conCAddress;
    }

    public String getConCAddress2() {
        return conCAddress2;
    }

    public void setConCAddress2(String conCAddress2) {
        this.conCAddress2 = conCAddress2;
    }

    public String getConCCity() {
        return conCCity;
    }

    public void setConCCity(String conCCity) {
        this.conCCity = conCCity;
    }

    public String getConCPhone() {
        return conCPhone;
    }

    public void setConCPhone(String conCPhone) {
        this.conCPhone = conCPhone;
    }

    public int getConCState() {
        return conCState;
    }

    public void setConCState(int conCState) {
        this.conCState = conCState;
    }

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureFileContentType() {
        return pictureFileContentType;
    }

    public void setPictureFileContentType(String pictureFileContentType) {
        this.pictureFileContentType = pictureFileContentType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddAddressFlag() {
        return addAddressFlag;
    }

    public void setAddAddressFlag(String addAddressFlag) {
        this.addAddressFlag = addAddressFlag;
    }

    public String getCurrentAddressFlag() {
        return currentAddressFlag;
    }

    public void setCurrentAddressFlag(String currentAddressFlag) {
        this.currentAddressFlag = currentAddressFlag;
    }

    public File getTaskAttachment() {
        return taskAttachment;
    }

    public void setTaskAttachment(File taskAttachment) {
        this.taskAttachment = taskAttachment;
    }

    public String getTaskAttachmentContentType() {
        return taskAttachmentContentType;
    }

    public void setTaskAttachmentContentType(String taskAttachmentContentType) {
        this.taskAttachmentContentType = taskAttachmentContentType;
    }

    public String getTaskAttachmentFileName() {
        return taskAttachmentFileName;
    }

    public void setTaskAttachmentFileName(String taskAttachmentFileName) {
        this.taskAttachmentFileName = taskAttachmentFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isCheckAddress() {
        return checkAddress;
    }

    public void setCheckAddress(boolean checkAddress) {
        this.checkAddress = checkAddress;
    }

    public boolean getAdd_checkAddress() {
        return add_checkAddress;
    }

    public void setAdd_checkAddress(boolean add_checkAddress) {
        this.add_checkAddress = add_checkAddress;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public List<String> getStateList() {
        return stateList;
    }

    public int getDepartments() {
        return departments;
    }

    public void setStateList(List<String> stateList) {
        this.stateList = stateList;
    }

    public void setDepartments(int departments) {
        this.departments = departments;
    }

    public int getTitles() {
        return titles;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isIsTeamLead() {
        return isTeamLead;
    }

    public void setIsTeamLead(boolean isTeamLead) {
        this.isTeamLead = isTeamLead;
    }

    public int getAddManager() {
        return addManager;
    }

    public void setAddManager(int addManager) {
        this.addManager = addManager;
    }

    public int getAddTeamLead() {
        return addTeamLead;
    }

    public void setAddTeamLead(int addTeamLead) {
        this.addTeamLead = addTeamLead;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getContactAEmail() {
        return ContactAEmail;
    }

    public void setContactAEmail(String ContactAEmail) {
        this.ContactAEmail = ContactAEmail;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(int accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public File getImageupdate() {
        return imageupdate;
    }

    public void setImageupdate(File imageupdate) {
        this.imageupdate = imageupdate;
    }

    public String getImageupdateContentType() {
        return imageupdateContentType;
    }

    public void setImageupdateContentType(String imageupdateContentType) {
        this.imageupdateContentType = imageupdateContentType;
    }

    public String getImageupdateFileName() {
        return imageupdateFileName;
    }

    public void setImageupdateFileName(String imageupdateFileName) {
        this.imageupdateFileName = imageupdateFileName;
    }

    public Map getEmployeeMaps() {
        return employeeMaps;
    }

    public void setEmployeeMaps(Map employeeMaps) {
        this.employeeMaps = employeeMaps;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getReportsto1() {
        return reportsto1;
    }

    public void setReportsto1(int reportsto1) {
        this.reportsto1 = reportsto1;
    }

    public int getReportsto2() {
        return reportsto2;
    }

    public void setReportsto2(int reportsto2) {
        this.reportsto2 = reportsto2;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getDesignation() {
        return designation;
    }

    public void setDesignation(int designation) {
        this.designation = designation;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    private String teamMemberStatus;

    public String getTeamMemberStatus() {
        return teamMemberStatus;
    }

    public void setTeamMemberStatus(String teamMemberStatus) {
        this.teamMemberStatus = teamMemberStatus;
    }

    public int getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(int teamMemberId) {
        this.teamMemberId = teamMemberId;


    }
    private String teamMemberIdname;

    public String getTeamMemberIdname() {
        return teamMemberIdname;
    }

    public void setTeamMemberIdname(String teamMemberIdname) {
        this.teamMemberIdname = teamMemberIdname;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    private String flagForTeamAdding;

    public String getFlagForTeamAdding() {
        return flagForTeamAdding;
    }

    public void setFlagForTeamAdding(String flagForTeamAdding) {
        this.flagForTeamAdding = flagForTeamAdding;
    }

    public ProjectTeamDetailsVTO getProjectTeamDetailsVTO() {
        return projectTeamDetailsVTO;
    }

    public void setProjectTeamDetailsVTO(ProjectTeamDetailsVTO projectTeamDetailsVTO) {
        this.projectTeamDetailsVTO = projectTeamDetailsVTO;
    }

    public Map getSubProject() {
        return subProject;
    }

    public void setSubProject(Map subProject) {
        this.subProject = subProject;
    }

    public Map getAssignedSubProject() {
        return assignedSubProject;
    }

    public void setAssignedSubProject(Map assignedSubProject) {
        this.assignedSubProject = assignedSubProject;
    }

    public List getSubProjectList() {
        return subProjectList;
    }

    public void setSubProjectList(List subProjectList) {
        this.subProjectList = subProjectList;
    }

    public Map getParameters() {
        return parameters;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(String assignedProject) {
        this.assignedProject = assignedProject;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getProjectEmpName() {
        return projectEmpName;
    }

    public void setProjectEmpName(String projectEmpName) {
        this.projectEmpName = projectEmpName;
    }

    public List getAssignProjectForTeam() {
        return assignProjectForTeam;
    }

    public void setAssignProjectForTeam(List assignProjectForTeam) {
        this.assignProjectForTeam = assignProjectForTeam;
    }

    public String getProjectFlag() {
        return projectFlag;
    }

    public void setProjectFlag(String projectFlag) {
        this.projectFlag = projectFlag;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    private Map designationMap;

    public Map getDesignationMap() {
        return designationMap;
    }

    public void setDesignationMap(Map designationMap) {
        this.designationMap = designationMap;
    }

    public String getContactDesignation() {
        return contactDesignation;
    }

    public void setContactDesignation(String contactDesignation) {
        this.contactDesignation = contactDesignation;
    }

    public Map getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(Map contactPersons) {
        this.contactPersons = contactPersons;
    }

    public Map getDesignations() {
        return designations;
    }

    public void setDesignations(Map designations) {
        this.designations = designations;
    }

    public Map getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(Map countryNames) {
        this.countryNames = countryNames;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Map getVendorTierMap() {
        return vendorTierMap;
    }

    public void setVendorTierMap(Map vendorTierMap) {
        this.vendorTierMap = vendorTierMap;
    }

    public String getEmail_ext() {
        return email_ext;
    }

    public void setEmail_ext(String email_ext) {
        this.email_ext = email_ext;
    }

    public Map getReportsTOMap() {
        return reportsTOMap;
    }

    public void setReportsTOMap(Map reportsTOMap) {
        this.reportsTOMap = reportsTOMap;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getConsSkills() {
        return consSkills;
    }

    public void setConsSkills(String consSkills) {
        this.consSkills = consSkills;
    }

    public String getRateSalary() {
        return rateSalary;
    }

    public void setRateSalary(String rateSalary) {
        this.rateSalary = rateSalary;
    }

    public String getTeamMemberFlag() {
        return teamMemberFlag;
    }

    public void setTeamMemberFlag(String teamMemberFlag) {
        this.teamMemberFlag = teamMemberFlag;
    }

    public String getValidateMessage() {
        return validateMessage;
    }

    public void setValidateMessage(String validateMessage) {
        this.validateMessage = validateMessage;
    }

    public Map getWorkLocations() {
        return workLocations;
    }

    public void setWorkLocations(Map workLocations) {
        this.workLocations = workLocations;
    }

    public int getWorkingLocation() {
        return workingLocation;
    }

    public void setWorkingLocation(int workingLocation) {
        this.workingLocation = workingLocation;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public int getContactIndustry() {
        return contactIndustry;
    }

    public void setContactIndustry(int contactIndustry) {
        this.contactIndustry = contactIndustry;
    }

    public int getContactExperience() {
        return contactExperience;
    }

    public void setContactExperience(int contactExperience) {
        this.contactExperience = contactExperience;
    }

    public String getContactSsnNo() {
        return contactSsnNo;
    }

    public void setContactSsnNo(String contactSsnNo) {
        this.contactSsnNo = contactSsnNo;
    }

    public String getContactEducation() {
        return contactEducation;
    }

    public void setContactEducation(String contactEducation) {
        this.contactEducation = contactEducation;
    }

    public String[] getSkillCategoryValueList() {
        return skillCategoryValueList;
    }

    public void setSkillCategoryValueList(String[] skillCategoryValueList) {
        this.skillCategoryValueList = skillCategoryValueList;
    }

    public String getContactSkillValues() {
        return contactSkillValues;
    }

    public void setContactSkillValues(String contactSkillValues) {
        this.contactSkillValues = contactSkillValues;
    }

    public Map getIndustryMap() {
        return industryMap;
    }

    public void setIndustryMap(Map industryMap) {
        this.industryMap = industryMap;
    }

    public Map getSkillMap() {
        return skillMap;
    }

    public void setSkillMap(Map skillMap) {
        this.skillMap = skillMap;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public Map getExperience() {
        return experience;
    }

    public void setExperience(Map experience) {
        this.experience = experience;
    }
    
    
}