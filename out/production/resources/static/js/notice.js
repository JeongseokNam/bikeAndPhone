$(document).ready(function(){
function uploadSummernoteImageFile(file, editor) {
    let data = new FormData();
    data.append("file", file);
    data.append("relatDiv", "notice" );
    $.ajax({
        type: "POST",
        url: "/upload/image",
        data: data,
        contentType: false,
        processData: false,
        success: function(data, result, res) {
            if(res.status == 200) {
                console.log(data);
                let imgUrl = data.img;
                $(editor).summernote('insertImage', imgUrl);
            } else {
                alert('이미지 업로드중 오류가 발생했습니다.');
            }
        },
        error: function (xhr) {
            alert('이미지 업로드중 오류가 발생했습니다.');
        }
    });
}


       $('#summernote').summernote({
        placeholder: '공지사항을 입력해주세요.',
        tabsize: 2,
        height: 400,
        toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
            ['color', ['forecolor', 'color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['insert', ['picture', 'link']]
        ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체',
            '굴림', '돋음체', '바탕체'],
        fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36',
            '50', '72'],
        callbacks: {
            onImageUpload: function (files) {
                uploadSummernoteImageFile(files[0], this);
            }
        }
    });


        $("#btn_submit").click(function(e) {
                let fileRowLength = $(".sub-row").length;
                if(!$("#title").val()) {
                    alert("제목을 입력하세요.");
                    e.preventDefault();
                } else if($("#summernote").summernote('isEmpty')) {
                    alert("내용을 입력하세요.");
                    e.preventDefault();
                }else{
                    alert("공지사항이 등록되었습니다.");
                }
                });

        $("#btn_modify").click(function(e) {
                let fileRowLength = $(".sub-row").length;
                alert("공지사항이 수정되었습니다.");
                });
})
