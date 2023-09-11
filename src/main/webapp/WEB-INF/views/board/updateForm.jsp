<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<div class="container mt-5 pt-5">
    <form class="container">
        <!-- <input type="hidden" id="id" value="${board.id}" /> -->
        <div class="mb-3">
          <label class="form-label">글번호</label>
          <input type="text"
          class="form-control" name="id" id="id" value="${board.id}" readonly>
        </div>
        <div class="mb-3">
          <label class="form-label">제목(Title)</label>
          <input type="text"
          class="form-control" name="title" id="title" value="${board.title}">
        </div>
        <div class="mb-3">
        <label class="form-label">내용(Content)</label>
        <textarea
        class="form-control summernote" rows="3" name="content" id="content">
         ${board.content}
        </textarea>
        </div>
        <a href="/">Back</a>
    </form>
<button type="submit" class="btn btn-primary" id="btn-update">글쓰기완료</button>
</div>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>
<script>
$('.summernote').summernote({
placeholder: '안녕 써머 노트입니다. ',
tabsize: 2,
height: 300
});
</script>