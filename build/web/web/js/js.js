var username = document.getElementById('username');
var password = document.getElementById('password');
var email = document.getElementById('email');
var phoneNumber = document.getElementById('phoneNumber');
var postNumber = document.getElementById('postNumber');
var address = document.getElementById('address');


function regValid() {
    var usernameReg = /[^a-zA-Z]/g;
    var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+\.){1,63}[a-z0-9]+$/;
    var phoneNumberReg = /^1[0-9]{10}/;
    var postNumberReg = /^[1-9][0-9]{5}$/;
    if (usernameReg.test(username.value)) {
        alert('请输入格式正确的用户名');
        return false;
    } else if (password.value.length < 6) {
        alert('密码长度过短');
        return false;
    } else if (!emailReg.test(email.value)) {
        alert('邮箱格式不正确');
        return false;
    } else if (!phoneNumberReg.test(phoneNumber.value)) {
        alert('手机号码不正确');
        return false;
    } else if (!postNumberReg.test(postNumber.value)) {
        alert('邮政编码不正确');
        return false;
    } else if (address.value == "") {
        alert('地址不能为空');
        return false;
    } else {
        return true;
    }
}

function loginValid() {
    var usernameReg = /[^a-zA-Z]/g;
    if (usernameReg.test(username.value)) {
        alert('请输入格式正确的用户名');
        return false;
    } else if (password.value.length < 6) {
        alert('密码长度过短');
        return false;
    } else {
        return true;
    }
}

//user register
function register() {
    if (regValid()) {
        $.ajax({
            url: '/shop/UserRegister',
            type: 'POST',
            data: $('#regForm').serialize(),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == "1") {
                    alert(data.message);
                    location.href = "login.html";
                } else {
                    alert(data.message);
                }
            },
            error: function (data) {
                console.log(data);
            }
        })
    }
}

// show userOpt panel
function showOption() {
    $('.username').click(function () {
        $('.userOpt').toggle('slow');
    })
}
showOption();

// get land state
function landState() {
    $.ajax({
        url: '/shop/session',
        type: 'POST',
        success: function (data) {
            // console.log(data);
            var data = JSON.parse(data);
            if (data.sessionstate === 1) {
                $('.navOpt').css('display', 'none');
                $('.user').css('display', 'inline-block');
                $('.name').text(data.username);
                $("#shoppingCart").attr('href', 'shopcart.html?username=' + data.username);
                $("#indent").attr('href', 'indent.html?username=' + data.username);
                $("#userInfo").attr('href', 'userinfo.html?username=' + data.username);
                $("#logOut").attr('href', 'javascript:logout()');
                return true;
            } else {
                return false;
            }
        },
        error: function (data) {
            console.log(data);
        }
    })
}


function logout(username) {
    console.log(username);
}

function minus() {
    $('.minus').click(function () {
        var count = $(this.parentNode.children[3])[0].value;
        if (count > 1) {
            $(this.parentNode.children[3])[0].value--;
            console.log();
            $(this.parentNode.children[1]).text($(this.parentNode.children[3])[0].value);
        }
    });
}

function plus() {
    $('.plus').click(function () {
        $(this.parentNode.children[3])[0].value++;
        $(this.parentNode.children[1]).text($(this.parentNode.children[3])[0].value);
    });
}

// add turn function
function turnDetail() {
    var productItem = $('.productItem');
    if (productItem.length > 0) {
        for (var i = 0; i < productItem.length; i++) {
            productItem[i].addEventListener('click', function () {
                location.href = "detail.html?pid=" + $(this).attr('pid');
            }, false);
        }
    }
}

// add search button function
function searchBtn() {
    $('.searchBtn')[0].addEventListener('click', function () {
        var value = $('.searchBox').val() || $('.searchBox').attr('placeholder');
        location.href = 'search.html?value=' + value;
        // console.log("search.html?value="+value);
    }, false);
}

function logout() {
    $.ajax({
        url: '/shop/SessionInvalidate',
        type: 'post',
        success: function (data) {
            var data = JSON.parse(data);
            if (data.state == 0) {
                alert(data.message);
            } else if (data.state == 1) {
                alert(data.message);
                location.href = 'index.html';
            }
        }
    })
}


