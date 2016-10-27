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
        <title>ServicesBay :: Requirements&nbsp;Add&nbsp;Page</title>

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
                $('#skillCategoryValue').change(function(e){
               
                  
                    removeErrorMessages();
                });
            });
        </script>
        <script>
            $(document).ready(function() {
                $('#preSkillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
                $('#preSkillCategoryValue').change(function(e){
                 
                    removeErrorMessages();
                });
            });
        </script>
        <script>
            function showHideDuration(){
                var RequirementTaxTerm=document.getElementById('RequirementTaxTerm').value;
                if(RequirementTaxTerm=='CO'){
                    document.getElementById('duration').style.display = "block";
                }else{
                    document.getElementById('duration').style.display = "none";
                }
            }
            
        </script>
    </head>

    <body style="overflow-x: hidden" oncontextmenu="return false" onload="showHideDuration();doOnLoadRequirement();">
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
                                    <div class="col-sm-14 ">
                                        <div class="" id="selectivityProfileBox" style="float: left; margin-top: 5px">
                                            <br>
                                            <div class=""  style="float: left  ">
                                                <label class="labelStyle" id="labelLevelStatusReq"> Account&nbsp;Name :</label>                                          
                                                <span style="color: #FF8A14;"> <s:property value="accountName"/></span>
                                            </div>
                                            <br>
                                            <%
                                                String orgId = session.getAttribute(ApplicationConstants.ORG_ID).toString();
                                            %>
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <font color="#ffffff"> Add&nbsp;Requirements </font>
                                                        <s:if test="accountFlag=='MyRequirements'">
                                                            <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                <s:param name="accountFlag">MyRequirements</s:param>
                                                            </s:url>
                                                            <span class="pull-right"><s:a href='%{#myUrl}' id="reqAddBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                        </s:if>
                                                        <s:elseif test="customerFlag=='customer'">
                                                            <s:url var="myUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                                                <s:param name="orgid"><%=orgId%></s:param> 
                                                                <s:param name="customerFlag">customer</s:param>
                                                                <s:param name="accountFlag">MyRequirements</s:param>
                                                            </s:url>
                                                            <span class="pull-right"><s:a href='%{#myUrl}' id="reqAddBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                        </s:elseif> 
                                                        <s:else>
                                                            <s:url var="myUrl" action="recruitment/consultant/getAllRequirementList.action">
                                                                <s:param name="orgid"><%=orgId%></s:param> 
                                                            </s:url>
                                                            <span class="pull-right"><s:a href='%{#myUrl}' id="reqAddBackButton"><i class="fa fa-undo"></i></s:a></span> 
                                                        </s:else>
                                                    </h4>
                                                </div>
                                            </div>
                                            <div class="col-sm-12">
                                                <s:form  name="requirementAdd" theme="simple" >
                                                    <br>
                                                    <span cellspacing="30">
                                                        <span><editrequirementerror></editrequirementerror></span>
                                                        <reqAdded></reqAdded>
                                                        <div>
                                                            <span><requirement></requirement></span>
                                                            <div id="loadingAddReq" class="loadingImg" style="display: none">
                                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                                            </div>
                                                            <div class="col-sm-3 required" id=""> 
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Title </label> <s:textfield cssClass="form-control" id="RequirementName" type="text" value="%{requirementVTO.RequirementName}"  placeholder="" onfocus="removeErrorMessages()" maxLength="50"/>
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Positions </label> <s:textfield cssClass="form-control" id="RequirementNoofResources" type="text" value="%{requirementVTO.RequirementNoofResources}"  onfocus="removeErrorMessages()" onkeypress="return acceptNumbers(event)" maxLength="11"/>
                                                                <div id="duration" style="display: none; ">
                                                                    <label class="labelStylereq" style="color:#56a5ec;"> Duration </label>
                                                                    <div class="form-group input-group" style="display:list-item;list-style-type: none;margin: 0">
                                                                        <s:textfield cssClass="form-control textMessageBox numeric_field" id="RequirementDuration" type="text" value="%{requirementVTO.RequirementDuration}"  placeholder="" onfocus="removeErrorMessages()" maxLength="10" onkeypress="return durationValidation(event)"/>
                                                                        <s:select  id="requirementDurationDescriptor" value="%{requirementVTO.requirementDurationDescriptor}" list="#@java.util.LinkedHashMap@{'Hours':'Hours','Weeks':'Weeks','Months':'Months'}" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control select_duration "  onfocus="removeErrorMessages()" style=""/> 
                                                                    </div>
                                                                </div>
                                                                <label class="labelStylereq " style="color:#56a5ec;">Req.Category</label>
                                                                <s:select id="reqCategoryValue" name="reqCategoryValue" cssClass="SelectBoxStyles form-control" headerValue="Select Category" headerKey="-1"  theme="simple" list="%{reqCategory}" value="%{requirementVTO.reqCatgory}" onchange="addRequirementSkillChange();"/>
                                                            </div>
                                                            <div class="col-sm-3 required" id="">
                                                                <label class="labelStyle" id="labelLevelStatusReq">From </label> <div class="calImage"><s:textfield cssClass="form-control"  id="RequirementFrom" placeholder="StartDate" value="%{requirementVTO.RequirementFrom}" onkeypress="return enterDateRepository();" cssStyle="z-index: 10000004;" onfocus="removeErrorMessages()"><i class="fa fa-calendar"></i></s:textfield></div>
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Min&nbsp;Rate </label>
                                                                <div class="input-group">
                                                                    <span class="input-group-addon "style="padding-top: 5px" >$</span>
                                                                    <s:textfield cssClass="form-control" id="RequirementTargetRate" type="text" value="%{requirementVTO.RequirementTargetRate}"   onfocus="removeErrorMessages()" onkeypress="return RequirementMinRate(event)" maxLength="10"/>
                                                                    <span class="input-group-addon" style="padding-top: 5px">/Hr</span>
                                                                </div>
                                                                <label class="labelStyle" id="labelLevelStatusReq">Billing&nbsp;Contact </label> <s:select  id="billingContact" name="buildingContact"  list="recruitmentMap" headerKey="DF" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.billingContact}"/></td>      
                                                                <s:hidden  id="RequirementType" list="#@java.util.LinkedHashMap@{'VR':'Vendor'}"  value="VR" headerKey="" headerValue="--select--" cssClass="selectBoxStyle form-control"/>
                                                                <s:hidden id="accountSearchID" name="accountSearchID" value="%{accountSearchID}"/>
                                                            </div>
                                                            <div class="col-sm-3 required" id="">       
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Type </label> <s:select  id="RequirementTaxTerm"  list="#@java.util.LinkedHashMap@{'CO':'Contract','PE':'Permanent'}" headerKey="CO"  cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementTaxTerm}" onchange="showHideDuration();disableFields()"/>
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Max&nbsp;Rate </label>
                                                                <div class=" input-group">
                                                                    <span class="input-group-addon "style="padding-top: 5px" >$</span>
                                                                    <s:textfield cssClass="form-control" id="requirementMaxRate" type="text" value="%{requirementVTO.requirementMaxRate}"   onfocus="removeErrorMessages()" onkeypress="return RequirementMaxRate(event)" maxLength="5"></s:textfield>
                                                                    <span class="input-group-addon" style="padding-top: 5px">/Hr</span>
                                                                </div> 
                                                                <label class="labelStyle" id="labelLevelStatusReq">Location </label> <s:select  id="RequirementLocation" list="#@java.util.LinkedHashMap@{'ON':'Onsite','OF':'Offsite','OS':'Offshore'}" headerKey="OF"  cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementLocation}"/></td>      
                                                            </div>
                                                            <div class="col-sm-3  required" >       
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Approver</label> <s:select  id="RequirementContact1" list="recruitmentMap" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" onchange="getid()" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementContact1}"/>
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Requisitioner</label> <s:select id="RequirementContact2" list="recruitmentMap" headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementContact2}"/>
                                                                <label class="labelStyle" id="labelLevelStatusReq"> Req.Exp.</label><s:select  id="RequirementYears" list="experienceMap"   headerKey="-1" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" value="%{requirementVTO.RequirementExp}"/>
                                                                <s:hidden  id="RequirementStatus" value="O" list="#@java.util.LinkedHashMap@{'O':'Open'}" headerKey="DF" headerValue="--select--" cssClass="SelectBoxStyles form-control" onfocus="removeErrorMessages()" />
                                                            </div>
                                                        </div>
                                                        <div>                                                      
                                                        </div>
                                                        <div class="col-sm-7 "></div>
                                                        <div class="col-sm-12 required">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Qualification </label> <s:textarea name="requirementQualification" id="requirementQualification" cssClass="commentsStyle" value="%{requirementVTO.RequirementQualification}" placeholder="Enter Qualification Here" cols="127" rows="3" onkeyup=" QualificationCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                            <div class="charNum pull-right" id="req_Qualification"></div>
                                                        </div>
                                                        <div class="col-sm-12 required">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Description </label> <s:textarea name="RequirementJobdesc" id="RequirementJobdesc" cssClass="commentsStyle" value="%{requirementVTO.RequirementJobdesc}" placeholder="Enter Requirement job description Here" cols="127" rows="3" onkeyup=" JobCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                            <div class="charNum pull-right" id="JobcharNum"></div>
                                                        </div>
                                                        <div class="col-sm-12 required">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Responsibilities </label> <s:textarea name="RequirementResponse" id="RequirementResponse" cssClass="commentsStyle" value="%{requirementVTO.RequirementResponse}" placeholder="Enter Requirement Responsibilities Here" cols="127" rows="3" onkeyup=" ResponseCheckCharacters(this)"  onfocus="removeErrorMessages()"/>
                                                            <div class="charNum pull-right" id="ResponsecharNum"></div>
                                                        </div>    
                                                        <div class="col-sm-14">
                                                            <div class="col-sm-6 ">
                                                                <span class="required">
                                                                    <label class="labelStyle skills" id="labelLevelStatusReq">Skill&nbsp;Set </label> <s:select cssClass="" name="skillCategoryValue"  id="skillCategoryValue" list="{}" multiple="true" onfocus="removeErrorMessages()"/> 
                                                                </span>
                                                            </div>
                                                            <div class="col-sm-6 ">
                                                                <label class="labelStyle" id="labelLevelStatusReq">Preferred&nbsp;Skills  </label> <s:select cssClass="" name="preSkillCategoryValue"  id="preSkillCategoryValue" list="preSkillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()" value="%{requirementVTO.preSkillSetList}"/> 
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-12 ">  
                                                            <div class="col-sm-6">
                                                                <div class="charNum pull-right" id="SkillcharNum"></div></div>
                                                            <div class="col-sm-6">
                                                                <div class="charNum pull-right" id="PreferredSkillcharNum"></div>
                                                            </div></div>
                                                        <div class="col-sm-12">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Comments </label> <s:textarea name="RequirementComments" id="RequirementComments" cssClass="commentsStyle" value="%{requirementVTO.RequirementComments}" placeholder="Enter Comments Here" cols="127" rows="3" onkeyup="CommentsCheckCharacters(this)"   onfocus="removeErrorMessages()"/>
                                                            <div class="charNum pull-right" id="CommcharNum"></div>
                                                        </div>
                                                        <div class="col-sm-6 pull-right "> 
                                                            <div class="col-sm-2 contact_search pull-right">
                                                                <input type="button"  id="requirementClear" style="margin:5px 0px;" class="add_searchButton  form-control fa fa" value="&#xf12d; Clear" theme="simple"  onfocus="removeErrorMessages()" onclick="clearFormFields()"/> &nbsp;    
                                                            </div>
                                                            <div class="col-sm-2 contact_search pull-right">
                                                                <s:submit type="button" id="requirementSubmit" cssStyle="margin:5px 0px;" cssClass="add_searchButton fa fa-check-circle-o  form-control"  onclick="return addRequirement()" value="submit" theme="simple"/>
                                                            </div>
                                                        </div>        
                                                    </s:form>       
                                            </div>
                                        </div>
                                    </div>
                                    <%--close of future_items--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- content end -->
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer>        
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
    </div>
</body>
</html>



