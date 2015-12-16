<%--
    Document   : AccountDetails
    Created on : May 3, 2015, 2:08:50 PM
    Author     : rama krishna<lankireddy@miraclesoft.com>
--%>

<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: SOW Edit Page</title>

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
        
        <script type="text/javascript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
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
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/techReviewAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/sowAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.price_format.2.0.min.js"/>"></script>


        <script>
            var pager;
            
//            function pagerOption(){
//                //alert("HI")
//                paginationSize = document.getElementById("paginationOption").value;
//                if(isNaN(paginationSize))
//                //alert(paginationSize);
//
//                pager = new Pager('sowResults', parseInt(paginationSize));
//            pager.init();
//            pager.showPageNav('pager', 'pageNavPosition');
//            pager.showPage(1);
//
//        };
        </script>
    </head>
    <body onload="hoursValidations();">
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
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                        <div class="features_items">

                            <div class="col-sm-14 ">
                                <ul class="nav nav-tabs">
                                    <s:if test="serviceType=='PE'">
                                        <li class="active_details" id="edit" ><a aria-expanded="false" href="#SOWEdit" data-toggle="tab">Finder Fee</a>
                                        </li>
                                    </s:if>
                                    <s:else>
                                        <li class="active_details" id="edit" ><a aria-expanded="false" href="#SOWEdit" data-toggle="tab">Contracts</a>
                                        </li>
                                    </s:else>

                                    <li class="active_details" id="Attachments" ><a aria-expanded="false" href="#attachments" onclick="PagerOption_attch()" data-toggle="tab">Attachments</a>
                                    </li>
                                    <s:if test="serviceType=='CO'">
                                        <li class="active_details" id="RecreatedList" ><a aria-expanded="false" href="#recreatedList" data-toggle="tab" onclick="getRecreatedList();pagerOption();">Recreated List</a>
                                        </li>
                                    </s:if>
                                </ul>

                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="SOWEdit">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <s:if test="serviceType=='PE'">
                                                            <font color="#ffffff">Finder Fee </font>
                                                        </s:if>
                                                        <s:else>
                                                            <font color="#ffffff">Contracts</font>
                                                        </s:else>
                                                        <s:url var="myUrl" action="getSowList.action">
                                                            <%--<s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> --%>
                                                        </s:url>
                                                        <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                            <%--<span class="pull-right"><s:a href='%{#contechReqEditUrl}'><img  src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>--%>
                                                    </h4>
                                                </div>
                                            </div>

                                            <s:form action="/sag/sow/SOWInsertAction"  name="SOWForm" onsubmit="return sowValidation();" theme="simple">

                                                <s:hidden name="consultantId" id="consultantId" value="%{consultantId}"/>
                                                <s:hidden name="requirementId" id="requirementId" value="%{requirementId}"/>
                                                <s:hidden name="customerId" id="customerId" value="%{customerId}"/>
                                                <s:hidden name="vendorId" id="vendorId" value="%{vendorId}"/>
                                                <s:hidden name="consultantName" id="consultantName" value="%{consultantName}"/>
                                                <s:hidden name="vendorName" id="vendorName" value="%{vendorName}"/>
                                                <s:hidden name="requirementName" id="requirementName" value="%{requirementName}"/>
                                                <s:hidden name="customerName" id="customerName" value="%{customerName}"/>
                                                <s:hidden name="serviceId" id="serviceId" value="%{serviceId}"/>
                                                <s:hidden name="serviceType" id="serviceType" value="%{serviceType}"/>
                                                <s:hidden id="typeOfUser" value="%{#session['typeOfUsr']}"/>
                                                <s:hidden id="his_id" name="his_id" value="%{his_id}"/>
                                                <s:hidden id="sowHisStatus" name="sowHisStatus" value="%{sowHisStatus}"/>
                                                <s:hidden name="serviceTypeRedirect" id="serviceTypeRedirect" value="%{serviceType}"/>
                                                <div class="inner-reqdiv-elements">
                                                    <span id="errorSpan"><e></e></span>

                                                    <div class="row">
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Consultant Name:</label>
                                                            <s:textfield cssClass="form-control" id="consultantName"  value="%{consultantName}" readonly="true"/>
                                                        </div>

                                                        <s:if test="#session.typeOfUsr == 'AC'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Name:</label>
                                                                <s:textfield cssClass="form-control" id="vendorName" value="%{vendorName}" readonly="true"/>
                                                            </div>
                                                        </s:if>
                                                        <s:if test="#session.typeOfUsr == 'VC'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Name:</label>
                                                                <s:textfield cssClass="form-control" id="customerName"  value="%{customerName}" readonly="true"/>
                                                            </div>
                                                        </s:if>

                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Requirement Name:</label>
                                                            <s:textfield cssClass="form-control" id="requirementName"  value="%{requirementName}" readonly="true"/>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Service Version:</label>
                                                            <s:textfield cssClass="form-control" id="serviceVersion" name="serviceVersion" value="%{serviceVersion}" readonly="true"/>
                                                        </div>
                                                        <%--<div class="col-lg-4">
                                                        <label class="labelStylereq" style="color:#56a5ec">Pay Mode:</label>
                                                        <s:select id="payType" cssClass="SelectBoxStyles form-control" name="payType" list="#@java.util.LinkedHashMap@{'ch':'Cheque','NEFT':'NEFT'}"/>
                                                    </div>--%>

                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Status:</label>
                                                            <s:textfield cssClass="form-control" name="status" id="status"  value="%{status}" readonly="true"/>   

                                                        </div>
                                                        <s:if test="#session.typeOfUsr == 'VC'">    
                                                            <div class="col-sm-4 required ">
                                                                <s:if test="serviceType=='PE'">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Pay Rate(in Thousands):</label>
                                                                </s:if>
                                                                <s:else>
                                                                    <label class="labelStylereq" style="color:#56a5ec">Pay Rate:</label>
                                                                </s:else>    
                                                                <div class="input-group">
                                                                    <%--<s:textfield cssClass="form-control" id="rateSalary"  name="rateSalary" value="%{rateSalary}" >
                                                                        <span class="urlDomain">$/Hr</span>
                                                                    </s:textfield>--%>
                                                                    <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                                    <s:textfield cssClass="form-control" id="targetRate"  name="targetRate" value="%{targetRate}" readonly="true"/>
                                                                    <s:if test="serviceType!='PE'">
                                                                        <span class="input-group-addon" style="padding-top: 5px">/</span>
                                                                        <s:select id="targetRateType" cssClass="SelectBoxStyles form-control input-group-addon" name="targetRateType" value="%{targetRateType}" list="#@java.util.LinkedHashMap@{'HR':'Hr','MONTH':'Month'}" onchange="hrsValidation()" readonly="true"/>
                                                                        <%--<select id="pRate" class="SelectBoxStyles form-control input-group-addon" name="pRate" style="width: auto">
                                                                            <option>/Hr</option>
                                                                            <option>/Week</option>
                                                                            <option>/Month</option>
                                                                        </select>--%>
                                                                    </s:if>
                                                                </div>
                                                            </div>
                                                        </s:if>

                                                        <div class="col-sm-4 required ">

                                                            <s:if test="serviceType=='PE'">
                                                                <label class="labelStylereq" style="color:#56a5ec">Submitted Pay Rate(in Thousands):</label>
                                                            </s:if>
                                                            <s:else>
                                                                <label class="labelStylereq" style="color:#56a5ec">Submitted Pay Rate:</label>
                                                            </s:else> 
                                                            <div class="input-group">
                                                                <%--<s:textfield cssClass="form-control" id="rateSalary"  name="rateSalary" value="%{rateSalary}" >
                                                                    <span class="urlDomain">$/Hr</span>
                                                                </s:textfield>--%>
                                                                <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                                <s:textfield cssClass="form-control" id="submittedPayRate"  name="submittedPayRate" value="%{submittedPayRate}"/>
                                                                <s:if test="serviceType!='PE'">
                                                                    <span class="input-group-addon" style="padding-top: 5px">/</span>
                                                                    <s:select id="submittedPayRateType" cssClass="SelectBoxStyles form-control input-group-addon" name="submittedPayRateType" value="%{submittedPayRateType}" list="#@java.util.LinkedHashMap@{'HR':'Hr','DAY':'Day','MONTH':'Month'}" onchange="hrsValidation()" />
                                                                    <%--<select id="pRate" class="SelectBoxStyles form-control input-group-addon" name="pRate" style="width: auto">
                                                                        <option>/Hr</option>
                                                                        <option>/Week</option>
                                                                        <option>/Month</option>
                                                                    </select>--%>
                                                                </s:if>
                                                            </div>
                                                        </div>
                                                        <s:if test="serviceType=='CO'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Deduction Amount:</label>
                                                                <div class="input-group">
                                                                    <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                                    <s:if test="(#session.typeOfUsr == 'AC')"> 
                                                                        <s:textfield cssClass="form-control" id="deductionAmt"  name="deductionAmt" value="%{deductionAmt}"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield cssClass="form-control" id="deductionAmt"  name="deductionAmt" value="%{deductionAmt}" readonly="true"/>
                                                                    </s:else>
                                                                    <%--<s:if test="serviceType!='PE'">--%>
                                                                    <span class="input-group-addon" style="padding-top: 5px">/</span>
                                                                    <s:textfield id="deductionAmtRateType" cssClass=" form-control input-group-addon" name="deductionAmtRateType" value="Hr" readonly="true"/> <%-- onchange="hrsValidation()" --%>
                                                                    <%-- </s:if> --%>
                                                                </div>
                                                            </div>
                                                        </s:if>


                                                        <s:if test="serviceType=='PE'">
                                                            <s:if test="(status == 'Approved' && #session.typeOfUsr == 'AC')||(status == 'Paid')">
                                                                <div class="col-sm-4 required">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Transaction Id:</label>
                                                                    <s:textfield cssClass="form-control" id="transId" name="transId" value="%{transId}"/>
                                                                </div>
                                                                <div class="col-sm-4 required">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Transaction No.:</label>
                                                                    <s:textfield cssClass="form-control" id="transNo" name="transNo"  value="%{transNo}"/>
                                                                </div>
                                                                <div class="col-sm-4 required">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Transaction Amount:</label>
                                                                    <s:textfield cssClass="form-control" id="transAmt" name="transAmt"  value="%{transAmt}"/>
                                                                </div>
                                                            </s:if>    
                                                        </s:if>
                                                        <s:else>
                                                            <div class="col-sm-4 required">
                                                                <label class="labelStylereq" style="color:#56a5ec">Net Terms:</label>
                                                                <s:select id="netTerms" cssClass="SelectBoxStyles form-control " name="netTerms" list="#@java.util.LinkedHashMap@{'15':'15','30':'30','45':'45','60':'60','90':'90'}" value="%{netTerms}">
                                                                    <span class="paymentDoller">Days</span>
                                                                </s:select>
                                                            </div>
                                                        </s:else>

                                                        <s:if test="serviceType=='CO'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Shift Type:</label>
                                                                <s:select id="shiftType" cssClass="SelectBoxStyles form-control" name="shiftType" list="#@java.util.LinkedHashMap@{'D':'Day','N':'Night','R':'Rotation'}" value="%{shiftType}"/>
                                                                <%--<s:textfield name="shiftType" id="shiftType" cssClass="form-control"/>--%>
                                                            </div>

                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Division:(If any..)</label>
                                                                <s:textfield name="customerDivision" id="customerDivision" placeholder="Customer Division" cssClass="form-control" value="%{customerDivision}" maxLength="80"/>
                                                            </div>

                                                            <div class="col-sm-4 required" id="minWorkhrsId">
                                                                <label class="labelStylereq" style="color:#56a5ec">Min Working Hrs:</label>
                                                                <s:textfield name="minWorkhrs" id="minWorkhrs" cssClass="form-control" value="%{minWorkhrs}" onblur="return minHrsValidation();"/>
                                                            </div>
                                                            <div class="col-sm-4 required" id="maxWorkhrsId">
                                                                <label class="labelStylereq" style="color:#56a5ec">Max Working Hrs:</label>
                                                                <s:textfield name="maxWorkhrs" id="maxWorkhrs" cssClass="form-control" value="%{maxWorkhrs}" onblur="return maxHrsValidation();"/>
                                                            </div>
                                                            <div class="col-sm-4 required" id="estHrsDivId">
                                                                <label class="labelStylereq" style="color:#56a5ec">Estimated Hours:</label>
                                                                <s:textfield name="estHrs" id="estHrs" cssClass="form-control" value="%{estHrs}"  onblur="return estimatedHrsValidation();"/>
                                                                <%--<s:textfield name="shiftType" id="shiftType" cssClass="form-control"/>--%>
                                                            </div>


                                                            <div class="col-sm-4" id="otFlagDivId">
                                                                <br>
                                                                <s:if test="otFlag=='true'">
                                                                    <div class="col-sm-13">
                                                                        <s:checkbox name="otFlag" id="otFlag" value="true" onclick="overTimeCheck()"/>
                                                                        <label class="labelStylereq" style="color:#56a5ec">Over Time Hrs</label>
                                                                    </div>
                                                                    <div class="col-sm-14">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Estimated OverTime Hrs:</label>
                                                                        <s:textfield name="estOtHrs" id="estOtHrs" placeholder="Estimated OverTime Hrs" cssClass="form-control" value="%{estOtHrs}" onblur="return estimatedOTValidation();"/>
                                                                    </div>
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="col-sm-13">
                                                                        <s:checkbox name="otFlag" id="otFlag" onclick="overTimeCheck()"/>
                                                                        <label class="labelStylereq" style="color:#56a5ec">Over Time Hrs</label>
                                                                    </div>
                                                                    <div class="col-sm-14">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Estimated OverTime Hrs:</label>
                                                                        <s:textfield name="estOtHrs" id="estOtHrs" placeholder="Estimated OverTime Hrs" cssClass="form-control" value="%{estOtHrs}" disabled="true" onblur="return estimatedOTValidation();"/>
                                                                    </div>
                                                                </s:else>
                                                            </div>
                                                            <s:if test="travelRequired=='true'">
                                                                <div class="col-sm-4">
                                                                    <div class="col-sm-13">  
                                                                        <br>
                                                                        <s:checkbox name="travelRequired" id="travelRequired" value="true" onclick="travelRequiredCheck()"/>
                                                                        <label class="labelStylereq" style="color:#56a5ec">Travel Required</label>
                                                                    </div>
                                                                    <div class="col-sm-14">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Travel Amount Percentage:</label>
                                                                        <s:textfield name="travelAmtPercentage" id="travelAmtPercentage" placeholder="Travel Amount Percentage" cssClass="form-control" value="%{travelAmtPercentage}" maxLength="80"/>
                                                                    </div>
                                                                </div>
                                                            </s:if>
                                                            <s:else>
                                                                <div class="col-sm-4">
                                                                    <div class="col-sm-13">
                                                                        <br>
                                                                        <s:checkbox name="travelRequired" id="travelRequired" onclick="travelRequiredCheck()"/>
                                                                        <label class="labelStylereq" style="color:#56a5ec">Travel Required</label>
                                                                    </div>
                                                                    <div class="col-sm-14">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Travel Amount Percentage:</label>
                                                                        <s:textfield name="travelAmtPercentage" id="travelAmtPercentage" placeholder="Travel Amount Percentage" cssClass="form-control" value="%{travelAmtPercentage}" disabled="true" maxLength="80" />
                                                                    </div>
                                                                </div>
                                                            </s:else>
                                                            <s:if test="securityCheck=='true'">
                                                                <div class="col-sm-4">
                                                                    <br>
                                                                    <s:checkbox name="securityCheck" id="securityCheck" value="true"/>
                                                                    <label class="labelStylereq" style="color:#56a5ec">Security Check</label>
                                                                </div>
                                                            </s:if>  
                                                            <s:else>
                                                                <div class="col-sm-4">
                                                                    <br>
                                                                    <s:checkbox name="securityCheck" id="securityCheck"/>
                                                                    <label class="labelStylereq" style="color:#56a5ec">Security Check</label>
                                                                </div>
                                                            </s:else>
                                                            <div class="col-sm-10" >
                                                                <div class="col-sm-5" style="margin-left: -15px">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Working Location 1:</label>
                                                                    <s:textarea name="locationOne" id="locationOne" placeholder="Working Location 1" cssClass="form-control" value="%{locationOne}"/>
                                                                </div>
                                                                <div class="col-sm-5 " >
                                                                    <label class="labelStylereq" style="color:#56a5ec">Working Location 2:</label>
                                                                    <s:textarea name="locationTwo" id="locationTwo" placeholder="Working Location 2" cssClass="form-control" value="%{locationTwo}"/>
                                                                </div>
                                                            </div>
                                                            <%--<s:property value="%{otFlag}"/>--%>




                                                        </s:if>
                                                        <s:if test="#session.typeOfUsr == 'VC'">
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Comments:</label>
                                                                <s:textarea name="vendorComments" id="vendorComments" placeholder="Vendor Comments" cssClass="form-control"/>
                                                            </div>
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Comments:</label>
                                                                <s:textarea name="customerComments" id="customerComments" placeholder="Customer Comments" cssClass="form-control" readonly="true"/>
                                                            </div>
                                                        </s:if>
                                                        <s:if test="#session.typeOfUsr == 'AC'">
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Comments:</label>
                                                                <s:textarea name="vendorComments" id="vendorComments" placeholder="Vendor Comments" cssClass="form-control" readonly="true"/>
                                                            </div>
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Comments:</label>
                                                                <s:textarea name="customerComments" id="customerComments" placeholder="Customer Comments" cssClass="form-control" />
                                                            </div>
                                                        </s:if>

                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <div class="col-sm-6"></div>
                                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                                            <s:if test="status == 'Created'||status== 'Rejected'">
                                                                <s:if test="(serviceType == 'PE' && #session.typeOfUsr == 'AC')||(serviceType == 'CO' && #session.typeOfUsr == 'VC')">   
                                                                    <div class="col-sm-2"> 
                                                                        <s:submit  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Created&SOWFlag=1"'><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                                        </div> 
                                                                        <div class="col-sm-2">
                                                                        <s:submit  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Submitted&SOWFlag=1"'><i class="fa fa-check-circle-o "></i>&nbsp;Submit</s:submit>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                        <s:submit  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Denied&SOWFlag=1"'><i class="fa fa-terminal"></i>&nbsp;Deny</s:submit>
                                                                        </div>
                                                                </s:if>
                                                            </s:if>
                                                            <s:elseif test="status == 'Submitted'">
                                                                <s:if test="(serviceType == 'PE' && #session.typeOfUsr == 'VC')||(serviceType == 'CO' && #session.typeOfUsr == 'AC')">  
                                                                    <div class="col-sm-2"> 
                                                                        <s:submit  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Approved&SOWFlag=1"'><i class="fa fa-check-circle"></i>&nbsp;Approve</s:submit>
                                                                        </div> 
                                                                        <div class="col-sm-2">
                                                                        <s:submit  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Rejected&SOWFlag=1"'><i class="fa fa-times-circle"></i>&nbsp;Reject</s:submit>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                        <s:submit  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Denied&SOWFlag=1"'><i class="fa fa-terminal"></i>&nbsp;Deny</s:submit>
                                                                        </div>
                                                                </s:if> 

                                                            </s:elseif>
                                                            <s:elseif test="status == 'Approved'">
                                                                <s:if test="serviceType == 'PE'&& #session.typeOfUsr == 'AC'"> 
                                                                    <div class="col-sm-4"></div>
                                                                    <div class="col-sm-2">   
                                                                        <s:submit  cssClass="add_searchButton form-control" type="submit" value="Paid" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Paid&SOWFlag=1"'/>
                                                                    </div>
                                                                </s:if>
                                                                <s:elseif test="serviceType == 'CO'"><!--&& #session.typeOfUsr == 'VC'-->
                                                                    <div class="col-sm-10"></div>
                                                                    <div class="col-sm-2"> 
                                                                        <s:if test="sowHisStatus != 'Recreated'&&sowHisStatus != 'Submitted'">   
                                                                            <s:submit  cssClass="add_searchButton form-control SOW_popup_open" type="submit" value="Recreate" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Recreated&SOWFlag=1"'/> 
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:submit  cssClass="add_searchButton form-control pull-right SOW_popup_open" type="submit" value="Recreate" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Recreated&SOWFlag=1"' disabled=""/>   
                                                                        </s:else>
                                                                    </div>
                                                                </s:elseif>
                                                            </s:elseif>



                                                        </div>
                                                    </div>
                                                </div>
                                            </s:form>
                                            <script type="text/javascript">

                                            $('#minWorkhrs').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.',
                                                limit: 5,
                                                centsLimit: 2
                                            });
                                            $('#maxWorkhrs').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.',
                                                limit: 5,
                                                centsLimit: 2
                                            });
                                            $('#estHrs').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.',
                                                limit: 5,
                                                centsLimit: 2
                                            });
                                            $('#estOtHrs').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.',
                                                limit: 5,
                                                centsLimit: 2
                                            });
                                            $('#targetRate').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.'
                                            });
                                            $('#submittedPayRate').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.',
                                                limit: 15,
                                                centsLimit: 2
                                            });
                                            $('#transAmt').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.'
                                            });
                                            $('#deductionAmt').priceFormat({
                                                prefix: '',
                                                centsSeparator: '.',
                                                limit: 15,
                                                centsLimit: 2
                                            });
                                            
                                            </script>
                                        </div>
                                    </div>
                                    <div id="sowAttachment_popup">
                                        <div id="SOWAttachBox">
                                            <div style="background-color: #3BB9FF ">
                                                <table>
                                                    <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;SOW Attachments Details&nbsp;&nbsp; </font></h4></td>
                                                    <span class=" pull-right"><h5><a href="#" class="sowAttachment_popup_close" onclick="addSOWAttachmentOverLay();"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                                </table>
                                            </div>
                                            <span id="attachSpan"><attachTag></attachTag></span>
                                            <div id="message"></div>
                                            <br>
                                            <s:form action="addSOWAttachments" id="SOWAttachmentId" onsubmit="return sowAttachmentValidation();" theme="simple" enctype="multipart/form-data" >
                                                <div>
                                                    <div class="inner-addtaskdiv-elements">
                                                        <s:hidden name="consultantId" id="consultantId" value="%{consultantId}"/>
                                                        <s:hidden name="requirementId" id="requirementId" value="%{requirementId}"/>
                                                        <s:hidden name="customerId" id="customerId" value="%{customerId}"/>
                                                        <s:hidden name="vendorId" id="vendorId" value="%{vendorId}"/>
                                                        <s:hidden name="rateSalary" id="rateSalary" value="%{rateSalary}"/>
                                                        <s:hidden name="consultantName" id="consultantName" value="%{consultantName}"/>
                                                        <s:hidden name="customerName" id="customerName" value="%{customerName}"/>
                                                        <s:hidden name="requirementName" id="requirementName" value="%{requirementName}"/>
                                                        <s:hidden name="vendorName" id="vendorName" value="%{vendorName}"/>
                                                        <s:hidden name="status" id="status" value="%{status}"/>

                                                        <s:file name="file" id="file"/>
                                                    </div>
                                                    <div class="col-sm-6"></div>
                                                    <div class="col-sm-6">
                                                        <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                                        <center><s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control " value="" theme="simple"  ><i class="fa fa-plus-square"></i>&nbsp;Add</s:submit></center><br>
                                                    </div>
                                                </div>
                                            </s:form>
                                        </div>
                                    </div>
                                    <!-- recreate list tab starts here-->
                                    <div class="tab-pane fade in " id="recreatedList">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <font color="#ffffff">Recreated List</font>
                                                        <i id="updownArrow" onclick="toggleContent('sowEditForm')" class="fa fa-angle-up"></i> 
                                                        <s:url var="myUrl" action="getSowList.action">
                                                        </s:url>
                                                        <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                        </h4>
                                                    </div>
                                                </div>

                                                <div class="col-sm-12" id="sowEditForm">
                                                    <div class="inner-reqdiv-elements">
                                                        <div class="row">
                                                            <div class="col-sm-3">
                                                                <label class="labelStylereq" style="color:#56a5ec">Status:</label>
                                                            <%--<s:select id="status" cssClass="SelectBoxStyles form-control" name="status" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Selected':'Selected','Approved':'Approved','Rejected':'Rejected','Generated':'SOW generated','Submitted':'SOW Submitted','Released':'Released'}"/>--%>
                                                            <s:select id="his_status" cssClass="SelectBoxStyles form-control" name="his_status" list="#@java.util.LinkedHashMap@{'All':'All','Approved':'Approved','Rejected':'Rejected','Denied':'Denied','Recreated':'Recreated'}"/>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Service&nbsp;Type</label>

                                                            <s:select name="his_serviceType" id="his_serviceType" cssClass="SelectBoxStyles form-control" list="#@java.util.LinkedHashMap@{'All':'All','S':'SOW Proceed','F':'Finder Fee'}"/>

                                                        </div>
                                                        <div class="col-sm-4"></div>
                                                        <div class="col-sm-2">
                                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                                            <s:submit  cssClass="add_searchButton form-control" type="button" value="" onclick="getRecreatedList()" cssStyle="margin:5px 0px 0px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-sm-12">
                                                    <br>
                                                <s:form>
                                                    <span><font style="color: green"><s:property value="resultMessage"></s:property></font></span>

                                                        <table id="sowResults" class="responsive CSSTable_task" border="5"cell-spacing="2">
                                                            <tbody>

                                                                <tr>
                                                                    <th>Consultant&nbsp;Name</th>
                                                                    <th>Service&nbsp;Type</th>
                                                                    <th>Service&nbsp;version</th>
                                                                    <th>Pay&nbsp;Rate</th>
                                                                    <th>Created&nbsp;Date</th>
                                                                    <th>Status</th>

                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <label class="page_option"> Display <select id="paginationOption_rec" class="disPlayRecordsCss" onchange="PagerOption()" style="width: auto">
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>25</option>
                                                                <option>50</option>
                                                            </select>
                                                            Contracts per page
                                                        </label>
                                                        <div align="right" id="pageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>

                                                        <script type="text/javascript">
