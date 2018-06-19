var app = new Vue({
  el: '#app',
  data: {
    product: '',
    img:'images/小米6.jpg',
    items:[{
    	name:'商品名称',
    	value:''
    },{
    	name:'商品价格',
    	value:''
    },{
    	name:'商品类别',
    	value:''
    },{
    	name:'商品库存',
    	value:''
    },{
    	name:'商品描述',
    	value:''
    }],
    num:1,
  },
  methods:{
  	up:function(){
  		this.num++;
  	},
  	down:function(){
  		if(this.num>0){
  			this.num--;
  		} 	
  	},
  	add:function(){
  		alert('添加成功');
  	},
  	buy:function(){
  		alert('购买成功');
  		this.items[4].value-=this.num;
  		console.log(this.items[4].value);
  	}
  }
})

var pid = location.href.split('=')[1];
$.ajax({
	url: '/shop/DesProduct',
	type: 'POST',
	data: 'pid='+pid,
	success: function(data){
		var data = JSON.parse(data);
		app.product = data.pname;
		app.items[0].value = data.pname;
		app.items[1].value = data.price;
		app.items[2].value = data.cname;
		app.items[3].value = data.store;
		// console.log(data.description);
		// var reg = /\\n/g;
		// var des = data.description.replace(reg,'<br/>');
		// console.log(des);
		console.log(data.description);
		app.items[4].value = data.description;
		app.img = data.pimage;
	},
	error: function(data){
		console.log(data);
	}
})
