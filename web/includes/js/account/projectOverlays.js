/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function setupAddProjectOverlay() {
    setupOverlay('addProjectOverlay', '#addProject_popup');
}
;

function setupAddSubProjectOverlay() {
    setupOverlay('addSubProjectOverlay', '#addSubProject_popup');
    var editProjectTargetHrs = document.getElementById("editProjectTargetHrs").value;
    var totalsubpjctTargetHrs = document.getElementById("totalTargetHrs").value;
    if (totalsubpjctTargetHrs == "") {
        totalsubpjctTargetHrs = 0;
    }
    var val = editProjectTargetHrs - totalsubpjctTargetHrs;
    document.getElementById("remainingTargetHrs").value = parseFloat(val).toFixed(0);
    $("hours").html(" <font color='gray'>Sub Project Target Hours must be less than <font color='red'>" + parseFloat(val).toFixed(0) + "</font> Hours</font>");
}
;


function setupAddProjectTeamMemberOverlay() {
    setupOverlay('addProjectTeamMemberOverlay', '#addProjectTeamMember_popup');
}
;

function setupOverlay(overlayBox, popupId) {
    var specialBox = document.getElementById(overlayBox);
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    // Initialize the plugin
    $(popupId).popup();
}
;

var subProjectsAreLoaded = true;
var projectTeamisLoaded = false;

function loadSubProjects(parentProjectId) {
    if (!subProjectsAreLoaded) {
        ajaxReplaceDiv('/getSubProjects', '#subProjects', 'parentProjectID=' + parentProjectId);
        subProjectsAreLoaded = true;
    }
}
;

function loadProjectTeamMembers(parentProjectId) {
    if (!projectTeamisLoaded) {
        ajaxReplaceDiv('/getProjectsTeamMembers', '#projectTeam', 'projectID=' + parentProjectId);
        projectTeamisLoaded = true;
    }
}
;


function ajaxReplaceDiv(actionUrl, divId, data)
{
    data = (typeof data === 'undefined') ? '{}' : data;
    $.ajax({
        type: "POST",
        url: CONTENXT_PATH + actionUrl,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        beforeSend: function(xhr) {
            $(divId).html("<span align='center'><font color='red'><i class='fa fa-spinner fa-pulse'></i>&nbsp;&nbsp;Loading...<font></span>");
        },
        success: function(data) {
            $(divId).children().remove();
            $(divId).html(data);
        }
    });
}
;


var projName;

function checkProjectName(projName, type) {
    var mainProjectId = $("#mainProjectId").val();
    var projectType;
    if (type == 'Main Project')
    {
        projectType = "main";
    }
    else {
        projectType = "subprojects";
    }
    // alert(projName)
    if (projName.replace(/\s/g, '') == "")
    {

        $("updateProject").html("<font color='red'>Enter Project name</font>");
        return false;
    }
    // alert(projName)
    $.ajax({
        url: "./checkProjectNames.action?projectName=" + projName + "&projectFlag=" + projectType + "&mainProjectId=" + mainProjectId,
        success: function(data) {

            if (data == "true") {
                $("updateProject").html(" <font color='red'>Project name exists!</font>");
                // document.getElementById("projectNameError").style.display = "block";
                document.getElementById("editprojectName").value = "";
                $("#editprojectName").focus();
            }

            else {
                $("updateProject").html("<font color='green'>Project name is valid.</font>");
                $("#editprojectName").css('border', '1px solid green');
                // $("#projectNameError").html("Project name is valid.");
                // document.getElementById("projectNameError").style.display = "none";
            }

        },
        type: 'GET'
    });
}
;


//developed by rk////////////////////////////////////////////////////////////////////////////////////////




function addVendorTierOverlay() {
    //$("#AddVendorTierOverlay").reset();
    var specialBox = document.getElementById("AddVendorTierOverlay");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#addVendorTier_popup').popup(
            );
    return false;
}
function editVendorTierOverlayClose() {
    initSessionTimer();
    var specialBox = document.getElementById("EditVendorTierOverlay");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#editVendorTier_popup').popup(
            );
    $("e1").html("");
    return false;
}
function editVendorTierOverlay(tierId) {

    $('#tierId').val(tierId);
    //+'&vendorTier='+vendorTier

    var url = 'editVendorTierOverlay.action?tierId=' + tierId;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText+"hhhhhhhhhh")
            populateVendorTierOverlay(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);

    var specialBox = document.getElementById("EditVendorTierOverlay");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#editVendorTier_popup').popup(
            );
    return false;
}

