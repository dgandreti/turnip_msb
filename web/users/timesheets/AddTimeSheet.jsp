


<%-- 
    Document   : TimeSheet Add
    Created on : May 21, 2015, 1:55:08 AM
    Author     : miracle
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page import="com.mss.msp.usr.timesheets.UsrTimeSheetAction"%>


<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: TimeSheet Add Page</title>

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

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/timesheet.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

    </head>
    <body oncontextmenu="return false" onload="getProjets();
            onloadTotal()">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div>
            </header>         
            <div id="main">
                <section id="generalForm"><!--form-->
                    <div class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/> 
                            <!-- content start -->
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items" style="background-color:#F9F9F9">
                                    <div id="projects_popup">
                                        <div id="projectsOverlay">
                                            <div class="backgroundcolor">
                                                <table>
                                                    <tr><td><h4 style=""><font color="#ffffff">&nbsp;Present Projects</font></h4></td>
                                                    </tr>
                                                    <span class=" pull-right"><h5><a id="timesheetSearchBackButton" href="timesheetSearch.action" class="projects_popup_close" onclick="addTimeSheetOverlayClose()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                </table>
                                            </div>

                                            <s:form action="#" theme="simple" >
                                                <div class="overlaytimesheet">
                                                    <span><addTimesheerResult></addTimesheerResult></span>


                                                    <div class="inner-addSkillDiv-elements textfieldLabel">

                                                        <span style="display: none" id="checkbox1">
                                                            <s:checkbox cssClass="checkboxAlign" name="p1" id="p1" />&nbsp;&nbsp;<s:textfield name="projectOver1" id="projectOver1" cssClass="noBorder textLabel-i" value="" disabled="true" readonly="true" /></span>   
                                                        <span style="display: none" id="checkbox2">
                                                            <br><s:checkbox cssClass="checkboxAlign" name="p2" id="p2" />&nbsp;&nbsp;<s:textfield name="projectOver2" id="projectOver2" cssClass="noBorder textLabel-i" value="" disabled="true" readonly="true" /></span>       
                                                        <span style="display: none" id="checkbox3">
                                                            <br><s:checkbox cssClass="checkboxAlign" name="p3" id="p3" />&nbsp;&nbsp;<s:textfield name="projectOver3" id="projectOver3" cssClass="noBorder textLabel-i" value="" disabled="true" readonly="true" /></span>         
                                                        <span style="display: none" id="checkbox4">
                                                            <br><s:checkbox cssClass="checkboxAlign" name="p4" id="p4" />&nbsp;&nbsp;<s:textfield name="projectOver4" id="projectOver4" cssClass="noBorder textLabel-i" value="" disabled="true" readonly="true" /></span>         
                                                        <span style="display: none" id="checkbox5">
                                                            <br><s:checkbox cssClass="checkboxAlign" name="p5" id="p5" />&nbsp;&nbsp;<s:textfield name="projectOver5" id="projectOver5" cssClass="noBorder textLabel-i" value="" disabled="true" readonly="true" /></span>         


                                                    </div>
                                                </div>

                                                <div class="col-md-5 pull-right">
                                                    <a id="addTimesheetPageButton" href="../timesheets/addTimeSheet.action"  ><button  style="margin: 5px 0px;" type="button" class="add_searchButton form-control" value="" onclick="return projectsData()">&nbsp;Go&nbsp;&nbsp;<i class="fa fa-arrow-circle-o-right"></i></button></a>&nbsp;
                                                </div>
                                            </s:form>

                                        </div>
                                    </div>
                                    <div id="timesheetMisc_popup">
                                        <div id="timesheetMisc_Overlay">
                                            <div class="backgroundcolor">
                                                <table>
                                                    <tr><td><h4 style=""><font color="#ffffff">&nbsp;Miscellaneous</font></h4></td>
                                                    </tr>
                                                    <span class=" pull-right"><h5><a href="" id="timesheetMiscellinousClose" class="timesheetMisc_popup_close" onclick="addTimeSheetOverlayClose()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                </table>
                                            </div>

                                            <form action="#" theme="simple" >
                                                <div class="overlaytimesheetMis">
                                                    <span><addTimesheerResult></addTimesheerResult></span>


                                                    <div class="inner-addSkillDiv-elements textfieldLabel">


                                                        <s:checkbox cssClass="checkboxAlign" name="vacation" id="vacation" /><label class="labelStyle add-to" id="labelLevelStatusReq">Vacation</label> 

                                                        <br><s:checkbox cssClass="checkboxAlign" name="holiday" id="holiday" /><label class="labelStyle add-to" id="labelLevelStatusReq">Holiday</label>         
                                                    </div>
                                                </div>
                                                <div class="col-md-6"></div>
                                                <div class="col-md-6 pull-right">
                                                    <button id="timesheetMiscellaneousGo"  type="button" style="margin: 5px 0px;"  class="add_searchButton form-control" value="" onclick="return miscellaneousData()">&nbsp;Go&nbsp;&nbsp;<i class="fa fa-arrow-circle-o-right"></i></button>&nbsp; 
                                                </div>
                                            </form>

                                        </div>
                                    </div>

                                    <div class="col-sm-12">


                                        <div class="" id="profileBox">

                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <font color="#ffffff"> Add TimeSheet </font> 
                                                        <span class="pull-right"><a href="#" id="addTimesheetBackButton" onclick="history.back();
                                                                return false;"><i class="fa fa-undo"></i></a></span>

                                                    </h4>
                                                </div>

                                            </div>

                                        </div>

                                        <div class="col-sm-12">
                                            <s:form id="timeSheetsForm" action="AddTimesheet.action" method="post" theme="simple" >

                                                <div class="col-sm-12"> 
                                                    <span><edittimesheetserror></edittimesheetserror></span>
                                                </div>



                                                <s:hidden name="usr_id" id="usr_id" value=""/>
                                                <s:hidden name="timesheetFlag" id="timesheetFlag" value=""/>

                                                <div class="inner-addtaskdiv-elements1">
                                                    <span style="display: none" id="projectButton" > <a href="#" ><input type="button" class="timesheetbutton projects_popup_open pull-left" style="margin-right: 3px;" value="Projects" onclick="ProjectsOverlayOpen1()"></a> </span>
                                                    <a href="#" id="miscellaneousOverlayOpenButton" ><input type="button" class="timesheetbutton timesheetMisc_popup_open pull-left" value="Miscellaneous" onclick="MiscellaneousOverlayOpen()"></a>
                                                        <%-- <s:reset cssClass="timesheetbutton pull-right" style="margin-left:4px;" value="Clear" theme="simple"  />  --%>   
                                                </div>
                                                <br/>
                                                <br/>
                                                <div class="">
                                                    <div class="col-sm-4"><label class="labelStyle" id="labelLevelStatusReq">Week Start Date</label> <s:textfield cssClass="timesheetdatebox" name="timeSheetStartDate" id="timeSheetStartDate" placeholder="StartDate" value="%{timeSheetVTO.timeSheetStartDate}" onchange="return betweenDate();" readonly="true" cssStyle="z-index: 10000004;" tabindex="1"/></div>
                                                    <div class="col-sm-4">  <label class="labelStyle" id="labelLevelStatusReq">Week End Date</label><s:textfield cssClass="timesheetdatebox" name="timeSheetEndDate" value="%{timeSheetVTO.timeSheetEndDate}" id="timeSheetEndDate" placeholder="EndDate" onchange="return betweenDate();" readonly="true" tabindex="1"/></div>
                                                    <div class="col-sm-4">   <label class="labelStyle" id="labelLevelStatusReq">Submit Date</label> <s:textfield cssClass="timesheetdatebox" name="timeSheetSubmittedDate" id="timeSheetSubmittedDate" placeholder="SubmitDate" value="%{timeSheetVTO.submittedDate}" onchange="return betweenDate();" cssStyle="z-index: 10000004;" readonly="true" tabindex="1"/></div>
                                                </div>
                                                <%-- <div class="inner-addtaskdiv-elements1">
                                                     <a href="#" ><input type="button" class="timesheetbutton projects_popup_open" value="new" onclick="ProjectsOverlayOpen1()"></a>
                                                     <a href="#" ><input type="button" class="timesheetbutton timesheetMisc_popup_open" value="Miscellaneous" onclick="MiscellaneousOverlayOpen()"></a>
                                                 </div>--%>
                                                <div class="col-sm-12">            
                                                    <div class="inner-addtaskdiv-elements scrollTimeSheet" id="addtimesheet" >
                                                        <table class=""> 
                                                            <tr>
                                                                <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq" ></label></td>
                                                                <td><s:textfield name="weeklyDates1" id="weeklyDates1" cssClass="noBorder tsDate tsColor" value="%{timeSheetVTO.weeklyDates1}" readonly="true" tabindex="-1"/></td>
                                                                <td><s:textfield name="weeklyDates2" id="weeklyDates2" cssClass="noBorder tsDate" value="%{timeSheetVTO.weeklyDates2}" readonly="true" tabindex="-1"/></td>
                                                                <td><s:textfield name="weeklyDates3" id="weeklyDates3" cssClass="noBorder tsDate" value="%{timeSheetVTO.weeklyDates3}" readonly="true" tabindex="-1"/></td>
                                                                <td><s:textfield name="weeklyDates4" id="weeklyDates4" cssClass="noBorder tsDate" value="%{timeSheetVTO.weeklyDates4}" readonly="true" tabindex="-1"/></td>
                                                                <td><s:textfield name="weeklyDates5" id="weeklyDates5" cssClass="noBorder tsDate" value="%{timeSheetVTO.weeklyDates5}" readonly="true" tabindex="-1"/></td>
                                                                <td><s:textfield name="weeklyDates6" id="weeklyDates6" cssClass="noBorder tsDate" value="%{timeSheetVTO.weeklyDates6}" readonly="true" tabindex="-1"/></td>
                                                                <td><s:textfield name="weeklyDates7" id="weeklyDates7" cssClass="noBorder tsDate tsColor" value="%{timeSheetVTO.weeklyDates7}" readonly="true" tabindex="-1"/></td>

                                                            </tr>
                                                            <tr>
                                                                <td><label class="labelStyle ReqinputStyleTime" id="labelLevelStatusReq"> Projects</label></td>

                                                                <td> <label class="labelStyle ReqinputStyleTimeSheet timesheet tsColor" id="labelLevelStatusReq">Sun</label></td>
                                                                <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Mon</label></td>
                                                                <td> <label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Tue</label></td>
                                                                <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Wed</label></td>
                                                                <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Thu</label></td>
                                                                <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Fri</label></td>
                                                                <td><label class="labelStyle ReqinputStyleTimeSheet timesheet tsColor" id="labelLevelStatusReq">Sat</label></td>
                                                                <td><label class="labelStyle-i ReqinputStyleTimeSheet" id="labelLevelStatusReq">Total</label></td>
                                                            </tr> 
                                                            &nbsp;&nbsp;&nbsp; 


                                                            <tr style="display:none" id ="projectid1">

                                                                <td>

                                                                    <s:textfield name="project1" id="project1" cssClass="noBorder textLabel" value="" disabled="true" readonly="true" />
                                                                    <s:hidden name="project1key" id="project1key" cssClass="noBorder textLabel" value="0" readonly="true" /></td>
                                                                <td><s:textfield name="projectNameSun1" id="projectNameSun1" cssClass="form-control SmallTextBox_Time" onchange="return projectSun1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameMon1" id="projectNameMon1" cssClass="form-control SmallTextBox_Time" onchange="return projectMon1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameTue1" id="projectNameTue1" cssClass="form-control SmallTextBox_Time" onchange="return projectTue1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameWed1" id="projectNameWed1" cssClass="form-control SmallTextBox_Time" onchange="return projectWed1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameThu1" id="projectNameThu1" cssClass="form-control SmallTextBox_Time" onchange="return projectThu1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameFri1" id="projectNameFri1" cssClass="form-control SmallTextBox_Time" onchange="return projectFri1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameSat1" id="projectNameSat1" cssClass="form-control SmallTextBox_Time" onchange="return projectSat1();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameAll1" id="projectNameAll1" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr>
                                                            <tr style="display:none" id ="projectid2">
                                                                <td><s:textfield name="project2" id="project2" cssClass="noBorder textLabel" value="" disabled="true" />
                                                                    <s:hidden name="project2key" id="project2key" cssClass="noBorder textLabel" value="0" readonly="true" /></td>
                                                                <td><s:textfield name="projectNameSun2" id="projectNameSun2" cssClass="form-control SmallTextBox_Time" onchange="return projectSun2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameMon2" id="projectNameMon2" cssClass="form-control SmallTextBox_Time" onchange="return projectMon2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameTue2" id="projectNameTue2" cssClass="form-control SmallTextBox_Time" onchange="return projectTue2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameWed2" id="projectNameWed2" cssClass="form-control SmallTextBox_Time" onchange="return projectWed2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameThu2" id="projectNameThu2" cssClass="form-control SmallTextBox_Time" onchange="return projectThu2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameFri2" id="projectNameFri2" cssClass="form-control SmallTextBox_Time" onchange="return projectFri2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameSat2" id="projectNameSat2" cssClass="form-control SmallTextBox_Time" onchange="return projectSat2();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameAll2" id="projectNameAll2" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr>
                                                            <tr style="display:none" id ="projectid3">
                                                                <td><s:textfield name="project3" id="project3" cssClass="noBorder textLabel" value="" disabled="true" />
                                                                    <s:hidden name="project3key" id="project3key" cssClass="noBorder textLabel" value="0" readonly="true" /></td>
                                                                <td><s:textfield name="projectNameSun3" id="projectNameSun3" cssClass="form-control SmallTextBox_Time" onchange="return projectSun3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameMon3" id="projectNameMon3" cssClass="form-control SmallTextBox_Time" onchange="return projectMon3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameTue3" id="projectNameTue3" cssClass="form-control SmallTextBox_Time" onchange="return projectTue3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameWed3" id="projectNameWed3" cssClass="form-control SmallTextBox_Time" onchange="return projectWed3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameThu3" id="projectNameThu3" cssClass="form-control SmallTextBox_Time" onchange="return projectThu3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameFri3" id="projectNameFri3" cssClass="form-control SmallTextBox_Time" onchange="return projectFri3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameSat3" id="projectNameSat3" cssClass="form-control SmallTextBox_Time" onchange="return projectSat3();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameAll3" id="projectNameAll3" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr>
                                                            <tr style="display:none" id ="projectid4">
                                                                <td><s:textfield name="project4" id="project4" cssClass="noBorder textLabel" value="" disabled="true" />
                                                                    <s:hidden name="project4key" id="project4key"  value="0" readonly="true" /></td>
                                                                <td><s:textfield name="projectNameSun4" id="projectNameSun4" cssClass="form-control SmallTextBox_Time" onchange="return projectSun4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameMon4" id="projectNameMon4" cssClass="form-control SmallTextBox_Time" onchange="return projectMon4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameTue4" id="projectNameTue4" cssClass="form-control SmallTextBox_Time" onchange="return projectTue4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameWed4" id="projectNameWed4" cssClass="form-control SmallTextBox_Time" onchange="return projectWed4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameThu4" id="projectNameThu4" cssClass="form-control SmallTextBox_Time" onchange="return projectThu4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameFri4" id="projectNameFri4" cssClass="form-control SmallTextBox_Time" onchange="return projectFri4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameSat4" id="projectNameSat4" cssClass="form-control SmallTextBox_Time" onchange="return projectSat4();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameAll4" id="projectNameAll4" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr>
                                                            <tr style="display:none" id ="projectid5">
                                                                <td><s:textfield name="project5" id="project5" cssClass="noBorder textLabel" value="" disabled="true" />
                                                                    <s:hidden name="project5key" id="project5key" cssClass="noBorder textLabel" value="0" readonly="true" /></td>
                                                                <td><s:textfield name="projectNameSun5" id="projectNameSun5" cssClass="form-control SmallTextBox_Time" onchange="return projectSun5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameMon5" id="projectNameMon5" cssClass="form-control SmallTextBox_Time" onchange="return projectMon5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameTue5" id="projectNameTue5" cssClass="form-control SmallTextBox_Time" onchange="return projectTue5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameWed5" id="projectNameWed5" cssClass="form-control SmallTextBox_Time" onchange="return projectWed5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameThu5" id="projectNameThu5" cssClass="form-control SmallTextBox_Time" onchange="return projectThu5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameFri5" id="projectNameFri5" cssClass="form-control SmallTextBox_Time" onchange="return projectFri5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameSat5" id="projectNameSat5" cssClass="form-control SmallTextBox_Time" onchange="return projectSat5();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="projectNameAll5" id="projectNameAll5" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1" maxLength="4"/></td>
                                                            </tr>



                                                            <tr>
                                                                <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq">Internal</label></td>
                                                                <td><s:textfield name="internalSun" id="internalSun" cssClass=" form-control SmallTextBox_Time" onchange="return internalSunday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalMon" id="internalMon" cssClass=" form-control SmallTextBox_Time" onchange="return internalMonday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalTue" id="internalTue" cssClass="  form-control SmallTextBox_Time" onchange="return internalTuesday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalWed" id="internalWed" cssClass=" form-control SmallTextBox_Time" onchange="return internalWednesday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalThu" id="internalThu" cssClass=" form-control SmallTextBox_Time" onchange="return internalThursday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalFri" id="internalFri" cssClass=" form-control SmallTextBox_Time" onchange="return internalFriday();"  value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalSat" id="internalSat" cssClass=" form-control SmallTextBox_Time" onchange="return internalSaturday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="internalAll" id="internalAll" cssClass=" form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr> 

                                                            <tr  style="display: none" id="miscellaneousVacation">
                                                                <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq">Vacation</label></td>
                                                                <td><s:textfield name="vacationSun" id="vacationSun" cssClass="form-control SmallTextBox_Time" onchange="return vacationSunday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationMon" id="vacationMon" cssClass="form-control SmallTextBox_Time" onchange="return vacationMonday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationTue" id="vacationTue" cssClass="form-control SmallTextBox_Time" onchange="return vacationTuesday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationWed" id="vacationWed" cssClass="form-control SmallTextBox_Time" onchange="return vacationWednesday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationThu" id="vacationThu" cssClass="form-control SmallTextBox_Time" onchange="return vacationThursday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationFri" id="vacationFri" cssClass="form-control SmallTextBox_Time" onchange="return vacationFriday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationSat" id="vacationSat" cssClass="form-control SmallTextBox_Time" onchange="return vacationSaturday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="vacationAll" id="vacationAll" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr> 
                                                            <tr style="display: none" id="miscellaneousHoliday">
                                                                <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq">Holiday</label></td>
                                                                <td><s:textfield name="holidaySun" id="holidaySun" cssClass="form-control SmallTextBox_Time" onchange="return holidaySunday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidayMon" id="holidayMon" cssClass="form-control SmallTextBox_Time" onchange="return holidayMonday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidayTue" id="holidayTue" cssClass="form-control SmallTextBox_Time" onchange="return holidayTuesday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidayWed" id="holidayWed" cssClass="form-control SmallTextBox_Time" onchange="return holidayWednesday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidayThu" id="holidayThu" cssClass="form-control SmallTextBox_Time" onchange="return holidayThursday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidayFri" id="holidayFri" cssClass="form-control SmallTextBox_Time" onchange="return holidayFriday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidaySat" id="holidaySat" cssClass="form-control SmallTextBox_Time" onchange="return holidaySaturday();" value="0.0" onkeypress="return acceptNumbers(event)" tabindex="1" maxLength="4"/></td>
                                                                <td><s:textfield name="holidayAll" id="holidayAll" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr> 

                                                            <tr>
                                                                <td><label class="labelStyle-i ReqinputStyleTime add-to" id="labelLevelStatusReq">Total</label></td>
                                                                <td><s:textfield name="totalSun" id="totalSun" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalMon" id="totalMon" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalTue" id="totalTue" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalWed" id="totalWed" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalThu" id="totalThu" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalFri" id="totalFri" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalSat" id="totalSat" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                                <td><s:textfield name="totalAll" id="totalAll" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1"/></td>
                                                            </tr> 
                                                        </table>

                                                    </div>  
                                                    <div class="row">



                                                        <div class="col-sm-5 col-lg-4 col-md-5">
                                                            <div class="row">
                                                                <div class="col-sm-12 pull-left ">     <label class="labelStyle-i add-to  contact_search" id="labelLevelStatusReq" style="float: left">Total Billable Hrs</label>
                                                                     <s:textfield name="totalBillHrs" id="totalBillHrs" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1" style="float: left"/>
                                                                </div>
                                                                <div class="col-sm-12 pull-left">  <label class="labelStyle-i add-to  contact_search" id="labelLevelStatusReq" style="float: left">Total Holiday Hrs</label>
                                                                    <s:textfield name="totalHolidayHrs" id="totalHolidayHrs" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1" style="float: left"/>
                                                                </div>
                                                                <div class="col-sm-12 pull-left"><label class="labelStyle-i add-to  contact_search" id="labelLevelStatusReq" style="float: left">Total Vacation Hrs</label>
                                                                      <s:textfield name="totalVacationHrs" id="totalVacationHrs" cssClass="form-control SmallTextBox_Time" readonly="true" value="0.0" tabindex="-1" style="float: left"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-7 col-sm-7 col-lg-8">
                                                            <div class="col-sm-12"> 
                                                                <div class=" form-group req-textarea">
                                                                    <label class="labelStyle" id="labelLevelStatusReq">Notes</label> <s:textarea name="timeSheetNotes" id="timeSheetNotes" cssClass="commentsStyle" value="" placeholder="Enter Here" rows="3" onkeydown="timeSheetsNotes(this)" onblur="removeErrorMessages()" tabindex="1"/>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="charNum" id="notes" style="width:80%"></div>
                                                                </div>
                                                            </div>
                                                        </div>



                                                        <div class="col-sm-12 pull-right"> 

                                                            <div class="col-sm-3 col-md-3 col-lg-2  pull-right">
                                                                <a href="#" id="clearTimesheetsButton" ><button type="button" class="add_searchButton form-control fa fa-eraser pull-right" tabindex="4" value="" style="margin: 5px 0px;width:116px" onclick="clearTimesheets()">&nbsp;Clear</button></a>
                                                                <s:hidden name="tempVar" id="tempVar" value=""/>
                                                            </div>

                                                            <div class="col-sm-3 col-md-3 col-lg-2  pull-right">
                                                                <s:submit type="button" id="saveTimesheetButton" cssClass="add_searchButton form-control pull-right"  value="" theme="simple" tabindex="3" cssStyle="margin:5px 0px;width:116px"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>

                                                                </div>
                                                                <div class="col-sm-4 col-md-3 col-lg-2  pull-right">
                                                                <s:submit type="button" id="save&submitButton" cssClass="add_searchButton form-control pull-right"  value="" theme="simple" cssStyle="margin:5px 0px;width:116px" tabindex="2" onclick="setTemVar1()"><i class="fa fa-check-circle-o"></i>&nbsp;Save&Submit</s:submit>
                                                                </div>
                                                            </div>
                                                            <div class="row">    <s:label><font color="red">*NOTE: After submitting this timesheet you can't edit.</font></s:label></div>






                                                        </div>  
                                                    </div>   
                                                <s:token />
                                            </s:form>       




                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </section>

            </div>
        </div>

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
    </body>
</html>

