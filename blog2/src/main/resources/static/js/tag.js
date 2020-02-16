function getArticleByTag(url) {
    $.ajax({
        url: url,
        type:"GET",
        success:function(data){
            $('#blogsBox').html(data);
            $('#pageList').css('display','none')
        }
    })
}