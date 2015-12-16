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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

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
    <header id="header"><!--header-->
        <div class="header_top"><!--header_top-->
            <div class="container">
                <s:include value="/includes/template/header.jsp"/>
            </div>
        </div>

    </header>

    <div>
        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/>
                    <!-- content start -->
                    <div class="col-sm-12 col-md-9 col-lg-10 right_content" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-sm-12 ">
                                <div class="row" id="profileBox" style="float: left; margin-top: 5px">
                                    <br>
                                    <% String accFlag = "accDetails";%> 
                                    <div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px">
                                        Account&nbsp;Name:                                          
                                        <%-- <s:url var="myUrl" action="/acc/viewAccount.action">
                                             <s:param name="accountSearchID"><s:property value="accountID"/></s:param>
                                             <s:param name="accFlag"><%= accFlag%></s:param>
                                         </s:url>--%>
                                        <span style="color:  #FF8A14;"><s:property value="%{account_name}"/></span>
                                    </div>   <br>
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
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
                                                <span onclick="javascript: $.getScript('/includes/js/general/GridNavigation.js' );"/> <s:a href='%{#myUrl}' cssClass="pull-right"><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                </h4>
                                            </div>

                                        </div>
                                        <!-- content start -->
                                        <div>
                                        <s:form action="updateProject" method="post" theme="simple" value="project">
                                            <s:if test="%{resultMessage== 'Successfully updated'}">
                                                <span><updateProject><font color="green"> Project Updated successfully !</font></updateProject></span>
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
                                                    <label class="projectLabelStyle">Parent Project Name: </label><s:a href="%{getDetails}"><s:property value="parentProjectName"/></s:a>
                                                </s:if>
                                                <s:hidden id="textParentProjectID" value="%{projectID}" />
                                                <s:hidden id="accountID" value="%{project.accountID}"/>
                                                <s:hidden id="projectType" value="%{project.projectType}"/>                                                    </div>
                                                <s:hidden id="mainProjectId" value="%{project.parentProjectID}"/> 

                                         
                                                <div class="col-sm-3 required">
                                                    <label > Name: </label><s:textfield  cssClass="form-control" id="editprojectName" name="project.projectName" key="project.projectName" value="%{project.projectName}" maxlength="30" onchange="checkProjectName(this.value,'%{project.projectType}');" cssStyle="margin-right:50px; "/>
                                                </div>    
                                                <div class="col-sm-3 required">
                                                    <label  >Status: </label><s:select  id="project.project_status"  name="project.project_status" cssClass="form-control SelectBoxStyles "  list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','Completed':'Completed','Paused':'Paused'}"  headerValue="'%{project.project_status}'" cssStyle="margin-right:50px"/>
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
                                                    <label >Number of Resources: </label><br> <% out.print(noOfResource + "");%>
                                                </div> 
                                                <div class="col-sm-12" >
                                                    <label> Skill Set: </label><s:textfield  cssClass="form-control" id="projectReqSkills" placeholder="Skill Set" name="project.projectReqSkillSet" value="%{project.projectReqSkillSet}" style="" onkeydown="checkCharSkill(this)" onblur="removeMsg()" maxLength="100"/>
                                                </div>
                                           


                                            <div class="charNum" id="Skill"></div>                                                 
                                            <div class="inner-addtaskdiv-elements " style="margin-left: -15px">
                                                <div class="col-sm-3 required">
                                                    <label >Start Date: </label><s:textfield cssClass="form-control" name="project.projectStartDate" value="%{project.projectStartDate}" id="projectStartDate" placeholder="%{project.projectStartDate}" cssStyle="background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;margin-right:38px" onkeypress=" return projectDateRepository();" onclick="dateValidate();" autocomplete="off"/>
                                                </div>  
                                                <div class="col-sm-3 required">
                                                    <label >Target Date: </label><s:textfield cssClass="form-control" name="project.projectTargetDate" value="%{project.projectTargetDate}" id="projectTargetDate" placeholder="%{project.projectTargetDate}" cssStyle="background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeypress=" return projectDateRepository();" onclick="dateValidate();" autocomplete="off"/>
                                                </div>
                                                <div class="col-sm-3  required">
                                                    <label>Target hours</label>
                                                    <div class="form-group input-group">
                                                        <s:textfield cssClass="form-control "  id="editProjectTargetHrs"  value="%{project.projectTargetHrs}" name="project.projectTargetHrs"  onkeypress="return noOfHoursValidate(event, this.id)"  onblur="calculateSubProjectTargetHrs()"/>
                                                        <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                                    </div>
                                                    <s:hidden name ="remainingSubpjctTotalHrs" id="remainingSubpjctTotalHrs" value="%{remainingTargetHrs}"/>
                                                    <s:hidden name ="targetHours" id="targetHours" value="%{project.projectTargetHrs}"/>
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
                                                        <label>Cost Center: </label><s:select  id="costCenterName"  name="project.costCenterName" cssClass="form-control SelectBoxStyles "  list="%{costCenterNames}" value="%{project.costCenterName}" cssStyle="margin-right:50px"/>
                                                    </div>
                                                </s:if> 
                                            </div>    


                                            <div class="inner-addtaskdiv-elements " >
                                                <label class="projectLabelStyle">Description: </label><s:textarea  cssClass="form-control" name="project.project_description" id="project_descriptions" placeholder="%{project.project_description}" onkeydown="checkCharProjects(this);" onblur="removeMsg()"/>
                                            </div>
                                            <div class="charNum" id="Projects"></div>
                                            <div class="inner-addtaskdiv-elements">
                                                <div style="text-align: right">
                                                    <s:submit cssClass="cssbutton" value="Update Project" theme="simple" onclick="return updateProjectValidation();"/>
                                                </div>
                                            </div>
                                        </s:form>

                                    </div>
                                    <div>
                                        <ul class="nav nav-tabs">
                                            <s:if test="project.projectType=='Main Project'">
                                                <li class="active">
                                                    <a data-toggle="tab" href="#subProjects" onclick="pagerOption()">Sub Projects</a>
                                                </li>
                                                <li>
                                                    <a data-toggle="tab" href="#projectTeam" onclick="PagerOption1()">Project Team</a>

                                                </li>
                                            </s:if>
                                        </ul>
                                        <div class="tab-content">
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
    <footer id="footer"><!--Footer-->
        <div class="footer-bottom" id="footer_bottom">
            <div class="container">
                <s:include value="/includes/template/footer.jsp"/>
            </div>
        </div>
    </footer><!--/Footer-->
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
