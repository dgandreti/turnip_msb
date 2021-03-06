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
        <title>ServicesBay :: Dashboard Page</title>
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
        <%--        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>--%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/dashBoardAjax.js"/>'></script>

        <script type="text/javascript" src="<s:url value="/includes/js/general/glinechart.js"/>"></script>
        <script>
            var pager;
            
            //$(document).ready(function(){
            function customerDboard(){
                //alert("hi")
                var paginationSize = 5;
                pager = new Pager('vendorReqDashboardResults', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                // document.getElementById("paginationOption").value=10;
                
                pager.showPage(1);
            };
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("vendorReqDashboardResults");
                sortableTableRows = document.getElementById("vendorReqDashboardResults").rows;
                sortableTableName = "vendorReqDashboardResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };

            //            function pagerOption(){
            //
            //                paginationSize = document.getElementById("paginationOption").value;
            //                if(isNaN(paginationSize))
            //                    alert(paginationSize);
            //
            //                pager = new Pager('customerDashboardResults', parseInt(paginationSize));
            //                pager.init();
            //                pager.showPageNav('pager', 'pageNavPosition');
            //                pager.showPage(1);
            //
            //            };
            
        </script>

    </head>
    <body style="overflow-x: hidden" onload="getVendorReqDashBoardGrid();">
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
                                                        <font color="#ffffff">Requirements</font>
                                                        <i id="updownArrow" onclick="toggleContent('venReqDashboardForm')" class="fa fa-minus"></i> 
                                                    </h4>
                                                </div>
                                            </div>

                                            <!-- content start -->
                                            <br>
                                            <div  id="venReqDashboardForm">
                                                <div class="col-sm-4">
                                                    <label class="labelStylereq " tabindex="1" style="color:#56a5ec;">Job Title</label>
                                                    <s:textfield cssClass="form-control" name="jobTitle" id="jobTitle" placeholder="Job Title" tabindex="1" />
                                                    <%--<s:select id="jobTitle" name="jobTitle" cssClass="reqSelectStyle" headerKey="-1" headerValue="jobTitle" theme="simple" list="{'Developer','Tester','Manager'}" />--%>
                                                </div>  

                                                <div class="col-sm-4">

                                                    <label style="color:#56a5ec;" class="labelStylereq">Candidate Name&nbsp;&nbsp;</label>
                                                    <s:textfield cssClass="form-control" name="candidateName" id="candidateName" placeholder="Candidate Name" tabindex="2" maxLength="30"/>

                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="" style="color:#56a5ec;"></label> 
                                                    <%--<s:submit type="submit" cssClass="cssbutton_emps form-control"
                                                            value="Search" onclick="getVendorDashboardList();"/> --%>
                                                    <a href="#" ><input type="button" class="cssbutton_action_search form-control" value="Search"  onclick="return getVendorReqDashBoardGrid();" tabindex="3"></a>
                                                </div>
                                            </div>
                                            <div  class="col-sm-12" >              
                                                <s:form>
                                                    <s:hidden id="accountSearchID" value="%{id}" ></s:hidden>
                                                    <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                        <table id="vendorReqDashboardResults" class="responsive CSSTable_task sortable" border="5">
                                                            <tbody>
                                                                <tr>
                                                                    <%--<th>Req.Won</th>
                                                                        <th>Req.Lost</th> --%>
                                                                    <th>Requirement</th>
                                                                    <th>Consultant </th>
                                                                    <th>Status</th>
                                                                </tr>

                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                <option>5</option>
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>20</option>
                                                            </select>
                                                            Requirements per page
                                                        </label>
                                                        <div align="right" class="pull-right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                    </div>
                                                </s:form>
                                            </div>

                                        </div>
                                        <div class="col-sm-12">
                                            <s:form theme="simple" >

                                                <div class="row">
                                                    <s:iterator value="requirementlistVTO">
                                                        <div class="col-lg-4 col-md-6">
                                                            <div class="panel panel-white">
                                                                <div class="panel-heading">
                                                                    <div class="row">
                                                                        <div class="col-xs-3">
                                                                            <img src="<s:url value="/includes/images/icons/requirement.png"/>" height="70" width="70">
                                                                            <!--<i class="fa fa-comments fa-5x"></i>-->
                                                                        </div>
                                                                        <div class="col-xs-9 ">
                                                                            <div class="huge"><label class="labelColor">Positions: </label><s:property value="noOfPosition"></s:property></div>
                                                                            <div><label class="labelColor">Name: </label><s:property value="title"></s:property></div>
                                                                            <div><label class="labelColor">Avg. Rate: $</label><s:property value="targetRate"></s:property></div>
                                                                            <div><label class="labelColor">Customer: </label><s:property value="customerName"></s:property></div>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <s:url id="addConsult" action="/recruitment/consultant/doAddConsultantForReq.action" namespace="/" encode="true">
                                                                    <s:param name="requirementId" value="%{id}" />
                                                                    <s:param name="jdId" value="%{jdId}"></s:param>
                                                                    <s:param name="jobTitle" value="%{title}"></s:param>
                                                                    <s:param name="orgid"><s:property value="%{orgId}"/></s:param>
                                                                    <s:param name="targetRate"><s:property value="%{targetRate}"/></s:param>
                                                                    <s:param name="maxRate"><s:property value="%{requirementMaxRate}"/></s:param>
                                                                </s:url>
                                                                <s:a href="%{#addConsult}">
                                                                    <div class="panel-footer">
                                                                        <span class="">Add Consultant</span>
                                                                        <span class="pull-right"><i class="fa fa-plus-square"></i></span>
                                                                        <div class="clearfix"></div>
                                                                    </div>
                                                                </s:a>
                                                            </div>
                                                        </div>
                                                    </s:iterator>
                                                </div>


                                                <%--div class="row">
                                                    <div class="col-sm-4"> <s:submit type="submit" cssClass="cssbutton_emps field-margin"
                                                              value="Search" cssStyle="margin:0px"/></div>
                                                    <div class="col-sm-4"></div>
                                                </div--%>

                                            </s:form>
                                            <span> <br/></span>
                                                <%--<s:submit cssClass="css_button" value="show"/><br>--%>

                                        </div>
                                    </div>


                                    <%--close of future_items--%>
                                </div>
                            </div>

                        </div>
                    </div>        <!-- content end -->
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
                $('#vendorReqDashboardResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#vendorReqDashboardResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script>
            sortables_init();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
