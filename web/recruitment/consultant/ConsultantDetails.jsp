<%-- 
    Document   : AccountAdd
    Created on : Apr 12, 2015, 7:05:25 PM
    Author     : NagireddySeerapu
--%>

<%@page import="com.mss.msp.recruitment.ConsultantVTO"%>
<%@page import="com.mss.msp.usersdata.UserVTO"%>
<%@page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@page import="com.mss.msp.recruitment.ConsultantVTO"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Consultant Details Page</title>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/media_queries.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">


        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <%--script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/includes/js/general/consultantOverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sweetalert.min.js"/>'></script>


    </head>
    <body style="overflow-x: hidden" onload="onloadIdProofDetails();consultdoOnLoad(); defaultClick();">
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
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                        <%--div id="profileBox" class="" style=""; margin-top: 5px">
                             <div class="features_items">
                                <div class="col-lg-14 ">
                                    <div class="backgroundcolor" >

                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Consultant Details</font--%>
                        <s:if test="consultFlag=='vendor'">
                            <s:url var="myUrl" action="getMyConsultantSearch.action">
                                <s:param name="consultantFlag"><s:property value="%{consultantFlag}"/></s:param> 

                            </s:url>
                            <span class="pull-right" style="padding: 19px"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span>
                            </s:if>
                            <s:if test="consultFlag=='customer'">

                            <s:if test="vendor=='yes'">
                                <s:url var="myUrl" action="../../Requirements/requirementedit.action">
                                    <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                    <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                    <s:param name="accountFlag"><s:property value="%{accountFlag}"/></s:param> 
                                    <s:param name="customerFlag"><s:property value="%{customerFlag}"/></s:param> 
                                    <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                    <s:param name="vendor"><s:property value="%{vendor}"/></s:param >
                                    <s:param name="reqFlag">consultantTab</s:param>
                                </s:url>
                            </s:if>
                            <s:elseif test="techReviewFlag=='techReview'">
                                <s:url var="myUrl" action="getTechReviewDetails.action">

                                </s:url>
                            </s:elseif>
                            <s:else>
                                <s:url var="myUrl" action="../../Requirements/requirementedit.action">
                                    <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                    <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                    <s:param name="accountFlag"><s:property value="%{accountFlag}"/></s:param> 
                                    <s:param name="customerFlag"><s:property value="%{customerFlag}"/></s:param> 
                                    <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                    <s:param name="reqFlag">consultantTab</s:param>
                                </s:url>
                            </s:else>

                            <span class="pull-right" style="padding: 19px"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span>
                            </s:if>
                            <s:if test="consultFlag =='consultant'">
                                <s:url var="myUrl" action="../../users/general/myprofile.action">
                                    <%--s:param name="consultantFlag"><s:property value="%{consultantFlag}"/></s:param--%> 
                                </s:url>
                            <span class="pull-right" style="padding: 19px"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/backButton.png"/>" height="25" width="25"></s:a></span>
                            </s:if>
                            <%--/h4>
                        </div>

                            </div>


                                </div>
                            </div--%>
                        <!-- Content end  -->
                        <br>
                        <!-- tab starting  -->
                        <s:if test="%{consultFlag !='consultant'}">
                            <!--<div><headingmess id="headingmessage"  class="acc_menu_heading pull-right" style="display:block">Consultant Details</headingmess>    </div>--> 
                        </s:if>
                        <!-- Nav tabs -->
                        <s:if test="%{consultFlag !='consultant'}">
                            <%--ul class="active_details" >
                                <li class="dropdown"  >

                                        Nav tabs 
                                        <ul class="panel-body nav-stacked  dropdown-menu " style="position:absolute">

                                            <li class=""><a aria-expanded="false" onclick="cheadingMessage(this);" id="c_details"  href="#Consultant" data-toggle="tab"> Consultant Details </a>
                                            </li>
                                            <li class=""><a aria-expanded="false" onclick="cheadingMessage(this);" id="c_skill"  href="#consult_Skillslide" data-toggle="tab"> Skill </a>
                                            </li>
                                            <li class=""><a aria-expanded="false"  onclick="cheadingMessage(this);" id="c_security"  href="#consult_security-info" data-toggle="tab"> Confidential </a>
                                            </li>
                                            <li class=""><a aria-expanded="false" onclick="cheadingMessage(this);" id="c_activities"  href="#consult_activities" data-toggle="tab"> Activities </a>
                                            </li>
                                            <li id="attachLi" class=""><a aria-expanded="false" onclick="showAttachmentDetails('<%= request.getParameter("consult_id")%>');cheadingMessage(this);" id="c_attach" href="#consult_attach" data-toggle="tab"> Attachment </a>
                                            </li>
                                            <li class=""><a aria-expanded="false" onclick="cheadingMessage(this);" href="#consult_personal" id="c_personal" data-toggle="tab">Personal Details</a>
                                            </li>
                                            <li class=""><a aria-expanded="false"  onclick="cheadingMessage(this);" href="#consult_notes" id="c_notes" data-toggle="tab">Notes</a>
                                            </li>
                                            <li class=""><a aria-expanded="false"  onclick="cheadingMessage(this);" href="#consult_tech-review" id="c_tech-review" data-toggle="tab">Tech Review</a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul--%>
                        </s:if>
                        <ul class="nav nav-tabs">
                            <li class="" id="consultantLi"><a aria-expanded="false" onclick="cheadingMessage(this);" id="c_details"  href="#Consultant" data-toggle="tab"> Consultant Details </a>
                            </li>
                            <li id="attachLi" class=""><a aria-expanded="false" onclick="showAttachmentDetails('<%= request.getParameter("consult_id")%>');cheadingMessage(this);" id="c_attach" href="#consult_attach" data-toggle="tab"> Attachments </a>
                            </li>
                        </ul>

                        <%-- tab content starts --%>
                        <div class="tab-content">

                            <%-- consultant details --%>
                            <div class="tab-pane fade in active" id="Consultant" >

                                <s:if test="hasActionMessages()">
                                    <div>
                                        <s:actionmessage cssClass="actionMessagecolor"/>
                                    </div>
                                </s:if>

                                <div class="panel-body">
                                    <!-- content start -->

                                    <!-- Content start -->
                                    <s:if test="%{updateMessage =='success'}">
                                        <span><successMessage><font style="color: green"> Consultant record Updated successfully!</font></successMessage></span>
                                            </s:if>
                                            <s:if test="%{updateMessage=='failure'}">
                                        <font style="color: green"> Sorry, Consultant record not update!</font>
                                    </s:if> 
                                    <span><consult_error></consult_error></span>
                                    <form action="updateConsultantDetails" id="consultantForm" theme="simple">
                                        <%--div><span><consult_error></consult_error></span></div--%>
                                        <s:hidden id="consultFlag" name="consultFlag" value="%{consultFlag}"/>
                                        <s:hidden value="%{ConsultantVTO.consult_id}" name="consult_id" />

                                        <s:hidden id="requirementId" name="requirementId" value="%{requirementId}"/>
                                        <s:hidden id="accountSearchID" name="accountSearchID" value="%{accountSearchID}"/>
                                        <s:hidden id="accountFlag" name="accountFlag" value="%{accountFlag}"/>
                                        <s:hidden id="customerFlag" name="customerFlag" value="%{customerFlag}"/>
                                        <s:hidden id="jdId" name="jdId" value="%{jdId}"/>
                                        <s:hidden id="vendor" name="vendor" value="%{vendor}" />
                                        <s:hidden id="consultantFlag" name="consultantFlag" value="%{consultantFlag}" />
                                        <s:hidden name="consultantIdProofAttach" value="%{consultantVTO.consultantIdProofAttach}" id="consultantIdProofAttach"/>
                                        <s:hidden id="consultantIdProofhidden" name="consultantIdProofhidden" value="%{consultantVTO.consultantIdProof}" />

                                        <div class="col-sm-4 required">
                                            <s:hidden value="%{consultantVTO.consult_email}" name="consult_email" />


                                            <label class="labelStylereq" >Email Id:</label>   
                                            <s:textfield cssClass="form-control" disabled="true" value="%{consultantVTO.consult_email}" required="true" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" id="consult_email" placeholder="Email Id" tabindex="1" onkeyup="consultvalid_email()"/>
                                        </div>
                                        <div class="col-sm-4" >
                                            <label class="labelStylereq" >Available Date:</label>
                                            <s:textfield cssClass="form-control dateImage" name="consult_favail" value="%{consultantVTO.consult_favail}" id="consult_favail"   placeholder="Date" onkeypress="return consultantDateRepository(this)" tabindex="1"/>
                                        </div>
                                        <div class="col-sm-4" >
                                            <label class="labelStylereq" >Available:</label>
                                            <s:select cssClass="form-control SelectBoxStyles " value="%{consultantVTO.consult_available}" name="consult_available" id="consult_available" onkeyup="consultvalid_avail()"   headerKey="-1" headerValue="Select availabilty" list="#@java.util.LinkedHashMap@{'Y':'Yes','N':'No'}" tabindex="1"/>
                                        </div>
                                        <div class="col-sm-4 required">
                                            <label class="labelStylereq" >First Name:</label>    
                                            <s:textfield cssClass="form-control" name="consult_fstname" value="%{consultantVTO.consult_fstname}" id="consult_fstname" required="true" placeholder="First Name" tabindex="1" onkeyup="consultvalid_fname()" maxlength="30"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <span class="required">
                                                <label class="labelStylereq" >Last Name:</label>   
                                                <s:textfield cssClass="form-control" name="consult_lstname" value="%{consultantVTO.consult_lstname}" id="consult_lstname" required="true" onkeyup="consultvalid_lstname()" placeholder="Last Name" tabindex="1" maxlength="30"/>
                                            </span>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Middle Name:</label>  
                                            <s:textfield cssClass="form-control" name="consult_midname" value="%{consultantVTO.consult_midname}"  placeholder="Middle Name" id="consult_midname" tabindex="1" maxlength="30"/>
                                        </div>
                                        <div class="col-sm-4 required">
                                            <label class="labelStylereq" >Date of Birth:</label>   
                                            <s:textfield cssClass="form-control dateImage" name="consult_dob" value="%{consultantVTO.consult_dob}" required="true" id="consult_dob" onkeypress="return consultantDateRepository(this)" tabindex="1"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Home Phone:</label>
                                            <s:textfield cssClass="form-control" name="consult_homePhone" value="%{consultantVTO.consult_homePhone}" id="consult_homePhone" placeholder="Home Phone" tabindex="1"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <span class="required">  
                                                <label class="labelStylereq" >Mobile No:</label>      
                                                <s:textfield cssClass="form-control" name="consult_mobileNo" value="%{consultantVTO.consult_mobileNo}" required="true" id="consult_mobileNo" placeholder="Mobile Number" tabindex="1" onkeyup="mobValidation()"/>
                                            </span>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Alternate Email:</label>
                                            <s:textfield cssClass="form-control" name="consult_alterEmail" value="%{consultantVTO.consult_alterEmail}" id="consult_alterEmail" placeholder="Alternate Email Id" onblur="consultValidAlterEmail();" tabindex="1" maxlength="60"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >SSN No:</label>
                                            <s:textfield cssClass="form-control" name="consult_ssnNo" value="%{consultantVTO.consult_ssnNo}" id="consult_ssnNo" placeholder="SSN Number"  tabindex="1" maxlength="20"/>                                     
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Living Country:</label>  
                                            <s:select cssClass="form-control SelectBoxStyles" name="consult_lcountry" value="%{consultantVTO.consult_lcountry}" list="country" placeholder="Living Country" id="consult_lcountry" tabindex="1"/>
                                            <%--s:radio label="Marital Status" name="consult_mStatus" value="%{consultantVTO.consult_mStatus}" id="consult_mStatus" required="true" list="#@java.util.LinkedHashMap@{'S':'Single','M':'Married'}"  /--%>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Linkedin link:</label>
                                            <s:textfield cssClass="form-control" name="consult_linkedInId" id="consult_linkedInId" placeholder="Linked In link"  value="%{consultantVTO.consult_linkedInId}" tabindex="1" maxlength="120"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Facebook link:</label>
                                            <s:textfield cssClass="form-control" name="consult_facebookId" id="consult_facebookId" value="%{consultantVTO.consult_facebookId}" placeholder="Facebook link"  tabindex="1" maxlength="120"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="labelStylereq" >Twitter link:</label>
                                            <s:textfield cssClass="form-control" name="consult_twitterId" id="consult_twitterId" value="%{consultantVTO.consult_twitterId}" placeholder="Twitter link"  tabindex="1" maxlength="120"/>
                                        </div>
                                        <div class="col-sm-4">
                                        <label class="labelStylereq" >Identity Proof:</label>
                                        <s:select cssClass="form-control SelectBoxStyles"  value="%{consultantVTO.consultantIdProof}" headerKey="0" headerValue="Select ID Proof" name="consultantIdProof" id="consultantIdProof" list="#@java.util.LinkedHashMap@{'VS':'Visa','PP':'Passport','DL':'Driving Licence'}"  tabindex="10" onchange="getVisaDetails();"/>
                                        </div>
                                        
                                        <div class="col-sm-4" id="visaSelectDiv" style="display: none">
                                            <label class="labelStylereq" >Visa Type:</label>
                                            <s:select cssClass="form-control SelectBoxStyles"  value="%{consultantVTO.consultantVisa}" headerKey="0" headerValue="Select Visa" name="consultantVisa" id="consultantVisa" list="#@java.util.LinkedHashMap@{'A-3':'A-3','G-5':'G-5','NATO-7':'NATO-7','B-1':'B-1','H-1B':'H-1B','H-1B1':'H-1B1','H-1B':'H-1B','H-2A':'H-2A','H-2B':'H-2B','J-1':'J-1'}"  tabindex="10"/>
                                        </div>

                                        <div class="col-sm-4" id="proofdownloadDiv" style="display: none">
                                            <s:label><figcaption>Download ID Proof:<button type='button'  onclick="doConsultAttachmentDownload(-1)"><img src='../../includes/images/download.png' height=25 width=25 ></button><img src='../../includes/images/deleteImage.png' height=20 width=20 onclick="removeConsultantAttachement();"></figcaption></s:label>

                                            <s:if test='downloadFlag=="noVisa"'>
                                                <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                                </s:if>
                                                <s:if test='downloadFlag=="noVisaFile"'>
                                                <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                                </s:if> 
                                        </div>
                                        <div class="col-sm-4" id="proofuploadDiv" style="display: none">
                                            <label class="labelStylereq" ></label>
                                            <s:label><figcaption>Upload ID Proof:<button type='button'  class="consultVisaAttachment_popup_open" onclick="openDialogforVisaAttachment()"><img src='../../includes/images/Browse.png' height=25 width=25  ></button></figcaption></s:label>
                                            </div>
                                            <div class="row"></div>
                                            <!-- Contact Information , start  -->
                                            <div class="col-sm-6" style="">
                                                <span id="updateResultp">Permanent Address Updated successfully</span>
                                                <div id="AddressBox"> 
                                                    <div class="contactInfoDiv">
                                                        <table>
                                                            <tr id="trStyleContact"><td>&nbsp;&nbsp;Permanent Address &nbsp;&nbsp;</td></tr>
                                                        </table>
                                                    </div>

                                                    <div class="col-lg-10 col-md-offset-1">
                                                        <label class="labelStylereq" >Address:</label>
                                                    <s:textfield cssClass="form-control" id="consult_Address" placeholder="Address" value="%{consultantVTO.consult_Address}" name="consult_Address" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onclick="removedCheckedAddress();" maxlength="100" tabindex="1"/>
                                                    <label class="labelStylereq" >Address2:</label>        
                                                    <s:textfield cssClass="form-control" id="consult_Address2" placeholder="Address2" value="%{consultantVTO.consult_Address2}" name="consult_Address2" onclick="removedCheckedAddress();" maxlength="100" tabindex="1"/>
                                                    <span class="required">
                                                        <label class="labelStylereq" >City:</label>             
                                                        <s:textfield cssClass="form-control" id="consult_City" placeholder="City" value="%{consultantVTO.consult_City}" name="consult_City" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pcityValidation()" onclick="removedCheckedAddress();" maxlength="20" tabindex="1"/>
                                                        <%-- <s:select cssClass="form-control SelectBoxStyles" name="consult_CCountry" id="consult_CCountry" label="Country" headerKey="" headerValue="Select Country" list="consult_CCountry" onchange="ConsultantStateChange()" tabindex="1"/>
                                                        <s:select cssClass="form-control SelectBoxStyles" label="State" id="consult_CState" name="consult_CState"    headerKey="" headerValue="Select state" list="{'Relocation','Travel'}" /> --%>
                                                        <label class="labelStylereq" >Country:</label>       
                                                        <s:select cssClass="form-control SelectBoxStyles" id="consult_Country" onchange="ConsultantEditStateChange()" list="country" value="%{consultantVTO.consult_Country}" name="consult_Country" required="true" onclick="removedCheckedAddress();" tabindex="1"/>
                                                        <label class="labelStylereq" >State:</label>        
                                                        <s:select cssClass="form-control SelectBoxStyles" id="consult_State" name="consult_State" value="%{consultantVTO.consult_State}"   headerKey="" headerValue="Select state"   list="permanentState" onchange="StateChangeValidation()"  tabindex="1"/>  <%-- onchange="alert(this.options[this.selectedIndex].innerHTML)" --%>
                                                    </span>  
                                                    <%-- <s:textfield cssClass="form-control " label="State" id="consult_State" list="{'abc','cde'}" value="%{consultantVTO.consult_State}" name="consult_State" required="true" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pStateValidation()"  />   --%>
                                                    <label class="labelStylereq" >Zip:</label>        
                                                    <s:textfield cssClass="form-control" id="consult_Zip" placeholder="Zip" value="%{consultantVTO.consult_Zip}" name="consult_Zip"  minlength="4" maxlength="10" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pZipValidation()" onclick="removedCheckedAddress();" tabindex="1"/>
                                                    <%--s:textfield cssClass="form-control" label="Phone" id="consult_Phone" value="%{consultantVTO.consult_Phone}" name="consult_Phone" required="true" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pPhoneValidation()" onclick="removedCheckedAddress();"/--%>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <span id="updateResultc">Current Address Updated successfully</span>
                                            <div id="AddressBox">
                                                <div class="contactInfoDiv">
                                                    <table>
                                                        <tr id="trStyleContact" ><td>&nbsp;&nbsp;Current Address &nbsp;&nbsp;</td></tr>
                                                    </table>
                                                </div>
                                                <div  class="col-lg-10 col-md-offset-1">
                                                    <center>
                                                        <table>
                                                            <s:checkbox label="Same as Permanent Address" name="consult_checkAddress"  id="consult_checkAddress" value="%{consultantVTO.address_flag}" onclick="sameAsAddress();"   ></s:checkbox>
                                                                <span><j2></j2></span>

                                                            </table>
                                                        </center>
                                                        <label class="labelStylereq" >Address:</label>   
                                                    <s:textfield cssClass="form-control" value="%{consultantVTO.consult_CAddress}" id="consult_CAddress" placeholder="Address" name="consult_CAddress" oninvalid="setCustomValidity('Must be valid fn')"   onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCAddressValidation()" maxlength="100" tabindex="1"/>
                                                    <label class="labelStylereq" >Address2:</label>    
                                                    <s:textfield cssClass="form-control" value="%{consultantVTO.consult_CAddress2}" id="consult_CAddress2" placeholder="Address2" name="consult_CAddress2"  maxlength="100" tabindex="1"/>
                                                    <span class="required">
                                                        <label class="labelStylereq" >City:</label>    
                                                        <s:textfield cssClass="form-control" value="%{consultantVTO.consult_CCity}" id="consult_CCity" placeholder="City" name="consult_CCity" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCCityValidation()" maxlength="20" tabindex="1"/>
                                                        <%-- <s:select cssClass="form-control SelectBoxStyles" name="consult_CCountry" id="consult_CCountry" label="Country" headerKey="" headerValue="Select Country" list="consult_CCountry" onchange="ConsultantStateChange()" tabindex="1"/>
                                                        <s:select cssClass="form-control SelectBoxStyles" label="State" id="consult_CState" name="consult_CState"    headerKey="" headerValue="Select state" list="{'Relocation','Travel'}" /> --%>
                                                        <label class="labelStylereq" >Country:</label>    
                                                        <s:select cssClass="form-control SelectBoxStyles"  value="%{consultantVTO.consult_CCountry}" list="country" id="consult_CCountry" name="consult_CCountry" required="true"  onchange="ConsultantCurrentStateChange()"  onkeyup="pCCountryValidation()" tabindex="1"/>
                                                        <label class="labelStylereq" >State:</label>     
                                                        <s:select cssClass="form-control SelectBoxStyles" name="consult_CState" id="consult_CState"  value="%{consultantVTO.consult_CState}"   headerKey="" headerValue="Select  state" list="currentState" onchange="CurrentStateChangeValidation()" tabindex="1" />  <%-- onchange="alert(this.options[this.selectedIndex].innerHTML)" --%>
                                                    </span>   <%--  <s:textfield cssClass="form-control" label="State" value="%{consultantVTO.consult_CState}"  id="consult_CState" name="consult_CState" required="true" onkeyup="pCStateValidation()" />  --%>
                                                    <label class="labelStylereq" >Zip:</label>  
                                                    <s:textfield cssClass="form-control" value="%{consultantVTO.consult_CZip}" id="consult_CZip" placeholder="Zip" name="consult_CZip" minlength="4" maxlength="10" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCZipValidation()" tabindex="1"/>

                                                </div>
                                            </div>
                                        </div>                


                                        <s:if test="%{consultFlag=='vendor'}">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label  class="labelStylereq" >Education:</label>
                                                    <s:textarea cssClass="titleStyle" value="%{consultantVTO.consult_education}"   id="consult_education" placeholder="Education" name="consult_education" maxlength="500" tabindex="1"/>
                                                </div>
                                            </div>
                                            <div class="" id="task-panel" style="margin-left:-13%">
                                                <div  class="col-sm-4 " >
                                                    <span class="required">
                                                        <label  class="labelStylereq" >Job Title:</label> 
                                                        <s:textfield cssClass="form-control" name="consult_jobTitle" value="%{consultantVTO.consult_jobTitle}" id="consult_jobTitle" placeholder="Job Title" tabindex="1" required="true" maxlength="30" onkeyup="jobtitleValidate()"/>                                      
                                                        <label  class="labelStylereq" >Industry:</label>     
                                                        <s:select cssClass="form-control SelectBoxStyles " value="%{consultantVTO.consult_industry}" name="consult_industry" id="consult_industry"  headerKey="" headerValue="Select Industry" list="industry" required="true" onchange="industryValidate()" tabindex="2"/>
                                                    </span> 
                                                    <label class="labelStylereq" >Relocation:</label>       
                                                    <s:select cssClass="form-control SelectBoxStyles" value="%{consultantVTO.consult_relocation}" name="consult_relocation" id="consult_relocation" headerKey="-1" headerValue="Select Relocation" list="{'Yes','No'}" onchange="handleSelect(this)" tabindex="3"/>
                                                    <label  class="labelStylereq" >Status:</label>    
                                                    <s:select cssClass="form-control SelectBoxStyles" value="%{consultantVTO.consult_status}" name="consult_status" id="consult_status" headerKey=""  list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" tabindex="4"/>

                                                </div>
                                                <div class="col-sm-4">      
                                                    <span class="required"> 
                                                        <label  class="labelStylereq" >Experience:</label>     
                                                        <s:select cssClass="form-control SelectBoxStyles" value="%{consultantVTO.consult_experience}" name="consult_experience" id="consult_experience" headerKey="" headerValue="Select experience" list="experience" required="true" onchange="expValidate()" tabindex="1"/>
                                                    </span> <%--s:textfield cssClass="form-control" value="%{consultantVTO.consult_workPhone}"  name="consult_workPhone" id="consult_workPhone" label="Work Phone" placeholder="Work Phone " tabindex="1"/--%> 
                                                    <label  class="labelStylereq" >Referred By:</label>   
                                                    <s:textfield cssClass="form-control" value="%{consultantVTO.consult_referredBy}" name="consult_referredBy" id="consult_referredBy" placeholder="Referred by"  tabindex="2" maxlength="30"/>
                                                    <label  class="labelStylereq pref_country" >Preferred Country:</label>
                                                    <s:select cssClass="form-control SelectBoxStyles" value="%{consultantVTO.consult_preferredCountry}"   name="consult_preferredCountry" id="consult_preferredCountry"  headerKey="-1" headerValue="Select Country" list="country" onchange="AddConsultantEditPreferredStateChange()"  tabindex="3"/>

                                                </div>
                                                <div class="col-sm-4">  
                                                    <span class="required">
                                                        <label  class="labelStylereq" >Working Country:</label>    
                                                        <s:select cssClass="form-control SelectBoxStyles" value="%{consultantVTO.consult_wcountry}"  name="consult_wcountry" id="consult_wcountry"  headerKey="" headerValue="Select Country" list="country" tabindex="1" required="true" onchange="workingCountryValidate()"/>
                                                    </span>
                                                    <label  class="labelStylereq" >Rate/Salary:</label>
                                                    <s:textfield cssClass="form-control" value="%{consultantVTO.consult_salary}" name="consult_salary" id="consult_salary" placeholder="Rate/Salary" tabindex="2" maxlength="10"/>

                                                    <label  class="labelStylereq pref_state" >Preferred State:</label>
                                                    <s:select cssClass="" value="%{consultantVTO.consult_preferredState}"  name="consult_preferredState" multiple="true" id="consult_preferredState"     headerKey="-1" headerValue="Select preferred state" list="preState" tabindex="3"/>
                                                    <s:hidden  id="PrefstateValues" name="PrefstateValues" />
                                                </div>
                                            </div></s:if>
                                        <s:if test="%{consultFlag!='vendor'}">

                                            <s:hidden   value="%{consultantVTO.consult_description}"   id="consult_description"  name="consult_description"/>
                                            <s:hidden  name="consult_jobTitle" value="%{consultantVTO.consult_jobTitle}" id="consult_jobTitle" />                                      
                                            <s:hidden  value="%{consultantVTO.consult_industry}" name="consult_industry" id="consult_industry"    headerKey="-1"  list="industry" />
                                            <s:hidden  value="%{consultantVTO.consult_organization}" name="consult_organization" id="consult_organization"   headerKey="-1"  list="organization" />
                                            <s:hidden  value="%{consultantVTO.consult_salary}" name="consult_salary" id="consult_salary" />
                                            <s:hidden  value="%{consultantVTO.consult_experience}" name="consult_experience" id="consult_experience"    headerKey="-1"  list="experience" />
                                            <s:hidden  value="%{consultantVTO.consult_workPhone}"  name="consult_workPhone" id="consult_workPhone" /> 
                                            <s:hidden  value="%{consultantVTO.consult_referredBy}" name="consult_referredBy" id="consult_referredBy" />
                                            <s:hidden  value="%{consultantVTO.consult_status}" name="consult_status" id="consult_status" headerKey="-1"  list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" />
                                            <s:hidden  value="%{consultantVTO.consult_wcountry}"  name="consult_wcountry" id="consult_wcountry"  headerKey="-1"  list="country" />
                                            <s:hidden  value="%{consultantVTO.consult_preferredCountry}"   name="consult_preferredCountry" id="consult_preferredCountry"  headerKey="-1"  list="country" onchange="ConsultantEditPreferredStateChange()"  />
                                            <s:hidden  value="%{consultantVTO.consult_preferredState}"  name="consult_preferredState" id="consult_preferredState" headerKey="-1"  list="preState" />

                                        </s:if>
                                        <br/>      
                                        <div class="row"></div>
                                        <div class="col-sm-6">

                                            <label  class="labelStylereq" style="margin-left:10px;">Skills:</label>


                                            <s:select cssClass="commentsStyle" name="skillCategoryValueList"  id="skillCategoryValue" list="skillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()" value="%{consultantVTO.skillSetList}" tabindex="5"/> 
                                            <s:hidden id="skillValues" name="skillValues" />
                                        </div>

                                        <%--  <div class="col-lg-12">
                                              <div class="form-group required">
                                                  <label  class="labelStylereq" style="margin-left:10px;">Skills:</label>
                                                  <s:textarea cssClass="titleStyle "   id="consult_skill" name="consult_skill" maxlength="100" cols="100" rows="1" value="%{consultantVTO.consult_skill}" tabindex="13" onkeyup="skillValidation()" onchange="workingCountryValidate()" />
                                              </div>
                                          </div>--%>
                                        <div class=" col-sm-12" >
                                            <div class="form-group">
                                                <label  class="labelStylereq">Comments:</label>
                                                <s:textarea cssClass="titleStyle " value="%{consultantVTO.consult_comments}"  id="consult_comments" placeholder="Comments" name="consult_comments" maxlength="500" tabindex="5"/>
                                            </div>
                                        </div>

                                        <s:if test="#session.typeOfUsr=='AC'">
                                            <%-- Save button is not seen by Customer  --%>
                                        </s:if>
                                        <s:else>
                                            <div class="col-lg-12 " >
                                                <div class="col-lg-10"></div>
                                                <div class="col-sm-4 pull-right ">
                                                    <s:submit type="button" cssStyle="margin:12px;" name="savetask" cssClass="cssbutton_save" onclick="return ConsultDetails_valid()"  value="" tabindex="5"><i class="fa fa-plus-square"></i>&nbsp;Save</s:submit>
                                                    </div>
                                                </div>
                                        </s:else>



                                    </form>
                                </div>
                                <div id="consultVisaAttachment_popup">
                                    <div id="visaAttachOverlay">

                                        <div style="background-color: #3BB9FF ">
                                            <table>
                                                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Upload Visa copy&nbsp;&nbsp; </font></h4></td>
                                                <span class=" pull-right"><h5><a href="" class="consultVisaAttachment_popup_close" onclick="visaAttachOverlayClose();"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                            </table>
                                        </div>
                                        <div>
                                            <br>

                                            <div>
                                                <div class="inner-addtaskdiv-elements">
                                                    <div id="message"></div>
                                                    <%-- <s:hidden name="downloadFlag" id="downloadFlag" value="%{downloadFlag}"/>
                                                     <s:hidden name="consultFlag" id="consultFlag" value="%{consultFlag}"/>--%>
                                                    <s:hidden id="consultantId" name="consultantId" value="%{consult_id}"/> 
                                                    <s:file name="file" id="file"/>
                                                </div>
                                                <input type="button" value="Upload" onclick="return teAttachemntUpload();" class="cssbutton pull-right" id="addButton"/> 
                                                <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>

                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div>

                            <%-- skill tab starts --%>
                            <div class="tab-pane fade" id="consult_Skillslide" >
                                <table id="consultskill_add" >
                                    <div id="add_consultskill" ><a href="" class="consultskilladd_popup_open btn add-recordBtn pull-right" onclick="removeDataAfterCloseOverlay()"  style="margin-right:0%">ADD NEW SKILL</a></div>
                                </table>   

                                <table id="consult_skilpagenav" class="CSSTable_task  responsive" border="5"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden" >
                                    <tr>

                                        <th>Skill Name</th>
                                        <th>Rate your Skill</th>
                                        <th>Comments</th>
                                    </tr>
                                </table> 
                                <div id="consult_pageNavPosition" align="right" style="margin-right: 0vw"></div>
                            </div>
                            <%-- skill tab ends --%>
                            <%-- security tab starts --%>
                            <div class="tab-pane fade textfieldLabel" id="consult_security-info">
                                <div class="container">
                                    <div class="col-sm-4" style="float: left">
                                        <span class="successInforesult"><securityinfo id="successInfo"></securityinfo></span>
                                        <div id="profileBox1"><center>
                                                <table>
                                                    <s:textfield cssClass="form-control" id="consult_pan" label="SSN/PAN" maxLength="10" required="true"   placeholder="ABCde1234F/123-12-1234" onkeyup="consult_panValidation()" />
                                                    <s:textfield cssClass="form-control" id="consult_panname" label="Name on PAN" maxLength="40" required="true"   placeholder="Ex.John" onkeyup="consult_nameValidation()"/>
                                                    <s:textfield cssClass="form-control" id="consult_bank" label="Bank Name" required="true"  maxLength="20" placeholder="Ex.SBI/kvb" onkeyup="consult_banknameValidation()"/>
                                                    <s:textfield cssClass="form-control" id="consult_banknum" label="Bank A/C No." required="true"  maxLength="20" placeholder="Ex.A1234d567891234567" onkeyup="consult_banAccknumValidation()"/>
                                                    <s:textfield cssClass="form-control" id="consult_hname" label="A/C H.Name" required="true"  maxLength="25" placeholder="Ex.John" onkeyup="consult_holdnameValidation()"/>
                                                </table></center></div>
                                    </div>
                                    <div class="col-sm-4" style="float: left">
                                        <div id="profileBox1"><center>
                                                <table>
                                                    <s:textfield cssClass="form-control" id="consult_ifsc" label="IFSC Code" required="true"  maxLength="11" placeholder="Ex.ABcd0123456" onkeyup="consult_ifscValidation()"/>   
                                                    <s:textfield cssClass="form-control" id="consult_pf" label="PF No." required="true"  maxLength="16" placeholder="Ex.Ab-12345-1234567" onkeyup="consult_pfValidation()"/>
                                                    <s:textfield cssClass="form-control" id="consult_uan" label="UAN No." required="true"  maxLength="25" placeholder="Ex.123456" onkeyup="consult_uanValidation()"/>
                                                    <s:textfield cssClass="form-control" id="consult_pass" label="Passport NO." required="true"  maxLength="15" placeholder="Ex.A12a3455" onkeyup="consult_passportnumValidation()"/>
                                                    <s:textfield cssClass="form-control dateImage" id="consult_passport" label="Passport Exp" required="true"  placeholder="" value="%{docdatepicker}" tabindex="0"  onkeypress="return consult_passportDateValidation();"/>
                                                    <s:submit  id="updatebutton" value="Save" onclick="addSecurityInfo()"/>
                                                </table></center></div>
                                    </div>

                                </div>
                            </div>
                            <%-- security tab ends --%>
                            <%-- activity tab starts --%>
                            <div class="tab-pane fade textfieldLabel" id="consult_activities">
                                <table id="consult-activities_add" >
                                    <div id="add_consult-activities" ><a href="" class="Activity_popup_open btn add-recordBtn pull-right" onclick="removeDataAfterActivityCloseOverlay()"  style="margin-right:0%">ADD NEW ACTIVITY</a></div>
                                </table>

                                <div id="Activity_popup">
                                    <div id="ActivityBoxOverlay">

                                        <div style="background-color: #3BB9FF ">
                                            <table>
                                                <div class="" id="addtsprofileBox">
                                                    <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Activity&nbsp;&nbsp; </font></h4></td>
                                                    <span class=" pull-right"><h5><a href="" class="Activity_popup_close" onclick="removeActivityResultMessageAll()"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                            </table>
                                        </div>
                                        <span><errorActivityUpdate></errorActivityUpdate></span>
                                        <form action="#" id="activityFrm"  theme="simple" name="activityAddForm">
                                            <s:hidden id="consultId" value="%{consult_id}" name="consult_id"/>

                                            <div>

                                                <div class="col-sm-12">
                                                    <%--span><errorActAdd></errorActAdd></span--%>
                                                    <span id="spanUpdatep" class="pull-right"></span>
                                                    <div>
                                                        <div class="inner-addtaskdiv-elements">
                                                            <div class="col-sm-4 required">
                                                                <label class="labelStyle">Name</label>:<s:textfield  id="add_Activity_name" name="activityName" cssClass="form-control" theme="simple"  placeholder="Activity Name" onfocus="removeActivityResultMessage()" />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="labelStyle">Status</label>:<s:select  id="add_Activity_status" value=""  name="activityStatus" cssClass="SelectBoxStyles form-control" headerKey="Created" headerValue="Created" theme="simple" list="{'Created'}" onfocus="removeActivityResultMessage()" disabled="true"/>
                                                            </div>
                                                            <div class="col-sm-4 required">
                                                                <label class="labelStyle">Activity Type</label>:<s:select  id="add_activityType" value="-1"   name="activityType" cssClass="SelectBoxStyles form-control" headerKey="" headerValue="Select Type" theme="simple" list="{'Call - Inbound','Call - Outbound','Email - Inbound','Email - Outbound','Notes'}" onfocus="removeActivityResultMessage()" />
                                                                <%--label class="labelStyle">Priority </label>:<s:select  id="priority"   name="activityPriority" cssClass="selectstyle" headerKey="-1" headerValue="Select Priority" theme="simple" list="{'Bug','Issue','Enhancement','Defect'}" /--%>
                                                            </div>
                                                            <div class="inner-addtaskdiv-elements">
                                                                <label class="labelStyle">Description</label>:<s:textarea name="activityDesc" id="add_Activity_desc" placeholder="Enter Activity Description Here" cssClass="areacss" onkeyup="checkCharacters(this)"/>
                                                            </div>
                                                            <div class="inner-addtaskdiv-elements">   
                                                                <label class="labelStyle">Comments</label>:<s:textarea name="activityComments" id="add_Activity_comments" placeholder="Enter Activity Comments Here" cssClass="areacss" onkeyup="checkCharacters(this)"/>
                                                            </div>
                                                            <div id="charNum"></div>
                                                        </div>
                                                        <div  class="inner-addtaskbtndiv-elements">
                                                            <s:reset cssClass="cssbutton " value="Clear" theme="simple" />
                                                            <s:submit cssClass="cssbutton task_popup_close" value="AddActivity" theme="simple" onclick="return addConsultActivity()" />
                                                        </div>
                                                        </form>


                                                    </div>
                                                </div>        
                                        </form>
                                    </div>

                                </div>


                            </div>


                            <s:form>
                                <table id="consult_activity" class="CSSTable_task  responsive" border="5" cell-spacing="1" style="overflow-x:auto;overflow-y:hidden" >
                                    <tr>
                                        <th>Activity Id</th>
                                        <th>Activity Name</th>
                                        <th>Activity Type</th>
                                        <th>Comments</th>
                                        <th>Status</th>
                                    </tr>
                                    <%
                                        int c = 0;
                                        if (session.getAttribute("consultantActivityDetails") != null) {

                                            List l = (List) session.getAttribute("consultantActivityDetails");

                                            Iterator it = l.iterator();
                                            while (it.hasNext()) {
                                                if (c == 0) {
                                                    c = 1;

                                                }
                                                ConsultantVTO cvto = (ConsultantVTO) it.next();
                                                //int leave_id = usa.getLeaveid();
                                                //String leave_start_date = usa.getLeavestartdate();
                                                //int consult_id = cvto.getConsult_id();
                                                int activity_id = cvto.getConsult_activityId();
                                                String activityName = cvto.getConsult_activityName();
                                                String activityType = cvto.getConsult_activityType();
                                                String activityComnts = cvto.getConsult_activityComments();
                                                String activityStatus = cvto.getConsult_activityStatus();
                                                //String approvedBy = usa.getApprovedBy();action - getConsultActivitybyActivity
%>
                                    <tr>

                                        <%--s:url var="myUrl" action="getConsultActivitybyActivity">
                                            <s:param name="activityId"><%=activity_id%></s:param>
                                        </s:url--%>
                                        <td>
                                            <%--   <s:hidden name="activityId"  id="ActivityId" ><%= activity_id%></s:hidden> 
                                               <act id="testconsultAct"><%= activity_id%></act> --%>
                                            <%-- <s:a cssClass="ActivityEdit_popup_open" onclick="populateActivityOverlay('<%=activity_id%>')" id="ActivityId"><%=activity_id%></s:a> --%>
                                            <a onclick="populateActivityOverlay('<%=activity_id%>')" href="#" class="ActivityEdit_popup_open">
                                                <%=activity_id%>
                                            </a>
                                        </td>
                                        <td><%= activityName%></td> 
                                        <td><%= activityType%></td>
                                        <td><%= activityComnts%></td>
                                        <td><%= activityStatus%></td>

                                    </tr> 
                                    <%
                                        }
                                    } else {
                                    %>
                                    <tr>
                                        <td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                    </tr> 
                                    <%      }
                                    %>
                                    </tbody>
                                </table>

                                <%
                                    if (c == 1) {
                                %>
                                <div align="right" id="pageNavPosition" style="margin-right: 0vw;"></div>
                                <%
                                        c = 0;

                                    }
                                    if (session.getAttribute("consultantActivityDetails") != null) {
                                        session.removeAttribute("consultantActivityDetails");
                                    }
                                %>
                            </s:form>
                            <div id="pageNavPosition" align="right" style="margin-right: 0vw"></div>
                            <script type="text/javascript">
                                var pager = new Pager('consult_activity', 4); 
                                pager.init(); 
                                pager.showPageNav('pager', 'pageNavPosition'); 
                                pager.showPage(1);
                            </script>
                            </table> 
                            <%--activity edit Start --%>
                            <div id="ActivityEdit_popup">
                                <div id="ActivityEditBoxOverlay">

                                    <div style="background-color: #3BB9FF ">
                                        <table>
                                            <div class="" id="addtsprofileBox">
                                                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Edit Activity&nbsp;&nbsp; </font></h4></td>
                                                <span class=" pull-right"><h5><a href="" class="ActivityEdit_popup_close" onclick="removeActivityResultMessageAll()"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                        </table>
                                    </div>


                                    <form action="#"  theme="simple" name="activityForm">
                                        <%--s:hidden value="%{activity_id}" name="activityId">editActivity</s:hidden--%>
                                        <span><errorActivityUpdate></errorActivityUpdate></span>
                                        <s:hidden  id="Activity_Id" value="%{activityId}" name="activityId"></s:hidden>
                                        <s:hidden id="consultId" value="%{consult_id}" name="consult_id"/>
                                        <div>

                                            <div class="col-sm-12">
                                                <span><errorEduAdd></errorEduAdd></span>

                                                <div>
                                                    <div class="inner-addtaskdiv-elements">
                                                        <div class="col-sm-4 required">
                                                            <label class="labelStyle">Name</label>:<s:textfield  id="Activity_name" name="activityName"  value="%{activityName}" cssClass="form-control" theme="simple"  placeholder="Activity Name" onfocus="removeActivityResultMessage()" />
                                                        </div>
                                                        <div class="col-sm-4"><label class="labelStyle">Status</label>:<s:select  id="Activity_status" value="%{activityStatus}"  name="activityStatus" cssClass="SelectBoxStyles form-control" headerKey="" headerValue="Select Status" theme="simple" list="{'Not Started','Completed','Inprogress','Cancelled','Created','On Hold'}" />
                                                        </div>
                                                        <div class="col-sm-4 required">
                                                            <label class="labelStyle">Activity Type</label>:<s:select  id="activityType" value="%{activityType}"   name="activityType" cssClass="SelectBoxStyles form-control" headerKey="" headerValue="Select Type" theme="simple" list="{'Call - Inbound','Call - Outbound','Email - Inbound','Email - Outbound','Notes'}"  />
                                                            <%--label class="labelStyle">Priority </label>:<s:select  id="priority"   name="activityPriority" cssClass="selectstyle" headerKey="-1" headerValue="Select Priority" theme="simple" list="{'Bug','Issue','Enhancement','Defect'}" /--%>
                                                        </div>

                                                        <div class="inner-addtaskdiv-elements">
                                                            <label class="labelStyle">Description</label>:<s:textarea name="activityDesc" id="Activity_desc"  value="%{activityDesc}" placeholder="Enter Activity Description Here" cssClass="areacss" onkeyup="checkCharacters(this)"/>
                                                        </div>
                                                        <div class="inner-addtaskdiv-elements">
                                                            <label class="labelStyle">Comments</label>:<s:textarea name="activityComments" id="Activity_comments" value="%{activityComments}" placeholder="Enter Activity Comments Here" cssClass="areacss" onkeyup="checkCharacters(this)"/>
                                                        </div>
                                                        <div id="charNum"></div>
                                                    </div>
                                                    <div  class="inner-addtaskbtndiv-elements">
                                                        <s:reset cssClass="cssbutton " value="Clear" theme="simple" />
                                                        <s:submit cssClass="cssbutton task_popup_close" value="Update" theme="simple" onclick="return editConsultActivityDetails()" />
                                                    </div>
                                                    </form>


                                                </div>
                                            </div>        
                                    </form>
                                </div>

                            </div>


                        </div>
                        <%--activity edit end --%>          
                    </div>

                    <%-- activity tab ends --%>
                    <%-- attachment tab starts --%>
                    <div class="tab-pane fade " id="consult_attach" onclick="getConsultantAttachments.action"   >

                        <div id="consultAttachment_popup">
                            <div id="taskAttachOverlay">

                                <div style="background-color: #3BB9FF ">
                                    <table>
                                        <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Resume&nbsp;&nbsp; </font></h4></td>
                                        <span class=" pull-right"><h5><a href="" class="consultAttachment_popup_close" onclick="attachPopJs();showAttachmentDetails('<%= request.getParameter("consult_id")%>');"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                                    </table>
                                </div>
                                <div>
                                    <br>
                                    <s:form action="addConsultAttachment" id="consultantAttachmentId" theme="simple"   enctype="multipart/form-data" >
                                        <div>
                                            <div class="inner-addtaskdiv-elements">
                                                <div id="message"></div>
                                                <s:hidden name="downloadFlag" id="downloadFlag" value="%{downloadFlag}"/>
                                                <s:hidden name="consultFlag" id="consultFlag" value="%{consultFlag}"/>
                                                <s:hidden id="consult_id" name="consult_id" value="%{consult_id}"/>
                                                <s:file name="consultAttachment" id="consultAttachment"/>
                                            </div>
                                            <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                            <center><s:submit cssClass="cssbutton" value="ADD" theme="simple" onclick="return editResumeValidation();" /></center><br>
                                        </div>

                                    </div>
                                </s:form>
                            </div>
                        </div>

                        <a href="" class="consultAttachment_popup_open" onclick="return attachPopJs();"><button id="cssbutton" class="pull-right">Add</button></a> <br/> 
                        <br/><br/>
                        <div class="col-lg-12">
                            <s:if test='downloadFlag=="noResume"'>
                                <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                </s:if>
                                <s:if test='downloadFlag=="noFile"'>
                                <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                </s:if> 
                            <table id="consult-attach_pagenav" class="CSSTable_task  responsive"  >
                                <tr>
                                    <th>Attachment</th>

                                    <th>Location</th>
                                    <th>Date Of Attachment</th>
                                    <th>Download Link</th>
                                    <th>Status</th>
                                </tr>
                            </table>
                            <br>
                            <label> Display <select id="con_paginationOption" class="disPlayRecordsCss" onchange="con_pagerOption()" style="width: auto">
                                    <option>10</option>
                                    <option>15</option>
                                    <option>25</option>
                                    <option>50</option>
                                </select>
                                Consultants per page
                            </label>
                            <br>
                            <div id="cattach_pageNavPosition" align="right" style="margin-right:0vw;display: none"></div>
                        </div>

                    </div>
                    <%-- attachment tab ends --%>
                    <%-- personal tab starts --%>

                    <div class="tab-pane fade" id="consult_personal" >
                        <table id="personalpagenav" class="CSSTable_task  responsive" border="5"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden" >
                            <tr>
                                <th>Experience In</th>
                                <th>Years Of Experience</th>
                                <th>Organisation Worked </th>
                                <th>Project Information</th>
                            </tr>
                        </table> 
                        <div id="pageNavPosition" align="right" style="margin-right: 0vw"></div>
                    </div>
                    <%-- personal tab ends --%>
                    <%-- notes tab starts --%>
                    <div class="tab-pane fade textfieldLabel" id="consult_notes">
                        <table id="notes_add" >
                            <div id="add_notes" ><a href="" class="notesadd_popup_open btn add-recordBtn pull-right" onclick="removeDataAfterCloseOverlay()"  style="margin-right:0%">ADD NEW NOTES</a></div>
                        </table>
                        <table id="notespagenav" class="CSSTable_task  responsive" border="5"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden" >
                            <tr>

                                <th>Notes</th>
                                <th>Comments</th>
                                <th>Created Date</th>
                                <th>Created By</th>

                            </tr>
                        </table> 
                        <div id="pageNavPosition" align="right" style="margin-right: 0vw"></div>

                    </div>
                    <%-- notes tab ends --%>
                    <%-- tech review tab starts --%>
                    <div class="tab-pane fade textfieldLabel" id="consult_tech-review">
                        <table id="tech-review_add" >
                            <div id="add_tech-review" ><a href="" class="tech-reviewadd_popup_open btn add-recordBtn pull-right" onclick="removeDataAfterCloseOverlay()"  style="margin-right:0%">ADD NEW REVIEW</a></div>
                        </table>
                        <table id="tech-reviewpagenav" class="CSSTable_task  responsive" border="5"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden" >
                            <tr>
                                <th>Forwarded To</th>
                                <th>Forwarded By</th>
                                <th>Forwarded Name</th>
                                <th>Last Modified Date</th>
                                <th>Comments</th>
                                <th>Date Forwarded</th>
                                <th>Alert</th>
                                <th>Rating</th>

                            </tr>
                        </table> 
                        <div id="pageNavPosition" align="right" style="margin-right: 0vw"></div>
                    </div>
                    <%-- tech review tab ends --%>                                   
                </div>
                <%-- tab content ends --%>
            </div>
        </div>     
    </div>
