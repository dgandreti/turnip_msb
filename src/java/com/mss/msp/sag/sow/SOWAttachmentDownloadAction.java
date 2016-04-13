/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag.sow;

/**
 * *****************************************************************************
 *
 * Project : servicesBay
 *
 * Package :
 *
 * Date : April 23, 2015, 3:01 PM
 *
 * Author : Ramakrishna<lankireddy@miraclesoft.com>
 *
 * File : DownloadAction.java
 *
 * Copyright 2007 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
import com.mss.msp.general.*;
import com.mss.msp.usrajax.*;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;

import com.mss.msp.util.Properties;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class SOWAttachmentDownloadAction implements Action, ServletRequestAware, ServletResponseAware {

    private String inputPath;
    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String consult_AttachmentLocation;
    private int acc_attachment_id;
    private String fileExists;
    private String resultMessage;
    private String resultType;
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
    private String tabFlag;
    private String serviceId;
    private String serviceType;

    /**
     * Creates a new instance of DownloadAction
     */
    public SOWAttachmentDownloadAction() {
    }
    private DataSourceDataProvider dataSourceDataProvider;

    public String execute() throws Exception {
        return null;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @SuppressWarnings("static-access")
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
/**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : downloadSOWAttachment() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String downloadSOWAttachment() throws Exception {
         System.out.println("********************SOWAttachmentDownloadAction :: downloadSOWAttachment Method Start*********************");
        try {
            resultType = null;
            this.setAcc_attachment_id(Integer.parseInt(httpServletRequest.getParameter("acc_attachment_id").toString()));
           
            try {
                this.setConsult_AttachmentLocation(dataSourceDataProvider.getInstance().getConsult_AttachmentLocation(this.getAcc_attachment_id()));
            } catch (ServiceLocatorException se) {
              
            }
            fileName = this.getConsult_AttachmentLocation()
                    .substring(this.getConsult_AttachmentLocation().lastIndexOf(File.separator) + 1, getConsult_AttachmentLocation().length());
            httpServletResponse.setContentType("application/force-download");
           
            if (!File.separator.equals(getConsult_AttachmentLocation()) && !"null".equals(getConsult_AttachmentLocation()) && getConsult_AttachmentLocation() != null && getConsult_AttachmentLocation().length() != 0) {


                File file = new File(getConsult_AttachmentLocation());
               
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                if (outputStream == null) {
                   
                } else {
                   
                    httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    int noOfBytesRead = 0;
                    byte[] byteArray = null;
                  
                    while (true) {
                        byteArray = new byte[1024];
                        noOfBytesRead = inputStream.read(byteArray);
                        if (noOfBytesRead == -1) {
                            break;
                        }
                        outputStream.write(byteArray, 0, noOfBytesRead);
                        
                    }
                    inputStream.close();
                    outputStream.close();
                }
                setResultMessage("record exists");
                setFileExists("Resume");
                setTabFlag("AT");

            } else {
               
                setFileExists("noFile");
                setTabFlag("AT");
                resultType = INPUT;
            }
        } catch (FileNotFoundException ex) {
           
            setFileExists("NotExisted");
            setTabFlag("AT");
            resultType = INPUT;

        } catch (IOException ex) {
           
        } catch (Exception ex) {
           
          
        }
         System.out.println("********************SOWAttachmentDownloadAction :: downloadSOWAttachment Method End*********************");
        return resultType;
    }

    public String getConsult_AttachmentLocation() {
        return consult_AttachmentLocation;
    }

    public void setConsult_AttachmentLocation(String consult_AttachmentLocation) {
        this.consult_AttachmentLocation = consult_AttachmentLocation;
    }

    public int getAcc_attachment_id() {
        return acc_attachment_id;
    }

    public void setAcc_attachment_id(int acc_attachment_id) {
        this.acc_attachment_id = acc_attachment_id;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
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

    public String getFileExists() {
        return fileExists;
    }

    public void setFileExists(String fileExists) {
        this.fileExists = fileExists;
    }

    public String getTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(String tabFlag) {
        this.tabFlag = tabFlag;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}