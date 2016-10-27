/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var requirementsearch,requirementAdd;
function doOnLoadRequirement() {
                
    
    requirementsearch = new dhtmlXCalendarObject(["year_start","year_end"]);
    //alert("hii1");
    requirementsearch.setSkin('omega');
    // alert("hii2");
    
    requirementsearch.setDateFormat("%Y/%m/%d");
                
    requirementAdd = new dhtmlXCalendarObject(["RequirementFrom","RequirementTo"]);
    // alert("hii1");
    requirementAdd.setSkin('omega');
    // alert("hii2");
    //leaveeditcalender.setDateFormat("%Y/%m/%d");
    requirementAdd.setDateFormat("%m/%d/%Y");
                
    
    
};
 function RequirementValidations(){
    var fromValue=$("#RequirementFrom").val();
    var toValue=$("#RequirementTo").val();
    var Exp=$("#RequirementExp").val();
    var status=$("#RequirementStatus").val();
    var name=$("#RequirementName").val();
    var noofresources=$("#RequirementNoofResources").val();
    var skill=$("#RequirementSkills").val();
    var comment=$("#RequirementComments").val();
    var desc=$("#RequirementDescription").val();
    
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
     var preferredSkills=$("#RequirementPreferredSkills").val();
   
    
      if(name==""||name.length>50){
        
        $("editrequirementerror").html(" <font color='red'>enter valid requirement name</font>");
        $("#RequirementName").css("border", "1px solid red");
        return false;
    }
    if(fromValue==""){
       
        $("editrequirementerror").html(" <font color='red'>fromdate field is required</font>");
        $("#RequirementFrom").css("border", "1px solid red");
        return false;
    }
    if(toValue==""){
        $("editrequirementerror").html(" <font color='red'>to date is required</font>");
        $("#RequirementTo").css("border", "1px solid red");
        return false;
    }
       if(duration==""||duration.length>10){
        
        $("editrequirementerror").html(" <font color='red'>enter valid duration</font>");
        $("#RequirementDuration").css("border", "1px solid red");
        return false;
    }
     if(noofresources==""||noofresources.length>9){
       
        $("editrequirementerror").html(" <font color='red'>enter valid noofresources field value</font>");
        $("#RequirementNoofResources").css("border", "1px solid red");
        return false;
    }
     if(targetrate==""||targetrate.length>10){
      
        $("editrequirementerror").html(" <font color='red'>enter valid target value</font>");
        $("#RequirementTargetRate").css("border", "1px solid red");
        return false;
    }
    if(taxterm=="DF"){
        $("editrequirementerror").html(" <font color='red'>taxterm field is required</font>");
        $("#RequirementTaxTerm").css("border", "1px solid red");
        return false;
    }
       if(location=="DF"){
        $("editrequirementerror").html(" <font color='red'>location field is required</font>");
        $("#RequirementLocation").css("border", "1px solid red");
        return false;
    }
    if(contact1=="-1"){
        
        $("editrequirementerror").html(" <font color='red'>contact1 field is required</font>");
        $("#RequirementContact1").css("border", "1px solid red");
        return false;
    }
    
     if(contact2=="-1"){
   
        $("editrequirementerror").html(" <font color='red'>contact2 field is required</font>");
        $("#RequirementContact2").css("border", "1px solid red");
        return false;
    }
     
   
    
   if(reason=="DF"){
       
        $("editrequirementerror").html(" <font color='red'> reason field is required</font>");
        $("#RequirementReason").css("border", "1px solid red");
        return false;
    }
    
     
  
    if(presales1=="-1"){
        $("editrequirementerror").html(" <font color='red'>presales1 field is required</font>");
        $("#RequirementPresales1").css("border", "1px solid red");
        return false;
    }
     if(presales2=="-1"){
        $("editrequirementerror").html(" <font color='red'>presales2 field is required</font>");
        $("#RequirementPresales2").css("border", "1px solid red");
        return false;
    }
   if(status=="DF"){
     
        $("editrequirementerror").html(" <font color='red'>status field is required</font>");
        $("#RequirementStatus").css("border", "1px solid red");
        return false;
    }
 
   if(Exp=="DF"){
        
        $("editrequirementerror").html(" <font color='red'>type field is required</font>");
        $("#RequirementExp").css("border", "1px solid red");
        return false;
    }
    if(practice=="-1"){
        
        $("editrequirementerror").html(" <font color='red'>practice field is required</font>");
        $("#RequirementPractice").css("border", "1px solid red");
        return false;
    }
    
   
   
    if(contactno==""){
        $("editrequirementerror").html(" <font color='red'>contact number field is required</font>");
        $("#RequirementContactNo").css("border", "1px solid red");
        return false;
    }
    
    if(state=="-1"){
        $("editrequirementerror").html(" <font color='red'>state field is required</font>");
        $("#RequirementState").css("border", "1px solid red");
        return false;
    }
    if(country=="-1"){
        $("editrequirementerror").html(" <font color='red'>Country field is required</font>");
        $("#RequirementCountry").css("border", "1px solid red");
        return false;
    }
     if(address==""){
        $("editrequirementerror").html(" <font color='red'>address field is required</font>");
        $("#RequirementAddress").css("border", "1px solid red");
        return false;
    }
    if(responsibilities==""){
        $("editrequirementerror").html(" <font color='red'>responsibilities field is required</font>");
        $("#RequirementResponse").css("border", "1px solid red");
        return false;
    }
    if(jobdesc==""){
        $("editrequirementerror").html(" <font color='red'>job desc field is required</font>");
        $("#RequirementJobdesc").css("border", "1px solid red");
        return false;
    }
     if(skill==""){
       
        $("editrequirementerror").html(" <font color='red'>skill set field is required</font>");
        $("#RequirementSkills").css("border", "1px solid red");
        return false;
    }
    if(preferredSkills==""){
         $("editrequirementerror").html(" <font color='red'>preferred skills field is required</font>");
        $("#RequirementPreferredSkills").css("border", "1px solid red");
        return false;
    }
    
     if(desc==""){
       
        $("editrequirementerror").html(" <font color='red'>description field is required</font>");
        $("#RequirementDescription").css("border", "1px solid red");
        return false;
    }
    
     if(comment==""){
      
        $("editrequirementerror").html(" <font color='red'>comments field is required</font>");
        $("#RequirementComments").css("border", "1px solid red");
        return false;
    }
   
     var res = fromValue.split(" ");
    fromValue=res[0];
    var res1 = toValue.split(" ");
    toValue=res1[0];
    //alert(fromValue+" and "+toValue)
    if (Date.parse(fromValue) >= Date.parse(toValue)) {
        //alert("Invalid Date Range!\nFrrom Date cannot be after To Date!")
        $("editrequirementerror").html(" <font color='red'>Invalid Date Range!\nFrrom Date cannot be after To Date!</font>");
        $("#RequirementFrom").css("border", "1px solid red");
        $("#RequirementTo").css("border", "1px solid red");

        return false;
    }
    else{
        $("editrequirementerror").html("");
        $("#RequirementFrom").css("border", "1px solid #3BB9FF");
        $("#RequirementComments").css("border", "1px solid #3BB9FF");
        $("#RequirementDescription").css("border", "1px solid #3BB9FF");
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
        $("#RequirementPreferredSkills").css("border","1px solid #3BB9FF");
     
         return true;
    }
  
};
function editRequirementDateValidation()
{

    document.getElementById('RequirementFrom').value = "";
    document.getElementById('RequirementTo').value = "";
    return false;
};

