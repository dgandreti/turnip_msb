<%--
    Document   : AccountDetails
    Created on : Oct 01, 2015, 2:08:50 PM
    Author     : divya gandreti<dgandreti@miraclesoft.com>
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Cost Center Search Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
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
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script>
            
            $(document).ready(function(){
                var myselect=document.getElementById("year"), year = new Date().getFullYear();
                var gen = function(max){
                    do
                    {myselect.add(new Option(year++,year-1),null);
                        max--;}
                    while(max>0);}
                (10);
                document.getElementById("loadingCostCenterSearch").style.display="none";
            });
            
        
            var pager;
            function costCenterPagination(){
                        
                var paginationSize = 10;
                        
                pager = new Pager('costCenterResults', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                pager.showPage(1);
            }; 
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("costCenterResults");
                sortableTableRows = document.getElementById("costCenterResults").rows;
                sortableTableName = "costCenterResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
        </script>

    </head>
    <body oncontextmenu="return false" onload="costCenterPagination();doOnLoadCostCenter();">
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
                                    <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                        <div class="backgroundcolor" >
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <font color="#ffffff">Cost Center</font>
                                                    <i id="updownArrow" onclick="toggleContent('costCenterSearch')" class="fa fa-minus"></i> 
                                                </h4>
                                            </div>
                                        </div>

                                        <s:form action="/costCenter/costCenterSearch" theme="simple" id="costCenterSearch">
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec">Cost Center Name</label>
                                                        <s:textfield cssClass="form-control" id="ccName" placeholder="Cost Center Name"  name="ccName" maxLength="30"/>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec">Cost Center Code</label>
                                                        <s:textfield cssClass="form-control" id="ccCode" placeholder="Cost Center Code" name="ccCode" maxLength="30"/>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                        <s:select id="status" cssClass="SelectBoxStyles form-control" name="status" list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"/>
                                                    </div>

                                                </div>
                                                <div class="row">  
                                                    <div class="col-sm-4 pull-right">
                                                        <div class="col-sm-6 ">
                                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                                            <s:a href='#' id="costCenterAddLink" onclick="costCenterAdd_overlay()" cssClass="costCenter_popup_open add_searchButton form-control"  cssStyle="margin:5px 0px 0px"><i class="fa fa-plus-square"></i>&nbsp;Add</button></s:a>  
                                                        </div>
                                                        <div class="col-sm-6 ">
                                                            <label class="labelStylereq" style="color:#56a5ec"></label>
                                                            <s:submit id="costCenterSearchButton" cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px"><i class="fa fa-search"></i>&nbsp;Search</s:submit> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </s:form>
                                        <br>
                                        <div id="loadingCostCenterSearch" class="loadingImg">
                                            <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                        </div>
                                        <s:form>
                                            <span><font style="color: green"><s:property value=""></s:property></font></span>
                                            <div class="task_content" id="task_div" align="center" style="display: none" >

                                                <div>
                                                    <div>
                                                        <table id="costCenterResults" class="responsive CSSTable_task sortable" border="5"cell-spacing="2">
                                                            <tbody>

                                                                <tr>
                                                                    <th>CcCode</th>
                                                                    <th>CcName</th>
                                                                    <th class="unsortable">Status</th>
                                                                    <th>Projects</th>
                                                                    <th class="unsortable">Budget</th>
                                                                    <th class="unsortable">Info</th>
                                                                </tr>
                                                                <s:if test="costCenterSearchList.size == 0">
                                                                    <tr>
                                                                        <td colspan="9"><center><font style="color: red;font-size: 15px;">No Records to display</font></center></td>
                                                                </tr>
                                                            </s:if>

                                                            <s:iterator value="costCenterSearchList">
                                                                <tr>
                                                                    <td>
                                                                        <s:a href="#" cssClass="costCenter_popup_open" onclick="costCenter_overlay('%{ccId}','%{ccName}',%{projCount});"><s:property value="ccCode"></s:property></s:a></td>
                                                                    <td><s:property value="ccName"></s:property></td>
                                                                    <td><s:property value="ccStatus"></s:property></td>
                                                                    <td>
                                                                        <s:if test="%{projCount>0}">
                                                                            <s:a href="#" cssClass="costCenterProjects_popup_open" onclick="costCenter_projects('%{ccCode}');">
                                                                                <s:property value="projCount"></s:property>
                                                                            </s:a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:property value="projCount"></s:property>
                                                                        </s:else>
                                                                    </td>
                                                                    <td>         
                                                                        <s:hidden name="role" id="role" value="%{#session['primaryrolevalue']}"/>
                                                                        <s:if test="%{id==0}">
                                                                            <s:if test="#session.primaryrolevalue!='Director'">
                                                                                <s:a href="#" cssClass="costCenterBudget_popup_open" onclick="costCenterBudget_overlay('%{ccCode}','%{id}','%{ccName}');">
                                                                                    <i class="fa fa-plus-square"></i>
                                                                                </s:a>   
                                                                            </s:if>
                                                                            <s:else>
                                                                                <i class="fa fa-plus-square"></i>
                                                                            </s:else>    
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:a href="#" cssClass="costCenterBudget_popup_open" onclick="costCenterBudget_overlay('%{ccCode}','%{id}','%{ccName}');">
                                                                                $<s:property value="budgetAmt"></s:property>
                                                                            </s:a>    
                                                                        </s:else>
                                                                    </td>
                                                                    <td>
                                                                        <s:url var="info" action="costCenterInfo.action">
                                                                            <s:param name="ccCode"><s:property value="ccCode"></s:property></s:param>
                                                                            <s:param name="ccName"><s:property value="ccName"></s:property></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{#info}" ><i class="fa fa-info-circle"></s:a></i>
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <s:if test="costCenterSearchList.size > 0">
                                                            <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                cost centers per page
                                                            </label>
                                                        </s:if>
                                                        <div align="right" id="pageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>
                                                    </div>
                                                    <script type="text/javascript">
                                                        var pager = new Pager('costCenterResults', 10); 
                                                        pager.init(); 
                                                        pager.showPageNav('pager', 'pageNavPosition'); 
                                                        pager.showPage(1);
                                                    </script>
                                                </div>
                                            </div>
                                        </s:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="costCenter_popup">
                        <div id="recruiterBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Cost Center&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="costCenterOverlayCloseLink" class="costCenter_popup_close" onclick="costCenter_overlay();removeCostCenterResults();" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div>
                                <s:form>
                                    <span><costCenter></costCenter></span>
                                    <center>
                                        <table>
                                            <s:hidden name="prjCount" id="prjCount" value=""/>
                                            <s:hidden name="ccFlag" id="ccFlag" value=""/>
                                            <s:hidden name="ccId" id="ccId" value=""/>
                                        </table>
                                        <div class="col-lg-12">
                                            <div class="col-lg-12 required">
                                                <label>Cost Center Name</label>
                                                <s:textfield cssClass="form-control " 
                                                             id="name" 
                                                             type="text" value="" 
                                                             name="name"
                                                             placeholder="Cost Center Name" 
                                                             maxLength="30"/>

                                            </div>
                                            <div class="col-lg-12" id="ccstatus" style="display: none">
                                                <label>Status</label>
                                                <s:select id="status" 
                                                          cssClass="SelectBoxStyles form-control" 
                                                          name="status" 
                                                          list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"/>
                                            </div>

                                        </div>
                                    </center>
                                    <br/>
                                    <div class="col-lg-12">
                                        <div id="ccadd" style="display: none">
                                            <s:submit type="button" id="add"  cssClass="cssbutton migrationButton " onclick="return addCostCenter();"  ><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                        </div><div id="ccedit" style="display: none">
                                            <s:submit type="button"  cssClass="cssbutton migrationButton" id="update" onclick="return editCostCenter();" ><i class="fa fa-refresh"></i>&nbsp;Update</s:submit>  
                                        </div>
                                    </div>
                                </s:form>             
                            </div>
                            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                        </div>
                    </div>

                    <%--overlay for budget starts--%> 
                    <div id="costCenterBudget_popup">
                        <div id="attachmentBox">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Budget&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" id="costCenterBudgetOverlayCloseLink" class="costCenterBudget_popup_close" onclick="budget_overlay();removeCostCenterResults();" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div class="techReviewPopupStyle">

                                <s:form>
                                    <span><costCenter></costCenter></span>
                                    <span id="info"></span>
                                    <center>
                                        <table>
                                            <s:hidden name="code" id="code" value=""/>
                                            <s:hidden name="flag" id="flag" value=""/>
                                            <s:hidden name="id" id="id" value=""/>
                                            <s:hidden name="budgetStatus"  id="budgetStatus" value=""/>
                                        </table>
                                        <div class="row2">
                                            <label class="headingLabel">Cost Center Name :</label>
                                            <s:textfield cssClass="noBorder" readonly="true" name="costCenterName"  id="costCenterName" value=""/>
                                        </div>
                                        <div class="row2 row">
                                            <s:if test="#session.primaryrolevalue!='Director'">
                                                <div class="col-lg-6">
                                                    <label>Year</label>
                                                    <s:select 
                                                        name="year"
                                                        tabindex="1"
                                                        cssClass="SelectBoxStyles form-control"
                                                        id="year" 
                                                        list="{}"
                                                        ></s:select>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label>Quarter</label>  
                                                    <s:select id="quarter" placeholder=""
                                                              cssClass="SelectBoxStyles form-control" 
                                                              tabindex="2"
                                                              name="quarter"
                                                              list="#@java.util.LinkedHashMap@{'Q1':'Q1','Q2':'Q2','Q3':'Q3','Q4':'Q4'}"/>
                                                </div>
                                            </s:if>
                                            <s:else>
                                                <div class="col-lg-6">
                                                    <label>Year</label>
                                                    <s:select name="year"
                                                              cssClass="SelectBoxStyles form-control"
                                                              tabindex="3"
                                                              id="year" 
                                                              list="{}" disabled="true"
                                                              ></s:select>
                                                </div>
                                                <div class="col-lg-6">
                                                    <label>Quarter</label> 
                                                    <s:select id="quarter" placeholder=""
                                                              cssClass="SelectBoxStyles form-control" 
                                                              tabindex="4"
                                                              name="quarter" disabled="true"
                                                              list="#@java.util.LinkedHashMap@{'Q1':'Q1','Q2':'Q2','Q3':'Q3','Q4':'Q4'}"/>
                                                </div>
                                            </s:else>
                                        </div>
                                        <div class="row2 row">
                                            <div class="col-lg-6">
                                                <label>Start&nbsp;Date</label>
                                                <div class="calImage">
                                                    <s:if test="#session.primaryrolevalue!='Director'">
                                                        <s:textfield cssClass="form-control  " 
                                                                     tabindex="5"
                                                                     id="startDate" 
                                                                     type="text" value="" 
                                                                     name="startDate"
                                                                     placeholder="Start Date"  
                                                                     onkeypress="return enterDateRepository(); ">
                                                            <i class="fa fa-calendar"></i></s:textfield>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield cssClass="form-control " 
                                                                     tabindex="6"
                                                                     id="startDate" 
                                                                     type="text" value="" 
                                                                     name="startDate"
                                                                     placeholder="Start Date"  
                                                                     onkeypress="return enterDateRepository();" readonly="true">
                                                            <i class="fa fa-calendar"></i></s:textfield>
                                                    </s:else>
                                                </div>
                                            </div>
                                            <div class="col-lg-6">
                                                <label>End&nbsp;Date</label>
                                                <div class="calImage">
                                                    <s:if test="#session.primaryrolevalue!='Director'">
                                                        <s:textfield cssClass="form-control " 
                                                                     tabindex="7"
                                                                     id="endDate" 
                                                                     type="text" value="" 
                                                                     name="endDate"
                                                                     placeholder="End Date"  
                                                                     onkeypress="return enterDateRepository();">
                                                            <i class="fa fa-calendar"></i></s:textfield>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield cssClass="form-control " 
                                                                     tabindex="8"
                                                                     id="endDate" 
                                                                     type="text" value="" 
                                                                     name="endDate"
                                                                     placeholder="End Date"  
                                                                     onkeypress="return enterDateRepository();" readonly="true">
                                                            <i class="fa fa-calendar"></i></s:textfield>
                                                    </s:else>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row2 row">
                                            <div class="col-lg-6">
                                                <label class="label" for="budgetAmt">Budget Amount</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                    <s:textfield cssClass="form-control " 
                                                                 tabindex="9"
                                                                 id="budgetAmt" 
                                                                 type="text" value="" 
                                                                 name="budgetAmt"
                                                                 placeholder="Budget Amount"
                                                                 onkeypress="return validationCostCenterBudget(event,this.id)"
                                                                 /></div>
                                            </div>  
                                            <div class="col-lg-6" id="spentAmtId" style="display:none">
                                                <label class="label" for="spentAmt">Consumed Amount</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                    <s:textfield cssClass="form-control " 
                                                                 tabindex="10"
                                                                 id="spentAmt" 
                                                                 type="text" value="" 
                                                                 name="spentAmt"
                                                                 placeholder="" 
                                                                 readonly="true"/>
                                                </div>

                                            </div>
                                            <div class="col-lg-6 " id="buttonId" style="display:none">
                                                <div class="pull-right">
                                                    <s:submit type="button" cssClass="cssbutton migrationButton fa fa-check-square-o opts"
                                                              cssStyle="margin-top:15px;" tabindex="12"
                                                              id="ccbudget" 
                                                              onclick="return addCostCenterBudget('SB');"
                                                              value="Save&Submit" />
                                                </div><div class="pull-right">
                                                    <s:submit type="button" cssClass="cssbutton migrationButton fa fa-floppy-o"
                                                              cssStyle="margin-top:15px;" tabindex="11"
                                                              id="ccbudget" 
                                                              onclick="return addCostCenterBudget('S');"
                                                              value="Save" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row row2" id="balanceAmtId" style="display:none">
                                            <div class="col-lg-6">
                                                <label class="label" for="balanceAmt">Balance Amount</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon " style="padding-top: 5px" >$</span>
                                                    <s:textfield cssClass="form-control " 
                                                                 id="balanceAmt" 
                                                                 type="text" value="" 
                                                                 name="balanceAmt"
                                                                 placeholder="" 
                                                                 readonly="true"/>
                                                </div>
                                            </div>

                                            <div id="enter" style="display:none">
                                                <s:if test="#session.primaryrolevalue!='Director'">
                                                    <div class="pull-right">
                                                        <s:submit type="button" cssClass="cssbutton fa fa-check-square-o opts"
                                                                  cssStyle="margin :20px 14px;"
                                                                  id="ccbudget" 
                                                                  onclick="return addCostCenterBudget('SB');"
                                                                  value="Save&Submit" />
                                                    </div> 
                                                    <div class="pull-right">

                                                        <s:submit type="button" cssClass="cssbutton fa fa-floppy-o opts"
                                                                  cssStyle="margin :20px 18px;"
                                                                  id="ccbudget" 
                                                                  onclick="return addCostCenterBudget('S');"
                                                                  value="Save" />
                                                    </div>  

                                                </div>  
                                            </s:if>
                                        </div>



                                        <div class="col-lg-12">
                                            <div class="" id="approveComm" style="display:none">
                                                <s:if test="#session.primaryrolevalue=='Director'">

                                                    <s:textarea id="approveComments"
                                                                name="approveComments"
                                                                cssClass="reviewareacss"
                                                                type="text"
                                                                placeholder="Any comments"
                                                                label="Comments on Approve"
                                                                value="" onkeydown="checkCharactersProjects(this)"   
                                                                onfocus="return removesMsg();"/>
                                                     <div class="charNum" id="charNumProjects"></div>
                                                </s:if>
                                            </div>
                                        </div>
                                       
                                        <div class="col-lg-12">
                                            <div  id="rejComm" style="display: none">
                                                <s:if test="#session.primaryrolevalue=='Director'">
                                                    <s:textarea id="rejectionComments"
                                                                name="rejectionComments"
                                                                cssClass="reviewareacss"
                                                                type="text"
                                                                placeholder="Any comments"
                                                                label="Comments on Rejection"
                                                                value="" onkeydown="checkCharactersSkill(this)"   
                                                                onfocus="return removesMsg();"/>  
                                                     <div class="charNum" id="charNumSkill"></div>
                                                </s:if>
                                            </div>
                                        </div>
                                       


                                        <div class="col-lg-6  col-md-7 col-sm-9 pull-right">
                                            <div id="submit" style="display: none">
                                                <s:if test="#session.primaryrolevalue=='Director'">
                                                    <div class="pull-right col-sm-7 col-md-6">
                                                        <s:submit cssClass="cssbutton migrationButton fa fa-check" type="button"
                                                                  cssStyle="margin-top:15px;"
                                                                  id="ccbudget" 
                                                                  onclick="return addCostCenterBudget('A');"
                                                                  value="Approve" />
                                                    </div>  <div class="pull-right">
                                                        <s:submit cssClass="cssbutton migrationButton fa fa-times" type="button"
                                                                  cssStyle="margin-top:15px;"
                                                                  id="ccbudget" 
                                                                  onclick="return addCostCenterBudget('R');"
                                                                  value="Reject" />
                                                    </div> 
                                                </s:if>
                                            </div>  
                                        </div>

                                    </center>

                                </s:form>   
                            </div>
                        </div>

                    </div>
            </div>
            <%--overlay for budget ends--%> 
            <%---projects names overlay starts----%>
            <div>
                <div id="costCenterProjects_popup" class="overlay">
                    <div id="resourceOverlay" >

                        <div class="overlayOrButton_color">
                            <table>
                                <tr><td style=""><h4>
                                            <font color="#ffffff">&nbsp;Projects&nbsp; </font>

                                        </h4></td>
                                <span class=" pull-right"><h5><a href="" id="costCenterProjectsOverlayCloseLink" class="costCenterProjects_popup_close" onclick="costCenterProjectsOverlayClose();pagerOption();"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
                            </table>
                        </div>
                        <div style="margin: 10px">
                            <table id="resourceTable" class="CSSTable_task  responsive" border="2"cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;" >
                                <tr>
                                    <th>&nbsp;S.No.&nbsp;</th>
                                    <th>&nbsp;Project&nbsp;Name&nbsp;</th>
                                </tr>
                            </table > 
                            <div id="edu_pageNavPosition" align="right" style="margin-right:0vw"></div>
                            <div   style="width:auto;height:auto" >
                                <div  id="editSpan" class="badge pull-right" style="display:none"></div>                                                       
                            </div>  
                        </div>
                    </div>
                </div>
                <%--projects names overlay ends----%>
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
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                $('#costCenterResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#costCenterResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script>
            sortables_init();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>