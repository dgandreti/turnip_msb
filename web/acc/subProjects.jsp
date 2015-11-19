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
<title>ServicesBay :: Sub Projects Page</title>
<script>
    var subPager;
    function onLoad(){
        //        //alert("onload")
        //        var paginationSize = parseInt(document.getElementById("paginationOption").value);
        //        // alert(paginationSize);
        //        subPager = new Pager('projectResults', paginationSize);
        //        subPager.init();
        //        subPager.showPageNav('subPager', 'pageNavPosition');
        //        subPager.showPage(1);
    };
    //    function pagerOption(){
    //
    //        paginationSize = document.getElementById("paginationOption").value;
    //        if(isNaN(paginationSize))
    //            alert(paginationSize);
    //
    //        subPager = new Pager('projectResults', parseInt(paginationSize));
    //        subPager.init();
    //        subPager.showPageNav('subPager', 'pageNavPosition');
    //        subPager.showPage(1);
    //
    //    };
</script>
<script>
    function searchSubProjects(){
         initSessionTimer();
        var searchStartDate=document.getElementById("projectStartDateSearch").value;
        var searchTargetDate=document.getElementById("projectTargetDateSearch").value;
        var splitStartDate = searchStartDate.split('-');
        var startDate = new Date(splitStartDate[2], splitStartDate[0]-1 , splitStartDate[1]);  
        var splitTargetDate = searchTargetDate.split('-');
        var targetDate = new Date(splitTargetDate[2], splitTargetDate[0]-1, splitTargetDate[1]); 
        var StartDateSearchProject = Date.parse(startDate);
        var endDateSearchProject= Date.parse(targetDate);
        var  difference=(endDateSearchProject - StartDateSearchProject) / (86400000 * 7);
        if((startDate=="Invalid Date")&&(targetDate=="Invalid Date")){
            difference = 1;
        }
        else if((startDate=="Invalid Date")&&(targetDate!="Invalid Date")){
            difference = -1;
        }
        else if((startDate!="Invalid Date")&&(targetDate=="Invalid Date")){
            difference = -2;
        }
        if(difference>0){
            document.getElementById('loadingSubProject').style.display = 'block';
                
            $.ajax({
                url:"<%=request.getContextPath()%>/subProjectsSearch.action"
                    +"?projectReqSkillSet=" + document.getElementById("projectReqSkillSet").value
                    +"&projectName=" + document.getElementById("projectName").value
                    +"&projectStartDate=" + document.getElementById("projectStartDateSearch").value
                    +"&projectTargetDate=" + document.getElementById("projectTargetDateSearch").value
                    +"&parentProjectID=" + document.getElementById("textParentProjectID").value
                    +"&accountID="+ document.getElementById("accountID").value
                    +"&projectType="+"SP"
                ,
                success: function(data){
                    $('#loadingSubProject').hide();
                    window.setTimeout("pagerOption();", 1000);
                    window.setTimeout("doOnLoad();", 1000);
                    //$(projectsPage).children().remove();
                    $("#subProjects").html(data);
                    setupAddSubProjectOverlay();
                },
                type: 'GET'
            
            });
        }
        else{
            if(difference==-1){
                $("searchSubProject").html(" <b><font color='red'>Select start date!</font></b>");
                $("#projectStartDateSearch").css("border", "1px solid red");
            }
            else if(difference==-2){
                $("searchSubProject").html(" <b><font color='red'>Select target date!</font></b>");
                $("#projectTargetDateSearch").css("border", "1px solid red");
            }else{
                $("searchSubProject").html(" <b><font color='red'>Start date must be less than target date!</font></b>");
                $("#projectStartDateSearch").css("border", "1px solid red");
                $("#projectTargetDateSearch").css("border", "1px solid red");
            }
            return false;
                    
        } 
    };
   

    function checkSubProjectName(projName){

        $.ajax({
            url:"<%=request.getContextPath()%>/checkProjectNames.action?projectName="+ projName,
            success: function(data){

                if(data == "true"){
                    $("#addProjectValidation").html("<font color='red'><b>Project name exists!</b></font>");
                    document.getElementById("projectNamePopup").value="";
                    $("#projectNamePopup").focus();
                    //$("#projectNameError").html("Project name exists!");
                    //document.getElementById("projectNameError").style.display = "block";
                    //$("#addSubProjectButton").prop("disabled",true);
                }

                else{
                    $("#addProjectValidation").html("");
                    //  $("#projectNamePopup").css('border','1px solid green');
                    //$("#projectNameError").html("Project name is valid.");
                    // document.getElementById("projectNameError").style.display = "none";
                    //$("#addSubProjectButton").prop("disabled",false);
                }

            },
            type: 'GET'
        });
    };
