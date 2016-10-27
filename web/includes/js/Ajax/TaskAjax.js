

function doOnLoad() {
    var editCalendar;
    editCalendar = new dhtmlXCalendarObject(["start_date","end_date"]);
    editCalendar.setSkin('omega');
    editCalendar.setDateFormat("%m-%d-%Y");
    editCalendar.hideTime();
     var today = new Date();
    
    editCalendar.setSensitiveRange(today,null);
    var task_related=document.getElementById("taskRelatedTo").value;
    if(task_related==2){
        $("#csrDiv").css('visibility', 'hidden');
    }
}
function changeTaskType(){
    $("#secondaryReport").val("");
    getTaskType();
//    var task_related=document.getElementById("taskRelatedTo").value;
//    if(task_related==1){
//         var secondaryAssign=document.getElementById("secondaryAssign").value;
////     alert(secondaryAssign)
//     $("#secondaryReport").val(secondaryAssign);
//    }
//    
}
function changeProject(){
    $("#secondaryReport").val("");
    getRelatedNames();
}
function clearProjects(){
    var $select = $('#taskType');
    $select.find('option').remove();   
    $("#csrDiv").css('visibility', 'hidden');
   document.getElementById("taskRelatedTo").value=2;
    getRelatedNames();
}
/* start, function creted by Aklakh for select task types */
function getTaskType(){
//    $("#secondaryReport").val("");
    clearTable();
    var task_related=document.getElementById("taskRelatedTo").value;
    //alert(task_related)
    if(task_related==1){
        $("#csrDiv").css('visibility', 'visible');
        var url='../general/getTask.action?task_related_to='+task_related;
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                setTaskTypes(req.responseText);
                getRelatedNames();
            } 
        
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    if(task_related==2){
        var $select = $('#taskType');
        $select.find('option').remove();   
        $("#csrDiv").css('visibility', 'hidden');
    }
    getRelatedNames();
}
function setTaskTypes(responseTypes){
    var taskType = document.getElementById("taskType");
    var flag=responseTypes.split("FLAG");
    var addTypes=flag[0].split("^");
    var $select = $('#taskType');
    $select.find('option').remove();   
    for(var i=0;i<addTypes.length-1;i++){        
        var Values=addTypes[i].split("#");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
     var proVal=document.getElementById("addTaskPage").value;
    if(proVal==1)
        {
          document.getElementById("taskType").value=document.getElementById("taskProject").value;   
        }
}


function getRelatedNames(){
    var taskType=document.getElementById("taskType").value;
    var task_related_to=document.getElementById("taskRelatedTo").value;
    //alert(task_related_to)
    var url='../general/getRelatedNames.action?taskType='+taskType+'&task_related_to='+task_related_to;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            setPrimaryAssigned(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function setPrimaryAssigned(responseTypes){
    var taskType = document.getElementById("primary_assign");
    var flag=responseTypes.split("FLAG");
    var addTypes=flag[0].split("^");
    var $select = $('#primary_assign');
    $select.find('option').remove();   
    for(var i=0;i<addTypes.length-1;i++){        
        var Values=addTypes[i].split("#");
        {
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
    document.getElementById("primary_assign").value=document.getElementById("primaryId").value;
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
/* end, function creted by Aklakh for select task types */




function doDeactiveAttachment(id)
{
    var taskid=document.getElementById("taskid").value;
    var url='../general/doDeactiveAttachment.action?taskAttachId='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            window.location.reload(true);           
        //location.reload();
        } 
        
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null); 
}
function doDownload(id)
{
    $("#resume").html("");
    var task_id=$("#taskid").val();
    var path=document.getElementById(id).value;
    var myTask = document.getElementById("myTask").value;
    window.location = '../general/downloadAttachment.action?attachmentId='+id+'&taskid='+task_id+'&myTask='+myTask;
}


function attachementPopUp(){

    $("#upaloadMessages").html("");
    
    specialBox = document.getElementById('taskAttachOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#taskAttachments_popup').popup( );
}

//function doUpdateTaskInfo()
//{
//   
//    var taskRelatedTo=document.getElementById("taskRelatedTo").value;
//
//    var taskStatus=document.getElementById("task_status").value;
//
//    var startDate=document.getElementById("start_date").value;
//
//    var taskid=document.getElementById("taskid").value;
//
//    var taskType=document.getElementById("taskType").value;
//
//    var priority=document.getElementById("task-label").value;
//
//    var endDate=document.getElementById("end_date").value;
//
//    var taskName=document.getElementById("task-textform").value;
//
//    var task_comments=document.getElementById("task_comments").value;
//    
//    var pri_assign_to=document.getElementById("primary_assign").value;
//    
//    var sec_assign_to=document.getElementById("sec_pri_id").value;
//
//    var url='../tasks/doupdateTaskDetails.action?taskRelatedTo='+taskRelatedTo+'&taskStatus='+taskStatus+
//    '&startDate='+startDate+
//    '&taskid='+taskid+
//    '&priority='+priority+
//    '&taskType='+taskType+
//    '&endDate='+endDate+
//    '&taskName='+taskName+
//    '&task_comments='+task_comments+
//    '&pri_assign_to='+pri_assign_to+'&sec_assign_to='+sec_assign_to;
//    var req=initRequest(url);
//    req.onreadystatechange = function() {
//        if (req.readyState == 4 && req.status == 200) {
//            
//            $("UpdateTaskInfo").html(" <b><font color='green'>Task Information updated Successfully.</font></b>");
//        } 
//    };
//    req.open("GET",url,"true");
//    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//    req.send(null);
//    return false;
//}
function doUpdateTaskInfo()
{
    initSessionTimer();
    var taskRelatedTo=document.getElementById("taskRelatedTo").value;
    var taskStatus=document.getElementById("task_status").value;
    var startDate=document.getElementById("start_date").value;

    var taskid=document.getElementById("taskid").value;

    var taskType=document.getElementById("taskType").value;

    var priority=document.getElementById("taskPriority").value;

    var endDate=document.getElementById("end_date").value;

    var taskName=document.getElementById("task-textform").value;
    var task_comments=document.getElementById("task_comments").value;

    var pri_assign_to=document.getElementById("primary_assign").value;
    //alert(pri_assign_to)
    if(pri_assign_to=="")
    {
        $("UpdateTaskInfo").html(" <font color='red'>Plase select Primary assign</font>");
        return false;
    }

    var sec_assign_to=document.getElementById("secondaryId").value;
    if(editTaskValidation()){
        var url='../tasks/doupdateTaskDetails.action?taskRelatedTo='+taskRelatedTo+'&taskStatus='+taskStatus+
        '&startDate='+startDate+
        '&taskid='+taskid+
        '&priority='+priority+
        '&taskType='+taskType+
        '&endDate='+endDate+
        '&taskName='+taskName+
        '&task_comments='+task_comments+
        '&pri_assign_to='+pri_assign_to+'&sec_assign_to='+sec_assign_to;
        //alert(url);
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
            
                $("UpdateTaskInfo").html(" <font color='green'>Task Information updated Successfully.</font>");
                $("UpdateTaskInfo").show().delay(5000).fadeOut();
            } 
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}

function doGetNotesDetails(){
    initSessionTimer();
    var taskid=document.getElementById("taskid").value;
    var randomNumber = Math.random();
    var url='../tasks/getNotesDetails.action?taskid='+taskid+'&randomNumber='+randomNumber;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingTask').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            $('#loadingTask').hide();
            populateNotes(req.responseText)
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}
function populateNotes(response){
    var eduList=response.split("^");
    $(".page_option").css('display', 'block'); 
   
    var RecordsData ='<table id="taskpagenav" class="CSSTable_task responsive" border="5" cell-spacing="2"><tr><th>Notes Name</th><th>Notes Comments</th><th >Created Date</th></tr>';
    if(eduList.length-1==0){
        RecordsData = RecordsData+'<td colspan="3"><font style="color: red;font-size: 15px;">No Records to display</font></td>';
        $(".page_option").css('display', 'none');
    }else{
        for(var i=0;i<eduList.length-1;i++){        
            var Values=eduList[i].split("|");
            {  
                                                         
            
                RecordsData = RecordsData+'<tr><td><a href="" class="notes_popup_open" onclick=" showNotesOverlayDetails('+Values[0]+','+Values[1]+');" >'+Values[2]+'</td></a>'+
                '<td>'+Values[3]+'</td>'+  
                '<td>'+Values[4]+'</td></tr>'
                              
            }
        }
    }
    
    RecordsData=RecordsData+ ' </table> <div id="pageNavPosition" align="right" style="margin-right: 0vw;display:none;"/>';    
  
    task_notes_populate.innerHTML=RecordsData; 
    $('#taskpagenav').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    tpager.init();  
  
    
}

function showNotesOverlayDetails(id,taskid){
    var taskid=taskid;
    var id=id;
    var url='../tasks/doGetNotesDetailsOverlay.action?taskid='+taskid+'&id='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            populateNotesOverlay(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);


}
function populateNotesOverlay(response){
    document.getElementById("id").value="";
    document.getElementById("taskidoverlay").value="";
    document.getElementById("noteNames").value="";
    document.getElementById("noteComments").value="";
    var Values=response.split("|");
    
    document.getElementById("id").value=Values[0];
    document.getElementById("taskidoverlay").value=Values[1];
    document.getElementById("noteNames").value=Values[2];
    document.getElementById("noteComments").value=Values[3];

}

function updateNoteDetails(){
    var id=document.getElementById("id").value;
    var taskid=document.getElementById("taskidoverlay").value;
    var note_name=document.getElementById("noteNames").value;
    var note_comments=document.getElementById("noteComments").value;
    if(editNotesValidation()){  
        var url='../tasks/doUpdateNotesDetails.action?taskid='+taskid+'&id='+id+'&note_name='+note_name+'&note_comments='+note_comments;
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                //alert("success")
                $("UpdateNoteInfo").html(" <font color='green'> &nbsp&nbspNotes Information updated Successfully.</font>");
                $("UpdateNoteInfo").show().delay(5000).fadeOut();
                getNotesDetailsBySearch();
            } 
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}

function addNotesDetails(){
   
    document.getElementById("addNoteButton").disabled = true;
    var taskid=document.getElementById("taskid").value;
    var note_name=document.getElementById("noteNamesadd").value;
    var note_comments=document.getElementById("noteCommentsadd").value;
    if(addNotesValidation()){
        var url='../tasks/DoInsertNotesDetails.action?taskid='+taskid+'&note_name='+note_name+'&note_comments='+note_comments;
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                $("InsertNoteInfo").html(" <font color='green'> &nbsp&nbspNotes Information Added Successfully.</font>");
                $("InsertNoteInfo").show().delay(5000).fadeOut();
                document.getElementById("noteNamesadd").value ="";
                document.getElementById("noteCommentsadd").value="";
                document.getElementById("addNoteButton").disabled = false;
                doGetNotesDetails()
              
            } 
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
   
}
$(document).ready(function() {

    specialBox = document.getElementById('AddNoteOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#Note_popup').popup( );
});


$(document).ready(function() {

    specialBox = document.getElementById('NotesOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    $('#notes_popup').popup( );
});




function getNotesDetailsBySearch()
{
    initSessionTimer();
    var taskid=document.getElementById("taskid").value;
    var notes_id=document.getElementById("notes_id").value;
    var notesName=document.getElementById("notes_NameSearch").value;
    var randomNo = Math.random();
    //    if(notesName=="" || notesName==null){
    //        $("notesErrorMsg").html(" <b><font color='red'>notesName is Required</font></b>.");
    //        $("#notes_NameSearch").css("border", "1px solid red");
    //       
    //        return false;
    //    }
    //    else if(notes_id=="" || notes_id==null){
    //        $("notesErrorMsg").html(" <b><font color='red'>notesid is Required</font></b>.");
    //        $("#notes_id").css("border", "1px solid red");
    //        return false;
    //    }
    //    else
    //    {
    var url='../tasks/getNotesDetailsBySearch.action?taskid='+taskid+'&notesName='+notesName+'&randomNo='+randomNo;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        document.getElementById('loadingTask').style.display = 'block';
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText)
            $('#loadingTask').hide();
          
            populateNotes(req.responseText);          
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
    
    return false;
//    }
}

function clearResultMsg()
{
    $("notesErrorMsg").html(""); 
    $("#notes_id").css("border", "1px solid #3BB9FF");
    $("#notes_NameSearch").css("border", "1px solid #3BB9FF");
}
function removeResultMsg(){
    $("InsertNoteInfo").html("");
    $("UpdateNoteInfo").html("");
    $("#noteNames").css("border","1px solid #3BB9FF");
    $("#noteNamesadd").css("border","1px solid #3BB9FF");
    $("#noteCommentsadd").css("border","1px solid #3BB9FF");
    $("#noteComments").css("border","1px solid #3BB9FF");
}
function editTaskValidation(){
    var startDate=document.getElementById("start_date").value;
    var endDate=document.getElementById("end_date").value;
    var taskName=document.getElementById("task-textform").value;
    var editTaskValidation=document.getElementById("editTaskValidate");
    if(startDate==""){
        $("editTask").html("<font color='red'> enter start date</font>");
        $("#start_date").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#start_date").css("border","1px solid #3BB9FF")
    }
    if(endDate==""){
        $("editTask").html("<font color='red'> enter end date</font>");
        $("#end_date").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#end_date").css("border","1px solid #3BB9FF")
    }
    if(taskName==""){
        $("editTask").html("<font color='red'> enter title</font>");
        $("#task-textform").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#task-textform").css("border","1px solid #3BB9FF")
    }
    return dateValidation(startDate,endDate,editTaskValidation);
    return true;
}
function addTaskValidation(){
    var startDate=document.getElementById("startDate").value;
    var endDate=document.getElementById("endDate").value;
    var taskName=document.getElementById("task_name").value;
    var editTask=document.getElementById("editTaskValidation");
    var primaryAssign =document.getElementById("primary_assign").value;
    if(startDate=="" || startDate==" "){
        $("editTask").html("<font color='red'> Enter start date</font>");
        $("#startDate").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#startDate").css("border","1px solid #3BB9FF")
    }
    if(endDate=="" || endDate==" "){
        $("editTask").html("<font color='red'> Ennter end date</font>");
        $("#endDate").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#endDate").css("border","1px solid #3BB9FF")
    }
     var splitTaskStartDate = startDate.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = endDate.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    if(difference<0)
    {
             
        // alert("hi")
        $("editTask").html("<font color='red'>Start date Must be less than End date</font>");
        //        $("#startDate").css("border", "1px solid red");
        //        $("#endDate").css("border", "1px solid red");
      
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
        return false;
    }  
    if(taskName=="" || taskName==" "){
        $("editTask").html("<font color='red'> Enter title</font>");
        $("#task_name").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#task_name").css("border","1px solid #3BB9FF")
    }
    if(primaryAssign=="" || primaryAssign==" "){
        $("editTask").html("<font color='red'> Select Primary Assign To</font>");
        $("#primary_assign").css("border","1px solid red");
        return false;
    }
    else{
        $("editTask").html("");
        $("#primary_assign").css("border","1px solid #3BB9FF")
    }
    var FileUploadPath = document.getElementById('taskAttachment').value;

               
      
    //To check if user upload any file
    if (FileUploadPath != '') {
       
        // $("editTask").html("<font color='red'>Please upload a file</font>");
        //        return false;
        //    } else {
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
            
        if (Extension == "pdf" || Extension == "doc" || Extension == "docx" ) {
            var fileSize = document.getElementById("taskAttachment").files[0];
            var sizeInMb = (fileSize.size/1024)/1024;
            //alert(sizeInMb)
            var sizeLimit= 2;
            //alert(sizeInMb)
            if(FileUploadPath.length>50)
            {
                                                                                                    
                $("editTask").html("<font color=red>File name should not more than 30 characters</font>");
                //alert("please select the file length");   
                return false;
            }
            
            if (sizeInMb > sizeLimit) {
                //alert("more size")
                $("#taskAttachment").val("");
                 $("editTask").html("<font color=red>File size must be less than 2MB.</font>"); 
                // $("#formValidationMsg").html("<font color='red'>File size must be less than 2MB.</font>");
                return false;
            }
            else{
           
                $("editTask").html(" ");
                return true;
            }
        } 
        else {
            $("#taskAttachment").val("");
            $("editTask").html("<font color='red'> Allows only doc ,docx or pdf type.</font>");
            return false;
        }
        
        
    }
   
    return true;

}
function editNotesValidation(){
    var note_name=document.getElementById("noteNames").value;
    var note_comments=document.getElementById("noteComments").value;
    if(note_name==""||note_name==null){
        $("UpdateNoteInfo").html("<font color='red'> enter name</font>");
        $("#noteNames").css("border","1px solid red");
        return false;
    }
    else{
        $("UpdateNoteInfo").html("");
        $("#noteNames").css("border","1px solid #3BB9FF");
        
    }
    if(note_comments==""||note_comments==null){
        $("UpdateNoteInfo").html("<font color='red'> enter comments</font>");
        $("#noteComments").css("border","1px solid red");
        return false;
    }
    else{
        $("UpdateNoteInfo").html("");
        $("#noteComments").css("border","1px solid #3BB9FF");
        
    }
    return true;
}
function addNotesValidation(){
    var note_name=document.getElementById("noteNamesadd").value;
    var note_comments=document.getElementById("noteCommentsadd").value;
    if(note_name==""||note_name==null){
        $("InsertNoteInfo").html("<font color='red'>Please, Enter Notes Name</font>");
        // $("#noteNamesadd").css("border","1px solid red");
        document.getElementById("addNoteButton").disabled = false;
        $("InsertNoteInfo").show().delay(4000).fadeOut();
        return false;
        
    }
    else{
        $("InsertNoteInfo").html("");
        $("#noteNamesadd").css("border","1px solid #3BB9FF");
        
    }
    if(note_comments==""||note_comments==null){
        $("InsertNoteInfo").html("<font color='red'>Please, Enter Description</font>");
        // $("#noteCommentsadd").css("border","1px solid red");
        document.getElementById("addNoteButton").disabled = false;
        $("InsertNoteInfo").show().delay(4000).fadeOut();
        return false;
    }
    else{
        $("InsertNoteInfo").html("");
        $("#noteNamesadd").css("border","1px solid #3BB9FF");
        $("#noteCommentsadd").css("border","1px solid #3BB9FF");
        
    }
    return true;
}
////////////////////////////////new methods start from here 07282015//////////////////////////////////////
function taskCommentsPopup(comments){
    //alert(addEditFlag)
    $("#commentsArea").val(comments);
    var specialBox = document.getElementById("preskillBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    
    
    } else {
        specialBox.style.display = "block";      
    
    
    }
    // Initialize the plugin    
    
    $('#taskComments_popup').popup(      
        ); 
}
function taskCommentsDetailsToViewOnOverlay(taskid){
    //alert("HI "+homeId)
    var url='../tasks/getCommentsOnOverlay.action?taskid='+taskid;
    //    alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            $("#commentsArea").val(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function checkDateRange() {
              
                
    var fromValue=document.getElementById("docdatepickerfrom").value;
    //alert(fromValue)
    var toValue=document.getElementById("docdatepicker").value;
    var taskSearchValidation=document.getElementById("taskSearchValidation");
    //alert(taskSearchValidation)
    if(fromValue!=""){
        if(toValue==""){
            $("#taskSearchValidation").html(" <font color='red'>Please select end date !</font>");
            return false;
        }
    }
    if(toValue!=""){
        if(fromValue==""){
            $("#taskSearchValidation").html(" <font color='red'>Please select start date !</font>");
            return false;
        }
    }

    
    return dateValidation(fromValue,toValue,taskSearchValidation);
    return true;      
}; 
function dateValidation(startDate,endDate,validatemessage)
{
    //alert("helloAA")
    var splitTaskStartDate = startDate.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = endDate.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    if(difference<0)
    {
             
        // alert("hi")
        $(validatemessage).html(" <font color='red'>Start date Must be less than End date</font>");
        //        $("#startDate").css("border", "1px solid red");
        //        $("#endDate").css("border", "1px solid red");
        $(validatemessage).show().delay(4000).fadeOut();
        //         $("#startDate").show().delay(5000).fadeOut();
        //          $("#endDate").show().delay(5000).fadeOut();
        return false;
    }  
    return true;
}
function enterTaskDateRepository(id)
{
    $(id).val(" ");
    return false;
    
}

function checkTaskDescription(id){
    //alert("hello")
    var elem = document.getElementById("description_feedback");
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 500){
            el.val( el.val().substr(0, 500) );
        } else {
            elem.style.color="green";
            $("#description_feedback").text(500-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==500)
        {
            elem.style.color="red";
            $("#description_feedback").text(' Cannot enter  more than 500 Characters .');
        }

    })
    return false;
}

function checkEditTaskDescription(id){
    // alert("hello")
    var elem = document.getElementById("description_feedback");
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 500){
            el.val( el.val().substr(0, 500) );
        } else {
            elem.style.color="green";
            $("#description_feedback").text(500-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==500)
        {
            elem.style.color="red";
            $("#description_feedback").text(' Cannot enter  more than 500 Characters .');
        }

    })
    return false;
}
function clearDescriptionSpan(){
    $("#description_feedback").text('');
}
function clearNotesFields(){
    document.getElementById("noteNamesadd").value="";
    document.getElementById("noteCommentsadd").value="";
}

