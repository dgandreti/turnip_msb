/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function stateChange()
{
    var id=document.getElementById('RequirementCountry').value;
    var url='../users/general/getState.action?countryId='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            
            stateChanging(req.responseText);
        //setPrimaryAssigned(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function stateChanging(data){
    var RequirementState = document.getElementById("RequirementState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#RequirementState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}

function initRequest(url) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
    if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }    
}

function RequirementValidations(skillCategoryArry){
    var fromValue=$("#RequirementFrom").val();
    // var toValue=$("#RequirementTo").val();
    var Exp=$("#RequirementExp").val();
    var Years=$("#RequirementYears").val();
    var status=$("#RequirementStatus").val();
    var name=$("#RequirementName").val();
    var noofresources=$("#RequirementNoofResources").val();
    var skill=$("#RequirementSkills").val();
    //  var comment=$("#RequirementComments").val();
    //  var desc=$("#RequirementDescription").val();
    var targetrate=$("#RequirementTargetRate").val();
    var duration=$("#RequirementDuration").val();
    var contact1=$("#RequirementContact1").val();
    var reason=$("#RequirementReason").val();
    var presales1=$("#RequirementPresales1").val();
    var contact2=$("#RequirementContact2").val();
    var taxterm=$("#RequirementTaxTerm").val();
    var presales2=$("#RequirementPresales2").val();
    var practice=$("#RequirementPractice").val();
    var location=$("#RequirementLocation").val();
    var contactno=$("#RequirementContactNo").val();
    var state=$("#RequirementState").val();
    var country=$("#RequirementCountry").val();
    var address=$("#RequirementAddress").val();
    var responsibilities=$("#RequirementResponse").val();
    var jobdesc=$("#RequirementJobdesc").val();
    var billingContact=$("#billingContact").val();
    var requirementDurationDescriptor=$("#requirementDurationDescriptor").val();
    var requirementMaxRate=$("#requirementMaxRate").val();
     if(skillCategoryArry==""){
       
        $("editrequirementerror").html(" <b><font color='red'>Skill field is required</font></b>");
        $("#skillCategoryValue").css("border", "1px solid red");
        return false;
    } else
    {
        $("editrequirementerror").html("");
        $("#skillCategoryValue").css("border", "1px solid green");
    }
    if(name==""||name.length>50){
        
        $("editrequirementerror").html(" <b><font color='red'>enter valid requirement name</font></b>");
        $("#RequirementName").css("border", "1px solid red");
        return false;
    }
    if(fromValue==""){
       
        $("editrequirementerror").html(" <b><font color='red'>fromdate field is required</font></b>");
        $("#RequirementFrom").css("border", "1px solid red");
        return false;
    }
    if(taxterm=="DF"){
        $("editrequirementerror").html(" <b><font color='red'>Type field is required</font></b>");
        $("#RequirementTaxTerm").css("border", "1px solid red");
        return false;
    }
    if(contact1=="-1"){
        
        $("editrequirementerror").html(" <b><font color='red'>Approver field is required</font></b>");
        $("#RequirementContact1").css("border", "1px solid red");
        return false;
    }
    
    if(noofresources==""||noofresources.length>9){
       
        $("editrequirementerror").html(" <b><font color='red'>enter valid noOfResources field value</font></b>");
        $("#RequirementNoofResources").css("border", "1px solid red");
        return false;
    }
    if(targetrate==""||targetrate.length>10){
      
        $("editrequirementerror").html(" <b><font color='red'>enter valid minimum rate</font></b>");
        $("#RequirementTargetRate").css("border", "1px solid red");
        return false;
    } 
    if(requirementMaxRate==""||requirementMaxRate.length>10){
      
        $("editrequirementerror").html(" <b><font color='red'>enter valid maximum rate</font></b>");
        $("#requirementMaxRate").css("border", "1px solid red");
        return false;
    }
    // alert("min rate---->"+targetrate +" max rate---->"+requirementMaxRate);
    var diff=requirementMaxRate-targetrate;
    if(diff<0)
    {
        $("editrequirementerror").html("<b><font color='red'>Maximum rate should be Greater than Minimum rate</font></b>");
        $("#requirementMaxRate").css("border","1px solid red");
        return false;
    } 
    if(contact2=="-1"){
   
        $("editrequirementerror").html(" <b><font color='red'>Requisitioner field is required</font></b>");
        $("#RequirementContact2").css("border", "1px solid red");
        return false;
    }
    if(taxterm!="PE") {
        if(duration==""||duration.length>10){
        
            $("editrequirementerror").html(" <b><font color='red'>enter valid duration</font></b>");
            $("#RequirementDuration").css("border", "1px solid red");
            return false;
        }
    }
    
if(taxterm!="PE") {
       
   
    if(requirementDurationDescriptor=="-1")
    {
        $("editrequirementerror").html("<b><font color='red'>enter hrs/weeks/months</font></b>");
        $("#requirementDurationDescriptor").css("border","1px solid red");
        return false;
    }
}
 
if(location=="DF"){
    $("editrequirementerror").html(" <b><font color='red'>location field is required</font></b>");
    $("#RequirementLocation").css("border", "1px solid red");
    return false;
}
if(Exp== -1){
        
    $("editrequirementerror").html(" <b><font color='red'>type field is required</font></b>");
    $("#RequirementExp").css("border", "1px solid red");
    return false;
}
if(Years==-1){
        
    $("editrequirementerror").html(" <b><font color='red'>Experience field is required</font></b>");
    $("#RequirementYears").css("border", "1px solid red");
    return false;
}
if(billingContact=="DF"){
    $("editrequirementerror").html(" <b><font color='red'>billingContact field is required</font></b>");
    $("#billingContact").css("border", "1px solid red");
    return false;
}
if(responsibilities==""){
    $("editrequirementerror").html(" <b><font color='red'>responsibilities field is required</font></b>");
    $("#RequirementResponse").css("border", "1px solid red");
    return false;
}
if(jobdesc==""){
    $("editrequirementerror").html(" <b><font color='red'>job desc field is required</font></b>");
    $("#RequirementJobdesc").css("border", "1px solid red");
    return false;
}
if(skill==""){
       
    $("editrequirementerror").html(" <b><font color='red'>skill set field is required</font></b>");
    $("#RequirementSkills").css("border", "1px solid red");
    return false;
}
   
   
         

  
    
    
    
     
   
    
if(reason=="DF"){
       
    $("editrequirementerror").html(" <b><font color='red'> reason field is required</font></b>");
    $("#RequirementReason").css("border", "1px solid red");
    return false;
}
    
     
  
    
if(status=="DF"){
     
    $("editrequirementerror").html(" <b><font color='red'>status field is required</font></b>");
    $("#RequirementStatus").css("border", "1px solid red");
    return false;
}
 
   
if(practice=="-1"){
        
    $("editrequirementerror").html(" <b><font color='red'>practice field is required</font></b>");
    $("#RequirementPractice").css("border", "1px solid red");
    return false;
}
    
   
   
if(contactno==""){
    $("editrequirementerror").html(" <b><font color='red'>contact number field is required</font></b>");
    $("#RequirementContactNo").css("border", "1px solid red");
    return false;
}
    
if(state=="-1"){
    $("editrequirementerror").html(" <b><font color='red'>state field is required</font></b>");
    $("#RequirementState").css("border", "1px solid red");
    return false;
}
if(country=="-1"){
    $("editrequirementerror").html(" <b><font color='red'>Country field is required</font></b>");
    $("#RequirementCountry").css("border", "1px solid red");
    return false;
}
if(address==""){
    $("editrequirementerror").html(" <b><font color='red'>address field is required</font></b>");
    $("#RequirementAddress").css("border", "1px solid red");
    return false;
}
  
   
    
//    if(desc==""){
//       
//        $("editrequirementerror").html(" <b><font color='red'>description field is required</font></b>");
//        $("#RequirementDescription").css("border", "1px solid red");
//        return false;
//    }
    
//    if(comment==""){
//      
//        $("editrequirementerror").html(" <b><font color='red'>comments field is required</font></b>");
//        $("#RequirementComments").css("border", "1px solid red");
//        return false;
//    }
   
//    var res = fromValue.split(" ");
//    fromValue=res[0];
//    var res1 = toValue.split(" ");
//    toValue=res1[0];
//    if (Date.parse(fromValue) >= Date.parse(toValue)) {
//        $("editrequirementerror").html(" <b><font color='red'>Invalid Date Range!\nFrrom Date cannot be after To Date!</font></b>");
//        $("#RequirementFrom").css("border", "1px solid red");
//        $("#RequirementTo").css("border", "1px solid red");
//
//        return false;
//    }
//    else{
$("editrequirementerror").html("");
$("#RequirementFrom").css("border", "1px solid #3BB9FF");
//$("#RequirementComments").css("border", "1px solid #3BB9FF");
// $("#RequirementDescription").css("border", "1px solid #3BB9FF");
$("#RequirementSkills").css("border", "1px solid #3BB9FF");
$("#RequirementJobdesc").css("border", "1px solid #3BB9FF");
$("#RequirementResponse").css("border", "1px solid #3BB9FF");
$("#RequirementAddress").css("border", "1px solid #3BB9FF");
$("#RequirementCountry").css("border", "1px solid #3BB9FF");
$("#RequirementState").css("border", "1px solid #3BB9FF");
$("#RequirementContactNo").css("border", "1px solid #3BB9FF");
$("#RequirementLocation").css("border", "1px solid #3BB9FF");
$("#RequirementPractice").css("border", "1px solid #3BB9FF");
$("#RequirementExp").css("border", "1px solid #3BB9FF");
$("#RequirementDuration").css("border", "1px solid #3BB9FF");
$("#RequirementStatus").css("border", "1px solid #3BB9FF");
$("#RequirementPresales2").css("border", "1px solid #3BB9FF");
$("#RequirementPresales1").css("border", "1px solid #3BB9FF");
$("#RequirementTargetRate").css("border", "1px solid #3BB9FF");
$("#RequirementNoofResources").css("border", "1px solid #3BB9FF");
$("#RequirementTaxTerm").css("border", "1px solid #3BB9FF");
$("#RequirementReason").css("border", "1px solid #3BB9FF");
$("#RequirementTo").css("border", "1px solid #3BB9FF");
$("#RequirementName").css("border", "1px solid #3BB9FF");
$("#RequirementContact2").css("border", "1px solid #3BB9FF");
$("#RequirementContact1").css("border", "1px solid #3BB9FF");
$("#billingContact").css("border","1px solid #3BB9FF");
$("#requirementDurationDescriptor").css("border","1px solid #3BB9FF");
$("#requirementMaxRate").css("border","1px solid #3BB9FF"); 
    
return true;
//    }
  
};

