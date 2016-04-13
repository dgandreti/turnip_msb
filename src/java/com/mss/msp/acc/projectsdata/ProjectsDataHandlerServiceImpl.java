/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Riza
 */
public class ProjectsDataHandlerServiceImpl implements ProjectsDataHandlerService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectSearchDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public List getProjectSearchDetails(ProjectsDataHandlerAction projectsDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectSearchDetails Method Start*********************");
        DateUtility dateUtility = new DateUtility();
        List<ProjectsVTO> projectsList = new ArrayList<ProjectsVTO>();
        String sql;

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.eq("accountID", projectsDataHandlerAction.getAccountID()));
        if (projectsDataHandlerAction.getProjectReqSkillSet() != null && !projectsDataHandlerAction.getProjectReqSkillSet().equals("")) {
            sql = " proj_req_skillset like '%" + projectsDataHandlerAction.getProjectReqSkillSet().toString().trim() + "%'";

            query.add(Restrictions.sqlRestriction(sql));
        }
        if (!projectsDataHandlerAction.getProjectName().equals("") && projectsDataHandlerAction.getProjectName() != null) {
            query.add(Restrictions.ilike("projectName", projectsDataHandlerAction.getProjectName().toString().trim(), MatchMode.ANYWHERE));

        }
        if (!"".equals(projectsDataHandlerAction.getProjectStartDate()) && projectsDataHandlerAction.getProjectStartDate() != null) {
            String startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(projectsDataHandlerAction.getProjectStartDate());
            sql = " proj_stdate >= '" + startDate.trim() + "'";
            query.add(Restrictions.sqlRestriction(sql));

        }
        if (!"".equals(projectsDataHandlerAction.getProjectTargetDate()) && projectsDataHandlerAction.getProjectTargetDate() != null) {
            String targetDate = dateUtility.getInstance().convertStringToMySQLDateInDash(projectsDataHandlerAction.getProjectTargetDate());
            sql = " proj_stdate <= '" + targetDate.trim() + "'";
            query.add(Restrictions.sqlRestriction(sql));

        }
        if (projectsDataHandlerAction.getParentProjectID() != null && !projectsDataHandlerAction.getParentProjectID().equals("")) {
            query.add(Restrictions.eq("parentProjectID", projectsDataHandlerAction.getParentProjectID()));

        }

        if (projectsDataHandlerAction.getProjectType() != null && projectsDataHandlerAction.getProjectType().equals("MP")) {
            query.add(Restrictions.eq("projectType", "MP"));

        } else {
            query.add(Restrictions.eq("projectType", "SP"));

        }

        if (projectsDataHandlerAction.getProjectType().equals("MP") && !projectsDataHandlerAction.getRoleValue().equalsIgnoreCase("Director")) {

            query.add(Restrictions.eq("projectCreatedBy", Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString())));
        }

        projectsList = query.list();

        try {

            session.flush();
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectSearchDetails Method End*********************");
        return projectsList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectsByAccID() method is used to get state names of a
     * particular country.
     *
     * *****************************************************************************
     */
    public List getProjectsByAccID(Integer accID, Integer projectCreatedBy, String roleValue) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectsByAccID Method Start*********************");
        List<ProjectsVTO> projectsList = new ArrayList<ProjectsVTO>();

        List<Org_RelVTO> childAccountsList = new ArrayList<Org_RelVTO>();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.eq("accountID", accID));
        query.add(Restrictions.eq("projectType", "MP"));
        if (!roleValue.equalsIgnoreCase("Director")) {
            query.add(Restrictions.eq("projectCreatedBy", projectCreatedBy));
        }

        projectsList = query.list();

        try {
            // Closing hibernate session
            session.flush();
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectsByAccID Method End*********************");
        return projectsList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : addProject() method is used to
     *
     * *****************************************************************************
     */
    public void addProject(ProjectsVTO projects) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: addProject Method Start*********************");
        Session session = HibernateServiceLocator.getInstance().getSession();
        //Creating a transaction for the session object.
        Transaction tran = session.beginTransaction();

        session.save(projects);
        tran.commit();
        if (tran.wasCommitted()) {
            System.out.println("Project Details Inserted Successfully");
        }
        session.close();
        System.out.println("********************ProjectsDataHandlerServiceImpl :: addProject Method End*********************");
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkProjectName() method is used to
     *
     * *****************************************************************************
     */
    public String checkProjectName(String projectName, String projectFlag, int projectId, int accountID) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: checkProjectName Method Start*********************");
        String nameFound = "false";
        List<String> projectNames = new ArrayList<String>();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.like("accountID", accountID));
        query.add(Restrictions.like("projectName", projectName));

        query.setProjection(Projections.property("projectName"));
        projectNames = query.list();

        if (projectNames.size() != 0) {
            nameFound = "true";
        }
        if (projectNames.size() == 0 || projectNames.isEmpty()) {
            nameFound = "false";
        }

        try {

            session.flush();
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: checkProjectName Method End*********************");
        return nameFound;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectsByProjectID() method is used to
     *
     * *****************************************************************************
     */
    public ProjectsVTO getProjectsByProjectID(ProjectsDataHandlerAction projectsDataHandlerAction) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectsByProjectID Method Start*********************");

        ProjectsVTO projects = new ProjectsVTO();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.like("accountID", projectsDataHandlerAction.getAccountID()));

        query.add(Restrictions.like("projectID", projectsDataHandlerAction.getProjectID()));
        projects = (ProjectsVTO) query.uniqueResult();

        if (projects.getProjectType().equals("SP")) {
            Criteria getParentNameQuery = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);
            getParentNameQuery.add(Restrictions.eq("projectID", projects.getParentProjectID()));
            getParentNameQuery.setProjection(Projections.property("projectName"));
            projectsDataHandlerAction.setParentProjectName(getParentNameQuery.uniqueResult().toString());
        }

        try {

            session.flush();
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectsByProjectID Method End*********************");
        return projects;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectResourceCount() method is used to
     *
     * *****************************************************************************
     */
    public Integer getProjectResourceCount(ProjectsDataHandlerAction projectsDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectResourceCount Method Start*********************");

        Integer counter;

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.UsersOrganizationsVTO.class);

        query.add(Restrictions.like("organizationID", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID)));

        query.add(Restrictions.like("projectID", projectsDataHandlerAction.getProjectID()));

        counter = query.list().size();
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectResourceCount Method End*********************");
        return counter;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : updateProject() method is used to
     *
     * *****************************************************************************
     */
    public void updateProject(ProjectsVTO project, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: updateProject Method Start*********************");
        Connection connection = null;
        CallableStatement callableStatement = null;
        httpServletRequest.getSession(false).setAttribute("projectID", project.getProjectID());
        boolean isExceute = false;
        String result = "";
        try {
            System.out.println("project.getProjectType()-->" + project.getProjectType());
            System.out.println("project.getProjectID()--->" + project.getProjectID());
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL spProjectDetailsUpdate(?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("updateProject :: procedure name : spProjectDetailsUpdate ");
            callableStatement.setInt(1, project.getProjectID());
            callableStatement.setString(2, project.getProjectType());
            callableStatement.setString(3, project.getProjectName());
            callableStatement.setString(4, project.getProjectStartDate());
            callableStatement.setString(5, project.getProjectTargetDate());
            callableStatement.setString(6, project.getProject_description());
            callableStatement.setString(7, project.getProject_status());
            callableStatement.setDouble(8, project.getProjectTargetHrs());
            callableStatement.setString(9, project.getCostCenterName());
            callableStatement.setString(10, project.getProjectReqSkillSet());

            callableStatement.registerOutParameter(11, Types.VARCHAR);
            isExceute = callableStatement.execute();
            result = callableStatement.getString(11);

            if ("1".equals(result)) {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: updateProject Method End*********************");

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getSubProjects() method is used to
     *
     *
     * *****************************************************************************
     */
    public List getSubProjects(Integer projectID) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getSubProjects Method Start*********************");

        List<ProjectsVTO> subProjectsList = new ArrayList<ProjectsVTO>();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.like("parentProjectID", projectID));

        subProjectsList = query.list();

        try {

            session.flush();
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getSubProjects Method End*********************");

        return subProjectsList;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectsDashboard() method is used to
     *
     *
     * *****************************************************************************
     */
    public List getProjectsDashboard(ProjectsDataHandlerAction projectsDataHandlerAction) throws ServiceLocatorException {
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectsDashboard Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        List<ProjectsVTO> projectDashBoardList = new ArrayList();

        try {
            queryString = "SELECT project_id,acc_id,proj_name,proj_type,targethrs,workedhrs FROM acc_projects"
                    + " WHERE  proj_type='MP' AND proj_status='Active' "
                    + " AND (EXTRACT(YEAR FROM proj_stdate) = " + projectsDataHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM proj_trdate) =" + projectsDataHandlerAction.getDashBoardYear() + " )"
                    + " AND acc_id= " + projectsDataHandlerAction.getSessionOrgId() + " ";

            System.out.println("getProjectsDashboard::queryString-------->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ProjectsVTO projectsVTO = new ProjectsVTO();
                projectsVTO.setProjectName(resultSet.getString("proj_name"));
                projectsVTO.setProjectTargetHrs(resultSet.getDouble("targethrs"));
                projectsVTO.setProjectWorkedHrs(resultSet.getDouble("workedhrs"));

                projectDashBoardList.add(projectsVTO);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************ProjectsDataHandlerServiceImpl :: getProjectsDashboard Method End*********************");
        return projectDashBoardList;
    }
}
