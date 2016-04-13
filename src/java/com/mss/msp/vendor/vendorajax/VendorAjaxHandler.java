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
     * *****************************************************************************
     * Date : MAY 04, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorStates() method is used to get states by country code
     *
     * *****************************************************************************
     */
    public String getVendorStates() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorStates Method Start*********************");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String states = ServiceLocator.getVendorAjaxHandlerService().getVendorStates(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(states);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }// Session validator if END
        System.out.println("********************VendorAjaxHandler Action :: getVendorStates Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : MAY 04, 2015, 8:30 PM IST
     *
     * Author : Praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorSearchDetails() method is used to search and get vendor
     * details.
     *
     * *****************************************************************************
     */
    public String getVendorSearchDetails() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorSearchDetails Method Start*********************");
        resultMessage = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                setSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setTeamMembersList(dataSourceDataProvider.getInstance().getMyTeamMembers(getSessionId()));
                String list = ServiceLocator.getVendorAjaxHandlerService().getVendorSearchDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(list);
                resultMessage = null;
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = ERROR;
            }
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorSearchDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 5, 2015, 8:30 PM IST
     *
     * Author : rama krishna<lankireddy@miraclesoft.com>
     *
     * ForUse : updateVendorDetails() method is used to update vendor details.
     *
     * *****************************************************************************
     */
    public String updateVendorDetails() {
        System.out.println("********************VendorAjaxHandler Action :: updateVendorDetails Method Start*********************");
        resultType = LOGIN;
        String stateList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {

                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                int result = ServiceLocator.getVendorAjaxHandlerService().updateVendorDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: updateVendorDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 05/05/2015
     *
     * Author : rama krishna<lankireddy@miraclesoft.com>
     *
     * ForUse : getStatesStringByCountry() method is used to getting states
     * through country in hibernate.
     *
     * *****************************************************************************
     */
    public String getStatesStringByCountry() {
        System.out.println("********************VendorAjaxHandler Action :: getStatesStringByCountry Method Start*********************");
        resultType = LOGIN;
        String stateList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                stateList = ServiceLocator.getLocationService().getStatesStringOfCountry(httpServletRequest, getCountryId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: getStatesStringByCountry Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorContactDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getVendorContacts() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorContactDetails Method Start*********************");
        resultType = LOGIN;
        String reponseString = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                reponseString = ServiceLocator.getVendorAjaxHandlerService().getVendorContactDetails(getOrgId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorContactDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : saveVendorContacts() method is used to
     *
     * *****************************************************************************
     */
    public String saveVendorContacts() {
        System.out.println("********************VendorAjaxHandler Action :: saveVendorContacts Method Start*********************");
        resultType = LOGIN;
        String reponseString = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                userSessionId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
                reponseString = ServiceLocator.getVendorAjaxHandlerService().saveVendorContacts(getVendorUserId(), userSessionId);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: saveVendorContacts Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorContactSearchResults() method is used
     *
     * *****************************************************************************
     */
    public String getVendorContactSearchResults() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorContactSearchResults Method Start*********************");
        resultType = LOGIN;
        String reponseString = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                reponseString = ServiceLocator.getVendorAjaxHandlerService().getVendorContactSearchResults(this, getOrgId());
                System.out.println("===============>in searchResults" + reponseString);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(reponseString);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorContactSearchResults Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : May 06 2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorsListByTireType() method is used
     *
     * *****************************************************************************
     */
    public String getVendorsListByTireType() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorsListByTireType Method Start*********************");
        resultType = LOGIN;
        String stateList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                stateList = ServiceLocator.getVendorAjaxHandlerService().getVendorsListByTireType(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorsListByTireType Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 06/May/2015
     *
     * Author :praveen kumar<pkatru@miraclesoft.com>
     *
     * ForUse : SaveVendorsAssociationDetals() method is used
     *
     * *****************************************************************************
     */
    public String SaveVendorsAssociationDetals() {
        System.out.println("********************VendorAjaxHandler Action :: SaveVendorsAssociationDetals Method Start*********************");
        resultType = LOGIN;
        int stateList = 0;
        int result = 0, mailResult = 0;
        RequirementVTO requirementVTO = null;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setAccountName(dataSourceDataProvider.getInstance().getAccountNameById(getOrgId()));
                requirementVTO = dataSourceDataProvider.getInstance().setRequirementDetails(getReq_id());
                stateList = ServiceLocator.getVendorAjaxHandlerService().SaveVendorsAssociationDetals(this);

                if (stateList > 0) {
                    setMailIds(dataSourceDataProvider.getInstance().getMailIdsOfVendorManagerAndLeads(getVendorList()));
                    StringTokenizer mailID = new StringTokenizer(getMailIds(), ",");

                    while (mailID.hasMoreElements()) {
                        setMailIds(mailID.nextElement().toString());
                        mailResult = mailManager.requirementReleaseMailGenerator(requirementVTO, getMailIds(), getUserSessionId(), getOrgId(), getAccountName());
                    }

                    if (mailResult > 0) {
                    }
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList + "");

            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: SaveVendorsAssociationDetals Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 06/05/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : getVendorAssociationDetails() method is used update vendor
     * details.
     *
     * *****************************************************************************
     */
    public String getVendorAssociationDetails() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorAssociationDetails Method Start*********************");
        resultType = LOGIN;
        String stateList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                stateList = ServiceLocator.getVendorAjaxHandlerService().getVendorAssociationDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorAssociationDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 06/05/2015
     *
     * Author : praveen<pkatru@miraclesoft.com>
     *
     * ForUse : searchVendorAssociationDetails() method is used search vendor
     * details.
     *
     * *****************************************************************************
     */
    public String searchVendorAssociationDetails() {
        System.out.println("********************VendorAjaxHandler Action :: searchVendorAssociationDetails Method Start*********************");
        resultType = LOGIN;
        String stateList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                stateList = ServiceLocator.getVendorAjaxHandlerService().searchVendorAssociationDetails(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(stateList);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: searchVendorAssociationDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 14/May/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : editVendorAssociation() method is used retrieve vendor details
     * based on vendorId.
     *
     * *****************************************************************************
     */
    public String editVendorAssociation() {
        System.out.println("********************VendorAjaxHandler Action :: editVendorAssociation Method Start*********************");
        resultType = LOGIN;
        String vendorList = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                vendorList = ServiceLocator.getVendorAjaxHandlerService().editVendorAssociation(getVendorId(), getSessionOrgId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(vendorList);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: editVendorAssociation Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/May/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : getVendorNames() method is used
     *
     * *****************************************************************************
     */
    public String getVendorNames() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorNames Method Start*********************");
        resultType = LOGIN;
        String vendorName = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                vendorName = ServiceLocator.getVendorAjaxHandlerService().getVendorNames(getTireId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(vendorName);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorNames Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : 15/May/2015
     *
     * Author : Aklakh Ahmad<mahmad@miraclesoft.com>
     *
     * ForUse : updateVendorAssociationDetails() method is used
     *
     * *****************************************************************************
     */
    public String updateVendorAssociationDetails() {
        System.out.println("********************VendorAjaxHandler Action :: updateVendorAssociationDetails Method Start*********************");
        resultType = LOGIN;
        String result = "";
        int updateResult = 0;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                updateResult = ServiceLocator.getVendorAjaxHandlerService().updateVendorAssociationDetails(this);
                if (updateResult > 0) {
                    result = "Success";
                } else {
                    result = "error";
                }
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

            } else {
                return null;
            }
        } catch (Exception e) {
            resultType = null;
        }
        System.out.println("********************VendorAjaxHandler Action :: updateVendorAssociationDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date : June 02 2015
     *
     * Author : manikanta eeralla<meeralla@miraclesoft.com>
     *
     * ForUse : getVendorDashboardList()
     *
     * *****************************************************************************
     */
    public String getVendorDashboardList() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorDashboardList Method Start*********************");
        resultType = LOGIN;
        String result = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                result = ServiceLocator.getVendorAjaxHandlerService().getVendorDashboardList(getYear(), getMonth(), getSessionOrgId());
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

            } else {
                return null;
            }
        } catch (Exception e) {
            resultType = null;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorDashboardList Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getVendorReqDashBoardGrid()
     *
     * *****************************************************************************
     */
    public String getVendorReqDashBoardGrid() {
        System.out.println("********************VendorAjaxHandler Action :: getVendorReqDashBoardGrid Method Start*********************");
        resultType = LOGIN;
        String result = "";
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
                setUserSessionId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                result = ServiceLocator.getVendorAjaxHandlerService().getVendorReqDashBoardGrid(this);
                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(result);

            } else {
                return null;
            }
        } catch (Exception e) {
            resultType = null;
        }
        System.out.println("********************VendorAjaxHandler Action :: getVendorReqDashBoardGrid Method End*********************");
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
