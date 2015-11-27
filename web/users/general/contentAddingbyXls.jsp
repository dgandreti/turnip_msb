<%-- 
    Document   : contentAddingbyXls
    Created on : Aug 28, 2015, 7:29:56 PM
    Author     : MIRACLE
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Miracle Service Bay :: Employee Search Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <%-- <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
             <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>



    </head>
    <body style="overflow-x: hidden">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div>

        </header>

        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Upload Selected Content</font>

                                            </h4>
                                           
                                        </div>

                                    </div>
                                    <!-- content start -->
                                    <div class="col-sm-12">
                                        <s:form action="getCellContentValues" theme="simple" method="POST" enctype="multipart/form-data">
                                            <s:hidden name="filePath" value="%{filePath}"/>
                                            <div class="col-lg-3">
                                                <label class="labelStyle" id="labelLevelStatusReq">Account&nbsp;Name: </label>
                                                <s:select cssClass="form-control SelectBoxStyles" name="columnValue" list="%{columnsMap}"></s:select>
                                                <s:hidden name="path" value="%{path}"/>
                                            </div>
                                            <div class="col-lg-3">
                                                <label class="labelStyle" id="labelLevelStatusReq">Account&nbsp;Url: </label>
                                                <s:select cssClass="form-control SelectBoxStyles" name="accUrl" list="%{columnsMap}"></s:select>

                                            </div>

                                            <div class="col-lg-3">
                                                <label class="labelStyle" id="labelLevelStatusReq">Account&nbsp;Type: </label>
                                                <s:textfield cssClass="form-control" name="accType" value="%{accountType}" readonly="true"></s:textfield>
                                            </div>

                                            <div class="col-lg-3">
                                                <label class="labelStyle" id="labelLevelStatusReq">Mail&nbsp;Extention: </label>
                                                <s:select cssClass="form-control SelectBoxStyles" name="mailExt" list="%{columnsMap}"></s:select>
                                            </div>
                                            <h4><b>Account Address</b></h4>
                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"> Address 1 </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="accAddress1" list="%{columnsMap}"></s:select>
                                                        </span></div>
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"> Address 2 </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="accAddress2" list="%{columnsMap}"></s:select>
                                                        </span></div>

                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"><%--<span class="accDetailsError">*</span>--%> City </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="accCity" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"> Zip </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="zip" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2">Country </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="accCountry" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>

                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"> State </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="accState" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>

                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label  class="labelStyle2"> Phone </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="phone" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label  class="labelStyle2">Fax </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="accFax" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                </div>

                                                <br/>
                                            </div>
                                            <h4><b>Basic Information</b></h4>
                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"> Industry</label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="industry" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label  class="labelStyle2">Region </label>   
                                                            <s:select cssClass="form-control SelectBoxStyles" name="region" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label   class="labelStyle2">Territory </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="territory" list="%{columnsMap}"></s:select>
                                                        </span></div>

                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2"> No. of Employees </label>     
                                                            <s:select cssClass="form-control SelectBoxStyles" name="noOfEmp" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="row">

                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2">Tax ID </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="taxId" list="%{columnsMap}"></s:select>
                                                        </span></div>
                                                    <!--Linked to State and Country-->
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2">Stock Symbol </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="stockSymbol" list="%{columnsMap}"></s:select>
                                                        </span></div>
                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label class="labelStyle2">Revenue </label>     
                                                            <s:select cssClass="form-control SelectBoxStyles" name="revenue" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>


                                                    <div class="col-sm-3">
                                                        <span>
                                                            <label  class="labelStyle2">Description </label>
                                                            <s:select cssClass="form-control SelectBoxStyles" name="description" list="%{columnsMap}"></s:select>
                                                        </span>
                                                    </div>

                                                    <div class="col-lg-3">
                                                        <br><s:submit cssClass="cssbutton" value="Load" onclick="return contentAdding()"/>
                                                    </div>


                                                </s:form>
                                                <br>
                                                <%--<s:submit cssClass="css_button" value="show"/><br>--%>

                                            </div>
                                        </div>
                                    </div>
                                    <%--close of future_items--%>
                                </div>
                            </div>
                        </div>

                    </div>



                    <!-- content end -->
                    </section><!--/form-->
                    <footer id="footer"><!--Footer-->
                        <div class="footer-bottom" id="footer_bottom">
                            <div class="container">
                                <s:include value="/includes/template/footer.jsp"/>
                            </div>
                        </div>

                    </footer>
                    <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

                    <!--/Footer-->

                    </body>
                    </html>

