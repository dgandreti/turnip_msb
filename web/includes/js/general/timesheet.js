/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var myCalendar,myCalendar1;
function doOnloadTimeSheets()
{
    
    myCalendar1 = new dhtmlXCalendarObject();
    // alert("hii1");
    myCalendar1.setSkin('omega');
    // alert("hii2");
    
    // myCalendar1.hide("timeSheetStartDate");
    myCalendar1.setDateFormat("%m-%d-%Y");
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    
    var submitDate= mm+'-'+dd+'-'+yyyy;
    if(dd<10) {
        dd='0'+dd
    } 
    if(mm<10) {
        mm='0'+mm
    } 
    var dd1 = today.getDay();
    if(dd1==0)
    {
        dd = today.getDate();
    ///alert(dd)  
    } 
    if(dd1==1)
    {
        dd = today.getDate()-1;
    // alert(dd)  
    } 
    if(dd1==2)
    {
        dd = today.getDate()-2;
    // alert(dd)  
    } 
    if(dd1==3)
    {
        dd = today.getDate()-3;
    // alert(dd)  
    }
    if(dd1==4)
    {
        dd = today.getDate()-4;
    //alert(dd)  
    }
    if(dd1==5)
    {
        dd = today.getDate()-5;
    // alert(dd)  
    }
    if(dd1==6)
    {
        dd = today.getDate()-6;
    // alert(dd)  
    } 
    //var from = yyyy+'/'+mm+'/01';
     
    var from = mm+'-'+dd+'-'+yyyy;
    var from1=mm+'/'+dd+'/'+yyyy;
   
       
    var weekDay1=from1;
    dd = dd+1;
    var weekDay2=mm+'/'+dd+'/'+yyyy;
    dd = dd+1;
    var weekDay3=mm+'/'+dd+'/'+yyyy;
    dd = dd+1;
    var weekDay4=mm+'/'+dd+'/'+yyyy;
    dd = dd+1;
    var weekDay5=mm+'/'+dd+'/'+yyyy;
    dd = dd+1;
    var weekDay6=mm+'/'+dd+'/'+yyyy;
    dd = dd+1;
    var weekDay7=mm+'/'+dd+'/'+yyyy;
    var endDate=mm+'-'+dd+'-'+yyyy;
    mm=today.getMonth()+1;
    if(mm<10) {
        mm='0'+mm
    } 
    dd=today.getDate()+7;
    dd1 = today.getDay();
    if(dd1==0)
    {
        dd = today.getDate();
    // alert(dd)  
    } 
    if(dd1==1)
    {
        dd = today.getDate()+1;
    // alert(dd)  
    } 
    if(dd1==2)
    {
        dd = today.getDate()+2;
    // alert(dd)  
    } 
    if(dd1==3)
    {
        dd = today.getDate()+3;
    // alert(dd)  
    }
    if(dd1==4)
    {
        dd = today.getDate()+4;
    // alert(dd)  
    }
    if(dd1==5)
    {
        dd = today.getDate()+5;
    // alert(dd)  
    }
    if(dd1==6)
    {
        dd = today.getDate()+6;
    // alert(dd)  
    } 
    var to = mm+'-'+dd+'-'+yyyy;
    
    dd = today.getDate();
    //alert(dd)  
         
    var weekDate=mm+'-'+dd+'-'+yyyy;
    dd = today.getDate()-1;
    var over = mm+'-'+dd+'-'+yyyy;
    var odd=today.getDate();
    var overlayDate=mm+'-'+odd+'-'+yyyy;
    document.getElementById("timeSheetStartDate").value=from;
    
    
    //alert(weekDate)
    document.getElementById("weeklyDates1").value=weekDay1;
    document.getElementById("weeklyDates2").value=weekDay2;
    document.getElementById("weeklyDates3").value=weekDay3;
    document.getElementById("weeklyDates4").value=weekDay4;
    document.getElementById("weeklyDates5").value=weekDay5;
    document.getElementById("weeklyDates6").value=weekDay6;
    document.getElementById("weeklyDates7").value=weekDay7;
    document.getElementById("timeSheetEndDate").value=endDate;
    document.getElementById("timeSheetSubmittedDate").value=submitDate;
}
function getDefault()
{   
    var dec= 0.0;
    var num = 0.0;
    var num1=num.toFixed(1);
    //document.getElementById(id).value=num1;
   
    var projectNameSun1=document.getElementById("projectNameSun1").value;
    var projectNameMon1=document.getElementById("projectNameMon1").value;
    var projectNameTue1=document.getElementById("projectNameTue1").value;
    var projectNameWed1=document.getElementById("projectNameWed1").value;
    var projectNameThu1=document.getElementById("projectNameThu1").value;
    var projectNameFri1=document.getElementById("projectNameFri1").value;
    var projectNameSat1=document.getElementById("projectNameSat1").value;
    var projectNameall1=document.getElementById("projectNameAll1").value;
   
    var projectNameSun2=document.getElementById("projectNameSun2").value;
    var projectNameMon2=document.getElementById("projectNameMon2").value;
    var projectNameTue2=document.getElementById("projectNameTue2").value;
    var projectNameWed2=document.getElementById("projectNameWed2").value;
    var projectNameThu2=document.getElementById("projectNameThu2").value;
    var projectNameFri2=document.getElementById("projectNameFri2").value;
    var projectNameSat2=document.getElementById("projectNameSat2").value;
    var projectNameall2=document.getElementById("projectNameAll2").value;
   
    var projectNameSun3=document.getElementById("projectNameSun3").value;
    var projectNameMon3=document.getElementById("projectNameMon3").value;
    var projectNameTue3=document.getElementById("projectNameTue3").value;
    var projectNameWed3=document.getElementById("projectNameWed3").value;
    var projectNameThu3=document.getElementById("projectNameThu3").value;
    var projectNameFri3=document.getElementById("projectNameFri3").value;
    var projectNameSat3=document.getElementById("projectNameSat3").value;
    var projectNameall3=document.getElementById("projectNameAll3").value;
   
    var projectNameSun4=document.getElementById("projectNameSun4").value;
    var projectNameMon4=document.getElementById("projectNameMon4").value;
    var projectNameTue4=document.getElementById("projectNameTue4").value;
    var projectNameWed4=document.getElementById("projectNameWed4").value;
    var projectNameThu4=document.getElementById("projectNameThu4").value;
    var projectNameFri4=document.getElementById("projectNameFri4").value;
    var projectNameSat4=document.getElementById("projectNameSat4").value;
    var projectNameall4=document.getElementById("projectNameAll4").value;
   
    var projectNameSun5=document.getElementById("projectNameSun5").value;
    var projectNameMon5=document.getElementById("projectNameMon5").value;
    var projectNameTue5=document.getElementById("projectNameTue5").value;
    var projectNameWed5=document.getElementById("projectNameWed5").value;
    var projectNameThu5=document.getElementById("projectNameThu5").value;
    var projectNameFri5=document.getElementById("projectNameFri5").value;
    var projectNameSat5=document.getElementById("projectNameSat5").value;
    var projectNameall5=document.getElementById("projectNameAll5").value;
   
    var vacationSun=document.getElementById("vacationSun").value;
    var vacationMon=document.getElementById("vacationMon").value;
    var vacationTue=document.getElementById("vacationTue").value;
    var vacationWed=document.getElementById("vacationWed").value;
    var vacationThu=document.getElementById("vacationThu").value;
    var vacationFri=document.getElementById("vacationFri").value;
    var vacationSat=document.getElementById("vacationSat").value;
    var vacationall=document.getElementById("vacationAll").value;
   
    var holidaySun=document.getElementById("holidaySun").value;
    var holidayMon=document.getElementById("holidayMon").value;
    var holidayTue=document.getElementById("holidayTue").value;
    var holidayWed=document.getElementById("holidayWed").value;
    var holidayThu=document.getElementById("holidayThu").value;
    var holidayFri=document.getElementById("holidayFri").value;
    var holidaySat=document.getElementById("holidaySat").value;
    var holidayall=document.getElementById("holidayAll").value;
   
    var internalSun=document.getElementById("internalSun").value;
    var internalMon=document.getElementById("internalMon").value;
    var internalTue=document.getElementById("internalTue").value;
    var internalWed=document.getElementById("internalWed").value;
    var internalThu=document.getElementById("internalThu").value;
    var internalFri=document.getElementById("internalFri").value;
    var internalSat=document.getElementById("internalSat").value;
    var internalall=document.getElementById("internalAll").value;
   
    if(internalSun==0||internalSun=="")
        document.getElementById("internalSun").value=num1;
    if(internalMon==0||internalMon=="")
        document.getElementById("internalMon").value=num1;
    if(internalTue==0||internalTue=="")
        document.getElementById("internalTue").value=num1;
    if(internalWed==0||internalWed=="")
        document.getElementById("internalWed").value=num1;
    if(internalThu==0||internalThu=="")
        document.getElementById("internalThu").value=num1;
    if(internalFri==0||internalFri=="")
        document.getElementById("internalFri").value=num1;
    if(internalSat==0||internalSat=="")
        document.getElementById("internalSat").value=num1;
    if(internalall==0||internalall=="")
        document.getElementById("internalAll").value=num1;
   
    if(projectNameSun1==0||projectNameSun1=="")
        document.getElementById("projectNameSun1").value=num1;
    if(projectNameMon1==0||projectNameMon1=="")
        document.getElementById("projectNameMon1").value=num1;
    if(projectNameTue1==0||projectNameTue1=="")
        document.getElementById("projectNameTue1").value=num1;
    if(projectNameWed1==0||projectNameWed1=="")
        document.getElementById("projectNameWed1").value=num1;
    if(projectNameThu1==0||projectNameThu1=="")
        document.getElementById("projectNameThu1").value=num1;
    if(projectNameFri1==0||projectNameFri1=="")
        document.getElementById("projectNameFri1").value=num1;
    if(projectNameSat1==0||projectNameSat1=="")
        document.getElementById("projectNameSat1").value=num1;
    if(projectNameall1==0||projectNameall1=="")
        document.getElementById("projectNameAll1").value=num1;
   
    if(projectNameSun2==0||projectNameSun2=="")
        document.getElementById("projectNameSun2").value=num1;
    if(projectNameMon2==0||projectNameMon2=="")
        document.getElementById("projectNameMon2").value=num1;
    if(projectNameTue2==0||projectNameTue2=="")
        document.getElementById("projectNameTue2").value=num1;
    if(projectNameWed2==0||projectNameWed2=="")
        document.getElementById("projectNameWed2").value=num1;
    if(projectNameThu2==0||projectNameThu2=="")
        document.getElementById("projectNameThu2").value=num1;
    if(projectNameFri2==0||projectNameFri2=="")
        document.getElementById("projectNameFri2").value=num1;
    if(projectNameSat2==0||projectNameSat2=="")
        document.getElementById("projectNameSat2").value=num1;
    if(projectNameall2==0||projectNameall2=="")
        document.getElementById("projectNameAll2").value=num1;
   
    if(projectNameSun3==0||projectNameSun3=="")
        document.getElementById("projectNameSun3").value=num1;
    if(projectNameMon3==0||projectNameMon3=="")
        document.getElementById("projectNameMon3").value=num1;
    if(projectNameTue3==0||projectNameTue3=="")
        document.getElementById("projectNameTue3").value=num1;
    if(projectNameWed3==0||projectNameWed3=="")
        document.getElementById("projectNameWed3").value=num1;
    if(projectNameThu3==0||projectNameThu3=="")
        document.getElementById("projectNameThu3").value=num1;
    if(projectNameFri3==0||projectNameFri3=="")
        document.getElementById("projectNameFri3").value=num1;
    if(projectNameSat3==0||projectNameSat3=="")
        document.getElementById("projectNameSat3").value=num1;
    if(projectNameall3==0||projectNameall3=="")
        document.getElementById("projectNameAll3").value=num1;
   
    if(projectNameSun4==0||projectNameSun4=="")
        document.getElementById("projectNameSun4").value=num1;
    if(projectNameMon4==0||projectNameMon4=="")
        document.getElementById("projectNameMon4").value=num1;
    if(projectNameTue4==0||projectNameTue4=="")
        document.getElementById("projectNameTue4").value=num1;
    if(projectNameWed4==0||projectNameWed4=="")
        document.getElementById("projectNameWed4").value=num1;
    if(projectNameThu4==0||projectNameThu4=="")
        document.getElementById("projectNameThu4").value=num1;
    if(projectNameFri4==0||projectNameFri4=="")
        document.getElementById("projectNameFri4").value=num1;
    if(projectNameSat4==0||projectNameSat4=="")
        document.getElementById("projectNameSat4").value=num1;
    if(projectNameall4==0||projectNameall4=="")
        document.getElementById("projectNameAll4").value=num1;
   
    if(projectNameSun5==0||projectNameSun5=="")
        document.getElementById("projectNameSun5").value=num1;
    if(projectNameMon5==0||projectNameMon5=="")
        document.getElementById("projectNameMon5").value=num1;
    if(projectNameTue5==0||projectNameTue5=="")
        document.getElementById("projectNameTue5").value=num1;
    if(projectNameWed5==0||projectNameWed5=="")
        document.getElementById("projectNameWed5").value=num1;
    if(projectNameThu5==0||projectNameThu5=="")
        document.getElementById("projectNameThu5").value=num1;
    if(projectNameFri5==0||projectNameFri5=="")
        document.getElementById("projectNameFri5").value=num1;
    if(projectNameSat5==0||projectNameSat5=="")
        document.getElementById("projectNameSat5").value=num1;
    if(projectNameall5==0||projectNameall5=="")
        document.getElementById("projectNameAll5").value=num1;
   
    if(vacationSun==0||vacationSun=="")
        document.getElementById("vacationSun").value=num1;
    if(vacationMon==0||vacationMon=="")
        document.getElementById("vacationMon").value=num1;
    if(vacationTue==0||vacationTue=="")
        document.getElementById("vacationTue").value=num1;
    if(vacationWed==0||vacationWed=="")
        document.getElementById("vacationWed").value=num1;
    if(vacationThu==0||vacationThu=="")
        document.getElementById("vacationThu").value=num1;
    if(vacationFri==0||vacationFri=="")
        document.getElementById("vacationFri").value=num1;
    if(vacationSat==0||vacationSat=="")
        document.getElementById("vacationSat").value=num1;
    if(vacationall==0||vacationall=="")
        document.getElementById("vacationAll").value=num1;
   
    if(holidaySun==0||holidaySun=="")
        document.getElementById("holidaySun").value=num1;
    if(holidayMon==0||holidayMon=="")
        document.getElementById("holidayMon").value=num1;
    if(holidayTue==0||holidayTue=="")
        document.getElementById("holidayTue").value=num1;
    if(holidayWed==0||holidayWed=="")
        document.getElementById("holidayWed").value=num1;
    if(holidayThu==0||holidayThu=="")
        document.getElementById("holidayThu").value=num1;
    if(holidayFri==0||holidayFri=="")
        document.getElementById("holidayFri").value=num1;
    if(holidaySat==0||holidaySat=="")
        document.getElementById("holidaySat").value=num1;
    if(holidayall==0||holidayall=="")
        document.getElementById("holidayAll").value=num1;
   
}