// get product list
function getAllProduct() {
    var productClothes = $('#productClothes');
    var productDigital = $('#productDigital');
    var productBooks = $('#productBooks');
    var productGame = $('#productGame');
    var productLife = $('#productLife');
    var productMakeup = $('#productMakeup');
    var productSports = $('#productSports');
    $.ajax({
        url: '/shop/AllProduct',
        type: 'POST',
        success: function (data) {
            var categroy = JSON.parse(data).categroy;
            // console.log(categroy);
            productClothes.append(categroyText(categroy.Clothing_Acc));
            productDigital.append(categroyText(categroy.Digital_products));
            productBooks.append(categroyText(categroy.Book_office));
            productGame.append(categroyText(categroy.Game));
            productLife.append(categroyText(categroy.daily_use));
            productMakeup.append(categroyText(categroy.Makeup_products));
            productSports.append(categroyText(categroy.Sports_products));
            turnDetail();
        }
    });
}

// get product list text to append 
function categroyText(categroy) {
    var text = '';
    for (var i = 0; i < categroy.length; i++) {
        text += '<div class=\"productItem\" pid=\"' + categroy[i].pid + '\">' +
            '<img src=\"' + categroy[i].pimage + '\" alt=\"product\">' +
            '<p>' + categroy[i].pname + '</p>' +
            '<span>&yen;</span><span>' + categroy[i].price + '</span>' +
            '</div>';
    }
    return text;
}

//add new product
function addProduct() {
    var form = new FormData($('#addProductForm'));
    console.log(form);
    $.ajax({
        url:'/shop/AddProduct',
        type:'post',
        data: form,
        processData:false,
        contentType:false,
        success:function(data){
            console.log(data);
        },
        error:function(data){
            console.log(data);
        }
    })
    // $.ajax({
    //     url: '/shop/AddProduct',
    //     type: 'post',
    //     data: $('#addProductForm').serialize(),
    //     success: function (data) {
    //         var data = JSON.parse(data);
    //         if (data.state == 0) {
    //             alert('请先登录管理员帐号');
    //             location.href = 'adminLogin.html';
    //         } else if (data.state == 1) {
    //             alert(data.message);
    //             location.href = 'admin.html';
    //         } else if (data.state == 2) {
    //             alert(data.message);
    //         }
    //     }
    // })
}

//show all product in admin page
function showAllPro() {
    $.ajax({
        url: '/shop/AdminAllProduct',
        type: 'post',
        success: function (data) {
            var data = JSON.parse(data);
            if (data.state == 0) {
                alert(data.message);
                location.href = 'adminLogin.html';
            } else {
                var cate = data.categroy;
                var text = '';
                for (var a in cate) {
                    for (var i = 0; i < cate[a].length; i++) {
                        var product = cate[a][i];
                        text += '<tr>' +
                            '<td>' + product.pname + '</td>' +
                            '<td>' + product.cname + '</td>' +
                            '<td>' + product.price + '</td>' +
                            '<td>' + product.store + '</td>' +
                            '<td><a href=\"changeDetail.html?pid=' + product.pid +
                            '\" class="glyphicon glyphicon-edit" title="修改商品信息" pid=\"' + product.pid +
                            '\"></a>' +
                            '<button type="button" class="glyphicon glyphicon-trash" title="删除该商品" pid=\"' +
                            product.pid + '\"></button></td>' +
                            '</tr>';
                    }
                }
                $('table').append(text);
                deleteProduct();
            }
        }
    })
}

// delete product
function deleteProduct() {
    $('.glyphicon-trash').click(function () {
        $.ajax({
            url: '/shop/DelProduct',
            type: 'post',
            data: 'pid=' + $(this).attr("pid"),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 0) {
                    alert(data.message);
                    location.href = 'adminLogin.html';
                } else if (data.state == 1) {
                    alert(data.message);
                    location.reload();
                } else if (data.state == 2) {
                    alert(data.message);
                }
            },
            error: function (data) {
                console.log(data);
            }
        })
    })
}

//admin login
function adminLogin() {
    if (loginValid()) {
        $.ajax({
            url: '/shop/AdminLogin',
            type: 'POST',
            data: $('#loginForm').serialize(),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == "1") {
                    location.href = "admin.html";
                } else {
                    alert(data.message);
                }
            },
            error: function (data) {
                console.log(data);
            }
        })
    }
}


//get product detail
function getDetail() {
    var detail;
    $.ajax({
        url: '/shop/DesProduct',
        type: 'POST',
        async: false,
        data: 'pid=' + location.href.split('=')[1],
        success: function (data) {
            var data = JSON.parse(data);
            detail = data;
        },
        error: function (data) {
            console.log(data);
        }
    });
    $('#pname').val(detail.pname);
    $('#price').val(detail.price);
    $('#store').val(detail.store);
    $('#description').val(detail.description);
    $('#pid').val(detail.pid);
}

