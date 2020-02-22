layui.define(['jquery', 'table', 'form', 'utils', 'menu'], function (exports) {
    let $ = layui.$,
        table = layui.table,
        form = layui.form,
        utils = layui.utils;


    form.on('checkbox(state)', function (data) {

        let states = [];
        states.push(-1);

        $('input[name=state]:checked').each(function () {
            states.push($(this).val());
        });

        table.reload('withdraw_list', {
            where: {
                states: states
            }
        })
    });

    table.on('tool(withdraw_list)', function (obj) {
        let data = obj.data;
        if (obj.event === 'pass') {
            updateWithdrawState(data.sid, 1)
        } else if (obj.event === 'refuse') {
            updateWithdrawState(data.sid, 2);
        }
    });


    function updateWithdrawState(orderSid, state) {
        utils.http('/withdraw/update_state', {
            withdrawSid: orderSid,
            state: state
        }, function (res) {
            table.reload('withdraw_list', {});
            layer.msg('操作成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        })
    }


    exports('withdraw', {});
});