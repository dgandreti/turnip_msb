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
        <title>ServicesBay :: Task Search</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

       
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <%-- for date picket end--%>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/TaskAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
 <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <sx:head />
        

        <script type="text/javascript">
        function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("homeRedirectTable");
                sortableTableRows = document.getElementById("homeRedirectTable").rows;
                sortableTableName = "homeRedirectTable";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };

        </script>
        <script type="text/javascript">
            var myCalendar,myCalendar1;
            //,"docdatepickerfrom1","docdatepicker1"
            function doOnLoad() {
                
                var paginationSize = 10; 
               
                pager = new Pager('teamTaskTable', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
              
                pager.showPage(1);
               
                myCalendar = new dhtmlXCalendarObject(["docdatepickerfrom","docdatepicker"]);
               
                myCalendar.setSkin('omega');
               
                myCalendar.setDateFormat("%m-%d-%Y");
                myCalendar.hideTime();
               
                
                myCalendar1 = new dhtmlXCalendarObject(["startDate","endDate"]);
                // alert("hii1");
                myCalendar1.setSkin('omega');
                // alert("hii2");
                myCalendar1.setDateFormat("%Y/%m/%d");
                myCalendar1.hideTime();
                
              
                
                var today = new Date();
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
                
                // var from = yyyy+'/'+mm+'/01';
                var from = mm+'-01'+'-'+yyyy;
                mm=today.getMonth()+1;
                if(mm<10) {
                    mm='0'+mm
                } 
                dd=today.getDate()+1;
                var to = mm+'-'+dd+'-'+yyyy;
                var odd=today.getDate()+1;
                var overlayDate=yyyy+'/'+mm+'/'+odd;
               
                document.getElementById("loadingTaskSearch").style.display="none";
            }
                        
            
            
            function enterDateRepository()
            {
               
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
            function pagerOption(){

                paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                    alert(paginationSize);

                pager = new Pager('teamTaskTable', parseInt(paginationSize));
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                pager.showPage(1);

            };
        </script>
    <script>
            $(document).ready(function(){
                $(".img-swap").click(function(){
                    $(".popup_block").animate({
                        width: 'toggle'
                    });
                });
            });
            
            
            
            
            
        </script>
        <style>
            .task_sidePopup{
                  background: url('${pageContext.request.contextPath}/includes/images/addtask.png')  no-repeat;
                background-size: 25px 26px;
                 background-position: 77% 54%;
            }
        </style>
    </head>





    <body style="overflow-x: hidden" oncontextmenu="return false" onload="loadPopup(); doOnLoad(); init(); getTaskType();">
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
                  
                    <!-- Model popup -->
                    
                         <s:include value="/includes/menu/LeftMenu.jsp"/> 
                        
                        <!-- Model popup, end -->

                        <!-- content start -->
                        <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                            <div class="features_items">
                                <div class="col-lg-14 ">
                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">

                                                   
                                                    <font color="#ffffff">Tasks Search</font>
                                                    <i id="updownArrow" onclick="toggleContent('showTaskSearchDetails')" class="fa fa-minus"></i>

                                                </h4>
                                            </div>

                                        </div>
                                        <!-- content start -->
                                        <span id="taskSearchValidation"></span>
                                        <div class="col-sm-13">
                                            <s:form action="showTaskSearchDetails" onsubmit="return checkDateRange()" theme="simple">
                                               
                                                <div class="inner-reqdiv-elements">
                                                    <div class="">
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec;">Task Name</label>
                                                            <s:textfield id="task_name" cssClass="form-control"  name="task_name" placeholder="Task Name" tabindex="1" maxLength="60"/>
                                                        </div>
                                                       <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color: #56a5ec;">Start Date </label>
                                                            <div class="calImage"><s:textfield cssClass=" form-control " name="docdatepickerfrom" id="docdatepickerfrom" placeholder="FromDate" value="%{startDate}" tabindex="1"  onkeypress="return enterDateRepository();"><i class="fa fa-calendar"></i></s:textfield>
                                                            </div></div>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color: #56a5ec;">End Date </label>
                                                            <div class="calImage"> <s:textfield cssClass=" form-control " name="docdatepicker" placeholder="ToDate" value="%{endDate}" id="docdatepicker" tabindex="2"  onkeypress="return enterDateRepository();"><i class="fa fa-calendar"></i></s:textfield>
                                                            </div></div>
                                                       
                                                    </div>
                                                </div>
                                                <div class="inner-reqdiv-elements">
                                                    <div class="">
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color: #56a5ec;">Status </label>
                                                            <s:select  id="status"  name="task_status" label="Status"  cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="All" theme="simple" list="tasksStatusList" tabindex="1"/>
                                                        </div>
                                                        <div class="col-sm-4 pull-right">
                                                            <div class=" ">

                                                               <s:if test="#session.typeOfUsr=='VC'">
                                                                 <s:if test="#session.primaryrole==8 ">   
                                                                <div class="col-sm-6 pull-right">
                                                                    <label class="labelStylereq" style=""></label>
                                                                    <a id="addTaskButtonVendor" href="../tasks/addTask.action" class="add_searchButton form-control " style="margin:5px 0px;" tabindex="4"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a> 
                                                                </div>
                                                                 </s:if>
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="col-sm-6 pull-right">
                                                                    <label class="labelStylereq" style=""></label>
                                                                    <a id="addTaskButtonCustomer" href="../tasks/addTask.action" class="add_searchButton form-control " style="margin:5px 0px;" tabindex="4"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a> 
                                                                </div>
                                                                </s:else>
                                                                <div class="col-sm-6 pull-right">
                                                                    <label class="labelStylereq" style=""></label>
                                                                    <s:submit type="button"  cssClass="add_searchButton form-control " id="searchButton" value="search" tabindex="3" cssStyle="margin:5px 0px;" ><i class="fa fa-search"></i>&nbsp;Search</s:submit>&nbsp;
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                                    <div class="row"></div>
                                            </s:form>
                                             <div id="loadingTaskSearch" class="loadingImg">
                                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                                </div>   
                                               <span id="msg_clr">
                                                    <%
                                                        if (request.getParameter("resultMessage") != null) {
                                                            out.println(request.getParameter("resultMessage"));
                                                        }
                                                    %> 
                                                    </span> 
                                                
                                            <s:form>
                                                <div class="emp_Content" id="emp_div" align="center" style="display: none">
                                                    <table id="teamTaskTable" class="responsive CSSTable_task " border="5" cell-spacing="2">
                                                        <tbody>
                                                            <tr>
                                                                <th>Title</th>
                                                                <th>Comments</th>
                                                                <th>CreatedBy</th>
                                                                <th>CreatedDate</th>
                                                                <th>Priority</th>
                                                                <th>Status</th>
                                                            </tr>
                                                            <s:if test="teamtaskDetails.size <= 0">
                                                                <tr>
                                                                    <td colspan="8"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>

                                                            <s:iterator value="teamtaskDetails">
                                                                <s:url var="myUrl" action="../tasks/getTaskDetails.action">
                                                                    <s:param name="taskid"><s:property value="task_id"></s:property></s:param>
                                                                     <s:param name="myTask">mytaskflag</s:param>
                                                                </s:url>
                                                                <tr>
                                                                    <s:hidden id="taskid" name="taskid" value="task_id"/>
                                                                    <td><s:a href='%{#myUrl}'><s:property value="task_name"></s:property></s:a></td>

                                                                    <s:if test="task_comments.length()>9">    
                                                                        <!--<td><s:a href="#" onclick="taskCommentsDetailsToViewOnOverlay('%{task_id}');taskCommentsPopup();" cssClass="taskComments_popup_open"><s:property value="%{task_comments.substring(0,10)}"></s:property>..</s:a></td>-->
                                                                        <td><s:a href="#" onclick="taskCommentsPopup('%{task_comments}');" cssClass="taskComments_popup_open"><s:property value="%{task_comments.substring(0,10)}"></s:property>..</s:a></td>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:property value="task_comments"></s:property></td>
                                                                    </s:else>
                                                                    <td><s:property value="task_created_by"></s:property></td>
                                                                    <td><s:property value="task_created_date"></s:property></td>
                                                                    <td><s:property value="taskPriority"></s:property></td>
                                                                    <td><s:property value="task_status"></s:property></td>
                                                                    </tr>
                                                            </s:iterator>
                                                        </tbody>
                                                    </table>
                                                    <br/>
                                                    <s:if test="teamtaskDetails.size  > 0">
                                                        <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>25</option>
                                                                <option>50</option>
                                                            </select>
                                                            Tasks per page
                                                        </label>
                                                    </s:if>
                                                    <div align="right" class="pull-right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                </div>
                                            </s:form>



                                        </div>
                                    </div>
                                </div>
                                <%--close of future_items--%>
                            </div>
                        </div>
                            <div class="side_popup">

                <div class="popup_block"> 

                    <div class="addlink" >
                       
                        

                        <div class="alignField">

                           
                           
                          <a href="../tasks/addTask.action" >Add Task</a>
                            </div>
                       
                        </div>
                    </div>

                   <div class="slide_popup task_sidePopup img-swap"><i></i></div>
                </div>   
                </div>
                    </div>
                          
                <!-- content end -->
                
                <div id="taskComments_popup">
                    <div id="preskillBox" class="marginTasks">
                        <div class="backgroundcolor">
                            <table>
                                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Comments Details&nbsp;&nbsp; </font></h4></td>
                                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="taskComments_popup_close" onclick="taskCommentsPopup();" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                            </table>
                        </div>
                        <div>

                            <s:textarea name="taskComments"   id="commentsArea"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                        </div>
                        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                    </div>
                </div>

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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
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
                // alert(recordPage)
                $('#teamTaskTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#teamTaskTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
     
 <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("tpaginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                //alert(recordPage)
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