//change product detail
function changeDetail() {
    $.ajax({
        url: '/shop/UpdateProductInf',
        type: 'post',
        data: $('#changeDetailForm').serialize(),
        success: function (data) {
            var data = JSON.parse(data);
            if (data.state == 0) {
                alert(data.message);
                location.href = 'adminLogin.html';
            } else if (data.state == 1) {
                alert(data.message);
                location.reload();
            } else if (data.state == 2) {
                alert(data.message);
            }
        }
    })
}

//show user's own indent
function showIndent() {
    $.ajax({
        url: '/shop/ShowIndent',
        type: 'POST',
        success: function (data) {
            var data = JSON.parse(data);
            console.log(data);
            if (data.state == 0) {
                alert('用户未登录，请先登录');
                location.href = 'login.html';
            } else if (data.isExist == 0) {
                $('table').append("<tr>用户没有任何订单</tr>");
            } else {
                var indent = data.indent;
                var text = '';
                for (var i = 0; i < indent.length; i++) {
                    if (indent[i].indentsta == '已完成'|| indent[i].indentsta == '待发货') {
                        text += '<tr>' +
                            '<td>' + indent[i].indentid + '</td>' +
                            '<td>' + indent[i].pname + '</td>' +
                            '<td>' + indent[i].cprice + '</td>' +
                            '<td>' + indent[i].counts + '</td>' +
                            '<td>' + indent[i].ctime + '</td>' +
                            '<td>' + indent[i].indentsta + '</td>' +
                            '<td><button type=\"button\" onclick=\"reciveProduct(\'' + indent[i].indentid + '\')\" class="glyphicon glyphicon-check btn" disabled title=\"确认收货\"></button></td>' +
                            '</tr>';
                    } else if (indent[i].indentsta == '未评论') {
                        text += '<tr>' +
                            '<td>' + indent[i].indentid + '</td>' +
                            '<td>' + indent[i].pname + '</td>' +
                            '<td>' + indent[i].cprice + '</td>' +
                            '<td>' + indent[i].counts + '</td>' +
                            '<td>' + indent[i].ctime + '</td>' +
                            '<td>' + indent[i].indentsta + '</td>' +
                            '<td><button type=\"button\" onclick=\"showComment(\'' + indent[i].pid + '\',\''+ indent[i].indentid + '\')\" class="glyphicon glyphicon-pencil btn"  title=\"评论\"></button></td>' +
                            '</tr>';
                    } else {
                        text += '<tr>' +
                            '<td>' + indent[i].indentid + '</td>' +
                            '<td>' + indent[i].pname + '</td>' +
                            '<td>' + indent[i].cprice + '</td>' +
                            '<td>' + indent[i].counts + '</td>' +
                            '<td>' + indent[i].ctime + '</td>' +
                            '<td>' + indent[i].indentsta + '</td>' +
                            '<td><button type=\"button\" onclick=\"reciveProduct(\'' + indent[i].indentid + '\')\" class="glyphicon glyphicon-check btn" title=\"确认收货\"></button></td>' +
                            '</tr>';
                    }
                }
                $('table').append(text);
            }
        }
    });
}