//                                                        paginationSize = document.getElementById("paginationOption").value; 
//                                                        var pager = new Pager('sowResults', 1); 
//                                                        pager.init(); 
//                                                        pager.showPageNav('pager', 'pageNavPosition'); 
//                                                        pager.showPage(1);
//                                                        function recreatedPagerOption(){
//
//                                                            paginationSize = document.getElementById("paginationOption").value;
//                                                            if(isNaN(paginationSize))
//                                                                alert(paginationSize);
//
//                                                            pager = new Pager('sowResults', parseInt(paginationSize));
//                                                            pager.init();
//                                                            pager.showPageNav('pager', 'pageNavPosition');
//                                                            pager.showPage(1);
//
//                                                        };
                                                        </script>
                                                </s:form>

                                            </div>


                                        </div>


                                    </div>
                                    <!--recreateed list tab ends here-->
                                    <div class="tab-pane fade in " id="attachments" >
                                        <s:hidden name="tabFlag" id="tabFlag" value="%{tabFlag}"/>
                                        <s:hidden name="uploadRes" id="uploadRes" value="%{uploadRes}"/>
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <font color="#ffffff">Attachments</font>


                                                        <s:url var="myUrl" action="getSowList.action">
                                                        </s:url>
                                                        <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                        </h4>
                                                    </div>
                                                </div>
                                            <s:form>
                                                <div class="row">
                                                    <div class="col-sm-10"></div>
                                                    <div class="col-sm-2 ">
                                                        <a href="#" class="sowAttachment_popup_open" onclick="addSOWAttachmentOverLay();"><button class="add_searchButton form-control" style="margin: 5px 0px"><i class="fa fa-plus-square"></i>&nbsp;Add</button></button></a>
                                                    </div>
                                                </div>
                                                <br>
                                                <span id="uploadSpan"><upload></upload></span>
                                                <s:if test="fileExists=='noFile'">
                                                    <span><font style="color: red">Sorry File Not Exists!</font></span>
                                                    </s:if>
                                                    <s:elseif test="fileExists=='NotFound'">
                                                    <span><font style="color: red">Sorry File Not Found!</font></span>
                                                    </s:elseif>


                                                <s:else>

                                                </s:else>

                                                <div class="task_content" id="task_div" align="center" style="display: none" >
                                                    <div>
                                                        <div>
                                                            <table id="sowAttachment" class="responsive CSSTable_task" border="5"cell-spacing="2">
                                                                <tbody>

                                                                    <tr>
                                                                        <th>Consultant&nbsp;Name</th>
                                                                        <th>Attachment &nbsp;Name</th>
                                                                        <th>Uploaded&nbsp;By</th>
                                                                        <th>Download</th>
                                                                    </tr>

                                                                    <s:if test="sowVTO.size == 0">
                                                                        <tr>
                                                                            <td colspan="4"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                        </tr>
                                                                    </s:if>
                                                                    <s:iterator  value="sowVTO">
                                                                        <tr>
                                                                            <td><s:property value="consultantName"></s:property></td>
                                                                            <td><s:property value="sowAttachmentName"></s:property></td>
                                                                            <td><s:property value="sowAttachmentUploadedBy"></s:property></td>
                                                                                <td><center>
                                                                            <a href="#" onclick=doSOWAttachmentDownload(<s:property value="sowAttachmentId"/>);>    
                                                                            <img src="<s:url value="/includes/images/download.png"/>" height="20" width="20">
                                                                        </a>
                                                                    </center>
                                                                    </td>
                                                                    </tr>        
                                                                </s:iterator>
                                                                </tbody>
                                                            </table>
                                                            <br/>
                                                            <s:if test="sowVTO.size > 0">
                                                            <label> Display <select id="PaginationOption1" class="disPlayRecordsCss" onchange="PagerOption_attch()" style="width: auto">
                                                                  
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                Accounts per page
                                                            </label>
                                                            </s:if>
                                                            <div align="right" id="PageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>
                                                        </div>

                                                        <script type="text/javascript">
