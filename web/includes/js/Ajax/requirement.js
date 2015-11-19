function closeRecruiterOverlay()
{
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
function showOverlayRecruiter(id){
    // alert(id)
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
function setRecruiterOverlay(response){
    var Values=response.split("|");
    document.getElementById("recruiterNameOverlay").value=Values[0];
    document.getElementById("recruiterEmailIdOverlay").value=Values[1];
    document.getElementById("recruiterPhoneOverlay").value=Values[2];

}

//praveen
function getSearchRequirementsList()
{
    initSessionTimer();
    // alert("viewAccountID-->"+$("#viewAccountID").val());
    var jobTitle=$("#jobTitle").val();
    var requirementSkill=$("#requirementSkill").val();
    var requirementStatus=$("#requirementStatus").val();
    var reqStart=$("#reqStart").val();
    var reqEnd=$("#reqEnd").val();
    var accountFlag=$("#accountFlag").val();
    var vendor=$("#vendor").val();
    var jdId=$("#jdId").val();
    var reqCategoryValue=$("#reqCategoryValue").val();
    var reqCreatedBy=$("#reqCreatedBy").val();
    var requirementValidation =document.getElementById("requirementValidation");
    //  alert(reqCreatedBy)
    if(reqCategoryValue==undefined)
    {
        reqCategoryValue=-1;
    }
    //  alert(reqCreatedBy)
    if(reqStart!="")
    {
        if (reqEnd=="")
        {
            // alert("Please select End date")
            $("#requirementValidation").html("<font color='red'>Please select End Date</font>");
            $("#reqEnd").css('border','1px solid red');
            return false;
        }
    }
        
    if(reqEnd!="")
    {
        if (reqStart=="")
        {
            $("#requirementValidation").html("<font color='red'>Please select Start Date</font>");
            $("#reqStart").css('border','1px solid red');
            // alert("Please select Start date")
             
            return false;
        }
    }
    if(reqStart!= '' && reqEnd!= '' )
    {
        var splitReqStartDate = reqStart.split('-');
        var reqAddStartDate = new Date(splitReqStartDate[2], splitReqStartDate[0]-1 , splitReqStartDate[1]); //Y M D 
        var splitReqEndDate = reqEnd.split('-');
        var reqAddtargetDate = new Date(splitReqEndDate[2], splitReqEndDate[0]-1, splitReqEndDate[1]); //Y M D
        var reqStartDate = Date.parse(reqAddStartDate);
        var reqTargetDate= Date.parse(reqAddtargetDate);
        var  difference=(reqTargetDate - reqStartDate) / (86400000 * 7);
        if(difference<=0)
        {
             
       
            $(requirementValidation).html(" <b><font color='red'>Start date Must be less than End date</font></b>");

            $(requirementValidation).show().delay(4000).fadeOut();

            return false;
        }
    
    }
    var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).text()); 
    });

    var url='getSearchRequirementsList.action?jdId='+ jdId +'&jobTitle='+jobTitle+
    '&skillCategoryArry='+skillCategoryArry+
    '&requirementStatus='+requirementStatus+
    '&reqCategoryValue='+reqCategoryValue+
    '&reqStart='+reqStart+'&reqEnd='+reqEnd+'&accountFlag='+accountFlag+'&vendor='+vendor+'&reqCreatedBy='+reqCreatedBy;
    // alert(url);
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingRequirements').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingRequirements').hide();
            // alert("response"+req.responseText);
            populateReqTableReq(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
        
    return false;
}
function removeEndDateErrorMsg()
{

    //alert("hello jagan")
    $("#requirementValidation").html("");
    $("#reqEnd").css('border','1px solid #ccc');
    $("#reqStart").css('border','1px solid #ccc');
   
    return false;
}

