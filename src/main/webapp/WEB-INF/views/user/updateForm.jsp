<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<%-- ${principal} --%>
<div class="container mb-3">
     <form>
      <input type="hidden" id="id" value="${principal.id}">
       <div class="form-group">
        <label for="username">사용자아이디(username)</label>
        <input type="text" id="username" class="form-control"
        value=${principal.username} readonly />
       </div>
       <div class="form-group">
          <label for="password">패스워드</label>
           <input type="password" id="password" class="form-control"
           value=${principal.password}
           >
       </div>
       <div class="form-group">
        <label for="email">이메일(Email)</label>
        <input type="email" id="email" class="form-control"
        value=${principal.email}
        >
       </div>
    </form>
   <button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>