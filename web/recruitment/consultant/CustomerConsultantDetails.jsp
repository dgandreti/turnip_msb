<%-- 
    Document   : AccountAdd
    Created on : Apr 12, 2015, 7:05:25 PM
    Author     : NagireddySeerapu
--%>

<%@page import="com.mss.msp.recruitment.ConsultantVTO"%>
<%@page import="com.mss.msp.usersdata.UserVTO"%>
<%@page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@page import="com.mss.msp.recruitment.ConsultantVTO"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Consultant Details Page</title>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">


        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <%--script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/includes/js/general/consultantOverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>


    </head>
    <body style="overflow-x: hidden" onload="consultdoOnLoad(); defaultClick();">
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
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">

                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="backgroundcolor" >
                                    <div class="panel-heading" id="" style="margin-top:6px">
                                        <h4 class="panel-title">
                                            <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                            <font style="color: #fff">Consultant Details</font>
                                            <s:if test="consultFlag=='vendor'">
                                                <s:url var="myUrl" action="getMyConsultantSearch.action">
                                                    <s:param name="consultantFlag"><s:property value="%{consultantFlag}"/></s:param> 

                                                </s:url>
                                                <span class="pull-right" ><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                </s:if>
                                                <s:if test="consultFlag=='customer'">

                                                <s:if test="vendor=='yes'">
                                                    <s:url var="myUrl" action="../../Requirements/requirementedit.action">
                                                        <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                                        <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                                        <s:param name="accountFlag"><s:property value="%{accountFlag}"/></s:param> 
                                                        <s:param name="customerFlag"><s:property value="%{customerFlag}"/></s:param> 
                                                        <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                                        <s:param name="vendor"><s:property value="%{vendor}"/></s:param >
                                                        <s:param name="reqFlag">consultantTab</s:param>
                                                    </s:url>
                                                </s:if>
                                                <s:elseif test="techReviewFlag=='techReview'">
                                                    <s:url var="myUrl" action="getTechReviewDetails.action">

                                                    </s:url>
                                                </s:elseif>
                                                <s:else>
                                                    <s:url var="myUrl" action="../../Requirements/requirementedit.action">
                                                        <s:param name="requirementId"><s:property value="%{requirementId}"/></s:param> 
                                                        <s:param name="accountSearchID"><s:property value="%{accountSearchID}"/></s:param> 
                                                        <s:param name="accountFlag"><s:property value="%{accountFlag}"/></s:param> 
                                                        <s:param name="customerFlag"><s:property value="%{customerFlag}"/></s:param> 
                                                        <s:param name="jdId"><s:property value="%{jdId}"/></s:param> 
                                                        <s:param name="reqFlag">consultantTab</s:param>
                                                    </s:url>
                                                </s:else>

                                                <span class="pull-right" ><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                </s:if>
                                                <s:if test="consultFlag =='consultant'">
                                                    <s:url var="myUrl" action="../../users/general/myprofile.action">
                                                        <%--s:param name="consultantFlag"><s:property value="%{consultantFlag}"/></s:param--%> 
                                                    </s:url>
                                                <span class="pull-right" ><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                </s:if>
                                        </h4>
                                    </div>
                                </div>
                                <s:if test="hasActionMessages()">
                                    <div>
                                        <s:actionmessage cssClass="actionMessagecolor"/>
                                    </div>
                                </s:if>

                                <div class="panel-body">



                                    <form action="" id="consultantForm" theme="simple">
                                        <%--div><span><consult_error></consult_error></span></div--%>
                                        <s:hidden id="consultFlag" name="consultFlag" value="%{consultFlag}"/>
                                        <s:hidden value="%{ConsultantVTO.consult_id}" name="consult_id" />

                                        <s:hidden id="requirementId" name="requirementId" value="%{requirementId}"/>
                                        <s:hidden id="accountSearchID" name="accountSearchID" value="%{accountSearchID}"/>
                                        <s:hidden id="accountFlag" name="accountFlag" value="%{accountFlag}"/>
                                        <s:hidden id="customerFlag" name="customerFlag" value="%{customerFlag}"/>
                                        <s:hidden id="jdId" name="jdId" value="%{jdId}"/>
                                        <s:hidden id="vendor" name="vendor" value="%{vendor}" />
                                        <s:hidden id="consultantFlag" name="consultantFlag" value="%{consultantFlag}" />
                                        <s:hidden value="%{consultantVTO.consult_email}" name="consult_email" />
                                        <div class="col-lg-12" style="background-color: #dfdfdf; padding-top: 4px">
                                            <div class="col-lg-4 " >
                                                <div class="form-group   ">
                                                    <s:label  cssClass="consultantAlingment"  >Email&nbsp;Id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_email}" readonly="true"></s:textfield>
                                                </div>
                                                <div class="form-group">
                                                    <s:label  cssClass="consultantAlingment"  id="label_align">First Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>

                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_fstname}" readonly="true"></s:textfield>
                                                </div>
                                                <div class="form-group">      
                                                    <s:label cssClass="consultantAlingment" >Date of Birth&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_dob}"  readonly="true"></s:textfield>
                                                  
                                                </div>
                                                <div class="form-group">
                                                    <s:label cssClass="consultantAlingment">Alternate Email&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_alterEmail}" readonly="true"> </s:textfield>
                                                </div>
                                                <div class="form-group"> 
                                                    <s:label cssClass="consultantAlingment" >Social Id &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:if test="consultantVTO.consult_linkedInId.length()>1" > 
                                                        <s:a  href="#" onclick="window.open('%{consultantVTO.consult_linkedInId}');"  ><img src="<s:url value="/includes/images/linkedinIcon.png"/>" style="width:25; height: 25" ></s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img src="<s:url value="/includes/images/linkedinIcon.png"/>" style="opacity: 0.5;width:25; height: 25">
                                                    </s:else>
                                                        <s:if test="consultantVTO.consult_twitterId.length()>1" > 
                                                            <s:a href="#" onclick="window.open('%{consultantVTO.consult_twitterId}');"  ><img src="<s:url value="/includes/images/twitterIcon.png"/>" style="width:25; height: 25"></s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img src="<s:url value="/includes/images/twitterIcon.png"/>"  style="opacity: 0.5; width:25; height: 25">
                                                    </s:else>
                                                        <s:if test="consultantVTO.consult_facebookId.length()>1" >
                                                            <s:a href="#" onclick="window.open('%{consultantVTO.consult_facebookId}');"   ><img src="<s:url value="/includes/images/facebookIcon.png"/>" style="width:25; height: 25" ></s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img src="<s:url value="/includes/images/facebookIcon.png"/>"  style="opacity: 0.5; width:25; height: 25">
                                                    </s:else>
                                                    <%--<s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_linkedInId}" readonly="true"></s:textfield> --%>
                                                </div>
                                            </div>
                                            <div class="col-lg-4" >
                                                <div class="form-group">      
                                                    <s:label cssClass="consultantAlingment" >Available&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:select cssClass="ConsultBoxStyles" value="%{consultantVTO.consult_available}" list="#@java.util.LinkedHashMap@{'Y':'Yes','N':'No'}" disabled="true"></s:select>
                                                </div>
                                                <div class="form-group">
                                                    <s:label cssClass="consultantAlingment" >Middle Name&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_midname}"></s:textfield>
                                                </div>
                                                <div class="form-group">
                                                    <s:label cssClass="consultantAlingment" >Mobile No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>  
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_mobileNo}" readonly="true"></s:textfield>
                                                </div>
                                                <div class="form-group">
                                                    <s:label class="labelStylereq" >SSN No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_ssnNo}" readonly="true"></s:textfield>
                                                </div>
                                                

                                            </div>
                                            <div class="col-lg-4" >
                                                <div class="form-group">
                                                    <s:label cssClass="consultantAlingment" >Available Date&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_favail}" readonly="true"></s:textfield>
                                                </div>
                                                <div class="form-group">
                                                    <s:label cssClass="consultantAlingment" >Last Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label> 

                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_lstname}" readonly="true"></s:textfield>
                                                </div>
                                                <div class="form-group">      
                                                    <s:label cssClass="consultantAlingment" >Home Phone&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_homePhone}" readonly="true"></s:textfield>
                                                </div>
                                                <div class="form-group">
                                                    <s:label cssClass="consultantAlingment" >Living Country&nbsp;&nbsp;&nbsp;:</s:label>
                                                    <s:select cssClass="ConsultBoxStyles" value="%{consultantVTO.consult_lcountry}" list="country" disabled="true"></s:select>
                                                </div>
                                                
                                            </div>


                                            <!-- Contact Information , start  -->
                                            <div class="col-lg-12" style="background-color: #dfdfdf;Border: 2px solid #876" >
                                            <div class="col-lg-6" >

                                                <div id="contactInfoDiv"> 
                                                    <div class="contactInfoDiv">
                                                        <table>
                                                            <tr id="trStyleContact"><td>&nbsp;&nbsp;Permanent Address &nbsp;&nbsp;</td></tr>
                                                        </table>
                                                    </div>
                                                    <s:hidden label="Same as Permanent Address" name="consult_checkAddress"  id="consult_checkAddress" value="%{consultantVTO.address_flag}" onclick="sameAsAddress();"   ></s:hidden>
                                                    <br/>
                                                    <div class="col-lg-10 col-md-offset-1">
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment " >Address&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:property  value="%{consultantVTO.consult_Address}"></s:property>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass=" consultantAlingment" >Address2&nbsp;&nbsp;:</s:label> 

                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_Address2}" readonly="true"></s:textfield>
                                                        </div>
                                                        <div class="form-group">      
                                                            <s:label cssClass="consultantAlingment " >City&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_City}" readonly="true"></s:textfield>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment " >Country&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:select cssClass="ConsultBoxStyles" value="%{consultantVTO.consult_Country}" list="country" disabled="true"></s:select>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment " >State&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:select cssClass="ConsultBoxStyles" value="%{consultantVTO.consult_State}" list="permanentState" disabled="true"></s:select>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment" >Zip&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_Zip}" readonly="true"></s:textfield>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6" >

                                                <div id="contactInfoDiv">
                                                    <div class="contactInfoDiv">
                                                        <table>
                                                            <tr id="trStyleContact" ><td>&nbsp;&nbsp;Current Address &nbsp;&nbsp;</td></tr>
                                                        </table>
                                                    </div>
                                                    <br/>
                                                    <div  class="col-lg-10 col-md-offset-1">
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment" >Address&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_CAddress}" id="consult_CAddress" readonly="true"></s:textfield>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment" >Address2&nbsp;&nbsp;:</s:label> 

                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_CAddress2}" readonly="true"></s:textfield>
                                                        </div>
                                                        <div class="form-group">      
                                                            <s:label cssClass="consultantAlingment" >City&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_CCity}" readonly="true"></s:textfield>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment" >Country&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:select cssClass="ConsultBoxStyles" value="%{consultantVTO.consult_CCountry}" list="country" disabled="true"></s:select>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment" >State&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:select cssClass="ConsultBoxStyles" value="%{consultantVTO.consult_CState}" list="currentState" disabled="true"></s:select>
                                                        </div>
                                                        <div class="form-group">
                                                            <s:label cssClass="consultantAlingment" >Zip&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</s:label>
                                                            <s:textfield cssClass="consultantAlingment" value="%{consultantVTO.consult_CZip}" readonly="true"></s:textfield>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                        </div>
                                            <s:if test="%{consultFlag!='vendor'}">

                                                <s:hidden   value="%{consultantVTO.consult_description}"   id="consult_description"  name="consult_description"/>
                                                <s:hidden  name="consult_jobTitle" value="%{consultantVTO.consult_jobTitle}" id="consult_jobTitle" />                                      
                                                <s:hidden  value="%{consultantVTO.consult_industry}" name="consult_industry" id="consult_industry"    headerKey="-1"  list="industry" />
                                                <s:hidden  value="%{consultantVTO.consult_organization}" name="consult_organization" id="consult_organization"   headerKey="-1"  list="organization" />
                                                <s:hidden  value="%{consultantVTO.consult_salary}" name="consult_salary" id="consult_salary" />
                                                <s:hidden  value="%{consultantVTO.consult_experience}" name="consult_experience" id="consult_experience"    headerKey="-1"  list="experience" />
                                                <s:hidden  value="%{consultantVTO.consult_workPhone}"  name="consult_workPhone" id="consult_workPhone" /> 
                                                <s:hidden  value="%{consultantVTO.consult_referredBy}" name="consult_referredBy" id="consult_referredBy" />
                                                <s:hidden  value="%{consultantVTO.consult_status}" name="consult_status" id="consult_status" headerKey="-1"  list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" />
                                                <s:hidden  value="%{consultantVTO.consult_wcountry}"  name="consult_wcountry" id="consult_wcountry"  headerKey="-1"  list="country" />
                                                <s:hidden  value="%{consultantVTO.consult_preferredCountry}"   name="consult_preferredCountry" id="consult_preferredCountry"  headerKey="-1"  list="country" onchange="ConsultantEditPreferredStateChange()"  />
                                                <s:hidden  value="%{consultantVTO.consult_preferredState}"  name="consult_preferredState" id="consult_preferredState" headerKey="-1"  list="preState" />

                                            </s:if>

                                            <s:hidden name="downloadFlag" id="downloadFlag" value="%{downloadFlag}"/>
                                            <s:hidden name="consultFlag" id="consultFlag" value="%{consultFlag}"/>
                                            <s:hidden id="consult_id" name="consult_id" value="%{consult_id}"/>
                                            <s:hidden name="consultAttachment" id="consultAttachment"/>
                                            <s:hidden id="requirementId" name="requirementId" value="%{consultantVTO.requirementId}"/>
                                            <s:hidden id="accountSearchID" name="accountSearchID" value="%{consultantVTO.accountSearchID}"/>
                                            <s:hidden id="accountFlag" name="accountFlag" value="%{accountFlag}"/>
                                            <s:hidden id="customerFlag" name="customerFlag" value="%{customerFlag}"/>
                                            <s:hidden id="jdId" name="jdId" value="%{consultantVTO.jdId}"/>

                                            <div class="col-lg-6" style="margin-top: 7px">
                                                <div class="form-group" style="color:#5C85FF"> 
                                                    <s:label cssClass="consultantAlingment" style="margin-left:10px;"><font style="color: black">Skills&nbsp;&nbsp;:</font></s:label>
                                                    <s:property   value="%{consultantVTO.consult_skill}"></s:property>
                                                </div>
                                            </div>

                                            <div class="col-lg-6"> 
                                                <div class="form-group">
                                                <s:hidden id="acc_attachment_id" name="acc_attachment_id" value="%{consultantVTO.consult_acc_attachment_id}" />
                                                <s:label  cssClass=" " style=" "><figcaption>Download Consultant Resume&nbsp;&nbsp;:<button type='button' style="margin-left: 1vw"   onclick=consultAttachmentDownload()><img src='../../includes/images/download.png' height=25 width=25 ></button></figcaption>
                                                </s:label>
                                                <s:hidden cssClass="consultantAlingment"  value="%{consultantVTO.consult_attachment_name}"  readonly="true"></s:hidden>
                                                <s:if test='downloadFlag=="noResume"'>
                                                    <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                                    </s:if>
                                                    <s:if test='downloadFlag=="noFile"'>
                                                    <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                                    </s:if>  
                                                </div>
                                            </div>
                                            <div class=" col-lg-12" >
                                                <div class="form-group" style="color:#5C85FF">
                                                    <s:label  cssClass="consultantAlingment"><font style="color: black">Comments&nbsp;&nbsp;:</font></s:label>
                                                    <s:property  value="%{consultantVTO.consult_comments}"  />
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!--                            </div>-->


                            </div>
                        </div>     
                    </div>
                    </section><!--/form-->
                    <footer id="footer"><!--Footer-->
                        <div class="footer-bottom" id="footer_bottom">
                            <div class="container">
                                <s:include value="/includes/template/footer.jsp"/>
                            </div>
                        </div>
                    </footer><!--/Footer-->
                    
                    <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>
                    <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script> 
                    
                   
                   
                    <script>
                        setTimeout(function(){              
                            $('#resume').remove();
                        },3000);
                    </script>
                    <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
                    </body>
                    </html>



