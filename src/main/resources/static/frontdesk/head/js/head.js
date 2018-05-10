/*头部文件跳转*/
$(function() {

    /*跳转至登录*/
    $("#toLogin").click(function(){
        window.location.href="/h5/head/login";
    });

    /*跳转至注册*/
    $("#toRegist").click(function(){
        window.location.href="/h5/head/regist";
    });

    /*跳转至首页*/
    $("#toIndex").click(function(){
       window.location.href="/h5/head/index";
    });

    /*跳转至全部商品*/
    $("#toProdList").click(function(){
        window.location.href="/h5/head/prodlist";
    });

    /*跳转到购物车 无需登录*/
    $("#toCart").click(function(){
        window.location.href="/h5/head/cart";
    });

    /*跳转到订单 需登录*/
    $("#toOrderAdd").click(function(){
        window.location.href="/h5/head/orderadd";
    });

    /*跳转到订单列表 需登录*/
    $("#toOrderList").click(function(){
        window.location.href="/h5/head/orderlist";
    });

    /*跳转到订单列表 需登录*/
    $("#toLogout").click(function(){
        window.location.href="/h5/head/toLogout";
    });

});