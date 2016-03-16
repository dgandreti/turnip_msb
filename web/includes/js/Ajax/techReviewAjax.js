
//==========================================================================================================================

var techReviewDate,reviewAlertDate,reviewStartDate,reviewEndDate,searchInterviewDate;
function doOnLoad() {  
    $('#techAlertContent').hide();
    $('#techReviewAlert').change(function(){
        if($(this).is(":checked"))
            $('#techAlertContent').fadeIn('slow');
        else
            $('#techAlertContent').fadeOut('slow');
    });
    
    
    techReviewDate = new dhtmlXCalendarObject(["interviewDate"]);
    // alert("hii1");
    techReviewDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    techReviewDate.setDateFormat("%m-%d-%Y %H:%i ");
   
    var today=new Date();
    techReviewDate.setSensitiveRange(today, null);
    
    
    searchInterviewDate = new dhtmlXCalendarObject(["searchInterviewDate"]);
    // alert("hii1");
    searchInterviewDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    searchInterviewDate.setDateFormat("%m-%d-%Y");
  
    
    reviewAlertDate = new dhtmlXCalendarObject(["reviewAlertDate"]);
    // alert("hii1");
    reviewAlertDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    reviewAlertDate.setDateFormat("%Y/%m/%d %H:%i");
    var todayDate=new Date();
    reviewAlertDate.setSensitiveRange(todayDate, null);
    
    
    
    reviewStartDate = new dhtmlXCalendarObject(["reviewStartDate"]);
    // alert("hii1");
    reviewStartDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    reviewStartDate.setDateFormat("%m-%d-%Y");
    reviewStartDate.hideTime();
    
    reviewEndDate = new dhtmlXCalendarObject(["reviewEndDate"]);
    // alert("hii1");
    reviewEndDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    reviewEndDate.setDateFormat("%m-%d-%Y");
    reviewEndDate.hideTime();

    //default date
    
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth(); //January is 0!
    var yyyy = today.getFullYear();
    //alert(fromDate)
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
    mm=today.getMonth()+1;
    if(mm<10) {
        mm='0'+mm
    } 
    var dd=today.getDate();
    var dateValue=yyyy+'/'+mm+'/'+dd;
    
    //document.getElementById("reviewStartDate").value=dateValue;
    document.getElementById("loadingTechReviewSearch").style.display="none";
    
}
function enterDateRepository()
{
    document.getElementById('interviewDate').value = "";
    document.getElementById('reviewAlertDate').value = "";

    alert("Please select from the Calender !");
    return false;
}

function forwardReviewToCustomer(){
    initSessionTimer();
    //alert("came")
    var flag=true;
    var techieName=$("#eNameTechReview").val();
    var reviewAlertDate=new Date($("#reviewAlertDate").val());
    var interviewDate=new Date($("#interviewDate").val());
    var interviewType=$("#interviewType").val();
    var interviewLocation=$("#interviewLocation").val();
    var iDate=$("#interviewDate").val();
    var interviewTechieDate = iDate.split(/[- :]/);
    var consultantDate = new Date(interviewTechieDate[2], interviewTechieDate[0]-1 , interviewTechieDate[1], interviewTechieDate[3],interviewTechieDate[4]);
    var todayDate= new Date();
    //alert(interviewType)
    //alert(interviewType)
    if(interviewType!="Online" && interviewType!="Psychometric")
    {
    
        if($("#eNameTechReview").val()=="" ){
            $("#eNameTechReview").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>please enter Techie Name</font></b>");
            flag=false;
            return false;
        }
        else{
            $("e").html("");
            $("#interviewDate").css("border", "1px solid green");
        }
    
        if($("#interviewDate").val()=="" ){
            $("#interviewDate").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>please enter Interview Date</font></b>");
            flag=false;
            return false;
        }
        else{
            $("e").html("");
            $("#interviewDate").css("border", "1px solid green");
        }
    
        if(consultantDate<todayDate){
            $("e").html(" <b><font color='red'>Interview time must be greater than  current time!</font></b>");
            flag=false;
            return false;
        }
        else{
            $("e").html("");
        }
        if(interviewType=="Face to Face")
        {
            if(interviewLocation=="")      
            {
                $("e").html(" <b><font color='red'>Please Enter Location!</font></b>");
                flag=false;
                return false;      
            }
        }
        responseForTechReview(interviewType,flag);
    }
    else
    {
          
       
        var examMints=$('#techReviewMints').val();
        var examTime=$('#techReviewTime').val();
        if($("#eNameTechReview").val()=="" ){
            //$("#eNameTechReview").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>please enter Techie Name</font></b>");
            
            return false;
        }
        if(examTime==0 && examMints==0)
        {
            //  alert("Please Select The Time"); 
            $("e").html(" <b><font color='red'>Please Select The Time</font></b>");
           // $("e").show().delay(4000).fadeOut();
            return false;
        }
         else{
            $("e").html("");
        }
        if(interviewType=="Online"){
            var skillCategoryArry = [];    
            $("#skillCategoryValue :selected").each(function(){
                skillCategoryArry.push($(this).val()); 
            });
      
            if($("#skillCategoryValue :selected").length==0)
            {
                //  alert("Please Select The skills"); 
                $("e").html(" <b><font color= #FF4D4D>Please Select The skills</font></b>.");
                //$("e").show().delay(4000).fadeOut();
                //  $("#consult_preferredState").val(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
                //$("#skillCategoryValue").css("border","1px solid red");
           
                return false;   
            }
             else{
            $("e").html("");
             }
        
        
            if($("#skillCategoryValue :selected").length>10) 
            {
                // alert("Skills should not be selected more than 10");
                $("e").html("<b><font color='red'>Skills should not be selected more than 10</font></b>");
               // $("e").show().delay(4000).fadeOut();
                // $("e").html(" <b><font color= #FF4D4D>Skills should not be selected more than 10</font></b>.");
                //  $("#consult_preferredState").val(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
                // $("#skillCategoryValue").css("border","1px solid red");
           
                return false;
            }  
             else{
            $("e").html("");
                 }
        }else
        {
            var skillCategoryArry = [];    
            $("#psychoskillCategoryValue :selected").each(function(){
                skillCategoryArry.push($(this).val()); 
            });
      
            if($("#psychoskillCategoryValue :selected").length==0)
            {
                //  alert("Please Select The skills"); 
                $("e").html(" <b><font color= #FF4D4D>Please Select The skills</font></b>.");
                //$("e").show().delay(4000).fadeOut();
                //  $("#consult_preferredState").val(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
                //$("#skillCategoryValue").css("border","1px solid red");
           
                return false;   
            }
             else{
            $("e").html("");
                 }
        
            if($("#psychoskillCategoryValue :selected").length>10) 
            {
                // alert("Skills should not be selected more than 10");
                $("e").html("<b><font color='red'>Skills should not be selected more than 10</font></b>");
                //$("e").show().delay(4000).fadeOut();
                // $("e").html(" <b><font color= #FF4D4D>Skills should not be selected more than 10</font></b>.");
                //  $("#consult_preferredState").val(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
                // $("#skillCategoryValue").css("border","1px solid red");
           
                return false;
            }
             else{
            $("e").html("");
        }
        }
        var techieQuestions=$("#techReviewQuestions").val();  
        if(techieQuestions<1)
        {
            // alert("You Must Give Questions");  
            $("e").html(" <b><font color='red'>Please Give No Of Questions</font></b>");
           // $("e").show().delay(4000).fadeOut();
            return false;
        }
         else{
            $("e").html("");
        }
        //alert(techieQuestions)
        //        if(techieQuestions=="")
        //        {
        //            
        //            alert("You Must Give Questions");  
        //            $("e").html(" <b><font color='red'>Please Enter number of Questions</font></b>");
        //            $("e").show().delay(4000).fadeOut();
        //            return false;
        //        }
        
        //techReviewQuestions
        //    else{
        //        $("#consult_preferredState :selected").each(function(){
        //            userCatArry.push($(this).val()); 
        //        });
        //        $("#PrefstateValues").val(userCatArry);
        //    }

      
        responseForTechReview(interviewType,flag);
        
    }
//    alert("responseForTechReview-->"+flag)
//    return flag;

}

