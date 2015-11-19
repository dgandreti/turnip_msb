/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag.sow;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class SOWAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private int userSeessionId;
    private int sessionOrgId;
    private List sowVTO;
    private String typeOfUser;
    private String consultantName;
    private String vendorName;
    private String requirementName;
    private String customerName;
    private String status;
    private String consultantId;
    private String requirementId;
    private String customerId;
    private String vendorId;
    private String rateSalary;
    private String vendorComments;
    private String customerComments;
    private String payTerms = "0";
    private String payRate;
    private String procResults;
    private File file;
    private String fileContentType;
    private String fileFileName;
    private String filesPath;
    private List sowAttachmentVTO;
    private String tabFlag;
    private String resultMessage;
    private String fileExists;
    private String uploadRes;
    private String migrateStatus;
    private String reqType;
    private String sowFlagValue;
    private String SOWSelectValue;
    // For SOW edit
    private int serviceId;
    private String netTerms;
    private String targetRate;
    private String targetRateType;
    private String securityCheck;
    private String estHrs;
    private String otFlag;
    private String estOtHrs;
    private String minWorkhrs;
    private String maxWorkhrs;
    private String shiftType;
    private String travelRequired;
    private String travelAmtPercentage;
    private String customerDivision;
    private String locationOne;
    private String locationTwo;
    private String serviceType;
    private int SOWFlag;
    private double serviceVersion;
    private String SOWStatus;
    private String transId;
    private String transNo;
    private String transAmt;
    private String sowRecreateFlag;
    private int his_id;
    private String submittedPayRate;
    private String submittedPayRateType;
    private String deductionAmt;
    private String deductionAmtRateType;
    private DataSourceDataProvider DataSourceDataProvider;
    private String sowHisStatus;
    private String serviceTypeRedirect;

    public String getSowList() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in getSowList Action...............................");
                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
