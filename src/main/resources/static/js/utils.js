$(function () {

    /*表单提交验证*/
    $("#js-check").click(function () {
        /*var res = formObj.checkForm();
        if(res){

        } else {
            alert("输入有误&hearts;请确认内容无误~")
        }*/
    });

    var formObj = {
        /*表单校验*/
        "checkForm": function () {
            var res1 = this.checkNull("username");
            var res2 = this.checkNull("password");
            var res3 = this.checkNull("password2");
            var res4 = this.checkNull("nickname");
            var res5 = this.checkNull("email");
            var res6 = this.checkNull("valistr");

            var res7 = this.checkPassWord("password");

            var res8 = this.checkEmail("email");

            return res1 && res2 && res3 && res4 && res5 && res6 && res7 && res8;
        },
        /*非空校验*/
        "checkNull": function (name) {
            var value = $("#" + name).val();
            /*清空之前的消息*/
            this.setMsg("");

            if (value.trim() == "") {
                this.setMsg("表单项不能为空");
                return false;
            }
            return true;

        },
        /*检查两次密码是否一致*/
        "checkPassWord": function (name) {
            var psd1 = $("#" + name).val();
            var psd2 = $("#" + name + "2").val();
            this.setMsg("");
            if (psd1 != psd2) {
                this.setMsg("两次密码不一致")
                return false;
            }
            return true;
        },
        /* 检查邮箱格式是否正确 */
        "checkEmail": function (name) {
            var email = $("#" + name).val();
            var regExp = /^\w+@\w+(\.\w+)+$/;

            if (email != "") {
                if (!regExp.test(email)) {
                    this.setMsg("邮箱格式不正确");
                    return false;
                }
            }
            return true;
        },
        /*设置提示消息*/
        "setMsg": function (msg) {
            var $span = $("#msg");
            if (msg != "") {
                $span.html(msg);
            } else {
                $span.html("请填写表单信息");
            }
            $span.css("color", "red")
        },

    }

});