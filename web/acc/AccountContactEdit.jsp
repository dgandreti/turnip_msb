<%-- 
   Document   : Account contact Edit
   Author     : manikanta,meeralla@miraclesoft.com
--%>


<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay ::  Account Contact Edit Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <%--<link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
<link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css"> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <%--<script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script> --%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script> --%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
       
        <style type="text/css">
            #placement-examples .east { margin-left: 450px; }
        </style>
        <script type="text/javascript">
            $(function() {
                // placement examples
			
                $('.east').powerTip({ placement: 'e' });
			
            });
        </script>
    </head>
    <body style="overflow-x: hidden">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div>

        </header>

        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="selectivityProfileBox" style="float: left; margin-top: 5px">
                                    <br>
                                    <% String accFlag = "accDetails";%> 
                                    <div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px">

                                        <s:url var="myUrl" action="viewAccount.action">
                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                            <s:param name="accFlag"><%= accFlag%></s:param>
                                        </s:url>
                                        <s:if test="flag=='customerlogin'">
                                            <%-- Here HyperLink is not Required --%>
                                            <span style="color: #FC9A11;"><s:property value="%{account_name}"/></span>
                                        </s:if>
                                        <s:elseif test="flag=='vendorlogin'">
                                            <%-- Here HyperLink is not Required --%>
                                            <span style="color: #FC9A11;"><s:property value="%{account_name}"/></span>
                                        </s:elseif>
                                        <s:else>
                                            <s:a href='%{#myUrl}' style="color: #FC9A11;"><s:property value="%{account_name}"/></s:a> 
                                        </s:else>
                                        <% String flag = "conSearch";
                                        %>
                                        <s:url var="myUrl" action="../acc/viewAccount.action">
                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                            <s:param name="accFlag"><%=flag%></s:param>
                                        </s:url>
                                        <s:a href='%{#myUrl}' style="color: #FC9A11;">->Contact Search</s:a>
                                        <span style="color: #FC9A11;">-><s:property value="%{accountContactVTO.firstName}"/>.<s:property value="%{accountContactVTO.lastName}"/></span>

                                    </div>   <br>
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <s:if test="flag=='customerlogin' || flag=='vendorlogin'">
                                                    <font color="#ffffff"> Profile</font>
                                                </s:if>
                                                <s:else>
                                                    <font color="#ffffff"> Edit Contact</font> 
                                                </s:else>

                                                <s:if test="accFlag=='vendorContact'">
                                                    <% String venorFlag = "vendorContactSearch";%>
                                                    <s:url var="myUrl" action="../vendor/vendorDetails.action">
                                                        <s:param name="vendorId"><s:property value="accountSearchID"/></s:param> 
                                                        <s:param name="venFlag"><%=venorFlag%></s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                    </s:if> 

                                                <s:elseif test="flag=='customerlogin'">
                                                    <%-- Back Button is not required here --%>
                                                </s:elseif>
                                                <s:elseif test="flag=='vendorlogin'">
                                                    <%-- Back Button is not required here --%>
                                                </s:elseif>
                                                <s:else>
                                                    <s:url var="myUrl" action="../acc/viewAccount.action">
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                        <s:param name="accFlag"><%=flag%></s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                    </s:else>
                                            </h4>
                                        </div>

                                    </div>
                                    <!-- content start -->

                                    <br/><span id="addContactError"> </span> 
                                    <s:if test="hasActionMessages()">
                                        <div  >
                                            <span id="actionMessage"><s:actionmessage cssClass="actionContactMessagecolor"/></span>
                                        </div>
                                    </s:if>
                                    <div>

                                        <form action="updateAccountContact" onsubmit="return contactInfoValidation()">
                                            <div>

                                                <span id="InsertContactInfo"></span>

                                                <s:hidden name="contactId" id="contactId" value="%{contactId}"/>
                                                <s:hidden name="accountSearchID" id="accountSearchID" value="%{accountSearchID}" />
                                                <s:hidden name="account_name" id="account_name" value="%{account_name}" />
                                                <s:hidden name="accountType" id="accountType" value="%{accountType}" />

                                                <div class="inner-reqdiv-elements">
                                                    <div class="row">
                                                        <div class="col-lg-2" >
                                                            <div class=""> 
                                                                <s:url id="image" action="rImage" namespace="/renderImage">
                                                                    <s:param name="path" value="accountContactVTO.profileImage"></s:param>
                                                                </s:url>
                                                                <div class="imgcol">
                                                                    <a href="#"> <img  src="<s:property value="#image"/>" class="imageupdate_popup_open" onclick="openUploadFileDialogue()" value="east" title="Change Image on Click"  alt="photo" height="110px" width="100px">
                                                                        <br><font style="font-size: 10px;background" class="imageupdate_popup_open btn btn-xs btn-info " onclick="openUploadFileDialogue()" value="east" title="Change Image on Click">Change Photo</font></a>
                                                                </div>
                                                            </div>

                                                        </div>


                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle"><span style="color:red;">*</span>First Name:</label><s:textfield  name="ContactFname" value="%{accountContactVTO.firstName}"  id="ContactFname" required="true" maxLength="28" cssClass="form-control" placeholder="first Name" onkeyup="contactFirstNameValidation()"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle">Middle&nbsp;Name:</label><s:textfield name="ContactMname" value="%{accountContactVTO.middleName}" id="ContactMname"  cssClass="form-control" placeholder="middle Name" maxLength="28"  onfocus="removeResultMessage()" pattern='[A-Za-z\\s]*' onkeypress="return middlename(event);"/>   
                                                            <span id="mnameError" class=""></span>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle"><span style="color:red;">*</span>Last Name:</label><s:textfield name="ContactLname" value="%{accountContactVTO.lastName}" id="ContactLname" maxLength="28"  cssClass="form-control" placeholder="last Name"  onkeyup="contactLastNameValidation()"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle">Email:</label><s:textfield cssClass="form-control" disabled="true" value="%{accountContactVTO.email}" name="ContactEmail" id="ContactEmail"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle">Alternate&nbsp;Email:</label><s:textfield cssClass="form-control" value="%{accountContactVTO.email2}" name="ContactEmail2" id="ContactEmail2" maxLength="58" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Must be valid email')" placeholder="Alternate Email" onblur="alternateMailValidation()" onkeyup="removeActionMessage()"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <%--label class="contactLabelStyle">Designation:</label><s:select cssClass="SelectBoxStyles form-control" name="contactDesignation" id="designation" headerKey="0" value="%{accountContactVTO.contactDesignation}" headerValue="-Select-" list="%{accountContactVTO.titles}"/--%>
                                                            <%-- <label class="contactLabelStyle"><span style="color:red;">*</span>Designation:</label><s:textfield cssClass="  form-control" name="contactDesignation" id="designation" required="true"  value="%{accountContactVTO.contactDesignation}" onkeyup="contactDesignationValidation()"/>--%>
                                                            <label class="addAcclabelStyle">Gender</label>
                                                            <s:select cssClass="SelectBoxStyles form-control " id="gender" name="gender" list="#@java.util.LinkedHashMap@{'M':'Male','F':'Female'}" value="%{accountContactVTO.gender}"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle"><span style="color:red;">*</span>Office&nbsp;Phone:</label> <s:textfield id="Officephone" value="%{accountContactVTO.officePhone}" cssClass="form-control" required="true" name="Officephone"  type="text" placeholder="Phone #" />  
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle">Mobile&nbsp;Number:</label> <s:textfield id="moblieNumber" value="%{accountContactVTO.moblieNumber}" cssClass="form-control" name="moblieNumber"  type="text" placeholder="Mobile #" />
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="contactLabelStyle">Home&nbsp;Phone:</label> <s:textfield id="homePhone" value="%{accountContactVTO.homePhone}" cssClass="form-control" name="homePhone"  type="text" placeholder="Home Phone #" />
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <%-- for space --%>
                                                        </div>
                                                        <div class="col-lg-3  required">
                                                            <label class="addAcclabelStyle">Title</label><s:textfield cssClass="form-control " name="contactTitle" id="contactTitle" placeholder="Title"  required="true" maxLength="30"  tabindex="1" value="%{accountContactVTO.contactTitle}"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="addAcclabelStyle">Industry</label><s:select cssClass="form-control SelectBoxStyles" name="contactIndustry" id="contactIndustry" headerKey="-1" headerValue="Select Industry" list="industryMap"  tabindex="1" value="%{accountContactVTO.contactIndustry}"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="addAcclabelStyle">Experience</label><s:select cssClass="form-control SelectBoxStyles" name="contactExperience" id="contactExperience" headerKey="-1" headerValue="Select Experience" list="experience"  tabindex="1" value="%{accountContactVTO.contactExperience}"/>
                                                        </div>
                                                        <div class="col-lg-3 col-md-offset-2">
                                                            <label class="addAcclabelStyle">Work&nbsp;Location</label><s:select cssClass="form-control SelectBoxStyles" name="workingLocation" id="workingLocation" headerKey="-1" headerValue="Select Work Location" list="workLocations"   value="%{accountContactVTO.workingLocation}" tabindex="1" />
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="addAcclabelStyle">SSN&nbsp;Number</label><s:textfield cssClass="form-control " name="contactSsnNo" id="contactSsnNo" placeholder="SSN Number" maxLength="20" tabindex="1" value="%{accountContactVTO.contactSsnNo}"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <s:if test="'customerlogin'!=flag && 'vendorlogin'!=flag">
                                                                <label class="contactLabelStyle">Role:</label><s:select name="primaryRole" list="orgRoles"  theme="simple" cssClass="SelectBoxStyles form-control" value="%{primaryRole}" /> 
                                                            </s:if>
                                                        </div>



                                                        <div class="col-lg-6 col-md-offset-2 ">
                                                            <label  class="labelStylereq" style="margin:-0px;">Skills:</label>


                                                            <s:select cssClass="commentsStyle" name="skillCategoryValueList"  id="skillListValue" list="skillMap" multiple="true" onfocus="clearErrosMsgForGrouping()"  value="%{accountContactVTO.skillListSet}"/> 
                                                            <s:hidden id="contactSkillValues" name="contactSkillValues" />
                                                        </div>
                                                        <s:if test="'customerlogin'!=flag && 'vendorlogin'!=flag">
                                                            <div class="col-lg-3 ">
                                                                <label class="contactLabelStyle">Status:</label><s:select id="status" value="%{accountContactVTO.status}" name="status"  cssClass="SelectBoxStyles form-control" accesskey="" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','Registered':'Registered'}"/>
                                                            </div>
                                                        </s:if>
                                                        <div class="inner-reqdiv-elements">
                                                            <div class="col-lg-12 ">
                                                                <label  class="task-label" style="max-height:10px;">Education:</label>
                                                                <s:textarea cssClass="titleStyle"   id="contactEducation"  name="contactEducation" maxlength="500" cols="100" rows="2" onkeyup="checkCharactersDescription(this)" tabindex="7" value="%{accountContactVTO.contactEducation}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="inner-reqdiv-elements">
                                                    <div class="row"> 
                                                        <h4><b>&nbsp;&nbsp;&nbsp;&nbsp;Contact Address</b></h4> 
                                                        <div class="col-lg-4">
                                                            <label class="contactLabelStyle">Address</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPAddress}"  id="conAddress" maxLength="98" name="conAddress" placeholder="Address"     /> <!-- onkeyup="paddresValidation()" -->
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <%--label class="contactLabelStyle">Designation:</label><s:select cssClass="SelectBoxStyles form-control" name="contactDesignation" id="designation" headerKey="0" value="%{accountContactVTO.contactDesignation}" headerValue="-Select-" list="%{accountContactVTO.titles}"/--%>
                                                            <label class="contactLabelStyle">Address2</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPAddress2}" id="conAddress2" maxLength="98" name="conAddress2" placeholder="Address2"/>
                                                        </div>

                                                        <div class="col-lg-4">
                                                            <label class="contactLabelStyle">City</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPCity}"  id="conCity" maxLength="18" name="conCity" pattern="[a-zA-Z\s]{3,}" title="must be valid name" placeholder="City" />
                                                        </div>
                                                        <s:if test="accountContactVTO.conPCountry==-1">
                                                            <div class="col-lg-4">
                                                                <label class="contactLabelStyle">Country</label><s:select cssClass="form-control SelectBoxStyles" required="true" value="3" name="conCountry" id="conCountry"   list="countryNames" onchange="ConPermanentStateChange()" />
                                                            </div>
                                                        </s:if>
                                                        <s:else>
                                                            <div class="col-lg-4">
                                                                <label class="contactLabelStyle">Country</label><s:select cssClass="form-control SelectBoxStyles" required="true" value="%{accountContactVTO.conPCountry}" name="conCountry" id="conCountry"  headerKey="-1" headerValue="Select Country" list="countryNames" onchange="ConPermanentStateChange()" />
                                                            </div>
                                                        </s:else>
                                                        <div class="col-lg-4">
                                                            <label class="contactLabelStyle">State</label><s:select cssClass="form-control SelectBoxStyles"  required="true" name="conState" id="conState" headerKey="-1" headerValue="Select State" value="%{accountContactVTO.conPState}" list="%{accountContactVTO.state1}"/>
                                                        </div>

                                                        <div class="col-lg-4">
                                                            <label class="contactLabelStyle">Zip</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPZip}"  id="conZip" name="conZip" maxLength="10"  placeholder="Zip"  /> <!-- onkeyup="contactPZipValidation()" -->
                                                        </div>

                                                    </div>
                                                </div>
                                                <%--<div class="col-lg-3">

                                                <%--<label class="labelStyle">Departments</label>
                                                <s:textfield cssClass="contactInputStyle" id="departments" name="department" value="%{accountContactVTO.department}"/>
                                                <label class="labelStyle">Titles</label>
                                                <s:textfield cssClass="contactInputStyle" name="titles" id="titlesId" value="%{accountContactVTO.title}"/>
                                                
                                                <%--  <div class="checkbox-inline  " >
                                                      <label for="is_manager" class="checkbox  ">
                                                          <s:checkbox name="isManager" id="isManager" value="%{accountContactVTO.isManager}"/>is Manager</label>
                                                  </div>
                                                  <div class="checkbox-inline  " >
                                                      <label for="team_leader" class="checkbox ">
                                                          <s:checkbox name="isTeamLead" id="isTeam" value="%{accountContactVTO.isTeamLead}"/>is TeamLead</label>
                                                  </div>  

                                                </div>--%>

                                            </div>
                                    </div>
                                </div>
                                <s:hidden name="flagname" value="%{flag}"></s:hidden>

                                <%--div class="row">

                                            <!-- Contact Information , start  -->
                                            <div class="col-lg-6">

                                                <div id="AddressBox"> 
                                                    <div class="contactInfoDiv">
                                                        <table >
                                                            <tr id="trStyleContact"><td>&nbsp;&nbsp;Permanent Address &nbsp;&nbsp;</td></tr>
                                                            <span id="spanUpdatep" class="pull-right"> </span>     

                                                        </table>
                                                    </div>
                                                    <div id="margins" class="showUpdatep"><br>

                                                        <center> <table>
                                                                <br/>
                                                                <span><errmsg></errmsg></span>
                                                            <s:textfield cssClass="form-control" label="Address" value="%{accountContactVTO.conPAddress}"  id="conAddress" name="conAddress" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z\s]{3,}" onchange="try{setCustomValidity('')}catch(e){}"  onkeyup="paddresValidation()"  />
                                                            <s:textfield cssClass="form-control" label="Address2" value="%{accountContactVTO.conPAddress2}" id="conAddress2" name="conAddress2"/>
                                                            <s:textfield cssClass="form-control" label="City" value="%{accountContactVTO.conPCity}"  id="conCity" name="conCity" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z\s]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="contactPcityValidation()" />


                                                            <s:textfield cssClass="form-control" label="Zip" value="%{accountContactVTO.conPZip}"  id="conZip" name="conZip" maxLength="5"  required="true" onkeyup="contactPZipValidation()" />
                                                            <s:select cssClass="form-control SelectBoxStyles" label="Country" value="%{accountContactVTO.conPCountry}" name="conCountry" id="conCountry"  headerKey="" headerValue="Select Country" list="countryNames" onchange="ConPermanentStateChange()" tabindex="1"/>
                                                            <s:select cssClass="form-control SelectBoxStyles"  label="State" name="conState" id="conState" headerKey="" value="%{accountContactVTO.conPState}" list="%{accountContactVTO.state1}"/>
                                                        </table></center>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">

                                            <div id="AddressBox">
                                                <div class="contactInfoDiv" >
                                                    <table >
                                                        <tr id="trStyleContact" ><td>&nbsp;&nbsp;Current&nbsp;Address &nbsp;&nbsp;</td></tr>
                                                        <span id="spanUpdatec" class="pull-right">    
                                                    </table>
                                                </div>
                                                <div id="margins"  class="showUpdatec">

                                                    <errmsgc></errmsgc>
                                                    <center> <table>
                                                            <s:if test="accountContactVTO.checkAddress==true">
                                                                <s:checkbox label="Same as Permenant Address" name="checkAddress" value="true" id="checkAddress" onclick="FillContactAddress()"   ></s:checkbox><br/>
                                                                <s:textfield cssClass="form-control" label="Address" value="%{accountContactVTO.conCAddress}" disabled="true" id="conCAddress" name="conCAddress" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="CaddresValidation()" />

                                                                <s:textfield cssClass="form-control" label="Address2" value="%{accountContactVTO.conCAddress2}" disabled="true" id="conCAddress2" name="conCAddress2" />
                                                                <s:textfield cssClass="form-control" label="City" value="%{accountContactVTO.conCCity}" disabled="true" id="conCCity" name="conCCity" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="ccityValidation()" />

                                                                <s:textfield cssClass="form-control" label="Zip" value="%{accountContactVTO.conCZip}" disabled="true" id="conCZip" name="conCZip" maxLength="5" required="true" onkeyup="contactCZipValidation()"/>
                                                                <s:select cssClass="form-control SelectBoxStyles" label="Country" value="%{accountContactVTO.conCCountry}" disabled="true" name="conCCountry" id="conCCountry" headerKey="" headerValue="Select Country" list="countryNames" onchange="ConCurrentStateChange()" tabindex="1"/>
                                                                <s:select cssClass="form-control SelectBoxStyles" label="State" name="conCState" id="conCState" headerKey="" disabled="true" value="%{accountContactVTO.conCState}" headerValue="Select State" list="%{accountContactVTO.state2}"  />

                                                            </s:if>  
                                                            <s:else>
                                                                <s:checkbox label="Same as Permenant Address" name="checkAddress"  id="checkAddress" onclick="FillContactAddress()"   ></s:checkbox><br/>
                                                                <s:textfield cssClass="form-control" label="Address" value="%{accountContactVTO.conCAddress}" id="conCAddress" name="conCAddress" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="CaddresValidation()" />
                                                                <s:textfield cssClass="form-control" label="Address2" value="%{accountContactVTO.conCAddress2}" id="conCAddress2" name="conCAddress2" />
                                                                <s:textfield cssClass="form-control" label="City" value="%{accountContactVTO.conCCity}" id="conCCity" name="conCCity" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="ccityValidation()" />
                                                                <s:textfield cssClass="form-control" label="Zip" value="%{accountContactVTO.conCZip}" id="conCZip" name="conCZip" maxLength="5" required="true" />
                                                                <s:select cssClass="form-control SelectBoxStyles" label="Country" value="%{accountContactVTO.conCCountry}" name="conCCountry" id="conCCountry" headerKey="" headerValue="Select Country" list="countryNames" onchange="ConCurrentStateChange()" tabindex="1"/>
                                                                <s:select cssClass="form-control SelectBoxStyles" label="State" name="conCState" id="conCState" headerKey="" value="%{accountContactVTO.conCState}" headerValue="Select State" list="%{accountContactVTO.state2}"  />
                                                            </s:else> 
                                                        </table></center>
                                                </div>
                                            </div>
                                        </div>                
                                    </div--%>    
                                <div class="col-lg-10"></div>
                                <div class="col-lg-2">
                                    <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton fa fa-refresh form-control" value="Update"  /> 
                                </div>
                            </div>
                            </form>  
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript" >
                $("#conPhone").mask("(999)-999-9999");
                $("#conCPhone").mask("(999)-999-9999");
            </script>
            <script type="text/javascript" >
                $("#Officephone").mask("(999)-999-9999");
                $("#moblieNumber").mask("(999)-999-9999");
                $("#homePhone").mask("(999)-999-9999");
            </script>

            <!-- Content Close -->
        </div>
    </div>

    <%--close of future_items--%>
