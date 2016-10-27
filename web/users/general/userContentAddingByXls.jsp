<%-- 
    Document   : userContentAddingByXls
    Created on : Sep 23, 2015, 6:48:34 AM
    Author     : praveen<pkatru@miraclesoft.com>
--%>

<%@ page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay ::  Add Contact Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
       
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
      
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.form.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
    
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <style type="text/css">
            #placement-examples .east { margin-left: 450px; }
        </style>
        <script type="text/javascript">
            $(function() {
                // placement examples
                $('.east').powerTip({ placement: 'e' });
            });
        </script>
    </head>
    <body style="overflow-x: hidden" oncontextmenu="return false" onload="jumper();">
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
                                            <!-- content start -->
                                            <br>
                                            <% String accFlag = "accDetails";%> 
                                            <div class="col-sm-12"  style=" margin-top:-12px; margin-bottom: 20px">
                                             
                                                <div class="backgroundcolor" >
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">

                                                           

                                                            <% String flag = "conSearch";
                                                            %>
                                                            <font color="#ffffff"> Add Contact</font>
                                                            <span class="pull-right"><s:a id="addContactBackButton" href="javascript:history.back()"><i class="fa fa-undo"></i></s:a></span>
                                                            </h4>
                                                        </div>
                                                    </div>
                                                    <span id="addContactError"> </span> 
                                                    &nbsp;&nbsp;<span id="InsertContactInfo"></span>
                                                    <div>
                                                    <s:form name="contactform" action="getCellContentValuesForUser" cssClass="form-horizontal" theme="simple">
                                                        <div>
                                                           
                                                            <s:hidden name="contactAccountType" value="%{contactAccountType}"/>
                                                            <s:hidden name="accountSearchOrgId" value="%{accountSearchOrgId}"/>
                                                           
                                                            <s:hidden id="email_ext" name="email_ext" value="%{email_ext}" />
                                                            <s:hidden name="loadingFileType" value="Contacts"/>
                                                            <s:hidden name="filePath" value="%{filePath}"/>
                                                            <s:hidden name="path" value="%{path}"/>
                                                            <div class="inner-reqdiv-elements">
                                                                <div class="col-sm-4">
                                                                    <label class="addAcclabelStyle"><span style="color:red;">*</span>First Name</label>
                                                                    <s:select id="contactFname" cssClass="form-control SelectBoxStyles" name="contactFname" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Middle&nbsp;Name</label>
                                                                    <s:select id="contactMname" cssClass="form-control SelectBoxStyles" name="contactMname" list="%{columnsMap}"></s:select>  
                                                                        <span id="mnameError" class=""></span> 
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle"><span style="color:red;">*</span>Last Name</label>
                                                                    <s:select id="contactLname" cssClass="form-control SelectBoxStyles" name="contactLname" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                </div>
                                                                <div class="inner-reqdiv-elements">
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle"><span style="color:red;">*</span>Email </label>
                                                                    <s:select id="email1" cssClass="form-control SelectBoxStyles" name="email1" list="%{columnsMap}"></s:select>  
                                                                  

                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <label class="addAcclabelStyle">Alternate Email</label>
                                                                    <s:select id="email2" cssClass="form-control SelectBoxStyles" name="email2" list="%{columnsMap}"></s:select>  

                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Gender</label>
                                                                    <s:select id="contactGender" cssClass="form-control SelectBoxStyles" name="contactGender" list="%{columnsMap}"></s:select>  
                                                                  
                                                                </div>
                                                            </div>
                                                            <div class="inner-reqdiv-elements">

                                                                <div class="col-sm-4">
                                                                    <label class="addAcclabelStyle"><span style="color:red;">*</span>Office&nbsp;Phone</label>
                                                                    <s:select id="workPhone" cssClass="form-control SelectBoxStyles" name="workPhone" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Mobile Phone</label>
                                                                    <s:select id="phone" cssClass="form-control SelectBoxStyles" name="phone" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Home Phone</label>
                                                                    <s:select id="phone1" cssClass="form-control SelectBoxStyles" name="phone1" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                </div>
                                                                <div class="inner-reqdiv-elements">

                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle"><span style="color:red;">*</span>Title</label>
                                                                    <s:select id="title" cssClass="form-control SelectBoxStyles" name="title" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                </div>
                                                                <!-- Add By Aklakh, start -->

                                                                <div class="inner-reqdiv-elements">

                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Address</label> 
                                                                    <s:select id="address" cssClass="form-control SelectBoxStyles" name="address" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Address2</label>     
                                                                    <s:select id="address2" cssClass="form-control SelectBoxStyles" name="address2" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">City</label>    
                                                                    <s:select id="city" cssClass="form-control SelectBoxStyles" name="city" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                </div>
                                                                <div class="inner-reqdiv-elements">

                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Country</label>    
                                                                    <s:select id="country" cssClass="form-control SelectBoxStyles" name="country" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">State</label>  
                                                                    <s:select id="state" cssClass="form-control SelectBoxStyles" name="state" list="%{columnsMap}"></s:select>  
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="addAcclabelStyle">Zip</label>   
                                                                    <s:select id="zip" cssClass="form-control SelectBoxStyles" name="zip" list="%{columnsMap}"></s:select>  
                                                                    </div>

                                                                </div>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-body" id="task-panel">

                                                <div class="row">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                        <div class="col-sm-2">

                                         
                                        </div>
                                        <div class="col-sm-2 pull-right">
                                            <s:submit id="saveButton" type="button" cssStyle="margin:5px 0px;"  value="Save" cssClass="fa fa-floppy-o cssbutton" theme="simple"></s:submit>
                                            </div>
                                            </div>
                                            <br/>
                                        <s:token/>
                                    </s:form>
                                </div>
                            </div>
                        </div>
                    </div>
        <script type="text/javascript" >
            $("#conPhone").mask("(999)-999-9999");
            $("#conCPhone").mask("(999)-999-9999");
        </script>
        <script type="text/javascript" >
            $("#Officephone").mask("(999)-999-9999");
            $("#moblieNumber").mask("(999)-999-9999");
            $("#homePhone").mask("(999)-999-9999");
        </script>
        <!-- Content Close -->
    </div>
 
</section><!--/form-->
</div>
<footer id="footer"><!--Footer-->
    <div class="footer-bottom" id="footer_bottom">
        <div class="container">
            <s:include value="/includes/template/footer.jsp"/>
        </div>
    </div>
</footer><!--/Footer-->
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
<script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
</body>
</html>
