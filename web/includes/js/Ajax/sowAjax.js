function addSOWAttachmentOverLay(){
    specialBox = document.getElementById('SOWAttachBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#sowAttachment_popup').popup( );
}
function doSOWAttachmentDownload(acc_attachment_id)
{
    var consultantId=$('#consultantId').val();
    var requirementId=$('#requirementId').val();
    var customerId=$('#customerId').val();
    var vendorId=$('#vendorId').val();
    var consultantName=$('#consultantName').val();
    var vendorName=$('#vendorName').val();
    var requirementName=$('#requirementName').val();
    var customerName=$('#customerName').val();
    var status=$('#status').val();
    var rateSalary=$('#rateSalary').val();
    var url='sowDownloadAttachment.action?acc_attachment_id='+acc_attachment_id
    +'&consultantId='+consultantId
    +'&requirementId='+requirementId
    +'&customerId='+customerId
    +'&vendorId='+vendorId
    +'&consultantName='+consultantName
    +'&vendorName='+vendorName
    +'&requirementName='+requirementName
    +'&customerName='+customerName
    +'&status='+status
    +'&rateSalary='+rateSalary
    ;
    //alert(url)
    window.location = url;
//window.location = 'sowDownloadAttachment.action?acc_attachment_id='+acc_attachment_id;
}
function migration_overlay(cName,consult_id,reqId){
    document.getElementById('consultid').value=consult_id;
    document.getElementById('req_Id').value=reqId;
    //alert(cName+"AND "+consult_id+" AND "+reqId+" hidden>"+document.getElementById('consultid').value);
    var loginId=document.getElementById("loginId").value;
    var usrName=cName.substring(cName.lastIndexOf('@')+1);
    var login= loginId.substring(loginId.lastIndexOf('@')+1);
    // var emailId;
    //alert(usrName+"pop......"+login+"..............................")
    if(usrName==login)
    {
        document.getElementById('migrationEmailId').value=cName;
    }
    else{
        document.getElementById('migrationEmailId').value="";  
    }
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#Migration_popup').popup(      
        );    
    $("mig").html("");
           
}
function migration_overlayClose(){
    
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#Migration_popup').popup(      
        );    
    $("mig").html("");        
}
function userMigration(){
    // alert("in mig")
    var vendor=$('#vendor').val();
    var req_Id=document.getElementById("req_Id").value;
    var consult_id=document.getElementById("consultid").value;
    //var vConsult=document.getElementById("vConsult").value;
    var migrationEmailId=document.getElementById("migrationEmailId").value;
    var loginId=document.getElementById("loginId").value;
    // var cName=$("#cName").val();
    
    // var usrName=cName.substring(0,cName.lastIndexOf('@')+1);
    var emailId = migrationEmailId.substring(
        migrationEmailId.lastIndexOf('@') + 1);
        
    var login= loginId.substring(loginId.lastIndexOf('@')+1);
    if(emailId==login)
    {
        //        var con=usrName.concat(emailId);
        //        document.getElementById('migrationEmailId').value=con;
        //        emailId=document.getElementById('migrationEmailId').value;   
       
      
        var url="Requirements/userMigration.action?req_Id="+req_Id+"&consult_id="+consult_id+"&migrationEmailId="+migrationEmailId;
        //alert(url)
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //alert(req.responseText)
                if(req.responseText==1){
                    $("mig").html(" <b><font color='green'>migrated Succesfully</font></b>");
                }else if(req.responseText==2){
                    $("mig").html(" <b><font color='red'>This User Already Migrated!</font></b>");
                }else{
                    $("mig").html(" <b><font color='red'>Error Occured</font></b>");
                }
            } 
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    else{
        $("mig").html(" <b><font color='red'>enter valid EmailId</font></b>");
    // alert("enter valid email")
    }
    return false;
}

