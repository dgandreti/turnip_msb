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

/**
 *
 * @author miracle
 */
public class BudgetServiceImpl implements BudgetService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

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
    public List getProjectBudgetDetails(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString = "";
        ArrayList<BudgetVTO> projectBudgetList = new ArrayList<BudgetVTO>();

        try {
            System.out.println("ENTERED IN TO THE IMPL FOR CUSTOMER DASHBOARD******************************************************");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            connection = ConnectionProvider.getInstance().getConnection();
            if (budgetAction.getRoleValue().equalsIgnoreCase("Director")) {
                queryString = "SELECT c.ccname,p.cccode,b.id,p.proj_name,p.proj_type,b.estbugetamt,b.balbudgetamt,b.STATUS,b.qutindetifier,b.YEAR,b.description "
                        + "FROM prjbudget b "
                        + "LEFT OUTER JOIN acc_projects p ON(p.project_id=b.prjid) "
                        + "LEFT OUTER JOIN costcenter c ON(c.cccode=p.cccode) "
                        + "WHERE 1=1 AND b.STATUS IN('Submitted','Approved','Rejected') AND b.YEAR = "+year+" AND p.acc_id=" + budgetAction.getSessionOrgId();
            } else {
                queryString = "SELECT c.ccname,p.cccode,b.id,p.proj_name,p.proj_type,b.estbugetamt,b.balbudgetamt,b.STATUS,b.qutindetifier,b.YEAR,b.description "
                        + "FROM prjbudget b "
                        + "LEFT OUTER JOIN acc_projects p ON(p.project_id=b.prjid) "
                        + "LEFT OUTER JOIN costcenter c ON(c.cccode=p.cccode) "
                        + "WHERE p.created_by=" + budgetAction.getUserSessionId() + "  AND b.YEAR = "+year+" AND p.acc_id=" + budgetAction.getSessionOrgId();
            }


            System.out.println("query...DashBoard....>" + queryString);
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
            System.out.println("result=========>" + resultString);
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
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
    public String getProjectBudgetSearchResults( BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        try {

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

            System.out.println("query...for..org id...." + queryString);
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

            System.out.println("result=========>" + resultString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * *************************************
     *
     * @saveProjectBudgetDetails() update status in requirement table
     *
     *
     * @Author:ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:06/03/2015
     *
     **************************************
     */
    public String saveProjectBudgetDetails( BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        int result = 0;
        String status = "";
        String Result = "";
        boolean isExceute = false;
        try {
            System.out.println(">>>>>>>>>>>>IN  IMPL>>>>>>>FLAG>>>>>>>" + budgetAction.getFlag());
            connection = ConnectionProvider.getInstance().getConnection();
            if (budgetAction.getFlag().equalsIgnoreCase("S")) {
                status = "Entered";
            } else {
                status = "Submitted";
            }

            System.out.println("****************** ENTERED INTO THE IMPL TRY BLOCK *******************");
            System.out.println(">>>>>>>pid>>" + budgetAction.getProject());
            System.out.println(">>>>>>>pid>>" + budgetAction.getEstimateBudget());
            System.out.println(">>>>>>>pid>>" + budgetAction.getQuarterId());
            System.out.println(">>>>>>>pid>>" + budgetAction.getYear());
            System.out.println(">>>>>>>pid>>" + budgetAction.getComments());
            System.out.println(">>>>>>>pid>>" + status);
            System.out.println(">>>>>>>pid>>" + budgetAction.getAddEditFlag());

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
                System.out.println("****************** in impl result after NotExist:::::::::" + Result);
            } else if (Result.equalsIgnoreCase("Updated")) {
                System.out.println("****************** in impl result after updatesuccess:::::::::" + Result);
            } else if (Result.equalsIgnoreCase("Added")) {
                System.out.println("****************** in impl result after added:::::::::" + Result);
            } else {
                System.out.println("****************** in impl result after error:::::::::" + Result);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
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
    public String getProjectBudgetDetailsToViewOnOverlay(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT id,prjid,estbugetamt,balbudgetamt,description,STATUS,qutindetifier,YEAR,approve_rej_comments "
                    + "FROM prjbudget WHERE id=" + budgetAction.getBudgetId();

            System.out.println("query...overlay..." + queryString);
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

            System.out.println("result=========>" + resultString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
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
    public String doBudgetRecordDelete( BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        int res=0;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "DELETE FROM prjbudget WHERE id="+ budgetAction.getBudgetId();

            System.out.println("query...overlay..." + queryString);
            statement = connection.createStatement();
            res = statement.executeUpdate(queryString);
           if(res>0){
               resultString="Success";
           }else{
               resultString="Fail";
           }

            System.out.println("result=========>" + resultString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
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
    public String setDirectorResultForBudget(BudgetAction budgetAction) throws ServiceLocatorException {
        String resultString = "";
        int res = 0;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "UPDATE prjbudget SET "
                    + "STATUS='" + budgetAction.getFlag() + "', "
                    + "balbudgetamt='" + budgetAction.getEstimateBudget() + "', "
                    + "estbugetamt='" + budgetAction.getEstimateBudget() + "', "
                    + "approve_rej_comments='" + budgetAction.getApproveComments() + "' "
                    + "WHERE prjid=" + budgetAction.getProject() + " "
                    + "AND qutindetifier='" + budgetAction.getQuarterId() + "' "
                    + "AND YEAR=" + budgetAction.getYear();

            System.out.println(budgetAction.getFlag() + "query...director update..." + queryString);
            statement = connection.createStatement();
            res = statement.executeUpdate(queryString);
            if (res > 0) {
                if ("Approved".equals(budgetAction.getFlag())) {
                    String queryString1 = "UPDATE costcenterbudgets SET "
                            + "spentamt= (spentamt) +(" + budgetAction.getEstimateBudget() + ") "
                            //+ "balance= (balance) -(" + budgetAction.getEstimateBudget() + ") "
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

            System.out.println("result=========>" + resultString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            resultString = "";
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return resultString;
    }
    /**
     * *************************************
     *
     *  
     *
     * @Author:Divya Gandreti<dgandreti@miraclesoft.com>
     *
     * @Created Date:October 14,2015
     *
     **************************************
     */
    public String getCostCentertDetailsByProjectId(BudgetAction budgetAction) throws ServiceLocatorException {
       String resultString = "";
        //String queryString = "";
        String queryString1 = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            queryString1 = "SELECT ap.cccode,ccname,budgetamt,spentamt,balance,year,Quarter,status FROM costcenter cc LEFT OUTER JOIN acc_projects ap ON(cc.cccode=ap.cccode) LEFT OUTER JOIN costcenterbudgets cb ON(cc.cccode=cb.cccode) WHERE project_id =" + budgetAction.getProjectId() + " and cb.ccbstatus = 'Active'";
            resultSet = statement.executeQuery(queryString1);
            while (resultSet.next()) {
                resultString += resultSet.getString("ccname") + "|" + resultSet.getDouble("budgetamt") + "|" + resultSet.getDouble("spentamt") + "|" + resultSet.getDouble("balance") + "|" + resultSet.getString("cccode") + "|" + resultSet.getString("year") + "|" + resultSet.getString("Quarter") + "|" + resultSet.getString("status") + "^";
            }
            if ("".equals(resultString)||resultString==null) {
                queryString1 = "SELECT ccname FROM costcenter cc LEFT OUTER JOIN acc_projects ap ON(cc.cccode=ap.cccode) WHERE project_id =" + budgetAction.getProjectId();
                resultSet = statement.executeQuery(queryString1);
                while (resultSet.next()) {
                    resultString += resultSet.getString("ccname")+"|"+"CCN";
                }
            }
            System.out.println("queryString helloooo -->" + queryString1);

        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
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
            }
        }
        return resultString;
    }
}
