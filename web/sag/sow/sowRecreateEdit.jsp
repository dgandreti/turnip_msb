
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
        <title>ServicesBay :: SOW Recreate Edit Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/sowAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.price_format.2.0.min.js"/>"></script>
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
                <%-- ------------MIDDLE -----------------------------------------%>
                <section id="generalForm"><!--form-->
                    <div class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">

                                    <div class="col-sm-14 ">
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
                                                        <s:url var="myUrl" action="SOWEditAction.action">
                                                            <s:param name="serviceId"><s:property value="serviceId"/></s:param>
                                                            <s:param name="consultantName"><s:property value="consultantName"/></s:param> 
                                                            <s:param name="customerName"><s:property value="customerName"/></s:param>
                                                            <s:param name="vendorName"><s:property value="vendorName"/></s:param>
                                                            <s:param name="requirementName"><s:property value="requirementName"/></s:param>
                                                            <s:param name="status"><s:property value="status"/></s:param>
                                                            <s:param name="serviceType"><s:property value="serviceTypeRedirect"/></s:param>
                                                            <s:param name="tabFlag">recreatedList</s:param>
                                                        </s:url>
                                                        <span class="pull-right"><s:a href='%{#myUrl}' id="finderBackLink"><i class="fa fa-undo"></i></s:a></span>
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
                                                <s:hidden id="his_id" name="his_id" value="%{his_id}"/>
                                                <s:hidden id="typeOfUser" value="%{#session['typeOfUsr']}"/>
                                                <s:hidden id="sowHisStatus" name="sowHisStatus" value="%{sowHisStatus}"/>
                                                <s:hidden name="serviceTypeRedirect" id="serviceTypeRedirect" value="%{serviceTypeRedirect}"/>
                                                <div class="inner-reqdiv-elements">
                                                    <span id="errorSpan"><e></e></span>

                                                    <div class="row">
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Consultant Name</label>
                                                            <s:textfield cssClass="form-control" id="consultantName"  value="%{consultantName}" readonly="true"/>
                                                        </div>

                                                        <s:if test="#session.typeOfUsr == 'AC'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Name</label>
                                                                <s:textfield cssClass="form-control" id="vendorName" value="%{vendorName}" readonly="true"/>
                                                            </div>
                                                        </s:if>
                                                        <s:if test="#session.typeOfUsr == 'VC'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Name</label>
                                                                <s:textfield cssClass="form-control" id="customerName"  value="%{customerName}" readonly="true"/>
                                                            </div>
                                                        </s:if>

                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Requirement Name</label>
                                                            <s:textfield cssClass="form-control" id="requirementName"  value="%{requirementName}" readonly="true"/>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Service Version</label>
                                                            <s:textfield cssClass="form-control" id="serviceVersion" name="serviceVersion" value="%{serviceVersion}" readonly="true"/>
                                                        </div>
                                                        <%--<div class="col-lg-4">
                                                        <label class="labelStylereq" style="color:#56a5ec">Pay Mode:</label>
                                                        <s:select id="payType" cssClass="SelectBoxStyles form-control" name="payType" list="#@java.util.LinkedHashMap@{'ch':'Cheque','NEFT':'NEFT'}"/>
                                                    </div>--%>

                                                        <div class="col-sm-4">
                                                            <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                            <s:textfield cssClass="form-control" name="status" id="status"  value="%{status}" readonly="true"/>   

                                                        </div>
                                                        <s:if test="#session.typeOfUsr == 'VC'">
                                                            <div class="col-sm-4 required ">
                                                                <s:if test="serviceType=='PE'">
                                                                    <label class="labelStylereq" style="color:#56a5ec">Pay Rate(in Thousands)</label>
                                                                </s:if>
                                                                <s:else>
                                                                    <label class="labelStylereq" style="color:#56a5ec">Pay Rate</label>
                                                                </s:else>  
                                                                <div class="input-group">
                                                                    <%--<s:textfield cssClass="form-control" id="rateSalary"  name="rateSalary" value="%{rateSalary}" >
                                                                        <span class="urlDomain">$/Hr</span>
                                                                    </s:textfield>--%>
                                                                    <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                                    <s:textfield cssClass="form-control" id="targetRate"  name="targetRate" value="%{targetRate}" readonly="true"/>
                                                                    <s:if test="serviceType!='PE'">
                                                                        <span class="input-group-addon" style="padding-top: 5px">/</span>
                                                                        <s:select id="targetRateType" cssClass="SelectBoxStyles form-control input-group-addon" name="targetRateType" value="%{targetRateType}" list="#@java.util.LinkedHashMap@{'HR':'Hr','MONTH':'Month'}" readonly="true"/>
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
                                                                <label class="labelStylereq" style="color:#56a5ec">Submitted Pay Rate(in Thousands)</label>
                                                            </s:if>
                                                            <s:else>
                                                                <label class="labelStylereq" style="color:#56a5ec">Submitted Pay Rate</label>
                                                            </s:else> 
                                                            <div class="input-group">
                                                                <%--<s:textfield cssClass="form-control" id="rateSalary"  name="rateSalary" value="%{rateSalary}" >
                                                                    <span class="urlDomain">$/Hr</span>
                                                                </s:textfield>--%>
                                                                <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                                <s:textfield cssClass="form-control" id="submittedPayRate"  name="submittedPayRate" value="%{submittedPayRate}"/>
                                                                <s:if test="serviceType!='PE'">
                                                                    <span class="input-group-addon" style="padding-top: 5px">/</span>
                                                                    <s:select id="submittedPayRateType" cssClass="SelectBoxStyles form-control input-group-addon" name="submittedPayRateType" value="%{submittedPayRateType}" list="#@java.util.LinkedHashMap@{'HR':'Hr','DAY':'Day','MONTH':'Month'}" />
                                                                </s:if>
                                                            </div>
                                                        </div>
                                                        <s:if test="serviceType=='CO'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Deduction Amount</label>
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
                                                            <%--  <s:if test="status == 'Approved' && #session.typeOfUsr == 'AC'">
                                                                  <div class="col-lg-4 required">
                                                                      <label class="labelStylereq" style="color:#56a5ec">Transaction Id:</label>
                                                                      <s:textfield cssClass="form-control" id="transId" name="transId" value="%{transId}"/>
                                                                  </div>
                                                                  <div class="col-lg-4 required">
                                                                      <label class="labelStylereq" style="color:#56a5ec">Transaction No.:</label>
                                                                      <s:textfield cssClass="form-control" id="transNo" name="transNo"  value="%{transNo}"/>
                                                                  </div>
                                                                  <div class="col-lg-4 required">
                                                                      <label class="labelStylereq" style="color:#56a5ec">Transaction Amount:</label>
                                                                      <s:textfield cssClass="form-control" id="transAmt" name="transAmt"  value="%{transAmt}"/>
                                                                  </div>
                                                              </s:if> --%>   
                                                        </s:if>
                                                        <s:else>
                                                            <div class="col-sm-4 required">
                                                                <label class="labelStylereq" style="color:#56a5ec">Net Terms</label>
                                                                <s:select id="netTerms" cssClass="SelectBoxStyles form-control " name="netTerms" list="#@java.util.LinkedHashMap@{'15':'15','30':'30','45':'45','60':'60','90':'90'}" value="%{netTerms}">
                                                                    <span class="paymentDoller">Days</span>
                                                                </s:select>
                                                            </div>
                                                        </s:else>

                                                        <s:if test="serviceType=='CO'">
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Shift Type</label>
                                                                <s:select id="shiftType" cssClass="SelectBoxStyles form-control" name="shiftType" list="#@java.util.LinkedHashMap@{'D':'Day','N':'Night','R':'Rotation'}" value="%{shiftType}"/>
                                                                <%--<s:textfield name="shiftType" id="shiftType" cssClass="form-control"/>--%>
                                                            </div>

                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Division(If any..)</label>
                                                                <s:textfield name="customerDivision" id="customerDivision" placeholder="Customer Division" cssClass="form-control" value="%{customerDivision}"/>
                                                            </div>

                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Min Working Hrs</label>
                                                                <s:textfield name="minWorkhrs" id="minWorkhrs" cssClass="form-control" value="%{minWorkhrs}" onblur="return minHrsValidation();"/>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Max Working Hrs</label>
                                                                <s:textfield name="maxWorkhrs" id="maxWorkhrs" cssClass="form-control" value="%{maxWorkhrs}" onblur="return maxHrsValidation();"/>
                                                            </div>
                                                            <div class="col-sm-4 required">
                                                                <label class="labelStylereq" style="color:#56a5ec">Estimated Hours</label>
                                                                <s:textfield name="estHrs" id="estHrs" cssClass="form-control" value="%{estHrs}"  onblur="return estimatedHrsValidation();"/>
                                                                <%--<s:textfield name="shiftType" id="shiftType" cssClass="form-control"/>--%>
                                                            </div>


                                                            <div class="col-sm-4">
                                                                <br>
                                                                <s:if test="otFlag=='true'">
                                                                    <div class="col-sm-13">
                                                                        <s:checkbox name="otFlag" id="otFlag" value="true" onclick="overTimeCheck()"/>
                                                                        <label class="labelStylereq" style="color:#56a5ec">Over Time Hrs</label>
                                                                    </div>
                                                                    <div class="col-sm-14">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Estimated OverTime Hrs</label>
                                                                        <s:textfield name="estOtHrs" id="estOtHrs" placeholder="Estimated OverTime Hrs" cssClass="form-control" value="%{estOtHrs}" onblur="return estimatedOTValidation();"/>
                                                                    </div>
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="col-sm-13">
                                                                        <s:checkbox name="otFlag" id="otFlag" onclick="overTimeCheck()"/>
                                                                        <label class="labelStylereq" style="color:#56a5ec">Over Time Hrs</label>
                                                                    </div>
                                                                    <div class="col-sm-14">
                                                                        <label class="labelStylereq" style="color:#56a5ec">Estimated OverTime Hrs</label>
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
                                                                        <label class="labelStylereq" style="color:#56a5ec">Travel Amount Percentage</label>
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
                                                                        <label class="labelStylereq" style="color:#56a5ec">Travel Amount Percentage</label>
                                                                        <s:textfield name="travelAmtPercentage" id="travelAmtPercentage" placeholder="Travel Amount Percentage" cssClass="form-control" value="%{travelAmtPercentage}" disabled="true" />
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
                                                                    <label class="labelStylereq" style="color:#56a5ec">Working Location 1</label>
                                                                    <s:textarea name="locationOne" id="locationOne" placeholder="Working Location 1" cssClass="form-control" value="%{locationOne}"/>
                                                                </div>
                                                                <div class="col-sm-5 " >
                                                                    <label class="labelStylereq" style="color:#56a5ec">Working Location 2</label>
                                                                    <s:textarea name="locationTwo" id="locationTwo" placeholder="Working Location 2" cssClass="form-control" value="%{locationTwo}"/>
                                                                </div>
                                                            </div>
                                                            <%--<s:property value="%{otFlag}"/>--%>




                                                        </s:if>
                                                        <s:if test="#session.typeOfUsr == 'VC'">
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Comments</label>
                                                                <s:textarea name="vendorComments" id="vendorComments" placeholder="Vendor Comments" cssClass="form-control"/>
                                                            </div>
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Comments</label>
                                                                <s:textarea name="customerComments" id="customerComments" placeholder="Customer Comments" cssClass="form-control" readonly="true"/>
                                                            </div>
                                                        </s:if>
                                                        <s:if test="#session.typeOfUsr == 'AC'">
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Vendor Comments</label>
                                                                <s:textarea name="vendorComments" id="vendorComments" placeholder="Vendor Comments" cssClass="form-control" readonly="true"/>
                                                            </div>
                                                            <div class="col-sm-12">
                                                                <label class="labelStylereq" style="color:#56a5ec">Customer Comments</label>
                                                                <s:textarea name="customerComments" id="customerComments" placeholder="Customer Comments" cssClass="form-control" />
                                                            </div>
                                                        </s:if>

                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <div class="col-sm-6"></div>
                                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                                            <s:if test="status == 'Recreated'||status== 'Rejected'">
                                                                <s:if test="(serviceType == 'PE' && #session.typeOfUsr == 'AC')||(serviceType == 'CO' && #session.typeOfUsr == 'VC')">   
                                                                   <div class="col-sm-2 pull-right">
                                                                        <s:submit id="denyButton" type="button" cssClass="add_searchButton form-control"  value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Denied&SOWFlag=2"'><i class="fa fa-terminal"></i>&nbsp;Deny</s:submit>
                                                                        </div>
                                                                        <div class="col-sm-2 pull-right">
                                                                        <s:submit id="submitButton" type="button" cssClass="add_searchButton form-control"  value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Submitted&SOWFlag=2"'><i class="fa fa-check-circle-o "></i>&nbsp;Submit</s:submit> 
                                                                        </div>
                                                                         <div class="col-sm-2 pull-right"> 
                                                                        <s:submit id="saveButton" type="button" cssClass="add_searchButton form-control"  value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Recreated&SOWFlag=2"'><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                                        </div> 
                                                                       
                                                                </s:if>
                                                            </s:if>
                                                            <s:elseif test="status == 'Submitted'">
                                                                <s:if test="(serviceType == 'PE' && #session.typeOfUsr == 'VC')||(serviceType == 'CO' && #session.typeOfUsr == 'AC')">  
                                                                    <div class="col-sm-2"> 
                                                                        <s:submit  id="approveButton"  cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Approved&SOWFlag=2"'><i class="fa fa-check-circle"></i>&nbsp;Approve</s:submit>
                                                                        </div> 
                                                                        <div class="col-sm-2">
                                                                        <s:submit  id="rejectButton" cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Rejected&SOWFlag=2"'><i class="fa fa-times-circle"></i>&nbsp;Reject</s:submit>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                        <s:submit  id="denyButton1" cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Denied&SOWFlag=2"'><i class="fa fa-terminal"></i>&nbsp;Deny</s:submit>
                                                                        </div>
                                                                </s:if> 

                                                            </s:elseif>
                                                            <%--   <s:elseif test="status == 'Approved'">
                                                                   <s:if test="serviceType == 'PE'&& #session.typeOfUsr == 'AC'"> 
                                                                       <div class="col-lg-4 pull-right">   
                                                                           <s:submit  cssClass="cssbutton_emps form-control pull-right" type="submit" value="Paid" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Paid&SOWFlag=2"'/>
                                                                       </div>
                                                                   </s:if>
                                                                   <s:elseif test="serviceType == 'CO'"><!--&& #session.typeOfUsr == 'VC'-->
                                                                       <div class="col-lg-4 pull-right">  
                                                                           <s:submit  cssClass="cssbutton_emps form-control pull-right SOW_popup_open" type="submit" value="Recreate" cssStyle="margin:5px 0px 0px" onclick='document.SOWForm.action="SOWSaveOrSubmit.action?SOWStatus=Recreated&SOWFlag=2"'/> 
                                                                       </div>
                                                                   </s:elseif>
                                                               </s:elseif>--%>

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
    
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <%-- ------------MIDDLE -----------------------------------------%>
            </div>
        </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>


