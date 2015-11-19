
function checkCommentsChars(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 250){
            el.val( el.val().substr(0, 250) );
        } else {
            $("#JobcharNum").text(250-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==250)
        {
            $("#JobcharNum").text(' Cannot enter  more than 250 Characters .');    
        }
        
    })
    return false;
};

function associationOverlay() {
   
    var specialBox = document.getElementById("vendorAssocitaionOverlay");

    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#vendorAsso_popup').popup(      
        );
    $("saveVendorAssociation").html("");
    document.getElementById("tireType").value="-1";
    document.getElementById("accessTime").value="";
    document.getElementById("vendorNames").value="";
    return false;
}
function associationEditOverlayclose(){
    var specialBox = document.getElementById("vendorAssocitaionEditOverlay");
    
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    }
    else {
        specialBox.style.display = "block";      
    }
    $('#vendorAssoEdit_popup').popup();
}



function associationEditOverlay(id) {
    // alert(id)
   
    var specialBox = document.getElementById("vendorAssocitaionEditOverlay");
    
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#vendorAssoEdit_popup').popup();
    document.getElementById("vendorId").value=id;
    var url=CONTENXT_PATH+'/vendor/editVendorAssociation.action?vendorId='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText);
                populateVendorOverlay(req.responseText,id);
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    // Initialize the plugin    
   
    return false;
}

function populateVendorOverlay(response,id)
{
    document.getElementById("vendorId").value=id; 
    //alert(document.getElementById("vendorId").value);
    var add=response.split("^");
    for(var i=0;i<add.length-1;i++){        
        var Values=add[i].split("|");
        {  
            //alert(Values[0]);
                
            document.getElementById("tireTypeEdit").value=Values[0];
            //  document.getElementById("vendorNamesEdit").value=Values[1];
            document.getElementById("statusEdit").value=Values[2];
            $("vendorNameDisplay").html(Values[1]);
            getVendorNames(Values[0])  
        }
    }
}
function getVendorNames(tireId)
{
    // alert("Consultant ajax");
    var id=document.getElementById('tireTypeEdit').value;
    
    var url=CONTENXT_PATH+'/vendor/getVendorNames.action?tireId='+tireId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            PopulateVenderName(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
// function to set the state of permanent address in addConsultant page add by Aklakh
function PopulateVenderName(data){
    //alert(data);
    var topicId = document.getElementById("vendorNamesEdit");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#vendorNamesEdit');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}
//created by Aklakh
function updateVendorAssociation(){
    initSessionTimer();
    var vendorId=document.getElementById("vendorId").value;
    //alert(vendorId);
    var tireTypeId=$("#tireTypeEdit").val();
    var statusEdit=$("#statusEdit").val();
    var url=CONTENXT_PATH+'/vendor/updateVendorDetails.action?vendorId='+ vendorId +'&statusEdit='+ statusEdit;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4){
           
            if(req.status == 200) {
                //location.reload(true);
                // $("EditSkillOverlayResult").html(" <b><font color='Green'>Skill record successfully Updated.</font></b>");
                // alert("record updated successfully");
                getVendorAssociationDetails();
                associationEditOverlayclose();
            }
        }
        else{
        }
            
        
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
   
}




function changeState()
{
    var country=$("#vendorCountry").val();
    var url='getStatesForCountry.action?countryId='+country;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            setVendorStates(req.responseText);
        } 
    
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function setVendorStates(states)
{
    var vendorState = document.getElementById("vendorState");
    var stateSet=states.split("^");
    var $select = $('#vendorState');
    $select.find('option').remove();   
    for(var i=0;i<stateSet.length-1;i++){        
        var Values=stateSet[i].split("#");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}


function updateVendorDetails()
{
    var vendorName=$("#vendorName").val();
    var vendorURL=$("#vendorURL").val();
    var vendorStatus=$("#vendorStatus").val();
    var vendorAddress1=$("#vendorAddress1").val();
    var vendorAddress2=$("#vendorAddress2").val();
    var vendorCity=$("#vendorCity").val();
    var vendorPhone=$("#vendorPhone").val();
    var vendorFax=$("#vendorFax").val();
    var vendorState=$("#vendorState").val();
    var vendorCountry=$("#vendorCountry").val();
    var vendorZip=$("#vendorZip").val();
    var vendorIndustry=$("#vendorIndustry").val();
    var vendorRegion=$("#vendorRegion").val();
    var vendorTerritory=$("#vendorTerritory").val();
    var vendorType=$("#vendorType").val();
    var vendorDescription=$("#vendorDescription").val();
    var vendorBudget=$("#vendorBudget").val();
    var vendorTaxid=$("#vendorTaxid").val();
    var stockSymbol=$("#stockSymbol").val();
    var vendorRvenue=$("#vendorRvenue").val();
    var empCount=$("#empCount").val();
    var vendorId=$("#vendorId").val();
    
    
    var url='updateVendorDetails.action?vendorName='+vendorName+
    '&vendorURL='+vendorURL+
    '&vendorStatus='+vendorStatus+
    '&vendorAddress1='+vendorAddress1+
    '&vendorAddress2='+vendorAddress2+
    '&vendorCity='+vendorCity+
    '&vendorPhone='+vendorPhone+
    '&vendorFax='+vendorFax+
    '&vendorState='+vendorState+
    '&vendorCountry='+vendorCountry+
    '&vendorZip='+vendorZip+
    '&vendorIndustry='+vendorIndustry+
    '&vendorRegion='+vendorRegion+
    '&vendorTerritory='+vendorTerritory+
    '&vendorType='+vendorType+
    '&vendorDescription='+vendorDescription+
    '&vendorBudget='+vendorBudget+
    '&vendorTaxid='+vendorTaxid+
    '&stockSymbol='+stockSymbol+
    '&vendorRvenue='+vendorRvenue+
    '&empCount='+empCount+
    '&vendorId='+vendorId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            $("UpdateVendorInfo").html(" <b><font class='StripForResultMessage' style='font-size:12px' color='green'>Vendor Information updated Successfully.</font></b><br>");
        } 
   
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
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



/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }
    
    this.showPage = function(pageNumber) {
        if (! this.inited) {
            alert("not inited");
            return;
        }

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        oldPageAnchor.className = 'pg-normal';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        
        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);
    }   
    
    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }
    
    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }                        
    
    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length -1); 
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
        if (! this.inited) {
            alert("not inited");
            return;
        }
        var element = document.getElementById(positionId);                                                                                  
        var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal" "> <font align="bottom" class="jumpbar"> Page: <i class="fa fa-chevron-circle-left"></i> </font></span> ';
        for (var page = 1; page <= this.pages; page++) 
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');" "><font color="black" face="verdana">' + page + '</font></span> ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"><font align="bottom" class="jumpbar"><i class="fa fa-chevron-circle-right"></i></font></span>';            
        
        // pagerHtml='<span style="margin-right:40vw;>'+pagerHtml+'</span>';
        element.innerHTML =pagerHtml ;
    }
}


// DBGrid.js file start



function doNavigate(pstrWhere, pintTot)
{
    var strTmp;
    var intPg; 
  
    strTmp = document.frmDBGrid.txtCurr.value;
    intPg = parseInt(strTmp);
    if (isNaN(intPg)) intPg = 1; 
    if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1)
    {
        alert("You are already viewing first page!");
        return;
    }
    else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot)
    {
        alert("You are already viewing last page!");
        return;
    }
    if (pstrWhere == 'F')
        intPg = 1;
    else if (pstrWhere == 'P')
        intPg = intPg - 1;
    else if (pstrWhere == 'N')
        intPg = intPg +1;
    else if (pstrWhere == 'L')
        intPg = pintTot; 
    if (intPg < 1) intPg = 1;
    if (intPg > pintTot) intPg = pintTot;
    document.frmDBGrid.txtCurr.value = intPg;
    document.frmDBGrid.submit();
}
function doSort(pstrFld, pstrOrd)
{
    document.frmDBGrid.txtSortCol.value = pstrFld;
    document.frmDBGrid.txtSortAsc.value = pstrOrd;
    document.frmDBGrid.submit();
}

