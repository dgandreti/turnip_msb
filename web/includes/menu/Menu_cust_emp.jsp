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
            <!--category-productsr-->
            <div class="panel panel-default left-menu" id="accordian_my">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="homeCustEmpLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#homeMenu">
                            <i  class="fa fa-home leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Home
                        </a>
                    </h4>
                </div>
                <div id="homeMenu" class="panel-collapse collapse">
                    <div class="panel-body" >
                        <ul>
                          
                            <li><a id="timesheetsHome" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/timesheetSearch.action"><img src="<s:url value="/includes/images/icons/timesheet_icon.png"/>" height="15" width="15">&nbsp;Time&nbsp;Sheets</a></li>
                                    <%
                                        String usrId = session.getAttribute(ApplicationConstants.USER_ID).toString();
                                        String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();

                                    %>
                            <li><a id="tasksHome" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTasksSearch.action"><img src="<s:url value="/includes/images/icons/addTask.png"/>" height="15" width="15">&nbsp;Tasks</a></li>
                                    <s:if test="#session.primaryrole == 4 || #session.primaryrole == 5 || #session.primaryrole == 6">
                                <li><a id="techReviewHome" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getTechReviewDetails.action"><img src="<s:url value="/includes/images/icons/review.png"/>" height="15" width="15">&nbsp;Tech&nbsp;Reviews</a></li>
                                    </s:if>
                            <li><a id="profileHome" href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountcontactedit.action?contactId=<%=usrId%>&accountSearchID=<%=orgId%>&flag=customerlogin"><img src="<s:url value="/includes/images/icons/editProfile.png"/>" height="15" width="15">&nbsp;Profile</a></li>

                        </ul>
                    </div>
                </div>
            </div>


            <div class="panel panel-default left-menu" id="accordian_services">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a id="utilitiesCustEmpLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#utilitiesMenu">
                            <i  class="fa fa-cogs leftBullet"></i>
                            <span class="badge pull-right"><i class="fa fa-sort-asc" style="color: white;"></i></span>
                            Utilities

                        </a>
                    </h4>
                </div>
                <div id="utilitiesMenu" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>

                            <li><a id="changePasswordUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/general/changeMyPassword.action"><img src="<s:url value="/includes/images/icons/changePass.png"/>" height="15" width="15">&nbsp;Change My Pwd</a></li>
                                    <s:if test="%{#session['usrgrpid']==1}">
                                     
                                <li><a id="requirementListUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/getLoginUserRequirementList.action?accountFlag=MyRequirements&orgid=<%=orgId%>&customerFlag=customer"><img src="<s:url value="/includes/images/icons/requirement.png"/>" height="15" width="15">&nbsp;Requirements&nbsp;List</a></li>
                                    </s:if>
                                    <s:if test="%{#session['usrgrpid']==2}">
                                <li><a id="allTimeSheetsUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/getAllTimeSheets.action"><img  src="<s:url value="/includes/images/icons/timesheet_icon.png"/>" height="15" width="15">&nbsp;All&nbsp;Time&nbsp;Sheets</a></li>
                                <li><a id="sowListUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/sag/sow/getSowList.action"><img src="<s:url value="/includes/images/icons/aggrement.png"/>" height="15" width="15">&nbsp;Contracts</a></li>
                                <li><a id="invoiceUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/sag/getInvoice.action"><img src="<s:url value="/includes/images/icons/invoiceImg.png"/>" height="15" width="15">&nbsp;Invoice</a></li>
                                <li><a id="outStandingUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/sag/getOutstandingInvoiceList.action"><img src="<s:url value="/includes/images/icons/invoiceImg.png"/>" height="15" width="15">&nbsp;Outstanding&nbsp;Invoice</a></li>
                                      
                                    </s:if>
                                    <s:if test="#session.primaryrole == 4 || #session.primaryrole == 6 || #session.primaryrole == 5">
                                <li><a id="teamTimesheetsUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/timesheets/teamTimesheet.action"><img src="<s:url value="/includes/images/icons/timesheet_icon.png"/>" height="15" width="15">&nbsp;Time&nbsp;Sheets</a></li>
                                <li><a id="teamTasksUtilities" href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/tasks/doTeamTasksSearch.action"><img src="<s:url value="/includes/images/icons/addTask.png"/>" height="15" width="15">&nbsp;Tasks</a></li>
                                    </s:if>

                        </ul>
                    </div>
                </div>
            </div>

        </div>


    </div>
</div>
