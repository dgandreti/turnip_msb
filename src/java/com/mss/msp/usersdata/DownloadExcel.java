/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usersdata;

import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.Properties;
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


import org.apache.commons.io.IOUtils;

/**
 *
 * @author miracle
 */
public class DownloadExcel implements Action, ServletRequestAware, ServletResponseAware {
    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */

    private String inputPath;
    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String attachmentLocation;
    private int attachmentId;
    private DataSourceDataProvider dataSourceDataProvider;
    private String resultType;
    private String downloadFlag;
    private int excel_id;
    private String samplePath;
    private String loggerFlag;
    private String loadingFileType;
    private String logDownloadFlag;

    public DownloadExcel() {
    }

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
     * ForUse : downloadExcelAttachment() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String downloadExcelAttachment() throws Exception {
        resultType = null;
        System.out.println("********************DownloadExcel :: downloadExcelAttachment Method Start*********************");
        try {

            this.setAttachmentId(Integer.parseInt(httpServletRequest.getParameter("excel_id").toString()));
            try {
                this.setAttachmentLocation(dataSourceDataProvider.getInstance().getExcelocation(this.getAttachmentId(),this.getLogDownloadFlag()));

            } catch (ServiceLocatorException se) {
            }
            
            fileName = this.getAttachmentLocation()
                    .substring(this.getAttachmentLocation().lastIndexOf(File.separator) + 1, getAttachmentLocation().length());
            httpServletResponse.setContentType("application/force-download");

            if (!"null".equals(getAttachmentLocation()) && getAttachmentLocation() != null && getAttachmentLocation().length() != 0) {

                File file = new File(getAttachmentLocation());
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                IOUtils.copyLarge(inputStream, outputStream);
                setDownloadFlag("AttachmentExists");
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } else {
                setDownloadFlag("noAttachment");
                resultType = INPUT;
            }
        } catch (FileNotFoundException ex) {
            setDownloadFlag("noFile");
            resultType = INPUT;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("********************DownloadExcel :: downloadExcelAttachment Method End*********************");
        return resultType;
    }

     /**
     * *****************************************************************************
     * Date : 
     *
     * Author : 
     *
     * ForUse : downloadSample() method is used to 
     * 
     *
     * *****************************************************************************
     */
    public String downloadSample() throws Exception {
        System.out.println("********************DownloadExcel :: downloadSample Method Start*********************");
        try {
            if ("Contacts".equalsIgnoreCase(getLoadingFileType())) {
                samplePath = Properties.getProperty("Sample.uploadContacts");
            } else if ("skills".equalsIgnoreCase(getLoadingFileType())) {
                samplePath = Properties.getProperty("Sample.uploadQues");
            } else {
                samplePath = Properties.getProperty("Sample.uploadAcc");
            }

            File file = new File(samplePath);
            inputStream = new FileInputStream(file);
            outputStream = httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + "SampleFile.xls" + "\"");
            IOUtils.copyLarge(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("********************DownloadExcel :: downloadSample Method End*********************");
        return null;
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

    public int getExcel_id() {
        return excel_id;
    }

    public void setExcel_id(int excel_id) {
        this.excel_id = excel_id;
    }

    public String getSamplePath() {
        return samplePath;
    }

    public void setSamplePath(String samplePath) {
        this.samplePath = samplePath;
    }

    public String getLoggerFlag() {
        return loggerFlag;
    }

    public void setLoggerFlag(String loggerFlag) {
        this.loggerFlag = loggerFlag;
    }

    public String getLoadingFileType() {
        return loadingFileType;
    }

    public void setLoadingFileType(String loadingFileType) {
        this.loadingFileType = loadingFileType;
    }

    public String getLogDownloadFlag() {
        return logDownloadFlag;
    }

    public void setLogDownloadFlag(String logDownloadFlag) {
        this.logDownloadFlag = logDownloadFlag;
    }
    
}
