let index={
    init:function(){
      $('#btn-save').on('click',()=>{
        this.save();
      });
      $('#btn-login').on('click',()=>{
         this.login();
      });
      $('#btn-check').on('click',(e)=>{
         e.preventDefault();
         this.check();
      });
      $('#btn-update').on('click',(e)=>{
       //        e.preventDefault();
            this.update();
      });
    },

      update:function(){
          let data={
             id:$('#id').val(),
             password:$('#password').val(),
             email:$('#email').val()
          }
          $.ajax({
            type:"PUT",
            url:"/user",
            data:JSON.stringify(data),
            contentType:"application/json;utf-8",
            dataType:"json"
          }).done(function(resp){
           alert("회원수정이 완료되었습니다.");
           location.href="/";
          }).fail(function(error){
            console.log(JSON.stringify(error));
          });
        } ,

     check:function(){
         let username=$('#username').val();
          $.ajax({
            type:"GET",
            url:"/api/user/"+username,
            contentType:"application/json;utf-8"
          }).done(function(resp){
            if(resp=="OK"){
                alert("사용할 수 있는 아이디입니다.");
                return;
            }else{
                alert("아이디 불가합니다.(중복)");
                $('#username').val("");
                $('#username').focus();
                return;
            }
          }).fail(function(error){
            console.log(JSON.stringify(error));
          });
        },

    // 로그인처리
   login:function(){
      let data={
         username:$('#username').val(),
         password:$('#password').val()
      }
      $.ajax({
        type:"POST",
        url:"/api/user/login",
        data:JSON.stringify(data),
        contentType:"application/json;utf-8",
        dataType:"json"
      }).done(function(resp){
        console.log(resp);
        if(resp.data==1){
            alert("로그인 되었습니다.");
              location.href="/";
        }else{
            alert("아이디 비번이 틀립니다.");
            $('#username').val("");
            $('#password').val("");
            return ;
        }
      }).fail(function(error){
        console.log(JSON.stringify(error));
      });
    },

// 회원가입
    save:function(){
      let data={
         username:$('#username').val(),
         password:$('#password').val(),
         email:$('#email').val()
      }
      console.log(data);  // javascript object
      console.log(JSON.stringify(data)) ;    // JSON문자열
      $.ajax({
        type:"POST",
        url:"/api/user",
        data:JSON.stringify(data),
        contentType:"application/json;utf-8",
        dataType:"json" // 응답이 왔을때
      }).done(function(resp){
        if(resp.data==1){
            alert("회원가입이 완료되었습니다.");
            location.href="/";
        }else{
            alert("아이디가 중복되었습니다. ");
            return;
        }

      }).fail(function(error){
        console.log(JSON.stringify(error));
      });
    }
}

index.init();

