<%-- 
    Document   : Employee categorization
    Created on : July 17, 2015, 7:50:23 PM
    Author     : Manikanta
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Employee categorization Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/GeneralAjax.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <script>
            var pager;
            function onLoad() {

                var paginationSize = 5;

                pager = new Pager('empCategorizationResults', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');

                pager.showPage(1);
            }
            ;
            function onLoadLoader() {


                document.getElementById("loadingEmpSearch").style.display = "none";
            }
            ;
        </script>
        <script type="text/javascript">
            function sortables_init() {

                if (!document.getElementsByTagName)
                    return;
                tbls = document.getElementById("empCategorizationResults");
                sortableTableRows = document.getElementById("empCategorizationResults").rows;
                sortableTableName = "empCategorizationResults";
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
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="pagerOption();
            onLoadLoader()">
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
                            <!-- content start -->
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="col-lg-14 ">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">

                                                        <font color="#ffffff">Employee Categorization</font>
                                                        <i id="updownArrow" onclick="toggleContent('empCategorizationForm')" class="fa fa-minus"></i> 
                                                    </h4>
                                                </div>

                                            </div>
                                            <!-- content start -->
                                            <div class="col-sm-12">
                                                <div id="empCategorizationForm">
                                                    <div class="col-sm-4">
                                                        <s:hidden name="teamMemberId" id="teamMemberId" value="0"/>
                                                        <label class="labelStylereq" style="color:#56a5ec;">Employee Name </label>
                                                        <s:textfield id="empName" tabindex="1"
                                                                     cssClass="form-control"
                                                                     type="text"
                                                                     name="empName"
                                                                     placeholder="Employee Name" maxLength="60"/>

                                                        <span id="validationMessage" />
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Category Group </label>
                                                        <s:select  id="categoryType"
                                                                   name="categoryType" tabindex="2"
                                                                   cssClass="SelectBoxStyles form-control"
                                                                   headerKey="-1" 
                                                                   headerValue="Select Category"
                                                                   theme="simple"
                                                                   list="categoryTypes"
                                                                   />
                                                    </div>

                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Status </label>
                                                        <s:select  id="empStatus"
                                                                   name="empStatus" tabindex="3"
                                                                   cssClass="SelectBoxStyles form-control"
                                                                   headerKey="-1"  
                                                                   theme="simple"
                                                                   list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','All':'All'}"
                                                                   />
                                                    </div>
                                                </div>
                                                    <div class="row">
                                                <div class="col-sm-4 col-md-6 col-lg-4 pull-right">
                                                    
                                                        <div class="col-sm-6 pull-right">
                                                            <label class="" style="color:#56a5ec;"></label>
                                                            <a id="addUserGroupButton" href='getUserGroping.action?addOrUpdate=add'><button type="button" tabindex="4" class="add_searchButton form-control" value="" style="margin: 5px 0px;"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>
                                                        </div>
                                                        <div class="col-sm-6 pull-right">
                                                            <label class="" style="color:#56a5ec;"></label>
                                                            <s:submit type="button" cssStyle="margin:5px 0px;"
                                                                      id="searchUserGroupButton"
                                                                      cssClass="add_searchButton form-control" tabindex="5"
                                                                      value=""  onclick="return getEmpCategories();"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-sm-12">
                                                    <div id="loadingEmpSearch" class="loadingImg">
                                                        <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                                </div>
                                                <s:form>
                                                    <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                        <table id="empCategorizationResults" class="responsive CSSTable_task sortable" border="5" cell-spacing="2">
                                                            <tbody>
                                                                <tr>
                                                                    <th>Name</th>
                                                                    <th>Category Group</th>
                                                                    <th>Role</th>
                                                                    <th class="unsortable">Is Primary</th> 
                                                                    <th class="unsortable">Status</th>
                                                                   <%-- <th class="unsortable">Created By</th> --%>
                                                                    <th class="unsortable">Delete</th>
                                                                </tr>
                                                                <s:if test="userVTO.size == 0">
                                                                    <tr>
                                                                        <td colspan="7"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:iterator value="userVTO">
                                                                    <tr>
                                                                        <s:hidden id="groupingId" name="groupingId" value="groupingId"/>
                                                                        <s:hidden id="userId" name="userId" value="empId"/>
                                                                        <s:hidden id="subCategory" name="subCategory" value="subCategory"/>
                                                                        <s:hidden id="catogoryTypeId" name="catogoryTypeId" value="catogoryTypeId"/>
                                                                        <s:url id="myUrl" action="getUserGroping.action?addOrUpdate=update">
                                                                            <s:param name="groupingId"><s:property value="groupingId"/></s:param>
                                                                            <s:param name="userId" value="%{empId}" ></s:param> 
                                                                        </s:url>
                                                                        <td><s:a href='%{#myUrl}'><s:property value="empName"></s:property></s:a></td>
                                                                        <td><s:a href="#" cssClass="categorizationOverlay_popup_open" onclick="categorizationOverlay();getEmpCategoryNames('%{subCategory}');"><s:property value="catogoryGroup"></s:property></s:a></td>
                                                                        <td><s:property value="role"></s:property></td>
                                                                        <td><s:property value="isPrimary"></s:property></td>
                                                                        <td><s:property value="status"></s:property></td>
                                                                        <%-- <td><s:property value="createdBy"></s:property></td> --%>
                                                                        <s:if test="status=='Active'">
                                                                            <td><center><s:a href="#" onclick="empCategoryTermination('%{groupingId}');"><i class="fa fa-trash-o fa-size"></i></s:a></center></td>
                                                                        </s:if>
                                                                        <s:else>
                                                                    <td><center><i class="fa fa-trash-o fa-size" style="opacity: 0.3"></i></center></td>
                                                                </s:else>
                                                                    </tr>
                                                            </s:iterator>
                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                <option>5</option>
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>50</option>
                                                            </select>
                                                            Employees per page
                                                        </label>
                                                        <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                    </div>
                                                </s:form>


                                            </div>

                                        </div>

                                        <%--close of future_items--%>

                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                    <div id="categorizationOverlay_popup">
                        <div id="categorizationBox">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor" >&nbsp;&nbsp;Assigned Groups &nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="categorizationOverlay_popup_close" onclick="categorizationOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div style="margin: 10px;margin-bottom: -10px"><center>
                                    <table id="empCategorizationTableOverlay"  class="CSSTable_task  " border="2" cell-spacing="1" style="overflow-x:auto;overflow-y:hidden;">
                                        <tbody>
                                            <tr>
                                            </tr>
                                        </tbody>
                                    </table>
                                </center>

                            </div>
                            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
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

        </footer>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <!--/Footer-->
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
                                        var recordPage = 10;
                                        function pagerOption() {

                                            var paginationSize = document.getElementById("paginationOption").value;
                                            if (isNaN(paginationSize))
                                            {

                                            }
                                            recordPage = paginationSize;
                                            // alert(recordPage)
                                            $('#empCategorizationResults').tablePaginate({navigateType: 'navigator'}, recordPage);

                                        }
                                        ;
                                        $('#empCategorizationResults').tablePaginate({navigateType: 'navigator'}, recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <script>
                                        sortables_init();
        </script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>

    </body>
</html>


