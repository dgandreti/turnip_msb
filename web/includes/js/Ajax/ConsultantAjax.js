// Add by Aklakh to validate the email Id
function ConsultEmailValidation(){
   
    var status=document.getElementById("consult_email").value;
   
    re=/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    //  /[^@]+@[^@]+\.[a-zA-Z]{2,}/;
    if(status==''){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Email field is Required.</font></b>.");
       
        $("#consult_email").css("border", "1px solid red"); 
    }
    else if(!re.test(status)){
       
        $("addCnsltError").html(" <b><font color=#FF4D4D>Please enter valid E-mail.</font></b>.");
        $("#consult_email").css("border", "1px solid red");
    }
    else
    {
       
        $("addCnsltError").html("");
        $("#consult_email").css("border", "1px solid green");
        CnsltEmailValidate();
    }
}


function CnsltEmailValidate(){
    // alert("in Ajax call ");
    var consultantId=document.getElementById("consult_email").value;
    var url=CONTENXT_PATH+'/recruitment/consultant/consultantEmailVerify.action?consultantId='+consultantId;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                //                alert(req.responseText);
                //  alert("2");
                resultEmail(req.responseText);
               
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

function resultEmail(response){
    if(response=="success"){
        $("addCnsltError").html("  <b><font color='green'>Email Id is available !</font></b>");
        $("#consult_email").css("border", "1px solid green");
        return true;
    }
    else{
        $("addCnsltError").html(" <b><font color=#B20000>Email Id is already exist !</font></b>");
        $("#consult_email").css("border", "1px solid red");
        consult_email.value='';
        return false;
    }
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


//Add by Aklakh for date picker
var cnsltDate,cnsltDOB;
function doOnLoadcnslt() {
    cnsltDate = new dhtmlXCalendarObject(["consult_add_date"]);
    cnsltDate.setSkin('omega');
    cnsltDate.setDateFormat("%m-%d-%Y");
    cnsltDate.hideTime();
    
    cnsltDOB = new dhtmlXCalendarObject(["consult_dob"]);
    cnsltDOB.setSkin('omega');
    cnsltDOB.setDateFormat("%m-%d-%Y");
    cnsltDOB.hideTime();
}

function enterDateRestrict()
{
   
    document.getElementById('consult_add_date').value = "";
    document.getElementById('consult_dob').value = "";
    
    // alert("Please select from the Calender !");
    return false;
}

// function to get the state of in addConsultant page add by Aklakh
function ConsultantStateChange()
{
    // alert("Consultant ajax");
    var id=document.getElementById('consult_pcountry').value;
    if(id==-1){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Preffered country field is Required</font></b>.");
        $("#consult_pcountry").css("border", "1px solid red");
    }
    else{
        $("#consult_pcountry").css("border", "1px solid green");
        $("addCnsltError").html("");
    }
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            ConsultantStateChanging(req.responseText);
          
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
// function to set the state of in addConsultant page add by Aklakh
function ConsultantStateChanging(data){
    //alert(data);
    var topicId = document.getElementById("consult_preferredState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_preferredState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}

// function to get the state of permanent address in addConsultant page add by Aklakh
function PermanentStateChange()
{
    // alert("Consultant ajax");
    var id=document.getElementById('consult_Country').value;
    if(id==""){
        //alert("hi");
        $("addCnsltError").html(" <b><font color=#FF4D4D>country field is Required</font></b>.");
        $("#consult_Country").css("border", "1px solid red");
    }
    else{
        //alert("hello");
        //$("#consult_Country").css("border", "1px solid green");
        $("addCnsltError").html("");
    }
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            PermanentState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function StateChange()
{
    //alert("Consultant ajax");
    var id=document.getElementById('consult_Country').value;
    //    if(id==""){
    //        //alert("hi");
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>country field is Required</font></b>.");
    //        $("#consult_Country").css("border", "1px solid red");
    //    }
    //    else{
    //        //alert("hello");
    //        $("#consult_Country").css("border", "1px solid green");
    //        $("addCnsltError").html("");
    //    }
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //alert(req.responseText)
            PermanentState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
// function to set the state of permanent address in addConsultant page add by Aklakh
function PermanentState(data){
    //alert(data);
    var topicId = document.getElementById("consult_State");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_State');
    $select.find('option').remove();  
    $('<option>').val(-1).text("Select state").appendTo($select);
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
    //var consultState=$("#consultState").val();
    $("#consult_State").val(consultState);
}
// function to get the state of current address in addConsultant page add by Aklakh
function CurrentStateChange()
{
    // alert("Consultant ajax");
    var id=document.getElementById('consult_CCountry').value;
    if(id==""){
        //alert("hi");
        $("addCnsltError").html(" <b><font color=#FF4D4D>country field is Required</font></b>.");
        $("#consult_CCountry").css("border", "1px solid red");
    }
    else{
        //alert("hello");
        // $("#consult_CCountry").css("border", "1px solid green");
        $("addCnsltError").html("");
    }
    //  alert(id);
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            //  alert(req.responseText);
           
            CurrentState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
// function to set the state of current address in addConsultant page add by Aklakh
function CurrentState(data){
    //alert(data);
    var topicId = document.getElementById("consult_CState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_CState');
    $select.find('option').remove();   
    $('<option>').val(-1).text("Select state").appendTo($select);
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}



/* function for add consultant validation , by aklakh */
function addConsultantValidate(){
    var FileUploadPath = document.getElementById('file').value;

    //To check if user upload any file
    
    var val_consult_email= $("#consult_email").val();
    // var val_consult_add_date=$("#consult_add_date").val();
    //var val_consult_available=$("#consult_available").val();
    var val_consult_fstname=$("#consult_fstname").val();
    var val_consult_lstname=$("#consult_lstname").val(); 
    // var genderM=document.getElementsByName("consult_gender")[0].checked;   
    // var genderF=document.getElementsByName("consult_gender")[1].checked;
    
    var val_consult_dob=$("#consult_dob").val();
    // var maritalStatusS=document.getElementsByName("consult_mStatus")[0].checked;   
    // var MaritalStatusM=document.getElementsByName("consult_mStatus")[1].checked;
    var val_consult_mobileNo=$("#consult_mobileNo").val(); 
    // var val_consult_lcountry=$("#consult_lcountry").val(); 
    //var val_consult_alterEmail=$("#consult_alterEmail").val(); 
    // var val_consult_ssnNo=$("#consult_ssnNo").val(); 
    var val_consult_Address=$("#consult_Address").val();
    var val_consult_City=$("#consult_City").val();
    var val_consult_Country=$("#consult_Country").val();
    var val_consult_State=$("#consult_State").val();
    // var val_consult_Zip=$("#consult_Zip").val();
    // var val_consult_Phone =$("#consult_Phone").val(); 
    var val_consult_CAddress=$("#consult_CAddress").val(); 
    var val_consult_CCity=$("#consult_CCity").val();
    var val_consult_CCountry=$("#consult_CCountry").val();
    var val_consult_CState=$("#consult_CState").val();
    // var val_consult_CZip=$("#consult_CZip").val();
    //var val_consult_CPhone =$("#consult_CPhone").val(); 
    var val_consult_industry=$("#consult_industry").val();
    //var val_consult_salary=$("#consult_salary").val();
    var val_consult_wcountry=$("#consult_wcountry").val();   
    var val_consult_organization=$("#consult_organization").val();
    var val_consult_experience=$("#consult_experience").val();
    var val_consult_preferredState=$("#consult_preferredState").val();  
    var val_consult_jobTitle=$("#consult_jobTitle").val();
    var skill=$("#consult_skill").val();
    var consultDOB = val_consult_dob.split('-');
    var consultDOBDate = new Date(consultDOB[2], consultDOB[0]-1 , consultDOB[1]);
    var todayDate= new Date();
    var dobYeardiff= (todayDate-consultDOBDate)/ (86400000 * 360);
    var userCatArry = []; 
    var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).val()); 
    });
    // alert("skillCategoryArry"+skillCategoryArry);
    if(skillCategoryArry==""){
       
        $("addCnsltError").html(" <b><font color='red'>Skill field is required</font></b>");
        $("#skillCategoryValue").css("border", "1px solid red");
        return false;
    } else
{
        $("addCnsltError").html("");
        $("#skillCategoryValue").css("border", "1px solid green");
    }
    if($("#consult_preferredState :selected").length>5)
    {
            
        $("addCnsltError").html(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
        //  $("#consult_preferredState").val(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
        $("#consult_preferredState").css("border","1px solid red");
           
        return false;
    }
    else{
        $("#consult_preferredState :selected").each(function(){
            userCatArry.push($(this).val()); 
        });
        $("#PrefstateValues").val(userCatArry);
    }
    
    // var val_consult_workPhone=$("#consult_workPhone").val();
    //var val_consult_pcountry=$("#consult_pcountry").val();    
    pat =/[^\s]+[a-zA-Z ]*[^\s]+/;    
    if(val_consult_email=="" || val_consult_email==null){
        $("addCnsltError").html(" <b><font color= #FF4D4D>Email field is Required</font></b>.");
        $("#val_consult_email").css("border", "1px solid red");
        $("#consult_email").css("border","1px solid red");
        return false;
    }
    if(val_consult_fstname==""||val_consult_fstname==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>First name field is Required</font></b>.");
        $("#val_consult_fstname").css("border", "1px solid red");
        $("#consult_fstname").css("border","1px solid red");
        return false;
    }
    if(!pat.test(val_consult_fstname)){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid Name</font></b>.");
        $("#val_consult_fstname").css("border", "1px solid red");
        $("#consult_fstname").css("border","1px solid red");
        return false;
    }    
    if(val_consult_lstname=="" || val_consult_lstname==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Last name field is Required</font></b>.");
        $("#val_consult_lstname").css("border", "1px solid red");
        $("#consult_lstname").css("border","1px solid red");
        return false;
    }
    if(!pat.test(val_consult_lstname)){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid last name</font></b>.");
        $("#val_consult_lstname").css("border", "1px solid red");
        $("#consult_lstname").css("border","1px solid red");
        return false;
    }    

    if(val_consult_dob=="" || val_consult_dob==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>DOB field is Required</font></b>.");
        $("#val_consult_dob").css("border", "1px solid red");
        $("#consult_dob").css("border","1px solid red");
        return false;
    }
    if(dobYeardiff < 20){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Consultant must be atleast 20 years old.</font></b>.");
        $("#val_consult_dob").css("border", "1px solid red");
        $("#consult_dob").css("border","1px solid red");
        return false;
    }
    //    if(maritalStatusS==false && MaritalStatusM==false){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Please select marital status</font></b>.");
    //        $("#maritalStatusS").css("border", "1px solid red");
    //        return false;
    //    }
    if(val_consult_mobileNo=="" || val_consult_mobileNo==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Mobile number field is Required</font></b>.");
        $("#val_consult_mobileNo").css("border", "1px solid red");
        $("#consult_mobileNo").css("border","1px solid red");
        return false;
    }
    //    if(val_consult_lcountry=="" || val_consult_lcountry==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Living country field is Required</font></b>.");
    //        $("#val_consult_lcountry").css("border", "1px solid red");
    //        $("#consult_lcountry").css("border","1px solid red");
    //        return false;
    //    }
    ////     if(val_consult_alterEmail=="" || val_consult_alterEmail==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Living country field is Required</font></b>.");
    //        $("#val_consult_alterEmail").css("border", "1px solid red");
    //        $("#consult_alterEmail").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_ssnNo=="" || val_consult_ssnNo==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Living country field is Required</font></b>.");
    //        $("#val_consult_ssnNo").css("border", "1px solid red");
    //        $("#consult_ssnNo").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_Address=="" || val_consult_Address==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Permanent address  field is Required</font></b>.");
    //        $("#val_consult_Address").css("border", "1px solid red");
    //        $("#consult_Address").css("border","1px solid red");
    //        return false;
    //    }
    //    if(!pat.test(val_consult_Address)){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid address in permanent address</font></b>.");
    //        $("#val_consult_Address").css("border", "1px solid red");
    //        $("#consult_Address").css("border","1px solid red");
    //        return false;
    //    }
    if(val_consult_City=="" || val_consult_City==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Permanent address city field is Required</font></b>.");
        $("#val_consult_City").css("border", "1px solid red");
        $("#consult_City").css("border","1px solid red");
        return false;
    }
    if(!pat.test(val_consult_City)){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid city name in permanent address</font></b>.");
        $("#val_consult_City").css("border", "1px solid red");
        $("#consult_City").css("border","1px solid red");
        return false;
    }
    if(val_consult_Country==-1 ){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Permanent address country field is Required</font></b>.");
        $("#val_consult_Country").css("border", "1px solid red");
        $("#consult_Country").css("border","1px solid red");
        return false;
    }
    if(val_consult_State==-1){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Permanent address state field is Required</font></b>.");
        $("#val_consult_State").css("border", "1px solid red");
        $("#consult_State").css("border","1px solid red");
        return false;
    }
    //    if(val_consult_Zip=="" || val_consult_Zip==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Permanent address zip field is Required</font></b>.");
    //        $("#val_consult_Zip").css("border", "1px solid red");
    //        $("#consult_Zip").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_Phone=="" || val_consult_Phone==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Permanent address phone field is Required</font></b>.");
    //        $("#val_consult_Phone").css("border", "1px solid red");
    //        $("#consult_Phone").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_CAddress=="" || val_consult_CAddress==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Current address  field is Required</font></b>.");
    //        $("#val_consult_CAddress").css("border", "1px solid red");
    //        $("#consult_CAddress").css("border","1px solid red");
    //        return false;
    //    }
    //    if(!pat.test(val_consult_CAddress)){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid address in current address</font></b>.");
    //        $("#val_consult_CAddress").css("border", "1px solid red");
    //        $("#consult_CAddress").css("border","1px solid red");
    //        return false;
    //    }
    if(val_consult_CCity=="" || val_consult_CCity==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Current city field is Required</font></b>.");
        $("#val_consult_CCity").css("border", "1px solid red");
        $("#consult_CCity").css("border","1px solid red");
        return false;
    }
    if(!pat.test(val_consult_CCity)){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid city name in current address</font></b>.");
        $("#val_consult_CCity").css("border", "1px solid red");
        $("#consult_CCity").css("border","1px solid red");
        return false;
    }
    if(val_consult_CCountry==-1){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Current address country field is Required</font></b>.");
        $("#val_consult_CCountry").css("border", "1px solid red");
        $("#consult_CCountry").css("border","1px solid red");
        return false;
    }
    if(val_consult_CState==-1){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Current address state field is Required</font></b>.");
        $("#val_consult_CState").css("border", "1px solid red");
        $("#consult_CState").css("border","1px solid red");
        return false;
    }
    //    if(val_consult_CZip=="" || val_consult_CZip==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Current address zip field is Required</font></b>.");
    //        $("#val_consult_CZip").css("border", "1px solid red");
    //        $("#consult_CZip").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_CPhone=="" || val_consult_CPhone==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Current address phone field is Required</font></b>.");
    //        $("#val_consult_CPhone").css("border", "1px solid red");
    //        $("#consult_CPhone").css("border","1px solid red");
    //        return false;
    //    }
    
    //    if(val_consult_salary=="" || val_consult_salary==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Salary field is Required</font></b>.");
    //        $("#val_consult_salary").css("border", "1px solid red");
    //        $("#consult_salary").css("border","1px solid red");
    //        return false;
    //    }
    if(val_consult_jobTitle=="" || val_consult_jobTitle==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Job title field is Required</font></b>.");
        $("#val_consult_jobTitle").css("border", "1px solid red");
        $("#consult_jobTitle").css("border","1px solid red");
        return false;
    }
    if(val_consult_experience=="" || val_consult_experience==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Experience field is Required</font></b>.");
        $("#val_consult_experience").css("border", "1px solid red");
        $("#consult_experience").css("border","1px solid red");
        return false;
    }
    
    if(val_consult_wcountry=="" || val_consult_wcountry==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Working country field is Required</font></b>.");
        $("#val_consult_wcountry").css("border", "1px solid red");
        $("#consult_wcountry").css("border","1px solid red");
        return false;
    }
    if(val_consult_industry=="" || val_consult_industry==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Industry field is Required</font></b>.");
        $("#val_consult_industry").css("border", "1px solid red");
        $("#consult_industry").css("border","1px solid red");
        return false;
    }
    if (FileUploadPath == '') {
       
        $("addCnsltError").html(" <b><font color='red'>Please upload a file</font></b>");
        return false;
    }
    else {
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

        if (Extension == "pdf" || Extension == "doc" || Extension == "docx" ) {
            $("addCnsltError").html(" ");
            $("#fileSpanId").css("color", "");
                   
        } 
        else {
            $("addCnsltError").html(" <b><font color='red'> Allows only doc, docx or pdf type.</font></b>");
            $("#fileSpanId").css("color", "red");
            return false;
        }
    }
    //    if(val_consult_organization=="" || val_consult_organization==null){
    //        alert("hi")
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Organizaton field is Req
    //        uired</font></b>.");
    //        $("#val_consult_organization").css("border", "1px solid red");
    //        $("#consult_organization").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_preferredState=="" || val_consult_preferredState==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Preferrd state field is Required</font></b>.");
    //        $("#val_consult_preferredState").css("border", "1px solid red");
    //        $("#consult_preferredState").css("border","1px solid red");
    //        return false;
    //    }
    
    //    if(val_consult_workPhone=="" || val_consult_workPhone==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Work phone field is Required</font></b>.");
    //        $("#val_consult_workPhone").css("border", "1px solid red");
    //        $("#consult_workPhone").css("border","1px solid red");
    //        return false;
    //    }
    //    if(val_consult_pcountry=="" || val_consult_pcountry==null){
    //        $("addCnsltError").html(" <b><font color=#FF4D4D>Preferrd country field is Required</font></b>.");
    //        $("#val_consult_pcountry").css("border", "1px solid red");
    //        $("#consult_pcountry").css("border","1px solid red");
    //        return false;
    //    }
    //   if(skill==""||skill==null){
    //      $("addCnsltError").html(" <b><font color=#FF4D4D>skill field is Required</font></b>.");
    //        //$("#val_consult_skill").css("border", "1px solid red");
    //        $("#consult_skill").css("border","1px solid red");
    //        return false;  
    //   }
   
    $("addCnsltError").html("");
    $("#consult_email").css("border", "1px solid #3BB9FF");
    $("#val_consult_email").css("border", "1px solid #3BB9FF");
    //$("#consult_add_date").css("border", "1px solid #3BB9FF");
    $("#consult_available").css("border", "1px solid #3BB9FF");
    $("#consult_fstname").css("border", "1px solid #3BB9FF");
    $("#consult_lstname").css("border", "1px solid #3BB9FF");
    $("#consult_dob").css("border", "1px solid #3BB9FF");
    $("#consult_mobileNo").css("border", "1px solid #3BB9FF");
    $("#consult_lcountry").css("border", "1px solid #3BB9FF");
    $("#consult_Address").css("border", "1px solid #3BB9FF");
    $("#consult_City").css("border", "1px solid #3BB9FF");
    $("#consult_Country").css("border", "1px solid #3BB9FF");
    $("#consult_State").css("border", "1px solid #3BB9FF");
    //$("#consult_Zip").css("border", "1px solid #3BB9FF");
    //$("#consult_Phone").css("border", "1px solid #3BB9FF");
    $("#consult_CAddress").css("border", "1px solid #3BB9FF");
    $("#consult_CCity").css("border", "1px solid #3BB9FF");
    $("#consult_CCountry").css("border", "1px solid #3BB9FF");
    $("#consult_CState").css("border", "1px solid #3BB9FF");
    //$("#consult_CZip").css("border", "1px solid #3BB9FF");
    // $("#consult_CPhone").css("border", "1px solid #3BB9FF");
    $("#consult_industry").css("border", "1px solid #3BB9FF");
    //$("#consult_salary").css("border", "1px solid #3BB9FF");
    $("#consult_wcountry").css("border", "1px solid #3BB9FF");
    // $("#consult_organization").css("border", "1px solid #3BB9FF");
    $("#consult_experience").css("border", "1px solid #3BB9FF");
    //$("#consult_preferredState").css("border", "1px solid #3BB9FF");
    $("#consult_jobTitle").css("border", "1px solid #3BB9FF");
    $("#consult_workPhone").css("border", "1px solid #3BB9FF");
    $("#consult_skill").css("border", "1px solid #3BB9FF");
        
    submmition();
        
    return true;
}
function submmition(){
    var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).val()); 
    });
    // alert(skillCategoryArry);
    document.getElementById("skillValues").value=skillCategoryArry;
    var v=document.getElementById("skillValues").value;
    
//  alert(v);
//  alert(skillCategoryArry);
//  document.getElementById("consultantForm").submit(); 
}

// function to copy the permanent address into  current address add by Aklakh 
function FillAddress() {
    if(addconsult_checkAddress.checked == true) {
        consult_CAddress.value= consult_Address.value;
        document.getElementById("consult_CAddress").disabled = true;
        consult_CAddress2.value= consult_Address2.value;
        document.getElementById("consult_CAddress2").disabled = true;
        consult_CCity.value=consult_City.value;
        document.getElementById("consult_CCity").disabled = true;
        consult_CCountry.value=consult_Country.value;        
        document.getElementById("consult_CCountry").disabled = true;     
        var $options = $("#consult_State > option").clone();
        $('#consult_CState').append($options);
        consult_CState.value=consult_State.value; 
        document.getElementById("consult_CState").disabled = true;
        consult_CZip.value=consult_Zip.value;
        document.getElementById("consult_CZip").disabled = true;
        consult_CPhone.value=consult_Phone.value;
        document.getElementById("consult_CPhone").disabled = true;
    }
    if(addconsult_checkAddress.checked == false) {
        document.getElementById("consult_CAddress").disabled = false;
        consult_CAddress.value='';
        document.getElementById("consult_CAddress2").disabled = false;
        consult_CAddress2.value='';
        document.getElementById("consult_CCity").disabled = false;
        consult_CCity.value='';
        document.getElementById("consult_CCountry").disabled = false;
        consult_CCountry.value='';
        document.getElementById("consult_CState").disabled = false;
        consult_CState.value='';
        document.getElementById("consult_CZip").disabled = false;
        consult_CZip.value='';
        document.getElementById("consult_CPhone").disabled = false;
        consult_CPhone.value='';

    }
}
// To clear the form add by Aklakh
function clearForm(){
    $("addCnsltError").html("");
    consult_email.value='';
    consult_fstname.value='';
    // consult_gender1.value='';
    consult_homePhone.value='';
    consult_add_date.value='';
    consult_midname.value='';
    consult_dob.value='';
    consult_mobileNo.value='';
    consult_available.value='';
    consult_lstname.value='';
    consult_homePhone.value='';
    // consult_mStatus.value='';
    consult_lcountry.value='';   
    consult_alterEmail.value='';
    consult_ssnNo.value='';
    consult_Address.value='';
    consult_Address2.value='';
    consult_City.value='';
    consult_Country.value='';
    consult_State.value='';
    consult_Zip.value='';
    consult_Phone.value='';
    consult_CAddress.value='';
    consult_CAddress2.value='';
    consult_CCity.value='';
    consult_CCountry.value='';
    consult_CState.value='';
    consult_CZip.value='';
    consult_CPhone.value='';    
    consult_jobTitle.value='';
    consult_industry.value='';
    consult_organization.value='';
    consult_salary.value='';
    consult_experience.value='';
    consult_workPhone.value='';
    consult_referredBy.value='';
    consult_wcountry.value='';
    consult_pcountry.value='';
    consult_preferredState.value='';
    consult_comments.value='';
    consult_education.value='';
    $("#description_feedback").html("");
    $("#comments_feedback").html("");
    $("#consult_email").css("border", "1px solid #3BB9FF");
    $("#val_consult_email").css("border", "1px solid #3BB9FF");
    $("#consult_add_date").css("border", "1px solid #3BB9FF");
    $("#consult_available").css("border", "1px solid #3BB9FF");
    $("#consult_fstname").css("border", "1px solid #3BB9FF");
    $("#consult_lstname").css("border", "1px solid #3BB9FF");
    $("#consult_dob").css("border", "1px solid #3BB9FF");
    $("#consult_homePhone").css("border", "1px solid #3BB9FF");
    $("#consult_mobileNo").css("border", "1px solid #3BB9FF");
    $("#consult_lcountry").css("border", "1px solid #3BB9FF");
    $("#consult_alterEmail").css("border", "1px solid #3BB9FF");
    $("#consult_ssnNo").css("border", "1px solid #3BB9FF");
    $("#consult_Address").css("border", "1px solid #3BB9FF");
    $("#consult_Address2").css("border", "1px solid #3BB9FF");
    $("#consult_City").css("border", "1px solid #3BB9FF");
    $("#consult_Country").css("border", "1px solid #3BB9FF");
    $("#consult_State").css("border", "1px solid #3BB9FF");
    $("#consult_Zip").css("border", "1px solid #3BB9FF");
    $("#consult_Phone").css("border", "1px solid #3BB9FF");
    $("#consult_CAddress").css("border", "1px solid #3BB9FF");
    $("#consult_CAddress2").css("border", "1px solid #3BB9FF");
    $("#consult_CCity").css("border", "1px solid #3BB9FF");
    $("#consult_CCountry").css("border", "1px solid #3BB9FF");
    $("#consult_CState").css("border", "1px solid #3BB9FF");
    $("#consult_CZip").css("border", "1px solid #3BB9FF");
    $("#consult_CPhone").css("border", "1px solid #3BB9FF");
    $("#consult_industry").css("border", "1px solid #3BB9FF");
    $("#consult_salary").css("border", "1px solid #3BB9FF");
    $("#consult_wcountry").css("border", "1px solid #3BB9FF");
    $("#consult_organization").css("border", "1px solid #3BB9FF");
    $("#consult_experience").css("border", "1px solid #3BB9FF");
    $("#consult_preferredState").css("border", "1px solid #3BB9FF");
    $("#consult_referredBy").css("border", "1px solid #3BB9FF");
    $("#consult_jobTitle").css("border", "1px solid #3BB9FF");
    $("#consult_workPhone").css("border", "1px solid #3BB9FF");
    $("#consult_pcountry").css("border", "1px solid #3BB9FF");
}

// To check the character length of description text area  add by Aklakh
function checkCharactersDescription(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#description_feedback").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#description_feedback").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}
// To check the character length of comments text area  add by Aklakh
function checkCharactersComments(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#comments_feedback").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#comments_feedback").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
}

//created by triveni

var consultCalender,consultdob,consultconfidential;
function consultdoOnLoad() {
                
   
    
    // alert("hii");docdatepickerfrom","docdatepicker
    consultCalender = new dhtmlXCalendarObject(["consult_favail"]);
    // alert("hii1");
    consultCalender.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    consultCalender.setDateFormat("%m-%d-%Y");
    
    consultdob = new dhtmlXCalendarObject(["consult_dob"]);
    // alert("hii1");
    consultdob.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    consultdob.setDateFormat("%m-%d-%Y");
   
    consultconfidential  = new dhtmlXCalendarObject(["consult_passport"]);
    // alert("hii1");
    consultconfidential.setSkin('omega');
    // alert("hii2");
    //myCalendar.setDateFormat("%m/%d/%Y %H:%i");
    consultconfidential.setDateFormat("%m/%d/%Y");
   
    
}
function consult_enterDateRepository(){
    document.getElementById('consult_favail').value = "";
    //document.getElementById('end_date').value = "";
    document.getElementById('consult_dob').value = "";
    //document.getElementById('end_date').value = "";
    document.getElementById('consult_passport').value = "";
    //document.getElementById('end_date').value = "";
    alert("Please select from the Calender !");
    return false;
}
/* consultant details validations */

function consultvalid_email(){
    
    var email=document.getElementById("consult_email").value;
    re = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
    if(email==""){
        $("consult_error").html(" <b><font color='red'>Email is Required</font></b>");
        $("#consult_email").css("border", "1px solid red");
    }
    else if(!re.test(email))
    {
     
        $("consult_error").html(" <b><font color='red'>must be valid Email</font></b>");
        $("#consult_email").css("border", "1px solid red");
    }
    else
    {
      
        $("consult_error").html(" ");
        $("#consult_email").css("border", "1px solid green");
    }
    
}

function consultvalid_fname(){
    $("successMessage").html(" ");
    var fstname=document.getElementById("consult_fstname").value;
    
    if(fstname=="" || fstname==null )
    {
   
        $("consult_error").html(" <b><font color='red'>First name field is Required</font></b>");
        $("#consult_fstname").css("border", "1px solid red");
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_fstname").css("border", "1px solid green");
    }
}



function consultvalid_lstname(){
    $("successMessage").html(" ");
    var lstname=document.getElementById("consult_lstname").value;
    
    if(lstname=="" || lstname==null)
    {
   
        $("consult_error").html(" <b><font color='red'>last name is required</font></b>");
        $("#consult_lstname").css("border", "1px solid red");
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_lstname").css("border", "1px solid green");
    }
}

function consultvalid_industry(){
    $("successMessage").html(" ");
    var consult_industry=$('#consult_industry').val();
      
    if(consult_industry==-1){
        $("consult_error").html(" <b><font color='red'>Industry field is required</font></b>");
        $("#consult_industry").css("border", "1px solid red");
    }
    else{
        $("consult_error").html(" ");
        $("#consult_industry").css("border", "1px solid green");
    }
}

function consultvalid_avail(){
    var consult_available=$('#consult_available').val();
    if(consult_available==-1){
        $("consult_error").html(" <b><font color='red'>Available field is required</font></b>");
        $("#consult_available").css("border", "1px solid red");
    }
    else{
        $("consult_error").html(" ");
        $("#consult_available").css("border", "1px solid green");
    }
   
}

function consultvalid_wcountry(){
    $("successMessage").html(" ");
    var consult_wcountry=$('#consult_wcountry').val();
    if(consult_wcountry=="" || consult_wcountry==null){
        $("consult_error").html(" <b><font color='red'>WorkingCountry field is required</font></b>");
        $("#consult_wcountry").css("border", "1px solid red");
    } 
    else{
        $("consult_error").html(" ");
        $("#consult_wcountry").css("border", "1px solid green");
    
    }
       
}

function consultvalid_organization(){
    var consult_organization=$('#consult_organization').val();
    if(consult_organization==-1){
        $("consult_error").html(" <b><font color='red'>Organisation field is required</font></b>");
        $("#consult_organization").css("border", "1px solid red");
    }
    else{
        $("consult_error").html(" ");
        $("#consult_organization").css("border", "1px solid green");
    } 
}

function consultvalid_experience(){
    $("successMessage").html(" ");
    var consult_experience=$('#consult_experience').val();
    if(consult_experience==-1){
        $("consult_error").html(" <b><font color='red'>Experiance field is required</font></b>");
        $("#consult_experience").css("border", "1px solid red");
    }
    else{
        $("consult_error").html(" ");
        $("#consult_experience").css("border", "1px solid green");
    }
   
}

function consultvalid_preferredState(){
    var consult_preferredState=$('#consult_preferredState').val();
    if(consult_preferredState==-1){
        $("consult_error").html(" <b><font color='red'>PreferredState field is required</font></b>");
        $("#consult_preferredState").css("border", "1px solid red");
    }
    else{
        $("consult_error").html(" ");
        $("#consult_preferredState").css("border", "1px solid green");
    }  
}
function consultvalid_jobTitle(){
    $("successMessage").html(" ");
    var consult_jobTitle=$('#consult_jobTitle').val();
    if(consult_jobTitle=="" || consult_jobTitle==null){
        $("consult_error").html(" <b><font color='red'>Job Title field is required</font></b>");
        $("#consult_jobTitle").css("border", "1px solid red");
    } 
    else{
        $("consult_error").html(" ");
        $("#consult_jobTitle").css("border", "1px solid green");
    
    } 
}

function consultvalid_Salary(){
    var consult_salary=$('#consult_salary').val();
    if(consult_salary=="" || consult_salary==null){
        $("consult_error").html(" <b><font color='red'>Rate/Salery field is required</font></b>");
        $("#consult_salary").css("border", "1px solid red");
    } 
    else{
        $("consult_error").html(" ");
        $("#consult_salary").css("border", "1px solid green");
    
    }  
}

function ConsultDetails_valid(){
    var skillCategoryArry = [];    
    $("#skillCategoryValue :selected").each(function(){
        skillCategoryArry.push($(this).val()); 
    });
    document.getElementById("skillValues").value=skillCategoryArry;
    $("successMessage").html(" ");
    var email=document.getElementById("consult_email").value;
    var consult_industry=$('#consult_industry').val();
    var dob=document.getElementById("consult_dob").value;
    var mblno=document.getElementById("consult_mobileNo").value;
    var city=document.getElementById("consult_City").value;
    var state=document.getElementById("consult_State").value;
    var country=document.getElementById("consult_Country").value;
    var ccity=document.getElementById("consult_CCity").value;
    var cstate=document.getElementById("consult_CState").value;
    var ccountry=document.getElementById("consult_CCountry").value;
    var workingCountry=document.getElementById("consult_wcountry").value;
    // var skill=document.getElementById("consult_skill").value;
    
    // var consult_favail=$('#consult_favail').val();
    //var consult_available=$('#consult_available').val();
    var consult_organization=$('#consult_organization').val();
    var consult_experience=$('#consult_experience').val();
    // var consult_preferredState=$('#consult_preferredState').val();
    var consult_jobTitle=$('#consult_jobTitle').val();
    // var consult_salary=$('#consult_salary').val();
    //var consult_wcountry=$('#consult_wcountry').val();
    var dobDate = dob.split('-');
    var dobChangedDate = new Date(dobDate[2], dobDate[0]-1 , dobDate[1]);
    var todayDate= new Date();
    var dobYeardifference= (todayDate-dobChangedDate)/ (86400000 * 360);
    
    var lstname=document.getElementById("consult_lstname").value;
    var fstname=document.getElementById("consult_fstname").value;
    
    var userCatArry = [];  
    if(skillCategoryArry=="" )
    {
        $("consult_error").html(" <b><font color='red'>skill  is Required</font></b>");
        $("#skillCategoryValue").css("border", "1px solid red");
        return false;
    } else
{
        $("consult_error").html(" ");
        $("#skillCategoryValue").css("border", "1px solid green");
    }
    if($("#consult_preferredState :selected").length>5)
    {
            
        $("consult_error").html(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
        //  $("#consult_preferredState").val(" <b><font color= #FF4D4D>Preferred State should not be selected more than 5</font></b>.");
        $("#consult_preferredState").css("border","1px solid red");
           
        return false;
    }
    else{
            
        $("#consult_preferredState").css("border","1px solid white");
        
        $("#consult_preferredState :selected").each(function(){
            userCatArry.push($(this).val()); 
        });
        $("#PrefstateValues").val(userCatArry);
   
    }
    re = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
    if(email==""){
        $("consult_error").html(" <b><font color='red'>Email is Required</font></b>");
        $("#consult_email").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_email").css("border", "1px solid green");
    }
    
    if(fstname==' ' || fstname==null )
    {
        $("consult_error").html(" <b><font color='red'>first name is Required</font></b>");
        $("#consult_fstname").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html("");
        $("#consult_fstname").css("border", "1px solid green");
    }

   
    if(lstname==' '  || lstname==null)
    {
        //alert("di")
        $("consult_error").html(" <b><font color='red'>last name is Required</font></b>");
        $("#consult_lstname").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html("");
        $("#consult_lstname").css("border", "1px solid green");
    }
    if(dob==' '){
        $("consult_error").html(" <b><font color='red'>Dob is Required</font></b>");
        $("#consult_dob").css("border", "1px solid red");
        return false;
    }
    else if(dobYeardifference < 20){
        $("consult_error").html(" <b><font color= #FF4D4D>Consultant must be atleast 20 years old.</font></b>.");
        $("#consult_dob").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_dob").css("border", "1px solid green");
    }
    if(mblno==""){
        $("consult_error").html(" <b><font color='red'>Mobile No is Required</font></b>");
        $("#consult_mobileNo").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_moblileNo").css("border", "1px solid green");
    }
    if(city==""){
        $("consult_error").html(" <b><font color='red'>City is Required</font></b>");
        $("#consult_City").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_City").css("border", "1px solid green");
    }
    if(country==""){
        $("consult_error").html(" <b><font color='red'>Country is Required</font></b>");
        $("#consult_Country").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_Country").css("border", "1px solid green");
    }
    if(state==""){
        $("consult_error").html(" <b><font color='red'>State is Required</font></b>");
        $("#consult_State").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_State").css("border", "1px solid green");
    }
    if(ccity==' '){
        $("consult_error").html(" <b><font color='red'>City is Required</font></b>");
        $("#consult_CCity").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_CCity").css("border", "1px solid green");
    }
    
    if(ccountry==""){
        $("consult_error").html(" <b><font color='red'>Country is Required</font></b>");
        $("#consult_CCountry").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_CCountry").css("border", "1px solid green");
    }
    if(cstate==""){
        $("consult_error").html(" <b><font color='red'>State is Required</font></b>");
        $("#consult_CState").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_CState").css("border", "1px solid green");
    }
    
    if(consult_jobTitle=="" || consult_jobTitle==null){
        $("consult_error").html(" <b><font color='red'>job Title is Required</font></b>");
        $("#consult_jobTitle").css("border", "1px solid red");
        return false;
    } 
    else{
        $("consult_error").html("");
        $("#consult_jobTitle").css("border", "1px solid green");
    
    }
    if(consult_experience==-1){
        $("consult_error").html(" <b><font color='red'>Experience field is Required</font></b>");
        $("#consult_experience").css("border", "1px solid red");
        return false;
    }
    else{
        $("consult_error").html("");
        $("#consult_experience").css("border", "1px solid green");
    }
    if(workingCountry=="" || workingCountry==null){
        $("consult_error").html(" <b><font color='red'>working country is Required</font></b>");
        $("#consult_wcountry").css("border", "1px solid red");
        return false;
    } 
    else{
        $("consult_error").html("");
        $("#consult_wcountry").css("border", "1px solid green");
    
    }
    
    if(consult_industry==-1){
        $("consult_error").html(" <b><font color='red'>industry is Required</font></b>");
        $("#consult_industry").css("border", "1px solid red");
        return false;
    }
    else{
        $("consult_error").html("");
        $("#consult_industry").css("border", "1px solid green");
    }
    if(skill==""){
        $("consult_error").html(" <b><font color='red'>Skill is Required</font></b>");
        $("#consult_skill").css("border", "1px solid red"); 
        return false;
    }
    else
    {
        $("consult_error").html(" ");
        $("#consult_skill").css("border", "1px solid green");
    }
     
//    if(consult_favail=="" || consult_favail==null){
//        $("#consult_favail").css("border", "1px solid red");
//    } 
//    else{
//        $("#consult_favail").css("border", "1px solid green");
//    }
//    if(consult_available==-1){
//        $("#consult_available").css("border", "1px solid red");
//    }
//    else{
//        $("#consult_available").css("border", "1px solid green");
//    }
//    if(consult_organization==-1){
//        $("#consult_organization").css("border", "1px solid red");
//    }
//    else{
//        $("#consult_organization").css("border", "1px solid green");
//    }
    
//    if(consult_preferredState==-1){
//        $("#consult_preferredState").css("border", "1px solid red");
//    }
//    else{
//        $("#consult_preferredState").css("border", "1px solid green");
//    }
    
//    if(consult_salary=="" || consult_salary==null){
//        $("#consult_salary").css("border", "1px solid red");
//    } 
//    else{
//        $("#consult_salary").css("border", "1px solid green");
//    
//    }
//       
    
}

/* consultant confidential info */

function consult_panValidation(){
    
    //alert("pan")
    var pancard=document.getElementById("consult_pan").value;
     
    pattern = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$|^\d{3}-\d{2}-\d{4}$/;
   
    if(pancard=="" || pancard==null){
        $("securityinfo").html("<b><font color='red'>field is required<br></font></b>");
        $("#consult_pan").css("border", "1px solid red");
       
    }
    
    else if(!pattern.test(pancard))
    {
        //alert("hii")
        $("securityinfo").html(" <b><font color='red'>must be valid pancard number<br>Example:ABCde1234F/123-12-1234</font></b>");
        $("#consult_pan").css("border", "1px solid red");
     
    }

    else
    {
        $("#consult_pan").css("border", "1px solid green");
        $("securityinfo").html("");
       
    }
    
}

function consult_nameValidation(){
    
    //alert("12")
    var Name=document.getElementById("consult_panname").value;
    pattern = /^[A-Za-z ]+$/;
    
    if(Name=="" || Name==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_panname").css("border", "1px solid red");
    }
    else if(!pattern.test(Name))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid name<br>Example:John</font></b>");
        $("#consult_panname").css("border", "1px solid red");
    }
    else
    {
        $("#consult_panname").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_banknameValidation(){
    
    //alert("12")
    var Bank=document.getElementById("consult_bank").value;
    pattern = /^[A-Za-z ]+$/;
    if(Bank=="" || Bank==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_bank").css("border", "1px solid red");
    }
    else if(!pattern.test(Bank))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid bank name<br>Example:SBI</font></b>");
        $("#consult_bank").css("border", "1px solid red");
    }
    else
    {
        $("#consult_bank").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_banAccknumValidation(){
    
    //alert("12")
    var Banknumber=document.getElementById("consult_banknum").value;
    //pattern = /^[A-Za-z]{2}[0-9]{16}$/;
    pattern=/^[a-zA-Z0-9](?=[\w.]{10,20}$)\w*\.?\w*$/i;
    if(Banknumber=="" || Banknumber==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_banknum").css("border", "1px solid red");
    }
    else if(!pattern.test(Banknumber))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid Bank Account Number<br>Example:A1234d567891234567</font></b>");
        $("#consult_banknum").css("border", "1px solid red");
    }
    else
    {
        $("#consult_banknum").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_holdnameValidation(){
    
    //alert("12")
    var Holdername=document.getElementById("consult_hname").value;
    pattern = /^[A-Za-z ]+$/;
    if(Holdername=="" || Holdername==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_hname").css("border", "1px solid red");
    }
    else if(!pattern.test(Holdername))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid Account Holder Name<br>Example:John</font></b>");
        $("#consult_hname").css("border", "1px solid red");
    }
    else
    {
        $("#consult_hname").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_ifscValidation(){
    
    //alert("12")
    var IFSC=document.getElementById("consult_ifsc").value;
    pattern = /^[A-Za-z]{4}[0-9]{7}$/;
    if(IFSC=="" || IFSC==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_ifsc").css("border", "1px solid red");
    }
    else if(!pattern.test(IFSC))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid IFSC number<br>Example:ABcd0123456</font></b>");
        $("#consult_ifsc").css("border", "1px solid red");
    }
    else
    {
        $("#consult_ifsc").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_uanValidation(){
    
    //alert("12")
    var UAN=document.getElementById("consult_uan").value;
    pattern = /^[0-9]+$/;
    if(UAN=="" || UAN==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_uan").css("border", "1px solid red");
    }
    else if(!pattern.test(UAN))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid UAN number<br>Example:123456</font></b>");
        $("#consult_uan").css("border", "1px solid red");
    }
    else
    {
        $("#consult_uan").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_pfValidation(){
    
    //alert("12")
    var PF=document.getElementById("consult_pf").value;
    pattern = /^[A-Za-z]{2}-[0-9]{5}-[0-9]{7}$/;
    if(PF=="" || PF==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_pf").css("border", "1px solid red");
    }
    else if(!pattern.test(PF))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid PF number<br>Example:Ab-12345-1234567</font></b>");
        $("#consult_pf").css("border", "1px solid red");
    }
    else
    {
        $("#consult_pf").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

function consult_passportnumValidation(){
    
    //alert("12")
    var PASS=document.getElementById("consult_pass").value;
    //pattern = /^[A-Za-z]{1}[0-9]{7}$/;
    pattern=/^[a-zA-Z0-9](?=[\w.]{7,16}$)\w*\.?\w*$/i;
    if(PASS=="" || PASS==null){
        $("securityinfo").html(" <b><font color='red'>field is required</font></b>");
        $("#consult_pass").css("border", "1px solid red");
    }
    else if(!pattern.test(PASS))
    {
        $("securityinfo").html(" <b><font color='red'>must be valid passport number<br>Example:A12a3455</font></b>");
        $("#consult_pass").css("border", "1px solid red");
    }
    else
    {
        $("#consult_pass").css("border", "1px solid green");
        $("securityinfo").html("");
    }
}

                        
function ConsultantEditStateChange(){
    // alert("Consultant ajax");
    document.getElementById("consult_State").disabled = false;
    var id=document.getElementById('consult_Country').value;
   
    // alert(id);
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
           
            ConsultantState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function ConsultantState(data){
    //alert(data);
    var topicId = document.getElementById("consult_State");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_State');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}


function ConsultantCurrentStateChange(){
    // alert("Consultant ajax");
    document.getElementById("consult_CState").disabled = false;
    var id=document.getElementById('consult_CCountry').value;
   
    // alert(id);
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
           
            ConsultantCurrentState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function ConsultantCurrentState(data){
    //alert(data);
    var topicId = document.getElementById("consult_CState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_CState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}

// function for change the prefrred state in edit consultant page
function ConsultantEditPreferredStateChange(){
    // alert("Consultant ajax");
    document.getElementById("consult_preferredState").disabled = false;
    var id=document.getElementById('consult_preferredCountry').value;
   
    // alert(id);
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
           
            preferredState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function preferredState(data){
    //alert(data);
    var topicId = document.getElementById("consult_preferredState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_preferredState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
}


function sameAsAddress() {
    // alert(consult_checkAddress.checked);
    if(consult_checkAddress.checked == true) {
        consult_CAddress.value= consult_Address.value;
        document.getElementById("consult_CAddress").disabled = true;
        consult_CAddress2.value= consult_Address2.value;
        document.getElementById("consult_CAddress2").disabled = true;
        consult_CCity.value=consult_City.value;
        document.getElementById("consult_CCity").disabled = true;
        consult_CCountry.value=consult_Country.value;
        document.getElementById("consult_CCountry").disabled = true;
      
        var $options = $("#consult_State > option").clone();
        $('#consult_CState').append($options);
        consult_CState.value=consult_State.value; 
        document.getElementById("consult_CState").disabled = true;
        consult_CZip.value=consult_Zip.value;
        document.getElementById("consult_CZip").disabled = true;
        consult_CPhone.value=consult_Phone.value;
        document.getElementById("consult_CPhone").disabled = true;

    }
    
    if(consult_checkAddress.checked == false) {
        document.getElementById("consult_CAddress").disabled = false;
        
        document.getElementById("consult_CAddress2").disabled = false;
        
        document.getElementById("consult_CCity").disabled = false;
        document.getElementById("consult_CCountry").disabled = false;
        
        document.getElementById("consult_CState").disabled = false;
        
        document.getElementById("consult_CZip").disabled = false;
        
        document.getElementById("consult_CPhone").disabled = false;
        

    }
    
}
function defaultClick(){
    //document.getElementById("consult_State").disabled = true;
    //  document.getElementById("consult_preferredState").disabled = true;
    if(consult_checkAddress.checked == true) {
        document.getElementById("consult_CAddress").disabled = true;
        document.getElementById("consult_CAddress2").disabled = true;
        document.getElementById("consult_CCity").disabled = true;
        document.getElementById("consult_CCountry").disabled = true;
        //  consult_CState.value=consult_State.value;
        // alert("hai i'm der")
        document.getElementById("consult_CState").disabled = true;
        document.getElementById("consult_CZip").disabled = true;
        document.getElementById("consult_CPhone").disabled = true;

    }
    else{
        // document.getElementById("consult_CState").disabled = true;
        document.getElementById("consult_CAddress").disabled = false;        
        document.getElementById("consult_CAddress2").disabled = false;        
        document.getElementById("consult_CCity").disabled = false;
        document.getElementById("consult_CCountry").disabled = false;        
        document.getElementById("consult_CState").disabled = false;        
        document.getElementById("consult_CZip").disabled = false;        
        document.getElementById("consult_CPhone").disabled = false;       
    }
//document.getElementById("consult_CState").disabled = true;

    
}   


/* All function for add consultant validation , by Jitendra */
function fnamevalidate(){
    var fname=document.getElementById("consult_fstname").value;
    re=/^[A-Za-z ]+$/;

    if(fname==""||fname==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>First name field is Required</font></b>.");
        $("#consult_fstname").css("border", "1px solid red");
        return false;
    }
    if(!re.test(fname)){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid Name <br> Ex.John</font></b>.");
        $("#consult_fstname").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_fstname").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function lnamevalidate(){
    var lname=document.getElementById("consult_lstname").value;
    re=/^[A-Za-z ]+$/;
    if(lname==""||lname==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Last name field is Required</font></b>.");
        $("#consult_lstname").css("border", "1px solid red");
        return false;
    }
    if(!re.test(lname)){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Must be valid Name <br>Ex.John</font></b>.");
        $("#consult_lstname").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_lstname").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function mobValidation(){
   
    var mobnumber=document.getElementById("consult_mobileNo").value;
    //alert(mobnumber);
    var mlen=mobnumber.length;
    //alert("hi"+mlen);
    if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Mobile field is Required</font></b>.");
        
        $("#consult_mobileNo").css("border", "1px solid red");
        //alert("1");
        return false;
    }
    else if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>field is must be valid</font></b>.");
        $("#consult_mobileNo").css("border", "1px solid red");
        //  alert("2");
        return false;
    }
    
    else
    {
        $("#consult_mobileNo").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html(" ");
        // alert("3");
        return true;
    }

}

function consult_addressValidation(){
    var address=document.getElementById("consult_Address").value;
    if(address==""||address==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Address field is Required</font></b>.");
        $("#consult_Address").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_Address").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function pcityValidation(){
    var pcity=document.getElementById("consult_City").value;
    if(pcity==""||pcity==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>City field is Required</font></b>.");
        $("#consult_City").css("border", "1px solid red");
        $("consult_error").html(" <b><font color=#FF4D4D>City field is Required</font></b>.");
        return false;
    }
    else
    {
        $("#consult_City").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html("");
        return true;
    }
}

function pZipValidation(){
    var zip=document.getElementById("consult_Zip").value;
    if(zip==""||status==zip){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Zip field is Required</font></b>.");
        $("#consult_Zip").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_Zip").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function availableValidation(){
    var available=document.getElementById("consult_available").value;
    if(available==""||available==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Availability field is Required</font></b>.");
        $("#consult_available").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_available").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function lcountryValidation(){
    var lcountry=document.getElementById("consult_lcountry").value;
    if(lcountry==""||lcountry==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>living country field is Required</font></b>.");
        $("#consult_lcountry").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_lcountry").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}
function jobtitleValidate(){
    $("successMessage").html(" ");
    var jtitle=document.getElementById("consult_jobTitle").value;
    if(jtitle==""||jtitle==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Job Title field is Required</font></b>.");
        $("consult_error").html(" <b><font color=#FF4D4D>Job Title field is Required</font></b>.");
        $("#consult_jobTitle").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_jobTitle").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html("");
        return true;
    }
}

function expValidate(){
    $("successMessage").html(" ");
    var exp=document.getElementById("consult_experience").value;
    if(exp==""||exp==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Experience field is Required</font></b>.");
        $("consult_error").html(" <b><font color=#FF4D4D>Experience field is Required</font></b>.");
        $("#consult_experience").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_experience").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html("");
        return true;
    }
}

function workingCountryValidate(){
    $("successMessage").html("");
    var working=document.getElementById("consult_wcountry").value;
    if(working==""||working==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Working Country field is Required</font></b>.");
        $("consult_error").html(" <b><font color=#FF4D4D>Working Country field is Required</font></b>.");
        $("#consult_wcountry").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_wcountry").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html("");
        return true;
    }
}

function industryValidate(){
    $("successMessage").html("");
    var industry=document.getElementById("consult_industry").value;
    if(industry==""||industry==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Industry field is Required</font></b>.");
        $("consult_error").html(" <b><font color=#FF4D4D>Industry field is Required</font></b>.");
        $("#consult_industry").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_industry").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html("");
        return true;
    }   
}

function orgValidate(){
    var org=document.getElementById("consult_organization").value;
    if(org==""||org==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Organization field is Required</font></b>.");
        $("#consult_organization").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_organization").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }   
}

function pCAddressValidation(){
    var caddress=document.getElementById("consult_CAddress").value;
    if(caddress==""||caddress==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Address field is Required</font></b>.");
        $("#consult_CAddress").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_CAddress").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }    
}

function pCCityValidation(){
    var ccity=document.getElementById("consult_CCity").value;
    if(ccity==""||ccity==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>City field is Required</font></b>.");
        $("consult_error").html(" <b><font color=#FF4D4D>City field is Required</font></b>.");
        $("#consult_CCity").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_CCity").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        $("consult_error").html("");
        return true;
    }
}

function pCZipValidation(){
    var czip=document.getElementById("consult_CZip").value;
    if(czip==""||status==czip){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Zip field is Required</font></b>.");
        $("#consult_CZip").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_CZip").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function pPhoneValidation(){
    var mobnumber=document.getElementById("consult_Phone").value;
    //alert(mobnumber);
    var mlen=mobnumber.length;
    //alert("hi"+mlen);
    if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Phone field is Required</font></b>.");
        $("#consult_Phone").css("border", "1px solid red");
        //alert("1");
        return false;
    }
    else if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>field is must be valid</font></b>.");
        $("#consult_Phone").css("border", "1px solid red");
        //  alert("2");
        return false;
    }
    
    else
    {
        $("#consult_Phone").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        // alert("3");
        return true;
    }

}

function pCPhoneValidation(){
    var mobnumber=document.getElementById("consult_CPhone").value;
    //alert(mobnumber);
    var mlen=mobnumber.length;
    //alert("hi"+mlen);
    if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Phone field is Required</font></b>.");
        $("#consult_CPhone").css("border", "1px solid red");
        //alert("1");
        return false;
    }
    else if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>field is must be valid</font></b>.");
        $("#consult_CPhone").css("border", "1px solid red");
        //  alert("2");
        return false;
    }
    
    else
    {
        $("#consult_CPhone").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        // alert("3");
        return true;
    }

}

function wphoneValidate(){
    var mobnumber=document.getElementById("consult_workPhone").value;
    //alert(mobnumber);
    var mlen=mobnumber.length;
    //alert("hi"+mlen);
    if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>work Phone field is Required</font></b>.");
        $("#consult_workPhone").css("border", "1px solid red");
        //alert("1");
        return false;
    }
    else if(mlen<14){
        $("addCnsltError").html(" <b><font color=#FF4D4D>field is must be valid</font></b>.");
        $("#consult_workPhone").css("border", "1px solid red");
        //  alert("2");
        return false;
    }
    
    else
    {
        $("#consult_workPhone").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        // alert("3");
        return true;
    }

}

function preState(){
    var org=document.getElementById("consult_preferredState").value;
    $("#consult_preferredState").css("border", "1px solid #3BB9FF");
    $("addCnsltError").html("");
    return true;
}

function salValidate(){
    var sal=document.getElementById("consult_salary").value;
    if(sal==""||sal==null){
        $("addCnsltError").html(" <b><font color=#FF4D4D>salary field is Required</font></b>.");
        $("#consult_salary").css("border", "1px solid red");
        return false;
    }
    else
    {
        $("#consult_salary").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}

function availableValidate(){
    var avail=document.getElementById("consult_add_date").value;
    if(avail==1){
        $("addCnsltError").html(" <b><font color= #FF4D4D>Available field is Required</font></b>.");
        $("#consult_add_date").css("border", "1px solid red");
        
        return false;
    }
    else
    {
        $("#consult_add_date").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}
    
function dobValidate(){
    var dob=document.getElementById("consult_dob").value;
    //alert("hi");
    // alert(dob.length);
    if(dob==1){
        $("addCnsltError").html(" <b><font color= #FF4D4D>DOB field is Required</font></b>.");
        $("#consult_dob").css("border", "1px solid red");
        
        return false;
    }
    else
    {
        $("#consult_dob").css("border", "1px solid #3BB9FF");
        $("addCnsltError").html("");
        return true;
    }
}


function showAttachmentDetails(consult_id){
    // alert("in Ajax call ");
    var url='../consultant/getConsultantAttachments.action?consult_id='+consult_id;
    var req=initRequest(url);
    
       
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
        
                //alert("in response");
                populateAttachmentTable(req.responseText);
            
            } 
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}

function populateAttachmentTable(response){
    //  alert(response);  
    var attachmentList=response.split("^");



    var table = document.getElementById("consult-attach_pagenav");
    for(var i = table.rows.length - 1; i > 0; i--)
    {
        //    alert("in response table"+i);
      
      
        table.deleteRow(i);
    }
    
    // alert("in response table"+attachmentList.length);
      
    
    for(var i=0;i<attachmentList.length-1;i++){     
       
        //alert("in response table"+attachmentList.length);
       
        var Values=attachmentList[i].split("|");
        {  
            var attach_row = $("<tr/>")
            $("#consult-attach_pagenav").append(attach_row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            attach_row.append($('<td>' + Values[0] + "</td>"));
            attach_row.append($("<td>" + Values[2] + "</td>"));
            attach_row.append($("<td>" + Values[3] + "</td>"));
            attach_row.append($("<td><figcaption><button type='button' id='id' value="+ Values[4] +" onclick=doConsultAttachmentDownload("+ Values[4] +")><img src='../../includes/images/download.png' height=20 width=20 ></button></figcaption></td>"));
            attach_row.append($("<td>" + Values[5] + "</td>"));    
        }
    }

    $('#consult-attach_pagenav').tablePaginate({
        navigateType:'navigator'
    },recordPage);
    cpager.init();     
    
}

function doConsultAttachmentDownload(acc_attachment_id)
{
    
    $("#resume").html("");
    var consult_id=$("#consult_id").val();
    var consultFlag=$("#consultFlag").val();
    var consultantFlag=$("#consultantFlag").val();
    //alert(consultFlag);
    if(consultFlag=='customer'){
        
        window.location = '../consultant/downloadConsultantAttachedDocument.action?acc_attachment_id='+acc_attachment_id+'&consult_id='+consult_id+'&consultFlag='+consultFlag+'&consultantFlag='+consultantFlag;
       
    }else{
        window.location = '../consultant/downloadConsultantAttachment.action?acc_attachment_id='+acc_attachment_id+'&consult_id='+consult_id+'&consultFlag='+consultFlag+'&consultantFlag='+consultantFlag;
    }
// window.location = '../consultant/downloadConsultantAttachment.action?acc_attachment_id='+acc_attachment_id+'&consult_id='+consult_id+'&consultFlag='+consultFlag;
}
function consultAttachmentDownload()
{
    
    $("#resume").html("");
    var acc_attachment_id=$("#acc_attachment_id").val();
    var consult_id=$("#consult_id").val();
    var consultFlag=$("#consultFlag").val();
    var requirementId=$("#requirementId").val();
    var jdId=$("#jdId").val();
    var accountSearchID=$("#accountSearchID").val();
    // alert("requirementId-->"+requirementId+"jdId-->"+jdId+"accountSearchID-->"+accountSearchID);
    // alert(acc_attachment_id);
    if(consultFlag=='customer'){
        // alert(consultFlag);
        window.location = '../consultant/downloadConsultantAttachedDocument.action?acc_attachment_id='+acc_attachment_id+'&consult_id='+consult_id+'&consultFlag='+consultFlag+'&requirementId='+requirementId+'&accountSearchID='+accountSearchID+'&jdId='+jdId;
       
    }else{
        window.location = '../consultant/downloadConsultantAttachment.action?acc_attachment_id='+acc_attachment_id+'&consult_id='+consult_id+'&consultFlag='+consultFlag;
    }
}
$(document).ready(function(){
    
    $('#addAttach_popup').popup(); 
});
// Add By Aklakh
function saveConsultantLoginDetails(id){
    // alert(id);    
    var url=CONTENXT_PATH+'/recruitment/consultant/consultantLoginDetails.action?consult_id='+id;
    //  alert(url)
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {                
                document.getElementById("responseMessage").innerHTML=req.responseText;                
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
function cheadingMessage(message)
{
    //alert(message.id);
    if(message.id=="c_details"){
        document.getElementById("headingmessage").innerHTML="Consultant Details";
    }
    if(message.id=="c_skill"){
        document.getElementById("headingmessage").innerHTML="Skill Information";
    }
    if(message.id=="c_security"){
        document.getElementById("headingmessage").innerHTML="Confidential Information";
    }
    if(message.id=="c_activities"){
        document.getElementById("headingmessage").innerHTML="Activities";
    }
    if(message.id=="c_attach"){
        document.getElementById("headingmessage").innerHTML="Attachment";
    }
    if(message.id=="c_personal"){
        document.getElementById("headingmessage").innerHTML="Personal Details";
    }
    if(message.id=="c_notes"){
        document.getElementById("headingmessage").innerHTML="Notes";
    }
    if(message.id=="c_tech-review"){
        document.getElementById("headingmessage").innerHTML="Tech Review";
    }
}




function removedCheckedAddress(){

    if(consult_checkAddress.checked == true) {
        document.getElementById("consult_checkAddress").checked = false;        
        document.getElementById("consult_CAddress").disabled = false;        
        document.getElementById("consult_CAddress2").disabled = false;        
        document.getElementById("consult_CCity").disabled = false;
        document.getElementById("consult_CCountry").disabled = false;        
        document.getElementById("consult_CState").disabled = false;        
        document.getElementById("consult_CZip").disabled = false;        
        document.getElementById("consult_CPhone").disabled = false;
    }
}
function disableCurrentAddress(){
    if(addconsult_checkAddress.checked==true ) {
        document.getElementById("addconsult_checkAddress").checked = false;      
        document.getElementById("consult_CAddress").disabled = false;        
        document.getElementById("consult_CAddress2").disabled = false;        
        document.getElementById("consult_CCity").disabled = false;
        document.getElementById("consult_CCountry").disabled = false;        
        document.getElementById("consult_CState").disabled = false;        
        document.getElementById("consult_CZip").disabled = false;        
        document.getElementById("consult_CPhone").disabled = false;
        consult_CAddress.value='';
        consult_CAddress2.value='';
        consult_CCity.value='';
        consult_CCountry.value='';
        consult_CState.value='';
        consult_CZip.value='';
        
    }
}

function consultValidAlterEmail(){
    
    var email=document.getElementById("consult_alterEmail").value;
    re = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;
    re = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if(email==""){
        $("addCnsltError").html("");
        $("consult_error").html("");        
        $("#consult_alterEmail").css("border", "");
    }
    else if(!re.test(email))
    {
     
        $("addCnsltError").html(" <b><font color='red'>Please enter valid Email</font></b>");
        $("consult_error").html(" <b><font color='red'>Please enter valid Email</font></b>");
        $("#consult_alterEmail").css("border", "1px solid red");
    }
    else
    {
        $("consult_error").html("");        
        $("addCnsltError").html("");
        $("#consult_alterEmail").css("border", "1px solid green");
    }
    
    
}
//Add by Aklakh

function consultantSkillSetOverlay(response){
    document.getElementById("consultantSkillSetDetails").value=response;
    var specialBox = document.getElementById("consultantSkillSetBox");
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
        
    } else {
        specialBox.style.display = "block";      
    }
    $('#consultantSkillOverlay_popup').popup(      
        ); 
}
function consultantSkillOverlayClose(){
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
function resumeValidation(){
    var FileUploadPath = document.getElementById('file').value;

    //To check if user upload any file
    if (FileUploadPath == '') {
       
        $("addCnsltError").html(" <b><font color='red'>Please upload a file</font></b>");
        return false;
    } else {
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

        if (Extension == "pdf" || Extension == "doc" || Extension == "docx" ) {
            $("addCnsltError").html(" ");
            return true;
        } 
        else {
            $("addCnsltError").html(" <b><font color='red'> Allows only doc,docx type or pdf.</font></b>");
            return false;
        }
    }
}


function editResumeValidation(){
    var FileUploadPath = document.getElementById('consultAttachment').value;

    //To check if user upload any file
    if (FileUploadPath == '') {
       
        $("#message").html(" <b><font color='red'>Please upload a file</font></b>");
        return false;
    } else {
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

        if (Extension == "pdf" || Extension == "doc" || Extension == "docx" ) {
            $("#message").html(" ");
            return true;
        } 
        else {
            $("#message").html(" <b><font color='red'> Allows only doc ,docx or pdf type.</font></b>");
            return false;
        }
    }
}
function doResumeDownlaod(acc_attachment_id){
    //alert("hello")
    window.location= "recruitment/consultantLogin/doResumeDownload.action?acc_attachment_id="+acc_attachment_id;
   
}
// for skill validation
function skillValidation(){
    
    //alert("12")
    var consult_skill=document.getElementById("consult_skill").value;
   
    if(consult_skill=="" || consult_skill==null){
        $("addCnsltError").html(" <b><font color='red'>Skills field is required</font></b>");
        $("consult_error").html(" <b><font color='red'>Skills field is required</font></b>");
        $("#consult_skill").css("border", "1px solid red");
    }
   
    else
    {
        $("#consult_skill").css("border", "1px solid green");
        $("addCnsltError").html("");
        $("consult_error").html("");
        
    }
}

function StateChangeValidation()
{
    // alert("Consultant ajax");
    
    var consult_State=document.getElementById('consult_State').value;
    if(consult_State==""){
        //alert("hi");
        $("addCnsltError").html(" <b><font color=#FF4D4D>permanent state field is Required</font></b>.");
        $("#consult_State").css("border", "1px solid red");
    }
    else{
        //alert("hello");
        $("#consult_State").css("border", "1px solid green");
        $("addCnsltError").html("");
    }
    
}
function CurrentStateChangeValidation()
{
    // alert("Consultant ajax");
    
    var consult_State=document.getElementById('consult_CState').value;
    if(consult_State==""){
        //alert("hi");
        $("addCnsltError").html(" <b><font color=#FF4D4D>current state field is Required</font></b>.");
        $("consult_error").html(" <b><font color=#FF4D4D>current state field is Required</font></b>.");
        $("#consult_CState").css("border", "1px solid red");
        
    }
    else{
        //alert("hello");
        $("#consult_CState").css("border", "1px solid green");
        $("addCnsltError").html("");
        $("consult_error").html("");
    }
    
}
// To check the character length of skills text area  add by Aklakh
function checkCharactersSkills(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 100){
            el.val( el.val().substr(0, 100) );
        } else {
            $("#skills_feedback").text(100-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==100)
        {
            $("#skills_feedback").text(' Cannot enter  more than 100 Characters .');    
        }
        
    })
    return false;
}
function consultantDateRepository(id)
{
    $(id).val(" ");
    return false;
}


// function to get the state of in addConsultant page add by Kamesh
function AddConsultantStateChange()
{
    // alert("Consultant ajax");
    var id=document.getElementById('consult_pcountry').value;
    if(id==-1){
        $("addCnsltError").html(" <b><font color=#FF4D4D>Preffered country field is Required</font></b>.");
        $("#consult_pcountry").css("border", "1px solid red");
    }
    else{
        
        $("#consult_pcountry").css("border", "1px solid green");
        $("addCnsltError").html("");
    }
    if($("div").is("#consult_preferredState"))
    {
        $("#consult_preferredState").remove();
        $(".pref_state").after('<select id="consult_preferredState" class=" " onchange="preState()" onclick="" multiple="multiple" tabindex="10" name="consult_preferredState "><option value="-1">Select preferred state</option></select>');
    }
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            AddConsultantStateChanging(req.responseText);
            document.getElementById("consult_preferredState").disabled = false;
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}


// function to set the state of in addConsultant page add by 
function AddConsultantStateChanging(data){
    //alert(data);
    var topicId = document.getElementById("consult_preferredState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_preferredState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
    
    $('#consult_preferredState').selectivity({
            
        multiple: true,
        placeholder: 'Type to search Categories'
                   
    });
    $("#consult_preferredState").show();
    $(".pref_state").show();
                
}





// function for change the prefrred state in edit consultant page
function AddConsultantEditPreferredStateChange(){
    // alert("Consultant ajax");
    document.getElementById("consult_preferredState").disabled = false;
    var id=document.getElementById('consult_preferredCountry').value;
    if($("div").is("#consult_preferredState"))
    {
        $("#consult_preferredState").remove();
        $(".pref_state").after('<select id="consult_preferredState" class=" " onchange="preState()" onclick="" multiple="multiple" tabindex="10" name="consult_preferredState "><option value="-1">Select preferred state</option></select>');
    }
    // alert(id);
    var url=CONTENXT_PATH+'/recruitment/consultant/getState.action?id='+id;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            // alert(req.responseText);
           
            AddpreferredState(req.responseText);
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function AddpreferredState(data){
    //alert(data);
    var topicId = document.getElementById("consult_preferredState");
    var flag=data.split("FLAG");
    var addList=flag[0].split("^");
    var $select = $('#consult_preferredState');
    $select.find('option').remove();   
    for(var i=0;i<addList.length-1;i++){        
        var Values=addList[i].split("#");
        {  
            
            $('<option>').val(Values[0]).text(Values[1]).appendTo($select); 
        }
    }
    $('#consult_preferredState').selectivity({
            
        multiple: true,
        placeholder: 'Type to search Categories'
                   
    });
    $("#consult_preferredState").show();
    $(".pref_state").show();
}

function getEmpRecruitment(){
    
    //alert(requirementId)
    var url;
   
    var v_empName=(document).getElementById('enameForRecruitment').value;
    //var v_empName=id.value;
    //alert("---"+id.valueOf());
    if (v_empName == "") {
        clearTable();
    } else {
        
        //alert("Empname->"+v_empName+"--len-->"+v_empName.length);
    
        if(v_empName.length>=1){
            //            if(interview=="I"){
            //                url=CONTENXT_PATH+"/getEmployeeDetails.action?empName="+v_empName+'&techReview=TR&requirementId='+requirementId;
            //            alert(url)
            //            }
            //            else{
                
            url=CONTENXT_PATH+"/getEmpRecruitment.action?empName="+v_empName;
            //alert(url)
            //            }
            //alert("url-->"+url);
            var req = initRequest(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        //alert(v_empName)
                        parseEmpForRecruitment(req.responseXML);
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

function parseEmpForRecruitment(responseXML) {
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
            //alert("alert alert")
            appendEmpForRecruitment(empName.childNodes[0].nodeValue,loginId.childNodes[0].nodeValue);
        }
        var position;
        position = findPosition(document.getElementById("enameForRecruitment"));
        
        //var position = findPosition(document.getElementById("assignedToUID"));
        posi = position.split(",");
        document.getElementById("menu-popup").style.left = posi[0]+"px";
        document.getElementById("menu-popup").style.top = (parseInt(posi[1])+20)+"px";
        document.getElementById("menu-popup").style.display = "block";
    } 
    if(chk.childNodes[0].nodeValue =="false") {
        // var validationMessage=document.getElementById("validationMessage");
        // validationMessage.innerHTML = "<font color=red>  Employee doesn't Exists </font>";
        $('#validationMessage').html(" <font color='red'>Employee doesn't Exists</font>");
        $('#validationMessage').show().delay(2000).fadeOut();
        document.getElementById("enameForRecruitment").value="";
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

function appendEmpForRecruitment(empName,loginId) {
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
        "javascript:setEmpRecruitment('"+empName +"','"+ loginId +"')");
    linkElement.appendChild(document.createTextNode(empName));
    linkElement["onclick"] = new Function("hideScrollBar()");
    nameCell.appendChild(linkElement);
}

function setEmpRecruitment(empName,loginId){
    clearTable();
    // alert("in set_cust");
    document.getElementById("enameForRecruitment").value =empName;
    document.getElementById("enameIdForRecruitment").value =loginId;
//alert(document.getElementById("enameIdForRecruitment").value)
}

function clearTable() {
    //alert("clearTable")
    if (completeTable) {
        completeTable.setAttribute("bordercolor", "white");
        completeTable.setAttribute("border", "0");
        completeTable.style.visible = false;
        var validationMessage=document.getElementById("validationMessage");
        validationMessage.innerHTML = " ";
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}


function jumper() {
           
    $.scrollUp({
        scrollName: 'scrollUp', // Element ID
        scrollDistance: 300, // Distance from top/bottom before showing element (px)
        scrollFrom: 'top', // 'top' or 'bottom'
        scrollSpeed: 300, // Speed back to top (ms)
        easingType: 'linear', // Scroll to top easing (see http://easings.net/)
        animation: 'fade', // Fade, slide, none
        animationSpeed: 200, // Animation in speed (ms)
        scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
        //scrollTarget: false, // Set a custom target element for scrolling to the top
        scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
        scrollTitle: false, // Set a custom <a> title if required.
        scrollImg: false, // Set true to use image
        activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
        zIndex: 2147483647 // Z-Index for the overlay
    });
};
        
        
function conDetailsCustomer()
{
     
     
              
    //alert("hello")
    var e = document.getElementById("consultantAvailable");
    var strUser = e.options[e.selectedIndex].text;
    //alert(strUser) 
          
    document.getElementById("consultantAvailableCustomer").innerHTML=strUser;
    if(document.getElementById("addressFlag").value!="emp")
    {
        var e1 = document.getElementById("consultantCountry");
        var strUser1 = e1.options[e1.selectedIndex].text;
        //alert(strUser) 
           
        document.getElementById("consultantLivingCountry").innerHTML=strUser1;    
    }
     
    var e2 = document.getElementById("consultantCountry");
    var strUser2 = e2.options[e2.selectedIndex].text;
    //alert(strUser) 
    document.getElementById("cosultantCountryCustomer").innerHTML=strUser2;    
    var e3 = document.getElementById("consultantState");
    var strUser3 = e3.options[e3.selectedIndex].text;
    //alert(strUser) 
    document.getElementById("cosultantCountryState").innerHTML=strUser3;
    var e4 = document.getElementById("cosultantCCountry");
    var strUser4 = e4.options[e4.selectedIndex].text;
    //alert(strUser) 
    document.getElementById("cosultantCCountryCustomer").innerHTML=strUser4;
    var e5 = document.getElementById("cosultantCState");
    var strUser5 = e5.options[e5.selectedIndex].text;
    //alert(strUser) 
    document.getElementById("cosultantCStateCustomer").innerHTML=strUser5;
        
}

function consultantVisaValidation(){
    var FileUploadPath = document.getElementById('visaAttachment').value;

    //To check if user upload any file
    if (FileUploadPath == '') {
       
        $("addCnsltError").html(" <b><font color='red'>Please upload a file</font></b>");
        return false;
    } else {
        var Extension = FileUploadPath.substring(
            FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

        if (Extension == "pdf" || Extension == "doc" || Extension == "docx" ) {
            $("addCnsltError").html(" ");
            return true;
        } 
        else {
            $("addCnsltError").html(" <b><font color='red'> Allows only doc,docx type or pdf.</font></b>");
            return false;
        }
    }
}
function openDialogforVisaAttachment(){
   // alert("hello")
    var specialBox = document.getElementById('visaAttachOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#consultVisaAttachment_popup').popup(      
        );

}

function visaAttachOverlayClose(){
    var specialBox = document.getElementById('visaAttachOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#consultVisaAttachment_popup').popup(      
        );
// getConsultantList();
}
function teAttachemntUpload(){
    
    var consultantId = $('#consultantId').val();
    
   // alert(consultantId);
    //    var imagePath =document.getElementById('consultVisaAttachment').value;
      
    //    if(imagePath == null || (imagePath =="")){
    //        document.getElementById('message').innerHTML = "<font color=red>please upload file</font>"
    //        return false;
    //    }
   
    
    // document.getElementById("load").style.display = 'block';
    $.ajaxFileUpload({
        url:'visaAttachemntUpload.action?consultantId='+consultantId,//
        secureuri:false,//false
        fileElementId:'file',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
            
            var displaymessage = "<font color=red>Please try again later</font>";
          
            if(data.indexOf("uploaded")>0){
               
                displaymessage = "<font color=green>Attachment uploaded Successfully.</font>";
                document.getElementById('proofdownloadDiv').style.display = "block";
                document.getElementById('proofuploadDiv').style.display = "none";
            }
            if(data.indexOf("Notvalid")>0){
                
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            if(data.indexOf("Error")>0){
               
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
           
            //   document.getElementById("load").style.display = 'none';
            document.getElementById('message').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        
        },
        error: function(e){
            
            // document.getElementById("load").style.display = 'none';
            document.getElementById('message').innerHTML = "<font color=red>Please try again later</font>";
       
        }
    });
    
   
    //}	
    return false;
}

function onloadIdProofDetails(){
   
    var consultantIdProof = document.getElementById('consultantIdProof').value;
   
    if(consultantIdProof=="VS"){
        document.getElementById('visaSelectDiv').style.display = "block";
    }
    else{
        document.getElementById('visaSelectDiv').style.display = "none"; 
    }
    var consultantIdProofAttach = document.getElementById('consultantIdProofAttach').value;
    //alert(consultantIdProofAttach);
    if(consultantIdProofAttach!=null && consultantIdProofAttach!=""){
        document.getElementById('proofdownloadDiv').style.display = "block";  
    }
    else{
        if(consultantIdProof=="0"){
            document.getElementById('proofuploadDiv').style.display = "none";     
            document.getElementById('proofdownloadDiv').style.display = "none"; 
        }
       
    }
    
}

function getVisaDetails(){
    var consultantId=document.getElementById('consultantId').value;
   // alert(consultantId+"-->consultantId")
    
    var consultantIdProof = document.getElementById('consultantIdProof').value;
   
    
    
    if(consultantIdProof=="VS"){
        document.getElementById('visaSelectDiv').style.display = "block";
    }
    else{
        document.getElementById('visaSelectDiv').style.display = "none"; 
    }
    var consultantIdProofAttach = document.getElementById('consultantIdProofAttach').value;
  //  alert(consultantIdProofAttach);
    if(consultantIdProofAttach==null || consultantIdProofAttach==""){
        document.getElementById('proofuploadDiv').style.display = "block";  
    }
   
    if(consultantIdProof=="0"){
        document.getElementById('proofuploadDiv').style.display = "none";     
        document.getElementById('proofdownloadDiv').style.display = "none"; 
    }
   
    var consultantIdProofhidden=document.getElementById('consultantIdProofhidden').value;
   // alert(consultantIdProofhidden);
    if(consultantIdProofAttach!=null && consultantIdProofAttach!=""){
        swal({
    
            title: "You will lose Current ID Proof. Do you want to continue..",
  
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
                var url='removeConsultantAttachement.action?consultantId='+consultantId;
                var req=initRequest(url);
                //alert(url);
                req.onreadystatechange = function() {
                    if (req.readyState == 4) {
                        if (req.status == 200) {
                            alert(req.responseText);
                            if(req.responseText=="1"){
                                document.getElementById('proofuploadDiv').style.display = "none"; 
                                document.getElementById('proofdownloadDiv').style.display = "none";
                                document.getElementById('consultantIdProofhidden').value="0";
                                document.getElementById('consultantIdProof').value="0";
                                document.getElementById('visaSelectDiv').style.display = "none"; 
                                document.getElementById('consultantIdProofAttach').value="";
                            }
                            // document.getElementById("outputMessage").innerHTML=req.responseText;
                            swal("Deleted!", "Deleted Successfully....", "success");
                        } 
                        else
                        {
                            swal("Sorry Not Deleted", "Deletion not done ", "error");
                        }
                    }
                };
                req.open("GET",url,"true");
                req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                req.send(null);
        
            }
            else {
     
                swal("Cancelled", "Deleted cancelled ", "error");
                document.getElementById('consultantIdProof').value=consultantIdProofhidden;
                if(consultantIdProofhidden=="VS"){
                    document.getElementById('visaSelectDiv').style.display = "block";
                }
                else{
                    document.getElementById('visaSelectDiv').style.display = "none"; 
                }
            }
        });
    }
    
}
    
    


function removeConsultantAttachement(){
    var consultantId=document.getElementById('consultantId').value;
   // alert(consultantId);
    swal({
    
        title: "You will lose Current ID Proof. Do you want to continue..",
  
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
            var url='removeConsultantAttachement.action?consultantId='+consultantId;
            var req=initRequest(url);
            //alert(url);
            req.onreadystatechange = function() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                       // alert(req.responseText);
                        if(req.responseText=="1"){
                            document.getElementById('proofuploadDiv').style.display = "none"; 
                            document.getElementById('proofdownloadDiv').style.display = "none";
                            document.getElementById('consultantIdProofhidden').value="0";
                            document.getElementById('consultantIdProof').value="0";
                            document.getElementById('visaSelectDiv').style.display = "none"; 
                            document.getElementById('consultantIdProofAttach').value="";
                        }
                        // document.getElementById("outputMessage").innerHTML=req.responseText;
                        swal("Deleted!", "Deleted Successfully....", "success");
                    } 
                    else
                    {
                        swal("Sorry Not Deleted", "Deletion not done ", "error");
                    }
                }
            };
            req.open("GET",url,"true");
            req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            req.send(null);
        }
        
    }); 
}
function displayHide_downloadButtons(){
var val = document.getElementById("rec_exits").value;
    if (val=="no"){
        $("#downloading_grid").css('display', 'none');
    }
    else{
        $("#downloading_grid").css('display', '');
    }
}