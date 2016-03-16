/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.recruitment;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author NagireddySeerapu
 */
public class RecruitmentAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String requirementStatus;
    private String resultMessage;
    private List ConsultantListDetails;
    private String resultType;
    private String vendorDocs;
    private String flag;
    //for requirement retrieval//
    private int userSessionId;
    private String requirementskDetails;
    private int accountSearchID;
    //for requirement retrieval//
    // variable created by triveni
    private int consult_id;
    private String consult_name;
    private String consult_email;
    private String consult_skill;
    private String consult_phno;
    //  private List ConsultantListDetails;
    private ConsultantVTO consultantVTO;
    private String consult_favail;
    private String consult_fstname;
    //private String consult_gender;
    private String consult_homePhone;
    private String consult_lstname;
    private String consult_dob;
    private String consult_mobileNo;
    private String consult_available;
    private String consult_midname;
    // private String consult_mStatus;
    private int consult_lcountry;
    private String consult_alterEmail;
    private String consult_ssnNo;
    private String consult_Address;
    private String consult_Address2;
    private String consult_City;
    private String consult_Country;
    private int consult_State;
    private String consult_Zip;
    private String consult_Phone;
    private String address_flag;
    private String consult_CAddress;
    private String consult_CAddress2;
    private String consult_CCity;
    private String consult_CCountry;
    private int consult_CState;
    private String consult_CZip;
    private String consult_CPhone;
    private String consult_education;
    private int consult_industry;
    private String consult_salary;
    private int consult_wcountry;
    private int consult_organization;
    private int consult_experience;
    private int consult_preferredState;
    private String consult_jobTitle;
    private String consult_workPhone;
    private String consult_referredBy;
    private String consult_comments;
    private String consult_status;
    private String consult_preferredCountry;
    private int modified_by;
    private Map country;
    private Map experience;
    private Map state;
    private String consult_checkAddress;
    private int requirementId;
    private String filePath;
    private File consultAttachment;
    private String consultAttachmentContentType;
    private String consultAttachmentFileName;
    /* Consultant Activity Start*/
    private int activityId;
    private String activityType;
    private String activityPriority;
    private String activityName;
    private String activityStatus;
    private String activityComments;
    private String activityDesc;
    private int activityCratedBy;
    private int orgid;
    /*
     *acr  ---- requirement
     *acc  ---- consultant
     *ac   ---- account
     */
    private String activityRelation;
    private List consultantActivityDetails;
    private String accountFlag;
    //for tech review
    private String interview;
    private String interviewType;
    private int empIdTechReview;
    private String interviewDate;
    private String reviewAlertDate;
    private String alertMessageTechReview;
    //for vendor view
    private String vendor;
    private Map permanentState;
    private Map currentState;
    private Map preState;
    //for tech review
    private String timeZone;
    private int empIdTechReview2;
    private String interviewLocation;
    private String empEmail2;
    private String resumeDownlaod;
    private String techReviewFlag;
    private Map reqCreatedByMap;
    private String AccountSearchOrgId;
    private int viewAccountID;
    private String editValidity;
    private String contechNote;
    private String consultantVisa;
    private File visaAttachment;
    private String visaAttachmentContentType;
    private String visaAttachmentFileName;
    private String visaAttachmentPath;
    private String consultantIdProof;

    /* Consultant Activity End*/
    public RecruitmentAction() {
    }
    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    // Map created for add consultant  by Aklakh
    private Map consult_WCountry;
    private Map organization;
    private Map industry;
    private List requirementlistVTO;
    private int sessionOrgId;
    private String consultantFlag;
    private Map teamMembersList;
    private String teamMembers;
    private DataSourceDataProvider dataSourceDataProvider;
    //Add by Aklakh
    private String consultantId;
    private String consult_add_date;
    /* 
     *
     private String cnslt_email;
     private String cnslt_fstname;
     private String cnslt_gender;
     private String cnslt_homePhone;
    
     private String cnslt_lstname;
     private String cnslt_dob;
     private String cnslt_mobileNo;
     private String cnslt_available;
     private String cnslt_midname;
     private String cnslt_mStatus;
     private int cnslt_lcountry;
     private String addAddressFlag;
     private String addConsult_Address;
     private String addConsult_Address2;
     private String addConsult_City;
     private String addConsult_Country;
     private int addConsult_State;
     private String addConsult_Zip;
     private String addConsult_Phone;
     private String currentAddressFlag;
     private String addConsult_CAddress;
     private String addConsult_CAddress2;
     private String addConsult_CCity;
     private String addConsult_CCountry;
     private int addConsult_CState;
     private String addConsult_CZip;
     private String addConsult_CPhone;
     private int cnslt_industry;
     private String cnslt_salary;
     private int cnslt_wcountry;
     private int cnslt_organization;
     private String cnslt_experience;
     private int cnslt_preferredState;
     private String cnslt_jobTitle;
     private String cnslt_workPhone;
     private String cnslt_referredBy;
     private String cnslt_description;
     private String cnslt_comments;
     */
    private List consultantListVTO;
    private int consult_pcountry;
    // private String consult_skills;    
    private String consult_position;
    // private int cnslt_pcountry;
    private String addconsult_checkAddress;
    // private String cnslt_skills;
    private File file;
    private String fileContentType;
    private String fileFileName;
    private String filesPath;
    private List consultantList;
    //for tech review
    //for tech review
    private String reviewStartDate;
    private String techSkill;
    private String domainSkill;
    private String comSkill;
    private String rating;
    private String consultantComments;
    private String finalTechReviewStatus;
    private String techTitle;
    private String reviewEndDate;
    private String consultId;
    private String techReviewStatus;
    private String consultFlag = null;
    private int acc_attachment_id;
    private String account_name;
    //for mailing
    private String conEmail;
    private String empEmail;
    private String mailIds;
    private String conSkills;
    private String reviewDate;
    private String reviewTime;
    private String forwardedByName;
    private String reqName;
    private String forwardedToName;
    private MailManager mailManager = new MailManager();
    private String customerFlag;
    private String updateMessage;
    private String techSearch;
    private String downloadFlag;
    // Add By Aklakh
    private String jdId;
    private String accountName;
    private String migrationStatus;
    private int req_Id;
    private String migrationEmailId;
    private int contechId;
    private String jobTitle;
    private String targetRate;
    private String maxRate;
    private String resultFlag;
    private int ven_id;
    private String userType;
    private int attachment_id_edit;
    private String validity;
    private String typeOfAccount;
    // Add by Aklakh for social media account
    private String consult_facebookId;
    private String consult_twitterId;
    private String consult_linkedInId;
    private String attachmentTitle;
    private String attachmentComments;
    private String editTitle;
    private String editattachmentComments;
    private Map skillValuesMap;
    private String skillCategoryValueString;
    private String[] skillCategoryValueList;
    private String skillValues;
//praven  
    private String consult_relocation;
    private String PrefstateValues;
    private String reqSkillSet;
