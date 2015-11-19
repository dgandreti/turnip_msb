/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm(opportunityName, opportunityDesc) {
    
    var oppName = document.getElementById(opportunityName).value;
    var oppDesc = document.getElementById(opportunityDesc).value;
    
    if (oppName === '') 
    {
        document.getElementById('opportunityNameError').innerHTML="Please Enter a vaild Opportunity Name";
        return false;
    }
    else
    {
        document.getElementById('opportunityNameError').innerHTML="";
    } 
                
    if(oppDesc === '')
    {
        document.getElementById('opportunityDescError').innerHTML="Please Enter valid Description";
        return false;
    }
    else
    {
        document.getElementById('opportunityDescError').innerHTML="";
    }
                
    return true;
}