function getProjets()
{
    //alert("hello"); 
     var usr_id=document.getElementById("usr_id").value;
    var timesheetFlag=document.getElementById("timesheetFlag").value;
    //alert(usr_id);
    var url="../timesheets/getProjects.action?usr_id="+usr_id+"&timesheetFlag="+timesheetFlag;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert("get the data");
                // alert(req.responseText);
                populateProjects(req.responseText);
                $("errorEduAdd").html(" <b><font color='green'>Record successfully Added</font></b>");
            // doGetLeavesData();
            //                    showEducationDetails(userid);
            } 
            else
            {
                $("edu").html(" <b><font color='red'>Record not Added</font></b>");
            //  alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}
function populateProjects(response)
{
    var projectList=response.split("^");
    //alert("projectList is---->"+projectList);
    for(var i=0;i<projectList.length;i++){
        //          {
        //              var value=projectList[i].split("^");
        //              
        //              for(i=0;i<=projectList.length;i++)
        //                 {
        if(i==5)
            break;
        if(i==0)
        {  
                     
            var project1=projectList[0].split("|");
                       
            document.getElementById("project1").value=project1[1];
            document.getElementById("projectOver1").value=project1[1];
            document.getElementById("project1key").value=project1[0];
            // alert(project1[0]);
            if(project1[1]!=null)
            {
                hideRow("projectid1");
                   
                showRow("checkbox1");
                showRow("projectButton");
                if(document.getElementById("projectNameAll1").value>0.0)
                    document.getElementById('p1').checked=true;
            }
        //document.getElementById("project1").innerHtml="<b><font color='green'>"+project1[1]+"</font></b>";
                      
        }
        if(i==1)
        {
            var project2=projectList[1].split("|");
            document.getElementById("project2").value=project2[1];
                     
            document.getElementById("projectOver2").value=project2[1];
            document.getElementById("project2key").value=project2[0];
            if(project2[1]!=null)
            {
                hideRow("projectid2");
                showRow("checkbox2");
                showRow("projectButton");
                if(document.getElementById("projectNameAll2").value>0.0)
                    document.getElementById('p2').checked=true;  
                      
            }                    //alert(project2[1]);
        }
        if(i==2)
        {
            var project3=projectList[2].split("|");
            document.getElementById("project3").value=project3[1];
            document.getElementById("projectOver3").value=project3[1];
            document.getElementById("project3key").value=project3[0];
            if(project3[1]!=null)
            {
                hideRow("projectid3");
                showRow("checkbox3");
                showRow("projectButton");
                if(document.getElementById("projectNameAll3").value>0.0)
                    document.getElementById('p3').checked=true;
            }
        }
        if(i==3)
        {
            var project4=projectList[3].split("|");
            document.getElementById("project4").value=project4[1];
            document.getElementById("projectOver4").value=project4[1];
            document.getElementById("project4key").value=project4[0];
            if(project4[1]!=null) 
            {
                hideRow("projectid4");
                showRow("checkbox4");
                showRow("projectButton");
                if(document.getElementById("projectNameAll4").value>0.0)
                    document.getElementById('p4').checked=true;
            }
        }
        if(i==4)
        {
            var project5=projectList[4].split("|");
            document.getElementById("project5").value=project5[1];
            // alert(project5[0]);
            document.getElementById("projectOver5").value=project5[1];                    
            document.getElementById("project5key").value=project5[0];
            if(project5[1]!=null){
                hideRow("projectid5");
                showRow("checkbox5");
                showRow("projectButton");
                if(document.getElementById("projectNameAll5").value>0.0)
                    document.getElementById('p5').checked=true;
            }
        }
    // $("project1").html("").innerHTML=project1;
    // alert(project1);
    // document.getElementById("project1").innerHtml="<b><font color='green'>"+project1[1]+"</font></b>";
              
    }
    
    
}

function acceptNumbers(evt){
    removeErrorMessages();
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
    {
        $("edittimesheetserror").html(" <b><font color='green'>enter only numbers</font></b>");
        return false;
    }
    else
    {
                    
        $("edittimesheetserror").html("");
        return true;
    }
};
function hideRow(id) {
    var row = document.getElementById(id);
    row.style.display = 'none';
}
function showRow(id) {
    var row = document.getElementById(id);
    row.style.display = '';
}  
function projectSun1(){
    //alert("div");
    var projectNameSun=$("#projectNameSun1").val();
    var sundays,alldays;
    var all=$("#projectNameAll1").val();
    var totalSun=$("#totalSun").val();
    if(projectNameSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSun1").css("border", "1px solid red");
        document.getElementById('projectNameSun1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSun1").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalProjectDays1();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun1').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun1').value= 0.0;
        totalProjectDays1();
        totalSunday();
        return false;
    }
    return true;    
};
function projectSun2(){
    //alert("div");
    var projectNameSun=$("#projectNameSun2").val();
    var sundays,alldays;
    var all=$("#projectNameAll2").val();
    var totalSun=$("#totalSun").val();
    if(projectNameSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSun2").css("border", "1px solid red");
        document.getElementById('projectNameSun2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSun2").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalProjectDays2();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun2').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun2').value= 0.0;
        totalProjectDays2();
        totalSunday();
        return false;
    }
    return true;    
};
function projectSun3(){
    //alert("div");
    var projectNameSun=$("#projectNameSun3").val();
    var sundays,alldays;
    var all=$("#projectNameAll3").val();
    var totalSun=$("#totalSun").val();
    if(projectNameSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSun3").css("border", "1px solid red");
        document.getElementById('projectNameSun3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSun3").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalProjectDays3();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun3').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun3').value= 0.0;
        totalProjectDays3();
        totalSunday();
        return false;
    }
    return true;    
};
function projectSun4(){
    //alert("div");
    var projectNameSun=$("#projectNameSun4").val();
    var sundays,alldays;
    var all=$("#projectNameAll4").val();
    var totalSun=$("#totalSun").val();
    if(projectNameSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSun4").css("border", "1px solid red");
        document.getElementById('projectNameSun4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSun4").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalProjectDays4();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun4').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun4').value= 0.0;
        totalProjectDays4();
        totalSunday();
        return false;
    }
    return true;    
};
function projectSun5(){
    //alert("div");
    var projectNameSun=$("#projectNameSun5").val();
    var sundays,alldays;
    var all=$("#projectNameAll5").val();
    var totalSun=$("#totalSun").val();
    if(projectNameSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSun5").css("border", "1px solid red");
         document.getElementById('projectNameSun5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSun5").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun5').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSun5').value= 0.0;
        totalProjectDays5();
        totalSunday();
        return false;
    }
    return true;    
};
function projectMon1()
{
    var projectNameMon=$("#projectNameMon1").val();
    var totalMon=$("#totalMon").val();
    var Mondays,alldays;
    var all=$("#projectNameAll1").val();
    if(projectNameMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameMon1").css("border", "1px solid red");
        document.getElementById('projectNameMon1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameMon1").css("border", "1px solid #D2D2D2");
    } 
    Mondays=totalMonday();
    alldays=totalProjectDays1();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon1').value= 0.0;
        totalMonday();
        return false;
    }
    else if(Mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon1').value= 0.0;
        totalProjectDays1();
        totalMonday();
        return false;
    }
    return true;
}
function projectMon2()
{
    var projectNameMon=$("#projectNameMon2").val();
    var totalMon=$("#totalMon").val();
    var Mondays,alldays;
    var all=$("#projectNameAll2").val();
    if(projectNameMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameMon2").css("border", "1px solid red");
         document.getElementById('projectNameMon2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameMon2").css("border", "1px solid #D2D2D2");
    } 
    Mondays=totalMonday();
    alldays=totalProjectDays2();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon2').value= 0.0;
        totalMonday();
        return false;
    }
    else if(Mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon2').value= 0.0;
        totalProjectDays2();
        totalMonday();
        return false;
    }
    return true;
}
function projectMon3()
{
    var projectNameMon=$("#projectNameMon3").val();
    var totalMon=$("#totalMon").val();
    var Mondays,alldays;
    var all=$("#projectNameAll3").val();
    if(projectNameMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameMon3").css("border", "1px solid red");
        document.getElementById('projectNameMon3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameMon3").css("border", "1px solid #D2D2D2");
    } 
    Mondays=totalMonday();
    alldays=totalProjectDays3();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon3').value= 0.0;
        totalMonday();
        return false;
    }
    else if(Mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon3').value= 0.0;
        totalProjectDays3();
        totalMonday();
        return false;
    }
    return true;
}
function projectMon4()
{
    var projectNameMon=$("#projectNameMon4").val();
    var totalMon=$("#totalMon").val();
    var Mondays,alldays;
    var all=$("#projectNameAll4").val();
    if(projectNameMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameMon4").css("border", "1px solid red");
        document.getElementById('projectNameMon4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameMon4").css("border", "1px solid #D2D2D2");
    } 
    Mondays=totalMonday();
    alldays=totalProjectDays4();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon4').value= 0.0;
        totalMonday();
        return false;
    }
    else if(Mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon4').value= 0.0;
        totalProjectDays4();
        totalMonday();
        return false;
    }
    return true;
}

function projectMon5()
{
    var projectNameMon=$("#projectNameMon5").val();
    var totalMon=$("#totalMon").val();
    var Mondays,alldays;
    var all=$("#projectNameAll5").val();
    if(projectNameMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameMon5").css("border", "1px solid red");
        document.getElementById('projectNameMon5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameMon5").css("border", "1px solid #D2D2D2");
    } 
    Mondays=totalMonday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon5').value= 0.0;
        totalMonday();
        return false;
    }
    else if(Mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameMon5').value= 0.0;
        totalProjectDays5();
        totalMonday();
        return false;
    }
    return true;
}

function projectTue1()
{
    var projectNameTue=$("#projectNameTue1").val();
    var all=$("#projectNameAll1").val();
    var totalTue=$("#totalTue").val();
    var tuesdays,alldays;
    if(projectNameTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameTue1").css("border", "1px solid red");
        document.getElementById('projectNameTue1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameTue1").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalProjectDays1();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue1').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue1').value= 0.0;
        totalProjectDays1();
        totalTuesday();
        return false;
    }
    return true;
}
function projectTue2()
{
    var projectNameTue=$("#projectNameTue2").val();
    var all=$("#projectNameAll2").val();
    var totalTue=$("#totalTue").val();
    var tuesdays,alldays;
    if(projectNameTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameTue2").css("border", "1px solid red");
        document.getElementById('projectNameTue2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameTue2").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalProjectDays2();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue2').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue2').value= 0.0;
        totalProjectDays2();
        totalTuesday();
        return false;
    }
    return true;
}
function projectTue3()
{
    var projectNameTue=$("#projectNameTue3").val();
    var all=$("#projectNameAll3").val();
    var totalTue=$("#totalTue").val();
    var tuesdays,alldays;
    if(projectNameTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameTue3").css("border", "1px solid red");
        document.getElementById('projectNameTue3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameTue3").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalProjectDays3();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue3').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue3').value= 0.0;
        totalProjectDays3();
        totalTuesday();
        return false;
    }
    return true;
}
function projectTue4()
{
    var projectNameTue=$("#projectNameTue4").val();
    var all=$("#projectNameAll4").val();
    var totalTue=$("#totalTue").val();
    var tuesdays,alldays;
    if(projectNameTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameTue4").css("border", "1px solid red");
        document.getElementById('projectNameTue4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameTue4").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalProjectDays4();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue4').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue4').value= 0.0;
        totalProjectDays4();
        totalTuesday();
        return false;
    }
    return true;
}
function projectTue5()
{
    var projectNameTue=$("#projectNameTue5").val();
    var all=$("#projectNameAll5").val();
    var totalTue=$("#totalTue").val();
    var tuesdays,alldays;
    if(projectNameTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameTue5").css("border", "1px solid red");
        document.getElementById('projectNameTue5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameTue5").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue5').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameTue5').value= 0.0;
        totalProjectDays5();
        totalTuesday();
        return false;
    }
    return true;
}
function projectWed1()
{
    var projectNameWed=$("#projectNameWed1").val();
    var all=$("#projectNameAll1").val();
    var totalWed=$("#totalWed").val();
    var wednesdays,alldays;
    if(projectNameWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameWed1").css("border", "1px solid red");
        document.getElementById('projectNameWed1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameWed1").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalProjectDays1();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed1').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed1').value= 0.0;
        totalProjectDays1();
        totalWednesday();
        return false;
    }
    return true;
}
function projectWed2()
{
    var projectNameWed=$("#projectNameWed2").val();
    var all=$("#projectNameAll2").val();
    var totalWed=$("#totalWed").val();
    var wednesdays,alldays;
    if(projectNameWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameWed2").css("border", "1px solid red");
         document.getElementById('projectNameWed2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameWed2").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalProjectDays2();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed2').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed2').value= 0.0;
        totalProjectDays2();
        totalWednesday();
        return false;
    }
    return true;
}
function projectWed3()
{
    var projectNameWed=$("#projectNameWed3").val();
    var all=$("#projectNameAll3").val();
    var totalWed=$("#totalWed").val();
    var wednesdays,alldays;
    if(projectNameWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameWed3").css("border", "1px solid red");
         document.getElementById('projectNameWed3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameWed3").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalProjectDays3();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed3').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed3').value= 0.0;
        totalProjectDays3();
        totalWednesday();
        return false;
    }
    return true;
}
function projectWed4()
{
    var projectNameWed=$("#projectNameWed4").val();
    var all=$("#projectNameAll4").val();
    var totalWed=$("#totalWed").val();
    var wednesdays,alldays;
    if(projectNameWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameWed4").css("border", "1px solid red");
         document.getElementById('projectNameWed4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameWed4").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalProjectDays4();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed4').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed4').value= 0.0;
        totalProjectDays4();
        totalWednesday();
        return false;
    }
    return true;
}
function projectWed5()
{
    var projectNameWed=$("#projectNameWed5").val();
    var all=$("#projectNameAll5").val();
    var totalWed=$("#totalWed").val();
    var wednesdays,alldays;
    if(projectNameWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameWed5").css("border", "1px solid red");
         document.getElementById('projectNameWed5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameWed5").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalProjectDays5();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed5').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameWed5').value= 0.0;
        totalProjectDays5();
        totalWednesday();
        return false;
    }
    return true;
}
function projectThu1()
{
    var projectNameThu=$("#projectNameThu1").val();
    var all=$("#projectNameAll1").val();
    var totalThu=$("#totalThu").val();
    var thursdays,alldays;
    if(projectNameThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameThu1").css("border", "1px solid red");
        document.getElementById('projectNameThu1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameThu1").css("border", "1px solid #D2D2D2");
    }
    thursdays=totalThursday();
    alldays=totalProjectDays1();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu1').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu1').value= 0.0;
        totalProjectDays1();
        totalThursday();
        return false;
    }
    return true;
}
function projectThu2()
{
    var projectNameThu=$("#projectNameThu2").val();
    var all=$("#projectNameAll2").val();
    var totalThu=$("#totalThu").val();
    var thursdays,alldays;
    if(projectNameThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameThu2").css("border", "1px solid red");
        document.getElementById('projectNameThu2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameThu2").css("border", "1px solid #D2D2D2");
    }
    thursdays=totalThursday();
    alldays=totalProjectDays2();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu2').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu2').value= 0.0;
        totalProjectDays2();
        totalThursday();
        return false;
    }
    return true;
}
function projectThu3()
{
    var projectNameThu=$("#projectNameThu3").val();
    var all=$("#projectNameAll3").val();
    var totalThu=$("#totalThu").val();
    var thursdays,alldays;
    if(projectNameThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameThu3").css("border", "1px solid red");
        document.getElementById('projectNameThu3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameThu3").css("border", "1px solid #D2D2D2");
    }
    thursdays=totalThursday();
    alldays=totalProjectDays3();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu3').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu3').value= 0.0;
        totalProjectDays3();
        totalThursday();
        return false;
    }
    return true;
}
function projectThu4()
{
    var projectNameThu=$("#projectNameThu4").val();
    var all=$("#projectNameAll4").val();
    var totalThu=$("#totalThu").val();
    var thursdays,alldays;
    if(projectNameThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameThu4").css("border", "1px solid red");
        document.getElementById('projectNameThu4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameThu4").css("border", "1px solid #D2D2D2");
    }
    thursdays=totalThursday();
    alldays=totalProjectDays4();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu4').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu4').value= 0.0;
        totalProjectDays4();
        totalThursday();
        return false;
    }
    return true;
}
function projectThu5()
{
    var projectNameThu=$("#projectNameThu5").val();
    var all=$("#projectNameAll5").val();
    var totalThu=$("#totalThu").val();
    var thursdays,alldays;
    if(projectNameThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameThu5").css("border", "1px solid red");
        document.getElementById('projectNameThu5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameThu5").css("border", "1px solid #D2D2D2");
    }
    thursdays=totalThursday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu5').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameThu5').value= 0.0;
        totalProjectDays5();
        totalThursday();
        return false;
    }
    return true;
}
function projectFri1()
{
    var projectNameFri=$("#projectNameFri1").val();
    var all=$("#projectNameAll1").val();
    var totalFri=$("#totalFri").val();
    var fridays,alldays;
    if(projectNameFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameFri1").css("border", "1px solid red");
        document.getElementById('projectNameFri1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameFri1").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalProjectDays1();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri1').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri1').value= 0.0;
        totalProjectDays1();
        totalFriday();
        return false;
    }
    return true;
}
function projectFri2()
{
    var projectNameFri=$("#projectNameFri2").val();
    var all=$("#projectNameAll2").val();
    var totalFri=$("#totalFri").val();
    var fridays,alldays;
    if(projectNameFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameFri2").css("border", "1px solid red");
        document.getElementById('projectNameFri2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameFri2").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalProjectDays2();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri2').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri2').value= 0.0;
        totalProjectDays2();
        totalFriday();
        return false;
    }
    return true;
}
function projectFri3()
{
    var projectNameFri=$("#projectNameFri3").val();
    var all=$("#projectNameAll3").val();
    var totalFri=$("#totalFri").val();
    var fridays,alldays;
    if(projectNameFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameFri3").css("border", "1px solid red");
        document.getElementById('projectNameFri3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameFri3").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalProjectDays3();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri3').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri3').value= 0.0;
        totalProjectDays3();
        totalFriday();
        return false;
    }
    return true;
}
function projectFri4()
{
    var projectNameFri=$("#projectNameFri4").val();
    var all=$("#projectNameAll4").val();
    var totalFri=$("#totalFri").val();
    var fridays,alldays;
    if(projectNameFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameFri4").css("border", "1px solid red");
        document.getElementById('projectNameFri4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameFri4").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri4').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri4').value= 0.0;
        totalProjectDays4();
        totalFriday();
        return false;
    }
    return true;
}
function projectFri5()
{
    var projectNameFri=$("#projectNameFri5").val();
    var all=$("#projectNameAll5").val();
    var totalFri=$("#totalFri").val();
    var fridays,alldays;
    if(projectNameFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameFri5").css("border", "1px solid red");
        document.getElementById('projectNameFri5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameFri5").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri5').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameFri5').value= 0.0;
        totalProjectDays5();
        totalFriday();
        return false;
    }
    return true;
}
function projectSat1()
{
    var projectNameSat=$("#projectNameSat1").val();
    var all=$("#projectNameAll1").val();
    var totalSat=$("#totalSat").val();
    var saturdays,alldays;
    if(projectNameSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSat1").css("border", "1px solid red");
        document.getElementById('projectNameSat1').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSat1").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalProjectDays1();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat1').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat1').value= 0.0;
        totalProjectDays1();
        totalSaturday();
        return false;
    }
    return true;
}
function projectSat2()
{
    var projectNameSat=$("#projectNameSat2").val();
    var all=$("#projectNameAll2").val();
    var totalSat=$("#totalSat").val();
    var saturdays,alldays;
    if(projectNameSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSat2").css("border", "1px solid red");
        document.getElementById('projectNameSat2').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSat2").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalProjectDays2();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat2').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat2').value= 0.0;
        totalProjectDays2();
        totalSaturday();
        return false;
    }
    return true;
}
function projectSat3()
{
    var projectNameSat=$("#projectNameSat3").val();
    var all=$("#projectNameAll3").val();
    var totalSat=$("#totalSat").val();
    var saturdays,alldays;
    if(projectNameSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSat3").css("border", "1px solid red");
        document.getElementById('projectNameSat3').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSat3").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalProjectDays3();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat3').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat3').value= 0.0;
        totalProjectDays3();
        totalSaturday();
        return false;
    }
    return true;
}
function projectSat4()
{
    var projectNameSat=$("#projectNameSat4").val();
    var all=$("#projectNameAll4").val();
    var totalSat=$("#totalSat").val();
    var saturdays,alldays;
    if(projectNameSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSat4").css("border", "1px solid red");
        document.getElementById('projectNameSat4').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSat4").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalProjectDays4();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat4').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat4').value= 0.0;
        totalProjectDays4();
        totalSaturday();
        return false;
    }
    return true;
}
function projectSat5()
{
    var projectNameSat=$("#projectNameSat5").val();
    var all=$("#projectNameAll5").val();
    var totalSat=$("#totalSat").val();
    var saturdays,alldays;
    if(projectNameSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#projectNameSat5").css("border", "1px solid red");
        document.getElementById('projectNameSat5').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#projectNameSat5").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalProjectDays5();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat5').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('projectNameSat5').value= 0.0;
        totalProjectDays5();
        totalSaturday();
        return false;
    }
    return true;
}
function internalSunday()
{
    var internalSun=$("#internalSun").val();
    var all=$("#internalAll").val();
    var totalSun=$("#totalSun").val();
    var alldays,sundays;
    if(internalSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalSun").css("border", "1px solid red");
         document.getElementById('internalSun').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalSun").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalinternalDays();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalSun').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalSun').value= 0.0;
        totalinternalDays();
        totalSunday();
        return false;
    }
    return true;
}
function internalMonday()
{
    var internalMon=$("#internalMon").val();
    var all=$("#internalAll").val();
    var totalMon=$("#totalMon").val();
    var alldays,mondays;
    if(internalMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalMon").css("border", "1px solid red");
         document.getElementById('internalMon').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalMon").css("border", "1px solid #D2D2D2");
    } 
    mondays=totalMonday();
    alldays=totalinternalDays();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalMon').value= 0.0;
        totalMonday();
        return false;
    }
    else if(mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalMon').value= 0.0;
        toalinternalDays();
        totalMonday();
        return false;
    }
    return true;
}
function internalTuesday()
{
    var internalTue=$("#internalTue").val();
    var all=$("#internalAll").val();
    var totalTue=$("#totalTue").val();
    var alldays,tuesdays;
    if(internalTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalTue").css("border", "1px solid red");
         document.getElementById('internalTue').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalTue").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalinternalDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalTue').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalTue').value= 0.0;
        totalinternalDays();
        totalTuesday();
        return false;
    }
    return true;
}
function internalWednesday()
{
    var internalWed=$("#internalWed").val();
    var all=$("#internalAll").val();
    var totalWed=$("#totalWed").val();
    var alldays,wednesdays;
    if(internalWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalWed").css("border", "1px solid red");
        document.getElementById('internalWed').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalWed").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalinternalDays();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalWed').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalWed').value= 0.0;
        totalinternalDays();
        totalWednesday();
        return false;
    }
    return true;
}
function internalThursday()
{
    var internalThu=$("#internalThu").val();
    var all=$("#internalAll").val();
    var totalThu=$("#totalThu").val();
    var alldays,thursdays;
    if(internalThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalThu").css("border", "1px solid red");
        document.getElementById('internalThu').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalThu").css("border", "1px solid #D2D2D2");
    } 
    thursdays=totalThursday();
    alldays=totalinternalDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalThu').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalThu').value= 0.0;
        totalinternalDays();
        totalThursday();
        return false;
    }
    return true;
}
function internalFriday()
{
    var internalFri=$("#internalFri").val();
    var all=$("#internalAll").val();
    var totalFri=$("#totalFri").val();
    var alldays,fridays;
    if(internalFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalFri").css("border", "1px solid red");
        document.getElementById('internalFri').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalFri").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalinternalDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalFri').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalFri').value= 0.0;
        totalinternalDays();
        totalFriday();
        return false;
    }
    return true;
}
function internalSaturday()
{
    var internalSat=$("#internalSat").val();
    var all=$("#internalAll").val();
    var totalSat=$("#totalSat").val();
    var alldays,saturdays;
    if(internalSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#internalSat").css("border", "1px solid red");
        document.getElementById('internalSat').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#internalSat").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalinternalDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalSat').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('internalSat').value= 0.0;
        totalinternalDays();
        totalSaturday();
        return false;
    }
    return true;
}
function vacationSunday()
{
    var vacationSun=$("#vacationSun").val();
    var all=$("#vacationAll").val();
    var totalSun=$("#totalSun").val();
    var alldays,sundays;
    if(vacationSun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationSun").css("border", "1px solid red");
        document.getElementById('vacationSun').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationSun").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalvacationDays();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationSun').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationSun').value= 0.0;
        totalvacationDays();
        totalSunday();
        return false;
    }
    return true;
}
function vacationMonday()
{
    var vacationMon=$("#vacationMon").val();
    var all=$("#vacationAll").val();
    var totalMon=$("#totalMon").val();
    var alldays,mondays;
    if(vacationMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationMon").css("border", "1px solid red");
        document.getElementById('vacationMon').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationMon").css("border", "1px solid #D2D2D2");
    } 
    mondays=totalMonday();
    alldays=totalvacationDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationMon').value= 0.0;
        totalMonday();
        return false;
    }
    else if(mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationMon').value= 0.0;
        totalvacationDays();
        totalMonday();
        return false;
    }
    return true;
}
function vacationTuesday()
{
    var vacationTue=$("#vacationTue").val();
    var all=$("#vacationAll").val();
    var totalTue=$("#totalTue").val();
    var alldays,tuesdays;
    if(vacationTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationTue").css("border", "1px solid red");
        document.getElementById('vacationTue').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationTue").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalvacationDays();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationTue').value= 0.0;
        totalTuesday();
        return false;
    }
    else if(tuesdays==totalTue)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationTue').value= 0.0;
        totalvacationDays();
        totalTuesday();
        return false;
    }
    return true;
}
function vacationWednesday()
{
    var vacationWed=$("#vacationWed").val();
    var all=$("#vacationAll").val();
    var totalWed=$("#totalWed").val();
    var alldays,wednesdays;
    if(vacationWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationWed").css("border", "1px solid red");
        document.getElementById('vacationWed').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationWed").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalvacationDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationWed').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationWed').value= 0.0;
        totalvacationDays();
        totalWednesday();
        return false;
    }
    return true;
}
function vacationThursday()
{
    var vacationThu=$("#vacationThu").val();
    var all=$("#vacationAll").val();
    var totalThu=$("#totalThu").val();
    var alldays,thursdays;
    if(vacationThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationThu").css("border", "1px solid red");
        document.getElementById('vacationThu').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationThu").css("border", "1px solid #D2D2D2");
    } 
    thursdays=totalThursday();
    alldays=totalvacationDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationThu').value= 0.0;
        totalThursday();
        return false;
    }
    else if(thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationThu').value= 0.0;
        totalvacationDays();
        totalThursday();
        return false;
    }
    return true;
}
function vacationFriday()
{
    var vacationFri=$("#vacationFri").val();
    var all=$("#vacationAll").val();
    var totalFri=$("#totalFri").val();
    var alldays,fridays;
    if(vacationFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationFri").css("border", "1px solid red");
        document.getElementById('vacationFri').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationFri").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalvacationDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationFri').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationFri').value= 0.0;
        totalvacationDays();
        totalFriday();
        return false;
    }
    return true;
}
function vacationSaturday()
{
    var vacationSat=$("#vacationSat").val();
    var all=$("#vacationAll").val();
    var totalSat=$("#totalSat").val();
    var alldays,saturdays;
    if(vacationSat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#vacationSat").css("border", "1px solid red");
        document.getElementById('vacationSat').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#vacationSat").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalvacationDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationSat').value= 0.0;
        totalSaturday();
        return false;
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('vacationSat').value= 0.0;
        totalvacationDays();
        totalSaturday();
        return false;
    }
    return true;
}
function holidaySunday()
{
    var holidaySun=$("#holidaySun").val();
    var all=$("#holidayAll").val();
    var totalSun=$("#totalSun").val();
    var alldays,sundays;
    if(holidaySun>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidaySun").css("border", "1px solid red");
        document.getElementById('holidaySun').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidaySun").css("border", "1px solid #D2D2D2");
    } 
    sundays=totalSunday();
    alldays=totalholidayDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidaySun').value= 0.0;
        totalSunday();
        return false;
    }
    else if(sundays==totalSun)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidaySun').value= 0.0;
        totalholidayDays();
        totalSunday();
        return false;
    }
    return true;
}
function holidayMonday()
{
    var holidayMon=$("#holidayMon").val();
    var all=$("#holidayAll").val();
    var totalMon=$("#totalMon").val();
    var alldays,mondays;
    if(holidayMon>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidayMon").css("border", "1px solid red");
        document.getElementById('holidayMon').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidayMon").css("border", "1px solid #D2D2D2");
    } 
    mondays=totalMonday();
    alldays=totalholidayDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayMon').value= 0.0;
        totalMonday();
        return false;
    }
    else if(mondays==totalMon)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayMon').value= 0.0;
        totalholidayDays();
        totalMonday();
        return false;
    }
    return true;
}
function holidayTuesday()
{
    var holidayTue=$("#holidayTue").val();
    var all=$("#holidayAll").val();
    var totalTue=$("#totalTue").val();
    var alldays,tuesdays;
    if(holidayTue>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidayTue").css("border", "1px solid red");
        document.getElementById('holidayTue').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidayTue").css("border", "1px solid #D2D2D2");
    } 
    tuesdays=totalTuesday();
    alldays=totalholidayDays();
    if(alldays==all)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayTue').value= 0.0;
        totalTuesday();
        return false;
    }
    else if((tuesdays==totalTue))
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayTue').value= 0.0;
        totalholidayDays();
        totalTuesday();
        return false;
            
    }
    return true;
}
function holidayWednesday()
{
    var holidayWed=$("#holidayWed").val();
    var all=$("#holidayAll").val();
    var totalWed=$("#totalWed").val();
    var alldays,wednesdays;
    if(holidayWed>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidayWed").css("border", "1px solid red");
        document.getElementById('holidayWed').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidayWed").css("border", "1px solid #D2D2D2");
    } 
    wednesdays=totalWednesday();
    alldays=totalholidayDays();
    if(alldays==all)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayWed').value= 0.0;
        totalWednesday();
        return false;
    }
    else if(wednesdays==totalWed)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayWed').value= 0.0;
        totalholidayDays();
        totalWednesday();
        return false;
    }
    return true;
};
function holidayThursday()
{
    var holidayThu=$("#holidayThu").val();
    var all=$("#holidayAll").val();
    var totalThu=$("#totalThu").val();
    var alldays,thursdays;
    if(holidayThu>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidayThu").css("border", "1px solid red");
        document.getElementById('holidayThu').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidayThu").css("border", "1px solid #D2D2D2");
    } 
    thursdays=totalThursday();
    alldays=totalholidayDays();
    if(alldays==all){
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayThu').value= 0.0;
        totalThursday();
        return false;
    }
    else if (thursdays==totalThu)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayThu').value= 0.0;
        totalholidayDays();
        totalThursday();
        return false;
    }
    return true;
};
function holidayFriday()
{
    var holidayFri=$("#holidayFri").val();
    var all=$("#holidayAll").val();
    var totalFri=$("#totalFri").val();
    var alldays,fridays;
    if(holidayFri>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidayFri").css("border", "1px solid red");
        document.getElementById('holidayFri').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidayFri").css("border", "1px solid #D2D2D2");
    } 
    fridays=totalFriday();
    alldays=totalholidayDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayFri').value= 0.0;
        totalFriday();
        return false;
    }
    else if(fridays==totalFri)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidayFri').value= 0.0;
        totalholidayDays();
        totalFriday();
        return false;
    }
    return true;
};
function holidaySaturday()
{
    var holidaySat=$("#holidaySat").val();
    var all=$("#holidayAll").val();
    var totalSat=$("#totalSat").val();
    var alldays,saturdays;
    if(holidaySat>24.0)
    {
        $("edittimesheetserror").html(" <b><font color='red'>value must be less than 24</font></b>");
        $("#holidaySat").css("border", "1px solid red");
        document.getElementById('holidaySat').value= parseFloat(0).toFixed(1);
        return false; 
    }
    else
    {
        $("edittimesheetserror").html(" ");
        $("#holidaySat").css("border", "1px solid #D2D2D2");
    } 
    saturdays=totalSaturday();
    alldays=totalholidayDays();
    if(alldays==all)
    {
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidaySat').value= 0.0;
        totalSaturday();
        return false; 
    }
    else if(saturdays==totalSat)
    {//alert(to);
        $("edittimesheetserror").html(" <b><font color='red'>total exceeding 24 hrs</font></b>");
        document.getElementById('holidaySat').value= 0.0;
        totalholidayDays();
        totalSaturday();
        return false;
    }
    return true;
};
function totalProjectDays1()
{
    var projectNameSun1=$("#projectNameSun1").val();
    var projectNameMon1=$("#projectNameMon1").val();
    var projectNameTue1=$("#projectNameTue1").val();
    var projectNameWed1=$("#projectNameWed1").val();
    var projectNameThu1=$("#projectNameThu1").val();
    var projectNameFri1=$("#projectNameFri1").val();
    var projectNameSat1=$("#projectNameSat1").val();
    var allProjectDays1=$("#projectNameAll1").val();
    
    if(projectNameSun1=="")
        projectNameSun1 = 0.0;
    if(projectNameMon1=="")
        projectNameMon1 = 0.0;
    if(projectNameTue1=="")
        projectNameTue1 = 0.0;
    if(projectNameWed1=="")
        projectNameWed1 = 0.0;
    if(projectNameThu1=="")
        projectNameThu1 = 0.0;
    if(projectNameFri1=="")
        projectNameFri1 = 0.0;
    if(projectNameSat1=="")
        projectNameSat1 = 0.0;
        { 
        var result = parseFloat(projectNameSun1)+parseFloat(projectNameMon1)+parseFloat(projectNameTue1)+parseFloat(projectNameWed1)+parseFloat(projectNameThu1)+parseFloat(projectNameFri1)+parseFloat(projectNameSat1);
        if (!isNaN(result)) {
            document.getElementById('projectNameAll1').value = result.toFixed(1);
            allTotal();
            totalBillableHrs();
        }
        return result;
    }
    
};
function totalProjectDays2()
{
    var projectNameSun2=$("#projectNameSun2").val();
    var projectNameMon2=$("#projectNameMon2").val();
    var projectNameTue2=$("#projectNameTue2").val();
    var projectNameWed2=$("#projectNameWed2").val();
    var projectNameThu2=$("#projectNameThu2").val();
    var projectNameFri2=$("#projectNameFri2").val();
    var projectNameSat2=$("#projectNameSat2").val();
    var allProjectDays2=$("#projectNameAll2").val();
    
    if(projectNameSun2=="")
        projectNameSun2 = 0.0;
    if(projectNameMon2=="")
        projectNameMon2 = 0.0;
    if(projectNameTue2=="")
        projectNameTue2 = 0.0;
    if(projectNameWed2=="")
        projectNameWed2 = 0.0;
    if(projectNameThu2=="")
        projectNameThu2 = 0.0;
    if(projectNameFri2=="")
        projectNameFri2 = 0.0;
    if(projectNameSat2=="")
        projectNameSat2 = 0.0;
        { 
        var result = parseFloat(projectNameSun2)+parseFloat(projectNameMon2)+parseFloat(projectNameTue2)+parseFloat(projectNameWed2)+parseFloat(projectNameThu2)+parseFloat(projectNameFri2)+parseFloat(projectNameSat2);
        if (!isNaN(result)) {
            document.getElementById('projectNameAll2').value = result.toFixed(1);
            allTotal();
            totalBillableHrs();
        }
        return result;
    }
    
};
function totalProjectDays3()
{
    var projectNameSun3=$("#projectNameSun3").val();
    var projectNameMon3=$("#projectNameMon3").val();
    var projectNameTue3=$("#projectNameTue3").val();
    var projectNameWed3=$("#projectNameWed3").val();
    var projectNameThu3=$("#projectNameThu3").val();
    var projectNameFri3=$("#projectNameFri3").val();
    var projectNameSat3=$("#projectNameSat3").val();
    var allProjectDays3=$("#projectNameAll3").val();
    
    if(projectNameSun3=="")
        projectNameSun3 = 0.0;
    if(projectNameMon3=="")
        projectNameMon3 = 0.0;
    if(projectNameTue3=="")
        projectNameTue3 = 0.0;
    if(projectNameWed3=="")
        projectNameWed3 = 0.0;
    if(projectNameThu3=="")
        projectNameThu3 = 0.0;
    if(projectNameFri3=="")
        projectNameFri3 = 0.0;
    if(projectNameSat3=="")
        projectNameSat3 = 0.0;
        { 
        var result = parseFloat(projectNameSun3)+parseFloat(projectNameMon3)+parseFloat(projectNameTue3)+parseFloat(projectNameWed3)+parseFloat(projectNameThu3)+parseFloat(projectNameFri3)+parseFloat(projectNameSat3);
        if (!isNaN(result)) {
            document.getElementById('projectNameAll3').value = result.toFixed(1);
            allTotal();
            totalBillableHrs();
        }
        return result;
    }
    
};
function totalProjectDays4()
{
    var projectNameSun4=$("#projectNameSun4").val();
    var projectNameMon4=$("#projectNameMon4").val();
    var projectNameTue4=$("#projectNameTue4").val();
    var projectNameWed4=$("#projectNameWed4").val();
    var projectNameThu4=$("#projectNameThu4").val();
    var projectNameFri4=$("#projectNameFri4").val();
    var projectNameSat4=$("#projectNameSat4").val();
    var allProjectDays4=$("#projectNameAll4").val();
        
    if(projectNameSun4=="")
        projectNameSun4 = 0.0;
    if(projectNameMon4=="")
        projectNameMon4 = 0.0;
    if(projectNameTue4=="")
        projectNameTue4 = 0.0;
    if(projectNameWed4=="")
        projectNameWed4 = 0.0;
    if(projectNameThu4=="")
        projectNameThu4 = 0.0;
    if(projectNameFri4=="")
        projectNameFri4 = 0.0;
    if(projectNameSat4=="")
        projectNameSat4 = 0.0;
        { 
        var result = parseFloat(projectNameSun4)+parseFloat(projectNameMon4)+parseFloat(projectNameTue4)+parseFloat(projectNameWed4)+parseFloat(projectNameThu4)+parseFloat(projectNameFri4)+parseFloat(projectNameSat4);
        if (!isNaN(result)) {
            document.getElementById('projectNameAll4').value = result.toFixed(1);
            allTotal();
            totalBillableHrs();
        }
        return result;
    }
    
};
function totalProjectDays5()
{
    var projectNameSun5=$("#projectNameSun5").val();
    var projectNameMon5=$("#projectNameMon5").val();
    var projectNameTue5=$("#projectNameTue5").val();
    var projectNameWed5=$("#projectNameWed5").val();
    var projectNameThu5=$("#projectNameThu5").val();
    var projectNameFri5=$("#projectNameFri5").val();
    var projectNameSat5=$("#projectNameSat5").val();
    var allProjectDays5=$("#projectNameAll5").val();
    
    if(projectNameSun5=="")
        projectNameSun5 = 0.0;
    if(projectNameMon5=="")
        projectNameMon5 = 0.0;
    if(projectNameTue5=="")
        projectNameTue5 = 0.0;
    if(projectNameWed5=="")
        projectNameWed5 = 0.0;
    if(projectNameThu5=="")
        projectNameThu5 = 0.0;
    if(projectNameFri5=="")
        projectNameFri5 = 0.0;
    if(projectNameSat5=="")
        projectNameSat5 = 0.0;
        { 
        var result = parseFloat(projectNameSun5)+parseFloat(projectNameMon5)+parseFloat(projectNameTue5)+parseFloat(projectNameWed5)+parseFloat(projectNameThu5)+parseFloat(projectNameFri5)+parseFloat(projectNameSat5);
        if (!isNaN(result)) {
            document.getElementById('projectNameAll5').value = result.toFixed(1);
            allTotal();
            totalBillableHrs();
        }
        return result;
    }
   
};

