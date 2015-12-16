<%-- 
    Document   : LeavesSearch
    Created on : Apr 13, 2015, 7:25:55 AM
    Author     : NagireddySeerapu
--%>

<%@page import="com.mss.msp.usersdata.UserVTO"%>
<%@page import="com.mss.msp.usersdata.UsersdataHandlerAction"%>
<%@page import="java.util.Iterator"%>

<%--@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Add consultant Page</title>

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/media_queries.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>


        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/profilediv.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/taskiframe.css"/>">
        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/dhtmlxcalendar.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>">
        <script type="text/JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>

        <script type="text/javascript" src="<s:url value="/includes/js/Ajax/ConsultantAjax.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

    </head>
    <body style="overflow-x: hidden" onload="doOnLoadcnslt(); PermanentStateChange(); CurrentStateChange();">
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div>

        </header>

        <section id="generalForm" ><!--form-->
            <div class="container">
                <div class="row">
                    <s:include value="/includes/menu/LeftMenu.jsp"/> 
                    <!-- content start -->
                    <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                        <div class="features_items">

                            <div class="col-lg-12 ">

                                <div class="backgroundcolor" >
                                    <div class="panel-heading" id="" style="margin-top:6px">
                                        <h4 class="panel-title">
                                            <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                            <font style="color: #fff">Add Consultant</font>
                                            <span class="pull-right"><a href="../../general/changeMyPassword.action"><img  src="<s:url value="/includes/images/repeat.png"/>" height="25" width="25"></a></span>
                                        </h4>
                                    </div>
                                </div>
                                <s:if test="%{consultFlag =='success'}" >
                                    <font style="color: green"> Consultant Added successfully!</font>
                                </s:if>
                                <s:if test="%{consultFlag =='failure'}">
                                    <font style="color:firebrick">  Sorry, record not Added, try again!</font>
                                </s:if> 
                                <!-- Content start -->
                                <s:form action="addConsultantDetails" id="consultantForm" theme="simple" method="POST" enctype="multipart/form-data" >

                                    <span><addCnsltError></addCnsltError></span><br/>
                                    <span class="status"></span>

                                    <div class="panel-body" id="task-panel">
                                        <div style="margin-left: -13%">   
                                            <div class="col-sm-4 required ">
                                                <label class="labelStylereq" >Email Id:</label>    
                                                <s:textfield cssClass="form-control" name="consult_email" id="consult_email" placeholder="Email Id" required="true" oninvalid="setCustomValidity('Must be valid email')"   onchange="try{setCustomValidity('')}catch(e){}"   onblur="ConsultEmailValidation()" tabindex="1"  maxlength="60"/>                                                
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" >Available Date:</label>
                                                <s:textfield cssClass="form-control" name="consult_add_date"  id="consult_add_date"  placeholder="Date" onfocus="removeResultMessage()"    onkeypress="return enterDateRestrict();" onclick="availableValidate()" tabindex="1"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" >Available:</label>
                                                <s:select cssClass="form-control SelectBoxStyles " name="consult_available " id="consult_available" headerKey="" headerValue="Select availabilty" list="#@java.util.LinkedHashMap@{'Y':'Yes','PRM':'No'}"  onchange="availableValidation()" tabindex="1"/>
                                            </div>

                                            <div class="col-sm-4 required">
                                                <label class="labelStylereq" >First Name:</label>
                                                <s:textfield cssClass="form-control" name="consult_fstname" id="consult_fstname"  placeholder="First Name" required="true" onkeyup="fnamevalidate()" tabindex="2" maxlength="30"/>
                                            </div>
                                            <div class="col-sm-4 ">
                                                <label class="labelStylereq" >Middle Name:</label>  
                                                <s:textfield cssClass="form-control" name="consult_midname" placeholder="Middle Name" id="consult_midname" tabindex="2" maxlength="30"/>
                                            </div>
                                            <div class="col-sm-4 required">
                                                <label class="labelStylereq" >Last Name:</label>    
                                                <s:textfield cssClass="form-control" name="consult_lstname" id="consult_lstname" placeholder="Last Name"   required="true" onkeyup="lnamevalidate()" tabindex="2" maxlength="30"/>
                                            </div>
                                            <div class="col-sm-4 required ">
                                                <label class="labelStylereq" >Date of Birth:</label>
                                                <s:textfield cssClass="form-control" name="consult_dob"  id="consult_dob"   onkeypress="return enterDateRestrict();" onclick="dobValidate()" tabindex="3" placeholder="Date of Birth"/>
                                            </div>
                                            <div class="col-sm-4 ">
                                                <label class="labelStylereq" >Home Phone:</label>  
                                                <s:textfield cssClass="form-control" name="consult_homePhone" id="consult_homePhone" placeholder="Home Phone" tabindex="3"/>                                      
                                            </div>
                                            <div class="col-sm-4 required">
                                                <label class="labelStylereq" >Mobile No:</label>    
                                                <s:textfield cssClass="form-control" name="consult_mobileNo" id="consult_mobileNo" placeholder="Mobile Number"  required="true" onkeyup="mobValidation()" tabindex="3" />
                                            </div>
                                            <div class="col-sm-4 ">
                                                <label class="labelStylereq" >Alternate Email:</label>
                                                <s:textfield cssClass="form-control" name="consult_alterEmail" id="consult_alterEmail" placeholder="Alternate Email Id" onblur="consultValidAlterEmail();" tabindex="4" maxlength="60"/>
                                            </div>
                                            <div class="col-sm-4 ">
                                                <label class="labelStylereq" >SSN No:</label>    
                                                <s:textfield cssClass="form-control" name="consult_ssnNo" id="consult_ssnNo" placeholder="SSN Number"   tabindex="4" maxlength="20" />
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" >Living Country:</label>    
                                                <s:select cssClass="form-control SelectBoxStyles" name="consult_lcountry"  id="consult_lcountry" headerKey="" headerValue="Select Country" list="consult_WCountry" onchange="lcountryValidation()" tabindex="4"/>
                                            </div>
                                            <div class="col-sm-4 ">
                                                <label class="labelStylereq" >Linkedin link:</label>
                                                <s:textfield cssClass="form-control" name="consult_linkedInId" id="consult_linkedInId" placeholder="Linked In link"  tabindex="4" maxlength="120"/>

                                            </div>
                                            <div class="col-sm-4 ">
                                                <label class="labelStylereq" >Facebook link:</label>
                                                <s:textfield cssClass="form-control" name="consult_facebookId" id="consult_facebookId" placeholder="Facebook link"  tabindex="4" maxlength="120"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label class="labelStylereq" >Twitter link:</label>
                                                <s:textfield cssClass="form-control" name="consult_twitterId" id="consult_twitterId" placeholder="Twitter link"  tabindex="4" maxlength="120"/>
                                            </div>
                                            <div class="col-sm-4">
                                            <label class="labelStylereq" >Visa:</label>
                                            <s:select cssClass="form-control SelectBoxStyles"  headerKey="0" headerValue="Select Visa" name="consultantVisa" id="consultantVisa" list="#@java.util.LinkedHashMap@{'A-3':'A-3','G-5':'G-5','NATO-7,':'NATO-7','B-1':'B-1','H-1B':'H-1B','H-1B1':'H-1B1','H-1B':'H-1B','H-2A':'H-2A','H-2B':'H-2B','J-1':'J-1'}"  tabindex="10"/>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- Contact Information , start  -->
                                    <div class="col-sm-6" style="margin-left:0%">
                                        <span id="updateResultp">Permanent Address Updated successfully</span>
                                        <div id="AddressBox"> 
                                            <div class="contactInfoDiv">
                                                <table >
                                                    <tr id="trStyleContact"><td>&nbsp;&nbsp;Permanent Address &nbsp;&nbsp;</td></tr>

                                                </table>
                                            </div>
                                            <br/>
                                            <div class="col-lg-10 col-md-offset-1">
                                                <label class="labelStylereq" >Address:</label>
                                                <s:textfield cssClass="form-control" id="consult_Address" placeholder="Address" name="consult_Address"  oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}"  tabindex="5" onclick="disableCurrentAddress();" maxlength="100" />
                                                <label class="labelStylereq" >Address2:</label>        
                                                <s:textfield cssClass="form-control" id="consult_Address2" placeholder="Address2" name="consult_Address2" tabindex="5" onclick="disableCurrentAddress();" maxlength="100"/>
                                                <span class="required">
                                                    <label class="labelStylereq" >City:</label>        
                                                    <s:textfield cssClass="form-control" id="consult_City" placeholder="City" name="consult_City" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}" onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pcityValidation()" tabindex="5" onclick="disableCurrentAddress();" maxlength="20"/>
                                                    <label class="labelStylereq" >Country:</label>        
                                                    <s:select cssClass="form-control SelectBoxStyles" name="consult_Country" value="3" id="consult_Country" headerKey="-1" headerValue="Select Country" list="consult_WCountry" onchange="PermanentStateChange()" tabindex="5" onclick="disableCurrentAddress();"/>
                                                    <label class="labelStylereq" >State:</label>        
                                                    <s:select cssClass="form-control SelectBoxStyles" name="consult_State" id="consult_State"  headerKey="-1" headerValue="Select state" list="{}" listValue="getTranslation(value)" onchange="StateChangeValidation()" tabindex="5"/>  <%-- onchange="alert(this.options[this.selectedIndex].innerHTML)"  --%>
                                                </span>
                                                <label class="labelStylereq" >Zip:</label>        
                                                <s:textfield cssClass="form-control" id="consult_Zip" placeholder="Zip" name="consult_Zip" minLength="4"  maxlength="10" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}"  tabindex="5" onclick="disableCurrentAddress();"/> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <span id="updateResultc">Current Address Updated successfully</span>
                                        <div id="eduSecondry">
                                            <div class="contactInfoDiv" >
                                                <table >
                                                    <tr id="trStyleContact" ><td>&nbsp;&nbsp;Current Address &nbsp;&nbsp;</td></tr>
                                                </table>
                                            </div>
                                            <div id=""  class="col-lg-10 col-md-offset-1">
                                                <center>
                                                    <table>
                                                        <s:checkbox name="addconsult_checkAddress"  id="addconsult_checkAddress" onclick="FillAddress();"  ></s:checkbox>
                                                            <label class="checkboxLabel" for="addconsult_checkAddress">Same as Permanent Address</label>
                                                            <span><j2></j2></span>

                                                        </table>
                                                    </center>
                                                    <label class="labelStylereq" >Address:</label>    
                                                <s:textfield cssClass="form-control" id="consult_CAddress" placeholder="Address" name="consult_CAddress"  oninvalid="setCustomValidity('Must be valid fn')"   onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCAddressValidation()" tabindex="6"  maxlength="100"/>
                                                <label class="labelStylereq" >Address2:</label>    
                                                <s:textfield cssClass="form-control" id="consult_CAddress2" placeholder="Address2" name="consult_CAddress2" tabindex="6" maxlength="100"/>
                                                <span class="required">
                                                    <label class="labelStylereq" >City:</label>    
                                                    <s:textfield cssClass="form-control" id="consult_CCity" placeholder="City" name="consult_CCity" required="true" oninvalid="setCustomValidity('Must be valid fn')" pattern="[a-zA-Z]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCCityValidation()" tabindex="6" maxlength="20"/>
                                                    <label class="labelStylereq" >Country:</label> 
                                                    <s:select cssClass="form-control SelectBoxStyles" name="consult_CCountry" id="consult_CCountry" value="3" headerKey="-1" headerValue="Select Country" list="consult_WCountry" onchange="CurrentStateChange()" tabindex="6"/>
                                                    <label class="labelStylereq" >State:</label>    
                                                    <s:select cssClass="form-control SelectBoxStyles" id="consult_CState" name="consult_CState"    headerKey="-1" headerValue="Select state" list="{}" onchange="CurrentStateChangeValidation()" tabindex="6"/>
                                                </span>
                                                <label class="labelStylereq" >Zip:</label>   
                                                <s:textfield cssClass="form-control" id="consult_CZip" placeholder="Zip" name="consult_CZip" minLength="4" maxlength="10" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}"  tabindex="6"/>  <%--onkeyup="pCZipValidation()"--%>
                                                <%--s:textfield cssClass="form-control" label="Phone" id="consult_CPhone" name="consult_CPhone" required="true" oninvalid="setCustomValidity('Must be valid fn')"  onchange="try{setCustomValidity('')}catch(e){}" onkeyup="pCPhoneValidation()" tabindex="6"/--%>

                                            </div>
                                        </div>
                                    </div>                




                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label  class="task-label" style="max-height:10px;">Education:</label>
                                            <s:textarea cssClass="titleStyle"   id="consult_education" placeholder="Education" name="consult_education" maxlength="500" cols="100" rows="2" onkeyup="checkCharactersDescription(this)" tabindex="7"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-7 required">
                                        <label  class="labelStylereq" style="margin:-0px;">Skills:</label>


                                        <s:select cssClass="commentsStyle" name="skillCategoryValueList"  id="skillCategoryValue" list="skillValuesMap" multiple="true" onfocus="clearErrosMsgForGrouping()" value="%{requirementVTO.skillSetList}"/> 
                                        <s:hidden id="skillValues" name="skillValues" />
                                    </div>
                                    <span id="skills_feedback"></span>

                                </div>  
                                <div class="row"></div>
                                <div class="panel-body" id="task-panel"> 
                                    <div style="margin-left: -13%;">
                                        <div id="description_feedback"></div>
                                        <div  class="col-sm-4 "  >
                                            <span class="required">     
                                                <label class="labelStylereq" >Title:</label>   
                                                <s:textfield cssClass="form-control" name="consult_jobTitle" id="consult_jobTitle" placeholder="title" required="true" onkeyup="jobtitleValidate()" tabindex="8" maxlength="30"/>                                      
                                                <label class="labelStylereq" >Industry:</label>   
                                                <s:select cssClass="form-control SelectBoxStyles " name="consult_industry " id="consult_industry"   headerKey="" headerValue="Select Industry" list="industry"  required="true" onchange="industryValidate()" tabindex="9"/>
                                            </span> 
                                            <label class="labelStylereq" >Relocation:</label>       
                                            <s:select cssClass="form-control SelectBoxStyles" name="consult_relocation" id="consult_relocation" headerKey="-1" headerValue="Select Relocation" list="{'Yes','No'}" onchange="handleSelect(this)" tabindex="10"/>
                                        </div>
                                        <div class="col-sm-4 " >
                                            <span class="required">
                                                <label class="labelStylereq" >Experience:</label>
                                                <s:select cssClass="form-control SelectBoxStyles " name="consult_experience " id="consult_experience" headerKey="" headerValue="Select experience" list="experience" required="true" onchange="expValidate()" tabindex="8"/>
                                            </span>
                                            <label class="labelStylereq" >Referred By:</label>       
                                            <s:textfield cssClass="form-control" name="consult_referredBy" id="consult_referredBy" placeholder="Referred by"  tabindex="9" maxlength="30"/>
                                            <label class="labelStylereq pref_country" >Preferred Country:</label>       
                                            <s:select cssClass="form-control SelectBoxStyles" name="consult_pcountry" id="consult_pcountry" headerKey="-1" headerValue="Select Country" list="consult_WCountry" onchange="AddConsultantStateChange()" tabindex="9" />
                                            <%--<label class="labelStylereq" >Employee Position:</label>
                                           <s:select cssClass="form-control SelectBoxStyles"  headerKey="0" name="consult_position" id="consult_position" list="#@java.util.LinkedHashMap@{'CRP':'Contract','PRM':'Permanent'}"  tabindex="10"/>--%>
                                        </div>
                                        <div class="col-sm-4  " > 
                                            <span class="required">
                                                <label class="labelStylereq" >Working Country:</label>
                                                <s:select cssClass="form-control SelectBoxStyles" name="consult_wcountry" id="consult_wcountry"  headerKey="" headerValue="Select Country" list="consult_WCountry" required="true" onchange="workingCountryValidate()" tabindex="8"/>
                                            </span>
                                            <label class="labelStylereq" >Rate/Salary:</label>   
                                            <s:textfield cssClass="form-control" name="consult_salary" id="consult_salary" placeholder="Rate/Salary"   onkeyup="salValidate()" tabindex="9" maxlength="10"/>
                                            <label class="labelStylereq pref_state" >Preferred State:</label>       
                                            <s:select cssClass=" "  name="consult_preferredState " id="consult_preferredState" multiple  = "true"   headerKey="-1" headerValue="Select preferred state" list="{}" onclick=""  onchange="preState()" tabindex="5" oninvalid="setCustomValidity('Preferred State should not be selected more than 5')" />

                                            <s:hidden  id="PrefstateValues" name="PrefstateValues" />

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12">           
                                    <div class="form-group required">
                                        <label class="labelStylereq">Upload resume:</label><s:file  name="file" id="file" cssClass=""  tabindex="10" onblur="return resumeValidation();"/>
                                        <span id="fileSpanId" style="color:#4E4E4E">Upload PDF or Doc or Docx file.</span>
                                    </div> 
                                </div>
                                <%--<div class="" >
                                    <div class="form-group required">
                                        <label  class="labelStylereq" style="margin:-0px;">Skills:</label>
                                        <s:textarea cssClass="titleStyle "   id="consult_skill" name="consult_skill" maxlength="100" cols="100" rows="1" required="true" tabindex="12" onkeyup="skillValidation()" onkeydown="checkCharactersSkills(this)"/>
                                    </div>
                                    <span id="skills_feedback"></span>
                                </div> --%>
                                <div class="col-sm-12" >
                                    <div class="form-group">
                                        <label  class="labelStylereq" style="margin:-0px;">Comments:</label>
                                        <s:textarea cssClass="titleStyle "   id="consult_comments" placeholder="Comments" name="consult_comments" maxlength="500" cols="100" rows="2" onkeydown="checkCharactersComments(this)" tabindex="14"/>
                                    </div>
                                    <span id="comments_feedback"></span>
                                </div>

                                <div class="" >
                                    <div class="col-sm-2 pull-right">
                                        <s:reset cssClass="add_searchButton form-control fa fa-eraser" cssStyle="margin:5px 0px;" type="button" id="clearForm" value="reset" key="reset"  onclick="clearForm()"></s:reset>
                                        </div>

                                        <div class="col-sm-2 pull-right">
                                        <s:submit type="button" cssClass=" add_searchButton form-control fa fa-floppy-o" cssStyle="margin:5px 0px;" id="addConsult" value="save" onclick="return addConsultantValidate()"></s:submit>
                                        </div>

                                    </div>
                                </div>
                        </s:form>

                    </div>
                </div>

            </div>
            <!-- Content end  -->



        </section>    

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script>

        <script type="text/javascript">
   
            $("#consult_homePhone").mask("(999)-999-9999");
            $("#consult_mobileNo").mask("(999)-999-9999");  
            $("#consult_workPhone").mask("(999)-999-9999");
            $("#consult_Phone").mask("(999)-999-9999");  
            $("#consult_CPhone").mask("(999)-999-9999");
            function handleSelect(a)    
            {
            
                if(a.value=='Yes')
                {
                    //document.getElementById("consult_pcountry").disabled = false;
                    $("#consult_pcountry").show();
                    $(".pref_country").show();
                    $("#consult_pcountry").val('-1');    
                }
                else
                {
                    $("#consult_pcountry").hide();
                    $(".pref_country").hide();
                    $("#consult_preferredState").hide();
                    $(".pref_state").hide();
                    $("#PrefstateValues").val('');
                    /* document.getElementById("consult_pcountry").disabled = true;
                     document.getElementById('consult_pcountry').value=-1;
                     document.getElementById("consult_preferredState").disabled = true;
                     document.getElementById('consult_preferredState').value=-1;*/
                }
            }   
 
        </script>


        <script>
            $(document).ready(function() {
                $('#skillCategoryValue').selectivity({
                    
                    multiple: true,
                    placeholder: 'Type to search skills'
                });
            });
            
        </script>

        <style type="text/css">
            #consult_preferredState
            {
                display: none;
            }
            .pref_state
            {
                display: none;  
            }


            #consult_pcountry
            {
                display: none;
            }
            .pref_country
            {
                display: none;  
            }
        </style>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>