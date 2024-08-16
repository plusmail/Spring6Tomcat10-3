
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>todo 등록페이지</h1>
<form action="/todo/register-view" method="post">
    <div>
        Title : <input type="text" name="title">
    </div>
    <div>
        DueDate : <input type="date" name="dueDate">
    </div>
    <div>
        Writer : <input type="text" name="writer">
    </div>
    <div>
<%--         <input type="submit" value="등록">--%>
        <button type="submit">등록</button>
    </div>

</form>


</body>
</html>
