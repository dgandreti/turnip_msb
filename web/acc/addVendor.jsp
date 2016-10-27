<%--
    Document   : AccountDetails
    Created on : May 3, 2015, 2:08:50 PM
    Author     : rama krishna<lankireddy@miraclesoft.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Add Vendor Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>


        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/addLeaveOverlay.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/requirementAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/techReviewAjax.js"/>"></script>
        <style>
            .required_label:after{
                content:" *";
                color: red;
            }
        </style>
        <script>

            $(document).ready(function() {
                $("#vendorTier").change(function() {
                   
                    $(".lb2").removeClass("required_label");
                    $(".lb1").addClass("required_label");
                });
                $('#PF').click(function() {
                  
                    $(".lb1").removeClass("required_label");
                    $(".lb2").addClass("required_label");

                if (document.getElementById('PF').checked) {
                    //alert("ugui")
                    $(".lb1").removeClass("required_label");
                } else {
                    $(".lb2").addClass("required_label");
                }
            });
            });
//            $('#isAgeSelected').click(function() {
//    $("#txtAge").toggle(this.checked);
//});
        </script>
    </head>
    <body oncontextmenu="return false" onload="doOnLoad();">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/>
                    </div>
                </div>

            </header>
            <%-- ------------MIDDLE -----------------------------------------%>
            <div id="main">
                <section id="generalForm"><!--form-->
                    <div class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <font color="#ffffff">Adding a Vendor For Customer</font>
                                                    <s:url var="myUrl" action="../acc/viewAccount.action">
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                        <s:param name="accFlag">venSearch</s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}' id="backToList"><i class="fa fa-undo"></i></s:a></span>
                                                    </h4>
                                                </div>
                                            </div>
                                            <label class="">Account Name : <font style="color: #FF8A14;"><s:property value="%{accountName}"/></font></label>
                                        <div class="inner-reqdiv-elements">
                                            <span><e></e></span>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <s:hidden name="accountSearchID" id="accountSearchID" value="%{accountSearchID}" />
                                                    <s:hidden name="vendorId" id="vendorId" />
                                                    <label class="labelStylereq required_label" style="color: #56a5ec">Vendor Name</label>
                                                    <s:textfield cssClass="form-control" id="vendorName" placeholder="Vendor Name"  name="vendorName" onkeyup="return getVendorNames();" autocomplete='off' maxLength="60"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="labelStylereq" style="color: #56a5ec">URL</label>
                                                    <s:textfield cssClass="form-control " id="vendorURL"  name="vendorURL" disabled="true" maxLength="60"/>
                                                </div>

                                                <div class="col-sm-3">
                                                    <label class="labelStylereq lb1 required_label" style="color: #56a5ec">Tier</label>
                                                    <s:select cssClass="SelectBoxStyles form-control " name="vendorTier" id="vendorTier" headerKey="0" headerValue="--select--" list="vendorTierMap"/>
                                                </div>
                                                <div class="col-sm-2">
                                                    <label class="labelStylereq lb2 required_label" style="color: #56a5ec">Head Hunter</label>
                                                    <s:checkbox name="PF" id="PF" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="inner-reqdiv-elements">
                                            <div class="inner-techReviewdiv-elements"><span id="validationMessage" /></div> 
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <label class="labelStylereq" style="color: #56a5ec">Comments</label>
                                                    <s:textarea cssClass="form-control" cssStyle="width=100%" name="vendorComments" placeholder="Comments" id="vendorComments"  onkeyup="checkCommentsChars(this)"/>
                                                </div>
                                            </div>
                                            <div class="charNum " id="JobcharNum" ></div>
                                            <div class="row">

                                                <div class="col-sm-2 pull-right">

                                                    <s:submit id="addVendorSave" type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" onclick="saveVendorTierDetails();" theme="simple"  ><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                    </div>
                                                </div>
                                            </div>
                                        <%--</s:form>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>            
            <%-- ------------MIDDLE -----------------------------------------%>
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
        <div style="display: none; position: absolute; top:186px;left:246px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>