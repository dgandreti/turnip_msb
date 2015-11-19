/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag.sow;

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
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author miracle
 */
public class SOWServiceImpl implements SOWService {

    private Connection connection;
    private Statement statement;

    public List getSowDetails(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        ArrayList<SowVTO> listVto = new ArrayList<SowVTO>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        int migId;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>TYPE OF USER>>>>>>>>>>>>>" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            if ("VC".equalsIgnoreCase(sowAction.getTypeOfUser())) {
                sqlquery = "SELECT * from vwCustomerSAG where createdOrgId=" + sowAction.getSessionOrgId() + " ";
            } else {
                sqlquery = "SELECT * from vwCustomerSAG where acc_id=" + sowAction.getSessionOrgId() + " ";
            }
            System.out.println(">>>>QueryString>>>>>" + sqlquery);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                SowVTO sowVto = new SowVTO();
                sowVto.setRequirementId(resultSet.getString("reqId"));
                sowVto.setRequirementName(resultSet.getString("req_name"));
                // sowVto.setCustomerName(resultSet.getString("account_name"));
                sowVto.setConsultantId(resultSet.getString("usr_id"));
                migId = com.mss.msp.util.DataSourceDataProvider.getInstance().doCheckEmailExistsOrNot(resultSet.getInt("usr_id"), resultSet.getInt("reqId"));
                if (migId > 0) {
                    sowVto.setMigrateStatus("Yes");
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>MIGRATE STATUS>>>>"+sowVto.getMigrateStatus());
                } else {
                    sowVto.setMigrateStatus("No");
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>MIGRATE STATUS>>>>"+sowVto.getMigrateStatus());
                }
                sowVto.setConsultantName(resultSet.getString("consultantName"));
                sowVto.setReqCreatedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("createdDate")));
                //sowVto.setTechReviewDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("tech_review_date")));
                sowVto.setVendorId(resultSet.getString("vendor_id"));
                sowVto.setCustomerId(resultSet.getString("acc_id"));

                sowVto.setCustomerName(resultSet.getString("customerName"));
                sowVto.setVendorName(resultSet.getString("vendorName"));
                sowVto.setReviewStatus(resultSet.getString("curstatus"));
                if ("F".equalsIgnoreCase(resultSet.getString("servicetype"))) {
                    sowVto.setServiceTypeForView("Finder Fee");
                }
                if ("S".equalsIgnoreCase(resultSet.getString("servicetype"))) {
                    sowVto.setServiceTypeForView("SOW");
                }
                sowVto.setServiceType(resultSet.getString("servicetype"));
                sowVto.setServiceVersion(resultSet.getString("serviceversion"));
                sowVto.setServiceId(resultSet.getInt("serviceid"));
                sowVto.setRateSalary(resultSet.getString("target_rate"));
                sowVto.setSubmittedPayRateType(resultSet.getString("target_rate_type"));
                listVto.add(sowVto);
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

    public List getSOWSearchResults(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        ArrayList<SowVTO> listVto = new ArrayList<SowVTO>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        int migId;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>TYPE OF USER>>>>>>>>>>>>>" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            if ("VC".equalsIgnoreCase(sowAction.getTypeOfUser())) {
                queryString = "SELECT * from vwCustomerSAG where createdOrgId=" + sowAction.getSessionOrgId() + " ";
            } else {
                queryString = "SELECT * from vwCustomerSAG where acc_id=" + sowAction.getSessionOrgId() + " ";
            }
            if (!"".equals(sowAction.getConsultantName())) {

                queryString = queryString + " AND consultantName LIKE '%" + sowAction.getConsultantName() + "%' ";

            }
            if (!"".equals(sowAction.getRequirementName())) {
                queryString = queryString + " AND req_name LIKE '%" + sowAction.getRequirementName() + "%' ";
            }
            if ("AC".equalsIgnoreCase(sowAction.getTypeOfUser())) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>customer logged in");
                if (!"".equals(sowAction.getVendorName())) {
                    queryString = queryString + " AND vendorName LIKE '%" + sowAction.getVendorName() + "%' ";
                }
            } else if ("VC".equalsIgnoreCase(sowAction.getTypeOfUser())) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>vendor logged in");
                if (!"".equals(sowAction.getCustomerName())) {
                    queryString = queryString + " AND customerName LIKE '%" + sowAction.getCustomerName() + "%' ";
                }
            } else {
            }
            if (!"-1".equalsIgnoreCase(sowAction.getStatus())) {
                queryString = queryString + " AND curstatus ='" + sowAction.getStatus() + "'  ";
            }
            if (!"-1".equalsIgnoreCase(sowAction.getSOWSelectValue())) {
                queryString = queryString + " AND servicetype='" + sowAction.getSOWSelectValue() + "'";
            }
            System.out.println(">>>>QueryString>>>>>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                SowVTO sowVto = new SowVTO();
                sowVto.setRequirementId(resultSet.getString("reqId"));
                sowVto.setRequirementName(resultSet.getString("req_name"));
                // sowVto.setCustomerName(resultSet.getString("account_name"));
                sowVto.setConsultantId(resultSet.getString("usr_id"));
                   migId = com.mss.msp.util.DataSourceDataProvider.getInstance().doCheckEmailExistsOrNot(resultSet.getInt("usr_id"), resultSet.getInt("reqId"));
                if (migId > 0) {
                    sowVto.setMigrateStatus("Yes");
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>MIGRATE STATUS>>>>"+sowVto.getMigrateStatus());
                } else {
                    sowVto.setMigrateStatus("No");
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>MIGRATE STATUS>>>>"+sowVto.getMigrateStatus());
                }
                sowVto.setConsultantName(resultSet.getString("consultantName"));
                sowVto.setReqCreatedDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("createdDate")));
                //sowVto.setTechReviewDate(com.mss.msp.util.DateUtility.getInstance().convertDateToViewInDashformat(resultSet.getDate("tech_review_date")));
                sowVto.setVendorId(resultSet.getString("vendor_id"));
                sowVto.setCustomerId(resultSet.getString("acc_id"));

                sowVto.setCustomerName(resultSet.getString("customerName"));
                sowVto.setVendorName(resultSet.getString("vendorName"));
                sowVto.setReviewStatus(resultSet.getString("curstatus"));
                if ("F".equalsIgnoreCase(resultSet.getString("servicetype"))) {
                    sowVto.setServiceTypeForView("Finder Fee");
                }
                if ("S".equalsIgnoreCase(resultSet.getString("servicetype"))) {
                    sowVto.setServiceTypeForView("SOW");
                }
                sowVto.setServiceType(resultSet.getString("servicetype"));
                sowVto.setServiceVersion(resultSet.getString("serviceversion"));
                sowVto.setServiceId(resultSet.getInt("serviceid"));
                // sowVto.setRateSalary(resultSet.getString("target_rate"));
                sowVto.setRateSalary(resultSet.getString("target_rate"));
                sowVto.setSubmittedPayRateType(resultSet.getString("target_rate_type"));


                listVto.add(sowVto);
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

    public String doAddUpdateSOWDetails(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        String result = "";
        boolean isExceute = false;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL sowAddUpdate(?,?,?,?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, sowAction.getUserSeessionId());
            System.out.println(">>>>>>>>>>>1");
            callableStatement.setInt(2, Integer.parseInt(sowAction.getConsultantId()));
            System.out.println(">>>>>>>>>>>2");
            callableStatement.setInt(3, Integer.parseInt(sowAction.getCustomerId()));
            System.out.println(">>>>>>>>>>>3");
            callableStatement.setInt(4, Integer.parseInt(sowAction.getVendorId()));
            System.out.println(">>>>>>>>>>>4");
            callableStatement.setInt(5, Integer.parseInt(sowAction.getRequirementId()));
            System.out.println(">>>>>>>>>>>5 " + sowAction.getRateSalary());
            callableStatement.setDouble(6, Double.parseDouble(sowAction.getRateSalary()));
            System.out.println(">>>>>>>>>>>6");
            callableStatement.setString(7, sowAction.getStatus());
            System.out.println(">>>>>>>>>>>7");
            callableStatement.setInt(8, Integer.parseInt(sowAction.getPayTerms()));
            System.out.println(">>>>>>>>>>>8");
            callableStatement.setString(9, sowAction.getVendorComments());
            System.out.println(">>>>>>>>>>>9");
            callableStatement.setString(10, sowAction.getCustomerComments());
            System.out.println(">>>>>>>>>>>10");
            callableStatement.registerOutParameter(11, Types.VARCHAR);
            System.out.println(">>>>>>>>>>>11");
            isExceute = callableStatement.execute();
            result = callableStatement.getString(11);
            System.out.println("result is>>>>>>>" + result);
            if (result.equalsIgnoreCase("Exist")) {
                sowAction.setResultMessage("Record Updated Successfully!");
                System.out.println("****************** in impl result after Procedure:::::::::" + result);
            } else if (result.equalsIgnoreCase("Success")) {
                sowAction.setResultMessage("Record Inserted Successfully!");
                System.out.println("****************** in impl result after Procedure:::::::::" + result);
            }
        } catch (SQLException ex) {
            System.out.println("doAddUpdateSOW>>>>" + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(">>>Exception>>>" + e.getMessage());
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
                    System.out.println("errror-->" + ex1.getMessage());
                    ex1.printStackTrace();
                }
            }
        }
        return result;
    }

    public String getSOWEditDetails(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>TYPE OF USER>>>>>>>>>>>>>" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            // System.out.println(">" + sowAction.getConsultantId() + ">" + sowAction.getRequirementId() + ">" + sowAction.getConsultantName() + ">" + sowAction.getCustomerId());
            queryString = "SELECT usr_id,req_Id,customer_id,vendor_id,curstatus,customercomments,vendorcomments,netterms,target_rate,target_rate_type,security_check,est_hrs,ot_flag,est_ot_hrs,min_workhrs,"
                    + " max_workhrs,shift_type,travelrequired,travelamtpercentage,customerdivision,location1,location2,serviceversion,submitted_rate,submitted_rate_type,deduction_amount,trans_id,trans_no,trans_amount FROM serviceagreements WHERE serviceid=" + sowAction.getServiceId();//+ " AND req_id=" + sowAction.getRequirementId();

            System.out.println(">>>>QueryString>>>>>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                sowAction.setCustomerComments(resultSet.getString("customercomments"));
                sowAction.setVendorComments(resultSet.getString("vendorcomments"));
                sowAction.setNetTerms(resultSet.getString("netterms"));
                sowAction.setSubmittedPayRate(resultSet.getString("target_rate"));
                sowAction.setSubmittedPayRateType(resultSet.getString("target_rate_type"));
                if ("Y".equalsIgnoreCase(resultSet.getString("security_check"))) {
                    sowAction.setSecurityCheck("true");
                } else {
                    sowAction.setSecurityCheck("false");
                }
                if ("Y".equalsIgnoreCase(resultSet.getString("ot_flag"))) {
                    sowAction.setOtFlag("true");
                } else {
                    sowAction.setOtFlag("false");
                }
                if ("Y".equalsIgnoreCase(resultSet.getString("travelrequired"))) {
                    sowAction.setTravelRequired("true");
                } else {
                    sowAction.setTravelRequired("false");
                }
                sowAction.setEstHrs(resultSet.getString("est_hrs"));
                sowAction.setEstOtHrs(resultSet.getString("est_ot_hrs"));
                sowAction.setMinWorkhrs(resultSet.getString("min_workhrs"));
                sowAction.setMaxWorkhrs(resultSet.getString("max_workhrs"));
                sowAction.setShiftType(resultSet.getString("shift_type"));
                sowAction.setTravelAmtPercentage(resultSet.getString("travelamtpercentage"));
                sowAction.setCustomerDivision(resultSet.getString("customerdivision"));
                sowAction.setLocationOne(resultSet.getString("location1"));
                sowAction.setLocationTwo(resultSet.getString("location2"));
                sowAction.setConsultantId(resultSet.getString("usr_id"));
                sowAction.setRequirementId(resultSet.getString("req_Id"));
                sowAction.setCustomerId(resultSet.getString("customer_id"));
                sowAction.setVendorId(resultSet.getString("vendor_id"));
                sowAction.setStatus(resultSet.getString("curstatus"));
                System.out.println("deduction_amount-->" + resultSet.getString("deduction_amount"));
                //  System.out.println("deduction_amount in-->" +Double.parseDouble(new DecimalFormat("#0.00").format(resultSet.getDouble("deduction_amount"))));
                sowAction.setDeductionAmt(resultSet.getString("deduction_amount"));
                //new DecimalFormat("#0.00");
                // sowAction.setDeductionAmt(Double.parseDouble(resultSet.getString("deduction_amount")));
                System.out.println("deduction_amount Getter" + sowAction.getDeductionAmt());
                sowAction.setTargetRate(resultSet.getString("submitted_rate"));
                sowAction.setTargetRateType(resultSet.getString("submitted_rate_type"));
                sowAction.setServiceVersion(Double.parseDouble(resultSet.getString("serviceversion")));

                sowAction.setTransId(resultSet.getString("trans_id"));
                sowAction.setTransNo(resultSet.getString("trans_no"));
                sowAction.setTransAmt(resultSet.getString("trans_amount"));

                System.out.println("------------------>" + resultSet.getString("serviceversion"));
                System.out.println("-------------------->" + Double.parseDouble(resultSet.getString("serviceversion")));
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
        return resultString;
    }

    public int doAddSOWAttachment(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN attachment IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        int result = 0;
        try {
            String fpath = sowAction.getFilesPath();
            StringTokenizer st = new StringTokenizer(fpath);
            StringTokenizer st2 = new StringTokenizer(fpath, "\\");
            String finalPath = "";
            while (st2.hasMoreElements()) {
                //System.out.println(".............>>>>>>>>>>>"+st2.nextElement());
                finalPath = finalPath + st2.nextElement() + "\\" + "\\";
            }

            System.out.println(">>>>Length>>>" + finalPath.length());
            System.out.println(">>>>After removing last 2 chars>>>" + finalPath.substring(0, finalPath.length() - 2));
            sowAction.setFilesPath(finalPath.substring(0, finalPath.length() - 2));
            System.out.println("values in impl are:::::::::::" + sowAction.getConsultantId() + " " + sowAction.getFileFileName() + " " + sowAction.getFilesPath());
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "INSERT INTO "
                    + "acc_rec_attachment(object_id,object_type,attachment_name,attachment_path,STATUS,opp_created_by) "
                    + "VALUES(" + sowAction.getConsultantId() + ",'SOW'" + ",'" + sowAction.getFileFileName() + "'" + ",'" + sowAction.getFilesPath() + "'" + ",'Active'," + sowAction.getUserSeessionId() + ")";

            System.out.println(">>>>QueryString>>>>>" + queryString);
            statement = connection.createStatement();
            result = statement.executeUpdate(queryString);

        } catch (SQLException ex) {
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
        return result;
    }

    public List getSOWAttachments(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        ArrayList<SowVTO> listVto = new ArrayList<SowVTO>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>TYPE OF USER>>>>>>>>>>>>>" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            sqlquery = "SELECT acc_attachment_id,object_id,CONCAT(u.first_name,'.',u.last_name) AS NAME,"
                    + "attachment_path,attachment_name,CONCAT(up.first_name,'.',up.last_name) AS uploadedBy "
                    + "FROM acc_rec_attachment "
                    + "LEFT OUTER JOIN users u ON(u.usr_id=object_id) "
                    + "LEFT OUTER JOIN users up ON(up.usr_id=opp_created_by) "
                    + "WHERE object_type='SOW' "
                    + "AND object_id=" + sowAction.getConsultantId();

            System.out.println(">>>>QueryString>>>>>" + sqlquery);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlquery);
            while (resultSet.next()) {
                SowVTO sowVto = new SowVTO();
                sowVto.setConsultantId(resultSet.getString("object_id"));
                sowVto.setConsultantName(resultSet.getString("NAME"));
                sowVto.setSowAttachmentName(resultSet.getString("attachment_name"));
                sowVto.setSowAttachmentPath(resultSet.getString("attachment_path"));
                sowVto.setSowAttachmentUploadedBy(resultSet.getString("uploadedBy"));
                sowVto.setSowAttachmentId(resultSet.getInt("acc_attachment_id"));
                listVto.add(sowVto);
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

    public String doInsertSAGRecord(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        String result = "";
        boolean isExceute = false;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>TYPE OF USER>>>>>>>>>>>>>" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            callableStatement = connection.prepareCall("{CALL sp_doInsertSAGRecord(?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, sowAction.getUserSeessionId());
            System.out.println(">>>>>>>>>>>1");
            callableStatement.setInt(2, Integer.parseInt(sowAction.getConsultantId()));
            System.out.println(">>>>>>>>>>>2");
//            if ("AC".equalsIgnoreCase(sowAction.getTypeOfUser())) {
//                callableStatement.setInt(3, sowAction.getSessionOrgId());
//            }else{
//                callableStatement.setInt(3, Integer.parseInt(sowAction.getCustomerId()));
//            }
//            System.out.println(">>>>>>>>>>>3");
//            if ("VC".equalsIgnoreCase(sowAction.getTypeOfUser())) {
//                callableStatement.setInt(4, sowAction.getSessionOrgId());
//            }else{
//                callableStatement.setInt(4, Integer.parseInt(sowAction.getVendorId()));
//            }
//            System.out.println(">>>>>>>>>>>4");
            callableStatement.setInt(3, Integer.parseInt(sowAction.getRequirementId()));
            System.out.println(">>>>>>>>>>>5 " + sowAction.getRateSalary());
            callableStatement.setDouble(4, Double.parseDouble(sowAction.getRateSalary()));
            System.out.println(">>>>>>>>>>>6");
            callableStatement.setString(5, sowAction.getSowFlagValue());
            System.out.println(">>>>>>>>>>>7");
            callableStatement.setString(6, sowAction.getTypeOfUser());
            System.out.println(">>>>>>>>>>>8");

            callableStatement.registerOutParameter(7, Types.VARCHAR);
            System.out.println(">>>>>>>>>>>9");
            isExceute = callableStatement.execute();
            result = callableStatement.getString(7);
            System.out.println("result is>>>>>>>" + result);
            if (result.equalsIgnoreCase("Denied")) {
                result = "Updated";
                System.out.println("****************** in impl result after Procedure:::::::::" + result);
            } else if (result.equalsIgnoreCase("Insert")) {
                result = "Inserted";
                System.out.println("****************** in impl result after Procedure:::::::::" + result);
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
        return result;
    }

    public int SOWSaveOrSubmit(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println("================================IN IMPL============================");
        CallableStatement callableStatement = null;
        // PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlquery = "";
        int result = 0;
        boolean isExceute = false;
        try {
            System.out.println("==========" + sowAction.getServiceVersion() + "==================TYPE OF USER===========================" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("****************** ENTERED INTO THE TRY BLOCK *******************");
            if (sowAction.getSOWFlag() == 1) {
                callableStatement = connection.prepareCall("{CALL spSOWOrFinderFeeUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            } else {
                callableStatement = connection.prepareCall("{CALL spSOWOrFinderFeeRecreation(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            }
            System.out.println("8888888888888888888888888888888888888----------------------"
                    + new java.text.DecimalFormat("0.00").format(sowAction.getServiceVersion() + 0.1));
            String i = Double.toString(sowAction.getServiceVersion() + 0.1).substring(0, 3);
            System.out.println("-------------->" + i + "--------------------------->" + sowAction.getSOWFlag());
            System.out.println("--------------------->" + sowAction.getSOWStatus());
            callableStatement.setString(1, sowAction.getSOWStatus());
            if ("Submitted".equalsIgnoreCase(sowAction.getSOWStatus())) {
                callableStatement.setString(2, i);
            } else {
                callableStatement.setString(2, Double.toString(sowAction.getServiceVersion()));
            }
            callableStatement.setString(3, sowAction.getNetTerms());
            callableStatement.setString(4, sowAction.getSubmittedPayRate().replaceAll(",", ""));
            callableStatement.setString(5, sowAction.getSubmittedPayRateType());
            callableStatement.setString(6, sowAction.getCustomerComments());
            callableStatement.setString(7, sowAction.getVendorComments());
            callableStatement.setInt(8, sowAction.getUserSeessionId());
            callableStatement.setTimestamp(9, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            if ("true".equalsIgnoreCase(sowAction.getSecurityCheck())) {
                callableStatement.setString(10, "Y");
            } else {
                callableStatement.setString(10, "N");
            }
            callableStatement.setString(11, sowAction.getEstHrs());
            if ("true".equalsIgnoreCase(sowAction.getOtFlag())) {
                callableStatement.setString(12, "Y");
            } else {
                callableStatement.setString(12, "N");
            }
            callableStatement.setString(13, sowAction.getEstOtHrs());
            callableStatement.setString(14, sowAction.getMinWorkhrs());
            callableStatement.setString(15, sowAction.getMaxWorkhrs());
            callableStatement.setString(16, sowAction.getShiftType());
            if ("true".equalsIgnoreCase(sowAction.getTravelRequired())) {
                callableStatement.setString(17, "Y");
            } else {
                callableStatement.setString(17, "N");
            }
            callableStatement.setString(18, sowAction.getTravelAmtPercentage());
            callableStatement.setString(19, sowAction.getCustomerDivision());
            callableStatement.setString(20, sowAction.getLocationOne());
            callableStatement.setString(21, sowAction.getLocationTwo());
            if ("CO".equalsIgnoreCase(sowAction.getServiceType())) {
                callableStatement.setString(22, "S");
            } else {
                callableStatement.setString(22, "F");
            }
            if (sowAction.getSOWFlag() == 1) {
                callableStatement.setInt(23, sowAction.getServiceId());
            } else {
                callableStatement.setInt(23, sowAction.getHis_id());
                System.out.println("hhhhhhhhhhhhhhhhhhh--------------------" + sowAction.getHis_id());
            }
            callableStatement.setString(24, sowAction.getTransId());
            callableStatement.setString(25, sowAction.getTransAmt());
            callableStatement.setString(26, sowAction.getTransNo());
            callableStatement.setString(27, sowAction.getTypeOfUser());
            callableStatement.setString(28, sowAction.getDeductionAmt());
            callableStatement.registerOutParameter(29, java.sql.Types.INTEGER);
            isExceute = callableStatement.execute();
            result = callableStatement.getInt(29);
            if (result > 0) {
                sowAction.setResultMessage("Record Updated Successfully!");
                System.out.println("****************** in impl result after Procedure:::::::::" + result);
            } else if (result == 0) {
                sowAction.setResultMessage("Record not Updated Successfully!");
                System.out.println("****************** in impl result after Procedure:::::::::" + result);
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
        return result;
    }

    public String sowRecreateEdit(SOWAction sowAction) throws ServiceLocatorException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>IN IMPL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>TYPE OF USER>>>>>>>>>>>>>" + sowAction.getTypeOfUser());
            connection = ConnectionProvider.getInstance().getConnection();
            // System.out.println(">" + sowAction.getConsultantId() + ">" + sowAction.getRequirementId() + ">" + sowAction.getConsultantName() + ">" + sowAction.getCustomerId());
            queryString = "SELECT his_usr_id,his_req_Id,his_customer_id,his_vendor_id,his_curstatus,his_customercomments,his_vendorcomments,his_netterms,his_target_rate,his_target_rate_type,his_security_check,his_est_hrs,his_ot_flag,his_est_ot_hrs,his_min_workhrs,"
                    + " his_max_workhrs,his_shift_type,his_travelrequired,his_travelamtpercentage,his_customerdivision,his_location1,his_location2,his_serviceversion,his_servicetype,his_submitted_rate,his_submitted_rate_type,his_deduction_amount FROM his_serviceagreements WHERE his_id=" + sowAction.getHis_id();//+ " AND req_id=" + sowAction.getRequirementId();

            System.out.println(">>>>QueryString>>>>>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                sowAction.setCustomerComments(resultSet.getString("his_customercomments"));
                sowAction.setVendorComments(resultSet.getString("his_vendorcomments"));
                sowAction.setNetTerms(resultSet.getString("his_netterms"));
                sowAction.setTargetRate(resultSet.getString("his_submitted_rate"));
                sowAction.setTargetRateType(resultSet.getString("his_submitted_rate_type"));
                if ("Y".equalsIgnoreCase(resultSet.getString("his_security_check"))) {
                    sowAction.setSecurityCheck("true");
                } else {
                    sowAction.setSecurityCheck("false");
                }
                if ("Y".equalsIgnoreCase(resultSet.getString("his_ot_flag"))) {
                    sowAction.setOtFlag("true");
                } else {
                    sowAction.setOtFlag("false");
                }
                if ("Y".equalsIgnoreCase(resultSet.getString("his_travelrequired"))) {
                    sowAction.setTravelRequired("true");
                } else {
                    sowAction.setTravelRequired("false");
                }
                sowAction.setEstHrs(resultSet.getString("his_est_hrs"));
                sowAction.setEstOtHrs(resultSet.getString("his_est_ot_hrs"));
                sowAction.setMinWorkhrs(resultSet.getString("his_min_workhrs"));
                sowAction.setMaxWorkhrs(resultSet.getString("his_max_workhrs"));
                sowAction.setShiftType(resultSet.getString("his_shift_type"));
                sowAction.setTravelAmtPercentage(resultSet.getString("his_travelamtpercentage"));
                sowAction.setCustomerDivision(resultSet.getString("his_customerdivision"));
                sowAction.setLocationOne(resultSet.getString("his_location1"));
                sowAction.setLocationTwo(resultSet.getString("his_location2"));
                sowAction.setConsultantId(resultSet.getString("his_usr_id"));
                sowAction.setRequirementId(resultSet.getString("his_req_Id"));
                sowAction.setCustomerId(resultSet.getString("his_customer_id"));
                sowAction.setVendorId(resultSet.getString("his_vendor_id"));
                sowAction.setStatus(resultSet.getString("his_curstatus"));
                sowAction.setServiceType(resultSet.getString("his_servicetype"));
                sowAction.setServiceVersion(Double.parseDouble(resultSet.getString("his_serviceversion")));
                sowAction.setDeductionAmt(resultSet.getString("his_deduction_amount"));
                sowAction.setSubmittedPayRate(resultSet.getString("his_target_rate"));
                sowAction.setSubmittedPayRateType(resultSet.getString("his_target_rate_type"));
                System.out.println("------------------>" + resultSet.getString("his_serviceversion"));
                System.out.println("-------------------->" + Double.parseDouble(resultSet.getString("his_serviceversion")));
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
        return resultString;

    }
}
