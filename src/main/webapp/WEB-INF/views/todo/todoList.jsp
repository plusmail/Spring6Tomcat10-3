<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo 목록보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>글 목록보기</h1>

<div class="card-body">
    <h5 class="card-title">할일 목록 .....</h5>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">tno</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">처리 마감일</th>
            <th scope="col">종료(여/부)</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${dtoList}" var="dto">
                <tr>
                    <th scope="row"><c:out value="${dto.tno}"/></th>
                    <td>
                        <a href="/todo/read/${dto.tno}" class="text-decoration-none">
                            <c:out value="${dto.title}"/>
                        </a>
                    </td>
                    <td><c:out value="${dto.writer}"/></td>
                    <td><c:out value="${dto.dueDate}"/> </td>
                    <td><c:out value="${dto.finished}"/></td>
                    <td></td>
                </tr>

            </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
