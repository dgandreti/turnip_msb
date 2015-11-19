/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.renderImage;

/**
 *
 * @author Nagireddy
 */

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
    
    public class ImageAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
    private HttpServletResponse httpServletResponse;
    private String imagePath;
     private HttpServletRequest httpServletRequest;
     private String resultType;
     private String path;
 public String execute() throws Exception {
    return SUCCESS;
 }
     
      public void renderImage() {
        byte[] image = null;
    
        try {
       
      // System.out.println("in render image process");
               File imageFile = new File(getPath());
                  InputStream is = new FileInputStream(imageFile);

//                Get the size of the file
                  long length = imageFile.length();

//                Create the byte array to hold the data
                  
                  image = new byte[(int)length];

//                Read in the bytes
                  int offset = 0;
                  int numRead = 0;
                  while (offset < image.length && (numRead=is.read(image, offset, image.length-offset)) >= 0) {
                    offset += numRead;
                  }

//                Ensure all the bytes have been read in
                  if (offset < image.length) {
                    throw new IOException("Could not completely read file ");
                  }

                  httpServletResponse.getOutputStream().write(image);
                 
//                Close the input stream and return bytes                                
                  is.close();
                  httpServletResponse.getOutputStream().close();
            //}
               

        }catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex);
            resultType=ERROR;
        }finally {
           
                
                if(image != null){
                    image = null;
                }
            }

      //  return SUCCESS;
    }
     
     
     
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    
    
}

