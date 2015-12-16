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
                        <a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
                             <i id="leftBullet" class="fa fa-briefcase"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Accounts
                        </a>
                    </h4>
                </div>
                <div id="sportswear" class="panel-collapse collapse">
                    <div class="panel-body" >
                        <ul>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/searchAccountsBy.action"><img src="<s:url value="/includes/images/icons/SearchGlobe.png"/>" height="15" width="15">&nbsp;Accounts&nbsp;Search</a> </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default left-menu" id="accordian_team">
                <div class="panel-heading" >
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                             <i id="leftBullet" class="fa fa-cogs"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Utilities
                        </a>
                    </h4>
                </div>
                <div id="mens" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change My Pwd</a></li>

                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#womens">
                            <i id="leftBullet" class="fa fa-tachometer"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Dashboard
                        </a>
                    </h4>
                </div>
                <div id="womens" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/dashboard/dashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue">&nbsp;DashBoard</i></a></li>
                            <%-- <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action">Change My Pwd</a></li>     --%>
                        </ul>
                    </div>
                </div>
            </div>

        </div><!--/category-products-->


    </div>
</div>