function totalinternalDays()
{
    var internalSun=$("#internalSun").val();
    var internalMon=$("#internalMon").val();
    var internalTue=$("#internalTue").val();
    var internalWed=$("#internalWed").val();
    var internalThu=$("#internalThu").val();
    var internalFri=$("#internalFri").val();
    var internalSat=$("#internalSat").val();
    var allinternalDays=$("#internalAll").val();
    if(internalSun=="")
        internalSun = 0.0;
    if(internalMon=="")
        internalMon = 0.0;
    if(internalTue=="")
        internalTue = 0.0;
    if(internalWed=="")
        internalWed = 0.0;
    if(internalThu=="")
        internalThu = 0.0;
    if(internalFri=="")
        internalFri = 0.0;
    if(internalSat=="")
        internalSat = 0.0;
        { 
        var result = parseFloat(internalSun)+parseFloat(internalMon)+parseFloat(internalTue)+parseFloat(internalWed)+parseFloat(internalThu)+parseFloat(internalFri)+parseFloat(internalSat);
        if (!isNaN(result)) {
            document.getElementById('internalAll').value = result.toFixed(1);
            allTotal();
            totalBillableHrs();
        }
        return result;
    }
   
};
function totalvacationDays()
{
    var vacationSun=$("#vacationSun").val();
    var vacationMon=$("#vacationMon").val();
    var vacationTue=$("#vacationTue").val();
    var vacationWed=$("#vacationWed").val();
    var vacationThu=$("#vacationThu").val();
    var vacationFri=$("#vacationFri").val();
    var vacationSat=$("#vacationSat").val();
    var allvacationDays=$("#vacationAll").val();
    if(vacationSun=="")
        vacationSun = 0.0;
    if(vacationMon=="")
        vacationMon = 0.0;
    if(vacationTue=="")
        vacationTue = 0.0;
    if(vacationWed=="")
        vacationWed = 0.0;
    if(vacationThu=="")
        vacationThu = 0.0;
    if(vacationFri=="")
        vacationFri = 0.0;
    if(vacationSat=="")
        vacationSat = 0.0;
        { 
        var result = parseFloat(vacationSun)+parseFloat(vacationMon)+parseFloat(vacationTue)+parseFloat(vacationWed)+parseFloat(vacationThu)+parseFloat(vacationFri)+parseFloat(vacationSat);
        if (!isNaN(result)) {
            document.getElementById('vacationAll').value = result.toFixed(1);
            document.getElementById('totalVacationHrs').value=result.toFixed(1);
            allTotal();
        }
        return result;
    }
    
};
function totalholidayDays()
{
    var holidaySun=$("#holidaySun").val();
    var holidayMon=$("#holidayMon").val();
    var holidayTue=$("#holidayTue").val();
    var holidayWed=$("#holidayWed").val();
    var holidayThu=$("#holidayThu").val();
    var holidayFri=$("#holidayFri").val();
    var holidaySat=$("#holidaySat").val();
    var allholidayDays=$("#holidayAll").val();
    if(holidaySun=="")
        holidaySun = 0.0;
    if(holidayMon=="")
        holidayMon = 0.0;
    if(holidayTue=="")
        holidayTue = 0.0;
    if(holidayWed=="")
        holidayWed = 0.0;
    if(holidayThu=="")
        holidayThu = 0.0;
    if(holidayFri=="")
        holidayFri = 0.0;
    if(holidaySat=="")
        holidaySat = 0.0;
        { 
        var result = parseFloat(holidaySun)+parseFloat(holidayMon)+parseFloat(holidayTue)+parseFloat(holidayWed)+parseFloat(holidayThu)+parseFloat(holidayFri)+parseFloat(holidaySat);
        if (!isNaN(result)) {
            document.getElementById('holidayAll').value = result.toFixed(1);
            document.getElementById('totalHolidayHrs').value = result.toFixed(1);
            allTotal();
        }
        return result;
    }
   
};
function totalSunday()
{
    //alert("div");
    var totalSun=$("#totalSun").val();
    var projectNameSun1=$("#projectNameSun1").val();
    var projectNameSun2=$("#projectNameSun2").val();
    var projectNameSun3=$("#projectNameSun3").val();
    var projectNameSun4=$("#projectNameSun4").val();
    var projectNameSun5=$("#projectNameSun5").val();
    var internalSun=$("#internalSun").val();
    var vacationSun=$("#vacationSun").val();
    var holidaySun=$("#holidaySun").val();
    // var comptimeSun=$("#comptimeSun").val();
    if(projectNameSun1=="")
        projectNameSun1 = 0.0;
    if(projectNameSun2=="")
        projectNameSun2 = 0.0;
    if(projectNameSun3=="")
        projectNameSun3 = 0.0;
    if(projectNameSun4=="")
        projectNameSun4 = 0.0;
    if(projectNameSun5=="")
        projectNameSun5 = 0.0;
    if(internalSun=="")
        internalSun = 0.0;
    if(vacationSun=="")
        vacationSun = 0.0;
    if(holidaySun=="")
        holidaySun = 0.0;
    // if(comptimeSun=="")
    //    comptimeSun = 0.0;
    if(totalSun>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalSun").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameSun1)+parseFloat(projectNameSun2)+parseFloat(projectNameSun3)+parseFloat(projectNameSun4)+parseFloat(projectNameSun5)+parseFloat(internalSun)+parseFloat(vacationSun)+parseFloat(holidaySun);//+parseFloat(comptimeSun);
        //alert(result);
        if (!isNaN(result)) {
            if(result>24.0)
            {
                result=totalSun;
                return result;
            }
            //alert(result);
            document.getElementById('totalSun').value = result.toFixed(1);
           
        }
        return result;
    }
    
};
function totalMonday()
{
    var totalMon=$("#totalMon").val();
    var projectNameMon1=$("#projectNameMon1").val();
    var projectNameMon2=$("#projectNameMon2").val();
    var projectNameMon3=$("#projectNameMon3").val();
    var projectNameMon4=$("#projectNameMon4").val();
    var projectNameMon5=$("#projectNameMon5").val();

    var internalMon=$("#internalMon").val();
    var vacationMon=$("#vacationMon").val();
    var holidayMon=$("#holidayMon").val();
    //var comptimeMon;//=$("#comptimeMon").val();
    if(projectNameMon1=="")
        projectNameMon1 = 0.0;
    if(projectNameMon2=="")
        projectNameMon2 = 0.0;
    if(projectNameMon3=="")
        projectNameMon3 = 0.0;
    if(projectNameMon4=="")
        projectNameMon4 = 0.0;
    if(projectNameMon5=="")
        projectNameMon5 = 0.0;
    if(internalMon=="")
        internalMon = 0.0;
    if(vacationMon=="")
        vacationMon = 0.0;
    if(holidayMon=="")
        holidayMon = 0.0;
    // if(comptimeMon=="")
    //   comptimeMon = 0.0;
    if(totalMon>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalMon").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameMon5)+parseFloat(projectNameMon3)+parseFloat(projectNameMon4)+parseFloat(projectNameMon2)+parseFloat(projectNameMon1)+ parseFloat(internalMon)+parseFloat(vacationMon)+parseFloat(holidayMon);//+parseFloat(comptimeMon);
        if(!isNaN(result)) {
            if(result>24.0)
            {
                result=totalMon;
                return result;            
            }
            document.getElementById('totalMon').value = result.toFixed(1);
        // allTotal();
        }
        return result;
    }
    
};
function totalTuesday()
{
    var totalTue=$("#totalTue").val();
    var projectNameTue1=$("#projectNameTue1").val();
    var projectNameTue2=$("#projectNameTue2").val();
    var projectNameTue3=$("#projectNameTue3").val();
    var projectNameTue4=$("#projectNameTue4").val();
    var projectNameTue5=$("#projectNameTue5").val();
    var internalTue=$("#internalTue").val();
    var vacationTue=$("#vacationTue").val();
    var holidayTue=$("#holidayTue").val();
    // var comptimeTue=$("#comptimeTue").val();
    if(projectNameTue1=="")
        projectNameTue1 = 0.0;
    if(projectNameTue2=="")
        projectNameTue2 = 0.0;
    if(projectNameTue3=="")
        projectNameTue3 = 0.0;
    if(projectNameTue4=="")
        projectNameTue4 = 0.0;
    if(projectNameTue5=="")
        projectNameTue5 = 0.0;
    if(internalTue=="")
        internalTue = 0.0;
    if(vacationTue=="")
        vacationTue = 0.0;
    if(holidayTue=="")
        holidayTue = 0.0;
    // if(comptimeTue=="")
    //   comptimeTue = 0.0;
    if(totalTue>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalTue").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameTue3)+parseFloat(projectNameTue4)+parseFloat(projectNameTue5)+parseFloat(projectNameTue2)+parseFloat(projectNameTue1)+parseFloat(internalTue)+parseFloat(vacationTue)+parseFloat(holidayTue);//+parseFloat(comptimeTue);
        if (!isNaN(result)) {
            if(result>24.0)
            {
                result=totalTue;
                return result;
            }
            document.getElementById('totalTue').value = result.toFixed(1);
        // allTotal();
        }
        return result;
    }
    
};
function totalWednesday()
{
    var totalWed=$("#totalWed").val();
    var projectNameWed1=$("#projectNameWed1").val();
    var projectNameWed2=$("#projectNameWed2").val();
    var projectNameWed3=$("#projectNameWed3").val();
    var projectNameWed4=$("#projectNameWed4").val();
    var projectNameWed5=$("#projectNameWed5").val();
    var internalWed=$("#internalWed").val();
    var vacationWed=$("#vacationWed").val();
    var holidayWed=$("#holidayWed").val();
    // var comptimeWed=$("#comptimeWed").val();
    if(projectNameWed1=="")
        projectNameWed1 = 0.0;
    if(projectNameWed2=="")
        projectNameWed2 = 0.0;
    if(projectNameWed3=="")
        projectNameWed3 = 0.0;
    if(projectNameWed4=="")
        projectNameWed4 = 0.0;
    if(projectNameWed5=="")
        projectNameWed5 = 0.0;
    if(internalWed=="")
        internalWed = 0.0;
    if(vacationWed=="")
        vacationWed = 0.0;
    if(holidayWed=="")
        holidayWed= 0.0;
    //if(comptimeWed=="")
    //     comptimeWed = 0.0;
    if(totalWed>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalWed").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameWed2)+parseFloat(projectNameWed5)+parseFloat(projectNameWed4)+parseFloat(projectNameWed3)+parseFloat(projectNameWed1)+parseFloat(internalWed)+parseFloat(vacationWed)+parseFloat(holidayWed);//+parseFloat(comptimeWed);
        if (!isNaN(result)) {
            if(result>24.0)
            {
                result=totalWed;
                return result;                  
            }
            document.getElementById('totalWed').value = result.toFixed(1);
        //allTotal();
        }
        return result;
    }
   
};
function totalThursday()
{
    var totalThu=$("#totalThu").val();
    
    var projectNameThu1=$("#projectNameThu1").val();
    var projectNameThu2=$("#projectNameThu2").val();
    var projectNameThu3=$("#projectNameThu3").val();
    var projectNameThu4=$("#projectNameThu4").val();
    var projectNameThu5=$("#projectNameThu5").val();
 
    var internalThu=$("#internalThu").val();
    var vacationThu=$("#vacationThu").val();
    var holidayThu=$("#holidayThu").val();
    //var comptimeThu=$("#comptimeThu").val();
    if(projectNameThu1=="")
        projectNameThu1 = 0.0;
    if(projectNameThu2=="")
        projectNameThu2 = 0.0;
    if(projectNameThu3=="")
        projectNameThu3 = 0.0;
    if(projectNameThu4=="")
        projectNameThu4 = 0.0;
    if(projectNameThu5=="")
        projectNameThu5 = 0.0;
    if(internalThu=="")
        internalThu= 0.0;
    if(vacationThu=="")
        vacationThu = 0.0;
    if(holidayThu=="")
        holidayThu= 0.0;
    // if(comptimeThu=="")
    //   comptimeThu = 0.0;
    if(totalThu>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalThu").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameThu5)+ parseFloat(projectNameThu4)+ parseFloat(projectNameThu2)+parseFloat(projectNameThu3)+parseFloat(projectNameThu1) + parseFloat(internalThu)+parseFloat(vacationThu)+parseFloat(holidayThu);//+parseFloat(comptimeThu);
        if (!isNaN(result)) {
            if(result>24.0)
            {
                result=totalThu;
                return result;
            }
            document.getElementById('totalThu').value = result.toFixed(1);
        //allTotal();
        }
        return result;
    }
    
};
function totalFriday()
{
    var totalFri=$("#totalFri").val();
    var projectNameFri1=$("#projectNameFri1").val();
    var projectNameFri2=$("#projectNameFri2").val();
    var projectNameFri3=$("#projectNameFri3").val();
    var projectNameFri4=$("#projectNameFri4").val();
    var projectNameFri5=$("#projectNameFri5").val();
    var internalFri=$("#internalFri").val();
    var vacationFri=$("#vacationFri").val();
    var holidayFri=$("#holidayFri").val();
    // var comptimeFri=$("#comptimeFri").val();
    if(projectNameFri1=="")
        projectNameFri1 = 0.0;
    if(projectNameFri2=="")
        projectNameFri2 = 0.0;
    if(projectNameFri3=="")
        projectNameFri3 = 0.0;
    if(projectNameFri4=="")
        projectNameFri4 = 0.0;
    if(projectNameFri5=="")
        projectNameFri5 = 0.0;
    if(internalFri=="")
        internalFri = 0.0;
    if(vacationFri=="")
        vacationFri = 0.0;
    if(holidayFri=="")
        holidayFri= 0.0;
    //if(comptimeFri=="")
    //  comptimeFri = 0.0;
    if(totalFri>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalFri").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameFri3)+parseFloat(projectNameFri4)+parseFloat(projectNameFri5)+parseFloat(projectNameFri2)+parseFloat(projectNameFri1)+parseFloat(internalFri)+parseFloat(vacationFri)+parseFloat(holidayFri);//+parseFloat(comptimeFri);
        if (!isNaN(result)) {
            if(result>24.0)
            {
                result=totalFri;
                return result;
            }
            document.getElementById('totalFri').value = result.toFixed(1);
        //allTotal();
        }
        return result;
    }
    
};
function totalSaturday()
{
    var totalSat=$("#totalSat").val();
    var projectNameSat1=$("#projectNameSat1").val();
    var internalSat=$("#internalSat").val();
    var projectNameSat2=$("#projectNameSat2").val();
    var projectNameSat3=$("#projectNameSat3").val();
    var projectNameSat4=$("#projectNameSat4").val();
    var projectNameSat5=$("#projectNameSat5").val();
    var vacationSat=$("#vacationSat").val();
    var holidaySat=$("#holidaySat").val();
    //// var comptimeSat=$("#comptimeSat").val();
    if(projectNameSat1=="")
        projectNameSat1 = 0.0;
    if(projectNameSat2=="")
        projectNameSat2 = 0.0;
    if(projectNameSat3=="")
        projectNameSat3 = 0.0;
    if(projectNameSat4=="")
        projectNameSat4 = 0.0;
    if(projectNameSat5=="")
        projectNameSat5 = 0.0;
    if(internalSat=="")
        internalSat = 0.0;
    if(vacationSat=="")
        vacationSat = 0.0;
    if(holidaySat=="")
        holidaySat= 0.0;
    //if(comptimeSat=="")
    //  comptimeSat = 0.0;
    if(totalSat>24.0){
        $("edittimesheetserror").html(" <b><font color='red'>value exceeded 24</font></b>");
        $("#totalSat").css("border", "1px solid red");
        return false; 
    }
    else{
        var result = parseFloat(projectNameSat1)+parseFloat(projectNameSat2)+parseFloat(projectNameSat3)+parseFloat(projectNameSat4)+parseFloat(projectNameSat5)+parseFloat(internalSat)+parseFloat(vacationSat)+parseFloat(holidaySat);//+parseFloat(comptimeSat);
        if (!isNaN(result)) {
            if(result>24.0)
            {
                result=totalSat;
                return result;
                   
            }
            document.getElementById('totalSat').value = result.toFixed(1);
        //allTotal();
        }
        return result;
    }
   
};
function totalBillableHrs(){
    var projectNameAll1=$("#projectNameAll1").val();
    var projectNameAll2=$("#projectNameAll2").val();
    var projectNameAll3=$("#projectNameAll3").val();
    var projectNameAll4=$("#projectNameAll4").val();
    var projectNameAll5=$("#projectNameAll5").val();
    var internalAll=$("#internalAll").val();
    if(projectNameAll1=="")
        projectNameAll1=0.0;
    if(internalAll=="")
        internalAll=0.0;
    var result = parseFloat(projectNameAll1)+parseFloat(projectNameAll2)+parseFloat(projectNameAll3)+parseFloat(projectNameAll4)+parseFloat(projectNameAll5)+parseFloat(internalAll);
    if (!isNaN(result)) {
        document.getElementById('totalBillHrs').value = result.toFixed(1);
            
    }
    return true;
};
function allTotal(){
    getDefault();
    var projectall1=$("#projectNameAll1").val();
    var projectall2=$("#projectNameAll2").val();
    var projectall3=$("#projectNameAll3").val();
    var projectall4=$("#projectNameAll4").val();
    var projectall5=$("#projectNameAll5").val();
    
    // var comptimeall=$("#comptimeAll").val();
    var vacationall=$("#vacationAll").val();
    var holidayall=$("#holidayAll").val();
    var internalall=$("#internalAll").val();
    //    var totalsun=$("#totalSun").val();
    //    var totalMon=$("#totalMon").val();
    //    var totalTue=$("#totalTue").val();
    //    var totalWed=$("#totalWed").val();
    //    var totalFri=$("#totalFri").val();
    //    var totalSat=$("#totalSat").val();
    //    var totalThu=$("#totalThu").val();
    if(projectall1=="")
        projectall1=0.0;
    // if(comptimeall=="")
    //   comptimeall=0.0;
    if(vacationall=="")
        vacationall=0.0;
    if(holidayall=="")
        holidayall=0.0;
    if(internalall=="")
        internalall=0.0;
    if(projectall2=="")
        projectall2=0.0;
    if(projectall3=="")
        projectall3=0.0;
    if(projectall4=="")
        projectall4=0.0;
    if(projectall5=="")
        projectall5=0.0;
    var result = parseFloat(projectall2)+parseFloat(projectall3)+parseFloat(projectall4)+parseFloat(projectall5)+parseFloat(internalall)+parseFloat(holidayall)+parseFloat(vacationall)+parseFloat(projectall1);//+parseFloat(comptimeall);
    if (!isNaN(result)) {
        document.getElementById('totalAll').value = result.toFixed(1);
            
    }
    return true;
    
}
function onloadTotal(){
    totalProjectDays1();
    totalProjectDays2();
    totalProjectDays3();
    totalProjectDays4();
    totalProjectDays5();
    totalinternalDays();
    totalvacationDays();
    totalholidayDays();
    // totalcomptimeDays();
    totalSunday();
    totalMonday();
    totalTuesday();
    totalWednesday();
    totalThursday();
    totalFriday();
    totalSaturday();
}  
function addTimeSheetOverlayOpen(){
   // var reportingPerson=document.getElementById("reportingPerson").value;
    //alert("reporting person is"+reportingPerson);
   /* if(reportingPerson==" "|| reportingPerson==null)
    {
        alert("You dont have Reporting person please contact support team");
       
    }else{*/
        var specialBox = document.getElementById('addTimeSheetOverlay');
        if(specialBox.style.display == "block"){       
            specialBox.style.display = "none";         
        } else {
            specialBox.style.display = "block";      
        }
        // Initialize the plugin    
        $('#addTimeSheet').popup(      
            );
    //}
}
function addTimeSheetOverlayClose(){
    var specialBox = document.getElementById('addTimeSheetOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#addTimeSheet').popup(      
        );
}
function validateDates() {    
    // alert('hello');
    var startDate = document.getElementById('docdatepickerfrom').value;             
    var endDate = document.getElementById('docdatepicker').value;
    //alert(startDate+"Hello"+endDate);
    if(startDate.length==0 || endDate.length==0){
        alert("Start-date and End-date are required.");
        return false;
    }else{
        return true;
    }
    
};

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
        var specialBox = document.getElementById('addTimeSheetOverlay');
        if(specialBox.style.display == "block"){       
        //specialBox.style.display = "none";         
        } 
        else {
            specialBox.style.display = "block";      
        }
        // Initialize the plugin    
        $('#addTimeSheet').popup(      
            );
    //         onClose: function(){
    //            window.location.reload(false);
    //        }   

    }
      
    return false;
}

