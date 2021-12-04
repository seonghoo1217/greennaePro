var id_check_num;



function checkId(){

    var username=$("#username").val();

    console.log(username)

    $.ajax({
        type:"get",
        url:'/api/login/'+username+'/exist',
        dataType: "json"
    })
        .done(res=>{
            console.log(res, "중복 api 성공")
            if(res === 1){
                alert("사용하실수 있습니다!! ")
                id_check_num=1;
            }else if(res===0){
                id_check_num=2;
                alert("이미 존재하는 아이디입니다")
            }else{
                id_check_num=0;
            }

        }).fail(error=>{
        console.log(error, "중복 api 오류")
    });
}


function block_none_idCheck(){
    $('#registed').on('click',function (e){
        if(id_check_num===2){
            alert("아이디 중복 확인 후 다시 이용하여주세요");
            e.preventDefault();
        }else if(id_check_num===0){
            alert("아이디 입력 후 다시 이용하여주세요");
            e.preventDefault();
        }else{
            console.log("통과");
        }
    })
}
$(".submitBtn").click(function (e){
    alert("회원가입 완료되었습니다.");
})
