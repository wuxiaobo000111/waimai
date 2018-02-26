
var vm = new Vue({
    el:'#dtApp',
    data:{

    },
    methods: {
        loadTable: function(){
            var columns = [
                {checkbox:true},
                {
                    field: 'advertId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '广告名', field: 'advertName'},
                { title: '广告创建时间', field: 'advertCreateTime'},
                { title: '广告图片', field: 'advertPictureUrl',formatter:function (value,row,index) {
                    var pic='<img src="/waimai'+value+'" style="width: 100px" height="60px">'
                    return pic
                }},
                { title: '广告描述', field: 'advertDescription'},
                { title: '是否显示', field: 'advertShow',formatter:function (value,row,index) {
                    if(value==1){
                        return "显示";
                    }else {
                        return "不显示"
                    }
                }},
                { title: '关联外卖', field: 'food.foodName'},
                { title:'编辑', field:'advertId', formatter: function(value,row,index){
                    var edit = '<a href="/waimai/advert/edit.action?advertId=' + value + '" class="btn btn-success btn-xs">编辑</a>';
                    return edit;
                }
                },
                {
                    title:'删除',field:'advertId' ,formatter:function (value,row,index) {
                    var edit = '<a href="javascript:void(0)" class="btn btn-danger btn-xs" onclick="vm.deleteAdvert('+value+')">删除</a>';
                    return edit;
                }
                }
            ];




            var option = T.btTableOption;
            var allColumns = columns;//合并列
            option.columns = allColumns;
            option.url = '/waimai/advert/list.action';

            $('#table').bootstrapTable(option);
        },
        deleteAdvert:function (advertId) {
            layer.open({
                title:"提示信息",
                content:"你确定要删除？",
                btn:["确定"],
                yes:function (index,layero) {
                    $.get("/waimai/advert/deleteAdvert.action?advertId="+advertId,function (r) {
                        if (r.code==1){
                            layer.open({
                                title:"提示信息",
                                content:"删除成功",
                                btn:["确定"],
                                yes:function (index,layero) {
                                    window.location.href="/waimai/advert/index.action";
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
            T.doTask("advertId","/waimai/advert/deleteMore.action");
        }
    }
});

vm.loadTable();