</div>

<div id="imageupdate_popup">
    <div id="imageupdateOverlay">

        <div style="background-color: #3BB9FF ">
            <table>
                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Change Profile Image&nbsp;&nbsp; </font></h4></td>
                <span class=" pull-right"><h5><a href="#" class="imageupdate_popup_close" onclick="openUploadFileDialogueClose()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
            </table>
        </div>
        <div>
            <br>
            <s:form action="ProfileImageUpdate" id="imageUpdateFormId" theme="simple"   enctype="multipart/form-data" >
                <div>
                    <span><imageErrorMsg></imageErrorMsg></span>
                    <div class="inner-addtaskdiv-elements">
                        <div id="message"></div>

                        <s:hidden name="contactId" value="%{contactId}"/>
                        <s:hidden name="accountSearchID" value="%{accountSearchID}"/>
                        <s:hidden name="flag" value="%{flag}"/>
                        <s:hidden name="accountType" id="accountType" value="%{accountType}" />
                        <s:file name="imageupdate" id="imageupdate"/>
                    </div>
                    <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                    <center><s:submit cssClass="cssbutton" value="ADD" onclick="return ValidateFileUpload()" theme="simple"  /></center><br>
                </div>

            </div>
        </s:form>
    </div>
</div>


<!-- content end -->
</section><!--/form-->
<div style="height: 18px"></div>
<script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script>
            $(document).ready(function() {
                $('#skillListValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            });
            
        </script>
<script type="text/javascript" >
                $("#conPhone").mask("(999)-999-9999");
                $("#conCPhone").mask("(999)-999-9999");
            </script>
            <script type="text/javascript" >
                $("#Officephone").mask("(999)-999-9999");
                $("#moblieNumber").mask("(999)-999-9999");
                $("#homePhone").mask("(999)-999-9999");
            </script>
<footer id="footer"><!--Footer-->
    <div class="footer-bottom" id="footer_bottom">
        <div class="container">
            <s:include value="/includes/template/footer.jsp"/>
        </div>
    </div>
</footer><!--/Footer-->
</body>
</html>
