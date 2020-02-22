layui.define(['jquery', 'layer','table','form','utils','menu','upload'], function (exports) {
    let $ = layui.$,
        layer = layui.layer,
        table = layui.table,
        form = layui.form,
        upload = layui.upload,
        utils = layui.utils;

    let active = {
        create_product: function () {
            layer.open({
                type: 2,
                area: ['800px', '600px'],
                title: '新增产品',
                fixed: false,
                scrollbar: false,
                content: ['/product/product_create', 'no'],
                shade: [0.1, '#fff'],
                anim: 2,
                resize: false,
                move: false,
                end: function () {
                    table.reload('product_list', {});
                }
            });
        }
    };

    $('.layer').on('click', function () {
        let method = $(this).data('method');
        active[method] ? active[method].call(this) : '';
    });

    form.on('submit(product_create)',function (data) {
        utils.http('/product/product_create',data.field,function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            parent.layer.msg('操作成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        });
    });

    table.on('tool(product_list)', function (obj) {
        let data = obj.data;
        if (obj.event === 'kline') {
            layer.open({
                type: 2,
                area: ['800px', '650px'],
                title: '[' + data.name + ']' + 'k线图',
                fixed: false,
                scrollbar: false,
                content: ['/product/product_kline?productSid=' + data.sid, 'no'],
                shade: [0.1, '#fff'],
                anim: 2,
                resize: false,
                move: false,
                // end: function () {
                //     table.reload('trip1_driver_list', {});
                // }
            });
        }else if (obj.event === 'open'){
            updateStatus(data.sid,1);
        }else if (obj.event === 'close'){
            updateStatus(data.sid,0);
        }else if (obj.event === 'del'){

            layer.confirm('该操作不可恢复，是否继续?', {icon: 4, title:'警告'}, function(index){

                utils.http('/product/product_del',{
                    productSid:data.sid
                },function () {
                    table.reload('product_list', {});
                    layer.msg('操作成功', {
                        icon: 1,
                        anim: 5,
                        time: 1500
                    });
                });

                layer.close(index);
            });


        }
    });


    function updateStatus(productSid,status){
            utils.http('/product/update_status',{
                productSid:productSid,
                status:status
            },function (res) {
                table.reload('product_list', {});
                layer.msg('操作成功', {
                    icon: 1,
                    anim: 5,
                    time: 1500
                });
            })
    }

    upload.render({
        elem: '#upload'
        ,url: '/upload/upload_json'
        ,accept: 'file'
        ,done: function(res){
            $('#upload').val(res.data);
        }
    });


    exports('product', {});
});