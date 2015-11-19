<%-- 
    Document   : add Or Update Resources
    Created on : July 21, 2015, 2:50:23 AM
    Author     : Manikanta
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Action Resources Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <%-- <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
             <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>

        <%--<script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>--%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/GeneralAjax.js"/>'></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>--%>

    </head>
    <body style="overflow-x: hidden" onload="getAccountNames(); getUserGroups();">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div>

        </header>

        <section id="generalForm">

            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-16 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <s:if test="flag=='update'">
                                                    <font color="#ffffff">Update Action Resources</font>
                                                </s:if>
                                                <s:else>
                                                    <font color="#ffffff">Add Action Resources</font>
                                                </s:else>
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <s:url var="myUrl" action="searchActionResources.action">
                                                    <s:param name="action_id"><s:property value="action_id"/></s:param> 
                                                    <s:param name="action_name"><s:property value="action_name"/></s:param>
                                                </s:url>
                                                <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>

                                                </h4>
                                            </div>

                                        </div>
                                        <!-- content start -->
                                        <div ><label class="labelStylereq" style="color:#FF8A14;">Action Name:&nbsp; </label><label style="color: #FF8A14  "><s:property value="action_name" /></label></div>
                                    <s:hidden id="action_id" name="action_id" value="%{action_id}"/>
                                    <s:hidden id="id" name="id" value="%{id}"/>
                                    <s:hidden id="authId" name="authId" />

                                    <div class="col-sm-16">
                                        <div id="outputMessage" style="color: green"></div>
                                        <%-- <s:form action="searchAccAuthorization" theme="simple"> --%>
                                        <div class="col-lg-4">
                                            <label class="labelStylereq" style="color:#56a5ec;">Account Type: </label>
                                            <s:select  id="accType"
                                                       name="accType"
                                                       cssClass="SelectBoxStyles form-control"
                                                       headerKey="-1" value="%{accType}"  
                                                       theme="simple" onchange="getRolesForAccType();"
                                                       list="#@java.util.LinkedHashMap@{'C':'Customer','V':'Vendor','M':'Main'}"
                                                       />
                                        </div>
                                        <s:if test="flag=='update'">

                                            <div class="col-lg-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Status: </label>
                                                <s:select  id="status"
                                                           name="status"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1"  
                                                           theme="simple"
                                                           value="%{status}"
                                                           list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'Inactive','All':'All'}"
                                                           />
                                            </div >
                                        </s:if>

                                        <div class="col-lg-4">
                                            <label class="labelStylereq" style="color:#56a5ec;">Roles: </label>
                                            <s:select  id="roles"
                                                       name="roles"
                                                       cssClass="SelectBoxStyles form-control"
                                                       headerKey="-1"  
                                                       theme="simple" 
                                                       value="%{roleId}"
                                                       list="rolesMap"
                                                       onchange="getUserGroups();"
                                                       />
                                        </div >
                                        <div class="col-lg-4" id="usergroupDiv" style="display: none">
                                            <label class="labelStylereq" style="color:#56a5ec;">Group: </label>
                                            <s:select  id="userGroups"
                                                       name="userGroupId"
                                                       cssClass="SelectBoxStyles form-control"
                                                       theme="simple"
                                                       headerKey="-1"
                                                       headerValue="Select Group"
                                                       value="%{userGroupList}"
                                                       list="userGroupIdList"
                                                       />
                                        </div>
                                        <div class="col-lg-4">
                                            <s:hidden name="orgId" id="orgId"/>
                                            <%--<s:textfield value="%{accountName}"/>
                                            <s:textfield value="%{status}"/>
                                            <s:textfield value="%{description}"/>--%>
                                            <%--<s:textfield value="%{rollName}"/> --%>

                                            <label class="labelStylereq" style="color:#56a5ec;">Account Name: </label>
                                            <s:if test="accountName == 'All'">   
                                                <s:textfield id="accountNamePopup"
                                                             cssClass="form-control"
                                                             type="text"
                                                             name="accName" 
                                                             value=" "
                                                             placeholder="Account Name"
                                                             onkeyup="return getAccountNames();" 
                                                             maxLength="60"/> 
                                            </s:if> 
                                            <s:else>
                                                <s:textfield id="accountNamePopup"
                                                             cssClass="form-control"
                                                             type="text"
                                                             name="accName" 
                                                             value="%{accountName}"
                                                             placeholder="Account Name"
                                                             onkeyup="return getAccountNames();" 
                                                             maxLength="60"/> 
                                            </s:else>
                                            <span id="validationMessage" />
                                        </div>
                                        <div class="col-lg-4">
                                            <label class="labelStylereq" style="color:#56a5ec;">Description: </label>
                                            <s:textarea id="addingAccAuthDesc" cssClass="form-control" name="addingAccAuthDesc" value="%{description}" onkeydown="actionAuthDescription(this)"/>
                                            <span class="charNum" id="addingAccAuthValid"></span> 
                                        </div>

                                        <div class="col-lg-4">
                                            <label for="block_flag" class="checkbox">
                                                <s:checkbox name="blockFlag" id="blockFlag" value="%{blockFlag}"/>Block Action
                                            </label>
                                        </div>

                                        <div class="col-lg-4">
                                            <s:if test="flag=='update'">

                                                <div class="col-lg-6"></div>
                                                <div class="col-lg-6">
                                                    <label class="labelStylereq" style="color:#56a5ec;"></label>

                                                    <button type="button"
                                                            class="add_searchButton form-control" style="margin: 5px 0px;"
                                                            value="Update" onclick="return insertOrUpdateActionResources('1');"><i class="fa fa-refresh"></i>&nbsp;Update</button>
                                                </s:if>
                                                <s:else>
                                                    <div class="col-lg-6"></div>
                                                    <div class="col-lg-6">
                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>

                                                        <button type="button" style="margin: 5px 0px;"
                                                                class="add_searchButton  form-control"
                                                                value="" onclick="return insertOrUpdateActionResources('0');"><i class="fa fa-plus-square"></i>&nbsp;Add</button>
                                                    </div>
                                                </s:else>
                                            </div>

                                            <%--  </s:form> --%>


                                        </div>
                                        <%--</s:form>--%>
                                    </div>

                                    <%--close of future_items--%>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>
                <%-- Start overlay for Display Description --%>
                <%--<div id="authAccOverlay_popup" >
                    <div id="authAccBox">
                        <div class="backgroundcolor">
                            <table>
                                <tr><td><h4 style="font-family:cursive"><font class="titleColor" >&nbsp;&nbsp;Description &nbsp;&nbsp; </font></h4></td>
                                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="authAccOverlay_popup_close" onclick="authAccOverlayFun()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                            </table>
                        </div>
                        <div>
                            <div class="inner-reqdiv-elements">

                            <div id="outputMessageOfauthAcc"></div>

                        </div>



                    </div>
                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                </div>   
            </div> --%>

                <%-- end overlay for Display Description --%>
                <%-- Start overlay for for Add Acc Authorization  --%>
                <%--<div id="addAuthAccOverlay_popup" >
                    <div id="addAuthAccBox">
                        <div class="backgroundcolor">
                            <table>
                                <tr><td><h4 id="" style="font-family:cursive"><font id="heading" class="titleColor" >&nbsp;&nbsp;Add Account Authorization &nbsp;&nbsp; </font></h4></td>
                                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="addAuthAccOverlay_popup_close" onclick="addAuthAccOverlayFun()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                            </table>
                        </div>
                        <div>
                            <s:hidden id="overlayActionId" value="overlayActionId"/>
                            <s:hidden id="overlayActionName" value="overlayActionName"/>
                            <s:hidden id="overlayActionStatus" value="overlayActionStatus"/>
                            <s:hidden id="overlayActionDesc" value="overlayActionDesc"/>
                            <div class="inner-reqdiv-elements" id="accNameDiv">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <label class="labelStylereq" style="color:#56a5ec;">Action Name: </label>
                                        <s:textfield id="action_name" 
                                                     cssClass="form-control"
                                                     type="text"
                                                     name="action_name"
                                                     placeholder="Action Name"
                                                     /> 
                                    </div>
                                </div>
                            </div>
                            <div class="inner-reqdiv-elements" id="statusDiv">
                <%--  <s:hidden id="overLayAction_id" name="overLayAction_id" value="action_id"/>--%>
                <%--<div class="row">
                    <div class="col-lg-6">
                        <label class="labelStylereq" style="color:#56a5ec;">Status: </label>
                        <s:select  id="accauthStatus"
                                   name="accauthStatus"
                                   cssClass="SelectBoxStyles form-control"
                                   headerKey="-1"  
                                   theme="simple" 
                                   list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'Inactive','All':'All'}"
                                   />
                    </div >
                </div>
            </div>
            <div class="inner-reqdiv-elements" id="descDiv">
                <div class="row">
                    <div class="col-lg-10">
                        <label class="labelStylereq" style="color:#56a5ec;">Description: </label>
                        <s:textarea id="addingAccAuthDesc" cssClass="form-control" name="addingAccAuthDesc"/>

                                </div>
                            </div>
                        </div>
                        <div class="inner-reqdiv-elements" id="addDiv">
                            <div class="row">
                                <div class="col-lg-10">
                                    <input type="button" class="cssbutton" onclick="insertOrUpdateAccAuth('0');" value="Add"/>
                                </div>
                            </div>
                        </div>
                        <div class="inner-reqdiv-elements" id="updateDiv">
                            <div class="row">
                                <div class="col-lg-10">
                                    <input type="button" class="cssbutton" onclick="insertOrUpdateAccAuth('1');" value="update"/>
                                </div>
                            </div>
                        </div>

                        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                    </div>   
                </div> 
            </div> --%>

                <%-- end overlay for Add Acc Authorization  --%>

                <!-- content end -->
        </section><!--/form-->

        <div style="height: 306px"></div>

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>

        </footer>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <!--/Footer-->
        <script>
            sortables_init();
        </script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>


