
var vm = new Vue({
    el:'#dtApp',
    data:{

    },
    methods: {
        loadTable: function(){
            var columns = [
                {checkbox:true},
                {
                    field: 'newsId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '新闻类型', field: 'newsType.newsTypeName'},
                { title: '新闻标题', field: 'newsTitle'},
                { title: '新闻创建时间', field: 'newsCreateTime'},
                { title: '新闻作者姓名', field: 'newsAuthorName'},
                { title: '新闻内容', field: 'newsId',formatter:function (value,row,index) {
                    var link='<a href="/news/details.action?newsId=' + value +'" class="btn btn-primary">新闻链接</a>';
                    return link;
                }},
                { title:'编辑', field:'newsId', formatter: function(value,row,index){
                    var edit = '<a href="/news/edit.action?newsId=' + value + '" class="btn btn-success btn-xs">编辑</a>';
                    return edit;
                }
                },
                {
                    title:'删除',field:'newsId' ,formatter:function (value,row,index) {
                    var edit = '<a href="javascript:void(0)" class="btn btn-danger btn-xs" onclick="vm.deleteNews('+value+')">删除</a>';
                    return edit;
                }
                }
            ];




            var option = T.btTableOption;
            var allColumns = columns;//合并列
            option.columns = allColumns;
            option.url = '/news/list.action';

            $('#table').bootstrapTable(option);
        },
        deleteNews:function (newsId) {
            layer.open({
                title:"提示信息",
                content:"你确定要删除？",
                btn:["确定"],
                yes:function (index,layero) {
                    $.get("/news/deleteNews.action?newsId="+newsId,function (r) {
                        if (r.code==1){
                            layer.open({
                                title:"提示信息",
                                content:"删除成功",
                                btn:["确定"],
                                yes:function (index,layero) {
                                    window.location.href="/news/index.action";
                                }
                            })
                        }
                        else{
                            layer.alert(data.data);
                        }
                    })
                }
            })
        },
        deleteBatch: function(){
            T.doTask("newsId","/news/deleteMore.action");
        }
    }
});

vm.loadTable();