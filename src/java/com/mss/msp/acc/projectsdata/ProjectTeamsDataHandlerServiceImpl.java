/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Senay
 */
public class ProjectTeamsDataHandlerServiceImpl implements ProjectTeamsDataHandlerService {

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
     * ForUse : getProjectsTeam() method is used to
     *
     *
     * *****************************************************************************
     */
    public List getProjectsTeam(Integer projectID) throws ServiceLocatorException {
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getProjectsTeam Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        DataSourceDataProvider dataProvider = DataSourceDataProvider.getInstance();
        List<ProjectTeamsVTO> projectsTeamsList = new ArrayList<ProjectTeamsVTO>();
        int i = 0;

        try {
            queryString = "select t.role_name,teamTable.usr_id,teamTable.project_id,teamTable.current_status,teamTable.reportsto1,teamTable.reportsto2,userTable.first_name,userTable.middle_name,userTable.last_name,acp.proj_status from Project_team as teamTable"
                    + " inner join users as userTable"
                    + " inner join roles as t "
                    + " on t.role_id = teamTable.designation "
                    + " and teamTable.usr_id = userTable.usr_id "
                    + " LEFT OUTER JOIN acc_projects AS acp ON(teamTable.project_id=acp.project_id)"
                    + " where teamTable.project_id=" + projectID
                    + " group by teamTable.usr_id";

            System.out.println("getProjectsTeam::queryString------->" + queryString);

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();

            String reportsToName;
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                ProjectTeamsVTO team = new ProjectTeamsVTO();
                team.setProjectID(resultSet.getInt("teamTable.project_id"));
                team.setStatus(resultSet.getString("teamTable.current_status"));

                reportsToName = dataProvider.getFnameandLnamebyUserid(resultSet.getInt("teamTable.reportsto1"));
                team.setReportsTo1Name(reportsToName);
                reportsToName = dataProvider.getFnameandLnamebyUserid(resultSet.getInt("teamTable.reportsto2"));
                team.setReportsTo2Name(reportsToName);
                team.setFirstName(resultSet.getString("userTable.first_name"));
                team.setMiddleName(resultSet.getString("userTable.middle_name"));
                team.setLastName(resultSet.getString("userTable.last_name"));
                team.setDesignation(resultSet.getString("t.role_name"));
                team.setUserID(resultSet.getInt("teamTable.usr_id"));
                team.setMainProjectStatus(resultSet.getString("acp.proj_status"));
                projectsTeamsList.add(team);
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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

        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getProjectsTeam Method End*********************");
        return projectsTeamsList;

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
    public String getTeamMemberDetails(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException {

        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getTeamMemberDetails Method Start*********************");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        DataSourceDataProvider dataProvider = DataSourceDataProvider.getInstance();

        String resultString = "";

        try {

            queryString = "SELECT r.role_name,teamTable.project_id,teamTable.usr_id,teamTable.current_status,teamTable.reportsto1,teamTable.reportsto2,"
                    + "userTable.first_name,userTable.last_name,acp.proj_status  FROM Project_team AS teamTable LEFT OUTER JOIN users AS userTable ON (teamTable.usr_id = userTable.usr_id) LEFT OUTER JOIN roles AS r "
                    + " ON (r.role_id = teamTable.designation) "
                    + " LEFT OUTER JOIN acc_projects AS acp ON(teamTable.project_id=acp.project_id)"
                    + " AND teamTable.usr_id = userTable.usr_id WHERE 1=1 ";

            if (projectTeamsDataHandlerAction.getTeamMemberName() != null && projectTeamsDataHandlerAction.getTeamMemberName().toString().isEmpty() == false) {
                queryString = queryString + " and (userTable.last_name LIKE '" + projectTeamsDataHandlerAction.getTeamMemberName() + "%' OR userTable.first_name LIKE '" + projectTeamsDataHandlerAction.getTeamMemberName() + "%')";
            }
            if (!"DF".equalsIgnoreCase(projectTeamsDataHandlerAction.getStatus())) {

                queryString = queryString + " and teamTable.current_status= '" + projectTeamsDataHandlerAction.getStatus() + "' ";
            }
            queryString = queryString + "and teamTable.project_id=" + projectTeamsDataHandlerAction.getProjectID();
            System.out.println("getTeamMemberDetails::query String->" + queryString);

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("userTable.first_name") + "." + resultSet.getString("userTable.last_name") + "|" + resultSet.getString("r.role_name") + "|" + resultSet.getString("teamTable.current_status") + "|" + dataProvider.getFnameandLnamebyUserid(resultSet.getInt("teamTable.reportsto1")) + '|' + dataProvider.getFnameandLnamebyUserid(resultSet.getInt("teamTable.reportsto2")) + '|' + resultSet.getInt("teamTable.project_id") + '|' + resultSet.getInt("teamTable.usr_id") + '|' + resultSet.getString("acp.proj_status") + '^';
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
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
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getTeamMemberDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : showResourceDetails() method is used
     *
     *
     * *****************************************************************************
     */
    public String showResourceDetails(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException {
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: showResourceDetails Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";

        try {
            queryString = "SELECT DISTINCT(sp.usr_id) AS id,CONCAT(first_name,'.',last_name) AS NAMES, ur.role_id,role_Name"
                    + " FROM prj_sub_prjteam sp LEFT OUTER JOIN project_team pt ON (sp.usr_id=pt.usr_id)"
                    + " LEFT OUTER JOIN usr_roles ur ON (sp.usr_id=ur.usr_id) LEFT OUTER JOIN users u ON "
                    + "(sp.usr_id=u.usr_id) LEFT OUTER JOIN roles r ON (ur.role_id=r.role_id) "
                    + "WHERE sp.current_status='Active' AND ur.primary_flag=1 and sub_project_id=" + projectTeamsDataHandlerAction.getProjectID() + " AND pt.project_id=" + projectTeamsDataHandlerAction.getPpid();
            System.out.println("showResourceDetails::queryString" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("Names") + "|" + resultSet.getString("role_Name") + "^";
            }
        } catch (Exception sqe) {
            sqe.printStackTrace();
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
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: showResourceDetails Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : EmpReleasefromProject() method is used to
     *
     *
     * *****************************************************************************
     */
    public int EmpReleasefromProject(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException {

        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: EmpReleasefromProject Method Start*********************");
        int resultString = 0;
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            callableStatement = connection.prepareCall("{call spEmpReleasefromProject(?,?,?,?)}");
            System.out.println("EmpReleasefromProject :: procedure name : spEmpReleasefromProject ");
            callableStatement.setInt(1, projectTeamsDataHandlerAction.getProjectID());
            callableStatement.setInt(2, projectTeamsDataHandlerAction.getUserID());
            callableStatement.setInt(3, projectTeamsDataHandlerAction.getAccountID());
            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            callableStatement.execute();
            resultString = callableStatement.getInt(4);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: EmpReleasefromProject Method End*********************");
            return resultString;
        }
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectDashboardList() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getProjectDashboardList(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException {
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getProjectDashboardList Method Start*********************");
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            if ("yearmainprojects".equalsIgnoreCase(projectTeamsDataHandlerAction.getProjectsFlag())) {
                queryString = "SELECT project_id,acc_id,proj_name,proj_type,targethrs,workedhrs FROM acc_projects"
                        + " WHERE  proj_type='MP' AND proj_status='Active' "
                        + " AND acc_id= " + projectTeamsDataHandlerAction.getSessionOrgId() + " ";
                if (projectTeamsDataHandlerAction.getDashBoardYear() != 0) {
                    queryString += " AND (EXTRACT(YEAR FROM proj_stdate) = " + projectTeamsDataHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM proj_trdate) =" + projectTeamsDataHandlerAction.getDashBoardYear() + " )";
                }
                System.out.println("getProjectDashboardList::queryString----------->" + queryString);
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                int counter = 0;
                while (resultSet.next()) {
                    counter = counter + 1;
                    resultString += resultSet.getString("proj_name") + "|" + resultSet.getString("targethrs") + "|" + resultSet.getString("workedhrs") + "|" + resultSet.getString("project_id") + "|" + resultSet.getString("proj_type") + "|" + "mainprojects" + "^";
                }
                if (counter == 0) {
                    resultString = "nomainprojects";
                }
            } else if ("mainprojects".equalsIgnoreCase(projectTeamsDataHandlerAction.getProjectsFlag())) {

                queryString = "SELECT project_id,proj_name,acc_id,proj_type,proj_status,targethrs,workedhrs FROM acc_projects "
                        + " WHERE proj_status='Active' AND parent_project_id=" + projectTeamsDataHandlerAction.getMainProjectID() + " AND acc_id=" + projectTeamsDataHandlerAction.getSessionOrgId() + " ";

                if (projectTeamsDataHandlerAction.getDashBoardYear() != 0) {
                    queryString += " AND (EXTRACT(YEAR FROM proj_stdate) = " + projectTeamsDataHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM proj_trdate) =" + projectTeamsDataHandlerAction.getDashBoardYear() + " )";
                }
                System.out.println("getProjectDashboardList::queryString----------->" + queryString);
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                int counter = 0;
                while (resultSet.next()) {
                    counter = counter + 1;
                    resultString += resultSet.getString("proj_name") + "|" + resultSet.getString("targethrs") + "|" + resultSet.getString("workedhrs") + "|" + resultSet.getString("project_id") + "|" + resultSet.getString("proj_type") + "|" + "subprojects" + "^";
                }
                if (counter == 0) {
                    resultString = "nosubprojects";
                }
            } else if ("subprojects".equalsIgnoreCase(projectTeamsDataHandlerAction.getProjectsFlag())) {
                queryString = "SELECT CONCAT(first_name,'.',last_name) AS NAME,us.usr_id,"
                        + " IF((subproject1id=" + projectTeamsDataHandlerAction.getSubProjects() + "),SUM(subproject1hrs),SUM(0)) AS proj1,"
                        + " IF((subproject2id=" + projectTeamsDataHandlerAction.getSubProjects() + "),SUM(subproject2hrs),SUM(0)) AS proj2,"
                        + " IF((subproject3id=" + projectTeamsDataHandlerAction.getSubProjects() + "),SUM(subproject3hrs),SUM(0)) AS proj3,"
                        + " IF((subproject4id=" + projectTeamsDataHandlerAction.getSubProjects() + "),SUM(subproject4hrs),SUM(0)) AS proj4,"
                        + " IF((subproject5id=" + projectTeamsDataHandlerAction.getSubProjects() + "),SUM(subproject5hrs),SUM(0)) AS proj5 "
                        + " FROM usrtimesheets us JOIN usrtimesheetlines ul ON(us.timesheetid=ul.timesheetid AND us.usr_id=ul.usr_id AND reportsto1status=3)"
                        + " JOIN users um ON(us.usr_id=um.usr_id) "
                        + " WHERE us.usr_id IN(SELECT pt.usr_id FROM prj_sub_prjteam pt JOIN acc_projects ap ON(pt.project_id=ap.project_id) "
                        + " JOIN users u ON(u.usr_id=pt.usr_id) WHERE  sub_project_id=" + projectTeamsDataHandlerAction.getSubProjects() + ") "
                        + " GROUP BY us.usr_id";

                System.out.println("getProjectDashboardList::queryString----------->" + queryString);
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                int counter = 0;
                while (resultSet.next()) {
                    counter = counter + 1;
                    double consumedHours = resultSet.getDouble("proj1") + resultSet.getDouble("proj2") + resultSet.getDouble("proj3") + resultSet.getDouble("proj4") + resultSet.getDouble("proj5");
                    resultString += resultSet.getString("NAME") + "|" + consumedHours + "|" + 0 + "|" + "resourceworkedhrs" + "^"; // here 0 for representing array in javascript
                }
                if (counter == 0) {
                    resultString = "noresources";
                }
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getProjectDashboardList Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getProjectsForYear() method is used to get state names of a
     * particular country.
     *
     * *****************************************************************************
     */
    public String getProjectsForYear(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException {

        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getProjectsForYear Method Start*********************");
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        connection = ConnectionProvider.getInstance().getConnection();
        queryString = "SELECT project_id,proj_name FROM acc_projects WHERE proj_type='MP' "
                + " AND (EXTRACT(YEAR FROM proj_stdate) = " + projectTeamsDataHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM proj_trdate) =" + projectTeamsDataHandlerAction.getDashBoardYear() + " )"
                + " AND acc_id=" + projectTeamsDataHandlerAction.getSessionOrgId();
        System.out.println("getProjectsForYear::queryString---------->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getInt("project_id") + "|" + resultSet.getString("proj_name") + "^";

            }
        } catch (SQLException ex) {
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
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        System.out.println("********************ProjectTeamsDataHandlerServiceImpl :: getProjectsForYear Method End*********************");
        return resultString;

    }
}