function addRequirement()
{
    var RequirementFrom= $("#RequirementFrom").val();
    //   var RequirementTo= $("#RequirementTo").val();
    var RequirementTargetRate=$("#RequirementTargetRate").val();
    var RequirementDuration=$("#RequirementDuration").val();
    var RequirementContact1=$("#RequirementContact1").val();
    var RequirementReason=$("#RequirementReason").val();
    var RequirementPresales1=$("#RequirementPresales1").val();
    var RequirementYears=$("#RequirementYears").val();
    var RequirementType=$("#RequirementType").val();
    var RequirementContact2=$("#RequirementContact2").val();
    var RequirementTaxTerm=$("#RequirementTaxTerm").val();
    var RequirementPresales2=$("#RequirementPresales2").val();
    //var RequirementPractice=$("#RequirementPractice").val();
    var RequirementNoofResources=$("#RequirementNoofResources").val();
    var RequirementName=$("#RequirementName").val();
    var RequirementStatus=$("#RequirementStatus").val();
    var RequirementLocation=$("#RequirementLocation").val();
     var billingtype=$("#billingtype").val();
    
    //    var RequirementResponse=$("#RequirementResponse").val();
    //    var RequirementJobdesc=$("#RequirementJobdesc").val();
    //    var RequirementPreferredSkills=$("#RequirementPreferredSkills").val();
    //    var RequirementSkills=$("#RequirementSkills").val();
    //    var RequirementDescription=$("#RequirementDescription").val();
    //    var RequirementComments=$("#RequirementComments").val();
    var responsibilitiesText = document.getElementById("RequirementResponse").value;
    responsibilitiesText = responsibilitiesText.replace(/\r?\n/g, '<br/>');
    var RequirementResponse=document.getElementById("RequirementResponse").innerHTML = responsibilitiesText;
    //alert(responsibilities);
    
    var jobDescText = document.getElementById("RequirementJobdesc").value;
    jobDescText = jobDescText.replace(/\r?\n/g, '<br/>');
    var RequirementJobdesc=document.getElementById("RequirementJobdesc").innerHTML = jobDescText;   
    //var jobdesc=$("#RequirementJobdesc").val();

//    var prefferdSkillsText = document.getElementById("RequirementPreferredSkills").value;
//    prefferdSkillsText = prefferdSkillsText.replace(/\r?\n/g, '<br/>');
//    var RequirementPreferredSkills=document.getElementById("RequirementPreferredSkills").innerHTML = prefferdSkillsText;  
//    //var preferredSkills=$("#RequirementPreferredSkills").val();
//
//    var skillsText = document.getElementById("RequirementSkills").value;
//    skillsText = skillsText.replace(/\r?\n/g, '<br/>');
//    var RequirementSkills=document.getElementById("RequirementSkills").innerHTML = skillsText;
    //var skill=$("#RequirementSkills").val();
    
     var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).val()); 
    });
    
     var preSkillCategoryArry = [];    
    $("#preSkillCategoryValue :selected").each(function(){
        preSkillCategoryArry.push($(this).val()); 
    });

    var commentsText = document.getElementById("RequirementComments").value;
    commentsText = commentsText.replace(/\r?\n/g, '<br/>');
    var RequirementComments=document.getElementById("RequirementComments").innerHTML = commentsText;
    
    var RequirementDescription=$("#RequirementDescription").val();
    var accountSearchID=$("#accountSearchID").val();
    var billingContact=$("#billingContact").val();
    var requirementDurationDescriptor=$("#requirementDurationDescriptor").val();
    var reqCategoryValue=$("#reqCategoryValue").val();
    var requirementMaxRate=$("#requirementMaxRate").val();
    // alert(reqCategoryValue);
    if(RequirementValidations(skillCategoryArry)){
        var url='../addRequirementDetails.action?RequirementFrom='+RequirementFrom+
        
        '&RequirementTargetRate='+RequirementTargetRate+
        '&RequirementDuration='+RequirementDuration+
        '&RequirementContact1='+RequirementContact1+
        '&RequirementReason='+RequirementReason+
        '&RequirementPresales1='+RequirementPresales1+
        '&RequirementYears='+RequirementYears+
        '&RequirementType='+RequirementType+
        '&RequirementContact2='+RequirementContact2+
        '&RequirementTaxTerm='+RequirementTaxTerm+
        '&RequirementPresales2='+RequirementPresales2+
        //'&RequirementPractice='+RequirementPractice+
        '&RequirementNoofResources='+RequirementNoofResources+
        '&RequirementName='+RequirementName+
        '&RequirementStatus='+RequirementStatus+
        '&RequirementLocation='+RequirementLocation+
        // '&RequirementContactNo='+RequirementContactNo+
        //'&RequirementCountry='+RequirementCountry+
        //'&RequirementState='+RequirementState+
        //   '&RequirementAddress='+RequirementAddress+
        '&RequirementResponse='+RequirementResponse+
        '&RequirementJobdesc='+RequirementJobdesc+
        '&preSkillCategoryArry='+preSkillCategoryArry+
        '&skillCategoryArry='+skillCategoryArry+
        '&RequirementDescription='+RequirementDescription+
        '&RequirementComments='+RequirementComments+
        '&reqCategoryValue='+reqCategoryValue+
        '&requirementDurationDescriptor='+requirementDurationDescriptor+
        '&accountSearchID='+accountSearchID+'&billingContact='+billingContact+
        '&requirementMaxRate='+requirementMaxRate+
         '&billingtype='+billingtype;

 
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
            
                if(req.responseText==0){
                    $("editrequirementerror").html(" <b><font color='red'>No requirement Added</font></b>");
                }else
                {
                    $("editrequirementerror").html(" <b><font color='green'>Requirement successfully Added</font></b>");
                    requirementClearValidation();
                }
            //setPrimaryAssigned(req.responseText);
            } 
        }
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}
var myCalendar,myCalendar1;
function doOnloadEditRequirement()
{
    
    myCalendar1 = new dhtmlXCalendarObject(["RequirementFrom","RequirementTo"]);
    // alert("hii1");
    myCalendar1.setSkin('omega');
    // alert("hii2");
    myCalendar1.setDateFormat("%m-%d-%Y");
    vednorAccessTime=new dhtmlXCalendarObject(["accessTime"]);
    vednorAccessTime.setSkin('omega');

    vednorAccessTime.setDateFormat("%m-%d-%Y %H:%i");
    var tDate=new Date();
    vednorAccessTime.setSensitiveRange(tDate, null);
    disableFields();
}

