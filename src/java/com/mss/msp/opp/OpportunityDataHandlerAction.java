/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.opp;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Anton Franklin
 */
public class OpportunityDataHandlerAction extends ActionSupport implements ServletRequestAware {

    private Integer opportunityID;
    private Integer accountID;
    private String opportunityType;
    private String opportunityName;
    private String opportunityDesc;
    private String opportunityCreatedDate;
    private int opportunityCreatedBy;
    private String opportunityComments;
    private int opportunityModifiedBy;
    private String opportunityModifiedDate;
    private String resultType;
    private String opportunityActionResponse;
    OpportunityVTO opportunity = new OpportunityVTO();
    List<OpportunityVTO> opportunities = new ArrayList<OpportunityVTO>();
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    /**
     * The httpServletRequest is used for storing httpServletRequest Object.
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private DataSourceDataProvider dataSourceDataProvider;
    OpportunityDataHandlerService opportunityDataHandlerService = new OpportunityDataHandlerServiceImpl();

    public OpportunityDataHandlerAction() {
    }

    public String getOpportunitySearchDetails() {
        resultType = LOGIN;

        try {

            if (httpServletRequest.getSession(false).getAttributeNames() != null) {

                opportunities = ServiceLocator.getOpportunityDataHandlerService().getOpportunitySearchDetails(this, httpServletRequest);
                //.getProjectDataHandlerService().getProjectSearchDetails(this, httpServletRequest);

                if (opportunities.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("opportunityData", opportunities);

                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("opportunityData", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            System.out.println(ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String getOpportunitiesByAccID() {
        resultType = LOGIN;

        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                opportunities = ServiceLocator.getOpportunityDataHandlerService().getOpportunitiesByAccID(accountID);
                //getProjectDataHandlerService().getProjectsByAccID(accountID);
                System.out.println(opportunities.size());
                if (opportunities.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("opportunityData", opportunities);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("opportunityData", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }

        return resultType;
    }

    public String getOpportunityByOpportunityID() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                opportunity = ServiceLocator.getOpportunityDataHandlerService().getOpportunityByOpportunityID(this, httpServletRequest);
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String addOpportunity() {

        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        resultType = LOGIN;

        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                opportunity.setAccountID(accountID);
                opportunity.setOpportunityType(opportunityType);
                opportunity.setOpportunityName(opportunityName);
                opportunity.setOpportunityDesc(opportunityDesc);
                opportunity.setOpportunityComments(opportunityComments);
                opportunity.setOpportunityCreatedBy(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                opportunity.setOpportunityModifiedBy(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));


                opportunityModifiedDate = new Timestamp(currentDate.getTime()).toString();
                opportunityCreatedDate = new Timestamp(currentDate.getTime()).toString();

                opportunity.setopportunityModifiedDate(opportunityModifiedDate);
                opportunity.setOpportunityCreatedDate(opportunityCreatedDate);

                opportunityDataHandlerService.addOpportunity(opportunity);

                opportunityActionResponse = "\"" + opportunityName + "\" Opportunity has been added.";

                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String getAllOpportunities() {
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                List theseOpprotunities = ServiceLocator.getOpportunityDataHandlerService().getOpportunitiesByAccID(accountID);
                System.out.println(theseOpprotunities.size());
                if (theseOpprotunities.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("opportunityData", theseOpprotunities);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("opportunityData", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String getOpportunityDetails() {
        resultType = LOGIN;

        if (opportunityID != null) {
            httpServletRequest.getSession(false).setAttribute("opportunityID", opportunityID);
        }
        if (opportunityID == null) {
            opportunityID = Integer.parseInt(httpServletRequest.getSession(false).getAttribute("opportunityID").toString());
        }

        System.out.println(opportunityID);

        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                opportunity = ServiceLocator.getOpportunityDataHandlerService().getOpportunityByOpportunityID(this, httpServletRequest); // (this, httpServletRequest);



                if (!opportunity.getOpportunityName().isEmpty()) {
                    httpServletRequest.getSession(false).setAttribute("opportunity", opportunity);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("opportunity", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String updateOpportunity() {
        resultType = LOGIN;
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        OpportunityVTO o = new OpportunityVTO();
        String user = (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID)).toString();
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                o = (OpportunityVTO) httpServletRequest.getSession(false).getAttribute("opportunity");
                o.setOpportunityName(opportunity.getOpportunityName());
                o.setOpportunityComments(opportunity.getOpportunityComments());
                o.setOpportunityDesc(opportunity.getOpportunityDesc());
                opportunityModifiedDate = new Timestamp(currentDate.getTime()).toString();
                o.setopportunityModifiedDate(opportunityModifiedDate);
                o.setOpportunityModifiedBy(Integer.parseInt(user));
                o.setOpportunityType("AO");

                //System.out.println(opportunity.getOpportunityID());

                //httpServletRequest.getSession(false).setAttribute("opportunityID", opportunity.getOpportunityID());
                opportunityDataHandlerService.updateOpportunity(o, httpServletRequest);
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }

    /*
     * Getters and Setters
     */
    public Integer getOpportunityID() {
        return opportunityID;
    }

