/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauth;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public class AccAuthServiceImpl implements AccAuthServices {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAccAuthrization() method is used to
     *
     *
     * *****************************************************************************
     */
    public List getAccAuthrization(AccAuthAction accAuthAction) throws ServiceLocatorException {
        System.out.println("********************AccAuthServiceImpl :: getAccAuthrization Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList<AccauthVTO> searchklist = new ArrayList<AccauthVTO>();
        try {
            queryString = "SELECT `action_id`, `action_name`, `status`, `description` FROM `servicebay`.`ac_action` where status='" + accAuthAction.getStatus() + "' LIMIT 0,150  ";
            System.out.println("getAccAuthrization::queryString----->" + queryString);

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                AccauthVTO accauthVTO = new AccauthVTO();
                accauthVTO.setAction_id(resultSet.getInt("action_id"));
                accauthVTO.setAction_name(resultSet.getString("action_name"));
                accauthVTO.setStatus(resultSet.getString("status"));
                accauthVTO.setDescription(resultSet.getString("description"));

                searchklist.add(accauthVTO);

            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
        System.out.println("********************AccAuthServiceImpl :: getAccAuthrization Method End*********************");
        return searchklist;
    }

    public List searchAccAuthorization(AccAuthAction accAuthAction) throws ServiceLocatorException {
        System.out.println("********************AccAuthServiceImpl :: searchAccAuthorization Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList<AccauthVTO> searchklist = new ArrayList<AccauthVTO>();
        try {
            queryString = "SELECT `action_id`, `action_name`, `status`, `description` FROM `servicebay`.`ac_action` where  1=1  ";
            System.out.println("getAccAuthrization::queryString------>" + queryString);

            if (accAuthAction.getStatus() != null) {

                if ("All".equals(accAuthAction.getStatus())) {
                    queryString = queryString + " and status like '%%'  ";
                } else {
                    queryString = queryString + " and status= '" + accAuthAction.getStatus() + "'  ";
                }
            }
            if (accAuthAction.getActionName() != null || !"".equals(accAuthAction.getActionName())) {

                queryString = queryString + " and  action_name LIKE '%" + accAuthAction.getActionName() + "%' ";

            }
            queryString = queryString + " LIMIT 0,150";
            System.out.println("searchAccAuthorization::queryString-------->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                AccauthVTO accauthVTO = new AccauthVTO();
                accauthVTO.setAction_id(resultSet.getInt("action_id"));
                accauthVTO.setAction_name(resultSet.getString("action_name"));
                accauthVTO.setStatus(resultSet.getString("status"));
                accauthVTO.setDescription(resultSet.getString("description"));

                searchklist.add(accauthVTO);
                System.out.println("in while" + searchklist.size());
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
        System.out.println("********************AccAuthServiceImpl :: searchAccAuthorization Method End*********************");
        return searchklist;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : searchActionResources() method is used to
     *
     *
     * *****************************************************************************
     */
    public List searchActionResources(AccAuthAction accAuthAction) throws ServiceLocatorException {
        System.out.println("********************AccAuthServiceImpl :: searchActionResources Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        ArrayList<AccauthVTO> searchklist = new ArrayList<AccauthVTO>();
        try {
            queryString = "SELECT a.id,block_flag,a.action_id,a.STATUS,a.description,CASE a.org_id WHEN 0 THEN 'All'  ELSE account_name END AS account_name,role_name,org_type,action_name,role_id,groupid FROM ac_action aa LEFT OUTER JOIN  ac_resources a ON(aa.action_id=a.action_id) LEFT OUTER JOIN accounts "
                    + " ON(a.org_id=accounts.account_id) LEFT OUTER JOIN roles ON(a.usr_role_id=roles.role_id)"
                    + " LEFT OUTER JOIN org_rel ON(a.org_id=org_rel.related_org_Id) "
                    + " WHERE a.action_id=" + accAuthAction.getAction_id() + "  AND a.STATUS='Active'";
            System.out.println("searchActionResources::queryString-------->" + queryString);

            queryString = queryString + " LIMIT 0,150";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                AccauthVTO accauthVTO = new AccauthVTO();
                accauthVTO.setId(resultSet.getInt("a.id"));

                accauthVTO.setAction_id(resultSet.getInt("a.action_id"));

                accauthVTO.setAccountName(resultSet.getString("account_name"));
                accauthVTO.setRollName(resultSet.getString("role_name"));
                accauthVTO.setRoleId(resultSet.getInt("role_id"));
                accauthVTO.setAccType(resultSet.getString("org_type"));
                accauthVTO.setAction_name(resultSet.getString("action_name"));

                accauthVTO.setStatus(resultSet.getString("a.status"));
                accauthVTO.setDescription(resultSet.getString("a.description"));
                accauthVTO.setUserGroupList(resultSet.getInt("groupid"));
                accauthVTO.setBlockFlag(resultSet.getInt("block_flag"));

                searchklist.add(accauthVTO);
                System.out.println("in while" + searchklist.size());
            }

        } catch (Exception sqe) {
            sqe.printStackTrace();
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
        System.out.println("********************AccAuthServiceImpl :: searchActionResources Method End*********************");
        return searchklist;

    }
}
