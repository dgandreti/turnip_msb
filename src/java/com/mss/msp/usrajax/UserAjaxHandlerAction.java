/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usrajax;

import com.mss.msp.security.SecurityServiceProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DefaultDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.PDFGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author miracle
 */
public class UserAjaxHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resourceType;
    /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String responseString;
    private DataSourceDataProvider dataSourceDataProvider;
    private DefaultDataProvider defaultDataProvider;
    private String emailId;
    /*
     * Fields User registration start
     * Date : 03/10/2015
     */
    private String leaveTo = "";
    private String leaveSupTo = "";
    private String regType;
    private String loginId;
    private String csrName;
    //private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String maritalStatus;
    private String dob;
    private String phone;
    private String officeAddress1;
    private String officeAddress2;
    private String officeCity;
    private String officeState;
    private String officeCountry;
    private String zip;
    private String officePhone;
    private File picture;
    private String pictureFileName;
    private String pictureFileContentType;
    private String message = "";
    private String filePath;
    private String livingCountry;
    private String empName;
    private int userid;
    //variables address updation created By Rk
    private String address;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String address_flag;
    /* variable created for edit skill details, created by triveni */
    private int skill_id;
    private String skill_name;
    private String skill_rate;
    private String comments;
    private int userSessionId;
    /* variable created for education details, created by Aklakh */
    private int usr_edu_id;
    private String qualification;
    private String year_start;
    private String year_end;
    private String university;
    private String institution;
    private String specialization;
    private Double percentage;
    private String start_year;
    private String end_year;
    private int roleId;
    private int orgId;
    /* variable created for education details, created by Aklakh */
//added by praveen<pkatru@miraclesoft.com>
    private int dept_id;
    private int title_id;
    private int isTeam;
    private int isManager;
    private int isPmo;
    private int isOpt;
    private int isSbteam;
    private int report_id;
    private int org_id;

    /* variable created for security details, created by manikanta */
    private int usr_sec_id;
    private String ssnPanNo;
    private String panName;
    private String empBankName;
    private String empBankAccNo;
    private String empBankAccName;
    private String empBankIfsc;
    private String empUan;
    private String empPfNo;
    private String empPassportNo;
    private String empPassportExpDate;
    private EmpConfidentialVTO empConfidentialVTO;
    //variables sddress updation
    //to get relateed to names by RK
    private String task_related_to;
    //to get relateed to names by RK
    /*
     * Fields User registration end
     * Date : 03/10/2015
     */
    //Added by jagan 
    private String fromleave;
    private String toleave;
    private String leavetype;
    private String reportsTo;
    private String reason;
    private String leavestatus;
    private String reportPerson;
    private String resultMessage;
    private String redirectAction;
    //////////////////////////////////////////////////////
    /* variable created for education details, created by Aklakh */
    /*variables from here are used in task edit functionality created by Rk*/
    private String taskAttachId;
    private String taskAttachmentPath;
    private String taskid;
    private String fileName;
    private long contentLength;
    private int countryId;
    //////////////////////////////////////////////////////
    private String requirementId;
    private String techReview = "N";
    private int sessionOrgId;
    private int taskType;
    //for adding a vendor to customer
    private int customerId;
    private String status;
    private String accountName;
    private int categoryType;
    private int empId;
