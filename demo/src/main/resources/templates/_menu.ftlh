<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree">

            <#assign SIDS = CURRENT_USER.resourceSids />
            <#list MENU_TREE as m>
                <li class="layui-nav-item">
                    <#if (SIDS?seq_contains(m.sid)) && (m.display == 1)>
                        <a class="" href="${m.url!'javascript:'}">${m.cname}</a>
                        <#assign children = m.children>
                        <#if children?size != 0>
                            <#include "_menu_children.ftlh" />
                        </#if>
                    </#if>
                </li>
            </#list>
        </ul>
    </div>
</div>