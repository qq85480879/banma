$(function () {

    /*表单提交验证*/
    $("#js-check").click(function () {

        var check = formObj.checkForm();
        if (check) {
            getParam();
            return false;
        } else {
            alert("请填写正确表单信息");
            return false;
        }


    });

    function getParam() {
        var param = {
            username: $("#username").val(),
            password: $("#password").val(),
        };
        ajaxUrl(param);

    };

    function ajaxUrl(param) {
        $.ajax({
            type: "POST",
            url: "/api/login",
            dataType: 'json',
            data: param,
            success: function (result) {
                if(result.status == 200){
                    /*跳转至首页*/
                    window.location.href="/h5/head/index";
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



        /* 注册表单js校验  总检查*/
        "checkForm": function () {
            //1.非空校验
            var res1 = formObj.checkNull("username", "用户名不能为空");
            var res2 = formObj.checkNull("password", "密码不能为空");
            return res1 && res2;

        },
    }

});