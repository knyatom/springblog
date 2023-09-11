$('#join--submit').on('click', function() {
    var data = {
        username : $('#username').val(),
        password : $('#password').val(),
        email : $('#email').val()
    };
$.ajax({
      type : 'POST',
      url : '/user/join',
      data : JSON.stringify(data),
      contentType : 'application/json; charset=utf-8',
      dataType : 'json'
}).done(function(r) {
       console.log(r);
       if (r.msg == 'OK') {
         console.log(r);
          alert('회원가입이 완료되었습니다.');
      } else {
           console.log(r);
           alert('회원가입 실패');
      }
}).fail(function(r) {
       alert('서버 오류');
   });
});