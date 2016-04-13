<!--
    Author     : Riza
-->
<%@page import="com.mss.msp.acc.projectsdata.ProjectsVTO"%>
<%@page import="com.mss.msp.acc.projectsdata.ProjectsDataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
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
        <title>ServicesBay :: Projects</title>
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
        <%-- for date picket end--%>

        <sx:head />

    </head>
    <body>
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
                                <div class="col-lg-12 ">
                                    <div class="" id="" style=" margin-top: 5px">


                                        <div id="projectsPage">
                                            <s:action name="getAccountProjects" executeResult="true" namespace="/"/>
                                        </div>

                                        <!-- content start -->
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
         </div>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
         <script>
            ;
            
            function searchProjects(){
                initSessionTimer();
                var projectStartDateOverlay=document.getElementById("projectStartDate").value;
                var projectTargetDateOverlay=document.getElementById("projectTargetDate").value;
                var splitSDate = projectStartDateOverlay.split('-');
                var sdate = new Date(splitSDate[2], splitSDate[0]-1 , splitSDate[1]); 
                var splitEDate = projectTargetDateOverlay.split('-');
                var edate = new Date(splitEDate[2], splitEDate[0]-1, splitEDate[1]); 
                var StartDateProject = Date.parse(sdate);
                var endDateProject= Date.parse(edate);
                var  difference=(endDateProject - StartDateProject) / (86400000 * 7);
                if((sdate=="Invalid Date")&&(edate=="Invalid Date")){
                    difference = 1;
                }
                else if((sdate=="Invalid Date")&&(edate!="Invalid Date")){
                    difference = -1;
                }
                else if((sdate!="Invalid Date")&&(edate=="Invalid Date")){
                    difference = -2;
                }
                if(difference>0){
                    document.getElementById('loadingSearchProject').style.display = 'block';
                    $.ajax({url:"<%=request.getContextPath()%>/mainProjectsSearch.action"
                            +"?projectReqSkillSet=" + document.getElementById("projectReqSkillSet").value
                            +"&projectName=" + document.getElementById("projectName").value
                            +"&projectStartDate=" + document.getElementById("projectStartDate").value
                            +"&projectTargetDate=" + document.getElementById("projectTargetDate").value
                            +"&accountID=<s:property value='accountID'/>"
                            +"&projectType="+"MP"
                        ,
                        success: function(data){
                            $('#loadingSearchProject').hide();
                            window.setTimeout("pagerOption();", 1000);
                            window.setTimeout("doOnLoad();", 1000);
                            $(projectsPage).children().remove();
                            document.getElementById('projectsPage').innerHTML = data;
                             document.getElementById("loadingProjectSearch").style.display="none";
                        },
                        type: 'GET'
                    });
                }
                else{
                    if(difference==-1){
                        $("searchProject").html(" <b><font color='red'>Select start date!</font></b>");
                        $("#projectStartDate").css("border", "1px solid red");
                    }
                    else if(difference==-2){
                        $("searchProject").html(" <b><font color='red'>Select target date!</font></b>");
                        $("#projectTargetDate").css("border", "1px solid red");
                    }else{
                        $("searchProject").html(" <b><font color='red'>Start date must be less than target date!</font></b>");
                        $("#projectStartDate").css("border", "1px solid red");
                        $("#projectTargetDate").css("border", "1px solid red");
                    }
                    return false;
                    
                } 
            };
            
            function addProject(){
                var projectNamePopup = document.getElementById("projectNamePopup").value;
                var projectWorkedHrs = document.getElementById("projectWorkedHrs").value;
                var projectStartDateOverlay=document.getElementById("projectStartDateOverlay").value;
                var projectTargetDateOverlay=document.getElementById("projectTargetDateOverlay").value;
                var projectTargetHrs=document.getElementById("projectTargetHrs").value;
                var costCenterName= document.getElementById("costCenterName").value;
                var splitProjectStartDate = projectStartDateOverlay.split('-');
                var startDate = new Date(splitProjectStartDate[2], splitProjectStartDate[0]-1 , splitProjectStartDate[1]); //Y M D 
                var splitProjectTargetDate = projectTargetDateOverlay.split('-');
                var targetDate = new Date(splitProjectTargetDate[2], splitProjectTargetDate[0]-1, splitProjectTargetDate[1]); //Y M D
                var mainProjectStartDate = Date.parse(startDate);
                var mainProjectTargetDate= Date.parse(targetDate);
                var  difference=(mainProjectTargetDate - mainProjectStartDate) / (86400000 * 7);
                if(difference >= 0 && projectNamePopup!="" && projectTargetHrs!= "" && costCenterName!= "DF" ){
                    projName = document.getElementById("projectNamePopup").value;
                       $.ajax({url:"<%=request.getContextPath()%>/addMainProject.action"
                            +"?projectName=" + document.getElementById("projectNamePopup").value
                            +"&project_status=" + document.getElementById("project_statusPopup").value
                            +"&projectStartDate=" + document.getElementById("projectStartDateOverlay").value
                            +"&projectTargetDate=" + document.getElementById("projectTargetDateOverlay").value
                            +"&projectReqSkillSet=" + document.getElementById("projectReqSkillSetPopup").value
                            +"&project_description=" + document.getElementById("project_descriptionPopup").value
                            +"&projectTargetHrs=" + document.getElementById("projectTargetHrs").value
                            +"&costCenterName=" + document.getElementById("costCenterName").value
                            +"&projectWorkedHrs="+document.getElementById("projectWorkedHrs").value
                            +"&accountID=<s:property value="accountID"/>"
                        ,
                        success: function(data){
                            $("#addProjectValidation").html("<font color='green'><b>Project inserted successfully.</b></font>");
                            $("#projects").html(data);
                            resetOverlayForm();
                        },
                        type: 'GET'
                    });
                }else{
                    if(projectNamePopup=="")
                    {
                        $("#addProjectValidation").html(" <font color='red'>Please , Enter the Project name!</font>");
                        $("#projectNamePopup").css("border", "1px solid red");
                        return false;
                    }
                   
                    if(projectStartDateOverlay=="")
                    {
                        $("#addProjectValidation").html(" <b><font color='red'>project start date is required</font></b>");
                        $("#projectStartDateOverlay").css("border", "1px solid red");
                        return false;
                    }
                    
                   
                    
                    if(projectTargetDateOverlay=="")
                    {
                        $("#addProjectValidation").html(" <b><font color='red'>project target date is required</font></b>");
                        $("#projectTargetDateOverlay").css("border", "1px solid red");
                        return false;
                    }
                  
                    if(difference<=0){
                        $("#addProjectValidation").html(" <b><font color='red'>Start date must be less then target date</font></b>");
                        $("#projectStartDateOverlay").css("border", "1px solid red");
                        $("#projectTargetDateOverlay").css("border", "1px solid red");
                        return false;
                    }
                    
                  
                    if(projectTargetHrs=="")
                    {
                        
                        $("#addProjectValidation").html(" <font color='red'>Please, Enter the Target hours!</font>");
                        $("#projectTargetHrs").css("border", "1px solid red");
                        return false;
                    }
                 
                    if(costCenterName=="DF"){
                        $("#addProjectValidation").html(" <font color='red'>Please, Select the Cost center!</font>");
                        $("#costCenterName").css("border", "1px solid red");
                        return false;
                    }
                }
            };

            function resetOverlayForm(){
                document.getElementById("overlayForm").reset();
                document.getElementById("projectNameError").style.display = "none";
            };
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
   
    </body>
</html>
