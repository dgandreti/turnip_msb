<%-- 
   Document   : Add Account contact
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
        <title>ServicesBay ::  Add Contact Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/media_queries.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>
        <script>
            $(document).ready(function() {
                $('#skillListValue').selectivity({
                    multiple: true,
                    placeholder: 'Type to Search Skills'
                });
            });

        </script>
        <style type="text/css">
            #placement-examples .east { margin-left: 450px; }
        </style>
        <script type="text/javascript">
            $(function() {
                $('.east').powerTip({placement: 'e'});

            });
        </script>
    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="ConPermanentStateChange();
            jumper();">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div>

            </header>

            <div id="main">

                <section id="generalForm"><!--form-->
                    <div class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/> 
                            <!-- content start -->
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="col-lg-14 ">
                                        <div class="" id="selectivityProfileBox" style="float: left; margin-top: 5px">


                                            <!-- content start -->
                                            <br>
                                            <% String accFlag = "accDetails";%> 
                                            <div class="col-lg-12"  style=" margin-top:-12px; margin-bottom: 20px">
                                                <label >Account Name :</label>                                       
                                                <s:url var="myUrl" action="viewAccount.action">
                                                    <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                    <s:param name="accFlag"><%= accFlag%></s:param>
                                                </s:url>
                                                <s:a href='%{#myUrl}' id="accountName"><s:property value="%{accountName}"/></s:a> 

                                                    <div class="backgroundcolor" >
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">
                                                            <% String flag = "conSearch";
                                                            %>
                                                            <font color="#ffffff"> Add Contact</font>
                                                            <s:url var="myUrl" action="../acc/viewAccount.action">
                                                                <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                                <s:param name="accFlag"><%=flag%></s:param>
                                                            </s:url>
                                                            <span class="pull-right"><s:a href='%{#myUrl}' id="backToList"><i class="fa fa-undo"></i></s:a></span>

                                                            </h4>
                                                        </div>

                                                    </div>
                                                    <span id="actionMessage"></span>
                                                    <span id="addContactError"> </span> 
                                                    &nbsp;&nbsp;<span id="InsertContactInfo"></span>

                                                    <div>
                                                    <s:form name="contactform" action="addContact" cssClass="form-horizontal" theme="simple"  enctype="multipart/form-data" onsubmit="return contactInfoValidation()">
                                                        <div>
                                                            <s:hidden name="AccountSearchOrgId" id="AccountSearchOrgId" value="%{accountSearchID}"/>
                                                            <s:hidden name="accountType" id="accountType" value="%{accountType}"/>
                                                            <s:hidden id="email_ext" name="email_ext" value="%{email_ext}" />


                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4 required">
                                                                        <label class="addAcclabelStyle">First Name</label><s:textfield  name="ContactFname"  id="ContactFname" maxLength="28" cssClass="form-control" placeholder="First Name"  onkeyup="contactFirstNameValidation()"/>
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Middle&nbsp;Name</label><s:textfield name="ContactMname"  id="ContactMname" maxLength="28"  cssClass="form-control" placeholder="Middle Name" pattern='[A-Za-z\\s]*' onkeypress="return middlename(event);" />
                                                                        <span id="mnameError" class=""></span> 
                                                                    </div>
                                                                    <div class="col-sm-4 required">
                                                                        <label class="addAcclabelStyle">Last Name</label><s:textfield name="ContactLname"  id="ContactLname" maxLength="28"  cssClass="form-control" placeholder="Last Name"  onkeyup="contactLastNameValidation()"/>
                                                                    </div>


                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4 required">
                                                                        <label class="addAcclabelStyle">Email </label>
                                                                        <s:textfield type="text" maxlength="25" n="3" autocomplete="off" cssClass="form-control "
                                                                                     name="ContactEmail" id="ContactEmail" value=""  onchange="EmailValidation()" style="padding-right: 150px;"  spellcheck="false" >
                                                                            <span class="urlDomain">@<s:property value="%{email_ext}" /></span>
                                                                        </s:textfield>
                                                                        <%--label class="addAcclabelStyle"><span style="color:red;">*</span>Email</label><s:textfield cssClass="form-control" placeholder="email" name="ContactEmail" id="ContactEmail" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Must be valid email')"   onchange="try{setCustomValidity('')}catch(e){}" required="true"  onkeyup="EmailValidation()" --%>

                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Alternate Email</label><s:textfield cssClass="form-control" placeholder="Alternate email" name="ContactAEmail" id="ContactAEmail" maxLength="58"   oninvalid="setCustomValidity('Must be valid email')"   onfocus="removeErrMsg();" onblur="alternateMailValidation()" onchange="try{setCustomValidity('')}catch(e){}"/>

                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Gender</label>
                                                                        <s:select cssClass="SelectBoxStyles form-control " id="gender" name="gender" label="gender" list="#@java.util.LinkedHashMap@{'M':'Male','F':'Female'}" />
                                                                    </div>

                                                                </div>
                                                            </div>

                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4 required">
                                                                        <label class="addAcclabelStyle">Office&nbsp;Phone</label><s:textfield id="Officephone" cssClass="form-control" name="Officephone"  type="text" placeholder="Phone #" />
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Mobile Phone</label><s:textfield cssClass="form-control" label="Phone" id="conPhone" name="conPhone" placeholder="Mobile Phone #"  oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pPhoneValidation()" />
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Home Phone</label><s:textfield cssClass="form-control" label="Phone" id="homePhone" name="conCPhone" placeholder="Office Phone #"  oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pPhoneValidation()" />
                                                                    </div>

                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Work&nbsp;Location</label><s:select cssClass="form-control SelectBoxStyles" name="workingLocation" id="workingLocation" headerKey="-1" headerValue="Select Work Location" list="workLocations"/>
                                                                    </div>
                                                                    <div class="col-sm-4 required">
                                                                        <label class="addAcclabelStyle">Title</label><s:textfield cssClass="form-control " name="contactTitle" id="contactTitle" placeholder="Title"  maxLength="30"  onkeyup="titleValidation();"/>
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Industry</label><s:select cssClass="form-control SelectBoxStyles" name="contactIndustry" id="contactIndustry" headerKey="-1" headerValue="Select Industry" list="industryMap" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Experience</label><s:select cssClass="form-control SelectBoxStyles" name="contactExperience" id="contactExperience" headerKey="-1" headerValue="Select Experience" list="experience" />
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">SSN&nbsp;Number</label><s:textfield cssClass="form-control " name="contactSsnNo" id="contactSsnNo" placeholder="SSN Number" maxLength="20"/>
                                                                    </div>

                                                                </div>
                                                            </div>  
                                                            <div class="row"></div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="col-sm-12">
                                                                    <label  class="labelStylereq" style="margin:-0px;">Skills</label>


                                                                    <s:select cssClass="" name="skillCategoryValueList"  id="skillListValue" list="skillMap" multiple="true" onfocus="clearErrosMsgForGrouping()" /> 
                                                                    <s:hidden id="contactSkillValues" name="contactSkillValues" />
                                                                </div>
                                                            </div>       
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="col-sm-12 ">
                                                                    <label  class="task-label" style="max-height:10px;">Education</label>
                                                                    <s:textarea cssClass="titleStyle"   id="contactEducation"  name="contactEducation" placeholder="Education" maxlength="500" cols="100" rows="2" onkeyup="CheckRemainingAttachCharacters(this)" tabindex="7"/>
                                                                    <div id="charRemainAttach"></div>
                                                                </div>
                                                            </div>
                                                            <!-- Add By Aklakh, start -->
                                                            <div class="col-sm-12">
                                                                <h4><b>Contact Address</b></h4> 
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Address</label> <s:textfield   cssClass="form-control" id="conAddress" maxLength="98" name="conAddress"  placeholder="Address"  tabindex="8" />  <!-- onkeyup="paddresValidation()" -->
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Address2</label>     <s:textfield cssClass="form-control"  id="conAddress2" maxLength="98" name="conAddress2" placeholder="Address2" tabindex="8"/>
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">City</label>     <s:textfield cssClass="form-control" id="conCity" maxLength="18" name="conCity" placeholder="City" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z\s]{3,}" tabindex="8" /> 
                                                                    </div>

                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Country</label>    <s:select cssClass="form-control SelectBoxStyles" name="conCountry" id="conCountry"  value="3" list="countryNames" onchange="ConPermanentStateChange()" tabindex="8"/>
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">State</label>   <s:select cssClass="form-control SelectBoxStyles" name="conState" id="conState" headerKey="-1" headerValue="Select  state" list="{}" listValue="getTranslation(value)"  tabindex="8" />
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Zip</label>    <s:textfield cssClass="form-control"  id="conZip" maxLength="10" name="conZip" minLength="4"  placeholder="Zip" tabindex="8" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row"></div>

                                                            <div class="inner-reqdiv-elements">
                                                                <div class=" ">
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Image</label><div><s:file cssClass="form-control" name="taskAttachment" id="taskAttachment" onchange="return fileFormatValidation();" tabindex="8"/></div>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                            <div class="row"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-body" id="task-panel">
                                                <div class="row">
                                                    <!-- Contact Information , start  -->
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-sm-2 pull-right">
                                            <s:submit id="saveContact" type="button" cssStyle="margin:5px 0px;"  value="Save" cssClass="add_searchButton fa fa-floppy-o form-control" theme="simple" tabindex="8"></s:submit>
                                            </div>
                                            <div class="col-sm-2 pull-right">
                                                <input  type="reset" style="margin:5px 0px;" class="add_searchButton fa fa form-control" id="button_manage"  value="&#xf12d; Clear" tabindex="8" onclick="clearContatForm();"/>&nbsp;
                                            </div>
                                            <br/>
                                        <%-- add token to JSP to be used by Token interceptor --%>
                                        <s:token />
                                    </s:form>
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
        <!-- content end -->
    </section><!--/form-->
</div>
<footer id="footer"><!--Footer-->
    <div class="footer-bottom" id="footer_bottom">
        <div class="container">
            <s:include value="/includes/template/footer.jsp"/>
        </div>
    </div>
</footer><!--/Footer-->
<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
</body>
</html>