function populateVendorTierOverlay(response) {
    //alert(response)
    document.getElementById("vendorTierStatus").value = "";
    document.getElementById("vendorTier").value = "";
    document.getElementById("PF").value = "";
    var Values = response.split("|");

    document.getElementById("vendorTierStatus").value = Values[1];
    document.getElementById("vendorTier").value = Values[0];
    if (Values[2] == 1)
        document.getElementById('PF').checked = true;
    else
        document.getElementById('PF').checked = false;

}
function getVendors() {
    //alert("hi")
    var accountSearchID = $('#accountSearchID').val();
//var rNo=Math.random();
    var url = 'getDefaultVendorTiers.action?accountSearchID=' + accountSearchID;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingVendor').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingVendor').hide();
            clearVendorsForm();
            populateVendorTierTable(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function clearVendorsForm() {
    $("#vendorTierType").val("-1");
    $("#TierStatus").val("-1");
}
function populateVendorTierTable(response) {
    //alert(response)
    $(".page_option").css('display', 'block');
    var techReviewList = response.split("^");
    //alert(techReviewList[0])
    var table = document.getElementById("vendorTierTable");
    for (var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if (response.length > 0) {

        for (var i = 0; i < techReviewList.length - 1; i++) {
            //alert(techReviewList[0])
            var Values = techReviewList[i].split("|");
            {
                var row = $("<tr/>")
                $("#vendorTierTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($('<td><a href="#" class="editVendorTier_popup_open" onclick="editVendorTierOverlay(' + Values[5] + ');">' + Values[6] + "</td>"));
                if (Values[0] != 'null' && Values[0] != "") {
                    row.append($('<td>' + Values[0] + "</td>"));
                } else {
                    Values[0] = "";
                    row.append($('<td>' + Values[0] + "</td>"));
                }
                row.append($("<td>" + Values[2] + "</td>"));
                if (Values[7] == 1) {
                    pf = "Yes";
                } else {
                    pf = "No";
                }
                row.append($("<td>" + pf + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#vendorTierTable").append(row);
        row.append($('<td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#vendorTierTable').tablePaginate({navigateType: 'navigator'}, recordPage);
    pager.init();

}
function addVendorTierType() {
    var accountSearchID = $('#accountSearchID').val();
    var vendorTier = $('#vendorTier').val();
    var url = 'addVendorTierToCustmer.action?accountSearchID=' + accountSearchID + '&vendorTier=' + vendorTier;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert("Success")
            $("e").html(" <font color='green'>vendor tier added Succesfully</font>");
            getVendors();
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function editVendorTierDetails() {

    var accountSearchID = $('#accountSearchID').val();
    var vendorTierStatus = $('#vendorTierStatus').val();
    var vendorTier = $('#vendorTier').val();
    var pf = $("#PF").is(':checked') ? 1 : 0;
    var tierId = $('#tierId').val();
    var flag = true;
    // var rndNo=Math.random();
    if (pf == 0 && vendorTier == 0) {
        flag = false;
        $("e1").html(" <font color='red'>select either Tier or Head Hunter</font>");
    }
    //alert("HIIIIIIIIIIIIII"+accountSearchID+" "+vendorTierStatus+"  "+tierId)
    if (flag == true) {
        var url = 'editVendorTierDetails.action?accountSearchID=' + accountSearchID + '&tierId=' + tierId + '&vendorTierStatus=' + vendorTierStatus + '&VendorTierId=' + vendorTier + '&pf=' + pf;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //alert("Success")
                $("e1").html(" <font color='green'>vendor tier updated Succesfully</font>");
                getVendors();
            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}

function searchVendorTier() {
    initSessionTimer();
    var vendorTierType = $('#vendorTierType').val();
    var TierStatus = $('#TierStatus').val();
    var accountSearchID = $('#accountSearchID').val();

    //alert("HIIII "+vendorTierType+" "+TierStatus+"  "+accountSearchID)
    var url = 'searchVendorTierDetails.action?vendorTierType=' + vendorTierType + '&TierStatus=' + TierStatus + '&accountSearchID=' + accountSearchID;
    //alert(url)
    var req = initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingVendor').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingVendor').hide();
            populateVendorTierTable(req.responseText)

        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function reqSkillSetOverlay(response) {
    document.getElementById("reqSkillSetDetails").value = response;
    var specialBox = document.getElementById("projectSkillSetBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#projectSkillOverlay_popup').popup(
            );
}

function reqSkillSetOverlayClose() {
    var specialBox = document.getElementById('projectSkillSetBox');
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    // Initialize the plugin    
    $('#projectSkillOverlay_popup').popup(
            );
}
function projectDescriptinOverlay(response) {
    document.getElementById("projectDescription").value = response;
    var specialBox = document.getElementById("projectDescBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#projectsDescOverlay_popup').popup(
            );
}

function projectDescriptinOverlayClose(response) {
    document.getElementById("projectDescription").value = response;
    var specialBox = document.getElementById("projectDescBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#projectsDescOverlay_popup').popup(
            );
}
function subprojectSkillSetOverlay(response) {
    document.getElementById("subprojectSkillSetDetails").value = response;
    var specialBox = document.getElementById("subprojectSkillSetBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#subprojectSkillOverlay_popup').popup(
            );
}

function subprojectSkillSetOverlayClose() {
    var specialBox = document.getElementById('subprojectSkillSetBox');
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    // Initialize the plugin    
    $('#subprojectSkillOverlay_popup').popup(
            );
}
function subprojectDescriptionOverlay(response) {
    document.getElementById("subprojectDescription").value = response;
    var specialBox = document.getElementById("subprojectDescBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#subprojectsDescOverlay_popup').popup(
            );
}

function subprojectDescriptionOverlayClose() {
    var specialBox = document.getElementById('subprojectDescBox');
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    // Initialize the plugin    
    $('#subprojectsDescOverlay_popup').popup(
            );
}


function searchTeamMembers() {
    initSessionTimer();
    //alert("membersSearchResults");
    var teamMemberName = $('#teamMemberName').val();
    var status = $('#status').val();
    var projectID = $('#projectID').val();


    var url = 'getTeamMemberDetails.action?projectID=' + projectID + '&teamMemberName=' + teamMemberName + '&status=' + status;
    // alert(url);
    var req = initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingTeamMember').style.display = 'block';
        if (req.readyState == 4) {
            if (req.status == 200) {
                $('#loadingTeamMember').hide();
                // alert(req.responseText);
                populateProjectTeamMembersTable(req.responseText);

                // alert(req.responseText);
            }
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
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


function populateProjectTeamMembersTable(response) {
    //alert(response)
    $(".page_options").css('display', 'block');
    $(".pagination").css('display', 'block');
    var techReviewList = response.split("^");
    //alert(techReviewList[0])
    var table = document.getElementById("membersSearchResults");
    for (var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if (response.length > 0) {

        for (var i = 0; i < techReviewList.length - 1; i++) {
            //alert(techReviewList[0])
            var Values = techReviewList[i].split("|");
            {
                var row = $("<tr/>")
                $("#membersSearchResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                //row.append($('<td><a href=editVendorTierDetails.action?VendorTierId='+Values[4]+">" + Values[0] + "</a></td>"));
                row.append($('<td><a href=acc/setTeamMembersForProject.action?userID=' + Values[6] + '&projectID=' + Values[5] + "&mainProjectStatus=" + Values[7] + "&projectFlag=>" + Values[0] + "</a></td>"));


                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                // row.append($("<td>" + Values[4] + "</td>"));
                if (Values[2] == "Active")
                    row.append($('<td><a href="#" onclick="EmpReleasefromProject(' + Values[6] + ')"><i class="fa fa-arrow-circle-right fa-size"></i></a></td>'));
                else
                    row.append($("<td> Released </td>"));

                //row.append($("<td>" + Values[4] + "</td>")); we got project id here
                // row.append($("<td>" + Values[5] + "</td>")); we got user id here

            }
        }
    }
    else {
        var row = $("<tr />")
        $("#membersSearchResults").append(row);
        row.append($('<td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_options").css('display', 'none');
        $(".pagination").css('display', 'none');
    }
    $('#membersSearchResults').tablePaginate({navigateType: 'navigator'}, recordPage);
    pager.init();
    ;
}


function showResourceTeam(pid) {

    var ppid = document.getElementById("ppid").value;
    //alert(ppid);
    var projectID = pid;
    var url = 'showResourceDetails.action?projectID=' + projectID + '&ppid=' + ppid;
    //alert(url)
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            populateResourceInTable(req.responseText)

        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    setupOverlay('resourceOverlay', '#resourceOverlay_popup');


}
function populateResourceInTable(responce) {
    var techReviewList = responce.split("^");
    //alert(techReviewList[0])
    var table = document.getElementById("resourceTable");
    for (var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    //alert("praveen")

    if (responce.length > 0) {

        for (var i = 0; i < techReviewList.length - 1; i++) {
            //alert(techReviewList[0])
            var Values = techReviewList[i].split("|");
            {
                var row = $("<tr/>")
                $("#resourceTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                //row.append($('<td><a href=editVendorTierDetails.action?VendorTierId='+Values[4]+">" + Values[0] + "</a></td>"));
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#resourceTable").append(row);
        row.append($('<td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>'))
    }
    $('#resourceTable').tablePaginate({navigateType: 'navigator'}, recordPage);
    pager.init();
    pager.showPageNav('pager', 'pageNavPosition');
    pager.showPage(1);
}
function showResourceTeamOverlayClose() {
    setupOverlay('resourceOverlay', '#resourceOverlay_popup');
}
function addProjectValidation()
{
    //alert("hai");
    var projType = document.getElementById("projType").value;

    var projectTargetHrs = document.getElementById("projectTargetHrs").value;
    //  var costCenterName = document.getElementById("costCenterName").value;
    var project_statusPopup = document.getElementById("project_statusPopup").value;
    var projectNamePopup = document.getElementById("projectNamePopup").value;
    var projectStartDateOverlay = document.getElementById("projectStartDateOverlay").value;
    var projectTargetDateOverlay = document.getElementById("projectTargetDateOverlay").value;
    var splitProjectStartDate = projectStartDateOverlay.split('-');
    var startDate = new Date(splitProjectStartDate[2], splitProjectStartDate[0] - 1, splitProjectStartDate[1]); //Y M D 
    var splitProjectTargetDate = projectTargetDateOverlay.split('-');
    var targetDate = new Date(splitProjectTargetDate[2], splitProjectTargetDate[0] - 1, splitProjectTargetDate[1]); //Y M D
    var mainProjectStartDate = Date.parse(startDate);
    var mainProjectTargetDate = Date.parse(targetDate);

    if (projectNamePopup == "")
    {
        $("#addProjectValidation").html("<font color='red'>Project name is required</font>");
        $("#projectNamePopup").css("border", "1px solid red");
        return false;
    }
    if (project_statusPopup == -1)
    {
        $("#addProjectValidation").html(" <font color='red'>Project status is required</font>");
        $("#project_statusPopup").css("border", "1px solid red");
        return false;
    }
    if (projectStartDateOverlay == "")
    {
        $("#addProjectValidation").html(" <font color='red'>Project start date is required</font>");
        $("#projectStartDateOverlay").css("border", "1px solid red");
        return false;
    }
    if (projectTargetDateOverlay == "")
    {
        $("#addProjectValidation").html(" <font color='red'>Project end date is required</font>");
        $("#projectTargetDateOverlay").css("border", "1px solid red");
        return false;
    }
    if (projectTargetHrs == "")
    {
        $("#addProjectValidation").html(" <font color='red'>Target hours is required</font>");
        $("#projectTargetHrs").css("border", "1px solid red");
        return false;
    }

    var parentProjectTargetDate = document.getElementById('projectTargetDate').value;
    // alert(parentProjectTargetDate)
    //alert(projectTargetDateOverlay)
    var splitTaskStartDate = parentProjectTargetDate.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0] - 1, splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = projectTargetDateOverlay.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0] - 1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate = Date.parse(taskAddtargetDate);
    var difference = (taskTargetDate - taskStartDate) / (86400000 * 7);
    // alert(difference)
    if (difference > 0)
    {

        // alert("hi")
        $("#addProjectValidation").html(" <font color='red'>Target Date Should not Exceed Main Project Target date</font>");
        document.getElementById('projectTargetDateOverlay').value = "";
        $("#projectTargetDateOverlay").css("border", "1px solid red");
        //        $("#startDate").css("border", "1px solid red");
        //        $("#endDate").css("border", "1px solid red");
        $("#addProjectValidation").show().delay(4000).fadeOut();
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
        return false;
    }
    var splitProjectStartDate = parentProjectTargetDate.split('-');
    var projectAddStartDate = new Date(splitProjectStartDate[2], splitProjectStartDate[0] - 1, splitProjectStartDate[1]); //Y M D 
    var splitProjectEndDate = projectStartDateOverlay.split('-');
    var projectAddtargetDate = new Date(splitProjectEndDate[2], splitProjectEndDate[0] - 1, splitProjectEndDate[1]); //Y M D
    var projectStartDate = Date.parse(projectAddStartDate);
    var projectTargetDate = Date.parse(projectAddtargetDate);
    var projectdifference = (projectTargetDate - projectStartDate) / (86400000 * 7);
    //alert(projectdifference)
    if (projectdifference > 0)
    {

        // alert("hi")
        $("#addProjectValidation").html(" <font color='red'>Start date Should not Exceed Main Project Target date</font>");
        document.getElementById('projectStartDateOverlay').value = "";
        $("#projectStartDateOverlay").css("border", "1px solid red");
        //        $("#startDate").css("border", "1px solid red");
        //        $("#endDate").css("border", "1px solid red");
        $("#addProjectValidation").show().delay(4000).fadeOut();
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
        return false;
    }
    //    
    //    if(projType=="MP"){
    //        if(costCenterName=="DF"){
    //        
    //            $("#addProjectValidation").html(" <b><font color='red'>Please Select Cost Center Name</font></b>");
    //            $("#costCenterName").css("border", "1px solid red");
    //            return false;
    //        }
    //    }
    var difference = (mainProjectTargetDate - mainProjectStartDate) / (86400000 * 7);
    if (difference < 0)
    {
        $("#addProjectValidation").html(" <font color='red'>Start date must be less than target date</font>");
        $("#projectStartDateOverlay").css("border", "1px solid red");
        $("#projectTargetDateOverlay").css("border", "1px solid red");
        return false;
    }

    else
    {
        $("#addProjectValidation").html("");
        $("#projectStartDateOverlay").css("border", "1px solid #3BB9FF");
        $("#projectTargetDateOverlay").css("border", "1px solid #3BB9FF");
        $("#projectNamePopup").css("border", "1px solid #3BB9FF");
        $("#project_statusPopup").css("border", "1px solid #3BB9FF");
        //        $("#costCenterName").css("border", "1px solid #3BB9FF");
        $("#projectTargetHrs").css("border", "1px solid #3BB9FF");
        // resetOverlayForm();
        return true;
    }


}
function projectDateValidation()
{
    document.getElementById('projectStartDateOverlay').value = "";
    document.getElementById('projectTargetDateOverlay').value = "";
    return false;
}
;
function removeResults() {
    $("#addProjectValidation").html("");
    $("#projectStartDateOverlay").css("border", "1px solid #3BB9FF");
    $("#projectTargetDateOverlay").css("border", "1px solid #3BB9FF");
    $("#projectNamePopup").css("border", "1px solid #3BB9FF");
    $("#project_statusPopup").css("border", "1px solid #3BB9FF");
    $("#projectNameError").html("");
    resetOverlayForm();
}
function projectTeamMemberValidation() {
    // alert("in");
    var teamMemberName = document.getElementById("teamMemberNamePopup").value;
    var id = document.getElementById("teamMemberId").value;
    var projectFlag = document.getElementById("projectFlag").value;
    // alert(id);
    //    var designation=document.getElementById("designation").value;
    // alert(designation);
//    var reportPersonId=document.getElementById("memberPrimaryReporting").value;
//    if(Number(reportPersonId)==Number(id) && teamMemberName!="" && teamMemberName.length>=2){
//       // alert("in");
//        document.getElementById("memberPrimaryReporting").value="-1"; 
//        $("#validationMessage").html("<b><font color='red'>Team member and Reports to Should not same</font></b>");
//        $('#validationMessage').show().fadeIn();
//        return false;
//    }
    if (id == 0 || teamMemberName == "" || teamMemberName.length <= 2) {
        $("#teamMemberNamePopup").css("border", "1px solid red");
        $("#validationMessage").html("<font color='red'>Enter name</font>");
        $('#validationMessage').show().fadeIn();
        return false;
    }
    else
    {
        $("#teamMemberNamePopup").css("border", "1px solid #3BB9FF");
        $("#validationMessage").html("");
    }
    if (projectFlag != "addMember") {
        var mainProjectStatus = document.getElementById("mainProjectStatus").value;
        //alert(mainProjectStatus)
        if (mainProjectStatus != "Active")
        {
            $("#validationMessage").html("<font color='red'>Main Project is Not In Active</font>");
            $("#validationMessage").show().delay(4000).fadeOut();
            return false;
        }
    }
    return true;
}
function assignProjectsValidation() {
    var teamMemberStatusHidden = document.getElementById("teamMemberStatusHidden").value;
    //alert(teamMemberStatusHidden);
    if (teamMemberStatusHidden == "In-Active") {
        $("#projectClear").html("<font color='red'>Team member is not in active status</font>");
        $("#projectClear").show().delay(2000).fadeOut();
        return false;
    }
}
function updateProjectValidation()
{
    //alert("hai");
    //var project_statusPopup=document.getElementById("project.project_status").value;
    var projectNamePopup = document.getElementById("editprojectName").value;
    var projectStartDateOverlay = document.getElementById("projectStartDate").value;
    var projectTargetDateOverlay = document.getElementById("projectTargetDate").value;
    var editProjectTargetHrs = document.getElementById("editProjectTargetHrs").value;
    //var costCenterNam
    var projectType = document.getElementById("projectType").value;
    //var costCenterNam
    //alert(projectType)
    if (projectType == 'Sub-Project')
    {
        var mainProjectStatus = document.getElementById("mainProjectStatus").value;
        if (mainProjectStatus != "Active")
        {
            $("updateProject").html(" <font color='red'>Main Project is Not In Active</font>");
            return false;
        }
        var parentProjectTargetDate = document.getElementById('mainProjectTargetDate').value;
        //alert(parentProjectTargetDate)
        var splitProjectStartDate = parentProjectTargetDate.split('-');
        var projectAddStartDate = new Date(splitProjectStartDate[2], splitProjectStartDate[0] - 1, splitProjectStartDate[1]); //Y M D 
        var splitProjectEndDate = projectStartDateOverlay.split('-');
        var projectAddtargetDate = new Date(splitProjectEndDate[2], splitProjectEndDate[0] - 1, splitProjectEndDate[1]); //Y M D
        var projectStartDate = Date.parse(projectAddStartDate);
        var projectTargetDate = Date.parse(projectAddtargetDate);
        var projectdifference = (projectTargetDate - projectStartDate) / (86400000 * 7);
        //alert(projectdifference)
        if (projectdifference > 0)
        {

            // alert("hi")
            $("updateProject").html(" <font color='red'>Start date Should not Exceed Main Project Target date</font>");
            document.getElementById('projectStartDate').value = "";
            $("#projectStartDate").css("border", "1px solid red");
            //        $("#startDate").css("border", "1px solid red");
            //        $("#endDate").css("border", "1px solid red");
            $("updateProject").show().delay(4000).fadeOut();
            //         $("#startDate").show().delay(5000).fadeOut();
            //          $("#endDate").show().delay(5000).fadeOut();
            return false;
        }
        var splitTaskStartDate = parentProjectTargetDate.split('-');
        var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0] - 1, splitTaskStartDate[1]); //Y M D 
        var splitTaskEndDate = projectTargetDateOverlay.split('-');
        var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0] - 1, splitTaskEndDate[1]); //Y M D
        var taskStartDate = Date.parse(taskAddStartDate);
        var taskTargetDate = Date.parse(taskAddtargetDate);
        var difference = (taskTargetDate - taskStartDate) / (86400000 * 7);
        //alert(difference)
        if (difference > 0)
        {

            // alert("hi")
            $("updateProject").html(" <font color='red'>Target date Should not Exceed Main Project Target date</font>");
            // $("#addProjectValidation").html(" <b><font color='red'>Target Date Should not Exceed Main Project Target date</font></b>");
            document.getElementById('projectTargetDate').value = "";
            $("#projectTargetDate").css("border", "1px solid red");
            //        $("#startDate").css("border", "1px solid red");
            //        $("#endDate").css("border", "1px solid red");
            $("updateProject").show().delay(4000).fadeOut();
            //         $("#startDate").show().delay(5000).fadeOut();
            //          $("#endDate").show().delay(5000).fadeOut();
            return false;
        }

    }
    var splitSDate = projectStartDateOverlay.split('-');
    var sdate = new Date(splitSDate[2], splitSDate[0] - 1, splitSDate[1]); //Y M D 
    var splitEDate = projectTargetDateOverlay.split('-');
    var edate = new Date(splitEDate[2], splitEDate[0] - 1, splitEDate[1]); //Y M D
    var StartDateProject = Date.parse(sdate);
    var endDateProject = Date.parse(edate);
    if (projectNamePopup == "")
    {
        $("updateProject").html(" <font color='red'>project name is required</font>");
        $("#editprojectName").css("border", "1px solid red");
        return false;
    }
    if (projectStartDateOverlay == "")
    {
        $("updateProject").html(" <font color='red'>project start date is required</font>");
        $("#projectStartDate").css("border", "1px solid red");
        return false;
    }
    if (projectTargetDateOverlay == "")
    {
        $("updateProject").html(" <font color='red'>project end date is required</font>");
        $("#projectTargetDate").css("border", "1px solid red");
        return false;
    }
    if (editProjectTargetHrs == 0.0 || editProjectTargetHrs == "") {
        $("updateProject").html(" <font color='red'>project Target Hours is required</font>");
        $("#editProjectTargetHrs").css("border", "1px solid red");
        return false;
    }
    var difference = (endDateProject - StartDateProject) / (86400000 * 7);
    if (difference < 0)
    {
        $("updateProject").html(" <font color='red'>Start date must be less than target date</font>");
        $("#projectStartDate").css("border", "1px solid red");
        $("#projectTargetDate").css("border", "1px solid red");
        return false;
    }

    $("updateProject").html("");
    $("#projectStartDate").css("border", "1px solid #3BB9FF");
    $("#projectTargetDate").css("border", "1px solid #3BB9FF");
    $("#editprojectName").css("border", "1px solid #3BB9FF");


    return true;


}
function EmpReleasefromProject(userID) {
    //alert("divy");
    // alert("prasad-->"+userID);
    var accountID = document.getElementById("accountID").value;
    // alert(accountID+"account")
    var projectID = document.getElementById("projectID").value;
    swal({
        title: "Do want to Terminate.....?",
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
            var url = "./EmpReleasefromProject.action?projectID=" + projectID + "&accountID=" + accountID + "&userID=" + userID;
            // alert(url)
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4 && req.status == 200) {
                    // alert(req.responseText)
                    if (req.responseText == 1) {
                        $("emp").html(" <font color='green'> Terminated Succesfully</font>");
                        $("emp").show().delay(5000).fadeOut();
                        searchTeamMembers();
                    } else {
                        $("emp").html(" <font color='red'>Error Occured</font>");
                        $("emp").show().delay(5000).fadeOut();
                    }
                }
            };
            req.open("GET", url, "true");
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            req.send(null);
            swal("Success!", "Successfully Terminated....", "success");
        }
        else
            swal("Cancelled", "Terminate cancelled", "error");
    });
    return false;

}
function checkCharactersProjects(id) {
    //alert("in sff leave"+id);
    var elem = document.getElementById("charNumProjects");
    $(id).keyup(function() {
        el = $(this);
        if (el.val().length >= 200) {
            el.val(el.val().substr(0, 200));
        } else {
            //alert("in elase")
            elem.style.color = "green";
            $("#charNumProjects").text(200 - el.val().length + ' Characters remaining . ');
        }
        if (el.val().length == 200)
        {
            //alert("in cannot")
            elem.style.color = "red";
            $("#charNumProjects").text(' Cannot enter  more than 200 Characters .');
        }

    })
    return false;
}
;
function checkCharactersSkill(id) {
    //alert("in sff leave"+id);
    var elem = document.getElementById("charNumSkill");
    $(id).keyup(function() {
        el = $(this);
        if (el.val().length >= 100) {
            el.val(el.val().substr(0, 100));
        } else {
            // alert("in elase")
            elem.style.color = "green";
            $("#charNumSkill").text(100 - el.val().length + ' Characters remaining . ');
        }
        if (el.val().length == 100)
        {
            // alert("in cannot")
            elem.style.color = "red";
            $("#charNumSkill").text(' Cannot enter  more than 100 Characters .');
        }

    })
    return false;
}
;
function checkCharProjects(id) {
    //alert("in sff leave"+id);
    var elem = document.getElementById("Projects");
    $(id).keyup(function() {
        el = $(this);
        if (el.val().length >= 200) {
            el.val(el.val().substr(0, 200));
        } else {
            //alert("in elase")
            elem.style.color = "green";
            $("#Projects").text(200 - el.val().length + ' Characters remaining . ');
        }
        if (el.val().length == 200)
        {
            //alert("in cannot")
            elem.style.color = "red";
            $("#Projects").text(' Cannot enter  more than 200 Characters .');
        }

    })
    return false;
}
;
function checkCharSkill(id) {
    //alert("in sff leave"+id);'
    var elem = document.getElementById("Skill");
    $(id).keyup(function() {
        el = $(this);
        if (el.val().length >= 100) {
            el.val(el.val().substr(0, 100));
        } else {
            // alert("in elase")
            elem.style.color = "green";
            $("#Skill").text(100 - el.val().length + ' Characters remaining . ');
        }
        if (el.val().length == 100)
        {
            // alert("in cannot")
            elem.style.color = "red";
            $("#Skill").text(' Cannot enter  more than 100 Characters .');
        }

    })
    return false;
}
;

function removeMsg() {
    $("#charNumSkill").html(" ");
    $("#charNumProjects").html("");
    $("#Skill").html(" ");
    $("#Projects").html("");
}
// Add By Aklakh
function projectDateRepository() {
    document.getElementById('projectStartDate').value = "";
    document.getElementById('projectTargetDate').value = "";
    //  alert("please select from calendar!")
    return false;
}
// Add By Aklakh
function subProjectDaterepository() {
    document.getElementById('projectStartDateSearch').value = "";
    document.getElementById('projectTargetDateSearch').value = "";
    // alert("please select from calendar!")
    return false;
}
//Add By Aklakh
function dateValidate() {
    // alert(dateId);
    $("updateProject").html("");
    $("#projectStartDate").css("border", "1px solid #ccc");
    $('#projectTargetDate').css('border', '1px solid #ccc');
}
function addLocationOverlay() {
    //$("#AddVendorTierOverlay").reset();

    var specialBox = document.getElementById("AddLocationOverlay");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#addLocation_popup').popup(
            );
    return false;
}
function addorEditLocationOverlay(val)
{
    //alert(val)
    if (val == "Add")
    {
        document.getElementById("locationHeading").innerHTML = "Add Location";
        document.getElementById("statusLoc").style.display = "none";
        document.getElementById("updateLoc").style.display = "none";
        document.getElementById("addLoc").style.display = "";
    }
    else
    {
        document.getElementById("locationHeading").innerHTML = "Edit Location";
        document.getElementById("statusLoc").style.display = "";
        document.getElementById("addLoc").style.display = "none";
        document.getElementById("updateLoc").style.display = "";
    }
}

function costCenter_overlay(ccId, ccName, projectCount) {
    document.getElementById("prjCount").value = projectCount;
    document.getElementById("name").value = ccName;
    document.getElementById("ccId").value = ccId;
    document.getElementById("ccFlag").value = "edit";
    document.getElementById("ccadd").style.display = "none";
    document.getElementById("ccedit").style.display = "";
    document.getElementById("ccstatus").style.display = "";
    var specialBox = document.getElementById("recruiterBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#costCenter_popup').popup(
            );
    $("costCenter").html("");

}
function costCenterAdd_overlay() {
    document.getElementById("ccadd").style.display = "";
    document.getElementById("ccedit").style.display = "none";
    document.getElementById("ccstatus").style.display = "none";
    document.getElementById("ccFlag").value = "add";
    var specialBox = document.getElementById("recruiterBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#costCenter_popup').popup(
            );
    $("costCenter").html("");

}
function costCenterValidation(ccName) {
    if (ccName == "") {
        $("costCenter").html(" <font color='red'>Please Enter name.</font>");
        $("#name").css("border", "1px solid red");
        return false;
    }
    else {
        $("costCenter").html("");
        $("#name").css("border", "1px solid #ccc");
        return true;
    }
}
;
function addCostCenter() {
    var ccName = $("#name").val();
    var ccFlag = $("#ccFlag").val();
    if (costCenterValidation(ccName) == true) {
        var url = CONTENXT_PATH + '/costCenter/addCostCenter.action?ccName=' + ccName + '&ccFlag=' + ccFlag;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                if (req.responseText == "Added Successfully") {
                    $("costCenter").html(" <font color='green'>Inserted Successfully.</font>");
                }
                else {
                    $("costCenter").html(" <font color='red'>Not Inserted.</font>");
                }

            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}
function removeCostCenterResults() {
    $("costCenter").html("");
    $("#name").css("border", "1px solid #ccc");
    $("#startDate").css("border", "1px solid #ccc");
    $("#endDate").css("border", "1px solid #ccc");
    $("#budgetAmt").css("border", "1px solid #ccc");
    document.getElementById("startDate").value = "";
    document.getElementById("endDate").value = "";
    document.getElementById("budgetAmt").value = "";
    document.getElementById("name").value = "";
    document.getElementById("status").value = "Active";
    removesMsg();
    window.location = "costCenterSearch.action";

}
function removesMsg() {
    $("#charNumSkill").html("");
    $("#charNumProjects").html("");
}
function editCostCenter() {
    var prjCount = $("#prjCount").val();
    var status = $("#status").val();
    if (prjCount > 0) {
        if (status === "In-Active") {
            $("costCenter").html("<font color='red'>Cost Center Consists Active Projects <br> So you cannot In-Activate it.</font>");
            return false;
        }
    }
    var ccName = $("#name").val();
    var ccFlag = $("#ccFlag").val();
    var ccId = $("#ccId").val();
    if (costCenterValidation(ccName) == true) {
        var url = CONTENXT_PATH + '/costCenter/addCostCenter.action?ccName=' + ccName + '&status=' + status + '&ccFlag=' + ccFlag + '&ccId=' + ccId;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                if (req.responseText == "updated Successfully") {
                    $("costCenter").html(" <font color='green'>Updated Successfully.</font>");
                    $("costCenter").show().delay(5000).fadeOut();
                }
                else {
                    $("costCenter").html(" <font color='red'>Not Updated.</font>");
                    $("costCenter").show().delay(5000).fadeOut();
                }
            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}
function costCenterBudget_overlay(ccCode, id, ccName) {
    var role = document.getElementById("role").value;

    var flag;
    if (id == 0) {
        flag = "budgetAdd";
        //document.getElementById("ccbudget").value="Add";
        document.getElementById("spentAmtId").style.display = "none";
        document.getElementById("balanceAmtId").style.display = "none";
        document.getElementById("buttonId").style.display = "";
    }
    else if (id > 0) {
        flag = "budgetEdit";
        //document.getElementById("ccbudget").value="Update";
        document.getElementById("spentAmtId").style.display = "";
        document.getElementById("balanceAmtId").style.display = "";
        document.getElementById("buttonId").style.display = "none";
        var url = CONTENXT_PATH + '/costCenter/getCostCenterBudgetDetails.action?id=' + id;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                populateCostCenterBudgetOverlay(req.responseText, role);
            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
    document.getElementById("flag").value = flag;
    document.getElementById("code").value = ccCode;
    document.getElementById("id").value = id;
    document.getElementById("costCenterName").value = ccName;
    budget_overlay();
}
function populateCostCenterBudgetOverlay(response, role) {
    var add = response.split("^");
    for (var i = 0; i < add.length - 1; i++) {
        var Values = add[i].split("|");
        {
            document.getElementById("budgetAmt").value = Values[0];
            document.getElementById("startDate").value = Values[1];
            document.getElementById("endDate").value = Values[2];
            document.getElementById("spentAmt").value = Values[3];
            var balamt = Values[0] - Values[3];
            document.getElementById("balanceAmt").value = parseFloat(balamt).toFixed(1);//Values[4]
            $("#year option:selected").text(Values[5]);
            $("#quarter").val(Values[6]);
            $("#budgetStatus").val(Values[7]);

            if (Values[7] == "Submitted") {
                document.getElementById("submit").style.display = "";
                document.getElementById("enter").style.display = "none";
                document.getElementById("rejComm").style.display = "";
                document.getElementById("approveComm").style.display = "";
            }
            else if (Values[7] == "Entered" || Values[7] == "Rejected") {
                document.getElementById("enter").style.display = "";
                document.getElementById("submit").style.display = "none";
            }
            else if (Values[7] == "Approved") {
                document.getElementById("rejComm").style.display = "none";
                document.getElementById("approveComm").style.display = "";
                document.getElementById("enter").style.display = "none";
                document.getElementById("submit").style.display = "none";
            }
            else {
                document.getElementById("rejComm").style.display = "";
                document.getElementById("approveComm").style.display = "none";
                document.getElementById("enter").style.display = "none";
                document.getElementById("submit").style.display = "none";
            }
            if (role == 'Director') {
                if (Values[8] != 'null' && Values[8] != "") {
                    document.getElementById("approveComments").value = Values[8];
                }
                else {
                    document.getElementById("approveComments").value = "";
                }
                if (Values[8] != 'null' && Values[8] != "") {
                    document.getElementById("rejectionComments").value = Values[9];
                }
                else {
                    document.getElementById("rejectionComments").value = "";
                }
            }
            // document.getElementById("quarter").value=Values[6];

        }
    }
}

function budget_overlay() {
    var specialBox = document.getElementById("attachmentBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#costCenterBudget_popup').popup(
            );
    $("costCenter").html("");
}
function costCenterBudgetValidation(budgetAmt) {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var splitSDate = startDate.split('-');
    var sdate = new Date(splitSDate[2], splitSDate[0] - 1, splitSDate[1]); //Y M D 
    var splitEDate = endDate.split('-');
    var edate = new Date(splitEDate[2], splitEDate[0] - 1, splitEDate[1]); //Y M D
    var StartDateProject = Date.parse(sdate);
    var endDateProject = Date.parse(edate);
    if (startDate == "") {
        $("costCenter").html(" <font color='red'>Please Enter startDate.</font>");
        $("#startDate").css("border", "1px solid red");
        return false;
    }
    else {
        $("costCenter").html("");
        $("#startDate").css("border", "1px solid #ccc");
    }
    if (endDate == "") {
        $("costCenter").html(" <font color='red'>Please Enter endDate.</font>");
        $("#endDate").css("border", "1px solid red");
        return false;
    }
    else {
        $("costCenter").html("");
        $("#endDate").css("border", "1px solid #ccc");
    }
    if (budgetAmt == "") {
        $("costCenter").html(" <font color='red'>Please Enter budgetAmt.</font>");
        $("#budgetAmt").css("border", "1px solid red");
        return false;
    }
    else {
        $("costCenter").html("");
        $("#budgetAmt").css("border", "1px solid #ccc");

    }
    var difference = (endDateProject - StartDateProject) / (86400000 * 7);
    if (difference < 0)
    {
        $("costCenter").html(" <font color='red'>Start date must be less than target date</font>");
        $("#startDate").css("border", "1px solid red");
        $("#endDate").css("border", "1px solid red");
        return false;
    }
    else {
        $("costCenter").html("");
        $("#startDate").css("border", "1px solid #ccc");
        $("#endDate").css("border", "1px solid #ccc");
    }
    return true;
}
function addCostCenterBudget(budgetStatus) {
    var ccCode = $("#code").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var budgetAmt = $("#budgetAmt").val();
    var ccFlag = $("#flag").val();
    var id = $("#id").val();
    var approveComments = "";
    var rejectionComments = "";
    var quarter = $("#quarter").val();
    var year = $("#year").val();
    if (budgetStatus == "S") {
        budgetStatus = "Entered";
    }
    else if (budgetStatus == "SB") {
        budgetStatus = "Submitted";
    }
    else if (budgetStatus == "A") {
        budgetStatus = "Approved";
        approveComments = $("#approveComments").val();
        if (approveComments == "") {
            $("costCenter").html(" <font color='red'>Please Enter Approve Comments</font>");
            $("#approveComments").css("border", "1px solid red");
            return false;
        }
        else {
            $("costCenter").html("");
            $("#approveComments").css("border", "1px solid #ccc");
        }
    }
    else if (budgetStatus == "R") {
        budgetStatus = "Rejected";
        rejectionComments = $("#rejectionComments").val();
        if (rejectionComments == "") {
            $("costCenter").html(" <font color='red'>Please Enter Comments for Rejection</font>");
            $("#rejectionComments").css("border", "1px solid red");
            return false;
        }
        else {
            $("costCenter").html("");
            $("#rejectionComments").css("border", "1px solid #ccc");
        }
    }
    else {

    }

    if (costCenterBudgetValidation(budgetAmt) == true) {

        var url = CONTENXT_PATH + '/costCenter/addCostCenterBudget.action?ccCode=' + ccCode + '&startDate=' + startDate + '&endDate=' + endDate + '&budgetAmt=' + budgetAmt + '&ccFlag=' + ccFlag + '&id=' + id + '&year=' + year + '&quarter=' + quarter + '&budgetStatus=' + budgetStatus + '&rejectionComments=' + rejectionComments + '&approveComments=' + approveComments;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                if (req.responseText == "Added Successfully") {
                    $("costCenter").html(" <font color='green'>Budget Created Successfully.</font>");
                    document.getElementById("enter").style.display = "none";
                    document.getElementById("submit").style.display = "none";
                } else if (req.responseText == "updated Successfully") {
                    $("costCenter").html(" <font color='green'>Updated Successfully.</font>");
                    document.getElementById("submit").style.display = "none";
                    document.getElementById("enter").style.display = "none";
                    document.getElementById("ccbudgetSave").style.display = "none";
                    document.getElementById("ccbudgetSubmit").style.display = "none";
                }
                else {
                    $("costCenter").html(" <font color='red'>Not Updated.</font>");
                }

            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}
var myCalendar;
function doOnLoadCostCenter() {
    myCalendar = new dhtmlXCalendarObject(["startDate"]);
    myCalendar.setSkin('omega');
    myCalendar.setDateFormat("%m-%d-%Y");
    myCalendar.hideTime();
    myCalendar.attachEvent("onClick", function setDate(date) {
        var date1 = date;//alert(startDate);
        var dd = date1.getDate();
        var mm = date1.getMonth() + 1; //January is 0!
        var yyyy = date1.getFullYear();
        if (dd > 1) {
            dd = '01';
            document.getElementById("startDate").value = mm + "-" + dd + "-" + yyyy;
        }
        var mm1, yyyy1;
        if (mm < 10) {
            mm1 = mm + 3;
            yyyy1 = yyyy;
        }
        else if (mm == 10) {
            mm1 = 1;
            yyyy1 = yyyy + 1;
        }
        else if (mm == 11) {
            mm1 = 2;
            yyyy1 = yyyy + 1;
        }
        else if (mm == 12) {
            mm1 = 3;
            yyyy1 = yyyy + 1;
        }
        if (mm1 < 10) {
            mm1 = '0' + mm1;
        }
        //var month=Date.prototype.getMonthDays(mm1);
        //alert(month);
        if ((yyyy1 % 100 == 0 && yyyy1 % 400 == 0) || yyyy1 % 4 == 0) {
            Date._MD = new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        }
        else {
            Date._MD = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        }
        var month = mm1 - 2;
        if (month < 0) {
            month = 11;
        }
        dd = Date._MD[month];
        mm1 = month + 1;
        document.getElementById("endDate").value = mm1 + "-" + dd + "-" + yyyy1;
    });
}
function enterDateRepository()
{
    document.getElementById('startDate').value = "";
    document.getElementById('endDate').value = "";
    return false;
}
function costCenterInfoSearch() {
    initSessionTimer();
    var year = $("#year").val();
    var ccCode = $("#ccCode").val();
    var url = CONTENXT_PATH + '/costCenter/costCenterInfoSearchList.action?year=' + year + '&ccCode=' + ccCode;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            populateCostCenterInfoTable(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateCostCenterInfoTable(response) {
    //alert(response.length)
    $(".page_option").css('display', 'block');
    var costCenterSearchList = response.split("^");
    var table = document.getElementById("costCenterInfoTable");
    for (var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if (response.length > 0) {

        for (var i = 0; i < costCenterSearchList.length - 1; i++) {
            var Values = costCenterSearchList[i].split("|");
            {
                var row = $("<tr />")
                $("#costCenterInfoTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                var bal = Values[0] - Values[3];
                row.append($("<td>" + parseFloat(bal).toFixed(1) + "</td>"));
                if (Values[5] != "null")
                {
                    row.append($("<td>" + Values[5] + "</td>"));
                }
                else {
                    row.append($("<td>" + "" + "</td>"));
                }
                row.append($("<td>" + Values[6] + "</td>"));
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#costCenterInfoTable").append(row);
        row.append($('<td colspan="10"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }

    $('#costCenterInfoTable').tablePaginate({navigateType: 'navigator'}, recordPage);
    pager.init();

}
;
function noOfHoursValidate(event, id)
{
    //alert(id);
    var inputValue = (event.which) ? event.which : event.keyCode
    if ((inputValue > 32 && inputValue < 48 || inputValue > 57 && inputValue < 91 || inputValue > 90 && inputValue < 123 || inputValue > 122 && inputValue < 128) && (inputValue != 32))
    {
        if (id == 'editProjectTargetHrs') {

            $('updateProject').css("color", "red");
            $('updateProject').css("display", "inline-block");
            $("updateProject").html("Must be numeric value");
            $("updateProject").show().delay(2000).fadeOut()
        }
        else {
            $('#addProjectValidation').css("color", "red");
            $('#addProjectValidation').css("display", "inline-block");
            $("#addProjectValidation").html("Must be numeric value");
            $("#addProjectValidation").show().delay(2000).fadeOut()
        }
        if (inputValue == 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

function calculateRemainingHrs() {
    //alert("hi");
    var editProjectTargetHrs = document.getElementById("editProjectTargetHrs").value;
    var editProjectWorkedHrs = document.getElementById("editProjectWorkedHrs").value;

    var remaingHrs = editProjectTargetHrs - editProjectWorkedHrs;
    document.getElementById("editProjectRemainingHrs").value = parseFloat(remaingHrs).toFixed(1);
    var editProjectRemainingHrs = document.getElementById("editProjectRemainingHrs").value;
    if (editProjectRemainingHrs < 0)
    {
        $("#editProjectRemainingHrs").css("border", "1px solid red");
    }
    else {
        $("#editProjectRemainingHrs").css("border", "1px solid #ccc");
    }
//alert(remaingHrs);
}
function validationCostCenterYear(evt, id)
{
    var minRate = document.getElementById(id).value;
    var rate = (minRate.toString()).length;
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
        if (rate != 4)
        {
            $("#info").html(" <font color='red'>enter only numbers</font>");
            $("#info").show().delay(4000).fadeOut();
        }
        if (iKeyCode == 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    else if (rate >= 4)
    {
        if (iKeyCode == 8)
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
        $("#info").html("");
        return true;
    }
}
function costCenter_projects(cccode) {
    var specialBox = document.getElementById("resourceOverlay");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    $('#costCenterProjects_popup').popup(
            );
    showProjectsInCostCenter(cccode)
}
function costCenterProjectsOverlayClose() {
    setupOverlay('resourceOverlay', '#costCenterProjects_popup');
}
function showProjectsInCostCenter(ccCode) {
    var url = CONTENXT_PATH + '/costCenter/getProjectNamesInCostCenter.action?ccCode=' + ccCode;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            populateResourceInTable(req.responseText)

        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}
function calculateSubProjectTargetHrs() {
    var projectType = document.getElementById("projectType").value;
    var projectTargetHrs = document.getElementById("editProjectTargetHrs").value;
    var targetHours = document.getElementById("targetHours").value;
    if (projectType == "Sub-Project") {
        var remaingHrs = document.getElementById("remainingSubpjctTotalHrs").value;
        var updatedremainingHrs = parseFloat(remaingHrs) + parseFloat(targetHours);
        var diff = updatedremainingHrs - projectTargetHrs;
        if (diff < 0) {
            $("updateProject").html(" <font color='red'>Sub Project Target Hours must be less than " + parseFloat(updatedremainingHrs).toFixed(1) + " Hours</font>");
            $("#editProjectTargetHrs").css("border", "1px solid red");
            document.getElementById("editProjectTargetHrs").value = "";
        }
        else {
            $("updateProject").html("");
            $("#editProjectTargetHrs").css("border", "1px solid #ccc");
        }
    }
}
function calculateTargetHrs() {
    var projectTargetHrs = document.getElementById("projectTargetHrs").value;
    var remaingHrs = document.getElementById("remainingTargetHrs").value;
    //    var editProjectTargetHrs = document.getElementById("editProjectTargetHrs").value;
    //    var totalsubpjctTargetHrs = document.getElementById("totalTargetHrs").value;
    //    var val = editProjectTargetHrs -totalsubpjctTargetHrs;
    var diff = remaingHrs - projectTargetHrs;

    if (diff < 0) {
        $("#addProjectValidation").html(" <font color='red'>Sub Project Target Hours must be less than Specified Hours</font>");
        $("#projectTargetHrs").css("border", "1px solid red");
        document.getElementById("projectTargetHrs").value = "";
    }
    else {
        $("#addProjectValidation").html("");
        $("#projectTargetHrs").css("border", "1px solid #ccc");
    }
}
function validationCostCenterBudget(evt, id)
{
    var minRate = document.getElementById(id).value;
    var rate = (minRate.toString()).length;
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
        $("#info").html(" <font color='red'>enter only numbers</font>");
        $("#info").show().delay(4000).fadeOut();
        if (iKeyCode == 8)
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
        $("#info").html("");
        return true;
    }
}


function clearFieldValues()
{


    $("#projectNamePopup").css("border", "1px solid #ccc");
    $("#projectStartDateOverlay").css("border", "1px solid #ccc");
    $("#projectTargetDateOverlay").css("border", "1px solid #ccc");
    $("#projectTargetHrs").css("border", "1px solid #ccc");
    $("#projectReqSkillSetPopup").css("border", "1px solid #ccc");
    $("#project_descriptionPopup").css("border", "1px solid #ccc");
    $("#costCenterName").css("border", "1px solid #ccc");
}


function clearSubProjectFieldValues()
{


    $("#projectNamePopup").css("border", "1px solid #ccc");
    $("#projectStartDateOverlay").css("border", "1px solid #ccc");
    $("#projectTargetDateOverlay").css("border", "1px solid #ccc");
    $("#projectTargetHrs").css("border", "1px solid #ccc");
    $("#projectReqSkillSetPopup").css("border", "1px solid #ccc");
    $("#project_descriptionPopup").css("border", "1px solid #ccc");

}

function clearSubprojectOverlay()
{
    document.getElementById("projectNamePopup").value = "";
    document.getElementById("projectStartDateOverlay").value = "";
    document.getElementById("projectTargetDateOverlay").value = "";
    document.getElementById("projectTargetHrs").value = "";
    // document.getElementById("projectWorkedHrs").value = "0.0";
    document.getElementById("projectReqSkillSetPopup").value = "";
    document.getElementById("project_descriptionPopup").value = "";
}