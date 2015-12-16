<%-- 
    Document   : consultant_login
    Created on : May 18, 2015, 10:20:03 PM
    Author     : praveen<pkatru@miraclesoft.com>
--%>


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<!DOCTYPE html>
<html>
    <head>


        <!-- new styles -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: consultant Login Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <!-- end of new styles -->
        <!-- start of js -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/jquery-1.8.2.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <!-- end of js -->

    </head>
    <body>
        <header id="header"><!--header-->
            <div class="header_top" id="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->

        <section id="form"><!--form-->
            <div class="container">
                <div class="row">

                    <div id="slide">
                        <div id="forgetoverlay" >
                            <div style="background-color: #DE9E2F;width: available">
                                <table>
                                    <tr ><td style="width:100%" align="left" colspan="2"><h4 style="font-family:Alike Angular"><font color="#ffffff">&nbsp;&nbsp;Password Retrieval </font></h4></td>
                                    </tr><span class="pull-right"><h5><a class="slide_close" href=""><img src="<s:url value="/includes/images/close.jpg"/>" height="25" width="25"></a></h5></span>

                                </table> 
                            </div>
                            <br><div style="margin: 10px;font-family:Alike Angular">
                                <p><h4>Forgot your password?</h4></p>
                                <p> Type your Email ID in the field bellow to receive your password by E-Mail</p>
                                <center>
                                    <s:form id="forgotPassword" name="forgotPassword" >
                                        <input type="email" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" placeholder="Valid Email Address" id="forgotEmailId" name="forgotEnailId" required= "required"/>
                                        &nbsp;<input style="margin:10px" type="button" class="passwordButton" value="Send E-mail" name="FPass" id="FPass" onclick="return forgotPassword();"/>
                                        <%--<button onclick="forgotPassword();">ResetPassword</button>--%>
                                        </br>
                                        <%-- <div id="Loading" style="width: auto;display: none;"/> --%>
                                        <span id="Loading" style="color:red; width:auto"></span>
                                        <span id="resultMessage" style="width: auto"/>
                                    </s:form>
                                </center>
                                <br><br>
                            </div>
                        </div> 

                        <!--<button class="slide_close btn btn-default">Close</button>-->
                    </div>
                    <!-- Start Special Centered Box -->
                    <div class="col-sm-4 col-sm-offset-1" id="col-sm-4">
                        <div class="login-form" ><!--login form-->
                            <h2>Login to your account</h2>
                            <s:form action="/recruitment/consultantLogin/loginCheckConsultant.action?"  method="post" name="userLoginForm" id="userLoginForm" > 
                                <input type="email" placeholder="Email" name="emailId" id="emailId" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required data-error-message="LoginId is required!" tabindex="1"/>
                                <input type="hidden" name="consultant_login" value="CL"/>
                                <input type="hidden" name="org_id" value="10004">
                                <input type="password" placeholder="password"  name='password' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password" title="Must be at least 6 characters long, and contain at least one number, one uppercase and one lowercase letter" required data-type="Password" tabindex="2" />

                                <p id="wrapper"><a  class="slide_open" href=""><font style="color: #3498DB">Forgot Password</font></a></p>

                                <button type="submit" >LogIn</button>

                            </s:form>
                            <% if (request.getAttribute("errorMessage") != null) {
                                    out.println(request.getAttribute("errorMessage"));
                                }%>
                        </div><!--/login form-->
                    </div>


                </div>
            </div>
        </section><!--/form-->


        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>

        </footer><!--/Footer-->
<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script>
            $(document).ready(function () {

                $('#slide').popup({
                    focusdelay: 400,
                    outline: true,
                    vertical: 'top'
                });
                $(function(){
                    $("#FPass").click(function(){
                        // alert('clicked!');
                        forgotPassword();
                    });
                });
   

            });
        </script>


<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>




    </body>
</html>
