<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>文件上传</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form action=<%=basePath%>user/uploadAvatar.koala method="POST" enctype="multipart/form-data" name="form1">
    <input type="file" name="file1">
    <%--<input type="file" name="file2">--%>
    <%--<input type="file" name="file3">--%>
    <%--<input type="file" name="file4">--%>
    <input type="submit" name="Submit" value="upload">
</form>

</body>
</html>
