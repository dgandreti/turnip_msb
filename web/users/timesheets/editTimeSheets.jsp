<%-- 
    Document   : requirementedit
    Created on : May 5, 2015, 1:55:08 AM
    Author     : miracle
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@page import="com.mss.msp.usr.timesheets.TimesheetVTO"%>


<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Timesheets Edit Page</title>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/timesheet.js"/>"></script>
    </head>
    <s:if test="timeSheetVTO.timeSheetStatus=='Entered'">
        <body oncontextmenu="return false" onload ="getProjets();
                onloadTotal();
                getProjectsEdit();
                onloadeditMis();">
        </s:if>
        <s:else>     
        <body oncontextmenu="return false" onload="getProjectsEdit();
                onloadTotal();
                onloadeditMis();">
        </s:else>
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
                                <div class="features_items">
                                    <div id="projects_popup">
                                        <div id="projectsOverlay">
                                            <div class="backgroundcolor" >
                                                <table>
                                                    <tr><td><h4 style=""><font color="#ffffff">&nbsp;Present Projects</font></h4></td>
                                                    </tr>
                                                    <span class=" pull-right"><h5><a href="" id="projectPopUpCloseButton" class="projects_popup_close" onclick="addTimeSheetOverlayClose()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                </table>
                                            </div>

                                            <form action="#" theme="simple" >
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

                                                    <a id="addProjectGoButton" href="../timesheets/addTimeSheet.action" ><button  style="margin: 5px 0px;" type="button" class="add_searchButton form-control" value="" onclick="return projectsData()">&nbsp;Go&nbsp;&nbsp;<i class="fa fa-arrow-circle-o-right"></i></button></a>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                    <div id="timesheetMisc_popup">
                                        <div id="timesheetMisc_Overlay">
                                            <div class="backgroundcolor">
                                                <table>
                                                    <tr><td><h4 style=""><font color="#ffffff">&nbsp;Miscellaneous</font></h4></td>
                                                    </tr>
                                                    <span class=" pull-right"><h5><a href="" id="miscellineousOverlayCloseButton" class="timesheetMisc_popup_close" onclick="addTimeSheetOverlayClose()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                </table>
                                            </div>

                                            <form action="#" theme="simple" >
                                                <div class="overlaytimesheetMis">
                                                    <span><addTimesheerResult></addTimesheerResult></span>
                                                    <div class="inner-addSkillDiv-elements textfieldLabel">
                                                        <s:checkbox cssClass="checkboxAlign" name="vacation" id="vacation" /> <label class="labelStyle add-to" id="labelLevelStatusReq" >Vacation</label>
                                                        <br><s:checkbox cssClass="checkboxAlign" name="holiday" id="holiday" /><label class="labelStyle add-to" id="labelLevelStatusReq" >Holiday</label>         
                                                    </div>
                                                </div>
                                                <div class="col-lg-6"></div>
                                                <div class="col-md-5 pull-right">
                                                    <button type="button" style="margin: 5px 0px;" id="miscellaneousOverlayGoButton" class="add_searchButton form-control" value="Go" onclick="return miscellaneousData()">&nbsp;Go&nbsp;&nbsp;<i class="fa fa-arrow-circle-o-right"></i></button> 
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="col-lg-14 ">


                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <font color="#ffffff"> Edit TimeSheets </font> 
                                                        <s:if test="timesheetFlag=='My'">
                                                            <span class="pull-right"><a href="#" id="timesheetSearchActionBackButton" onclick="window.location = 'timesheetSearch.action'"><i class="fa fa-undo"></i></a></span>
                                                                </s:if>
                                                                <s:elseif test="timesheetFlag=='Operations'">
                                                            <span class="pull-right"><a href="#" id="allTimesheetsActionBackButton" onclick="window.location = 'getAllTimeSheets.action'"><i class="fa fa-undo"></i></a></span>   
                                                                </s:elseif>
                                                                <s:else>
                                                            <span class="pull-right"><a href="#" id="teamTimesheetsActionBackButton" onclick="window.location = 'teamTimesheet.action'"><i class="fa fa-undo"></i></a></span>    
                                                                </s:else>
                                                    </h4>
                                                </div>

                                            </div>
                                            <div class="col-sm-12">
                                                <s:form name="timeSheetsForm" action="editTimeSheets" method="post" theme="simple" >
                                                    <div class="col-sm-12"> 
                                                        <span><edittimesheetserror></edittimesheetserror></span>
                                                    </div>

                                                    <span cellspacing="5">
                                                        <div class="inner-addtaskdiv-elements">
                                                            <s:if test="timesheetFlag=='Team'">
                                                                <s:text name="Employee Name :"/>
                                                                <s:property value="%{empName}"/>
                                                            </s:if>
                                                            <s:hidden name="timesheetid" id="timesheetid" value="%{timeSheetVTO.timesheetid}"/>
                                                            <s:hidden name="usr_id" id="usr_id" value="%{timeSheetVTO.usr_id}"/>
                                                            <s:hidden name="timesheetFlag" id="timesheetFlag" value="%{timesheetFlag}"/>

                                                        </div>


                                                        <s:if test="timesheetFlag=='My'">
                                                            <!--s:if test="timeSheetVTO.timeSheetStatus=='Entered'||timeSheetVTO.timeSheetStatus=='Disapproved'"-->

                                                            <s:if test="timeSheetVTO.timeSheetStatus=='Entered'||timeSheetVTO.timeSheetStatus=='Disapproved'">
                                                                <div class="inner-addtaskdiv-elements1" style="padding:0">
                                                                    <span style="display: none" id="projectButton" > <a href="#" id="timesheetProjectPopupOpenButton" ><input type="button" class="timesheetbutton projects_popup_open pull-left " style="margin-left:-2px;margin-right: 3px;" value="Projects" onclick="ProjectsOverlayOpen1()"></a></span>
                                                                    <a href="#" id="timesheetMiscPopupOpenButton" ><input type="button"  class="timesheetbutton timesheetMisc_popup_open pull-left" value="Miscellaneous" onclick="MiscellaneousOverlayOpen()"></a>
                                                                </div>
                                                                <br/>
                                                                <br/>
                                                            </s:if>
                                                        </s:if>
                                                        <!--/s:if-->


                                                        <div class="inner-addtaskdiv-elements1" style="padding:0">
                                                            <div class="col-sm-4 col-sx-4">  <label class="labelStyle" id="labelLevelStatusReq">Week Start Date</label> <s:textfield cssClass="timesheetdatebox" name="timeSheetStartDate" id="timeSheetStartDate" placeholder="StartDate" value="%{timeSheetVTO.timeSheetStartDate}" onchange="return betweenDate();" readonly="true" cssStyle="z-index: 10000004;" tabindex="1" /></div>
                                                            <div class="col-sm-4 col-sx-4"> <label class="labelStyle" id="labelLevelStatusReq">Week End Date</label>&nbsp;<s:textfield cssClass="timesheetdatebox" name="timeSheetEndDate" value="%{timeSheetVTO.timeSheetEndDate}" id="timeSheetEndDate" placeholder="EndDate" onchange="return betweenDate();" readonly="true" tabindex="1"/></div>
                                                            <s:if test="timeSheetVTO.timeSheetStatus!='Approved'">
                                                                <div class="col-sm-4 col-sx-4">    <label class="labelStyle" id="labelLevelStatusReq"> Date Submitted</label><s:textfield cssClass="timesheetdatebox" name="timeSheetSubmittedDate" value="%{timeSheetVTO.timeSheetSubmittedDate}" id="timeSheetSubmittedDate" placeholder="EndDate" readonly="true" tabindex="1"/></div>
                                                            </s:if>    
                                                        </div>
                                                        <s:if test="timeSheetVTO.timeSheetStatus=='Approved'">
                                                            <div class="inner-addtaskdiv-elements1" style="padding:0">
                                                                <div class="col-sm-4">  <label class="labelStyle" id="labelLevelStatusReq"> Date Submitted</label>&nbsp;<s:textfield cssClass="timesheetdatebox" name="timeSheetSubmittedDate" value="%{timeSheetVTO.timeSheetSubmittedDate}" id="timeSheetSubmittedDate" placeholder="EndDate" readonly="true" tabindex="1"/></div>
                                                                <div class="col-sm-4">    <label class="labelStyle" id="labelLevelStatusReq"> Date Approved</label> <s:textfield cssClass="timesheetdatebox" name="timeSheetApprovedDate" id="timeSheetApprovedDate" placeholder="StartDate" value="%{timeSheetVTO.timeSheetApprovedDate}" readonly="true" cssStyle="z-index: 10000004;" tabindex="1"/></div>
                                                            </div>
                                                        </s:if> 
                                                        <s:hidden name="timeSheetApprovedDate" id="timeSheetApprovedDate" value="%{timeSheetVTO.timeSheetApprovedDate}"/>
                                                        <div class="col-sm-12">  

                                                            <div class="inner-addtaskdiv-elements scrollTimeSheet" >
                                                                <table id="timesheetTable" class="">
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
                                                                        <td><label class="labelStyle ReqinputStyleTime " id="labelLevelStatusReq"> Projects</label></td>
                                                                        <td> <label class="labelStyle ReqinputStyleTimeSheet timesheet tsColor" id="labelLevelStatusReq">Sun</label></td>
                                                                        <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Mon</label></td>
                                                                        <td> <label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Tue</label></td>
                                                                        <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Wed</label></td>
                                                                        <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Thu</label></td>
                                                                        <td><label class="labelStyle ReqinputStyleTimeSheet timesheet" id="labelLevelStatusReq">Fri</label></td>
                                                                        <td><label class="labelStyle ReqinputStyleTimeSheet timesheet tsColor" id="labelLevelStatusReq">Sat</label></td>
                                                                        <td><label class="labelStyle-i ReqinputStyleTimeSheet" style="padding-left: 8px;" id="labelLevelStatusReq">Total</label></td>
                                                                    </tr>

                                                                    <tr style="display:none" id ="projectid1">
                                                                        <td><s:textfield name="project1" id="project1" cssClass="noBorder textLabel " value="" disabled="true" /></td>
                                                                        <s:hidden name="project1key" id="project1key" value="%{timeSheetVTO.project1key}"/>

                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSun1}" name="projectNameSun1" id="projectNameSun1" cssClass="form-control SmallTextBox_Time" onchange="return projectSun1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameMon1}" name="projectNameMon1" id="projectNameMon1" cssClass="form-control SmallTextBox_Time" onchange="return projectMon1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameTue1}" name="projectNameTue1" id="projectNameTue1" cssClass="form-control SmallTextBox_Time" onchange="return projectTue1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameWed1}" name="projectNameWed1" id="projectNameWed1" cssClass="form-control SmallTextBox_Time" onchange="return projectWed1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameThu1}" name="projectNameThu1" id="projectNameThu1" cssClass="form-control SmallTextBox_Time" onchange="return projectThu1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameFri1}" name="projectNameFri1" id="projectNameFri1" cssClass="form-control SmallTextBox_Time" onchange="return projectFri1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSat1}" name="projectNameSat1" id="projectNameSat1" cssClass="form-control SmallTextBox_Time" onchange="return projectSat1();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="projectNameAll1" id="projectNameAll1" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr>
                                                                    <tr style="display:none" id ="projectid2">
                                                                        <td><s:textfield name="project2" id="project2" cssClass="noBorder textLabel" value="" disabled="true" /></td>
                                                                        <s:hidden name="project2key" id="project2key" value="%{timeSheetVTO.project2key}"/>

                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSun2}" name="projectNameSun2" id="projectNameSun2" cssClass="form-control SmallTextBox_Time" onchange="return projectSun2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4" /></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameMon2}" name="projectNameMon2" id="projectNameMon2" cssClass="form-control SmallTextBox_Time" onchange="return projectMon2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4" /></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameTue2}" name="projectNameTue2" id="projectNameTue2" cssClass="form-control SmallTextBox_Time" onchange="return projectTue2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameWed2}" name="projectNameWed2" id="projectNameWed2" cssClass="form-control SmallTextBox_Time" onchange="return projectWed2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameThu2}" name="projectNameThu2" id="projectNameThu2" cssClass="form-control SmallTextBox_Time" onchange="return projectThu2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameFri2}" name="projectNameFri2" id="projectNameFri2" cssClass="form-control SmallTextBox_Time" onchange="return projectFri2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSat2}" name="projectNameSat2" id="projectNameSat2" cssClass="form-control SmallTextBox_Time" onchange="return projectSat2();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="projectNameAll2" id="projectNameAll2" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr>
                                                                    <tr style="display:none" id ="projectid3">
                                                                        <td><s:textfield name="project3" id="project3" cssClass="noBorder textLabel" value="" disabled="true" /></td>
                                                                        <s:hidden name="project3key" id="project3key" value="%{timeSheetVTO.project3key}"/>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSun3}" name="projectNameSun3" id="projectNameSun3" cssClass="form-control SmallTextBox_Time" onchange="return projectSun3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameMon3}" name="projectNameMon3" id="projectNameMon3" cssClass="form-control SmallTextBox_Time" onchange="return projectMon3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameTue3}" name="projectNameTue3" id="projectNameTue3" cssClass="form-control SmallTextBox_Time" onchange="return projectTue3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameWed3}" name="projectNameWed3" id="projectNameWed3" cssClass="form-control SmallTextBox_Time" onchange="return projectWed3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameThu3}" name="projectNameThu3" id="projectNameThu3" cssClass="form-control SmallTextBox_Time" onchange="return projectThu3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameFri3}" name="projectNameFri3" id="projectNameFri3" cssClass="form-control SmallTextBox_Time" onchange="return projectFri3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSat3}" name="projectNameSat3" id="projectNameSat3" cssClass="form-control SmallTextBox_Time" onchange="return projectSat3();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="projectNameAll3" id="projectNameAll3" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr>
                                                                    <tr style="display:none" id ="projectid4">
                                                                        <td><s:textfield name="project4" id="project4" cssClass="noBorder textLabel" value="" disabled="true" /></td>
                                                                        <s:hidden name="project4key" id="project4key" value="%{timeSheetVTO.project4key}"/>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSun4}" name="projectNameSun4" id="projectNameSun4" cssClass="form-control SmallTextBox_Time" onchange="return projectSun4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameMon4}" name="projectNameMon4" id="projectNameMon4" cssClass="form-control SmallTextBox_Time" onchange="return projectMon4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameTue4}" name="projectNameTue4" id="projectNameTue4" cssClass="form-control SmallTextBox_Time" onchange="return projectTue4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameWed4}" name="projectNameWed4" id="projectNameWed4" cssClass="form-control SmallTextBox_Time" onchange="return projectWed4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameThu4}" name="projectNameThu4" id="projectNameThu4" cssClass="form-control SmallTextBox_Time" onchange="return projectThu4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameFri4}" name="projectNameFri4" id="projectNameFri4" cssClass="form-control SmallTextBox_Time" onchange="return projectFri4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSat4}" name="projectNameSat4" id="projectNameSat4" cssClass="form-control SmallTextBox_Time" onchange="return projectSat4();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="projectNameAll4" id="projectNameAll4" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr>
                                                                    <tr style="display:none" id ="projectid5">
                                                                        <td><s:textfield name="project5" id="project5" cssClass="noBorder textLabel" value="" disabled="true" /></td>
                                                                        <s:hidden name="project5key" id="project5key" value="%{timeSheetVTO.project5key}"/>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSun5}" name="projectNameSun5" id="projectNameSun5" cssClass="form-control SmallTextBox_Time" onchange="return projectSun5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameMon5}" name="projectNameMon5" id="projectNameMon5" cssClass="form-control SmallTextBox_Time" onchange="return projectMon5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameTue5}" name="projectNameTue5" id="projectNameTue5" cssClass="form-control SmallTextBox_Time" onchange="return projectTue5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameWed5}" name="projectNameWed5" id="projectNameWed5" cssClass="form-control SmallTextBox_Time" onchange="return projectWed5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameThu5}" name="projectNameThu5" id="projectNameThu5" cssClass="form-control SmallTextBox_Time" onchange="return projectThu5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameFri5}" name="projectNameFri5" id="projectNameFri5" cssClass="form-control SmallTextBox_Time" onchange="return projectFri5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.projectNameSat5}" name="projectNameSat5" id="projectNameSat5" cssClass="form-control SmallTextBox_Time" onchange="return projectSat5();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="projectNameAll5" id="projectNameAll5" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr>

                                                                    <tr>
                                                                        <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq" >Internal</label></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalSun}" name="internalSun" id="internalSun" cssClass=" form-control SmallTextBox_Time" onchange="return internalSunday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalMon}" name="internalMon" id="internalMon" cssClass=" form-control SmallTextBox_Time" onchange="return internalMonday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalTue}" name="internalTue" id="internalTue" cssClass=" form-control SmallTextBox_Time" onchange="return internalTuesday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalWed}" name="internalWed" id="internalWed" cssClass=" form-control SmallTextBox_Time" onchange="return internalWednesday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalThu}" name="internalThu" id="internalThu" cssClass=" form-control SmallTextBox_Time" onchange="return internalThursday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalFri}" name="internalFri" id="internalFri" cssClass=" form-control SmallTextBox_Time" onchange="return internalFriday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.internalSat}" name="internalSat" id="internalSat" cssClass=" form-control SmallTextBox_Time" onchange="return internalSaturday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="internalAll" id="internalAll" cssClass=" form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr> 
                                                                    <tr style="display: none" id="miscellaneousVacation">
                                                                        <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq" >Vacation</label></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationSun}" name="vacationSun" id="vacationSun" cssClass="form-control SmallTextBox_Time" onchange="return vacationSunday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationMon}" name="vacationMon" id="vacationMon" cssClass="form-control SmallTextBox_Time" onchange="return vacationMonday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationTue}" name="vacationTue" id="vacationTue" cssClass="form-control SmallTextBox_Time" onchange="return vacationTuesday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationWed}" name="vacationWed" id="vacationWed" cssClass="form-control SmallTextBox_Time" onchange="return vacationWednesday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationThu}" name="vacationThu" id="vacationThu" cssClass="form-control SmallTextBox_Time" onchange="return vacationThursday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationFri}" name="vacationFri" id="vacationFri" cssClass="form-control SmallTextBox_Time" onchange="return vacationFriday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.vacationSat}" name="vacationSat" id="vacationSat" cssClass="form-control SmallTextBox_Time" onchange="return vacationSaturday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="vacationAll" id="vacationAll" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr> 
                                                                    <tr style="display: none" id="miscellaneousHoliday">
                                                                        <td><label class="labelStyle ReqinputStyleTime add-to" id="labelLevelStatusReq" >Holiday</label></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidaySun}" name="holidaySun" id="holidaySun" cssClass="form-control SmallTextBox_Time" onchange="return holidaySunday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidayMon}" name="holidayMon" id="holidayMon" cssClass="form-control SmallTextBox_Time" onchange="return holidayMonday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidayTue}" name="holidayTue" id="holidayTue" cssClass="form-control SmallTextBox_Time" onchange="return holidayTuesday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidayWed}" name="holidayWed" id="holidayWed" cssClass="form-control SmallTextBox_Time" onchange="return holidayWednesday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidayThu}" name="holidayThu" id="holidayThu" cssClass="form-control SmallTextBox_Time" onchange="return holidayThursday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidayFri}" name="holidayFri" id="holidayFri" cssClass="form-control SmallTextBox_Time" onchange="return holidayFriday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="%{timeSheetVTO.holidaySat}" name="holidaySat" id="holidaySat" cssClass="form-control SmallTextBox_Time" onchange="return holidaySaturday();" onkeypress="return acceptNumbers(event);" tabindex="1" maxLength="4"/></td>
                                                                        <td><s:textfield value="0.0" name="holidayAll" id="holidayAll" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr> 

                                                                    <tr>
                                                                        <td><label class="labelStyle-i ReqinputStyleTime add-to" id="labelLevelStatusReq" >Total</label></td>
                                                                        <td><s:textfield value="0.0" name="totalSun" id="totalSun" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalMon" id="totalMon" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalTue" id="totalTue" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalWed" id="totalWed" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalThu" id="totalThu" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalFri" id="totalFri" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalSat" id="totalSat" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                        <td><s:textfield value="0.0" name="totalAll" id="totalAll" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/></td>
                                                                    </tr> 
                                                                </table>

                                                            </div>  

                                                            <div class="row">    

                                                                <div class="col-sm-5 col-lg-4 col-md-5">
                                                                    <div class="row"> 
                                                                        <div class="col-sm-12 pull-left "> 
                                                                            <label class="labelStyle-i add-to contact_search" id="labelLevelStatusReq" style="float: left">Total Billable Hrs</label>
                                                                            <s:textfield name="totalBillHrs" value="0.0" id="totalBillHrs" style="float: left"  cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/>
                                                                        </div>


                                                                        <div class="col-sm-12 pull-left "> 
                                                                            <label class="labelStyle-i add-to contact_search" id="labelLevelStatusReq" style="float: left">Total Holiday Hrs</label>
                                                                            <s:textfield name="totalHolidayHrs" value="0.0" id="totalHolidayHrs" style="float: left" cssClass="form-control SmallTextBox_Time" readonly="true" tabindex="-1"/>
                                                                        </div>
                                                                        <div class="col-sm-12 pull-left "> 
                                                                            <label class="contact_search add-to contact_search" id="labelLevelStatusReq" style="float: left">Total Vacation Hrs</label>
                                                                            <s:textfield name="totalVacationHrs" value="0.0" id="totalVacationHrs" style="float: left" cssClass="form-control SmallTextBox_Time"  readonly="true" tabindex="-1"/>
                                                                        </div>

                                                                    </div>                                                         </div>



                                                                <div class="col-md-7 col-sm-7 col-lg-8">
                                                                    <div class="col-sm-12">
                                                                        <div class=" form-group">
                                                                            <label class="labelStyle" id="labelLevelStatusReq">Notes</label> <s:textarea name="timeSheetNotes" id="timeSheetNotes" cssClass="titleStyle" value="%{timeSheetVTO.timeSheetNotes}" placeholder="Enter Here" rows="3" onkeydown=" timeSheetsNotes(this)" onblur="removeErrorMessages()" tabindex="1"/>
                                                                        </div>
                                                                        <div class="charNum" id="notes"></div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12">
                                                                <div class="form-group  " >
                                                                    <s:if test="timesheetFlag=='Team'"> 
                                                                        <label class="labelStyle" id="labelLevelStatusReq">Comments</label> <s:textarea name="comments" id="comments" cssClass="titleStyle" value="%{timeSheetVTO.comments}" placeholder="Enter Here" rows="3" onkeyup=" timeSheetsComments(this)" onblur="removeErrorMessages()" tabindex="1"/>
                                                                    </s:if> 
                                                                    <s:elseif test="timesheetFlag=='My' && timeSheetVTO.timeSheetStatus=='Disapproved'">
                                                                        <label class="labelStyle" id="labelLevelStatusReq">Comments On Rejection</label> <s:textarea name="comments" id="comments" cssClass="titleStyle" value="%{timeSheetVTO.comments}" placeholder="Enter Here" rows="3" onkeyup=" ResponseCheckCharacters(this)" onfocus="removeErrorMessages()" readonly="true" tabindex="-1"/>
                                                                    </s:elseif>
                                                                    <div class="charNum" id="comments"></div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6 col-sm-6">
                                                                <s:hidden name="timeSheetStatus" id="timeSheetStatus" value="%{timeSheetVTO.timeSheetStatus}" cssClass="noBorder" readonly="true"/>
                                                            </div>
                                                        </div>


                                                        <div class="inner-addtaskdiv-elements">
                                                            <div class="col-sm-12">
                                                                <s:if test="timesheetFlag=='My'">
                                                                    <s:if test="timeSheetVTO.timeSheetStatus=='Entered'||timeSheetVTO.timeSheetStatus=='Disapproved'">
                                                                        <s:label><font color="red">*NOTE: After submitting this timesheet you can't edit.</font></s:label>
                                                                            <div class="col-lg-8"></div>
                                                                            <div class="col-sm-2 pull-right">
                                                                            <s:submit  type="button" cssStyle="margin:5px 0px;" tabindex="3" cssClass="add_searchButton fa fa-floppy-o form-control " id="enter" value="Save" theme="simple" onclick="setTemVar1()"/>

                                                                        </div>
                                                                        <div class="col-sm-2 pull-right">
                                                                            <s:submit type="button" cssStyle="margin:5px 0px;" tabindex="2" cssClass="add_searchButton fa fa-check-circle-o form-control " id="update" value="Submit" theme="simple" onclick="setTemVar2()" />
                                                                            <s:hidden name="tempVar" id="tempVar" value=""/>
                                                                        </div>

                                                                    </s:if>
                                                                </s:if>    

                                                                <s:if test="timesheetFlag=='Team'">
                                                                    <s:if test="timeSheetVTO.timeSheetStatus=='Submitted'">
                                                                        <div class="col-lg-8"></div>
                                                                        <div class="col-lg-2 pull-right">
                                                                            <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control fa fa-check-circle-o" style="margin-left:3px;" id="approve" value="Approve" theme="simple" onclick='document.timeSheetsForm.action="newapprove.action?tempVar=1"' />
                                                                        </div>
                                                                        <div class="col-sm-2 pull-right">
                                                                            <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control fa fa-times" id="reject" value="Reject" theme="simple" onclick='document.timeSheetsForm.action="newapprove.action?tempVar=2"'/>
                                                                        </div>
                                                                    </s:if>
                                                                </s:if>
                                                            </div>  
                                                        </div> 
                                                        <br/>
                                                        <br/>
                                                        <!--s:submit cssClass=" col-sm-offset-10 btn cssbutton" id="update" onclick="return updaterequirements();"  theme="simple"/-->

                                                    </s:form>       
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
<script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>

<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
</body>
</html>