function goToPage(element) {
    document.frmDBGrid.txtCurr.value = element.options[element.selectedIndex].value;
    document.frmDBGrid.submit();
}
function goToMyPage(element) {
    if (element == null || element.value == null 
        || element.value == ''){
        return;
    }
    document.frmDBGrid.txtCurr.value = element.value;
    document.frmDBGrid.submit();
}

//responsive_tables.js start 


$(document).ready(function() {
    var switched = false;
    var updateTables = function() {
        if (($(window).width() < 917) && !switched ){
            switched = true;
            $("table.responsive").each(function(i, element) {
                splitTable($(element));
            });
            return true;
        }
        else if (switched && ($(window).width() > 917)) {
            switched = false;
            $("table.responsive").each(function(i, element) {
                unsplitTable($(element));
            });
        }
    };
   
    $(window).load(updateTables);
    $(window).on("redraw",function(){
        switched=false;
        updateTables();
    }); // An event to listen for
    $(window).on("resize", updateTables);
   
	
    function splitTable(original)
    {
        original.wrap("<div class='table-wrapper' />");
		
        var copy = original.clone();
        copy.find("td:not(:first-child), th:not(:first-child)").css("display", "none");
        copy.removeClass("responsive");
		
        original.closest(".table-wrapper").append(copy);
        copy.wrap("<div class='pinned' />");
        original.wrap("<div class='scrollable' />");

        setCellHeights(original, copy);
    }
	
    function unsplitTable(original) {
        original.closest(".table-wrapper").find(".pinned").remove();
        original.unwrap();
        original.unwrap();
    }

    function setCellHeights(original, copy) {
        var tr = original.find('tr'),
        tr_copy = copy.find('tr'),
        heights = [];

        tr.each(function (index) {
            var self = $(this),
            tx = self.find('th, td');

            tx.each(function () {
                var height = $(this).outerHeight(true);
                heights[index] = heights[index] || 0;
                if (height > heights[index]) heights[index] = height;
            });

        });

        tr_copy.each(function (index) {
            $(this).height(heights[index]);
        });
    }

});
//Added By manikanta for vendor contact

function showVendorContacts()
{
    var orgId= document.getElementById("vendorSearchId").value;
    var url='../vendor/getVendorContacts.action?orgId='+orgId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                
                populateVendorContactTable(req.responseText);
                
            } 
            else
            {
            //alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateVendorContactTable(response){
    if(response!=""){
        var eduList=response.split("^");
        var OrgID=document.getElementById("vendorSearchId").value;
        var vendorFlag="vendorContact";
        var table = document.getElementById("contactPageNav");
        
        for(var i = table.rows.length - 1; i > 0; i--)
        {
            table.deleteRow(i);
        }
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                $("#contactPageNav").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                
                row.append($('<td><a href="../acc/accountcontactedit.action?accFlag='+ vendorFlag +'&accountSearchID='+ OrgID +'&contactId='+Values[0]+'" > '+ Values[1] + "</td>"));
                // row.append($("<td>" + Values[1] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($('<td><a href="#" onclick="saveVendorContactDetails('+Values[0]+')">'  + "CLICK" + "</td>"));
            //row.append($("<td>" + Values[4] + "</td>"));
            //row.append($("<td>" + Values[7] + "</td>"));
            //onclick="saveContactDetails(' + Values[0] +');" > '
            }
        }
  
        pager.init(); 
        pager.showPageNav('pager', 'contactPageNavPosition'); 
        pager.showPage(1);
    }
    else {
       
    }
     
}

function saveVendorContactDetails(usrid)
{
    // var orgId= document.getElementById("accountsearchid").value;
    var url='../vendor/saveVendorContacts.action?vendorUserId='+usrid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                
                document.getElementById("outputMessage").innerHTML=req.responseText;
                
            } 
            else
            {
            // alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

    return false;
}