    public void setOpportunityID(int opportunityID) {
        this.opportunityID = opportunityID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(String opportunityType) {
        this.opportunityType = opportunityType;
    }

    public String getOpportunityName() {
        return opportunityName;
    }

    public void setOpportunityName(String opportunityName) {
        this.opportunityName = opportunityName;
    }

    public String getOpportunityDesc() {
        return opportunityDesc;
    }

    public void setOpportunityDesc(String opportunityDesc) {
        this.opportunityDesc = opportunityDesc;
    }

    public String getOpportunityCreatedDate() {
        return opportunityCreatedDate;
    }

    public void setOpportunityCreatedDate(String opportunityCreatedDate) {
        this.opportunityCreatedDate = opportunityCreatedDate;
    }

    public int getOpportunityCreatedBy() {
        return opportunityCreatedBy;
    }

    public void setOpportunityCreatedBy(int opportunityCreatedBy) {
        this.opportunityCreatedBy = opportunityCreatedBy;
    }

    public String getOpportunityComments() {
        return opportunityComments;
    }

    public void setOpportunityComments(String opportunityComments) {
        this.opportunityComments = opportunityComments;
    }

    public int getOpportunityModifiedBy() {
        return opportunityModifiedBy;
    }

    public void setOpportunityModifiedBy(int opportunityModifiedBy) {
        this.opportunityModifiedBy = opportunityModifiedBy;
    }

    public String getOpportunityModfiedDate() {
        return opportunityModifiedDate;
    }

    public void setOpportunityModfiedDate(String opportunityModifiedDate) {
        this.opportunityModifiedDate = opportunityModifiedDate;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public DataSourceDataProvider getDataSourceDataProvider() {
        return dataSourceDataProvider;
    }

    public void setDataSourceDataProvider(DataSourceDataProvider dataSourceDataProvider) {
        this.dataSourceDataProvider = dataSourceDataProvider;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.httpServletRequest = hsr;
    }

    public OpportunityVTO getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(OpportunityVTO opportunity) {
        this.opportunity = opportunity;
    }

    public List<OpportunityVTO> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<OpportunityVTO> opportunities) {
        this.opportunities = opportunities;
    }

    public String getOpportunityActionResponse() {
        return opportunityActionResponse;
    }

    public void setOpportunityActionResponse(String opportunityActionResponse) {
        this.opportunityActionResponse = opportunityActionResponse;
    }

    public String getOpportunityDescTitle() {
        if (this.opportunityDesc.length() < 10) {
            return this.opportunityDesc;
        } else {
            return this.opportunityDesc.substring(0, 10);
        }

    }
}
