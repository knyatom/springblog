<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="./layout/header.jsp" %>

<div class="container mt-3 pt-3">
 <h2> 전체게시글 목록</h2>

 <c:forEach var="board" items="${boards.content}">
      <div class="card m-1">
        <div class="card-body">
            <p class="card-text">
            글번호 : ${board.id}  | 작성자 : ${board.user.username}  |
            조회수 : <i>${board.count}</i>
            </p>
            <h4 class="card-title">${board.title}</h4>
         <!--   <p class="card-text">${board.content}</p>  -->
            <a href="/board/${board.id}" class="btn btn-primary" id="detail-btn" data-id="${board.id}">상세보기</a>
        </div>
      </div>
 </c:forEach>

  <nav aria-label="Page navigation example">
     <ul class="pagination justify-content-center">
     <c:choose>
      <c:when test="${boards.first}">
       <li class="page-item disabled">
       <a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
      </c:when>
     <c:otherwise>
      <li class="page-item">
      <a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
     </c:otherwise>
    </c:choose>

 <c:forEach begin="0" end="${boards.totalElements / 3}" step="1" varStatus="number">
    <li class="page-item">
     <a class="page-link"  href="?page=${number.index}">${number.index + 1}</a>
    </li>
 </c:forEach>

    <c:choose>
     <c:when test="${boards.last}">
      <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
     </c:when>
    <c:otherwise>
      <li class="page-item">
      <a class="page-link" href="?page=${boards.number+1}">Next</a></li>
    </c:otherwise>
   </c:choose>
  </ul>
 </nav>
 <p>전체게시글 수 : ${boards.totalElements} </p>
현재페이지 ${boards.number + 1} /총 페이지 : ${boards.totalPages}

</div>

<script src="/js/index.js"></script>
<%@ include file="./layout/footer.jsp" %>