google.load('visualization', '1.1', {
    'packages': ['corechart']
});
google.setOnLoadCallback();

function getCustomerRequirementsDashBoard(){
      $("#loadingReqDashboard").show();
    $("#reqCustomerYearChart").css('visibility', 'visible');
    var dashYears=$('#dashYears').val();
    var csrCustomers=$('#orgId').val();
    //alert("HI "+csrCustomers+"  "+dashYears)
      if(document.getElementById("accountNamePopup").value==""){
        csrCustomers="";
    }
    var url='../dashboard/getRequirementDashBoardDetails.action?dashYears='+dashYears+'&csrCustomer='+csrCustomers;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
              $("#loadingReqDashboard").hide();
            populateDashBoardTableForCsrRequirements(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateDashBoardTableForCsrRequirements(response){
    $(".page_option").css('display', 'block');
    //alert(response)
    var dashBoardReq=response.split("^");
    var table = document.getElementById("dashBoardTable");
    
    var cust = new Array();
    var open=new Array();
    var release =new Array();
    var close=new Array();
    
    var total=0;
    
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
                $("#dashBoardTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($('<td><a href="#" class="csrCustomerReq_popup_open" onclick="csrCustReqDetails('+Values[5]+');csrCustReqOverlay();">'+ Values[0] +"</td>"));
                //total= parseInt(total)+ parseInt(Values[0]);
                
                
                cust.push(Values[4]);
                open.push(parseInt(Values[1]));
                release.push(parseInt(Values[2]));
                close.push(parseInt(Values[3]));
            }
        }
        showChart(cust,open,release,close);
    }
    else{
        $("#reqCustomerYearChart").css('visibility', 'hidden');
        var row = $("<tr />")
        $("#dashBoardTable").append(row);
        row.append($('<td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    $('#dashBoardTable').tablePaginate({
        navigateType:'navigator'
    },recordPage);
} 
function csrCustReqOverlay(){
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#csrCustomerReq_popup').popup(      
        );    
    return false;
}
function csrCustReqDetails(accountId)
{
    var dashYears=$("#dashYears").val();
    //alert("HI  "+accountId+" "+dashYears)
    var url='../dashboard/getRequirementDashBoardDetailsOnOverlay.action?dashYears='+dashYears+'&accountId='+accountId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            populateDashBoardTableForCsrRequirementsOnOverlay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateDashBoardTableForCsrRequirementsOnOverlay(response){
    //alert(response)
    var dashBoardReq=response.split("^");
    var table = document.getElementById("dashBoardTableOnOverlay");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){

        var total=0;
        for(var i=0;i<dashBoardReq.length-1;i++){   
            //alert(techReviewList[0])
            var Values=dashBoardReq[i].split("|");
            {  
                var row = $("<tr />")
                $("#dashBoardTableOnOverlay").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                total= parseInt(total)+ parseInt(Values[1]);
            }
        }
        var row = $("<tr />")
        $("#dashBoardTableOnOverlay").append(row);
        row.append($('<td><font style= "color:red">Total</font></td>'));
        row.append($("<td><font style= 'color:red'>" + total + "</font></td>"));
    }
}

// aklakh javascript start
function dashboardMessage(message)
{
    //  alert(message.id);
    if(message.id=="customerBoard"){
        document.getElementById("headingmessage").innerHTML='Customer Dashboard<i  class="fa fa-angle-up " id="updownArrowAccount" onclick="toggleContentAccount(\'customerDiv\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
    }
    if(message.id=="vendorBoard"){
        document.getElementById("headingmessage").innerHTML='Vendor Dashboard<i  class="fa fa-angle-up " id="updownArrowAccount" onclick="toggleContentAccount(\'vendorDivision\')" style="margin-top: 0vw;position:absolute;color:#56a5ec"> </i>';
    }
}


