/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sagAjax;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miracle
 */
public class InvoiceAjaxServiceImpl implements InvoiceAjaxService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    public String generateInvoice(InvoiceAjaxAction invoiceAjaxAction) throws ServiceLocatorException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************InvoiceAjaxServiceImpl :: generateInvoice Method Start*********************");
        boolean response = false;

        String responseFromSP = "";
        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            sqlquery = "";
            if (!invoiceAjaxAction.isCheked()) {
                callableStatement = connection.prepareCall("{CALL invoiceGeneration(?,?,?,?,?,?)}");
                System.out.println("generateInvoice :: procedure name : invoiceGeneration ");
            } else {
                callableStatement = connection.prepareCall("{CALL GenerateAllEmpInvoice(?,?,?,?,?,?)}");
                System.out.println("generateInvoice :: procedure name : GenerateAllEmpInvoice ");
            }
            callableStatement.setInt(1, invoiceAjaxAction.getInvoiceMonth());
            callableStatement.setInt(2, invoiceAjaxAction.getInvoiceYear());
            callableStatement.setString(3, invoiceAjaxAction.getInvoiceResource());
            callableStatement.setInt(4, invoiceAjaxAction.getOrgSessionId());
            callableStatement.setInt(5, invoiceAjaxAction.getUsrSessionId());
            response = callableStatement.execute();
            responseFromSP = callableStatement.getString(6);


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
            } catch (SQLException ex) {
                try {
                    throw new ServiceLocatorException(ex);
                } catch (ServiceLocatorException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        System.out.println("********************InvoiceAjaxServiceImpl :: generateInvoice Method End*********************");

        return responseFromSP;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getTotalHoursTooltip() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getTotalHoursTooltip(InvoiceAjaxAction invAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";


        String resultString = "";
        int usr_Id = 0;
        String details = "";
        int isRecordExists = 0;
        System.out.println("********************InvoiceAjaxServiceImpl :: getTotalHoursTooltip Method Start*********************");
        connection = ConnectionProvider.getInstance().getConnection();

        queryString = "SELECT u.workdate,"
                + "(subproject1hrs+subproject2hrs+subproject3hrs+subproject4hrs+subproject5hrs) AS total  FROM usrtimesheetlines u JOIN users us ON u.usr_id=us.usr_id WHERE MONTH(workdate)=" + invAction.getInvoiceMonth() + " AND YEAR(workdate)=" + invAction.getInvoiceYear() + " AND u.usr_id=" + invAction.getUsrId() + " AND (subproject1hrs+subproject2hrs+subproject3hrs+subproject4hrs+subproject5hrs)>0 ";
        System.out.println("getTotalHoursTooltip::queryString--------->" + queryString);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                details += com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("workdate")) + "|" + resultSet.getString("total") + "^";
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

        System.out.println("********************InvoiceAjaxServiceImpl :: getTotalHoursTooltip Method End*********************");

        return details;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRecreatedList() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getRecreatedList(InvoiceAjaxAction invoiceAjaxAction) throws ServiceLocatorException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String resultString = "";
        String sqlquery = "";
        String serviceTypeForView = "";
        System.out.println("********************InvoiceAjaxServiceImpl :: getRecreatedList Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            sqlquery = "select * from his_serviceagreements where his_serviceId= " + invoiceAjaxAction.getServiceId();
            if (!"All".equalsIgnoreCase(invoiceAjaxAction.getHis_serviceType())) {
                sqlquery = sqlquery + " AND his_servicetype= '" + invoiceAjaxAction.getHis_serviceType() + "'";
            }
            if (!"All".equalsIgnoreCase(invoiceAjaxAction.getHis_status())) {
                sqlquery = sqlquery + " AND his_curstatus= '" + invoiceAjaxAction.getHis_status() + "'";
            }
            System.out.println("getRecreatedList::sqlquery------>" + sqlquery);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                if ("F".equalsIgnoreCase(resultSet.getString("his_servicetype"))) {
                    serviceTypeForView = "Finder Fee";
                }
                if ("S".equalsIgnoreCase(resultSet.getString("his_servicetype"))) {
                    serviceTypeForView = "SOW";
                }
                resultString += resultSet.getString("his_servicetype") + "|" + resultSet.getString("his_serviceversion") + "|" + resultSet.getString("his_target_rate") + "|" + resultSet.getString("his_target_rate_type") + "|" + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("his_createddate")) + "|" + resultSet.getString("his_curstatus") + "|" + resultSet.getString("his_id") + "|" + serviceTypeForView + "^";

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
        System.out.println("********************InvoiceAjaxServiceImpl :: getRecreatedList Method End*********************");

        return resultString;

    }
}
