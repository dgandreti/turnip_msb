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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class RequirementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static Logger log = Logger.getLogger(RequirementAction.class);
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
    private String requirementskDetails;
    private int accountSearchID;
    private String requirementSkill;
    private String requirementStatus;
    private String reqStart;
    private String reqEnd;
    private String jobTitle;
    private int sessionOrgId;
    private Map recruitmentMap;
    private Map presalesMap;
    private String accountName;
    private String conEmail;
    private String conId;
    private String reqId;
    private String proofType;
    private String ppno;
    private String pan;
    private String proofValue;
    private String accountFlag;
    private String account_name;
    private String vendor;
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
    private String customerName;
    private String status_check;

    public RequirementAction() {
    }
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultMessage;
    private DataSourceDataProvider dataSourceDataProvider;
    private RequirementVTO requirementVTO;
    private String RequirementPreferredSkills;
    private MailManager mailManager = new MailManager();

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addRequirements() method is used to
     *
     *
     * *****************************************************************************
     */
    public String addRequirements() {
        resultMessage = LOGIN;
        log.info("********************RequirementAction :: addRequirements Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setContacts(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                setCountryMap(dataSourceDataProvider.getInstance().getCountryNames());
                setExperienceMap(dataSourceDataProvider.getInstance().getYearsOfExp());
                setRecruitmentMap(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(getOrgId()));
                setPresalesMap(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();

                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setPreSkillValuesMap(skillsmap);
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "errorMessage: " + ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;

        }
        log.info("********************RequirementAction :: addRequirements Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doCopyRequirement() method is used to
     *
     *
     * *****************************************************************************
     */
    public String doCopyRequirement() {
        resultMessage = LOGIN;
        log.info("********************RequirementAction :: doCopyRequirement Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setContacts(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                setCountryMap(dataSourceDataProvider.getInstance().getCountryNames());
                setExperienceMap(dataSourceDataProvider.getInstance().getYearsOfExp());
                setRecruitmentMap(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(getOrgId()));
                setPresalesMap(dataSourceDataProvider.getInstance().getSalesTeam(getOrgId()));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getAccountSearchID()));
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setPreSkillValuesMap(skillsmap);
                requirementVTO = ServiceLocator.getRequirementService().editrequirement(getRequirementId(), skillsmap);
                resultMessage = SUCCESS;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "errorMessage: " + ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;

        }
        log.info("********************RequirementAction :: doCopyRequirement Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addRequirementDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public String addRequirementDetails() {
        resultMessage = LOGIN;
        log.info("********************RequirementAction :: addRequirementDetails Method Start*********************");
        int requirement;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                requirement = ServiceLocator.getRequirementService().addRequirementDetails(this, getOrgId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + requirement + "");

                return null;

            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "errorMessage: " + ex.toString());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;

        }
        log.info("********************RequirementAction :: addRequirementDetails Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : setrequirementedit() method is used to
     *
     *
     * *****************************************************************************
     */
    public String setrequirementedit() {
        String resultMessage;
        resultMessage = LOGIN;
        log.info("********************RequirementAction :: setrequirementedit Method Start*********************");

        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                int org_id = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                String typeOfUser = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                int roleId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString());
                setContacts(dataSourceDataProvider.getInstance().getSalesTeam(org_id));
                setReqCategory(dataSourceDataProvider.getInstance().getRequiteCategory(1));
                setCountryMap(dataSourceDataProvider.getInstance().getCountryNames());
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();

                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                setPreSkillValuesMap(skillsmap);

                if (typeOfUser.equals("VC") || typeOfUser.equals("SA")|| roleId==1) {

                    int orgId = ServiceLocator.getRequirementService().getOrgIdCustomer(getRequirementId());

                    setEmployeeNames(dataSourceDataProvider.getInstance().getRecruitmentDeptNames(orgId));

                } else {

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
                log.log(Level.ERROR, "errorMessage: " + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());

                resultMessage = ERROR;
            }
        }
        log.info("********************RequirementAction :: setrequirementedit Method End*********************");
        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : updateRequirements() method is used to
     *
     *
     * *****************************************************************************
     */
    public String updateRequirements() {
        log.info("********************RequirementAction :: updateRequirements Method Start*********************");
        String resultMessage;
        resultMessage = LOGIN;
        int updated = 0;
        int mailResult = 0;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                int userid = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());

                int org_id = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());

                updated = ServiceLocator.getRequirementService().updateRequirement(userid, this);

                if (updated > 0) {
                    if ((!"OR".equalsIgnoreCase(getStatus_check())) && ("OR".equalsIgnoreCase(getRequirementStatus()))) {
                        setMailIds(DataSourceDataProvider.getInstance().getMailIdsOfVendorAssociated(getRequirementId()));
                        StringTokenizer mailID = new StringTokenizer(getMailIds(), ",");
                        while (mailID.hasMoreElements()) {
                            setMailIds(mailID.nextElement().toString());

                            mailResult = mailManager.requirementStatusChangeMailGenerator(this, getMailIds(), userid);
                        }
                        if (mailResult > 0) {
                        }
                    }
                }

            } catch (Exception ex) {
                log.log(Level.ERROR, "errorMessage: " + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());

                resultType = ERROR;
            }
        }
        log.info("********************RequirementAction :: updateRequirements Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :05/06/2015
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getRequirementDetails() method is used to get Requirement
     * details of account
     *
     *
     * *****************************************************************************
     */
    public String getRequirementDetails() {
        resultType = LOGIN;
        log.info("********************RequirementAction :: getRequirementDetails Method Start*********************");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                requirementskDetails = ServiceLocator.getRequirementService().getRequirementDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(requirementskDetails);
                resultType = null;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "errorMessage: " + ex.toString());
            resultType = ERROR;
        }
        log.info("********************RequirementAction :: getRequirementDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :05/06/2015
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getReqDetailsBySearch() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getReqDetailsBySearch() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getReqDetailsBySearch Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String list = ServiceLocator.getRequirementService().getReqDetailsBySearch(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getReqDetailsBySearch Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :05/06/2015
     *
     * Author : Ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getSkillDetaisls() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getSkillDetaisls() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getSkillDetaisls Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String skills = ServiceLocator.getRequirementService().getSkillDetaisls(this);
                skills = skills.replaceAll("<br/>", "\n");
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(skills);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getSkillDetaisls Method End*********************");
        return null;
    }
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

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : storeProofData() method is used to
     *
     *
     * *****************************************************************************
     */
    public String storeProofData() {
        resultMessage = LOGIN;
         log.info("********************RequirementAction :: storeProofData Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                if (getFileFileName() == null) {
                } else {
                    filesPath = Properties.getProperty("Task.Attachment");
                    File createPath = new File(filesPath);
                    Date dt = new Date();

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

                    createPath = new File(createPath.getAbsolutePath() + "/" + String.valueOf(dt.getYear() + 1900) + "/" + month + "/" + String.valueOf(week));

                    createPath.mkdir();

                    File theFile = new File(createPath.getAbsolutePath());

                    setFilesPath(theFile.toString());

                    File destFile = new File(theFile + File.separator + fileFileName);
                    FileUtils.copyFile(file, destFile);
                }
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));

                setProofValue("");
                setProofValue("");
                String proof = ServiceLocator.getRequirementService().storeProofData(httpServletRequest, this);

                resultMessage = SUCCESS;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
         log.info("********************RequirementAction :: storeProofData Method End*********************");
        return resultMessage;
    }
    /**
     * *****************************************************************************
     * Date : 05/06/2015
     *
     * Author : praveen <pkatru@miraclesoft.com>
     *
     * ForUse : getSearchRequirementsList() method is used to
     *
     *
     * *****************************************************************************
     */
    private int reqCreatedBy;

    public String getSearchRequirementsList() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getSearchRequirementsList Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                setPrimaryRole(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();

                Map skillsmap = (Map) session.get("skillsmap");
                String list = ServiceLocator.getRequirementService().getSearchRequirementsList(httpServletRequest, this, skillsmap);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getSearchRequirementsList Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 05/12/2015
     *
     * Author : praveen <pkatru@miraclesoft.com>
     *
     * ForUse :
     *
     * @getRecruiterOverlay() method is used to get Requirement details of
     * account
     *
     *
     * *****************************************************************************
     */
    public String getRecruiterOverlay() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getRecruiterOverlay Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String list = ServiceLocator.getRequirementService().getRecruiterOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getRecruiterOverlay Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse :
     *
     * @getSkillOverlay() method is used
     *
     *
     * *****************************************************************************
     */
    public String getSkillOverlay() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getSkillOverlay Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String list = ServiceLocator.getRequirementService().getSkillOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getSkillOverlay Method Start*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse :
     *
     * @getPreSkillOverlay() method is used
     *
     *
     * *****************************************************************************
     */
    public String getPreSkillOverlay() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getPreSkillOverlay Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String list = ServiceLocator.getRequirementService().getPreSkillOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getPreSkillOverlay Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 06/02/2015
     *
     * Author : praveen <pkatru@miraclesoft.com>
     *
     * ForUse :
     *
     * @doReleaseRequirements() release requirements for tier 1
     *
     *
     * *****************************************************************************
     */
    public String doReleaseRequirements() {
        resultMessage = LOGIN;
         log.info("********************RequirementAction :: doReleaseRequirements Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setAccount_name(dataSourceDataProvider.getInstance().getAccountNameById(getSessionOrgId()));
                int record = ServiceLocator.getRequirementService().doReleaseRequirements(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + record + "");
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
         log.info("********************RequirementAction :: doReleaseRequirements Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     *
     * *****************************************************************************
     */
    public String getRequirementDashBoardDetails() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getRequirementDashBoardDetails Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                String csrReq = ServiceLocator.getRequirementService().getRequirementDashBoardDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getRequirementDashBoardDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getRequirementDashBoardDetailsOnOverlay() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getRequirementDashBoardDetailsOnOverlay() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getRequirementDashBoardDetailsOnOverlay Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                String csrReq = ServiceLocator.getRequirementService().getRequirementDashBoardDetailsOnOverlay(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getRequirementDashBoardDetailsOnOverlay Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getVendorRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * *****************************************************************************
     */
    public String getVendorRequirementDashBoardDetails() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getVendorRequirementDashBoardDetails Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String csrReq = null;
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getVendorRequirementDashBoardDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getVendorRequirementsDashBoard() method is used to get Requirement
     * details of account
     *
     * *****************************************************************************
     */
    public String getVendorRequirementsDashBoard() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getVendorRequirementsDashBoard Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String csrReq = ServiceLocator.getRequirementService().getVendorRequirementsDashBoard(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getVendorRequirementsDashBoard Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getVendorDashBoardDetailsOnOverlay() method is used
     *
     * *****************************************************************************
     */
    public String getVendorDashBoardDetailsOnOverlay() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getVendorDashBoardDetailsOnOverlay Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String csrReq = ServiceLocator.getRequirementService().getVendorDashBoardDetailsOnOverlay(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getVendorDashBoardDetailsOnOverlay Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @customerDashBoardDetails() update status in requirement table
     *
     * *****************************************************************************
     */
    public String customerDashBoardDetails() throws ServiceLocatorException {
        String resulttype = LOGIN;
        System.out.println("********************RequirementAction :: customerDashBoardDetails Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            customerDashBoardList = (ServiceLocator.getRequirementService().getDefaultCustomerRequirementDashBoardDetails(this));

            setYear(Calendar.getInstance().get(Calendar.YEAR));
            resulttype = SUCCESS;
        }
        System.out.println("********************RequirementAction :: customerDashBoardDetails Method End*********************");
        return resulttype;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getRequirementDashBoardDetails() method is used to get Requirement
     * details of account
     *
     * *****************************************************************************
     */
    public String getCustomerRequirementDashBoardDetails() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getCustomerRequirementDashBoardDetails Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                String csrReq = ServiceLocator.getRequirementService().getCustomerRequirementDashBoardDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(csrReq);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getCustomerRequirementDashBoardDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse :
     *
     * @getPreferedSkillDetails() method is used to get Requirement prefered
     * skill details of account
     *
     * *****************************************************************************
     */
    public String getPreferedSkillDetails() {
        resultMessage = LOGIN;
        System.out.println("********************RequirementAction :: getPreferedSkillDetails Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String skills = ServiceLocator.getRequirementService().getPreferedSkillDetails(this);
                skills = skills.replaceAll("<br/>", "\n");
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(skills);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************RequirementAction :: getPreferedSkillDetails Method End*********************");
        return null;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * *****************************************************************************
     * Date : 15/06/2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * ForUse : This method is used to set the Servlet Response
     *
     * @param httpServletResponse
     *
     * *****************************************************************************
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus_check() {
        return status_check;
    }

    public void setStatus_check(String status_check) {
        this.status_check = status_check;
    }
}