var requirementsearch,requirementAdd;
function doOnLoadRequirement() {
                
    
    requirementsearch = new dhtmlXCalendarObject(["year_start","year_end"]);
    requirementsearch.setSkin('omega');
    requirementsearch.setDateFormat("%m-%d-%Y");
                
    requirementAdd = new dhtmlXCalendarObject(["RequirementFrom","RequirementTo"]);
    requirementAdd.setSkin('omega');
    //leaveeditcalender.setDateFormat("%Y/%m/%d");
    
    requirementAdd.setDateFormat("%m-%d-%Y");
    var todayDate=new Date();
    requirementAdd.setSensitiveRange(todayDate, null);
    vednorAccessTime=new dhtmlXCalendarObject(["accessTime"]);
    vednorAccessTime.setSkin('omega');

    vednorAccessTime.setDateFormat("%Y/%m/%d %h:%i");            
    $("#RequirementFrom").mask("99-99-9999");
    
    $("#RequirementTo").mask("99-99-9999");
    // requirementClearValidation();
    disableFields()
    doReqOnLoad();
}

var myCalendar,myCalendar1;
//,"docdatepickerfrom1","docdatepicker1"
function doReqOnLoad() {
                
    myCalendar1 = new dhtmlXCalendarObject(["startDate","endDate"]);
    myCalendar1.setSkin('omega');
    myCalendar1.setDateFormat("%Y/%m/%d");
                
    // default date code start from here
                
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
                
    //var from = yyyy+'/'+mm+'/01';
    var from = mm+'-'+dd+'-'+yyyy;
                
    mm=today.getMonth()+1;
    if(mm<10) {
        mm='0'+mm
    } 
    dd=today.getDate()+7;
    var to = mm+'/'+dd+'/'+yyyy;
    var odd=today.getDate()+1;
    var overlayDate=yyyy+'-'+mm+'-'+odd;
    document.getElementById("RequirementFrom").value=from;
    // document.getElementById("RequirementTo").value=to;
    document.getElementById("startDate").value=overlayDate;
    document.getElementById("endDate").value=overlayDate;
}


