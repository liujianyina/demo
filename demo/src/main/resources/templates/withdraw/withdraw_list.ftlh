<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title>流水列表</title>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <#include "../_top.ftlh" />

    <#include "../_menu.ftlh" />

    <div class="layui-body">
        <div class="cont">
            <div class="cont-top">
                <div class="left layui-form">
                    <input type="checkbox" value="0" name="state" lay-skin="primary" title="未处理" lay-filter="state"
                           checked="">
                    <input type="checkbox" value="1" name="state" lay-skin="primary" title="已通过" lay-filter="state"
                           checked="">
                    <input type="checkbox" value="2" name="state" lay-skin="primary" title="拒绝" lay-filter="state"
                           checked="">
                </div>
                <div class="right">
                </div>
            </div>
            <div class="table-box">
                <table class="layui-table" lay-even
                       lay-data="{url:'/withdraw/withdraw_list',method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'withdraw_list'}"
                       lay-filter="withdraw_list">
                    <thead>
                    <tr>
                        <th lay-data="{field:'createTime',width:180}">时间</th>
                        <th lay-data="{field:'bankName'}">开户行</th>
                        <th lay-data="{field:'username'}">开户名</th>
                        <th lay-data="{field:'carNumber'}">卡号</th>
                        <th lay-data="{field:'money'}">提现金额</th>
                        <th lay-data="{field:'shouxu'}">手续费</th>
                        <th lay-data="{templet: '#state'}">状态</th>
                        <th lay-data="{width:180,toolbar: '#toolbar'}">操作</th>
                    </tr>
                    </thead>
                </table>

                <script type="text/html" id="state">
                    {{#  if(d.state == 0){ }}
                    <span class="red">未处理</span>
                    {{#  } else if(d.state == 1){ }}
                    <span class="blue">已通过</span>
                    {{#  } else { }}
                    <span class="blue">拒绝</span>
                    {{#  } }}
                </script>

                <script type="text/html" id="toolbar">
                    {{#  if(d.state == 0){ }}
                    <button type="button" class="layui-btn layui-bg-orange" lay-event="pass">通过</button>
                    <button type="button" class="layui-btn" lay-event="refuse">拒绝</button>
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
    }).use('withdraw');
</script>
</body>
</html>
