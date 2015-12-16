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
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Tech Review Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>



        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

        <%-- <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script> --%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/addLeaveOverlay.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/requirementAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/techReviewAjax.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>


        <script>
            $(document).ready(function() {
                
                $('#skillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                    
                });
                $('#psychoskillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                    
                });
            });
            $(document).ready(function(){
                $('#skillCategoryValue').change(function(e){
                    var values = $('#skillCategoryValue').val()
                    //                    var skillCategoryArry = [];    
                    //                    $("#skillCategoryValue :selected").each(function(){
                    //                        skillCategoryArry.push($(this).val()); 
                    //                    });
                    // alert(skillCategoryArry);
                  
                    skillsQuestions();
                });
                $('#psychoskillCategoryValue').change(function(e){
                    var values = $('#psychoskillCategoryValue').val()
                    //                    var skillCategoryArry = [];    
                    //                    $("#skillCategoryValue :selected").each(function(){
                    //                        skillCategoryArry.push($(this).val()); 
                    //                    });
                    // alert(skillCategoryArry);
                 
                    skillsQuestions();
                });
            });
      
        </script>

    </head>
    <body onload="doOnLoad(); time();setLocationForFaceToFace();">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/>
                    </div>
                </div>

            </header>
            <%-- ------------MIDDLE -----------------------------------------%>
            <div id="main">
            <section id="generalForm"><!--form-->
                <div class="container">
                    <div class="row">
                        <s:include value="/includes/menu/LeftMenu.jsp"/>
                        <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                            <div class="features_items">

                                <div class="" style="float: left; margin-top:2px; margin-bottom: -2px">
                                    <s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />
                                    <s:hidden name="consult_id" id="consult_id" value="%{consult_id}" />
                                    <s:hidden name="accountFlag" id="accountFlag" value="%{accountFlag}" />
                                    <s:hidden name="jdId" id="jdId" value="%{jdId}" />
                                    <s:hidden name="accountSearchID" id="accountSearchID" value="%{accountSearchID}" />



                                    <s:url var="csrMyUrl" action="acc/viewAccount.action">
                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                        <s:param name="accFlag">accDetails</s:param>
                                    </s:url>
                                    <s:url var="csrReqUrl" action="acc/viewAccount.action">
                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                        <s:param name="accFlag">reqSearch</s:param>
                                    </s:url>

                                    <s:url var="csrReqEditUrl" action="Requirements/requirementedit.action">
                                        <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                        <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                        <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                        <s:param name="accountFlag">csr</s:param>
                                    </s:url>
                                    <s:url var="csrTechReviewtUrl" action="Requirements/techReview.action">
                                        <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param>
                                        <s:param name="consult_id"><s:property value="%{consult_id}"/></s:param>
                                        <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                        <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                        <s:param name="accountFlag">csr</s:param>
                                    </s:url>


                                    <label class=""> </label> 
                                    <s:if test="accountFlag=='csr'" >
                                        <s:a href='%{#csrMyUrl}' style="color:#FC9A11;"><s:property value="%{accountName}"/></s:a>
                                    </s:if>
                                    <s:else>
                                        <s:a href='#' style="color:#FC9A11;"><s:property value="%{accountName}"/></s:a>
                                    </s:else>
                                    <s:if test="accountFlag=='csr'" >
                                        <s:a href='%{#csrReqUrl}' style="color:#FC9A11;">->Requirements List</s:a>
                                    </s:if>
                                    <s:else>
                                        <s:a href='#' style="color:#FC9A11;">->Requirements List</s:a>        
                                    </s:else>
                                    <s:if test="accountFlag=='csr'" >
                                        <s:a href='%{#csrReqEditUrl}' style="color:#FC9A11;">-><s:property value="%{jdId}"/></s:a>
                                    </s:if>
                                    <s:if test="accountFlag=='csr'" >
                                        <s:a href='%{#csrTechReviewtUrl}' style="color:#FC9A11;">->Tech Review</s:a>
                                    </s:if>
                                    <span style="color: #FC9A11;">->Forward Review</span>



                                </div>


                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <s:url var="revUrl" action="Requirements/techReview.action">
                                                    <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                                    <s:param name="consult_id" ><s:property value="%{consult_id}" /></s:param> 
                                                    <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                                    <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                                    <s:param name="accountFlag" ><s:property value="%{accountFlag}" /></s:param> 
                                                </s:url>

                                                <font color="#ffffff">Tech Review Details</font>
                                                <span class="pull-right"><s:a href='%{#revUrl}'><img  src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                </h4>
                                            </div>
                                        </div>
                                        <div id="loadingDashboardPage" class="loadingImg" style="display: none">
                                            <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                    </div>
                                    <%--<s:form action="" id="#" theme="simple" enctype="multipart/form-data" >--%>
                                    <s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />
                                    <s:hidden name="consult_id" id="consult_id" value="%{consult_id}" />
                                    <div class="col-sm-12">

                                        <div class="col-sm-4 pull-left">   <label class="">Consultant Name:<font style="color: #FF8A14;"><s:property value="%{consult_name}"/></font></label></div>
                                        <label class="col-sm-4  pull-right ">Requirement Name:<font style="color: #FF8A14;"><s:property value="%{reqName}"/></font></label>
                                    </div> 
                                    <%--div class="inner-techReviewdiv-elements">
                                        
                                        
                                            <span><e></e></span><br>
                                    <%--  <label class="techReviewlabelStyle">Interview:</label><s:select cssClass="techReviewSelectStyle" name="interview" id="interview" list="#@java.util.LinkedHashMap@{'I':'Internal'}" headerKey="-1" value="1" onchange="return getEmpForTechReview();"/>--%>
                                    <%--label class="">Interview Mode:</label><s:select cssClass="techReviewSelectStyle" name="interviewType" id="interviewType" list="#@java.util.LinkedHashMap@{'Face to Face':'Face to Face','Telephonic':'Telephonic','Skype':'Skype','Written':'Written','hr':'hr'}" headerKey="-1" value="1" onchange="setLocationForFaceToFace();"/>
                                    <s:hidden name="empIdTechReview" id="empIdTechReview" />
                                    <s:hidden name="empIdTechReview2" id="empIdTechReview2" />
                                    <label class="">Techie1 Name:</label><s:textfield cssClass="techReviewInputStyle" id="eNameTechReview"  name="eNameTechReview" onkeyup="return getEmpForTechReview();" />
                                    <label class="">Techie2 Name:</label><s:textfield cssClass="techReviewInputStyle" id="eNameTechReview2"  name="eNameTechReview" onkeyup="return getEmpForTechReview2();" />
                                    <div class="inner-techReviewdiv-elements"><span id="validationMessage" /></div>   
                                </div--%>
                                    <div class="inner-reqdiv-elements">
                                        <span ><e></e></span><br>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label class="labelStylereq" style="color: #56a5ec">Interview Mode:</label>
                                                <s:select cssClass="SelectBoxStyles form-control" name="interviewType" id="interviewType" list="#@java.util.LinkedHashMap@{'Face to Face':'Face to Face','Telephonic':'Telephone','Skype':'Skype','Written':'Written','hr':'Hr','Online':'Online','Psychometric':'Psychometric','WebEx':'WebEx'}" headerKey="-1" value="1" onchange="setLocationForFaceToFace();"/>
                                            </div>
                                            <s:hidden name="empIdTechReview" id="empIdTechReview" />
                                            <s:hidden name="empIdTechReview2" id="empIdTechReview2" />

                                            <div class="col-sm-3 required" id="reviewTech">
                                                <label class="labelStylereq" style="color: #56a5ec">Reviewer:</label>
                                                <s:textfield cssClass="form-control" id="eNameTechReview" placeholder="Reviewer"  name="eNameTechReview" onkeyup="return getEmpForTechReview();" autocomplete='off' maxLength="30" onfocus="return removeErrorMsgForTechie();"/>
                                            </div>


                                            <div class="col-sm-3 " id="reviewSeverity" style="display: none">
                                                <label class="labelStylereq" style="color: #56a5ec">Severity:</label>
                                                <s:select cssClass="SelectBoxStyles form-control" id="techReviewSeverity"  name="techReviewSeverity"  list="#@java.util.LinkedHashMap@{'L':'Low','M':'Medium','H':'High'}" onchange="skillsQuestions();"/>
                                            </div>
                                            <div id="techDuration" style="display: none">
                                                <div class="col-sm-3 required"  >
                                                    <label class="labelStylereq" style="color: #56a5ec">HH</label>
                                                    <s:select cssClass="form-control" id="techReviewTime"  name="techReviewTime"   maxLength="30" list="{}" cssStyle="padding: 4px"/>
                                                </div>
                                                <div class="col-sm-3 required" >
                                                    <label class="labelStylereq" style="color: #56a5ec">MM:</label>
                                                    <s:select cssClass="form-control" id="techReviewMints"  name="techReviewMints"   maxLength="30" list="{}" cssStyle="padding: 4px"/>
                                                </div>
                                                <div class="col-sm-3 required" id="reviewQuestions" style="display: none">
                                                    <label class="labelStylereq" style="color: #56a5ec">Total Questions:</label>
                                                    <s:textfield cssClass="form-control" id="techReviewQuestions"  name="techReviewQuestions"  maxLength="30" onfocus="return removeErrorMsgForTechie();" readonly="true"/> <%--onblur="return questionsCountCheck();" --%>
                                                </div>

                                            </div>

                                            <div class="required" style="display: none;padding:0;" id="techSkills">
                                                <div class="col-sm-12 skill_online required" id="">
                                                    <label class="labelStylereq" style="color: #56a5ec">Skill Set</label>  

                                                    <s:hidden id="skillValuesMap" value="%{skillValuesMap}"/>
                                                    <s:select cssClass="commentsStyle" name="skillCategoryValue"  id="skillCategoryValue" list="skillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()"  value=""  /> 
                                                    <%-- <a href='#' ><img  src="<s:url value="/includes/images/icons/isymbol.png"/>"  class="questionsCountResults_popup_open" data-popup-ordinal="0" height="25" width="25" onclick="getQuestionsCount();questionOverlay();"></a>--%>
                                                </div>
                                            </div>
                                            <div class="required" style="display: none" id="psychoTestSkills" >
                                                <div class="col-sm-12 skill_online required" id="">
                                                    <label class="labelStylereq skilllist" style="color: #56a5ec">Skill Set</label>  

                                                    <%--<s:hidden id="skillValuesMap" value="%{skillValuesMap}"/>--%>
                                                    <s:select cssClass="commentsStyle" name="psychoskillCategoryValue"  id="psychoskillCategoryValue" list="psychoSkillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()"  value=""  /> 
                                                    <%-- <a href='#' ><img  src="<s:url value="/includes/images/icons/isymbol.png"/>"  class="questionsCountResults_popup_open" data-popup-ordinal="0" height="25" width="25" onclick="getQuestionsCount();questionOverlay();"></a>--%>
                                                </div>
                                            </div>


                                            <div class="col-sm-3 required" id="revewSkill0" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill0"></span>
                                                <s:textfield cssClass="form-control " id="skill0"  name="skill0"  value="0" onblur="checkQuestionsAvailble0()" onkeypress="return isNumberKey(event)" /> <%--onblur="checkQuestionsAvailble()"--%>
                                                <s:hidden cssClass="form-control " id="skillid0"  name="skillid0"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions0"  name="skillQuestions0"  value="" />

                                            </div>
                                            <div class="col-sm-3 required" id="revewSkill1" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill1"></span>
                                                <s:textfield cssClass="form-control " id="skill1"  name="skill1"  value="0" onblur="checkQuestionsAvailble1()" onkeypress="return isNumberKey(event)" />
                                                <s:hidden cssClass="form-control " id="skillid1"  name="skillid1"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions1"  name="skillQuestions1"  value="" />
                                            </div>                   
                                            <div class="col-sm-3 required" id="revewSkill2" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill2"></span>
                                                <s:textfield cssClass="form-control " id="skill2"  name="skill2"  value="0" onblur="checkQuestionsAvailble2()" onkeypress="return isNumberKey(event)" />
                                                <s:hidden cssClass="form-control " id="skillid2"  name="skillid2"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions2"  name="skillQuestions2"  value="" />
                                            </div>             
                                            <div class="col-sm-3 required" id="revewSkill3" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill3"></span>
                                                <s:textfield cssClass="form-control " id="skill3"  name="skill3"  value="0" onblur="checkQuestionsAvailble3()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid3"  name="skillid3"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions3"  name="skillQuestions3"  value="" />
                                            </div>   
                                            <div class="col-sm-3 required" id="revewSkill4" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill4"></span>
                                                <s:textfield cssClass="form-control " id="skill4"  name="skill4"  value="0" onblur="checkQuestionsAvailble4()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid4"  name="skillid4"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions4"  name="skillQuestions4"  value="" />
                                            </div> 
                                            <div class="col-sm-3 required" id="revewSkill5" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill5"></span>
                                                <s:textfield cssClass="form-control " id="skill5"  name="skill5"  value="0" onblur="checkQuestionsAvailble5()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid5"  name="skillid5"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions5"  name="skillQuestions5"  value="" />
                                            </div> 
                                            <div class="col-sm-3 required" id="revewSkill6" style="display: none"> 
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill6"></span>
                                                <s:textfield cssClass="form-control " id="skill6"  name="skill6"  value="0" onblur="checkQuestionsAvailble6()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid6"  name="skillid6"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions6"  name="skillQuestions6"  value="" />
                                            </div> 
                                            <div class="col-sm-3 required" id="revewSkill7" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill7"></span>
                                                <s:textfield cssClass="form-control " id="skill7"  name="skill7"  value="0" onblur="checkQuestionsAvailble7()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid7"  name="skillid7"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions7"  name="skillQuestions7"  value="" />
                                            </div> 
                                            <div class="col-sm-3 required" id="revewSkill8" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill8"></span>
                                                <s:textfield cssClass="form-control " id="skill8"  name="skill8"  value="0" onblur="checkQuestionsAvailble8()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid8"  name="skillid8"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions8"  name="skillQuestions8"  value="" />
                                            </div> 
                                            <div class="col-sm-3 required" id="revewSkill9" style="display: none">
                                                <span class="labelStylereq" style="color: #56a5ec" id="labelSkill9"></span>
                                                <s:textfield cssClass="form-control " id="skill9"  name="skill9"  value="0" onblur="checkQuestionsAvailble9()" onkeypress="return isNumberKey(event)"/>
                                                <s:hidden cssClass="form-control " id="skillid9"  name="skillid9"  value="" />
                                                <s:hidden cssClass="form-control " id="skillQuestions9"  name="skillQuestions9"  value="" />
                                            </div> 




                                            <%-- <div class="col-lg-3">
                                            <%-- <label class="labelStylereq" style="color: #56a5ec">Techie2 Name:</label>--%>
                                            <%--<s:textfield cssClass="form-control" id="eNameTechReview2"  name="eNameTechReview" onkeyup="return getEmpForTechReview2();" autocomplete='off'/>--%>
                                            <s:hidden id="eNameTechReview2"  name="eNameTechReview" value=" "/>
                                            <%-- </div>--%>
                                            <div class="col-sm-3 required" id="revewInterview">
                                                <label class="labelStylereq" style="color: #56a5ec">Interview Date:</label>
                                                <s:textfield cssClass="techReviewInputStyle dateImage" id="interviewDate" placeholder="Interview Date" name="interviewDate" onkeypress="return enterTechDateRepository(this);" onfocus="return removeErrorMsgForTechie();" />
                                            </div>
                                            <div class="col-sm-3" id="reviewZone">
                                                <label class="labelStylereq" style="color: #56a5ec">Zone:</label>
                                                <s:select cssClass="SelectBoxStyles form-control" name="timeZone" id="timeZone" list="#@java.util.LinkedHashMap@{'IST':'IST','EST':'EST','CST':'CST','PST':'PST'}" headerKey="-1" value="1"/>
                                            </div>

                                            <div class="inner-techReviewdiv-elements"><span id="validationMessage" /></div>  
                                        </div>
                                    </div>
                                    <div 
                                        <%--div class="inner-techReviewdiv-elements">
                                            <label class="">Interview Date:</label><s:textfield cssClass="techReviewInputStyle" id="interviewDate"  name="interviewDate" onkeyup="return enterDateRepository();"  />
                                            <s:select cssClass="techReviewSelectStyleForZone" name="timeZone" id="timeZone" list="#@java.util.LinkedHashMap@{'IST':'IST','EST':'EST','CST':'CST','PST':'PST'}" headerKey="-1" value="1"/>
                                            <span id="location">
                                                <label class="">Location:</label><s:textarea maxlength="200" name="interviewLocation" id="interviewLocation"  />
                                            </span>
                                        </div--%>
                                        <div class="inner-reqdiv-elements">
                                            <div class="row">

                                                <div class="col-sm-12 required" id="locationData" >
                                                    <label class="labelStylereq" style="color: #56a5ec">Location:</label>
                                                    <s:textarea cssClass="form-control" maxlength="200" name="interviewLocation" id="interviewLocation" placeholder="Location" onkeyup="checkCharactersComment(this)"  onfocus="return removeErrorMsgForTechie();"/>
                                                </div>


                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12 " id="reviewComments" style="display: none">
                                                    <label class="labelStylereq" style="color: #56a5ec">Comments:</label>
                                                    <s:textarea cssClass="form-control" maxlength="200" name="interviewComments" id="interviewComments" onkeyup="checkCharactersComment(this)"  onfocus="return removeErrorMsgForTechie();"/>
                                                </div>
                                            </div>    
                                        </div>
                                        <div class="charNum" id="description_feedback"></div>
                                        <div class="inner-reqdiv-elements">
                                            <div class="row">

                                                <div class="col-sm-12 " id="notesData" style="display: none">
                                                    <label class="labelStylereq" style="color: #56a5ec">Notes:</label>
                                                    <s:textarea cssClass="form-control" maxlength="200" name="interviewNotes" id="interviewNotes" onkeyup="checkCharactersNotes(this)"  onfocus="return removeErrorMsgForTechie();"/>
                                                </div>

                                            </div>
                                        </div>  
                                        <div class="charNum" id="notes_feedback"></div>    
                                        <br/>
                                        <div class="col-sm-12">
                                            <div class="panel panel-warning">
                                                <div class="panel-heading"> 
                                                    <div class="form-group">
                                                        <s:checkbox name="techReviewAlert" style="color:#0066FF;" id="techReviewAlert" label="Do you need alert" onchange="toggleDisabled(this.checked)" />
                                                    </div>
                                                </div>
                                                <div class="panel-body" id="techAlertContent">
                                                    <%--label class="techReviewlabelStyle">Alert Date:</label><s:textfield cssClass="techReviewInputStyle" id="reviewAlertDate"  name="reviewAlertDate" onkeyup="return enterDateRepository();" onchange="return dateValidationWithInterviewDate();" />
                                                    <label style="color:#56a5ec;" class="techReviewlabelStyle">Alert message:</label>
                                                    <s:textarea name="alerttextarea" id="alertMessageTechReview"  /--%>
                                                    <div class="inner-reqdiv-elements">
                                                        <div class="row">
                                                            <div class="col-sm-6 required">
                                                                <label class="labelStylereq" style="color: #56a5ec">Alert Date:</label>
                                                                <s:textfield cssClass="techReviewInputStyle dateImage" id="reviewAlertDate" placeholder="Date" name="reviewAlertDate" onkeyup="return enterDateRepository();" onchange="return dateValidationWithInterviewDate();" />
                                                            </div>
                                                            <div class="col-sm-6">
                                                                <label class="labelStylereq" style="color: #56a5ec">Alert message:</label>
                                                                <s:textarea cssClass="form-control" name="alerttextarea" id="alertMessageTechReview" placeholder="Alert message"  />
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>





                                        <div class="col-sm-12">
                                            <div class="col-sm-2 pull-right">     <s:submit type="button" cssClass="add_searchButton form-control" cssStyle="margin:0px 0px;" value="" onclick="forwardReviewToCustomer();" theme="simple"><i class="fa fa-forward"></i>&nbsp;Forward</s:submit></div>
                                        </div>
                                        <%--</s:form>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="questionsCountResults_popup">
                        <div id="questionsCounttechReviewBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">Questions Count</font></h4></td>
                                    <span class="pull-right"> <h5 ><a href="" class="questionsCountResults_popup_close" onclick="questionOverlay()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                                </table>
                            </div>
                            <div style="margin: 10px;margin-bottom: -10px"><center>
                                    <table id="questionsCountTable"  class="CSSTable_task  " border="2" cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;">
                                        <tbody>
                                            <tr>
                                                <th>Topic Name</th>
                                                <th>Low Level Questions</th>
                                                <th>Medium Level Questions</th>
                                                <th>High Level Questions</th>
                                            </tr>
                                        </tbody>
                                    </table>
                                </center>

                            </div><center>
                                <font style="color: #fff">........................ ......................................... .................................</font>
                            </center>
                        </div>
                    </div>
                    <%--            <div id="questionsCountResults_popup">
                                    <div id="questionsCounttechReviewBox">
                                        <div class="backgroundcolor">
                                            <table>
                                                <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Questions Count&nbsp;&nbsp; </font></h4></td>
                                                <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="questionsCountResults_popup_close"  ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                                            </table>
                                        </div>
                                       
                                      
                                        <table id="questionsCountTable">
                                        </table>

                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                </div>
            </div>--%>

            </section>
                                                                </div>
        </div>

        <%-- ------------MIDDLE -----------------------------------------%>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>