<%-- 
    Document   : xlsuploading
    Created on : Aug 28, 2015, 6:48:51 PM
    Author     : praveen<pkatru@miraclesoft.com>
--%>



<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServiceBay :: Account Uploading Page</title>

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



    </head>
    <body oncontextmenu="return false" style="overflow-x: hidden" >
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
                                    <div class="col-lg-14 ">
                                        <div class="" id="profileBox" style="float: left; margin-top: 5px">

                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">

                                                     
                                                        <font color="#ffffff">Accounts Uploading</font>
                                                        <s:url var="myUrl" action="../../acc/accountadd.action">
                                                        </s:url>
                                                     

                                                    </h4>
                                                </div>
                                            </div>
                                            <!-- content start -->
                                            <div class="col-sm-12">
                                                <div class="row"></div>
                                                <div class="row">
                                                    <s:form action="xlsFileUpload" id="myForm" theme="simple" method="POST" enctype="multipart/form-data" >
                                                        <s:hidden name="fileExist" value="%{fileExist}"/>
                                                        <span id="fileUploadingValidation"><fileuplaoderror></fileuplaoderror></span>
                                                        <div class="col-sm-12">
                                                            <s:if test="fileExist!=''&&fileExist!=null">
                                                                <span id="resume"><font style='color:red;font-size:15px;'>File Name Already Exists!!</font></span>
                                                                </s:if> 
                                                        </div>
                                                        <!--<div class="col-lg-12">-->
                                                        <div class="row"><br></div>
                                                        <div class="col-sm-12">
                                                            <div class="col-sm-4">
                                                                <s:select name="accountType" id="accountType" value="" list="#@java.util.LinkedHashMap@{'Customer':'Customer','Vendor':'Vendor'}" cssClass="SelectBoxStyles form-control"/>
                                                            </div>

                                                            <div class="col-sm-4">
                                                                <s:file name="xlsfile" cssClass="" style="width:200px" label="Xls File" id="file" onchange="return checkExtensionFile(this.id)"></s:file>
                                                                    <span style="color:#4E4E4E;font-size: 10px">Upload XLS file.</span>
                                                                </div>
                                                                <div class="col-sm-4 pull-right">
                                                           

                                                                <s:submit value="Upload" type="button" cssClass="cssbutton widget_1195417415 btn_upld fa fa-upload pull-left" name="submit" onclick="return checkExtention()" />
                                                            </div>
                                                        </div>
                                                        <s:token/>
                                                        <!--</div>-->
                                                    </s:form>       


                                                  
                                                </div>
                                                <br>
                                                <div class="col-sm-12">
                                                    <div class="row">
                                                        <ol>
                                                            <b><font color="">File Attributes :</font></b>
                                                            <li>File Name should be Less Than 30 Characters.</li>
                                                            <li>File Type must be XLS.</li>
                                                        </ol>  
                                                        <ol>
                                                            <b>File Contents :</b>
                                                            <li>Data must Start from A1 column.</li>
                                                            <li>First Row contains Header Part only.</li>
                                                            <li>Mandatory Fields are Company Name,Company URL and Mail Extention.</li>
                                                            <li>Data should not contain special character except ('.',',','#','*','&','@','%','$') if incase, value will be treated as null</li>
                                                            <li>Phone,Fax Number's must be in the Following Format</li>
                                                            (000)-000-0000
                                                        </ol> 
                                                        To Download Sample File Format 
                                                        <s:a id="sampleDownloadButton" href="downloadSample.action">Click Here</s:a> 
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        <%--close of future_items--%>
                                    </div>
                                </div>
                            </div>

                        </div>
                </section>
            </div>


            <!-- content end -->
            <!--/form-->
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <!--/Footer-->
         <script type="text/javascript">
            $("#fileUploadingValidation").show().delay(5000).fadeOut();
        </script>
        <script>
            setTimeout(function(){              
                $('#resume').remove();
            },3000);
        </script>
    </body>
</html>


