<%--
    Document   : Vendor Dashboard
    Created on : July 01, 2015, 07:10:41 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Vendor Dashboard Page</title>
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>--%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/glinechart.js"/>"></script>
    </head>
    <body style="overflow-x: hidden">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>
        </header>
        <s:include value="/includes/menu/LeftMenu.jsp"/>
        <section id="generalForm"><!--form-->
            <div  class="container">
                <div class="row">
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Dashboard</font>
                                            </h4>
                                        </div>
                                    </div>
                                    <span> <br/></span>
                                    <!-- content start -->
                                    <div class="col-sm-12">
                                        <s:form theme="simple" >

                                            <div class="inner-reqdiv-elements">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label class="" style="color:#56a5ec;">Year: </label>
                                                        <s:textfield cssClass="form-control" id="year"
                                                                     name="year" placeholder="Year" 
                                                                     />
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <label class="" style="color:#56a5ec;">Month: </label>
                                                        <s:select id="month" cssClass="form-control SelectBoxStyles" name="month" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"/>
                                                    </div>

                                                    <div class="col-lg-2">
                                                        <div class="row">
                                                            <div class="col-lg-11">
                                                                <label class="" style="color:#56a5ec;"></label> 
                                                                <%--<s:submit type="submit" cssClass="cssbutton_emps form-control"
                                                                        value="Search" onclick="getVendorDashboardList();"/> --%>
                                                                <a href="#" ><input type="button" class="cssbutton_action_search form-control" value="Search" style="margin:5px" onclick="getVendorDashboardList();"></a>
                                                            </div>
                                                        </div>
                                                    </div>   
                                                </div>
                                            </div>
                                        </div>


                                        <%--div class="row">
                                            <div class="col-sm-4"> <s:submit type="submit" cssClass="cssbutton_emps field-margin"
                                                      value="Search" cssStyle="margin:0px"/></div>
                                            <div class="col-sm-4"></div>
                                        </div--%>

                                    </s:form>
                                    <span> <br/></span>
                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                    <div class="col-sm-12">

                                        <s:form>
                                            <s:hidden id="accountSearchID" value="%{id}" ></s:hidden>
                                            <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                <table id="dashboardResults" class="responsive CSSTable_task" border="5">
                                                    <tbody>
                                                        <tr>
                                                            <%--<th>Req.Won</th>
                                                                <th>Req.Lost</th> --%>
                                                            <th>Month</th>
                                                            <th>Requirements</th>
                                                            <th>Processing </th>
                                                            <th>Servicing </th>
                                                            <th>Selected</th>
                                                            <th>ShortListed</th>
                                                            <th>Rejected</th>

                                                        </tr>
                                                        <s:if test="vendorDashboardList.size == 0">
                                                            <tr>
                                                                <td colspan="7"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                            </tr>
                                                        </s:if>
                                                    <script>
                                                        google.load('visualization', '1.1', {
                                                            'packages': ['corechart']
                                                        });
                                                        google.setOnLoadCallback();
                                                        var montharr = new Array();
                                                        var reqarr=new Array();
                                                        var selarr =new Array();
                                                        var rejarr=new Array();
                                                        var servicingarr=new Array();
                                                        var shortlistarr=new Array();
                                                        var processarr=new Array();
                                                    </script>

                                                    <s:iterator  value="vendorDashboardList">
                                                        <tr>
                                                            <%-- <td><s:property value="noOfReqWon"></s:property></td>
                                                                 <td><s:property value="noOfReqLose"></s:property></td>--%>
                                                            <td><s:property value="months"></s:property></td>
                                                            <td><s:property value="requirementCount"></s:property></td>
                                                            <td><s:property value="noOfConProcessing"></s:property></td>
                                                            <td><s:property value="noOfConServicing"></s:property></td>
                                                            <td><s:property value="noOfConSelected"></s:property></td>
                                                            <td><s:property value="noOfConShortListed"></s:property></td>
                                                            <td><s:property value="noOfConRejected"></s:property></td>
                                                        <script>
                                                                montharr.push('<s:property value="months"></s:property>')
                                                       
                                                                reqarr.push(parseInt('<s:property value="requirementCount"></s:property>'));
                                                                processarr.push(parseInt('<s:property value="noOfConProcessing"></s:property>'))
                                                                servicingarr.push(parseInt('<s:property value="noOfConServicing"></s:property>'));
                                                                selarr.push(parseInt('<s:property value="noOfConSelected"></s:property>'))
                                                                shortlistarr.push(parseInt('<s:property value="noOfConShortListed"></s:property>'))
                                                                rejarr.push(parseInt('<s:property value="noOfConRejected"></s:property>'));
                                                        </script>
                                                        </tr>
                                                    </s:iterator>

                                                    </tbody>
                                                </table>
                                                <br/>
                                                <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                        <option>5</option>
                                                        <option>10</option>
                                                        <option>12</option>
                                                    </select>
                                                    Months per page
                                                </label>
                                                <div align="right" class="pull-right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                            </s:form>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-12"><br></div>
                            <div class="col-sm-12" id="BarchartDiv">
                                <div class="col-lg-12 panel panel-default panel-heading">
                                    <i class="fa fa-bar-chart-o fa-fw"></i>Bar Chart<div align="center"><span id="chartTitle"></span></div>
                                    <br>
                                    <span id="norecords"></span>
                                    <div id="Barchart"></div>

                                </div>
                            </div>
                            <%--close of future_items--%>
                        </div>

                        <%-- <div class="col-sm-9 padding-right" id="BarchartDiv">
                            <div class="features_items"><!--features_items-->
                                <div class="col-lg-6 panel panel-default panel-heading">

                                    <i class="fa fa-bar-chart-o fa-fw"></i>
                                    Bar Chart

                                    <div id="Barchart"></div>
                                </div>
                            </div>
                        </div> --%>
                    </div>

                </div>
            </div>        <!-- content end -->
        </section><!--/form-->
        <script>
            //alert("In outSide");
            var Combined = new Array();
            Combined[0] = ['Year', 'Processing','Servicing', 'Selected','Short Listed','Rejected'];
            for (var i = 0; i < montharr.length; i++){
                Combined[i + 1] = [ montharr[i], processarr[i], servicingarr[i], selarr[i], shortlistarr[i], rejarr[i] ];
            }
            //alert(Combined+"------>Combined");
            //second parameter is false because first row is headers, not data.
            var data = google.visualization.arrayToDataTable(Combined, false);
    
            var options = {
                // width: 370,
                // height:300,
                legend: { position: 'top', alignment: 'center' },
               // title: 'Monthly Analysis',
                colors: ['#0000FF', '#00FF00', '#FF0000'],
                titleColor: "green",
                vAxis: {
                    title: "Consultants",
                    titleColor: "green"
                },
                hAxis: {
                    title: "Months",
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
             document.getElementById('chartTitle').innerHTML = "<font color=green><b>Consultants Monthly Analysis</b></font>";
            drawChart();
            function drawChart() {
                // Instantiate and draw our chart, passing in some options.
                chart.draw(data, options);
            }
            
            $(window).resize(function () {
                drawChart();
            });
           
        </script> 
        <s:if test="vendorDashboardList.size == 0">
            <script>
                $("#BarchartDiv").css('visibility', 'hidden');
            </script>

        </s:if>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=5;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                // alert(recordPage)
                $('#dashboardResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#dashboardResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
    </body>
</html>
