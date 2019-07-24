function post() {
    var questionId = $("#question_id").val();
    var questionContent = $("#comment_content").val();
    $.ajax({
            type: "POST",
            url: "/comment",
            contentType:"application/json",
            data: JSON.stringify({
                "parentId":questionId,
                "content":questionContent,
                "type":1
            }),
        success:function (response) {
            if(response.code == 200){
                console.log(response.code)
                window.location.reload();
            }
        },
        dataType:"json"
        })
}