var username = document.getElementById('username');
var password = document.getElementById('password');
var email = document.getElementById('email');
var phoneNumber = document.getElementById('phoneNumber');
var postNumber = document.getElementById('postNumber');
var address = document.getElementById('address');


function regValid(){
    var usernameReg = /[^a-zA-Z]/g;
    var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+\.){1,63}[a-z0-9]+$/;
    var phoneNumberReg = /^1[0-9]{10}/;
    var postNumberReg = /^[1-9][0-9]{5}$/;
    if(usernameReg.test(username.value)){
        alert('请输入格式正确的用户名');
        return false;
    }else if(password.value.length<6){
        alert('密码长度过短');
        return false;
    }else if(!emailReg.test(email.value)){
        alert('邮箱格式不正确');
        return false;
    }else if(!phoneNumberReg.test(phoneNumber.value)){
        alert('手机号码不正确');
        return false;
    }else if(!postNumberReg.test(postNumber.value)){
        alert('邮政编码不正确');
        return false;
    }else if(address.value==""){
        alert('地址不能为空');
        return false;
    }else{
        return true;
    }
}

function loginValid(){
    var usernameReg = /[^a-zA-Z]/g;
    if(usernameReg.test(username.value)){
        alert('请输入格式正确的用户名');
        return false;
    }else if(password.value.length<6){
        alert('密码长度过短');
        return false;
    }else{
        return true;
    }
}

// show userOpt panel
$('.username').click(function(){
    $('.userOpt').toggle('slow');
})

// get land state
function landState(){
    $.ajax({
        url: '/shop/session',
        type: 'POST',
        success: function(data){
            // console.log(data);
            var data = JSON.parse(data);
            if(data.sessionstate===1){
                $('.navOpt').css('display','none');
                $('.user').css('display','inline-block');
                $('.name').text(data.username);
                $("#shoppingCart").attr('href','shopcart.html?username='+data.username);
                $("#indent").attr('href','indent.html?username='+data.username);
                $("#userInfo").attr('href','userinfo.html?username='+data.username);
                $("#logOut").attr('href','javascript:logout()');
                return true;
            }else{
                return false;
            }
        },
        error: function(data){
            console.log(data);
        }
    })
}
landState();

function logout(username){
    console.log(username);
}

function minus(){
    $('.minus').click(function(){
        var count = $(this.parentNode.children[3])[0].value;
        if(count > 1){
            $(this.parentNode.children[3])[0].value--;
            console.log();
            $(this.parentNode.children[1]).text($(this.parentNode.children[3])[0].value);
        }
    });
}

function plus(){
    $('.plus').click(function(){
        $(this.parentNode.children[3])[0].value++;
        $(this.parentNode.children[1]).text($(this.parentNode.children[3])[0].value);
    });
}

window.onload = function () {
    var productItem = $('.productItem');
    if (productItem.length > 0) {
        for (var i = 0; i < productItem.length; i++) {
            productItem[i].addEventListener('click', function () {
                location.href = "detail.html?pid=" + $(this).attr('pid');
            }, false);
        }
    }

    $('.searchBtn')[0].addEventListener('click', function () {
        var value = $('.searchBox').val() || $('.searchBox').attr('placeholder');
        location.href = 'search.html?value=' + value;
        // console.log("search.html?value="+value);
    }, false);
}

function logout(){
    $.ajax({
        url:'/shop/SessionInvalidate',
        type:'post',
        success:function(data){
            var data = JSON.parse(data);
            if(data.state==0){
                alert(data.message);
            }else if(data.state==1){
                alert(data.message);
                location.href = 'index.html';
            }
        }
    })
}