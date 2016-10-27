<%-- 
    Document   : login
    Created on : Feb 3, 2015, 4:04:37 PM
    Author     : Nagireddy
--%>

<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/403.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.util.List" isErrorPage="true"%>
<%@ page import="com.mss.msp.util.ApplicationConstants"%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>ServicesBay :: Reviews & Ratings</title>
        <!--[if IE]>
                <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/ie_support.css"/>">
               <![endif]-->
        <link rel="stylesheet" type="text/css" href="<s:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/main.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/reviewsRatings.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/responsive.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/flexslider.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/animate.css"/>">

        <!-- end of new styles -->
        <!-- start of js -->
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/home/home.css"/>">
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/general/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/popupoverlay.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/general/jquery.toggle.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/GeneralAjax.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/AppConstants.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/includes/js/homeslider/home/jquery.cycle.all.js"/>"></script> 
        <script type="text/JavaScript" src="<s:url value="/includes/js/account/formVerification.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/bootstrap.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/homeslider/js/jssor.slider.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/Ajax/vendorReviews.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="/includes/css/register/style2.css"/>">
        <script type="text/JavaScript" src="<s:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"/>"></script>
        <script type="text/JavaScript" src="<s:url value="/includes/js/jquery.combostars.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<s:url value="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="fontawesome-stars.css"/>">
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    </head>


    <body onload="showLogo();showPercentage();">
        <div id="wrap">
            <header id="header"><!--header-->
                <div class="header_top" id="header_top"><!--header_top-->
                    <div class="container">
                        <s:include value="/includes/template/header.jsp"/> 
                    </div>
                </div><!--/header_top-->

            </header><!--/header-->
            <div id="main">
                <section id="generalForm">
                    <div class="container">
                        <div class="row">
                            <s:include value="/includes/menu/LeftMenu.jsp"/>
                            <div class="col-sm-12 col-md-9 col-lg-9" style="background-color:#fff">
                                <div class="" id="" style="float: left; margin-top: 5px">
                                    <div class="backgroundcolor" >
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <font color="#ffffff">Reviews and Ratings</font>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="well well-sm">
                                        <div class="row">
                                            <div style="border-right:1px solid lightgrey" class="col-sm-4 pull-left">
                                                <img src="" style="height: 100px;width: 100px" id="imgLogo">
                                                <h4><s:property value="%{accountName}"/></h4>
                                                <s:hidden name="accName" id="accName" value="%{accountName}"/>
                                                <s:hidden name="vendorOrgId" id="vendorOrgId" value="%{vendorOrgId}"></s:hidden>
                                            </div>
                                            <div class="col-sm-3 pull-left">
                                                <h1 class="rating-num" id="avgValue">
                                                </h1>
                                                <div class="ratingS" id="ratingS">

                                                </div>
                                                <div>
                                                    <span class="glyphicon glyphicon-user" id="total"></span>
                                                </div>
                                            </div>
                                            <div class="col-sm-4 pull-left">
                                                <div class="row rating-desc">
                                                    <div class="col-xs-3 col-md-3 text-right">
                                                        <span class="glyphicon glyphicon-star"></span>5
                                                    </div>
                                                    <div class="col-xs-8 col-md-9">
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                                                 aria-valuemin="0" aria-valuemax="100"  id="bar5">
                                                                <span class="sr-only">80%</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- end 5 -->
                                                    <div class="col-xs-3 col-md-3 text-right">
                                                        <span class="glyphicon glyphicon-star"></span>4
                                                    </div>
                                                    <div class="col-xs-8 col-md-9">
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                                                                 aria-valuemin="0" aria-valuemax="100" id="bar4">
                                                                <span class="sr-only">60%</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- end 4 -->
                                                    <div class="col-xs-3 col-md-3 text-right">
                                                        <span class="glyphicon glyphicon-star"></span>3
                                                    </div>
                                                    <div class="col-xs-8 col-md-9">
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                                                                 aria-valuemin="0" aria-valuemax="100" id="bar3">
                                                                <span class="sr-only">40%</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- end 3 -->
                                                    <div class="col-xs-3 col-md-3 text-right">
                                                        <span class="glyphicon glyphicon-star"></span>2
                                                    </div>
                                                    <div class="col-xs-8 col-md-9">
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20"
                                                                 aria-valuemin="0" aria-valuemax="100" id="bar2">
                                                                <span class="sr-only">20%</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- end 2 -->
                                                    <div class="col-xs-3 col-md-3 text-right">
                                                        <span class="glyphicon glyphicon-star"></span>1
                                                    </div>
                                                    <div class="col-xs-8 col-md-9">
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                                                 aria-valuemin="0" aria-valuemax="100" id="bar1">
                                                                <span class="sr-only">15%</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- end 1 -->
                                                </div>
                                                <!-- end row -->
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <s:if test="#session.typeOfUsr=='AC'">
                                            <div class="panel panel-default left-menu" id="accordian_my">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a id="homeCustAdminLeftMenu" data-toggle="collapse" data-parent="#accordian" href="#homeMenuCustAdmin">
                                                            <i  class="fa fa-user left_Bullet"></i>
                                                            <span class="pull-right"><i class="fa-sort-asc fa" style="color: black;"></i></span>
                                                            Add&nbsp;Your&nbsp;Comment
                                                        </a>

                                                    </h4>
                                                </div>
                                                <div id="homeMenuCustAdmin" class="panel-collapse collapse">
                                                    <div class="panel-body" >

                                                        <label></label>
                                                        <div class="col-sm-12"></div>
                                                        <div class="col-sm-12">
                                                            <div class="col-sm-2">
                                                                <label class="labelStyle2">&nbsp;&nbsp;&nbsp;&nbsp;Title<font color="red">&nbsp;*</font></label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <s:textfield cssClass="form-control textbox" 
                                                                             type="text" id="reviewName" 
                                                                             maxLength="30" required="required" 
                                                                             placeholder="Enter the title"
                                                                             onblur="javascript: titleCheck('#reviewName','#reviewNameValidation')"/>
                                                                <span id="reviewNameValidation" class="accDetailsError"></span>
                                                            </div></div>


                                                        <div class="col-sm-12">
                                                            <label></label>
                                                        </div>

                                                        <div class="col-sm-12">
                                                            <div class="col-sm-2">
                                                                <label  class="labelStyle2">&nbsp;&nbsp;&nbsp;&nbsp;Comment<font color="red">&nbsp;*</font> </label>
                                                            </div>
                                                            <div class="col-sm-7">
                                                                <s:textarea cssClass="form-control textbox" 
                                                                            id="comment" type="text" 
                                                                            placeholder="Comment here" 
                                                                            onblur="javascript: commentCheck('#comment','#commentValidation')"
                                                                            onkeyup="ResponseCheckCharacters(this)"/>
                                                                <span id="commentValidation" class="accDetailsError"></span>
                                                                <span id="ResponsecharNum" class="charNum"></span>
                                                            </div></div>
                                                        <div class="col-sm-12">
                                                            <label></label>
                                                        </div>
                                                        <div class="col-sm-12">
                                                            <div class="col-sm-2">
                                                                <label  class="labelStyle2 stars_label">&nbsp;&nbsp;&nbsp;&nbsp;Rating </label></div>
                                                            <div class="ratingStar"></div>
                                                        </div>
                                                        <div>
                                                            <div class="col-sm-2 pull-right">
                                                                <input type="button" id="commentbutton"  style="margin:13px 0px;" class="add_searchButton form-control" onclick="return addVendorRatingAndReveiw()" value="Save&nbsp;Comment" />
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </s:if>
                                        <div class="">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <h3>Customer Comments</h3>
                                                </div><!-- /col-sm-12 -->
                                            </div><!-- /row -->
                                            <div class="row" >
                                                <%int k = 0;%>
                                                <s:iterator value="vendorReviewsList">
                                                    <div class="col-sm-12">
                                                        <div class="col-sm-1">
                                                            <div class="thumbnail">
                                                                <s:if test="logo!=null">
                                                                    <s:url id="image1" action="rImage" namespace="/renderImage">
                                                                        <s:param name="path" value="logo"></s:param>
                                                                    </s:url>
                                                                    <img src="<s:property value="image1"/>" > 
                                                                </s:if>
                                                                <s:else>
                                                                    <i class="fa-3x fa fa-user" aria-hidden="true"></i>
                                                                </s:else>
                                                            </div><!-- /thumbnail -->
                                                        </div><!-- /col-sm-1 -->
                                                        <div class="col-sm-10">
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <strong>
                                                                        <div class="" style="width: 45%;">  <s:property value="reviewName"/></div></strong> 
                                                                    <span class="text-muted"></span>
                                                                    <div class="rating star_ed" style="width:55%;margin-top: -19px;text-align:right;" id="reviewRating<%=k%>" ></div>
                                                                    <script>
                                                                        editStars('<s:property value="reviewRating"></s:property>',<%=k%>);
                                                                    </script>
                                                                    <s:hidden id="count5" value="%{count5}"/>
                                                                    <s:hidden id="count4" value="%{count4}"/>
                                                                    <s:hidden id="count3" value="%{count3}"/>
                                                                    <s:hidden id="count2" value="%{count2}"/>
                                                                    <s:hidden id="count1" value="%{count1}"/>
                                                                    <s:hidden id="totalCount" value="%{total}"/>
                                                                    <s:hidden id="avg" value="%{avg}"/>
                                                                </div>
                                                                <div class="panel-body">
                                                                    <s:property value="reviewComments"/>
                                                                </div><!-- /panel-body -->
                                                            </div><!-- /panel panel-default -->
                                                        </div><!-- /col-sm-5 -->
                                                    </div>
                                                    <%k++;%>
                                                </s:iterator>
                                            </div><!-- /row -->
                                        </div><!-- /container -->
                                    </div>
                                </div>
                            </div>         
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <footer id="footer"><!--Footer-->
            <div class="footer-bottom" id="footer_bottom">
                <div class="container">
                    <s:include value="/includes/template/footer.jsp"/>
                </div>
            </div>

        </footer><!--/Footer-->
        <script>
            $(document).ready(function(){
                $('.ratingStar').addRatingStar();
                
            })
        
            /**
             * jQuery Star Rating plugin
             * Joost van Velzen - http://joost.in
             *
             * v 1.0.3
             *
             * cc - attribution + share alike
             * http://creativecommons.org/licenses/by-sa/4.0/
             */

            (function( $ ) {
                $.fn.addRatingStar = function(options) {
                    
                    var obj = this;
                    var settings = $.extend({
                        max : 5,
                        half : true,
                        fieldName : 'ratingStar',
                        fieldId : 'ratingStar',
                        icon : 'star'

                    }, options );
                    this.settings = settings;

                    // create the stars
                    for(var i = 1 ; i <= settings.max ; i++)
                    {
                        var star = $('<i/>').addClass('material-icons').html(this.settings.icon+'_border').data('ratingStar', i).appendTo(this).click(
                        function(){
                            obj.setRatingStar($(this).data('ratingStar'));
                        }
                    )
                    }
                    $(this).append('<input type="hidden" name="'+settings.fieldName+'" id="'+settings.fieldId+'" />');
                };

                $.fn.setRatingStar = function(numRating) {
                    var obj = this;
                    $('#'+obj.settings.fieldId).val(numRating);
                    obj.showRatingStar(numRating, true);
                };

                $.fn.showRatingStar = function(numRating, force) {
                    var obj = this;
                    if($('#'+obj.settings.fieldId).val() == '' || force)
                    {
                        $(obj).find('i').each(function(){
                            var icon = obj.settings.icon+'_border';
                            if($(this).data('ratingStar') <= numRating)
                            {
                                icon = obj.settings.icon;
                            }
                            $(this).html(icon);
                        })
                    }
                }

            }( jQuery ));
        </script>    
    </body>
</html>