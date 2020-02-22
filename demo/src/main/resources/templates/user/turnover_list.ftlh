<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title></title>
</head>
<body>
<div class="pop-box">
    <table class="layui-table" lay-even
           lay-data="{url:'/turnover/user_turnover_list',where:{userSid:${userSid?c}},method:'post',page:true,loading:true,limit:10,limits: [10,20,50,80,100],id:'user_list'}"
           lay-filter="user_list">
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
        <script type="text/html" id="type">
            {{#  if(d.type == 0){ }}
            <span class="red">充值</span>
            {{#  } else if(d.type == 1){ }}
            <span class="blue">投资</span>
            {{#  } else { }}
            <span class="blue">提现</span>
            {{#  } }}
        </script>
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
