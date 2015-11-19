 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.authorizationcheck;

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
//
//    private HttpServletRequest httpServletRequest;
//    private HttpServletResponse httpServletResponse;

    private String errMessage;

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {

        // System.out.println("In intercept()--->"+ai.getAction().toString()+"-AN--"+ai.getProxy().getActionName()+"-N-"+ai.getProxy().getNamespace()+"-AM-"+ai.getProxy().getMethod());
        //  HttpServletRequest httpServletRequest = (HttpServletRequest) ai.get(HTTP_REQUEST); 

        //  HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext context = ai.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        Map<String, Object> userDetailsMap = ai.getInvocationContext().getSession();
        System.out.println("userrolesmap from session-->" + userDetailsMap);
        String invokeaction = ai.getProxy().getActionName();

        if (userDetailsMap.get("userId") != null) {
            int invokuserId = Integer.parseInt(userDetailsMap.get("userId").toString());

            int invokusrorgid = Integer.parseInt(userDetailsMap.get("orgId").toString());
            Map userrolesFromsession = (HashMap) userDetailsMap.get("rolesMap");
            int primaryRole = Integer.parseInt(userDetailsMap.get("primaryrole").toString());
            int usrGroupList = Integer.parseInt(userDetailsMap.get("usrgrpid").toString());
            System.out.println("invokation action-->" + invokeaction + "-- userId--" + invokuserId + "-- sessionrolesmap--->" + userrolesFromsession);
            System.out.println("OrgId--->" + invokusrorgid);
            System.out.println("primaryRole--->" + primaryRole);
            System.out.println("usrGroupList--->" + usrGroupList);

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            Statement statement = null;
            ResultSet resultSet = null;
            Connection connection1 = null;
            PreparedStatement preparedStatement1 = null;
            Statement statement1 = null;
            ResultSet resultSet1 = null;
            int result = 0;
            String resultMessage = "";
            String queryString = "";
            String queryString1 = "";
            // Map userActions = new HashMap();
            boolean check = false;
            boolean check1 = true;
            try {

                connection = ConnectionProvider.getInstance().getConnection();


                queryString = "SELECT usr_role_id,org_id,action_name,groupid FROM ac_resources ar LEFT OUTER JOIN ac_action a"
                        + "  ON(ar.action_id=a.action_id) WHERE  action_name LIKE '%" + invokeaction + "%' AND block_flag=0";

                System.out.println("queryString-->" + queryString);

                preparedStatement = connection.prepareStatement(queryString);
                // preparedStatement.setInt(1, dept_id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getInt("org_id") == 0) {
                        System.out.println("in firdt if--->");

                        if (primaryRole == resultSet.getInt("usr_role_id")) {
                            System.out.println("in second if--->");
                            if (resultSet.getInt("groupid") > 0) {
                                if (usrGroupList == resultSet.getInt("groupid")) {
                                    check = true;
                                    break;
                                }
                                else{
                                  check = false;   
                                }
                            } else {
                                check = true;
                                break;
                            }
                        } else {
                            System.out.println("in else--->");

                            check = false;
                        }
                    } else if (resultSet.getInt("org_id") == invokusrorgid && resultSet.getInt("usr_role_id") == primaryRole) {
                        System.out.println("in else if--->");
                          if (resultSet.getInt("groupid") > 0) {
                                if (usrGroupList == resultSet.getInt("groupid")) {
                                    check = true;
                                    break;
                                }
                                else{
                                  check = false;   
                                }
                            } else {
                                check = true;
                                break;
                            }
                       
                    } else {
                        System.out.println("in else --->");

                        check = false;
                    }
                }

                connection1 = ConnectionProvider.getInstance().getConnection();


                queryString1 = "SELECT usr_role_id,org_id,action_name,groupid FROM ac_resources ar LEFT OUTER JOIN ac_action a"
                        + "  ON(ar.action_id=a.action_id) WHERE  action_name LIKE '%" + invokeaction + "%' AND block_flag=1";

                System.out.println("queryString1-->" + queryString1);

                preparedStatement1 = connection1.prepareStatement(queryString1);
                // preparedStatement.setInt(1, dept_id);
                resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    if (resultSet1.getInt("org_id") == 0) {
                        System.out.println("in first if--->");

                        if (primaryRole == resultSet1.getInt("usr_role_id")) {
                            System.out.println("in second if--->");
                            if (resultSet1.getInt("groupid") > 0) {
                                if (usrGroupList == resultSet1.getInt("groupid")) {
                                    check1 = false;
                                    break;
                                }
                                else{
                                  check1 = true;   
                                }
                            } else {
                                check1 = false;
                                break;
                            }

//                            check1 = false;
//                            break;
                        } else {
                            System.out.println("in first if-> else--->");

                            check1 = true;
                        }
                    } else if (resultSet1.getInt("org_id") == invokusrorgid && resultSet1.getInt("usr_role_id") == primaryRole) {
                        System.out.println("in else if--->");
                        
                        if (resultSet1.getInt("groupid") > 0) {
                                if (usrGroupList == resultSet1.getInt("groupid")) {
                                    check1 = false;
                                    break;
                                }
                                else{
                                  check1 = true;   
                                }
                            } else {
                                check1 = false;
                                break;
                            }
                        

//                        check1 = false;
//                        break;
                    } else {
                        System.out.println("in else--->");

                        check1 = true;
                    }
                }



                System.out.println("Check-->" + check);
                System.out.println("Check1--->" + check1);
                if (check == true && check1 == true) {
                    return ai.invoke();
                } else {

                    HttpSession session = request.getSession(false);
                    session.invalidate();
                    // request.setAttribute("errorMessage", "<font color=\"red\" size=\"1.5\">Access is denied!. Please Login. </font>");
                    System.out.println("invalidate");
                    return "accessDenied";
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
                } catch (SQLException sql) {
                    //System.err.print("Error :"+sql);
                }

            }




            // select roles,org from acaction acresources where acaction.name like invokeaction and flg=1;

            // map -->  orgid

            //alow --> 0,9  --> 1009,9 true;

            //  blokmap --> false;


            //  return ai.invoke();

        } else {
            return "sessionExpire";
        }
    }

    @Override
    public void destroy() {
    }

//    public void setServletRequest(HttpServletRequest httpServletRequest) {
//        this.httpServletRequest = httpServletRequest;
//    }
//
//    public void setServletResponse(HttpServletResponse httpServletResponse) {
//        this.httpServletResponse = httpServletResponse;
//    }
//
//    public HttpServletRequest getHttpServletRequest() {
//        return httpServletRequest;
//    }
//
//    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
//        this.httpServletRequest = httpServletRequest;
//    }
    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
