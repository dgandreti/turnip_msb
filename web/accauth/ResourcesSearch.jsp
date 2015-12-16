<%-- 
    Document   : Action Resources
    Created on : July 20, 2015, 7:50:23 PM
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
        <title>ServicesBay :: Action Resources Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <%-- <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
             <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">--%>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>

        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/GeneralAjax.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <script>
            var pager;
            //function onLoad(){
            //alert("onload")
            $(document).ready(function(){
                var paginationSize = 10; //parseInt(document.getElementById("paginationOption").value);
                // alert(paginationSize);
                pager = new Pager('empCategorizationResults', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                // document.getElementById("paginationOption").value=10;
                pager.showPage(1);
            });
           
        </script>
        <script type="text/javascript">
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("empCategorizationResults");
                sortableTableRows = document.getElementById("empCategorizationResults").rows;
                sortableTableName = "empCategorizationResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
           
        </script>
    </head>
    <body style="overflow-x: hidden" onload="getRolesForAccType();getAccountNames();">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div>

        </header>

        <section id="generalForm">

            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-13 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font color="#ffffff">Action Resources</font>
                                                <i id="updownArrow" onclick="toggleContent('searchAccAuthorization')" class="fa fa-angle-up"></i> 
                                                <s:url var="myUrl" action="getAccAuthrization.action">
                                                </s:url>
                                                <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                </h4>
                                            </div>

                                        </div>
                                        <!-- content start -->
                                        <div ><label class="labelStylereq" style="color: #56a5ec;">Action Name:&nbsp; </label><label style="color: #FF8A14;"><s:property value="action_name" /></label></div>
                                    <s:hidden id="action_id" name="action_id" value="%{action_id}"/>
                                    <s:hidden id="action_name" name="action_name" value="%{action_name}"/>
                                    <s:hidden id="authId" name="authId" value="authrization"/>
                                    <div class="col-sm-13">
                                        <div id="outputMessage" style="color: red"></div>
                                        <div class="row">
                                        <s:form action="searchAccAuthorization" theme="simple">
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Account Type: </label>
                                                <s:select  id="accType"
                                                           name="accType"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1"  
                                                           headerValue="All"
                                                           theme="simple" onchange="getRolesForAccType();"
                                                           list="#@java.util.LinkedHashMap@{'C':'Customer','V':'Vendor','M':'Main'}"
                                                           />
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Status: </label>
                                                <s:select  id="status"
                                                           name="status"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1"  
                                                           theme="simple"
                                                           list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'Inactive','All':'All'}"
                                                           />
                                            </div >
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Roles: </label>
                                                <s:select  id="roles"
                                                           name="roles"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1" 
                                                           theme="simple"
                                                           headerValue="All"
                                                           list="#@java.util.LinkedHashMap@{}"
                                                           />
                                            </div >
                                            <div class="col-sm-4">
                                                <s:hidden name="orgId" id="orgId"/>
                                                <label class="labelStylereq" style="color:#56a5ec;">Account Name: </label>
                                                <s:textfield id="accountNamePopup"
                                                             cssClass="form-control"
                                                             type="text"
                                                             name="accName"
                                                             placeholder="Account Name"
                                                             onkeyup="return getAccountNames();"  maxLength="60"/> 
                                                <span id="validationMessage" />
                                            </div>
                                            <div class="col-sm-2 pull-right">
                                                <label class="labelStylereq" style="color:#56a5ec;"> </label>
                                                <a href='actionResourcesForAddOrUpdate.action?action_id=<s:property value="%{action_id}"/>&AMP;action_name=<s:property value="%{action_name}"/>&AMP;accType=C' style="margin: 5px 0px;" class="add_searchButton form-control"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>

                                            </div>
                                            <div class="col-sm-2 pull-right">
                                                <label class="labelStylereq" style="color:#56a5ec;"> </label>
                                                <button type="button" style="margin: 5px 0px;"
                                                        class="add_searchButton  form-control"
                                                        value="" onclick="return getActionResorucesSearchResults();"><i class="fa fa-search"></i>&nbsp;Search</button>
                                            </div>
                                                </div>
                                            <br>
                                        </s:form>

                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                        <div class="col-sm-12">

                                            <s:form>
                                                <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                    <table id="empCategorizationResults" class="responsive CSSTable_task sortable" border="5" cell-spacing="2">
                                                        <tbody>
                                                            <tr>

                                                                <th>Organization Name</th>
                                                                <th class="unsortable">Role Name</th>
                                                                <th class="unsortable">Status</th>
                                                                <th class="unsortable">Description</th>
                                                                <th class="unsortable">Delete</th>
                                                            </tr>
                                                            <s:if test="accauthVTO.size == 0">
                                                                <tr>
                                                                    <td colspan="5"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>

                                                            <s:iterator value="accauthVTO">
                                                                <tr>
                                                                    <s:hidden id="id" name="id" value="id"/>
                                                                    <s:url var="myUrl" action="actionResourcesForAddOrUpdate.action">
                                                                        <s:param name="id"><s:property value="id"/></s:param>
                                                                        <s:param name="action_id"><s:property value="action_id"/></s:param>
                                                                        <s:param name="action_name"><s:property value="action_name"/></s:param>
                                                                        <s:param name="accountName"><s:property value="accountName"/></s:param>
                                                                        <s:param name="roleId"><s:property value="roleId"/></s:param>

                                                                        <s:param name="accType"><s:property value="accType"/></s:param>
                                                                        <s:param name="status"><s:property value="status"/></s:param>
                                                                        <s:param name="description"><s:property value="description"/></s:param>
                                                                        <s:param name="rollName"><s:property value="rollName"/></s:param>
                                                                        <s:param name="userGroupList"><s:property value="userGroupList"/></s:param>
                                                                        <s:param name="flag">update</s:param>


                                                                    </s:url>
                                                                    <td><s:a href='%{#myUrl}'><s:property value="accountName"></s:property></s:a></td>
                                                                    <td><s:property value="rollName"></s:property></td>
                                                                    <td><s:property value="status"></s:property></td>
                                                                    <td><s:property value="description"></s:property></td>
                                                                    <td><s:a href="#" onclick="actionResourceTermination('%{id}','%{action_id}');"><img src="<s:url value="/includes/images/delete.png"/>" height="25" width="25"></s:a></td>
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
                                                        Action's per page
                                                    </label>

                                                    <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                </div>
                                            </s:form>
                                            <script type="text/javascript">
                                                var vpager = new Pager('empCategorizationResults', 10);
                                                vpager.init();
                                                vpager.showPageNav('vpager', 'pageNavPosition');
                                                vpager.showPage(1);
                                            </script>

                                        </div>
                                    </div>
                                    <%--</s:form>--%>
                                </div>

                                <%--close of future_items--%>

                            </div>

                        </div>

                    </div>

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

        </footer>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <!--/Footer-->
        <script>
            sortables_init();
        </script>
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
                $('#empCategorizationResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#empCategorizationResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>


