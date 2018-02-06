
//全局配置
$.ajaxSetup({
	dataType: "json",
	contentType: "application/json",
	cache: false
});

//工具对象
window.T = {};

//获取请求参数
//使用示例
//location.href = http://localhost:8080/index.html?id=123
//T.p('id') --> 123;
T.p = function(key) {
	 // 获取URL中?之后的字符
	 var str = location.search;
	 str = str.substring(1,str.length);
	 
	 // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
	 var arr = str.split("&");
	 var obj = new Object();
	 
	 // 将每一个数组元素以=分隔并赋给obj对象    
	 for(var i = 0; i < arr.length; i++) {
	     var tmp_arr = arr[i].split("=");
	     obj[decodeURIComponent(tmp_arr[0])] = decodeURIComponent(tmp_arr[1]);
	 }
	 return obj[key];
};
//T.hasP('add') 如果查询字符串中包含'add'返回true，否则返回false;
T.hasP = function(key) {
		// 获取URL中?之后的字符
	 var str = location.search;
	 str = str.substring(1,str.length);
	 
	 // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
	 var arr = str.split("&");
	 var obj = new Object();
	 
	 // 将每一个数组元素以=分隔并赋给obj对象    
	 for(var i = 0; i < arr.length; i++) {
	     var tmp_arr = arr[i].split("=");
	     if(tmp_arr[0] == key) return true;
	 }
	 return false;
};
//删除通用方法
T.deleteMoreItem = function (id) {  
	
	 //返回所有选择的行，当没有选择的记录时，返回一个空数组  
   var rows = $('#table').bootstrapTable('getSelections');   
   if (rows.length == 0) {  
       layer.alert('请选择要删除的数据');  
       return;  
   }  
   //提示确认框
   layer.confirm('您确定要删除所选数据吗？', {
	  	btn: ['确定', '取消'] //可以无限个按钮
	}, function(index, layero){
		var ids = new Array();  
       //遍历所有选择的行数据，取每条数据对应的ID  
       $.each(rows, function(i, row) { 
           ids[i] = row[id];
       });  
        
       $.ajax({ 
       	type: 'post',
           url : 'delete',
           data: JSON.stringify(ids),
           success : function(data) { 
           	if(data.code == 0){
           		layer.alert('删除成功'); 
	            	$('#table').bootstrapTable('refresh');
           	}else{
           		layer.alert(data.msg); 
           	}
           },  
           error : function() {  
               layer.alert('服务器没有返回数据，可能服务器忙，请重试');  
           }  
       }); 
	});
}

//bootstraptable的通用表格设置
T.btTableOption = {
    /*url: 'list',*/
    pageSize:4,
	classes:"table table-hover",
    pagination: true,	//显示分页条  
    sidePagination: 'server',//服务器端分页
    search: true,
    pageList:[2,4,5,10],
    searchOnEnterKey:true,
    showPaginationSwitch:true, //是否显示表格搜索
    strictSearch: true,
    showColumns: true,                  //是否显示所有的列（选择显示的列）
    showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: true,               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
    showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                  //是否显示父子表
    toolbar: '#toolbar',
    striped: true     //设置为true会有隔行变色效果
}



//任务调度通用方法
T.doTask = function (id, url) {  
	
	 //返回所有选择的行，当没有选择的记录时，返回一个空数组  
   var rows = $('#table').bootstrapTable('getSelections');   
   if (rows.length == 0) {  
       layer.alert('请选择要操作的记录');  
       return;  
   }  
   //提示确认框
   layer.confirm('您确定要操作所选记录吗？', {
	  	btn: ['确定', '取消'] //可以无限个按钮
	}, function(index, layero){
		var ids = new Array();  
       //遍历所有选择的行数据，取每条数据对应的ID  
       $.each(rows, function(i, row) { 
           ids[i] = row[id];
       });  
        
       $.ajax({ 
       	type: 'post',
           url : url,
           data: JSON.stringify(ids),
           success : function(r) {
           	if(r.code == 1){
           		layer.alert('操作成功'); 
	            	$('#table').bootstrapTable('refresh');
           	}else{
           		layer.alert(data.data);
           	}
           },  
           error : function() {  
               layer.alert('服务器没有返回数据，可能服务器忙，请重试');  
           }  
       }); 
	});
}