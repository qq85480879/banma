$(function () {

    /*表单提交验证*/
    $("#js-check").click(function () {

        var check = formObj.checkForm();
        if (check) {
            getParam();
        } else {
            alert("请填写正确表单信息");
        }


    });

    function getParam() {
        var param = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };
        var user = JSON.stringify(param);
        ajaxUrl(param);

    };

    function getUsername() {
        var param = {
            username: $("#username").val(),
        };
        return param;
    }

    function ajaxUrl(param) {
        $.ajax({
            type: "POST",
            url: "/api/regist",
            dataType: 'json',
            data: param,
            success: function (result) {
                alert(result.status);
                if(result.status == 200){
                    /*跳转至登录页面*/
                    window.location.href="/h5/head/login";
                } else {
                    formObj.setMsg("all",result.msg);
                }
            },
            error: function (result) {
            }
        });
    };


    /*form-control类的 失去焦点后 对应提示信息*/
    $(".form-control").blur(function () {
        _id = this.id;
        _name = this.name;
        formObj.checkNull(_id, _name + "不能为空");
    });

    /*form-control类的 获得焦点后 就清空对应提示信息*/
    $(".form-control").focus(function () {
        _id = this.id;
        formObj.setMsg(_id, "");
    });

    /*用户名不为空还要判断是否*/
    $("#username").blur(function () {
        var check = formObj.checkNull("username", "用户名不能为空");
        if (check) {
            formObj.checkUsername("username","");
        }

    });

    $("#password2").blur(function () {
        var check = formObj.checkNull("password2", "确认密码不能为空");
        if (check) {
            formObj.checkPassword("password", "两次密码不一致");
        }
    });

    $("#password").blur(function () {
        var check = formObj.checkNull("password", "密码不能为空");
        if (check) {
            formObj.checkPassword("password", "两次密码不一致");
        }
    });

    $("#email").blur(function () {
        var check = formObj.checkNull("email", "邮箱不能为空");
        if (check) {
            formObj.checkEmail("email", "邮箱格式错误");
        }
    });


    /* 注册表单的js校验 */
    var formObj = {
        /* 检查输入项是否为空 */
        "checkNull": function (name, msg) {
            var value = $("input[id='" + name + "']").val().trim();

            //清空之前的提示消息
            formObj.setMsg(name, "");

            if (value == "") {
                formObj.setMsg(name, msg);
                return false;
            }
            return true;
        },
        /* 设置错误提示消息 */
        "setMsg": function (name, msg) {
            $("#" + name + "_msg").html(msg);
            $("#" + name + "_msg").css("color", "red");
        }
        ,

        /* 检查邮箱格式是否正确  */
        "checkEmail": function (name, msg) {
            var email = $("input[id='" + name + "']").val().trim();

            if (email == "") {
                formObj.setMsg("email", "邮箱不能为空");
            }

            if (email != "") {
                var reg = /^\w+@\w+(\.\w+)+$/;
                if (!reg.test(email)) {
                    formObj.setMsg(name, msg);
                    return false;
                }
            }
            return true;
        }
        ,
        /* 检查两次密码是否一致 */
        "checkPassword": function (name, msg) {
            var psw1 = $("input[id='" + name + "']").val().trim();
            var psw2 = $("input[id='" + name + "2']").val().trim();
            if (psw2 == "") {
                formObj.setMsg(name + "2", "确认密码不能为空");
            }

            if (psw1 != "" && psw2 != "") {
                if (psw1 != psw2) {
                    name2 = name + "2";
                    formObj.setMsg(name2, msg);
                    return false;
                }
            }
            return true;
        },

        /*检查用户名是否存在*/
        "checkUsername": function (name, msg) {
            var check = false;
            var username = $("input[id='" + name + "']").val().trim();
            if(username != ""){
                var param = getUsername();
                $.ajax({
                    type: "POST",
                    url: "/api/regist/checkName",
                    dataType: 'json',
                    data: param,
                    async:false,
                    success: function (result) {
                        check = result;
                        if(result){
                            formObj.setMsg("username","用户名可用");
                        } else {
                            formObj.setMsg("username","用户名已存在");
                        }
                    },

                });
            }
            return check;

        },

        /* 注册表单js校验  总检查*/
        "checkForm": function () {
            //1.非空校验
            var res1 = formObj.checkNull("username", "用户名不能为空");
            var res2 = formObj.checkNull("password", "密码不能为空");
            var res3 = formObj.checkNull("password2", "确认密码不能为空");
            var res4 = formObj.checkNull("email", "邮箱不能为空");

            //2.两次密码是否一致
            var res5 = formObj.checkPassword("password", "两次密码不一致");

            //3.邮箱格式是否正确
            var res6 = formObj.checkEmail("email", "邮箱格式不正确");

            /*检查用户名是否存在*/
            var res7 = formObj.checkUsername("username","用户名不可用");

            return res1 && res2 && res3 && res4 && res5 && res6 && res7;

        },
    }

    /* 利用ajax实现用户名是否存在的校验 */
    function ajaxCheckeUsername(thisobj) {

        //非空校验
        if (!formObj.checkNull("username", "用户名不能为空!")) {
            return;
        }

        //获取用户名
        var username = thisobj.value;

        //使用ajax检查用户名是否存在
        $("#username_msg").load("/h5/regist/toAjax", {"username": username});
    }


});