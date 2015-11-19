$(document).ready(function() {
setupTaskOverlay()
});

function setupTaskOverlay(){
   var specialBox = document.getElementById('taskBoxOverlay');
    if(specialBox.style.display == "block"){
        specialBox.style.display = "none";
    } else {
        specialBox.style.display = "block";
    }
    // Initialize the plugin
    $('#task_popup').popup(
        );

    $('#alert-content').hide();
    $('#alert-check').change(function(){
        //alert("ajax called")

        if($(this).is(":checked"))
            $('#alert-content').show();
        else
            $('#alert-content').hide();

    });
}






//The below function checks the characters remaining in the textarea.
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
}
