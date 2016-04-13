<%@page import="javax.sound.midi.Soundbank"%>
<!--
    Author     : Riza
-->
<%@page import="com.mss.msp.acc.projectsdata.ProjectsVTO"%>
<%@page import="com.mss.msp.acc.projectsdata.ProjectsDataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<!DOCTYPE html>

<!-- new styles -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ServicesBay :: Sub&nbsp;Projects&nbsp;Page</title>
<script>
    var subPager;
    function onLoad() {
    }
    ;

</script>
<script>
    function searchSubProjects() {
        initSessionTimer();
        var searchStartDate = document.getElementById("projectStartDateSearch").value;
        var searchTargetDate = document.getElementById("projectTargetDateSearch").value;
        var splitStartDate = searchStartDate.split('-');
        var startDate = new Date(splitStartDate[2], splitStartDate[0] - 1, splitStartDate[1]);
        var splitTargetDate = searchTargetDate.split('-');
        var targetDate = new Date(splitTargetDate[2], splitTargetDate[0] - 1, splitTargetDate[1]);
        var StartDateSearchProject = Date.parse(startDate);
        var endDateSearchProject = Date.parse(targetDate);
        var difference = (endDateSearchProject - StartDateSearchProject) / (86400000 * 7);
        if ((startDate == "Invalid Date") && (targetDate == "Invalid Date")) {
            difference = 1;
        }
        else if ((startDate == "Invalid Date") && (targetDate != "Invalid Date")) {
            difference = -1;
        }
        else if ((startDate != "Invalid Date") && (targetDate == "Invalid Date")) {
            difference = -2;
        }
        if (difference > 0) {
            document.getElementById('loadingSubProject').style.display = 'block';

            $.ajax({
                url: "<%=request.getContextPath()%>/subProjectsSearch.action"
                        + "?projectReqSkillSet=" + document.getElementById("projectReqSkillSet").value
                        + "&projectName=" + document.getElementById("projectName").value
                        + "&projectStartDate=" + document.getElementById("projectStartDateSearch").value
                        + "&projectTargetDate=" + document.getElementById("projectTargetDateSearch").value
                        + "&parentProjectID=" + document.getElementById("textParentProjectID").value
                        + "&accountID=" + document.getElementById("accountID").value
                        + "&projectType=" + "SP"
                ,
                success: function(data) {
                    $('#loadingSubProject').hide();
                    window.setTimeout("pagerOption();", 1000);
                    window.setTimeout("doOnLoad();", 1000);
                    setupAddSubProjectOverlay();
                    $("#subProjects").html(data);

                },
                type: 'GET'

            });
        }
        else {
            if (difference == -1) {
                $("searchSubProject").html(" <b><font color='red'>Select start date!</font></b>");
                $("#projectStartDateSearch").css("border", "1px solid red");
            }
            else if (difference == -2) {
                $("searchSubProject").html(" <b><font color='red'>Select target date!</font></b>");
                $("#projectTargetDateSearch").css("border", "1px solid red");
            } else {
                $("searchSubProject").html(" <b><font color='red'>Start date must be less than target date!</font></b>");
                $("#projectStartDateSearch").css("border", "1px solid red");
                $("#projectTargetDateSearch").css("border", "1px solid red");
            }
            return false;

        }
    }
    ;


    function checkSubProjectName(projName) {
        if (projName.replace(/\s/g, '') == "")
        {
            $("#addProjectValidation").html(" <b><font color='red'>Enter Project name</font></b>");
            return false;
        }
        var mainProjectId = $("#parentProjectId").val();
        $.ajax({
            url: "<%=request.getContextPath()%>/checkProjectNames.action?projectName=" + projName + "&mainProjectId=" + mainProjectId + "&projectFlag=subprojects",
            success: function(data) {

                if (data == "true") {
                    $("#addProjectValidation").html("<font color='red'><b>Project name exists!</b></font>");
                    document.getElementById("projectNamePopup").value = "";
                    $("#projectNamePopup").focus();
                }

                else {
                    $("#addProjectValidation").html("");
                }

            },
            type: 'GET'
        });
    }
    ;
