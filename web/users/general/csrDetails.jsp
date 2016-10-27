<%-- 
    Document   : CSR Details
    Created on : July 16, 2015, 7:50:23 PM
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
        <title>ServicesBay :: CSR Details Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
     

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script>
            var pager;
          
        </script>
        <script type="text/javascript">
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("csrAccountResults");
                sortableTableRows = document.getElementById("csrAccountResults").rows;
                sortableTableName = "csrAccountResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
             function onLoad()
            {
           
                document.getElementById("loadingCsrDetailsSearch").style.display="none";
                
    
            }
        </script>
    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="onLoad();">
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

                                                    
                                                        <font color="#ffffff">CSR Accounts</font>
                                                        <i id="updownArrowAccount" onclick="toggleContentAccount('csrDetailsForm')" class="fa fa-angle-up"></i> 
                                                        <s:url var="myUrl" action="csrList.action">
                                                        </s:url>
                                                        <span class="pull-right"><s:a href='%{#myUrl}' id="csrDetailsBackButton" ><i class="fa fa-undo"></i></s:a></span>
                                                        </h4>
                                                    </div>

                                                </div>
                                                <!-- content start -->
                                                <div class="col-sm-12">
                                                    <div id="csrDetailsForm">
                                                        <div>  <label class="labelStylereq" style="color:#56a5ec;">CSR Name : </label>
                                                            <span style="color: #FF8A14;"><s:property value="csrName"/></span>
                                                    </div>
                                                    <s:hidden name="csrUserId" id="csrUserId" value="%{userId}"/>  
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Account Name </label>
                                                        <s:textfield id="accountName"
                                                                     cssClass="form-control"
                                                                     type="text"
                                                                     name="accountName"
                                                                     placeholder="Account Name" maxLength="60"
                                                                     tabindex="1" /> 
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Status </label>
                                                        <s:select  id="csrStatus"
                                                                   name="csrStatus"
                                                                   cssClass="SelectBoxStyles form-control"
                                                                   headerKey="-1"  
                                                                   theme="simple"
                                                                   list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','All':'All'}"
                                                                   tabindex="2" />
                                                    </div>
                                                    <br>

                                                    <div class="col-sm-2 pull-right">



                                                        <span class=""><s:submit type="button"
                                                                  id="csrAccountsSearchButton"
                                                                  cssClass="add_searchButton form-control"
                                                                  value="" cssStyle="margin:5px 0PX;" onclick="return accountSearch();"  tabindex="3" ><i class="fa fa-search"></i>&nbsp;Search</s:submit></span>



                                                        </div><br/><br/>
                                                    </div>
                                                    <div id="loadingCsrDetailsSearch" class="loadingImg">
                                                                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                                                                            </div>
                                                 
                                                <div class="col-sm-12">

                                                    <s:form>
                                                        <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                            <table id="csrAccountResults" class="responsive CSSTable_task sortable" border="5" cell-spacing="2">
                                                                <tbody>
                                                                    <tr>
                                                                        <th>Account Name</th>
                                                                        <th class="unsortable">Status</th>
                                                                    </tr>
                                                                    <s:if test="userVTO.size == 0">
                                                                        <tr>
                                                                            <td colspan="5"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                        </tr>
                                                                    </s:if>
                                                                    <s:iterator value="userVTO">
                                                                        <tr>
                                                                            <s:hidden id="userId" name="userId" value="empId"/>
                                                                            <s:hidden id="orgId" name="orgId" value="orgId"/> 
                                                                            <td><s:property value="accountName"></s:property></td>
                                                                          
                                                                            <td><s:a href="#" cssClass="csrTerminateOverlay_popup_open" onclick="csrTerminateOverlay('%{cur_status}');csrStatusValue('%{orgId}','%{empId}')"><s:property value="cur_status"></s:property></s:a></td>
                                                                                </tr>
                                                                    </s:iterator>
                                                                </tbody>
                                                            </table>
                                                            <br/>

                                                            <s:if test="userVTO.size > 0">

                                                                <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="csr_pagerOption()" style="width: auto">
                                                                        
                                                                        <option>10</option>
                                                                        <option>15</option>
                                                                        <option>25</option>
                                                                        <option>50</option>
                                                                    </select>
                                                                    CSR's per page
                                                                </label>
                                                            </s:if>
                                                        
                                                        </s:form>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                        <%--close of future_items--%>

                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                    <%-- Start overlay for csr termination --%>
                    <div id="csrTerminateOverlay_popup" >
                        <div id="csrTerminateBox">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor" >&nbsp;&nbsp;Change Status &nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="csrTerminateOverlay_popup_close" onclick="csrTerminateOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                </table>
                            </div>
                            <div>
                                <div class="inner-reqdiv-elements">
                                    <s:hidden id="overlayUserId" name="csrUserId"/>
                                    <s:hidden id="overlayOrgId" name="csrOrgId"/>
                                    <div id="outputMessageOfUpdate"></div>
                                    <div class="col-sm-10">
                                        <label class="labelStylereq" style="color:#56a5ec;">Status </label>
                                        <s:select  id="status"
                                                   name="status"
                                                   cssClass="SelectBoxStyles form-control"
                                                   headerKey="-1" 
                                                   theme="simple"
                                                   list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}"
                                                   />
                                    </div>
                                </div>


                                
                                <div class="col-sm-4 pull-right"> 

                                    <s:submit type="button"  cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" id="contactSend" value="" onclick="changeCsrAccountStatus()"><i class="fa fa-check-circle-o"></i>&nbsp;Ok</s:submit> 

                                    </div>
                                    <div class="col-sm-4 pull-right">
                                    <s:submit type="button"  cssStyle="margin:5px 0px;" cssClass="add_searchButton  form-control csrTerminateOverlay_popup_close" id="contactCancel" onclick="csrTerminateOverlay()" value=""><i class="fa fa-times"></i>&nbsp;Cancel</s:submit>  
                                    </div> 
                                </div>
                                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                            </div>   
                        </div> 

                  
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

        <!--/Footer-->
        <script>
            sortables_init();
        </script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>

        <script type="text/javascript">
            var recordPage=10;
            function csr_pagerOption(){
                //alert("recordPage")
                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                //alert(paginationSize+"pagesiz");
                {
                            
                }
                recordPage=paginationSize;
                // alert(recordPage)
                $('#csrAccountResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#csrAccountResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>


