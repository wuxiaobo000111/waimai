
var vm = new Vue({
    el:'#dtApp',
    data:{

    },
    methods: {
        loadTable: function(){

            var columns = [
                {checkbox:true},
                {
                    field: 'foodTypeId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                {title:'食物类型名称',field:'foodTypeName'},
                { title:'编辑', field:'foodTypeId', formatter: function(value,row,index){
                    var edit = '<a href="/waimai/foodType/edit.action?foodTypeId=' + value + '" class="btn btn-success btn-xs">编辑</a>';
                    return edit;
                }
                }
            ];
            var option = T.btTableOption;
            var allColumns = columns;//合并列
            option.columns = allColumns;
            option.url = '/waimai/foodType/list.action';

            $('#table').bootstrapTable(option);
        },
        deleteBatch: function(){
            T.doTask("userId","/waimai/user/deleteMore.action");
        }
    }
});

vm.loadTable();