function sowValidation(){
    var rateSalary;
    // alert("--------------");
    var typeOfUser=document.getElementById("typeOfUser").value;
    var status=document.getElementById("status").value;
    var serviceType=document.getElementById("serviceType").value;
    
    //   // alert(typeOfUser);
    //   // alert(status);
    //   // alert(serviceType);
    //    
    //   //// alert(typeOfUser);
    //    alert(status);
    //    alert(minWorkhrs);
    //    alert(maxWorkhrs);
    //    alert(estWorkhrs);
    //    alert(otFlag);
    var rVal=true;
    if(serviceType=="CO"){
        var minWorkhrs=$("#minWorkhrs").val();
        var maxWorkhrs=$("#maxWorkhrs").val();
        var estWorkhrs=$("#estHrs").val();
        var otFlag=document.getElementById("otFlag").checked;
        var travelRequired= document.getElementById("travelRequired").checked;
        var estOtHrs=$("#estOtHrs").val();
        var travelAmtPercentage=$("#travelAmtPercentage").val();
        var rateType=document.getElementById("submittedPayRateType").value;
   
        //    if(typeOfUser=="VC"){
        //        rateSalary=$('#targetRate').val();
        //    }
        //    else
        //    {
        rateSalary=$('#submittedPayRate').val(); 
        // }
        // alert(rateSalary)
        if(rateSalary=="" || rateSalary==null){
            $("e").html(" <b><font color='red'>Pay Rate shoud not empty!</font></b>");
            $("#targetRate").css("border", "1px solid red");
            $("#submittedPayRate").css("border", "1px solid red");
            $("#errorSpan").show().delay(5000).fadeOut();
            rVal=false;
        //  alert("--------------");
        }
        if(rateType!="HR")
        {
            if(minWorkhrs<=0.00 || minWorkhrs=="")
            {
                $("#minWorkhrs").css("border", "1px solid red");
                $("e").html(" <b><font color='red'>Enter Min Hrs.</font></b>");
                // minWorkhrs=0.00;
                document.getElementById("minWorkhrs").value=parseFloat(0).toFixed(2);
                rVal=false; 
            }
            if(maxWorkhrs<=0.00 || maxWorkhrs=="")
            {
                $("#maxWorkhrs").css("border", "1px solid red");
                $("e").html(" <b><font color='red'>Enter Max Hrs.</font></b>");
                // minWorkhrs=0.00;
                document.getElementById("maxWorkhrs").value=parseFloat(0).toFixed(2);
                rVal=false; 
            }
            if(estWorkhrs<=0.00 || estWorkhrs=="")
            {
                $("#estHrs").css("border", "1px solid red");
                $("e").html(" <b><font color='red'>Enter Est Hrs.</font></b>");
                // minWorkhrs=0.00;
                document.getElementById("estHrs").value=parseFloat(0).toFixed(2);
                rVal=false; 
            }
        }
        if(travelRequired=="true"){
            if(travelAmtPercentage<=0||travelAmtPercentage==""||travelAmtPercentage>100){
            
                $("#travelAmtPercentage").css("border","1px solid red");
                $("e").html(" <b><font color='red'>Enter travelAmtPercentage .</font></b>");
       
                rVal=false;
            }
        }
        if(otFlag=="true"){
            if(estOtHrs<=0.00||estOtHrs=="") {
                $("#estOtHrs").css("border", "1px solid red");
                $("e").html(" <b><font color='red'>Enter Est OT Hrs.</font></b>");
                // minWorkhrs=0.00;
                document.getElementById("estOtHrs").value=parseFloat(0).toFixed(2);
                rVal=false;
            }
        }
    }
    if(typeOfUser=="AC" && status=="Approved"&&serviceType=="PE"){
        var transId=document.getElementById("transId").value;
        var transNo=document.getElementById("transNo").value;
        var transAmt=document.getElementById("transAmt").value;
        if(transId<=0 ||transId==""){
            $("#transId").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>Enter Trasaction Id.</font></b>");
            rVal=false;
        }
        else{
            $("#transId").css("border", "1px solid #D2D2D2");
            $("e").html("");
        }
        if(transNo<=0 || transNo==""){
            $("#transNo").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>Enter Trasaction Number.</font></b>");
            rVal=false;
        }
        else{
            $("#transNo").css("border", "1px solid #D2D2D2");
            $("e").html("");
        }
        if(transAmt<=0 || transAmt==""){
            $("#transAmt").css("border", "1px solid red");
            $("e").html(" <b><font color='red'>Enter Trasaction Amount.</font></b>");
            rVal=false;
        }
        else{
            $("#transAmt").css("border", "1px solid #D2D2D2");
            $("e").html("");
        }
     
    }
    
    return rVal;
}
function sowAttachmentValidation(){
    var file=$('#file').val();
    //alert(file)
    var p=file.lastIndexOf(".");
    var e=file.substring(p+1, file.length);
    //alert(e)
    var rVal=false;
    if(e=="pdf" || e=="doc" || e=="docx"){
        rVal=true;
    }else{
        $("attachTag").html(" <b><font color='red'>only pdf or doc files allowed!</font></b>");
        $("#attachSpan").show().delay(5000).fadeOut();
    }
    return rVal;
}
function minHrsValidation()
{
    var targetRateType;
    var typeOfUser=document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType=document.getElementById("submittedPayRateType").value;
    //}
    // alert(targetRateType);
    
    var minWorkhrs=$("#minWorkhrs").val();
    if(minWorkhrs<=0.00 || minWorkhrs=="")
    {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Min Hrs.</font></b>");
        // minWorkhrs=0.00;
        document.getElementById("minWorkhrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else if (targetRateType=="DAY"&&minWorkhrs>24)
    {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 24 hrs.</font></b>");
        document.getElementById("minWorkhrs").value=parseFloat(0).toFixed(2);
        return false;
    }
    else if(targetRateType=="WEEK"&&minWorkhrs>168){
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 168 hrs.</font></b>");
        document.getElementById("minWorkhrs").value=parseFloat(0).toFixed(2);
        return false;
        
    }   
    else if(targetRateType=="MONTH"&&minWorkhrs>744)
    {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 744 hrs.</font></b>");
        document.getElementById("minWorkhrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else
    {
        $("#minWorkhrs").css("border", "1px solid #D2D2D2");
        $("e").html("");

        return true;
    } 
}

function maxHrsValidation()
{
    var targetRateType;
    var typeOfUser=document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType=document.getElementById("submittedPayRateType").value;
    //   }
    //alert(targetRateType);
    var maxWorkhrs=$("#maxWorkhrs").val();
    if(maxWorkhrs<=0.00 || maxWorkhrs=="")
    {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Max Hrs.</font></b>");
        // minWorkhrs=0.00;
        document.getElementById("maxWorkhrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else if (targetRateType=="DAY"&&maxWorkhrs>24)
    {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 24 hrs.</font></b>");
        document.getElementById("maxWorkhrs").value=parseFloat(0).toFixed(2);
        return false;
    }
    else if(targetRateType=="WEEK"&&maxWorkhrs>168){
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 168 hrs.</font></b>");
        document.getElementById("maxWorkhrs").value=parseFloat(0).toFixed(2);
        return false;
        
    }   
    else if(targetRateType=="MONTH"&&maxWorkhrs>744)
    {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 744 hrs.</font></b>");
        document.getElementById("maxWorkhrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else
    {
        $("#maxWorkhrs").css("border", "1px solid #D2D2D2");
        $("e").html("");

        return true;
    } 
}
function estimatedHrsValidation()
{
    var targetRateType;
    var typeOfUser=document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType=document.getElementById("submittedPayRateType").value;
    // }
    var estWorkhrs=$("#estHrs").val();
    if(estWorkhrs<=0.00 || estWorkhrs=="")
    {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Est Hrs.</font></b>");
        // minWorkhrs=0.00;
        document.getElementById("estHrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else if (targetRateType=="DAY"&&estWorkhrs>24)
    {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 24 hrs.</font></b>");
        document.getElementById("estHrs").value=parseFloat(0).toFixed(2);
        return false;
    }
    else if(targetRateType=="WEEK"&&estWorkhrs>168){
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 168 hrs.</font></b>");
        document.getElementById("estHrs").value=parseFloat(0).toFixed(2);
        return false;
        
    }   
    else if(targetRateType=="MONTH"&&estWorkhrs>744)
    {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 744 hrs.</font></b>");
        document.getElementById("estHrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else
    {
        $("#estHrs").css("border", "1px solid #D2D2D2");
        $("e").html("");

        return true;
    } 
}
function estimatedOTValidation()
{
    var targetRateType;
    var typeOfUser=document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType=document.getElementById("submittedPayRateType").value;
    //  }
    var estWorkhrs=$("#estOtHrs").val();
    if(estWorkhrs<=0.00 || estWorkhrs=="")
    {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Est OT Hrs.</font></b>");
        // minWorkhrs=0.00;
        document.getElementById("estOtHrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else if (targetRateType=="DAY"&&estWorkhrs>24)
    {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 24 hrs.</font></b>");
        document.getElementById("estOtHrs").value=parseFloat(0).toFixed(2);
        return false;
    }
    else if(targetRateType=="WEEK"&&estWorkhrs>168){
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 168 hrs.</font></b>");
        document.getElementById("estOtHrs").value=parseFloat(0).toFixed(2);
        return false;
        
    }   
    else if(targetRateType=="MONTH"&&estWorkhrs>744)
    {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <b><font color='red'>Enter Hrs less than 744 hrs.</font></b>");
        document.getElementById("estOtHrs").value=parseFloat(0).toFixed(2);
        return false; 
    }
    else
    {
        $("#estOtHrs").css("border", "1px solid #D2D2D2");
        $("e").html("");

        return true;
    } 
}
function hrsValidation()
{
    var otFlag=document.getElementById("otFlag").checked;
    if(otFlag==true){ 
        document.getElementById("estOtHrs").value=parseFloat(0).toFixed(2);
    }
    document.getElementById("estHrs").value=parseFloat(0).toFixed(2);
    document.getElementById("minWorkhrs").value=parseFloat(0).toFixed(2);
    document.getElementById("maxWorkhrs").value=parseFloat(0).toFixed(2);
    var rateType=document.getElementById("submittedPayRateType").value
    
    if(rateType=="HR"){
        $('#minWorkhrsId').hide();
        $('#maxWorkhrsId').hide();
        $('#estHrsDivId').hide();
        $('#otFlagDivId').hide();
    }
    else{
        $('#minWorkhrsId').show();
        $('#maxWorkhrsId').show();
        $('#estHrsDivId').show();
        $('#otFlagDivId').show();  
    }
}
function overTimeCheck() {
    var i=document.getElementById("otFlag").checked;
    if (i==true) {
                             
        document.getElementById("estOtHrs").disabled= false;
    } 
    else
    {
        document.getElementById("estOtHrs").disabled= true; 
    }
}

function travelRequiredCheck() {
    var i=document.getElementById("travelRequired").checked;
    if (i==true) {
        document.getElementById("travelAmtPercentage").disabled= false;
    } 
    else
    {
        document.getElementById("travelAmtPercentage").disabled= true; 
    }
}
function getRecreatedList(){
    initSessionTimer();
    //alert("div")
    var serviceId= document.getElementById("serviceId").value;
    var his_serviceType=document.getElementById("his_serviceType").value;
    var his_status= document.getElementById("his_status").value;
    // alert(his_status);
    var url=CONTENXT_PATH+'/sag/getRecreatedList.action?serviceId='+serviceId+'&his_serviceType='+his_serviceType+'&his_status='+his_status;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText);
                populateRecreatedList(req.responseText);
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    // Initialize the plugin    
   
    return false;
    
}

function populateRecreatedList(response)
{
$(".page_option").css('display', 'block');  
  var serviceTypeRedirect= document.getElementById("serviceTypeRedirect").value;
    var serviceId= document.getElementById("serviceId").value;
    var consultantName = document.getElementById("consultantName").value;
    var customerName = document.getElementById("customerName").value;
    var vendorName = document.getElementById("vendorName").value;
    var reqName = document.getElementById("requirementName").value;
    
    var eduList=response.split("^");
    var table = document.getElementById("sowResults");
        
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                $("#sowResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                
                row.append($('<td><a href="sowRecreateEdit.action?serviceTypeRedirect='+serviceTypeRedirect+'&serviceId='+ serviceId +'&consultantName='+consultantName+'&customerName='+customerName+'&vendorName='+vendorName+'&requirementName='+reqName+'&his_id='+ Values[6] +'" > '+  consultantName +  "</td>"));
               
                // row.append($("<td>" + consultantName + "</td>"));
                row.append($("<td> " + Values[7] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                //row.append($("<td>" + Values[2] + "</td>"));
                if(Values[3]!="null"){
                    row.append($("<td>$" + Values[2]+"/"+Values[3] + "</td>"));
                }
                else
                {
                    row.append($("<td>$"+Values[2]+"(thousands) </td>"));  
                }
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[5] + "</td>"));
            }
        }
  
       
    }
    else {
        var row = $("<tr />")
        $("#sowResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
         
        row.append($('<td colspan="10"> <font style="color: red;font-size: 15px;"><center>No Records to display</center></font></td>'));
$(".page_option").css('display', 'none');
    }
 $('#sowResults').tablePaginate({navigateType:'navigator'},recordPage);  
   pager.init(); 

}
function hoursValidations(){
    var rateType=document.getElementById("submittedPayRateType").value   
    if(rateType=="HR"){
        $('#minWorkhrsId').hide();
        $('#maxWorkhrsId').hide();
        $('#estHrsDivId').hide();
        $('#otFlagDivId').hide();
    }
    else{
        $('#minWorkhrsId').show();
        $('#maxWorkhrsId').show();
        $('#estHrsDivId').show();
        $('#otFlagDivId').show();  
    }
}
