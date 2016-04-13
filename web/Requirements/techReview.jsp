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
        <title>ServicesBay :: Tech&nbsp;Review&nbsp;Page</title>

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
                    skillsQuestions();
                });
                $('#psychoskillCategoryValue').change(function(e){
                    var values = $('#psychoskillCategoryValue').val()
                     skillsQuestions();
                });
            });
      
        </script>

    </head>
    <body onload="doOnLoadTechReviewForward(); time();setLocationForFaceToFace();">
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
                                            <s:a href='%{#csrMyUrl}' id="techAccountName"><s:property value="%{accountName}"/></s:a>
                                        </s:if>
                                        <s:else>
                                            <s:a href='#' id="techAccountName"><s:property value="%{accountName}"/></s:a>
                                        </s:else>
                                        <s:if test="accountFlag=='csr'" >
                                            <s:a href='%{#csrReqUrl}' id="techReqList"> >> Requirements&nbsp;List</s:a>
                                        </s:if>
                                        <s:else>
                                            <s:a href='#' id="techReqList"> >> Requirements&nbsp;List</s:a>        
                                        </s:else>
                                        <s:if test="accountFlag=='csr'" >
                                            <s:a href='%{#csrReqEditUrl}' id="techJdId"> >> <s:property value="%{jdId}"/></s:a>
                                        </s:if>
                                        <s:if test="accountFlag=='csr'" >
                                            <s:a href='%{#csrTechReviewtUrl}' id="csrTechReview"> >> Tech&nbsp;Review</s:a>
                                        </s:if>
                                            <span class="breadcrumActive"> >> Forward&nbsp;Review</span>



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
                                                         <s:param name="techReviewStatus" ><s:property value="%{techReviewStatus}" /></s:param> 
                                                    </s:url>

                                                    <font color="#ffffff">Tech&nbsp;Review&nbsp;Details</font>
                                                    <span class="pull-right"><s:a href='%{#revUrl}' id="techBackButton"><i class="fa fa-undo"></i></s:a></span>
                                                    </h4>
                                                </div>
                                            </div>
                                            <div id="loadingDashboardPage" class="loadingImg" style="display: none">
                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                        </div>
                                        <s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />
                                        <s:hidden name="consult_id" id="consult_id" value="%{consult_id}" />
                                        <div class="">

                                            <label class="">Consultant&nbsp;Name:<font style="color: #FF8A14;"><s:property value="%{consult_name}"/></font></label>
                                            <label class="pull-right ">Requirement&nbsp;Name:<font style="color: #FF8A14;"><s:property value="%{reqName}"/></font></label>
                                        </div> 
                                        <div class="inner-reqdiv-elements">
                                            <span ><e></e></span>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <label class="labelStylereq" style="color: #56a5ec">Interview&nbsp;Mode</label>
                                                    <s:select cssClass="SelectBoxStyles form-control" name="interviewType" id="interviewType" list="#@java.util.LinkedHashMap@{'Face to Face':'Face to Face','Telephonic':'Telephone','Skype':'Skype','Written':'Written','hr':'Hr','Online':'Online','Psychometric':'Psychometric','WebEx':'WebEx'}" headerKey="-1" value="1" onchange="setLocationForFaceToFace();"/>
                                                </div>
                                                <s:hidden name="empIdTechReview" id="empIdTechReview" />
                                                <s:hidden name="empIdTechReview2" id="empIdTechReview2" />

                                                <div class="col-sm-3 required" id="reviewTech">
                                                    <label class="labelStylereq" style="color: #56a5ec">Reviewer</label>
                                                    <s:textfield cssClass="form-control" id="eNameTechReview" placeholder="Reviewer"  name="eNameTechReview" onkeyup="return getEmpForTechReview();" autocomplete='off' maxLength="30" onfocus="return removeErrorMsgForTechie();"/>
                                                </div>


                                                <div class="col-sm-3 " id="reviewSeverity" style="display: none">
                                                    <label class="labelStylereq" style="color: #56a5ec">Severity</label>
                                                    <s:select cssClass="SelectBoxStyles form-control" id="techReviewSeverity"  name="techReviewSeverity"  list="#@java.util.LinkedHashMap@{'L':'Low','M':'Medium','H':'High'}" onchange="skillsQuestions();"/>
                                                </div>
                                                <div id="techDuration" style="display: none">
                                                    <div class="col-sm-3 required"  >
                                                        <label class="labelStylereq" style="color: #56a5ec">HH</label>
                                                        <s:select cssClass="SelectBoxStyles form-control" id="techReviewTime"  name="techReviewTime"   maxLength="30" list="{}" cssStyle="padding: 4px"/>
                                                    </div>
                                                    <div class="col-sm-3 required" >
                                                        <label class="labelStylereq" style="color: #56a5ec">MM</label>
                                                        <s:select cssClass="SelectBoxStyles form-control" id="techReviewMints"  name="techReviewMints"   maxLength="30" list="{}" cssStyle="padding: 4px"/>
                                                    </div>
                                                    <div class="col-sm-3 required" id="reviewQuestions" style="display: none">
                                                        <label class="labelStylereq" style="color: #56a5ec">Total&nbsp;Questions</label>
                                                        <s:textfield cssClass="form-control" id="techReviewQuestions"  name="techReviewQuestions"  maxLength="30" onfocus="return removeErrorMsgForTechie();" readonly="true"/> <%--onblur="return questionsCountCheck();" --%>
                                                    </div>

                                                </div>

                                                <div class="required" style="display: none;padding:0;" id="techSkills">
                                                    <div class="col-sm-12 skill_online required" id="">
                                                        <label class="labelStylereq" style="color: #56a5ec">Skill&nbsp;Set</label>  

                                                        <s:hidden id="skillValuesMap" value="%{skillValuesMap}"/>
                                                        <s:select cssClass="commentsStyle" name="skillCategoryValue"  id="skillCategoryValue" list="skillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()"  value=""  /> 
                                                    </div>
                                                </div>
                                                <div class="required" style="display: none" id="psychoTestSkills" >
                                                    <div class="col-sm-12 skill_online required" id="">
                                                        <label class="labelStylereq skilllist" style="color: #56a5ec">Skill&nbsp;Set</label>  

                                                        <s:select cssClass="commentsStyle" name="psychoskillCategoryValue"  id="psychoskillCategoryValue" list="psychoSkillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()"  value=""  /> 
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

                                                <s:hidden id="eNameTechReview2"  name="eNameTechReview" value=" "/>
                                                <div class="col-sm-3 required" id="revewInterview">
                                                    <label class="labelStylereq" style="color: #56a5ec">Interview&nbsp;Date</label>
                                                    <div class="calImage"><s:textfield cssClass="form-control " id="interviewDate" placeholder="Interview Date" name="interviewDate" onkeypress="return enterTechDateRepository(this);" onfocus="return removeErrorMsgForTechie();" ><i class="fa fa-calendar"></i></s:textfield>
                                                        </div></div>
                                                    <div class="col-sm-3" id="reviewZone">
                                                        <label class="labelStylereq" style="color: #56a5ec">Zone</label>
                                                    <s:select cssClass="SelectBoxStyles form-control" name="timeZone" id="timeZone" list="#@java.util.LinkedHashMap@{'IST':'IST','EST':'EST','CST':'CST','PST':'PST'}" headerKey="-1" value="1"/>
                                                </div>

                                                <div class="inner-techReviewdiv-elements"><span id="validationMessage" /></div>  
                                            </div>
                                        </div>
                                        <div 
                                           
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">

                                                    <div class="col-sm-12 required" id="locationData" >
                                                        <label class="labelStylereq" style="color: #56a5ec">Location</label>
                                                        <s:textarea cssClass="form-control" maxlength="200" name="interviewLocation" id="interviewLocation" placeholder="Location" onkeyup="checkCharactersComment(this)"  onfocus="return removeErrorMsgForTechie();"/>
                                                    </div>


                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12 " id="reviewComments" style="display: none">
                                                        <label class="labelStylereq" style="color: #56a5ec">Comments</label>
                                                        <s:textarea cssClass="form-control" maxlength="200" name="interviewComments" id="interviewComments" onkeyup="checkCharactersComment(this)"  onfocus="return removeErrorMsgForTechie();"/>
                                                    </div>
                                                </div>    
                                            </div>
                                            <div class="charNum" id="description_feedback"></div>
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">

                                                    <div class="col-sm-12 " id="notesData" style="display: none">
                                                        <label class="labelStylereq" style="color: #56a5ec">Notes</label>
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
                                                       
                                                        <div class="inner-reqdiv-elements">
                                                            <div class="row">
                                                                <div class="col-sm-4 required">
                                                                    <label class="labelStylereq" style="color: #56a5ec">Alert&nbsp;Date</label>
                                                                    <div class="calImage"><s:textfield cssClass="form-control" id="reviewAlertDate" placeholder="Date" name="reviewAlertDate" onkeyup="return enterDateRepository();" onchange="return dateValidationWithInterviewDate();" ><i class="fa fa-calendar"></i></s:textfield>
                                                                        </div>
                                                                    </div>
                                                                 <div class="col-sm-3 "  >
                                                        <label class="labelStylereq" style="color: #56a5ec">HH</label>
                                                        <s:select cssClass="SelectBoxStyles form-control" id="techReviewAlertHours"  name="techReviewAlertHours"   maxLength="30" list="{}" cssStyle="padding: 4px"/>
                                                    </div>
                                                    <div class="col-sm-3 " >
                                                        <label class="labelStylereq" style="color: #56a5ec">MM</label>
                                                        <s:select cssClass="SelectBoxStyles form-control" id="techReviewAlertMints"  name="techReviewAlertMints"   maxLength="30" list="{}" cssStyle="padding: 4px"/>
                                                    </div>         
                                                                    <div class="col-sm-6">
                                                                        <label class="labelStylereq" style="color: #56a5ec">Alert&nbsp;message</label>
                                                                    <s:textarea cssClass="form-control" name="alerttextarea" id="alertMessageTechReview" placeholder="Alert message"  />
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>





                                            <div class="col-sm-12">
                                                <div class="col-sm-2 pull-right">     <s:submit type="button" id="techForwardButton" cssClass="add_searchButton form-control" cssStyle="margin:0px 0px;" value="" onclick="forwardReviewToCustomer();" theme="simple"><i class="fa fa-forward"></i>&nbsp;Forward</s:submit></div>
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
                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">Questions&nbsp;Count</font></h4></td>
                                        <span class="pull-right"> <h5 ><a href="" id="questionOverlayCloseButton" class="questionsCountResults_popup_close" onclick="questionOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                    </table>
                                </div>
                                <div style="margin: 10px;margin-bottom: -10px"><center>
                                        <table id="questionsCountTable"  class="CSSTable_task  " border="2" cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;">
                                            <tbody>
                                                <tr>
                                                    <th>Topic&nbsp;Name</th>
                                                    <th>Low&nbsp;Level&nbsp;Questions</th>
                                                    <th>Medium&nbsp;Level&nbsp;Questions</th>
                                                    <th>High&nbsp;Level&nbsp;Questions</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </center>

                                </div><center>
                                    <font style="color: #fff">........................ ......................................... .................................</font>
                                </center>
                            </div>
                        </div>
                       

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