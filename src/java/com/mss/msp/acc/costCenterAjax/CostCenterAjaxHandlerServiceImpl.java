/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.costCenterAjax;

import com.mss.msp.acc.costCenter.*;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author miracle
 */
public class CostCenterAjaxHandlerServiceImpl implements CostCenterAjaxHandlerService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";
    String resultString = "";

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getCostCenterDashboardDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getCostCenterDashboardDetails(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString = "";
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;
        String startDate = "";
        String endDate = "";
        String budget = "";
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getCostCenterDashboardDetails Method Start*********************");
        try {
            if ("SA".equalsIgnoreCase(costCenterAjaxHandlerAction.getTypeOfUser())) {
                queryString = "SELECT account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount"
                        + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a ON(account_id=orgid)"
                        + " WHERE  ccstatus='Active' AND ccbstatus='Active' ";
                if (costCenterAjaxHandlerAction.getDashBoardYear() != 0) {
                    queryString += " AND ccb.YEAR=" + costCenterAjaxHandlerAction.getDashBoardYear() + " ";
                }
                if (!"DF".equals(costCenterAjaxHandlerAction.getQuarter())) {
                    queryString += " AND ccb.QUARTER='" + costCenterAjaxHandlerAction.getQuarter() + "' ";
                }
                queryString += " GROUP BY account_id ORDER BY budgetAmount DESC LIMIT 0,10";
            } else {
                queryString = "SELECT account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount"
                        + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a ON(account_id=orgid)"
                        + " WHERE ccstatus='Active' AND ccbstatus='Active'  "
                        + " AND orgid= " + costCenterAjaxHandlerAction.getSessionOrgId() + " ";
                if (costCenterAjaxHandlerAction.getDashBoardYear() != 0 && !"".equals(costCenterAjaxHandlerAction.getDashBoardYear())) {
                    queryString += " AND ccb.YEAR =" + costCenterAjaxHandlerAction.getDashBoardYear() + " ";
                }
                if (!"DF".equals(costCenterAjaxHandlerAction.getQuarter())) {
                    queryString += " AND ccb.QUARTER='" + costCenterAjaxHandlerAction.getQuarter() + "' ";
                }
            }
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("account_name") + "|" + resultSet.getDouble("budgetAmount") + "|" + resultSet.getDouble("spentAmount") + "|" + resultSet.getDouble("balanceAmount") + "^";
            }
        } catch (Exception ex) {
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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getCostCenterDashboardDetails Method End*********************");
        return resultString;
    }

    public String getCostCentersDashboardForOrg(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString = "";
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;
        String startDate = "";
        String endDate = "";
        String budget = "";
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getCostCentersDashboardForOrg Method Start*********************");
        try {
            if ("yearquaters".equals(costCenterAjaxHandlerAction.getQuarter())) {
                queryString = "SELECT QUARTER,account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount "
                        + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a "
                        + " ON(account_id=orgid) WHERE ccstatus='Active' AND ccbstatus='Active'"
                        + " AND orgid=" + costCenterAjaxHandlerAction.getOrgId() + " ";
                if (costCenterAjaxHandlerAction.getDashBoardYear() != 0 && !"".equals(costCenterAjaxHandlerAction.getDashBoardYear())) {
                    queryString += " AND ccb.YEAR =" + costCenterAjaxHandlerAction.getDashBoardYear() + " ";
                }
                queryString += " GROUP BY ccb.QUARTER ";
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                double budgetAmt = 0;
                double spentAmt = 0;
                double balanceAmt = 0;
                while (resultSet.next()) {
                    budgetAmt = budgetAmt + resultSet.getDouble("budgetAmount");
                    spentAmt = spentAmt + resultSet.getDouble("spentAmount");
                    balanceAmt = balanceAmt + (resultSet.getDouble("budgetAmount") - resultSet.getDouble("spentAmount"));
                    resultString += resultSet.getString("QUARTER") + "|" + resultSet.getDouble("budgetAmount") + "|" + resultSet.getDouble("spentAmount") + "|" + (resultSet.getDouble("budgetAmount") - resultSet.getDouble("spentAmount")) + "^";
                }
                resultString += "All Quarters" + "|" + budgetAmt + "|" + spentAmt + "|" + balanceAmt + "^";
            } else if (!"-1".equals(costCenterAjaxHandlerAction.getCostCenters()) && !"null".equals(costCenterAjaxHandlerAction.getCostCenters())) {
                queryString = "SELECT  proj_name,cc.cccode,cc.ccname,estbugetamt as budgetAmount,balbudgetamt as balanceAmount FROM "
                        + " costcenter cc JOIN costcenterbudgets ccb ON(ccb.cccode=cc.cccode) "
                        + " JOIN acc_projects ap  ON(ap.cccode=cc.cccode) JOIN prjbudget pb ON(ap.project_id=pb.prjid) WHERE proj_type='MP' AND proj_status='Active' AND "
                        + " ccstatus='Active'  AND ccbstatus='Active' AND pb.STATUS='Approved' "
                        + " AND orgid=" + costCenterAjaxHandlerAction.getOrgId() + " "
                        + " AND cc.ccname='" + costCenterAjaxHandlerAction.getCostCenters() + "' ";
                if (costCenterAjaxHandlerAction.getDashBoardYear() != 0 && !"".equals(costCenterAjaxHandlerAction.getDashBoardYear())) {
                    queryString += " AND ccb.YEAR =" + costCenterAjaxHandlerAction.getDashBoardYear() + " ";
                }
                if (!"".equals(costCenterAjaxHandlerAction.getQuarter())) {
                    queryString += " AND ccb.QUARTER='" + costCenterAjaxHandlerAction.getQuarter() + "' ";
                }
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                int counter = 0;
                double spentAmt = 0;
                while (resultSet.next()) {
                    spentAmt = 0;
                    counter = counter + 1;
                    spentAmt = resultSet.getDouble("budgetAmount") - resultSet.getDouble("balanceAmount");
                    counter = counter + 1;
                    resultString += resultSet.getString("proj_name") + "|" + resultSet.getDouble("budgetAmount") + "|" + spentAmt + "|" + resultSet.getDouble("balanceAmount") + "|" + "costcenterpojectresponse" + "|" + resultSet.getString("cc.cccode") + "|" + resultSet.getString("cc.ccname") + "^";
                }
                if (counter == 0) {
                    resultString = "noprojects";
                }
            } else {
                queryString = "SELECT  cc.cccode,account_name,cc.ccname,budgetamt AS budgetAmount,spentamt AS spentAmount,balance AS balanceAmount "
                        + " FROM costcenter cc JOIN costcenterbudgets ccb ON(ccb.cccode=cc.cccode) JOIN accounts a ON(account_id=orgid)"
                        + " WHERE  ccstatus='Active' AND ccbstatus='Active'  "
                        + " and orgid=" + costCenterAjaxHandlerAction.getOrgId() + " ";
                if (costCenterAjaxHandlerAction.getDashBoardYear() != 0 && !"".equals(costCenterAjaxHandlerAction.getDashBoardYear())) {
                    queryString += " AND ccb.YEAR =" + costCenterAjaxHandlerAction.getDashBoardYear() + " ";
                }
                if (!"".equals(costCenterAjaxHandlerAction.getQuarter())) {
                    queryString += " AND ccb.QUARTER='" + costCenterAjaxHandlerAction.getQuarter() + "' ";
                }
                connection = ConnectionProvider.getInstance().getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryString);
                int counter = 0;
                double balanceAmt = 0;
                while (resultSet.next()) {
                    balanceAmt = 0;
                    counter = counter + 1;
                    balanceAmt = resultSet.getDouble("budgetAmount") - resultSet.getDouble("spentAmount");
                    resultString += resultSet.getString("cc.ccname") + "|" + resultSet.getDouble("budgetAmount") + "|" + resultSet.getDouble("spentAmount") + "|" + balanceAmt + "|" + "costcenterresponse" + "|" + resultSet.getString("cc.cccode") + "^";
                }
                if (counter == 0) {
                    resultString = "nocostcenters";
                }
            }
            System.out.println("getCostCentersDashboardForOrg::queryString getCostCenterDashboardDetails() -->" + queryString);
        } catch (Exception ex) {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getCostCentersDashboardForOrg Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : October 14, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getProjectNamesInCostCenter() method is used to
     *
     * *****************************************************************************
     */
    public String getProjectNamesInCostCenter(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getProjectNamesInCostCenter Method Start*********************");
        try {
            queryString = "SELECT project_id,proj_name FROM acc_projects WHERE cccode = '" + costCenterAjaxHandlerAction.getCcCode() + "'";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            int i = 1;
            while (resultSet.next()) {
                resultString += i + "|" + resultSet.getString("proj_name") + "^";
                i = i + 1;
            }
        } catch (Exception ex) {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getProjectNamesInCostCenter Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : costCenterInfoSearchList() method is used to
     *
     * *****************************************************************************
     */
    public String costCenterInfoSearchList(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        String startDate = "", endDate = "";
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getProjectNamesInCostCenter Method Start*********************");
        try {
            queryString = "SELECT * FROM costcenterbudgets WHERE cccode like '" + costCenterAjaxHandlerAction.getCcCode() + "'";
            if (!"".equals(costCenterAjaxHandlerAction.getYear()) && costCenterAjaxHandlerAction.getYear() != null) {
                queryString = queryString + " AND (EXTRACT(YEAR FROM startdate)=" + costCenterAjaxHandlerAction.getYear() + " OR EXTRACT(YEAR FROM enddate)=" + costCenterAjaxHandlerAction.getYear() + ")";
            }
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                startDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("startdate"));
                endDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("enddate"));
                resultString += resultSet.getDouble("budgetamt") + "|" + startDate + "|" + endDate + "|" + resultSet.getDouble("spentamt") + "|" + resultSet.getDouble("balance") + "|" + resultSet.getString("status") + "|" + resultSet.getString("ccbstatus") + "^";
            }
        } catch (Exception ex) {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getProjectNamesInCostCenter Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : addCostCenter() method is used to
     *
     * *****************************************************************************
     */
    public String addCostCenter(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        CallableStatement callableStatement=null;
       
        String resultString = null;
        String queryString = "";
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: addCostCenter Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call costCenterAdd(?,?,?,?)}");
            System.out.println("addCostCenter :: procedure name : costCenterAdd ");
            callableStatement.setString(1, costCenterAjaxHandlerAction.getCcName());
            callableStatement.setInt(2, costCenterAjaxHandlerAction.getSessionOrgId());
            callableStatement.setInt(3, costCenterAjaxHandlerAction.getSessionUserId());
            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            callableStatement.execute();
            int i = callableStatement.getInt(4);
            if (i > 0) {
                resultString = "Added Successfully";
            } else {
                resultString = "Please try again!";
            }
        } catch (Exception ex) {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: addCostCenter Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : editCostCenter() method is used to
     *
     * *****************************************************************************
     */
    public String editCostCenter(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        String resultString = null;
        String queryString = "";
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: editCostCenter Method Start*********************");
        try {
            queryString = "UPDATE costcenter SET ccname=?,ccstatus=?,modifiedby=?,modifieddate=? WHERE ccid=?";
            System.out.println("editCostCenter::queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, costCenterAjaxHandlerAction.getCcName());
            preparedStatement.setString(2, costCenterAjaxHandlerAction.getStatus());
            preparedStatement.setInt(3, costCenterAjaxHandlerAction.getSessionUserId());
            preparedStatement.setTimestamp(4, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            preparedStatement.setInt(5, costCenterAjaxHandlerAction.getCcId());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                resultString = "updated Successfully";
            } else {
                resultString = "Please try again!";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: editCostCenter Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : addCostCenterBudget() method is used to
     *
     * *****************************************************************************
     */
    public String addCostCenterBudget(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String queryString = "";
         Connection connection = null;
        PreparedStatement preparedStatement=null;
         String resultString = null;
        String startDate = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(costCenterAjaxHandlerAction.getStartDate());
        String endDate = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(costCenterAjaxHandlerAction.getEndDate());
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: addCostCenterBudget Method Start*********************");
        try {
            if ("budgetEdit".equalsIgnoreCase(costCenterAjaxHandlerAction.getCcFlag())) {
                queryString = "UPDATE costcenterbudgets SET cccode=?,startdate=?,enddate=?,budgetamt=?,ccbstatus=?,year=?,Quarter=?,status=?,balance=?";
                if ("Approved".equalsIgnoreCase(costCenterAjaxHandlerAction.getBudgetStatus())) {
                    queryString = queryString + " ,approvecomments ='" + costCenterAjaxHandlerAction.getApproveComments() + "'";
                } else if ("Rejected".equalsIgnoreCase(costCenterAjaxHandlerAction.getBudgetStatus())) {
                    queryString = queryString + " ,rejectioncomments ='" + costCenterAjaxHandlerAction.getRejectionComments() + "'";
                }
                queryString = queryString + " WHERE id=" + costCenterAjaxHandlerAction.getId();
            } else {
                queryString = "INSERT INTO costcenterbudgets(cccode,startdate,enddate,budgetamt,ccbstatus,year,Quarter,status,balance,spentamt) VALUES(?,?,?,?,?,?,?,?,?,?)";
            }
            System.out.println("addCostCenterBudget::queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, costCenterAjaxHandlerAction.getCcCode());
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setDouble(4, costCenterAjaxHandlerAction.getBudgetAmt());
            preparedStatement.setString(5, "Active");
            preparedStatement.setString(6, costCenterAjaxHandlerAction.getYear());
            preparedStatement.setString(7, costCenterAjaxHandlerAction.getQuarter());
            preparedStatement.setString(8, costCenterAjaxHandlerAction.getBudgetStatus());
            if ("Approved".equalsIgnoreCase(costCenterAjaxHandlerAction.getBudgetStatus())) {
                preparedStatement.setDouble(9, costCenterAjaxHandlerAction.getBudgetAmt());
            } else {
                preparedStatement.setDouble(9, 0.0);
            }
            if (!"budgetEdit".equalsIgnoreCase(costCenterAjaxHandlerAction.getCcFlag())) {
                preparedStatement.setDouble(10, 0.0);
            }
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                if ("budgetEdit".equalsIgnoreCase(costCenterAjaxHandlerAction.getCcFlag())) {
                    resultString = "updated Successfully";
                } else {
                    resultString = "Added Successfully";
                }
            } else {
                resultString = "Please try again!";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: addCostCenterBudget Method End*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     *
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * ForUse : getCostCenterBudgetDetails() method is used to
     *
     * *****************************************************************************
     */
    public String getCostCenterBudgetDetails(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = "";
        String queryString = "";
        String startDate = "";
        String endDate = "";
        String budget = "";
          Connection connection = null;
          Statement statement = null;
          ResultSet resultSet = null;
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getCostCenterBudgetDetails Method Start*********************");
        try {
            queryString = "SELECT * FROM costcenterbudgets WHERE id=" + costCenterAjaxHandlerAction.getId() + " AND ccbstatus LIKE 'Active'";
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                startDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("startdate"));
                endDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("enddate"));
                //budget = Double.toString(resultSet.getDouble("budgetamt"));
                resultString += resultSet.getDouble("budgetamt") + "|" + startDate + "|" + endDate + "|" + resultSet.getDouble("spentamt") + "|" + resultSet.getDouble("balance") + "|" + resultSet.getString("year") + "|" + resultSet.getString("quarter") + "|" + resultSet.getString("status") + "|" + resultSet.getString("approvecomments") + "|" + resultSet.getString("rejectioncomments") + "^";
            }
        } catch (Exception ex) {
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
        System.out.println("********************CostCenterAjaxHandlerServiceImpl :: getCostCenterBudgetDetails Method End*********************");
        return resultString;
    }
}
