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
        <%-- <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script> --%>
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
<!--        <script type="text/javascript">
            
            $(function(){
                $("#usrCatType").change(function(){
                    var cb1 = $('#usrPrimary').is(':checked');        
                    var primary;
                    if(cb1){
                        primary=1;
                    }
                    else{
                        primary=0;
                    }
                    var groupingId=$("#groupingId").val();
                    var userId=$("#userId").val();
                    var usrStatus=$("#usrStatus").val();
                    //var usrPrimary=$("#usrPrimary").val();
                    var userName=$("#userName").val();
                    var usrDescription=$("#usrDescription").val();
                    window.location='../general/getUserGroping.action?usrCatType=' + this.value +'&groupingId='+ groupingId +'&userId='+ userId +'&usrStatus='+usrStatus+'&primary='+primary+ '&userName='+userName+'&usrDescription='+usrDescription+'';
                });
            });
        </script>-->


    </head>

    <body>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>

        </header>

        <div class="container">
            <div class="row">
                <!-- Main Content-->
                <s:include value="/includes/menu/LeftMenu.jsp"/>
                <div class="col-md-10" style="">
                    <!-- Add Form Area -->
                    <div class="col-lg-12">
                        <div class="" id="selectivityProfileBox" style="float: left; margin-top: 15px; margin-bottom: 20px">
                            <!-- Add Form Header-->
                            <div class="backgroundcolor" >
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                        <font color="#ffffff">User Grouping</font>
                                        <s:url var="myUrl" action="getEmployeeCategorization.action">
                                        </s:url>
                                        <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                        </h4>
                                    </div>
                                </div> 
                                <div class="col-lg-12">
                                    <span><userGrouping></userGrouping></span>
                                    <div class="inner-reqdiv-elements">
                                        <div class="row">

                                            <div class="col-lg-3">
                                            <s:hidden id="groupingId" name="groupingId" value="%{groupingId}"/>
                                            <label class="labelStylereq" style="color:#56a5ec"><span class="accDetailsError">*</span>User Name:</label>
                                            <s:if test="groupingId!=0">
                                                <s:textfield name="userName" cssClass="form-control" id="userName" value="%{userName}" onkeyup="return getUserNameForCategory()" onfocus="clearErrosMsgForGrouping()" disabled="true" maxLength="60"/>
                                            </s:if>
                                            <s:else>
                                                <s:textfield name="userName" cssClass="form-control" id="userName" value="%{userName}" onkeyup="return getUserNameForCategory()" onfocus="clearErrosMsgForGrouping()"  maxLength="60"/>
                                            </s:else>
                                            <s:hidden name="userId" id="userId" value="%{userId}"/>
                                            <span id="validationMessage" />
                                        </div>
                                        <div class="col-lg-3">
                                            <label class="labelStylereq" style="color:#56a5ec"><span class="accDetailsError">*</span>Category Type</label>
                                            <s:select id="usrCatType" name="usrCatType" cssClass="SelectBoxStyles form-control " headerKey="-1" headerValue="--select--"  value="%{usrCatType}"  theme="simple" list="%{usrCategory}" onchange="getCategoryList()" onfocus="clearErrosMsgForGrouping()"/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label class="labelStylereq" style="color:#56a5ec"><span class="accDetailsError">*</span>Status</label>
                                            <s:select id="usrStatus" name="usrStatus" cssClass="SelectBoxStyles form-control " headerKey="-1" headerValue="--select--"  value="%{usrStatus}"  theme="simple" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"  onfocus="clearErrosMsgForGrouping()" />
                                        </div>
                                        <div class="col-lg-3">
                                            <br>
                                            <label for="" class="checkbox">
                                                <s:checkbox id="usrPrimary" name="usrPrimary"  value="%{primaryvalue}"/>IsPrimary
                                            </label>
                                        </div>
                                    </div>

                                </div> <br>
                            </div>

                            <div class="col-lg-3">
                                <label class="labelStylereq categoryReq" style="color:#56a5ec"><span class="accDetailsError">*</span>Categories :</label>
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
                            <div class="col-lg-9">
                                <label class="labelStylereq" style="color:#56a5ec">Description:</label>
                                <s:textarea id="usrDescription" name="usrDescription" cssClass="form-control "  value="%{usrDescription}" onfocus="clearErrosMsgForGrouping()" onkeyup="CalculateLeangth()"  />
                                <span><deascOpt></deascOpt></span>
                            </div>
                            <div class="col-lg-10"></div>
                            <div class="col-lg-2">   
                                <s:submit type="button"  cssStyle="margin:5px 0px;" value="" cssClass="add_searchButton form-control " onclick="doUserGroupingMethod()"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                </div>
                                <div class="col-lg-1"> 
                                    <label class="labelStylereq" style="color:#56a5ec"></label>
                                </div>

                            </div>
                        </div>
                    </div><!-- Add Form-->
                </div>
            </div>
            <div style="height: 274px"></div>

            <footer id="footer"><!--Footer-->
                <div class="footer-bottom" id="footer_bottom">
                    <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>