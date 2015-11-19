<%-- 
    Document   : current_status
    Created on : May 18, 2015, 10:40:07 PM
    Author     : praveen<pkatru@miraclesoft.com>
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="org.apache.struts2.components.Property"%>
<%@page import="com.mss.msp.usersdata.UserAddress"%>
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
        <title>ServicesBay :: Current status Page</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/consultantOverlay.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/fileUploadScript.js"/>"></script>


        <!-- end of new styles -->
    </head>
    <body style="font-family: 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans' ;overflow-x: hidden">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->
        </header><!--/header-->
        <!-- overlays code starts from -->   
        <!-- overlays ended here -->

        <s:include value="/includes/menu/LeftMenu.jsp"/> 

        <section id="generalForm"><!--form-->
            <div  class="container">
                <div class="row">
                    <!-- content start -->
                    <div class="col-md-9 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items">
                            <div class="col-lg-12 ">
                                <div class="" id="profileBox" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                <font color="#ffffff">My&nbsp;Resumes</font>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="col-lg-12" id="consult-attach">
                                        <s:hidden name="resumeDownlaod" id="resumeDownload" value="%{resumeDownlaod}"/>
                                        <a href="" class="consultAttachment_popup_open pull-right" onclick="return attachPopJs();"><button id="cssbutton" >Add Updated Resume</button></a> <br/>    
                                        <s:if test='resumeDownlaod=="noResume"'>
                                             <span id="resume"><font style='color:red;font-size:15px;'>No Attachment exists !!</font></span>
                                         </s:if>
                                       <s:hidden name="resumeDownlaod" id="resumeDownload" value="%{resumeDownlaod}"/>
                                        <s:if test='resumeDownlaod=="noFile"'>
                                            <span id="resume"><font style='color:red;font-size:15px;'>File Not Found !!</font></span>
                                           </s:if> 
                                       <table id="consult-attach_pagenav" class="CSSTable_task  responsive"  >

                                            <tr>
                                                <th>Attachment</th>
                                                <th>Date Of Attachment</th>
                                                <th>Download Link
                                                </th>
                                                <th>Status
                                                </th>
                                                <th>Delete</th>
                                            </tr>

                                            <s:if test="%{consultantListVTO.size()==0}">
                                                <tr>
                                                    <td colspan="10"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                </tr>
                                            </s:if>
                                            <s:else> 
                                                <s:iterator  value="consultantListVTO">
                                                    <!--build url TO goto Account Details-->
                                                    <s:url id="myUrl" action="Requirements/requirementedit.action" namespace="/" encode="true">
                                                        <s:param name="object_id" value="%{object_id}" />
                                                        <s:param name="acc_attachment_id" value="%{acc_attachment_id}" />

                                                    </s:url>
                                                    <tr>
                                                        <td><s:property value="attachement_file_name"></s:property></td>
                                                        <td><s:property value="date_of_attachment"></s:property></td>
                                                        <td><s:a href='#' onclick="doResumeDownlaod('%{acc_attachment_id}')"><img src='../../includes/images/download.png' height=20 width=20 ></s:a></td>
                                                        <td><s:property value="status"></s:property></td>
                                                        <td><s:url var="consultUrl" action="deleteConsultantAttachment">
                                                                <s:param name="acc_attachment_id" value="%{acc_attachment_id}"></s:param>
                                                            </s:url>
                                                            <s:a href='%{#consultUrl}' onclick="return confirm('Are you sure you want to delete this record');" ><img src='../../includes/images/deleteImage.png' height=20 width=20 ></s:a></td>

                                                    </tr>
                                                </s:iterator>
                                            </s:else>
                                            </tbody>
                                        </table>


                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- content end -->
                <!-- Attachment overlay  -->
                <div id="consultAttachment_popup">
                    <div id="taskAttachOverlay">

                        <div style="background-color: #3BB9FF ">
                            <table>
                                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Resume&nbsp;&nbsp; </font></h4></td>
                                <span class=" pull-right"><h5><a href="" class="consultAttachment_popup_close" onclick="attachPopJs();showAttachmentDetails('<%= request.getParameter("consult_id")%>');"><img src="<s:url value="/includes/images/close_button.jpg"/>" height="25" width="25"></a>&nbsp;</h5></span>
                            </table>
                        </div>
                        <div>
                            <br>
                            <s:form action="UploadConsultantAttachments" id="UploadForm" theme="simple"   enctype="multipart/form-data" >
                                <div>
                                    <div class="inner-addtaskdiv-elements">
                                        <div id="message"></div>

                                        <s:hidden id="consult_id" name="consult_id" value="%{consult_id}"/>
                                        <s:file name="file" id="file"/>
                                    </div>
                                    <%--<s:submit cssClass="cssbutton task_popup_close" value="AddTask" theme="simple" onclick="addTaskFunction();" />--%>
                                    <center><s:submit cssClass="cssbutton" value="ADD" theme="simple"  /></center><br>
                                </div>

                            </div>
                        </s:form>
                    </div>
                </div>

            </div>

        </section><!--/form-->
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script>
              setTimeout(function(){
                     $('#resume').remove();
                    },3000);
   </script>
    </body>
</html>


