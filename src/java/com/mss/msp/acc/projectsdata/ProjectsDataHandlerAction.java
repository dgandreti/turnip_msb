package com.mss.msp.acc.projectsdata;

import com.mss.msp.acc.details.AccountDetailsAction;
import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Riza Erbas Action Class for the Projects functionalities
 */
public class ProjectsDataHandlerAction extends ActionSupport implements ServletRequestAware {

    private Integer projectID;
    private Integer accountID;
    private String projectName;
    private String projectType;
    private String projectReqSkillSet;
    private String project_description;
    private String projectStartDate;
    private String projectTargetDate;
    private String project_status;
    private Integer parentProjectID;
    private String parentProjectName;
    private Integer projectCreatedBy;
    private String projectCreatedDate;
    private Integer projectModifiedBy;
    private String projectModifiedDate;
    private String resultType;
    private ByteArrayInputStream inputStream;
    private int numberOfResources;
    private String projectsActionResponse;
    private String account_name;
    private Boolean parentProjectCheck;
    private String roleValue;
    private String typeOfUser;
    private int sessionOrgId;
    private Map costCenterNames;
    private String costCenterName;
    private Double projectTargetHrs;
    private Double projectWorkedHrs;
    private int dashBoardYear;
    private Map projectsMap;
    private String remainingTargetHrs;
    /**
     * The project object is used for storing ProjectsVTO object for the
     * ProjectDetails.jsp for displaying project details and updating the
     * retreived project
     */
    ProjectsVTO project = new ProjectsVTO();
    /**
     * The searchDetails list is used as a central location for storing search
     * results from the searches performed by multiple methods.
     */
    List<ProjectsVTO> searchDetails = new ArrayList<ProjectsVTO>();
    /**
     * The projectsTeamList list is used for storing data for team members per
     * project
     */
    List<ProjectTeamsVTO> projectsTeamList = new ArrayList<ProjectTeamsVTO>();
    /**
     * The accountDetailsAction object is used for accessing the variables in
     * the AccountDetailsAction class.
     */
    AccountDetailsAction accountDetailsAction;
    /**
     * The resultMessage is used for storing resultMessage.
     */
    private String resultMessage;
    /**
     * The httpServletRequest is used for storing httpServletRequest Object
     *
     */
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private DataSourceDataProvider dataSourceDataProvider;
    java.util.Date currentDate = new java.util.Date();

    public ProjectsDataHandlerAction() {
    }
    ProjectsDataHandlerService projectsDataHandlerService = new ProjectsDataHandlerServiceImpl();

