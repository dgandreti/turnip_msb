<%-- 
    Document   : onlineexaminstructionspage
    Created on : Sep 30, 2015, 6:46:53 PM
    Author     : miracle
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
        <script type="text/JavaScript" src="<s:url value="/includes/js/onlineexam/onlineexamAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/onlineexam/CountDown.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/onlineExam/onlineExam.css"/>">
        <script type="text/JavaScript">

            function disableButtons(){

            document.getElementById("submitButton").disabled = true;
            document.getElementById("previous").disabled = true;
            document.getElementById("next").disabled = true;

            var totalQuestions = parseInt(document.getElementById("totalQuest").value);
            for (var i=1;i<=totalQuestions;i++)
            {

            document.getElementById("q"+i).disabled = true;
            }

            }
            function enableButtons(){

            document.getElementById("submitButton").disabled = false;
            document.getElementById("previous").disabled = false;
            document.getElementById("next").disabled = false;

            var totalQuestions = parseInt(document.getElementById("totalQuest").value);

            for (var i=1;i<=totalQuestions;i++)
            {
            document.getElementById("q"+i).disabled = false;
            }
            }
            function strtExamInit() {
            var t = document.getElementById("durationTime").value;
            var examId = parseInt(document.getElementById("examId").value);
            var time=t.split(":");
            var hours=parseInt(time[0],10);
            var minuts=parseInt(time[1],10);
            var seconds=parseInt(time[2],10);
            var totalSeconds=0;
            if(minuts>0)
            totalSeconds=parseInt(minuts*60);
            if(hours>0)
            totalSeconds=parseInt(totalSeconds+parseInt(hours*60*60));
            if(seconds>0)
            totalSeconds=parseInt(totalSeconds+seconds);


            ActivateCountDown("CountDownPanel", totalSeconds);

            getQuestion(parseInt(0),0,0,0,0,0,0,'I',0,0,0,0,examId);
            }
            function getNext() {
            var isChecked = false;
            var answer1=0;
            var answer2=0;
            var answer3=0;
            var answer4=0;
            var answer5=0;
            var answer6=0;
            disableButtons();
            var subtopicId = parseInt(document.getElementById("subtopicId").value);

            var nextQid = parseInt(document.getElementById("questionId").value);
            var examId = parseInt(document.getElementById("examId").value);

            var radios = document.getElementsByName('option');
            var checkedValue = 0;
            for (var i = 0, length = radios.length; i < length; i++) {

            if (radios[i].checked) {
            // do whatever you want with the checked radio
            checkedValue = radios[i].value;
            //alert(checkedValue)
            if(checkedValue==1)
            {
            answer1=1;   
            }
            if(checkedValue==2)
            {
            answer2=1;   
            }
            if(checkedValue==3)
            {
            answer3=1;   
            }  
            if(checkedValue==4)
            {
            answer4=1;   
            }
            if(checkedValue==5)
            {
            answer5=1;   
            } 

            if(checkedValue==6)
            {
            answer6=1;   
            }  

            isChecked = true;

            }
            }

            var remQues = document.getElementById("hideremainingQuestions").value;
            getQuestion(nextQid,answer1,answer2,answer3,answer4,answer5,answer6,'N',1,remQues,subtopicId,0,examId);
            enableButtons();
            if(isChecked == true) {

            document.getElementById("flag"+nextQid).style.color="green";

            }
            }

            function getPrevious() {

            var answer1=0;
            var answer2=0;
            var answer3=0;
            var answer4=0;
            var answer5=0;
            var answer6=0;
            disableButtons();
            var isChecked = false;

            var subtopicId = parseInt(document.getElementById("subtopicId").value);
            var previoueQuestionNo = parseInt(document.getElementById("questionId").value);
            var examId = parseInt(document.getElementById("examId").value);
            var radios = document.getElementsByName('option');
            var checkedValue = 0;
            for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
            checkedValue = radios[i].value;

            if(checkedValue==1)
            {
            answer1=1;   
            }
            if(checkedValue==2)
            {
            answer2=1;   
            }
            if(checkedValue==3)
            {
            answer3=1;   
            }  
            if(checkedValue==4)
            {
            answer4=1;   
            }
            if(checkedValue==5)
            {
            answer5=1;   
            } 

            if(checkedValue==6)
            {
            answer6=1;   
            }  
            isChecked = true;

            }
            }
            var remQues = document.getElementById("hideremainingQuestions").value;
            getQuestion(previoueQuestionNo,answer1,answer2,answer3,answer4,answer5,answer6,'P',1,remQues,subtopicId,0,examId);
            enableButtons();
            if(isChecked == true) {
            document.getElementById("flag"+previoueQuestionNo).style.color="green";

            }
            }
            function getSpecificQuestion(reqQuestion) {
            var answer1=0;
            var answer2=0;
            var answer3=0;
            var answer4=0;
            var answer5=0;
            var answer6=0;
            disableButtons();
            var isChecked = false;
            var subtopicId = parseInt(document.getElementById("subtopicId").value);
            var questionNo = parseInt(document.getElementById("questionId").value);
            var examId = parseInt(document.getElementById("examId").value);
            var radios = document.getElementsByName('option');
            var checkedValue = 0;
            for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
            checkedValue = radios[i].value;
            // only one radio can be logically checked, don't check the rest
            if(checkedValue==1)
            {
            answer1=1;   
            }
            if(checkedValue==2)
            {
            answer2=1;   
            }
            if(checkedValue==3)
            {
            answer3=1;   
            }  
            if(checkedValue==4)
            {
            answer4=1;   
            }
            if(checkedValue==5)
            {
            answer5=1;   
            } 

            if(checkedValue==6)
            {
            answer6=1;   
            }  
            isChecked = true;

            }
            }
            var remQues = document.getElementById("hideremainingQuestions").value;
            getQuestion(questionNo,answer1,answer2,answer3,answer4,answer5,answer6,'R',1,remQues,subtopicId,reqQuestion,examId);
            enableButtons();
            if(isChecked == true) {
            document.getElementById("flag"+questionNo).style.color="green";

            }
            }
            function getsubmitForm(){

            var answer1=0;
            var answer2=0;
            var answer3=0;
            var answer4=0;
            var answer5=0;
            var answer6=0;

            swal({

            title: "Are You Sure to Submit Exam?",

            //text: "Tranfering csr",
            textSize:"170px",
            type: "warning",

            showCancelButton: true,
            confirmButtonColor: "#3498db",

            //cancelButtonColor: "#56a5ec",
            cancelButtonText: "No",
            confirmButtonText: "Yes",
            closeOnConfirm: false,
            closeOnCancel: false

            },
            function(isConfirm){
            if (isConfirm) {

            disableButtons();
            var subtopicId = parseInt(document.getElementById("subtopicId").value);
            var nextQid = document.getElementById("questionId").value;
            var radios = document.getElementsByName('option');
            var checkedValue = 0;
            var examId = parseInt(document.getElementById("examId").value);
            for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
            checkedValue = radios[i].value;
            if(checkedValue==1)
            {
            answer1=1;   
            }
            if(checkedValue==2)
            {
            answer2=1;   
            }
            if(checkedValue==3)
            {
            answer3=1;   
            }  
            if(checkedValue==4)
            {
            answer4=1;   
            }
            if(checkedValue==5)
            {
            answer5=1;   
            } 

            if(checkedValue==6)
            {
            answer6=1;   
            } 

            }
            }
            var remQues = document.getElementById("hideremainingQuestions").value;
            getQuestion(nextQid,answer1,answer2,answer3,answer4,answer5,answer6,'S',1,remQues,subtopicId,0,examId);
            swal("Sucess", " Submitted Succefully ", "success");


            }



            else {

            swal("Cancelled", " Cancelled ", "error");


            }
            });

            }
            function showDiv(idInfo,idInfo1,id) {
            var sel = document.getElementById(idInfo+'Que').getElementsByTagName('div');
            for (var i=0; i<sel.length; i++) {
            sel[i].style.display = 'none';
            }
            sel = document.getElementById(idInfo+'QueLinks').getElementsByTagName('a');
            // alert(sel.length);
            for (var i=0; i<sel.length; i++) {
            //   alert(sel[i].id);
            sel[i].className = '';
            }

            id.className = 'active';
            document.getElementById(idInfo+'container'+idInfo1).style.display = 'block';
            }

            function disableBack(){
            window.location.hash="no-back-button";
            window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
            window.onhashchange=function(){window.location.hash="no-back-button";}
            }



        </script>
        <script>

        </script>



    <body oncontextmenu="return false" onload="strtExamInit();
            disableBack();" onpageshow="if (event.persisted) noBack();">

        <!-- header -->
        <div id="header_id">
            <div id="header_logo" style="">
                <a href="#" id="servicesBayExamLogo"><img src="<s:url value="/includes/images/logo30.jpg"/>" alt="loin" width="200" height="33"/></a>
            </div>
            <div id="header_name">
                <p style="  ">
                    Welcome : <font color="#115B8F"><b><s:property value="#session.onlineexamconsultantname"/>,</b></font></font> 
                </p> 
            </div>
        </div>
        <s:form action="doSubmitOnlineExam.action" name="examinationForm" id="examinationForm" theme="simple">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
                <tr>

                    <td id="onlineExamtd">

                        <!--  webinar topic starts-->
                        <div id="queTable"  style="">

                            <b id="topic">
                                Topic:&nbsp;<span id="sectionName"></span>&nbsp;&nbsp; </b> <br>
                            <span style="align:right;">Remaining Questions:</span>&nbsp;
                            <span id="remainingQuestions"></span>&nbsp;&nbsp;
                            <div id="remTime">
                                <span >Remaining&nbsp;Time&nbsp;:</span>&nbsp;<span id="CountDownPanel" style="font-family: fantasy"></span>

                            </div><br>
                            <input type="button" value="Submit Exam" onclick="getsubmitForm()" Class="buttonBg" id="submitButton"/> 

                            <b id="marqueeText">
                                <font color="white"> 
                                <marquee direction="left" scrollamount="3" >Don't refresh while writing the exam !</marquee>
                                </font>
                            </b>



                            <s:hidden name="domainName" id="domainName" value="%{domainName}"/>
                            <s:hidden name="topicName" id="topicName" />
                            <s:hidden name="subTopicName" id="subTopicName" />
                            <s:hidden name="totalQuest" id = "totalQuest" value="%{totalQuestions}"/>
                            <s:hidden name="durationTime" id="durationTime" value="%{durationTime}"/>
                            <s:hidden name="hideremainingQuestions" id = "hideremainingQuestions"/>
                            <s:hidden name="examNumber" id = "examNumber" value="%{examNumber}"/>
                            <s:hidden name="examType" id="examType" value="%{examType}"/>
                            <s:hidden name="questionId" id = "questionId" value=""/>
                            <s:hidden name="subtopicId" id = "subtopicId" value=""/>
                            <s:hidden id="validationKey" name="validationKey" value="%{validationKey}"/>
                            <s:hidden id="examTopics" name="examTopics" value="%{examTopics}"/>
                            <s:hidden id="conTechReviewId" name="conTechReviewId" value="%{conTechReviewId}"/>
                            <s:hidden id="examId" name="examId" value="%{examId}"/>
                            <s:hidden name="totalQuestions" id = "totalQuestions" value="%{totalQuestions}"/>
                            <div id="mainQue" style="">
                                <div id="javaQue" style="">
                                    <div id="container"> 
                                        <!-- <p style="text-align:justify;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;font-size:17px"> Welcome to Online Technical Assesment Test. </p><br>-->
                                        <p> 
                                            <span id="ques"></span><span>.</span><span id="qname" style="text-align: justify;"></span><br> </p>
                                        <!--<img src="#image" alt="" name="image6" width="100"span height="100" id="image6" />-->
                                        <!--<p style="text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;">To Write the Online Exam click on the following link  <a href="#">Click Here</a></p>-->
                                        <!--<p style="text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;">To Activate the Exam use the following code : </p><br>-->
                                        <span id="questionPath"  style="text-align: justify;display: none">Q</span><br/>
                                        <div id="option1Div" style="display: none"><input type="radio" name="option" value="1" id="radio1">&nbsp;<span id="opt1"></span><br></div>
                                        <div id="option2Div" style="display: none"><input type="radio" name="option" value="2" id="radio2">&nbsp;<span id="opt2"></span><br></div>
                                        <div id="option3Div" style="display: none"><input type="radio" name="option" value="3" id="radio3">&nbsp;<span id="opt3"></span><br></div>
                                        <div id="option4Div" style="display: none"><input type="radio" name="option" value="4" id="radio4">&nbsp;<span id="opt4"></span><br></div>
                                        <div id="option5Div" style="display: none"><input type="radio" name="option" value="5" id="radio5">&nbsp;<span id="opt5"></span><br></div>
                                        <div id="option6Div" style="display: none"><input type="radio" name="option" value="6" id="radio6">&nbsp;<span id="opt6"></span><br></div>
                                    </div>    
                                </div>

                                <%
                                    if (request.getAttribute("resultMessage") != null) {
                                        out.println(request.getAttribute("resultMessage").toString());
                                    }

                                %>
                            </div>    




                            <br>
                            <input type="button" value="Previous" Class="buttonBg" onclick="getPrevious()" id="previous"/>
                            <input  type="button" value="Next" Class="buttonBg" onclick="getNext()" id="next" /> &nbsp;&nbsp;
                        </div>
                        <!--   webinar topic ends-->
                        <div id="topicQue"  style="">
                            <div id="topics" >

                                <!--<b style="color: #F03D49;font-size: 32px;margin: 0px;padding: 0px;font-family:Lato,Calibri,Arial,sans-serif;">Examination page</b>-->
                                <b>Topics:</b>

                                <div id="topicborder"></div>
                                <s:iterator value="#session.onlineskillsmap" var="myVar">  

                                    <!--                                    <a href="javascript:void(0);"  class="active"> 
                                                                           </a> -->
                                    <s:property value="value" /><br/>


                                </s:iterator> 
                            </div>                   
                            <%--
                                    if(session.getAttribute("onlineskillsmap")!=null){
                                      Map<Integer, Integer> map = new HashMap<Integer, Integer>();
 for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
}

                            --%>

                            <s:hidden id="requirementId" name="requirementId" value="%{requirementId}"/>
                            <s:hidden id="consultantId" name="consultantId" value="%{consultantId}"/>



                            <!--                                        <td style=" border-bottom: 1px solid black !important;width: 10px;">
                                                                        <p style="font-size: 27px;color:#115B8F;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 0px;"><b><s:property value="%{requirementTitle}"/></b></p>
                                                                        <p style="font-family:Lato,Calibri,Arial,sans-serif;font-size: 18px;margin: 0px;padding: 0px;">Total Questions : <s:property value="%{totalQuestions}"/></p>
                                                                        <p style="font-family:Lato,Calibri,Arial,sans-serif;font-size: 18px;margin: 0px;padding: 0px;">Qualified Marks : <s:property value="%{qualifiedMarks}"/></p>
                                                                    </td>-->
                            <div id="queLinks">
                                <div id="quediv">
                                    <p>
                                        <b>Questions</b></p>


                                </div>
                                <%                                    int i = 1;

                                %> <div id="mainQueLinks">
                                    <div id="QueLinks">
                                        <s:iterator value="#session.onlineexamquestionmap">
                                            <!--<tr class="gridRowEven">-->
                                            <%--   <td class="gridColumn" align="left"><a href="javascript:changeStatus(<s:property value="authorId"/>,<s:property value="topicId"/>,'<s:property value="authorLoginId"/>');" ><s:property value="key"/></a></td>--%>


                                            <a href="javascript:getSpecificQuestion(<s:property value="key"/>);" id="q<s:property value="key"/>"><span id="flag<s:property value="key"/>" style="color: red"><s:property value="key" />&nbsp;</span></a>
                                            <%                                                if ((i / 10) >= 1) {
                                                    i = 0;
                                            %>
                                            <br>
                                            <% }
                                                i++;

                                            %>


                                        </s:iterator>
                                    </div>
                                </div>
                            </div>



                            <!-- <button  type="button" class="add_searchButton form-control" value="" style="margin:5px 0px;" onclick="getCustomerDashboardList();"> -->
                            <%--<s:submit type="submit" value="Start" cssClass="add_searchButton form-control"/>--%>



                            <!-- speakers content ends-->

                        </div>
                    </s:form>       
                </td>
            </tr>

        </table>
        <!-- Wrapper -->

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>

</html>

