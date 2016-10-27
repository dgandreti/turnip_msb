<%-- 
    Document   : dashboard
    Created on : Feb 3, 2015, 7:50:23 PM
    Author     : Nagireddy
--%>



<%@page import="com.mss.msp.recruitment.ConsultantVTO"%>

<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@page import="java.util.List" isErrorPage="true"%>
<%@page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>


        <!-- new styles -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Consultant Search Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>

        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script>
            var pager;   //this pagination for consultant search
            function consultantPage(){
                
                var paginationSize = 10; //parseInt(document.getElementById("cpaginationOption").value);
                pager = new Pager('task_results', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'taskpageNavPosition');
                //document.getElementById("cpaginationOption").value=10;
                pager.showPage(1);
                document.getElementById("loadingConsultantSearch").style.display="none";
            };
           
            
            function submmition(){
                var skillCategoryArry = [];    
                $("#skillCategoryValue :selected").each(function(){
                    skillCategoryArry.push($(this).text()); 
                });
                // alert("skillCategoryArry");
                document.getElementById("skillValues").value=skillCategoryArry;
                var v=document.getElementById("skillValues").value;
    
                return true;
            }
        </script>
        <sx:head />



    </head>





    <body style="overflow-x: hidden" oncontextmenu="return false" onload="consultantPage();StateChange();jumper();">
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


                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="col-sm-14 ">
                                        <div class="" id="selectivityProfileBox" style="float: left; margin-top: 5px">
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">

                                                        <font color="#ffffff">Consultant Search</font>
                                                        <i id="updownArrow" onclick="toggleContent('getConsultant')" class="fa fa-minus"></i> 

                                                    </h4>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <s:form action="getConsultant" theme="simple">
                                                        <span id="validationMessage"></span>
                                                       
                                                        <s:hidden name="gridDownload" id="gridDownload" value="%{gridPDFDownload}"/>
                                                        <div class="inner-reqdiv-elements">

                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Name</label>
                                                                <s:textfield cssClass="form-control" name="consult_name" id="consult_name" placeholder="Name" value="%{consult_name}" tabindex="1" maxLength="60"/>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Email Id</label>
                                                                <s:textfield cssClass="form-control" name="consult_email" id="consult_email" placeholder="Email" value="%{consult_email}" tabindex="2" maxLength="60"/>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                                <s:select cssClass="SelectBoxStyles form-control" name="consult_status" id="consult_status" headerKey="-1" headerValue="-- Select status--"  list="{'Active','In-Active'}" tabindex="3"/>
                                                            </div>


                                                            <s:hidden name="consultState" id="consultState" value="%{consult_State}"/>
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Country</label>
                                                                <s:select cssClass="form-control SelectBoxStyles" name="consult_Country" id="consult_Country" headerKey="-1" headerValue="All" list="consult_WCountry" onchange="StateChange()" tabindex="4" />
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">State</label>
                                                                <s:select cssClass="form-control SelectBoxStyles" name="consult_State" id="consult_State"  headerKey="-1" headerValue="Select state" list="{}"  tabindex="5" value="%{consult_State}"/> 
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">City</label>
                                                                <s:textfield cssClass="form-control" name="consult_City" id="consult_City" placeholder="Enter City"  tabindex="6" />
                                                            </div>


                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Phone</label>
                                                                <s:textfield cssClass="form-control" name="consult_phno" id="consult_phno" placeholder="Phone" value="%{consult_phno}" tabindex="7" maxLength="15"/>
                                                            </div>
                                                            
                                                            <div class="col-sm-4">
                                                                <label class="labelStylereq" style="color:#56a5ec">Experience</label>
                                                                <s:textfield cssClass="form-control" name="yearExperience" id="yearExperience" placeholder="Experience" value="" tabindex="8" maxLength="15"/>
                                                            </div>

                                                            <s:if test="consultantFlag == 'Team' || consultantFlag == 'All'">
                                                                <div class="col-sm-4" >
                                                                    <label class="labelStylereq" style="color:#56a5ec;margin-right: 10px;" >Members</label>
                                                                    <s:textfield cssClass="form-control" id="enameForRecruitment"  name="enameForRecruitment" onkeyup="return getEmpRecruitment();" autocomplete='off' maxLength="30" tabindex="8" placeholder="Name" onfocus="return removeErrorMsgForTechie();"/>
                                                                    <s:hidden cssClass="form-control" id="enameIdForRecruitment"  name="enameIdForRecruitment"  autocomplete='off' maxLength="30" onfocus="return removeErrorMsgForTechie();"/>
                                                                </div>
                                                            </s:if>
                                                            <s:hidden name="consultantFlag" id="consultantFlag" value="%{consultantFlag}"/>
                                                            <div class="col-sm-4">
                                                                <label class="labelStyle" id="labelLevelStatusReq">Specialization </label> <s:select cssClass="" name="skillCategoryValue" tabindex="9"  id="skillCategoryValue" list="skillValuesMap" multiple="true"/> 
                                                                <s:hidden id="skillValues" name="skillValues" />
                                                            </div>
                                                            <div class="col-sm-4"></div>
                                                            <div class="col-sm-4"></div>
                                                            <div class="col-sm-2 pull-right">
                                                                <label class="labelStylereq" style=""></label>
                                                                <s:submit type="button" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" tabindex="10" id="searchButton" value="" onclick="submmition();"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                                </div>

                                                            </div>


                                                            <br>
                                                    </s:form>
                                                </div>
                                            </div>
                                            <div id="responseMessage" style="color: green;margin-left: 2%"></div>
                                            <div class="col-sm-12">
                                                <div id="loadingConsultantSearch" class="loadingImg">
                                                    <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                                </div>
                                                <s:form>
                                                    <div class="task_content" id="task_div" align="center" style="display: none" >

                                                        <div>
                                                            <div>

                                                                <table id="task_results" class="responsive CSSTable_task" border="5"cell-spacing="2">

                                                                    <tbody>
                                                                        <tr>
                                                                            <th>Name</th>
                                                                            <th>E-Mail</th>
                                                                            <th>Experience</th>
                                                                            <th>Skill Set</th>
                                                                            <th>Phone No</th>
                                                                            <th>Status</th>
                                                                        </tr>
                                                                       
                                                                        <s:if test="ConsultantListDetails.size == 0">
                                                                            <s:hidden id="rec_exits" value="no"/>
                                                                            <tr>
                                                                                <td colspan="10"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                            </tr>
                                                                        </s:if>
                                                                        <s:iterator  value="ConsultantListDetails">
                                                                            <%
                                                                                String strFlag = request.getParameter("consultantFlag");
                                                                            %>
                                                                            <!--build url TO goto Account Details-->
                                                                            <s:url var="myUrl" action="editConsultantDetails.action?consultFlag=vendor">
                                                                                <s:param name="consult_id" value="%{consult_id}"/>  
                                                                                <s:param name="consultantFlag"><%= strFlag%></s:param>
                                                                            </s:url>
                                                                            <tr>
                                                                                <s:hidden id="rec_exits" value="yes"/>
                                                                                <td> <s:a href='%{#myUrl}'><s:property value="%{consult_name}"></s:property></s:a></td>
                                                                                <td><s:property value="consult_email"></s:property></td>
                                                                                 <td><s:property value="yearExperience"></s:property></td>
                                                                                <s:if test="%{consult_skill.length()>8}">
                                                                                    <td><s:a href="#" cssClass="consultantSkillOverlay_popup_open" onclick="consultantSkillSetOverlay('%{consult_skill}')"><s:property value="consult_skill.substring(0,8)"></s:property></s:a></td>
                                                                                </s:if>
                                                                                <s:else>
                                                                                    <td><s:a href="#" cssClass="consultantSkillOverlay_popup_open" onclick="consultantSkillSetOverlay('%{consult_skill}')"><s:property value="consult_skill"></s:property></s:a></td>
                                                                                </s:else>
                                                                                <%--<td><s:property value="consult_salary"></s:property></td>--%>
                                                                                <td><s:property value="consult_phno"></s:property></td>
                                                                                <td><s:property value="consult_status"></s:property></td>
                                                                                </tr>
                                                                        </s:iterator>
                                                                    </tbody> 
                                                                </table>
                                                                <br/>

                                                                <s:if test="ConsultantListDetails.size > 0"><label> Display <select id="cpaginationOption" class="disPlayRecordsCss" onchange="cpagerOption()" style="width: auto">
                                                                            <option>10</option>
                                                                            <option>15</option>
                                                                            <option>25</option>
                                                                            <option>50</option>
                                                                        </select>
                                                                        Consultants per page
                                                                    </label>
                                                                </s:if>
                                                                <div align="right" id="taskpageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                                <script type="text/javascript">
                                                                    var pager = new Pager('task_results', 10); 
                                                                    pager.init(); 
                                                                    pager.showPageNav('pager', 'taskpageNavPosition'); 
                                                                    pager.showPage(1);
                                                                </script>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="downloading_grid">
                                                        <div class="col-lg-2 pull-right">
                                                            <div onclick="downloadPDFConsultantList()" tabindex="11" class="fa fa-download cssbutton form-control">&nbsp;DownloadPDF</div>
                                                        </div>
                                                    </div>
                                                </s:form>

                                            </div>

                                            <br><br>


                                        </div>
                                    </div>
                                </div>
                                <div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <%--  Skill Overlay --%>
                    <div id="consultantSkillOverlay_popup">
                        <div id="consultantSkillSetBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill Details&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="consultantSkillOverlay_popup_close" onclick="consultantSkillOverlayClose()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div>

                                <s:textarea name="skillSetDetails"   id="consultantSkillSetDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                            </div>
                            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                        </div>
                    </div>
                    <%--close of overlay --%> 
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script> 
        <script>
            
            $('#skillCategoryValue').selectivity({
                    
                multiple: true,
                placeholder: 'Type to search skills'
            });
            
            
        </script>



        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=10;
            function cpagerOption(){

                var paginationSize = document.getElementById("cpaginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                // alert(recordPage)
                $('#task_results').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#task_results').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/javascript" >
            $("#consult_phno").mask("(999)-999-9999");
        </script>
        <script type="text/JavaScript">
            displayHide_downloadButtons();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:270px;left:700px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>

    </body>
</html>

