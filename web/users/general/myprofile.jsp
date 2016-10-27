<%-- 
    Document   : myprofile
    Created on : Mar 17, 2015, 9:11:15 PM
    Author     : Nagireddy
--%>


<%@page import="java.sql.Timestamp"%>
<%@page import="org.apache.struts2.components.Property"%>
<%@page import="com.mss.msp.usersdata.UserAddress"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
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
        <title>ServicesBay :: Profile Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <!--this is for all css in profile view -->
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>" > </script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <!-- end of new styles -->
    </head>
    <body oncontextmenu="return false" style="font-family: 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans'">
        <div id="wrap">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->
        </header><!--/header-->
        <!-- overlays code starts from -->   
        <!-- overlays ended here -->
        <div id="main">
        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row" >
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->

                    <div class="col-sm-9 padding-right" style="background-color:#fff">
                        <div class="features_items" ><!--features_items--> 	                                               
                            <!-- maps start -->
                            <!-- maps end s_items--> 	                                               
                            <!-- maps start -->
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Profile Information</font>
                                            </h4>
                                        </div>
                                    </div>
                                    <div id="picture" style="float: left" >
                                        <s:url id="image" action="rImage" namespace="/renderImage">
                                            <s:param name="path" value="%{#session.usrImagePath}"></s:param>
                                        </s:url>
                                        <img alt="Employee Image" src="<s:property value="#image"/>"  alt="loin" height="125px" width="120px">
                                    </div>
                                    <div style="float: left" id="margins">
                                        <table style="margin-left: 10px">
                                            <tr><th>First Name&nbsp;&nbsp</th><td>:&nbsp;&nbsp;<s:property value="%{#session.firstName}"/> </td></tr>
                                            <tr><th>Last Name&nbsp;&nbsp</th><td>:&nbsp;&nbsp;<s:property value="%{#session.lastName}"/></td></tr>
                                            <tr><th>Alias Name</th><td>:&nbsp;&nbsp;<s:property value="%{#session.alias}"/></td></tr>
                                            <tr><th>Phone</th><td>:&nbsp;&nbsp;<s:property value="%{#session.empphone}"/></td></tr>
                                            <%

                                                String Mstatus = (String) session.getAttribute("mstatus");

                                                if ("M".equalsIgnoreCase(Mstatus)) {
                                                    Mstatus = "Married";
                                                } else {
                                                    Mstatus = "Single";
                                                }
                                            %>
                                            <tr><th>Marital Status</th><td>:&nbsp;&nbsp;<%=Mstatus%></td></tr>

                                        </table>
                                    </div>
                                    <div id="margins" style="float: left">
                                        <table style="margin-left: 10px" >
                                            <%
                                                String Dob = (String) session.getAttribute("dob");
                                                Timestamp timestamp = com.mss.msp.util.DateUtility.getInstance().strToTimeStampObj(Dob);
                                                System.out.println(Dob + " here print date");
                                                String dob1 = com.mss.msp.util.DateUtility.TimeStampMonthDayYear(timestamp);
                                            %>
                                            <tr><th>DOB</th><td>:&nbsp;&nbsp;<%=dob1%></td></tr>
                                            <% String Gender = (String) session.getAttribute("gender");

                                                if ("M".equalsIgnoreCase(Gender)) {
                                                    Gender = "Male";
                                                } else {
                                                    Gender = "FeMale";
                                                }
                                            %>

                                            <tr><th>Gender</th><td>:&nbsp;&nbsp;<%=Gender%></td></tr>
                                            <tr><th>Working FOR</th><td>:&nbsp;&nbsp;<s:property value="%{#session.workingCountry}"/></td></tr>
                                            <tr><th>Living country</th><td>:&nbsp;&nbsp;<s:property value="%{#session.livingCountry}"/></td></tr>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="col-lg-12 " style="margin-bottom: 8px" >
                            <div id="" class=" backgroundcolor" padding: 0px">

                                 <div class="" id="accordian_contact">
                                    <div class="panel-heading">
                                        <h4 class="panel-title" >
                                           <%-- <a data-toggle="collapse" data-parent="#accordian" href="#Addresspop" >--%>

                                                
                                            <%-- </a> --%>
                                            <font color="#ffffff">Contact Information</font>

                                        </h4>
                                    </div>
                                </div>
                            </div>
                            <div id="Addresspop">
                                <div class="panel-body" >
                                    <div class="col-lg-6">
                                        <div class="" id="AddressBox" style="float: left"> 
                                            <div style="background-color:  #EBF4FA;padding:6px">
                                                <table >
                                                    <tr style="font-family: 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px"><td>&nbsp;&nbsp;<font color="#FF8A14"><b>Permanent Address &nbsp;&nbsp;</b></font></td></tr>
                                                            <%--<span class="pull-right">    
                                                                <h5 ><a href="" class="permanentAddress_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a>&nbsp;&nbsp;</h5></span>--%>
                                                </table>
                                            </div>

                                            <div id="margins">
                                                <center> <table>
                                                        <tr><th>Address </th><td>:&nbsp;&nbsp<s:property value="userAddress.p_address"/></td></tr>
                                                        <tr><th>Address2</th><td>:&nbsp;&nbsp<s:property value="userAddress.p_address2"/></td></tr>
                                                        <tr><th>City </th><td>:&nbsp;&nbsp<s:property value="userAddress.p_city"/></td></tr>
                                                        <tr><th>State</th><td>:&nbsp;&nbsp<s:property value="userAddress.p_state"/></td></tr>
                                                        <tr><th>Zip</th><td>:&nbsp;&nbsp<s:property value="userAddress.p_zip"/></td></tr>
                                                        <tr><th>country</th><td>:&nbsp;&nbsp<s:property value="userAddress.p_country"/></td></tr>
                                                        <tr><th>phone</th><td>:&nbsp;&nbsp<s:property value="userAddress.p_phone"/></td></tr>
                                                    </table></center>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="" id="eduSecondry" style="float: left" >
                                            <div style="background-color:  #EBF4FA;padding: 6px">
                                                <table >
                                                    <tr style="font-family: 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px"><td>&nbsp;&nbsp;<font color="#FF8A14"><b>Current Address &nbsp;&nbsp;</b></font></td></tr>
                                                </table>
                                            </div>

                                            <div id="margins" style="">
                                                <center> <table>
                                                        <s:checkbox label="Same as Permanent Address" name="userAddress.address_flag" disabled="true" id="Address" ></s:checkbox>
                                                        <tr><th>Address </th><td>:&nbsp;&nbsp<s:property value="userAddress.c_address"/></td></tr>
                                                        <tr><th>Address2</th><td>:&nbsp;&nbsp<s:property value="userAddress.c_address2"/></td></tr>
                                                        <tr><th>City </th><td>:&nbsp;&nbsp<s:property value="userAddress.c_city"/></td></tr>
                                                        <tr><th>State</th><td>:&nbsp;&nbsp<s:property value="userAddress.c_state"/></td></tr>
                                                        <tr><th>Zip</th><td>:&nbsp;&nbsp<s:property value="userAddress.c_zip"/></td></tr>
                                                        <tr><th>country</th><td>:&nbsp;&nbsp<s:property value="userAddress.c_country"/></td></tr>
                                                        <tr><th>phone</th><td>:&nbsp;&nbsp<s:property value="userAddress.c_phone"/></td></tr>
                                                    </table>

                                                </center>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- content end -->

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


