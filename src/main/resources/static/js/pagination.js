function pagination(pageUrl) {
    const form = $('#searchForm');
    $("#page").val(pageUrl)
    form.submit();
}
/*법인,개인 사업자 분류검색용*/
function businessSearch(businessKind) {
    if (businessKind=='all'){
        $("#soleOrCorporate").val().remove()
    }
    if (businessKind=='sole'){
        $("#soleOrCorporate").val(businessKind)
    }
    if (businessKind=='corporate'){
        $("#soleOrCorporate").val(businessKind)
    }
    $("#searchForm").submit();
}