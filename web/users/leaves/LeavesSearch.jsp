<%-- 
    Document   : dashboard
    Created on : Feb 3, 2015, 7:50:23 PM
    Author     : Nagireddy
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
        <title>ServicesBay :: Leave Search Page</title>
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
                            <div id="leave_popup">
                                <div id="leaveBoxOverlay">

                                    <div style="background-color: #3BB9FF ">
                                        <table>
                                            <div class="" id="addtsprofileBox">
                                                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Leave&nbsp;&nbsp; </font></h4></td>
                                                <span class=" pull-right"><h5><a href="" class="leave_popup_close"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
                                        </table>
                                    </div>
                                    <div>
                                        <form action="" theme="simple" name="leaveOverlayForm">
                                            <div>

                                                <div class="col-sm-12">
                                                    <span><errorEduAdd></errorEduAdd></span>

                                                    <s:hidden value="%{reportingPerson}" name="reportingPerson" id="reportingPerson"/>
                                                    <s:hidden value="%{userid}" name="userid"/>
                                                    <div class="inner-addtaskdiv-elements">
                                                        <label class="labelStyle" style="white-space: nowrap;margin-right: 10px">Leave From</label>:<s:textfield cssClass="inputStyle leavebox dateImage" name="fromleave" id="fromleave"  placeholder="from Leave" value="%{fromleave} " cssStyle="z-index: 10000004; " onkeypress="return enterDateRepository();" onfocus="removeResultMessage()" onclick="return onLeaveEnd();"/>
                                                        <label class="labelStyle" >Required To</label>:<s:textfield cssClass="inputStyle leavebox dateImage " name="toleave" value="%{toleave} " id="toleave" placeholder="toleave "  onkeypress="return enterDateRepository();" onfocus="removeResultMessage()" onclick="return onLeaveStart();"/>


                                                    </div>  
                                                    <div class="inner-addtaskdiv-elements">
                                                        <label class="labelStyle" style="white-space: nowrap;margin-right: 5px">Leave Type</label>:<s:select  id="leavetype"  name="leaveType" cssStyle="margin-right: 109px" cssClass="selectstyle" headerKey="" headerValue="--PleaseSelect--" theme="simple" list="#@java.util.LinkedHashMap@{'PA':'Post Approval','CT':'Comptime','VA':'Vacation','TO':'Timeoff','CL':'Cancel-Leave'}" onfocus="removeResultMessage()"/>
                                                        <label class="labelStyle">Reports To</label>:<s:textfield id="reportsTo" disabled="true" name="reportPerson" placeholder="Reports To" cssClass="inputStyle" value="%{reportingPerson}" onfocus="removeResultMessage()"/>
                                                    </div>
                                                    <div class="inner-addtaskdiv-elements">
                                                        <%--label class="labelStyle" id="labelLevelStatus">Leave Status</label--%>
                                                        <s:hidden  id="leavestatus"  name="leavestatus" cssStyle="margin-right: 15px" cssClass="selectstyle" headerKey="O" headerValue="--Please Select--" theme="simple" list="#@java.util.LinkedHashMap@{'O':'Applied'}" value="O" onfocus="removeResultMessage()" />
                                                        
                                                    </div>

                                                    <div class="inner-addtaskdiv-elements">
                                                        <label class="labelStyle">Reason</label>:<s:textarea id="reason" name="task_description" placeholder="Describe The Reason for Leave " cssClass="areacss" onfocus="removeResultMessage()"  onkeyup="checkCharacters(this)" />
                                                    </div>
                                                    <div id="charNum"></div>
                                                    <div  class="inner-addtaskbtndiv-elements">
                                                        <s:reset cssClass="cssbutton " value="Clear" theme="simple" onfocus="removeResultMessage()"/>
                                                        <s:submit cssClass="cssbutton" value="AddLeave" theme="simple" onclick="return addLeave()" onfocus="removeResultMessage()"/>
                                                    </div>



                                                </div>
                                            </div>        
                                        </form>
                                    </div>

                                </div>


                            </div>

                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <%
                                                    String reportingPerson = session.getAttribute("reportsTo").toString();
                                                %>
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Leave Search</font>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="col-lg-14">
                                        <s:form action="getMyLeavesList" theme="simple">
                                            <br>
                                            <span><leaveerror></leaveerror></span>
                                            <ul class="nav nav-pills">
                                                <div class="leavefield-margin">
                                                    <label class="csslabel">Status:</label>
                                                </div>
                                                <s:select label="Status:" id="leaveStatus" name="leave_status" cssClass="selectBoxStyle leavefield-margin" headerKey="DF" headerValue="Select Leave status" value="%{leave_status}" theme="simple" list="#@java.util.LinkedHashMap@{'O':'Applied','C':'canceled','A':'approved','R':'rejected'}"/>
                                                <div class="leavefield-margin">
                                                    <label class="csslabel">Leave Type:</label>
                                                </div>
                                                <s:select label="Leave Type:" id="leaveType"  name="leave_type" cssStyle="margin-left: 10px" cssClass="selectBoxStyle leavefield-margin" headerKey="DF" headerValue="Select Leave Type" value="%{leave_type}" theme="simple" list="#@java.util.LinkedHashMap@{'PA':'Post Approval','CT':'CompTime','VA':'Vacation','TO':'TimeOff','CL':'Cancel Leave'}" />
                                                </br>
                                                <div class="leavefield-margin">
                                                    <label class="csslabel">From Date:</label>
                                                </div>
                                                <s:textfield cssClass="leavebox txtleavefield-margin dateImage" label=" " pattern="{10}" name="leave_startdate" id="leavefrom" placeholder="FromDate" value="%{leave_startdate}" tabindex="1" onkeypress="return enterDateRepository();" oninvalid="setCustomValidity('Must be valid date')"  onchange="try{setCustomValidity('')}catch(e){}" />
                                                <div class="leavefield-margin">
                                                    <label class="csslabel"> &nbsp; To Date:</label>
                                                </div>
                                                <s:textfield cssClass="leavebox txtleavefield-margin dateImage" label=" " pattern="{10}" name="leave_enddate" placeholder="ToDate" value="%{leave_enddate}" id="leaveto" tabindex="2" onkeypress="return enterDateRepository();" />
                                                <div class="leavefield-margin">
                                                    <a href="" class="leave_popup_open" ><input type="button" class="cssbutton " value="Add Leave" onclick="return checkReportedPerson()"  onfocus="clearLeaveOverlay()" ></a> 
                                                    <s:submit cssClass="cssbutton_action_search " id="searchButton" value="search" />&nbsp;                         
                                                </div>
                                            </ul>
                                            <br>
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
                                                            String myLeaveFlag="myLeaveFlag";
                                                                int c = 0;
                                                                if (session.getAttribute("leavesData") != null) {

                                                                    List l = (List) session.getAttribute("leavesData");

                                                                    Iterator it = l.iterator();
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
                                                                <td>
                                                                    <s:url var="myUrl" action="../leaves/editLeaves.action">
                                                                        <s:param name="leave_id"><%=leave_id%></s:param>
                                                                        <s:param name="leaveflag"><%=myLeaveFlag%></s:param>
                                                                    </s:url>
                                                                        
                                                                    <s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/edit_icon.png"/>" height="20" width="20"></s:a></td>
                                                                    <%--td><s:url var="myUrl" action="#"> <s:a href='%{#myUrl}'><%= leave_id%></s:a></s:url></td--%>
                                                                <td><%= leave_start_date%></td> 
                                                                <td><%= leave_end_date%></td>
                                                                <td><%= cur_status%></td>
                                                                <td><%= leave_type%></td>
                                                                <td><%= reports_to%></td>
                                                                <td><%= approvedBy%></td>
                                                            </tr> 
                                                            <%

                                                                }
                                                            } else {
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
                                                        if (session.getAttribute("leavesData") != null) {
                                                            session.removeAttribute("leavesData");
                                                        }
                                                    %>
                                                </s:form>
                                                </div>
                                                <script type="text/javascript">
                                                    var pager = new Pager('leaves_results', 4); 
                                                    pager.init(); 
                                                    pager.showPageNav('pager', 'pageNavPosition'); 
                                                    pager.showPage(1);
                                                </script>



                                                <%--<tr>
                                                    <td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                </tr> 

                                                        </tbody>
                                                    </table>
                                                    <br/>

                                                    <div align="right" id="taskpageNavPosition" style="margin-right: 0vw;"></div>



                                                    <script type="text/javascript">
                                                        var pager = new Pager('task_results', 4); 
                                                        pager.init(); 
                                                        pager.showPageNav('pager', 'taskpageNavPosition'); 
                                                        pager.showPage(1);
                                                    </script>--%>

                                            </div>
                                        </div>
                                    </div>
                                    <%--/s:form--%>
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