package com.rx.demo.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.rx.demo.utils.AjaxResult;
import com.rx.demo.utils.ShiroUtils;
import com.rx.demo.utils.Utils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        String[] roleNames = (String[]) mappedValue;

        if (!Utils.isEmpty(roleNames)) {
            Subject subject = ShiroUtils.getCurrentSubject();
            for (String roleName : roleNames) {
                if (subject.hasRole(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object o) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String s = req.getHeader("uniapp");

        if (!Utils.isEmpty(s)) {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("text/html;charset=utf-8");

            if (Utils.isNull(ShiroUtils.getCurrentUser())) {
                res.getWriter().write(JSONObject.toJSONString(AjaxResult.unlogin()));
            } else {
                res.getWriter().write(JSONObject.toJSONString(AjaxResult.unauthorized()));
            }
            return false;
        } else {
            return super.onAccessDenied(request, response);
        }

    }
}
