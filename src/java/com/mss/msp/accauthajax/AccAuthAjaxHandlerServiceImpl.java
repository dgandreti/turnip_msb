/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauthajax;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.io.IOException;
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
public class AccAuthAjaxHandlerServiceImpl implements AccAuthAjaxHandlerService {

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
     * ForUse : insertOrUpdateAccAuth() method is used to
     *
     *
     * *****************************************************************************
     */
    public String insertOrUpdateAccAuth(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {

        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: insertOrUpdateAccAuth Method Start*********************");
        int result = 0;
        String resultMessage = "";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String status = "";
        String updateAction = "";
        String exist = "";
        String available = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            if (accAuthAjaxHandlerAction.getFlag() == 0) {
                exist = this.checkActionExistOrNot(accAuthAjaxHandlerAction.getActionName());
                if (exist.equalsIgnoreCase("exist")) {

                    resultMessage = "Already existed";
                } else {
                    queryString = "insert into ac_action (action_name,status,description) values('" + accAuthAjaxHandlerAction.getActionName() + "'," + "'Active','" + accAuthAjaxHandlerAction.getDesc() + "')";
                    System.out.println("insertOrUpdateAccAuth :: query string ------>" + queryString);
                    statement = connection.createStatement();
                    result = statement.executeUpdate(queryString);
                    resultMessage = "Successfully inserted";
                }
            } else {

                if (!accAuthAjaxHandlerAction.getActionName().equals(accAuthAjaxHandlerAction.getActionHiddenName())) {
                    exist = this.checkActionExistOrNot(accAuthAjaxHandlerAction.getActionName());
                    if (exist.equalsIgnoreCase("exist")) {

                        resultMessage = "Already existed";
                    } else {
                        available = "notExisted";
                    }
                } else {
                    available = "notExisted";
                }
                if ("notExisted".equals(available)) {
                    if ("Active".equals(accAuthAjaxHandlerAction.getStatus())) {

                        updateAction = "Action";

                    } else {
                        status = "In-Active";
                    }
                    if ("Action".equals(updateAction)) {
                        queryString = " update ac_action  SET action_name=?,status=?,description=? WHERE action_id =" + accAuthAjaxHandlerAction.getActionId();

                        preparedStatement = connection.prepareStatement(queryString);
                        preparedStatement.setString(1, accAuthAjaxHandlerAction.getActionName());
                        preparedStatement.setString(2, accAuthAjaxHandlerAction.getStatus());
                        preparedStatement.setString(3, accAuthAjaxHandlerAction.getDesc());

                    } else {
                        queryString = " update ac_action a ,ac_resources ar SET a.action_name=?,a.status=?,a.description=?,ar.status=? WHERE a.action_id =" + accAuthAjaxHandlerAction.getActionId() + " AND ar.action_id=" + accAuthAjaxHandlerAction.getActionId();

                        preparedStatement = connection.prepareStatement(queryString);
                        preparedStatement.setString(1, accAuthAjaxHandlerAction.getActionName());
                        preparedStatement.setString(2, accAuthAjaxHandlerAction.getStatus());
                        preparedStatement.setString(3, accAuthAjaxHandlerAction.getDesc());
                        preparedStatement.setString(4, status);
                    }
                    System.out.println("insertOrUpdateAccAuth :: query string ------>" + queryString);

                    result = preparedStatement.executeUpdate();
                }

            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (statement != null) {

                    statement.close();
                    statement = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: insertOrUpdateAccAuth Method End*********************");
        return resultMessage;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getRolesForAccType() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getRolesForAccType(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {

        int result = 0;
        String resultMessage = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";

        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: getRolesForAccType Method Start*********************");

        try {

            connection = ConnectionProvider.getInstance().getConnection();
            if ("-1".equals(accAuthAjaxHandlerAction.getAccType())) {
                queryString = "SELECT `role_id`,`role_name`,org_type FROM `servicebay`.`roles` ";

            } else {
                queryString = "SELECT `role_id`,`role_name`,org_type FROM `servicebay`.`roles` WHERE org_type='" + accAuthAjaxHandlerAction.getAccType() + "' ";
            }
            System.out.println("getRolesForAccType::queryString-->" + queryString);

            preparedStatement = connection.prepareStatement(queryString);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String roleName = "";
                if (resultSet.getString("role_name").equalsIgnoreCase("Employee") || resultSet.getString("role_name").equalsIgnoreCase("Team Lead") || resultSet.getString("role_name").equalsIgnoreCase("Manager")) {
                    if (resultSet.getString("org_type").equalsIgnoreCase("C")) {
                        roleName += "Customer." + resultSet.getString("role_name");

                    } else if (resultSet.getString("org_type").equalsIgnoreCase("V")) {
                        roleName += "Vendor." + resultSet.getString("role_name");
                    } else {
                        roleName += resultSet.getString("role_name");
                    }
                    resultMessage += resultSet.getInt("role_id") + "#" + roleName + "^";
                } else {
                    resultMessage += resultSet.getInt("role_id") + "#" + resultSet.getString("role_name") + "^";
                }

            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: getRolesForAccType Method End*********************");

        return resultMessage;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getAccountNames() method is used to
     *
     *
     * *****************************************************************************
     */
    public String getAccountNames(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = "";
        boolean isGetting = false;
        StringBuffer sb = new StringBuffer();
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: getAccountNames Method Start*********************");
        if ("dashboard".equals(accAuthAjaxHandlerAction.getAccNameFlag())) {

            if (accAuthAjaxHandlerAction.getTypeOfUser().equalsIgnoreCase("SA") || "V".equals(accAuthAjaxHandlerAction.getAccType())) {
                queryString = "SELECT a.account_name ,a.account_id FROM accounts a LEFT OUTER JOIN org_rel o ON(a.account_id=o.related_org_Id) WHERE o.type_of_relation='" + accAuthAjaxHandlerAction.getAccType() + "'"
                        + " AND a.account_name LIKE '" + accAuthAjaxHandlerAction.getAccName() + "%'";
            } else {
                queryString = " SELECT a.account_name ,a.account_id "
                        + " FROM accounts a "
                        + " LEFT OUTER JOIN org_rel o ON(o.related_org_Id=a.account_id) "
                        + " LEFT OUTER JOIN csrorgrel csr ON(csr.org_id=a.account_id) "
                        + " WHERE o.type_of_relation='" + accAuthAjaxHandlerAction.getAccType() + "'"
                        + " AND a.account_name LIKE '" + accAuthAjaxHandlerAction.getAccName() + "%'"
                        + " AND csr.csr_id=" + accAuthAjaxHandlerAction.getUserSessionId();
            }

        } else {
            if ("-1".equals(accAuthAjaxHandlerAction.getAccType())) {
                queryString = "SELECT a.account_name ,a.account_id FROM accounts a LEFT OUTER JOIN org_rel o ON(a.account_id=o.related_org_Id) WHERE "
                        + " a.account_name LIKE '%" + accAuthAjaxHandlerAction.getAccName() + "%'";
            } else {
                queryString = "SELECT a.account_name ,a.account_id FROM accounts a LEFT OUTER JOIN org_rel o ON(a.account_id=o.related_org_Id) WHERE o.type_of_relation='" + accAuthAjaxHandlerAction.getAccType() + "'"
                        + " AND a.account_name LIKE '%" + accAuthAjaxHandlerAction.getAccName() + "%'";
            }
        }
        System.out.println("getAccountNames::queryString-->" + queryString);

        try {

            connection = ConnectionProvider.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            int count = 0;

            sb.append("<xml version=\"1.0\">");
            sb.append("<ACCOUNTS>");
            while (resultSet.next()) {
                sb.append("<ACCOUNT><VALID>true</VALID>");

                if (resultSet.getString(1) == null || resultSet.getString(1).equals("")) {
                    sb.append("<NAME>NoRecord</NAME>");
                } else {
                    String title = resultSet.getString(1);
                    if (title.contains("&")) {
                        title = title.replace("&", "&amp;");
                    }
                    sb.append("<NAME>" + title + "</NAME>");
                }

                sb.append("<ACCOUNTID>" + resultSet.getInt(2) + "</ACCOUNTID>");
                sb.append("</ACCOUNT>");
                isGetting = true;
                count++;
            }

            if (!isGetting) {
                isGetting = false;

                sb.append("<ACCOUNT><VALID>false</VALID></ACCOUNT>");
            }
            sb.append("</ACCOUNTS>");
            sb.append("</xml>");

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet != null) {

                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: getAccountNames Method End*********************");

        return sb.toString();
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : getActionResorucesSearchResults() method is used to get state
     * names of a particular country.
     *
     * *****************************************************************************
     */
    public String getActionResorucesSearchResults(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {
        String resultMessage = "";
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: getActionResorucesSearchResults Method Start*********************");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {
            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = "SELECT DISTINCT block_flag,a.id,a.action_id,a.STATUS,a.description,CASE a.org_id WHEN 0 THEN 'All'  ELSE account_name END AS account_name,role_name,action_name,org_type FROM ac_action aa LEFT OUTER JOIN ac_resources a ON(aa.action_id=a.action_id) LEFT OUTER JOIN accounts "
                    + " ON(a.org_id=accounts.account_id) LEFT OUTER JOIN roles ON(a.usr_role_id=roles.role_id)"
                    + " LEFT OUTER JOIN org_rel ON(a.org_id=CASE a.org_id WHEN 0 THEN 0  ELSE org_rel.related_org_Id END) "
                    + " WHERE a.action_id=" + accAuthAjaxHandlerAction.getActionId() + "";
            if (!"-1".equals(accAuthAjaxHandlerAction.getAccType())) {
                queryString = queryString + " and org_type LIKE '" + accAuthAjaxHandlerAction.getAccType() + "%'";
            }

            if (accAuthAjaxHandlerAction.getRoles() != -1) {
                queryString = queryString + " and roles.role_id = " + accAuthAjaxHandlerAction.getRoles() + "";
            }
            if (accAuthAjaxHandlerAction.getStatus() != null) {

                if ("All".equals(accAuthAjaxHandlerAction.getStatus())) {
                    queryString = queryString + " and a.STATUS like '%%' ";
                } else {
                    queryString = queryString + " and a.STATUS= '" + accAuthAjaxHandlerAction.getStatus() + "'";
                }
            }
            if (!"".equals(accAuthAjaxHandlerAction.getAccName())) {
                queryString = queryString + " and account_name like '" + accAuthAjaxHandlerAction.getAccName() + "%'";
            }
            System.out.println("getActionResorucesSearchResults :: query string ------>" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {

                resultMessage += resultSet.getInt("a.id") + "|"
                        + resultSet.getString("account_name") + "|"
                        + resultSet.getString("role_name") + "|"
                        + resultSet.getString("a.status") + "|"
                        + resultSet.getString("a.description") + "|"
                        + resultSet.getInt("a.action_id") + "|"
                        + resultSet.getString("action_name") + "|"
                        + resultSet.getString("org_type") + "|"
                        + resultSet.getString("block_flag") + "^";

            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (resultSet != null) {

                    resultSet.close();
                    resultSet = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: getActionResorucesSearchResults Method End*********************");

        return resultMessage;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : insertOrUpdateActionResources() method is used to get state
     * names of a particular country.
     *
     * *****************************************************************************
     */
    public String insertOrUpdateActionResources(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {

        int result = 0;
        String resultMessage = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";

        String exist = "";
        String status = accAuthAjaxHandlerAction.getStatus();
        String available = "";
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: insertOrUpdateActionResources Method Start*********************");

        try {

            connection = ConnectionProvider.getInstance().getConnection();
            if (accAuthAjaxHandlerAction.getFlag() == 0) {

                exist = this.checkActionExistForResourceOrNot(accAuthAjaxHandlerAction, status);
                if (exist.equalsIgnoreCase("exist")) {

                    resultMessage = "Already existed";
                } else {
                    queryString = "insert into ac_resources (action_id,org_id,usr_role_id,status,description,block_flag,groupid)"
                            + " values(" + accAuthAjaxHandlerAction.getActionId() + "," + accAuthAjaxHandlerAction.getOrgId() + "," + accAuthAjaxHandlerAction.getRoles() + ",'" + status + "','" + accAuthAjaxHandlerAction.getDesc() + "'," + accAuthAjaxHandlerAction.getBlockFlag() + "," + accAuthAjaxHandlerAction.getUserGroupId() + ")";

                    statement = connection.createStatement();
                    result = statement.executeUpdate(queryString);
                    resultMessage = "Added Successfuiiy";
                }

            } else {

                if (accAuthAjaxHandlerAction.getRoles() != accAuthAjaxHandlerAction.getActionHiddenRole()) {
                    exist = this.checkActionExistForResourceOrNot(accAuthAjaxHandlerAction, status);
                    if (exist.equalsIgnoreCase("exist")) {

                        resultMessage = "Already existed";
                    } else {
                        available = "notExisted";
                    }
                } else {
                    available = "notExisted";
                }

                if ("notExisted".equals(available)) {
                    queryString = " update ac_resources SET org_id=?,usr_role_id=?,status=?,description=?,block_flag=?,groupid=? WHERE id =" + accAuthAjaxHandlerAction.getId();

                    preparedStatement = connection.prepareStatement(queryString);
                    preparedStatement.setInt(1, accAuthAjaxHandlerAction.getOrgId());
                    preparedStatement.setInt(2, accAuthAjaxHandlerAction.getRoles());
                    preparedStatement.setString(3, accAuthAjaxHandlerAction.getStatus());
                    preparedStatement.setString(4, accAuthAjaxHandlerAction.getDesc());
                    preparedStatement.setInt(5, accAuthAjaxHandlerAction.getBlockFlag());
                    preparedStatement.setInt(6, accAuthAjaxHandlerAction.getUserGroupId());

                    result = preparedStatement.executeUpdate();
                    resultMessage = "Updated Successfully";
                }
            }

            System.out.println("insertOrUpdateActionResources::queryString----------->" + queryString);

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (statement != null) {

                    statement.close();
                    statement = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: insertOrUpdateActionResources Method End*********************");

        return resultMessage;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : actionResourceTermination() method is used to get state names of
     * a particular country.
     *
     * *****************************************************************************
     */
    public String actionResourceTermination(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {

        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: actionResourceTermination Method Start*********************");
        int result = 0;
        String resultMessage = "";
        String status = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String queryString = "";

        if ("Active".equals(accAuthAjaxHandlerAction.getActionResourceStatus())) {
            status = "In-Active";
        } else {
            status = "Active";
        }
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            queryString = " update ac_resources SET status=? WHERE id =" + accAuthAjaxHandlerAction.getId();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, status);
            result = preparedStatement.executeUpdate();

            if (result > 0) {
                resultMessage = "Deleted Successfully";

            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: actionResourceTermination Method End*********************");
        return resultMessage;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkActionExistForResourceOrNot() method is used to
     *
     *
     * *****************************************************************************
     */
    public String checkActionExistForResourceOrNot(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction, String status) throws ServiceLocatorException {

        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: checkActionExistForResourceOrNot Method Start*********************");
        int result = 0;
        String resultMessage = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();

         if(accAuthAjaxHandlerAction.getRoles()==7 || accAuthAjaxHandlerAction.getRoles()==11)
            {
              queryString = "select action_id FROM ac_resources WHERE action_id=" + accAuthAjaxHandlerAction.getActionId() + " and usr_role_id=" + accAuthAjaxHandlerAction.getRoles() + " and org_id=" + accAuthAjaxHandlerAction.getOrgId()+" and groupid ="+accAuthAjaxHandlerAction.getUserGroupId();   
            }
            else{
            queryString = "select action_id FROM ac_resources WHERE action_id=" + accAuthAjaxHandlerAction.getActionId() + " and usr_role_id=" + accAuthAjaxHandlerAction.getRoles() + " and org_id=" + accAuthAjaxHandlerAction.getOrgId();
            }
            System.out.println("checkActionExistForResourceOrNot::record exist---------------->" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            if (resultSet.next()) {

                resultMessage = "exist";
            } else {
                resultMessage = "not exist";
            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: checkActionExistForResourceOrNot Method End*********************");
        return resultMessage;

    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkActionExistOrNot() method is used to
     *
     *
     * *****************************************************************************
     */
    public String checkActionExistOrNot(String actionName) throws ServiceLocatorException {

        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: checkActionExistOrNot Method Start*********************");
        int result = 0;
        String resultMessage = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "";
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "select * FROM ac_action WHERE action_name='" + actionName + "'";
            System.out.println("checkActionExistOrNot::queryString" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            if (resultSet.next()) {

                resultMessage = "exist";
            } else {
                resultMessage = "not exist";
            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
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
            } catch (SQLException sql) {
            }

        }
        System.out.println("********************AccAuthAjaxHandlerServiceImpl :: checkActionExistOrNot Method End*********************");
        return resultMessage;

    }
}