function updaterequirements(){
    alert("hello");
    var reqId=$("#RequirementId").val();
     var fromValue=$("#RequirementFrom").val();
    var toValue=$("#RequirementTo").val();
    //var Exp=$("#RequirementExp").val();
    var status=$("#RequirementStatus").val();
    var name=$("#RequirementName").val();
    var noofresources=$("#RequirementNoofResources").val();
    var skill=$("#RequirementSkills").val();
    var comment=$("#RequirementComments").val();
    var desc=$("#RequirementDescription").val();
    
    var targetrate=$("#RequirementTargetRate").val();
    var duration=$("#RequirementDuration").val();
    var contact1=$("#RequirementContact1").val();
    //var reason=$("#RequirementReason").val();
    var presales1=$("#RequirementPresales1").val();
    var contact2=$("#RequirementContact2").val();
    var taxterm=$("#RequirementTaxTerm").val();
    var presales2=$("#RequirementPresales2").val();
    //var practice=$("#RequirementPractice").val();
    var location=$("#RequirementLocation").val();
    //var contactno=$("#RequirementContactNo").val();
    //var state=$("#RequirementState").val();
    //var country=$("#RequirementCountry").val();
    var address=$("#RequirementAddress").val();
    
     var responsibilities=$("#RequirementResponse").val();
     var jobdesc=$("#RequirementJobdesc").val();
     var preferredSkills=$("#RequirementPreferredSkills").val();
    if(RequirementValidations()){

     
    
    var url='../Requirements/updateRequirements.action?RequirementId='+reqId+'&RequirementFrom='+fromValue+'&RequirementTo='+toValue+
    '&RequirementStatus='+status+'&RequirementName='+name+'&RequirementNoofResources='+noofresources+'&RequirementSkills='+skill+
    '&RequirementComments='+comment+'&RequirementDescription='+desc+'&RequirementTargetRate='+targetrate+'&RequirementDuration='+
    duration+'&RequirementContact1='+contact1+'&RequirementPresales1='+presales1+
    '&RequirementContact2='+contact2+'&RequirementTaxTerm='+taxterm+'&RequirementPresales2='+presales2+
    '&RequirementLocation='+location+
    '&RequirementAddress='+address+'&RequirementResponse='+responsibilities+'&RequirementJobdesc='
   +jobdesc+'&RequirementPreferredSkills='+preferredSkills;
    var req=initRequest(url);
    alert(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
              $("editrequirementerror").html(" <font color='green'>Record updated successfully </font>");
                  
        }
        else
                {
                    $("editrequirementerror").html(" <font color='red'>Record not updated</font>");
                //  alert("Error occured");
                }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    }
    return false;
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
jQuery(function($){
                
    $("#RequirementContactNo").mask("(999)-999-9999");
   
});
 function removeErrorMessages(){
    $("charNum").html("");
    $("editrequirementerror").html("");
        $("#RequirementName").css("border", "1px solid #53C2FF");
        $("#RequirementFrom").css("border", "1px solid #53C2FF");
        $("#RequirementTo").css("border", "1px solid #53C2FF");
        $("#RequirementTargetRate").css("border", "1px solid #53C2FF");
        $("#RequirementExp").css("border", "1px solid #53C2FF");
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
        $("#RequirementDescription").css("border", "1px solid #ccc");
        $("#RequirementComments").css("border", "1px solid #ccc");
        $("#RequirementPreferredSkills").css("border","1px solid #ccc");
     
}
