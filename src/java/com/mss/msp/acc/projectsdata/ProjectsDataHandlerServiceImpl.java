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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List getProjectSearchDetails(ProjectsDataHandlerAction projectsDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> getProjectSearchDetails ::::::::::::::::::");
          DateUtility dateUtility = new DateUtility();
        List<ProjectsVTO> projectsList = new ArrayList<ProjectsVTO>();
        String sql;

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.eq("accountID", projectsDataHandlerAction.getAccountID()));

        System.out.println("account id is " + projectsDataHandlerAction.getAccountID());

        if (projectsDataHandlerAction.getProjectReqSkillSet() != null && !projectsDataHandlerAction.getProjectReqSkillSet().equals("")) {
            sql = " proj_req_skillset like '%" + projectsDataHandlerAction.getProjectReqSkillSet().toString().trim() + "%'";
            System.out.println("The Req skill set is: " + projectsDataHandlerAction.getProjectReqSkillSet());
            query.add(Restrictions.sqlRestriction(sql));
        }
        if (!projectsDataHandlerAction.getProjectName().equals("") && projectsDataHandlerAction.getProjectName() != null) {
            query.add(Restrictions.ilike("projectName", projectsDataHandlerAction.getProjectName().toString().trim(), MatchMode.ANYWHERE));
            System.out.println("The project name is: " + projectsDataHandlerAction.getProjectName());
        }
        if (!"".equals(projectsDataHandlerAction.getProjectStartDate()) && projectsDataHandlerAction.getProjectStartDate() != null) {
            String startDate = dateUtility.getInstance().convertStringToMySQLDateInDash(projectsDataHandlerAction.getProjectStartDate());
             sql = " proj_stdate >= '" + startDate.trim() + "'";
            query.add(Restrictions.sqlRestriction(sql));
            System.out.println("The projects Start date is: " + projectsDataHandlerAction.getProjectStartDate());
        }
        if (!"".equals(projectsDataHandlerAction.getProjectTargetDate()) && projectsDataHandlerAction.getProjectTargetDate() != null) {
            String targetDate = dateUtility.getInstance().convertStringToMySQLDateInDash(projectsDataHandlerAction.getProjectTargetDate());
            sql = " proj_stdate <= '" + targetDate.trim() + "'";
            query.add(Restrictions.sqlRestriction(sql));
            System.out.println("the project target date is: " + projectsDataHandlerAction.getProjectTargetDate());
        }
        if (projectsDataHandlerAction.getParentProjectID() != null && !projectsDataHandlerAction.getParentProjectID().equals("")) {
            query.add(Restrictions.eq("parentProjectID", projectsDataHandlerAction.getParentProjectID()));
            System.out.println("parent project id is " + projectsDataHandlerAction.getParentProjectID());
        }

        if (projectsDataHandlerAction.getProjectType() != null && projectsDataHandlerAction.getProjectType().equals("MP")) {
            query.add(Restrictions.eq("projectType", "MP"));
            System.out.println("the project type is: MP");
        } else {
            query.add(Restrictions.eq("projectType", "SP"));
            System.out.println("project type is SP");
        }

        if (projectsDataHandlerAction.getProjectType().equals("MP") && !projectsDataHandlerAction.getRoleValue().equalsIgnoreCase("Director")) {
            System.err.println("session usr id --->"+httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString());
            query.add(Restrictions.eq("projectCreatedBy", Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString())));
        }
        
        System.err.println("query-->"+query.toString());
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

        return projectsList;
    }

    public List getProjectsByAccID(Integer accID, Integer projectCreatedBy, String roleValue) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> getProjectsByAccID ::::::::::::::::::");
        System.out.println(">>>>>>>>>>>>>>>>>>>>in impl userID:::::" + projectCreatedBy);
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

        return projectsList;
    }

    public void addProject(ProjectsVTO projects) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> addProject ::::::::::::::::::");

        Session session = HibernateServiceLocator.getInstance().getSession();
        //Creating a transaction for the session object.
        Transaction tran = session.beginTransaction();

        session.save(projects);
        tran.commit();
        if (tran.wasCommitted()) {
            System.out.println("Project Details Inserted Successfully");
        }
        session.close();

    }

    public String checkProjectName(String projectName,String projectFlag,int projectId,int accountID) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> checkProjectName ::::::::::::::::::");

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

        System.out.println(projectNames.toString().toUpperCase());

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

        return nameFound;
    }

    public ProjectsVTO getProjectsByProjectID(ProjectsDataHandlerAction projectsDataHandlerAction) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> getProjectsByProjectID ::::::::::::::::::");

        ProjectsVTO projects = new ProjectsVTO();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.like("accountID", projectsDataHandlerAction.getAccountID()));
        System.out.println("accountID is " + projectsDataHandlerAction.getAccountID());

        query.add(Restrictions.like("projectID", projectsDataHandlerAction.getProjectID()));
        System.out.println("projectID is " + projectsDataHandlerAction.getProjectID());


        projects = (ProjectsVTO) query.uniqueResult();

        if (projects.getProjectType().equals("SP")) {
            Criteria getParentNameQuery = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);
            getParentNameQuery.add(Restrictions.eq("projectID", projects.getParentProjectID()));
            getParentNameQuery.setProjection(Projections.property("projectName"));
            projectsDataHandlerAction.setParentProjectName(getParentNameQuery.uniqueResult().toString());
        }

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

        return projects;
    }

    public Integer getProjectResourceCount(ProjectsDataHandlerAction projectsDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> getProjectResourceCount ::::::::::::::::::");

        Integer counter;

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.UsersOrganizationsVTO.class);

        query.add(Restrictions.like("organizationID", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID)));

        query.add(Restrictions.like("projectID", projectsDataHandlerAction.getProjectID()));

        counter = query.list().size();

        return counter;
    }

    public void updateProject(ProjectsVTO project, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> updateProject ::::::::::::::::::");

        System.out.println("The project object is: " + project.toString());

        //Setting the projectID in the session to be passed onto getProjectDetails method
        httpServletRequest.getSession(false).setAttribute("projectID", project.getProjectID());

        Session session = HibernateServiceLocator.getInstance().getSession();

        //Creating a transaction for the session object.
        Transaction tran = session.beginTransaction();

        session.update(project);
        tran.commit();

        if (tran.wasCommitted()) {
            System.out.println("Project Details Updated Successfully");
        }
        session.close();

    }

    public List getSubProjects(Integer projectID) throws ServiceLocatorException {
        System.out.println(":::::::::::::: ProjectsDataHandlerServiceImpl ==> getSubProjects ::::::::::::::::::");

        System.out.println("In getSubProjects impl");
        List<ProjectsVTO> subProjectsList = new ArrayList<ProjectsVTO>();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.acc.projectsdata.ProjectsVTO.class);

        query.add(Restrictions.like("parentProjectID", projectID));

        subProjectsList = query.list();

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
        System.out.println("In getSubProjects impl and returning the list");
        return subProjectsList;
    }
    public List getProjectsDashboard(ProjectsDataHandlerAction projectsDataHandlerAction) throws ServiceLocatorException {
        List<ProjectsVTO> projectDashBoardList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            queryString = "SELECT project_id,acc_id,proj_name,proj_type,targethrs,workedhrs FROM acc_projects"
                    + " WHERE  proj_type='MP' AND proj_status='Active' "
                    + " AND (EXTRACT(YEAR FROM proj_stdate) = " + projectsDataHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM proj_trdate) =" + projectsDataHandlerAction.getDashBoardYear() + " )"
                    + " AND acc_id= " + projectsDataHandlerAction.getSessionOrgId() + " ";
            
            //    + " GROUP BY account_id ORDER BY budgetAmount DESC LIMIT 0,10";
            // queryString += " AND (EXTRACT(YEAR FROM startdate) = " + costCenterAjaxHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM enddate) =" + costCenterAjaxHandlerAction.getDashBoardYear() + " )";
            //   queryString = "SELECT * FROM costcenterbudgets WHERE cccode like '"+costCenterAction.getCcCode()+"' AND(startdate LIKE '%" + year + "%' OR enddate LIKE '%" + year + "%')";
            
            System.out.println("queryString getProjectsDashboard() -->" + queryString);
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
        return projectDashBoardList;
    }
}