function getContactSearchResults(){
    var firstName=$("#firstNameContacts").val();
    var lastName=$("#lastNameContacts").val();
    var email=$("#emailContacts").val();
    var status=$("#statusContacts").val();
    var phone=$("#phoneContacts").val();
    
    var orgId= document.getElementById("vendorSearchId").value;
    var url='../vendor/getVendorContactSearchResults.action?orgId='+orgId +'&vendorFirstName='+firstName +'&vendorLastName='+lastName +'&vendorEmail='+email +'&vendorStatus='+status+ '&vendorPhone='+ phone ;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                populateContactTable(req.responseText);
               
            //$("securityinfo").html(" <b><font color='green'>Confidential information Saved Successfully</font></b>");
            } 
            else
            {
               
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function getVendorsNames()
{
    var tireType=document.getElementById("tireType").value;
    if(tireType=="DF")
      
        return false;
    var url='getVendorsListByTireType.action?tireType='+tireType;

    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            populateVendorsNames(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
}
function populateVendorsNames(response)
{
 
    var vendorState = document.getElementById("vendorNames");
    var stateSet=response.split("^");
    var $select = $('#vendorNames');
    $select.find('option').remove();   
    for(var i=0;i<stateSet.length-1;i++){        
        var Values=stateSet[i].split("|");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}

function saveVendorAssociation(){
    initSessionTimer();
    var tireType=document.getElementById("tireType").value;
    
    var vendorList = [];    
    $("#vendorNames :selected").each(function(){
        vendorList.push($(this).val()); 
    });
    //  vendorList="10004";
    var accessTime=document.getElementById("accessTime").value;
    //  var req_id=document.getElementById("req_id").value;
    var req_id=document.getElementById("req_id").value;
   
    //var url='SaveVendorsAssociationDetals.action?tireType='+tireType+'&vendorList='+vendorList+'&accessTime='+accessTime+'req_id='+req_id;
    if(validateVendorAssociation())
    {
        var url='SaveVendorsAssociationDetals.action?vendorList='+vendorList+'&accessTime='+accessTime+'&req_id='+req_id;
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                if(req.responseText>0)
                    $("saveVendorAssociation").html(" <b><font color='green'> "+req.responseText+" Records Saved Successfully</font></b>");
                else
                    $("saveVendorAssociation").html(" <b><font color='red'> Vendor already exists!</font></b>");
                //                    $("saveVendorAssociation").html(" <b><font color='red'> Requirement already exists</font></b>");
                getVendorAssociationDetails()
            } 
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    
        return false;
    }
}

function validateVendorAssociation(){
    //alert("validate");
    var tireType=$("#tireType").val();
    var accessTime=$("#accessTime").val();
    var vendorNames=$("#vendorNames").val();
    var venAccessTime = accessTime.split(/[- :]/);
    var vendorAccessTime = new Date(venAccessTime[2], venAccessTime[0]-1 , venAccessTime[1], venAccessTime[3],venAccessTime[4]);
    var vendorAccesstime = Date.parse(vendorAccessTime);
    var todayDate=new Date();
    if(tireType==-1){
       
        $("saveVendorAssociation").html("<font color='red'>Please select Type of tier</font>");
        $("#tireType").css('border','1px solid red');
        return false;
    }  
    else{
        $("saveVendorAssociation").html("");
        $("#tireType").css('border','1px solid #ccc');
        
    }
    if(accessTime==""){
        //  alert("desc");
        $("saveVendorAssociation").html("<font color='red'>Please select accesstime</font>");
        $("#accessTime").css('border','1px solid red');
        return false;
    }  
    else{
        $("saveVendorAssociation").html("");
        $("#accessTime").css('border','1px solid #ccc');
        
    }
    if(vendorAccesstime< todayDate){
        //  alert("desc");
        $("saveVendorAssociation").html("<font color='red'>Access time should not be past time.</font>");
        $("#accessTime").css('border','1px solid red');
        return false;
    }  
    else{
        $("saveVendorAssociation").html("");
        $("#accessTime").css('border','1px solid #ccc');
        
    }
    if(vendorNames==null){
        //  alert("desc");
        $("saveVendorAssociation").html("<font color='red'>Please select vendorNames</font>");
        $("#vendorNames").css('border','1px solid red');
        return false;
    }  
    else{
        $("saveVendorAssociation").html("");
        $("#vendorNames").css('border','1px solid #ccc');
        
    }
    return true;
}

function removeVendorErrorMsg()
{

    //alert("hello jagan")
    $("saveVendorAssociation").html("");
    $("#tireType").css('border','1px solid #ccc');
    $("#accessTime").css('border','1px solid #ccc');
    $("#vendorNames").css('border','1px solid #ccc');
    return false;
}


//BY RK
function getRequirementDetails()
{
    var accountSearchID=$("#accountSearchID").val();
    var url='searchRequirement.action?accountSearchID='+accountSearchID;
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            populateReqTable(req.responseText);
        } 
    
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
}

function populateReqTable(response){
    var reqList=response.split("^");
    
    var accountSearchID=$("#accountSearchID").val();
    var name =  document.getElementById("account_name").value;
  
    var table = document.getElementById("reqTable");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    for(var i=0;i<reqList.length-1;i++){     
       
        var Values=reqList[i].split("|");
        {  
                                                         
            var reqRow = $("<tr />")
            $("#reqTable").append(reqRow); //this will append tr element to table... keep its reference for a while since we will add cels into it
            // reqRow.append($('<td><a href="../Requirements/requirementedit.action?accountSearchID='+ accountSearchID +'&accountFlag=Account&requirementId='+Values[0]+'" > ' + Values[1] + "</td>"));
            reqRow.append($('<td><a href="../Requirements/requirementedit.action?account_name='+name+'&accountSearchID='+accountSearchID+'&accountFlag=Account&requirementId='+Values[0]+'" > ' + Values[1] + "</td>"));

            reqRow.append($("<td>" + Values[2] + "</td>"));
            if(Values[5]=='null'||Values[5]==""){
                Values[5]="";
                reqRow.append($('<td><a href="" class="skillShow_popup_open" onclick=" showSkillDetails('+Values[0]+');" >'+Values[5].substr(0,10)+"</td>"));
            }
            else{
                reqRow.append($('<td><a href="" class="skillShow_popup_open" onclick=" showSkillDetails('+Values[0]+');" >'+Values[5].substr(0,10)+"..</td>"));
            }
            if(Values[6]=='null'||Values[6]==""){
                Values[6]="";
                reqRow.append($('<td><a href="" class="skillShow_popup_open" onclick=" showPreferedSkillDetails('+Values[0]+');" >'+Values[6].substr(0,10)+"</td>"));
            }
            else{
                reqRow.append($('<td><a href="" class="skillShow_popup_open" onclick=" showPreferedSkillDetails('+Values[0]+');" >'+Values[6].substr(0,10)+"..</td>"));
            }
            reqRow.append($("<td>" + Values[3] + "</td>"));
            reqRow.append($("<td>" + Values[4] + "</td>"));
            reqRow.append($('<td><a href="#" class="addConsultant_popup_open" onclick=" storeReqIdinOverlay('+Values[0]+');" >'+"Click</td>"));      
        //reqRow.append($('<td><a href="../Requirements/requirementedit.action?account_name='+name+'&accountSearchID='+accountSearchID+'&accountFlag=Account&requirementId='+Values[0]+'" > ' + Values[1] + "</td>"));
                  
        }
    }

   
    pager.init();     
    pager.showPageNav('pager', 'reqpageNavPosition'); 
    pager.showPage(1);
}


function showSkillDetails(sid){
   
    var url='getSkillDetaisls.action?requirementId='+sid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("skillDetails").value=req.responseText;
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function showPreferedSkillDetails(sid){
   
    var url='getPreferedSkillDetails.action?requirementId='+sid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("skillDetails").value=req.responseText;
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
$(document).ready(function(){
    
   
    $('#skillShow_popup').popup(); 
});

function getReqDetailsBySearch()
{
    var requirementId=$("#requirementId").val();
    var jobTitle=$("#jobTitle").val();
    var requirementSkill=$("#requirementSkill").val();
    var requirementStatus=$("#requirementStatus").val();
    var reqStart=$("#reqStart").val();
    var reqEnd=$("#reqEnd").val();
    var accountSearchID=$("#accountSearchID").val();
    var url='getReqDetailsBySearch.action?requirementId='+requirementId+'&jobTitle='+jobTitle+'&requirementSkill='+requirementSkill+'&requirementStatus='+requirementStatus+'&reqStart='+reqStart+'&reqEnd='+reqEnd+'&accountSearchID='+accountSearchID;
   
    var req=initRequest(url);
    req.onreadystatechange = function() {
       
        if (req.readyState == 4 && req.status == 200) {
            populateReqTable(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    return false;
}

//BY RK

function getEmpMailPhone(val)
{
    var url='getEmailPhoneDetails.action?userId='+val;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                var details=req.responseText.split("|");
                document.getElementById("email").value=details[0];
                document.getElementById("contactNo").value=details[1];

            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    
    
}
$(document).ready(function(){
    
   
    $('#emailPhoneShow_popup').popup(); 
});

function Vendorheading(message)
{
    if(message.id=="vendordetails"){
   
        document.getElementById("headingmessage").innerHTML="Vendor Details";
    }

    if(message.id=="vendorSoftware"){
        document.getElementById("headingmessage").innerHTML="Vendor Softwares";
    }
    if(message.id=="vendorTeam"){
        document.getElementById("headingmessage").innerHTML="Assign Team";
    }
    if(message.id=="vendorContacts"){
        document.getElementById("headingmessage").innerHTML="Contacts";
        showContacts();
    }
   
}



function getVendorAssociationDetails()
{
    initSessionTimer();
    var RequirementId=document.getElementById("RequirementId").value;
    var randomNmbr = Math.random();
    var url='Requirements/getVendorAssociationDetails.action?RequirementId='+RequirementId+'&randomNmbr='+randomNmbr;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingVendor').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingVendor').hide();
            vendorAssociationGridDisplay(req.responseText);
           
        //setPrimaryAssigned(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;
}
function vendorAssociationGridDisplay(response){
    $(".page_option").css('display', 'block');
    if(response!=null)
        var eduList=response.split("^");
   
    var table = document.getElementById("vendorAssociationResults");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
                row.append($('<td><a href="" class="vendorAssoEdit_popup_open" onclick="associationEditOverlay('+Values[0]+');" > ' + Values[5] + "</td>"));

                //  row.append($("<td>" + Values[5] + "</td>"));
                $("#vendorAssociationResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                // row.append($('<td><a href="" class="vendorAssoEdit_popup_open" onclick="associationEditOverlay('+Values[0]+');" > ' + Values[1] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
                if(Values[7]>0){
                    row.append($('<td class="active_details"  id="consultantListLi"><a aria-expanded="false" href="#consultantList" data-toggle="tab" onclick="return getOrgConsultant(\''+Values[5]+'\',\''+Values[9]+'\')">' + Values[7] + "</a></td>"));
                }
                else
                {
                    row.append($("<td>" + '--' + "</td>"));
                }
                if(Values[8]>0){
                    row.append($("<td>$" + Values[8] + "Hr</td>"));
                }
                else
                {
                    row.append($("<td>" +'--'  + "</td>"));
                }
                // row.append($("<td>" + Values[2] + "</td>"));
                row.append($("<td>" + Values[3] + "</td>"));
            
                //row.append($("<td>" + Values[6] + "</td>"))
                row.append($("<td>" + Values[4] + "</td>"));
            //  row.append($("<td>" + Values[4] + "</td>"));
            //row.append($("<td>" + Values[4] + "</td>"));
            // row.append($('<td><a href="#" onclick="saveVendorContactDetails('+Values[0]+')">'  + "CLICK" + "</td>"));
            //row.append($("<td>" + Values[4] + "</td>"));
            //row.append($("<td>" + Values[7] + "</td>"));
            //onclick="saveContactDetails(' + Values[0] +');" > '
            }
        }
    }
    else {
        var row = $("<tr />")
        $("#vendorAssociationResults").append(row);
        row.append($('<td colspan="6"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
  $('#vendorAssociationResults').tablePaginate({navigateType:'navigator'},recordPage);
    pag.init(); 
  
}
 
function searchVendorAssociationDetails()
{
    initSessionTimer();
    var tireType=document.getElementById("tireTypeSearch").value;
    //alert(tireType)
    var status=document.getElementById("status").value;
    var RequirementId=$('#RequirementId').val();
     var randomNum = Math.random();
    //alert(status)
    var url='Requirements/searchVendorAssociationDetails.action?tireType='+tireType+'&status='+status+'&RequirementId='+RequirementId+'&randomNum='+randomNum;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingVendor').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingVendor').hide();
            vendorAssociationGridDisplay(req.responseText);
           
        //setPrimaryAssigned(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;
}

//for add con functions by rk start
function storeReqIdinOverlay(reqId){
    alert(reqId)
    //    
    document.getElementById("reqId").value=reqId;
    
    var specialBox = document.getElementById("addVendorConsultantOverlay");
    
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#addVendorConsultant_popup').popup(      
        ); 
}

function getEmailExistance(){
    //alert("HI im in email existance")
    var conEmail=$("#conEmail").val();
    var reqId=$("#reqId").val();
    
    //alert(conEmail+" and...........>  "+reqId)
    var url='Requirements/getConsultanceExistance.action?conEmail='+conEmail;
    //alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if(req.responseText!="0"){
                if(req.responseText!='null'||req.responseText!=""){
                    //alert(req.responseText)
                    //$("e1").html("");
                    $("e1").html(" <b><font color='green'>Already consultant exists</font></b>");
                    document.getElementById("conEmail").value=" ";
                //checkRecordInReqConRel(req.responseText,reqId);
                }
                else{
                    //$("e1").html("");
                    document.getElementById("conEmail").value=" ";
                    $("e1").html(" <b><font color='red'>sorry email doesnt exist</font></b>");
                }
            }
            else{
                //alert("sorry email doesnt exist")
                //$("e1").html("");
                document.getElementById("conEmail").value=" ";
                $("e1").html(" <b><font color='red'>sorry email doesnt exist</font></b>");

            }
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}


function checkRecordInReqConRel(conId,reqId){
    //alert(conId+"  |||||||||| "+reqId)
    var url='Requirements/checkRecordInReqConRel.action?conId='+conId+'&reqId='+reqId;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if(req.responseText!="0"){
                var vals=req.responseText.split("|");
                // alert(vals[0]+"  and  "+vals[1]+"  and  "+vals[2]);
            
                if(vals[0]=="PN"){
                    document.getElementById("pan").value=vals[1];
                    document.getElementById("proofType").value=vals[0];
                    setPPorPAN("PN");
                }
                if(vals[0]=="PP"){
                    document.getElementById("ppno").value=vals[1];
                    document.getElementById("proofType").value=vals[0];
                    setPPorPAN("PP");
                } 
            }
            else{
                $("e1").html(" <b><font color='red'>You dont have any proof add any one</font></b>");
            }
            
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;
}
function storeProofData(){
    //alert("called submit function here")
    var proofType=$("#proofType").val();//1=passport 2=pan
    var ppno=$("#ppno").val();
    var pan=$("#pan").val();
    var reqId=$("#reqId").val();
    var conEmail=$("#conEmail").val();

    
    //alert("type:"+proofType+"  passport:"+ppno+"  pan:"+pan+"    reqId:"+reqId)
    var url='Requirements/storeProofData.action?proofType='+proofType+'&ppno='+ppno+'&pan='+pan+'&reqId='+reqId+'&conEmail='+conEmail;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if(req.responseText=="NotExist"){
                $("e1").html(" <b><font color='red'>You dont have consultant account</font></b>");
            }else if(req.responseText=="updatesuccess"){
                $("e1").html(" <b><font color='green'>Your Identification For Requirement Activated</font></b>");
            }else if(req.responseText=="lessthanoneEighty"){
                $("e1").html(" <b><font color='green'>Already your Identification is in Activation</font></b>");
            }else if(req.responseText=="AddSuccess"){
                $("e1").html(" <b><font color='green'>Your Identification for Requirement is Added</font></b>");
            }else{
                $("e1").html(" <b><font color='green'>Error</font></b>");
            }
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;

}
function setPPorPAN(proofType){
    //alert("HI im in PAN or PASSPORT")
    //alert(proofType)
    if(proofType=="PN"){
        $("#panId").css('visibility', 'visible');
        $("#ppId").css('visibility', 'hidden');
    }
    if(proofType=="PP"){
        $("#ppId").css('visibility', 'visible');
        $("#panId").css('visibility', 'hidden');
    }
}


$(document).ready(function(){
    
    $('#Contact_popup').popup(); 
});
$(document).ready(function(){
    
    $('#addConsultant_popup').popup(); 
});

// for add con functions end
function getConsultantListBySearch(){
    initSessionTimer();
    var techSearch="search";
    document.getElementById("techSearch").value=techSearch;
    var RequirementId=$('#RequirementId').val();
    var consult_name=$('#consult_name').val();
    var consult_email=$("#consult_email").val();
    var consult_skill=$('#consult_skill').val();
    var consult_phno=$('#consult_phno').val();
    var accountFlag=$('#accountFlag').val();
    var vendorName=$('#vendorName').val();
    
    var url='Requirements/getConsultantListBySearch?RequirementId='+RequirementId+'&consult_name='+consult_name+
    '&consult_email='+consult_email+'&consult_skill='+consult_skill+'&consult_phno='+consult_phno+'&vendor='+vendorName;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingConsultant').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingConsultant').hide(); 
            consultantListGridDisplay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;     
    
}
// Add By Aklakh
function getConsultantListAjax(){
    initSessionTimer();
    //alert(document.getElementById('vendorAssociationLi').className);
    document.getElementById('consultantListLi').className='active active_details  ';
    document.getElementById('vendorAssociationLi').className='tab-pane fade in';
    document.getElementById('consultantList').className='tab-pane fade in active ';
    var RequirementId=$('#RequirementId').val();
    var url='Requirements/getConsultantList.action?RequirementId='+RequirementId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('LoadingContent').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingConsultant').hide();
            consultantListGridDisplay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

    return false;  
}

function getConsultantList(){
    initSessionTimer();
    var RequirementId=$('#RequirementId').val();
    
    var url='Requirements/getConsultantList.action?RequirementId='+RequirementId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('LoadingContent').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingConsultant').hide();
            consultantListGridDisplay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;  
}
function consultantListGridDisplay(response){
     $(".page_option").css('display', 'block');
    var typeOfUser=$('#typeOfUser').val();
    //alert(typeOfUser)
    var requirementId=$("#RequirementId").val();
    var accountFlag=$('#accountFlag').val();
    var consultFlag="customer";
    var vendor=$('#vendor').val();
    var accountSearchID=$('#accountSearchID').val();
    var jdId =$('#jdId').val()
    var customerFlag=$('#customerFlag').val();
    var RequirementTaxTerm=$("#RequirementTaxTerm").val();
    //alert(RequirementTaxTerm)
    if(response!=null)
        var eduList=response.split("^");
   
    var table = document.getElementById("consultantListTable");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(vendor!='yes'){
        var res="Vendor" + "|" + "Candidate Name" + "|" + "Phone Number" + "|" + "Email" + "|" + "Status" + "|" + "Skills" + "|" + "Rate" + "^";
    }
    else
    {
        var res="Candidate Name" + "|" + "Phone Number" + "|" + "Email" + "|" + "Status" + "|" + "Skills" + "|" + "Rate" + "^";      
    }
    if(response.length>0){
        for(var i=0;i<eduList.length-1;i++){   
       
            var Values=eduList[i].split("|");
            {  
         
         
                var row = $("<tr />")
          
                if(vendor!='yes'){
                    res=res+(Values[7] +"|"+ Values[1]+"|"+ Values[4]+"|"+ Values[2]+"|"+ Values[5]+"|"+ Values[3]+"|$"+ Values[8]+"/Hr^");      
                }
                else
                {
                    res=res+(Values[1]+"|"+ Values[4]+"|"+ Values[2]+"|"+ Values[5]+"|"+ Values[3]+"|$"+ Values[8]+"/Hr^");      
                }
                
                $("#consultantListTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
                if(vendor!='yes'){
                    row.append($("<td>" + Values[7] + "</td>"));
                }
                // row.append($('<td><a href=../recruitment/consultant/editConsultantDetails.action?vendor='+ vendor +'&consult_id='+Values[0]+'&consultFlag='+consultFlag  +'&requirementId='+requirementId+'&accountSearchID='+accountSearchID+'&accountFlag='+accountFlag+'&jdId='+jdId+'&customerFlag='+customerFlag+">"+ Values[1] +"</a></td>"));
                if(vendor!='yes'){
                    row.append($('<td><a   href=../recruitment/consultant/getConsultantDetails.action?vendor='+ vendor +'&consult_id='+Values[0]+'&consultFlag='+consultFlag  +'&requirementId='+requirementId+'&accountSearchID='+accountSearchID+'&accountFlag='+accountFlag+'&jdId='+jdId+'&customerFlag='+customerFlag+">"+ Values[1] +"</a></td>"));
                }else{
                    row.append($('<td><a href=../recruitment/consultant/getConsultantDetails.action?vendor='+ vendor +'&consult_id='+Values[0]+'&consultFlag='+consultFlag  +'&requirementId='+requirementId+'&accountSearchID='+accountSearchID+'&accountFlag='+accountFlag+'&jdId='+jdId+'&customerFlag='+customerFlag+">"+ Values[1] +"</a></td>"));
                }
                //row.append($("<td>" + Values[7] + "</td>"));
                row.append($("<td>" + Values[2] + "</td>"));
                if(Values[3]=='null'||Values[3]==""){
                    Values[3]="";
                    row.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick=" showSkillDetailsCustomer(\'' + Values[3] + '\');" >'+Values[3].substr(0,10)+"</td>"));
                }else{
                    row.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick=" showSkillDetailsCustomer(\'' + Values[3] + '\');" >'+Values[3].substr(0,10)+"...</td>"));
                }
                row.append($("<td>" + Values[4] + "</td>"));
                row.append($("<td>" + Values[5] + "</td>"));
                row.append($("<td>$" + Values[8] + "/Hr.</td>"));
                row.append($("<td><figcaption><button type='button' value="+ Values[6] +" onclick=doAttachmentDownload("+ Values[6] +")><img src='./../includes/images/download.png' height='20' width='20' ></button></figcaption></td>"));
                if(vendor!='yes'){
                    row.append($('<td><a href=.././Requirements/techReview.action?requirementId='+requirementId+'&consult_id='+Values[0]+'&accountSearchID='+accountSearchID+'&jdId='+jdId+'&accountFlag='+accountFlag+">Click</td>"));
                }
                if(typeOfUser=='AC'){
                    if(RequirementTaxTerm=='PE' && (Values[5]=='Selected' || Values[5]=='Denied' )){
                        row.append($('<td><a href="#" class="SOW_popup_open" onclick=SOWPopup(\'' + Values[0] + '\',\'' + Values[8] + '\');><img src="../includes/images/go.gif" height="20" width="25"></td>'));
                    }else {
                        row.append($('<td><a href="#" ><img style="opacity: 0.4;cursor:default" src="../includes/images/go.gif" height="20" width="25"></td>'));
                    }
                }else{
                    if(RequirementTaxTerm=='CO' && (Values[5]=='Selected' || Values[5]=='Denied' ) ){
                        row.append($('<td><a href="#" class="SOW_popup_open" onclick=SOWPopup(\'' + Values[0] + '\',\'' + Values[8] + '\');><img src="../includes/images/go.gif" height="20" width="25"></td>'));
                    }else{
                        row.append($('<td><a href="#" ><img style="opacity: 0.4;cursor:default" src="../includes/images/go.gif" height="20" width="25"></td>'));
                    }
                }
                
            //                if(accountFlag=='csr'&& Values[5]!='Selected' )
            //                {
            //                    row.append($('<td >'  + "<img src='./../includes/images/user-login.png' height='20' width='30' style='opacity: 0.3' > " + "</td>"));
            //                }
            //                if(accountFlag=='csr'&& Values[5]=='Selected' )
            //                {
            //                    row.append($('<td ><a href="#" class="consultantLoginOverlay_popup_open"  onclick="consultantOverlayLogin(\'' + Values[2] + '\',\'' + Values[0] + '\',\'' + Values[5] + '\')">'  + "<img src='./../includes/images/user-login.png' height='20' width='30'  > " + "</td>"));
            //                }
            //                if(Values[5]=='Selected'){
            //                    if(vendor=='yes')
            //                    {
            //                        if(RequirementTaxTerm!='PE')
            //                        {
            //                            row.append($('<td><center><a href=# class="Migration_popup_open" onclick="migration_overlay(\'' + Values[2] + '\',\'' + Values[0] + '\');">'+'<img src="../includes/images/userMigration.png" height="25" width="25"></center></td>'));  
            //                        //document.getElementById('consult_id').value=Values[0];
            //                        //document.getElementById('cName').value=Values[2];
            //                        }
            //                        else
            //                            row.append($("<td><center> -</center> </td>"));    
            //                    }
            //                    else
            //                    {  // row.append($('<td><center><a href="#" onclick="doMigration('+Values[0]+');">'+"<img src='./../includes/images/migration.png' height='25' width='25' ></center></td>"));
            //                     
            //                        if(RequirementTaxTerm=='PE')
            //                        {    
            //                            row.append($('<td><center><a href=# class="Migration_popup_open" onclick="migration_overlay(\'' + Values[2] + '\',\'' + Values[0] + '\');">'+'<img src="../includes/images/userMigration.png" height="25" width="25"></center></td>'));
            //                        // document.getElementById('consult_id').value=Values[0];
            //                        // document.getElementById('cName').value=Values[2];
            //                        }
            //                        //  row.append($("<td><figcaption>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' value=click onclick=doMigration("+ Values[0] +")><img src='./../includes/images/migration.png' height='20' width='20' ></button></figcaption></td>"));
            //                        else
            //                            row.append($("<td><center> -</center> </td>"));    
            //                    }
            //                     
            //                  
            //                }
            //                else
            //                    row.append($("<td><center> -</center> </td>"));
            }
        }
        document.getElementById("gridDownload").value=res;
    }
    
    else
    {
        var row = $("<tr />")
        $("#consultantListTable").append(row);
        row.append($('<td colspan="10"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));  
        $(".page_option").css('display', 'none');
    }

  $('#consultantListTable').tablePaginate({navigateType:'navigator'},recordPage);
    pager.init(); 
   
}
function migration_overlay(cName,consult_id){
    document.getElementById('consult_id').value=consult_id;
    document.getElementById('cName').value=cName;
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
    var consult_id=document.getElementById("consult_id").value;
    //var vConsult=document.getElementById("vConsult").value;
    var migrationEmailId=document.getElementById("migrationEmailId").value;
    var loginId=document.getElementById("loginId").value;
    var status;
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
        if(vendor=='yes')
            status="C";
        else
            status="P";
      
        var url="Requirements/userMigration.action?req_Id="+req_Id+"&consult_id="+consult_id+"&migrationStatus="+status+"&migrationEmailId="+migrationEmailId;
        alert(url)
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                if(req.responseText==1){
                    $("mig").html(" <b><font color='green'>migrated Succesfully</font></b>");
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

function doAttachmentDownload(acc_attachment_id)
{
    //alert(acc_attachment_id);
    //var path=document.getElementById(acc_attachment_id).value;
    //alert(path);
    $("#resume").html("");
    var reqId=$("#RequirementId").val();
    var accountId=$("#accountSearchID").val();
    var accFlag=$("#accountFlag").val();
    var customerFlag=$("#customerFlag").val();
    
    var consult_name=$('#consult_name').val();
    var consult_email=$("#consult_email").val();
    var consult_skill=$('#consult_skill').val();
    var consult_phno=$('#consult_phno').val();
    var techSearch=$('#techSearch').val();
    var jdId=$('#jdId').val();
    var vendor=$("#vendor").val();
    //alert(techSearch);
    window.location = '../recruitment/consultant/consultDownloadAttachment.action?acc_attachment_id='
    +acc_attachment_id+'&requirementId='+reqId+'&accountSearchID='+accountId+
    '&accountFlag='+accFlag+'&customerFlag='+customerFlag+'&consult_name='+consult_name+'&consult_email='+consult_email+
    '&consult_skill='+consult_skill+'&consult_phno='+consult_phno+'&techSearch='+techSearch+'&jdId='+jdId+'&vendor='+vendor;
}

// add by Aklakh
function getSearchRequirementsList()
{
    var jobTitle=$("#jobTitle").val();
    var requirementSkill=$("#requirementSkill").val();
    var requirementStatus=$("#requirementStatus").val();
    var reqStart=$("#reqStart").val();
    var reqEnd=$("#reqEnd").val();
    //var accountFlag=$("#accountFlag").val();
    var vendor=$("#vendor").val();
    var accountFlag="csr";   
    var accountSearchID=$("#accountSearchID").val();
    //alert(accountFlag)
    if(reqStart!="")
    {
        if (reqEnd=="")
        {
            alert("Please select End date")
            return false;
        }
    }
        
    if(reqEnd!="")
    {
        if (reqStart=="")
        {
            alert("Please select Start date")
            return false;
        }
    }
    if(reqStart!= '' && reqEnd!= '' && reqStart> reqEnd)
    {
        alert("End date should be greater than Start date!");
        return false;
    }

    var url='getSearchRequirementsList.action?jobTitle='+jobTitle+'&requirementSkill='+requirementSkill+'&requirementStatus='+requirementStatus+'&reqStart='+reqStart+'&reqEnd='+reqEnd+'&accountFlag='+accountFlag+'&vendor='+vendor+'&accountSearchID='+accountSearchID;
    //alert(url);
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingReq').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingReq').hide();
            // alert("response"+req.responseText);
            populateReqTableReq(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    return false;
}
// add by Aklakh
function populateReqTableReq(response){
    //alert(response);  
     $(".page_option").css('display', 'block');
    var reqList=response.split("^");
    var accountFlag="csr";
    var vendor=$("#vendor").val();
    var orgid=$("#orgid").val();
    //var accountSearchID=$("#accountSearchID").val();
    //  alert(orgid);
    var accountSearchID=document.getElementById("accountSearchID").value;
    var table = document.getElementById("reqTableInAccount");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        for(var i=0;i<reqList.length-1;i++){     
       
            var Values=reqList[i].split("|");
            {  
                                                         
                var reqRow = $("<tr />")
                $("#reqTableInAccount").append(reqRow); //this will append tr element to table... keep its reference for a while since we will add cels into it
                if(vendor=='yes'){
                    reqRow.append($('<td><a href="../Requirements/requirementedit.action?accountSearchID=' + accountSearchID + '&jdId='+ Values[12] +'&requirementId='+Values[0]+'&accountFlag='+accountFlag+'&vendor=yes" > ' + Values[12] + "</td>"));
                    reqRow.append($("<td>" +Values[1] + "</td>"));    
                }
                else{
                    reqRow.append($('<td><a href="../Requirements/requirementedit.action?accountSearchID=' + accountSearchID + '&jdId='+ Values[12] +'&requirementId='+Values[0]+'&accountFlag='+accountFlag+'" > ' + Values[12] + "</td>"));
                    reqRow.append($("<td>" +Values[1] + "</td>"));
                }
                if(vendor!='yes'){
                    reqRow.append($("<td>" + Values[2] + "</td>"));
                }
                if(Values[10]=='null'||Values[10]==""){
                    Values[10]="";
                    reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick=" showSkillDetailsCSR('+Values[0]+');" >'+Values[10].substr(0,10)+"</td>"));
                }else{
                    reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick=" showSkillDetailsCSR('+Values[0]+');" >'+Values[10].substr(0,10)+"...</td>"));
                }
                if(Values[11]=='null'||Values[11]==""){
                    Values[11]="";
                    reqRow.append($('<td><a href="#" class="preSkillOverlay_popup_open" onclick=" showPreferedSkillDetailsCSR('+Values[0]+');" >'+Values[11].substr(0,10)+"</td>"));
                }else{
                    reqRow.append($('<td><a href="#" class="preSkillOverlay_popup_open" onclick=" showPreferedSkillDetailsCSR('+Values[0]+');" >'+Values[11].substr(0,10)+"...</td>"));
                }       

                reqRow.append($('<td><a href="" class="recruiterOverlay_popup_open" onclick="showOverlayRecruiter('+Values[5]+');" >'+Values[7]+"</td>"));
                reqRow.append($('<td><a href="" class="recruiterOverlay_popup_open" onclick="showOverlayRecruiter('+Values[6]+');" >'+Values[8]+"</td>"));
                reqRow.append($("<td>" + Values[4] + "</td>"));
                
            }
        }
    }
    else{
        var row = $("<tr />")
        $("#reqTableInAccount").append(row);
        row.append($('<td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }
 $('#reqTableInAccount').tablePaginate({navigateType:'navigator'},recordPage);
   
    reqPag.init();     
    
}
// add by Aklakh

function requiredSkillOverlay(response){
    document.getElementById("reqSkillDetails").value=response;
    var specialBox = document.getElementById("reqskillBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#recSkillOverlay_popup').popup(      
        ); 
}
function preferredSkillOverlay(response){
    //alert(response)
    document.getElementById("preSkillDetails").value=response;
    var specialBox = document.getElementById("preskillBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#preSkillOverlay_popup').popup(      
        ); 
          
}
function showSkillDetailsCSR(sid){
   
    var url='getSkillDetaisls.action?requirementId='+sid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                if(req.responseText!='null'){
                    document.getElementById("reqSkillDetails").value=req.responseText;
                }else{
                    document.getElementById("reqSkillDetails").value="Sorry, No skills!";
                }
                
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    var specialBox = document.getElementById("reqskillBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#recSkillOverlay_popup').popup(   
        );
    return false;
}
// add by Aklakh
function showPreferedSkillDetailsCSR(sid){
   
    var url='getPreferedSkillDetails.action?requirementId='+sid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText);
                if(req.responseText!='null'){
                    document.getElementById("preSkillDetails").value=req.responseText;
                }else{
                    document.getElementById("preSkillDetails").value="Sorry, No Preferred skills!";
                }
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    var specialBox = document.getElementById("preskillBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#preSkillOverlay_popup').popup(      
        );
    return false;
}

$(document).ready(function(){
    
   
    $('#skillShow_popup').popup(); 
});
// add by Aklakh
function showOverlayRecruiter(id){
    //  alert(id)
    var url='getRecruiterOverlay.action?id='+id;
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert("result is::")
            // setVendorStates(req.responseText);
            setRecruiterOverlay(req.responseText)
        } 
    //alert(req.readyState +" and "+req.status)

    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#recruiterOverlay_popup').popup(      
        );    
    return false;
}

// add by Aklakh
function setRecruiterOverlay(response){
    var Values=response.split("|");
    document.getElementById("recruiterNameOverlay").value=Values[0];
    document.getElementById("recruiterEmailIdOverlay").value=Values[1];
    document.getElementById("recruiterPhoneOverlay").value=Values[2];

}

function closeRequirementOverlay(){
    var specialBox = document.getElementById("recruiterBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";  
    } else {
        specialBox.style.display = "block"; 
    }
    $('#recruiterOverlay_popup').popup(      
        );    
    return false;    
}
// Add By Aklakh 
function enterDateRepositoryReq()
{
    document.getElementById('reqStart').value = "";
    document.getElementById('reqEnd').value = "";
    alert("Please select from the Calender !");
    return false;
}

// Add By Aklakh

function showSkillDetailsCustomer(sid){
    // alert(sid);
    //    var url='../acc/getConsultantSkillCSR.action?requirementId='+sid;
    //    var req=initRequest(url);
    //    req.onreadystatechange = function() {
    //        if (req.readyState == 4) {
    //            if (req.status == 200) {   
    //                alert(req.responseText);
    document.getElementById("customerSkillDetails").value=sid;
    //                
    //            } 
    //        }
    //    };
    //    req.open("GET",url,"true");
    //    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    //    req.send(null);
    var specialBox = document.getElementById("consultantSkillSetBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#recSkillOverlay_popup').popup(   
        );
    return false;
}

function consultantSkillCloseOverlay(){
    var specialBox = document.getElementById('consultantSkillSetBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#consultantSkillOverlay_popup').popup(      
        );
}
// Add By Aklakh
function getReportingPerson(){
    var designationId=document.getElementById('designation').value;
    var projectID =document.getElementById('projectID').value;  
    // alert(designationId+"  "+projectID);
    var url=CONTENXT_PATH+'/acc/setReportingPerson.action?designationId='+designationId+'&projectID='+projectID;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert(req.responseText);
                populateReportingPerson1(req.responseText);
                populateReportingPerson2(req.responseText);
            } 

        }
    };
    
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
 
function populateReportingPerson1(personList) { 
    // alert(personList); 
    // var primaryReporting = document.getElementById("memberPrimaryReporting"); 
    var reportingList=personList.split("^"); 
    var $select = $('#memberPrimaryReporting'); 
    $select.find('option').remove(); 
    //  $('<option>').text(-1).text("select").appendTo($select);
    for(var i=0;i<reportingList.length-1;i++){
        var Values=reportingList[i].split("|");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select);
          
        }
    }
}
function populateReportingPerson2(personList) { 
    // alert(personList); 
    var reportingList=personList.split("^"); 
    var $select = $('#memberSecondaryReporting'); 
    $select.find('option').remove(); 
    //  $('<option>').text(-1).text("select").appendTo($select);
    for(var i=0;i<reportingList.length-1;i++){
        var Values=reportingList[i].split("|");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select);
        }
    }
}
////////////////////////////////////////////////////fro adding vendor/////////////////////////////////////////
//for adding a vendor////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
// for account names ie" VENDOR NAMES



