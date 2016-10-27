<%--
    Document   : Projects Dashboard
    Created on : October 06, 2015, 07:10:41 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Projects&nbsp;Dashboard&nbsp;Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/glinechart.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/dashBoardAjax.js"/>'></script>
    </head>
    <body oncontextmenu="return false" style="overflow-x: hidden">
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
            <div  class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/>
                    <!-- content start -->
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font color="#ffffff">Projects</font>
                                            </h4>
                                        </div>
                                    </div>
                                    <span> <br/></span>
                                    <!-- content start -->
                                    <div class="col-sm-12">
                                        <s:form theme="simple" >
                                            <div id="loadingDashboardPage" class="loadingImg" style="display: none">
                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                            </div>
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">
                                                     <s:hidden id="mainprojects"/>
                                                    <s:hidden id="subprojects"/>
                                                    <div class="col-sm-2">
                                                        <label class="" style="color:#56a5ec;">Year </label>
                                                        <s:textfield cssClass="form-control" id="dashBoardYear"
                                                                     name="dashBoardYear" placeholder="Year" onkeyup="getMainProjectsChart();" tabindex="1"
                                                                     />
                                                    </div>
                                                    <s:hidden id="mainprojectsList" value="%{projectsMap}"/>
                                                    <s:hidden id="subprojectsMap"/>
                                                    <input type="hidden" onclick=""/>

                                                </div>
                                            </div>
                                        </div>
                                    </s:form>
                                    <script>
                                        google.load('visualization', '1.1', {
                                            'packages': ['corechart']
                                        });
                                        google.setOnLoadCallback();
                                        var projects = new Array();
                                        var targethrs = new Array();
                                        var workedhrs=new Array();
                                    </script>
                                    <s:if test="searchDetails.size != 0">
                                        <s:iterator  value="searchDetails">
                                            <script>
                                                projects.push('<s:property value="projectName"></s:property>')
                                                targethrs.push(parseInt('<s:property value="projectTargetHrs"></s:property>'))
                                                workedhrs.push(parseInt('<s:property value="projectWorkedHrs"></s:property>'));
                                            </script>
                                        </s:iterator>
                                    </s:if>
                                    <div class="col-sm-12"><br></div>
                                    <div class="col-sm-12">
                                        <div class="col-lg-12 panel panel-default panel-heading">
                                            <i class="fa fa-bar-chart-o fa-fw"></i>Bar chart<div align="center"><span id="chartTitle"></span></div>
                                            <br>
                                            <span id="norecords"></span>
                                            <div id="Barchart"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12"><br></div>
                                    <div class="col-sm-12" id="subProjectsDiv" style="display: none">
                                        <div class="col-lg-12 panel panel-default panel-heading">
                                            <i class="fa fa-bar-chart-o fa-fw"></i>Bar chart<div align="center"><span id="subProjectsTitle"></span></div>
                                            <br>
                                            <span id="subProjectsnorecords"></span>
                                            <div id="subProjectsBarchart"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12" style="display: none" id="resouresDiv">
                                        <div class="col-lg-12 panel panel-default panel-heading">
                                            <i class="fa fa-bar-chart-o fa-fw"></i>Bar chart<div align="center"><span id="resorceschartTitle"></span></div>
                                            <br>
                                            <span id="resourcesnorecords"></span>
                                            <div id="BarchartForResources"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content end -->
        </section><!--/form-->
        </div>
        <s:if test="searchDetails.size != 0">
            <script>
                var Combined = new Array();
                Combined[0] = ['Project','Target Hours', 'Worked Hours'];
                for (var i = 0; i < projects.length; i++){
                    Combined[i + 1] = [projects[i],targethrs[i], workedhrs[i]];
                }
                var data = google.visualization.arrayToDataTable(Combined, false);
                var options = {
                    legend: { position: 'top', alignment: 'center' },
                    colors: ['#0000FF', '#00FF00'],
                    titleColor: "green",
                    vAxis: {
                        title: "Hours",
                        titleColor: "green"
                    },
                    hAxis: {
                        title: "Projects",
                        titleColor: "green"
                    }
                }
                var chart = new google.visualization.ColumnChart(document.getElementById('Barchart'));
                document.getElementById('chartTitle').innerHTML = "<font color=green><b> Projects Analysis</b></font>";
                function selectHandler() {
                    var selectedItem = chart.getSelection()[0];
                    if (selectedItem) {
                        var practice = data.getValue(selectedItem.row, 0);
                      getProjectDashboardList(practice,"mainprojects");
                    }
                }
                google.visualization.events.addListener(chart, 'select', selectHandler); 
                chart.draw(data, options);
                $(window).resize(function () {
                    chart.draw(data, options);
                });
           
            </script> 
        </s:if>
        <s:if test="searchDetails.size == 0">
            <script>
                document.getElementById("norecords").innerHTML="<font color='red'>No Records to display</font>";
            </script>

        </s:if>
    </div>
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
