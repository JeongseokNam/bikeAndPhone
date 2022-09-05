$(document).ready(function (){
    if ($("#boardId").val()){
        fileData.append('relatId',$("#boardId").val());
    }
    $("input[name='file1']").change(function() {//파일 변경시
        if( $("input[name='file1']").val() == '' ) {
            $('#fileView1').attr('src' , '');
        }
        $('#fileView1').css({ 'display' : '' });
        readURL1(this);
        fileSetup(this);
    });
    $("input[name='file2']").change(function() {//파일 변경시
        if( $("input[name='file2']").val() == '' ) {
            $('#fileView2').attr('src' , '');
        }
        $('#fileView2').css({ 'display' : '' });
        readURL2(this);
        fileSetup(this);
    });
    $("input[name='file3']").change(function() {//파일 변경시
        if( $("input[name='file3']").val() == '' ) {
            $('#fileView3').attr('src' , '');
        }
        $('#fileView3').css({ 'display' : '' });
        readURL3(this);
        fileSetup(this);
    });
    $("input[name='file4']").change(function() {//파일 변경시
        if( $("input[name='file4']").val() == '' ) {
            $('#fileView4').attr('src' , '');
        }
        $('#fileView4').css({ 'display' : '' });
        readURL4(this);
        fileSetup(this);
    });
    $("input[name='file5']").change(function() {//파일 변경시
        if( $("input[name='file5']").val() == '' ) {
            $('#fileView5').attr('src' , '');
        }
        $('#fileView5').css({ 'display' : '' });
        readURL5(this);
        fileSetup(this);
    });
    $("input[name='file6']").change(function() {//파일 변경시
        if( $("input[name='file6']").val() == '' ) {
            $('#fileView6').attr('src' , '');
        }
        $('#fileView6').css({ 'display' : '' });
        readURL6(this);
        fileSetup(this);
    });
    $("input[name='file7']").change(function() {//파일 변경시
        if( $("input[name='file7']").val() == '' ) {
            $('#fileView7').attr('src' , '');
        }
        $('#fileView7').css({ 'display' : '' });
        readURL7(this);
        fileSetup(this);
    });
    $("input[name='file8']").change(function() {//파일 변경시
        if( $("input[name='file8']").val() == '' ) {
            $('#fileView8').attr('src' , '');
        }
        $('#fileView8').css({ 'display' : '' });
        readURL8(this);
        fileSetup(this);
    });
    $("input[name='file9']").change(function() {//파일 변경시
        if( $("input[name='file9']").val() == '' ) {
            $('#fileView9').attr('src' , '');
        }
        $('#fileView9').css({ 'display' : '' });
        readURL9(this);
        fileSetup(this);
    });
    $("input[name='file10']").change(function() {//파일 변경시
        if( $("input[name='file10']").val() == '' ) {
            $('#fileView10').attr('src' , '');
        }
        $('#fileView10').css({ 'display' : '' });
        readURL10(this);
        fileSetup(this);
    });
    $("input[name='file11']").change(function() {//파일 변경시
        if( $("input[name='file11']").val() == '' ) {
            $('#fileView11').attr('src' , '');
        }
        $('#fileView11').css({ 'display' : '' });
        readURL11(this);
        fileSetup(this);
    });
/*유효성체크*/
    $("#btn_submit").click(function(e) {
        let checked1 = $('#changeAgree1').is(':checked');
        let checked2 = $('#changeAgree2').is(':checked');
        let checked3 = $('#negoAgree1').is(':checked');
        let checked4 = $('#negoAgree2').is(':checked');
        let checked5 = $('#tuningYn1').is(':checked');
        let checked6 = $('#tuningYn2').is(':checked');
        let checked7 = $('#accidentYn1').is(':checked');
        let checked8 = $('#accidentYn2').is(':checked');
        let checked9 = $('#afterServiceYn1').is(':checked');
        let checked10 = $('#afterServiceYn2').is(':checked');
        if(fileData.get('file1')==null){
            alert("메인 사진은 필수 등록사항 입니다.");
            $("#file1").focus();
            e.preventDefault();
        }else if($("#title").val()==''){
            alert("게시글 제목을 입력해 주세요.");
            $("#title").focus();
            e.preventDefault();
        }else if($("#location").val()==''){
            alert("판매지역을 선택해 주세요.");
            $("#location").focus();
            e.preventDefault();
        }else if($("#locationDetail").val()==''){
            alert("판매지역을 선택해 주세요.");
            $("#locationDetail").focus();
            e.preventDefault();
        }else if($("#bikeBrand").val()==''){
            alert("제조회사를 선택해 주세요.");
            $("#bikeBrand").focus();
            e.preventDefault();
        }else if($("#bikeModel").val()==''){
            alert("바이크 모델을 선택해 주세요.");
            $("#bikeModel").focus();
            e.preventDefault();
        }else if($("#bikeYear").val()==''){
            alert("제조년도를 선택해 주세요.");
            $("#bikeYear").focus();
            e.preventDefault();
        }else if($("#driveKm").val()==""){
            alert("주행거리를 입력해 주세요");
            $("#driveKm").focus();
            e.preventDefault();
        }else if($("#bikeColor").val()=='') {
            alert("색상을 선택해 주세요.");
            $("#bikeColor").focus();
            e.preventDefault();
        }else if($("#bikeCc").val()==''){
            alert("배기량 입력해 주세요");
            $("#bikeCc").focus();
            e.preventDefault();
        }else if(!checked1&&!checked2){
            alert("대차/교환 여부를 확인해 주세요.");
            $("#changeAgree1").focus();
            e.preventDefault();
        }else if(checked1&&checked2){
            alert("둘중 하나만 선택 가능합니다.");
            $("#changeAgree1").focus();
            e.preventDefault();
        }else if(!checked3&&!checked4){
            alert("가격절충 여부를 확인해 주세요.");
            $("#negoAgree1").focus();
            e.preventDefault();
        }else if(checked3&&checked4){
            alert("둘중 하나만 선택 가능합니다.");
            $("#negoAgree1").focus();
            e.preventDefault();
        }else if(!checked5&&!checked6){
            alert("튜닝유무 여부를 확인해 주세요.");
            $("#tuningYn1").focus();
            e.preventDefault();
        }else if(checked5&&checked6){
            alert("둘중 하나만 선택 가능합니다.");
            $("#tuningYn1").focus();
            e.preventDefault();
        }else if(!checked7&&!checked8){
            alert("사고유무 여부를 확인해 주세요.");
            $("#accidentYn1").focus();
            e.preventDefault();
        }else if(checked7&&checked8){
            alert("둘중 하나만 선택 가능합니다.");
            $("#tuningYn1").focus();
            e.preventDefault();
        }else if(!checked9&&!checked10){
            alert("A/S 여부를 확인해 주세요.");
            $("#afterServiceYn1").focus();
            e.preventDefault();
        }else if(checked9&&checked10){
            alert("둘중 하나만 선택 가능합니다.");
            $("#afterServiceYn2").focus();
            e.preventDefault();
        }else if(!$("#price").val()){
            alert("판매가격을 입력해 주세요");
            $("#price").focus();
            e.preventDefault();
        }else{
            fileUpload($("#fileDiv").val());
        }
    });

})
//경로따라이미지변경
function readURL1(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView1').css({'background-image':'url('+e.target.result+')'});
            $('#fileView1').addClass('addimg')
            // 220609 오후 6시 32분 수정
            // $('#fileView1').css({'background-size':'contain'});
            // $('#fileView1').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL2(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView2').css({'background-image':'url('+e.target.result+')'});
            $('#fileView2').addClass('addimg')
            // $('#fileView2').css({'background-size':'contain'});
            // $('#fileView2').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL3(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView3').css({'background-image':'url('+e.target.result+')'});
            $('#fileView3').addClass('addimg')
            // $('#fileView3').css({'background-size':'contain'});
            // $('#fileView3').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL4(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView4').css({'background-image':'url('+e.target.result+')'});
            $('#fileView4').addClass('addimg')
            // $('#fileView4').css({'background-size':'contain'});
            // $('#fileView4').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL5(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView5').css({'background-image':'url('+e.target.result+')'});
            $('#fileView5').addClass('addimg')
            // $('#fileView5').css({'background-size':'contain'});
            // $('#fileView5').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL6(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView6').css({'background-image':'url('+e.target.result+')'});
            $('#fileView6').addClass('addimg')
            // $('#fileView6').css({'background-size':'contain'});
            // $('#fileView6').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL7(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView7').css({'background-image':'url('+e.target.result+')'});
            $('#fileView7').addClass('addimg')
            // $('#fileView7').css({'background-size':'contain'});
            // $('#fileView7').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL8(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView8').css({'background-image':'url('+e.target.result+')'});
            $('#fileView8').addClass('addimg')
            // $('#fileView8').css({'background-size':'contain'});
            // $('#fileView8').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL9(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView9').css({'background-image':'url('+e.target.result+')'});
            $('#fileView9').addClass('addimg')
            // $('#fileView9').css({'background-size':'contain'});
            // $('#fileView9').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL10(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView10').css({'background-image':'url('+e.target.result+')'});
            $('#fileView10').addClass('addimg')
            // $('#fileView10').css({'background-size':'contain'});
            // $('#fileView10').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL11(input) {
    if (input.files && input.files[0]) {//선택된파일이 있을시
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#fileView11').css({'background-image':'url('+e.target.result+')'});
            $('#fileView11').addClass('addimg')
            // $('#fileView11').css({'background-size':'contain'});
            // $('#fileView11').css({'background-repeat':'no-repeat'});
        }
        reader.readAsDataURL(input.files[0]);
    }
}
//파일 리스트 셋팅
const fileData = new FormData();
function fileSetup(fileInput){
    for (let key of fileData.keys()){ //파일데이터 키값 조회
        if (key==fileInput.id){ //같은 키값 있으면
            fileData.delete(fileInput.id); // 기존데이터 삭제
        }
    }
    fileData.append(fileInput.id,fileInput.files[0]); //새로운 데이터 추가
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

//서버에서 파일 삭제
function deleteFileOnServer(btnId,relatId,fileDiv){
    let fileNum = btnId.substring(10,11)
    let flag = confirm("등록된 사진을 삭제하시겠 습니까? 수정취소 하더라도 복구되지 않습니다.");
    if (flag){
        if (btnId=="delFileBtn1"){
            $("input[name='file1']").val('');
            fileDelete('file1');
            $('#fileView1').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView1').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn2"){
            $("input[name='file2']").val('');
            fileDelete('file2');
            $('#fileView2').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView2').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn3"){
            $("input[name='file3']").val('');
            fileDelete('file3');
            $('#fileView3').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView3').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn4"){
            $("input[name='file4']").val('');
            fileDelete('file4');
            $('#fileView4').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView4').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn5"){
            $("input[name='file5']").val('');
            fileDelete('file5');
            $('#fileView5').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView5').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn6"){
            $("input[name='file6']").val('');
            fileDelete('file6');
            $('#fileView6').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView6').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn7"){
            $("input[name='file7']").val('');
            fileDelete('file7');
            $('#fileView7').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView7').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn8"){
            $("input[name='file8']").val('');
            fileDelete('file8');
            $('#fileView8').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView8').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn9"){
            $("input[name='file9']").val('');
            fileDelete('file9');
            $('#fileView9').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView9').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn10"){
            $("input[name='file10']").val('');
            fileDelete('file10');
            $('#fileView10').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView10').attr('class','btn btn-primary imagePreviewMain');
        }
        if (btnId=="delFileBtn11"){
            $("input[name='file11']").val('');
            fileDelete('file11');
            $('#fileView11').css({'background-image':'url('+'http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg'+')'});
            $('#fileView11').attr('class','btn btn-primary imagePreviewMain');
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