</script>
<script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

<div>
    <div id="resourceOverlay_popup" class="overlay">
        <div id="resourceOverlay" >

            <div style="background-color: #3BB9FF ">
                <table>
                    <tr><td style=""><h4>
                                <font color="#ffffff">&nbsp;Resources&nbsp;Of&nbsp;Sub-Project&nbsp;&nbsp; </font>

                            </h4></td>
                    <span class=" pull-right"><h5><a href="" class="resourceOverlay_popup_close" onclick="showResourceTeamOverlayClose()"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25" ></a>&nbsp;</h5></span>
                </table>
            </div>
            <div style="margin: 10px">
                <table id="resourceTable" class="CSSTable_task  responsive" border="2"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;" >
                    <tr>
                        <th>Resource&nbsp;Name</th>
                        <th>Designation</th>
                    </tr>
                </table > 
                <div id="edu_pageNavPosition" align="right" style="margin-right:0vw"></div>
                <div   style="width:auto;height:auto" >
                    <div  id="editSpan" class="badge pull-right" style="display:none"></div>                                                       
                </div>  
            </div>
        </div>
    </div>


    <div id="addSubProject_popup" class="overlay">
        <div id="addSubProjectOverlay" >

            <div style="background-color: #3BB9FF ">
                <table>
                    <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Sub-Project&nbsp;&nbsp; </font></h4></td>
                    <span class=" pull-right"><h5><a href="" class="addSubProject_popup_close" onclick="removeResults();"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25" ></a>&nbsp;</h5></span>
                </table>
            </div>
            <div style="width:fit-content">
                <form action="addSubProject" theme="simple" id="overlayForm" >
                    <s:hidden  id="project_statusPopup"  name="project_status" value="Active" />
                    <s:hidden id="projType" value="SP"/>
                    <span id="addProjectValidation"></span>
                    <!--<div><label>Project Name:</label> 

                    
                    </div>-->
                    <div>
                        <div class="inner-addtaskdiv-elements">
                            <div>
                                <label>Parent&nbsp;Project&nbsp;Name:&nbsp;</label><span style="color:  #FF8A14;"><s:property value="parentProjectName"></s:property></span>
                                </div>
                                <div class="col-sm-4 required">  
                                    <label>Name</label><s:textfield  cssClass="form-control" id="projectNamePopup" type="text" name="projectName" placeholder="Project Name" onchange="checkSubProjectName(this.value);" maxlength="30"/>
                            </div>
                            <%--div class="col-sm-3 required"> 
                                <label>Status</label><s:select  id="project_statusPopup"  name="project_status" cssClass="SelectBoxStyles form-control "  theme="simple" list="{'Active','Inactive','Completed','Paused'}" />
                        </div--%>
                            <div class="col-sm-4 required"> 
                                <label>Start Date</label><s:textfield cssClass="form-control" name="projectStartDate" id="projectStartDateOverlay" placeholder="Start Date" value="%{projectStartDate}" cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeyup="return projectDateValidation()" autocomplete="off"/>
                            </div>
                            <div class="col-sm-4 required">  
                                <label>Target Date</label><s:textfield cssClass="form-control" name="projectTargetDate" value="%{projectTargetDate}" id="projectTargetDateOverlay" placeholder="Target Date" cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeyup="return projectDateValidation()" autocomplete="off"/>

                            </div>
                        </div>  
                        <div class="col-lg-10"></div>
                        <div class="inner-addtaskdiv-elements"> 
                            <div class="col-sm-4  required">
                                <label>Target hours</label>
                                <div class="form-group input-group">
                                    <s:textfield cssClass="form-control "  id="projectTargetHrs" value="" name="projectTargetHrs"  onkeypress="return noOfHoursValidate(event, this.id)" onblur="calculateTargetHrs()" />
                                    <span class="input-group-addon" style="padding-top: 5px">Hrs</span>
                                </div>
                            </div>
                            <div class="col-sm-4  ">
                                <label>Worked hours</label>
                                <div class="form-group input-group">
                                    <s:textfield cssClass="form-control " id="projectWorkedHrs"  value="0.0" name="projectWorkedHrs" placeholder="" readonly="true" />
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
                            <div class="col-lg-10">
                                <label class="labelStyle">Skill Set</label>
                            </div>
                            <div class="inner-addtaskdiv-elements">

                                <s:textarea  cssClass="areacss" id="projectReqSkillSetPopup" type="text" name="projectReqSkillSet" placeholder="Required Skill Set" onkeydown="checkCharactersSkill(this)" onblur="removeMsg()"/>
                            </div>
                            <div class="charNum" id="charNumSkill"></div>
                            <div class="inner-addtaskdiv-elements">
                                <label class="labelStyle">Description</label><s:textarea name="project_description" placeholder="Enter Task Description Here" id="project_descriptionPopup" cssClass="areacss" onkeydown="checkCharactersProjects(this)" onblur="removeMsg()"/>
                            </div>
                            <div class="charNum" id="charNumProjects"></div>
                            <div  class="inner-addtaskdiv-elements" style="text-align: right">
                                <s:reset cssClass="cssbutton " value="Clear" theme="simple" onclick="resetOverlayForm();"/>
                                <s:submit id="addSubProjectButton" cssClass="cssbutton" value="Add Sub Project" theme="simple" onclick="return addProjectValidation();"/>
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
                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill Details&nbsp;&nbsp; </font></h4></td>
                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="subprojectSkillOverlay_popup_close" onclick="subprojectSkillSetOverlayClose()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
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
                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="subprojectsDescOverlay_popup_close" onclick="subprojectDescriptionOverlayClose()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                </table>
            </div>
            <div>

                <s:textarea name="subprojectDescription"   id="subprojectDescription"   disabled="true" cssClass="form-control"/> 


            </div>
            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
        </div>


    </div>                    
    <section id="generalForm" style="margin-bottom: 1px"><!--form-->
        <div class=" ">
            <div class=" ">

                <!-- content start -->

                <div class="col-md-13 col-md-offset-0" style="background-color:#fff">
                    <div class="features_items" >
                        <div class="col-lg-11 " >
                            <div class="" id="" style="margin-top: 5px; width: 100%">
                                <%-- <div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px">  Account&nbsp;Name:                                          
                                     <s:url var="myUrl" action="/acc/viewAccount.action">
                                         <s:param name="accountSearchID"><s:property value="accountID"/></s:param>

                                            </s:url>
                                            <s:a href='%{#myUrl}' style="color: #0000FF;"><s:property value="%{account_name}"/></s:a>
                                            </div> --%>
                                <div class="backgroundcolor" >
                                    <div class="panel-heading">
                                        <h4 class="panel-title">

                                            <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                            <font color="#ffffff">Sub Projects</font>
                                            <i id="updownArrow" onclick="toggleContent('subProjectsForm')" class="fa fa-angle-up"></i>

                                        </h4>
                                    </div>

                                </div>
                                <!-- content start -->

                                <label class="labelStyle" style="color: green; width: auto;margin-left: 20px" id="projectAddResult"><s:property value="projectsActionResponse"/></label>

                                <div class="col-sm-13" id="profileBox" style="margin-top: -20px">
                                    <span><searchSubProject></searchSubProject></span>
                                    <div >
                                        <div id="subProjectsForm">
                                            <div class="inner-addtaskdiv-elements">
                                                <div class="col-sm-3">
                                                    <label >Skill Set: </label>
                                                    <s:textfield cssClass="form-control" id="projectReqSkillSet" type="text"  placeholder="Skill Set" maxLength="100"/>

                                                </div>
                                                <div class="col-sm-3">
                                                    <label >Project Name: </label>
                                                    <s:textfield  cssClass="form-control" id="projectName" type="text" placeholder="Project Name" maxLength="30"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label >Start Date: </label>
                                                    <s:textfield cssClass="form-control" id="projectStartDateSearch" type="text" placeholder="Project Start Date" cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeypress=" return subProjectDaterepository();" autocomplete="off"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label >Target Date: </label>
                                                    <s:textfield cssClass="form-control" id="projectTargetDateSearch" type="text" placeholder="Project Target Date" cssStyle="height: 25px;background: white url(%{request.getContextPath()}/MSB/includes/images/calendar.gif) right no-repeat;padding-left: 17px;" onkeypress=" return subProjectDaterepository();" autocomplete="off"/>
                                                </div>

                                            </div>
                                            <br>
                                            <div class="col-sm-12 col-md-offset-8" style="margin-bottom: 5px">
                                                <div class="col-lg-2 ">
                                                    <s:submit type="button" cssClass="add_searchButton form-control" value="" onclick="searchSubProjects()" align="right" cssStyle="margin:5px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                    </div>
                                                    <div class="col-lg-2 ">
                                                        <a href="" class="addSubProject_popup_open" ><button class="add_searchButton form-control" style="margin:5px" ><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>
                                                    </div>
                                                </div> </div>
                                            <div>
                                            <s:form>
                                                <div style="width: fit-content">
                                                    <s:hidden name="remainingTargetHrs" id="remainingTargetHrs" value=""/>
                                                    <div class="emp_Content" id="emp_div" align="center" style="width:auto;margin: auto" >
                                                        <table id="projectResults" class="responsive CSSTable_task" border="5"  >
                                                            <script>
                                                                var  total =0;
                                                            </script>
                                                            <s:hidden name="totalTargetHrs" id="totalTargetHrs" value=""/>
                                                            <br>
                                                            <tr>
                                                                <th>Name</th>
                                                                <%-- <th>Project Type</th>--%>
                                                                <th>Skill-Set</th>
                                                                <th>No.Of.Resources</th>
                                                                <th>Description</th>
                                                                <th>Start Date</th>
                                                                <th>Target Date</th>
                                                                <th>Target Hrs</th>
                                                                <th>Status</th>
                                                            </tr>
                                                            <s:if test="searchDetails ==null || searchDetails.size() == 0">
                                                                <tr>
                                                                    <td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:set name="parentPid" scope="request" value="projectID"/>
                                                            <s:iterator  value="searchDetails">
                                                                <s:hidden name="ppid" value="%{parentProjectID}" id="ppid"/>
                                                                <tr>

                                                                    <%-- <s:url action="projectDetails.action" var="getDetails">
                                                                         <s:param name="projectID"><s:property value="projectID"/></s:param>
                                                                         <s:param name="accountID"><s:property value="accountID"/></s:param>
                                                                     </s:url>--%>
                                                                    <td><s:a href="" onclick="this.href='projectDetails.action?projectID='+%{projectID}+'&accountID='+%{accountID}+'&remainingTargetHrs='+document.getElementById('remainingTargetHrs').value"><s:property value="projectName"/></s:a></td>
                                                                    <%-- <td><s:property value="projectType"/></td>--%>
                                                                    <%-- <td><s:property value="projectReqSkillSet"/></td>
                                                                     <td><s:property value="project_description"/></td>--%>

                                                                    <s:if test="%{projectReqSkillSet.length()>10}">
                                                                        <%-- <td><s:property value="projectReqSkillSet"/></td>--%>
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
                                                                    <td><center><s:a href="#" cssClass="resourceOverlay_popup_open" onclick="showResourceTeam(%{projectID})"><% out.print("" + noOfResoudeById);%></s:a></center></td>

                                                                <%-- <td><s:property value="project_description"/></td>--%>
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
                                                                    total = total+t;
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

                            </div>
                        </div>
                    </div>
                </div>

                <%--close of future_items--%>
            </div>
        </div>

    </section>
</div>

<script>
    window.setTimeout("pagerOption();", 1000);
    window.setTimeout("doOnLoad();", 1000);
    setupAddSubProjectOverlay();
</script>

<script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

<script type="text/javascript">
    var recordPage=10;
    function pagerOption(){
        //alert("sub")
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