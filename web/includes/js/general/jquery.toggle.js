

$(window).load(function(){

    

    $('#accordian_my div a').click(function()
    {
        $(this).find('span i').toggleClass('fa fa-sort-asc fa fa-sort-desc ')
        $("#accordian_services div a ").find('span i').removeClass('fa fa-sort-desc').addClass('fa fa-sort-asc')
        $("#accordian_team div a").find('span i').removeClass('fa fa-sort-desc').addClass('fa fa-sort-asc')    
    })
    $('#accordian_team div a').click(function()
    {
        $(this).find('span i').toggleClass('fa fa-sort-asc fa fa-sort-desc ')
        $("#accordian_my div a ").find('span i').removeClass('fa fa-sort-desc').addClass('fa fa-sort-asc')
        $("#accordian_services div a").find('span i').removeClass('fa fa-sort-desc').addClass('fa fa-sort-asc')
    })
    $('#accordian_services div a').click(function()
    {
        $(this).find('span i').toggleClass('fa fa-sort-asc fa fa-sort-desc')
        $("#accordian_my div a").find('span i').removeClass('fa fa-sort-desc').addClass('fa fa-sort-asc')
        $("#accordian_team div a").find('span i').removeClass('fa fa-sort-desc').addClass('fa fa-sort-asc')
    })
    $('#accordian_contact a').click(function()
    {
        $(this).find('i').toggleClass('fa fa-angle-double-down fa fa-angle-double-up ') 
        $("#accordian_my div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_team div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_services div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
    })
      $('#accordian_educaton div a').click(function()
    {
        $(this).find('i').toggleClass('fa fa-angle-double-down fa fa-angle-double-up ') 
        $("#accordian_my div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_team div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_services div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
    })
    $('#accordian_skill div a').click(function()
    {
        $(this).find('i').toggleClass('fa fa-angle-double-down fa fa-angle-double-up ') 
        $("#accordian_my div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_team div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_services div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
    })
        
    $('#accordian_security div a').click(function()
    {
        $(this).find('i').toggleClass('fa fa-angle-double-down fa fa-angle-double-up ') 
        $("#accordian_my div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_team div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
        $("#accordian_services div a").find('i').removeClass('fa fa-minus').addClass('fa fa-plus')
    })
    $('#education-info').click(function(){
        $('#Educationslide').slideToggle();       
        $('#Addresspop').slideUp();
        $('#Skillslide').slideUp();
    });
    $('#contact-info').click(function(){
       
        $('#Addresspop').slideToggle();
        $('#Educationslide').slideUp();
        $('#Skillslide').slideUp();
      });
    $('#skill-info').click(function(){
        $('#Skillslide').slideToggle();
        $('#Addresspop').slideUp();
        $('#Educationslide').slideUp();
      });
})
 

