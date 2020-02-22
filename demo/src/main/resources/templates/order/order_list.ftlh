<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title>订单列表</title>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <#include "../_top.ftlh" />

    <#include "../_menu.ftlh" />

    <div class="layui-body">
        <div class="cont">
            <div class="cont-top">
                <div class="left">
                    <button type="button" class="layui-btn layui-btn-normal layer" data-method="reloadNotDeal">未处理
                    </button>
                    <button type="button" class="layui-btn layui-btn-normal layer" data-method="reloadAll">所有</button>
                    <button type="button" class="layui-btn layui-btn-normal layer" data-method="auto_refresh">自动刷新</button>
                    <button type="button" class="layui-btn layui-btn-normal layer" data-method="close_refresh">关闭刷新</button>
                </div>
                <div class="right">
                </div>
            </div>

            <div class="table-box">
                <table class="layui-table" lay-even
                       lay-data="{url:'/order/order_list_not_deal',method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'order_list'}"
                       lay-filter="order_list">
                    <thead>
                    <tr>
                        <th lay-data="{field:'createTime',width:180}">创建时间</th>
                        <th lay-data="{field:'username'}">用户名</th>
                        <th lay-data="{field:'productName'}">产品名</th>
                        <th lay-data="{templet: '#inx'}">方向</th>
                        <th lay-data="{field:'orderMoney'}">订单金额</th>
                        <th lay-data="{field:'time'}">间隔时间</th>
                        <th lay-data="{field:'state',templet: '#status'}">状态</th>
                        <th lay-data="{field:'success',templet: '#success'}">订单结果</th>
                        <th lay-data="{width:210,toolbar: '#toolbar'}">操作</th>
                    </tr>
                    </thead>
                </table>

                <script type="text/html" id="inx">
                    {{#  if(d.inx == 1){ }}
                    <span class="red">买涨</span>
                    {{#  } else{ }}
                    <span class="blue">买跌</span>
                    {{#  } }}
                </script>

                <script type="text/html" id="success">
                    {{#  if(d.success == 0){ }}
                    <span class="red">未完成</span>
                    {{#  } else{ }}
                    <span class="blue">已完成</span>
                    {{#  } }}
                </script>

                <script type="text/html" id="status">
                    {{#  if(d.state == 0){ }}
                    <span class="blue">默认为亏</span>
                    {{#  } else if(d.state == 1){ }}
                    <span>平仓</span>
                    {{#  } else if(d.state == 2){ }}
                    <span class="red">盈利</span>
                    {{#  } }}
                </script>

                <script type="text/html" id="toolbar">
                    {{#  if(d.success == 0){ }}
                        <button type="button" class="layui-btn" lay-event="down">亏</button>
                        <button type="button" class="layui-btn layui-bg-blue" lay-event="ping">平</button>
                        <button type="button" class="layui-btn layui-bg-green" lay-event="up">涨</button>
                    {{#  } }}
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
    }).use('order');
</script>
</body>
</html>
