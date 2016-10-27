<%-- 
    Document   : validationexpiredpage
    Created on : Sep 30, 2015, 6:46:35 PM
    Author     : miracle
--%>

<%-- 
    Document   : onlineexamvalidationpage
    Created on : Sep 30, 2015, 6:46:18 PM
    Author     : miracle
--%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>ServicesBay Online Exam</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>

        </style>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/onlineExam/onlineExam.css"/>"/>
    </head>
    <body oncontextmenu="return false">

        <!-- Wrapper -->

        <div id="header_id">
                      <div id="header_logo" style="">
                                <a href="#" id="serviceBayLogoValidationExpire"><img src="<s:url value="/includes/images/logo30.jpg"/>" alt="loin" width="200" height="33"/></a>
                           </div>
           </div>
                <!-- End  Header-->
            

      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
            <tr>

                <td id="onlineExamtd">
                                <!--  webinar topic starts-->
                                 <div id="validationTable"  >
                                     <b id="online_text">Online Technical Assesment Test</b>
                                     <p id="online_para" > Welcome to Online Technical Assesment Test. </p><br>
<!--					<p id="online_para"> Hello Fname.Lname, </p>
                                        <p id="online_para">To Write the Online Exam click on the following link  <a href="#">Click Here</a></p>
					<p id="online_para">To Activate the Exam use the following code : </p><br>-->
                                            <div id="note_div">
                                                <font color="red">Note*:</font> 
                                           
                                            <%
                                            if(request.getAttribute("resultMessage")!=null){
                                                out.println(request.getAttribute("resultMessage").toString());
                                            }

                                            %>
                                            
                                            
                                            </div>
                                        <p id="online_thanks" > Thanks, </p>
					<p id="online_team"> ServicesBay Team.</p>
                                         </div> 
                                <!--   webinar topic ends-->
                                <div id="validationexpireLinks">
                                            <b style="">Requirement Details</b>

                                        
                                        <div id="validationExpDiv">
                                            <p id="requir_title">Requirement Title.</p>
<!--                                            <p id="requir_detail">Requirement Details</p>
                                            <p id="requir_team">ServicesBay Team</p>-->
                                        </div>
                                   
                                    <!-- speakers content ends-->
                                </div>
                                
                </td>
            </tr>

        </table>
        <!-- Wrapper -->
   <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>

</html>
