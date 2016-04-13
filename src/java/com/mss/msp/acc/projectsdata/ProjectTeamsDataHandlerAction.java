/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Senay
 */
public class ProjectTeamsDataHandlerAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private String resultType;
    private Integer projectID;
    private Integer userID;
    private String status;
    private String createdDate;
    private Integer createdBy;
    private String modifiedDate;
    private Integer modifiedBy;
    private Integer reportsTo1;
    private Integer reportsTo2;
    private String teamMemberName;
    private int ppid;
    private int accountID;
    private String typeOfUser;
    private int sessionOrgId;
    private int sessionUserId;
    private int dashBoardYear;
    private int mainProjectID;
    private int subProjects;
    private String projectsFlag;
    /**
     * The projectsTeamList list is used for storing data for team members per
     * project
     */
    List<ProjectTeamsVTO> projectsTeamList = new ArrayList<ProjectTeamsVTO>();
    Map teamReportsToList = new HashMap();
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private DataSourceDataProvider dataSourceDataProvider;
    java.util.Date currentDate = new java.util.Date();

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectsTeam() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getProjectsTeam() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: getProjectsTeam Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                projectsTeamList = ServiceLocator.getProjectTeamsDataHandlerService().getProjectsTeam(projectID);
                if (projectsTeamList.size() > 0) {
                    resultType = SUCCESS;
                } else {
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: getProjectsTeam Method End*********************");
        return resultType;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTeamReportsTo() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getTeamReportsTo() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: getTeamReportsTo Method Start*********************");
        resultType = LOGIN;
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                if (teamReportsToList.size() > 0) {
                    resultType = SUCCESS;
                } else {
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: getTeamReportsTo Method End*********************");
        return Action.SUCCESS;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTeamMemberDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getTeamMemberDetails() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: getTeamMemberDetails Method Start*********************");
        resultType = LOGIN;

        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                String teamMembersResultString = ServiceLocator.getProjectTeamsDataHandlerService().getTeamMemberDetails(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(teamMembersResultString);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: getTeamMemberDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :praveen <pkatru@miraclesoft.com>
     *
     * ForUse : showResourceDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public String showResourceDetails() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: showResourceDetails Method Start*********************");
        resultType = LOGIN;
        String resultString = "";
        try {

            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                resultString = ServiceLocator.getProjectTeamsDataHandlerService().showResourceDetails(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: showResourceDetails Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : EmpReleasefromProject() method is used to
     *
     * *****************************************************************************
     */
    public String EmpReleasefromProject() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: EmpReleasefromProject Method Start*********************");
        resultType = LOGIN;

        String result;
        try {
            if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
                int updateTeamStatus = ServiceLocator.getProjectTeamsDataHandlerService().EmpReleasefromProject(this);

                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                if (updateTeamStatus > 0) {
                    result = "1";
                    httpServletResponse.getWriter().write(result);
                } else {
                    result = "0";
                    httpServletResponse.getWriter().write(result);
                }

            } else {
                return resultType;
            }
        } catch (Exception e) {
            resultType = ERROR;
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: EmpReleasefromProject Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :september 30, 2015
     *
     * Author :Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : getProjectDashboardList() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getProjectDashboardList() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: getProjectDashboardList Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {
                String resultString = null;
                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());

                resultString = ServiceLocator.getProjectTeamsDataHandlerService().getProjectDashboardList(this);



                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: getProjectDashboardList Method End*********************");
        return null;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectsForYear() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getProjectsForYear() {
        System.out.println("********************ProjectTeamsDataHandlerAction :: getProjectsForYear Method Start*********************");
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            try {

                setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));

                setSessionUserId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());

                String resultString = ServiceLocator.getProjectTeamsDataHandlerService().getProjectsForYear(this);



                httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                httpServletResponse.setHeader("Pragma", "no-cache");
                httpServletResponse.setDateHeader("Expires", 0);
                httpServletResponse.setContentType("text");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.getWriter().write(resultString);
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
            }
        }
        System.out.println("********************ProjectTeamsDataHandlerAction :: getProjectsForYear Method End*********************");
        return null;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.httpServletRequest = hsr;
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

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getReportsTo1() {
        return reportsTo1;
    }

    public void setReportsTo1(Integer reportsTo1) {
        this.reportsTo1 = reportsTo1;
    }

    public Integer getReportsTo2() {
        return reportsTo2;
    }

    public void setReportsTo2(Integer reportsTo2) {
        this.reportsTo2 = reportsTo2;
    }

    public List<ProjectTeamsVTO> getProjectsTeamList() {
        return projectsTeamList;
    }

    public void setProjectsTeamList(List<ProjectTeamsVTO> projectsTeamList) {
        this.projectsTeamList = projectsTeamList;
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

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Map getTeamReportsToList() {
        return teamReportsToList;
    }

    public void setTeamReportsToList(Map teamReportsToList) {
        this.teamReportsToList = teamReportsToList;
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public void setTeamMemberName(String teamMemberName) {
        this.teamMemberName = teamMemberName;
    }

    public int getPpid() {
        return ppid;
    }

    public void setPpid(int ppid) {
        this.ppid = ppid;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
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

    public int getDashBoardYear() {
        return dashBoardYear;
    }

    public void setDashBoardYear(int dashBoardYear) {
        this.dashBoardYear = dashBoardYear;
    }

    public int getMainProjectID() {
        return mainProjectID;
    }

    public void setMainProjectID(int mainProjectID) {
        this.mainProjectID = mainProjectID;
    }

    public int getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(int subProjects) {
        this.subProjects = subProjects;
    }

    public String getProjectsFlag() {
        return projectsFlag;
    }

    public void setProjectsFlag(String projectsFlag) {
        this.projectsFlag = projectsFlag;
    }
}
