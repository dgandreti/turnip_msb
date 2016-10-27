<%-- 
    Document   : invoiceSearch
    Created on : Jul 28, 2015, 9:59:44 AM
    Author     : praveen<pkatru@miraclesoft.com>
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
        <title>ServicesBay :: Invoice Search</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value='/includes/css/accountDetails/details.css'/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/account/formVerification.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script language="JavaScript" type="text/javascript" src="<s:url value="/includes/js/general/ProfilePage.js"/>" ></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>

        <script>
            var pager;
            $(document).ready(function(){

                var paginationSize = 10;
                pager = new Pager('InvoiceTable', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                pager.showPage(1);
            });

        </script>
    </head>

    <body oncontextmenu="return false" onload="getPresentTimeYear()">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/>
                    </div>
                </div>

            </header>
            <div id="main">
                <div class="container">
                    <div class="row">
                        <!-- Main Content-->
                        <s:include value="/includes/menu/LeftMenu.jsp"/>
                        <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="">
                            <!-- Add Form Area -->
                            <div class="col-sm-12">
                                <div class="" id="profileBox" style="float: left; margin-top: 15px; margin-bottom: 20px">
                                    <!-- Add Form Header-->
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font color="#ffffff">Invoice Search</font>
                                                <i id="updownArrow" onclick="toggleContent('doSearchInvoice')" class="fa fa-minus"></i> 

                                            </h4>
                                        </div>
                                    </div> 
                                    <span id="invoiceValidation"></span>             
                                    <s:form action="doSearchInvoice" theme="simple">
                                        <div class="col-sm-12">
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec">Month</label>
                                                <s:select id="invoiceMonth" name="invoiceMonth" cssClass="SelectBoxStyles form-control " theme="simple" list="#@java.util.LinkedHashMap@{'0':'All','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  onfocus="clearErrosMsgForGrouping()" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec">Year</label>
                                                <s:textfield id="invoiceYear" name="invoiceYear" cssClass=" form-control " onkeypress="return invoceYear(event)" value="%{invoiceYear}"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec">Resource&nbsp;Name</label>
                                                <s:textfield id="invoiceResource" name="invoiceResource" placeholder="Resource Name" cssClass=" form-control " maxLength="60" />

                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Name</label>
                                                <s:textfield id="invVendor" name="invVendor" placeholder="Vendor Name" cssClass="form-control " maxLength="60"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                <s:if test="#session.typeOfUsr=='VC'">
                                                    <s:select id="invoiceStatus" name="invoiceStatus" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'All':'All','created':'Created','submitted':'Submitted','approved':'Approved','rejected':'Rejected','paid':'Paid'}" />
                                                </s:if>
                                                <s:else>
                                                    <s:select id="invoiceStatus" name="invoiceStatus" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'All':'All','submitted':'Submitted','approved':'Approved','rejected':'Rejected','paid':'Paid'}" />
                                                </s:else>
                                            </div>


                                            <div class="col-sm-2 pull-right">
                                                <label class="labelStylereq" style="color:#56a5ec"></label><br>
                                                <s:submit id="invoiceSearch" type="button" value="Search" cssClass="add_searchButton form-control" cssStyle="margin:5px 0px;"><i class="fa fa-search"></i>&nbsp;Search</s:submit> 
                                                </div>
                                                <div class="col-sm-2 pull-right">
                                                    <label class="labelStylereq" style="color:#56a5ec"></label><br>
                                                <s:if test="#session.typeOfUsr=='VC'">
                                                    <s:submit id="generateInvoice" type="button" value="Generate" cssClass="add_searchButton form-control generate_btn InvoiceGenerationOverlay_popup_open fa fa-building-o" cssStyle="margin:5px 0px;" onclick="return generateInvoiceOverlay()"/>
                                                </s:if>

                                            </div>
                                        </div>
                                    </s:form>
                                    <div id="loadingInvoiceSearch" class="loadingImg">
                                        <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                    </div>
                                    <div class="row"></div>
                                    <div class="col-lg-12">
                                        <table id="InvoiceTable" class="responsive CSSTable_task" border="5"cell-spacing="2">

                                            <tbody>
                                                <tr>
                                                    <th>User&nbsp;Name</th>
                                                    <th>Customer&nbsp;Name</th>
                                                    <th>Invoice Date</th>
                                                    <th>Total Hrs.</th>
                                                    <th>Total Amt</th>
                                                    <th>Status</th>
                                                    <th>Delete</th>
                                                </tr>

                                                <s:if test="invoiceVto.size==0">
                                                    <tr>
                                                        <td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                    </tr>
                                                </s:if>
                                                <s:iterator  value="invoiceVto">
                                                    <!--build url TO goto Account Details-->
                                                    <s:url var="goInvoice" action="goInvoiceEditDetails.action">
                                                        <s:param name="invoiceId"><s:property value="invoiceId"></s:property></s:param>
                                                        <s:param name="usrId"><s:property value="usr_id"></s:property></s:param>
                                                    </s:url>
                                                    <tr>
                                                        <td> <s:a href='%{#goInvoice}'><s:property value="userName"></s:property></s:a></td>
                                                        <td><s:property value="custName"></s:property></td>
                                                        <td><s:property value="invoiceDate"></s:property></td>
                                                        <td><s:property value="totalHrs"></s:property></td>
                                                        <td><s:property value="totalAmt"></s:property></td>
                                                        <td><s:property value="status"></s:property></td>

                                                            <td>

                                                            <s:if test="status=='Created' || status=='Rejected'">
                                                                <s:url var="myUrl" action="deleteInvoice.action">
                                                                    <s:param name="invoiceId"><s:property value="invoiceId"></s:property></s:param>

                                                                </s:url>
                                                                <%--<s:a href="%{#myUrl}"><img src="<s:url value="/includes/images/delete.png"/>" height="25" width="25"></s:a>--%>
                                                                <a href="#" onclick="deleteInvoice('<s:property value="invoiceId"/>');" id="deleteInvoice" ><img src="<s:url value="/includes/images/delete.png"/>" height="25" width="25"></a>
                                                                </s:if>
                                                                <s:else>
                                                                <img src="<s:url value="/includes/images/delete.png"/>" height="25" width="25" style="opacity: 0.5">
                                                            </s:else>

                                                        </td>

                                                    </tr>
                                                </s:iterator>
                                            </tbody> 
                                        </table>
                                        <br/>
                                        <s:if test="invoiceVto.size > 0">
                                            <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                    <option>10</option>
                                                    <option>15</option>
                                                    <option>25</option>
                                                    <option>50</option>
                                                </select>
                                                Invoice per Page
                                            </label>
                                        </s:if>
                                        <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                        <script type="text/javascript">
                                            var pag = new Pager('InvoiceTable', 10); 
                                            pag.init(); 
                                            pag.showPageNav('pag', 'pageNavPosition'); 
                                            pag.showPage(1);
                                        </script>
                                    </div>
                                </div>
                            </div><!-- Add Form-->
                        </div>
                    </div>
                </div>

                <div id="InvoiceGenerationOverlay_popup">

                    <div id="InvoiceGenerationOverlay">
                        <div class="backgroundcolor">
                            <table>
                                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Generate&nbsp;Invoice&nbsp; </font></h4></td>
                                <span class="pull-right"> <h5 ><a href="">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="InvoiceGenerationOverlay_popup_close" onclick="closeInvoiceOverlay()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                            </table>
                        </div><div>
                            <span><invoiceGenerarionMessage></invoiceGenerarionMessage></span></div>
                        <div class="col-lg-12">
                            <div class="col-lg-6">
                                <label class="labelStylereq" style="color:#56a5ec">Month</label>
                                <s:select id="invoiceMonthOver" name="invoiceMonthOver" cssClass="SelectBoxStyles form-control "    theme="simple" list="#@java.util.LinkedHashMap@{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  onfocus="clearErrosMsgForGrouping()" />
                            </div>
                            <div class="col-lg-6">
                                <label class="labelStylereq" style="color:#56a5ec">Year</label>
                                <s:textfield id="invoiceYearOver" name="invoiceYearOver" cssClass=" form-control "  onkeypress="return invoceOverlayYear(event)"/>
                            </div>

                        </div>
                        <div class="col-lg-12">
                            <div class="col-lg-6">
                                <br>
                                <s:checkbox id="resourceAll" name="resourceAll" label="All Employees" list="#@java.util.LinkedHashMap@{'1':'All'}" value="false"/>
                            </div>
                            <div class="col-lg-6" id="OverResourceName">
                                <label class="labelStylereq" style="color:#56a5ec">Resource&nbsp;Email</label>
                                <s:textfield id="resourceNameOver" name="resourceNameOver" placeholder="Resource Email" cssClass="form-control" maxLength="60"/>
                            </div>
                        </div>

                        <div class="col-lg-12">
                            <div class="col-lg-6 pull-right">
                                <label class="" style="color:#56a5ec"></label>
                                <s:submit  id="invoiceGenerateOverlay" cssClass="cssbutton fa fa-building-o" value="Generate" type="button" onclick="return generateInvoice()"/>

                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                // alert(recordPage)
                $('#InvoiceTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#InvoiceTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>
