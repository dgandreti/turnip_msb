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
    var qualification = $("#requirementQualification").val();
    qualification = qualification.replace(/\r?\n/g, '<br/>');
    var requirementQualification = document.getElementById("requirementQualification").innerHTML = qualification;
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
        '&billingtype='+billingtype+
        '&requirementQualification='+requirementQualification;

 
        var req=initRequest(url);
        $('html,body').scrollTop(0);
        req.onreadystatechange = function() {
            document.getElementById('loadingAddReq').style.display = 'block';
            if (req.readyState == 4 && req.status == 200) {
                $('#loadingAddReq').hide();
                if(req.responseText==0){
                    $("editrequirementerror").html(" <font color='red'>Requirement is not Added</font>");
                }else
                {
                     requirementClearValidation();
                    $("editrequirementerror").html(" <font color='green'>Requirement Added Successfully</font>");
                   
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
    vendorReqEdit();
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
    //alert("hello")
    $("#charNum").html("");
    $("#ResponsecharNum").html("");
    $("#req_Qualification").html("");
    $("#JobcharNum").html("");
    $("#SkillcharNum").html("");
    $("#PreferredSkillcharNum").html("");
    $("#CommcharNum").html("");
    $("editrequirementerror").html("");
    $("#RequirementName").css("border", "1px solid #53C2FF");
    $("#RequirementFrom").css("border", "1px solid #53C2FF");
    $("#RequirementTaxTerm").css("border", "1px solid #53C2FF");
    $("#RequirementContact1").css("border", "1px solid #53C2FF");
    $("#RequirementNoofResources").css("border", "1px solid #53C2FF");
     $("#RequirementTargetRate").css("border", "1px solid #53C2FF");
     
    $("#requirementMaxRate").css("border","1px solid #3BB9FF");
    $("#RequirementContact2").css("border", "1px solid #53C2FF");
    $("#RequirementDuration").css("border", "1px solid #53C2FF");
    $("#requirementDurationDescriptor").css("border","1px solid #ccc");
     $("#billingContact").css("border","1px solid #ccc");
      $("#RequirementLocation").css("border", "1px solid #53C2FF");
      $("#RequirementYears").css("border", "1px solid #53C2FF");
       $("#requirementQualification").css("border","1px solid #ccc");
        $("#RequirementJobdesc").css("border", "1px solid #ccc");
        $("#RequirementResponse").css("border", "1px solid #ccc");
        $("#skillCategoryValue").css("border", "1px solid #ccc");
   
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
    //var responsibilities=document.getElementById("RequirementResponse").innerHTML = responsibilitiesText;
    //alert(responsibilities);
    
    var jobDescText = document.getElementById("RequirementJobdesc").value;
    jobDescText = jobDescText.replace(/\r?\n/g, '<br/>');
    //var jobdesc=document.getElementById("RequirementJobdesc").innerHTML = jobDescText;   
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
    // var comment=document.getElementById("RequirementComments").innerHTML = commentsText;
    var requirementMaxRate=document.getElementById("requirementMaxRate").value;
    var billingtype=$("#billingtype").val();
    var qualification = $("#requirementQualification").val();
    qualification = qualification.replace(/\r?\n/g, '<br/>');
    var status_check=$("#status_check").val();
    //var requirementQualification = document.getElementById("requirementQualification").innerHTML = qualification;
    
    //    var responsibilities=$("#RequirementResponse").val();
    //    var jobdesc=$("#RequirementJobdesc").val();
    //    var preferredSkills=$("#RequirementPreferredSkills").val();
    //    var skill=$("#RequirementSkills").val();
    //    var comment=$("#RequirementComments").val();
    //alert("hello")
    if(RequirementValidations(skillCategoryArry)){

     
    
        var url='../Requirements/updateRequirements.action?RequirementId='+reqId+'&RequirementFrom='+fromValue+
        '&RequirementStatus='+status+'&RequirementName='+name+'&RequirementNoofResources='+noofresources+'&skillCategoryArry='+skillCategoryArry+
        '&RequirementComments='+commentsText+'&RequirementTargetRate='+targetrate+'&RequirementDuration='+
        duration+'&RequirementContact1='+contact1+
        '&RequirementContact2='+contact2+'&RequirementTaxTerm='+taxterm+
        '&RequirementLocation='+location+
        '&RequirementResponse='+responsibilitiesText+'&RequirementJobdesc='
        +jobDescText+'&preSkillCategoryArry='+preSkillCategoryArry+'&RequirementYears='+Exp+'&billingContact='+billingContact+
        '&requirementDurationDescriptor='+requirementDurationDescriptor+'&requirementMaxRate='+requirementMaxRate+
        '&billingtype='+billingtype+'&requirementQualification='+qualification+'&status_check='+status_check;
        //  alert(url)
        var req=initRequest(url);
        $('html,body').scrollTop(0);
        req.onreadystatechange = function() {
            document.getElementById('loadingReq').style.display = 'block';
            if (req.readyState == 4) {
                if (req.status == 200) {
                    $('#loadingReq').hide();
                    $("editrequirementerror").html(" <font color='green'>Requirement updated successfully </font>");
                    $("editrequirementerror").show().delay(5000).fadeOut();
                     document.getElementById("status_check").value=status;
                }
                else
                {
                    $("editrequirementerror").html(" <font color='red'>Requirement not updated</font>");
                    $("editrequirementerror").show().delay(5000).fadeOut();
                }
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
        e2 = $(this);
        var count = el.val().length + ((el.val().split('\n').length)*5);
        if(count >= 1000){
            e2.val( e2.val().substr(0, 1000-((el.val().split('\n').length)*5)) );
        } else {
            $("#ResponsecharNum").text(1000-count+' Characters remaining . ');
        }
        if(count==1000)
        {
            $("#ResponsecharNum").text(' Cannot enter  more than 1000 Characters .');    
        }
        
    })
    return false;
};
function JobCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        e2 = $(this);
        var count = el.val().length + ((el.val().split('\n').length)*5);
        if(count >= 1500){
            e2.val( e2.val().substr(0, 1500-((el.val().split('\n').length)*5)) );
        } else {
            $("#JobcharNum").text(1500-count+' Characters remaining . ');
        }
        if(count==1500)
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
        e2 = $(this);
        var count = el.val().length + ((el.val().split('\n').length)*5);
        if(count >= 1000){
            e2.val( e2.val().substr(0, 1000-((el.val().split('\n').length)*5)) );
        } else {
            $("#CommcharNum").text(1000-count+' Characters remaining . ');
        }
        if(count==1000)
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
        $("editrequirementerror").html(" <font color='green'>enter only numbers</font>");
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
   // alert(type)
    document.getElementById('RequirementName').value= "";
    document.getElementById('RequirementFrom').value = "";
    //document.getElementById('RequirementTaxTerm').value = type;
    document.getElementById('RequirementContact1').selectedIndex = "0";
    
    
    
    document.getElementById('RequirementNoofResources').value = "";
    document.getElementById('RequirementTargetRate').value = "";
    document.getElementById('requirementMaxRate').value= ""; 
    
    document.getElementById('RequirementContact2').selectedIndex = "0"; 
    document.getElementById('RequirementDuration').value ="";
    document.getElementById('requirementDurationDescriptor').selectedIndex = "0";
     document.getElementById('billingContact').selectedIndex = "0";
     document.getElementById('RequirementLocation').selectedIndex = "0";
    document.getElementById('RequirementYears').selectedIndex = "0";
   
    document.getElementById('reqCategoryValue').value = "1";
    document.getElementById('requirementQualification').value= ""; 
    document.getElementById('RequirementJobdesc').value = "";
    document.getElementById('RequirementResponse').value = "";
    
   
    
     $("#skillCategoryValue").selectivity('clear');
     $("#preSkillCategoryValue").selectivity('clear');
    //    document.getElementById('RequirementDescription').value = "";
    document.getElementById('RequirementComments').value= "";
    
}

function RequirementMaxRate(evt){
    //alert("heelo")
    var  maxRate=document.getElementById("requirementMaxRate").value;
    var rate=(maxRate.toString()).length;
   
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        $("editrequirementerror").html(" <font color='red'>Enter only numbers</font>");     
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
        $("editrequirementerror").html(" <font color='red'>Enter only numbers</font>");     
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
       
        $("editrequirementerror").html(" <font color='red'>Enter only numbers</font>");  
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

function vendorReqEdit()
{
    //alert("hello")
    
    var type=document.getElementById("userType").value;
    //alert(type)RequirementYears skillCategoryValue
    // alert(type)
    if(type=='VC')
    {
            
        var e = document.getElementById("billingContact");
        var strUser = e.options[e.selectedIndex].text;
        //alert(strUser) 
        document.getElementById("billingContactVendor").innerHTML=strUser;
        //alert(strUser)        
        var e1 = document.getElementById("RequirementTaxTerm");
        var strUser1 = e1.options[e1.selectedIndex].text;
        //alert(strUser1) 
        document.getElementById("requremenType").innerHTML=strUser1;
        var e2 = document.getElementById("RequirementYears");
        var strUser2 = e2.options[e2.selectedIndex].text;
        //alert(strUser2) 
        document.getElementById("requiremenExperience").innerHTML=strUser2;
        var e3 = document.getElementById("RequirementLocation");
        var strUser3 = e3.options[e3.selectedIndex].text;
        //alert(strUser3) 
        document.getElementById("requrementVendorLocation").innerHTML=strUser3;
        var e4 = document.getElementById("reqCategoryValue");
        var strUser4 = e4.options[e4.selectedIndex].text;
        //alert(strUser4) 
        document.getElementById("requrementVendorCategory").innerHTML=strUser4;
        if(strUser1=='Contract')
        {
            var e5 = document.getElementById("requirementDurationDescriptor");
            var strUser5 = e5.options[e5.selectedIndex].text;
            //alert(strUser5)
            document.getElementById("reqDuration").innerHTML=strUser5;
//            var e6 = document.getElementById("billingtype");
//            var strUser6 = e6.options[e6.selectedIndex].text;
//            //alert(strUser5)
//            document.getElementById("reqVendorBillingType").innerHTML=strUser6;

        }
        var skillCategoryArry = [];    
        $("#skillCategoryValue :selected").each(function(){
            skillCategoryArry.push($(this).text()); 
        });
        document.getElementById("requrementSkills").innerHTML=skillCategoryArry;
        var preskillCategoryArry = [];    
        $("#preSkillCategoryValue :selected").each(function(){
            preskillCategoryArry.push($(this).text()); 
        });
        // alert(preskillCategoryArry)
        document.getElementById("requrementPreferedSkills").innerHTML=preskillCategoryArry;        
        if(preskillCategoryArry=='')
        {
            document.getElementById("reqvendorPrefer").style.display="none";
        }else
        {
            document.getElementById("reqvendorPrefer").style.display="block";    
        }
    }
        

}
function QualificationCheckCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        e2 = $(this);
        var count = el.val().length + ((el.val().split('\n').length)*5);
        if(count >= 250){
            e2.val( e2.val().substr(0, 250-((el.val().split('\n').length)*5)) );
        } else {
            $("#req_Qualification").text(250-count+' Characters remaining . ');
        }
        if(count==1000)
        {
            $("#req_Qualification").text(' Cannot enter  more than 250 Characters .');    
        }
        
    })
    return false;
}
function downloadPDFSubmittedList(){
    var gridDownload=document.getElementById('gridDownload').value;
    var url= "./../recruitment/consultant/downloadResults.action?pdfHeaderName=Consultant Submitted List&gridDownload="
        +gridDownload+"&gridDownloadFlag=Sub";
   window.location=url;
}
function downloadXLSSubmittedList(){
    var gridDownload=document.getElementById('gridDownload').value;
    var url= "./../recruitment/consultant/downloadXlsResults.action?pdfHeaderName=Consultant Submitted List&gridDownload="
        +gridDownload+"&gridDownloadFlag=Sub";
   window.location=url;
}


