<%--
    Document   : empMenu
    Created on : Feb 3, 2015, 8:32:32 PM
    Author     : Nagireddy
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>

<div class="col-sm-12 col-md-3 col-lg-2 side_menu">
    <div class="left-sidebar">

        <div class="panel-group category-products" id="accordian">
            <!--category-products-->
            <div class="panel panel-default left-menu" id="accordian_my">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#accountsMenuAdmin">
                            <i  class="fa fa-briefcase leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Accounts
                        </a>
                    </h4>
                </div>
                <div id="accountsMenuAdmin" class="panel-collapse collapse">
                    <div class="panel-body" >
                        <ul>
                            <li><a id="addAccountMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountadd.action"><img src="<s:url value="/includes/images/icons/addAccount.png"/>" height="15" width="15">&nbsp;Add&nbsp;Account</a></li>
                            <li><a id="searchAccountsMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/searchAccountsBy.action"><img src="<s:url value="/includes/images/icons/SearchGlobe.png"/>" height="15" width="15">&nbsp;Accounts&nbsp;Search</a> </li>
                            <li><a id="csrListAccountsMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/csrList.action"><img src="<s:url value="/includes/images/icons/contactSearch.png"/>" height="15" width="15">&nbsp;CSR&nbsp;Search</a></li>   
                            <li><a id="assignAccountsMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/assignedRoles.action"><img src="<s:url value="/includes/images/icons/assignAcc.png"/>" height="15" width="15">&nbsp;Assign&nbsp;Accounts</a></li>
                            <li><a id="loggerAccountsMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/searchLogger.action?loggerFlag=left"><img src="<s:url value="/includes/images/icons/log_search.png"/>" height="15" width="15">&nbsp;DataLoad&nbsp;Loggers</a></li>
                        </ul>
                    </div>
                </div>
            </div>
           <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#dashboardMenuAdmin">
                            <i  class="fa fa-tachometer leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Dashboard
                        </a>
                    </h4>
                </div>
                <div id="dashboardMenuAdmin" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a id="requirementsDashboardMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/dashboard/dashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Requirements</a></li>
                           
                        </ul>
                    </div>
                </div>
            </div>             
            <div class="panel panel-default left-menu" id="accordian_team">
                <div class="panel-heading" >
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#utilitiesMenuAdmin">
                            <i  class="fa fa-cogs leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></i></span>
                            Utilities
                        </a>
                    </h4>
                </div>
                <div id="utilitiesMenuAdmin" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a id="resetPasswordUtilitiesMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetUserPassword.action"><img src="<s:url value="/includes/images/icons/resetPass.png"/>" height="15" width="15">&nbsp;Reset&nbsp;User&nbsp;Pwd</a></li>
                            <li><a id="changePasswordUtilitiesMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change&nbsp;My&nbsp;Pwd</a></li>
                            <li><a id="actionAuthUtilitiesMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/accauth/getAccAuthrization.action"><img src="<s:url value="/includes/images/icons/actionAuth.png"/>" height="15" width="15">&nbsp;Act&nbsp;Authorization</a></li>
                            <li><a id="homeRedirectUtilitiesMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/getHomeRedirectDetails.action"><img src="<s:url value="/includes/images/icons/homeredirect.png"/>" height="12" width="12">&nbsp;Home&nbsp;Redirect</a></li>
                            <li><a id="uploadAccountsUtilitiesMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/loadData.action"><img src="<s:url value="/includes/images/icons/upload.png"/>" height="12" width="12">&nbsp;Upload&nbsp;Accounts</a></li>
                            <li><a id="uploadContactsUtilitiesMenuAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/loadDataForUser.action"><img src="<s:url value="/includes/images/icons/upload.png"/>" height="12" width="12">&nbsp;Upload&nbsp;Contacts</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            

        </div>


    </div>
</div>
