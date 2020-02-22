<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
</head>
<body>
    <div class="pop-box layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label">产品名称：</label>
            <div class="layui-input-block">
                <input type="text" name="name" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">字母符号：</label>
            <div class="layui-input-block">
                <input type="text" name="symbol" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">波动范围：</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="min" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="max" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分类：</label>
            <div class="layui-input-block">
                <select lay-verify="required">
                    <option value="0" selected="">外汇</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">文件：</label>
            <div class="layui-input-block">
                <input type="text" name="filename" autocomplete="off" class="layui-input" id="upload" readonly>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="product_create">立即提交</button>
            </div>
        </div>

    </div>
    <script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script>
        layui.config({
            base: '/static/js/'
        }).use('product');
    </script>
</body>
</html>
