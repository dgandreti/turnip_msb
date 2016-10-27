<%--
    Document   : Projects Budget Page
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
        <title>ServicesBay ::Projects Budget Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <%-- <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
             <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <%--script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <%-- <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>--%>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/dashBoardAjax.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/budgetAjax.js"/>'></script>
        <%--script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script--%>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sweetalert.min.js"/>'></script>


        <style>
            .budget{

                background: url('${pageContext.request.contextPath}/includes/images/budget.png')  no-repeat;
                background-size: 25px 26px;
                background-position: 77% 54%;
            }
        </style>

        <script>
            $(document).ready(function() {
                $(".img-swap").click(function() {
                    $(".popup_block").animate({
                        width: 'toggle'
                    });
                });
                document.getElementById("loadingBudjetSearch").style.display = "none";
            });





        </script>
        <script>
            $(document).ready(function() {
                var myselect = document.getElementById("oyear"), year = new Date().getFullYear();
                var gen = function(max) {
                    do {
                        myselect.add(new Option(year++, year - 1), null);
                        max--
                    } while (max > 0);
                }(10);
            });

        </script>
        <script type="text/javascript">
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName)
                    return;
                tbls = document.getElementById("projectBudgetTable");
                sortableTableRows = document.getElementById("projectBudgetTable").rows;
                sortableTableName = "projectBudgetTable";
                for (ti = 0; ti < tbls.rows.length; ti++) {
                    thisTbl = tbls[ti];
                    if (((' ' + thisTbl.className + ' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            }
            ;
        </script>

    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="loadPopup();" >
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
                                    <div class="col-lg-12 ">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                        <font color="#ffffff">Budgets Search</font>
                                                        <i id="updownArrow" onclick="toggleContent('projectbudgetForm')" class="fa fa-minus"></i> 
                                                    </h4>
                                                </div>
                                            </div>
                                            <span> <br/></span>
                                            <span id="budgetValidation"></span>
                                            <!-- content start -->
                                            <div class="col-sm-12">
                                                <s:form theme="simple" id="projectbudgetForm">
                                                    <div id="loadingProjectBudgets" class="loadingImg" style="display: none">
                                                        <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                                    </div>
                                                    <s:hidden name="addEditFlag" id="addEditFlag"/>
                                                    <s:hidden name="roleValue" id="roleValue" value="%{roleValue}"/>
                                                    <div class="inner-reqdiv-elements">
                                                        <div class="row">
                                                            <div class="col-sm-3">
                                                                <label class="" style="color:#56a5ec;">Year </label>
                                                                <s:textfield cssClass="form-control" id="budgetYear" tabindex="1"
                                                                             name="year" value="%{year}" onkeypress="return validationYear(event)"
                                                                             />
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <label class="" style="color:#56a5ec;">Projects </label>
                                                                <s:select cssClass="SelectBoxStyles form-control" tabindex="2" name="project" id="project" headerKey="-1" headerValue="All" list="projectsMap" />
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <label class="" style="color:#56a5ec;">Quarter </label>
                                                                <s:select cssClass="SelectBoxStyles form-control" tabindex="3" name="quarterId" id="quarterId" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Q1':'Q1','Q2':'Q2','Q3':'Q3','Q4':'Q4'}" />
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <label class="" style="color:#56a5ec;">Status </label>
                                                                <s:if test="roleValue!='Director'">
                                                                    <s:select cssClass="SelectBoxStyles form-control" tabindex="4" name="status" id="status" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Entered':'Entered','Submitted':'Submitted','Approved':'Approved','Rejected':'Rejected'}" />
                                                                </s:if>
                                                                <s:else>
                                                                    <s:select cssClass="SelectBoxStyles form-control" tabindex="4" name="status" id="status" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Submitted':'Submitted','Approved':'Approved','Rejected':'Rejected'}" />
                                                                </s:else>
                                                            </div>

                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-3 pull-right budget_search">
                                                                <s:a href="#" id="budgetSearch"><button type="button" tabindex="6" class="add_searchButton form-control" value="" style="margin:5px;padding:0" onclick="getProjectBudgetSearch();"><i class="fa fa-search"></i>&nbsp;Search</button></s:a>
                                                                </div>
                                                            <s:if test="roleValue!='Director'">
                                                                <div class="col-sm-3 pull-right contact_search">
                                                                    <s:a href="#" id="budgetAdd" cssClass="projectBudget_popup_open" onclick="projectBudgetOverlay('Add');"><button class="add_searchButton form-control" tabindex="5" style="margin:5px;padding:0" ><i class="fa fa-plus-square"></i>&nbsp;Add</button></s:a>
                                                                    </div>
                                                            </s:if>

                                                        </div>
                                                    </div>
                                                </div>
                                            </s:form>
                                            <span> <br/></span>
                                                <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                            <div id="loadingBudjetSearch" class="loadingImg">
                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                            </div>
                                            <div class="col-sm-12">

                                                <s:form>
                                                    <div class="emp_Content" id="emp_div" align="center"  >
                                                        <span><d></d></span>
                                                        <table id="projectBudgetTable" class="responsive CSSTable_task sortable" border="5">
                                                            <tbody>
                                                                <tr>
                                                                    <th>Project&nbsp;Name</th>
                                                                    <th>Cost&nbsp;Center&nbsp;Name</th>
                                                                    <th>Year</th>
                                                                    <th>Quarter</th>
                                                                    <th class="unsortable">Est&nbsp;Budget </th>
                                                                    <th class="unsortable">Available&nbsp;Budget </th>
                                                                    <th>Status</th>
                                                                    <th class="unsortable">Comments</th>
                                                                        <s:if test="roleValue!='Director'">
                                                                        <th class="unsortable">Delete</th>
                                                                        </s:if>
                                                                </tr>
                                                                <s:if test="projectBudgetList.size == 0">
                                                                    <tr>
                                                                        <td colspan="9"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:iterator  value="projectBudgetList">
                                                                    <tr>
                                                                        <s:if test="roleValue!='Director'">
                                                                            <s:if test="status=='Submitted'">
                                                                                <td><s:property value="projectName"></s:property></td>
                                                                            </s:if>
                                                                            <s:elseif test="status=='Approved'">
                                                                                <td><s:property value="projectName"></s:property></td>
                                                                            </s:elseif>
                                                                            <s:else>
                                                                                <td><s:a href="#" onclick="projectBudgetDetailsToViewOnOverlay('%{id}');projectBudgetOverlay('Edit');" cssClass="projectBudget_popup_open"><s:property value="projectName"></s:property></s:a></td> 
                                                                                <%--   <td><s:a href="#" onclick="projectBudgetDetailsToViewOnOverlay('%{id}');" cssClass="projectBudget_popup_open"><s:property value="projectName"></s:property></s:a></td>--%>
                                                                            </s:else>
                                                                        </s:if>
                                                                        <s:if test="roleValue=='Director'">
                                                                            <s:if test="status=='Submitted'">
                                                                                <td><s:a href="#" onclick="projectBudgetDetailsToViewOnOverlay('%{id}');projectBudgetOverlay('Edit');" cssClass="projectBudget_popup_open"><s:property value="projectName"></s:property></s:a></td>
                                                                            </s:if>
                                                                            <s:elseif test="status=='Approved'">
                                                                                <td><s:a href="#" onclick="projectBudgetDetailsToViewOnOverlay('%{id}');projectBudgetOverlay('Edit');" cssClass="projectBudget_popup_open"><s:property value="projectName"></s:property></s:a></td>
                                                                            </s:elseif>
                                                                            <s:else>
                                                                                <td><s:property value="projectName"></s:property></td>
                                                                            </s:else>
                                                                        </s:if>
                                                                        <td><s:property value="ccName"></s:property></td>        
                                                                        <td><s:property value="year"></s:property></td>
                                                                        <td><s:property value="quarterId"></s:property></td>
                                                                        <td> $ <s:property value="estimatedBudget"></s:property></td>
                                                                        <s:if test="%{remainingBudget!=0}">
                                                                            <td> $ <s:property value="remainingBudget"></s:property></td>
                                                                        </s:if>
                                                                        <s:else >
                                                                            <td> ---- </td>
                                                                        </s:else>
                                                                        <td><s:property value="status"></s:property></td>
                                                                        <s:if test="comments.length()>9">    
                                                                            <td><s:a href="#" cssClass="budgetCommentsOverlay_popup_open" onclick="budgetCommentsOverlay('%{comments}')"><s:property  value="%{comments.substring(0,10)}"/>...</s:a></td>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <td><s:a href="#" cssClass="budgetCommentsOverlay_popup_open" onclick="budgetCommentsOverlay('%{comments}')"><s:property  value="%{comments}"/></s:a></td>
                                                                        </s:else>
                                                                        <s:if test="roleValue!='Director'">
                                                                            <s:if test="status=='Submitted'">
                                                                                <td><center><i class="fa fa-trash-o fa-size" style="opacity: 0.2;"></i></center></td>
                                                                            </s:if>
                                                                            <s:elseif test="status=='Approved'">
                                                                        <td><center><i class="fa fa-trash-o fa-size" style="opacity: 0.2;"></i></center></td>
                                                                        </s:elseif>
                                                                        <s:else>
                                                                        <td><center><s:a href="#" onclick="return doBudgetRecordDelete('%{id}');"><i class="fa fa-trash-o fa-size"></i></s:a></center></td>
                                                                        </s:else>
                                                                    </s:if>
                                                                </tr>
                                                            </s:iterator>

                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <label class="page_option"> Display <select id="paginationOption" tabindex="7" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>25</option>
                                                                <option>50</option>
                                                            </select>
                                                            Projects per page
                                                        </label>
                                                        <div align="right" class="pull-right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                    </s:form>


                                                    <script type="text/javascript">
                                                        var dashPager = new Pager('projectBudgetTable', 10);
                                                        dashPager.init();
                                                        dashPager.showPageNav('dashPager', 'pageNavPosition');
                                                        dashPager.showPage(1);
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%--close of future_items--%>
                                </div>
                            </div>

                            <%-- model window popup --%>
                            <s:if test="roleValue!='Director'">

                                <div class="side_popup">

                                    <div class="popup_block"> 

                                        <div class="addlink">





                                            <div class="alignField">



                                                <a href="#"  onclick="projectBudgetOverlay('Add');" class="projectBudget_popup_open"  data-toggle="" data-target="" application_id="">   Add Budget  </a>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="slide_popup budget img-swap" onclick="sidepopup()"></i></div>
                                </div>
                            </s:if>
                        </div>
                    </div>        <!-- content end -->

                    <div id="budgetCommentsOverlay_popup">
                        <div id="budgetCommentsBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Comments&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="budgetCommentsOverlay_popup_close" onclick="budgetCommentsOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div>

                                <s:textarea name="commentDetails" id="commentDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                            </div>
                            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                        </div>

                        <%--close of future_items--%>
                    </div>





                    <div id="projectBudget_popup">
                        <div id="projectBudgetBox" class="techReviewPopupStyle">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Budget Details&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="projectBudget_popup_close" onclick="closeProjectBudgetOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div class="" style="padding:5px"> 
                                <div class="row row2"> <span><e></e></span></div>
                                            <%--form start from here --%>
                                            <s:hidden name="costCenterCode" id="costCenterCode" value=""/>


                                <label class="headingLabel">Project Details:</label>
                                <!--<div id="reviewalignBox">-->
                                <div class="row row2">
                                    <div class="col-lg-4">
                                        <label class="popuplabel">Project  </label>
                                        <s:select cssClass="form-control SelectBoxStyles" 
                                                  name="oproject" 
                                                  id="oproject" 
                                                  list="projectsMap" 
                                                  tabindex="1"
                                                  onchange = "return getCostCenterName();"
                                                  />
                                    </div>
                                    <div class="col-lg-2">  
                                        <label class="popuplabel">Cost&nbsp;Center&nbsp;Name:</label>
                                    </div>
                                    <div class="col-lg-2">
                                        <s:textfield cssClass="noBorder" readonly="true" name="costCenterName"  tabindex="2" id="costCenterName" value=""/>
                                    </div>

                                    <s:if test="#session.primaryrolevalue=='Director'">
                                        <div class="col-lg-2">
                                            <label class="popuplabel">Available&nbsp;Budget&nbsp;($):</label>
                                        </div>   
                                        <div class="col-sm-2">
                                            <s:textfield cssClass="noBorder" readonly="true" name="costCenterBudgetAmt"  tabindex="3" id="costCenterBudgetAmt" value=""/>
                                        </div>
                                    </s:if>
                                    <s:else>  
                                        <label class="popuplabel">&nbsp;</label>
                                        <s:hidden name="costCenterBudgetAmt" id="costCenterBudgetAmt" value=""/>
                                    </s:else>
                                </div>

                                <div class="row row2">
                                    <div class ="col-lg-4">
                                        <span class="required">
                                            <label class="popuplabel">Year </label>
                                            <s:select type="text"
                                                      name="oyear"
                                                      cssClass="form-control SelectBoxStyles"
                                                      id="oyear" 
                                                      tabindex="3"
                                                      onkeypress="return validationYearOverlay(event)"
                                                      list="{}" disabled="true"
                                                      ></s:select>
                                            </span>
                                        </div>
                                        <div class="col-lg-4">
                                            <label class="">Quarter</label>
                                        <s:select cssClass="form-control SelectBoxStyles" 
                                                  name="oquarterId" 
                                                  id="oquarterId" disabled="true"
                                                  tabindex="4"
                                                  list="#@java.util.LinkedHashMap@{'Q1':'Q1','Q2':'Q2','Q3':'Q3','Q4':'Q4'}" 
                                                  />
                                    </div>
                                    <div class="required col-lg-4">
                                        <label class="">Estimated&nbsp;Amount </label>
                                        <div class=" input-group ">
                                            <!--<a href="#" data-toggle="tooltip" title="please"--> 
                                            <span class="input-group-addon" style="padding-top: 5px">$</span>
                                            <s:textfield type="text"
                                                         name="oestimateBudget"
                                                         cssClass="form-control"
                                                         id="oestimateBudget"
                                                         placeholder="Estimated Amount"
                                                         onkeypress="return estimateBudgetValidation(event)"
                                                         maxLength="10"
                                                         tabindex="5"
                                                         onfocus="return removeBudgetErrorMsg();"
                                                         onblur="return calculateAmt()"
                                                         />
                                        </div>
                                    </div>
                                </div>
                                <span><tip></tip></span>
                                <br/>
                                <!--<label class="headingLabel">Budget Details:</label>-->
                                <!--<div id="reviewalignBox">-->
                                <div class="row row2">
                                    <div style="display: none" id="consumedAmt">
                                        <div class="required col-lg-4">
                                            <label class="">Consumed&nbsp;Amount </label>
                                            <div class=" input-group ">
                                                <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                <s:textfield type="text"
                                                             name="oconsumedAmt"
                                                             cssClass="form-control "
                                                             id="oconsumedAmt"
                                                             maxLength="10"
                                                             tabindex="5"
                                                             onfocus="return removeBudgetErrorMsg();"
                                                             readonly="true"
                                                             />
                                            </div>
                                        </div>
                                        <!--</div>-->

                                        <div class="col-lg-4">
                                            <label class="">Remaining&nbsp;Amount</label>
                                            <div class=" input-group ">
                                                <span class="input-group-addon" style="padding-top: 5px">$</span>
                                                <s:textfield type="text"
                                                             name="oremainingAmt"
                                                             cssClass="form-control"
                                                             id="oremainingAmt"
                                                             maxLength="10"
                                                             tabindex="5"
                                                             onfocus="return removeBudgetErrorMsg();"
                                                             readonly="true"
                                                             />
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <!--</div>-->
                                <span class="required">
                                    <label class="headingLabel">Comments</label>
                                    <!--<div id="reviewalignBox">-->
                                    <div class="inner-techReviewdiv-elements">
                                        <s:textarea id="ocomments"
                                                    name="ocomments"
                                                    cssClass="reviewareacss"
                                                    type="text"
                                                    placeholder="Any comments"
                                                    tabindex="6"
                                                    value="" onkeydown="checkCharactersComment(this)"   onfocus="return removeBudgetErrorMsg();"/>
                                    </div>
                                    <div class="charNum" id="description_feedback"></div>
                                    <!--</div>-->
                                </span>
                                <s:if test="roleValue=='Director'">
                                    <span class="required">
                                        <label class="headingLabel">Approve/Rejection Comments</label>
                                        <!--<div id="reviewalignBox">-->
                                        <div class="inner-techReviewdiv-elements">
                                            <s:textarea id="approveComments"
                                                        name="approveComments"
                                                        cssClass="reviewareacss"
                                                        type="text"
                                                        placeholder="Any comments"
                                                        value="" onkeydown="checkCharactersApproveComment(this)"   onfocus="return removeBudgetErrorMsg();"/>
                                        </div>
                                        <!--</div>-->
                                    </span>
                                </s:if>
                                <div class="charNum" id="Comments"></div>
                                <div class="inner-techReviewdiv-elements">
                                    <div id="oLaybuttons">
                                        <s:if test="roleValue=='Director'">
                                            <div class="pull-right "><s:submit type="button" cssClass="cssbutton  fa fa-check-circle-o" onclick="saveBudgetDetails('A');" style="" value="Approve"></s:submit></div>
                                            <div class="pull-right col-sm-3"><s:submit type="button" cssClass="cssbutton fa fa-times"  onclick="saveBudgetDetails('R');" style="" value="Reject">
                                                </s:submit></div>
                                            </s:if>
                                            <s:else>
                                            <div class="pull-right "><s:submit type="button" cssClass="cssbutton fa fa-check-square-o opts" onclick="saveBudgetDetails('SB');"  tabindex="8" value="Save&Submit"></s:submit></div>
                                            <div class="pull-right col-sm-3"><s:submit type="button" cssClass="cssbutton fa fa-floppy-o opts" style="" onclick="saveBudgetDetails('S');"  tabindex="7" value="Save"></s:submit></div>
                                        </s:else>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--close of future_items--%>
                    </div>
                </section><!--/form-->
            </div>
        </div>

        <%-- model window popup --%>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script>
            ;
            sortables_init();
        </script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/popupoverlay.js"/>'></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <script type="text/javascript">
            var recordPage = 10;
            function pagerOption() {

                var paginationSize = document.getElementById("paginationOption").value;
                if (isNaN(paginationSize))
                {

                }
                recordPage = paginationSize;
                // alert(recordPage)
                $('#projectBudgetTable').tablePaginate({navigateType: 'navigator'}, recordPage);

            }
            ;
            $('#projectBudgetTable').tablePaginate({navigateType: 'navigator'}, recordPage);

        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>

    </body>
</html>
