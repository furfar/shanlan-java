<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-lg-3 control-label">name:</label>

        <div class="col-lg-9">
            <input name="name" style="display:inline; width:94%;" class="form-control" type="text" id="nameID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">description:</label>

        <div class="col-lg-9">
            <input name="description" style="display:inline; width:94%;" class="form-control" type="text"
                   id="descriptionID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">price:</label>

        <div class="col-lg-9">
            <input name="price" style="display:inline; width:94%;" class="form-control" type="text" id="priceID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">status:</label>

        <div class="col-lg-9">
            <input name="status" style="display:inline; width:94%;" class="form-control" type="text" id="statusID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">validDate:</label>

        <div class="col-lg-9">
            <div class="input-group date form_datetime" style="width:160px;float:left;">
                <input type="text" class="form-control" style="width:160px;" name="validDate" id="validDateID">
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">invalidDate:</label>

        <div class="col-lg-9">
            <div class="input-group date form_datetime" style="width:160px;float:left;">
                <input type="text" class="form-control" style="width:160px;" name="invalidDate" id="invalidDateID">
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">address:</label>

        <div class="col-lg-9">
            <input name="address" style="display:inline; width:94%;" class="form-control" type="text" id="addressID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">photographer:</label>

        <div class="col-lg-9">
            <input name="photographer" style="display:inline; width:94%;" class="form-control" type="text"
                   id="photographerID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">days:</label>

        <div class="col-lg-9">
            <input name="days" style="display:inline; width:94%;" class="form-control" type="text" id="daysID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">photoCount:</label>

        <div class="col-lg-9">
            <input name="photoCount" style="display:inline; width:94%;" class="form-control" type="text"
                   id="photoCountID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">alterCount:</label>

        <div class="col-lg-9">
            <input name="alterCount" style="display:inline; width:94%;" class="form-control" type="text"
                   id="alterCountID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">placeId:</label>

        <div class="col-lg-9">
            <input name="placeId" style="display:inline; width:94%;" class="form-control" type="text" id="placeIdID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">other:</label>

        <div class="col-lg-9">
            <input name="other" style="display:inline; width:94%;" class="form-control" type="text" id="otherID"/>
        </div>
    </div>
</form>
<script type="text/javascript">
    var selectItems = {};
</script>
</body>
</html>