function getCustomerDashboardList(){
    $("#individualCustomerYearChart").css('visibility', 'visible');
    document.getElementById('chartTitle').innerHTML = "<font color=green>Customer Requirements Yearly Analysis</font>";

    var dashYears=$('#year').val();
    var dashMonths=$('#month').val();
    //alert("HI "+csrCustomers+"  "+dashYears)
    
    var url='../dashboard/getCustomerRequirementDashBoardDetails.action?dashYears='+dashYears+'&dashMonths='+dashMonths;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            populateCustomerDashBoardTable(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function populateCustomerDashBoardTable(response){
    //alert(response.length)
    $(".page_option").css('display', 'block');
    var dashBoardReq=response.split("^");
    var table = document.getElementById("customerDashboardResults");
    var month = new Array();
    var open=new Array();
    var release =new Array();
    var openForResume =new Array();
    var close=new Array();
    var others=new Array();
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length!=0){

        var total=0;
        for(var i=0;i<dashBoardReq.length-1;i++){   
            //alert(techReviewList[0])
            var Values=dashBoardReq[i].split("|");
            {  
                //alert(Values[0])
                var row = $("<tr />")
                $("#customerDashboardResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[6] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                
                row.append($("<td>" + Values[5] + "</td>"));
                
                row.append($("<td>" + Values[4] + "</td>"));
                
                month.push(Values[0]);
                open.push(parseInt(Values[1]));
                release.push(parseInt(Values[2]));
                openForResume.push(parseInt(Values[6]));
                close.push(parseInt(Values[3]));
                others.push(parseInt(Values[5]))
            }
        }
        showCustomerChart(month,open,release,openForResume,close,others);
        
    }
    else{
        $("#individualCustomerYearChart").css('visibility', 'hidden');
        var row = $("<tr />")
        $("#customerDashboardResults").append(row);
        row.append($('<td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
        
    }
    $('#customerDashboardResults').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    pager.init(); 
   
}

function showChart(cust,open,release,close)
{
    //alert(month.length);
     
    var Combined = new Array();
    Combined[0] = ['Customer', 'open', 'release','close'];
    for (var i = 0; i < cust.length; i++){
        Combined[i + 1] = [ cust[i], open[i], release[i],close[i] ];
    }
    //second parameter is false because first row is headers, not data.
    var data = google.visualization.arrayToDataTable(Combined, false);
    
    var options = {
        //        width: 370,
        //        height:300,
        title: 'Customer Requirements Yearly Analysis',
        colors: ['#0000FF', '#00FF00', '#FF0000'],
        legend: {
            position: 'top', 
            alignment: 'center'
        }
    // animation: {
    //duration: 1000,
    //easing: 'linear'
    //vAxis: {
    //viewWindow: {
    //max: 8
    }
    var chart = new google.visualization.ColumnChart(document.getElementById('reqCustomerYearChart'));
   
    // Instantiate and draw our chart, passing in some options.
    chart.draw(data, options);
    
    $(window).resize(function () {
        chart.draw(data, options);
    });
}


function showCustomerChart(month,open,release,openForResume,close,others)
{
    //alert(month.length);
     
    var Combined = new Array();
    Combined[0] = ['Month', 'open', 'release','open for resume','close','others'];
    for (var i = 0; i < month.length; i++){
        Combined[i + 1] = [ month[i], open[i], release[i],openForResume[i],close[i],others[i] ];
    }
    //second parameter is false because first row is headers, not data.
    var data = google.visualization.arrayToDataTable(Combined, false);
    
    var options = {
        //        width: 370,
        //        height:300,
        // title: 'Customer Requirements Yearly Analysis',
        colors: ['#0000FF', '#00FF00', '#FF0000','#00BFFF'],
        legend: {
            position: 'top', 
            alignment: 'center'
        }
        
    // animation: {
    //duration: 1000,
    //easing: 'linear'
    //vAxis: {
    //viewWindow: {
    //max: 8
    }
    var chart = new google.visualization.ColumnChart(document.getElementById('individualCustomerYearChart'));
    
    // Instantiate and draw our chart, passing in some options.
    chart.draw(data, options);
  
    $(window).resize(function () {
        chart.draw(data, options);
    });
}

function getAccountsNames(accType) {
    //alert(accType);
    if(accType=='V'){
        var accName=(document).getElementById('vendorAccountNamePopup').value;  
    }
    else
    {
        var accName=(document).getElementById('accountNamePopup').value;
    }
    
    var accNameFlag="dashboard";
    //var v_empName=id.value;
    // alert("---"+accName);
    if (accName == "") {
        clearsTable(accType);
    } else {
        
        //alert("Empname->"+v_empName+"--len-->"+v_empName.length);
    
        if(accName.length>=1){
            var url='../accauth/getAccountNames.action?accType='+ accType +'&accName='+accName+'&accNameFlag='+accNameFlag;
            //alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // alert(req.responseXML);
                        parseAccountName(req.responseXML,accType);
                    } else if (req.status == 204){
                        clearsTable(accType);
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseAccountName(responseXML,accType) {
    // alert("hii");
    clearsTable(accType);
    //alert("hii");
    var consultants = responseXML.getElementsByTagName("ACCOUNTS")[0];
    //alert(consultants.childNodes.length);
    if (consultants.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearsTable(accType);
    }
    //alert("Hello"+consultants.childNodes.length)
 

    //alert("Hello")
    
    var consultant = consultants.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        if(accType=='V'){
            var validationMessage=document.getElementById("validationMessageForVendor");
            validationMessage.innerHTML = "";
        }
        else
        {
            var validationMessage=document.getElementById("validationMessageForCustomer");
            validationMessage.innerHTML = "";   
        }
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < consultants.childNodes.length; loop++) {
            var consultant = consultants.childNodes[loop];
            var accId = consultant.getElementsByTagName("ACCOUNTID")[0];
            var accName = consultant.getElementsByTagName("NAME")[0];
            //alert(empName.childNodes[0].nodeValue)
            //alert(loginId.childNodes[0].nodeValue)

            appendAccounutName(accName.childNodes[0].nodeValue,accId.childNodes[0].nodeValue,accType);
        }
        var position;
        if(accType=='V'){
            position = findPosition(document.getElementById("vendorAccountNamePopup"));
        }
        else
        {
            position = findPosition(document.getElementById("accountNamePopup"));
        }
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        if(accType=='V'){
            var validationMessageForVendor=document.getElementById("validationMessageForVendor");
            validationMessageForVendor.innerHTML = "<font color=red>  Account doesn't Exists </font>";
        }
        else
        {
            var validationMessageForCustomer=document.getElementById("validationMessageForCustomer");
            validationMessageForCustomer.innerHTML = "<font color=red>  Account doesn't Exists </font>"; 
        }
    //document.getElementById('validationMessage').innerHTML = "<font color=red> Employee doesn't Exists!</font>";
    }
    if(consultants.childNodes.length<10) {
        // autorow1.style.overflowY = "hidden";
        autorow.style.overflowY = "hidden";
    }
    else {
        // autorow1.style.overflowY = "scroll";
        autorow.style.overflowY = "scroll";
    }
}

function appendAccounutName(accName,accId,accType) {
    //alert("deaswwwasfd")
    var row;
    var nameCell;
    if (!isIE) {
        row = completeTable.insertRow(completeTable.rows.length);
        nameCell = row.insertCell(0);
    } else {
        row = document.createElement("tr");
        nameCell = document.createElement("td");
        row.appendChild(nameCell);
        completeTable.appendChild(row);
    }
    row.className = "popupRow";
    nameCell.setAttribute("bgcolor", "#fff");
    var linkElement = document.createElement("a");
    linkElement.className = "popupItem";


    linkElement.setAttribute("href",
        "javascript:setAccountName('"+accName +"','"+ accId +"','"+ accType +"')");
    linkElement.appendChild(document.createTextNode(accName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}

function setAccountName(accName,accId,accType){
    clearsTable(accType);
    // alert(accName);
    //  alert(accId);
    if(accType=='V'){
        document.getElementById("vendorAccountNamePopup").value =accName;
        document.getElementById("vendorOrgId").value =accId;
    }
    else
    {
        document.getElementById("accountNamePopup").value =accName;
        document.getElementById("orgId").value =accId;
    }
}
function clearsTable(accType) {
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        if(accType=='V'){
            var validationMessageForVendor=document.getElementById("validationMessageForVendor");
            validationMessageForVendor.innerHTML = " ";
        }
        else
        {
            var validationMessageForCustomer=document.getElementById("validationMessageForCustomer");
            validationMessageForCustomer.innerHTML = " "; 
        }
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}
function removeTextFieldData()
{
    var vendorOrgIdText=$("#vendorAccountNamePopup").val();
    var customerOrgIdText=$("#accountNamePopup").val();
    if(vendorOrgIdText==""){
        document.getElementById("vendorOrgId").value=""; 
    }
    if(customerOrgIdText==""){
        document.getElementById("orgId").value=null;
        document.getElementById("costCenters").value=-1;
    }
    return true;
}
function validationDashboardYear(evt)
{
    
    //alert("alert alert")
    var  minRate=document.getElementById("year").value;
    //alert(minRate)
    
    var rate=(minRate.toString()).length;
   
    //alert(rate)
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate != 4)
        {
            $("#customerDashValidation").html(" <font color='red'>enter only numbers</font>");  
            $("#customerDashValidation").show().delay(4000).fadeOut();
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
        $("#customerDashValidation").html("");
        return true;
    }
    
}
function validationDashboardVendorYear(evt)
{
    
    //alert("alert alert")
    var  minRate=document.getElementById("vdashYears").value;
    //alert(minRate)
    
    var rate=(minRate.toString()).length;
   
    // alert(rate)
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate!=4)
        {   
             
            //alert(rate+"rate is")
            $("#reqVendorDashboard").html(" <font color='red'>Enter only numbers</font>");  
            $("#reqVendorDashboard").show().delay(4000).fadeOut();
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
        $("#reqVendorDashboard").html("");
        return true;
    }
    
}
function validationDashboardCustomerYear(evt)
{
    
    //alert("alert alert")
    var  minRate=document.getElementById("dashYears").value;
    //alert(minRate)
    
    var rate=(minRate.toString()).length;
   
    //alert(rate)
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    
    if ( iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57) )
    {
        //alert("enter only numbers ")
        if(rate!=4)
        {    
            $("#reqCustomerDashBoardValidation").html(" <font color='red'>Enter only numbers</font>");  
            $("#reqCustomerDashBoardValidation").show().delay(4000).fadeOut();
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
        $("#reqCustomerDashBoardValidation").html("");
        return true;
    }
    
}

function getQuartersChart(){
    var year=document.getElementById("dashBoardYear").value;
    document.getElementById('costCentersDiv').style.display = 'none'; 
    document.getElementById('costCentersProjectsDiv').style.display = 'none';
    if((year.length)==4){
 
        getCostCenterDashboardList(year,"yearquaters");
    }
}

function getCostCenterDashboardList(selectedValue,flag){
    
    var quarters=null;
    var costcenternames=null;
    
    if(flag=="quarters")
    {
        document.getElementById('costCentersProjectsDiv').style.display = 'none';
        document.getElementById("quarters").value=selectedValue;
        quarters=selectedValue; 
    // alert("quarters value-->"+quarters);
    }
    
    if(flag=="costcenters")
    {
        //  var costcenternames=document.getElementById("costcenternames").value; 
        quarters=document.getElementById("quarters").value;
        costcenternames=selectedValue;
    // alert("in if costcenternames-->"+costcenternames);
    }
    
    if(flag=="yearquaters")
    {
        quarters=flag;
    }
    
    
    
    var dashYears=$('#dashBoardYear').val();
    var orgId=$('#orgId').val();
    
    // alert("quarters-->"+quarters);
    // alert("costcenternames--> "+costcenternames);
    
    if(orgId!=null && orgId!=""){
        var url='../costCenter/getCostCentersDashboardForOrg.action?quarter='+quarters+'&costCenters='+costcenternames+'&dashBoardYear='+dashYears+'&orgId='+orgId; 
    }
    else
    {
        var url='../costCenter/getCostCenterDashboardList.action?quarter='+quarters+'&dashBoardYear='+dashYears+'&orgId='+orgId;
    }
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingDashboardPage').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
            $('#loadingDashboardPage').hide();
            populateCostCenterDashBoardTable(req.responseText,quarters,costcenternames);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function populateCostCenterDashBoardTable(response,quarters,costcenternames){
    //    alert(response)
    $("#Barchart").css('visibility', 'visible');
    $("#norecords").css('visibility', 'hidden');
    var dashBoardReq=response.split("^");
    //   alert("dashBoardReq"+dashBoardReq);
    var month = new Array();
    var open=new Array();
    var release =new Array();
    var close=new Array();
    var costCenterIdsArray=new Array();
    
    if(response=="nocostcenters"){
        //  alert("nocostcenters");
        document.getElementById("costCentersDiv").style.display = 'block';
        document.getElementById("costcentersBarchart").style.display = 'none';
        document.getElementById("costcentersnorecords").style.display = 'block';
        // $("#costCentersDiv").css('visibility', 'visible');
        // $("#costcentersBarchart").css('visibility', 'hidden');
        // $("#costcentersnorecords").css('visibility', 'visible');
        // document.getElementById("norecords").style.display = 'block';
        document.getElementById('costcentersnorecords').innerHTML = "<font color=red> No Cost Centers to Display!</font>";
        // var v=$('#quarter option:selected').text();
        document.getElementById('costcenterchartTitle').innerHTML = "<font color=red >&nbsp; "+ quarters +" </font><font color=green>Cost Center Budget Yearly Analysis</font>";
        document.getElementById("costCentersProjectsDiv").style.display = 'none';
    // document.getElementById("projectsnorecords").style.display = 'none';        
    }
    else if(response=="noprojects"){
        //  alert("noprojects");
        //$("#Barchart").css('visibility', 'hidden');
        document.getElementById("costCentersProjectsDiv").style.display = 'block';
        document.getElementById("projectsnorecords").style.display = 'block';
        document.getElementById('projectsnorecords').innerHTML = "<font color=red> No Projects to Display!</font>";
        //  var v=$('#costCenters option:selected').text();
        document.getElementById('projectschartTitle').innerHTML = "<font color=red >&nbsp; "+ costcenternames +" </font><font color=green>Cost Center Projects Budget Yearly Analysis</font>";
        document.getElementById("projectsBarchart").style.display = 'none';
        
    // document.getElementById("BarchartForResources").style.display = 'none';
    //  document.getElementById("resorceschartTitle").style.display = 'none';
    //document.getElementById("resourcesnorecords").style.display = 'none';     
    }
    else
    {
        //  var $select = $('#costCenters');
        //        $select.find('option').remove();
        //        $('<option>').val(-1).text('All').appendTo($select);
       
        for(var i=0;i<dashBoardReq.length-1;i++){   
            //alert(techReviewList[0])
            var Values=dashBoardReq[i].split("|");
            {  
                //alert(Values[0])
                month.push(Values[0]);
                open.push(parseInt(Values[1]));
                release.push(parseInt(Values[2]));
                close.push(parseInt(Values[3]));
                var costcenterResponse=Values[4];
                if(costcenterResponse=="costcenterresponse"){
                    costCenterIdsArray.push(Values[5]);
                }
            //                if(costcenterResponse=="costcenterresponse" && i==0){
            //                    
            //                    //document.getElementById("costCentersSelectDiv").style.display = 'block';
            //                    $select.find('option').remove();
            //                    $('<option>').val(-1).text('All').appendTo($select);
            //                }
            //                if(costcenterResponse=="costcenterresponse"){
            //                    
            //                    $('<option>').val(Values[5]).text(Values[0]).appendTo($select);  
            //                }
            }
        }
        if(costcenterResponse=="costcenterresponse"){
            //  document.getElementById("costCentersSelectDiv").style.display = 'block';
            document.getElementById("costCentersDiv").style.display = 'block';
            document.getElementById("costcentersnorecords").style.display = 'none';
           
            document.getElementById("costcentersBarchart").style.display = 'block'; 
        // $("#costCentersSelectDiv").css('visibility', 'visible'); 
        }
        if(costcenterResponse=="costcenterpojectresponse"){
            //  document.getElementById("costCentersSelectDiv").style.display = 'block';
            document.getElementById("costCentersProjectsDiv").style.display = 'block';
            document.getElementById("projectsnorecords").style.display = 'none';
            document.getElementById("projectsBarchart").style.display = 'block'; 
        // $("#costCentersSelectDiv").css('visibility', 'visible'); 
        }
        //   alert("cost-->"+month);
        //   alert("budget-->"+open);
        //   alert("spent-->"+release);
        //  alert("bal-->"+close);
        
        showCostCenterChart(month,open,release,close,costcenterResponse,quarters,costcenternames);
        
    }
//    else{
//        
//        $("#Barchart").css('visibility', 'hidden');
//        $("#norecords").css('visibility', 'visible');
//        document.getElementById('norecords').innerHTML = "<font color=red> No Records to Display!</font>";
//        
//    }
}

function showCostCenterChart(month,open,release,close,costcenterResponse,quarters,costcenternames)
{
    //  alert("costCenterIdsArray-->"+costCenterIdsArray);
    var Combined = new Array();
    if(costcenterResponse=="costcenterresponse"){
        Combined[0] = ['Cost Center','Budget Amount', 'Consumed Amount','Balance Amount'];  
    }
    else if(costcenterResponse=="costcenterpojectresponse"){
        Combined[0] = ['Projects','Budget Amount', 'Consumed Amount','Balance Amount'];  
    }
    else
    {
        Combined[0] = ['Account','Budget Amount', 'Consumed Amount','Balance Amount'];   
    }
    
    //    document.getElementById("costIds").value=costCenterIdsArray;
    //    document.getElementById("costNames").value=month;
    
    for (var i = 0; i < month.length; i++){
        Combined[i + 1] = [ month[i], open[i], release[i],close[i] ];
    }
    // alert("Combined-->"+Combined);
    //second parameter is false because first row is headers, not data.
    var data = google.visualization.arrayToDataTable(Combined, false);
    //  var v=$('#quarter option:selected').text();
    if(costcenterResponse=="costcenterresponse"){
        document.getElementById("costCentersDiv").style.display = 'block'; 
        // var v=$('#quarter option:selected').text();
        document.getElementById('costcenterchartTitle').innerHTML = "<font color=red >&nbsp; " + quarters +" </font><font color=green>Cost Centers Budget Yearly Analysis</font>";
        var options = {
            //        width: 370,
            //        height:300,
            // title: 'Cost Centers Yearly Analysis',
            colors: ['#0000FF', '#FF0000', '#00FF00'],
            titleColor:"green",
            'is3D':true,
            // backgroundColor: '#1b1b1b',
            vAxis: {
                title: "Budget Amount",
                format:'$###,###,###.00',
                titleColor:"green"
            //                textStyle: {color: '#24BEFF'}
            },
            hAxis: {
                title: "Cost Centers",
                titleColor:"green"
            //                textStyle: {color: '#24BEFF'}
            },
            legend: {
                position: 'top', 
                alignment: 'center'
            }
        }
    }
    else if(costcenterResponse=="costcenterpojectresponse"){
        document.getElementById("costCentersProjectsDiv").style.display = 'block';
        document.getElementById('projectschartTitle').innerHTML = "<font color=red >&nbsp; "+ costcenternames +" </font><font color=green>Cost Center Projects Budget Yearly Analysis</font>";
        var options = {
            //        width: 370,
            //        height:300,
            //  title: 'Cost Center Projects Yearly Analysis',
            colors: ['#0000FF', '#FF0000', '#00FF00'],
            titleColor:"green",
            'is3D':true,
            vAxis: {
                title: "Budget Amount",
                format:'$###,###,###.00',
                titleColor:"green"
            },
            hAxis: {
                title: "Projects",
                titleColor:"green"
            },
            legend: {
                position: 'top', 
                alignment: 'center'
            }
        }
    }
    else
    {
        document.getElementById('chartTitle').innerHTML = "<font color=green>Budget Analysis</font>";
        var options = {
            titleColor:"green",
            colors: ['#0000FF', '#FF0000', '#00FF00'],
            vAxis: {
                title: "Budget Amount",
                format:'$###,###,###.00',
                titleColor:"green"
            },
            hAxis: {
                title: "Quartes",
                titleColor:"green"
            },
            legend: {
                position: 'top', 
                alignment: 'center'
            }
        }
    }
    if(costcenterResponse=="costcenterresponse"){
        var chart = new google.visualization.ColumnChart(document.getElementById('costcentersBarchart'));
        
        function selectHandlers() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
                var practice = data.getValue(selectedItem.row, 0);
                // alert('The user selected--> '+practice);
                getCostCenterDashboardList(practice,"costcenters");
            }
        }
        google.visualization.events.addListener(chart, 'select', selectHandlers);
       
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
        
        $(window).resize(function () {
            chart.draw(data, options);
        });
    }
    else if(costcenterResponse=="costcenterpojectresponse")
    {
        //  alert("in else");
        var chart = new google.visualization.ColumnChart(document.getElementById('projectsBarchart'));
       
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
        
        $(window).resize(function () {
            chart.draw(data, options);
        });
    }
    else
    {
        var chart = new google.visualization.ColumnChart(document.getElementById('Barchart'));
        
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
                var practice = data.getValue(selectedItem.row, 0);
                //    alert('The user selected--> '+practice);
                // document.getElementById('costCentersProjectsDiv').style.display = 'none';
                getCostCenterDashboardList(practice,"quarters");
            }
        }
        google.visualization.events.addListener(chart, 'select', selectHandler);
       
       
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
        
        $(window).resize(function () {
            chart.draw(data, options);
        });
    }
}
function settingValue(){
    //var v=document.getElementById("costCenters").value;  
    //alert(v);
    document.getElementById("costCenters").value=-1;
    document.getElementById("costCentersSelectDiv").style.display = 'none';
    document.getElementById("costCentersProjectsDiv").style.display = 'none';
    var v=document.getElementById("quarter").value
    if(v=="DF"){
        document.getElementById("costCentersDiv").style.display = 'none';
    }
//$("#costCentersSelectDiv").css('visibility', 'hidden');
}
function removeProjectsDiv(){
    var v=document.getElementById("costCenters").value; 
    if(v==-1){
        document.getElementById("costCentersProjectsDiv").style.display = 'none';   
    }
}

function getMainProjectsChart(){
    var year=document.getElementById("dashBoardYear").value;
    document.getElementById('subProjectsDiv').style.display = 'none'; 
    document.getElementById('resouresDiv').style.display = 'none';
    if((year.length)==4){
        // alert(year.length);
        getProjectDashboardList(year,"yearmainprojects");
    }
}

function getProjectDashboardList(selectedValue,flag){
     alert("selectedValue-->"+selectedValue);
    // $("#individualCustomerYearChart").css('visibility', 'visible');
    
  
    // var mainprojects=null;
    // var subprojects=null;
    // alert("flag-->"+flag);
    
    
    if(flag=="mainprojectsselected")
    {
        var subprojectsMap=document.getElementById("mainprojectsList").value;
           alert("subprojectsMap-->"+subprojectsMap);
    
        var flags=subprojectsMap.split("FLAG");
        var addList=flags[0].split("^");
        for(var k=0;k<addList.length-1;k++){        
            var subproValues=addList[k].split("=");
            if (subproValues[1] == selectedValue) {
                finalId=subproValues[0];
                break ;
            } 
        }
       flag="mainprojects"; 
    }
    
   else if(flag=="mainprojects")
    {
        var mainprojectsList=document.getElementById("mainprojectsList").value;
         alert("mainprojectsList"+mainprojectsList);
    
        var mainprojectsValues=  mainprojectsList.replace(/[{()}]/g, '');
        var mainprofflagList=mainprojectsValues.split("FLAG")
        var totalSkills=mainprofflagList[0].split(",");
  
        var finalId=0;   
        for (var i = 0; i < totalSkills.length; i++){ 
            var Values=totalSkills[i].split("=");
            if (Values[1] == selectedValue) {
                finalId=Values[0]
                break ;
            }
        }
    }
    
  else if(flag=="subprojects")
    {
        var subprojectsMap=document.getElementById("subprojectsMap").value;
           alert("subprojectsMap-->"+subprojectsMap);
    
        var flags=subprojectsMap.split("FLAG");
        var addList=flags[0].split("^");
        for(var k=0;k<addList.length-1;k++){        
            var subproValues=addList[k].split("=");
            if (subproValues[1] == selectedValue) {
                finalId=subproValues[0];
                break ;
            } 
        }
    }
    var dashYears=$('#dashBoardYear').val();
    if(flag=="subprojects"){
        // alert(" in if");
        //  var costCenters=$('#costCenters').val();
        var url='getProjectDashboardList.action?dashBoardYear='+dashYears+'&subProjects='+finalId+'&projectsFlag='+flag;
    }
    else if(flag=="yearmainprojects"){
        var url='getProjectDashboardList.action?dashBoardYear='+selectedValue+'&projectsFlag='+flag;
    }
    else
    {
        var url='getProjectDashboardList.action?dashBoardYear='+dashYears+'&mainProjectID='+finalId+'&projectsFlag='+flag;
        document.getElementById("resouresDiv").style.display = 'none';   
    }
    //  alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingDashboardPage').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
           // alert(req.responseText);
            $('#loadingDashboardPage').hide();
            populateProjectsDashBoardTable(req.responseText,selectedValue);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function populateProjectsDashBoardTable(response,selectedValue){
    //  alert(response)
    $("#subProjectsBarchart").css('visibility', 'visible');
    // document.getElementById("subProjects").style.display = 'block';
    $("#norecords").css('visibility', 'hidden');
    var dashBoardReq=response.split("^");
    var project = new Array();
    var workedHrs=new Array();
    var targetHrs =new Array();
    // var close=new Array();
   
    // $select.find('option').remove();
    // $('<option>').val(-1).text('All').appendTo($select);
    
    if(response.length!=0){
        // var $select = $('#subProjects');
        if(response=="nomainprojects"){
            // alert("nomainprojects");
            document.getElementById("Barchart").style.display = 'none';
            document.getElementById("subProjectsBarchart").style.display = 'none';
            document.getElementById("norecords").style.display = 'block';
            //            $("#Barchart").css('visibility', 'hidden');
            //            $("#subProjectsBarchart").css('visibility', 'hidden');
            //            $("#norecords").css('visibility', 'visible');
            //  document.getElementById("norecords").style.display = 'block';
            document.getElementById('norecords').innerHTML = "<font color=red> No Projects to Display!</font>";
            document.getElementById("resouresDiv").style.display = 'none';
            document.getElementById("resourcesnorecords").style.display = 'none';
        }
        else if(response=="nosubprojects"){
            //   alert("nosubprojects-->"+response);
            // $("#subProjectsBarchart").css('visibility', 'hidden');
            // $("#subProjectsnorecords").css('visibility', 'visible');
            
            document.getElementById("subProjectsDiv").style.display = 'block';
            document.getElementById("subProjectsBarchart").style.display = 'none';
            document.getElementById("subProjectsnorecords").style.display = 'block';
            // document.getElementById("norecords").style.display = 'block';
            
            //var v=$('#projects option:selected').text();
            document.getElementById('subProjectsTitle').innerHTML = "<font color=red >&nbsp; "+ selectedValue +" </font><font color=green>Sub Project(s) Hours Analysis</font>";
           
            // document.getElementById("norecords").style.display = 'block';
            document.getElementById('subProjectsnorecords').innerHTML = "<font color=red> No Sub Projects to Display!</font>";
            document.getElementById("resouresDiv").style.display = 'none';
            document.getElementById("resourcesnorecords").style.display = 'none';   
        // alert("nosubprojects end-->"+response);
        }
        else if(response=="noresources"){
            //   alert("noresources");
            //$("#Barchart").css('visibility', 'hidden');
            document.getElementById("resouresDiv").style.display = 'block';
            document.getElementById("resourcesnorecords").style.display = 'block';
            
            // var v=$('#subProjects option:selected').text();
            document.getElementById('resorceschartTitle').innerHTML = "<font color=red >&nbsp; "+ selectedValue +" </font><font color=green>Project Resources Hours Analysis</font>";
      
            
            
            document.getElementById('resourcesnorecords').innerHTML = "<font color=red> No Resources to Display!</font>";
            document.getElementById("BarchartForResources").style.display = 'none';
            document.getElementById("resorceschartTitle").style.display = 'none';
        //document.getElementById("resourcesnorecords").style.display = 'none';     
        }
        else
        {
            var subprojectsMap="";
            var mainprojectsList="";
            //        $select.find('option').remove();
            //        $('<option>').val(-1).text('All').appendTo($select);
            for(var i=0;i<dashBoardReq.length-1;i++){   
                //alert(techReviewList[0])
                var Values=dashBoardReq[i].split("|");
                {  
                    //alert(Values[0])
                    project.push(Values[0]);
                    targetHrs.push(parseInt(Values[1]));
                    workedHrs.push(parseInt(Values[2]));
                    //close.push(parseInt(Values[3]));
                    var subProjects=Values[5];
                    // alert("subProjects--values--"+subProjects);
                    if(subProjects=="mainprojects"){
                        mainprojectsList=mainprojectsList+Values[3]+"="+Values[0]+"^";
                        document.getElementById("mainprojectsList").value=mainprojectsList;
                       // alert(document.getElementById("mainprojectsList").value);
                        
                        //document.getElementById("mainProjectsDiv").style.display = 'block';
                        //  $("#Barchart").css('visibility', 'visible');
                        document.getElementById("Barchart").style.display = 'block';
                    }
                    if(subProjects=="subprojects"){
                        //   alert("in if-->")
                        subprojectsMap=subprojectsMap+Values[3]+"="+Values[0]+"^";
                        document.getElementById("subProjectsnorecords").style.display = 'none';
                        document.getElementById("subProjectsDiv").style.display = 'block';
                        document.getElementById("subProjectsBarchart").style.display = 'block';
                        document.getElementById("subprojectsMap").value=subprojectsMap;
                      
                    }
                    
                    
                    var resourceHours=Values[3];
                //  alert("Sub project ids"+Values[3]);
                
                //                if(costcenterResponse=="costcenterpojectresponse"){
                //                //  $('<option>').val(Values[5]).text(Values[6]).appendTo($select);    
                //                }
                //alert(month);
                }
            }
            //   alert(project);
            //  alert(targetHrs);
            //  alert(workedHrs);
            //            if(subProjects=="subprojects"){
            //                //  alert("in if")
            //                document.getElementById("subProjectsSelectDiv").style.display = 'block';
            //            //            // $("#costCentersSelectDiv").css('visibility', 'visible'); 
            //            }
            if(resourceHours=="resourceworkedhrs")
            {
                //   alert(resourceHours);
                document.getElementById("resouresDiv").style.display = 'block';
                document.getElementById("BarchartForResources").style.display = 'block';
                document.getElementById("resorceschartTitle").style.display = 'block';
                document.getElementById("resourcesnorecords").style.display = 'none'; 
            }
        
            // alert(costcenterResponse);
            showProjectsChart(project,targetHrs,workedHrs,subProjects,resourceHours,selectedValue);
        
        }
    //  alert("subprojectsMap-->"+subprojectsMap);
        
        
    //  $("#Barchart").css('visibility', 'hidden');
    //  $("#norecords").css('visibility', 'visible');
    //  document.getElementById('norecords').innerHTML = "<font color=red> No Records to Display!</font>";
    //  $("#resouresDiv").css('visibility', 'hidden');
    //  $("#resourcesnorecords").css('visibility', 'visible');
    //  document.getElementById('resourcesnorecords').innerHTML = "<font color=red> No Records to Display!</font>";
        
        
    }
}

function showProjectsChart(project,targetHrs,workedHrs,subProjects,resourceHours,selectedValue)
{
    //    alert("showProjectsChart-->"+subProjects);
    //    var quarter=$('#quarter').val();
    //    if(quarter=="DF"){
    //        document.getElementById("costCentersSelectDiv").style.display = 'none';   
    //    //$("#costCentersSelectDiv").css('visibility', 'hidden');
    //    // var v=document.getElementById("costCenters").value;
    //    // document.getElementById("costCenters").value=-1;
    //    }
    var Combined = new Array();
    //    if(costcenterResponse=="costcenterresponse"){
    //        Combined[0] = ['Cost Center','Budget Amount', 'Consumed Amount','Balance Amount'];  
    //    }
    //    else if(costcenterResponse=="costcenterpojectresponse"){
    //        Combined[0] = ['Projects','Budget Amount', 'Consumed Amount','Balance Amount'];  
    //    }
    //    else
    //    {
    if(resourceHours=="resourceworkedhrs"){
        Combined[0] = ['Projects','Consumed Hours']; // 
    }
    else
    {
        Combined[0] = ['Projects','Target Hours', 'Worked Hours'];   
    }
     
    //    }
    if(resourceHours=="resourceworkedhrs"){
        for (var i = 0; i < project.length; i++){
            Combined[i + 1] = [ project[i], targetHrs[i]];
        } 
    }
    else
    {
      //  alert("subProjects-->"+subProjects);
        for (var i = 0; i < project.length; i++){
            Combined[i + 1] = [ project[i], targetHrs[i], workedHrs[i] ];
        }
    }
    //second parameter is false because first row is headers, not data.
    var data = google.visualization.arrayToDataTable(Combined, false);
    // alert("main Projects data-->"+data);
    if(subProjects=="mainprojects"){
        document.getElementById('chartTitle').innerHTML = "<font color=green>Projects Analysis</font>";
        var options = {
            //        width: 370,
            //        height:300,
            // title: 'Cost Centers Yearly Analysis',
            colors: ['#0000FF', '#00FF00'],
            titleColor:"green",
            'is3D':true,
            // backgroundColor: '#1b1b1b',
            vAxis: {
                title: "Hours",
                titleColor:"green"
            //                textStyle: {color: '#24BEFF'}
            },
            hAxis: {
                title: "Main Projects",
                titleColor:"green"
            //                textStyle: {color: '#24BEFF'}
            },
            legend: {
                position: 'top', 
                alignment: 'center'
            }
        }
    }
    else if(resourceHours=="resourceworkedhrs"){
        // document.getElementById("resouresDiv").style.display = 'block';
        //  var v=null;
        document.getElementById('resorceschartTitle').innerHTML = "<font color=red >&nbsp; "+ selectedValue +" </font><font color=green>Project Resources Hours Analysis</font>";
        var options = {
            //        width: 370,
            //        height:300,
            //  title: 'Cost Center Projects Yearly Analysis',
            colors: ['#00FF00'],
            titleColor:"green",
            'is3D':true,
            vAxis: {
                title: "Project Hours",
                   
                titleColor:"green"
            },
            hAxis: {
                title: "Resources",
                titleColor:"green"
            },
            legend: {
                position: 'top', 
                alignment: 'center'
            }
        }
    }
    else
    {
        //  alert("in ");
        // var v=null;
        document.getElementById('subProjectsTitle').innerHTML = "<font color=red >&nbsp; "+ selectedValue +" </font><font color=green>Sub Project(s) Hours Analysis</font>";
      
        // document.getElementById('subProjectsTitle').innerHTML = "<font color=green><b>Project Hours Analysis</b></font>";
        var options = {
            
            //        width: 370,
            //        height:300,
            //  title: 'Budget Analysis',
            titleColor:"green",
            colors: ['#0000FF', 'red'],
            vAxis: {
                title: "Hours",
                titleColor:"green"
            //                textStyle: {color: '#24BEFF'}
            },
            hAxis: {
                title: "Sub Projects",
                titleColor:"green"
            //                textStyle: {color: '#24BEFF'}
            },
            legend: {
                position: 'top', 
                alignment: 'center'
            }
           
        }
    }
   
    if(subProjects=="mainprojects"){
       //  alert("subProjects-->"+subProjects);
        var chart = new google.visualization.ColumnChart(document.getElementById('Barchart'));
        
        function selectMainProHandlers() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
                var practice = data.getValue(selectedItem.row, 0);
                 alert('The user selected--> '+practice);
                getProjectDashboardList(practice,"mainprojectsselected");
            }
        }
        google.visualization.events.addListener(chart, 'select', selectMainProHandlers);
       
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
        
        $(window).resize(function () {
            chart.draw(data, options);
        });
    }
    else if(subProjects=="subprojects"){
        var chart = new google.visualization.ColumnChart(document.getElementById('subProjectsBarchart'));
     
     
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
                var practice = data.getValue(selectedItem.row, 0);
                  alert('The user selected in sub--> '+practice);
                getProjectDashboardList(practice,"subprojects");
            }
        }
        google.visualization.events.addListener(chart, 'select', selectHandler);
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
       
        $(window).resize(function () {
            chart.draw(data, options);
        });
    }
    //   alert("in if");
    // document.getElementById("resouresDiv").style.display = 'block';
    else
    {
        var chart = new google.visualization.ColumnChart(document.getElementById('BarchartForResources'));
       
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
        
        $(window).resize(function () {
            chart.draw(data, options);
        }); 
    }
}