function populateReqTableReq(response){
    //alert(response);  
    $(".page_option").css('display', 'block');  
    var reqList=response.split("^");
    var accountFlag=$("#accountFlag").val();
    var vendor=$("#vendor").val();
    var orgid=$("#orgid").val();
    var role=$("#sessionPRole").val();
    //alert(role)
    var table = document.getElementById("reqTableInRecruiter");
    var customer="customer";
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
    if(vendor=='yes')
    {
        var res = "Job Id" + "|" + "Jog Title" + "|" + "Customer" + "|" + "Skills Set" + "|" + "Status" + "^"; // for grid download
    }
    else
    {
        var res = "Job Id" + "|" + "Jog Title" + "|" + "Positions" + "|" + "Skills Set" + "|" + "Approver" + "|" + "Requisitioner" + "|" + "Status" + "^"; // for grid download
    }
    if(response.length>0){
        for(var i=0;i<reqList.length-1;i++){     
       
            var Values=reqList[i].split("|");
            {  
                                                         
                var reqRow = $("<tr />")
                $("#reqTableInRecruiter").append(reqRow); //this will append tr element to table... keep its reference for a while since we will add cels into it
                if(vendor=='yes')
                {
                    res=res+(Values[12] +"|"+ Values[1]+"|"+ Values[9]+"|"+ Values[10]+"|"+ Values[4]+"^");      
                }
                else
                {
                    res=res+(Values[12]+"|"+ Values[1]+"|"+ Values[2]+"|"+ Values[10]+"|"+ Values[7]+"|"+ Values[8]+ "|"+ Values[4]+"^");      
                }
                if(vendor=='yes'){
                    reqRow.append($('<td><a href="../../Requirements/requirementedit.action?jdId='+ Values[12] +'&accountSearchID='+orgid+'&requirementId='+Values[0]+'&accountFlag='+accountFlag+'&vendor=yes" > ' + Values[12] + "</td>"));
                    reqRow.append($("<td>" +Values[1] + "</td>"));
                    reqRow.append("<td>"+Values[9]+"</td>");
                }
                else{
                    reqRow.append($('<td><a href="../../Requirements/requirementedit.action?jdId='+ Values[12] +'&customerFlag='+customer+'&accountSearchID='+orgid+'&requirementId='+Values[0]+'&accountFlag='+accountFlag+'" > ' + Values[12] + "</td>"));
                    reqRow.append($("<td>" +Values[1] + "</td>"));     
                }
                if(role==13){
                    reqRow.append($("<td>" + Values[13] + "</td>"));
                }
                if(vendor!='yes'){
                    reqRow.append($("<td>" + Values[2] + "</td>"));
                }
                if(Values[10]=='null'||Values[10]==""){
                    Values[10]="";
                    // reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick="showSkillDetails('+Values[0]+');">'+Values[10].substr(0,10)+"</td>"));
                    reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick="reqSkillOverlay(\''+Values[10]+'\');">'+Values[10].substr(0,10)+"</td>"));
                }else{
                    //reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick="showSkillDetails('+Values[0]+');">'+Values[10].substr(0,10)+"...</td>"));
                    reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick="reqSkillOverlay(\''+Values[10]+'\');">'+Values[10].substr(0,10)+"...</td>"));
                }
                //                if(Values[11]=='null'||Values[11]==""){
                //                    Values[11]="";
                //                    reqRow.append($('<td><a href="#" class="preSkillOverlay_popup_open" onclick="showPreferedSkillDetails('+Values[0]+');" >'+Values[11].substr(0,10)+"</td>"));
                //                }else{
                //                    reqRow.append($('<td><a href="#" class="preSkillOverlay_popup_open" onclick="showPreferedSkillDetails('+Values[0]+');" >'+Values[11].substr(0,10)+"...</td>"));
                //                }
                //                // reqRow.append($('<td><a href="#" class="recSkillOverlay_popup_open" onclick="showReqSkillOverlay('+Values[0]+');" >Click'+"</td>"));
                // reqRow.append($('<td><a href="#" class="preSkillOverlay_popup_open" onclick="showPreReqSkillOverlay('+Values[0]+');" >Click'+"</td>"));
                if(vendor!='yes'){
 
                    reqRow.append($('<td><a href="" class="recruiterOverlay_popup_open" onclick="showOverlayRecruiter('+Values[5]+');" >'+Values[7]+"</td>"));
                    reqRow.append($('<td><a href="" class="recruiterOverlay_popup_open" onclick="showOverlayRecruiter('+Values[6]+');" >'+Values[8]+"</td>"));
                }
                reqRow.append($("<td>" + Values[4] + "</td>"));
                if(vendor!='yes'){
                    //                    if(Values[4]=='Closed'){
                    //                        reqRow.append($('<td><center><a href="#" >'+"<img src='../../includes/images/release.png' height='20' width='20' style='opacity:0.3' ></center></td>"));
                    //                    }
                    //                    else if(Values[4]=='Released'){
                    //                        reqRow.append($('<td><center><a href="#" >'+"<img src='../../includes/images/release.png' height='20' width='20' style='opacity:0.3' ></center></td>"));
                    //                    }
                    if(Values[4]=='Opened')
                    {
                        if(role==3 || role==13){
                            reqRow.append($('<td><center><a href="#" onclick="doReleaseRequirement('+Values[0]+','+orgid+',\''+Values[14]+ '\');">'+"<img src='../../includes/images/release.png' height='20' width='20' ></center></td>"));
                        }
                        else
                        {
                            reqRow.append($('<td><center><a href="#" >'+"<img src='../../includes/images/release.png' height='20' width='20' style='opacity:0.3' ></center></td>"));       
                        }
                    }else{
                        reqRow.append($('<td><center><a href="#" >'+"<img src='../../includes/images/release.png' height='20' width='20' style='opacity:0.3' ></center></td>"));
                    }
                }
                if(vendor=='yes'){
                    if(Values[4]=='Closed'){
                        reqRow.append($('<td><center>'+"<img src='../../includes/images/addCons.png' height='20' width='20' style='opacity:0.3' ></center></td>"));      
                    }else{
                        reqRow.append($('<td><center><a href="../../recruitment/consultant/doAddConsultantForReq.action?requirementId='+Values[0]+'&jdId='+Values[12]+'&jobTitle='+Values[1]+'&targetRate='+Values[15]+'&maxRate='+Values[16]+'&orgid='+orgid+'">'+"<img src='../../includes/images/addCons.png' height='20' width='20' ></center></td>"));      
                    }
                }
                if(role==3){
                    reqRow.append($('<td><center><a href="<%=request.getContextPath()%>/../../../Requirements/doCopyRequirement.action?customerFlag='+customer+'&accountSearchID='+orgid+'&requirementId='+Values[0]+'">'+"<img src='../../includes/images/copyImage.png' height='20' width='20' ></center></td>"));      
                }
                
                
            }
        }
        document.getElementById("gridDownload").value=res;
    }
    else{
        var row = $("<tr />")
        $("#reqTableInRecruiter").append(row);
        row.append($('<td colspan="11"><font style="color: red;font-size: 15px;">No Records to display</font></td>'));
        $(".page_option").css('display', 'none');
    }

    $('#reqTableInRecruiter').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    pag.init();     
   
}

 


