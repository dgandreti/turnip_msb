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
        <title>ServicesBay :: Questions&nbsp;Search&nbsp;Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
      

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
    
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
    
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/onlineexam/onlineexamAjax.js"/>"></script>


        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/javascript">
            var myCalendar;
            function sortables_init() {
              
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
        <script>
            $(document).ready(function(){

              
                document.getElementById("loadingQuestionsSearch").style.display="none";
                
            });
        </script>





    </head>
    <body oncontextmenu="return false">
        <div id="wrap">
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
                            <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="questionOverlay_popup_close" onclick="questionOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                        </table>
                    </div>

                    <div class="col-sm-12">
                        <center>    <div  id="qname" style="display:none;margin-top: 1vw;">

                            </div></center>

                        <div id="hideText" >
                            <s:textarea name="quesDetails" id="quesDetails"   disabled="true" cssClass="form-control "/> 
                        </div>

                        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                    </div>
                    
                    
                </div>


            </div>

            <div id="main">
                <section id="generalForm"><!--form-->
                    <div  class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <!-- content start -->
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="col-lg-12 ">
                                        <div class="" id="profileBox" style="float: left;">
                                            <div class="backgroundcolor" style="margin-bottom: -1vw;">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                     
                                                        <font color="#ffffff">Questions Search</font>
                                                        <i id="updownArrow" onclick="toggleContent('questionssearch')" class="fa fa-minus"></i> 
                                                    </h4>
                                                </div>
                                            </div>
                                            <span> <br/></span>
                                            <!-- content start -->
                                            <span id="customerDashValidation"> <br/></span>
                                            <div class="col-sm-12" id="questionssearch">
                                                <s:form action="getQuestionsSearchList" method="post" theme="simple">
                                                    <s:hidden id="userSessionId" name="userSessionId" value="%{userSessionId}"/>
                                                    <s:hidden id="userOrgSessionId" name="userOrgSessionId" value="%{userOrgSessionId}"/>
                                                    <s:hidden id="search"  value="search"/>
                                                    <div class="inner-reqdiv-elements">
                                                        <div class="row">
                                                           
                                                                <div class="col-sm-4">
                                                                    <label class="" style="color:#56a5ec;">Question </label>
                                                                    <s:textfield cssClass="form-control" id="question"
                                                                                 name="question" placeholder="Enter Question" 
                                                                                 tabindex="1"
                                                                                 />
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <label class="" style="color:#56a5ec;">ExamType </label>

                                                                    <s:select id="examType" cssClass="form-control SelectBoxStyles" name="examType"  list="#@java.util.LinkedHashMap@{'T':'Technical','S':'Psychometric'}" value="%{editQues.examType}" onchange="return skillValues(this.value);" tabindex="2" />
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <label class="" style="color:#56a5ec;">Skill </label>

                                                                    <s:select id="skillCategoryValue" cssClass="form-control SelectBoxStyles" name="skillCategoryValue" headerKey="-1" headerValue="All" list="skillValuesMap" tabindex="3" />
                                                                </div>

                                                                <div class="col-sm-4">
                                                                    <label class="" style="color:#56a5ec;">Status </label>

                                                                    <s:select id="status" cssClass="form-control SelectBoxStyles" name="status" headerKey="DF" headerValue="All" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" tabindex="4" />
                                                                </div>


                                                            
                                                         
                                                                <div class="col-sm-4">
                                                                    <label class="" style="color:#56a5ec;">Level </label>

                                                                    <s:select id="level" cssClass="form-control SelectBoxStyles" name="level" headerKey="DF" headerValue="All" list="#@java.util.LinkedHashMap@{'L':'Low','M':'Medium','H':'High'}" tabindex="5"
                                                                              />
                                                                </div>

                                                                <div class="col-sm-4">
                                                                    <label class="" style="color:#56a5ec;">Answer&nbsp;Type </label>

                                                                    <s:select id="answerType" cssClass="form-control SelectBoxStyles" name="answerType" headerKey="DF" headerValue="All"  list="#@java.util.LinkedHashMap@{'S':'Single','M':'Multiple'}" tabindex="6" />
                                                                </div>

                                                                <div class="pull-right">

                                                                <div class="col-sm-3 pull-right">
                                                                   
                                                                       
                                                                            <label class="" style="color:#56a5ec;"></label> 

                                                                            <s:submit id="searchQuestionsForm" type="button" cssClass="add_searchButton form-control pull-right" value="Search" style="margin:5px 0px;width:100px" tabindex="9" ><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                       
                                                                       
                                                                    </div> 

                                                                    <div class="col-sm-3 pull-right">
                                                                      
                                                                        
                                                                                <label class="" style="color:#56a5ec;"></label> 
                                                                                
                                                                       
                                                                            <a id="addEditQuestions" href='doAddOrEditExamQues.action' class="add_searchButton form-control pull-right" value="" style="margin:5px 0px; width:100px" tabindex="8" ><i class="fa fa-plus-square"></i>&nbsp;Add</a>
                                                                   
                                                                        
                                                                    </div>
                                                                    <div class="col-sm-3 pull-right">
                                                                      
                                                                    
                                                                                <label class="" style="color:#56a5ec;"></label> 

                                                                            
                                                                          <a id="uploadQuestions " href='getSkillDetails.action?uploadFlag=uploadFlag' class=" fa fa-upload add_searchButton form-control pull-right" value="" style="margin:5px 0px;width:145px" tabindex="7" >UploadQuestions</a>
                                                                        
                                                                       
                                                                    </div>
                                                        </div>
                                                               





                                                       
                                                        </div>
                                                    </div>





                                                </s:form>
                                            </div>

                                           

                                            
                                            <span> <br/></span>
                                            <div id="loadingQuestionsSearch" class="loadingImg">
                                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                                </div> 
                                              
                                            <div class="col-sm-12" >

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

                                                                    <td><center><s:a href='%{#addOrEditUrl}'><i class="fa fa-pencil-square-o fa-size"></i></s:a></center></td>
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


                                 

                                </div>

                            </div>
                            <br>

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
                // alert(recordPage)
                $('#QuestionsList').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#QuestionsList').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script>
            sortables_init();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
