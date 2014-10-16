<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-lg-3 control-label">name:</label>

        <div class="col-lg-9">
            <input name="name" style="display: inline; width: 94%;"
                   class="form-control" type="text" id="nameID"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">level:</label>

        <div class="col-lg-9">
            <div class="btn-group select" id="levelID"></div>
            <input type="hidden" id="levelID_" name="level"/>
        </div>
    </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">upid:</label>

        <div class="col-lg-9">
            <input name="upid" style="display: inline; width: 94%;"
                   class="form-control" type="text" id="upidID"/>
        </div>
    </div>
</form>
<script type="text/javascript">
    var selectItems = {};
    selectItems['levelID'] = [
        {
            title: '请选择',
            value: ''
        },
        {
            title: '国家',
            value: '1'
        },
        {
            title: '省(州)',
            value: '2'
        },
        {
            title: '市(郡)',
            value: '3'
        }
    ];
</script>
</body>
</html>