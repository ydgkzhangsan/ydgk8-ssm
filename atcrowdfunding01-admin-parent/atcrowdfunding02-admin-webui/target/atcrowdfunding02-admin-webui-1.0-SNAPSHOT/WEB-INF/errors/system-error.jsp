<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统错误页面</title>
</head>
<body>
    <h4>哦噢！不小心出错了~</h4>
    ${requestScope.exception.message}
</body>
</html>
