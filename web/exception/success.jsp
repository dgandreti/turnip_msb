<%-- 
    Document   : login
    Created on : Feb 3, 2015, 4:04:37 PM
    Author     : Nagireddy
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>


        <!-- new styles -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Success Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
          <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/general.css"/>">
        <!-- end of js -->
       <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
         <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
    </head>
    <body>
        <div id="wrap">
        <header id="header"><!--header-->
            <div class="header_top" id="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->
        <div id="main">
        <section id="form"><!--form--> 
          
            <p align="center" class="success" href=""><img src="<s:url value="/includes/images/success.jpg"/>" height="150" width="150"</p><br><br>
            <p align="center" class="success1">
                 <% if(request.getAttribute("resultMessage") != null){
                                                        out.println(request.getAttribute("resultMessage"));
                                                    }%>
                
            </p>       
        </section><!--/form-->
         <div class="login-form modal fade" id="myLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" id="Form_login" role="document" >
                                <div class="modal-content">
                                    <div class="modal-header " style="background-color: rgb(52,152,219);background-color: rgba(52,152,219,0.8);  border-top-right-radius: 6px; border-top-left-radius: 6px;"> 
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel" style="color:white">Login to your account</h4>
                                    </div>
                                    <div class="modal-body">
                                        <% if (request.getAttribute("errorMessage") != null) {
                                        %>
                                        <script type="text/javascript">
                                             
                                            $('#myLogin').modal('show');
                                  
                                        </script>  
                                        <% out.println(request.getAttribute("errorMessage"));
                                        %>
                                        <%
                                            }%>
                                        <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" > 
                                            <input type="email" placeholder="Email" class="frgt_pwd" name="emailId" id="emailId" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required data-error-message="LoginId is required!" tabindex="1"/>

                                            <input type="password" placeholder="Password" class="frgt_pwd"  name='password' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password" title="Must be at least 6 characters long, and contain at least one number, one uppercase and one lowercase letter" required data-type="Password" tabindex="2" />

                                            <p id="wrapper" class="frgt_pwd"><a  class="slide_open" href="" id="closeLogin" data-dismiss="modal" data-toggle="modal" data-target="#forgotPwd"><font class ="fgtPwd">Forgot Password</font></a></p>
                                            <div class="LoginButton">
                                                <button type="submit" >LogIn</button>
                                            </div>
                                        </s:form>



                                    </div></div></div></div>
                              <div id="slide" style="display: none;">
                            <div id="forgetoverlay" >
                                <div style="width: available;border-top-left-radius: 12px;border-top-right-radius: 12px;background-color: rgb(52, 152, 219);">
                                    <table>
                                        <tr ><td style="width:100%" align="left" colspan="2"><h4 style="font-family:Alike Angular">
                                                    <font color="white" style="font-weight: bold;font-size: 22px;">&nbsp;&nbsp;Forgot your password </font></h4></td>
                                        </tr><span class="pull-right"><h5><a class="slide_close" href=""><img src="<s:url value="/includes/images/close_trans.png"/>" height="25" width="25"></a></h5></span>

                                    </table> 
                                </div>
                                <hr style="margin: 0px;">
                                <div style="font-family: Alike Angular; margin-top: 10px; margin-left: 10px; margin-right: 10px;">

                                    <p> Enter the Email address of your account to reset your password</p>
                                    <span id="Loading" style="color:red; width:auto"></span>
                                    <span id="resultMessage" style="width: auto"></span>
                                    <center>
                                        <s:form id="forgotPassword" name="forgotPassword" >

                                            <input type="email"  class="frgt_pwd" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" placeholder="Valid Email Address" id="forgotEmailId" name="forgotEnailId" required= "required"/>
                                            &nbsp;<input style="margin:4px" type="button" class="passwordButton" value="Send E-mail" name="FPass" id="FPass" onclick="return forgotPassword();"/>


                                            <%--<button onclick="forgotPassword();">ResetPassword</button>--%>

                                            <%-- <div id="Loading" style="width: auto;display: none;"/> --%>

                                        </s:form>
                                    </center>
                                    <br><br>
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

        </footer><!--/Footer-->
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
   
                $(".slide_close").click(function(){
                    // alert('clicked!');
                    document.getElementById('resultMessage').innerHTML = "";
                });
            });
            /*$("[data-toggle]").click(function() {
		  
                 var toggle_el = $(this).data("toggle");
                 var duration = 500;
                  var effect = 'slide';
                  var options = { direction: 'right' };
                  $("#menu_section").toggle();
                  $(toggle_el).toggle();
                  $('#menu_section_details').toggle();
             });*/
        </script>
    </body>
</html>
