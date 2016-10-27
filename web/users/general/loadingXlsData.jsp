<%-- 
    Document   : loadingXlsData
    Created on : Aug 31, 2015, 2:50:46 PM
    Author     : MIRACLE
--%>


<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>

    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServiceBay :: Data Logger Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
     

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="doOnLoadExcel() ;pagerOption()">
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

                                                     
                                                        <font color="#ffffff">Data&nbsp;Logger</font>
                                                        <i id="updownArrow" onclick="toggleContent('searchLogger')" class="fa fa-minus"></i> 
                                                    </h4>
                                                </div>

                                            </div>
                                            <!-- content start -->
                                            <div class ="col-sm-12">
                                                <s:form action="searchLogger" theme="simple" method="POST" enctype="multipart/form-data">
                                                    <s:hidden name="sp_res" id="sp_res" value="%{sp_res}"/>
                                                    <s:hidden name="sp_exists" id="sp_exists" value="%{sp_exists}"/>
                                                    <s:hidden name="sp_failure" id="sp_failure" value="%{sp_failure}"/>
                                                
                                                    <s:hidden id="downloadFlag" name="downloadFlag" value="%{downloadFlag}"/>
                                                 
                                                    <s:hidden id="loggerFlag" name="loggerFlag" value="%{loggerFlag}"/>
                                                    <s:if test='downloadFlag=="noAttachment"'>
                                                        <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                                        </s:if>
                                                        <s:elseif test='downloadFlag=="noFile"'>
                                                        <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                                        </s:elseif> 



                                                    <s:elseif test="sp_res == 'Success'">
                                                        <span id="resultMessage"><font style="color:green">Inserted Successfully!!</font></span>
                                                        </s:elseif>
                                                        <s:elseif test="sp_exists != '' && loggerFlag == ''">
                                                        <span id="resultMessage"><font style="color:green">Record Already Exists!!</font></span>
                                                        </s:elseif>
                                                        <s:elseif test="sp_failure !='' && loggerFlag == ''">
                                                        <span id="resultMessage"><font style="color:green">Failed to Insert!!</font></span>
                                                        </s:elseif>
                                                        <s:else>

                                                    </s:else> 
                                                    <div class="row">
                                                        <div class="col-sm-3">
                                                            <label class="labelStyle" id="labelLevelStatusReq">Created Date </label>
                                                            <div class="calImage"><s:textfield cssClass="form-control " name="createdDate" placeholder="Created Date" id="created_Date" value="%{createdDate}" onkeypress="return enterDateRepository();"><i class="fa fa-calendar"></i></s:textfield>
                                                                </div></div>
                                                            <div class="col-sm-3">
                                                                <label class="labelStyle" id="labelLevelStatusReq">Status </label>
                                                            <s:select id="status" cssClass="form-control SelectBoxStyles " name="status" list="#@java.util.LinkedHashMap@{'DF':'---Select---','Success':'Success','Un-Success':'Un-Success'}"></s:select>
                                                            </div>  
                                                            <div class="col-sm-3 col-md-2 pull-right">
                                                                <label class="labelStyle" id="labelLevelStatusReq"></label>
                                                            <s:submit type="button" id="searchButton" value="" cssStyle="margin:5px" cssClass="add_searchButton form-control"> <i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                                            </div> 
                                                        </div>
                                                </s:form>
                                            </div>
                                            <div class="col-sm-12">
                                                <s:form>
                                                    <div class="col-sm-13 emp_Content" id="emp_div" align="center" style="display: none" >
                                                        <br>
                                                        <table id="Utility_logger" class="responsive CSSTable_task " border="5" cell-spacing="2">
                                                            <tbody>
                                                                <tr>
                                                                    <th>Logger</th>
                                                                    <th>Uploaded&nbsp;File</th>
                                                                    <th>Resultant&nbsp;File</th>
                                                                    <th>Created&nbsp;Date</th>
                                                                    <th>Status</th>
                                                                </tr>
                                                                <s:if test="utility_logger.size <= 0">
                                                                    <tr>
                                                                        <td colspan="8"><font style="color: red;font-size: 15px;text-align: center">No Records to display</font></td>
                                                                    </tr>
                                                                </s:if>

                                                                <s:iterator value="utility_logger">
                                                                    <s:url var="myUrl" action="downloadXlsRecords.action">
                                                                        <s:param name="excel_id"><s:property value="log_id"></s:property></s:param>
                                                                        <s:param name="loggerFlag"><s:property value="loggerFlag"></s:property></s:param>
                                                                    </s:url> 
                                                                    <s:url var="downloadUrl" action="downloadXlsRecords.action">
                                                                        <s:param name="excel_id"><s:property value="log_id"></s:property></s:param>
                                                                        <s:param name="loggerFlag"><s:property value="loggerFlag"></s:property></s:param>
                                                                        <s:param name="logDownloadFlag">logDownload</s:param>
                                                                    </s:url>
                                                                    <tr>
                                                                        <td><s:a href='%{#downloadUrl}'><s:property value="logger"></s:property></s:a></td>
                                                                        <td><s:property value="uploaded_file"></s:property></td>
                                                                        <s:if test='%{utility_status}=="Success"'>
                                                                            <td><s:property value="resultant_file"></s:property></td>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden id="log_id" name="log_id" value="%{log_id}"/>
                                                                            <td><s:a href='%{#myUrl}'><s:property value="resultant_file"></s:property></s:a></td> 
                                                                        </s:else>
                                                                        <td><s:property value="loggerCreatedDate"></s:property></td>
                                                                        <td><s:property value="utility_status"></s:property></td>
                                                                        </tr>
                                                                </s:iterator>
                                                            </tbody>
                                                        </table>
                                                        <br/>
                                                        <s:if test="utility_logger.size > 0">
                                                            <label> Display <select id="paginationOption" onchange="pagerOption()" style="width: auto" class="disPlayRecordsCss">
                                                                    <option>10</option>
                                                                    <option>15</option>
                                                                    <option>25</option>
                                                                    <option>50</option>
                                                                </select>
                                                                Log's per page
                                                            </label>
                                                        </s:if>
                                                        <div align="right" class="pull-right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>
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
        <script>
            setTimeout(function(){              
                $('#resume').remove();
            },3000);
            setTimeout(function(){              
                $('#resultMessage').remove();
            },3000);
        </script>
        <!--/Footer-->
       
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
         <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){
// alert("paginationSize");
                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                 alert(paginationSize);
                    recordPage=paginationSize;
                //alert(recordPage)
                $('#Utility_logger').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#Utility_logger').tablePaginate({navigateType:'navigator'},recordPage);
        </script>
    </body>
</html>