function responseForTechReview(interviewType,flag){
    //alert(interviewType)
    var checked;
    if(interviewType=='Face to Face'){
        if(interviewLocation==""){
            $("#interviewLocation").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>Location should not be empty for Face to Face interview</font></b>"); 
            flag=false;
        }
        else{
            $("e").html("");
        }    
    }
    if($('#techReviewAlert').prop('checked')){
        checked ="checked";
        if($("#reviewAlertDate").val()=="" ){
            $("e").html(" <b><font color='red'>Alert Date is Mandatory</font></b>");
            flag=false;
            //alert("Empty reviewAlertDate")
            return false;
        } 
        else{
            $("e").html("");
        }  
        if(interviewDate<reviewAlertDate){
            $("e").html(" <b><font color='red'>Alert Date must be less than Interview Date</font></b>");
            flag=false;
        }
        else{
            $("e").html("");
        }
         var alertHour =document.getElementById("techReviewAlertHours").value ;
        var alertMints= document.getElementById("techReviewAlertMints").value;
     
        var dateTime = new Date();
        var hours = dateTime.getHours();
     
        var mints = dateTime.getMinutes();
    
        var dd1 = dateTime.getDate();
               var mm1 = dateTime.getMonth()+1; //January is 0!
               var yyyy1 = dateTime.getFullYear();
               if(mm1<10)
        {
            mm1 = "0"+mm1;   
        }
        if(dd1<10)
        {
            dd1 = "0"+dd1;   
        }
        var startDate =   mm1+"-"+dd1+"-"+yyyy1;
        var endDate=  document.getElementById("reviewAlertDate").value;
         var splitTaskStartDate = startDate.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = endDate.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    //alert("diff"+difference)
    if(difference==0)
    {
        //alert("alertHour--"+alertHour+"<---hours"+hours)
        if(alertHour > hours)
            {
               
                flag=true;  
            }
            else if(alertHour == hours)
                {
                   if(alertMints > mints)
                    {
                        // alert("true")
                         $("e").html("");
                      flag=true;  
                    }
                    else
                        {
                            // alert("false")
                             $("e").html(" <b><font color='red'>Alert Date must be greater than Current Date and Time</font></b>");
                               flag=false;  
                        }  
                }
            else
                {
                   // alert("not checked") 
                        $("e").html(" <b><font color='red'>Alert Date must be greater than Current Date and Time</font></b>");
                               flag=false;  
                }
    }
    }
    //alert("flag is"+flag)
    if(flag){
     
        swal({
    
            title: "Are You Sure to Forward Techreview ?",
  
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
                //  var flag=true;
                // alert(forwardReviewToCustomer(flag));
                var requirementId=$('#requirementId').val();
                var consult_id=$('#consult_id').val();
                var interview=$('#interview').val();
                var interviewType=$('#interviewType').val();
                var empIdTechReview=$('#empIdTechReview').val();
                var empIdTechReview2=$('#empIdTechReview2').val();
                var contechNote =$('#interviewNotes').val();
                //alert(empIdTechReview)
                //alert(empIdTechReview2)
                var alertHours="",alertMints="";
                if($('#techReviewAlertHours').val()!="")
                {
                    alertHours = $('#techReviewAlertHours').val();     
                }
                
                if($('#techReviewAlertMints').val()!="")
                {
                    alertMints = $('#techReviewAlertMints').val();     
                }
                var interviewDate=$('#interviewDate').val();
                var reviewAlertDate=$('#reviewAlertDate').val()+" "+alertHours+":"+alertMints;
                var alertMessageTechReview=$('#alertMessageTechReview').val();
                var timeZone=$('#timeZone').val();
                var interviewLocation=$('#interviewLocation').val();
                var techReviewQuestions=$('#techReviewQuestions').val();
                var techReviewComments=$('#interviewComments').val();
                var techReviewSeverity=$('#techReviewSeverity').val();
                var techReviewTime =$('#techReviewTime').val()+":"+$('#techReviewMints').val();
                var skillCategoryArry = [];    
                $("#skillCategoryValue :selected").each(function(){
                    skillCategoryArry.push($(this).val()); 
                });
        
       
                if($('#skillid0').val()!="")
                {
                    if($('#skill0').val()!="")
                    {
                        var skill1Questions=$('#skillid0').val()+"-"+$('#skill0').val();        
                    }
           
                    else
                    {
                        var skill1Questions=$('#skillid0').val()+"-"+0;       
                    }
              
               
                }
                else
                {
                    var skill1Questions="";  
                }
                if($('#skillid1').val()!="")
                {
          
                    if($('#skill1').val()!="")
                    {
                        var skill2Questions=$('#skillid1').val()+"-"+$('#skill1').val();        
                    }
           
                    else
                    {
                        var skill2Questions=$('#skillid1').val()+"-"+0;       
                    }
                }
                else
                {
                    var skill2Questions="";  
                }
                if($('#skillid2').val()!="")
                {
                    if($('#skill2').val()!="")
                    {
                        var skill3Questions=$('#skillid2').val()+"-"+$('#skill2').val();        
                    }
           
                    else
                    {
                        var skill3Questions=$('#skillid2').val()+"-"+0;       
                    }
                }
                else
                {
                    var skill3Questions="";  
                }
                if($('#skillid3').val()!="")
                {
                    if($('#skill3').val()!="")
                    {
                        var skill4Questions=$('#skillid3').val()+"-"+$('#skill3').val();        
                    }
           
                    else
                    {
                        var skill4Questions=$('#skillid3').val()+"-"+0;       
                    } 
                }
                else
                {
                    var skill4Questions="";  
                }
                if($('#skillid4').val()!="")
                {
                    if($('#skill4').val()!="")
                    {
                        var skill5Questions=$('#skillid4').val()+"-"+$('#skill4').val();        
                    }
           
                    else
                    {
                        var skill5Questions=$('#skillid4').val()+"-"+0;       
                    }  
                }
                else
                {
                    var skill5Questions="";  
                }
                if($('#skillid5').val()!="")
                {
                    if($('#skill5').val()!="")
                    {
                        var skill6Questions=$('#skillid5').val()+"-"+$('#skill5').val();        
                    }
           
                    else
                    {
                        var skill6Questions=$('#skillid5').val()+"-"+0;       
                    } 
                }
                else
                {
                    var skill6Questions="";  
                }
                if($('#skillid6').val()!="")
                {
                    if($('#skill6').val()!="")
                    {
                        var skill7Questions=$('#skillid6').val()+"-"+$('#skill6').val();        
                    }
           
                    else
                    {
                        var skill7Questions=$('#skillid6').val()+"-"+0;       
                    }   
                }
                else
                {
                    var skill7Questions="";  
                }
                if($('#skillid7').val()!="")
                {
                    if($('#skill7').val()!="")
                    {
                        var skill8Questions=$('#skillid7').val()+"-"+$('#skill7').val();        
                    }
           
                    else
                    {
                        var skill8Questions=$('#skillid7').val()+"-"+0;       
                    }   
                }
                else
                {
                    var skill8Questions="";  
                }
                if($('#skillid8').val()!="")
                {
                    if($('#skill8').val()!="")
                    {
                        var skill9Questions=$('#skillid8').val()+"-"+$('#skill8').val();        
                    }
           
                    else
                    {
                        var skill9Questions=$('#skillid8').val()+"-"+0;       
                    }    
                }
                else
                {
                    var skill9Questions="";  
                }
                if($('#skillid9').val()!="")
                {
                    if($('#skill9').val()!="")
                    {
                        var skill10Questions=$('#skillid9').val()+"-"+$('#skill9').val();        
                    }
           
                    else
                    {
                        var skill10Questions=$('#skillid9').val()+"-"+0;       
                    }   
                }
                else
                {
                    var skill10Questions="";  
                }
      
       
                var url='.././Requirements/techReviewForward.action?requirementId='+requirementId+'&consult_id='+consult_id+'&interview='+interview+'&interviewType='+interviewType+'&empIdTechReview='+empIdTechReview+'&interviewDate='+interviewDate+'&reviewAlertDate='+reviewAlertDate+'&alertMessageTechReview='+alertMessageTechReview+'&timeZone='+timeZone+'&empIdTechReview2='+empIdTechReview2+'&interviewLocation='+interviewLocation+
                '&techReviewQuestions='+techReviewQuestions+'&techReviewComments='+techReviewComments+'&techReviewSeverity='+techReviewSeverity+'&techReviewTime='+techReviewTime+'&skillCategoryArry='+skillCategoryArry+
                '&skill1Questions='+skill1Questions+'&skill2Questions='+skill2Questions+'&skill3Questions='+skill3Questions+'&skill4Questions='+skill4Questions+'&skill5Questions='+skill5Questions+
                '&skill6Questions='+skill6Questions+'&skill7Questions='+skill7Questions+'&skill8Questions='+skill8Questions+'&skill9Questions='+skill9Questions+'&skill10Questions='+skill10Questions+'&contechNote='+contechNote+'&checked='+checked;
        
                //alert(url)
                var req=initRequest(url);
                req.onreadystatechange = function() {
                    if (req.readyState == 4 && req.status == 200) {
                        //alert("Success")
                        //$("e").html(" <b><font color='green'>Tech Review Forwarded Succesfully</font></b>");
                        //document.getElementsByTagName("e").innerHTML="<b><font color='green'>Tech Review Forwarded Succesfully</font></b>";
                        document.getElementById("empIdTechReview").value="";
                        document.getElementById("eNameTechReview").value="";
                        document.getElementById("interviewDate").value="";
                        document.getElementById("interviewLocation").value="";
                        document.getElementById("interviewType").selectedIndex = "0";
                        document.getElementById("techReviewQuestions").value="";
                        document.getElementById("interviewComments").value="";
                        document.getElementById("interviewType").selectedIndex = "M";
                        document.getElementById("techReviewTime").selectedIndex = "";
                        document.getElementById("techReviewMints").selectedIndex = "";
                        setLocationForFaceToFace();
                    } 
                };
                req.open("GET",url,"true");
                req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                req.send(null);
                swal("Success", " Successfully Forwarded ", "success");
            }
        
    
         
            else {
     
                swal("Cancelled", " Cancelled ", "error");
 
      
            }
        });
    }
}

function removeErrorMsgForTechie()
{
    //alert("hello jagan")
    // alert("rem");
    $("e").html("");
   
    $("#eNameTechReview").css('border','1px solid #ccc');
    $("#interviewDate").css('border','1px solid #ccc');
    $("#interviewLocation").css('border','1px solid #ccc');
    return false;
}


