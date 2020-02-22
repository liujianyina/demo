<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title>用户列表</title>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <#include "../_top.ftlh" />

    <#include "../_menu.ftlh" />

    <div class="layui-body">
        <div class="cont">
            <div class="cont-top">
                <div class="left layui-form" lay-filter="role_sids">
                    <#list roles as r>
                        <input type="checkbox" value="${r.sid}" name="roleSid" lay-skin="primary" title="${r.name}" lay-filter="role" checked="">
                    </#list>
                </div>
                <div class="right">
                    <button type="button" class="layui-btn layui-btn-warm layer" data-method="userCreate">新增</button>
                </div>
            </div>

            <div class="table-box">
                <table class="layui-table" lay-even
                       lay-data="{url:'/user/user_list',method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'user_list'}"
                       lay-filter="user_list">
                    <thead>
                    <tr>
                        <th lay-data="{field:'createTime',width:180}">创建时间</th>
                        <th lay-data="{field:'sid'}">邀请码</th>
                        <th lay-data="{field:'username'}">用户名</th>
                        <th lay-data="{field:'phone'}">手机号</th>
                        <th lay-data="{field:'money'}">账户余额</th>
                        <th lay-data="{width:510,toolbar: '#toolbar'}">操作</th>
                    </tr>
                    </thead>
                </table>

                <script type="text/html" id="toolbar">
                    <button type="button" class="layui-btn layui-bg-orange" lay-event="customer">客户</button>
                    <button type="button" class="layui-btn" lay-event="order">订单</button>
                    <button type="button" class="layui-btn" lay-event="turnover">流水</button>
                    <button type="button" class="layui-btn layui-bg-orange" lay-event="add">充值</button>
                    <button type="button" class="layui-btn layui-bg-blue" lay-event="reset">重置密码</button>
                    <button type="button" class="layui-btn" lay-event="del">删除</button>
                </script>

            </div>
        </div>
    </div>
    <#include "../_footer.ftlh" />
</div>
<script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.config({
        base: '/static/js/'
    }).use('user');
</script>
</body>
</html>
