<%--
    Document   : AccountDetails
    Created on : May 3, 2015, 2:08:50 PM
    Author     : rama krishna<lankireddy@miraclesoft.com>
--%>
<%@page import="com.mss.msp.usersdata.HomeVTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay ::Home Redirect Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/addLeaveOverlay.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/requirementAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/techReviewAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/homeRedirectAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery-1.2.2.pack.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/htmltooltip.js"/>"></script>

        <style type="text/css">
            div#imageTooltip{
                display: none;
            }
            .bgcolor{
                background-color:  #6495ED;
                border-radius:4px 4px 4px 4px;
            }

            div.htmltooltip{
                position: absolute; /*leave this and next 3 values alone*/
                z-index: 1000;
                left: -1000px;
                top: -1000px;
                background: #272727;
                border: 10px solid #6495ED;
                color: white;
                padding: 3px;
                width: 600px;
                height: auto;
                margin: 13px 11px 10px 21px;/*width of tooltip*/
            }

        </style>
    </head>
    <s:if test="homeRedirectActionId!=0">
        <body onload="doOnLoad();">
        </s:if>
        <s:else>
        <body onload="doOnLoad();setHRValues();">
        </s:else>    
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>

        </header>
        <%-- ------------MIDDLE -----------------------------------------%>
        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/>
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <s:if test="homeRedirectActionId!=0">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <s:url var="revUrl" action="getHomeRedirectDetails.action">
                                                </s:url>
                                                <font color="#ffffff">Edit Home Redirection Details</font>
                                                <span class="pull-right"><s:a href='%{#revUrl}'><img  src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                            </h4>
                                        </div>
                                    </div>
                                    <s:hidden name="homeRedirectActionId" id="homeRedirectActionId" value="%{homeRedirectActionId}"/>
                                    <s:hidden id="acc_type" value="%{#session.typeOfUsr}"/>
                                    <div class="inner-reqdiv-elements">
                                        <span><e id="errorSpan"></e></span><br>
                                        <div class="row">
                                            <div class="inner-techReviewdiv-elements"><span id="validationMessage" /></div> 
                                            <div class="col-lg-3 required">
                                                <label class="labelStylereq" style="color: #56a5ec">Account Type:</label>
                                                <%--  <s:if test="#session.typeOfUsr=='AC'">
                                                      <s:select cssClass="SelectBoxStyles form-control" name="accountType" id="accountType" list="#@java.util.LinkedHashMap@{'M':'Main','AC':'Customer','VC':'Vendor'}" headerKey="M" onchange="setAccNamesAndRoles();" value="%{homeVto.accountType}" disabled="true"/>
                                                  </s:if>
                                                  <s:elseif test="#session.typeOfUsr=='VC'">
                                                      <s:select cssClass="SelectBoxStyles form-control" name="accountType" id="accountType" list="#@java.util.LinkedHashMap@{'M':'Main','AC':'Customer','VC':'Vendor'}" headerKey="M" onchange="setAccNamesAndRoles();" value="%{homeVto.accountType}" disabled="true"/>
                                                  </s:elseif>
                                                  <s:else>
                                                      <s:select cssClass="SelectBoxStyles form-control" name="accountType" id="accountType" list="#@java.util.LinkedHashMap@{'M':'Main','AC':'Customer','VC':'Vendor'}" headerKey="M" onchange="setAccNamesAndRoles();" value="%{homeVto.accountType}" disabled="true"/>
                                                  </s:else>--%>
                                                <s:select cssClass="SelectBoxStyles form-control" name="accountType" id="accountType" list="#@java.util.LinkedHashMap@{'M':'Main','AC':'Customer','VC':'Vendor'}" headerKey="M" onchange="setAccNamesAndRoles();" value="%{homeVto.accountType}" disabled="true"/>

                                            </div>
                                            <s:hidden name="accountId" id="accountId" value="%{homeVto.accountId}" />

                                            <div class="col-lg-3">
                                                <label class="labelStylereq" style="color: #56a5ec">Account Name:</label>
                                                <s:textfield cssClass="form-control" id="accountName"  name="accountName" onkeyup="return getAccountNamesForHomeRedirection();" autocomplete='off' value="%{homeVto.accountName}" maxLength="60" disabled="true"/>

                                            </div>
                                            <div class="col-lg-3 required">
                                                <label class="labelStylereq" style="color: #56a5ec">Role:</label>
                                                <s:select cssClass="SelectBoxStyles form-control" name="roleName" id="roleName" list="rolesMap"  value="homeVto.roleName" disabled="true"/>

                                            </div>
                                            <div class="col-lg-3">
                                                <label class="labelStylereq" style="color: #56a5ec">Status</label>
                                                <s:select cssClass="SelectBoxStyles form-control" name="status" id="status" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" value="%{homeVto.status}"/>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="inner-reqdiv-elements">
                                        <div class="row">
                                            <div class="col-lg-12 required">
                                                <label class="labelStylereq" style="color: #56a5ec">Action:</label>
                                                
                                                    <!--<a  href="" rel="htmltooltip" onmouseover="getActionDescription()" onmouseout="displaynon()"> <img src="<s:url value="/includes/images/icons/isymbol.png" />" height="20" width="20" style="margin: 0px -49px -1px -2px"> </a>-->
                                                <s:if test="#session.typeOfUsr=='AC' or #session.typeOfUsr=='VC'">
                                                    <s:select cssClass="SelectBoxStyles form-control" name="actionName" id="actionName" value="%{homeVto.actionName}" list="actionName" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield cssClass="form-control" id="actionName" onblur="validateAction();"  name="actionName" value="%{homeVto.actionName}" maxLength="100"/>
                                                </s:else>
                                                
                                            </div>
                                            <%--<div class="col-lg-4 required">
                                            <label class="labelStylereq" style="color: #56a5ec">Type Of User:</label>
                                            <s:select cssClass="SelectBoxStyles form-control" name="typeOfUser" id="typeOfUser" list="#@java.util.LinkedHashMap@{'SA':'Site Admin','AC':'Customer','VC':'Vendor'}" value="%{homeVto.typeOfUSer}"/>

                                            </div>--%>
                                        </div>
                                    </div>
                                    <div class="inner-reqdiv-elements">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <label class="labelStylereq" style="color: #56a5ec">Description:</label>
                                                <s:textarea cssClass="form-control" id="description"  name="description" value="%{homeVto.description}" onkeydown=" ResponseCheckCharacters(this);" />
                                                <div class="charNum" id="ResponsecharNum"></div>
                                            </div>


                                        </div>
                                    </div>

                                </div>
                                <div class="col-lg-10"></div>
                                <div class="col-lg-2">
                                    <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton fa fa-floppy-o form-control" value="Save" onclick="storeAddOrEditHomeRedirectDetails();" theme="simple"  />
                                </div>
                            </s:if>
                            <s:if test="homeRedirectActionId==0">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font color="#ffffff">Add Home Redirection Details</font>
                                                <span class="pull-right"><a href="#"><img onclick="history.back();return false;" src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></a></span>
                                            </h4>
                                        </div>
                                    </div>
                                    <s:hidden name="homeRedirectActionId" id="homeRedirectActionId" value="%{homeRedirectActionId}"/>
                                    <s:hidden id="acc_type" value="%{#session.typeOfUsr}"/>
                                    <div class="inner-reqdiv-elements">
                                        <span><e id="errorSpan"></e></span><br>
                                        <div class="row">
                                            <div class="inner-techReviewdiv-elements"><span id="validationMessage" /></div>  
                                            <div class="col-lg-4 required">
                                                <label class="labelStylereq" style="color: #56a5ec">Account Type:</label>
                                                <s:select cssClass="SelectBoxStyles form-control" name="accountType" id="accountType" list="#@java.util.LinkedHashMap@{'M':'Main','AC':'Customer','VC':'Vendor'}" headerKey="M" onchange="setAccNamesAndRoles();"/>
                                            </div>
                                            <s:hidden name="accountId" id="accountId" value="%{accountSearchOrgId}"/>
                                            <div class="col-lg-4">
                                                <label class="labelStylereq" style="color: #56a5ec">Account Name:</label>
                                                <s:textfield cssClass="form-control" id="accountName" value="%{accountName}" name="accountName"  onkeyup="return getAccountNamesForHomeRedirection();" autocomplete='off' maxLength="60"/>
                                            </div>
                                            <div class="col-lg-4 required">
                                                <label class="labelStylereq" style="color: #56a5ec">Role:</label>
                                                <%--<s:if test="#session.typeOfUsr=='AC' or #session.typeOfUsr=='VC'">--%>
                                                <%--<s:select cssClass="SelectBoxStyles form-control" name="roleName" id="roleName" list="{}" onchange="getActionNames();"/>--%>
                                                <%--</s:if>--%>
                                                <%--<s:else>--%>
                                                <s:select cssClass="SelectBoxStyles form-control" name="roleName" id="roleName" list="{}" />
                                                <%--</s:else>--%>
                                            </div>
                                            <s:if test="homeRedirectActionId!=0">
                                                <div class="col-lg-3">
                                                    <label class="labelStylereq" style="color: #56a5ec">Status</label>
                                                    <s:select cssClass="SelectBoxStyles form-control" name="accountType" id="accountType" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" value="%{homeVto.status}"/>
                                                </div>
                                            </s:if>

                                        </div>
                                    </div>
                                    <div class="inner-reqdiv-elements">
                                        <div class="row">
                                            <div class="col-lg-12 required">
                                                <label class="labelStylereq" style="color: #56a5ec">Action:</label>
                                                <s:if test="#session.typeOfUsr=='AC' or #session.typeOfUsr=='VC'">
                                                    <s:select cssClass="SelectBoxStyles form-control" name="actionName" id="actionName" list="actionName" />
                                                </s:if>
                                                <s:else>
                                                    <s:textfield cssClass="form-control" id="actionName"  name="actionName" onblur="validateAction();" maxLength="100"/>
                                                </s:else>
                                            </div>
                                            <%--<div class="col-lg-4 required">
                                            <label class="labelStylereq" style="color: #56a5ec">Type Of User:</label>
                                            <s:select cssClass="SelectBoxStyles form-control" name="typeOfUser" id="typeOfUser" list="#@java.util.LinkedHashMap@{'SA':'Site Admin','AC':'Customer','VC':'Vendor'}" />
                                        </div>--%>
                                        </div>
                                    </div>
                                    <div class="inner-reqdiv-elements">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <label class="labelStylereq" style="color: #56a5ec">Description:</label>
                                                <s:textarea cssClass="form-control" id="description"  name="description" onkeydown=" ResponseCheckCharacters(this);" />
                                                <div class="charNum" id="ResponsecharNum"></div>
                                            </div>


                                        </div>
                                    </div>

                                </div>
                                <div class="col-lg-10"></div>
                                <div class="col-lg-2">
                                    <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton fa fa-plus-square form-control" value="Add" onclick="storeAddOrEditHomeRedirectDetails();" theme="simple"  />
                                </div>
                            </s:if>

                            <%--</s:form>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div style="height: 206px"></div>

    </section>
    <div class="htmltooltip">
        <div id="data-tooltip" >
            <center>
                <div class="bgcolor">
                    <table>
                        <tr><td><h4 style="font-style: italic;color: black;">&nbsp;Action Description&nbsp;&nbsp;</h4></td></tr>
                    </table>
                </div>
                <div>
                <!--<table>-->
                   <%--<s:textarea cssClass="form-control" id="NameOverlay" disabled="true" />--%>
                <!--</table>-->
                <span id ="NameOverlay" ></span>
                </div>
            </center></div></div>
            <%-- ------------MIDDLE -----------------------------------------%>
    <footer id="footer"><!--Footer-->
        <div class="footer-bottom" id="footer_bottom">
            <div class="container">
                <s:include value="/includes/template/footer.jsp"/>
            </div>
        </div>
    </footer><!--/Footer-->
    
    <div style="display: none; position: absolute; top:170px;left:540px;overflow:auto; z-index: 1900000" id="menu-popup">
        <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
    </div>

</body>
</html>