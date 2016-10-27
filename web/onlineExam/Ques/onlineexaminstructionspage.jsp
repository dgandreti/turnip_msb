<%-- 
    Document   : onlineexaminstructionspage
    Created on : Sep 30, 2015, 6:46:53 PM
    Author     : miracle
--%>

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
                <a href="#" id="serviceBayLogoInstruction"><img src="<s:url value="/includes/images/logo30.jpg"/>" alt="loin" width="200" height="33"/></a>
            </div>
        </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
            <tr>

                <td id="onlineExamtd">

                    <!--  webinar topic starts-->
                    <div id="queTable"  >
                        <b id="ass_text" >Online Technical Assesment Test</b>                    
                        <p id="ass_para"> Welcome to Online Technical Assesment Test. </p><br>
                         <p id="ass_hello"> Hello  :  <font color="#115B8F"><b><s:property value="%{consultantName}"/>,</b></font> </p><br>
                        <!--<p style="text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;">To Write the Online Exam click on the following link  <a href="#">Click Here</a></p>-->
                        <!--<p style="text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;">To Activate the Exam use the following code : </p><br>-->
                        
                        <div align="center" id="note_inst" style="border: dashed 2px red" > <font color="red">Instructions:</font> <br>
                            1. Click On the 'Start' button given in the Right Side To Start Exam. <br>
                            2. Don't refresh the page..<br>
                            3. Click the 'Submit Test' button given in the Top of this page to Submit your answers.<br>
                            4. Test will be submitted automatically if the time expired.<br>
                          

                            <%
                                if (request.getAttribute("resultMessage") != null) {
                                    out.println(request.getAttribute("resultMessage").toString());
                                }

                            %>
                             

                        </div> 
                           
                        <p id="inst_thanks"> Thanks, </p>
                        <p id="inst_team"> ServicesBay Team.</p>
                    </div>
                    <!--   webinar topic ends-->
                    <s:form action="doStartOnlineExam.action" name="instructionForm" id="instructionForm" theme="simple">
                        <div id="instructionLinks">
                            <b style="">Details </b>


                            <s:hidden id="requirementId" name="requirementId" value="%{requirementId}"/>
                            <s:hidden id="consultantId" name="consultantId" value="%{consultantId}"/>
                            <s:hidden id="examSeverity" name="examSeverity" value="%{examSeverity}"/>
                            <s:hidden id="totalQuestions" name="totalQuestions" value="%{totalQuestions}"/>
                            <s:hidden id="examType" name="examType" value="%{examType}"/>
                            <s:hidden id="durationTime" name="durationTime" value="%{durationTime}"/>
                            <s:hidden id="conTechReviewId" name="conTechReviewId" value="%{conTechReviewId}"/>
                            <s:hidden id="validationKey" name="validationKey" value="%{validationKey}"/>
                            <s:hidden id="examTopics" name="examTopics" value="%{examTopics}"/>
                            <s:hidden id="orgId" name="orgId" value="%{orgId}"/>
                            <s:hidden id="examId" name="examId" value="%{examId}"/>
                            <p id="req_title" ><b><s:property value="%{requirementTitle}"/></b></p>
                            <p id="total_marks">Total Questions : <s:property value="%{totalQuestions}"/></p>
                           <%-- <p id="que_marks">Time alloted : <s:property value="%{totalQuestions}"/></p> --%>
                            <%-- <p id="que_marks">Qualified Marks : <s:property value="%{qualifiedMarks}"/></p> --%>



                            <!-- <button  type="button" class="add_searchButton form-control" value="" style="margin:5px 0px;" onclick="getCustomerDashboardList();"> -->
                            <s:submit type="submit" value="Start" cssClass="add_searchButton form-control" id="start_button"/>
                            <!-- speakers content ends-->

                        </div>
                    </s:form>
                </td>
            </tr>
        </table>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
        <!-- Wrapper -->
    </body>

</html>

