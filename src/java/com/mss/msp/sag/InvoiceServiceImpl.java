/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataUtility;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author miracle
 */
public class InvoiceServiceImpl implements InvoiceService {

    private Connection connection;
    private Statement statement;
    private CallableStatement callableStatement;
    private boolean isExceute;

    public List getInvoiceDetails(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {
        ArrayList<InvoiceVTO> listVto = new ArrayList<InvoiceVTO>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

//            String sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,account_name,invoiceyear,inv.usr_id,"
//                    + "CONCAT(first_name,'.',last_name) NAMES,account_name AS custname FROM invoice inv "
//                    + "LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
//                    + "LEFT OUTER JOIN project_team pt ON(inv.usr_id=pt.usr_id) "
//                    + "LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE  pt.current_status='Active' AND u.org_id=" + invoiceAction.getSessionOrgId();

            if ("VC".equalsIgnoreCase(typeOfUser)) {
                sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name AS custname "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id)"
                        + "  LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE inv.frm_orgid=" + invoiceAction.getSessionOrgId();
            } else {
                sqlquery = "   SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name AS custname "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
                        + "LEFT OUTER JOIN accounts acc ON (inv.frm_orgid=acc.account_id) WHERE  inv.custorg_id=" + invoiceAction.getSessionOrgId();
            }
            sqlquery += " order by NAMES limit 100 ";


            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                InvoiceVTO ivto = new InvoiceVTO();
                ivto.setInvoiceId(resultSet.getInt("invoiceid"));
                ivto.setUserName(resultSet.getString("NAMES"));
                ivto.setCustName(resultSet.getString("custname"));
                ivto.setTotalHrs(resultSet.getString("totalhrs"));
                ivto.setInvoiceDate(DataUtility.getInstance().getMonthNameByNumber(resultSet.getInt("invoicemonth")) + ", " + resultSet.getString("invoiceyear"));
                ivto.setTotalAmt(resultSet.getString("totalamt"));
                ivto.setStatus(resultSet.getString("invoicestatus"));
                ivto.setUsr_id(resultSet.getInt("usr_id"));
                listVto.add(ivto);
            }
        } catch (SQLException ex) {
            listVto = null;
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return listVto;
    }

    public boolean deleteInvoice(int invoiceId) throws ServiceLocatorException {
        boolean response = false;
        ResultSet resultSet = null;
        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            sqlquery = "delete from invoice where invoiceid=" + invoiceId;
            statement = connection.createStatement();
            response = statement.execute(sqlquery);

        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return response;
    }

    public List doSearchInvoiceDetails(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {
        ArrayList<InvoiceVTO> listVto = new ArrayList<InvoiceVTO>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

//            String sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,account_name,invoiceyear,inv.usr_id,"
//                    + "CONCAT(first_name,'.',last_name) NAMES,account_name AS custname FROM invoice inv "
//                    + "LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
//                    + "LEFT OUTER JOIN project_team pt ON(inv.usr_id=pt.usr_id) "
//                    + "LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE  pt.current_status='Active' AND u.org_id=" + invoiceAction.getSessionOrgId();

            if ("VC".equalsIgnoreCase(typeOfUser)) {
                sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name  "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id)"
                        + "  LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE  inv.frm_orgid=" + invoiceAction.getSessionOrgId();
            } else {
                sqlquery = "   SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name  "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
                        + "LEFT OUTER JOIN accounts acc ON (inv.frm_orgid=acc.account_id) WHERE  inv.custorg_id=" + invoiceAction.getSessionOrgId();
            }

            if (invoiceAction.getInvoiceMonth() != 0) {
                sqlquery += " and invoicemonth=" + invoiceAction.getInvoiceMonth();
            }
            if (invoiceAction.getInvoiceYear() != 0) {
                sqlquery += " and invoiceyear=" + invoiceAction.getInvoiceYear();
            }
            //if (invoiceAction.getInvoiceStatus() != null) {

            //}
            if (invoiceAction.getInvVendor() != null && invoiceAction.getInvVendor() != "") {
                sqlquery += " and account_name like '" + invoiceAction.getInvVendor() + "%'";
            }
            if (invoiceAction.getInvoiceResource() != null && invoiceAction.getInvoiceResource() != "") {
                sqlquery += " and (first_name like '%" + invoiceAction.getInvoiceResource() + "%' or last_name like '%" + invoiceAction.getInvoiceResource() + "%') ";
            }
            if (!"All".equals(invoiceAction.getInvoiceStatus())) {
                sqlquery += " and invoicestatus='" + invoiceAction.getInvoiceStatus() + "'";
            }

          //  sqlquery += " and invoicestatus='" + invoiceAction.getInvoiceStatus() + "'";
            sqlquery += " order by NAMES limit 100 ";
            System.out.println("sql query invoices" + sqlquery);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                InvoiceVTO ivto = new InvoiceVTO();
                ivto.setInvoiceId(resultSet.getInt("invoiceid"));
                ivto.setUserName(resultSet.getString("NAMES"));
                ivto.setCustName(resultSet.getString("account_name"));
                ivto.setTotalHrs(resultSet.getString("totalhrs"));
                ivto.setInvoiceDate(DataUtility.getInstance().getMonthNameByNumber(resultSet.getInt("invoicemonth")) + ", " + resultSet.getString("invoiceyear"));
                ivto.setTotalAmt(resultSet.getString("totalamt"));
                ivto.setStatus(resultSet.getString("invoicestatus"));
                 ivto.setUsr_id(resultSet.getInt("usr_id"));
                listVto.add(ivto);
            }
        } catch (SQLException ex) {
            listVto = null;
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return listVto;
    }

