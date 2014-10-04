<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-lg-3 control-label">serviceId:</label>

        <div class="col-lg-9">
            <input name="serviceId" style="display:inline; width:94%;" class="form-control" type="text"
                   id="serviceIdID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">scenario:</label>

        <div class="col-lg-9">
            <input name="scenario" style="display:inline; width:94%;" class="form-control" type="text" id="scenarioID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">businessParam:</label>

        <div class="col-lg-9">
            <input name="businessParam" style="display:inline; width:94%;" class="form-control" type="text"
                   id="businessParamID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">reponse:</label>

        <div class="col-lg-9">
            <input name="reponse" style="display:inline; width:94%;" class="form-control" type="text" id="reponseID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">errorCode:</label>

        <div class="col-lg-9">
            <input name="errorCode" style="display:inline; width:94%;" class="form-control" type="text"
                   id="errorCodeID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">responseSample:</label>

        <div class="col-lg-9">
            <input name="responseSample" style="display:inline; width:94%;" class="form-control" type="text"
                   id="responseSampleID"/>
        </div>
    </div>
</form>
<script type="text/javascript">
    var selectItems = {};
</script>
</body>
</html>