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

   public String getCostCenterDashboardDetails(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        String queryString = "";
        String startDate = "";
        String endDate = "";
        String budget = "";
        ResultSet resultSet = null;
        try {
            // queryString = "SELECT  FROM costcenterbudgets WHERE id=" + costCenterAjaxHandlerAction.getId() + " AND ccbstatus LIKE 'Active'";
            System.out.println("getDashBoardYear()-->" + costCenterAjaxHandlerAction.getDashBoardYear());
            System.out.println("getQuarter()-->" + costCenterAjaxHandlerAction.getQuarter());

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
            System.out.println("queryString getCostCenterDashboardDetails() -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                resultString += resultSet.getString("account_name") + "|" + resultSet.getDouble("budgetAmount") + "|" + resultSet.getDouble("spentAmount") + "|" + resultSet.getDouble("balanceAmount") + "^";
            }

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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return resultString;
    }

    public String getCostCentersDashboardForOrg(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        String queryString = "";
        String startDate = "";
        String endDate = "";
        String budget = "";
        ResultSet resultSet = null;
        try {
            // queryString = "SELECT  FROM costcenterbudgets WHERE id=" + costCenterAjaxHandlerAction.getId() + " AND ccbstatus LIKE 'Active'";
            System.out.println("getCostCentersDashboardForOrg-->getDashBoardYear()-->" + costCenterAjaxHandlerAction.getDashBoardYear());
            System.out.println("getCostCenters()-->" + costCenterAjaxHandlerAction.getCostCenters());
            System.out.println("getOrgId()-->" + costCenterAjaxHandlerAction.getOrgId());
            System.out.println("getQuarter()-->" + costCenterAjaxHandlerAction.getQuarter());

            if ("yearquaters".equals(costCenterAjaxHandlerAction.getQuarter())) {
                queryString = "SELECT QUARTER,account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount "
                        + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a "
                        + " ON(account_id=orgid) WHERE ccstatus='Active' AND ccbstatus='Active'"
                        + " AND orgid=" + costCenterAjaxHandlerAction.getOrgId() + " ";

//                if (costCenterAjaxHandlerAction.getDashBoardYear() != 0) {
//                    queryString += " AND (EXTRACT(YEAR FROM startdate) = " + costCenterAjaxHandlerAction.getDashBoardYear() + "  OR EXTRACT(YEAR FROM enddate) =" + costCenterAjaxHandlerAction.getDashBoardYear() + " )";
//
//                }
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
                    balanceAmt = balanceAmt + (budgetAmt - spentAmt);

                    resultString += resultSet.getString("QUARTER") + "|" + resultSet.getDouble("budgetAmount") + "|" + resultSet.getDouble("spentAmount") + "|" + (budgetAmt - spentAmt) + "^";


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

                //  queryString   += "  GROUP BY cc.cccode";
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
                    resultString += resultSet.getString("proj_name") + "|" + resultSet.getDouble("budgetAmount") + "|" + spentAmt + "|"  + resultSet.getDouble("balanceAmount") + "|" + "costcenterpojectresponse" + "|" + resultSet.getString("cc.cccode") + "|" + resultSet.getString("cc.ccname") + "^";
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
                //  queryString   += "  GROUP BY cc.cccode";

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



            System.out.println("queryString getCostCenterDashboardDetails() -->" + queryString);


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
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return resultString;
    }
    /**
     * *****************************************************************************
     * Date : October 14, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     * 
     * *****************************************************************************
     */
    public String getProjectNamesInCostCenter(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            queryString = "SELECT project_id,proj_name FROM acc_projects WHERE cccode = '" + costCenterAjaxHandlerAction.getCcCode() + "'";
            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            int i = 1;
            while (resultSet.next()) {
                resultString += i + "|" + resultSet.getString("proj_name") + "^";
                i = i + 1;
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
        return resultString;
    }
    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
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
        try {
            queryString = "SELECT * FROM costcenterbudgets WHERE cccode like '"+costCenterAjaxHandlerAction.getCcCode()+"'";
            if (!"".equals(costCenterAjaxHandlerAction.getYear()) && costCenterAjaxHandlerAction.getYear() != null) {
                queryString = queryString + " AND (EXTRACT(YEAR FROM startdate)=" + costCenterAjaxHandlerAction.getYear() + " OR EXTRACT(YEAR FROM enddate)=" + costCenterAjaxHandlerAction.getYear() + ")";
            }
            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                startDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("startdate"));
                endDate = com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("enddate"));
                resultString += resultSet.getDouble("budgetamt") + "|" + startDate + "|" + endDate + "|" + resultSet.getDouble("spentamt") + "|" + resultSet.getDouble("balance") +"|" + resultSet.getString("status") +"|" + resultSet.getString("ccbstatus") + "^";

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
        return resultString;
    }
/**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String addCostCenter(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        String queryString = "";
        try {
            // queryString = "INSERT INTO costcenter(cccode,ccname,ccstatus,orgid,createdby,createddate) VALUES(?,?,?,?,?,?)";
            //System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call costCenterAdd(?,?,?,?)}");
//            preparedStatement = connection.prepareStatement(queryString);
//            preparedStatement.setString(1, );
//            preparedStatement.setString(2, costCenterAction.getCcName());
//            preparedStatement.setString(3, "Active");
//            preparedStatement.setInt(4, costCenterAction.getSessionOrgId());
//            preparedStatement.setInt(5, costCenterAction.getSessionUserId());
//            preparedStatement.setTimestamp(6, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
//            int i = preparedStatement.executeUpdate();
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
            System.out.println(ex.toString());
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
        return resultString;
    }
    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String editCostCenter(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        try {
            queryString = "UPDATE costcenter SET ccname=?,ccstatus=?,modifiedby=?,modifieddate=? WHERE ccid=?";
            System.out.println("queryString helloooo -->" + queryString);
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
            System.out.println(ex.toString());
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
        return resultString;
    }
 /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String addCostCenterBudget(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";
        String startDate = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(costCenterAjaxHandlerAction.getStartDate());
        String endDate = com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(costCenterAjaxHandlerAction.getEndDate());
        try {
            System.out.println("-----------" + costCenterAjaxHandlerAction.getBudgetStatus());
            if ("budgetEdit".equalsIgnoreCase(costCenterAjaxHandlerAction.getCcFlag())) {
                queryString = "UPDATE costcenterbudgets SET cccode=?,startdate=?,enddate=?,budgetamt=?,ccbstatus=?,year=?,Quarter=?,status=?,balance=?";
                if ("Approved".equalsIgnoreCase(costCenterAjaxHandlerAction.getBudgetStatus())) {

                    queryString = queryString + " ,approvecomments ='" + costCenterAjaxHandlerAction.getApproveComments()+"'";
                    System.out.println("-------------" + queryString);
                } else if ("Rejected".equalsIgnoreCase(costCenterAjaxHandlerAction.getBudgetStatus())){
                    queryString = queryString + " ,rejectioncomments ='" + costCenterAjaxHandlerAction.getRejectionComments()+"'";
                    System.out.println("-------------" + queryString);
                }
                                    System.out.println("-------------" + queryString);
                queryString = queryString + " WHERE id=" + costCenterAjaxHandlerAction.getId();
                System.out.println("---------------" + queryString);
            } else {
                queryString = "INSERT INTO costcenterbudgets(cccode,startdate,enddate,budgetamt,ccbstatus,year,Quarter,status,balance,spentamt) VALUES(?,?,?,?,?,?,?,?,?,?)";
            }
            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, costCenterAjaxHandlerAction.getCcCode());
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setDouble(4, costCenterAjaxHandlerAction.getBudgetAmt());
            preparedStatement.setString(5, "Active");
            System.out.println(costCenterAjaxHandlerAction.getYear() + "-----------" + costCenterAjaxHandlerAction.getQuarter());
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
            System.out.println(ex.toString());
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
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public String getCostCenterBudgetDetails(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException {
        String resultString = "";
        Connection connection = null;
        Statement statement = null;
        String queryString = "";
        String startDate = "";
        String endDate = "";
        String budget = "";
        ResultSet resultSet = null;
        try {
            queryString = "SELECT * FROM costcenterbudgets WHERE id=" + costCenterAjaxHandlerAction.getId() + " AND ccbstatus LIKE 'Active'";
            System.out.println("queryString helloooo -->" + queryString);
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
        return resultString;
    }

}
