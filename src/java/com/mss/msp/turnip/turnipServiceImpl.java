/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.turnip;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miracle
 */
public class turnipServiceImpl implements turnipService {

    public String getDefaultVendorResourceDetails(turnipAction turnipAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        System.out.println("********************turnipServiceImpl :: getDefaultVendorResourceDetails Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT COUNT(rc.createdbyOrgId) AS resourcecount,acc.account_name,rv.req_id,"
                    + "a.req_name,a.no_of_resources  FROM req_con_rel rc"
                    + " LEFT OUTER JOIN req_ven_rel rv ON (rc.reqId = rv.req_id AND rc.createdbyOrgId = rv.ven_id) "
                    + "LEFT OUTER JOIN accounts acc ON(acc.account_id=rv.ven_id) "
                    + "LEFT OUTER JOIN acc_requirements a ON (a.requirement_id = rv.req_id) "
                    + "WHERE a.acc_id =" + turnipAction.getSessionOrgId() + " AND rc.STATUS NOT LIKE '%SOW%' "
                    + " AND DATE_FORMAT(req_st_date,'%Y')='" + turnipAction.getDashYears() + "' ";

            if (!"-1".equalsIgnoreCase(turnipAction.getDashMonths())) {
                queryString = queryString + " AND DATE_FORMAT(req_st_date,'%m')= '" + turnipAction.getDashMonths() + "'  ";
            }
            queryString = queryString + " GROUP BY a.requirement_id,rv.ven_id ORDER BY req_id,resourcecount DESC ";
            System.out.println("getDefaultVendorResourceDetails::queryDashBoard....>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                int count = 0;
                int i = resultSet.getInt("req_id");
//                count = resultSet.getInt("Forecast") + resultSet.getInt("Inprogress") + resultSet.getInt("Hold") + resultSet.getInt("Withdrawn") + resultSet.getInt("Won") + resultSet.getInt("Lost");
                resultString += resultSet.getString("req_name") + "|"
                        + resultSet.getString("account_name") + "|"
                        + resultSet.getString("resourcecount") + "|"
                        + resultSet.getString("req_id") + "|"
                        + resultSet.getString("no_of_resources") + "^";
                //                if (resultSet.getString("MONTH").equalsIgnoreCase("null")) {
                //                    resultString = "";
                //                }
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

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************turnipServiceImpl :: getDefaultVendorResourceDetails Method End*********************");
        return resultString;
    }

    public String getDefaultProjectResourceDetails(turnipAction turnipAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        System.out.println("********************turnipServiceImpl :: getDefaultVendorResourceDetails Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
//            queryString = "SELECT COUNT(rc.createdbyOrgId) AS resourcecount,GROUP_CONCAT(acc.account_name) AS ccc ,"
//                    + " rv.req_id,a.req_name,acc.account_name FROM req_con_rel rc LEFT OUTER JOIN req_ven_rel rv "
//                    + " ON (rc.reqId = rv.req_id AND rc.createdbyOrgId = rv.ven_id) LEFT OUTER JOIN"
//                    + " accounts acc ON(acc.account_id=rv.ven_id) LEFT OUTER JOIN acc_requirements a "
//                    + " ON (a.requirement_id = rv.req_id) WHERE a.acc_id ="+turnipAction.getSessionOrgId()+" AND"
//                    + " rc.STATUS NOT LIKE '%SOW%' GROUP BY a.requirement_id LIMIT 5 ";
//            if (!"-1".equalsIgnoreCase(requirementAction.getDashMonths())) {
//                queryString = queryString + " AND DATE_FORMAT(req_st_date,'%m')= '" + requirementAction.getDashMonths() + "'  ";
//            } else {
//                queryString = queryString + " GROUP BY DATE_FORMAT(req_st_date,'%m')";
//            }\
            queryString = "SELECT ap.project_id,ap.proj_name,SUM(ar.no_of_resources) as resourceCount,lrc.req_category "
                    + " FROM acc_projects ap LEFT OUTER JOIN acc_requirements ar "
                    + "ON(ar.req_category = ap.proj_primary_skillset) "
                    + "LEFT OUTER JOIN lk_req_category lrc ON(lrc.id = ar.req_category) WHERE ap.proj_type ='MP' "
                    + "AND  (ar.req_status = 'O' OR ar.req_status = 'OR') GROUP BY ap.project_id,lrc.req_category ";
//            queryString = "SELECT ap.project_id,ap.proj_name,psr.no_res, ps.NAME FROM acc_projects ap "
//                    + "LEFT OUTER JOIN proj_skill_req psr ON (ap.project_id=psr.project_id)"
//                    + " LEFT OUTER JOIN prim_skills ps ON (psr.skill_id=ps.p_id) WHERE ap.proj_type ='MP'";
            System.out.println("getDefaultVendorResourceDetails::queryDashBoard....>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                int count = 0;
//                int i = resultSet.getInt("req_id");
//                count = resultSet.getInt("Forecast") + resultSet.getInt("Inprogress") + resultSet.getInt("Hold") + resultSet.getInt("Withdrawn") + resultSet.getInt("Won") + resultSet.getInt("Lost");
                resultString += resultSet.getString("proj_name") + "|"
                        + resultSet.getString("req_category") + "|"
                        + resultSet.getString("resourceCount") + "|"
                        + resultSet.getString("project_id") + "^";
                //                        + resultSet.getString("ccc")+"^";
//                if (resultSet.getString("MONTH").equalsIgnoreCase("null")) {
//                    resultString = "";
//                }
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

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************turnipServiceImpl :: getDefaultVendorResourceDetails Method End*********************");
        return resultString;
    }

    public String getDefaultClientResourceDetails(turnipAction turnipAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        System.out.println("********************turnipServiceImpl :: getDefaultClientResourceDetails Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
//            queryString = " SELECT ar.acc_id,acc.account_name,ar.no_of_resources,lrc.req_category"
//                    + " FROM acc_requirements ar LEFT OUTER JOIN accounts acc "
//                    + " ON(acc.account_id = ar.acc_id) LEFT OUTER JOIN lk_req_category "
//                    + " lrc ON(lrc.id = ar.req_category) LEFT OUTER JOIN req_ven_rel rvr "
//                    + " ON (rvr.req_id=ar.requirement_id) WHERE rvr.STATUS = 'Active' "
//                    + " AND rvr.ven_id = " + turnipAction.getSessionOrgId()
//                    + " AND  (ar.req_status = 'O' OR ar.req_status = 'OR')" ;
            queryString = " SELECT ar.acc_id,acc.account_name,"
                    + " SUM(ar.no_of_resources) as resourceCount,lrc.req_category "
                    + " FROM acc_requirements ar LEFT OUTER JOIN accounts acc "
                    + " ON(acc.account_id = ar.acc_id) LEFT OUTER JOIN lk_req_category  "
                    + " lrc ON(lrc.id = ar.req_category) LEFT OUTER JOIN req_ven_rel "
                    + " rvr  ON (rvr.req_id=ar.requirement_id) WHERE rvr.STATUS = 'Active' "
                    + " AND rvr.ven_id = " + turnipAction.getSessionOrgId()
                    + " AND  (ar.req_status = 'O' OR ar.req_status = 'OR') "
                    + " GROUP BY acc.account_name,lrc.req_category ORDER BY acc_id,SUM(ar.no_of_resources) DESC";

            System.out.println("getDefaultClientResourceDetails::queryDashBoard....>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
//                count = resultSet.getInt("Forecast") + resultSet.getInt("Inprogress") + resultSet.getInt("Hold") + resultSet.getInt("Withdrawn") + resultSet.getInt("Won") + resultSet.getInt("Lost");
                resultString += resultSet.getString("account_name") + "|"
                        + resultSet.getString("req_category") + "|"
                        + resultSet.getString("resourceCount") + "|"
                        + resultSet.getInt("acc_id") + "^";
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

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************turnipServiceImpl :: getDefaultClientResourceDetails Method End*********************");
        return resultString;
    }

    public String getClientRequirementDetails(turnipAction turnipAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        String resultString = "";
        System.out.println("********************turnipServiceImpl :: getDefaultClientResourceDetails Method Start*********************");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT ar.acc_id,acc.account_name,ar.no_of_resources AS resourceCount,"
                    + " lrc.req_category,ar.req_skills FROM acc_requirements ar LEFT OUTER JOIN "
                    + " accounts acc ON(acc.account_id = ar.acc_id) LEFT OUTER JOIN lk_req_category  "
                    + " lrc ON(lrc.id = ar.req_category) LEFT OUTER JOIN req_ven_rel rvr  ON"
                    + " (rvr.req_id=ar.requirement_id) WHERE rvr.STATUS = 'Active' "
                    + " AND rvr.ven_id = " + turnipAction.getSessionOrgId()
                    + " AND acc.account_name LIKE '" + turnipAction.getAccountName() + "'"
                    + " AND lrc.req_category LIKE '" + turnipAction.getReqName() + "' AND "
                    + " (ar.req_status = 'O' OR ar.req_status = 'OR') ORDER BY acc_id,ar.no_of_resources DESC";

            System.out.println("getDefaultClientResourceDetails::queryDashBoard....>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
//                count = resultSet.getInt("Forecast") + resultSet.getInt("Inprogress") + resultSet.getInt("Hold") + resultSet.getInt("Withdrawn") + resultSet.getInt("Won") + resultSet.getInt("Lost");
                resultString += resultSet.getString("account_name") + "|"
                        + resultSet.getString("req_category") + "|"
                        + resultSet.getString("resourceCount") + "|"
                        + resultSet.getString("req_skills") + "^";
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

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        System.out.println("********************turnipServiceImpl :: getDefaultClientResourceDetails Method End*********************");
        return resultString;
    }

    public String getDefaultRegionResourcesDetails(turnipAction turnipAction) throws ServiceLocatorException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        CallableStatement callableStatement = null;
        String resultString = "";
        System.out.println("********************turnipServiceImpl :: getDefaultClientResourceDetails Method Start*********************");
        try {
            System.out.println("-------------------"+turnipAction.getSessionOrgId());
            connection = ConnectionProvider.getInstance().getConnection();
             callableStatement = connection.prepareCall("{call sp_turnip_vendorResourceCountGraph(?,?)}");
             callableStatement.setInt(1, turnipAction.getSessionOrgId());
             callableStatement.registerOutParameter(2, Types.VARCHAR);
             callableStatement.execute();
             resultString = callableStatement.getString(2);

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
        System.out.println("********************turnipServiceImpl :: getDefaultClientResourceDetails Method End*********************");
        return resultString;
    }
}
