<%--
    Document   : dashboard
    Created on : Feb 3, 2015, 7:50:23 PM
    Author     : Nagireddy
--%>



<%@page import="com.mss.msp.usr.task.TasksVTO"%>

<%@page import="java.util.Iterator"%>
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
        <title>ServicesBay :: Add Task Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

        <%-- aklakh js single file start --%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <%-- aklakh js single file end --%>
        <%-- aklakh css single file start --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <%-- aklakh css single file end --%>
        <%-- for date picket start--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>--%>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>--%>

        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <%-- for date picket end--%>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/TaskAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>

        <sx:head />

        <script type="text/javascript">

            var myCalendar,myCalendar1;
            //,"docdatepickerfrom1","docdatepicker1"
            function doOnLoad() {
                // alert("hii");docdatepickerfrom","docdatepicker


                myCalendar1 = new dhtmlXCalendarObject(["startDate","endDate"]);
                // alert("hii1");
                myCalendar1.setSkin('omega');
                // alert("hii2");
                // myCalendar1.setDateFormat("%Y/%m/%d");
                myCalendar1.setDateFormat("%m-%d-%Y");
                // default date code start from here




                var today = new Date();
                //alert(today);
                var dd = today.getDate();
                var mm = today.getMonth(); //January is 0!
                var yyyy = today.getFullYear();
                //alert(fromDate)
                if(dd<10) {
                    dd='0'+dd
                }
                if(mm<10) {
                    mm='0'+mm
                }

                var from = mm+'-'+'01'+'-'+yyyy;

                mm=today.getMonth()+1;
                if(mm<10) {
                    mm='0'+mm
                }
                // dd=today.getDate()+1;
                var to = mm+'/'+dd+'/'+yyyy;
                var odd=today.getDate();
                if(odd<10) {
                    odd='0'+odd;
                }
                var overlayDate=mm+'-'+odd+'-'+yyyy;
                //alert(from+" and "+to)
                document.getElementById("startDate").value=overlayDate;
                document.getElementById("endDate").value=overlayDate;
                
                
                
                $('#alertCheck').change(function(){
                    if($(this).is(":checked"))
                        $('#alertContent').show();
                    else
                        $('#alertContent').hide();

                });
            }



            function enterDateRepository()
            {
                // alert(document.documentForm.docdatepickerfrom.value);
                // document.documentForm.docdatepickerfrom.value="";
                document.getElementById('docdatepickerfrom').value = "";
                document.getElementById('docdatepicker').value = "";
                alert("Please select from the Calender !");
                return false;
            }






            function checkRange() {
                var fromValue=$('#docdatepickerfrom').val();
                var toValue=$('#docdatepicker').val();
                //alert(fromValue+" and "+toValue)
                if(fromValue==""){
                    alert("from date is madatory")
                    return false;
                }
                if(toValue==""){
                    alert("to date is madatory")
                    return false;
                }

                /*  var res = fromValue.split(" ");
                fromValue=res[0];
                var res1 = toValue.split(" ");
                toValue=res1[0];
                //alert(fromValue+" and "+toValue)
                if (Date.parse(fromValue) > Date.parse(toValue)) {
                    alert("Invalid Date Range!\nFrrom Date cannot be after To Date!")
                    return false;
                }*/
                var addStartDate = Date.parse(fromValue);
                var addEndDate = Date.parse(toValue);

                var difference = (addEndDate - addStartDate) / (86400000 * 7);
                if (difference < 0) {
                    alert("The start date must come before the end date.");
                    $("errorEduAdd").html(" <b><font color='red'>start date must be less than end date</font></b>.");
                    $("#fromValue").css("border", "1px solid red");

                    $("#toValue").css("border","1px solid red");
                    return false;
                }
            };


        </script>


    </head>





    <body style="overflow-x: hidden" onload="doOnLoad(); init(); getTaskType();">
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
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Add Task</font>
                                                <span class="pull-right"><a href="doTasksSearch.action"><img  src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></a></span> 

                                            </h4>
                                        </div>

                                    </div>
                                    <%--<form action="../tasks/addNewTask" theme="simple" > list="tasksRelatedToList" list="tasksStatusList"--%>
                                    <s:form action="addNewTask"  theme="simple" onsubmit="return addTaskValidation()"  enctype="multipart/form-data" >
                                        <span id="editTaskValidation"><editTask></editTask></span>
                                        <div class="inner-reqdiv-elements">
                                            <div class=" ">
                                                <div class="col-sm-4 required">
                                                    <label class="labelStylereq" style="color: #56a5ec;">StartDate: </label>
                                                    <s:textfield cssClass="form-control dateImage" name="startDate" id="startDate" placeholder="StartDate" value="%{startDate}" cssStyle="z-index: 10000004;" onkeypress="return enterTaskDateRepository(this)"/>
                                                </div>
                                                <div class="col-sm-4 required">
                                                    <label class="labelStylereq" style="color: #56a5ec;">EndDate: </label>
                                                    <s:textfield cssClass="form-control dateImage"  name="endDate" value="%{endDate}" id="endDate" placeholder="EndDate"  onkeypress="return enterTaskDateRepository(this)"/>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color: #56a5ec;">Status: </label>
                                                    <s:select  id="task_status" value="1"  name="taskStatus" cssClass="SelectBoxStyles form-control" headerKey="1" theme="simple" list="tasksStatusList" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="inner-reqdiv-elements">
                                            <div class=" ">
                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color: #56a5ec;">Priority: </label>
                                                    <s:select  id="task_priority"  name="taskPriority" cssClass="SelectBoxStyles form-control" headerKey="M" headerValue="Medium" theme="simple"  list="#@java.util.LinkedHashMap@{'L':'Low','H':'High'}" value="{'M':'Medium'}" />
                                                </div>

                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color: #56a5ec;">Related To: </label>
                                                    <s:select  id="taskRelatedTo" value="2" name="taskRelatedTo" cssClass="SelectBoxStyles form-control"  theme="simple" list="tasksRelatedToList" onchange="getTaskType();" />
                                                </div>
                                                <div class="col-sm-4" id="csrDiv">
                                                    <label class="labelStylereq" style="color: #56a5ec;">Projects: </label>
                                                    <s:select  id="taskType" name="taskType" cssClass="SelectBoxStyles form-control"  theme="simple" list="{}" onchange="getRelatedNames();" />
                                                </div>

                                            </div>
                                        </div>
                                        <div>
                                            
                                            <%--div class="inner-addtaskdiv-elements">
                                                <label class="labelStyle">RelatedTo</label>:<s:select  id="taskRelatedTo" value="2"   name="taskRelatedTo" cssClass="selectstyle" headerKey="" headerValue="Related To" theme="simple" list="tasksRelatedToList" onchange="getTaskType();" />
                                                <label class="labelStyle">Type</label>:<s:select  id="taskType"   name="taskType" cssClass="selectstyle" headerKey="-1" headerValue="Select Type" theme="simple" list="{'Bug','Issue','Enhancement','Defect'}" />
                                            </div--%>
                                            <%-- <div class="inner-addtaskdiv-elements" id="projects" style="display: none;">
                                                 <label class="labelStyle">Main Projects</label>:<s:select   id="mainProjects"   name="mainProject"  cssClass="selectstyle" headerKey="" headerValue="Main Projects" theme="simple" list="userMainProjects"  />
                                                 <label class="labelStyle">Sub Projects</label>:<s:select     id="subProjects"   name="subProject"   cssClass="selectstyle" headerKey="" headerValue="Sub Projects"  theme="simple" list="userSubProjects"/>
                                             </div>
                                            --%>
                                            <%--div class="inner-addtaskdiv-elements">
                                                <label class="labelStyle">Status</label>:<s:select  id="task_status" value="1"  name="taskStatus" cssClass="selectstyle" headerKey="1" theme="simple" list="tasksStatusList" />
                                                <label class="labelStyle">Priority</label>:<s:select  id="task_priority"  name="taskPriority" cssClass="selectstyle" headerKey="M" headerValue="Medium" theme="simple"  list="#@java.util.LinkedHashMap@{'L':'Low','H':'High'}" value="{'M':'Medium'}" />
                                            </div>
                                            <div class="inner-addtaskdiv-elements">
                                                <label class="labelStyle">StartDate</label>:<s:textfield cssClass="inputStyle dateImage" name="startDate" id="startDate" placeholder="StartDate" value="%{startDate}" cssStyle="z-index: 10000004;"/>
                                                <label class="labelStyle">EndDate</label>:<s:textfield cssClass="inputStyle dateImage"  name="endDate" value="%{endDate}" id="endDate" placeholder="EndDate" />

                                            </div--%>
                                            <div class="inner-reqdiv-elements-elements required">
                                                <div class="col-sm-12">
                                                <label class="labelStylereq">Title</label>:<s:textfield name="taskName" id="task_name" placeholder="Enter Title Here" cssClass="titleStyle"  maxLength="60"/>
                                                </div>
                                                </div>
                                            <div class="inner-reqdiv-elements">
                                                <div class="col-sm-12">
                                                <label class="labelStylereq">Description</label>:<s:textarea name="task_comments" id="task_comments" placeholder="Enter Task Description Here" cssClass="areacss" onkeyup="checkTaskDescription(this)"/>
                                                </div>
                                                </div>
                                            <div class="charNum" id="description_feedback"></div>
                                            <div class="inner-reqdiv-elements">
                                                <div class="col-sm-13">
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color: #56a5ec;">Primary Assign: </label>
                                                        <s:select  id="primary_assign"  name="primaryAssign" cssClass="SelectBoxStyles form-control" headerKey="-1" theme="simple" list="{}" />
                                                        <s:hidden name="secondaryId" id="secondaryId"/>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color: #56a5ec;">Secondary Assign:</label>
                                                        <s:textfield  id="secondaryReport"  name="secondaryAssign" placeholder="SecondaryAssignTo" cssClass="form-control"  theme="simple" onkeyup="getSecondaryAssignedNames();" autocomplete='off' maxLength="30"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <%--div class="inner-addtaskdiv-elements">
                                                <label class="labelStyle">Pri.Assign</label>:<s:select  id="primary_assign"  name="primaryAssign" cssClass="selectstyle" headerKey="-1" theme="simple" list="{'Low','High'}" />
                                                <s:hidden name="secondaryId" id="secondaryId"/>
                                                <label class="labelStyle">Sec.Assign</label>:<s:textfield  id="secondaryReport"  name="secondaryAssign" placeholder="SecondaryAssignTo" cssClass="inputStyle"  theme="simple" onkeyup="getSecondaryAssignedNames();" />
                                            </div--%>
                                            <div class="inner-addtaskdiv-elements"><span id="validationMessage" /></div>
                                            <div class="inner-reqdiv-elements">
                                                <div class="col-sm-12">
                                                <label class="labelStyle">Attachments</label>:<s:file  name="taskAttachment" id="taskAttachment" cssClass=""  />
                                                </div>
                                                </div>
                                            <div class="col-sm-12">
                                                <s:checkbox name="alert_check" id="alertCheck"  />&nbsp;&nbsp;<s:label>Do you need alert?</s:label>
                                                </div>
                                            <%--div id="alertContent" class="inner-addtaskdiv-elements">
                                                <label class="labelStyle">Alert By</label>:<s:select  id="alertTime"  name="alertTime" cssClass="selectstyle" headerKey="-1" headerValue="Alert By" theme="simple" list="{'EveryMonth','Before One Week','Before One Day'}" /><br>
                                            <label class="labelStyle">Alert Message</label>:<s:textarea name="alertMessage" placeholder="Enter Alert Message Here"  cssClass="areacss"  />
                                        </div--%>
                                            <div class="inner-reqdiv-elements">
                                                <div id="alertContent">
                                                    <div class="col-sm-13">
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color: #56a5ec;">Alert By: </label>
                                                            <s:select  id="alertTime"  name="alertTime" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="Alert By" theme="simple" list="{'EveryMonth','Before One Week','Before One Day'}" /><br>
                                                        </div>
                                                   
                                                    <div  class="col-sm-8 required">
                                                        <label class="labelStylereq">Alert Message</label>:<s:textarea name="alertMessage" placeholder="Enter Alert Message Here"  cssClass="areacss"  />
                                                    </div>
                                                     </div>
                                                </div>
                                            </div>
                                                    <div class="row"></div>
                                           <div class="row">
                                                <div class="col-lg-8"></div>
                                               <div class="col-sm-2 pull-right">
                                                        <s:submit type="button"  cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" theme="simple" ><i class="fa fa-plus-square"></i>&nbsp;AddTask</s:submit>
                                                        </div>
                                                <div class="col-sm-2 pull-right">
                                                   
                                                        <s:reset type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control fa fa-eraser " value="Clear" theme="simple" />
                                                        <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                                    </div>
                                                    
                                                   

                                                </div>
                                        </s:form>
                                        <%--close of future_items--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- content end -->


        </section><!--/form-->

        <div style="height: 27px"></div>

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>

    </body>
</html>

