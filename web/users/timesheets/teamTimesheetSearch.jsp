<%-- 
    Document   : teamTimesheetSearch
    Created on : May 28, 2015, 7:19:40 PM
    Author     : miracle
--%>
<%@ page import="com.mss.msp.usr.timesheets.TimesheetVTO"%>
<%@ page import="com.mss.msp.usr.timesheets.UsrTimeSheetAction"%>
<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
        <title>ServicesBay :: Team&nbsp;Time&nbsp;sheets&nbsp;Search</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/timesheet.js"/>"></script>

        <sx:head />

        <script type="text/javascript">
            var myCalendar,myCalendar1,mycalenderweek;
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["docdatepickerfrom","docdatepicker"]);
                myCalendar.setSkin('omega');
                myCalendar.setDateFormat("%m-%d-%Y");
                myCalendar.hideTime();
                mycalenderweek = new dhtmlXCalendarObject(["weekrange"]);
                mycalenderweek.setSkin('omega');
                mycalenderweek.setDateFormat("%m-%d-%Y");
                
                myCalendar1 = new dhtmlXCalendarObject(["startDate","endDate"]);
                myCalendar1.setSkin('omega');
                
                myCalendar1.setDateFormat("%Y/%m/%d");
                myCalendar1.hideTime();
                
                // default date code start from here
                
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth(); //January is 0!
                var yyyy = today.getFullYear();
                if(dd<10) {
                    dd='0'+dd
                } 
                if(mm<10) {
                    mm='0'+mm
                } 
                
                var from = yyyy+'/'+mm+'/01';
                var from = mm+'/01'+'/'+yyyy;
                mm=today.getMonth()+1;
                if(mm<10) {
                    mm='0'+mm
                } 
                dd=today.getDate()+1;
                if(dd<10) {
                    dd='0'+dd
                } 
                var to = yyyy+'/'+mm+'/'+dd;
                var to = mm+'/'+dd+'/'+ yyyy;
                var odd=today.getDate()+1;
                var overlayDate=yyyy+'/'+mm+'/'+odd;
                document.getElementById("loadingTeamTimesheetSearch").style.display="none";
            }
            
            
            
            function enterDateRepository()
            {
                return false;
            }
            
           
            
            
          
            function checkRange() {
                var fromValue=$('#docdatepickerfrom').val();
                var toValue=$('#docdatepicker').val();
                if(fromValue==""){
                    alert("from date is madatory");
                    return false;
                }
                if(toValue==""){
                    alert("to date is madatory");
                    return false;
                }
                
                var addStartDate = Date.parse(fromValue);
                var addEndDate = Date.parse(toValue);

                var difference = (addEndDate - addStartDate) / (86400000 * 7);
                if (difference < 0) {
                    alert("The start date must come before the end date.");
                    $("errorEduAdd").html(" <font color='red'>start date must be less than end date</font>.");
                    $("#fromValue").css("border", "1px solid red");
      
                    $("#toValue").css("border","1px solid red");
                    return false;
                }
            }; 
            
            
            $(document).ready(function(){
        
                $('#alert-content').hide();
                $('#alert-check').change(function(){
                                    
                                
                    if($(this).is(":checked"))
                        $('#alert-content').show();
                    else
                        $('#alert-content').hide();

                });
            });
            
            
        </script>

        <script>
            var pager;   //this pagination for team time sheet search
            $(document).ready(function(){

                var paginationSize = 10; 
                pager = new Pager('timesheet_results', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'timesheetpageNavPosition');
                pager.showPage(1);
            });
         
        </script>


    </head>





    <body style="overflow-x: hidden" oncontextmenu="return false" onload="doOnLoad();checkWeekStatus()">
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
                                    <div class="col-lg-14 ">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">

                                                        <font color="#ffffff">Team&nbsp;Time&nbsp;Sheets&nbsp;Search</font>
                                                        <i id="updownArrow" onclick="toggleContent('teamTimesheetSearch')" class="fa fa-minus"></i>

                                                    </h4>
                                                </div>

                                            </div>

                                            <s:url var="myUrl" action="#"></s:url>
                                                <span id="timesheetValidation"></span>
                                                <div class="col-sm-12">
                                                <s:form action="teamTimesheetSearch" onsubmit="return checkTimesheetDateRange();" theme="simple" >
                                                    <br>
                                                    <ul class="nav nav-pills">
                                                        <s:hidden value="%{reportingPerson}" name="reportingPerson" id="reportingPerson"/>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec;">From&nbsp;Date</label> 
                                                            <div class="calImage"><s:textfield cssClass="form-control " label="from date" name="startDate" id="docdatepickerfrom" placeholder="FromDate" value="%{startDate}" tabindex="1"  onkeypress="return enterDateRepository();"><i class="fa fa-calendar"></i></s:textfield>
                                                            </div></div>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec;">To&nbsp;Date</label>
                                                            <div class="calImage"><s:textfield cssClass="form-control " label="To date" name="endDate" placeholder="ToDate" value="%{endDate}" id="docdatepicker" tabindex="2"  onkeypress="return enterDateRepository();"><i class="fa fa-calendar"></i></s:textfield>
                                                        </div></div>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec;">Status</label>
                                                            <s:select id="tmstatus" name="tmstatus" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="Select Status" theme="simple" tabindex="3" list="#@java.util.LinkedHashMap@{'CA':'Entered','SU':'Submitted','AP':'Approved'}"/>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec;">Employee</label>
                                                            <s:select id="tmstatus" name="tmmember" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="Select Employee" tabindex="4" theme="simple" list="teamMembersList"/>
                                                        </div>
                                                        <div class="col-sm-8 pull-right">
                                                            <div class=" pull-right">

                                                                <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" id="searchButton" value="" tabindex="5" ><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                </div>
                                                            </div>
                                                        </ul>
                                                        <br>
                                                </s:form>
                                                        <span id="msg_clr">
                                                        <%
                                                    if (request.getParameter("resultMessage") != null) {
                                                        out.println(request.getParameter("resultMessage"));
                                                    }
                                                %>  
                                                        </span>
                                                <div id="loadingTeamTimesheetSearch" class="loadingImg">
                                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                                </div>
                                                <s:form>
                                                    <div class="task_content" id="task_div" align="center" style="display: none" >

                                                        <div>
                                                            <div>

                                                                <table id="timesheet_results" class="responsive CSSTable_task" border="5" cell-spacing="2">

                                                                    <tbody>
                                                                        <tr>
                                                                            <th>Appd/Rej</th>
                                                                            <th>Emp&nbsp;Name</th>
                                                                            <th>Start&nbsp;Date</th>
                                                                            <th>End&nbsp;Date</th>
                                                                            <th>Billable&nbsp;Hours</th>
                                                                            <th>Status</th>
                                                                            <th>Submitted Date</th>

                                                                        </tr>
                                                                        <%

                                                                            int c = 0;
                                                                            if ((List) session.getAttribute("teamTimesheetsData") != null) {

                                                                                List l = (List) session.getAttribute("teamTimesheetsData");
                                                                                Iterator it = l.iterator();
                                                                                while (it.hasNext()) {
                                                                                    if (c == 0) {
                                                                                        c = 1;
                                                                                    }
                                                                                    TimesheetVTO usa = (TimesheetVTO) it.next();
                                                                                    int tms_id = usa.getTimesheetid();
                                                                                    int usr_id = usa.getUsr_id();
                                                                                    String tm_empname = usa.getEmpName();
                                                                                    String tm_start_date = usa.getDateStart();
                                                                                    String tm_end_date = usa.getDateEnd();
                                                                                    String cur_status = usa.getReportsto1status();
                                                                                    String reports_to = usa.getReportsto1();
                                                                                    String tm_submitted_date = usa.getSubmittedDate();
                                                                                    String tm_approved_date = usa.getApprovedDate();
                                                                                    double billableHr = usa.getTotalBillHrs();
                                                                        %>
                                                                        <tr>
                                                                            <td>

                                                                                <s:url var="myUrl" action="../timesheets/getTimeSheets.action?timesheetFlag=Team">
                                                                                    <s:param name="usr_id"><%=usr_id%></s:param>
                                                                                    <s:param name="timesheetid"><%=tms_id%></s:param></s:url>

                                                                                <s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/edit_Msb.png"/>" height="20" width="20"></s:a></td>
                                                                            <td><%= tm_empname%></td>
                                                                            <td><%= tm_start_date%></td>
                                                                            <td><%= tm_end_date%></td>
                                                                            <td><%= billableHr%></td>
                                                                            <td><%= cur_status%></td>
                                                                            <td><%= tm_submitted_date%></td>

                                                                        </tr> 

                                                                        <%
                                                                                }

                                                                            }
                                                                            if (c == 0) {
                                                                        %>
                                                                        <tr>
                                                                            <td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                        </tr> 
                                                                        <%    }
                                                                        %>

                                                                    </tbody>
                                                                </table>
                                                                <br/>
                                                                <%



                                                                    if (c > 0) {
                                                                %>
                                                                <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                        <option>10</option>
                                                                        <option>15</option>
                                                                        <option>25</option>
                                                                        <option>50</option>
                                                                    </select>
                                                                    Time Sheets per page
                                                                </label>
                                                                <%    }
                                                                %>

                                                                <%
                                                                    if (c == 1) {
                                                                %>
                                                                <%                                                            }

                                                                 
                                                                %>


                                                                <div align="right" id="timesheetpageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                                <script type="text/javascript">
                                                                    var pager = new Pager('timesheet_results', 10); 
                                                                    pager.init(); 
                                                                    pager.showPageNav('pager', 'timesheetpageNavPosition'); 
                                                                    pager.showPage(1);
                                                                </script>

                                                            </div>
                                                        </div>
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
                    <!-- content end -->


                </section><!--/form-->
            </div>
        </div>
        <div id="addTimeSheet">
            <div id="addTimeSheetOverlay" >
                <div style="background-color: #3bb9ff ; padding: 0px">
                    <table>
                        <tr><td><h4 style=""><font color="#ffffff">&nbsp;&nbsp;Add&nbsp;Time&nbsp;Sheet&nbsp;&nbsp; </font></h4></td>
                        </tr>
                        <span class=" pull-right"><h5><a href="" id="tsAddOverlayCloseButton" class="addTimeSheet_close" onclick="addTimeSheetOverlayClose()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                    </table>
                </div>
                <div>
                    <form action="#" theme="simple" >
                        <div>
                            <span><addTimesheerResult></addTimesheerResult></span>


                            <div class="inner-addSkillDiv-elements textfieldLabel">

                                <span><error></error></span>
                                <s:radio id="radiobutton" name="myRadioButton" 
                                         list="#@java.util.LinkedHashMap@{'1' : 'submit timesheet for this week'}" onclick="return checkWeekStatus()" value="1"/>
                                <br> <s:radio id="radiobutton" name="myRadioButton" 
                                         list="#@java.util.LinkedHashMap@{'2':'previous week starting on'}" onclick="return checkWeekStatus()"/>
                                <br>
                                <s:textfield cssClass=" form-control dateImage"   name="weekrange" id="weekrange" />

                                <br> 

                               
                                <a href="../timesheets/addTimeSheet.action" id="tsGoButton" ><input type="button" class="cssbutton " value="Go"></a>&nbsp; 
                            </div>
                        </div>
                    </form>
                </div> 
            </div>
        </div>

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                $('#timesheet_results').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#timesheet_results').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script>
        $("#msg_clr").show().delay(5000).fadeOut();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>

    </body>
</html>