//    private int customerId;
    private int primary;
    private int userId;
    private int usrCategoryValue;
    private String usrStatus;
    private String usrDescription;
    private String userName;
    //for home redirection
    private String typeOfUser;
    private String primaryRole;
    private String accountType;
    private int homeRedirectActionId;
    private String accountId;
    private String roleName;
    private String actionName;
    private String homeRedirectDescription;
    private String homeRedirectStatus;
    private String categoryNames;
    private String categoryNamesList;
    private int projectID;
    private int questionNo;
    private int selectedAns;
    private String navigation;
    private int onClickStatus;
    private int remainingQuestions;
    private int subTopicId;
    private int specficQuestionNo;
    private int answer1;
    private int answer2;
    private int answer3;
    private int answer4;
    private int answer5;
    private int answer6;
    private int examId;
    private File file;
    private String fileFileName;
    private String fileFileContentType;
    private String filesPath;
    private int consultantId;
    private String logoFileName;
    private String accLogoHidden;
    
     private int sowId;
     private String acclogo;
    private String poStartDate;
    private String poEndDate;
    private String baseRate;
    private String overTimeRate;
    private String overTimeLimit;
    public UserAjaxHandlerAction() {
    }

    /**
     * DESC : This method is for getting the forgot password METHOD :
     * processForgotPassword CREATED DATE : 02/26/2015
     *
     * @return
     */
    public String processForgotPassword1() throws Exception {

        System.err.println("::::Forgot pwd service::::");
        resultType = "success";
        return resultType;
    }

    public String checkIsExitOrNotForGrouping() {
        resultType = LOGIN;
        try {
            // System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                boolean editoverlay = ServiceLocator.getUserAjaxHandlerService().isUserGroupExist(this.getUserId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write("" + editoverlay);
            }
        } catch (Exception ex) {

            resultType = ERROR;
        }
        return null;
    }

    /**
     *
     * This method is used to set the Servlet Request
     *
     * @param httpServletRequest
     */
    public String processForgotPassword() {
        System.err.println("::::NewForgot pwd service::::");
        int isInsert = 0;
        String resVal = "";
        String resultString = "";
        try {


            resVal = dataSourceDataProvider.getInstance().getUserIdAndStatusByEmail(getEmailId());
            //isInsert= ServiceLocator.getUserAjaxHandlerService().addForGotPasswordDetails(getEmailId(),urlLink,key);
            //System.out.println("isInsert-->"+isInsert);
            System.out.println("resVal-->" + resVal);
            String resVal1[] = resVal.split("^");

            if (resVal.equals("NoRecordExists")) {
                resultString = "<font color=red>No Account is associted with given email id</font>";
            } else {

                // System.out.println("resVal1[0]-->" + resVal.split("\\^")[0]);
                if (resVal.split("\\^")[1].equals("Active")) {
                    // System.out.println("In Active state If");
                    String sessionId16Digit = SecurityServiceProvider.generateRandamSecurityKey(16, 16, 1, 1, 1);
                    String resurl = Properties.getProperty("FORGOTPASSWORDPROCESS.URL").toString();
                    MailManager.sendResetPwdLink(resurl, getEmailId(), sessionId16Digit);
                    // System.out.println("sessionId7Digit-->" + sessionId16Digit);

                    resurl = resurl + "?emailId=" + getEmailId() + "&sessionId=" + sessionId16Digit;

                    isInsert = ServiceLocator.getUserAjaxHandlerService().addForGotPasswordDetails(getEmailId(), resurl, sessionId16Digit);
                    resultString = "<font color=green>Please check your mail to reset your password</font>";
                } else if (resVal.split("\\^")[1].equals("Registered")) {
                    resultString = "<font color=red>Your Account is in registered state .Please contact operation team to activate your account</font>";
                } else if (resVal.split("\\^")[1].equals("InActive")) {
                    resultString = "<font color=red>Your account is in inactive state !!!</font>";
                }
            }
            //String resultString = "ForGotPassword Response String";

            System.err.println("resultString---->" + resultString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(resultString);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

        return null;
    }

    @SuppressWarnings("deprecation")
    public String doUserRegister() throws Exception {

        // System.err.println("objectType==>" + getObjectType());
        // System.err.println("memberId--->" + memberId);
        String generatedPath = "";
        String completePath = "";

        try {
            //  System.out.println("init");
            File f = getPicture();
            System.err.println("file--->file get----->" + f.getName());
            System.err.println("file--->file get----->" + getLivingCountry());
            /*if (this.getPictureFileName().endsWith(".exe")) {
             message = "Notvalid";
             return ERROR;
             }*/

            // generatedPath = generatePath(Properties.getProperty("Profile.Image"));
            generatedPath = Properties.getProperty("Profile.Image");
            if (DataSourceDataProvider.getInstance().getOrgIdByEmailExt(getLoginId()) > 0) {
                generatedPath = generatedPath + "/" + DataSourceDataProvider.getInstance().getOrgIdByEmailExt(getLoginId());
            } else {
                generatedPath = generatedPath;
            }
            // System.out.println("generatedPath-->" + generatedPath);
            File filePath = new File(generatedPath);
            //  System.err.println("filePath--->" + filePath);

            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            FileInputStream inputStream = new FileInputStream(f);
            //  System.out.println("File-->" + getPictureFileName());
            // System.out.println(getFirstName() + getLastName() + "." + getPictureFileName().split("\\.")[1]);
            this.setPictureFileName(getFirstName() + "_" + getLastName() + "." + getPictureFileName().split("\\.")[1]);
            FileOutputStream outputStream = new FileOutputStream(generatedPath + "/" + this.getPictureFileName());
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            completePath = generatedPath + File.separator + this.getPictureFileName();
            setFilePath(completePath);
            //  String extension = getPictureFileName().substring(getPictureFileName().lastIndexOf(".") + 1);
            //System.out.println("File-->" + getPictureFileName());
            // System.out.println("completePath-->" + completePath);
            // int count = ServiceLocator.getUserAjaxHandlerService().doUserRegister(this);
            // System.out.println("count" + count);
            if (ServiceLocator.getUserAjaxHandlerService().doUserRegister(this) > 0) {
                message = "uploaded";
            } else {
                message = "Error";
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            // setMessage("Internal Error!!"); 
            message = "Error";
            return ERROR;
            // httpServletResponse.setStatus(404);
        } finally {
            System.out.close();

        }

        return SUCCESS;
    }

    public String generatePath(String contextPath) throws ServiceLocatorException {

        Date date = new Date();
        String monthName = null;
        String weekName = null;

        /*The path which is created in the drive and used as a home directroy*/
        String generatedPath = contextPath;


        if (date.getMonth() == 0) {
            monthName = "January";
        } else if (date.getMonth() == 1) {
            monthName = "February";
        } else if (date.getMonth() == 2) {
            monthName = "March";
        } else if (date.getMonth() == 3) {
            monthName = "April";
        } else if (date.getMonth() == 4) {
            monthName = "May";
        } else if (date.getMonth() == 5) {
            monthName = "June";
        } else if (date.getMonth() == 6) {
            monthName = "July";
        } else if (date.getMonth() == 7) {
            monthName = "August";
        } else if (date.getMonth() == 8) {
            monthName = "September";
        } else if (date.getMonth() == 9) {
            monthName = "October";
        } else if (date.getMonth() == 10) {
            monthName = "November";
        } else if (date.getMonth() == 11) {
            monthName = "December";
        }
        short week = (short) (Math.round(date.getDate() / 7));

        if (week == 0) {
            weekName = "WeekFirst";
        } else if (week == 1) {
            weekName = "WeekSecond";
        } else if (week == 2) {
            weekName = "WeekThird";
        } else if (week == 3) {
            weekName = "WeekFourth";
        } else if (week == 4) {
            weekName = "WeekFifth";
        }

        /*getrequestType is used to create a directory of the object type specified in the jsp page*/
//        createPath=new File(createPath.getAbsolutePath()+"//MirageV2/+getRequestType()+"/+String.valueOf(date.getYear()+1900)+"/+monthName+"/+weekName);
        generatedPath = generatedPath + "/MSP/"
                + String.valueOf(date.getYear() + 1900)
                + "/" + monthName + "/" + weekName;
        return generatedPath;
    }

    /*methods for contact informaation created by ramakrishna*/
    public String getAddressDetails() {

        resultType = LOGIN;
        String searchAddressDetails = "";
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                searchAddressDetails = ServiceLocator.getUserAjaxHandlerService().getEmpAddressDetails(userid);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(searchAddressDetails);
                httpServletRequest.getSession(false).setAttribute("eduProfile", searchAddressDetails);
                //System.out.println("============================ In Action ============================");
                //System.out.println("address details in aaction :::::"+searchAddressDetails);
            } else {
                //System.out.println("in ifelse edu profile");
                httpServletRequest.getSession(false).setAttribute("eduProfile", null);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        //return searchAddressDetails;
        return null;
    }

    //   setAddressDetails method created by ramakrishna to update permanent address
    public String setAddressDetails() {
        resultType = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int updateDetails = ServiceLocator.getUserAjaxHandlerService().setEmpAddressDetails(userid, address, address2, city, state, zip, country, phone, address_flag);
                // System.out.println("====================================>in action for contact information---------------->");
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(updateDetails);
                httpServletRequest.getSession(false).setAttribute("updateResult", updateDetails);

            } else {
                httpServletRequest.getSession(false).setAttribute("eduProfile", null);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return null;
    }

    //   setCAddressDetails method created by ramakrishna to update current address
    public String setCAddressDetails() {
        resultType = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int updateDetails = ServiceLocator.getUserAjaxHandlerService().setEmpCAddressDetails(userid, address, address2, city, state, zip, country, phone, address_flag);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(updateDetails);
                httpServletRequest.getSession(false).setAttribute("updateResult", updateDetails);

            } else {
                httpServletRequest.getSession(false).setAttribute("eduProfile", null);
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return null;
    }
    /*methods for contactinformation created by ramakrishna end*/

    public String getEmployeeDetails() throws IOException, ServiceLocatorException {
        try {
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getEmployeeDetails(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /* --- Start , skillDetails action add by triveni---   */
//    public String getskillDetails() {
//        resultType = LOGIN;
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                String skillDetails = ServiceLocator.getUserAjaxHandlerService().getSkillDetails(httpServletRequest, userid);
////
////                System.out.println("--------------> if normal skill");
////                System.out.println("" + skillDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(skillDetails);
//
//
//            } else {
////                System.out.println("----------------> else normal skill");
////               
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    public String seteditSkillDetails() {
//        resultType = LOGIN;
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                // System.out.println("---->into action of edit");
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//
//                int setskillDetails = ServiceLocator.getUserAjaxHandlerService().setEditSkillDetails(httpServletRequest, userSessionId, skill_id, this);
//                //System.out.println("--------------> if edit skill action");
//                // System.out.println("" + setskillDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(setskillDetails);
//                resultType = SUCCESS;
//
//            } else {
//                //  System.out.println("----------------> else edit skill action");
//
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    public String geteditOverlayDetails() {
//        resultType = LOGIN;
//        try {
//            // System.out.println("Ajax Handler action");
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                String editoverlay = ServiceLocator.getUserAjaxHandlerService().getEditOverlayDetails(httpServletRequest, userid, skill_id);
//
////                System.out.println("--------------> if overlay skill");
////                System.out.println("" + editoverlay);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(editoverlay);
//
//
//            } else {
//                //   System.out.println("----------------> else overlay skill");
//            }
//        } catch (Exception ex) {
//
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    public String getaddSkills() {
//        resultType = LOGIN;
//        System.out.println("I Qualification Update");
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//                int addDetails = ServiceLocator.getUserAjaxHandlerService().getAddSkills(httpServletRequest, userSessionId, this);
//
//                // System.out.println("in if edu profile");
//                // System.out.println("" + addDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(addDetails);
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", searchDetails);
//
//            }
//        } catch (Exception ex) {
//            //  httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    /* --- End , skillDetails action add by triveni---   */
//    /* --- Start , EducationaDetails action add by aklakh---   */
//    public String getEduDetails() {
//        resultType = LOGIN;
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                String searchDetails = ServiceLocator.getUserAjaxHandlerService().getQualificationDetails(httpServletRequest, userid);
//
//                // System.out.println("details in action" + searchDetails);
//                // System.out.println("" + searchDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(searchDetails);
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", searchDetails);
//
//            } else {
//                // System.out.println("in ifelse edu profile");
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", null);
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//            // httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    /* --- End , EducationaDetails action add by aklakh---   */
//    /* --- Start , ADD Qualification action add by aklakh---   */
//    public String addQualificationDetails() {
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//
//                int searchDetails = ServiceLocator.getUserAjaxHandlerService().getInsertedQualificationDetails(httpServletRequest, userSessionId, this);
//
//                //  System.out.println("in if edu profile");
//                // System.out.println("" + searchDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(searchDetails);
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", searchDetails);
//
//            } else {
//                //   System.out.println("in ifelse edu profile");
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", null);
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    /* --- End , Add Qualification  action add by aklakh---   */
//    /* --- Start , Update Qualification  action add by aklakh---   */
//    public String getEduUpdate() {
//        resultType = LOGIN;
//        try {
//            // System.out.println("getusr_edu_id---> "+usr_edu_id);
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                //  System.out.println("---->"+usr_edu_id);
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//
//                int editEduDetails = ServiceLocator.getUserAjaxHandlerService().getEduUpdateDetails(httpServletRequest, usr_edu_id, this);
//
//                // System.out.println("--------------> if update qualification action");
//                //System.out.println("" + editEduDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(editEduDetails);
//                resultType = SUCCESS;
//
//            } else {
//                System.out.println("----------------> else update qualificaton action");
//
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//
//            resultType = ERROR;
//        }
//        return null;
//    }
//
//    public String getEditEduDetails() {
//        resultType = LOGIN;
//        try {
//            //  System.out.println("Ajax Handler action");
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                String editEduOverlay = ServiceLocator.getUserAjaxHandlerService().getEditQualificationDetails(httpServletRequest, userid, usr_edu_id);
//
//                //System.out.println("--------------> if overlay qualification");
//                //System.out.println("" + editEduOverlay);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(editEduOverlay);
//
//
//            } else {
//                // System.out.println("----------------> else overlay qualification");
//            }
//        } catch (Exception ex) {
//
//            resultType = ERROR;
//        }
//        return null;
//    }

    /* --- End , Update Qualification  action add by aklakh---   */
    /* ---START ReportInformation  action add by RK---   */
//    public String getReportData() {
//        resultType = LOGIN;
//        String reportInfo = "";
//        System.out.println("********************Action*****************");
//        try {
//            //  System.out.println("Ajax Handler action");
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                reportInfo = ServiceLocator.getUserAjaxHandlerService().getReportInfo(httpServletRequest, userid);
//                //       System.out.println("===============>in action" + reportInfo);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(reportInfo);
//                //resultType = SUCCESS;
//
//            } else {
//                System.out.println("----------------> else overlay qualification");
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//
//            resultType = ERROR;
//        }
//        return null;
//    }
//    //role submit action 
    public String roleSubmit() {
        resultType = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int orgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                String action = ServiceLocator.getUserAjaxHandlerService().roleSubmit(httpServletRequest, getRoleId(), orgId);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(action);
            }
        } catch (Exception ex) {

            resultType = ERROR;
        }
        return null;
    }
    /*END Report information created by RK*/

    /**
     *
     * This method is used to getting Title Names based on Department Name
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
//    public String getTitles() {
//        resultType = LOGIN;
//        String repoString = "";
//        try {
//            //  System.out.println("Ajax Handler action");
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                repoString = ServiceLocator.getUserAjaxHandlerService().getTitles(httpServletRequest, getDept_id());
//                //System.out.println("===============>in titles" + repoString);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(repoString);
//
//                //  resultType = SUCCESS;
//            } else {
//                return resultType;
//            }
//        } catch (Exception e) {
//            resultType = ERROR;
//        }
//        return null;
//    }
    /**
     *
     * This method is used to Update miscellaneous Information
     *
     * added by praveen<pkatru@miraclesoft.com>
     */
//    public String updateMiscellaneousInfo() {
//        resultType = LOGIN;
//        try {
//            // System.out.println("Ajax Handler action");
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//
//                ServiceLocator.getUserAjaxHandlerService().updateMiscellaneousInfo(httpServletRequest, this);
//                //  System.out.println("in action ajax class updateMiscell......");
//                //  resultType = SUCCESS;
//            } else {
//                return resultType;
//            }
//        } catch (Exception e) {
//            resultType = ERROR;
//        }
//        return null;
//    }
//    public String getSecurityDetails() throws ServiceLocatorException {
//
//        resultType = LOGIN;
//        //System.out.println("getSecurity Details");
//
//
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                //System.out.println("getSecurity Details session");
//                //int usrId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//                //System.out.println("UesrID-->" + getUserid());
//                String securityDetails = ServiceLocator.getUserAjaxHandlerService().getSecurityDetails(httpServletRequest, getUserid());
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(securityDetails);
//                httpServletRequest.getSession(false).setAttribute("security", securityDetails);
//
//            } else {
//                //     System.out.println("in else security details");
//                httpServletRequest.getSession(false).setAttribute("security", null);
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//            System.out.println("exception in getSecurityDetails action-->" + ex.getMessage());
//            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return null;
//
//
//
//    }
    //START Secrity Information  action add by Manikanta
//    public String addSecurityDetails() {
//
//        resultType = LOGIN;
//        //System.out.println("getSecurity Details");
//        //System.out.println("PF no is:" + getEmpPfNo());
//        //System.out.println("Emp Acc Name is:" + getEmpBankAccName());
//        EmpConfidentialVTO empVto = new EmpConfidentialVTO();
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                // System.out.println("getSecurity Details session");
//                //int usrId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//                ///// System.out.println("UesrID-->" + getUserid());
//                // System.out.println("Object is" + empConfidentialVTO);
//                empVto.setUserid(getUserid());
//                empVto.setSsnPanNo(getSsnPanNo());
//                empVto.setPanName(getPanName());
//                empVto.setEmpUan(getEmpUan());
//                empVto.setEmpPfNo(getEmpPfNo());
//                empVto.setEmpPassportNo(getEmpPassportNo());
//                empVto.setEmpPassportExpDate(com.mss.msp.util.DateUtility.getInstance().convertStringToMySql(getEmpPassportExpDate()));
//                //System.out.println("Date is" + com.mss.msp.util.DateUtility.getInstance().convertStringToMySql(getEmpPassportExpDate()));
//
//                empVto.setEmpBankName(getEmpBankName());
//                empVto.setEmpBankIfsc(getEmpBankIfsc());
//                empVto.setEmpBankAccNo(getEmpBankAccNo());
//                empVto.setEmpBankAccName(getEmpBankAccName());
//
//                String updateSecurityDetails = ServiceLocator.getUserAjaxHandlerService().updateSecurityDetails(httpServletRequest, empVto);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(updateSecurityDetails);
//                httpServletRequest.getSession(false).setAttribute("security", updateSecurityDetails);
//
//            } else {
//                // System.out.println("in else security details");
//                httpServletRequest.getSession(false).setAttribute("security", null);
//
//            }
//        } catch (Exception ex) {
//            //System.out.println("exception in getSecurityDetails action-->" + ex.getMessage());
//            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return null;
//
//    }

    /* Start, Task types data in ADD task functionality , created by Aklakh */
    public String getTaskTypeData() {
        resultType = LOGIN;
        String taskString = "";
        try {
            //  System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                taskString = ServiceLocator.getUserAjaxHandlerService().getTypesOfTask(this);
                System.out.println("===============>in task" + taskString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(taskString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /* End, Task types data in ADD task functionality , created by Aklakh */
    public String getRelatedToNames() {
        resultType = LOGIN;
        String taskString = "";
        try {
            //  System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                taskString = ServiceLocator.getUserAjaxHandlerService().getRelatedToNames(this);
                //taskString=dataSourceDataProvider.getInstance().getTeamMembers(getUserSessionId());
                System.out.println("===============>in task" + taskString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(taskString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /* *************************************
     *
     * @showTaskSearchDetails() method is used to get task details based on
     * search scenario
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     *
     **************************************
     */
    public String getAttachment() {
        resultType = LOGIN;
        String attachmentString = "";
        try {
            //  System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                attachmentString = ServiceLocator.getUserAjaxHandlerService().getAttachment(this);
                System.out.println("===============>in Action----->" + attachmentString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(attachmentString);
                //resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }
    /* *************************************
     *
     * @showTaskSearchDetails() method is used to get task details based on
     * search scenario
     *
     * @Author:RK Ankireddy
     *
     * @Created Date:04/15/2015
     *
     *
     **************************************
     */

    public String doDeactiveAttachment() {
        System.out.println("Enter the new dragon action    " + getTaskAttachId());
        resultType = LOGIN;
        int attachmentString = 0;
        try {
            //  System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));

                attachmentString = ServiceLocator.getUserAjaxHandlerService().doDeactiveAttachment(this);
                System.out.println("===============>in Action----->" + attachmentString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(attachmentString);
                //resultType = SUCCESS;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : April 15 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * addLeaveDetails() method can be used to add the leave by the user using
     * user id,
     *
     * *****************************************************************************
     */
//    public String addLeaveDetails() {
//        resultType = LOGIN;
////        System.out.println("In Qualification adding1==========>" + getUserid() + "and " + getQualification() + "and " + getPercentage());
////        System.out.println("In Qualification adding2==========>" + getUniversity() + " and " + getInstitution() + " and " + getSpecialization());//+" and  "+getYear_start()+" and "+getYear_end());
////        System.out.println("In Qualification adding2==========>" + getYear_start() + " and " + getYear_end());
//        try {
//
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
//                System.out.println("leave from-->" + getFromleave());
//                System.out.println("leave to-->" + getToleave());
//                System.out.println("leave type-->" + getLeavetype());
//                System.out.println("reports to-->" + getReportsTo());
//                System.out.println("reasson is-->" + getReason());
//                System.out.println("Status  is-->" + getLeavestatus());
//                System.out.println("reported persons is-->" + getReportPerson());
////                String reportingPerson=DataSourceDataProvider.getInstance().getReportingPersonByUserId(userSessionId);
////                System.out.println("---------> "+reportingPerson);
////                if(" ".equals(reportingPerson))
////                {
////                System.out.println("reports to is null");
//////                setResultMessage("Please contact Support team!");
//////                httpServletResponse.sendRedirect("../general/regerrorDirect.action?resultMessage="+getResultMessage());
//////                //setRedirectAction("../general/regerrorDirect.action?resultMessage="+getResultMessage()); 
//////                resultType = SUCCESS;
////                }
//                int leaveDetails = ServiceLocator.getUserAjaxHandlerService().getInsertedLeaveDetails(httpServletRequest, userSessionId, this);
//                System.out.println("insetted rows------->" + leaveDetails);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                //httpServletResponse.getWriter().write(leaveDetails);
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", searchDetails);
//
//            } else {
//                //   System.out.println("in ifelse edu profile");
//                // httpServletRequest.getSession(false).setAttribute("eduProfile", null);
//                resultType = SUCCESS;
//            }
//        } catch (Exception ex) {
//            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
//            resultType = ERROR;
//        }
//        return null;
//    }
    public String addLeaveDetails() {
        resultType = LOGIN;
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                System.out.println("add Leave details method---123---->");
                int leaveDetails = ServiceLocator.getUserAjaxHandlerService().getInsertedLeaveDetails(userSessionId, this);
                System.out.println("insetted rows---123---->" + leaveDetails);
                //manager
                //String leaveloginid=
                String username = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.FIRST_NAME).toString() + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.Last_NAME).toString();
                String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString();
                System.out.println("userid---" + userId);
                DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
                System.out.println("DSDP intial------->");
                List rep = null; //dsdp.getReportsTo(Integer.parseInt(userId));
                //please update code over here for unused table
                //List rep=dsdp.getReportsTo(1003);
                System.out.println("DSDP after------->");
                System.out.println("reporting_persons" + rep);
                System.out.println("TO" + rep.get(0));
                System.out.println("SUp" + rep.get(1));
                if (rep.size() > 1) {
                    setLeaveTo(rep.get(0).toString());
                    setLeaveSupTo(rep.get(1).toString());
                }
                // System.out.println("to--->"+to+"sup-->"+supTo);
                //String to="vsiram";
                //String leaveCc="kpodipireddy";
                if (leaveDetails == 1) {
                    MailManager mailManager = new MailManager();
                    mailManager.sendLeaveEmail(getLeaveTo(), getLeaveSupTo(), "", username, reason, leavestatus, fromleave, toleave, leavetype, userSessionId);
                }
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");

            } else {

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return null;
    }
    //MANIKANTA

//    public String getEmployeeNames() {
//        resultType = LOGIN;
//        String reponseString = "";
//        try {
//            //  System.out.println("Ajax Handler action");
//            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
//                reponseString = ServiceLocator.getUserAjaxHandlerService().getEmployeeNames(httpServletRequest, getDept_id());
//                //System.out.println("===============>in titles" + repoString);
//                httpServletResponse.setContentType("text");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(reponseString);
//
//                //  resultType = SUCCESS;
//            } else {
//                return resultType;
//            }
//        } catch (Exception e) {
//            resultType = ERROR;
//        }
//        return null;
//    }
    public String getStatesOfCountry() {

        try {
            System.out.println("I am   in States action");
            // Map countries =ServiceLocator.getLocationService().getCountriesNames();
            System.out.println("id is" + getCountryId());
            String stateList = ServiceLocator.getUserAjaxHandlerService().getStatesOfCountry(getCountryId());
            //setStateMap(states);

            System.out.println("list of States----->" + stateList);

            // System.out.println("list od countries----->"+getCounryMap());
            //System.out.println(getResultMessage());
            //setResultMessage(getResultMessage());
            //httpServletRequest.setAttribute("stateList", stateList);
            setResultMessage(getResultMessage());
            httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("text");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(stateList);
            // httpServletRequest.setAttribute("stateList", stateList);
            //System.out.println(httpServletRequest.getAttribute("statesList"));
            // resultType = SUCCESS;

        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }


        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * LeavesListOfUser() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String LeavesListOfUser() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            //System.out.println("hello i am in action..");
            try {
                //System.out.println("in if leave sarech");
                String leavesListDetails = ServiceLocator.getUserAjaxHandlerService().getLeavesListDetails(this);
                System.out.println("leavesListDetails" + leavesListDetails);
                int userId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                //setReportPerson(DataSourceDataProvider.getInstance().getReportingPersonByUserId(userId));
                //please change the getreporting person method code.
                //System.out.println("reporting person---->" + getReportingPerson());
                System.out.println("leave list ------ " + leavesListDetails.length());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(leavesListDetails);


            } catch (Exception ex) {
                //List errorMsgList = ExceptionToListUtility.errorMessagetUserLeavesServiceges(ex);
                System.out.println("I am in error" + ex.toString());
                httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());


                resultMessage = ERROR;
            }
        }// Session validator if END
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * LeavesListOfUser() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String getExternalEmployeeDetails() throws IOException, ServiceLocatorException {
        try {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getExternalEmployeeDetails(this);
//            httpServletResponse.setContentType("text/xml");
//            httpServletResponse.getWriter().write(responseString);
            
            httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(responseString);


        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getTechEmployeeDetails() throws IOException, ServiceLocatorException {
        try {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getTechEmployeeDetails(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * getExternalEmployee2Details() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String getExternalEmployee2Details() throws IOException, ServiceLocatorException {
        try {
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getExternalEmployee2Details(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * getVendorNames() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String getVendorNames() throws IOException, ServiceLocatorException {
        try {
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getVendorNames(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : july 15 2015
     *
     * Author : jagan<jchukkala@miraclesoft.com>
     *
     * getCsrNames() getting the csr names
     *
     *
     * *****************************************************************************
     */
    public String getCsrNames() throws IOException, ServiceLocatorException {
        try {
            //setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            //System.out.println("csr name" + getCsrName());
            responseString = ServiceLocator.getUserAjaxHandlerService().getCsrNames(getCsrName());
            //System.out.println("csrName---->" + responseString);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : july 15 2015 Author : manikanta<meeralla@miraclesoft.com>
     * csrTermination() Inactivating the csrs
     * *****************************************************************************
     */
    public String csrTermination() throws IOException, ServiceLocatorException {
        try {
            System.out.println("In Action--> csrTermination()");
            System.out.println("userid" + getUserid());

            //setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().csrTermination(getUserid());
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : july 16 2015 Author : manikanta<meeralla@miraclesoft.com>
     * changeCsrAccountStatus() changing the ststus of csr
     * *****************************************************************************
     */
    public String changeCsrAccountStatus() throws IOException, ServiceLocatorException {
        try {
            System.out.println("In Action--> changeCsrAccountStatus()");
            System.out.println("csruserid" + getUserid());
            System.out.println("csrOrgid" + getOrgId());

            //setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().changeCsrAccountStatus(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : july 16 2015 Author : manikanta<meeralla@miraclesoft.com>
     * getCsrAccount() getting csr Account
     * *****************************************************************************
     */
    public String getCsrAccount() throws IOException, ServiceLocatorException {
        try {
            System.out.println("In Action--> getCsrAccount()");
            System.out.println("csruserid" + getUserid());
            System.out.println("csrOrgid" + getOrgId());

            //setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getCsrAccount(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : july 17 2015 Author : manikanta<meeralla@miraclesoft.com>
     * getEmpCategories() getting Customer Employee Categories
     * *****************************************************************************
     */
    public String getEmpCategories() throws IOException, ServiceLocatorException {
        try {
            System.out.println("In Action--> getEmpCategories()");
            //System.out.println("csruserid"+getUserid());
            //System.out.println("csrOrgid"+getOrgId());

            //setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            sessionOrgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            responseString = ServiceLocator.getUserAjaxHandlerService().getEmpCategories(this, sessionOrgId);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String visaAttachemntUpload() {

        try {

            System.out.println("visaAttachemntUpload-->" + getFileFileName());
            File destFile = null;
            if (getFileFileName() == null) {
                System.out.println("file is null so it adds only data in task_list table");
            } else {
                // String visaAttachPath = dataSourceDataProvider.getInstance().getConsultVisaAttachment(getConsultantId());

                // System.out.println("visaAttachPath from db-->" + visaAttachPath);


                String filePath = "";
                filesPath = Properties.getProperty("Visa.Attachment");
                File createPath = new File(filesPath);
                Date dt = new Date();
                String month = "";
                short week = 0;



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
                week = (short) (Math.round(dt.getDate() / 7));

                /*getrequestType is used to create a directory of the object type specified in the jsp page*/
                createPath = new File(createPath.getAbsolutePath() + "/" + String.valueOf(dt.getYear() + 1900) + "/" + month + "/" + String.valueOf(week));
                /*This creates a directory forcefully if the directory does not exsist*/

                //System.out.println("path::"+createPath);
                createPath.mkdir();
                /*here it takes the absolute path and the name of the file that is to be uploaded*/
                File theFile = new File(createPath.getAbsolutePath());

                setFilesPath(theFile.toString());
                /*copies the file to the destination*/
                destFile = new File(theFile + File.separator + fileFileName);
                FileUtils.copyFile(getFile(), destFile);

            }


            System.out.println("this is printing file path-->" + this.getFilesPath() + "...." + fileFileName);
            // setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));






            if (destFile == null) {
                responseString = "Error";
            }
            if (ServiceLocator.getUserAjaxHandlerService().doUpdateVisaAttachment(getConsultantId(), destFile.toString()) > 0) {
                responseString = "uploaded";
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String removeConsultantAttachement() {
        /*
         *This if loop is to check whether there is Session or not
         **/
        File destFile1 = null;
        String updateFile = "";
        try {
            String visaAttachPath = dataSourceDataProvider.getInstance().getConsultVisaAttachment(getConsultantId());

            if (visaAttachPath != null) {

                destFile1 = new File(visaAttachPath);
                System.out.println("destFile1--->" + destFile1);
                destFile1.delete();
                updateFile = "";
            }
            System.out.println("destFile1--->" + destFile1);

            int updatedRows = ServiceLocator.getUserAjaxHandlerService().doUpdateVisaAttachment(getConsultantId(), updateFile);

            responseString = String.valueOf(updatedRows);
//responseString =String.valueOf(lastId);
            System.out.println("responseString-->" + responseString);
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (Exception ex) {
        }

        //Close Session Checking
        return null;
    }
    //praveen

    public String getUserCatArry() {
        return userCatArry;
    }

    public void setUserCatArry(String userCatArry) {
        this.userCatArry = userCatArry;
    }
    private String userCatArry;

    public String doUserGroupingMethod() throws ServiceLocatorException {
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("user id is--->" + this.getUserId());
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                responseString = ServiceLocator.getUserAjaxHandlerService().doUserGroupingMethod(this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;

    }

    /**
     * *****************************************************************************
     * Date : july 17 2015 Author : manikanta<meeralla@miraclesoft.com>
     * empCategoryTermination() For terminating Employee Categories
     * *****************************************************************************
     */
    public String empCategoryTermination() throws IOException, ServiceLocatorException {
        try {
            System.out.println("In Action--> empCategoryTermination()");
            sessionOrgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
            responseString = ServiceLocator.getUserAjaxHandlerService().empCategoryTermination(this, sessionOrgId);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *************************************
     *
     * @getHomeRedirectSearchDetails() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String getHomeRedirectSearchDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setAccountType(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                String result = ServiceLocator.getUserAjaxHandlerService().getHomeRedirectSearchDetails(this);
                System.out.println("in getHomeRedirectSearchDetails action" + result);
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
     * @getHomeRedirectSearchDetails() method is used to get Requirement details
     * of account
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:05/18/2015
     *
     **************************************
     */
    public String getRolesByAccountType() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String result = com.mss.msp.util.DataSourceDataProvider.getInstance().getAllRolesString(getAccountType());
                System.out.println("in getHomeRedirectSearchDetails action" + result);
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
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * LeavesListOfUser() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String getAccountNames() throws IOException, ServiceLocatorException {
        try {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getAccountNames(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * LeavesListOfUser() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String storeAddOrEditHomeRedirectDetails() throws IOException, ServiceLocatorException {
        try {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().storeAddOrEditHomeRedirectDetails(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * LeavesListOfUser() getting the user leaves
     *
     *
     * *****************************************************************************
     */
    public String getHomeRedirectCommentsDetails() throws IOException, ServiceLocatorException {
        try {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getHomeRedirectCommentsDetails(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 15 2015
     *
     * Author : manikanta<meeralla@miraclesoft.com>
     *
     * getEmpCategoryNames() getting the get EmpCategoryNames
     *
     *
     * *****************************************************************************
     */
    public String getEmpCategoryNames() throws IOException, ServiceLocatorException {
        try {
            // setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            // setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getEmpCategoryNames(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }
    
    public String logoUploadAccount() throws Exception {


        try {

            System.out.println("visaAttachemntUpload-->" + getFileFileName());
            File destFile = null;
             File destFile1 = null;
               String updateFile = "";
               System.out.println("logo hidden-->"+getAccLogoHidden());
              System.out.println("property file-->"+Properties.getProperty("DEFAULTLOGO")); 
            if(!"".equals(getAccLogoHidden()) && !getAccLogoHidden().equals(Properties.getProperty("DEFAULTLOGO")))
            {
                System.out.println("DELETING");
                 if (getAccLogoHidden() != null) {
                      System.out.println("DELETING null");
                destFile1 = new File(getAccLogoHidden());
                System.out.println("destFile1--->" + destFile1);
                destFile1.delete();
                updateFile = "";
            }
            System.out.println("destFile1 --->" + destFile1);
            }
            System.out.println("account logo--->"+getFileFileName());
            if (getFileFileName() == null) {
                System.out.println("file is null so it adds only data in task_list table");
            } else {
                // String visaAttachPath = dataSourceDataProvider.getInstance().getConsultVisaAttachment(getConsultantId());

                // System.out.println("visaAttachPath from db-->" + visaAttachPath);

                  String basename = FilenameUtils.getBaseName(fileFileName);
                    String extension = FilenameUtils.getExtension(fileFileName);
                String filePath = "";
                filesPath = Properties.getProperty("ACCOUNTLOGO");
                File createPath = new File(filesPath);
                Date dt = new Date();
                String month = "";
                short week = 0;



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
                week = (short) (Math.round(dt.getDate() / 7));

                /*getrequestType is used to create a directory of the object type specified in the jsp page*/
//                createPath = new File(createPath.getAbsolutePath() + "/" + String.valueOf(dt.getYear() + 1900) + "/" + month + "/" + String.valueOf(week));
                createPath = new File(createPath.getAbsolutePath() );
                /*This creates a directory forcefully if the directory does not exsist*/

                //System.out.println("path::"+createPath);
                createPath.mkdir();
                /*here it takes the absolute path and the name of the file that is to be uploaded*/
                File theFile = new File(createPath.getAbsolutePath()+ File.separator + getAccountId() + '.' + extension);
               // File destFile = new File(filePath + File.separator + getContactId() + '.' + extension);
                setFilesPath(theFile.toString());
                /*copies the file to the destination*/
                //fileFileName=getAccountId();
                destFile = new File(theFile + File.separator);
                FileUtils.copyFile(getFile(), destFile);
               
               
            }


            System.out.println("this is printing file path data-->" + this.getFilesPath() + "...." + fileFileName);
            // setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            System.out.println("dest file--->"+destFile);
           // ServiceLocator.getUserAjaxHandlerService().doUpdateLogo(getAccountId(), destFile.toString());




            if (destFile == null) {
                responseString = "Error";
            }
            if (ServiceLocator.getUserAjaxHandlerService().doUpdateLogo(getAccountId(), destFile.toString()) > 0) {
                responseString = destFile.toString();
            } else {
                responseString = "Error";
            }
            httpServletResponse.setContentType("text");
            httpServletResponse.getWriter().write(responseString);
        } catch (ServiceLocatorException ex) {
            System.err.println(ex);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public int getUsrCatType() {
        return usrCatType;
    }

    public void setUsrCatType(int usrCatType) {
        this.usrCatType = usrCatType;
    }
    private int usrCatType;
    //praveen

    public String getCategoryList() throws ServiceLocatorException {
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                responseString = ServiceLocator.getUserAjaxHandlerService().getCategoryList(this);
                httpServletResponse.setContentType("text/xml");
                httpServletResponse.getWriter().write(responseString);
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String checkFileNameExistOrNot() {
        System.out.println("in action");
        resultType = LOGIN;
        String isExist = null;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                //  isExist = ServiceLocator.getUserAjaxHandlerService().checkFileName(this);
                System.out.println("-----cheking file name exsit or not-------" + isExist);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(isExist);
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
            e.printStackTrace();
        }
        return null;
    }

    public String getQuestion() {
        try {
            /*
             *This if loop is to check whether there is Session or not
             **/
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID) != null) {
                //System.out.println(getAnswer1()+""+getAnswer2()+""+getAnswer3()+""+getAnswer4()+""+getAnswer5()+""+getAnswer6());
                // responseString = ServiceLocator.getAjaxHandlerService().getQuestion(getQuestionNo(),httpServletRequest,getSelectedAns(),getNavigation(),getRemainingQuestions(),getOnClickStatus(),getSubTopicId());

                responseString = ServiceLocator.getUserAjaxHandlerService().getQuestion(getQuestionNo(), httpServletRequest, getSelectedAns(), getNavigation(), getRemainingQuestions(), getOnClickStatus(), getSubTopicId(), getSpecficQuestionNo(), this);
                httpServletResponse.setContentType("text/xml");
                System.out.println("responseString-->" + responseString);
                httpServletResponse.getWriter().write(responseString);
            }//Close Session Checking
        } catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getEmpRecruitment() throws IOException, ServiceLocatorException {
        try {
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            responseString = ServiceLocator.getUserAjaxHandlerService().getEmpRecruitment(this);
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
    }

    public String getActionNames() {
        resultMessage = LOGIN;
        List<String> ActionNames = new ArrayList<String>();
        String result = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String accType = (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                ActionNames = com.mss.msp.util.DataSourceDataProvider.getInstance().getActionNamesList(getSessionOrgId(), roleId, accType);
                System.out.println("----------------------------list--------------------" + ActionNames);
                for (int i = 0; i < ActionNames.size(); i++) {
                    result += ActionNames.get(i) + "|";
                }
                //result+="^";
                System.out.println("in getHomeRedirectSearchDetails action" + result);
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

    public String getActionDescription() {
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                String result = com.mss.msp.util.DataSourceDataProvider.getInstance().getActionDescription(getActionName());
                System.out.println("in getHomeRedirectSearchDetails action" + result);
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
    public String poReleaseMethod() throws IOException, ServiceLocatorException, Exception {
        try {
            MailManager mailManager = new MailManager();
            setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            //responseString = ServiceLocator.getUserAjaxHandlerService().getEmpRecruitment(this);
            //System.out.println("heelo babu--->"+Properties.getProperty("Sample.upload"));
            //ServiceLocator.getUserAjaxHandlerService().poRelease(this);
            String customerName = DataSourceDataProvider.getInstance().getAccountNameById(getSessionOrgId());


            responseString = ServiceLocator.getUserAjaxHandlerService().poRelease(this);
            //filesPath = Properties.getProperty("POATTACHMENT").toString();


            File createPath = new File(Properties.getProperty("POATTACHMENT").toString());

            System.out.println("createPath.getAbsolutePath()-->" + createPath.getAbsolutePath());







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
            // createPath = new File(createPath.getAbsolutePath() + "/" + getSessionOrgId() + "/" + String.valueOf(dt.getYear() + 1900) + "/" + month + "/" + String.valueOf(week));
            /*This creates a directory forcefully if the directory does not exsist*/




            String poFileName = getRequirementId() + "_" + getUserId() + "PO";
            poFileName = poFileName.concat(".pdf");

            String path = createPath.getAbsolutePath() + File.separator + File.separator + getSessionOrgId() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week) + File.separator + poFileName;
// Use relative path for Unix systems
            String filePath = createPath.toString() + File.separator + getSessionOrgId() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week) + File.separator;
            String fileAttch = filePath.concat(poFileName);

            System.out.println("fileAttch-->" + fileAttch + "--->filepath--->" + filePath);
            File f = new File(path);

            f.getParentFile().mkdirs();
            f.createNewFile();





            /*copies the file to the destination*/
            //   File destFile = new File(theFile + File.separator + poFileName);




            PDFGenerator.getPOPDF(this, f.toString(), customerName);
            System.out.println("-->" + f.toString() + "-->" + poFileName + "--->" + f.toString());
            ServiceLocator.getUserAjaxHandlerService().insertPoAttachment(this, f.toString(), filePath, poFileName);
            System.out.println("account logo--->" + getAcclogo());
            mailManager.sendPOStatement(this, getAcclogo(), fileAttch, poFileName, customerName);

            responseString = "send";
            httpServletResponse.setContentType("text/xml");
            httpServletResponse.getWriter().write(responseString);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return null;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the officeAddress1
     */
    public String getOfficeAddress1() {
        return officeAddress1;
    }

    /**
     * @param officeAddress1 the officeAddress1 to set
     */
    public void setOfficeAddress1(String officeAddress1) {
        this.officeAddress1 = officeAddress1;
    }

    /**
     * @return the officeAddress2
     */
    public String getOfficeAddress2() {
        return officeAddress2;
    }

    /**
     * @param officeAddress2 the officeAddress2 to set
     */
    public void setOfficeAddress2(String officeAddress2) {
        this.officeAddress2 = officeAddress2;
    }

    /**
     * @return the officeCity
     */
    public String getOfficeCity() {
        return officeCity;
    }

    /**
     * @param officeCity the officeCity to set
     */
    public void setOfficeCity(String officeCity) {
        this.officeCity = officeCity;
    }

    /**
     * @return the officeState
     */
    public String getOfficeState() {
        return officeState;
    }

    /**
     * @param officeState the officeState to set
     */
    public void setOfficeState(String officeState) {
        this.officeState = officeState;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the officePhone
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * @param officePhone the officePhone to set
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * @return the picture
     */
    public File getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(File picture) {
        this.picture = picture;
    }

    /**
     * @return the pictureFileName
     */
    public String getPictureFileName() {
        return pictureFileName;
    }

    /**
     * @param pictureFileName the pictureFileName to set
     */
    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    /**
     * @return the pictureFileContentType
     */
    public String getPictureFileContentType() {
        return pictureFileContentType;
    }

    /**
     * @param pictureFileContentType the pictureFileContentType to set
     */
    public void setPictureFileContentType(String pictureFileContentType) {
        this.pictureFileContentType = pictureFileContentType;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the regType
     */
    public String getRegType() {
        return regType;
    }

    /**
     * @param regType the regType to set
     */
    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getLivingCountry() {
        return livingCountry;
    }

    public void setLivingCountry(String livingCountry) {
        this.livingCountry = livingCountry;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the officeCountry
     */
    public String getOfficeCountry() {
        return officeCountry;
    }

    /**
     * @param officeCountry the officeCountry to set
     */
    public void setOfficeCountry(String officeCountry) {
        this.officeCountry = officeCountry;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public int getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(int skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_rate() {
        return skill_rate;
    }

    public void setSkill_rate(String skill_rate) {
        this.skill_rate = skill_rate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getUsr_edu_id() {
        return usr_edu_id;
    }

    public void setUsr_edu_id(int usr_edu_id) {
        this.usr_edu_id = usr_edu_id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getYear_start() {
        return year_start;
    }

    public void setYear_start(String year_start) {
        this.year_start = year_start;
    }

    public String getYear_end() {
        return year_end;
    }

    public void setYear_end(String year_end) {
        this.year_end = year_end;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getEnd_year() {
        return end_year;
    }

    public void setEnd_year(String end_year) {
        this.end_year = end_year;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public int getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(int isTeam) {
        this.isTeam = isTeam;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getIsPmo() {
        return isPmo;
    }

    public void setIsPmo(int isPmo) {
        this.isPmo = isPmo;
    }

    public int getIsOpt() {
        return isOpt;
    }

    public void setIsOpt(int isOpt) {
        this.isOpt = isOpt;
    }

    public int getIsSbteam() {
        return isSbteam;
    }

    public void setIsSbteam(int isSbteam) {
        this.isSbteam = isSbteam;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public int getUsr_sec_id() {
        return usr_sec_id;
    }

    public void setUsr_sec_id(int usr_sec_id) {
        this.usr_sec_id = usr_sec_id;
    }

    public String getSsnPanNo() {
        return ssnPanNo;
    }

    public void setSsnPanNo(String ssnPanNo) {
        this.ssnPanNo = ssnPanNo;
    }

    public String getPanName() {
        return panName;
    }

    public void setPanName(String panName) {
        this.panName = panName;
    }

    public String getEmpBankName() {
        return empBankName;
    }

    public void setEmpBankName(String empBankName) {
        this.empBankName = empBankName;
    }

    public String getEmpBankAccNo() {
        return empBankAccNo;
    }

    public void setEmpBankAccNo(String empBankAccNo) {
        this.empBankAccNo = empBankAccNo;
    }

    public String getEmpBankAccName() {
        return empBankAccName;
    }

    public void setEmpBankAccName(String empBankAccName) {
        this.empBankAccName = empBankAccName;
    }

    public String getEmpBankIfsc() {
        return empBankIfsc;
    }

    public void setEmpBankIfsc(String empBankIfsc) {
        this.empBankIfsc = empBankIfsc;
    }

    public String getEmpUan() {
        return empUan;
    }

    public void setEmpUan(String empUan) {
        this.empUan = empUan;
    }

    public String getEmpPfNo() {
        return empPfNo;
    }

    public void setEmpPfNo(String empPfNo) {
        this.empPfNo = empPfNo;
    }

    public String getEmpPassportNo() {
        return empPassportNo;
    }

    public void setEmpPassportNo(String empPassportNo) {
        this.empPassportNo = empPassportNo;
    }

    public String getEmpPassportExpDate() {
        return empPassportExpDate;
    }

    public void setEmpPassportExpDate(String empPassportExpDate) {
        try {
            this.empPassportExpDate = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate(empPassportExpDate);
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(UserAjaxHandlerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EmpConfidentialVTO getEmpConfidentialVTO() {
        return empConfidentialVTO;
    }

    public void setEmpConfidentialVTO(EmpConfidentialVTO empConfidentialVTO) {
        this.empConfidentialVTO = empConfidentialVTO;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getTask_related_to() {
        return task_related_to;
    }

    public void setTask_related_to(String task_related_to) {
        this.task_related_to = task_related_to;
    }

    public String getFromleave() {
        return fromleave;
    }

    public void setFromleave(String fromleave) {
        this.fromleave = fromleave;
    }

    public String getToleave() {
        return toleave;
    }

    public void setToleave(String toleave) {
        this.toleave = toleave;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeavestatus() {
        return leavestatus;
    }

    public void setLeavestatus(String leavestatus) {
        this.leavestatus = leavestatus;
    }

    public String getReportPerson() {
        return reportPerson;
    }

    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getRedirectAction() {
        return redirectAction;
    }

    public void setRedirectAction(String redirectAction) {
        this.redirectAction = redirectAction;
    }

    public String getTaskAttachId() {
        return taskAttachId;
    }

    public void setTaskAttachId(String taskAttachId) {
        this.taskAttachId = taskAttachId;
    }

    public String getTaskAttachmentPath() {
        return taskAttachmentPath;
    }

    public void setTaskAttachmentPath(String taskAttachmentPath) {
        this.taskAttachmentPath = taskAttachmentPath;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(String leaveTo) {
        this.leaveTo = leaveTo;
    }

    public String getLeaveSupTo() {
        return leaveSupTo;
    }

    public void setLeaveSupTo(String leaveSupTo) {
        this.leaveSupTo = leaveSupTo;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getTechReview() {
        return techReview;
    }

    public void setTechReview(String techReview) {
        this.techReview = techReview;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCsrName() {
        return csrName;
    }

    public void setCsrName(String csrName) {
        this.csrName = csrName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }
    private int groupingId;

    public int getGroupingId() {
        return groupingId;
    }

    public void setGroupingId(int groupingId) {
        this.groupingId = groupingId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getHomeRedirectActionId() {
        return homeRedirectActionId;
    }

    public void setHomeRedirectActionId(int homeRedirectActionId) {
        this.homeRedirectActionId = homeRedirectActionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getHomeRedirectDescription() {
        return homeRedirectDescription;
    }

    public void setHomeRedirectDescription(String homeRedirectDescription) {
        this.homeRedirectDescription = homeRedirectDescription;
    }

    public String getHomeRedirectStatus() {
        return homeRedirectStatus;
    }

    public void setHomeRedirectStatus(String homeRedirectStatus) {
        this.homeRedirectStatus = homeRedirectStatus;
    }

    public String getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getCategoryNamesList() {
        return categoryNamesList;
    }

    public void setCategoryNamesList(String categoryNamesList) {
        this.categoryNamesList = categoryNamesList;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getSelectedAns() {
        return selectedAns;
    }

    public void setSelectedAns(int selectedAns) {
        this.selectedAns = selectedAns;
    }

    public int getOnClickStatus() {
        return onClickStatus;
    }

    public void setOnClickStatus(int onClickStatus) {
        this.onClickStatus = onClickStatus;
    }

    public int getRemainingQuestions() {
        return remainingQuestions;
    }

    public void setRemainingQuestions(int remainingQuestions) {
        this.remainingQuestions = remainingQuestions;
    }

    public int getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(int subTopicId) {
        this.subTopicId = subTopicId;
    }

    public int getSpecficQuestionNo() {
        return specficQuestionNo;
    }

    public void setSpecficQuestionNo(int specficQuestionNo) {
        this.specficQuestionNo = specficQuestionNo;
    }

    public int getAnswer1() {
        return answer1;
    }

    public void setAnswer1(int answer1) {
        this.answer1 = answer1;
    }

    public int getAnswer2() {
        return answer2;
    }

    public void setAnswer2(int answer2) {
        this.answer2 = answer2;
    }

    public int getAnswer3() {
        return answer3;
    }

    public void setAnswer3(int answer3) {
        this.answer3 = answer3;
    }

    public int getAnswer4() {
        return answer4;
    }

    public void setAnswer4(int answer4) {
        this.answer4 = answer4;
    }

    public int getAnswer5() {
        return answer5;
    }

    public void setAnswer5(int answer5) {
        this.answer5 = answer5;
    }

    public int getAnswer6() {
        return answer6;
    }

    public void setAnswer6(int answer6) {
        this.answer6 = answer6;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType() {
        return fileFileContentType;
    }

    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getAccLogoHidden() {
        return accLogoHidden;
    }

    public void setAccLogoHidden(String accLogoHidden) {
        this.accLogoHidden = accLogoHidden;
    }

    public int getSowId() {
        return sowId;
    }

    public void setSowId(int sowId) {
        this.sowId = sowId;
    }

    public String getAcclogo() {
        return acclogo;
    }

    public void setAcclogo(String acclogo) {
        this.acclogo = acclogo;
    }

    public String getPoStartDate() {
        return poStartDate;
    }

    public void setPoStartDate(String poStartDate) {
        this.poStartDate = poStartDate;
    }

    public String getPoEndDate() {
        return poEndDate;
    }

    public void setPoEndDate(String poEndDate) {
        this.poEndDate = poEndDate;
    }

    public String getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }

    public String getOverTimeRate() {
        return overTimeRate;
    }

    public void setOverTimeRate(String overTimeRate) {
        this.overTimeRate = overTimeRate;
    }

    public String getOverTimeLimit() {
        return overTimeLimit;
    }

    public void setOverTimeLimit(String overTimeLimit) {
        this.overTimeLimit = overTimeLimit;
    }
    
    
}