function removeErrorMessages(){
    $("#charNum").html(" ");
    $("#ResponsecharNum").html(" ");
    $("#JobcharNum").html(" ");
    $("#SkillcharNum").html(" ");
    $("#PreferredSkillcharNum").html("");
    $("#CommcharNum").html("");
    $("editrequirementerror").html("");
    $("#RequirementName").css("border", "1px solid #53C2FF");
    $("#RequirementFrom").css("border", "1px solid #53C2FF");
    $("#requirementMaxRate").css("border","1px solid #3BB9FF");
    //  $("#RequirementTo").css("border", "1px solid #53C2FF");
    $("#RequirementTargetRate").css("border", "1px solid #53C2FF");
    $("#RequirementExp").css("border", "1px solid #53C2FF");
    $("#RequirementYears").css("border", "1px solid #53C2FF");
    $("#RequirementContact1").css("border", "1px solid #53C2FF");
    $("#RequirementReason").css("border", "1px solid #53C2FF");
    $("#RequirementPresales1").css("border", "1px solid #53C2FF");
    $("#RequirementPractice").css("border", "1px solid #53C2FF");
    $("#RequirementContact2").css("border", "1px solid #53C2FF");
    $("#RequirementTaxTerm").css("border", "1px solid #53C2FF");
    $("#RequirementPresales2").css("border", "1px solid #53C2FF");
    $("#RequirementDuration").css("border", "1px solid #53C2FF");
    $("#RequirementNoofResources").css("border", "1px solid #53C2FF");
    $("#RequirementStatus").css("border", "1px solid #53C2FF");
    $("#RequirementLocation").css("border", "1px solid #53C2FF");
    $("#RequirementContactNo").css("border", "1px solid #53C2FF");
    $("#RequirementCountry").css("border", "1px solid #53C2FF");
    $("#RequirementState").css("border", "1px solid #53C2FF");
    $("#RequirementAddress").css("border", "1px solid #ccc");
    $("#RequirementResponse").css("border", "1px solid #ccc");
    $("#RequirementJobdesc").css("border", "1px solid #ccc");
    $("#RequirementSkills").css("border", "1px solid #ccc");
    $("#skillCategoryValue").css("border", "1px solid #ccc");
    
    $("#RequirementDescription").css("border", "1px solid #ccc");
    $("#RequirementComments").css("border", "1px solid #ccc");
    $("#RequirementPreferredSkills").css("border","1px solid #ccc");
    $("#requirementDurationDescriptor").css("border","1px solid #ccc");
    
    $("#billingContact").css("border","1px solid #ccc");
     
}
function editRequirementDateValidation()
{

    document.getElementById('RequirementFrom').value = "";
    // document.getElementById('RequirementTo').value = "";
    return false;
};

