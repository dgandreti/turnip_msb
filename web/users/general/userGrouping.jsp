<%-- 
    Document   : userGrouping
    Created on : Jul 17, 2015, 1:00:06 AM
    Author     : praveen<pkatru@miraclesoft.com>
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
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
        <title>ServicesBay :: User Grouping</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value='/includes/css/accountDetails/details.css'/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/account/formVerification.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script language="JavaScript" type="text/javascript" src="<s:url value="/includes/js/general/ProfilePage.js"/>" ></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>

        <script>
            $(document).ready(function() {
                $('#usrCategoryValue').selectivity({
                    multiple: true,
                    placeholder: 'Type to search Categories'
                });
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
                </div>

            </header>
            <div id="main">
                <div class="container">
                    <div class="row">
                        <!-- Main Content-->
                        <s:include value="/includes/menu/LeftMenu.jsp"/>
                        <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="">
                            <!-- Add Form Area -->
                            <div class="col-lg-12">
                                <div class="" id="selectivityProfileBox" style="float: left; margin-top: 15px; margin-bottom: 20px">
                                    <!-- Add Form Header-->
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <s:if test="groupingId!=0">
                                                    <font color="#ffffff">Edit Employee Grouping</font>
                                                </s:if>
                                                <s:else>
                                                    <font color="#ffffff">Add Employee Grouping</font>    
                                                </s:else>
                                                <s:url var="myUrl" action="getEmployeeCategorization.action">
                                                </s:url>
                                                <span class="pull-right"><s:a id="userGroupBackButton" href='%{#myUrl}'><i class="fa fa-undo"></i></s:a></span>
                                                </h4>
                                            </div>
                                        </div> 
                                        <div class="col-sm-12">
                                            <span><userGrouping></userGrouping></span>
                                                <s:hidden id="addOrUpdate" value="%{addOrUpdate}" />
                                                <s:hidden id="grouperRoleId" value="%{roleId}" />
                                        <div class="inner-reqdiv-elements">
                                            <div class="row">

                                                <div class="col-sm-3 required">
                                                    <s:hidden id="groupingId" name="groupingId" value="%{groupingId}"/>
                                                    <label class="labelStylereq" style="color:#56a5ec">User Name</label>
                                                    <s:if test="groupingId!=0">
                                                        <s:textfield name="userName" cssClass="form-control" id="userName" placeholder="User Name" value="%{userName}" onkeyup="return getUserNameForCategory()" onfocus="clearErrosMsgForGrouping()" disabled="true" maxLength="60"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield name="userName" cssClass="form-control" id="userName" placeholder="User Name" value="%{userName}" onkeyup="return getUserNameForCategory()" onfocus="clearErrosMsgForGrouping()"  maxLength="60"/>
                                                    </s:else>
                                                    <s:hidden name="userId" id="userId" value="%{userId}"/>
                                                    <span id="validationMessage" />
                                                </div>
                                                <div id="loadingUserGrouping" class="loadingImg" style="display: none">
                                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>" ></span>
                                                </div>    
                                                <div class="col-sm-3 required">
                                                    <label class="labelStylereq" style="color:#56a5ec">Category Type</label>
                                                    <s:select id="usrCatType" name="usrCatType" cssClass="SelectBoxStyles form-control " headerKey="-1" headerValue="--select--"  value="%{usrCatType}"  theme="simple" list="%{usrCategory}" onchange="getCategoryList()" onfocus="clearErrosMsgForGrouping()"/>
                                                </div>
                                                <s:if test="groupingId!=0">
                                                    <div class="col-sm-3 required">
                                                        <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                        <s:select id="usrStatus" name="usrStatus" cssClass="SelectBoxStyles form-control " headerKey="-1" headerValue="--select--"  value="%{usrStatus}"  theme="simple" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"  onfocus="clearErrosMsgForGrouping()" />
                                                    </div>  
                                                </s:if>
                                                <s:else>
                                                    <s:hidden id="usrStatus" name="usrStatus"  value="Active" />
                                                </s:else>
                                                <div class="col-sm-3">
                                                    <br>
                                                    <label for="" class="checkbox">
                                                        <s:checkbox id="usrPrimary" name="usrPrimary"  value="%{primaryvalue}"/>IsPrimary
                                                    </label>
                                                </div>
                                            </div>

                                        </div> <br>
                                    </div>

                                    <div class="col-sm-3 required">
                                        <label class="labelStylereq categoryReq" style="color:#56a5ec">Categories </label>
                                        <s:if test="groupingId!=0">
                                            <s:select cssClass="" name="usrCategoryValue"  id="usrCategoryValue" 
                                                      list      = "catValuesMap" 

                                                      multiple  = "true" 
                                                      value     = "catValuesList"
                                                      onfocus="clearErrosMsgForGrouping()"
                                                      />
                                        </s:if>
                                        <s:else >
                                            <s:select cssClass=" " name="usrCategoryValue"  id="usrCategoryValue" list="catValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()"/> 
                                        </s:else>
                                    </div>
                                    <div class="col-sm-9">
                                        <label class="labelStylereq" style="color:#56a5ec">Description</label>
                                        <s:textarea id="usrDescription" name="usrDescription" placeholder="Description" cssClass="form-control "  value="%{usrDescription}" onfocus="clearErrosMsgForGrouping()" onkeyup="CalculateLeangth()" onblur="clearNoofChar()" />
                                        <span><deascOpt></deascOpt></span>
                                    </div>
                                    <div class="col-sm-10"></div>
                                    <div class="col-sm-2 pull-right">   
                                        <s:submit type="button" id="saveUserGroupButton" cssStyle="margin:5px 0px;" value="" cssClass="add_searchButton form-control " onclick="doUserGroupingMethod()"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                        </div>
                                        <div class="col-sm-1"> 
                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                        </div>

                                    </div>
                                </div>
                            </div><!-- Add Form-->
                        </div>
                    </div>
                </div> 
            </div>
            <footer id="footer"><!--Footer-->
                <div class="footer-bottom" id="footer_bottom">
                    <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>
