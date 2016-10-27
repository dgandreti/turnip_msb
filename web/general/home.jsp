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
        <title>ServicesBay :: Home Page</title>
        <!--[if IE]>
                <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/ie_support.css"/>">
               <![endif]-->
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/flexslider.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">

        <!-- end of new styles -->
        <!-- start of js -->
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/jquery-1.8.2.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/home/jquery.cycle.all.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/homeslider/js/jssor.slider.min.js"/>"></script>

        <!-- end of js -->


        <!-- end of js -->


        <style>

            /* jssor slider arrow navigator skin 07 css */
            /*
            .jssora07l                  (normal)
            .jssora07r                  (normal)
            .jssora07l:hover            (normal mouseover)
            .jssora07r:hover            (normal mouseover)
            .jssora07l.jssora07ldn      (mousedown)
            .jssora07r.jssora07rdn      (mousedown)
            */
            .jssora07l, .jssora07r {
                display: block;
                position: absolute;
                /* size of arrow element */
                width: 50px;
                height: 50px;
                cursor: pointer;
                background: url('${pageContext.request.contextPath}/includes/js/homeslider/img/a07.png') no-repeat;
                overflow: hidden;
            }
            .jssora07l { background-position: -5px -35px; }
            .jssora07r { background-position: -65px -35px; }
            .jssora07l:hover { background-position: -125px -35px; }
            .jssora07r:hover { background-position: -185px -35px; }
            .jssora07l.jssora07ldn { background-position: -245px -35px; }
            .jssora07r.jssora07rdn { background-position: -305px -35px; }
            /* jssor slider thumbnail navigator skin 04 css *//*.jssort04 .p            (normal).jssort04 .p:hover      (normal mouseover).jssort04 .pav          (active).jssort04 .pav:hover    (active mouseover).jssort04 .pdn          (mousedown)*/.jssort04 .p {    position: absolute;    top: 0;    left: 0;    width: 62px;    height: 32px;}.jssort04 .t {    position: absolute;    top: 0;    left: 0;    width: 100%;    height: 100%;    border: none;}.jssort04 .w, .jssort04 .pav:hover .w {    position: absolute;    width: 60px;    height: 30px;    border: #0099FF 1px solid;    box-sizing: content-box;}.jssort04 .pdn .w, .jssort04 .pav .w {    border-style: dashed;}.jssort04 .c {    position: absolute;    top: 0;    left: 0;    width: 62px;    height: 32px;    background-color: #000;    filter: alpha(opacity=45);    opacity: .45;    transition: opacity .6s;    -moz-transition: opacity .6s;    -webkit-transition: opacity .6s;    -o-transition: opacity .6s;}.jssort04 .p:hover .c, .jssort04 .pav .c {    filter: alpha(opacity=0);    opacity: 0;}.jssort04 .p:hover .c {    transition: none;    -moz-transition: none;    -webkit-transition: none;    -o-transition: none;}* html .jssort04 .w {    width /**/: 62px;    height /**/: 32px;}

        </style>
        <script>
            jssor_1_slider_init = function() {
            
                var jssor_1_options = {
                    $AutoPlay: true,
                    $SlideDuration: 3000,
                    $ArrowNavigatorOptions: {
                        $Class: $JssorArrowNavigator$
                    },
                    $ThumbnailNavigatorOptions: {
                        $Class: $JssorThumbnailNavigator$,
                        $Cols: 15,
                        $SpacingX: 3,
                        $SpacingY: 3,
                        $Align: 455
                    }
                };
            
                var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
            
                //responsive code begin
                //you can remove responsive code if you don't want the slider scales while window resizing
                function ScaleSlider() {
                    var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                    if (refSize) {
                        refSize = Math.min(refSize, 1200);
                        jssor_1_slider.$ScaleWidth(refSize);
                    }
                    else {
                        window.setTimeout(ScaleSlider, 30);
                    }
                }
                ScaleSlider();
                $Jssor$.$AddEvent(window, "load", ScaleSlider);
                $Jssor$.$AddEvent(window, "resize", ScaleSlider);
                $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
                //responsive code end
            };
            </script>
            <script>
              function disableBack(){
            window.location.hash="no-back-button";
            window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
            window.onhashchange=function(){window.location.hash="";}
            }
        </script>
    </head>
    <body oncontextmenu="return false" onload="disableBack();">

        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top" id="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div><!--/header_top-->

            </header><!--/header-->
            <div id="main">
                <section id="generalForm"><!--form-->
                    <div id="login_home" >
                        <div class="container" style="width:93%">
                            <div class="row">
                                <div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:970px;height:400px;overflow:hidden;visibility:hidden;background:url('${pageContext.request.contextPath}/includes/js/homeslider/img/main_bg.jpg') 50% 50% no-repeat;">
                                    <!-- Loading Screen -->
                                    <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
                                        <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
                                        <div style="position:absolute;display:block;background:url('${pageContext.request.contextPath}/img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
                                    </div>
                                    <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 980px; height: 400px; overflow: hidden;">
                                        <div data-p="172.50" style="display: none;">
                                            <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/accountslide1.png"  style="margin-left:4%;margin-top:3%"/>
                                            <img data-u="thumb" src="<%=request.getContextPath()%>/includes/js/homeslider/img/slide1thumb1.png" />
                                        </div>
                                        <div data-p="172.50" style="display: none;">
                                            <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/slides_source_2_1.png"/>
                                            <img data-u="thumb" src="<%=request.getContextPath()%>/includes/js/homeslider/img/slide2thumb1.png" />
                                        </div>
                                        <div data-p="172.50" style="display: none;">
                                            
                                           <%-- <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/responsive4.gif" /> --%>
                                            <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/TT.gif" style="margin-left:10%;margin-top:7%"/>
                                            <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/text.png" style="margin-left: 5%;margin-top:3%"/> 
                                            <img data-u="thumb" src="<%=request.getContextPath()%>/includes/js/homeslider/img/slide-Copy.png" />
                                        </div>
                                        <div data-p="172.50" style="display: none;">
                                            <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/customerView1.png"/>
                                            <img data-u="thumb" src="<%=request.getContextPath()%>/includes/js/homeslider/img/slide4_thumb.png" />
                                        </div>
                                        <div data-p="172.50" style="display: none;">
                                            <img src="<%=request.getContextPath()%>/includes/js/homeslider/img/vendorView1.png"/>
                                            <img data-u="thumb" src="<%=request.getContextPath()%>/includes/js/homeslider/img/slide5_thumb.png" />
                                        </div>

                                    </div>
                                    <!-- Thumbnail Navigator -->
                                    <div data-u="thumbnavigator" class="jssort04" style="position:absolute;left:0px;bottom:0px;width:980px;height:60px;" data-autocenter="1">
                                        <!-- Thumbnail Item Skin Begin -->
                                        <div data-u="slides" style="cursor: default;">
                                            <div data-u="prototype" class="p">
                                                <div class="w">
                                                    <div data-u="thumbnailtemplate" class="t"></div>
                                                </div>
                                                <div class="c"></div>
                                            </div>
                                        </div>
                                        <!-- Thumbnail Item Skin End -->
                                    </div>
                                    <!-- Arrow Navigator -->
                                    <span data-u="arrowleft" class="jssora07l" style="top:0px;left:8px;width:50px;height:50px;" data-autocenter="2"></span>
                                    <span data-u="arrowright" class="jssora07r" style="top:0px;right:8px;width:50px;height:50px;" data-autocenter="2"></span>

                                </div>
                                <script>
                                    jssor_1_slider_init();
                                </script>         
                                <div id="slide" style="display: none;">
                                    <div id="forgetoverlay" >
                                        <div style="width: available;border-top-left-radius: 12px;border-top-right-radius: 12px; background-color: rgb(68,145,223);background-color: rgb(68,145,223);">
                                            <table>
                                                <tr ><td style="width:100%" align="left" colspan="2"><h4 style="font-family:Alike Angular">
                                                            <font color="white" style="font-weight: bold;font-size: 22px;">&nbsp;&nbsp;Forgot your password </font></h4></td>
                                                </tr><span class="pull-right"><h5><a class="slide_close" href=""><button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin: 0px 15px;"><i class="fa fa-times"></i></button></a></h5></span>

                                            </table> 
                                        </div>
                                        <hr style="margin: 0px;">
                                        <div style="font-family: Alike Angular; margin-top: 10px; margin-left: 10px; margin-right: 10px;">

                                            <p> Enter the Email address of your account to reset your password</p>
                                            <span id="Loading" style="color:red; width:auto"></span>
                                            <span id="resultMessage" style="width: auto"></span>
                                            <center>
                                                <s:form id="forgotPassword" name="forgotPassword" >
                                                    <div class="logCredential"><i class="fa fa-envelope"></i>
                                                        <input type="email"  class="frgt_pwd" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" placeholder="Valid Email Address" id="forgotEmailId" name="forgotEnailId" required= "required"/></div>
                                                    &nbsp;<input style="margin:4px" type="button" class="passwordButton" value="Send E-mail" name="FPass" id="FPass" onclick="return forgotPassword();"/>

                                                </s:form>
                                            </center>
                                            <br><br>
                                        </div>
                                    </div> 
                                </div>
                                <!-- Start Special Centered Box -->
                                <div class="col-sm-4 col-sm-offset-1" id="col-sm-4">
                                    <div class="login-form" ><!--login form-->
                                        <h2>Login to your account</h2>
                                        <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" > 
                                            <div class="logCredential"><i class="fa fa-envelope"></i><input type="email" placeholder="Email" name="emailId" id="emailId" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required data-error-message="LoginId is required!" tabindex="1"/></div>

                                            <div class="logCredential"><i class="fa fa-key"></i><input type="password" placeholder="password"  name='password' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password" title="Must be at least 6 characters long, and contain at least one number, one uppercase and one lowercase letter" required data-type="Password" tabindex="2" /></div>

                                            <p id="wrapper"><a  class="slide_open" href=""><font>Forgot password?</font></a></p>
                                            <div class="form-group">
                                                <div class="LoginButton">
                                                    <button type="submit" style="display: inline">Log In</button> Or <a href="<%=request.getContextPath()%>/general/register.action">Register</a>
                                                </div></div>
                                            </s:form>
                                            <% if (request.getAttribute("errorMessage") != null) {
                                                    out.println(request.getAttribute("errorMessage"));
                                                }%>
                                    </div><!--/login form-->
                                </div>
                                <!-- Start Special Centered Box -->
                                <div class="login-form modal fade" id="myLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                    <div class="modal-dialog" id="Form_login" role="document" >
                                        <div class="modal-content">
                                            <div class="modal-header " style="background-color: rgb(68,145,223);background-color: rgb(68,145,223);  border-top-right-radius: 0px; border-top-left-radius: 0px;">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><i class="fa fa-times"></i></button>
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
                                                    <div class="logCredential"><i class="fa fa-envelope"></i> <input type="email" placeholder="Email" class="frgt_pwd" name="emailId" id="emailId" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required data-error-message="LoginId is required!" tabindex="1"/></div>

                                                    <div class="logCredential"><i class="fa fa-key"></i> <input type="password" placeholder="Password" class="frgt_pwd"  name='password' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password" title="Must be at least 6 characters long, and contain at least one number, one uppercase and one lowercase letter" required data-type="Password" tabindex="2" /></div>

                                                    <p id="wrapper" class="frgt_pwd"><a  class="slide_open" href="" id="closeLogin" data-dismiss="modal" data-toggle="modal" data-target="#forgotPwd"><font class ="fgtPwd">Forgot password?</font></a></p>
                                                    <div class="LoginButton">
                                                        <button type="submit" >LogIn</button>
                                                    </div>
                                                </s:form>



                                            </div></div></div></div>


                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="Home" class="modal fade" style="display: none;">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 id="myModalLabel" class="modal-title">Account Management</h4>

                                    </div>
                                    <div class="col-sm-12 popup-align">
                                        <div class="col-sm-5"> <img src="<s:url value="/includes/images/home/acc_1.jpg"/>" height="100px" width="190px"></div>
                                        <p> Which provides a virtual workspace to facilitate effective communication between team members. It helps you to share information and work jointly on projects and efficiently use all available resources.</p>
                                    </div>
                                    <div class="modal-body">


                                        <br><br><b>Features:</b>
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Adding Account in one click.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Manage Users for a customer / vendor.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Hierarchy Maintenance.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Handling accounts by a CSR(Customer Service Representative) to the respective accounts.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Management of Accounts(Customer/Vendor) for CSR.                                

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel-body">
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="projectManager" class="modal fade" style="display: none;">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 id="myModalLabel" class="modal-title">Project Management</h4>
                                    </div>
                                    <div class="col-sm-12 popup-align">
                                        <div class="col-sm-5"> <img src="<s:url value="/includes/images/home/project2.jpg"/>" height="100px" width="190px"></div>
                                        <p>Which provides a virtual workspace to facilitate effective communication between team members. It helps you to share information and work jointly on projects and efficiently use all available resources.</p>

                                    </div>
                                    <div class="modal-body">
                                        <br><br><b>Features:</b>
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Management of Resources.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Assigning projects to respective person
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Hierarchy Maintenance.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Assigning of tasks based on the project.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Handling tasks 

                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="panel-body">
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="budgetManagement" class="modal fade" style="display: none;">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 id="myModalLabel" class="modal-title">Budget Management</h4>
                                    </div>
                                    <div class="col-sm-12 popup-align">
                                        <div class="col-sm-5"> <img src="<s:url value="/includes/images/home/budget_management12.jpg"/>" height="100px" width="190px"></div>
                                        <p> A budget is a quantitative expression of a plan for a defined period of time. It may include planned cost of expenditure towards project.</p>

                                    </div>
                                    <div class="modal-body">
                                        <br><br><b>Features:</b>
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Cost Planning.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Request Budget.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Approve Budget.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Allocate Budget.


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel-body">
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="timeSheetsModal" class="modal fade" style="display: none;">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 id="myModalLabel" class="modal-title">TimeSheet and Invoice Management</h4>
                                    </div>
                                    <div class="col-sm-12 popup-align">
                                        <div class="col-sm-5"><img src="<s:url value="/includes/images/home/clock123.jpg"/>" height="100px" width="190px"></div>
                                        <p>Amount of time spent for each project by a person and it helps in Project Time Tracking.</p>

                                    </div>
                                    <div class="modal-body">
                                        <br><br><b>Features:</b>
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Ability to track times.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Get Approvals of Timesheet(Approved/Rejected).
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Calculate employee expenses by total billable hours.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Holiday Tracking.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Vacation Hours Tracking.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Project Hours Tracking.



                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel-body">
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ResourceModal" class="modal fade" style="display: none;">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 id="myModalLabel" class="modal-title">Recruitment and Staffing Management</h4>
                                    </div>
                                    <div class="col-sm-12 popup-align">
                                        <div class="col-sm-5">
                                            <img src="<s:url value="/includes/images/home/recruit2_.png"/>" height="100px" width="190px"></div>
                                        <p>To Handle the Requirement and post the job to the associated vendors in an organization and selecting Right person to the Right Job will satisfy the client requirement.</p>

                                    </div>
                                    <div class="modal-body">
                                        <br><br><b>Features:</b>
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Adding Requirement and releasing it to the vendors.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Publishing the requirement to vendors.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Manage the requirement for the level of vendors in the vendor pool.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Fit the appropriate consultant to the requirement.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Conducting of technical review to the consultant.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Screening the consultant in multiple levels and interview follow-up.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Selection of candidates.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Migration of candidates from vendor to customer by (Statement of Work/Finder Fee)
                                        .

                                        </p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel-body">
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="MDO_Modal" class="modal fade" style="display: none;">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                        <h4 id="myModalLabel" class="modal-title">Multi device operability</h4>
                                    </div>
                                    <div class="col-sm-12 popup-align">
                                        <div class="col-sm-5">
                                            <img src="<s:url value="/includes/images/home/responsive 1.png"/>" height="100px" width="190px"></div>
                                        <p> Provide an optimal view & interaction  experience-easy & secure navigation.Includes many features defined below..</p>
                                    </div>
                                    <div class="modal-body">

                                        <br><br><b>Features:</b>
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Portal is a cross browser application support.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;Accessible in multiple devices.
                                        <br><i class="fa fa-chevron-circle-right"></i>&nbsp;User friendly application.
                                        </p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>












                    <div class="marginTasks">
                        <div id="menus"  class="container">
                            <div class="row" >
                                <div class="col-sm-6 col-md-4 col-sx-6 contentwidth" >

                                    <div   id="account_home" data-target="#Home" data-toggle="modal" title="Account Management" class=" panel_home panel hometextalignment">


                                    </div>
                                    <div class="htextalign" >
                                        <b id="align">Account Management</b>
                                        <div class="h-body text-center">
                                            <p id="accountalign"> Tool which provides a virtual workspace to  facilitate effective communication between team members. It helps you to share the..</p>

                                            <a class="popup-with-zoom-anim" href="#small-dialog1" data-target="#Home" data-toggle="modal">
                                                View more..
                                            </a>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-sx-6 contentwidth">
                                    <div  id="project_home" data-target="#projectManager" data-toggle="modal"  title="Project Management" class=" panel_home panel hometextalignment">


                                    </div>
                                    <div class="htextalign" >
                                        <b id="align1">Project & Task Management</b>
                                        <div class="h-body text-center">
                                            <p id="accountalign">Tool which provides a virtual workspace to facilitate effective communication between team members. It helps you to share the..</P> 

                                            <a class="popup-with-zoom-anim" href="#small-dialog1" data-target="#projectManager" data-toggle="modal">
                                                View more..
                                            </a>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-sx-6 contentwidth">
                                    <div  id="budget_home" data-target="#budgetManagement" data-toggle="modal"  title="Budget Management" class="panel_home panel hometextalignment">

                                    </div>
                                    <div class="htextalign" >
                                        <b id="align">Budget Management</b>
                                        <div class="h-body text-center">
                                            <p id="accountalign">Budget is quantitative expression of a plan for a defined period of time. It may include planned cost of expenditure towards the..</p>

                                            <a class="popup-with-zoom-anim" href="#small-dialog1" data-target="#budgetManagement" data-toggle="modal">
                                                View more..
                                            </a>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-sx-6 contentwidth">
                                    <div  id="timesheet_home" data-target="#timeSheetsModal" data-toggle="modal"  title="Timesheet Management" class=" panel_home panel hometextalignment">

                                    </div>
                                    <div class="htextalign" >
                                        <b id="align1">Time Sheet & Invoice Management</b>
                                        <div class="h-body text-center">
                                            <p id="accountalign">Amount of time spent for the project by a person and also helps in the Project Time Tracking. It includes some of the features..</p>

                                            <a class="popup-with-zoom-anim" href="#small-dialog1" data-target="#timeSheetsModal" data-toggle="modal">
                                                View more..
                                            </a>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-sx-6 contentwidth">
                                    <div id="rfm_home" data-target="#ResourceModal" data-toggle="modal"  title="Resource Filtering and Management" class=" panel_home panel hometextalignment">

                                    </div>
                                    <div class="htextalign" >
                                        <b id="align1">Recruitment & Staffing Management </b>
                                        <div class="h-body text-center">
                                            <p id="accountalign1">To handle the Requirements and posting job to the associated vendors in an organization and selecting Right person to the Right Job ..</p>

                                            <a class="popup-with-zoom-anim" href="#small-dialog1" data-target="#ResourceModal" data-toggle="modal">
                                                View more..
                                            </a>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-sx-6 contentwidth ">
                                    <div  id="mdo_home" data-target="#MDO_Modal" data-toggle="modal"  title="Multi device operability" class="panel_home panel hometextalignment">

                                    </div>
                                    <div class="htextalign" >
                                        <b id="align">Multi Device Operability</b>
                                        <div class="h-body text-center">
                                            <p id="accountalign"> Provide an optimal view & interaction experience easy & secure navigation.Includes many features defined below..</p>

                                            <a class="popup-with-zoom-anim" href="#small-dialog1" data-target="#MDO_Modal" data-toggle="modal">
                                                View more..
                                            </a>

                                        </div>
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
            
        </script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/home/jquery.flexslider.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/home/custom.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

    </body>
</html>
