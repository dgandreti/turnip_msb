

function getHomeRedirectSearchDetails() {
    initSessionTimer();
    document.getElementById("loadingHomeRedirectSearch").style.display="block"
    var accountName = $('#accountName').val();
    var typeOfUser = $('#typeOfUser').val();
    var primaryRole = $('#primaryRole').val();
    //alert("HI "+accountName+" "+typeOfUser+" "+primaryRole)
    var url = '../general/getHomeRedirectSearchDetails.action?accountName=' + accountName + '&typeOfUser=' + typeOfUser + '&primaryRole=' + primaryRole;
    //alert(url)
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            
            populateHomeRedirectTable(req.responseText);
            document.getElementById("loadingHomeRedirectSearch").style.display="none"
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);

}


function populateHomeRedirectTable(response) {
    var acc_type = $("#acc_type").val();
    //alert(acc_type)
    $(".page_option").css('display', 'block');
    //alert(response)
    var dashBoardReq = response.split("^");
    var table = document.getElementById("homeRedirectTable");
    for (var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if (response.length > 0) {
        for (var i = 0; i < dashBoardReq.length - 1; i++) {
            //alert(techReviewList[0])
            var Values = dashBoardReq[i].split("|");
            {
                var row = $("<tr />")
                $("#homeRedirectTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it

                row.append($('<td><a href=../general/doAddOrEditHomeRedirect.action?homeRedirectActionId=' + Values[0] + ">" + Values[4] + "</a></td>"));

                //row.append($("<td>" + Values[1] + "</td>"));
                if (acc_type != 'VC' && acc_type != 'AC') {
                    if (Values[1] != 'null' && Values[1] != "") {
                        row.append($("<td>" + Values[1] + "</td>"));
                    } else {
                        Values[1] = "All";
                        row.append($("<td>" + Values[1] + "</td>"));
                    }
                    row.append($("<td>" + Values[2] + "</td>"));
                }
                row.append($("<td>" + Values[3] + "</td>"));
                if (Values[5] != 'null' && Values[5] != "") {
                    if (Values[5].length > 9) {
                        //row.append($('<td><a href="#" class="homeRedirectComments_popup_open" onclick="homeRedirectCommentsDetailsToViewOnOverlay('+Values[0]+');homeRedirectCommentsPopup();">'+ Values[5].substr(0, 10) +"..</td>"));
                        row.append($('<td><a href="#" class="homeRedirectComments_popup_open" onclick="homeRedirectCommentsPopup(\'' + Values[5] + '\');">' + Values[5].substr(0, 10) + "..</td>"));
                    }
                    else {
                        row.append($("<td>" + Values[5] + "</td>"));
                    }
                } else {
                    Values[5] = "";
                    row.append($("<td>" + Values[5] + "</td>"));
                }
                //row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#homeRedirectTable").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#homeRedirectTable').tablePaginate({
        navigateType: 'navigator'
    }, recordPage);

}
function setAccNamesAndRoles() {
    //alert("HI")
    var accountType = $('#accountType').val();
    getAccountNamesForHomeRedirection();
    var url = '../general/getRolesByAccountType.action?accountType=' + accountType;
    //    alert(url)
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            setRolesForHomeRedirect(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}
function setRolesForHomeRedirect(responseTypes) {
    //alert(responseTypes)
    var taskType = document.getElementById("roleName");
    var flag = responseTypes.split("FLAG");
    var addTypes = flag[0].split("^");
    var $select = $('#roleName');
    $select.find('option').remove();
    for (var i = 0; i < addTypes.length - 1; i++) {
        var Values = addTypes[i].split("|");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select);
        }
    }
}
function storeAddOrEditHomeRedirectDetails() {

    var homeRedirectActionId = $('#homeRedirectActionId').val();
    var accountId = $('#accountId').val();
    var roleName = $('#roleName').val();
    var actionName = $('#actionName').val();
    var typeOfUser = $('#accountType').val();
    if (typeOfUser == 'M') {
        typeOfUser = 'SA';
    }
    var description = $('#description').val();
    var status = $('#status').val();
    var flag = true;
    //alert("accountId >"+accountId)
    if (accountId == 'null' || accountId == "") {
        accountId = 0;
    }
    if (actionName == 'null' || actionName == "") {
        flag = false;
    }
    flag = validateAction();
    if (flag == true) {
        //alert("HI "+homeRedirectActionId+" "+accountId+" "+roleName+" "+actionName+" "+typeOfUser+" "+description+" "+status)
        var url = '../general/storeAddOrEditHomeRedirectDetails.action?homeRedirectActionId=' + homeRedirectActionId
                + '&accountId=' + accountId
                + '&roleName=' + roleName
                + '&actionName=' + actionName
                + '&typeOfUser=' + typeOfUser
                + '&homeRedirectDescription=' + description
                + '&homeRedirectStatus=' + status;
        //alert(url)
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //alert(req.responseText)
                if (req.responseText == 'Success') {
                    $("e").html(" <font color='green'>Home Redirect Details saved Succesfully</font>");
                    $("#errorSpan").show().delay(5000).fadeOut();
                } else {
                    $("e").html(" <font color='red'>Sorry, This action is already exists for this Role!</font>");
                    $("#errorSpan").show().delay(5000).fadeOut();
                }
            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    } else {
        re = /^([/])+([A-Za-z0-9_])+([/]+[A-Za-z0-9_]+)*\.([a-zA-Z]{6})+$/;
        if (actionName.length == 0) {
            $("e").html(" <font color='red'>Action Should Not Empty!</font>");
            $("#errorSpan").show().delay(5000).fadeOut();
        } else if (!re.test(actionName)) {
            //alert("Not Valid")
            $("e").html("<font color='red'>Enter a Valid Action!</font>");
            $("#errorSpan").show().delay(5000).fadeOut();
        } else if (actionName.length > 100) {
            $("e").html("<font color='red'>Action Name Length Exceeded, Max 100 Chars Allowed!</font>");
            $("#errorSpan").show().delay(5000).fadeOut();
        }
        else {
            $("e").html(" <font color='red'>Action is Mandatory,Enter Valid Action!</font>");
            $("#errorSpan").show().delay(5000).fadeOut();
        }
    }
}
function homeRedirectCommentsPopup(comments) {
    //alert(addEditFlag)
    $("#commentsArea").val(comments);
    var specialBox = document.getElementById("preskillBox");
    if (specialBox.style.display == "block") {
        specialBox.style.display = "none";


    } else {
        specialBox.style.display = "block";


    }
    // Initialize the plugin    

    $('#homeRedirectComments_popup').popup(
            );
}
function homeRedirectCommentsDetailsToViewOnOverlay(homeId) {
    //alert("HI "+homeId)
    var url = '../general/getHomeRedirectCommentsDetails.action?homeRedirectActionId=' + homeId;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            $("#commentsArea").val(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}
function validateAction() {
    var flag = false;
    var actionName = $('#actionName').val();
    //    alert(actionName.length)
    re = /^([/])+([A-Za-z0-9_])+([/]+[A-Za-z0-9_]+)*\.([a-zA-Z]{6})+$/;
    if (!re.test(actionName)) {
        //alert("Not Valid")
        $("e").html(" <font color='red'>Action is Not a Valid Action!</font>");
        $("#errorSpan").show().delay(5000).fadeOut();
    } else if (actionName.length > 100) {

        $("e").html(" <font color='red'>Action Name Length Exceeded, Max 100 Chars Allowed!</font>");
        $("#errorSpan").show().delay(5000).fadeOut();
    }
    else {
        $("e").html("");
        flag = true;
    }
    return flag;
}
function ResponseCheckCharacters(id) {
    var elem = document.getElementById("ResponsecharNum");
    $(id).keyup(function() {
        el = $(this);
        if (el.val().length >= 500) {
            el.val(el.val().substr(0, 500));
        } else {

            elem.style.color = "green";
            $("#ResponsecharNum").text(500 - el.val().length + ' Characters remaining . ');
            $("#ResponsecharNum").show().delay(5000).fadeOut();
        }
        if (el.val().length == 500)
        {
            elem.style.color = "red";
            $("#ResponsecharNum").text(' Cannot enter  more than 500 Characters .');
            $("#ResponsecharNum").show().delay(5000).fadeOut();
        }

    })
    return false;
}
;
function setHRValues() {
    //        alert("KO")
    var homeRedirectActionId = document.getElementById("homeRedirectActionId").value;
    if (homeRedirectActionId == 0) {
        var acc_type = document.getElementById("acc_type").value;
        var org_name = $("#org_name").val();
        //    alert(acc_type)
        if (acc_type == 'AC' || acc_type == 'VC') {
            if (acc_type == 'AC') {
                $("#accountType").val("AC");
            }
            else
            {
                $("#accountType").val("VC");
            }
            //            $("#accountName").val(org_name);
            document.getElementById("accountName").disabled = true;
            document.getElementById("accountType").disabled = true;
        }
        else {
            $("#accountType").val("M");
            document.getElementById("accountType").disabled = false;
            document.getElementById("accountName").disabled = false;
        }
        setAccNamesAndRoles1();
    }
}


function getActionNames() {
    var roleName = $('#roleName').val();
    var url = '../general/getActionNames.action?roleId=' + roleName;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            setActionNameForHomeRedirect(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}
function setActionNameForHomeRedirect(responseTypes) {
    var Values = responseTypes.split("|");
    var $select = $('#actionName');
    $select.find('option').remove();
    for (var i = 0; i < Values.length - 1; i++) {
        {
            $('<option>').val(Values[i]).text(Values[i]).appendTo($select);
        }
    }
}
function setAccNamesAndRoles1() {
    var accountType = $('#accountType').val();
    //    getAccountNamesForHomeRedirection();
    var url = '../general/getRolesByAccountType.action?accountType=' + accountType;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            setRolesForHomeRedirect(req.responseText);
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}
function getActionDescription() {
    var actionName = $("#actionName").val();
    if (actionName == "") {
        $("#action_description").tooltip('hide')
                .attr('data-original-title', "")
                .tooltip('fixTitle')
                .tooltip('show');
    }
    else {
        var url = '../general/getActionDescription.action?actionName=' + actionName;
        var req = initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //            document.getElementById("action_description").title="";
                //            document.getElementById("action_description").title=req.responseText;
                $("#action_description").tooltip('hide')
                        .attr('data-original-title', req.responseText)
                        .tooltip('fixTitle')
                        .tooltip('show');
                //            tool_tip();
            }
        };
        req.open("GET", url, "true");
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send(null);
    }
}