//jagan
    private String techReviewComments;
    private String techReviewQuestions;
    private String techReviewSeverity;
    private String techReviewTime;
    private String skillCategoryArry;
    private String skill1Questions;
    private String skill2Questions;
    private String skill3Questions;
    private String skill4Questions;
    private String skill5Questions;
    private String skill6Questions;
    private String skill7Questions;
    private String skill8Questions;
    private String skill9Questions;
    private String skill10Questions;
    private Map psychoSkillValuesMap;
    private String gridPDFDownload;
    private int enameIdForRecruitment;
    private String enameForRecruitment;
    private String reviewType;
    private String venEmail;
    private String venName;
    private String vendorcomments;
    private String checked;
    public String getGridPDFDownload() {
        return gridPDFDownload;
    }

    public void setGridPDFDownload(String gridPDFDownload) {
        this.gridPDFDownload = gridPDFDownload;
    }

    public String getPrefstateValues() {
        return PrefstateValues;
    }

    public void setPrefstateValues(String PrefstateValues) {
        this.PrefstateValues = PrefstateValues;
    }

    public String doAddConsultantForReq() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                if ("added".equalsIgnoreCase(getResultFlag())) {
                    addActionMessage("Consultant added Successfully");
                }
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * Method : Consultant add action
     *
     */
    /**
     * ****************************************************************************
     * Date : May 12 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * getMyConsultantSearch method can be used to show default requirement list
     * in grid view
     * *****************************************************************************
     */
    public String getMyConsultantSearch() {
        resultMessage = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(getUserSessionId()));
                setConsult_WCountry(ServiceLocator.getLocationService().getCountryNames());

                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                ConsultantListDetails = ServiceLocator.getRecruitmentService().getMyDefaultConsListDetails(this);
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessages(ex);

                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }// Session validator if END
        return resultMessage;
    }

    /**
     * ****************************************************************************
     * Date : May 12 2015
     *
     * Author : divya gandreti<dgandreti@miraclesoft.com>
     *
     * getConsultant method can be used to search required persons requirement
     * list in grid view
     * *****************************************************************************
     */
    public String getConsultant() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
            try {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(getUserSessionId()));
                setConsult_WCountry(ServiceLocator.getLocationService().getCountryNames());
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                ConsultantListDetails = ServiceLocator.getRecruitmentService().getConsListDetails(this);
                setConsultantFlag(getConsultantFlag());
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessagetUserLeavesServiceges(ex);
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * Method : Consultant add action Use to open the add consultant jsp page
     * created by Aklakh
     */
    public String doAddConsultant() {

        try {
            String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
            int orgid = Integer.valueOf(oId);
            //  setOrganization(DataSourceDataProvider.getInstance().getOrganizationByOrgId(orgid));
            setConsult_WCountry(ServiceLocator.getLocationService().getCountryNames());   //  getCountriesNames());
            setIndustry(ServiceLocator.getLookUpHandlerService().getIndustryTypesMap());
            setExperience(DataSourceDataProvider.getInstance().getYearsOfExp());

            SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
            System.out.println("Session-->addRequirements-->" + session);
            Map skillsmap = (Map) session.get("skillsmap");
            setSkillValuesMap(skillsmap);
            // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
            resultMessage = SUCCESS;
        } catch (Exception ex) {
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);

            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultMessage = ERROR;
        }
        return resultMessage;
    }

    /**
     * *************************************
     *
     * @getRequirementDetails() method is used to get Requirement details of
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String getRequirementDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                requirementskDetails = ServiceLocator.getRecruitmentService().getRequirementDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(requirementskDetails);
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
     * @getAllRequirementList() method is used to get Requirement details of
     * account
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:05/07/2015
     *
     **************************************
     */
    public String getAllRequirementList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setReqCreatedByMap(com.mss.msp.util.DataSourceDataProvider.getInstance().GetProjectManagersListByOrgId(this.getSessionOrgId()));
                setAccount_name(DataSourceDataProvider.getInstance().getAccountNameById(getOrgid()));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                //setRequirementStatus("R");
                requirementlistVTO = ServiceLocator.getRecruitmentService().getAllRequirementList(httpServletRequest, this);
                if (requirementlistVTO.size() == 0) {
                    setRequirementlistVTO(null);
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date : May 5 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * doAddnewConsultant() method can be used to add the new consultant on the
     * basic of organization id
     *
     * *****************************************************************************
     */
    public String addNewConsultant() {
        resultType = LOGIN;
        //filePath = "D:\\uploadedfiles";
        int addresult = 0;
        //filePath="C:\\usr\\ProjectFiles\\MSP\\TASK_ATTACHMENTS";
        //filePath = Properties.getProperty("Task.Attachment");

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {


                if (getFileFileName() == null) {
                    System.out.println("file is null so it adds only data in task_list table");
                } else {
                    filesPath = Properties.getProperty("Task.Attachment");
                    File createPath = new File(filesPath);
                    Date dt = new Date();
                    /*The month is generated from here*/

                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }
                    short week = (short) (Math.round(dt.getDate() / 7));
                    /*getrequestType is used to create a directory of the object type specified in the jsp page*/
                    createPath = new File(createPath.getAbsolutePath() + "/" + String.valueOf(dt.getYear() + 1900) + "/" + month + "/" + String.valueOf(week));
                    /*This creates a directory forcefully if the directory does not exsist*/

                    //System.out.println("path::"+createPath);
                    createPath.mkdir();
                    /*here it takes the absolute path and the name of the file that is to be uploaded*/
                    File theFile = new File(createPath.getAbsolutePath());

                    setFilesPath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile + File.separator + fileFileName);
                    FileUtils.copyFile(file, destFile);
                }

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                if (getAddconsult_checkAddress().equalsIgnoreCase("true")) {
                    setAddress_flag("PC");

                }
                if (getAddconsult_checkAddress().equalsIgnoreCase("false")) {
                    setAddress_flag("P");
                }

                String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
                int orgid = Integer.valueOf(oId);
                //   setOrganization(DataSourceDataProvider.getInstance().getOrganizationByOrgId(orgid));
                setConsult_WCountry(ServiceLocator.getLocationService().getCountriesNamesMap());        // getGeneralService().getCountriesNames());
                setIndustry(DataSourceDataProvider.getInstance().getIndustryName());
                setExperience(DataSourceDataProvider.getInstance().getYearsOfExp());
                int res = ServiceLocator.getRecruitmentService().doAddConsultantDetails(this, orgid);
                //TODO Display Success message on JSP
                if (res > 0) {
                    setConsultFlag("success");
                    System.out.println("in action success in adding consultant detailssssssssss");

                } else {
                    setConsultFlag("failure");
                    System.out.println("in action failed to adding consultant detailssssssssss");
                    //httpServletRequest.getSession(false).setAttribute("taskdata", null);
                    //resultType = SUCCESS;
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

    /**
     * triveni method start
     */
    public String getupdateConsultantDetails() {
        System.out.println("-----------------get Update action--------------");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
            if (getConsultFlag().equalsIgnoreCase("consultant")) {
                setConsult_id(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            } else if (getConsultFlag().equalsIgnoreCase("vendor")) {
                setConsultFlag("vendor");
            } else if (getConsultFlag().equalsIgnoreCase("customer")) {
                setConsultFlag("customer");
            }

            try {
                String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
                int orgid = Integer.valueOf(oId);

                //  setOrganization(DataSourceDataProvider.getInstance().getOrganizationByOrgId(orgid));
                setIndustry(ServiceLocator.getLookUpHandlerService().getIndustryTypesMap());
                setCountry(ServiceLocator.getLocationService().getCountryNames());
                setExperience(DataSourceDataProvider.getInstance().getYearsOfExp());

                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);

                //  setState(DataSourceDataProvider.getInstance().getAllStates());
                consultantVTO = ServiceLocator.getRecruitmentService().getupdateConsultantDetails(this, skillsmap);
                setPermanentState(DataSourceDataProvider.getInstance().getPermanentStates(consultantVTO.getConsult_Country()));
                setCurrentState(DataSourceDataProvider.getInstance().getPermanentStates(consultantVTO.getConsult_CCountry()));
                setPreState(DataSourceDataProvider.getInstance().getPermanentStates(consultantVTO.getConsult_preferredCountry()));

                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                System.out.println("orgId->" + orgid + "industry-->" + consultantVTO.getConsult_industry() + "exp--->" + getConsult_experience());
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessagetUserLeavesServiceges(ex);
                System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    public String getCurrentStatus() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setConsultantListVTO(ServiceLocator.getRecruitmentService().getCurrentStatus(this));
            } catch (Exception e) {
            }
        }
        return SUCCESS;
    }

    public Map getState() {
        return state;
    }

    public void setState(Map state) {
        this.state = state;
    }

    public String doupdateConsultantDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            if (getConsultFlag().equalsIgnoreCase("consultant")) {
            } else if (getConsultFlag().equalsIgnoreCase("vendor")) {
                setConsultFlag("vendor");
            } else if (getConsultFlag().equalsIgnoreCase("customer")) {
                setConsultFlag("customer");
            }
            try {
                int orgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                // String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
                //int orgid = Integer.valueOf(oId);


                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                if (getConsult_checkAddress().equalsIgnoreCase("true")) {
                    setConsult_checkAddress("PC");
                } else {
                    setConsult_checkAddress("P");
                }

                consultantVTO = ServiceLocator.getRecruitmentService().doupdateConsultantDetails(this, userSessionId, orgId);

                addActionMessage("Updated successfully");

                resultMessage = SUCCESS;
            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessagetUserLeavesServiceges(ex);
                //  System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * getDefaultConsultantListDetails() getting consultant list Default.
     *
     *
     * *****************************************************************************
     */
    public String getDefaultConsultantListDetails() {
        resultType = LOGIN;
        String consultantList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                consultantList = ServiceLocator.getRecruitmentService().getConsultantListDetails(httpServletRequest, this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(consultantList);

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
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : Divya<dgandreti@miraclesoft.com>
     *
     * getConsultantListDetailsBySearch() getting consultant list by searching.
     *
     *
     * *****************************************************************************
     */
    public String getConsultantListDetailsBySearch() {
        resultType = LOGIN;
        String consultantList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                consultantList = ServiceLocator.getRecruitmentService().searchConsultantListDetails(httpServletRequest, this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(consultantList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String addConsultAttachment() {
        resultType = LOGIN;
        //filePath = "D:\\uploadedfiles";
        int addresult = 0;
        //filePath="C:\\usr\\ProjectFiles\\MSP\\TASK_ATTACHMENTS";
        //filePath = Properties.getProperty("Task.Attachment");

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {

                //setTasksStatusList(dataSourceDataProvider.getInstance().getTaskStatusByOrgId());
                //setTasksRelatedToList(dataSourceDataProvider.getInstance().getTaskrelatedToMap());
                if (getConsultAttachmentFileName() == null) {
                    System.out.println("file is null so it adds only data in task_list table");
                } else {
                    filePath = Properties.getProperty("Task.Attachment");
                    File createPath = new File(filePath);
                    Date dt = new Date();
                    /*The month is generated from here*/

                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }
                    short week = (short) (Math.round(dt.getDate() / 7));
                    /*getrequestType is used to create a directory of the object type specified in the jsp page*/
                    createPath = new File(createPath.getAbsolutePath() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    /*This creates a directory forcefully if the directory does not exsist*/

                    //System.out.println("path::"+createPath);
                    createPath.mkdir();
                    /*here it takes the absolute path and the name of the file that is to be uploaded*/
                    File theFile = new File(createPath.getAbsolutePath());

                    setFilePath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile, consultAttachmentFileName);
                    //setFilePath(destFile.toString());
                    FileUtils.copyFile(consultAttachment, destFile);
                }
//                File destFile = new File(filePath, taskAttachmentFileName);
//                FileUtils.copyFile(taskAttachment, destFile);

                //System.out.println("file is null so it adds only data in task_list table");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                //  System.out.println("Action called in TaskHandlerAction classsssssssssssssssssssssssssssssssssssssssss");
                addresult = ServiceLocator.getRecruitmentService().addConsultAttachmentDetails(this);

                if (addresult > 0) {
                    System.out.println("in action success in adding task detailssssssssss");
                    //httpServletRequest.getSession(false).setAttribute("taskdata", taskDetails);
                    //resultType = SUCCESS;
                } else {
                    System.out.println("in action failed to adding task detailssssssssss");
                    //httpServletRequest.getSession(false).setAttribute("taskdata", null);
                    //resultType = SUCCESS;
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
    /*
     * @getActivityDetails() method is for getting consultant activity details 
     * 
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/02/2015
     * 
     */

    public String getActivityDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //int usrid=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                //System.out.println("--------------> Consult Id"+getConsultantId());
                //System.out.println("--------------> Consult Id"+getConsult_id());
                String activityDetails = ServiceLocator.getRecruitmentService().getActivityDetails(getConsult_id());
                // System.out.println("" + activityDetails);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(activityDetails);
            } else {
//                System.out.println("----------------> else normal skill");
                resultType = SUCCESS;
            }
        } catch (Exception ex) {

            resultType = ERROR;
        }
        return null;
    }

    /*
     * @doAddConsultantActivityDetails() method is for adding consultant activity details 
     * 
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/02/2015
     * 
     */
    public String doAddConsultantActivityDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setActivityCratedBy(userid);
                //setConsult_id(consult_id);
                setActivityRelation("acc");
                int update = ServiceLocator.getRecruitmentService().doAddConsultantActivityDetails(this);
                // System.out.println("update is------>"+update);
                //if(update>0){
                /*consultantActivityDetails = ServiceLocator.getRecruitmentService().getConsultantActivityDetails(this);
                 httpServletRequest.getSession(false).setAttribute("consultantActivityDetails", consultantActivityDetails);
                 //}
                 resultMessage = SUCCESS;*/

            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }

        }
        //return resultMessage;
        return null;
    }

    /*
     * @dogetConsultActivitydetailsbyActivity() method is for gettingn consultant activity details by activity id
     * 
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/02/2015
     * 
     */
    public String dogetConsultActivitydetailsbyActivity() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                //System.out.println("dogetConsultActivitydetailsbyActivity********"+getActivityId());
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setActivityCratedBy(userid);
                //setConsult_id(consult_id);
                setActivityRelation("acc");
                String activityString = ServiceLocator.getRecruitmentService().getConsultantActivityDetailsbyActivityId(this);
                // System.out.println("activityString"+activityString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(activityString);
                //httpServletRequest.getSession(false).setAttribute("activityString", activityString);
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }

        }
        return null;
    }

    /*
     * @doEditConsultantActivityDetails() method for editing consultant activity details 
     * 
     * @Author:Kiran Arogya<adoddi@miraclesoft.com>
     *
     * @Created Date:05/02/2015
     * 
     */
    public String doEditConsultantActivityDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("doEditConsultantActivityDetails********1");
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setActivityCratedBy(userid);
                //setActivityId(2);
                // System.out.println("Activity id---"+getActivityId());
                setActivityRelation("acc");
                int update = ServiceLocator.getRecruitmentService().doEditConsultantActivityDetails(this);
