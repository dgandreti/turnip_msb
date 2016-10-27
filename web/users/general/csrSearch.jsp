<%-- 
    Document   : CSR Search
    Created on : july 15, 2015, 7:50:23 PM
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
        <title>ServicesBay :: CSR Search Page</title>

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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        

        <script type="text/javascript">
            function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("csrResults");
                sortableTableRows = document.getElementById("csrResults").rows;
                sortableTableName = "csrResults";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
            function onLoad()
            {
           
                document.getElementById("loadingCsrSearch").style.display="none";
                
    
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
                                    <div class="col-sm-14 ">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">

                                                        <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                        <font color="#ffffff">CSR Search</font>
                                                        <i id="updownArrow" onclick="toggleContent('csrList')" class="fa fa-minus"></i> 

                                                    </h4>
                                                </div>

                                            </div>
                                            <span id="csrList">
                                            <!-- content start -->
                                            <!--<div class="col-sm-12">-->
                                                <s:form action="csrList" theme="simple" >
                                                    <div class="col-sm-4">
                                                        <s:hidden name="accFlag" id="accFlag" value="%{accFlag}"/>
                                                        <label class="labelStylereq" style="color:#56a5ec;">Name </label>
                                                        <s:textfield id="name"
                                                                     cssClass="form-control"
                                                                     type="text"
                                                                     name="empName"
                                                                     placeholder="First Name"
                                                                     maxLength="30"
                                                                     tabindex="1" /> 
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Email Id </label>
                                                        <s:textfield id="email"
                                                                     name="email1"
                                                                     cssClass="form-control"
                                                                     theme="simple"
                                                                     type="text"
                                                                     placeholder="Email" maxLength="60"
                                                                     tabindex="2" />
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="labelStylereq" style="color:#56a5ec;">Status </label>
                                                        <s:select  id="status"
                                                                   name="status"
                                                                   cssClass="SelectBoxStyles form-control"
                                                                   headerKey="-1"  
                                                                   theme="simple"
                                                                   list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active','All':'All'}"
                                                                   tabindex="3" />
                                                    </div>
                                                    <!--<div class="col-sm-8"></div>-->
                                                <!-- </div>-->
                                                <div class="row">
                                                    <div class="col-sm-2 pull-right">



                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                        <span class=""><s:submit type="button"
                                                                  id="csrSearchButton"
                                                                  cssClass="add_searchButton form-control"
                                                                  value="" cssStyle="margin:5px 0px;" 
                                                                  tabindex="4" ><i class="fa fa-search"></i>&nbsp;Search</s:submit></span>



                                                        </div>
                                                    </div>


                                            </s:form>
                                             </span>
                                            <div id="loadingCsrSearch" class="loadingImg">
                                                <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>   ></span>
                                            </div>
                                            <br>
                                            <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                            <div class="col-sm-12">

                                                <s:form>
                                                    <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                        <table id="csrResults" class="responsive CSSTable_task sortable" border="5" cell-spacing="2">
                                                            <tbody>
                                                                <tr>
                                                                    <th>Name</th>
                                                                    <th class="unsortable">E-mail</th>
                                                                    <th class="unsortable">Status</th>
                                                                    <th>No.of Accounts</th>
                                                                    <th class="unsortable">Terminate</th>
                                                                </tr>
                                                                <s:if test="userVTO.size == 0">
                                                                    <tr>
                                                                        <td colspan="5"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:iterator value="userVTO">
                                                                    <tr>

                                                                        <s:hidden id="usrId" name="usrId" value="empId"/>
                                                                        <td><s:property value="first_name"></s:property></td>
                                                                        <td><s:property value="email1"></s:property></td>
                                                                        <td><s:property value="cur_status"></s:property></td>
                                                                            <td>
                                                                            <s:url var="myUrl" action="getCsrAccounts.action">
                                                                                <%--<s:param name="taskid"><%=task_id%></s:param></s:url>--%>
                                                                                <s:param name="userId" value="%{empId}" />
                                                                                <s:param name="csrName" value="%{first_name}" />
                                                                            </s:url>
                                                                            <s:a href='%{#myUrl}'><s:property value="noOfAccounts"></s:property></s:a>
                                                                                </td>
                                                                                <td>
                                                                           <s:if test="noOfAccounts!=0">
                                                                            <s:a href="#" onclick="csrTermination('%{empId}');"><img src="<s:url value="/includes/images/delete.png"/>" height="25" width="25"></s:a></td>
                                                                            </s:if>
                                                                            <s:else>
                                                                            <img style="opacity: 0.2" src="<s:url value="/includes/images/delete.png"/>" height="25" width="25" >
                                                                            </s:else>    </tr>
                                                                </s:iterator>
                                                            </tbody>
                                                        </table>
                                                        <s:if test="userVTO.size > 0">
                                                            <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">

                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                CSR's per page
                                                            </label>
                                                        </s:if>
                                                        <!--                                                    <div align="right" id="pageNavPosition" style="margin-right: 0vw;"></div>-->
                                                    </s:form>
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
                    <%--<div id="csrTerminateOverlay_popup" >
                        <div id="csrTerminateBox">
                            <div class="backgroundcolor">
                                <table>
                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor" >&nbsp;&nbsp;Termination &nbsp;&nbsp; </font></h4></td>
                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="csrTerminateOverlay_popup_close" onclick="csrTerminateOverlay()" ><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a></h5></span>
                                </table>
                            </div>
                            <div>
                                <div class="inner-reqdiv-elements">

                            <s:hidden id="userId" name="userId"/>


                            Terminate the CSR?<div  id="csrName" ></div>
                        </div>

                        <div class="pull-left " >
                            <s:submit type="button" cssClass="cssbutton csrTerminateOverlay_popup_close" id="contactCancel" onclick="csrTerminateOverlay()" value="Cancel"/>  
                        </div>  
                        <div class="pull-right " > 

                            <s:submit type="button" cssClass="cssbutton" id="contactSend" value="Ok" onclick="saveContactDetails()"/> 

                        </div>

                    </div>
                    <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                </div>   
            </div> 
                    --%>
                    <%-- end overlay for csr termination --%>

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
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){
                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                //alert(paginationSize);
                {
                            
                }
                recordPage=paginationSize;
              
                $('#csrResults').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#csrResults').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <!--/Footer-->
        <script>
            sortables_init();
        </script>

    </body>
</html>


