<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title></title>
</head>
<body>
<div class="pop-box">
    <table class="layui-table" lay-even
           lay-data="{url:'/order/user_order_list',where:{userSid:${userSid?c}},method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'user_list'}"
           lay-filter="user_list">
        <thead>
        <tr>
            <th lay-data="{field:'createTime',width:180}">创建时间</th>
            <th lay-data="{field:'productName'}">产品</th>
            <th lay-data="{templet: '#inx'}">方向</th>
            <th lay-data="{field:'money'}">实际盈亏</th>
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
</div>
<script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.config({
        base: '/static/js/'
    }).use('user');
</script>
</body>
</html>
