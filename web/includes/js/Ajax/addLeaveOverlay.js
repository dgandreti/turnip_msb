/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Date :04/17/2015 10:33PM 
 * Author:jagan<jchukkala@miraclesoft.com>
 */
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*   Add Leave  validation, start */


function addLeaveValidate(){
   
   
    // var skill_rate=document.getElementById("skill_rate").value;
    var val_fromleave= $("#fromleave").val();
    //alert("leaave from"+val_fromleave);
    var val_toleave= $("#toleave").val();
    var val_leaveType=$("#leavetype").val();
    //alert("leaave type"+val_leaveType);
    var val_reportsTo= $("#reportsTo").val();
    var val_reason= $("#reason").val();
    var val_leavestatus=$("#leavestatus").val();
    //alert(val_leavestatus)
    
    //    var startDate = new Date($('#fromleave').val());
    //    var endDate = new Date($('#toleave').val());

    
  
    
    if(val_fromleave==" " || val_fromleave==null){
        $("errorEduAdd").html(" <font color='red'>Leave From field is Required</font>.");
        $("#val_fromleave").css("border", "1px solid red");
        $("#fromleave").css("border","1px solid red");
        return false;
    }
    if(val_toleave==" " || val_toleave==null){
        $("errorEduAdd").html(" <font color='red'>Required To is Required</font>.");
        $("#val_toleave").css("border", "1px solid red");
        $("#toleave").css("border","1px solid red");
        return false;
    }
    
    if(val_leaveType=="" || val_leaveType==null){
        $("errorEduAdd").html(" <font color='red'>Leave Type is Required</font>.");
        $("#val_leaveType").css("border", "1px solid red");
        $("#leavetype").css("border","1px solid red");
        return false;
    }
    //    if(val_reportsTo==" " || val_reportsTo==null){
    //        $("errorEduAdd").html(" <b><font color='red'>start year is Required</font></b>.");
    //        $("#val_reportsTo").css("border", "1px solid red");
    //        $("#reportsTo").css("border","1px solid red");
    //        return false;
    //    }
    if(val_reason=="" || val_reason==null){
        $("errorEduAdd").html(" <font color='red'>Reason is Required</font>.");
        $("#val_reason").css("border", "1px solid red");
        $("#reason").css("border","1px solid red");
        return false;
    }
    if(val_leavestatus=="" || val_leavestatus==null){
        $("errorEduAdd").html(" <font color='red'>leave status  is Required</font>.");
        $("#val_leavestatus").css("border", "1px solid red");
        $("#leavestatus").css("border","1px solid red");
        return false;
    }
    if(val_fromleave > val_toleave){
        //alert("start"+"val_year_start"+"----end----"+"val_year_end");
        $("errorEduAdd").html(" <font color='red'>start date must be less than end date</font>.");
        $("#val_fromleave").css("border", "1px solid red");
        $("#val_fromleave").css("border","1px solid red");
        $("#val_toleave").css("border","1px solid red");
        return false;
    }
    if( val_reportsTo==null){
        $("errorEduAdd").html(" <font color='red'>You dont have any reporting person</font>.");
        $("#val_reportsTo").css("border", "1px solid red");
        $("#reportsTo").css("border","1px solid red");
        return false;
    }
    var addStartDate = Date.parse(val_fromleave);
    var addEndDate = Date.parse(val_toleave);

    var difference = (addEndDate - addStartDate) / (86400000 * 7);
    if (difference < 0) {
        // alert("The start date must come before the end date.");
        $("errorEduAdd").html(" <font color='red'>start date must be less than end date</font>.");
        $("#val_fromleave").css("border", "1px solid red");
        $("#val_fromleave").css("border","1px solid red");
        $("#val_toleave").css("border","1px solid red");
        return false;
    }

    
    
    else
    {
        $("errorEduAdd").html("");
        $("#fromleave").css("border", "1px solid #3BB9FF");
        $("#val_fromleave").css("border", "1px solid #3BB9FF");
        $("#toleave").css("border", "1px solid #3BB9FF");
        $("#val_toleave").css("border", "1px solid #3BB9FF");
        $("#val_leaveType").css("border", "1px solid #3BB9FF");
        $("#leavetype").css("border", "1px solid #3BB9FF");
        $("#val_reportsTo").css("border", "1px solid #3BB9FF");
        $("#reportsTo").css("border", "1px solid #3BB9FF");
        $("#reason").css("border", "1px solid #3BB9FF");
        $("#val_reason").css("border", "1px solid #3BB9FF");
        $("#leavestatus").css("border", "1px solid #3BB9FF");
        $("#val_leavestatus").css("border", "1px solid #3BB9FF");
        
        return true;
    }
       
}
function addLeave()
{
    //  alert("called")
    var fromleave=$("#fromleave").val();
    var toleave= $("#toleave").val();
    //alert(fromleave)
    //alert(toleave)
    var leavetype= $("#leavetype").val();
    var reportsTo=$("#reportsTo").val();
    var reason=$("#reason").val();
    var leavestatus=$("#leavestatus").val();
    
    // alert(userid+" "+qualification+" "+percentage+" "+university+" "+year_start+" "+year_end+" "+institution+" "+specialization)
    //var url='../general/addQualification.action?userid='+userid+'&qualification='+qualification +'&year_start='+year_start +'&year_end='+year_end +'&percentage='+percentage +'&university='+university +'&institution='+institution +'&specialization='+specialization ;
    //var url='../general/addQualification.action?userid='+userid+'&qualification='+qualification+'&percentage='+percentage+'&university='+university+'&year_start='+year_start+'&year_end='+year_end+'&institution='+institution+'&specialization='+specialization;
    if(addLeaveValidate()){
        var url='../general/addLeave.action?fromleave='+fromleave+'&toleave='+toleave+'&leavetype='+leavetype+'&reportsTo='+reportsTo+'&reason='+reason+'&leavestatus='+leavestatus;
        //+'&year_start='+year_start+'&year_end='+year_end;
        // alert(url)
        //alert(url)
        var req=initRequest(url);
        req.onreadystatechange = function() {
            if (req.readyState == 4) {
                if (req.status == 200) {
                   
                    $("errorEduAdd").html(" <font color='green'>Record successfully Added</font>");
                    doGetLeavesData();
                //                    showEducationDetails(userid);
                } 
                else
                {
                    $("edu").html(" <font color='red'>Record not Added</font>");
                //  alert("Error occured");
                }
            }
        };
        req.open("GET",url,"true");
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.send(null);
    }
    return false;
}
function enterDateRepository()
{
    // alert(document.documentForm.docdatepickerfrom.value);
    // document.documentForm.docdatepickerfrom.value="";
    // document.getElementById('dob').value = "";
    //document.getElementById('leavefrom').value = "";
    //document.getElementById('end_date').value = "";
    //alert("Please select from the Calender !");
    return false;
}