    public String getProjectSearchDetails() throws ParseException {
        resultType = LOGIN;
        String currentFormat = "MM-dd-yyyy";
        String newFormat = "yyyy-MM-dd";
        System.out.println("::::::::::by nag:::: ProjectsDataHandlerAction ==> getProjectSearchDetails ::::::::::::::::::");

        System.out.println("accountID from the page is " + accountID);

        System.out.println("parent project id is " + getParentProjectID());
        roleValue = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLEVALUE).toString();

//        if (!projectStartDate.equals("") && projectStartDate != null) {
//            projectStartDate = formatDate(projectStartDate, currentFormat, newFormat);
//        };
//        if (!projectTargetDate.equals("") && projectTargetDate != null) {
//            projectTargetDate = formatDate(projectTargetDate, currentFormat, newFormat);
//        };
        try {

            if (httpServletRequest.getSession(false).getAttributeNames() != null) {

                searchDetails = ServiceLocator.getProjectDataHandlerService().getProjectSearchDetails(this, httpServletRequest);
                setCostCenterNames(DataSourceDataProvider.getInstance().getCostCenterNames(getSessionOrgId()));
                searchDetails = processProjectType(searchDetails);
                System.out.println(searchDetails.size());
                if (searchDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("projectData", searchDetails);

                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("projectData", null);
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

    public String checkProjectNames() throws ServiceLocatorException, UnsupportedEncodingException {
        System.out.println(":::::::::::::: ProjectsDataHandlerAction ==> checkProjectNames ::::::::::::::::::");

        String projName = httpServletRequest.getAttribute("projectName").toString();
        inputStream = new ByteArrayInputStream(ServiceLocator.getProjectDataHandlerService().checkProjectName(projName).toString().getBytes("ISO-8859-1"));
        System.out.println(inputStream.toString().toUpperCase());
        return SUCCESS;
    }

    public String getProjectsByAccID() {
        resultType = LOGIN;

        System.out.println("::::::by nag:::::::: ProjectsDataHandlerAction ==> getProjectsByAccID ::::::::::::::::::");
        System.out.println("accountID from the page is " + accountID);
        projectCreatedBy = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
        System.out.println(">>>>>>>>>>>>>>User Session id:::::" + projectCreatedBy);
        accountID = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
        System.out.println("accountID was null getting accountID from the session. accountID is: " + accountID);
        roleValue = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLEVALUE).toString();
        System.out.println(">>>>>>>>>>>>>>ROLE IN ACTION>>>>" + roleValue);
        setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                searchDetails = ServiceLocator.getProjectDataHandlerService().getProjectsByAccID(accountID, projectCreatedBy, roleValue);
                searchDetails = processProjectType(searchDetails);
                setCostCenterNames(DataSourceDataProvider.getInstance().getCostCenterNames(getSessionOrgId()));
                System.out.println(searchDetails.size());
                if (searchDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("projectData", searchDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("projectData", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String addProject() {
        System.out.println(":::::::::::::: ProjectsDataHandlerAction ==> addProject ::::::::::::::::::");

        String currentFormat = "MM-dd-yyyy";
        String newFormat = "yyyy-MM-dd";

        resultType = LOGIN;

        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                project.setAccountID(accountID);
                project.setProjectName(projectName);
                project.setProjectType("MP");
                project.setProjectReqSkillSet(projectReqSkillSet);
                project.setProject_description(project_description);
                project.setProjectStartDate(formatDate(projectStartDate, currentFormat, newFormat));
                project.setProjectTargetDate(formatDate(projectTargetDate, currentFormat, newFormat));
                project.setProject_status(project_status);
                project.setParentProjectID(null);
                project.setProjectModifiedBy(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                project.setProjectCreatedBy(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                projectModifiedDate = new Timestamp(currentDate.getTime()).toString();
                projectCreatedDate = new Timestamp(currentDate.getTime()).toString();
                project.setProjectModifiedDate(projectModifiedDate);
                project.setProjectCreatedDate(projectCreatedDate);
                project.setCostCenterName(costCenterName);
                project.setProjectTargetHrs(projectTargetHrs);
                System.out.println("-------projectWorkedHrs------------" + projectWorkedHrs);
                project.setProjectWorkedHrs(projectWorkedHrs);
                System.out.println(project.toString());
                projectsDataHandlerService.addProject(project);
                projectsActionResponse = "\"" + projectName + "\" Project has been added.";
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }

    public String addSubProject() {
        System.out.println(":::::::::::::: ProjectsDataHandlerAction ==> addSubProject ::::::::::::::::::");

        resultType = LOGIN;

        String currentFormat = "MM-dd-yyyy";
        String newFormat = "yyyy-MM-dd";

        ProjectsVTO project = (ProjectsVTO) httpServletRequest.getSession(false).getAttribute("project");
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                project.setAccountID(project.getAccountID());
                project.setProjectName(projectName);
                project.setProjectType("SP");
                project.setProjectReqSkillSet(projectReqSkillSet);
                project.setProject_description(project_description);
                project.setProjectStartDate(formatDate(projectStartDate, currentFormat, newFormat));
                project.setProjectTargetDate(formatDate(projectTargetDate, currentFormat, newFormat));
                project.setProject_status(project_status);
                project.setParentProjectID(project.getProjectID());
                project.setProjectModifiedBy(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                project.setProjectCreatedBy(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString()));
                projectModifiedDate = new Timestamp(currentDate.getTime()).toString();
                projectCreatedDate = new Timestamp(currentDate.getTime()).toString();
                project.setProjectModifiedDate(projectModifiedDate);
                project.setProjectCreatedDate(projectCreatedDate);
                project.setCostCenterName(null);
                project.setProjectTargetHrs(projectTargetHrs);
                System.out.println("-------projectWorkedHrs------------" + projectWorkedHrs);
                project.setProjectWorkedHrs(projectWorkedHrs);

                //Setting accountID and projectID to access from chained actions
                setAccountID(project.getAccountID());
                setProjectID(project.getProjectID());

                //Printing the object to verify the correct input
                System.out.println("The object being added is: " + project.toString());

                projectsDataHandlerService.addProject(project);
                projectsActionResponse = "\"" + projectName + "\" Sub-Project has been added.";
                System.out.println(projectsActionResponse);
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }

    public String getProjectDetails() {
        System.out.println(":::::::::::::: ProjectsDataHandlerAction ==> getProjectDetails ::::::::::::::::::");

        resultType = LOGIN;
        setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
        System.out.println("project Id is " + projectID);
        System.out.println("accountID is " + accountID);
        if (projectID != null) {
            httpServletRequest.getSession(false).setAttribute("projectID", projectID);
            httpServletRequest.setAttribute("parentProjectID", projectID);
        }
        if (projectID == null) {
            projectID = Integer.parseInt(httpServletRequest.getSession(false).getAttribute("projectID").toString());
            httpServletRequest.setAttribute("parentProjectID", projectID);
        }

        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                String currentFormat = "yyyy-MM-dd";
                String newFormat = "MM-dd-yyyy";

                System.out.println("in first if");

                project = ServiceLocator.getProjectDataHandlerService().getProjectsByProjectID(this);
                setAccount_name(DataSourceDataProvider.getInstance().getAccountNameById(getAccountID()));

                if (project.getProjectType().equals("MP")) {
                    project.setProjectType("Main Project");
                    setCostCenterNames(DataSourceDataProvider.getInstance().getCostCenterNames(getSessionOrgId()));
                    project.setCostCenterName(project.getCostCenterName());
                } else {
                    project.setProjectType("Sub-Project");
                }

                project.setProjectStartDate(formatDate(project.getProjectStartDate(), currentFormat, newFormat));
                project.setProjectTargetDate(formatDate(project.getProjectTargetDate(), currentFormat, newFormat));
                project.setProjectTargetHrs(project.getProjectTargetHrs());
                project.setProjectWorkedHrs(project.getProjectWorkedHrs());
                httpServletRequest.getSession(false).setAttribute("projectStatus", project.getProject_status());
                numberOfResources = ServiceLocator.getProjectDataHandlerService().getProjectResourceCount(this, httpServletRequest);
                if (!project.getProjectName().isEmpty()) {
                    httpServletRequest.getSession(false).setAttribute("project", project);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("project", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public String updateProject() {
        System.out.println(":::::::::::::: ProjectsDataHandlerAction ==> updateProject ::::::::::::::::::");

        resultType = LOGIN;

        String currentFormat = "MM-dd-yyyy";
        String newFormat = "yyyy-MM-dd";

        ProjectsVTO p = new ProjectsVTO();
        String user = (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID)).toString();
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                p = (ProjectsVTO) httpServletRequest.getSession(false).getAttribute("project");
                if (!p.getProjectName().equals(project.getProjectName())) {
                    p.setProjectName(project.getProjectName());
                }
                p.setProjectModifiedBy(Integer.parseInt(user));
                p.setProjectModifiedDate(new Timestamp(currentDate.getTime()).toString());
                if (!p.getProjectReqSkillSet().equals(project.getProjectReqSkillSet())) {
                    p.setProjectReqSkillSet(project.getProjectReqSkillSet());
                }

                System.out.println("the start date before formatting is: " + project.getProjectStartDate());
                p.setProjectStartDate(formatDate(project.getProjectStartDate(), currentFormat, newFormat).toString());
                System.out.println("the target date before formatting is: " + project.getProjectTargetDate());
                p.setProjectTargetDate(formatDate(project.getProjectTargetDate(), currentFormat, newFormat).toString());

                if (project.getProject_status().equals("-1")) {
                    p.setProject_status(httpServletRequest.getSession(false).getAttribute("projectStatus").toString());
                    System.out.println("projectStatus is " + p.getProject_status().toUpperCase());
                } else {
                    p.setProject_status(project.getProject_status());
                }
                if (!p.getProject_description().equals(project.getProject_description())) {
                    p.setProject_description(project.getProject_description());
                }
               if (p.getProjectType().equals("Main Project")) {
                    if (!project.getCostCenterName().equals("DF")) {
                        p.setCostCenterName(project.getCostCenterName());
                    }
                }
                if (p.getProjectType().equals("Main Project")) {
                    p.setProjectType("MP");
                    System.out.println("The project type in p was Main Project and changed to MP");
                }
                if (p.getProjectType().equals("Sub-Project")) {
                    p.setCostCenterName(null);
                    p.setProjectType("SP");
                    System.out.println("The project type in p was Sub-Project and changed to SP");
                }
                if (project.getProjectTargetHrs() != 0) {
                    p.setProjectTargetHrs(project.getProjectTargetHrs());
                }

                httpServletRequest.getSession(false).setAttribute("projectID", project.getProjectID());
                if (!project.getProjectName().isEmpty()
                        && !project.getProjectTargetDate().isEmpty()
                        // && !project.getProjectReqSkillSet().isEmpty()
                        && !project.getProjectStartDate().isEmpty()
                        && !project.getProject_status().isEmpty()) {
                    //  && !project.getProject_description().isEmpty()) {
                    // Setting accountID and projectID so that it can be accessed from chanined methods
                    setAccountID(p.getAccountID());
                    setProjectID(p.getProjectID());

                    //Printing the updated object to see if the output is correct
                    System.out.println("The information updated are " + p.toString());
                    projectsDataHandlerService.updateProject(p, httpServletRequest);
                }
                resultMessage = "Successfully updated";
                resultType = SUCCESS;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;

    }

    public String getSubProjects() {
        System.out.println(":::::::::::::: ProjectsDataHandlerAction ==> getSubProjects ::::::::::::::::::");

        resultType = LOGIN;
        projectID = Integer.parseInt(httpServletRequest.getSession(false).getAttribute("projectID").toString());
        try {
            if (httpServletRequest.getSession(false).getAttributeNames() != null) {
                System.out.println("In getSutProjects ==> first if");
                searchDetails = ServiceLocator.getProjectDataHandlerService().getSubProjects(projectID);
                searchDetails = processProjectType(searchDetails);
                System.out.println(searchDetails.size());
                if (searchDetails.size() > 0) {
                    httpServletRequest.getSession(false).setAttribute("subProjectsList", searchDetails);
                    resultType = SUCCESS;
                } else {
                    httpServletRequest.getSession(false).setAttribute("subProjectsList", null);
                    resultType = SUCCESS;
                }
            }
        } catch (Exception ex) {
            httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            resultType = ERROR;
        }
        return resultType;
    }

    public List<ProjectsVTO> processProjectType(List<ProjectsVTO> projectList) {
        for (int i = 0; i < projectList.size(); i++) {
            try {
                String currentFormat = "yyyy-MM-dd";
                String newFormat = "MM-dd-yyyy";
                projectList.get(i).setProjectStartDate(formatDate(projectList.get(i).getProjectStartDate(), currentFormat, newFormat));
                //Truncate the String so the HH:MM:SS isn't in it, some of them are set at 0000:00:00 which casues problems
                projectList.get(i).setProjectTargetDate(formatDate(projectList.get(i).getProjectTargetDate().substring(0, 10), currentFormat, newFormat));
            } catch (ParseException p) {
            }
            if (projectList.get(i).getProjectType().equals("MP")) {
                projectList.get(i).setProjectType("Main Project");
            } else {
                projectList.get(i).setProjectType("Sub-Project");
            }
        }
        return projectList;
    }
     /**
     * *****************************************************************************
     * Date : October 06, 2015, 04:13 PM IST
     * Author:Manikanta<meeralla@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
     public String getProjectsDashboard() {
        resultType = LOGIN;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null) {
            setTypeOfUser(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.TYPE_OF_USER).toString());
            setSessionOrgId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString()));
            try {
                setDashBoardYear(Calendar.getInstance().get(Calendar.YEAR));
                projectsMap = DataSourceDataProvider.getInstance().getProjectsMap(getSessionOrgId(),"MP",getDashBoardYear());
                searchDetails=ServiceLocator.getProjectDataHandlerService().getProjectsDashboard(this);
                resultType = SUCCESS;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                resultType = ERROR;
            }
        }
        return resultType;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.httpServletRequest = hsr;
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

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectReqSkillSet() {
        return projectReqSkillSet;
    }

    public void setProjectReqSkillSet(String projectReqSkillSet) {
        this.projectReqSkillSet = projectReqSkillSet;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectTargetDate() {
        return projectTargetDate;
    }

    public void setProjectTargetDate(String projectTargetDate) {
        this.projectTargetDate = projectTargetDate;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public Integer getParentProjectID() {
        return parentProjectID;
    }

    public void setParentProjectID(Integer parentProjectID) {
        this.parentProjectID = parentProjectID;
    }

    public Integer getProjectCreatedBy() {
        return projectCreatedBy;
    }

    public void setProjectCreatedBy(Integer projectCreatedBy) {
        this.projectCreatedBy = projectCreatedBy;
    }

    public Integer getProjectModifiedBy() {
        return projectModifiedBy;
    }

    public void setProjectModifiedBy(Integer projectModifiedBy) {
        this.projectModifiedBy = projectModifiedBy;
    }

    public String getProjectModifiedDate() {
        return projectModifiedDate;
    }

    public void setProjectModifiedDate(String projectModifiedDate) {
        this.projectModifiedDate = projectModifiedDate;
    }

    public ProjectsVTO getProject() {
        return project;
    }

    public void setProject(ProjectsVTO project) {
        this.project = project;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getProjectCreatedDate() {
        return projectCreatedDate;
    }

    public void setProjectCreatedDate(String projectCreatedDate) {
        this.projectCreatedDate = projectCreatedDate;
    }

    public int getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    public List<ProjectsVTO> getSearchDetails() {
        return searchDetails;
    }

    public void setSearchDetails(List<ProjectsVTO> searchDetails) {
        this.searchDetails = searchDetails;
    }

    public String getParentProjectName() {
        return parentProjectName;
    }

    public void setParentProjectName(String parentProjectName) {
        this.parentProjectName = parentProjectName;
    }

    public String getProjectsActionResponse() {
        return projectsActionResponse;
    }

    public void setProjectsActionResponse(String projectsActionResponse) {
        this.projectsActionResponse = projectsActionResponse;
    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);
        System.out.println("The date after formatting is: " + parsedDate);
        return parsedDate;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Boolean getParentProjectCheck() {
        return parentProjectCheck;
    }

    public void setParentProjectCheck(Boolean parentProjectCheck) {

        this.parentProjectCheck = parentProjectCheck;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
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

    public int getDashBoardYear() {
        return dashBoardYear;
    }

    public void setDashBoardYear(int dashBoardYear) {
        this.dashBoardYear = dashBoardYear;
    }

    public Map getProjectsMap() {
        return projectsMap;
    }

    public void setProjectsMap(Map projectsMap) {
        this.projectsMap = projectsMap;
    }

    public Map getCostCenterNames() {
        return costCenterNames;
    }

    public void setCostCenterNames(Map costCenterNames) {
        this.costCenterNames = costCenterNames;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public Double getProjectTargetHrs() {
        return projectTargetHrs;
    }

    public void setProjectTargetHrs(Double projectTargetHrs) {
        this.projectTargetHrs = projectTargetHrs;
    }

    public Double getProjectWorkedHrs() {
        return projectWorkedHrs;
    }

    public void setProjectWorkedHrs(Double projectWorkedHrs) {
        this.projectWorkedHrs = projectWorkedHrs;
    }

    public String getRemainingTargetHrs() {
        return remainingTargetHrs;
    }

    public void setRemainingTargetHrs(String remainingTargetHrs) {
        this.remainingTargetHrs = remainingTargetHrs;
    }
    
}
