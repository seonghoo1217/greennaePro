function onUpgrade(id){
    $.ajax({
        type:"POST",
        url:'/api/user/'+id+'/upgrade',
        dataType: "json"
    })
        .done(res=>{
            if(res === 1) {
                alert("유저 2레벨로 등급이 올라갔습니다")
            }
            else{
                console.log("api 실패")
            }
            }).fail(error=>{
            console.log("update 실패")
    });
}
function onDelete(id){
    $.ajax({
        type:"POST",
        url:'/api/user/'+id+'/delete',
        dataType: "json"
    })
        .done(res=>{
            if(res === 1) {
                alert("유저가 삭제되었습니다")
            }
            else{
                console.log("api 실패")
            }
        }).fail(error=>{
        console.log(error, "update 실패")
    });
}