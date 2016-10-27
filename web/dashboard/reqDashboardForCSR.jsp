<%-- 
    Document   : dashboard
    Created on : July 2nd , 2015, 12:32:23 PM
    Author     : Aklakh Ahmad
--%>



<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
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
        <title>ServicesBay :: Requirements Dashboard Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/requirementStyle.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/vendorDetailsStyles.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <!-- end of new styles -->

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/requirement.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/dashBoardAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorDashboardAjax.js"/>'></script>

        <script type="text/javascript" src="<s:url value="/includes/js/general/glinechart.js"/>"></script>


    </head>
    <body oncontextmenu="return false" onload="getCustomerRequirementsDashBoard();">
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
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">

                                <div style="margin-top: 10px">
                                    <!--<headingmess id="headingmessage"  class="acc_menu_heading pull-right" style="display:block">Customer Dashboard</headingmess>-->    

                                </div> 
                                <ul class="active_details" >
                                    <li class="dropdown"  >
                                        <a class="dropdown-toggle " data-toggle="dropdown"  href="#" title="Dashboard Tabs"   style="background-color: #000; width:40px;"><img src="<s:url value="/includes/images/toggleMenu.png"/>" height="40" width="38"></a>
                                    <headingmess id="headingmessage"  class="accDetails" >Customer Dashboard<i class="fa fa-angle-up" id="updownArrowAccount" onclick="toggleContentAccount('customerDiv')" style="margin-top: 0vw;position:absolute;color:#56a5ec"></i></headingmess>
                                    <!-- Nav tabs -->
                                    <ul class="panel-body nav-stacked  dropdown-menu " style="position:absolute">

                                        <li class=" "><a aria-expanded="false" onclick="dashboardMessage(this); getCustomerRequirementsDashBoard();" id="customerBoard"  href="#Customer" data-toggle="tab"> Customer Dashboard</a>
                                        </li>
                                        <li class=""><a aria-expanded="false" onclick=" dashboardMessage(this); getVendorRequirementsDashBoard();" id="vendorBoard"  href="#Vendor" data-toggle="tab">Vendor Dashboard </a>
                                        </li>

                                    </ul>
                                    </li>
                                </ul>
                                <div id="loadingReqDashboard" class="loadingImg" style="display: none">
                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                </div>
                                <div class="tab-content" style="padding : 0px">
                                    <div class="tab-pane fade in " id="Vendor">
                                        <div class="col-sm-12">
                                            <br>
                                            <span id="reqVendorDashboard"></span>

                                            <div class="row" id="vendorDivision">
                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color:#56a5ec;">Year </label>
                                                    <s:textfield cssClass="form-control" name="vdashYears" id="vdashYears" placeholder="Year" value="%{year}" onkeypress="return validationDashboardVendorYear(event)" tabindex="1" />
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color:#56a5ec;">Vendor </label>
                                                    <s:hidden name="vendorOrgId" id="vendorOrgId"/>
                                                    <s:textfield id="vendorAccountNamePopup" autocomplete="off" 
                                                                 cssClass="form-control"
                                                                 type="text"
                                                                 name="accName"
                                                                 placeholder="Vendor Name" 
                                                                 onkeyup="return getAccountsNames('V');" onkeydown="return removeTextFieldData();" maxLength="60" tabindex="2" />


                                                    <span id="validationMessageForVendor" />
                                                </div>
                                                <div class="col-sm-2 col-md-2 col-lg-2 pull-right">
                                                    <br>
                                                    <s:submit type="button" cssClass="add_searchButton form-control " id="requirementsDashBoardSearch" 
                                                              value="" onclick="return getVendorRequirementsDashBoard()" cssStyle="margin:5px" tabindex="3" ><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                    </div>

                                                </div>

                                                <table id="VendorDashBoardTable" class="responsive CSSTable_task" border="5" cell-spacing="2">
                                                    <tbody>
                                                        <tr>
                                                            <th>Vendor Name</th>
                                                            <th>Processing</th>
                                                            <th>Selected</th>
                                                            <th>Rejected</th>
                                                            <th>Won</th>
                                                            <th>Lost</th>
                                                            <th>Total</th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <br/>
                                                <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                        <option>10</option>
                                                        <option>15</option>
                                                        <option>25</option>
                                                        <option>50</option>
                                                    </select>
                                                    Accounts per page
                                                </label>
                                                <div align="right" id="pageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>

                                                <script type="text/javascript">
                                                    var pager = new Pager('VendorDashBoardTable', 8); 
                                                    pager.init(); 
                                                    pager.showPageNav('pager', 'pageNavPosition'); 
                                                    pager.showPage(1);
                                                </script>
                                            </div>
                                        </div> 
                                        <div class="tab-pane fade in active"  id="Customer" >
                                            <div class="col-sm-12">
                                            <s:form  theme="simple" >
                                                <br>


                                                <span id="reqCustomerDashBoardValidation"></span>
                                                <div class="row" id="customerDiv">
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Year </label>
                                                        <s:textfield cssClass="form-control" name="dashYears" id="dashYears" placeholder="Year" value="%{year}" onkeypress="return validationDashboardCustomerYear(event)" tabindex="1" />
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Customer </label>
                                                        <s:hidden name="orgId" id="orgId"/>
                                                        <s:textfield id="accountNamePopup" autocomplete="off" 
                                                                     cssClass="form-control"
                                                                     type="text"
                                                                     name="accName"
                                                                     placeholder="Customer Name"
                                                                     onkeyup="return getAccountsNames('C');" onkeydown="return removeTextFieldData();" maxLength="60" tabindex="2" /> 
                                                        <span id="validationMessageForCustomer" />
                                                    </div>
                                                    <div class="col-sm-2 col-md-2 col-lg-2 pull-right">
                                                        <br>
                                                        <s:submit type="button" cssClass="add_searchButton form-control "
                                                                  value="" onclick="return getCustomerRequirementsDashBoard()" cssStyle="margin:5px" tabindex="3" ><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                        </div>
                                                    </div>
                                            </s:form>
                                            <s:form>

                                                <div class="task_content" id="task_div" align="center" style="display: none" >

                                                    <div>
                                                        <div>
                                                            <table id="dashBoardTable" class="responsive CSSTable_task" border="5" cell-spacing="2">
                                                                <tbody>
                                                                    <tr>
                                                                        <th>Customer Name</th>
                                                                        <th>Open</th>
                                                                        <th>Release</th>
                                                                        <th>Closed</th>
                                                                        <th>Total</th>
                                                                    </tr>
                                                                    <s:if test="csrDashBoardList ==null">
                                                                        <tr>
                                                                            <td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                        </tr>
                                                                    </s:if>
                                                                    <s:iterator  value="csrDashBoardList">
                                                                        <tr>
                                                                            <s:hidden name="accountId" id="accountId" value="%{accountId}" />
                                                                            <td><s:property value="customerName"></s:property></td>
                                                                            <td><s:property value="open"></s:property></td>
                                                                            <td><s:property value="released"></s:property></td>
                                                                            <td><s:property value="closed"></s:property></td>
                                                                            <td><s:a href="#" cssClass="csrCustomerReq_popup_open" onclick="csrCustReqOverlay();csrCustReqDetails(%{accountId});"><s:property value="total"></s:property></s:a></td>
                                                                                </tr>
                                                                    </s:iterator>

                                                                </tbody>
                                                            </table>
                                                            <br>
                                                            <label class="page_option"> Display <select id="paginationOption_c" class="disPlayRecordsCss" onchange="pagerOption_c()" style="width: auto">
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                Accounts per page
                                                            </label>
                                                            <div align="right" id="pageNavPosition1" style="margin: 0px -1px 9px 5px;display: none"></div>
                                                        </div>
                                                        <script type="text/javascript">
                                                            var pager = new Pager('dashBoardTable', 8); 
                                                            pager.init(); 
                                                            pager.showPageNav('pager', 'pageNavPosition1'); 
                                                            pager.showPage(1);
                                                        </script>
                                                    </div>
                                                </div>
                                            </s:form>





                                            <br>

                                            <div class="col-sm-12" >
                                                <div class="col-lg-12 panel panel-default panel-heading">
                                                    <i class="fa fa-bar-chart-o fa-fw"></i>Bar Chart</div>
                                                <div id="reqCustomerYearChart"></div>

                                            </div>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>



                    <div id="csrCustomerReq_popup">
                        <div id="recruiterBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;No.Of&nbsp;Requirements&nbsp;Posted </font></h4></td>
                                    <span class="pull-right"> <h5 ><a href="" class="csrCustomerReq_popup_close" onclick="csrCustReqOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div style="margin: 10px;margin-bottom: -10px"><center>
                                    <table id="dashBoardTableOnOverlay"  class="CSSTable_task  " border="2" cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;">
                                        <tbody>
                                            <tr>
                                                <th>Month</th>
                                                <th>No.Of.Req</th>
                                            </tr>
                                        </tbody>
                                    </table>
                                </center>

                            </div><center>
                                <font style="color: #fff">........................ ......................................... .................................</font>
                            </center>
                        </div>
                    </div>



                    <div id="csrVendorReq_popup">
                        <div id="csrVenBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4><font class="titleColor">&nbsp;No.Of&nbsp;Requirements&nbsp;won/loss&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 ><a href="" class="csrVendorReq_popup_close" onclick="csrVenOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div style="margin: 10px;margin-bottom: -10px"><center>
                                    <table id="dashBoardTableVendorOnOverlay"  class="CSSTable_task  " border="2" cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;" >
                                        <tbody>
                                            <tr>
                                                <th>Month</th>
                                                <th>Won</th>
                                                <th>Lost</th>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div id="dashBoardTableVendorOnOverlay" align="right" style="margin-right:0vw"></div>
                                    <div   style="width:auto;height:auto" >
                                        <div  id="dashBoardTableVendorOnOverlay" class="badge pull-right" style="display:none"></div>                                                       
                                    </div>  
                                </center>
                            </div><center>
                                <font style="color: #fff">........................ ......................................... .................................</font>
                            </center>
                        </div>
                    </div>
                </section ><!--form-->
            </div>
        </div>
        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
            <script language="JavaScript" src='<s:url value="/includes/js/general/popupoverlay.js"/>'></script>

        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        <script>
    
            var recordPage=10;
          
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                //  alert(paginationSize)
                if(isNaN(paginationSize)){
                    
                }
                    
                recordPage=paginationSize;
          
                var dashBoardTable = document.getElementById('dashBoardTable');
                var VendorDashBoardTable = document.getElementById('VendorDashBoardTable');
                if ($("#Vendor").css('display') == 'none') {
                    // alert(recordPage+"cus");
                    $('#dashBoardTable').tablePaginate({navigateType:'navigator'},recordPage);
                }
                if ($("#Customer").css('display') == 'none') {
                    //alert(recordPage+"ven");
                    $('#VendorDashBoardTable').tablePaginate({navigateType:'navigator'},recordPage);
                 
                }         
            };
       
       
            function pagerOption_c(){

                var paginationSize = document.getElementById("paginationOption_c").value;
                //  alert(paginationSize)
                if(isNaN(paginationSize)){
                    
                }
                    
                recordPage=paginationSize;
          
                var dashBoardTable = document.getElementById('dashBoardTable');
                var VendorDashBoardTable = document.getElementById('VendorDashBoardTable');
                if ($("#Vendor").css('display') == 'none') {
                    // alert(recordPage+"cus");
                    $('#dashBoardTable').tablePaginate({navigateType:'navigator'},recordPage);
                }
                if ($("#Customer").css('display') == 'none') {
                    //alert(recordPage+"ven");
                    $('#VendorDashBoardTable').tablePaginate({navigateType:'navigator'},recordPage);
                 
                }         
            };
       
   
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>