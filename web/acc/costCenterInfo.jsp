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
        <title>ServicesBay :: Cost Center Add Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/account/projectOverlays.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script>
            var pager;
            function costCenterPagination(){
                        
                var paginationSize = 10; //parseInt(document.getElementById("paginationOption").value);
                        
                pager = new Pager('costCenterInfoTable', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                //document.getElementById("paginationOption").value=10;
                pager.showPage(1);
            };
            function pagerOption(){
                        
                paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                    alert(paginationSize);
                        
                pager = new Pager('costCenterInfoTable', parseInt(paginationSize));
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                pager.showPage(1);
                        
            };
        </script>

    </head>
    <body>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>

        </header>
        <%-- ------------MIDDLE -----------------------------------------%>
        <section id="generalForm"><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/>
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                <div class="backgroundcolor" >
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <font color="#ffffff">Cost Center Budget Reviews</font>
                                            <i id="updownArrowLeft" onclick="toggleContentLeft('costCenterInfo')" class="fa fa-angle-up"></i> 
                                            <s:url var="backUrl" action="costCenterSearch.action">
                                            </s:url>    
                                            <span class="pull-right"><s:a href='%{#backUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                        </h4>
                                    </div>
                                </div>

                                <s:form action="" theme="simple" id="costCenterInfo">
                                    <div class="inner-reqdiv-elements">
                                        <div class="row">
                                            <div class="col-lg-3">
                                                <s:hidden name="ccCode" id="ccCode" value="%{ccCode}"/>
                                                <label>Cost Center Code:</label><font style="color: #FF8A14;"><s:property value="ccCode"></s:property></font>
                                            </div>
                                            <div class ="col-lg-3"></div>
                                            <div class ="col-lg-3"></div>
                                            <div class ="col-lg-3">
                                                <label>Cost Center Name:</label><font style="color: #FF8A14;"><s:property value="ccName"/></font>
                                            </div></div>
                                            <span id="info"></span>
                                        <div class="row">
                                            <div class="col-lg-3">
                                                <label class="labelStylereq" style="color:#56a5ec">Year:</label>
                                                <s:textfield cssClass="form-control" id="year"  name="year" placeHolder="YYYY" value="2015" onkeypress="return validationCostCenterYear(event,this.id)"/>
                                            </div>
                                            <div class="col-lg-2 ">
                                                <label class="labelStylereq" style="color:#56a5ec"></label>
                                                <s:submit  cssClass="add_searchButton form-control" type="button" value="" onclick="return costCenterInfoSearch()" cssStyle="margin:5px 0px 0px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>
                                            </div>
                                        </div>

                                    </div>
                                </s:form>
                                <br>
                                <s:form>
                                    <span><font style="color: green"><s:property value=""></s:property></font></span>
                                    <div class="task_content" id="task_div" align="center" style="display: none" >

                                        <div>
                                            <div>
                                                <table id="costCenterInfoTable" class="responsive CSSTable_task" border="5"cell-spacing="2">
                                                    <tbody>

                                                        <tr>
                                                            <!--<th>CcId</th>-->
                                                            <th>Start Date</th>
                                                            <th>End Date</th>
                                                            <th>Estimated Amt</th>
                                                            <th>Consumed Amt</th>
                                                            <th>Balance Amt</th>
                                                            <th>Budget Status</th>
                                                            <th>Status</th>
                                                        </tr>
                                                        <s:if test="costCenterSearchList.size == 0">
                                                            <tr>
                                                                <td colspan="9"><center><font style="color: red;font-size: 15px;">No Records to display</font></center></td>
                                                        </tr>
                                                    </s:if>

                                                    <s:iterator value="costCenterSearchList">
                                                        <tr>
                                                            <td>
                                                                <s:property value="startDate"></s:property>  
                                                            </td>
                                                            <%--<s:property value="ccName"></s:property>--%>
                                                            <td><s:property value="endDate"></s:property></td>
                                                            <td><s:property value="budgetAmt"></s:property></td>
                                                            <td><s:property value="spentAmt"></s:property></td>
                                                            <td><s:property value="balanceAmt"></s:property></i></td>
                                                            <td><s:property value="budgetStatus"></s:property></i></td>
                                                            <td><s:property value="status"></s:property></i></td>
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
                                                    Budgets per page
                                                </label>
                                                <div align="right" id="pageNavPosition" style="margin: -31px -1px 9px 5px;display: none"></div>
                                            </div>
                                            <script type="text/javascript">
                                                var pager = new Pager('costCenterInfoTable', 10); 
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

        </section>
        <%-- ------------MIDDLE -----------------------------------------%>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
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
                 $('#costCenterInfoTable').tablePaginate({navigateType:'navigator'},recordPage);

            };
        $('#costCenterInfoTable').tablePaginate({navigateType:'navigator'},recordPage);
        </script>

    </body>
</html>