function doOnLoadReqList() {
                
                
    myCalendar = new dhtmlXCalendarObject(["reqStart","reqEnd"]);
    // alert("hii1");
    myCalendar.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    myCalendar.setDateFormat("%m-%d-%Y");
    myCalendar.hideTime();
    
}

function reqSkillOverlay(response){
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
function preSkillOverlay(response){
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
    } else {
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
                // row.append($('<td><a href="" class="eduEdit_popup_open " onclick=" showEditEduOverlayDetails('+Values[0]+');" > ' + Values[1] + "</td>"));
                //row.append($("<td>" + Values[0] + "</td>"));
                row.append($("<td>" + Values[1] + "</td>"));
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
    var url='SaveVendorsAssociationDetals.action?vendorList='+vendorList+'&accessTime='+accessTime+'&req_id='+req_id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            $("saveVendorAssociation").html(" <b><font color='green'>Vendor Association Saved Successfully</font></b>");
            getVendorAssociationDetails()
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
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
                reqRow.append($('<td><a href="" class="recSkillOverlay_popup_open" onclick=" showSkillDetails('+Values[0]+');" >'+Values[5].substr(0,10)+"</td>"));
            }
            else{
                reqRow.append($('<td><a href="" class="recSkillOverlay_popup_open" onclick=" showSkillDetails('+Values[0]+');" >'+Values[5].substr(0,10)+"..</td>"));
            }
            if(Values[6]=='null'||Values[6]==""){
                Values[6]="";
                reqRow.append($('<td><a href="" class="preSkillOverlay_popup_open" onclick=" showPreferedSkillDetails('+Values[0]+');" >'+Values[6].substr(0,10)+"</td>"));
            }
            else{
                reqRow.append($('<td><a href="" class="preSkillOverlay_popup_open" onclick=" showPreferedSkillDetails('+Values[0]+');" >'+Values[6].substr(0,10)+"..</td>"));
            }
            reqRow.append($("<td>" + Values[3] + "</td>"));
            reqRow.append($("<td>" + Values[4] + "</td>"));
            reqRow.append($('<td><a href="#" class="addConsultant_popup_open" onclick=" storeReqIdinOverlay('+Values[0]+');" >'+"Click</td>"));      

                  
        }
    }

   
    pager.init();     
    pager.showPageNav('pager', 'reqpageNavPosition'); 
    pager.showPage(1);
}


