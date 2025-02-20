<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<form>
    <div class="card-body">
        <div class="input-group mb-3">
            <span class="input-group-text">번호</span>
            <input class="form-control" type="text" name="tno"
                   value="<c:out value="${dto.tno}"/>" readonly>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">제목</span>
            <input class="form-control" type="text" name="title"
                   value="<c:out value="${dto.title}"/>">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">작성자</span>
            <input class="form-control" type="text" name="writer"
                   value="<c:out value="${dto.writer}"/>">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">마감날짜</span>
            <input class="form-control" type="date" name="dueDate"
                   value="<c:out value="${dto.dueDate}"/>">
        </div>
        <div class="form-check">
            <label class="form-check-label">마감(여/부)</label>
            <input class="form-check-input" type="checkbox" name="finished"
            ${dto.finished ? "checked" : ""}>
        </div>
        <div class="my-4">
            <div class="float-end">
                <button type="button" class="btn btn-danger">삭제하기</button>
                <button type="button" class="btn btn-primary">수정하기</button>
                <button type="button" class="btn btn-secondary">목록보기</button>
            </div>
        </div>
    </div>
</form>
</body>
<script>
    const formObj = document.querySelector("form");

    document.querySelector(".btn-danger").addEventListener("click", (e)=>{
        e.preventDefault();
        e.stopPropagation();
        formObj.action = "/todo/remove/${dto.tno}";
        formObj.method ="post";
        formObj.submit();
    })

    document.querySelector(".btn-primary").addEventListener("click", (e)=>{
        // e.preventDefault();
        // e.stopPropagation();
        // submit 일때는 데이터 전송 문제 발생 기본 submit로 전송필요.
        formObj.action = "/todo/modify/${dto.tno}";
        formObj.method ="post";
        formObj.submit();
    })

    document.querySelector(".btn-secondary").addEventListener("click", (e)=>{
        e.preventDefault();
        e.stopPropagation();
        formObj.action = "/todo/list";
        formObj.method ="get";
        formObj.submit();
    })



    <%--document.querySelector(".btn-primary").addEventListener("click", (e) => {--%>
    <%--    self.location = "/todo/modify/${dto.tno}"--%>
    <%--}, false)--%>

    <%--document.querySelector(".btn-secondary").addEventListener("click", (e) => {--%>
    <%--    self.location = "/todo/list"--%>
    <%--}, false)--%>
    const serverValidResult = {}
    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</html>