function attachmentUploadValidation(){
    var FileUploadPath = document.getElementById('taskAttachment').value;

               
      
    //To check if user upload any file
    if (FileUploadPath == '') {
       
        $("#upaloadMessages").html("<font color='red'>Please upload a file</font>");
        return false;
    } else {
         var leafname= FileUploadPath.split('\\').pop().split('/').pop();
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
            
        if (Extension == "pdf" || Extension == "doc" || Extension == "docx" ) {
            var fileSize = document.getElementById("taskAttachment").files[0];
            var sizeInMb = (fileSize.size/1024)/1024;
            //alert(sizeInMb)
            var sizeLimit= 2;
            //alert(sizeInMb)
            if(leafname.length>50)
            {
                                                                                                    
                document.getElementById('upaloadMessages').innerHTML = "<font color=red>file name should not maore than 30 characters</font>";  
                //alert("please select the file length");   
                return false;
            }
            
            if (sizeInMb > sizeLimit) {
                //alert("more size")
                $("#taskAttachment").val("");
                document.getElementById('upaloadMessages').innerHTML = "<font color=red>File size must be less than 2MB.</font>";  
                // $("#formValidationMsg").html("<font color='red'>File size must be less than 2MB.</font>");
                return false;
            }
            else{
           
                $("#upaloadMessages").html(" ");
                return true;
            }
        } 
        else {
            $("#taskAttachment").val("");
            $("#upaloadMessages").html("<font color='red'> Allows only doc ,docx or pdf type.</font>");
            return false;
        }
        
        
    }
}