<!DOCTYPE html>
<html>
<head>
    <#include "_header.ftlh" />
    <title>系统登陆</title>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css" />
</head>
<body>

<div class="login-box layui-form layui-form-pane">

    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" autocomplete="off" placeholder="请输入用户名" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" autocomplete="off" placeholder="请输入密码" lay-verify="required" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <button class="layui-btn layui-btn-primary reset-btn layer" data-method="reset">重置</button>
            <button class="layui-btn layui-btn-warm submit-btn" lay-submit lay-filter="login">提交</button>
        </div>
    </div>

</div>

<script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.config({
        base: '/static/js/'
    }).use('login');
</script>
</body>
</html>