//               
                setSowVTO(ServiceLocator.getSOWService().getSowDetails(this));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            System.out.println("...............>error:" + ex.getMessage());
            resultType = ERROR;
        }
        return resultType;
    }
    

    public String getSOWSearchResults() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in getSowList Action...............................");
                userSeessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                setSowVTO(ServiceLocator.getSOWService().getSOWSearchResults(this));
                System.out.println(">>>>LIST SIZE::::" + getSowVTO().size());
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String getSOWEditDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in getSowList Action...............................");
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                ServiceLocator.getSOWService().getSOWEditDetails(this);
                setSowVTO(ServiceLocator.getSOWService().getSOWAttachments(this));
                // setSowVTO(ServiceLocator.getSOWService().getRecreatedList(this));
                setSowHisStatus(DataSourceDataProvider.getInstance().getSOWStatus(this));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String doAddUpdateSOWDetails() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in doAddUpdateSOWDetails Action...............................");
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                System.out.println(">>>>>>>>>getRateSalary() " + getRateSalary());
                procResults = ServiceLocator.getSOWService().doAddUpdateSOWDetails(this);
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
     * Date : June 1 2015
     *
     * Author : ramakrishna<lankireddy@miraclesoft.com>
     *
     * addSOWAttachments() method can be used to add the SOW
     *
     * *****************************************************************************
     */
    public String addSOWAttachments() throws Exception {
        System.out.println("entered into updateAttachment  action");
        resultType = LOGIN;
        int addresult = 0;
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% File Data Start %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("File Name is:" + getFileFileName());
        System.out.println("File ContentType is:" + getFileContentType());
        System.out.println("Files Directory is:" + filesPath + "-------->" + getFilesPath());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% File Data End %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("User Id----------------------------->" + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID));

        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString() != null) {
                if (getFileFileName() == null) {
                    System.out.println("file is null so it adds only data in  table");
                } else {
                    filesPath = Properties.getProperty("Sow.Attachment");
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
                    short week = (short) (Math.round(dt.getDate() / 7) + 1);

                    createPath = new File(createPath.getAbsolutePath() + File.separator + getConsultantId() + File.separator + String.valueOf(dt.getYear() + 1900) + File.separator + month + File.separator + String.valueOf(week));
                    createPath.mkdir();

                    File theFile = new File(createPath.getAbsolutePath());

                    System.out.println("File Path::" + theFile);

                    setFilesPath(theFile.toString());
                    /*copies the file to the destination*/
                    File destFile = new File(theFile, getFileFileName());
                    System.out.println(">>DestFile>>" + destFile);
                    //setFilePath(destFile.toString());
                    System.out.println("THE FINAL PATH IT IS SET IN THE ACTION::>>>>>" + getFilesPath());
                    FileUtils.copyFile(file, destFile);
                }
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                addresult = ServiceLocator.getSOWService().doAddSOWAttachment(this);
                setTabFlag("AT");
                if (addresult == 1) {
                    setUploadRes("S");
                } else {
                    setUploadRes("F");
                }

                System.out.println(">>>>>>>>>>>>" + getTabFlag() + ">>" + getUploadRes());
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

    public String getSOWAttachments() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in doAddUpdateSOWDetails Action...............................");
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                setSowVTO(ServiceLocator.getSOWService().getSOWAttachments(this));

                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String doInsertSAGRecord() {
        resultType = LOGIN;
        String result = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in doInsertSAGRecord Action...............................");
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                String res = ServiceLocator.getSOWService().doInsertSAGRecord(this);
                System.out.println(">>>>>>>>RESLT IN ACTION>>" + res);

                System.out.println(">>>>>>RESULT TO AJAX>>>" + res);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(res);
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return null;
    }
    public String SOWSaveOrSubmit() {
        resultType = LOGIN;
        int update = 0;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in SOWSaveOrSubmit Action...............................");
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                System.out.println(">>>>>>>>>getRateSalary() " + getRateSalary());
                update = ServiceLocator.getSOWService().SOWSaveOrSubmit(this);
                if (update > 0) {
                    resultMessage = "Updated Successfully!!";
                } else {
                    resultMessage = "Not Updated !";
                }
                if (getSOWFlag() == 1) {
                    resultType = SUCCESS;
                } else if(getSOWFlag() == 2){
                    resultType = "successRedirect";
                }
            }
            }  catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }
    public String sowRecreateEdit() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("im in getSowList Action...............................");
                setUserSeessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
                ServiceLocator.getSOWService().sowRecreateEdit(this);
                // setSowVTO(ServiceLocator.getSOWService().getRecreatedList(this));
                //setSowHisStatus(DataSourceDataProvider.getInstance().getSOWStatus(this));
                resultType = SUCCESS;
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public int getUserSeessionId() {
        return userSeessionId;
    }

    public void setUserSeessionId(int userSeessionId) {
        this.userSeessionId = userSeessionId;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public List getSowVTO() {
        return sowVTO;
    }

    public void setSowVTO(List sowVTO) {
        this.sowVTO = sowVTO;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getRateSalary() {
        return rateSalary;
    }

    public void setRateSalary(String rateSalary) {
        this.rateSalary = rateSalary;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public void setVendorComments(String vendorComments) {
        this.vendorComments = vendorComments;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    public String getPayTerms() {
        return payTerms;
    }

    public void setPayTerms(String payTerms) {
        this.payTerms = payTerms;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getProcResults() {
        return procResults;
    }

    public void setProcResults(String procResults) {
        this.procResults = procResults;
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

    public List getSowAttachmentVTO() {
        return sowAttachmentVTO;
    }

    public void setSowAttachmentVTO(List sowAttachmentVTO) {
        this.sowAttachmentVTO = sowAttachmentVTO;
    }

    public String getTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(String tabFlag) {
        this.tabFlag = tabFlag;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getFileExists() {
        return fileExists;
    }

    public void setFileExists(String fileExists) {
        this.fileExists = fileExists;
    }

    public String getUploadRes() {
        return uploadRes;
    }

    public void setUploadRes(String uploadRes) {
        this.uploadRes = uploadRes;
    }

    public String getMigrateStatus() {
        return migrateStatus;
    }

    public void setMigrateStatus(String migrateStatus) {
        this.migrateStatus = migrateStatus;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getSowFlagValue() {
        return sowFlagValue;
    }

    public void setSowFlagValue(String sowFlagValue) {
        this.sowFlagValue = sowFlagValue;
    }

    public String getSOWSelectValue() {
        return SOWSelectValue;
    }

    public void setSOWSelectValue(String SOWSelectValue) {
        this.SOWSelectValue = SOWSelectValue;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getNetTerms() {
        return netTerms;
    }

    public void setNetTerms(String netTerms) {
        this.netTerms = netTerms;
    }

    public String getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(String targetRate) {
        this.targetRate = targetRate;
    }

    public String getTargetRateType() {
        return targetRateType;
    }

    public void setTargetRateType(String targetRateType) {
        this.targetRateType = targetRateType;
    }

    public String getSecurityCheck() {
        return securityCheck;
    }

    public void setSecurityCheck(String securityCheck) {
        this.securityCheck = securityCheck;
    }

    public String getEstHrs() {
        return estHrs;
    }

    public void setEstHrs(String estHrs) {
        this.estHrs = estHrs;
    }

    public String getOtFlag() {
        return otFlag;
    }

    public void setOtFlag(String otFlag) {
        this.otFlag = otFlag;
    }

    public String getEstOtHrs() {
        return estOtHrs;
    }

    public void setEstOtHrs(String estOtHrs) {
        this.estOtHrs = estOtHrs;
    }

    public String getMinWorkhrs() {
        return minWorkhrs;
    }

    public void setMinWorkhrs(String minWorkhrs) {
        this.minWorkhrs = minWorkhrs;
    }

    public String getMaxWorkhrs() {
        return maxWorkhrs;
    }

    public void setMaxWorkhrs(String maxWorkhrs) {
        this.maxWorkhrs = maxWorkhrs;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public String getTravelRequired() {
        return travelRequired;
    }

    public void setTravelRequired(String travelRequired) {
        this.travelRequired = travelRequired;
    }

    public String getTravelAmtPercentage() {
        return travelAmtPercentage;
    }

    public void setTravelAmtPercentage(String travelAmtPercentage) {
        this.travelAmtPercentage = travelAmtPercentage;
    }

    public String getCustomerDivision() {
        return customerDivision;
    }

    public void setCustomerDivision(String customerDivision) {
        this.customerDivision = customerDivision;
    }

    public String getLocationOne() {
        return locationOne;
    }

    public void setLocationOne(String locationOne) {
        this.locationOne = locationOne;
    }

    public String getLocationTwo() {
        return locationTwo;
    }

    public void setLocationTwo(String locationTwo) {
        this.locationTwo = locationTwo;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        if ("F".equalsIgnoreCase(serviceType)) {
            this.serviceType = "PE";
        } else if ("S".equalsIgnoreCase(serviceType)) {
            this.serviceType = "CO";
        } else {
            this.serviceType = serviceType;
        }
    }

    public int getSOWFlag() {
        return SOWFlag;
    }

    public void setSOWFlag(int SOWFlag) {
        this.SOWFlag = SOWFlag;
    }

    public double getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(double serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getSOWStatus() {
        return SOWStatus;
    }

    public void setSOWStatus(String SOWStatus) {
        this.SOWStatus = SOWStatus;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getSowRecreateFlag() {
        return sowRecreateFlag;
    }

    public void setSowRecreateFlag(String sowRecreateFlag) {
        this.sowRecreateFlag = sowRecreateFlag;
    }

    public int getHis_id() {
        return his_id;
    }

    public void setHis_id(int his_id) {
        this.his_id = his_id;
    }

    public String getSubmittedPayRate() {
        return submittedPayRate;
    }

    public void setSubmittedPayRate(String submittedPayRate) {
        this.submittedPayRate = submittedPayRate;
    }

    public String getSubmittedPayRateType() {
        return submittedPayRateType;
    }

    public void setSubmittedPayRateType(String submittedPayRateType) {
        this.submittedPayRateType = submittedPayRateType;
    }

    public String getDeductionAmt() {
        return deductionAmt;
    }

    public void setDeductionAmt(String deductionAmt) {
        this.deductionAmt = deductionAmt;
    }

    public String getDeductionAmtRateType() {
        return deductionAmtRateType;
    }

    public void setDeductionAmtRateType(String deductionAmtRateType) {
        this.deductionAmtRateType = deductionAmtRateType;
    }

    public DataSourceDataProvider getDataSourceDataProvider() {
        return DataSourceDataProvider;
    }

    public void setDataSourceDataProvider(DataSourceDataProvider DataSourceDataProvider) {
        this.DataSourceDataProvider = DataSourceDataProvider;
    }

    public String getSowHisStatus() {
        return sowHisStatus;
    }

    public void setSowHisStatus(String sowHisStatus) {
        this.sowHisStatus = sowHisStatus;
    }

    public String getServiceTypeRedirect() {
        return serviceTypeRedirect;
    }

    public void setServiceTypeRedirect(String serviceTypeRedirect) {
        this.serviceTypeRedirect = serviceTypeRedirect;
    }
    
}
