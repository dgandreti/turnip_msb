
<%@page import="com.mss.msp.util.ApplicationConstants"%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="../../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%--<s:if test="#session.typeOfUsr=='E' || #session.typeOfUsr=='SA'">   --%>
<s:if test="#session.primaryrole == 0">
    <!-- Menu Generation for Admin Role-->
    <s:include value="/includes/menu/Menu_sa.jsp"></s:include>
</s:if>
<s:if test="#session.primaryrole == 1">
    <!-- Menu Generation for Admin Role-->
    <s:include value="/includes/menu/Menu_csr.jsp"></s:include>
</s:if>


<s:if test="#session.primaryrole == 2">
    <!-- Menu Generation for Employee Role-->
    <s:include value="/includes/menu/Menu_cust_Admin.jsp"></s:include>
</s:if>
    
<s:if test="#session.primaryrole == 13">
    <!-- Menu Generation for Sales Role-->
    <s:include value="/includes/menu/Menu_cust_director.jsp"></s:include>
</s:if>
    
<s:if test="#session.primaryrole == 3">
    <!-- Menu Generation for Sales Role-->
    <s:include value="/includes/menu/Menu_cust_prj_mgr.jsp"></s:include>
</s:if>
    
<s:if test="#session.primaryrole == 7 || #session.primaryrole == 4 || #session.primaryrole == 6 || #session.primaryrole == 5">
    <!-- Menu Generation for Sales Role-->
    <s:include value="/includes/menu/Menu_cust_emp.jsp"></s:include>
</s:if>
    
<s:if test="#session.primaryrole == 8">
    <!-- Menu Generation for Sales Role-->
    <s:include value="/includes/menu/Menu_ven_Admin.jsp"></s:include>
</s:if>
    
 <s:if test="#session.primaryrole == 9 || #session.primaryrole == 10 || #session.primaryrole == 11">
    <!-- Menu Generation for Sales Role-->
    <s:include value="/includes/menu/Menu_ven_emp.jsp"></s:include>
</s:if>
   
<s:if test="#session.primaryrole == 12">
    <!-- Menu Generation for Sales Role-->
    <s:include value="/includes/menu/Menu_ven_consultant.jsp"></s:include>
</s:if>