//                consultantActivityDetails = ServiceLocator.getRecruitmentService().getConsultantActivityDetails(this);
//                httpServletRequest.getSession(false).setAttribute("consultantActivityDetails", consultantActivityDetails);
//                resultMessage = SUCCESS;
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
                resultMessage = ERROR;
            }

        }
        return null;
    }

    /**
     * *************************************
     *
     * @getLoginUserRequirementList() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String getLoginUserRequirementList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                System.out.println("by nag");
                setTypeOfUser(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setAccount_name(DataSourceDataProvider.getInstance().getAccountNameById(getOrgid()));

                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);

                //  setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());

                requirementlistVTO = ServiceLocator.getRecruitmentService().getLoginUserRequirementList(httpServletRequest, this);
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                System.out.println("requirementlistVTO" + requirementlistVTO);
                if (requirementlistVTO.size() == 0) {
                    setRequirementlistVTO(null);
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }
    private Map reqCategory;

    public Map getReqCategory() {
        return reqCategory;
    }

    public void setReqCategory(Map reqCategory) {
        this.reqCategory = reqCategory;
    }

    /**
     * *************************************
     *
     * @getConsultantStatus() getting status of requirement of account
     *
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:05/21/2015
     *
     **************************************
     */
    public String getConsultantStatus() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setConsultantListVTO(ServiceLocator.getRecruitmentService().getConsultantStatus(this));
            } catch (Exception e) {
            }
        }
        return SUCCESS;
    }

    /**
     * *************************************
     *
     * @getLoginUserRequirementList() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String techReview() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setReqName(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqNameById(getRequirementId()));
                setConsult_name(com.mss.msp.util.DataSourceDataProvider.getInstance().getConsultNameById(getConsult_id()));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + getAccountSearchID() + ">>>>>" + getAccountFlag());
                consultantList = ServiceLocator.getRecruitmentService().getTechReviewSearchDetails(this);
                if (consultantList.size() == 0) {
                    setConsultantList(null);
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date : May 14 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * techReviewForward() getting consultant list Default.
     *
     *
     * *****************************************************************************
     */
    public String techReviewForward() {
        resultType = LOGIN;
        int result = 0, mailResult = 0, conMailResult = 0;
        try {
            System.out.println("techReviewForward action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //System.out.println("===============>rk");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                //(dataSourceDataProvider.getInstance().getAccountNameById(getSessionOrgId()));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getSessionOrgId()));
                String accToken = com.mss.msp.util.DataUtility.getRandomHexadecimal(64);
                String validKey = com.mss.msp.util.DataUtility.getRandomHexadecimal(6);
                //System.out.println("accToken----->"+accToken);
                result = ServiceLocator.getRecruitmentService().techReviewForward(this, accToken, validKey);

                if (result > 0) {
                    dataSourceDataProvider.getInstance().getMailIdsOfConAndEmp(this);
                    dataSourceDataProvider.getInstance().getVendorEmpEmail(this);
                    //System.out.println("MAIL IDS IN ACTION AFTER DSDP::>>>>" + getConEmail() + "  " + getEmpEmail());
                    if ("Online".equals(getInterviewType()) || "Psychometric".equals(getInterviewType())) {
                        setReqName(DataSourceDataProvider.getInstance().getReqNameById(getRequirementId()));
                        mailResult = mailManager.doAddTechReviewEmailLogger(this, validKey, getConEmail(), accToken, getReqName());
                        mailResult = mailManager.techReviewTechieEmailGenerator(this, getEmpEmail());
                        mailResult = mailManager.techReviewMailToVendor(this, getVenEmail());
                    } else {
                        mailManager.sendInvitationToConsultant(this, getConEmail(), getEmpEmail());
                        mailResult = mailManager.techReviewTechieEmailGenerator(this, getEmpEmail());
                        mailResult = mailManager.techReviewMailToVendor(this, getVenEmail());
                        conMailResult = mailManager.techReviewConsultantEmailGenerator(this);
                    }
                    if (mailResult > 0 && conMailResult > 0) {
                        //System.out.println("Email logger added ================================>%%%%%%%%%%%%%%%%%%%%%%%%");
                    }
                }
                // System.out.println("in recruitment action" + result);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

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
     * @getTechReviewDetails() method is used to get Requirement details of
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String getTechReviewDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                consultantList = ServiceLocator.getRecruitmentService().getTechReviewDetails(this);
                System.out.println("consultantList" + consultantList.toString());
                if (consultantList.size() == 0) {
                    setConsultantList(null);
                }
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * *************************************
     *
     * @getSearchTechReviewList() method is used to get Requirement details of
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String getSearchTechReviewList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String result = ServiceLocator.getRecruitmentService().getSearchTechReviewList(this);
                System.out.println("in recruitment action" + result);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getSearchTechReviewList() method is used to get Requirement details of
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String getConsultDetailsOnOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String result = ServiceLocator.getRecruitmentService().getConsultDetailsOnOverlay(this);
                System.out.println("in recruitment action" + result);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getSearchTechReviewList() method is used to get Requirement details of
     * account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String saveTechReviewResults() {
        resultMessage = LOGIN;
        String resultString = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int result = ServiceLocator.getRecruitmentService().saveTechReviewResults(this);
                System.out.println("in recruitment action" + result);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                if (result >= 1) {
                    resultString = "1";
                    httpServletResponse.getWriter().write(resultString);
                } else {
                    resultString = "0";
                    httpServletResponse.getWriter().write(resultString);
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : June 1 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * doAddnewConsultant() method can be used to add the new consultant on the
     * basic of organization id
     *
     * *****************************************************************************
     */
    public String deleteConsultantAttachment() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("Attachment Id============>" + getAcc_attachment_id());
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int deleteResult = ServiceLocator.getRecruitmentService().doDeleteConsultantAttachment(this);
            } catch (Exception e) {
            }
        }
        return SUCCESS;
    }

    /**
     * *****************************************************************************
     * Date : June 1 2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * UploadConsultantAttachments() method can be used to add the consultant
     * updated resume
     *
     * *****************************************************************************
     */
    public String UploadConsultantAttachments() throws Exception {
        System.out.println("entered into updateAttachment  action");
        resultType = LOGIN;
        int addresult = 0;
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% File Data Start %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("File Name is:" + getFileFileName());
        System.out.println("File ContentType is:" + getFileContentType());
        System.out.println("Files Directory is:" + filePath + "-------->" + getFilePath());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% File Data End %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("User Id----------------------------->" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID));

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                if (getFileFileName() == null) {
                    System.out.println("file is null so it adds only data in  table");
                } else {
                    filePath = Properties.getProperty("Task.Attachment");
                    File createPath = new File(filePath);
                    Date dt = new Date();
                    /*The month is generated from here*/

                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }
                    short week = (short) (Math.round(dt.getDate() / 7));

                    createPath = new File(createPath.getAbsolutePath() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    createPath.mkdir();

                    File theFile = new File(createPath.getAbsolutePath());

                    System.out.println("File Path::" + theFile);

                    setFilePath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile, getFileFileName());
                    System.out.println(">>DestFile>>" + destFile);
                    //setFilePath(destFile.toString());
                    System.out.println("THE FINAL PATH IT IS SET IN THE ACTION::>>>>>" + getFilePath());
                    FileUtils.copyFile(file, destFile);
                }
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                addresult = ServiceLocator.getRecruitmentService().updateConsultAttachmentDetails(this);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        } catch (Exception ex) {
            System.out.println("Exception in update attachment  action-->" + ex.getMessage());
            resultType = ERROR;
        }

        return SUCCESS;
    }

    /**
     * *************************************
     *
     * @getLoginUserRequirementList() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String forwardTechReview() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setReqName(com.mss.msp.util.DataSourceDataProvider.getInstance().getReqNameById(getRequirementId()));
                setConsult_name(com.mss.msp.util.DataSourceDataProvider.getInstance().getConsultNameById(getConsult_id()));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));

                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setPsychoSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory(0));
                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                //consultantList = ServiceLocator.getRecruitmentService().getTechReviewSearchDetails(httpServletRequest, this);
                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return resultMessage;
    }

    /**
     * *************************************
     *
     * @ userMigration() method is used to migrate consultant into user
     *
     *
     * @Author:divya gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:07/20/2015
     *
     **************************************
     */
    public String userMigration() {
        System.out.println("in mi");
        String resultString = "";
        int inserted = 0;
        int exists = 0;
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                System.out.println("BEFORE DSDP>>>>" + getConsult_id() + " " + getReq_Id());
                exists = dataSourceDataProvider.getInstance().doCheckEmailExistsOrNot(getConsult_id(), getReq_Id());
                System.out.println(">>>>result of dsdp method>>" + exists);

                if (exists > 0) {
                    resultString = "2";
                    //httpServletResponse.getWriter().write(resultString);
                } else {
                    inserted = ServiceLocator.getRecruitmentService().userMigration(this);
                    if (inserted > 0) {
                        System.out.println("in migration action");
                        resultString = "1";
                        //httpServletResponse.getWriter().write(resultString);
                    } else {
                        resultString = "0";
                        //httpServletResponse.getWriter().write(resultString);
                        System.out.println("no");
                    }
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);



            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String addVendorFormAttachments() {
        resultType = LOGIN;
        //filePath = "D:\\uploadedfiles";
        int addresult = 0;
        //filePath="C:\\usr\\ProjectFiles\\MSP\\TASK_ATTACHMENTS";
        //filePath = Properties.getProperty("Task.Attachment");

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {


                if (getFileFileName() == null) {
                    System.out.println("file is null so it adds only data in task_list table");
                } else {
                    filesPath = Properties.getProperty("Forms.Attachment");
                    File createPath = new File(filesPath);
                    Date dt = new Date();
                    /*The month is generated from here*/

                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }
                    short week = (short) (Math.round(dt.getDate() / 7));
                    /*getrequestType is used to create a directory of the object type specified in the jsp page*/
                    createPath = new File(createPath.getAbsolutePath() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    /*This creates a directory forcefully if the directory does not exsist*/

                    //System.out.println("path::"+createPath);
                    createPath.mkdir();
                    /*here it takes the absolute path and the name of the file that is to be uploaded*/
                    File theFile = new File(createPath.getAbsolutePath());

                    setFilesPath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile + File.separator + fileFileName);
                    FileUtils.copyFile(file, destFile);
                }

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                typeOfAccount = dataSourceDataProvider.getInstance().getTypeOfAccount(getViewAccountID());

                String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
                int orgid = Integer.valueOf(oId);


                int res = ServiceLocator.getRecruitmentService().doAddVendorFormDetails(this, orgid, typeOfAccount);
                //TODO Display Success message on JSP
                if (res > 0) {
                    setConsultFlag("success");
                    System.out.println("in action success in adding Attachment detailssssssssss");

                } else {
                    setConsultFlag("failure");
                    System.out.println("in action failed to adding Attachment detailssssssssss");
                    //httpServletRequest.getSession(false).setAttribute("taskdata", null);
                    //resultType = SUCCESS;
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

    public String updateVendorFormAttachments() {
        resultType = LOGIN;
        //filePath = "D:\\uploadedfiles";
        int addresult = 0;
        //filePath="C:\\usr\\ProjectFiles\\MSP\\TASK_ATTACHMENTS";
        //filePath = Properties.getProperty("Task.Attachment");

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {


                if (getFileFileName() == null) {
                    System.out.println("file is null so it adds only data in task_list table");
                } else {
                    filesPath = Properties.getProperty("Forms.Attachment");
                    File createPath = new File(filesPath);
                    Date dt = new Date();
                    /*The month is generated from here*/

                    String month = "";
                    if (dt.getMonth() == 0) {
                        month = "Jan";
                    } else if (dt.getMonth() == 1) {
                        month = "Feb";
                    } else if (dt.getMonth() == 2) {
                        month = "Mar";
                    } else if (dt.getMonth() == 3) {
                        month = "Apr";
                    } else if (dt.getMonth() == 4) {
                        month = "May";
                    } else if (dt.getMonth() == 5) {
                        month = "Jun";
                    } else if (dt.getMonth() == 6) {
                        month = "Jul";
                    } else if (dt.getMonth() == 7) {
                        month = "Aug";
                    } else if (dt.getMonth() == 8) {
                        month = "Sep";
                    } else if (dt.getMonth() == 9) {
                        month = "Oct";
                    } else if (dt.getMonth() == 10) {
                        month = "Nov";
                    } else if (dt.getMonth() == 11) {
                        month = "Dec";
                    }
                    short week = (short) (Math.round(dt.getDate() / 7));
                    /*getrequestType is used to create a directory of the object type specified in the jsp page*/
                    createPath = new File(createPath.getAbsolutePath() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    /*This creates a directory forcefully if the directory does not exsist*/

                    //System.out.println("path::"+createPath);
                    createPath.mkdir();
                    /*here it takes the absolute path and the name of the file that is to be uploaded*/
                    File theFile = new File(createPath.getAbsolutePath());

                    setFilesPath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile + File.separator + fileFileName);
                    FileUtils.copyFile(file, destFile);
                }

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());


                String oId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString();
//                int orgid = Integer.valueOf(oId);

                System.out.println("getAttachment_id--------->" + getAttachment_id_edit());
                int res = ServiceLocator.getRecruitmentService().doUpdateVendorFormDetails(this, getAttachment_id_edit());
                //TODO Display Success message on JSP
                if (res > 0) {
                    setConsultFlag("success");
                    System.out.println("in action success in adding Attachment detailssssssssss");

                } else {
                    setConsultFlag("failure");
                    System.out.println("in action failed to adding Attachment detailssssssssss");
                    //httpServletRequest.getSession(false).setAttribute("taskdata", null);
                    //resultType = SUCCESS;
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

    public String getVendorRequirementsDashboard() {
        resultMessage = LOGIN;
        try {
            System.out.println("getVendorRequirementsDashboard");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgid(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                requirementlistVTO = ServiceLocator.getRecruitmentService().getVendorRequirementsDashboard(httpServletRequest, this);
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultMessage = ERROR;

        }
        return resultMessage;
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

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public List getConsultantListDetails() {
        return ConsultantListDetails;
    }

    public void setConsultantListDetails(List ConsultantListDetails) {
        this.ConsultantListDetails = ConsultantListDetails;
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

    public Map getConsult_WCountry() {
        return consult_WCountry;
    }

    public void setConsult_WCountry(Map consult_WCountry) {
        this.consult_WCountry = consult_WCountry;
    }

    public Map getOrganization() {
        return organization;
    }

    public void setOrganization(Map organization) {
        this.organization = organization;
    }

    public Map getIndustry() {
        return industry;
    }

    public void setIndustry(Map industry) {
        this.industry = industry;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getRequirementskDetails() {
        return requirementskDetails;
    }

    public void setRequirementskDetails(String requirementskDetails) {
        this.requirementskDetails = requirementskDetails;
    }

    public int getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(int accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public List getRequirementlistVTO() {
        return requirementlistVTO;
    }

    public void setRequirementlistVTO(List requirementlistVTO) {
        this.requirementlistVTO = requirementlistVTO;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public Map getTeamMembersList() {
        return teamMembersList;
    }

    public void setTeamMembersList(Map teamMembersList) {
        this.teamMembersList = teamMembersList;
    }

    public String getConsultantFlag() {
        return consultantFlag;
    }

    public void setConsultantFlag(String consultantFlag) {
        this.consultantFlag = consultantFlag;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
    /*
     public String getCnslt_email() {
     return cnslt_email;
     }

     public void setCnslt_email(String cnslt_email) {
     this.cnslt_email = cnslt_email;
     }

     public String getCnslt_fstname() {
     return cnslt_fstname;
     }

     public void setCnslt_fstname(String cnslt_fstname) {
     this.cnslt_fstname = cnslt_fstname;
     }

     public String getCnslt_gender() {
     return cnslt_gender;
     }

     public void setCnslt_gender(String cnslt_gender) {
     this.cnslt_gender = cnslt_gender;
     }

     public String getCnslt_homePhone() {
     return cnslt_homePhone;
     }

     public void setCnslt_homePhone(String cnslt_homePhone) {
     this.cnslt_homePhone = cnslt_homePhone;
     }

     public String getCnslt_add_date() {
     return cnslt_add_date;
     }

     public void setCnslt_add_date(String cnslt_add_date) {
     this.cnslt_add_date = cnslt_add_date;
     }

     public String getCnslt_lstname() {
     return cnslt_lstname;
     }

     public void setCnslt_lstname(String cnslt_lstname) {
     this.cnslt_lstname = cnslt_lstname;
     }

     public String getCnslt_dob() {
     return cnslt_dob;
     }

     public void setCnslt_dob(String cnslt_dob) {
     this.cnslt_dob = cnslt_dob;
     }

     public String getCnslt_mobileNo() {
     return cnslt_mobileNo;
     }

     public void setCnslt_mobileNo(String cnslt_mobileNo) {
     this.cnslt_mobileNo = cnslt_mobileNo;
     }

     public String getCnslt_available() {
     return cnslt_available;
     }

     public void setCnslt_available(String cnslt_available) {
     this.cnslt_available = cnslt_available;
     }

     public String getCnslt_midname() {
     return cnslt_midname;
     }

     public void setCnslt_midname(String cnslt_midname) {
     this.cnslt_midname = cnslt_midname;
     }

     public String getCnslt_mStatus() {
     return cnslt_mStatus;
     }

     public void setCnslt_mStatus(String cnslt_mStatus) {
     this.cnslt_mStatus = cnslt_mStatus;
     }

     public int getCnslt_lcountry() {
     return cnslt_lcountry;
     }

     public void setCnslt_lcountry(int cnslt_lcountry) {
     this.cnslt_lcountry = cnslt_lcountry;
     }

     public String getAddAddressFlag() {
     return addAddressFlag;
     }

     public void setAddAddressFlag(String addAddressFlag) {
     this.addAddressFlag = addAddressFlag;
     }

     public String getAddConsult_Address() {
     return addConsult_Address;
     }

     public void setAddConsult_Address(String addConsult_Address) {
     this.addConsult_Address = addConsult_Address;
     }

     public String getAddConsult_Address2() {
     return addConsult_Address2;
     }

     public void setAddConsult_Address2(String addConsult_Address2) {
     this.addConsult_Address2 = addConsult_Address2;
     }

     public String getAddConsult_City() {
     return addConsult_City;
     }

     public void setAddConsult_City(String addConsult_City) {
     this.addConsult_City = addConsult_City;
     }

     public String getAddConsult_Country() {
     return addConsult_Country;
     }

     public void setAddConsult_Country(String addConsult_Country) {
     this.addConsult_Country = addConsult_Country;
     }

     public int getAddConsult_State() {
     return addConsult_State;
     }

     public void setAddConsult_State(int addConsult_State) {
     this.addConsult_State = addConsult_State;
     }

     public String getAddConsult_Zip() {
     return addConsult_Zip;
     }

     public void setAddConsult_Zip(String addConsult_Zip) {
     this.addConsult_Zip = addConsult_Zip;
     }

     public String getAddConsult_Phone() {
     return addConsult_Phone;
     }

     public void setAddConsult_Phone(String addConsult_Phone) {
     this.addConsult_Phone = addConsult_Phone;
     }

     public String getCurrentAddressFlag() {
     return currentAddressFlag;
     }

     public void setCurrentAddressFlag(String currentAddressFlag) {
     this.currentAddressFlag = currentAddressFlag;
     }

     public String getAddConsult_CAddress() {
     return addConsult_CAddress;
     }

     public void setAddConsult_CAddress(String addConsult_CAddress) {
     this.addConsult_CAddress = addConsult_CAddress;
     }

     public String getAddConsult_CAddress2() {
     return addConsult_CAddress2;
     }

     public void setAddConsult_CAddress2(String addConsult_CAddress2) {
     this.addConsult_CAddress2 = addConsult_CAddress2;
     }

     public String getAddConsult_CCity() {
     return addConsult_CCity;
     }

     public void setAddConsult_CCity(String addConsult_CCity) {
     this.addConsult_CCity = addConsult_CCity;
     }

     public String getAddConsult_CCountry() {
     return addConsult_CCountry;
     }

     public void setAddConsult_CCountry(String addConsult_CCountry) {
     this.addConsult_CCountry = addConsult_CCountry;
     }

     public int getAddConsult_CState() {
     return addConsult_CState;
     }

     public void setAddConsult_CState(int addConsult_CState) {
     this.addConsult_CState = addConsult_CState;
     }

     public String getAddConsult_CZip() {
     return addConsult_CZip;
     }

     public void setAddConsult_CZip(String addConsult_CZip) {
     this.addConsult_CZip = addConsult_CZip;
     }

     public String getAddConsult_CPhone() {
     return addConsult_CPhone;
     }

     public void setAddConsult_CPhone(String addConsult_CPhone) {
     this.addConsult_CPhone = addConsult_CPhone;
     }

     public int getCnslt_industry() {
     return cnslt_industry;
     }

     public void setCnslt_industry(int cnslt_industry) {
     this.cnslt_industry = cnslt_industry;
     }

     public String getCnslt_salary() {
     return cnslt_salary;
     }

     public void setCnslt_salary(String cnslt_salary) {
     this.cnslt_salary = cnslt_salary;
     }

     public int getCnslt_wcountry() {
     return cnslt_wcountry;
     }

     public void setCnslt_wcountry(int cnslt_wcountry) {
     this.cnslt_wcountry = cnslt_wcountry;
     }

     public int getCnslt_organization() {
     return cnslt_organization;
     }

     public void setCnslt_organization(int cnslt_organization) {
     this.cnslt_organization = cnslt_organization;
     }

     public String getCnslt_experience() {
     return cnslt_experience;
     }

     public void setCnslt_experience(String cnslt_experience) {
     this.cnslt_experience = cnslt_experience;
     }

     public int getCnslt_preferredState() {
     return cnslt_preferredState;
     }

     public void setCnslt_preferredState(int cnslt_preferredState) {
     this.cnslt_preferredState = cnslt_preferredState;
     }

     public String getCnslt_jobTitle() {
     return cnslt_jobTitle;
     }

     public void setCnslt_jobTitle(String cnslt_jobTitle) {
     this.cnslt_jobTitle = cnslt_jobTitle;
     }

     public String getCnslt_workPhone() {
     return cnslt_workPhone;
     }

     public void setCnslt_workPhone(String cnslt_workPhone) {
     this.cnslt_workPhone = cnslt_workPhone;
     }

     public String getCnslt_referredBy() {
     return cnslt_referredBy;
     }

     public void setCnslt_referredBy(String cnslt_referredBy) {
     this.cnslt_referredBy = cnslt_referredBy;
     }

     public int getCnslt_pcountry() {
     return cnslt_pcountry;
     }

     public void setCnslt_pcountry(int cnslt_pcountry) {
     this.cnslt_pcountry = cnslt_pcountry;
     }

     public String getCnslt_description() {
     return cnslt_description;
     }

     public void setCnslt_description(String cnslt_description) {
     this.cnslt_description = cnslt_description;
     }

     public String getCnslt_comments() {
     return cnslt_comments;
     }

     public void setCnslt_comments(String cnslt_comments) {
     this.cnslt_comments = cnslt_comments;
     }

     public String getAddconsult_checkAddress() {
     return addconsult_checkAddress;
     }

     public void setAddconsult_checkAddress(String addconsult_checkAddress) {
     this.addconsult_checkAddress = addconsult_checkAddress;
     }

     public String getCnslt_skills() {
     return cnslt_skills;
     }

     public void setCnslt_skills(String cnslt_skills) {
     this.cnslt_skills = cnslt_skills;
     }
     */

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    /**
     * @return the experience
     */
    public Map getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(Map experience) {
        this.experience = experience;
    }

    public int getConsult_id() {
        return consult_id;
    }

    public void setConsult_id(int consult_id) {
        this.consult_id = consult_id;
    }

    public String getConsult_name() {
        return consult_name;
    }

    public void setConsult_name(String consult_name) {
        this.consult_name = consult_name;
    }

    public String getConsult_email() {
        return consult_email;
    }

    public void setConsult_email(String consult_email) {
        this.consult_email = consult_email;
    }

    public String getConsult_skill() {
        return consult_skill;
    }

    public void setConsult_skill(String consult_skill) {
        this.consult_skill = consult_skill;
    }

    public String getConsult_phno() {
        return consult_phno;
    }

    public void setConsult_phno(String consult_phno) {
        this.consult_phno = consult_phno;
    }

    public ConsultantVTO getConsultantVTO() {
        return consultantVTO;
    }

    public void setConsultantVTO(ConsultantVTO consultantVTO) {
        this.consultantVTO = consultantVTO;
    }

    public String getConsult_favail() {
        return consult_favail;
    }

    public void setConsult_favail(String consult_favail) {
        this.consult_favail = consult_favail;
    }

    public String getConsult_fstname() {
        return consult_fstname;
    }

    public void setConsult_fstname(String consult_fstname) {
        this.consult_fstname = consult_fstname;
    }

    public String getConsult_homePhone() {
        return consult_homePhone;
    }

    public void setConsult_homePhone(String consult_homePhone) {
        this.consult_homePhone = consult_homePhone;
    }

    public String getConsult_lstname() {
        return consult_lstname;
    }

    public void setConsult_lstname(String consult_lstname) {
        this.consult_lstname = consult_lstname;
    }

    public String getConsult_dob() {
        return consult_dob;
    }

    public void setConsult_dob(String consult_dob) {
        this.consult_dob = consult_dob;
    }

    public String getConsult_mobileNo() {
        return consult_mobileNo;
    }

    public void setConsult_mobileNo(String consult_mobileNo) {
        this.consult_mobileNo = consult_mobileNo;
    }

    public String getConsult_available() {
        return consult_available;
    }

    public void setConsult_available(String consult_available) {
        this.consult_available = consult_available;
    }

    public String getConsult_midname() {
        return consult_midname;
    }

    public void setConsult_midname(String consult_midname) {
        this.consult_midname = consult_midname;
    }

    public String getConsult_alterEmail() {
        return consult_alterEmail;
    }

    public void setConsult_alterEmail(String consult_alterEmail) {
        this.consult_alterEmail = consult_alterEmail;
    }

    public String getConsult_ssnNo() {
        return consult_ssnNo;
    }

    public void setConsult_ssnNo(String consult_ssnNo) {
        this.consult_ssnNo = consult_ssnNo;
    }

    public String getConsult_education() {
        return consult_education;
    }

    public void setConsult_education(String consult_education) {
        this.consult_education = consult_education;
    }

    public int getConsult_lcountry() {
        return consult_lcountry;
    }

    public void setConsult_lcountry(int consult_lcountry) {
        this.consult_lcountry = consult_lcountry;
    }

    public String getConsult_Address() {
        return consult_Address;
    }

    public void setConsult_Address(String consult_Address) {
        this.consult_Address = consult_Address;
    }

    public String getConsult_Address2() {
        return consult_Address2;
    }

    public void setConsult_Address2(String consult_Address2) {
        this.consult_Address2 = consult_Address2;
    }

    public String getConsult_City() {
        return consult_City;
    }

    public void setConsult_City(String consult_City) {
        this.consult_City = consult_City;
    }

    public String getConsult_Country() {
        return consult_Country;
    }

    public void setConsult_Country(String consult_Country) {
        this.consult_Country = consult_Country;
    }

    public int getConsult_State() {
        return consult_State;
    }

    public void setConsult_State(int consult_State) {
        this.consult_State = consult_State;
    }

    public String getConsult_Phone() {
        return consult_Phone;
    }

    public void setConsult_Phone(String consult_Phone) {
        this.consult_Phone = consult_Phone;
    }

    public String getAddress_flag() {
        return address_flag;
    }

    public void setAddress_flag(String address_flag) {
        this.address_flag = address_flag;
    }

    public String getConsult_CAddress() {
        return consult_CAddress;
    }

    public void setConsult_CAddress(String consult_CAddress) {
        this.consult_CAddress = consult_CAddress;
    }

    public String getConsult_CAddress2() {
        return consult_CAddress2;
    }

    public void setConsult_CAddress2(String consult_CAddress2) {
        this.consult_CAddress2 = consult_CAddress2;
    }

    public String getConsult_CCity() {
        return consult_CCity;
    }

    public void setConsult_CCity(String consult_CCity) {
        this.consult_CCity = consult_CCity;
    }

    public String getConsult_CCountry() {
        return consult_CCountry;
    }

    public void setConsult_CCountry(String consult_CCountry) {
        this.consult_CCountry = consult_CCountry;
    }

    public int getConsult_CState() {
        return consult_CState;
    }

    public void setConsult_CState(int consult_CState) {
        this.consult_CState = consult_CState;
    }

    public String getConsult_CZip() {
        return consult_CZip;
    }

    public void setConsult_CZip(String consult_CZip) {
        this.consult_CZip = consult_CZip;
    }

    public String getConsult_CPhone() {
        return consult_CPhone;
    }

    public void setConsult_CPhone(String consult_CPhone) {
        this.consult_CPhone = consult_CPhone;
    }

    public int getConsult_industry() {
        return consult_industry;
    }

    public void setConsult_industry(int consult_industry) {
        this.consult_industry = consult_industry;
    }

    public String getConsult_salary() {
        return consult_salary;
    }

    public void setConsult_salary(String consult_salary) {
        this.consult_salary = consult_salary;
    }

    public int getConsult_wcountry() {
        return consult_wcountry;
    }

    public void setConsult_wcountry(int consult_wcountry) {
        this.consult_wcountry = consult_wcountry;
    }

    public int getConsult_organization() {
        return consult_organization;
    }

    public void setConsult_organization(int consult_organization) {
        this.consult_organization = consult_organization;
    }

    public int getConsult_experience() {
        return consult_experience;
    }

    public void setConsult_experience(int consult_experience) {
        this.consult_experience = consult_experience;
    }

    public int getConsult_preferredState() {
        return consult_preferredState;
    }

    public void setConsult_preferredState(int consult_preferredState) {
        this.consult_preferredState = consult_preferredState;
    }

    public String getConsult_jobTitle() {
        return consult_jobTitle;
    }

    public void setConsult_jobTitle(String consult_jobTitle) {
        this.consult_jobTitle = consult_jobTitle;
    }

    public String getConsult_workPhone() {
        return consult_workPhone;
    }

    public void setConsult_workPhone(String consult_workPhone) {
        this.consult_workPhone = consult_workPhone;
    }

    public String getConsult_referredBy() {
        return consult_referredBy;
    }

    public void setConsult_referredBy(String consult_referredBy) {
        this.consult_referredBy = consult_referredBy;
    }

    public String getConsult_comments() {
        return consult_comments;
    }

    public void setConsult_comments(String consult_comments) {
        this.consult_comments = consult_comments;
    }

    public String getConsult_status() {
        return consult_status;
    }

    public void setConsult_status(String consult_status) {
        this.consult_status = consult_status;
    }

    public String getConsult_preferredCountry() {
        return consult_preferredCountry;
    }

    public void setConsult_preferredCountry(String consult_preferredCountry) {
        this.consult_preferredCountry = consult_preferredCountry;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public Map getCountry() {
        return country;
    }

    public void setCountry(Map country) {
        this.country = country;
    }

    public String getConsult_checkAddress() {
        return consult_checkAddress;
    }

    public void setConsult_checkAddress(String consult_checkAddress) {
        this.consult_checkAddress = consult_checkAddress;
    }

    public String getConsult_Zip() {
        return consult_Zip;
    }

    public void setConsult_Zip(String consult_Zip) {
        this.consult_Zip = consult_Zip;
    }

    public String getConsult_add_date() {
        return consult_add_date;
    }

    public void setConsult_add_date(String consult_add_date) {
        this.consult_add_date = consult_add_date;
    }

    public int getConsult_pcountry() {
        return consult_pcountry;
    }

    public void setConsult_pcountry(int consult_pcountry) {
        this.consult_pcountry = consult_pcountry;
    }

//    public String getConsult_skills() {
//        return consult_skills;
//    }
//
//    public void setConsult_skills(String consult_skills) {
//        this.consult_skills = consult_skills;
//    }
    public String getConsult_position() {
        return consult_position;
    }

    public void setConsult_position(String consult_position) {
        this.consult_position = consult_position;
    }

    public String getAddconsult_checkAddress() {
        return addconsult_checkAddress;
    }

    public void setAddconsult_checkAddress(String addconsult_checkAddress) {
        this.addconsult_checkAddress = addconsult_checkAddress;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getConsultAttachment() {
        return consultAttachment;
    }

    public void setConsultAttachment(File consultAttachment) {
        this.consultAttachment = consultAttachment;
    }

    public String getConsultAttachmentContentType() {
        return consultAttachmentContentType;
    }

    public void setConsultAttachmentContentType(String consultAttachmentContentType) {
        this.consultAttachmentContentType = consultAttachmentContentType;
    }

    public String getConsultAttachmentFileName() {
        return consultAttachmentFileName;
    }

    public void setConsultAttachmentFileName(String consultAttachmentFileName) {
        this.consultAttachmentFileName = consultAttachmentFileName;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityPriority() {
        return activityPriority;
    }

    public void setActivityPriority(String activityPriority) {
        this.activityPriority = activityPriority;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityComments() {
        return activityComments;
    }

    public void setActivityComments(String activityComments) {
        this.activityComments = activityComments;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public int getActivityCratedBy() {
        return activityCratedBy;
    }

    public void setActivityCratedBy(int activityCratedBy) {
        this.activityCratedBy = activityCratedBy;
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getActivityRelation() {
        return activityRelation;
    }

    public void setActivityRelation(String activityRelation) {
        this.activityRelation = activityRelation;
    }

    public List getConsultantActivityDetails() {
        return consultantActivityDetails;
    }

    public void setConsultantActivityDetails(List consultantActivityDetails) {
        this.consultantActivityDetails = consultantActivityDetails;
    }

    public String getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    public List getConsultantListVTO() {
        return consultantListVTO;
    }

    public void setConsultantListVTO(List consultantListVTO) {
        this.consultantListVTO = consultantListVTO;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public int getEmpIdTechReview() {
        return empIdTechReview;
    }

    public void setEmpIdTechReview(int empIdTechReview) {
        this.empIdTechReview = empIdTechReview;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getReviewAlertDate() {
        return reviewAlertDate;
    }

    public void setReviewAlertDate(String reviewAlertDate) {
        this.reviewAlertDate = reviewAlertDate;
    }

    public String getAlertMessageTechReview() {
        return alertMessageTechReview;
    }

    public void setAlertMessageTechReview(String alertMessageTechReview) {
        this.alertMessageTechReview = alertMessageTechReview;
    }

    public List getConsultantList() {
        return consultantList;
    }

    public void setConsultantList(List consultantList) {
        this.consultantList = consultantList;
    }

    public String getTechReviewStatus() {
        return techReviewStatus;
    }

    public void setTechReviewStatus(String techReviewStatus) {
        this.techReviewStatus = techReviewStatus;
    }

    public String getReviewStartDate() {
        return reviewStartDate;
    }

    public void setReviewStartDate(String reviewStartDate) {
        this.reviewStartDate = reviewStartDate;
    }

    public String getTechSkill() {
        return techSkill;
    }

    public void setTechSkill(String techSkill) {
        this.techSkill = techSkill;
    }

    public String getDomainSkill() {
        return domainSkill;
    }

    public void setDomainSkill(String domainSkill) {
        this.domainSkill = domainSkill;
    }

    public String getComSkill() {
        return comSkill;
    }

    public void setComSkill(String comSkill) {
        this.comSkill = comSkill;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getConsultantComments() {
        return consultantComments;
    }

    public void setConsultantComments(String consultantComments) {
        this.consultantComments = consultantComments;
    }

    public String getFinalTechReviewStatus() {
        return finalTechReviewStatus;
    }

    public void setFinalTechReviewStatus(String finalTechReviewStatus) {
        this.finalTechReviewStatus = finalTechReviewStatus;
    }

    public String getTechTitle() {
        return techTitle;
    }

    public void setTechTitle(String techTitle) {
        this.techTitle = techTitle;
    }

    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }

    public String getConsultFlag() {
        return consultFlag;
    }

    public void setConsultFlag(String consultFlag) {
        this.consultFlag = consultFlag;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getAcc_attachment_id() {
        return acc_attachment_id;
    }

    public void setAcc_attachment_id(int acc_attachment_id) {
        this.acc_attachment_id = acc_attachment_id;
    }

    public Map getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(Map permanentState) {
        this.permanentState = permanentState;
    }

    public Map getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Map currentState) {
        this.currentState = currentState;
    }

    public Map getPreState() {
        return preState;
    }

    public void setPreState(Map preState) {
        this.preState = preState;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getReviewEndDate() {
        return reviewEndDate;
    }

    public void setReviewEndDate(String reviewEndDate) {
        this.reviewEndDate = reviewEndDate;
    }

    public String getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(String requirementStatus) {
        this.requirementStatus = requirementStatus;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getMailIds() {
        return mailIds;
    }

    public void setMailIds(String mailIds) {
        this.mailIds = mailIds;
    }

    public String getConSkills() {
        return conSkills;
    }

    public void setConSkills(String conSkills) {
        this.conSkills = conSkills;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getForwardedByName() {
        return forwardedByName;
    }

    public void setForwardedByName(String forwardedByName) {
        this.forwardedByName = forwardedByName;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getForwardedToName() {
        return forwardedToName;
    }

    public void setForwardedToName(String forwardedToName) {
        this.forwardedToName = forwardedToName;
    }

    public MailManager getMailManager() {
        return mailManager;
    }

    public void setMailManager(MailManager mailManager) {
        this.mailManager = mailManager;
    }

    public String getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(String customerFlag) {
        this.customerFlag = customerFlag;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getEmpIdTechReview2() {
        return empIdTechReview2;
    }

    public void setEmpIdTechReview2(int empIdTechReview2) {
        this.empIdTechReview2 = empIdTechReview2;
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public String getEmpEmail2() {
        return empEmail2;
    }

    public void setEmpEmail2(String empEmail2) {
        this.empEmail2 = empEmail2;
    }

    public String getTechSearch() {
        return techSearch;
    }

    public void setTechSearch(String techSearch) {
        this.techSearch = techSearch;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getJdId() {
        return jdId;
    }

    public void setJdId(String jdId) {
        this.jdId = jdId;
    }

    public String getResumeDownlaod() {
        return resumeDownlaod;
    }

    public void setResumeDownlaod(String resumeDownlaod) {
        this.resumeDownlaod = resumeDownlaod;
    }

    public String getTechReviewFlag() {
        return techReviewFlag;
    }

    public void setTechReviewFlag(String techReviewFlag) {
        this.techReviewFlag = techReviewFlag;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    private int typeOfUser;

    public int getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(int typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Map getReqCreatedByMap() {
        return reqCreatedByMap;
    }

    public void setReqCreatedByMap(Map reqCreatedByMap) {
        this.reqCreatedByMap = reqCreatedByMap;
    }

    public String getMigrationStatus() {
        return migrationStatus;
    }

    public void setMigrationStatus(String migrationStatus) {
        this.migrationStatus = migrationStatus;
    }

    public int getReq_Id() {
        return req_Id;
    }

    public void setReq_Id(int req_Id) {
        this.req_Id = req_Id;
    }

    public String getMigrationEmailId() {
        return migrationEmailId;
    }

    public void setMigrationEmailId(String migrationEmailId) {
        this.migrationEmailId = migrationEmailId;
    }

    public int getContechId() {
        return contechId;
    }

    public void setContechId(int contechId) {
        this.contechId = contechId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(String targetRate) {
        this.targetRate = targetRate;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }

    public int getVen_id() {
        return ven_id;
    }

    public void setVen_id(int ven_id) {
        this.ven_id = ven_id;
    }

    public String getVendorDocs() {
        return vendorDocs;
    }

    public void setVendorDocs(String vendorDocs) {
        this.vendorDocs = vendorDocs;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAccountSearchOrgId() {
        return AccountSearchOrgId;
    }

    public void setAccountSearchOrgId(String AccountSearchOrgId) {
        this.AccountSearchOrgId = AccountSearchOrgId;
    }

    public int getViewAccountID() {
        return viewAccountID;
    }

    public void setViewAccountID(int viewAccountID) {
        this.viewAccountID = viewAccountID;
    }

    public String getEditValidity() {
        return editValidity;
    }

    public void setEditValidity(String editValidity) {
        this.editValidity = editValidity;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAttachment_id_edit() {
        return attachment_id_edit;
    }

    public void setAttachment_id_edit(int attachment_id_edit) {
        this.attachment_id_edit = attachment_id_edit;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getConsult_facebookId() {
        return consult_facebookId;
    }

    public void setConsult_facebookId(String consult_facebookId) {
        this.consult_facebookId = consult_facebookId;
    }

    public String getConsult_twitterId() {
        return consult_twitterId;
    }

    public void setConsult_twitterId(String consult_twitterId) {
        this.consult_twitterId = consult_twitterId;
    }

    public String getConsult_linkedInId() {
        return consult_linkedInId;
    }

    public void setConsult_linkedInId(String consult_linkedInId) {
        this.consult_linkedInId = consult_linkedInId;
    }

    public String getAttachmentTitle() {
        return attachmentTitle;
    }

    public void setAttachmentTitle(String attachmentTitle) {
        this.attachmentTitle = attachmentTitle;
    }

    public String getAttachmentComments() {
        return attachmentComments;
    }

    public void setAttachmentComments(String attachmentComments) {
        this.attachmentComments = attachmentComments;
    }

    public String getEditTitle() {
        return editTitle;
    }

    public void setEditTitle(String editTitle) {
        this.editTitle = editTitle;
    }

    public String getEditattachmentComments() {
        return editattachmentComments;
    }

    public void setEditattachmentComments(String editattachmentComments) {
        this.editattachmentComments = editattachmentComments;
    }

    public Map getSkillValuesMap() {
        return skillValuesMap;
    }

    public void setSkillValuesMap(Map skillValuesMap) {
        this.skillValuesMap = skillValuesMap;
    }

    public String getSkillCategoryValueString() {
        return skillCategoryValueString;
    }

    public void setSkillCategoryValueString(String skillCategoryValueString) {
        this.skillCategoryValueString = skillCategoryValueString;
    }

    public String[] getSkillCategoryValueList() {
        return skillCategoryValueList;
    }

    public void setSkillCategoryValueList(String[] skillCategoryValueList) {
        this.skillCategoryValueList = skillCategoryValueList;
    }

    public String getSkillValues() {
        return skillValues;
    }

    public void setSkillValues(String skillValues) {
        this.skillValues = skillValues;
    }
    //Added for Relocation

    public String getConsult_relocation() {

        return consult_relocation;
    }

    public void setConsult_relocation(String consult_relocation) {

        this.consult_relocation = consult_relocation;
    }

    public String getReqSkillSet() {
        return reqSkillSet;
    }

    public void setReqSkillSet(String reqSkillSet) {
        this.reqSkillSet = reqSkillSet;
    }

    public String getTechReviewComments() {
        return techReviewComments;
    }

    public void setTechReviewComments(String techReviewComments) {
        this.techReviewComments = techReviewComments;
    }

    public String getTechReviewQuestions() {
        return techReviewQuestions;
    }

    public void setTechReviewQuestions(String techReviewQuestions) {
        this.techReviewQuestions = techReviewQuestions;
    }

    public String getTechReviewSeverity() {
        return techReviewSeverity;
    }

    public void setTechReviewSeverity(String techReviewSeverity) {
        this.techReviewSeverity = techReviewSeverity;
    }

    public String getTechReviewTime() {
        return techReviewTime;
    }

    public void setTechReviewTime(String techReviewTime) {
        this.techReviewTime = techReviewTime;
    }

    public String getSkillCategoryArry() {
        return skillCategoryArry;
    }

    public void setSkillCategoryArry(String skillCategoryArry) {
        this.skillCategoryArry = skillCategoryArry;
    }

    public String getSkill1Questions() {
        return skill1Questions;
    }

    public void setSkill1Questions(String skill1Questions) {
        this.skill1Questions = skill1Questions;
    }

    public String getSkill2Questions() {
        return skill2Questions;
    }

    public void setSkill2Questions(String skill2Questions) {
        this.skill2Questions = skill2Questions;
    }

    public String getSkill3Questions() {
        return skill3Questions;
    }

    public void setSkill3Questions(String skill3Questions) {
        this.skill3Questions = skill3Questions;
    }

    public String getSkill4Questions() {
        return skill4Questions;
    }

    public void setSkill4Questions(String skill4Questions) {
        this.skill4Questions = skill4Questions;
    }

    public String getSkill5Questions() {
        return skill5Questions;
    }

    public void setSkill5Questions(String skill5Questions) {
        this.skill5Questions = skill5Questions;
    }

    public String getSkill6Questions() {
        return skill6Questions;
    }

    public void setSkill6Questions(String skill6Questions) {
        this.skill6Questions = skill6Questions;
    }

    public String getSkill7Questions() {
        return skill7Questions;
    }

    public void setSkill7Questions(String skill7Questions) {
        this.skill7Questions = skill7Questions;
    }

    public String getSkill8Questions() {
        return skill8Questions;
    }

    public void setSkill8Questions(String skill8Questions) {
        this.skill8Questions = skill8Questions;
    }

    public String getSkill9Questions() {
        return skill9Questions;
    }

    public void setSkill9Questions(String skill9Questions) {
        this.skill9Questions = skill9Questions;
    }

    public String getSkill10Questions() {
        return skill10Questions;
    }

    public void setSkill10Questions(String skill10Questions) {
        this.skill10Questions = skill10Questions;
    }

    public Map getPsychoSkillValuesMap() {
        return psychoSkillValuesMap;
    }

    public void setPsychoSkillValuesMap(Map psychoSkillValuesMap) {
        this.psychoSkillValuesMap = psychoSkillValuesMap;
    }

    public int getEnameIdForRecruitment() {
        return enameIdForRecruitment;
    }

    public void setEnameIdForRecruitment(int enameIdForRecruitment) {
        this.enameIdForRecruitment = enameIdForRecruitment;
    }

    public String getEnameForRecruitment() {
        return enameForRecruitment;
    }

    public void setEnameForRecruitment(String enameForRecruitment) {
        this.enameForRecruitment = enameForRecruitment;
    }

    public String getContechNote() {
        return contechNote;
    }

    public void setContechNote(String contechNote) {
        this.contechNote = contechNote;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getConsultantVisa() {
        return consultantVisa;
    }

    public void setConsultantVisa(String consultantVisa) {
        this.consultantVisa = consultantVisa;
    }

    public File getVisaAttachment() {
        return visaAttachment;
    }

    public void setVisaAttachment(File visaAttachment) {
        this.visaAttachment = visaAttachment;
    }

    public String getVisaAttachmentContentType() {
        return visaAttachmentContentType;
    }

    public void setVisaAttachmentContentType(String visaAttachmentContentType) {
        this.visaAttachmentContentType = visaAttachmentContentType;
    }

    public String getVisaAttachmentFileName() {
        return visaAttachmentFileName;
    }

    public void setVisaAttachmentFileName(String visaAttachmentFileName) {
        this.visaAttachmentFileName = visaAttachmentFileName;
    }

    public String getVisaAttachmentPath() {
        return visaAttachmentPath;
    }

    public void setVisaAttachmentPath(String visaAttachmentPath) {
        this.visaAttachmentPath = visaAttachmentPath;
    }

    public String getVenEmail() {
        return venEmail;
    }

    public void setVenEmail(String venEmail) {
        this.venEmail = venEmail;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getConsultantIdProof() {
        return consultantIdProof;
    }

    public void setConsultantIdProof(String consultantIdProof) {
        this.consultantIdProof = consultantIdProof;
    }

    public String getVendorcomments() {
        return vendorcomments;
    }

    public void setVendorcomments(String vendorcomments) {
        this.vendorcomments = vendorcomments;
    }
    
    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
    
}
