/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.budget;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class BudgetServiceImpl implements BudgetService {

    private static Logger log = Logger.getLogger(BudgetAction.class);
    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /**
     * *************************************
     *
     * @getProjectBudgetDetails() is used to Pro get Project Budget Details
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public List getProjectBudgetDetails(BudgetAction budgetAction) throws ServiceLocatorException {
        String queryString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<BudgetVTO> projectBudgetList = new ArrayList<BudgetVTO>();
        try {
            log.info("************Entered to The BudgetServiceImpl ::: getProjectBudgetDetails***********");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            connection = ConnectionProvider.getInstance().getConnection();
            if (budgetAction.getRoleValue().equalsIgnoreCase("Director")) {
                queryString = "SELECT c.ccname,p.cccode,b.id,p.proj_name,p.proj_type,b.estbugetamt,b.balbudgetamt,b.STATUS,b.qutindetifier,b.YEAR,b.description "
                        + "FROM prjbudget b "
                        + "LEFT OUTER JOIN acc_projects p ON(p.project_id=b.prjid) "
                        + "LEFT OUTER JOIN costcenter c ON(c.cccode=p.cccode) "
                        + "WHERE 1=1 AND b.STATUS IN('Submitted','Approved','Rejected') AND b.YEAR = " + year + " AND p.acc_id=" + budgetAction.getSessionOrgId();
            } else {
                queryString = "SELECT c.ccname,p.cccode,b.id,p.proj_name,p.proj_type,b.estbugetamt,b.balbudgetamt,b.STATUS,b.qutindetifier,b.YEAR,b.description "
                        + "FROM prjbudget b "
                        + "LEFT OUTER JOIN acc_projects p ON(p.project_id=b.prjid) "
                        + "LEFT OUTER JOIN costcenter c ON(c.cccode=p.cccode) "
                        + "WHERE p.created_by=" + budgetAction.getUserSessionId() + "  AND b.YEAR = " + year + " AND p.acc_id=" + budgetAction.getSessionOrgId();
            }
            log.info("BudgetServiceImpl ::: getProjectBudgetDetails query......>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                BudgetVTO budgetVTO = new BudgetVTO();
                budgetVTO.setId(resultSet.getInt("id"));
                budgetVTO.setProjectName(resultSet.getString("proj_name"));
                budgetVTO.setEstimatedBudget(resultSet.getString("estbugetamt"));
                budgetVTO.setRemainingBudget(resultSet.getString("balbudgetamt"));
                budgetVTO.setQuarterId(resultSet.getString("qutindetifier"));
                budgetVTO.setYear(resultSet.getString("YEAR"));
                budgetVTO.setStatus(resultSet.getString("STATUS"));
                budgetVTO.setComments(resultSet.getString("description"));
                budgetVTO.setProjectType(resultSet.getString("proj_type"));
                budgetVTO.setCcName(resultSet.getString("ccname"));

                projectBudgetList.add(budgetVTO);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetDetails()" + ex.toString());
            }
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
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetDetails()" + sqle.toString());
                }
            }
        }
        return projectBudgetList;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetSearchResults() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String getProjectBudgetSearchResults(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            log.info("************Entered to The BudgetServiceImpl ::: getProjectBudgetSearchResults***********");
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "";
            if (budgetAction.getRoleValue().equalsIgnoreCase("Director")) {
                queryString = "SELECT c.ccname,p.cccode,b.id,p.project_id,p.proj_type,p.proj_name,b.estbugetamt,b.balbudgetamt,b.STATUS,b.qutindetifier,b.YEAR,b.description "
                        + "FROM prjbudget b "
                        + "LEFT OUTER JOIN acc_projects p ON(p.project_id=b.prjid) "
                        + "LEFT OUTER JOIN costcenter c ON(c.cccode=p.cccode) "
                        + "WHERE 1=1 AND b.STATUS IN('Submitted','Approved','Rejected') AND p.acc_id=" + budgetAction.getSessionOrgId();
            } else {
                queryString = "SELECT c.ccname,p.cccode,b.id,p.project_id,p.proj_type,p.proj_name,b.estbugetamt,b.balbudgetamt,b.STATUS,b.qutindetifier,b.YEAR,b.description "
                        + "FROM prjbudget b "
                        + "LEFT OUTER JOIN acc_projects p ON(p.project_id=b.prjid) "
                        + "LEFT OUTER JOIN costcenter c ON(c.cccode=p.cccode) "
                        + "WHERE p.created_by=" + budgetAction.getUserSessionId() + " AND p.acc_id=" + budgetAction.getSessionOrgId();
            }

            if (budgetAction.getYear() != 0) {
                queryString = queryString + " AND b.YEAR=" + budgetAction.getYear() + "  ";
            }
            if (!"-1".equalsIgnoreCase(budgetAction.getStatus())) {
                queryString = queryString + " AND b.STATUS = '" + budgetAction.getStatus() + "'  ";
            }
            if (!"-1".equalsIgnoreCase(budgetAction.getQuarterId())) {
                queryString = queryString + " AND b.qutindetifier='" + budgetAction.getQuarterId() + "'  ";
            }
            if (!"-1".equalsIgnoreCase(budgetAction.getProject())) {
                queryString = queryString + " AND p.project_id='" + budgetAction.getProject() + "'  ";
            }

            log.info("BudgetServiceImpl ::: getProjectBudgetSearchResults query......>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("project_id") + "|"
                        + resultSet.getString("proj_name") + "|"
                        + resultSet.getString("estbugetamt") + "|"
                        + resultSet.getString("balbudgetamt") + "|"
                        + resultSet.getString("qutindetifier") + "|"
                        + resultSet.getString("YEAR") + "|"
                        + resultSet.getString("STATUS") + "|"
                        + resultSet.getString("description") + "|"
                        + resultSet.getString("id") + "|"
                        + resultSet.getString("ccname") + "|"
                        + resultSet.getString("proj_type") + "^";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetSearchResults()" + ex.toString());
            }
        } catch (NullPointerException ex) {
            resultString = "";
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetSearchResults()" + ex.toString());
            }
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
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetSearchResults()" + sqle.toString());
                }
            }
        }
        return resultString;
    }

    /**
     * *************************************
     *
     * @saveProjectBudgetDetails() is for save Project Budget Details
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String saveProjectBudgetDetails(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        String status = "";
        String Result = "";
        Connection connection = null;
        CallableStatement callableStatement = null;
        boolean isExceute = false;
        try {
            log.info("************Entered to The BudgetServiceImpl ::: saveProjectBudgetDetails***********");
            connection = ConnectionProvider.getInstance().getConnection();
            if (budgetAction.getFlag().equalsIgnoreCase("S")) {
                status = "Entered";
            } else {
                status = "Submitted";
            }
            callableStatement = connection.prepareCall("{CALL addProjectBudgetDetails(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, budgetAction.getUserSessionId());
            callableStatement.setInt(2, Integer.parseInt(budgetAction.getProject()));
            callableStatement.setDouble(3, Double.parseDouble(budgetAction.getEstimateBudget()));
            callableStatement.setString(4, budgetAction.getQuarterId());
            callableStatement.setInt(5, budgetAction.getYear());
            callableStatement.setString(6, budgetAction.getComments());
            callableStatement.setString(7, status);
            callableStatement.setString(8, budgetAction.getAddEditFlag());
            callableStatement.registerOutParameter(9, Types.VARCHAR);
            isExceute = callableStatement.execute();
            Result = callableStatement.getString(9);
            if (Result.equalsIgnoreCase("Exist")) {
                log.info("******************BudgetServiceImpl ::: saveProjectBudgetDetails::::Procedure Result:::::" + Result);
            } else if (Result.equalsIgnoreCase("Updated")) {
                log.info("******************BudgetServiceImpl ::: saveProjectBudgetDetails::::Procedure Result::::" + Result);
            } else if (Result.equalsIgnoreCase("Added")) {
                log.info("****************** BudgetServiceImpl ::: saveProjectBudgetDetails::::Procedure Result:::::" + Result);
            } else {
                log.info("****************** BudgetServiceImpl ::: saveProjectBudgetDetails:::::Procedure Result::::" + Result);
            }
            log.info("************End of The BudgetServiceImpl ::: saveProjectBudgetDetails***********");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
        return Result;
    }

    /**
     * *************************************
     *
     * @getProjectBudgetDetailsToViewOnOverlay() is for get Project Budget
     * Details Overlay
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String getProjectBudgetDetailsToViewOnOverlay(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            log.info("************Entered into BudgetServiceImpl ::: getProjectBudgetDetailsToViewOnOverlay***********");
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT id,prjid,estbugetamt,balbudgetamt,description,STATUS,qutindetifier,YEAR,approve_rej_comments "
                    + "FROM prjbudget WHERE id=" + budgetAction.getBudgetId();

            log.info("****BudgetServiceImpl ::: getProjectBudgetDetailsToViewOnOverlay***Query**" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("id") + "|"
                        + resultSet.getString("prjid") + "|"
                        + resultSet.getString("estbugetamt") + "|"
                        + resultSet.getString("balbudgetamt") + "|"
                        + resultSet.getString("qutindetifier") + "|"
                        + resultSet.getString("YEAR") + "|"
                        + resultSet.getString("STATUS") + "|"
                        + resultSet.getString("description") + "|"
                        + resultSet.getString("approve_rej_comments") + "^";
            }
            log.info("************End of BudgetServiceImpl ::: getProjectBudgetDetailsToViewOnOverlay***********");
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetDetailsToViewOnOverlay()" + ex.toString());
            }
        } catch (NullPointerException ex) {
            resultString = "";
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetDetailsToViewOnOverlay()" + ex.toString());
            }
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
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetServiceImpl:: getProjectBudgetDetailsToViewOnOverlay()" + sqle.toString());
                }
            }
        }
        return resultString;
    }

    /**
     * *************************************
     *
     * @getDefaultRequirementDashBoardDetails() update status in requirement
     * table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String doBudgetRecordDelete(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        int res = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            log.info("************Entered into BudgetServiceImpl ::: doBudgetRecordDelete***********");
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "DELETE FROM prjbudget WHERE id=" + budgetAction.getBudgetId();
            log.info("************BudgetServiceImpl ::: doBudgetRecordDelete*****Query******" + queryString);
            statement = connection.createStatement();
            res = statement.executeUpdate(queryString);
            if (res > 0) {
                resultString = "Success";
            } else {
                resultString = "Fail";
            }
            log.info("************End of BudgetServiceImpl ::: doBudgetRecordDelete***********");
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: doBudgetRecordDelete()" + ex.toString());
            }
        } catch (NullPointerException ex) {
            resultString = "";
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: doBudgetRecordDelete()" + ex.toString());
            }
        } finally {
            try {
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
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetServiceImpl:: doBudgetRecordDelete()" + sqle.toString());
                }
            }
        }
        return resultString;
    }

    /**
     * *************************************
     *
     * @setDirectorResultForBudget() is for set Director Result For Budget
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String setDirectorResultForBudget(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString1 = "";
        String queryString = "";
        int res = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            log.info("************Entered to The BudgetServiceImpl ::: setDirectorResultForBudget***********");
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "UPDATE prjbudget SET "
                    + "STATUS='" + budgetAction.getFlag() + "', "
                    + "balbudgetamt='" + budgetAction.getEstimateBudget() + "', "
                    + "estbugetamt='" + budgetAction.getEstimateBudget() + "', "
                    + "approve_rej_comments='" + budgetAction.getApproveComments() + "' "
                    + "WHERE prjid=" + budgetAction.getProject() + " "
                    + "AND qutindetifier='" + budgetAction.getQuarterId() + "' "
                    + "AND YEAR=" + budgetAction.getYear();
            statement = connection.createStatement();
            res = statement.executeUpdate(queryString);
            if (res > 0) {
                if ("Approved".equals(budgetAction.getFlag())) {
                    queryString1 = "UPDATE costcenterbudgets SET "
                            + "spentamt= (spentamt) +(" + budgetAction.getEstimateBudget() + ") "
                            + "WHERE cccode='" + budgetAction.getCostCenterCode() + "' "
                            + "AND Quarter='" + budgetAction.getQuarterId() + "' "
                            + "AND YEAR=" + budgetAction.getYear();
                    System.out.println("--------------------" + queryString1);
                    int res1 = statement.executeUpdate(queryString1);
                }
                resultString = "DirectorStatusUpdated";
            } else {
                resultString = "DirectorStatusFail";
            }
            log.info("************BudgetServiceImpl ::: setDirectorResultForBudget******Query1*****" + queryString1);
            log.info("************BudgetServiceImpl ::: setDirectorResultForBudget******Query*****" + queryString);
            log.info("************End of The BudgetServiceImpl ::: setDirectorResultForBudget***********");
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: setDirectorResultForBudget()" + ex.toString());
            }
        } catch (NullPointerException ex) {
            resultString = "";
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: setDirectorResultForBudget()" + ex.toString());
            }
        } finally {
            try {
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
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetServiceImpl:: setDirectorResultForBudget()" + sqle.toString());
                }
            }
        }
        return resultString;
    }

    /**
     * *************************************
     *
     * @getCostCentertDetailsByProjectId() is for getting CostCentert Details By
     * ProjectId
     *
     * @Author:Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:October 14,2015
     *
     **************************************
     */
    public String getCostCentertDetailsByProjectId(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString1 = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            log.info("************Entered into The BudgetServiceImpl ::: getCostCentertDetailsByProjectId***********");
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString1 = "SELECT ap.cccode,ccname,budgetamt,spentamt,balance,year,Quarter,status FROM costcenter cc LEFT OUTER JOIN acc_projects ap ON(cc.cccode=ap.cccode) LEFT OUTER JOIN costcenterbudgets cb ON(cc.cccode=cb.cccode) WHERE project_id =" + budgetAction.getProjectId() + " and cb.ccbstatus = 'Active'";
            resultSet = statement.executeQuery(queryString1);
            while (resultSet.next()) {
                resultString += resultSet.getString("ccname") + "|" + resultSet.getDouble("budgetamt") + "|" + resultSet.getDouble("spentamt") + "|" + resultSet.getDouble("balance") + "|" + resultSet.getString("cccode") + "|" + resultSet.getString("year") + "|" + resultSet.getString("Quarter") + "|" + resultSet.getString("status") + "^";
            }
            if ("".equals(resultString) || resultString == null) {
                queryString1 = "SELECT ccname FROM costcenter cc LEFT OUTER JOIN acc_projects ap ON(cc.cccode=ap.cccode) WHERE project_id =" + budgetAction.getProjectId();
                resultSet = statement.executeQuery(queryString1);
                while (resultSet.next()) {
                    resultString += resultSet.getString("ccname") + "|" + "CCN";
                }
            }
            log.info("************BudgetServiceImpl ::: getCostCentertDetailsByProjectId******Query*****" + queryString1);
            log.info("************End of The BudgetServiceImpl ::: getCostCentertDetailsByProjectId***********");
        } catch (Exception ex) {
            ex.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("Exception is :: BudgetServiceImpl:: getCostCentertDetailsByProjectId()" + ex.toString());
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                if (log.isDebugEnabled()) {
                    log.error("Exception is :: BudgetServiceImpl:: getCostCentertDetailsByProjectId()" + sqle.toString());
                }
            }
        }
        return resultString;
    }
}