</div>    
</div>        
</section><!--/form-->
<footer id="footer"><!--Footer-->
    <div class="footer-bottom" id="footer_bottom">
        <div class="container">
            <s:include value="/includes/template/footer.jsp"/>
        </div>
    </div>
</footer><!--/Footer-->
<script type="text/javascript">
           
    var cpager = new Pager('consult-attach_pagenav', 10);
    //  alert("-------->in jsp");
    cpager.init();     
    cpager.showPageNav('cpager', 'cattach_pageNavPosition'); 
    cpager.showPage(1);
           
</script>
<script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
<script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
<script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script> 
<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script>
    $(document).ready(function() {
        $('#skillCategoryValue').selectivity({
                    
            multiple: true,
            placeholder: 'Type to search skills'
        });
    });
            
</script>
<script type="text/javascript">
      
    $("#consult_mobileNo").mask("(999)-999-9999");
    $("#consult_workPhone").mask("(999)-999-9999");
    $("#consult_Phone").mask("(999)-999-9999");
    $("#consult_CPhone").mask("(999)-999-9999");
    $("#consult_homePhone").mask("(999)-999-9999");
              
    
     
</script>
<script type="text/javascript">
    var flag=document.getElementById("downloadFlag").value;
    //alert(flag);
    if(flag=="noResume"||flag=="noFile")
    {
        //alert("in if");
        var consult_id=document.getElementById('consult_id').value;
        //alert(consult_id);
        showAttachmentDetails(consult_id);
        document.getElementById('attachLi').className='active active_details';
        document.getElementById('consult_attach').className='tab-pane fade in active  ';
        document.getElementById('Consultant').className='tab-pane fade ';
        cheadingMessage(c_attach);
        //window.location = '../consultant/getConsultantAttachments.action?consult_id='+consult_id;
        // javascript: ajaxReplaceDiv('/getConsultantAttachments','#consult_attach','consult_id='+consult_id);
       
        
    }
    else
    {
        document.getElementById('consultantLi').className='active';
         
    }
    
    function handleSelect(a)    
    {
            
        if(a.value=='Yes')
        {
            //document.getElementById("consult_pcountry").disabled = false;
            $("#consult_preferredCountry").show();
            $(".pref_country").show();
            $("#consult_preferredCountry").val('-1');    
        }
        else
        {
            $("#consult_preferredCountry").hide();
            $(".pref_country").hide();
            $("#consult_preferredState").hide();
            $(".pref_state").hide();
            $("#PrefstateValues").val('');
            /* document.getElementById("consult_pcountry").disabled = true;
                 document.getElementById('consult_pcountry').value=-1;
                 document.getElementById("consult_preferredState").disabled = true;
                 document.getElementById('consult_preferredState').value=-1;*/
        }
    }
    // alert($("#consult_relocation").val());
    if($("#consult_relocation").val()=='Yes')
    {
        $("#consult_pcountry").show();
        $(".pref_country").show();
    }
    else
    {
        $("#consult_preferredCountry").hide();
        $(".pref_country").hide();
        $("#consult_preferredState").hide();
        $(".pref_state").hide();
        $("#PrefstateValues").val('');
        /* document.getElementById("consult_pcountry").disabled = true;
                 document.getElementById('consult_pcountry').value=-1;
                 document.getElementById("consult_preferredState").disabled = true;
                 document.getElementById('consult_preferredState').value=-1;*/
    }
    $('#consult_preferredState').selectivity({
            
        multiple: true,
        placeholder: 'Type to search Categories'
                   
    });
</script>





<script type="text/javascript">
    var recordPage=10;
    function con_pagerOption(){

        var paginationSize = document.getElementById("con_paginationOption").value;
        if(isNaN(paginationSize))
        {
                       
        }
        recordPage=paginationSize;
        // alert(recordPage)
        $('#consult-attach_pagenav').tablePaginate({navigateType:'navigator'},recordPage);

    };
    $('#consult-attach_pagenav').tablePaginate({navigateType:'navigator'},recordPage);
</script>

<script>
    setTimeout(function(){              
        $('#resume').remove();
    },3000);
</script>

<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/jQueryAjaxFileUpload.js"/>"></script>
</body>
</html>



