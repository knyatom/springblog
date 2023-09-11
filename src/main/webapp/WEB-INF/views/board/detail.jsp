<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
   <button class='btn btn-secondary' onclick="history.back()">돌아가기</button>

   <c:if test="${board.user.id==principal.id}">
    <!-- <button class='btn btn-warning' id="btn-update">수정</button> -->
     <a class='btn btn-warning' href="/board/${board.id}/updateForm">수정</a>
    <button class='btn btn-danger' id="btn-delete">삭제</button>
   </c:if>
   <hr>
 <div class="m-3 form-group">
    글번호 : <span id="id"><i>${board.id}</i></span> |
    작성자 : <i>${board.user.username}</i> |
     조회수 : <span><i>${board.count}</i></span>
  </div>
     <hr>
  <div class="m-3 form-group">
    <label class="form-label">제목(Title)</label>
    <h4> ${board.title}</h4>
 </div>
 <hr>
 <div class="m-3 form-group">
   <label class="form-label">내용(Content)</label>
   <div>${board.content}</div>
 </div>

<hr>

<!-- 댓글 등록화면 -->

<div class="card">
 <form>
    <input type="hidden" id="boardId" value="${board.id}">
    <div class="card-body">
     <textarea class="form-control" id="reply-content"></textarea></div>
    <div class="card-footer">
      <button class="btn btn-primary" id="btn-reply-save">댓글등록</button>
    </div>
 </form>
</div>

 <div class="card">
   <div class="card-header">댓글리스트</div>
     <ul class="list-group" id="reply-box">
      <c:forEach var="reply" items="${board.replies}">
        <li class="list-group-item d-flex justify-content-between" id="reply-1">
          <div>${reply.content}</div>
          <div class="d-flex">
              <div class="font-italic"> 작성자: ${reply.user.username} |

              </div>
          <c:if test="${reply.user.id eq principal.id}">
           <button class="badge"
           onclick="index.replyDelete(${board.id},${reply.id})">삭제</button>
           </c:if>
          </div>
        </li>
      </c:forEach>
     </ul>
   </div>
 </div>
</div>


<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>