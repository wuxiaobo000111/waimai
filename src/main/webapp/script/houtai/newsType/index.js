
var vm = new Vue({
    el:'#dtApp',
    data:{

    },
    methods: {
        loadTable: function(){

            var columns = [
                {checkbox:true},
                {
                    field: 'newsTypeId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                {title:'新闻类型名称',field:'newsTypeName'},
                { title:'编辑', field:'newsTypeId', formatter: function(value,row,index){
                    var edit = '<a href="/newsType/edit.action?newsTypeId=' + value + '" class="btn btn-success btn-xs">编辑</a>';
                    return edit;
                }
                }
            ];
            var option = T.btTableOption;
            var allColumns = columns;//合并列
            option.columns = allColumns;
            option.url = '/newsType/list.action';

            $('#table').bootstrapTable(option);
        },
        deleteBatch: function(){
            T.doTask("newsTypeId","/newsType/deleteMore.action");
        }
    }
});

vm.loadTable();