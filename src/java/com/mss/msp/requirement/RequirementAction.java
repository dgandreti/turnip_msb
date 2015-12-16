/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.requirement;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class RequirementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private int primaryRole;
    private String downloadFlag;
    private int userSessionId;
    private String RequirementFrom;
    private String RequirementTo;
    private String RequirementTargetRate;
    private String RequirementDuration;
    private int RequirementContact1;
    private String RequirementReason;
    private String RequirementPresales1;
    private String RequirementType;
    private int RequirementContact2;
    private String RequirementTaxTerm;
    private String RequirementPresales2;
    private String RequirementPractice;
    private String RequirementName;
    private int RequirementNoofResources;
    private String RequirementStatus;
    private String RequirementLocation;
    private String RequirementContactNo;
    private String RequirementState;
    private String RequirementCountry;
    private String RequirementAddress;
    private String RequirementResponse;
    private String RequirementJobdesc;
    private String RequirementSkills;
    private String RequirementDescription;
    private String RequirementComments;
    private int RequirementYears;
    private Map contacts;
    private int orgId;
    private int id;
    private String resultType;
    private Map countryMap;
    private Map experienceMap;
    private Map employeeNames;
    private String requirementId;
    private String requirementExp;
    private Map typesTiers;
    //for requirement retrieval//
    private String requirementskDetails;
    private int accountSearchID;
    //for requirement retrieval//
    //for requirement search//
    private String requirementSkill;
    private String requirementStatus;
    private String reqStart;
    private String reqEnd;
    private String jobTitle;
    private int sessionOrgId;
    //for requirement search//
    private Map recruitmentMap;
    private Map presalesMap;
    private String accountName;
    //for consultance existance
    private String conEmail;
    //for getting data of the cosultant in req_con_rel table
    private String conId;
    private String reqId;
    private String proofType;
    private String ppno;
    private String pan;
    private String proofValue;
    private String accountFlag;
    private String account_name;
    //for vendor viewing
    private String vendor;
    //06082015 vars created by rk 
    private String mailIds;
    private String reqName;
    private String reqStartDate;
    private String reqEndDate;
    private String reqResources;
    private String reqDesc;
    private String customerFlag;
    private String jdId;
    private String ratePerHour;
    private String consult_name;
    private String consult_email;
    private String consult_skill;
    private String consult_phno;
    private String reqFlag;
    private String billingContact;
    private String requirementDurationDescriptor;
    //for dashBoard CSR
    private String dashYears;
    private String csrCustomer;
    private String accountId;
    private List customerDashBoardList;
    private int year;
    private String dashMonths;
    private String typeOfUser;
    private Map reqCategory;
    private int reqCategoryValue;
    private String targetRate;
    private String requirementMaxRate;
    private String taxTerm;
    private Map skillValuesMap;
    private String skillCategoryArry;
    private String preSkillCategoryArry;
    private List skillSetList;
    private Map preSkillValuesMap;
    private String reqSkillSet;
    private String skillList;
    private String billingtype;
    private String vendorComments;
    private String ssnNo;
    private String requirementQualification;

    public RequirementAction() {
    }
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultMessage;
    private DataSourceDataProvider dataSourceDataProvider;
    private RequirementVTO requirementVTO;
    private String RequirementPreferredSkills;
    private MailManager mailManager = new MailManager();

    public String addRequirements() {
        resultMessage = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("$$$$$$$$$$$$$$$$$$$$ IN ACTION.......> " + getAccountSearchID());
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                System.out.println("user id" + getUserSessionId());
                setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                // System.out.println("orgid is" + getOrgId());
                setContacts(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                // System.out.println("contacts are" + getContacts());
                setCountryMap(dataSourceDataProvider.getInstance().getCountryNames());
                setExperienceMap(dataSourceDataProvider.getInstance().getYearsOfExp());
                setRecruitmentMap(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(getOrgId()));
                setPresalesMap(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                //  session.get("");

                setSkillValuesMap(skillsmap);
                setPreSkillValuesMap(skillsmap);
                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                // setPreSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                /* 
                 * in the belo liine 1 indeicates recruitment from lkusr_grp table and from there we are classifying  
                 *groups for the users in an oraganization
                 */
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;

        }
        return resultMessage;
    }

    public String doCopyRequirement() {
        resultMessage = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("$$$$$$$$$$$$$$$$$$$$ IN ACTION.......> " + getAccountSearchID());
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                System.out.println("user id" + getUserSessionId());
                setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                // System.out.println("orgid is" + getOrgId());
                setContacts(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                // System.out.println("contacts are" + getContacts());
                setCountryMap(dataSourceDataProvider.getInstance().getCountryNames());
                setExperienceMap(dataSourceDataProvider.getInstance().getYearsOfExp());
                setRecruitmentMap(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(getOrgId()));
                setPresalesMap(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setPreSkillValuesMap(skillsmap);
                requirementVTO = ServiceLocator.getRequirementService().editrequirement(getRequirementId(), skillsmap);
                //  setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                //  setPreSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                requirementVTO = ServiceLocator.getRequirementService().editrequirement(getRequirementId(), skillsmap);
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;

        }
        return resultMessage;
    }

    public String addRequirementDetails() {
        resultMessage = LOGIN;
        int requirement;
        try {
            System.out.println("hello Requirement");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));

                setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                System.out.println("account Search id" + getAccountSearchID());
                requirement = ServiceLocator.getRequirementService().addRequirementDetails(this, getOrgId());
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + requirement + "");

                return null;

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;

        }
        return resultMessage;
    }

    public String setrequirementedit() {
        String resultMessage;
        resultMessage = LOGIN;

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int org_id = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                String typeOfUser = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                setContacts(dataSourceDataProvider.getInstance().getSalesTeam(org_id));
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                setCountryMap(dataSourceDataProvider.getInstance().getCountryNames());
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setPreSkillValuesMap(skillsmap);
                //  setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                //  setPreSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                if (typeOfUser.equals("VC") || typeOfUser.equals("SA")) {
                    // System.out.println("------User Type is Vendor----->");    
                    //System.out.println("requirement id----->"+getRequirementId());    
                    int orgId = ServiceLocator.getRequirementService().getOrgIdCustomer(getRequirementId());
                    // System.out.println("orgId------->"+orgId);
                    setEmployeeNames(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(orgId));

                } else {
                    //System.out.println("--------user type is Customer----->");        
                    setEmployeeNames(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(org_id));
                }
                setExperienceMap(dataSourceDataProvider.getInstance().getYearsOfExp());
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                typesTiers = com.mss.msp.util.DataSourceDataProvider.getInstance().getVendorTierTypes();

                requirementVTO = ServiceLocator.getRequirementService().editrequirement(getRequirementId(), skillsmap);
                if (typeOfUser.equals("VC")) {
                    System.out.println("vendor Requirement");
                    resultMessage = INPUT;
                } else {
                    resultMessage = SUCCESS;
                }
            } catch (Exception ex) {

                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }// Session validator if END
        return resultMessage;
    }

    public String updateRequirements() {
        String resultMessage;
        resultMessage = LOGIN;
        int updated = 0;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("ddddd" + getRequirementId() + "     " + getRequirementFrom() + "   " + getRequirementAddress());
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                System.out.println("in update requirement");
                int org_id = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());

                // setSales(dataSourceDataProvider.getInstance().getSalesTeam(org_id));

                updated = ServiceLocator.getRequirementService().updateRequirement(userid, this);
                System.out.println("updated" + updated);

            } catch (Exception ex) {

                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultType = ERROR;
            }
        }// Session validator if END
        return null;
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
                System.out.println("############# IN REQUIREMENT ACTIONNNNNNNNNNNNNNNNNNNNNNNNNN");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                requirementskDetails = ServiceLocator.getRequirementService().getRequirementDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(requirementskDetails);
                resultType = null;
            }
        } catch (Exception ex) {
            System.out.println("Exception in req search action1-->" + ex.getMessage());
            resultType = ERROR;
        }
        return null;
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
    public String getReqDetailsBySearch() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String list = ServiceLocator.getRequirementService().getReqDetailsBySearch(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
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
     * @getRequirementDetails() method is used to get Requirement details of
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String getSkillDetaisls() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String skills = ServiceLocator.getRequirementService().getSkillDetaisls(this);
                skills = skills.replaceAll("<br/>", "\n");
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(skills);
                resultMessage = null;
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
     * @getRequirementDetails() method is used to get Requirement details of
     * account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    private String fileContentType;
    private String fileFileName;
    private String filesPath;
    private File file;
    private String resourceType;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

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
    private String propsedSkills;

    public String getPropsedSkills() {
        return propsedSkills;
    }

    public void setPropsedSkills(String propsedSkills) {
        this.propsedSkills = propsedSkills;
    }

    public String storeProofData() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

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

                System.out.println("this is printing file path-->" + this.getFilesPath() + "...." + this.getFileFileName());
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                /* if (getProofType().equalsIgnoreCase("PP")) {
                 System.out.println("ProofType is>>>>>>>" + getProofType());
                 setProofValue(getPpno());
                 }
                 if (getProofType().equalsIgnoreCase("PN")) {
                 System.out.println("ProofType is>>>>>>>" + getProofType());
                 setProofValue(getPan());
                 }
                 */
                setProofValue("");
                setProofValue("");
                String proof = ServiceLocator.getRequirementService().storeProofData(httpServletRequest, this);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(proof);
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
     * @getRequirementDetails() method is used to get Requirement details of
     * account
     *
     * @Author:praveen <pkatru@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    private int reqCreatedBy;

    public String getSearchRequirementsList() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("Account Flag in search action>>>>>>test--->>>>>" + getAccountFlag() + "--accid-->" + getAccountSearchID());
                setPrimaryRole(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                String list = ServiceLocator.getRequirementService().getSearchRequirementsList(httpServletRequest, this, skillsmap);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
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
     * @getRecruiterOverlay() method is used to get Requirement details of
     * account
     *
     * @Author:praveen <pkatru@miraclesoft.com>
     *
     * @Created Date:05/12/2015
     *
     **************************************
     */
    public String getRecruiterOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String list = ServiceLocator.getRequirementService().getRecruiterOverlay(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }

        return null;
    }

    public String getSkillOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String list = ServiceLocator.getRequirementService().getSkillOverlay(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }

        return null;
    }

    public String getPreSkillOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String list = ServiceLocator.getRequirementService().getPreSkillOverlay(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
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
     * @doReleaseRequirements() release reuirements for tier 1
     *
     *
     * @Author:praveen <pkatru@miraclesoft.com>
     *
     * @Created Date:06/02/2015
     *
     **************************************
     */
    public String doReleaseRequirements() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setAccount_name(dataSourceDataProvider.getInstance().getAccountNameById(getSessionOrgId()));
                System.out.println("In Action doReleaseRequirements-->TaxTerm--->" + getTaxTerm());
                System.out.println("In Action doReleaseRequirements-->reqId-->" + getRequirementId());

                int record = ServiceLocator.getRequirementService().doReleaseRequirements(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + record + "");
                resultMessage = null;
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
     * @getRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * @Author ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:15/06/2015
     *
     **************************************
     */
    public String getRequirementDashBoardDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                String csrReq = ServiceLocator.getRequirementService().getRequirementDashBoardDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
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
     * @getRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * @Author ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:15/06/2015
     *
     **************************************
     */
    public String getRequirementDashBoardDetailsOnOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                String csrReq = ServiceLocator.getRequirementService().getRequirementDashBoardDetailsOnOverlay(this);
                //please change query after uncomment above line.
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
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
     * @getVendorRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * @Author ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:15/06/2015
     *
     **************************************
     */
    public String getVendorRequirementDashBoardDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String csrReq = null;//ServiceLocator.getRequirementService().getVendorRequirementDashBoardDetails(httpServletRequest, this);
                //please change query after uncomment it and remove null;
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
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
     * @getVendorRequirementsDashBoard() method is used to get Requirement
     * details of account
     *
     * @Author ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:15/06/2015
     *
     **************************************
     */
    public String getVendorRequirementsDashBoard() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String csrReq = ServiceLocator.getRequirementService().getVendorRequirementsDashBoard(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
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
     * @getRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * @Author ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:15/06/2015
     *
     **************************************
     */
    public String getVendorDashBoardDetailsOnOverlay() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                System.out.println("IN ACTION getVendorDashBoardDetailsOnOverlay()");
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String csrReq = ServiceLocator.getRequirementService().getVendorDashBoardDetailsOnOverlay(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
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
     * @customerDashBoardDetails() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String customerDashBoardDetails() throws ServiceLocatorException {
        String resulttype = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            System.out.println("ENTERED IN TO THE ACTION FOR CUSTOMER DASHBOARD******************************************************");
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            customerDashBoardList = (ServiceLocator.getRequirementService().getDefaultCustomerRequirementDashBoardDetails(this));
            System.out.println(">>>>>>>>ACTION>>>>>>" + getCustomerDashBoardList().toString());
            setYear(Calendar.getInstance().get(Calendar.YEAR));
            resulttype = SUCCESS;
        }
        return resulttype;
    }

    /**
     * *************************************
     *
     * @getRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * @Author ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:15/06/2015
     *
     **************************************
     */
    public String getCustomerRequirementDashBoardDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String csrReq = ServiceLocator.getRequirementService().getCustomerRequirementDashBoardDetails(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
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
     * @getPreferedSkillDetails() method is used to get Requirement prefered
     * skill details of account
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/06/2015
     *
     **************************************
     */
    public String getPreferedSkillDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String skills = ServiceLocator.getRequirementService().getPreferedSkillDetails(this);
                skills = skills.replaceAll("<br/>", "\n");
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(skills);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        return null;
    }

    @Override
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

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getRequirementFrom() {
        return RequirementFrom;
    }

    public void setRequirementFrom(String RequirementFrom) {
        this.RequirementFrom = RequirementFrom;
    }

    public String getRequirementTo() {
        return RequirementTo;
    }

    public void setRequirementTo(String RequirementTo) {
        this.RequirementTo = RequirementTo;
    }

    public String getRequirementTargetRate() {
        return RequirementTargetRate;
    }

    public void setRequirementTargetRate(String RequirementTargetRate) {
        this.RequirementTargetRate = RequirementTargetRate;
    }

    public String getRequirementDuration() {
        return RequirementDuration;
    }

    public void setRequirementDuration(String RequirementDuration) {
        this.RequirementDuration = RequirementDuration;
    }

    public int getRequirementContact1() {
        return RequirementContact1;
    }

    public void setRequirementContact1(int RequirementContact1) {
        this.RequirementContact1 = RequirementContact1;
    }

    public String getRequirementReason() {
        return RequirementReason;
    }

    public void setRequirementReason(String RequirementReason) {
        this.RequirementReason = RequirementReason;
    }

    public String getRequirementPresales1() {
        return RequirementPresales1;
    }

    public void setRequirementPresales1(String RequirementPresales1) {
        this.RequirementPresales1 = RequirementPresales1;
    }

    public String getRequirementType() {
        return RequirementType;
    }

    public void setRequirementType(String RequirementType) {
        this.RequirementType = RequirementType;
    }

    public int getRequirementContact2() {
        return RequirementContact2;
    }

    public void setRequirementContact2(int RequirementContact2) {
        this.RequirementContact2 = RequirementContact2;
    }

    public String getRequirementTaxTerm() {
        return RequirementTaxTerm;
    }

    public void setRequirementTaxTerm(String RequirementTaxTerm) {
        this.RequirementTaxTerm = RequirementTaxTerm;
    }

    public String getRequirementPresales2() {
        return RequirementPresales2;
    }

    public void setRequirementPresales2(String RequirementPresales2) {
        this.RequirementPresales2 = RequirementPresales2;
    }

    public String getRequirementPractice() {
        return RequirementPractice;
    }

    public void setRequirementPractice(String RequirementPractice) {
        this.RequirementPractice = RequirementPractice;
    }

    public String getRequirementName() {
        return RequirementName;
    }

    public void setRequirementName(String RequirementName) {
        this.RequirementName = RequirementName;
    }

    public int getRequirementNoofResources() {
        return RequirementNoofResources;
    }

    public void setRequirementNoofResources(int RequirementNoofResources) {
        this.RequirementNoofResources = RequirementNoofResources;
    }

    public String getRequirementStatus() {
        return RequirementStatus;
    }

    public void setRequirementStatus(String RequirementStatus) {
        this.RequirementStatus = RequirementStatus;
    }

    public String getRequirementLocation() {
        return RequirementLocation;
    }

    public void setRequirementLocation(String RequirementLocation) {
        this.RequirementLocation = RequirementLocation;
    }

    public String getRequirementContactNo() {
        return RequirementContactNo;
    }

    public void setRequirementContactNo(String RequirementContactNo) {
        this.RequirementContactNo = RequirementContactNo;
    }

    public String getRequirementState() {
        return RequirementState;
    }

    public void setRequirementState(String RequirementState) {
        this.RequirementState = RequirementState;
    }

    public String getRequirementCountry() {
        return RequirementCountry;
    }

    public void setRequirementCountry(String RequirementCountry) {
        this.RequirementCountry = RequirementCountry;
    }

    public String getRequirementAddress() {
        return RequirementAddress;
    }

    public void setRequirementAddress(String RequirementAddress) {
        this.RequirementAddress = RequirementAddress;
    }

    public String getRequirementResponse() {
        return RequirementResponse;
    }

    public void setRequirementResponse(String RequirementResponse) {
        this.RequirementResponse = RequirementResponse;
    }

    public String getRequirementJobdesc() {
        return RequirementJobdesc;
    }

    public void setRequirementJobdesc(String RequirementJobdesc) {
        this.RequirementJobdesc = RequirementJobdesc;
    }

    public String getRequirementSkills() {
        return RequirementSkills;
    }

    public void setRequirementSkills(String RequirementSkills) {
        this.RequirementSkills = RequirementSkills;
    }

    public String getRequirementDescription() {
        return RequirementDescription;
    }

    public void setRequirementDescription(String RequirementDescription) {
        this.RequirementDescription = RequirementDescription;
    }

    public String getRequirementComments() {
        return RequirementComments;
    }

    public Map getContacts() {
        return contacts;
    }

    public void setContacts(Map contacts) {
        this.contacts = contacts;
    }

    public void setRequirementComments(String RequirementComments) {
        this.RequirementComments = RequirementComments;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Map getCountryMap() {
        return countryMap;
    }

    public void setCountryMap(Map countryMap) {
        this.countryMap = countryMap;
    }

    public int getRequirementYears() {
        return RequirementYears;
    }

    public void setRequirementYears(int RequirementYears) {
        this.RequirementYears = RequirementYears;
    }

    public Map getExperienceMap() {
        return experienceMap;
    }

    public void setExperienceMap(Map experienceMap) {
        this.experienceMap = experienceMap;
    }

    public Map getEmployeeNames() {
        return employeeNames;
    }

    public void setEmployeeNames(Map employeeNames) {
        this.employeeNames = employeeNames;
    }

    public RequirementVTO getRequirementVTO() {
        return requirementVTO;
    }

    public void setRequirementVTO(RequirementVTO requirementVTO) {
        this.requirementVTO = requirementVTO;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementExp() {
        return requirementExp;
    }

    public void setRequirementExp(String requirementExp) {
        this.requirementExp = requirementExp;
    }

    public Map getTypesTiers() {
        return typesTiers;
    }

    public void setTypesTiers(Map typesTiers) {
        this.typesTiers = typesTiers;
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

    public String getRequirementSkill() {
        return requirementSkill;
    }

    public void setRequirementSkill(String requirementSkill) {
        this.requirementSkill = requirementSkill;
    }

    public String getReqStart() {
        return reqStart;
    }

    public void setReqStart(String reqStart) {
        this.reqStart = reqStart;
    }

    public String getReqEnd() {
        return reqEnd;
    }

    public void setReqEnd(String reqEnd) {
        this.reqEnd = reqEnd;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRequirementPreferredSkills() {
        return RequirementPreferredSkills;
    }

    public void setRequirementPreferredSkills(String RequirementPreferredSkills) {
        this.RequirementPreferredSkills = RequirementPreferredSkills;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public Map getRecruitmentMap() {
        return recruitmentMap;
    }

    public void setRecruitmentMap(Map recruitmentMap) {
        this.recruitmentMap = recruitmentMap;
    }

    public Map getPresalesMap() {
        return presalesMap;
    }

    public void setPresalesMap(Map presalesMap) {
        this.presalesMap = presalesMap;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getPpno() {
        return ppno;
    }

    public void setPpno(String ppno) {
        this.ppno = ppno;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getProofValue() {
        return proofValue;
    }

    public void setProofValue(String proofValue) {
        this.proofValue = proofValue;
    }

    public String getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getMailIds() {
        return mailIds;
    }

    public void setMailIds(String mailIds) {
        this.mailIds = mailIds;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqStartDate() {
        return reqStartDate;
    }

    public void setReqStartDate(String reqStartDate) {
        this.reqStartDate = reqStartDate;
    }

    public String getReqEndDate() {
        return reqEndDate;
    }

    public void setReqEndDate(String reqEndDate) {
        this.reqEndDate = reqEndDate;
    }

    public String getReqResources() {
        return reqResources;
    }

    public void setReqResources(String reqResources) {
        this.reqResources = reqResources;
    }

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    public String getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(String customerFlag) {
        this.customerFlag = customerFlag;
    }

    public String getJdId() {
        return jdId;
    }

    public void setJdId(String jdId) {
        this.jdId = jdId;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(String ratePerHour) {
        this.ratePerHour = ratePerHour;
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

    public String getReqFlag() {
        return reqFlag;
    }

    public void setReqFlag(String reqFlag) {
        this.reqFlag = reqFlag;
    }

    public String getBillingContact() {
        return billingContact;
    }

    public void setBillingContact(String billingContact) {
        this.billingContact = billingContact;
    }

    public String getRequirementDurationDescriptor() {
        return requirementDurationDescriptor;
    }

    public void setRequirementDurationDescriptor(String requirementDurationDescriptor) {
        this.requirementDurationDescriptor = requirementDurationDescriptor;
    }

    public String getDashYears() {
        return dashYears;
    }

    public void setDashYears(String dashYears) {
        this.dashYears = dashYears;
    }

    public String getCsrCustomer() {
        return csrCustomer;
    }

    public void setCsrCustomer(String csrCustomer) {
        this.csrCustomer = csrCustomer;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List getCustomerDashBoardList() {
        return customerDashBoardList;
    }

    public void setCustomerDashBoardList(List customerDashBoardList) {
        this.customerDashBoardList = customerDashBoardList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDashMonths() {
        return dashMonths;
    }

    public void setDashMonths(String dashMonths) {
        this.dashMonths = dashMonths;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Map getReqCategory() {
        return reqCategory;
    }

    public void setReqCategory(Map reqCategory) {
        this.reqCategory = reqCategory;
    }

    public int getReqCategoryValue() {
        return reqCategoryValue;
    }

    public void setReqCategoryValue(int reqCategoryValue) {
        this.reqCategoryValue = reqCategoryValue;
    }

    public int getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(int primaryRole) {
        this.primaryRole = primaryRole;
    }

    public int getReqCreatedBy() {
        return reqCreatedBy;
    }

    public void setReqCreatedBy(int reqCreatedBy) {
        this.reqCreatedBy = reqCreatedBy;
    }

    public String getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(String targetRate) {
        this.targetRate = targetRate;
    }

    public String getRequirementMaxRate() {
        return requirementMaxRate;
    }

    public void setRequirementMaxRate(String requirementMaxRate) {
        this.requirementMaxRate = requirementMaxRate;
    }

    public String getTaxTerm() {
        return taxTerm;
    }

    public void setTaxTerm(String taxTerm) {
        this.taxTerm = taxTerm;
    }

    public Map getSkillValuesMap() {
        return skillValuesMap;
    }

    public void setSkillValuesMap(Map skillValuesMap) {
        this.skillValuesMap = skillValuesMap;
    }

    public String getSkillCategoryArry() {
        return skillCategoryArry;
    }

    public void setSkillCategoryArry(String skillCategoryArry) {
        this.skillCategoryArry = skillCategoryArry;
    }

    public String getPreSkillCategoryArry() {
        return preSkillCategoryArry;
    }

    public void setPreSkillCategoryArry(String preSkillCategoryArry) {
        this.preSkillCategoryArry = preSkillCategoryArry;
    }

    public List getSkillSetList() {
        return skillSetList;
    }

    public void setSkillSetList(List skillSetList) {
        this.skillSetList = skillSetList;
    }

    public Map getPreSkillValuesMap() {
        return preSkillValuesMap;
    }

    public void setPreSkillValuesMap(Map preSkillValuesMap) {
        this.preSkillValuesMap = preSkillValuesMap;
    }

    public String getReqSkillSet() {
        return reqSkillSet;
    }

    public void setReqSkillSet(String reqSkillSet) {
        this.reqSkillSet = reqSkillSet;
    }

    public String getSkillList() {
        return skillList;
    }

    public void setSkillList(String skillList) {
        this.skillList = skillList;
    }

    public String getBillingtype() {
        return billingtype;
    }

    public void setBillingtype(String billingtype) {
        this.billingtype = billingtype;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public void setVendorComments(String vendorComments) {
        this.vendorComments = vendorComments;
    }

    public String getSsnNo() {
        return ssnNo;
    }

    public void setSsnNo(String ssnNo) {
        this.ssnNo = ssnNo;
    }

    public String getRequirementQualification() {
        return requirementQualification;
    }

    public void setRequirementQualification(String requirementQualification) {
        this.requirementQualification = requirementQualification;
    }
}
