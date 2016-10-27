<%-- 
    Document   : vendorsSearch
    Created on : May 1, 2015, 3:46:51 PM
    Author     : Praveen<pkatru@miraclesoft.com>
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Vendor Account Search Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/vendorAjax.js"/>'></script>
        <script>
            var pager;
            $(document).ready(function(){

                var paginationSize = 10;
                pager = new Pager('accountSearchResults', paginationSize);
                pager.init();
                pager.showPageNav('pager', 'pageNavPosition');
                pager.showPage(1);
            });
            function pagerOption(){

                paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                //alert(paginationSize);

                pager = new Pager('accountSearchResults', parseInt(paginationSize));
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);

        };
        </script>


        <script type="text/javascript">
        var myCalendar;

        function doOnLoad(){
            myCalendar = new dhtmlXCalendarObject(["accountLastAccessDate"]);
            myCalendar.setSkin('omega');
            //        myCalendar.setDateFormat("%Y/%m/%d ");
            var today = new Date();
            var maxPastDate = new Date(today.getFullYear()-3,today.getMonth(),today.getDate());
            myCalendar.setSensitiveRange(maxPastDate, today);
            document.getElementById("accountLastAccessDate").value=overlayDate;
        }


        </script>

    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="doOnLoad();">
        <div id="wrap">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/>
                </div>
            </div>
        </header>
        <s:include value="/includes/menu/LeftMenu.jsp"/>
      <div id="main">
        <section id="generalForm"><!--form-->
            <div  class="container">
                <div class="row">
                    <!-- content start -->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Vendor Search</font>
                                                 <i id="updownArrow" onclick="toggleContent('vendorDashboardSearch')" class="fa fa-angle-up"></i> 
                                            </h4>
                                        </div>
                                    </div>
                                    <!-- content start -->
                                    <div class="col-sm-12">
                                        <s:form  theme="simple" id="vendorDashboardSearch">
                                            <s:hidden name="vendorFlag" id="vendorFlag" value="%{vendorFlag}"/>
                                            <br>
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <s:textfield  cssClass="textbox" label="VendorName" id="VendorName"
                                                                  type="text" name="VendorName" placeholder="Vendor Name"
                                                                  />
                                                </div>
                                                <div class="col-sm-4">
                                                    <s:textfield cssClass="textbox" id="VendorURL" type="text"
                                                                 name="VendorURL" placeholder="Vendor Url" />
                                                </div>
                                                <div class="col-sm-4">
                                                    <s:textfield cssClass="textbox" id="VendorPhone"
                                                                 type="dropdown" name="VendorPhone"
                                                                 placeholder="VendorPhone"
                                                                 />
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <s:select  id="VendorCountry" type="dropdown"
                                                               name="VendorCountry" placeholder="Vendorcountry"
                                                               list="%{countryNames}" label="VendorCountry" headerKey="-1"
                                                               headerValue="Vendor country "
                                                               cssClass="selectBoxStyle" onchange="return getStates()"/>
                                                </div>
                                                <div class="col-sm-4">
                                                    <s:select  id="VendorState" type="dropdown"
                                                               name="VendorState" placeholder="State"
                                                               list="{}" label="State" headerKey="-1"
                                                               headerValue="Select State"
                                                               cssClass="selectBoxStyle"/>
                                                </div>

                                                <div class="col-sm-4">
                                                    <s:select  id="vendorStatus" type="dropdown"
                                                               name="selectedAccountStatus" placeholder="Status"
                                                               list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" label="Status" headerKey="-1"
                                                               headerValue="Select Status"
                                                               cssClass="selectBoxStyle "/>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <s:if test="vendorFlag == 'Team'">  
                                                    <div class="col-sm-4">

                                                        <s:select id="teamMembers" name="teamMembers"
                                                                  cssClass="selectBoxStyle" headerKey="-1"
                                                                  headerValue="--Please Select--"
                                                                  theme="simple" list="teamMembersList"
                                                                  />
                                                    </div>
                                                </s:if>   

                                                <div class="col-sm-4 pull-right"> <s:submit type="button" cssClass="cssbutton_emps "
                                                          value="Search" onclick="return getVendorSearchDetails()" cssStyle="margin:5px"/></div>
                                            </div>
                                        </div>

                                    </s:form>
                                    <br>
                                    <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                    <div class="">
                                        <br>
                                        <s:form>

                                            <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                <table id="vendorSearchResults" class="responsive CSSTable_task" border="5">
                                                    <tbody>
                                                        <tr>
                                                            <th>Vendor_Name</th>
                                                            <th>Vendor_URL </th>
                                                            <th>Vendor_City</th>
                                                            <th>Vendor_State</th>
                                                            <th>Phone</th>
                                                            <th>Industry</th>
                                                            <th>Last Access Date</th>
                                                            <th>Status</th>
                                                        </tr>

                                                        <s:if test="vendorListVto ==null">
                                                            <tr>
                                                                <td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                            </tr>
                                                        </s:if>
                                                        <% String venFlag="venDetails"; %>
                                                        <s:iterator  value="vendorListVto">
                                                            <s:url var="myUrl" action="vendorDetails.action">
                                                                <%--<s:param name="taskid"><%=task_id%></s:param></s:url>--%>

                                                                <s:param name="vendorId" value="%{org_id}" />
                                                                <s:param name="venFlag"><%=venFlag%></s:param>
                                                            </s:url>
                                                            <tr>
                                                                <td><s:a href='%{#myUrl}'><s:property value="%{vendorName}"></s:property></s:a></td>
                                                                <td><s:a value="http://%{vendorUrl}"><s:property value="%{vendorUrl}" /></s:a></td>
                                                                <td><s:property value="%{vendorCity}"></s:property></td>
                                                                <td><s:property value="%{vendorState}"></s:property></td>
                                                                <td><s:property value="%{vendorPhone}"></s:property></td>
                                                                <td><s:property value="%{industry}"></s:property></td>
                                                                <td><s:property value="%{lastAccessDate}"></s:property></td>

                                                                    <td><s:property value="%{Status}"></s:property></td>
                                                                </tr>
                                                        </s:iterator>

                                                    </tbody>
                                                </table>
                                                <br/>
                                                <label> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                        <option>10</option>
                                                        <option>15</option>
                                                        <option>25</option>
                                                        <option>50</option>
                                                    </select>
                                                    Accounts per page
                                                </label>
                                                <div align="right" id="pageNavPosition" style="margin-right: 0vw;"></div>
                                                <script type="text/javascript">
                                                var pager = new Pager('vendorSearchResults', 10); 
                                                pager.init(); 
                                                pager.showPageNav('pager', 'pageNavPosition'); 
                                                pager.showPage(1);
                                                </script>
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
    </footer><!--/Footer-->
    <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
    <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
</body>
</html>
