layui.define(['jquery', 'layer','form','utils','menu','upload'], function (exports) {
    let $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        utils = layui.utils;


    form.on('submit(acs_submit)',function (data) {
        utils.http('/admin/acs_update',data.field,function (res) {
            window.location.reload();
            layer.msg('操作成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        });
    });

    form.on('submit(acs_test)',function (data) {

        $('#res').text('');

        layer.prompt({title:'输入接收的手机号码'},function(val, index){
            data.field.phoneNumbers = val;

            utils.http('/admin/acs_test',data.field,function (res) {
                $('#res').text(res.data);
            });

            layer.close(index);
        });
    });

    form.on('submit(customer_submit)',function (data) {

        delete data.field.file;

        utils.http('/admin/update_customer_service_url',data.field,function () {
            layer.msg('操作成功', {
                icon: 1,
                anim: 5,
                time: 1500
            });
        });

    });

    form.on('submit(customer_test)',function (data) {
        window.open(data.field.customerService);
    });


    upload.render({
        elem: '#url'
        ,url: '/upload/upload_json'
        ,accept: 'file'
        ,done: function(res){
            $('#url').val(res.data);

            $('#img').attr('src','/jsons/' + res.data);
        }
    });


    exports('admin', {});
});