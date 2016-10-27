/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var url= null;


function newXMLHttpRequest() {
    var xmlreq = false;
    if(window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        try {
            xmlreq = new ActiveXObject("MSxm12.XMLHTTP");
        } catch(e1) {
            try {
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e2) {
                xmlreq = false;
            }
        }
    }
    return xmlreq;
}



function readyStateHandlerXml(req,responseXmlHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error"+req.status+" : "+req.statusText);
            }
        }
    }
}


function getQuestion(questionNumber,answer1,answer2,answer3,answer4,answer5,answer6,navigation,onClickStatus,remainingQuestions,subtopicId,specficQuestion,examId){
    // alert("QuestionId-->"+questionNumber+"-----------------selectedAns--------------->"+selectedAns);
    //alert(remainingQuestions);
    var req = newXMLHttpRequest();
    req.onreadystatechange = readyStateHandlerXml(req, populateQuestions);
    //  url = CONTENXT_PATH+"/getQuestion.action?questionNo="+questionNumber+"&selectedAns="+selectedAns+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId;
    url = CONTENXT_PATH+"/getQuestion.action?questionNo="+questionNumber+"&answer1="+answer1+"&answer2="+answer2+"&answer3="+answer3+"&answer4="+answer4+"&answer5="+answer5+"&answer6="+answer6+"&navigation="+navigation+"&onClickStatus="+onClickStatus+"&remainingQuestions="+remainingQuestions+"&random="+Math.random()+"&subTopicId="+subtopicId+"&specficQuestionNo="+specficQuestion+"&examId="+examId;
    //alert("url----->"+url);

    req.open("GET",url,"true");    
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}



