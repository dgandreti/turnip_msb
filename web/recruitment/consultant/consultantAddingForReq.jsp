<%-- 
    Document   : consultantAddingForReq
    Created on : Jul 20, 2015, 6:51:13 PM
    Author     : praveen<pkatru@miraclesoft.com>
--%>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Consultant Adding For Requirement</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/requirementStyle.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/requirement.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <%--script type="text/javascript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <style>
            .selectivity-dropdown{
                top:60px !important;
                height: 110px !important;
            }

            .selectivity-dropdown{
                width:15em !important;

            }

            .selectivity-results-container{
                height:110px !important;
            }
        </style>

    </head>
    <body oncontextmenu="return false" style="overflow-x: hidden">
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
                    <div  class="container">
                        <div class="row">
                            <!-- content start -->
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <font color="#ffffff">Add Consultant For Job </font> 
                                                    <s:url var="myUrl" action="getLoginUserRequirementList.action">
                                                        <s:param name="accountFlag">MyRequirements</s:param> 
                                                        <s:param name="orgid"><s:property value="%{orgid}"/></s:param> 
                                                        <s:param name="vendor">yes</s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><i class="fa fa-undo"></i></s:a></span>

                                                </h4>

                                            </div>

                                        </div>

                                        <div>
                                            <div class="pull-left" style="float: left; margin-top:0px;margin-bottom: -7px;">
                                                <label class=""> Job Title :</label>                                         
                                                <span style="color:#FF8A14;"><s:property value="%{jobTitle}"/></span>
                                            </div>
                                            <div class="pull-right" style="float: left; margin-top:0px;margin-bottom: -7px;">
                                                <label class=""> Job ID :</label>                                         
                                                <span style="color:#FF8A14;"><s:property value="%{jdId}"/></span>
                                            </div>
                                            <br>
                                            <div class="pull-left" style="float: left; margin-top:0px;margin-bottom: -7px;">
                                                <label class=""> Rate(Min - Max) :</label>                                         
                                                <span style="color:#FF8A14;">$<s:property value="%{targetRate}"/>/Hr</span><font style="color: #FF8A14"> -</font> <span style="color: #FF8A14;">$<s:property value="%{maxRate}"/>/Hr</span>
                                                
                                            </div>
                                        </div>
                                                <br/>
                                              <br/>

                                            <s:if test="hasActionMessages()">
                                                <div style="margin-left: 23px;" >
                                                    <span id="actionMessage"><s:actionmessage cssClass="actionContactMessagecolor"/></span>
                                                </div>
                                            </s:if>
                                            <s:form action="storeProofData" theme="simple"  enctype="multipart/form-data" onsubmit="return addconsultantValidation();">
                                               
                                                <br>
                                                <span><e1></e1></span>
                                                <div class="col-sm-12">
                                                    <s:hidden name="reqId" id="reqId" value="%{requirementId}"/>

                                                    <s:hidden name="resourceType" id="resourceType"/>
                                                    <s:hidden name="jdId" id="jdId" value="%{jdId}"/>
                                                    <s:hidden name="jobTitle" id="jobTitle" value="%{jobTitle}"/>
                                                    <s:hidden name="orgId" id="orgid" value="%{orgid}"/>
                                                    <s:hidden name="targetRate" id="targetRate" value="%{targetRate}"/>
                                                    <s:hidden name="requirementMaxRate" id="requirementMaxRate" value="%{maxRate}"/> 

                                                    <div class="col-sm-4">
                                                        <label>Resource Type</label>
                                                        <s:select  id="contactType"  name="contactType" cssClass="form-control SelectBoxStyles "  theme="simple" list="#@java.util.LinkedHashMap@{'IC':'Consultant','VC':'Employee'}" tabindex="1" onchange="clearConsultantSubmissionFields();"/>
                                                    </div>
                                                    <div class="col-sm-4 required">
                                                        <label class="labelStyleAddCon">Email</label><s:textfield name="conEmail" id="conEmail" placeholder="Email" theme="simple" cssClass="form-control" onblur="getEmailExistance();" onclick="clearConultantAddOverlay()" tabindex="1" maxlength="60" />
                                                    </div>
                                                    <div class="col-sm-4 required">
                                                        <label class="labelStyleAddCon">Rate/Hr</label>
                                                        <div class="input-group">
                                                            <s:textfield name="ratePerHour" id="ratePerHour" placeholder="Rate/Hr" theme="simple" cssClass="form-control"  onkeyup="return ratePerHourValidation();" onclick="clearConultantAddOverlay()" tabindex="2" maxlength="10" ><span class="input-group-addon">$</span></s:textfield>
                                                            <span class="input-group-addon">/Hr</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStyleAddCon">SSN No</label><s:textfield name="ssnNo" id="ssnNo" placeholder="SSN No" theme="simple" cssClass="form-control" tabindex="3" maxlength="20"/>
                                                    </div>

                                                </div>
                                                <div class="col-lg-12">

                                                    <div class="col-lg-4">

                                                        <s:hidden id="proofType" name="proofType" value="N"/>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <div  id="ppId">
                                                            <s:hidden name="ppno" id="ppno" value="" theme="simple" cssClass="form-control"/>
                                                        </div>
                                                        <div  id="panId">
                                                            <s:hidden name="pan" id="pan" value="" theme="simple" cssClass="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-12">
                                                    <div id="skillField"  style="position: relative" class="col-sm-6 ">
                                                        <div class="required" >
                                                            <label  class="labelStylereq skilllist" style="margin-left:10px;">Skills</label>
                                                            <s:select cssClass="commentsStyle" name="skillCategoryValueList"  id="skillListValue" list="skillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()" tabindex="3" /> 
                                                            <s:hidden id="propsedSkills" name="propsedSkills" />
                                                            <s:hidden id="tempSkillList"  value="%{skillValuesMap}"/>
                                                        </div>  
                                                    </div>
                                                    <div class="col-sm-6"> <!--id="IsEmployee"-->
                                                        <label class="labelStylereq" style="margin-left:0px">Upload&nbsp;resume&nbsp;<font color="red">*</font></label><s:file  cssStyle="margin-left:  0px" name="file" id="file" cssClass="" onchange="return addConReqFileValidation()"     onclick="clearConultantAddOverlay()" tabindex="4" />
                                                        <span style="color:red;margin-left: 0px">Upload PDF or Doc or Docx file.</span>
                                                    </div>
                                                    <div class="form-group col-sm-12">
                                                        <label class="labelStyle" id="labelLevelStatusReq">Comments </label> <s:textarea name="vendorComments" id="vendorComments" cssClass="titleStyle" value="" placeholder="Enter Comments Here" rows="3" onkeyup=" commentsCheckCharacters(this)"/>
                                                    </div>
                                                </div>

                                                <div id="charNum"></div>

                                                <div class="col-sm-12">
                                                    <div class="col-sm-2 pull-right">
                                                        <s:submit type="button" cssStyle="margin:5px 0px;" id="addConSubmit" cssClass="add_searchButton form-control" value="" onclick="return storeProofData()" tabindex="5" ><i class=" fa fa-check-circle-o"></i>&nbsp;Submit</s:submit>
                                                    </div>
                                                </div>
                                                <s:token />         
                                            </s:form>
                                        </div>
                                        <%--close of future_items--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content end -->
                </section><!--/form-->
            </div>
        </div>

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script language="JavaScript" src='<s:url value="/includes/js/general/popupoverlay.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>
        <script>
            $(document).ready(function() {
                $('#skillListValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            });
            
        </script>
        <script>
            $("#actionMessage").show().delay(5000).fadeOut();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
