<%-- 
    Document   : Registration Form
    Created on : Sep 22, 2015, 3:39:21 PM
    Author     : miracle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServicesBay :: Registration Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/jquery-1.8.2.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>

        <script language="JavaScript" type="text/javascript" src="<s:url value="/includes/js/general/ProfilePage.js"/>" ></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/register/demo.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/register/style2.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/register/modernizr.custom.04022.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/account/formVerification.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>

      
    </head>
    <body oncontextmenu="return false" onload="regStateChange(document.getElementById('org_country').id);regStateChange(document.getElementById('country').id);">
        <header id="header"><!--header-->
            <div class="header_top" ><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->

        <div class="container register_form">
            <section class="af-wrapper">
                <h2 class="vendorHeading">Vendor Registration</h2>
                <div class="pull-right"><font style="color: red">* To Show only required fields&nbsp;</font><label style="cursor: pointer;color: blue;font-weight: normal" for="af-showreq" >click here.</label></div>
                <input id="af-showreq" class="af-show-input" type="checkbox" name="showreq"/>
                <s:form action="userRegistration.action" cssClass="af-form" id="af-form"  method="post" name="userLoginForm"   validate="" theme="simple"> 
                    <div class="row">



                        <s:if test="%{resultMessage=='NULL'}">
                            <span id="resultMessage" style="color:red;width: auto">Not Inserted Successfully..!!</span>
                        </s:if> 
                        <s:elseif test="%{resultMessage=='Added'}">
                            <span id="resultMessage" style="color:green;width: auto">Inserted Successfully..!!</span> 
                        </s:elseif>  
                        <span id="regValidation"></span>
                    </div>
                    <div class="row">
                        <h3>Vendor Details</h3>
                        <div class="af-outer af-required  ">
                            <div class="af-inner">
                                <label for="input-oname">Name<font style="color: red"> *</font></label>
                                    <s:textfield name="orgName" type="text" id="orgName" maxLength="60" required="required" placeholder="Vendor Name"
                                                 onblur="javascript: orgNameCheck('#orgName','#orgNameCheckSpan')"/>
                                <span id="orgNameCheckSpan"></span>
                            </div>
                        </div>

                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-owebadd">URL<font style="color: red"> *</font></label>
                                    <s:textfield name="org_web_address" type="text" id="org_web_address" maxLength="60" required="required" placeholder="Vendor URL"
                                                 onblur="javascript: orgWebAddressCheck('#org_web_address','#orgWebCheckSpan')"/>
                                <span id="orgWebCheckSpan"></span>
                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-owebadd">Mail Extension<font style="color: red">*</font></label>
                                    <s:textfield name="email_ext" type="text" id="email_ext" maxLength="60" required="required" placeholder="Mail Extension"
                                                 onchange="getValidMailExtention()"/>
                                <span id="orgExtCheckSpan"></span>
                            </div>
                        </div>  
                    </div>
                    <div class="row">
                        <h3>Vendor Address</h3>

                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-add1">Address 1<font style="color: red"> *</font></label>
                                    <s:textfield name="org_address1" type="text" maxLength="100" id="org_address1" required="required" placeholder="Address 1"/>

                            </div>
                        </div>
                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-add2">Address 2</label>
                                <s:textfield name="org_address2" type="text" maxLength="100" id="org_address2"  placeholder="Address 2"/>

                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-ocity">City<font style="color: red"> *</font></label>
                                    <s:textfield name="org_city" type="text" id="org_city" maxLength="20" required="required" placeholder="City"/>

                            </div>
                        </div>

                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-ocountry">Country<font style="color: red"> *</font></label>
                                    <s:select name="org_country" type="text" id="org_country" required="required" placeholder="Country"
                                              headerKey="-1" headerValue="Select Country" theme="simple" value="3"
                                              list="%{countryList}" onchange="regStateChange(this.id)"/>

                            </div>
                        </div>

                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-ostate">State<font style="color: red"> *</font></label>
                                    <s:select name="org_state" type="text" id="org_state" required="required" placeholder="State"
                                              headerKey="" headerValue="Select State" theme="simple"
                                              list="{}" />

                            </div>
                        </div>
                        <div class="af-outer ">
                            <div class="af-inner">
                                <label for="input-ozip">Zip</label>
                                <s:textfield name="org_zip" type="text" id="org_zip" maxLength="10"  placeholder="Zip"/>

                            </div>
                        </div>

                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-ofax">Fax</label>
                                <s:textfield name="org_fax" type="text" maxLength="15" id="org_fax" placeholder="Fax"/>

                            </div>
                        </div>

                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-title">Title<font style="color: red"> *</font></label>
                                    <s:textfield name="title" type="text" maxLength="30" id="title" required="required" placeholder="Title"/>
                            </div>
                        </div>
                        <div class="af-outer af-required" >
                            
                            <div class="af-inner">
                                <label for="input-noemp">No. of Employees<font style="color: red"> *</font></label>
                              
                                    <s:textfield  name="noOfEmployees" type="text" maxLength="7" id="noOfEmployees" required="required" placeholder="Number of Employees"/>
                             
                        </div>
                        </div>
                    </div>
                    <div class="row">
                       <h3 >Administrator</h3>
                        <div class="col-sm-12">
                            <div class="col-sm-2"></div>
                            <div class="">
                                
                                <s:checkbox name="checkAddress" style="float:left;margin:8px" tabindex="13" id="checkAddress" onclick="userRegFillAddress();" ></s:checkbox>
                       
                            </div> <div  class="col-sm-6">  <label class="checkboxLabel" for="checkAddress" style="width:171px;float: left" >Same as Above Address</label>
                                
                          <span><j2></j2></span>
                            </div>
                                <div class="col-sm-4"></div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">

                                <label for="input-fname">First Name<font style="color: red"> *</font></label>

                                <s:textfield  name="first_name" type="text" id="first_name" maxLength="30" required="required" placeholder="First Name" x-moz-errormessage="Please enter the First Name"/>

                            </div>
                        </div>

                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-lname">Last Name<font style="color: red"> *</font></label>
                                    <s:textfield name="last_name" type="text" id="last_name" maxLength="30" required="required" placeholder="Last Name"/>

                            </div>
                        </div>
                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-mname">Middle Name</label>
                                <s:textfield name="middle_name" type="text" maxLength="30" id="middle_name"  placeholder="Middle Name"/>

                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-phone">Office Phone<font style="color: red"> *</font></label>
                                    <s:textfield name="office_Phone" type="text" id="office_Phone" placeholder="Phone" required="required"  />

                            </div>
                        </div>

                        <div class="af-outer ">
                            <div class="af-inner">
                                <label for="input-ophone">Phone</label>
                                <s:textfield name="phone" type="text" id="phone" placeholder="Phone"/>

                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-oemail">Office Email<font style="color: red"> *</font></label>
                                    <s:textfield name="office_emailId" type="email" maxLength="60" id="office_emailId" required="required" placeholder="Office Email Address" onblur="return regOfficeEmailValidation()"/>
                                <span id="officeemail"></span>
                            </div>
                        </div>
                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-email">Email</label>
                                <s:textfield name="emailId" type="email" id="emailId" maxLength="60" placeholder="Email Address" onblur="regEmailValidation()"/>
                                <span id="email"></span>
                            </div>
                        </div>


                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-add1">Address 1<font style="color: red"> *</font></label>
                                    <s:textfield name="address1" type="text" maxLength="100" id="address1" required="required" placeholder="Address 1"/>

                            </div>
                        </div>
                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-add2">Address 2</label>
                                <s:textfield name="address2" type="text" maxLength="100" id="address2"  placeholder="Address 2"/>

                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-city">City<font style="color: red"> *</font></label>
                                    <s:textfield name="city" type="text" maxLength="20" id="city" required="required" placeholder="City"/>

                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-cont">Country<font style="color: red"> *</font></label>
                                    <s:select name="country" type="text" id="country" required="required" placeholder="Country"
                                              headerKey="-1" headerValue="Select Country" theme="simple" cssClass="SelectBoxStyles" value="3"
                                              list="%{countryList}" onchange="regStateChange(this.id)"/>

                            </div>
                        </div>
                        <div class="af-outer af-required">
                            <div class="af-inner">
                                <label for="input-state">State<font style="color: red"> *</font></label>
                                    <s:select name="state1" type="text" id="state1" required="required" placeholder="State"
                                              headerKey="-1" headerValue="Select State" theme="simple"
                                              list="{}"/>

                            </div>
                        </div>

                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-zip">Zip</label>
                                <s:textfield name="zip" type="text" id="zip" maxLength="10"  placeholder="Zip"/>

                            </div>
                        </div>

                        <div class="af-outer">
                            <div class="af-inner">
                                <label for="input-ofax">Fax</label>
                                <s:textfield name="fax" type="text" maxLength="15" id="fax" placeholder="Fax"/>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="pull-right_vendor">

                            <s:reset value="Reset" id="reset" cssClass="add_searchButton fa fa-eraser" style="width:75px;height:30px" type="button" onclick="clearRegistraionForm()"/>
                            <s:submit value="Register" id="submit" cssClass="add_searchButton fa fa-floppy-o" style="width:75px;height:30px" type="button" />

                        </div>
                    </div>

                    <s:token />
                </s:form>
            </section>
        </div>

        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">

                <s:include value="/includes/template/footer.jsp"/>

            </div>
            <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script>
            $("#org_fax").mask("(999)-999-9999");
        </script>
    </body>
</html>
