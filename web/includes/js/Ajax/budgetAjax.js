function getProjectBudgetSearch(){
    initSessionTimer();
    var year=$('#budgetYear').val();
    var project=$('#project').val();
    var quarterId=$('#quarterId').val();
    var status=$('#status').val();

    //alert("HI "+csrCustomers+"  "+dashYears)
    
    var url='../budgets/getProjectBudgetSearchResults.action?year='+year+'&project='+project+'&quarterId='+quarterId+'&status='+status;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingProjectBudgets').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingProjectBudgets').hide();
            populateProjectBudgetTable(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}


function populateProjectBudgetTable(response){
    $(".page_option").css('display', 'block');
    //alert(response)
    var dashBoardReq=response.split("^");
    var table = document.getElementById("projectBudgetTable");
    var roleValue=$('#roleValue').val();
    
    //alert(roleValue)
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){

        
        for(var i=0;i<dashBoardReq.length-1;i++){   
            //alert(techReviewList[0])
            var Values=dashBoardReq[i].split("|");
            {  
                var row = $("<tr />")
                $("#projectBudgetTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                
                
                if(roleValue!='Director'){
                    if(Values[6]=='Submitted' || Values[6]=='Approved'){
                        row.append($("<td>" + Values[1] + "</td>"));
                    }else{
                        row.append($('<td><a href="#" class="projectBudget_popup_open" onclick="projectBudgetDetailsToViewOnOverlay('+Values[8]+');projectBudgetOverlay(\'Edit\');">'+ Values[1] +"</td>"));
                    }
                }
                if(roleValue=='Director'){
                    if(Values[6]=='Submitted' || Values[6]=='Approved'){
                        row.append($('<td><a href="#" class="projectBudget_popup_open" onclick="projectBudgetDetailsToViewOnOverlay('+Values[8]+');projectBudgetOverlay(\'Edit\');">'+ Values[1] +"</td>"));
                    }else{
                        row.append($("<td>" + Values[1] + "</td>"));
                    }
                }
                row.append($("<td>" + Values[9] + "</td>"));
                row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td> $ " + Values[2] + "</td>"));
                if(Values[3]!=0)
                    row.append($("<td> $ " + Values[3] + "</td>"));
                else
                    row.append($("<td> ----</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
                if(Values[7].length>9){
                    row.append($('<td><a href="#" class="budgetCommentsOverlay_popup_open" onclick="budgetCommentsOverlay(\'' + Values[7] + '\');">' + Values[7].substring(0,10) + "</td>"));
                }else{
                 
                    row.append($('<td><a href="#" class="budgetCommentsOverlay_popup_open" onclick="budgetCommentsOverlay(\'' + Values[7] + '\');">' + Values[7] + "</td>"));
                }
                if(roleValue!='Director'){
                    if(Values[6]=='Submitted' || Values[6]=='Approved'){
                        row.append($('<td><img style="opacity: 0.4;" src="../includes/images/deleteImage.png" height="20" width="25"></td>'));
                    }else{
                        row.append($('<td><a href="#" onclick="doBudgetRecordDelete('+Values[8]+');"><img src="../includes/images/deleteImage.png" height="20" width="25"></td>'));
                    }
                }
            }
        }
    }
    else{
        var row = $("<tr />")
        $("#projectBudgetTable").append(row);
        row.append($('<td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#projectBudgetTable').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    pager.init(); 

}


function projectBudgetOverlay(addEditFlag){
    initSessionTimer();
    //alert(addEditFlag)
    $("#oproject").val("");
    $("#oyear").val("");
    $("#oquarterId").val("");
    $("#oestimateBudget").val("");
    $("#ocomments").val("");
    $("e").html("");
    $("d").html("");
    $("#oLaybuttons").css('visibility', 'visible');
    $("#addEditFlag").val(addEditFlag);
    if(addEditFlag=="Edit"){
        document.getElementById("consumedAmt").style.display="";
    //alert()
    }
    else{
        document.getElementById("consumedAmt").style.display="none"; 
    }
    var specialBox = document.getElementById("projectBudgetBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#projectBudget_popup').popup(      
        );    
    //getProjectBudgetSearch();
    
    // alert(document.getElementById("oproject").value);
    if(addEditFlag=="Add"){
    getCostCenterName();
    }
    return false;
}
function getCostCenterName(){
    var oproject = document.getElementById("oproject").value;
    //var year=$('#oyear option:selected').text();
    //var quarterId= document.getElementById("oquarterId").value;
    var url='../budgets/getCostCenterNameByProjectId.action?projectId='+oproject;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
           // alert(req.responseText);
            var add=req.responseText.split("|");
            if(add[1]=="CCN")
            {
               // alert(add[0]);
                $("e").html("<font color='red'>Cost center has no budget <br> so you can't add the budget for this project</font>");
                $("e").show().delay(4000).fadeOut();
                document.getElementById("costCenterBudgetAmt").value="";
                document.getElementById("costCenterName").value=add[0];
                document.getElementById("oestimateBudget").value="";
                document.getElementById("oremainingAmt").value="";  
                $("tip").html("");//" <font color='gray'>Estimated amount should not exceed</font> <font color='red'>$0.0.</font>");  ;
            }
            else{
                populateCCDetails(req.responseText);
            }
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
    return false;
}
function populateCCDetails(response){
    var add=response.split("^");
    for(var i=0;i<add.length-1;i++){        
        var Values=add[i].split("|");
        {  
            var balance =0;
            //alert(Values[7])
            if(Values[7]=='Approved'){
                balance= Values[1]-Values[2];
            }
            document.getElementById("costCenterBudgetAmt").value=parseFloat(balance).toFixed(1);//remaining bal of cost center;
            document.getElementById("costCenterName").value=Values[0];//cost center name;
            document.getElementById("costCenterCode").value=Values[4];//cost center code;
            document.getElementById("oquarterId").value=Values[6];
            //document.getElementById("oyear").value=Values[5];
            var id=document.getElementById("oestimateBudget").id;
            if(id=="oestimateBudget"){
                var costCenterBudgetAmt = document.getElementById("costCenterBudgetAmt").value;
                $("tip").html(" <font color='gray'>Estimated amount should not exceed</font> <font color='red'>$"+costCenterBudgetAmt+".</font>");  
            //document.getElementById("oestimateBudget").title="Estimated amount should not exceed $"+costCenterBudgetAmt+"."; 
            }
        //document.getElementById("oremainingAmt").value=Values[0];
        // document.getElementById("oconsumedAmt").value=Values[2];
        }
    }
}
function calculateAmt(){
    var addEditFlag=$('#addEditFlag').val();
    var oestimateBudget= document.getElementById("oestimateBudget").value;
    var costCenterBudgetAmt= document.getElementById("costCenterBudgetAmt").value;
    var diff = costCenterBudgetAmt - oestimateBudget;
    if(diff<0){
        document.getElementById("oestimateBudget").value="";
        //parseToEstAmt((" <font color='gray'>Estimated amount should not exceed</font> <font color='red'>$"+costCenterBudgetAmt+".</font>"));
        $("e").html("<font color='red'>Estimated amount should be less than the specified Amount.</font>");  
        $("e").show().delay(4000).fadeOut();
    }
    return false;
}

function closeProjectBudgetOverlay(){
    document.getElementById("costCenterBudgetAmt").value="";
    document.getElementById("costCenterName").value="";
    document.getElementById("oestimateBudget").value="";
    document.getElementById("oremainingAmt").value="";
    window.location = "ProjectBudgetDetails.action";
}


function saveBudgetDetails(formFlag){
    initSessionTimer();
    var flg=true;
    var project=$('#oproject').val();
    var year=$('#oyear option:selected').text();
    var quarterId=$('#oquarterId').val();
    var estimateBudget=$('#oestimateBudget').val();
    var comments=$('#ocomments').val();
    var flag=formFlag;
    var addEditFlag=$('#addEditFlag').val();
    var costCenterBudgetAmt=$('#costCenterBudgetAmt').val();
    var costCenterCode=$('#costCenterCode').val();
    var approveComments="";
    //    var oconsumedAmt ;
    //    var oremainingAmt =0.0;
    var diff = costCenterBudgetAmt - estimateBudget;
    if(diff<0){
        document.getElementById("oestimateBudget").value="";
        //document.getElementById("oremainingAmt").value="";
        $("e").html(" <font color='red'>Estimated amount should not exceed Cost Center Budget.</font>");  
        $("e").show().delay(4000).fadeOut();
        flg=false;
        return false;
    }

    // alert(flag+" "+project+" "+year+" "+quarterId+" "+estimateBudget+" "+comments)
    re=/^[0-9]*$/;
    // alert(flag+" "+project+" "+year+" "+quarterId+" "+estimateBudget+" "+comments)
    //if($("#oyear").val()=="" && !re.test(oyear))
    if(year== -1){
        $("#oyear").css("border", "1px solid red");
        $("e").html(" <font color='red'>Year is Mandatory</font>");
        
        flg=false;
        return false;
    }
    else if(!re.test(year)){
       
        $("#oyear").css("border", "1px solid red");
        $("e").html(" <font color='red'>Please select year</font>");
        flg=false;
        return false;
    }
    else{
       
        $("e").html("");
        $("#oyear").css("border", "1px solid green");
    }
    if(estimateBudget==""){
        $("#oestimateBudget").css("border", "1px solid red");
        $("e").html(" <font color='red'>Estimated Budget is Mandatory</font>");
        flg=false;
        return false;
    }
    //    else if(!re1.test(estimateBudget)){
    //        $("#oestimateBudget").css("border", "1px solid red");
    //        $("e").html(" <font color='red'>please enter numeric values</font>");
    //        flg=false;
    //        return false;
    //    }
    else //if(re1.test(estimateBudget))
    {
        $("e").html("");
        $("#oestimateBudget").css("border", "1px solid green");
    }
    
    if(comments==""){
        // alert("comments");
        $("#ocomments").css("border", "1px solid red");
        $("e").html(" <font color='red'>Please Enter Comments</font>");
        flg=false;
        return false;
    } 
    else
    {
        $("e").html("");
        $("#ocomments").css("border", "1px solid green");
   
    }
//    if(formFlag=='A'){
        approveComments = $("#approveComments").val();
        if(approveComments==""){
            $("#approveComments").css("border", "1px solid red");
            $("e").html(" <font color='red'>Please Enter Approve/Rejection Comments</font>");
            flg=false;
            return false;
        } 
        else
        {
            $("e").html("");
            $("#approveComments").css("border", "1px solid green");
        }
//    }
    //alert(flg)
    if(flg){
        
    
        var url='../budgets/saveProjectBudgetDetails.action?year='+year+'&project='+project+'&quarterId='+quarterId+'&estimateBudget='+estimateBudget+'&comments='+comments+'&flag='+flag+'&addEditFlag='+addEditFlag+'&costCenterBudgetAmt='+costCenterBudgetAmt+'&costCenterCode='+costCenterCode+'&approveComments='+approveComments;
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                if(req.responseText=='Added'){
                    $("e").html(" <font color='Green'>Budget Details Added For Project Selected</font>");
                //getProjectBudgetSearch();
                // window.location = "ProjectBudgetDetails.action";
                }else if(req.responseText=='Exist'){
                    $("e").html(" <font color='red'>Budget Details Already Exists!</font>");
                }else if(req.responseText=='Updated'){
                    $("e").html(" <font color='green'>Budget Details Updated!</font>");
                //getProjectBudgetSearch();
                //window.location = "ProjectBudgetDetails.action";
                }else if(req.responseText=='DirectorStatusUpdated'){
                    $("e").html(" <font color='green'>Budget Details Saved!</font>");
                //getProjectBudgetSearch();
                //window.location = "ProjectBudgetDetails.action";
                }else if(req.responseText=='DirectorStatusFail'){
                    $("e").html(" <font color='green'>Budget Details Failed to Approve!</font>");
                //getProjectBudgetSearch();
                //window.location = "ProjectBudgetDetails.action";
                }else{
                    $("e").html(" <font color='red'>Error Check Details</font>");
                }
            } 
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;

}

function projectBudgetDetailsToViewOnOverlay(id)
{
    initSessionTimer();
    var roleValue = document.getElementById("roleValue").value;
    var url='../budgets/getProjectBudgetDetailsToViewOnOverlay.action?budgetId='+id;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            setOverlayValues(req.responseText,roleValue);
            getCostCenterName();
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function setOverlayValues(response,roleValue){
    var dashBoardReq=response.split("^");
    if(response.length>0){
        for(var i=0;i<dashBoardReq.length-1;i++){   
            var Values=dashBoardReq[i].split("|");
            {  
                if(Values[6]=='Approved'){
                    $("#oLaybuttons").css('visibility', 'hidden');
                }
                $("#oproject").val(Values[1]);
                $("#oyear option:selected").text(Values[5]);
                $("#oquarterId").val(Values[4]);
                $("#oestimateBudget").val(Values[2]);
                $("#oremainingAmt").val(Values[3]);
                if(roleValue=='Director'){
                    if(Values[8]!='null' && Values[8]!=""){
                        $("#approveComments").val(Values[8]);
                    }
                    else{
                        $("#approveComments").val("");
                    }
                }
                if(Values[3]!='null' && Values[3]!=""){
                    var consumedAmt = Values[2]-Values[3];
                    $("#oconsumedAmt").val(consumedAmt);
                }
                if(Values[7]!='null' && Values[7]!=""){
                    $("#ocomments").val(Values[7]);
                }else{
                    Values[7]='';
                    $("#ocomments").val(Values[7]);
                }
                
            }
        }
    }
    $('#oproject').attr('disabled', true);
    $('#oyear').prop('disabled','disabled');
    $('#oquarterId').prop('disabled','disabled');
//projectBudgetOverlay('Edit');
}



function doBudgetRecordDelete(id){
    initSessionTimer();
    swal({
    
        title: "Do you want delete?",
  
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
            var url='../budgets/doBudgetRecordDelete.action?budgetId='+id;
            //alert(url)
            var req=initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4 && req.status == 200) {
                    //alert(req.responseText)
                    if(req.responseText=='Success'){
                        //$("d").html(" <b><font color='green'>Record Deleted!</font></b>");
                        getProjectBudgetSearch();
                        swal("Deleted!", "Deleted Successfully....", "success");
                    }else{
                        //$("d").html(" <font color='red'>Failed to Delete!</font></b>");
                        swal("Sorry!", "Delete Failed....", "Error");
                    }
                } 
            };
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
            return false;
   
   
    
        } else {
     
            swal("Cancelled", "Deletion cancelled ", "error");
 
      
        }
    });
}


//function doBudgetRecordDelete(id){
//    //alert("deleted "+id)
//    var url='../budgets/doBudgetRecordDelete.action?budgetId='+id;
//    //alert(url)
//    var req=initRequest(url);
//    req.onreadystatechange = function() {
//        if (req.readyState == 4 && req.status == 200) {
//            alert(req.responseText)
//            if(req.responseText=='Success'){
//                $("d").html(" <b><font color='green'>Record Deleted!</font></b>");
//                getProjectBudgetSearch();
//            }else{
//                $("d").html(" <b><font color='red'>Failed to Delete!</font></b>");
//            }
//        } 
//    };
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//    return false;
//}

function checkCharactersComment(id){
    var elem = document.getElementById("description_feedback");
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            //alert("in elase")
            elem.style.color="green";
            $("#description_feedback").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            //alert("in cannot")
            elem.style.color="red";
            $("#description_feedback").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}
function checkCharactersApproveComment(id){
    var elem = document.getElementById("Comments");
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            //alert("in elase")
            elem.style.color="green";
            $("#Comments").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            //alert("in cannot")
            elem.style.color="red";
            $("#Comments").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}

function validationYearOverlay(evt)
{
    
    //alert("alert alert")
    var  minRate=document.getElementById("oyear").value;
    //alert(minRate)
    
    var rate=(minRate.toString()).length;
   
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate!=4)
        {
            $("e").html(" <font color='red'>enter only numbers</font>");  
            $("e").show().delay(4000).fadeOut();
        }
        if(iKeyCode == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
    else if( rate >= 4)
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
        //alert("not allow")            
        $("e").html("");
        return true;
    }
    
}
function validationYear(evt)
{
    
    //alert("alert alert")
    var  minRate=document.getElementById("budgetYear").value;
    //alert(minRate)
    
    var rate=(minRate.toString()).length;
   
    //alert(rate)
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate!=4){
            $("#budgetValidation").html(" <font color='red'>enter only numbers</font>");  
            $("#budgetValidation").show().delay(4000).fadeOut();
        }
        if(iKeyCode == 8)
        {
            return true;       
        }
        else
        {
            return false;
        }
    }
    else if( rate >= 4)
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
        //alert("not allow")            
        $("#budgetValidation").html("");
        return true;
    }
    
}
function estimateBudgetValidation(evt)
{
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode!= 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
       
        $("e").html(" <font color='red'>enter only numbers</font>");  
        $("e").show().delay(4000).fadeOut();
        
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

function removeBudgetErrorMsg()
{ //document.getElementById("").
    //alert("hello jagan")
   
    
    $("#e").html("");
    $("#Comments").html("");
    $("#description_feedback").html("");
    $("#ocomments").css('border','1px solid #ccc');
    $("#oestimateBudget").css('border','1px solid #ccc');
    $("#oconsumedAmt").css('border','1px solid #ccc');
    $("#oremainingAmt").css('border','1px solid #ccc');
    $("#oyear").css('border','1px solid #ccc');
    return false;
}
function budgetCommentsOverlay(response){
    
    document.getElementById("commentDetails").value=response;
    var specialBox = document.getElementById("budgetCommentsBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#budgetCommentsOverlay_popup').popup(      
        ); 
}