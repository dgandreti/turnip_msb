<%-- 
    Document   : assignAccounts
    Created on : Jul 15, 2015, 1:53:37 AM
    Author     : miracle
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Assigned Accounts Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
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
                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <font color="#ffffff">Assign Accounts</font>
                                                </h4>
                                            </div>
                                        </div>
                                        <!-- content start -->
                                        <div class="col-sm-12">
                                            <form autocomplete="off">
                                                <s:hidden id="toCSRID" name="toCSRID"/>
                                                <s:hidden id="fromCSRID" name="fromCSRID"/>

                                                <span id="validationMessage"></span>
                                                <div class="row">    
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Accounts</label>
                                                        <s:select id="typeTransfer" name="typeTransfer" value="" cssClass="SelectBoxStyles form-control" theme="simple" list="#@java.util.LinkedHashMap@{'TA':'Transfer Accounts','CA':'Copy Accounts'}" onchange="setTransferCopy();" tabindex="1" />
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">From CSR</label>
                                                        <s:textfield cssClass=" form-control" name="fromCSR" placeholder="From CSR"  id="fromCSR" tabindex="2"  onkeyup="return getCSR();" onfocus="return removeErrorMsg();" maxLength="60" />
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">To CSR</label>
                                                        <s:textfield cssClass=" form-control" name="toCSR" placeholder="To CSR"  id="toCSR" tabindex="3"  onkeyup="return getToCSR();" onfocus="return removeErrorMsg();" maxLength="60" />
                                                    </div>
                                                </div>
                                        </div>  
                                        <div class="inner-reqdiv-elements">  
                                            <div class="row">
                                                <div class="col-sm-2 pull-right">

                                                    <s:submit type="button" cssClass="add_searchButton form-control"
                                                              id="transfer" value="Next" cssStyle="margin:5px 0px;" onclick="return transferAccounts();" tabindex="4" >&nbsp Next &nbsp;<i class="fa fa-arrow-right"></i></s:submit>
                                                </div>
                                            </div> 
                                        </div>  

                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>
