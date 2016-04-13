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
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/media_queries.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>


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

                                            <% String accFlag = "accDetails";%> 
                                            <% String flag = "conSearch";
                                            %>
                                            <s:if test="flag=='customerlogin'">

                                            </s:if>

                                            <s:elseif test="flag=='vendorlogin'">

                                            </s:elseif>

                                            <s:else>
                                                <br><div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px">

                                                    <s:url var="myUrl" action="viewAccount.action">
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                        <s:param name="accFlag"><%= accFlag%></s:param>
                                                    </s:url>

                                                    <s:a href='%{#myUrl}' cssClass="breadcrum"><s:property value="%{account_name}"/></s:a> 
                                                    <s:url var="myUrl" action="../acc/viewAccount.action">
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                        <s:param name="accFlag"><%=flag%></s:param>
                                                    </s:url>
                                                    <s:a href='%{#myUrl}' cssClass="breadcrum">>>&nbsp;Contact Search</s:a>
                                                    <span class="breadcrumActive">&nbsp;>> <s:property value="%{accountContactVTO.firstName}"/>.<s:property value="%{accountContactVTO.lastName}"/></span>
                                                </div>  
                                                <br>
                                            </s:else>

                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">

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
                                                            <span class="pull-right"><s:a href='%{#myUrl}' id="contactBackButton"><i class="fa fa-undo"></i></s:a></span>
                                                        </s:if> 

                                                        <s:elseif test="flag=='customerlogin' || flag=='vendorlogin'">

                                                            <%-- Back Button is not required here --%>
                                                        </s:elseif>
                                                        <s:else>
                                                            <s:url var="myUrl" action="../acc/viewAccount.action">
                                                                <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                                <s:param name="accFlag"><%=flag%></s:param>
                                                            </s:url>
                                                            <span class="pull-right"><s:a href='%{#myUrl}' id="contactBackButton"><i class="fa fa-undo"></i></s:a></span>
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
                                                            <div class=" ">
                                                                <div class="col-sm-2" >
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


                                                                <div class="col-sm-3 required">
                                                                    <label class="contactLabelStyle">First Name</label><s:textfield  name="ContactFname" value="%{accountContactVTO.firstName}"  id="ContactFname" required="true" maxLength="28" cssClass="form-control" placeholder="first Name" onkeyup="contactFirstNameValidation()" tabindex="1"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="contactLabelStyle">Middle&nbsp;Name</label><s:textfield name="ContactMname" value="%{accountContactVTO.middleName}" id="ContactMname"  cssClass="form-control" placeholder="middle Name" maxLength="28"  onfocus="removeResultMessage()" pattern='[A-Za-z\\s]*' onkeypress="return middlename(event);" tabindex="2"/>   
                                                                    
                                                                </div>
                                                                <div class="col-sm-3 required">
                                                                    <label class="contactLabelStyle">Last Name</label><s:textfield name="ContactLname" value="%{accountContactVTO.lastName}" id="ContactLname" maxLength="28"  cssClass="form-control" placeholder="last Name"  onkeyup="contactLastNameValidation()" tabindex="3"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="contactLabelStyle">Email</label><s:textfield cssClass="form-control" disabled="true" value="%{accountContactVTO.email}" name="ContactEmail" id="ContactEmail" tabindex="4"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="contactLabelStyle">Alternate&nbsp;Email</label><s:textfield cssClass="form-control" value="%{accountContactVTO.email2}" name="ContactEmail2" id="ContactEmail2" maxLength="58" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Must be valid email')" placeholder="Alternate Email" onblur="alternateMailValidation()" onkeyup="removeActionMessage()" tabindex="5"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="addAcclabelStyle">Gender</label>
                                                                    <s:select cssClass="SelectBoxStyles form-control " id="gender" name="gender" list="#@java.util.LinkedHashMap@{'M':'Male','F':'Female'}" value="%{accountContactVTO.gender}" tabindex="6"/>
                                                                </div>
                                                                <div class="col-sm-3 required">
                                                                    <label class="contactLabelStyle">Office&nbsp;Phone</label> <s:textfield id="Officephone" value="%{accountContactVTO.officePhone}" cssClass="form-control" required="true" name="Officephone"  type="text" placeholder="Phone #"  tabindex="7"/>  
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="contactLabelStyle">Mobile&nbsp;Number</label> <s:textfield id="moblieNumber" value="%{accountContactVTO.moblieNumber}" cssClass="form-control" name="moblieNumber"  type="text" placeholder="Mobile #" tabindex="8"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="contactLabelStyle">Home&nbsp;Phone</label> <s:textfield id="homePhone" value="%{accountContactVTO.homePhone}" cssClass="form-control" name="homePhone"  type="text" placeholder="Home Phone #" tabindex="9"/>
                                                                </div>
                                                                <div class="col-sm-2">
                                                                    <%-- for space --%>
                                                                </div>
                                                                <div class="col-sm-3  required">
                                                                    <label class="addAcclabelStyle">Title</label><s:textfield cssClass="form-control " name="contactTitle" id="contactTitle" placeholder="Title"  required="true" maxLength="30"  tabindex="10" value="%{accountContactVTO.contactTitle}"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="addAcclabelStyle">Industry</label><s:select cssClass="form-control SelectBoxStyles" name="contactIndustry" id="contactIndustry" headerKey="-1" headerValue="Select Industry" list="industryMap"  tabindex="11" value="%{accountContactVTO.contactIndustry}"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="addAcclabelStyle">Experience</label><s:select cssClass="form-control SelectBoxStyles" name="contactExperience" id="contactExperience" headerKey="-1" headerValue="Select Experience" list="experience"  tabindex="12" value="%{accountContactVTO.contactExperience}"/>
                                                                </div>
                                                                <div class="col-sm-3 col-md-offset-2">
                                                                    <label class="addAcclabelStyle">Work&nbsp;Location</label><s:select cssClass="form-control SelectBoxStyles" name="workingLocation" id="workingLocation" headerKey="-1" headerValue="Select Work Location" list="workLocations"   value="%{accountContactVTO.workingLocation}" tabindex="13" />
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="addAcclabelStyle">SSN&nbsp;Number</label><s:textfield cssClass="form-control " name="contactSsnNo" id="contactSsnNo" placeholder="SSN Number" maxLength="20" tabindex="14" value="%{accountContactVTO.contactSsnNo}"/>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <s:if test="'customerlogin'!=flag && 'vendorlogin'!=flag">
                                                                        <label class="contactLabelStyle">Role</label><s:select name="primaryRole" list="orgRoles"  theme="simple" cssClass="SelectBoxStyles form-control" value="%{primaryRole}"  tabindex="15"/> 
                                                                    </s:if>
                                                                </div>


                                                                <div class="row"></div>
                                                                <div class="col-sm-6 col-md-offset-2 ">
                                                                    <label  class="labelStylereq" style="margin:-0px;">Skills</label>


                                                                    <s:select cssClass="" name="skillCategoryValueList"  id="skillListValue" list="skillMap" multiple="true" onfocus="clearErrosMsgForGrouping()"  value="%{accountContactVTO.skillListSet}" tabindex="16"/> 
                                                                    <s:hidden id="contactSkillValues" name="contactSkillValues" />
                                                                </div>
                                                                <s:if test="'customerlogin'!=flag && 'vendorlogin'!=flag">
                                                                    <div class="col-sm-3 ">
                                                                        <label class="contactLabelStyle">Status</label>

                                                                        <s:select id="status" value="%{accountContactVTO.status}" name="status"  cssClass="SelectBoxStyles form-control" accesskey="" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','Registered':'Registered'}" tabindex="18"/>

                                                                    </div>
                                                                </s:if>
                                                                <div class="inner-reqdiv-elements">
                                                                    <div class="col-sm-12 ">
                                                                        <label  class="task-label" style="max-height:10px;">Education</label>
                                                                        <s:textarea cssClass="titleStyle"   id="contactEducation" placeholder="Education"  name="contactEducation" maxlength="500" cols="100" rows="2" onkeyup="checkCharactersDescription(this)" tabindex="19" value="%{accountContactVTO.contactEducation}"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row"></div>
                                                        <div class="inner-reqdiv-elements">
                                                            <div class=" "> 
                                                                <h4><b>&nbsp;&nbsp;&nbsp;&nbsp;Contact Address</b></h4> 
                                                                <div class="col-sm-4">
                                                                    <label class="contactLabelStyle">Address</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPAddress}"  id="conAddress" maxLength="98" name="conAddress" placeholder="Address"   tabindex="20"  /> <!-- onkeyup="paddresValidation()" -->
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <%--label class="contactLabelStyle">Designation:</label><s:select cssClass="SelectBoxStyles form-control" name="contactDesignation" id="designation" headerKey="0" value="%{accountContactVTO.contactDesignation}" headerValue="-Select-" list="%{accountContactVTO.titles}"/--%>
                                                                    <label class="contactLabelStyle">Address2</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPAddress2}" id="conAddress2" maxLength="98" name="conAddress2" placeholder="Address2" tabindex="21"/>
                                                                </div>

                                                                <div class="col-sm-4">
                                                                    <label class="contactLabelStyle">City</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPCity}"  id="conCity" maxLength="18" name="conCity" pattern="[a-zA-Z\s]{3,}" title="must be valid name" placeholder="City" tabindex="22" />
                                                                </div>
                                                                <s:if test="accountContactVTO.conPCountry==-1">
                                                                    <div class="col-sm-4">
                                                                        <label class="contactLabelStyle">Country</label><s:select cssClass="form-control SelectBoxStyles" required="true" value="3" name="conCountry" id="conCountry"   list="countryNames" onchange="ConPermanentStateChange()" tabindex="23" />
                                                                    </div>
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="col-sm-4">
                                                                        <label class="contactLabelStyle">Country</label><s:select cssClass="form-control SelectBoxStyles" required="true" value="%{accountContactVTO.conPCountry}" name="conCountry" id="conCountry"  headerKey="-1" headerValue="Select Country" list="countryNames" onchange="ConPermanentStateChange()" tabindex="23"/>
                                                                    </div>
                                                                </s:else>
                                                                <div class="col-sm-4">
                                                                    <label class="contactLabelStyle">State</label><s:select cssClass="form-control SelectBoxStyles"  required="true" name="conState" id="conState" headerKey="-1" headerValue="Select State" value="%{accountContactVTO.conPState}" list="%{accountContactVTO.state1}" tabindex="24"/>
                                                                </div>

                                                                <div class="col-sm-4">
                                                                    <label class="contactLabelStyle">Zip</label><s:textfield cssClass="form-control"  value="%{accountContactVTO.conPZip}"  id="conZip" name="conZip" maxLength="10"  placeholder="Zip"  tabindex="25"/> <!-- onkeyup="contactPZipValidation()" -->
                                                                </div>

                                                            </div>
                                                        </div>


                                                    </div>
                                            </div>
                                        </div>
                                        <s:hidden name="flagname" value="%{flag}"></s:hidden>

                                            
                                        <div class="col-lg-10"></div>
                                        <div class="col-sm-2 pull-right">
                                            <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton fa fa-refresh form-control" value="Update" tabindex="26" /> 
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



                    <%--close of future_items--%>


                    <div id="imageupdate_popup">
                        <div id="imageupdateOverlay">

                            <div class="overlayOrButton_color">
                                <table>
                                    <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Change Profile Image&nbsp;&nbsp; </font></h4></td>
                                    <span class=" pull-right"><h5><a href="#" class="imageupdate_popup_close" onclick="openUploadFileDialogueClose()" ><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
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

                                        <center><button id="profileImageUpdateButton" type="submit" class="cssbutton"  onclick="return ValidateFileUpload()" theme="simple"  ><i class="fa fa-plus-square">&nbsp;ADD</i></button> </center><br>
                                    </div>

                                </div>
                            </s:form>
                        </div>
                    </div>


                    <!-- content end -->
                </section><!--/form-->
            </div>
        </div>
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

        <script>
            $("#actionMessage").show().delay(5000).fadeOut();
        </script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
