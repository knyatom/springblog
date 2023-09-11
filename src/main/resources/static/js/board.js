let index={
    init:function(){
      $('#btn-write').on('click',()=>{
        this.save();
      });
      $('#btn-delete').on('click',()=>{
         this.deleteById();
      });
      $('#btn-update').on('click',()=>{
               this.update();
      });
      $('#btn-reply-save').on('click',()=>{
           event.preventDefault();
              this.replySave();
      });

    },

    // 댓글삭제하기
      replyDelete:function(boardId,replyId){
          $.ajax({
            type:"DELETE",
            url:`/api/board/${boardId}/reply/${replyId}`,
          }).done(function(resp){
            alert("댓글 삭제가 성공하였습니다.");
            location.href=`/board/${boardId}`;
          }).fail(function(error){
            console.log(JSON.stringify(error));
          });
        },

     replySave:function(){
          let data={
             boardId:$('#boardId').val(),
             content:$('#reply-content').val()
          }
          console.log(data);
          $.ajax({
            type:"POST",
            url:`/api/board/${data.boardId}/reply`,
            data:JSON.stringify(data),
            contentType:"application/json;utf-8",
            dataType:"json"
          }).done(function(resp){
            alert("댓글이 등록이 되었습니다.");
            location.href=`/board/${data.boardId}`;
          }).fail(function(error){
            console.log(JSON.stringify(error));
          });
        },


    // 수정하기
     update:function(){
          let id=$("#id").val();
          let data={
             title:$('#title').val(),
             content:$('#content').val()
          }
          $.ajax({
            type:"PUT",
            url:"/api/board/"+id,
            data:JSON.stringify(data),
            contentType:"application/json;utf-8",
            dataType:"json"
          }).done(function(resp){
            alert("글수정이 완료되었습니다.");
            location.href="/";
          }).fail(function(error){
            console.log(JSON.stringify(error));
          });
        },

    // 삭제하기
      deleteById:function(){
         var id=$('#id').text();
          $.ajax({
            type:"DELETE",
            url:"/api/board/"+id
          }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.href="/";
          }).fail(function(error){
            console.log(JSON.stringify(error));
          });
        },

    // 글쓰기완료
   save:function(){
      let data={
         title:$('#title').val(),
         content:$('#content').val()
      }
      $.ajax({
          type:"POST",
          url:'/api/board',
          data:JSON.stringify(data),
           contentType:"application/json; charset=utf-8",
           dataType:"json"
      }).done(function(resp){
           if(resp.data==1){
               alert("글쓰기가 완료되었습니다.");
               location.href="/";
            }else{
                 alert("세션이 만료되었습니다.");
                 location.href="/user/loginForm";
            }
         }).fail(function(error){
             alert(JSON.stringify(error));
         });
    }
}

index.init();

