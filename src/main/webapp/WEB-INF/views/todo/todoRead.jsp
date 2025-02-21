<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<div class="card-body">
    <div class="input-group mb-3">
        <span class="input-group-text">번호</span>
        <input class="form-control" type="text" name="tno"
               value="<c:out value="${dto.tno}"/>" readonly>
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text">제목</span>
        <input  class="form-control" type="text" name="title"
                value="<c:out value="${dto.title}"/>" readonly>
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text">작성자</span>
        <input  class="form-control" type="text" name="writer"
                value="<c:out value="${dto.writer}"/>" readonly>
    </div>
    <div class="input-group mb-3">
        <span class="input-group-text">마감날짜</span>
        <input  class="form-control" type="text" name="dueDate"
                value="<c:out value="${dto.dueDate}"/>" readonly>
    </div>
    <div class="form-check">
        <label class="form-check-label">마감(여/부)</label>
        <input  class="form-check-input" type="checkbox" name="finished"
        ${dto.finished ? "checked" : ""} disabled>
    </div>
    <div class="my-4">
        <div class="float-end">
            <button type="button" class="btn btn-primary">수정</button>
            <button type="button" class="btn btn-secondary">목록보기</button>
        </div>
    </div>


</div>
</body>
<script>
    document.querySelector(".btn-primary").addEventListener("click", (e)=>{
        self.location = "/todo/modify/${dto.tno}?${reqDTO.link}"
    }, false)

    document.querySelector(".btn-secondary").addEventListener("click", (e)=>{
        self.location = "/todo/list?${reqDTO.link}"
    }, false)

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>