</script>
<script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>



<div id="resourceOverlay_popup" class="overlay" >
    <div id="resourceOverlay" >

        <div class="overlayOrButton_color">
            <table>
                <tr><td style=""><h4>
                            <font color="#ffffff">&nbsp;Resources&nbsp;Of&nbsp;Sub-Project&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</font>

                        </h4></td>
                <span class=" pull-right"><h5><a href="" id="resOverlayCloseButton" class="resourceOverlay_popup_close" onclick="showResourceTeamOverlayClose();
        pagerOption();"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
            </table>
        </div>
        <div style="margin: 10px">
            <table id="resourceTable" class="CSSTable_task  responsive" border="2"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;" >
                <tr>
                    <th>Resource&nbsp;Name</th>
                    <th>Designation</th>
                </tr>
            </table > 

            <label> Display:
                <select id="paginationOption_res" class="disPlayRecordsCss" onchange="pagerOption_resource();" style="width: auto">
                    <option>5</option>
                    <option>10</option>
                    <option>15</option>
                </select>&nbsp;Sub-Projects per page
            </label>


        </div>
    </div>
</div>


<div id="addSubProject_popup" class="overlay" style="width:75%">
    <div id="addSubProjectOverlay" >

        <div class="backgroundcolor" >
            <table>
                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add&nbsp;Sub-Project&nbsp;&nbsp; </font></h4></td>
                <span class=" pull-right"><h5><a href="" id="addSubPjctCloseButton" class="addSubProject_popup_close" onclick="removeResults();"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
            </table>
        </div>
        <div style="width:fit-content">
            <form action="addSubProject" theme="simple" id="overlayForm" >
                <s:hidden  id="project_statusPopup"  name="project_status" value="Active" />
                <s:hidden id="projType" value="SP"/>
                <s:hidden id="parentProjectId" value="%{projectID}"/>
                <span id="addProjectValidation"></span>
                <div>
                    <div class="inner-addtaskdiv-elements">
                        <div>
                            <label>Parent&nbsp;Project&nbsp;Name:&nbsp;</label><span style="color:  #FF8A14;"><s:property value="parentProjectName"></s:property></span>
                            </div>
                            <div class="col-sm-4 required">  
                                <label>Name</label><s:textfield  cssClass="form-control" id="projectNamePopup" type="text" name="projectName" placeholder="Project Name" onchange="checkSubProjectName(this.value);" maxlength="30" onfocus="clearSubProjectFieldValues()"/>
                        </div>

                        <div class="col-sm-4 required"> 
                            <label>Start&nbsp;Date</label>
                            <div class="calImage"><s:textfield cssClass="form-control" name="projectStartDate" id="projectStartDateOverlay" placeholder="Start Date" value="%{projectStartDate}"  onkeyup="return projectDateValidation()" autocomplete="off" onfocus="clearSubProjectFieldValues()"><i class="fa fa-calendar"></i></s:textfield>
                                </div></div>
                            <div class="col-sm-4 required">  
                                <label>Target&nbsp;Date</label>
                                <div class="calImage"><s:textfield cssClass="form-control" name="projectTargetDate" value="%{projectTargetDate}" id="projectTargetDateOverlay" placeholder="Target Date"  onkeyup="return projectDateValidation()" autocomplete="off" onfocus="clearSubProjectFieldValues()"><i class="fa fa-calendar"></i></s:textfield>
                                </div>
                            </div>
                        </div>  
                        <div class="col-lg-10"></div>
                        <div class="inner-addtaskdiv-elements"> 
                            <div class="col-sm-4  required">
                                <label>Target&nbsp;hours</label>
                                <div class="form-group input-group">
                                <s:textfield cssClass="form-control "  id="projectTargetHrs" placeholder="Target hours" value="" name="projectTargetHrs"  onkeypress="return noOfHoursValidate(event, this.id)" onblur="calculateTargetHrs()" onfocus="clearSubProjectFieldValues()"/>
                                <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                            </div>
                        </div>
                        <div class="col-sm-4  ">
                            <label>Worked&nbsp;hours</label>
                            <div class="form-group input-group">
                                <s:textfield cssClass="form-control " id="projectWorkedHrs"  value="0.0" name="projectWorkedHrs" placeholder="" readonly="true" onfocus="clearSubProjectFieldValues()"/>
                                <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                            </div>
                        </div>
                    </div> 
                    <div class="inner-addtaskdiv-elements">
                        <div class="col-sm-12">
                            <span><hours></hours></span>
                        </div>
                    </div>
                    <div class="inner-addtaskdiv-elements">
                        <div class="col-sm-12">
                            <label class="labelStyle">Skill&nbsp;Set</label>
                        </div>
                        <div class="inner-addtaskdiv-elements">

                            <s:textarea  cssClass="areacss" id="projectReqSkillSetPopup" type="text" name="projectReqSkillSet" placeholder="Required Skill Set" onkeydown="checkCharactersSkill(this)" onblur="removeMsg()" onfocus="clearSubProjectFieldValues()"/>
                        </div>
                        <div class="charNum" id="charNumSkill"></div>
                        <div class="inner-addtaskdiv-elements">
                            <label class="labelStyle">Description</label><s:textarea name="project_description" placeholder="Enter Task Description Here" id="project_descriptionPopup" cssClass="areacss" onkeydown="checkCharactersProjects(this)" onblur="removeMsg()" onfocus="clearSubProjectFieldValues()"/>
                        </div>
                        <div class="charNum" id="charNumProjects"></div>
                        <div  class="inner-addtaskdiv-elements" style="text-align: right">
                            <s:reset cssClass="add_searchButton fa fa-eraser"  style="width:105px;height:30px;" type="button"  id="subPjctClearButton" value="Clear" theme="simple" onclick="resetOverlayForm();"/>
                            <s:submit id="addSubProjectButton" cssClass="add_searchButton fa fa-floppy-o" type="button" style="width:105px;height:30px;" value="Save" theme="simple" onclick="return addProjectValidation();"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="subprojectSkillOverlay_popup">
    <div id="subprojectSkillSetBox" class="marginTasks">
        <div class="backgroundcolor">
            <table>
                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill&nbsp;Details&nbsp;&nbsp; </font></h4></td>
                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="skillDetailsCloseButton" class="subprojectSkillOverlay_popup_close" onclick="subprojectSkillSetOverlayClose()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
            </table>
        </div>
        <div>

            <s:textarea name="subprojectskillSetDetails"   id="subprojectSkillSetDetails"   disabled="true" cssClass="form-control"/> 


        </div>
        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
    </div>


