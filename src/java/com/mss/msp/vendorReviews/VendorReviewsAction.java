/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.vendorReviews;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class VendorReviewsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    public VendorReviewsAction() {
    }
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String resultType;
    private String accountName;
    private String reviewComments;
    private int sessionOrgId;
    private int sessionUserId;
    private int vendorOrgId;
    private List vendorReviewsList;
    private String vendorImg;
    private String reviewName;
    private int reviewRating;

    public String vendorRatingAndReveiw() {
        resultType = LOGIN;
        System.out.println("********************VendorReviewsAction :: vendorRatingAndReveiw Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setVendorReviewsList(ServiceLocator.getVendorReviewsService().vendorRatingAndReveiw(this));
                resultType = SUCCESS;
                System.out.println("********************VendorReviewsAction :: vendorRatingAndReveiw Method End*********************");
            } catch (Exception e) {
                e.printStackTrace();
                resultType = ERROR;
            }
        }
        return resultType;
    }

    public String getImgPath() {
        resultType = LOGIN;
        String resultString = "";
        System.out.println("********************VendorReviewsAction :: getImgPath Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                resultString = DataSourceDataProvider.getInstance().getImgPath(this);
                System.out.println("-------------------------------------------------"+resultString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
                System.out.println("********************VendorReviewsAction :: getImgPath Method End*********************");
            } catch (Exception e) {
                e.printStackTrace();
                resultType = ERROR;
            }
        }
        return null;
    }

    public String addVendorRatingAndReveiw() {
        resultType = LOGIN;
        int result = 0;
        String resultString = "";
        System.out.println("********************VendorReviewsAction :: addVendorRatingAndReveiw Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

            try {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                result = ServiceLocator.getVendorReviewsService().addVendorRatingAndReveiw(this);
                if (result > 0) {
                    resultString = "success";
                }
                System.out.println("------------------------------" + result);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
                System.out.println("********************VendorReviewsAction :: addVendorRatingAndReveiw Method End*********************");
            } catch (Exception e) {
                e.printStackTrace();
                resultType = ERROR;
            }
        }
        return null;
    }
//vendorRatingAndReveiw

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public int getSessionOrgId() {
        return sessionOrgId;
    }

    public void setSessionOrgId(int sessionOrgId) {
        this.sessionOrgId = sessionOrgId;
    }

    public int getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(int sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public int getVendorOrgId() {
        return vendorOrgId;
    }

    public void setVendorOrgId(int vendorOrgId) {
        this.vendorOrgId = vendorOrgId;
    }

    public List getVendorReviewsList() {
        return vendorReviewsList;
    }

    public void setVendorReviewsList(List vendorReviewsList) {
        this.vendorReviewsList = vendorReviewsList;
    }

    public String getVendorImg() {
        return vendorImg;
    }

    public void setVendorImg(String vendorImg) {
        this.vendorImg = vendorImg;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }
}
