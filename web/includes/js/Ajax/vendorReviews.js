/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function addVendorRatingAndReveiw(){
    var comment=$("#comment").val();
    var reviewName=$("#reviewName").val();
    var vendorOrgId=$("#vendorOrgId").val();
    var ratingValue = $("#ratingStar").val();
    var accountName=$("#accName").val();
    var url = 'Requirements/addVendorRatingAndReveiw.action?reviewComments='+comment+
    '&vendorOrgId='+vendorOrgId+'&reviewName='+reviewName+'&reviewRating='+ratingValue;
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if(req.responseText=="success"){
                alert("success");
                window.location=".././Requirements/vendorRatingAndReveiw.action?vendorOrgId="+vendorOrgId+"&accountName="+accountName;
            } 
            else{
                alert("fail")
            }
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
    return false;
}

function showPercentage(){
    var c5=document.getElementById('count5').value;
    var c4=document.getElementById('count4').value;
    var c3=document.getElementById('count3').value;
    var c2=document.getElementById('count2').value;
    var c1=document.getElementById('count1').value;
    var avg=document.getElementById('avg').value;
    var c=document.getElementById('totalCount').value;
    c5=(c5/c)*100;
    c4=(c4/c)*100;
    c3=(c3/c)*100;
    c2=(c2/c)*100;
    c1=(c1/c)*100;
    $("#total").html("&nbsp;Total:&nbsp;"+c);
    document.getElementById("bar5").style.width=c5+"%";
    document.getElementById("bar4").style.width=c4+"%";
    document.getElementById("bar3").style.width=c3+"%";
    document.getElementById("bar2").style.width=c2+"%";
    document.getElementById("bar1").style.width=c1+"%";
    var avg1=avg;
    var className;
    $("#avgValue").html(avg1);
    for(var i =0; i<avg1;i++)
    {
        className= "glyphicon glyphicon-star";
        $("#ratingS").append('<span class="'+className+'" id="avgRating"'+i+'/>');
    }
    for(var i=5;i>avg1;i--){
        className = "glyphicon glyphicon-star-empty";
        $("#ratingS").append('<span class="'+className+'"/>');
    }
}
function editStars(reviewRating,k){
    var className;
    for(var i =0; i<reviewRating;i++)
    { 
        className= "glyphicon glyphicon-star";
        $("#reviewRating"+k).append('<span class="'+className+'" id="avgRating"'+i+'/>');
    }
    for(var i=5;i>reviewRating;i--){
        className = "glyphicon glyphicon-star-empty";
        $("#reviewRating"+k).append('<span class="'+className+'"/>');
    }
}
function showLogo(){
    var url = '.././Requirements/getImgPath.action';
    var req = initRequest(url);
    req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
            if(req.responseText==""||req.responseText=='null'){
                document.getElementById("imgLogo").src='../renderImage/rImage.action?path='+req.responseText;
            }
            {
                document.getElementById("imgLogo").src='../renderImage/rImage.action?path='+req.responseText;
            }
        }
    };
    req.open("GET", url, "true");
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(null);
}

function titleCheck(textBoxId,errorTextId){
  if($(textBoxId).val() == ''){
    $(errorTextId).html('<span>You must enter the title</span>');
    $(textBoxId).css('border', '1px solid red');
    setTimeout(function(){
      $(textBoxId).css('border', '');
      $(errorTextId).children().remove();
    },3000);
    return;
  }
}
function commentCheck(textBoxId,errorTextId){
  if($(textBoxId).val() == ''){
    $(errorTextId).html('<span>You must enter the comment</span>');
    $(textBoxId).css('border', '1px solid red');
    setTimeout(function(){
      $(textBoxId).css('border', '');
      $(errorTextId).children().remove();
    },3000);
    return;
  }
}