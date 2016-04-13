<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>

<%--<div id="footer_seperator"></div>--%>
<div class="row">
    
    <div class="col-sm-6 pull-left">Copyright <i class="fa fa-copyright"></i> <%GregorianCalendar cal = new GregorianCalendar();out.print(cal.get(Calendar.YEAR));%> ServicesBay. All rights reserved.</div>
    <div class="col-sm-6">
        <ul class="pull-right socialHover">
            <a id="footerFacebookLink" class="btn btn-social btn-facebook " href="https://www.facebook.com/miracle45625" target="_blank" itemprop="sameAs"><i class="fa fa-facebook" style=""></i></a>
            <a id="footerGooglePlusLink" class="btn btn-social btn-google-plus" href="https://plus.google.com/+Team_MSS/" target="_blank" itemprop="sameAs"><i class="fa fa-google-plus"></i></a>
            <a id="footerTwitterLink" class="btn btn-social btn-twitter" href="https://twitter.com/Team_MSS" target="_blank" itemprop="sameAs"><i class="fa fa-twitter"></i></a>
            <a id="footerLinkedInLink" class="btn btn-social btn-linkedin" href="https://www.linkedin.com/company/miracle-software-systems-inc" target="_blank" itemprop="sameAs"><i class="fa fa-linkedin"></i></a>
        </ul>
    </div>
</div>

