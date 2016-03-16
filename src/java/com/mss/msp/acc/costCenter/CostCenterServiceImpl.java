/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.costCenter;

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
public class CostCenterServiceImpl implements CostCenterService {
//divya 29th sep
/**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     * 
     * 
     * *****************************************************************************
     */
     public List costCenterSearch(CostCenterAction costCenterAction) throws ServiceLocatorException {
        ArrayList costSearchList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String queryString1 = "";
        StringTokenizer str;
        try {
            queryString = "SELECT ccid,cc.cccode,ccname,ccstatus,COUNT(project_id) AS projectsCount FROM costcenter cc LEFT OUTER JOIN acc_projects ap ON(cc.cccode=ap.cccode) WHERE orgid=" + costCenterAction.getSessionOrgId();// + " AND ccstatus LIKE 'Active'";
            if (!"".equalsIgnoreCase(costCenterAction.getCcCode()) && costCenterAction.getCcCode() != null) {
                queryString = queryString + " AND cc.cccode like '%" + costCenterAction.getCcCode() + "%'";
            }
            if (!"".equalsIgnoreCase(costCenterAction.getCcName()) && costCenterAction.getCcName() != null) {
                queryString = queryString + " AND ccname like '%" + costCenterAction.getCcName() + "%'";
            }
            if (!"".equalsIgnoreCase(costCenterAction.getStatus()) && costCenterAction.getStatus() != null) {
                queryString = queryString + " AND ccstatus like '" + costCenterAction.getStatus() + "'";
            } else {
                queryString = queryString + " AND ccstatus like 'Active'";
            }
            queryString = queryString + " GROUP BY cccode";
            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
//            queryString1 = "SELECT budgetamt FROM costcenter cc LEFT OUTER JOIN costcenterbudgets ccb ON "
//                    + "(cc.cccode=ccb.cccode) WHERE orgid=" + costCenterAction.getSessionOrgId() + " AND ccbstatus"
//                    + " LIKE 'Active' AND ccstatus LIKE 'Active'";
            while (resultSet.next()) {
                CostCenterVTO costCenterVTO = new CostCenterVTO();
                costCenterVTO.setCcId(resultSet.getInt("ccid"));
                costCenterVTO.setCcCode(resultSet.getString("cccode"));
                costCenterVTO.setCcName(resultSet.getString("ccname"));
                costCenterVTO.setCcStatus(resultSet.getString("ccstatus"));
                costCenterVTO.setProjCount(resultSet.getInt("projectsCount"));
                String result = com.mss.msp.util.DataSourceDataProvider.getInstance().getCostCenterBudget(resultSet.getString("cccode"));
                str = new StringTokenizer(result, "^");
                while (str.hasMoreElements()) {
//                    excelColValue[k] = str.nextElement();
                    System.out.println("");
                    costCenterVTO.setBudgetAmt(Double.parseDouble(str.nextElement().toString()));
                    costCenterVTO.setId(Integer.parseInt(str.nextElement().toString()));
                    costCenterVTO.setBudgetStatus(str.nextElement().toString());
                    System.out.println("---in st------" + costCenterVTO.getBudgetStatus());
                }

                costSearchList.add(costCenterVTO);
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
        return costSearchList;
    }
   /**
     * *****************************************************************************
     * Date : september 30, 2015, 04:13 PM EST
     * Author:Divya<dgandreti@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public List costCenterInfoSearch(CostCenterAction costCenterAction) throws ServiceLocatorException {
        ArrayList costSearchList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";double bal = 0.0;
        int year = costCenterAction.getDashBoardYear();
        try {
            queryString = "SELECT * FROM costcenterbudgets WHERE cccode like '" + costCenterAction.getCcCode() + "' AND(startdate LIKE '%" + year + "%' OR enddate LIKE '%" + year + "%')";
            System.out.println("queryString helloooo -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                CostCenterVTO costCenterVTO = new CostCenterVTO();
                costCenterVTO.setCcCode(resultSet.getString("cccode"));
                costCenterVTO.setStartDate(com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("startdate")));
                costCenterVTO.setEndDate(com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("enddate")));
                costCenterVTO.setSpentAmt(resultSet.getDouble("spentamt"));
                bal = resultSet.getDouble("budgetamt") - resultSet.getDouble("spentamt");
                costCenterVTO.setBalanceAmt(bal);
                costCenterVTO.setBudgetAmt(resultSet.getDouble("budgetamt"));
                costCenterVTO.setId(resultSet.getInt("id"));
                costCenterVTO.setBudgetStatus(resultSet.getString("status"));
                costCenterVTO.setStatus(resultSet.getString("ccbstatus"));
                costSearchList.add(costCenterVTO);
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
        return costSearchList;
    }
     /**
     * *****************************************************************************
     * Date : October 01, 2015, 04:13 PM IST
     * Author:Manikanta<meeralla@miraclesoft.com>
     *
     *
     * *****************************************************************************
     */
    public List costCenterDashBoardDetails(CostCenterAction costCenterAction) throws ServiceLocatorException {
        ArrayList costCenterDashBoardList = new ArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        try {
            if ("SA".equalsIgnoreCase(costCenterAction.getTypeOfUser())) {
//             queryString = "SELECT account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount"
//                    + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a ON(account_id=orgid)"
//                    + " WHERE  ccstatus='Active' AND ccbstatus='Active' AND EXTRACT(YEAR FROM startdate) =" + costCenterAction.getDashBoardYear() + " "
//                    + " GROUP BY account_id ORDER BY budgetAmount DESC LIMIT 0,10"; 
                queryString = "SELECT account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount"
                        + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a ON(account_id=orgid)"
                        + " WHERE  ccstatus='Active' AND ccbstatus='Active' AND ccb.YEAR=" + costCenterAction.getDashBoardYear() + " "
                        + " GROUP BY account_id ORDER BY budgetAmount DESC LIMIT 0,10";

            } else {
                queryString = "SELECT QUARTER,account_name,SUM(budgetamt) AS budgetAmount,SUM(spentamt) AS spentAmount,SUM(balance) AS balanceAmount"
                        + " FROM costcenterbudgets ccb JOIN costcenter cc ON(ccb.cccode=cc.cccode) JOIN accounts a ON(account_id=orgid)"
                        + " WHERE ccstatus='Active' AND ccbstatus='Active' AND ccb.YEAR=" + costCenterAction.getDashBoardYear() + " "
                        + " AND orgid= " + costCenterAction.getSessionOrgId() + " "
                        + " GROUP BY ccb.QUARTER";
            }
            //   queryString = "SELECT * FROM costcenterbudgets WHERE cccode like '"+costCenterAction.getCcCode()+"' AND(startdate LIKE '%" + year + "%' OR enddate LIKE '%" + year + "%')";
            System.out.println("queryString costCenterDashBoardDetails() -->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            double budget = 0;
            double spent = 0;
            double balance = 0;
            String accName = "";
            while (resultSet.next()) {
                CostCenterVTO costCenterVTO = new CostCenterVTO();
                costCenterVTO.setAccountName(resultSet.getString("QUARTER"));
                costCenterVTO.setSpentAmt(resultSet.getDouble("spentAmount"));
                costCenterVTO.setBudgetAmt(resultSet.getDouble("budgetAmount"));
                costCenterVTO.setBalanceAmt(costCenterVTO.getBudgetAmt() - costCenterVTO.getSpentAmt());

                if ("SA".equalsIgnoreCase(costCenterAction.getTypeOfUser())) {
                } else {
                    accName = costCenterVTO.getAccountName();
                    budget = budget + costCenterVTO.getBudgetAmt();
                    spent = spent + costCenterVTO.getSpentAmt();
                    balance = balance + costCenterVTO.getBalanceAmt();
                }
                costCenterDashBoardList.add(costCenterVTO);
            }
            
            if ("SA".equalsIgnoreCase(costCenterAction.getTypeOfUser())) {
            } else {
                CostCenterVTO costCenterVTO = new CostCenterVTO();
                costCenterVTO.setAccountName("All Quarters");
                costCenterVTO.setBudgetAmt(budget);
                costCenterVTO.setSpentAmt(spent);
                costCenterVTO.setBalanceAmt(balance);
                costCenterDashBoardList.add(costCenterVTO);
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
        return costCenterDashBoardList;
    }

}
