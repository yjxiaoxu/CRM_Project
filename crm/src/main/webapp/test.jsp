<%--
  Created by IntelliJ IDEA.
  User: 小许智能
  Date: 2020/10/30
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>

<html>
<head>
    <base href="${pageContext.request.contextPath}"/>
    <title>Title</title>
    <script type="text/javascript">
        alert("${pageContext.request.contextPath}/");
        alert("<%=basePath%>");
    </script>
</head>
<body>

</body>
</html>
