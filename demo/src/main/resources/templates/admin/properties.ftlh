<!DOCTYPE html>
<html>
<head>
    <#include "../_header.ftlh" />
    <title>参数设置</title>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <#include "../_top.ftlh" />

    <#include "../_menu.ftlh" />

    <div class="layui-body">
        <div class="cont">
            <div class="layui-tab">
                <ul class="layui-tab-title">
                    <li class="layui-this">短信接口配置</li>
                    <li>客服接口配置</li>
                    <li>logo及app信息</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <div class="layui-form layui-form-pane">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">keyId：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="accessKeyId" autocomplete="off"
                                                   placeholder="请输入accessKeyId" lay-verify="required"
                                                   class="layui-input" value="${accessKeyId!}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">secret：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="accessSecret" autocomplete="off"
                                                   placeholder="请输入accessSecret" lay-verify="required"
                                                   class="layui-input" value="${accessSecret!}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">签名：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="signName" autocomplete="off"
                                                   placeholder="请输入SignName" lay-verify="required" class="layui-input"
                                                   value="${signName!}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">模版id：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="templateCode" autocomplete="off"
                                                   placeholder="请输入TemplateCode" lay-verify="required"
                                                   class="layui-input" value="${templateCode!}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button type="submit" class="layui-btn" lay-submit=""
                                                    lay-filter="acs_submit">立即提交
                                            </button>
                                            <button type="reset" class="layui-btn layui-btn-primary" lay-submit=""
                                                    lay-filter="acs_test">测试
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6" style="padding: 20px">
                                <div style="height: 50px;line-height: 50px">
                                    正确返回结果：<span style="color: #FF5722">(有"Code":"OK"即为发送成功)</span>
                                </div>
                                <div>
                                    {"Message":"OK","RequestId":"XXXXX","BizId":"XXXX","Code":"OK"}
                                </div>
                                <div style="height: 50px;line-height: 50px">
                                    测试返回结果：
                                </div>
                                <div id="res">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-tab-item">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <div class="layui-form layui-form-pane">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">客服地址：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="customer_service" autocomplete="off"
                                                   placeholder="请输入" lay-verify="required"
                                                   class="layui-input" value="${customer_service!}">
                                        </div>
                                    </div>

                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button type="submit" class="layui-btn" lay-submit=""
                                                    lay-filter="customer_submit">立即提交
                                            </button>
                                            <button type="reset" class="layui-btn layui-btn-primary" lay-submit=""
                                                    lay-filter="customer_test">测试
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6" style="padding: 20px">
                                <a href="https://www.cckefu.net" target="_blank" class="layui-btn">点此申请客服接口以及下载客户端</a>

                            </div>
                        </div>

                    </div>
                    <div class="layui-tab-item">
                        <div class="layui-row">
                            <div class="layui-col-xs6">
                                <div class="layui-form layui-form-pane">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">标题：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="app_name" autocomplete="off"
                                                   placeholder="请输入app_name" lay-verify="required"
                                                   class="layui-input" value="${app_name!}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">小标题：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="app_small_name" autocomplete="off"
                                                   placeholder="请输入app_small_name" lay-verify="required"
                                                   class="layui-input" value="${app_small_name!}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">图标：</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="url" id="url" autocomplete="off" class="layui-input"
                                                   value="${url!}" readonly>
                                        </div>
                                    </div>

                                    <img src="/jsons/${url!}" id="img">

                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button type="submit" class="layui-btn" lay-submit=""
                                                    lay-filter="customer_submit">立即提交
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-xs6" style="padding: 20px">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <#include "../_footer.ftlh" />
</div>
<script src="/static/src/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.config({
        base: '/static/js/'
    }).use('admin');
</script>
</body>
</html>
