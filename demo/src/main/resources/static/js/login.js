layui.define(['jquery', 'form', 'utils'], function (exports) {

    let $ = layui.$,
        form = layui.form,
        utils = layui.utils;

    $(function () {
        let height = $(window).height();
        $('.login-body').css('height', (height - 95) + "px"); //根据浏览器屏幕，调节login-body的高度
    });

    form.on('submit(login)', function (data) {
        utils.http('/login', data.field, function (res) {
            self.location = '/product/product_list';
        });
        return false;
    });

    let active = {
        reset: function () {
            $('input').val('');
        }
    };

    $('.layer').on('click', function () {
        let method = $(this).data('method');
        active[method] ? active[method].call(this) : '';
    });

    exports('login', {});
});



