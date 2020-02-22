<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
</head>
<body>
    <div class="pop-box layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-block">
                <input type="text" name="username" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-block">
                <input type="password" name="password" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号：</label>
            <div class="layui-input-block">
                <input type="text" name="phone" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">用户组：</label>
            <div class="layui-input-block">
                <#list roles as r>
                    <input type="checkbox" value="${r.sid?c}" name="roleSids" title="${r.name}">
                </#list>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="user_create">立即提交</button>
            </div>
        </div>

    </div>
    <script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script>
        layui.config({
            base: '/static/js/'
        }).use('user');
    </script>
</body>
</html>