function populateQuestions(resXML) { 
    
    var questDetails = resXML.getElementsByTagName("QUESTIONDETAILS")[0];
     
    /** Xml status  **/
    var questionStatusObj = questDetails.getElementsByTagName("QUESTIONSTATUS");
    var questionStatusId = questionStatusObj[0];
    var qStatus  = questionStatusId.firstChild.nodeValue;  
    // alert("qStatus---->"+qStatus);

    if(qStatus == "true"){
     
        /*For Getting Question Id */
        var questionIdObj = questDetails.getElementsByTagName("QUESTIONID");
        var questionId = questionIdObj[0];
        var id  = questionId.firstChild.nodeValue;
        document.getElementById("questionId").value=id;
        var questionPic=questDetails.getElementsByTagName("QUESTIONPIC");
        var questionPicTag = questionPic[0];
        var isPictorial =questionPicTag.firstChild.nodeValue; 
        /*For Getting Question */
        var questionNameObj = questDetails.getElementsByTagName("QUESTIONNAME");
        
        var questionName = questionNameObj[0];
        var question  = (questionName.firstChild.nodeValue); 
        //alert(question)
        question=question.replace(/</g, "&lt;");
        question=question.replace(/>/g, "&gt;");
        
        //alert(question);
        
        var questionPath = questDetails.getElementsByTagName("QUESTIONPATH");
        var imagePath = questionPath[0];
        var questionImage  = (imagePath.firstChild.nodeValue); 
        if(isPictorial=="1"){
    var oldChild=document.getElementById("questionPath").lastChild;

         var myImage = document.createElement("img");
myImage.src = CONTENXT_PATH+"/renderImage/rImage.action?path="+questionImage+"";
myImage.className="img-responsive";
document.getElementById("questionPath").replaceChild(myImage, oldChild);
document.getElementById("questionPath").style.display="block"; 
}else{
  document.getElementById("questionPath").style.display="none"; 
}
document.getElementById("qname").innerHTML =question;
        /*For Getting Option One */
        var questionType=questDetails.getElementsByTagName("QUESTIONTYPE");
        var questionTag = questionType[0];
        var questionsType =questionTag.firstChild.nodeValue; 
       
        if(questionsType=="M")
        {
            document.getElementById("radio1").type="checkbox"; 
            document.getElementById("radio2").type="checkbox";
            document.getElementById("radio3").type="checkbox"; 
            document.getElementById("radio4").type="checkbox";
            document.getElementById("radio5").type="checkbox"; 
            document.getElementById("radio6").type="checkbox";
        }
        else
        {
            document.getElementById("radio1").type="radio"; 
            document.getElementById("radio2").type="radio";
            document.getElementById("radio3").type="radio"; 
            document.getElementById("radio4").type="radio";
            document.getElementById("radio5").type="radio"; 
            document.getElementById("radio6").type="radio";   
        }      
        var option1TagObj = questDetails.getElementsByTagName("OPTION1");
        var option1Tag = option1TagObj[0];
        var option1  = option1Tag.firstChild.nodeValue; 
        option1=option1.replace(/</g, "&lt;");
        option1=option1.replace(/>/g, "&gt;");
       
        if(option1!="null" ){
            if( option1!="")
            {
                document.getElementById("option1Div").style.display = "block";
                document.getElementById("opt1").innerHTML = option1;   
            }
            else
            {
                document.getElementById("opt1").innerHTML = "";
                document.getElementById("option1Div").style.display = "none";           
            }   
            
        }
        
        else
        {
            document.getElementById("opt1").innerHTML = "";
            document.getElementById("option1Div").style.display = "none"; 
        }
        
        /*For Getting Option Two*/

        var option2TagObj = questDetails.getElementsByTagName("OPTION2");
        var option2Tag = option2TagObj[0];
        var option2  = option2Tag.firstChild.nodeValue;
         option2=option2.replace(/</g, "&lt;");
        option2=option2.replace(/>/g, "&gt;");
        if(option2!="null" )
        {  
            if( option2!="")
            {
                document.getElementById("option2Div").style.display = "block";
                document.getElementById("opt2").innerHTML = option2;   
            }
            else{
                document.getElementById("opt2").innerHTML = "";  
                document.getElementById("option2Div").style.display = "none"; 
            }
                     
            
        }
        else
        {
            document.getElementById("opt2").innerHTML = "";  
            document.getElementById("option2Div").style.display = "none"; 
        }
        
        

        /* For Getting Option three */

        var option3TagObj = questDetails.getElementsByTagName("OPTION3");
        var option3Tag = option3TagObj[0];
        var option3  = option3Tag.firstChild.nodeValue;
         option3=option3.replace(/</g, "&lt;");
        option3=option3.replace(/>/g, "&gt;");
        
        //alert("---->"+option3+"<---");
        if(option3!="null" ){
            if( option3!=""){
                document.getElementById("option3Div").style.display = "block";
                document.getElementById("opt3").innerHTML = option3;
            }else{
                document.getElementById("opt3").innerHTML = "";  
                document.getElementById("option3Div").style.display = "none";    
            }
             
        } else{
            document.getElementById("opt3").innerHTML = "";  
            document.getElementById("option3Div").style.display = "none"; 
        }
          
        /* For Getting Option four */

        var option4TagObj = questDetails.getElementsByTagName("OPTION4");
        var option4Tag = option4TagObj[0];
        var option4  = option4Tag.firstChild.nodeValue;
         option4=option4.replace(/</g, "&lt;");
        option4=option4.replace(/>/g, "&gt;");
        if(option4!="null" ){
            if(option4!=""){
                document.getElementById("option4Div").style.display = "block";
                document.getElementById("opt4").innerHTML = option4;
            }
            else{
                document.getElementById("opt4").innerHTML = "";   
                document.getElementById("option4Div").style.display = "none";   
            }
            
        } else{
            document.getElementById("opt4").innerHTML = "";   
            document.getElementById("option4Div").style.display = "none"; 
        }
       
        /* For Getting Option five */

        var option5TagObj = questDetails.getElementsByTagName("OPTION5");
        var option5Tag = option5TagObj[0];
        var option5  = option5Tag.firstChild.nodeValue;
         option5=option5.replace(/</g, "&lt;");
        option5=option5.replace(/>/g, "&gt;");
        if(option5!="null" ) {
            if( option5!=""){
                document.getElementById("option5Div").style.display = "block";
                
                document.getElementById("opt5").innerHTML = option5;
            }else{
                document.getElementById("opt5").innerHTML = "";  
                document.getElementById("option5Div").style.display = "none"; 
            }
                
               
        }else{
            document.getElementById("opt5").innerHTML = "";  
            document.getElementById("option5Div").style.display = "none"; 
        }
        
        /* For Getting Option six */

        var option6TagObj = questDetails.getElementsByTagName("OPTION6");
        var option6Tag = option6TagObj[0];
        var option6  = option6Tag.firstChild.nodeValue;
         option6=option6.replace(/</g, "&lt;");
        option6=option6.replace(/>/g, "&gt;");
        //alert("option6--->"+option6+"<----")
        if(option6!="null" ){
            if(option6!=""){
                document.getElementById("option6Div").style.display = "block";
                document.getElementById("opt6").innerHTML = option6;
            }else
            {
                document.getElementById("opt6").innerHTML = ""; 
                document.getElementById("option6Div").style.display = "none";        
            }
             
        } else{
            document.getElementById("opt6").innerHTML = ""; 
            document.getElementById("option6Div").style.display = "none"; 
        }
        
        /* For Getting Question Serial Number */

        var MapQIDObj = questDetails.getElementsByTagName("MAPQUESTIONID");
        var MqTag = MapQIDObj[0];
        var mapQId  = MqTag.firstChild.nodeValue;
        document.getElementById("ques").innerHTML = mapQId;
        document.getElementById("questionId").value = mapQId;
        
        //-----------------------------------------
        var SubtopicIDObj = questDetails.getElementsByTagName("SUBTOPICID");
        var subtopicTag = SubtopicIDObj[0];
        var subtopicId1  = subtopicTag.firstChild.nodeValue;
        //document.getElementById("ques").innerHTML = mapQId;
        document.getElementById("subtopicId").value = subtopicId1;

       


        //---------------------------------------




        /*For Getting Answer */

        var empAnsTagObj1 = questDetails.getElementsByTagName("EMPANSWER1");
        var empAnsTag1 = empAnsTagObj1[0];
        var empAns1  = empAnsTag1.firstChild.nodeValue;
        
        var empAnsTagObj2 = questDetails.getElementsByTagName("EMPANSWER2");
        var empAnsTag2 = empAnsTagObj2[0];
        var empAns2  = empAnsTag2.firstChild.nodeValue;
        
        var empAnsTagObj3 = questDetails.getElementsByTagName("EMPANSWER3");
        var empAnsTag3 = empAnsTagObj3[0];
        var empAns3  = empAnsTag3.firstChild.nodeValue;
        
        var empAnsTagObj4 = questDetails.getElementsByTagName("EMPANSWER4");
        var empAnsTag4 = empAnsTagObj4[0];
        var empAns4  = empAnsTag4.firstChild.nodeValue;
        
        var empAnsTagObj5 = questDetails.getElementsByTagName("EMPANSWER5");
        var empAnsTag5 = empAnsTagObj5[0];
        var empAns5  = empAnsTag5.firstChild.nodeValue;
        
        var empAnsTagObj6 = questDetails.getElementsByTagName("EMPANSWER6");
        var empAnsTag6 = empAnsTagObj6[0];
        var empAns6  = empAnsTag6.firstChild.nodeValue;
       // empAns=empAns-1;

        /* Remaining Questions Count */
        var remainigQuesTagObj = questDetails.getElementsByTagName("REMAININGQUESTIONS");
        var remainigQuesTag = remainigQuesTagObj[0];
        var remainigQues  = remainigQuesTag.firstChild.nodeValue;
        document.getElementById("remainingQuestions").innerHTML = remainigQues;
        document.getElementById("hideremainingQuestions").value = remainigQues;
        
        /*Subtopic name*/
        var sectionTagObj = questDetails.getElementsByTagName("SECTION");
        var sectionTag = sectionTagObj[0];
        var section  = sectionTag.firstChild.nodeValue;
        //alert(section)
        document.getElementById("sectionName").innerHTML = section;
        
        
        
        var nextObj = document.getElementById("next");
        if(document.getElementById("totalQuest").value == mapQId){
            nextObj.style.display = 'none';
        }else {
            nextObj.style.display = '';
        }

        var previousObj = document.getElementById("previous");
        if(mapQId == 1){
            previousObj.style.display = 'none';
        }else {
            previousObj.style.display = '';
        }
        var radiooptions = document.getElementsByName('option');
           
        for (var i = 0, length = radiooptions.length; i < length; i++){
            radiooptions[i].checked = false;
        }
        if(empAns1 == 1){
            radiooptions[0].checked = true;
        }
        if(empAns2 == 1){
            radiooptions[1].checked = true;
        }
        if(empAns3 == 1){
            radiooptions[2].checked = true;
        }
        if(empAns4 == 1){
            radiooptions[3].checked = true;
        }
        if(empAns5 == 1){
            radiooptions[4].checked = true;
        }
        if(empAns6 == 1){
            radiooptions[5].checked = true;
        }
    }else{
        /* Remaining Questions Count */
        var remainigQuesTagObj = questDetails.getElementsByTagName("REMAININGQUESTIONS");
        var remainigQuesTag = remainigQuesTagObj[0];
        var remainigQues  = remainigQuesTag.firstChild.nodeValue;
        document.getElementById("remainingQuestions").innerHTML = remainigQues;
        document.getElementById("hideremainingQuestions").value = remainigQues;

        document.forms["examinationForm"].submit();
    }
//   alert("URL1111111--->"+url);  
                
}


