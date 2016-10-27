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
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/ajaxfileupload.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/TaskAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/dashboard.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
         <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
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
    <body onload="doOnLoad(); init(); getTaskType(); " oncontextmenu="return false" style="overflow-x: hidden">
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
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content">
                        <div class="features_items">
                            <div class="row">
                                <div class="col-lg-14">
                                    <div class="panel panel-info">
                                        <div class="panel-heading" id="taskedit-heading">
                                            <font class="titleColor">&nbsp;&nbsp;&nbsp;Edit Task  </font>  
                                           <s:if test="myTask=='mytaskflag'">
                                            <span class="pull-right"><a href="doTasksSearch.action" id="editTaskSearchBackButtton" style="margin-right:9px" ><i class="fa fa-undo"></i></a></span>
                                            </s:if>
                                            <s:else>
                                              <span class="pull-right"><a href="doTeamTasksSearch.action" id="editTaskSearchBackButtton" style="margin-right:9px" ><i class="fa fa-undo"></i></a></span>   
                                            </s:else>
                                              <s:hidden id="myTask"  value="%{myTask}"></s:hidden>
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
                                                   
                                                    <form  theme="simple">
                                                        <div><br>
                                                            <span id="editTaskValidate"><editTask></editTask></span>
                                                             <span ><UpdateTaskInfo></UpdateTaskInfo></span>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4 required">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">StartDate </label>
                                                                        <div class="calImage"><s:textfield cssClass="form-control " name="startDate" id="start_date"  value="%{tasksVto.start_date}" tabindex="1"  onkeypress="return enterTaskDateRepository(this)"><i class="fa fa-calendar"></i></s:textfield>
                                                                        <s:hidden name="taskid" value="%{taskid}" id="taskid"/>
                                                                          <s:hidden id="addTaskPage" value="1" name="addTaskPage"/>  
                                                                        </div></div>
                                                                    <div class="col-sm-4 required">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">EndDate </label>
                                                                        <div class="calImage"> <s:textfield cssClass="form-control " name="endDate" id="end_date"  value="%{tasksVto.end_date}" tabindex="2"  onkeypress="return enterTaskDateRepository(this)"><i class="fa fa-calendar"></i></s:textfield>
                                                                        </div></div>
                                                                    <div class="col-sm-4 ">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Status </label>
                                                                        <s:select  id="task_status"  name="taskStatus" cssClass="SelectBoxStyles form-control" headerKey="-1" theme="simple" list="tasksStatusList" tabindex="3" value="%{tasksVto.task_status}" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Priority </label>
                                                                        <s:select  id="taskPriority"  name="priority" cssClass="SelectBoxStyles form-control" headerKey="M" headerValue="Medium" theme="simple"  tabindex="4" list="#@java.util.LinkedHashMap@{'L':'Low','H':'High'}" value="%{tasksVto.task_priority}"  />
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Related To </label>
                                                                        <s:select  id="taskRelatedTo" name="taskRelatedTo" cssClass="SelectBoxStyles form-control" headerKey="-1"  theme="simple" list="tasksRelatedToList"  onchange="changeTaskType();" tabindex="5" value="%{tasksVto.task_related_to}"  />
                                                                        <s:hidden name="taskid" value="%{taskid}" id="taskid"/>
                                                                    </div>
                                                                    <div class="col-sm-4" id="csrDiv">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Projects </label>
                                                                        <s:select  id="taskType"   name="taskType" cssClass="SelectBoxStyles form-control" headerKey="-1"  theme="simple" list="%{tasksVto.typeMaps}" value="%{tasksVto.task_prj_related_to}" tabindex="6" onchange="changeProject();" />
                                                                        <s:hidden name="taskProject" value="%{tasksVto.task_prj_related_to}" id="taskProject"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements required">
                                                                <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color: #56a5ec;">Title</label>
                                                                <s:textfield name="taskName" id="task-textform" placeholder="Enter Title Here" cssClass="form-control" value="%{tasksVto.task_title}" tabindex="7" maxLength="60" />
                                                                </div>
                                                                </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color: #56a5ec;">Description</label>
                                                                <s:textarea name="task_comments" id="task_comments" placeholder="Enter Task Description Here" cssClass="form-control" value="%{tasksVto.task_comments}" onkeypress="checkEditTaskDescription(this)" tabindex="8" maxLength="60" onblur="clearDescriptionSpan()"/>
                                                                </div>
                                                                </div>
                                                              <div class="charNum" id="description_feedback"></div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="col-sm-13">
                                                                     <s:if test="#session.primaryrole==7 || #session.primaryrole==11">
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Primary AssignTo </label>
                                                                        <s:select  id="primary_assign" disabled="true"  name="primaryAssign" cssClass="SelectBoxStyles form-control" headerKey="-1" theme="simple" list="teamMemberNames" tabindex="9" value="%{tasksVto.pri_assigned_to}" />
                                                                        <s:hidden name="secondaryId" id="secondaryId" value="%{tasksVto.sec_reportsId}" />
                                                                        <s:hidden name="primaryId" id="primaryId" value="%{tasksVto.pri_assigned_to}" />
                                                                    </div>
                                                                    
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Secondary AssignTo</label>
                                                                        <s:textfield  id="secondaryReport" disabled="true"  name="sec_assign_to" placeholder="SecondaryAssignTo" cssClass="form-control"  theme="simple" onkeyup="return getSecondaryAssignedNames();" value="%{tasksVto.sec_assigned_to}" autocomplete='off' tabindex="10" maxLength="30"/>
                                                                    </div>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Primary AssignTo </label>
                                                                        <s:select  id="primary_assign"  name="primaryAssign"  cssClass="SelectBoxStyles form-control" headerKey="-1" theme="simple" list="teamMemberNames" tabindex="9" value="%{tasksVto.pri_assigned_to}" />
                                                                        <s:hidden name="secondaryId" id="secondaryId" value="%{tasksVto.sec_reportsId}" />
                                                                        <s:hidden name="primaryId" id="primaryId" value="%{tasksVto.pri_assigned_to}" />
                                                                    </div>
                                                                    
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color: #56a5ec;">Secondary AssignTo</label>
                                                                        <s:hidden id="secondaryAssign" value="%{tasksVto.sec_assigned_to}"/>
                                                                        <s:textfield  id="secondaryReport"  name="sec_assign_to"  placeholder="SecondaryAssignTo" cssClass="form-control"  theme="simple" onkeyup="return getSecondaryAssignedNames();" value="%{tasksVto.sec_assigned_to}" autocomplete='off' tabindex="10" maxLength="30" onblur="removingValidateMsg()"/>
                                                                    </div> 
                                                                    </s:else>
                                                                </div>
                                                            </div>
                                                                    <div class="row"></div>
                                                            <div class="inner-addtaskdiv-elements "><span id="validationMessage" /></div>   
                                                         
                                                                                    <div class="col-lg-10"></div>
                                                            <div  class="col-sm-2 pull-right">
                                                                <s:submit type="button"  cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" theme="simple" tabindex="14" onclick="return doUpdateTaskInfo();"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div id="taskAttachments_popup">
                                                    <div id="taskAttachOverlay">
                                                        <div class="overlayOrButton_color">
                                                            <table>
                                                                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Task Attachments Details&nbsp;&nbsp; </font></h4></td>
                                                              <td style="">  <span class=" pull-right"><h5><a href="" class="taskAttachments_popup_close" onclick="attachementPopUp()"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span></td>
                                                            </tr>
                                                            </table>
                                                        </div>
                                                        <div>
                                                            <br>
                                                             <span id="upaloadMessages"></span>
                                                            <s:form action="addAttachment"  theme="simple"   enctype="multipart/form-data" onsubmit="return attachmentUploadValidation();">
                                                                <div>
                                                                    <div class="inner-addtaskdiv-elements">
                                                                        <s:hidden id="downloadFlag" name="downloadFlag" value="%{downloadFlag}"/>
                                                                        <s:hidden id="taskid" name="taskid" value="%{taskid}"/>
                                                                        <s:file name="taskAttachment" id="taskAttachment"/>
                                                                    </div>
                                                                
                                                                    <center><s:submit type="button" cssClass="cssbutton fa fa-upload" value="Upload" theme="simple"  /></center><br>
                                                                </div>
                                                                <s:token />
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
                                                                                    <br><br>&nbsp;&nbsp;<a href="" id="addAttachmentPopUpButtton" class="taskAttachments_popup_open" ><button type="button" onclick="attachementPopUp()"><img src="<s:url value="/includes/images/add3.png"/>" height="65" width="65" ></button></a>
                                                                                </div>
                                                                                <%                                                                                    } else {
                                                                                %>


                                                                                <div class="logcol logclr" >
                                                                                    <br><br>&nbsp;&nbsp;<a href="" id="addAttachmentPopUpButton" class="taskAttachments_popup_open" ><button type="button"  onclick="attachementPopUp()"><img src="<s:url value="/includes/images/add3.png"/>" height="65" width="65" ></button></a>
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

                                                    <section id="generalForm"><!--form-->



                                                        <!-- content start -->
                                                        <div class="" style="background-color:#fff">
                                                            <div class="features_items">
                                                                <div class="">
                                                                    <div class="" id="profileBox" style="float: center; margin-top: 5px">
                                                                        <div class="backgroundcolor" >
                                                                            <div class="panel-heading">
                                                                                <h4 class="panel-title">

                                                                                
                                                                                    <font color="#ffffff">Notes Search</font>

                                                                                </h4>
                                                                            </div>

                                                                        </div>
                                                                        <!-- content start -->
                                                                        <div class="col-lg-12 " >
                                                                            <s:form action="" theme="simple">


                                                                                <div class="inner-reqdiv-elements">
                                                                                    <div class="row">
                                                                                        <span><notesErrorMsg></notesErrorMsg></span>
                                                                                        <div>
                                                                                            <div class="col-sm-4">
                                                                                                <label class="labelStylereq " style="color:#56a5ec;">Notes Name</label>
                                                                                                <s:textfield cssClass="form-control"  name="notes" id="notes_NameSearch" placeholder="Notes" onclick="clearResultMsg()" maxLength="60" tabindex="1"/>
                                                                                            </div>  
                                                                                            <div class="col-sm-1">
                                                                                                
                                                                                                <s:hidden cssClass="form-control " id="notes_id" name="notes_id"  placeholder="Notes_Id" onclick="clearResultMsg()" maxLength="11"/>
                                                                                            </div> 
                                                                                            <div class="col-sm-4">
                                                                                                <div class="row">
                                                                                                    <div class="col-sm-6 pull-right">
                                                                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                                        <s:submit type="button" cssClass="add_searchButton form-control " id="searchButton" value="search" onclick="clearResultMsg();return getNotesDetailsBySearch()" cssStyle="margin:5px 0px;" tabindex="2"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                                                    </div>
                                                                                                    <div class="col-sm-6 pull-right">
                                                                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                                        <a href="" class="Note_popup_open" id="addNotesOverlay" ><button type="button" class="add_searchButton form-control " value="" style="margin:5px 0px;" onclick="clearNotesFields();" tabindex="3"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>
                                                                                                        
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

                                                                    <div class="overlayOrButton_color">
                                                                        <table>
                                                                            <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;ADD Notes Details&nbsp;&nbsp; </font></h4></td>
                                                                            <span class=" pull-right"><h5><a href="" id="closeNoteOverlayButton" class="Note_popup_close" onclick="removeResultMsg()"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
                                                                        </table>
                                                                    </div>
                                                                    <div>

                                                                        <%--<form action="../tasks/addNewTask" theme="simple" >--%>
                                                                        <s:form action=""  theme="simple"   enctype="multipart/form-data" >
                                                                            <div>
                                                                                <span ><InsertNoteInfo></InsertNoteInfo></span>
                                                                                <div class="inner-addtaskdiv-elements required">
                                                                                    <label style="color:#56a5ec;" class="task-label">&nbsp;Notes&nbspNames&nbsp;</label>
                                                                                    <s:textfield name="noteNames"  id="noteNamesadd" placeholder="Notes Names" cssClass="form-control" maxLength="60"/>
                                                                                    <label style="color:#56a5ec;" class="task-label">&nbsp;Description&nbsp;</label>
                                                                                    <s:textarea name="noteComments" id="noteCommentsadd" placeholder="Description" cssClass="form-control" />
                                                                                </div> 
                                                                                <div class="col-lg-6"></div>
                                                                                <div  class="col-sm-3 pull-right">
                                                                                    <s:submit type="button" id="addNoteButton" cssClass="add_searchButton form-control" cssStyle="margin:5px 0px;" value="" theme="simple" onclick="return addNotesDetails()"><i class="fa fa-plus-square"></i>&nbsp;Save</s:submit>
                                                                                    
                                                                               
                                                                                </div>
                                                                                 <div  class="col-sm-3 pull-right">
                                                                                   <s:reset type="button" id="resetClearButton" cssClass="add_searchButton form-control fa fa-eraser " cssStyle="margin:5px 0px;" value="Clear" theme="simple" />
                                                                                </div>

                                                                            </div>
                                                                        </s:form>
                                                                    </div>       
                                                                    <p><font color="#F9f9f9">............................................ .......................................... ..................................................</p>

                                                                </div>
                                                            </div>

                                                            <div id="notes_popup">
                                                                <div id="NotesOverlay">

                                                                    <div class="overlayOrButton_color">
                                                                        <table>
                                                                            <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Edit Notes Details&nbsp;&nbsp; </font></h4></td>
                                                                            <span class=" pull-right"><h5><a href="" class="notes_popup_close" onclick="removeResultMsg()"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
                                                                        </table>
                                                                    </div>

                                                                    <div>
                                                                     

                                                                        <div>
                                                                            <div>
                                                                                <span ><UpdateNoteInfo></UpdateNoteInfo></span>

                                                                                <form>
                                                                                    <div class="inner-addtaskdiv-elements required">
                                                                                        <label style="color:#56a5ec;" class="task-label">&nbsp;Notes&nbspNames&nbsp;</label>
                                                                                        <s:hidden name="id" id="id" />
                                                                                        <s:hidden name="taskid" id="taskidoverlay"/>
                                                                                        <s:textfield name="noteNames"  id="noteNames" cssClass="form-control" maxLength="60"/>
                                                                                        <label style="color:#56a5ec;" class="task-label">&nbsp;Description&nbsp;</label>
                                                                                        <s:textarea name="noteComments" id="noteComments" cssClass="form-control" />
                                                                                    </div> 
                                                                                   
                                                                                    <div  class="col-sm-3 pull-right">
                                                                                        <s:submit type="button" id="updateTaskButton" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" theme="simple" onclick="return updateNoteDetails()"><i class="fa fa-refresh"></i>&nbsp;Update</s:submit>
                                                                                       
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
            </div>
            </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
<!--         <script type="text/JavaScript">
            $("#validationMessage").show().delay(5000).fadeOut();
        </script>-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
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
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>



    </body>


</html>