    public InvoiceVTO goInvoiceEditDetails(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {
        ResultSet resultSet = null;
        InvoiceVTO ivto = new InvoiceVTO();

        String sqlquery = " ";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
//            sqlquery = "SELECT invoiceid,invoicemonth, invoiceyear, invoicestatus, frm_orgid, custorg_id, "
//                    + "totalhrs, totalamt, netterms, paytype, cheortrnsno, paymentdate, balanceamt, "
//                    + "description, custapprid, custapprdate , account_name, "
//                    + "CONCAT(first_name,'.',last_name) NAMES FROM invoice i "
//                    + "LEFT OUTER JOIN users u ON (i.usr_id=u.usr_id) LEFT OUTER JOIN "
//                    + "accounts acc ON (i.custorg_id=account_id) WHERE invoiceid=" + invoiceAction.getInvoiceId();


            if ("VC".equalsIgnoreCase(typeOfUser)) {
                sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,paidAmt,CONCAT(first_name,'.',last_name) NAMES,account_name,frm_orgid, custorg_id, "
                        + " description, custapprid, custapprdate , "
                        + "totalhrs, totalamt, netterms, paytype, cheortrnsno, paymentdate, balanceamt "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id)"
                        + "  LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE  invoiceid=" + invoiceAction.getInvoiceId();
            } else {
                sqlquery = "   SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,paidAmt,CONCAT(first_name,'.',last_name) NAMES,account_name ,frm_orgid, custorg_id, "
                        + "totalhrs, totalamt, netterms, paytype, cheortrnsno, paymentdate, balanceamt, "
                        + " description, custapprid, custapprdate "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
                        + "LEFT OUTER JOIN accounts acc ON (inv.frm_orgid=acc.account_id) WHERE  invoiceid=" + invoiceAction.getInvoiceId();
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                ivto.setInvoiceId(resultSet.getInt("invoiceid"));
                ivto.setUserName(resultSet.getString("NAMES"));
                ivto.setCustName(resultSet.getString("account_name"));
                ivto.setTotalHrs(resultSet.getString("totalhrs"));
                ivto.setInvoicemonth(resultSet.getInt("invoicemonth"));
                ivto.setInvoiceyear(resultSet.getInt("invoiceyear"));
                System.out.println("the invoice year-->" + ivto.getInvoiceyear());
                ivto.setTotalAmt(resultSet.getString("totalamt"));
                ivto.setStatus(resultSet.getString("invoicestatus"));
                ivto.setNetTerms(resultSet.getInt("netterms"));
                ivto.setPayType(resultSet.getString("paytype"));
                ivto.setCheOrTransno(resultSet.getString("cheortrnsno"));
                ivto.setBalanceAmt(resultSet.getDouble("balanceamt"));
                ivto.setDescription(resultSet.getString("description"));
                if (resultSet.getString("custapprdate") != null) {
                    ivto.setCustApprDate(com.mss.msp.util.DateUtility.getInstance().convertDateYMDtoMDY(resultSet.getString("custapprdate")));
                }
                ivto.setCustApprName(com.mss.msp.util.DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(resultSet.getInt("custapprid")));
                ivto.setPaidAmt(resultSet.getDouble("paidAmt"));
                if (resultSet.getString("paymentdate") != null) {
                    ivto.setPaymentDate(com.mss.msp.util.DateUtility.getInstance().convertDateYMDtoMDY(resultSet.getString("paymentdate")));
                }
            }

        } catch (SQLException ex) {
            ivto = null;
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return ivto;
    }

