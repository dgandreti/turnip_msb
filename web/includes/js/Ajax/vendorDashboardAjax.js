google.load('visualization', '1.1', {
    'packages': ['corechart']
});
google.setOnLoadCallback(getVendorRequirementsDashBoard);
function getVendorRequirementsDashBoard(){
     $("#loadingReqDashboard").show();
    $("#reqCustomerYearChart").css('visibility', 'visible');
    var dashYears=$('#vdashYears').val();
    var csrCustomers=$('#vendorOrgId').val();
    //alert("HI "+csrCustomers+"  "+dashYears)
    if(document.getElementById("vendorAccountNamePopup").value=="")
        {
            csrCustomers="";
        }
    var url='../dashboard/getVendorRequirementsDashBoard.action?dashYears='+dashYears+'&csrCustomer='+csrCustomers;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
             $("#loadingReqDashboard").hide();
            populateVendorDashBoardTableForCsrRequirements(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function getDefaultVendorRequirementsDashBoard(){
    //    $("#reqVendorYearChart").css('visibility', 'visible');
    //    $("#reqCustomerYearChart").css('visibility', 'hidden');
    //alert("HI ")
    
    var url='../dashboard/getVendorRequirementDashBoardDetails.action';
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            populateVendorDashBoardTableForCsrRequirements(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateVendorDashBoardTableForCsrRequirements(response){
    //alert(response)
    $(".page_option").css('display', 'block');
    var dashBoardReq=response.split("^");
    var table = document.getElementById("VendorDashBoardTable");
    
    var vendor = new Array();
    var won=new Array();
    var lost =new Array();
    
    
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
                $("#VendorDashBoardTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[6] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[5] + "</td>"));
                row.append($('<td><a href="#" class="csrVendorReq_popup_open" onclick="csrVenDetails('+Values[7]+');csrVenOverlay();">'+ Values[0] +"</td>"));
                //row.append($("<td>" + Values[0] + "</td>"));
                vendor.push(Values[6]);
                won.push(parseInt(Values[4]));
                lost.push(parseInt(Values[5]));
            }
        }
        showVendorChart(vendor,won,lost);
    }
    else{
        $("#reqCustomerYearChart").css('visibility', 'hidden');
        var row = $("<tr />")
        $("#VendorDashBoardTable").append(row);
        row.append($('<td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
    pager.init(); 
  $('#VendorDashBoardTable').tablePaginate({navigateType:'navigator'},recordPage);
}
function csrVenOverlay(){
    var specialBox = document.getElementById("csrVenBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#csrVendorReq_popup').popup(      
        );    
    return false;
}
function csrVenDetails(accountId)
{
    var dashYears=$("#vdashYears").val();
    //alert("HI  "+accountId+" "+dashYears)
    var url='../dashboard/getVendorDashBoardDetailsOnOverlay.action?dashYears='+dashYears+'&accountId='+accountId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            populateDashBoardTableForCsrVendorOnOverlay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function populateDashBoardTableForCsrVendorOnOverlay(response){
    //alert(response)
    var dashBoardReq=response.split("^");
    var table = document.getElementById("dashBoardTableVendorOnOverlay");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){

        var total=0,lostTotal=0;
        for(var i=0;i<dashBoardReq.length-1;i++){   
            //alert(techReviewList[0])
            var Values=dashBoardReq[i].split("|");
            {  
                var row = $("<tr />")
                $("#dashBoardTableVendorOnOverlay").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                total= parseInt(total)+ parseInt(Values[1]);
                lostTotal=parseInt(lostTotal)+ parseInt(Values[2]);
            }
        }
        var row = $("<tr />")
        $("#dashBoardTableVendorOnOverlay").append(row);
        row.append($("<td><font style='color:red'>Total</font></td>"));
        row.append($("<td><font style='color:red'>" + total + "</font></td>"));
        row.append($("<td><font style='color:red'>" + lostTotal + "</font></td>"));
    }
}


function showVendorChart(vendor,won,lost)
{
    google.load('visualization', '1.1', {
        'packages': ['corechart']
    });
    google.setOnLoadCallback(getVendorRequirementsDashBoard);
    //alert(vendor);
    
     
    var Combined = new Array();
    Combined[0] = ['Vendor', 'Won', 'Lost'];
    for (var i = 0; i < won.length; i++){
        Combined[i + 1] = [vendor[i],won[i], lost[i]];
    }
    //alert(Combined+"---->Combined")
    //second parameter is false because first row is headers, not data.
    var data = google.visualization.arrayToDataTable(Combined, false);
    
    var options = {
        //        width: 370,
        //        height:300,
        title: 'Vendor Requirements Yearly Analysis',
        colors: ['#39FF07', '#FF0707'],
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
     $("#reqCustomerYearChart").css('visibility', 'visible');
    var chart = new google.visualization.ColumnChart(document.getElementById('reqCustomerYearChart'));
    drawChart();
    function drawChart() {
        //alert("in drawChart")
        // Instantiate and draw our chart, passing in some options.
        chart.draw(data, options);
    }
    $(window).resize(function () {
        drawChart();
    });
}