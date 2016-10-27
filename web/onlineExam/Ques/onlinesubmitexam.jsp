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
        <script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.onhashchange=function(){window.location.hash="no-back-button";}
</script> 
    </head>
    <body oncontextmenu="return false">

        <!-- Wrapper -->

        <div id="header_id">
                      <div id="header_logo" style="">
                                <a href="#" id="servicesBayLogoSubmit"><img src="<s:url value="/includes/images/logo30.jpg"/>" alt="loin" width="200" height="33"/></a>
                           </div>
           </div> 
                <!-- End  Header-->
            

                <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
            <tr>

                <td id="onlineExamtd">
                    <div id="successTable"  >
                 <b style="">Online Exam Submitted Successfully</b>                 
                 <p id="inst_thanks"> Thanks, </p>
		 <p id="inst_team"> ServicesBay Team.</p>
                                         </div> 
                </td>  

                                    </tr>
                                </table>
                <div align="center" class="col-sm-12" id="note_inst" style="border: solid 2px #5bc0de;width: fit-content" > <font color="red">Exam Result</font> 
            <s:if test="skillName1!='no'">
               
                <s:if test="skillResult1 < 50">
                    <br> <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName1}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult1}"/>%</b> 
                </s:if>
                <s:else>
                    <br> <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName1}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult1}"/>%</b>     
                </s:else>
            </s:if>  
            <s:if test="skillName2!='no'">
               
                <s:if test="skillResult2 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName2}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult2}"/>%</b> 
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName2}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult2}"/>%</b> 
                </s:else>

            </s:if>
            <s:if test="skillName3!='no'">
               
                <s:if test="skillResult3 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName3}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult3}"/>%</b>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName3}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult3}"/>%</b>
                </s:else>

            </s:if>
            <s:if test="skillName4!='no'">
               
                <s:if test="skillResult4 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName4}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult4}"/>%</b>

                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName4}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult4}"/>%</b>

                </s:else>
            </s:if>
            <s:if test="skillName5!='no'">
              
                <s:if test="skillResult5 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName5}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult5}"/>%</b><br>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName5}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult5}"/>%</b><br>   
                </s:else>

            </s:if>
            <s:if test="skillName6!='no'">
               
                <s:if test="skillResult6 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName6}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult6}"/>%</b>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName6}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult6}"/>%</b>
                </s:else>

            </s:if>
            <s:if test="skillName7!='no'"> 
              
                <s:if test="skillResult7 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName7}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult7}"/>%</b>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName7}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult7}"/>%</b>
                </s:else>

            </s:if>
            <s:if test="skillName8!='no'">
               
                <s:if test="skillResult8 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName8}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult8}"/>%</b><br>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName8}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult8}"/>%</b><br>     
                </s:else>

            </s:if>
            <s:if test="skillName9!='no'">
               
                <s:if test="skillResult9 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName9}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult9}"/>%</b>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName9}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult9}"/>%</b>
                </s:else>

            </s:if>
            <s:if test="skillName10!='no'">
               
                <s:if test="skillResult10 < 50">
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName10}"/></b>:<b style="color: red;margin-right: 10px"><s:property value="%{skillResult10}"/>%</b>
                </s:if>
                <s:else>
                    <b style="color: #56a5ec;margin-right: 10px"><s:property value="%{skillName10}"/></b>:<b style="color: green;margin-right: 10px"><s:property value="%{skillResult10}"/>%</b>
                </s:else>
            </s:if>
            <%--<div class="col-sm-4 menu"> <b style=" color: #56a5ec">Total</b>
            </div>
            <div class="col-sm-8 ">
                <b style="color: green">
                    <s:property value="%{totalResult}"/></b><br>  
            </div>--%>
            <%--<s:if test="totalResult < 50">
                <br><b style="color: #56a5ec;margin-right: 10px">Total</b>:<b style="color: red;margin-right: 10px"><s:property value="%{totalResult}"/></b>
            </s:if>
            <s:else> <br><b style="color: #56a5ec;margin-right: 10px">Total</b>:<b style="color: green;margin-right: 10px"><s:property value="%{totalResult}"/></b></s:else>--%>

        </div>                   
              <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>                    
                         
        <!-- Wrapper -->
    </body>

</html>

