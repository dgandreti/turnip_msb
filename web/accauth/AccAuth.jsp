<%-- 
    Document   : Action Authorization
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
        <title>ServicesBay :: Action Authorization Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/addAccountStyles.css"/>'>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>

        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/GeneralAjax.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script>
//            var pager;
//            function onLoad(){
//                //alert("onload")
//                var paginationSize = 10; // parseInt(document.getElementById("paginationOption").value);
//                // alert(paginationSize);
//                pager = new Pager('empCategorizationResults', paginationSize);
//                pager.init();
//                pager.showPageNav('pager', 'pageNavPosition');
//              //  document.getElementById("paginationOption").value=10;
//                pager.showPage(1);
//            };
//            function pagerOption(){
//
//                paginationSize = document.getElementById("paginationOption").value;
//                if(isNaN(paginationSize))
//                    alert(paginationSize);
//
//                pager = new Pager('empCategorizationResults', parseInt(paginationSize));
//                pager.init();
//                pager.showPageNav('pager', 'pageNavPosition');
//                pager.showPage(1);
//
//            };
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
    <body style="overflow-x: hidden" onload="onLoad();">
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
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-16 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Action Search</font>
                                                 <i id="updownArrow" onclick="toggleContent('searchAccAuthorization')" class="fa fa-angle-up"></i> 
                                            </h4>
                                        </div>

                                    </div>
                                    <!-- content start -->
                                    <div class="charNum" style="color:green;font-weight: bold" id="accAuthId">
                                        <%
                                                if (request.getParameter("resultMessage") != null) {
                                                    out.println(request.getParameter("resultMessage"));
                                                }
                                            %> 
                                     </div>   
                                    <div class="col-sm-16">
                                        <s:form action="searchAccAuthorization" theme="simple">
                                            <div class="col-lg-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Action Name: </label>
                                                <s:textfield id="actionName"
                                                             cssClass="form-control"
                                                             type="text"
                                                             name="actionName"
                                                             placeholder="Action Name" maxLength="60"
                                                             /> 
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="labelStylereq" style="color:#56a5ec;">Status: </label>
                                                <s:select  id="status"
                                                           name="status"
                                                           cssClass="SelectBoxStyles form-control"
                                                           headerKey="-1"  
                                                           theme="simple"
                                                           list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','All':'All'}"
                                                           />
                                            </div >
                                            <br>
                                            <div class="col-lg-2">

                                                <button type="button" style="margin: 5px 0px;" class="add_searchButton form-control addAuthAccOverlay_popup_open cssbutton" onclick="addAuthAccOverlayFun();addOrUpdateChecking('a');clearActionValues();"  style=""><i class="fa fa-plus-square"></i>&nbsp;Add</button>
                                            </div>
                                            <div class="col-lg-2">

                                                <s:submit type="button" cssStyle="margin:5px 0px;"
                                                          cssClass="add_searchButton form-control"
                                                          value=""><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                            </div>

                                        </s:form>

                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                        <div class="col-sm-12">

                                            <s:form>
                                                <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                    <table id="empCategorizationResults" class="responsive CSSTable_task sortable" border="5" cell-spacing="2">
                                                        <tbody>
                                                            <tr>

                                                                <th>Action Name</th>

                                                                <th class="unsortable">Status</th>

                                                                <th class="unsortable">Description</th>

                                                                <th class="unsortable">Resources</th>
                                                            </tr>
                                                            <s:if test="accauthVTO.size == 0">
                                                                <tr>
                                                                    <td colspan="4"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>

                                                            <s:iterator value="accauthVTO">
                                                                <tr>
                                                                    <s:hidden id="action_id" name="action_id" value="action_id"/>
                                                                    <td><s:a href='#' cssClass="addAuthAccOverlay_popup_open" onclick="addAuthAccOverlayFun();addOrUpdateChecking('u');setAccAuthorizationValues('%{action_id}','%{action_name}','%{status}','%{description}');"><s:property value="action_name"></s:property></s:a></td>
                                                                    <td><s:property value="status"></s:property></td>
                                                                    <s:if test="description.length()>9">  
                                                                        <td><s:a href="#" cssClass="authAccOverlay_popup_open" onclick="authAccOverlayFun('%{description}')" ><s:property  value="%{description.substring(0,10)}"/></s:a></td>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:a href="#" cssClass="authAccOverlay_popup_open" onclick="authAccOverlayFun('%{description}')" ><s:property  value="%{description}"/></s:a></td>
                                                                    </s:else>
                                                                    <s:url var="myUrl" action="searchActionResources.action">
                                                                        <s:param name="action_id"><s:property value="action_id"/></s:param>
                                                                        <s:param name="action_name"><s:property value="action_name"/></s:param>
                                                                    </s:url>
                                                                    <td><s:a href='%{#myUrl}'>GO</s:a></td>
                                                                    </tr>
                                                            </s:iterator>
                                                        </tbody>
                                                    </table>
                                                    <br/>
                                                    <s:if test="accauthVTO.size > 0">
                                                    <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                            <option>10</option>
                                                            <option>15</option>
                                                            <option>25</option>
                                                            <option>50</option>
                                                        </select>
                                                        Action's per page
                                                    </label>
                                                     </s:if>
                                                    <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
                                                </div>
                                            </s:form>


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
            <%-- Start overlay for Display Description --%>
            <div id="authAccOverlay_popup" >
                <div id="authAccBox">
                    <div class="backgroundcolor">
                        <table>
                            <tr><td><h4 style="font-family:cursive"><font class="titleColor" >&nbsp;&nbsp;Description &nbsp;&nbsp; </font></h4></td>
                            <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="authAccOverlay_popup_close" onclick="authAccOverlayFun()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                        </table>
                    </div>
                        
                    <div>
                        <div class="inner-reqdiv-elements">

                           <s:textarea id="outputMessageOfauthAcc" disabled="true" cssClass="form-control textareaActionDescOverlay"></s:textarea>

                        </div>



                    </div>
                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                </div>   
            </div> 

            <%-- end overlay for Display Description --%>
            <%-- Start overlay for for Add Acc Authorization  --%>
            <div id="addAuthAccOverlay_popup" >
                <div id="addAuthAccBox">
                    <div class="backgroundcolor">
                        <table>
                            <tr><td><h4 id="" style="font-family:cursive"><font id="heading" class="titleColor" >&nbsp;&nbsp;Add Account Authorization &nbsp;&nbsp; </font></h4></td>
                            <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="addAuthAccOverlay_popup_close" onclick="addAuthAccOverlayFun()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                        </table>
                    </div>
                         <span id="validationMessage"></span>
                    <div>
                        <s:hidden id="overlayActionId" value="overlayActionId"/>
                        <s:hidden id="overlayActionName" value="overlayActionName"/>
                        <s:hidden id="overlayActionStatus" value="overlayActionStatus"/>
                        <s:hidden id="overlayActionDesc" value="overlayActionDesc"/>
                        <%-- <div class="inner-addAcc-elements ">
                             <label class="addAcclabelStyle">Email:</label><s:textfield cssClass="addAccInputStyle" placeholder="Email" name="contactEmail" id="ContactEmail" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,}" oninvalid="setCustomValidity('Must be valid email')"   onchange="try{setCustomValidity('')}catch(e){}"  onkeyup="EmailValidation()" />
                             </div>--%>
                        <br>
                        <div class="inner-addAcc-elements" id="accNameDiv">
                            <label class="addAcclabelStyle" style="color:#56a5ec;"><span class="accAuthError">*</span>Action Name: </label>
                            <s:textfield id="action_name" 
                                         cssClass="addAccInputStyle"
                                         type="text"
                                         name="action_name"
                                         placeholder="Action Name"
                                            onfocus="return removeErrorMsg();"
                                            maxLength="60"
                                         /> 
                        </div>
                        <div class="inner-addAcc-elements" id="statusDiv">
                            <%--  <s:hidden id="overLayAction_id" name="overLayAction_id" value="action_id"/>--%>
                            <label class="addAcclabelStyle" style="color:#56a5ec;">Status: </label>
                            <s:select  id="accauthStatus"
                                       name="accauthStatus"
                                       cssClass="addAccSelectStyle"
                                       headerKey="-1"  
                                       theme="simple" 
                                       list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"
                                       />
                        </div>
                        <div class="inner-addAcc-elements" id="descDiv">
                            <label class="addAcclabelStyle" style="color:#56a5ec;"><span class="accAuthError">*</span>Description: </label>
                            <s:textarea id="addingAccAuthDesc" cssClass="authareacss" name="addingAccAuthDesc" onkeydown="actionAuthDescription(this)"  onfocus="return removeErrorMsg();"/>

                        </div>
                        <span class="charNum" id="addingAccAuthValid"></span> 
                        <div class="col-lg-6"></div>
                        <div class="col-lg-4" id="addDiv">


                            <button type="button" style="margin: 5px 0px;" class="add_searchButton  form-control" onclick="insertOrUpdateAccAuth('0');" value=""><i class="fa fa-plus-square"></i>&nbsp;Add</button>


                        </div>
                        <div class="col-lg-4" id="updateDiv">

                            <button type="button" style="margin: 5px 0px;" class="add_searchButton  form-control" onclick="insertOrUpdateAccAuth('1');" value=""><i class="fa fa-refresh"></i>&nbsp;update</button>

                        </div>

                        <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                    </div>   
                </div> 
            </div>

            <%-- end overlay for Add Acc Authorization  --%>

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
         <script>
            var message=$("#accAuthId").html("");
            //alert(message)
             $("#accAuthId").show().delay(15000).fadeOut();
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
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>
    </body>
</html>