function getSearchTechReviewList(){
    initSessionTimer();
    var reviewStartDate=$('#reviewStartDate').val();
    var reviewEndDate=$('#reviewEndDate').val();
    var techReviewStatus=$('#techReviewStatus').val();
    // alert(techReviewStatus)
    if(reviewStartDate!=''){
        if(reviewEndDate==''){
            $("#reviewEndDate").css("border", "1px solid red");
            $('#techReviewValidation').html(" <b><font color='red'>please enter End Date</font></b>");

            $('#techReviewValidation').show().delay(4000).fadeOut();

            return false;
        }
        
    }
    if(reviewEndDate!=''){
        if(reviewStartDate==''){
            $("#reviewStartDate").css("border", "1px solid red");
            $('#techReviewValidation').html(" <b><font color='red'>please enter Start Date</font></b>");

            $('#techReviewValidation').show().delay(4000).fadeOut();

            return false;
        }
        
    }
    if(reviewStartDate!= '' && reviewEndDate!= '' )
    {
        //alert("hello")
        var splitReqStartDate = reviewStartDate.split('-');
        var reqAddStartDate = new Date(splitReqStartDate[2], splitReqStartDate[0]-1 , splitReqStartDate[1]); //Y M D 
        var splitReqEndDate = reviewEndDate.split('-');
        var reqAddtargetDate = new Date(splitReqEndDate[2], splitReqEndDate[0]-1, splitReqEndDate[1]); //Y M D
        var reqStartDate = Date.parse(reqAddStartDate);
        var reqTargetDate= Date.parse(reqAddtargetDate);
        var  difference=(reqTargetDate - reqStartDate) / (86400000 * 7);
        if(difference<0)
        {
             
       
            $('#techReviewValidation').html(" <b><font color='red'>Start date Must be less than End date</font></b>");

            $('#techReviewValidation').show().delay(4000).fadeOut();

            return false;
        }
    
    }
    var url='.././Requirements/getSearchTechReviewList.action?reviewStartDate='+reviewStartDate+'&techReviewStatus='+techReviewStatus+'&reviewEndDate='+reviewEndDate;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingTechReviewSearch').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
             $('#loadingTechReviewSearch').hide();
            populateTechReviewTable(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateTechReviewTable(response){
    //alert(response.length)
   
    $(".page_option").css('display', 'block');
    $(".pagination").css('display', 'block');
    var techReviewList=response.split("^");
    var consultFlag="customer";
    var techSearch="search";
    document.getElementById("techSearch").value=techSearch;
    var table = document.getElementById("techReviewTable");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        
        for(var i=0;i<techReviewList.length-1;i++){   
            //alert(techReviewList[0])
            var Values=techReviewList[i].split("|");
            {  
                //$("#requirementId").val(Values[9]);
                //alert($('#requirementId').val()+" "+Values[9])
                var row = $("<tr />")
                $("#techReviewTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                var v_comments = Values[15].trim().replace(/ /g, '%20');
                row.append($('<td><a href=../consultant/getConsultantDetails.action?techReviewFlag=techReview&consultFlag='+consultFlag+'&consult_id='+Values[0]+'&vendorcomments='+v_comments+'>' + Values[1] + "</a></td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[10]+ "</td>"));
                if(Values[10]=='Online' || Values[10]=='Psychometric')
                {
                    row.append($("<td>" + Values[5]+ "</td>"));       
                }else
                {
                    row.append($("<td>" + Values[12] + "&nbsp;&nbsp;"+ Values[13] + "&nbsp;&nbsp;"+ Values[14] +"</td>"));       
                }
                row.append($('<td><a href="#" class="Forwarded_popup_open" onclick="showOverlayForwardedBy('+Values[7]+');">'+ Values[4] +"</td>"));
                //row.append($("<td>" + Values[4] + "</td>"));
                //row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>" + Values[8] + "</td>"));
                if(Values[8]=='Rejected'){
                    row.append($("<td><figcaption><button style='cursor: default' type='button' value="+ Values[6] +" ><img src='./../../includes/images/download.png' height='20' width='20' style='opacity:0.3'></button></figcaption></td>"));
                    row.append($('<td><a href="#" class="" style="pointer-events: none; cursor: default; opacity:0.3"> Click'+"</td>"));
                
                }else{
                    row.append($("<td><figcaption><button type='button' value="+ Values[6] +" onclick=doTechReviewAttachmentDownload("+ Values[6] +")><img src='./../../includes/images/download.png' height='20' width='20' ></button></figcaption></td>"));
                    row.append($('<td><a href="#" class="techReview_popup_open" onclick="techReviewOverlay('+Values[9]+',\'' + Values[8] + '\');techReviewDetailsOnOverlay('+Values[0]+','+Values[11]+',\''+ Values[10]+'\');"> Click'+"</td>"));
                }
            }
        }
    }
    else{
        var row = $("<tr />")
        $("#techReviewTable").append(row);
        row.append($('<td colspan="10"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
        $(".pagination ").css('display', 'none');
    }
   
    $('#techReviewTable').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    pager.init(); 
   
}
function doTechReviewAttachmentDownload(acc_attachment_id)
{
    $("#resume").html("");
    var techSearch=document.getElementById("techSearch").value;
    
    //alert(techSearch);
    var reviewStartDate=$('#reviewStartDate').val();
    var reviewEndDate=$('#reviewEndDate').val();
    var techReviewStatus=$('#techReviewStatus').val();
    window.location = './recruitment/consultant/downloadAttachmentTechReview.action?acc_attachment_id='+acc_attachment_id+'&techSearch='+techSearch+'&reviewStartDate='+reviewStartDate+'&techReviewStatus='+techReviewStatus+'&reviewEndDate='+reviewEndDate;
}

function techReviewOverlay(reqId,status){
    $("e").html(" ");
    document.getElementById("techSkill").value="";
    document.getElementById("domainSkill").value="";
    document.getElementById("comSkill").value="";
    document.getElementById("consultantComments").value=""; 
    $("#requirementId").val(reqId);
    $("#finalTechReviewStatus").val(status);
    if(status=="Selected" || status=="ShortListed")
    {
        document.getElementById("saveTechReview").style.display="none";
        $('#skillRate').removeClass('required');
        $('#CommentsDiv').removeClass('required');
    }
    else
    {
        document.getElementById("saveTechReview").style.display="";   
        $('#skillRate').addClass('required');
        $('#CommentsDiv').addClass('required');
    }
    var specialBox = document.getElementById("techReviewBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#techReview_popup').popup(      
        );    
    return false;
}
function techReviewDetailsOnOverlay(consultId,conTechId,reviewType){
    //alert("hi "+conTechId)
    $('#consult_id').val();
    $('#contechId').val(conTechId);
    $('#consultantId').val(consultId);
    // alert(reviewType)
    if(reviewType=="Online" || reviewType=="Psychometric")
    {
        $("#notOnline").hide();  
        $("#online").hide();
        $("#loctionDiv").hide(); 
        $("#notesDiv").hide();  
        $("#skillQuestion1").html("");
        $("#skillQuestion2").html("");
        $("#skillQuestion3").html("");
        $("#skillQuestion4").html("");
        $("#skillQuestion5").html("");
        $("#skillQuestion6").html("");
        $("#skillQuestion7").html("");
        $("#skillQuestion8").html("");
        $("#skillQuestion9").html("");
        $("#skillQuestion10").html("");
        $("#examButton").hide();
        $("#saveButtonReview").hide();
          
    }
    else{
        $("#notOnline").show(); 
        $("#online").hide();
        $("#skillQuestion1").html("");
        $("#skillQuestion2").html("");
        $("#skillQuestion3").html("");
        $("#skillQuestion4").html("");
        $("#skillQuestion5").html("");
        $("#skillQuestion6").html("");
        $("#skillQuestion7").html("");
        $("#skillQuestion8").html("");
        $("#skillQuestion9").html("");
        $("#skillQuestion10").html("");
        $("#examButton").hide();
        $("#saveButtonReview").show(); 
        if(reviewType=="Face to Face" )
        {
            $("#loctionDiv").show(); 
            $("#notesDiv").hide(); 
        }
        else
        {
            $("#notesDiv").show();
            $("#loctionDiv").hide(); 
        }
    }
    var req_Id=$("#requirementId").val()
    var contechId=conTechId;
    var url='.././Requirements/getConsultDetailsOnOverlay.action?consultantId='+consultId+'&contechId='+contechId+'&req_Id='+req_Id+'&reviewType='+reviewType;
    ;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            var techReviewList=req.responseText.split("^");
            for(var i=0;i<techReviewList.length-1;i++){   
                var Values=techReviewList[i].split("|");
                {  
                    if(reviewType!="Online" && reviewType!="Psychometric"){
                        $("#contechId").val(contechId);
                        $("#consultId").val(consultId);
                        $("#consultantName").val(Values[0]);
                        $("#consultantEmail").val(Values[1]);
                        $("#consultantMobile").val(Values[2]);
                        $("#interviewDate").val(Values[3]);
                        //$("#techTitle").val(Values[5]);
                        $("#onlineExam").show();
                        $("#onlineExamStatus").show();

                        $("#examStatus").hide(); 
                        $("#ResumeId").val(Values[4]);
                    
                        if(Values[5]=='null' || Values[5]=="")
                        {
                            Values[5]=" "; 
                        }
                        $("#consultantJobTitle").val(Values[5]);
                    
                        if(Values[6]=='null' || Values[6]=="")
                        {
                            Values[6]=" "; 
                        }
                        $("#consultantSkill").val(Values[6]);
                        $("#interviewType").val(Values[7]);
                        if(Values[8]=='null' || Values[8]=="")
                        {
                            Values[8]=" "; 
                            document.getElementById("techSkill").readOnly = false;
                        }else{
                            $("#techSkill").val(Values[8]);
                            document.getElementById("techSkill").readOnly = true;
                        }
                        if(Values[9]=='null' || Values[9]=="")
                        {
                            Values[9]=" "; 
                            document.getElementById("domainSkill").readOnly = false;
                        }else{
                            $("#domainSkill").val(Values[9]);
                            document.getElementById("domainSkill").readOnly = true;
                        }
                        if(Values[10]=='null' || Values[10]=="")
                        {
                            Values[10]=" "; 
                            document.getElementById("comSkill").readOnly = false;
                        }else{
                            $("#comSkill").val(Values[10]);
                            document.getElementById("comSkill").readOnly = true;
                        }
                        if(Values[11]=='null' || Values[11]=="")
                        {
                            Values[11]=" "; 
                            document.getElementById("consultantComments").readOnly = false;
                        }else{
                            $("#consultantComments").val(Values[11]);
                            document.getElementById("consultantComments").readOnly = true;
                        }
                        //alert(Values[12])
                        if(Values[12]=='null' ){
                            $("#consultantNotes").val("");
                        }else
                        {
                            $("#consultantNotes").val(Values[12]);    
                        }
                        if(reviewType=="Face to Face" ){
                            $("#consultantLocaiton").val(Values[13]);
                        }    
                      
                      
                    }
                    else
                    {
                        $("#consultantName").val(Values[0]);
                        if(Values[1]=='null' || Values[1]==""){
                            Values[1]="";
                        }
                        $("#consultantEmail").val(Values[1]);
                        $("#consultantEmail").val(Values[2]);
                        $("#consultantMobile").val(Values[3]);
                        $("#interviewDate").val(Values[4]);
                        $("#consultantNotes").val(Values[38]);
                        if(Values[39]=="null")
                        {
                            $("#consultantComments").val("");         
                        }else{
                            $("#consultantComments").val(Values[39]);      
                        } 
                        $("#examStatus").html(" <b><font color='green'>"+Values[5]+"</font></b>");
                        $("#onlineExam").hide(); 
                        $("#onlineExamStatus").hide();
                        $("#examStatus").show(); 
                        $("#online").show();
                        $("#skillDivQuestion1").hide(); 
                        $("#skillDivQuestion2").hide(); 
                        $("#skillDivQuestion3").hide();
                        $("#skillDivQuestion4").hide();
                        $("#skillDivQuestion5").hide();
                        $("#skillDivQuestion6").hide(); 
                        $("#skillDivQuestion7").hide(); 
                        $("#skillDivQuestion8").hide(); 
                        $("#skillDivQuestion9").hide(); 
                        $("#skillDivQuestion10").hide(); 
                        if(Values[5]=="Rejected" || Values[5]=="ShortListed" || Values[5]=="Processing")
                        {
                              
                            $("#examButton").hide();   
                        }
                        else
                        {
                            $("#examButton").show();    
                        }
                        
                        if(Values[36]=='Submitted')
                        {
                            $("#examsubmittedDate").html("<b><font color='green'>"+Values[37]+"</font></b>");
                            if(Values[6]!='null' && Values[7]!=0)
                            {
                                $("#skillDivQuestion1").show();  
                                var percentage1=findPercentage(Values[26],Values[7]);
                                if(percentage1 < 50)
                                {
                                    $("#skillQuestion1").html(" <b><font color='#56a5ec'>"+Values[6]+":</font><font color='red'> "+percentage1+"%</font></b>");    
                                } 
                                else{
                                    $("#skillQuestion1").html(" <b><font color='#56a5ec'>"+Values[6]+":</font><font color='green'> "+percentage1+"%</font></b>");   
                                }
                            }
                            if(Values[8]!='null' && Values[9]!=0)
                            {
                                $("#skillDivQuestion2").show();  
                                var percentage2=findPercentage(Values[27],Values[9])
                                if( percentage2 < 50)
                                {    
                            
                                    $("#skillQuestion2").html(" <b><font color='#56a5ec'>"+Values[8]+":</font><font color='red'> "+percentage2+"%</font></b>");      
                                }
                                else
                                {
                                    $("#skillQuestion2").html(" <b><font color='#56a5ec'>"+Values[8]+":</font><font color='green'> "+percentage2+"%</font></b>");             
                                }
                            }
                            if(Values[10]!='null' && Values[11]!=0)
                            {
                                $("#skillDivQuestion3").show(); 
                                var percentage3=findPercentage(Values[28],Values[11])  
                                if(percentage3 < 50)
                                {    
                                    $("#skillQuestion3").html(" <b><font color='#56a5ec'>"+Values[10]+":</font><font color='red'>"+percentage3+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion3").html(" <b><font color='#56a5ec'>"+Values[10]+":</font><font color='green'>"+percentage3+"%</font></b>");           
                                }
                            }
                               
                            if(Values[12]!='null' && Values[13]!=0)
                            {
                                $("#skillDivQuestion4").show();  
                                var percentage4=findPercentage(Values[29],Values[13])
                                if(percentage4 < 50)
                                {  
                                    $("#skillQuestion4").html(" <b><font color='#56a5ec'>"+Values[12]+":</font><font color='red'>"+percentage4+"%</font></b>");   
                                }else
                                {
                                    $("#skillQuestion4").html(" <b><font color='#56a5ec'>"+Values[12]+":</font><font color='green'>"+percentage4+"%</font></b>");       
                                }
                            }
                               
                            if(Values[14]!='null' && Values[15]!=0)
                            {
                                $("#skillDivQuestion5").show(); 
                                var percentage5=findPercentage(Values[30],Values[15])
                                if(percentage5 < 50)
                                {  
                                    $("#skillQuestion5").html(" <b><font color='#56a5ec'>"+Values[14]+":</font><font color='red'>"+percentage5+"%</font></b>"); 
                                }
                                else
                                {
                                    $("#skillQuestion5").html(" <b><font color='#56a5ec'>"+Values[14]+":</font><font color='green'>"+percentage5+"%</font></b>");      
                                }
                            }
                            if(Values[16]!='null' && Values[17]!=0)
                            {
                                $("#skillDivQuestion6").show();
                                var percentage6=findPercentage(Values[31],Values[17])
                                if(percentage6 < 50)
                                { 
                                    $("#skillQuestion6").html(" <b><font color='#56a5ec'>"+Values[16]+":</font><font color='red'>"+percentage6+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion6").html(" <b><font color='#56a5ec'>"+Values[16]+":</font><font color='green'>"+percentage6+"%</font></b>");      
                                }
                            }
                            if(Values[18]!='null' && Values[19]!=0)
                            {
                                $("#skillDivQuestion7").show();  
                                var percentage7=findPercentage(Values[32],Values[19])
                                if(percentage7 < 50)
                                {  
                                    $("#skillQuestion7").html(" <b><font color='#56a5ec'>"+Values[18]+":</font><font color='red'>"+percentage7+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion7").html(" <b><font color='#56a5ec'>"+Values[18]+":</font><font color='green'>"+Values[32]+"/"+Values[19]+"</font></b>");         
                                }
                            }
                            if(Values[20]!='null' && Values[21]!=0)
                            {
                                $("#skillDivQuestion8").show();  
                                var percentage8=findPercentage(Values[33],Values[21])
                                if(percentage8 < 50)
                                { 
                                    $("#skillQuestion8").html(" <b><font color='#56a5ec'>"+Values[20]+":</font><font color='red'>"+percentage8+"%</font></b>");  
                                }else
                                {
                                    $("#skillQuestion8").html(" <b><font color='#56a5ec'>"+Values[20]+":</font><font color='green'>"+percentage8+"%</font></b>");      
                                }
                            }
                            if(Values[22]!='null' && Values[23]!=0)
                            {
                                $("#skillDivQuestion9").show();  
                                var percentage9=findPercentage(Values[34],Values[23])
                                if(percentage9 < 50)
                                { 
                                    $("#skillQuestion9").html(" <b><font color='#56a5ec'>"+Values[22]+":</font><font color='red'>"+percentage9+"%</font></b>");  
                                }else
                                {
                                    $("#skillQuestion9").html(" <b><font color='#56a5ec'>"+Values[22]+":</font><font color='green'>"+percentage9+"%</font></b>");       
                                }
                            }
                            if(Values[24]!='null' && Values[25]!=0)
                            {
                                $("#skillDivQuestion10").show();  
                                var percentage10=findPercentage(Values[35],Values[25])
                                if(percentage10 < 50)
                                {   
                                    $("#skillQuestion10").html(" <b><font color='#56a5ec'>"+Values[24]+":</font><font color='red'>"+percentage10+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion10").html(" <b><font color='#56a5ec'>"+Values[24]+":</font><font color='green'>"+percentage10+"%</font></b>");         
                                }
                            }
                                
                        }else
                        {
                            $("#online").hide(); 
                        }
                        
                    }
                }
            }
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function saveTechReviewResults(){
    var contechId =$("#contechId").val();
    var requirementId=$('#requirementId').val();
    //alert(requirementId)
    var techSkill=$("#techSkill").val();
    var domainSkill=$("#domainSkill").val();
    var comSkill=$("#comSkill").val();
    var consultantComments=$("#consultantComments").val();
    if(techSkill=="")
    {
        $("e").html(" <b><font color='red'>Technical Skills Should not be empty</font></b>");    
        return false;
    }
    if(domainSkill=="")
    {
        $("e").html(" <b><font color='red'>Domain Skills Should not be empty</font></b>");    
        return false;
    } 
    if(comSkill=="")
    {
        $("e").html(" <b><font color='red'>Communication Skills Should not be empty</font></b>");    
        return false;
    } 
    if(consultantComments=="")
    {
        $("e").html(" <b><font color='red'>Comments Should not be empty</font></b>");    
        return false;
    }
    var iDate =$("#interviewDate").val();
    var interviewDate = iDate.split('-');
    var scheduleDate = new Date(interviewDate[2], interviewDate[0]-1 , interviewDate[1]);
    var curDate=new Date();
    if(curDate<scheduleDate){
        return interviewDateConfirmation();
    }
    var rating =((parseInt(techSkill, 10)+parseInt(domainSkill, 10)+parseInt(comSkill, 10))/3);
    var consultantComments=$("#consultantComments").val();
    var finalTechReviewStatus=$("#finalTechReviewStatus").val();
    var techTitle=$("#techTitle").val();
    var consultId=$("#consultId").val();
    var interviewType=$("#interviewType").val();
    //alert("hi "+techSkill+"  "+comSkill+"   "+domainSkill+"  "+consultantComments+"   "+finalTechReviewStatus+"  "+rating+"  "+techTitle+"  "+consultId)
    var url='.././Requirements/saveTechReviewResults.action?techSkill='+techSkill+'&domainSkill='+domainSkill+'&comSkill='+comSkill+'&rating='+rating+'&consultantComments='+consultantComments+'&finalTechReviewStatus='+finalTechReviewStatus+'&techTitle='+techTitle+'&consultId='+consultId+'&requirementId='+requirementId+'&interviewType='+interviewType+'&contechId='+contechId;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if(req.responseText==1){
                //alert(req.responseText)
                getSearchTechReviewList()
                $("e").html(" <b><font color='green'>Tech Review Result saved Succesfully</font></b>");
            }else{
                $("e").html(" <b><font color='red'>Error Occured</font></b>");
            }
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}


//Add By Aklakh
function interviewDateConfirmation(){
    var contechId =$("#contechId").val();
    var requirementId=$('#requirementId').val();
    //alert(requirementId)
    var techSkill=$("#techSkill").val();
    var domainSkill=$("#domainSkill").val();
    var comSkill=$("#comSkill").val();
    var consultantComments=$("#consultantComments").val();
    var rating =((parseInt(techSkill, 10)+parseInt(domainSkill, 10)+parseInt(comSkill, 10))/3);
    var consultantComments=$("#consultantComments").val();
    var finalTechReviewStatus=$("#finalTechReviewStatus").val();
    var techTitle=$("#techTitle").val();
    var consultId=$("#consultId").val();
    var interviewType=$("#interviewType").val();
    swal({
    
        title: "Do you want to add Review  before scheduled date?",
  
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
            var url='.././Requirements/saveTechReviewResults.action?techSkill='+techSkill+'&domainSkill='+domainSkill+'&comSkill='+comSkill+'&rating='+rating+'&consultantComments='+consultantComments+'&finalTechReviewStatus='+finalTechReviewStatus+'&techTitle='+techTitle+'&consultId='+consultId+'&requirementId='+requirementId+'&interviewType='+interviewType+'&contechId='+contechId;
            // alert(url)
            var req=initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4 && req.status == 200) {
                    //alert(req.responseText)
                    if(req.responseText==1){
                        //$("d").html(" <b><font color='green'>Record Deleted!</font></b>");
                        getSearchTechReviewList();
                        $("e").html(" <b><font color='green'>Tech Review Result saved Succesfully</font></b>");
                        swal("Success!", "Tech Review saved successfully....", "success");
                    }else{
                        //$("d").html(" <b><font color='red'>Failed to Delete!</font></b>");
                        $("e").html(" <b><font color='red'>Error Occured</font></b>");
                        swal("Sorry!", "Saved Failed....", "Error");
                    }
                } 
            };
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
            return false;
   
   
    
        } else {
     
            swal("Cancelled", " cancelled ", "error");
 
      
        }
    });
}

function showOverlayForwardedBy(id){
    // alert(id)
    var url='getRecruiterOverlay.action?id='+id;
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert("result is::")
            // setVendorStates(req.responseText);
            setForwardedBy(req.responseText)
        } 
    //alert(req.readyState +" and "+req.status)

    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#Forwarded_popup').popup(      
        );    
    return false;
}
function setForwardedBy(response){
    var Values=response.split("|");
    document.getElementById("recruiterNameOverlay").value=Values[0];
    document.getElementById("recruiterEmailIdOverlay").value=Values[1];
    document.getElementById("recruiterPhoneOverlay").value=Values[2];

}
function closeForwardedByOverlay()
{
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#Forwarded_popup').popup(      
        );    
    return false;    
}

function searchTechReviews(){
    initSessionTimer();
    var requirementId=$('#requirementId').val();
    var consult_id=$('#consult_id').val();
    var interviewDate=$('#searchInterviewDate').val();
    var empIdTechReview=$('#empIdTechReview').val();
    if($('#eNameTechReview').val()=="")
    {
            
        empIdTechReview=0;
          
    }
    //alert("HIIIIIIIIIIIIIIIIII "+requirementId+" "+consult_id+" "+interviewDate+" "+empIdTechReview)
    var url='.././Requirements/getConsultantTechReviews.action?requirementId='+requirementId+'&consult_id='+consult_id+'&interviewDate='+interviewDate+'&empIdTechReview='+empIdTechReview;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            populateTechReviewSearchTable(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;

}
function populateTechReviewSearchTable(response){
    /// alert(response)
    $(".page_option").css('display','block');
    $(".pagination").css('display','block');
    
    var techReviewList=response.split("^");
    
    var table = document.getElementById("techReviewSearchTable");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        
        for(var i=0;i<techReviewList.length-1;i++){   
            //alert(techReviewList[0])
            var Values=techReviewList[i].split("|");
            {  
                //alert(Values[3]+" "+Values[7])
                $("#consult_id").val(Values[0]);
                $("#requirementId").val(Values[1]);
                $("#reviewType").val(Values[9]);
                $("#forwardedToId").val(Values[7]);
                
                var row = $("<tr />")
                $("#techReviewSearchTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                //row.append($("<td>" + Values[9] + "</td>"));
                row.append($('<td><a href="#" class="techReviewResults_popup_open" onclick="techReviewResultsToViewOnOverlay(\''+Values[9]+'\','+Values[8]+');techReviewResultsOverlay();" >'+Values[9]+"</td>"));
                //row.append($('<td><a href="#" class="emailPhoneShow_popup_open" onclick="getMailPhoneOfReviewedBy('+Values[7]+');techReviewEmailPhoneOverlay();" >'+Values[9]+"</td>"));
                if(Values[9]=='Online' || Values[9]=='Psychometric'){
                    row.append($("<td>" + Values[15] + "</td>"));
                }else{
                    row.append($("<td>" + Values[2] + "</td>"));    
                }
                if(Values[3]!='null' && Values[3]!=""){
                    row.append($('<td><a href="#" class="emailPhoneShow_popup_open" onclick="getMailPhoneOfReviewedBy('+Values[7]+');techReviewEmailPhoneOverlay();" >'+Values[3]+"</td>"));
                }else{
                    if(Values[9]=='Online'|| Values[9]=='Psychometric'){
                        if(Values[3]=='null')
                        {
                            row.append($('<td><a href="#" class="emailPhoneShow_popup_open" onclick="getMailPhoneOfReviewedBy('+Values[13]+');techReviewEmailPhoneOverlay();" >'+""+"</td>"));                 
                        }
                        else
                        {
                            row.append($('<td><a href="#" class="emailPhoneShow_popup_open" onclick="getMailPhoneOfReviewedBy('+Values[13]+');techReviewEmailPhoneOverlay();" >'+Values[3]+"</td>"));                       
                        }
                        
                    }else{
                        Values[3]="";
                        row.append($("<td>" + Values[3] + "</td>"));
                    }
                }
                if(Values[12]!='null'){
                    row.append($("<td>" + Values[12] + "</td>"));
                }
                else{
                    row.append($("<td>"+ +"</td>")); 
                }
                //                if(Values[11]!='null' && Values[11]!=""){
                //                    row.append($('<td><a href="#" class="emailPhoneShow_popup_open" onclick="getMailPhoneOfReviewedBy('+Values[10]+');techReviewEmailPhoneOverlay();" >'+Values[11]+"</td>"));
                //                }else{
                //                    Values[11]="";
                //                    row.append($("<td>" + Values[11] + "</td>"));
                //                }
                if(Values[4]!='null' && Values[4]!=""){
                    row.append($('<td><a href="#" class="techReviewCommentsOverlay_popup_open" onclick="techReviewCommentsOverlayJs(\''+Values[4]+'\');" >'+Values[4].substr(0, 20)+"..</td>"));
                }else{
                    Values[4]="";
                    row.append($("<td>" + Values[4] + "</td>"));
                }
                //row.append($("<td>" + Values[11] + "</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
            }
        }
    }
    else{
        var row = $("<tr />")
        $("#techReviewSearchTable").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display','none');
        $(".pagination").css('display','none');
    }
    pager.init(); 
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
}

function getMailPhoneOfReviewedBy(val)
{
    var url='../acc/getEmailPhoneDetails.action?userId='+val;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                var details=req.responseText.split("|");
                document.getElementById("email").value=details[0];
                document.getElementById("contactNo").value=details[1];

            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function techReviewEmailPhoneOverlay(){
    var specialBox = document.getElementById("emailPhoneShowOnOverlay");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#emailPhoneShow_popup').popup(      
        );    
    return false;
}
function techReviewCommentsOverlay(val)
{
    var url='.././Requirements/techReviewCommentsOverlay.action?conTechReviewId='+val;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("reviewComments").value=req.responseText;
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function techReviewCommentsOverlayJs(comments){
    document.getElementById("reviewComments").value=comments;
    var specialBox = document.getElementById("reviewCommentsOverlay");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    }
    else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#techReviewCommentsOverlay_popup').popup(      
        );    
    return false;
}
//for viewing results
function techReviewResultsToViewOnOverlay(reviewType,id){
    var requirementId=$('#requirementId').val();
    var consult_id=$('#consult_id').val();
    var conTechReviewId=id;
    $('#contechId').val(id);
    //alert(conTechReviewId)
    //alert("Before "+reviewType)
    if(reviewType=="Online" || reviewType=="Psychometric")
    {
        $("#notOnline").hide();  
        $("#online").show();
        document.getElementById("consultantComments").disabled = false;
        $("#skillQuestion1").html("");
        $("#skillQuestion2").html("");
        $("#skillQuestion3").html("");
        $("#skillQuestion4").html("");
        $("#skillQuestion5").html("");
        $("#skillQuestion6").html("");
        $("#skillQuestion7").html("");
        $("#skillQuestion8").html("");
        $("#skillQuestion9").html("");
        $("#skillQuestion10").html("");
        $("#examButton").hide();
         
          
    }
    else{
        $("#notOnline").show(); 
        $("#online").hide();
        document.getElementById("consultantComments").disabled = true;
        $("#skillQuestion1").html("");
        $("#skillQuestion2").html("");
        $("#skillQuestion3").html("");
        $("#skillQuestion4").html("");
        $("#skillQuestion5").html("");
        $("#skillQuestion6").html("");
        $("#skillQuestion7").html("");
        $("#skillQuestion8").html("");
        $("#skillQuestion9").html("");
        $("#skillQuestion10").html("");
        $("#examButton").hide();
        
    }
    if(reviewType==undefined || reviewType==""){
        //alert(reviewType)
        reviewType=$('#reviewType').val();
    //alert(reviewType)
    }
    var forwardedToId=$('#forwardedToId').val();

    //alert("hi "+requirementId+"  "+consult_id+"  "+reviewType)
    var url='.././Requirements/getTechReviewResultOnOverlay.action?requirementId='+requirementId+'&consult_id='+consult_id+'&reviewType='+reviewType+'&forwardedToId='+forwardedToId+'&conTechReviewId='+conTechReviewId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            var techReviewList=req.responseText.split("^");
            for(var i=0;i<techReviewList.length-1;i++){   
                var Values=techReviewList[i].split("|");
                {  
                    if(reviewType!="Online" && reviewType!="Psychometric"){
                        $("#consultId").val(consult_id);
                        $("#consultantName").val(Values[0]);
                        $("#consultantEmail").val(Values[1]);
                        $("#consultantMobile").val(Values[2]);
                        $("#interviewDate").val(Values[3]);
                        $("#onlineExam").show();
                        $("#examStatus").hide(); 
                        //$("#techTitle").val(Values[5]);
                        $("#ResumeId").val(Values[4]);
                        if(Values[5]=='null' || Values[5]==""){
                            Values[5]="";
                        }
                        $("#consultantJobTitle").val(Values[5]);
                    
                        if(Values[6]=='null' || Values[6]=="")
                        {
                            Values[6]="";     
                        }
                        $("#consultantSkill").val(Values[6]);         
                            
                        if(Values[7]=='null' || Values[7]==""){
                            Values[7]="";
                        }
                        $("#techSkill").val(Values[7]);
                        if(Values[8]=='null' || Values[8]==""){
                            Values[8]="";
                        }
                        $("#domainSkill").val(Values[8]);
                        if(Values[9]=='null' || Values[9]==""){
                            Values[9]="";
                        }
                        $("#comSkill").val(Values[9]);
                        if(Values[10]=='null' || Values[10]==""){
                            Values[10]="";
                        }
                        $("#consultantComments").val(Values[10]);
                        if(Values[11]=='null' || Values[11]==""){
                            Values[11]="";
                        }
                        $("#finalTechReviewStatus").val(Values[11]);
                    }
                    else
                    {
                        $("#consultantName").val(Values[0]);
                        if(Values[1]=='null' || Values[1]==""){
                            Values[1]="";
                        }
                        $("#consultantEmail").val(Values[1]);
                        $("#consultantEmail").val(Values[2]);
                        $("#consultantMobile").val(Values[3]);
                        $("#interviewDate").val(Values[4]);
                        if(Values[38]!='null'){
                            $("#consultantComments").val(Values[38]);   
                        }
                        $("#examStatus").html(" <b><font color='green'>"+Values[5]+"</font></b>");
                        $("#onlineExam").hide();  
                        $("#examStatus").show(); 
                        $("#skillDivQuestion1").hide(); 
                        $("#skillDivQuestion2").hide(); 
                        $("#skillDivQuestion3").hide();
                        $("#skillDivQuestion4").hide();
                        $("#skillDivQuestion5").hide();
                        $("#skillDivQuestion6").hide(); 
                        $("#skillDivQuestion7").hide(); 
                        $("#skillDivQuestion8").hide(); 
                        $("#skillDivQuestion9").hide(); 
                        $("#skillDivQuestion10").hide(); 
                        if(Values[5]=="Rejected" || Values[5]=="ShortListed" || Values[5]=="Processing")
                        {
                            // alert(Values[5])
                            $("#examButton").hide();   
                        }
                        else
                        {
                            $("#examButton").show();    
                        }
                        if(Values[5]=="Processing" )
                        {
                            $("#reviewComments").hide();      
                        }
                        if(Values[5]=="Rejected" || Values[5]=="ShortListed" || Values[5]=="Exam Completed")
                        {
                            $("#reviewComments").show();         
                        }   
                        if(Values[5]=="Rejected" || Values[5]=="ShortListed" ){
                            // alert("disabled")
                            document.getElementById("consultantComments").disabled = true;  
                        }   
                        else{
                            document.getElementById("consultantComments").disabled = false;  
                             
                        }
                        
                        if(Values[36]=='Submitted')
                        {
                            $("#examsubmittedDate").html("<b><font color='green'>"+Values[37]+"</font></b>");
                            if(Values[6]!='null' && Values[7]!=0)
                            {
                                $("#skillDivQuestion1").show();  
                                var percentage1=findPercentage(Values[26],Values[7]);
                                if(percentage1 < 50)
                                {
                                    $("#skillQuestion1").html(" <b><font color='#56a5ec'>"+Values[6]+":</font><font color='red'> "+percentage1+"%</font></b>");    
                                } 
                                else{
                                    $("#skillQuestion1").html(" <b><font color='#56a5ec'>"+Values[6]+":</font><font color='green'> "+percentage1+"%</font></b>");   
                                }
                            }
                            if(Values[8]!='null' && Values[9]!=0)
                            {
                                $("#skillDivQuestion2").show();  
                                var percentage2=findPercentage(Values[27],Values[9])
                                if( percentage2 < 50)
                                {    
                            
                                    $("#skillQuestion2").html(" <b><font color='#56a5ec'>"+Values[8]+":</font><font color='red'> "+percentage2+"%</font></b>");      
                                }
                                else
                                {
                                    $("#skillQuestion2").html(" <b><font color='#56a5ec'>"+Values[8]+":</font><font color='green'> "+percentage2+"%</font></b>");             
                                }
                            }
                            if(Values[10]!='null' && Values[11]!=0)
                            {
                                $("#skillDivQuestion3").show(); 
                                var percentage3=findPercentage(Values[28],Values[11])  
                                if(percentage3 < 50)
                                {    
                                    $("#skillQuestion3").html(" <b><font color='#56a5ec'>"+Values[10]+":</font><font color='red'>"+percentage3+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion3").html(" <b><font color='#56a5ec'>"+Values[10]+":</font><font color='green'>"+percentage3+"%</font></b>");           
                                }
                            }
                               
                            if(Values[12]!='null' && Values[13]!=0)
                            {
                                $("#skillDivQuestion4").show();  
                                var percentage4=findPercentage(Values[29],Values[13])
                                if(percentage4 < 50)
                                {  
                                    $("#skillQuestion4").html(" <b><font color='#56a5ec'>"+Values[12]+":</font><font color='red'>"+percentage4+"%</font></b>");   
                                }else
                                {
                                    $("#skillQuestion4").html(" <b><font color='#56a5ec'>"+Values[12]+":</font><font color='green'>"+percentage4+"%</font></b>");       
                                }
                            }
                               
                            if(Values[14]!='null' && Values[15]!=0)
                            {
                                $("#skillDivQuestion5").show(); 
                                var percentage5=findPercentage(Values[30],Values[15])
                                if(percentage5 < 50)
                                {  
                                    $("#skillQuestion5").html(" <b><font color='#56a5ec'>"+Values[14]+":</font><font color='red'>"+percentage5+"%</font></b>"); 
                                }
                                else
                                {
                                    $("#skillQuestion5").html(" <b><font color='#56a5ec'>"+Values[14]+":</font><font color='green'>"+percentage5+"%</font></b>");      
                                }
                            }
                            if(Values[16]!='null' && Values[17]!=0)
                            {
                                $("#skillDivQuestion6").show();
                                var percentage6=findPercentage(Values[31],Values[17])
                                if(percentage6 < 50)
                                { 
                                    $("#skillQuestion6").html(" <b><font color='#56a5ec'>"+Values[16]+":</font><font color='red'>"+percentage6+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion6").html(" <b><font color='#56a5ec'>"+Values[16]+":</font><font color='green'>"+percentage6+"%</font></b>");      
                                }
                            }
                            if(Values[18]!='null' && Values[19]!=0)
                            {
                                $("#skillDivQuestion7").show();  
                                var percentage7=findPercentage(Values[32],Values[19])
                                if(percentage7 < 50)
                                {  
                                    $("#skillQuestion7").html(" <b><font color='#56a5ec'>"+Values[18]+":</font><font color='red'>"+percentage7+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion7").html(" <b><font color='#56a5ec'>"+Values[18]+":</font><font color='green'>"+Values[32]+"/"+Values[19]+"</font></b>");         
                                }
                            }
                            if(Values[20]!='null' && Values[21]!=0)
                            {
                                $("#skillDivQuestion8").show();  
                                var percentage8=findPercentage(Values[33],Values[21])
                                if(percentage8 < 50)
                                { 
                                    $("#skillQuestion8").html(" <b><font color='#56a5ec'>"+Values[20]+":</font><font color='red'>"+percentage8+"%</font></b>");  
                                }else
                                {
                                    $("#skillQuestion8").html(" <b><font color='#56a5ec'>"+Values[20]+":</font><font color='green'>"+percentage8+"%</font></b>");      
                                }
                            }
                            if(Values[22]!='null' && Values[23]!=0)
                            {
                                $("#skillDivQuestion9").show();  
                                var percentage9=findPercentage(Values[34],Values[23])
                                if(percentage9 < 50)
                                { 
                                    $("#skillQuestion9").html(" <b><font color='#56a5ec'>"+Values[22]+":</font><font color='red'>"+percentage9+"%</font></b>");  
                                }else
                                {
                                    $("#skillQuestion9").html(" <b><font color='#56a5ec'>"+Values[22]+":</font><font color='green'>"+percentage9+"%</font></b>");       
                                }
                            }
                            if(Values[24]!='null' && Values[25]!=0)
                            {
                                $("#skillDivQuestion10").show();  
                                var percentage10=findPercentage(Values[35],Values[25])
                                if(percentage10 < 50)
                                {   
                                    $("#skillQuestion10").html(" <b><font color='#56a5ec'>"+Values[24]+":</font><font color='red'>"+percentage10+"%</font></b>");   
                                }
                                else
                                {
                                    $("#skillQuestion10").html(" <b><font color='#56a5ec'>"+Values[24]+":</font><font color='green'>"+percentage10+"%</font></b>");         
                                }
                            }
                                
                        }else
                        {
                            $("#online").hide(); 
                        }
                        
                    }
                }
            }
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function techReviewResultsOverlay(){
    var specialBox = document.getElementById("techReviewBoxResults");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#techReviewResults_popup').popup(      
        );    
    return false;
}
function setLocationForFaceToFace(){
    // alert(document.getElementById("skillValuesMap").value);
    // var skill_map=document.getElementById("skillValuesMap").value;
    var interviewType=$('#interviewType').val();
    if(interviewType=='Face to Face'){
        $("#locationData").show(); 
        $("#reviewComments").hide();
        $("#reviewQuestions").hide();
        $("#reviewTech").show();
        $("#notesData").hide();
        $("#revewInterview").show();
        $("#reviewZone").show();
        $("#reviewSeverity").hide();
        $("#techDuration").hide();
        $("#techSkills").hide();
        $("#revewSkill0").hide();
        $("#revewSkill1").hide();
        $("#revewSkill2").hide();
        $("#revewSkill3").hide();
        $("#revewSkill4").hide();
        $("#revewSkill5").hide();
        $("#revewSkill6").hide();
        $("#revewSkill7").hide();
        $("#revewSkill8").hide();
        $("#revewSkill9").hide();
        $("#psychoTestSkills").hide();
        $("#interviewDate").val('');
        $("#reviewAlertDate").val('');
        $("#techReviewAlertHours").val('0');
        $("#techReviewAlertMints").val('0');
    }
    else if(interviewType=='Online'){
        //      var $select = $('.selectivity-multiple-selected-item-remove');
        //    $select.remove();  
         
        $("#techReviewSeverity").val('M');
        $("#reviewTech").show();
        $("#revewInterview").hide();
        $("#reviewZone").hide();
        $("#locationData").hide();
        $("#reviewComments").show();
        $("#notesData").hide();
        $("#reviewQuestions").show();
        $("#reviewSeverity").show();
        $("#techDuration").show();
        $("#techSkills").show();
        $("#psychoTestSkills").hide();
          $("#interviewDate").val('');
        //$("#skillCategoryValue").val('');
        //$("#skillCategoryValue").multiselect("");
        //$("#skillCategoryValue option[value]").remove();
        skillsQuestions();
        setAlertDateForOnline();
    }else if(interviewType=='Psychometric'){
        $("#techReviewSeverity").val('M');
        $("#reviewTech").show();
        $("#revewInterview").hide();
        $("#reviewZone").hide();
        $("#locationData").hide();
        $("#notesData").hide();
        $("#reviewComments").show();
        $("#reviewQuestions").show();
        $("#reviewSeverity").show();
        $("#techDuration").show();
        $("#techSkills").hide();
        $("#psychoTestSkills").show();
          $("#interviewDate").val('');
        //psychometricSkill(interviewType);
        skillsQuestions();
        setAlertDateForOnline();
    }
    else{
       
        $("#reviewComments").hide();
        $("#reviewQuestions").hide();
        $("#reviewTech").show();
        $("#revewInterview").show();
        $("#notesData").show();
        $("#reviewZone").show();
        $("#locationData").hide();
        $("#reviewSeverity").hide();
        $("#techDuration").hide();
        $("#techSkills").hide();
        $("#revewSkill0").hide();
        $("#revewSkill1").hide();
        $("#revewSkill2").hide();
        $("#revewSkill3").hide();
        $("#revewSkill4").hide();
        $("#revewSkill5").hide();
        $("#revewSkill6").hide();
        $("#revewSkill7").hide();
        $("#revewSkill8").hide();
        $("#revewSkill9").hide();
        $("#psychoTestSkills").hide();
          $("#interviewDate").val('');
        $("#reviewAlertDate").val('');
        $("#techReviewAlertHours").val('0');
        $("#techReviewAlertMints").val('0');
    }
    
}
function skillRateValidation()
{
    
    var skillRate=document.getElementById("techSkill").value;
    var domainSkill=document.getElementById("domainSkill").value;
    var comSkill=document.getElementById("comSkill").value;
    if(skillRate == 0){
        document.getElementById("techSkill").value="";
    }
    if(skillRate>10){
        
        var skillvalue=skillRate/10;
        //  alert(skillvalue)
        document.getElementById("techSkill").value=parseInt(skillvalue);
        
    }
    if(domainSkill == 0){
        document.getElementById("domainSkill").value="";
    }
    if(domainSkill>10){
        skillvalue=domainSkill/10;
        document.getElementById("domainSkill").value=parseInt(skillvalue);
    }
    if(comSkill == 0){
        document.getElementById("comSkill").value="";
    }
    if(comSkill>10){
        skillvalue=comSkill/10;
        document.getElementById("comSkill").value=parseInt(skillvalue);
    }
}
function acceptNumbers(evt){
    
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
        $("#skillValid").html(" <b><font color='green'>enter only numbers</font></b>");
        return false;
    }
    else
    {
                    
        $("#skillValid").html("");
        return true;
    }
};
function enterTechDateRepository(id)
{ 
    $(id).val("");
    return false;
    
}

function removeErrorMsgForTechieConsultant()
{
    //alert("hello jagan")
    //alert("rem");
    $("#techReviewValidation").html("");
   
    $("#reviewStartDate").css('border','1px solid #ccc');
    $("#reviewEndDate").css('border','1px solid #ccc');
     
    return false;
}
function techReviewsComments(id){
    var elem = document.getElementById("remainingCharsDiv");

    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 1000){
            el.val( el.val().substr(0, 1000) );
        } else {
            elem.style.color="green";

            $("#remainingCharsDiv").text(1000-el.val().length+' Characters remaining . ');
        //            $("#remainingCharsDiv").show().delay(5000).fadeOut();

        }
        if(el.val().length==1000)
        {
            elem.style.color="red";

            $("#remainingCharsDiv").text(' Cannot enter  more than 1000 Characters .'); 
        //  $("#remainingCharsDiv").show().delay(5000).fadeOut();

        }
        
    })
    return false;
};
function removeCommentsRemainMessage()
{
    $("#remainingCharsDiv").html("");
}



function checkCharactersComment(id){
    
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#description_feedback").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#description_feedback").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}

function time()
{
    var min = 0,
    max = 23,
    select = document.getElementById('techReviewTime');

    for (var i = min; i<=max; i++){
        var opt = document.createElement('option');
        if(i < 10)
        {
                          
            opt.value = i;  
            opt.innerHTML = "0"+i;
        }
        else
        {
            opt.value = i;
            opt.innerHTML = i;
        }     
                    
                    
        select.appendChild(opt);
    }
    var minMinu=0,maxMinu=59;
    select = document.getElementById('techReviewMints');

    for (var i = minMinu; i<=maxMinu; i=i+5){
        var opt = document.createElement('option');
                  
        if(i < 10)
        {
            opt.value = i;  
            opt.innerHTML = "0"+i;
        }
        else
        {
            opt.value = i;
            opt.innerHTML = i;
        }
        //opt.innerHTML = i;
        select.appendChild(opt);
    }

//                select = document.getElementById('ss');
//
//                for (var i = minMinu; i<=maxMinu; i++){
//                    var opt = document.createElement('option');
//                    opt.value = i;
//                    opt.innerHTML = i;
//                    select.appendChild(opt);
//                }
}

function getQuestionsCount()
{
    //alert("Hello")
    var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).val()); 
    }); 
    //alert(skillCategoryArry)
    var url='.././Requirements/questionsCount.action?skillCategoryArry='+skillCategoryArry;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                populateQuestionsTable(req.responseText); 
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
// return v; 

}

function populateQuestionsTable(response){
    //alert(response)    
    var techReviewList=response.split("^");
    var table = document.getElementById("questionsCountTable");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        
        for(var i=0;i<techReviewList.length-1;i++){   
            //alert(techReviewList[0])
            var Values=techReviewList[i].split("|");
            {  
                var row = $("<tr />")
                //this will append tr element to table... keep its reference for a while since we will add cels into it
                $("#questionsCountTable").append(row); 
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                
                row.append($("<td>" + Values[2] + "</td>"));
            }
        }
       
    }
    else{
        var row = $("<tr />")
       
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'))
    }
   
// questionOverlay(response);
}

