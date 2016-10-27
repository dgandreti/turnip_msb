<%-- 
    Document   : registration
    Created on : Feb 3, 2015, 7:50:55 PM
    Author     : Nagireddy
--%>


<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
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
        <title>ServicesBay :: Registration Page</title>

        <link rel="stylesheet"  href="<s:url value="/includes/css/general/jquery.idealforms.css"/>">

        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <!-- end of new styles -->
    </head>
    <body oncontextmenu="return false">
        <header id="header"><!--header-->
            <div class="header_top" id="header_top"><!--header_top-->
                <div class="container">
                    <s:include value="/includes/template/header.jsp"/> 
                </div>
            </div><!--/header_top-->

        </header><!--/header-->

        <div class="container">
            <div class="row">

                <div class="general-form"><!--login form-->
                  <h2>Registration Page</h2>
                    <div class="content">
                        <div class="idealsteps-container">
                            <nav class="idealsteps-nav"></nav>
                            <form action="" novalidate autocomplete="off" class="idealforms">
                                <div class="idealsteps-wrap">                                   
                                    <!-- Step 1 -->

                                    <section class="idealsteps-step">
                                         <div class="field">
                                               
                                        <div id="fileMessage"></div>
                                         </div>
                                         <div class="field">
                                            <label class="main">Living&nbsp;Country</label>
                                            <select name="livingCountry" id="livingCountry">
                                                <option value="India">India</option>
                                                  <option value="USA">USA</option>
                                                    <option value="UK">UK</option>
                                            </select>
                                            <span class="error"></span> </div> 
                                    <div class="field">
                                        <label class="main">Reg.Type</label>
                                        <select name="regType" id="regType">
                                            <option value="E"> Employee </option>
                                            <option value="CO"> Consultant</option>
                                        </select>
                                    </div>
                                        <div class="field">
                                            <label class="main">Login Id</label>
                                            <input name="loginId" id="loginId" type="email" placeholder="loginId" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">First Name </label>
                                            <input name="firstName" id="firstName" maxlength="30" type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Middle Name </label>
                                            <input name="middleName" id="middleName" type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Last Name </label>
                                            <input name="lastName" id="lastName" maxlength="30" type="text" >
                                            <span class="error"></span> </div>
                                             
                                        <div class="field">
                                            <label class="main">Gender</label>
                                            <p class="group">
                                                <label>
                                                    <input name="gender" type="radio" value="male">
                                                    Male</label>
                                                <label>
                                                    <input name="gender" type="radio" value="female">
                                                    Female</label>
                                            </p>
                                            <span class="error"></span> </div>
                                            <div class="field">
                                            <label class="main" id="text_alignment">Date of Birth</label>
                                            <input name="dob" id="dob" type="text" placeholder="mm/dd/yyyy" class="datepicker">
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Marital Status</label>
                                            <p class="group">
                                                <label>
                                                    <input name="maritalStatus" type="radio" value="single">
                                                    Single</label>
                                                <label>
                                                    <input name="maritalStatus" type="radio" value="married">
                                                    Married</label>
                                            </p>
                                            <span class="error"></span> </div>
                                       <div class="field">
                                            <label class="main">Profile Image</label>
                                            <input id="picture" name="picture" id="picture" type="file" multiple >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Phone</label>
                                            <input name="phone" id="phone" type="text">
                                            <span class="error"></span> </div>

                                        <div class="field buttons">
                                            <label class="main">&nbsp;</label>
                                            <!--<button type="button" class="prev">&laquo; Prev</button>-->
                                            <button type="button" class="next">Next &raquo;</button>
                                        </div>
                                    </section>

                                    <!-- Step 2 -->

                                     <section class="idealsteps-step">
                                         
                                         <label class="main" style="font-size:16px">Contact&nbsp;Address</label>
                                         <div class="field">
                                               <div id="load" style="color: green;display: none;">Loading..</div>
                                        <div id="resultMessage"></div>
                                         </div>
                                         
                                        <div class="field">
                                            <label class="main">Address line 1 </label>
                                            <input name="officeAddress1" id="officeAddress1" maxlength="100" type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Address line 2 </label>
                                            <input name="officeAddress2" id="officeAddress2" maxlength="100"  type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">City </label>
                                            <input name="officeCity" id="officeCity" maxlength="10" type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">State</label>
                                            <select name="options" id="officeState">
                                                <option value="default">&ndash; Select a State &ndash;</option>
                                                
                                                <%
                                                    Map hashmapStates = com.mss.msp.util.DefaultDataProvider.stateProvider();
                                                    Set<String> keys = hashmapStates.keySet();
                                                    
                                                    for (String key : keys) {

                                                        
                                                %>
                                                <option value="<%=key%>"> <%= hashmapStates.get(key) %></option>"
                                                <% }%>

                                            </select>
                                            <span class="error"></span> </div>
                                            <div class="field">
                                            <label class="main">Country </label>
                                            <input name="officeCountry" id="officeCountry" maxlength="10" type="text" >
                                            <span class="error"></span> </div>
                                            
                                        <div class="field">
                                            <label class="main">Zip</label>
                                            <input name="zip" id="zip" type="text" maxlength="5" placeholder="00000">
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Contact&nbsp;Phone</label>
                                            <input name="officePhone" id="officePhone" type="text" placeholder="">
                                            <span class="error"></span> </div>


                                      <%--  <div class="field">
                                            <label class="main"  style="font-size:16px" >Cont.Address :</label>
                                            <p class="group">
                                                <label>
                                                    <input name="same" type="checkbox" value="same" id="same">
                                                    Use same as office address</label>
                                                
                                                
                                            </p>
                                            <span class="error"></span> </div>

                                        <div class="field">
                                            <label class="main">Address&nbsp;Line 1: </label>
                                            <input name="coAddress1" id="coAddress1" maxlength="100" type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Address&nbsp;line 2: </label>
                                            <input name="coAddress2" id="coAddress2" maxlength="100" type="text" >
                                            <span class="error"></span> </div>
                                         <div class="field">
                                            <label class="main">City: </label>
                                            <input name="coCity" id="coCity" maxlength="10" type="text" >
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">State:</label>
                                            <select name="options" id="coState">
                                                <option value="default">&ndash; Select a State &ndash;</option>
                                                <% for (String key : keys) {
                                                %>
                                                <option value="<%=key%>"> <%= hashmapStates.get(key) %></option>"
                                                <% }%>
                                            </select>
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Zip:</label>
                                            <input name="coZip" id="coZip" maxlength="5" type="text" placeholder="00000">
                                            <span class="error"></span> </div>
                                        <div class="field">
                                            <label class="main">Office Phone:</label>
                                            <input name="coPhone" id="coPhone" type="text" placeholder="">
                                            <span class="error"></span> </div>
                                            
                                            
                                            --%>
                                            
                                            

                                        <div class="field buttons">
                                            <label class="main">&nbsp;</label>
                                            <button type="button" class="prev">&laquo; Prev</button>
                                           <!-- <button type="submit" class="submit">Submit</button> -->
                                            <button type="button" class="submit" onclick="return doRegister();">Submit</button>
                                        </div>


                                    </section>



                                </div>
                              
                            </form>
                        </div>
                    </div> <!-- Content-->

                </div><!--/login form-->


            </div>
        </div><!-- Container-->

        <footer id="footer"><!--Footer-->

            <div class="footer-bottom" id="footer_bottom">
                <div class="container">

                    <s:include value="/includes/template/footer.jsp"/>

                </div>
            </div>

        </footer><!--/Footer-->

        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery-ui.min.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.idealforms.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>  
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/ajaxfileupload.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/registration.js"/>"></script>
         <script>

            $('form.idealforms').idealforms({

              silentLoad: false,

              rules: {
                    'loginId': 'required email',
                    'firstName': 'required firstName',
                    'lastName': 'required lastName',
                    'picture': 'required extension:jpg:png',
                    'gender': 'minoption:1',
                    'maritalStatus': 'minoption:1',
                    'dob': 'required date',
                    'phone': 'required phone',
                    'officePhone': 'required phone',
                   // 'coPhone':'required phone',
                    'officeAddress1': 'required address',
                  //  'coAddress1': 'required address',
                    'officeCity': 'required city',
                   // 'coCity': 'required city',
                    'officeState': 'required',
                     'officeCountry': 'required country',
                    'zip': 'required zip',
                    //'coZip': 'required zip',
                    'options': 'select:default'
              },

              errors: {
                'LoginId': {
                  ajaxError: 'LoginId not available'
                }
              },
             
              onSubmit: function(invalid, e) {
                e.preventDefault();
                $('#invalid')
                  .show()
                  .toggleClass('valid', ! invalid)
                  .text(invalid ? (invalid +' invalid fields') : 'All good!');
              }
            });

           

            $('form.idealforms').find('input, select, textarea').on('change keyup', function() {
              $('#invalid').hide();
            });

            $('form.idealforms').idealforms('addRules', {
              'comments': 'required minmax:50:200'
            });

            $('.prev').click(function(){
              $('.prev').show();
              $('form.idealforms').idealforms('prevStep');
            });
            $('.next').click(function(){
              $('.next').show();
              $('form.idealforms').idealforms('nextStep');
            });

        </script>
       <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.maskedinput.js"/>"></script> 

    <script type="text/javascript">
        jQuery(function($){
        $("#date").mask("99/99/9999",{placeholder:"mm/dd/yyyy"});
         $("#phone").mask("(999)-999-9999",{placeholder:""});
         $("#officePhone").mask("(999)-999-9999",{placeholder:""});
           $("#coPhone").mask("(999)-999-9999",{placeholder:""});

        });
    </script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>