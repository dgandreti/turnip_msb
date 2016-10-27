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

<div class="col-sm-12 col-md-3 col-lg-2 side_menu" >
    <div class="left-sidebar">

        <div class="panel-group category-products" id="accordian">
          

            <div class="panel panel-default left-menu" id="accordian_my">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="homeCustAdminLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#homeMenuCustAdmin">
                            <i  class="fa fa-home leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Home
                        </a>
                    </h4>
                </div>
                <div id="homeMenuCustAdmin" class="panel-collapse collapse">
                    <div class="panel-body" >
                        <%
                            String usrId = session.getAttribute(ApplicationConstants.USER_ID).toString();
                            String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();

                        %>
                        <ul>
                           
                            <li><a id="accountInfoHomeCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/viewAccount.action?accountSearchID=<%=orgId%>&accFlag=accDetails"><img src="<s:url value="/includes/images/icons/accountInfo.png"/>" height="15" width="15">&nbsp;Account Info</a></li>
                            <li><a id="profileHomeCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountcontactedit.action?contactId=<%=usrId%>&accountSearchID=<%=orgId%>&flag=customerlogin"><img src="<s:url value="/includes/images/icons/editProfile.png"/>" height="15" width="15">&nbsp;Profile</a></li>
                            <li><a id="timesheetsHomeCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/timesheetSearch.action"><img src="<s:url value="/includes/images/icons/timesheet_icon.png"/>" height="15" width="15">&nbsp;Time&nbsp;Sheets</a></li>
                            <li><a id="tasksHomeCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTasksSearch.action"><img src="<s:url value="/includes/images/icons/addTask.png"/>" height="15" width="15">&nbsp;Tasks</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="dashboardCustAdminLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#dashboardMenuCustAdmin">
                            <i  class="fa fa-tachometer leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Dashboard
                        </a>
                    </h4>
                </div>
                <div id="dashboardMenuCustAdmin" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a id="customerDashboardCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/dashboard/customerDashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Requirements</a></li>
                            <li><a id="costcenterDashboardCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/costCenter/costCenterDashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Cost&nbsp;Center</a></li>
                            <li><a id="projectDashboardCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/projectDashBoardDetails.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Projects</a></li>
                            <li><a id="customerDashboardCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/turnip/getVendorResourcePage.action"><i class="fa fa-bar-chart-o" style="color: blue"></i>&nbsp;Turnip</a></li>
                            
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="utilitiesCustAdminLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#utilitiesMenuCustAdmin">
                            <i  class="fa fa-cogs leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Utilities

                        </a>
                    </h4>
                </div>
                <div id="utilitiesMenuCustAdmin" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>

                            <li><a id="costCenterUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/costCenter/costCenterSearch.action"><img src="<s:url value="/includes/images/icons/homeredirect.png"/>" height="12" width="12">&nbsp;Cost&nbsp;Center</a></li>
                            <li><a id="empCategoryUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/getEmployeeCategorization.action"><img src="<s:url value="/includes/images/icons/empGrouping.png"/>" height="15" width="15">&nbsp;Emp&nbsp;Grouping</a></li>
                            <li><a id="questionsUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/onlineExam/Ques/getQuestionsList.action"><font class="quest">&#xf059;</font> Questions&nbsp;</a></li>
                            <li><a id="uploadQuesionsUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/onlineExam/Ques/getSkillDetails.action"><img src="<s:url value="/includes/images/icons/upload.png"/>" height="15" width="15">&nbsp;Upload&nbsp;Questions&nbsp;</a></li>
                            <li><a id="uploadContactsUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/loadDataForUser.action"><img src="<s:url value="/includes/images/icons/upload.png"/>" height="15" width="15">&nbsp;Upload&nbsp;Contacts</a></li>
                            <li><a id="homeRedirectUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/getHomeRedirectDetails.action"><img src="<s:url value="/includes/images/icons/homeredirect.png"/>" height="15" width="15">&nbsp;Home&nbsp;Redirection</a></li>
                            <li><a id="loggerSearchUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/searchLogger.action?loggerFlag=left"><img src="<s:url value="/includes/images/icons/log_search.png"/>" height="15" width="15">&nbsp;DataLoad&nbsp;Logger</a></li>
                            <li><a id="changePasswordUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change My Pwd</a></li>
                            <li><a id="resetPasswordUtilitiesCustAdmin" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/resetUserPassword.action"><img src="<s:url value="/includes/images/icons/resetPass.png"/>" height="15" width="15">&nbsp;Reset user Pwd</a></li>
                        </ul>
                    </div>
                </div>
            </div>



        </div>

    </div>
</div>