function RequirementValidations(skillCategoryArry){
    var name=$("#RequirementName").val();
    var fromValue=$("#RequirementFrom").val();
    var taxterm=$("#RequirementTaxTerm").val();
    var contact1=$("#RequirementContact1").val();
    var noofresources=$("#RequirementNoofResources").val();
    var targetrate=$("#RequirementTargetRate").val();
    var requirementMaxRate=$("#requirementMaxRate").val();
    var contact2=$("#RequirementContact2").val();
    var duration=$("#RequirementDuration").val();
    var requirementDurationDescriptor=$("#requirementDurationDescriptor").val();
     var billingContact=$("#billingContact").val();
     var location=$("#RequirementLocation").val();
      var Years=$("#RequirementYears").val();
     var requirementQualification = $("#requirementQualification").val();  
      var jobdesc=$("#RequirementJobdesc").val();
       var responsibilities=$("#RequirementResponse").val();
    
   if(name==""||name.length>50){
        
        $("editrequirementerror").html("<font color='red'>Enter requirement title</font>");
        $("#RequirementName").css("border", "1px solid red");
        return false;
    }
   if(fromValue==""){
       
        $("editrequirementerror").html(" <font color='red'>From date field is required</font>");
        $("#RequirementFrom").css("border", "1px solid red");
        return false;
    }
    if(taxterm=="DF"){
        $("editrequirementerror").html("<font color='red'>Type field is required</font>");
        $("#RequirementTaxTerm").css("border", "1px solid red");
        return false;
    }
    if(contact1=="-1"){
        
        $("editrequirementerror").html("<font color='red'>Approver field is required</font>");
        $("#RequirementContact1").css("border", "1px solid red");
        return false;
    }
     if(noofresources==""||noofresources.length>9){
       
        $("editrequirementerror").html("<font color='red'>Enter valid Positions field value</font>");
        $("#RequirementNoofResources").css("border", "1px solid red");
        return false;
    }
   if(targetrate==""||targetrate.length>10){
      
        $("editrequirementerror").html("<font color='red'>Enter valid minimum rate</font>");
        $("#RequirementTargetRate").css("border", "1px solid red");
        return false;
    } 
    if(requirementMaxRate==""||requirementMaxRate.length>10){
      
        $("editrequirementerror").html("<font color='red'>Enter valid maximum rate</font>");
        $("#requirementMaxRate").css("border", "1px solid red");
        return false;
    }
    // alert("min rate---->"+targetrate +" max rate---->"+requirementMaxRate);
    var diff=requirementMaxRate-targetrate;
    if(diff <= 0)
    {
        $("editrequirementerror").html("<font color='red'>Maximum rate should be Greater than Minimum rate</font>");
        $("#requirementMaxRate").css("border","1px solid red");
        return false;
    } 
     if(contact2=="-1"){
   
        $("editrequirementerror").html("<font color='red'>Requisitioner field is required</font>");
        $("#RequirementContact2").css("border", "1px solid red");
        return false;
    }
    if(taxterm!="PE") {
        if(duration==""||duration.length>10){
        
            $("editrequirementerror").html("<font color='red'>Enter valid duration</font>");
            $("#RequirementDuration").css("border", "1px solid red");
            return false;
        }
    }
    if(taxterm!="PE") {
       
   
        if(requirementDurationDescriptor=="-1")
        {
            $("editrequirementerror").html("<font color='red'>Enter hrs/weeks/months</font>");
            $("#requirementDurationDescriptor").css("border","1px solid red");
            return false;
        }
    }
    if(billingContact=="DF"){
        $("editrequirementerror").html("<font color='red'>BillingContact field is required</font>");
        $("#billingContact").css("border", "1px solid red");
        return false;
    }
    if(location=="DF"){
        $("editrequirementerror").html("<font color='red'>Location field is required</font>");
        $("#RequirementLocation").css("border", "1px solid red");
        return false;
    }
    if(Years==-1){
        
        $("editrequirementerror").html("<font color='red'>Experience field is required</font>");
        $("#RequirementYears").css("border", "1px solid red");
        return false;
    }
    if(requirementQualification==""){
       
        $("editrequirementerror").html("<font color='red'>Qualification field is required</font>");
        $("#requirementQualification").css("border", "1px solid red");
        return false;
    } 
    if(jobdesc==""){
        $("editrequirementerror").html("<font color='red'>Job description field is required</font>");
        $("#RequirementJobdesc").css("border", "1px solid red");
        return false;
    }
     if(responsibilities==""){
        $("editrequirementerror").html("<font color='red'>Responsibilities field is required</font>");
        $("#RequirementResponse").css("border", "1px solid red");
        return false;
    }
    if(skillCategoryArry==""){
       
        $("editrequirementerror").html("<font color='red'>Skill field is required</font>");
        $("#skillCategoryValue").css("border", "1px solid red");
        return false;
    } 
    
    $("editrequirementerror").html("");
    
    $("#RequirementName").css("border", "1px solid #3BB9FF");
    $("#RequirementFrom").css("border", "1px solid #3BB9FF");
    $("#RequirementTaxTerm").css("border", "1px solid #3BB9FF");
    $("#RequirementContact1").css("border", "1px solid #3BB9FF");
    $("#RequirementNoofResources").css("border", "1px solid #3BB9FF");
    $("#RequirementTargetRate").css("border", "1px solid #3BB9FF");
    $("#requirementMaxRate").css("border","1px solid #3BB9FF"); 
    $("#RequirementContact2").css("border", "1px solid #3BB9FF");
    $("#RequirementDuration").css("border", "1px solid #3BB9FF");
    $("#requirementDurationDescriptor").css("border","1px solid #3BB9FF");
     $("#billingContact").css("border","1px solid #3BB9FF");
     $("#RequirementLocation").css("border", "1px solid #3BB9FF");
     $("#RequirementYears").css("border", "1px solid #3BB9FF");
     $("#requirementQualification").css("border", "1px solid #3BB9FF");
      $("#RequirementJobdesc").css("border", "1px solid #3BB9FF");
 
    $("#RequirementResponse").css("border", "1px solid #3BB9FF");
     $("#skillCategoryValue").css("border", "1px solid #3BB9FF");
     $("reqAdded").html("");
  return true;
    }
    
    function clearFormFields(){

   // alert(type)
    document.getElementById('RequirementName').value= "";
    document.getElementById('RequirementFrom').value = "";
    //document.getElementById('RequirementTaxTerm').value = type;
    document.getElementById('RequirementContact1').selectedIndex = "0";
    
    document.getElementById('RequirementNoofResources').value = "";
    document.getElementById('RequirementTargetRate').value = "";
    document.getElementById('requirementMaxRate').value= ""; 
    
    document.getElementById('RequirementContact2').selectedIndex = "0"; 
    document.getElementById('RequirementDuration').value ="";
    document.getElementById('requirementDurationDescriptor').selectedIndex = "0";
     document.getElementById('billingContact').selectedIndex = "0";
     document.getElementById('RequirementLocation').selectedIndex = "0";
    document.getElementById('RequirementYears').selectedIndex = "0";
   
    document.getElementById('reqCategoryValue').value = "1";
    document.getElementById('requirementQualification').value= ""; 
    document.getElementById('RequirementJobdesc').value = "";
    document.getElementById('RequirementResponse').value = "";
    
     $("#skillCategoryValue").selectivity('clear');
     $("#preSkillCategoryValue").selectivity('clear');
    //    document.getElementById('RequirementDescription').value = "";
    document.getElementById('RequirementComments').value= "";
}

