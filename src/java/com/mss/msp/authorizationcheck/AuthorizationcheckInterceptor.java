 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.authorizationcheck;

import com.mss.msp.util.AuthorizationManager;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class AuthorizationcheckInterceptor implements Interceptor, StrutsStatics {

    private String errMessage;

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {

        ActionContext context = ai.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        Map<String, Object> userDetailsMap = ai.getInvocationContext().getSession();
        //  System.out.println("userrolesmap from session-->" + userDetailsMap);
        String invokeaction = ai.getProxy().getActionName();

        if (userDetailsMap.get("userId") != null) {
            int invokuserId = Integer.parseInt(userDetailsMap.get("userId").toString());

            int invokusrorgid = Integer.parseInt(userDetailsMap.get("orgId").toString());
            Map userrolesFromsession = (HashMap) userDetailsMap.get("rolesMap");
            int primaryRole = Integer.parseInt(userDetailsMap.get("primaryrole").toString());
            int usrGroupList = Integer.parseInt(userDetailsMap.get("usrgrpid").toString());
               String typeOfUser = userDetailsMap.get("typeOfUsr").toString();
            // System.out.println("invokation action-->" + invokeaction + "-- userId--" + invokuserId + "-- sessionrolesmap--->" + userrolesFromsession);
            // System.out.println("OrgId--->" + invokusrorgid);
            // System.out.println("primaryRole--->" + primaryRole);
            // System.out.println("usrGroupList--->" + usrGroupList);
            System.out.println("context params" + context.getParameters());

            Map params = request.getParameterMap();
//Retrieve the map and check..
            for (Iterator iterator = params.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                String[] obj = (String[]) (params.get(key));
                if (obj.length > 0) {
                    System.out.println(key + "<>" + obj[0]);
                }

            }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection1 = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        int result = 0;
        String resultMessage = "";
        String queryString = "";
        String queryString1 = "";
        boolean check = false;
        boolean check1 = true;
        try {

            connection = ConnectionProvider.getInstance().getConnection();

            queryString = "SELECT usr_role_id,org_id,action_name,groupid FROM ac_resources ar LEFT OUTER JOIN ac_action a"
                    + "  ON(ar.action_id=a.action_id) WHERE  action_name LIKE '%" + invokeaction + "%' AND block_flag=0 AND a.STATUS='Active' AND ar.STATUS='Active'";

            System.out.println("queryString-->" + queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("org_id") == 0) {
                    //System.out.println("in firdt if--->");

                    if (primaryRole == resultSet.getInt("usr_role_id")) {
                        // System.out.println("in second if--->");
                        if (resultSet.getInt("groupid") > 0) {
                            if (usrGroupList == resultSet.getInt("groupid")) {
                                check = true;
                                break;
                            } else {
                                check = false;
                            }
                        } else {
                            check = true;
                            break;
                        }
                    } else {
                        // System.out.println("in else--->");

                        check = false;
                    }
                } else if (resultSet.getInt("org_id") == invokusrorgid && resultSet.getInt("usr_role_id") == primaryRole) {
                    //  System.out.println("in else if--->");
                    if (resultSet.getInt("groupid") > 0) {
                        if (usrGroupList == resultSet.getInt("groupid")) {
                            check = true;
                            break;
                        } else {
                            check = false;
                        }
                    } else {
                        check = true;
                        break;
                    }

                } else {
                    // System.out.println("in else --->");

                    check = false;
                }
            }
            connection1 = ConnectionProvider.getInstance().getConnection();
            queryString1 = "SELECT usr_role_id,org_id,action_name,groupid FROM ac_resources ar LEFT OUTER JOIN ac_action a"
                    + "  ON(ar.action_id=a.action_id) WHERE  action_name LIKE '%" + invokeaction + "%' AND block_flag=1 AND a.STATUS='Active' AND ar.STATUS='Active'";

            System.out.println("queryString1-->" + queryString1);

            preparedStatement1 = connection1.prepareStatement(queryString1);
            resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                if (resultSet1.getInt("org_id") == 0) {
                    // System.out.println("in first if--->");

                    if (primaryRole == resultSet1.getInt("usr_role_id")) {
                        // System.out.println("in second if--->");
                        if (resultSet1.getInt("groupid") > 0) {
                            if (usrGroupList == resultSet1.getInt("groupid")) {
                                check1 = false;
                                break;
                            } else {
                                check1 = true;
                            }
                        } else {
                            check1 = false;
                            break;
                        }
//                            check1 = false;
//                            break;
                    } else {
                        //   System.out.println("in first if-> else--->");

                        check1 = true;
                    }
                } else if (resultSet1.getInt("org_id") == invokusrorgid && resultSet1.getInt("usr_role_id") == primaryRole) {
                    //  System.out.println("in else if--->");

                    if (resultSet1.getInt("groupid") > 0) {
                        if (usrGroupList == resultSet1.getInt("groupid")) {
                            check1 = false;
                            break;
                        } else {
                            check1 = true;
                        }
                    } else {
                        check1 = false;
                        break;
                    }
                } else {
                    // System.out.println("in else--->");
                    check1 = true;
                }
            }
                // System.out.println("Check-->" + check);
            // System.out.println("Check1--->" + check1);
            if (check == true && check1 == true) {
                    boolean authorizationCheck = false;
                     authorizationCheck = AuthorizationManager.getInstance().isAuthorizedUser(request,invokeaction,userDetailsMap);

                    System.out.println("authorizationCheck-->"+authorizationCheck);
                    if (authorizationCheck == true) {
                        System.out.println("****AuthorizationcheckInterceptor ::: Action invoked******");
                        return ai.invoke();
                    } else {
                        HttpSession session = request.getSession(false);
                        session.invalidate();
                        System.out.println("******AuthorizationcheckInterceptor  ::: session invalidated because unauthorized user :: parameters invalid******");
                        return "accessDenied";
                    }
                } else {
                    HttpSession session = request.getSession(false);
                    session.invalidate();
                    System.out.println("******AuthorizationcheckInterceptor  ::: session invalidated because unauthorized user******");
                    return "accessDenied";
                }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {

                if (resultSet1 != null) {

                    resultSet1.close();
                    resultSet1 = null;
                }
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                    preparedStatement1 = null;
                }

                if (connection1 != null) {
                    connection1.close();
                    connection1 = null;
                }
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
    }

    
        else {
            System.out.println("**** AuthorizationcheckInterceptor  ::: session Expired.******");
        return "sessionExpire";
    }
}

@Override
        public void destroy() {
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