</div>
<div id="subprojectsDescOverlay_popup">
    <div id="subprojectDescBox" class="marginTasks">
        <div class="backgroundcolor">
            <table>
                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Project Description&nbsp;&nbsp; </font></h4></td>
                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="subPjctDescCloseButton" class="subprojectsDescOverlay_popup_close" onclick="subprojectDescriptionOverlayClose()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
            </table>
        </div>
        <div>

            <s:textarea name="subprojectDescription"   id="subprojectDescription"   disabled="true" cssClass="form-control"/> 


        </div>
        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
    </div>


</div>                    
<section id="generalForm" style="margin-bottom: 1px"><!--form-->



    <!-- content start -->


    <div class="features_items col-sm-12" style="background-color:#fff">

        <div class="backgroundcolor" >
            <div class="panel-heading">
                <h4 class="panel-title">
                    <font color="#ffffff">Sub&nbsp;Projects</font>
                    <i id="updownArrow" onclick="toggleContent('subProjectsForm')" class="fa fa-minus"></i>

                </h4>
            </div>

        </div>
        <!-- content start -->

        <label class="labelStyle" style="color: green; width: auto;margin-left: 20px" id="projectAddResult"><s:property value="projectsActionResponse"/></label>

        <div class="col-sm-12" id="profileBox" style="margin-top: -20px">
            <span><searchSubProject></searchSubProject></span>
            <div class="row">
                <div id="subProjectsForm">

                    <div class="col-sm-3">
                        <label >Skill&nbsp;Set </label>
                        <s:textfield cssClass="form-control" id="projectReqSkillSet" type="text"  placeholder="Skill Set" maxLength="100" tabindex="1"/>

                    </div>
                    <div class="col-sm-3">
                        <label >Project&nbsp;Name </label>
                        <s:textfield  cssClass="form-control" id="projectName" type="text" placeholder="Project Name" maxLength="30" tabindex="1"/>
                    </div>
                    <div class="col-sm-3">
                        <label >Start&nbsp;Date </label>
                        <div class="calImage"> <s:textfield cssClass="form-control" id="projectStartDateSearch" type="text" placeholder="Project Start Date"  onkeypress=" return subProjectDaterepository();" autocomplete="off" tabindex="3"><i  class="fa fa-calendar"></i></s:textfield>
                            </div></div>
                        <div class="col-sm-3">
                            <label >Target&nbsp;Date </label>
                            <div class="calImage"><s:textfield cssClass="form-control" id="projectTargetDateSearch" type="text" placeholder="Project Target Date"  onkeypress=" return subProjectDaterepository();" autocomplete="off" tabindex="4"><i  class="fa fa-calendar"></i></s:textfield>
                            </div></div>



                        <div class="col-sm-12" style="margin-bottom: 5px">
                            <div class="col-sm-2 pull-right contact_search">
                            <s:submit type="button" cssClass="add_searchButton form-control" value="" onclick="searchSubProjects()" id="subPjctSearchButton" align="right" cssStyle="margin:5px" tabindex="6"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                            </div>
                            <div class="col-sm-2 pull-right contact_search" id="addSubPro">
                                <a href="" class="addSubProject_popup_open" id="subPjctAddButton"><button class="add_searchButton form-control" style="margin:5px" onclick="clearSubprojectOverlay()" tabindex="5"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>
                            </div>
                        </div> 
                    </div>
                </div>
                <div class="row">
                <s:form>
                    <div style="width: fit-content">
                        <s:hidden name="remainingTargetHrs" id="remainingTargetHrs" value=""/>
                        <div class="emp_Content" id="emp_div" align="center" style="width:auto;margin: auto" >
                            <table id="projectResults" class="responsive CSSTable_task" border="5"  >
                                <script>
                                    var total = 0;
                                </script>
                                <s:hidden name="totalTargetHrs" id="totalTargetHrs" value=""/>
                                <br>
                                <tr>
                                    <th>Name</th>
                                        <%-- <th>Project Type</th>--%>
                                    <th>Skill-Set</th>
                                    <th>No.Of.Resources</th>
                                    <th>Description</th>
                                    <th>Start&nbsp;Date</th>
                                    <th>Target&nbsp;Date</th>
                                    <th>Target&nbsp;Hrs</th>
                                    <th>Status</th>
                                </tr>
                                <s:if test="searchDetails ==null || searchDetails.size() == 0">
                                    <tr>
                                        <td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                    </tr>
                                </s:if>
                                <s:set name="parentPid" scope="request" value="projectID"/>
                                <s:iterator  value="searchDetails">

                                    <tr>
                                        <s:hidden name="ppid" value="%{parentProjectID}" id="ppid"/>
                                        <td><s:a href="#" style="cursor:pointer;" onclick="this.href='projectDetails.action?projectID='+%{projectID}+'&accountID='+%{accountID}+'&mainProjectStartDate='+document.getElementById('projectStartDate').value+'&mainProjectTargetDate='+document.getElementById('projectTargetDate').value+'&remainingTargetHrs='+document.getElementById('remainingTargetHrs').value+'&mainProjectStatus='+document.getElementById('project.project_status').value"><s:property value="projectName"/></s:a></td>
                                        <s:if test="%{projectReqSkillSet.length()>10}">
                                            <td><s:a href="#" cssClass="subprojectSkillOverlay_popup_open" onclick="subprojectSkillSetOverlay('%{projectReqSkillSet}')"><s:property value="projectReqSkillSet.substring(0,10)"/></s:a></td>
                                        </s:if>
                                        <s:else>
                                            <td> <s:a href="#" cssClass="subprojectSkillOverlay_popup_open" onclick="subprojectSkillSetOverlay('%{projectReqSkillSet}')"><s:property value="projectReqSkillSet"/></s:a></td>
                                        </s:else>
                                        <%
                                            int noOfResoudeById = 0;
                                            System.out.println("in jsp we print:===" + noOfResoudeById);
                                        %>
                                        <s:set name="pid" value="projectID"  scope="request"/>
                                        <%
                                            int pid = Integer.parseInt(request.getAttribute("pid").toString());
                                            System.out.println("this is jsp pid value===========" + pid);
                                            noOfResoudeById = com.mss.msp.util.DataSourceDataProvider.getInstance().getNoOfResourcesInProject(pid, "SP");
                                        %>
                                        <td><center><s:a href="#" cssClass="resourceOverlay_popup_open" onclick="showResourceTeam(%{projectID});pagerOption_resource()"><% out.print("" + noOfResoudeById);%></s:a></center></td>
                                        <s:if test="%{project_description.length()>10}">
                                        <td><s:a href="#" cssClass="subprojectsDescOverlay_popup_open" onclick="subprojectDescriptionOverlay('%{project_description}')"><s:property value="project_description.substring(0,10)"/></s:a></td>
                                    </s:if>
                                    <s:else>
                                        <td><s:a href="#" cssClass="subprojectsDescOverlay_popup_open" onclick="subprojectDescriptionOverlay('%{project_description}')"><s:property value="project_description"/></s:a></td>    
                                    </s:else>
                                    <td><s:property value="projectStartDate"/></td>
                                    <td><s:property value="projectTargetDate"/></td>
                                    <td><s:property value="projectTargetHrs"/></td>
                                    <script>
                                        var t = parseFloat('<s:property value="projectTargetHrs"/>');
                                        total = total + t;
                                        document.getElementById("totalTargetHrs").value = total;
                                    </script>

                                    <td><s:property value="project_status"/></td>
                                    </tr>
                                </s:iterator>

                            </table> 
                            <br/>
                            <s:if test=" searchDetails.size() > 0">
                                <label> Display:
                                    <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption();" style="width: auto">
                                        <option>5</option>
                                        <option>10</option>
                                        <option>15</option>
                                    </select>&nbsp;Sub-Projects per page
                                </label>
                            </s:if>
                            <div id="loadingSubProject" class="loadingImg" style="display: none">
                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                            </div>
                            <div align="right" class="pull-right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                        </div>
                    </div>
                </s:form>

            </div>

        </div>



    </div>


    <%--close of future_items--%>



</section>


<script>
    window.setTimeout("pagerOption();", 1000);
    window.setTimeout("doOnLoad();", 1000);
    setupAddSubProjectOverlay();
</script>

<script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

<script type="text/javascript">
    var recordPage = 10;
    function pagerOption() {
        var paginationSize = document.getElementById("paginationOption").value;
        if (isNaN(paginationSize))
        {

        }
        recordPage = paginationSize;
        $('#projectResults').tablePaginate({navigateType: 'navigator'}, recordPage);

    }
    ;
    $('#projectResults').tablePaginate({navigateType: 'navigator'}, recordPage);

</script>
<script>
    var recordPage = 10;
    function pagerOption_resource() {

        var paginationSize = document.getElementById("paginationOption_res").value;
        if (isNaN(paginationSize))
        {

        }
        recordPage = paginationSize;
        $('#resourceTable').tablePaginate({navigateType: 'navigator'}, recordPage);

    }
    ;
    $('#resourceTable').tablePaginate({navigateType: 'navigator'}, recordPage);
</script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>