$(function () {
    $('.upload_text').val('사업자등록증 사본 파일 업로드해주세요');
    $('.input_file').change(function () {
        var i = $(this).val();
        $('.upload_text').val(i);
    });
});