//                                                        paginationSize = document.getElementById("attachPaginationOption").value; 
//                                                        var pager = new Pager('sowAttachment', parseInt(paginationSize)); 
//                                                        pager.init(); 
//                                                        pager.showPageNav('pager', 'PageNavPosition'); 
//                                                        pager.showPage(1);
//                                                        function attachPagerOption(){
//
//                                                            paginationSize = document.getElementById("attachPaginationOption").value;
//                                                            if(isNaN(paginationSize))
//                                                                alert(paginationSize);
//
//                                                            pager = new Pager('sowAttachment', parseInt(paginationSize));
//                                                            pager.init();
//                                                            pager.showPageNav('pager', 'PageNavPosition');
//                                                            pager.showPage(1);
//
//                                                        };
                                                        </script>
                                                    </div>
                                                    <div class="col-lg-12"></div>
                                                </div>
                                                <br>

                                            </s:form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%-- ------------MIDDLE -----------------------------------------%>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/javascript">
        var tabFlag=document.getElementById("tabFlag").value;
        var uploadRes=document.getElementById("uploadRes").value;
        if(uploadRes=='S'){
            $("upload").html(" <b><font color='green'>Attachment Uploaded Successfully!</font></b>");
            $("#uploadSpan").show().delay(5000).fadeOut();
        }else if(uploadRes=='F'){
            $("upload").html(" <b><font color='red'>Failed To upload!</font></b>");
            $("#uploadSpan").show().delay(5000).fadeOut();
        }else{
            $("upload").html("");
        }
        if(tabFlag=="AT")
        {
            document.getElementById('Attachments').className='active active_details';
            document.getElementById('SOWEdit').className='tab-pane fade in';
            document.getElementById('attachments').className='tab-pane fade in active';
        }
        else if(tabFlag=='recreatedList'){
            document.getElementById('RecreatedList').className='active active_details';
            document.getElementById('recreatedList').className='tab-pane fade in active';
            document.getElementById('SOWEdit').className='tab-pane fade in';
            document.getElementById('attachments').className='tab-pane fade in ';
            getRecreatedList();
        }
        else{
            document.getElementById('edit').className='active active_details';
            document.getElementById('SOWEdit').className='tab-pane fade in active';
        }
    
        </script>
   
     
        
       
  
           <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script>
           
           <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
       
   <script type="text/javascript">
       var recordPage=10;
         function PagerOption_attch(){

              var paginationSize = document.getElementById("PaginationOption1").value;
            
             if(isNaN(paginationSize))
                   {
                       
                   }
                   //alert(paginationSize);
               recordPage=paginationSize;
               //alert(recordPage);
                $('#sowAttachment').tablePaginate({navigateType:'navigator'},recordPage);

           };
       $('#sowAttachment').tablePaginate({navigateType:'navigator'},recordPage);
      </script>
      
       <script>
       var recordPage=10;
         function PagerOption(){

              var paginationSize = document.getElementById("paginationOption_rec").value;
             //  alert(paginationSize);
               if(isNaN(paginationSize))
                   {
                       
                   }
               recordPage=paginationSize;
                //alert(recordPage);
                $('#sowResults').tablePaginate({navigateType:'navigator'},recordPage);

           };
       $('#sowResults').tablePaginate({navigateType:'navigator'},recordPage);
      </script>
      <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>