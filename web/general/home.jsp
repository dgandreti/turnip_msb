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

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/flexslider.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <!-- end of new styles -->
        <!-- start of js -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/includes/js/general/jquery-1.8.2.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/home/jquery.cycle.all.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <!-- end of js -->
        <script type="text/JavaScript">
            $('#desktop_slider') .cycle({
                fx: 'none', // here change effect to blindX, blindY, blindZ etc 
                speed: 'slow', 
                timeout: 2000 
            });

            $('#tab_slider') .cycle({
                fx: 'none', // here change effect to blindX, blindY, blindZ etc 
                speed: 'slow', 
                timeout: 2000 
            });

            $('#mobile_slider') .cycle({
                fx: 'none', // here change effect to blindX, blindY, blindZ etc 
                speed: 'slow', 
                timeout: 2000 
            });
        </script>
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
            <div id="login_home" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-7">

                            <!-- Rotator Content -->
                            <div class="intro_slider">											
                                <ul class="slides">
                                    <!-- Intro Slide #1 -->
                                    <li>
                                        <div class="intro_slide" >


                                            <!-- INTRO BUTTONS -->
                                            <div class="col-md-6" id="bannerimage">


                                                <img id="" width="880px" height="445px" src="<s:url value="/includes/images/home/homepage_banner.png"/>">

                                            </div>

                                            <div class="col-md-6 col-sm-12" id="banner_left_menu">
                                                <h3 class="cd-headline letters">
                                                    <span class="cd-words-wrapper">
                                                        <b></b>
                                                        <b class="is-visible">Accounts Support</b>
                                                    </span>
                                                </h3>
                                                <ul>
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Adding Account in one click.</li>
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Manage Users for a customers / vendors.</li>	
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Handling accounts by a CSR.</li>
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Management of Accounts(Customers / Vendors) for CSR.</li>

                                                </ul>


                                            </div>
                                        </div>
                                    </li>	<!-- End Intro Slide #1 -->											

                                    <!-- Intro Slide #2 -->	
                                    <li>
                                        <div class="intro_slide" id="staffing_logo">
                                            <div class="col-md-6" >
                                                <img width="725px" height="445px" src="<s:url value="/includes/images/home/staffing_company.png"/>"/>
                                            </div>            
                                            <div class="col-md-6 col-sm-12" >
                                                <h3 class="cd-headline letters ">
                                                    <span class="cd-words-wrapper">

                                                        <b>Recruiting&nbsp;&AMP;&nbsp;Staffing</b>
                                                    </span>
                                                </h3>

                                                <ul>
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Employees</li>
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Networking Jobs</li>	
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Professionals Search</li>
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Recruiting</li>
                                                    <li><span ><i class="fa fa-hand-o-right "></i></span>&nbsp;Career HeadHunter</li>	
                                                    <li><span><i class="fa fa-hand-o-right "></i></span>&nbsp;Team Management</li>					
                                                </ul>
                                            </div>                                             
                                        </div>
                                    </li>	<!-- End Intro Slide #2 -->	
                                    <!-- Intro Slide #4 -->	
                                    <li>
                                        <div class="intro_slide"  >
                                            <h1 class="cd-headline letters1 ">
                                                <span class="cd-words-wrapper">
                                                    <b class="is-visible"></b>
                                                    <b >Multi&nbsp; Device&nbsp; Operability</b>
                                                </span>
                                            </h1>
                                            <div class="col-md-6" id="multiple_devices">
                                                <ul id="desktop_slider">
                                                    <li>
                                                        <img id="desktop_home" width="375px" height="297px"  src="<s:url value="/includes/images/home/home.png"/>"/>
                                                    </li>
                                                    <li>
                                                        <img id="desktop_home1" width="375px" height="297px"  src="<s:url value="/includes/images/home/ServicesBay_Account_Search_Page.jpg"/>"/>
                                                    </li>
                                                    <li>
                                                        <img id="desktop_home2" width="375px" height="297px"  src="<s:url value="/includes/images/home/ServicesBay_Dashboard_Page.jpg"/>" style=""/>
                                                    </li>
                                                </ul>
                                                <ul id="tab_slider">
                                                    <li>
                                                        <img name="imageslider" width="167px" height="246px"  src="<s:url value="/includes/images/home/home_tab.jpg"/>"/>
                                                    </li>
                                                    <li>
                                                        <img name="imageslider" width="167px" height="246px"  src="<s:url value="/includes/images/home/account_search_tab.jpg"/>" />
                                                    </li>
                                                    <li>
                                                        <img name="imageslider" width="167px" height="246px"  src="<s:url value="/includes/images/home/dashboard_tab.jpg"/>" />
                                                    </li>
                                                </ul>
                                                <ul id="mobile_slider" style="  "><li>
                                                        <img name="imageslider" width="100px" height="160px"  src="<s:url value="/includes/images/home/home_mobile.jpg"/>" />
                                                    </li>
                                                    <li>
                                                        <img name="imageslider" width="100px" height="160px"  src="<s:url value="/includes/images/home/account_search_mobile.jpg"/>" />
                                                    </li>
                                                    <li>
                                                        <img name="imageslider" width="100px" height="160px"   src="<s:url value="/includes/images/home/dashboard_mobile.jpg"/>"/>
                                                    </li>
                                                </ul>
                                            </div>       


                                        </div>
                                    </li>	<!-- End Intro Slide #4 -->

                                </ul>												
                            </div>	 <!-- End Rotator Content -->
                        </div>
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

                                            <input type="email" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" placeholder="Valid Email Address" id="forgotEmailId" name="forgotEnailId" required= "required"/>
                                            &nbsp;<input style="margin:4px" type="button" class="passwordButton" value="Send E-mail" name="FPass" id="FPass" onclick="return forgotPassword();"/>


                                            <%--<button onclick="forgotPassword();">ResetPassword</button>--%>

                                            <%-- <div id="Loading" style="width: auto;display: none;"/> --%>

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
                                <s:form action="/general/loginCheck.action" method="post" name="userLoginForm" id="userLoginForm" > 
                                    <input type="email" placeholder="Email" name="emailId" id="emailId" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required data-error-message="LoginId is required!" tabindex="1"/>

                                    <input type="password" placeholder="password"  name='password' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password" title="Must be at least 6 characters long, and contain at least one number, one uppercase and one lowercase letter" required data-type="Password" tabindex="2" />

                                    <p id="wrapper"><a  class="slide_open" href=""><font>Forgot Password</font></a></p>
                                    <div class="form-group">
                                        <div class="LoginButton">
                                            <button type="submit" style="display: inline">Log In</button> Or <a href="<%=request.getContextPath()%>/general/register.action">Sign Up</a>
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
                                            <input type="email" placeholder="Email" name="emailId" id="emailId" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required data-error-message="LoginId is required!" tabindex="1"/>

                                            <input type="password" placeholder="Password"  name='password' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password" title="Must be at least 6 characters long, and contain at least one number, one uppercase and one lowercase letter" required data-type="Password" tabindex="2" />

                                            <p id="wrapper"><a  class="slide_open" href="" id="closeLogin" data-dismiss="modal" data-toggle="modal" data-target="#forgotPwd"><font class ="fgtPwd">Forgot Password</font></a></p>
                                            <div class="LoginButton">
                                                <button type="submit" >LogIn</button>
                                            </div>
                                        </s:form>



                                    </div></div></div></div>
                        <div class="col-sm-1">

                        </div>

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
                                <img src="<s:url value="/includes/images/home/account_manage.jpg"/>" height="100px" width="190px">

                            </div>
                            <div class="modal-body">
                                <p><b>Account Management tool</b> which provides a virtual workspace to facilitate effective communication between team members. It helps you to share information and work jointly on projects and efficiently use all available resources.

                                    <br><br><b>Features:</b>
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Adding Account in one click.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Manage Users for a customer / vendor.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Hierarchy Maintenance.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Handling accounts by a CSR(Customer Service Representative) to the respective accounts.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Management of Accounts(Customer/Vendor) for CSR.                                
                                </p>
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
                                <img src="<s:url value="/includes/images/home/project_manage.jpg"/>" height="100px" width="190px">
                            </div>
                            <div class="modal-body">
                                <p><b>Project Management</b> and <b>Task management</b> tool which provides a virtual workspace to facilitate effective communication between team members. It helps you to share information and work jointly on projects and efficiently use all available resources.
                                    <br><br><b>Features:</b>
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Management of Resources.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Assigning projects to respective person
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Hierarchy Maintenance.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Assigning of tasks based on the project.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Handling tasks 
                                </p>
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
                                <img src="<s:url value="/includes/images/home/budget_manage.jpg"/>" height="100px" width="190px">
                            </div>
                            <div class="modal-body">
                                <p><b>Budget Management:</b> A budget is a quantitative expression of a plan for a defined period of time. It may include planned cost of expenditure towards project.
                                    <br><br><b>Features:</b>
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Cost Planning.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Request Budget.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Approve Budget.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Allocate Budget.

                                </p>
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
                                <img src="<s:url value="/includes/images/home/time_manage.jpg"/>" height="100px" width="190px">
                            </div>
                            <div class="modal-body">
                                <p><b>TimeSheet Management and Invoice Management:</b> Amount of time spent for each project by a person and it helps in Project Time Tracking.
                                    <br><br><b>Features:</b>
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Ability to track times.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Get Approvals of Timesheet(Approved/Rejected).
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Calculate employee expenses by total billable hours.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Holiday Tracking.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Vacation Hours Tracking.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Project Hours Tracking.


                                </p>
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
                                <img src="<s:url value="/includes/images/home/resource_filter.jpg"/>" height="100px" width="190px">
                            </div>
                            <div class="modal-body">
                                <p><b>Recruitment and Staffing Management:</b>To Handle the Requirements in an organization and post job to the associated vendors to the organization and then selecting  Right person to the Right Job and satisfy the client requirement.
                                    <br><br><b>Features:</b>
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Adding Requirement and releasing it to the vendors.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Publishing the requirement to vendors.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Manage the requirement for the level of vendors in the vendor pool.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Fit the appropriate consultant to the requirement.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Conducting of technical review to the consultant.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Screening the consultant in multiple levels and interview follow-up.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Selection of candidates.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Migration of candidates from vendor to customer by (Statement of Work/Finder Fee)
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
                                <img src="<s:url value="/includes/images/home/responsive_galleryIcon-.png"/>" height="100px" width="190px">
                            </div>
                            <div class="modal-body">
                                <p><b>Multi device operability</b>
                                    <br><br><b>Features:</b>
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Portal is a cross browser application support.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;Accessible in multiple devices.
                                    <br><i class="fa fa-spinner fa-pulse"></i>&nbsp;User friendly application.
                                </p>
                            </div>

                        </div>
                    </div>
                </div>
            </div>












            <div class="row">
                <div id="menus" >
                    <div id="menu_section" >

                        <div id="account_home" data-target="#Home" data-toggle="modal" title="Account Management">
                            <div class="ch-info">
                                <a class="popup-with-zoom-anim" href="#small-dialog1">

                                    <h3>Account Management</h3>
                                </a>

                            </div>
                        </div>
                        <div id="project_home" data-target="#projectManager" data-toggle="modal"  title="Project Management">
                            <div class="ch-info">
                                <a class="popup-with-zoom-anim" href="#small-dialog1">

                                    <h3>Project Management</h3>
                                </a>

                            </div>

                        </div>
                        <div id="budget_home" data-target="#budgetManagement" data-toggle="modal"  title="Budget Management">
                            <div class="ch-info">
                                <a class="popup-with-zoom-anim" href="#small-dialog1">

                                    <h3>Budget Management</h3>
                                </a>

                            </div>

                        </div>
                        <div id="timesheet_home" data-target="#timeSheetsModal" data-toggle="modal"  title="Timesheet Management">
                            <div class="ch-info">
                                <a class="popup-with-zoom-anim" href="#small-dialog1">

                                    <h3>Timesheet Management</h3>
                                </a>

                            </div>

                        </div>
                        <div id="rfm_home" data-target="#ResourceModal" data-toggle="modal"  title="Resource Filtering and Management">
                            <div class="ch-info">
                                <a class="popup-with-zoom-anim" href="#small-dialog1">

                                    <h3>Resource Filtering and Management</h3>
                                </a>

                            </div>

                        </div>
                        <div id="mdo_home" data-target="#MDO_Modal" data-toggle="modal"  title="Multi device operability">
                            <div class="ch-info">
                                <a class="popup-with-zoom-anim" href="#small-dialog1">

                                    <h3>Multi device operability</h3>
                                </a>

                            </div>

                        </div>
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


        <script type="text/JavaScript" src="<s:url value="/includes/js/home/jquery.flexslider.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/home/custom.js"/>"></script>




    </body>
</html>
