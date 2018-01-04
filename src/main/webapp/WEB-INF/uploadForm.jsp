<%--
  Created by IntelliJ IDEA.
  User: chenhe
  Date: 2018/1/3
  Time: 17:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件上传下载</title>
    <c:set var="context" property="${pageContext.request.contextPath}" scope="session"></c:set>
</head>
<body>
<div>
    ${context}
        <br>
    ${message}
</div>
<div>
     <form method="post" enctype="multipart/form-data" action="fileupload" >
                 文件上传:
                 <input type="file" name="file">
         <input type="submit" value="上传">
     </form>
</div>
<div>
    <ul>
        <c:forEach var="item" items="${requestScope.file}">
            <li><a href="${item}">${item} </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