function updaterequirements(){
    initSessionTimer();
    var reqId=$("#RequirementId").val();
    var fromValue=$("#RequirementFrom").val();
    //  var toValue=$("#RequirementTo").val();
    var Exp=$("#RequirementYears").val();
    var status=$("#RequirementStatus").val();
    var name=$("#RequirementName").val();
    var noofresources=$("#RequirementNoofResources").val();
    
    //   var desc=$("#RequirementDescription").val();
    
    var targetrate=$("#RequirementTargetRate").val();
    var duration=$("#RequirementDuration").val();
    var contact1=$("#RequirementContact1").val();
    //var reason=$("#RequirementReason").val();
    // var presales1=$("#RequirementPresales1").val();
    var contact2=$("#RequirementContact2").val();
    var taxterm=$("#RequirementTaxTerm").val();
    // var presales2=$("#RequirementPresales2").val();
    //var practice=$("#RequirementPractice").val();
    var location=$("#RequirementLocation").val();
    //var contactno=$("#RequirementContactNo").val();
    //var state=$("#RequirementState").val();
    //var country=$("#RequirementCountry").val();
    //  var address=$("#RequirementAddress").val();
    var billingContact=$("#billingContact").val();
    
    var requirementDurationDescriptor=$("#requirementDurationDescriptor").val();
    var responsibilitiesText = document.getElementById("RequirementResponse").value;
    responsibilitiesText = responsibilitiesText.replace(/\r?\n/g, '<br/>');
    var responsibilities=document.getElementById("RequirementResponse").innerHTML = responsibilitiesText;
    //alert(responsibilities);
    
    var jobDescText = document.getElementById("RequirementJobdesc").value;
    jobDescText = jobDescText.replace(/\r?\n/g, '<br/>');
    var jobdesc=document.getElementById("RequirementJobdesc").innerHTML = jobDescText;   
    //var jobdesc=$("#RequirementJobdesc").val();

//    var prefferdSkillsText = document.getElementById("RequirementPreferredSkills").value;
//    prefferdSkillsText = prefferdSkillsText.replace(/\r?\n/g, '<br/>');
//    var preferredSkills=document.getElementById("RequirementPreferredSkills").innerHTML = prefferdSkillsText;  
//    //var preferredSkills=$("#RequirementPreferredSkills").val();
//
//    var skillsText = document.getElementById("RequirementSkills").value;
//    skillsText = skillsText.replace(/\r?\n/g, '<br/>');
//    var skill=document.getElementById("RequirementSkills").innerHTML = skillsText;
    //var skill=$("#RequirementSkills").val();
    
    var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).val()); 
    });
    
     var preSkillCategoryArry = [];    
    $("#preSkillCategoryValue :selected").each(function(){
        preSkillCategoryArry.push($(this).val()); 
    });


    var commentsText = document.getElementById("RequirementComments").value;
    commentsText = commentsText.replace(/\r?\n/g, '<br/>');
    var comment=document.getElementById("RequirementComments").innerHTML = commentsText;
    var requirementMaxRate=document.getElementById("requirementMaxRate").value;
    var billingtype=$("#billingtype").val();
    
    
    //    var responsibilities=$("#RequirementResponse").val();
    //    var jobdesc=$("#RequirementJobdesc").val();
    //    var preferredSkills=$("#RequirementPreferredSkills").val();
    //    var skill=$("#RequirementSkills").val();
    //    var comment=$("#RequirementComments").val();
    //alert("hello")
    if(RequirementValidations(skillCategoryArry)){

     
    
        var url='../Requirements/updateRequirements.action?RequirementId='+reqId+'&RequirementFrom='+fromValue+
        '&RequirementStatus='+status+'&RequirementName='+name+'&RequirementNoofResources='+noofresources+'&skillCategoryArry='+skillCategoryArry+
        '&RequirementComments='+comment+'&RequirementTargetRate='+targetrate+'&RequirementDuration='+
        duration+'&RequirementContact1='+contact1+
        '&RequirementContact2='+contact2+'&RequirementTaxTerm='+taxterm+
        '&RequirementLocation='+location+
        '&RequirementResponse='+responsibilities+'&RequirementJobdesc='
        +jobdesc+'&preSkillCategoryArry='+preSkillCategoryArry+'&RequirementYears='+Exp+'&billingContact='+billingContact+'&requirementDurationDescriptor='+requirementDurationDescriptor+'&requirementMaxRate='+requirementMaxRate+'&billingtype='+billingtype;
        //  alert(url)
        var req=initRequest(url);
        req.onreadystatechange = function() {
       
            if (req.readyState == 4 && req.status == 200) {
                $("editrequirementerror").html(" <b><font color='green'>Record updated successfully </font></b>");
                  
            }
            else
            {
                $("editrequirementerror").html(" <b><font color='red'>Record not updated</font></b>");
            }
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}


function ResponseCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 1000){
            el.val( el.val().substr(0, 1000) );
        } else {
            $("#ResponsecharNum").text(1000-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==1000)
        {
            $("#ResponsecharNum").text(' Cannot enter  more than 1000 Characters .');    
        }
        
    })
    return false;
};
function JobCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 1500){
            el.val( el.val().substr(0, 1500) );
        } else {
            $("#JobcharNum").text(1500-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==1500)
        {
            $("#JobcharNum").text(' Cannot enter  more than 1500 Characters .');    
        }
        
    })
    return false;
};
function SkillCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 500){
            el.val( el.val().substr(0, 500) );
        } else {
            $("#SkillcharNum").text(500-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==500)
        {
            $("#SkillcharNum").text(' Cannot enter  more than 500 Characters .');    
        }
        
    })
    return false;
};
function PreferredSkillCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 1000){
            el.val( el.val().substr(0, 1000) );
        } else {
            $("#PreferredSkillcharNum").text(1000-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==1000)
        {
            $("#PreferredSkillcharNum").text(' Cannot enter  more than 1000 Characters .');    
        }
        
    })
    return false;
};
function CommentsCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 1000){
            el.val( el.val().substr(0, 1000) );
        } else {
            $("#CommcharNum").text(1000-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==1000)
        {
            $("#CommcharNum").text(' Cannot enter  more than 1000 Characters .');    
        }
        
    })
    return false;
};
function acceptNumbers(evt){
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
        $("editrequirementerror").html(" <b><font color='green'>enter only numbers</font></b>");
        return false;
    }
    else
    {
                    
        $("editrequirementerror").html("");
        return true;
    }
};

