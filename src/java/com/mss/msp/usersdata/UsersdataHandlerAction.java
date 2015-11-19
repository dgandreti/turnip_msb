/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usersdata;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class UsersdataHandlerAction extends ActionSupport implements ServletRequestAware, ParameterAware {

    private String resultType;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    private String empLoginId;
    private String first_name;
    private String last_name;
    private String cur_status;
    private String Phone1;
    private String empName;
    private String status;
    private String workPhone;
    private String organization;
    private String reportingName;
    private String userType;
    private String dob;
    private String working_country;
    private String living_country;
    private String alias;
    private String marital_status;
    private String gender;
    private String email1;
    private String email2;
    private String corp_phone;
    private String fax;
    private String current_status;
    //variables address updation added by Rk
    private String address;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String address_flag;
    private int userSessionId;
    private String emp_position;
    private int resultFlag;
    //variables sddress updation
    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private int userid;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private UserAddress userAddress;
    private EmpDetails empDetails;
    private Map orgRoles;
    private Map notAssignedRoles;
    private Map assignedRoles;
    //added by praveen <pkatru@miraclesoft.com>
    private Map department;
    private Map Title_name;
    private int primaryRole;
    private Map parameters;
    private List leftSideEmployeeRoles;
    private List addedRolesList;
    private boolean is_manager;
    private boolean opt_contact;
    private boolean team_leader;
    private String departmentId;
    private String reportingPerson;
    private Map departments;
    private Map reportsTo;
    private Map countries;
    List<UserVTO> userVTO = new ArrayList<UserVTO>();
    private String csrName;
    private int userId;
    private Map categoryTypes;
    private int userOrgSessionId;
    private int categoryType;
    private int primary;
    //private int userId;
    private int usrCategoryValue;
    private String usrStatus;
    private String usrDescription;
    private String userName;
    private int groupingId;
    private boolean primaryvalue;
    private Map usrCategory;
    //for home Rediredt
    private List homeVTO;
    private Map accountsMap;
    private Map rolesMap;
    private int homeRedirectActionId;
    private String accountType = "All";
    private HomeVTO homeVto;
    private String accountSearchID;
    private String addOrUpdate;
// for adding file upload
    private String filePath;
    private File xlsfile;
    private String xlsfileContentType;
    private String xlsfileFileName;
    //  
    private File fileWithPath;
    private Map rowMap;
    private Map columnsMap;
    public String path;
    private int accUrl;
    private String accType;
    private int mailExt;
    private int zip;
    private int phone;
    private int industry;
    private int region;
    private int territory;
    private int noOfEmp;
    private int taxId;
    private int stockSymbol;
    private int revenue;
    private int description;
    private int accAddress1;
    private int accAddress2;
    private int accState;
    private int accFax;
    private int accCountry;
    private int accCity;
    private int columnValue;
    private String sp_res;
    private String sp_exists;
    private String sp_failure;
    private int excel_id;
    private List utility_logger;
    private String createdDate;
    private String FileExist;
    private String loggerFlag;
    private String downloadFlag;
    private String loadingFileType;
    private String contactFname;
    private String contactLname;
    private String contactMname;
    private String contactGender;
    private int accountSearchOrgId;
    private String contactAccountType;
    private Map skillValuesMap;
    private String uploadFlag;
    private String title;
    private int sessionOrgId;
    private String accountName;
    private List actionName;
    //

    public UsersdataHandlerAction() {
    }
    private DataSourceDataProvider dataSourceDataProvider;

    /**
     *
     * This method is used to personal employee profile
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public String getMyProfile() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                // System.err.println("get my profile action");
                if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString().equalsIgnoreCase("CO")) {
                    setUserAddress(ServiceLocator.getUsersdataHandlerservicee().getEmployeeAddress(httpServletRequest, "consultant_address"));
                } else {
                    setUserAddress(ServiceLocator.getUsersdataHandlerservicee().getEmployeeAddress(httpServletRequest, "usr_address"));
                }


                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String getSkillDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);

                // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     *
     * This method is used to GET employee profile
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public String getempProfile() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                // System.err.println("get emp profile action");
                setPrimaryRole(Integer.parseInt(DataSourceDataProvider.getInstance().getUsrPrimaryRole(this.getUserid()).split("$$")[0]));
                //setNotAssignedRoles(ServiceLocator.getUsersdataHandlerservicee().getNotAssignedRoles(httpServletRequest, this.getUserid()));
                //setAssignedRoles(ServiceLocator.getUsersdataHandlerservicee().getAssignedRoles(httpServletRequest, this.getUserid()));
                //setOrgRoles(ServiceLocator.getUsersdataHandlerservicee().getAllRoles(httpServletRequest, this.getUserid()));
                //above method written by jagan
                setCountries(com.mss.msp.util.DataSourceDataProvider.getInstance().getCountryNames());
                setEmpDetails(ServiceLocator.getUsersdataHandlerservicee().getEmployeeDetails(this.getUserid()));
                setDepartment(ServiceLocator.getUsersdataHandlerservicee().getDepartment_Names(this));
                if (getResultFlag() > 0) {
                    addActionMessage("Profile update successfully");
                }

                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String doAddOrUpdateEmpRoles() throws Exception {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //int usrId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                //setUserid(usrId);
                //System.out.println("doAddOrUpdateEmpRoles");

                //System.out.println("primary roll-->" + getPrimaryRole() + "--getaddedoles-->" + getAddedRolesList());

                String[] rightParams = (String[]) parameters.get("addedRolesList");
                //leftSideEmployeeRoles

                // System.out.println("rightParams.length--->" + rightParams.length);

                int insertedRows = ServiceLocator.getUsersdataHandlerservicee().insertRoles(rightParams, getUserid(), getPrimaryRole());
                // resultMessage = "<font color=\"green\" size=\"1.5\">Roles has been successfully Added!</font>";
                if (insertedRows > 0) {
                    addActionMessage("Roles has been successfully Added!");
                } else {
                    addActionMessage("No Records Updated!");
                }
                httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, resultMessage);
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            //System.out.println("doAddOrUpdateEmpRoles-->" + ex.getMessage());
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }

        return resultType;
    }

    /**
     *
     * This method is used to update employee profile
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public String updateEmpProfile() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                //  System.err.println("update profile action");

                ServiceLocator.getUsersdataHandlerservicee().updateEmpDetails(this, userSessionId);
                //  System.out.println("hear updation is completed....");
                addActionMessage("Profile Updated successfully");
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     *
     * This method is used to GET employee profile
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public String getUserRoles() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                // System.err.println("get emp profile action");
                String role = DataSourceDataProvider.getInstance().getUsrPrimaryRole(this.getUserid());
                String[] parts = role.split("#");
                String part1 = parts[0]; // 004
                String part2 = parts[1];
                String type_of_relation = com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationType(this.getAccountSearchID());
                setPrimaryRole(Integer.parseInt(part1));
                /*setOrgRoles(ServiceLocator.getUsersdataHandlerservicee().getAllRoles(httpServletRequest, this.getUserid()));
                 setNotAssignedRoles(ServiceLocator.getUsersdataHandlerservicee().getNotAssignedRoles(httpServletRequest, this.getUserid()));
                 setAssignedRoles(ServiceLocator.getUsersdataHandlerservicee().getAssignedRoles(httpServletRequest, this.getUserid()));
                 */
                setOrgRoles(ServiceLocator.getUsersdataHandlerservicee().getAllRoles(this.getUserid(), type_of_relation));
                setNotAssignedRoles(ServiceLocator.getUsersdataHandlerservicee().getNotAssignedRoles(this.getUserid(), type_of_relation));
                setAssignedRoles(ServiceLocator.getUsersdataHandlerservicee().getAssignedRoles(this.getUserid()));
                setEmpDetails(ServiceLocator.getUsersdataHandlerservicee().getEmployeeDetails(this.getUserid()));

                if (getResultFlag() > 0) {
                    addActionMessage("Profile update successfully");
                }
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : july 15 2015 Author : manikanta<meeralla@miraclesoft.com>
     * getCsrList() getting the csrs List
     * *****************************************************************************
     */
    public String getCsrList() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                //  System.err.println("update profile action");

                userVTO = ServiceLocator.getUsersdataHandlerservicee().getCsrList(this, userSessionId);
                //  System.out.println("hear updation is completed....");
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : july 15 2015 Author : manikanta<meeralla@miraclesoft.com>
     * getCsrAccounts() getting the csrs Accounts
     * *****************************************************************************
     */
    public String getCsrAccounts() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                //  System.err.println("update profile action");

                userVTO = ServiceLocator.getUsersdataHandlerservicee().getCsrAccounts(this);
                //  System.out.println("hear updation is completed....");
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }
    private int usrCatType;
    private String catValues;
    private Map catValuesMap;
    private String catActiveId;

    public Map getCatValuesMap() {
        return catValuesMap;
    }

    public String getCatActiveId() {
        return catActiveId;
    }

    public void setCatActiveId(String catActiveId) {
        this.catActiveId = catActiveId;
    }

    public void setCatValuesMap(Map catValuesMap) {
        this.catValuesMap = catValuesMap;
    }

    public String getCatValues() {
        return catValues;
    }

    public void setCatValues(String catValues) {
        this.catValues = catValues;
    }

    public int getUsrCatType() {
        return usrCatType;
    }

    public void setUsrCatType(int usrCatType) {
        this.usrCatType = usrCatType;
    }
    private List catValuesList;

    public List getCatValuesList() {
        return catValuesList;
    }

    public void setCatValuesList(List catValuesList) {
        this.catValuesList = catValuesList;
    }

    public String getEmployeeCategorization() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                userOrgSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                //  System.err.println("update profile action");
                setCategoryTypes(dataSourceDataProvider.getInstance().getRequiteCategory());
                userVTO = ServiceLocator.getUsersdataHandlerservicee().getEmployeeCategorization(this, userOrgSessionId);
                //  System.out.println("hear updation is completed....");
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     *
     * This method is used to GET employee profile
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
    public String getUserGroping() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                if ("add".equalsIgnoreCase(getAddOrUpdate())) {
                    System.out.println("In getUserGroping ADD-->");
                    setUsrCategory(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory());
                    setCatValuesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory(getUsrCatType()));
                } else if ("update".equalsIgnoreCase(getAddOrUpdate())) {
                    System.out.println("In getUserGroping Update---->");
                    setUsrCategory(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory());
                    setCatValuesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory(getUsrCatType()));

                    ServiceLocator.getUsersdataHandlerservicee().getUserGroupingData(this);
                } else {
                    System.out.println("in getUserGroping not ADD,Update--->");
                    if (getPrimary() == 1) {
                        System.out.println("in if-->" + getPrimary());
                        setPrimaryvalue(true);
                    } else {
                        setPrimaryvalue(false);
                    }
                    setUsrCategory(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory());
                    setCatValuesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getRequiteCategory(getUsrCatType()));
                }

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }

    /**
     * *****************************************************************************
     * Date : july 15 2015 Author :ramakrishna<lankireddy@miraclesoft.com>
     * getHomeRedirectDetails() getting HomeRedirectDetails List
     * *****************************************************************************
     */
    public String getHomeRedirectDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String accType = (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                System.out.println("------------------->" + accType);
                if ("AC".equalsIgnoreCase(accType)) {
                    setAccountType("AC");
                } else if ("VC".equalsIgnoreCase(accType)) {
                    setAccountType("VC");
                } //                else {
                //                    setSessionOrgId(0);
                //                }
                else {
                    setSessionOrgId(0);
                    setAccountsMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getAllAccounts(getSessionOrgId()));
                }
                setRolesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getAllRoles(getAccountType()));
                homeVTO = ServiceLocator.getUsersdataHandlerservicee().getHomeRedirectDetails(this);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : july 15 2015 Author :ramakrishna<lankireddy@miraclesoft.com>
     * getHomeRedirectDetails() getting HomeRedirectDetails List
     * *****************************************************************************
     */
    public String doAddOrEditHomeRedirect() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                System.out.println(getAccountType() + ">>>>>>>>>>homeRedirectActionId>>>>" + getHomeRedirectActionId());
                String accType =(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                System.out.println("------------------->" + accType + "-----------------------" + getHomeRedirectActionId());
                int roleId = 0;
                if ("AC".equalsIgnoreCase(accType) || "VC".equalsIgnoreCase(accType)) {
                    //setAccountName(com.mss.msp.util.DataSourceDataProvider.getInstance().getOrganizationName(getSessionOrgId()));
                    setAccountType(accType);
                    if (getHomeRedirectActionId() == 0) {
                        if ("AC".equalsIgnoreCase(accType)) {
                            roleId = 2;
                           
                        } else {
                            roleId = 8;
                        }
                        setActionName(com.mss.msp.util.DataSourceDataProvider.getInstance().getActionNamesList(getSessionOrgId(), roleId, accType));
                        Map map = (com.mss.msp.util.DataSourceDataProvider.getInstance().getAllAccounts(getSessionOrgId()));
                        Iterator mapIterator = map.entrySet().iterator();
                        while (mapIterator.hasNext()) {
                            Map.Entry mapEntry = (Map.Entry) mapIterator.next();
                            Integer keyValue = (Integer) mapEntry.getKey();
                            String value = (String) mapEntry.getValue();
                            setAccountName(value);
                            setAccountSearchOrgId(keyValue);
                            System.out.println();
                        }
                    }
                }
                if (getHomeRedirectActionId() > 0) {
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    setRolesMap(com.mss.msp.util.DataSourceDataProvider.getInstance().getAllRoles(getAccountType()));
                    homeVto = ServiceLocator.getUsersdataHandlerservicee().getHomeRedirectDetailsForEdit(this);
                }
//                System.out.println(">>>>" + homeVto.toString());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }


    /**
     * *****************************************************************************
     * Date : 08/28/2015 praveen <pkatru@miraclesoft.com> loadData() loading
     * date trough xml file.
     * *****************************************************************************
     */
    public String loadData() {
        resultType = LOGIN;
        try {
            System.out.println("-----------------------------------in load");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("----------------------------------in if");
                SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
                System.out.println("Session-->addRequirements-->" + session);
                Map skillsmap = (Map) session.get("skillsmap");
                setSkillValuesMap(skillsmap);
                
               // setSkillValuesMap(dataSourceDataProvider.getInstance().getReqSkillsCategory());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 08/28/2015 praveen <pkatru@miraclesoft.com> loadData() loading
     * date trough xml file.
     * *****************************************************************************
     */
    public String doXlsFileUpload() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                System.out.println("this is file " + getXlsfile());
                System.out.println("this is file name " + getXlsfileFileName());
                System.out.println("this is file content type " + getXlsfileContentType());

                if (getXlsfileFileName() == null) {
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
                    File destFile = new File(theFile + File.separator + xlsfileFileName);
                    FileUtils.copyFile(xlsfile, destFile);
                }
                System.out.println("this is file path getFilePath--->" + getFilePath());
                setPath(getFilePath() + File.separator + xlsfileFileName);
                System.out.println("-----------------" + getPath());
                System.out.println("this is file path getFilePath--->" + getFilePath());
                setFileWithPath(new File(getFilePath() + File.separator + xlsfileFileName));
                System.out.println("this is file path getFilePath--->" + getFileWithPath());
                String FileNameExist = ServiceLocator.getUserAjaxHandlerService().checkFileName(xlsfileFileName);
                if ("Exist".equals(FileNameExist)) {
                    setFileExist("File Name Already Exists!!");
                    resultType = INPUT;
                } else {
                    ServiceLocator.getUsersdataHandlerservicee().doXlsFileUpload(this, xlsfileFileName);
                    resultType = SUCCESS;
                }

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date : 08/28/2015 praveen <pkatru@miraclesoft.com> loadData() loading
     * date trough xml file.
     * *****************************************************************************
     */
    public String getCellContentValues() {
        resultType = LOGIN;
        try {
            System.out.println("----------------in con");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setFileWithPath(new File(getPath()));
                System.out.println("------------------------" + getLoadingFileType());

                Workbook workbook = Workbook.getWorkbook(getFileWithPath());
                Sheet sheet = workbook.getSheet(0);
                int count = sheet.getRows();
                System.out.println(" i am printing rows count:" + count);
                System.out.println(" i am printing rows count:" + count);
                System.out.println("this is accountName-->" + getColumnValue());
                System.out.println("this is url-->" + getAccUrl());
                System.out.println("this is accType-->" + getAccType());
                System.out.println("this is extention-->" + getMailExt());
                System.out.println("this is address1-->" + getAccAddress1());
                System.out.println("this is address2-->" + getAccAddress2());
                System.out.println("this is city-->" + getAccCity());
                System.out.println("this is state-->" + getAccState());
                System.out.println("this is zip-->" + getZip());
                System.out.println("this is country-->" + getAccCountry());
                System.out.println("this is phone-->" + getPhone());
                System.out.println("this is fax-->" + getAccFax());
                System.out.println("this is industry-->" + getIndustry());
                System.out.println("this is region-->" + getRegion());
                System.out.println("this is terrotory-->" + getTerritory());
                System.out.println("this is no.of emp-->" + getNoOfEmp());
                System.out.println("this is tax id-->" + getTaxId());
                System.out.println("this is stockSymbol-->" + getStockSymbol());
                System.out.println("this is Revenue-->" + getRevenue());
                System.out.println("this is description-->" + getDescription());
                Cell rows = null;
                List<String> rowList = new ArrayList<String>();
                Map rowMap = new HashMap();
                String accRecord = "";
                String stringForBatch = "";

                stringForBatch = getColumnValue() + "|"
                        + getAccUrl() + "|"
                        + +getMailExt() + "|"
                        + getAccAddress1() + "|"
                        + getAccAddress2() + "|"
                        + getAccCity() + "|"
                        + getZip() + "|"
                        + getAccCountry() + "|"
                        + getAccState() + "|"
                        + getPhone() + "|"
                        + getAccFax() + "|"
                        + getIndustry() + "|"
                        + getRegion() + "|"
                        + getTerritory() + "|"
                        + getNoOfEmp() + "|"
                        + getTaxId() + "|"
                        + getStockSymbol() + "|"
                        + getRevenue() + "|"
                        + getDescription();
                StringTokenizer st = new StringTokenizer(stringForBatch, "|");
                int excelColValue[] = new int[50];
                int k = 0;
                while (st.hasMoreElements()) {
                    excelColValue[k] = Integer.parseInt(st.nextElement().toString());
                    k++;
                }
                int colCount = sheet.getColumns();
                int rowsCount = sheet.getRows();
                Cell cellValue = null;
                List<String[]> list = new ArrayList<String[]>();
                Map columnsMap = new HashMap();
                //String dataString = "";

                for (int j = 1; j < rowsCount; j++) {
                    String dataArray[] = new String[k];
                    String dataString = "";
                    for (int i = 0; i < k; i++) {

                        if (excelColValue[i] != -1) {
                            cellValue = sheet.getCell(excelColValue[i], j);
                            if (!cellValue.getContents().contains("|") && !cellValue.getContents().contains("^")) {

//                                if (i == 11 && (cellValue.getContents() != "" || cellValue.getContents() != null)) {
//                                    String industry;
//                                    industry = cellValue.getContents();
//                                    int industryValue = ServiceLocator.getUsersdataHandlerservicee().getIndustryValue(industry);
//                                    dataArray[i] = industryValue + "";
//                                } else if (i == 7 && (cellValue.getContents() != "" || cellValue.getContents() != null)) {
//                                    String country;
//                                    country = cellValue.getContents();
//                                    int countryValue = ServiceLocator.getUsersdataHandlerservicee().getCountryValue(country);
//                                    dataArray[i] = countryValue + "";
////                                } else if (i == 8 && (cellValue.getContents() != "" || cellValue.getContents() != null)) {
////                                    String state;
////                                    state = cellValue.getContents();
////                                    int stateValue = ServiceLocator.getUsersdataHandlerservicee().getStateValue(state, sheet.getCell(excelColValue[i - 1], j).getContents());
////                                    dataString += stateValue + "|";
//                                } else 
                                {
                                    dataArray[i] = cellValue.getContents().trim() + "";
                                }
                            } else {
                                dataArray[i] = "";
                            }
                        } else {
                            dataArray[i] = "";
                        }
                        //System.out.println("--->"+dataArray[i]);
                    }
                    list.add(dataArray);
                }
                setRowMap(rowMap);
                // System.out.println("this is complete String of data-->" + dataString);
                System.out.println("here i am printing content of row" + rowList);
                System.out.println("getting path from ----------->" + getPath());
                workbook.close();
                String res = ServiceLocator.getUsersdataHandlerservicee().getCellContentValues(list, this, k, "Accounts", stringForBatch);
                utility_logger = ServiceLocator.getUsersdataHandlerservicee().defaultSearch(this);
                //System.out.println("-------------- after procedure------------" + res);


                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }
        return resultType;
    }

    public String searchLogger() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                utility_logger = ServiceLocator.getUsersdataHandlerservicee().defaultSearch(this);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }
        return resultType;
    }

    public String getCellContentValuesForUser() {
        resultType = LOGIN;
        try {
            System.out.println("----------------in con");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setUserOrgSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setFileWithPath(new File(getPath()));
                System.out.println("------------------------" + getLoadingFileType());
                Workbook workbook = Workbook.getWorkbook(getFileWithPath());
                Sheet sheet = workbook.getSheet(0);
                int count = sheet.getRows();
                System.out.println(" i am printing rows count:" + count);
                System.out.println("this is firstName-->" + getContactFname());
                System.out.println("this is middle-->" + getContactMname());
                System.out.println("this is last-->" + getContactLname());
                Cell rows = null;
                List<String> rowList = new ArrayList<String>();
                Map rowMap = new HashMap();
                String accRecord = "";
                String stringForBatch = "";

                stringForBatch = getContactFname().trim() + "|"
                        + getContactMname().trim() + "|"
                        + getContactLname().trim() + "|"
                        + getEmail1().trim() + "|"
                        + getWorkPhone().trim() + "|"
                        + getPhone() + "|"
                        + getAddress().trim() + "|"
                        + getAddress2().trim() + "|"
                        + getCity().trim() + "|"
                        + getZip() + "|"
                        + getCountry().trim() + "|"
                        + getState().trim() + "|"
                        + getPhone1().trim() + "|"
                        + getContactGender().trim() + "|"
                        + getEmail2().trim() + "|"
                        + getTitle();

                StringTokenizer st = new StringTokenizer(stringForBatch, "|");
                int excelColValue[] = new int[50];
                int k = 0;
                while (st.hasMoreElements()) {
                    excelColValue[k] = Integer.parseInt(st.nextElement().toString());
                    k++;
                }
                int colCount = sheet.getColumns();
                int rowsCount = sheet.getRows();
                Cell cellValue = null;
                List<String[]> list = new ArrayList<String[]>();
                Map columnsMap = new HashMap();
                //String dataString = "";

                for (int j = 1; j < rowsCount; j++) {
                    String dataArray[] = new String[k];
                    String dataString = "";
                    for (int i = 0; i < k; i++) {

                        if (excelColValue[i] != -1) {
                            cellValue = sheet.getCell(excelColValue[i], j);
                            if (!cellValue.getContents().contains("|") && !cellValue.getContents().contains("^")) {

                                dataArray[i] = cellValue.getContents().trim() + "";

                            } else {
                                dataArray[i] = "null";
                            }
                        } else {
                            dataArray[i] = "null";
                        }
                    }
                    list.add(dataArray);
                }
                setRowMap(rowMap);
                // System.out.println("this is complete String of data-->" + dataString);
                System.out.println("here i am printing content of row" + rowList);
                System.out.println("getting path from ----------->" + getPath());
                workbook.close();
                String res = ServiceLocator.getUsersdataHandlerservicee().getCellContentValues(list, this, k, getLoadingFileType(), stringForBatch);
                utility_logger = ServiceLocator.getUsersdataHandlerservicee().defaultSearch(this);
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            ex.printStackTrace();
            resultType = ERROR;
        }
        return resultType;
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

    public String getResultMessage() {
        return resultMessage;
    }

    public String getEmpLoginId() {
        return empLoginId;
    }

    public void setEmpLoginId(String empLoginId) {
        this.empLoginId = empLoginId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCur_status() {
        return cur_status;
    }

    public void setCur_status(String cur_status) {
        this.cur_status = cur_status;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String Phone1) {
        this.Phone1 = Phone1;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getReportingName() {
        return reportingName;
    }

    public void setReportingName(String reportingName) {
        this.reportingName = reportingName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the userAddress
     */
    public UserAddress getUserAddress() {
        return userAddress;
    }

    /**
     * @param userAddress the userAddress to set
     */
    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWorking_country() {
        return working_country;
    }

    public void setWorking_country(String working_country) {
        this.working_country = working_country;
    }

    public String getLiving_country() {
        return living_country;
    }

    public void setLiving_country(String living_country) {
        this.living_country = living_country;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        if ("1".equalsIgnoreCase(marital_status)) {
            this.marital_status = "S";
        } else {
            this.marital_status = "M";
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if ("1".equalsIgnoreCase(gender)) {
            this.gender = "M";
        } else {
            this.gender = "F";
        }
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getCorp_phone() {
        return corp_phone;
    }

    public void setCorp_phone(String corp_phone) {
        this.corp_phone = corp_phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public EmpDetails getEmpDetails() {
        return empDetails;
    }

    public void setEmpDetails(EmpDetails empDetails) {
        this.empDetails = empDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress_flag() {
        return address_flag;
    }

    public void setAddress_flag(String address_flag) {
        this.address_flag = address_flag;
    }

    public Map getDepartment() {
        return department;
    }

    public void setDepartment(Map department) {
        this.department = department;
    }

    public Map getTitle_name() {
        return Title_name;
    }

    public void setTitle_name(Map Title_name) {
        this.Title_name = Title_name;
    }

    public int getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(int primaryRole) {
        this.primaryRole = primaryRole;
    }

    public Map getParameters() {
        return parameters;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public List getLeftSideEmployeeRoles() {
        return leftSideEmployeeRoles;
    }

    public void setLeftSideEmployeeRoles(List leftSideEmployeeRoles) {
        this.leftSideEmployeeRoles = leftSideEmployeeRoles;
    }

    public List getAddedRolesList() {
        return addedRolesList;
    }

    public void setAddedRolesList(List addedRolesList) {
        this.addedRolesList = addedRolesList;
    }

    public Map getOrgRoles() {
        return orgRoles;
    }

    public void setOrgRoles(Map orgRoles) {
        this.orgRoles = orgRoles;
    }

    public Map getNotAssignedRoles() {
        return notAssignedRoles;
    }

    public void setNotAssignedRoles(Map notAssignedRoles) {
        this.notAssignedRoles = notAssignedRoles;
    }

    public Map getAssignedRoles() {
        return assignedRoles;
    }

    public void setAssignedRoles(Map assignedRoles) {
        this.assignedRoles = assignedRoles;
    }

    public String getEmp_position() {
        return emp_position;
    }

    public void setEmp_position(String emp_position) {
        this.emp_position = emp_position;
    }

    public boolean isIs_manager() {
        return is_manager;
    }

    public void setIs_manager(boolean is_manager) {
        this.is_manager = is_manager;
    }

    public boolean isOpt_contact() {
        return opt_contact;
    }

    public void setOpt_contact(boolean opt_contact) {
        this.opt_contact = opt_contact;
    }

    public boolean isTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(boolean team_leader) {
        this.team_leader = team_leader;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getReportingPerson() {
        return reportingPerson;
    }

    public void setReportingPerson(String reportingPerson) {
        this.reportingPerson = reportingPerson;
    }

    public Map getDepartments() {
        return departments;
    }

    public void setDepartments(Map departments) {
        this.departments = departments;
    }

    public Map getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Map reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Map getCountries() {
        return countries;
    }

    public void setCountries(Map countries) {
        this.countries = countries;
    }

    public int getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(int resultFlag) {
        this.resultFlag = resultFlag;
    }

    public List<UserVTO> getUserVTO() {
        return userVTO;
    }

    public void setUserVTO(List<UserVTO> userVTO) {
        this.userVTO = userVTO;
    }

    public String getCsrName() {
        return csrName;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map getCategoryTypes() {
        return categoryTypes;
    }

    public void setCategoryTypes(Map categoryTypes) {
        this.categoryTypes = categoryTypes;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public int getUserOrgSessionId() {
        return userOrgSessionId;
    }

    public void setUserOrgSessionId(int userOrgSessionId) {
        this.userOrgSessionId = userOrgSessionId;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public int getUsrCategoryValue() {
        return usrCategoryValue;
    }

    public void setUsrCategoryValue(int usrCategoryValue) {
        this.usrCategoryValue = usrCategoryValue;
    }

    public String getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(String usrStatus) {
        this.usrStatus = usrStatus;
    }

    public String getUsrDescription() {
        return usrDescription;
    }

    public void setUsrDescription(String usrDescription) {
        this.usrDescription = usrDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGroupingId() {
        return groupingId;
    }

    public void setGroupingId(int groupingId) {
        this.groupingId = groupingId;
    }

    public boolean isPrimaryvalue() {
        return primaryvalue;
    }

    public void setPrimaryvalue(boolean primaryvalue) {
        this.primaryvalue = primaryvalue;
    }

    public Map getUsrCategory() {
        return usrCategory;
    }

    public void setUsrCategory(Map usrCategory) {
        this.usrCategory = usrCategory;
    }

    public List getHomeVTO() {
        return homeVTO;
    }

    public void setHomeVTO(List homeVTO) {
        this.homeVTO = homeVTO;
    }

    public Map getAccountsMap() {
        return accountsMap;
    }

    public void setAccountsMap(Map accountsMap) {
        this.accountsMap = accountsMap;
    }

    public Map getRolesMap() {
        return rolesMap;
    }

    public void setRolesMap(Map rolesMap) {
        this.rolesMap = rolesMap;
    }

    public int getHomeRedirectActionId() {
        return homeRedirectActionId;
    }

    public void setHomeRedirectActionId(int homeRedirectActionId) {
        this.homeRedirectActionId = homeRedirectActionId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public HomeVTO getHomeVto() {
        return homeVto;
    }

    public void setHomeVto(HomeVTO homeVto) {
        this.homeVto = homeVto;
    }

    public String getAccountSearchID() {
        return accountSearchID;
    }

    public void setAccountSearchID(String accountSearchID) {
        this.accountSearchID = accountSearchID;
    }

    public String getAddOrUpdate() {
        return addOrUpdate;
    }

    public void setAddOrUpdate(String addOrUpdate) {
        this.addOrUpdate = addOrUpdate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getXlsfile() {
        return xlsfile;
    }

    public void setXlsfile(File xlsfile) {
        this.xlsfile = xlsfile;
    }

    public String getXlsfileContentType() {
        return xlsfileContentType;
    }

    public void setXlsfileContentType(String xlsfileContentType) {
        this.xlsfileContentType = xlsfileContentType;
    }

    public String getXlsfileFileName() {
        return xlsfileFileName;
    }

    public void setXlsfileFileName(String xlsfileFileName) {
        this.xlsfileFileName = xlsfileFileName;
    }

    public File getFileWithPath() {
        return fileWithPath;
    }

    public void setFileWithPath(File fileWithPath) {
        this.fileWithPath = fileWithPath;
    }

    public Map getColumnsMap() {
        return columnsMap;
    }

    public void setColumnsMap(Map columnsMap) {
        this.columnsMap = columnsMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAccUrl() {
        return accUrl;
    }

    public void setAccUrl(int accUrl) {
        this.accUrl = accUrl;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public int getMailExt() {
        return mailExt;
    }

    public void setMailExt(int mailExt) {
        this.mailExt = mailExt;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getTerritory() {
        return territory;
    }

    public void setTerritory(int territory) {
        this.territory = territory;
    }

    public int getNoOfEmp() {
        return noOfEmp;
    }

    public void setNoOfEmp(int noOfEmp) {
        this.noOfEmp = noOfEmp;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public int getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(int stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getAccAddress1() {
        return accAddress1;
    }

    public void setAccAddress1(int accAddress1) {
        this.accAddress1 = accAddress1;
    }

    public int getAccAddress2() {
        return accAddress2;
    }

    public void setAccAddress2(int accAddress2) {
        this.accAddress2 = accAddress2;
    }

    public int getAccState() {
        return accState;
    }

    public void setAccState(int accState) {
        this.accState = accState;
    }

    public int getAccFax() {
        return accFax;
    }

    public void setAccFax(int accFax) {
        this.accFax = accFax;
    }

    public int getAccCountry() {
        return accCountry;
    }

    public void setAccCountry(int accCountry) {
        this.accCountry = accCountry;
    }

    public int getAccCity() {
        return accCity;
    }

    public void setAccCity(int accCity) {
        this.accCity = accCity;
    }

    public int getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(int columnValue) {
        this.columnValue = columnValue;
    }

    public String getSp_res() {
        return sp_res;
    }

    public void setSp_res(String sp_res) {
        this.sp_res = sp_res;
    }

    public String getSp_exists() {
        return sp_exists;
    }

    public void setSp_exists(String sp_exists) {
        this.sp_exists = sp_exists;
    }

    public String getSp_failure() {
        return sp_failure;
    }

    public void setSp_failure(String sp_failure) {
        this.sp_failure = sp_failure;
    }

    public int getExcel_id() {
        return excel_id;
    }

    public void setExcel_id(int excel_id) {
        this.excel_id = excel_id;
    }

    public List getUtility_logger() {
        return utility_logger;
    }

    public void setUtility_logger(List utility_logger) {
        this.utility_logger = utility_logger;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFileExist() {
        return FileExist;
    }

    public void setFileExist(String FileExist) {
        this.FileExist = FileExist;
    }

    public Map getRowMap() {
        return rowMap;
    }

    public void setRowMap(Map rowMap) {
        this.rowMap = rowMap;
    }

    public String getLoggerFlag() {
        return loggerFlag;
    }

    public void setLoggerFlag(String loggerFlag) {
        this.loggerFlag = loggerFlag;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getLoadingFileType() {
        return loadingFileType;
    }

    public void setLoadingFileType(String loadingFileType) {
        this.loadingFileType = loadingFileType;
    }

    public String getContactFname() {
        return contactFname;
    }

    public void setContactFname(String contactFname) {
        this.contactFname = contactFname;
    }

    public String getContactLname() {
        return contactLname;
    }

    public void setContactLname(String contactLname) {
        this.contactLname = contactLname;
    }

    public String getContactMname() {
        return contactMname;
    }

    public void setContactMname(String contactMname) {
        this.contactMname = contactMname;
    }

    public String getContactGender() {
        return contactGender;
    }

    public void setContactGender(String contactGender) {
        this.contactGender = contactGender;
    }

    public int getAccountSearchOrgId() {
        return accountSearchOrgId;
    }

    public void setAccountSearchOrgId(int accountSearchOrgId) {
        this.accountSearchOrgId = accountSearchOrgId;
    }

    public String getContactAccountType() {
        return contactAccountType;
    }

    public void setContactAccountType(String contactAccountType) {
        this.contactAccountType = contactAccountType;
    }

    public Map getSkillValuesMap() {
        return skillValuesMap;
    }

    public void setSkillValuesMap(Map skillValuesMap) {
        this.skillValuesMap = skillValuesMap;
    }

    public String getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List getActionName() {
        return actionName;
    }

    public void setActionName(List actionName) {
        this.actionName = actionName;
    }
}
