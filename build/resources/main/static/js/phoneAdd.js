$(document).ready(function(){
    function uploadSummernoteImageFile(file, editor) {
        let data = new FormData();
        data.append("file", file);
        data.append("relatDiv", "phone" );
        $.ajax({
            data: data,
            type: "POST",
            url: "/upload/image",
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
        placeholder: '상품설명을 입력해주세요.',
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
        if(fileData.get('file1')==null) {
            alert("메인 사진은 필수 등록사항 입니다.");
            $("#file1").focus();
            e.preventDefault();
        }else if ($("#location").val()==''){
            alert("지역을 선택 해주세요.");
            $("#location").focus();
            e.preventDefault();
        }else if ($("#dealWay").val()==''){
            alert("거래방법을 선택해주세요.");
            $("#dealWay").focus();
            e.preventDefault();
        }else if ($("#phoneYn").val()==''){
            alert("연락처 공개여부를 선택해주세요.");
            $("#phoneYn").focus();
            e.preventDefault();
        }else if (!$("#price").val()){
            alert("가격을 입력하세요.");
            $("#price").focus();
            e.preventDefault();
        }else if ($("#inquiry").val()==''){
            alert("상품문의여부 체크를 해주세요.");
            $("#inquiry").focus();
            e.preventDefault();
        }else if(!$("#title").val()) {
            alert("제목을 입력하세요.");
            $("#title").focus();
            e.preventDefault();
        } else if($("#summernote").summernote('isEmpty')) {
            alert("내용을 입력하세요.");
            e.preventDefault();
        }else{
            alert("등록되었습니다.");
            fileUpload($("#fileDiv").val());
        }
    });
})

$(document).ready(function (){

    if ($("#phoneId").val()){
        fileData.append('relatId',$("#phoneId").val());
    }
    $("input[name='file1']").change(function() {
        if( $("input[name='file1']").val() == '' ) {
            $('#fileView1').attr('src' , '');
        }
        $('#fileView1').css({ 'display' : '' });
        readURL1(this);
        fileSetup(this);
    });
    $("input[name='file2']").change(function() {
        if( $("input[name='file2']").val() == '' ) {
            $('#fileView2').attr('src' , '');
        }
        $('#fileView2').css({ 'display' : '' });
        readURL2(this);
        fileSetup(this);
    });
    $("input[name='file3']").change(function() {
        if( $("input[name='file3']").val() == '' ) {
            $('#fileView3').attr('src' , '');
        }
        $('#fileView3').css({ 'display' : '' });
        readURL3(this);
        fileSetup(this);
    });
    $("input[name='file4']").change(function() {
        if( $("input[name='file4']").val() == '' ) {
            $('#fileView4').attr('src' , '');
        }
        $('#fileView4').css({ 'display' : '' });
        readURL4(this);
        fileSetup(this);
    });
});

//경로따라이미지변경
function readURL1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView1').css({'background-image':'url('+e.target.result+')'});
            $('#fileView1').addClass('addimg')
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView2').css({'background-image':'url('+e.target.result+')'});
            $('#fileView2').addClass('addimg')
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL3(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView3').css({'background-image':'url('+e.target.result+')'});
            $('#fileView3').addClass('addimg')
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL4(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView4').css({'background-image':'url('+e.target.result+')'});
            $('#fileView4').addClass('addimg')
        }
        reader.readAsDataURL(input.files[0]);
    }
}

//파일 리스트 셋팅
const fileData = new FormData();
function fileSetup(fileInput){
    for (let key of fileData.keys()){
        if (key==fileInput.id){
            fileData.delete(fileInput.id);
        }
    }
    fileData.append(fileInput.id,fileInput.files[0]);
}

//파일 리스트에서 삭제
function fileDelete(fileInput){
    fileData.delete(fileInput);
}

//파일 업로드
function fileUpload(fileDiv){
    fileData.append('fileDiv',fileDiv);
    $.ajax({
        type:"POST",
        url:"/file/upload",
        processData: false,
        contentType: false,
        data: fileData,
    })
}

//파일 수정
function fileUpdate(fileDiv){
    fileData.append('fileDiv',fileDiv);
    $.ajax({
        type:"POST",
        url:"/file/update",
        processData: false,
        contentType: false,
        data: fileData,
    })
}

$("#btn_modify").click(function(e) {
    let fileRowLength = $(".sub-row").length;
    alert("공지사항이 수정되었습니다.");
});

//서버에서 파일 삭제
function deleteFileOnServer(btnId,relatId,fileDiv) {
    let fileNum = btnId.substring(10, 11)
    let flag = confirm("등록된 사진을 삭제하시겠 습니까? 수정취소 하더라도 복구되지 않습니다.");
    if (flag) {
        if (btnId == "delFileBtn1") {
            $("input[name='file1']").val('');
            fileDelete('file1');
            $('#fileView1').css({'background-image': 'url(' + 'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg' + ')'});
            $('#fileView1').attr('class', 'btn btn-primary imagePreviewMain');
        }
        if (btnId == "delFileBtn2") {
            $("input[name='file2']").val('');
            fileDelete('file2');
            $('#fileView2').css({'background-image': 'url(' + 'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg' + ')'});
            $('#fileView2').attr('class', 'btn btn-primary imagePreviewMain');
        }
        if (btnId == "delFileBtn3") {
            $("input[name='file3']").val('');
            fileDelete('file3');
            $('#fileView3').css({'background-image': 'url(' + 'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg' + ')'});
            $('#fileView3').attr('class', 'btn btn-primary imagePreviewMain');
        }
        if (btnId == "delFileBtn4") {
            $("input[name='file4']").val('');
            fileDelete('file4');
            $('#fileView4').css({'background-image': 'url(' + 'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg' + ')'});
            $('#fileView4').attr('class', 'btn btn-primary imagePreviewMain');
        }
        $.ajax({
            url:"/file/deleteFile",
            type:"POST",
            data:{"fileNum":fileNum,"relatId":relatId,"fileDiv":fileDiv},
            error : function (){
                alert("삭제 오류입니다. 관리자에게 문의하세요.")
            }
        })
    }
}