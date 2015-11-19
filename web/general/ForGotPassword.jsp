<%-- 
    Document   : login
    Created on : Feb 3, 2015, 4:04:37 PM
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
        <title>ServicesBay :: User Forgot password age</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <!-- end of new styles -->
        
        <!-- start of js -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        
       
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
                   
       <center><div class="col-lg-12" id="row"> 
     
         <h1>Set your new password</h1>
     
         </div>   </center>        
              
                 
                <!-- Start Special Centered Box -->
                    <div class="col-sm-4 col-sm-offset-4" id="col-sm-5">
                        <div class="login-form" ><!--login form-->
                           
                          <div class="jumbotron">  
                              
                               <s:form id="formCheckPassword" action="resetPassword">
                                   <input  class="form-control" type="text" value=<%= request.getParameter("emailId") %> disabled="true" placeholder="Email" kl_virtual_keyboard_secure_input="on" />
                                    <input type="hidden" id="emailId"  name="emailId" value=<%= request.getParameter("emailId") %> />
                                    <input type="password" placeholder="password"  required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password" onchange="form.conformpwd.pattern = this.value;" id="password" title="atleast one capital letter and a number must be there" required data-error-message="password is required!" tabindex="1"/>
                                    <input type="password" placeholder="confirm password"  id="conformpwd" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="conformpwd" required data-type="Password" title="must be same as password" tabindex="2" />
                                    <button type="submit" cssClass="cssbutton">ResetPassword</button>
                                </s:form>
                              
                              
                              
                                    <%--<s:form id="formCheckPassword" action="resetPassword">
     
                                 <input id="emailId" class="form-control" type="text" disabled=""  name="emailId" id="emailId" value="%{emailId}" placeholder="Email" kl_virtual_keyboard_secure_input="on" tabindex="2"/>
                                <s:textfield  class="form-control" type="text" readonly="true"  name="emailId" id="emailId" />
                               <%-- <input type="password" placeholder="password"  required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="password" onchange="form.pwd2.pattern = this.value;" id="password" title="atleast one capital letter and a number must be there" required data-error-message="password is required!" tabindex="1"/>

                                <input type="password" placeholder="confirm password"  id="confirmpass" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="cnfrmpwd" required data-type="Password" title="must be same as password" tabindex="1" />
                                <input type="submit" class="resetPasswordButton" type="submit" value="submit" /> 
                               <s:password name="password" id="password"/>
                               <s:password name="conformpwd" id="conformpwd"/>
                               <s:submit value="ResetPassword" />
                            </s:form>--%>
                               
                        </div><!--/login form-->
                    </div>
                    <div class="col-sm-1">
                      
                    </div>
                    </div>
                </div>
                    </div>
            </div>
        <!--   <div class="col-lg-6" >
        <h4>  your session will be closed within 2mins </h4>
        </div>-->
        </section><!--/form-->
        
</div>

        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                     <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>

        </footer><!--/Footer-->
   </body>
</html>
