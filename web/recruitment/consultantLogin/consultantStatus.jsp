<%-- 
    Document   : consultantStatus
    Created on : May 21, 2015, 3:07:00 PM
    Author     : miracle
--%>



<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Consultant Status Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>

        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>





    </head>
    <body style="overflow-x: hidden">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>
        </header>
        <s:include value="/includes/menu/LeftMenu.jsp"/>
        <section id="generalForm"><!--form-->
            <div  class="container">
                <div class="row">
                    <!-- content start -->
                    <div class="col-md-9 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Consultant Status Information</font>
                                            </h4>
                                        </div>
                                    </div><br/><br/>
                                    <!-- content start -->
                                    <div class="col-sm-12">

                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>


                                        <s:form>
                                            <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                <table id="consultantStatus" class="responsive CSSTable_task" border="5">
                                                    <tbody>
                                                        <tr>
                                                            <th>Job Title</th>
                                                            <th>Skill Set</th>
                                                            <th>Applied Date</th>
                                                            <th>Status</th>

                                                        </tr>
                                                        <s:if test="consultantListVTO ==null">
                                                            <tr>
                                                                <td colspan="8"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:iterator  value="consultantListVTO">
                                                            <!--build url TO goto Account Details-->

                                                            <tr>
                                                                <td><s:property value="req_name"></s:property></td>
                                                                <td><s:property value="req_skills"></s:property></td>
                                                                <td><s:property value="createdDate"></s:property></td>
                                                                <td><s:property value="status"></s:property></td>
                                                            </tr>
                                                        </s:iterator>

                                                    </tbody>
                                                </table>
                                                <br/>
                                                <div align="right" id="pageNavPosition" style="margin-right: 0vw;"></div>
                                            </s:form>
                                            <script type="text/javascript">
                                                var pager = new EmpPager('consultantStatus', 10); 
                                                pager.init(); 
                                                pager.showPageNav('pager', 'pageNavPosition'); 
                                                pager.showPage(1);
                                            </script>
                                        </div>
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
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>

