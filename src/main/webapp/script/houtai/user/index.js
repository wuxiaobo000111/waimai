
var vm = new Vue({
    el:'#dtApp',
    data:{

    },
    methods: {
        loadTable: function(){
            var columns = [
                {checkbox:true},
                {
                    field: 'userId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '用户ID', field: 'userId'},
                { title: '用户名', field: 'userName'},
                { title: '邮箱', field: 'userEmail'},
                { title: '手机号', field: 'userPhone'},
                { title: '注册时间', field: 'userRegisterTime'},
                { title:'编辑', field:'userId', formatter: function(value,row,index){
                    var edit = '<a href="/waimai/user/edit.action?userId=' + value + '" class="btn btn-success btn-xs">编辑</a>';
                    return edit;
                }
                },
                {
                    title:'删除',field:'userId' ,formatter:function (value,row,index) {
                    var edit = '<a href="javascript:void(0)" class="btn btn-danger btn-xs" onclick="vm.deleteUser('+value+')">删除</a>';
                    return edit;
                }
                }
            ];




            var option = T.btTableOption;
            var allColumns = columns;//合并列
            option.columns = allColumns;
            option.url = '/waimai/user/list.action';

            $('#table').bootstrapTable(option);
        },
        deleteUser:function (userId) {
            layer.open({
                title:"提示信息",
                content:"你确定要删除？",
                btn:["确定"],
                yes:function (index,layero) {
                    $.get("/waimai/user/deleteUser.action?userId="+userId,function (r) {
                        if (r.code==1){
                            layer.open({
                                title:"提示信息",
                                content:"删除成功",
                                btn:["确定"],
                                yes:function (index,layero) {
                                    window.location.href="/waimai/user/index.action";
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
            T.doTask("userId","/waimai/user/deleteMore.action");
        }
    }
});

vm.loadTable();