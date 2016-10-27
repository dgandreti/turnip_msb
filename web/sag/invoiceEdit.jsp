<%-- 
    Document   : invoiceEdit
    Created on : Jul 29, 2015, 8:18:12 PM
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
        <title>ServicesBay :: Invoice Details Page </title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value='/includes/css/accountDetails/details.css'/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/account/formVerification.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script language="JavaScript" type="text/javascript" src="<s:url value="/includes/js/general/ProfilePage.js"/>" ></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
    </head>

    <body oncontextmenu="return false" onload="onloadDate();">
        <div id="wrap">
            <div id="generalForm">
                <header id="header"><!--header-->
                    <div class="header_top"><!--header_top-->
                        <div class="container">
                            <s:include value="/includes/template/header.jsp"/>
                        </div>
                    </div>

                </header>
                <div id="main">
                    <div id="hoursOverlay_popup" class="hoursOverlay" style="width:267px;">
                        <div id="hoursInfoBox" >

                            <div class="overlayOrButton_color">
                                <table>
                                    <tr><td style=""><h4>
                                                <font color="#ffffff">&nbsp;Working&nbsp;Hours/Day</font>

                                            </h4></td>
                                    <span class=" pull-right"><h5><a href="" class="hoursOverlay_popup_close" onclick="closeEmpOverlay()"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
                                </table>
                            </div>
                            <div style="margin: 10px">
                                <table id="hoursOverlayTable" class="CSSTable_task  responsive" border="2"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;" >
                                    <tr>
                                        <th>     <center>  Work&nbsp;Date</center></th>
                                    <th>     <center>  Working&nbsp;Hours</center></th>
                                    </tr>
                                </table > 
                                <div>

                                    <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                            <option>5</option>
                                            <option>10</option>
                                            <option>15</option>
                                            <option>25</option>
                                            <option>50</option>
                                        </select>&nbsp;per&nbsp;page

                                    </label>


                                </div> 
                            </div>
                        </div>
                    </div>

                    <div class="container">
                        <div class="row">
                            <!-- Main Content-->
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="">
                                <!-- Add Form Area -->
                                <div class="col-lg-12">
                                    <div class="" id="profileBox" style="float: left; margin-top: 15px; margin-bottom: 20px">
                                        <!-- Add Form Header-->
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                    <font color="#ffffff">Invoice Edit</font>
                                                    <s:url var="myUrl" action="getInvoice.action">
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}' id="invoiceBackButton"><i class="fa fa-undo"></i></s:a></span>
                                                    </h4>
                                                </div>
                                            </div> 
                                        <s:form action="doEditInvoiceDetatils" theme="simple">
                                            <span id="invoiceValidation"><errorMsg></errorMsg></span>
                                            <s:hidden name="invoiceVTOClass.invoiceId" value="%{invoiceVTOClass.invoiceId}"/>
                                            <div class="col-sm-12">
                                                <s:if test="invoiceVTOClass.status=='Approved' || invoiceVTOClass.status=='Rejected'|| invoiceVTOClass.status=='Paid'">
                                                    <div class="col-sm-3">
                                                    </s:if>
                                                    <s:else>
                                                        <div class="col-sm-6">
                                                        </s:else>
                                                        <label class="labelStylereq" style="color:#56a5ec">Resource Name</label>
                                                        <s:textfield id="resourceName" name="" value="%{invoiceVTOClass.userName}" cssClass=" form-control " tabindex="1" disabled="true" />
                                                    </div>
                                                    <s:if test="invoiceVTOClass.status=='Approved' || invoiceVTOClass.status=='Rejected' || invoiceVTOClass.status=='Paid'">
                                                        <div class="col-sm-3">
                                                        </s:if>
                                                        <s:else>
                                                            <div class="col-sm-6">
                                                            </s:else>

                                                            <label class="labelStylereq" style="color:#56a5ec">Organization</label>
                                                            <s:textfield id="orgName" name="" value="%{invoiceVTOClass.custName}" cssClass=" form-control " tabindex="2"  disabled="true"/>
                                                        </div>
                                                        <s:if test="invoiceVTOClass.status=='Approved' || invoiceVTOClass.status=='Rejected' || invoiceVTOClass.status=='Paid'">
                                                            <div class="col-sm-3">
                                                                <s:if test="invoiceVTOClass.status!='Rejected'">
                                                                    <label class="labelStylereq" tabindex="3" style="color:#56a5ec">Approver</label>
                                                                </s:if>
                                                                <s:else>
                                                                    <label class="labelStylereq" tabindex="4" style="color:#56a5ec">Rejecter</label>
                                                                </s:else>
                                                                <s:textfield id="approverName" name="" tabindex="5" value="%{invoiceVTOClass.custApprName}" cssClass=" form-control "  disabled="true"/>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <s:if test="invoiceVTOClass.status!='Rejected'">
                                                                    <label class="labelStylereq" tabindex="6" style="color:#56a5ec">Approver Date</label>
                                                                </s:if>
                                                                <s:else>
                                                                    <label class="labelStylereq" tabindex="7" style="color:#56a5ec">Rejecter Date</label>
                                                                </s:else>
                                                                <s:textfield id="approverDate" name="" tabindex="8" value="%{invoiceVTOClass.custApprDate}" cssClass=" form-control "  disabled="true"/>
                                                            </div>
                                                        </s:if>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Month</label>
                                                            <s:select id="invoiceMonth" tabindex="9" name="invoiceVTOClass.invoicemonth" value="%{invoiceVTOClass.invoicemonth}" cssClass="SelectBoxStyles form-control " theme="simple" list="#@java.util.LinkedHashMap@{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"   />
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Year</label>
                                                            <s:textfield id="invoiceYear" tabindex="10" name="invoiceVTOClass.invoiceyear" value="%{invoiceVTOClass.invoiceyear}" cssClass=" form-control "  onkeypress="return invoceYear(event)"/>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                            <s:if test="#session.typeOfUsr=='VC'">
                                                                <s:if test="invoiceVTOClass.status=='Created'">
                                                                    <s:select id="invoiceStatus" tabindex="11" name="invoiceVTOClass.status" value="%{invoiceVTOClass.status}" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'Created':'Created','Submitted':'Submitted'}"  />
                                                                </s:if>
                                                                <s:elseif test="invoiceVTOClass.status=='Submitted' || invoiceVTOClass.status=='Rejected'">
                                                                    <s:select id="invoiceStatus" tabindex="12" name="invoiceVTOClass.status" value="%{invoiceVTOClass.status}" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'Submitted':'Submitted','Rejected':'Rejected'}"  />
                                                                </s:elseif>
                                                                <s:else>
                                                                    <s:select id="invoiceStatus" tabindex="13" name="invoiceVTOClass.status" value="%{invoiceVTOClass.status}" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'Created':'Created','Submitted':'Submitted','Approved':'Approved','Rejected':'Rejected','Paid':'Paid'}"  disabled="true" />
                                                                </s:else>
                                                            </s:if>
                                                            <s:else>
                                                                <s:if test="invoiceVTOClass.status=='Submitted'">
                                                                    <s:select id="invoiceStatus" tabindex="14" name="invoiceVTOClass.status" value="%{invoiceVTOClass.status}" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'Submitted':'Submitted','Approved':'Approved','Rejected':'Rejected'}"  onchange="return setPaybleDate();"/>
                                                                </s:if>
                                                                <s:elseif test="invoiceVTOClass.status=='Approved'">
                                                                    <s:select id="invoiceStatus" tabindex="15" name="invoiceVTOClass.status" value="%{invoiceVTOClass.status}" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'Approved':'Approved','Paid':'Paid'}"  onchange="return setPaybleDate();"/>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <s:select id="invoiceStatus" tabindex="16" name="invoiceVTOClass.status" value="%{invoiceVTOClass.status}" cssClass="SelectBoxStyles form-control "  list="#@java.util.LinkedHashMap@{'Submitted':'Submitted','Approved':'Approved','Rejected':'Rejected','Paid':'Paid'}"  onchange="return setPaybleDate();" disabled="true"/>
                                                                </s:else>
                                                            </s:else>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec"><a  href="#" class="hoursOverlay_popup_open"  onclick="OverlayForWorkingHours();" >Total Hours</a></label>
                                                            <s:textfield id="totalHours" tabindex="17" name="invoiceVTOClass.totalHrs" value="%{invoiceVTOClass.totalHrs}" cssClass=" form-control " maxLength="15" readonly="true" />
                                                        </div>
                                                        <s:hidden id="usrId" name="usrId" value="%{usrId}"/>
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Total Amount</label>
                                                            <s:textfield id="totalAmt" tabindex="18" name="invoiceVTOClass.totalAmt" value="%{invoiceVTOClass.totalAmt}" cssClass=" form-control " maxLength="11" readonly="true" />
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Net Terms</label>
                                                            <s:select id="netTerms" tabindex="19" name="invoiceVTOClass.netTerms" value="%{invoiceVTOClass.netTerms}" cssClass="SelectBoxStyles form-control" list="#@java.util.LinkedHashMap@{'15':'15','30':'30','45':'45','60':'60','75':'75','90':'90'}" disabled="true"  />
                                                        </div>
                                                        <s:if test="invoiceVTOClass.status!='Created'">
                                                            <s:if test="#session.typeOfUsr=='VC' ">
                                                                <s:if test="invoiceVTOClass.status!='Submitted'">
                                                                    <div class="col-sm-3">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Payment Date</label>
                                                                        <s:textfield id="paymentDate" name="" tabindex="20" value="%{invoiceVTOClass.paymentDate}"  cssClass=" form-control "  disabled="true"/>
                                                                    </div>
                                                                </s:if>
                                                            </s:if>
                                                            <s:else>
                                                                <div class="col-sm-3">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Payable Date</label>
                                                                    <s:textfield id="pamentDate" tabindex="21" name="invoiceVTOClass.paymentDate"  value="%{invoiceVTOClass.paymentDate}" cssClass=" form-control " readonly="true" />
                                                                </div>
                                                            </s:else>
                                                        </s:if>
                                                    </div>
                                                    <div class="col-sm-12">
                                                        <s:if test="(invoiceVTOClass.status=='Approved' && #session.typeOfUsr!='VC' )||invoiceVTOClass.status=='Paid'">
                                                            <div class="col-sm-3">
                                                                <label class="labelStylereq" style="color:#56a5ec">Pay Type</label>
                                                                <s:select id="paymentType" tabindex="22" name="invoiceVTOClass.payType" value="%{invoiceVTOClass.payType}" cssClass="SelectBoxStyles form-control"  list="#@java.util.LinkedHashMap@{'CH':'Cheque','FT':'Funds Transfer'}"/>
                                                            </div>
                                                            <s:if test="#session.typeOfUsr=='VC'">
                                                                <div class="col-sm-3">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Payment Trns.No</label>
                                                                    <s:textfield id="transNO" tabindex="23" name="" placeholder="Payment Trns.No." value="%{invoiceVTOClass.cheOrTransno}" cssClass=" form-control "  disabled="true"/>
                                                                </div>
                                                            </s:if>
                                                            <s:else>
                                                                <div class="col-sm-3 required">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Payment Trns.No</label>
                                                                    <s:textfield id="transNO" tabindex="23" placeholder="Payment Trns.No." name="invoiceVTOClass.cheOrTransno" value="%{invoiceVTOClass.cheOrTransno}" cssClass=" form-control" maxLength="15"/>
                                                                </div>
                                                            </s:else>
                                                            <s:if test="#session.typeOfUsr=='VC' ">
                                                                <div class="col-sm-3">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Paid Amount</label>
                                                                    <s:textfield id="paidAmt" tabindex="24" name="" value="%{invoiceVTOClass.paidAmt}"  cssClass=" form-control "  disabled="true" maxLength="11"/>
                                                                </div></s:if>
                                                            <s:else>
                                                                <div class="col-sm-3 required">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Paid Amount</label>
                                                                    <s:textfield id="paidAmt" tabindex="24" name="invoiceVTOClass.paidAmt" value="%{invoiceVTOClass.paidAmt}"  cssClass=" form-control" onblur="getBalanceAmt()" onkeypress="return acceptNumbersOnly(event)" maxLength="11"/>
                                                                </div>  
                                                            </s:else>
                                                            <div class="col-sm-3">
                                                                <label class="labelStylereq" style="color:#56a5ec">Balance Amount</label>
                                                                <s:textfield id="balanceAmt" tabindex="25" name="invoiceVTOClass.balanceAmt" value="%{invoiceVTOClass.balanceAmt}" cssClass=" form-control " readonly="true" maxLength="11"/>
                                                            </div>


                                                        </s:if>

                                                    </div>
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-12">
                                                            <label class="labelStylereq" style="color:#56a5ec">Description</label>
                                                            <s:textarea id="description" tabindex="26" name="invoiceVTOClass.description" maxLength="200" placeholder="Description" value="%{invoiceVTOClass.description}" cssClass=" form-control"  />
                                                        </div>

                                                    </div>
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-10"></div>
                                                        <div class="col-sm-2 pull-right">
                                                            <s:if test="invoiceVTOClass.status!='Paid' ">

                                                                <s:if test="(invoiceVTOClass.status=='Created' && #session.typeOfUsr=='VC') || invoiceVTOClass.status=='Rejected' || invoiceVTOClass.status=='Submitted' ">
                                                                    <s:submit id="invoiceSave" type="button" tabindex="27" cssStyle="margin:5px 0px;" value="" cssClass="add_searchButton form-control"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                                </s:if>
                                                                <s:elseif test="#session.typeOfUsr!='VC'">

                                                                    <s:submit id="invoicePaid" type="button" tabindex="28" value="Paid" cssClass="add_searchButton form-control fa fa-money btn_order marqueeText" onclick="return getBalanceAmt();"/>
                                                                </s:elseif>
                                                            </s:if>

                                                        </div>
                                                    </s:form>



                                                </div>
                                            </div><!-- Add Form-->
                                        </div>
                                    </div>
                                </div>
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
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
                $('#hoursOverlayTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#hoursOverlayTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>
