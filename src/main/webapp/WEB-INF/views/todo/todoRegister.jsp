<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Todo 등록</title>
</head>
<body>
<h1>Todo 등록화면</h1>
<h2>${errors}</h2>
<form action="/todo/register" method="post">
    <div>
        제목: <input type="text" name="title">
    </div>
    <div>
        등록일자: <input type="date" name="dueDate" value="2025-02-18">
    </div>
    <div>
        작성자 : <input type="text" name="writer">
    </div>
    <div>
        결과 : <input type="checkbox" name="finished" value="false">
    </div>
    <div>
        <button type="submit">할일 등록</button>
    </div>
</form>
</body>
</html>