//recive product option
function reciveProduct(id) {
    $.ajax({
        url: '/shop/ReceiveProduct',
        type: 'POST',
        data: 'indentid=' + id,
        success: function (data) {
            var data = JSON.parse(data);
            if (data.state == 0) {
                alert(data.message);
                location.href = 'login.html';
            } else if (data.state == 1) {
                alert(data.message);
                location.reload();
            } else if (data.state == 2) {
                alert(data.message);
                location.reload();
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

//show comment panner
function showComment(pid,indentid) {
    $('.comment').addClass('show');
    $('.shadow').addClass('show');
    comment(pid,indentid);
}

//comment function
function comment(pid,indentid) {
    $('.commentBtn').click(function () {
        $.ajax({
            url: '/shop/UserComment',
            type: 'post',
            data: {
                pid:pid,
                indentid:indentid,
                comment:$('#comment').val()
            },
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 0) {
                    alert('评论失败，请重试');
                } else if (data.state == 1) {
                    alert(data.message);
                    location.reload();
                }
            },
            error: function (data) {
                console.log(data);
            }
        })
    })
}

//show all indent in admin page
function showAllIndent() {
    var text = '';
    $.ajax({
        url: '/shop/ViewAllIndent',
        type: 'post',
        async: false,
        success: function (data) {
            var data = JSON.parse(data);
            var indent = data.AllIndent;
            if (data.state == 0) {
                alert(data.message);
                location.href = 'adminLogin.html';
            } else {
                for (var i = 0; i < indent.length; i++) {
                    if (indent[i].indentsta !== "待发货") {
                        text += '<tr>' +
                            '<td>' + indent[i].indentid + '</td>' +
                            '<td>' + indent[i].pname + '</td>' +
                            '<td>' + indent[i].cprice + '</td>' +
                            '<td>' + indent[i].counts + '</td>' +
                            '<td>' + indent[i].ctime + '</td>' +
                            '<td>' + indent[i].indentsta + '</td>' +
                            '<td><button type=\"button\" data-indentid=\"' + indent[i].indentid +
                            '\" class=\"glyphicon glyphicon-check btn" title=\"发货\" disabled></button></td>' +
                            '</tr>';
                    } else {
                        text += '<tr>' +
                            '<td>' + indent[i].indentid + '</td>' +
                            '<td>' + indent[i].pname + '</td>' +
                            '<td>' + indent[i].cprice + '</td>' +
                            '<td>' + indent[i].counts + '</td>' +
                            '<td>' + indent[i].ctime + '</td>' +
                            '<td>' + indent[i].indentsta + '</td>' +
                            '<td><button type=\"button\" data-indentid=\"' + indent[i].indentid +
                            '\" class=\"glyphicon glyphicon-check btn" title=\"发货\"></button></td>' +
                            '</tr>';
                    }
                }
            }
        }
    });
    $('table').append(text);
    sendProduct()
}

//add send product option
function sendProduct() {
    $('.glyphicon-check').click(function () {
        $.ajax({
            url: '/shop/Shipment',
            type: 'post',
            data: 'indentid=' + $(this).attr('data-indentid'),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 0) {
                    alert(data.message);
                    location.href = 'adminLogin.hrml';
                } else if (data.state == 1) {
                    alert(data.message);
                    location.reload();
                } else if (data.state == 2) {
                    alert('发货失败，请重试');
                }
            }
        })
    })
}


//user login
function login() {
    if (loginValid()) {
        $.ajax({
            url: '/shop/UserLogin',
            type: 'POST',
            data: $('#loginForm').serialize(),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == "1") {
                    location.href = "index.html";
                } else {
                    alert(data.message);
                }
            },
            error: function (data) {
                console.log(data);
            }
        })
    }
}

//searh product
function search() {
    var value = location.href.split('=')[1] || $('.searchInput').val() || $('.searchInput').attr('placeholder');
    var text = "";
    $.ajax({
        url: '/shop/FindProduct',
        type: 'POST',
        data: 'pname=' + value,
        success: function (data) {
            var data = JSON.parse(data);
            var product = data.product;

            if (data.isExist !== 0) {
                for (var i = 0; i < product.length; i++) {
                    text += '<div class=\"productItem\" pid=\"' + product[i].pid + '\">' +
                        '<img src=\"' + product[i].pimage + '\" alt=\"product\">' +
                        '<p>' + product[i].pname + '</p>' +
                        '<span>&yen;</span><span>' + product[i].price + '</span>' +
                        '</div>';
                }
                $('.searchNote').text("一共查询到" + product.length + "件商品");
                $('.searchContainer').append(text);
                turnDetail();
            } else {
                $('.searchNote').text("没有查询到商品");
            }
            $('.searchInput').val(decodeURIComponent(value));
        },
        error: function (data) {
            console.log(data);
        }
    })
}

//show product in cart
function showCart() {
    $.ajax({
        url: '/shop/ShowCart',
        type: 'POST',
        data: 'username=' + location.href.split('=')[1],
        success: function (data) {
            var data = JSON.parse(data);
            var cart = data.AllCart;
            var text = '';
            if (cart.length > 0) {
                for (var i = 0; i < cart.length; i++) {
                    text += '<tr>' +
                        '<td><input type=\"checkbox\" name=\"checkbox1\" value=\"' + cart[i].pid +
                        '\"></td>' +
                        '<td>' + cart[i].pname + '</td>' +
                        '<td>' + cart[i].price + '</td>' +
                        '<td><button class=\"glyphicon glyphicon-minus minus\" type=\"button\"></button>' +
                        '<span class=\"count\" name=\"count\">' + cart[i].counts + '</span>' +
                        '<button class=\"glyphicon glyphicon-plus plus\" type=\"button\"></button>' +
                        '<input type=\"text\" name=\"counts\" class=\"countValue\" value=\"' + cart[i].counts +
                        '\">' +
                        '</td>' +
                        '<td><button class=\"glyphicon glyphicon-trash delete\" type=\"button\" pid=\"' +
                        cart[i].pid + '\"></button></td>' +
                        '</tr>';
                }
            } else {
                text = '<tr>购物车内暂无商品</tr>';
            }
            $('table').append(text);
            minus();
            plus();
            cartDelete()
        }
    });
}

