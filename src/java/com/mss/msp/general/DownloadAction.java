/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.general;

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
public class DownloadAction implements Action, ServletRequestAware, ServletResponseAware {

    private String inputPath;
    // private String URL="/images/flower.GIF";
    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    //private int id;
    private String fileName;
    private String attachmentLocation;
    private int attachmentId;
    
    private String resultType;
    private String downloadFlag;
    private int taskid;
    private String myTask;
    /**
     * Creates a new instance of DownloadAction
     */
    public DownloadAction() {
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
    public String downloadAttachment() throws Exception{
        
        resultType=null;
         System.out.println("********************DownloadAction :: downloadAttachment Action Start*********************");
        try {
         
            this.setAttachmentId(Integer.parseInt(httpServletRequest.getParameter("attachmentId").toString()));
            
            try{
                this.setAttachmentLocation(dataSourceDataProvider.getInstance().getAttachmentLocation(this.getAttachmentId()));
            }catch(ServiceLocatorException se)
            {
                throw new ServiceLocatorException(se);
            }
           
            fileName = this.getAttachmentLocation()
                    .substring(this.getAttachmentLocation().lastIndexOf( File.separator) + 1, getAttachmentLocation().length());
            httpServletResponse.setContentType("application/force-download");
         
           
             if ( !"null".equals(getAttachmentLocation()) && getAttachmentLocation() != null && getAttachmentLocation().length() != 0) {

            File file = new File(getAttachmentLocation());
            inputStream = new FileInputStream(file);
            outputStream = httpServletResponse.getOutputStream();
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
            setDownloadFlag("AttachmentExists");
             inputStream.close();
                outputStream.close();
             }
             else
             {
               setDownloadFlag("noAttachment");
               resultType = INPUT;
             }
        } catch (FileNotFoundException ex) {
        
           setDownloadFlag("noFile");
           resultType = INPUT;
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
         System.out.println("********************DownloadAction :: downloadAttachment Action End*********************");
      return resultType;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getMyTask() {
        return myTask;
    }

    public void setMyTask(String myTask) {
        this.myTask = myTask;
    }
    
}