function showSkillDetails(sid){
   
    var url='../../acc/getSkillDetaisls.action?requirementId='+sid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("reqSkillDetails").value=req.responseText;
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

function showPreferedSkillDetails(sid){
   
    var url='../../acc/getPreferedSkillDetails.action?requirementId='+sid;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                document.getElementById("preSkillDetails").value=req.responseText;
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
    var RequirementId=document.getElementById("RequirementId").value;
    var url='Requirements/getVendorAssociationDetails.action?RequirementId='+RequirementId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            
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
    
    if(response!=null)
        var eduList=response.split("^");
   
    var table = document.getElementById("vendorAssociationResults");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }

    for(var i=0;i<eduList.length-1;i++){   
       
        var Values=eduList[i].split("|");
        {  
         
         
            var row = $("<tr />")
          
            $("#vendorAssociationResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($('<td><a href="" class="vendorAssoEdit_popup_open" onclick="associationEditOverlay('+Values[0]+');" > ' + Values[1] + "</td>"));
            row.append($("<td>" + Values[2] + "</td>"));
            row.append($("<td>" + Values[3] + "</td>"));
            row.append($("<td>" + Values[5] + "</td>"));
            row.append($("<td>" + Values[6] + "</td>"))
            row.append($("<td>" + Values[4] + "</td>"));
        //  row.append($("<td>" + Values[4] + "</td>"));
        //row.append($("<td>" + Values[4] + "</td>"));
        // row.append($('<td><a href="#" onclick="saveVendorContactDetails('+Values[0]+')">'  + "CLICK" + "</td>"));
        //row.append($("<td>" + Values[4] + "</td>"));
        //row.append($("<td>" + Values[7] + "</td>"));
        //onclick="saveContactDetails(' + Values[0] +');" > '
        }
    }
  
    pager.init(); 
    pager.showPageNav('pager', 'contactPageNavPosition'); 
    pager.showPage(1);
    els
    {
        $("#vendorAssociationResults").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
        row.append($("<td>" + "No Records Display" + "</td>"));
    }
}
 
function searchVendorAssociationDetails()
{
    var tireType=document.getElementById("tireType").value;
    //alert(tireType)
    var status=document.getElementById("status").value;
    var RequirementId=$('#RequirementId').val();
    //alert(status)
    var url='Requirements/searchVendorAssociationDetails.action?tireType='+tireType+'&status='+status+'&RequirementId='+RequirementId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            
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
    //  alert(reqId)
    // e1
    $("e1").html("");
    document.getElementById("proofType").value="N";
    document.getElementById("ppno").value=" ";
    document.getElementById("pan").value=" ";
    document.getElementById("ratePerHour").value=" ";
    document.getElementById("conEmail").value=" ";
    document.getElementById("panId").style.display = 'none';
    document.getElementById("ppId").style.display = 'none';
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
    var id=document.getElementById('skillListValue').value;
    var topicId=document.getElementById('skillListValue');
    if($("div").is("#skillListValue"))
    {
        $("#skillListValue").remove();
        $(".skilllist").after('<select id="skillListValue" class="selectivity-result-item "   onclick="" multiple="multiple" tabindex="10" name="skillListValue "><option value="-1">Select skill</option></select>');
    }
    $("#actionMessage").html(""); 
    var conEmail=$("#conEmail").val();
    var reqId=$("#reqId").val();
    var resourceType=$("#resourceType").val();
    // alert(conEmail+" and...........>  "+reqId)
    // alert(resourceType)
    if(EmailValidation1(conEmail)){
        var url='../../recruitment/consultant/getConsultanceExistance.action?conEmail='+conEmail+'&resourceType='+resourceType+'&reqId='+reqId;
        //alert(url);
        var req=initRequest(url);
        req.onreadystatechange = function() {
            //  alert("success")
            if (req.readyState == 4 && req.status == 200) {
              
                var resultMsg=req.responseText;
                if(resultMsg!=""){
                    var Values=resultMsg.split("#");
                    // alert(Values[0])
                    //alert(Values[1])
                    if(Values[0]==3){
                        $("e1").html("");
                        $("e1").html(" <b><font color='red'>Already consultant exists.</font></b>");
                        document.getElementById("conEmail").value="";
                        $("#IsEmployee").css('visibility', 'hidden');
                        $("#skillField").css('visibility', 'hidden');
                    }
                    if(Values[0]==2){
                        $("e1").html("");
                        $("e1").html(" <b><font color='green'>email is valid.</font></b>");
                        $("#skillField").css('visibility', 'visible');
                    }
                    if(Values[0]==1){
                        //alert("sorry email doesnt exist")
                        $("e1").html("");
                        $("e1").html(" <b><font color='red'>sorry email doesnt exist.</font></b>");
                        document.getElementById("conEmail").value="";
                        $("#skillField").css('visibility', 'hidden');
                        $("#IsEmployee").css('visibility', 'hidden');
                    }
                    document.getElementById("resourceType").value=Values[1];
                    var val=  Values[2].replace(/[\[\]']+/g,'');
                    resourceVendorType();
                    setSkillForVendorEmployee(val,id);
                } else{
                    $("e1").html("");
                    $("e1").html(" <b><font color='red'>sorry email doesnt exist.</font></b>");
                    document.getElementById("conEmail").value="";
                    document.getElementById("resourceType").value="Val";
                    $("#skillField").css('visibility', 'hidden');
                    $("#IsEmployee").css('visibility', 'hidden');
                    resourceVendorType();
                }
            }
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }else{
        $("e1").html("");
        $("e1").html(" <b><font color='red'>sorry email not valid.</font></b>");
        $("#skillField").css('visibility', 'hidden');
        $("#IsEmployee").css('visibility', 'hidden');
    }
    return false;
}

function EmailValidation1(email){
   
    var status=email;
   
    re=/[^@]+@[^@]+\.[a-zA-Z]{2,}/;
    if(!re.test(status))
    {
        return false;
    }
    else
    {
        return true
    }
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
function addconsultantValidation(){
    var skillCatArry = [];    
    $("#skillListValue :selected").each(function(){
        skillCatArry.push($(this).val()); 
    });
    document.getElementById("propsedSkills").value=skillCatArry;
    $("#actionMessage").html(""); 
    // alert("called submit function here")
    //var resourceType=$("#resourceType").val();
    var conEmail=$("#conEmail").val();
    //var proofType=$("#proofType").val();//1=passport 2=pan
    var ratePerHour=$("#ratePerHour").val();
    //var ppno=$("#ppno").val();
    //var pan=$("#pan").val();
    //var reqId=$("#reqId").val();
    var file=$("#file").val();
    //var isHidden = $('#IsEmployee').is(':hidden');
    var isHidden=$('#IsEmployee').css('visibility') == 'hidden';
    //alert(isHidden);
    //alert(conEmail);
    //alert(ratePerHour);
    //alert(file);
    emailpattern=/[^@]+@[^@]+\.[a-zA-Z]{2,}/;
    ratepattern = /^[0-9]+$/;
    
    //alert("type:"+proofType+"  passport:"+ppno+"  pan:"+pan+"    reqId:"+reqId)
    if(conEmail=="")
    {
        // alert("no email");
        $("e1").html(" <b><font color='red'>Email Should not be empty</font></b>");
        
        return false;
    }
    if(ratePerHour=="")
    {
        // alert("no ratePerHour");
        $("e1").html(" <b><font color='red'>Please Enter Rate/Hr </font></b>");
        return false;
    }
    if(skillCatArry==""){
       
        $("e1").html(" <b><font color='red'>Please select skill</font></b>");
        //  $("#skillListValue").css("border", "1px solid red");
        // $("e1").show().delay(4000).fadeOut();
        return false;
    } else{
        $("e1").html("");
        $("#skillListValue").css("border", "1px solid green");
    }
    if(!emailpattern.test(conEmail))
    {
        // alert("no email");
        $("e1").html(" <b><font color='red'>Email Must be Valid</font></b>");
        
        return false;
    }
    if(!ratepattern.test(ratePerHour))
    {
        // alert("no ratePerHour");
        $("e1").html(" <b><font color='red'>Rate/Hr Must be valid</font></b>");
        return false;
    }
    if(file=="" && isHidden==false)
    {
        // alert("no ratePerHour");
        $("e1").html(" <b><font color='red'>Please upload the attachment</font></b>");
        return false;
    }
    if(file!='' && isHidden==false)
    {
        var allowed_extensions = new Array("pdf","doc","docx");
        var file_extension = file.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

        for(var i = 0; i < allowed_extensions.length; i++)
        {
            if(allowed_extensions[i]==file_extension)
            {
                return true; // valid file extension
            }
        }
        $("e1").html(" <b><font color='red'>The file uploaded is invalid type</font></b>");

        return false;
    }
    //    else
    //    { 
    //        // var url='Requirements/storeProofData.action?proofType='+proofType+'&ppno='+ppno+'&pan='+pan+'&reqId='+reqId+'&conEmail='+conEmail+'&ratePerHour='+ratePerHour;
    //        // alert(url);
    //        var req=initRequest(url);
    //        req.onreadystatechange = function() {
    //            if (req.readyState == 4 && req.status == 200) {
    //                if(req.responseText=="NotExist"){
    //                    $("e1").html(" <b><font color='red'>You dont have consultant account</font></b>");
    //                }else if(req.responseText=="updatesuccess"){
    //                    $("e1").html(" <b><font color='green'>Your Identification For Requirement Activated</font></b>");
    //                }else if(req.responseText=="lessthanoneEighty"){
    //                    $("e1").html(" <b><font color='green'>Already your Identification is in Activation</font></b>");
    //                }else if(req.responseText=="AddSuccess"){
    //                    $("e1").html(" <b><font color='green'>Your Identification for Requirement is Added</font></b>");
    //                }else{
    //                    $("e1").html(" <b><font color='green'>Error</font></b>");
    //                }
    //            } 
    //        };
    //        req.open("GET",url,"true");
    //        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    //        req.send(null);
    
    return true;
   
}
function setPPorPAN(proofType){
    //alert("HI im in PAN or PASSPORT")
    //alert(proofType)
    if(proofType=="PN"){
        document.getElementById("panId").style.display= '';
        document.getElementById("ppId").style.display= 'none';
        $("#panId").css('visibility', 'visible');
        $("#ppId").css('visibility', 'hidden');
    }
    if(proofType=="PP"){
        document.getElementById("panId").style.display= 'none';
        document.getElementById("ppId").style.display= '';
     
        $("#ppId").css('visibility', 'visible');
        $("#panId").css('visibility', 'hidden');
    }
    if(proofType=="N"){
        document.getElementById("panId").style.display= 'none';
        document.getElementById("ppId").style.display= 'none';
     
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
    var RequirementId=$('#RequirementId').val();
    var consult_name=$('#consult_name').val();
    var consult_email=$("#consult_email").val();
    var consult_skill=$('#consult_skill').val();
    var consult_phno=$('#consult_phno').val();
    var accountFlag=$('#accountFlag').val();
    var url='Requirements/getConsultantListBySearch?RequirementId='+RequirementId+'&consult_name='+consult_name+
    '&consult_email='+consult_email+'&consult_skill='+consult_skill+'&consult_phno='+consult_phno;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            consultantListGridDisplay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;     
    
}
function getConsultantList(){
    var RequirementId=$('#RequirementId').val();
    var url='Requirements/getConsultantList.action?RequirementId='+RequirementId;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            consultantListGridDisplay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;  
}
function consultantListGridDisplay(response){
    var requirementId=$("#RequirementId").val();
    var accountFlag=$('#accountFlag').val();
    if(response!=null)
        var eduList=response.split("^");
   
    var table = document.getElementById("consultantListTable");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }

    for(var i=0;i<eduList.length-1;i++){   
        var vendor="vendor";
        var Values=eduList[i].split("|");
        {  
         
         
            var row = $("<tr />")
          
            $("#consultantListTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($('<td><a href=../recruitment/consultant/getConsultantDetails.action?consult_id='+Values[0]+ '&consultFlag='+vendor+ ">" + Values[1] + "</a></td>"));
            row.append($("<td>" + Values[2] + "</td>"));
            row.append($("<td>" + Values[3] + "</td>"));
            row.append($("<td>" + Values[4] + "</td>"));
            row.append($("<td>" + Values[5] + "</td>"));
            row.append($("<td><figcaption><button type='button' value="+ Values[6] +" onclick=doAttachmentDownload("+ Values[6] +")><img src='./../includes/images/download.png' height='20' width='20' ></button></figcaption></td>"));
            if(accountFlag!='VendorReq'){
                row.append($('<td><a href=.././Requirements/techReview.action?requirementId='+requirementId+'&consult_id='+Values[0]+">Click</td>"));
            }
        }
    }
  
    pager.init(); 
    pager.showPageNav('pager', 'contactPageNavPosition'); 
    pager.showPage(1);
}
function doAttachmentDownload(acc_attachment_id)
{
    //alert(acc_attachment_id);
    //var path=document.getElementById(acc_attachment_id).value;
    //alert(path);
    window.location = '../recruitment/consultant/consultDownloadAttachment.action?acc_attachment_id='+acc_attachment_id;
}

function doReleaseRequirement(id,orgId,taxTerm){
    $("releaseMessage").html("");
    var requirementId=id;
    var url='Requirements/doReleaseRequirements.action?requirementId='+requirementId+'&orgId='+orgId+'&taxTerm='+taxTerm;
    //alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            if(req.responseText==0){
                $("releaseMessage").html(" <b><font color='red'>Requirement already Released</font></b>");
            }
            else{
                if(taxTerm=='CO'){
                    $("releaseMessage").html(" <b><font color='green'>Requirement release for Tier1 Vendors</font></b>");
                }else{
                    $("releaseMessage").html(" <b><font color='green'>Requirement release for Head Hunters Vendors</font></b>");
                }
            }
            getSearchRequirementsList();
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;     
}


//added by jagan
function showReqSkillOverlay(id)
{
    
    var url='getSkillOverlay.action?id='+id;
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert("result is::")
            // setVendorStates(req.responseText);
            setSkillOverlay(req.responseText)
        } 
    //alert(req.readyState +" and "+req.status)

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
function setSkillOverlay(response){
    var Values=response.split("|");
    document.getElementById("reqSkillDetails").value=Values[0];
//    document.getElementById("recruiterEmailIdOverlay").value=Values[1];
//    document.getElementById("recruiterPhoneOverlay").value=Values[2];

}


function showPreReqSkillOverlay(id)
{
    
    var url='getPreSkillOverlay.action?id='+id;
    
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert("result is::")
            // setVendorStates(req.responseText);
            setPreSkillOverlay(req.responseText)
        } 
    //alert(req.readyState +" and "+req.status)

    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);   
   
    //document.getElementById("preSkillDetails").value=response;
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
function setPreSkillOverlay(response){
    var Values=response.split("|");
    document.getElementById("preSkillDetails").value=Values[0];
//    document.getElementById("recruiterEmailIdOverlay").value=Values[1];
//    document.getElementById("recruiterPhoneOverlay").value=Values[2];

}
function clearConultantAddOverlay()
{
    $("e1").html(" "); 
    
}
function resourceVendorType(){
    var resourceType=document.getElementById("resourceType").value;
    if(resourceType=="VC"){
        $("#IsEmployee").css('visibility', 'visible');   
       
    }else{
        $("#IsEmployee").css('visibility', 'hidden');
    }
}
function ratePerHourValidation(){
    
    $("#actionMessage").html(""); 
    var rate=document.getElementById("ratePerHour").value;
    pattern = /^[0-9]+$/;
    if(rate=="" || rate==null){
        $("e1").html("<font color='red'>field is required</font>");
        $("#ratePerHour").css("border", "1px solid red");
        return false;
    }
    else if(!pattern.test(rate))
    {
        $("e1").html("<font color='red'>must be valid number");
        $("#ratePerHour").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#ratePerHour").css("border", "1px solid green");
        $("e1").html("");
       
    }
    return true;
}
function dateReqValidation(startDate,endDate,validatemessage)
{
    alert("helloDatesValidation")
    var splitTaskStartDate = startDate.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = endDate.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    if(difference<0)
    {
             
       
        $(validatemessage).html(" <b><font color='red'>Start date Must be less than End date</font></b>");
        //        $("#startDate").css("border", "1px solid red");
        //        $("#endDate").css("border", "1px solid red");
        $(validatemessage).show().delay(4000).fadeOut();
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
        return false;
    }
    
  
}
function enterDateRepository(id)
{
    $(id).val("");
    return false;
   
}

function setSkillForVendorEmployee(skillId,topicId){
    var ctr=0;
    var skillList=document.getElementById("tempSkillList").value;
    var skillValue=  skillList.replace(/[{()}]/g, '');
    var flagList=skillValue.split("FLAG")
    var totalSkills=flagList[0].split(",");
    var flag=skillId.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#skillListValue');
    $select.find('option').remove();   
    for (var i = 0; i < totalSkills.length; i++){ 
        var Values=totalSkills[i].split("=");
        for(var j=0;j<addList.length-1;j++){  
            var Values1=addList[j].split(",");
            // alert("Values[0]--->"+Values[0]+"Values1[0]---->"+Values1[0]);
            if (Number(Values[0]) == Number(Values1[0])) {
                ctr=1;
                break ;
            }
        }
        if(ctr==1){
            $('<option selected="true">').val(Number(Values[0])).text(Values[1]).appendTo($select); 
        }
        else{
            $('<option>').val(Number(Values[0])).text(Values[1]).appendTo($select);
        }
        ctr=0;
    }
    
   
    $('#skillListValue').selectivity({
            
        multiple: true,
        placeholder: 'Type to search Categories'
                   
    });
    $("#skillListValue").show();
    $("#IsEmployee").show();
    
    
}