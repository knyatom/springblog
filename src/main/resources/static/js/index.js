let index={
    init:function(){
//      $('#detail-btn').on('click',()=>{
//        this.count();
//         var id=$('#detail-btn').attr("data-id");
//                console.log(id);
//      });
    },

    // 조회수증가하기
      count:function(){
        var id=$('#detail-btn').attr("data-id");
        console.log(id);
          $.ajax({
            type:"PATCH",
            url:"/api/count/"+id
          });
      }

}

index.init();

