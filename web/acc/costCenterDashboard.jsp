<%--
    Document   : Cost Center Dashboard
    Created on : October 01, 2015, 07:10:41 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Cost Center Dashboard Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <%-- <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
             <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>--%>
        <script type="text/javascript" src="<s:url value="/includes/js/general/glinechart.js"/>"></script>
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>

        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/dashBoardAjax.js"/>'></script>


    </head>
    <body style="overflow-x: hidden">
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
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Cost Center</font>
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
                                                    <s:hidden id="quarters"/>
                                                    <s:hidden id="costcenternames"/>
                                                    <div class="col-sm-2">
                                                        <label class="" style="color:#56a5ec;">Year </label>
                                                        <s:textfield cssClass="form-control" id="dashBoardYear"
                                                                     name="dashBoardYear" placeholder="Year" onkeyup="getQuartersChart();" tabindex="1"
                                                                     />
                                                    </div>
                                                    <%--  <div class="col-lg-2">
                                                           <label class="" style="color:#56a5ec;">Quarter: </label>
                                                           <s:select cssClass="form-control SelectBoxStyles" 
                                                                     id="quarter" 
                                                                     name="quarter" 
                                                                     list="#@java.util.LinkedHashMap@{'DF':'All','Q1':'Q1','Q2':'Q2','Q3':'Q3','Q4':'Q4'}" 
                                                                     onchange="settingValue();"
                                                                     />

                                                    </div> --%>
                                                    <s:if test="#session.primaryrole == 0">
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec;">Customer </label>
                                                            <s:hidden name="orgId" id="orgId"/>
                                                            <s:textfield id="accountNamePopup" autocomplete="off" 
                                                                         cssClass="form-control"
                                                                         type="text"
                                                                         name="accName"
                                                                         placeholder="Customer Name"
                                                                         onkeyup="return getAccountsNames('C');" onkeydown="return removeTextFieldData();" maxLength="60"/> 
                                                            <span id="validationMessageForCustomer" />
                                                            <%-- <s:select cssClass="SelectBoxStyles form-control" name="csrCustomers" id="csrCustomers" headerKey="-1" headerValue="All" list="custerMap" /> --%>
                                                        </div>
                                                    </s:if>


                                                    <s:if test="#session.primaryrole == 2 || #session.primaryrole == 13">
                                                        <s:hidden name="orgId" id="orgId" value="%{#session['orgId']}"/>
                                                    </s:if>
                                                    <%-- <div class="col-lg-2">
                                                        <label class="" style="color:#56a5ec;">Month: </label>
                                                        <s:select id="month" cssClass="form-control SelectBoxStyles" name="month" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"/>
                                                    </div> --%>


                                                    <input type="hidden" onclick=""/>

                                                </div>




                                                <%--div class="row">
                                                    <div class="col-sm-4"> <s:submit type="submit" cssClass="cssbutton_emps field-margin"
                                                              value="Search" cssStyle="margin:0px"/></div>
                                                    <div class="col-sm-4"></div>
                                                </div--%>

                                            </s:form>
                                        </div>

                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>


                                        <s:if test="costCenterVTOList.size != 0">
                                            <script>
                                                google.load('visualization', '1.1', {
                                                    'packages': ['corechart']
                                                });
                                                google.setOnLoadCallback();
                                                var accounts = new Array();
                                                var budgetAmt = new Array();
                                                var consumedAmt=new Array();
                                                var balanceAmt =new Array();
                                            </script>
                                            <s:iterator  value="costCenterVTOList">
                                                <%--<s:property value="accountName"></s:property>
                                                <s:property value="budgetAmt"></s:property>
                                                <s:property value="spentAmt"></s:property>
                                                <s:property value="balanceAmt"></s:property> --%>
                                                <script>
                                                accounts.push('<s:property value="accountName"></s:property>')
                                                                
                                                budgetAmt.push(parseInt('<s:property value="budgetAmt"></s:property>'))
                                                consumedAmt.push(parseInt('<s:property value="spentAmt"></s:property>'));
                                                balanceAmt.push(parseInt('<s:property value="balanceAmt"></s:property>'));
                                                            
                                                </script>

                                            </s:iterator>
                                        </s:if>
                                        <%--close of future_items--%>
                                        <%-- <div class="col-lg-2" id="costCentersSelectDiv" style="display: none">
                                            <label  >Cost Centers </label><s:select  id="costCenters"  name="costCenters" cssClass="form-control SelectBoxStyles "  list="#@java.util.LinkedHashMap@{}" onchange="removeProjectsDiv();"/>
                                        </div>  --%>
                                        <div class="col-sm-12"><br></div>
                                        <div class="col-sm-12">
                                            <div class="col-sm-12 panel panel-default panel-heading">
                                                <i class="fa fa-bar-chart-o fa-fw"></i>Bar Chart<div align="center"><span id="chartTitle"></span></div>
                                                <br>
                                                <span id="norecords"></span>
                                                <div id="Barchart"></div>

                                            </div>
                                        </div>
                                        <div class="col-sm-12"><br></div>
                                        <div class="col-sm-12" id="costCentersDiv" style="display: none">
                                            <div class="col-sm-12 panel panel-default panel-heading">
                                                <i class="fa fa-bar-chart-o fa-fw"></i>Bar Chart<div align="center"><span id="costcenterchartTitle"></span></div>
                                                <br>
                                                <span id="costcentersnorecords"></span>
                                                <div id="costcentersBarchart"></div>

                                            </div>
                                        </div>
                                        <div class="col-sm-12"><br></div>
                                        <div class="col-sm-12" id="costCentersProjectsDiv" style="display: none">
                                            <div class="col-sm-12 panel panel-default panel-heading">
                                                <i class="fa fa-bar-chart-o fa-fw"></i>Bar Chart<div align="center"><span id="projectschartTitle"></span></div>
                                                <br>
                                                <span id="projectsnorecords"></span>
                                                <div id="projectsBarchart"></div>

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
        <s:if test="costCenterVTOList.size != 0">
            <script>
                //alert("In outSide");
                var Combined = new Array();
                Combined[0] = ['Account','Budget Amount', 'Consumed Amount','Balance Amount'];
                for (var i = 0; i < accounts.length; i++){
                    Combined[i + 1] = [accounts[i],budgetAmt[i], consumedAmt[i], balanceAmt[i] ];
                }
                //alert(Combined+"------>Combined");
                //second parameter is false because first row is headers, not data.
                var data = google.visualization.arrayToDataTable(Combined,false);
                //alert(Combined);
                var options = {
                    // width: 370,
                    // height:300,
                    legend: { position: 'top', alignment: 'center' },
                    colors: ['#0000FF', '#FF0000', '#00FF00'],
                    titleColor: "green",
                    vAxis: {
                        title: "Budget Amount",
                        format:'$###,###,###.00',
                        titleColor: "green"
                    },
                    hAxis: {
                        title: "Quarters",
                        titleColor: "green"
                    }
                    // animation: {
                    //duration: 1000,
                    //easing: 'linear'
                    //vAxis: {
                    //viewWindow: {
                    //max: 8
                }
                var chart = new google.visualization.ColumnChart(document.getElementById('Barchart'));
                document.getElementById('chartTitle').innerHTML = "<font color=green><b> Budget Analysis</b></font>";
                
                function selectHandler() {
                    var selectedItem = chart.getSelection()[0];
                    if (selectedItem) {
                        var practice = data.getValue(selectedItem.row, 0);
                        // alert('The user selected--> '+practice);
                        getCostCenterDashboardList(practice,"quarters");
                        // opportunitiesDetailsByPractice(practice);
                        //chatOverlay(topping);
                    }
                }
                google.visualization.events.addListener(chart, 'select', selectHandler); 
               
                chart.draw(data, options);
                
                $(window).resize(function () {
                    chart.draw(data, options);
                });
           
            </script> 
        </s:if>
        <s:if test="costCenterVTOList.size == 0">
            <script>
                // $("#BarchartDiv").css('visibility', 'hidden');
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
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>
