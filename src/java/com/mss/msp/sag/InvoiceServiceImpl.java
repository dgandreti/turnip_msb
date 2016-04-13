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
 * *****************************************************************************
 * Date :
 *
 * Author :miracle
 *
 * ForUse : InvoiceServiceImpl() method is used to
 *
 *
 * *****************************************************************************
 */
public class InvoiceServiceImpl implements InvoiceService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";
    private boolean isExceute;

    public List getInvoiceDetails(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************InvoiceServiceImpl :: getInvoiceDetails Method Start*********************");
        ArrayList<InvoiceVTO> listVto = new ArrayList<InvoiceVTO>();


        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if ("VC".equalsIgnoreCase(typeOfUser)) {
                sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name AS custname "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id)"
                        + "  LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE inv.frm_orgid=" + invoiceAction.getSessionOrgId() + " and invoiceyear=" + invoiceAction.getInvoiceYear();
            } else {
                sqlquery = "   SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name AS custname "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
                        + "LEFT OUTER JOIN accounts acc ON (inv.frm_orgid=acc.account_id) WHERE  inv.custorg_id=" + invoiceAction.getSessionOrgId() + " and invoiceyear=" + invoiceAction.getInvoiceYear() + " and invoicestatus !='Created' ";
            }
            sqlquery += " order by NAMES limit 100 ";
            System.out.println("getInvoiceDetails :: query string ------>" + sqlquery);

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
        System.out.println("********************InvoiceServiceImpl :: getInvoiceDetails Method End*********************");
        return listVto;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : deleteInvoice() method is used to get state names of a
     * particular country.
     *
     * *****************************************************************************
     */
    public boolean deleteInvoice(int invoiceId) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        boolean response = false;
        String sqlquery = "";
        System.out.println("********************InvoiceServiceImpl :: deleteInvoice Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            sqlquery = "delete from invoice where invoiceid=" + invoiceId;
            System.out.println("deleteInvoice::sqlquery--------->" + sqlquery);
            statement = connection.createStatement();
            response = statement.execute(sqlquery);

        } catch (SQLException ex) {

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
            } catch (SQLException ex) {
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        System.out.println("********************InvoiceServiceImpl :: deleteInvoice Method End*********************");
        return response;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doSearchInvoiceDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public List doSearchInvoiceDetails(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************InvoiceServiceImpl :: doSearchInvoiceDetails Method Start*********************");
        ArrayList<InvoiceVTO> listVto = new ArrayList<InvoiceVTO>();
        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();



            if ("VC".equalsIgnoreCase(typeOfUser)) {
                sqlquery = "SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name  "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id)"
                        + "  LEFT OUTER JOIN accounts acc ON (inv.custorg_id=acc.account_id) WHERE  inv.frm_orgid=" + invoiceAction.getSessionOrgId();
            } else {
                sqlquery = "   SELECT inv.invoiceid,inv.totalhrs,totalamt,invoicestatus,invoicemonth,"
                        + "invoiceyear,inv.usr_id,CONCAT(first_name,'.',last_name) NAMES,account_name  "
                        + "FROM invoice inv LEFT OUTER JOIN  users u ON (inv.usr_id=u.usr_id) "
                        + "LEFT OUTER JOIN accounts acc ON (inv.frm_orgid=acc.account_id) WHERE  inv.custorg_id=" + invoiceAction.getSessionOrgId() + " and invoicestatus !='Created'";
            }

            if (invoiceAction.getInvoiceMonth() != 0) {
                sqlquery += " and invoicemonth=" + invoiceAction.getInvoiceMonth();
            }
            if (invoiceAction.getInvoiceYear() != 0) {
                sqlquery += " and invoiceyear=" + invoiceAction.getInvoiceYear();
            }

            if (invoiceAction.getInvVendor() != null && invoiceAction.getInvVendor() != "") {
                sqlquery += " and account_name like '" + invoiceAction.getInvVendor() + "%'";
            }
            if (invoiceAction.getInvoiceResource() != null && invoiceAction.getInvoiceResource() != "") {
                sqlquery += " and (first_name like '%" + invoiceAction.getInvoiceResource() + "%' or last_name like '%" + invoiceAction.getInvoiceResource() + "%') ";
            }
            if (!"All".equals(invoiceAction.getInvoiceStatus())) {
                sqlquery += " and invoicestatus='" + invoiceAction.getInvoiceStatus() + "'";
            }


            sqlquery += " order by NAMES limit 100 ";
            System.out.println("doSearchInvoiceDetails::sql query invoices-------->" + sqlquery);
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
        System.out.println("********************InvoiceServiceImpl :: doSearchInvoiceDetails Method End*********************");
        return listVto;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : goInvoiceEditDetails() method is used to
     *
     *
     * *****************************************************************************
     */
    public InvoiceVTO goInvoiceEditDetails(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************InvoiceServiceImpl :: goInvoiceEditDetails Method Start*********************");

        InvoiceVTO ivto = new InvoiceVTO();

        String sqlquery = " ";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
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
            System.out.println("goInvoiceEditDetails::sqlquery------->" + sqlquery);
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
        System.out.println("********************InvoiceServiceImpl :: goInvoiceEditDetails Method End*********************");
        return ivto;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : doEditInvoiceDetatils() method is used to
     *
     * *****************************************************************************
     */
    public boolean doEditInvoiceDetatils(InvoiceVTO invoiceVTO, String typeOfUser, InvoiceAction invoiceAction) throws ServiceLocatorException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************InvoiceServiceImpl :: doEditInvoiceDetatils Method Start*********************");
        boolean response = false;

        String sqlquery = "";

        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();
            if ("VC".equalsIgnoreCase(typeOfUser)) {

                sqlquery = "UPDATE invoice SET invoicemonth=" + invoiceVTO.getInvoicemonth() + ",invoiceyear=" + invoiceVTO.getInvoiceyear()
                        + ",invoicestatus='" + invoiceVTO.getStatus() + "',totalamt=" + invoiceVTO.getTotalAmt() + ",description='" + invoiceVTO.getDescription() + "',modified_by=" + invoiceAction.getUserSeessionId() + ",modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
            } else if ("approved".equalsIgnoreCase(invoiceVTO.getStatus())) {

                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus()
                        + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
                        + "totalhrs = " + invoiceVTO.getTotalHrs() + ",  "
                        + " paymentdate = '" + com.mss.msp.util.DateUtility.getInstance().convertStringToMySQLDate1(invoiceVTO.getPaymentDate()) + "', "
                        + " description ='" + invoiceVTO.getDescription() + "' ,custapprid=" + invoiceAction.getUserSeessionId() + ", custapprdate='" + com.mss.msp.util.DateUtility.getInstance().getCurrentSQLDate() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();

            } else if ("rejected".equalsIgnoreCase(invoiceVTO.getStatus())) {

                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus()
                        + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
                        + "totalhrs = " + invoiceVTO.getTotalHrs() + ",  "
                        + " description ='" + invoiceVTO.getDescription() + "' ,custapprid=" + invoiceAction.getUserSeessionId() + ", custapprdate='" + com.mss.msp.util.DateUtility.getInstance().getCurrentSQLDate() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
            } else if ("paid".equalsIgnoreCase(invoiceVTO.getStatus())) {

                callableStatement = connection.prepareCall("{CALL InvoiceAmtBudgetAmtRel(?,?,?,?,?,?,?,?,?,?,?)}");
                System.out.println("doEditInvoiceDetatils::procedure name:InvoiceAmtBudgetAmtRel");
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

                callableStatement.registerOutParameter(11, Types.INTEGER);

                isExceute = callableStatement.execute();
                callableStatement.getInt(11);

            } else {

                sqlquery = "  UPDATE invoice SET invoicestatus = '" + invoiceVTO.getStatus() + "', totalamt = " + invoiceVTO.getTotalAmt() + ", "
                        + "totalhrs = " + invoiceVTO.getTotalHrs()
                        + ", description ='" + invoiceVTO.getDescription()
                        + "', modified_by=" + invoiceAction.getUserSeessionId()
                        + ",modified_date='" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime() + "' WHERE invoiceid=" + invoiceVTO.getInvoiceId();
            }
            if (!"paid".equalsIgnoreCase(invoiceVTO.getStatus())) {

                int res = statement.executeUpdate(sqlquery);
            }
            System.out.println("doEditInvoiceDetatils::sqlquery------->" + sqlquery);
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {

            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
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
        System.out.println("********************InvoiceServiceImpl :: doEditInvoiceDetatils Method End*********************");
        return response;
    }

    /**
     * *****************************************************************************
     * Date : 03/26/2016
     *
     * Author : Manikanta <meeralla@miraclesoft.com>
     *
     * ForUse : getOutstandingInvoiceList() method is used to get Outstanding
     * Invoices List
     *
     *
     * *****************************************************************************
     */
    public List getOutstandingInvoiceList(InvoiceAction invoiceAction, String typeOfUser) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************InvoiceServiceImpl :: getOutstandingInvoiceList Method Start*********************");

        ArrayList<InvoiceVTO> listVto = new ArrayList<InvoiceVTO>();
        String sqlquery = " ";
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            sqlquery = "SELECT invoiceid,invoicestatus,invoicemonth,invoiceyear,i.usr_id,frm_orgid,custorg_id,totalamt,"
                    + " paidAmt,balanceamt,account_name,CONCAT(first_name,'.',last_name) NAMES FROM invoice i "
                    + " LEFT OUTER JOIN users u ON (i.usr_id=u.usr_id) LEFT OUTER JOIN "
                    + " accounts acc ON (i.frm_orgid=account_id) "
                    + " WHERE invoicestatus='Submitted' AND i.custorg_id=" + invoiceAction.getSessionOrgId();

            if ("invoiceSearch".equals(invoiceAction.getSearchFlag())) {
            } else {
                sqlquery += " and invoiceyear=" + invoiceAction.getCurrentYear();
            }


            if (invoiceAction.getInvoiceMonth() != 0) {
                sqlquery += " and invoicemonth=" + invoiceAction.getInvoiceMonth();
            }
            if (invoiceAction.getInvoiceYear() != 0) {
                sqlquery += " and invoiceyear=" + invoiceAction.getInvoiceYear();
            }

            if (invoiceAction.getInvVendor() != null && invoiceAction.getInvVendor() != "") {
                sqlquery += " and account_name like '" + invoiceAction.getInvVendor() + "%'";
            }
            if (invoiceAction.getInvoiceResource() != null && invoiceAction.getInvoiceResource() != "") {
                sqlquery += " and (first_name like '%" + invoiceAction.getInvoiceResource() + "%' or last_name like '%" + invoiceAction.getInvoiceResource() + "%') ";
            }



            sqlquery += " order by NAMES limit 100 ";

            System.out.println("getOutstandingInvoiceList::sqlquery----->" + sqlquery);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                InvoiceVTO ivto = new InvoiceVTO();
                ivto.setInvoiceId(resultSet.getInt("invoiceid"));
                ivto.setUsr_id(resultSet.getInt("usr_id"));

                ivto.setUserName(resultSet.getString("NAMES"));
                ivto.setCustName(resultSet.getString("account_name"));
                ivto.setInvoiceDate(DataUtility.getInstance().getMonthNameByNumber(resultSet.getInt("invoicemonth")));

                ivto.setInvoiceyear(resultSet.getInt("invoiceyear"));
                ivto.setTotalAmt(resultSet.getString("totalamt"));
                ivto.setStatus(resultSet.getString("invoicestatus"));
                ivto.setBalanceAmt(resultSet.getDouble("balanceamt"));
                ivto.setPaidAmt(resultSet.getDouble("paidAmt"));
                ivto.setGridDownload(sqlquery);
                listVto.add(ivto);
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
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        System.out.println("********************InvoiceServiceImpl :: getOutstandingInvoiceList Method End*********************");
        return listVto;
    }
}
