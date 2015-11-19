<%-- 
    Document   : login
    Created on : Feb 3, 2015, 4:04:37 PM
    Author     : Nagireddy
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Link Expired Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
         <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/general.css"/>">
       
    </head>
    <body>
        <header id="header"><!--header-->
            <div class="header_top" id="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->

        <section id="form"><!--form-->
            <p align="center" class="linkexpired_image" href=""><img src="<s:url value="/includes/images/linkexpired.jpg"/>" height="220" width="200"</p>
         <p align="center" class="linkexpired">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Try Again!!!</p>
       </section><!--/form-->
         
        <footer id="footer"><!--Footer-->
                <div class="footer-bottom" id="footer_bottom" >
                <div class="container">
                     <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
         </footer><!--/Footer-->

    </body>
</html>