function checkWeekStatus()
{
   
    var val=$('input:radio[name=myRadioButton ]:checked').val();
    if(val==1){
        document.getElementById("weekrange").disabled =true;
        document.getElementById("weekrange").value ="";
        $("addTimesheerResult").html(" ");
    }else
    {
        document.getElementById("weekrange").disabled =false;
        $("addTimesheerResult").html(" ");
    }
} 
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
  
/*function addTimeSheetOverlayOpen(){
    var reportingPerson=document.getElementById("reportingPerson").value;
    //alert("reporting person is"+reportingPerson);
    if(reportingPerson==" "|| reportingPerson==null)
    {
        alert("You dont have Reporting person please contact support team");
       
    }else{
        var specialBox = document.getElementById('addTimeSheetOverlay');
        if(specialBox.style.display == "block"){       
            specialBox.style.display = "none";         
        } else {
            specialBox.style.display = "block";      
        }
        // Initialize the plugin    
        $('#addTimeSheet').popup(      
            );
    }
}*/
function addTimeSheetOverlayClose(){
    var specialBox = document.getElementById('addTimeSheetOverlay');
    if(specialBox.style.display == "block"){       
        specialBox.style.display = "none";         
    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#addTimeSheet').popup(      
        );
}
function validateDates() {    
    // alert('hello');
    var startDate = document.getElementById('docdatepickerfrom').value;             
    var endDate = document.getElementById('docdatepicker').value;
    //alert(startDate+"Hello"+endDate);
    if(startDate.length==0 || endDate.length==0){
        alert("Start-date and End-date are required.");
        return false;
    }else{
        return true;
    }
    
};

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
        var specialBox = document.getElementById('addTimeSheetOverlay');
        if(specialBox.style.display == "block"){       
        //specialBox.style.display = "none";         
        } 
        else {
            specialBox.style.display = "block";      
        }
        // Initialize the plugin    
        $('#addTimeSheet').popup(      
            );
    //         onClose: function(){
    //            window.location.reload(false);
    //        }   

    }
      
    return false;
}

