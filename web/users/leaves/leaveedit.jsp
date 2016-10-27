<%-- 
   Document   : LeaveEdit
   Created on : Apr 14, 2015, 7:00:40 PM
   Author     : miracle
--%>

<%@page import="com.mss.msp.usr.leaves.UserLeavesAction"%>
<%@ page import="com.mss.msp.usersdata.UserVTO"%>
<%@ page import="com.mss.msp.usr.leaves.LeavesVTO"%>
<%@ page import="com.mss.msp.usr.leaves.EmpLeaves"%>
<%@ page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Leave Edit Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/addLeaveOverlay.js"/>'></script>


    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="doOnLoadLeave();statusValid();">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div>

        </header>

        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->
                    <div class="col-md-9 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff"> Edit Leave Request </font>
                                                <span class="pull-right"><a href="#"><img onclick="history.back();return false;" src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></a></span>
                                            </h4>
                                        </div>

                                    </div>
                                    <!-- content start -->
                                  <!--
                                        int leaveId = Integer.parseInt(request.getParameter("leave_id"));
                                        System.out.println("======================>" + leaveId);
                                        
                                    -->

                                    <div class="col-sm-10">
                                        <s:form action="updateLeaves" method="post" theme="simple" >

                                            <br>
                                            <span cellspacing="30">
                                                <span><editleaveerror></editleaveerror></span>
                                                <div class="inner-addtaskdiv-elements">
                                                    <s:text name="Employee Name :"/>
                                                    <s:hidden name="leave_id" id="leave_id" value="%{empLeaves.leaveId}"/>
                                                    <s:hidden id="empName" name="empName" />
                                                        <s:property value="%{empLeaves.empName}"/>
                                                    
                                                    <%-- <s:property value="#session.firstName"/>&nbsp;<s:property value="#session.lastName"/>--%>
                                                         <s:hidden name="isManager" id="isManager" value="%{#session['is_manager']}"/>
                                                          <s:hidden name="isTeamLead" id="isTeamLead" value="%{#session['is_team_lead']}"/>
                                                          <s:hidden name="user" id="user" value="%{empLeaves.user}"/>
                                                            <s:hidden name="leaveflag" id="leaveFlag" value="%{leaveflag}"/>
                                                            <s:hidden name="userId" id="userId" value="%{#session['userId']}"/>
                                                            <s:hidden name="leavestatus" id="leavestatus" value="%{empLeaves.status}"/>
                                                    </div>
                                                    
                                                    <div class="inner-addtaskdiv-elements">
                                                        <label class="labelStyle"> Start Date:</label> <s:textfield cssClass="leavebox field-margin dateImage" name="leaveEditFrmDate" id="leaveEditFrmDate" placeholder="StartDate" value="%{empLeaves.leaveEditFrmDate}" onkeypress="return editleavesDateValidation()" cssStyle="z-index: 10000004;" />
                                                        <label class="labelStyle"> End Date:</label><s:textfield cssClass="leavebox field-margin dateImage" name="leaveEditEndDate" value="%{empLeaves.leaveEditEndDate}" id="leaveEditEndDate" placeholder="EndDate" onkeyPress="return editleavesDateValidation()" />
                                                    

                                                    
                                                        <label class="labelStyle">Status:</label><s:select name="status" id="status" value="%{empLeaves.status}" list="#@java.util.LinkedHashMap@{'O':'Applied','C':'canceled','A':'approved','R':'rejected'}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle field-margin" disabled="true"/>
                                                   
                                                        <label class="labelStyle"> Reports To:</label> <s:textfield cssClass=" field-margin" id="reporsto" type="text" value="%{empLeaves.reportsTo}" name="reportsTo" placeholder="Reports To" disabled="true"/>
                                                        <label class="labelStyle"> Leave Type:</label><s:select name="leaveType" id="leavetype" list="#@java.util.LinkedHashMap@{'PA':'Post Approval','CT':'Comptime','VA':'Vacation','TO':'Timeoff','CL':'Cancel-Leave'}"  value="%{empLeaves.leaveType}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle field-margin"/>
                                                    </div>
                                                    <%--s:textarea name="reason" id="reason" cssClass="textbox "  value="%{currentLeave.reason}" onchange="fieldLengthValidator(this);" placeholder="Reason" cols="60" rows="5" /--%>
                                                    <div class="inner-addtaskdiv-elements">
                                                        <label class="labelStyle">Reason :</label> <s:textarea name="alertMessage" id="alertMessage" cssClass="titleStyle" value="%{empLeaves.alertMessage}" placeholder="Enter Reason Here" cols="40" rows="3" onkeyup=" checkCharacters(this)" />
                                                    </div>
                                                    <div id="charNum"></div>
                                                    <div class="leavefield-marigin">
                                                        
                                                    <s:submit cssClass=" col-sm-offset-12 btn cssbutton" id="update" onclick="return editleave()" value="Update" theme="simple"/>
                                                    <!--s:submit  cssClass="btn cssbutton " onclick="goBack()" value="go back" /-->
                                                    </div>            
                                            </s:form>       
                                    

                                    </div>
                                </div>
                            </div>

                            <%--close of future_items--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content end -->
</section><!--/form-->
<footer id="footer"><!--Footer-->
    <div class="footer-bottom" id="footer_bottom">
        <div class="container">
            <s:include value="/includes/template/footer.jsp"/>
        </div>
    </div>
</footer><!--/Footer-->
<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
</body>
</html>
