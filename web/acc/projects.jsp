<!--
    Author     : Riza Erbas
-->
<div>
    <%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
    <%@ page import="java.util.List" isErrorPage="true"%>
    <%@ page import="com.mss.msp.util.ApplicationConstants"%>
    <%--<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
    <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
    <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/accountDetails/projects.css"/>">
    <%-- aklakh js single file start --%>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
    <%-- aklakh js single file end --%>
    <%-- aklakh css single file start --%>
    <%-- <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
    <%-- aklakh css single file end --%>
    <%-- for date picket start--%>
    <%--<script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

    <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
    <script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
    <%-- for date picket end--%>
    <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
    <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
    <script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
    <script>
        var pager;   //this pagination for Project Search
        $(document).ready(function(){

            var paginationSize = 10; // parseInt(document.getElementById("vpaginationOption").value);
            pager = new Pager('projectResults', paginationSize);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            // document.getElementById("paginationOption").value=10;
            pager.showPage(1);
        });
        function pagerOption(){

            paginationSize = document.getElementById("paginationOption").value;
            if(isNaN(paginationSize))
                alert(paginationSize);

            pager = new Pager('projectResults', parseInt(paginationSize));
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);

        };
    </script>

    <script>
        //           Pagination Script
        var pager;
        var projName;

        <%--  function pagerOption(){
              paginationSize = document.getElementById("paginationOption").value;
              pager = new Pager('projectResults', parseInt(paginationSize));
              pager.init();
              pager.showPageNav('pager', 'pageNavPosition');
              pager.showPage(1);
  }; --%>
      //            End Pagination Script

      var myCalendar;
      function doOnLoad() {
          myCalendar = new dhtmlXCalendarObject(["projectStartDateOverlay","projectTargetDateOverlay"]);
          myCalendar2 = new dhtmlXCalendarObject(["projectStartDate","projectTargetDate"]);
          myCalendar.setSkin('omega');
          myCalendar2.setSkin('omega');
          myCalendar.setDateFormat("%m-%d-%Y");
          myCalendar2.setDateFormat("%m-%d-%Y");
          myCalendar.hideTime();
          myCalendar2.hideTime();
          document.getElementById("projectStartDateOverlay").value=overlayDate;
          document.getElementById("projectTargetDateOverlay").value=overlayDate;
          document.getElementById("projectStartDate").value=overlayDate;
          document.getElementById("projectTargetDate").value=overlayDate;
      };
            
      function checkAddProjectName(projName){
               
          $.ajax({url:"<%=request.getContextPath()%>/checkProjectNames.action?projectName="+ projName,
              success: function(data){
                   
                  if(data == "true"){
                      $("#addProjectValidation").html("<font color='red'><b>Project name exists!</b></font>");
                      //$("#projectNameError").html("Project name exists!");
                      //document.getElementById("projectNameError").style.display = "block";
                      //$("#addProjectButton").prop("disabled",true);
                      document.getElementById("projectNamePopup").value="";
                      $("#projectNamePopup").focus();
                  }
                    
                  else{
                      $("#addProjectValidation").html("");
                      // $("#projectNamePopup").css('border','1px solid green');
                      // $("#projectNameError").html("Project name is valid.");
                      // document.getElementById("projectNameError").style.display = "none";
                      // document.getElementById("addProjectButton").disable = false;
                      // $("#addProjectButton").prop("disabled",false);
                  }
                    
              },
              type: 'GET'
          });
      };
      function searchProjects(){
               
          $.ajax({url:"<%=request.getContextPath()%>/projectsSearch.action"
                  +"?projectReqSkillSet=" + document.getElementById("projectReqSkillSet").value
                  +"&projectName=" + document.getElementById("projectName").value
                  +"&projectStartDate=" + document.getElementById("projectStartDate").value
                  +"&projectTargetDate=" + document.getElementById("projectTargetDate").value
                  +"&accountID=<s:property value='accountID'/>"
                  +"&projectType="+"MP"
              ,
              success: function(data){
                  window.setTimeout("pagerOption();", 1000);
                  window.setTimeout("doOnLoad();", 1000);
                  $("#projects").html(data);
              },
              type: 'GET'
          });
      };
      function addProject(){
          projName = document.getElementById("projectNamePopup").value;
          $.ajax({url:"<%=request.getContextPath()%>/addProject.action"
                  +"?projectName=" + document.getElementById("projectNamePopup").value
                  +"&project_status=" + document.getElementById("project_statusPopup").value
                  +"&projectStartDate=" + document.getElementById("projectStartDateOverlay").value
                  +"&projectTargetDate=" + document.getElementById("projectTargetDateOverlay").value
                  +"&projectReqSkillSet=" + document.getElementById("projectReqSkillSetPopup").value
                  +"&project_description=" + document.getElementById("project_descriptionPopup").value
                  +"&accountID=<s:property value="accountID"/>"
              ,
              success: function(data){
                  window.setTimeout("pagerOption();", 1000);
                  window.setTimeout("doOnLoad();", 1000);
                  //$("#addProjectValidation").html("Project inserted successfully.");
                  document.getElementById("projectsPopupCloseButton").click();
                  $("#projects").html(data);
              },
              type: 'GET'
          });
      };
      function resetOverlayForm(){
          document.getElementById("overlayForm").reset();
          $("#projectNameError").html("Project name is valid.");
          document.getElementById("projectNameError").style.display = "none";
      };
       
    </script>

    <div>
        <div id="clickHere"></div>
        <div id="addProject_popup" class="overlay">
            <div id="addProjectOverlay">

                <div style="background-color: #3BB9FF ">
                    <table>
                        <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Project&nbsp;&nbsp; </font></h4></td>
                        <span class=" pull-right"><h5><a href="" class="addProject_popup_close" onclick="removeResults()"><img id="projectsPopupCloseButton"src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                    </table>
                </div>
                <div style="width:fit-content">
                    <s:form action="" id="overlayForm" theme="simple" namespace="/">
                        <s:hidden  id="project_statusPopup"  name="project_status" value="Active"/>
                        <s:hidden id="projType" value="MP"/>
                        <span id="addProjectValidation"></span>
                        <div>
                            <div class="inner-addtaskdiv-elements">
                                <div class="col-sm-4 required">
                                    <label>Project Name</label><s:textfield  cssClass="form-control" id="projectNamePopup" type="text" name="projectName" placeholder="Project Name"  cssStyle="height: 25px;" onchange="checkAddProjectName(this.value);" maxlength="30"/>
                                </div>
                                <%--div class="col-sm-3 required">
                                    <label>Status</label><s:select  id="project_statusPopup"  name="project_status" cssClass="SelectBoxStyles form-control "  theme="simple" list="{'Active','Inactive','Completed','Paused'}" cssStyle="height: 25px"/>
                                </div--%>

                                <div class="col-sm-4 required">
                                    <label>Start Date</label><s:textfield cssClass="form-control" name="projectStartDate" id="projectStartDateOverlay" placeholder="Start Date" value="%{projectStartDate}" cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeypress="return projectDateValidation()" autocomplete="off"/>
                                </div>
                                <div class="col-sm-4 required"> 
                                    <label>Target Date</label><s:textfield cssClass="form-control" name="projectTargetDate" value="%{projectTargetDate}" id="projectTargetDateOverlay" placeholder="Target Date" cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;"  onkeypress="return projectDateValidation();" autocomplete="off"/>
                                </div>
                                <div>
                                    <label class="labelStyle" style="display: none; color: red; width: auto;margin-left: 20px" id="projectNameError"></label>
                                </div>

                                <div class="col-sm-4 required">
                                    <label>Target hours</label>
                                    <div class="form-group input-group">
                                        <s:textfield cssClass="form-control "  id="projectTargetHrs"  value="" name="projectTargetHrs"  onkeypress="return noOfHoursValidate(event, this.id)"/>
                                        <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <label>Worked hours</label>
                                    <div class="form-group input-group">
                                        <s:textfield cssClass="form-control " id="projectWorkedHrs"   name="projectWorkedHrs" placeholder="" readonly="true" value="0.0" />
                                        <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                    </div>
                                </div>                       
                                <div class="col-sm-4 required "> 
                                    <label>Cost Center</label><s:select  id="costCenterName"  name="costCenterName" cssClass="form-control SelectBoxStyles "  list="%{costCenterNames}"  headerValue="---Select---" headerKey="DF" cssStyle="margin-right:50px"/>
                                </div>
                                <div class="col-md-10"></div>
                                <div class="inner-addtaskdiv-elements">
                                    <label class="labelStyle popupLabelStyle" style="width: 150px">Required Skill Set</label><s:textarea  cssClass="areacss" id="projectReqSkillSetPopup" type="text" name="projectReqSkillSet" placeholder="Required Skill Set" value="%{projectReqSkillSet}" onkeydown="checkCharactersSkill(this)" onblur="removeMsg()"/>
                                </div>
                                <div class="charNum" id="charNumSkill"></div>
                                <div class="inner-addtaskdiv-elements">
                                    <label class="labelStyle">Description</label><s:textarea id="project_descriptionPopup" name="project_description" placeholder="Enter Task Description Here" cssClass="areacss" value="%{project_description}" onkeydown="checkCharactersProjects(this)" onblur="removeMsg()"/>
                                </div>
                                <div class="charNum" id="charNumProjects"></div>
                            </div>
                        </div>
                    </s:form>
                    <div  class="inner-addtaskdiv-elements" style="text-align: right">
                        <s:reset cssClass="cssbutton " value="Clear" theme="simple" onclick="resetOverlayForm();"/>
                        <s:submit id="addProjectButton" cssClass="cssbutton" value="Add Project" theme="simple" onclick="addProjectValidation();addProject();"/>
                    </div>
                </div>
            </div>
        </div>
        <div id="projectSkillOverlay_popup">
            <div id="projectSkillSetBox" class="marginTasks">
                <div class="backgroundcolor">
                    <table>
                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill Details&nbsp;&nbsp; </font></h4></td>
                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="projectSkillOverlay_popup_close" onclick="reqSkillSetOverlayClose()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                    </table>
                </div>
                <div>

                    <s:textarea name="skillSetDetails"   id="reqSkillSetDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                </div>
                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
            </div>

            <%--close of future_items--%>
        </div>
        <div id="projectsDescOverlay_popup">
            <div id="projectDescBox" class="marginTasks">
                <div class="backgroundcolor">
                    <table>
                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Project Description&nbsp;&nbsp; </font></h4></td>
                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="projectsDescOverlay_popup_close" onclick="projectDescriptinOverlayClose()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                    </table>
                </div>
                <div>

                    <s:textarea name="projectDescription"   id="projectDescription"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                </div>
                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
            </div>

            <%--close of future_items--%>
        </div>

        <section id=""><!--form-->
            <div class="container" style="overflow: auto;">
                <div class="row">

                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class=" " >
                            <div class=" " >
                                <div class="" id="profileBox" style="margin-top: 5px; width: 90%">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Projects</font>
                                                <i id="updownArrow" onclick="toggleContent('projectSearchForm')" class="fa fa-angle-up"></i>

                                            </h4>
                                        </div>

                                    </div>
                                    <!-- content start -->
                                    <label class="labelStyle" style="color: green; width: auto;margin-left: 20px" id="projectAddResult"><s:property value="projectsActionResponse"/></label>

                                    <div class="col-sm-12" id="profileBox" style="margin-top: -20px">
                                        <div >
                                            <div id="projectSearchForm">
                                                <div class="col-sm-12">
                                                    <s:form action="" theme="simple" method="GET" target="%{projects}">
                                                        <br>
                                                        <span><searchProject></searchProject></span>
                                                        <div class="inner-addtaskdiv-elements" style="margin-left: -30px">
                                                            <div class="col-sm-3">
                                                                <label >Skill Set: </label>
                                                                <s:textfield cssClass="form-control " label="projectReqSkillSet" id="projectReqSkillSet" type="text" name="projectReqSkillSet"  placeholder="Skill Set" maxLength="100"/>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <label >Project Name: </label>
                                                                <s:textfield  cssClass="form-control " label="projectName" id="projectName" type="text" name="projectName" placeholder="Project Name" maxLength="30"/>
                                                            </div>

                                                            <div class="col-sm-3">
                                                                <label >Start Date: </label>
                                                                <s:textfield cssClass="form-control " id="projectStartDate" type="text" name="projectStartDate" placeholder="Project Start Date"  cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeypress=" return projectDateRepository();" autocomplete="off"/>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <label >Target Date: </label>
                                                                <s:textfield cssClass="form-control " id="projectTargetDate" type="text" name="projectTargetDate" placeholder="Project Target Date"  cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeypress=" return projectDateRepository();" autocomplete="off"/>
                                                            </div>


                                                            
                                                            </div>
                                                        </div>


                                                </s:form>


                                            </div>
                                            <br>
