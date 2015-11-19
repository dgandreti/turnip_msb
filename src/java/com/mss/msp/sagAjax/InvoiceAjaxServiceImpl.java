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

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public String generateInvoice(InvoiceAjaxAction invoiceAjaxAction) throws ServiceLocatorException {

        boolean response = false;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        String responseFromSP = "";
        String sqlquery = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            sqlquery = "";

            System.out.println("this is for checked " + invoiceAjaxAction.isCheked());
            System.out.println("this is for month " + invoiceAjaxAction.getInvoiceMonth());
            System.out.println("this is for year " + invoiceAjaxAction.getInvoiceYear());
            System.out.println("this is for Resource Name " + invoiceAjaxAction.getInvoiceResource());
            System.out.println("this is user session id " + invoiceAjaxAction.getUsrSessionId());
            System.out.println("this is org session id " + invoiceAjaxAction.getOrgSessionId());

            if (!invoiceAjaxAction.isCheked()) {
                callableStatement = connection.prepareCall("{CALL invoiceGeneration(?,?,?,?,?,?)}");
//            response = statement.execute(sqlquery);
            } else {
                callableStatement = connection.prepareCall("{CALL GenerateAllEmpInvoice(?,?,?,?,?,?)}");
            }
            callableStatement.setInt(1, invoiceAjaxAction.getInvoiceMonth());
            callableStatement.setInt(2, invoiceAjaxAction.getInvoiceYear());
            callableStatement.setString(3, invoiceAjaxAction.getInvoiceResource());
            callableStatement.setInt(4, invoiceAjaxAction.getOrgSessionId());
            callableStatement.setInt(5, invoiceAjaxAction.getUsrSessionId());
            response = callableStatement.execute();
            responseFromSP = callableStatement.getString(6);
            System.out.println("this is printing is executed -->" + response);
            System.out.println("this is printing response from sp-->" + responseFromSP);

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

        return responseFromSP;
    }
    
        public String getTotalHoursTooltip(InvoiceAjaxAction invAction) throws ServiceLocatorException {
        String resultString = "";
        int usr_Id = 0;
        String details = "";
        int isRecordExists = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
         System.out.println("invAction.getUsrId()"+invAction.getUsrId());
        String queryString ="SELECT u.workdate,"
                + "(subproject1hrs+subproject2hrs+subproject3hrs+subproject4hrs+subproject5hrs) AS total  FROM usrtimesheetlines u JOIN users us ON u.usr_id=us.usr_id WHERE MONTH(workdate)="+invAction.getInvoiceMonth()+" AND YEAR(workdate)="+invAction.getInvoiceYear()+" AND u.usr_id="+invAction.getUsrId()+" AND (subproject1hrs+subproject2hrs+subproject3hrs+subproject4hrs+subproject5hrs)>0 ";
 
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                details +=com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("workdate"))+"|"+resultSet.getString("total")+"^";
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
                throw new ServiceLocatorException(ex);
            }
        }
       
     
     return details;
        
    }
    
     public String getRecreatedList(InvoiceAjaxAction invoiceAjaxAction)throws ServiceLocatorException{
        ResultSet resultSet = null;
        String resultString = "";
        String sqlquery = "";
        String serviceTypeForView="";
        
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("this is for checked " + invoiceAjaxAction.getServiceId());
            sqlquery = "select * from his_serviceagreements where his_serviceId= "+invoiceAjaxAction.getServiceId();
            if(!"All".equalsIgnoreCase(invoiceAjaxAction.getHis_serviceType())){
               sqlquery = sqlquery + " AND his_servicetype= '"+invoiceAjaxAction.getHis_serviceType()+"'"; 
            }
            if(!"All".equalsIgnoreCase(invoiceAjaxAction.getHis_status())){
               sqlquery = sqlquery + " AND his_curstatus= '"+invoiceAjaxAction.getHis_status()+"'"; 
            }
            System.out.println("this is printing is executed -->" + sqlquery );
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                if("F".equalsIgnoreCase(resultSet.getString("his_servicetype"))){
                serviceTypeForView="Finder Fee";   
                }
                if("S".equalsIgnoreCase(resultSet.getString("his_servicetype"))){
                 serviceTypeForView="SOW";    
                }
                resultString += resultSet.getString("his_servicetype") + "|" + resultSet.getString("his_serviceversion") + "|" + resultSet.getString("his_target_rate")+ "|" +resultSet.getString("his_target_rate_type") + "|" + com.mss.msp.util.DateUtility.getInstance().convertToviewFormatInDash(resultSet.getString("his_createddate")) + "|" + resultSet.getString("his_curstatus") +  "|" + resultSet.getString("his_id")  +  "|" + serviceTypeForView + "^";

            }
          //  System.out.println("this is printing is executed -->" + response);
            System.out.println("this is printing response from sp-->" + resultString);

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

        return resultString;
         
     }
}
