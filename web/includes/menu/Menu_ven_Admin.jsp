<%-- 
    Document   : MenuSales
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
        <%
            String usrId = session.getAttribute(ApplicationConstants.USER_ID).toString();
            String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();

        %>
        <div class="panel-group category-products" id="accordian">
            <!--category-productsr-->
            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                            <i id="leftBullet" class="fa fa-cogs"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Dashboard
                        </a>
                    </h4>
                </div>
                <div id="mens" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/vendor/vendorDashboard.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Requirements&nbsp;</a></li>
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
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/viewAccount.action?accountSearchID=<%=orgId%>&accFlag=accDetails"><img src="<s:url value="/includes/images/icons/accountInfo.png"/>" height="15" width="15">&nbsp;Account Info</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountcontactedit.action?contactId=<%=usrId%>&accountSearchID=<%=orgId%>&flag=vendorlogin"><img src="<s:url value="/includes/images/icons/editProfile.png"/>" height="15" width="15">&nbsp;Profile</a></li>

                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTasksSearch.action"><img src="<s:url value="/includes/images/icons/addTask.png"/>" height="15" width="15">&nbsp;Tasks</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/timesheetSearch.action"><img  src="<s:url value="/includes/images/icons/timesheet_icon.png"/>" height="15" width="15">&nbsp;Time&nbsp;Sheets</a></li>

                        </ul>
                    </div>
                </div>
            </div>
            <%-- <s:if test="%{#session.is_manager == 1 || #session.is_team_lead == 1}">
                 <div class="panel panel-default left-menu" id="accordian_team">
                     <div class="panel-heading" >
                         <h4 class="panel-title">
                             <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                                 <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                 Team
                             </a>
                         </h4>
                     </div>
                     <div id="mens" class="panel-collapse collapse">
                         <div class="panel-body">
                             <ul>
            <%-- <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getMyConsultantSearch.action?consultantFlag=Team">Consultant&nbsp;Search</a></li>
            <%--<li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/doEmployeeSearch.action">Employee Search</a></li>
            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getLoginUserRequirementList.action?accountFlag=MyRequirements&orgid=<%=orgId%>&vendor=yes">Requirements</a></li>
            <%-- <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getAllRequirementList.action">All&nbsp;Requirements&nbsp;</a></li>
            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getMyConsultantSearch.action?consultantFlag=Team">Consultant&nbsp;Search</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
            </s:if>
            --%>
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

                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/getEmployeeCategorization.action"><img src="<s:url value="/includes/images/icons/empGrouping.png"/>" height="15" width="15">&nbsp;Emp&nbsp;Grouping</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change My Pwd</a></li> 
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetUserPassword.action"><img src="<s:url value="/includes/images/icons/resetPass.png"/>" height="15" width="15">&nbsp;Reset User Pwd</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/loadDataForUser.action"><img src="<s:url value="/includes/images/icons/homeredirect.png"/>" height="12" width="12">&nbsp;Upload&nbsp;Contacts</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/getHomeRedirectDetails.action"><img src="<s:url value="/includes/images/icons/homeredirect.png"/>" height="15" width="15">&nbsp;Home&nbsp;Redirection</a></li>
                            <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/searchLogger.action?loggerFlag=left"><img src="<s:url value="/includes/images/icons/log_search.png"/>" height="15" width="15">&nbsp;Logger&nbsp;Search</a></li>
                        </ul>
                    </div>
                </div>
            </div>



        </div><!--/category-productsr-->


    </div>
</div>