function onStartDate(){
     
    var addDate=document.getElementById("year_start").value;
    eduCalendar.setSkin('omega');   
    eduCalendar.setDate(addDate);  
      
    eduCalendar.setDateFormat("%m/%d/%Y");   
    eduCalendar.hideTime(); 
    //alert(sDate);
    //eduCalendar.("year_end").set_selectedDate(addDate);
    eduCalendar.setInsensitiveRange(null, addDate);
}
function checkReportedPerson()
{
    var reportingPerson=document.getElementById("reportingPerson").value;
    //alert("reporting person is"+reportingPerson);
    if(reportingPerson==" "|| reportingPerson==null)
    {
        alert("You dont have Reporting person please contact support team");
       
    }
    else
    {
        //alert("You  have Reporting person record");
        var specialBox = document.getElementById('leaveBoxOverlay');
        if(specialBox.style.display == "block"){       
        //specialBox.style.display = "none";         
        } 
        else {
            specialBox.style.display = "block";      
        }
        // Initialize the plugin    
        $('#leave_popup').popup(      
            );
    //         onClose: function(){
    //            window.location.reload(false);
    //        }   

    }
      
    return false;
}
function checkRange(){
    $("#reason").keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#charNum").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#charNum").text('Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
};
function checkCharacters(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 200){
            el.val( el.val().substr(0, 200) );
        } else {
            $("#charNum").text(200-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==200)
        {
            $("#charNum").text(' Cannot enter  more than 200 Characters .');    
        }
        
    })
    return false;
};
//added by jagan
function clearLeaveOverlay(){
    document.forms["leaveOverlayForm"].reset();
    $("#charNum").text('');
}
function  doGetLeavesData()
{
    //alert("getting leaves"); 
    var leavetype= $("#leavetype").val();
    var leavestatus=$("#leavestatus").val();
    var url='../general/getLeavesList.action';
   // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
         //   alert(req.responseText)
            populateLeaves(req.responseText)
        } 
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}
function populateLeaves(response)
{
   
    var eduList=response.split("^");
   // alert("eduList is---->"+eduList);
    var myLeaveFlag="myLeaveFlag";
    var RecordsData ='<table id="leaves_results" class="responsive CSSTable_task" border="5"cell-spacing="2"><tr><th>Leave Id</th><th>Start Date</th><th >End Date</th><th>Leave Status</th><th>Leave Type</th><th>Reports To</th><th>Approved By</th></tr>';
    
    for(var i=0;i<eduList.length-1;i++){        
        var Values=eduList[i].split("|");
        {  
                                                         
           
          //  alert("Get ready ")
            RecordsData = RecordsData+'<tr><td><s:url action="../leaves/editLeaves.action"><s:param name="leave_id"></s:param></s:url>'+

            '<a href="editLeaves.action?leave_id='+Values[0]+'&leaveflag='+myLeaveFlag+'"><img width="20" height="20" src="'+CONTENXT_PATH+'/includes/images/edit_icon.png"></td></a>'+
            '<td>'+Values[1]+'</td>'+
            '<td>'+Values[2]+'</td>'+
            '<td>'+Values[3]+'</td>'+
            '<td>'+Values[4]+'</td>'+
            '<td>'+Values[5]+'</td>'+
            '<td>'+Values[6]+'</td></tr>';                  
        }
    }
    
    RecordsData=RecordsData+ '</table>';    
    leaves_results.innerHTML=RecordsData;
    
    pager.init(); 
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);


}