    public boolean doEditInvoiceDetatils(InvoiceVTO invoiceVTO, String typeOfUser, InvoiceAction invoiceAction) throws ServiceLocatorException {
        boolean response = false;
        ResultSet resultSet = null;
        String sqlquery = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            //  sqlquery = "delete from invoice where ivoiceid=" + ;
            statement = connection.createStatement();
//            System.out.println("this is printg edit Invoice Impl status-->" + invoiceVTO.getStatus());
//            System.out.println("this is printg edit Invoice Impl month-->" + invoiceVTO.getInvoicemonth());
//            System.out.println("this is printg edit Invoice Impl year-->" + invoiceVTO.getInvoiceyear());
//            System.out.println("this is printg edit Invoice Impl paytype-->" + invoiceVTO.getPayType());
//            System.out.println("this is printg edit invoice impl cheortrans-->" + invoiceVTO.getCheOrTransno());
//            System.out.println("this is printg edit invoice impl tothrs-->" + invoiceVTO.getTotalHrs());
//            System.out.println("this is printg edit invoice impl totamt-->" + invoiceVTO.getTotalAmt());
//            System.out.println("this is printg edit invoice impl terms-->" + invoiceVTO.getNetTerms());
//            System.out.println("this is printg edit invoice impl bal-->" + invoiceVTO.getBalanceAmt());
//            System.out.println("this is printg edit invoice impl paidamt-->" + invoiceVTO.getPaidAmt());
//            System.out.println("this is printg edit invoice impl paybleDate-->" + invoiceVTO.getPaymentDate());
//            System.out.println("this is printg edit invoice impl userSessionId-->" + invoiceAction.getUserSeessionId());
//

            if ("VC".equalsIgnoreCase(typeOfUser)) {
                System.out.println("this is VC query..");
                sqlquery = "UPDATE invoice SET invoicemonth=" + invoiceVTO.getInvoicemonth() + ",invoiceyear=" + invoiceVTO.getInvoiceyear()
                        + ",invoicestatus='" + invoiceVTO.getStatus() + "',totalamt=" + invoiceVTO.getTotalAmt() + ",description='" + invoiceVTO.getDescription() + "',modified_by=" + invoiceAction.getUserSeessionId() + ",modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
            } else if ("approved".equalsIgnoreCase(invoiceVTO.getStatus())) {
                System.out.println("this is approved query..");
                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus()
                        + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
                        + "totalhrs = " + invoiceVTO.getTotalHrs() + ",  "
                        + " paymentdate = '" + com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(invoiceVTO.getPaymentDate()) + "', "
                        + " description ='" + invoiceVTO.getDescription() + "' ,custapprid=" + invoiceAction.getUserSeessionId() + ", custapprdate='" + com.mss.msp.util.DateUtility.getInstance().getCurrentSQLDate() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
                System.out.println("this is in approved-->" + sqlquery);
            } else if ("rejected".equalsIgnoreCase(invoiceVTO.getStatus())) {
                System.out.println("this is rejected query..");
                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus()
                        + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
                        + "totalhrs = " + invoiceVTO.getTotalHrs() + ",  "
                        + " description ='" + invoiceVTO.getDescription() + "' ,custapprid=" + invoiceAction.getUserSeessionId() + ", custapprdate='" + com.mss.msp.util.DateUtility.getInstance().getCurrentSQLDate() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
            } else if ("paid".equalsIgnoreCase(invoiceVTO.getStatus())) {
                System.out.println("this is paid query..");
//                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus()
//                        + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
//                        + "totalhrs = " + invoiceVTO.getTotalHrs() + ",  "
//                        + " paymentdate = '" + com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(invoiceVTO.getPaymentDate()) + "', "
//                        + " description ='" + invoiceVTO.getDescription()
//                        + "', modified_by=" + invoiceAction.getUserSeessionId()
//                        + ",paidAmt = " + invoiceVTO.getPaidAmt()
//                        + ",paytype = '" + invoiceVTO.getPayType() + "'"
//                        + ",cheortrnsno =  '" + invoiceVTO.getCheOrTransno() + "'"
//                        + ",balanceamt = " + invoiceVTO.getBalanceAmt()
//                        + ",modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime()
//                        + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
                callableStatement = connection.prepareCall("{CALL InvoiceAmtBudgetAmtRel(?,?,?,?,?,?,?,?,?,?,?)}");
                callableStatement.setString(1, invoiceVTO.getStatus());
                callableStatement.setString(2, invoiceVTO.getTotalAmt());
                callableStatement.setString(3, invoiceVTO.getTotalHrs());
                callableStatement.setDouble(4, invoiceVTO.getPaidAmt());
                callableStatement.setString(5, invoiceVTO.getPayType());
                callableStatement.setString(6, invoiceVTO.getCheOrTransno());
                callableStatement.setDouble(7, invoiceVTO.getBalanceAmt());
                callableStatement.setInt(8, invoiceAction.getUserSeessionId());
                callableStatement.setInt(9, invoiceVTO.getInvoiceId());
                callableStatement.setString(10, invoiceVTO.getDescription());
                System.out.println("upto here fine-->");
                callableStatement.registerOutParameter(11, Types.INTEGER);
                //  System.out.println("hello here print after prepare call parameter ");

                isExceute = callableStatement.execute();
                callableStatement.getInt(11);
                System.out.println("this is success process ...");
            } else {
                System.out.println("this is modified query..");
                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus() + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
                        + "totalhrs = " + invoiceVTO.getTotalHrs()
                        + ", description ='" + invoiceVTO.getDescription()
                        + "', modified_by=" + invoiceAction.getUserSeessionId()
                        + ",modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
            }
            if (!"paid".equalsIgnoreCase(invoiceVTO.getStatus())) {
                System.out.println("this is update query -->" + sqlquery);
                int res = statement.executeUpdate(sqlquery);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return response;
    }
}