function requirementClearValidation()
{
    document.getElementById('RequirementName').value= "";
    document.getElementById('RequirementFrom').value = "";
    document.getElementById('RequirementTaxTerm').selectedIndex = "0";
    document.getElementById('RequirementContact1').selectedIndex = "0";
    
    document.getElementById('RequirementNoofResources').value = "";
    document.getElementById('RequirementTargetRate').value = "";
    document.getElementById('RequirementLocation').selectedIndex = "0";
    document.getElementById('RequirementContact2').selectedIndex = "0"; 
    document.getElementById('RequirementDuration').value ="";
    document.getElementById('requirementDurationDescriptor').selectedIndex = "0";
    
    document.getElementById('RequirementYears').selectedIndex = "0";
    document.getElementById('billingContact').selectedIndex = "0";
    document.getElementById('reqCategoryValue').selectedIndex = "0";
     
    
    document.getElementById('RequirementResponse').value = "";
    document.getElementById('RequirementJobdesc').value = "";
    document.getElementById('RequirementPreferredSkills').value = "";
    document.getElementById('RequirementSkills').value = "";
    //    document.getElementById('RequirementDescription').value = "";
    document.getElementById('RequirementComments').value= "";
    document.getElementById('requirementMaxRate').value= ""; 
}
function RequirementMaxRate(evt){
    //alert("heelo")
    var  maxRate=document.getElementById("requirementMaxRate").value;
    var rate=(maxRate.toString()).length;
   
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        $("editrequirementerror").html(" <b><font color='red'>enter only numbers</font></b>");     
        if(iKeyCode == 8 )
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
    else if( rate >= 3)
    {
            
        if(iKeyCode == 8)    
        {
            return true; 
        }
        else
        {
            return false;       
        }
            
           
    }
   
        
    else 
    {
                    
        $("editrequirementerror").html("");
        return true;
    }
};
function RequirementMinRate(evt){
    var  minRate=document.getElementById("RequirementTargetRate").value;
    var rate=(minRate.toString()).length;
   
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        $("editrequirementerror").html(" <b><font color='red'>enter only numbers</font></b>");     
        if(iKeyCode == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
    else if( rate >= 3)
    {
            
        if(iKeyCode == 8)    
        {
            return true; 
        }
        else
        {
            return false;       
        }
            
           
    }
   
        
    else 
    {
                    
        $("editrequirementerror").html("");
        return true;
    }
    
};
function disableFields()
{
    //alert("hello")
    var val=document.getElementById("RequirementTaxTerm").value;
    var userType=document.getElementById("userType").value;
    if(val=="PE")
    {
        document.getElementById("requirementDurationDescriptor").disabled = true;    
        document.getElementById("RequirementDuration").disabled = true;       
    }
    else
    {
        document.getElementById("requirementDurationDescriptor").disabled = false;
        document.getElementById("RequirementDuration").disabled = false;       
    }
    if(userType=="VC"){
        document.getElementById("requirementDurationDescriptor").disabled = true;
    }
}

function durationValidation(evt)
{
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode!= 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
       
        $("editrequirementerror").html(" <b><font color='red'>enter only numbers</font></b>");  
        $("editrequirementerror").show().delay(4000).fadeOut();
        
        if(iKeyCode == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
}