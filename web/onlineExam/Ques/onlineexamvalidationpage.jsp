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
                                <a href="#" id="serviceBayLogoValidation" ><img src="<s:url value="/includes/images/logo30.jpg"/>" alt="loin" width="200" height="33"/></a>
                           </div>
           </div>
                <!-- End  Header-->
            
 <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
            <tr>

                <td id="onlineExamtd">
                    
                                <!--  webinar topic starts-->
                                       <div id="validateTable"  >
                                           <b id="tech_text">Online Technical Assesment Test</b>                 
                                     <p id="tech_para"> Welcome to Online Technical Assesment Test. </p><br>
				   <p id="tech_hello"> Hello  <font color="#115B8F"><b><s:property value="%{consultantName}"/>,</b></font> </p>
                                            <!--<p style="text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;">To Write the Online Exam click on the following link  <a href="#">Click Here</a></p>-->
											<!--<p style="text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;">To Activate the Exam use the following code : </p><br>-->
                                            <div id="div_notes"style=""> <font color="red">Note*:</font> 
                                            <ul>
                                                <li>The Activation code provided will be expired in 24 Hours.</li>
                                                <li>The Activation code is only for one time use.</li>
                                            </ul>
                                            
                                            
                                            
                                            
                                            </div>
                                            <p id="inst_thanks"> Thanks, </p>
					    <p id="inst_team"> ServicesBay Team.</p>
                                         </div> 
                                                                                         
                                <!--   webinar topic ends-->
                                <s:form action="doValidateConsultant.action" name="validationForm" id="validationForm" theme="simple">
                                    <div id="consultLinks">
                                        <b >Consultant&nbsp;Verification</b>

                                     
                                    <s:hidden name="token" id="token" value="%{token}"/>
                                    <s:hidden id="consultantName" name="consultantName" value="%{consultantName}"/>
                                    <s:hidden id="examTopics" name="examTopics" value="%{examTopics}"/>
                                    <br/>
                                    <div>
                                     <%
                                            if(request.getAttribute("resultMessage")!=null){
                                                out.println(request.getAttribute("resultMessage").toString());
                                            }

                                            %>
                                    </div>        
                                    <div id="valid_form">
                                            Token: 
                                        
                                            <s:textfield name="validationKey" id="validationKey" /> <br/><br/>
                                       
                                            Email:
                                        
                                             <s:textfield name="email" id="email" /><br/>
                                       
                                            
                                          
                                             
                                             <s:submit  name="submit" value="Next" cssClass="add_searchButton form-control" id="sumitValidationButton"/>
                                               
                                            
                                        
                                  

                                      </div>
                                    </div>
                                                </s:form>
                                
                                
                         
                </td>
            </tr>

        </table>
                                            <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <!-- Wrapper -->
    </body>

</html>
