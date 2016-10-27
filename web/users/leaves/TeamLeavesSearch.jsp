<%-- 
    Document   : Team Leave Search Page
    Created on : 19 April, 2015, 7:50:23 PM
    Author     : Kiran<adoddi@miraclesoft.com>
--%>



<%@ page import="com.mss.msp.usr.leaves.LeavesVTO"%>
<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>


        <!-- new styles -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Team Leave Search Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/addLeaveOverlay.js"/>'></script>




        <sx:head />



    </head>





    <body style="overflow-x: hidden" oncontextmenu="return false" onload="doOnLoadLeave();">
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


                    <div class="col-md-9 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">


                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Team Leave Search</font>

                                            </h4>
                                        </div>
                                    </div>

                                    <div class="col-lg-14"><br>
                                        <s:form action="showTeamMemberSearchDetails" theme="simple">

                                            <span><leaveerror></leaveerror></span>
                                            <ul class="nav nav-pills">
                                                <div class="leavefield-margin">
                                                    <label class="csslabel">Status:</label>
                                                </div>
                                                <s:select label="Status:" id="leaveStatus" name="leave_status" cssClass="selectBoxStyle leavefield-margin" headerKey="DF" headerValue="Select Leave status" theme="simple" list="#@java.util.LinkedHashMap@{'O':'Applied','C':'canceled','A':'approved','R':'rejected'}"/>
                                                <div class="leavefield-margin">
                                                    <label class="csslabel">Leave Type:</label>
                                                </div>
                                                <s:select label="Leave Type:" id="leaveType"  name="leave_type" cssStyle="margin-left: 10px" cssClass="selectBoxStyle leavefield-margin" headerKey="DF" headerValue="Select Leave Type" theme="simple" list="#@java.util.LinkedHashMap@{'PA':'Post Approval','CT':'CompTime','VA':'Vacation','TO':'TimeOff','CL':'Cancel Leave'}" />
                                                <br>
                                                <div class="leavefield-margin">
                                                    <label class="csslabel">From Date:</label>
                                                </div>
                                                <s:textfield style="margin-left:3%" cssClass="leavebox leavefield-margin dateImage" label="" name="leave_startdate" id="leavefrom" placeholder="FromDate" value="%{leave_startdate}" tabindex="1" onkeypress="return enterDateRepository();" oninvalid="setCustomValidity('Must be valid date')"  onchange="try{setCustomValidity('')}catch(e){}" />
                                                <div class="leavefield-margin">
                                                    <label class="csslabel"> &nbsp; To Date:</label>
                                                </div>
                                                <s:textfield style="margin-left:3%" cssClass="leavebox leavefield-margin dateImage" name="leave_enddate" placeholder="ToDate" value="%{leave_enddate}" id="leaveto" tabindex="2" onkeypress="return enterDateRepository();" />
                                                <br>

                                                <div class="leavefield-margin">
                                                    <label  class="csslabel">Employee Name:</label>
                                                </div>
                                                <s:select label="Employee name:" style="margin-left:-1%" id="teamMembers" name="teamMember" cssClass="selectBoxStyle leavefield-margin" headerKey="-1" headerValue="--Please Select--" theme="simple" list="teamMembersList"/>

                                                <div class="leavefield-margin">
                                                    <s:submit cssClass="col-lg-offset-9 cssbutton_action_search " id="searchButton" value="search"  onclick="return leaveSearchDateValidation()"/>
                                                </div>
                                            </ul>
                                        </s:form>
                                    </div>
                                    <div class="col-sm-12">
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
                                                                    String teamLeaveFlag = "teamLeaveFlag";
                                                                    int c = 0;
                                                                    // out.println("befor id");
                                                                    if (session.getAttribute("teamleavesData") != null) {

                                                                        List l = (List) session.getAttribute("teamleavesData");

                                                                        Iterator it = l.iterator();
                                                                        Object object = it.next();
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

                                                                    <td> <s:url var="myUrl" action="../leaves/editLeaves.action">
                                                                            <s:param name="leave_id"><%=leave_id%></s:param>
                                                                            <s:param name="leaveflag"><%=teamLeaveFlag%></s:param>
                                                                        </s:url>

                                                                        <s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/edit_icon.png"/>" height="20" width="20"></s:a></td>
                                                                    <td><%= leave_start_date%></td> 
                                                                    <td><%= leave_end_date%></td>
                                                                    <td><%= cur_status%></td>
                                                                    <td><%= leave_type%></td>
                                                                    <td><%= reports_to%></td>
                                                                    <td><%= approvedBy%></td>

                                                                </tr> 
                                                                <%

                                                                        }
                                                                    }
                                                                    if (c == 0) {
                                                                %>
                                                                <tr>
                                                                    <td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                </tr> 
                                                                <%                                                        }
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

                                                            if (session.getAttribute("teamleavesData") != null) {
                                                                session.removeAttribute("teamleavesData");
                                                            }

                                                        %>
                                                    </s:form>
                                                </div>
                                                <script type="text/javascript">
                                                    var pager = new Pager('leaves_results', 10); 
                                                    pager.init(); 
                                                    pager.showPageNav('pager', 'pageNavPosition'); 
                                                    pager.showPage(1);
                                                </script>





                                            </div>
                                        </div>
                                    </div>




                                    <br><br>


                                </div>
                            </div>
                        </div>
                        <div>
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
            </footer><!--/Footer-->

<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>

