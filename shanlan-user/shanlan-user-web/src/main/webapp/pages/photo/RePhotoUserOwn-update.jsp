<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <%--<label class="col-lg-3 control-label">userName:</label>--%>

        <div class="col-lg-9">
            <input name="userName" style="display:none; width:94%;" class="form-control" type="text" id="userNameID"/>
        </div>
    </div>
    <div class="form-group">
        <%--<label class="col-lg-3 control-label">photoId:</label>--%>

        <div class="col-lg-9">
            <input name="photoId" style="display:none;  width:94%;" class="form-control" type="text" id="photoIdID" />
        </div>
    </div>
    <div class="form-group">
        <%--<label class="col-lg-3 control-label">photoPath:</label>--%>

        <div class="col-lg-9">
            <input name="photoPath" style="display:none; width:94%;" class="form-control" type="text"
                   id="photoPathID" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">title:</label>

        <div class="col-lg-9">
            <input name="title" style="display:inline; width:94%;" class="form-control" type="text" id="titleID"/>
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
        <label class="col-lg-3 control-label">visibility:</label>

        <div class="col-lg-9">
            <%--<input name="visibility" style="display:inline; width:94%;" class="form-control" type="text"--%>
                   <%--id="visibilityID"/>--%>
            <div class="btn-group select" id="visibilityID"></div>
            <input type="hidden" id="visibilityID_" name="visibility" />
        </div>
    </div>
    <div class="form-group">
        <%--<label class="col-lg-3 control-label">createdAt:</label>--%>

        <div class="col-lg-9">
            <input name="createdAt" style="display:none; width:94%;" class="form-control" type="text"
                   id="createdAtID" />
        </div>
    </div>
    <div class="form-group">
        <%--<label class="col-lg-3 control-label">updatedAt:</label>--%>

        <div class="col-lg-9">
            <input name="updatedAt" style="display:none; width:94%;" class="form-control" type="text"
                   id="updatedAtID" />
        </div>
    </div>
</form>
<script type="text/javascript">
    var selectItems = {};
    var contents = [ {
        title : '请选择',
        value : ''
    } ];
    contents.push({title: '大众可见', value: '4'});
    contents.push({title: '大众可见但不显示在摄影师交易页面', value: '3'});
    contents.push({title: '参与者可见', value: '2'});
    contents.push({title: '好友可见', value: '1'});
    contents.push({title: '仅自己可见', value: '0'});
    selectItems['visibilityID'] = contents;
</script>
</body>
</html>