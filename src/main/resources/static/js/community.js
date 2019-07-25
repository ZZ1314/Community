function post() {
    var questionId = $("#question_id").val();
    var questionContent = $("#comment_content").val();
    if (!questionContent) {
        window.alert("回复内容不可为空")
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": questionContent,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                console.log(response.code)
                window.location.reload();
            }
        },
        dataType: "json"
    })
}

function selectTag(value) {
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}
function showSelectTag(){
    $("#select-tag").show();
}
function cleanTag(){
    $("#tag").val("");
    console.log(' clean done')
}