function questionOverlay()
{   //alert("questionOverlay"+response)
    //  document.getElementById('questionsCounttechReviewBox').innerHTML=response;
    var specialBox = document.getElementById('questionsCounttechReviewBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#questionsCountResults_popup').popup(      
        );  
    return false;
}

function skillsQuestions()
{
    // alert(skillCategoryArry)
    for(var i=0;i<=9;i++){   
        // alert("clear")
        $("#labelSkill"+i).html(""); 
        $("#skillid"+i).val("");
        $("#revewSkill"+i).hide();
        $("#skillQuestions"+i).val("");
        $("#skill"+i).val(0);  // because of no.of questions issue
    }
    $("#techReviewQuestions").val("");  //// because of no.of questions issue
    
    if($('#interviewType').val()=="Online") {
        var examType="T";   
        var values = $('#skillCategoryValue').val()
        var skillCategoryArry = [];    
        $("#skillCategoryValue :selected").each(function(){
            skillCategoryArry.push($(this).val()); 
        });  
    }
    else
    {
        var examType="S";   
        var values = $('#psychoskillCategoryValue').val()
        var skillCategoryArry = [];    
        $("#psychoskillCategoryValue :selected").each(function(){
            skillCategoryArry.push($(this).val()); 
        });   
    }
                    
                    
    var techReviewSeverity=$("#techReviewSeverity").val();
    //    var techReviewQuestions=$("#techReviewQuestions").val();
    //    if(techReviewQuestions=="")
    //        {
    //           $("e").html(" <b><font color='red'>Please Enter the No of Questions</font></b>");
    //            $("e").show().delay(4000).fadeOut();
    //            return false; 
    //        }
    // alert(skillCategoryArry)
    var url='.././Requirements/skillsQuestions.action?skillCategoryArry='+skillCategoryArry+'&techReviewSeverity='+techReviewSeverity+'&techExamType='+examType;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingDashboardPage').style.display = 'block';
        if (req.readyState == 4) {
            if (req.status == 200) {
                $('#loadingDashboardPage').hide();
                //  alert(req.responseText); 
                populateSkillsQuestion(req.responseText);
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateSkillsQuestion(response)
{
    var techReviewList=response.split("^");
    
    if(response.length>0){
        var count=0;
        for(var i=0;i<techReviewList.length-1;i++){   
            //alert(techReviewList[0])
            var Values=techReviewList[i].split("|");
            {  
               
                // alert("response")
                //  $("#skill"+i).val(Values[0]);
                //$("#labelSkill"+i).val(Values[2]);
                $("#labelSkill"+i).html("<b><font color='#56a5ec'>"+Values[2]+"</font>&nbsp;<font color='#ae9b9b'>Available Questions("+Values[0]+")</font></b>"); 
                $("#skillid"+i).val(Values[1]);
                $("#skillQuestions"+i).val(Values[0]);
                $("#revewSkill"+i).show();
           
            }
            
        }
   
    }
    else{
        var row = $("<tr />")
       
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'))
    }   
}

function checkQuestionsAvailble0()
{
    var questions= document.getElementById("skillQuestions0").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill0").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red' >Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill0").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill0").value=0;    
    }
      
  
}
function checkQuestionsAvailble1()
{
    var questions= document.getElementById("skillQuestions1").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill1").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
        //           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill1").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill1").value=0;    
    }
      
  
}
function checkQuestionsAvailble2()
{
    var questions= document.getElementById("skillQuestions2").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill2").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill2").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill2").value=0;    
    }
  
}
function checkQuestionsAvailble3()
{
    var questions= document.getElementById("skillQuestions3").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill3").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill3").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill3").value=0;    
    } 
  
}
function checkQuestionsAvailble4()
{
    var questions= document.getElementById("skillQuestions4").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill4").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill4").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill4").value=0;    
    } 
      
  
}
function checkQuestionsAvailble5()
{
    var questions= document.getElementById("skillQuestions5").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill5").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill5").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill5").value=0;    
    } 
      
  
}
function checkQuestionsAvailble6()
{
    var questions= document.getElementById("skillQuestions6").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill6").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill6").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    
    if(questionsEnter=="")  
    {
        document.getElementById("skill6").value=0;    
    } 
      
  
}
function checkQuestionsAvailble7()
{
    var questions= document.getElementById("skillQuestions7").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill7").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill7").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill7").value=0;    
    } 
      
  
}
function checkQuestionsAvailble8()
{
    var questions= document.getElementById("skillQuestions8").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill8").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
        //           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill8").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill8").value=0;    
    } 
      
  
}
function checkQuestionsAvailble9()
{
    var questions= document.getElementById("skillQuestions9").value;//$("#skillQuestions0").val();
    var questionsEnter= document.getElementById("skill9").value; //$("#skill0").val();
    //    var techReviewQuestions= $("#techReviewQuestions").val();
    //    alert(techReviewQuestions);
    
    if(Number(questionsEnter) > Number(questions))
    {
        //        alert(questionsEnter+"-->questionsEnter"); 
        //        alert(questions+"-->questions");
           
        $("e").html("<font color='red'>Questions Not Existed For the skills.</font>");
        $("e").show().delay(4000).fadeOut();
        $("#skill9").val(0);
        totalQuestionscount();
    }
    else
    {
        totalQuestionscount();   
      
    }
    if(questionsEnter=="")  
    {
        document.getElementById("skill9").value=0;    
    } 
      
  
}
function totalQuestionscount()
{
    var questionsEnter0= document.getElementById("skill0").value; 
    var questionsEnter1= document.getElementById("skill1").value; 
    var questionsEnter2= document.getElementById("skill2").value; 
    var questionsEnter3= document.getElementById("skill3").value; 
    var questionsEnter4= document.getElementById("skill4").value; 
    var questionsEnter5= document.getElementById("skill5").value; 
    var questionsEnter6= document.getElementById("skill6").value; 
    var questionsEnter7= document.getElementById("skill7").value; 
    var questionsEnter8= document.getElementById("skill8").value; 
    var questionsEnter9= document.getElementById("skill9").value; 
   
    $("#techReviewQuestions").val(Number(questionsEnter0)+Number(questionsEnter1)+Number(questionsEnter2)+Number(questionsEnter3)+Number(questionsEnter4)+Number(questionsEnter5)+Number(questionsEnter6)+Number(questionsEnter7)+Number(questionsEnter8)+Number(questionsEnter9));    
}
function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    return true;
}
      
