<%-- 
    Document   : All Requirement List
    Created on : May 7, 2015
    Author     : Praveen<pkatru@miraclesoft.com>
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Tech review details page</title>
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
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/vendorDetailsStyles.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
          <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">



        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/requirement.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/techReviewAjax.js"/>'></script>
         <script language="JavaScript" src='<s:url value="/includes/js/general/sweetalert.min.js"/>'></script>

        <script>
            var pager;
            // $(document).ready(function(){
            function techReview(){
                var paginationSize = 10;
                pager = new EmpPager('techReviewTable', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                // document.getElementById("paginationOption").value=10;
                pager.showPage(1);
            };
//            function pagerOption(){
//
//                paginationSize = document.getElementById("paginationOption").value;
//                if(isNaN(paginationSize))
//                    alert(paginationSize);
//
//                pager = new EmpPager('techReviewTable', parseInt(paginationSize));
//                pager.init();
//                pager.showPageNav('pager', 'pageNavPosition');
//                pager.showPage(1);
//
//            };
        </script>
    </head>
    <body style="overflow-x: hidden" onload="doOnLoad(); techReview();">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>
        </header>

        <s:include value="/includes/menu/LeftMenu.jsp"/>
        <section id="generalForm"><!--form-->
            <div  class="container">
                <div class="row">
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Tech Review Consultants List</font>
                                                <i id="updownArrow" onclick="toggleContent('techReviewDetailsForm')" class="fa fa-angle-up"></i> 
                                            </h4>
                                        </div>
                                    </div>
                                    <!-- content start -->
                                    <span id="techReviewValidation"></span>
                                    <div class="col-sm-12">
                                        <s:form  theme="simple" id="techReviewDetailsForm">
                                            <br>
                                            <%-- <s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />
                                            < div class="inner-reqdiv-elements">
                                                 <label class="" style="color:#56a5ec;">From:</label>  
                                                 <s:textfield cssClass=" reqInputStyle dateImage" name="reviewStartDate" id="reviewStartDate" placeholder="From Date"  tabindex="1"  onkeypress="return enterDateRepository();"/>
                                                 <label class="" style="color:#56a5ec;">To:</label>  
                                                 <s:textfield cssClass=" reqInputStyle dateImage" name="reviewEndDate" id="reviewEndDate" placeholder="To Date"  tabindex="1"  onkeypress="return enterDateRepository();"/>
                                                 <label class="">Status:&nbsp;</label><s:select cssClass="techReviewSelectStyle" name="techReviewStatus" id="techReviewStatus" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Processing':'Processing','ShortListed':'ShortListed','Rejected':'Rejected'}" />
                                                 <s:submit type="button" cssClass="cssbutton_emps "
                                                           value="Search" onclick="return getSearchTechReviewList()" cssStyle="margin:5px"/>
                                             </div--%> 
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <label class="labelStylereq" style="color:#56a5ec;">From: </label>
                                                        <s:textfield cssClass="form-control dateImage" name="reviewStartDate" id="reviewStartDate" placeholder="From Date"  tabindex="1"  onkeypress="return enterTechDateRepository(this);" autocomplete="off" onfocus="return removeErrorMsgForTechieConsultant();"/>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label class="labelStylereq" style="color: #56a5ec;">To: </label>
                                                        <s:textfield cssClass="form-control dateImage" name="reviewEndDate" id="reviewEndDate" placeholder="To Date"  tabindex="1"  onkeypress="return enterTechDateRepository(this);" autocomplete="off" onfocus="return removeErrorMsgForTechieConsultant();"/>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label class="labelStylereq" style="color: #56a5ec;">Status: </label>
                                                        <s:select cssClass="SelectBoxStyles form-control" name="techReviewStatus" id="techReviewStatus" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Processing':'Processing','ShortListed':'ShortListed','Rejected':'Rejected','Selected':'Selected'}" />
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <label class="labelStylereq" style="color: #56a5ec;"></label>
                                                        <s:submit type="button" cssClass="add_searchButton form-control "
                                                                  value="" onclick="return getSearchTechReviewList()" cssStyle="margin:5px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                        <s:hidden name="techSearch" id="techSearch" value="%{techSearch}"/>
                                                        <s:hidden name="downloadFlag" id="downloadFlag" value="%{downloadFlag}"/>
                                                    </div>
                                                </div>
                                            </div>

                                        </s:form>
                                    </div>
                                    <br>
                                    <s:form>
                                        <s:if test='downloadFlag=="noAttachment"'>
                                            <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                            </s:if>
                                            <s:if test='downloadFlag=="noFile"'>
                                            <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                            </s:if>
                                        <div class="task_content" id="task_div" align="center" style="display: none" >

                                            <div>
                                                <div>
                                                    <table id="techReviewTable" class="responsive CSSTable_task" border="5"cell-spacing="2">
                                                        <tbody>
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Email</th>
                                                                <th>Phone</th>
                                                                <th>Interview Type</th>
                                                                <th>Interview Date&Time</th>
                                                                <!--<th>Forwarded Date</th>-->
                                                                <th>Forwarded By</th>
                                                                <th>Status</th>
                                                                <th>Resume</th>
                                                                <th>Review</th>
                                                            </tr>
                                                            <s:if test="consultantList ==null">
                                                                <tr>
                                                                    <td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:iterator  value="consultantList">
                                                                <!--build url TO goto Account Details-->
                                                                <s:url id="myUrl" action="getConsultantDetails.action?consultFlag=customer&techReviewFlag=techReview">
                                                                    <s:param name="consult_id" value="%{consult_id}" />
                                                                </s:url>
                                                                <%--<s:hidden name="requirementId" id="requirementId" value="%{requirementId}" />--%>
                                                                <tr>
                                                                    <td><s:a href='%{#myUrl}'><s:property value="%{consult_name}"></s:property></s:a></td>
                                                                    <td><s:property value="consult_email"></s:property></td>
                                                                    <td><s:property value="consult_Phone"></s:property></td>
                                                                    <td><s:property value="reviewType"></s:property></td>
                                                                    <td><s:property value="scheduledDate"></s:property>&nbsp;&nbsp;<s:property value="scheduledTime"></s:property>&nbsp;&nbsp;<s:property value="zone"></s:property></td>
                                                                    
                                                                    <td><s:a href="#" cssClass="Forwarded_popup_open" onclick="showOverlayForwardedBy(%{forwardedById})"><s:property value="forwardedBy"></s:property></s:a></td>
                                                                    <%--<td><s:property value="forwardedBy"></s:property></td>--%>
                                                                    <%--<td><s:property value="forwardedDate"></s:property></td>--%>
                                                                    <td><s:property value="status"></s:property></td>
                                                                    <s:if test="status=='Rejected'">
                                                                        <td><figcaption><button style="cursor: default" type=button value="<s:property value="resumeId"></s:property>" ><img src='../../includes/images/download.png' height='20' width='20' style="opacity: 0.3"></button></figcaption></td>
                                                                <td><s:a href="#" cssClass=""  style="pointer-events: none; cursor: default; opacity: 0.5">Click</s:a></td>

                                                            </s:if>
                                                            <s:else>
                                                                <td><figcaption><button type=button value="<s:property value="resumeId"></s:property>" onclick=doTechReviewAttachmentDownload(<s:property value="resumeId"></s:property>)><img src='../../includes/images/download.png' height='20' width='20' ></button></figcaption></td>
                                                                <td><s:a href="#" cssClass="techReview_popup_open" onclick="techReviewOverlay(%{requirementId},\'%{status}\');techReviewDetailsOnOverlay(%{consult_id},%{conTechReviewId});">Click</s:a></td>
                                                            </s:else>
                                                            </tr>
                                                        </s:iterator>

                                                        </tbody>
                                                    </table>
                                                    <br/>
                                                    <label class="page_option "> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                            <option>10</option>
                                                            <option>15</option>
                                                            <option>25</option>
                                                            <option>50</option>
                                                        </select>
                                                        Reviews per page
                                                    </label>
                                                 
                                                </div>
                                                <script type="text/javascript">
                                                    var pager = new EmpPager('techReviewTable', 8); 
                                                    pager.init(); 
                                                    pager.showPageNav('pager', 'pageNavPosition'); 
                                                    pager.showPage(1);
                                                </script>
                                            </div>
                                        </div>
                                    </s:form>
                                </div>
                            </div>
                            <div id="Forwarded_popup">
                                <div id="recruiterBox" class="marginTasks">
                                    <div class="backgroundcolor">
                                        <table>
                                            <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Forwarded By Details&nbsp;&nbsp; </font></h4></td>
                                            <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="Forwarded_popup_close" onclick="closeForwardedByOverlay()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                                        </table>
                                    </div>
                                    <div><center>
                                            <table >
                                                <s:textfield label="Name" cssClass="form-control " id="recruiterNameOverlay" />
                                                <s:textfield label="Email Id" cssClass="form-control margin" id="recruiterEmailIdOverlay" />
                                                <s:textfield label="Phone" cssClass="form-control margin" id="recruiterPhoneOverlay" />
                                            </table>
                                        </center>
                                    </div>
                                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                </div>
                            </div>
                            <div id="techReview_popup">
                                <div id="techReviewBox" class="techReviewPopupStyle">
                                    <div class="backgroundcolor">
                                        <table>
                                            <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Tech Review Process&nbsp;&nbsp; </font></h4></td>
                                            <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="techReview_popup_close" onclick="techReviewOverlay()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                                        </table>
                                    </div>
                                    <%--form start from here --%>
                                    <div class="pull-right "><s:submit id="saveTechReview" cssClass="cssbutton" onclick="saveTechReviewResults();" value="Save"></s:submit></div>
                                        <span><e></e></span><br>
                                    <s:hidden name="consultId" id="consultId"/>
                                    <s:hidden name="requirementId" id="requirementId"  />
                                    <s:hidden name="interviewType" id="interviewType"  />
                                    <s:hidden name="contechId" id="contechId"  />

                                    <label class="headingLabel">Consultant Details:</label>
                                    <div id="reviewalignBox">
                                        <div class="inner-techReviewdiv-elements">
                                            <label class="popuplabel">Name : </label>
                                            <s:textfield type="text"
                                                         name="consultantName"
                                                         cssClass="techReviewInputStyle"
                                                         id="consultantName"
                                                         disabled="true"
                                                         />
                                            <label class="popuplabel">Title: </label>
                                            <s:textfield type="text"
                                                         name="consultantJobTitle"
                                                         cssClass="techReviewInputStyle"
                                                         id="consultantJobTitle"
                                                         disabled="true"
                                                         />
                                            <label class="">Email:&nbsp;&nbsp; </label>
                                            <s:textfield type="text"
                                                         name="consultantEmail"
                                                         cssClass="techReviewInputStyle"
                                                         id="consultantEmail"
                                                         disabled="true"
                                                         />
                                        </div>
                                        <div class="inner-techReviewdiv-elements">
                                            <label class="popuplabel">Mobile: </label>
                                            <s:textfield type="text"
                                                         name="consultantMobile"
                                                         cssClass="techReviewInputStyle"
                                                         id="consultantMobile"
                                                         disabled="true"
                                                         />
                                            <label class="popuplabel">Sch.Date:</label>
                                            <s:textfield type="text"
                                                         name="interviewDate"
                                                         cssClass="techReviewInputStyle"
                                                         id="interviewDate"
                                                         disabled="true"
                                                         />
                                            <label class="">Status:</label>

                                            <s:if test="#session.primaryrole == 6">
                                                <s:select cssClass="techReviewSelectStyle" 
                                                          name="finalTechReviewStatus" 
                                                          id="finalTechReviewStatus" 
                                                          list="#@java.util.LinkedHashMap@{'Rejected':'Rejected','ShortListed':'ShortListed','Processing':'Processing'}" 
                                                          />
                                            </s:if>
                                            <s:else>
                                                <s:select cssClass="techReviewSelectStyle" 
                                                          name="finalTechReviewStatus" 
                                                          id="finalTechReviewStatus" 
                                                          list="#@java.util.LinkedHashMap@{'Rejected':'Rejected','ShortListed':'ShortListed','Selected':'Selected','Processing':'Processing'}" 
                                                          />

                                            </s:else>

                                        </div>

                                    </div>
                                    <label class="headingLabel">Skill Details:</label>

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
                                    <label class="headingLabel">Rating Details:(**Rating between 1(min) to 10(max))</label>
                                    <span id="skillValid"></span>
                                    <div id="reviewalignBox">

                                        <div class="inner-techReviewdiv-elements required" id="skillRate">
                                            <label class="">Technical Skills: </label>
                                            <s:textfield type="text"
                                                         name="techSkill"
                                                         cssClass="ratingInputStyle"
                                                         id="techSkill"
                                                         value=""
                                                         onkeyup="skillRateValidation()"
                                                         onkeypress="return acceptNumbers(event);"
                                                         maxLength="11"
                                                         />

                                            <label class="">Domain Skills: </label>
                                            <s:textfield type="text"
                                                         name="domainSkill"
                                                         cssClass="ratingInputStyle"
                                                         id="domainSkill"
                                                         value=""
                                                         onkeyup="skillRateValidation()"
                                                         onkeypress="return acceptNumbers(event);"
                                                         maxLength="11"
                                                         />  
                                            <label class="">Communication Skills:</label>
                                            <s:textfield type="text"
                                                         name="comSkill"
                                                         cssClass="ratingInputStyle"
                                                         id="comSkill"
                                                         placeholder=""
                                                         value=""
                                                         onkeyup="skillRateValidation()"
                                                         onkeypress="return acceptNumbers(event);"
                                                         maxLength="11"
                                                         /> 
                                        </div>
                                    </div>
                                    <div class="required" id="CommentsDiv">
                                        <label class="headingLabel">Comments:</label>

                                        <div id="reviewalignBox">

                                            <div class="inner-techReviewdiv-elements">

                                                <s:textarea id="consultantComments"
                                                            name="consultantComments"
                                                            cssClass="reviewareacss"
                                                            type="text"
                                                            placeholder="Enter Comments Here"
                                                            onkeydown="techReviewsComments(this)" 
                                                            onblur="removeCommentsRemainMessage()"
                                                            value=""/>
                                                <div class="charNum" id="remainingCharsDiv"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                </div>
                                <%--close of future_items--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>        <!-- content end -->
        </section><!--/form-->
        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
           

        <script type="text/javascript">
            var flag=document.getElementById("techSearch").value;
            if(flag=="search")
            {
                // alert("in fi");
                getSearchTechReviewList();
            }
    
        </script>
        <script>
            setTimeout(function(){              
                $('#resume').remove();
            },3000);
        </script>
      
        <script language="JavaScript" src='<s:url value="/includes/js/general/popupoverlay.js"/>'></script>
   <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        
    <script type="text/javascript">
        var recordPage=10;
          function pagerOption(){
//alert("recordPage")
               var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                   {
                       
                   }
                recordPage=paginationSize;
                //alert(recordPage)
                 $('#techReviewTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
        $('#techReviewTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
    </body>
</html>