function skillValues(id){
   var editskill=$('#editskill').val();
   if(editskill==undefined){
       editskill =0;
   }
    if(id=='T')
        {
            flag=1;
        }else{
            
            flag=0;
        }
     var url='getSkillValuesOnChange.action?examTypeFlag='+flag;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {   
            //  alert("req.responseText"+req.responseText);
                populateSkillsOnChange(req.responseText);
                if(editskill!=0){
                document.getElementById('skillCategoryValue').value=editskill;
                }
                //document.getElementById("outputMessage").innerHTML=req.responseText; 
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
    
}

function populateSkillsOnChange(data){
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#skillCategoryValue');
    var flag1=$('#search').val();
    //alert("flag1"+flag1);
    $select.find('option').remove();
    if(flag1=="search"){
    $('<option>').val(-1).text('All').appendTo($select);
     for(var i=0;i<addList.length;i++){        
        var Values=addList[i].split("|");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
    }else{
    for(var i=0;i<addList.length;i++){        
        var Values=addList[i].split("|");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
    }
}
function submitQuestionData()
{
    
  
    var option1=$("#option1").val();  
    var option2=$("#option2").val();  
    var option3=$("#option3").val(); 
    var option4=$("#option4").val(); 
    var option5=$("#option5").val(); 
    var option6=$("#option6").val(); 
    var ansType=$("#answerType").val(); 
    var optionCount=0;
    var ansCount=0;
    
   
                
    
    if(option1!="")
    {
                    
        optionCount=1; 
    }
    if(option2!="")
    {
                    
        optionCount=2; 
    }
    if(option3!="")
    {
                    
        optionCount=3; 
    }
    if(option4!="")
    {
        optionCount=4; 
    }
    if(option5!="")
    {
        optionCount=5; 
    }
    if(option6!="")
    {
        optionCount=6; 
    }
    
    if(ansType=='S'){
        var ans1=$('input:radio[name=answer ]:checked').val();
       
        if(ans1 == "Answer1")
        {
            ansCount=1; 
        }
        if(ans1 == "Answer2")
        {
            ansCount=2; 
        }
        if(ans1 == "Answer3")
        {
            ansCount=3; 
        }
        if(ans1 == "Answer4")
        {
            ansCount=4; 
        }
        if(ans1 == "Answer5")
        {
            ansCount=5; 
        }
        if(ans1 == "Answer6")
        {
            ansCount=6; 
        }
      
        if(ansCount>optionCount)
        {
            $("#formValidationMsg").html("<font color='red'>Please select Proper answers for given options </font>");
                 
            return false;     
        }
        else
        {
            return true;       
        }
    }
    else
    {
        var ans1=document.getElementById('ans1'); 
        var ans2=document.getElementById('ans2'); 
        var ans3=document.getElementById('ans3'); 
        var ans4=document.getElementById('ans4'); 
        var ans5=document.getElementById('ans5'); 
        var ans6=document.getElementById('ans6'); 
        if(ans1.checked == true)
        {
            ansCount=1; 
        }
        if(ans2.checked == true)
        {
            ansCount=2; 
        }
        if(ans3.checked == true)
        {
            ansCount=3; 
        }
        if(ans4.checked == true)
        {
            ansCount=4; 
        }
        if(ans5.checked == true)
        {
            ansCount=5; 
        }
        if(ans6.checked == true)
        {
            ansCount=6; 
        }
       
        if(ansCount>optionCount)
        {
            $("#formValidationMsg").html("<font color='red'>Please select Proper answers for given options </font>");
                 
            return false;     
        }
         else
        {
            return true;       
        }
    }
}