function findPercentage(dividend,divisor)
{
    var percentage =(dividend / divisor)*100;
   
    //alert(percentage)
    return Math.round(percentage);
}
function saveExamResult(val,flag)
{
    //alert(flag)
    var contechId=  $('#contechId').val();
    var consultantId=  $('#consultantId').val();
    var requirementId=  $('#requirementId').val();
    var comments= $('#consultantComments').val();
    //alert(comments)
    if(comments=="")
    {
        $("e").html("<b><font color='red'>Please Give Comments</font></b>"); 
        $("e").show().delay(4000).fadeOut();
        return false;
    }
    var url='.././Requirements/onlineExamStatusSave.action?conTechReviewId='+contechId+"&examStatus="+val+"&consult_id="+consultantId+"&requirementId="+requirementId+"&cnslt_comments="+comments;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText);
                $("e").html("<b><font color='green'>"+req.responseText+"</font></b>");
                $("e").show().delay(4000).fadeOut();
                $("#examStatus").html("<b><font color='green'>"+val+"</font></b>");
                if(flag=="reviewSearch")
                {
                    searchTechReviews();    
                }
                else
                {
                    getSearchTechReviewList();        
                }
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}

function checkCharactersNotes(id){
    
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#notes_feedback").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#notes_feedback").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}


function doOnLoadTechReview() {
    var techReviewDate,reviewAlertDate,reviewStartDate,reviewEndDate,searchInterviewDate;
    //alert("babu chitti")            
    $('#techAlertContent').hide();
    $('#techReviewAlert').change(function(){
        if($(this).is(":checked"))
            $('#techAlertContent').fadeIn('slow');
        else
            $('#techAlertContent').fadeOut('slow');
    });
    
    
    techReviewDate = new dhtmlXCalendarObject(["interviewDate"]);
    // alert("hii1");
    techReviewDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    techReviewDate.setDateFormat("%m-%d-%Y %H:%i ");
   
    var today=new Date();
    techReviewDate.setSensitiveRange(today, null);
    
    reviewAlertDate = new dhtmlXCalendarObject(["reviewAlertDate"]);
    // alert("hii1");
    reviewAlertDate.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    reviewAlertDate.setDateFormat("%Y/%m/%d %H:%i");
    var todayDate=new Date();
    reviewAlertDate.setSensitiveRange(todayDate, null);

    
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth(); //January is 0!
    var yyyy = today.getFullYear();
    //alert(fromDate)
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
    mm=today.getMonth()+1;
    if(mm<10) {
        mm='0'+mm
    } 
    var dd=today.getDate();
    var dateValue=yyyy+'/'+mm+'/'+dd;
}
function doOnLoadTechReviewForward() {
    
   
    var myCalendar,myCalendar1;
    $('#techAlertContent').hide();
    $('#techReviewAlert').change(function(){
        if($(this).is(":checked"))
            $('#techAlertContent').fadeIn('slow');
        else
            $('#techAlertContent').fadeOut('slow');
    });
    myCalendar = new dhtmlXCalendarObject(["interviewDate"]);
    myCalendar.setSkin('omega');
    myCalendar.setDateFormat("%m-%d-%Y %H:%i");
  
    var today=new Date();
    myCalendar.setSensitiveRange(today, null);
   
    myCalendar.attachEvent("onClick", function setDate(date){
        // clearFieldsOfAlertMints();
        var date1 = date;//alert(startDate);
        var dd = date1.getDate();
        var mm = date1.getMonth()+1; //January is 0!
        var yyyy = date1.getFullYear();
        var hh  = date1.getHours();
        var ss  =  date1.getMinutes();
       // date1.setMinutes(date1.getMinutes-1);

        var hh1;
        var dd1;
        // alert(hh)
        
        var mm1,yyyy1;
        hh1=hh;
        //alert(hh)
        if(hh1<0)
        {
            hh1  = 23;
            //alert(mm)
            if(dd == 1 )
            {
                mm = mm-1;  
                if(mm==0)
                {
                    mm=12; 
                    yyyy =yyyy-1;
                    dd= 31;
                }
                else
                {
                    if((yyyy % 100 == 0&&yyyy % 400==0)||yyyy % 4 ==0){
                        Date._MD = new Array(31,29,31,30,31,30,31,31,30,31,30,31);
                    }
                    else{
                        Date._MD = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
                    }
  
                    mm=mm-1;
                    dd1=Date._MD[mm]; 
                    mm=mm+1;
                    dd = dd1;    

                }
           
            }
            else
            {
                dd = dd-1; 
            }
         
        }
        if(mm<10)
        {
            mm = "0"+mm;   
        }
        if(dd<10)
        {
            dd = "0"+dd;   
        }
           
        
        document.getElementById("reviewAlertDate").value = mm+"-"+dd+"-"+yyyy;
         
        timeAlert(hh1,ss)
       
        var startDate= document.getElementById("interviewDate").value;
        var res = new Date(startDate.split(" ",1));
        myCalendar1 = new dhtmlXCalendarObject(["reviewAlertDate"]);
        myCalendar1.setSkin('omega');
        myCalendar1.setDateFormat("%m-%d-%Y");
        myCalendar1.hideTime();
        myCalendar1.setSensitiveRange(today,res);
           myCalendar1.attachEvent("onClick", function setDate(date){
            //alert(date)
            // alert(res)
               var dd = date.getDate();
               var mm = date.getMonth()+1; //January is 0!
               var yyyy = date.getFullYear();
               if(mm<10)
        {
            mm = "0"+mm;   
        }
        if(dd<10)
        {
            dd = "0"+dd;   
        }
        var dateEn=   mm+"-"+dd+"-"+yyyy;
        var dd1 = res.getDate();
               var mm1 = res.getMonth()+1; //January is 0!
               var yyyy1 = res.getFullYear();
               if(mm<10)
        {
            mm1 = "0"+mm1;   
        }
        if(dd<10)
        {
            dd1 = "0"+dd1;   
        }
        var resEn=   mm1+"-"+dd1+"-"+yyyy1;
            var splitTaskStartDate = dateEn.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = resEn.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    //alert(difference)
    if(difference>0)
    {
             
        // alert("hi")
       
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
         timeAlert(60,60);
          $("#techReviewAlertHours").val(0);
    $("#techReviewAlertMints").val(0);
    }
    if(difference==0)
        {
            timeAlert(hh1,ss); 
        }
        });
    });
}

function setAlertDateForOnline()
{
   
    var myCalendar;
 
    myCalendar = new dhtmlXCalendarObject(["reviewAlertDate"]);
    myCalendar.setSkin('omega');
    myCalendar.setDateFormat("%m-%d-%Y");
     myCalendar.hideTime(); 
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    var hh  = today.getHours();
    var ss  = today.getMinutes();
    var hh1;
    var dd1;
    //alert(fromDate)
   
    var myDate = new Date(today) // your date object
    myDate.setHours(myDate.getHours() + 24)
    today  = myDate;
    // alert(today);
    
    
    var alertDate=formatDate(today);
    document.getElementById("reviewAlertDate").value = alertDate;
    timeAlert(hh,ss);
    var currentDate = new Date();
    myCalendar.setSensitiveRange(currentDate,today);
         myCalendar.attachEvent("onClick", function setDate(date){
           
               var dd = date.getDate();
               var mm = date.getMonth()+1; //January is 0!
               var yyyy = date.getFullYear();
               if(mm<10)
        {
            mm = "0"+mm;   
        }
        if(dd<10)
        {
            dd = "0"+dd;   
        }
        var dateEn=   mm+"-"+dd+"-"+yyyy;
        var dd1 = today.getDate();
               var mm1 = today.getMonth()+1; //January is 0!
               var yyyy1 = today.getFullYear();
               if(mm<10)
        {
            mm1 = "0"+mm1;   
        }
        if(dd<10)
        {
            dd1 = "0"+dd1;   
        }
        var resEn=   mm1+"-"+dd1+"-"+yyyy1;
            var splitTaskStartDate = dateEn.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = resEn.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    //alert(difference)
    if(difference>0)
    {
             
        // alert("hi")
       
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
          //timeChangeAlert(hh,ss);
       timeAlert(60,60);
          $("#techReviewAlertHours").val(0);
    $("#techReviewAlertMints").val(0);
    }
    if(difference==0)
        {
            timeAlert(hh,ss);
        }
        }); 
             
        
         
}

function formatDate(date) {
    var d = new Date(date),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear(),
    hh = d.getHours(),
    mm = d.getMinutes();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [month, day, year].join('-');
}

function timeAlert(hours,mints)
{
    $("#techReviewAlertHours").empty();
    $("#techReviewAlertMints").empty();
    
    
     
     
    var min = 0,limitHH=0,limitMM=0,
    max = 23,
    select = document.getElementById('techReviewAlertHours');
   
    for (var i = min; i<=max && i <=hours; i++){
        var opt = document.createElement('option');
        if(i < 10)
        {
                          
            opt.value = i;  
            opt.innerHTML = "0"+i;
        }
        else
        {
            opt.value = i;
            opt.innerHTML = i;
        }     
                    
        limitHH= limitHH+i;          
        select.appendChild(opt);
    
    }
    var minMinu=0,maxMinu=59;
    select = document.getElementById('techReviewAlertMints');
   
    for (var i = minMinu; i<=maxMinu && i <=mints; i=i+1){
        var opt = document.createElement('option');
                  
        if(i < 10)
        {
            opt.value = i;  
            opt.innerHTML = "0"+i;
        }
        else
        {
            opt.value = i;
            opt.innerHTML = i;
        }
        //opt.innerHTML = i;
        limitMM =limitMM+i;
        select.appendChild(opt);
    }
        
    document.getElementById("techReviewAlertHours").value  = hours;    
    document.getElementById("techReviewAlertMints").value  = mints;    
}