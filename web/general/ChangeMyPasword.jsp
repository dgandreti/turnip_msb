<%-- 
    Document   : ChangePassword
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
        <title>ServicesBay :: Change password Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive_queries.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>"><!--this is for all css in profile view -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <!-- end of new styles -->
        <style type="text/css">
            label[for="pwd1"] {
                color: #56a5ec;
            }label[for="pwd2"] {
                color: #56a5ec;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#pwd1").keyup(checkPasswordMatch);
            });
        </script>
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
                <div class="wrap_pwd">
                    <section id="generalForm" class=""><!--form-->
                       
                        <div class="container">
                            <div class="row">
                                <s:include value="/includes/menu/LeftMenu.jsp"/> 
                                <div class="col-sm-12 col-md-8 col-lg-7 right_block">
                                    <div class="">
                                        <div class="features_items"><!--features_items-->
                                            <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                                <div class="backgroundcolor" >
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <font color="#ffffff">Change your password</font>
                                                        </h4>
                                                    </div>
                                                </div>
                                                <font id="resMessage" style="color: green;font-size: 12px;"><s:property value="#request.resultMessage"/></font>
                                                <span><resetMessage></resetMessage></span>
                                                <div class="password_box" ><!--login form-->

                                                    <form id="ResetPassword" action="resetMyPassword" onclick="return checkPasswordMatch();" >
                                                        <div class="required">
                                                            <div class="col-lg-5 col-sm-5 req_margin">
                                                                <label id="labelLevelStatusReq">Current Password</label>                            
                                                            </div>
                                                            <div class="col-lg-6 col-sm-6 req_margin">
                                                                <s:password name="curPwd" cssClass="form-control" id="curPwd"  placeholder="Current Password"  required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"  onchange="matchCurrentPassword()"    title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"   tabindex="1"/>
                                                            </div>
                                                            <div class="col-lg-5 col-sm-5 req_margin">
                                                                <label id="labelLevelStatusReq">New Password</label>                            
                                                            </div>
                                                            <div class="col-lg-6 col-sm-6 req_margin">
                                                                <s:password name="newpwd" cssClass="form-control" id="pwd1"  placeholder="New Password"  required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"  onchange="form.cnfrmpwd.pattern=this.value"    title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"   tabindex="2"/>
                                                            </div>

                                                            <div class="col-lg-5 col-sm-5 req_margin">     
                                                                <label id="labelLevelStatusReq">Confirm Password</label>                          
                                                            </div>
                                                            <div class="col-lg-6 col-sm-6 req_margin"> 
                                                                <s:password  name="cnfrmpwd" cssClass="form-control" id="pwd2" placeholder="Confirm Password" required="true" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"    oninvalid="setCustomValidity('Please enter the same Password as above')"   onchange="try{setCustomValidity('')}catch(e){}" tabindex="3"/>
                                                            </div>

                                                            <br>
                                                             <div  class="col-lg-6 pull-right btn_pull">
                                                                <div class="col-sm-5 req_margin pull-right">  

                                                                      <div align="right"><button id="reset" type="reset" value="Cancel" tabindex="5" class="cssbutton req_margin align_left  fa fa-times" onclick="javascript:history.back();">&nbsp;Cancel</button>
                                                                    </div>



                                                                </div>
                                                                <div  class="col-sm-6 req_margin pull-right"> 
<div align="right"><button type="submit" id="submit" value="Submit" tabindex="4" class="cssbutton req_margin align_left fa fa-check-circle-o">&nbsp;Submit</button>
                                                                  
                                                                    </div>



                                                                </div>
                                                            </div>

                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <!-- maps end -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- content end -->
                        <!-- content start -->
                    </section><!--/form-->
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
        $("#resMessage").show().delay(5000).fadeOut();
    </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>