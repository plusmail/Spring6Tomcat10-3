<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo 목록보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>글 목록보기</h1>
<div>
    <div>
        <div>
            <div>
                <h5>검색어 :</h5>
                <form action="/todo/list" method="get">
                    <input type="hidden" name="size" value="${reqDTO.size}">
                    <div>
                        <input type="checkbox" name="finished"> 완료여부
                    </div>
                    <div>
                        <input type="checkbox" name="types" value="t">제목
                        <input type="checkbox" name="types" value="w">작성자
                        <input type="text" name="keyword">
                    </div>
                    <div>
                        <input type="date" name="from">
                        <input type="date" name="to">
                    </div>
                    <div>
                        <div>
                            <button type="submit">검색</button>
                            <button type="reset">취소</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



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
            <c:forEach items="${resDTO.dtoList}" var="dto">
                <tr>
                    <th scope="row"><c:out value="${dto.tno}"/></th>
                    <td>
                        <a href="/todo/read/${dto.tno}?${reqDTO.getLink()}"
                           data-tno="${dto.tno}"
                           class="text-decoration-none">
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


    <div>
        <ul class="pagination d-flex justify-content-center">

            <c:if test="${resDTO.prev}">
                <li class="page-item">
                    <a class="page-link"  data-num="${resDTO.start -1 }">이전</a>
                </li>
            </c:if>

            <c:forEach begin="${resDTO.start}" end="${resDTO.end}" var="num">
                <li class="page-item ${resDTO.page == num ? "active" : ""}"   >
                    <a href="#" class="page-link" data-num="${num}">${num}</a>
                </li>
            </c:forEach>

            <c:if test="${resDTO.next}">
                <li class="page-item">
                    <a class="page-link" data-num="${resDTO.end + 1}">다음</a>
                </li>
            </c:if>


        </ul>
    </div>
</div>
<script>
    document.querySelector(".pagination").addEventListener("click", (e)=>{
        e.preventDefault()
        e.stopPropagation()
        const target = e.target
        if(target.tagName !== 'A'){
            return
        }
        const num = target.getAttribute("data-num")
        self.location = `/todo/list?page=\${num}`

    },false)


</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