<div class="col-lg-8"></div>
                                                            <div class="col-lg-2 ">   
                                                                <s:submit type="button" cssStyle="margin:5px 0px" cssClass="add_searchButton form-control "  onclick="searchProjects();" ><i class="fa fa-search" ></i>&nbsp;Search</s:submit>
                                                                </div>
                                                                <div class="col-lg-2">
                                                                    <a href="" class="addProject_popup_open" ><button style="margin:5px 0px" class="add_searchButton form-control" ><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>
                                                                </div>
                                            <div class="col-sm-12">
                                                <s:form id="projectResultsForm">
                                                    <div style="width: fit-content">
                                                        <div class="emp_Content" id="emp_div" align="center" style="width:auto;margin: auto" >
                                                            <table id="projectResults" class="responsive CSSTable_task" border="5" style="width: 100%;margin: auto" >
                                                                <br>
                                                                <tr>
                                                                    <th>Project Name</th>

                                                                    <th>Required Skill-Set</th>
                                                                    <th>Description</th>
                                                                    <th>Start Date</th>
                                                                    <th>Target Date</th>
                                                                    <th>Status</th>
                                                                </tr>
                                                                <s:if test="searchDetails ==null || searchDetails.size() ==0">
                                                                    <tr>
                                                                        <td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:iterator  value="searchDetails">
                                                                    <tr>

                                                                        <s:url action="projectDetails.action" var="getDetails">
                                                                            <s:param name="projectID"><s:property value="projectID"/></s:param>
                                                                            <s:param name="accountID"><s:property value="accountID"/></s:param>
                                                                        </s:url>
                                                                        <td><s:a href="%{getDetails}"><s:property value="projectName"/></s:a></td>
                                                                        <s:if test="%{projectReqSkillSet.length()>10}">
                                                                            <%-- <td><s:property value="projectReqSkillSet"/></td>--%>
                                                                            <td><s:a href="#" cssClass="projectSkillOverlay_popup_open" onclick="reqSkillSetOverlay('%{projectReqSkillSet}')"><s:property value="projectReqSkillSet.substring(0,10)"/></s:a></td>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <td> <s:a href="#" cssClass="projectSkillOverlay_popup_open" onclick="reqSkillSetOverlay('%{projectReqSkillSet}')"><s:property value="projectReqSkillSet"/></s:a></td>
                                                                        </s:else>
                                                                        <%-- <td><s:property value="project_description"/></td>--%>
                                                                        <s:if test="%{project_description.length()>10}">
                                                                            <td><s:a href="#" cssClass="projectsDescOverlay_popup_open" onclick="projectDescriptinOverlay('%{project_description}')"><s:property value="project_description.substring(0,10)"/></s:a></td>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <td><s:a href="#" cssClass="projectsDescOverlay_popup_open" onclick="projectDescriptinOverlay('%{project_description}')"><s:property value="project_description"/></s:a></td>    
                                                                        </s:else>
                                                                        <td><s:property value="projectStartDate"/></td>
                                                                        <td><s:property value="projectTargetDate"/></td>
                                                                        <td><s:property value="project_status"/></td>
                                                                    </tr>

                                                                </s:iterator>

                                                            </table>
                                                            <br/>
                                                            <label> Display:
                                                                <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption();" style="width: auto">
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                </select>&nbsp;Projects per page
                                                            </label>
                                                            <div id="loadingSearchProject" class="loadingImg" style="display: none">
                                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                                            </div>
                                                            <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                        </div>
                                                    </div>
                                                </s:form>

                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <%--close of future_items--%>
                </div>
            </div>

        </section>
    </div>

    <%--close of future_items--%>
</div>
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
                 $('#projectResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
        $('#projectResults').tablePaginate({navigateType:'navigator'},recordPage);
       </script>
<script type="text/javascript">
    window.setTimeout("pagerOption();", 1000);
    window.setTimeout("doOnLoad();", 1000);
    setupAddProjectOverlay();
	// new code.
</script>