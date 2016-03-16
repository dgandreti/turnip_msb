/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function fileValidation() {
    document.getElementById('fileMessage').innerHTML ='';
    var imagePath =document.getElementById('picture').value;
    // alert(imagePath);
    if(imagePath.length<30){
        // alert("imagePath-->"+imagePath);
        if(imagePath != null && (imagePath !="")) {
                    

            var extension = imagePath.substring(imagePath.lastIndexOf('.')+1);

            if(extension=="jpg"|| extension=="png"||extension=="jpeg"){
                // document.imageForm.imagePath.focus();
                var size = document.getElementById('picture').files[0].size;
                //  alert("size-->"+size);
                // alert("size in mb-->"+(parseInt(size)/1000000));
                //if((parseInt(size)/1000000)<2) {
                if(parseInt(size)<2000000) {
                    return (true);
                }else {
                    document.getElementById('picture').value = "";
                    // alert("File size must be less than 1 MB.");
                    document.getElementById('fileMessage').innerHTML = "<font color=red>File size must be less than 2 MB.</font>"
                    return (false);
                }
            }else {
                document.getElementById('picture').value = "";
                document.getElementById('fileMessage').innerHTML = "<font color=red>Invalid file extension!Please select jpg or jpeg or png file.</font>"
                // alert("Invalid file extension!Please select pdf or jpg or png file.");
                return (false);
            }
        }
    }else {
        document.getElementById('fileMessage').innerHTML = "<font color=red>File name must be less than 30 characters!</font>"
        //alert("File name must be less than 30 characters!");
        document.getElementById('picture').value = "";
    }
    return (false);
};
            
            
            
            
            
function doRegister()
{
    //  alert("Do regisetr");
    // alert("in file uploading ------");
    //   var objType = document.getElementById("fileType").value;
    var loginId = document.getElementById("loginId").value;
    //  var password = document.getElementById("password").value;
    //  var confirmpass = document.getElementById("confirmpass").value;
    var firstName = document.getElementById("firstName").value;
    var middleName = document.getElementById("middleName").value;
    var lastName = document.getElementById("lastName").value;
    var regType=document.getElementById("regType").value;
    var gender = document.getElementsByName('gender');
    var gender_value;
    for(var i = 0; i < gender.length; i++){
        if(gender[i].checked){
            gender_value = gender[i].value;
        }
    }
    
    
    var maritalStatus = document.getElementsByName('maritalStatus');
    var maritalStatus_value;
    for(var i = 0; i < maritalStatus.length; i++){
        if(maritalStatus[i].checked){
            maritalStatus_value = maritalStatus[i].value;
        }
    }
    
    
    var dob = document.getElementById("dob").value;
    var phone = document.getElementById("phone").value;
    var officeAddress1 = document.getElementById("officeAddress1").value;
    var officeAddress2 = document.getElementById("officeAddress2").value;
    var officeCity = document.getElementById("officeCity").value;
    var officeState = document.getElementById("officeState").value;
    var officeCountry = document.getElementById("officeCountry").value;
    var livingCountry = document.getElementById("livingCountry").value;
    
    var zip = document.getElementById("zip").value;
    var officePhone = document.getElementById("officePhone").value;
    /*  var same = document.getElementById("same").value;
        var coAddress1 = document.getElementById("coAddress1").value;
         var coAddress2 = document.getElementById("coAddress2").value;
          var coCity = document.getElementById("coCity").value;
           var coState = document.getElementById("coState").value;
           var coZip = document.getElementById("coZip").value;
           var coPhone = document.getElementById("coPhone").value;*/
           
    /*alert("email-->"+email);
    alert("password-->"+password);
    alert("firstName-->"+firstName);
    alert("middleName-->"+middleName);
    alert("lastName-->"+lastName);
    alert("gender_value-->"+gender_value);
    alert("maritalStatus_value-->"+maritalStatus_value);
    alert("dob-->"+dob);
    alert("phone-->"+phone);
    alert("officeAddress1-->"+officeAddress1);
    alert("officeAddress2-->"+officeAddress2);
    alert("officeCity-->"+officeCity);
    alert("officeState-->"+officeState);
    alert("zip-->"+zip);
    alert("same-->"+same);
    alert("coAddress1-->"+coAddress1);
    alert("coAddress2-->"+coAddress2);
    alert("coCity-->"+coCity);
    alert("coState-->"+coState);
    alert("coZip-->"+coZip);
    alert("coPhone-->"+coPhone);
    */
    // alert("officeCity-->"+officeCountry);
    document.getElementById("load").style.display = 'block';
    $.ajaxFileUpload({
        url:'doUserRegister.action?loginId='+loginId+'&firstName='+firstName+'&middleName='+middleName+'&lastName='+lastName+'&gender='+gender_value+'&maritalStatus='+maritalStatus_value+'&dob='+dob+'&phone='+phone+'&officeAddress1='+officeAddress1+'&officeAddress2='+officeAddress2+'&officeCity='+officeCity+'&officeState='+officeState+'&officeCountry='+officeCountry+'&zip='+zip+'&officePhone='+officePhone+'&regType='+regType+'&livingCountry='+livingCountry,
        secureuri:false,//false
        fileElementId:'picture',//id  <input type="file" id="file" name="file" />
        dataType: 'json',// json
        success: function(data,status){
            // alert("Data-->"+data);
            var displaymessage = "<font color=red>Please try again later</font>";
            // alert("Data index-->"+data.indexOf("uploaded"));
            //  alert("success-->11111-->"+data);
            // var json = $.parseJSON(data);
            if(parseInt(data.indexOf("uploaded"))>0){
                //alert("uploaded");
                displaymessage = "<font color=green>File uploaded Successfully!</font>";
                var  resultMessage = "<font color=green>Registration Complted successfully!</font>";
                window.location="/MSB/general/regSuccessDirect.action?resultMessage="+resultMessage;
               
               
            }
            else if(data.indexOf("Notvalid")>0){
                // alert("not valid");
                displaymessage = "<font color=red>Not a valid file!,Please check the file and try again.</font>";
            }
            else if(data.indexOf("Error")>0){
                // alert("Erro");
                displaymessage = "<font color=red>Internal Error!, Please try again later.</font>"
            }
           
            //alert(data.indexOf("uploaded"));
           
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = displaymessage;//"<font color=green>File uploaded successfully</font>";
        // document.getElementById('fileType').value = "";
        },
        error: function(e){
            //  alert('Error:111 ');
            document.getElementById("load").style.display = 'none';
            document.getElementById('resultMessage').innerHTML = "<font color=red>Please try again later</font>";
              
                
        }
    });
    
   
		
    return false;

}
$("#zip").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
        return false;
    }
});