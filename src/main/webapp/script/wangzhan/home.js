
var vm = new Vue({
    el:'#dtApp',
    data:{
        adverts:{}
    },
    methods: {
        loadAdvert:function () {
            $.get("/advert/getAdverts.action",function (r) {
                var data=JSON.parse(r);
                if (data.code==1){
                    vm.adverts=data.data;
                    console.log(vm.adverts);
                }
            })
        }
    }
});
vm.loadAdvert();
