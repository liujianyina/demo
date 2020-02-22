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
            <div class="table-box">
                <table class="layui-table" lay-even
                       lay-data="{url:'/turnover/turnover_list',method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'order_list'}"
                       lay-filter="order_list">
                    <thead>
                    <tr>
                        <th lay-data="{field:'createTime',width:180}">时间</th>
                        <th lay-data="{field:'username'}">用户名</th>
                        <th lay-data="{field:'money'}">变动金额</th>
                        <th lay-data="{field:'balance'}">本次余额</th>
                        <th lay-data="{templet: '#type'}">类别</th>
                        <th lay-data="{field:'text',width:400}">详情</th>
                    </tr>
                    </thead>
                </table>

                <script type="text/html" id="type">
                    {{#  if(d.type == 0){ }}
                    <span class="red">充值</span>
                    {{#  } else if(d.type == 1){ }}
                    <span class="blue">投资</span>
                    {{#  } else { }}
                    <span class="blue">提现</span>
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
