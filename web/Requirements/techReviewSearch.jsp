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
        <title>ServicesBay :: Tech&nbsp;Review&nbsp;search&nbsp;Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive_queries.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

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
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/techReviewAjax.js"/>"></script>
        <style>
            .techreview_details{
                padding: 1% 1%;
            }
        </style>
    </head>

    <script>
        var pager;
        function techreview(){
                    
            var paginationSize = 10;
            pager = new Pager('techReviewSearchTable', paginationSize);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        };
        function pagerOption(){
                    
            paginationSize = document.getElementById("paginationOption").value;
            if(isNaN(paginationSize))
            pager = new Pager('techReviewSearchTable', parseInt(paginationSize));
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
                    
        };
    </script>

    <body oncontextmenu="return false" onload="doOnLoad(); techreview();">
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
                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                            <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                            <s:param name="accountFlag">csr</s:param>
                                            <s:param name="reqFlag">consultantTab</s:param>
                                        </s:url>

                                        <s:url var="custReqUrl" action="recruitment/consultant/getLoginUserRequirementList.action">
                                            <s:param name="orgid"><s:property value="accountSearchID"/></s:param>
                                            <s:param name="customerFlag">customer</s:param>
                                            <s:param name="accountFlag">MyRequirements</s:param>
                                        </s:url>   
                                        <s:url var="custReqEditUrl" action="Requirements/requirementedit.action">
                                            <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                            <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                            <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                            <s:param name="customerFlag">customer</s:param>
                                            <s:param name="reqFlag">consultantTab</s:param>
                                        </s:url>



                                        <label class=""> </label> 
                                        <s:if test="accountFlag=='csr'" >
                                            <s:a href='%{#csrMyUrl}' id="techSearchAccountName" ><s:property value="%{accountName}"/></s:a>
                                        </s:if>
                                        <s:else>
                                            <s:a href='#' id="techSearchAccountName" ><s:property value="%{accountName}"/></s:a>
                                        </s:else>
                                        <s:if test="accountFlag=='csr'" >
                                            <s:a href='%{#csrReqUrl}' id="techSearchReqList" > >> Requirements&nbsp;List</s:a>
                                        </s:if>
                                        <s:else>
                                            <s:a href='%{#custReqUrl}'  id="techSearchReqList"> >> Requirements&nbsp;List</s:a>        
                                        </s:else>
                                        <s:if test="accountFlag=='csr'" >
                                            <s:a href='%{#csrReqEditUrl}' id="techSearchJdId"> >> <s:property value="%{jdId}"/></s:a>
                                        </s:if>
                                        <s:else>
                                            <s:a href='%{#custReqEditUrl}' id="techSearchJdId" > >> <s:property value="%{jdId}"/></s:a>
                                        </s:else>    
                                        <span class="breadcrumActive"> >> Tech&nbsp;Review</span>



                                    </div>

                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <font color="#ffffff">Tech&nbsp;Review&nbsp;Details</font>
                                                    <i id="updownArrowAccount" onclick="toggleContentAccount('techReviewForm')" class="fa fa-angle-up"></i> 
                                                    <s:url var="contechReqEditUrl" action="Requirements/requirementedit.action">
                                                        <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 

                                                        <s:param name="customerFlag">customer</s:param> 
                                                        <s:param name="jdId" ><s:property value="%{jdId}" /></s:param> 
                                                        <s:param name="reqFlag">consultantTab</s:param>
                                                        <s:param name="accountSearchID"><s:property value="accountSearchID"/></s:param>
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#contechReqEditUrl}'><i class="fa fa-undo"></i></s:a></span>
                                                    </h4>
                                                </div>
                                            </div>


                                            <div id="emailPhoneShow_popup">
                                                <div id="emailPhoneShowOnOverlay" >
                                                    <div class="overlayOrButton_color">
                                                        <table>
                                                            <tr><td><h4 style=""><font color="#ffffff">&nbsp;&nbsp;Techie&nbsp;Details&nbsp;&nbsp; </font></h4></td>
                                                            </tr>
                                                            <span class=" pull-right"><h5><a href="" id="techSearchPhoneOverlayClose" class="emailPhoneShow_popup_close" onclick="techReviewEmailPhoneOverlay();"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <form action="#" theme="simple" >
                                                            <div class="techie">
                                                                <div class="inner-reqdiv-elements">
                                                                    <table>
                                                                        <span><error></error></span>
                                                                    <s:textfield name="email"  label="Email-Id" id="email"  style="background-color:white;color:black;border:solid 1px #B0B0B0 ;" disabled="true" cssClass="form-control"/>
                                                                    <s:textfield name="contactNo"  label="Contact No" id="contactNo"  style="background-color:white;color:black;border:solid 1px #B0B0B0 ;" disabled="true" cssClass="form-control"/>

                                                                </table>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>


                                        <div id="techReviewCommentsOverlay_popup">
                                            <div id="reviewCommentsOverlay">
                                                <div class="backgroundcolor">
                                                    <table>
                                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Review&nbsp;Comments&nbsp;&nbsp; </font></h4></td>
                                                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="techSearchCommentsOverlayClose" class="techReviewCommentsOverlay_popup_close" onclick="techReviewCommentsOverlayJs()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                    </table>
                                                </div>
                                                <div>

                                                    <s:textarea name="reviewComments" id="reviewComments"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                                                </div>
                                                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                            </div>
                                        </div>



                                        <div id="techReviewResults_popup">
                                            <div id="techReviewBoxResults" class="techReviewPopupStyle">
                                                <div class="backgroundcolor">
                                                    <table>
                                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Tech&nbsp;Review&nbsp;Results&nbsp;of&nbsp;Consultant&nbsp;&nbsp; </font></h4></td>
                                                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="techSearchResultOverlay" class="techReviewResults_popup_close" onclick="techReviewResultsOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                    </table>
                                                </div>
                                                <div style="padding:0 1%">
                                                    <%--form start from here --%>

                                                    <span><e></e></span><br>
                                                    <s:hidden name="consultId" id="consultId"/>

                                                    <label class="headingLabel">Consultant&nbsp;Details</label>
                                                    <div id="reviewalignBox">
                                                        <div class="techreview_details row">
                                                            <div class="col-sm-4">
                                                                <label class="popuplabel">Name  </label>
                                                                <s:textfield type="text"
                                                                             name="consultantName"
                                                                             cssClass="techReviewInputStyle"
                                                                             id="consultantName"
                                                                             disabled="true"
                                                                             />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="popuplabel">Title </label>
                                                                <s:textfield type="text"
                                                                             name="consultantJobTitle"
                                                                             cssClass="techReviewInputStyle"
                                                                             id="consultantJobTitle"
                                                                             disabled="true"
                                                                             />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="label_order popuplabel">Email</label>
                                                                <s:textfield type="text"
                                                                             name="consultantEmail"
                                                                             cssClass="techReviewInputStyle"
                                                                             id="consultantEmail"
                                                                             disabled="true"
                                                                             />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="popuplabel">Mobile </label>
                                                                <s:textfield type="text"
                                                                             name="consultantMobile"
                                                                             cssClass="techReviewInputStyle"
                                                                             id="consultantMobile"
                                                                             disabled="true"
                                                                             />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="popuplabel">Sch.Date</label>
                                                                <s:textfield type="text"
                                                                             name="interviewDate"
                                                                             cssClass="techReviewInputStyle"
                                                                             id="interviewDate"
                                                                             disabled="true"
                                                                             />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="label_order popuplabel col-lg-6 no_gutter">Status</label>
                                                                <span id="examStatus" class="col-sm-12 col-lg-6 no_gutter"> </span>
                                                                <span id="onlineExam">
                                                                    <s:select cssClass="techReviewSelectStyle" 
                                                                              name="finalTechReviewStatus" 
                                                                              id="finalTechReviewStatus" 
                                                                              list="#@java.util.LinkedHashMap@{'Processing':'Processing','Rejected':'Rejected','ShortListed':'ShortListed','Selected':'Selected'}" 
                                                                              disabled="true"
                                                                              />
                                                                </span>
                                                                
                                                            </div>
                                                            <div class="col-sm-12">   </div>



                                                        </div>

                                                    </div>
                                                    <span id="notOnline">   
                                                        <label class="headingLabel">Skill&nbsp;Details</label>

                                                        <div id="reviewalignBox">
                                                            <div class="inner-techReviewdiv-elements">
                                                                <s:textarea type="text"
                                                                            name="consultantSkill"
                                                                            cssClass="reviewareacss"
                                                                            id="consultantSkill"
                                                                            disabled="true"
                                                                            />
                                                            </div>

                                                        </div>
                                                        <label class="headingLabel">Rating&nbsp;Details(**Rating&nbsp;between&nbsp;1(min)&nbsp;to&nbsp;10(max))</label>

                                                        <div id="reviewalignBox row">

                                                            
                                                                <div class="col-sm-3">
                                                                    <label class="contact_search">Technical&nbsp;Skills </label>
                                                                    <s:textfield type="text"
                                                                                 name="techSkill"
                                                                                 cssClass="ratingInputStyle"
                                                                                 id="techSkill"
                                                                                 value=""
                                                                                 disabled="true"
                                                                                 />
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="contact_search">Domain&nbsp;Skills </label>
                                                                    <s:textfield type="text"
                                                                                 name="domainSkill"
                                                                                 cssClass="ratingInputStyle"
                                                                                 id="domainSkill"
                                                                                 value=""
                                                                                 disabled="true"
                                                                                 /> 
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <label class="">Communication&nbsp;Skills</label>
                                                                    <s:textfield type="text"
                                                                                 name="comSkill"
                                                                                 cssClass="ratingInputStyle"
                                                                                 id="comSkill"
                                                                                 placeholder=""
                                                                                 value=""
                                                                                 disabled="true"
                                                                                 /> 
                                                                </div>

                                                          
                                                        </div>



                                                        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                                    </span>
                                                    <span id="online" >
                                                        <s:hidden id="contechId" name="contechId"/>
                                                        <s:hidden id="consultantId" name="consultantId" value="%{consult_id}"/>
                                                        <s:hidden id="requirementId" name="consultantId" value="%{requirementId}"/>
                                                        <label class="headingLabel">Online&nbsp;Exam&nbsp;Details</label>

                                                        <div class="skillQuestionBox">
                                                            <label class="headingLabel " style="color: #56a5ec">Exam&nbsp;Performed&nbsp;On &nbsp;<span id="examsubmittedDate" /></label>   
                                                            <div class="row">

                                                                <div class="col-sm-3" id="skillDivQuestion1">  <label class="skillQuestionsLabel"> <span id="skillQuestion1" ></span></label> </div>
                                                                <div class="col-sm-3" id="skillDivQuestion2"> <label class="skillQuestionsLabel"> <span id="skillQuestion2"></span></label></div> 
                                                                <div class="col-sm-3" id="skillDivQuestion3"> <label class="skillQuestionsLabel"> <span id="skillQuestion3"></span></label></div>
                                                                <div class="col-sm-3" id="skillDivQuestion4"> <label class="skillQuestionsLabel"> <span id="skillQuestion4"></span></label></div> 
                                                                <div class="col-sm-3" id="skillDivQuestion5"> <label class="skillQuestionsLabel"> <span id="skillQuestion5"></span></label></div> 
                                                            </div>
                                                            <div class="row">

                                                                <div class="col-sm-3" id="skillDivQuestion6"> <label class="skillQuestionsLabel"> <span id="skillQuestion6"></span></label></div> 
                                                                <div class="col-sm-3" id="skillDivQuestion7"> <label class="skillQuestionsLabel"> <span id="skillQuestion7"></span></label></div> 
                                                                <div class="col-sm-3" id="skillDivQuestion8"> <label class="skillQuestionsLabel"> <span id="skillQuestion8"></span></label></div> 
                                                                <div class="col-sm-3" id="skillDivQuestion9"><label class="skillQuestionsLabel"> <span id="skillQuestion9"></span></label></div> 
                                                                <div class="col-sm-3" id="skillDivQuestion10"><label class="skillQuestionsLabel"> <span id="skillQuestion10"></span></label></div> 
                                                            </div>
                                                        </div>
                                                    </span>
                                                    <div id="reviewalignBox"  name="reviewComments">
                                                        <label class="headingLabel">Comments</label>
                                                        <div class="inner-techReviewdiv-elements">
                                                            <s:textarea id="consultantComments"
                                                                        name="consultantComments"
                                                                        cssClass="reviewareacss"
                                                                        type="text"
                                                                        placeholder="Any comments"
                                                                        value=""
                                                                        disabled="true"
                                                                        />
                                                        </div>
                                                    </div>           
                                                    <span id="examButton"> 
                                                        <div class="pull-left "><s:submit type="button" id="rejectButton" cssClass="cssbutton fa fa-times"  value="Rejected" onclick="saveExamResult(this.value,'reviewSearch');" style="margin-left: -27px;"></s:submit></div>
                                                        <div class="pull-right "><s:submit type="button" id="shortlistButton" cssClass="cssbutton fa fa-check"  value="ShortListed" onclick="saveExamResult(this.value,'reviewSearch');"></s:submit></div>
                                                    </span>  
                                                </div>
                                            </div>
                                            <%--close of future_items--%>
                                        </div>







                                        <s:url var="myUrl" action="Requirements/requirementedit.action">
                                            <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                            <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                            <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                            <s:param name="reqFlag">consultantTab</s:param>
                                        </s:url>
                                        <label class="">Consultant Name:<font style="color: #FF8A14;"><s:a href='%{#myUrl}' id="techSearchConsName"><s:property value="%{consult_name}"/></s:a></font></label>
                                            <s:url var="ReqUrl" action="Requirements/requirementedit.action">
                                                <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                                <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                                <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                            </s:url>
                                        <label class="pull-right ">Job Title:<font style="color: #FF8A14; pointer-events: hand;"><s:a href='%{#ReqUrl}' id="techSearchReqName"><s:property value="%{reqName}"/></s:a></font></label>
                                            <div><span id="validationMessage" /> </div>

                                            <div class="inner-reqdiv-elements" id="techReviewForm">
                                                <div class="row">
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec">Interview&nbsp;Date</label>
                                                        <div class="calImage"><s:textfield cssClass="form-control" tabindex="1" id="searchInterviewDate" placeholder="Interview Date"  name="searchInterviewDate"  onkeypress="return enterTechDateRepository(this);" ><i class="fa fa-calendar"></i></s:textfield>
                                                        </div></div>
                                                    <s:hidden name="empIdTechReview" id="empIdTechReview" />

                                                <div class="col-sm-4">
                                                    <label class="labelStylereq" style="color:#56a5ec">Reviewer </label>
                                                    <s:textfield cssClass="form-control" id="eNameTechReview" tabindex="2"  name="eNameTechReview" onkeyup="return getEmpForTechReview();" autocomplete='off' maxLength="30" placeholder="Reviewer"/>
                                                </div>
                                                <div class="col-sm-2 pull-right">
                                                    <label class="labelStylereq" style="color:#56a5ec"></label>
                                                    <s:submit id="techSearchButton" cssClass="add_searchButton form-control" tabindex="4" type="button" onclick="searchTechReviews();" value="" cssStyle="margin:5px 0px 0px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                    </div>
                                                <s:form action="../Requirements/forwardTechReview.action" id="#" theme="simple" enctype="multipart/form-data" >
                                                    <s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />
                                                    <s:hidden name="consult_id" id="consult_id" value="%{consult_id}" />
                                                    <s:hidden name="accountSearchID" id="accountSearchID" value="%{accountSearchID}" />
                                                    <s:hidden name="accountFlag" id="accountFlag" value="%{accountFlag}" />
                                                    <s:hidden name="jdId" id="jdId" value="%{jdId}" />

                                                    <s:url var="reviewUrl" action="Requirements/forwardTechReview.action">
                                                        <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                                        <s:param name="consult_id" ><s:property value="%{consult_id}" /></s:param> 
                                                        <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                                        <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                                        <s:param name="accountFlag" ><s:property value="%{accountFlag}" /></s:param> 
                                                        <s:param name="techReviewStatus" ><s:property value="%{techReviewStatus}" /></s:param> 
                                                    </s:url>
                                                    <s:if test="techReviewStatus=='Processing' || techReviewStatus=='ShortListed'">
                                                        <div class="col-sm-2 pull-right">
                                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                                            <%--  <s:submit cssClass="cssbutton form-control" value="Add Review" cssStyle="margin:5px 0px 0px" />--%>
                                                            <s:a href='%{#reviewUrl}' id="techSearchAddButton" cssClass="add_searchButton form-control" tabindex="3" cssStyle="margin:5px 0px 0px"><i class="fa fa-plus-square"></i>&nbsp;Add</button></s:a>
                                                            </div>
                                                    </s:if>     
                                                    <%--<a href="../Requirements/forwardTechReview.action"><input type="button" class="cssbutton " value="Forward Review" /></a> --%>
                                                </s:form>
                                            </div>
                                        </div><br>
                                        <div id="loadingTechReviewSearch" class="loadingImg">
                                            <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                        </div>
                                        <s:form>

                                            <div class="task_content" id="task_div" align="center" style="display: none" >

                                                <div>
                                                    <div>
                                                        <table id="techReviewSearchTable" class="responsive CSSTable_task" border="5"cell-spacing="2">
                                                            <tbody>
                                                                <tr>
                                                                    <th>Review&nbsp;Type</th>
                                                                    <th>Date&nbsp;of&nbsp;Review</th>
                                                                    <th>Reviewer</th>
                                                                    <!--<th>Review By Techie2</th>-->
                                                                    <th>Avg&nbsp;Rating(10)</th>
                                                                    <th>Comments</th>
                                                                    <th>Status</th>
                                                                </tr>
                                                                <s:if test="consultantList ==null">
                                                                    <tr>
                                                                        <td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:iterator  value="consultantList">
                                                                    <!--build url TO goto Account Details-->


                                                                    <tr>
                                                                        <s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />
                                                                        <s:hidden name="consult_id" id="consult_id" value="%{consult_id}" />
                                                                        <s:hidden name="reviewType" id="reviewType" value="%{reviewType}" />
                                                                        <s:hidden name="forwardedToId" id="forwardedToId" value="%{forwardedToId}" />
                                                                        <s:hidden name="conTechReviewId" id="conTechReviewId" value="%{conTechReviewId}" />
                                                                        <td><s:a href="#" onclick="techReviewResultsToViewOnOverlay('%{reviewType}',%{conTechReviewId});techReviewResultsOverlay();" cssClass="techReviewResults_popup_open"><s:property value="reviewType"></s:property></s:a></td>
                                                                        <%--<td><s:property value="reviewType"></s:property></td>--%>
                                                                        <s:if test="reviewType=='Online'"><td><s:property value="forwardedDate"></s:property></td></s:if>
                                                                        <s:elseif test="reviewType=='Psychometric'"><td><s:property value="forwardedDate"></s:property></td></s:elseif>
                                                                        <s:else><td><s:property value="dateOfReview"></s:property></td></s:else>
                                                                        <%--<td><s:property value="forwardedToName"></s:property></td>--%>

                                                                        <td><s:a href="#" onclick="getMailPhoneOfReviewedBy(%{forwardedToId});techReviewEmailPhoneOverlay();" cssClass="emailPhoneShow_popup_open"><s:property value="forwardedToName"></s:property></s:a></td>     

                                                                                <td><s:property value="avgRating"></s:property></td>
                                                                        <%-- <td><s:a href="#" onclick="getMailPhoneOfReviewedBy(%{forwardedToId1});techReviewEmailPhoneOverlay();" cssClass="emailPhoneShow_popup_open"><s:property value="forwardedToName1"></s:property></s:a></td>--%>
                                                                        <%--<td><s:property value="techieTitle"></s:property></td>--%>
                                                                        <s:if test="comments.length()>20">  
                                                                            <td><s:a href="#" onclick="techReviewCommentsOverlayJs(\'%{comments}\');" cssClass="techReviewCommentsOverlay_popup_open" ><s:property value="%{comments.substring(0,20)}"></s:property>..</s:a></td>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <td><s:a href="#" onclick="techReviewCommentsOverlayJs(\'%{comments}\');" cssClass="techReviewCommentsOverlay_popup_open" ><s:property value="%{comments}"></s:property></s:a></td>    
                                                                        </s:else>
                                                                        <%--<td><s:property value="comments"></s:property></td>--%>
                                                                        <td><s:property value="status"></s:property></td>
                                                                        </tr>
                                                                </s:iterator>

                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>25</option>
                                                                <option>50</option>
                                                            </select>
                                                            Review's per page
                                                        </label>
                                                        <div align="right" id="pageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>
                                                    </div>
                                                    <script type="text/javascript">
                                                        var pager = new Pager('techReviewSearchTable', 8); 
                                                        pager.init(); 
                                                        pager.showPageNav('pager', 'pageNavPosition'); 
                                                        pager.showPage(1);
                                                    </script>
                                                </div>
                                            </div>
                                        </s:form>
                                        <%--</s:form>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <%-- ------------MIDDLE -----------------------------------------%>
        </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){
                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                $('#techReviewSearchTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#techReviewSearchTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>

    </body>
</html>