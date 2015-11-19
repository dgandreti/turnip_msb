<%--
    Document   : AccountDetails
    Created on : May 3, 2015, 2:08:50 PM
    Author     : rama krishna<lankireddy@miraclesoft.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServicesBay :: Vendor Details Page</title>
        <sx:head cache="true"/>
        <script language="JavaScript" type="text/javascript" src="<s:url value="/struts/dojo/struts_dojo.js"/>"></script>
        <script language="JavaScript" type="text/javascript" src="<s:url value="/struts/ajax/dojoRequire.js"/>" ></script>
        <%--ACCOUNT DETAILS SPECIFIC--%>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/vendorDetailsStyles.css"/>">



        <script type="text/JavaScript" src="<s:url value='/includes/js/general/dhtmlxcalendar.js'/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>




        <%--script type="text/javascript" src="<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>"></script--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/ProfilePage.js"/>"></script>
        <!-- this file for writing all profile function and  jquerys -->
        <!-- this is overlay jquery responsive and centered-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script>
        <script type="text/javascript">
            
        </script>

    </head>
    <body >
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
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="<%--backgroundcolor--%>" >
                                        <div class="row">
                                            <div class="col-lg-12">

                                                <%-- TOP TABS BEGIN--%>
                                                <div class="panel panel-info">
                                                    <div class="panel-body" id="panel-task-body" >
                                                        <!-- Nav tabs -->
                                                        <!--     <ul class="nav nav-tabs"> -->
                                                        <div><headingmess id="headingmessage"  class="vendor_menu_heading pull-right" style="display:block">Vendor Details</headingmess>    </div> 
                                                        <!-- Nav tabs -->
                                                        <ul class="active_details" >
                                                            <li class="dropdown"  >
                                                                <a class="active_details" data-toggle="dropdown"  href="#" title="Employee Details"   style="background-color: #fff; width:40px;"><img src="<s:url value="/includes/images/toggleMenu.png"/>" height="40" width="38"></a>

                                                                <ul class="active_details dropdown-menu " style="position:absolute">
                                                                    <li class=" active_details" ><a aria-expanded="false" href="#details" data-toggle="tab" id="vendordetails" onclick="Vendorheading(this);">Vendor Details</a>
                                                                    </li>
                                                                    <%--<li class="active_details"><a aria-expanded="false" href="#softwares" data-toggle="tab" id="vendorSoftware" onclick="Vendorheading(this);"  >Softwares</a>
                                                                    </li>
                                                                    <li class="active_details"><a aria-expanded="false" href="#team" data-toggle="tab" id="vendorTeam" onclick="Vendorheading(this);">Assign Team</a>
                                                                    </li>--%>
                                                                    <li class="active_details"><a aria-expanded="false" href="#contacts" data-toggle="tab" id="vendorContacts" onclick="showVendorContacts(); Vendorheading(this);">Contacts</a>
                                                                    </li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                        <div class="tab-content">
                                                            <div class="tab-pane fade in" id="details">
                                                                <s:form action="vendorDetails" id="vendorDetailsForm" theme="simple" >
                                                                    <div class="panel-body" id="task-panel" >
                                                                        <div class="row">
                                                                            <span><UpdateVendorInfo></UpdateVendorInfo></span>
                                                                            <div>
                                                                                <label style="color:#FF8000;" class="">BASIC DETAILS: </label>
                                                                                <div id="vendorBox">

                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <s:hidden name="venFlag" id="venFlag" value="%{venFlag}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Vendor Name : </label>
                                                                                        <s:textfield type="text"
                                                                                                     name="vendorName"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     id="vendorName"
                                                                                                     placeholder="vendorName"
                                                                                                     value="%{vendorListVTO.vendorName}"
                                                                                                     disabled="true"/>
                                                                                        <s:hidden name="vendorId" value="10004" id="vendorId"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Vendor URL : </label>
                                                                                        <s:textfield type="text"
                                                                                                     name="vendorURL"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     id="vendorURL"
                                                                                                     placeholder="vendorURL"
                                                                                                     value="%{vendorListVTO.vendorURL}"
                                                                                                     disabled="true"/>
                                                                                        <script>

                                                                                        </script>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Status : </label>
                                                                                        <s:select  id="vendorStatus"
                                                                                                   name="vendorStatus"
                                                                                                   cssClass="vendorSelectStyle" 
                                                                                                   headerKey="-1"

                                                                                                   theme="simple"
                                                                                                   list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" 
                                                                                                   value="%{vendorListVTO.vendorStatus}"/>

                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div>
                                                                                <label style="color:#FF8000;" class="">ADDRESS DETAILS : </label>
                                                                                <div id="vendorBox">

                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Address 1 : </label>
                                                                                        <s:textarea type="text"
                                                                                                    name="vendorAddress1"
                                                                                                    cssClass="vendorInputStyle"
                                                                                                    id="vendorAddress1"
                                                                                                    placeholder="vendorAddress1"
                                                                                                    value="%{vendorListVTO.vendorAddress1}"/>

                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Address 2 : </label>
                                                                                        <s:textarea type="text"
                                                                                                    name="vendorAddress2"
                                                                                                    cssClass="vendorInputStyle"
                                                                                                    id="vendorAddress2"
                                                                                                    placeholder="vendorAddress2"
                                                                                                    value="%{vendorListVTO.vendorAddress2}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Country : </label>
                                                                                        <s:select   id="vendorCountry"
                                                                                                    name="vendorCountry"
                                                                                                    cssClass="vendorSelectStyle"
                                                                                                    headerKey="-1"

                                                                                                    theme="simple"
                                                                                                    list="countryList"  
                                                                                                    value="%{vendorListVTO.vendorCountry}" onchange="changeState();"/>


                                                                                    </div>
                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">City : </label>
                                                                                        <s:textfield  id="vendorCity"
                                                                                                      cssClass="vendorInputStyle"
                                                                                                      name="vendorCity"
                                                                                                      type="text"
                                                                                                      placeholder="vendorCity"
                                                                                                      value="%{vendorListVTO.vendorCity}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Phone : </label>
                                                                                        <s:textfield  id="vendorPhone"
                                                                                                      cssClass="vendorInputStyle"
                                                                                                      name="vendorPhone"
                                                                                                      patter="{14}"
                                                                                                      type="text"
                                                                                                      placeholder="vendorPhone"
                                                                                                      value="%{vendorListVTO.vendorPhone}"/>





                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">State : </label>
                                                                                        <s:select   id="vendorState"
                                                                                                    name="vendorState"
                                                                                                    cssClass="vendorSelectStyle"
                                                                                                    headerKey="-1"

                                                                                                    theme="simple"
                                                                                                    list="stateList" 
                                                                                                    value="%{vendorListVTO.vendorState}"/>
                                                                                    </div>
                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Fax : </label>
                                                                                        <s:textfield  id="vendorFax"
                                                                                                      cssClass="vendorInputStyle"
                                                                                                      name="vendorFax"
                                                                                                      type="text"
                                                                                                      pattern="{14}"
                                                                                                      placeholder="vendorFax"
                                                                                                      value="%{vendorListVTO.vendorFax}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Zip : </label>
                                                                                        <s:textfield id="vendorZip"
                                                                                                     name="vendorZip"
                                                                                                     type="text"
                                                                                                     cssClass="vendorInputStyle" 
                                                                                                     placeholder="vendorZip"
                                                                                                     value="%{vendorListVTO.vendorZip}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Region : </label>
                                                                                        <s:textfield id="vendorRegion"
                                                                                                     name="vendorRegion"
                                                                                                     type="text"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     placeholder="vendorRegion"
                                                                                                     value="%{vendorListVTO.vendorRegion}"/>
                                                                                    </div>
                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Territory : </label>
                                                                                        <s:textfield id="vendorTerritory"
                                                                                                     name="vendorTerritory"
                                                                                                     type="text"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     placeholder="vendorTerritory" 
                                                                                                     value="%{vendorListVTO.vendorTerritory}"/>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div>
                                                                                <label style="color:#FF8000;" class="">OTHER DETAILS: </label>
                                                                                <div id="vendorBox">

                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Description : </label>
                                                                                        <s:textarea id="vendorDescription"
                                                                                                    name="vendorDescription"
                                                                                                    cssClass="vendorAreaStyle"
                                                                                                    type="text"
                                                                                                    placeholder="vendorDescription"
                                                                                                    value="%{vendorListVTO.vendorDescription}"/>


                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">VendorType : </label>
                                                                                        <s:select  id="vendorType"

                                                                                                   name="vendorType"
                                                                                                   cssClass="vendorSelectStyle"
                                                                                                   headerKey="-1"

                                                                                                   theme="simple"
                                                                                                   list="vendorTypeList" 
                                                                                                   value="%{vendorListVTO.vendorType}"/>
                                                                                    </div>
                                                                                    <div class="inner-vendordiv-elements">

                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Budget : </label>
                                                                                        <s:textfield id="vendorBudget"
                                                                                                     name="vendorBudget"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     type="text"
                                                                                                     placeholder="vendorBudget"
                                                                                                     value="%{vendorListVTO.vendorBudget}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Tax ID : </label>
                                                                                        <s:textfield id="vendorTaxid"
                                                                                                     name="vendorTaxid"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     type="text"
                                                                                                     placeholder="vendorTaxid" 
                                                                                                     value="%{vendorListVTO.vendorTaxid}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Industry : </label>
                                                                                        <s:select   id="vendorIndustry"
                                                                                                    name="vendorIndustry"
                                                                                                    cssClass="vendorSelectStyle"
                                                                                                    type="text"

                                                                                                    list="industryList"
                                                                                                    value="%{vendorListVTO.vendorIndustry}"/>
                                                                                    </div>
                                                                                    <div class="inner-vendordiv-elements">
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Stock Symbol : </label>
                                                                                        <s:textfield id="stockSymbol"
                                                                                                     name="stockSymbol"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     type="text"
                                                                                                     placeholder="stockSymbol"
                                                                                                     value="%{vendorListVTO.stockSymbol}"/>

                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">Revenue : </label>
                                                                                        <s:textfield id="vendorRvenue"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     name="vendorRvenue"
                                                                                                     type="text"
                                                                                                     placeholder="vendorRvenue"
                                                                                                     value="%{vendorListVTO.vendorRvenue}"/>
                                                                                        <label style="color:#56a5ec;" class="labelStyleVendor">No.of Employees: </label>
                                                                                        <s:textfield id="empCount"
                                                                                                     name="empCount"
                                                                                                     cssClass="vendorInputStyle"
                                                                                                     type="text"
                                                                                                     placeholder="empCount"
                                                                                                     value="%{vendorListVTO.empCount}"/>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>


                                                                        <div class="row">
                                                                            <div class="col-lg-12">
                                                                                <div class="col-sm-6" style=""></div>

                                                                                <div class="pull-right submitDiv">
                                                                                    <a href="#" ><input type="button" class="cssbutton " value="Save" onclick="updateVendorDetails();"></a> 
                                                                                </div>

                                                                            </div>
                                                                        </div>

                                                                    </div>

                                                                </s:form>
                                                            </div>
                                                            <div class="tab-pane fade in " id="softwares">
                                                                <div class="panel-body" id="task-panel" >
                                                                    <div class="row">
                                                                        <div class="col-lg-12">
                                                                            <s:form action="#" theme="simple">
                                                                                <div class="inner-vendordiv-elements">
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is SAP</div>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is Oracle</div>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is Java</div>
                                                                                </div>
                                                                                <div class="inner-vendordiv-elements"><br>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is SAP</div>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is Oracle</div>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is Java</div>
                                                                                </div>
                                                                                <div class="inner-vendordiv-elements"><br>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is SAP</div>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is Oracle</div>
                                                                                    <div class="checkboxAlign"><s:checkbox cssClass="checkboxAlign" name="a"/>is Java</div>
                                                                                </div>

                                                                            </s:form>
                                                                            <div style="float: right;">
                                                                                <a href="#save"><div class="details_button">UPDATE</div></a>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="tab-pane fade in " id="team">
                                                                <div class="panel-body" id="task-panel" >
                                                                    <div class="row">
                                                                        <div class="col-lg-12">
                                                                            <s:form action="vendorSalesUpdate" theme="simple">
                                                                                <label for="leftTitle"  id="primaryAssign1">Primary Assign</label><label  id="primaryAssign2"> : </label>
                                                                                <s:select   id="accountindustry"
                                                                                            name="primaryAccount"
                                                                                            cssClass="selectstyle"
                                                                                            headerKey="-1"
                                                                                            headerValue="Select an Account Team"
                                                                                            type="text"
                                                                                            value="%{primaryAccount}"
                                                                                            list="allVendorTeam"/>
                                                                                <div class="form-controls">
                                                                                    <label for="leftTitle" style="margin-left: 5px">
                                                                                        Assign team&nbsp;:
                                                                                    </label>
                                                                                </div>
                                                                                <div class="row " style="margin-left: 5px ;width: auto " >

                                                                                    <div style="margin-left: 0px ;overflow-x: auto">
                                                                                        <s:hidden name="vendorId" id="vendorId"  value="%{vendorId}"></s:hidden>
                                                                                        <s:optiontransferselect
                                                                                            label="User Roles"
                                                                                            name="salesTeam"
                                                                                            leftTitle="Avilable Members"
                                                                                            rightTitle="Added Members"
                                                                                            list="salesList"
                                                                                            headerKey="headerKey"
                                                                                            cssStyle="width:150px;height:235px" 							
                                                                                            cssClass="form-control"
                                                                                            doubleName="vendorSalesTeam"
                                                                                            doubleList="vendorSalesList"
                                                                                            doubleHeaderKey="doubleHeaderKey"
                                                                                            doubleValue="%{primaryAccount}"
                                                                                            doubleCssStyle="width:150px;height:235px"
                                                                                            doubleCssClass="form-control"
                                                                                            />	

                                                                                    </div>
                                                                                    <%--<div class="pull-right submitDiv">
                                                                                    <a href="#" ><input type="button" class="cssbutton " value="Save" onclick="updateVendorSales();"></a> 
                                                                                </div>--%>
                                                                                    <s:submit cssClass="pull-right btn cssbutton"  value="Update" />                                                                                                    

                                                                                </div>

                                                                            </s:form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="tab-pane fade in" id="contacts">
                                                                <%-- <s:include value="/acc/AccountContacts.jsp"/>--%>
                                                                <div class="row">
                                                                    <%-- <s:form action="getContactDetails" method="get" theme="simple" > --%>
                                                                    <div class="col-sm-12">

                                                                        <div class="row">

                                                                            <s:hidden name="vendorSearchId" id="vendorSearchId" value="%{vendorId}"/>
                                                                            <div class="col-lg-4">
                                                                                <label class="" style="">First Name:&nbsp;</label>
                                                                                <s:textfield id="firstNameContacts"
                                                                                             cssClass="textbox"  
                                                                                             type="text"
                                                                                             name="firstName"  
                                                                                             placeholder="First Name"/>
                                                                            </div>
                                                                            <div class="col-lg-4">
                                                                                <label class="" style="">Last Name:&nbsp;</label>
                                                                                <s:textfield id="lastNameContacts"
                                                                                             name="lastName"
                                                                                             cssClass="textbox" 
                                                                                             theme="simple"
                                                                                             type="text" 
                                                                                             placeholder="Last Name" />
                                                                            </div>
                                                                            <div class="col-lg-4">
                                                                                <label class="" style="">Email Id:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                                                <s:textfield id="emailContacts"
                                                                                             name="email"
                                                                                             cssClass="textbox" 
                                                                                             theme="simple"
                                                                                             type="text" 
                                                                                             placeholder="Email" />
                                                                            </div>
                                                                        </div>
                                                                        <br>
                                                                        <div class="row">

                                                                            <div class="col-lg-4">  
                                                                                <label class="" style="">Phone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                                                <s:textfield id="phoneContacts"
                                                                                             cssClass="textbox" 
                                                                                             name="phone" 
                                                                                             type="text"  
                                                                                             placeholder="Phone #" /> 
                                                                            </div>
                                                                            <div class="col-lg-4">
                                                                                <label class="" style="">Status:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                                                                <s:select id="statusContacts"
                                                                                          cssClass="selectBoxStyle"  
                                                                                          name="status"
                                                                                          list="#@java.util.LinkedHashMap@{'Active':'Active','Inactive':'Inactive'}"
                                                                                          headerKey="DF"

                                                                                          placeholder="Status" />
                                                                            </div>
                                                                            <div class="col-lg-4">
                                                                                <s:submit type="submit" 
                                                                                          cssClass="cssbutton_emps field-margin" cssStyle="margin:4px;"
                                                                                          value="Search" onclick="getContactSearchResults()"/>
                                                                                <span id="validationMessage" />
                                                                            </div>
                                                                        </div>


                                                                    </div>
                                                                    <div id="outputMessage"></div>
                                                                    <div class="col-sm-12">
                                                                        <s:form>

                                                                            <table id="contactPageNav" class="responsive CSSTable_task" border="5"cell-spacing="2">

                                                                                <tbody>
                                                                                    <tr>
                                                                                        <th>Name</th>
                                                                                        <th>E-mail</th>
                                                                                        <th>Phone</th>
                                                                                        <th>Status</th>
                                                                                        <th>Login</th>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            <br/>

                                                                            <div id="contactPageNavPosition" align="right" style="margin-right:0vw"></div>
                                                                            <div style="width:auto;height:auto" >
                                                                                <div  id="editSpan" class="badge pull-right" style="display:none"></div>                                                       
                                                                            </div> 

                                                                        </s:form>      


                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <script type="text/javascript">
                                                                var pager = new Pager('contactPageNav', 5); 
                                                                pager.init(); 
                                                                pager.showPageNav('pager', 'contactPageNavPosition'); 
                                                                pager.showPage(1);
                                                    
                                                                            
                                                            </script>
                                                        </div>
                                                        <!--End Tabs-->
                                                    </div>

                                                </div><%-- panel task body complete--%>


                                            </div>
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
        <script type="text/javascript">
                        
            var flag=document.getElementById("venFlag").value;
            //alert(flag);
            if(flag=="venDetails")
            {
                //alert("in if");
                // document.getElementById('details').className="";
                document.getElementById("headingmessage").innerHTML="Vendor Details";
                document.getElementById('details').className='tab-pane fade in active';
                // var obj=document.getElementById('contacts');
            }
            if(flag=="vendorContactSearch")
            {
                //alert("in if");
                // document.getElementById('details').className="";
                document.getElementById("headingmessage").innerHTML="Contacts";
                document.getElementById('contacts').className='tab-pane fade in active';
                            
                showVendorContacts();
                // alert("after show contacts function");


            }
            if(flag=="vendorAssignTeamUpdate")
            {
                //alert("in if");
                // document.getElementById('details').className="";
                document.getElementById("headingmessage").innerHTML="Assign Team";
                document.getElementById('team').className='tab-pane fade in active';
            }
                       
        </script>
    </body>
</html>