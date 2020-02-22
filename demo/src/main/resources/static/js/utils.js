layui.define(['jquery', 'layer'], function (exports) {
    let $ = layui.$,
        layer = layui.layer;

    let utils = {
        http: function (url, data, success, options) {
            let index = {},
                type = typeof data === 'function';
            if (type) {
                options = success;
                success = data;
                data = {};
            }
            options = options || {};

            return $.ajax({
                type: options.type || 'post',
                dataType: options.dataType || 'json',
                data: data,
                url: url,
                beforeSend: function () {
                    index = layer.load(0);
                },
                success: function (res) {
                    layer.close(index);
                    if (res.code === 0) {
                        success && success(res);
                    } else {
                        layer.msg(res.data || '请求异常，请重试!', {
                            icon: 2,
                            anim: 6,
                            time: 1500
                        });
                    }
                },
                error: function (e) {
                    layer.close(index);
                    layer.msg('请求异常，请重试', {
                        shift: 6
                    });
                    options.error && options.error(e);
                }
            })
        }
    };



    exports('utils', utils);
});