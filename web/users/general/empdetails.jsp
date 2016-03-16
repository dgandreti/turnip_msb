<%-- 
    Document   : empdetails
    Created on : Mar 17, 2015, 9:11:28 PM
    Author     : Nagireddy
--%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.mss.msp.usersdata.UserVTO"%>
<%@page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<!DOCTYPE html>
<html>
    <head>

        <!-- new styles -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Emp Details Page</title>
        <sx:head cache="true"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>"><!--this is for all css in profile view -->

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/dhtmlxcalendar.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <script type="text/JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>" > </script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script> 

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/ProfilePage.js"/>"></script> 
        <!-- this file for writing all profile function and  jquerys -->
        <!-- this is overlay jquery responsive and centered-->

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>


        <script type="text/javascript">
             
            var epager=new EmpPager('edu_pagenav',5);
            epager.init(); 
            epager.showPageNav('epager', 'edu_pageNavPosition'); 
            epager.showPage(1);

          
        </script>   
        <script type="text/javascript">
            var pager = new EmpPager('skilpagenav', 5);
            pager.init();     
            pager.showPageNav('pager', 'pageNavPosition'); 
            pager.showPage(1);
            // alert("skil in jsp")
        </script>

        <style>

        </style>
        <!-- end of new styles -->
    </head>
    <body style="font-family: 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans'" onload="doOnLoad(); doOnLoadDatePicker(); init(); initReport('<%= request.getParameter("userid")%>'); showAddressDetails('<%= request.getParameter("userid")%>');">
        <div id="main">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">

                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->
      
        <section id="generalForm"><!--form-->

            <%--<div class="header-middle"><!--header-middle-->
                <div class="container">
                    
                    <div class="row">
                        <s:include value="/includes/menu/generalTopMenu.jsp"/> 
                    </div>
                        
               </div>
            </div> --%>


            <div class="container">
                <div class="row" >

                    <s:include value="/includes/menu/LeftMenu.jsp"/> 


                    <!-- content start -->

                    <div class="col-sm-9 padding-right" style="background-color:#fff">
                        <div class="features_items" ><!--features_items--> 	                                               
                            <!-- maps start -->
                            <!-- maps end s_items--> 	                                               
                            <!-- maps start -->

                            <div class="col-lg-12 ">
                                <div class="" id="profileBox1" >

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font class="titleColor">Profile Information</font>
                                                <span class="pull-right"><a href="/<%=ApplicationConstants.CONTEXT_PATH%>/users/general/doEmployeeSearch.action"><i class="fa fa-undo"></i></a></span>
                                                </a>
                                            </h4>
                                        </div>

                                    </div>

                                    <div id="picture" style="float: left;margin-top: 20px" >

                                        <s:url id="image" action="rImage" namespace="/renderImage">
                                            <s:param name="path" value="empDetails.image_path"></s:param>
                                        </s:url>
                                        <img  src="<s:property value="#image"/>"  alt="loin" height="125px" width="120px">
                                    </div>
                                    <s:if test="hasActionMessages()">
                                        <div >
                                            <s:actionmessage cssClass="actionMessagecolor"/>
                                        </div>
                                    </s:if>
                                    <form action="updateEmpDetails" >

                                        <div class="updateCss">

                                            <div class="textfieldLabel">
                                                <%
                                                    String userid = request.getAttribute("userid").toString();
                                                    // System.out.println(request.getAttribute("userid") + "in jsp we printing");
