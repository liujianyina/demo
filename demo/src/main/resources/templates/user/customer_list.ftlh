<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title></title>
</head>
<body>
<div class="pop-box">
    <table class="layui-table" lay-even
           lay-data="{url:'/user/customer_list',where:{userSid:${userSid?c}},method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'user_list'}"
           lay-filter="user_list">
        <thead>
        <tr>
            <th lay-data="{field:'createTime',width:180}">创建时间</th>
            <th lay-data="{field:'username'}">用户名</th>
            <th lay-data="{field:'phone'}">手机号</th>
            <th lay-data="{field:'money'}">账户余额</th>
        </tr>
        </thead>
    </table>
</div>
<script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.config({
        base: '/static/js/'
    }).use('user');
</script>
</body>
</html>