function timesheetAddOverlayDetails(){
   
    //var userid=$("#userid").val();
     
    // alert("===>>>");
    var weekrange=$("#weekrange").val();
    //alert(weekrange);
    if(weekrange==null || weekrange==" ")
    {
        var url='../timesheets/timeSheetCheck.action';//?userid='+userid+'&usr_edu_id='+eid;
    } 
    else
    {
        var url='../timesheets/timeSheetCheck.action?previousDate='+weekrange;//?userid='+userid+'&usr_edu_id='+eid;
    } 
    //alert(url)
    var myurl='<a href='+url+'>';
    //alert(myurl)
    var req=overlayRequest(myurl);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert("here we are.......");
                // alert(req.responseText)
                if(req.responseText=="exist")
                {
                    $("addTimesheerResult").html(" <font color='green' size=1.5px >The TimeSheet already filled for this week</font>");    
                }
                else if(req.responseText=="notallow")    
                {
                    $("addTimesheerResult").html(" <font color='green' size=1.5px >The TimeSheet is not allowed for this week</font>");            
                }
                else
                {
                    if(weekrange==""){
                        window.location = "addTimeSheet.action";
                    }
                    else
                        window.location = "addTimeSheet.action?previousDate="+weekrange;

                }
                      
            ////alert("here we print in req status......"+req.responseText);
            //populateoverlayEduDetails(req.responseText);
            } 
            
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function overlayRequest(myurl) {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else
    if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }    
}

