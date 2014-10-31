<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
    String id=request.getParameter("id");
%>
<body>
<form class="form-horizontal">
    <%--<div class="form-group">--%>
        <%--<label class="col-lg-3 control-label">id:</label>--%>

        <%--<div class="col-lg-9">--%>
            <%--<input name="id" style="display:inline; width:94%;" class="form-control" type="text" id="idID"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="form-group">
        <label class="col-lg-3 control-label">name:</label>

        <div class="col-lg-9">
            <input name="name" style="display:inline; width:94%;" class="form-control" type="text" id="nameID"/>
        </div>
    </div>
</form>
<form action="PhotoCollection/uploadPhoto.koala?id=<%=id%>" method="POST" enctype="multipart/form-data" name="form1">
    <input type="file" name="file1">
    <br>
    <input type="file" name="file2">
    <br>
    <input type="file" name="file3">
    <br>
    <input type="file" name="file4">
    <br>
    <input type="file" name="file5">
    <br>
    <input type="submit" name="Submit" value="upload">
</form>
<script type="text/javascript">
    var selectItems = {};
</script>
</body>
</html>