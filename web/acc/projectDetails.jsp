<!--
    Author     : Riza
-->
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Project Details Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/accountDetails/projects.css"/>">
        <%-- aklakh js single file start --%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>

        <%-- aklakh js single file end --%>
        <%-- aklakh css single file start --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <%-- aklakh css single file end --%>
        <%-- for date picket start--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>


        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <%-- for date picket end--%>

    <sx:head />
    <script>
        //           Pagination Script
        var pager;
        function pagerOption(){

            paginationSize = document.getElementById("paginationOption").value;
            pager = new Pager('projectResults', parseInt(paginationSize));
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);

        };
        //            End Pagination Script
        var myCalendar1;
        var myCalendar2;
        var myCalendar3;
        function doOnLoad() {
            myCalendar1 = new dhtmlXCalendarObject(["projectStartDate","projectTargetDate"]);
            myCalendar2 = new dhtmlXCalendarObject(["projectStartDateOverlay","projectTargetDateOverlay"]);
            myCalendar3 = new dhtmlXCalendarObject(["projectStartDateSearch","projectTargetDateSearch"]);
            myCalendar1.setSkin('omega');
            myCalendar2.setSkin('omega');
            myCalendar3.setSkin('omega');
            myCalendar1.setDateFormat("%m-%d-%Y");
            myCalendar2.setDateFormat("%m-%d-%Y");
            myCalendar3.setDateFormat("%m-%d-%Y");
            myCalendar1.hideTime();
            myCalendar2.hideTime();
            myCalendar3.hideTime();
            var startDate=document.getElementById("projectStartDate").value;
            var endDate =document.getElementById("projectTargetDate").value;
            var status = document.getElementById("project.project_status").value;
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1;
            var year = today.getFullYear();
           
            if(dd < 10)
            {
                dd = "0"+dd;     
            }
            if(mm < 10)
            {
                mm = "0"+mm;     
            }   
            var day = mm+"-"+dd+"-"+year;
           
           
            var splitTaskStartDate = endDate.split('-');
            var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
            var splitTaskEndDate = day.split('-');
            var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
            var taskStartDate = Date.parse(taskAddStartDate);
            var taskTargetDate= Date.parse(taskAddtargetDate);
            var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
            if(difference>0)
            {
             
                document.getElementById("addSubPro").style.display="none";
                document.getElementById("addTeamMemberButton").style.display="none";
            }  
            if(status!="Active" )
            {
                document.getElementById("addSubPro").style.display="none";
                document.getElementById("addTeamMemberButton").style.display="none";
             
            }
            myCalendar2.setSensitiveRange(startDate, null);
            
            var mainPrjectStartDate = document.getElementById('mainPrjectStartDate').value;
               
            var mainPrjectTargetDate = document.getElementById('mainPrjectTargetDate').value;
            myCalendar1.setSensitiveRange(mainPrjectStartDate, null);
          
            document.getElementById("projectStartDate").value=overlayDate;
            document.getElementById("projectTargetDate").value=overlayDate;
            document.getElementById("projectStartDateOverlay").value=overlayDate;
            document.getElementById("projectTargetDateOverlay").value=overlayDate;
            document.getElementById("projectStartDateSearch").value=overlayDate;
            document.getElementById("projectTargetDateSearch").value=overlayDate;
            
        };






    </script>


