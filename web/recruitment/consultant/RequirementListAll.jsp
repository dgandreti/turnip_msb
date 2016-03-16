<%-- 
Document   : All Requirement List
Created on : May 7, 2015
Author     : Praveen<pkatru@miraclesoft.com>
--%>

<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- new styles -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ServicesBay :: Requirement List For All</title>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/GridStyle.css"/>">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/profilediv.css"/>'>
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar.css"/>' type="text/css">
        <link rel="stylesheet" href='<s:url value="/includes/css/general/dhtmlxcalendar_omega.css"/>' type="text/css">
        <link rel="stylesheet" type="text/css" href='<s:url value="/includes/css/general/requirementStyle.css"/>'>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/general/selectivity-full.css"/>">

        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/GridNavigation.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>

        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/main.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/CountriesAjax.js"/>"></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/dhtmlxcalendar.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/EmployeeProfile.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/Ajax/requirement.js"/>'></script>
        <script language="JavaScript" src='<s:url value="/includes/js/general/sortable.js"/>'></script>

        <%--script type="text/javascript" src="<s:url value="/includes/js/Ajax/vendorAjax.js"/>"></script--%>




        <script type="text/javascript">
            ;function sortables_init() {
                // Find all tables with class sortable and make them sortable
                if (!document.getElementsByTagName) return;
                tbls = document.getElementById("accountSearchResults");
                sortableTableRows = document.getElementById("reqTableInRecruiter").rows;
                sortableTableName = "reqTableInRecruiter";
                for (ti=0;ti<tbls.rows.length;ti++) {
                    thisTbl = tbls[ti];
                    if (((' '+thisTbl.className+' ').indexOf("sortable") != -1) && (thisTbl.id)) {
                        ts_makeSortable(thisTbl);
                    }
                }
            };
            
            function submmition(){
                var skillCategoryArry = [];    
                $("#skillCategoryValue :selected").each(function(){
                    skillCategoryArry.push($(this).val()); 
                });
                // alert("skillCategoryArry");
                document.getElementById("skillValues").value=skillCategoryArry;
                var v=document.getElementById("skillValues").value;
    
                alert(v);
                return true;
                //  alert(skillCategoryArry);
                //  document.getElementById("consultantForm").submit(); 
            }
            
        </script>

        <style>
            .requirementPopup img{
               margin-left: -4%;
            }

        </style>


    </head>
    <body style="overflow-x: hidden" onload="doOnLoadReqList(); loadPopup();reqPagination();">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/>
                    </div>
                </div>
            </header>

            <div id="main">
                <div id="addVendorConsultant_popup">
                    <div id="addVendorConsultantOverlay" >
                        <div style="background-color: #3bb9ff ; padding: 0px">
                            <table>
                                <tr><td><h4 style=""><font color="#ffffff">&nbsp;&nbsp;Add Consultant Details&nbsp;&nbsp; </font></h4></td>
                                </tr>
                                <span class=" pull-right"><h5><a href="#"  class="addVendorConsultant_popup_close" onclick="storeReqIdinOverlay(21)"><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                            </table>
                        </div>
                        <div>
                            <s:form action="" theme="simple" >
                                <div id="mainAddConsultantDiv">
                                    <span><e1></e1></span>
                                    <div class="innerAddConElements">
                                        <s:hidden name="conId" id="conId" ></s:hidden>
                                        <s:hidden name="reqId" id="reqId" ></s:hidden>
                                        <s:hidden name="orgid" id="orgid" ></s:hidden>
                                        <label class="labelStyleAddCon">Email</label><s:textfield name="email" id="conEmail" theme="simple" cssClass="addConInputStyle" onblur="getEmailExistance();" onclick="clearConultantAddOverlay()"/><font color="red">*</font>
                                    </div>
                                    <div class="innerAddConElements">
                                        <label class="labelStyleAddCon">ProofType</label><s:select id="proofType" cssClass="reqSelectStyle" name="proofType" list="#@java.util.LinkedHashMap@{'N':'--Select--','PN':'PAN','PP':'Passport'}" onchange="setPPorPAN(this.value);" onclick="clearConultantAddOverlay()"/>
                                    </div>
                                    <div>
                                        <div class="innerAddConElements" id="ppId">
                                            <label class="labelStyleAddCon">PassPortNo</label><s:textfield name="ppno" id="ppno" theme="simple" cssClass="addConInputStyle" onclick="clearConultantAddOverlay()"/>
                                        </div>
                                        <div class="innerAddConElements" id="panId">
                                            <label class="labelStyleAddCon">Pan no</label><s:textfield name="pan" id="pan" theme="simple" cssClass="addConInputStyle" onclick="clearConultantAddOverlay()"/>
                                        </div>
                                        <div class="innerAddConElements">
                                            <label class="labelStyleAddCon">Rate/Hr</label><span>$</span><s:textfield name="ratePerHour" id="ratePerHour" theme="simple" cssClass="addConInputStyle80" onclick="clearConultantAddOverlay()"/><font color="red">*</font>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <s:submit id="addConSubmit" cssClass="cssbutton" value="Submit" onclick="return storeProofData()" />
                                    </div>
                                </div>
                            </s:form>
                        </div> 
                    </div>
                </div>

                <%-- model window popup --%>

                <%-- model window popup --%>
                <section id="generalForm"><!--form-->
                    <div  class="container">
                        <div class="row">
                            <!-- content start -->
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <div class="col-sm-12 col-md-9 col-lg-9 right_content" style="background-color:#fff">
                                <div class="features_items">
                                    <div class="col-sm-12">
                                        <div class="" id="selectivityProfileBox" style="">
                                            <br>
                                            <div class=""  style="float: left; margin-top:-12px; margin-bottom: 20px;">
                                                Account&nbsp;Name:                                          
                                                <span style="color: #FF8A14"><s:property value="%{account_name}"/></span>
                                            </div> 
                                            <br>
                                            <div class="backgroundcolor" >
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <!--<span class="pull-right"><a href="" class="profile_popup_open" ><font color="#DE9E2F"><b>Edit</b></font></a></span>-->
                                                        <font color="#ffffff">Requirement List </font>
                                                        <i id="updownArrow" onclick="toggleContent('RequirementListAllForm')" class="fa fa-minus"></i>
                                                    </h4>
                                                </div>
                                            </div>
                                            <span id="requirementValidation"></span>
                                            <!-- content start -->
                                            <div class="col-sm-12">
                                                <div class="row">
                                                    <s:form  theme="simple" id="RequirementListAllForm">
                                                        <s:hidden name="sessionPRole" id="sessionPRole" value="%{#session.primaryrole}"/>
                                                        <s:hidden name="vendor" id="vendor" value="%{vendor}" ></s:hidden>
                                                        <s:hidden name="accountFlag" id="accountFlag" value="%{accountFlag}" ></s:hidden>
                                                        <s:hidden name="customerFlag" id="customerFlag" value="%{customerFlag}" ></s:hidden> 

                                                            <div class="col-sm-12">
                                                                <div class="row">
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq " style="color:#56a5ec;">Job Id</label>
                                                                    <s:textfield cssClass="form-control" tabindex="1" name="jdId" id="jdId" placeholder="Job Id"/>
                                                                    <%--<s:select id="jobTitle" name="jobTitle" cssClass="reqSelectStyle" headerKey="-1" headerValue="jobTitle" theme="simple" list="{'Developer','Tester','Manager'}" />--%>
                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <label class="labelStylereq " style="color:#56a5ec;">Job Title</label>
                                                                    <s:textfield cssClass="form-control" tabindex="2" name="jobTitle" id="jobTitle" placeholder="Job Title"/>
                                                                    <%--<s:select id="jobTitle" name="jobTitle" cssClass="reqSelectStyle" headerKey="-1" headerValue="jobTitle" theme="simple" list="{'Developer','Tester','Manager'}" />--%>
                                                                </div>  
                                                                <s:if test="#session.primaryrole==13 || #session.primaryrole==3 || #session.primaryrole==9 || #session.primaryrole==5">
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq " style="color:#56a5ec;">Req.Category</label>
                                                                        <s:select id="reqCategoryValue" tabindex="3" name="reqCategoryValue" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="All" theme="simple" list="%{reqCategory}" />
                                                                    </div>
                                                                </s:if>
                                                                <s:if test="vendor=='yes'">
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color:#56a5ec;">Customer&nbsp;Name</label>
                                                                        <s:textfield cssClass=" form-control " name="customerName" placeholder="Customer Name"  id="customerName" tabindex="4"/>
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color:#56a5ec;">Start Date</label>  
                                                                        <div class="calImage"><s:textfield cssClass=" form-control  " name="reqStart" id="reqStart" placeholder="FromDate"  tabindex="5"  onkeypress="return enterDateRepository(this);" onfocus="return removeEndDateErrorMsg();"><i class="fa fa-calendar"></i></s:textfield>
                                                                            </div></div>
                                                                        <div class="col-sm-4">
                                                                            <label class="labelStylereq" style="color:#56a5ec;">End Date</label>
                                                                            <div class="calImage"><s:textfield cssClass=" form-control  " name="reqEnd" placeholder="ToDate"  id="reqEnd" tabindex="6"  onkeypress="return enterDateRepository(this);" onfocus="return removeEndDateErrorMsg();"><i class="fa fa-calendar"></i></s:textfield>
                                                                            </div></div>
                                                                    </s:if>
                                                                    <s:if test="#session.primaryrole==13">
                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq " style="color:#56a5ec;">Created By</label>
                                                                        <s:select id="reqCreatedBy" name="reqCreatedBy" tabindex="7" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="--Select--" theme="simple" list="%{reqCreatedByMap}" />
                                                                    </div>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:hidden id="reqCreatedBy" name="reqCreatedBy" value="-1"/>
                                                                </s:else>





                                                                <s:if test="vendor!='yes'">
                                                                    <div class="col-sm-4">
                                                                        <%--<label class="labelStylereq " style="color:#56a5ec;">Status:</label>
                                                                        <s:select id="requirementStatus" name="requirementStatus" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="All" theme="simple" list="#@java.util.LinkedHashMap@{'R':'Released','C':'Closed'}" />--%>
                                                                        <label class="labelStylereq" style="color:#56a5ec;">Status</label>
                                                                        <s:select id="requirementStatus" name="requirementStatus" tabindex="8" cssClass="SelectBoxStyles form-control" headerKey="-1" headerValue="All" theme="simple" list="#@java.util.LinkedHashMap@{'O':'Opened','R':'Released','OR':'Open for Resume','C':'Closed','F':'Forecast','I':'Inprogess','H':'Hold','W':'Withdrawn','S':'Won','L':'Lost'}" />
                                                                    </div>

                                                                    <div class="col-sm-4">
                                                                        <label class="labelStylereq" style="color:#56a5ec;">Start Date</label>  
                                                                        <div class="calImage"><s:textfield cssClass=" form-control " name="reqStart" id="reqStart" placeholder="FromDate"  tabindex="9"  onkeypress="return enterDateRepository(this);" onfocus="return removeEndDateErrorMsg();"><i class="fa fa-calendar"></i></s:textfield>
                                                                            </div></div>
                                                                        <div class="col-sm-4">
                                                                            <label class="labelStylereq" style="color:#56a5ec;">End Date</label>
                                                                            <div class="calImage"><s:textfield cssClass=" form-control " name="reqEnd" placeholder="ToDate"  id="reqEnd" tabindex="10"  onkeypress="return enterDateRepository(this);" onfocus="return removeEndDateErrorMsg();"><i class="fa fa-calendar"></i></s:textfield>
                                                                            </div></div>
                                                                    </s:if>
                                                                <div class="col-sm-6">
                                                                    <label class="labelStyle" id="labelLevelStatusReq">Specialization </label>
                                                                    <s:select cssClass="" name="skillCategoryValue" tabindex="11" id="skillCategoryValue" list="skillValuesMap" multiple="true"/> 

                                                                </div>


                                                                <div class="col-sm-6 pull-right">


                                                                    <div class="pull-right btn_alignment">

                                                                        <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                        <s:submit type="button" cssClass="add_searchButton form-control contact_search" tabindex="12"
                                                                                  value="Search" onclick="return getSearchRequirementsList()" cssStyle="margin:5px"><i class="fa fa-search"></i>&nbsp;Search</s:submit>

                                                                        </div>
                                                                        <div class="pull-right btn_alignment">

                                                                        <s:if test="#session.primaryrole==3">
                                                                            <s:url id="myUrl" action="/../../../Requirements/addRequirements.action">
                                                                                <s:param name="accountSearchID"><s:property value="orgid"/></s:param>
                                                                                <s:param name="customerFlag" value="%{customerFlag}" ></s:param> 
                                                                            </s:url>
                                                                            <label class="labelStylereq" style="color:#56a5ec;"></label>
                                                                            <s:a  href='%{#myUrl}' cssClass="add_searchButton form-control contact_search" tabindex="13" style="margin:5px"><i class="fa fa-plus-square"></i>&nbsp;Add</s:a> 
                                                                        </s:if>



                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </s:form>
                                                </div>
                                            </div> 
                                        </div>

                                        <div id="loadingRequirements" class="loadingImg"  >
                                            <span id ="LoadingContent" > <img src="<s:url value="/includes/images/Loader1.gif"/>"   ></span>
                                        </div>
                                        <%--<s:submit cssClass="css_button" value="show"/><br>--%>
                                        <div class="col-sm-12  share">
                                            <span><releaseMessage></releaseMessage></span>
                                            <s:form>

                                                <div class="task_content form-control col-sm-12" id="task_div" align="center" style="display: none" >



                                                    <table id="reqTableInRecruiter" class="responsive CSSTable_task sortable" border="5"cell-spacing="2">
                                                        <tbody>
                                                            <tr>
                                                                <th >Job Id</th>
                                                                <th style="color: red">Job Title</th>
                                                                <s:if test="#session.primaryrole == 13"> 
                                                                    <th >Created By</th>
                                                                </s:if>  
                                                                <s:if test="vendor=='yes'">
                                                                    <th >Customer</th>
                                                                </s:if>
                                                                <s:else>
                                                                    <th >Positions</th>
                                                                </s:else>
                                                                <th >Skill set</th>
                                                                <%--<th class="unsortable">Pre Skills</th>--%>
                                                                <%-- <s:if test="vendor!='yes'">
                                                                     <th class="unsortable">Approver</th>
                                                                     <th class="unsortable">Requisitioner</th>
                                                                 </s:if> --%>
                                                                <th >Posted Date</th>
                                                                <th >Status</th>
                                                                <s:if test="vendor=='yes'"></s:if>
                                                                <s:else>
                                                                    <th >No.Of&nbsp;Submissions</th>
                                                                    <th class="unsortable">Release</th>
                                                                </s:else>
                                                                <s:if test="vendor=='yes'">
                                                                    <th class="unsortable">Consultant</th>
                                                                </s:if>
                                                                <s:if test="#session.primaryrole==3"> 
                                                                    <th class="unsortable">Copy Req</th>
                                                                </s:if>

                                                            </tr>
                                                            <s:if test="requirementlistVTO ==null">
                                                                <s:hidden id="rec_exits" value="no"/>
                                                                <tr>
                                                                    <td colspan="11"><font style="color: red;font-size: 15px;">No Records to display</font></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:iterator  value="requirementlistVTO">

                                                                <!--build url TO goto Account Details-->
                                                                <s:if test="customerFlag=='customer'">
                                                                    <s:url id="myUrl" action="Requirements/requirementedit.action" namespace="/" encode="true">
                                                                        <s:param name="requirementId" value="%{id}" />
                                                                        <s:param name="accountSearchID"><s:property value="orgid"/></s:param>
                                                                        <s:param name="accountFlag" value="%{accountFlag}"></s:param>
                                                                        <s:param name="customerFlag" value="%{customerFlag}" ></s:param>
                                                                        <s:param name="jdId" value="%{jdId}"></s:param>
                                                                    </s:url> 
                                                                </s:if>
                                                                <s:else>
                                                                    <s:url id="myUrl" action="Requirements/requirementedit.action" namespace="/" encode="true">
                                                                        <s:param name="requirementId" value="%{id}" />
                                                                        <s:param name="accountSearchID"><s:property value="orgid"/></s:param>
                                                                        <s:param name="accountFlag" value="%{accountFlag}" />
                                                                        <s:param name="vendor" value="%{vendor}" />
                                                                        <s:param name="jdId" value="%{jdId}"></s:param>
                                                                    </s:url>
                                                                </s:else>
                                                                <tr>
                                                                    <s:hidden name="rec_exits" id="rec_exits" value="yes"/>
                                                                    <td><s:a href='%{#myUrl}'><s:property value="%{jdId}"></s:property></s:a></td>
                                                                    <td><s:property value="title"/></td>
                                                                    <s:if test="#session.primaryrole == 13">
                                                                        <td>  <s:property value="createdByName"/></td>

                                                                    </s:if>
                                                                    <s:if test="vendor=='yes'">
                                                                        <td><s:property value="customerName"/></td>    
                                                                    </s:if>

                                                                    <s:else>
                                                                        <td><s:property value="noOfPosition"></s:property></td>
                                                                    </s:else>
                                                                    <s:if test="reqSkillSet.length()>9">    
                                                                        <td><s:a href="#" cssClass="recSkillOverlay_popup_open" onclick="reqSkillOverlay('%{reqSkillSet}')"><s:property  value="%{reqSkillSet.substring(0,10)}"/></s:a></td>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:a href="#" cssClass="recSkillOverlay_popup_open" onclick="reqSkillOverlay('%{reqSkillSet}')"><s:property  value="%{reqSkillSet}"/></s:a></td>
                                                                    </s:else>
                                                                    <%--s:if test="preSkillSet.length()>9">   
                                                                        <td><s:a href="#" cssClass="preSkillOverlay_popup_open" onclick="preSkillOverlay('%{preSkillSet}')"><s:property  value="%{preSkillSet.substring(0,10)}"/></s:a></td>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:a href="#" cssClass="preSkillOverlay_popup_open" onclick="preSkillOverlay('%{preSkillSet}')"><s:property  value="%{preSkillSet}"/></s:a></td>
                                                                    </s:else--%>
                                                                    <%-- <s:if test="vendor!='yes'">
                                                                         <td><s:a href="#" cssClass="recruiterOverlay_popup_open" onclick="showOverlayRecruiter(%{req_contact1})"><s:property value="reqContactName1"></s:property></s:a></td>
                                                                         <td><s:a href="#" cssClass="recruiterOverlay_popup_open" onclick="showOverlayRecruiter(%{req_contact2})"><s:property value="reqContactName2"></s:property></s:a></td>
                                                                     </s:if> --%>
                                                                    <td><s:property value="postedDate"/></td> 
                                                                    <td><s:property value="status"></s:property></td>
                                                                    <s:if test="vendor=='yes'">

                                                                    </s:if>
                                                                    <s:else>
                                                                        <td><s:property value="noOfSubmissions"/></td>
                                                                        <%--    <s:if test="status=='Closed'">
                                                                                <td><center><img src="<s:url value="/includes/images/release.png"/>" height="20" width="20" style="opacity: 0.3"></center></td>                                                                                
                                                                            </s:if>
                                                                            <s:elseif test="status=='Released'">
                                                                        <td><center><img src="<s:url value="/includes/images/release.png"/>" height="20" width="20" style="opacity: 0.3"></center></td>                                                                                
                                                                        </s:elseif> --%>
                                                                        <s:if test="status=='Opened'">
                                                                            <s:if test="#session.primaryrole == 3">
                                                                                <td><center><s:a href="#" onclick="doReleaseRequirement(%{id},%{orgid},'%{taxTerm}')"><i class="fa fa-arrow-circle-o-right fa-size"></i></s:a></center></td>
                                                                            </s:if>
                                                                            <s:elseif test="#session.primaryrole == 13">
                                                                        <td><center><s:a href="#" onclick="doReleaseRequirement(%{id},%{orgid},'%{taxTerm}')"><i class="fa fa-arrow-circle-o-right fa-size"></i></s:a></center></td>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <td><center><i class="fa fa-arrow-circle-o-right fa-size" style="opacity: 0.3" ></i></center></td>                                                                                
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <td><center><i class="fa fa-arrow-circle-o-right fa-size" style="opacity: 0.3" ></i></center></td>                                                                                
                                                                </s:else>
                                                            </s:else>
                                                            <s:if test="vendor=='yes'">
                                                                <s:if test="status=='Closed'||status=='Released'">
                                                                    <td><center><i class="fa fa-user-plus fa-size" style="opacity: 0.3"></i></center></td>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:url id="addConsult" action="/recruitment/consultant/doAddConsultantForReq.action" namespace="/" encode="true">
                                                                        <s:param name="requirementId" value="%{id}" />
                                                                        <s:param name="jdId" value="%{jdId}"></s:param>
                                                                        <s:param name="jobTitle" value="%{title}"></s:param>
                                                                        <s:param name="orgid"><s:property value="orgid"/></s:param>
                                                                        <s:param name="targetRate"><s:property value="%{targetRate}"/></s:param>
                                                                        <s:param name="maxRate"><s:property value="%{requirementMaxRate}"/></s:param>
                                                                    </s:url>
                                                                    <td><center><s:a href="%{#addConsult}"><i class="fa fa-user-plus fa-size"></i></s:a></center></td>
                                                                </s:else>
                                                            </s:if>
                                                            <s:if test="#session.primaryrole == 3">
                                                                <s:url id="copyReq" action="/../../../Requirements/doCopyRequirement.action">
                                                                    <s:param name="accountSearchID"><s:property value="orgid"/></s:param>
                                                                    <s:param name="customerFlag" value="%{customerFlag}" ></s:param> 
                                                                    <s:param name="requirementId" value="%{id}" />
                                                                </s:url>

                                                                <td><center><s:a href='%{copyReq}' ><i class="fa fa-files-o fa-size" ></i></s:a></center></td>
                                                            </s:if>



                                                            </tr>
                                                        </s:iterator>

                                                        </tbody>
                                                    </table>
                                                    <br/>

                                                    <label class="page_option"> Display <select id="paginationOption" class="disPlayRecordsCss" onchange="pagerOption()" style="width: auto">
                                                            <option>10</option>
                                                            <option>15</option>
                                                            <option>25</option>
                                                            <option>50</option>
                                                        </select>
                                                        Requirements per page
                                                    </label>
                                                    <div align="right" id="pageNavPosition" style="margin-right: 0vw;display: none"></div>

                                                    <script type="text/javascript">
                                                        var pag = new Pager('reqTableInRecruiter', 10); 
                                                        pag.init(); 
                                                        pag.showPageNav('pag', 'pageNavPosition'); 
                                                        pag.showPage(1);
                                                    </script>
                                                    <s:hidden name="gridDownload" id="gridDownload" value="%{gridPDFDownload}"/>


                                                </div>
                                                <div class="col-sm-12">
                                                    <div id ="downloading_grid" class="col-sm-6 pull-right">
                                                        <div class="pull-right  req_btn col-sm-0" style="margin: 0px 0px 0px 10px">
                                                            <div onclick="downloadPDFRequirementList()" tabindex="14" class="fa fa-download cssbutton form-control">&nbsp;DownloadPDF</div>
                                                        </div>
                                                        <div class="pull-right  req_btn col-sm-0">
                                                            <div onclick="downloadXLSRequirementList()" tabindex="15" class=" fa fa-download cssbutton form-control">&nbsp;DownloadXLS</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </s:form>
                                        </div>

                                    </div>
                                    <div id="recruiterOverlay_popup">
                                        <div id="recruiterBox" class="marginTasks">
                                            <div class="backgroundcolor">
                                                <table>
                                                    <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Contact Information&nbsp;&nbsp; </font></h4></td>
                                                    <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="recruiterOverlay_popup_close" onclick="closeRecruiterOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                </table>
                                            </div>
                                            <div><center>
                                                    <table >
                                                        <s:textfield label="Name" cssClass="form-control " id="recruiterNameOverlay" />
                                                        <s:textfield label="Email Id" cssClass="form-control margin" id="recruiterEmailIdOverlay" />
                                                        <s:textfield label="Phone" cssClass="form-control margin" id="recruiterPhoneOverlay" />
                                                    </table>
                                                </center>
                                            </div>
                                            <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                        </div>
                                        <%--req skills and pre skills details add by jitendra--%>
                                        <div id="recSkillOverlay_popup">
                                            <div id="reqskillBox" class="marginTasks">
                                                <div class="backgroundcolor">
                                                    <table>
                                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill Details&nbsp;&nbsp; </font></h4></td>
                                                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="recSkillOverlay_popup_close" onclick="reqSkillOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                    </table>
                                                </div>
                                                <div>

                                                    <s:textarea name="skillDetails" id="reqSkillDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                                                </div>
                                                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                            </div>

                                            <%--close of future_items--%>
                                        </div>

                                        <div id="preSkillOverlay_popup">
                                            <div id="preskillBox" class="marginTasks">
                                                <div class="backgroundcolor">
                                                    <table>
                                                        <tr><td><h4 style="font-family:cursive"><font class="titleColor">&nbsp;&nbsp;Skill Details&nbsp;&nbsp; </font></h4></td>
                                                        <span class="pull-right"> <h5 >&nbsp;&nbsp;&nbsp;&nbsp;<a href="" class="preSkillOverlay_popup_close" onclick="preSkillOverlay()" ><i class="fa fa-times-circle-o fa-size"></i></a></h5></span>
                                                    </table>
                                                </div>
                                                <div>

                                                    <s:textarea name="skillDetails"   id="preSkillDetails"   disabled="true" cssClass="form-control textareaSkillOverlay"/> 


                                                </div>
                                                <font style="color: #ffffff">..................... ..............................  ..........................................</font>
                                            </div>
                                        </div>
                                        <%--close of future_items--%>
                                    </div>
                                </div>
                            </div> <s:if test="#session.primaryrole==3">         
                                <div class="side_popup">

                                    <div class="popup_block"> 

                                        <div class="addlink" style="height: 30px;" >
                                            <s:url id="myUrl" action="/../../../Requirements/addRequirements.action">
                                                <s:param name="accountSearchID"><s:property value="orgid"/></s:param>
                                                <s:param name="customerFlag" value="%{customerFlag}" ></s:param> 
                                            </s:url>



                                            <div class="alignField">



                                                <s:a href='%{#myUrl}'>   Add Requirement  </s:a>
                                                </div>

                                            </div>
                                        </div>

                                        <div class="slide_popup requirementPopup"><img src="../../includes/images/requirement_1.png" style="width:24px;height:26px;"  class="img-swap"></div>
                                    </div>


                            </s:if>
                        </div>
                    </div>        <!-- content end -->
                </section><!--/form-->
            </div>
        </div>
        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>
        </footer><!--/Footer-->

        <script language="JavaScript" src='<s:url value="/includes/js/general/popupoverlay.js"/>'></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/selectivity-full.min.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.scrollUp.min.js"/>"></script>
        <script>
            
            $('#skillCategoryValue').selectivity({
                    
                multiple: true,
                placeholder: 'Type to search skills'
            });
            
            
        </script>
        <script>
            ;   sortables_init();
        </script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/pagination.js"/>"></script> 

        <script type="text/javascript">
            var recordPage=10;
            function pagerOption(){

                var paginationSize = document.getElementById("paginationOption").value;
                if(isNaN(paginationSize))
                {
                       
                }
                recordPage=paginationSize;
                // alert(recordPage)
                $('#reqTableInRecruiter').tablePaginate({navigateType:'navigator'},recordPage);

            };
            $('#reqTableInRecruiter').tablePaginate({navigateType:'navigator'},recordPage);
        </script>

        <script>
            $(document).ready(function(){
                $(".img-swap").click(function(){
                    $(".popup_block").animate({
                        width: 'toggle'
                    });
                });
            });
            
           
            
            
           
        </script>
        <script type="text/JavaScript">
            display_downloadButtons();
        </script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>
    </body>
</html>