/*function checkWeekStatus()
{
   
    var val=$('input:radio[name=myRadioButton ]:checked').val();
    if(val==1){
        document.getElementById("weekrange").disabled =true;
    }else
    {
        document.getElementById("weekrange").disabled =false;
  
    }
} */  
function ProjectsOverlayOpen1(){
    // alert("hello");

    var specialBox = document.getElementById('projectsOverlay');
        
    if(specialBox.style.display == "block"){       
                             

    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#projects_popup').popup(      
        );
}
        
function projectsData()
{
    var projectslist;
    var p1 = document.getElementById('p1').checked;
    var p2 = document.getElementById('p2').checked;
    var p3 = document.getElementById('p3').checked;
    var p4 = document.getElementById('p4').checked;
    var p5 = document.getElementById('p5').checked;
    
    if(p1==true)
    {
        showRow("projectid1");
    }
    if(p1==false)
    {
        hideRow("projectid1");
        document.getElementById("projectNameSun1").value=0.0;
        document.getElementById("projectNameMon1").value=0.0;
        document.getElementById("projectNameTue1").value=0.0;
        document.getElementById("projectNameWed1").value=0.0;
        document.getElementById("projectNameThu1").value=0.0;
        document.getElementById("projectNameFri1").value=0.0;
        document.getElementById("projectNameSat1").value=0.0;
        document.getElementById("projectNameAll1").value=0.0;
    }
    if(p2==true)
    {
        showRow("projectid2");
        
    }
    if(p2==false)
    {    
        hideRow("projectid2");
        document.getElementById("projectNameSun2").value=0.0;
        document.getElementById("projectNameMon2").value=0.0;
        document.getElementById("projectNameTue2").value=0.0;
        document.getElementById("projectNameWed2").value=0.0;
        document.getElementById("projectNameThu2").value=0.0;
        document.getElementById("projectNameFri2").value=0.0;
        document.getElementById("projectNameSat2").value=0.0;   
        document.getElementById("projectNameAll2").value=0.0;
    } 
    if(p3==true)
    {
        showRow("projectid3");
        
    }
    if(p3==false)
    {    
        hideRow("projectid3");
        document.getElementById("projectNameSun3").value=0.0;
        document.getElementById("projectNameMon3").value=0.0;
        document.getElementById("projectNameTue3").value=0.0;
        document.getElementById("projectNameWed3").value=0.0;
        document.getElementById("projectNameThu3").value=0.0;
        document.getElementById("projectNameFri3").value=0.0;
        document.getElementById("projectNameSat3").value=0.0;
        document.getElementById("projectNameAll3").value=0.0;
    } 
    if(p4==true)
    {
        showRow("projectid4");
        
    }
    if(p4==false)
    {    
        hideRow("projectid4");
        document.getElementById("projectNameSun4").value=0.0;
        document.getElementById("projectNameMon4").value=0.0;
        document.getElementById("projectNameTue4").value=0.0;
        document.getElementById("projectNameWed4").value=0.0;
        document.getElementById("projectNameThu4").value=0.0;
        document.getElementById("projectNameFri4").value=0.0;
        document.getElementById("projectNameSat4").value=0.0;
        document.getElementById("projectNameAll4").value=0.0;
    }
    if(p5==true)
    {
        showRow("projectid5");
       
    }
    if(p5==false)
    {    
        hideRow("projectid5");
        document.getElementById("projectNameSun5").value=0.0;
        document.getElementById("projectNameMon5").value=0.0;
        document.getElementById("projectNameTue5").value=0.0;
        document.getElementById("projectNameWed5").value=0.0;
        document.getElementById("projectNameThu5").value=0.0;
        document.getElementById("projectNameFri5").value=0.0;
        document.getElementById("projectNameSat5").value=0.0;
        document.getElementById("projectNameAll5").value=0.0;
    }  
    
    getDefault();
    return false;
}
function MiscellaneousOverlayOpen(){
    // alert("hello");

    var specialBox = document.getElementById('timesheetMisc_Overlay');
        
    if(specialBox.style.display == "block"){       
                             

    } else {
        specialBox.style.display = "block";      
    }
    // Initialize the plugin    
    $('#timesheetMisc_popup').popup(      
        );
    if(document.getElementById('vacationAll').value>0.0)
        document.getElementById('vacation').checked=true;
    if(document.getElementById('holidayAll').value>0.0)
        document.getElementById('holiday').checked=true;
}

