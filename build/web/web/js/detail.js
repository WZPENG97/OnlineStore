var app = new Vue({
    el: '#app',
    data: {
        product: '小米MIX',
        img: 'images/1.jpg',
        //产品信息
        items: [{
            name: '商品名称',
            value: '小米MIX'
        }, {
            name: '商品价格',
            value: '3999'
        }, {
            name: '商品类别',
            value: '2'
        }, {
            name: '商品库存',
            value: '1939'
        }],
        //购买数量
        num: 1,
        lists: [{
                //                商品描述
                id: 'gDecribe',
                // content: []
            },
            {
                //            评论
                id: 'gEvaluate',
                content: [{
                    time: '',
                    username: '',
                    comment: '该商品暂无评论'
                }]

            }, {
                id: 'gSecurity',
                // content: '全国联保'
            }
        ]
        //  	
    },
    methods: {
        up: function () {
            this.num++;
        },
        down: function () {
            if (this.num > 1) {
                this.num--;
            }
        },
        //        添加购物车
        add: function () {
            $.ajax({
                url: '/shop/AddShoppingCart',
                type: 'post',
                data: {
                    pid: location.href.split('=')[1],
                    counts: app.num
                },
                success: function (data) {
                    var data = JSON.parse(data);
                    if(data.state==0){
                        alert(data.message);
                        location.href='login.html';
                    }else if(data.state==2){
                        alert(data.message);
                    }else if(data.state==1){
                        alert(data.message);
                    }
                },
                error: function (data) {
                    console.log(data);
                }
            });
        },
        //        直接购买
        buy: function () {
            var a = confirm('确认购买');
            if (a === true) {
                $.ajax({
                    url: '/shop/ImmdediateBuy',
                    type: 'post',
                    data: {
                        pid: location.href.split('=')[1],
                        counts: app.num
                    },
                    success: function (data) {
                        var data = JSON.parse(data);
                        if(data.state==0){
                            alert(data.message);
                            location.href='login.html';
                        }else if(data.state==2){
                            alert(data.message);
                        }else if(data.state==3){
                            alert(data.message);
                        }else if(data.state==1){
                            alert(data.message);
                            location.reload();
                        }
                    },
                    error: function (data) {
                        console.log(data);
                    }
                });
            };
        }
    },
    mounted() {
        //  商品描述  
        $.ajax({
            url: '/shop/DesProduct',
            type: 'POST',
            data: 'pid='+location.href.split('=')[1],
            success: function (data) {
                var data = JSON.parse(data);
                app.product = data.pname;
                app.items[0].value = data.pname;
                app.items[1].value = data.price;
                app.items[2].value = data.cname;
                app.items[3].value = data.store;
                app.img = data.pimage;
                var a = data.description;
                var aa = a.split('，');
                app.lists[0].content = aa;
        
            },
            error: function (data) {
                console.log(data);
            }
        });
        //    商品评价
        $.ajax({
            url: '/shop/AllComment',
            type: 'POST',
            data: 'pid='+location.href.split('=')[1],
            success: function (data) {
                var data = JSON.parse(data);
                console.log(data);
                if(data.Comment.length>0){
                    app.lists[1].content = data.Comment;
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
        searchBtn();
        landState();
        showOption();
    }

});

