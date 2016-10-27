<%--
    Document   : addOrEditQuestions
    Created on : Apr 12, 2015, 7:05:25 PM
    Author     : Tirumala Teja
--%>

<%@page import="com.mss.msp.usersdata.UserVTO"%>
<%@page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>
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
        <title>ServicesBay :: AddOrEditQuestions</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value='/includes/css/accountDetails/details.css'/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/sweetalert.css"/>">



        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/account/formVerification.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script language="JavaScript" type="text/javascript" src="<s:url value="/includes/js/general/ProfilePage.js"/>" ></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/onlineexam/onlineexamAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/sweetalert.min.js"/>"></script>
        <script>
            function checkanswerType(){
              
                var answerType=document.getElementById('answerType').value;
                var quesId=$("#quesId").val();
                
             
                var answer=document.getElementsByName('answer');
                var ans_value;
              
                    
                var ans1=document.getElementById('ans1'); 
                var ans2=document.getElementById('ans2'); 
                var ans3=document.getElementById('ans3'); 
                var ans4=document.getElementById('ans4'); 
                var ans5=document.getElementById('ans5'); 
                var ans6=document.getElementById('ans6'); 
                
                
                if(answerType=='S')
                {
                  
                    document.getElementById('answer1').style.display = "none";
                    document.getElementById('answer').style.display = "block"; 
                    if(quesId>0){
                     
                    
                        ans1.checked = false;
                        ans2.checked = false;
                        ans3.checked = false;
                        ans4.checked = false;
                        ans5.checked = false;
                        ans6.checked = false;
                    }
                    
                }else{
                  
                    document.getElementById('answer1').style.display = "block"; 
                    document.getElementById('answer').style.display = "none";
                    if(quesId>0){
                      
                    
                        for(var i = 0; i < answer.length; i++){
                       
                            answer[i].checked = undefined;
                        
                        }
                    }
            }
        }
            
        function checkValidationsForAddQues(){
              
                
               
               
            var quesId=$("#quesId").val();
            var question=$("#question").val();
            var showImg=$("#showImg").val();
            var oldPath=$("#oldPath").val();
              
                
            var isPic=document.getElementById('isPic');
            var option1=$("#option1").val();  
            var option2=$("#option2").val();  
            var answer=$("#answer").val(); 
            var ans1=document.getElementById('ans1'); 
            var ans2=document.getElementById('ans2'); 
            var ans3=document.getElementById('ans3'); 
            var ans4=document.getElementById('ans4'); 
            var ans5=document.getElementById('ans5'); 
            var ans6=document.getElementById('ans6'); 
            var answer=document.getElementsByName('answer');
                 
            var ans_value;
              
            for(var i = 0; i < answer.length; i++){
                if(answer[i].checked){
                    ans_value = answer[i].value;
                }
            }
               
            if(question.trim()==="")
            {
                $("#formValidationMsg").html("<font color='red'>Please enter the question</font>");
                $("#question").css('border','1px solid red');
                return false; 
            } else
            {
                $("formValidationMsg").html(" ");
                $("#question").css("border", "1px solid green");
                  
            }
                
                
            if(isPic.checked == true && (quesId == 0 || oldPath=="")){
                
                if (showImg == ""  ) {
                    $("#formValidationMsg").html("<font color='red'>please upload Image related to the Question</font>");
                    $("#showImg").css('border','1px solid red');
                    return false;
                } else {
                    $("formValidationMsg").html(" ");
                    $("#showImg").css("border", "1px solid green");
                }
            }
                
            if(showImg!="" && isPic.checked == true)
            {
                var Extension = showImg.substring(
                showImg.lastIndexOf('.') + 1).toLowerCase();
                if (Extension == "gif" || Extension == "png" || Extension == "bmp"
                    || Extension == "jpeg" || Extension == "jpg") {
                    var fileSize = document.getElementById("showImg").files[0];
                    var sizeInMb = (fileSize.size/1024)/1024;
                     
                    var sizeLimit= 2;
                    if (sizeInMb > sizeLimit) {
                        $("#formValidationMsg").html("<font color='red'>File size must be less than 2MB.</font>");
                        return false
                    }
                } 
                else {
                    $("#formValidationMsg").html("<font color='red'>please upload file types of GIF, PNG, JPG, JPEG and BMP.</font>");
                    return false;
                }  
            }
   
            if(option1=="")
            {
                $("#formValidationMsg").html("<font color='red'>Option1 is mandatory</font>");
                $("#option1").css('border','1px solid red');
                return false; 
            }else
            {
                $("formValidationMsg").html(" ");
                $("#option1").css("border", "1px solid green");
                   
            }
            if(option2=="")
            {
                $("#formValidationMsg").html("<font color='red'>Option2 is mandatory</font>");
                $("#option2").css('border','1px solid red');
                return false; 
            }else
            {
                $("formValidationMsg").html(" ");
                $("#option2").css("border", "1px solid green");
                   
            }
                
            if((ans_value != undefined) || (ans1.checked == true)||(ans2.checked == true) || (ans3.checked == true) || (ans4.checked == true) || (ans5.checked == true) || (ans6.checked == true)){
                   
                $("formValidationMsg").html(" ");
                  
            }else{
                  
                $("#formValidationMsg").html("<font color='red'>please select atleast one answer</font>");
                 
                return false; 
            }
               
                
        }
            
        function showBrowse(){
            var isPic=document.getElementById('isPic');
            if(isPic.checked == true){
                browsePicture.style.display='block';
            }
            else
            {
                browsePicture.style.display='none';
            }
        }
            
        function readURL(input) {
              
            if (input.files && input.files[0]) {
                var reader = new FileReader();
            
                reader.onload = function (e) {
                    $('#selectedImg').attr('src', e.target.result);
                }
            
                reader.readAsDataURL(input.files[0]);
            }
        }
    
        $("#showImg").change(function(){
            readURL(this);
        });
        function clearImage(){
            document.getElementById('isPic').checked=false;
            var isPic=document.getElementById('isPic')
            if(isPic.checked == true){
                    
                document.getElementById('browsePicture').style.display='block';
            }
            else
            {
                   
                document.getElementById('browsePicture').style.display='none';
            }
            document.getElementById('selectedImg').src="";
                
               
        }
            
            
        </script>



    </head>

    <body oncontextmenu="return false" onload="checkanswerType();showBrowse();skillValues(examType.value);">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/>
                    </div>
                </div>

            </header>
            <div id="main">
                <div class="container">
                    <div class="row">
                        <!-- Main Content-->
                        <s:include value="/includes/menu/LeftMenu.jsp"/>
                        <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="">
                            <!-- Add Form Area -->
                            <div class="col-sm-12">

                                <div class="" id="profileBox" style="float: left; margin-top: 15px; margin-bottom: 20px">
                                    <!-- Add Form Header-->
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                                <s:if test="quesId!=0">
                                                    <font color="#ffffff">Edit&nbsp;Exam&nbsp;Questions</font>

                                                </s:if>
                                                <s:else>
                                                    <font color="#ffffff">Add&nbsp;Exam&nbsp;Questions</font>

                                                </s:else>
                                               
                                                <s:url var="myUrl" action="storeAddOrEditExamQues.action">
                                                    <s:param name="quesId"><s:property value="quesId"/></s:param> 
                                                </s:url>

                                               

                                                <span class="pull-right"><a id="addOrEdirQuestionBackButton" href="../Ques/getQuestionsList.action"><i class="fa fa-undo"></i></a></span>
                                            </h4>
                                        </div>
                                    </div>
                                    <!-- Add Form-->
                                    <div class="col-sm-12" style="margin-bottom: 20px">
                                        <br>

                                        <s:form action="storeAddOrEditExamQues" id="addEditForm" method="post"  enctype="multipart/form-data" theme="simple" onsubmit="return submitQuestionData()">
                                            <span id="resume"><font style='color:green;font-size:15px;'><s:property value="successMsg"/></font></span>
                                            <div class="col-sm-12">
                                                <span id="formValidationMsg"></span>
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <label class="" style="color:#56a5ec;">ExamType </label>

                                                        <s:select id="examType" cssClass="form-control SelectBoxStyles" name="examType"  list="#@java.util.LinkedHashMap@{'T':'Technical','S':'Psychometric'}" value="%{editQues.examType}" onchange="return skillValues(this.value);" tabindex="1" />
                                                    </div>
                                                    <s:hidden id="editskill" value="%{editQues.skillCategoryValue1}"/>

                                                    <div class="col-sm-3">
                                                        <label class="" style="color:#56a5ec;">Skill </label>

                                                        <s:select id="skillCategoryValue" cssClass="form-control SelectBoxStyles" name="skillCategoryValue"  list="skillValuesMap" value="%{editQues.skillCategoryValue1}" tabindex="2" />
                                                    </div>

                                                    <div class="col-sm-3">
                                                        <label class="" style="color:#56a5ec;">Level </label>

                                                        <s:select id="level" cssClass="form-control SelectBoxStyles" name="level" value="%{editQues.level}" list="#@java.util.LinkedHashMap@{'L':'Low','M':'Medium','H':'High'}"  tabindex="3" 
                                                                  />
                                                    </div>

                                                    <div class="col-sm-3">
                                                        <label class="" style="color:#56a5ec;">Status </label>

                                                        <s:select id="status" cssClass="form-control SelectBoxStyles" name="status"  list="#@java.util.LinkedHashMap@{'Active':'Active','In-Active':'In-Active'}" value="%{editQues.status}" tabindex="4" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <label class="" style="color:#56a5ec;">Answer&nbsp;Type </label>
                                                        <s:hidden name="quesId" id="quesId" value="%{quesId}"/>
                                                        <s:hidden id="successMsg" name="successMsg" value="%{successMsg}"/>
                                                        <s:select id="answerType" cssClass="form-control SelectBoxStyles" name="answerType" value="%{editQues.answerType}" list="#@java.util.LinkedHashMap@{'S':'Single','M':'Multiple'}" onchange="checkanswerType();" tabindex="5"/>
                                                    </div> 


                                                    <div class="col-sm-3 form-group" style="margin-top: 2vw;">

                                                        <label class="checkbox-inline"> Is&nbsp;Pictorial<s:checkbox cssClass="form-group"   name="isPic" id="isPic" value="%{editQues.isPic}" onclick="showBrowse();" tabindex="6" /> </label>

                                                    </div>
                                                </div></div>


                                            <div class="col-sm-12 required">
                                                <div class="row">
                                                    <div class="col-sm-12" id="questionTextArea">
                                                        <span>
                                                            <label class="labelStyle2">Question</label>
                                                            <s:textarea name="question" id="question" placeholder="Question" rows="8" value="%{editQues.question}" cssClass="form-control" tabindex="7" maxLength="5000" /> 
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <s:url id="image" action="rImage" namespace="/renderImage">
                                                        <s:param name="path" value="editQues.question_path"></s:param>
                                                    </s:url>
                                                    <s:hidden name="oldPath" id="oldPath" value="%{editQues.question_path}"/>
                                                    <div class="col-lg-12 required" id="browsePicture" style="display:none;">
                                                        <label class="labelStyle2">Image&nbsp;related&nbsp;to&nbsp;above&nbsp;Question</label>
                                                        <span>
                                                            <s:if test='editQues.question_path != null'>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;<img id="editImg" class="img-responsive" src='<s:property value="#image"/>' height="50%" width="50%"/></s:if>
                                                            <s:file name="quesImage"  label="Upload File"  id="showImg" onchange="readURL(this)" tabindex="8" ></s:file>
                                                                &nbsp;&nbsp;<img id="selectedImg" src="#" alt="selected image:" height="50%" width="50%"/>
                                                            </span>
                                                        </div>

                                                    </div>
                                                </div>


                                                <div class="col-sm-12">
                                                    <div class="row">
                                                        <div class="col-sm-6 required">
                                                            <span>
                                                                <label class="labelStyle2">Option1</label>
                                                            <s:textarea name="option1" id="option1" placeholder="Option1" rows="8" cssClass="form-control " value="%{editQues.option1}" tabindex="9" maxLength="5000" /> 
                                                        </span>
                                                        <br />
                                                    </div>
                                                    <div class="col-sm-6 required">
                                                        <span>

                                                            <label class="labelStyle2">Option2 </label>
                                                            <s:textarea name="option2" id="option2" placeholder="Option2" rows="8" cssClass="form-control" value="%{editQues.option2}" tabindex="10" maxLength="5000"/> 

                                                        </span>
                                                    </div>


                                                    <br/>
                                                </div></div>




                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <span>
                                                            <label class="labelStyle2">Option3</label>
                                                            <s:textarea name="option3" id="option3" placeholder="Option3"  rows="8" cssClass="form-control " value="%{editQues.option3}" tabindex="11" maxLength="5000"/> 
                                                            <span id="accountTypeValidation" class="accDetailsError"></span>
                                                        </span>
                                                    </div>

                                                    <div class="col-sm-6">
                                                        <span>
                                                            <label class="labelStyle2">option4</label>
                                                            <s:textarea name="option4" id="option4" placeholder="Option4" rows="8" cssClass="form-control" value="%{editQues.option4}" tabindex="12" maxLength="5000"/> 
                                                        </span></div>

                                                </div></div>

                                            <div class="col-sm-12">         
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <span>
                                                            <label class="labelStyle2"> option5 </label>
                                                            <s:textarea name="option5" id="option5" placeholder="Option5" rows="8" cssClass="form-control" value="%{editQues.option5}" tabindex="13" maxLength="5000"/> 
                                                        </span></div>

                                                    <div class="col-sm-6">
                                                        <span>
                                                            <label class="labelStyle2"> option6 </label>
                                                            <s:textarea name="option6" id="option6" placeholder="Option6" rows="8" cssClass="form-control" value="%{editQues.option6}" tabindex="14" maxLength="5000"/> 
                                                        </span>

                                                    </div>
                                                </div>
                                            </div>


                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <span>
                                                        <div class="col-sm-12 required">
                                                            <label class="labelStyle2"> Answers </label>
                                                        </div>
                                                        <div id="answer" style="disply:none">
                                                            <s:radio cssClass="" name="answer" id="answer" value="%{editQues.answer}" list="{'Answer1','Answer2','Answer3','Answer4','Answer5','Answer6'}" cssStyle="width: 50px;" tabindex="15" />
                                                        </div>

                                                    



                                                        <div id="answer1" class="form-group" style="display: none"> 
                                                            <label class="checkbox-inline"> Answer1 <s:checkbox cssClass=""  name="ans1" id="ans1" value="%{editQues.ans1}" tabindex="16" /></label>
                                                            <label class="checkbox-inline"> Answer2 <s:checkbox cssClass=""  name="ans2" id="ans2" value="%{editQues.ans2}" tabindex="17" /></label>
                                                            <label class="checkbox-inline"> Answer3 <s:checkbox cssClass=""  name="ans3" id="ans3" value="%{editQues.ans3}" tabindex="18" /></label>
                                                            <label class="checkbox-inline"> Answer4 <s:checkbox cssClass=""  name="ans4" id="ans4" value="%{editQues.ans4}" tabindex="19" /></label>
                                                            <label class="checkbox-inline"> Answer5 <s:checkbox cssClass=""  name="ans5" id="ans5" value="%{editQues.ans5}" tabindex="20" /></label>
                                                            <label class="checkbox-inline"> Answer6 <s:checkbox cssClass=""  name="ans6" id="ans6" value="%{editQues.ans6}" tabindex="21" /></label>
                                                        </div>



                                                    </span> 
                                                </div>
                                            </div>

                                            <div class="col-sm-12">         
                                                <div class="row">
                                                    <div class="col-sm-12 ">
                                                        <span>
                                                            <label class="labelStyle2">Explanation</label>
                                                            <s:textarea name="explanation" id="explanation" placeholder="Explanation" rows="8" cssClass="form-control" value="%{editQues.explanation}" tabindex="22" maxLength="6000"/> 

                                                        </span>
                                                    </div>
                                                </div>
                                            </div>



                                            <div class="col-sm-12 ">
                                              
                                                <div class="col-sm-2 pull-right">
                                                 
                                                    <s:reset  type="button" id="resetQuestion" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control fa fa-eraser" key="reset" value="Clear" onclick="clearImage();" tabindex="23" />
                                                </div>
                                                <div class="col-sm-2 pull-right">
                                                    <s:reset  type="button" id="cancelQuestion"  cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control fa fa-times" onclick="javascript:history.back();" value="Cancel" tabindex="24" />
                                                </div>
                                                <div class="col-sm-2 pull-right">
                                                    <s:submit  type="button" id="addEditSubmit" cssStyle="margin:5px 0px;" cssClass="add_searchButton form-control" value="" onclick="return checkValidationsForAddQues();" tabindex="25" ><i class="fa fa-floppy-o"></i>&nbsp;Save</s:submit>
                                                    </div>
                                                </div>

                                            <div class="row">
                                                <p id="errorMessage" align="center" class="accDetailsError" >
                                                    <s:property value="resultMessage"  />
                                                </p></div>
                                            </s:form>
                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
