/*
 * Does an ajax call to get the states for a given country.
 */

function getStates(countryName, stateDropDownId)
{
  $.ajax({
    type: "POST",
    url:"../location/getStates",
    data: {
      countryId: countryName
    },
    contentType: "application/x-www-form-urlencoded; charset=utf-8",
    success:function(data){
//    console.log(data);
    content='<option value="">Select State</option>';;
    data.forEach(function(state){
      content+='<option value=\''+state.id+'\'>'+state.name+'</option>';
    });
    $(stateDropDownId).children().remove();
    $(stateDropDownId).append(content);


  }
  });
}

function getStockSymbol(countryId,stateName)
{
  $.ajax({
    type: "POST",
    url:"../location/getStockSymbol",
    data: {
      countryId: countryId
    },
    contentType: "application/x-www-form-urlencoded; charset=utf-8",
    success:function(data){
//    console.log(data);
      $('#acc_stock_symbol').val(data);
     $('#stock_symbol').val(data);
  }
  });

}