function miscellaneousData()
{
    var vacation = document.getElementById('vacation').checked;
    var holiday = document.getElementById('holiday').checked;
 
    if(vacation==true)
    {
        showRow("miscellaneousVacation");  
    }
    if(vacation==false)
    {
        hideRow("miscellaneousVacation"); 
        document.getElementById("vacationSun").value=0.0;
        document.getElementById("vacationMon").value=0.0;
        document.getElementById("vacationTue").value=0.0;
        document.getElementById("vacationWed").value=0.0;
        document.getElementById("vacationThu").value=0.0;
        document.getElementById("vacationFri").value=0.0;
        document.getElementById("vacationSat").value=0.0;
        document.getElementById("vacationAll").value=0.0;
    }   
    if(holiday==true)
    {
        showRow("miscellaneousHoliday"); 
          
    }
    if(holiday==false)
    {
        hideRow("miscellaneousHoliday");
        document.getElementById("holidaySun").value=0.0;
        document.getElementById("holidayMon").value=0.0;
        document.getElementById("holidayTue").value=0.0;
        document.getElementById("holidayWed").value=0.0;
        document.getElementById("holidayThu").value=0.0;
        document.getElementById("holidayFri").value=0.0;
        document.getElementById("holidaySat").value=0.0;
        document.getElementById("holidayAll").value=0.0;
    }     
    //    if(comptime==true)
    //        {
    //          showRow("miscellaneousComptime");  
    //        }
    //    if(comptime==false)
    //        {
    //          hideRow("miscellaneousComptime");  
    //        } 
    getDefault();
    return false;
}

