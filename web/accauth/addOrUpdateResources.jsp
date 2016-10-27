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
        <title>ServicesBay :: Action&nbsp;Resources&nbsp;Page</title>

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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/GeneralAjax.js"/>'></script>


    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="getAccountNames(); getUserGroups();setBlockFlag();">
        <div id="wrap">

            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div>

            </header>
                    <div id="main">
            <section id="generalForm">

                <div class="container">
                    <div class="row">
                        <s:include value="/includes/menu/LeftMenu.jsp"/> 
                        <!-- content start -->
                        <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                            <div class="features_items">
                                <div class="col-lg-16 ">
                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <s:if test="flag=='update'">
                                                        <font color="#ffffff">Update&nbsp;Action&nbsp;Resources</font>
                                                    </s:if>
                                                    <s:else>
                                                        <font color="#ffffff">Add&nbsp;Action&nbsp;Resources</font>
                                                    </s:else>
                                                    <s:url var="myUrl" action="searchActionResources.action">
                                                        <s:param name="action_id"><s:property value="action_id"/></s:param> 
                                                        <s:param name="action_name"><s:property value="action_name"/></s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}' id="addResBackButton"><i class="fa fa-undo"></i></s:a></span>

                                                    </h4>
                                                </div>

                                            </div>
                                            <!-- content start -->
                                            <div ><label class="labelStylereq" style="color:#FF8A14;">Action&nbsp;Name : &nbsp; </label><span style="color: #FF8A14  "><s:property value="action_name" /></span></div>
                                        <s:hidden id="action_id" name="action_id" value="%{action_id}"/>
                                        <s:hidden id="id" name="id" value="%{id}"/>
                                        <s:hidden id="authId" name="authId" />

                                        <div class="col-sm-16">
                                            <div id="outputMessage" style="color: green"></div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Account&nbsp;Type </label>
                                                <s:select  id="accType"
                                                           name="accType"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1" value="%{accType}"  
                                                           theme="simple" onchange="getRolesForAccType();"
                                                           list="#@java.util.LinkedHashMap@{'C':'Customer','V':'Vendor','M':'Main'}"
                                                           />
                                                
                                            </div>
                                            <s:if test="flag=='update'">

                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color:#56a5ec;">Status </label>
                                                    <s:select  id="status"
                                                               name="status"
                                                               cssClass="SelectBoxStyles form-control"
                                                               headerKey="-1"  
                                                               theme="simple"
                                                               value="%{status}"
                                                               list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','All':'All'}"
                                                               />
                                                </div >
                                            </s:if>

                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Roles </label>
                                                <s:select  id="roles"
                                                           name="roles"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1"  
                                                           theme="simple" 
                                                           value="%{roleId}"
                                                           list="rolesMap"
                                                           onchange="getUserGroups();"
                                                           />
                                                <s:hidden id="actionHiddenRole" name="actionHiddenRole" value="%{roleId}" ></s:hidden>
                                            </div >
                                            <div class="col-sm-4" id="usergroupDiv" style="display: none">
                                                <label class="labelStylereq" style="color:#56a5ec;">Group </label>
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
                                            <div class="col-sm-4">
                                                <s:hidden name="orgId" id="orgId"/>


                                                <label class="labelStylereq" style="color:#56a5ec;">Account&nbsp;Name </label>
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
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Description </label>
                                                <s:textarea id="addingAccAuthDesc" cssClass="form-control" name="addingAccAuthDesc" placeholder="Description" value="%{description}" onkeydown="actionAuthDescription(this)"/>
                                                <span class="charNum" id="addingAccAuthValid"></span> 
                                            </div>
                                                <s:hidden id="blockFlagHidden" name="blockFlagHidden" value="%{blockFlag}" ></s:hidden>
                                            <div class="col-sm-4">
                                                <label for="block_flag" class="checkbox" style="margin: 25px 0px">
                                                    <s:checkbox name="blockFlag" id="blockFlag" value="%{blockFlag}"/>Block&nbsp;Action
                                                </label>
                                            </div>

                                            <div class="col-sm-4 pull-right">
                                                <s:if test="flag=='update'">

                                                    <div class="col-lg-6"></div>
                                                    <div class="col-sm-6">
                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>

                                                        <button type="button" id="addResUpdateButton"
                                                                class="add_searchButton form-control" style="margin: 5px 0px;"
                                                                value="Update" onclick="return insertOrUpdateActionResources('1');"><i class="fa fa-refresh"></i>&nbsp;Update</button>
                                                    </s:if>
                                                    <s:else>
                                                        <div class="col-lg-6"></div>
                                                        <div class="col-sm-6">
                                                            <label class="labelStylereq" style="color:#56a5ec;"></label>

                                                            <button type="button" style="margin: 5px 15px;"
                                                                    class="add_searchButton  form-control" id="addResAddButton"
                                                                    value="" onclick="return insertOrUpdateActionResources('0');"><i class="fa fa-plus-square"></i>&nbsp;Add</button>
                                                        </div>
                                                    </s:else>
                                                </div>



                                            </div>
                                        </div>
                                        <%--close of future_items--%>
                                    </div>
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

        </footer>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <!--/Footer-->
        <script>
            sortables_init();
        </script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>


