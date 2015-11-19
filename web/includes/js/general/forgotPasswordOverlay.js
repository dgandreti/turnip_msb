
 function forgotPassword()
            {
             
             var email=(document).getElementById('forgotEmailId').value;
                var  dataString ="email=" + email;
                //alert("inAjax  "+email);
            $.ajax(
                {
                    url:'forgotPassword.action?email='+email,
                    //secureuri:false,
                   dataType: 'json',
                  //  data:'email',
                    success: function (data) 
                    {
                       // alert("in success  "+data);
                        
                        console.log(""+data);
                        //alert(data.message);//jsonmessage,messagestruts2
                        // $("#testImg").attr("src",data.message);
                        //  $("#upload").attr('disabled',true);
                        if(typeof(data.error) != 'undefined')
                        {
                            if(data.error != '')
                            {
                                alert("in eror  "+data.error);
                            }else
                            {
                                alert("in error1  "+data.message);
                            }
                        }
                    },
                    error: function (data, status, e)//
                    {
                        alert("error:"+e);
                    }
                }
            )
		
                return false;

            }