function addRequirementSkillChange(){

    var reqCatId = document.getElementById('reqCategoryValue').value;

    alert("reqCategoryValue----->"+reqCatId)
   
    if ($("div").is("#skillCategoryValue"))
    {
        $("#skillCategoryValue").remove();
        $(".skills").after('<select id="skillCategoryValue" class=" "  onclick="" multiple="multiple" tabindex="10" name="skillCategoryValue "><option value="-1">Select Req.Category</option></select>');
    }
    var url = CONTENXT_PATH + '/recruitment/consultant/getSkillsBasedOnPrimarySkill.action?priSkillId=' + reqCatId; 
    //alert("url--->"+url)
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            requirementSkillsChanging(req.responseText); 
           document.getElementById("skillCategoryValue").disabled = false;
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}

function requirementSkillsChanging(data) {
  // alert("response---->"+data);
    //var topicId = document.getElementById("skillCategoryValue");
    var flag = data.split("FLAG");
    var addList = flag[0].split("^");
    var $select = $('#skillCategoryValue');
    $select.find('option').remove();
    for (var i = 0; i < addList.length - 1; i++) {
        var Values = addList[i].split("#");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select);
        }
    }

    $('#skillCategoryValue').selectivity({
        multiple: true,
        placeholder: 'Type to search Categories'

    });
    $("#skillCategoryValue").show();
    $(".skills").show();
}