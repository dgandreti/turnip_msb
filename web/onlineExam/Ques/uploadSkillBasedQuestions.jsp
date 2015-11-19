<%-- 
    Document   : ChangePassword
    Created on : Feb 3, 2015, 7:50:23 PM
    Author     : Nagireddy
--%>



<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
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
        <title>ServicesBay :: Change password Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
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

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script language="JavaScript" src="<s:url value="/includes/js/account/accountDetailsAJAX.js"/>" type="text/javascript"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/onlineexam/onlineexamAjax.js"/>"></script>

        <!-- end of new styles -->

    </head>
    <body onload="skillValues(examType.value);">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">

                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->

        <section id="generalFormDesign"><!--form-->

            <%--<div class="header-middle"><!--header-middle-->
                <div class="container">
                    
                    <div class="row">
                        <s:include value="/includes/menu/generalTopMenu.jsp"/> 
                    </div>
                        
               </div>
            </div> --%>
            <div class="container">
                <div class="row">

                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff;height:40vw;">
                        <div class="features_items"><!--features_items-->
                            <div class="col-lg-12 " style="height:35vw;">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                    <div class="backgroundcolor" >


                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">Upload Questions</font>
                                                <s:if test="uploadFlag=='uploadFlag'">
                                                    <s:url var="myUrl" action="getQuestionsList.action">
                                                    </s:url>
                                                    <span class="pull-right"><s:a href='%{#myUrl}'><img src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></s:a></span>
                                                    </s:if>

                                            </h4>
                                        </div>

                                    </div>

                                    <span><resetMessage></resetMessage></span>



                                    <div class="col-sm-12">
                                        <div class="col-lg-3"></div>
                                        <div class="col-lg-12">
                                            <s:form action="skillXlsFileUpload" id="myForm" theme="simple" method="POST" enctype="multipart/form-data" >
                                                <s:hidden name="fileExist" value="%{fileExist}"/>
                                                <span><fileuplaoderror></fileuplaoderror></span>
                                                <div class="col-lg-12">
                                                    <s:if test="fileExist!=''&&fileExist!=null">
                                                        <span id="resume"><font style='color:red;font-size:15px;'>File Name Already Exists!!</font></span>
                                                        </s:if> 
                                                </div>

                                                <div class="col-lg-12">
                                                    <div class="col-lg-3">
                                                        <label class="" style="color:#56a5ec;">ExamType: </label>
                                                        <s:select id="examType" cssClass="form-control SelectBoxStyles" name="examType"  list="#@java.util.LinkedHashMap@{'T':'Technical','S':'Psychometric'}" value="%{editQues.examType}" onchange="return skillValues(this.value);"/>
                                                    </div>
                                                    <div class="col-lg-3 ">
                                                        <label class="labelStyle" id="labelLevelStatusReq">Skills:</label> <s:select cssClass="SelectBoxStyles form-control" name="skillCategoryValue"  id="skillCategoryValue" list="skillValuesMap" /> 


                                                    </div>  

                                                    <div class="col-lg-3 required"><br>
                                                        <s:file name="xlsfile" cssClass="" label="Xls File" id="file"></s:file>
                                                            <span style="color:#4E4E4E;font-size: 10px">Upload XLS file.</span>
                                                        </div>

                                                        <div class="col-lg-3">
                                                        <s:hidden name="accountType" id="accountType" value="%{accountType}"/>

                                                        <sx:submit value="Upload" cssClass="cssbutton_emps" name="submit" onclick="return checkExtention()"/>
                                                    </div>
                                                </div>



                                            </s:form>       
                                        </div>
                                        <br>
                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                    </div>

                                    <div class="col-lg-12"><br></div>
                                    <div class="col-lg-12 dotteddiv" >
                                        <ol>
                                            <b><font color="">File Attributes :</font></b>
                                            <li>File Name should be Less Than 30 Characters.</li>
                                            <li>Accepted format of uploading file is XLS.</li>
                                        </ol>  
                                        <ol>
                                            <b>File Contents :</b>
                                            <li>Data must Start from A1 column.</li>
                                            <li>First Row contains Header Part only.</li>
                                            <li>Insert all columns in the sheet</li>
                                            <li>The correct answer for the option are represented as 1</li>
                                            <li>The remaining feilds in answers are represented as 0</li>
                                            <li>All feilds are mandatory.</li>
                                            <li>Maximum of 2000 Questions are to be uploaded</li>
                                            <li>Large file may take few minutes to upload- -Please be paitient during the process!</li>
                                            <li>Data should not contain special character except ('.',',','#','*','&','@','%','$') if incase, value will be treated as null</li>


                                        </ol> 
                                        To Download Sample File Format 
                                        <s:a href="downloadSampleExamQuesFile.action?loadingFileType=skills">Click Here</s:a> 
                                        </div>
                                    </div>

                                    <!-- maps end -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- content end -->

                <!-- content start -->

            </section><!--/form-->

            <div style="height: 95px"></div>

            <footer id="footer"><!--Footer-->

                <div class="footer-bottom" id="footer_bottom">
                    <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>

        </footer><!--/Footer-->




    </body>
</html>