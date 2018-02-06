
var vm = new Vue({
    el:'#dtApp',
    data:{

    },
    methods: {
        loadTable: function(){

            var columns = [
                {checkbox:true},
                {
                    field: 'foodId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '食物价格', field: 'foodPrice',formatter:function (value,row,index) {
                    return value+"元";
                }},
                { title: '食物名称', field: 'foodName'},
                { title: '卖出数量', field: 'foodSaleCount'},
                { title: '创建时间', field: 'foodCreateTime'},
                { title: '美图', field: 'foodPictureUrl',formatter:function (value,row,index) {
                    var pic='<img src="'+value+'" style="width: 100px" height="60px">'
                    return pic
                }},
                { title: '描述', field: 'foodDescription'},
                { title:'编辑', field:'foodId', formatter: function(value,row,index){
                    var edit = '<a href="/food/edit.action?foodId=' + value + '" class="btn btn-success btn-xs">编辑</a>';
                    return edit;
                }
                },
                {
                    title:'删除',field:'foodId' ,formatter:function (value,row,index) {
                    var edit = '<a href="javascript:void(0)" class="btn btn-danger btn-xs" onclick="vm.deleteFood('+value+')">删除</a>';
                    return edit;
                }
                }
            ];
            var option = T.btTableOption;
            var allColumns = columns;//合并列
            option.columns = allColumns;
            option.url = '/food/list.action';

            $('#table').bootstrapTable(option);
        },
        deleteFood:function (foodId) {
            layer.open({
                title:"提示信息",
                content:"你确定要删除？",
                btn:["确定"],
                yes:function (index,layero) {
                    $.get("/food/deleteFood.action?foodId="+foodId,function (r) {
                        if (r.code==1){
                            layer.open({
                                title:"提示信息",
                                content:"删除成功",
                                btn:["确定"],
                                yes:function (index,layero) {
                                    window.location.href="/food/index.action";
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
            T.doTask("foodId","/food/deleteMore.action");
        }
    }
});

vm.loadTable();