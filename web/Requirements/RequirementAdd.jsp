<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Service Bay :: Requirements Add Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive_queries.css"/>">
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

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>
        <style>


            .numeric_field{
                width: 30%!important ;
                padding: 0 5px !important;
                float: left;
            }

            .select_duration{
                width: 70% !important;
            }
        </style>
        <script>
            $(document).ready(function() {
                $('#skillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            });
            
        </script>

        <script>
            $(document).ready(function() {
                $('#preSkillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            });
            
        </script>

        <script>
            function showHideDuration(){
                // alert("hii")
                var RequirementTaxTerm=document.getElementById('RequirementTaxTerm').value;
               
                if(RequirementTaxTerm=='CO'){
                
                    document.getElementById('duration').style.display = "block";
                    document.getElementById('billingtype1').style.display = "block";
                    // document.getElementById('durationdesr').style.display = "block";
                }else{
                    document.getElementById('duration').style.display = "none";
                    document.getElementById('billingtype1').style.display = "none";
                    //document.getElementById('durationdesr').style.display = "none";

                    
                }
            }
            
        </script>

    </head>

    <body style="overflow-x: hidden" onload="showHideDuration();doOnLoadRequirement();">
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
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="selectivityProfileBox" style="float: left; margin-top: 5px">
                                    <br>
                                    <div class=""  style="float: left  ">
                                        <label class="labelStyle" id="labelLevelStatusReq"> Account Name :</label>                                          
                                        <%--
                                            <s:url var="myUrl" action="acc/viewAccount.action">
                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                            <s:param name="accFlag">accDetails</s:param>
                                            </s:url>
                                        <s:a href='%{#myUrl}' style="color: #0000FF;"><s:property value="accountName"/></s:a>
                                        --%>
                                        <span style="color: #FF8A14;"> <s:property value="accountName"/></span>
                                    </div>
                                    <br>
                                    <%
                                        String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();
                                    %>
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font color="#ffffff"> Add Requirements </font>
                                                <%-- <% String flag = "reqSearch";
                                                 %>
                                                 <s:url var="myUrl" action="acc/viewAccount.action">

                                                    <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param> 
                                                    <s:param name="accFlag"><%=flag%></s:param>

                                                </s:url>

                                                <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span> 
                                                --%>
                                                <s:if test="accountFlag=='MyRequirements'">
                                                    <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                        <s:param name="accountFlag">MyRequirements</s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span> 
                                                    </s:if>
                                                    <s:elseif test="customerFlag=='customer'">
                                                        <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                            <s:param name="orgid"><%=orgId%></s:param> 
                                                            <s:param name="customerFlag">customer</s:param>
                                                        <s:param name="accountFlag">MyRequirements</s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span> 
                                                    </s:elseif> 
                                                    <s:else>
                                                        <s:url var="myUrl" action="recruitment/consultant/getAllRequirementList.action">
                                                            <s:param name="orgid"><%=orgId%></s:param> 
                                                        </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span> 
                                                    </s:else>
                                            </h4>
                                        </div>

                                    </div>
                                    <div class="col-sm-12">
                                        <s:form  name="requirementAdd" theme="simple" >

                                            <br>
                                            <span cellspacing="30">
                                                <span><editrequirementerror></editrequirementerror></span>

                                                <span><requirement></requirement></span>

                                                <div class="col-lg-12 required" id=""> 


                                                    <div class="col-sm-4"> <label class="labelStyle" id="labelLevelStatusReq"> Title </label> <s:textfield cssClass="form-control" id="RequirementName" type="text" value="%{requirementVTO.RequirementName}"  placeholder="Title" onfocus="removeErrorMessages()" maxLength="50"/></div>
                                                    <div class="col-sm-4">  <label class="labelStyle" id="labelLevelStatusReq"> Positions </label> <s:textfield cssClass="form-control" id="RequirementNoofResources" placeholder="Positions" type="text" value="%{requirementVTO.RequirementNoofResources}"  onfocus="removeErrorMessages()" onkeypress="return acceptNumbers(event)" maxLength="11"/></div>

                                                    <%--<label class="labelStyle" id="labelLevelStatusReq"> Duration: </label> <s:textfield cssClass="form-control textMessageBox" id="RequirementDuration" type="text" value="%{requirementVTO.RequirementDuration}"  placeholder="" onfocus="removeErrorMessages()" maxLength="10" onkeypress="return durationValidation(event)"/>--%>
                                                    <%-- <label class="labelStyle ReqStyleEdit" style="margin-left: 10px"/>Months</label>--%>
                                                    <div id="duration" style="display: none;" class="col-sm-4">
                                                        <div class="">  <label class="labelStylereq" style="color:#56a5ec;"> Duration </label></div>

                                                        <div class="form-group input-group col-sm-12 duration_field" style="margin-bottom:0">
                                                            <s:textfield cssClass="form-control textMessageBox numeric_field" id="RequirementDuration" placeholder="Duration" type="text" value="%{requirementVTO.RequirementDuration}" onfocus="removeErrorMessages()" maxLength="10" onkeypress="return durationValidation(event)"/>
                                                            <s:select  id="requirementDurationDescriptor" value="%{requirementVTO.requirementDurationDescriptor}" list="#@java.util.LinkedHashMap@{'Hours':'Hours','Weeks':'Weeks','Months':'Months'}" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control select_duration "  onfocus="removeErrorMessages()" style=""/> 
                                                        </div>

                                                    </div>

                                                    <div class="col-sm-4">   <label class="labelStylereq " style="color:#56a5ec;">Req.Category</label>
                                                        <s:select id="reqCategoryValue" name="reqCategoryValue" cssClass="SelectBoxStyles form-control"  theme="simple" list="%{reqCategory}" /></div>

                                                    <div class="col-sm-4">   <label class="labelStyle" id="labelLevelStatusReq">From </label> <s:textfield cssClass="form-control"  id="RequirementFrom" placeholder="StartDate" value="%{requirementVTO.RequirementFrom}" onkeypress="return enterDateRepository();" cssStyle="z-index: 10000004;" onfocus="removeErrorMessages()"/></div>

                                                    <div class="col-sm-4">         <label class="labelStyle" id="labelLevelStatusReq"> Min Rate </label>
                                                        <%--<label class="labelStyle" id="labelLevelStatusReq"> Presales 1 :</label> <s:select  id="RequirementPresales1"  list="presalesMap" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/>
                                                         <label class="labelStyle" id="labelLevelStatusReq"> Practice :</label> <s:select  id="RequirementPractice" value="" list="#@java.util.LinkedHashMap@{'1':'SAP','2':'J2EE','3':'Integration','4':'Portable','5':'Commerce','6':'B2B','7':'Other'}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/>
                                                         
                                                        --%>
                                                        <div class="input-group">
                                                            <span class="input-group-addon "style="padding-top: 5px" >$</span>


                                                            <s:textfield cssClass="form-control" id="RequirementTargetRate" placeholder="Min Rate" type="text" value="%{requirementVTO.RequirementTargetRate}"   onfocus="removeErrorMessages()" onkeypress="return RequirementMinRate(event)" maxLength="10"/>
                                                            <span class="input-group-addon" style="padding-top: 5px">/Hr</span>
                                                        </div>
                                                    </div>
                                                    <div id="billingtype1" style="display: none;">
                                                        <div class="col-sm-4">    <label class="labelStyle" id="labelLevelStatusReq">Billing&nbsp;Type:</label> <s:select  id="billingtype" list="#@java.util.LinkedHashMap@{'After Work':'After Work','On Work':'On Work'}"  value="%{requirementVTO.billingtype}" cssClass="SelectBoxStyles form-control" /> </div>     
                                                    </div>
                                                    <br>
                                                    <%--<label class="labelStyle" id="labelLevelStatusReq">Hr/week/month:</label> <s:select  id="requirementDurationDescriptor" list="#@java.util.LinkedHashMap@{'Hours':'Hours','Weeks':'Weeks','Months':'Months'}"  value="%{requirementVTO.requirementDurationDescriptor}" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" /> --%>     
                                                    <div class="col-sm-4">    <label class="labelStyle" id="labelLevelStatusReq">Billing Contact </label> <s:select  id="billingContact" name="buildingContact"  list="recruitmentMap" headerKey="DF" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.billingContact}"/></div>      


                                                    <%-- <label class="labelStyle" id="labelLevelStatusReq">Reject Reason :</label> <s:select  id="RequirementReason" value="" list="#@java.util.LinkedHashMap@{'DR':'Delayed Resume','NC':'Not Right Candidate','PH':'Price Too High','LB':'Lack of Budges','NR':'No Reason'}" headerKey="DF" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/>
                                                    --%>


                                                    <s:hidden  id="RequirementType" list="#@java.util.LinkedHashMap@{'VR':'Vendor'}"  value="VR" headerKey="" headerValue="--select--" cssClass="selectBoxStyle form-control"/>
                                                    <s:hidden id="accountSearchID" name="accountSearchID" value="%{accountSearchID}"/>






                                                    <%-- <label class="labelStyle" id="labelLevelStatusReq">Requirement To :</label><s:textfield cssClass="form-control " value="" id="RequirementTo" placeholder="EndDate" onkeypress="return enterDateRepository();" onfocus="removeErrorMessages()"/>--%>
                                                    <div class="col-sm-4">    <label class="labelStyle" id="labelLevelStatusReq"> Type </label> <s:select  id="RequirementTaxTerm"  list="#@java.util.LinkedHashMap@{'CO':'Contract','PE':'Permanent'}" headerKey="CO"  cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementTaxTerm}" onchange="showHideDuration();disableFields()"/></div>
                                                    <%--<label class="labelStyle" id="labelLevelStatusReq"> Presales 1 :</label> <s:select  id="RequirementPresales1"  list="presalesMap" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/>
                                                     <label class="labelStyle" id="labelLevelStatusReq"> Practice :</label> <s:select  id="RequirementPractice" value="" list="#@java.util.LinkedHashMap@{'1':'SAP','2':'J2EE','3':'Integration','4':'Portable','5':'Commerce','6':'B2B','7':'Other'}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/>
                                                     
                                                    --%>

                                                    <div class="col-sm-4">    <label class="labelStyle" id="labelLevelStatusReq"> Max Rate </label>
                                                        <div class=" input-group">
                                                            <span class="input-group-addon "style="padding-top: 5px" >$</span>

                                                            <s:textfield cssClass="form-control" id="requirementMaxRate" placeholder="Max Rate" type="text" value="%{requirementVTO.requirementMaxRate}"   onfocus="removeErrorMessages()" onkeypress="return RequirementMaxRate(event)" maxLength="5"></s:textfield>
                                                                <span class="input-group-addon" style="padding-top: 5px">/Hr</span>
                                                            </div> 
                                                        </div>
                                                        <div class="col-sm-4">          <label class="labelStyle" id="labelLevelStatusReq">Location </label> <s:select  id="RequirementLocation" list="#@java.util.LinkedHashMap@{'ON':'Onsite','OF':'Offsite','OS':'Offshore'}" headerKey="OF"  cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementLocation}"/></div>      



                                                    <div class="col-sm-4">     <label class="labelStyle" id="labelLevelStatusReq"> Approver</label> <s:select  id="RequirementContact1" list="recruitmentMap" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" onchange="getid()" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementContact1}"/></div>
                                                    <div class="col-sm-4">       <label class="labelStyle" id="labelLevelStatusReq"> Requisitioner</label> <s:select id="RequirementContact2" list="recruitmentMap" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementContact2}"/></div>
                                                    <div class="col-sm-4">    <label class="labelStyle" id="labelLevelStatusReq"> Req.Exp.</label><s:select  id="RequirementYears" list="experienceMap"   headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementExp}"/></div>
                                                    <%-- <tr>
                                                        <td> <label class="labelStyle" id="labelLevelStatusReq"> Presales 2 :</label> <s:select  id="RequirementPresales2"  list="presalesMap" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/></td>
                                                    </tr>
                                                    <label class="labelStyle" style=" margin-left:5px;" id="labelLevelStatusReq"> Requirement Status :</label>--%>


                                                    <s:hidden  id="RequirementStatus" value="O" list="#@java.util.LinkedHashMap@{'O':'Open'}" headerKey="DF" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" />

                                                    <%-- <label class="labelStyle" id="labelLevelStatusReq"> Contact No. :</label> <s:textfield cssClass="form-control ReqinputStyle" id="NoofResources" type="text" value="" name="" placeholder=""/>
                                                    <label class="labelStyle" id="labelLevelStatusReq"> Country :</label><s:select name="" id="Practice" value="" list="#@java.util.LinkedHashMap@{'S':'SAP','J':'J2EE','I':'Integration','P':'Portable','C':'Commerce','B':'B2B','O':'Other'}" headerKey="DF" headerValue="--select--" cssClass="selectBoxStyle form-control"/>
                                                     <label class="labelStyle" id="labelLevelStatusReq"> State :</label> <s:select name="" id="Practice" value="" list="#@java.util.LinkedHashMap@{'S':'SAP','J':'J2EE','I':'Integration','P':'Portable','C':'Commerce','B':'B2B','O':'Other'}" headerKey="DF" headerValue="--select--" cssClass="selectBoxStyle form-control"/>
                                                    <label class="labelStyle" id="labelLevelStatusReq"> Address :</label> <s:textarea cssClass="form-control ReqinputStyle" id="RequirementName" type="text" value="" name="" placeholder="Address"/>--%>





                                                    <%-- <label style=" margin-left: 10px;font-size: 1.6em;font-weight: normal ">
                                                <br> Requirement   Address   :                                                     
                                            </label>     
                                                    --%>


                                                    <%-- <div class="updateCss ReqinputStyle ">   

                                                    <table>
                                                        <label class="labelStyle" id="labelLevelStatusReq"> Contact No. :</label> <s:textfield cssClass="form-control " id="RequirementContactNo" type="text" value=""  placeholder="" onkeyup="contactValidation()" pattern="{14}" onfocus="removeErrorMessages()"/>
                                                    </table>
                                                </div> 
                                                <div class="updateCss ReqinputStyle ">     
                                                    <table>
                                                        <label class="labelStyle" id="labelLevelStatusReq"> Country :</label><s:select  id="RequirementCountry" value="countryMap" list="countryMap" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onchange="stateChange()" onfocus="removeErrorMessages()"/>
                                                    </table>
                                                </div> 
                                                <div class="updateCss ReqinputStyle ">     
                                                    <table>
                                                        <label class="labelStyle" id="labelLevelStatusReq"> State :</label> <s:select  id="RequirementState" value="" list="#@java.util.LinkedHashMap@{}" headerKey="-1" headerValue="--select--" cssClass="selectBoxStyle form-control" onfocus="removeErrorMessages()"/>
                                                    </table> 
                                                </div>        
                                                    --%>
                                                    <%--  <div class="updateCss Require" >
                                                          <label class="labelStyle" id="labelLevelStatusReq">Address :</label> <s:textarea  id="RequirementAddress" cssClass="commentsStyle" value="" placeholder="Enter Skills Here" cols="127" rows="3" onkeyup=" checkCharacters(this)" onfocus="removeResultMessage()" />
                                                          <div class="charNum" id="AddresscharNum"></div>
                                                      </div>  --%>
                                                    <div class="col-sm-12 ">
                                                        <label class="labelStyle" id="labelLevelStatusReq">Qualification </label> <s:textarea name="requirementQualification" id="requirementQualification" cssClass="commentsStyle" value="" placeholder="Enter Qualification Here" cols="127" rows="3" onkeyup=" QualificationCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                        <div class="charNum pull-right" id="req_Qualification"></div>
                                                    </div>
                                                    <div class="col-sm-12"> <label class="labelStyle" id="labelLevelStatusReq">Description </label> <s:textarea name="RequirementJobdesc" id="RequirementJobdesc" cssClass="commentsStyle" value="%{requirementVTO.RequirementJobdesc}" placeholder="Enter Requirement job description Here" cols="127" rows="3" onkeyup=" JobCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                        <div class="charNum pull-right" id="JobcharNum"></div>
                                                    </div>
                                                    <div class="col-sm-12"><label class="labelStyle" id="labelLevelStatusReq">Responsibilities </label> <s:textarea name="RequirementResponse" id="RequirementResponse" cssClass="commentsStyle" value="%{requirementVTO.RequirementResponse}" placeholder="Enter Requirement Responsibilities Here" cols="127" rows="3" onkeyup=" ResponseCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                        <div class="charNum pull-right" id="ResponsecharNum"></div>
                                                    </div>
</div>

                                                    <div class="col-sm-12">
                                                        <div class="col-sm-6">
                                                            <span class="required">
                                                                <label class="labelStyle " id="labelLevelStatusReq">Skill Set </label> <s:select cssClass="commentsStyle" name="skillCategoryValue"  id="skillCategoryValue" list="skillValuesMap" multiple="true" onfocus="removeErrorMessages()" value="%{requirementVTO.skillSetList}"/> 
                                                            </span>

                                                        </div>
                                                        <div class="col-sm-6">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Preferred Skills  </label> <s:select cssClass="commentsStyle" name="preSkillCategoryValue"  id="preSkillCategoryValue" list="preSkillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()" value="%{requirementVTO.preSkillSetList}"/> 
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-12">

                                                        <%--<div class="col-sm-14">
                                                              <div class="col-lg-6 required">
                                                                  <label class="labelStyle" id="labelLevelStatusReq">Skill Set </label> <s:textarea name="RequirementSkills" id="RequirementSkills" cssClass="commentsStyle" value="%{requirementVTO.RequirementSkills}" placeholder="Enter Skills Here" cols="127" rows="3" onkeyup=" SkillCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                              </div> 
                                                              <div class="col-lg-6">
                                                                  <label class="labelStyle" id="labelLevelStatusReq">Preferred Skills  </label> <s:textarea name="RequirementPreferredSkills" id="RequirementPreferredSkills" cssClass="commentsStyle" value="%{requirementVTO.RequirementPreferredSkills}" placeholder="Enter prefered Skills Here" cols="127" rows="3" onkeyup=" PreferredSkillCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                              </div>
                                                          </div>--%>
                                                        <div class="col-lg-12 ">  
                                                            <div class="col-lg-6">
                                                                <div class="charNum pull-right" id="SkillcharNum"></div></div>
                                                            <div class="col-lg-6">
                                                                <div class="charNum pull-right" id="PreferredSkillcharNum"></div>
                                                            </div></div>

                                                        <div class="col-sm-12">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Comments </label> <s:textarea name="RequirementComments" id="RequirementComments" cssClass="commentsStyle" value="%{requirementVTO.RequirementComments}" placeholder="Enter Comments Here" cols="127" rows="3" onkeyup="CommentsCheckCharacters(this)"   onfocus="removeErrorMessages()"/>
                                                            <div class="charNum pull-right" id="CommcharNum"></div>
                                                        </div>



                                                        <div class="col-sm-12"> 

                                                            <div class="col-sm-2 pull-right">
                                                                <s:reset type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton  form-control fa fa-eraser" value="Clear" theme="simple"  onfocus="removeErrorMessages()"/> &nbsp;    
                                                            </div>
                                                            <div class="col-sm-2 pull-right">
                                                                <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton fa fa-check-circle-o  form-control"  onclick="return addRequirement()" value="submit" theme="simple"/>
                                                            </div>
                                                        </div>        
                                                    </s:form>       


                                                </div>
                                            
                                    </div>

                                    <%--close of future_items--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content end -->
        </section>


        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer>        
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>



