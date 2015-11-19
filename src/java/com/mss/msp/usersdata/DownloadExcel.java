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
    private DataSourceDataProvider dataSourceDataProvider;
    private String resultType;
    private String downloadFlag;
    private int excel_id;
    private String samplePath;
    private String loggerFlag;
    private String loadingFileType;

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

    public String downloadExcelAttachment() throws Exception {
        resultType = null;
        try {
            //this.setAttachmentLocation(httpServletRequest.getParameter("attachmentLocation"));


            this.setAttachmentId(Integer.parseInt(httpServletRequest.getParameter("excel_id").toString()));
            System.out.println("=================>Entered into the DownloadAction");
            try {
                this.setAttachmentLocation(dataSourceDataProvider.getInstance().getExcelocation(this.getAttachmentId()));
//               String[] token= response.split("|");
//               path=token[0];
//                System.out.println("------------------"+path);
//                System.out.println("----------============"+token[1]);
//                this.setAttachmentLocation(token[1]);
            } catch (ServiceLocatorException se) {
                System.out.println(se.getMessage());
            }
            System.out.println("=================>" + attachmentLocation);
            System.out.println("getAttachmentLocation()-->" + getAttachmentLocation());
//             int count;
//                int cell_count;
//                int[] colNumbers = null;
//                File fileobj = new File(getAttachmentLocation());
//		Workbook workbook = Workbook.getWorkbook(fileobj);		
//		Sheet sheet = workbook.getSheet(0);
//        int count1 = sheet.getColumns();
//		int rowsCount = sheet.getRows();
//				Cell rows = null;
//                String dataArray[]=new String[colNumbers.length];
//				for (int j=0;j<1;j++) 
//				{	
//					for(int i=0;i<count1;i++){
//                    rows = sheet.getCell(colNumbers[i], j);
//                    dataArray[i]=rows.getContents();
//					//System.out.println("--->"+dataArray[i]);
//					}
//					
//                }
//
//		
//		//System.out.println("get abs path-->"+fileobj.getPath());
//		
//		//System.out.println("get abs path-->"+fileobj.getParent());
//		//System.out.println("this is file name-->"+fileobj.getName());
//		
//		WritableWorkbook wworkbook;
//                wworkbook = Workbook.createWorkbook(new File(getAttachmentLocation()));
//                WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
//                WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
//                cellFont.setColour(Colour.PINK);
//
//                WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
//                cellFormat.setBackground(Colour.BLUE);
//                Label label1 = new Label(0, 0, "Company Name", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(1, 0, "Url", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(2, 0, "Mail Extentions", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(3, 0, "Address1", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(4, 0, "Address2", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(5, 0, "City", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(6, 0, "Zip Code", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(7, 0, "Country", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(8, 0, "State", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(9, 0, "Phone Number", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(10, 0, "Fax", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(11, 0, "Industry", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(12, 0, "Region", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(13, 0, "territory", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(14, 0, "No.of Employees", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(15, 0, "TaxId", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(16, 0, "stockSymbol", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(17, 0, "Revenue", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(18, 0, "Description", cellFormat);
//                wsheet.addCell(label1);
//                label1 = new Label(19, 0, "Result", cellFormat);
//                wsheet.addCell(label1);
//                Label label;
//                System.out.println("--------------- in downoad xls-----------" + getSp_exists());
//                List<String> record = new ArrayList<String>();
//                StringTokenizer str = new StringTokenizer(getSp_exists(), "^");
//                while (str.hasMoreTokens()) {
//                    record.add(str.nextToken());
//                }
//                count = record.size();
//                CellView cv = new CellView();
//                cv.setAutosize(true);
//                int l = 1;
//                for (int k = 0; k < count; k++) {
//                    // str = new StringTokenizer(record.get(k), "|", true);
//                    int i = 0;
//                    String[] tokens1 = record.get(k).split("\\|", -1);
//                    // for (int i = 0; i < tokens1.length - 1; i++) {
//                    for (String string : tokens1) {
//                        if (string.equalsIgnoreCase("")) {
//                            string = null;
//                        }
//                        System.out.println(string);
//                        label = new Label(i, l, string);
//                       wsheet.setColumnView(i, cv);
//                        wsheet.addCell(label);
//                        i++;
//                    }
//                
//                l++;
//            }
//
//            System.out.println("--------------" + record);
//            wworkbook.write();
//            wworkbook.close();
            fileName = this.getAttachmentLocation()
                    .substring(this.getAttachmentLocation().lastIndexOf(File.separator) + 1, getAttachmentLocation().length());
            httpServletResponse.setContentType("application/force-download");
            System.out.println("=================>" + fileName);

            if (!"null".equals(getAttachmentLocation()) && getAttachmentLocation() != null && getAttachmentLocation().length() != 0) {

                File file = new File(getAttachmentLocation());
                inputStream = new FileInputStream(file);
                outputStream = httpServletResponse.getOutputStream();
                System.out.println("-------------after outputstream---------------");
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
            System.out.println("File not Found");
            setDownloadFlag("noFile");
            resultType = INPUT;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultType;
    }

    public String downloadSample() throws Exception {
        try {
            if ("Contacts".equalsIgnoreCase(getLoadingFileType())) {
                samplePath = Properties.getProperty("Sample.uploadContacts");
            } else if("skills".equalsIgnoreCase(getLoadingFileType())){
                samplePath = Properties.getProperty("Sample.uploadQues");
            }else {
                samplePath = Properties.getProperty("Sample.uploadAcc");
            }

            System.out.println("samplePath" + samplePath);
            File file = new File(samplePath);
            inputStream = new FileInputStream(file);
            outputStream = httpServletResponse.getOutputStream();
            System.out.println("-------------after outputstream---------------");
            // httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + "SampleFile.xls" + "\"");
            IOUtils.copyLarge(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
}
