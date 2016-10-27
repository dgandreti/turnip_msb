<%--
    Document   : opportunityDetails
    Created on : May 18, 2015, 4:01:39 PM
    Author     : Anton Franklin
--%>
<%@page import="com.mss.msp.opp.OpportunityVTO"%>
<%@page import="com.mss.msp.opp.OpportunityDataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServicesBay :: Opportunity Details Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/opportunity/opportunityDetails.css"/>">

        <%-- Javascript Files --%>
        <script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        
        <script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/opportunity/formValidation.js"/>"></script>

    </head>
    <body oncontextmenu="return false">
        <div id="wrap">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>

        </header>
                <div id="main">
        <section id="generalForm">
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/>

                    <div class="col-md-9 col-md-offset-0" style="background-color: #fff">
                        <div class="features_items">
                            <div class="col-lg-12">
                                <div class="" id="profileBox" style="float: left; margin-top:5px">

                                    <div class="backgroundcolor">
                                       <div class="panel-heading">
                                           <h4 class="panel-title">
                                           <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                               <font color="#ffffff">Opportunity Update Details</font>
                                            </h4>
                                       </div>
                                    </div>

                                    <!-- Form Starts Here-->
                                     <div>
                                         <s:form action="updateOpportunity" method="post" theme="simple" value="opportunity" onsubmit="return validateForm('oppName','oppDesc')">
                                            <table>
                                                <!--Account ID, Opportunity Type, Opportunity Name, Opportunity Comments-->
                                                  <tr>
                                                    <div class="inner-addtaskdiv-elements " >
                                                        <label class="labelStyle ">Opportunity Name </label><s:textfield id="oppName"  cssClass="oppTextField" name="opportunity.opportunityName" key="opportunity.opportunityName" value="%{opportunity.opportunityName}" onchange=""/>
                                                        <div style="color: red; width: auto" id="opportunityNameError"></div>
                                                    </div>
                                                  </tr>
                                                  <tr>
                                                    <div class="inner-addtaskdiv-elements " >
                                                        <s:hidden name="opportunityID" value="%{opportunitID}"/>
                                                        <label class="labelStyle ">Opportunity Description (200 Characters Max) </label><s:textarea id="oppDesc"  cssClass="oppTextArea" name="opportunity.opportunityDesc" key="opportunity.opportunityDesc" value="%{opportunity.opportunityDesc}" onchange=""/>
                                                        <div class="labelStyle" style="color: red; width: auto" id="opportunityDescError"></div>
                                                    </div>
                                                  </tr>
                                                  <tr>
                                                    <div class="inner-addtaskdiv-elements " >
                                                        <label class="labelStyle ">Opportunity Comments </label><s:textarea  cssClass="oppTextArea" name="opportunity.opportunityComments" key="opportunity.opportunityComments" value="%{opportunity.opportunityComments}" onchange=""/>
                                                        <label class="labelStyle" style="display: none; color: red; width: auto" id="opportunityCommentsError"></label>
                                                    </div>
                                                  </tr>
                                                  <tr>
                                                      <div style="text-align: right">
                                                          <s:submit cssClass="cssbutton" value="Update" theme="simple"/>
                                                      </div>
                                                  </tr>
                                            </table>
                                        </s:form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        </div>
    </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->

<script language="JavaScript" type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
