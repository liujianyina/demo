<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title>产品列表</title>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <#include "../_top.ftlh" />

    <#include "../_menu.ftlh" />

    <div class="layui-body">
        <div class="cont">
            <div class="cont-top">
                <div class="left">
                    <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                    <button type="button" class="layui-btn layui-btn-normal">搜索</button>
                </div>
                <div class="right">
                    <button type="button" class="layui-btn layui-btn-warm layer" data-method="create_product">新增</button>
                </div>
            </div>

            <div class="table-box">
                <table class="layui-table" lay-even
                       lay-data="{url:'/product/product_list',method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'product_list'}"
                       lay-filter="product_list">
                    <thead>
                    <tr>
                        <th lay-data="{field:'createTime'}">创建时间</th>
                        <th lay-data="{field:'name'}">产品名</th>
                        <th lay-data="{field:'symbol'}">产品代码</th>
                        <th lay-data="{templet: '#scroll'}">波动范围</th>
                        <th lay-data="{field:'state',templet: '#status'}">状态</th>
                        <th lay-data="{width:270,toolbar: '#toolbar'}">操作</th>
                    </tr>
                    </thead>
                </table>

                <script type="text/html" id="scroll">
                    {{ d.min }} - {{ d.max }}
                </script>

                <script type="text/html" id="status">
                    {{#  if(d.status == 1){ }}
                        <span class="red">开市</span>
                    {{#  } else{ }}
                        <span class="blue">休市</span>
                    {{#  } }}
                </script>

                <script type="text/html" id="toolbar">
                    <button type="button" class="layui-btn" lay-event="kline">K线图</button>
                    {{#  if(d.status == 1){ }}
                        <button type="button" class="layui-btn layui-bg-blue" lay-event="close">休市</button>
                    {{#  } else{ }}
                        <button type="button" class="layui-btn layui-bg-blue" lay-event="open">开市</button>
                    {{#  } }}
                    <button type="button" class="layui-btn bg-this" lay-event="del">删除</button>
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
    }).use('product');
</script>
</body>
</html>
