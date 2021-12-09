function likeup(id){
    var one=0
    $.ajax({
        type:"GET",
        url:'/api/'+id+'/like',
        dataType:"json"
    })
        .done(res=>{
            if(res===1){
                alert("회원에게 좋아요를 누르셨어요");
                if(one==0){
                    one=one+1;
                }else {
                    alert("회원에게 이미 좋아요 누르셨어요");
                    window.location.href='http://localhost:8080/';
                }

            }else {
                alert("서버실행 중 에러");
                window.location.href='http://localhost:8080/';
            }
        }).fail(error=>{
            console.log("api실패",error);
    });
}