function settingProjectValue(){
    //var v=document.getElementById("costCenters").value;  
    //alert(v);
    document.getElementById("subProjects").value=-1;
    document.getElementById("subProjectsSelectDiv").style.display = 'none';
    
    var v=document.getElementById("projects").value;
    
    if(v==-1)
    {
        //  alert(v);
        document.getElementById("subProjectsDiv").style.display = 'none';  
    }
    
    document.getElementById("resouresDiv").style.display = 'none';
//$("#costCentersSelectDiv").css('visibility', 'hidden');
}
function settingProjectResourcesValue(){
    //var v=document.getElementById("costCenters").value;  
    // alert(v);
    var v=document.getElementById("subProjects").value;
    if(v==-1)
    {
        //  alert(v);
        document.getElementById("resouresDiv").style.display = 'none';    
    }
//document.getElementById("subProjectsSelectDiv").style.display = 'none';
}
function getProjectsForYear()
{
    document.getElementById("subProjectsSelectDiv").style.display = 'none'; 
    document.getElementById("subProjectsDiv").style.display = 'none'; 
    document.getElementById("resouresDiv").style.display = 'none';
    //  document.getElementById("projects").value=-1;
    document.getElementById("subProjects").value=-1;
    //alert("Consultant ajax");
    var dashBoardYear=document.getElementById('dashBoardYear').value;
    //alert(id);
    var url='getProjectsForYear.action?dashBoardYear='+dashBoardYear;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
           
            populateMainProjects(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function populateMainProjects(data){
    //alert(data);
    var projects = document.getElementById("projects");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#projects');
    $select.find('option').remove();
    $('<option>').val(-1).text('All').appendTo($select);
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("|");
        {  
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}
function setQuarter()
{
    document.getElementById("quarter").value='DF';
    document.getElementById("costCentersDiv").style.display = 'none'; 
    document.getElementById("costCentersProjectsDiv").style.display = 'none'; 
    document.getElementById("costCentersSelectDiv").style.display = 'none'; 
    
}

function getVendorReqDashBoardGrid(){
    var candidateName=$('#candidateName').val();
    var jobTitle=$('#jobTitle').val();
    //alert("HI "+csrCustomers+"  "+dashYears)
    
    var url='../vendor/getVendorReqDashBoardGrid.action?candidateName='+candidateName+'&jobTitle='+jobTitle;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            populateVendorReqDashBoardGrid(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateVendorReqDashBoardGrid(response){
    var dashBoardReq=response.split("^");
    var table = document.getElementById("vendorReqDashboardResults");
    
    
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
                $("#vendorReqDashboardResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
            }
        }
    }
    else{
        var row = $("<tr />")
        $("#vendorReqDashboardResults").append(row);
        row.append($('<td colspan="5"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
    }
    $('#vendorReqDashboardResults').tablePaginate({
        navigateType:'navigator'
    },recordPage);
}   
    




