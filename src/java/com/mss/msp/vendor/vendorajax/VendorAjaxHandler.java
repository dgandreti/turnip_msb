/**
 * *************************************
 *
 * @Author:praveen kumar<pkatru@miraclesoft.com>
 * @Author:rama krishna<lankireddy@miraclesoft.com>
 * @Created Date:05/05/2015
 *
 *
 * *************************************
 */
package com.mss.msp.vendor.vendorajax;

import com.mss.msp.requirement.RequirementVTO;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.MailManager;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class VendorAjaxHandler extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultMessage;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int countryId;
    private String vendorName;
    private String vendorURL;
    private String vendorPhone;
    private String vendorState;
    private String vendorCountry;
    private String vendorStatus;
    private int sessionId;
    private String resultType;
    private String responseString;
    private DataSourceDataProvider dataSourceDataProvider;
    // variables for update vendor details start
    private String vendorAddress1;
    private String vendorAddress2;
    private String vendorCity;
    private String vendorFax;
    private String vendorZip;
    private int vendorIndustry;
    private String vendorRegion;
    private String vendorTerritory;
    private int vendorType;
    private String vendorDescription;
    private int vendorBudget;
    private String vendorTaxid;
    private String stockSymbol;
    private int vendorRvenue;
    private int empCount;
    private int vendorId;
    //variables for update vendor details end
    private int userSessionId;
    private int orgId;
    private int vendorUserId;
    private String vendorFirstName;
    private String vendorLastName;
    private String vendorEmail;
    private int tireType;
    private String req_id;
    private String ArrayList[];
    private String accessTime;
    private String vendorList;
    private String status;
    private String vendorFlag;
    private String teamMembers;
    private Map teamMembersList;
    //created by aklakh
    private int tireId;
    private int tireTypeId;
    private String statusEdit;
    private int requirementId;
    private int sessionOrgId;
    private RequirementVTO requirementVTO;
    //for mailing 
    private String accountName;
    private String mailIds;
    private int year;
    private int month;
    private String candidateName;
    private String jobTitle;
    private MailManager mailManager = new MailManager();

    public VendorAjaxHandler() {
    }

    /**
     * *************************************
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:05/05/2015
     * @for Use:getting states by country code
     *
     ************************************
     */
    public String getVendorStates() {

        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String states = ServiceLocator.getVendorAjaxHandlerService().getVendorStates( this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(states);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }// Session validator if END

        return null;
    }

    /**
     * *************************************
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:05/05/2015
     *
     ***********************************
     */
    public String getVendorSearchDetails() {
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(getSessionId()));
                String list = ServiceLocator.getVendorAjaxHandlerService().getVendorSearchDetails( this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
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
     *
     * @Author:rama krishna<lankireddy@miraclesoft.com>
     * @Created Date:05/05/2015
     * @for Use :update vendor Details
     *
     * *************************************
     */
    public String updateVendorDetails() {
        resultType = LOGIN;
        String stateList = "";
        try {
//            System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));



                int result = ServiceLocator.getVendorAjaxHandlerService().updateVendorDetails( this);
//                System.out.println("===============>in ajax  handler action" + stateList);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     *
     * @Author:rama krishna<lankireddy@miraclesoft.com>
     * @Created Date:05/05/2015
     * @for Use :getting states through country in hibernate
     *
     * *************************************
     */
    public String getStatesStringByCountry() {
        resultType = LOGIN;
        String stateList = "";
        try {
            System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                stateList = ServiceLocator.getLocationService().getStatesStringOfCountry(httpServletRequest, getCountryId());
//                System.out.println("===============>in ajax  handler action" + stateList);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String getVendorContacts() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getOrgId());
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                reponseString = ServiceLocator.getVendorAjaxHandlerService().getVendorContactDetails(getOrgId());
                System.out.println("===============>in titles" + reponseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String saveVendorContacts() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getVendorUserId());
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                reponseString = ServiceLocator.getVendorAjaxHandlerService().saveVendorContacts(getVendorUserId(), userSessionId);
                //System.out.println("===============>in titles" + repoString);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    public String getVendorContactSearchResults() {
        resultType = LOGIN;
        String reponseString = "";
        try {
            System.out.println("Ajax Handler action -->getContactDetails");
            System.out.println("orgid" + getOrgId());
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                reponseString = ServiceLocator.getVendorAjaxHandlerService().getVendorContactSearchResults(this, getOrgId());
                System.out.println("===============>in searchResults" + reponseString);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:06/May/2015
     *
     ***********************************
     */
    public String getVendorsListByTireType() {
        resultType = LOGIN;
        String stateList = "";
        try {
            System.out.println("Ajax Handler action" + this.getTireId() + "  " + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID));
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                System.out.println("this is in handler getVendorsListByTireType");
                stateList = ServiceLocator.getVendorAjaxHandlerService().getVendorsListByTireType( this);
//                System.out.println("===============>in ajax  handler action" + stateList);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:06/May/2015
     *
     ***********************************
     */
    public String SaveVendorsAssociationDetals() {
        resultType = LOGIN;
        int stateList = 0;
        int result = 0, mailResult = 0;
        RequirementVTO requirementVTO = null;
        try {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Entered in to the Action &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getSessionOrgId()));

                System.out.println("=============================================================================");
                System.out.println("IN ACTION VENDOR LIST>>>>>>>>>>>>>>>>>>>>>>>>" + getVendorList() + " AND ACCNAME>>>" + getAccountName());
                System.out.println("=============================================================================");
                requirementVTO = dataSourceDataProvider.getInstance().setRequirementDetails(getReq_id());
                stateList = ServiceLocator.getVendorAjaxHandlerService().SaveVendorsAssociationDetals( this);

                    if (stateList > 0) {
                    setMailIds(dataSourceDataProvider.getInstance().getMailIdsOfVendorManagerAndLeads(getVendorList()));
                    StringTokenizer mailID = new StringTokenizer(getMailIds(), ",");

                    while (mailID.hasMoreElements()) {
                        setMailIds(mailID.nextElement().toString());
                        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&>>>>" + getMailIds());
                        mailResult = mailManager.requirementReleaseMailGenerator(requirementVTO, getMailIds(), getUserSessionId(), getSessionOrgId(), getAccountName());
                    }

                    if (mailResult > 0) {
                        System.out.println("Email logger added ================================>%%%%%%%%%%%%%%%%%%%%%%%%");
                    }
                }


                System.out.println("Ajax Handler action in save   3");

                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList + "");

            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:06/May/2015
     *
     ***********************************
     */
    public String getVendorAssociationDetails() {
        resultType = LOGIN;
        String stateList = "";
        try {
            System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                stateList = ServiceLocator.getVendorAjaxHandlerService().getVendorAssociationDetails( this);
//                System.out.println("===============>in ajax  handler action" + stateList);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:praveen kumar<pkatru@miraclesoft.com>
     *
     * @Created Date:06/May/2015
     *
     ***********************************
     */
    public String searchVendorAssociationDetails() {
        resultType = LOGIN;
        String stateList = "";
        try {
            System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("===============>in ajax  handler action praven");

                stateList = ServiceLocator.getVendorAjaxHandlerService().searchVendorAssociationDetails( this);
                System.out.println("===============>in ajax  handler action" + stateList);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:14/May/2015
     *
     ***********************************
     */
    public String editVendorAssociation() {
        resultType = LOGIN;
        String vendorList = "";
        try {
            System.out.println("Ajax Handler action");
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("===============>in ajax  handler action praven");
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                vendorList = ServiceLocator.getVendorAjaxHandlerService().editVendorAssociation( getVendorId(),getSessionOrgId());
                System.out.println("===============>in ajax  handler action" + vendorList);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(vendorList);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:15/May/2015
     *
     ***********************************
     */
    public String getVendorNames() {
        resultType = LOGIN;
        String vendorName = "";
        try {
            System.out.println("Ajax Handler action>>>>>>>>>>>>>>In get vendor details " + getTireId() + "  " + httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID));
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("===============>in ajax  handler action praven" + getTireId());

                vendorName = ServiceLocator.getVendorAjaxHandlerService().getVendorNames(getTireId());
                System.out.println("===============>in ajax  handler action" + vendorName);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(vendorName);

                //  resultType = SUCCESS;
            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        return null;
    }

    /**
     * *************************************
     *
     * @Author:Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * @Created Date:15/May/2015
     *
     ***********************************
     */
    public String updateVendorAssociationDetails() {
        resultType = LOGIN;
        String result = "";
        int updateResult = 0;
        try {
            System.out.println("Ajax Handler action---->" + getVendorId());
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                System.out.println("===============>in ajax  handler action ");

                updateResult = ServiceLocator.getVendorAjaxHandlerService().updateVendorAssociationDetails(this);
                System.out.println("Update Result====>" + updateResult);
                if (updateResult > 0) {
                    result = "Success";
                } else {
                    result = "error";
                }
                System.out.println("===============>in ajax  handler action" + updateResult);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

                //  resultType = SUCCESS;
            } else {
                return null;
            }
        } catch (Exception e) {
            resultType = null;
        }
        return null;
    }

    /**
     * ****************************************************************************
     * Date : June 02 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * *****************************************************************************
     */
    public String getVendorDashboardList() {
        resultType = LOGIN;
        String result = "";
        System.out.println("getVendorDashboardList---->AJAX");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                System.out.println("month------->" + getMonth());
                result = ServiceLocator.getVendorAjaxHandlerService().getVendorDashboardList(getYear(), getMonth(), getSessionOrgId());
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

                //  resultType = SUCCESS;
            } else {
                return null;
            }
        } catch (Exception e) {
            resultType = null;
        }
        return null;
    }
    public String getVendorReqDashBoardGrid() {
        resultType = LOGIN;
        String result = "";
        System.out.println("getVendorDashboardList---->AJAX");
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                result = ServiceLocator.getVendorAjaxHandlerService().getVendorReqDashBoardGrid(this);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

                //  resultType = SUCCESS;
            } else {
                return null;
            }
        } catch (Exception e) {
            resultType = null;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorURL() {
        return vendorURL;
    }

    public void setVendorURL(String vendorURL) {
        this.vendorURL = vendorURL;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorState() {
        return vendorState;
    }

    public void setVendorState(String vendorState) {
        this.vendorState = vendorState;
    }

    public String getVendorCountry() {
        return vendorCountry;
    }

    public void setVendorCountry(String vendorCountry) {
        this.vendorCountry = vendorCountry;
    }

    public String getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(String vendorStatus) {
        this.vendorStatus = vendorStatus;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getVendorAddress1() {
        return vendorAddress1;
    }

    public void setVendorAddress1(String vendorAddress1) {
        this.vendorAddress1 = vendorAddress1;
    }

    public String getVendorAddress2() {
        return vendorAddress2;
    }

    public void setVendorAddress2(String vendorAddress2) {
        this.vendorAddress2 = vendorAddress2;
    }

    public String getVendorCity() {
        return vendorCity;
    }

    public void setVendorCity(String vendorCity) {
        this.vendorCity = vendorCity;
    }

    public String getVendorFax() {
        return vendorFax;
    }

    public void setVendorFax(String vendorFax) {
        this.vendorFax = vendorFax;
    }

    public String getVendorZip() {
        return vendorZip;
    }

    public void setVendorZip(String vendorZip) {
        this.vendorZip = vendorZip;
    }

    public int getVendorIndustry() {
        return vendorIndustry;
    }

    public void setVendorIndustry(int vendorIndustry) {
        this.vendorIndustry = vendorIndustry;
    }

    public String getVendorRegion() {
        return vendorRegion;
    }

    public void setVendorRegion(String vendorRegion) {
        this.vendorRegion = vendorRegion;
    }

    public String getVendorTerritory() {
        return vendorTerritory;
    }

    public void setVendorTerritory(String vendorTerritory) {
        this.vendorTerritory = vendorTerritory;
    }

    public int getVendorType() {
        return vendorType;
    }

    public void setVendorType(int vendorType) {
        this.vendorType = vendorType;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public int getVendorBudget() {
        return vendorBudget;
    }

    public void setVendorBudget(int vendorBudget) {
        this.vendorBudget = vendorBudget;
    }

    public String getVendorTaxid() {
        return vendorTaxid;
    }

    public void setVendorTaxid(String vendorTaxid) {
        this.vendorTaxid = vendorTaxid;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getVendorRvenue() {
        return vendorRvenue;
    }

    public void setVendorRvenue(int vendorRvenue) {
        this.vendorRvenue = vendorRvenue;
    }

    public int getEmpCount() {
        return empCount;
    }

    public void setEmpCount(int empCount) {
        this.empCount = empCount;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getVendorUserId() {
        return vendorUserId;
    }

    public void setVendorUserId(int vendorUserId) {
        this.vendorUserId = vendorUserId;
    }

    public String getVendorFirstName() {
        return vendorFirstName;
    }

    public void setVendorFirstName(String vendorFirstName) {
        this.vendorFirstName = vendorFirstName;
    }

    public String getVendorLastName() {
        return vendorLastName;
    }

    public void setVendorLastName(String vendorLastName) {
        this.vendorLastName = vendorLastName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public int getTireType() {
        return tireType;
    }

    public void setTireType(int tireType) {
        this.tireType = tireType;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String req_id) {
        this.req_id = req_id;
    }

    public String[] getArrayList() {
        return ArrayList;
    }

    public void setArrayList(String[] ArrayList) {
        this.ArrayList = ArrayList;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public String getVendorList() {
        return vendorList;
    }

    public void setVendorList(String vendorList) {
        this.vendorList = vendorList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorFlag() {
        return vendorFlag;
    }

    public void setVendorFlag(String vendorFlag) {
        this.vendorFlag = vendorFlag;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public Map getTeamMembersList() {
        return teamMembersList;
    }

    public void setTeamMembersList(Map teamMembersList) {
        this.teamMembersList = teamMembersList;
    }

    public int getTireId() {
        return tireId;
    }

    public void setTireId(int tireId) {
        this.tireId = tireId;
    }

    public int getTireTypeId() {
        return tireTypeId;
    }

    public void setTireTypeId(int tireTypeId) {
        this.tireTypeId = tireTypeId;
    }

    public String getStatusEdit() {
        return statusEdit;
    }

    public void setStatusEdit(String statusEdit) {
        this.statusEdit = statusEdit;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
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

    public String getMailIds() {
        return mailIds;
    }

    public void setMailIds(String mailIds) {
        this.mailIds = mailIds;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
}
