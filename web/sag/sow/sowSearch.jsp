<%--
    Document   : AccountDetails
    Created on : May 3, 2015, 2:08:50 PM
    Author     : rama krishna<lankireddy@miraclesoft.com>
--%>

<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: SOW Search Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
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
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/techReviewAjax.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/sowAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>


        <script>
            var pager;
            function sowpagination(){
                        
                var paginationSize = 10; //parseInt(document.getElementById("paginationOption").value);
                        
                pager = new Pager('sowResults', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                //document.getElementById("paginationOption").value=10;
                pager.showPage(1);
                document.getElementById("loadingSowSearch").style.display="none";
            };
           
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                //alert("hello")
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("sowResults");
                sortableTableRows = document.getElementById("sowResults").rows;
                sortableTableName = "sowResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
        </script>

    </head>
    <body oncontextmenu="return false" onload="sowpagination();">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/>
                    </div>
                </div>

            </header>
            <div id="main">
                <%-- ------------MIDDLE -----------------------------------------%>
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
                                                    <font color="#ffffff">Contracts</font>
                                                    <i id="updownArrow" onclick="toggleContent('SOWSearchForm')" class="fa fa-minus"></i> 
                                                </h4>
                                            </div>
                                        </div>

                                        <s:form action="/sag/sow/SOWSearchAction" theme="simple" id="SOWSearchForm">
                                            <div class="inner-reqdiv-elements">
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <label class="labelStylereq" style="color:#56a5ec">Consultant Name</label>
                                                        <s:textfield cssClass="form-control" id="consultantName" placeholder="Consultant Name" name="consultantName" maxLength="60"/>
                                                    </div>

                                                    <s:if test="#session.typeOfUsr == 'AC'">
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Vendor Name</label>
                                                            <s:textfield cssClass="form-control" id="vendorName" placeholder="Vendor Name" name="vendorName" maxLength="60"/>
                                                        </div>
                                                    </s:if>
                                                    <s:if test="#session.typeOfUsr == 'VC'">
                                                        <div class="col-sm-3">
                                                            <label class="labelStylereq" style="color:#56a5ec">Customer Name</label>
                                                            <s:textfield cssClass="form-control" id="customerName" placeholder="Customer Name" name="customerName" maxLength="60"/>
                                                        </div>
                                                    </s:if>

                                                    <div class="col-sm-3">
                                                        <label class="labelStylereq" style="color:#56a5ec">Requirement Name</label>
                                                        <s:textfield cssClass="form-control" id="requirementName" placeholder="Requirement Name" name="requirementName" maxLength="50"/>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label class="labelStylereq" style="color:#56a5ec">Status</label>
                                                        <%--<s:select id="status" cssClass="SelectBoxStyles form-control" name="status" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Selected':'Selected','Approved':'Approved','Rejected':'Rejected','Generated':'SOW generated','Submitted':'SOW Submitted','Released':'Released'}"/>--%>
                                                        <s:select id="status" cssClass="SelectBoxStyles form-control" name="status" headerKey="-1" headerValue="All" list="#@java.util.LinkedHashMap@{'Created':'Created','Approved':'Approved','Rejected':'Rejected','Submitted':'Submitted','Paid':'Paid','Denied':'Denied'}"/>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label class="labelStylereq" style="color:#56a5ec">Service&nbsp;Type</label>

                                                        <s:select name="SOWSelectValue" id="SOWSelectValue" cssClass="SelectBoxStyles form-control" list="#@java.util.LinkedHashMap@{'-1':'All','S':'SOW Proceed','F':'Finder Fee'}"/>

                                                    </div>
                                                    <div class="col-sm-7"></div>
                                                    <div class="col-sm-2  pull-right">
                                                        <label class="labelStylereq" style="color:#56a5ec"></label>
                                                        <s:submit id="sowSearch" cssClass="add_searchButton form-control" type="button" value="" cssStyle="margin:5px 0px 0px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                        </div>
                                                    </div>
                                                </div>
                                        </s:form>
                                        <br>
                                        <div id="loadingSowSearch" class="loadingImg">
                                            <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                        </div>
                                        <s:form>
                                            <span id="updatedmessage"><font style="color: green"><s:property value="resultMessage"></s:property></font></span>
                                                <div class="task_content" id="task_div" align="center" style="display: none" >

                                                    <div>
                                                        <div>
                                                            <table id="sowResults" class="responsive CSSTable_task sortable" border="5" cell-spacing="2">
                                                                <tbody>

                                                                    <tr>
                                                                        <th >Consultant&nbsp;Name</th>
                                                                    <s:if test="#session.typeOfUsr == 'VC'">
                                                                        <th>Customer&nbsp;Name</th>
                                                                    </s:if>
                                                                    <s:if test="#session.typeOfUsr == 'AC'">
                                                                        <th>Vendor&nbsp;Name</th>
                                                                    </s:if>
                                                                    <th >Service&nbsp;Type</th>
                                                                    <th class="unsortable">Service&nbsp;version</th>
                                                                    <th class="unsortable">Job&nbsp;Name</th>
                                                                    <th class="unsortable">Pay&nbsp;Rate</th>
                                                                    <th class="unsortable">Created&nbsp;Date</th>
                                                                    <th class="unsortable">Status</th>
                                                                    <s:if test="#session.typeOfUsr=='AC'">
                                                                    <th class="unsortable">Release&nbsp;PO</th>
                                                                    </s:if>
                                                                    <th class="unsortable">Migrate</th>  

                                                                </tr>
                                                                <s:if test="sowVTO.size == 0">
                                                                    <tr>
                                                                        <td colspan="10"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:iterator  value="sowVTO">
                                                                    <tr>
                                                                        <s:if test="#session.typeOfUsr == 'AC' && reviewStatus == 'Created' && serviceTypeForView=='SOW'">
                                                                            <%-- we are not displaying SOW Created status results to customers --%>
                                                                        </s:if>
                                                                        <s:elseif test="#session.typeOfUsr == 'VC' && reviewStatus == 'Created' && serviceTypeForView=='Finder Fee'">
                                                                            <%-- we are not displaying Finder Fee Created status results to vendors --%>
                                                                        </s:elseif>
                                                                        <s:else>
                                                                           
                                                                                <td> <s:url var="editUrl" action="SOWEditAction.action" >
                                                                                    <s:param name="serviceId"><s:property value="serviceId"></s:property></s:param>
                                                                                    <%-- <s:param name="consultantId"><s:property value="consultantId"></s:property></s:param>
                                                                                     <s:param name="requirementId"><s:property value="requirementId"></s:property></s:param>
                                                                                     <s:param name="customerId"><s:property value="customerId"></s:property></s:param>
                                                                                     <s:param name="vendorId"><s:property value="vendorId"></s:property></s:param>
                                                                                     <s:param name="rateSalary"><s:property value="rateSalary"></s:property></s:param> --%>
                                                                                    <s:param name="consultantName"><s:property value="consultantName"></s:property></s:param>
                                                                                    <s:param name="customerName"><s:property value="customerName"></s:property></s:param>
                                                                                    <s:param name="vendorName"><s:property value="vendorName"></s:property></s:param>
                                                                                    <s:param name="requirementName"><s:property value="requirementName"></s:property></s:param>
                                                                                    <s:param name="status"><s:property value="reviewStatus"></s:property></s:param>
                                                                                    <s:param name="serviceType"><s:property value="serviceType"></s:property></s:param>
                                                                                </s:url>
                                                                                <s:a href='%{#editUrl}'><s:property value="consultantName"></s:property></s:a>
                                                                                    </td>
                                                                            <%--<td><s:property value="consultantName"></s:property></td>--%>
                                                                            <s:if test="#session.typeOfUsr == 'VC'">
                                                                                <td><s:property value="CustomerName"></s:property></td>
                                                                            </s:if>
                                                                            <s:if test="#session.typeOfUsr == 'AC'">
                                                                                <td><s:property value="vendorName"></s:property></td>
                                                                            </s:if>
                                                                            <td><s:property value="serviceTypeForView"></s:property></td>
                                                                            <td><s:property value="serviceVersion"></s:property></td>
                                                                            <td><s:property value="requirementName"></s:property></td>
                                                                            <s:if test="serviceTypeForView!='Finder Fee'">
                                                                                <td>$<s:property value="rateSalary"></s:property><font style="color: peru">/<s:property value="submittedPayRateType"></s:property></font></td>
                                                                            </s:if>
                                                                                <s:else>
                                                                                <td>$<s:property value="rateSalary"></s:property><font style="color: peru"> &nbsp;(thousands)</f
                                                                                    ont><s:property value="submittedPayRateType"></s:property></td>
                                                                                </s:else>
                                                                            <td><s:property value="reqCreatedDate"></s:property></td>

                                                                                <td><s:property value="reviewStatus"></s:property></td>
                                                                            <s:if test="#session.typeOfUsr == 'AC' ">
                                                                                <s:if test="reviewStatus=='Approved' && serviceTypeForView=='SOW'">
                                                                                    <td><center><s:a href="#" cssClass="" onclick="releasePo('%{serviceId}','%{requirementId}');"><i class="fa fa-arrow-circle-o-right fa-size"></i></s:a></center></td>
                                                                                </s:if> 
                                                                                <s:else>
                                                                            <td><center><s:a href="#" ><i class="fa fa-arrow-circle-o-right fa-size" style="opacity: 0.3" ></i></s:a></center></td>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:if test="migrateStatus=='No'">
                                                                        <s:if test="(reviewStatus=='Approved'&&serviceTypeForView=='SOW'&&#session.typeOfUsr == 'VC')||(reviewStatus=='Paid'&&serviceTypeForView=='Finder Fee'&&#session.typeOfUsr == 'AC')">
                                                                            <td><center><s:a href="#" cssClass="Migration_popup_open" onclick="migration_overlay('%{consultantEmailId}','%{consultantId}','%{requirementId}');"><img src="<s:url value="/includes/images/userMigration.png"/>" height="20" width="20"></s:a></center></td>
                                                                            </s:if>
                                                                            <s:else>
                                                                            <td><center><img src="<s:url value="/includes/images/userMigration.png"/>" height="20" width="20" style="opacity: 0.3"></center></td>
                                                                            </s:else>
                                                                        </s:if>
                                                                        <s:else>
                                                                        <td><center><img src="<s:url value="/includes/images/AfterUserMigration.gif"/>" height="20" width="20" ></center></td>
                                                                        </s:else>
                                                                    </s:else>
                                                                </tr>
                                                            </s:iterator>
                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <s:if test="sowVTO.size > 0">
                                                            <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                Contracts per page
                                                            </label>
                                                        </s:if>
                                                        <div align="right" id="pageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>
                                                    </div>
                                                    <script type="text/javascript">
                                                        var pager = new Pager('sowResults', 10); 
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
                    <div id="Migration_popup">
                        <div id="recruiterBox" class="marginTasks">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Migration Details&nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="Migration_popup_close" onclick="migration_overlayClose()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div>
                                <form>
                                    <span><mig></mig></span>
                                    <s:hidden name="consultid" id="consultid" value=""/>
                                    <s:hidden name="req_Id" id="req_Id" value="%{requirementId}"/>
                                    <s:hidden name="acc_Id" id="acc_Id" value=""/>
                                    <s:hidden name="acc_type" id="acc_type" value=""/>
                                    <s:hidden name="vendor" id="vendor" value="%{vendorId}"/>
                                    <s:hidden name="loginId" id="loginId" value="%{#session['loginId']}"/>

                                    <center>   
                                        <table>

                                            <div class="inner-techReviewdiv-elements">
                                                <s:textfield cssClass="form-control " 
                                                             id="migrationEmailId" 
                                                             type="text" value="" 
                                                             name="migrationEmailId"
                                                             placeholder="" label="EmailId"
                                                             onfocus="removeErrorMessages()" 
                                                             maxLength="60"/>
                                            </div>
                                        </table>
                                    </center>
                                    <br/>
                                    <s:submit cssClass="cssbutton migrationButton btn_pull"
                                              id="migrate" 
                                              onclick="return userMigration();"
                                              value="Migrate" />
                                </form>             
                            </div>
                            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                        </div>
                    </div>
                </section>
                <%-- ------------MIDDLE -----------------------------------------%>
            </div>
        </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->


<script type="text/javascript">
            $("#updatedmessage").show().delay(2000).fadeOut();
        </script>
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
                // alert(recordPage)
                $('#sowResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#sowResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script>
            sortables_init();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>