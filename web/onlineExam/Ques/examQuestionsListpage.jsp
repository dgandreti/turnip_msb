<%--
    Document   : Vendor Dashboard
    Created on : July 01, 2015, 07:10:41 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Dashboard Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <%-- <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
             <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <%--        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>--%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/onlineexam/onlineexamAjax.js"/>"></script>


        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/javascript">
            var myCalendar;
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("QuestionsList");
                sortableTableRows = document.getElementById("QuestionsList").rows;
                sortableTableName = "QuestionsList";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };

        </script>






    </head>
    <body onload="skillValues(examType.value);">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>

        </header>
        <div id="questionOverlay_popup" class="quesDescOverlay">
            <div id="quesOverlayBox">
                <div class="backgroundcolor">
                    <table>
                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Question&nbsp;&nbsp; </font></h4></td>
                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="questionOverlay_popup_close" onclick="questionOverlay()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                    </table>
                </div>

                <div>
                    <center>    <div id="qname" style="display:none;margin-top: 1vw;">

                        </div></center>

                    <span id="hideText">
                        <s:textarea name="quesDetails" id="quesDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 
                    </span>


                </div>
                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
            </div>


        </div>

        <s:include value="/includes/menu/LeftMenu.jsp"/>
        <section id="generalForm"><!--form-->
            <div  class="container">
                <div class="row">
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left;">
                                    <div class="backgroundcolor" style="margin-bottom: -1vw;">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Questions Search</font>
                                                <i id="updownArrow" onclick="toggleContent('customerDashboardForm')" class="fa fa-angle-up"></i> 
                                            </h4>
                                        </div>
                                    </div>
                                    <span> <br/></span>
                                    <!-- content start -->
                                    <span id="customerDashValidation"> <br/></span>
                                    <div class="col-sm-12">
                                        <s:form action="getQuestionsSearchList" method="post" theme="simple">
                                            <s:hidden id="userSessionId" name="userSessionId" value="%{userSessionId}"/>
                                            <s:hidden id="userOrgSessionId" name="userOrgSessionId" value="%{userOrgSessionId}"/>
                                            <s:hidden id="search"  value="search"/>
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-3">
                                                            <label class="" style="color:#56a5ec;">Question: </label>
                                                            <s:textfield cssClass="form-control" id="question"
                                                                         name="question" placeholder="Enter Question" 

                                                                         />
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="" style="color:#56a5ec;">ExamType: </label>

                                                            <s:select id="examType" cssClass="form-control SelectBoxStyles" name="examType"  list="#@java.util.LinkedHashMap@{'T':'Technical','S':'Psychometric'}" value="%{editQues.examType}" onchange="return skillValues(this.value);"/>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label class="" style="color:#56a5ec;">Skill: </label>

                                                            <s:select id="skillCategoryValue" cssClass="form-control SelectBoxStyles" name="skillCategoryValue" headerKey="-1" headerValue="All" list="skillValuesMap"/>
                                                        </div>

                                                        <div class="col-lg-3">
                                                            <label class="" style="color:#56a5ec;">Status: </label>

                                                            <s:select id="status" cssClass="form-control SelectBoxStyles" name="status" headerKey="DF" headerValue="All" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"/>
                                                        </div>


                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-3">
                                                            <label class="" style="color:#56a5ec;">Level: </label>

                                                            <s:select id="level" cssClass="form-control SelectBoxStyles" name="level" headerKey="DF" headerValue="All" list="#@java.util.LinkedHashMap@{'L':'Low','M':'Medium','H':'High'}"
                                                                      />
                                                        </div>

                                                        <div class="col-lg-3">
                                                            <label class="" style="color:#56a5ec;">Answer&nbsp;Type </label>

                                                            <s:select id="answerType" cssClass="form-control SelectBoxStyles" name="answerType" headerKey="DF" headerValue="All"  list="#@java.util.LinkedHashMap@{'S':'Single','M':'Multiple'}"/>
                                                        </div>



                                                        <div class="col-lg-2">
                                                            <div class="row">
                                                                <div class="col-lg-11">
                                                                    <label class="" style="color:#56a5ec;"></label> 

                                                                    <s:submit type="button" cssClass="add_searchButton form-control" value="Search" style="margin:5px 0px;"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                </div>
                                                            </div>
                                                        </div> 

                                                        <div class="col-lg-2">
                                                            <div class="row">
                                                                <div class="col-lg-11">
                                                                    <label class="" style="color:#56a5ec;"></label> 

                                                                    <s:a href='doAddOrEditExamQues.action'><button  type="button" class="add_searchButton form-control" value="" style="margin:5px 0px;"><i class="fa fa-search"></i>&nbsp;Add</button></s:a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <div class="row">
                                                                <div class="col-lg-11">
                                                                    <label class="" style="color:#56a5ec;"></label> 

                                                                    <s:a href='getSkillDetails.action?uploadFlag=uploadFlag'><button  type="button" class="add_searchButton form-control" value="" style="margin:5px 0px;">Upload Questions</button></s:a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <%-- <div class="col-lg-2">
                                                            <a href="getSkillDetails.action"  class="cssbutton req_margin"  />UploadQuestions</a>  
    </div>--%>





                                                    </div>
                                                </div>
                                            </div>
                                        </div>




                                    </s:form>

                                    <%-- <div class="col-lg-12">
                                        <div class="col-sm-4 "> </div>
                                        <s:form action="/onlineExam/Ques/getSkillDetails">

                                            <div class="col-sm-4 ">  
                                                <s:submit  cssClass="cssbutton req_margin"  type="submit" value="UploadQuestions" cssStyle="margin-left: 13vw;"/>

                                            </div></s:form>
                                            <div class="col-sm-4 "> </div>

                                        </div> --%>


                                    <span> <br/></span>
                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                    <div class="col-sm-12">

                                        <s:form>
                                            <s:hidden id="accountSearchID" value="%{id}" ></s:hidden>
                                            <div class="emp_Content" id="emp_div" align="center" style="display: none">
                                                <table id="QuestionsList" class="responsive CSSTable_task sortable" border="5">
                                                    <tbody>
                                                        <tr>

                                                            <th class="unsortable"><center> Edit </center> </th> 
                                                    <th class="unsortable"><center> Question</center> </th>
                                                    <th class="unsortable"><center> Skill</center> </th>
                                                    <th class="unsortable"><center> Status</center> </th>
                                                    <th><center>Level</center> </th>
                                                    <th><center>Answer&nbsp;Type</center> </th>

                                                    </tr>
                                                    <s:if test="skills.size == 0">
                                                        <tr>
                                                            <td colspan="6"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                        </tr>
                                                    </s:if>
                                                    <s:iterator value="skills"> 
                                                        <s:url id="addOrEditUrl" action="doAddOrEditExamQues.action">
                                                            <s:param name="quesId"><s:property value="quesId"></s:property></s:param>
                                                        </s:url>

                                                        <tr>
                                                            <s:hidden id="quesId" name="quesId" value="%{quesId}"/>
                                                            <s:hidden id="isPic" name="isPic" value="%{isPic}"/>

                                                            <td><s:a href='%{#addOrEditUrl}'><img src="<s:url value="/includes/images/edit_Msb.png"/>" height="20" width="20"></s:a></td>
                                                                <s:hidden id="question_Overlay" value="%{question}"/>
                                                                <s:if test="isPic == true">

                                                                <td><s:a href="#" cssClass="questionOverlay_popup_open" onclick="questionOverlayImage();getImagePath('%{quesId}','%{question}');" >Click&nbsp;to&nbsp;view</s:a></td>
                                                            </s:if>
                                                            <s:elseif test="question.length()>70">  

                                                                <td><s:a href="#" cssClass="questionOverlay_popup_open" onclick="questionOverlay('%{question}')" ><s:property  value="%{question.substring(0,70)}"/></s:a></td>
                                                            </s:elseif>
                                                            <s:else>
                                                                <td><s:a href="#" cssClass="questionOverlay_popup_open" onclick="questionOverlay('%{question}')" ><s:property  value="%{question}"/></s:a></td>
                                                            </s:else>
                                                            <td><s:property value="skillCategoryValue"></s:property></td>
                                                            <td><s:property value="status"></s:property></td>
                                                            <td><s:property value="level"></s:property></td>
                                                            <td><s:property value="answerType"></s:property></td>
                                                        </tr>
                                                    </s:iterator>

                                                    </tbody>
                                                </table>
                                                <br/>
                                                <div>
                                                    <div class="col-lg-6">
                                                        <s:if test="skills.size > 0">
                                                            <label> Display <select id="paginationOption"  class="disPlayRecordsCss"  onchange="pagerOption()" style="width: auto">

                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                    <option>100</option>
                                                                </select>Questions&nbsp;per&nbsp;page

                                                            </label>
                                                        </s:if>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div id="ques_pageNavPosition" align="right" style="margin-right:0vw"></div>
                                                    </div>
                                                </div> 
                                            </s:form>
                                            <script type="text/javascript">
                                                var pager = new Pager('quesResults', 10);
                                                pager.init();
                                                pager.showPageNav('pager', 'quesPageNavPosition');
                                                pager.showPage(1);
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <%--close of future_items--%>

                        </div>

                    </div>
                    <br>

                </div>

            </div>
            <!-- content end -->

        </section><!--/form-->
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->

        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 


        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                // alert(recordPage)
                $('#QuestionsList').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#QuestionsList').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script>
            sortables_init();
        </script>

    </body>
</html>
