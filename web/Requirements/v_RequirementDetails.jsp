<%-- 
Document   : v_requirementDetails
Created on : May 5, 2015, 1:55:08 AM
Author     : miracle
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@page import="com.mss.msp.requirement.RequirementVTO"%>


<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Requirements&nbsp;Edit&nbsp;Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">

        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

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
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <style>


            .numeric_field{
                width: 30%!important ;
                padding: 0 5px !important;
            }

            .select_duration{
                width: 70% !important;
            }
        </style>
        <script type="text/javascript">
            ; function sortables_init() {
                // Find all tables with class sortable and make them sortable
                ; if (!document.getElementsByTagName) return;
                tbls = document.getElementById("vendorAssociationResults");
                sortableTableRows = document.getElementById("vendorAssociationResults").rows;
                sortableTableName = "vendorAssociationResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
            
            
            function showHideDuration(){
                var RequirementTaxTerm=document.getElementById('RequirementTaxTerm').value;
               
                if(RequirementTaxTerm=='CO'){
                //alert("CO");
                 document.getElementById('RequirementTaxTerm').style.visibility="hidden";
                 document.getElementById('RequirementTaxTerm').style.display="";
                    document.getElementById('duration').style.display = "block";
                }
                else
                {
                     //alert("do");
                      document.getElementById('RequirementTaxTerm').style.visibility="hidden";
                    document.getElementById('RequirementTaxTerm').style.display="";
                    document.getElementById('duration').style.display = "none";
                    document.getElementById('duration').style.width="0";
                    document.getElementById('RequirementTaxTerm').style.width="0";
                   
                }
            }
            
        </script>



    </head>

    <body style="overflow-x: hidden" oncontextmenu="return false" onload="showHideDuration();doOnloadEditRequirement(); paginationVC();">
        <div id="wrap">   
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div>
            </header>    
            <%
                String flag = "reqSearch";
                String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();
            %>
            <div id="main">
                <section id="generalForm"><!--form-->
                    <div class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/> 
                            <!-- content start -->
                            <div class="col-sm-12 col-md-9 col-lg-10 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="col-lg-14 ">


                                        <%-- TOP TABS BEGIN--%>
                                        <div class=" panel-info">
                                            <div class="panel-body" id="panel-task-body" >
                                                <s:if test="accountFlag=='Account'">
                                                    <div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px">
                                                        <label class="labelStyle"> Account&nbsp;Name: </label>                                         
                                                        <s:url var="myUrl" action="acc/viewAccount.action">
                                                            &nbsp;&nbsp;<s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                            <s:param name="accFlag">accDetails</s:param>
                                                        </s:url>
                                                        <s:a href='%{#myUrl}' id="v_reqDetailsAccountName" style="color: #0000FF;"><s:property value="%{account_name}"/></s:a>
                                                        </div>
                                                </s:if> 
                                                <div class="" style="float: left; margin-top:-5px; margin-bottom: -2px">
                                                    <s:url var="reqUrl" action="acc/viewAccount.action">
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                        <s:param name="accFlag">reqSearch</s:param>
                                                    </s:url>
                                                    <s:url var="myUrl" action="acc/viewAccount.action">
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                        <s:param name="accFlag">accDetails</s:param>
                                                    </s:url>
                                                    <s:url var="custReqUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                        <s:param name="orgid"><%=orgId%></s:param>
                                                        <s:param name="customerFlag">customer</s:param>
                                                        <s:param name="accountFlag">MyRequirements</s:param>
                                                    </s:url>
                                                    <s:url var="venReqUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                        <s:param name="accountFlag">MyRequirements</s:param>
                                                        <s:param name="vendor">yes</s:param>
                                                        <s:param name="orgid"><%=orgId%></s:param>
                                                    </s:url>


                                                    <label class=""> </label> 
                                                    <s:if test="accountFlag=='csr'" >
                                                        <s:a href='%{#myUrl}' id="v_reqAccountName" cssClass="breadcrum"><s:property value="%{accountName}"/></s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <s:a href='#' id="v_reqAccountName" cssClass="breadcrum"><s:property value="%{accountName}"/></s:a>
                                                    </s:else>
                                                    <s:if test="accountFlag=='csr'" >
                                                        <s:a href='%{#reqUrl}' id="v_requirementList" cssClass="breadcrum">>>&nbsp;Requirements&nbsp;List</s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <s:if test="vendor!='yes'">
                                                            <s:a href='%{#custReqUrl}' id="v_requirementList" cssClass="breadcrum">>>&nbsp;Requirements&nbsp;List</s:a>        
                                                        </s:if>
                                                        <s:else>
                                                            <s:a href='%{#venReqUrl}' id="v_requirementList" cssClass="breadcrum">>>&nbsp;Requirements&nbsp;List</s:a>        
                                                        </s:else>
                                                    </s:else>

                                                    <span class="breadcrumActive">>>&nbsp;
                                                        <label class="" ><b class="breadcrumLabelColor " > Job&nbsp;ID:</b> </label>  
                                                        <s:property value="%{jdId}"/>
                                                    </span>

                                                    <div class="pull-right" >
                                                        <div  style=" margin-top:0px;margin-bottom: -7px;">
                                                            <label class="" >&nbsp;<b class="breadcrumLabelColor " >| Job&nbsp;Title:</b> </label>                                         
                                                            <s:property value="%{requirementVTO.RequirementName}"/>
                                                        </div>
                                                    </div>


                                                </div>
                                                <%--<div class="" style="float: right; margin-top:-5px; margin-bottom: -2px">
                                                    <label class="">Id: </label>                                         
                                                    <span style="color:#FF8A14;"><s:property value="%{jdId}"/></span>
                                                </div--%>
                                                <br/>
                                                <!-- Nav tabs -->
                                                <ul class="nav nav-tabs">
                                                    <s:if test="vendor!='yes'">
                                                        <li class=" active_details" id="detailsLi" ><a aria-expanded="false" href="#details" data-toggle="tab">Requirement&nbsp;Edit</a>
                                                        </li>
                                                    </s:if>
                                                    <s:if test="vendor=='yes'">
                                                        <li class=" active_details" id="detailsLi" ><a aria-expanded="false" href="#details" data-toggle="tab">Requirement&nbsp;Details</a>
                                                        </li>
                                                    </s:if>
                                                    <s:if test="vendor!='yes'">
                                                        <li class="active_details" id="vendorAssociationLi"><a aria-expanded="false" href="#vendorAssociation" data-toggle="tab" onclick="return getVendorAssociationDetails()"   >Vendors</a>
                                                        </li>
                                                    </s:if>
                                                    <li class="active_details"  id="consultantListLi"><a aria-expanded="false" href="#consultantList" data-toggle="tab" onclick="return getConsultantList()"   >Submitted&nbsp;List</a></li>

                                                    <s:if test="accountFlag=='Account'">
                                                        <s:url var="myUrl" action="acc/viewAccount.action">
                                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                            <s:param name="accFlag"><%=flag%></s:param>
                                                        </s:url>
                                                        <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="v_reqBackButton"><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span>
                                                        </s:if>
                                                        <s:elseif test="accountFlag=='MyRequirements' && customerFlag=='customer'">

                                                        <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                            <s:param name="accountFlag">MyRequirements</s:param>
                                                            <s:param name="orgid"><%=orgId%></s:param>
                                                            <s:param name="customerFlag">customer</s:param>
                                                        </s:url>
                                                        <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="v_reqBackButton"><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span> 
                                                        </s:elseif>
                                                        <s:elseif test="accountFlag=='MyRequirements'">

                                                        <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                            <s:param name="accountFlag">MyRequirements</s:param>
                                                            <s:param name="vendor">yes</s:param>
                                                            <s:param name="orgid"><%=orgId%></s:param>
                                                        </s:url>
                                                        <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="v_reqBackButton"><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span> 
                                                        </s:elseif>
                                                        <s:elseif test="accountFlag=='OnlyMyRequirements'">

                                                        <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                            <s:param name="accountFlag">OnlyMyRequirements</s:param>
                                                            <s:param name="vendor">yes</s:param>
                                                            <s:param name="orgid"><%=orgId%></s:param>

                                                        </s:url>

                                                    </s:elseif>
                                                    <s:elseif test="accountFlag=='csr'">

                                                        <s:url var="myUrl" action="acc/viewAccount.action">                                                        
                                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                            <s:param name="accFlag"><%=flag%></s:param>

                                                        </s:url>
                                                        <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="v_reqBackButton"><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span> 
                                                        </s:elseif>
                                                        <s:elseif test="#session.primaryrole == 13">
                                                            <s:url var="myUrl" action="recruitment/consultant/getAllRequirementList.action">
                                                                <s:param name="orgid"><%=orgId%></s:param>    
                                                            </s:url>
                                                        <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="v_reqBackButton"><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span> 
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                <s:param name="accountFlag">MyRequirements</s:param>
                                                            <s:param name="orgid"><%=orgId%></s:param>
                                                            <s:param name="customerFlag">customer</s:param>
                                                        </s:url>
                                                        <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="v_reqBackButton"><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span> 
                                                        </s:else>
                                                </ul>

                                                <div class="tab-content">
                                                    <div class="tab-pane fade in active" id="details">
                                                        <div class="" id="selectivityProfileBox" style="float: left; margin-top: 5px;background-color: white">


                                                            <div class="col-lg-14">
                                                                <div class="panel-body">
                                                                    <s:form action="#" method="post" theme="simple" >
                                                                        <s:hidden name="RequirementId" id="RequirementId" value="%{requirementVTO.RequirementId}"/>
                                                                        <s:hidden id="userType" value="%{#session.typeOfUsr}" />

                                                                        <span><editrequirementerror></editrequirementerror></span>


                                                                        <div class="row">

                                                                            <div class="col-sm-6 col-md-6 col-lg-4">

                                                                                <label class="labelStyle" id="labelLevelStatusReq"> Title : </label><label><b class="ven_ReqEdit" ><s:property value="%{requirementVTO.RequirementName}"/></b></label></span>
                                                                                <s:hidden  id="RequirementName" type="text" value="%{requirementVTO.RequirementName}" name="RequirementName"/>
                                                                            </div>
                                                                            <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                <label class="labelStyle" id="labelLevelStatusReq">Start&nbsp;Date :</label><font class="ven_ReqEdit"><s:property value="%{requirementVTO.RequirementFrom}"/></font>
                                                                            </div>

                                                                            <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                <label class="labelStyle" id="labelLevelStatusReq"> Type :</label><font id="requremenType" class="ven_ReqEdit" ></font>
                                                                                <s:select name="RequirementTaxTerm" id="RequirementTaxTerm" value="%{requirementVTO.RequirementTaxTerm}" list="#@java.util.LinkedHashMap@{'CO':'Contract','PE':'Permanent'}" headerKey="DF" headerValue="--select--" cssClass="ConsultBoxStyles "  disabled="true"  cssStyle=" visibility:hidden;"/>

                                                                            </div>


                                                                            <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                <label class="labelStyle"  id="labelLevelStatusReq"> Positions :</label><b class="ven_ReqEdit"><s:property  value="%{requirementVTO.RequirementNoofResources}"/></b> 
                                                                            </div>

                                                                            <s:if test="requirementVTO.RequirementTaxTerm=='PE'">
                                                                                <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq"> Location :</label><font id="requrementVendorLocation" class="ven_ReqEdit"  ></font>
                                                                                    <s:select name="RequirementLocation" id="RequirementLocation" value="%{requirementVTO.RequirementLocation}" list="#@java.util.LinkedHashMap@{'ON':'Onsite','OF':'Offsite','OS':'Offshore'}" headerKey="DF" headerValue="--select--" cssClass="ConsultBoxStyles" onfocus="removeErrorMessages()" disabled="true" cssStyle="display:none"/>

                                                                                </div>
                                                                                <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                    <span style="white-space: nowrap"> <label class="labelStyle" id="labelLevelStatusReq">Billing&nbsp;Contact :</label><font id="billingContactVendor" class="ven_ReqEdit" ></font></span>
                                                                                    <s:select  id="billingContact" name="buildingContact" value="%{requirementVTO.billingContact}" list="EmployeeNames" headerKey="DF" headerValue="--select--" cssClass="ConsultBoxStyles " disabled="true" cssStyle="display: none"/> </td>

                                                                                </div>
                                                                            </s:if> 
                                                                            <s:if test="requirementVTO.RequirementTaxTerm=='PE'">
                                                                                <div class="col-sm-4">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq"> Min&nbsp;Rate :</label><font class="ven_ReqEdit" >$&nbsp;<s:property value="%{requirementVTO.RequirementTargetRate}"/>&nbsp;/Hr</font>
                                                                                </div>

                                                                            </s:if>
                                                                            <s:if test="requirementVTO.RequirementTaxTerm=='PE'">
                                                                                <div class="col-sm-4">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq"> Max&nbsp;Rate :</label><font class="ven_ReqEdit" >$&nbsp;<s:property value="%{requirementVTO.requirementMaxRate}"/>&nbsp;/Hr</font>    
                                                                                </div>
                                                                            </s:if>   



                                                                            <div id="duration" style="display: none;" class="col-sm-6 col-md-6 col-lg-4">
                                                                                <label class="labelStylereq" style="color:#56a5ec;"> Duration :</label><font class="ven_ReqEdit" style="padding-left:50px"><s:property value="%{requirementVTO.RequirementDuration}"/></font>&nbsp;<font id="reqDuration" style="color: black"></font>


                                                                                <s:textfield cssClass="form-control textMessageBox numeric_field" id="RequirementDuration" type="text" value="%{requirementVTO.RequirementDuration}"  placeholder="" onfocus="removeErrorMessages()" readonly="true" style="display: none"/>
                                                                                <s:select  id="requirementDurationDescriptor" value="%{requirementVTO.requirementDurationDescriptor}" list="#@java.util.LinkedHashMap@{'Hours':'Hours','Weeks':'Weeks','Months':'Months'}" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control select_duration " onfocus="removeErrorMessages()" style="display: none"/> 

                                                                            </div>





                                                                            <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                <label class="labelStyle" id="labelLevelStatusReq"> Req.Exp. :</label><font id="requiremenExperience" class="ven_ReqEdit" ></font>
                                                                                <s:select  id="RequirementYears" list="experienceMap"  value="%{requirementVTO.RequirementExp}" headerKey="-1" headerValue="--select--" cssClass="ConsultBoxStyles " onfocus="removeErrorMessages()" disabled="true" cssStyle="display:none"/>

                                                                            </div>

                                                                            <s:if test="requirementVTO.RequirementTaxTerm=='CO'">
                                                                                <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq"> Location :</label><font id="requrementVendorLocation" class="ven_ReqEdit" ></font>
                                                                                    <s:select name="RequirementLocation" id="RequirementLocation" value="%{requirementVTO.RequirementLocation}" list="#@java.util.LinkedHashMap@{'ON':'Onsite','OF':'Offsite','OS':'Offshore'}" headerKey="DF" headerValue="--select--" cssClass="ConsultBoxStyles" onfocus="removeErrorMessages()" disabled="true" cssStyle="display:none"/>

                                                                                </div>
                                                                                <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                    <span > <label class="labelStyle" id="labelLevelStatusReq">Billing&nbsp;Contact :</label><font id="billingContactVendor" class="ven_ReqEdit" ></font></span>
                                                                                    <s:select  id="billingContact" name="buildingContact" value="%{requirementVTO.billingContact}" list="EmployeeNames" headerKey="DF" headerValue="--select--" cssClass="ConsultBoxStyles " disabled="true" cssStyle="display: none"/> </td>

                                                                                </div>
                                                                            </s:if>   

                                                                            <s:if test="requirementVTO.RequirementTaxTerm=='CO'">
                                                                                <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq"> Min&nbsp;Rate :</label><font style="color:black">$&nbsp;<s:property value="%{requirementVTO.RequirementTargetRate}"/>&nbsp;/Hr</font>
                                                                                </div>
                                                                                <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq"> Max&nbsp;Rate :</label><font style="color:black">$&nbsp;<s:property value="%{requirementVTO.requirementMaxRate}"/>&nbsp;/Hr</font>     
                                                                                </div>
                                                                            </s:if>
                                                                            <div class="col-sm-6 col-md-6 col-lg-4">
                                                                                <label class="labelStylereq " style="padding-right: 20px">Req.Category :</label><font id="requrementVendorCategory" class="ven_ReqEdit" ></font>
                                                                                <s:select id="reqCategoryValue" name="reqCategoryValue" cssClass="ConsultBoxStyles"  theme="simple" list="%{reqCategory}" value="%{requirementVTO.reqCatgory}" disabled="true" cssStyle="display:none"/>

                                                                            </div>
                                                                        </div>
                                                                        <div class="col-lg-12 row">
                                                                            <label class="labelStyle" id="labelLevelStatusReq">Qualification :</label><font style="color:black" ><s:property value="%{requirementVTO.RequirementQualification}"/></font>
                                                                        </div>
                                                                        <div class="col-lg-12 row">
                                                                            <label class="labelStyle" id="labelLevelStatusReq">Description :</label> <p style="color:black" align="justify"><s:property value="%{requirementVTO.RequirementJobdesc}"/></p> 

                                                                        </div>
                                                                        <div class="charNum" id="JobcharNum"></div>
                                                                        <div class="col-lg-12 row">
                                                                            <label class="labelStyle" id="labelLevelStatusReq">Responsibilities :</label><p style="color:black" align="justify"><s:property value="%{requirementVTO.RequirementResponse}"/></p> 

                                                                        </div>
                                                                        <div class="charNum" id="ResponsecharNum"></div>


                                                                        <div class="col-sm-12">
                                                                            <div class="row">

                                                                                <span class="">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq" style="margin-right: 70px">Skill&nbsp;Set :</label><font id="requrementSkills"  style="color: black"></font>
                                                                                    <s:select cssClass="commentsStyle" name="skillCategoryValue"  id="skillCategoryValue" list="skillValuesMap" multiple="true" onfocus="removeErrorMessages()" value="%{requirementVTO.skillSetList}" disabled="true" cssStyle="display: none"/> 
                                                                                </span>

                                                                            </div>
                                                                        </div>
                                                                        <div class="row">     
                                                                            <div class="col-sm-12">        
                                                                                <div id="reqvendorPrefer">
                                                                                    <label class="labelStyle" id="labelLevelStatusReq" style="margin-right: 70px">Preferred&nbsp;Skill&nbsp;Set :</label><font id="requrementPreferedSkills"  style="color: black;"></font>  
                                                                                    <s:select cssClass="commentsStyle" name="preSkillCategoryValue"  id="preSkillCategoryValue" list="preSkillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()" value="%{requirementVTO.preSkillSetList}" disabled="true" cssStyle="display: none"/> 
                                                                                </div>
                                                                            </div>
                                                                        </div>  
                                                                        <s:if test="requirementVTO.RequirementComments!=''">
                                                                            <div class="col-lg-12 row">
                                                                                <label class="labelStyle" id="labelLevelStatusReq">Comments :</label> <p style="color:black" align="justify"><s:property value="%{requirementVTO.RequirementComments}"/></p> 

                                                                            </div>
                                                                            <div class="charNum" id="CommcharNum"></div>
                                                                        </s:if>

                                                                    </div>



                                                                    <div class="col-lg-12 ">  
                                                                        <div class="col-lg-6">
                                                                            <div class="charNum" id="SkillcharNum"></div></div>
                                                                        <div class="col-lg-6">
                                                                            <div class="charNum" id="PreferredSkillcharNum"></div>
                                                                        </div></div>

                                                                    <div id="charNum"></div>


                                                                </s:form>      
                                                            </div>    

                                                            <s:if test="accountFlag !='csr'">  
                                                                <s:if test="vendor!='yes'">
                                                                    <div class="col-lg-10"></div>
                                                                    <div class="col-lg-2">
                                                                        <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" id="update" onclick="return updaterequirements();" value="" theme="simple"><i class="fa fa-refresh"></i>&nbsp;Update</s:submit>
                                                                        </div>
                                                                </s:if>
                                                            </s:if>
                                                        </div> 

                                                    </div>
                                                    <div class="tab-pane fade in " id="vendorAssociation">

                                                        <div class="" id="profileBox" style="float: left; margin-top: 5px;background-color: white">
                                                            <div>
                                                                <div class="backgroundcolor" >
                                                                    <div class="panel-heading">
                                                                        <h4 class="panel-title">

                                                                            <font color="#ffffff">Vendor&nbsp;Search</font>
                                                                            <i id="updownArrow" onclick="toggleContent('vendorSearchForm')" class="fa fa-angle-up"></i>
                                                                            <s:if test="accountFlag=='Account'">
                                                                                <s:url var="myUrl" action="acc/viewAccount.action">
                                                                                    <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                                                    <s:param name="accFlag"><%=flag%></s:param>
                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span>
                                                                            </s:if>
                                                                            <s:elseif test="accountFlag=='MyRequirements' && customerFlag=='customer'">

                                                                                <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                                    <s:param name="accountFlag">MyRequirements</s:param>
                                                                                    <s:param name="orgid"><%=orgId%></s:param>
                                                                                    <s:param name="customerFlag">customer</s:param>
                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                                            </s:elseif>
                                                                            <s:elseif test="accountFlag=='MyRequirements'">

                                                                                <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                                    <s:param name="accountFlag">MyRequirements</s:param>
                                                                                    <s:param name="vendor">yes</s:param>
                                                                                    <s:param name="orgid"><%=orgId%></s:param>
                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"> <s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                                            </s:elseif>
                                                                            <s:elseif test="accountFlag=='OnlyMyRequirements'">

                                                                                <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                                    <s:param name="accountFlag">OnlyMyRequirements</s:param>
                                                                                    <s:param name="vendor">yes</s:param>
                                                                                    <s:param name="orgid"><%=orgId%></s:param>
                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                                            </s:elseif>

                                                                            <s:elseif test="accountFlag=='csr'">

                                                                                <s:url var="myUrl" action="acc/viewAccount.action">                                                        
                                                                                    <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                                                    <s:param name="accFlag"><%=flag%></s:param>

                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                                            </s:elseif>
                                                                            <s:elseif test="#session.primaryrole == 13">
                                                                                <s:url var="myUrl" action="recruitment/consultant/getAllRequirementList.action">
                                                                                    <s:param name="orgid"><%=orgId%></s:param>    
                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                                            </s:elseif>
                                                                            <s:else>
                                                                                <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                                    <s:param name="accountFlag">MyRequirements</s:param>
                                                                                    <s:param name="orgid"><%=orgId%></s:param>
                                                                                    <s:param name="customerFlag">customer</s:param>
                                                                                </s:url>
                                                                                <span class="pull-right" style="margin-right: 40px"><s:a href='%{#myUrl}' id="r_venBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                                            </s:else>                                                                  

                                                                        </h4>
                                                                    </div>
                                                                </div>
                                                                <s:hidden id="req_id" value="%{RequirementId}"/>

                                                                <div class="pull-right" style="float: left; margin-top:0px;margin-bottom: -7px;">
                                                                    <label class=""> Job&nbsp;Title: </label>                                         
                                                                    <span style="color: #FF8A14;"><s:property value="%{requirementVTO.RequirementName}"/></span>
                                                                </div>
                                                                <div class="col-sm-12" id="vendorSearchForm">
                                                                    <div class="col-lg-4">   
                                                                        <label class="labelStylereq" style="color:#56a5ec;"> Type&nbsp;Of&nbsp;Tier </label><s:select name="tireType" id="tireTypeSearch" value="" list="%{typesTiers}" headerKey="-1" headerValue="All" cssClass="SelectBoxStyles form-control" onchange="return getVendorsNames()"/>
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <label class="labelStylereq" style="color:#56a5ec;"> Status </label><s:select name="status" id="status" value="" list="#@java.util.LinkedHashMap@{'DF':'All','Active':'Active','In-Active':'In-Active'}" cssClass="SelectBoxStyles form-control"/>
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <div class="row">

                                                                            <div class="col-lg-6"> 
                                                                                <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                <button class="cssbutton form-control vendorAsso_popup_open" id="v_reqAddButton" onclick="return associationOverlay()"/><i class="fa fa-plus-square"></i>&nbsp;Add</button>
                                                                            </div> 
                                                                            <div class="col-lg-6">
                                                                                <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                <input type="button" class="cssbutton_action_search form-control" id="v_reqSearchButton" value="Search" onclick="return searchVendorAssociationDetails()"/>
                                                                            </div>  
                                                                        </div>   
                                                                    </div>                                                       
                                                                </div>
                                                                <div id="vendorAsso_popup">

                                                                    <div id="vendorAssocitaionOverlay">
                                                                        <div class="backgroundcolor">
                                                                            <table>
                                                                                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Add&nbsp;Association&nbsp;&nbsp; </font></h4></td>
                                                                                <span class="pull-right"> <h5 ><a href="">&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="v_vendorAddAssociationOverlayClose" class="vendorAsso_popup_close" onclick="associationOverlay();"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                                            </table>
                                                                        </div><div>
                                                                            <span><saveVendorAssociation></saveVendorAssociation></span></div>
                                                                        <div class="updateCss ReqinputStyle ">   
                                                                            <table>
                                                                                <label class="labelStyle" id="labelLevelStatusReq"><span class="reqError">*</span>  Type&nbsp;Of&nbsp;Tier </label><s:select name="tireType" id="tireType" value="" list="%{typesTiers}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onchange="return getVendorsNames()" onfocus="return removeVendorErrorMsg();"/>
                                                                            </table>
                                                                        </div> 
                                                                        <div class="updateCss ReqinputStyle ">     
                                                                            <table>
                                                                                <label class="labelStyle" id="labelLevelStatusReq"><span class="reqError">*</span>  Access&nbsp;Time </label><s:textfield name="accessTime" id="accessTime" value=""  cssClass="form-control dateImage" onkeypress="return enterDateRepository(this)" onfocus="return removeVendorErrorMsg();"/>
                                                                            </table>
                                                                        </div> 
                                                                        <div class="updateCss ReqinputStyle ">     
                                                                            <table> <label class="labelStyle" id="labelLevelStatusReq"><span class="reqError">*</span> Vendor&nbsp;Names </label>
                                                                                <s:select cssClass="selectBoxStyle form-control"  list="{}" name="vendorNames" id="vendorNames" value="%{vendorNames}"  multiple="true" size="4" onfocus="return removeVendorErrorMsg();"/>   
                                                                            </table>
                                                                        </div> 
                                                                        <div class="col-lg-10"></div>
                                                                        <div class="col-lg-2">
                                                                            <s:submit cssStyle="margin:5px 0px;" id="v_reqAddOverlaySaveButton" cssClass=" add_searchButton form-control col-sm-offset-10 btn cssbutton" value="" type="button" onclick="return saveVendorAssociation()"><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div id="vendorAssoEdit_popup">

                                                                        <div id="vendorAssocitaionEditOverlay">
                                                                            <div class="backgroundcolor">
                                                                                <table>
                                                                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Edit&nbsp;Association&nbsp;&nbsp; </font></h4></td>
                                                                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="v_vendorEditAssociationOverlayClose" class="vendorAssoEdit_popup_close" onclick="associationEditOverlayclose()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                                                </table>

                                                                            </div>
                                                                            <span class="" style="margin-left: 15px; color:#FC9A11;">Name:&nbsp;<vendorNameDisplay></vendorNameDisplay></span><br>
                                                                        <s:hidden id="vendorId" value=""/>

                                                                        <div class="updateCss ReqinputStyle " style="margin-left: 10px">   
                                                                            <table>
                                                                                <label class="labelStyle" id="labelLevelStatusReq"> Type&nbsp;Of&nbsp;Tier </label><s:select name="tireTypeEdit" id="tireTypeEdit" value="" list="%{typesTiers}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onchange="return getVendorsNames()" disabled="true"/>
                                                                            </table>
                                                                        </div> 

                                                                        <div class="updateCss ReqinputStyle " style="margin-left: 10px">     
                                                                            <table>
                                                                                <label class="labelStyle" id="labelLevelStatusReq"> Status </label><s:select name="statusEdit" id="statusEdit" value="" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" cssClass="selectBoxStyle form-control"/>
                                                                            </table>
                                                                        </div> 
                                                                        <div class="col-lg-10"></div>
                                                                        <div class="col-lg-2">
                                                                            <s:submit  cssStyle="margin:5px 0px;" id="v_vendorAssociationUpdateButton" cssClass=" add_searchButton form-control vendorAssoEdit_popup_close"  value="" type="button" onclick="return updateVendorAssociation()"><i class="fa fa-floppy-o"></i>&nbsp;Update</s:submit>
                                                                            </div>
                                                                        </div>
                                                                    </div>


                                                                </div> 
                                                                <div class="col-sm-12">
                                                                    <br/> 
                                                                    <div class="emp_Content" id="emp_div" align="">
                                                                        <table id="vendorAssociationResults" class="responsive CSSTable_task sortable" border="5">
                                                                            <tbody>
                                                                                <tr> 
                                                                                    <th>Vendor</th>
                                                                                    <th class="unsortable">Vendor&nbsp;Tier </th>
                                                                                    <th class="unsortable">No.Of.Submissions</th>
                                                                                    <th class="unsortable"> Avg.Rate</th>
                                                                                    <th class="unsortable">Access&nbsp;Time</th>
                                                                                    <th class="unsortable">Status</th>

                                                                                </tr>
                                                                            </tbody>
                                                                        </table>
                                                                        <br/>
                                                                        <label class="page_option"> Display <select id="vpaginationOption" class="disPlayRecordsCss" onchange="vpagerOption()" style="width: auto">
                                                                                <option>10</option>
                                                                                <option>15</option>
                                                                                <option>25</option>
                                                                                <option>50</option>
                                                                            </select>
                                                                            Accounts per page
                                                                        </label>
                                                                        <div id="loadingVendor" class="loadingImg" style="display: none">
                                                                            <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                                                    </div>
                                                                    <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                                    <script type="text/javascript">
                                                                        var pag = new Pager('vendorAssociationResults', 10); 
                                                                        pag.init(); 
                                                                        pag.showPageNav('pag', 'pageNavPosition'); 
                                                                        pag.showPage(1);
                                                                    </script>
                                                                </div>
                                                            </div>
                                                        </div> 
                                                    </div>


                                                    <div class="tab-pane fade in " id="consultantList">
                                                        <div class="" id="profileBox" style="float: left; margin-top: 5px;background-color: white">
                                                            <div class="backgroundcolor" style="background-color: white">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">

                                                                        <i id="updownArrowSub" style="color:#56a5ec" onclick="toggleContentRequirementSubmittedList('consultantSearchForm')" class="fa fa-sort-asc"></i>

                                                                    </h4>
                                                                </div>
                                                            </div>
                                                            <div class="pull-right" style="float: left; margin-top:0px; margin-bottom: -7px">
                                                                <label class=""> Job&nbsp;Title: </label>                                         
                                                                <span style="color: #FF8A14;"><s:property value="%{requirementVTO.RequirementName}"/></span>
                                                            </div>

                                                            <div class="col-sm-12" id="consultantSearchForm">
                                                                <s:form action="" theme="simple" id="consultantSearchForm">
                                                                    <s:hidden name="vendor" id="vendor" value="%{vendor}"/>
                                                                    <s:hidden name="RequirementId" id="RequirementId" value="%{requirementVTO.RequirementId}"/>
                                                                    <s:hidden name="jdId" id="jdId" value="%{jdId}"/>

                                                                    <s:hidden name="downloadFlag" id="downloadFlag" value="%{downloadFlag}"/>
                                                                    <s:hidden name="customerFlag" id="customerFlag" value="%{customerFlag}"/>
                                                                    <s:hidden name="accountFlag" id="accountFlag" value="%{accountFlag}" ></s:hidden>
                                                                    <s:hidden name="accountSearchID" id="accountSearchID" value="%{accountSearchID}"></s:hidden>
                                                                    <s:hidden name="gridDownload" id="gridDownload" value=""/>
                                                                    <div class="row">
                                                                        <s:hidden name="vendorName" id="vendorName" />
                                                                        <div class="col-sm-4">

                                                                            <label style="color:#56a5ec;" class="labelStylereq">First&nbsp;Name&nbsp;&nbsp;</label>
                                                                            <s:textfield cssClass="form-control" name="consult_name" id="consult_name" placeholder="First Name" value="%{consult_name}" tabindex="1" maxLength="30"/>

                                                                        </div>
                                                                        <div class="col-sm-4">

                                                                            <label style="color:#56a5ec;" class="labelStylereq">Last&nbsp;Name&nbsp;&nbsp;</label>
                                                                            <s:textfield cssClass="form-control" name="consult_lstname" id="consult_lstname" placeholder="Last Name" value="%{consult_lstname}" tabindex="2" maxLength="30"/>

                                                                        </div>
                                                                        <div class="col-sm-4">

                                                                            <label style="color:#56a5ec;" class="labelStylereq">SSN&nbsp;Number&nbsp;&nbsp;</label>
                                                                            <s:textfield cssClass="form-control" tabindex="3" name="consult_ssnNo" id="consult_ssnNo"  value="%{consult_ssnNo}" placeholder="SSN Number"/>

                                                                        </div>

                                                                        <div class="col-sm-4">
                                                                            <label style="color:#56a5ec;" class="labelStylereq">E-Mail</label>
                                                                            <s:textfield cssClass="form-control" name="consult_email" id="consult_email" placeholder="E-Mail" value="%{consult_email}" tabindex="4" maxLength="60"/>

                                                                        </div>


                                                                        <div class="col-sm-4">
                                                                            <label style="color:#56a5ec;" class="labelStylereq">Skill&nbsp;Set</label>
                                                                            <s:textfield cssClass="form-control" name="consult_skill" id="consult_skill" placeholder="Skill Set" value="%{consult_skill}" tabindex="5" maxLength="100"/>
                                                                        </div>

                                                                        <div class="col-sm-4">
                                                                            <label style="color:#56a5ec;" class="labelStylereq">Phone</label>
                                                                            <s:textfield cssClass="form-control" name="consult_phno" id="consult_phno" placeholder="Phone" value="%{consult_phno}" tabindex="6" maxLength="15"/>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <label class="labelStylereq" style="color:#56a5ec">Experience</label>
                                                                            <s:textfield cssClass="form-control" name="yearExperience" id="yearExperience" placeholder="Experience" value="" tabindex="8" maxLength="15"/>
                                                                        </div>

                                                                        <s:hidden name="consultantFlag" id="consultantFlag" value="%{consultantFlag}"/>


                                                                        <div class="col-sm-4 pull-right">


                                                                            <div class="pull-right">
                                                                                <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                <s:submit type="button" cssClass="add_searchButton form-control contact_search" tabindex="7" id="searchButton" cssStyle="margin:5px 0px;" value="" onclick="return getConsultantListBySearch();"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                                </div>
                                                                            <s:if test="consultantFlag == 1">
                                                                                <div class="col-sm-6 pull-right">

                                                                                    <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                                    <a href="/<%=ApplicationConstants.CONTEXT_PATH%>/recruitment/consultant/addConsultant.action" id="v_reqAddConsultant" ><input type="button" class=" cssbutton form-control" tabindex="8" value="Add Consultant"></a> &nbsp;

                                                                                </div>
                                                                            </s:if>
                                                                        </div>
                                                                        <s:hidden name="techSearch" id="techSearch" value="%{techSearch}"/>
                                                                        <s:hidden name="reqFlag" id="reqFlag" value="%{reqFlag}"/>

                                                                    </div>

                                                                </s:form>
                                                            </div>

                                                            <div class="col-sm-12">
                                                                <s:form>
                                                                    <s:hidden id="jdId" name="jdId" value="%{jdId}"/>
                                                                    <s:if test='downloadFlag=="noResume"'>
                                                                        <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                                                        </s:if>
                                                                        <s:if test='downloadFlag=="noFile"'>
                                                                        <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                                                        </s:if>  
                                                                    <br/>
                                                                    <span id="conWithRejectValid"></span>
                                                                    <div class="task_content" id="task_div" align="center" style="display: none" >

                                                                        <div>
                                                                            <div>

                                                                                <table id="consultantListTable" class="responsive CSSTable_task" border="5"cell-spacing="2">

                                                                                    <tbody>
                                                                                        <tr>
                                                                                            <s:if test="vendor!='yes'">
                                                                                                <th>Vendor</th>
                                                                                            </s:if>
                                                                                            <th>Candidate&nbsp;Name</th>
                                                                                            <th>Submitted&nbsp;Date</th>
                                                                                            <th>SSN&nbsp;No.</th>
                                                                                            <s:if test="vendor=='yes'">
                                                                                                <th>E-Mail</th>
                                                                                            </s:if>
                                                                                            <th>Skill&nbsp;Set</th>
                                                                                            <s:if test="vendor=='yes'">
                                                                                                <th>Phone&nbsp;No</th>
                                                                                            </s:if>
                                                                                                 <th>Experience</th>
                                                                                            <th>Status</th>
                                                                                            <th>Rate</th>
                                                                                            <th>Resume</th>
                                                                                            <s:if test="vendor!='yes'">
                                                                                                <th>Reviews</th>
                                                                                            </s:if>
                                                                                            <th>Proceed</th>
                                                                                            <s:if test="vendor=='yes'">
                                                                                                <th>Withdrawn</th>
                                                                                            </s:if>

                                                                                        </tr>

                                                                                    </tbody>
                                                                                </table>
                                                                                <br/>
                                                                                <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                                        <option>10</option>
                                                                                        <option>15</option>
                                                                                        <option>25</option>
                                                                                        <option>50</option>
                                                                                    </select>
                                                                                    Accounts per page
                                                                                </label>
                                                                                <div id="loadingConsultant" class="loadingImg">
                                                                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                                                                </div>
                                                                                <div align="right" id="taskpageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                                                <script type="text/javascript">
                                                                                    var pager = new Pager('consultantListTable', 10); 
                                                                                    pager.init(); 
                                                                                    pager.showPageNav('pager', 'taskpageNavPosition'); 
                                                                                    pager.showPage(1);
                                                                                </script>

                                                                            </div>
                                                                            <div class="com-sm-12">
                                                                                <div id ="downloading_grid" class="col-sm-6 pull-right">
                                                                                    <div class="pull-right  req_btn col-sm-0" style="margin: 0px 0px 0px 10px">
                                                                                        <div onclick="downloadPDFSubmittedList()" id="v_submittedListDownloadPDFButton" class="fa fa-download cssbutton form-control">&nbsp;DownloadPDF</div>
                                                                                    </div>
                                                                                    <div class="pull-right  req_btn col-sm-0">
                                                                                        <div onclick="downloadXLSSubmittedList()" id="v_submittedListDownloadXLSButton" class=" fa fa-download cssbutton form-control">&nbsp;DownloadXLS</div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                        </div>


                                                                    </s:form>

                                                                </div>
                                                                <div id="Migration_popup">
                                                                    <div id="recruiterBox" class="marginTasks">
                                                                        <div class="backgroundcolor">
                                                                            <table>
                                                                                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Forwarded By Details&nbsp;&nbsp; </font></h4></td>
                                                                                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="Migration_popup_close" onclick="migration_overlayClose()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                                            </table>
                                                                        </div>
                                                                        <div>
                                                                            <form>
                                                                                <span><mig></mig></span>
                                                                                <s:hidden name="consult_id" id="consult_id" value=""/>
                                                                                <s:hidden name="req_Id" id="req_Id" value="%{requirementVTO.RequirementId}"/>
                                                                                <s:hidden name="acc_Id" id="acc_Id" value=""/>
                                                                                <s:hidden name="acc_type" id="acc_type" value=""/>
                                                                                <!--s:property value="%{vendor}"/-->
                                                                                <s:hidden name="vendor" id="vendor" value="%{vendor}"/>
                                                                                <s:hidden name="loginId" id="loginId" value="%{#session['loginId']}"/>
                                                                                <s:hidden id="cName" name="cName" value=""/>

                                                                                <center>   
                                                                                    <table>

                                                                                        <div class="inner-techReviewdiv-elements">
                                                                                            <s:textfield cssClass="form-control " 
                                                                                                         id="migrationEmailId" 
                                                                                                         type="text" value="" 
                                                                                                         name="migrationEmailId"
                                                                                                         placeholder="" label="EmailId"
                                                                                                         onfocus="removeErrorMessages()"/>


                                                                                        </div>

                                                                                    </table>

                                                                                </center>
                                                                                <br/>
                                                                                <s:submit cssClass="cssbutton migrationButton"
                                                                                          id="migrate" 
                                                                                          onclick="return userMigration();"
                                                                                          value="Migrate" />
                                                                            </form>             
                                                                        </div>
                                                                        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                                                    </div>
                                                                </div>

                                                                <br><br>


                                                            </div>


                                                        </div>
                                                    </div>
                                                    <!--End Tabs-->
                                                </div>

                                            </div><%-- panel task body complete--%>





                                        </div>

                                        <%--close of future_items--%>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- content end -->
                        <%--  Skill Overlay --%>
                        <div id="recSkillOverlay_popup">
                            <div id="consultantSkillSetBox" class="marginTasks">
                                <div class="backgroundcolor">
                                    <table>
                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill&nbsp;Details&nbsp;&nbsp; </font></h4></td>
                                        <span class="pull-right"> <h5>&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="v_consSkillOverlayCloseButton" class="recSkillOverlay_popup_close" onclick="consultantSkillCloseOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                    </table>
                                </div>
                                <div>

                                    <s:textarea name="customerSkillDetails"   id="customerSkillDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                                </div>
                                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                            </div>
                        </div>

                        <div id="SOW_popup">
                            <div id="SOWBox" class="marginTasks">
                                <div class="backgroundcolor">
                                    <table>
                                        <s:if test="requirementVTO.RequirementTaxTerm=='CO'">
                                            <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;SOW&nbsp;Process &nbsp;&nbsp; </font></h4></td>
                                                    </s:if>
                                                    <s:else>
                                            <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Finder&nbsp;Fee&nbsp;Process &nbsp;&nbsp; </font></h4></td>        
                                                    </s:else>
                                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="v_sowPopUpClose" class="SOW_popup_close" onclick="SOWPopupClose()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                    </table>
                                </div>
                                <div class="col-sm-12">
                                    <s:hidden name="rateSalary" id="rateSalary"/>
                                    <s:hidden name="conId" id="conId"/>
                                    <span id="SOWSpan"><res></res></span>
                                    <s:if test="requirementVTO.RequirementTaxTerm=='CO'">
                                        <s:select name="SOWSelectValue" id="SOWSelectValue" cssClass="SelectBoxStyles form-control" list="#@java.util.LinkedHashMap@{'SOWProceed':'SOW Proceed','Denied':'Denied'}"/>
                                    </s:if>
                                    <s:else>
                                        <s:select name="SOWSelectValue" id="SOWSelectValue" cssClass="SelectBoxStyles form-control" list="#@java.util.LinkedHashMap@{'FinderFee':'Finder Fee','Denied':'Denied'}"/>
                                    </s:else>
                                    <br>
                                    <div class="pull-right " > 
                                        <s:submit type="button" cssClass="cssbutton fa fa-floppy-o" id="contactSend" value="Save" onclick="saveSOWFinderFeeType();"/> 
                                    </div>
                                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                </div>
                            </div>
                        </div>

                        <div id="conWithdraw_popup">
                            <div id="conWithdrawBox" class="marginTasks">
                                <div class="backgroundcolor">
                                    <table>
                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor" id="commentsLabel">&nbsp;Comments </font></h4></td>        
                                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="v_withdrawCommentsClose" class="conWithdraw_popup_close" onclick="conWithdrawClose('close')" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                    </table>
                                </div>
                                <s:hidden name="reqwithdrawId" id="reqwithdrawId"/>
                                <s:hidden name="conWithdrawId" id="conWithdrawId"/>
                                <div class="required">
                                    <label >Comments</label>
                                    <s:textarea name="withdrawComments" id="withdrawComments"  cssClass="form-control textareaSkillOverlay" />
                                </div>

                                <div class="pull-right btn_pull" id="withdrawButtonDiv">
                                    <s:submit type="button" cssClass="cssbutton conWithdraw_popup_close" id="contactSend" value="OK" onclick="conWithdrawClose('ok');"> <i class="fa fa-check-circle-o"></i>&nbsp;Ok</s:submit>

                                    </div>
                                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                </div>
                            </div>


                            <div id="consultantLoginOverlay_popup" >
                                <div id="consultantLoginBox" class="marginTasks">
                                    <div class="backgroundcolor">
                                        <table>
                                            <tr><td><h4 style="font-family:cursive"><font class="titleColor" >&nbsp;&nbsp; Consultant&nbsp;Login&nbsp;Credentials&nbsp;&nbsp; </font></h4></td>
                                            <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="v_consCredentialsOverlayClose" class="consultantLoginOverlay_popup_close" onclick="consultantLoginCredential()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                        </table>
                                    </div>
                                    <div>
                                        <div class="inner-consuldiv-elements">
                                            <div  id="outputMessage"></div>
                                        <s:hidden id="consultantEmail" name="consultantEmail" value=""/>
                                        <s:hidden id="consultantId" name="consultantId" value=""/>
                                        <div  id="consultantdivEmail" ></div>
                                    </div>
                                    <div class="pull-left " >
                                        <s:submit type="button" cssClass="consultantLoginOverlay_popup_close" id="contactCancel" onclick="consultantLoginCredential()" value="Cancel"/>  
                                    </div>  
                                    <div class="pull-right " > 

                                        <s:submit type="button" cssClass="cssbutton" id="contactSend" value="Send" onclick="saveConsultantLoginDetails()"/> 

                                    </div>

                                </div>
                                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                            </div>   
                        </div>                  

                        <%--close of overlay --%> 
                </section>
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
        <script type="text/javascript">
            var techSearch=document.getElementById("techSearch").value;
            var flag=document.getElementById("downloadFlag").value;
            var reqFalg=document.getElementById("reqFlag").value;
            if(flag=="noResume"||flag=="noFile")
            {
                document.getElementById('consultantListLi').className='active active_details';
                document.getElementById('details').className='tab-pane fade in';
                document.getElementById('consultantList').className='tab-pane fade in active';
       
       
                if(techSearch=="search")
                { getConsultantListBySearch();}
                else if(techSearch!="search")
                {
                    getConsultantList();}
            }
            else if(reqFalg=="consultantTab")
            {
                document.getElementById('consultantListLi').className='active active_details';
                document.getElementById('details').className='tab-pane fade in';
                document.getElementById('consultantList').className='tab-pane fade in active';
                getConsultantList();
         
            }
            else
            {
                document.getElementById('detailsLi').className='active active_details';
         
            }
        </script>
        <script>
            setTimeout(function(){              
                $('#resume').remove();
            },3000);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <s:if test="#session.typeOfUsr=='VC'" >
            <script>
            
                $('#skillCategoryValue').selectivity({
                    
                    readOnly:true
                });
            
                $('#preSkillCategoryValue').selectivity({
                    
                    readOnly:true
                });
            
            
            </script>
        </s:if>
        <s:else>
            <script>
            
                $('#skillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            
            
            
                $('#preSkillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            
            
            </script>
        </s:else>
        <script>
            ;   sortables_init();
        </script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=10;
            function vpagerOption(){

                var paginationSize = document.getElementById("vpaginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                $('#vendorAssociationResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#vendorAssociationResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/javascript" >
            $("#consult_phno").mask("(999)-999-9999");
        </script>

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                $('#consultantListTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#consultantListTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>

    </body>
</html>

