<%-- 
    Document   : header
    Created on : Feb 3, 2015, 7:52:40 PM
    Author     : Nagireddy
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/general.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
<link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive_queries.css"/>">
<!DOCTYPE html>

<%-- <div class="row headerAlingment"></div>--%>
<div class="row">
    <div class="col-sm-6">
        <div class="contactinfo">
            <ul class="nav nav-pills">
                <li><a href="<%=request.getContextPath()%>/general/login.action"><img src="<s:url value="/includes/images/logo.png"/>" alt="loin" width="200" height="33"/></a></li>
            </ul>
        </div>
    </div>




    <s:if test="#session.userId == null">  
        <div class="social-icons">
            <ul class="nav navbar-nav navbar-right" style="margin-right: 8%;">

                 <s:if test="home!='No'&&home!='Logout'">
                    <li class="sign-in sign_in"><a class="login_open" href="#" data-toggle="modal" data-target="#myLogin">SIGN IN</a></li>
                    
                    <li><a href="<%=request.getContextPath()%>/general/register.action?home=No">Register</a></li>
                </s:if>
                <s:if test="home=='No'||home=='Logout'">
                    <li><a href="<%=request.getContextPath()%>/general/login.action"><i class="fa"><img class="fa" src="<s:url value="/includes/images/login.png"/>" alt="loin" height="20"/></a></i></li>
                            </s:if>

            </ul>

        </div>	
        <%--  <div class="col-sm-6" id="col-sm-6">
              <div class="social-icons pull-right">
                  <ul class="nav navbar-nav">
        <%--li><a href="#"><i class="fa"><img src="<s:url value="/includes/images/help.jpg"/>" alt="loin" width="20" height="20"/></a></i></li--%>
        <%-- <li><a href="<%=request.getContextPath()%>/general/login.action"><i class="fa"><img class="fa" src="<s:url value="/includes/images/login.png"/>" alt="loin" height="20"/></a></i></li>
    </ul>
</div>
</div>	--%>			
    </s:if>
    <s:else>
        <%--  <s:hidden type="hidden" id="orgId"  name="orgId" value="%{#session.orgId}"  /> ---%>
        <div class="col-sm-6" id="col-sm-6" style="padding-bottom: 0px">
            <div class=" pull-right anchorstyle">
                <%
                    String usrId = session.getAttribute(ApplicationConstants.USER_ID).toString();
                    String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();

                %>


                <ul class="" >

                    <li class="dropdown"  >


                        <!--<ul class="nav navbar-nav">-->

                        <table>
                            <td> 
                                <s:url id="image" action="rImage" namespace="/renderImage">
                                    <s:param name="path" value="%{#session.usrImagePath}"></s:param>
                                </s:url>
                                <img alt="Employee Image" src="<s:property value="#image"/>"  alt="loin" height="40px" width="40px">
                            </td>
                            <td style="white-space: nowrap">
                                <ul class="dropdown-menu pull-right multi-column columns-2" style="height:auto;" role="menu" aria-labelledby="dropdownMenu2">
                                    <li style="position: absolute;top:-8px;left: 170px;"><div class="arrow"></div></li>
                                    <div class="row ">
                                        <div class="col-sm-5">
                                            <ul class="multi-column-dropdown">
                                                <li><a  href="<%=request.getContextPath()%>/general/changeMyPassword.action">Change&nbsp;Pwd&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                                </li>
                                                <!--                                    <li><a  href="#">View Profile</a>
                                                                                    </li>-->
                                                <li class="divider"></li>
                                            </ul>
                                            <!--<ul class="panel-body nav-stacked  dropdown-menu " style="position:absolute">-->
                                        </div>
                                        <div class="col-sm-5">
                                            <ul class="multi-column-dropdown">
                                                <%-- <li><a  href="<%=request.getContextPath()%>/general/changeMyPassword.action">Change Password&nbsp;&nbsp;&nbsp;&nbsp</a> 
                                                 </li> --%>
                                                <li><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/acc/accountcontactedit.action?contactId=<%=usrId%>&accountSearchID=<%=orgId%>&flag=customerlogin">View&nbsp;Profile</a>
                                                </li>

                                                <!--<ul class="panel-body nav-stacked  dropdown-menu " style="position:absolute">-->

                                                <li class="divider"></li>
                                                <li><a style="text-align:left" href="<%=request.getContextPath()%>/general/logout.action"><i class="fa fa-power-off">&nbsp;&nbsp;Logout</i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </ul>
                                <font style="color:#FAF6F6;font-size:15px;font-weight:400;font-family:Roboto,sans-serif">
                                <%-- <s:text name="Welcome :"/> --%>
                                </font>
                                <a id="dropdownMenu2" class="dropdown-toggle" aria-expanded="flase" data-toggle="dropdown" href="#">
                                    <font style="color:#D6E3F2;font-size:15px;font-weight:600;font-family:Roboto,sans-serif">
                                    <s:property value="#session.firstName"/>&nbsp;<s:property value="#session.lastName"/>
                                    <span class=""><i class="fa fa-angle-down"></i></span>
                                    </font></a>
                                <br>
                                <%-- <%
                                     String userType = session.getAttribute(ApplicationConstants.TYPE_OF_USER).toString();
                                     //out.println("userType"+userType);
                                     if (userType.equalsIgnoreCase("E") || userType.equalsIgnoreCase("SA")) {
                                 %>--%>
                                <font style="color:#FAF6F6;font-size:15px;font-weight:400;font-family:Roboto,sans-serif">
                                <%-- <s:text name="Role :"/> --%>
                                </font>
                                <font style="color:#D6E3F2;font-size:15px;font-weight:600;font-family:Roboto,sans-serif">
                                [<s:property value="#session.primaryrolevalue" />]
                                </font>
                                <%-- <s:select headerKey="0" list="#session.rolesMap" value="#session.primaryrole" theme="simple" id="headSelectBoxStyle" onchange="performAction('/general/roleSubmit.action',this)" /> --%>

                                <%--<%}%>--%>
                            </td>
                            <td>

                            </td>
                        </table>




                    </li>
                </ul>
            </div>
                            
        </div>
    </s:else>
</div>
<%--<div id="seperator"></div>--%>
