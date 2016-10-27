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


            <div class="panel panel-default left-menu" id="accordian_my">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="accountsCsrLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#accountsMenuCsr">
                            <i  class="fa fa-briefcase leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Accounts
                        </a>
                    </h4>
                </div>
                <div id="accountsMenuCsr" class="panel-collapse collapse">
                    <div class="panel-body" >
                        <ul>
                            <li><a id="searchAccountsCsr" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/searchAccountsBy.action"><img src="<s:url value="/includes/images/icons/SearchGlobe.png"/>" height="15" width="15">&nbsp;Accounts&nbsp;Search</a> </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="dashboardCsrLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#dashboardMenuCsr">
                            <i class="fa fa-tachometer leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Dashboard
                        </a>
                    </h4>
                </div>
                <div id="dashboardMenuCsr" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a id="dashboardDetailsCsr" href="/<%=ApplicationConstants.CONTEXT_PATH%>/dashboard/dashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue">&nbsp;DashBoard</i></a></li>

                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default left-menu" id="accordian_team">
                <div class="panel-heading" >
                    <h4 class="panel-title">
                        <a id="utilitiesCsrLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#utilitiesMenuCsr">
                            <i  class="fa fa-cogs leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Utilities
                        </a>
                    </h4>
                </div>
                <div id="utilitiesMenuCsr" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a id="changeMyPasswordCsr" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change My Pwd</a></li>
                            <li><a id="tasksSearchCsr" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTasksSearch.action"><img src="<s:url value="/includes/images/icons/addTask.png"/>" height="15" width="15">&nbsp;Tasks</a></li>
                        </ul>
                    </div>
                </div>
            </div>



        </div>


    </div>
</div>
