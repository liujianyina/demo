layui.define(['jquery', 'layer','table','form','utils','menu'], function (exports) {
    let $ = layui.$,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        utils = layui.utils;

    let refresh;

    let active = {
        reloadNotDeal: function () {
            table.reload('order_list',{
                url:'/order/order_list_not_deal'
            });
        },

        reloadAll:function () {
            table.reload('order_list',{
                url:'/order/order_list_all'
            });
        },
        auto_refresh(){
            refresh = setInterval(function () {
                table.reload('order_list',{})
            },3000);

            layer.msg('开启成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        },

        close_refresh(){
            clearInterval(refresh);
            refresh = null;
            layer.msg('关闭成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        }
    };

    $('.layer').on('click', function () {
        let method = $(this).data('method');
        active[method] ? active[method].call(this) : '';
    });



    table.on('tool(order_list)', function (obj) {
        let data = obj.data;
        if (obj.event === 'down') {
            updateOrderState(data.sid,0)
        }else if (obj.event === 'ping'){
            updateOrderState(data.sid,1);
        }else if (obj.event === 'up'){
            updateOrderState(data.sid,2);
        }
    });


    function updateOrderState(orderSid,state){
            utils.http('/order/update_state',{
                orderSid:orderSid,
                state:state
            },function (res) {
                table.reload('order_list', {});
                layer.msg('操作成功', {
                    icon: 1,
                    anim: 5,
                    time: 1500
                });
            })
    }


    exports('order', {});
});