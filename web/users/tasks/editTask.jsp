<%-- 
    Document   : newjspeditTask
    Created on : Mar 17, 2015, 11:28:53 PM
    Author     : Praveen<pkatru@miraclesoft.com>Ramakrishna<lankireddy@miraclesoft.com>
--%>
<%@page import="com.mss.msp.usr.task.TasksVTO"%>
<%@page import="com.mss.msp.usr.task.TaskHandlerAction"%>

<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" %>
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
        <title>ServicesBay :: Edit Tasks Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/dhtmlxcalendar.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">


        <script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>        
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/ajaxfileupload.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/TaskAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/dashboard.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $('#autoUpdate').hide();
                $('#checkbox1').change(function(){
                                    
                                
                    if($(this).is(":checked"))
                        $('#autoUpdate').fadeIn('slow');
                    else
                        $('#autoUpdate').fadeOut('slow');

                });
            });
        </script>
        <script type="text/javascript">
             
            var tpager=new  Pager('taskpagenav',3);
            tpager.init(); 
            tpager.showPageNav('tpager', 'task_pageNavPosition'); 
            tpager.showPage(1);

          
        </script>     

        <script>
            var pager;   //this pagination for tasks search
            $(document).ready(function(){
                // alert("hi")
//                var paginationSize = 10; //parseInt(document.getElementById("paginationOption").value);
//                pager = new Pager('taskpagenav', paginationSize);
//               // document.getElementById("paginationOption").value=10;
//                pager.init();
//                pager.showPageNav('pager', 'task_pageNavPosition');
//                // document.getElementById("paginationOption").value=10;
//                pager.showPage(1);
            });
