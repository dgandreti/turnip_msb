<%@ taglib prefix="s" uri="/struts-tags" %>

<div>

    <div class="row">
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-6">
                    <div id="message" style="color:green"></div>
                </div>
                <div id="checkboxListForm"  style="color:#00CC00" class="col-lg-6">
                </div>
            </div>
            <div class="row">
                <s:form action="checkBoxResultAction" namespace="/acc" id="resultActionID">

                    <div class="col-lg-3">
                        <s:checkboxlist name="yourSoftware2" list="software2"
                                        value="DefaultSofwares" theme="simple"
                                        id="yourSoftware2"/>
                    </div>

                    <div class="col-lg-3">
                        <s:checkboxlist name="yourSoftware0" list="software0"
                                        value="DefaultSofwares" theme="simple"
                                        id="yourSoftware0"/>
                    </div>

                    <div class="col-lg-4">
                        <s:checkboxlist name="yourSoftware1" list="software1"
                                        value="DefaultSofwares" theme="simple"
                                        id="yourSoftware1"/>
                    </div>
                </s:form>
            </div>
            <div style="float: right;">
                <a href="#save" id="checkListButton" onclick="javascript: saveSoftwaresAjax();">
                    <div class="details_button">UPDATE</div></a>
            </div>
        </div>
        <script type="text/javascript">
            function saveSoftwaresAjax() {
                $.ajax({
                    url: "saveToDB",
                    type: "post",
                    
                    data:$("#resultActionID").serialize()+'&&accountId='+<s:property value="accountId" />,
                    success: function(data) {
                        $('#message').html("Software updated!")
                        .append("<p></p>").fadeOut(5000);

                    }
                });
            }

        </script>
    </div>