function getProjectsEdit()
{
 
     var usr_id=document.getElementById("usr_id").value;
    var timesheetFlag=document.getElementById("timesheetFlag").value;
   // alert(usr_id);
    var url="../timesheets/getProjects.action?usr_id="+usr_id+"&timesheetFlag="+timesheetFlag;;
    // alert(url);
    var req=initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
                // alert("get the data");
                // alert(req.responseText);
                populateProjectsedit(req.responseText);
            
                $("errorEduAdd").html(" <b><font color='green'>Record successfully Added</font></b>");
            // doGetLeavesData();
            //                    showEducationDetails(userid);
            } 
            else
            {
                $("edu").html(" <b><font color='red'>Record not Added</font></b>");
            //  alert("Error occured");
            }
        }
    };
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);

}
function populateProjectsedit(response)
{
    var timesheetFlag=$("#timesheetFlag").val();
    var p1=document.getElementById("project1key").value;
    var p2=document.getElementById("project2key").value;
    var p3=document.getElementById("project3key").value;
    var p4=document.getElementById("project4key").value;
    var p5=document.getElementById("project5key").value;
    var projectList=response.split("^");
    for(var i=0;i<projectList.length;i++){
        if(i==5)
            break;
        if(i==0)
        {  
                     
            var project1=projectList[0].split("|");
                       
            document.getElementById("project1").value=project1[1];
            if(project1[1]!=null)
            {
                if((p1==project1[0])||(p2==project1[0])||(p3==project1[0])||(p4==project1[0])||(p5==project1[0]))
                {
                    if(document.getElementById("projectNameAll1").value>0.0)    
                 
                        showRow("projectid1");
                }
            }
        }
        if(i==1)
        {
            var project2=projectList[1].split("|");
            document.getElementById("project2").value=project2[1];
            if(project2[1]!=null)
            {
                if((p1==project2[0])||(p2==project2[0])||(p3==project2[0])||(p4==project2[0])||(p5==project2[0])){
                    if(document.getElementById("projectNameAll2").value>0.0)
                        showRow("projectid2");
                }
            }                    //alert(project2[1]);
        }
        if(i==2)
        {
            var project3=projectList[2].split("|");
            document.getElementById("project3").value=project3[1];
            if(project3[1]!=null)
            {
                if((p1==project3[0])||(p2==project3[0])||(p3==project3[0])||(p4==project3[0])||(p5==project3[0])){
                    if(document.getElementById("projectNameAll3").value>0.0)     
                        showRow("projectid3");
                }
            }
        }
        if(i==3)
        {
            var project4=projectList[3].split("|");
            document.getElementById("project4").value=project4[1];
            if(project4[1]!=null) 
            {
                if((p1==project4[0])||(p2==project4[0])||(p3==project4[0])||(p4==project4[0])||(p5==project4[0])){
                    if(document.getElementById("projectNameAll4").value>0.0) 
                        showRow("projectid4");
                }
            }
        }
        if(i==4)
        {
            var project5=projectList[4].split("|");
            document.getElementById("project5").value=project5[1];
            if(project5[1]!=null){
                if((p1==project5[0])||(p2==project5[0])||(p3==project5[0])||(p4==project5[0])||(p5==project5[0])){
                    if(document.getElementById("projectNameAll5").value>0.0) 
                        showRow("projectid5");
                }
            }
        }
    }
    
    
}
function onloadeditMis(){
    //alert("msi");
    var vacation = $("#totalVacationHrs").val();
    var holiday = $("#totalHolidayHrs").val();
    //alert(vacation);
    if(vacation>0){
        showRow("miscellaneousVacation");
    }
    if(holiday>0){
        showRow("miscellaneousHoliday");
    }
}
function setTemVar1(){
    document.timeSheetsForm.tempVar.value=1;
}
function setTemVar2(){
    document.timeSheetsForm.tempVar.value=2;
    document.getElementById("timeSheetSubmittedDate").value=getDate();
}
function clearPreviousDate()
{
    //alert("previos")
    document.getElementById("weekrange").value="";
    document.getElementById("weekrange").disabled =true;
    //document.getElementById("").value="1";
    //$('input:radio[name="myRadioButton1"][value="1"]').prop('checked', true);
    document.getElementById("radiobutton1").checked =true;
    $("addTimesheerResult").html(" ");
    return false;
}

function clearTimesheets()
{   
    //alert("heelo") 
    var dec= 0.0;
    var num = 0.0;
    var num1=num.toFixed(1);
    //document.getElementById(id).value=num1;
    document.getElementById("projectNameSun1").value=num1;
    document.getElementById("projectNameMon1").value=num1;
    document.getElementById("projectNameTue1").value=num1;
    document.getElementById("projectNameWed1").value=num1;
    document.getElementById("projectNameThu1").value=num1;
    document.getElementById("projectNameFri1").value=num1;
    document.getElementById("projectNameSat1").value=num1;
    document.getElementById("projectNameAll1").value=num1;
    document.getElementById("projectNameSun2").value=num1;
    document.getElementById("projectNameMon2").value=num1;
    document.getElementById("projectNameTue2").value=num1;
    document.getElementById("projectNameWed2").value=num1;
    document.getElementById("projectNameThu2").value=num1;
    document.getElementById("projectNameFri2").value=num1;
    document.getElementById("projectNameSat2").value=num1;
    document.getElementById("projectNameAll2").value=num1;
    document.getElementById("projectNameSun3").value=num1;
    document.getElementById("projectNameMon3").value=num1;
    document.getElementById("projectNameTue3").value=num1;
    document.getElementById("projectNameWed3").value=num1;
    document.getElementById("projectNameThu3").value=num1;
    document.getElementById("projectNameFri3").value=num1;
    document.getElementById("projectNameSat3").value=num1;
    document.getElementById("projectNameAll3").value=num1;
    document.getElementById("projectNameSun4").value=num1;
    document.getElementById("projectNameMon4").value=num1;
    document.getElementById("projectNameTue4").value=num1;
    document.getElementById("projectNameWed4").value=num1;
    document.getElementById("projectNameThu4").value=num1;
    document.getElementById("projectNameFri4").value=num1;
    document.getElementById("projectNameSat4").value=num1;
    document.getElementById("projectNameAll4").value=num1;
    document.getElementById("projectNameSun5").value=num1;
    document.getElementById("projectNameMon5").value=num1;
    document.getElementById("projectNameTue5").value=num1;
    document.getElementById("projectNameWed5").value=num1;
    document.getElementById("projectNameThu5").value=num1;
    document.getElementById("projectNameFri5").value=num1;
    document.getElementById("projectNameSat5").value=num1;
    document.getElementById("projectNameAll5").value=num1;
   
    
    document.getElementById("totalSun").value=num1;
    document.getElementById("totalMon").value=num1;
    document.getElementById("totalTue").value=num1;
    document.getElementById("totalFri").value=num1; 
    document.getElementById("totalWed").value=num1; 
    document.getElementById("totalThu").value=num1;
    document.getElementById("totalSat").value=num1;
    document.getElementById("totalAll").value=num1;
   
    document.getElementById("internalSun").value=num1;
    document.getElementById("internalMon").value=num1;
    document.getElementById("internalThu").value=num1;
    document.getElementById("internalWed").value=num1;
    document.getElementById("internalFri").value=num1;
    document.getElementById("internalSat").value=num1;
    document.getElementById("internalTue").value=num1;
    document.getElementById("internalAll").value=num1;
   
    document.getElementById("vacationSun").value=num1;
    document.getElementById("vacationMon").value=num1;
    document.getElementById("vacationTue").value=num1;
    document.getElementById("vacationWed").value=num1;
    document.getElementById("vacationThu").value=num1;
    document.getElementById("vacationFri").value=num1;
    document.getElementById("vacationSat").value=num1;
    document.getElementById("vacationAll").value=num1;
   
    document.getElementById("holidaySun").value=num1;
    document.getElementById("holidayMon").value=num1;
    document.getElementById("holidayTue").value=num1;
    document.getElementById("holidayWed").value=num1;
    document.getElementById("holidayThu").value=num1;
    document.getElementById("holidayFri").value=num1;
    document.getElementById("holidaySat").value=num1;
    document.getElementById("holidayAll").value=num1;
   
    document.getElementById("totalBillHrs").value=num1;
    document.getElementById("totalHolidayHrs").value=num1;
    document.getElementById("totalVacationHrs").value=num1;
    document.getElementById("timeSheetNotes").value="";
   
  
   
}
function timeSheetsNotes(id){
   var elem = document.getElementById("notes");

    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 250){
            el.val( el.val().substr(0, 250) );
        } else {
            elem.style.color="green";

            $("#notes").text(250-el.val().length+' Characters remaining . ');
            $("#notes").show().delay(5000).fadeOut();

        }
        if(el.val().length==250)
        {
            elem.style.color="red";

            $("#notes").text(' Cannot enter  more than 250 Characters .'); 
            $("#notes").show().delay(5000).fadeOut();

        }
        
    })
    return false;
};
function timeSheetsComments(id){
    $(id).keyup(function(){
        el = $(this);
        if(el.val().length >= 250){
            el.val( el.val().substr(0, 250) );
        } else {
            $("#comments").text(250-el.val().length+' Characters remaining . ');
        }
        if(el.val().length==250)
        {
            $("#comments").text(' Cannot enter  more than 250 Characters .');    
        }
        
    })
    return false;
};
function removeErrorMessages(){
    $("#notes").html("");
    $("#comments").html("");
    
}

function checkPreviousDate()
{
    var weekrange=$("#weekrange").val();
    //alert(weekrange);
    var val=$('input:radio[name=myRadioButton ]:checked').val();
   
    if(val==2)
    {
        if(weekrange==null || weekrange=="")
        {
            $("addTimesheerResult").html(" <font color='green' size=1.5px >Pick Date To Fill TimeSheet For Previous Week</font>");            
            return false;
        }
   
        else{
            timesheetAddOverlayDetails();
            return false;
        }
    }

    else
    {
        timesheetAddOverlayDetails();     
    }
    return false;    
}
function checkTimesheetDateRange() {
                //var fromValue=$('#docdatepickerfrom').val();
                var fromValue=document.getElementById("docdatepickerfrom").value;
                //var toValue=$('#docdatepicker').val();
                var toValue=document.getElementById("docdatepicker").value;
                var timesheetValdation=document.getElementById("timesheetValidation");
                //alert(fromValue+" and "+toValue)
                if(fromValue==""){
                    alert("from date is madatory");
                    return false;
                }
                if(toValue==""){
                    alert("to date is madatory");
                    return false;
                }
                
               
                return dateTimesheetValidation(fromValue,toValue,timesheetValdation);
            };
function dateTimesheetValidation(startDate,endDate,validatemessage)
{
    //alert("helloAA")
    var splitTaskStartDate = startDate.split('-');
    var taskAddStartDate = new Date(splitTaskStartDate[2], splitTaskStartDate[0]-1 , splitTaskStartDate[1]); //Y M D 
    var splitTaskEndDate = endDate.split('-');
    var taskAddtargetDate = new Date(splitTaskEndDate[2], splitTaskEndDate[0]-1, splitTaskEndDate[1]); //Y M D
    var taskStartDate = Date.parse(taskAddStartDate);
    var taskTargetDate= Date.parse(taskAddtargetDate);
    var  difference=(taskTargetDate - taskStartDate) / (86400000 * 7);
    if(difference<=0)
    {
             
        // alert("hi")
        $(validatemessage).html(" <b><font color='red'>Start date Must be less than End date</font></b>");
//        $("#startDate").css("border", "1px solid red");
//        $("#endDate").css("border", "1px solid red");
        $(validatemessage).show().delay(4000).fadeOut();
//         $("#startDate").show().delay(5000).fadeOut();
//          $("#endDate").show().delay(5000).fadeOut();
        return false;
    }  
   
}
function enterTimesheetDateRepository(id)
{ 
    $(id).val("");
    return false;
    
}


function deleteTimeSheet(id){
 
    swal({
    
        title: "Are You Sure to Delete The TimeSheet?",
  
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
            window.location='../timesheets/deleteTimesheet.action?timesheetid='+id;
//             swal("Deleted!", "TimeSheet Deleted Successfully....", "success");
    
        } else {
     
            swal("Cancelled", "Deletion cancelled ", "error");
 
      
        }
    });
}