</head>
<body onload="calculateRemainingHrs();doOnLoad();javascript: $.getScript('/includes/js/general/GridNavigation.js' );">
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
                        <div class="col-sm-12 col-md-9 col-lg-10 right_content" style="background-color:#fff">
                            <div class="features_items">
                                <div class="col-sm-12">
                                    <div class="row" id="profileBox" style="margin-left: auto;margin-right: auto; margin-top: 5px">
                                        <br>
                                        <% String accFlag = "accDetails";%> 
                                        <div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px">
                                            Account&nbsp;Name:                                          
                                            <span style="color:  #FF8A14;"><s:property value="%{account_name}"/></span>
                                        </div>   <br>
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">

                                                    <s:if test="project.projectType=='Main Project'">
                                                        <font color="#ffffff">Project Details</font>
                                                    </s:if>
                                                    <s:else>
                                                        <font color="#ffffff">Sub Project Details</font>

                                                    </s:else>

                                                    <% String flag = "proSearch";
                                                    %>


                                                    <s:if test="project.projectType=='Main Project'">
                                                        <s:url var="myUrl" action="getMainProjects.action">

                                                        </s:url>
                                                    </s:if>
                                                    <s:else>
                                                        <s:url var="myUrl" action="projectDetails.action">
                                                            <s:param name="projectID"><s:property value="project.parentProjectID"/></s:param>
                                                            <s:param name="accountID"><s:property value="accountID"/></s:param>
                                                        </s:url>
                                                    </s:else>
                                                    <span onclick="javascript: $.getScript('/includes/js/general/GridNavigation.js' );"/> <s:a href='%{#myUrl}' id="backToList" cssClass="pull-right"><i class="fa fa-undo"></i></s:a></span>
                                                </h4>
                                            </div>

                                        </div>
                                        <!-- content start -->
                                        <div>
                                            <s:form action="updateProject" method="post" theme="simple" value="project">
                                                <s:if test="%{resultMessage== 'Successfully updated'}">
                                                    <span><updateProject>
                                                            <div style="margin: 5px 15px" id="validation"><font color="green"> Project Updated successfully !</font></div></updateProject></span>
                                                            </s:if>
                                                            <s:else>
                                                    <span><updateProject></updateProject></span> 
                                                </s:else>

                                                <div class="inner-addtaskdiv-elements " >
                                                    <s:if test="%{parentProjectName != null}">
                                                        <s:url action="projectDetails.action" var="getDetails">
                                                            <s:param name="projectID"><s:property value="project.parentProjectID"/></s:param>
                                                            <s:param name="accountID"><s:property value="accountID"/></s:param>
                                                        </s:url>
                                                        <label class="projectLabelStyle">Parent Project Name : </label><s:a href="%{getDetails}"><s:property value="parentProjectName"/></s:a>
                                                    </s:if>
                                                    <s:hidden id="textParentProjectID" value="%{projectID}" />
                                                    <s:hidden id="accountID" value="%{project.accountID}"/>
                                                    <s:hidden id="projectType" value="%{project.projectType}"/>                                                    </div>
                                                    <s:hidden id="mainProjectId" value="%{project.parentProjectID}"/> 
                                                     <s:hidden id="mainProjectStatus" value="%{mainProjectStatus}" /> 


                                                <div class="col-sm-3 required">
                                                    <label > Name </label><s:textfield  cssClass="form-control" id="editprojectName" name="project.projectName" key="project.projectName" value="%{project.projectName}" maxlength="30" onchange="checkProjectName(this.value,'%{project.projectType}');" cssStyle="margin-right:50px; "/>
                                                </div>    
                                                <div class="col-sm-3 required">
                                                    <label  >Status </label><s:select  id="project.project_status"  name="project.project_status" cssClass="form-control SelectBoxStyles "  list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','Completed':'Completed','Paused':'Paused'}"  headerValue="'%{project.project_status}'" cssStyle="margin-right:50px"/>
                                                </div>  
                                                <div class="col-sm-4">    
                                                    <s:set scope="request" name="prjFlag" value="%{project.projectType}"/>
                                                    <%
                                                        int noOfResource = 0;
                                                        int projectId = Integer.parseInt(request.getAttribute("projectID").toString());
                                                        String prjFlag = "";
                                                        prjFlag = request.getAttribute("prjFlag").toString();
                                                        System.out.println("the flag for project is..." + prjFlag);
                                                        noOfResource = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfResourcesInProject(projectId, prjFlag);
                                                    %>
                                                    <label >Number of Resources </label><br> <% out.print(noOfResource + "");%>
                                                </div> 
                                                <div class="col-sm-12" >
                                                    <label> Skill Set </label><s:textfield  cssClass="form-control" id="projectReqSkills" placeholder="Skill Set" name="project.projectReqSkillSet" value="%{project.projectReqSkillSet}" style="" onkeydown="checkCharSkill(this)" onblur="removeMsg()" maxLength="100"/>
                                                    <div class="charNum" id="Skill"></div> 
                                                </div>




                                                <div class="inner-addtaskdiv-elements " style="margin-left: -15px">
                                                    <div class="col-sm-3 required">
                                                        <label >Start Date </label>
                                                        <div class="calImage"><s:textfield cssClass="form-control" name="project.projectStartDate" value="%{project.projectStartDate}" id="projectStartDate" placeholder="%{project.projectStartDate}"  onkeypress=" return projectDateRepository();" onclick="dateValidate();" autocomplete="off"><i class="fa fa-calendar"></i></s:textfield>
                                                        </div></div>  
                                                    <div class="col-sm-3 required">
                                                        <label >Target Date </label>
                                                        <div class="calImage"><s:textfield cssClass="form-control" name="project.projectTargetDate" value="%{project.projectTargetDate}" id="projectTargetDate" placeholder="%{project.projectTargetDate}"  onkeypress=" return projectDateRepository();" onclick="dateValidate();" autocomplete="off"><i class="fa fa-calendar"></i></s:textfield>
                                                        </div></div>
                                                    <div class="col-sm-3  required">
                                                        <label>Target hours</label>
                                                        <div class="form-group input-group">
                                                            <s:textfield cssClass="form-control "  id="editProjectTargetHrs"  value="%{project.projectTargetHrs}" name="project.projectTargetHrs"  onkeypress="return noOfHoursValidate(event, this.id)"  onblur="calculateSubProjectTargetHrs()"/>
                                                            <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                                        </div>
                                                        <s:hidden name ="remainingSubpjctTotalHrs" id="remainingSubpjctTotalHrs" value="%{remainingTargetHrs}"/>
                                                        <s:hidden name ="targetHours" id="targetHours" value="%{project.projectTargetHrs}"/>
                                                        <s:hidden name ="mainPrjectStartDate" id="mainPrjectStartDate" value="%{mainProjectStartDate}"/>
                                                        <s:hidden name ="mainProjectTargetDate" id="mainProjectTargetDate" value="%{mainProjectTargetDate}"/>

                                                    </div> 
                                                </div>
                                                <div class="inner-addtaskdiv-elements" style="margin-left: -15px">

                                                    <div class="col-sm-3 ">
                                                        <label>Worked hours</label>
                                                        <div class="form-group input-group">
                                                            <s:textfield cssClass="form-control " id="editProjectWorkedHrs"   name="project.projectWorkedHrs" value="%{project.projectWorkedHrs}" placeholder="" readonly="true"  />
                                                            <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label>Remaining hours</label>
                                                        <div class="form-group input-group">
                                                            <s:textfield cssClass="form-control " id="editProjectRemainingHrs"   name="project.projectRemainingHrs" placeholder="" readonly="true"  />
                                                            <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                                        </div>
                                                    </div>        

                                                    <s:if test="project.projectType=='Main Project'">   
                                                        <div class="col-sm-3">
                                                            <label>Cost Center </label><s:select  id="costCenterName"  name="project.costCenterName" cssClass="form-control SelectBoxStyles "  list="%{costCenterNames}" value="%{project.costCenterName}" cssStyle="margin-right:50px"/>
                                                        </div>
                                                    </s:if> 
                                                </div>    


                                                <div class="inner-addtaskdiv-elements " >
                                                    <label class="projectLabelStyle">Description </label><s:textarea  cssClass="form-control" name="project.project_description" id="project_descriptions" placeholder="%{project.project_description}" onkeydown="checkCharProjects(this);" onblur="removeMsg()"/>
                                                </div>
                                                <div class="charNum" id="Projects"></div>
                                                <div class="inner-addtaskdiv-elements">
                                                    <div style="text-align: right">
                                                        <s:submit id="updateProjectButton" type="button" cssClass="cssbutton fa fa-refresh" value="Update Project" style="height:30px" theme="simple" onclick="return updateProjectValidation();"/>
                                                    </div>
                                                </div>
                                            </s:form>

                                        </div>
                                        <div>
                                            <ul class="nav nav-tabs">
                                                <s:if test="project.projectType=='Main Project'">
                                                    <li class="active">
                                                        <a data-toggle="tab" id="subProjectsLi" href="#subProjects" onclick="pagerOption()">Sub Projects</a>
                                                    </li>
                                                    <li>
                                                        <a data-toggle="tab" id="teamMembersLi" href="#projectTeam" onclick="PagerOption1();javascript: $.getScript('/includes/js/general/GridNavigation.js' );">Project Team</a>

                                                    </li>
                                                </s:if>
                                            </ul>
                                            <div class="tab-content">
                                                <br>
                                                <s:if test="%{resultMessage=='added'}">

                                                    <span id="validation" style="margin-left: 14px "> <font color="green"> Sub-Project Added successfully !</font></span>
                                                    </s:if>
                                                <div id="subProjects" class="tab-pane fade in active" >

                                                </div>
                                                <div id="projectTeam" class="tab-pane fade">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%--close of future_items--%>
                                </div>
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
    <script>
        $("#validation").show().delay(5000).fadeOut();
    </script>

    <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
    <s:if test="project.projectType=='Main Project'">

        <script>
            ajaxReplaceDiv('/getSubProjects?parentProjectName=<s:property value="project.projectName" />','#subProjects','parentProjectID=<s:property value="project.projectID" />');
            ajaxReplaceDiv('/getProjectsTeamMembers','#projectTeam','projectID=<s:property value="project.projectID" />');
        </script>
    </s:if>
    <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>

</body>
</html>