//buy product in cart
function buy() {
    $.ajax({
        url: '/shop/CartBuy',
        type: 'POST',
        data: $('#cartForm').serialize(),
        success: function (data) {
            var data = JSON.parse(data);
            if (data.state == 2) {
                alert(data.message);
                return false;
            } else if (data.state == 0) {
                alert("用户未登陆，请重新登陆");
                location.href = 'login.html';
            } else if (data.state == 3) {
                alert('数据库错误，请联系管理员');
            } else if (data.state == 4) {
                alert('余额不足，请充值');
                return false;
            } else if (data.state == 1) {
                alert('购买成功');
                location.reload();
            }else if (data.state == 5) {
                alert(data.message);
            }
        }
    });
}

//delete product in cart
function cartDelete() {
    $(".delete").click(function () {
        console.log($(this).attr('pid'));
        $.ajax({
            url: '/shop/UserDelProduct',
            type: 'POST',
            data: 'pid=' + $(this).attr('pid'),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 1) {
                    alert(data.message);
                    location.reload();
                } else if (data.state == 0) {
                    alert(data.message);
                }
            }
        })
    })
}

//get user info
function getUserInfo() {
    var userinfo;
    $.ajax({
        url: '/shop/UserRequest',
        type: 'POST',
        async: false,
        success: function (data) {
            var data = JSON.parse(data);
            userinfo = data;
        },
        error: function (data) {
            console.log(data);
        }
    });
    return userinfo;
}

//show user info
function showInfo() {
    var userinfo = getUserInfo();
    $('#sex').val(userinfo.sex);
    $('#email').val(userinfo.email);
    $('#phoneNumber').val(userinfo.telephone);
    $('#postNumber').val(userinfo.postcode);
    $('#address').val(userinfo.address);
    $('#money').text(userinfo.money);
}

//save user info
function save() {
    if (infoChangeValid()) {
        $.ajax({
            url: '/shop/UserUpdateInf',
            type: 'POST',
            data: $('#userinfoForm').serialize(),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 0) {
                    alert(data.message);
                    location.href = 'login.html';
                } else if (data.state == 1) {
                    alert(data.message);
                    location.reload();
                } else if (data.state == 2) {
                    alert('更新失败，请重试或联系管理员');
                }
            }
        });
    }
}

//valid user info change
function infoChangeValid() {
    var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+\.){1,63}[a-z0-9]+$/;
    var phoneNumberReg = /^1[0-9]{10}/;
    var postNumberReg = /^[1-9][0-9]{5}$/;
    if (!emailReg.test(email.value)) {
        alert('邮箱格式不正确');
        return false;
    } else if (!phoneNumberReg.test(phoneNumber.value)) {
        alert('手机号码不正确');
        return false;
    } else if (!postNumberReg.test(postNumber.value)) {
        alert('邮政编码不正确');
        return false;
    } else if (address.value == "") {
        alert('地址不能为空');
        return false;
    } else {
        return true;
    }
}

//recharge
function recharge() {
    var money = $('#rechargeMoney').val();
    var moneyReg = /^[1-9][0-9]*$/;
    if (moneyReg.test(money)) {
        $.ajax({
            url: '/shop/UserRecharge',
            type: 'POST',
            data: $('#rechargeForm').serialize(),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 0) {
                    alert('充值失败，请重试或联系管理员');
                } else if (data.state == 1) {
                    alert(data.message);
                    location.reload();
                }
            }
        });
    } else {
        alert('请输入正确的金额');
    }
}

//change user password
function changePassword() {
    if ($('#newPassword').val().length < 6) {
        alert('密码长度过短');
    } else {
        $.ajax({
            url: '/shop/UserUpdatePwd',
            type: 'POST',
            data: $('#changePassForm').serialize(),
            success: function (data) {
                var data = JSON.parse(data);
                if (data.state == 0) {
                    alert(data.message);
                    location.href = 'login.html';
                } else if (data.state == 1) {
                    alert('更新成功，请重新登陆');
                    location.href = 'login.html';
                } else if (data.state == 2) {
                    alert('更新失败，请与管理员联系');
                } else if (data.state == 3) {
                    alert(data.message);
                }
            }
        });
    }
}