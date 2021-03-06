<%-- 
   Document   : MenuForDirector
   Created on : Jul 15, 2015, 4:35:47 PM
   Author     : praveen<pkatru@miraclesoft.com>
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>

<div class="col-sm-12 col-md-3 col-lg-2 side_menu" >
    <div class="left-sidebar">

        <div class="panel-group category-products" id="accordian">
            <!--category-productsr-->
             <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                            <i id="leftBullet" class="fa fa-tachometer"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Dashboard
                        </a>
                    </h4>
                </div>
                <div id="mens" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/dashboard/customerDashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Requirements</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/costCenter/costCenterDashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Cost&nbsp;Center</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/projectDashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Projects</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default left-menu" id="accordian_my">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
                            <i id="leftBullet" class="fa fa-home"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Home
                        </a>
                    </h4>
                </div>
                <div id="sportswear" class="panel-collapse collapse">
                    <div class="panel-body" >
                        <ul>
                            <%--  <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/myprofile.action">Profile</a></li>--%>
                            <%--   <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/timesheetSearch.action">Time&nbsp;Sheets</a></li>--%>
                            <%
                                String usrId = session.getAttribute(ApplicationConstants.USER_ID).toString();
                                String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();

                            %>
                            <%--<li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTasksSearch.action">Tasks</a></li>--%>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/viewAccount.action?accountSearchID=<%=orgId%>&accFlag=accDetails"><img src="<s:url value="/includes/images/icons/accountInfo.png"/>" height="15" width="15">&nbsp;Account Info</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountcontactedit.action?contactId=<%=usrId%>&accountSearchID=<%=orgId%>&flag=customerlogin"><img src="<s:url value="/includes/images/icons/editProfile.png"/>" height="15" width="15">&nbsp;Profile</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getTechReviewDetails.action"><img src="<s:url value="/includes/images/icons/review.png"/>" height="15" width="15">&nbsp;Tech&nbsp;Reviews</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/getMainProjects.action"><img src="<s:url value="/includes/images/icons/project.png"/>" height="15" width="15">&nbsp;Projects</a></li>

                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#womens">
                            <i id="leftBullet" class="fa fa-cogs"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Utilities

                        </a>
                    </h4>
                </div>
                <div id="womens" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>


                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getAllRequirementList.action?orgid=<%=orgId%>"><img src="<s:url value="/includes/images/icons/requirement.png"/>" height="15" width="15">&nbsp;Requirements&nbsp;</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/budgets/ProjectBudgetDetails.action"><img src="<s:url value="/includes/images/icons/dollar.png"/>" height="15" width="15">&nbsp;Budgets</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change My Pwd</a></li>

                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/teamTimesheet.action"><img  src="<s:url value="/includes/images/icons/timesheet_icon.png"/>" height="15" width="15">&nbsp;Time&nbsp;Sheets</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTeamTasksSearch.action"><img src="<s:url value="/includes/images/icons/addTask.png"/>" height="15" width="15">&nbsp;Tasks</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/costCenter/costCenterSearch.action"><img src="<s:url value="/includes/images/icons/homeredirect.png"/>" height="12" width="12">&nbsp;Cost&nbsp;Center</a></li>
                        </ul>
                    </div>
                </div>
            </div>

           

        </div><!--/category-productsr-->


    </div>
</div>
