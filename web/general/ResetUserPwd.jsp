<%-- 
    Document   : ResetUserPassword
    Created on : Feb 3, 2015, 7:50:23 PM
    Author     : Nagireddy
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
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
        <title>ServicesBay :: Reset User Password Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>"><!--this is for all css in profile view -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <!-- end of new styles -->
        <style type="text/css">
            label[for="email"] {
                color: #56a5ec;
            }label[for="pwd1"] {
                color: #56a5ec;
            }label[for="pwd2"] {
                color: #56a5ec;
            }


        </style>
    </head>
    <body oncontextmenu="return false">
        <div id="wrap">  
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">

                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div><!--/header_top-->

            </header><!--/header-->

            <div id="main">
                <section id="generalFormDesign"><!--form-->
                    <div class="container">
                        <div class="col-sm-12">

                            <s:include value="/includes/menu/LeftMenu.jsp"/> 

                            <div class="col-sm-12 col-md-8 col-lg-7 right_block">
                                <div class="features_items"><!--features_items-->

                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <font color="#ffffff">Reset user password</font>
                                                </h4>
                                            </div>
                                        </div>
                                        <!-- Start Special Centered Box -->
                                        <div class="col-sm-12 password_box"><!--login form-->
                                            <form id="ResetPassword" action="changeUserPassword" autocomplete="off">
                                                <font style="color: green;font-size: 12px; margin : 7px;" id="clear"><s:property value="#request.resultMessage"/></font>
                                               
                                                <span><resetMessage></resetMessage></span>
                                              
                                                <div class=" required"> 
                                                    <div class="col-lg-5  col-sm-5 req_margin">
                                                        <label id="labelLevelStatusReq" >Email</label> 
                                                    </div>
                                                    <div class="col-lg-6 col-sm-6 req_margin">
                                                        <s:textfield id="email" name="emailId" autocomplete="off" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}"  cssClass="form-control"  placeholder="Email Id" required="true" oninvalid="setCustomValidity('Must be valid email')"   onchange="try{setCustomValidity('')}catch(e){}" onblur="return checkEmailIdExistance();" tabindex="1" maxLength="60"/>
                                                    </div>
                                                    <div class="col-lg-5  col-sm-5 req_margin"> 
                                                        <label id="labelLevelStatusReq">New Password</label> 
                                                    </div>
                                                    <div class="col-lg-6 col-sm-6  req_margin">
                                                        <s:password name="newpwd" id="pwd1" placeholder="Password" autocomplete="off" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" cssClass="form-control" onchange="form.cnfrmpwd.pattern=this.value"    title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"   tabindex="2"/>
                                                    </div>
                                                    <div class="col-lg-5 col-sm-5 req_margin">
                                                        <label id="labelLevelStatusReq">Confirm Password</label> 
                                                    </div>
                                                    <div class="col-lg-6 col-sm-6 req_margin">
                                                        <s:password name="cnfrmpwd" id="pwd2"  autocomplete="off" placeholder="Confirm Password" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"  cssClass="form-control"  oninvalid="setCustomValidity('Please enter the same Password as above')"   onchange="try{setCustomValidity('')}catch(e){}" tabindex="3"/>
                                                    </div> 
                                                </div>
                                                <div class="col-sm-11" >
                                                    <div class=" col-sm-13 req_margin ">
                                                        <div align="right"><button type="submit" id="submit" value="Submit" tabindex="4" class="cssbutton req_margin align_left fa fa-check-circle-o">&nbsp;Submit</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- content start -->
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
<script>
        $("#clear").show().delay(5000).fadeOut();
    </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>