/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauthajax;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.io.IOException;
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

    public String insertOrUpdateAccAuth(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        //StringBuffer sb = new StringBuffer();
        System.out.println("getActionName-->" + accAuthAjaxHandlerAction.getActionName());
        System.out.println("Status-->" + accAuthAjaxHandlerAction.getStatus());
        System.out.println("Desc-->" + accAuthAjaxHandlerAction.getDesc());

        try {
             connection = ConnectionProvider.getInstance().getConnection();
            if (accAuthAjaxHandlerAction.getFlag() == 0) {
                String exist = this.checkActionExistOrNot(accAuthAjaxHandlerAction.getActionName());
                if (exist.equalsIgnoreCase("exist")) {
                    System.out.println("existed action");
                    resultMessage = "Already existed";
                }
                else
                {    
                queryString = "insert into ac_action (action_name,status,description) values('" + accAuthAjaxHandlerAction.getActionName() + "'," + "'Active','" + accAuthAjaxHandlerAction.getDesc() + "')";
                System.out.println("queryString-->" + queryString);
                statement = connection.createStatement();
                result = statement.executeUpdate(queryString);
                resultMessage = "Successfully inserted";
                }
            } else {
                
                queryString = " update ac_action SET action_name=?,status=?,description=? WHERE action_id =" + accAuthAjaxHandlerAction.getActionId();

                //System.out.println("get edit skill details update query" + queryStringupdate);
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, accAuthAjaxHandlerAction.getActionName());
                preparedStatement.setString(2, accAuthAjaxHandlerAction.getStatus());
                preparedStatement.setString(3, accAuthAjaxHandlerAction.getDesc());
                
                result = preparedStatement.executeUpdate();
            }
//            if (result > 0) {
//                Connection connection1 = null;
//                Statement statement1 = null;
//                ResultSet resultSet1 = null;
//                DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
//                connection1 = ConnectionProvider.getInstance().getConnection();
//                String queryString1 = "SELECT `action_id`, `action_name`, `status`, `description` FROM `servicebay`.`ac_action` where status='" + accAuthAjaxHandlerAction.getStatus() + "' LIMIT 0,150  ";
//
//                statement1 = connection1.createStatement();
//                resultSet1 = statement1.executeQuery(queryString1);
//                while (resultSet1.next()) {
//
//                    resultMessage += resultSet1.getInt("action_id") + "|"
//                            + resultSet1.getString("action_name") + "|"
//                            + resultSet1.getString("status") + "|"
//                            + resultSet1.getString("description") + "^";
//                }
//                System.out.println("In Result if" + queryString1);
//
//            }
            //System.out.println("String-->" + sb);
            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        return resultMessage;

    }

    public String getRolesForAccType(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        //StringBuffer sb = new StringBuffer();
//        System.out.println("getActionName-->" + accAuthAjaxHandlerAction.getActionName());
//        System.out.println("Status-->" + accAuthAjaxHandlerAction.getStatus());
//        System.out.println("Desc-->" + accAuthAjaxHandlerAction.getDesc());

        try {
            
            connection = ConnectionProvider.getInstance().getConnection();
            if ("-1".equals(accAuthAjaxHandlerAction.getAccType())) {
                queryString = "SELECT `role_id`,`role_name`,org_type FROM `servicebay`.`roles` ";
                
                
            } else {
                queryString = "SELECT `role_id`,`role_name`,org_type FROM `servicebay`.`roles` WHERE org_type='" + accAuthAjaxHandlerAction.getAccType() + "' ";
            }
            System.out.println("queryString-->" + queryString);

            preparedStatement = connection.prepareStatement(queryString);
            // preparedStatement.setInt(1, dept_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String roleName="";
                   if (resultSet.getString("role_name").equalsIgnoreCase("Employee") || resultSet.getString("role_name").equalsIgnoreCase("Team Lead") || resultSet.getString("role_name").equalsIgnoreCase("Manager")) {
                    if (resultSet.getString("org_type").equalsIgnoreCase("C")) {
                        roleName += "Customer." + resultSet.getString("role_name");
                        System.out.println(roleName);
                    } else if (resultSet.getString("org_type").equalsIgnoreCase("V")) {
                        roleName += "Vendor." + resultSet.getString("role_name");
                    } else {
                        roleName += resultSet.getString("role_name");
                    }
                   resultMessage += resultSet.getInt("role_id") + "#" + roleName + "^";
                }else{
                    resultMessage += resultSet.getInt("role_id") + "#" + resultSet.getString("role_name") + "^";
                } 
               
            }
             System.out.println("resultMessage--->"+resultMessage);

            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        return resultMessage;


    }

    public String getAccountNames(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {
        boolean isGetting = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sb = new StringBuffer();
        String queryString = "";

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
          if("-1".equals(accAuthAjaxHandlerAction.getAccType()))
            {
            queryString = "SELECT a.account_name ,a.account_id FROM accounts a LEFT OUTER JOIN org_rel o ON(a.account_id=o.related_org_Id) WHERE "
                    + " a.account_name LIKE '%" + accAuthAjaxHandlerAction.getAccName() + "%'";
            }
            else
            {     
              queryString = "SELECT a.account_name ,a.account_id FROM accounts a LEFT OUTER JOIN org_rel o ON(a.account_id=o.related_org_Id) WHERE o.type_of_relation='" + accAuthAjaxHandlerAction.getAccType() + "'"
                    + " AND a.account_name LIKE '%" + accAuthAjaxHandlerAction.getAccName() + "%'";   
            }
        }
        try {
            System.out.println("queryString-->" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            // System.out.println("query-->getEmployeeDetails"+queryString);
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
                //sb.append("<NAME>" +resultSet.getString(1) + "</NAME>");
                sb.append("<ACCOUNTID>" + resultSet.getInt(2) + "</ACCOUNTID>");
                sb.append("</ACCOUNT>");
                isGetting = true;
                count++;
            }

            if (!isGetting) {
                //sb.append("<EMPLOYEES>" + sb.toString() + "</EMPLOYEES>");
                //} else {
                isGetting = false;
                //nothing to show
                //  response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                sb.append("<ACCOUNT><VALID>false</VALID></ACCOUNT>");
            }
            sb.append("</ACCOUNTS>");
            sb.append("</xml>");
            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        return sb.toString();
    }

    public String getActionResorucesSearchResults(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {
        String resultMessage = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {


            DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
            connection = ConnectionProvider.getInstance().getConnection();
//            String queryString = "SELECT a.id,a.action_id,a.STATUS,a.description,CASE a.org_id WHEN 0 THEN 'All'  ELSE account_name END AS account_name,role_name,action_name,type_of_relation FROM ac_action aa LEFT OUTER JOIN ac_resources a ON(aa.action_id=a.action_id) LEFT OUTER JOIN accounts "
//                    + " ON(a.org_id=accounts.account_id) LEFT OUTER JOIN roles ON(a.usr_role_id=roles.role_id)"
//                    + " LEFT OUTER JOIN org_rel ON(a.org_id=org_rel.related_org_Id) "
//                    + " WHERE a.action_id=" + accAuthAjaxHandlerAction.getActionId() + "";
            String queryString = "SELECT DISTINCT block_flag,a.id,a.action_id,a.STATUS,a.description,CASE a.org_id WHEN 0 THEN 'All'  ELSE account_name END AS account_name,role_name,action_name,org_type FROM ac_action aa LEFT OUTER JOIN ac_resources a ON(aa.action_id=a.action_id) LEFT OUTER JOIN accounts "
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
            System.out.println("AccName" + accAuthAjaxHandlerAction.getAccName());
            if (!"".equals(accAuthAjaxHandlerAction.getAccName())) {
                queryString = queryString + " and account_name like '" + accAuthAjaxHandlerAction.getAccName() + "%'";
            }
//            if (accAuthAjaxHandlerAction.getAccountName() != null || !"".equals(userAjaxHandlerAction.getAccountName())) {
//                queryString1 = queryString1 + " and a.account_name LIKE '" + userAjaxHandlerAction.getAccountName() + "%'";
//            }
//LIKE '" + userAjaxHandlerAction.getEmpName() + "%' "
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
                        + resultSet.getString("org_type") + "^";

                /*   accauthVTO.setId(resultSet.getInt("a.id"));

                 accauthVTO.setAction_id(resultSet.getInt("a.action_id"));
                 //accauthVTO.setAction_name(resultSet.getString("action_name"));
                 accauthVTO.setAccountName(resultSet.getString("account_name"));
                 accauthVTO.setRollName(resultSet.getString("role_name"));
                 accauthVTO.setAccType(resultSet.getString("type_of_relation"));


                 accauthVTO.setStatus(resultSet.getString("a.status"));
                 accauthVTO.setDescription(resultSet.getString("a.description"));*/
            }
            System.out.println("In Result if" + queryString);


            //System.out.println("String-->" + sb);
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
            } catch (SQLException sql) {
                //System.err.print("Error :"+sql);
            }

        }
        return resultMessage;
    }

    public String insertOrUpdateActionResources(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        //StringBuffer sb = new StringBuffer();
        System.out.println("getActionName-->" + accAuthAjaxHandlerAction.getActionName());
        System.out.println("Status-->" + accAuthAjaxHandlerAction.getStatus());
        System.out.println("Desc-->" + accAuthAjaxHandlerAction.getDesc());

        try {
 
            connection = ConnectionProvider.getInstance().getConnection();
            if (accAuthAjaxHandlerAction.getFlag() == 0) {
                String status = accAuthAjaxHandlerAction.getStatus();
                String exist = this.checkActionExistForResourceOrNot(accAuthAjaxHandlerAction,status);
                if (exist.equalsIgnoreCase("exist")) {
                    System.out.println("existed al");
                    resultMessage = "Already existed";
                } else {
                   queryString = "insert into ac_resources (action_id,org_id,usr_role_id,status,description,block_flag,groupid)"
                            + " values(" + accAuthAjaxHandlerAction.getActionId() + "," + accAuthAjaxHandlerAction.getOrgId() + "," + accAuthAjaxHandlerAction.getRoles() + ",'" + status + "','" + accAuthAjaxHandlerAction.getDesc() + "'," + accAuthAjaxHandlerAction.getBlockFlag() + "," + accAuthAjaxHandlerAction.getUserGroupId() + ")";                                                      
                    System.out.println("queryString-->" + queryString);
                    statement = connection.createStatement();
                    result = statement.executeUpdate(queryString);
                    resultMessage = "Added Successfuiiy";
                }

            } else {

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


            System.out.println("In Result if" + queryString);


            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        System.out.println("resultMessage" + resultMessage);
        return resultMessage;

    }

    public String actionResourceTermination(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        try {
            connection = ConnectionProvider.getInstance().getConnection();
           
                queryString = " update ac_resources SET status=? WHERE id =" + accAuthAjaxHandlerAction.getId();

                //System.out.println("get edit skill details update query" + queryStringupdate);
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, "In-Active");

                result = preparedStatement.executeUpdate();
            
            if (result > 0) {
              resultMessage="Deleted Successfully";  
//                Connection connection1 = null;
//                Statement statement1 = null;
//                ResultSet resultSet1 = null;
//               // DataSourceDataProvider dsdp = DataSourceDataProvider.getInstance();
//                connection1 = ConnectionProvider.getInstance().getConnection();
//               // String queryString1 = "SELECT `action_id`, `action_name`, `status`, `description` FROM `servicebay`.`ac_action` where status='" + accAuthAjaxHandlerAction.getStatus() + "' LIMIT 0,150  ";
//                
//                String queryString1 = "SELECT a.id,a.action_id,a.STATUS,a.description,CASE a.org_id WHEN 0 THEN 'All'  ELSE account_name END AS account_name,role_name,action_name,type_of_relation FROM ac_action aa LEFT OUTER JOIN ac_resources a ON(aa.action_id=a.action_id) LEFT OUTER JOIN accounts "
//                    + " ON(a.org_id=accounts.account_id) LEFT OUTER JOIN roles ON(a.usr_role_id=roles.role_id)"
//                    + " LEFT OUTER JOIN org_rel ON(a.org_id=org_rel.related_org_Id) "
//                    + " WHERE a.action_id=" + accAuthAjaxHandlerAction.getActionId() + "";
//                statement1 = connection1.createStatement();
//                resultSet1 = statement1.executeQuery(queryString1);
//                while (resultSet1.next()) {
//
//                  resultMessage += resultSet.getInt("a.id") + "|"
//                        + resultSet.getString("account_name") + "|"
//                        + resultSet.getString("role_name") + "|"
//                        + resultSet.getString("a.status") + "|"
//                        + resultSet.getString("a.description") + "|"
//                        + resultSet.getInt("a.action_id") + "|"
//                        + resultSet.getString("action_name") + "|"
//                        + resultSet.getString("type_of_relation") + "^";
//                }
//                System.out.println("In Result if" + queryString1);
//
            }
            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        return resultMessage;

    }
    public String checkActionExistForResourceOrNot(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction,String status) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        System.out.println(accAuthAjaxHandlerAction.getActionId()+","+accAuthAjaxHandlerAction.getOrgId()+","+accAuthAjaxHandlerAction.getRoles());
      //  AccAuthAjaxHandlerAction accAuthAjaxHandlerAction=new AccAuthAjaxHandlerAction();
        try {
            System.out.println("method checkActionExistForResourceOrNot()");
            connection = ConnectionProvider.getInstance().getConnection();
            System.out.println("status"+status);

            queryString = "select action_id FROM ac_resources WHERE action_id=" + accAuthAjaxHandlerAction.getActionId() + " and usr_role_id=" + accAuthAjaxHandlerAction.getRoles() + " and org_id=" + accAuthAjaxHandlerAction.getOrgId();    
                  
            System.out.println("record exist" + queryString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            
            
            if (resultSet.next()) {
               
                resultMessage = "exist";
            } else {
                resultMessage = "not exist";
            }

            //System.out.println("String-->" + sb);
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
                //System.err.print("Error :"+sql);
            }

        }
        return resultMessage;

    }
    
    public String checkActionExistOrNot(String actionName) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        System.out.println("actionName"+actionName);
        try {
            System.out.println("method checkActionExistOrNot()");
            connection = ConnectionProvider.getInstance().getConnection();
            
            queryString = "select * FROM ac_action WHERE action_name='"+actionName+"'" ;
            
            System.out.println("record exist" + queryString);
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sql) {
                //System.err.print("Error :"+sql);
            }

        }
        return resultMessage;

    }
}
