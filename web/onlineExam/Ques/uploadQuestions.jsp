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
        <title>ServicesBay :: Upload Questions</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>"><!--this is for all css in profile view -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
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
        <section id="generalFormDesign"><!--form-->

            <%--<div class="header-middle"><!--header-middle-->
                <div class="container">
                    
                    <div class="row">
                        <s:include value="/includes/menu/generalTopMenu.jsp"/> 
                    </div>
                        
               </div>
            </div> --%>
            <div class="container">
                <div class="row">

                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                     <div class="col-md-10 col-md-offset-0" style="background-color:#fff;height:30vw;">
                        <div class="features_items"><!--features_items-->
                            <div class="col-lg-12 " style="height:25vw;">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >


                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Upload Questions</font>

                                            </h4>
                                        </div>

                                    </div>
                               
                                    <span><resetMessage></resetMessage></span>
                                    <div class="" ><!--login form-->

                                        <s:form action="/users/general/getSkillDetails">
                                          
                                            <div class="col-sm-4">  
                                                <s:submit id="uploadQuestionsButton"  cssClass="cssbutton req_margin"  type="submit" value="UploadQuestions" cssStyle="margin-top:9vw;margin-right:34vw;"/>
                                               
                                            </div>
                                        </s:form>

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
        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>

        </footer><!--/Footer-->

<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>

    </body>
</html>