function getVendorNames(){
    // alert("ENTERED")
    var url;
    var v_empName=(document).getElementById('vendorName').value;
    var accountSearchID=$('#accountSearchID').val();
    //var v_empName=id.value;
    //alert("---"+id.valueOf());
    if (v_empName == "") {
        clearTable();
    } else {
        
        //alert("Empname->"+v_empName+"--len-->"+v_empName.length);
    
        if(v_empName.length>=1){
            url=CONTENXT_PATH+"/getVendorNamesAutoPopulate.action?empName="+v_empName+'&customerId='+accountSearchID;
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        //alert(v_empName)
                        parseVendorNames(req.responseXML);
                    } else if (req.status == 204){
                        clearTable();
                    }
                }
            };
            req.open("GET", url, true);
            req.send(null);
        }
    }
}

function parseVendorNames(responseXML) {
    //alert("hii");
    clearTable();
    //alert("hii");
    var consultants = responseXML.getElementsByTagName("EMPLOYEES")[0];
    //alert(consultants.childNodes.length);
    if (consultants.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "0");
    } else {
        clearTable();
    }
    //alert("Hello"+consultants.childNodes.length)
 

    //alert("Hello")
    
    var consultant = consultants.childNodes[0];
    var chk=consultant.getElementsByTagName("VALID")[0];
    isExist = chk.childNodes[0].nodeValue;
    if(chk.childNodes[0].nodeValue =="true") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "";
        document.getElementById("menu-popup").style.display = "block";
        for (loop = 0; loop < consultants.childNodes.length; loop++) {
            var consultant = consultants.childNodes[loop];
            var loginId = consultant.getElementsByTagName("EMPID")[0];
            var empName = consultant.getElementsByTagName("NAME")[0];
            //alert(empName.childNodes[0].nodeValue)
            //alert(loginId.childNodes[0].nodeValue)

            appendVendorNames(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
        var position;
        position = findPosition(document.getElementById("eNameTechReview2"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = "<font color=red>  Vendor doesn't Exists/Already added! </font>";
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

function appendVendorNames(empName,loginId) {
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
        "javascript:setVendorNames('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}

function setVendorNames(empName,loginId){
    clearTable();
    // alert("in set_cust");
    document.getElementById("vendorName").value =empName;
    document.getElementById("vendorId").value =loginId;
    getVendorDetails();
   
}
////////////////////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////////////
function getVendorDetails(){
    var vendorId=$('#vendorId').val();
    //alert(vendorId)
    var url=CONTENXT_PATH+'/acc/getVendorDetails.action?vendorId='+vendorId;
    //alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert(req.responseText);
                var vendorDetails=req.responseText.split("|");
                $("#vendorURL").val(vendorDetails[0]);
                $("#vendorStatus").val(vendorDetails[1]);
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function saveVendorTierDetails(){
    var vendorId=$('#vendorId').val();
    var vendorTier=$('#vendorTier').val();
    var accountSearchID=$('#accountSearchID').val();
    //var vendorStatus=$('#vendorStatus').val();
    var vendorComments=$('#vendorComments').val();
    var pf=$("#PF").is(':checked') ? 1 : 0;
    var flag=true;
    //alert(pf)
    if(vendorId==0){
        flag=false;
        $("e").html(" <b><font color='red'>Vendor name should not be empty</font></b>");
    }
    if(pf==0 && vendorTier==0 ){
        flag=false;
        $("e").html(" <b><font color='red'>Vendor should belongs to either Tier or Head Hunter</font></b>");
    }
    if(flag==true){
        var url=CONTENXT_PATH+'/acc/saveVendorTierDetails.action?vendorId='+vendorId+'&VendorTierId='+vendorTier+'&accountSearchID='+accountSearchID+'&pf='+pf+'&vendorComments='+vendorComments;
        //alert(url);
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    //alert(req.responseText)
                    if(req.responseText=='Success'){
                        $("e").html(" <b><font color='green'>Vendor Added Succesfully</font></b>");
                        $('#vendorId').val('');
                        $('#vendorTier').val('0');
                        $('#vendorComments').val('');
                        $('#vendorName').val('');
                        $('#vendorURL').val('');
                        document.getElementById("PF").checked = false;
                        document.getElementById("submitbutton").disabled=false;
                    }
                } 
            }
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    
}
function getOrgConsultant(vendorName,ven_id)
{
    alert(ven_id);
    var techSearch="search";
    document.getElementById("techSearch").value=techSearch;
    document.getElementById('consultantListLi').className='active active_details  ';
    document.getElementById('vendorAssociationLi').className='tab-pane fade in';
    document.getElementById('consultantList').className='tab-pane fade in active ';
    var RequirementId=$('#RequirementId').val();
    var consult_name=$('#consult_name').val();
    var consult_email=$("#consult_email").val();
    var consult_skill=$('#consult_skill').val();
    var consult_phno=$('#consult_phno').val();
    var accountFlag=$('#accountFlag').val();
    
    var url='Requirements/getConsultantListBySearch?RequirementId='+RequirementId+'&consult_name='+consult_name+
    '&consult_email='+consult_email+'&consult_skill='+consult_skill+'&consult_phno='+consult_phno+'&vendor='+vendorName+'&ven_id='+ven_id;
    //    var RequirementId=$('#RequirementId').val();
    //    alert(RequirementId);
    //    //var url='Requirements/getConsultantList.action?RequirementId='+RequirementId;
    //    
    //   var url='Requirements/getConsultantListBySearch?RequirementId='+RequirementId+'&vendor='+vendorName;
    // alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingConsultant').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingConsultant').hide(); 
            consultantListGridDisplay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;   
}
google.load('visualization', '1.1', {
    'packages': ['corechart']
});
google.setOnLoadCallback(getVendorDashboardList);

function getVendorDashboardList()
{
    var year=$("#year").val();
    var month=$("#month").val();
    //alert("getVendorDashboardList");
    var url='getVendorDashboardList.action?year='+year+'&month='+month;
    //alert(url);
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        
        if (req.readyState == 4 && req.status == 200) {
           
            //alert("response"+req.responseText);
            populateVendorDashboard(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    return false;
}

function populateVendorDashboard(response){
    //alert(response);  
    var reqList=response.split("^");
    var table = document.getElementById("dashboardResults");
    var montharr = new Array();
    var reqarr=new Array();
    var selarr =new Array();
    var rejarr=new Array();
    var servicingarr=new Array();
    var shortlistarr=new Array();
    var processarr=new Array();
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(response.length>0){
        $("#BarchartDiv").css('visibility', 'visible');
        for(var i=0;i<reqList.length-1;i++){     
       
            var Values=reqList[i].split("|");
            {  
                var reqRow = $("<tr />")
                $("#dashboardResults").append(reqRow); //this will append tr element to table... keep its reference for a while since we will add cels into it
                //reqRow.append($("<td>" + Values[0] + "</td>")); For req.Won
                // reqRow.append($("<td>" + Values[1] + "</td>")); For req.Lost..As of now These two not required .
                reqRow.append($("<td>" + Values[0] + "</td>")); // For Month
                reqRow.append($("<td>" + Values[1] + "</td>")); // Requirement count
                reqRow.append($("<td>" + Values[2] + "</td>")); //Processing
                reqRow.append($("<td>" + Values[5] + "</td>")); //Servicing
                reqRow.append($("<td>" + Values[3] + "</td>")); //Selected
                reqRow.append($("<td>" + Values[6] + "</td>")); //Short listed
                reqRow.append($("<td>" + Values[4] + "</td>")); //Rejected.
                
                
                
                montharr.push(Values[0]);
                reqarr.push(parseInt(Values[1]));
                selarr.push(parseInt(Values[3]));
                rejarr.push(parseInt(Values[4]));
                servicingarr.push(parseInt(Values[5]));
                shortlistarr.push(parseInt(Values[6]));
                processarr.push(parseInt(Values[2]));
            }
        }
        showChart(montharr,reqarr,selarr,rejarr,servicingarr,shortlistarr,processarr);   
    }
    else{
        var row = $("<tr />")
        $("#dashboardResults").append(row);
        row.append($('<td colspan="7"><font style="color: red;font-size: 15px;">No Records to display</font></td>'))
        $("#BarchartDiv").css('visibility', 'hidden');
    
    }
    dashPager.init();
    dashPager.showPageNav('dashPager', 'pageNavPosition');
    dashPager.showPage(1);
}

function showChart(month,reqCount,selected,rejected,servicing,shortlisted,processing )
{
    // alert(month.length);
     
    var Combined = new Array();
            Combined[0] = ['Year', 'Processing','Servicing', 'Selected','Short Listed','Rejected'];
            for (var i = 0; i < month.length; i++){
                Combined[i + 1] = [ month[i], processing[i], servicing[i], selected[i], shortlisted[i], rejected[i] ];
            }
            //alert(Combined+"------>Combined");
            //second parameter is false because first row is headers, not data.
            var data = google.visualization.arrayToDataTable(Combined, false);
    
            var options = {
                // width: 370,
                // height:300,
                legend: { position: 'top', alignment: 'center' },
               // title: 'Monthly Analysis',
                colors: ['#0000FF', '#00FF00', '#FF0000'],
                titleColor: "green",
                    vAxis: {
                        title: "Consultants",
                        titleColor: "green"
                    },
                    hAxis: {
                        title: "Months",
                        titleColor: "green"
                    }
                // animation: {
                //duration: 1000,
                //easing: 'linear'
                //vAxis: {
                //viewWindow: {
                //max: 8
            }
            var chart = new google.visualization.ColumnChart(document.getElementById('Barchart'));
            document.getElementById('chartTitle').innerHTML = "<font color=green><b>Consultants Monthly Analysis</b></font>";
            drawChart();
            function drawChart() {
                // Instantiate and draw our chart, passing in some options.
                chart.draw(data, options);
            }
            
            $(window).resize(function () {
                drawChart();
            });
    
}

function consultantOverlayLogin(email,consultantId,status)
{
    document.getElementById("consultantId").value=consultantId;
    document.getElementById("consultantEmail").value=email;
    consultantLoginCredential(email,status);
    consultantCheck(consultantId) 
       
      
         
}
function consultantCheck(consultantId)
{
    //alert("consultantCheck"+consultantId)
    var url='Requirements/checkConsultantExist.action?consultantCheck='+consultantId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert(req.responseText)
                document.getElementById("outputMessage").innerHTML=req.responseText;
                if(req.responseText=="Consultant is already Registered Please Check Email")
                {
                    document.getElementById('contactSend').style.display="none";   
                    document.getElementById('contactCancel').style.display="none";   
                }
                if(req.responseText=="Do you want to send Login Credentials To Email?")  
                {
                    document.getElementById('contactSend').style.display="";   
                    document.getElementById('contactCancel').style.display="";   
                    
                }
            } 
            else
            {
            // alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
 
}
function consultantLoginCredential(email)
{
    document.getElementById("consultantdivEmail").innerHTML="("+email+")";
   
  
    var specialBox = document.getElementById('consultantLoginBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#consultantLoginOverlay_popup').popup(      
        ); 
}
function saveConsultantLoginDetails(){
    var id= document.getElementById("consultantId").value;
  
    //alert(id);    
    var url=CONTENXT_PATH+'/recruitment/consultant/consultantLoginDetails.action?consult_id='+id;
    //  alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {                
                document.getElementById("outputMessage").innerHTML=req.responseText; 
                //alert( document.getElementById("responseMessage").innerHTML)
                if(req.responseText=="Consultant login credentials sent succesfully!")
                {
                    document.getElementById('contactSend').style.display="none";   
                    document.getElementById('contactCancel').style.display="none";       
                }
            } 
            else
            {
                alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    return false;
}
function saveSOWFinderFeeType(){
    var RequirementTaxTerm=$("#RequirementTaxTerm").val();
    var SOWSelectValue=$("#SOWSelectValue").val();
    var sValue="";
    //alert(SOWSelectValue)
    if(SOWSelectValue=='SOWProceed'){
        sValue="S";
    }else if(SOWSelectValue=='FinderFee'){
        sValue="F";
    }else{
        sValue="D";
    }
    var rateSalary=$("#rateSalary").val();
    var conId=$("#conId").val();
    var reqId=$("#req_Id").val();
    //    alert("HI "+SOWSelectValue+" "+RequirementTaxTerm+" "+rateSalary+" "+vendorId+" "+conId+" "+reqId)
    var url=CONTENXT_PATH+'/sag/sow/doInsertSAGRecord.action?rateSalary='+rateSalary
    +'&consultantId='+conId
    +'&requirementId='+reqId
    +'&sowFlagValue='+sValue;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //alert(req.responseText);
                if(req.responseText=='Updated'){
                    $("res").html(" <b><font color='green'>Record Denied Successfully!</font></b>");
                    $("#SOWSpan").show().delay(5000).fadeOut();
                }else{
                    $("res").html(" <b><font color='green'>Record saved Successfully!</font></b>");
                    $("#SOWSpan").show().delay(5000).fadeOut();
                }
                
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function SOWPopup(conId,rateSalary){
    //alert(customerId)
    var specialBox = document.getElementById('SOWBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#SOW_popup').popup(      
        );
    $("#rateSalary").val(rateSalary);
    $("#conId").val(conId);
}
function SOWPopupClose(){
    var specialBox = document.getElementById('SOWBox');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#SOW_popup').popup(      
        );
    getConsultantList();
}
function enterDateRepositoryAccount(id)
{
    $(id).val(" ");
    return false;
}
function questionOverlay(response){
    // alert("overlay");
     document.getElementById("qname").style.display='none';
      document.getElementById("hideText").style.display='block';
    document.getElementById("quesDetails").value=response;
    var specialBox = document.getElementById("quesOverlayBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    

    $('#questionOverlay_popup').popup(      
        ); 
}
function getImagePath(quesId,question){
  
    var url='getImagePath.action?quesId='+quesId;
     document.getElementById("quesDetails").value=question;
    //alert(url);
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        
        if (req.readyState == 4 && req.status == 200) {
           
           // alert("response"+req.responseText);
            document.getElementById("qname").style.display='block';
           // document.getElementById("hideText").style.display='none';
          
            var oldChild=document.getElementById("qname").lastChild;

            var myImage = document.createElement("img");
            myImage.src = "/MSB/renderImage/rImage.action?path="+req.responseText+"";

            document.getElementById("qname").replaceChild(myImage, oldChild);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    return false;
}

function questionOverlayImage(){
    //document.getElementById("quesDetails").value=response;
    var specialBox = document.getElementById("quesOverlayBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        

    } else {
        specialBox.style.display = "block";      
        

    }
    // Initialize the plugin    

    $('#questionOverlay_popup').popup(      
        ); 
}