//            function pagerOption(){
//
//                paginationSize = document.getElementById("paginationOption").value;
//                if(isNaN(paginationSize))
//                    alert(paginationSize);
//
//                pager = new Pager('taskpagenav', parseInt(paginationSize));
//                pager.init();
//                pager.showPageNav('pager', 'task_pageNavPosition');
//                pager.showPage(1);
//
//            };
        </script>

        <sx:head />
    </head>
    <body onload="doOnLoad(); init(); getTaskType(); " style="overflow-x: hidden">
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
                    <div class="col-sm-9 padding-right">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="panel panel-info">
                                        <div class="panel-heading" id="taskedit-heading">
                                            <font class="titleColor">Edit Task  </font>
                                            <span class="pull-right"><a href="#"><img onclick="history.back();return false;" src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></a></span>
                                        </div>
                                        <div class="panel-body" id="panel-task-body" >
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="" id="homeLi"><a aria-expanded="false" href="#home" data-toggle="tab">Task</a>
                                                </li>
                                                <li class="" id="profileLi"><a aria-expanded="false" href="#profile" data-toggle="tab" >Attachments</a>
                                                </li>
                                                <li class="" id="messagesLi"><a aria-expanded="false" href="#messages" data-toggle="tab" onclick="doGetNotesDetails()">Notes</a>
                                                </li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="home">
                                                    <span ><UpdateTaskInfo></UpdateTaskInfo></span>
                                                    <form  theme="simple">
                                                        <div><br>
                                                            <span id="editTaskValidate"><editTask></editTask></span>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="row">
                                                                    <div class="col-lg-4 required">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">StartDate: </label>
                                                                        <s:textfield cssClass="form-control dateImage" name="startDate" id="start_date"  value="%{tasksVto.start_date}" tabindex="1"  onkeypress="return enterTaskDateRepository(this)"/>
                                                                        <s:hidden name="taskid" value="%{taskid}" id="taskid"/>
                                                                    </div>
                                                                    <div class="col-lg-4 required">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">EndDate: </label>
                                                                        <s:textfield cssClass="form-control dateImage" name="endDate" id="end_date"  value="%{tasksVto.end_date}" tabindex="1"  onkeypress="return enterTaskDateRepository(this)"/>
                                                                    </div>
                                                                    <div class="col-lg-4 ">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Status: </label>
                                                                        <s:select  id="task_status"  name="taskStatus" cssClass="SelectBoxStyles form-control" headerKey="-1" theme="simple" list="tasksStatusList" value="%{tasksVto.task_status}" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="row">
                                                                    <div class="col-lg-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Priority: </label>
                                                                        <s:select  id="taskPriority"  name="priority" cssClass="SelectBoxStyles form-control" headerKey="M" headerValue="Medium" theme="simple"  list="#@java.util.LinkedHashMap@{'L':'Low','H':'High'}" value="%{tasksVto.task_priority}"  />
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Related To: </label>
                                                                        <s:select  id="taskRelatedTo" name="taskRelatedTo" cssClass="SelectBoxStyles form-control" headerKey="-1"  theme="simple" list="tasksRelatedToList"  onchange="getTaskType();" value="%{tasksVto.task_related_to}"  />
                                                                        <s:hidden name="taskid" value="%{taskid}" id="taskid"/>
                                                                    </div>
                                                                    <div class="col-lg-4" id="csrDiv">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Projects: </label>
                                                                        <s:select  id="taskType"   name="taskType" cssClass="SelectBoxStyles form-control" headerKey="-1"  theme="simple" list="%{tasksVto.typeMaps}" value="%{tasksVto.task_prj_related_to}" onchange="getRelatedNames();" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements required">
                                                                <label class="labelStylereq" style="color: #56a5ec;">Title:</label>
                                                                <s:textfield name="taskName" id="task-textform" placeholder="Enter Title Here" cssClass="form-control" value="%{tasksVto.task_title}" maxLength="60" />
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <label class="labelStylereq" style="color: #56a5ec;">Description:</label>
                                                                <s:textarea name="task_comments" id="task_comments" placeholder="Enter Task Description Here" cssClass="form-control" value="%{tasksVto.task_comments}" onkeypress="checkEditTaskDescription(this)"/>
                                                            </div>
                                                              <div class="charNum" id="description_feedback"></div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="row">
                                                                    <div class="col-lg-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Primary AssignTo: </label>
                                                                        <s:select  id="primary_assign"  name="primaryAssign" cssClass="SelectBoxStyles form-control" headerKey="-1" theme="simple" list="teamMemberNames" value="%{tasksVto.pri_assigned_to}" />
                                                                        <s:hidden name="secondaryId" id="secondaryId" value="%{tasksVto.sec_reportsId}" />
                                                                        <s:hidden name="primaryId" id="primaryId" value="%{tasksVto.pri_assigned_to}" />
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Secondary AssignTo:</label>
                                                                        <s:textfield  id="secondaryReport"  name="sec_assign_to" placeholder="SecondaryAssignTo" cssClass="form-control"  theme="simple" onkeyup="return getSecondaryAssignedNames();" value="%{tasksVto.sec_assigned_to}" autocomplete='off' maxLength="30"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="inner-addtaskdiv-elements "><span id="validationMessage" /></div>   
                                                            <div class="col-lg-11">
                                                                <div class="panel panel-warning">
                                                                    <div class="panel-heading"> 
                                                                        <div class="form-group">
                                                                            <s:checkbox name="textcheck" style="color:#0066FF;" id="checkbox1" label="Do you need alert" onchange="toggleDisabled(this.checked)" />
                                                                        </div>
                                                                    </div>
                                                                    <div class="panel-body" id="autoUpdate">
                                                                        <%--div class="form-group">
                                                                            <label style="color:#56a5ec;" class="task-label">Alert me:</label> 
                                                                            <s:select  cssClass="SelectBoxStyles" name="alertme" theme="simple" list="#@java.util.LinkedHashMap@{'EM':'every month','BW':'before week','BD':'before 1 day'}" /><br>
                                                                            <br>
                                                                            <span class="required">
                                                                            <label style="color:#56a5ec;" class="task-label">Alert message:</label>
                                                                            <s:textarea name="alerttextarea"  />
                                                                            </span>
                                                                        </div--%> 
                                                                        <div class="form-group ">
                                                                            <div class="row">
                                                                                <div class="col-lg-4">
                                                                                    <label class="labelStylereq" style="color: #56a5ec;">Alert me:</label>
                                                                                    <s:select  cssClass="SelectBoxStyles form-control" name="alertme" theme="simple" list="#@java.util.LinkedHashMap@{'EM':'every month','BW':'before week','BD':'before 1 day'}" /><br>
                                                                                </div>

                                                                                <div class="col-lg-8">
                                                                                    <span class="required">
                                                                                        <label class="labelStylereq" style="color: #56a5ec;">Alert message:</label>
                                                                                        <s:textarea name="alerttextarea" cssClass="form-control" />
                                                                                    </span>
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                                                    <div class="col-lg-10"></div>
                                                            <div  class="col-lg-2">
                                                                <s:submit type="button"  cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" theme="simple"  onclick="return doUpdateTaskInfo();"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div id="taskAttachments_popup">
                                                    <div id="taskAttachOverlay">
                                                        <div style="background-color: #3BB9FF ">
                                                            <table>
                                                                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Task Attachments Details&nbsp;&nbsp; </font></h4></td>
                                                                <span class=" pull-right"><h5><a href="" class="taskAttachments_popup_close"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                                            </table>
                                                        </div>
                                                        <div>
                                                            <br>
                                                            <s:form action="addAttachment"  theme="simple"   enctype="multipart/form-data" >
                                                                <div>
                                                                    <div class="inner-addtaskdiv-elements">
                                                                        <s:hidden id="downloadFlag" name="downloadFlag" value="%{downloadFlag}"/>
                                                                        <s:hidden id="taskid" name="taskid" value="%{taskid}"/>
                                                                        <s:file name="taskAttachment" id="taskAttachment"/>
                                                                    </div>
                                                                    <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                                                    <center><s:submit cssClass="cssbutton" value="ADD" theme="simple"  /></center><br>
                                                                </div>

                                                            </div>
                                                        </s:form>
                                                    </div>
                                                </div>

                                                <div class="tab-pane fade" id="profile" onload="getTaskAttachments.action">


                                                    <section id="generalForm"><!--form-->

                                                        <div class="container">
                                                            <div class="row">

                                                                <div class="col-sm-9 padding-right">
                                                                    <div class="features_items">

                                                                        <h2 align="left">Task Attachments</h2><hr>
                                                                        <s:if test='downloadFlag=="noAttachment"'>
                                                                            <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                                                            </s:if>
                                                                            <s:if test='downloadFlag=="noFile"'>
                                                                            <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                                                            </s:if> 
                                                                        <div class="logcontainer paginated" id="log_grid_1" >
                                                                            <div class="logrow grid cs-style-7 log_grid_1_record" id="mainAttachmentDiv">
                                                                                <%
                                                                                    int c = 0;
                                                                                    // out.println("befor id");
                                                                                    if ((List) request.getAttribute("taskAttachments") != null) {

                                                                                        List l = (List) request.getAttribute("taskAttachments");

                                                                                        Iterator it = l.iterator();
                                                                                        while (it.hasNext()) {
                                                                                            if (c == 0) {
                                                                                                c = 1;
                                                                                            }
                                                                                            TasksVTO tasksVto = (TasksVTO) it.next();
                                                                                            String id = tasksVto.getAttachmentId();
                                                                                            String file_name = tasksVto.getAttachmentName();
                                                                                            String file_path = tasksVto.getAttachmentPath();

                                                                                %>

                                                                                <div class="logcol" >
                                                                                    <br><br><br><%= file_name%><br>
                                                                                    <figcaption>
                                                                                        <a href="javascript:doDeactiveAttachment('<%= id%>')">
                                                                                            <img src="../../includes/images/close_trans.png" height=25 width=25>
                                                                                        </a>
                                                                                        <br><br><%= file_name%><br><br>
                                                                                        <button type="button" id="<%= id%>" value="<%= file_path%>" onclick=doDownload("<%= id%>");>
                                                                                            <img src="../../includes/images/download.png" height=65 width=65 >
                                                                                        </button>
                                                                                    </figcaption>
                                                                                </div>

                                                                                <%
                                                                                        }

                                                                                    }
                                                                                    if (c == 0) {
                                                                                %>
                                                                                <div class="logcol logclr" >
                                                                                    <br><font style="color: red;font-size: 15px;">No Records to display</font>
                                                                                    <br><br>&nbsp;&nbsp;<a href="" class="taskAttachments_popup_open" ><button type="button" ><img src="<s:url value="/includes/images/add3.png"/>" height="65" width="65" ></button></a>
                                                                                </div>
                                                                                <%                                                                                    } else {
                                                                                %>


                                                                                <div class="logcol logclr" >
                                                                                    <br><br>&nbsp;&nbsp;<a href="" class="taskAttachments_popup_open" ><button type="button" ><img src="<s:url value="/includes/images/add3.png"/>" height="65" width="65" ></button></a>
                                                                                </div>
                                                                                <%                                                                                    }
                                                                                %>
                                                                            </div>
                                                                        </div>

                                                                    </div>

                                                                    <%--close of future_items--%>

                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- content end -->
                                                    </section> 
                                                </div>
                                                <div class="tab-pane fade" id="messages">


                                                    <%--div class="form-group"> &nbsp; &nbsp; <s:label> Notes: </s:label>     
                                                        <s:textarea class="form-control" id="resizable"  />


                                                                </div--%>

                                                    <section id="generalForm"><!--form-->



                                                        <!-- content start -->
                                                        <div class="col-md-12 col-md-offset-0" style="background-color:#fff">
                                                            <div class="features_items">
                                                                <div class="col-lg-12 ">
                                                                    <div class="" id="profileBox" style="float: center; margin-top: 5px">
                                                                        <div class="backgroundcolor" >
                                                                            <div class="panel-heading">
                                                                                <h4 class="panel-title">

                                                                                    <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                                                    <font color="#ffffff">Notes Search</font>

                                                                                </h4>
                                                                            </div>

                                                                        </div>
                                                                        <!-- content start -->
                                                                        <div class="col-lg-12 " >
                                                                            <s:form action="" theme="simple">

                                                                                <%--div class="edittaskfield-margin">                                                               
                                                                                    <s:textfield cssClass="textbox notesfieldMargin " id="notes_id" name="notes_id"  placeholder="Notes_Id" />
                                                                                    <s:textfield cssClass="textbox notesfieldMargin"  name="notes" id="notes_NameSearch" placeholder="Notes" />

                                                                                    <s:submit cssClass="cssbutton " id="searchButton" value="search" onclick="return getNotesDetailsBySearch()"/>&nbsp;
                                                                                    <a href="" class="Note_popup_open" ><input type="button" class="cssbutton " value="Add notes"></a> 
                                                                                </div--%>

                                                                                <div class="inner-reqdiv-elements">
                                                                                    <div class="row">
                                                                                        <span><notesErrorMsg></notesErrorMsg></span>
                                                                                        <div>
                                                                                            <div class="col-lg-4">
                                                                                                <label class="labelStylereq " style="color:#56a5ec;">Notes Name:</label>
                                                                                                <s:textfield cssClass="form-control"  name="notes" id="notes_NameSearch" placeholder="Notes" onclick="clearResultMsg()" maxLength="60"/>
                                                                                            </div>  
                                                                                            <div class="col-lg-1">
                                                                                                
                                                                                                <s:hidden cssClass="form-control " id="notes_id" name="notes_id"  placeholder="Notes_Id" onclick="clearResultMsg()" maxLength="11"/>
                                                                                            </div> 
                                                                                            <div class="col-lg-4">
                                                                                                <div class="row">
                                                                                                    <div class="col-lg-6">
                                                                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                                        <s:submit type="button" cssClass="add_searchButton form-control " id="searchButton" value="search" onclick="clearResultMsg();return getNotesDetailsBySearch()" cssStyle="margin:5px 0px;"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                                                    </div>
                                                                                                    <div class="col-lg-6">
                                                                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                                        <a href="" class="Note_popup_open" ><button type="button" class="add_searchButton form-control " value="" style="margin:5px 0px;" onclick="clearNotesFields();"><i class="fa fa-plus-square"></i>&nbsp;Add notes</button></a>
                                                                                                        
                                                                                                    </div>
                                                                                                    
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>         
                                                                                    </div>
                                                                                    <div id="loadingTask" class="loadingImg" style="display: none">
                                                                                        <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                                                                    </div>          
                                                                                </div>
                                                                            </s:form>

                                                                        </div>


                                                                        <div class="col-lg-12">
                                                                            <div  id="task_notes_populate"   style="padding-top: 8px; width:100%" >

                                                                            </div>
                                                                            <div id="task_pageNavPosition" align="right" style="margin-right:0vw"></div>
                                                                        </div>
                                                                    </div>


                                                                    <%--close of future_items--%>
                                                                </div>
                                                            </div>

                                                                <label class="page_option"> Display <select id="paginationOption_T" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                Tasks per page
                                                            </label>
                                                            <%-- overlay --%>
                                                            <div id="Note_popup">
                                                                <div id="AddNoteOverlay">

                                                                    <div style="background-color: #3BB9FF ">
                                                                        <table>
                                                                            <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;ADD Notes Details&nbsp;&nbsp; </font></h4></td>
                                                                            <span class=" pull-right"><h5><a href="" class="Note_popup_close" onclick="removeResultMsg()"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                                                        </table>
                                                                    </div>
                                                                    <div>

                                                                        <%--<form action="../tasks/addNewTask" theme="simple" >--%>
                                                                        <s:form action=""  theme="simple"   enctype="multipart/form-data" >
                                                                            <div>
                                                                                <span ><InsertNoteInfo></InsertNoteInfo></span>
                                                                                <div class="inner-addtaskdiv-elements required">
                                                                                    <label style="color:#56a5ec;" class="task-label">Notes&nbspNames:</label>
                                                                                    <s:textfield name="noteNames"  id="noteNamesadd" cssClass="form-control" maxLength="60"/>
                                                                                    <label style="color:#56a5ec;" class="task-label">Description:</label>
                                                                                    <s:textarea name="noteComments" id="noteCommentsadd" cssClass="form-control" />
                                                                                </div> 
                                                                                <div class="col-lg-6"></div>
                                                                                <div  class="col-lg-3">
                                                                                     <s:submit type="button" cssClass="add_searchButton form-control" cssStyle="margin:5px 0px;" value="" theme="simple" onclick="return addNotesDetails()"><i class="fa fa-plus-square"></i>&nbsp;AddNote</s:submit>
                                                                                    
                                                                                    <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                                                                </div>
                                                                                 <div  class="col-lg-3">
                                                                                   <s:reset type="button" cssClass="add_searchButton form-control fa fa-eraser " cssStyle="margin:5px 0px;" value="Clear" theme="simple" />
                                                                                </div>

                                                                            </div>
                                                                        </s:form>
                                                                    </div>       
                                                                    <p><font color="#F9f9f9">............................................ .......................................... ..................................................</p>

                                                                </div>
                                                            </div>

                                                            <div id="notes_popup">
                                                                <div id="NotesOverlay">

                                                                    <div style="background-color: #3BB9FF ">
                                                                        <table>
                                                                            <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Edit Notes Details&nbsp;&nbsp; </font></h4></td>
                                                                            <span class=" pull-right"><h5><a href="" class="notes_popup_close" onclick="removeResultMsg()"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                                                        </table>
                                                                    </div>

                                                                    <div>
                                                                        <%--<form action="../tasks/addNewTask" theme="simple" >--%>

                                                                        <div>
                                                                            <div>
                                                                                <span ><UpdateNoteInfo></UpdateNoteInfo></span>

                                                                                <form>
                                                                                    <div class="inner-addtaskdiv-elements required">
                                                                                        <label style="color:#56a5ec;" class="task-label">Notes&nbspNames:</label>
                                                                                        <s:hidden name="id" id="id" />
                                                                                        <s:hidden name="taskid" id="taskidoverlay"/>
                                                                                        <s:textfield name="noteNames"  id="noteNames" cssClass="form-control" maxLength="60"/>
                                                                                        <label style="color:#56a5ec;" class="task-label">Description:</label>
                                                                                        <s:textarea name="noteComments" id="noteComments" cssClass="form-control" />
                                                                                    </div> 
                                                                                    <div class="col-lg-6"></div>
                                                                                    <div  class="col-lg-3">
                                                                                        <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" theme="simple" onclick="return updateNoteDetails()"><i class="fa fa-plus-square"></i>&nbsp;AddNote</s:submit>
                                                                                       
                                                                                    </div>
                                                                                    <div  class="col-lg-3">
                                                                                        <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                                                                         <s:reset type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control fa fa-eraser " value="Clear" theme="simple" />
                                                                                    </div>
                                                                                </form>
                                                                            </div>           
                                                                        </div>

                                                                    </div>

                                                                    <p><font color="#F9f9f9">............................................ .......................................... ..................................................</p>
                                                                </div>

                                                            </div>
                                                        </div>





                                                    </section><!--/form-->


                                                </div>
                                            </div>
                                            <!-- /.panel-body -->
                                        </div>
                                        <!-- /.panel -->
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
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/javascript">
            var flag=document.getElementById("downloadFlag").value;
            //alert(flag);
            if(flag=="noAttachment"||flag=="noFile")
            {
                //alert("in if");
                document.getElementById('profileLi').className='active';
                document.getElementById('home').className='tab-pane fade in';
                document.getElementById('profile').className='tab-pane fade in active';
     
                //alert("before show consultantList function");
        
            }
            else
            {
                document.getElementById('homeLi').className='active';
         
            }
        </script>
        <script>
            setTimeout(function(){              
                $('#resume').remove();
            },3000);
        </script>
        
    <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        
    <script type="text/javascript">
        var recordPage=10;
          function pagerOption(){

               var paginationSize = document.getElementById("paginationOption_T").value;
                if(isNaN(paginationSize))
                   {
                       
                   }
                recordPage=paginationSize;
              // alert(recordPage)
                 $('#taskpagenav').tablePaginate({navigateType:'navigator'},recordPage);

            };
        $('#taskpagenav').tablePaginate({navigateType:'navigator'},recordPage);
        </script>

        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>



    </body>


</html>

