<%-- 
    Document   : leavesDashboard
    Created on : Apr 27, 2015, 4:58:07 PM
    Author     : miracle
--%>

<%@page import="com.mss.msp.usr.leaves.LeavesVTO"%>
<%-- 
    Document   : dashboard
    Created on : Apr 16, 2015, 10:50:23 PM
    Author     : Ramakrishna<lankireddy@miraclesoft.com>,Praveen<pkatru@miraclesoft.com>
--%>



<%@page import="com.mss.msp.usr.task.TasksVTO"%>

<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<!DOCTYPE html>
<html>
    <head>


        <!-- new styles -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay ::Leaves Dashboard Search</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">

        <%-- aklakh js single file start --%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <%-- aklakh js single file end --%>
        <%-- aklakh css single file start --%>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <%-- aklakh css single file end --%>
        <%-- for date picket start--%>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
       <%--   <script type="text/JavaScript" src="<s:url value="/includes/js/general/taskOverlay.js"/>"></script>
          <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>--%>

        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <%-- for date picket end--%>
        <%--  <script type="text/javascript" src="<s:url value="/includes/js/Ajax/TaskAjax.js"/>"></script>
<script type="text/javascript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>--%>

        <sx:head />

        
            


    </head>





    <body style="overflow-x: hidden" oncontextmenu="return false" onload="getEmployeeNames();">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">

                    <s:include value="/includes/template/header.jsp"/> 

                </div>
            </div>

        </header>





        
       

        <section id="generalForm"><!--form-->


            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 

                    <!-- content start -->
                    <div class="col-md-9 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Leaves Dashboard Search</font>

                                            </h4>
                                        </div>

                                    </div>
                                    <!-- content start -->
                                    <div class="col-sm-12">
                                        <s:form action="showLeaveDashboardSearchDetails" theme="simple">
                                            <br>
                                            <ul class="nav nav-pills">
                                                <s:select  id="year"  name="year" label="Year"  cssClass="taskstatus select-margin" headerKey="-1" headerValue="-select-"  list="#@java.util.LinkedHashMap@{'2000':'2000','2001':'2001','2002':'2002','2003':'2003','2004':'2004','2005':'2005','2006':'2006','2007':'2007','2008':'2008','2009':'2009','2010':'2010','2011':'2011','2012':'2012','2013':'2013','2014':'2014','2015':'2015'}" value="2015" theme="simple" />
                                                <s:select id="leavemonth" cssClass="taskstatus select-margin" name="month" label="Month" headerKey="-1" headerValue="-select-" list="#@java.util.LinkedHashMap@{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" value="1" theme="simple"/>
                                                <s:select id="countrynames" cssClass="taskstatus select-margin" name="countryId" list="countryNames" headerKey="-1" headerValue="Select Country"/>
                                                <s:select id="departmentid" cssClass="taskstatus select-margin" name="departmentId" list="departments" headerKey="-1" headerValue="Select Department" onchange="getEmployeeNames()"/>
                                                
                                                <s:select id="employeename" cssClass="taskstatus select-margin" name="userId" list="#@java.util.LinkedHashMap@{}" headerKey="-1" headerValue="Select Employee"/>
                                                <div class="taskfieldbtn-margin">
                                                    <s:submit cssClass="cssbutton " id="searchButton" value="search"/>&nbsp;
                                                </div>
                                            </ul>
                                            <br>
                                        </s:form>
                                        
                                            
                                            
                                      <s:form>
                                        <div class="task_content" id="task_div" align="center" style="display: none" >

                                            <div>
                                                <div>

                                                    <table id="leaves_results" class="responsive CSSTable_task" border="5"cell-spacing="2">

                                                        <tbody>
                                                            <tr>
                                                                <th>Leave Id</th>
                                                                <th>Start Date</th>
                                                                <th>End Date</th>
                                                                <th>Leave Status</th>
                                                                <th>Leave Type</th>
                                                                <th>Reports To</th>
                                                                <th>Modified By</th>
                                                            </tr>
                                                            <%
                                                             int c = 0;
                                                                    // out.println("befor id");
                                                                    if (session.getAttribute("leavesDashboardData") != null) {

                                                                    List l = (List) session.getAttribute("leavesDashboardData");

                                                                        Iterator it = l.iterator();
                                                                        Object object=it.next();
                                                                        while (it.hasNext()) {
                                                                            if (c == 0) {
                                                                                c = 1;
                                                                            }
                                                            
                                                            LeavesVTO usa = (LeavesVTO) it.next();
                                                                        int leave_id = usa.getLeaveid();
                                                                        String leave_start_date = usa.getLeavestartdate();
                                                                        String leave_end_date = usa.getLeaveenddate();
                                                                        String cur_status = usa.getLeavestatus();
                                                                        String reports_to = usa.getReportsto();
                                                                        String leave_type = usa.getLeavetype();
                                                                        String approvedBy = usa.getApprovedBy();
                                                            
                                                               
                                                    %>
                                                    <tr>
                                                                          
                                                                <td> <%=leave_id%></td>
                                                                <td><%= leave_start_date%></td> 
                                                                <td><%= leave_end_date%></td>
                                                                <td><%= cur_status%></td>
                                                                <td><%= leave_type%></td>
                                                                <td><%= reports_to%></td>
                                                                <td><%= approvedBy%></td>
                                                        
                                                    </tr> 
                                                    <%

                                                        }
                                                    } if(c == 0) {
                                                    %>
                                                    <tr>
                                                        <td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                    </tr> 
                                                    <%     
                                                      }
                                                    %>
                                                </tbody>
                                            </table>
                                            <br/>
                                            <%
                                                if (c == 1) {
                                            %>
                                            <div align="right" id="pageNavPosition" style="margin-right: 0vw;"></div>
                                            <%
                                                    c = 0;

                                                }
                                                              
                                                        if (session.getAttribute("leavesDashboardData") != null) {
                                                            session.removeAttribute("leavesDashboardData");
                                                        }
                                                
                                            %>
                                        </s:form>
                                        <script type="text/javascript">
                                            var pager = new Pager('leaves_results', 4); 
                                            pager.init(); 
                                            pager.showPageNav('pager', 'pageNavPosition'); 
                                            pager.showPage(1);
                                        </script>
                                            
                                            
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


        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <div style="display: none; position: absolute; top:170px;left:320px;overflow:auto; z-index: 1900000" id="menu-popup">
            <table id="completeTable" border="1" bordercolor="#e5e4f2" style="border: 1px dashed gray;" cellpadding="0" class="cellBorder" cellspacing="0" />
        </div>

    </body>
</html>


