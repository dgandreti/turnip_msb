function addSOWAttachmentOverLay() {
    specialBox = document.getElementById('SOWAttachBox');
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#sowAttachment_popup').popup( );
}
function doSOWAttachmentDownload(acc_attachment_id)
{
    var consultantId = $('#consultantId').val();
    var requirementId = $('#requirementId').val();
    var customerId = $('#customerId').val();
    var vendorId = $('#vendorId').val();
    var consultantName = $('#consultantName').val();
    var vendorName = $('#vendorName').val();
    var requirementName = $('#requirementName').val();
    var customerName = $('#customerName').val();
    var status = $('#status').val();
    var rateSalary = $('#rateSalary').val();
    var serviceId = $('#serviceId').val();
    var serviceType = $('#serviceType').val();
    var url = 'sowDownloadAttachment.action?acc_attachment_id=' + acc_attachment_id
            + '&consultantId=' + consultantId
            + '&requirementId=' + requirementId
            + '&customerId=' + customerId
            + '&vendorId=' + vendorId
            + '&consultantName=' + consultantName
            + '&vendorName=' + vendorName
            + '&requirementName=' + requirementName
            + '&customerName=' + customerName
            + '&status=' + status
            + '&rateSalary=' + rateSalary
            + '&serviceId=' + serviceId
            + '&serviceType=' + serviceType
            ;
    //alert(url)
    window.location = url;
//window.location = 'sowDownloadAttachment.action?acc_attachment_id='+acc_attachment_id;
}
function migration_overlay(cName, consult_id, reqId) {
    document.getElementById('consultid').value = consult_id;
    document.getElementById('req_Id').value = reqId;
    //alert(cName+"AND "+consult_id+" AND "+reqId+" hidden>"+document.getElementById('consultid').value);
    var loginId = document.getElementById("loginId").value;
    var usrName = cName.substring(cName.lastIndexOf('@') + 1);
    var login = loginId.substring(loginId.lastIndexOf('@') + 1);
    // var emailId;
    //alert(usrName+"pop......"+login+"..............................")
    if (usrName == login)
    {
        document.getElementById('migrationEmailId').value = cName;
    }
    else {
        document.getElementById('migrationEmailId').value = "";
    }
    var specialBox = document.getElementById("recruiterBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#Migration_popup').popup(
            );
    $("mig").html("");

}
function migration_overlayClose() {

    var specialBox = document.getElementById("recruiterBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#Migration_popup').popup(
            );
    $("mig").html("");
    window.location = "getSowList.action";
}
function userMigration() {
    // alert("in mig")
    var vendor = $('#vendor').val();
    var req_Id = document.getElementById("req_Id").value;
    var consult_id = document.getElementById("consultid").value;
    //var vConsult=document.getElementById("vConsult").value;
    var migrationEmailId = document.getElementById("migrationEmailId").value;
    var loginId = document.getElementById("loginId").value;
    // var cName=$("#cName").val();

    // var usrName=cName.substring(0,cName.lastIndexOf('@')+1);
    var emailId = migrationEmailId.substring(
            migrationEmailId.lastIndexOf('@') + 1);

    var login = loginId.substring(loginId.lastIndexOf('@') + 1);
    if (emailId == login)
    {
        //        var con=usrName.concat(emailId);
        //        document.getElementById('migrationEmailId').value=con;
        //        emailId=document.getElementById('migrationEmailId').value;   


        var url = "Requirements/userMigration.action?req_Id=" + req_Id + "&consult_id=" + consult_id + "&migrationEmailId=" + migrationEmailId;
        //alert(url)
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //alert(req.responseText)
                if (req.responseText == 1) {
                    $("mig").html(" <font color='green'>Migrated Succesfully</font>");
                    $("mig").show().delay(5000).fadeOut();
                } else if (req.responseText == 2) {
                    $("mig").html(" <font color='red'>This User Already Migrated!</font>");
                    $("mig").show().delay(5000).fadeOut();
                } else {
                    $("mig").html(" <font color='red'>Error Occured</font>");
                    $("mig").show().delay(5000).fadeOut();
                }
            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
    else {
        $("mig").html(" <font color='red'>Enter valid EmailId</font>");
        // alert("enter valid email")
    }
    return false;
}

function sowValidation() {
    var rateSalary;
    // alert("--------------");
    var typeOfUser = document.getElementById("typeOfUser").value;
    var status = document.getElementById("status").value;
    var serviceType = document.getElementById("serviceType").value;

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
    var rVal = true;
    if (serviceType == "CO") {
        var minWorkhrs = $("#minWorkhrs").val();
        var maxWorkhrs = $("#maxWorkhrs").val();
        var estWorkhrs = $("#estHrs").val();
        var otFlag = document.getElementById("otFlag").checked;
        var travelRequired = document.getElementById("travelRequired").checked;
        var estOtHrs = $("#estOtHrs").val();
        var travelAmtPercentage = $("#travelAmtPercentage").val();
        var rateType = document.getElementById("submittedPayRateType").value;

        //    if(typeOfUser=="VC"){
        //        rateSalary=$('#targetRate').val();
        //    }
        //    else
        //    {
        rateSalary = $('#submittedPayRate').val();
        // }
        // alert(rateSalary)
        if (rateSalary == "" || rateSalary == null) {
            $("e").html(" <font color='red'>Pay Rate shoud not empty!</font>");
            $("#targetRate").css("border", "1px solid red");
            $("#submittedPayRate").css("border", "1px solid red");
            $("#errorSpan").show().delay(5000).fadeOut();
            rVal = false;
            //  alert("--------------");
        }
        if (rateType != "HR")
        {
            if (minWorkhrs <= 0.00 || minWorkhrs == "")
            {
                $("#minWorkhrs").css("border", "1px solid red");
                $("e").html(" <font color='red'>Enter Min Hrs.</font>");
                // minWorkhrs=0.00;
                document.getElementById("minWorkhrs").value = parseFloat(0).toFixed(2);
                rVal = false;
            }
            if (maxWorkhrs <= 0.00 || maxWorkhrs == "")
            {
                $("#maxWorkhrs").css("border", "1px solid red");
                $("e").html(" <font color='red'>Enter Max Hrs.</font>");
                // minWorkhrs=0.00;
                document.getElementById("maxWorkhrs").value = parseFloat(0).toFixed(2);
                rVal = false;
            }
            if (estWorkhrs <= 0.00 || estWorkhrs == "")
            {
                $("#estHrs").css("border", "1px solid red");
                $("e").html(" <font color='red'>Enter Est Hrs.</font>");
                // minWorkhrs=0.00;
                document.getElementById("estHrs").value = parseFloat(0).toFixed(2);
                rVal = false;
            }
        }
        if (travelRequired == "true") {
            if (travelAmtPercentage <= 0 || travelAmtPercentage == "" || travelAmtPercentage > 100) {

                $("#travelAmtPercentage").css("border", "1px solid red");
                $("e").html(" <font color='red'>Enter travelAmtPercentage .</font>");

                rVal = false;
            }
        }
        if (otFlag == "true") {
            if (estOtHrs <= 0.00 || estOtHrs == "") {
                $("#estOtHrs").css("border", "1px solid red");
                $("e").html(" <font color='red'>Enter Est OT Hrs.</font>");
                // minWorkhrs=0.00;
                document.getElementById("estOtHrs").value = parseFloat(0).toFixed(2);
                rVal = false;
            }
        }
    }
    if (typeOfUser == "AC" && status == "Approved" && serviceType == "PE") {
        var transId = document.getElementById("transId").value;
        var transNo = document.getElementById("transNo").value;
        var transAmt = document.getElementById("transAmt").value;
        if (transId <= 0 || transId == "") {
            $("#transId").css("border", "1px solid red");
            $("e").html(" <font color='red'>Enter Trasaction Id.</font>");
            rVal = false;
        }
        else {
            $("#transId").css("border", "1px solid #D2D2D2");
            $("e").html("");
            if (transNo <= 0 || transNo == "") {
                $("#transNo").css("border", "1px solid red");
                $("e").html(" <font color='red'>Enter Trasaction Number.</font>");
                rVal = false;
            }
            else {
                $("#transNo").css("border", "1px solid #D2D2D2");
                $("e").html("");
                if (transAmt <= 0 || transAmt == "") {
                    $("#transAmt").css("border", "1px solid red");
                    $("e").html(" <font color='red'>Enter Trasaction Amount.</font>");
                    rVal = false;
                }
                else {
                    $("#transAmt").css("border", "1px solid #D2D2D2");
                    $("e").html("");
                }
            }
        }



    }

    return rVal;
}
function sowAttachmentValidation() {
    var file = $('#file').val();
    //alert(file)
    var p = file.lastIndexOf(".");
    var e = file.substring(p + 1, file.length);
    //alert(e)
    var rVal = false;
    if (file == "") {
        $("attachTag").html(" <font color='red'>Please Upload File.</font>");
        $("#attachSpan").show().delay(5000).fadeOut();
    }
    else if (e == "pdf" || e == "doc" || e == "docx") {
        rVal = true;
    } else {
        $("attachTag").html(" <font color='red'>Only pdf or doc files allowed!</font>");
        $("#attachSpan").show().delay(5000).fadeOut();
    }
    return rVal;
}
function minHrsValidation()
{
    var targetRateType;
    var typeOfUser = document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType = document.getElementById("submittedPayRateType").value;
    //}
    // alert(targetRateType);

    var minWorkhrs = $("#minWorkhrs").val();
    if (minWorkhrs <= 0.00 || minWorkhrs == "")
    {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Min Hrs.</font>");
        // minWorkhrs=0.00;
        document.getElementById("minWorkhrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "DAY" && minWorkhrs > 24)
    {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 24 hrs.</font>");
        document.getElementById("minWorkhrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "WEEK" && minWorkhrs > 168) {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 168 hrs.</font>");
        document.getElementById("minWorkhrs").value = parseFloat(0).toFixed(2);
        return false;

    }
    else if (targetRateType == "MONTH" && minWorkhrs > 744)
    {
        $("#minWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 744 hrs.</font>");
        document.getElementById("minWorkhrs").value = parseFloat(0).toFixed(2);
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
    var typeOfUser = document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType = document.getElementById("submittedPayRateType").value;
    //   }
    //alert(targetRateType);
    var maxWorkhrs = $("#maxWorkhrs").val();
    if (maxWorkhrs <= 0.00 || maxWorkhrs == "")
    {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Max Hrs.</font>");
        // minWorkhrs=0.00;
        document.getElementById("maxWorkhrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "DAY" && maxWorkhrs > 24)
    {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 24 hrs.</font>");
        document.getElementById("maxWorkhrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "WEEK" && maxWorkhrs > 168) {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 168 hrs.</font>");
        document.getElementById("maxWorkhrs").value = parseFloat(0).toFixed(2);
        return false;

    }
    else if (targetRateType == "MONTH" && maxWorkhrs > 744)
    {
        $("#maxWorkhrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 744 hrs.</font>");
        document.getElementById("maxWorkhrs").value = parseFloat(0).toFixed(2);
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
    var typeOfUser = document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType = document.getElementById("submittedPayRateType").value;
    // }
    var estWorkhrs = $("#estHrs").val();
    if (estWorkhrs <= 0.00 || estWorkhrs == "")
    {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Est Hrs.</font>");
        // minWorkhrs=0.00;
        document.getElementById("estHrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "DAY" && estWorkhrs > 24)
    {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 24 hrs.</font>");
        document.getElementById("estHrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "WEEK" && estWorkhrs > 168) {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 168 hrs.</font>");
        document.getElementById("estHrs").value = parseFloat(0).toFixed(2);
        return false;

    }
    else if (targetRateType == "MONTH" && estWorkhrs > 744)
    {
        $("#estHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 744 hrs.</font>");
        document.getElementById("estHrs").value = parseFloat(0).toFixed(2);
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
    var typeOfUser = document.getElementById("typeOfUser").value;
    //    if(typeOfUser=="VC"){
    //        targetRateType=document.getElementById("targetRateType").value;  
    //    }
    //    else{
    targetRateType = document.getElementById("submittedPayRateType").value;
    //  }
    var estWorkhrs = $("#estOtHrs").val();
    if (estWorkhrs <= 0.00 || estWorkhrs == "")
    {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Est OT Hrs.</font>");
        // minWorkhrs=0.00;
        document.getElementById("estOtHrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "DAY" && estWorkhrs > 24)
    {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 24 hrs.</font>");
        document.getElementById("estOtHrs").value = parseFloat(0).toFixed(2);
        return false;
    }
    else if (targetRateType == "WEEK" && estWorkhrs > 168) {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 168 hrs.</font>");
        document.getElementById("estOtHrs").value = parseFloat(0).toFixed(2);
        return false;

    }
    else if (targetRateType == "MONTH" && estWorkhrs > 744)
    {
        $("#estOtHrs").css("border", "1px solid red");
        $("e").html(" <font color='red'>Enter Hrs less than 744 hrs.</font>");
        document.getElementById("estOtHrs").value = parseFloat(0).toFixed(2);
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
    var otFlag = document.getElementById("otFlag").checked;
    if (otFlag == true) {
        document.getElementById("estOtHrs").value = parseFloat(0).toFixed(2);
    }
    document.getElementById("estHrs").value = parseFloat(0).toFixed(2);
    document.getElementById("minWorkhrs").value = parseFloat(0).toFixed(2);
    document.getElementById("maxWorkhrs").value = parseFloat(0).toFixed(2);
    var rateType = document.getElementById("submittedPayRateType").value

    if (rateType == "HR") {
        $('#minWorkhrsId').hide();
        $('#maxWorkhrsId').hide();
        $('#estHrsDivId').hide();
        $('#otFlagDivId').hide();
    }
    else {
        $('#minWorkhrsId').show();
        $('#maxWorkhrsId').show();
        $('#estHrsDivId').show();
        $('#otFlagDivId').show();
    }
}
function overTimeCheck() {
    var i = document.getElementById("otFlag").checked;
    if (i == true) {

        document.getElementById("estOtHrs").disabled = false;
    }
    else
    {
        document.getElementById("estOtHrs").disabled = true;
    }
}

function travelRequiredCheck() {
    var i = document.getElementById("travelRequired").checked;
    if (i == true) {
        document.getElementById("travelAmtPercentage").disabled = false;
    }
    else
    {
        document.getElementById("travelAmtPercentage").disabled = true;
    }
}
function getRecreatedList() {
    initSessionTimer();
    //alert("div")
    var serviceId = document.getElementById("serviceId").value;
    var his_serviceType = document.getElementById("his_serviceType").value;
    var his_status = document.getElementById("his_status").value;
    // alert(his_status);
    var url = CONTENXT_PATH + '/sag/getRecreatedList.action?serviceId=' + serviceId + '&his_serviceType=' + his_serviceType + '&his_status=' + his_status;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingSowRecreate').style.display = 'block';
        if (req.readyState == 4) {
            if (req.status == 200) {
                $('#loadingSowRecreate').hide();
                // alert(req.responseText);
                populateRecreatedList(req.responseText);
            }
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);

    // Initialize the plugin    

    return false;

}

function populateRecreatedList(response)
{
    $(".page_option").css('display', 'block');
    var serviceTypeRedirect = document.getElementById("serviceTypeRedirect").value;
    var serviceId = document.getElementById("serviceId").value;
    var consultantName = document.getElementById("consultantName").value;
    var customerName = document.getElementById("customerName").value;
    var vendorName = document.getElementById("vendorName").value;
    var reqName = document.getElementById("requirementName").value;

    var eduList = response.split("^");
    var table = document.getElementById("sowResults");

    for (var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if (response.length > 0) {
        for (var i = 0; i < eduList.length - 1; i++) {

            var Values = eduList[i].split("|");
            {


                var row = $("<tr />")
                $("#sowResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it

                row.append($('<td><a href="sowRecreateEdit.action?serviceTypeRedirect=' + serviceTypeRedirect + '&serviceId=' + serviceId + '&consultantName=' + consultantName + '&customerName=' + customerName + '&vendorName=' + vendorName + '&requirementName=' + reqName + '&his_id=' + Values[6] + '" > ' + consultantName + "</td>"));

                // row.append($("<td>" + consultantName + "</td>"));
                row.append($("<td> " + Values[7] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                //row.append($("<td>" + Values[2] + "</td>"));
                if (Values[3] != "null") {
                    row.append($("<td>$" + Values[2] + "/" + Values[3] + "</td>"));
                }
                else
                {
                    row.append($("<td>$" + Values[2] + "(thousands) </td>"));
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
    $('#sowResults').tablePaginate({
        navigateType: 'navigator'
    }, recordPage);
    pager.init();

}
function hoursValidations() {
    var rateType = document.getElementById("submittedPayRateType").value
    if (rateType == "HR") {
        $('#minWorkhrsId').hide();
        $('#maxWorkhrsId').hide();
        $('#estHrsDivId').hide();
        $('#otFlagDivId').hide();
    }
    else {
        $('#minWorkhrsId').show();
        $('#maxWorkhrsId').show();
        $('#estHrsDivId').show();
        $('#otFlagDivId').show();
    }
}
function releasePo(id, reqId)
{
    //alert(id)
    swal({
        title: "Are You Sure to Release ?",
        //text: "Tranfering csr",
        textSize: "170px",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3498db",
        //cancelButtonColor: "#56a5ec",
        cancelButtonText: "No",
        confirmButtonText: "Yes",
        closeOnConfirm: false,
        closeOnCancel: false

    },
    function(isConfirm) {

        if (isConfirm) {

            var url = CONTENXT_PATH + "/poRelease.action?sowId=" + id + "&requirementId=" + reqId;

            //alert(url)
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4 && req.status == 200) {
                    //alert("success");
                }
            };
            req.open("GET", url, "true");
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            req.send(null);
            swal("Success", " Successfully released ", "success");
        }



        else {

            swal("Cancelled", " Cancelled ", "error");


        }
    });
}
function SOWApproveOverlay() {


    document.getElementById("deductionAmt_overlay").value = document.getElementById("deductionAmt").value;
    document.getElementById("serviceId_overlay").value = document.getElementById("serviceId").value;
    var specialBox = document.getElementById("attachmentBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#SOWApproveOverlay_popup').popup(
            );
    $("sowApprove").html("");
    document.getElementById("startDate").value = "";
    $("#startDate").css("border", "1px solid #ccc");
    document.getElementById("endDate").value = "";
    $("#endDate").css("border", "1px solid #ccc");
    document.getElementById("overTimeRate").value = "";
    $("#overTimeRate").css("border", "1px solid #ccc");
    document.getElementById("overTimeLimit").value = "";
    $("#overTimeLimit").css("border", "1px solid #ccc");
    document.getElementById("rolesAndResponsibilites").value = "";
    $("#rolesAndResponsibilites").css("border", "1px solid #ccc");
}
var myCalendar;
function onloadDates() {
    myCalendar = new dhtmlXCalendarObject(["startDate", "endDate"]);
    myCalendar.setSkin('omega');
    myCalendar.setDateFormat("%m-%d-%Y");
    myCalendar.hideTime();
}
function enterDateRepository()
{
    document.getElementById('startDate').value = "";
    document.getElementById('endDate').value = "";
    return false;
}
function SOWApproveValidation() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var rolesAndResponsibilites = $("#rolesAndResponsibilites").val();
    var overTimeLimit = $("#overTimeLimit").val();
    var overTimeRate = $("#overTimeRate").val();
    var splitProjectStartDate = startDate.split('-');
    var sDate = new Date(splitProjectStartDate[2], splitProjectStartDate[0] - 1, splitProjectStartDate[1]); //Y M D 
    var splitProjectTargetDate = endDate.split('-');
    var eDate = new Date(splitProjectTargetDate[2], splitProjectTargetDate[0] - 1, splitProjectTargetDate[1]); //Y M D
    var mainStartDate = Date.parse(sDate);
    var mainEndDate = Date.parse(eDate);
    if (startDate == "")
    {
        $("sowApprove").html(" <font color='red'>Start Date is required</font>");
        $("#startDate").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#startDate").css("border", "1px solid #ccc");
    }
    if (endDate == "")
    {
        $("sowApprove").html(" <font color='red'>End Date is required</font>");
        $("#endDate").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#endDate").css("border", "1px solid #ccc");
    }
    if (overTimeRate == "")
    {
        $("sowApprove").html(" <font color='red'>Over Time Rate is required</font>");
        $("#overTimeRate").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#overTimeRate").css("border", "1px solid #ccc");
    }

    if (overTimeLimit == "")
    {
        $("sowApprove").html(" <font color='red'>Over Time Limit is required</font>");
        $("#overTimeLimit").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#overTimeLimit").css("border", "1px solid #ccc");
    }

    if (rolesAndResponsibilites == "")
    {
        $("sowApprove").html(" <font color='red'>Roles and Responsibilites are required</font>");
        $("#rolesAndResponsibilites").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#rolesAndResponsibilites").css("border", "1px solid #ccc");
    }
    var difference = (mainEndDate - mainStartDate) / (86400000 * 7);
    if (difference < 0)
    {
        $("sowApprove").html(" <font color='red'>invalid range</font>");
        $("#startDate").css("border", "1px solid red");
        $("#endDate").css("border", "1px solid red");
        return false;
    }

    else
    {
        $("sowApprove").html("");
        $("#startDate").css("border", "1px solid #3BB9FF");
        $("#endDate").css("border", "1px solid #3BB9FF");
        $("#overTimeRate").css("border", "1px solid #3BB9FF");
        $("#overTimeLimit").css("border", "1px solid #3BB9FF");
        $("#rolesAndResponsibilites").css("border", "1px solid #3BB9FF");
        return true;
    }
}
function POdownloadButton() {
    var serviceId = $("#serviceId").val();
//    alert("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+serviceId)
    var url = CONTENXT_PATH + '/sag/sow/poDownloadButton.action?serviceId=' + serviceId;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
//            alert(req.responseText)
            document.getElementById("attach_id").value = req.responseText;
            if (req.responseText == "") {
                document.getElementById("download_button").style.display = "none";
//                alert("in if")
            }
            else
            {
                document.getElementById("download_button").style.display = "";
//                alert("in else")
            }
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function doPODownload() {
    var attach_id = document.getElementById("attach_id").value;
    // window.location = 'sowDownloadAttachment.action?acc_attachment_id='+attach_id;
    var consultantId = $('#consultantId').val();
    var requirementId = $('#requirementId').val();
    var customerId = $('#customerId').val();
    var vendorId = $('#vendorId').val();
    var consultantName = $('#consultantName').val();
    var vendorName = $('#vendorName').val();
    var requirementName = $('#requirementName').val();
    var customerName = $('#customerName').val();
    var status = $('#status').val();
    var rateSalary = $('#rateSalary').val();
    var serviceId = $('#serviceId').val();
    var serviceType = $('#serviceType').val();
    var tabFlag = 'y';
    var url = 'sowDownloadAttachment.action?acc_attachment_id=' + attach_id
            + '&consultantId=' + consultantId
            + '&requirementId=' + requirementId
            + '&customerId=' + customerId
            + '&vendorId=' + vendorId
            + '&consultantName=' + consultantName
            + '&vendorName=' + vendorName
            + '&requirementName=' + requirementName
            + '&customerName=' + customerName
            + '&status=' + status
            + '&rateSalary=' + rateSalary
            + '&serviceId=' + serviceId
            + '&serviceType=' + serviceType
            + '&tabFlag=' + tabFlag
            ;
//    alert(tabFlag)
    window.location = url;
}

function sendPendingInvoiceMail() {
    //alert("sendPendingInvoiceMail");
    document.getElementById("invResultMessage").innerHTML = "";
    var invEmailSubject = $("#invEmailSubject").val();
    var invEmailBodyContent = $("#invEmailBodyContent").val();
    var invCreatedBy = $("#invCreatedBy").val();
    //alert(invEmailSubject);
    //alert(invCreatedBy);
    if (invEmailSubject == "") {
        document.getElementById("invResultMessage").innerHTML = "<font color='red'>Email subject field is mandatory</font>";
        return false;
    }
    if (invEmailBodyContent == "") {
        document.getElementById("invResultMessage").innerHTML = "<font color='red'>Email bodycontent field is mandatory</font>";
        return false;
    }
    //alert(invEmailSubject);
    var url = CONTENXT_PATH + '/sag/sendPendingInvMail.action?invEmailSubject=' + invEmailSubject + '&invEmailBodyContent=' + invEmailBodyContent + '&invCreatedBy=' + invCreatedBy;
    //alert(url);
    var req = initRequest(url);

    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if (req.responseText == "success") {
                document.getElementById("invResultMessage").innerHTML = "<font color='green'>The Mail has been Sent Successfully</font>";
            }
            else
            {
                document.getElementById("invResultMessage").innerHTML = "<font color='red'>Mail not send. Please try again</font>";
            }
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}