<%--
    Document   : opportunity
    Created on : May 15, 2015, 3:48:56 PM
    Author     : Anton Franklin
--%>

<div>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>


    <style>
        .inputStyleCustom{
              width: 480px;
              margin-right: 100px;
        }

    </style>
    <script>
        //Pagination Script
        var pager;

        function pagerOption(){
            paginationSize = document.getElementById("paginationOption").value;
            pager = new Pager('projectResults', parseInt(paginationSize));
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        };
        //End Pagination Script

        //Search Opportunities
        function searchOpportunities(){

                $.ajax({url:"<%=request.getContextPath()%>/opportunitySearch.action"
                        +"?opportunityName=" + document.getElementById("opportunityName").value
                        +"&accountID= <s:property value="accountID"/>"
                    ,
                    success: function(data){
                        window.setTimeout("pagerOption();", 1000);
                        window.setTimeout("doOnLoad();", 1000);
                        $("#opportunities").html(data);
                    },
                    type: 'GET'
                });
            };

        function addOppotunity(){

                $.ajax({url:"<%=request.getContextPath()%>/addOpportunity.action"
                        +"?opportunityType=" + "AO"
                        +"&opportunityName=" + document.getElementById("opportunity_Name").value
                        +"&opportunityComments=" + document.getElementById("opportunityComments").value
                        +"&opportunityDesc=" + document.getElementById("opportunityDesc").value
                        +"&accountID= <s:property value="accountID"/>"
                    ,
                    success: function(data){
                        window.setTimeout("pagerOption();", 1000);
                        //window.setTimeout("doOnLoad();", 1000);
                        closePopup();
                        $("#opportunities").html(data);
                    },
                    type: 'GET'
                });
        };

        function resetOverlayForm(){
                document.getElementById("overlayForm").reset();
                //$("#projectNameError").html("Oppor name is valid.");
                //document.getElementById("projectNameError").style.display = "none";
       };

       function closePopup(){
                document.getElementById("clickHere").click();
       };
       $('#emailPhoneShow_popup').popup();
       $('#description_popup').popup();
    </script>

    <div>
        <div id="clickHere"></div>
        <div id="task_popup">
            <div id="taskBoxOverlay">

            <div style="background-color: #3BB9FF ">
              <table>
                <tr><td style=""><h4><font color="#ffffff">&nbsp;&nbsp;Add Opportunity&nbsp;&nbsp; </font></h4></td>
                <span class=" pull-right"><h5><a href="" class="task_popup_close"><i class="fa fa-times-circle-o fa-size"></i></a>&nbsp;</h5></span>
              </table>
            </div>

           <div style="width:fit-content">
               <s:form action="" id="overlayForm" theme="simple" namespace="/" onsubmit="">
                <table>
                    <!--Account ID, Opportunity Type, Opportunity Name, Opportunity Comments-->
                   <tr>
                        <div class="inner-addtaskdiv-elements " >
                            <label class="labelStyle field-margin">Opportunity Name </label><s:textfield  cssClass="inputStyleCustom" id="opportunity_Name" name="opportunityName" value="%{opportunityName}" size="40"/><label class="labelStyle" style="display: none; color: red; width: auto" id="opportunityNameError"></label>
                            <div style="color: red; margin-left: 40px;  width: auto" id="oppNameError"></div>
                        </div>
                   </tr>
                   <tr>
                        <div class="inner-addtaskdiv-elements " >
                            <label class="labelStyle field-margin">Opportunity Description </label><s:textarea  cssClass="inputStyleCustom" id="opportunityDesc" name="opportunityDesc" value="%{opportunityDesc}"/><label class="labelStyle" style="display: none; color: red; width: auto" id="opportunityDescError"></label>
                            <div class="labelStyle" style="color: red; margin-left: 40px; width: auto" id="oppDescError"></div>
                        </div>
                   </tr>
                   <tr>
                        <div class="inner-addtaskdiv-elements " >
                            <label class="labelStyle field-margin">Opportunity Comments </label><s:textarea  cssClass="inputStyleCustom" id="opportunityComments" name="opportunityComments" value="%{opportunityComments}"/><label class="labelStyle" style="display: none; color: red; width: auto" id="opportunityCommentsError"></label>
                        </div>
                   </tr>
                   <tr>
                       <div  class="inner-addtaskdiv-elements" style="text-align: right">
                            <s:reset cssClass="cssbutton " value="Clear" theme="simple" onclick="resetOverlayForm();"/>
                            <s:submit cssClass="cssbutton" value="Add Opportunity" theme="simple" onclick="return(validateForm() && addOppotunity())"/>
                       </div>
                   </tr>
               </table>
              </s:form>

            </div>
         </div>
       </div>

        <!---->
        <section id="generalForm">
            <div class="container">
                <div class="row">

                    <!--Content Start-->
                    <div class="col-md-10 col-md-offset-0" style="background-color:#fff">
                        <div class="features_items" >
                            <div class="col-lg-12 " >
                                <div class="" id="profileBox" style="margin-top: 5px; width: 90%">

                                    <!--Setting Background -->
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">

                                            <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                            <font color="#ffffff">Opportunities</font>

                                            </h4>
                                        </div>
                                    </div>

                                    <!---->
                                    <label class="labelStyle" style="color: green; width: auto;margin-left: 20px" id="opportunityAddResult"><s:property value="opportunityActionResponse"/></label>

                                    <div class="col-sm-12">

                                        <div style="width: 100%">
                                            <!--Search Area-->
                                            <s:form action="" theme="simple" method="GET" id="opportunitySearchForm" target="%{opportunity}">
                                                <br/>

                                                <table style="width: 100%">
                                                    <tr>
                                                        <td style="text-align: center">
                                                            <s:textfield cssClass="textbox" label="opportunityName" id="opportunityName" type="text" name="opportunityName"  placeholder="Opportunity Name"/>

                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </div>

                                        <div style="width: 100%">
                                            <s:submit type="submit" cssClass="cssbutton" value="Search" onclick="searchOpportunities();" align="center"/>
                                        </div>

                                        <div class="col-sm-12">

                                            <!--Email Popup-->
                                            <div id="emailPhoneShow_popup">
                                                <div id="emailPhoneShowBoxOverlay" >
                                                    <div style="background-color: #3bb9ff ; padding: 0px">
                                                        <table>
                                                            <tr><td><h4 style=""><font color="#ffffff">&nbsp;&nbsp;Employee Details&nbsp;&nbsp; </font></h4></td>
                                                            </tr>
                                                            <span class=" pull-right"><h5><a href="" class="emailPhoneShow_popup_close" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <form action="#" theme="simple" >
                                                            <div>
                                                                <div class="inner-reqdiv-elements">
                                                                    <table>
                                                                        <span><error></error></span>
                                                                        <s:textfield name="email"  label="Email-Id" id="email"  style="background-color:white;color:black;border:solid 1px #B0B0B0 ;" disabled="true" cssClass="form-control"/>
                                                                        <s:textfield name="contactNo"  label="Contact No" id="contactNo"  style="background-color:white;color:black;border:solid 1px #B0B0B0 ;" disabled="true" cssClass="form-control"/>

                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                            <!--Search Results-->
                                            <s:form id="opportunityResultsForm">
                                                <div style="width: fit-content">

                                                    <!--Pagination and Add Button-->
                                                    <div>
                                                        <label> Showing:
                                                            <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption();" style="width: auto">
                                                                <option>10</option>
                                                                <option>15</option>
                                                            </select>
                                                        </label>

                                                        <a href="../opportunity/opportunityDetails.jsp" class="task_popup_open" ><input type="button" class="cssbutton" value="Add Opportunity" style="float: right"></a>
                                                    </div>

                                                    <!--Search Results Table-->
                                                    <div class="emp_Content" id="emp_div" align="center" style="width:auto;margin: auto" >
                                                        <table id="opportunityResults" class="responsive CSSTable_task" border="5" style="width: 100%;margin: auto" >
                                                            <tr>
                                                                <th>Opportunity Name</th>
                                                                <th>Opportunity Description</th>
                                                                <th>Opportunity Comments</th>
                                                                <th>Created By</th>
                                                                <th>Edit</th>
                                                            </tr>
                                                            <s:if test="opportunities == null || opportunities.size() == 0">
                                                                <tr>
                                                                    <td colspan="9"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:iterator value="opportunities">
                                                                <tr>

                                                                    <s:url action="opportunityDetails.action" var="getDetails">
                                                                        <s:param name="opportunityID"><s:property value="opportunityID"/></s:param>
                                                                    </s:url>

                                                                    <td><s:property value="opportunityName"/></td>
                                                                    <td><s:a href="#" onclick="$(this).children().toggle();" class="description_popup_open"><span><s:property value="opportunityDescTitle"/></span><span style="display: none;"><s:property value="opportunityDesc"/></span></s:a></td>
                                                                    <td><s:property value="opportunityComments"/></td>
                                                                    <td><s:a href="#" onclick="getEmpMailPhone(%{opportunityCreatedBy});" cssClass="emailPhoneShow_popup_open"><s:property value="opportunityCreatedBy"/></s:a></td>
                                                                    <td><s:a href="%{getDetails}" style="color: red">Edit</s:a></td>

                                                                </tr>
                                                            </s:iterator>
                                                        </table>

                                                        <br/>
                                                        <div align="right" id="pageNavPosition" style="margin-right: 0vw;"></div>

                                                    </div>
                                                </div>
                                            </s:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>

    </div>


<script type="text/javascript">
        window.setTimeout("pagerOption();", 1000);
        window.setTimeout("doOnLoad();", 1000);
        setupTaskOverlay();

        var specialBox = document.getElementById('descriptionOverlay');
        if(specialBox.style.display == "block"){
            specialBox.style.display = "none";
        } else {
            specialBox.style.display = "block";
        }
        // Initialize the plugin
        $('#description_popup').popup();

        function validateForm() {

            var oppName = document.getElementById('opportunity_Name').value;
            var oppDesc = document.getElementById('opportunityDesc').value;

            if (oppName === '')
            {
                document.getElementById('oppNameError').innerHTML="Please Enter a vaild Opportunity Name";
                return false;
            }
            else{
                 document.getElementById('oppNameError').innerHTML="";
            }

            if(oppDesc === '')
            {
                 document.getElementById('oppDescError').innerHTML="Please Enter valid Description";
                 return false;
            }
            else{
                 document.getElementById('oppDescError').innerHTML="";
            }

            return true;
        }
</script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
</div>                                  