/*
 * 产品部-产品库列表 业务JS代码
 * @author shaolong
 * @Last update 2017/07/18 by shaolong
 *
 */
//$.ajaxSetup({contentType: "application/json; charset=utf-8"});
var  currentCount=1;
define(function (require, exports, module) {
    require("plugins/laydate/laydate");//引入日期插件
    require("common/common") //引入公共JS工具库
    require("common/menu");  //引入多级菜单导航模块
    var Handlebars = require('handlebars'); //引入模板引擎
    var transFormat = require('transFormat'); //引入模板引擎Helper
    require("plugins/pager/pager");//引入模板分页模块
    var layer = require("layer");//引入Layer弹窗插件
    require("plugins/layer/skin/default/layer.css");  //进入Layer弹窗依赖样式
    require("common/dialog");  //引入Layer封装消息框
    require("plugins/easyui/1_5_2/easyloader");//引入easyui
    require("plugins/easyui/1_5_2/themes/default/easyui.css");//引入easyui css
    require("plugins/validation/1_16/jquery.validate");//表单校验插件
    require("plugins/validation/1_16/jquery.metadata");//表单校验插件
    require("../plugins/jquery.bigautocomplete");//表单自动补全插件(本地使用)



    //获取请求参数$("input[name*='man']")
    $("select[name='templateType']").change(function () {  //产品类型
        ajaxUrl(getParam());
    });
    $("select[name='productStatus']").change(function () {  //产品状态
        ajaxUrl(getParam());
    });
    $("select[name='auditStatus']").change(function () {  //审核状态
        ajaxUrl(getParam());
    });
    $("#conditions").click(function () {  //查询条件name或code
        ajaxUrl(getParam());
    });
    $(".getFormData").click(function () {
        ajaxUrl(getParam());
    })
    $("#addProductType").change(function () {  //创建人
        loadCopyGrid(getCopyGridParam());
    })
    $("#searchbtn").click(function () {
        loadCopyGrid(getCopyGridParam());
    });


    function getParam() {

        var queryParam = {
            templateType: $("[name=templateType] option:selected").val(),
            auditStatus: $("[name=auditStatus] option:selected").val(),
            status: $("[name=productStatus] option:selected").val(),
            creater: creater,
            endTimeStart: $("#modifiedTimeStart").val(),
            endTimeEnd: $("#modifiedTimeEnd").val(),
            createdTimeStart: $("#createdTimeStart").val(),
            createdTimeEnd: $("#createdTimeEnd").val(),
            searchKey: $("#inputConditions").val()
        };

    }
    ajaxUrl(getParam());//初始化调用
    function ajaxUrl(param) {
        $.ajax({
            type: 'get',
            url: contextPath+'/api/products/page' ,
            dataType: 'json',
            data: param,
            success: function (response) {
                var pagevo = response.data;
                console.log(pagevo);
                //总记录数
                var tpl = require('../products/tpl/list.tpl');
                var template = Handlebars.compile(tpl);
                var html = template(pagevo);
                $('#listDataView').html(html);
                //分页插件
                if (pagevo!=null&&pagevo.totalPage> 0) {
                    kkpager.generPageHtml({
                        pno: currentCount,//当前页码   需求根据实际数据修改
                        total: pagevo.totalPage,//总页码 需求根据实际数据修改
                        totalRecords: pagevo.totalCount,//总数据条数  需求根据实际数据修改
                        isShowFirstPageBtn: true,
                        isShowLastPageBtn: true,
                        isShowTotalPage: true,
                        isShowTotalRecords: true,
                        isGoPage: false,
                        lang: {
                            prePageText: '<b>&lt;</b>',
                            nextPageText: '<b>&gt;</b>'
                        },
                        mode: 'click',//click模式匹配getHref 和 click
                        click: function (n, total, totalRecords) {
                            $.ajax({
                                type: "get",
                                url: contextPath+"/api/products/page?page=" + n +"&random="+Math.random(),
                                dataType: "json",
                                data:getParam(),
                                success: function (result) {
                                    var tpl = require('../products/tpl/list.tpl');
                                    var template = Handlebars.compile(tpl);
                                    var html = template(result.data);
                                    $('#listDataView').html(html);
                                }
                            });
                            this.selectPage(n); //处理完后可以手动条用selectPage进行页码选中切换
                        }
                    });
                } else {
                    $("#kkpager").html('<div class="nodata"></div>');
                }
                //分页结束
            },
            error: function (error) {
                errorMsg("加载失败，请重试！")
            }
        })
    }





});