%>




                                                <span ><j></j></span>
                                                <table> 

                                                    <s:hidden value="%{userid}" name="userid"/>

                                                    <s:textfield cssClass="form-control"   id="first_name" required="true" oninvalid="setCustomValidity('Must be valid Name')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" label="First Name" name="first_name" value="%{empDetails.first_name}" onkeyup="firstNameValidation()"/>
                                                    <s:textfield cssClass="form-control" id="last_name" required="true" oninvalid="setCustomValidity('Must be valid Name')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" label="Last Name" value="%{empDetails.last_name}" name="last_name" onkeyup="lastNameValidation()"/>
                                                    <s:textfield cssClass="form-control" label="Alias" value="%{empDetails.alias}" name="alias"/>
                                                    <s:radio label="Marital Status" name="marital_status" list="#@java.util.LinkedHashMap@{'1':'Single','2':'Married'}" value="%{empDetails.marital_status}" />
                                                    <%--<s:textfield cssClass="form-control"  label="Marital Status" value="%{empDetails.marital_status}" name="marital_status"/>--%>
                                                    <s:textfield  disabled="true" cssClass="form-control"  label="Corp.Email" value="%{empDetails.email1}" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}"  required="true" oninvalid="setCustomValidity('Must be valid email')"   onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" onkeyup="EmailValidation1()"/>
                                                    <s:hidden name="email1" id="email1" value="%{empDetails.email1}"/>
                                                    <s:select cssClass="form-control SelectBoxStyles" required="true" headerKey="0" name="emp_position"  label="Emp Position" id="emp_position" list="#@java.util.LinkedHashMap@{'CRP':'Contract','PRM':'Permanent'}" value="%{empDetails.emp_position}"/>

                                                </table>
                                            </div>
                                        </div>
                                        <div  class="updateCss" >

                                            <div class="textfieldLabel">
                                                <table>

                                                    <s:textfield cssClass=" form-control dateImage" label="DOB"  name="dob" id="dob"  value="%{empDetails.dob}"  pattern="{10}"  required="true" oninvalid="setCustomValidity('Must be valid date')"   onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2"  onkeypress="return enterDateRepository();"/>
                                                    <%--<s:textfield cssClass="form-control" label="DoB" value="%{empDetails.dob}" name="dob"/>--%>
                                                    <%-- <s:textfield cssClass="form-control" label="Gender" value="%{empDetails.gender}" name="genderClass"/>--%>
                                                    <s:radio label="Gender" name="gender" list="#@java.util.LinkedHashMap@{'1':'Male','2':'Female'}" value="%{empDetails.gender}" />
                                                    <%--  <s:textfield cssClass="form-control" id="working_country" required="true" oninvalid="setCustomValidity('Must be valid country')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" label="Working In" value="%{empDetails.working_country}" name="working_country" onkeyup="CountryValidation()"/>
                                                    <s:textfield cssClass="form-control" id="living_country" required="true" oninvalid="setCustomValidity('Must be valid country')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" label="Living Country" value="%{empDetails.living_country}" name="living_country" onkeyup="CountryValidation1()"/>
                                                    --%>
                                                    <s:select cssClass="form-control SelectBoxStyles" required="true" oninvalid="setCustomValidity('Must be selected')" onchange="try{setCustomValidity('')}catch(e){}" headerKey="" headerValue="Select Country" label="Working In" name="working_country" id="working_country" list="%{countries}" value="%{empDetails.working_country}" onblur="CountryValidation()"/> 
                                                    <s:select cssClass="form-control SelectBoxStyles" required="true" oninvalid="setCustomValidity('Must be selected')" onchange="try{setCustomValidity('')}catch(e){}" headerKey="" headerValue="Select Country" label="Living country" name="living_country" id="living_country" list="%{countries}" value="%{empDetails.living_country}" onblur="CountryValidation1()"/> 
                                                    <s:textfield cssClass="form-control" label="Corp.Phone" value="%{empDetails.corp_phone}" pattern="{14}"   oninvalid="setCustomValidity('Must be valid phone')"   onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" id="corp_phone" name="corp_phone"/>

                                                </table>
                                            </div>
                                        </div>

                                        <div  class="updateCss" >

                                            <div class="textfieldLabel">
                                                <table>
                                                    <s:textfield cssClass="form-control" label="Email" value="%{empDetails.email2}" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Must be valid email')"   onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" name="email2" id="email2" onkeyup="EmailValidation2()"/>
                                                    <s:textfield cssClass="form-control" label="Phone1" value="%{empDetails.phone1}" pattern="{14}"  required="true" oninvalid="setCustomValidity('Must be valid phone')"   onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" name="phone1" id="phone1" onkeyup="phoneValidation()" />

                                                    <s:textfield cssClass="form-control" label="Fax"  value="%{empDetails.fax}" pattern="{14}"  oninvalid="setCustomValidity('Must be valid fax')"   onchange="try{setCustomValidity('')}catch(e){}"  tabindex="2" name="fax" id="fax"/>
                                                    <s:select cssClass="form-control SelectBoxStyles" required="true" oninvalid="setCustomValidity('Must be selected')" onchange="try{setCustomValidity('')}catch(e){}" headerKey="" headerValue="Select Country" label="Current Status" name="current_status" id="current_status" list="#@java.util.LinkedHashMap@{'Registered':'REGISTERED','Active':'ACTIVE','In-Active':'IN-ACTIVE'}" value="%{empDetails.current_status}" onblur="StatusValidation()"/> 
                                                    <s:textfield cssClass="form-control"  label="Organization " id="" name="Oraganization " value="%{empDetails.account_name}" />
                                                    <s:submit cssClass="col-sm-offset-4 btn cssbutton" value="Update"></s:submit>
                                                    </table>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>


                                <!-- Start Miscellaneous Information-->

                                <div class="col-lg-6 " style="margin-bottom: 8px" >
                                    <div id="" class="backgroundcolor" padding: 0px">

                                         <div class="panel-heading">
                                            <h4 class="panel-title" id="miscellaneous-info">
                                                <!-- <a data-toggle="collapse" data-parent="#accordian" href="#miscellaneous-slide">
                                                     <span class="badge pull-right"><i class="fa fa-angle-double-down "></i></span>-->
                                                <font color="#ffffff">Miscellaneous Information</font>
                                                </a>
                                            </h4>
                                        </div>
                                    </div>

                                    <div class="panel-body" id="reportingBox">
                                        <div class="col-lg-12">

                                            <div class="updateCss" id="" style="float: left"> 
                                                <span id="MiscellaneousResult">Miscellaneous Updated successfully</span>

                                                <div class="margins textfieldLabel">
                                                    <table>

                                                    <s:select  cssClass="SelectBoxStyles form-control" id="departments" label="Departments" value="%{empDetails.department}" headerKey="0" headerValue="-Select-"   list="%{department}" onchange="getTitles()"/>
                                                    <%--s:select id="headSelectBoxStyle"  headerKey="" headerValue="--Select--" theme="simple" list="#session.rolesMap" value="#session.primaryrole"/><br><br--%>
                                                    <s:select  cssClass="SelectBoxStyles form-control" label="Titles"  id="titlesId" headerKey="0" headerValue="-Select-"   list="%{empDetails.titles}" value="%{empDetails.title}" />
                                                    <s:hidden name="empId" id="reportsToId"/>
                                                    <s:hidden value="%{userid}" name="userid" id="userid"/>

                                                    <span id="validationMessage" ></span>
                                                    <s:textfield cssClass="form-control" label="ReportsTo" id="primaryReport"  onkeyup="getAllEmpNames();" />

                                                </table>
                                                <div class="row_checkbox-inline " >
                                                    <div class="col"><s:checkbox name="" id="isTeam" value="%{empDetails.is_team_lead}"/><label>is TeamLeader</label></div>
                                                </div>
                                                <div class="row_checkbox-inline" >    
                                                    <div class="col"><s:checkbox name="" id="isManager" value="%{empDetails.is_manager}"/><label>is Manager</label></div>
                                                </div>
                                                <div class="row_checkbox-inline ">

                                                    <div class="col"><s:checkbox name="" id="isPmo" value="%{empDetails.is_pmo}"/><label>&nbsp;is PMO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></div>
                                                    <div class="col"><s:checkbox name="" id="isOpt" value="%{empDetails.opt_contact}"/><label>is opt contact</label></div>
                                                </div>
                                                <div class="row_checkbox-inline ">
                                                    <div class="col"><s:checkbox name="" id="isSbteam" value="%{empDetails.is_sbteam}"/><label>is SB Team</label></div>

                                                </div>

                                                <s:submit cssClass="col-sm-offset-4 btn cssbutton" value="Update" onclick="updateMiscellaneousInfo()"  />

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <!-- End Miscellaneous Information-->

                            <div class="col-lg-6" >

                                <div id=""  class="backgroundcolor"  >


                                    <div class="panel-heading">
                                        <h4 class="panel-title" id="roles-info">
                                            <!-- <a data-toggle="collapse" data-parent="#accordian"  href="#rolesBox" >
                                                 <span class="badge pull-right"><i class="fa fa-angle-double-down "></i></span> </a>-->
                                            <font color="#ffffff">Roles Information</font>

                                        </h4>
                                    </div>                                  
                                </div>



                                <div class="col-lg-12 textfieldLabel" id="reportingBox">
                                    <div class=" panel-body">
                                        <!-- <div class="panel-collapse collapse margins" id="rolesBox" > -->
                                        <s:form action="doAddOrUpdateRoles" theme="simple">

                                            <s:hidden value="%{userid}" name="userid"/>

                                            <label for="leftTitle" style="margin-left: 5px">Primary Role</label><label style="margin-left: 20px;color:#2148f0"> : </label>

                                            <s:select headerKey="0" name="primaryRole" headerValue="Select Roles" list="orgRoles"  theme="simple" cssClass="headSelectBoxStyle" value="%{primaryRole}" /> 

                                            <div class="form-controls">
                                                <label for="leftTitle" style="margin-left: 5px">
                                                    Assign Roles&nbsp;

                                                </label>


                                            </div>


                                            <div class="row " style="margin-left: 5px ;width: auto " >

                                                <div style="margin-left: 0px ;overflow-x: auto">

                                                    <s:optiontransferselect
                                                        label="User Roles"
                                                        name="leftSideEmployeeRoles"
                                                        leftTitle="Avilable Roles"
                                                        rightTitle="Added Roles"
                                                        list="notAssignedRoles"
                                                        headerKey="headerKey"
                                                        cssStyle="width:120px;height:235px" 							
                                                        cssClass="form-control"
                                                        doubleName="addedRolesList"
                                                        doubleList="assignedRoles"
                                                        doubleHeaderKey="doubleHeaderKey"
                                                        doubleValue="%{primaryRole}"
                                                        doubleCssStyle="width:120px;height:235px"
                                                        doubleCssClass="form-control"
                                                        />	

                                                </div>
                                                <s:submit cssClass="pull-right btn cssbutton" value="Update"  />                                                                                                    

                                            </div>
                                        </s:form>   
                                    </div> </div>
                            </div>





                            <!-- contact information -->

                            <!-- tab starting  -->
                            <div class="col-lg-12" >
                                <div class="panel panel-info">
                                    <div class="panel-heading" id="empEdit-heading">
                                        Employee Details  
                                    </div>

                                    <div id="panel-task-body" class="panel-body">
                                        <!-- Nav tabs -->
                                        <ul class="" >
                                            <li class="dropdown"  >
                                                <a class="dropdown-toggle " data-toggle="dropdown"  href="#" title="Employee Details"   style="background-color: #fff; width:40px;"><img src="<s:url value="/includes/images/toggleMenu.png"/>" height="40" width="38"></a>
                                                <span id="spanId" class="employee_menu_heading pull-right" style="display: none;"></span>
                                                <ul class=" dropdown-menu  nav-stacked" style="position:absolute">
                                                    <li class=""><a aria-expanded="false" id="contactDetails"  onclick="showAddressDetails('<%= request.getParameter("userid")%>');employeeHeadingMessage(this);" href="#contactBox" data-toggle="tab">Contact Information</a>
                                                    </li>
                                                    <li class=""><a aria-expanded="false" id="educationDetails"  onclick="showEducationDetails('<%= request.getParameter("userid")%>');employeeHeadingMessage(this);" href="#Educationslide" data-toggle="tab"   >Education Information</a>
                                                    </li>
                                                    <li class=""><a aria-expanded="false" id="skillDetails" onclick="showSkillDetails('<%= request.getParameter("userid")%>');employeeHeadingMessage(this);" href="#Skillslide" data-toggle="tab">Skill Information</a>
                                                    </li>
                                                    <li class=""><a aria-expanded="false" id="confidentialInformation" onclick="getSecurityDetails('<%= request.getParameter("userid")%>');employeeHeadingMessage(this);" href="#security-info" data-toggle="tab">Confidential Information</a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>


                                        <div class="tab-content">
                                            <div class="tab-pane fade textfieldLabel in active" id="contactBox">
                                                <div class="panel-body" id="task-panel" >
                                                    <div class="row">
                                                        <!-- Contact Information , start  -->
                                                        <div class="col-lg-6">
                                                            <span id="updateResultp">Permanent Address Updated successfully</span>
                                                            <div id="AddressBox"> 
                                                                <div class="contactInfoDiv">
                                                                    <table >
                                                                        <tr id="trStyleContact"><td>&nbsp;&nbsp;Permanent Address &nbsp;&nbsp;</td></tr>
                                                                        <span id="spanUpdatep" class="pull-right">    
                                                                            <h5><a href="javascript:permanentAddressUpdate('<%= request.getParameter("userid")%>');" class="contactUpdateAnchor" >Update</a>&nbsp;&nbsp;</h5></span>
                                                                    </table>
                                                                </div>
                                                                <div id="margins" class="showUpdatep"><br>
                                                                    <center> <table>
                                                                            <span><j1></j1></span>
                                                                            <s:textfield cssClass="form-control" label="Address" id="Address" name="address" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="paddressValidation()"  />
                                                                            <s:textfield cssClass="form-control" label="Address2" id="Address2" name="address2"  />
                                                                            <s:textfield cssClass="form-control" label="City" id="City" name="city" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pcityValidation()" />
                                                                            <s:textfield cssClass="form-control" label="State" id="State" name="state" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pStateValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Zip" id="Zip" name="zip" maxLength="5" required="true" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pZipValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Country" id="Country" name="country" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCountryValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Phone" id="Phone" name="phone" required="true" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pPhoneValidation()" />
                                                                        </table></center>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <span id="updateResultc">Current Address Updated successfully</span>
                                                            <div id="eduSecondry">
                                                                <div class="contactInfoDiv" >
                                                                    <table >
                                                                        <tr id="trStyleContact" ><td>&nbsp;&nbsp;Current Address &nbsp;&nbsp;</td></tr>
                                                                        <span id="spanUpdatec" class="pull-right">    
                                                                            <h5><a href="javascript:currentAddressUpdate('<%= request.getParameter("userid")%>');" class="contactUpdateAnchor" >Update</a>&nbsp;&nbsp;</h5></span>
                                                                    </table>
                                                                </div>
                                                                <div id="margins"  class="showUpdatec">
                                                                    <center> <table>
                                                                            <s:checkbox label="Same as Permanent Address" name="address_flag"  id="checkAddress"   ></s:checkbox>
                                                                                <span><j2></j2></span>
                                                                            <s:textfield cssClass="form-control" label="Address" id="CAddress" name="address" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCAddressValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Address2" id="CAddress2" name="address2" />
                                                                            <s:textfield cssClass="form-control" label="City" id="CCity" name="city" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCCityValidation()" />
                                                                            <s:textfield cssClass="form-control" label="State" id="CState" name="state" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCStateValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Zip" id="CZip" name="zip" maxLength="5" required="true" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCZipValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Country" id="CCountry" name="country" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCCountryValidation()" />
                                                                            <s:textfield cssClass="form-control" label="Phone" id="CPhone" name="phone" required="true" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCPhoneValidation()"/>

                                                                        </table></center>

                                                                </div>
                                                            </div>
                                                        </div>                
                                                    </div>
                                                </div>    
                                            </div>

                                            <!-- Contact Information , end  -->                  
                                            <!-- Education Detail , start  -->
                                            <div class="tab-pane fade " id="Educationslide"   >

                                                <table id="edu_add" >
                                                    <div id="add_edu" ><a href="" class="edu_popup_open btn add-recordBtn pull-right " onclick="removeDataAfterCloseOverlay()" style="margin-right:0%"  >ADD QUALIFICATION</a><span><addEdu></addEdu></span></div>
                                                </table>   

                                                <edu></edu>
                                                <table id="edu_pagenav" class="CSSTable_task  responsive" border="5"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;" >

                                                    <tr>
                                                        <th>Qualification</th>
                                                        <th>University</th>
                                                        <th>Institute</th>
                                                        <th>Year Start</th>
                                                        <th>Year End</th>
                                                        <th>Percentage</th>
                                                        <th>Specialization</th>
                                                    </tr>

                                                </table > 
                                                <div id="edu_pageNavPosition" align="right" style="margin-right:0vw"></div>
                                                <div   style="width:auto;height:auto" >
                                                    <div  id="editSpan" class="badge pull-right" style="display:none"></div>                                                       
                                                </div>                                                            

                                            </div>

                                            <!-- Education Detail , end  -->

                                            <!-- Skill Detail , start  -->

                                            <div class="tab-pane fade" id="Skillslide" >
                                                <table id="skill_add" >
                                                    <div id="add_skill" ><a href="" class="skilladd_popup_open btn add-recordBtn pull-right" onclick="removeDataAfterCloseOverlay()"  style="margin-right:0%">ADD NEW SKILL</a></div>
                                                </table>   

                                                <table id="skilpagenav" class="CSSTable_task  responsive" border="5"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden" onclick="removeDataAfterCloseOverlay()">
                                                    <tr>

                                                        <th>Skill Name</th>
                                                        <th>Rate your Skill</th>
                                                        <th>Comments</th>
                                                    </tr>
                                                </table> 
                                                <div id="pageNavPosition" align="right" style="margin-right: 0vw"></div>
                                            </div>
                                            <!-- Skill Detail , end  -->
                                            <!-- Security information , start  -->
                                            <div class="tab-pane fade textfieldLabel" id="security-info">
                                                <div class="row">
                                                    <div class="col-lg-6" style="float: left">
                                                        <span class="successInforesult"><securityinfo id="successInfo"></securityinfo></span>
                                                        <div id="profileBox1"><center>
                                                                <table>
                                                                    <s:textfield cssClass="form-control" id="pan" label="SSN/PAN" maxLength="10" required="true"   placeholder="ABCde1234F/123-12-1234" onkeyup="panValidation()" />
                                                                    <s:textfield cssClass="form-control" id="name" label="Name on PAN" maxLength="40" required="true"   placeholder="Ex.John" onkeyup="nameValidation()"/>
                                                                    <s:textfield cssClass="form-control" id="bank" label="Bank Name" required="true"  maxLength="20" placeholder="Ex.SBI/kvb" onkeyup="banknameValidation()"/>
                                                                    <s:textfield cssClass="form-control" id="banknum" label="Bank A/C No." required="true"  maxLength="20" placeholder="Ex.A1234d567891234567" onkeyup="banAccknumValidation()"/>
                                                                    <s:textfield cssClass="form-control" id="hname" label="A/C H.Name" required="true"  maxLength="25" placeholder="Ex.John" onkeyup="b_holdnameValidation()"/>
                                                                </table></center></div>
                                                    </div>
                                                    <div class="col-lg-6" style="float: left">
                                                        <div id="profileBox1"><center>
                                                                <table>
                                                                    <s:textfield cssClass="form-control" id="ifsc" label="IFSC Code" required="true"  maxLength="11" placeholder="Ex.ABcd0123456" onkeyup="ifscValidation()"/>   
                                                                    <s:textfield cssClass="form-control" id="pf" label="PF No." required="true"  maxLength="16" placeholder="Ex.Ab-12345-1234567" onkeyup="pfValidation()"/>
                                                                    <s:textfield cssClass="form-control" id="uan" label="UAN No." required="true"  maxLength="25" placeholder="Ex.123456" onkeyup="uanValidation()"/>
                                                                    <s:textfield cssClass="form-control" id="pass" label="Passport NO." required="true"  maxLength="15" placeholder="Ex.A12a3455" onkeyup="passportnumValidation()"/>
                                                                    <s:textfield cssClass="form-control dateImage" id="passport" label="Passport Exp" required="true"  placeholder="" value="%{docdatepicker}" tabindex="0"  onkeypress="return passportDateValidation();"/>
                                                                    <s:submit  id="updatebutton" value="Save" onclick="addSecurityInfo()"/>
                                                                </table></center></div>
                                                    </div>

                                                </div>
                                            </div>
                                            <!-- Security information  , end  -->                  

                                        </div>
                                    </div>     
                                </div>
                            </div>    



                            <!-- tab ending  -->





                            <!-- qualification overlay for add record start here....-->

                            <div id="edu_popup">
                                <div id="eduBoxOverlay">
                                    <div class="backgroundcolor">
                                        <div class="panel-heading" >
                                            <h4 class="panel-title" >
                                                <a data-toggle="collapse" data-parent="#accordian"  href="#" >
                                                    <span><a href="" class="edu_popup_close pull-right"><i class="fa fa-times-circle-o fa-size"></i></a></span>
                                                    <font color="#ffffff">Add Education</font>
                                                </a> 
                                            </h4>
                                        </div>

                                    </div>
                                    <div>
                                        <form action="#" theme="simple" name="EduAddInfo">
                                            <div>


                                                <span><errorEduAdd></errorEduAdd></span>
                                                <s:hidden value="%{userid}" name="userid"/>
                                                <s:hidden id="usr_edu_id" name="usr_edu_id" /> <br> 

                                                <div class="inner-addEduDiv-elements ">
                                                    <label class="labelStyle">Qualification</label>:<s:textfield cssClass="inputStyle" name="qualification" id="qualification" placeholder="Qualification" onfocus="removeResultMessage()"/>
                                                </div> 
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">University</label>:<s:textfield  name="university"  id="university" placeholder="University" cssClass="eduInputStyle" onfocus="removeResultMessage()"/>
                                                </div>
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Institution Name</label>:<s:textfield name="institution"  id="institution" placeholder="Institution name" cssClass="eduInputStyle"  onfocus="removeResultMessage()"/>
                                                </div>
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Start year</label>:<s:textfield cssClass="inputStyle " name="year_start" id="year_start" placeholder="StartDate" value="%{startDate}" cssStyle="z-index: 10000004;" onfocus="removeResultMessage()" pattern="{10}"  required="true" oninvalid="setCustomValidity('Must be valid date')"   onchange="try{setCustomValidity('')}catch(e){}"  onkeypress="return enterDateRepository();"  />
                                                    <label class="labelStyle">End year</label>:<s:textfield cssClass="inputStyle " name="year_end" value="%{endDate}" id="year_end" placeholder="EndDate" onfocus="removeResultMessage()" pattern="{10}"  required="true" oninvalid="setCustomValidity('Must be valid date')"   onchange="try{setCustomValidity('')}catch(e){}"  onkeypress="return enterDateRepository();" />
                                                </div>
                                                <%--<div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Start year</label>:<s:textfield  id="year_start"  name="year_start" cssClass="inputStyle" placeholder="Start year" />
                                                    <label class="labelStyle">End year</label>:<s:textfield  id="year_end"  name="year_end" cssClass="inputStyle" placeholder="Start year" />
                                                </div>--%>
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Percentage</label>:<s:textfield  id="percentage"  name="percentage" cssClass="inputStyle" placeholder="Percentage" maxlength="5"  onfocus="removeResultMessage()"/>
                                                    <label class="labelStyle">Specialization</label>:<s:textfield  id="specialization"  name="specialization" cssClass="inputStyle" placeholder="Specialization" onfocus="removeResultMessage()"/>
                                                </div>
                                                <div  class="inner-addEduDiv-elements">
                                                    <s:reset cssClass="col-md-offset-9 btn cssbutton" id="updatebutton"  value="Clear" theme="simple"  />

                                                    <s:submit cssClass="btn cssbutton" value="Save" theme="simple" onclick="return addQualification()" />
                                                </div>

                                            </div>
                                        </form>
                                    </div> 
                                </div>
                            </div>


                            <!-- qualification overlay for edit  record start here.... -->                         

                            <div id="eduEdit_popup">

                                <div id="eduEditBoxOverlay" >
                                    <div style="background-color: #3bb9ff ; padding: 0px">
                                        <table>
                                            <tr><td><h4 style=""><font color="#ffffff">&nbsp;&nbsp;Edit Education Details&nbsp;&nbsp; </font></h4></td>
                                            </tr>
                                            <span class=" pull-right"><h5><a href="" class="eduEdit_popup_close" onclick="removeDataAfterCloseOverlay()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                        </table>
                                    </div>

                                    <div>
                                        <form action="#" theme="simple" >
                                            <div>

                                                <span><errorEduUpdate></errorEduUpdate></span>
                                                <s:hidden value="%{userid}" name="userid"/>
                                                <s:hidden id="usr_edu_id" name="usr_edu_id" /> <br> 
                                                <div class="inner-addEduDiv-elements ">
                                                    <label class="labelStyle">Qualification</label>:<s:textfield cssClass="inputStyle" name="qualification" id="edu_qualification" onfocus="removeResultMessage()" />
                                                </div> 
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">University</label>:<s:textfield  name="university"  id="edu_university"  cssClass="eduInputStyle" onfocus="removeResultMessage()"/>
                                                </div>
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Institution Name</label>:<s:textfield name="institution"  id="edu_institution"  cssClass="eduInputStyle" onfocus="removeResultMessage()" />
                                                </div>
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Start year</label>:<s:textfield  id="edu_start_year"  name="edu_start_year"  cssClass="inputStyle "  onfocus="removeResultMessage()"  pattern="{10}" required="true" oninvalid="setCustomValidity('Must be valid date')"   onchange="try{setCustomValidity('')}catch(e){}"  onkeypress="return enterDateRepository();" />
                                                    <label class="labelStyle">End year</label>:<s:textfield  id="edu_end_year"  name="edu_end_year" cssClass="inputStyle "  onfocus="removeResultMessage()" pattern="{10}" required="true" oninvalid="setCustomValidity('Must be valid date')"   onchange="try{setCustomValidity('')}catch(e){}"  onkeypress="return enterDateRepository();" />
                                                </div>
                                                <div class="inner-addEduDiv-elements">
                                                    <label class="labelStyle">Percentage</label>:<s:textfield  id="edu_percentage"  name="percentage" cssClass="inputStyle" maxlength="5" onfocus="removeResultMessage()"/>
                                                    <label class="labelStyle">Specialization</label>:<s:textfield  id="edu_specialization"  name="edu_specialization" cssClass="inputStyle" onfocus="removeResultMessage()" />
                                                </div>
                                                <div  class="inner-addEduDiv-elements">

                                                    <s:submit cssClass="pull-right btn cssbutton_update" value="Update" onclick="return editQualificationDetails()" />
                                                    <br/>
                                                </div>

                                            </div>
                                        </form>
                                    </div> 


                                </div>
                            </div>

                            <!-- skill overlay for add record start here....-->


                            <!-- Skill edit  pop-up overlay  start -->
                            <div class="col-lg-12  " > 
                                <div id="skilledit_popup">
                                    <div id="skilleditBoxOverlay" >
                                        <div style="background-color: #3bb9ff ; padding: 0px">
                                            <table>
                                                <tr><td><h4 style=""><font color="#ffffff">&nbsp;&nbsp;Edit Skill Details&nbsp;&nbsp; </font></h4></td>
                                                </tr>
                                                <span class=" pull-right"><h5><a href="" class="skilledit_popup_close" onclick="removeResultMessageAll()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                            </table>
                                        </div>
                                        <div>
                                            <form action="#" theme="simple" >
                                                <div>
                                                    <span><EditSkillOverlayResult></EditSkillOverlayResult></span>

                                                    <s:hidden value="%{userid}" name="userid"/>
                                                    <s:hidden id="skill_id" name="skill_id" /> <br> 
                                                    <div class="inner-addSkillDiv-elements textfieldLabel">
                                                        <table>
                                                            <span><error></error></span>
                                                            <s:textfield  label="Skill Name"  name="skill_name"  id="skill_name"  cssClass=" form-control" onfocus="removeResultMessage()"/>
                                                            <s:textfield name="skill_rate" label="Rate your skill (out of 10)" id="skill_rate"  cssClass="form-control" onkeyup="HandleSkillRateEdit()" onkeypress="return event.charCode === 0 || /\d/.test(String.fromCharCode(event.charCode));" onfocus="removeResultMessage()"/>
                                                            <s:textarea name="comments"  label="Comments" id="comments"  style="background-color:white;color:black;border:solid 1px #B0B0B0 ;" cssClass="form-control"  onfocus="removeResultMessage()" onkeyup="checkCharacters(this)"/>
                                                            <s:submit cssClass="col-sm-offset-4 btn cssbutton" value="Save"  onclick="return seteditSkillDetails()"   ></s:submit>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div> 
                                        </div>
                                    </div>
                                    <!-- Skill edit  pop-up overlay  end -->

                                    <!-- Skill add  pop-up overlay  start -->
                                    <div id="skilladd_popup">
                                        <div id="skilladdBoxOverlay">
                                            <div class="backgroundcolor">
                                                <table>
                                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Add Skills&nbsp;&nbsp; </font></h4></td>
                                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="skilladd_popup_close" onclick="removeResultMessageAll()"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                            </table>
                                        </div>
                                        <div class="textfieldLabel margins">
                                            <form action="#" theme="simple" name="skillForm">
                                                <div>

                                                    <table>
                                                        <span><addSkillOverlayResult></addSkillOverlayResult></span>
                                                        <span><error></error></span>
                                                        <s:textfield  name="s_name" label="Skill Name" id="s_name" placeholder="Skill Name" cssClass="form-control" onfocus="removeResultMessage()"/>
                                                        <s:textfield name="s_rate" label="Rate your skill(out of 10)" id="s_rate" placeholder="Rate your skill" cssClass="form-control"  onkeyup="HandleSkillRateADD()" onkeypress="return event.charCode === 0 || /\d/.test(String.fromCharCode(event.charCode));" onfocus="removeResultMessage()"/>
                                                        <s:textarea name="s_comments" label="Comments"  id="s_comments" placeholder="comments" style="background-color:white;color:black;border:solid 1px #B0B0B0 ;" cssClass="form-control" onfocus="removeResultMessage()" />
                                                    </table>
                                                    <br/>
                                                    <s:reset cssClass=" col-sm-offset-6 btn cssbutton" value="Clear" theme="simple" />
                                                    <s:submit cssClass="btn cssbutton" value="Add Skill" theme="simple" onclick="return addSkills()" />

                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <!-- Skill add  pop-up overlay end -->
                            <!-- skill overlay for add record end here....-->           


                            <!-- overlays ended here -->

                        </div>
                    </div>

                    <!-- hear Code end -->
                </div>
            </div>


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



        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>