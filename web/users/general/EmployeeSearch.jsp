<%-- 
    Document   : dashboard
    Created on : Feb 3, 2015, 7:50:23 PM
    Author     : Nagireddy
--%>
<%@page import="com.mss.msp.usersdata.UserVTO"%>
<%@page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@page import="java.util.Iterator"%>
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
        <title>ServicesBay :: Employee Search Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
    </head>
    <body oncontextmenu="return false" style="overflow-x: hidden">
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
                    <div class="col-md-9 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-14 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Employee Search</font>

                                            </h4>
                                        </div>

                                    </div>
                                    <!-- content start -->
                                    <div class="col-sm-12">
                                        <s:form action="searchEmployee" method="post" theme="simple" >
                                            <br>
                                            <span cellspacing="30">
                                                <div class="employeefield-margin" >
                                                    <label class="csslabel2" style="margin-right: -5px">Email Id</label>
                                                    <s:textfield cssClass="textbox field-margin" id="empLoginId" type="text" name="empLoginId"  placeholder="Email Id"/>   
                                                </div>
                                                <div class="employeefield-margin" >
                                                    <label class="csslabel2" style="margin-right: -18px">Emp Name</label>
                                                    <s:textfield cssClass="textbox field-margin inputTextBlue" theme="simple" id="empName" type="text" name="empName" placeholder="Employee Name" />
                                                </div>
                                                <div class="employeefield-margin" >
                                                    <label class="csslabel2" style="margin-right:-18px ">W Phone</label>
                                                    <s:textfield cssClass="textbox field-margin" id="workPhone" type="text" name="workPhone" placeholder="Work Phone No." />  
                                                </div> <%--  <s:select cssClass="selectBoxStyle field-margin" id="organization" name="organization" headerKey="" headerValue="Select Organization" theme="simple" list="{'marketing','Sales'}"/>--%>
                                                <div class="employeefield-margin" >
                                                    <label class="csslabel2">Department</label>  
                                                    <s:select id="departmentId" name="departmentId" cssClass="selectBoxStyle field-margin" headerKey="-1" headerValue="Select Department" theme="simple" list="departments" />
                                                </div>
                                                <div class="employeefield-margin" >
                                                    <label class="csslabel2 ">Rep Person</label>
                                                    <s:select id="reportingPerson" name="reportingPerson" cssClass="selectBoxStyle field-margin" headerKey="-1" headerValue="Select Reporting Person" theme="simple" list="reportsTo" />                                    
                                                </div>
                                                <div class="employeefield-margin" >
                                                    <label class="csslabel2">Cur status</label>
                                                    <s:select name="status" id="status" label="Status"  cssClass="selectBoxStyle field-margin" theme="simple" list="{'Active','Inactive','Registered'}" />    <br/>                                       
                                                </div>
                                                <div class="checkbox-inline selectBOx-field-margin" >
                                                    <label for="is_manager" class="checkbox field-margin">
                                                        <s:checkbox name="is_manager" id="is_manager" type="checkbox"/> Manager</label>
                                                </div><div class="checkbox-inline selectBOx-field-margin" >
                                                    <label for="opt_contact" class="checkbox field-margin">
                                                        <s:checkbox name="opt_contact" id="opt_contact" type="checkbox"/>Operation Contact</label>
                                                </div>
                                                <div class="checkbox-inline selectBOx-field-margin" >
                                                    <label for="team_leader" class="checkbox field-margin">

                                                        <s:checkbox name="team_leader" id="team_leader" type="checkbox"/>Team Leader</label>
                                                </div><br/>

                                                <s:submit type="submit" cssClass="cssbutton_search field-margin pull-right" value="Search"/>
                                                <span id="validationMessage" />
                                            </span>
                                        </s:form>       
                                        <br>
                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                        <div>
                                            <s:form>   
                                                <div class="emp_Content" id="emp_div" align="center" style="display: none"    >
                                                    <table id="emp_results" class="responsive CSSTable_task" border="5"cell-spacing="2">
                                                        <tbody>
                                                            <tr>
                                                                <th>Login Id</th>
                                                                <th>Employee Name</th>
                                                                <th>Status</th>
                                                                <th>Contact Number</th>
                                                            </tr>
                                                            <%
                                                                int c = 0;
                                                                if (session.getAttribute("empData") != null) {

                                                                    List l = (List) session.getAttribute("empData");

                                                                    Iterator it = l.iterator();
                                                                    while (it.hasNext()) {
                                                                        if (c == 0) {
                                                                            c = 1;

                                                                        }
                                                                        UserVTO usa = (UserVTO) it.next();
                                                                        String login_id = usa.getEmpLoginId();
                                                                        String empName = usa.getEmpName();
                                                                        int usr_Id = usa.getEmpId();
                                                                        String cur_status = usa.getCur_status();
                                                                        String phone1 = usa.getPhone1();
                                                            %>
                                                            <tr>
                                                                <td>
                                                                    <s:url var="myUrl" action="../general/getempProfile.action">
                                                                        <s:param name="userid"><%= usr_Id%></s:param></s:url>

                                                                    <s:a href='%{#myUrl}'><%= login_id%></s:a></td> 

                                                                <td><%= empName%></td>
                                                                <td><%= cur_status%></td>
                                                                <td><%= phone1%></td>
                                                            </tr> 
                                                            <%

                                                                }
                                                            } else {
                                                            %>
                                                            <tr>
                                                                <td colspan="4"><font style="color: red;font-size: 15px;">No Records to display</font></td>
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
                                                        if (session.getAttribute("empData") != null) {
                                                            session.removeAttribute("empData");
                                                        }
                                                    %>
                                                </s:form>
                                                <script type="text/javascript">
                                                    var pager = new Pager('emp_results', 20); 
                                                    pager.init(); 
                                                    pager.showPageNav('pager', 'pageNavPosition'); 
                                                    pager.showPage(1);
                                                </script>

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
                             </section><!--/form-->
        </div>
        <!-- content end -->
   
    </div>
    <footer id="footer"><!--Footer-->
        <div class="footer-bottom" id="footer_bottom">
            <div class="container">
                <s:include value="/includes/template/footer.jsp"/>
            </div>
        </div>
            
    </footer><!--/Footer-->
    <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
 <%--    <script type="text/javascript">
   
            $("#workPhone").mask("(999)-999-9999");
            
 
        </script>  --%>
</body>
</html>


