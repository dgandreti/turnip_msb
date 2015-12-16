<%-- 
    Document   : mainPageWScript
    Created on : May 29, 2015, 11:36:19 AM
    Author     : Riza Erbas
--%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>

<jsp:include page="projects.jsp"/>
<script>
    ;     
    function searchProjects(){
        $.ajax({url:"<%=request.getContextPath()%>/mainProjectsSearch.action"
                +"?projectReqSkillSet=" + document.getElementById("projectReqSkillSet").value
                +"&projectName=" + document.getElementById("projectName").value
                +"&projectStartDate=" + document.getElementById("projectStartDate").value
                +"&projectTargetDate=" + document.getElementById("projectTargetDate").value
                +"&accountID=<s:property value='accountID'/>"
                +"&projectType="+"MP"
            ,
            success: function(data){
                console.log('testing');
                window.setTimeout("pagerOption();", 1000);
                window.setTimeout("doOnLoad();", 1000);
                $(projectsPage).children().remove();
                $("#projectsPage").html(data);
            },
            type: 'GET'
        });
    };
    function addProject(){
        projName = document.getElementById("projectNamePopup").value;
        $.ajax({url:"<%=request.getContextPath()%>/addMainProject.action"
                +"?projectName=" + document.getElementById("projectNamePopup").value
                +"&project_status=" + document.getElementById("project_statusPopup").value
                +"&projectStartDate=" + document.getElementById("projectStartDateOverlay").value
                +"&projectTargetDate=" + document.getElementById("projectTargetDateOverlay").value
                +"&projectReqSkillSet=" + document.getElementById("projectReqSkillSetPopup").value
                +"&project_description=" + document.getElementById("project_descriptionPopup").value
                +"&accountID=<s:property value="accountID"/>"
            ,
            type: 'GET'
        });
    }
    function resetOverlayForm(){
        document.getElementById("overlayForm").reset();
        $("#projectNameError").html("Project name is valid.");
        document.getElementById("projectNameError").style.display = "none";
    };
    window.setTimeout("pagerOption();", 1000);
    window.setTimeout("doOnLoad();", 1000);
   
</script>
<script type="text/JavaScript" src="<s:url value="/includes/js/general/placeholders.min.js"/>"></script>