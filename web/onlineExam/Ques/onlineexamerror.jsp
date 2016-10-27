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
                                <a href="#" id="serviceBayLogo"><img src="<s:url value="/includes/images/logo30.jpg"/>" alt="loin" width="200" height="33"/></a>
                           </div>
           </div>
                <!-- End  Header-->
            

      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
            <tr>

                <td id="onlineExamtd">
                             
                                 <div id="validationTable"  >
                                     <b id="online_text">Online&nbsp;Technical&nbsp;Assesment Test</b>
                                   
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
                            
                                <div id="validationexpireLinks">
                                            <b style="">Requirement Details</b>

                                        
                                        <div id="validationExpDiv">
                                            <p id="requir_title">Requirement Title.</p>

                                        </div>
                                   
                               
                                </div>
                                
                </td>
            </tr>

        </table>
        <!-- Wrapper -->
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>

</html>
