function paginator (totalCount,pageSize,url) {
    $('#pageList').jqPaginator({
        totalCounts: totalCount,
        pageSize: pageSize,
        visiblePages: 5,
        currentPage: 1,
        first: '<a href="javascript:void(0);">首页</a>',
        prev: '<a href="javascript:void(0);">上一页</a>',
        next: '<a href="javascript:void(0);">下一页</a>',
        last: '<a href="javascript:void(0);">末页</a>',
        page: '<a href="javascript:void(0);">{{page}}</a>',
        onPageChange: function (num, type) {
            $.ajax({
                url: url,
                type:"GET",
                data: {
                    page: num-1,
                    pageSize: pageSize,
                },
                success:function(data){
                    $('#blogsBox').html(data);
                }
            })

        }
    });
}


function getCurrentURI() {
    var url = window.location.href
    url = url.split("\/\/")[1]
    var index = url.indexOf("\/")
    var uri =url.substring(index)
    if(uri.indexOf("?") !== -1){
        uri = uri.split("?")[0];
    }
    return uri
}


