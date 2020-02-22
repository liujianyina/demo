layui.define(['jquery', 'layer', 'table', 'form', 'utils', 'menu'], function (exports) {
    let $ = layui.$,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        utils = layui.utils;

    let active = {


        userCreate: function () {
            layer.open({
                type: 2,
                area: ['800px', '600px'],
                title: '新增产品',
                fixed: false,
                scrollbar: false,
                content: ['/user/user_create', 'no'],
                shade: [0.1, '#fff'],
                anim: 2,
                resize: false,
                move: false,
                end: function () {
                    table.reload('user_list', {});
                }
            });
        },


    };

    $('.layer').on('click', function () {
        let method = $(this).data('method');
        active[method] ? active[method].call(this) : '';
    });

    table.on('tool(user_list)', function (obj) {
        let data = obj.data;
        if (obj.event === 'customer') {
            layer.open({
                type: 2,
                area: ['1000px', '600px'],
                title: '客户列表',
                fixed: false,
                scrollbar: false,
                content: ['/user/customer_list?userSid=' + data.sid, 'no'],
                shade: [0.1, '#fff'],
                anim: 2,
                resize: false,
                move: false
            });

        } else if (obj.event === 'order') {
            layer.open({
                type: 2,
                area: ['1000px', '600px'],
                title: '订单列表',
                fixed: false,
                scrollbar: false,
                content: ['/user/order_list?userSid=' + data.sid, 'no'],
                shade: [0.1, '#fff'],
                anim: 2,
                resize: false,
                move: false
            });
        } else if (obj.event === 'turnover') {
            layer.open({
                type: 2,
                area: ['1000px', '600px'],
                title: '流水列表',
                fixed: false,
                scrollbar: false,
                content: ['/user/turnover_list?userSid=' + data.sid, 'no'],
                shade: [0.1, '#fff'],
                anim: 2,
                resize: false,
                move: false
            });
        } else if (obj.event === 'add') {
            layer.prompt({title: '充值'}, function (val, index) {
                utils.http('/user/user_add', {
                    userSid: data.sid,
                    money: val
                }, function (res) {
                    layer.msg('操作成功', {
                        icon: 1,
                        anim: 5,
                        time: 1500
                    });
                    table.reload('user_list', {});
                });

                layer.close(index);
            });
        } else if (obj.event === 'reset') {
            layer.prompt({title: '重置密码'}, function (val, index) {
                utils.http('/user/reset_pass', {
                    userSid: data.sid,
                    password: val
                }, function (res) {
                    layer.msg('操作成功', {
                        icon: 1,
                        anim: 5,
                        time: 1500
                    });
                });


                layer.close(index);
            });
        } else if (obj.event === 'del') {
            layer.confirm('确认删除吗?', {icon: 4, title: '提示'}, function (index) {

                utils.http('/user/del', {
                    userSid: data.sid,
                }, function (res) {
                    layer.msg('操作成功', {
                        icon: 1,
                        anim: 5,
                        time: 1500
                    });
                    table.reload('user_list', {});
                });



                layer.close(index);
            });
        }


    });

    form.on('checkbox(role)', function (data) {

        let roleSids = [];
        roleSids.push(-1);

        $('input[name=roleSid]:checked').each(function () {
            roleSids.push($(this).val());
        });

        table.reload('user_list', {
            where: {
                roleSids: roleSids
            }
        })
    });

    form.on('submit(user_create)',function (data) {


        let roleSids = [];

        $('input[name=roleSids]:checked').each(function () {
            roleSids.push($(this).val());
        });

        data.field.roleSids = roleSids;

        utils.http('/user/user_create',data.field,function () {

            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            parent.layer.msg('操作成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        });
    });

    exports('user', {});
});